package com.pudutech.lidar.echox;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarDeviceInfo;
import com.pudutech.lidar.base.LidarListener;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.crypto.qteslarnd1.Polynomial;

/* compiled from: Echox.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020.2\u0006\u00104\u001a\u000200H\u0016J\u0018\u00105\u001a\u0002062\u0006\u00107\u001a\u000200H\u0002ø\u0001\u0000¢\u0006\u0002\u00108J\u0018\u00109\u001a\u00020:2\u0006\u00107\u001a\u000200H\u0002ø\u0001\u0000¢\u0006\u0002\u0010;J\u0018\u0010<\u001a\u00020=2\u0006\u00107\u001a\u000200H\u0002ø\u0001\u0000¢\u0006\u0002\u0010>J\b\u0010?\u001a\u00020.H\u0002J\b\u0010@\u001a\u00020.H\u0016J\b\u0010A\u001a\u00020.H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\bR\u0014\u0010 \u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0014\u0010%\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0014\u0010'\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0014\u0010)\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006B"}, m3961d2 = {"Lcom/pudutech/lidar/echox/Echox;", "Lcom/pudutech/lidar/base/EthernetLidar;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "currentDegree", "", "echoxStatusParser", "Lcom/pudutech/lidar/echox/EchoxStatusParser;", "eth0IP", "getEth0IP", "indexEchox", "", "isRunning", "", "lastDegree", "lidarNodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "getLidarNodes", "()Lcom/pudutech/lidar/ListLidarNodePool;", "setLidarNodes", "(Lcom/pudutech/lidar/ListLidarNodePool;)V", "mBlocksPerFrame", "mDataPerBlock", "oneFrameComplete", "sn", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "getLidarInfo", "Lcom/pudutech/lidar/base/LidarDeviceInfo;", "parseStatusData", "", "statusSrc", "", "lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "parser", "src", "popUByte", "Lkotlin/UByte;", "byteArray", "([B)B", "popUInt", "Lkotlin/UInt;", "([B)I", "popUShort", "Lkotlin/UShort;", "([B)S", "sendFrame", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.lidar.echox.Echox, reason: case insensitive filesystem */
/* loaded from: classes5.dex */
public final class C9043Echox extends EthernetLidar {
    private double currentDegree;
    private EchoxStatusParser echoxStatusParser;
    private final String eth0IP;
    private int indexEchox;
    private volatile boolean isRunning;
    private double lastDegree;
    private ListLidarNodePool lidarNodes;
    private final int mBlocksPerFrame;
    private final int mDataPerBlock;
    private boolean oneFrameComplete;
    private String sn;
    private final String socketIP;
    private final int socketPort;
    private final int udpPackageSize;
    private final int udpPort;
    private final int udpStatusPackageSize;
    private final int udpStatusPort;

    @Override // com.pudutech.lidar.base.BaseLidar
    public String getTAG() {
        return "Echox";
    }

    public C9043Echox(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.eth0IP = "192.168.1.77";
        this.socketIP = "192.168.1.201";
        this.socketPort = Polynomial.PRIVATE_KEY_III_SPEED;
        this.udpPort = Polynomial.PRIVATE_KEY_III_SPEED;
        this.udpPackageSize = 1248;
        this.udpStatusPort = 8080;
        this.udpStatusPackageSize = 1033;
        this.lidarNodes = ListLidarNodePool.INSTANCE.obtain();
        this.mBlocksPerFrame = 12;
        this.mDataPerBlock = 16;
        this.sn = "";
        Pdlog.m3273d(getTAG(), "ECHOX init");
        this.echoxStatusParser = new EchoxStatusParser(context);
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

    public final ListLidarNodePool getLidarNodes() {
        return this.lidarNodes;
    }

    public final void setLidarNodes(ListLidarNodePool listLidarNodePool) {
        Intrinsics.checkParameterIsNotNull(listLidarNodePool, "<set-?>");
        this.lidarNodes = listLidarNodePool;
    }

    public void parseStatusData(byte[] statusSrc, LidarUpgradeListener lidarUpgralistener) {
        Intrinsics.checkParameterIsNotNull(statusSrc, "statusSrc");
        Intrinsics.checkParameterIsNotNull(lidarUpgralistener, "lidarUpgralistener");
        EchoxStatusParser echoxStatusParser = this.echoxStatusParser;
        if (echoxStatusParser == null) {
            Intrinsics.throwNpe();
        }
        setSerialNum(echoxStatusParser.parseStatusData(statusSrc, lidarUpgralistener));
        this.sn = getSerialNum();
        if (!StringsKt.isBlank(getSerialNum())) {
            isDifopInfoGetComplete().set(true);
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        this.indexEchox = 0;
        int i = this.mBlocksPerFrame;
        for (int i2 = 0; i2 < i; i2++) {
            popUShort(src);
            int i3 = this.mDataPerBlock;
            for (int i4 = 0; i4 < i3; i4++) {
                if (!this.isRunning) {
                    return;
                }
                this.lastDegree = this.currentDegree;
                short popUShort = popUShort(src);
                short popUShort2 = popUShort(src);
                byte popUByte = popUByte(src);
                popUByte(src);
                LidarNode obtain = LidarNode.obtain();
                obtain.distanceM = ((popUShort2 & UShort.MAX_VALUE) * 0.01d) / 4;
                this.currentDegree = (popUShort & UShort.MAX_VALUE) / 100.0d;
                obtain.angleInRad = Math.toRadians(this.currentDegree);
                obtain.reflectivity = popUByte & 255;
                this.lidarNodes.add(obtain);
                if (this.currentDegree < this.lastDegree) {
                    Pdlog.m3273d(getTAG(), "ECHOX_ONFRAME :currentDegree = " + this.lastDegree + ",nextDegree = " + this.currentDegree + ",size  = " + this.lidarNodes.size());
                    this.oneFrameComplete = true;
                    sendFrame();
                    this.lidarNodes = ListLidarNodePool.INSTANCE.obtain();
                }
            }
            popUShort(src);
        }
    }

    private final void sendFrame() {
        if (this.lidarNodes.isEmpty()) {
            Pdlog.m3277w(getTAG(), "empty frame");
        } else if (this.oneFrameComplete) {
            LidarListener listener = getListener();
            if (listener != null) {
                listener.onOneFrameComplete(this.lidarNodes);
            }
            this.oneFrameComplete = false;
        }
    }

    private final short popUShort(byte[] byteArray) {
        int i = this.indexEchox;
        this.indexEchox = i + 2;
        return UShort.m4761constructorimpl((short) UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(byteArray[i]) & 255) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(byteArray[i + 1]) & 255) << 8)));
    }

    private final byte popUByte(byte[] byteArray) {
        int i = this.indexEchox;
        this.indexEchox = i + 1;
        return UByte.m4528constructorimpl(byteArray[i]);
    }

    private final int popUInt(byte[] byteArray) {
        int i = this.indexEchox;
        this.indexEchox = i + 4;
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(byteArray[i]) & 255) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(byteArray[i + 3]) & 255) << 24) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(byteArray[i + 2]) & 255) << 16)) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByte.m4528constructorimpl(byteArray[i + 1]) & 255) << 8)));
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        this.isRunning = true;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        this.isRunning = false;
        Iterator<LidarNode> it = this.lidarNodes.iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        this.lidarNodes.clear();
    }

    public LidarDeviceInfo getLidarInfo() {
        return new LidarDeviceInfo(LidarVersion.ECHOX.name(), this.sn);
    }
}
