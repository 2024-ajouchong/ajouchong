package com.ajouchong.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class NoticePostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private int imageOrder;

    @ManyToOne
    @JoinColumn(name = "nPostId")
    private NoticePost noticePost;
}
