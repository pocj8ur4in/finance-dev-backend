package finance.dev.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "finance_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long post_id;

    @Column(name = "post_title")
    private String post_title;

    @Column(name = "post_content")
    @Lob
    private String post_content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String post_title, String post_content) {
        this.post_title = post_title;
        this.post_content = post_content;
    }
}
