package com.github.PopovDmitry.nstu.webcw.service;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.Comment;
import com.github.PopovDmitry.nstu.webcw.repository.CommentRepository;
import com.github.PopovDmitry.nstu.webcw.security.JwtAuthenticationException;
import com.github.PopovDmitry.nstu.webcw.security.JwtTokenProvider;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ArticleService articleService;

    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository, JwtTokenProvider jwtTokenProvider, UserService userService, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.articleService = articleService;
    }

    public void addComment(Comment comment, HttpServletRequest httpServletRequest) {
        logger.info("addComment");
        try {
            jwtTokenProvider.validate(jwtTokenProvider.resolveToken(httpServletRequest));

            comment.setTimestamp(new Date(new java.util.Date().getTime()));
            comment.setUser(userService.getUser(
                    jwtTokenProvider.getUsername(
                            jwtTokenProvider.resolveToken(httpServletRequest))).get());
            commentRepository.save(comment);
        }
        catch (JwtAuthenticationException exception) {
            logger.info("Can't saving comment");
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }

    }

    public Optional<List<Comment>> getAllCommentsByArticleId(long id) throws NotFoundException {
        logger.info("getAllCommentsByArticleId {}", id);
        if (articleService.getArticle(id).isPresent()) {
            return commentRepository.findAllByArticle(articleService.getArticle(id).get());
        }
        throw new NotFoundException("Page is not found");
    }
}
