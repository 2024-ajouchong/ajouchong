package com.ajouchong.controller.user;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.NoticePostResponseDto;
import com.ajouchong.entity.NoticePost;
import com.ajouchong.service.NoticePostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notice")
public class NoticePostUserController {
    private final NoticePostService noticePostService;

    public NoticePostUserController(NoticePostService noticePostService) {
        this.noticePostService = noticePostService;
    }

    @GetMapping
    public ApiResponse<List<NoticePostResponseDto>> getAllNoticePosts() {
        List<NoticePostResponseDto> posts = noticePostService.getAllNoticePosts();
        return new ApiResponse<>(1, "모든 게시글 조회 성공", posts);
    }

    // 특정 게시물 조회
    @GetMapping("/{id}")
    public ApiResponse<NoticePostResponseDto> getNoticePostById(@PathVariable Long id) {
        NoticePostResponseDto post = noticePostService.getNoticePostById(id);
        return new ApiResponse<>(1, id + "번 게시글 조회 성공", post);
    }
}
