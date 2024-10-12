package com.example.DS_Assignment2_Monitoring_Microserv.service;

import com.example.DS_Assignment2_Monitoring_Microserv.dtos.MeasurementsDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.builder.MeasurementsBuilder;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Measurements;
import com.example.DS_Assignment2_Monitoring_Microserv.repository.DeviceRepository;
import com.example.DS_Assignment2_Monitoring_Microserv.repository.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MonitoringService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private MeasurementsRepository measurementsRepository;


    public Optional<List<Measurements>> getListOfMeasurementsForChart(int device_id){

        Optional<Device> device = deviceRepository.findById(device_id);
        Iterable<Measurements> measurements = measurementsRepository.findAll();
        if(device.isPresent()){
            List<Measurements> list = new ArrayList<>();
            for(Measurements m:measurements){
                if(m.getDevice_id().getID() == device_id){
                    list.add(m);
                }
            }

            Collections.sort(list, (m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()));
            List<Measurements> sixItemTotals = new ArrayList<>();

            for (int i = 0; i < list.size(); i += 6) {
                // Get a sublist of six consecutive items
                List<Measurements> sublist = list.subList(i, Math.min(i + 6, list.size()));

                // Calculate the total measurement_value for the sublist
                double totalMeasurementValue = sublist.stream()
                        .mapToDouble(Measurements::getMeasurement_value)
                        .sum();

                // Create a new Measurements object with the total measurement_value
                Measurements sixItemTotal = Measurements.builder()
                        .device_id(device.get())
                        .timestamp(sublist.get(0).getTimestamp())  // Use the timestamp of the first item in the sublist
                        .measurement_value(totalMeasurementValue)
                        .build();

                // Add the new Measurements object to the result list
                sixItemTotals.add(sixItemTotal);
            }
            return Optional.of(sixItemTotals);


        }
        return Optional.empty();
    }

}
