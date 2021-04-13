package com.github.PopovDmitry.nstu.webcw.service;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.repository.ArticleRepository;
import com.github.PopovDmitry.nstu.webcw.utils.BBParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired private UserService userService; // FIXME: 11.04.2021

    @Autowired
    public ArticleService(ArticleRepository articleRepository) { this.articleRepository = articleRepository; }

    public void saveArticle(Article article) {
        article.setTimestamp(new Date(new java.util.Date().getTime()));
        article.setContent(BBParserUtil.parse(article.getContent()));
        article.setAuthor(userService.getUser(1).get()); // FIXME: 11.04.2021
        articleRepository.save(article);
    }

    public Optional<Article> getArticle(long id) { return articleRepository.findById(id); }

    public Optional<Article> getArticle(User user) {
        return articleRepository.findByAuthor(user);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> getArticlesLimit(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return articleRepository.findAll(pageable).toList();
    }

    public Long getArticlesCount() { return articleRepository.count(); }

    public Optional<List<Article>> searchArticles(String query) {

        String[] words = query.split(" ");
        List<Article> wordsResponseList = new ArrayList<>();
        for(String word : words) {
            articleRepository.findAllByTitleContainingIgnoreCase(word).ifPresent(wordsResponseList::addAll);
        }
        for(String word : words) {
            articleRepository.findAllByContentContainingIgnoreCase(word).ifPresent(wordsResponseList::addAll);
        }

        return Optional.ofNullable(Stream.of(
                articleRepository.findAllByTitleContainingIgnoreCase(query).get(),
                articleRepository.findAllByContentContainingIgnoreCase(query).get(),
                wordsResponseList)
                .flatMap(Collection::stream)
                .distinct()
                .toList());
    }

    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }
}
