package io.grpc.netty.shaded.io.netty.buffer.search;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractSearchProcessorFactory implements SearchProcessorFactory {
    public static KmpSearchProcessorFactory newKmpSearchProcessorFactory(byte[] bArr) {
        return new KmpSearchProcessorFactory(bArr);
    }

    public static BitapSearchProcessorFactory newBitapSearchProcessorFactory(byte[] bArr) {
        return new BitapSearchProcessorFactory(bArr);
    }
}
