package lk.ijse.gdse70.agreebloom.repository;

import lk.ijse.gdse70.agreebloom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Marks the interface as a Spring Data repository
//enabling exception translation and component scanning


@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
