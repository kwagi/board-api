package myproj.api.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import myproj.api.board.model.BoardReplyWrite;
import myproj.api.board.service.BoardService;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "게시글-댓글 API")
public final class BoardReplyController {

    private final BoardService boardService;

    @GetMapping("/api/board/replies")
    @ApiOperation(value = "해당 게시글의 모든 댓글호출")
    public ResponseEntity<?> findAllReply(@RequestParam long id) {
        ServiceResult result = boardService.findAllReply(id);
        return ResponseResult.result(result);
    }

    @PostMapping("/api/board/reply/write")
    @ApiOperation(value = "댓글 작성")
    public ResponseEntity<?> writeReply(@RequestParam long id, @RequestBody BoardReplyWrite boardReplyWrite) {
        ServiceResult result = boardService.writeReply(id, boardReplyWrite);
        return ResponseResult.result(result);
    }

    @DeleteMapping("/api/board/reply/delete")
    @ApiOperation(value = "댓글삭제")
    public ResponseEntity<?> deleteMyReply(@RequestParam long id, @RequestParam String email) {
        ServiceResult result = boardService.deleteMyReply(id, email);
        return ResponseResult.result(result);
    }
}
