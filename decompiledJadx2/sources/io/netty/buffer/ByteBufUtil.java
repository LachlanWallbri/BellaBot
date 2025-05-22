package io.netty.buffer;

import androidx.core.view.ViewCompat;
import com.pudu.library.loracall.SlipConfig;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import io.netty.util.Recycler;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.anko.DimensionsKt;
import org.mozilla.javascript.typedarrays.Conversions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class ByteBufUtil {
    static final ByteBufAllocator DEFAULT_ALLOCATOR;
    private static final ByteProcessor FIND_NON_ASCII;
    private static final int MAX_CHAR_BUFFER_SIZE;
    private static final int THREAD_LOCAL_BUFFER_SIZE;
    static final int WRITE_CHUNK_SIZE = 8192;
    private static final byte WRITE_UTF_UNKNOWN = 63;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ByteBufUtil.class);
    private static final FastThreadLocal<CharBuffer> CHAR_BUFFERS = new FastThreadLocal<CharBuffer>() { // from class: io.netty.buffer.ByteBufUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.util.concurrent.FastThreadLocal
        public CharBuffer initialValue() throws Exception {
            return CharBuffer.allocate(1024);
        }
    };
    private static final int MAX_BYTES_PER_CHAR_UTF8 = (int) CharsetUtil.encoder(CharsetUtil.UTF_8).maxBytesPerChar();

    public static boolean ensureWritableSuccess(int i) {
        return i == 0 || i == 2;
    }

    public static int swapMedium(int i) {
        int i2 = ((i >>> 16) & 255) | ((i << 16) & 16711680) | (65280 & i);
        return (8388608 & i2) != 0 ? i2 | ViewCompat.MEASURED_STATE_MASK : i2;
    }

    static {
        ByteBufAllocator byteBufAllocator;
        String trim = SystemPropertyUtil.get("io.netty.allocator.type", PlatformDependent.isAndroid() ? "unpooled" : "pooled").toLowerCase(Locale.US).trim();
        if ("unpooled".equals(trim)) {
            byteBufAllocator = UnpooledByteBufAllocator.DEFAULT;
            logger.debug("-Dio.netty.allocator.type: {}", trim);
        } else if ("pooled".equals(trim)) {
            byteBufAllocator = PooledByteBufAllocator.DEFAULT;
            logger.debug("-Dio.netty.allocator.type: {}", trim);
        } else {
            byteBufAllocator = PooledByteBufAllocator.DEFAULT;
            logger.debug("-Dio.netty.allocator.type: pooled (unknown: {})", trim);
        }
        DEFAULT_ALLOCATOR = byteBufAllocator;
        int i = SystemPropertyUtil.getInt("io.netty.threadLocalDirectBufferSize", 0);
        THREAD_LOCAL_BUFFER_SIZE = i;
        logger.debug("-Dio.netty.threadLocalDirectBufferSize: {}", Integer.valueOf(i));
        int i2 = SystemPropertyUtil.getInt("io.netty.maxThreadLocalCharBufferSize", 16384);
        MAX_CHAR_BUFFER_SIZE = i2;
        logger.debug("-Dio.netty.maxThreadLocalCharBufferSize: {}", Integer.valueOf(i2));
        FIND_NON_ASCII = new ByteProcessor() { // from class: io.netty.buffer.ByteBufUtil.2
            @Override // io.netty.util.ByteProcessor
            public boolean process(byte b) {
                return b >= 0;
            }
        };
    }

    public static String hexDump(ByteBuf byteBuf) {
        return hexDump(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    public static String hexDump(ByteBuf byteBuf, int i, int i2) {
        return HexUtil.hexDump(byteBuf, i, i2);
    }

    public static String hexDump(byte[] bArr) {
        return hexDump(bArr, 0, bArr.length);
    }

    public static String hexDump(byte[] bArr, int i, int i2) {
        return HexUtil.hexDump(bArr, i, i2);
    }

    public static byte decodeHexByte(CharSequence charSequence, int i) {
        return StringUtil.decodeHexByte(charSequence, i);
    }

    public static byte[] decodeHexDump(CharSequence charSequence) {
        return StringUtil.decodeHexDump(charSequence, 0, charSequence.length());
    }

    public static byte[] decodeHexDump(CharSequence charSequence, int i, int i2) {
        return StringUtil.decodeHexDump(charSequence, i, i2);
    }

    public static int hashCode(ByteBuf byteBuf) {
        int i;
        int readableBytes = byteBuf.readableBytes();
        int i2 = readableBytes >>> 2;
        int i3 = readableBytes & 3;
        int readerIndex = byteBuf.readerIndex();
        if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
            i = 1;
            while (i2 > 0) {
                i = (i * 31) + byteBuf.getInt(readerIndex);
                readerIndex += 4;
                i2--;
            }
        } else {
            i = 1;
            while (i2 > 0) {
                i = (i * 31) + swapInt(byteBuf.getInt(readerIndex));
                readerIndex += 4;
                i2--;
            }
        }
        while (i3 > 0) {
            i = (i * 31) + byteBuf.getByte(readerIndex);
            i3--;
            readerIndex++;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public static int indexOf(ByteBuf byteBuf, ByteBuf byteBuf2) {
        int readableBytes = (byteBuf2.readableBytes() - byteBuf.readableBytes()) + 1;
        for (int i = 0; i < readableBytes; i++) {
            if (equals(byteBuf, byteBuf.readerIndex(), byteBuf2, byteBuf2.readerIndex() + i, byteBuf.readableBytes())) {
                return byteBuf2.readerIndex() + i;
            }
        }
        return -1;
    }

    public static boolean equals(ByteBuf byteBuf, int i, ByteBuf byteBuf2, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("All indexes and lengths must be non-negative");
        }
        if (byteBuf.writerIndex() - i3 < i || byteBuf2.writerIndex() - i3 < i2) {
            return false;
        }
        int i4 = i3 >>> 3;
        if (byteBuf.order() == byteBuf2.order()) {
            while (i4 > 0) {
                if (byteBuf.getLong(i) != byteBuf2.getLong(i2)) {
                    return false;
                }
                i += 8;
                i2 += 8;
                i4--;
            }
        } else {
            while (i4 > 0) {
                if (byteBuf.getLong(i) != swapLong(byteBuf2.getLong(i2))) {
                    return false;
                }
                i += 8;
                i2 += 8;
                i4--;
            }
        }
        for (int i5 = i3 & 7; i5 > 0; i5--) {
            if (byteBuf.getByte(i) != byteBuf2.getByte(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public static boolean equals(ByteBuf byteBuf, ByteBuf byteBuf2) {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes != byteBuf2.readableBytes()) {
            return false;
        }
        return equals(byteBuf, byteBuf.readerIndex(), byteBuf2, byteBuf2.readerIndex(), readableBytes);
    }

    public static int compare(ByteBuf byteBuf, ByteBuf byteBuf2) {
        long compareUintBigEndianB;
        int readableBytes = byteBuf.readableBytes();
        int readableBytes2 = byteBuf2.readableBytes();
        int min = Math.min(readableBytes, readableBytes2);
        int i = min >>> 2;
        int i2 = min & 3;
        int readerIndex = byteBuf.readerIndex();
        int readerIndex2 = byteBuf2.readerIndex();
        if (i > 0) {
            boolean z = byteBuf.order() == ByteOrder.BIG_ENDIAN;
            int i3 = i << 2;
            if (byteBuf.order() == byteBuf2.order()) {
                if (z) {
                    compareUintBigEndianB = compareUintBigEndian(byteBuf, byteBuf2, readerIndex, readerIndex2, i3);
                } else {
                    compareUintBigEndianB = compareUintLittleEndian(byteBuf, byteBuf2, readerIndex, readerIndex2, i3);
                }
            } else if (z) {
                compareUintBigEndianB = compareUintBigEndianA(byteBuf, byteBuf2, readerIndex, readerIndex2, i3);
            } else {
                compareUintBigEndianB = compareUintBigEndianB(byteBuf, byteBuf2, readerIndex, readerIndex2, i3);
            }
            if (compareUintBigEndianB != 0) {
                return (int) Math.min(2147483647L, Math.max(-2147483648L, compareUintBigEndianB));
            }
            readerIndex += i3;
            readerIndex2 += i3;
        }
        int i4 = i2 + readerIndex;
        while (readerIndex < i4) {
            int unsignedByte = byteBuf.getUnsignedByte(readerIndex) - byteBuf2.getUnsignedByte(readerIndex2);
            if (unsignedByte != 0) {
                return unsignedByte;
            }
            readerIndex++;
            readerIndex2++;
        }
        return readableBytes - readableBytes2;
    }

    private static long compareUintBigEndian(ByteBuf byteBuf, ByteBuf byteBuf2, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            long unsignedInt = byteBuf.getUnsignedInt(i) - byteBuf2.getUnsignedInt(i2);
            if (unsignedInt != 0) {
                return unsignedInt;
            }
            i += 4;
            i2 += 4;
        }
        return 0L;
    }

    private static long compareUintLittleEndian(ByteBuf byteBuf, ByteBuf byteBuf2, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            long unsignedIntLE = byteBuf.getUnsignedIntLE(i) - byteBuf2.getUnsignedIntLE(i2);
            if (unsignedIntLE != 0) {
                return unsignedIntLE;
            }
            i += 4;
            i2 += 4;
        }
        return 0L;
    }

    private static long compareUintBigEndianA(ByteBuf byteBuf, ByteBuf byteBuf2, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            long unsignedInt = byteBuf.getUnsignedInt(i) - byteBuf2.getUnsignedIntLE(i2);
            if (unsignedInt != 0) {
                return unsignedInt;
            }
            i += 4;
            i2 += 4;
        }
        return 0L;
    }

    private static long compareUintBigEndianB(ByteBuf byteBuf, ByteBuf byteBuf2, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            long unsignedIntLE = byteBuf.getUnsignedIntLE(i) - byteBuf2.getUnsignedInt(i2);
            if (unsignedIntLE != 0) {
                return unsignedIntLE;
            }
            i += 4;
            i2 += 4;
        }
        return 0L;
    }

    public static int indexOf(ByteBuf byteBuf, int i, int i2, byte b) {
        if (i <= i2) {
            return firstIndexOf(byteBuf, i, i2, b);
        }
        return lastIndexOf(byteBuf, i, i2, b);
    }

    public static short swapShort(short s) {
        return Short.reverseBytes(s);
    }

    public static int swapInt(int i) {
        return Integer.reverseBytes(i);
    }

    public static long swapLong(long j) {
        return Long.reverseBytes(j);
    }

    public static ByteBuf writeShortBE(ByteBuf byteBuf, int i) {
        return byteBuf.order() == ByteOrder.BIG_ENDIAN ? byteBuf.writeShort(i) : byteBuf.writeShortLE(i);
    }

    public static ByteBuf setShortBE(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.order() == ByteOrder.BIG_ENDIAN ? byteBuf.setShort(i, i2) : byteBuf.setShortLE(i, i2);
    }

    public static ByteBuf writeMediumBE(ByteBuf byteBuf, int i) {
        return byteBuf.order() == ByteOrder.BIG_ENDIAN ? byteBuf.writeMedium(i) : byteBuf.writeMediumLE(i);
    }

    public static ByteBuf readBytes(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, int i) {
        ByteBuf buffer = byteBufAllocator.buffer(i);
        try {
            byteBuf.readBytes(buffer);
            return buffer;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    private static int firstIndexOf(ByteBuf byteBuf, int i, int i2, byte b) {
        int max = Math.max(i, 0);
        if (max >= i2 || byteBuf.capacity() == 0) {
            return -1;
        }
        return byteBuf.forEachByte(max, i2 - max, new ByteProcessor.IndexOfProcessor(b));
    }

    private static int lastIndexOf(ByteBuf byteBuf, int i, int i2, byte b) {
        int min = Math.min(i, byteBuf.capacity());
        if (min < 0 || byteBuf.capacity() == 0) {
            return -1;
        }
        return byteBuf.forEachByteDesc(i2, min - i2, new ByteProcessor.IndexOfProcessor(b));
    }

    public static ByteBuf writeUtf8(ByteBufAllocator byteBufAllocator, CharSequence charSequence) {
        ByteBuf buffer = byteBufAllocator.buffer(utf8MaxBytes(charSequence));
        writeUtf8(buffer, charSequence);
        return buffer;
    }

    public static int writeUtf8(ByteBuf byteBuf, CharSequence charSequence) {
        return reserveAndWriteUtf8(byteBuf, charSequence, utf8MaxBytes(charSequence));
    }

    public static int reserveAndWriteUtf8(ByteBuf byteBuf, CharSequence charSequence, int i) {
        while (!(byteBuf instanceof AbstractByteBuf)) {
            if (byteBuf instanceof WrappedByteBuf) {
                byteBuf = byteBuf.unwrap();
            } else {
                byte[] bytes = charSequence.toString().getBytes(CharsetUtil.UTF_8);
                byteBuf.writeBytes(bytes);
                return bytes.length;
            }
        }
        AbstractByteBuf abstractByteBuf = (AbstractByteBuf) byteBuf;
        abstractByteBuf.ensureWritable0(i);
        int writeUtf8 = writeUtf8(abstractByteBuf, abstractByteBuf.writerIndex, charSequence, charSequence.length());
        abstractByteBuf.writerIndex += writeUtf8;
        return writeUtf8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int writeUtf8(AbstractByteBuf abstractByteBuf, int i, CharSequence charSequence, int i2) {
        int i3 = 0;
        int i4 = i;
        while (i3 < i2) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 128) {
                abstractByteBuf._setByte(i4, (byte) charAt);
                i4++;
            } else if (charAt < 2048) {
                int i5 = i4 + 1;
                abstractByteBuf._setByte(i4, (byte) ((charAt >> 6) | 192));
                i4 = i5 + 1;
                abstractByteBuf._setByte(i5, (byte) ((charAt & '?') | 128));
            } else {
                if (StringUtil.isSurrogate(charAt)) {
                    if (!Character.isHighSurrogate(charAt)) {
                        abstractByteBuf._setByte(i4, 63);
                        i4++;
                    } else {
                        i3++;
                        try {
                            char charAt2 = charSequence.charAt(i3);
                            if (!Character.isLowSurrogate(charAt2)) {
                                int i6 = i4 + 1;
                                abstractByteBuf._setByte(i4, 63);
                                i4 = i6 + 1;
                                abstractByteBuf._setByte(i6, Character.isHighSurrogate(charAt2) ? '?' : charAt2);
                            } else {
                                int codePoint = Character.toCodePoint(charAt, charAt2);
                                int i7 = i4 + 1;
                                abstractByteBuf._setByte(i4, (byte) ((codePoint >> 18) | DimensionsKt.HDPI));
                                int i8 = i7 + 1;
                                abstractByteBuf._setByte(i7, (byte) (((codePoint >> 12) & 63) | 128));
                                int i9 = i8 + 1;
                                abstractByteBuf._setByte(i8, (byte) (((codePoint >> 6) & 63) | 128));
                                i4 = i9 + 1;
                                abstractByteBuf._setByte(i9, (byte) ((codePoint & 63) | 128));
                            }
                        } catch (IndexOutOfBoundsException unused) {
                            abstractByteBuf._setByte(i4, 63);
                            i4++;
                        }
                    }
                } else {
                    int i10 = i4 + 1;
                    abstractByteBuf._setByte(i4, (byte) ((charAt >> '\f') | 224));
                    int i11 = i10 + 1;
                    abstractByteBuf._setByte(i10, (byte) ((63 & (charAt >> 6)) | 128));
                    abstractByteBuf._setByte(i11, (byte) ((charAt & '?') | 128));
                    i4 = i11 + 1;
                }
            }
            i3++;
        }
        return i4 - i;
    }

    public static int utf8MaxBytes(int i) {
        return i * MAX_BYTES_PER_CHAR_UTF8;
    }

    public static int utf8MaxBytes(CharSequence charSequence) {
        return utf8MaxBytes(charSequence.length());
    }

    public static int utf8Bytes(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return charSequence.length();
        }
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        return i < length ? i + utf8Bytes(charSequence, i, length) : i;
    }

    private static int utf8Bytes(CharSequence charSequence, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i3 += ((127 - charAt) >>> 31) + 1;
            } else if (!StringUtil.isSurrogate(charAt)) {
                i3 += 3;
            } else if (Character.isHighSurrogate(charAt)) {
                i++;
                try {
                    i3 = !Character.isLowSurrogate(charSequence.charAt(i)) ? i3 + 2 : i3 + 4;
                } catch (IndexOutOfBoundsException unused) {
                    return i3 + 1;
                }
            } else {
                i3++;
            }
            i++;
        }
        return i3;
    }

    public static ByteBuf writeAscii(ByteBufAllocator byteBufAllocator, CharSequence charSequence) {
        ByteBuf buffer = byteBufAllocator.buffer(charSequence.length());
        writeAscii(buffer, charSequence);
        return buffer;
    }

    public static int writeAscii(ByteBuf byteBuf, CharSequence charSequence) {
        int length = charSequence.length();
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            byteBuf.writeBytes(asciiString.array(), asciiString.arrayOffset(), length);
            return length;
        }
        while (!(byteBuf instanceof AbstractByteBuf)) {
            if (byteBuf instanceof WrappedByteBuf) {
                byteBuf = byteBuf.unwrap();
            } else {
                byte[] bytes = charSequence.toString().getBytes(CharsetUtil.US_ASCII);
                byteBuf.writeBytes(bytes);
                return bytes.length;
            }
        }
        AbstractByteBuf abstractByteBuf = (AbstractByteBuf) byteBuf;
        abstractByteBuf.ensureWritable0(length);
        int writeAscii = writeAscii(abstractByteBuf, abstractByteBuf.writerIndex, charSequence, length);
        abstractByteBuf.writerIndex += writeAscii;
        return writeAscii;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int writeAscii(AbstractByteBuf abstractByteBuf, int i, CharSequence charSequence, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            abstractByteBuf._setByte(i, AsciiString.c2b(charSequence.charAt(i3)));
            i3++;
            i++;
        }
        return i2;
    }

    public static ByteBuf encodeString(ByteBufAllocator byteBufAllocator, CharBuffer charBuffer, Charset charset) {
        return encodeString0(byteBufAllocator, false, charBuffer, charset, 0);
    }

    public static ByteBuf encodeString(ByteBufAllocator byteBufAllocator, CharBuffer charBuffer, Charset charset, int i) {
        return encodeString0(byteBufAllocator, false, charBuffer, charset, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuf encodeString0(ByteBufAllocator byteBufAllocator, boolean z, CharBuffer charBuffer, Charset charset, int i) {
        ByteBuf buffer;
        CharsetEncoder encoder = CharsetUtil.encoder(charset);
        int remaining = ((int) (charBuffer.remaining() * encoder.maxBytesPerChar())) + i;
        if (z) {
            buffer = byteBufAllocator.heapBuffer(remaining);
        } else {
            buffer = byteBufAllocator.buffer(remaining);
        }
        try {
            try {
                ByteBuffer internalNioBuffer = buffer.internalNioBuffer(buffer.readerIndex(), remaining);
                int position = internalNioBuffer.position();
                CoderResult encode = encoder.encode(charBuffer, internalNioBuffer, true);
                if (!encode.isUnderflow()) {
                    encode.throwException();
                }
                CoderResult flush = encoder.flush(internalNioBuffer);
                if (!flush.isUnderflow()) {
                    flush.throwException();
                }
                buffer.writerIndex((buffer.writerIndex() + internalNioBuffer.position()) - position);
                return buffer;
            } catch (CharacterCodingException e) {
                throw new IllegalStateException(e);
            }
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeString(ByteBuf byteBuf, int i, int i2, Charset charset) {
        if (i2 == 0) {
            return "";
        }
        CharsetDecoder decoder = CharsetUtil.decoder(charset);
        int maxCharsPerByte = (int) (i2 * decoder.maxCharsPerByte());
        CharBuffer charBuffer = CHAR_BUFFERS.get();
        if (charBuffer.length() < maxCharsPerByte) {
            charBuffer = CharBuffer.allocate(maxCharsPerByte);
            if (maxCharsPerByte <= MAX_CHAR_BUFFER_SIZE) {
                CHAR_BUFFERS.set(charBuffer);
            }
        } else {
            charBuffer.clear();
        }
        if (byteBuf.nioBufferCount() == 1) {
            decodeString(decoder, byteBuf.nioBuffer(i, i2), charBuffer);
        } else {
            ByteBuf heapBuffer = byteBuf.alloc().heapBuffer(i2);
            try {
                heapBuffer.writeBytes(byteBuf, i, i2);
                decodeString(decoder, heapBuffer.internalNioBuffer(heapBuffer.readerIndex(), i2), charBuffer);
            } finally {
                heapBuffer.release();
            }
        }
        return charBuffer.flip().toString();
    }

    private static void decodeString(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            CoderResult flush = charsetDecoder.flush(charBuffer);
            if (flush.isUnderflow()) {
                return;
            }
            flush.throwException();
        } catch (CharacterCodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ByteBuf threadLocalDirectBuffer() {
        if (THREAD_LOCAL_BUFFER_SIZE <= 0) {
            return null;
        }
        if (PlatformDependent.hasUnsafe()) {
            return ThreadLocalUnsafeDirectByteBuf.newInstance();
        }
        return ThreadLocalDirectByteBuf.newInstance();
    }

    public static byte[] getBytes(ByteBuf byteBuf) {
        return getBytes(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    public static byte[] getBytes(ByteBuf byteBuf, int i, int i2) {
        return getBytes(byteBuf, i, i2, true);
    }

    public static byte[] getBytes(ByteBuf byteBuf, int i, int i2, boolean z) {
        if (MathUtil.isOutOfBounds(i, i2, byteBuf.capacity())) {
            throw new IndexOutOfBoundsException("expected: 0 <= start(" + i + ") <= start + length(" + i2 + ") <= buf.capacity(" + byteBuf.capacity() + ')');
        }
        if (byteBuf.hasArray()) {
            if (z || i != 0 || i2 != byteBuf.capacity()) {
                int arrayOffset = byteBuf.arrayOffset() + i;
                return Arrays.copyOfRange(byteBuf.array(), arrayOffset, i2 + arrayOffset);
            }
            return byteBuf.array();
        }
        byte[] bArr = new byte[i2];
        byteBuf.getBytes(i, bArr);
        return bArr;
    }

    public static void copy(AsciiString asciiString, ByteBuf byteBuf) {
        copy(asciiString, 0, byteBuf, asciiString.length());
    }

    public static void copy(AsciiString asciiString, int i, ByteBuf byteBuf, int i2, int i3) {
        if (MathUtil.isOutOfBounds(i, i3, asciiString.length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= srcIdx(" + i + ") <= srcIdx + length(" + i3 + ") <= srcLen(" + asciiString.length() + ')');
        }
        ((ByteBuf) ObjectUtil.checkNotNull(byteBuf, "dst")).setBytes(i2, asciiString.array(), i + asciiString.arrayOffset(), i3);
    }

    public static void copy(AsciiString asciiString, int i, ByteBuf byteBuf, int i2) {
        if (MathUtil.isOutOfBounds(i, i2, asciiString.length())) {
            throw new IndexOutOfBoundsException("expected: 0 <= srcIdx(" + i + ") <= srcIdx + length(" + i2 + ") <= srcLen(" + asciiString.length() + ')');
        }
        ((ByteBuf) ObjectUtil.checkNotNull(byteBuf, "dst")).writeBytes(asciiString.array(), i + asciiString.arrayOffset(), i2);
    }

    public static String prettyHexDump(ByteBuf byteBuf) {
        return prettyHexDump(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    public static String prettyHexDump(ByteBuf byteBuf, int i, int i2) {
        return HexUtil.prettyHexDump(byteBuf, i, i2);
    }

    public static void appendPrettyHexDump(StringBuilder sb, ByteBuf byteBuf) {
        appendPrettyHexDump(sb, byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    public static void appendPrettyHexDump(StringBuilder sb, ByteBuf byteBuf, int i, int i2) {
        HexUtil.appendPrettyHexDump(sb, byteBuf, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public static final class HexUtil {
        private static final char[] BYTE2CHAR = new char[256];
        private static final char[] HEXDUMP_TABLE = new char[1024];
        private static final String[] HEXPADDING = new String[16];
        private static final String[] HEXDUMP_ROWPREFIXES = new String[4096];
        private static final String[] BYTE2HEX = new String[256];
        private static final String[] BYTEPADDING = new String[16];

        private HexUtil() {
        }

        static {
            char[] charArray = "0123456789abcdef".toCharArray();
            int i = 0;
            for (int i2 = 0; i2 < 256; i2++) {
                char[] cArr = HEXDUMP_TABLE;
                int i3 = i2 << 1;
                cArr[i3] = charArray[(i2 >>> 4) & 15];
                cArr[i3 + 1] = charArray[i2 & 15];
            }
            int i4 = 0;
            while (true) {
                String[] strArr = HEXPADDING;
                if (i4 >= strArr.length) {
                    break;
                }
                int length = strArr.length - i4;
                StringBuilder sb = new StringBuilder(length * 3);
                for (int i5 = 0; i5 < length; i5++) {
                    sb.append("   ");
                }
                HEXPADDING[i4] = sb.toString();
                i4++;
            }
            for (int i6 = 0; i6 < HEXDUMP_ROWPREFIXES.length; i6++) {
                StringBuilder sb2 = new StringBuilder(12);
                sb2.append(StringUtil.NEWLINE);
                sb2.append(Long.toHexString(((i6 << 4) & 4294967295L) | Conversions.THIRTYTWO_BIT));
                sb2.setCharAt(sb2.length() - 9, '|');
                sb2.append('|');
                HEXDUMP_ROWPREFIXES[i6] = sb2.toString();
            }
            int i7 = 0;
            while (true) {
                String[] strArr2 = BYTE2HEX;
                if (i7 >= strArr2.length) {
                    break;
                }
                strArr2[i7] = ' ' + StringUtil.byteToHexStringPadded(i7);
                i7++;
            }
            int i8 = 0;
            while (true) {
                String[] strArr3 = BYTEPADDING;
                if (i8 >= strArr3.length) {
                    break;
                }
                int length2 = strArr3.length - i8;
                StringBuilder sb3 = new StringBuilder(length2);
                for (int i9 = 0; i9 < length2; i9++) {
                    sb3.append(' ');
                }
                BYTEPADDING[i8] = sb3.toString();
                i8++;
            }
            while (true) {
                char[] cArr2 = BYTE2CHAR;
                if (i >= cArr2.length) {
                    return;
                }
                if (i <= 31 || i >= 127) {
                    BYTE2CHAR[i] = FilenameUtils.EXTENSION_SEPARATOR;
                } else {
                    cArr2[i] = (char) i;
                }
                i++;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String hexDump(ByteBuf byteBuf, int i, int i2) {
            if (i2 < 0) {
                throw new IllegalArgumentException("length: " + i2);
            }
            if (i2 == 0) {
                return "";
            }
            int i3 = i + i2;
            char[] cArr = new char[i2 << 1];
            int i4 = 0;
            while (i < i3) {
                System.arraycopy(HEXDUMP_TABLE, byteBuf.getUnsignedByte(i) << 1, cArr, i4, 2);
                i++;
                i4 += 2;
            }
            return new String(cArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String hexDump(byte[] bArr, int i, int i2) {
            if (i2 < 0) {
                throw new IllegalArgumentException("length: " + i2);
            }
            if (i2 == 0) {
                return "";
            }
            int i3 = i + i2;
            char[] cArr = new char[i2 << 1];
            int i4 = 0;
            while (i < i3) {
                System.arraycopy(HEXDUMP_TABLE, (bArr[i] & 255) << 1, cArr, i4, 2);
                i++;
                i4 += 2;
            }
            return new String(cArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String prettyHexDump(ByteBuf byteBuf, int i, int i2) {
            if (i2 == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder(((i2 / 16) + (i2 % 15 == 0 ? 0 : 1) + 4) * 80);
            appendPrettyHexDump(sb, byteBuf, i, i2);
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void appendPrettyHexDump(StringBuilder sb, ByteBuf byteBuf, int i, int i2) {
            if (MathUtil.isOutOfBounds(i, i2, byteBuf.capacity())) {
                throw new IndexOutOfBoundsException("expected: 0 <= offset(" + i + ") <= offset + length(" + i2 + ") <= buf.capacity(" + byteBuf.capacity() + ')');
            }
            if (i2 == 0) {
                return;
            }
            sb.append("         +-------------------------------------------------+" + StringUtil.NEWLINE + "         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |" + StringUtil.NEWLINE + "+--------+-------------------------------------------------+----------------+");
            int i3 = i2 >>> 4;
            int i4 = i2 & 15;
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = (i5 << 4) + i;
                appendHexDumpRowPrefix(sb, i5, i6);
                int i7 = i6 + 16;
                for (int i8 = i6; i8 < i7; i8++) {
                    sb.append(BYTE2HEX[byteBuf.getUnsignedByte(i8)]);
                }
                sb.append(" |");
                while (i6 < i7) {
                    sb.append(BYTE2CHAR[byteBuf.getUnsignedByte(i6)]);
                    i6++;
                }
                sb.append('|');
            }
            if (i4 != 0) {
                int i9 = (i3 << 4) + i;
                appendHexDumpRowPrefix(sb, i3, i9);
                int i10 = i9 + i4;
                for (int i11 = i9; i11 < i10; i11++) {
                    sb.append(BYTE2HEX[byteBuf.getUnsignedByte(i11)]);
                }
                sb.append(HEXPADDING[i4]);
                sb.append(" |");
                while (i9 < i10) {
                    sb.append(BYTE2CHAR[byteBuf.getUnsignedByte(i9)]);
                    i9++;
                }
                sb.append(BYTEPADDING[i4]);
                sb.append('|');
            }
            sb.append(StringUtil.NEWLINE + "+--------+-------------------------------------------------+----------------+");
        }

        private static void appendHexDumpRowPrefix(StringBuilder sb, int i, int i2) {
            String[] strArr = HEXDUMP_ROWPREFIXES;
            if (i < strArr.length) {
                sb.append(strArr[i]);
                return;
            }
            sb.append(StringUtil.NEWLINE);
            sb.append(Long.toHexString((i2 & 4294967295L) | Conversions.THIRTYTWO_BIT));
            sb.setCharAt(sb.length() - 9, '|');
            sb.append('|');
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public static final class ThreadLocalUnsafeDirectByteBuf extends UnpooledUnsafeDirectByteBuf {
        private static final Recycler<ThreadLocalUnsafeDirectByteBuf> RECYCLER = new Recycler<ThreadLocalUnsafeDirectByteBuf>() { // from class: io.netty.buffer.ByteBufUtil.ThreadLocalUnsafeDirectByteBuf.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.netty.util.Recycler
            public ThreadLocalUnsafeDirectByteBuf newObject(Recycler.Handle<ThreadLocalUnsafeDirectByteBuf> handle) {
                return new ThreadLocalUnsafeDirectByteBuf(handle);
            }
        };
        private final Recycler.Handle<ThreadLocalUnsafeDirectByteBuf> handle;

        static ThreadLocalUnsafeDirectByteBuf newInstance() {
            ThreadLocalUnsafeDirectByteBuf threadLocalUnsafeDirectByteBuf = RECYCLER.get();
            threadLocalUnsafeDirectByteBuf.setRefCnt(1);
            return threadLocalUnsafeDirectByteBuf;
        }

        private ThreadLocalUnsafeDirectByteBuf(Recycler.Handle<ThreadLocalUnsafeDirectByteBuf> handle) {
            super(UnpooledByteBufAllocator.DEFAULT, 256, Integer.MAX_VALUE);
            this.handle = handle;
        }

        @Override // io.netty.buffer.UnpooledUnsafeDirectByteBuf, io.netty.buffer.AbstractReferenceCountedByteBuf
        protected void deallocate() {
            if (capacity() > ByteBufUtil.THREAD_LOCAL_BUFFER_SIZE) {
                super.deallocate();
            } else {
                clear();
                this.handle.recycle(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public static final class ThreadLocalDirectByteBuf extends UnpooledDirectByteBuf {
        private static final Recycler<ThreadLocalDirectByteBuf> RECYCLER = new Recycler<ThreadLocalDirectByteBuf>() { // from class: io.netty.buffer.ByteBufUtil.ThreadLocalDirectByteBuf.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.netty.util.Recycler
            public ThreadLocalDirectByteBuf newObject(Recycler.Handle<ThreadLocalDirectByteBuf> handle) {
                return new ThreadLocalDirectByteBuf(handle);
            }
        };
        private final Recycler.Handle<ThreadLocalDirectByteBuf> handle;

        static ThreadLocalDirectByteBuf newInstance() {
            ThreadLocalDirectByteBuf threadLocalDirectByteBuf = RECYCLER.get();
            threadLocalDirectByteBuf.setRefCnt(1);
            return threadLocalDirectByteBuf;
        }

        private ThreadLocalDirectByteBuf(Recycler.Handle<ThreadLocalDirectByteBuf> handle) {
            super(UnpooledByteBufAllocator.DEFAULT, 256, Integer.MAX_VALUE);
            this.handle = handle;
        }

        @Override // io.netty.buffer.UnpooledDirectByteBuf, io.netty.buffer.AbstractReferenceCountedByteBuf
        protected void deallocate() {
            if (capacity() > ByteBufUtil.THREAD_LOCAL_BUFFER_SIZE) {
                super.deallocate();
            } else {
                clear();
                this.handle.recycle(this);
            }
        }
    }

    public static boolean isText(ByteBuf byteBuf, Charset charset) {
        return isText(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), charset);
    }

    public static boolean isText(ByteBuf byteBuf, int i, int i2, Charset charset) {
        ObjectUtil.checkNotNull(byteBuf, "buf");
        ObjectUtil.checkNotNull(charset, "charset");
        int readerIndex = byteBuf.readerIndex() + byteBuf.readableBytes();
        if (i < 0 || i2 < 0 || i > readerIndex - i2) {
            throw new IndexOutOfBoundsException("index: " + i + " length: " + i2);
        }
        if (charset.equals(CharsetUtil.UTF_8)) {
            return isUtf8(byteBuf, i, i2);
        }
        if (charset.equals(CharsetUtil.US_ASCII)) {
            return isAscii(byteBuf, i, i2);
        }
        CharsetDecoder decoder = CharsetUtil.decoder(charset, CodingErrorAction.REPORT, CodingErrorAction.REPORT);
        try {
            if (byteBuf.nioBufferCount() == 1) {
                decoder.decode(byteBuf.nioBuffer(i, i2));
            } else {
                ByteBuf heapBuffer = byteBuf.alloc().heapBuffer(i2);
                try {
                    heapBuffer.writeBytes(byteBuf, i, i2);
                    decoder.decode(heapBuffer.internalNioBuffer(heapBuffer.readerIndex(), i2));
                } finally {
                    heapBuffer.release();
                }
            }
            return true;
        } catch (CharacterCodingException unused) {
            return false;
        }
    }

    private static boolean isAscii(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.forEachByte(i, i2, FIND_NON_ASCII) == -1;
    }

    private static boolean isUtf8(ByteBuf byteBuf, int i, int i2) {
        int i3;
        int i4 = i2 + i;
        while (i < i4) {
            int i5 = i + 1;
            byte b = byteBuf.getByte(i);
            if ((b & 128) == 0) {
                i = i5;
            } else if ((b & 224) == 192) {
                if (i5 >= i4) {
                    return false;
                }
                int i6 = i5 + 1;
                if ((byteBuf.getByte(i5) & SlipConfig.END) != 128 || (b & 255) < 194) {
                    return false;
                }
                i = i6;
            } else {
                if ((b & 240) != 224) {
                    if ((b & 248) != 240 || i5 > i4 - 3) {
                        return false;
                    }
                    int i7 = i5 + 1;
                    byte b2 = byteBuf.getByte(i5);
                    int i8 = i7 + 1;
                    byte b3 = byteBuf.getByte(i7);
                    int i9 = i8 + 1;
                    byte b4 = byteBuf.getByte(i8);
                    if ((b2 & SlipConfig.END) == 128 && (b3 & SlipConfig.END) == 128 && (b4 & SlipConfig.END) == 128 && (i3 = b & 255) <= 244 && ((i3 != 240 || (b2 & 255) >= 144) && (i3 != 244 || (b2 & 255) <= 143))) {
                        i = i9;
                    }
                    return false;
                }
                if (i5 > i4 - 2) {
                    return false;
                }
                int i10 = i5 + 1;
                byte b5 = byteBuf.getByte(i5);
                int i11 = i10 + 1;
                byte b6 = byteBuf.getByte(i10);
                if ((b5 & SlipConfig.END) != 128 || (b6 & SlipConfig.END) != 128) {
                    return false;
                }
                int i12 = b & 15;
                if (i12 == 0 && (b5 & 255) < 160) {
                    return false;
                }
                if (i12 == 13 && (b5 & 255) > 159) {
                    return false;
                }
                i = i11;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void readBytes(ByteBufAllocator byteBufAllocator, ByteBuffer byteBuffer, int i, int i2, OutputStream outputStream) throws IOException {
        if (byteBuffer.hasArray()) {
            outputStream.write(byteBuffer.array(), i + byteBuffer.arrayOffset(), i2);
            return;
        }
        int min = Math.min(i2, 8192);
        byteBuffer.clear().position(i);
        if (byteBufAllocator.isDirectBufferPooled()) {
            ByteBuf heapBuffer = byteBufAllocator.heapBuffer(min);
            try {
                getBytes(byteBuffer, heapBuffer.array(), heapBuffer.arrayOffset(), min, outputStream, i2);
                return;
            } finally {
                heapBuffer.release();
            }
        }
        getBytes(byteBuffer, new byte[min], 0, min, outputStream, i2);
    }

    private static void getBytes(ByteBuffer byteBuffer, byte[] bArr, int i, int i2, OutputStream outputStream, int i3) throws IOException {
        do {
            int min = Math.min(i2, i3);
            byteBuffer.get(bArr, i, min);
            outputStream.write(bArr, i, min);
            i3 -= min;
        } while (i3 > 0);
    }

    private ByteBufUtil() {
    }
}
