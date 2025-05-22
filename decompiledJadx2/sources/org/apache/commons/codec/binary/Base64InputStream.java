package org.apache.commons.codec.binary;

import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Base64InputStream extends BaseNCodecInputStream {
    public Base64InputStream(InputStream inputStream) {
        this(inputStream, false);
    }

    public Base64InputStream(InputStream inputStream, boolean z) {
        super(inputStream, new Base64(false), z);
    }

    public Base64InputStream(InputStream inputStream, boolean z, int i, byte[] bArr) {
        super(inputStream, new Base64(i, bArr), z);
    }
}
