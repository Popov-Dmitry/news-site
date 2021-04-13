package com.github.PopovDmitry.nstu.webcw.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User author;

    @NotEmpty(message = "Название статьи не может быть пустым")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Содержание статьи не может быть пустым")
    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Date timestamp;

}
