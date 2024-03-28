package finance.dev.domain.board.repository;

import finance.dev.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface BoardRepository extends JpaRepository<Board, Long> {}
