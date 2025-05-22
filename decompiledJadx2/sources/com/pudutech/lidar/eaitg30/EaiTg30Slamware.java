package com.pudutech.lidar.eaitg30;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SlamwareLidar;
import com.slamtec.slamware.robot.LaserPoint;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EaiTg30Slamware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/lidar/eaitg30/EaiTg30Slamware;", "Lcom/pudutech/lidar/base/SlamwareLidar;", "()V", "isStartFlag", "", "lastDegree", "", "lastTimestamp", "", "socketIP", "", "getSocketIP", "()Ljava/lang/String;", "socketPort", "", "getSocketPort", "()I", "parser", "", "src", "", "slamwareParser", "laserPoints", "Ljava/util/Vector;", "Lcom/slamtec/slamware/robot/LaserPoint;", "startScan", "stopScan", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EaiTg30Slamware extends SlamwareLidar {
    private static final double MATH_2PI = 6.283185307179586d;
    private static final double VALID_DISTANCE = 0.3d;
    private long lastTimestamp;
    private final String socketIP = "192.168.11.1";
    private final int socketPort = 1445;
    private boolean isStartFlag = true;
    private double lastDegree = -1.0d;

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
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
        ListLidarNodePool obtain = ListLidarNodePool.INSTANCE.obtain();
        int i = 0;
        for (Object obj : laserPoints) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LaserPoint laserPoint = (LaserPoint) obj;
            LidarNode obtain2 = LidarNode.obtain();
            Intrinsics.checkExpressionValueIsNotNull(laserPoint, "laserPoint");
            if (laserPoint.isValid() && laserPoint.getDistance() > VALID_DISTANCE) {
                obtain2.angleInRad = laserPoint.getAngle();
                while (obtain2.angleInRad > MATH_2PI) {
                    obtain2.angleInRad -= MATH_2PI;
                }
                while (obtain2.angleInRad < 0) {
                    obtain2.angleInRad += MATH_2PI;
                }
                obtain2.distanceM = laserPoint.getDistance();
            }
            obtain.add(obtain2);
            i = i2;
        }
        this.lastTimestamp = SystemClock.elapsedRealtime();
        LidarListener listener = getListener();
        if (listener != null) {
            if (obtain == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
            }
            listener.onOneFrameComplete(obtain);
        }
        ListLidarNodePool.INSTANCE.obtain();
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d(getTAG(), "startScan do nothing");
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(getTAG(), "stopScan do nothing");
    }
}
