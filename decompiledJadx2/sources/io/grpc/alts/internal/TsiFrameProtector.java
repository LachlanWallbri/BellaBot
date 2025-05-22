package io.grpc.alts.internal;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import java.security.GeneralSecurityException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface TsiFrameProtector {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Consumer<T> {
        void accept(T t);
    }

    void destroy();

    void protectFlush(List<ByteBuf> list, Consumer<ByteBuf> consumer, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException;

    void unprotect(ByteBuf byteBuf, List<Object> list, ByteBufAllocator byteBufAllocator) throws GeneralSecurityException;
}
