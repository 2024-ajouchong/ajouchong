package com.ajouchong.service;

import com.ajouchong.entity.IntroPost;
import com.ajouchong.entity.IntroPostPageName;
import com.ajouchong.repository.IntroPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class IntroPostService {
    private IntroPostRepository introPostRepository;

    public IntroPost uploadImage(IntroPostPageName page, String imageUrl) {
        IntroPost post = new IntroPost();
        post.setPage(page);
        post.setImageUrl(imageUrl);
        post.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return introPostRepository.save(post);
    }

    public List<IntroPost> getPhotosByPage(IntroPostPageName page) {
        return introPostRepository.findByPage(page);
    }

}
