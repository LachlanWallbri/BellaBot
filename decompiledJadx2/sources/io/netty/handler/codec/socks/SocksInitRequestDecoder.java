package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SocksInitRequestDecoder extends ReplayingDecoder<State> {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    enum State {
        CHECK_PROTOCOL_VERSION,
        READ_AUTH_SCHEMES
    }

    public SocksInitRequestDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socks.SocksInitRequestDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72671 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socks$SocksInitRequestDecoder$State */
        static final /* synthetic */ int[] f8527x5e58f624;

        static {
            int[] iArr = new int[State.values().length];
            f8527x5e58f624 = iArr;
            try {
                iArr[State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8527x5e58f624[State.READ_AUTH_SCHEMES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        List emptyList;
        int i = C72671.f8527x5e58f624[state().ordinal()];
        if (i == 1) {
            if (byteBuf.readByte() != SocksProtocolVersion.SOCKS5.byteValue()) {
                list.add(SocksCommonUtils.UNKNOWN_SOCKS_REQUEST);
                channelHandlerContext.pipeline().remove(this);
            }
            checkpoint(State.READ_AUTH_SCHEMES);
        } else if (i != 2) {
            throw new Error();
        }
        byte readByte = byteBuf.readByte();
        if (readByte > 0) {
            emptyList = new ArrayList(readByte);
            for (int i2 = 0; i2 < readByte; i2++) {
                emptyList.add(SocksAuthScheme.valueOf(byteBuf.readByte()));
            }
        } else {
            emptyList = Collections.emptyList();
        }
        list.add(new SocksInitRequest(emptyList));
        channelHandlerContext.pipeline().remove(this);
    }
}
