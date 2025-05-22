package com.pudutech.lidar.pandarxt;

import android.os.SystemClock;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.util.ByteArrayWrapper;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010.\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u0019H\u0002J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0016J\u0010\u00106\u001a\u0002012\u0006\u00107\u001a\u000203H\u0016J:\u00108\u001a\u0002012\u0006\u00109\u001a\u00020\u001c2\u0006\u0010:\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u00192\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\b\u0010A\u001a\u000201H\u0002J\b\u0010B\u001a\u000201H\u0016J\b\u0010C\u001a\u000201H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0019X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020\u0019X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b'\u0010#R\u0014\u0010(\u001a\u00020\u0019X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0014\u0010*\u001a\u00020\u0019X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u0014\u0010,\u001a\u00020\u0019X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/PandarXT;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "dataHeadVerify", "Lkotlin/UByteArray;", "getDataHeadVerify", "()[B", "[B", "eth0IP", "getEth0IP", "format", "Ljava/text/DecimalFormat;", "getFormat", "()Ljava/text/DecimalFormat;", "lidarNodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "getLidarNodes", "()Lcom/pudutech/lidar/ListLidarNodePool;", "setLidarNodes", "(Lcom/pudutech/lidar/ListLidarNodePool;)V", "mBlockNumPerFrame", "", "mChannelNumPerFrame", "mLastDegree", "", "mParseOriginDataWrapper", "Lcom/pudutech/lidar/util/ByteArrayWrapper;", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpHead", "Lcom/pudutech/lidar/pandarxt/UdpHead;", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "getLidarNodeTimeStamp", "channelId", "parseStatusData", "", "statusSrc", "", "lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "parser", "src", "processLidarNode", "horizontalDegree", "blockStartTime", "distance_mm", "Lkotlin/UShort;", "reflectivity", "Lkotlin/UByte;", "processLidarNode-7etULL8", "(DDISB)V", "sendFrame", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PandarXT extends EthernetLidar {
    private double mLastDegree;
    private ByteArrayWrapper mParseOriginDataWrapper;
    private UdpHead udpHead;
    private final int udpStatusPackageSize;
    private final int udpStatusPort;
    private final String eth0IP = "192.168.1.100";
    private final String socketIP = "192.168.1.201";
    private final int socketPort = 10000;
    private final int udpPort = 2369;
    private final int udpPackageSize = 568;
    private final String TAG = "PandarXT";
    private final int mBlockNumPerFrame = 8;
    private final int mChannelNumPerFrame = 16;
    private ListLidarNodePool lidarNodes = ListLidarNodePool.INSTANCE.obtain();
    private final DecimalFormat format = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));
    private final byte[] dataHeadVerify = {UByte.m4528constructorimpl((byte) 238), UByte.m4528constructorimpl((byte) 255)};

    private final double getLidarNodeTimeStamp(int channelId) {
        return ((channelId - 1) * 3.024d) + 0.28d;
    }

    public PandarXT() {
        Pdlog.m3273d(getTAG(), "PandarXT lidar init");
        this.format.setRoundingMode(RoundingMode.FLOOR);
        this.mParseOriginDataWrapper = new ByteArrayWrapper();
        isDifopInfoGetComplete().set(true);
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

    public void parseStatusData(byte[] statusSrc, LidarUpgradeListener lidarUpgralistener) {
        Intrinsics.checkParameterIsNotNull(statusSrc, "statusSrc");
        Intrinsics.checkParameterIsNotNull(lidarUpgralistener, "lidarUpgralistener");
        isDifopInfoGetComplete().set(true);
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

    public final byte[] getDataHeadVerify() {
        return this.dataHeadVerify;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        ByteArrayWrapper byteArrayWrapper = this.mParseOriginDataWrapper;
        if (byteArrayWrapper == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper.prepare(src);
        ByteArrayWrapper byteArrayWrapper2 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper2 == null) {
            Intrinsics.throwNpe();
        }
        byte popUByte = byteArrayWrapper2.popUByte();
        ByteArrayWrapper byteArrayWrapper3 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper3 == null) {
            Intrinsics.throwNpe();
        }
        byte popUByte2 = byteArrayWrapper3.popUByte();
        ByteArrayWrapper byteArrayWrapper4 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper4 == null) {
            Intrinsics.throwNpe();
        }
        byte popUByte3 = byteArrayWrapper4.popUByte();
        ByteArrayWrapper byteArrayWrapper5 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper5 == null) {
            Intrinsics.throwNpe();
        }
        byte popUByte4 = byteArrayWrapper5.popUByte();
        ByteArrayWrapper byteArrayWrapper6 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper6 == null) {
            Intrinsics.throwNpe();
        }
        this.udpHead = new UdpHead(popUByte, popUByte2, popUByte3, popUByte4, byteArrayWrapper6.popUShort(), null);
        UdpHead udpHead = this.udpHead;
        if (udpHead == null) {
            Intrinsics.throwNpe();
        }
        if (udpHead.getPacketStartFlag1() == UByteArray.m4577getimpl(this.dataHeadVerify, 0)) {
            UdpHead udpHead2 = this.udpHead;
            if (udpHead2 == null) {
                Intrinsics.throwNpe();
            }
            if (udpHead2.getPacketStartFlag2() == UByteArray.m4577getimpl(this.dataHeadVerify, 1)) {
                ByteArrayWrapper byteArrayWrapper7 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper7 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper7.indexPlus(6);
                int i = this.mBlockNumPerFrame;
                for (int i2 = 0; i2 < i; i2++) {
                    double elapsedRealtime = SystemClock.elapsedRealtime() / 1000.0d;
                    if (this.mParseOriginDataWrapper == null) {
                        Intrinsics.throwNpe();
                    }
                    double popUShortLowByteFirst = (r0.popUShortLowByteFirst() & UShort.MAX_VALUE) / 100;
                    int i3 = 0;
                    for (int i4 = this.mChannelNumPerFrame; i3 < i4; i4 = i4) {
                        ByteArrayWrapper byteArrayWrapper8 = this.mParseOriginDataWrapper;
                        if (byteArrayWrapper8 == null) {
                            Intrinsics.throwNpe();
                        }
                        short popUShortLowByteFirst2 = byteArrayWrapper8.popUShortLowByteFirst();
                        ByteArrayWrapper byteArrayWrapper9 = this.mParseOriginDataWrapper;
                        if (byteArrayWrapper9 == null) {
                            Intrinsics.throwNpe();
                        }
                        byte popUByte5 = byteArrayWrapper9.popUByte();
                        ByteArrayWrapper byteArrayWrapper10 = this.mParseOriginDataWrapper;
                        if (byteArrayWrapper10 == null) {
                            Intrinsics.throwNpe();
                        }
                        byteArrayWrapper10.popUByte();
                        int i5 = i3 + 1;
                        m4391processLidarNode7etULL8(popUShortLowByteFirst, elapsedRealtime, i5, popUShortLowByteFirst2, popUByte5);
                        i3 = i5;
                    }
                }
                ByteArrayWrapper byteArrayWrapper11 = this.mParseOriginDataWrapper;
                if (byteArrayWrapper11 != null) {
                    byteArrayWrapper11.reset();
                    return;
                }
                return;
            }
        }
        Pdlog.m3274e(getTAG(), "Udp data head verify error");
    }

    /* renamed from: processLidarNode-7etULL8, reason: not valid java name */
    private final void m4391processLidarNode7etULL8(double horizontalDegree, double blockStartTime, int channelId, short distance_mm, byte reflectivity) {
        LidarNode obtain = LidarNode.obtain();
        obtain.distanceM = ((distance_mm & UShort.MAX_VALUE) * 4) / 1000.0d;
        obtain.reflectivity = reflectivity & 255;
        obtain.angleInRad = Math.toRadians(horizontalDegree);
        obtain.timeStamp = blockStartTime + (getLidarNodeTimeStamp(channelId) / 1000000.0d);
        obtain.channelId = channelId;
        Double d = BaseConfigOfPandarXT.INSTANCE.getVerticalDegreeMap().get(Integer.valueOf(channelId));
        if (d == null) {
            Intrinsics.throwNpe();
        }
        double radians = Math.toRadians(d.doubleValue());
        obtain.ptX = obtain.distanceM * Math.cos(radians) * Math.sin(obtain.angleInRad);
        obtain.ptY = obtain.distanceM * Math.cos(radians) * Math.cos(obtain.angleInRad);
        obtain.ptZ = obtain.distanceM * Math.sin(radians);
        if (this.mLastDegree > horizontalDegree) {
            Log.d(getTAG(), "PandarXT :lastDegree = " + this.mLastDegree + ",currentDegree = " + horizontalDegree + ",size  = " + this.lidarNodes.size());
            sendFrame();
        }
        this.lidarNodes.add(obtain);
        this.mLastDegree = horizontalDegree;
    }

    private final void sendFrame() {
        if (this.lidarNodes.isEmpty()) {
            Pdlog.m3277w(getTAG(), "empty frame");
        } else {
            LidarListener listener = getListener();
            if (listener != null) {
                ListLidarNodePool listLidarNodePool = this.lidarNodes;
                if (listLidarNodePool == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                }
                listener.onOneFrameComplete(listLidarNodePool);
            }
        }
        this.lidarNodes = ListLidarNodePool.INSTANCE.obtain();
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
