package myproj.api.board.service;

import myproj.api.board.model.BoardPostInput;
import myproj.api.board.model.BoardReplyWrite;
import myproj.api.board.model.BoardWriteAnswerInput;
import myproj.api.board.model.MyPostInput;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    /**
     * 페이지 구현
     */
    ServiceResult getAllLists(int page, int size);

    /**
     * 게시글 클릭
     */
    ServiceResult clickTitle(long id);

    /**
     * 게시글 쓰기
     */
    ServiceResult postBoard(BoardPostInput boardPostInput, List<MultipartFile> files) throws IOException;

    /**
     * 좋아요 누르기
     */
    ServiceResult clickLikes(String email, long id);

    ServiceResult myPosts(int page, int size, MyPostInput myPostInput);

    ServiceResult deleteMyPost(long id, String email);

    ServiceResult findAllReply(long id);

    ServiceResult writeReply(long id, BoardReplyWrite boardReplyWrite);

    ServiceResult deleteMyReply(long id, String email);

    ServiceResult getAllReplyAnswer();

    ServiceResult writeAnswer(BoardWriteAnswerInput boardWriteAnswerInput);

    ServiceResult showImage(long id) throws IOException;

    void add100();
}
