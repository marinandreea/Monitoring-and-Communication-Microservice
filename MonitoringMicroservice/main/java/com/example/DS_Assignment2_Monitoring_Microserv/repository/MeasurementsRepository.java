package com.example.DS_Assignment2_Monitoring_Microserv.repository;

import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import com.example.DS_Assignment2_Monitoring_Microserv.model.Measurements;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementsRepository extends CrudRepository<Measurements,Integer> {

    //List<Measurements> findAllByDevice_id(Device device_id);
}
