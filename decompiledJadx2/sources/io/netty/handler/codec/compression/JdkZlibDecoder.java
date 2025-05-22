package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class JdkZlibDecoder extends ZlibDecoder {
    private static final int FCOMMENT = 16;
    private static final int FEXTRA = 4;
    private static final int FHCRC = 2;
    private static final int FNAME = 8;
    private static final int FRESERVED = 224;
    private final ByteBufChecksum crc;
    private boolean decideZlibOrNone;
    private final boolean decompressConcatenated;
    private final byte[] dictionary;
    private volatile boolean finished;
    private int flags;
    private GzipState gzipState;
    private Inflater inflater;
    private int xlen;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public enum GzipState {
        HEADER_START,
        HEADER_END,
        FLG_READ,
        XLEN_READ,
        SKIP_FNAME,
        SKIP_COMMENT,
        PROCESS_FHCRC,
        FOOTER_START
    }

    public JdkZlibDecoder() {
        this(ZlibWrapper.ZLIB, null, false);
    }

    public JdkZlibDecoder(byte[] bArr) {
        this(ZlibWrapper.ZLIB, bArr, false);
    }

    public JdkZlibDecoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, null, false);
    }

    public JdkZlibDecoder(ZlibWrapper zlibWrapper, boolean z) {
        this(zlibWrapper, null, z);
    }

    public JdkZlibDecoder(boolean z) {
        this(ZlibWrapper.GZIP, null, z);
    }

    private JdkZlibDecoder(ZlibWrapper zlibWrapper, byte[] bArr, boolean z) {
        this.gzipState = GzipState.HEADER_START;
        this.flags = -1;
        this.xlen = -1;
        if (zlibWrapper == null) {
            throw new NullPointerException("wrapper");
        }
        this.decompressConcatenated = z;
        int i = C71181.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[zlibWrapper.ordinal()];
        if (i == 1) {
            this.inflater = new Inflater(true);
            this.crc = ByteBufChecksum.wrapChecksum(new CRC32());
        } else if (i == 2) {
            this.inflater = new Inflater(true);
            this.crc = null;
        } else if (i == 3) {
            this.inflater = new Inflater();
            this.crc = null;
        } else if (i == 4) {
            this.decideZlibOrNone = true;
            this.crc = null;
        } else {
            throw new IllegalArgumentException("Only GZIP or ZLIB is supported, but you used " + zlibWrapper);
        }
        this.dictionary = bArr;
    }

    @Override // io.netty.handler.codec.compression.ZlibDecoder
    public boolean isClosed() {
        return this.finished;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        boolean z;
        if (this.finished) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            return;
        }
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            return;
        }
        if (this.decideZlibOrNone) {
            if (readableBytes < 2) {
                return;
            }
            this.inflater = new Inflater(!looksLikeZlib(byteBuf.getShort(byteBuf.readerIndex())));
            this.decideZlibOrNone = false;
        }
        if (this.crc != null) {
            if (C71181.f8479x5be85941[this.gzipState.ordinal()] == 1) {
                if (readGZIPFooter(byteBuf)) {
                    this.finished = true;
                    return;
                }
                return;
            } else if (this.gzipState != GzipState.HEADER_END && !readGZIPHeader(byteBuf)) {
                return;
            } else {
                readableBytes = byteBuf.readableBytes();
            }
        }
        if (byteBuf.hasArray()) {
            this.inflater.setInput(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), readableBytes);
        } else {
            byte[] bArr = new byte[readableBytes];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            this.inflater.setInput(bArr);
        }
        ByteBuf heapBuffer = channelHandlerContext.alloc().heapBuffer(this.inflater.getRemaining() << 1);
        while (true) {
            try {
                try {
                    if (this.inflater.needsInput()) {
                        break;
                    }
                    byte[] array = heapBuffer.array();
                    int writerIndex = heapBuffer.writerIndex();
                    int arrayOffset = heapBuffer.arrayOffset() + writerIndex;
                    int inflate = this.inflater.inflate(array, arrayOffset, heapBuffer.writableBytes());
                    if (inflate > 0) {
                        heapBuffer.writerIndex(writerIndex + inflate);
                        if (this.crc != null) {
                            this.crc.update(array, arrayOffset, inflate);
                        }
                    } else if (this.inflater.needsDictionary()) {
                        if (this.dictionary == null) {
                            throw new DecompressionException("decompression failure, unable to set dictionary as non was specified");
                        }
                        this.inflater.setDictionary(this.dictionary);
                    }
                    if (this.inflater.finished()) {
                        if (this.crc == null) {
                            this.finished = true;
                        } else {
                            z = true;
                        }
                    } else {
                        heapBuffer.ensureWritable(this.inflater.getRemaining() << 1);
                    }
                } catch (DataFormatException e) {
                    throw new DecompressionException("decompression failure", e);
                }
            } finally {
                if (heapBuffer.isReadable()) {
                    list.add(heapBuffer);
                } else {
                    heapBuffer.release();
                }
            }
        }
        z = false;
        byteBuf.skipBytes(readableBytes - this.inflater.getRemaining());
        if (z) {
            this.gzipState = GzipState.FOOTER_START;
            if (readGZIPFooter(byteBuf)) {
                this.finished = this.decompressConcatenated ? false : true;
                if (!this.finished) {
                    this.inflater.reset();
                    this.crc.reset();
                    this.gzipState = GzipState.HEADER_START;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* renamed from: io.netty.handler.codec.compression.JdkZlibDecoder$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C71181 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper;

        static {
            int[] iArr = new int[GzipState.values().length];
            f8479x5be85941 = iArr;
            try {
                iArr[GzipState.FOOTER_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8479x5be85941[GzipState.HEADER_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8479x5be85941[GzipState.FLG_READ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8479x5be85941[GzipState.XLEN_READ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8479x5be85941[GzipState.SKIP_FNAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8479x5be85941[GzipState.SKIP_COMMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8479x5be85941[GzipState.PROCESS_FHCRC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8479x5be85941[GzipState.HEADER_END.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[ZlibWrapper.values().length];
            $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = iArr2;
            try {
                iArr2[ZlibWrapper.GZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB_OR_NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerRemoved0(channelHandlerContext);
        Inflater inflater = this.inflater;
        if (inflater != null) {
            inflater.end();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000f. Please report as an issue. */
    private boolean readGZIPHeader(ByteBuf byteBuf) {
        switch (this.gzipState) {
            case HEADER_START:
                if (byteBuf.readableBytes() < 10) {
                    return false;
                }
                byte readByte = byteBuf.readByte();
                byte readByte2 = byteBuf.readByte();
                if (readByte != 31) {
                    throw new DecompressionException("Input is not in the GZIP format");
                }
                this.crc.update(readByte);
                this.crc.update(readByte2);
                short readUnsignedByte = byteBuf.readUnsignedByte();
                if (readUnsignedByte != 8) {
                    throw new DecompressionException("Unsupported compression method " + ((int) readUnsignedByte) + " in the GZIP header");
                }
                this.crc.update(readUnsignedByte);
                short readUnsignedByte2 = byteBuf.readUnsignedByte();
                this.flags = readUnsignedByte2;
                this.crc.update(readUnsignedByte2);
                if ((this.flags & FRESERVED) != 0) {
                    throw new DecompressionException("Reserved flags are set in the GZIP header");
                }
                this.crc.update(byteBuf, byteBuf.readerIndex(), 4);
                byteBuf.skipBytes(4);
                this.crc.update(byteBuf.readUnsignedByte());
                this.crc.update(byteBuf.readUnsignedByte());
                this.gzipState = GzipState.FLG_READ;
            case FLG_READ:
                if ((this.flags & 4) != 0) {
                    if (byteBuf.readableBytes() < 2) {
                        return false;
                    }
                    short readUnsignedByte3 = byteBuf.readUnsignedByte();
                    short readUnsignedByte4 = byteBuf.readUnsignedByte();
                    this.crc.update(readUnsignedByte3);
                    this.crc.update(readUnsignedByte4);
                    this.xlen = (readUnsignedByte3 << 8) | readUnsignedByte4 | this.xlen;
                }
                this.gzipState = GzipState.XLEN_READ;
            case XLEN_READ:
                if (this.xlen != -1) {
                    if (byteBuf.readableBytes() < this.xlen) {
                        return false;
                    }
                    this.crc.update(byteBuf, byteBuf.readerIndex(), this.xlen);
                    byteBuf.skipBytes(this.xlen);
                }
                this.gzipState = GzipState.SKIP_FNAME;
            case SKIP_FNAME:
                if ((this.flags & 8) != 0) {
                    if (!byteBuf.isReadable()) {
                        return false;
                    }
                    do {
                        short readUnsignedByte5 = byteBuf.readUnsignedByte();
                        this.crc.update(readUnsignedByte5);
                        if (readUnsignedByte5 == 0) {
                        }
                    } while (byteBuf.isReadable());
                }
                this.gzipState = GzipState.SKIP_COMMENT;
            case SKIP_COMMENT:
                if ((this.flags & 16) != 0) {
                    if (!byteBuf.isReadable()) {
                        return false;
                    }
                    do {
                        short readUnsignedByte6 = byteBuf.readUnsignedByte();
                        this.crc.update(readUnsignedByte6);
                        if (readUnsignedByte6 == 0) {
                        }
                    } while (byteBuf.isReadable());
                }
                this.gzipState = GzipState.PROCESS_FHCRC;
            case PROCESS_FHCRC:
                if ((this.flags & 2) != 0) {
                    if (byteBuf.readableBytes() < 4) {
                        return false;
                    }
                    verifyCrc(byteBuf);
                }
                this.crc.reset();
                this.gzipState = GzipState.HEADER_END;
                return true;
            case HEADER_END:
                return true;
            default:
                throw new IllegalStateException();
        }
    }

    private boolean readGZIPFooter(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() < 8) {
            return false;
        }
        verifyCrc(byteBuf);
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i |= byteBuf.readUnsignedByte() << (i2 * 8);
        }
        int totalOut = this.inflater.getTotalOut();
        if (i == totalOut) {
            return true;
        }
        throw new DecompressionException("Number of bytes mismatch. Expected: " + i + ", Got: " + totalOut);
    }

    private void verifyCrc(ByteBuf byteBuf) {
        long j = 0;
        for (int i = 0; i < 4; i++) {
            j |= byteBuf.readUnsignedByte() << (i * 8);
        }
        long value = this.crc.getValue();
        if (j == value) {
            return;
        }
        throw new DecompressionException("CRC value mismatch. Expected: " + j + ", Got: " + value);
    }

    private static boolean looksLikeZlib(short s) {
        return (s & 30720) == 30720 && s % 31 == 0;
    }
}
