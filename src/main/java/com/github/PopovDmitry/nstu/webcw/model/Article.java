package com.github.PopovDmitry.nstu.webcw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Article {

    private long id;
    private String author;
    private String title;
    private String content;
    private Date timestamp;

}
