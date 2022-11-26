package myproj.api.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_reply_id")
    private Long id;

    private String replyWriter;
    private String replyUuid;
    private String replyContent;
    private LocalDateTime replyDate;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "boardReply", orphanRemoval = true)
    @JsonIgnoreProperties({"boardReply"})
    @Builder.Default
    private List<BoardReplyAnswer> boardReplyAnswerList = new ArrayList<>();
}
