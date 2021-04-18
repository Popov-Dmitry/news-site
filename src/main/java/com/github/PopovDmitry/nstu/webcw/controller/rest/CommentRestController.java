package com.github.PopovDmitry.nstu.webcw.controller.rest;

import com.github.PopovDmitry.nstu.webcw.dto.CommentDTO;
import com.github.PopovDmitry.nstu.webcw.model.Comment;
import com.github.PopovDmitry.nstu.webcw.security.JwtAuthenticationException;
import com.github.PopovDmitry.nstu.webcw.service.ArticleService;
import com.github.PopovDmitry.nstu.webcw.service.CommentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("dev/api/public/comments")
public class CommentRestController {

    private final CommentService commentService;
    private final ArticleService articleService;


    @Autowired
    public CommentRestController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComments(@PathVariable long id) {
        try {
            if(commentService.getAllCommentsByArticleId(id).isPresent()) {
                return ResponseEntity.ok(commentService.getAllCommentsByArticleId(id).get());
            }
            return ResponseEntity.ok().build();
        }
        catch (NotFoundException exception) {
            return new ResponseEntity<>("Page is not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addComment(@PathVariable long id,
                           @RequestBody CommentDTO commentDTO,
                           HttpServletRequest httpServletRequest) {
        try {
            if(articleService.getArticle(id).isPresent()) {
                Comment comment = new Comment();
                comment.setContent(commentDTO.getContent());
                comment.setArticle(articleService.getArticle(id).get());
                commentService.addComment(comment, httpServletRequest);
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<>("Page is not found", HttpStatus.NOT_FOUND);
        }
        catch (JwtAuthenticationException exception) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
