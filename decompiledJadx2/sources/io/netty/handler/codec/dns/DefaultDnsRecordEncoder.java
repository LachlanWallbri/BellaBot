package io.netty.handler.codec.dns;

import com.pudu.library.loracall.SlipConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class DefaultDnsRecordEncoder implements DnsRecordEncoder {
    private static final int PREFIX_MASK = 7;

    static int calculateEcsAddressLength(int i, int i2) {
        return (i >>> 3) + (i2 != 0 ? 1 : 0);
    }

    @Override // io.netty.handler.codec.dns.DnsRecordEncoder
    public final void encodeQuestion(DnsQuestion dnsQuestion, ByteBuf byteBuf) throws Exception {
        encodeName(dnsQuestion.name(), byteBuf);
        byteBuf.writeShort(dnsQuestion.type().intValue());
        byteBuf.writeShort(dnsQuestion.dnsClass());
    }

    @Override // io.netty.handler.codec.dns.DnsRecordEncoder
    public void encodeRecord(DnsRecord dnsRecord, ByteBuf byteBuf) throws Exception {
        if (dnsRecord instanceof DnsQuestion) {
            encodeQuestion((DnsQuestion) dnsRecord, byteBuf);
            return;
        }
        if (dnsRecord instanceof DnsPtrRecord) {
            encodePtrRecord((DnsPtrRecord) dnsRecord, byteBuf);
            return;
        }
        if (dnsRecord instanceof DnsOptEcsRecord) {
            encodeOptEcsRecord((DnsOptEcsRecord) dnsRecord, byteBuf);
        } else if (dnsRecord instanceof DnsOptPseudoRecord) {
            encodeOptPseudoRecord((DnsOptPseudoRecord) dnsRecord, byteBuf);
        } else {
            if (dnsRecord instanceof DnsRawRecord) {
                encodeRawRecord((DnsRawRecord) dnsRecord, byteBuf);
                return;
            }
            throw new UnsupportedMessageTypeException(StringUtil.simpleClassName(dnsRecord));
        }
    }

    private void encodeRecord0(DnsRecord dnsRecord, ByteBuf byteBuf) throws Exception {
        encodeName(dnsRecord.name(), byteBuf);
        byteBuf.writeShort(dnsRecord.type().intValue());
        byteBuf.writeShort(dnsRecord.dnsClass());
        byteBuf.writeInt((int) dnsRecord.timeToLive());
    }

    private void encodePtrRecord(DnsPtrRecord dnsPtrRecord, ByteBuf byteBuf) throws Exception {
        encodeRecord0(dnsPtrRecord, byteBuf);
        encodeName(dnsPtrRecord.hostname(), byteBuf);
    }

    private void encodeOptPseudoRecord(DnsOptPseudoRecord dnsOptPseudoRecord, ByteBuf byteBuf) throws Exception {
        encodeRecord0(dnsOptPseudoRecord, byteBuf);
        byteBuf.writeShort(0);
    }

    private void encodeOptEcsRecord(DnsOptEcsRecord dnsOptEcsRecord, ByteBuf byteBuf) throws Exception {
        encodeRecord0(dnsOptEcsRecord, byteBuf);
        int sourcePrefixLength = dnsOptEcsRecord.sourcePrefixLength();
        int scopePrefixLength = dnsOptEcsRecord.scopePrefixLength();
        int i = sourcePrefixLength & 7;
        byte[] address = dnsOptEcsRecord.address();
        int length = address.length << 3;
        if (length < sourcePrefixLength || sourcePrefixLength < 0) {
            throw new IllegalArgumentException(sourcePrefixLength + ": " + sourcePrefixLength + " (expected: 0 >= " + length + ')');
        }
        short addressNumber = (short) (address.length == 4 ? InternetProtocolFamily.IPv4 : InternetProtocolFamily.IPv6).addressNumber();
        int calculateEcsAddressLength = calculateEcsAddressLength(sourcePrefixLength, i);
        int i2 = calculateEcsAddressLength + 8;
        byteBuf.writeShort(i2);
        byteBuf.writeShort(8);
        byteBuf.writeShort(i2 - 4);
        byteBuf.writeShort(addressNumber);
        byteBuf.writeByte(sourcePrefixLength);
        byteBuf.writeByte(scopePrefixLength);
        if (i > 0) {
            int i3 = calculateEcsAddressLength - 1;
            byteBuf.writeBytes(address, 0, i3);
            byteBuf.writeByte(padWithZeros(address[i3], i));
            return;
        }
        byteBuf.writeBytes(address, 0, calculateEcsAddressLength);
    }

    private void encodeRawRecord(DnsRawRecord dnsRawRecord, ByteBuf byteBuf) throws Exception {
        encodeRecord0(dnsRawRecord, byteBuf);
        ByteBuf content = dnsRawRecord.content();
        int readableBytes = content.readableBytes();
        byteBuf.writeShort(readableBytes);
        byteBuf.writeBytes(content, content.readerIndex(), readableBytes);
    }

    protected void encodeName(String str, ByteBuf byteBuf) throws Exception {
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

    private static byte padWithZeros(byte b, int i) {
        int i2;
        switch (i) {
            case 0:
                return (byte) 0;
            case 1:
                i2 = b & 128;
                break;
            case 2:
                i2 = b & SlipConfig.END;
                break;
            case 3:
                i2 = b & 224;
                break;
            case 4:
                i2 = b & 240;
                break;
            case 5:
                i2 = b & 248;
                break;
            case 6:
                i2 = b & 252;
                break;
            case 7:
                i2 = b & 254;
                break;
            case 8:
                return b;
            default:
                throw new IllegalArgumentException("lowOrderBitsToPreserve: " + i);
        }
        return (byte) i2;
    }
}
