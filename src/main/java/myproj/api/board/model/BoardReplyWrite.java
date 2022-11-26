package myproj.api.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReplyWrite {
    private String replyWriter;
    private String replyContent;
}
