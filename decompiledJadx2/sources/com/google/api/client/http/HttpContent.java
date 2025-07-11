package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface HttpContent extends StreamingContent {
    long getLength() throws IOException;

    String getType();

    boolean retrySupported();

    @Override // com.google.api.client.util.StreamingContent
    void writeTo(OutputStream outputStream) throws IOException;
}
