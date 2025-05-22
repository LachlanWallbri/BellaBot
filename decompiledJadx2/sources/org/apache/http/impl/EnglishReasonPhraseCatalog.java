package org.apache.http.impl;

import java.util.Locale;
import org.apache.http.HttpStatus;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.util.Args;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class EnglishReasonPhraseCatalog implements ReasonPhraseCatalog {
    public static final EnglishReasonPhraseCatalog INSTANCE = new EnglishReasonPhraseCatalog();
    private static final String[][] REASON_PHRASES = {null, new String[3], new String[8], new String[8], new String[25], new String[8]};

    static {
        setReason(200, "OK");
        setReason(201, "Created");
        setReason(202, "Accepted");
        setReason(204, "No Content");
        setReason(301, "Moved Permanently");
        setReason(302, "Moved Temporarily");
        setReason(304, "Not Modified");
        setReason(400, "Bad Request");
        setReason(401, "Unauthorized");
        setReason(403, "Forbidden");
        setReason(404, "Not Found");
        setReason(500, "Internal Server Error");
        setReason(501, "Not Implemented");
        setReason(502, "Bad Gateway");
        setReason(503, "Service Unavailable");
        setReason(100, "Continue");
        setReason(307, "Temporary Redirect");
        setReason(405, "Method Not Allowed");
        setReason(409, "Conflict");
        setReason(412, "Precondition Failed");
        setReason(HttpStatus.SC_REQUEST_TOO_LONG, "Request Too Long");
        setReason(HttpStatus.SC_REQUEST_URI_TOO_LONG, "Request-URI Too Long");
        setReason(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
        setReason(300, "Multiple Choices");
        setReason(303, "See Other");
        setReason(305, "Use Proxy");
        setReason(402, "Payment Required");
        setReason(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable");
        setReason(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required");
        setReason(HttpStatus.SC_REQUEST_TIMEOUT, "Request Timeout");
        setReason(101, "Switching Protocols");
        setReason(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, "Non Authoritative Information");
        setReason(HttpStatus.SC_RESET_CONTENT, "Reset Content");
        setReason(HttpStatus.SC_PARTIAL_CONTENT, "Partial Content");
        setReason(504, "Gateway Timeout");
        setReason(505, "Http Version Not Supported");
        setReason(HttpStatus.SC_GONE, "Gone");
        setReason(HttpStatus.SC_LENGTH_REQUIRED, "Length Required");
        setReason(416, "Requested Range Not Satisfiable");
        setReason(HttpStatus.SC_EXPECTATION_FAILED, "Expectation Failed");
        setReason(102, "Processing");
        setReason(HttpStatus.SC_MULTI_STATUS, "Multi-Status");
        setReason(422, "Unprocessable Entity");
        setReason(HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, "Insufficient Space On Resource");
        setReason(420, "Method Failure");
        setReason(423, "Locked");
        setReason(507, "Insufficient Storage");
        setReason(424, "Failed Dependency");
    }

    protected EnglishReasonPhraseCatalog() {
    }

    @Override // org.apache.http.ReasonPhraseCatalog
    public String getReason(int i, Locale locale) {
        Args.check(i >= 100 && i < 600, "Unknown category for status code " + i);
        int i2 = i / 100;
        int i3 = i - (i2 * 100);
        String[][] strArr = REASON_PHRASES;
        if (strArr[i2].length > i3) {
            return strArr[i2][i3];
        }
        return null;
    }

    private static void setReason(int i, String str) {
        int i2 = i / 100;
        REASON_PHRASES[i2][i - (i2 * 100)] = str;
    }
}
