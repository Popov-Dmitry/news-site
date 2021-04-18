package com.github.PopovDmitry.nstu.webcw.service;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.repository.ArticleRepository;
import com.github.PopovDmitry.nstu.webcw.security.JwtTokenProvider;
import com.github.PopovDmitry.nstu.webcw.utils.BBParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void saveArticle(Article article, HttpServletRequest httpServletRequest) {
        logger.info("saveArticle with token: {}", jwtTokenProvider.resolveToken(httpServletRequest));
        logger.info("saveArticle with username: {}", jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(httpServletRequest)));

        article.setTimestamp(new Date(new java.util.Date().getTime()));
        article.setContent(BBParserUtil.parse(article.getContent()));
        article.setAuthor(
                userService.getUser(
                        jwtTokenProvider.getUsername(
                                jwtTokenProvider.resolveToken(httpServletRequest)))
                        .get());
        articleRepository.save(article);
    }

    public Optional<Article> getArticle(long id) {
        logger.info("getArticle with id {}", id);
        return articleRepository.findById(id);
    }

    public Optional<Article> getArticle(User user) {
        logger.info("getArticle");
        return articleRepository.findByAuthor(user);
    }

    public List<Article> getAllArticles() {
        logger.info("getAllArticles");
        return articleRepository.findAll();
    }

    public List<Article> getArticlesLimit(int offset, int limit, String sortBy) {
        logger.info("getArticlesLimit with offset {}", offset);
        Pageable pageable;
        switch (sortBy) {
            case "dateASC" -> {
                logger.info("sortBy {}", sortBy);
                pageable = PageRequest.of(offset / limit, limit, Sort.by("timestamp").ascending());
            }
            case "dateDESC" -> {
                logger.info("sortBy {}", sortBy);
                pageable = PageRequest.of(offset / limit, limit, Sort.by("timestamp").descending());
            }
            case "popularity" -> {
                logger.info("sortBy {}", sortBy);
                pageable = PageRequest.of(offset / limit, limit, Sort.by("views").descending());
            }
            default -> {
                logger.info("sortBy default");
                pageable = PageRequest.of(offset / limit, limit);
            }
        }

        return articleRepository.findAll(pageable).toList();
    }

    public Long getArticlesCount() {
        logger.info("getArticlesCount");
        return articleRepository.count();
    }

    public Optional<List<Article>> searchArticles(String query) {
        logger.info("searchArticles with query {}", query);

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
        logger.info("updateArticle");
        articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        logger.info("deleteArticle");
        articleRepository.delete(article);
    }
}
