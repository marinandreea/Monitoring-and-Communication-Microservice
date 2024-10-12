package com.example.DS_Assignment2_Monitoring_Microserv.controller;

import com.example.DS_Assignment2_Monitoring_Microserv.controller.errorHandler.Error;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DeviceDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.example.DS_Assignment2_Monitoring_Microserv.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.POST,value = "/addDevice")
    public ResponseEntity<?> addDevice(@RequestBody DeviceDTO deviceDTO){

        Optional<Device> device = deviceService.addDevice(deviceDTO);
        if(device.isEmpty()){
           Error error = new Error("Device could not be added!");
           return new ResponseEntity<>(error,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(device,HttpStatus.OK);
    }




}
