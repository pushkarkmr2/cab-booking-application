package com.wipro.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.entity.VehicleLocation;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class VehicleLocationDeserialiser implements Deserializer<VehicleLocation> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public VehicleLocation deserialize(String s, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        try {
            // Deserialize to a generic map first
            Map<String, Object> map = objectMapper.readValue(data, Map.class);
            // Map to Order DTO
            VehicleLocation vehicleLocation = new VehicleLocation();
            vehicleLocation.setId((Integer) map.get("id"));
            vehicleLocation.setVehicleNumber((String) map.get("vehicleNumber"));
            vehicleLocation.setLatitude((Double) map.get("latitude"));
            vehicleLocation.setLongitude((Double) map.get("longitude"));

            return vehicleLocation;

        } catch (Exception e) {
            throw new SerializationException("Error deserializing message", e);
        }
    }

    @Override
    public void close() {
    }
}
