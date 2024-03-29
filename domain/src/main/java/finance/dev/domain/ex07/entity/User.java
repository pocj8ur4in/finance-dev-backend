package finance.dev.domain.ex07.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String provider;
    private String providerId;
    private String profileImage;

    @Builder
    public User(Long id, String username, String email, String password, String role, String provider, String providerId, String profileImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.profileImage = profileImage;
    }
}
