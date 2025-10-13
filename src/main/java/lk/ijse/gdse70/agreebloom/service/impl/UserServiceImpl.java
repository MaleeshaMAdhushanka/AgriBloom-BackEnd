package lk.ijse.gdse70.agreebloom.service.impl;


import lk.ijse.gdse70.agreebloom.dto.UserDTO;
import lk.ijse.gdse70.agreebloom.entity.User;
import lk.ijse.gdse70.agreebloom.exception.DataPersistFailedException;
import lk.ijse.gdse70.agreebloom.exception.UserNotFoundException;
import lk.ijse.gdse70.agreebloom.repository.UserRepository;
import lk.ijse.gdse70.agreebloom.service.UserService;
import lk.ijse.gdse70.agreebloom.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//marks the class has a Spring service component . marking it discoverable for DI and enable business logic separation
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Mapper mapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO updateUser(String email, UserDTO userDTO) {
       Optional<User> tempUser = userRepository.findById(email);
       if (tempUser.isPresent()){
           if (!userDTO.getPassword().equals("null")){
               tempUser.get().setPassword(passwordEncoder.encode(userDTO.getPassword()));
           }
           tempUser.get().setRole(userDTO.getRole());
           return mapper.convertToUserDTO(userRepository.save(tempUser.get()));
       } else {
           throw new UserNotFoundException(email);
       }
    }

    @Override
    public void deleteUser(String email) {
      Optional<User> tempUser =  userRepository.findById(email);
       if (tempUser.isPresent()){
           userRepository.delete(tempUser.get());
       } else {
              throw new UserNotFoundException(email);
       }
    }

    @Override
    public UserDTO searchUser(String email) {
        if (userRepository.existsById(email)){
            return mapper.convertToUserDTO(userRepository.getReferenceById(email));
        } else {
            throw new UserNotFoundException(email);
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userRepository.findById(email)
                        .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return mapper.convertToUserDTOList(users);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
      Optional<User> tempUser = userRepository.findById(userDTO.getEmail());
      if (tempUser.isPresent()){
          throw new RuntimeException(userDTO.getEmail());
      }
      try {
         User user  = mapper.convertToUserEntity(userDTO);
         user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
         return mapper.convertToUserDTO(userRepository.save(user));
      } catch (Exception e) {
          throw new DataPersistFailedException("Failed to save user");

      }
    }
}
