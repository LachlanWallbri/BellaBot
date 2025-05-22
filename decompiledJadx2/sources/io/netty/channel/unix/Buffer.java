package io.netty.channel.unix;

import io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public final class Buffer {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static native int addressSize0();

    private static native long memoryAddress0(ByteBuffer byteBuffer);

    private Buffer() {
    }

    public static void free(ByteBuffer byteBuffer) {
        PlatformDependent.freeDirectBuffer(byteBuffer);
    }

    public static ByteBuffer allocateDirectWithNativeOrder(int i) {
        return ByteBuffer.allocateDirect(i).order(PlatformDependent.BIG_ENDIAN_NATIVE_ORDER ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
    }

    public static long memoryAddress(ByteBuffer byteBuffer) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.directBufferAddress(byteBuffer);
        }
        return memoryAddress0(byteBuffer);
    }

    public static int addressSize() {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.addressSize();
        }
        return addressSize0();
    }
}
