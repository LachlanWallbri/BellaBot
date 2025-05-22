package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface HttpEncoding {
    void encode(StreamingContent streamingContent, OutputStream outputStream) throws IOException;

    String getName();
}
