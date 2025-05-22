package com.google.auth.oauth2;

import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.auth.oauth2.StsTokenExchangeResponse;
import com.google.common.base.Joiner;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class StsRequestHandler {
    private static final String PARSE_ERROR_PREFIX = "Error parsing token response.";
    private static final String REQUESTED_TOKEN_TYPE = "urn:ietf:params:oauth:token-type:access_token";
    private static final String TOKEN_EXCHANGE_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:token-exchange";

    @Nullable
    private final HttpHeaders headers;
    private final HttpRequestFactory httpRequestFactory;

    @Nullable
    private final String internalOptions;
    private final StsTokenExchangeRequest request;
    private final String tokenExchangeEndpoint;

    private StsRequestHandler(String str, StsTokenExchangeRequest stsTokenExchangeRequest, HttpRequestFactory httpRequestFactory, @Nullable HttpHeaders httpHeaders, @Nullable String str2) {
        this.tokenExchangeEndpoint = str;
        this.request = stsTokenExchangeRequest;
        this.httpRequestFactory = httpRequestFactory;
        this.headers = httpHeaders;
        this.internalOptions = str2;
    }

    public static Builder newBuilder(String str, StsTokenExchangeRequest stsTokenExchangeRequest, HttpRequestFactory httpRequestFactory) {
        return new Builder(str, stsTokenExchangeRequest, httpRequestFactory);
    }

    public StsTokenExchangeResponse exchangeToken() throws IOException {
        HttpRequest buildPostRequest = this.httpRequestFactory.buildPostRequest(new GenericUrl(this.tokenExchangeEndpoint), new UrlEncodedContent(buildTokenRequest()));
        buildPostRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        HttpHeaders httpHeaders = this.headers;
        if (httpHeaders != null) {
            buildPostRequest.setHeaders(httpHeaders);
        }
        try {
            return buildResponse((GenericData) buildPostRequest.execute().parseAs(GenericData.class));
        } catch (HttpResponseException e) {
            GenericJson parseJson = parseJson(e.getContent());
            throw new OAuthException((String) parseJson.get("error"), parseJson.containsKey(TrackKey.ERROR_DESCRIPTION) ? (String) parseJson.get(TrackKey.ERROR_DESCRIPTION) : null, parseJson.containsKey("error_uri") ? (String) parseJson.get("error_uri") : null);
        }
    }

    private GenericData buildTokenRequest() {
        GenericData genericData = new GenericData().set(OAuth2Constants.GRANT_TYPE, TOKEN_EXCHANGE_GRANT_TYPE).set("subject_token_type", this.request.getSubjectTokenType()).set("subject_token", this.request.getSubjectToken());
        ArrayList arrayList = new ArrayList();
        if (this.request.hasScopes()) {
            arrayList.addAll(this.request.getScopes());
            genericData.set("scope", Joiner.m608on(' ').join(arrayList));
        }
        genericData.set("requested_token_type", this.request.hasRequestedTokenType() ? this.request.getRequestedTokenType() : REQUESTED_TOKEN_TYPE);
        if (this.request.hasResource()) {
            genericData.set("resource", this.request.getResource());
        }
        if (this.request.hasAudience()) {
            genericData.set("audience", this.request.getAudience());
        }
        if (this.request.hasActingParty()) {
            genericData.set("actor_token", this.request.getActingParty().getActorToken());
            genericData.set("actor_token_type", this.request.getActingParty().getActorTokenType());
        }
        String str = this.internalOptions;
        if (str != null && !str.isEmpty()) {
            genericData.set("options", this.internalOptions);
        }
        return genericData;
    }

    private StsTokenExchangeResponse buildResponse(GenericData genericData) throws IOException {
        StsTokenExchangeResponse.Builder newBuilder = StsTokenExchangeResponse.newBuilder(OAuth2Utils.validateString(genericData, "access_token", PARSE_ERROR_PREFIX), OAuth2Utils.validateString(genericData, "issued_token_type", PARSE_ERROR_PREFIX), OAuth2Utils.validateString(genericData, "token_type", PARSE_ERROR_PREFIX), Long.valueOf(OAuth2Utils.validateLong(genericData, "expires_in", PARSE_ERROR_PREFIX)));
        if (genericData.containsKey("refresh_token")) {
            newBuilder.setRefreshToken(OAuth2Utils.validateString(genericData, "refresh_token", PARSE_ERROR_PREFIX));
        }
        if (genericData.containsKey("scope")) {
            newBuilder.setScopes(Arrays.asList(OAuth2Utils.validateString(genericData, "scope", PARSE_ERROR_PREFIX).trim().split("\\s+")));
        }
        return newBuilder.build();
    }

    private GenericJson parseJson(String str) throws IOException {
        return (GenericJson) OAuth2Utils.JSON_FACTORY.createJsonParser(str).parseAndClose(GenericJson.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {

        @Nullable
        private HttpHeaders headers;
        private final HttpRequestFactory httpRequestFactory;

        @Nullable
        private String internalOptions;
        private final StsTokenExchangeRequest request;
        private final String tokenExchangeEndpoint;

        private Builder(String str, StsTokenExchangeRequest stsTokenExchangeRequest, HttpRequestFactory httpRequestFactory) {
            this.tokenExchangeEndpoint = str;
            this.request = stsTokenExchangeRequest;
            this.httpRequestFactory = httpRequestFactory;
        }

        public Builder setHeaders(HttpHeaders httpHeaders) {
            this.headers = httpHeaders;
            return this;
        }

        public Builder setInternalOptions(String str) {
            this.internalOptions = str;
            return this;
        }

        public StsRequestHandler build() {
            return new StsRequestHandler(this.tokenExchangeEndpoint, this.request, this.httpRequestFactory, this.headers, this.internalOptions);
        }
    }
}
