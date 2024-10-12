package com.example.DS_Assignment2_Monitoring_Microserv.dtos;

import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

@JsonDeserialize(using = MeasurementsDTODeserializer.class)
public class MeasurementsDTO {

    private int ID;

    private LocalTime timestamp;

    private DevDTO device_id;

    private double measurement_value;

    public MeasurementsDTO(){}

    public MeasurementsDTO(int ID, LocalTime timestamp, DevDTO devDTO, double measurement_value) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.device_id = devDTO;
        this.measurement_value = measurement_value;
    }

    public MeasurementsDTO(LocalTime timestamp, DevDTO devDTO, double measurement_value) {

        this.timestamp = timestamp;
        this.device_id = devDTO;
        this.measurement_value = measurement_value;
    }



    @Override
    public String toString() {
        return "JsonMessage{" +
                "timestamp=" + timestamp +
                ", device_id=" + device_id.getID() +
                ", measurement_value=" + measurement_value +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public DevDTO getDevDTO() {
        return device_id;
    }

    public void setDevDTO(DevDTO devDTO) {
        this.device_id = devDTO;
    }

    public double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(double measurement_value) {
        this.measurement_value = measurement_value;
    }
}
