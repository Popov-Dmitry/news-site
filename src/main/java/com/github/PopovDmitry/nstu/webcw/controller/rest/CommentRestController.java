package com.github.PopovDmitry.nstu.webcw.controller.rest;

import com.github.PopovDmitry.nstu.webcw.model.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dev/api/public/comments")
public class CommentRestController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getComments(@PathVariable String id) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}")
    public void addComment(@PathVariable String id, @RequestBody Comment comment) {

    }
}
