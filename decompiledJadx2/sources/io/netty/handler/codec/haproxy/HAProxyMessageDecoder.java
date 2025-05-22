package io.netty.handler.codec.haproxy;

import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ProtocolDetectionResult;
import io.netty.util.CharsetUtil;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class HAProxyMessageDecoder extends ByteToMessageDecoder {
    private static final byte[] BINARY_PREFIX;
    private static final int BINARY_PREFIX_LENGTH;
    private static final int DELIMITER_LENGTH = 2;
    private static final ProtocolDetectionResult<HAProxyProtocolVersion> DETECTION_RESULT_V1;
    private static final ProtocolDetectionResult<HAProxyProtocolVersion> DETECTION_RESULT_V2;
    private static final byte[] TEXT_PREFIX;
    private static final int V1_MAX_LENGTH = 108;
    private static final int V2_MAX_LENGTH = 65551;
    private static final int V2_MAX_TLV = 65319;
    private static final int V2_MIN_LENGTH = 232;
    private int discardedBytes;
    private boolean discarding;
    private boolean finished;
    private final int v2MaxHeaderSize;
    private int version;

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    public boolean isSingleDecode() {
        return true;
    }

    static {
        byte[] bArr = {13, 10, 13, 10, 0, 13, 10, 81, 85, PrinterUtils.BarCode.CODE128, 84, 10};
        BINARY_PREFIX = bArr;
        TEXT_PREFIX = new byte[]{80, 82, 79, TarConstants.LF_PAX_EXTENDED_HEADER_UC, ClassDefinitionUtils.OPS_dup};
        BINARY_PREFIX_LENGTH = bArr.length;
        DETECTION_RESULT_V1 = ProtocolDetectionResult.detected(HAProxyProtocolVersion.V1);
        DETECTION_RESULT_V2 = ProtocolDetectionResult.detected(HAProxyProtocolVersion.V2);
    }

    public HAProxyMessageDecoder() {
        this.version = -1;
        this.v2MaxHeaderSize = V2_MAX_LENGTH;
    }

    public HAProxyMessageDecoder(int i) {
        this.version = -1;
        if (i < 1) {
            this.v2MaxHeaderSize = V2_MIN_LENGTH;
            return;
        }
        if (i > V2_MAX_TLV) {
            this.v2MaxHeaderSize = V2_MAX_LENGTH;
            return;
        }
        int i2 = i + V2_MIN_LENGTH;
        if (i2 > V2_MAX_LENGTH) {
            this.v2MaxHeaderSize = V2_MAX_LENGTH;
        } else {
            this.v2MaxHeaderSize = i2;
        }
    }

    private static int findVersion(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() < 13) {
            return -1;
        }
        int readerIndex = byteBuf.readerIndex();
        if (match(BINARY_PREFIX, byteBuf, readerIndex)) {
            return byteBuf.getByte(readerIndex + BINARY_PREFIX_LENGTH);
        }
        return 1;
    }

    private static int findEndOfHeader(ByteBuf byteBuf) {
        int unsignedShort;
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes >= 16 && readableBytes >= (unsignedShort = byteBuf.getUnsignedShort(byteBuf.readerIndex() + 14) + 16)) {
            return unsignedShort;
        }
        return -1;
    }

    private static int findEndOfLine(ByteBuf byteBuf) {
        int writerIndex = byteBuf.writerIndex();
        for (int readerIndex = byteBuf.readerIndex(); readerIndex < writerIndex; readerIndex++) {
            if (byteBuf.getByte(readerIndex) == 13 && readerIndex < writerIndex - 1 && byteBuf.getByte(readerIndex + 1) == 10) {
                return readerIndex;
            }
        }
        return -1;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        super.channelRead(channelHandlerContext, obj);
        if (this.finished) {
            channelHandlerContext.pipeline().remove(this);
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected final void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        ByteBuf decodeStruct;
        if (this.version == -1) {
            int findVersion = findVersion(byteBuf);
            this.version = findVersion;
            if (findVersion == -1) {
                return;
            }
        }
        if (this.version == 1) {
            decodeStruct = decodeLine(channelHandlerContext, byteBuf);
        } else {
            decodeStruct = decodeStruct(channelHandlerContext, byteBuf);
        }
        if (decodeStruct != null) {
            this.finished = true;
            try {
                if (this.version == 1) {
                    list.add(HAProxyMessage.decodeHeader(decodeStruct.toString(CharsetUtil.US_ASCII)));
                } else {
                    list.add(HAProxyMessage.decodeHeader(decodeStruct));
                }
            } catch (HAProxyProtocolException e) {
                fail(channelHandlerContext, null, e);
            }
        }
    }

    private ByteBuf decodeStruct(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        int findEndOfHeader = findEndOfHeader(byteBuf);
        if (this.discarding) {
            if (findEndOfHeader >= 0) {
                byteBuf.readerIndex(findEndOfHeader);
                this.discardedBytes = 0;
                this.discarding = false;
            } else {
                int readableBytes = byteBuf.readableBytes();
                this.discardedBytes = readableBytes;
                byteBuf.skipBytes(readableBytes);
            }
            return null;
        }
        if (findEndOfHeader >= 0) {
            int readerIndex = findEndOfHeader - byteBuf.readerIndex();
            if (readerIndex > this.v2MaxHeaderSize) {
                byteBuf.readerIndex(findEndOfHeader);
                failOverLimit(channelHandlerContext, readerIndex);
                return null;
            }
            return byteBuf.readSlice(readerIndex);
        }
        int readableBytes2 = byteBuf.readableBytes();
        if (readableBytes2 > this.v2MaxHeaderSize) {
            this.discardedBytes = readableBytes2;
            byteBuf.skipBytes(readableBytes2);
            this.discarding = true;
            failOverLimit(channelHandlerContext, "over " + this.discardedBytes);
        }
        return null;
    }

    private ByteBuf decodeLine(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        int findEndOfLine = findEndOfLine(byteBuf);
        if (this.discarding) {
            if (findEndOfLine >= 0) {
                byteBuf.readerIndex(findEndOfLine + (byteBuf.getByte(findEndOfLine) == 13 ? 2 : 1));
                this.discardedBytes = 0;
                this.discarding = false;
            } else {
                int readableBytes = byteBuf.readableBytes();
                this.discardedBytes = readableBytes;
                byteBuf.skipBytes(readableBytes);
            }
            return null;
        }
        if (findEndOfLine >= 0) {
            int readerIndex = findEndOfLine - byteBuf.readerIndex();
            if (readerIndex > 108) {
                byteBuf.readerIndex(findEndOfLine + 2);
                failOverLimit(channelHandlerContext, readerIndex);
                return null;
            }
            ByteBuf readSlice = byteBuf.readSlice(readerIndex);
            byteBuf.skipBytes(2);
            return readSlice;
        }
        int readableBytes2 = byteBuf.readableBytes();
        if (readableBytes2 > 108) {
            this.discardedBytes = readableBytes2;
            byteBuf.skipBytes(readableBytes2);
            this.discarding = true;
            failOverLimit(channelHandlerContext, "over " + this.discardedBytes);
        }
        return null;
    }

    private void failOverLimit(ChannelHandlerContext channelHandlerContext, int i) {
        failOverLimit(channelHandlerContext, String.valueOf(i));
    }

    private void failOverLimit(ChannelHandlerContext channelHandlerContext, String str) {
        fail(channelHandlerContext, "header length (" + str + ") exceeds the allowed maximum (" + (this.version == 1 ? 108 : this.v2MaxHeaderSize) + ')', null);
    }

    private void fail(ChannelHandlerContext channelHandlerContext, String str, Exception exc) {
        this.finished = true;
        channelHandlerContext.close();
        if (str != null && exc != null) {
            throw new HAProxyProtocolException(str, exc);
        }
        if (str != null) {
            throw new HAProxyProtocolException(str);
        }
        if (exc != null) {
        }
    }

    public static ProtocolDetectionResult<HAProxyProtocolVersion> detectProtocol(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() < 12) {
            return ProtocolDetectionResult.needsMoreData();
        }
        int readerIndex = byteBuf.readerIndex();
        if (match(BINARY_PREFIX, byteBuf, readerIndex)) {
            return DETECTION_RESULT_V2;
        }
        if (match(TEXT_PREFIX, byteBuf, readerIndex)) {
            return DETECTION_RESULT_V1;
        }
        return ProtocolDetectionResult.invalid();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes7.dex */
    private abstract class HeaderExtractor {
        private final int maxHeaderSize;

        protected abstract int delimiterLength(ByteBuf byteBuf, int i);

        protected abstract int findEndOfHeader(ByteBuf byteBuf);

        protected HeaderExtractor(int i) {
            this.maxHeaderSize = i;
        }

        public ByteBuf extract(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            int findEndOfHeader = findEndOfHeader(byteBuf);
            if (HAProxyMessageDecoder.access$000(HAProxyMessageDecoder.this)) {
                if (findEndOfHeader >= 0) {
                    int access$200 = (HAProxyMessageDecoder.access$200(HAProxyMessageDecoder.this) + findEndOfHeader) - byteBuf.readerIndex();
                    byteBuf.readerIndex(findEndOfHeader + delimiterLength(byteBuf, findEndOfHeader));
                    HAProxyMessageDecoder.access$202(HAProxyMessageDecoder.this, 0);
                    HAProxyMessageDecoder.access$002(HAProxyMessageDecoder.this, false);
                    if (!HAProxyMessageDecoder.access$300(HAProxyMessageDecoder.this)) {
                        HAProxyMessageDecoder.access$400(HAProxyMessageDecoder.this, channelHandlerContext, "over " + access$200);
                    }
                } else {
                    HAProxyMessageDecoder hAProxyMessageDecoder = HAProxyMessageDecoder.this;
                    HAProxyMessageDecoder.access$202(hAProxyMessageDecoder, HAProxyMessageDecoder.access$200(hAProxyMessageDecoder) + byteBuf.readableBytes());
                    byteBuf.skipBytes(byteBuf.readableBytes());
                }
                return null;
            }
            if (findEndOfHeader >= 0) {
                int readerIndex = findEndOfHeader - byteBuf.readerIndex();
                if (readerIndex > this.maxHeaderSize) {
                    byteBuf.readerIndex(findEndOfHeader + delimiterLength(byteBuf, findEndOfHeader));
                    HAProxyMessageDecoder.access$100(HAProxyMessageDecoder.this, channelHandlerContext, readerIndex);
                    return null;
                }
                ByteBuf readSlice = byteBuf.readSlice(readerIndex);
                byteBuf.skipBytes(delimiterLength(byteBuf, findEndOfHeader));
                return readSlice;
            }
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes > this.maxHeaderSize) {
                HAProxyMessageDecoder.access$202(HAProxyMessageDecoder.this, readableBytes);
                byteBuf.skipBytes(readableBytes);
                HAProxyMessageDecoder.access$002(HAProxyMessageDecoder.this, true);
                if (HAProxyMessageDecoder.access$300(HAProxyMessageDecoder.this)) {
                    HAProxyMessageDecoder.access$400(HAProxyMessageDecoder.this, channelHandlerContext, "over " + HAProxyMessageDecoder.access$200(HAProxyMessageDecoder.this));
                }
            }
            return null;
        }
    }

    private static boolean match(byte[] bArr, ByteBuf byteBuf, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (byteBuf.getByte(i + i2) != bArr[i2]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes7.dex */
    private final class LineHeaderExtractor extends HeaderExtractor {
        LineHeaderExtractor(int i) {
            super(i);
        }

        @Override // io.netty.handler.codec.haproxy.HAProxyMessageDecoder.HeaderExtractor
        protected int findEndOfHeader(ByteBuf byteBuf) {
            return HAProxyMessageDecoder.access$500(byteBuf);
        }

        @Override // io.netty.handler.codec.haproxy.HAProxyMessageDecoder.HeaderExtractor
        protected int delimiterLength(ByteBuf byteBuf, int i) {
            return byteBuf.getByte(i) == 13 ? 2 : 1;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes7.dex */
    private final class StructHeaderExtractor extends HeaderExtractor {
        @Override // io.netty.handler.codec.haproxy.HAProxyMessageDecoder.HeaderExtractor
        protected int delimiterLength(ByteBuf byteBuf, int i) {
            return 0;
        }

        StructHeaderExtractor(int i) {
            super(i);
        }

        @Override // io.netty.handler.codec.haproxy.HAProxyMessageDecoder.HeaderExtractor
        protected int findEndOfHeader(ByteBuf byteBuf) {
            return HAProxyMessageDecoder.access$600(byteBuf);
        }
    }
}
