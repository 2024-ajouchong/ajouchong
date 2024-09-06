package com.ajouchong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class QnaPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qPostId;

    private String qpTitle;
    private String qpContent;

    private boolean isReplied = false;

    private int qpUserLikeCnt = 0;
    private int qpHitCnt = 0;

    private LocalDateTime qpCreateTime;
    private LocalDateTime qpUpdateTime;

    @OneToOne(mappedBy = "qnaPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer answer;

    @PrePersist
    protected void onCreate() {
        this.qpCreateTime = LocalDateTime.now();
        this.qpUpdateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.qpUpdateTime = LocalDateTime.now();
    }

    public void incrementHitCount() {
        this.qpHitCnt++;
    }

    public void incrementUserLikeCount() {
        this.qpUserLikeCnt++;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        this.isReplied = true;
    }
}
