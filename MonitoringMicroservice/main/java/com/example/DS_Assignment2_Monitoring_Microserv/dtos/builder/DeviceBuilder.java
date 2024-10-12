package com.example.DS_Assignment2_Monitoring_Microserv.dtos.builder;

import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DeviceDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;

public class DeviceBuilder {

    public DeviceBuilder(){}

    public static DeviceDTO toDeviceDTO(Device device){
        return new DeviceDTO(device.getID(), device.getDescription(), device.getAddress(),
                device.getMaxHourlyEnergyConsumption(), device.getUser_id());
    }

    public static Device toEntity(DeviceDTO deviceDTO){
        return new Device(deviceDTO.getDescription(),
                deviceDTO.getAddress(),
                deviceDTO.getMaxHourlyEnergyConsumption(),
               deviceDTO.getUser_id());
    }
}
