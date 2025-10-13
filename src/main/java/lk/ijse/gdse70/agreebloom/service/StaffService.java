package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.dto.StaffDTO;

import java.util.List;

public interface StaffService {

    StaffDTO saveStaff(StaffDTO staffDTO);

    StaffDTO updateStaff(String staffId, StaffDTO staffDTO);

    void deleteStaff(String staffId);

    StaffDTO searchStaff(String staffId);

    List<StaffDTO> getAllStaff();
}
