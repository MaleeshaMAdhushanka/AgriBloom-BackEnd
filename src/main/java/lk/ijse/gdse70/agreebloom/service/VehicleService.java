package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(String vehicleId, VehicleDTO vehicleDTO);

    void deleteVehicle(String vehicleId);

    VehicleDTO searchVehicle(String vehicleId);

    List<VehicleDTO> getAllVehicles();
}
