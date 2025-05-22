package io.netty.handler.codec.spdy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
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
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
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
        if (spdyVersion == null) {
            throw new NullPointerException("spdyVersion");
        }
        this.maxHeaderSize = i;
        this.state = State.READ_NUM_HEADERS;
    }

    private static int readLengthField(ByteBuf byteBuf) {
        int signedInt = SpdyCodecUtil.getSignedInt(byteBuf, byteBuf.readerIndex());
        byteBuf.skipBytes(4);
        return signedInt;
    }

    @Override // io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    void decode(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        if (byteBuf == null) {
            throw new NullPointerException("headerBlock");
        }
        if (spdyHeadersFrame == null) {
            throw new NullPointerException(TypedValues.Attributes.S_FRAME);
        }
        ByteBuf byteBuf2 = this.cumulation;
        if (byteBuf2 == null) {
            decodeHeaderBlock(byteBuf, spdyHeadersFrame);
            if (byteBuf.isReadable()) {
                ByteBuf buffer = byteBufAllocator.buffer(byteBuf.readableBytes());
                this.cumulation = buffer;
                buffer.writeBytes(byteBuf);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C72831 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$spdy$SpdyHeaderBlockRawDecoder$State */
        static final /* synthetic */ int[] f8542x30e8720d;

        static {
            int[] iArr = new int[State.values().length];
            f8542x30e8720d = iArr;
            try {
                iArr[State.READ_NUM_HEADERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8542x30e8720d[State.READ_NAME_LENGTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8542x30e8720d[State.READ_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8542x30e8720d[State.SKIP_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8542x30e8720d[State.READ_VALUE_LENGTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8542x30e8720d[State.READ_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8542x30e8720d[State.SKIP_VALUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8542x30e8720d[State.END_HEADER_BLOCK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8542x30e8720d[State.ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0000 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decodeHeaderBlock(ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        int i;
        int i2;
        int i3;
        while (byteBuf.isReadable()) {
            switch (C72831.f8542x30e8720d[this.state.ordinal()]) {
                case 1:
                    if (byteBuf.readableBytes() >= 4) {
                        int readLengthField = readLengthField(byteBuf);
                        this.numHeaders = readLengthField;
                        if (readLengthField >= 0) {
                            if (readLengthField == 0) {
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
                case 2:
                    if (byteBuf.readableBytes() >= 4) {
                        int readLengthField2 = readLengthField(byteBuf);
                        this.length = readLengthField2;
                        if (readLengthField2 <= 0) {
                            this.state = State.ERROR;
                            spdyHeadersFrame.setInvalid();
                            break;
                        } else {
                            int i4 = this.maxHeaderSize;
                            if (readLengthField2 > i4 || (i = this.headerSize) > i4 - readLengthField2) {
                                this.headerSize = this.maxHeaderSize + 1;
                                this.state = State.SKIP_NAME;
                                spdyHeadersFrame.setTruncated();
                                break;
                            } else {
                                this.headerSize = i + readLengthField2;
                                this.state = State.READ_NAME;
                                break;
                            }
                        }
                    } else {
                        return;
                    }
                case 3:
                    int readableBytes = byteBuf.readableBytes();
                    int i5 = this.length;
                    if (readableBytes >= i5) {
                        byte[] bArr = new byte[i5];
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
                case 4:
                    int min = Math.min(byteBuf.readableBytes(), this.length);
                    byteBuf.skipBytes(min);
                    int i6 = this.length - min;
                    this.length = i6;
                    if (i6 != 0) {
                        break;
                    } else {
                        this.state = State.READ_VALUE_LENGTH;
                        break;
                    }
                case 5:
                    if (byteBuf.readableBytes() >= 4) {
                        int readLengthField3 = readLengthField(byteBuf);
                        this.length = readLengthField3;
                        if (readLengthField3 >= 0) {
                            if (readLengthField3 == 0) {
                                if (!spdyHeadersFrame.isTruncated()) {
                                    spdyHeadersFrame.headers().add((SpdyHeaders) this.name, "");
                                }
                                this.name = null;
                                int i7 = this.numHeaders - 1;
                                this.numHeaders = i7;
                                if (i7 == 0) {
                                    this.state = State.END_HEADER_BLOCK;
                                    break;
                                } else {
                                    this.state = State.READ_NAME_LENGTH;
                                    break;
                                }
                            } else {
                                int i8 = this.maxHeaderSize;
                                if (readLengthField3 > i8 || (i2 = this.headerSize) > i8 - readLengthField3) {
                                    this.headerSize = this.maxHeaderSize + 1;
                                    this.name = null;
                                    this.state = State.SKIP_VALUE;
                                    spdyHeadersFrame.setTruncated();
                                    break;
                                } else {
                                    this.headerSize = i2 + readLengthField3;
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
                case 6:
                    int readableBytes2 = byteBuf.readableBytes();
                    int i9 = this.length;
                    if (readableBytes2 < i9) {
                        return;
                    }
                    byte[] bArr2 = new byte[i9];
                    byteBuf.readBytes(bArr2);
                    int i10 = 0;
                    if (bArr2[0] == 0) {
                        this.state = State.ERROR;
                        spdyHeadersFrame.setInvalid();
                        break;
                    } else {
                        int i11 = 0;
                        while (i10 < this.length) {
                            while (i10 < i9 && bArr2[i10] != 0) {
                                i10++;
                            }
                            if (i10 < i9 && ((i3 = i10 + 1) == i9 || bArr2[i3] == 0)) {
                                this.state = State.ERROR;
                                spdyHeadersFrame.setInvalid();
                            } else {
                                try {
                                    spdyHeadersFrame.headers().add((SpdyHeaders) this.name, new String(bArr2, i11, i10 - i11, "UTF-8"));
                                    i11 = i10 + 1;
                                    i10 = i11;
                                } catch (IllegalArgumentException unused) {
                                    this.state = State.ERROR;
                                    spdyHeadersFrame.setInvalid();
                                }
                            }
                            this.name = null;
                            if (this.state == State.ERROR) {
                                int i12 = this.numHeaders - 1;
                                this.numHeaders = i12;
                                if (i12 == 0) {
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
                case 7:
                    int min2 = Math.min(byteBuf.readableBytes(), this.length);
                    byteBuf.skipBytes(min2);
                    int i13 = this.length - min2;
                    this.length = i13;
                    if (i13 != 0) {
                        break;
                    } else {
                        int i14 = this.numHeaders - 1;
                        this.numHeaders = i14;
                        if (i14 == 0) {
                            this.state = State.END_HEADER_BLOCK;
                            break;
                        } else {
                            this.state = State.READ_NAME_LENGTH;
                            break;
                        }
                    }
                case 8:
                    this.state = State.ERROR;
                    spdyHeadersFrame.setInvalid();
                    break;
                case 9:
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    return;
                default:
                    throw new Error("Shouldn't reach here.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
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
    @Override // io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
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
