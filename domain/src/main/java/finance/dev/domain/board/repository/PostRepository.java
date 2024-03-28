package finance.dev.domain.board.repository;

import finance.dev.domain.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PostRepository extends JpaRepository<Post, Long> {}
