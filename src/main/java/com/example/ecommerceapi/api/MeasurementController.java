package com.example.ecommerceapi.api;

import com.example.ecommerceapi.dto.MeasurementDTO;
import com.example.ecommerceapi.service.MeasurementService;
import com.example.ecommerceapi.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 28-Jul-23
 */
@RestController
@RequestMapping("/api/v1/measurement")
@CrossOrigin
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @PutMapping(params = {"nic"})
    public ResponseEntity<StandardResponse> updateMeasurement(@RequestBody MeasurementDTO measurementDTO, @RequestParam String nic) {
        boolean b = measurementService.updateMeasurement(measurementDTO, nic);
        return new ResponseEntity<>(new StandardResponse(204, "Success...", b), HttpStatus.OK);
    }

    @GetMapping(value = "/find", params = {"nic"})
    public ResponseEntity<StandardResponse> findMeasurement(@RequestParam String nic) {
        MeasurementDTO measurementDTO = measurementService.findMeasurement(nic);
        return new ResponseEntity<>(new StandardResponse(200, "Success...", measurementDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllMeasurement() {
        List<MeasurementDTO> allMeasurements = measurementService.getAllMeasurements();
        return new ResponseEntity<>(new StandardResponse(200, "Success...", allMeasurements), HttpStatus.OK);
    }
}
