package com.ajouchong.entity;

import lombok.Getter;

@Getter
public enum IntroPostPageName {
    INTRODUCE("총학생회 소개"),
    MAPS("오시는 길"),
    PROMISE("공약"),
    ORGANIZATION("조직도");

    private final String description;

    IntroPostPageName(String description) {
        this.description = description;
    }

}
