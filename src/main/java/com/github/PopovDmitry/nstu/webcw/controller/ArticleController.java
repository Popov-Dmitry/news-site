package com.github.PopovDmitry.nstu.webcw.controller;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news")
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getNews() {
        logger.debug("getNews");
        return "views/index";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable long id) {
        logger.debug("getArticle , {}", id);
        return "";
    }

    @PostMapping()
    public String addArticle(@RequestBody Article article) {
        logger.debug("addArticle");
        articleService.saveArticle(article);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable long id) {
        logger.debug("deleteArticle , {}", id);
        return "";
    }

    @GetMapping("/new")
    public String newArticle() {
        logger.debug("newArticle");
        return "views/newArticle";
    }

}
