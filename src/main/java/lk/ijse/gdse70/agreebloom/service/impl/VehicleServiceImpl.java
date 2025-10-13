package lk.ijse.gdse70.agreebloom.service.impl;

import lk.ijse.gdse70.agreebloom.dto.VehicleDTO;
import lk.ijse.gdse70.agreebloom.entity.Staff;
import lk.ijse.gdse70.agreebloom.entity.Vehicle;
import lk.ijse.gdse70.agreebloom.exception.DataPersistFailedException;
import lk.ijse.gdse70.agreebloom.exception.StaffNotFoundException;
import lk.ijse.gdse70.agreebloom.exception.VehicleNotFoundException;
import lk.ijse.gdse70.agreebloom.repository.StaffRepository;
import lk.ijse.gdse70.agreebloom.repository.VehicleRepository;
import lk.ijse.gdse70.agreebloom.service.VehicleService;
import lk.ijse.gdse70.agreebloom.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final StaffRepository staffRepository;

    private final Mapper mapper;

    @Override
    @Transactional
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        try {
            Vehicle tempVehicle = mapper.convertToVehicleEntity(vehicleDTO);
            if (vehicleDTO.getStaff() != null) {
                Optional<Staff> tempStaff = staffRepository.findById(vehicleDTO.getStaff());
                if (tempStaff.isPresent()) {
                    tempVehicle.setStaff(tempStaff.get());
                } else {
                    throw new StaffNotFoundException(vehicleDTO.getStaff());
                }
            }
            return mapper.convertToVehicleDTO(vehicleRepository.save(tempVehicle));
        } catch (Exception e) {
            throw new DataPersistFailedException("Failed to save the vehicle");
        }
    }

    @Override
    @Transactional
    public VehicleDTO updateVehicle(String vehicleId, VehicleDTO vehicleDTO) {
        Optional<Vehicle> tempVehicle = vehicleRepository.findById(vehicleId);

        if (tempVehicle.isPresent()) {
            Vehicle vehicle = tempVehicle.get();
            vehicle.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            vehicle.setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicle.setFuelType(vehicleDTO.getFuelType());
            vehicle.setStatus(vehicleDTO.getStatus());
            vehicle.setRemarks(vehicleDTO.getRemarks());

            if (vehicleDTO.getStaff() != null) {
                Optional<Staff> tempStaff = staffRepository.findById(vehicleDTO.getStaff());
                if (tempStaff.isPresent()) {
                    vehicle.setStaff(tempStaff.get());
                } else {
                    throw new StaffNotFoundException(vehicleDTO.getStaff());
                }
            } else {
                vehicle.setStaff(null);
            }
            return mapper.convertToVehicleDTO(tempVehicle.get());
        } else {
            throw new VehicleNotFoundException(vehicleId);
        }
    }

    @Override
    public void deleteVehicle(String vehicleId) {

        Optional<Vehicle> tempVehicle = vehicleRepository.findById(vehicleId);
        if (tempVehicle.isPresent()) {
            vehicleRepository.deleteById(vehicleId);
        } else {
            throw new VehicleNotFoundException(vehicleId);
        }

    }

    @Override
    public VehicleDTO searchVehicle(String vehicleId) {
        if (vehicleRepository.existsById(vehicleId)) {
            return mapper.convertToVehicleDTO(vehicleRepository.getReferenceById(vehicleId));
        } else {
            throw new VehicleNotFoundException(vehicleId);
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return mapper.convertToVehicleDTOList(vehicles);
    }
}
