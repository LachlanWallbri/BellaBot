package org.apache.http.entity;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface ContentProducer {
    void writeTo(OutputStream outputStream) throws IOException;
}
