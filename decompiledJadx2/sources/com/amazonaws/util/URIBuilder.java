package com.amazonaws.util;

import com.amazonaws.Protocol;
import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes.dex */
public class URIBuilder {
    private static final int DEFAULT_PORT = -1;
    private static final String DEFAULT_SCHEME = Protocol.HTTPS.toString();
    private String fragment;
    private String host;
    private String path;
    private int port;
    private String query;
    private String scheme;
    private String userInfo;

    private URIBuilder() {
        this.scheme = DEFAULT_SCHEME;
        this.port = -1;
    }

    private URIBuilder(URI uri) {
        this.scheme = uri.getScheme();
        this.userInfo = uri.getUserInfo();
        this.host = uri.getHost();
        this.port = uri.getPort();
        this.path = uri.getPath();
        this.query = uri.getQuery();
        this.fragment = uri.getFragment();
    }

    public static URIBuilder builder() {
        return new URIBuilder();
    }

    public static URIBuilder builder(URI uri) {
        return new URIBuilder(uri);
    }

    public URIBuilder scheme(String str) {
        this.scheme = str;
        return this;
    }

    public URIBuilder userInfo(String str) {
        this.userInfo = str;
        return this;
    }

    public URIBuilder host(String str) {
        this.host = str;
        return this;
    }

    public URIBuilder port(int i) {
        this.port = i;
        return this;
    }

    public URIBuilder path(String str) {
        this.path = str;
        return this;
    }

    public URIBuilder query(String str) {
        this.query = str;
        return this;
    }

    public URIBuilder fragment(String str) {
        this.fragment = str;
        return this;
    }

    public URI build() throws URISyntaxException {
        return new URI(this.scheme, this.userInfo, this.host, this.port, this.path, this.query, this.fragment);
    }
}
