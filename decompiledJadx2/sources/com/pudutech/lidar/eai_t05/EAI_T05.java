package com.pudutech.lidar.eai_t05;

import android.os.SystemClock;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: EAI_T05.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0014\u0010\u0013\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/lidar/eai_t05/EAI_T05;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "InitialCapacity", "", "eth0IP", "", "getEth0IP", "()Ljava/lang/String;", "lastTimestamp", "", "oneFrameNodes", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/LidarNode;", "Lkotlin/collections/ArrayList;", "parser", "Lcom/pudutech/lidar/eai_t05/T05Parser;", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "", "src", "", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EAI_T05 extends EthernetLidar {
    private long lastTimestamp;
    private final String eth0IP = "192.168.0.100";
    private final String socketIP = "192.168.0.11";
    private final int socketPort = 9000;
    private final int udpPort = InternalConstant.RATE8K;
    private final int udpPackageSize = 1408;
    private final int InitialCapacity = 4096;
    private ArrayList<LidarNode> oneFrameNodes = new ArrayList<>(this.InitialCapacity);
    private final T05Parser parser = new T05Parser();

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
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

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        Frame parse = this.parser.parse(src);
        if (parse != null) {
            for (Point point : parse.getPoints()) {
                LidarNode lidarNode = new LidarNode();
                lidarNode.distance_m = point.getDistance_mm() / 1000.0d;
                lidarNode.angleInRad = Math.toRadians(point.getAngle_degree());
                this.oneFrameNodes.add(lidarNode);
            }
            if (parse.getHeadFlag_4b() == UByte.m4528constructorimpl((byte) 1)) {
                Pdlog.m3275i(getTAG(), "on head flag. time=" + (SystemClock.elapsedRealtime() - this.lastTimestamp) + " size=" + this.oneFrameNodes.size());
                this.lastTimestamp = SystemClock.elapsedRealtime();
                if (this.oneFrameNodes.isEmpty()) {
                    Pdlog.m3277w(getTAG(), "empty frame");
                } else {
                    LidarListener listener = getListener();
                    if (listener != null) {
                        listener.onOneFrameComplete(this.oneFrameNodes);
                    }
                }
                this.oneFrameNodes = new ArrayList<>(this.InitialCapacity);
            }
        }
    }
}
