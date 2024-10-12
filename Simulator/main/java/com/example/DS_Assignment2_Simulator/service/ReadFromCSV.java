package com.example.DS_Assignment2_Simulator.service;

import com.example.DS_Assignment2_Simulator.producer.JsonMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ReadFromCSV {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.key.name}")
    private String key;

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadFromCSV.class);

    public ReadFromCSV(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }




    public void readCsvFile(String filePath, int userInput) throws IOException, CsvException, InterruptedException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {

            String[] row;
            while ((row = csvReader.readNext()) != null) {
                processRow(row);
                JsonMessage jsonMessage = new JsonMessage(String.valueOf(LocalTime.now()), userInput, Double.parseDouble(row[0]));
                sendJsonMessage(jsonMessage);


                Thread.sleep(1000);
            }
        }
    }

    // You can define a method to process each row as needed
    private void processRow(String[] row) {
        // Implement your logic to process each row here
        System.out.print("Values read: ");
        for (String value : row) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

//    public void sendMessage(String message){
//
//        LOGGER.info(String.format("Message sent -> %s", message));
//        rabbitTemplate.convertAndSend(exchange,key, message);
//    }

    public void sendJsonMessage(JsonMessage jsonMessage){
        LOGGER.info(String.format("Message sent -> %s", jsonMessage.toString()));
        rabbitTemplate.convertAndSend(exchange,key, jsonMessage);
    }
}
