package finance.dev.domain.ex09.dto;

import lombok.Data;

@Data
public class AdminUpdateRequest {
    private String target;
    private String username;
    private String password;
}
