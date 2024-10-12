package com.example.DS_Assignment2_Simulator.producer;

import java.time.LocalTime;

public class JsonMessage {


    private String timestamp;

    private int device_id;
    private double measurement_value;

    public JsonMessage(String timestamp, int device_id, double measurement_value) {
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
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(double measurementValue) {
        this.measurement_value = measurementValue;
    }
}
