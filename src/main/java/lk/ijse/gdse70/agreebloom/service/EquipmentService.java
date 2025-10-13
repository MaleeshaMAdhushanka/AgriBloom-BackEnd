package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO);

    EquipmentDTO updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);

    void deleteEquipment(String equipmentId);

    EquipmentDTO searchEquipment(String equipmentId);

    List<EquipmentDTO> getAllEquipment();
}
