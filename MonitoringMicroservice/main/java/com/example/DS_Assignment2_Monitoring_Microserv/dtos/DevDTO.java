package com.example.DS_Assignment2_Monitoring_Microserv.dtos;

public class DevDTO {

    private int ID;

    public DevDTO(int ID) {
        this.ID = ID;
    }
    public DevDTO(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
