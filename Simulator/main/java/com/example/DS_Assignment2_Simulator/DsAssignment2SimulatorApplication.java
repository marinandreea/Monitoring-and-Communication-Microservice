package com.example.DS_Assignment2_Simulator;

import com.example.DS_Assignment2_Simulator.service.ReadFromCSV;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DsAssignment2SimulatorApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DsAssignment2SimulatorApplication.class, args);
		// Launch the Spring application and get the application context
		ConfigurableApplicationContext context = SpringApplication.run(DsAssignment2SimulatorApplication.class, args);

		// Get the CsvReaderService bean from the application context
		ReadFromCSV csvReaderService = context.getBean(ReadFromCSV.class);

		// Specify the file path to your CSV file
		String filePath = "D:\\FACULTATE\\AN 4\\DS\\DS_Assignment2_Simulator\\DS_Assignment2_Simulator\\DS_Assignment2_Simulator\\sensor.csv";

		try {
			// Read user input
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter a number: ");
			int userInput = scanner.nextInt();
			// Call the readCsvFile method
			csvReaderService.readCsvFile(filePath, userInput);
		} catch (IOException | CsvException | InterruptedException e) {
			e.printStackTrace(); // Handle the exception appropriately
		} finally {
			// Close the application context
			context.close();
		}
	}


}
