package com.example.DS_Assignment2_Monitoring_Microserv.dtos.builder;

import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DevDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;

public class DevDTOBuilder {

    public DevDTOBuilder(){}

    public static DevDTO toDevDTO(Device device){
        return new DevDTO(device.getID());
    }

    public static Device toEntity(DevDTO devDTO){
        return new Device(devDTO.getID());
    }
}
