package myproj.api.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BoardWriteAnswerInput {

    private String replyAnswerContent;
    private long id;
}
