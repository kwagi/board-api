package myproj.api.board.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import myproj.api.board.model.BoardPostInput;
import myproj.api.board.model.MyPostInput;
import myproj.api.board.service.BoardService;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.error.ResponseErrors;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "게시글 API")
public final class BoardController {

    private final BoardService boardService;

    @PutMapping("/api/board/lists")
    public ResponseEntity<?> getAllLists(int page, int size) {
        ServiceResult result = boardService.getAllLists(page, size);
        return ResponseResult.result(result);
    }

    @GetMapping("/api/board/contents")
    public ResponseEntity<?> clickTitle(long id) {
        ServiceResult result = boardService.clickTitle(id);
        return ResponseResult.result(result);
    }

    @PutMapping("api/board/contents")
    public ResponseEntity<?> showImage(long id) throws IOException {
        ServiceResult result = boardService.showImage(id);
        return ResponseResult.result(result);
    }

    @PostMapping("/api/board/post")//@RequestParam 과 @ModelAttribution 는 디폴트
    //다중 이미지는 테이블을 분리해야하므로 다음에 구현
    //error 파라미터 위치는 validation 대상 바로 다음
    public ResponseEntity<?> postBoard(@Valid BoardPostInput boardPostInput, Errors errors, @RequestParam(required = false) List<MultipartFile> files) throws IOException {
        List<ResponseErrors> responseErrors = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrors.add(ResponseErrors.of((FieldError) e)));
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }
        ServiceResult result = boardService.postBoard(boardPostInput, files);
        return ResponseResult.result(result);
    }

    @PutMapping("/api/board/my-post/{page}/{size}")
    public ResponseEntity<?> myPost(@PathVariable int page, @PathVariable int size, @RequestBody MyPostInput myPostInput) {
        ServiceResult result = boardService.myPosts(page, size, myPostInput);
        return ResponseResult.result(result);
    }

    @DeleteMapping("/api/board/delete/{id}")
    public ResponseEntity<?> deleteMyPost(@PathVariable long id, String email) {
        ServiceResult result = boardService.deleteMyPost(id, email);
        return ResponseResult.result(result);
    }

    @PostMapping("/api/board/add100")
    public ResponseEntity<?> add100() {
        boardService.add100();
        return ResponseResult.success();
    }
}
