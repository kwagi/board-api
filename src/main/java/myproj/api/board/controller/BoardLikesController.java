package myproj.api.board.controller;

import lombok.RequiredArgsConstructor;
import myproj.api.board.service.BoardService;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class BoardLikesController {

    private final BoardService boardService;

    @PostMapping(value = "/api/board/clickLikes", produces = "application/json")
    public ResponseEntity<?> clickLikes(String email, long id) {
        ServiceResult result = boardService.clickLikes(email, id);
        return ResponseResult.result(result);
    }
}
