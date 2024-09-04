package com.ajouchong.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class QnaPostResponseDto {
    private Long qPostId;
    private String qpTitle;
    private String qpContent;
    private boolean isReplied = false;
    private int qpUserLikeCnt;
    private int qpHitCnt;
    private LocalDateTime qpCreateTime;
    private LocalDateTime qpUpdateTime;
    private AnswerDto answer;

    @Getter
    @Setter
    public static class AnswerDto {
        private Long answerId;
        private String content;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
    }
}
