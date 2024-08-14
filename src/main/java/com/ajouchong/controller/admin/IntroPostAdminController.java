package com.ajouchong.controller.admin;

import com.ajouchong.common.ApiResponse;
import com.ajouchong.entity.IntroPost;
import com.ajouchong.entity.IntroPostPageName;
import com.ajouchong.service.IntroPostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/about/upload")
public class IntroPostAdminController {
    private final IntroPostService introPostService;

    public IntroPostAdminController(IntroPostService introPostService) {
        this.introPostService = introPostService;
    }

    @PostMapping("/{page}")
    public ApiResponse<IntroPost> uploadPhoto(@PathVariable("page") String pageName, @RequestParam String imageUrl) {
        try {
            IntroPostPageName page = IntroPostPageName.valueOf(pageName.toUpperCase());
            IntroPost savedPost = introPostService.uploadImage(page, imageUrl);
            return new ApiResponse<>(1, "사진 업로드를 완료했습니다.", savedPost);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(0, "유효하지 않은 페이지 이름입니다: " + pageName, null);
        }
    }
}
