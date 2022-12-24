package myproj.api.board.controller;

import lombok.RequiredArgsConstructor;
import myproj.api.board.model.BoardReplyWrite;
import myproj.api.board.service.BoardService;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public final class BoardReplyController {

    private final BoardService boardService;

    @GetMapping("/api/board/replies")
    public ResponseEntity<?> findAllReply(long id) {
        ServiceResult result = boardService.findAllReply(id);
        return ResponseResult.result(result);
    }

    @PostMapping("/api/board/reply/write")
    public ResponseEntity<?> writeReply(long id, @RequestBody BoardReplyWrite boardReplyWrite) {
        ServiceResult result = boardService.writeReply(id, boardReplyWrite);
        return ResponseResult.result(result);
    }

    @DeleteMapping("/api/board/reply/delete")
    public ResponseEntity<?> deleteMyReply(@RequestParam long id, @RequestParam String email) {
        ServiceResult result = boardService.deleteMyReply(id, email);
        return ResponseResult.result(result);
    }
}
