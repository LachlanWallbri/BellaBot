package com.pudutech.lidar.base;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialLidar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/lidar/base/SerialLidar;", "Lcom/pudutech/lidar/base/BaseLidar;", "()V", "baudRate", "", "getBaudRate", "()I", "lidarInterface", "Lcom/pudutech/lidar/base/SerialLidarInterface;", "getLidarInterface", "()Lcom/pudutech/lidar/base/SerialLidarInterface;", "setLidarInterface", "(Lcom/pudutech/lidar/base/SerialLidarInterface;)V", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class SerialLidar extends BaseLidar {
    private SerialLidarInterface lidarInterface;

    public abstract int getBaudRate();

    public final SerialLidarInterface getLidarInterface() {
        return this.lidarInterface;
    }

    public final void setLidarInterface(SerialLidarInterface serialLidarInterface) {
        this.lidarInterface = serialLidarInterface;
    }
}
