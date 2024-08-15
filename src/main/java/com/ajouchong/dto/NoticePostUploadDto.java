package com.ajouchong.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class NoticePostUploadDto {
    private String title;
    private String content;
    private List<String> imageUrls;
}
