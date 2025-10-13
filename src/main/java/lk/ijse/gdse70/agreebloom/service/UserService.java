package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDTO updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);
    UserDTO searchUser(String email);
    UserDetailsService userDetailsService();
    List<UserDTO> getAllUsers();
    UserDTO saveUser(UserDTO userDTO);

}
