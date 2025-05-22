package com.pudutech.factory_test.esp32;

import com.pudutech.mirsdk.hardware.base.CommonKt;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: ESPProtocol.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 12\u00020\u0001:\u00011B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010%\u001a\u00020\nJ\u0006\u0010&\u001a\u00020\u001aJ\u0006\u0010'\u001a\u00020\u001aJ\u000e\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nJ\u0006\u0010*\u001a\u00020\nJ\u0006\u0010+\u001a\u00020\nJ\b\u0010,\u001a\u00020-H\u0016J#\u0010.\u001a\u0004\u0018\u00010\u001e*\u00020\n2\u0006\u0010)\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u001eH\u0002¢\u0006\u0002\u00100R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001f\u0010\u0012\u001a\u00020\u0013X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u0011\u0010\u0017\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u000eR\u001a\u0010\"\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\f\"\u0004\b$\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/ESPProtocol;", "", "()V", "channel", "", "getChannel", "()B", "setChannel", "(B)V", "crc", "", "getCrc", "()[B", "setCrc", "([B)V", "data", "getData", "setData", "dataLen", "Lkotlin/UByte;", "getDataLen", "setDataLen-7apg3OU", "B", "head", "getHead", "<set-?>", "", "isParseSuccess", "()Z", "leastSize", "", "type", "getType", "setType", "version", "getVersion", "setVersion", "calculateCRC", "checkCRCOK", "isDataType", "parse", "bytes", "toCheckTypeFrame", "toDataTypeFrame", "toString", "", "match", "startIndex", "([B[BI)Ljava/lang/Integer;", "Companion", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ESPProtocol {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte[] TYPE_CHECK;
    private static final byte[] TYPE_DATA;
    private byte[] crc;
    private byte[] data;
    private boolean isParseSuccess;
    private final int leastSize;
    private byte[] type;
    private byte[] version;
    private final byte[] head = {(byte) 170, (byte) 85};
    private byte dataLen = UByte.m4528constructorimpl((byte) 0);
    private byte channel = (byte) 2;

    /* compiled from: ESPProtocol.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/ESPProtocol$Companion;", "", "()V", "TYPE_CHECK", "", "getTYPE_CHECK", "()[B", "TYPE_DATA", "getTYPE_DATA", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] getTYPE_DATA() {
            return ESPProtocol.TYPE_DATA;
        }

        public final byte[] getTYPE_CHECK() {
            return ESPProtocol.TYPE_CHECK;
        }
    }

    public ESPProtocol() {
        byte[] bArr = TYPE_DATA;
        this.type = bArr;
        byte[] bArr2 = {0, 0};
        this.crc = bArr2;
        byte[] bArr3 = {0, 0, 0, 0};
        this.version = bArr3;
        this.data = new byte[0];
        this.leastSize = this.head.length + 1 + 1 + bArr.length + bArr2.length + bArr3.length;
    }

    static {
        byte b = (byte) 218;
        TYPE_DATA = new byte[]{b, b};
        byte b2 = (byte) 117;
        TYPE_CHECK = new byte[]{b2, b2};
    }

    public final byte[] getHead() {
        return this.head;
    }

    public final byte getDataLen() {
        return this.dataLen;
    }

    /* renamed from: setDataLen-7apg3OU, reason: not valid java name */
    public final void m4337setDataLen7apg3OU(byte b) {
        this.dataLen = b;
    }

    public final byte getChannel() {
        return this.channel;
    }

    public final void setChannel(byte b) {
        this.channel = b;
    }

    public final byte[] getType() {
        return this.type;
    }

    public final void setType(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.type = bArr;
    }

    public final byte[] getCrc() {
        return this.crc;
    }

    public final void setCrc(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.crc = bArr;
    }

    public final byte[] getVersion() {
        return this.version;
    }

    public final void setVersion(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.version = bArr;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final void setData(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.data = bArr;
    }

    public final byte[] toDataTypeFrame() {
        return ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(this.head, this.dataLen), this.channel), TYPE_DATA), this.crc), this.version), this.data);
    }

    public final byte[] toCheckTypeFrame() {
        return ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(this.head, this.dataLen), this.channel), TYPE_CHECK), this.crc), this.version);
    }

    public final byte[] calculateCRC() {
        short value = new ESPCRC().update(this.head).update(this.dataLen).update(this.channel).update(this.type).update(new byte[]{0, 0}).update(this.version).update(this.data).getValue();
        byte[] bArr = {0, 0};
        bArr[0] = (byte) value;
        bArr[1] = (byte) UInt.m4595constructorimpl(UInt.m4595constructorimpl(value & UShort.MAX_VALUE) >>> 8);
        return bArr;
    }

    public final boolean checkCRCOK() {
        byte[] calculateCRC = calculateCRC();
        byte b = calculateCRC[0];
        byte[] bArr = this.crc;
        return b == bArr[0] && calculateCRC[1] == bArr[1];
    }

    private final Integer match(byte[] bArr, byte[] bArr2, int i) {
        int lastIndex;
        if (ArraysKt.getLastIndex(bArr2) + i <= ArraysKt.getLastIndex(bArr) && i <= (lastIndex = ArraysKt.getLastIndex(bArr))) {
            while (true) {
                int length = bArr2.length;
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i + i2;
                    if (i3 > ArraysKt.getLastIndex(bArr)) {
                        return null;
                    }
                    if (bArr[i3] != bArr2[i2]) {
                        break;
                    }
                    if (i2 == ArraysKt.getLastIndex(bArr2)) {
                        return Integer.valueOf(i);
                    }
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return null;
    }

    /* renamed from: isParseSuccess, reason: from getter */
    public final boolean getIsParseSuccess() {
        return this.isParseSuccess;
    }

    public final byte[] parse(byte[] bytes) {
        Integer match;
        Byte orNull;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.isParseSuccess = false;
        if (bytes.length < this.leastSize || (match = match(bytes, this.head, 0)) == null) {
            return bytes;
        }
        int intValue = match.intValue();
        int length = this.head.length + 0;
        Byte orNull2 = ArraysKt.getOrNull(bytes, length);
        if (orNull2 == null) {
            return bytes;
        }
        byte m4528constructorimpl = UByte.m4528constructorimpl(orNull2.byteValue());
        this.dataLen = m4528constructorimpl;
        int i = length + 1;
        if (intValue + this.leastSize + (m4528constructorimpl & 255) > bytes.length || (orNull = ArraysKt.getOrNull(bytes, i)) == null) {
            return bytes;
        }
        this.channel = orNull.byteValue();
        int i2 = i + 1;
        byte[] sliceArray = ArraysKt.sliceArray(bytes, RangesKt.until(i2, this.type.length + i2));
        this.type = sliceArray;
        int length2 = i2 + sliceArray.length;
        if (match(sliceArray, TYPE_CHECK, 0) == null && match(this.type, TYPE_DATA, 0) == null) {
            return ArraysKt.sliceArray(bytes, new IntRange(length2, ArraysKt.getLastIndex(bytes)));
        }
        byte[] sliceArray2 = ArraysKt.sliceArray(bytes, RangesKt.until(length2, this.crc.length + length2));
        this.crc = sliceArray2;
        int length3 = length2 + sliceArray2.length;
        byte[] sliceArray3 = ArraysKt.sliceArray(bytes, RangesKt.until(length3, this.version.length + length3));
        this.version = sliceArray3;
        int length4 = length3 + sliceArray3.length;
        if (this.dataLen == UByte.m4528constructorimpl((byte) 0)) {
            this.data = new byte[0];
        } else {
            this.data = ArraysKt.sliceArray(bytes, RangesKt.until(length4, (this.dataLen & 255) + length4));
            length4 += this.dataLen & 255;
        }
        this.isParseSuccess = true;
        return length4 > ArraysKt.getLastIndex(bytes) ? new byte[0] : ArraysKt.sliceArray(bytes, new IntRange(length4, ArraysKt.getLastIndex(bytes)));
    }

    public final boolean isDataType() {
        Integer match = match(this.type, TYPE_DATA, 0);
        return match != null && match.intValue() == 0;
    }

    public String toString() {
        return "channel=" + ((int) this.channel) + " crc=" + CommonKt.toHexString(this.crc) + " type=" + CommonKt.toHexString(this.type) + " dataLen=" + UByte.m4563toStringimpl(this.dataLen) + " version=" + CommonKt.toHexString(this.version);
    }
}
