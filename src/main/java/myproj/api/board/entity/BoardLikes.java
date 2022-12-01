package myproj.api.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class BoardLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_likes_id")
    private Long id;

    private String email;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private Board board;
}
