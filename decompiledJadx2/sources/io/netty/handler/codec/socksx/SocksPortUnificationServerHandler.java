package io.netty.handler.codec.socksx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.socksx.p075v4.Socks4ServerDecoder;
import io.netty.handler.codec.socksx.p075v4.Socks4ServerEncoder;
import io.netty.handler.codec.socksx.p076v5.Socks5InitialRequestDecoder;
import io.netty.handler.codec.socksx.p076v5.Socks5ServerEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SocksPortUnificationServerHandler extends ByteToMessageDecoder {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SocksPortUnificationServerHandler.class);
    private final Socks5ServerEncoder socks5encoder;

    public SocksPortUnificationServerHandler() {
        this(Socks5ServerEncoder.DEFAULT);
    }

    public SocksPortUnificationServerHandler(Socks5ServerEncoder socks5ServerEncoder) {
        if (socks5ServerEncoder == null) {
            throw new NullPointerException("socks5encoder");
        }
        this.socks5encoder = socks5ServerEncoder;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readerIndex = byteBuf.readerIndex();
        if (byteBuf.writerIndex() == readerIndex) {
            return;
        }
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        byte b = byteBuf.getByte(readerIndex);
        SocksVersion valueOf = SocksVersion.valueOf(b);
        int i = C72691.$SwitchMap$io$netty$handler$codec$socksx$SocksVersion[valueOf.ordinal()];
        if (i == 1) {
            logKnownVersion(channelHandlerContext, valueOf);
            pipeline.addAfter(channelHandlerContext.name(), null, Socks4ServerEncoder.INSTANCE);
            pipeline.addAfter(channelHandlerContext.name(), null, new Socks4ServerDecoder());
        } else if (i == 2) {
            logKnownVersion(channelHandlerContext, valueOf);
            pipeline.addAfter(channelHandlerContext.name(), null, this.socks5encoder);
            pipeline.addAfter(channelHandlerContext.name(), null, new Socks5InitialRequestDecoder());
        } else {
            logUnknownVersion(channelHandlerContext, b);
            byteBuf.skipBytes(byteBuf.readableBytes());
            channelHandlerContext.close();
            return;
        }
        pipeline.remove(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.socksx.SocksPortUnificationServerHandler$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72691 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$socksx$SocksVersion;

        static {
            int[] iArr = new int[SocksVersion.values().length];
            $SwitchMap$io$netty$handler$codec$socksx$SocksVersion = iArr;
            try {
                iArr[SocksVersion.SOCKS4a.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$socksx$SocksVersion[SocksVersion.SOCKS5.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static void logKnownVersion(ChannelHandlerContext channelHandlerContext, SocksVersion socksVersion) {
        logger.debug("{} Protocol version: {}({})", channelHandlerContext.channel(), socksVersion);
    }

    private static void logUnknownVersion(ChannelHandlerContext channelHandlerContext, byte b) {
        if (logger.isDebugEnabled()) {
            logger.debug("{} Unknown protocol version: {}", channelHandlerContext.channel(), Integer.valueOf(b & 255));
        }
    }
}
