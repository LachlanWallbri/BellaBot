package io.netty.handler.codec.socksx.p076v5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.socksx.SocksVersion;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Socks5CommandResponseDecoder extends ReplayingDecoder<State> {
    private final Socks5AddressDecoder addressDecoder;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum State {
        INIT,
        SUCCESS,
        FAILURE
    }

    public Socks5CommandResponseDecoder() {
        this(Socks5AddressDecoder.DEFAULT);
    }

    public Socks5CommandResponseDecoder(Socks5AddressDecoder socks5AddressDecoder) {
        super(State.INIT);
        if (socks5AddressDecoder == null) {
            throw new NullPointerException("addressDecoder");
        }
        this.addressDecoder = socks5AddressDecoder;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socksx.v5.Socks5CommandResponseDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72751 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socksx$v5$Socks5CommandResponseDecoder$State */
        static final /* synthetic */ int[] f8535x620fff3d;

        static {
            int[] iArr = new int[State.values().length];
            f8535x620fff3d = iArr;
            try {
                iArr[State.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8535x620fff3d[State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8535x620fff3d[State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        try {
            int i = C72751.f8535x620fff3d[state().ordinal()];
            if (i == 1) {
                byte readByte = byteBuf.readByte();
                if (readByte != SocksVersion.SOCKS5.byteValue()) {
                    throw new DecoderException("unsupported version: " + ((int) readByte) + " (expected: " + ((int) SocksVersion.SOCKS5.byteValue()) + ')');
                }
                Socks5CommandStatus valueOf = Socks5CommandStatus.valueOf(byteBuf.readByte());
                byteBuf.skipBytes(1);
                Socks5AddressType valueOf2 = Socks5AddressType.valueOf(byteBuf.readByte());
                list.add(new DefaultSocks5CommandResponse(valueOf, valueOf2, this.addressDecoder.decodeAddress(valueOf2, byteBuf), byteBuf.readUnsignedShort()));
                checkpoint(State.SUCCESS);
            } else if (i != 2) {
                if (i != 3) {
                    return;
                }
                byteBuf.skipBytes(actualReadableBytes());
                return;
            }
            int actualReadableBytes = actualReadableBytes();
            if (actualReadableBytes > 0) {
                list.add(byteBuf.readRetainedSlice(actualReadableBytes));
            }
        } catch (Exception e) {
            fail(list, e);
        }
    }

    private void fail(List<Object> list, Exception exc) {
        if (!(exc instanceof DecoderException)) {
            exc = new DecoderException(exc);
        }
        checkpoint(State.FAILURE);
        DefaultSocks5CommandResponse defaultSocks5CommandResponse = new DefaultSocks5CommandResponse(Socks5CommandStatus.FAILURE, Socks5AddressType.IPv4, null, 0);
        defaultSocks5CommandResponse.setDecoderResult(DecoderResult.failure(exc));
        list.add(defaultSocks5CommandResponse);
    }
}
