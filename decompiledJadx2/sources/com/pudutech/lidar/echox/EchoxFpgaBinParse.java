package com.pudutech.lidar.echox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.pudutech.lidar.LidarUpgradeListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: EchoxFpgaBinParse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\tH\u0002J\u001e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0011j\b\u0012\u0004\u0012\u00020\t`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/lidar/echox/EchoxFpgaBinParse;", "", "()V", "TAG", "", "assetManager", "Landroid/content/res/AssetManager;", "assetsFolderName", "cmdArray", "", "echoxFpgaUpgrade", "Lcom/pudutech/lidar/echox/EchoxFpgaUpgrade;", "fpgaBinParseJob", "Lkotlinx/coroutines/Job;", "mHasProcessUpgrade", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mUpgradePackageList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "perUpgradeByteSizeFormBin", "", "perUpgradePackageSize", "recognitionHead", "recognitionTail", "processInputStream", "", "inputStream", "Ljava/io/InputStream;", "splitUpgradeBuffer", "upgradeBuffer", "startFpgaBinParse", "context", "Landroid/content/Context;", "currentVersion", "lidarUpgralistener", "Lcom/pudutech/lidar/LidarUpgradeListener;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class EchoxFpgaBinParse {
    private AssetManager assetManager;
    private EchoxFpgaUpgrade echoxFpgaUpgrade;
    private Job fpgaBinParseJob;
    private final String TAG = "EchoxUpgrade";
    private final String assetsFolderName = "echox";
    private final int perUpgradePackageSize = 1033;
    private final int perUpgradeByteSizeFormBin = 1024;
    private ArrayList<byte[]> mUpgradePackageList = new ArrayList<>();
    private AtomicBoolean mHasProcessUpgrade = new AtomicBoolean(false);
    private final byte[] recognitionHead = {(byte) 84, (byte) 63, (byte) 81, (byte) 165};
    private final byte[] cmdArray = {(byte) 49, (byte) 17, (byte) 1};
    private final byte[] recognitionTail = {(byte) 85, (byte) 161, (byte) 15, (byte) 65};

    public final void startFpgaBinParse(Context context, String currentVersion, LidarUpgradeListener lidarUpgralistener) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(currentVersion, "currentVersion");
        Intrinsics.checkParameterIsNotNull(lidarUpgralistener, "lidarUpgralistener");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EchoxFpgaBinParse$startFpgaBinParse$1(this, context, currentVersion, lidarUpgralistener, null), 3, null);
        this.fpgaBinParseJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processInputStream(InputStream inputStream) {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = (BufferedInputStream) null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    int available = bufferedInputStream.available();
                    Log.d(this.TAG, "processUpgrade: length = " + available + " byte");
                    byte[] bArr = new byte[available];
                    bufferedInputStream.read(bArr);
                    splitUpgradeBuffer(bArr);
                    bufferedInputStream.close();
                } catch (Exception e) {
                    e = e;
                    bufferedInputStream2 = bufferedInputStream;
                    String str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("processUpgrade: Exception is ");
                    e.printStackTrace();
                    sb.append(Unit.INSTANCE);
                    Log.d(str, sb.toString());
                    e.printStackTrace();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private final void splitUpgradeBuffer(byte[] upgradeBuffer) {
        Log.d(this.TAG, "splitUpgradeBuffer: upgradeBuffer = " + upgradeBuffer.length + " byte");
        int length = upgradeBuffer.length;
        int i = this.perUpgradeByteSizeFormBin;
        int i2 = length % i;
        int length2 = upgradeBuffer.length / i;
        int i3 = 0;
        while (true) {
            int i4 = 2;
            if (i3 >= length2) {
                break;
            }
            byte[] bArr = new byte[this.perUpgradePackageSize];
            byte[] bArr2 = this.recognitionHead;
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
            byte[] bArr3 = this.cmdArray;
            if (i3 <= 1) {
                i4 = i3;
            }
            System.arraycopy(bArr3, i4, bArr, this.recognitionHead.length, 1);
            int i5 = this.perUpgradeByteSizeFormBin;
            System.arraycopy(upgradeBuffer, (i3 * i5) + 0, bArr, this.recognitionHead.length + 1, i5);
            byte[] bArr4 = this.recognitionTail;
            System.arraycopy(bArr4, 0, bArr, this.recognitionHead.length + 1 + this.perUpgradeByteSizeFormBin, bArr4.length);
            this.mUpgradePackageList.add(bArr);
            i3++;
        }
        if (i2 > 0) {
            byte[] bArr5 = new byte[this.perUpgradePackageSize];
            byte[] bArr6 = this.recognitionHead;
            System.arraycopy(bArr6, 0, bArr5, 0, bArr6.length);
            System.arraycopy(this.cmdArray, 2, bArr5, this.recognitionHead.length, 1);
            System.arraycopy(upgradeBuffer, length2 * this.perUpgradeByteSizeFormBin, bArr5, this.recognitionHead.length + 1, i2);
            int i6 = this.perUpgradeByteSizeFormBin;
            for (int length3 = this.recognitionHead.length + 1 + i2; length3 < i6; length3++) {
                bArr5[length3] = 0;
            }
            byte[] bArr7 = this.recognitionTail;
            System.arraycopy(bArr7, 0, bArr5, this.recognitionHead.length + 1 + this.perUpgradeByteSizeFormBin, bArr7.length);
            this.mUpgradePackageList.add(bArr5);
        }
        byte[] bArr8 = this.mUpgradePackageList.get(0);
        Intrinsics.checkExpressionValueIsNotNull(bArr8, "mUpgradePackageList.get(0)");
        this.mUpgradePackageList.remove(0);
        this.mUpgradePackageList.add(bArr8);
        Log.d(this.TAG, "mUpgradePackageList size = " + this.mUpgradePackageList.size() + ' ');
        EchoxFpgaUpgrade echoxFpgaUpgrade = this.echoxFpgaUpgrade;
        if (echoxFpgaUpgrade != null) {
            echoxFpgaUpgrade.startFpgaUpgrade(this.mUpgradePackageList);
        }
    }
}
