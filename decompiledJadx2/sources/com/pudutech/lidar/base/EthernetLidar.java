package com.pudutech.lidar.base;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: EthernetLidar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0012\u0010\u0015\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/lidar/base/EthernetLidar;", "Lcom/pudutech/lidar/base/BaseLidar;", "()V", "eth0IP", "", "getEth0IP", "()Ljava/lang/String;", "lidarInterface", "Lcom/pudutech/lidar/base/EthernetLidarInterface;", "getLidarInterface", "()Lcom/pudutech/lidar/base/EthernetLidarInterface;", "setLidarInterface", "(Lcom/pudutech/lidar/base/EthernetLidarInterface;)V", "socketIP", "getSocketIP", "socketPort", "", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class EthernetLidar extends BaseLidar {
    private EthernetLidarInterface lidarInterface;

    public abstract String getEth0IP();

    public abstract String getSocketIP();

    public abstract int getSocketPort();

    public abstract int getUdpPackageSize();

    public abstract int getUdpPort();

    public final EthernetLidarInterface getLidarInterface() {
        return this.lidarInterface;
    }

    public final void setLidarInterface(EthernetLidarInterface ethernetLidarInterface) {
        this.lidarInterface = ethernetLidarInterface;
    }
}
