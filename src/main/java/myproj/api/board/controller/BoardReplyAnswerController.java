package myproj.api.board.controller;

import lombok.RequiredArgsConstructor;
import myproj.api.board.model.BoardWriteAnswerInput;
import myproj.api.board.service.BoardService;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardReplyAnswerController {

    private final BoardService boardService;

    @GetMapping("/api/board/reply/answer")
    public ResponseEntity<?> getAllReplyAnswer() {
        ServiceResult result = boardService.getAllReplyAnswer();
        return ResponseResult.result(result);
    }

    @PostMapping("/api/board/reply/answer/write")
    public ResponseEntity<?> writeAnswer(@RequestBody BoardWriteAnswerInput boardWriteAnswerInput) {
        ServiceResult result = boardService.writeAnswer(boardWriteAnswerInput);
        return ResponseResult.result(result);
    }
}
