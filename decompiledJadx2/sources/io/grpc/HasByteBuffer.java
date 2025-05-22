package io.grpc;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HasByteBuffer {
    boolean byteBufferSupported();

    @Nullable
    ByteBuffer getByteBuffer();
}
