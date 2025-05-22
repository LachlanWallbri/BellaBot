package com.pudutech.lidar.rslidar_16;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.util.ByteArrayWrapper;
import com.tencent.bugly.BuglyStrategy;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RSLIDAR_16.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u0019H\u0002J \u00105\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u00192\u0006\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u00020\u0016H\u0002J\b\u00108\u001a\u00020\u0019H\u0002J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000202H\u0016J\u0010\u0010<\u001a\u00020:2\u0006\u0010=\u001a\u000202H\u0016JB\u0010>\u001a\u00020:2\u0006\u0010\r\u001a\u00020\u00192\u0006\u00106\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u00162\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0002ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\b\u0010F\u001a\u00020:H\u0002J\b\u0010G\u001a\u00020:H\u0016J\b\u0010H\u001a\u00020:H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0006R\u0014\u0010&\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u0014\u0010+\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010(R\u0014\u0010/\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006I"}, m3961d2 = {"Lcom/pudutech/lidar/rslidar_16/RSLIDAR_16;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "eth0IP", "getEth0IP", "format", "Ljava/text/DecimalFormat;", "getFormat", "()Ljava/text/DecimalFormat;", "frameTime", "Lcom/pudutech/lidar/rslidar_16/FrameTime;", "lidarNodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "getLidarNodes", "()Lcom/pudutech/lidar/ListLidarNodePool;", "setLidarNodes", "(Lcom/pudutech/lidar/ListLidarNodePool;)V", "mBlocksPerFrame", "", "mChannelDataGroupPerBlocks", "mCurrentDegree", "", "mCurrentFrameTimeStamp", "mDataHeadVerifyByte", "mLastDegree", "mLastFrameTimeStamp", "mLastUnitBlockId", "mParseOriginDataWrapper", "Lcom/pudutech/lidar/util/ByteArrayWrapper;", "mParseSecondDataPerBlockWrapper", "oneFrameComplete", "", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "unitBlockByteArray", "", "formatData", "data", "getExactPointTime", "unitBlockId", "channelId", "getMiddleDegree", "parseStatusData", "", "statusSrc", "parser", "src", "processLidarNode", "azimuth", "distance_cm", "Lkotlin/UShort;", "reflectivity", "Lkotlin/UByte;", "processLidarNode-7Wvb02o", "(DIDISB)V", "sendFrame", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RSLIDAR_16 extends EthernetLidar {
    private FrameTime frameTime;
    private double mCurrentFrameTimeStamp;
    private double mLastFrameTimeStamp;
    private int mLastUnitBlockId;
    private ByteArrayWrapper mParseOriginDataWrapper;
    private ByteArrayWrapper mParseSecondDataPerBlockWrapper;
    private boolean oneFrameComplete;
    private final int udpStatusPackageSize;
    private final int udpStatusPort;
    private byte[] unitBlockByteArray;
    private final String eth0IP = "192.168.1.102";
    private final String socketIP = "192.168.1.200";
    private final int socketPort = 6699;
    private final int udpPort = 6699;
    private final int udpPackageSize = 1248;
    private final String TAG = "RSLIDAR_16";
    private final int mDataHeadVerifyByte = 8;
    private final int mBlocksPerFrame = 12;
    private final int mChannelDataGroupPerBlocks = 16;
    private double mLastDegree = -1.0d;
    private double mCurrentDegree = -1.0d;
    private ListLidarNodePool lidarNodes = ListLidarNodePool.INSTANCE.obtain(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
    private final DecimalFormat format = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));

    private final double getExactPointTime(double frameTime, int unitBlockId, int channelId) {
        return frameTime + ((((unitBlockId - 1) * 55.5d) + ((channelId - 1) * 2.8d)) / 1000000.0d);
    }

    public RSLIDAR_16() {
        Pdlog.m3273d(getTAG(), "RSLIDAR_16 lidar init");
        this.format.setRoundingMode(RoundingMode.FLOOR);
        this.mParseOriginDataWrapper = new ByteArrayWrapper();
        this.mParseSecondDataPerBlockWrapper = new ByteArrayWrapper();
    }

    @Override // com.pudutech.lidar.base.EthernetLidar
    public String getEth0IP() {
        return this.eth0IP;
    }

    @Override // com.pudutech.lidar.base.EthernetLidar
    public String getSocketIP() {
        return this.socketIP;
    }

    @Override // com.pudutech.lidar.base.EthernetLidar
    public int getSocketPort() {
        return this.socketPort;
    }

    @Override // com.pudutech.lidar.base.EthernetLidar
    public int getUdpPort() {
        return this.udpPort;
    }

    @Override // com.pudutech.lidar.base.EthernetLidar
    public int getUdpPackageSize() {
        return this.udpPackageSize;
    }

    public int getUdpStatusPort() {
        return this.udpStatusPort;
    }

    public int getUdpStatusPackageSize() {
        return this.udpStatusPackageSize;
    }

    public void parseStatusData(byte[] statusSrc) {
        Intrinsics.checkParameterIsNotNull(statusSrc, "statusSrc");
        setSnGetSucceed(true);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public String getTAG() {
        return this.TAG;
    }

    public final ListLidarNodePool getLidarNodes() {
        return this.lidarNodes;
    }

    public final void setLidarNodes(ListLidarNodePool listLidarNodePool) {
        Intrinsics.checkParameterIsNotNull(listLidarNodePool, "<set-?>");
        this.lidarNodes = listLidarNodePool;
    }

    public final DecimalFormat getFormat() {
        return this.format;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01e6  */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v5 */
    @Override // com.pudutech.lidar.base.BaseLidar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void parser(byte[] src) {
        String str;
        int i;
        double d;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteArrayWrapper byteArrayWrapper = this.mParseOriginDataWrapper;
        if (byteArrayWrapper == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper.prepare(src);
        int i7 = this.mDataHeadVerifyByte;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            int i10 = 1;
            if (i9 < i7) {
                ByteArrayWrapper byteArrayWrapper2 = this.mParseOriginDataWrapper;
                if ((byteArrayWrapper2 != null ? UByte.m4522boximpl(byteArrayWrapper2.popUByte()) : null) == null ? true : !UByte.m4535equalsimpl0(r12.getData(), UByteArray.m4577getimpl(BaseConfigOfRsLidar16.INSTANCE.getDataHeadVerify(), i9))) {
                    Pdlog.m3274e(getTAG(), "Udp data head verify error");
                    return;
                }
                i9++;
            } else {
                ByteArrayWrapper byteArrayWrapper3 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper3 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper3.indexPlus(22);
                ByteArrayWrapper byteArrayWrapper4 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper4 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper4.popUByte();
                ByteArrayWrapper byteArrayWrapper5 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper5 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper5.indexPlus(11);
                this.mCurrentFrameTimeStamp = SystemClock.elapsedRealtime() / 1000.0d;
                int i11 = this.mBlocksPerFrame;
                int i12 = 0;
                while (i12 < i11) {
                    this.mLastDegree = this.mCurrentDegree;
                    ByteArrayWrapper byteArrayWrapper6 = this.mParseOriginDataWrapper;
                    if (byteArrayWrapper6 == null) {
                        Intrinsics.throwNpe();
                    }
                    byteArrayWrapper6.popUShort();
                    if (this.mParseOriginDataWrapper == null) {
                        Intrinsics.throwNpe();
                    }
                    this.mCurrentDegree = (r0.popUShort() & UShort.MAX_VALUE) / 100.0d;
                    double middleDegree = getMiddleDegree();
                    String str2 = ",size  = ";
                    if (middleDegree >= 0.0d && this.mLastDegree > middleDegree) {
                        String tag = getTAG();
                        Object[] objArr = new Object[i10];
                        objArr[i8] = "RSLIDAR_16 :mLastDegree = " + this.mLastDegree + ",middleDegree = " + middleDegree + ",size  = " + this.lidarNodes.size();
                        Pdlog.m3273d(tag, objArr);
                        this.oneFrameComplete = i10;
                        sendFrame();
                    }
                    byte[] bArr = this.unitBlockByteArray;
                    if (bArr != null) {
                        if (bArr == null) {
                            Intrinsics.throwNpe();
                        }
                        if (bArr.length > 0) {
                            ByteArrayWrapper byteArrayWrapper7 = this.mParseSecondDataPerBlockWrapper;
                            if (byteArrayWrapper7 == null) {
                                Intrinsics.throwNpe();
                            }
                            byte[] bArr2 = this.unitBlockByteArray;
                            if (bArr2 == null) {
                                Intrinsics.throwNpe();
                            }
                            byteArrayWrapper7.prepare(bArr2);
                            int i13 = this.mChannelDataGroupPerBlocks;
                            int i14 = i8;
                            while (i14 < i13) {
                                double d2 = this.mLastFrameTimeStamp;
                                int i15 = (this.mLastUnitBlockId * 2) + 2;
                                int i16 = i14 + 1;
                                ByteArrayWrapper byteArrayWrapper8 = this.mParseSecondDataPerBlockWrapper;
                                if (byteArrayWrapper8 == null) {
                                    Intrinsics.throwNpe();
                                }
                                short popUShort = byteArrayWrapper8.popUShort();
                                ByteArrayWrapper byteArrayWrapper9 = this.mParseSecondDataPerBlockWrapper;
                                if (byteArrayWrapper9 == null) {
                                    Intrinsics.throwNpe();
                                }
                                m4407processLidarNode7Wvb02o(d2, i15, middleDegree, i16, popUShort, byteArrayWrapper9.popUByte());
                                str2 = str2;
                                middleDegree = middleDegree;
                                i14 = i16;
                                i13 = i13;
                                i11 = i11;
                            }
                            str = str2;
                            i = i11;
                            d = middleDegree;
                            ByteArrayWrapper byteArrayWrapper10 = this.mParseSecondDataPerBlockWrapper;
                            if (byteArrayWrapper10 == null) {
                                Intrinsics.throwNpe();
                            }
                            byteArrayWrapper10.reset();
                            this.unitBlockByteArray = (byte[]) null;
                            if (middleDegree >= 0.0d || d <= this.mCurrentDegree) {
                                i2 = 1;
                                i8 = 0;
                            } else {
                                i8 = 0;
                                Pdlog.m3273d(getTAG(), "RSLIDAR_16 :middleDegree = " + d + ",mCurrentDegree = " + this.mCurrentDegree + str + this.lidarNodes.size());
                                i2 = 1;
                                this.oneFrameComplete = true;
                                sendFrame();
                            }
                            i3 = this.mChannelDataGroupPerBlocks;
                            i4 = i8;
                            while (i4 < i3) {
                                double d3 = this.mCurrentFrameTimeStamp;
                                int i17 = (i12 * 2) + i2;
                                double d4 = this.mCurrentDegree;
                                int i18 = i4 + 1;
                                ByteArrayWrapper byteArrayWrapper11 = this.mParseOriginDataWrapper;
                                if (byteArrayWrapper11 == null) {
                                    Intrinsics.throwNpe();
                                }
                                short popUShort2 = byteArrayWrapper11.popUShort();
                                ByteArrayWrapper byteArrayWrapper12 = this.mParseOriginDataWrapper;
                                if (byteArrayWrapper12 == null) {
                                    Intrinsics.throwNpe();
                                }
                                m4407processLidarNode7Wvb02o(d3, i17, d4, i18, popUShort2, byteArrayWrapper12.popUByte());
                                i4 = i18;
                                i2 = 1;
                            }
                            i5 = this.mChannelDataGroupPerBlocks * 3;
                            byte[] bArr3 = new byte[i5];
                            for (i6 = i8; i6 < i5; i6++) {
                                ByteArrayWrapper byteArrayWrapper13 = this.mParseOriginDataWrapper;
                                if (byteArrayWrapper13 == null) {
                                    Intrinsics.throwNpe();
                                }
                                byte b = src[byteArrayWrapper13.getIndex()];
                                ByteArrayWrapper byteArrayWrapper14 = this.mParseOriginDataWrapper;
                                if (byteArrayWrapper14 == null) {
                                    Intrinsics.throwNpe();
                                }
                                byteArrayWrapper14.indexPlus(1);
                                bArr3[i6] = b;
                            }
                            this.unitBlockByteArray = bArr3;
                            this.mLastUnitBlockId = i12;
                            this.mLastFrameTimeStamp = this.mCurrentFrameTimeStamp;
                            i12++;
                            i10 = 1;
                            i11 = i;
                        }
                    }
                    str = ",size  = ";
                    i = i11;
                    d = middleDegree;
                    if (middleDegree >= 0.0d) {
                    }
                    i2 = 1;
                    i8 = 0;
                    i3 = this.mChannelDataGroupPerBlocks;
                    i4 = i8;
                    while (i4 < i3) {
                    }
                    i5 = this.mChannelDataGroupPerBlocks * 3;
                    byte[] bArr32 = new byte[i5];
                    while (i6 < i5) {
                    }
                    this.unitBlockByteArray = bArr32;
                    this.mLastUnitBlockId = i12;
                    this.mLastFrameTimeStamp = this.mCurrentFrameTimeStamp;
                    i12++;
                    i10 = 1;
                    i11 = i;
                }
                ByteArrayWrapper byteArrayWrapper15 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper15 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper15.indexPlus(4);
                ByteArrayWrapper byteArrayWrapper16 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper16 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper16.popUShort();
                ByteArrayWrapper byteArrayWrapper17 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper17 != null) {
                    byteArrayWrapper17.reset();
                    return;
                }
                return;
            }
        }
    }

    private final double getMiddleDegree() {
        double d = this.mLastDegree;
        if (d < 0.0d) {
            return -1.0d;
        }
        double d2 = this.mCurrentDegree;
        if (d2 < 0.0d) {
            return -1.0d;
        }
        if (d2 < d) {
            this.mCurrentDegree = d2 + 360.0d;
        }
        double d3 = (this.mLastDegree + this.mCurrentDegree) / 2;
        if (d3 >= 360.0d) {
            d3 -= 360.0d;
        }
        double d4 = this.mCurrentDegree;
        if (d4 >= 360.0d) {
            this.mCurrentDegree = d4 - 360.0d;
        }
        return d3;
    }

    /* renamed from: processLidarNode-7Wvb02o, reason: not valid java name */
    private final void m4407processLidarNode7Wvb02o(double frameTime, int unitBlockId, double azimuth, int channelId, short distance_cm, byte reflectivity) {
        LidarNode obtain = LidarNode.obtain();
        obtain.distance_m = ((distance_cm & UShort.MAX_VALUE) * 0.01d) / 2;
        obtain.reflectivity = reflectivity & 255;
        obtain.angleInRad = Math.toRadians(azimuth);
        obtain.timeStamp = getExactPointTime(frameTime, unitBlockId, channelId);
        Double d = BaseConfigOfRsLidar16.INSTANCE.getVerticalDegreeMap().get(Integer.valueOf(channelId));
        if (d == null) {
            Intrinsics.throwNpe();
        }
        double radians = Math.toRadians(d.doubleValue());
        obtain.ptX = obtain.distance_m * Math.cos(radians) * Math.sin(obtain.angleInRad);
        obtain.ptY = obtain.distance_m * Math.cos(radians) * Math.cos(obtain.angleInRad);
        obtain.ptZ = obtain.distance_m * Math.sin(radians);
        this.lidarNodes.add(obtain);
    }

    private final double formatData(double data) {
        String format = this.format.format(data);
        Intrinsics.checkExpressionValueIsNotNull(format, "format.format(data)");
        return Double.parseDouble(format);
    }

    private final void sendFrame() {
        if (this.lidarNodes.isEmpty()) {
            Pdlog.m3277w(getTAG(), "empty frame");
        } else if (this.oneFrameComplete) {
            LidarListener listener = getListener();
            if (listener != null) {
                listener.onOneFrameComplete(this.lidarNodes);
            }
            this.oneFrameComplete = false;
        }
        this.lidarNodes = ListLidarNodePool.INSTANCE.obtain(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d(getTAG(), "startScan");
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(getTAG(), "stopScan");
    }
}
