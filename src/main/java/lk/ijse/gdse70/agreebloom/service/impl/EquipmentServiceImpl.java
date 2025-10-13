package lk.ijse.gdse70.agreebloom.service.impl;

import lk.ijse.gdse70.agreebloom.dto.EquipmentDTO;
import lk.ijse.gdse70.agreebloom.entity.Equipment;
import lk.ijse.gdse70.agreebloom.entity.Field;
import lk.ijse.gdse70.agreebloom.entity.Staff;
import lk.ijse.gdse70.agreebloom.exception.DataPersistFailedException;
import lk.ijse.gdse70.agreebloom.exception.EquipmentNotFoundException;
import lk.ijse.gdse70.agreebloom.exception.FieldNotFoundException;
import lk.ijse.gdse70.agreebloom.exception.StaffNotFoundException;
import lk.ijse.gdse70.agreebloom.repository.EquipmentRepository;
import lk.ijse.gdse70.agreebloom.repository.FiledRepository;
import lk.ijse.gdse70.agreebloom.repository.StaffRepository;
import lk.ijse.gdse70.agreebloom.service.EquipmentService;
import lk.ijse.gdse70.agreebloom.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final StaffRepository staffRepository;

    private final FiledRepository filedRepository;

    private final Mapper mapper;


    @Override
    @Transactional
    public EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO) {
        Equipment tempEquipment = mapper.convertToEquipmentEntity(equipmentDTO);

        if (equipmentDTO.getStaff() != null) {
            Optional<Staff> tempStaff = staffRepository.findById(equipmentDTO.getStaff());
            if (tempStaff.isPresent()) {
                tempEquipment.setStaff(tempStaff.get());
            } else {
                throw new StaffNotFoundException(equipmentDTO.getStaff());
            }
        }

        if (equipmentDTO.getField() != null) {
            Optional<Field> tempField = filedRepository.findById(equipmentDTO.getField());
            if (tempField.isPresent()) {
                tempEquipment.setField(tempField.get());
            } else {
                throw new FieldNotFoundException(equipmentDTO.getField());
            }
        }

        try {
            Equipment savedEquipment = equipmentRepository.save(tempEquipment);
            return mapper.convertToEquipmentDTO(savedEquipment);
        } catch (Exception e) {
            throw new DataPersistFailedException("Failed to save the equipment");
        }
    }

    @Override
    @Transactional
    public EquipmentDTO updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<Equipment> tempEquipment = equipmentRepository.findById(equipmentId);
        if (tempEquipment.isPresent()){

            tempEquipment.get().setEquipmentType(equipmentDTO.getEquipmentType());
            tempEquipment.get().setName(equipmentDTO.getName());
            tempEquipment.get().setStatus(equipmentDTO.getStatus());

            if (equipmentDTO.getStaff() != null) {
                Optional<Staff> tempStaff = staffRepository.findById(equipmentDTO.getStaff());
                if (tempStaff.isPresent()) {
                    tempEquipment.get().setStaff(tempStaff.get());
                } else {
                    throw new StaffNotFoundException(equipmentDTO.getStaff());
                }
            } else {
                tempEquipment.get().setStaff(null);
            }

            if (equipmentDTO.getField() != null) {
                Optional<Field> tempField = filedRepository.findById(equipmentDTO.getField());
                if (tempField.isPresent()) {
                    tempEquipment.get().setField(tempField.get());
                } else {
                    throw new FieldNotFoundException(equipmentDTO.getField());
                }
            } else {
                tempEquipment.get().setField(null);
            }

            return mapper.convertToEquipmentDTO(tempEquipment.get());
        }else {
            throw new EquipmentNotFoundException(equipmentId);
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {

        if (equipmentRepository.existsById(equipmentId)) {
            equipmentRepository.deleteById(equipmentId);
        } else {
            throw new EquipmentNotFoundException(equipmentId);
        }

    }

    @Override
    public EquipmentDTO searchEquipment(String equipmentId) {

        if (equipmentRepository.existsById(equipmentId)) {
            return mapper.convertToEquipmentDTO(equipmentRepository.getReferenceById(equipmentId));
        }else {
            throw new EquipmentNotFoundException(equipmentId);
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {

        List<Equipment> equipments = equipmentRepository.findAll();
        return mapper.convertToEquipmentDTOList(equipments);
    }
}
