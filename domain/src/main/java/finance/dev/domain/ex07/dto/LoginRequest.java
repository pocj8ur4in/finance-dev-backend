package finance.dev.domain.ex07.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
