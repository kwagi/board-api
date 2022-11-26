package myproj.api.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardWriteAnswerInput {

    private String replyAnswerContent;
    private long id;
}
