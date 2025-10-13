package lk.ijse.gdse70.agreebloom.repository;

import lk.ijse.gdse70.agreebloom.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {

}
