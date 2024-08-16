package com.ajouchong.entity;

import com.ajouchong.entity.enumClass.RuleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RulePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rPostId;

    private String rpTitle;
    private String rpContent;
    private String attachmentUrl;

    @Enumerated(EnumType.STRING)
    private RuleType type; // 회칙 or 세칙 구분

    private LocalDateTime rpCreateTime;
    private LocalDateTime rpUpdateTime;

    @PrePersist
    protected void onCreate() {
        this.rpCreateTime = LocalDateTime.now();
        this.rpUpdateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.rpUpdateTime = LocalDateTime.now();
    }
}

