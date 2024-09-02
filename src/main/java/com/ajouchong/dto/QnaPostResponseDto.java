package com.ajouchong.dto;

import com.ajouchong.entity.QnaPost;

import java.time.LocalDateTime;

public class QnaPostRequestDto {
    private Long qPostId;
    private String qpTitle;
    private String qpContent;
    private boolean isReplied = false;
    private int qpUserLikeCnt;
    private int qpHitCnt;
    private LocalDateTime qpCreateTime;
    private LocalDateTime qpUpdateTime;

    public QnaPostRequestDto(QnaPost qnaPost) {
        this.qPostId = qnaPost.getQPostId();
        this.qpTitle = qnaPost.getQpTitle();
        this.qpContent = qnaPost.getQpContent();
        this.isReplied = qnaPost.isReplied();
        this.qpUserLikeCnt = qnaPost.getQpUserLikeCnt();
        this.qpHitCnt = qnaPost.getQpHitCnt();
        this.qpCreateTime = LocalDateTime.now();
        this.qpUpdateTime = LocalDateTime.now();
    }
}
