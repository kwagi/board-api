package myproj.api.board.repository;

import myproj.api.board.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikesRepository extends JpaRepository<BoardLikes, Long> {

    long countByEmailAndBoardId(String email,Long boardId);
}
