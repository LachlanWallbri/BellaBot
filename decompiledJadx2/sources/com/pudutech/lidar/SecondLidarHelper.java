package com.pudutech.lidar;

import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.base.BaseLidar;
import com.pudutech.lidar.base.BaseLidarAdapter;
import com.pudutech.lidar.base.CalibrationHelper;
import com.pudutech.lidar.base.EthernetLidar;
import com.pudutech.lidar.base.EthernetLidarAdapter;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarAdapter;
import com.pudutech.lidar.base.SerialUSBLidarAdapter;
import com.pudutech.lidar.base.SlamwareLidar;
import com.pudutech.lidar.base.SlamwareLidarAdapter;
import com.pudutech.lidar.ld06.LD06;
import com.pudutech.lidar.port.SerialUSB;
import com.pudutech.lidar.test_activity.ShowRadarActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SecondLidarHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/lidar/SecondLidarHelper;", "", "()V", "TAG", "", "<set-?>", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "mAdapter", "getMAdapter", "()Lcom/pudutech/lidar/base/BaseLidarAdapter;", "createLidar", "context", "Landroid/content/Context;", "lidarVersion", "Lcom/pudutech/lidar/LidarVersion;", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "Lcom/pudutech/lidar/base/BaseLidar;", "startTestActivity", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SecondLidarHelper {
    private static BaseLidarAdapter mAdapter;
    public static final SecondLidarHelper INSTANCE = new SecondLidarHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LidarVersion.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LidarVersion.LD_06.ordinal()] = 1;
        }
    }

    private SecondLidarHelper() {
    }

    public final BaseLidarAdapter getMAdapter() {
        return mAdapter;
    }

    private final BaseLidar createLidar(LidarVersion lidarVersion) {
        LD06 ld06;
        Pdlog.m3273d(TAG, "createLidar lidarVersion=" + lidarVersion);
        if (WhenMappings.$EnumSwitchMapping$0[lidarVersion.ordinal()] == 1) {
            ld06 = new LD06();
        } else {
            Pdlog.m3274e(TAG, "version=" + lidarVersion + " not support.");
            ld06 = null;
        }
        return ld06;
    }

    public final BaseLidarAdapter createLidar(Context context, LidarVersion lidarVersion, LidarAdapterCallback callback) {
        EthernetLidarAdapter ethernetLidarAdapter;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(lidarVersion, "lidarVersion");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        BaseLidar createLidar = createLidar(lidarVersion);
        if (createLidar == null) {
            Pdlog.m3274e(TAG, "lidar is null. please add lidar creator");
            return null;
        }
        if (lidarVersion == LidarVersion.EAI_T05 || lidarVersion == LidarVersion.LTME_02A) {
            EthernetLidarAdapter ethernetLidarAdapter2 = new EthernetLidarAdapter(context, lidarVersion, callback);
            ethernetLidarAdapter2.setLidar((EthernetLidar) createLidar);
            ethernetLidarAdapter = ethernetLidarAdapter2;
        } else if (lidarVersion == LidarVersion.EAI_G6_UART || lidarVersion == LidarVersion.RPLIDAR_A2M7_UART || lidarVersion == LidarVersion.RPLIDAR_S1_UART || lidarVersion == LidarVersion.EAI_TG30_UART || lidarVersion == LidarVersion.EAI_G7_UART) {
            SerialLidarAdapter serialLidarAdapter = new SerialLidarAdapter(context, lidarVersion, callback);
            serialLidarAdapter.setLidar((SerialLidar) createLidar);
            ethernetLidarAdapter = serialLidarAdapter;
        } else if (lidarVersion == LidarVersion.EAI_TG30_SLAMWARE) {
            SlamwareLidarAdapter slamwareLidarAdapter = new SlamwareLidarAdapter(context, lidarVersion, callback);
            slamwareLidarAdapter.setLidar((SlamwareLidar) createLidar);
            ethernetLidarAdapter = slamwareLidarAdapter;
        } else {
            SerialUSBLidarAdapter serialUSBLidarAdapter = new SerialUSBLidarAdapter(context, lidarVersion, callback);
            serialUSBLidarAdapter.setSerialModel(SerialUSB.INSTANCE.getCP2102_A());
            serialUSBLidarAdapter.setLidar((SerialLidar) createLidar);
            ethernetLidarAdapter = serialUSBLidarAdapter;
        }
        createLidar.setControlHandler(ethernetLidarAdapter.getControlHandler());
        mAdapter = ethernetLidarAdapter;
        return ethernetLidarAdapter;
    }

    public final void startTestActivity(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        CalibrationHelper.INSTANCE.setSecondLidar(true);
        Intent intent = new Intent(context, (Class<?>) ShowRadarActivity.class);
        ShowRadarActivity.INSTANCE.setLidar(mAdapter);
        context.startActivity(intent);
    }
}
