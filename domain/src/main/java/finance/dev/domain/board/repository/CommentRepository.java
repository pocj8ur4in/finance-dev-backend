package finance.dev.domain.board.repository;

import finance.dev.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {}
