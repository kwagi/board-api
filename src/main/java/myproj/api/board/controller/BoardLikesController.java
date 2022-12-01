package myproj.api.board.controller;

import lombok.RequiredArgsConstructor;
import myproj.api.board.service.BoardService;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class BoardLikesController {

    private final BoardService boardService;

    @PatchMapping("/api/board/clickLikes")
    public ResponseEntity<?> clickLikes(@RequestParam String email, @RequestParam long id) {
        ServiceResult result = boardService.clickLikes(email, id);
        return ResponseResult.result(result);
    }
}
