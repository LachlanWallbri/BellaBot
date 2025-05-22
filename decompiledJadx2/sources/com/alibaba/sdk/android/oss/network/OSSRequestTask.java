package com.alibaba.sdk.android.oss.network;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.CRC64;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.OSSRetryType;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.internal.ResponseParsers;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.CheckedInputStream;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    private ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser, ExecutionContext executionContext, int i) {
        this.responseParser = responseParser;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x011b A[Catch: Exception -> 0x0270, TryCatch #0 {Exception -> 0x0270, blocks: (B:3:0x0004, B:5:0x000c, B:6:0x0019, B:8:0x003a, B:9:0x0057, B:11:0x005d, B:13:0x0074, B:24:0x0180, B:109:0x00a1, B:110:0x00a7, B:111:0x00ad, B:114:0x00b8, B:116:0x00c7, B:120:0x011b, B:122:0x0123, B:123:0x012e, B:125:0x014f, B:126:0x016c, B:127:0x00dd, B:129:0x00e5, B:130:0x00fb, B:132:0x0103, B:133:0x0110, B:135:0x0268, B:136:0x026f), top: B:2:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public T call() throws Exception {
        Call call;
        Request request;
        Exception clientException;
        ResponseMessage responseMessage;
        OSSRetryType shouldRetry;
        OSSRequest request2;
        long j;
        String stringBody;
        InputStream inputStream;
        long contentLength;
        try {
            if (this.context.getApplicationContext() != null) {
                OSSLog.logInfo(OSSUtils.buildBaseLogInfo(this.context.getApplicationContext()));
            }
            OSSLog.logDebug("[call] - ");
            request2 = this.context.getRequest();
            OSSUtils.ensureRequestValid(request2, this.message);
            OSSUtils.signRequest(this.message);
        } catch (Exception e) {
            e = e;
            call = null;
            request = null;
        }
        if (this.context.getCancellationHandler().isCancelled()) {
            throw new InterruptedIOException("This task is cancelled!");
        }
        Request.Builder url = new Request.Builder().url(this.message.buildCanonicalURL());
        for (String str : this.message.getHeaders().keySet()) {
            url = url.addHeader(str, (String) this.message.getHeaders().get(str));
        }
        String str2 = (String) this.message.getHeaders().get("Content-Type");
        int i = C08291.$SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[this.message.getMethod().ordinal()];
        if (i == 1 || i == 2) {
            OSSUtils.assertTrue(str2 != null, "Content type can't be null when upload!");
            if (this.message.getUploadData() != null) {
                inputStream = new ByteArrayInputStream(this.message.getUploadData());
                contentLength = this.message.getUploadData().length;
            } else if (this.message.getUploadFilePath() != null) {
                File file = new File(this.message.getUploadFilePath());
                FileInputStream fileInputStream = new FileInputStream(file);
                j = file.length();
                inputStream = fileInputStream;
                stringBody = null;
                if (inputStream != null) {
                    if (this.message.isCheckCRC64()) {
                        inputStream = new CheckedInputStream(inputStream, new CRC64());
                    }
                    this.message.setContent(inputStream);
                    this.message.setContentLength(j);
                    url = url.method(this.message.getMethod().toString(), NetworkProgressHelper.addProgressRequestBody(inputStream, j, str2, this.context));
                } else if (stringBody != null) {
                    url = url.method(this.message.getMethod().toString(), RequestBody.create(MediaType.parse(str2), stringBody.getBytes("UTF-8")));
                } else {
                    url = url.method(this.message.getMethod().toString(), RequestBody.create((MediaType) null, new byte[0]));
                }
            } else if (this.message.getContent() != null) {
                inputStream = this.message.getContent();
                contentLength = this.message.getContentLength();
            } else {
                j = 0;
                stringBody = this.message.getStringBody();
                inputStream = null;
                if (inputStream != null) {
                }
            }
            j = contentLength;
            stringBody = null;
            if (inputStream != null) {
            }
        } else if (i == 3) {
            url = url.get();
        } else if (i == 4) {
            url = url.head();
        } else if (i == 5) {
            url = url.delete();
        }
        request = url.build();
        try {
            if (request2 instanceof GetObjectRequest) {
                this.client = NetworkProgressHelper.addProgressResponseListener(this.client, this.context);
                OSSLog.logDebug("getObject");
            }
            call = this.client.newCall(request);
            try {
                this.context.getCancellationHandler().setCall(call);
                Response execute = call.execute();
                if (OSSLog.isEnableLog()) {
                    Map<String, List<String>> multimap = execute.headers().toMultimap();
                    StringBuilder sb = new StringBuilder();
                    sb.append("response:---------------------\n");
                    sb.append("response code: " + execute.code() + " for url: " + request.url() + "\n");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("response body: ");
                    sb2.append(execute.body().toString());
                    sb2.append("\n");
                    sb.append(sb2.toString());
                    for (String str3 : multimap.keySet()) {
                        sb.append("responseHeader [" + str3 + "]: ");
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(multimap.get(str3).get(0));
                        sb3.append("\n");
                        sb.append(sb3.toString());
                    }
                    OSSLog.logDebug(sb.toString());
                }
                responseMessage = buildResponseMessage(this.message, execute);
                clientException = null;
            } catch (Exception e2) {
                e = e2;
                OSSLog.logError("Encounter local execpiton: " + e.toString());
                if (OSSLog.isEnableLog()) {
                    e.printStackTrace();
                }
                clientException = new ClientException(e.getMessage(), e);
                responseMessage = null;
                if (responseMessage != null) {
                }
                if (clientException != null) {
                }
                if (clientException == null) {
                }
                if (call != null) {
                    clientException = new ClientException("Task is cancelled!", clientException.getCause(), true);
                    shouldRetry = this.retryHandler.shouldRetry(clientException, this.currentRetryCount);
                    OSSLog.logError("[run] - retry, retry type: " + shouldRetry);
                    if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                    }
                }
                clientException = new ClientException("Task is cancelled!", clientException.getCause(), true);
                shouldRetry = this.retryHandler.shouldRetry(clientException, this.currentRetryCount);
                OSSLog.logError("[run] - retry, retry type: " + shouldRetry);
                if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
                }
            }
        } catch (Exception e3) {
            e = e3;
            call = null;
        }
        if (responseMessage != null) {
            try {
                DateUtil.setCurrentServerTime(DateUtil.parseRfc822Date((String) responseMessage.getHeaders().get("Date")).getTime());
            } catch (Exception unused) {
            }
        }
        if (clientException != null && (responseMessage.getStatusCode() == 203 || responseMessage.getStatusCode() >= 300)) {
            clientException = ResponseParsers.parseResponseErrorXML(responseMessage, request.method().equals("HEAD"));
        } else if (clientException == null) {
            try {
                T parse = this.responseParser.parse(responseMessage);
                if (this.context.getCompletedCallback() != null) {
                    try {
                        this.context.getCompletedCallback().onSuccess(this.context.getRequest(), parse);
                    } catch (Exception unused2) {
                    }
                }
                return parse;
            } catch (IOException e4) {
                clientException = new ClientException(e4.getMessage(), e4);
            }
        }
        if ((call != null && call.isCanceled()) || this.context.getCancellationHandler().isCancelled()) {
            clientException = new ClientException("Task is cancelled!", clientException.getCause(), true);
        }
        shouldRetry = this.retryHandler.shouldRetry(clientException, this.currentRetryCount);
        OSSLog.logError("[run] - retry, retry type: " + shouldRetry);
        if (shouldRetry == OSSRetryType.OSSRetryTypeShouldRetry) {
            this.currentRetryCount++;
            if (this.context.getRetryCallback() != null) {
                this.context.getRetryCallback().onRetryCallback();
            }
            return call();
        }
        if (shouldRetry == OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry) {
            if (responseMessage != null) {
                this.message.getHeaders().put("Date", responseMessage.getHeaders().get("Date"));
            }
            this.currentRetryCount++;
            if (this.context.getRetryCallback() != null) {
                this.context.getRetryCallback().onRetryCallback();
            }
            return call();
        }
        if (clientException instanceof ClientException) {
            if (this.context.getCompletedCallback() != null) {
                this.context.getCompletedCallback().onFailure(this.context.getRequest(), (ClientException) clientException, null);
                throw clientException;
            }
            throw clientException;
        }
        if (this.context.getCompletedCallback() != null) {
            this.context.getCompletedCallback().onFailure(this.context.getRequest(), null, (ServiceException) clientException);
            throw clientException;
        }
        throw clientException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.alibaba.sdk.android.oss.network.OSSRequestTask$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C08291 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod = iArr;
            try {
                iArr[HttpMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.GET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.HEAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod[HttpMethod.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private ResponseMessage buildResponseMessage(RequestMessage requestMessage, Response response) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setRequest(requestMessage);
        responseMessage.setResponse(response);
        HashMap hashMap = new HashMap();
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            hashMap.put(headers.name(i), headers.value(i));
        }
        responseMessage.setHeaders(hashMap);
        responseMessage.setStatusCode(response.code());
        responseMessage.setContentLength(response.body().get$contentLength());
        responseMessage.setContent(response.body().byteStream());
        return responseMessage;
    }
}
