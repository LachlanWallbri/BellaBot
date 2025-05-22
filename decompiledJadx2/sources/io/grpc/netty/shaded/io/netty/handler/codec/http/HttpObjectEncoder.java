package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.FileRegion;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class HttpObjectEncoder<H extends HttpMessage> extends MessageToMessageEncoder<Object> {
    static final int CRLF_SHORT = 3338;
    private static final float HEADERS_WEIGHT_HISTORICAL = 0.8f;
    private static final float HEADERS_WEIGHT_NEW = 0.2f;
    private static final int ST_CONTENT_ALWAYS_EMPTY = 3;
    private static final int ST_CONTENT_CHUNK = 2;
    private static final int ST_CONTENT_NON_CHUNK = 1;
    private static final int ST_INIT = 0;
    private static final float TRAILERS_WEIGHT_HISTORICAL = 0.8f;
    private static final float TRAILERS_WEIGHT_NEW = 0.2f;
    private static final int ZERO_CRLF_MEDIUM = 3149066;
    private static final byte[] ZERO_CRLF_CRLF = {TarConstants.LF_NORMAL, 13, 10, 13, 10};
    private static final ByteBuf CRLF_BUF = Unpooled.unreleasableBuffer(Unpooled.directBuffer(2).writeByte(13).writeByte(10));
    private static final ByteBuf ZERO_CRLF_CRLF_BUF = Unpooled.unreleasableBuffer(Unpooled.directBuffer(ZERO_CRLF_CRLF.length).writeBytes(ZERO_CRLF_CRLF));
    private int state = 0;
    private float headersEncodedSizeAccumulator = 256.0f;
    private float trailersEncodedSizeAccumulator = 256.0f;

    protected abstract void encodeInitialLine(ByteBuf byteBuf, H h) throws Exception;

    protected boolean isContentAlwaysEmpty(H h) {
        return false;
    }

    protected void sanitizeHeadersBeforeEncode(H h, boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010a  */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void encode(ChannelHandlerContext channelHandlerContext, Object obj, List<Object> list) throws Exception {
        ByteBuf byteBuf;
        int i;
        if (!(obj instanceof HttpMessage)) {
            byteBuf = null;
        } else {
            if (this.state != 0) {
                throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(obj) + ", state: " + this.state);
            }
            HttpMessage httpMessage = (HttpMessage) obj;
            byteBuf = channelHandlerContext.alloc().buffer((int) this.headersEncodedSizeAccumulator);
            encodeInitialLine(byteBuf, httpMessage);
            if (isContentAlwaysEmpty(httpMessage)) {
                i = 3;
            } else {
                i = HttpUtil.isTransferEncodingChunked(httpMessage) ? 2 : 1;
            }
            this.state = i;
            sanitizeHeadersBeforeEncode(httpMessage, this.state == 3);
            encodeHeaders(httpMessage.headers(), byteBuf);
            ByteBufUtil.writeShortBE(byteBuf, CRLF_SHORT);
            this.headersEncodedSizeAccumulator = (padSizeForAccumulation(byteBuf.readableBytes()) * 0.2f) + (this.headersEncodedSizeAccumulator * 0.8f);
        }
        boolean z = obj instanceof ByteBuf;
        if (z) {
            ByteBuf byteBuf2 = (ByteBuf) obj;
            if (!byteBuf2.isReadable()) {
                list.add(byteBuf2.retain());
                return;
            }
        }
        boolean z2 = obj instanceof HttpContent;
        if (!z2 && !z && !(obj instanceof FileRegion)) {
            if (byteBuf != null) {
                list.add(byteBuf);
                return;
            }
            return;
        }
        int i2 = this.state;
        if (i2 == 0) {
            throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(obj));
        }
        if (i2 == 1) {
            long contentLength = contentLength(obj);
            if (contentLength > 0) {
                if (byteBuf != null && byteBuf.writableBytes() >= contentLength && z2) {
                    byteBuf.writeBytes(((HttpContent) obj).content());
                    list.add(byteBuf);
                } else {
                    if (byteBuf != null) {
                        list.add(byteBuf);
                    }
                    list.add(encodeAndRetain(obj));
                }
                if (obj instanceof LastHttpContent) {
                    this.state = 0;
                }
                if (obj instanceof LastHttpContent) {
                }
            }
            if (byteBuf == null) {
            }
            if (obj instanceof LastHttpContent) {
            }
        } else if (i2 == 2) {
            if (byteBuf != null) {
                list.add(byteBuf);
            }
            encodeChunkedContent(channelHandlerContext, obj, contentLength(obj), list);
            if (obj instanceof LastHttpContent) {
            }
        } else {
            if (i2 != 3) {
                throw new Error();
            }
            if (byteBuf == null) {
                list.add(byteBuf);
            } else {
                list.add(Unpooled.EMPTY_BUFFER);
            }
            if (obj instanceof LastHttpContent) {
                return;
            }
            this.state = 0;
        }
    }

    protected void encodeHeaders(HttpHeaders httpHeaders, ByteBuf byteBuf) {
        Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence = httpHeaders.iteratorCharSequence();
        while (iteratorCharSequence.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = iteratorCharSequence.next();
            HttpHeadersEncoder.encoderHeader(next.getKey(), next.getValue(), byteBuf);
        }
    }

    private void encodeChunkedContent(ChannelHandlerContext channelHandlerContext, Object obj, long j, List<Object> list) {
        if (j > 0) {
            String hexString = Long.toHexString(j);
            ByteBuf buffer = channelHandlerContext.alloc().buffer(hexString.length() + 2);
            buffer.writeCharSequence(hexString, CharsetUtil.US_ASCII);
            ByteBufUtil.writeShortBE(buffer, CRLF_SHORT);
            list.add(buffer);
            list.add(encodeAndRetain(obj));
            list.add(CRLF_BUF.duplicate());
        }
        if (!(obj instanceof LastHttpContent)) {
            if (j == 0) {
                list.add(encodeAndRetain(obj));
                return;
            }
            return;
        }
        HttpHeaders trailingHeaders = ((LastHttpContent) obj).trailingHeaders();
        if (trailingHeaders.isEmpty()) {
            list.add(ZERO_CRLF_CRLF_BUF.duplicate());
            return;
        }
        ByteBuf buffer2 = channelHandlerContext.alloc().buffer((int) this.trailersEncodedSizeAccumulator);
        ByteBufUtil.writeMediumBE(buffer2, ZERO_CRLF_MEDIUM);
        encodeHeaders(trailingHeaders, buffer2);
        ByteBufUtil.writeShortBE(buffer2, CRLF_SHORT);
        this.trailersEncodedSizeAccumulator = (padSizeForAccumulation(buffer2.readableBytes()) * 0.2f) + (this.trailersEncodedSizeAccumulator * 0.8f);
        list.add(buffer2);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageEncoder
    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return (obj instanceof HttpObject) || (obj instanceof ByteBuf) || (obj instanceof FileRegion);
    }

    private static Object encodeAndRetain(Object obj) {
        if (obj instanceof ByteBuf) {
            return ((ByteBuf) obj).retain();
        }
        if (obj instanceof HttpContent) {
            return ((HttpContent) obj).content().retain();
        }
        if (obj instanceof FileRegion) {
            return ((FileRegion) obj).retain();
        }
        throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(obj));
    }

    private static long contentLength(Object obj) {
        if (obj instanceof HttpContent) {
            return ((HttpContent) obj).content().readableBytes();
        }
        if (obj instanceof ByteBuf) {
            return ((ByteBuf) obj).readableBytes();
        }
        if (obj instanceof FileRegion) {
            return ((FileRegion) obj).count();
        }
        throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(obj));
    }

    private static int padSizeForAccumulation(int i) {
        return (i << 2) / 3;
    }

    @Deprecated
    protected static void encodeAscii(String str, ByteBuf byteBuf) {
        byteBuf.writeCharSequence(str, CharsetUtil.US_ASCII);
    }
}
