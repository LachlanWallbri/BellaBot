package com.pudutech.lidar.base;

import com.slamtec.slamware.robot.LaserPoint;
import java.util.Vector;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SlamwareLidar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/lidar/base/SlamwareLidar;", "Lcom/pudutech/lidar/base/BaseLidar;", "()V", "socketIP", "", "getSocketIP", "()Ljava/lang/String;", "socketPort", "", "getSocketPort", "()I", "slamwareParser", "", "laserPoints", "Ljava/util/Vector;", "Lcom/slamtec/slamware/robot/LaserPoint;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class SlamwareLidar extends BaseLidar {
    public abstract String getSocketIP();

    public abstract int getSocketPort();

    public abstract void slamwareParser(Vector<LaserPoint> laserPoints);
}
