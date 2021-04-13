package com.github.PopovDmitry.nstu.webcw.controller.rest;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dev/api/public/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) { this.articleService = articleService; }

//    @GetMapping
//    public ResponseEntity<List<Article>> getSiteArticles() {
//        return ResponseEntity.ok(articleService.getAllArticles());
//    }

    @GetMapping
    public ResponseEntity<List<Article>> getSiteArticlesLimit(@RequestParam("offset") int offset,
                                                              @RequestParam("limit") int limit) {
        return ResponseEntity.ok(articleService.getArticlesLimit(offset, limit));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getArticlesCount() { return ResponseEntity.ok(articleService.getArticlesCount()); }

}
