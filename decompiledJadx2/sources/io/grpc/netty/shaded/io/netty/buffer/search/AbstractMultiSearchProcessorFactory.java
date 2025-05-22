package io.grpc.netty.shaded.io.netty.buffer.search;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractMultiSearchProcessorFactory implements MultiSearchProcessorFactory {
    public static AhoCorasicSearchProcessorFactory newAhoCorasicSearchProcessorFactory(byte[]... bArr) {
        return new AhoCorasicSearchProcessorFactory(bArr);
    }
}
