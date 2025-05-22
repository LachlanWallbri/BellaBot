package io.netty.handler.codec.socksx.p076v5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Socks5PasswordAuthRequestDecoder extends ReplayingDecoder<State> {

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

    public Socks5PasswordAuthRequestDecoder() {
        super(State.INIT);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socksx.v5.Socks5PasswordAuthRequestDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72781 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socksx$v5$Socks5PasswordAuthRequestDecoder$State */
        static final /* synthetic */ int[] f8538xa51101bd;

        static {
            int[] iArr = new int[State.values().length];
            f8538xa51101bd = iArr;
            try {
                iArr[State.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8538xa51101bd[State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8538xa51101bd[State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        try {
            int i = C72781.f8538xa51101bd[state().ordinal()];
            if (i == 1) {
                int readerIndex = byteBuf.readerIndex();
                byte b = byteBuf.getByte(readerIndex);
                if (b != 1) {
                    throw new DecoderException("unsupported subnegotiation version: " + ((int) b) + " (expected: 1)");
                }
                short unsignedByte = byteBuf.getUnsignedByte(readerIndex + 1);
                int i2 = readerIndex + 2;
                short unsignedByte2 = byteBuf.getUnsignedByte(i2 + unsignedByte);
                byteBuf.skipBytes(unsignedByte + unsignedByte2 + 3);
                list.add(new DefaultSocks5PasswordAuthRequest(byteBuf.toString(i2, unsignedByte, CharsetUtil.US_ASCII), byteBuf.toString(readerIndex + 3 + unsignedByte, unsignedByte2, CharsetUtil.US_ASCII)));
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
        DefaultSocks5PasswordAuthRequest defaultSocks5PasswordAuthRequest = new DefaultSocks5PasswordAuthRequest("", "");
        defaultSocks5PasswordAuthRequest.setDecoderResult(DecoderResult.failure(exc));
        list.add(defaultSocks5PasswordAuthRequest);
    }
}
