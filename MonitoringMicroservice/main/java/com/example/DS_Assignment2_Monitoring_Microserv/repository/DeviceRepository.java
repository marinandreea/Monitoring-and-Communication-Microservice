package com.example.DS_Assignment2_Monitoring_Microserv.repository;

import com.example.DS_Assignment2_Monitoring_Microserv.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device,Integer> {
}
