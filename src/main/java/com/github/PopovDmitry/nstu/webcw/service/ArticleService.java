package com.github.PopovDmitry.nstu.webcw.service;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public Optional<Article> getArticle(long id) {
        return articleRepository.findById(id);
    }

    public Optional<Article> getArticle(User user) {
        return articleRepository.findByAuthor(user);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
