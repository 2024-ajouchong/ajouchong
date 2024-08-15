package com.ajouchong.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NoticePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nPost_id;

}
