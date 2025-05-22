package com.amazonaws.http;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpRequestFactory {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public HttpRequest createHttpRequest(Request<?> request, ClientConfiguration clientConfiguration, ExecutionContext executionContext) {
        String appendUri;
        URI endpoint = request.getEndpoint();
        boolean z = true;
        if (request.getEncodedUriResourcePath() != null) {
            appendUri = HttpUtils.appendUriEncoded(endpoint.toString(), request.getEncodedUriResourcePath());
        } else {
            appendUri = HttpUtils.appendUri(endpoint.toString(), request.getResourcePath(), true);
        }
        String encodeParameters = HttpUtils.encodeParameters(request);
        HttpMethodName httpMethod = request.getHttpMethod();
        boolean z2 = request.getContent() != null;
        if ((httpMethod == HttpMethodName.POST) && !z2) {
            z = false;
        }
        if (encodeParameters != null && z) {
            appendUri = appendUri + "?" + encodeParameters;
        }
        HashMap hashMap = new HashMap();
        configureHeaders(hashMap, request, executionContext, clientConfiguration);
        InputStream content = request.getContent();
        if (httpMethod == HttpMethodName.PATCH) {
            httpMethod = HttpMethodName.POST;
            hashMap.put("X-HTTP-Method-Override", HttpMethodName.PATCH.toString());
        }
        if (httpMethod == HttpMethodName.POST && request.getContent() == null && encodeParameters != null) {
            byte[] bytes = encodeParameters.getBytes(StringUtils.UTF8);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            hashMap.put("Content-Length", String.valueOf(bytes.length));
            content = byteArrayInputStream;
        }
        if (clientConfiguration.isEnableGzip() && hashMap.get("Accept-Encoding") == null) {
            hashMap.put("Accept-Encoding", "gzip");
        } else {
            hashMap.put("Accept-Encoding", "identity");
        }
        HttpRequest httpRequest = new HttpRequest(httpMethod.toString(), URI.create(appendUri), hashMap, content);
        httpRequest.setStreaming(request.isStreaming());
        return httpRequest;
    }

    private void configureHeaders(Map<String, String> map, Request<?> request, ExecutionContext executionContext, ClientConfiguration clientConfiguration) {
        URI endpoint = request.getEndpoint();
        String host = endpoint.getHost();
        if (HttpUtils.isUsingNonDefaultPort(endpoint)) {
            host = host + ":" + endpoint.getPort();
        }
        map.put("Host", host);
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        if (map.get("Content-Type") == null || map.get("Content-Type").isEmpty()) {
            map.put("Content-Type", "application/x-www-form-urlencoded; charset=" + StringUtils.lowerCase("UTF-8"));
        }
        if (executionContext == null || executionContext.getContextUserAgent() == null) {
            return;
        }
        map.put("User-Agent", createUserAgentString(clientConfiguration, executionContext.getContextUserAgent()));
    }

    private String createUserAgentString(ClientConfiguration clientConfiguration, String str) {
        if (clientConfiguration.getUserAgent().contains(str)) {
            return clientConfiguration.getUserAgent();
        }
        return clientConfiguration.getUserAgent() + " " + str;
    }
}
