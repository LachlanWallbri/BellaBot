package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import java.security.cert.X509Certificate;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
interface OpenSslKeyMaterial extends ReferenceCounted {
    X509Certificate[] certificateChain();

    long certificateChainAddress();

    long privateKeyAddress();

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    boolean release();

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    boolean release(int i);

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    OpenSslKeyMaterial retain();

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    OpenSslKeyMaterial retain(int i);

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    OpenSslKeyMaterial touch();

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    OpenSslKeyMaterial touch(Object obj);
}
