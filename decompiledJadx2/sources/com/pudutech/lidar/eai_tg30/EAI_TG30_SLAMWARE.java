package com.pudutech.lidar.eai_tg30;

import android.os.SystemClock;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SlamwareLidar;
import com.slamtec.slamware.robot.LaserPoint;
import java.util.ArrayList;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: EAI_TG30_SLAMWARE.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/lidar/eai_tg30/EAI_TG30_SLAMWARE;", "Lcom/pudutech/lidar/base/SlamwareLidar;", "()V", "MATH_2PI", "", "isStartFlag", "", "lastDegree", "lastTimestamp", "", "socketIP", "", "getSocketIP", "()Ljava/lang/String;", "socketPort", "", "getSocketPort", "()I", "parser", "", "src", "", "slamwareParser", "laserPoints", "Ljava/util/Vector;", "Lcom/slamtec/slamware/robot/LaserPoint;", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EAI_TG30_SLAMWARE extends SlamwareLidar {
    private long lastTimestamp;
    private final String socketIP = "192.168.11.1";
    private final int socketPort = 1445;
    private final double MATH_2PI = 6.283185307179586d;
    private boolean isStartFlag = true;
    private double lastDegree = -1.0d;

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
    }

    @Override // com.pudutech.lidar.base.SlamwareLidar
    public String getSocketIP() {
        return this.socketIP;
    }

    @Override // com.pudutech.lidar.base.SlamwareLidar
    public int getSocketPort() {
        return this.socketPort;
    }

    @Override // com.pudutech.lidar.base.SlamwareLidar
    public void slamwareParser(Vector<LaserPoint> laserPoints) {
        Intrinsics.checkParameterIsNotNull(laserPoints, "laserPoints");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : laserPoints) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LaserPoint laserPoint = (LaserPoint) obj;
            LidarNode lidarNode = new LidarNode();
            Intrinsics.checkExpressionValueIsNotNull(laserPoint, "laserPoint");
            if (laserPoint.isValid() && laserPoint.getDistance() > 0.3d) {
                lidarNode.angleInRad = laserPoint.getAngle();
                while (lidarNode.angleInRad > this.MATH_2PI) {
                    lidarNode.angleInRad -= this.MATH_2PI;
                }
                while (lidarNode.angleInRad < 0) {
                    lidarNode.angleInRad += this.MATH_2PI;
                }
                lidarNode.distance_m = laserPoint.getDistance();
            }
            arrayList.add(lidarNode);
            i = i2;
        }
        this.lastTimestamp = SystemClock.elapsedRealtime();
        LidarListener listener = getListener();
        if (listener != null) {
            listener.onOneFrameComplete(arrayList);
        }
        new ArrayList();
    }
}
