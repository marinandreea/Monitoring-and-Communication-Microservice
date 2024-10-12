package com.example.DS_Assignment2_Monitoring_Microserv.dtos.builder;

import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DevDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.MeasurementsDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Measurements;

public class MeasurementsBuilder {

    public MeasurementsBuilder(){}

    public static MeasurementsDTO toMeasurementsDTO(Measurements measurements){
        return new MeasurementsDTO(measurements.getID(),measurements.getTimestamp(), DevDTOBuilder.toDevDTO(measurements.getDevice_id()),measurements.getMeasurement_value());
    }

    public static Measurements toEntity(MeasurementsDTO measurementsDTO){
        return new Measurements(measurementsDTO.getID(), measurementsDTO.getTimestamp(), DevDTOBuilder.toEntity(measurementsDTO.getDevDTO()),measurementsDTO.getMeasurement_value());
    }
}
