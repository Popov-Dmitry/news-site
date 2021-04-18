package com.github.PopovDmitry.nstu.webcw.controller.rest;

import com.github.PopovDmitry.nstu.webcw.dto.CommentDTO;
import com.github.PopovDmitry.nstu.webcw.model.Comment;
import com.github.PopovDmitry.nstu.webcw.security.JwtAuthenticationException;
import com.github.PopovDmitry.nstu.webcw.service.ArticleService;
import com.github.PopovDmitry.nstu.webcw.service.CommentService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("dev/api/public/comments")
public class CommentRestController {

    private final CommentService commentService;
    private final ArticleService articleService;

    private final Logger logger = LoggerFactory.getLogger(CommentRestController.class);

    @Autowired
    public CommentRestController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentsLimit(@PathVariable long id) {
        logger.info("getComments by article id {}", id);
        try {
            if(commentService.getAllCommentsByArticleId(id).isPresent()) {
                return ResponseEntity.ok(commentService.getAllCommentsByArticleId(id).get());
            }
            return ResponseEntity.ok().build();
        }
        catch (NotFoundException exception) {
            logger.info("page with id {} is not found", id);
            return new ResponseEntity<>("Page is not found", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('comments:write')")
    @PostMapping("/{id}")
    public ResponseEntity<?> addComment(@PathVariable long id,
                                        @RequestBody @Valid CommentDTO commentDTO,
                                        HttpServletRequest httpServletRequest,
                                        BindingResult bindingResult) {
        logger.info("addComment by article id {}", id);
        if(bindingResult.hasErrors()) {
            logger.info("comment has errors");
            return new ResponseEntity<>("Comment is empty", HttpStatus.NO_CONTENT);
        }

        try {
            if(articleService.getArticle(id).isPresent()) {
                Comment comment = new Comment();
                comment.setContent(commentDTO.getContent());
                comment.setArticle(articleService.getArticle(id).get());
                commentService.addComment(comment, httpServletRequest);
                return ResponseEntity.ok().build();
            }
            logger.info("page with id {} is not found", id);
            return new ResponseEntity<>("Page is not found", HttpStatus.NOT_FOUND);
        }
        catch (JwtAuthenticationException exception) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
