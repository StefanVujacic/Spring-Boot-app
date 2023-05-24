package com.example.demo.entity;

public class JWT_response {

    private User1 user;
    private String jwt_token;


    public JWT_response(String jwt_token, User1 user) {
        this.jwt_token = jwt_token;
        this.user=user;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }
}
