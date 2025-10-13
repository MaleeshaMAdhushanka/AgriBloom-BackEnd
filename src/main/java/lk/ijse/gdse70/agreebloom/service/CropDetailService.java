package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.dto.CropDetailDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CropDetailService {
    CropDetailDTO<String> saveCropDetail(CropDetailDTO<MultipartFile> cropDetailDTO);
    CropDetailDTO<String> updateCropDetail(String logCode, CropDetailDTO<MultipartFile> cropDetailDTO);
    CropDetailDTO<String> getCropDetail(String logCode);
    List<CropDetailDTO<String>> getAllCropDetails();
    void deleteCropDetail(String logCode);
}
