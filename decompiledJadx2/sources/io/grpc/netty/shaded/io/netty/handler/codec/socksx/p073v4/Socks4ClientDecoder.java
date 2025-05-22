package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.ReplayingDecoder;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Socks4ClientDecoder extends ReplayingDecoder<State> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        START,
        SUCCESS,
        FAILURE
    }

    public Socks4ClientDecoder() {
        super(State.START);
        setSingleDecode(true);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.Socks4ClientDecoder$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C65861 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ClientDecoder$State */
        static final /* synthetic */ int[] f8399x6ab6e36e = new int[State.values().length];

        static {
            try {
                f8399x6ab6e36e[State.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8399x6ab6e36e[State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8399x6ab6e36e[State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        try {
            int i = C65861.f8399x6ab6e36e[state().ordinal()];
            if (i == 1) {
                short readUnsignedByte = byteBuf.readUnsignedByte();
                if (readUnsignedByte != 0) {
                    throw new DecoderException("unsupported reply version: " + ((int) readUnsignedByte) + " (expected: 0)");
                }
                list.add(new DefaultSocks4CommandResponse(Socks4CommandStatus.valueOf(byteBuf.readByte()), NetUtil.intToIpAddress(byteBuf.readInt()), byteBuf.readUnsignedShort()));
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
        DefaultSocks4CommandResponse defaultSocks4CommandResponse = new DefaultSocks4CommandResponse(Socks4CommandStatus.REJECTED_OR_FAILED);
        defaultSocks4CommandResponse.setDecoderResult(DecoderResult.failure(exc));
        list.add(defaultSocks4CommandResponse);
        checkpoint(State.FAILURE);
    }
}
