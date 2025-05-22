package com.pudutech.lidar.mid70;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.util.ByteArrayWrapper;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: Mid70.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\fH\u0002J\u0018\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020(H\u0017J\u0018\u0010-\u001a\u00020.2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\fH\u0003J\b\u0010/\u001a\u00020!H\u0002J\b\u00100\u001a\u00020!H\u0016J\b\u00101\u001a\u00020!H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0014\u0010\u001c\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017¨\u00062"}, m3961d2 = {"Lcom/pudutech/lidar/mid70/Mid70;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "eth0IP", "getEth0IP", "lidarNodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "mCloudPointsNum", "", "mFrameTimeTickJob", "Lkotlinx/coroutines/Job;", "mParseOriginDataWrapper", "Lcom/pudutech/lidar/util/ByteArrayWrapper;", "oneFrameComplete", "Ljava/util/concurrent/atomic/AtomicBoolean;", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "frameTimeTick", "", "getExactPointTime", "", "frameTime", "cloudPointIndex", "parseStatusData", "statusSrc", "", "lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "parser", "src", "parserNode", "Lcom/pudutech/lidar/LidarNode;", "sendFrame", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Mid70 extends EthernetLidar {
    private Job mFrameTimeTickJob;
    private ByteArrayWrapper mParseOriginDataWrapper;
    private final int socketPort;
    private final int udpStatusPackageSize;
    private final int udpStatusPort;
    private final String eth0IP = "192.168.1.100";
    private final String socketIP = "";
    private final int udpPort = 56000;
    private final int udpPackageSize = 1362;
    private ListLidarNodePool lidarNodes = ListLidarNodePool.INSTANCE.obtain(20000);
    private AtomicBoolean oneFrameComplete = new AtomicBoolean(false);
    private final int mCloudPointsNum = 96;

    private final double getExactPointTime(double frameTime, int cloudPointIndex) {
        return frameTime + ((cloudPointIndex * 10) / 1000000.0d);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public String getTAG() {
        return "Mid70";
    }

    public Mid70() {
        Pdlog.m3273d(getTAG(), "Mid70 lidar init");
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

    private final void frameTimeTick() {
        Job launch$default;
        Job job = this.mFrameTimeTickJob;
        if (job == null || !job.isActive()) {
            Pdlog.m3273d(getTAG(), "start FrameTimeTick");
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new Mid70$frameTimeTick$1(this, null), 3, null);
            this.mFrameTimeTickJob = launch$default;
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        this.mParseOriginDataWrapper.prepare(src);
        double elapsedRealtime = SystemClock.elapsedRealtime() / 1000.0d;
        this.mParseOriginDataWrapper.popUByte();
        this.mParseOriginDataWrapper.popUByte();
        this.mParseOriginDataWrapper.popUByte();
        this.mParseOriginDataWrapper.popUByte();
        this.mParseOriginDataWrapper.popUIntLowByteFirst();
        this.mParseOriginDataWrapper.popUByte();
        this.mParseOriginDataWrapper.popUByte();
        this.mParseOriginDataWrapper.indexPlus(8);
        int i = this.mCloudPointsNum;
        for (int i2 = 0; i2 < i; i2++) {
            this.lidarNodes.add(parserNode(elapsedRealtime, i2));
            if (this.oneFrameComplete.get()) {
                sendFrame();
                this.lidarNodes = ListLidarNodePool.INSTANCE.obtain(20000);
            }
        }
        this.mParseOriginDataWrapper.reset();
    }

    private final LidarNode parserNode(double frameTime, int cloudPointIndex) {
        LidarNode node = LidarNode.obtain();
        node.ptX = UnsignedKt.uintToDouble(this.mParseOriginDataWrapper.popUIntLowByteFirst()) / 1000.0d;
        node.ptY = UnsignedKt.uintToDouble(this.mParseOriginDataWrapper.popUIntLowByteFirst()) / 1000.0d;
        node.ptZ = UnsignedKt.uintToDouble(this.mParseOriginDataWrapper.popUIntLowByteFirst()) / 1000.0d;
        node.reflectivity = this.mParseOriginDataWrapper.popUByte() & 255;
        node.timeStamp = getExactPointTime(frameTime, cloudPointIndex);
        double d = 2;
        node.distanceM = Math.sqrt(Math.pow(node.ptX, d) + Math.pow(node.ptY, d) + Math.pow(node.ptZ, d));
        Intrinsics.checkExpressionValueIsNotNull(node, "node");
        return node;
    }

    private final void sendFrame() {
        if (this.lidarNodes.isEmpty()) {
            Pdlog.m3277w(getTAG(), "empty frame");
            return;
        }
        Pdlog.m3273d(getTAG(), "add node oneFrameComplete size = " + this.lidarNodes.size());
        LidarListener listener = getListener();
        if (listener != null) {
            listener.onOneFrameComplete(this.lidarNodes);
        }
        this.oneFrameComplete.set(false);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d(getTAG(), "startScan");
        frameTimeTick();
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(getTAG(), "stopScan");
        BuildersKt__BuildersKt.runBlocking$default(null, new Mid70$stopScan$1(this, null), 1, null);
    }
}
