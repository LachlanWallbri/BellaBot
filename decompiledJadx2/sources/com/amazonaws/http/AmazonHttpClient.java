package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.RequestClientOptions;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.Signer;
import com.amazonaws.handlers.CredentialsRequestHandler;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.internal.CRC32MismatchException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.retry.RetryUtils;
import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.TimingInfo;
import com.amazonaws.util.URIBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class AmazonHttpClient {
    private static final String HEADER_SDK_RETRY_INFO = "aws-sdk-retry";
    private static final String HEADER_SDK_TRANSACTION_ID = "aws-sdk-invocation-id";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final int HTTP_STATUS_MULTIPLE_CHOICES = 300;
    private static final int HTTP_STATUS_OK = 200;
    private static final int HTTP_STATUS_REQ_TOO_LONG = 413;
    private static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;
    private static final int HTTP_STATUS_TEMP_REDIRECT = 307;
    private static final long TIME_MILLISEC = 1000;
    final ClientConfiguration config;
    final HttpClient httpClient;
    private final HttpRequestFactory requestFactory;
    private final RequestMetricCollector requestMetricCollector;
    private static final Log REQUEST_LOG = LogFactory.getLog("com.amazonaws.request");
    static final Log log = LogFactory.getLog((Class<?>) AmazonHttpClient.class);

    @Deprecated
    public ResponseMetadata getResponseMetadataForRequest(AmazonWebServiceRequest amazonWebServiceRequest) {
        return null;
    }

    public AmazonHttpClient(ClientConfiguration clientConfiguration) {
        this(clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonHttpClient(ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        this(clientConfiguration, new UrlHttpClient(clientConfiguration), requestMetricCollector);
    }

    public AmazonHttpClient(ClientConfiguration clientConfiguration, HttpClient httpClient) {
        this.requestFactory = new HttpRequestFactory();
        this.config = clientConfiguration;
        this.httpClient = httpClient;
        this.requestMetricCollector = null;
    }

    @Deprecated
    public AmazonHttpClient(ClientConfiguration clientConfiguration, HttpClient httpClient, RequestMetricCollector requestMetricCollector) {
        this.requestFactory = new HttpRequestFactory();
        this.config = clientConfiguration;
        this.httpClient = httpClient;
        this.requestMetricCollector = requestMetricCollector;
    }

    public <T> Response<T> execute(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> httpResponseHandler, HttpResponseHandler<AmazonServiceException> httpResponseHandler2, ExecutionContext executionContext) {
        if (request.getHostPrefix() != null) {
            try {
                URI endpoint = request.getEndpoint();
                request.setEndpoint(URIBuilder.builder(endpoint).host(request.getHostPrefix() + endpoint.getHost()).build());
            } catch (URISyntaxException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Failed to prepend host prefix: " + e.getMessage(), e);
                }
            }
        }
        if (executionContext == null) {
            throw new AmazonClientException("Internal SDK Error: No execution context parameter specified.");
        }
        List<RequestHandler2> requestHandler2s = requestHandler2s(request, executionContext);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        Response<T> response = null;
        try {
            response = executeHelper(request, httpResponseHandler, httpResponseHandler2, executionContext);
            afterResponse(request, requestHandler2s, response, awsRequestMetrics.getTimingInfo().endTiming());
            return response;
        } catch (AmazonClientException e2) {
            afterError(request, response, requestHandler2s, e2);
            throw e2;
        }
    }

    void afterError(Request<?> request, Response<?> response, List<RequestHandler2> list, AmazonClientException amazonClientException) {
        Iterator<RequestHandler2> it = list.iterator();
        while (it.hasNext()) {
            it.next().afterError(request, response, amazonClientException);
        }
    }

    <T> void afterResponse(Request<?> request, List<RequestHandler2> list, Response<T> response, TimingInfo timingInfo) {
        Iterator<RequestHandler2> it = list.iterator();
        while (it.hasNext()) {
            it.next().afterResponse(request, response);
        }
    }

    List<RequestHandler2> requestHandler2s(Request<?> request, ExecutionContext executionContext) {
        List<RequestHandler2> requestHandler2s = executionContext.getRequestHandler2s();
        if (requestHandler2s == null) {
            return Collections.emptyList();
        }
        for (RequestHandler2 requestHandler2 : requestHandler2s) {
            if (requestHandler2 instanceof CredentialsRequestHandler) {
                ((CredentialsRequestHandler) requestHandler2).setCredentials(executionContext.getCredentials());
            }
            requestHandler2.beforeRequest(request);
        }
        return requestHandler2s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x034d A[Catch: all -> 0x03d8, TRY_ENTER, TryCatch #36 {all -> 0x03d8, blocks: (B:43:0x0343, B:46:0x034d, B:47:0x0365, B:49:0x03ab, B:63:0x03d7, B:100:0x0281, B:102:0x0287, B:104:0x028d, B:105:0x0294, B:118:0x02bb), top: B:42:0x0343 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x03ab A[Catch: all -> 0x03d8, TRY_LEAVE, TryCatch #36 {all -> 0x03d8, blocks: (B:43:0x0343, B:46:0x034d, B:47:0x0365, B:49:0x03ab, B:63:0x03d7, B:100:0x0281, B:102:0x0287, B:104:0x028d, B:105:0x0294, B:118:0x02bb), top: B:42:0x0343 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x03d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v46, types: [com.amazonaws.http.HttpRequestFactory] */
    /* JADX WARN: Type inference failed for: r27v0, types: [com.amazonaws.http.AmazonHttpClient] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [com.amazonaws.http.ExecutionContext] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    <T> Response<T> executeHelper(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> httpResponseHandler, HttpResponseHandler<AmazonServiceException> httpResponseHandler2, ExecutionContext executionContext) {
        Throwable th;
        Object obj;
        Object obj2;
        LinkedHashMap linkedHashMap;
        HashMap hashMap;
        long j;
        Signer signer;
        int i;
        HttpResponse httpResponse;
        Object obj3;
        AmazonClientException amazonClientException;
        Object obj4;
        Signer signer2;
        HttpRequest createHttpRequest;
        HttpRequest httpRequest;
        Object obj5;
        Object obj6;
        Object obj7;
        AmazonServiceException handleErrorResponse;
        ?? r9 = executionContext;
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.addProperty(AWSRequestMetrics.Field.ServiceName, request.getServiceName());
        awsRequestMetrics.addProperty(AWSRequestMetrics.Field.ServiceEndpoint, request.getEndpoint());
        setUserAgent(request);
        request.addHeader(HEADER_SDK_TRANSACTION_ID, UUID.randomUUID().toString());
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(request.getParameters());
        HashMap hashMap2 = new HashMap(request.getHeaders());
        InputStream content = request.getContent();
        if (content != null && content.markSupported()) {
            content.mark(-1);
        }
        AWSCredentials credentials = executionContext.getCredentials();
        int i2 = 0;
        boolean z = false;
        long j2 = 0;
        AmazonClientException amazonClientException2 = null;
        Signer signer3 = null;
        HttpResponse httpResponse2 = null;
        HttpRequest httpRequest2 = null;
        URI uri = null;
        while (true) {
            HttpRequest httpRequest3 = httpRequest2;
            int i3 = i2 + 1;
            long j3 = j2;
            awsRequestMetrics.setCounter(AWSRequestMetrics.Field.RequestCount, i3);
            if (i3 > 1) {
                request.setParameters(linkedHashMap2);
                request.setHeaders(hashMap2);
                request.setContent(content);
            }
            if (uri != null && request.getEndpoint() == null && request.getResourcePath() == null) {
                request.setEndpoint(URI.create(uri.getScheme() + "://" + uri.getAuthority()));
                request.setResourcePath(uri.getPath());
            }
            try {
                if (i3 > 1) {
                    try {
                        try {
                            try {
                                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RetryPauseTime);
                                try {
                                    j3 = pauseBeforeNextRetry(request.getOriginalRequest(), amazonClientException2, i3, this.config.getRetryPolicy());
                                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RetryPauseTime);
                                    InputStream content2 = request.getContent();
                                    if (content2 != null && content2.markSupported()) {
                                        content2.reset();
                                    }
                                } catch (Throwable th2) {
                                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RetryPauseTime);
                                    throw th2;
                                    break;
                                }
                            } catch (Error e) {
                                e = e;
                                throw ((Error) handleUnexpectedFailure(e, awsRequestMetrics));
                            } catch (RuntimeException e2) {
                                e = e2;
                                throw ((RuntimeException) handleUnexpectedFailure(e, awsRequestMetrics));
                            }
                        } catch (IOException e3) {
                            e = e3;
                            obj2 = "Cannot close the response content.";
                            linkedHashMap = linkedHashMap2;
                            hashMap = hashMap2;
                            j = j3;
                            signer = signer3;
                            httpResponse = httpResponse2;
                            i = i3;
                            obj3 = obj2;
                            try {
                                if (log.isDebugEnabled()) {
                                }
                                awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                httpResponse2 = httpResponse;
                                obj = obj3;
                                if (!z) {
                                }
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        obj = "Cannot close the response content.";
                        if (!z) {
                            throw th;
                        }
                        if (httpResponse2 == null) {
                            throw th;
                        }
                        try {
                            if (httpResponse2.getRawContent() == null) {
                                throw th;
                            }
                            httpResponse2.getRawContent().close();
                            throw th;
                        } catch (IOException e4) {
                            log.warn(obj, e4);
                            throw th;
                        }
                    }
                }
                linkedHashMap = linkedHashMap2;
                hashMap = hashMap2;
                long j4 = j3;
                try {
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append(i3 - 1);
                        sb.append("/");
                        sb.append(j4);
                        request.addHeader(HEADER_SDK_RETRY_INFO, sb.toString());
                        if (signer3 == null) {
                            try {
                                signer3 = r9.getSignerByURI(request.getEndpoint());
                            } catch (IOException e5) {
                                e = e5;
                                signer = signer3;
                                obj2 = "Cannot close the response content.";
                                j = j4;
                                httpResponse = httpResponse2;
                                i = i3;
                                obj3 = obj2;
                                if (log.isDebugEnabled()) {
                                }
                                awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                }
                            }
                        }
                        signer2 = signer3;
                        if (signer2 != null && credentials != null) {
                            try {
                                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestSigningTime);
                                try {
                                    signer2.sign(request, credentials);
                                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestSigningTime);
                                } catch (Throwable th5) {
                                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestSigningTime);
                                    throw th5;
                                    break;
                                }
                            } catch (IOException e6) {
                                e = e6;
                                signer = signer2;
                                obj2 = "Cannot close the response content.";
                                j = j4;
                                httpResponse = httpResponse2;
                                i = i3;
                                obj3 = obj2;
                                if (log.isDebugEnabled()) {
                                }
                                awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                }
                            }
                        }
                        try {
                            if (REQUEST_LOG.isDebugEnabled()) {
                                REQUEST_LOG.debug("Sending Request: " + request.toString());
                            }
                            createHttpRequest = this.requestFactory.createHttpRequest(request, this.config, r9);
                            try {
                                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.HttpRequestTime);
                            } catch (IOException e7) {
                                e = e7;
                                httpRequest = createHttpRequest;
                                signer = signer2;
                                obj5 = "Cannot close the response content.";
                                j = j4;
                                i = i3;
                            }
                        } catch (IOException e8) {
                            e = e8;
                            signer = signer2;
                            obj4 = "Cannot close the response content.";
                            j = j4;
                            i = i3;
                            httpResponse = httpResponse2;
                            obj3 = obj4;
                            if (log.isDebugEnabled()) {
                            }
                            awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                            amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                            if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                            }
                        }
                    } catch (IOException e9) {
                        e = e9;
                        obj4 = "Cannot close the response content.";
                        j = j4;
                        i = i3;
                        signer = signer3;
                    }
                    try {
                        httpResponse2 = this.httpClient.execute(createHttpRequest);
                        try {
                            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.HttpRequestTime);
                        } catch (IOException e10) {
                            e = e10;
                            httpRequest = createHttpRequest;
                            signer = signer2;
                            obj5 = "Cannot close the response content.";
                            j = j4;
                        } catch (Error e11) {
                            e = e11;
                        } catch (RuntimeException e12) {
                            e = e12;
                        } catch (Throwable th6) {
                            th = th6;
                            r9 = "Cannot close the response content.";
                        }
                        if (isRequestSuccessful(httpResponse2)) {
                            try {
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.StatusCode, Integer.valueOf(httpResponse2.getStatusCode()));
                                z = httpResponseHandler.needsConnectionLeftOpen();
                                Response<T> response = new Response<>(handleResponse(request, httpResponseHandler, httpResponse2, r9), httpResponse2);
                                if (!z && httpResponse2 != null) {
                                    try {
                                        if (httpResponse2.getRawContent() != null) {
                                            httpResponse2.getRawContent().close();
                                        }
                                    } catch (IOException e13) {
                                        log.warn("Cannot close the response content.", e13);
                                    }
                                }
                                return response;
                            } catch (IOException e14) {
                                e = e14;
                                httpRequest3 = createHttpRequest;
                                signer = signer2;
                                obj2 = "Cannot close the response content.";
                                j = j4;
                                httpResponse = httpResponse2;
                                i = i3;
                                obj3 = obj2;
                                if (log.isDebugEnabled()) {
                                    log.debug("Unable to execute HTTP request: " + e.getMessage(), e);
                                }
                                awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                    throw amazonClientException;
                                }
                                resetRequestAfterError(request, e);
                                if (!z && httpResponse != null) {
                                    try {
                                        if (httpResponse.getRawContent() != null) {
                                            httpResponse.getRawContent().close();
                                        }
                                    } catch (IOException e15) {
                                        log.warn(obj3, e15);
                                    }
                                }
                                httpResponse2 = httpResponse;
                                httpRequest2 = httpRequest3;
                                signer3 = signer;
                                amazonClientException2 = amazonClientException;
                                j2 = j;
                                r9 = executionContext;
                                i2 = i;
                                linkedHashMap2 = linkedHashMap;
                                hashMap2 = hashMap;
                            }
                        } else {
                            if (isTemporaryRedirect(httpResponse2)) {
                                try {
                                    String str = httpResponse2.getHeaders().get("Location");
                                    Log log2 = log;
                                    signer = signer2;
                                    try {
                                        StringBuilder sb2 = new StringBuilder();
                                        obj6 = "Cannot close the response content.";
                                        try {
                                            sb2.append("Redirecting to: ");
                                            sb2.append(str);
                                            log2.debug(sb2.toString());
                                            uri = URI.create(str);
                                            request.setEndpoint(null);
                                            request.setResourcePath(null);
                                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.StatusCode, Integer.valueOf(httpResponse2.getStatusCode()));
                                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.RedirectLocation, str);
                                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                            httpRequest = createHttpRequest;
                                            j = j4;
                                            obj7 = obj6;
                                            amazonClientException2 = null;
                                            httpResponse = httpResponse2;
                                            i = i3;
                                        } catch (IOException e16) {
                                            e = e16;
                                            httpRequest3 = createHttpRequest;
                                            j = j4;
                                            obj2 = obj6;
                                            httpResponse = httpResponse2;
                                            i = i3;
                                            obj3 = obj2;
                                            if (log.isDebugEnabled()) {
                                            }
                                            awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                            amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                            if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                            }
                                        } catch (Error e17) {
                                            e = e17;
                                            throw ((Error) handleUnexpectedFailure(e, awsRequestMetrics));
                                        } catch (RuntimeException e18) {
                                            e = e18;
                                            throw ((RuntimeException) handleUnexpectedFailure(e, awsRequestMetrics));
                                        } catch (Throwable th7) {
                                            th = th7;
                                            obj = obj6;
                                            if (!z) {
                                            }
                                        }
                                    } catch (IOException e19) {
                                        e = e19;
                                        httpRequest3 = createHttpRequest;
                                        obj2 = "Cannot close the response content.";
                                        j = j4;
                                        httpResponse = httpResponse2;
                                        i = i3;
                                        obj3 = obj2;
                                        if (log.isDebugEnabled()) {
                                        }
                                        awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                        awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                        awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                        amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                        if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                        }
                                    }
                                } catch (IOException e20) {
                                    e = e20;
                                    signer = signer2;
                                }
                            } else {
                                signer = signer2;
                                obj6 = "Cannot close the response content.";
                                try {
                                    z = httpResponseHandler2.needsConnectionLeftOpen();
                                    handleErrorResponse = handleErrorResponse(request, httpResponseHandler2, httpResponse2);
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, handleErrorResponse.getRequestId());
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSErrorCode, handleErrorResponse.getErrorCode());
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.StatusCode, Integer.valueOf(handleErrorResponse.getStatusCode()));
                                    httpRequest = createHttpRequest;
                                    obj5 = obj6;
                                    j = j4;
                                    httpResponse = httpResponse2;
                                    i = i3;
                                } catch (IOException e21) {
                                    e = e21;
                                    httpRequest = createHttpRequest;
                                    j = j4;
                                    obj5 = obj6;
                                    httpResponse = httpResponse2;
                                    i = i3;
                                    httpRequest3 = httpRequest;
                                    obj3 = obj5;
                                    if (log.isDebugEnabled()) {
                                    }
                                    awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                    amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                    if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                    }
                                } catch (Error e22) {
                                    e = e22;
                                    throw ((Error) handleUnexpectedFailure(e, awsRequestMetrics));
                                } catch (RuntimeException e23) {
                                    e = e23;
                                    throw ((RuntimeException) handleUnexpectedFailure(e, awsRequestMetrics));
                                } catch (Throwable th8) {
                                    th = th8;
                                    r9 = obj6;
                                    th = th;
                                    obj = r9;
                                    if (!z) {
                                    }
                                }
                                try {
                                    if (!shouldRetry(request.getOriginalRequest(), createHttpRequest.getContent(), handleErrorResponse, i3, this.config.getRetryPolicy())) {
                                        throw handleErrorResponse;
                                        break;
                                    }
                                    if (RetryUtils.isClockSkewError(handleErrorResponse)) {
                                        SDKGlobalConfiguration.setGlobalTimeOffset(parseClockSkewOffset(httpResponse, handleErrorResponse));
                                    }
                                    resetRequestAfterError(request, handleErrorResponse);
                                    amazonClientException2 = handleErrorResponse;
                                    obj7 = obj5;
                                } catch (IOException e24) {
                                    e = e24;
                                    httpRequest3 = httpRequest;
                                    obj3 = obj5;
                                    if (log.isDebugEnabled()) {
                                    }
                                    awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                                    awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                                    amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                                    if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                                    }
                                } catch (Error e25) {
                                    e = e25;
                                    throw ((Error) handleUnexpectedFailure(e, awsRequestMetrics));
                                } catch (RuntimeException e26) {
                                    e = e26;
                                    throw ((RuntimeException) handleUnexpectedFailure(e, awsRequestMetrics));
                                }
                            }
                            if (!z && httpResponse != null) {
                                try {
                                    if (httpResponse.getRawContent() != null) {
                                        httpResponse.getRawContent().close();
                                    }
                                } catch (IOException e27) {
                                    log.warn(obj7, e27);
                                }
                            }
                            httpResponse2 = httpResponse;
                            signer3 = signer;
                            httpRequest2 = httpRequest;
                            j2 = j;
                            r9 = executionContext;
                            i2 = i;
                            linkedHashMap2 = linkedHashMap;
                            hashMap2 = hashMap;
                        }
                    } catch (Throwable th9) {
                        httpRequest = createHttpRequest;
                        signer = signer2;
                        obj5 = "Cannot close the response content.";
                        j = j4;
                        i = i3;
                        try {
                            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.HttpRequestTime);
                            throw th9;
                            break;
                        } catch (IOException e28) {
                            e = e28;
                            httpResponse = httpResponse2;
                            httpRequest3 = httpRequest;
                            obj3 = obj5;
                            if (log.isDebugEnabled()) {
                            }
                            awsRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, e);
                            awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, (Object) null);
                            amazonClientException = new AmazonClientException("Unable to execute HTTP request: " + e.getMessage(), e);
                            if (shouldRetry(request.getOriginalRequest(), httpRequest3.getContent(), amazonClientException, i, this.config.getRetryPolicy())) {
                            }
                        } catch (Error e29) {
                            e = e29;
                            throw ((Error) handleUnexpectedFailure(e, awsRequestMetrics));
                        } catch (RuntimeException e30) {
                            e = e30;
                            throw ((RuntimeException) handleUnexpectedFailure(e, awsRequestMetrics));
                        }
                    }
                } catch (Throwable th10) {
                    th = th10;
                    r9 = "Cannot close the response content.";
                }
            } catch (Throwable th11) {
                th = th11;
            }
        }
    }

    private <T extends Throwable> T handleUnexpectedFailure(T t, AWSRequestMetrics aWSRequestMetrics) {
        aWSRequestMetrics.incrementCounter(AWSRequestMetrics.Field.Exception);
        aWSRequestMetrics.addProperty(AWSRequestMetrics.Field.Exception, t);
        return t;
    }

    void resetRequestAfterError(Request<?> request, Exception exc) {
        if (request.getContent() == null) {
            return;
        }
        if (!request.getContent().markSupported()) {
            throw new AmazonClientException("Encountered an exception and stream is not resettable", exc);
        }
        try {
            request.getContent().reset();
        } catch (IOException unused) {
            throw new AmazonClientException("Encountered an exception and couldn't reset the stream to retry", exc);
        }
    }

    void setUserAgent(Request<?> request) {
        RequestClientOptions requestClientOptions;
        String clientMarker;
        String str = ClientConfiguration.DEFAULT_USER_AGENT;
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        if (originalRequest != null && (requestClientOptions = originalRequest.getRequestClientOptions()) != null && (clientMarker = requestClientOptions.getClientMarker(RequestClientOptions.Marker.USER_AGENT)) != null) {
            str = createUserAgentString(str, clientMarker);
        }
        if (!ClientConfiguration.DEFAULT_USER_AGENT.equals(this.config.getUserAgent())) {
            str = createUserAgentString(str, this.config.getUserAgent());
        }
        if (this.config.getUserAgentOverride() != null) {
            str = this.config.getUserAgentOverride();
        }
        request.addHeader("User-Agent", str);
    }

    static String createUserAgentString(String str, String str2) {
        if (str.contains(str2)) {
            return str;
        }
        return str.trim() + " " + str2.trim();
    }

    public void shutdown() {
        this.httpClient.shutdown();
    }

    private boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, InputStream inputStream, AmazonClientException amazonClientException, int i, RetryPolicy retryPolicy) {
        int i2 = i - 1;
        int maxErrorRetry = this.config.getMaxErrorRetry();
        if (maxErrorRetry < 0 || !retryPolicy.isMaxErrorRetryInClientConfigHonored()) {
            maxErrorRetry = retryPolicy.getMaxErrorRetry();
        }
        if (i2 >= maxErrorRetry) {
            return false;
        }
        if (inputStream != null && !inputStream.markSupported()) {
            if (log.isDebugEnabled()) {
                log.debug("Content not repeatable");
            }
            return false;
        }
        return retryPolicy.getRetryCondition().shouldRetry(amazonWebServiceRequest, amazonClientException, i2);
    }

    private static boolean isTemporaryRedirect(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusCode();
        String str = httpResponse.getHeaders().get("Location");
        return (statusCode != 307 || str == null || str.isEmpty()) ? false : true;
    }

    private boolean isRequestSuccessful(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusCode();
        return statusCode >= 200 && statusCode < 300;
    }

    <T> T handleResponse(Request<?> request, HttpResponseHandler<AmazonWebServiceResponse<T>> httpResponseHandler, HttpResponse httpResponse, ExecutionContext executionContext) throws IOException {
        try {
            AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
            awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ResponseProcessingTime);
            try {
                AmazonWebServiceResponse<T> handle = httpResponseHandler.handle(httpResponse);
                if (handle == null) {
                    throw new RuntimeException("Unable to unmarshall response metadata. Response Code: " + httpResponse.getStatusCode() + ", Response Text: " + httpResponse.getStatusText());
                }
                if (REQUEST_LOG.isDebugEnabled()) {
                    REQUEST_LOG.debug("Received successful response: " + httpResponse.getStatusCode() + ", AWS Request ID: " + handle.getRequestId());
                }
                awsRequestMetrics.addProperty(AWSRequestMetrics.Field.AWSRequestID, handle.getRequestId());
                return handle.getResult();
            } finally {
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ResponseProcessingTime);
            }
        } catch (CRC32MismatchException e) {
            throw e;
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new AmazonClientException("Unable to unmarshall response (" + e3.getMessage() + "). Response Code: " + httpResponse.getStatusCode() + ", Response Text: " + httpResponse.getStatusText(), e3);
        }
    }

    AmazonServiceException handleErrorResponse(Request<?> request, HttpResponseHandler<AmazonServiceException> httpResponseHandler, HttpResponse httpResponse) throws IOException {
        AmazonServiceException amazonServiceException;
        int statusCode = httpResponse.getStatusCode();
        try {
            amazonServiceException = httpResponseHandler.handle(httpResponse);
            REQUEST_LOG.debug("Received error response: " + amazonServiceException.toString());
        } catch (Exception e) {
            if (statusCode == 413) {
                amazonServiceException = new AmazonServiceException("Request entity too large");
                amazonServiceException.setServiceName(request.getServiceName());
                amazonServiceException.setStatusCode(413);
                amazonServiceException.setErrorType(AmazonServiceException.ErrorType.Client);
                amazonServiceException.setErrorCode("Request entity too large");
            } else if (statusCode == 503 && "Service Unavailable".equalsIgnoreCase(httpResponse.getStatusText())) {
                amazonServiceException = new AmazonServiceException("Service unavailable");
                amazonServiceException.setServiceName(request.getServiceName());
                amazonServiceException.setStatusCode(503);
                amazonServiceException.setErrorType(AmazonServiceException.ErrorType.Service);
                amazonServiceException.setErrorCode("Service unavailable");
            } else {
                if (e instanceof IOException) {
                    throw ((IOException) e);
                }
                throw new AmazonClientException("Unable to unmarshall error response (" + e.getMessage() + "). Response Code: " + statusCode + ", Response Text: " + httpResponse.getStatusText() + ", Response Headers: " + httpResponse.getHeaders(), e);
            }
        }
        amazonServiceException.setStatusCode(statusCode);
        amazonServiceException.setServiceName(request.getServiceName());
        amazonServiceException.fillInStackTrace();
        return amazonServiceException;
    }

    private long pauseBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i, RetryPolicy retryPolicy) {
        int i2 = (i - 1) - 1;
        long delayBeforeNextRetry = retryPolicy.getBackoffStrategy().delayBeforeNextRetry(amazonWebServiceRequest, amazonClientException, i2);
        if (log.isDebugEnabled()) {
            log.debug("Retriable error detected, will retry in " + delayBeforeNextRetry + "ms, attempt number: " + i2);
        }
        try {
            Thread.sleep(delayBeforeNextRetry);
            return delayBeforeNextRetry;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AmazonClientException(e.getMessage(), e);
        }
    }

    private String getServerDateFromException(String str) {
        int indexOf;
        int indexOf2 = str.indexOf("(");
        if (str.contains(" + 15")) {
            indexOf = str.indexOf(" + 15");
        } else {
            indexOf = str.indexOf(" - 15");
        }
        return str.substring(indexOf2 + 1, indexOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v4 */
    long parseClockSkewOffset(HttpResponse httpResponse, AmazonServiceException amazonServiceException) {
        Date parseRFC822Date;
        Date date = new Date();
        String str = httpResponse.getHeaders().get("Date");
        String str2 = null;
        try {
            if (str != 0) {
                try {
                    if (!str.isEmpty()) {
                        parseRFC822Date = DateUtils.parseRFC822Date(str);
                        long time = date.getTime() - parseRFC822Date.getTime();
                        str = 1000;
                        return time / 1000;
                    }
                } catch (RuntimeException e) {
                    e = e;
                    log.warn("Unable to parse clock skew offset from response: " + str2, e);
                    return 0L;
                }
            }
            parseRFC822Date = DateUtils.parseCompressedISO8601Date(getServerDateFromException(amazonServiceException.getMessage()));
            long time2 = date.getTime() - parseRFC822Date.getTime();
            str = 1000;
            return time2 / 1000;
        } catch (RuntimeException e2) {
            e = e2;
            str2 = str;
        }
    }

    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }

    public RequestMetricCollector getRequestMetricCollector() {
        return this.requestMetricCollector;
    }
}
