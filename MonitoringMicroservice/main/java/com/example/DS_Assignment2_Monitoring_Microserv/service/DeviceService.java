package com.example.DS_Assignment2_Monitoring_Microserv.service;

import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DeviceDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.builder.DeviceBuilder;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.example.DS_Assignment2_Monitoring_Microserv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    public Optional<Device> addDevice(DeviceDTO deviceDTO) {

        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        if (device == null) {
            return Optional.empty();
        }

        return Optional.of(device);
    }

    public Optional<Device> deleteDevice(int id){
        Optional<Device> device = deviceRepository.findById(id);
        if(device.isPresent()){
            deviceRepository.deleteById(id);
            return Optional.of(device.get());
        }
        return Optional.empty();
    }
}
