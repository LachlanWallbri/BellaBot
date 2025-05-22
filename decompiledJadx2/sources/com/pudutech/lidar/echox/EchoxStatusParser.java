package com.pudutech.lidar.echox;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.util.ByteArrayWrapper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UByte;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.bouncycastle.pqc.crypto.qteslarnd1.Polynomial;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: EchoxStatusParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\"H\u0007J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\fH\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u00020\b8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u001b8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\nR\u000e\u0010\u001d\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/lidar/echox/EchoxStatusParser;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "difopDataHeadVerify", "", "difopDataHeadVerify$annotations", "()V", "difopDataHeadVerifyByteNum", "", "difopDataTailVerify", "echoxFpgaBinParse", "Lcom/pudutech/lidar/echox/EchoxFpgaBinParse;", "echoxSnByteLength", "fpgaRequestPort", "fpgaVersionByteNum", "mContext", "mParseOriginDataWrapper", "Lcom/pudutech/lidar/util/ByteArrayWrapper;", "pointCloudFrequencyCmd", "", "pointCloudFrequencyJob", "Lkotlinx/coroutines/Job;", "pointCloudFrequencyMap", "", "pointCloudFrequencyMap$annotations", "pointCloudFrequencySetStartPosition", "socketIP", "parseStatusData", "statusSrc", "lidarUpgradelistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "setPointCloudFrequency", "", "originDifopData", "newPointCloudFrequency", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EchoxStatusParser {
    private final String TAG;
    private final byte[] difopDataHeadVerify;
    private final int difopDataHeadVerifyByteNum;
    private final byte[] difopDataTailVerify;
    private EchoxFpgaBinParse echoxFpgaBinParse;
    private final int echoxSnByteLength;
    private final int fpgaRequestPort;
    private final int fpgaVersionByteNum;
    private Context mContext;
    private ByteArrayWrapper mParseOriginDataWrapper;
    private final byte pointCloudFrequencyCmd;
    private Job pointCloudFrequencyJob;
    private final Map<Integer, byte[]> pointCloudFrequencyMap;
    private final int pointCloudFrequencySetStartPosition;
    private final String socketIP;

    private static /* synthetic */ void difopDataHeadVerify$annotations() {
    }

    private static /* synthetic */ void pointCloudFrequencyMap$annotations() {
    }

    public EchoxStatusParser(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "EchoxUpgrade";
        this.socketIP = "192.168.1.201";
        this.fpgaRequestPort = Polynomial.PRIVATE_KEY_III_SPEED;
        this.echoxSnByteLength = 13;
        this.difopDataHeadVerifyByteNum = 4;
        this.fpgaVersionByteNum = 4;
        this.pointCloudFrequencySetStartPosition = 72;
        byte b = (byte) 15;
        byte b2 = (byte) 0;
        this.pointCloudFrequencyMap = MapsKt.mutableMapOf(new Pair(15, new byte[]{b, b2}), new Pair(30, new byte[]{(byte) 30, b2}), new Pair(60, new byte[]{(byte) 60, b2}));
        this.difopDataHeadVerify = new byte[]{(byte) 84, (byte) 63, (byte) 81, (byte) 165};
        this.pointCloudFrequencyCmd = (byte) 9;
        this.difopDataTailVerify = new byte[]{(byte) 85, (byte) 161, b, (byte) 65};
        this.mParseOriginDataWrapper = new ByteArrayWrapper();
        this.echoxFpgaBinParse = new EchoxFpgaBinParse();
        this.mContext = context;
    }

    public final String parseStatusData(byte[] statusSrc, LidarUpgradeListener lidarUpgradelistener) {
        Intrinsics.checkParameterIsNotNull(statusSrc, "statusSrc");
        Intrinsics.checkParameterIsNotNull(lidarUpgradelistener, "lidarUpgradelistener");
        ByteArrayWrapper byteArrayWrapper = this.mParseOriginDataWrapper;
        if (byteArrayWrapper == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper.prepare(statusSrc);
        int i = this.difopDataHeadVerifyByteNum;
        for (int i2 = 0; i2 < i; i2++) {
            ByteArrayWrapper byteArrayWrapper2 = this.mParseOriginDataWrapper;
            if (byteArrayWrapper2 == null || byteArrayWrapper2.popByte() != this.difopDataHeadVerify[i2]) {
                Pdlog.m3274e(this.TAG, "Udp data head verify error");
                return "";
            }
        }
        ByteArrayWrapper byteArrayWrapper3 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper3 == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper3.indexPlus(3);
        StringBuffer stringBuffer = new StringBuffer();
        int i3 = this.fpgaVersionByteNum;
        for (int i4 = 0; i4 < i3; i4++) {
            ByteArrayWrapper byteArrayWrapper4 = this.mParseOriginDataWrapper;
            if (byteArrayWrapper4 == null) {
                Intrinsics.throwNpe();
            }
            stringBuffer.append(UByte.m4563toStringimpl(byteArrayWrapper4.popUByte()));
        }
        ByteArrayWrapper byteArrayWrapper5 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper5 == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper5.indexPlus(61);
        ByteArrayWrapper byteArrayWrapper6 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper6 == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper6.popUShortLowByteFirst();
        ByteArrayWrapper byteArrayWrapper7 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper7 == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper7.indexPlus(443);
        StringBuffer stringBuffer2 = new StringBuffer();
        int i5 = this.echoxSnByteLength;
        for (int i6 = 0; i6 < i5; i6++) {
            ByteArrayWrapper byteArrayWrapper8 = this.mParseOriginDataWrapper;
            if (byteArrayWrapper8 == null) {
                Intrinsics.throwNpe();
            }
            stringBuffer2.append((char) byteArrayWrapper8.popByte());
        }
        Pdlog.m3273d(this.TAG, "ECHOX SN = " + stringBuffer2);
        ByteArrayWrapper byteArrayWrapper9 = this.mParseOriginDataWrapper;
        if (byteArrayWrapper9 == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper9.reset();
        String stringBuffer3 = stringBuffer2.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer3, "snTemp.toString()");
        return stringBuffer3;
    }

    private final void setPointCloudFrequency(byte[] originDifopData, int newPointCloudFrequency) {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "setPointCloudFrequency: newPointCloudFrequency = " + newPointCloudFrequency);
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EchoxStatusParser$setPointCloudFrequency$1(this, newPointCloudFrequency, originDifopData, null), 3, null);
        this.pointCloudFrequencyJob = launch$default;
    }
}
