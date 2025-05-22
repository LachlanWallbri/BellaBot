package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SSLSessionCache {
    long getSession(long j, byte[] bArr);

    boolean sessionCreated(long j, long j2);
}
