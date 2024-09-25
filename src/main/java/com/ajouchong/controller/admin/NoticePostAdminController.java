package com.ajouchong.controller.admin;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.dto.response.NoticePostResponseDto;
import com.ajouchong.dto.request.NoticePostUploadRequestDto;
import com.ajouchong.service.NoticePostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/notice")
public class NoticePostAdminController {
    private final NoticePostService noticePostService;

    public NoticePostAdminController(NoticePostService noticePostService) {
        this.noticePostService = noticePostService;
    }

    @PostMapping
    public ApiResponse<NoticePostResponseDto> uploadNoticePost(
            @RequestBody NoticePostUploadRequestDto dto,
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);
        NoticePostResponseDto savedNoticePost = noticePostService.saveNoticePost(dto, token);

        return new ApiResponse<>(1, "게시글 업로드 성공", savedNoticePost);
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteNoticePost(@PathVariable Long id) {
        noticePostService.deleteNoticePost(id);
        return new ApiResponse<>(1, id + "번 게시글 삭제 성공", null);
    }
}
