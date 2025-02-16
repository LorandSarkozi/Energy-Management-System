package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartMeterSimulator {
    private static final String QUEUE_NAME = "energy_data_queue";
    private static final String SENSOR_FILE = "src/main/resources/sensor.csv";
    private static final String DEVICE_ID1 = "4b0675b4-7526-4cd8-af00-1925bad0e029";
    private static final String DEVICE_ID2 = "3ac821df-55ad-44e2-bf0a-aa4c80fc14c7";

    public static void main(String[] args) {
        // ExecutorService to run two parallel threads for two devices
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Runnable for DEVICE_ID1
        Runnable device1Task = () -> simulateSmartMeter(DEVICE_ID1, SENSOR_FILE);

        // Runnable for DEVICE_ID2
        Runnable device2Task = () -> simulateSmartMeter(DEVICE_ID2, SENSOR_FILE);

        // Submit both tasks to the executor
        executorService.submit(device1Task);
        executorService.submit(device2Task);

        // Shutdown executor after tasks complete
        executorService.shutdown();
    }

    private static void simulateSmartMeter(String deviceId, String sensorFile) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
             BufferedReader br = new BufferedReader(new FileReader(sensorFile))) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length > 0) {
                    String measurementValue = values[0].trim();
                    String timestamp = String.valueOf(System.currentTimeMillis());

                    JsonObject message = new JsonObject();
                    message.addProperty("timestamp", timestamp);
                    message.addProperty("device_id", deviceId);
                    message.addProperty("measurement_value", Double.parseDouble(measurementValue));

                    channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes(StandardCharsets.UTF_8));
                    System.out.println("Sent (" + deviceId + "): " + message);
                } else {
                    System.out.println("Skipping invalid line for device " + deviceId + ": " + line);
                }


                Thread.sleep(6000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
