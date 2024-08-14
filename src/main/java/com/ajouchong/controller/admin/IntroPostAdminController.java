package com.ajouchong.controller.admin;

import com.ajouchong.service.IntroPostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about/upload")
public class IntroPostController {
    private IntroPostService introPostService;
}
