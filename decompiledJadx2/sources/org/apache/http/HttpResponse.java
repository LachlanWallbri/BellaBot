package org.apache.http;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface HttpResponse extends HttpMessage {
    HttpEntity getEntity();

    Locale getLocale();

    StatusLine getStatusLine();

    void setEntity(HttpEntity httpEntity);

    void setLocale(Locale locale);

    void setReasonPhrase(String str) throws IllegalStateException;

    void setStatusCode(int i) throws IllegalStateException;

    void setStatusLine(ProtocolVersion protocolVersion, int i);

    void setStatusLine(ProtocolVersion protocolVersion, int i, String str);

    void setStatusLine(StatusLine statusLine);
}
