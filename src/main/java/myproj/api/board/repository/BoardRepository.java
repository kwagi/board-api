package myproj.api.board.repository;

import myproj.api.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByOrderByPostDateDesc(Pageable pageable);

    Page<Board> findAllByEmailOrderByPostDateDesc(Pageable pageable, String email);

}
