package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SocksAuthResponseDecoder extends ReplayingDecoder<State> {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    enum State {
        CHECK_PROTOCOL_VERSION,
        READ_AUTH_RESPONSE
    }

    public SocksAuthResponseDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socks.SocksAuthResponseDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72621 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socks$SocksAuthResponseDecoder$State */
        static final /* synthetic */ int[] f8520x1e903f96;

        static {
            int[] iArr = new int[State.values().length];
            f8520x1e903f96 = iArr;
            try {
                iArr[State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8520x1e903f96[State.READ_AUTH_RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i = C72621.f8520x1e903f96[state().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new Error();
            }
        } else {
            if (byteBuf.readByte() != SocksSubnegotiationVersion.AUTH_PASSWORD.byteValue()) {
                list.add(SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE);
                channelHandlerContext.pipeline().remove(this);
            }
            checkpoint(State.READ_AUTH_RESPONSE);
        }
        list.add(new SocksAuthResponse(SocksAuthStatus.valueOf(byteBuf.readByte())));
        channelHandlerContext.pipeline().remove(this);
    }
}
