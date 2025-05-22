package io.grpc.netty.shaded.io.netty.internal.tcnative;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Buffer {
    public static native long address(ByteBuffer byteBuffer);

    public static native long size(ByteBuffer byteBuffer);

    private Buffer() {
    }
}
