package com.ajouchong.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class NoticePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nPost_id;

    private String np_title;
    private String np_content;
    private int np_userLike_cnt;
    private Long user_id;
    private Date np_createTime;
    private Date np_updateTime;
    private int np_hit_cnt;
    private boolean np_isReplied;
}
