package com.pudutech.lidar.echox;

import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.LidarListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.crypto.qteslarnd1.Polynomial;

/* compiled from: ECHOX.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020,H\u0016J\u0018\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0002\u00102J\u0018\u00103\u001a\u0002042\u0006\u00101\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0002\u00105J\u0018\u00106\u001a\u0002072\u0006\u00101\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0002\u00108J\b\u00109\u001a\u00020*H\u0002J\b\u0010:\u001a\u00020*H\u0016J\b\u0010;\u001a\u00020*H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\nR\u0014\u0010\u001e\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0014\u0010%\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0014\u0010'\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, m3961d2 = {"Lcom/pudutech/lidar/echox/ECHOX;", "Lcom/pudutech/lidar/base/EthernetLidar;", "()V", "currentDegree", "", "echoxStatusParser", "Lcom/pudutech/lidar/echox/EchoxStatusParser;", "eth0IP", "", "getEth0IP", "()Ljava/lang/String;", "format", "Ljava/text/DecimalFormat;", "getFormat", "()Ljava/text/DecimalFormat;", "indexEchox", "", "lastDegree", "lidarNodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "getLidarNodes", "()Lcom/pudutech/lidar/ListLidarNodePool;", "setLidarNodes", "(Lcom/pudutech/lidar/ListLidarNodePool;)V", "mBlocksPerFrame", "mDataPerBlock", "oneFrameComplete", "", "socketIP", "getSocketIP", "socketPort", "getSocketPort", "()I", "udpPackageSize", "getUdpPackageSize", "udpPort", "getUdpPort", "udpStatusPackageSize", "getUdpStatusPackageSize", "udpStatusPort", "getUdpStatusPort", "parseStatusData", "", "statusSrc", "", "parser", "src", "popUByte", "Lkotlin/UByte;", "byteArray", "([B)B", "popUInt", "Lkotlin/UInt;", "([B)I", "popUShort", "Lkotlin/UShort;", "([B)S", "sendFrame", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ECHOX extends EthernetLidar {
    private double currentDegree;
    private EchoxStatusParser echoxStatusParser;
    private int indexEchox;
    private double lastDegree;
    private boolean oneFrameComplete;
    private final String eth0IP = "192.168.1.77";
    private final String socketIP = "192.168.1.201";
    private final int socketPort = Polynomial.PRIVATE_KEY_III_SPEED;
    private final int udpPort = Polynomial.PRIVATE_KEY_III_SPEED;
    private final int udpPackageSize = 1248;
    private final int udpStatusPort = 8080;
    private final int udpStatusPackageSize = 1033;
    private ListLidarNodePool lidarNodes = ListLidarNodePool.INSTANCE.obtain();
    private final int mBlocksPerFrame = 12;
    private final int mDataPerBlock = 16;
    private final DecimalFormat format = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.US));

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
    }

    public ECHOX() {
        Pdlog.m3273d(getTAG(), "ECHOX init");
        this.format.setRoundingMode(RoundingMode.FLOOR);
        this.echoxStatusParser = new EchoxStatusParser();
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

    public final DecimalFormat getFormat() {
        return this.format;
    }

    public void parseStatusData(byte[] statusSrc) {
        Intrinsics.checkParameterIsNotNull(statusSrc, "statusSrc");
        EchoxStatusParser echoxStatusParser = this.echoxStatusParser;
        if (echoxStatusParser == null) {
            Intrinsics.throwNpe();
        }
        setSerialNum(echoxStatusParser.parseStatusData(statusSrc));
        if (!StringsKt.isBlank(getSerialNum())) {
            setSnGetSucceed(true);
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
                this.lastDegree = this.currentDegree;
                short popUShort = popUShort(src);
                short popUShort2 = popUShort(src);
                popUShort(src);
                LidarNode obtain = LidarNode.obtain();
                String format = this.format.format(((popUShort2 & UShort.MAX_VALUE) * 0.01d) / 4);
                Intrinsics.checkExpressionValueIsNotNull(format, "format.format(distance_cm.toDouble() *0.01/4)");
                obtain.distance_m = Double.parseDouble(format);
                this.currentDegree = (popUShort & UShort.MAX_VALUE) / 100.0d;
                obtain.angleInRad = Math.toRadians(this.currentDegree);
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
}
