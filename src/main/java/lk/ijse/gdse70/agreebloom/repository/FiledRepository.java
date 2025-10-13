package lk.ijse.gdse70.agreebloom.repository;

import lk.ijse.gdse70.agreebloom.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiledRepository  extends JpaRepository<Field, String> {
}
