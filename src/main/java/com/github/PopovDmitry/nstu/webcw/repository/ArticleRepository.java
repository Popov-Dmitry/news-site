package com.github.PopovDmitry.nstu.webcw.repository;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(long id);
    Optional<Article> findByAuthor(User author);
}
