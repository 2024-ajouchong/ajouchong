package com.ajouchong.repository;

import com.ajouchong.entity.IntroPost;
import com.ajouchong.entity.IntroPostPageName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntroPostRepository extends JpaRepository<IntroPost, Long> {
    List<IntroPost> findByPage(IntroPostPageName page);
}
