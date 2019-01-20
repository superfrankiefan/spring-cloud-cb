package com.frankie.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String orgId = new String();

    public String getCorrelationId() {
        return correlationId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static String getUserId() {
        return USER_ID;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public static String getOrgId() {
        return ORG_ID;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static String getAuthToken() {
        return AUTH_TOKEN;
    }
}
