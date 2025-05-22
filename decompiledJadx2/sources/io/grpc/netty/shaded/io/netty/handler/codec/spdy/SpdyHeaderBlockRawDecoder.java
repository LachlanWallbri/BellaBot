package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SpdyHeaderBlockRawDecoder extends SpdyHeaderBlockDecoder {
    private static final int LENGTH_FIELD_SIZE = 4;
    private ByteBuf cumulation;
    private int headerSize;
    private int length;
    private final int maxHeaderSize;
    private String name;
    private int numHeaders;
    private State state;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        READ_NUM_HEADERS,
        READ_NAME_LENGTH,
        READ_NAME,
        SKIP_NAME,
        READ_VALUE_LENGTH,
        READ_VALUE,
        SKIP_VALUE,
        END_HEADER_BLOCK,
        ERROR
    }

    public SpdyHeaderBlockRawDecoder(SpdyVersion spdyVersion, int i) {
        ObjectUtil.checkNotNull(spdyVersion, "spdyVersion");
        this.maxHeaderSize = i;
        this.state = State.READ_NUM_HEADERS;
    }

    private static int readLengthField(ByteBuf byteBuf) {
        int signedInt = SpdyCodecUtil.getSignedInt(byteBuf, byteBuf.readerIndex());
        byteBuf.skipBytes(4);
        return signedInt;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    void decode(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        ObjectUtil.checkNotNull(byteBuf, "headerBlock");
        ObjectUtil.checkNotNull(spdyHeadersFrame, TypedValues.Attributes.S_FRAME);
        ByteBuf byteBuf2 = this.cumulation;
        if (byteBuf2 == null) {
            decodeHeaderBlock(byteBuf, spdyHeadersFrame);
            if (byteBuf.isReadable()) {
                this.cumulation = byteBufAllocator.buffer(byteBuf.readableBytes());
                this.cumulation.writeBytes(byteBuf);
                return;
            }
            return;
        }
        byteBuf2.writeBytes(byteBuf);
        decodeHeaderBlock(this.cumulation, spdyHeadersFrame);
        if (this.cumulation.isReadable()) {
            this.cumulation.discardReadBytes();
        } else {
            releaseBuffer();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0000 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decodeHeaderBlock(ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        int i;
        int i2;
        int i3;
        while (byteBuf.isReadable()) {
            switch (this.state) {
                case READ_NUM_HEADERS:
                    if (byteBuf.readableBytes() >= 4) {
                        this.numHeaders = readLengthField(byteBuf);
                        int i4 = this.numHeaders;
                        if (i4 >= 0) {
                            if (i4 == 0) {
                                this.state = State.END_HEADER_BLOCK;
                                break;
                            } else {
                                this.state = State.READ_NAME_LENGTH;
                                break;
                            }
                        } else {
                            this.state = State.ERROR;
                            spdyHeadersFrame.setInvalid();
                            break;
                        }
                    } else {
                        return;
                    }
                case READ_NAME_LENGTH:
                    if (byteBuf.readableBytes() >= 4) {
                        this.length = readLengthField(byteBuf);
                        int i5 = this.length;
                        if (i5 <= 0) {
                            this.state = State.ERROR;
                            spdyHeadersFrame.setInvalid();
                            break;
                        } else {
                            int i6 = this.maxHeaderSize;
                            if (i5 > i6 || (i = this.headerSize) > i6 - i5) {
                                this.headerSize = this.maxHeaderSize + 1;
                                this.state = State.SKIP_NAME;
                                spdyHeadersFrame.setTruncated();
                                break;
                            } else {
                                this.headerSize = i + i5;
                                this.state = State.READ_NAME;
                                break;
                            }
                        }
                    } else {
                        return;
                    }
                case READ_NAME:
                    int readableBytes = byteBuf.readableBytes();
                    int i7 = this.length;
                    if (readableBytes >= i7) {
                        byte[] bArr = new byte[i7];
                        byteBuf.readBytes(bArr);
                        this.name = new String(bArr, "UTF-8");
                        if (spdyHeadersFrame.headers().contains(this.name)) {
                            this.state = State.ERROR;
                            spdyHeadersFrame.setInvalid();
                            break;
                        } else {
                            this.state = State.READ_VALUE_LENGTH;
                            break;
                        }
                    } else {
                        return;
                    }
                case SKIP_NAME:
                    int min = Math.min(byteBuf.readableBytes(), this.length);
                    byteBuf.skipBytes(min);
                    this.length -= min;
                    if (this.length != 0) {
                        break;
                    } else {
                        this.state = State.READ_VALUE_LENGTH;
                        break;
                    }
                case READ_VALUE_LENGTH:
                    if (byteBuf.readableBytes() >= 4) {
                        this.length = readLengthField(byteBuf);
                        int i8 = this.length;
                        if (i8 >= 0) {
                            if (i8 == 0) {
                                if (!spdyHeadersFrame.isTruncated()) {
                                    spdyHeadersFrame.headers().add((SpdyHeaders) this.name, "");
                                }
                                this.name = null;
                                int i9 = this.numHeaders - 1;
                                this.numHeaders = i9;
                                if (i9 == 0) {
                                    this.state = State.END_HEADER_BLOCK;
                                    break;
                                } else {
                                    this.state = State.READ_NAME_LENGTH;
                                    break;
                                }
                            } else {
                                int i10 = this.maxHeaderSize;
                                if (i8 > i10 || (i2 = this.headerSize) > i10 - i8) {
                                    this.headerSize = this.maxHeaderSize + 1;
                                    this.name = null;
                                    this.state = State.SKIP_VALUE;
                                    spdyHeadersFrame.setTruncated();
                                    break;
                                } else {
                                    this.headerSize = i2 + i8;
                                    this.state = State.READ_VALUE;
                                    break;
                                }
                            }
                        } else {
                            this.state = State.ERROR;
                            spdyHeadersFrame.setInvalid();
                            break;
                        }
                    } else {
                        return;
                    }
                case READ_VALUE:
                    int readableBytes2 = byteBuf.readableBytes();
                    int i11 = this.length;
                    if (readableBytes2 < i11) {
                        return;
                    }
                    byte[] bArr2 = new byte[i11];
                    byteBuf.readBytes(bArr2);
                    int i12 = 0;
                    if (bArr2[0] == 0) {
                        this.state = State.ERROR;
                        spdyHeadersFrame.setInvalid();
                        break;
                    } else {
                        int i13 = 0;
                        while (i12 < this.length) {
                            while (i12 < bArr2.length && bArr2[i12] != 0) {
                                i12++;
                            }
                            if (i12 < bArr2.length && ((i3 = i12 + 1) == bArr2.length || bArr2[i3] == 0)) {
                                this.state = State.ERROR;
                                spdyHeadersFrame.setInvalid();
                            } else {
                                try {
                                    spdyHeadersFrame.headers().add((SpdyHeaders) this.name, new String(bArr2, i13, i12 - i13, "UTF-8"));
                                    i13 = i12 + 1;
                                    i12 = i13;
                                } catch (IllegalArgumentException unused) {
                                    this.state = State.ERROR;
                                    spdyHeadersFrame.setInvalid();
                                }
                            }
                            this.name = null;
                            if (this.state == State.ERROR) {
                                int i14 = this.numHeaders - 1;
                                this.numHeaders = i14;
                                if (i14 == 0) {
                                    this.state = State.END_HEADER_BLOCK;
                                    break;
                                } else {
                                    this.state = State.READ_NAME_LENGTH;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        this.name = null;
                        if (this.state == State.ERROR) {
                        }
                    }
                    break;
                case SKIP_VALUE:
                    int min2 = Math.min(byteBuf.readableBytes(), this.length);
                    byteBuf.skipBytes(min2);
                    this.length -= min2;
                    if (this.length != 0) {
                        break;
                    } else {
                        int i15 = this.numHeaders - 1;
                        this.numHeaders = i15;
                        if (i15 == 0) {
                            this.state = State.END_HEADER_BLOCK;
                            break;
                        } else {
                            this.state = State.READ_NAME_LENGTH;
                            break;
                        }
                    }
                case END_HEADER_BLOCK:
                    this.state = State.ERROR;
                    spdyHeadersFrame.setInvalid();
                    break;
                case ERROR:
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    return;
                default:
                    throw new Error("Shouldn't reach here.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    public void endHeaderBlock(SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        if (this.state != State.END_HEADER_BLOCK) {
            spdyHeadersFrame.setInvalid();
        }
        releaseBuffer();
        this.headerSize = 0;
        this.name = null;
        this.state = State.READ_NUM_HEADERS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    public void end() {
        releaseBuffer();
    }

    private void releaseBuffer() {
        ByteBuf byteBuf = this.cumulation;
        if (byteBuf != null) {
            byteBuf.release();
            this.cumulation = null;
        }
    }
}
