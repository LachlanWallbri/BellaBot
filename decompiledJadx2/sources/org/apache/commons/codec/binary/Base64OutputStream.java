package org.apache.commons.codec.binary;

import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Base64OutputStream extends BaseNCodecOutputStream {
    public Base64OutputStream(OutputStream outputStream) {
        this(outputStream, true);
    }

    public Base64OutputStream(OutputStream outputStream, boolean z) {
        super(outputStream, new Base64(false), z);
    }

    public Base64OutputStream(OutputStream outputStream, boolean z, int i, byte[] bArr) {
        super(outputStream, new Base64(i, bArr), z);
    }
}
