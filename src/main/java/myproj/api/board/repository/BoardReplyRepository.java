package myproj.api.board.repository;

import myproj.api.board.entity.Board;
import myproj.api.board.entity.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {
    List<BoardReply> findAllByBoard(Board board);
}
