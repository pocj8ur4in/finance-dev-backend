package finance.dev.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "finance_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_email")
    private String user_email;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @Builder
    public User(String user_name, String user_password, String user_email) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
    }
}
