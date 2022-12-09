package myproj.api.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public final class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    private String email;
    private String title;
    private String contents;
    private int hits;
    private int likes;
    private LocalDateTime postDate;
    private String fileName;
    private String filePath;
    private String fileTitle;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    @JsonIgnoreProperties({"board"})
    @Builder.Default
    private final List<BoardReply> boardReplyList = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    @JsonIgnoreProperties({"board"})
    @Builder.Default
    private final List<BoardLikes> boardLikesList = new ArrayList<>();
}
