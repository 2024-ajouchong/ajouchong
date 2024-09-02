package com.ajouchong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QnaPostRequestDto {
    private Long qPostId;
    private String qpTitle;
    private String qpContent;
}
