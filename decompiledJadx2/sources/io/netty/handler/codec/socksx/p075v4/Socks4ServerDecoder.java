package io.netty.handler.codec.socksx.p075v4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.socksx.SocksVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Socks4ServerDecoder extends ReplayingDecoder<State> {
    private static final int MAX_FIELD_LENGTH = 255;
    private String dstAddr;
    private int dstPort;
    private Socks4CommandType type;
    private String userId;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum State {
        START,
        READ_USERID,
        READ_DOMAIN,
        SUCCESS,
        FAILURE
    }

    public Socks4ServerDecoder() {
        super(State.START);
        setSingleDecode(true);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socksx.v4.Socks4ServerDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72711 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$socksx$v4$Socks4ServerDecoder$State */
        static final /* synthetic */ int[] f8533xc9960af6;

        static {
            int[] iArr = new int[State.values().length];
            f8533xc9960af6 = iArr;
            try {
                iArr[State.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8533xc9960af6[State.READ_USERID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8533xc9960af6[State.READ_DOMAIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8533xc9960af6[State.SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8533xc9960af6[State.FAILURE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0097 A[Catch: Exception -> 0x00b6, TryCatch #0 {Exception -> 0x00b6, blocks: (B:2:0x0000, B:15:0x001f, B:17:0x0091, B:19:0x0097, B:22:0x0060, B:24:0x006a, B:26:0x0074, B:27:0x007c, B:28:0x0053, B:29:0x0028, B:31:0x0034, B:32:0x009f, B:33:0x00b5), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int actualReadableBytes;
        try {
            int i = C72711.f8533xc9960af6[state().ordinal()];
            if (i == 1) {
                short readUnsignedByte = byteBuf.readUnsignedByte();
                if (readUnsignedByte != SocksVersion.SOCKS4a.byteValue()) {
                    throw new DecoderException("unsupported protocol version: " + ((int) readUnsignedByte));
                }
                this.type = Socks4CommandType.valueOf(byteBuf.readByte());
                this.dstPort = byteBuf.readUnsignedShort();
                this.dstAddr = NetUtil.intToIpAddress(byteBuf.readInt());
                checkpoint(State.READ_USERID);
            } else if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                        byteBuf.skipBytes(actualReadableBytes());
                        return;
                    }
                    actualReadableBytes = actualReadableBytes();
                    if (actualReadableBytes > 0) {
                        list.add(byteBuf.readRetainedSlice(actualReadableBytes));
                        return;
                    }
                    return;
                }
                if (!"0.0.0.0".equals(this.dstAddr) && this.dstAddr.startsWith("0.0.0.")) {
                    this.dstAddr = readString("dstAddr", byteBuf);
                }
                list.add(new DefaultSocks4CommandRequest(this.type, this.dstAddr, this.dstPort, this.userId));
                checkpoint(State.SUCCESS);
                actualReadableBytes = actualReadableBytes();
                if (actualReadableBytes > 0) {
                }
            }
            this.userId = readString("userid", byteBuf);
            checkpoint(State.READ_DOMAIN);
            if (!"0.0.0.0".equals(this.dstAddr)) {
                this.dstAddr = readString("dstAddr", byteBuf);
            }
            list.add(new DefaultSocks4CommandRequest(this.type, this.dstAddr, this.dstPort, this.userId));
            checkpoint(State.SUCCESS);
            actualReadableBytes = actualReadableBytes();
            if (actualReadableBytes > 0) {
            }
        } catch (Exception e) {
            fail(list, e);
        }
    }

    private void fail(List<Object> list, Exception exc) {
        if (!(exc instanceof DecoderException)) {
            exc = new DecoderException(exc);
        }
        Socks4CommandType socks4CommandType = this.type;
        if (socks4CommandType == null) {
            socks4CommandType = Socks4CommandType.CONNECT;
        }
        String str = this.dstAddr;
        if (str == null) {
            str = "";
        }
        int i = this.dstPort;
        if (i == 0) {
            i = 65535;
        }
        String str2 = this.userId;
        DefaultSocks4CommandRequest defaultSocks4CommandRequest = new DefaultSocks4CommandRequest(socks4CommandType, str, i, str2 != null ? str2 : "");
        defaultSocks4CommandRequest.setDecoderResult(DecoderResult.failure(exc));
        list.add(defaultSocks4CommandRequest);
        checkpoint(State.FAILURE);
    }

    private static String readString(String str, ByteBuf byteBuf) {
        int bytesBefore = byteBuf.bytesBefore(256, (byte) 0);
        if (bytesBefore < 0) {
            throw new DecoderException("field '" + str + "' longer than 255 chars");
        }
        String byteBuf2 = byteBuf.readSlice(bytesBefore).toString(CharsetUtil.US_ASCII);
        byteBuf.skipBytes(1);
        return byteBuf2;
    }
}
