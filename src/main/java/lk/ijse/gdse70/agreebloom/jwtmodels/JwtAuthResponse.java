package lk.ijse.gdse70.agreebloom.jwtmodels;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtAuthResponse {

    private String token;
}
