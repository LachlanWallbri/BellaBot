package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttpObject implements HttpObject {
    private static final int HASH_CODE_PRIME = 31;
    private DecoderResult decoderResult = DecoderResult.SUCCESS;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.decoderResult;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObject
    @Deprecated
    public DecoderResult getDecoderResult() {
        return decoderResult();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult decoderResult) {
        this.decoderResult = (DecoderResult) ObjectUtil.checkNotNull(decoderResult, "decoderResult");
    }

    public int hashCode() {
        return 31 + this.decoderResult.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof DefaultHttpObject) {
            return decoderResult().equals(((DefaultHttpObject) obj).decoderResult());
        }
        return false;
    }
}
