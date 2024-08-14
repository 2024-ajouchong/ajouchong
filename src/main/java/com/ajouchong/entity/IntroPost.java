package com.ajouchong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class NoticePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String page;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private String createdAt;
}
