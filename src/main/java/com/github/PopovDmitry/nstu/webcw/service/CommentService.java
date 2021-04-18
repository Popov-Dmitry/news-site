package com.github.PopovDmitry.nstu.webcw.service;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.Comment;
import com.github.PopovDmitry.nstu.webcw.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }

    public void addComment(Comment comment) {
        logger.info("addComment");
        commentRepository.save(comment);
    }

    public Optional<List<Comment>> getAllCommentsByArticle(Article article) {
        logger.info("getAllCommentsByArticle");
        return commentRepository.findAllByArticle(article);
    }
}
