package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUsername(String token);
    String generateToken(User user);
    boolean isTokenValid(String token, UserDetails userDetails);
}
