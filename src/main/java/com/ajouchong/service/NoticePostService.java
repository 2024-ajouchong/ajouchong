package com.ajouchong.service;

import com.ajouchong.dto.response.NoticePostResponseDto;
import com.ajouchong.dto.request.NoticePostUploadRequestDto;
import com.ajouchong.entity.Member;
import com.ajouchong.entity.NoticePost;
import com.ajouchong.entity.NoticePostImage;
import com.ajouchong.jwt.JwtTokenProvider;
import com.ajouchong.repository.MemberRepository;
import com.ajouchong.repository.NoticePostImageRepository;
import com.ajouchong.repository.NoticePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticePostService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final NoticePostRepository noticePostRepository;
    private final NoticePostImageRepository noticePostImageRepository;

    @Transactional
    public NoticePostResponseDto saveNoticePost(NoticePostUploadRequestDto dto, String token) {

        String email = jwtTokenProvider.getUserEmailFromToken(token);

        Member author = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        NoticePost noticePost = new NoticePost();
        noticePost.setAuthor(author);
        noticePost.setNpTitle(dto.getTitle());
        noticePost.setNpContent(dto.getContent());
        noticePost.setNpCreateTime(LocalDateTime.now());
        noticePost.setNpUpdateTime(LocalDateTime.now());

        NoticePost savedNoticePost = noticePostRepository.save(noticePost);


        List<NoticePostImage> images = new ArrayList<>();
        for (int i = 0; i < dto.getImageUrls().size(); i++) {
            String imageUrl = dto.getImageUrls().get(i);
            NoticePostImage image = new NoticePostImage();
            image.setImageUrl(imageUrl);
            image.setImageOrder(i);
            image.setNoticePost(savedNoticePost);
            images.add(image);
        }

        noticePostImageRepository.saveAll(images);

        List<String> imageUrls = images.stream().map(NoticePostImage::getImageUrl).collect(Collectors.toList());
        return new NoticePostResponseDto(
                savedNoticePost.getNPostId(),
                savedNoticePost.getNpTitle(),
                savedNoticePost.getNpContent(),
                savedNoticePost.getNpUserLikeCnt(),
                savedNoticePost.getNpHitCnt(),
                savedNoticePost.getNpCreateTime(),
                savedNoticePost.getNpUpdateTime(),
                imageUrls
        );
    }

    @Transactional(readOnly = true)
    public List<NoticePostResponseDto> getAllNoticePosts() {
        List<NoticePost> posts = noticePostRepository.findAll();

        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoticePostResponseDto getNoticePostById(Long id) {
        NoticePost post = noticePostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "번 게시글을 찾을 수 없습니다."));

        return convertToDto(post);
    }

    @Transactional
    public void deleteNoticePost(Long id) {
        noticePostRepository.deleteById(id);
    }

    @Transactional
    public void increaseLikeCount(Long id) {
        NoticePost noticePost = noticePostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "번 게시글을 찾을 수 없습니다."));
        noticePost.setNpUserLikeCnt(noticePost.getNpUserLikeCnt() + 1);
        noticePostRepository.save(noticePost);
    }

    @Transactional
    public void increaseHitCount(Long id) {
        NoticePost noticePost = noticePostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "번 게시글을 찾을 수 없습니다."));
        noticePost.setNpHitCnt(noticePost.getNpHitCnt() + 1);
        noticePostRepository.save(noticePost);
    }

    private NoticePostResponseDto convertToDto(NoticePost noticePost) {
        List<String> imageUrls = noticePost.getImages().stream()
                .map(NoticePostImage::getImageUrl)
                .collect(Collectors.toList());

        return new NoticePostResponseDto(
                noticePost.getNPostId(),
                noticePost.getNpTitle(),
                noticePost.getNpContent(),
                noticePost.getNpUserLikeCnt(),
                noticePost.getNpHitCnt(),
                noticePost.getNpCreateTime(),
                noticePost.getNpUpdateTime(),
                imageUrls
        );
    }
}
