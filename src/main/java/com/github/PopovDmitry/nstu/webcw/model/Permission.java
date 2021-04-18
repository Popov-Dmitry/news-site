package com.github.PopovDmitry.nstu.webcw.model;

public enum Permission {
    ARTICLES_READ("articles:read"),
    ARTICLES_WRITE("articles:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
