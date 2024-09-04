package com.ajouchong.controller.admin;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.entity.QnaPost;
import com.ajouchong.service.QnaPostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qna")
public class QnaPostAdminController {
    private final QnaPostService qnaPostService;

    public QnaPostAdminController(QnaPostService qnaPostService) {
        this.qnaPostService = qnaPostService;
    }

    @PostMapping("/{postId}/answer")
    public ApiResponse<QnaPost> addAnswer(@PathVariable Long postId, @RequestParam String answerContent) {
        QnaPost updatedPost = qnaPostService.addAnswer(postId, answerContent);
        return new ApiResponse<>(1, "답변이 게시되었습니다.", updatedPost);
    }

    @PutMapping("/{postId}/answer")
    public ApiResponse<QnaPost> changeAnswer(@PathVariable Long postId, @RequestParam String updatedAnswerContent) {
        QnaPost updatedPost = qnaPostService.changeAnswer(postId, updatedAnswerContent);
        return new ApiResponse<>(1, "답변이 수정되었습니다.", updatedPost);
    }

    @DeleteMapping("/{postId}/answer")
    public ApiResponse<QnaPost> deleteAnswer(@PathVariable Long postId) {
        QnaPost updatedPost = qnaPostService.deleteAnswer(postId);
        return new ApiResponse<>(1, "답변이 삭제되었습니다.", updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        qnaPostService.deletePost(postId);
        return new ApiResponse<>(1, "게시글이 삭제되었습니다.", null);
    }
}
