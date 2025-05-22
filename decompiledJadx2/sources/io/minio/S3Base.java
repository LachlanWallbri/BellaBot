package io.minio;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.util.Mimetypes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.iflytek.speech.VoiceWakeuperAidl;
import io.minio.credentials.Credentials;
import io.minio.credentials.Provider;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.HttpUtils;
import io.minio.http.Method;
import io.minio.messages.CompleteMultipartUpload;
import io.minio.messages.CompleteMultipartUploadOutput;
import io.minio.messages.CopyPartResult;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteMarker;
import io.minio.messages.DeleteObject;
import io.minio.messages.DeleteRequest;
import io.minio.messages.DeleteResult;
import io.minio.messages.ErrorResponse;
import io.minio.messages.InitiateMultipartUploadResult;
import io.minio.messages.Item;
import io.minio.messages.ListBucketResultV1;
import io.minio.messages.ListBucketResultV2;
import io.minio.messages.ListMultipartUploadsResult;
import io.minio.messages.ListObjectsResult;
import io.minio.messages.ListPartsResult;
import io.minio.messages.ListVersionsResult;
import io.minio.messages.LocationConstraint;
import io.minio.messages.NotificationRecords;
import io.minio.messages.Part;
import io.minio.messages.Prefix;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes7.dex */
public abstract class S3Base {
    protected static final long DEFAULT_CONNECTION_TIMEOUT;
    private static final String END_HTTP = "----------END-HTTP----------";
    protected static final int MAX_BUCKET_POLICY_SIZE = 20480;
    protected static final String NO_SUCH_BUCKET = "NoSuchBucket";
    protected static final String NO_SUCH_BUCKET_MESSAGE = "Bucket does not exist";
    protected static final String NO_SUCH_BUCKET_POLICY = "NoSuchBucketPolicy";
    protected static final String NO_SUCH_OBJECT_LOCK_CONFIGURATION = "NoSuchObjectLockConfiguration";
    private static final String RETRY_HEAD = "RetryHead";
    protected static final String SERVER_SIDE_ENCRYPTION_CONFIGURATION_NOT_FOUND_ERROR = "ServerSideEncryptionConfigurationNotFoundError";
    private static final Set<String> TRACE_QUERY_PARAMS;
    private static final String UPLOAD_ID = "uploadId";
    protected static final String US_EAST_1 = "us-east-1";
    protected HttpUrl baseUrl;
    private OkHttpClient httpClient;
    private boolean isAcceleratedHost;
    private boolean isAwsHost;
    private boolean isDualStackHost;
    protected Provider provider;
    protected String region;
    private PrintWriter traceStream;
    private boolean useVirtualStyle;
    protected final Map<String, String> regionCache = new ConcurrentHashMap();
    private String userAgent = MinioProperties.INSTANCE.getDefaultUserAgent();

    static {
        try {
            RequestBody.create(new byte[0], (MediaType) null);
            DEFAULT_CONNECTION_TIMEOUT = TimeUnit.MINUTES.toMillis(5L);
            TRACE_QUERY_PARAMS = ImmutableSet.m680of("retention", "legal-hold", "tagging", "uploadId");
        } catch (NoSuchMethodError e) {
            throw new RuntimeException("Unsupported OkHttp library found. Must use okhttp >= 4.8.1", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public S3Base(HttpUrl httpUrl, String str, boolean z, boolean z2, boolean z3, boolean z4, Provider provider, OkHttpClient okHttpClient) {
        this.baseUrl = httpUrl;
        this.region = str;
        this.isAwsHost = z;
        this.isAcceleratedHost = z2;
        this.isDualStackHost = z3;
        this.useVirtualStyle = z4;
        this.provider = provider;
        this.httpClient = okHttpClient;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public S3Base(S3Base s3Base) {
        this.baseUrl = s3Base.baseUrl;
        this.region = s3Base.region;
        this.isAwsHost = s3Base.isAwsHost;
        this.isAcceleratedHost = s3Base.isAcceleratedHost;
        this.isDualStackHost = s3Base.isDualStackHost;
        this.useVirtualStyle = s3Base.useVirtualStyle;
        this.provider = s3Base.provider;
        this.httpClient = s3Base.httpClient;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkArgs(BaseArgs baseArgs) {
        if (baseArgs == null) {
            throw new IllegalArgumentException("null arguments");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Multimap<String, String> merge(Multimap<String, String> multimap, Multimap<String, String> multimap2) {
        HashMultimap create = HashMultimap.create();
        if (multimap != null) {
            create.putAll(multimap);
        }
        if (multimap2 != null) {
            create.putAll(multimap2);
        }
        return create;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Multimap<String, String> newMultimap(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating keys and values");
        }
        HashMultimap create = HashMultimap.create();
        for (int i = 0; i < strArr.length; i += 2) {
            create.put(strArr[i], strArr[i + 1]);
        }
        return create;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Multimap<String, String> newMultimap(Map<String, String> map) {
        return map != null ? Multimaps.forMap(map) : HashMultimap.create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Multimap<String, String> newMultimap(Multimap<String, String> multimap) {
        return multimap != null ? HashMultimap.create(multimap) : HashMultimap.create();
    }

    private String[] handleRedirectResponse(Method method, String str, Response response, boolean z) {
        String str2;
        String str3;
        String str4 = null;
        if (response.code() == 301) {
            str2 = "PermanentRedirect";
            str3 = "Moved Permanently";
        } else if (response.code() == 307) {
            str2 = "Redirect";
            str3 = "Temporary redirect";
        } else if (response.code() == 400) {
            str2 = "BadRequest";
            str3 = "Bad request";
        } else {
            str2 = null;
            str3 = null;
        }
        String str5 = response.headers().get(Headers.S3_BUCKET_REGION);
        if (str3 != null && str5 != null) {
            str3 = str3 + ". Use region " + str5;
        }
        if (!z || str5 == null || !method.equals(Method.HEAD) || str == null || this.regionCache.get(str) == null) {
            str4 = str3;
        } else {
            str2 = RETRY_HEAD;
        }
        return new String[]{str2, str4};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpUrl buildUrl(Method method, String str, String str2, String str3, Multimap<String, String> multimap) throws NoSuchAlgorithmException {
        if (str == null && str2 != null) {
            throw new IllegalArgumentException("null bucket name for object '" + str2 + "'");
        }
        HttpUrl.Builder newBuilder = this.baseUrl.newBuilder();
        String host = this.baseUrl.host();
        String str4 = "s3.";
        if (str != null) {
            boolean z = true;
            if ((method != Method.PUT || str2 != null || multimap != null) && ((multimap == null || !multimap.containsKey(RequestParameters.SUBRESOURCE_LOCATION)) && (!str.contains(".") || !this.baseUrl.isHttps()))) {
                z = false;
            }
            if (this.isAwsHost) {
                if (this.isAcceleratedHost) {
                    if (str.contains(".")) {
                        throw new IllegalArgumentException("bucket name '" + str + "' with '.' is not allowed for accelerated endpoint");
                    }
                    if (!z) {
                        str4 = "s3-accelerate.";
                    }
                }
                String str5 = str4 + (this.isDualStackHost ? "dualstack." : "");
                if (z || !this.isAcceleratedHost) {
                    str5 = str5 + str3 + ".";
                }
                host = str5 + host;
            }
            if (z || !this.useVirtualStyle) {
                newBuilder.host(host);
                newBuilder.addEncodedPathSegment(S3Escaper.encode(str));
            } else {
                newBuilder.host(str + "." + host);
            }
            if (str2 != null) {
                for (String str6 : str2.split("/")) {
                    if (str6.equals(".") || str6.equals("..")) {
                        throw new IllegalArgumentException("object name with '.' or '..' path segment is not supported");
                    }
                }
                newBuilder.addEncodedPathSegments(S3Escaper.encodePath(str2));
            }
        } else if (this.isAwsHost) {
            newBuilder.host("s3." + str3 + "." + host);
        }
        if (multimap != null) {
            for (Map.Entry<String, String> entry : multimap.entries()) {
                newBuilder.addEncodedQueryParameter(S3Escaper.encode(entry.getKey()), S3Escaper.encode(entry.getValue()));
            }
        }
        return newBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public okhttp3.Headers httpHeaders(Multimap<String, String> multimap) {
        Headers.Builder builder = new Headers.Builder();
        if (multimap == null) {
            return builder.build();
        }
        if (multimap.containsKey("Content-Encoding")) {
            builder.add("Content-Encoding", (String) multimap.get("Content-Encoding").stream().distinct().filter(new Predicate() { // from class: io.minio.-$$Lambda$S3Base$9gYh4h4HDNijQS6plV_sJBUEyTQ
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return S3Base.lambda$httpHeaders$0((String) obj);
                }
            }).collect(Collectors.joining(",")));
        }
        for (Map.Entry<String, String> entry : multimap.entries()) {
            if (!entry.getKey().equals("Content-Encoding")) {
                builder.addUnsafeNonAscii(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$httpHeaders$0(String str) {
        return !str.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Request createRequest(HttpUrl httpUrl, Method method, okhttp3.Headers headers, Object obj, int i, Credentials credentials) throws InsufficientDataException, InternalException, IOException, NoSuchAlgorithmException {
        String str;
        String str2;
        HttpRequestBody httpRequestBody;
        Request.Builder builder = new Request.Builder();
        builder.url(httpUrl);
        if (headers != null) {
            builder.headers(headers);
        }
        builder.header("Host", HttpUtils.getHostHeader(httpUrl));
        builder.header("Accept-Encoding", "identity");
        builder.header("User-Agent", this.userAgent);
        if (obj != null) {
            if (obj instanceof PartSource) {
                str = ((PartSource) obj).md5Hash();
            } else if (obj instanceof byte[]) {
                str = Digest.md5Hash((byte[]) obj, i);
            }
            if (credentials != null) {
                str2 = null;
            } else if (httpUrl.isHttps()) {
                str2 = "UNSIGNED-PAYLOAD";
            } else {
                if (obj != null) {
                    if (obj instanceof PartSource) {
                        str2 = ((PartSource) obj).sha256Hash();
                    } else if (obj instanceof byte[]) {
                        str2 = Digest.sha256Hash((byte[]) obj, i);
                    }
                }
                str2 = Digest.ZERO_SHA256_HASH;
            }
            if (str != null) {
                builder.header("Content-MD5", str);
            }
            if (str2 != null) {
                builder.header("x-amz-content-sha256", str2);
            }
            if (credentials != null && credentials.sessionToken() != null) {
                builder.header("X-Amz-Security-Token", credentials.sessionToken());
            }
            builder.header(com.amazonaws.services.p048s3.Headers.S3_ALTERNATE_DATE, ZonedDateTime.now().format(Time.AMZ_DATE_FORMAT));
            if (obj == null) {
                String str3 = headers != null ? headers.get("Content-Type") : null;
                if (obj instanceof PartSource) {
                    httpRequestBody = new HttpRequestBody((PartSource) obj, str3);
                } else {
                    httpRequestBody = new HttpRequestBody((byte[]) obj, i, str3);
                }
            } else {
                httpRequestBody = null;
            }
            builder.method(method.toString(), httpRequestBody);
            return builder.build();
        }
        str = Digest.ZERO_MD5_HASH;
        if (credentials != null) {
        }
        if (str != null) {
        }
        if (str2 != null) {
        }
        if (credentials != null) {
            builder.header("X-Amz-Security-Token", credentials.sessionToken());
        }
        builder.header(com.amazonaws.services.p048s3.Headers.S3_ALTERNATE_DATE, ZonedDateTime.now().format(Time.AMZ_DATE_FORMAT));
        if (obj == null) {
        }
        builder.method(method.toString(), httpRequestBody);
        return builder.build();
    }

    private StringBuilder newTraceBuilder(Request request, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------START-HTTP---------\n");
        String encodedPath = request.url().encodedPath();
        String encodedQuery = request.url().encodedQuery();
        if (encodedQuery != null) {
            encodedPath = encodedPath + "?" + encodedQuery;
        }
        sb.append(request.method());
        sb.append(" ");
        sb.append(encodedPath);
        sb.append(" HTTP/1.1\n");
        sb.append(request.headers().toString().replaceAll("Signature=([0-9a-f]+)", "Signature=*REDACTED*").replaceAll("Credential=([^/]+)", "Credential=*REDACTED*"));
        if (str != null) {
            sb.append("\n");
            sb.append(str);
        }
        return sb;
    }

    protected Response execute(Method method, BaseArgs baseArgs, Multimap<String, String> multimap, Multimap<String, String> multimap2, Object obj, int i) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        String str;
        String str2;
        if (baseArgs instanceof BucketArgs) {
            BucketArgs bucketArgs = (BucketArgs) baseArgs;
            str2 = bucketArgs.bucket();
            str = bucketArgs.region();
        } else {
            str = null;
            str2 = null;
        }
        return execute(method, str2, baseArgs instanceof ObjectArgs ? ((ObjectArgs) baseArgs).object() : null, getRegion(str2, str), httpHeaders(merge(baseArgs.extraHeaders(), multimap)), merge(baseArgs.extraQueryParams(), multimap2), obj, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:97:0x01fd. Please report as an issue. */
    public Response execute(Method method, String str, String str2, String str3, okhttp3.Headers headers, Multimap<String, String> multimap, Object obj, int i) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        int i2;
        boolean z;
        ErrorResponse errorResponse;
        String str4;
        String str5;
        String str6;
        byte[] bArr = obj;
        if (bArr == null || (bArr instanceof PartSource) || (bArr instanceof byte[])) {
            i2 = i;
            z = false;
        } else {
            if (bArr instanceof CharSequence) {
                bArr = obj.toString().getBytes(StandardCharsets.UTF_8);
            } else {
                bArr = Xml.marshal(obj).getBytes(StandardCharsets.UTF_8);
            }
            i2 = bArr.length;
            z = true;
        }
        if (bArr == null && (method == Method.PUT || method == Method.POST)) {
            bArr = HttpUtils.EMPTY_BODY;
        }
        byte[] bArr2 = bArr;
        HttpUrl buildUrl = buildUrl(method, str, str2, str3, multimap);
        Provider provider = this.provider;
        ErrorResponse errorResponse2 = null;
        Credentials fetch = provider == null ? null : provider.fetch();
        Request createRequest = createRequest(buildUrl, method, headers, bArr2, i2, fetch);
        if (fetch != null) {
            createRequest = Signer.signV4S3(createRequest, str3, fetch.accessKey(), fetch.secretKey(), createRequest.header("x-amz-content-sha256"));
        }
        StringBuilder newTraceBuilder = newTraceBuilder(createRequest, z ? new String(bArr2, StandardCharsets.UTF_8) : null);
        PrintWriter printWriter = this.traceStream;
        if (printWriter != null) {
            printWriter.println(newTraceBuilder.toString());
        }
        newTraceBuilder.append("\n");
        OkHttpClient okHttpClient = this.httpClient;
        if (!(bArr2 instanceof byte[]) && (method == Method.PUT || method == Method.POST)) {
            okHttpClient = this.httpClient.newBuilder().retryOnConnectionFailure(false).build();
        }
        Response execute = okHttpClient.newCall(createRequest).execute();
        String str7 = execute.protocol().toString().toUpperCase(Locale.US) + " " + execute.code() + "\n" + execute.headers();
        newTraceBuilder.append(str7);
        newTraceBuilder.append("\n");
        if (printWriter != null) {
            printWriter.println(str7);
        }
        if (execute.isSuccessful()) {
            if (printWriter != null) {
                Set<String> keySet = multimap.keySet();
                if ((method != Method.GET || str2 == null || !Collections.disjoint(keySet, TRACE_QUERY_PARAMS)) && (!keySet.contains(TmpConstant.DEVICE_MODEL_EVENTS) || (!keySet.contains(RequestParameters.PREFIX) && !keySet.contains("suffix")))) {
                    printWriter.println(execute.peekBody(1048576L).string());
                }
                printWriter.println(END_HTTP);
            }
            return execute;
        }
        ResponseBody body = execute.body();
        try {
            String string = body.string();
            if (body != null) {
                body.close();
            }
            if (!"".equals(string) || !method.equals(Method.HEAD)) {
                newTraceBuilder.append(string);
                newTraceBuilder.append("\n");
                if (printWriter != null) {
                    printWriter.println(string);
                }
            }
            newTraceBuilder.append(END_HTTP);
            newTraceBuilder.append("\n");
            if (printWriter != null) {
                printWriter.println(END_HTTP);
            }
            String str8 = execute.headers().get("content-type");
            if (!method.equals(Method.HEAD) && (str8 == null || !Arrays.asList(str8.split(VoiceWakeuperAidl.PARAMS_SEPARATE)).contains(Mimetypes.MIMETYPE_XML))) {
                throw new InvalidResponseException(execute.code(), str8, string.substring(0, string.length() <= 1024 ? string.length() : 1024), newTraceBuilder.toString());
            }
            if (!"".equals(string)) {
                errorResponse2 = (ErrorResponse) Xml.unmarshal(ErrorResponse.class, string);
            } else if (!method.equals(Method.HEAD)) {
                throw new InvalidResponseException(execute.code(), str8, string, newTraceBuilder.toString());
            }
            if (errorResponse2 == null) {
                int code = execute.code();
                String str9 = NO_SUCH_BUCKET_MESSAGE;
                if (code == 301 || code == 307 || code == 400) {
                    String[] handleRedirectResponse = handleRedirectResponse(method, str, execute, true);
                    str4 = handleRedirectResponse[0];
                    str9 = handleRedirectResponse[1];
                } else if (code != 409) {
                    if (code == 412) {
                        str5 = "PreconditionFailed";
                        str6 = "At least one of the preconditions you specified did not hold";
                    } else if (code != 416) {
                        if (code != 501) {
                            switch (code) {
                                case 403:
                                    str5 = "AccessDenied";
                                    str6 = "Access denied";
                                    break;
                                case 404:
                                    if (str2 == null) {
                                        if (str == null) {
                                            str5 = "ResourceNotFound";
                                            str6 = "Request resource not found";
                                            break;
                                        }
                                        str4 = NO_SUCH_BUCKET;
                                        break;
                                    } else {
                                        str5 = "NoSuchKey";
                                        str6 = "Object does not exist";
                                        break;
                                    }
                                case 405:
                                    break;
                                default:
                                    throw new ServerException("server failed with HTTP status code " + execute.code(), newTraceBuilder.toString());
                            }
                        }
                        str5 = "MethodNotAllowed";
                        str6 = "The specified method is not allowed against this resource";
                    } else {
                        str5 = "InvalidRange";
                        str6 = "The requested range cannot be satisfied";
                    }
                    str9 = str6;
                    str4 = str5;
                } else {
                    if (str == null) {
                        str5 = "ResourceConflict";
                        str6 = "Request resource conflicts";
                        str9 = str6;
                        str4 = str5;
                    }
                    str4 = NO_SUCH_BUCKET;
                }
                errorResponse = new ErrorResponse(str4, str9, str, str2, createRequest.url().encodedPath(), execute.header(com.amazonaws.services.p048s3.Headers.REQUEST_ID), execute.header(com.amazonaws.services.p048s3.Headers.EXTENDED_REQUEST_ID));
            } else {
                errorResponse = errorResponse2;
            }
            if (errorResponse.code().equals(NO_SUCH_BUCKET) || errorResponse.code().equals(RETRY_HEAD)) {
                this.regionCache.remove(str);
            }
            throw new ErrorResponseException(errorResponse, execute, newTraceBuilder.toString());
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a2, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String getRegion(String str, String str2) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        if (str2 != null) {
            String str3 = this.region;
            if (str3 == null || str3.equals(str2)) {
                return str2;
            }
            throw new IllegalArgumentException("region must be " + this.region + ", but passed " + str2);
        }
        String str4 = this.region;
        if (str4 != null && !str4.equals("")) {
            return this.region;
        }
        String str5 = US_EAST_1;
        if (str == null || this.provider == null) {
            return US_EAST_1;
        }
        String str6 = this.regionCache.get(str);
        if (str6 != null) {
            return str6;
        }
        ResponseBody body = execute(Method.GET, str, null, US_EAST_1, null, newMultimap(RequestParameters.SUBRESOURCE_LOCATION, null), null, 0).body();
        try {
            LocationConstraint locationConstraint = (LocationConstraint) Xml.unmarshal(LocationConstraint.class, body.charStream());
            if (locationConstraint.location() != null && !locationConstraint.location().equals("")) {
                str5 = locationConstraint.location().equals("EU") ? "eu-west-1" : locationConstraint.location();
            }
            this.regionCache.put(str, str5);
            return str5;
        } catch (Throwable th) {
            if (body != null) {
                try {
                    body.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response executeGet(BaseArgs baseArgs, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return execute(Method.GET, baseArgs, multimap, multimap2, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response executeHead(BaseArgs baseArgs, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        try {
            Response execute = execute(Method.HEAD, baseArgs, multimap, multimap2, null, 0);
            execute.body().close();
            return execute;
        } catch (ErrorResponseException e) {
            if (!e.errorResponse().code().equals(RETRY_HEAD)) {
                throw e;
            }
            try {
                Response execute2 = execute(Method.HEAD, baseArgs, multimap, multimap2, null, 0);
                execute2.body().close();
                return execute2;
            } catch (ErrorResponseException e2) {
                ErrorResponse errorResponse = e2.errorResponse();
                if (!errorResponse.code().equals(RETRY_HEAD)) {
                    throw e2;
                }
                String[] handleRedirectResponse = handleRedirectResponse(Method.HEAD, errorResponse.bucketName(), e2.response(), false);
                throw new ErrorResponseException(new ErrorResponse(handleRedirectResponse[0], handleRedirectResponse[1], errorResponse.bucketName(), errorResponse.objectName(), errorResponse.resource(), errorResponse.requestId(), errorResponse.hostId()), e2.response(), e2.httpTrace());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response executeDelete(BaseArgs baseArgs, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        Response execute = execute(Method.DELETE, baseArgs, multimap, multimap2, null, 0);
        execute.body().close();
        return execute;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response executePost(BaseArgs baseArgs, Multimap<String, String> multimap, Multimap<String, String> multimap2, Object obj) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return execute(Method.POST, baseArgs, multimap, multimap2, obj, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response executePut(BaseArgs baseArgs, Multimap<String, String> multimap, Multimap<String, String> multimap2, Object obj, int i) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return execute(Method.PUT, baseArgs, multimap, multimap2, obj, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int calculatePartCount(List<ComposeSource> list) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        long j;
        int i = 0;
        long j2 = 0;
        int i2 = 0;
        long j3 = 0;
        for (ComposeSource composeSource : list) {
            i2++;
            StatObjectResponse statObject = statObject(new StatObjectArgs(composeSource));
            composeSource.buildHeaders(statObject.size(), statObject.etag());
            long size = statObject.size();
            if (composeSource.length() != null) {
                size = composeSource.length().longValue();
            } else if (composeSource.offset() != null) {
                size -= composeSource.offset().longValue();
            }
            if (size < 5242880 && list.size() != 1 && i2 != list.size()) {
                throw new IllegalArgumentException("source " + composeSource.bucket() + "/" + composeSource.object() + ": size " + size + " must be greater than 5242880");
            }
            j3 += size;
            if (j3 > ObjectWriteArgs.MAX_OBJECT_SIZE) {
                throw new IllegalArgumentException("destination object size must be less than 5497558138880");
            }
            long j4 = 5368709120L;
            if (size > 5368709120L) {
                long j5 = size / 5368709120L;
                long j6 = size - (j5 * 5368709120L);
                if (j6 > j2) {
                    j = j5 + 1;
                    j4 = j6;
                } else {
                    j = j5;
                }
                if (j4 < 5242880 && list.size() != 1 && i2 != list.size()) {
                    throw new IllegalArgumentException("source " + composeSource.bucket() + "/" + composeSource.object() + ": for multipart split upload of " + size + ", last part size is less than 5242880");
                }
                i += (int) j;
            } else {
                i++;
            }
            if (i > 10000) {
                throw new IllegalArgumentException("Compose sources create more than allowed multipart count 10000");
            }
            j2 = 0;
        }
        return i;
    }

    /* loaded from: classes7.dex */
    private abstract class ObjectIterator implements Iterator<Result<Item>> {
        protected boolean completed;
        protected Iterator<DeleteMarker> deleteMarkerIterator;
        protected Result<Item> error;
        protected Iterator<? extends Item> itemIterator;
        protected String lastObjectName;
        protected ListObjectsResult listObjectsResult;
        protected Iterator<Prefix> prefixIterator;

        protected abstract void populateResult() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException;

        private ObjectIterator() {
            this.completed = false;
        }

        protected synchronized void populate() {
            try {
                populateResult();
            } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
                this.error = new Result<>(e);
            }
            if (this.listObjectsResult != null) {
                this.itemIterator = this.listObjectsResult.contents().iterator();
                this.deleteMarkerIterator = this.listObjectsResult.deleteMarkers().iterator();
                this.prefixIterator = this.listObjectsResult.commonPrefixes().iterator();
            } else {
                this.itemIterator = new LinkedList().iterator();
                this.deleteMarkerIterator = new LinkedList().iterator();
                this.prefixIterator = new LinkedList().iterator();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.completed) {
                return false;
            }
            if (this.error == null && this.itemIterator == null && this.deleteMarkerIterator == null && this.prefixIterator == null) {
                populate();
            }
            if (this.error == null && !this.itemIterator.hasNext() && !this.deleteMarkerIterator.hasNext() && !this.prefixIterator.hasNext() && this.listObjectsResult.isTruncated()) {
                populate();
            }
            if (this.error != null || this.itemIterator.hasNext() || this.deleteMarkerIterator.hasNext() || this.prefixIterator.hasNext()) {
                return true;
            }
            this.completed = true;
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Result<Item> next() {
            if (this.completed) {
                throw new NoSuchElementException();
            }
            if (this.error == null && this.itemIterator == null && this.deleteMarkerIterator == null && this.prefixIterator == null) {
                populate();
            }
            if (this.error == null && !this.itemIterator.hasNext() && !this.deleteMarkerIterator.hasNext() && !this.prefixIterator.hasNext() && this.listObjectsResult.isTruncated()) {
                populate();
            }
            Result<Item> result = this.error;
            if (result != null) {
                this.completed = true;
                return result;
            }
            DeleteMarker deleteMarker = null;
            if (this.itemIterator.hasNext()) {
                deleteMarker = this.itemIterator.next();
                deleteMarker.setEncodingType(this.listObjectsResult.encodingType());
                this.lastObjectName = deleteMarker.objectName();
            } else if (this.deleteMarkerIterator.hasNext()) {
                deleteMarker = this.deleteMarkerIterator.next();
            } else if (this.prefixIterator.hasNext()) {
                deleteMarker = this.prefixIterator.next().toItem();
            }
            if (deleteMarker != null) {
                deleteMarker.setEncodingType(this.listObjectsResult.encodingType());
                return new Result<>(deleteMarker);
            }
            this.completed = true;
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable<Result<Item>> listObjectsV2(final ListObjectsArgs listObjectsArgs) {
        return new Iterable<Result<Item>>() { // from class: io.minio.S3Base.1
            @Override // java.lang.Iterable
            public Iterator<Result<Item>> iterator() {
                return new ObjectIterator() { // from class: io.minio.S3Base.1.1
                    private ListBucketResultV2 result;

                    {
                        S3Base s3Base = S3Base.this;
                        this.result = null;
                    }

                    @Override // io.minio.S3Base.ObjectIterator
                    protected void populateResult() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
                        this.listObjectsResult = null;
                        this.itemIterator = null;
                        this.prefixIterator = null;
                        S3Base s3Base = S3Base.this;
                        String bucket = listObjectsArgs.bucket();
                        String region = listObjectsArgs.region();
                        String delimiter = listObjectsArgs.delimiter();
                        String str = listObjectsArgs.useUrlEncodingType() ? "url" : null;
                        String startAfter = listObjectsArgs.startAfter();
                        Integer valueOf = Integer.valueOf(listObjectsArgs.maxKeys());
                        String prefix = listObjectsArgs.prefix();
                        ListBucketResultV2 listBucketResultV2 = this.result;
                        ListObjectsV2Response listObjectsV2 = s3Base.listObjectsV2(bucket, region, delimiter, str, startAfter, valueOf, prefix, listBucketResultV2 == null ? listObjectsArgs.continuationToken() : listBucketResultV2.nextContinuationToken(), listObjectsArgs.fetchOwner(), listObjectsArgs.includeUserMetadata(), listObjectsArgs.extraHeaders(), listObjectsArgs.extraQueryParams());
                        this.result = listObjectsV2.result();
                        this.listObjectsResult = listObjectsV2.result();
                    }
                };
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable<Result<Item>> listObjectsV1(final ListObjectsArgs listObjectsArgs) {
        return new Iterable<Result<Item>>() { // from class: io.minio.S3Base.2
            @Override // java.lang.Iterable
            public Iterator<Result<Item>> iterator() {
                return new ObjectIterator() { // from class: io.minio.S3Base.2.1
                    private ListBucketResultV1 result;

                    {
                        S3Base s3Base = S3Base.this;
                        this.result = null;
                    }

                    @Override // io.minio.S3Base.ObjectIterator
                    protected void populateResult() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
                        this.listObjectsResult = null;
                        this.itemIterator = null;
                        this.prefixIterator = null;
                        ListBucketResultV1 listBucketResultV1 = this.result;
                        String marker = listBucketResultV1 == null ? listObjectsArgs.marker() : listBucketResultV1.nextMarker();
                        if (marker == null) {
                            marker = this.lastObjectName;
                        }
                        ListObjectsV1Response listObjectsV1 = S3Base.this.listObjectsV1(listObjectsArgs.bucket(), listObjectsArgs.region(), listObjectsArgs.delimiter(), listObjectsArgs.useUrlEncodingType() ? "url" : null, marker, Integer.valueOf(listObjectsArgs.maxKeys()), listObjectsArgs.prefix(), listObjectsArgs.extraHeaders(), listObjectsArgs.extraQueryParams());
                        this.result = listObjectsV1.result();
                        this.listObjectsResult = listObjectsV1.result();
                    }
                };
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable<Result<Item>> listObjectVersions(final ListObjectsArgs listObjectsArgs) {
        return new Iterable<Result<Item>>() { // from class: io.minio.S3Base.3
            @Override // java.lang.Iterable
            public Iterator<Result<Item>> iterator() {
                return new ObjectIterator() { // from class: io.minio.S3Base.3.1
                    private ListVersionsResult result;

                    {
                        S3Base s3Base = S3Base.this;
                        this.result = null;
                    }

                    @Override // io.minio.S3Base.ObjectIterator
                    protected void populateResult() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
                        this.listObjectsResult = null;
                        this.itemIterator = null;
                        this.prefixIterator = null;
                        S3Base s3Base = S3Base.this;
                        String bucket = listObjectsArgs.bucket();
                        String region = listObjectsArgs.region();
                        String delimiter = listObjectsArgs.delimiter();
                        String str = listObjectsArgs.useUrlEncodingType() ? "url" : null;
                        ListVersionsResult listVersionsResult = this.result;
                        String keyMarker = listVersionsResult == null ? listObjectsArgs.keyMarker() : listVersionsResult.nextKeyMarker();
                        Integer valueOf = Integer.valueOf(listObjectsArgs.maxKeys());
                        String prefix = listObjectsArgs.prefix();
                        ListVersionsResult listVersionsResult2 = this.result;
                        ListObjectVersionsResponse listObjectVersions = s3Base.listObjectVersions(bucket, region, delimiter, str, keyMarker, valueOf, prefix, listVersionsResult2 == null ? listObjectsArgs.versionIdMarker() : listVersionsResult2.nextVersionIdMarker(), listObjectsArgs.extraHeaders(), listObjectsArgs.extraQueryParams());
                        this.result = listObjectVersions.result();
                        this.listObjectsResult = listObjectVersions.result();
                    }
                };
            }
        };
    }

    private PartReader newPartReader(Object obj, long j, long j2, int i) {
        if (obj instanceof RandomAccessFile) {
            return new PartReader((RandomAccessFile) obj, j, j2, i);
        }
        if (obj instanceof InputStream) {
            return new PartReader((InputStream) obj, j, j2, i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ObjectWriteResponse putObject(PutObjectBaseArgs putObjectBaseArgs, Object obj, long j, long j2, int i, String str) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        Part[] partArr;
        String str2;
        Multimap<String, String> newMultimap = newMultimap(putObjectBaseArgs.extraHeaders());
        newMultimap.putAll(putObjectBaseArgs.genHeaders());
        if (!newMultimap.containsKey("Content-Type")) {
            newMultimap.put("Content-Type", str);
        }
        PartReader newPartReader = newPartReader(obj, j, j2, i);
        if (newPartReader == null) {
            throw new IllegalArgumentException("data must be RandomAccessFile or InputStream");
        }
        Part[] partArr2 = null;
        String str3 = null;
        while (true) {
            try {
                PartSource part = newPartReader.getPart(!this.baseUrl.isHttps());
                if (part != null) {
                    if (newPartReader.partCount() == 1) {
                        return putObject(putObjectBaseArgs.bucket(), putObjectBaseArgs.region(), putObjectBaseArgs.object(), part, newMultimap, putObjectBaseArgs.extraQueryParams());
                    }
                    if (str3 == null) {
                        String uploadId = createMultipartUpload(putObjectBaseArgs.bucket(), putObjectBaseArgs.region(), putObjectBaseArgs.object(), newMultimap, putObjectBaseArgs.extraQueryParams()).result().uploadId();
                        try {
                            str2 = uploadId;
                            partArr = new Part[10000];
                        } catch (RuntimeException e) {
                            e = e;
                            str3 = uploadId;
                            if (str3 != null) {
                                abortMultipartUpload(putObjectBaseArgs.bucket(), putObjectBaseArgs.region(), putObjectBaseArgs.object(), str3, null, null);
                            }
                            throw e;
                        } catch (Exception e2) {
                            e = e2;
                            str3 = uploadId;
                            if (str3 != null) {
                                abortMultipartUpload(putObjectBaseArgs.bucket(), putObjectBaseArgs.region(), putObjectBaseArgs.object(), str3, null, null);
                            }
                            throw e;
                        }
                    } else {
                        partArr = partArr2;
                        str2 = str3;
                    }
                    try {
                        Map<String, String> headers = (putObjectBaseArgs.sse() == null || !(putObjectBaseArgs.sse() instanceof ServerSideEncryptionCustomerKey)) ? null : putObjectBaseArgs.sse().headers();
                        int partNumber = part.partNumber();
                        partArr[partNumber - 1] = new Part(partNumber, uploadPart(putObjectBaseArgs.bucket(), putObjectBaseArgs.region(), putObjectBaseArgs.object(), part, partNumber, str2, headers != null ? Multimaps.forMap(headers) : null, null).etag());
                        str3 = str2;
                        partArr2 = partArr;
                    } catch (RuntimeException e3) {
                        e = e3;
                        str3 = str2;
                        if (str3 != null) {
                        }
                        throw e;
                    } catch (Exception e4) {
                        e = e4;
                        str3 = str2;
                        if (str3 != null) {
                        }
                        throw e;
                    }
                } else {
                    return completeMultipartUpload(putObjectBaseArgs.bucket(), putObjectBaseArgs.region(), putObjectBaseArgs.object(), str3, partArr2, null, null);
                }
            } catch (RuntimeException e5) {
                e = e5;
            } catch (Exception e6) {
                e = e6;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes7.dex */
    public static class NotificationResultRecords {
        ObjectMapper mapper;
        Response response;
        Scanner scanner;

        public NotificationResultRecords(Response response) {
            this.response = null;
            this.scanner = null;
            this.mapper = null;
            this.response = response;
            this.scanner = new Scanner(response.body().charStream()).useDelimiter("\n");
            this.mapper = new ObjectMapper();
            this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        }

        public CloseableIterator<Result<NotificationRecords>> closeableIterator() {
            return new CloseableIterator<Result<NotificationRecords>>() { // from class: io.minio.S3Base.NotificationResultRecords.1
                String recordsString = null;
                NotificationRecords records = null;
                boolean isClosed = false;

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    if (this.isClosed) {
                        return;
                    }
                    try {
                        NotificationResultRecords.this.response.body().close();
                        NotificationResultRecords.this.scanner.close();
                    } finally {
                        this.isClosed = true;
                    }
                }

                public boolean populate() {
                    if (this.isClosed) {
                        return false;
                    }
                    if (this.recordsString != null) {
                        return true;
                    }
                    while (NotificationResultRecords.this.scanner.hasNext()) {
                        this.recordsString = NotificationResultRecords.this.scanner.next().trim();
                        if (!this.recordsString.equals("")) {
                            break;
                        }
                    }
                    String str = this.recordsString;
                    if (str != null && !str.equals("")) {
                        return true;
                    }
                    try {
                        close();
                    } catch (IOException unused) {
                        this.isClosed = true;
                    }
                    return false;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return populate();
                }

                @Override // java.util.Iterator
                public Result<NotificationRecords> next() {
                    Result<NotificationRecords> result;
                    if (this.isClosed) {
                        throw new NoSuchElementException();
                    }
                    String str = this.recordsString;
                    if ((str == null || str.equals("")) && !populate()) {
                        throw new NoSuchElementException();
                    }
                    try {
                        try {
                            try {
                                try {
                                    this.records = (NotificationRecords) NotificationResultRecords.this.mapper.readValue(this.recordsString, NotificationRecords.class);
                                    return new Result<>(this.records);
                                } catch (JsonParseException e) {
                                    result = new Result<>(e);
                                    return result;
                                }
                            } catch (JsonMappingException e2) {
                                result = new Result<>(e2);
                                return result;
                            }
                        } catch (IOException e3) {
                            result = new Result<>(e3);
                            return result;
                        }
                    } finally {
                        this.recordsString = null;
                        this.records = null;
                    }
                }
            };
        }
    }

    private Multimap<String, String> getCommonListObjectsQueryParams(String str, String str2, Integer num, String str3) {
        String[] strArr = new String[6];
        strArr[0] = RequestParameters.DELIMITER;
        if (str == null) {
            str = "";
        }
        strArr[1] = str;
        strArr[2] = RequestParameters.MAX_KEYS;
        strArr[3] = Integer.toString(num.intValue() > 0 ? num.intValue() : 1000);
        strArr[4] = RequestParameters.PREFIX;
        if (str3 == null) {
            str3 = "";
        }
        strArr[5] = str3;
        Multimap<String, String> newMultimap = newMultimap(strArr);
        if (str2 != null) {
            newMultimap.put(RequestParameters.ENCODING_TYPE, str2);
        }
        return newMultimap;
    }

    public void setTimeout(long j, long j2, long j3) {
        this.httpClient = HttpUtils.setTimeout(this.httpClient, j, j2, j3);
    }

    public void ignoreCertCheck() throws KeyManagementException, NoSuchAlgorithmException {
        this.httpClient = HttpUtils.disableCertCheck(this.httpClient);
    }

    public void setAppInfo(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.userAgent = MinioProperties.INSTANCE.getDefaultUserAgent() + " " + str.trim() + "/" + str2.trim();
    }

    public void traceOn(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("trace stream must be provided");
        }
        this.traceStream = new PrintWriter((Writer) new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
    }

    public void traceOff() throws IOException {
        this.traceStream = null;
    }

    public void enableAccelerateEndpoint() {
        this.isAcceleratedHost = true;
    }

    public void disableAccelerateEndpoint() {
        this.isAcceleratedHost = false;
    }

    public void enableDualStackEndpoint() {
        this.isDualStackHost = true;
    }

    public void disableDualStackEndpoint() {
        this.isDualStackHost = false;
    }

    public void enableVirtualStyleEndpoint() {
        this.useVirtualStyle = true;
    }

    public void disableVirtualStyleEndpoint() {
        this.useVirtualStyle = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StatObjectResponse statObject(StatObjectArgs statObjectArgs) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        checkArgs(statObjectArgs);
        statObjectArgs.validateSsec(this.baseUrl);
        return new StatObjectResponse(executeHead(statObjectArgs, statObjectArgs.getHeaders(), statObjectArgs.versionId() != null ? newMultimap("versionId", statObjectArgs.versionId()) : null).headers(), statObjectArgs.bucket(), statObjectArgs.region(), statObjectArgs.object());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbortMultipartUploadResponse abortMultipartUpload(String str, String str2, String str3, String str4, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Response execute = execute(Method.DELETE, str, str3, getRegion(str, str2), httpHeaders(multimap), merge(multimap2, newMultimap("uploadId", str4)), null, 0);
        try {
            AbortMultipartUploadResponse abortMultipartUploadResponse = new AbortMultipartUploadResponse(execute.headers(), str, str2, str3, str4);
            if (execute != null) {
                execute.close();
            }
            return abortMultipartUploadResponse;
        } catch (Throwable th) {
            if (execute != null) {
                try {
                    execute.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectWriteResponse completeMultipartUpload(String str, String str2, String str3, String str4, Part[] partArr, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Multimap<String, String> newMultimap = newMultimap(multimap2);
        newMultimap.put("uploadId", str4);
        Response execute = execute(Method.POST, str, str3, getRegion(str, str2), httpHeaders(multimap), newMultimap, new CompleteMultipartUpload(partArr), 0);
        try {
            String trim = execute.body().string().trim();
            if (!trim.isEmpty()) {
                if (Xml.validate(ErrorResponse.class, trim)) {
                    throw new ErrorResponseException((ErrorResponse) Xml.unmarshal(ErrorResponse.class, trim), execute, null);
                }
                try {
                    CompleteMultipartUploadOutput completeMultipartUploadOutput = (CompleteMultipartUploadOutput) Xml.unmarshal(CompleteMultipartUploadOutput.class, trim);
                    ObjectWriteResponse objectWriteResponse = new ObjectWriteResponse(execute.headers(), completeMultipartUploadOutput.bucket(), completeMultipartUploadOutput.location(), completeMultipartUploadOutput.object(), completeMultipartUploadOutput.etag(), execute.header(com.amazonaws.services.p048s3.Headers.S3_VERSION_ID));
                    if (execute != null) {
                        execute.close();
                    }
                    return objectWriteResponse;
                } catch (XmlParserException unused) {
                    Logger.getLogger(MinioClient.class.getName()).warning("S3 service returned unknown XML for CompleteMultipartUpload REST API. " + trim);
                }
            }
            ObjectWriteResponse objectWriteResponse2 = new ObjectWriteResponse(execute.headers(), str, str2, str3, null, execute.header(com.amazonaws.services.p048s3.Headers.S3_VERSION_ID));
            if (execute != null) {
                execute.close();
            }
            return objectWriteResponse2;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CreateMultipartUploadResponse createMultipartUpload(String str, String str2, String str3, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Multimap<String, String> newMultimap = newMultimap(multimap2);
        newMultimap.put(RequestParameters.SUBRESOURCE_UPLOADS, "");
        Multimap<String, String> newMultimap2 = newMultimap(multimap);
        if (!newMultimap2.containsKey("Content-Type")) {
            newMultimap2.put("Content-Type", "application/octet-stream");
        }
        Response execute = execute(Method.POST, str, str3, getRegion(str, str2), httpHeaders(newMultimap2), newMultimap, null, 0);
        try {
            CreateMultipartUploadResponse createMultipartUploadResponse = new CreateMultipartUploadResponse(execute.headers(), str, str2, str3, (InitiateMultipartUploadResult) Xml.unmarshal(InitiateMultipartUploadResult.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return createMultipartUploadResponse;
        } catch (Throwable th) {
            if (execute != null) {
                try {
                    execute.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DeleteObjectsResponse deleteObjects(String str, String str2, List<DeleteObject> list, boolean z, boolean z2, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        List<DeleteObject> linkedList = list == null ? new LinkedList<>() : list;
        if (linkedList.size() > 1000) {
            throw new IllegalArgumentException("list of objects must not be more than 1000");
        }
        Response execute = execute(Method.POST, str, null, getRegion(str, str2), httpHeaders(merge(multimap, z2 ? newMultimap("x-amz-bypass-governance-retention", "true") : null)), merge(multimap2, newMultimap(RequestParameters.SUBRESOURCE_DELETE, "")), new DeleteRequest(linkedList, z), 0);
        try {
            String string = execute.body().string();
            try {
                if (Xml.validate(DeleteError.class, string)) {
                    DeleteObjectsResponse deleteObjectsResponse = new DeleteObjectsResponse(execute.headers(), str, str2, new DeleteResult((DeleteError) Xml.unmarshal(DeleteError.class, string)));
                    if (execute != null) {
                        execute.close();
                    }
                    return deleteObjectsResponse;
                }
            } catch (XmlParserException unused) {
            }
            DeleteObjectsResponse deleteObjectsResponse2 = new DeleteObjectsResponse(execute.headers(), str, str2, (DeleteResult) Xml.unmarshal(DeleteResult.class, string));
            if (execute != null) {
                execute.close();
            }
            return deleteObjectsResponse2;
        } finally {
        }
    }

    protected ListObjectsV2Response listObjectsV2(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, boolean z, boolean z2, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException, IOException {
        Multimap<String, String> merge = merge(multimap2, getCommonListObjectsQueryParams(str3, str4, num, str6));
        merge.put("list-type", "2");
        if (str7 != null) {
            merge.put("continuation-token", str7);
        }
        if (z) {
            merge.put("fetch-owner", "true");
        }
        if (str5 != null) {
            merge.put("start-after", str5);
        }
        if (z2) {
            merge.put("metadata", "true");
        }
        Response execute = execute(Method.GET, str, null, getRegion(str, str2), httpHeaders(multimap), merge, null, 0);
        try {
            ListObjectsV2Response listObjectsV2Response = new ListObjectsV2Response(execute.headers(), str, str2, (ListBucketResultV2) Xml.unmarshal(ListBucketResultV2.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return listObjectsV2Response;
        } catch (Throwable th) {
            if (execute != null) {
                try {
                    execute.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    protected ListObjectsV1Response listObjectsV1(String str, String str2, String str3, String str4, String str5, Integer num, String str6, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Multimap<String, String> merge = merge(multimap2, getCommonListObjectsQueryParams(str3, str4, num, str6));
        if (str5 != null) {
            merge.put(RequestParameters.MARKER, str5);
        }
        Response execute = execute(Method.GET, str, null, getRegion(str, str2), httpHeaders(multimap), merge, null, 0);
        try {
            ListObjectsV1Response listObjectsV1Response = new ListObjectsV1Response(execute.headers(), str, str2, (ListBucketResultV1) Xml.unmarshal(ListBucketResultV1.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return listObjectsV1Response;
        } finally {
        }
    }

    protected ListObjectVersionsResponse listObjectVersions(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Multimap<String, String> merge = merge(multimap2, getCommonListObjectsQueryParams(str3, str4, num, str6));
        if (str5 != null) {
            merge.put(RequestParameters.KEY_MARKER, str5);
        }
        if (str7 != null) {
            merge.put("version-id-marker", str7);
        }
        merge.put("versions", "");
        Response execute = execute(Method.GET, str, null, getRegion(str, str2), httpHeaders(multimap), merge, null, 0);
        try {
            ListObjectVersionsResponse listObjectVersionsResponse = new ListObjectVersionsResponse(execute.headers(), str, str2, (ListVersionsResult) Xml.unmarshal(ListVersionsResult.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return listObjectVersionsResponse;
        } finally {
        }
    }

    private ObjectWriteResponse putObject(String str, String str2, String str3, PartSource partSource, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Response execute = execute(Method.PUT, str, str3, getRegion(str, str2), httpHeaders(multimap), multimap2, partSource, 0);
        try {
            ObjectWriteResponse objectWriteResponse = new ObjectWriteResponse(execute.headers(), str, str2, str3, execute.header("ETag").replaceAll("\"", ""), execute.header(com.amazonaws.services.p048s3.Headers.S3_VERSION_ID));
            if (execute != null) {
                execute.close();
            }
            return objectWriteResponse;
        } catch (Throwable th) {
            if (execute != null) {
                try {
                    execute.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectWriteResponse putObject(String str, String str2, String str3, Object obj, long j, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        if (!(obj instanceof InputStream) && !(obj instanceof RandomAccessFile) && !(obj instanceof byte[]) && !(obj instanceof CharSequence)) {
            throw new IllegalArgumentException("data must be InputStream, RandomAccessFile, byte[] or String");
        }
        PartReader newPartReader = newPartReader(obj, j, j, 1);
        if (newPartReader != null) {
            return putObject(str, str2, str3, newPartReader.getPart(!this.baseUrl.isHttps()), multimap, multimap2);
        }
        Response execute = execute(Method.PUT, str, str3, getRegion(str, str2), httpHeaders(multimap), multimap2, obj, (int) j);
        try {
            ObjectWriteResponse objectWriteResponse = new ObjectWriteResponse(execute.headers(), str, str2, str3, execute.header("ETag").replaceAll("\"", ""), execute.header(com.amazonaws.services.p048s3.Headers.S3_VERSION_ID));
            if (execute != null) {
                execute.close();
            }
            return objectWriteResponse;
        } finally {
        }
    }

    protected ListMultipartUploadsResponse listMultipartUploads(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        String[] strArr = new String[8];
        strArr[0] = RequestParameters.SUBRESOURCE_UPLOADS;
        strArr[1] = "";
        strArr[2] = RequestParameters.DELIMITER;
        strArr[3] = str3 != null ? str3 : "";
        strArr[4] = RequestParameters.MAX_UPLOADS;
        strArr[5] = num != null ? num.toString() : "1000";
        strArr[6] = RequestParameters.PREFIX;
        strArr[7] = str6 != null ? str6 : "";
        Multimap<String, String> merge = merge(multimap2, newMultimap(strArr));
        if (str4 != null) {
            merge.put(RequestParameters.ENCODING_TYPE, str4);
        }
        if (str5 != null) {
            merge.put(RequestParameters.KEY_MARKER, str5);
        }
        if (str7 != null) {
            merge.put(RequestParameters.UPLOAD_ID_MARKER, str7);
        }
        Response execute = execute(Method.GET, str, null, getRegion(str, str2), httpHeaders(multimap), merge, null, 0);
        try {
            ListMultipartUploadsResponse listMultipartUploadsResponse = new ListMultipartUploadsResponse(execute.headers(), str, str2, (ListMultipartUploadsResult) Xml.unmarshal(ListMultipartUploadsResult.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return listMultipartUploadsResponse;
        } finally {
        }
    }

    protected ListPartsResponse listParts(String str, String str2, String str3, Integer num, Integer num2, String str4, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        String[] strArr = new String[4];
        strArr[0] = "uploadId";
        strArr[1] = str4;
        strArr[2] = RequestParameters.MAX_PARTS;
        strArr[3] = num != null ? num.toString() : "1000";
        Multimap<String, String> merge = merge(multimap2, newMultimap(strArr));
        if (num2 != null) {
            merge.put(RequestParameters.PART_NUMBER_MARKER, num2.toString());
        }
        Response execute = execute(Method.GET, str, str3, getRegion(str, str2), httpHeaders(multimap), merge, null, 0);
        try {
            ListPartsResponse listPartsResponse = new ListPartsResponse(execute.headers(), str, str2, str3, (ListPartsResult) Xml.unmarshal(ListPartsResult.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return listPartsResponse;
        } finally {
        }
    }

    private UploadPartResponse uploadPart(String str, String str2, String str3, PartSource partSource, int i, String str4, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Response execute = execute(Method.PUT, str, str3, getRegion(str, str2), httpHeaders(multimap), merge(multimap2, newMultimap(RequestParameters.PART_NUMBER, Integer.toString(i), "uploadId", str4)), partSource, 0);
        try {
            UploadPartResponse uploadPartResponse = new UploadPartResponse(execute.headers(), str, str2, str3, str4, i, execute.header("ETag").replaceAll("\"", ""));
            if (execute != null) {
                execute.close();
            }
            return uploadPartResponse;
        } catch (Throwable th) {
            if (execute == null) {
                throw th;
            }
            try {
                execute.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    protected UploadPartResponse uploadPart(String str, String str2, String str3, Object obj, long j, String str4, int i, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        if (!(obj instanceof InputStream) && !(obj instanceof RandomAccessFile) && !(obj instanceof byte[]) && !(obj instanceof CharSequence)) {
            throw new IllegalArgumentException("data must be InputStream, RandomAccessFile, byte[] or String");
        }
        PartReader newPartReader = newPartReader(obj, j, j, 1);
        if (newPartReader != null) {
            return uploadPart(str, str2, str3, newPartReader.getPart(!this.baseUrl.isHttps()), i, str4, multimap, multimap2);
        }
        Response execute = execute(Method.PUT, str, str3, getRegion(str, str2), httpHeaders(multimap), merge(multimap2, newMultimap(RequestParameters.PART_NUMBER, Integer.toString(i), "uploadId", str4)), obj, (int) j);
        try {
            UploadPartResponse uploadPartResponse = new UploadPartResponse(execute.headers(), str, str2, str3, str4, i, execute.header("ETag").replaceAll("\"", ""));
            if (execute != null) {
                execute.close();
            }
            return uploadPartResponse;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UploadPartCopyResponse uploadPartCopy(String str, String str2, String str3, String str4, int i, Multimap<String, String> multimap, Multimap<String, String> multimap2) throws NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, ServerException, XmlParserException, ErrorResponseException, InternalException, InvalidResponseException {
        Response execute = execute(Method.PUT, str, str3, getRegion(str, str2), httpHeaders(multimap), merge(multimap2, newMultimap(RequestParameters.PART_NUMBER, Integer.toString(i), "uploadId", str4)), null, 0);
        try {
            UploadPartCopyResponse uploadPartCopyResponse = new UploadPartCopyResponse(execute.headers(), str, str2, str3, str4, i, (CopyPartResult) Xml.unmarshal(CopyPartResult.class, execute.body().charStream()));
            if (execute != null) {
                execute.close();
            }
            return uploadPartCopyResponse;
        } catch (Throwable th) {
            if (execute == null) {
                throw th;
            }
            try {
                execute.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }
}
