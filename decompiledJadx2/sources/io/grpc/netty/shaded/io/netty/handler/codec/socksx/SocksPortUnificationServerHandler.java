package io.grpc.netty.shaded.io.netty.handler.codec.socksx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4ServerDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4.Socks4ServerEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5InitialRequestDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5ServerEncoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SocksPortUnificationServerHandler extends ByteToMessageDecoder {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SocksPortUnificationServerHandler.class);
    private final Socks5ServerEncoder socks5encoder;

    public SocksPortUnificationServerHandler() {
        this(Socks5ServerEncoder.DEFAULT);
    }

    public SocksPortUnificationServerHandler(Socks5ServerEncoder socks5ServerEncoder) {
        this.socks5encoder = (Socks5ServerEncoder) ObjectUtil.checkNotNull(socks5ServerEncoder, "socks5encoder");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readerIndex = byteBuf.readerIndex();
        if (byteBuf.writerIndex() == readerIndex) {
            return;
        }
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        byte b = byteBuf.getByte(readerIndex);
        SocksVersion valueOf = SocksVersion.valueOf(b);
        int i = C65851.$SwitchMap$io$netty$handler$codec$socksx$SocksVersion[valueOf.ordinal()];
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
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksPortUnificationServerHandler$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C65851 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$socksx$SocksVersion = new int[SocksVersion.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$socksx$SocksVersion[SocksVersion.SOCKS4a.ordinal()] = 1;
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
