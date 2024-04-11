package finance.dev.domain.ex07.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    @Builder
    public User(Long id, String username, String email, String password, LocalDate createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }
}
