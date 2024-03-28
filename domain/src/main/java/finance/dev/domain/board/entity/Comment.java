package finance.dev.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "finance_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long comment_id;

    @Column(name = "comment_content")
    @Lob
    private String comment_content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comment(String comment_content) {
        this.comment_content = comment_content;
    }
}
