package org.apache.http.client;

import org.apache.http.util.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class HttpResponseException extends ClientProtocolException {
    private static final long serialVersionUID = -7186627969477257933L;
    private final String reasonPhrase;
    private final int statusCode;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HttpResponseException(int i, String str) {
        super(String.format(r0.toString(), Integer.valueOf(i), str));
        StringBuilder sb = new StringBuilder();
        sb.append("status code: %d");
        sb.append(TextUtils.isBlank(str) ? "" : ", reason phrase: %s");
        this.statusCode = i;
        this.reasonPhrase = str;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
