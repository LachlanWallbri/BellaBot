package com.pudutech.lidar.eait05;

import android.os.SystemClock;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EaiT05.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010\u000b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001eH\u0016J\b\u0010\"\u001a\u00020\u001cH\u0016J\b\u0010#\u001a\u00020\u001cH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0014\u0010\u0019\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006%"}, m3961d2 = {"Lcom/pudutech/lidar/eait05/EaiT05;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "eth0IP", "", "getEth0IP", "()Ljava/lang/String;", "lastTimestamp", "", "oneFrameNodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "parser", "Lcom/pudutech/lidar/eait05/T05Parser;", "socketIP", "getSocketIP", "socketPort", "", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "parseStatusData", "", "statusSrc", "", "lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "src", "startScan", "stopScan", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EaiT05 extends EthernetLidar {
    private static final int InitialCapacity = 4096;
    private static final double MILLIMETER_UNIT = 1000.0d;
    private long lastTimestamp;
    private final int udpStatusPackageSize;
    private final int udpStatusPort;
    private final String eth0IP = "192.168.0.100";
    private final String socketIP = "192.168.0.11";
    private final int socketPort = 9000;
    private final int udpPort = InternalConstant.RATE8K;
    private final int udpPackageSize = 1408;
    private ListLidarNodePool oneFrameNodes = ListLidarNodePool.INSTANCE.obtain(4096);
    private final T05Parser parser = new T05Parser();

    public EaiT05() {
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
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        Frame parse = this.parser.parse(src);
        if (parse != null) {
            for (Point point : parse.getPoints()) {
                LidarNode obtain = LidarNode.obtain();
                obtain.distanceM = point.getDistanceMm() / MILLIMETER_UNIT;
                obtain.angleInRad = Math.toRadians(point.getAngleDegree());
                this.oneFrameNodes.add(obtain);
            }
            if (parse.getHeadFlag() == UByte.m4528constructorimpl((byte) 1)) {
                Pdlog.m3275i(getTAG(), "on head flag. time=" + (SystemClock.elapsedRealtime() - this.lastTimestamp) + " size=" + this.oneFrameNodes.size());
                this.lastTimestamp = SystemClock.elapsedRealtime();
                if (this.oneFrameNodes.isEmpty()) {
                    Pdlog.m3277w(getTAG(), "empty frame");
                } else {
                    LidarListener listener = getListener();
                    if (listener != null) {
                        ListLidarNodePool listLidarNodePool = this.oneFrameNodes;
                        if (listLidarNodePool == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                        }
                        listener.onOneFrameComplete(listLidarNodePool);
                    }
                }
                this.oneFrameNodes = ListLidarNodePool.INSTANCE.obtain(4096);
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d(getTAG(), "startScan not need to do ");
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(getTAG(), "stopScan not need to do ");
    }
}
