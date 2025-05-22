package io.netty.handler.codec.socksx.p075v4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.NetUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Socks4ClientDecoder extends ReplayingDecoder<State> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
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
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socksx.v4.Socks4ClientDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72701 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ClientDecoder$State */
        static final /* synthetic */ int[] f8532x6ab6e36e;

        static {
            int[] iArr = new int[State.values().length];
            f8532x6ab6e36e = iArr;
            try {
                iArr[State.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8532x6ab6e36e[State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8532x6ab6e36e[State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        try {
            int i = C72701.f8532x6ab6e36e[state().ordinal()];
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
