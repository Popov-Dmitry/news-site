package com.github.PopovDmitry.nstu.webcw.repository;

import com.github.PopovDmitry.nstu.webcw.model.Article;
import com.github.PopovDmitry.nstu.webcw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(long id);
    Optional<Article> findByAuthor(User author);
    Optional<List<Article>> findAllByContentContainingIgnoreCase(String query);
    Optional<List<Article>> findAllByTitleContainingIgnoreCase(String query);
}
