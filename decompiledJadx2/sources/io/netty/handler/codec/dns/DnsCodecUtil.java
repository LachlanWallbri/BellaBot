package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.util.CharsetUtil;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes7.dex */
final class DnsCodecUtil {
    private DnsCodecUtil() {
    }

    static void encodeDomainName(String str, ByteBuf byteBuf) {
        if (".".equals(str)) {
            byteBuf.writeByte(0);
            return;
        }
        for (String str2 : str.split("\\.")) {
            int length = str2.length();
            if (length == 0) {
                break;
            }
            byteBuf.writeByte(length);
            ByteBufUtil.writeAscii(byteBuf, str2);
        }
        byteBuf.writeByte(0);
    }

    static String decodeDomainName(ByteBuf byteBuf) {
        int writerIndex = byteBuf.writerIndex();
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            return ".";
        }
        StringBuilder sb = new StringBuilder(readableBytes << 1);
        int i = 0;
        int i2 = -1;
        while (byteBuf.isReadable()) {
            short readUnsignedByte = byteBuf.readUnsignedByte();
            if (!((readUnsignedByte & 192) == 192)) {
                if (readUnsignedByte == 0) {
                    break;
                }
                if (!byteBuf.isReadable(readUnsignedByte)) {
                    throw new CorruptedFrameException("truncated label in a name");
                }
                sb.append(byteBuf.toString(byteBuf.readerIndex(), readUnsignedByte, CharsetUtil.UTF_8));
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                byteBuf.skipBytes(readUnsignedByte);
            } else {
                if (i2 == -1) {
                    i2 = byteBuf.readerIndex() + 1;
                }
                if (!byteBuf.isReadable()) {
                    throw new CorruptedFrameException("truncated pointer in a name");
                }
                int readUnsignedByte2 = ((readUnsignedByte & 63) << 8) | byteBuf.readUnsignedByte();
                if (readUnsignedByte2 >= writerIndex) {
                    throw new CorruptedFrameException("name has an out-of-range pointer");
                }
                byteBuf.readerIndex(readUnsignedByte2);
                i += 2;
                if (i >= writerIndex) {
                    throw new CorruptedFrameException("name contains a loop.");
                }
            }
        }
        if (i2 != -1) {
            byteBuf.readerIndex(i2);
        }
        if (sb.length() == 0) {
            return ".";
        }
        if (sb.charAt(sb.length() - 1) != '.') {
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        }
        return sb.toString();
    }

    static ByteBuf decompressDomainName(ByteBuf byteBuf) {
        String decodeDomainName = decodeDomainName(byteBuf);
        ByteBuf buffer = byteBuf.alloc().buffer(decodeDomainName.length() << 1);
        encodeDomainName(decodeDomainName, buffer);
        return buffer;
    }
}
