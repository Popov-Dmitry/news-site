package com.github.PopovDmitry.nstu.webcw.controller.rest;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dev/api/public/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    private final Logger logger = LoggerFactory.getLogger(ArticleRestController.class);

    @Autowired
    public ArticleRestController(ArticleService articleService) { this.articleService = articleService; }

    @GetMapping
    public ResponseEntity<List<Article>> getSiteArticlesLimit(@RequestParam("offset") int offset,
                                                              @RequestParam("limit") int limit,
                                                              @RequestParam("sortBy") String sortBy) {
        logger.info("getSiteArticlesLimit with offset={} and limit={}", offset, limit);
        return ResponseEntity.ok(articleService.getArticlesLimit(offset, limit, sortBy));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getArticlesCount() {
        logger.info("getArticlesCount");
        return ResponseEntity.ok(articleService.getArticlesCount());
    }

}
