package com.pudutech.lidar.echox;

import android.util.Log;
import com.pudutech.lidar.LidarUpgradeListener;
import com.pudutech.lidar.util.ByteArrayWrapper;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.bouncycastle.pqc.crypto.qteslarnd1.Polynomial;

/* compiled from: EchoxFpgaUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 12\u00020\u0001:\u00011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020#H\u0002J)\u0010'\u001a\u00020#2\u0016\u0010(\u001a\u0012\u0012\u0004\u0012\u00020%0)j\b\u0012\u0004\u0012\u00020%`*H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0011\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020%H\u0086 J\t\u0010.\u001a\u00020#H\u0086 J\t\u0010/\u001a\u00020#H\u0086 J\u001e\u00100\u001a\u00020#2\u0016\u0010(\u001a\u0012\u0012\u0004\u0012\u00020%0)j\b\u0012\u0004\u0012\u00020%`*R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8\u0002X\u0083\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000f\u0012\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, m3961d2 = {"Lcom/pudutech/lidar/echox/EchoxFpgaUpgrade;", "", "_lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "(Lcom/pudutech/lidar/LidarUpgradeListener;)V", "TAG", "", "eth0IP", "fpgaRequestPackageSize", "", "fpgaRequestPort", "fpgaResponseArray", "Lkotlin/UByteArray;", "fpgaResponseArray$annotations", "()V", "[B", "fpgaResponseDataVerifyOk", "Ljava/util/concurrent/atomic/AtomicBoolean;", "fpgaResponsePackageSize", "fpgaResponsePort", "fpgaSendNeedRetry", "fpgaUpgradeJob", "Lkotlinx/coroutines/Job;", "hasSendNewPacket", "isKeepRunning", "isSendJobEnd", "lidarUpgralistener", "mParseFpgaResponseDataWrapper", "Lcom/pudutech/lidar/util/ByteArrayWrapper;", "sendFpgaDataCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "socketIP", "upgradePackageListSize", "waitForFpgaResponseTime", "parseFpgaResponseData", "", "responseData", "", "receiveFpgaData", "sendFpgaData", "upgradePackageList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOriginBin", "fpgaData", "socketClose", "socketInit", "startFpgaUpgrade", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EchoxFpgaUpgrade {
    private final String TAG;
    private final String eth0IP;
    private final int fpgaRequestPackageSize;
    private final int fpgaRequestPort;
    private final byte[] fpgaResponseArray;
    private AtomicBoolean fpgaResponseDataVerifyOk;
    private final int fpgaResponsePackageSize;
    private final int fpgaResponsePort;
    private AtomicBoolean fpgaSendNeedRetry;
    private Job fpgaUpgradeJob;
    private AtomicBoolean hasSendNewPacket;
    private AtomicBoolean isKeepRunning;
    private AtomicBoolean isSendJobEnd;
    private final LidarUpgradeListener lidarUpgralistener;
    private ByteArrayWrapper mParseFpgaResponseDataWrapper;
    private AtomicInteger sendFpgaDataCount;
    private final String socketIP;
    private int upgradePackageListSize;
    private AtomicInteger waitForFpgaResponseTime;

    private static /* synthetic */ void fpgaResponseArray$annotations() {
    }

    public final native void sendOriginBin(byte[] fpgaData);

    public final native void socketClose();

    public final native void socketInit();

    public EchoxFpgaUpgrade(LidarUpgradeListener _lidarUpgralistener) {
        Intrinsics.checkParameterIsNotNull(_lidarUpgralistener, "_lidarUpgralistener");
        this.TAG = "EchoxUpgrade";
        this.lidarUpgralistener = _lidarUpgralistener;
        this.eth0IP = "192.168.1.77";
        this.socketIP = "192.168.1.201";
        this.fpgaRequestPort = Polynomial.PRIVATE_KEY_III_SPEED;
        this.fpgaRequestPackageSize = 1033;
        this.fpgaResponsePort = 8080;
        this.fpgaResponsePackageSize = 10;
        this.waitForFpgaResponseTime = new AtomicInteger(0);
        this.fpgaResponseDataVerifyOk = new AtomicBoolean(true);
        this.fpgaSendNeedRetry = new AtomicBoolean(false);
        this.hasSendNewPacket = new AtomicBoolean(false);
        this.sendFpgaDataCount = new AtomicInteger(0);
        this.isSendJobEnd = new AtomicBoolean(false);
        this.isKeepRunning = new AtomicBoolean(true);
        byte b = (byte) 85;
        this.fpgaResponseArray = new byte[]{UByte.m4528constructorimpl((byte) 84), UByte.m4528constructorimpl((byte) 63), UByte.m4528constructorimpl((byte) 81), UByte.m4528constructorimpl((byte) 165), UByte.m4528constructorimpl((byte) 6), UByte.m4528constructorimpl(b), UByte.m4528constructorimpl(b), UByte.m4528constructorimpl((byte) 161), UByte.m4528constructorimpl((byte) 15), UByte.m4528constructorimpl((byte) 65)};
        this.mParseFpgaResponseDataWrapper = new ByteArrayWrapper();
    }

    public final void startFpgaUpgrade(ArrayList<byte[]> upgradePackageList) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(upgradePackageList, "upgradePackageList");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EchoxFpgaUpgrade$startFpgaUpgrade$1(this, upgradePackageList, null), 3, null);
        this.fpgaUpgradeJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object sendFpgaData(ArrayList<byte[]> arrayList, Continuation<? super Unit> continuation) {
        EchoxFpgaUpgrade$sendFpgaData$1 echoxFpgaUpgrade$sendFpgaData$1;
        int i;
        EchoxFpgaUpgrade echoxFpgaUpgrade;
        Throwable th;
        EchoxFpgaUpgrade echoxFpgaUpgrade2;
        if (continuation instanceof EchoxFpgaUpgrade$sendFpgaData$1) {
            echoxFpgaUpgrade$sendFpgaData$1 = (EchoxFpgaUpgrade$sendFpgaData$1) continuation;
            if ((echoxFpgaUpgrade$sendFpgaData$1.label & Integer.MIN_VALUE) != 0) {
                echoxFpgaUpgrade$sendFpgaData$1.label -= Integer.MIN_VALUE;
                Object obj = echoxFpgaUpgrade$sendFpgaData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = echoxFpgaUpgrade$sendFpgaData$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Log.d(this.TAG, "sendFpgaData start");
                    this.upgradePackageListSize = arrayList.size();
                    while (this.isKeepRunning.get()) {
                        try {
                            try {
                                if (this.sendFpgaDataCount.get() < this.upgradePackageListSize) {
                                    if (this.fpgaResponseDataVerifyOk.get()) {
                                        Log.d(this.TAG, "sendFpgaData: sendFpgaDataCount = " + this.sendFpgaDataCount);
                                        byte[] bArr = arrayList.get(this.sendFpgaDataCount.get());
                                        Intrinsics.checkExpressionValueIsNotNull(bArr, "upgradePackageList.get(sendFpgaDataCount.get())");
                                        byte[] bArr2 = bArr;
                                        Log.d(this.TAG, "sendFpgaData: fpgaData size = " + bArr2.length);
                                        sendOriginBin(bArr2);
                                        this.fpgaResponseDataVerifyOk.set(false);
                                        this.sendFpgaDataCount.incrementAndGet();
                                        this.hasSendNewPacket.set(true);
                                    }
                                } else {
                                    this.isKeepRunning.set(false);
                                }
                            } catch (Exception e) {
                                Log.d(this.TAG, "sendFpgaData: Exception is " + e);
                                e.printStackTrace();
                                Log.d(this.TAG, "fpgaSendSocket close");
                                echoxFpgaUpgrade$sendFpgaData$1.L$0 = this;
                                echoxFpgaUpgrade$sendFpgaData$1.L$1 = arrayList;
                                echoxFpgaUpgrade$sendFpgaData$1.label = 2;
                                if (DelayKt.delay(2000L, echoxFpgaUpgrade$sendFpgaData$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        } catch (Throwable th2) {
                            Log.d(this.TAG, "fpgaSendSocket close");
                            echoxFpgaUpgrade$sendFpgaData$1.L$0 = this;
                            echoxFpgaUpgrade$sendFpgaData$1.L$1 = arrayList;
                            echoxFpgaUpgrade$sendFpgaData$1.L$2 = th2;
                            echoxFpgaUpgrade$sendFpgaData$1.label = 3;
                            if (DelayKt.delay(2000L, echoxFpgaUpgrade$sendFpgaData$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            echoxFpgaUpgrade = this;
                            th = th2;
                        }
                    }
                    Log.d(this.TAG, "fpgaSendSocket close");
                    echoxFpgaUpgrade$sendFpgaData$1.L$0 = this;
                    echoxFpgaUpgrade$sendFpgaData$1.L$1 = arrayList;
                    echoxFpgaUpgrade$sendFpgaData$1.label = 1;
                    if (DelayKt.delay(2000L, echoxFpgaUpgrade$sendFpgaData$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    echoxFpgaUpgrade2 = this;
                } else {
                    if (i != 1 && i != 2) {
                        if (i == 3) {
                            th = (Throwable) echoxFpgaUpgrade$sendFpgaData$1.L$2;
                            echoxFpgaUpgrade = (EchoxFpgaUpgrade) echoxFpgaUpgrade$sendFpgaData$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            echoxFpgaUpgrade.socketClose();
                            echoxFpgaUpgrade.lidarUpgralistener.onLidarUpgradeComplete(true);
                            throw th;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    echoxFpgaUpgrade2 = (EchoxFpgaUpgrade) echoxFpgaUpgrade$sendFpgaData$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                echoxFpgaUpgrade2.socketClose();
                echoxFpgaUpgrade2.lidarUpgralistener.onLidarUpgradeComplete(true);
                return Unit.INSTANCE;
            }
        }
        echoxFpgaUpgrade$sendFpgaData$1 = new EchoxFpgaUpgrade$sendFpgaData$1(this, continuation);
        Object obj2 = echoxFpgaUpgrade$sendFpgaData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = echoxFpgaUpgrade$sendFpgaData$1.label;
        if (i != 0) {
        }
        echoxFpgaUpgrade2.socketClose();
        echoxFpgaUpgrade2.lidarUpgralistener.onLidarUpgradeComplete(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void receiveFpgaData() {
        DatagramSocket datagramSocket;
        Log.d(this.TAG, "receiveFpgaData start");
        DatagramSocket datagramSocket2 = (DatagramSocket) null;
        try {
            try {
                datagramSocket = new DatagramSocket((SocketAddress) null);
                try {
                    datagramSocket.setReuseAddress(true);
                    datagramSocket.bind(new InetSocketAddress(this.fpgaResponsePort));
                    while (this.isKeepRunning.get()) {
                        byte[] bArr = new byte[this.fpgaResponsePackageSize];
                        datagramSocket.receive(new DatagramPacket(bArr, bArr.length));
                        parseFpgaResponseData(bArr);
                    }
                    Log.d(this.TAG, "fpgaReceiveSocket close");
                    datagramSocket.close();
                } catch (Exception e) {
                    e = e;
                    datagramSocket2 = datagramSocket;
                    Log.d(this.TAG, "receiveFpgaData: Exception is " + e);
                    e.printStackTrace();
                    Log.d(this.TAG, "fpgaReceiveSocket close");
                    if (datagramSocket2 != null) {
                        datagramSocket2.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    Log.d(this.TAG, "fpgaReceiveSocket close");
                    if (datagramSocket != null) {
                        datagramSocket.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                datagramSocket = datagramSocket2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private final void parseFpgaResponseData(byte[] responseData) {
        ByteArrayWrapper byteArrayWrapper = this.mParseFpgaResponseDataWrapper;
        if (byteArrayWrapper == null) {
            Intrinsics.throwNpe();
        }
        byteArrayWrapper.prepare(responseData);
        int length = responseData.length;
        int i = 0;
        while (true) {
            if (i < length) {
                ByteArrayWrapper byteArrayWrapper2 = this.mParseFpgaResponseDataWrapper;
                UByte m4522boximpl = byteArrayWrapper2 != null ? UByte.m4522boximpl(byteArrayWrapper2.popUByte()) : null;
                if (m4522boximpl != null ? true ^ UByte.m4535equalsimpl0(m4522boximpl.getData(), UByteArray.m4577getimpl(this.fpgaResponseArray, i)) : true) {
                    ByteArrayWrapper byteArrayWrapper3 = this.mParseFpgaResponseDataWrapper;
                    if (byteArrayWrapper3 == null) {
                        Intrinsics.throwNpe();
                    }
                    byteArrayWrapper3.reset();
                    return;
                }
                i++;
            } else {
                Log.d(this.TAG, "fpga response data verify success");
                this.waitForFpgaResponseTime.set(0);
                this.hasSendNewPacket.set(false);
                this.fpgaResponseDataVerifyOk.set(true);
                ByteArrayWrapper byteArrayWrapper4 = this.mParseFpgaResponseDataWrapper;
                if (byteArrayWrapper4 == null) {
                    Intrinsics.throwNpe();
                }
                byteArrayWrapper4.reset();
                return;
            }
        }
    }

    static {
        System.loadLibrary("EchoxUpgrade");
    }
}
