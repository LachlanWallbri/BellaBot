package com.aliyun.alink.p022h2.api;

import com.aliyun.alink.p022h2.entity.Http2Response;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class H2ClientException extends RuntimeException {
    private static final String FORMAT = "%s, code: %s, request id: %s, content: %s";
    private String content;
    private String errorCode;
    private String requestId;

    public H2ClientException(String str, String str2, String str3, String str4) {
        super(String.format(FORMAT, str, str3, str2, str4));
    }

    public H2ClientException(String str) {
        super(str);
    }

    public H2ClientException(String str, Throwable th) {
        super(str, th);
    }

    public H2ClientException(Throwable th) {
        super(th);
    }

    public H2ClientException(String str, Http2Response http2Response) {
        this(str, http2Response.getRequestId(), String.valueOf(http2Response.getStatus().code()), new String(http2Response.getContent()));
    }
}
