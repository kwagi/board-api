package myproj.api.board.repository;

import myproj.api.board.entity.BoardReplyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardReplyAnswerRepository extends JpaRepository<BoardReplyAnswer, Long> {
}
