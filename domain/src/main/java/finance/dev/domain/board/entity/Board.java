package finance.dev.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "finance_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long board_id;

    @Column(name = "board_title")
    private String board_title;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;

    @Builder
    public Board(String board_title) {
        this.board_title = board_title;
    }
}
