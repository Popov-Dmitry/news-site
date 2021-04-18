package com.github.PopovDmitry.nstu.webcw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @NotEmpty(message = "Комментарий не может быть пустым")
    private String content;
}
