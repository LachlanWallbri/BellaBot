package org.apache.commons.io.input;

import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class InfiniteCircularInputStream extends InputStream {
    private int position = -1;
    private final byte[] repeatedContent;

    public InfiniteCircularInputStream(byte[] bArr) {
        this.repeatedContent = bArr;
    }

    @Override // java.io.InputStream
    public int read() {
        int i = this.position + 1;
        byte[] bArr = this.repeatedContent;
        int length = i % bArr.length;
        this.position = length;
        return bArr[length] & 255;
    }
}
