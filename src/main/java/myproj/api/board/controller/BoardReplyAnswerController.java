package myproj.api.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "게시글-답글 API")
public final class BoardReplyAnswerController {

    private final BoardService boardService;

    @GetMapping("/api/board/reply/answer")
    @ApiOperation(value = "모든 답글호출")
    public ResponseEntity<?> getAllReplyAnswer() {
        ServiceResult result = boardService.getAllReplyAnswer();
        return ResponseResult.result(result);
    }

    @PostMapping("/api/board/reply/answer/write")
    @ApiOperation(value = "답글작성")
    public ResponseEntity<?> writeAnswer(@RequestBody BoardWriteAnswerInput boardWriteAnswerInput) {
        ServiceResult result = boardService.writeAnswer(boardWriteAnswerInput);
        return ResponseResult.result(result);
    }
}
