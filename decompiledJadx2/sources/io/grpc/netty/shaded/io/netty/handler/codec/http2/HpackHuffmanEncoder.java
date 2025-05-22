package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HpackHuffmanEncoder {
    private final int[] codes;
    private final EncodeProcessor encodeProcessor;
    private final EncodedLengthProcessor encodedLengthProcessor;
    private final byte[] lengths;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HpackHuffmanEncoder() {
        this(HpackUtil.HUFFMAN_CODES, HpackUtil.HUFFMAN_CODE_LENGTHS);
    }

    private HpackHuffmanEncoder(int[] iArr, byte[] bArr) {
        this.encodedLengthProcessor = new EncodedLengthProcessor();
        this.encodeProcessor = new EncodeProcessor();
        this.codes = iArr;
        this.lengths = bArr;
    }

    public void encode(ByteBuf byteBuf, CharSequence charSequence) {
        ObjectUtil.checkNotNull(byteBuf, "out");
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            try {
                try {
                    this.encodeProcessor.out = byteBuf;
                    asciiString.forEachByte(this.encodeProcessor);
                } catch (Exception e) {
                    PlatformDependent.throwException(e);
                }
                return;
            } finally {
                this.encodeProcessor.end();
            }
        }
        encodeSlowPath(byteBuf, charSequence);
    }

    private void encodeSlowPath(ByteBuf byteBuf, CharSequence charSequence) {
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            int charAt = charSequence.charAt(i2) & 255;
            int i3 = this.codes[charAt];
            byte b = this.lengths[charAt];
            j = (j << b) | i3;
            i += b;
            while (i >= 8) {
                i -= 8;
                byteBuf.writeByte((int) (j >> i));
            }
        }
        if (i > 0) {
            byteBuf.writeByte((int) ((255 >>> i) | (j << (8 - i))));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEncodedLength(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            AsciiString asciiString = (AsciiString) charSequence;
            try {
                this.encodedLengthProcessor.reset();
                asciiString.forEachByte(this.encodedLengthProcessor);
                return this.encodedLengthProcessor.length();
            } catch (Exception e) {
                PlatformDependent.throwException(e);
                return -1;
            }
        }
        return getEncodedLengthSlowPath(charSequence);
    }

    private int getEncodedLengthSlowPath(CharSequence charSequence) {
        long j = 0;
        for (int i = 0; i < charSequence.length(); i++) {
            j += this.lengths[charSequence.charAt(i) & 255];
        }
        return (int) ((j + 7) >> 3);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class EncodeProcessor implements ByteProcessor {
        private long current;

        /* renamed from: n */
        private int f8378n;
        ByteBuf out;

        private EncodeProcessor() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) {
            byte b2 = HpackHuffmanEncoder.this.lengths[b & 255];
            this.current <<= b2;
            this.current |= HpackHuffmanEncoder.this.codes[r6];
            this.f8378n += b2;
            while (true) {
                int i = this.f8378n;
                if (i < 8) {
                    return true;
                }
                this.f8378n = i - 8;
                this.out.writeByte((int) (this.current >> this.f8378n));
            }
        }

        void end() {
            try {
                if (this.f8378n > 0) {
                    this.current <<= 8 - this.f8378n;
                    this.current |= 255 >>> this.f8378n;
                    this.out.writeByte((int) this.current);
                }
            } finally {
                this.out = null;
                this.current = 0L;
                this.f8378n = 0;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class EncodedLengthProcessor implements ByteProcessor {
        private long len;

        private EncodedLengthProcessor() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ByteProcessor
        public boolean process(byte b) {
            this.len += HpackHuffmanEncoder.this.lengths[b & 255];
            return true;
        }

        void reset() {
            this.len = 0L;
        }

        int length() {
            return (int) ((this.len + 7) >> 3);
        }
    }
}
