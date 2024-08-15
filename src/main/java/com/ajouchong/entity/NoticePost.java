package com.ajouchong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter @Setter
public class NoticePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nPostId;

    private String npTitle;
    private String npContent;
    private int npUserLikeCnt = 0;
    private int npHitCnt = 0;
    private boolean npIsReplied = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    private LocalDateTime npCreateTime;
    private LocalDateTime npUpdateTime;

    @PrePersist
    protected void onCreate() {
        this.npCreateTime = LocalDateTime.now();
        this.npUpdateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.npUpdateTime = LocalDateTime.now();
    }

}
