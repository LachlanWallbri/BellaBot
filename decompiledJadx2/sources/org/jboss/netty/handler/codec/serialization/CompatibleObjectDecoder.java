package org.jboss.netty.handler.codec.serialization;

import java.io.InputStream;
import java.io.ObjectInputStream;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;

@Deprecated
/* loaded from: classes7.dex */
public class CompatibleObjectDecoder extends ReplayingDecoder<CompatibleObjectDecoderState> {
    private final SwitchableInputStream bin;
    private ObjectInputStream oin;

    public CompatibleObjectDecoder() {
        super(CompatibleObjectDecoderState.READ_HEADER);
        this.bin = new SwitchableInputStream();
    }

    protected ObjectInputStream newObjectInputStream(InputStream inputStream) throws Exception {
        return new ObjectInputStream(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.handler.codec.serialization.CompatibleObjectDecoder$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C87141 {

        /* renamed from: $SwitchMap$org$jboss$netty$handler$codec$serialization$CompatibleObjectDecoderState */
        static final /* synthetic */ int[] f10038xbdda96cb = new int[CompatibleObjectDecoderState.values().length];

        static {
            try {
                f10038xbdda96cb[CompatibleObjectDecoderState.READ_HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10038xbdda96cb[CompatibleObjectDecoderState.READ_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, CompatibleObjectDecoderState compatibleObjectDecoderState) throws Exception {
        this.bin.switchStream(new ChannelBufferInputStream(channelBuffer));
        int i = C87141.f10038xbdda96cb[compatibleObjectDecoderState.ordinal()];
        if (i == 1) {
            this.oin = newObjectInputStream(this.bin);
            checkpoint(CompatibleObjectDecoderState.READ_OBJECT);
        } else if (i != 2) {
            throw new IllegalStateException("Unknown state: " + compatibleObjectDecoderState);
        }
        return this.oin.readObject();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decodeLast(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, CompatibleObjectDecoderState compatibleObjectDecoderState) throws Exception {
        int readableBytes = channelBuffer.readableBytes();
        if (readableBytes == 0) {
            return null;
        }
        if (readableBytes == 1 && channelBuffer.getByte(channelBuffer.readerIndex()) == 121) {
            channelBuffer.skipBytes(1);
            this.oin.close();
            return null;
        }
        Object decode = decode(channelHandlerContext, channel, channelBuffer, compatibleObjectDecoderState);
        this.oin.close();
        return decode;
    }
}
