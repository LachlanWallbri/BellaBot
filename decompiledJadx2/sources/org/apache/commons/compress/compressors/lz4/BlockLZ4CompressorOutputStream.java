package org.apache.commons.compress.compressors.lz4;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public class BlockLZ4CompressorOutputStream extends CompressorOutputStream {
    private static final int MIN_BACK_REFERENCE_LENGTH = 4;
    private static final int MIN_OFFSET_OF_LAST_BACK_REFERENCE = 12;
    private final LZ77Compressor compressor;
    private final Deque<byte[]> expandedBlocks;
    private boolean finished;
    private final byte[] oneByte;

    /* renamed from: os */
    private final OutputStream f8936os;
    private final Deque<Pair> pairs;

    public BlockLZ4CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, createParameterBuilder().build());
    }

    public BlockLZ4CompressorOutputStream(OutputStream outputStream, Parameters parameters) throws IOException {
        this.oneByte = new byte[1];
        this.pairs = new LinkedList();
        this.expandedBlocks = new LinkedList();
        this.f8936os = outputStream;
        this.compressor = new LZ77Compressor(parameters, new LZ77Compressor.Callback() { // from class: org.apache.commons.compress.compressors.lz4.-$$Lambda$BlockLZ4CompressorOutputStream$j7OLpQ2ekn0KW_ActR7DGXbmQ94
            @Override // org.apache.commons.compress.compressors.lz77support.LZ77Compressor.Callback
            public final void accept(LZ77Compressor.Block block) {
                BlockLZ4CompressorOutputStream.this.lambda$new$0$BlockLZ4CompressorOutputStream(block);
            }
        });
    }

    /* renamed from: org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream$1 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C81061 {

        /* renamed from: $SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType */
        static final /* synthetic */ int[] f8937x90901988 = new int[LZ77Compressor.Block.BlockType.values().length];

        static {
            try {
                f8937x90901988[LZ77Compressor.Block.BlockType.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8937x90901988[LZ77Compressor.Block.BlockType.BACK_REFERENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8937x90901988[LZ77Compressor.Block.BlockType.EOD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public /* synthetic */ void lambda$new$0$BlockLZ4CompressorOutputStream(LZ77Compressor.Block block) throws IOException {
        int i = C81061.f8937x90901988[block.getType().ordinal()];
        if (i == 1) {
            addLiteralBlock((LZ77Compressor.LiteralBlock) block);
        } else if (i == 2) {
            addBackReference((LZ77Compressor.BackReference) block);
        } else {
            if (i != 3) {
                return;
            }
            writeFinalLiteralBlock();
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.compressor.compress(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            finish();
        } finally {
            this.f8936os.close();
        }
    }

    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        this.compressor.finish();
        this.finished = true;
    }

    public void prefill(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2 + i);
            this.compressor.prefill(copyOfRange);
            recordLiteral(copyOfRange);
        }
    }

    private void addLiteralBlock(LZ77Compressor.LiteralBlock literalBlock) throws IOException {
        recordLiteral(writeBlocksAndReturnUnfinishedPair(literalBlock.getLength()).addLiteral(literalBlock));
        clearUnusedBlocksAndPairs();
    }

    private void addBackReference(LZ77Compressor.BackReference backReference) throws IOException {
        writeBlocksAndReturnUnfinishedPair(backReference.getLength()).setBackReference(backReference);
        recordBackReference(backReference);
        clearUnusedBlocksAndPairs();
    }

    private Pair writeBlocksAndReturnUnfinishedPair(int i) throws IOException {
        writeWritablePairs(i);
        Pair peekLast = this.pairs.peekLast();
        if (peekLast != null && !peekLast.hasBackReference()) {
            return peekLast;
        }
        Pair pair = new Pair();
        this.pairs.addLast(pair);
        return pair;
    }

    private void recordLiteral(byte[] bArr) {
        this.expandedBlocks.addFirst(bArr);
    }

    private void clearUnusedBlocksAndPairs() {
        clearUnusedBlocks();
        clearUnusedPairs();
    }

    private void clearUnusedBlocks() {
        Iterator<byte[]> it = this.expandedBlocks.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            i++;
            i2 += it.next().length;
            if (i2 >= 65536) {
                break;
            }
        }
        int size = this.expandedBlocks.size();
        while (i < size) {
            this.expandedBlocks.removeLast();
            i++;
        }
    }

    private void recordBackReference(LZ77Compressor.BackReference backReference) {
        this.expandedBlocks.addFirst(expand(backReference.getOffset(), backReference.getLength()));
    }

    private byte[] expand(int i, int i2) {
        byte[] bArr = new byte[i2];
        if (i == 1) {
            byte[] peekFirst = this.expandedBlocks.peekFirst();
            byte b = peekFirst[peekFirst.length - 1];
            if (b != 0) {
                Arrays.fill(bArr, b);
            }
        } else {
            expandFromList(bArr, i, i2);
        }
        return bArr;
    }

    private void expandFromList(byte[] bArr, int i, int i2) {
        int i3;
        int min;
        int i4 = i;
        int i5 = 0;
        while (i2 > 0) {
            byte[] bArr2 = null;
            if (i4 > 0) {
                Iterator<byte[]> it = this.expandedBlocks.iterator();
                int i6 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    byte[] next = it.next();
                    if (next.length + i6 >= i4) {
                        bArr2 = next;
                        break;
                    }
                    i6 += next.length;
                }
                if (bArr2 == null) {
                    throw new IllegalStateException("Failed to find a block containing offset " + i);
                }
                i3 = (i6 + bArr2.length) - i4;
                min = Math.min(i2, bArr2.length - i3);
            } else {
                i3 = -i4;
                min = Math.min(i2, i5 + i4);
                bArr2 = bArr;
            }
            System.arraycopy(bArr2, i3, bArr, i5, min);
            i4 -= min;
            i2 -= min;
            i5 += min;
        }
    }

    private void clearUnusedPairs() {
        Iterator<Pair> descendingIterator = this.pairs.descendingIterator();
        int i = 0;
        int i2 = 0;
        while (descendingIterator.hasNext()) {
            i++;
            i2 += descendingIterator.next().length();
            if (i2 >= 65536) {
                break;
            }
        }
        int size = this.pairs.size();
        while (i < size && this.pairs.peekFirst().hasBeenWritten()) {
            this.pairs.removeFirst();
            i++;
        }
    }

    private void writeFinalLiteralBlock() throws IOException {
        rewriteLastPairs();
        for (Pair pair : this.pairs) {
            if (!pair.hasBeenWritten()) {
                pair.writeTo(this.f8936os);
            }
        }
        this.pairs.clear();
    }

    private void writeWritablePairs(int i) throws IOException {
        Iterator<Pair> descendingIterator = this.pairs.descendingIterator();
        while (descendingIterator.hasNext()) {
            Pair next = descendingIterator.next();
            if (next.hasBeenWritten()) {
                break;
            } else {
                i += next.length();
            }
        }
        for (Pair pair : this.pairs) {
            if (!pair.hasBeenWritten()) {
                i -= pair.length();
                if (!pair.canBeWritten(i)) {
                    return;
                } else {
                    pair.writeTo(this.f8936os);
                }
            }
        }
    }

    private void rewriteLastPairs() {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        Iterator<Pair> descendingIterator = this.pairs.descendingIterator();
        int i = 0;
        while (descendingIterator.hasNext()) {
            Pair next = descendingIterator.next();
            if (next.hasBeenWritten()) {
                break;
            }
            int length = next.length();
            linkedList2.addFirst(Integer.valueOf(length));
            linkedList.addFirst(next);
            i += length;
            if (i >= 12) {
                break;
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            this.pairs.remove((Pair) it.next());
        }
        int size = linkedList.size();
        int i2 = 0;
        for (int i3 = 1; i3 < size; i3++) {
            i2 += ((Integer) linkedList2.get(i3)).intValue();
        }
        Pair pair = new Pair();
        if (i2 > 0) {
            pair.prependLiteral(expand(i2, i2));
        }
        Pair pair2 = (Pair) linkedList.get(0);
        int i4 = 12 - i2;
        int backReferenceLength = pair2.hasBackReference() ? pair2.backReferenceLength() : 0;
        if (!pair2.hasBackReference() || backReferenceLength < i4 + 4) {
            if (pair2.hasBackReference()) {
                pair.prependLiteral(expand(i2 + backReferenceLength, backReferenceLength));
            }
            pair2.prependTo(pair);
        } else {
            pair.prependLiteral(expand(i2 + i4, i4));
            this.pairs.add(pair2.splitWithNewBackReferenceLengthOf(backReferenceLength - i4));
        }
        this.pairs.add(pair);
    }

    public static Parameters.Builder createParameterBuilder() {
        return Parameters.builder(65536).withMinBackReferenceLength(4).withMaxBackReferenceLength(65535).withMaxOffset(65535).withMaxLiteralLength(65535);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static final class Pair {
        private int brLength;
        private int brOffset;
        private final Deque<byte[]> literals = new LinkedList();
        private boolean written;

        private static int lengths(int i, int i2) {
            int i3 = 15;
            if (i >= 15) {
                i = 15;
            }
            if (i2 < 4) {
                i3 = 0;
            } else if (i2 < 19) {
                i3 = i2 - 4;
            }
            return (i << 4) | i3;
        }

        Pair() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void prependLiteral(byte[] bArr) {
            this.literals.addFirst(bArr);
        }

        byte[] addLiteral(LZ77Compressor.LiteralBlock literalBlock) {
            byte[] copyOfRange = Arrays.copyOfRange(literalBlock.getData(), literalBlock.getOffset(), literalBlock.getOffset() + literalBlock.getLength());
            this.literals.add(copyOfRange);
            return copyOfRange;
        }

        void setBackReference(LZ77Compressor.BackReference backReference) {
            if (hasBackReference()) {
                throw new IllegalStateException();
            }
            this.brOffset = backReference.getOffset();
            this.brLength = backReference.getLength();
        }

        boolean hasBackReference() {
            return this.brOffset > 0;
        }

        boolean canBeWritten(int i) {
            return hasBackReference() && i >= 16;
        }

        int length() {
            return literalLength() + this.brLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasBeenWritten() {
            return this.written;
        }

        void writeTo(OutputStream outputStream) throws IOException {
            int literalLength = literalLength();
            outputStream.write(lengths(literalLength, this.brLength));
            if (literalLength >= 15) {
                writeLength(literalLength - 15, outputStream);
            }
            Iterator<byte[]> it = this.literals.iterator();
            while (it.hasNext()) {
                outputStream.write(it.next());
            }
            if (hasBackReference()) {
                ByteUtils.toLittleEndian(outputStream, this.brOffset, 2);
                int i = this.brLength;
                if (i - 4 >= 15) {
                    writeLength((i - 4) - 15, outputStream);
                }
            }
            this.written = true;
        }

        private int literalLength() {
            Iterator<byte[]> it = this.literals.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().length;
            }
            return i;
        }

        private static void writeLength(int i, OutputStream outputStream) throws IOException {
            while (i >= 255) {
                outputStream.write(255);
                i -= 255;
            }
            outputStream.write(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int backReferenceLength() {
            return this.brLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void prependTo(Pair pair) {
            Iterator<byte[]> descendingIterator = this.literals.descendingIterator();
            while (descendingIterator.hasNext()) {
                pair.prependLiteral(descendingIterator.next());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Pair splitWithNewBackReferenceLengthOf(int i) {
            Pair pair = new Pair();
            pair.literals.addAll(this.literals);
            pair.brOffset = this.brOffset;
            pair.brLength = i;
            return pair;
        }
    }
}
