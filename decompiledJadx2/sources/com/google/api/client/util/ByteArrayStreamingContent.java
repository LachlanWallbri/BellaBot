package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes2.dex */
public class ByteArrayStreamingContent implements StreamingContent {
    private final byte[] byteArray;
    private final int length;
    private final int offset;

    public ByteArrayStreamingContent(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ByteArrayStreamingContent(byte[] bArr, int i, int i2) {
        this.byteArray = (byte[]) Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(i >= 0 && i2 >= 0 && i + i2 <= bArr.length);
        this.offset = i;
        this.length = i2;
    }

    @Override // com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.byteArray, this.offset, this.length);
        outputStream.flush();
    }
}
