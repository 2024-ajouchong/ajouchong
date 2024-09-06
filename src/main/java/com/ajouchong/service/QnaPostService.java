package com.ajouchong.service;

import com.ajouchong.dto.AnswerRequestDto;
import com.ajouchong.dto.AnswerResponseDto;
import com.ajouchong.dto.QnaPostRequestDto;
import com.ajouchong.dto.QnaPostResponseDto;
import com.ajouchong.entity.Answer;
import com.ajouchong.entity.QnaPost;
import com.ajouchong.repository.AnswerRepository;
import com.ajouchong.repository.QnaPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QnaPostService {
    private final QnaPostRepository qnaPostRepository;

    public QnaPostService(QnaPostRepository qnaPostRepository, AnswerRepository answerRepository) {
        this.qnaPostRepository = qnaPostRepository;
    }

    @Transactional
    public QnaPostResponseDto createPost(QnaPostRequestDto requestDto) {
        QnaPost post = new QnaPost();

        post.setQpTitle(requestDto.getQpTitle());
        post.setQpContent(requestDto.getQpContent());

        post.setQpUserLikeCnt(0);
        post.setQpHitCnt(0);
        post.setQpUpdateTime(LocalDateTime.now());
        post.setQpCreateTime(LocalDateTime.now());

        qnaPostRepository.save(post);

        return new QnaPostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public Optional<QnaPost> getPostById(Long postId) {
        return qnaPostRepository.findById(postId);
    }

    @Transactional(readOnly = true)
    public List<QnaPost> getAllPosts() {
        return qnaPostRepository.findAll();
    }

    @Transactional
    public void incrementHitCount(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다."));

        post.incrementHitCount();
        qnaPostRepository.save(post);
    }

    @Transactional
    public void incrementUserLikeCount(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다."));

        post.incrementUserLikeCount();
        qnaPostRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다."));

        qnaPostRepository.delete(post);
    }

    @Transactional
    public AnswerResponseDto addAnswer(Long postId, AnswerRequestDto requestDto) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다."));

        Answer answer = post.getAnswer();

        if (answer != null) {
            answer.setContent(requestDto.getContent());
            answer.setUpdateTime(LocalDateTime.now());
        } else {
            answer = new Answer();
            answer.setContent(requestDto.getContent());
            answer.setQnaPost(post);
            answer.setCreateTime(LocalDateTime.now());
            answer.setUpdateTime(LocalDateTime.now());
            post.setAnswer(answer);
            post.setReplied(true);

        }

        qnaPostRepository.save(post);

        return new AnswerResponseDto(answer);
    }

    @Transactional
    public QnaPostResponseDto deleteAnswer(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (post.getAnswer() != null) {
            post.setAnswer(null);
            post.setReplied(false);
            QnaPost updatedPost = qnaPostRepository.save(post);
            return new QnaPostResponseDto(updatedPost);
        } else {
            throw new IllegalArgumentException("해당 게시글에 답변이 없습니다.");
        }
    }
}