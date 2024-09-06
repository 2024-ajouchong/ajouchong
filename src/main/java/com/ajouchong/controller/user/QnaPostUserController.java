package com.ajouchong.controller.user;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.QnaPostRequestDto;
import com.ajouchong.dto.QnaPostResponseDto;
import com.ajouchong.entity.QnaPost;
import com.ajouchong.service.QnaPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qna")
public class QnaPostController {
    private final QnaPostService qnaPostService;

    public QnaPostController(QnaPostService qnaPostService) {
        this.qnaPostService = qnaPostService;
    }

    @PostMapping
    public ApiResponse<QnaPostResponseDto> createPost(@RequestBody QnaPostRequestDto requestDto) {
        QnaPostResponseDto responseDto = qnaPostService.createPost(requestDto);
        return new ApiResponse<>(1, "게시글이 게시되었습니다.", responseDto);
    }

    @GetMapping
    public ApiResponse<List<QnaPost>> getAllPosts() {
        List<QnaPost> allPosts = qnaPostService.getAllPosts();
        return new ApiResponse<>(1, "전체 게시글 목록 조회 성공", allPosts);
    }

    @GetMapping("{postId}")
    public ApiResponse<Optional<QnaPost>> getPostById(@PathVariable Long postId) {
        Optional<QnaPost> post = qnaPostService.getPostById(postId);
        if (post.isPresent()) {
            qnaPostService.incrementHitCount(postId);
            return new ApiResponse<>(1, postId + "번 게시글 조회 성공", post);
        } else {
            return new ApiResponse<>(0, "게시글을 찾을 수 없습니다.", null);
        }
    }

    @PostMapping("/{postId}/like")
    public ApiResponse<Void> incrementLikeCount(@PathVariable Long postId) {
        qnaPostService.incrementUserLikeCount(postId);
        return new ApiResponse<>(1, postId + "번 게시글 좋아요 성공", null);
    }
}
