package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DeflateInputStreamFactory implements InputStreamFactory {
    private static final DeflateInputStreamFactory INSTANCE = new DeflateInputStreamFactory();

    public static DeflateInputStreamFactory getInstance() {
        return INSTANCE;
    }

    @Override // org.apache.http.client.entity.InputStreamFactory
    public InputStream create(InputStream inputStream) throws IOException {
        return new DeflateInputStream(inputStream);
    }
}
