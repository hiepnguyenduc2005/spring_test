package com.example.runner.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserHttpClient {

    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/user/{id}")
    User findById(@PathVariable Integer id);
}
