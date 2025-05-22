package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
abstract class CoderBase {
    private final Class<?>[] acceptableOptions;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract InputStream decode(String str, InputStream inputStream, long j, Coder coder, byte[] bArr, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getOptionsFromCoder(Coder coder, InputStream inputStream) throws IOException {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoderBase(Class<?>... clsArr) {
        this.acceptableOptions = clsArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean canAcceptOptions(Object obj) {
        for (Class<?> cls : this.acceptableOptions) {
            if (cls.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getOptionsAsProperties(Object obj) throws IOException {
        return ByteUtils.EMPTY_BYTE_ARRAY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStream encode(OutputStream outputStream, Object obj) throws IOException {
        throw new UnsupportedOperationException("Method doesn't support writing");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int numberOptionOrDefault(Object obj, int i) {
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }
}
