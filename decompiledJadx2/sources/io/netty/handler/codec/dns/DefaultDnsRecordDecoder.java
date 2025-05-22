package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.util.CharsetUtil;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class DefaultDnsRecordDecoder implements DnsRecordDecoder {
    static final String ROOT = ".";

    @Override // io.netty.handler.codec.dns.DnsRecordDecoder
    public final DnsQuestion decodeQuestion(ByteBuf byteBuf) throws Exception {
        return new DefaultDnsQuestion(decodeName(byteBuf), DnsRecordType.valueOf(byteBuf.readUnsignedShort()), byteBuf.readUnsignedShort());
    }

    @Override // io.netty.handler.codec.dns.DnsRecordDecoder
    public final <T extends DnsRecord> T decodeRecord(ByteBuf byteBuf) throws Exception {
        int readerIndex = byteBuf.readerIndex();
        String decodeName = decodeName(byteBuf);
        int writerIndex = byteBuf.writerIndex();
        if (writerIndex - readerIndex < 10) {
            byteBuf.readerIndex(readerIndex);
            return null;
        }
        DnsRecordType valueOf = DnsRecordType.valueOf(byteBuf.readUnsignedShort());
        int readUnsignedShort = byteBuf.readUnsignedShort();
        long readUnsignedInt = byteBuf.readUnsignedInt();
        int readUnsignedShort2 = byteBuf.readUnsignedShort();
        int readerIndex2 = byteBuf.readerIndex();
        if (writerIndex - readerIndex2 < readUnsignedShort2) {
            byteBuf.readerIndex(readerIndex);
            return null;
        }
        T t = (T) decodeRecord(decodeName, valueOf, readUnsignedShort, readUnsignedInt, byteBuf, readerIndex2, readUnsignedShort2);
        byteBuf.readerIndex(readerIndex2 + readUnsignedShort2);
        return t;
    }

    protected DnsRecord decodeRecord(String str, DnsRecordType dnsRecordType, int i, long j, ByteBuf byteBuf, int i2, int i3) throws Exception {
        if (dnsRecordType == DnsRecordType.PTR) {
            return new DefaultDnsPtrRecord(str, i, j, decodeName0(byteBuf.duplicate().setIndex(i2, i2 + i3)));
        }
        return new DefaultDnsRawRecord(str, dnsRecordType, i, j, byteBuf.retainedDuplicate().setIndex(i2, i2 + i3));
    }

    protected String decodeName0(ByteBuf byteBuf) {
        return decodeName(byteBuf);
    }

    public static String decodeName(ByteBuf byteBuf) {
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
}
