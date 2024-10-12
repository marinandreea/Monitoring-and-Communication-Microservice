package com.example.DS_Assignment2_Monitoring_Microserv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
public class Measurements {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;
    @Column
    private LocalTime timestamp;
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Device device_id;
    @Column
    private double measurement_value;

    public Measurements(int ID, LocalTime timestamp, Device device_id, double measurement_value) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.device_id = device_id;
        this.measurement_value = measurement_value;
    }

    public Measurements( LocalTime timestamp, Device device_id, double measurement_value) {

        this.timestamp = timestamp;
        this.device_id = device_id;
        this.measurement_value = measurement_value;
    }

    @Override
    public String toString() {
        return "JsonMessage{" +
                "timestamp=" + timestamp +
                ", device_id=" + device_id +
                ", measurement_value=" + measurement_value +
                '}';
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public Device getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Device device_id) {
        this.device_id = device_id;
    }

    public double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(double measurementValue) {
        this.measurement_value = measurementValue;
    }
}
