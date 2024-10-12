package com.example.DS_Assignment2_Monitoring_Microserv.consumer;

import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DeviceDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.MeasurementsDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.builder.MeasurementsBuilder;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Measurements;
import com.example.DS_Assignment2_Monitoring_Microserv.repository.DeviceRepository;
import com.example.DS_Assignment2_Monitoring_Microserv.repository.MeasurementsRepository;
import com.example.DS_Assignment2_Monitoring_Microserv.webSocket.Message;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class RabbitMQConsumer {

    @Autowired
    private MeasurementsRepository measurementsRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private double sumBuffer = 0;
    private int bufferCount = 0;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(MeasurementsDTO measurementsDTO) {

        LOGGER.info(String.format("Received message -> %s", measurementsDTO.toString()));

        Optional<Device> device = deviceRepository.findById(measurementsDTO.getDevDTO().getID());


        if (device.isPresent()) {
            Measurements measurements = MeasurementsBuilder.toEntity(measurementsDTO);
            measurementsRepository.save(measurements);

            double measurementValue = measurementsDTO.getMeasurement_value();

            // Accumulate the measurement value
            sumBuffer += measurementValue;
            bufferCount++;


            // Check if the buffer count reaches 6
            if (bufferCount == 6) {
                // Calculate the average and save the result
                double averageValue = sumBuffer;
                measurements.setMeasurement_value(averageValue);


                // Reset the buffer
                sumBuffer = 0;
                bufferCount = 0;
                System.out.println(device.get().getMaxHourlyEnergyConsumption());
                if (averageValue > device.get().getMaxHourlyEnergyConsumption()) {
                    Message websocketMessage = new Message("Average value exceeds the limit with value " + averageValue
                    + " at timestamp: " + measurements.getTimestamp() + "!");
                    // Send a message through WebSocket to the user corresponding to the device
                    LOGGER.info("Sending WebSocket message: {}", websocketMessage.toString());

                    sendMessageToUser(String.valueOf(device.get().getUser_id()), websocketMessage);
                }
            }
        } else {
            LOGGER.info(String.format("No device with this id"));
        }
    }
    private void sendMessageToUser(String userId, Message message) {
        // Send the message to the specified user
        messagingTemplate.convertAndSendToUser(userId, "/userNotification", message);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.nameD}"})
    public void consumeD(Device receivedDevice) {
        LOGGER.info(String.format("Received message -> %s", receivedDevice.toString()));

        Optional<Device> existingDeviceOptional = deviceRepository.findById(receivedDevice.getID());

        if (existingDeviceOptional.isPresent()) {
            Device existingDevice = existingDeviceOptional.get();
            existingDevice.setDescription(receivedDevice.getDescription());
            existingDevice.setAddress(receivedDevice.getAddress());
            existingDevice.setMaxHourlyEnergyConsumption(receivedDevice.getMaxHourlyEnergyConsumption());
            existingDevice.setUser_id(receivedDevice.getUser_id());

            deviceRepository.save(existingDevice);
            LOGGER.info(String.format("Device with ID %d updated successfully.", existingDevice.getID()));
        } else {
            LOGGER.info(String.format("No device with ID %d found.", receivedDevice.getID()));
        }
    }

}


