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

    public QnaPostService(QnaPostRepository qnaPostRepository, AnswerRepository answerRepository) {
        this.qnaPostRepository = qnaPostRepository;
    }

    @Transactional
    public QnaPost createPost(String title, String content) {
        QnaPost post = new QnaPost();
        post.setQpTitle(title);
        post.setQpContent(content);
        return qnaPostRepository.save(post);
    }

    @Transactional
    public QnaPost addAnswer(Long postId, String answerContent) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("QnaPost not found with id: " + postId));

        if (post.isReplied()) {
            throw new IllegalStateException("This post already has an answer.");
        }

        Answer answer = new Answer();
        answer.setContent(answerContent);
        answer.setQnaPost(post);

        post.setAnswer(answer);

        return qnaPostRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Optional<QnaPost> getPostById(Long postId) {
        return qnaPostRepository.findById(postId);
    }

    @Transactional
    public void incrementHitCount(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("QnaPost not found with id: " + postId));

        post.incrementHitCount();
        qnaPostRepository.save(post);
    }

    @Transactional
    public void incrementUserLikeCount(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("QnaPost not found with id: " + postId));

        post.incrementUserLikeCount();
        qnaPostRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        QnaPost post = qnaPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("QnaPost not found with id: " + postId));

        qnaPostRepository.delete(post);
    }

}