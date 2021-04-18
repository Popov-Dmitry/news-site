package com.github.PopovDmitry.nstu.webcw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    @NotEmpty(message = "Название статьи не может быть пустым")
    private String title;
    @NotEmpty(message = "Содержание статьи не может быть пустым")
    private String content;
}
