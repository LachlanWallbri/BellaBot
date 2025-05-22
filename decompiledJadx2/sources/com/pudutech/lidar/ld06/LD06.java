package com.pudutech.lidar.ld06;

import android.os.SystemClock;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.felhr.utils.HexData;
import com.google.common.base.Ascii;
import com.pudutech.base.Pdlog;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LD06.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\bH\u0002J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0012H\u0002J\u0018\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\bH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\fH\u0002J\b\u0010&\u001a\u00020%H\u0002J\u0010\u0010'\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\fH\u0002J\u0010\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020\fH\u0016J\b\u0010*\u001a\u00020%H\u0016J\b\u0010+\u001a\u00020%H\u0016J\f\u0010,\u001a\u00020\b*\u00020\u001cH\u0002J\f\u0010-\u001a\u00020\b*\u00020\u001cH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/lidar/ld06/LD06;", "Lcom/pudutech/lidar/base/SerialLidar;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "baudRate", "", "getBaudRate", "()I", "cache", "", "crcTable", "", "isStartFlag", "", "lastDegree", "", "oneFrameNodes", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/LidarNode;", "startTimestamp", "", "struct", "Lcom/pudutech/lidar/ld06/LD06DataStruct;", "timestamp", "calCRC8", "", "bytes", "startIndex", "toIndex", "checkOneFrameComplete", "degree", "findHead", "Lcom/pudutech/lidar/ld06/LD06$FindResult;", "handleData", "", "oneComplete", "parseLD06", "parser", "src", "startScan", "stopScan", "shl8Bit", "toU8", "FindResult", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LD06 extends SerialLidar {
    private long startTimestamp;
    private long timestamp;
    private final String TAG = "LD06_Lidar";
    private final int[] crcTable = {0, 77, 154, 215, 121, 52, 227, 174, 242, 191, 104, 37, 139, 198, 17, 92, 169, 228, 51, 126, 208, 157, 74, 7, 91, 22, 193, 140, 34, 111, 184, 245, 31, 82, 133, 200, 102, 43, 252, 177, 237, 160, 119, 58, 148, 217, 14, 67, 182, 251, 44, 97, HttpStatus.SC_MULTI_STATUS, 130, 85, 24, 68, 9, 222, 147, 61, 112, 167, 234, 62, 115, 164, 233, 71, 10, 221, 144, 204, 129, 86, 27, 181, GateControllerMsg.ControlCode.Error, 47, 98, 151, 218, 13, 64, 238, 163, 116, 57, 101, 40, 255, 178, 28, 81, 134, HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, 33, 108, 187, 246, 88, 21, 194, 143, Primes.SMALL_FACTOR_LIMIT, 158, 73, 4, 170, 231, 48, 125, 136, 197, 18, 95, 241, 188, 107, 38, 122, 55, 224, 173, 3, 78, 153, 212, 124, 49, 230, 171, 5, 72, 159, 210, 142, 195, 20, 89, 247, 186, 109, 32, DimensionsKt.TVDPI, 152, 79, 2, 172, 225, 54, 123, 39, 106, 189, DimensionsKt.HDPI, 94, 19, 196, 137, 99, 46, 249, 180, 26, 87, 128, HttpStatus.SC_RESET_CONTENT, 145, 220, 11, 70, 232, 165, 114, 63, 202, 135, 80, 29, 179, 254, 41, 100, 56, 117, 162, 239, 65, 12, 219, 150, 66, 15, 216, 149, 59, 118, 161, 236, 176, 253, 42, 103, 201, 132, 83, 30, 235, 166, 113, 60, 146, 223, 8, 69, 25, 84, 131, HttpStatus.SC_PARTIAL_CONTENT, 96, 45, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 183, 93, 16, 199, 138, 36, 105, 190, 243, 175, 226, 53, 120, 214, 155, 76, 1, 244, 185, 110, 35, 141, 192, 23, 90, 6, 75, 156, 209, 127, 50, 229, 168};
    private byte[] cache = new byte[0];
    private final LD06DataStruct struct = new LD06DataStruct();
    private ArrayList<LidarNode> oneFrameNodes = new ArrayList<>();
    private boolean isStartFlag = true;
    private double lastDegree = -1.0d;

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 230400;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (SystemClock.elapsedRealtime() - this.startTimestamp < 10000) {
            Pdlog.m3273d(getTAG(), "receive after start scan in 10s. " + HexData.hexToString(src));
        }
        handleData(src);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        this.startTimestamp = SystemClock.elapsedRealtime();
    }

    static /* synthetic */ byte calCRC8$default(LD06 ld06, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ld06.calCRC8(bArr, i, i2);
    }

    private final byte calCRC8(byte[] bytes, int startIndex, int toIndex) {
        int i = 0;
        while (startIndex < toIndex) {
            i = this.crcTable[(i ^ toU8(bytes[startIndex])) & 255];
            startIndex++;
        }
        return (byte) i;
    }

    private final void handleData(byte[] bytes) {
        this.cache = ArraysKt.plus(this.cache, bytes);
        int i = 0;
        while (true) {
            int structBytesSize = this.struct.getStructBytesSize() + i;
            byte[] bArr = this.cache;
            if (structBytesSize <= bArr.length) {
                FindResult findHead = findHead(bArr, i);
                if (!findHead.getHasFind()) {
                    this.cache = ArraysKt.copyOfRange(this.cache, findHead.getStopIndex(), this.cache.length);
                    return;
                }
                int stopIndex = findHead.getStopIndex();
                int stopIndex2 = findHead.getStopIndex() + this.struct.getStructBytesSize();
                byte[] bArr2 = this.cache;
                if (stopIndex2 <= bArr2.length) {
                    parseLD06(ArraysKt.copyOfRange(bArr2, stopIndex, this.struct.getStructBytesSize() + stopIndex));
                    stopIndex += this.struct.getStructBytesSize();
                }
                i = stopIndex;
            } else {
                this.cache = ArraysKt.copyOfRange(bArr, i, bArr.length);
                return;
            }
        }
    }

    private final void parseLD06(byte[] bytes) {
        double d;
        int len;
        byte calCRC8 = calCRC8(bytes, 0, bytes.length - 1);
        if (calCRC8 != ArraysKt.last(bytes)) {
            Pdlog.m3277w(getTAG(), "crc fail. cal=" + ((int) calCRC8) + " receive=" + ((int) ArraysKt.last(bytes)));
            return;
        }
        double radians = Math.toRadians((toU8(bytes[4]) | shl8Bit(bytes[5])) / 100.0d);
        double radians2 = Math.toRadians((toU8(bytes[ArraysKt.getLastIndex(bytes) - 4]) | shl8Bit(bytes[ArraysKt.getLastIndex(bytes) - 3])) / 100.0d);
        if (radians2 > radians) {
            d = radians2 - radians;
            len = this.struct.getLen();
        } else {
            d = (radians2 + 6.283185307179586d) - radians;
            len = this.struct.getLen();
        }
        double d2 = d / (len - 1);
        int len2 = this.struct.getLen();
        for (int i = 0; i < len2; i++) {
            LidarNode lidarNode = new LidarNode();
            lidarNode.angleInRad = (i * d2) + radians;
            lidarNode.distance_m = (toU8(bytes[r8]) | shl8Bit(bytes[r8 + 1])) / 1000.0d;
            lidarNode.quality = toU8(bytes[(i * 3) + 6 + 2]);
            this.oneFrameNodes.add(lidarNode);
        }
        if ((!this.oneFrameNodes.isEmpty()) && checkOneFrameComplete(Math.toDegrees(((LidarNode) CollectionsKt.last((List) this.oneFrameNodes)).angleInRad))) {
            oneComplete();
        }
    }

    private final int toU8(byte b) {
        return UInt.m4595constructorimpl(b) & 255;
    }

    private final int shl8Bit(byte b) {
        return toU8(b) << 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: LD06.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/lidar/ld06/LD06$FindResult;", "", "hasFind", "", "stopIndex", "", "(ZI)V", "getHasFind", "()Z", "getStopIndex", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class FindResult {
        private final boolean hasFind;
        private final int stopIndex;

        public static /* synthetic */ FindResult copy$default(FindResult findResult, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = findResult.hasFind;
            }
            if ((i2 & 2) != 0) {
                i = findResult.stopIndex;
            }
            return findResult.copy(z, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getHasFind() {
            return this.hasFind;
        }

        /* renamed from: component2, reason: from getter */
        public final int getStopIndex() {
            return this.stopIndex;
        }

        public final FindResult copy(boolean hasFind, int stopIndex) {
            return new FindResult(hasFind, stopIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FindResult)) {
                return false;
            }
            FindResult findResult = (FindResult) other;
            return this.hasFind == findResult.hasFind && this.stopIndex == findResult.stopIndex;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.hasFind;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.stopIndex;
        }

        public String toString() {
            return "FindResult(hasFind=" + this.hasFind + ", stopIndex=" + this.stopIndex + ")";
        }

        public FindResult(boolean z, int i) {
            this.hasFind = z;
            this.stopIndex = i;
        }

        public final boolean getHasFind() {
            return this.hasFind;
        }

        public final int getStopIndex() {
            return this.stopIndex;
        }
    }

    private final FindResult findHead(byte[] bytes, int startIndex) {
        int lastIndex = ArraysKt.getLastIndex(bytes);
        if (startIndex <= lastIndex) {
            while (true) {
                if (bytes[startIndex] == this.struct.getHeader()) {
                    if (startIndex < ArraysKt.getLastIndex(bytes) && (bytes[startIndex + 1] & Ascii.f1926US) == this.struct.getLen()) {
                        return new FindResult(true, startIndex);
                    }
                    if (startIndex == ArraysKt.getLastIndex(bytes)) {
                        return new FindResult(false, startIndex);
                    }
                }
                if (startIndex == lastIndex) {
                    break;
                }
                startIndex++;
            }
        }
        return new FindResult(false, bytes.length);
    }

    private final boolean checkOneFrameComplete(double degree) {
        double d = this.lastDegree;
        this.lastDegree = degree;
        if (degree < d) {
            return false;
        }
        if (degree > 315 || degree < 135) {
            if (this.isStartFlag) {
                this.isStartFlag = false;
                return true;
            }
        } else {
            this.isStartFlag = true;
        }
        return false;
    }

    private final void oneComplete() {
        if (this.oneFrameNodes.isEmpty()) {
            Pdlog.m3277w(getTAG(), "empty frame");
            return;
        }
        LidarListener listener = getListener();
        if (listener != null) {
            Pdlog.m3276v(getTAG(), "on One Frame Complete");
            listener.onOneFrameComplete(this.oneFrameNodes);
        } else {
            Pdlog.m3277w(getTAG(), "lidarCallback is null");
        }
        if (SystemClock.elapsedRealtime() - this.timestamp > 200) {
            Pdlog.m3277w(getTAG(), "one frame complete too long " + (SystemClock.elapsedRealtime() - this.timestamp));
        }
        this.timestamp = SystemClock.elapsedRealtime();
        this.oneFrameNodes = new ArrayList<>();
    }

    /* compiled from: LD06.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/lidar/ld06/LD06$Companion;", "", "()V", "ANGLE_UNIT", "", "CRC_TABLE", "", "DISTANCE_UNIT", "FRAME_COMPLETE_TIME", "", "MATH_2_PI", "MAX_DEGREE", "MIN_DEGREE", "RECEIVE_TIME_OUT", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
