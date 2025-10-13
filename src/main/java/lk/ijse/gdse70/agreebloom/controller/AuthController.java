package lk.ijse.gdse70.agreebloom.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse70.agreebloom.jwtmodels.AuthRequest;
import lk.ijse.gdse70.agreebloom.jwtmodels.JwtAuthResponse;
import lk.ijse.gdse70.agreebloom.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication controller handling sign-in, sign-up and token refresh operations.
 * The controller maps to multiple URL patterns to support different frontend configurations.
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class AuthController {

    private final AuthService authService;

    // Original URL pattern
    @PostMapping(value = "api/auth/v1/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> signUp(@Valid @RequestBody AuthRequest signUp) {

        log.info("Received sign-up request for email: {}", signUp.getEmail());

        JwtAuthResponse response = authService.signUp(signUp);

        log.info("User successfully signed up with email: {}", signUp.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Alternative URL pattern


    // Original URL pattern
    @PostMapping(value = "api/auth/v1/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> signIn(@Valid @RequestBody AuthRequest signIn) {

        log.info("Received sign-in request for email: {}", signIn.getEmail());

        JwtAuthResponse response = authService.signIn(signIn);

        log.info("User successfully signed in with email: {}", signIn.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    // Original URL pattern
    @PostMapping(value = "api/auth/v1/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {

        log.info("Received token refresh request");

        JwtAuthResponse response = authService.refreshToken(refreshToken);

        log.info("Token successfully refreshed");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
