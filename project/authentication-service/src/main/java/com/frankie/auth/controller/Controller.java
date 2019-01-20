package com.frankie.auth.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @GetMapping("/api/get")
    public String get() {
        return "Hello World!";
    }

    @PostMapping("/api/post")
    public String post() {
        return "POST process has finished.";
    }

    @RequestMapping(value = {"/api/user"}, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put(
                "user",
                user.getUserAuthentication()
                        .getPrincipal());
        userInfo.put("authorities",
                AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }

}
