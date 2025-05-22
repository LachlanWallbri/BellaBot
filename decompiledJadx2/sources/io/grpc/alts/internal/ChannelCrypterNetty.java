package io.grpc.alts.internal;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.security.GeneralSecurityException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelCrypterNetty {
    void decrypt(ByteBuf byteBuf, ByteBuf byteBuf2) throws GeneralSecurityException;

    void decrypt(ByteBuf byteBuf, ByteBuf byteBuf2, List<ByteBuf> list) throws GeneralSecurityException;

    void destroy();

    void encrypt(ByteBuf byteBuf, List<ByteBuf> list) throws GeneralSecurityException;

    int getSuffixLength();
}
