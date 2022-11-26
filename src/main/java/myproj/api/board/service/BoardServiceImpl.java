package myproj.api.board.service;

import lombok.RequiredArgsConstructor;
import myproj.api.board.entity.Board;
import myproj.api.board.entity.BoardLikes;
import myproj.api.board.entity.BoardReply;
import myproj.api.board.entity.BoardReplyAnswer;
import myproj.api.board.model.BoardPostInput;
import myproj.api.board.model.BoardReplyWrite;
import myproj.api.board.model.BoardWriteAnswerInput;
import myproj.api.board.model.MyPostInput;
import myproj.api.board.repository.BoardLikesRepository;
import myproj.api.board.repository.BoardReplyAnswerRepository;
import myproj.api.board.repository.BoardReplyRepository;
import myproj.api.board.repository.BoardRepository;
import myproj.api.common.response.service.ServiceResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final BoardReplyRepository boardReplyRepository;
    private final BoardReplyAnswerRepository boardReplyAnswerRepository;

    @Override
    public ServiceResult getAllLists(int page, int size) {
        Page<Board> boards = boardRepository.findAllByOrderByPostDateDesc(PageRequest.of(page, size));
        return ServiceResult.success(boards);
    }

    @Override
    public ServiceResult clickTitle(long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 없습니다.");
        }
        Board board = optionalBoard.get();
        board.setHits(board.getHits() + 1);
        boardRepository.save(board);
        return ServiceResult.success(board);
    }

    @Override
    public ServiceResult postBoard(BoardPostInput boardPostInput, List<MultipartFile> files) throws IOException {
        if (!CollectionUtils.isEmpty(files)) {
            String filePath = System.getProperty("user.home") + "/files/";
            String fileName = UUID.randomUUID() + "_" + files.get(0).getOriginalFilename();
            String fileTitle = files.get(0).getOriginalFilename();
            files.get(0).transferTo(new File(filePath + fileName));

            Board board = Board.builder()
                    .email(boardPostInput.getEmail())
                    .title(boardPostInput.getTitle())
                    .contents(boardPostInput.getContents())
                    .hits(0)
                    .likes(0)
                    .postDate(LocalDateTime.now())
                    .fileName(fileName)
                    .filePath(filePath + fileName)
                    .fileTitle(fileTitle)
                    .build();

            boardRepository.save(board);
            return ServiceResult.success();
        }
        Board board = Board.builder()
                .email(boardPostInput.getEmail())
                .title(boardPostInput.getTitle())
                .contents(boardPostInput.getContents())
                .hits(0)
                .likes(0)
                .postDate(LocalDateTime.now())
                .build();

        boardRepository.save(board);
        return ServiceResult.success();
    }

    @Override
    public ServiceResult clickLikes(String email, long id) {
        if (Objects.equals(email, "undefined") || Objects.equals(email, "")) {
            return ServiceResult.fail("먼저 로그인하세요.");
        }

        Optional<Board> optionalBoard = boardRepository.findById(id);
        long count = boardLikesRepository.countByEmailAndBoardId(email, id);

        if (count > 0) {
            return ServiceResult.fail("해당계정으로 이미 추천한 게시글입니다.");
        }
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지않습니다.");
        }

        Board board = optionalBoard.get();
        BoardLikes boardLikes = BoardLikes.builder()
                .email(email)
                .board(board)
                .build();

        board.setLikes(board.getLikes() + 1);
        boardRepository.save(board);
        boardLikesRepository.save(boardLikes);
        return ServiceResult.success();
    }

    @Override
    public ServiceResult myPosts(int page, int size, MyPostInput myPostInput) {
        Page<Board> boards = boardRepository.findAllByEmailOrderByPostDateDesc(PageRequest.of(page, size), myPostInput.getEmail());
        return ServiceResult.success(boards);
    }

    @Override
    public ServiceResult deleteMyPost(long id, String email) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지않습니다.");
        }
        Board board = optionalBoard.get();
        if (!Objects.equals(board.getEmail(), email)) {
            return ServiceResult.fail("작성자가 일치하지않습니다.");
        }
        boardRepository.delete(board);
        return ServiceResult.success();
    }

    @Override
    public ServiceResult findAllReply(long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지않습니다.");
        }
        Board board = optionalBoard.get();
        List<BoardReply> boardReplyList = boardReplyRepository.findAllByBoard(board);
        return ServiceResult.success(boardReplyList);
    }

    @Override
    public ServiceResult writeReply(long id, BoardReplyWrite boardReplyWrite) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 없습니다.");
        }
        BoardReply boardReply = BoardReply.builder()
                .board(optionalBoard.get())
                .replyWriter(boardReplyWrite.getReplyWriter())
                .replyUuid(UUID.randomUUID().toString().substring(0, 8))
                .replyContent(boardReplyWrite.getReplyContent())
                .replyDate(LocalDateTime.now())
                .build();
        boardReplyRepository.save(boardReply);
        return ServiceResult.success(boardReply);
    }

    @Override
    public ServiceResult deleteMyReply(long id, String email) {
        if (Objects.equals(email, "undefined") || Objects.equals(email, "")) {
            return ServiceResult.fail("먼저 로그인하세요.");
        }
        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(id);
        if (optionalBoardReply.isEmpty()) {
            return ServiceResult.fail("댓글이 존재하지않습니다.");
        }
        BoardReply boardReply = optionalBoardReply.get();
        if (Objects.equals(email, boardReply.getReplyWriter())) {
            return ServiceResult.fail("작성자가 일치하지않습니다.");
        }
        boardReplyRepository.delete(boardReply);
        return ServiceResult.success();
    }

    @Override
    public ServiceResult getAllReplyAnswer() {
        List<BoardReplyAnswer> boardReplyAnswerList = boardReplyAnswerRepository.findAll();
        return ServiceResult.success(boardReplyAnswerList);
    }

    @Override
    public ServiceResult writeAnswer(BoardWriteAnswerInput boardWriteAnswerInput) {
        Optional<BoardReply> optionalBoardReply = boardReplyRepository.findById(boardWriteAnswerInput.getId());
        if (optionalBoardReply.isEmpty()) {
            return ServiceResult.fail("댓글이 존재하지않습니다.");
        }
        BoardReply boardReply = optionalBoardReply.get();
        boardReplyAnswerRepository.save(BoardReplyAnswer.builder()
                .replyAnswerContent(boardWriteAnswerInput.getReplyAnswerContent())
                .replyAnswerUuid(UUID.randomUUID().toString().substring(0, 8))
                .boardReply(boardReply)
                .replyAnswerDate(LocalDateTime.now())
                .build());

        return ServiceResult.success();
    }

    @Override
    public ServiceResult showImage(long id) throws IOException {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지않습니다.");
        }
        Board board = optionalBoard.get();
        InputStream imageStream = new FileInputStream(board.getFilePath());
        byte[] imageByteArray = StreamUtils.copyToByteArray(imageStream);//image => byteArray
        imageStream.close();
        String image = Base64.getEncoder().encodeToString(imageByteArray);//byteArray => base64
        return ServiceResult.success(image);
    }

    @Override
    public void add100() {
        for (int i = 1; i < 101; i++) {
            Board board = Board.builder()
                    .contents(i + "번째 게시글입니다.")
                    .email("test@naver.com")
                    .hits(0)
                    .likes(0)
                    .postDate(LocalDateTime.now())
                    .title(i + "번째 제목")
                    .build();
            boardRepository.save(board);
        }
    }
}
