package com.github.PopovDmitry.nstu.webcw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Имя не может быть пустым")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "Email не может быть пустым")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым")
    @Min(value = 5, message = "Пароль должен состоять от 5 символов")
    private String password;

    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>();

    public String getFullName() { return firstName + " " + lastName; }

}
