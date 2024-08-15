package com.ajouchong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticePostResponseDto {
    private Long nPost_id;
    private String npTitle;
    private String npContent;
    private int npUserLikeCnt;
    private int npHitCnt;
    private LocalDateTime npCreateTime;
    private LocalDateTime npUpdateTime;
    private List<String> imageUrls;
}
