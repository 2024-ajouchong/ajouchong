package com.ajouchong.service;

import com.ajouchong.entity.Answer;
import com.ajouchong.entity.QnaPost;
import com.ajouchong.repository.AnswerRepository;
import com.ajouchong.repository.QnaPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QnaPostService {
    private final QnaPostRepository qnaPostRepository;
    private final AnswerRepository answerRepository;

    public QnaPostService(QnaPostRepository qnaPostRepository, AnswerRepository answerRepository) {
        this.qnaPostRepository = qnaPostRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public QnaPost createPost(String title, String content) {
        QnaPost post = new QnaPost();
        post.setQpTitle(title);
        post.setQpContent(content);
        return qnaPostRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Optional<QnaPost> getPostById(Long postId) {
        return qnaPostRepository.findById(postId);
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
    public QnaPost addAnswer(Long postId, String answerContent) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다."));

        if (post.isReplied()) {
            throw new IllegalStateException("이미 답변이 존재합니다.");
        }

        Answer answer = new Answer();
        answer.setContent(answerContent);
        answer.setQnaPost(post);

        post.setAnswer(answer);

        return qnaPostRepository.save(post);
    }

    @Transactional
    public QnaPost changeAnswer(Long postId, String updatedAnswerContent) {
        Optional<QnaPost> postOptional = qnaPostRepository.findById(postId);
        if (postOptional.isPresent()) {
            QnaPost post = postOptional.get();
            if (post.getAnswer() != null) {
                post.getAnswer().setContent(updatedAnswerContent);
                return qnaPostRepository.save(post);
            } else {
                throw new IllegalArgumentException("해당 게시글에 답변이 없습니다.");
            }
        } else {
            throw new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public QnaPost deleteAnswer(Long postId) {
        Optional<QnaPost> postOptional = qnaPostRepository.findById(postId);
        if (postOptional.isPresent()) {
            QnaPost post = postOptional.get();
            if (post.getAnswer() != null) {
                post.setAnswer(null);
                post.setReplied(false);
                return qnaPostRepository.save(post);
            } else {
                throw new IllegalArgumentException("해당 게시글에 답변이 없습니다.");
            }
        } else {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }
}