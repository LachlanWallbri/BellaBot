package com.google.api.client.http;

import com.google.api.client.util.LoggingStreamingContent;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.StringUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.opencensus.common.Scope;
import io.opencensus.contrib.http.util.HttpTraceAttributeConstants;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class HttpRequest {
    public static final int DEFAULT_NUMBER_OF_RETRIES = 10;

    @Deprecated
    private BackOffPolicy backOffPolicy;
    private HttpContent content;
    private HttpEncoding encoding;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpIOExceptionHandler ioExceptionHandler;
    private ObjectParser objectParser;
    private String requestMethod;
    private HttpResponseInterceptor responseInterceptor;
    private boolean suppressUserAgentSuffix;
    private final HttpTransport transport;
    private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    private GenericUrl url;
    public static final String VERSION = getVersion();
    public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/" + VERSION + " (gzip)";
    private HttpHeaders headers = new HttpHeaders();
    private HttpHeaders responseHeaders = new HttpHeaders();
    private int numRetries = 10;
    private int contentLoggingLimit = 16384;
    private boolean loggingEnabled = true;
    private boolean curlLoggingEnabled = true;
    private int connectTimeout = 20000;
    private int readTimeout = 20000;
    private int writeTimeout = 0;
    private boolean followRedirects = true;
    private boolean useRawRedirectUrls = false;
    private boolean throwExceptionOnExecuteError = true;

    @Deprecated
    private boolean retryOnExecuteIOException = false;
    private Sleeper sleeper = Sleeper.DEFAULT;
    private final Tracer tracer = OpenCensusUtils.getTracer();
    private boolean responseReturnRawInputStream = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequest(HttpTransport httpTransport, String str) {
        this.transport = httpTransport;
        setRequestMethod(str);
    }

    public HttpTransport getTransport() {
        return this.transport;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public HttpRequest setRequestMethod(String str) {
        Preconditions.checkArgument(str == null || HttpMediaType.matchesToken(str));
        this.requestMethod = str;
        return this;
    }

    public GenericUrl getUrl() {
        return this.url;
    }

    public HttpRequest setUrl(GenericUrl genericUrl) {
        this.url = (GenericUrl) Preconditions.checkNotNull(genericUrl);
        return this;
    }

    public HttpContent getContent() {
        return this.content;
    }

    public HttpRequest setContent(HttpContent httpContent) {
        this.content = httpContent;
        return this;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }

    public HttpRequest setEncoding(HttpEncoding httpEncoding) {
        this.encoding = httpEncoding;
        return this;
    }

    @Deprecated
    public BackOffPolicy getBackOffPolicy() {
        return this.backOffPolicy;
    }

    @Deprecated
    public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
        this.backOffPolicy = backOffPolicy;
        return this;
    }

    public int getContentLoggingLimit() {
        return this.contentLoggingLimit;
    }

    public HttpRequest setContentLoggingLimit(int i) {
        Preconditions.checkArgument(i >= 0, "The content logging limit must be non-negative.");
        this.contentLoggingLimit = i;
        return this;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public HttpRequest setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        return this;
    }

    public boolean isCurlLoggingEnabled() {
        return this.curlLoggingEnabled;
    }

    public HttpRequest setCurlLoggingEnabled(boolean z) {
        this.curlLoggingEnabled = z;
        return this;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public HttpRequest setConnectTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.connectTimeout = i;
        return this;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public HttpRequest setReadTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.readTimeout = i;
        return this;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public HttpRequest setWriteTimeout(int i) {
        Preconditions.checkArgument(i >= 0);
        this.writeTimeout = i;
        return this;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public HttpRequest setHeaders(HttpHeaders httpHeaders) {
        this.headers = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpRequest setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
        return this;
    }

    public HttpExecuteInterceptor getInterceptor() {
        return this.executeInterceptor;
    }

    public HttpRequest setInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
        this.executeInterceptor = httpExecuteInterceptor;
        return this;
    }

    public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
        return this.unsuccessfulResponseHandler;
    }

    public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler httpUnsuccessfulResponseHandler) {
        this.unsuccessfulResponseHandler = httpUnsuccessfulResponseHandler;
        return this;
    }

    public HttpIOExceptionHandler getIOExceptionHandler() {
        return this.ioExceptionHandler;
    }

    public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler httpIOExceptionHandler) {
        this.ioExceptionHandler = httpIOExceptionHandler;
        return this;
    }

    public HttpResponseInterceptor getResponseInterceptor() {
        return this.responseInterceptor;
    }

    public HttpRequest setResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        this.responseInterceptor = httpResponseInterceptor;
        return this;
    }

    public int getNumberOfRetries() {
        return this.numRetries;
    }

    public HttpRequest setNumberOfRetries(int i) {
        Preconditions.checkArgument(i >= 0);
        this.numRetries = i;
        return this;
    }

    public HttpRequest setParser(ObjectParser objectParser) {
        this.objectParser = objectParser;
        return this;
    }

    public final ObjectParser getParser() {
        return this.objectParser;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public HttpRequest setFollowRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    public boolean getUseRawRedirectUrls() {
        return this.useRawRedirectUrls;
    }

    public HttpRequest setUseRawRedirectUrls(boolean z) {
        this.useRawRedirectUrls = z;
        return this;
    }

    public boolean getThrowExceptionOnExecuteError() {
        return this.throwExceptionOnExecuteError;
    }

    public HttpRequest setThrowExceptionOnExecuteError(boolean z) {
        this.throwExceptionOnExecuteError = z;
        return this;
    }

    @Deprecated
    public boolean getRetryOnExecuteIOException() {
        return this.retryOnExecuteIOException;
    }

    @Deprecated
    public HttpRequest setRetryOnExecuteIOException(boolean z) {
        this.retryOnExecuteIOException = z;
        return this;
    }

    public boolean getSuppressUserAgentSuffix() {
        return this.suppressUserAgentSuffix;
    }

    public HttpRequest setSuppressUserAgentSuffix(boolean z) {
        this.suppressUserAgentSuffix = z;
        return this;
    }

    public boolean getResponseReturnRawInputStream() {
        return this.responseReturnRawInputStream;
    }

    public HttpRequest setResponseReturnRawInputStream(boolean z) {
        this.responseReturnRawInputStream = z;
        return this;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(32:8|(1:10)|11|(1:13)|14|(1:181)(1:18)|(2:20|(24:22|(1:24)|25|(2:27|(1:29)(1:30))|31|(1:33)|34|(1:178)(1:38)|39|(7:41|(1:43)(1:176)|44|(1:46)(1:175)|(5:48|(2:50|(1:52))(1:173)|(2:54|(1:56))|57|(1:59))(1:174)|(1:61)|62)(1:177)|(2:64|(3:66|(1:68)|69))|(1:172)(1:72)|73|74|75|(1:77)|78|79|80|(3:112|113|(7:115|(1:117)(1:137)|(3:119|(1:(3:127|128|(2:130|131)))|121)|134|(1:136)|85|(4:88|(1:90)(1:110)|91|(4:93|(1:95)|96|(1:108)(3:100|101|102))(1:109))(1:87)))|(1:83)(1:111)|84|85|(0)(0)))(1:180)|179|25|(0)|31|(0)|34|(1:36)|178|39|(0)(0)|(0)|(0)|172|73|74|75|(0)|78|79|80|(0)|(0)(0)|84|85|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x029a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x029e, code lost:
    
        if (r1.retryOnExecuteIOException == false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x02b7, code lost:
    
        if (r9 != false) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02b9, code lost:
    
        r8.log(java.util.logging.Level.WARNING, "exception thrown while executing request", (java.lang.Throwable) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02c0, code lost:
    
        r4.close();
        r16 = r0;
        r4 = null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x026a A[Catch: all -> 0x0297, IOException -> 0x029a, TRY_LEAVE, TryCatch #0 {IOException -> 0x029a, blocks: (B:75:0x0264, B:77:0x026a, B:145:0x028d, B:147:0x0293, B:148:0x0296), top: B:74:0x0264, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x035c A[LOOP:0: B:8:0x0035->B:87:0x035c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0328 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v4, types: [com.google.api.client.util.LoggingStreamingContent] */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.api.client.http.HttpEncodingStreamingContent] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HttpResponse execute() throws IOException {
        StringBuilder sb;
        StringBuilder sb2;
        HttpContent httpContent;
        Span span;
        int i;
        Span span2;
        Scope withSpan;
        HttpResponse httpResponse;
        boolean z;
        LowLevelHttpResponse execute;
        String name;
        long j;
        HttpRequest httpRequest = this;
        Preconditions.checkArgument(httpRequest.numRetries >= 0);
        int i2 = httpRequest.numRetries;
        BackOffPolicy backOffPolicy = httpRequest.backOffPolicy;
        if (backOffPolicy != null) {
            backOffPolicy.reset();
        }
        Preconditions.checkNotNull(httpRequest.requestMethod);
        Preconditions.checkNotNull(httpRequest.url);
        Span startSpan = httpRequest.tracer.spanBuilder(OpenCensusUtils.SPAN_NAME_HTTP_REQUEST_EXECUTE).setRecordEvents(OpenCensusUtils.isRecordEvent()).startSpan();
        int i3 = i2;
        HttpResponse httpResponse2 = null;
        while (true) {
            startSpan.addAnnotation("retry #" + (httpRequest.numRetries - i3));
            if (httpResponse2 != null) {
                httpResponse2.ignore();
            }
            HttpExecuteInterceptor httpExecuteInterceptor = httpRequest.executeInterceptor;
            if (httpExecuteInterceptor != null) {
                httpExecuteInterceptor.intercept(httpRequest);
            }
            String build = httpRequest.url.build();
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_METHOD, httpRequest.requestMethod);
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_HOST, httpRequest.url.getHost());
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_PATH, httpRequest.url.getRawPath());
            addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_URL, build);
            LowLevelHttpRequest buildRequest = httpRequest.transport.buildRequest(httpRequest.requestMethod, build);
            Logger logger = HttpTransport.LOGGER;
            boolean z2 = httpRequest.loggingEnabled && logger.isLoggable(Level.CONFIG);
            try {
                try {
                    if (z2) {
                        sb = new StringBuilder();
                        sb.append("-------------- REQUEST  --------------");
                        sb.append(StringUtils.LINE_SEPARATOR);
                        sb.append(httpRequest.requestMethod);
                        sb.append(' ');
                        sb.append(build);
                        sb.append(StringUtils.LINE_SEPARATOR);
                        if (httpRequest.curlLoggingEnabled) {
                            sb2 = new StringBuilder("curl -v --compressed");
                            if (!httpRequest.requestMethod.equals("GET")) {
                                sb2.append(" -X ");
                                sb2.append(httpRequest.requestMethod);
                            }
                            String userAgent = httpRequest.headers.getUserAgent();
                            if (!httpRequest.suppressUserAgentSuffix) {
                                if (userAgent == null) {
                                    httpRequest.headers.setUserAgent(USER_AGENT_SUFFIX);
                                    addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_USER_AGENT, USER_AGENT_SUFFIX);
                                } else {
                                    String str = userAgent + " " + USER_AGENT_SUFFIX;
                                    httpRequest.headers.setUserAgent(str);
                                    addSpanAttribute(startSpan, HttpTraceAttributeConstants.HTTP_USER_AGENT, str);
                                }
                            }
                            OpenCensusUtils.propagateTracingContext(startSpan, httpRequest.headers);
                            HttpHeaders.serializeHeaders(httpRequest.headers, sb, sb2, logger, buildRequest);
                            if (!httpRequest.suppressUserAgentSuffix) {
                                httpRequest.headers.setUserAgent(userAgent);
                            }
                            httpContent = httpRequest.content;
                            boolean z3 = httpContent != null || httpContent.retrySupported();
                            if (httpContent == null) {
                                String type = httpRequest.content.getType();
                                if (z2) {
                                    span = startSpan;
                                    httpContent = new LoggingStreamingContent(httpContent, HttpTransport.LOGGER, Level.CONFIG, httpRequest.contentLoggingLimit);
                                } else {
                                    span = startSpan;
                                }
                                HttpEncoding httpEncoding = httpRequest.encoding;
                                if (httpEncoding == null) {
                                    j = httpRequest.content.getLength();
                                    name = null;
                                } else {
                                    name = httpEncoding.getName();
                                    httpContent = new HttpEncodingStreamingContent(httpContent, httpRequest.encoding);
                                    j = -1;
                                }
                                if (z2) {
                                    if (type != null) {
                                        StringBuilder sb3 = new StringBuilder();
                                        i = i3;
                                        sb3.append("Content-Type: ");
                                        sb3.append(type);
                                        String sb4 = sb3.toString();
                                        sb.append(sb4);
                                        sb.append(StringUtils.LINE_SEPARATOR);
                                        if (sb2 != null) {
                                            sb2.append(" -H '" + sb4 + "'");
                                        }
                                    } else {
                                        i = i3;
                                    }
                                    if (name != null) {
                                        String str2 = "Content-Encoding: " + name;
                                        sb.append(str2);
                                        sb.append(StringUtils.LINE_SEPARATOR);
                                        if (sb2 != null) {
                                            sb2.append(" -H '" + str2 + "'");
                                        }
                                    }
                                    if (j >= 0) {
                                        sb.append("Content-Length: " + j);
                                        sb.append(StringUtils.LINE_SEPARATOR);
                                    }
                                } else {
                                    i = i3;
                                }
                                if (sb2 != null) {
                                    sb2.append(" -d '@-'");
                                }
                                buildRequest.setContentType(type);
                                buildRequest.setContentEncoding(name);
                                buildRequest.setContentLength(j);
                                buildRequest.setStreamingContent(httpContent);
                            } else {
                                span = startSpan;
                                i = i3;
                            }
                            if (z2) {
                                logger.config(sb.toString());
                                if (sb2 != null) {
                                    sb2.append(" -- '");
                                    sb2.append(build.replaceAll("'", "'\"'\"'"));
                                    sb2.append("'");
                                    if (httpContent != null) {
                                        sb2.append(" << $$$");
                                    }
                                    logger.config(sb2.toString());
                                }
                            }
                            boolean z4 = !z3 && i > 0;
                            httpRequest = this;
                            buildRequest.setTimeout(httpRequest.connectTimeout, httpRequest.readTimeout);
                            buildRequest.setWriteTimeout(httpRequest.writeTimeout);
                            span2 = span;
                            withSpan = httpRequest.tracer.withSpan(span2);
                            OpenCensusUtils.recordSentMessageEvent(span2, buildRequest.getContentLength());
                            execute = buildRequest.execute();
                            if (execute != null) {
                                OpenCensusUtils.recordReceivedMessageEvent(span2, execute.getContentLength());
                                span2.putAttribute(HttpTraceAttributeConstants.HTTP_STATUS_CODE, AttributeValue.longAttributeValue(execute.getStatusCode()));
                            }
                            HttpResponse httpResponse3 = new HttpResponse(httpRequest, execute);
                            withSpan.close();
                            httpResponse = httpResponse3;
                            Integer num = null;
                            IOException iOException = null;
                            if (httpResponse != null) {
                                try {
                                    if (!httpResponse.isSuccessStatusCode()) {
                                        boolean handleResponse = httpRequest.unsuccessfulResponseHandler != null ? httpRequest.unsuccessfulResponseHandler.handleResponse(httpRequest, httpResponse, z4) : false;
                                        if (!handleResponse) {
                                            if (!httpRequest.handleRedirect(httpResponse.getStatusCode(), httpResponse.getHeaders())) {
                                                if (z4 && httpRequest.backOffPolicy != null && httpRequest.backOffPolicy.isBackOffRequired(httpResponse.getStatusCode())) {
                                                    long nextBackOffMillis = httpRequest.backOffPolicy.getNextBackOffMillis();
                                                    if (nextBackOffMillis != -1) {
                                                        try {
                                                            httpRequest.sleeper.sleep(nextBackOffMillis);
                                                        } catch (InterruptedException unused) {
                                                        }
                                                    }
                                                }
                                            }
                                            handleResponse = true;
                                        }
                                        z = handleResponse & z4;
                                        if (z) {
                                            httpResponse.ignore();
                                        }
                                        int i4 = i - 1;
                                        if (!z) {
                                            span2.end(OpenCensusUtils.getEndSpanOptions(httpResponse == null ? num : Integer.valueOf(httpResponse.getStatusCode())));
                                            if (httpResponse == null) {
                                                throw iOException;
                                            }
                                            HttpResponseInterceptor httpResponseInterceptor = httpRequest.responseInterceptor;
                                            if (httpResponseInterceptor != null) {
                                                httpResponseInterceptor.interceptResponse(httpResponse);
                                            }
                                            if (!httpRequest.throwExceptionOnExecuteError || httpResponse.isSuccessStatusCode()) {
                                                return httpResponse;
                                            }
                                            try {
                                                throw new HttpResponseException(httpResponse);
                                            } finally {
                                                httpResponse.disconnect();
                                            }
                                        }
                                        i3 = i4;
                                        httpResponse2 = httpResponse;
                                        startSpan = span2;
                                    }
                                } catch (Throwable th) {
                                    if (httpResponse != null) {
                                    }
                                    throw th;
                                }
                            }
                            z = (httpResponse != null) & z4;
                            int i42 = i - 1;
                            if (!z) {
                            }
                        }
                    } else {
                        sb = null;
                    }
                    HttpResponse httpResponse32 = new HttpResponse(httpRequest, execute);
                    withSpan.close();
                    httpResponse = httpResponse32;
                    Integer num2 = null;
                    IOException iOException2 = null;
                    if (httpResponse != null) {
                    }
                    z = (httpResponse != null) & z4;
                    int i422 = i - 1;
                    if (!z) {
                    }
                } catch (Throwable th2) {
                    InputStream content = execute.getContent();
                    if (content != null) {
                        content.close();
                    }
                    throw th2;
                    break;
                }
                execute = buildRequest.execute();
                if (execute != null) {
                }
            } catch (Throwable th3) {
                withSpan.close();
                throw th3;
            }
            sb2 = null;
            String userAgent2 = httpRequest.headers.getUserAgent();
            if (!httpRequest.suppressUserAgentSuffix) {
            }
            OpenCensusUtils.propagateTracingContext(startSpan, httpRequest.headers);
            HttpHeaders.serializeHeaders(httpRequest.headers, sb, sb2, logger, buildRequest);
            if (!httpRequest.suppressUserAgentSuffix) {
            }
            httpContent = httpRequest.content;
            if (httpContent != null) {
            }
            if (httpContent == null) {
            }
            if (z2) {
            }
            if (z3) {
            }
            httpRequest = this;
            buildRequest.setTimeout(httpRequest.connectTimeout, httpRequest.readTimeout);
            buildRequest.setWriteTimeout(httpRequest.writeTimeout);
            span2 = span;
            withSpan = httpRequest.tracer.withSpan(span2);
            OpenCensusUtils.recordSentMessageEvent(span2, buildRequest.getContentLength());
        }
        span2.end(OpenCensusUtils.getEndSpanOptions(null));
        throw e;
    }

    public Future<HttpResponse> executeAsync(Executor executor) {
        FutureTask futureTask = new FutureTask(new Callable<HttpResponse>() { // from class: com.google.api.client.http.HttpRequest.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public HttpResponse call() throws Exception {
                return HttpRequest.this.execute();
            }
        });
        executor.execute(futureTask);
        return futureTask;
    }

    public Future<HttpResponse> executeAsync() {
        return executeAsync(Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).build()));
    }

    public boolean handleRedirect(int i, HttpHeaders httpHeaders) {
        String location = httpHeaders.getLocation();
        if (!getFollowRedirects() || !HttpStatusCodes.isRedirect(i) || location == null) {
            return false;
        }
        setUrl(new GenericUrl(this.url.toURL(location), this.useRawRedirectUrls));
        if (i == 303) {
            setRequestMethod("GET");
            setContent(null);
        }
        String str = (String) null;
        this.headers.setAuthorization(str);
        this.headers.setIfMatch(str);
        this.headers.setIfNoneMatch(str);
        this.headers.setIfModifiedSince(str);
        this.headers.setIfUnmodifiedSince(str);
        this.headers.setIfRange(str);
        return true;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public HttpRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }

    private static void addSpanAttribute(Span span, String str, String str2) {
        if (str2 != null) {
            span.putAttribute(str, AttributeValue.stringAttributeValue(str2));
        }
    }

    private static String getVersion() {
        String str = "unknown-version";
        try {
            InputStream resourceAsStream = HttpRequest.class.getResourceAsStream("/com/google/api/client/http/google-http-client.properties");
            if (resourceAsStream != null) {
                try {
                    Properties properties = new Properties();
                    properties.load(resourceAsStream);
                    str = properties.getProperty("google-http-client.version");
                } finally {
                }
            }
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        } catch (IOException unused) {
        }
        return str;
    }
}
