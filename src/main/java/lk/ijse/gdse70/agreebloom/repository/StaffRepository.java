package lk.ijse.gdse70.agreebloom.repository;

import lk.ijse.gdse70.agreebloom.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository  extends JpaRepository<Staff, String> {

}
