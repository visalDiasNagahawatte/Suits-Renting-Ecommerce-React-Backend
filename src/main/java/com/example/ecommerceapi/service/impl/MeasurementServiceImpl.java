package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.MeasurementDTO;
import com.example.ecommerceapi.entity.Measurement;
import com.example.ecommerceapi.exception.EntryDuplicateException;
import com.example.ecommerceapi.exception.EntryNotFoundException;
import com.example.ecommerceapi.exception.NotFoundException;
import com.example.ecommerceapi.repo.CustomerRepo;
import com.example.ecommerceapi.repo.MeasurementRepo;
import com.example.ecommerceapi.service.MeasurementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 27-Jul-23
 */
@Service
@Transactional
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public boolean addMeasurement(MeasurementDTO measurementDTO) {
        if (!Objects.isNull(measurementDTO)) {
            if (repo.findMeasurementCountByCustomerId(customerRepo.getLastCustomerId()) < 1) {
                Measurement measurement = mapper.map(measurementDTO, Measurement.class);
                measurement.setCustomerId(customerRepo.getLastCustomerId());
                repo.save(measurement);
                return true;
            } else {
                throw new EntryDuplicateException("Already exists...");
            }
        } else {
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public boolean updateMeasurement(MeasurementDTO measurementDTO, String nic) {
        if (!Objects.isNull(measurementDTO) && !nic.isEmpty()) {
            Optional<Measurement> optionalMeasurement = repo.findByCustomerNic(nic);
            if (optionalMeasurement.isPresent()) {
                Measurement measurement = optionalMeasurement.get();
                measurement.setHipSize(measurementDTO.getHipSize());
                measurement.setJacketSize(measurementDTO.getJacketSize());
                measurement.setNeckSize(measurementDTO.getNeckSize());
                measurement.setPantLength(measurementDTO.getPantLength());
                measurement.setSleeveLength(measurementDTO.getSleeveLength());
                measurement.setWaistSize(measurementDTO.getWaistSize());
                measurement.setExtra(measurementDTO.getExtra());
                repo.save(measurement);
                return true;
            } else {
                throw new NotFoundException("Not found...");
            }
        } else {
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public MeasurementDTO findMeasurement(String nic) {
        if (!nic.isEmpty()) {
            Optional<Measurement> optionalMeasurement = repo.findByCustomerNic(nic);
            if (optionalMeasurement.isPresent()) {
                Measurement measurement = optionalMeasurement.get();
                return mapper.map(measurement, MeasurementDTO.class);
            } else {
                throw new NotFoundException("Not found...");
            }
        } else {
            throw new EntryNotFoundException("Invalid inputs...");
        }
    }

    @Override
    public List<MeasurementDTO> getAllMeasurements() {
        List<Measurement> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<MeasurementDTO>>() {
        }.getType());
    }
}
