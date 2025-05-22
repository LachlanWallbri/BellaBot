package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes8.dex */
class NioZipEncoding implements ZipEncoding, CharsetAccessor {
    private final Charset charset;
    private final boolean useReplacement;
    private static final byte[] REPLACEMENT_BYTES = {63};
    private static final char REPLACEMENT = '?';
    private static final String REPLACEMENT_STRING = String.valueOf(REPLACEMENT);
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioZipEncoding(Charset charset, boolean z) {
        this.charset = charset;
        this.useReplacement = z;
    }

    @Override // org.apache.commons.compress.archivers.zip.CharsetAccessor
    public Charset getCharset() {
        return this.charset;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public boolean canEncode(String str) {
        return newEncoder().canEncode(str);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public ByteBuffer encode(String str) {
        CharsetEncoder newEncoder = newEncoder();
        CharBuffer wrap = CharBuffer.wrap(str);
        ByteBuffer allocate = ByteBuffer.allocate(estimateInitialBufferSize(newEncoder, wrap.remaining()));
        CharBuffer charBuffer = null;
        while (wrap.hasRemaining()) {
            CoderResult encode = newEncoder.encode(wrap, allocate, false);
            if (encode.isUnmappable() || encode.isMalformed()) {
                if (estimateIncrementalEncodingSize(newEncoder, encode.length() * 6) > allocate.remaining()) {
                    int i = 0;
                    for (int position = wrap.position(); position < wrap.limit(); position++) {
                        i += !newEncoder.canEncode(wrap.get(position)) ? 6 : 1;
                    }
                    allocate = ZipEncodingHelper.growBufferBy(allocate, estimateIncrementalEncodingSize(newEncoder, i) - allocate.remaining());
                }
                if (charBuffer == null) {
                    charBuffer = CharBuffer.allocate(6);
                }
                for (int i2 = 0; i2 < encode.length(); i2++) {
                    allocate = encodeFully(newEncoder, encodeSurrogate(charBuffer, wrap.get()), allocate);
                }
            } else if (encode.isOverflow()) {
                allocate = ZipEncodingHelper.growBufferBy(allocate, estimateIncrementalEncodingSize(newEncoder, wrap.remaining()));
            } else if (encode.isUnderflow() || encode.isError()) {
                break;
            }
        }
        newEncoder.encode(wrap, allocate, true);
        allocate.limit(allocate.position());
        allocate.rewind();
        return allocate;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public String decode(byte[] bArr) throws IOException {
        return newDecoder().decode(ByteBuffer.wrap(bArr)).toString();
    }

    private static ByteBuffer encodeFully(CharsetEncoder charsetEncoder, CharBuffer charBuffer, ByteBuffer byteBuffer) {
        while (charBuffer.hasRemaining()) {
            if (charsetEncoder.encode(charBuffer, byteBuffer, false).isOverflow()) {
                byteBuffer = ZipEncodingHelper.growBufferBy(byteBuffer, estimateIncrementalEncodingSize(charsetEncoder, charBuffer.remaining()));
            }
        }
        return byteBuffer;
    }

    private static CharBuffer encodeSurrogate(CharBuffer charBuffer, char c) {
        charBuffer.position(0).limit(6);
        charBuffer.put('%');
        charBuffer.put(Matrix.MATRIX_TYPE_RANDOM_UT);
        charBuffer.put(HEX_CHARS[(c >> '\f') & 15]);
        charBuffer.put(HEX_CHARS[(c >> '\b') & 15]);
        charBuffer.put(HEX_CHARS[(c >> 4) & 15]);
        charBuffer.put(HEX_CHARS[c & 15]);
        charBuffer.flip();
        return charBuffer;
    }

    private CharsetEncoder newEncoder() {
        if (this.useReplacement) {
            return this.charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(REPLACEMENT_BYTES);
        }
        return this.charset.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
    }

    private CharsetDecoder newDecoder() {
        if (!this.useReplacement) {
            return this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        }
        return this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(REPLACEMENT_STRING);
    }

    private static int estimateInitialBufferSize(CharsetEncoder charsetEncoder, int i) {
        return (int) Math.ceil(charsetEncoder.maxBytesPerChar() + ((i - 1) * charsetEncoder.averageBytesPerChar()));
    }

    private static int estimateIncrementalEncodingSize(CharsetEncoder charsetEncoder, int i) {
        return (int) Math.ceil(i * charsetEncoder.averageBytesPerChar());
    }
}
