package io.minio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* loaded from: classes7.dex */
class ByteBufferStream extends ByteArrayOutputStream {
    public InputStream inputStream() {
        return new ByteArrayInputStream(this.buf, 0, this.count);
    }
}
