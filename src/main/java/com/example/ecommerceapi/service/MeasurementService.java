package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.MeasurementDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 27-Jul-23
 */
public interface MeasurementService {
    boolean addMeasurement(MeasurementDTO measurementDTO);
    boolean updateMeasurement(MeasurementDTO measurementDTO, String nic);
    MeasurementDTO findMeasurement(String nic);
    List<MeasurementDTO> getAllMeasurements();
}
