package com.example.DS_Assignment2_Monitoring_Microserv.controller;

import com.example.DS_Assignment2_Monitoring_Microserv.controller.errorHandler.Error;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.DeviceDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.dtos.MeasurementsDTO;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Measurements;
import com.example.DS_Assignment2_Monitoring_Microserv.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MeasurementsController {

    @Autowired
    private MonitoringService monitoringService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET,value = "/chart/{device_id}")
    public ResponseEntity<?> getChart(@PathVariable int device_id){

        Optional<List<Measurements>> list = monitoringService.getListOfMeasurementsForChart(device_id);
        if(list.isEmpty()){
            Error error = new Error("List is empty!");
            return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
        }
        for(Measurements m: list.get()){
            System.out.println(m.toString()+"\n");
        }
        return new ResponseEntity<>(list.get(),HttpStatus.OK);
    }
}
