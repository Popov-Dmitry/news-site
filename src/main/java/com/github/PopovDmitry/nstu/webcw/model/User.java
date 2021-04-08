package com.github.PopovDmitry.nstu.webcw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private long id;
    private String firstName;
    private String secondName;
    private Role role;
    private Status status;

}
