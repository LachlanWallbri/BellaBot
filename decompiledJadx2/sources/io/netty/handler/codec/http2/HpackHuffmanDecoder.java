package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ThrowableUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class HpackHuffmanDecoder {
    private static final Http2Exception EOS_DECODED = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - EOS Decoded", new Object[0]), HpackHuffmanDecoder.class, "decode(..)");
    private static final Http2Exception INVALID_PADDING = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - Invalid Padding", new Object[0]), HpackHuffmanDecoder.class, "decode(..)");
    private static final Node ROOT = buildTree(HpackUtil.HUFFMAN_CODES, HpackUtil.HUFFMAN_CODE_LENGTHS);
    private final DecoderProcessor processor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HpackHuffmanDecoder(int i) {
        this.processor = new DecoderProcessor(i);
    }

    public AsciiString decode(ByteBuf byteBuf, int i) throws Http2Exception {
        this.processor.reset();
        byteBuf.forEachByte(byteBuf.readerIndex(), i, this.processor);
        byteBuf.skipBytes(i);
        return this.processor.end();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Node {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final int bits;
        private final Node[] children;
        private final int symbol;

        Node() {
            this.symbol = 0;
            this.bits = 8;
            this.children = new Node[256];
        }

        Node(int i, int i2) {
            this.symbol = i;
            this.bits = i2;
            this.children = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isTerminal() {
            return this.children == null;
        }
    }

    private static Node buildTree(int[] iArr, byte[] bArr) {
        Node node = new Node();
        for (int i = 0; i < iArr.length; i++) {
            insert(node, i, iArr[i], bArr[i]);
        }
        return node;
    }

    private static void insert(Node node, int i, int i2, byte b) {
        while (b > 8) {
            if (!node.isTerminal()) {
                b = (byte) (b - 8);
                int i3 = (i2 >>> b) & 255;
                if (node.children[i3] == null) {
                    node.children[i3] = new Node();
                }
                node = node.children[i3];
            } else {
                throw new IllegalStateException("invalid Huffman code: prefix not unique");
            }
        }
        Node node2 = new Node(i, b);
        int i4 = 8 - b;
        int i5 = (i2 << i4) & 255;
        int i6 = 1 << i4;
        for (int i7 = i5; i7 < i5 + i6; i7++) {
            node.children[i7] = node2;
        }
    }

    /* loaded from: classes.dex */
    private static final class DecoderProcessor implements ByteProcessor {
        private byte[] bytes;
        private int current;
        private int currentBits;
        private int index;
        private final int initialCapacity;
        private Node node;
        private int symbolBits;

        DecoderProcessor(int i) {
            this.initialCapacity = ObjectUtil.checkPositive(i, "initialCapacity");
        }

        void reset() {
            this.node = HpackHuffmanDecoder.ROOT;
            this.current = 0;
            this.currentBits = 0;
            this.symbolBits = 0;
            this.bytes = new byte[this.initialCapacity];
            this.index = 0;
        }

        @Override // io.netty.util.ByteProcessor
        public boolean process(byte b) throws Http2Exception {
            this.current = (b & 255) | (this.current << 8);
            this.currentBits += 8;
            this.symbolBits += 8;
            do {
                Node[] nodeArr = this.node.children;
                int i = this.current;
                int i2 = this.currentBits;
                Node node = nodeArr[(i >>> (i2 - 8)) & 255];
                this.node = node;
                this.currentBits = i2 - node.bits;
                if (this.node.isTerminal()) {
                    if (this.node.symbol == 256) {
                        throw HpackHuffmanDecoder.EOS_DECODED;
                    }
                    append(this.node.symbol);
                    this.node = HpackHuffmanDecoder.ROOT;
                    this.symbolBits = this.currentBits;
                }
            } while (this.currentBits >= 8);
            return true;
        }

        AsciiString end() throws Http2Exception {
            while (this.currentBits > 0) {
                Node node = this.node.children[(this.current << (8 - this.currentBits)) & 255];
                this.node = node;
                if (!node.isTerminal() || this.node.bits > this.currentBits) {
                    break;
                }
                if (this.node.symbol == 256) {
                    throw HpackHuffmanDecoder.EOS_DECODED;
                }
                this.currentBits -= this.node.bits;
                append(this.node.symbol);
                this.node = HpackHuffmanDecoder.ROOT;
                this.symbolBits = this.currentBits;
            }
            int i = this.symbolBits;
            int i2 = (1 << i) - 1;
            if (i > 7 || (this.current & i2) != i2) {
                throw HpackHuffmanDecoder.INVALID_PADDING;
            }
            return new AsciiString(this.bytes, 0, this.index, false);
        }

        private void append(int i) {
            byte[] bArr = this.bytes;
            if (bArr.length == this.index) {
                int length = bArr.length;
                int length2 = bArr.length;
                byte[] bArr2 = new byte[length >= 1024 ? length2 + this.initialCapacity : length2 << 1];
                byte[] bArr3 = this.bytes;
                System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
                this.bytes = bArr2;
            }
            byte[] bArr4 = this.bytes;
            int i2 = this.index;
            this.index = i2 + 1;
            bArr4[i2] = (byte) i;
        }
    }
}
