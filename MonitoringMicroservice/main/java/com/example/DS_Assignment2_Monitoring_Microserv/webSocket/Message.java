package com.example.DS_Assignment2_Monitoring_Microserv.webSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message implements Serializable {


    private String receiverName;
    private String message;
    private String date;

    public Message(String message) {
        this.message = message;
    }

//    @Override
//    public String toString() {
//        return "{\"message\":\"" + this.message + "\"}";
//    }
}
