package com.pudutech.lidar.ltme02a;

import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Ltme02A.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020 H\u0016J\b\u0010%\u001a\u00020\u001eH\u0016J\b\u0010&\u001a\u00020\u001eH\u0016J\u0014\u0010'\u001a\u00020(*\u00020 H\u0002ø\u0001\u0000¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\u00020+*\u00020 H\u0002ø\u0001\u0000¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\u00020.*\u00020 H\u0002ø\u0001\u0000¢\u0006\u0002\u0010/R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u0019\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0014\u0010\u001b\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, m3961d2 = {"Lcom/pudutech/lidar/ltme02a/Ltme02a;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "eth0IP", "", "getEth0IP", "()Ljava/lang/String;", "index", "", "list", "", "Lcom/pudutech/lidar/ltme02a/Frame;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "parseStatusData", "", "statusSrc", "", "lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "parser", "src", "startScan", "stopScan", "popUByte", "Lkotlin/UByte;", "([B)B", "popUInt", "Lkotlin/UInt;", "([B)I", "popUShort", "Lkotlin/UShort;", "([B)S", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Ltme02a extends EthernetLidar {
    private int index;
    private List<Frame> list;
    private final int udpStatusPackageSize;
    private final int udpStatusPort;
    private final String eth0IP = "192.168.10.10";
    private final String socketIP = "192.168.10.160";
    private final int socketPort = 2105;
    private final int udpPort = 2105;
    private final int udpPackageSize = 880;

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
    }

    public Ltme02a() {
        isDifopInfoGetComplete().set(true);
        this.list = new ArrayList();
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

    public final List<Frame> getList() {
        return this.list;
    }

    public final void setList(List<Frame> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.list = list;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Iterator it;
        double d;
        Intrinsics.checkParameterIsNotNull(src, "src");
        Frame frame = new Frame();
        int i = 0;
        this.index = 0;
        if (frame.getSignature() != popUShort(src)) {
            return;
        }
        frame.m4369setReserved1xj2QHRw(popUShort(src));
        frame.m4365setBlockNumber7apg3OU(popUByte(src));
        frame.m4368setFlags7apg3OU(popUByte(src));
        frame.m4367setCountxj2QHRw(popUShort(src));
        frame.m4371setTimestampUsWZ4Q5Ns(popUInt(src));
        frame.m4366setCheckSumxj2QHRw(popUShort(src));
        frame.m4370setReserved2xj2QHRw(popUShort(src));
        Pdlog.m3276v(getTAG(), "parse " + frame);
        short count = frame.getCount();
        short s = UShort.MAX_VALUE;
        int i2 = count & UShort.MAX_VALUE;
        Double[] dArr = new Double[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i3] = Double.valueOf(popUShort(src) & UShort.MAX_VALUE);
        }
        frame.setRanges(dArr);
        int count2 = frame.getCount() & UShort.MAX_VALUE;
        UByte[] uByteArr = new UByte[count2];
        for (int i4 = 0; i4 < count2; i4++) {
            uByteArr[i4] = UByte.m4522boximpl(popUByte(src));
        }
        frame.setIntensities(uByteArr);
        if ((frame.getBlockNumber() & 255) == 7) {
            ListLidarNodePool obtain = ListLidarNodePool.INSTANCE.obtain();
            double radians = Math.toRadians(45.0d);
            Iterator it2 = this.list.iterator();
            while (it2.hasNext()) {
                Frame frame2 = (Frame) it2.next();
                double blockNumber = ((frame2.getBlockNumber() & 255) * Math.toRadians(33.75d)) + radians;
                double radians2 = Math.toRadians(33.75d) / (frame2.getCount() & s);
                Double[] ranges = frame2.getRanges();
                if (ranges != null) {
                    int length = ranges.length;
                    d = radians;
                    double d2 = blockNumber;
                    int i5 = i;
                    while (i5 < length) {
                        double doubleValue = ranges[i5].doubleValue();
                        LidarNode obtain2 = LidarNode.obtain();
                        obtain2.angleInRad = d2;
                        d2 += radians2;
                        obtain2.dist = (int) doubleValue;
                        obtain2.distanceM = obtain2.dist * 0.002d;
                        obtain.add(obtain2);
                        i5++;
                        it2 = it2;
                    }
                    it = it2;
                } else {
                    it = it2;
                    d = radians;
                }
                it2 = it;
                radians = d;
                i = 0;
                s = UShort.MAX_VALUE;
            }
            if (obtain.isEmpty()) {
                Pdlog.m3277w(getTAG(), "empty frame");
            } else {
                LidarListener listener = getListener();
                if (listener != null) {
                    if (obtain == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                    }
                    listener.onOneFrameComplete(obtain);
                }
            }
            this.list.clear();
        }
        this.list.add(frame);
    }

    private final short popUShort(byte[] bArr) {
        int i = this.index;
        this.index = i + 2;
        return UShort.m4761constructorimpl((short) UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(bArr[i]) & 255) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(bArr[i + 1]) & 255) << 8)));
    }

    private final byte popUByte(byte[] bArr) {
        int i = this.index;
        this.index = i + 1;
        return UByte.m4528constructorimpl(bArr[i]);
    }

    private final int popUInt(byte[] bArr) {
        int i = this.index;
        this.index = i + 4;
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(bArr[i]) & 255) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(bArr[i + 3]) & 255) << 24) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(bArr[i + 2]) & 255) << 16)) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(bArr[i + 1]) & 255) << 8)));
    }
}
