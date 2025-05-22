package io.netty.handler.codec.http2;

import com.google.common.base.Ascii;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http2.HpackUtil;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ThrowableUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class HpackDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte READ_HEADER_REPRESENTATION = 0;
    private static final byte READ_INDEXED_HEADER = 2;
    private static final byte READ_INDEXED_HEADER_NAME = 3;
    private static final byte READ_LITERAL_HEADER_NAME = 6;
    private static final byte READ_LITERAL_HEADER_NAME_LENGTH = 5;
    private static final byte READ_LITERAL_HEADER_NAME_LENGTH_PREFIX = 4;
    private static final byte READ_LITERAL_HEADER_VALUE = 9;
    private static final byte READ_LITERAL_HEADER_VALUE_LENGTH = 8;
    private static final byte READ_LITERAL_HEADER_VALUE_LENGTH_PREFIX = 7;
    private static final byte READ_MAX_DYNAMIC_TABLE_SIZE = 1;
    private long encoderMaxDynamicTableSize;
    private final HpackDynamicTable hpackDynamicTable;
    private final HpackHuffmanDecoder hpackHuffmanDecoder;
    private long maxDynamicTableSize;
    private boolean maxDynamicTableSizeChangeRequired;
    private long maxHeaderListSize;
    private long maxHeaderListSizeGoAway;
    private static final Http2Exception DECODE_ULE_128_DECOMPRESSION_EXCEPTION = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - decompression failure", new Object[0]), HpackDecoder.class, "decodeULE128(..)");
    private static final Http2Exception DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - long overflow", new Object[0]), HpackDecoder.class, "decodeULE128(..)");
    private static final Http2Exception DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - int overflow", new Object[0]), HpackDecoder.class, "decodeULE128ToInt(..)");
    private static final Http2Exception DECODE_ILLEGAL_INDEX_VALUE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", new Object[0]), HpackDecoder.class, "decode(..)");
    private static final Http2Exception INDEX_HEADER_ILLEGAL_INDEX_VALUE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", new Object[0]), HpackDecoder.class, "indexHeader(..)");
    private static final Http2Exception READ_NAME_ILLEGAL_INDEX_VALUE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", new Object[0]), HpackDecoder.class, "readName(..)");
    private static final Http2Exception INVALID_MAX_DYNAMIC_TABLE_SIZE = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - invalid max dynamic table size", new Object[0]), HpackDecoder.class, "setDynamicTableSize(..)");
    private static final Http2Exception MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED = (Http2Exception) ThrowableUtil.unknownStackTrace(Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "HPACK - max dynamic table size change required", new Object[0]), HpackDecoder.class, "decode(..)");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum HeaderType {
        REGULAR_HEADER,
        REQUEST_PSEUDO_HEADER,
        RESPONSE_PSEUDO_HEADER
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private interface Sink {
        void appendToHeaderList(CharSequence charSequence, CharSequence charSequence2);

        void finish() throws Http2Exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HpackDecoder(long j, int i) {
        this(j, i, 4096);
    }

    HpackDecoder(long j, int i, int i2) {
        this.maxHeaderListSize = ObjectUtil.checkPositive(j, "maxHeaderListSize");
        this.maxHeaderListSizeGoAway = Http2CodecUtil.calculateMaxHeaderListSizeGoAway(j);
        long j2 = i2;
        this.encoderMaxDynamicTableSize = j2;
        this.maxDynamicTableSize = j2;
        this.maxDynamicTableSizeChangeRequired = false;
        this.hpackDynamicTable = new HpackDynamicTable(j2);
        this.hpackHuffmanDecoder = new HpackHuffmanDecoder(i);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0020. Please report as an issue. */
    public void decode(int i, ByteBuf byteBuf, Http2Headers http2Headers, boolean z) throws Http2Exception {
        int i2;
        int i3;
        CharSequence charSequence;
        int i4;
        HeaderType validate;
        HeaderType headerType = null;
        HpackUtil.IndexType indexType = HpackUtil.IndexType.NONE;
        CharSequence charSequence2 = null;
        long j = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        boolean z2 = false;
        while (byteBuf.isReadable()) {
            switch (i8) {
                case 0:
                    i2 = i5;
                    i3 = i6;
                    charSequence = charSequence2;
                    byte readByte = byteBuf.readByte();
                    if (this.maxDynamicTableSizeChangeRequired && (readByte & 224) != 32) {
                        throw MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED;
                    }
                    if (readByte < 0) {
                        int i9 = readByte & Byte.MAX_VALUE;
                        if (i9 == 0) {
                            throw DECODE_ILLEGAL_INDEX_VALUE;
                        }
                        if (i9 != 127) {
                            HpackHeaderField indexedHeader = getIndexedHeader(i9);
                            HeaderType validate2 = validate(indexedHeader.name, headerType, z);
                            j = addHeader(http2Headers, indexedHeader.name, indexedHeader.value, j);
                            headerType = validate2;
                            i8 = i8;
                        } else {
                            i8 = 2;
                        }
                        i7 = i9;
                        i6 = i3;
                        charSequence2 = charSequence;
                        i5 = i2;
                    } else if ((readByte & 64) == 64) {
                        indexType = HpackUtil.IndexType.INCREMENTAL;
                        i7 = readByte & 63;
                        if (i7 == 0) {
                            i6 = i3;
                            charSequence2 = charSequence;
                            i5 = i2;
                            i8 = 4;
                        } else if (i7 != 63) {
                            charSequence2 = readName(i7);
                            headerType = validate(charSequence2, headerType, z);
                            i6 = charSequence2.length();
                            i5 = i2;
                            i8 = 7;
                        } else {
                            i6 = i3;
                            charSequence2 = charSequence;
                            i5 = i2;
                            i8 = 3;
                        }
                    } else if ((readByte & 32) == 32) {
                        i7 = readByte & Ascii.f1926US;
                        if (i7 == 31) {
                            i6 = i3;
                            charSequence2 = charSequence;
                            i8 = 1;
                            i5 = i2;
                        } else {
                            setDynamicTableSize(i7);
                            i6 = i3;
                            charSequence2 = charSequence;
                            i5 = i2;
                            i8 = 0;
                        }
                    } else {
                        indexType = (readByte & 16) == 16 ? HpackUtil.IndexType.NEVER : HpackUtil.IndexType.NONE;
                        i7 = readByte & 15;
                        if (i7 == 0) {
                            i6 = i3;
                            charSequence2 = charSequence;
                            i5 = i2;
                            i8 = 4;
                        } else if (i7 != 15) {
                            charSequence2 = readName(i7);
                            headerType = validate(charSequence2, headerType, z);
                            i6 = charSequence2.length();
                            i5 = i2;
                            i8 = 7;
                        } else {
                            i6 = i3;
                            charSequence2 = charSequence;
                            i5 = i2;
                            i8 = 3;
                        }
                    }
                    break;
                case 1:
                    int i10 = i7;
                    setDynamicTableSize(decodeULE128(byteBuf, i10));
                    i7 = i10;
                    i6 = i6;
                    i8 = 0;
                case 2:
                    i2 = i5;
                    i3 = i6;
                    i4 = i7;
                    charSequence = charSequence2;
                    HpackHeaderField indexedHeader2 = getIndexedHeader(decodeULE128(byteBuf, i4));
                    HeaderType validate3 = validate(indexedHeader2.name, headerType, z);
                    j = addHeader(http2Headers, indexedHeader2.name, indexedHeader2.value, j);
                    headerType = validate3;
                    i7 = i4;
                    i6 = i3;
                    charSequence2 = charSequence;
                    i5 = i2;
                    i8 = 0;
                case 3:
                    i2 = i5;
                    charSequence2 = readName(decodeULE128(byteBuf, i7));
                    headerType = validate(charSequence2, headerType, z);
                    i6 = charSequence2.length();
                    i5 = i2;
                    i8 = 7;
                case 4:
                    i2 = i5;
                    i3 = i6;
                    charSequence = charSequence2;
                    byte readByte2 = byteBuf.readByte();
                    z2 = (readByte2 & 128) == 128;
                    i6 = readByte2 & Byte.MAX_VALUE;
                    if (i6 == 127) {
                        i8 = 5;
                        i7 = i6;
                        i6 = i3;
                        charSequence2 = charSequence;
                        i5 = i2;
                    } else {
                        long j2 = i6;
                        long j3 = this.maxHeaderListSizeGoAway;
                        if (j2 > j3 - j) {
                            Http2CodecUtil.headerListSizeExceeded(j3);
                        }
                        i7 = i6;
                        charSequence2 = charSequence;
                        i5 = i2;
                        i8 = 6;
                    }
                case 5:
                    i2 = i5;
                    int i11 = i7;
                    charSequence = charSequence2;
                    i6 = decodeULE128(byteBuf, i11);
                    long j4 = i6;
                    long j5 = this.maxHeaderListSizeGoAway;
                    if (j4 > j5 - j) {
                        Http2CodecUtil.headerListSizeExceeded(j5);
                    }
                    i7 = i11;
                    charSequence2 = charSequence;
                    i5 = i2;
                    i8 = 6;
                case 6:
                    i2 = i5;
                    int i12 = i6;
                    int i13 = i7;
                    if (byteBuf.readableBytes() < i12) {
                        throw notEnoughDataException(byteBuf);
                    }
                    charSequence2 = readStringLiteral(byteBuf, i12, z2);
                    headerType = validate(charSequence2, headerType, z);
                    i7 = i13;
                    i6 = i12;
                    i5 = i2;
                    i8 = 7;
                case 7:
                    i2 = i5;
                    i3 = i6;
                    charSequence = charSequence2;
                    byte readByte3 = byteBuf.readByte();
                    z2 = (readByte3 & 128) == 128;
                    i4 = readByte3 & Byte.MAX_VALUE;
                    if (i4 == 0) {
                        validate = validate(charSequence, headerType, z);
                        j = insertHeader(http2Headers, charSequence, AsciiString.EMPTY_STRING, indexType, j);
                        headerType = validate;
                        i7 = i4;
                        i6 = i3;
                        charSequence2 = charSequence;
                        i5 = i2;
                        i8 = 0;
                    } else if (i4 != 127) {
                        long j6 = i4 + i3;
                        long j7 = this.maxHeaderListSizeGoAway;
                        if (j6 > j7 - j) {
                            Http2CodecUtil.headerListSizeExceeded(j7);
                        }
                        i5 = i4;
                        i7 = i5;
                        i6 = i3;
                        charSequence2 = charSequence;
                        i8 = 9;
                    } else {
                        i8 = 8;
                        i7 = i4;
                        i6 = i3;
                        charSequence2 = charSequence;
                        i5 = i2;
                    }
                case 8:
                    i3 = i6;
                    int i14 = i7;
                    charSequence = charSequence2;
                    i5 = decodeULE128(byteBuf, i14);
                    long j8 = i5 + i3;
                    long j9 = this.maxHeaderListSizeGoAway;
                    if (j8 > j9 - j) {
                        Http2CodecUtil.headerListSizeExceeded(j9);
                    }
                    i7 = i14;
                    i6 = i3;
                    charSequence2 = charSequence;
                    i8 = 9;
                case 9:
                    if (byteBuf.readableBytes() < i5) {
                        throw notEnoughDataException(byteBuf);
                    }
                    CharSequence readStringLiteral = readStringLiteral(byteBuf, i5, z2);
                    validate = validate(charSequence2, headerType, z);
                    i2 = i5;
                    i4 = i7;
                    i3 = i6;
                    charSequence = charSequence2;
                    j = insertHeader(http2Headers, charSequence2, readStringLiteral, indexType, j);
                    headerType = validate;
                    i7 = i4;
                    i6 = i3;
                    charSequence2 = charSequence;
                    i5 = i2;
                    i8 = 0;
                default:
                    throw new Error("should not reach here state: " + i8);
            }
        }
        int i15 = i8;
        long j10 = this.maxHeaderListSize;
        if (j > j10) {
            Http2CodecUtil.headerListSizeExceeded(i, j10, true);
        }
        if (i15 != 0) {
            throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "Incomplete header block fragment.", new Object[0]);
        }
    }

    public void setMaxHeaderTableSize(long j) throws Http2Exception {
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxDynamicTableSize = j;
        if (j < this.encoderMaxDynamicTableSize) {
            this.maxDynamicTableSizeChangeRequired = true;
            this.hpackDynamicTable.setCapacity(j);
        }
    }

    public void setMaxHeaderListSize(long j, long j2) throws Http2Exception {
        if (j2 < j || j2 < 0) {
            throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Header List Size GO_AWAY %d must be positive and >= %d", Long.valueOf(j2), Long.valueOf(j));
        }
        if (j < 0 || j > 4294967295L) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", 0L, 4294967295L, Long.valueOf(j));
        }
        this.maxHeaderListSize = j;
        this.maxHeaderListSizeGoAway = j2;
    }

    public long getMaxHeaderListSize() {
        return this.maxHeaderListSize;
    }

    public long getMaxHeaderListSizeGoAway() {
        return this.maxHeaderListSizeGoAway;
    }

    public long getMaxHeaderTableSize() {
        return this.hpackDynamicTable.capacity();
    }

    int length() {
        return this.hpackDynamicTable.length();
    }

    long size() {
        return this.hpackDynamicTable.size();
    }

    HpackHeaderField getHeaderField(int i) {
        return this.hpackDynamicTable.getEntry(i + 1);
    }

    private void setDynamicTableSize(long j) throws Http2Exception {
        if (j > this.maxDynamicTableSize) {
            throw INVALID_MAX_DYNAMIC_TABLE_SIZE;
        }
        this.encoderMaxDynamicTableSize = j;
        this.maxDynamicTableSizeChangeRequired = false;
        this.hpackDynamicTable.setCapacity(j);
    }

    private HeaderType validate(CharSequence charSequence, HeaderType headerType, boolean z) throws Http2Exception {
        if (!z) {
            return null;
        }
        if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(charSequence)) {
            if (headerType == HeaderType.REGULAR_HEADER) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Pseudo-header field '%s' found after regular header.", charSequence);
            }
            Http2Headers.PseudoHeaderName pseudoHeader = Http2Headers.PseudoHeaderName.getPseudoHeader(charSequence);
            if (pseudoHeader == null) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 pseudo-header '%s' encountered.", charSequence);
            }
            HeaderType headerType2 = pseudoHeader.isRequestOnly() ? HeaderType.REQUEST_PSEUDO_HEADER : HeaderType.RESPONSE_PSEUDO_HEADER;
            if (headerType == null || headerType2 == headerType) {
                return headerType2;
            }
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Mix of request and response pseudo-headers.", new Object[0]);
        }
        return HeaderType.REGULAR_HEADER;
    }

    private CharSequence readName(int i) throws Http2Exception {
        if (i <= HpackStaticTable.length) {
            return HpackStaticTable.getEntry(i).name;
        }
        if (i - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
            return this.hpackDynamicTable.getEntry(i - HpackStaticTable.length).name;
        }
        throw READ_NAME_ILLEGAL_INDEX_VALUE;
    }

    private HpackHeaderField getIndexedHeader(int i) throws Http2Exception {
        if (i <= HpackStaticTable.length) {
            return HpackStaticTable.getEntry(i);
        }
        if (i - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
            return this.hpackDynamicTable.getEntry(i - HpackStaticTable.length);
        }
        throw INDEX_HEADER_ILLEGAL_INDEX_VALUE;
    }

    private long insertHeader(Http2Headers http2Headers, CharSequence charSequence, CharSequence charSequence2, HpackUtil.IndexType indexType, long j) throws Http2Exception {
        long addHeader = addHeader(http2Headers, charSequence, charSequence2, j);
        int i = C72081.$SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[indexType.ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3) {
                this.hpackDynamicTable.add(new HpackHeaderField(charSequence, charSequence2));
            } else {
                throw new Error("should not reach here");
            }
        }
        return addHeader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.http2.HpackDecoder$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C72081 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType;

        static {
            int[] iArr = new int[HpackUtil.IndexType.values().length];
            $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType = iArr;
            try {
                iArr[HpackUtil.IndexType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[HpackUtil.IndexType.NEVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$http2$HpackUtil$IndexType[HpackUtil.IndexType.INCREMENTAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private long addHeader(Http2Headers http2Headers, CharSequence charSequence, CharSequence charSequence2, long j) throws Http2Exception {
        long sizeOf = j + HpackHeaderField.sizeOf(charSequence, charSequence2);
        long j2 = this.maxHeaderListSizeGoAway;
        if (sizeOf > j2) {
            Http2CodecUtil.headerListSizeExceeded(j2);
        }
        http2Headers.add((Http2Headers) charSequence, charSequence2);
        return sizeOf;
    }

    private CharSequence readStringLiteral(ByteBuf byteBuf, int i, boolean z) throws Http2Exception {
        if (z) {
            return this.hpackHuffmanDecoder.decode(byteBuf, i);
        }
        byte[] bArr = new byte[i];
        byteBuf.readBytes(bArr);
        return new AsciiString(bArr, false);
    }

    private static IllegalArgumentException notEnoughDataException(ByteBuf byteBuf) {
        return new IllegalArgumentException("decode only works with an entire header block! " + byteBuf);
    }

    static int decodeULE128(ByteBuf byteBuf, int i) throws Http2Exception {
        int readerIndex = byteBuf.readerIndex();
        long decodeULE128 = decodeULE128(byteBuf, i);
        if (decodeULE128 <= 2147483647L) {
            return (int) decodeULE128;
        }
        byteBuf.readerIndex(readerIndex);
        throw DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION;
    }

    static long decodeULE128(ByteBuf byteBuf, long j) throws Http2Exception {
        int i = 0;
        boolean z = j == 0;
        int writerIndex = byteBuf.writerIndex();
        int readerIndex = byteBuf.readerIndex();
        while (readerIndex < writerIndex) {
            byte b = byteBuf.getByte(readerIndex);
            if (i == 56 && ((b & 128) != 0 || (b == Byte.MAX_VALUE && !z))) {
                throw DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION;
            }
            if ((b & 128) == 0) {
                byteBuf.readerIndex(readerIndex + 1);
                return j + ((b & 127) << i);
            }
            j += (b & 127) << i;
            readerIndex++;
            i += 7;
        }
        throw DECODE_ULE_128_DECOMPRESSION_EXCEPTION;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private static final class Http2HeadersSink implements Sink {
        private boolean exceededMaxLength;
        private final Http2Headers headers;
        private long headersLength;
        private final long maxHeaderListSize;
        private HeaderType previousType;
        private final int streamId;
        private final boolean validate;
        private Http2Exception validationException;

        Http2HeadersSink(int i, Http2Headers http2Headers, long j, boolean z) {
            this.headers = http2Headers;
            this.maxHeaderListSize = j;
            this.streamId = i;
            this.validate = z;
        }

        @Override // io.netty.handler.codec.http2.HpackDecoder.Sink
        public void finish() throws Http2Exception {
            if (this.exceededMaxLength) {
                Http2CodecUtil.headerListSizeExceeded(this.streamId, this.maxHeaderListSize, true);
                return;
            }
            Http2Exception http2Exception = this.validationException;
            if (http2Exception != null) {
                throw http2Exception;
            }
        }

        @Override // io.netty.handler.codec.http2.HpackDecoder.Sink
        public void appendToHeaderList(CharSequence charSequence, CharSequence charSequence2) {
            this.headersLength += HpackHeaderField.sizeOf(charSequence, charSequence2);
            this.exceededMaxLength |= this.headersLength > this.maxHeaderListSize;
            if (this.exceededMaxLength || this.validationException != null) {
                return;
            }
            if (this.validate) {
                try {
                    this.previousType = HpackDecoder.access$000(this.streamId, charSequence, this.previousType);
                } catch (Http2Exception e) {
                    this.validationException = e;
                    return;
                }
            }
            this.headers.add((Http2Headers) charSequence, charSequence2);
        }
    }
}
