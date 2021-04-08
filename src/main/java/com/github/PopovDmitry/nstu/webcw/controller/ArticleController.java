package com.github.PopovDmitry.nstu.webcw.controller;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String addArticle(@ModelAttribute("article") @Valid Article article, BindingResult bindingResult) {
        logger.debug("addArticle");
        if(bindingResult.hasErrors()) {
            logger.debug("Article has errors");
            return "views/newArticle";
        }

        articleService.saveArticle(article);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable long id) {
        logger.debug("deleteArticle , {}", id);
        return "";
    }

    @GetMapping("/new")
    public String newArticle(Model model) {
        logger.debug("newArticle");
        model.addAttribute("article", new Article());
        return "views/newArticle";
    }

}
