package com.example.DS_Assignment2_Monitoring_Microserv.dtos;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalTime;

@JsonComponent
public class MeasurementsDTODeserializer extends StdDeserializer<MeasurementsDTO> {

    public MeasurementsDTODeserializer() {
        this(null);
    }

    public MeasurementsDTODeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public MeasurementsDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        // Extracting values from JSON
        LocalTime timestamp = LocalTime.parse(node.get("timestamp").asText());
        int deviceId = node.get("device_id").asInt();
        double measurementValue = node.get("measurement_value").asDouble();

        // Creating DevDTO object
        DevDTO devDTO = new DevDTO(deviceId);
        // You may need to set other properties for DevDTO based on your actual structure

        // Creating MeasurementsDTO object
        MeasurementsDTO measurementsDTO = new MeasurementsDTO(timestamp, devDTO, measurementValue);

        return measurementsDTO;
    }
}
