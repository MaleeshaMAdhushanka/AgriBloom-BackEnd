package lk.ijse.gdse70.agreebloom.controller;


import jakarta.validation.Valid;
import lk.ijse.gdse70.agreebloom.dto.UserDTO;
import lk.ijse.gdse70.agreebloom.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//marks the class as a RESTController , combining @Controller and @ResponseBody  to handle http request and return Json/xml response directly

//Specifies the base URL for all endpoints in the controller organizing routes under common prefix.

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    //Secures methods by specifying access control expression
    //ensuring only user with roles/permissions can invoke the endpoint
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {

        log.info("Received request to save user: {}", userDTO.getEmail());

       UserDTO savedUser = userService.saveUser(userDTO);
       log.info("User saved successfully: {}", savedUser.getEmail());

       return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }


    @PreAuthorize("hasAnyRole('MANAGER','ADMINISTRATIVE')")
    @PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserDTO userDTO) {

        log.info("Received request to update user: {}", userId);

        UserDTO updatedUser = userService.updateUser(userId, userDTO);

        log.info("User updated successfully: {}", userId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {

        log.info("Received request to delete user: {}", userId);

        userService.deleteUser(userId);

        log.info("User deleted successfully: {}", userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId) {
        log.info("Received request to get user: {}", userId);

        UserDTO userDTO = userService.searchUser(userId);

        log.info("User found: {}", userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        log.info("Received request to get all users");

        List<UserDTO> allUsers = userService.getAllUsers();

        log.info("Retrieved {} users", allUsers.size());

        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }






}
