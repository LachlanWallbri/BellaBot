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
public class SocksAuthRequestDecoder extends ReplayingDecoder<State> {
    private String username;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    enum State {
        CHECK_PROTOCOL_VERSION,
        READ_USERNAME,
        READ_PASSWORD
    }

    public SocksAuthRequestDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socks.SocksAuthRequestDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72611 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socks$SocksAuthRequestDecoder$State */
        static final /* synthetic */ int[] f8519xb63b32bc;

        static {
            int[] iArr = new int[State.values().length];
            f8519xb63b32bc = iArr;
            try {
                iArr[State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8519xb63b32bc[State.READ_USERNAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8519xb63b32bc[State.READ_PASSWORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i = C72611.f8519xb63b32bc[state().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new Error();
                }
                list.add(new SocksAuthRequest(this.username, SocksCommonUtils.readUsAscii(byteBuf, byteBuf.readByte())));
                channelHandlerContext.pipeline().remove(this);
            }
        } else {
            if (byteBuf.readByte() != SocksSubnegotiationVersion.AUTH_PASSWORD.byteValue()) {
                list.add(SocksCommonUtils.UNKNOWN_SOCKS_REQUEST);
                channelHandlerContext.pipeline().remove(this);
            }
            checkpoint(State.READ_USERNAME);
        }
        this.username = SocksCommonUtils.readUsAscii(byteBuf, byteBuf.readByte());
        checkpoint(State.READ_PASSWORD);
        list.add(new SocksAuthRequest(this.username, SocksCommonUtils.readUsAscii(byteBuf, byteBuf.readByte())));
        channelHandlerContext.pipeline().remove(this);
    }
}
