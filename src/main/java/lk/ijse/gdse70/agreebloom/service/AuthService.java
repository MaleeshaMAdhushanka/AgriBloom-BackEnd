package lk.ijse.gdse70.agreebloom.service;

import lk.ijse.gdse70.agreebloom.jwtmodels.AuthRequest;
import lk.ijse.gdse70.agreebloom.jwtmodels.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signIn(AuthRequest signIn);
    JwtAuthResponse signUp(AuthRequest signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
