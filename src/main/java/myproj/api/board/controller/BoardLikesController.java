package myproj.api.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "게시글-좋아요 API")
public final class BoardLikesController {

    private final BoardService boardService;

    @PatchMapping("/api/board/clickLikes")
    @ApiOperation(value = "게시글 좋아요 누르기")
    public ResponseEntity<?> clickLikes(@RequestParam String email, @RequestParam long id) {
        ServiceResult result = boardService.clickLikes(email, id);
        return ResponseResult.result(result);
    }
}
