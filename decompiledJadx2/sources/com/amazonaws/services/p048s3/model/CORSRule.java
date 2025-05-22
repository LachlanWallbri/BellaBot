package com.amazonaws.services.p048s3.model;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class CORSRule {
    private List<String> allowedHeaders;
    private List<AllowedMethods> allowedMethods;
    private List<String> allowedOrigins;
    private List<String> exposedHeaders;

    /* renamed from: id */
    private String f1183id;
    private int maxAgeSeconds;

    public void setId(String str) {
        this.f1183id = str;
    }

    public String getId() {
        return this.f1183id;
    }

    public CORSRule withId(String str) {
        this.f1183id = str;
        return this;
    }

    public void setAllowedMethods(List<AllowedMethods> list) {
        this.allowedMethods = list;
    }

    public void setAllowedMethods(AllowedMethods... allowedMethodsArr) {
        this.allowedMethods = Arrays.asList(allowedMethodsArr);
    }

    public List<AllowedMethods> getAllowedMethods() {
        return this.allowedMethods;
    }

    public CORSRule withAllowedMethods(List<AllowedMethods> list) {
        this.allowedMethods = list;
        return this;
    }

    public void setAllowedOrigins(List<String> list) {
        this.allowedOrigins = list;
    }

    public void setAllowedOrigins(String... strArr) {
        this.allowedOrigins = Arrays.asList(strArr);
    }

    public List<String> getAllowedOrigins() {
        return this.allowedOrigins;
    }

    public CORSRule withAllowedOrigins(List<String> list) {
        this.allowedOrigins = list;
        return this;
    }

    public void setMaxAgeSeconds(int i) {
        this.maxAgeSeconds = i;
    }

    public int getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public CORSRule withMaxAgeSeconds(int i) {
        this.maxAgeSeconds = i;
        return this;
    }

    public void setExposedHeaders(List<String> list) {
        this.exposedHeaders = list;
    }

    public void setExposedHeaders(String... strArr) {
        this.exposedHeaders = Arrays.asList(strArr);
    }

    public List<String> getExposedHeaders() {
        return this.exposedHeaders;
    }

    public CORSRule withExposedHeaders(List<String> list) {
        this.exposedHeaders = list;
        return this;
    }

    public void setAllowedHeaders(List<String> list) {
        this.allowedHeaders = list;
    }

    public void setAllowedHeaders(String... strArr) {
        this.allowedHeaders = Arrays.asList(strArr);
    }

    public List<String> getAllowedHeaders() {
        return this.allowedHeaders;
    }

    public CORSRule withAllowedHeaders(List<String> list) {
        this.allowedHeaders = list;
        return this;
    }

    /* loaded from: classes.dex */
    public enum AllowedMethods {
        GET("GET"),
        PUT("PUT"),
        HEAD("HEAD"),
        POST("POST"),
        DELETE("DELETE");

        private final String AllowedMethod;

        AllowedMethods(String str) {
            this.AllowedMethod = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.AllowedMethod;
        }

        public static AllowedMethods fromValue(String str) throws IllegalArgumentException {
            for (AllowedMethods allowedMethods : values()) {
                String allowedMethods2 = allowedMethods.toString();
                if (allowedMethods2 == null && str == null) {
                    return allowedMethods;
                }
                if (allowedMethods2 != null && allowedMethods2.equals(str)) {
                    return allowedMethods;
                }
            }
            throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
        }
    }
}
