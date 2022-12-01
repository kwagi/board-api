package myproj.api.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class BoardReplyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_reply_answer_id")
    private Long id;

    private String replyAnswerUuid;
    private String replyAnswerContent;
    private LocalDateTime replyAnswerDate;

    @JoinColumn(name = "board_reply_id")
    @ManyToOne
    private BoardReply boardReply;
}
