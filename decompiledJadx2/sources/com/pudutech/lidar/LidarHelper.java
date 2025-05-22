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
import com.pudutech.lidar.eai_t05.EAI_T05;
import com.pudutech.lidar.eai_tg30.EAI_TG30;
import com.pudutech.lidar.eai_tg30.EAI_TG30_SLAMWARE;
import com.pudutech.lidar.ltme_02a.LTME_02A;
import com.pudutech.lidar.port.SerialUSB;
import com.pudutech.lidar.rplidar_15d.LDS_15D;
import com.pudutech.lidar.rplidar_50c.LDS_50C;
import com.pudutech.lidar.rplidar_a2m7.RPLIDAR_A2M7;
import com.pudutech.lidar.rplidar_a3.RPLIDAR_A3;
import com.pudutech.lidar.rplidar_s1.RPLIDAR_S1;
import com.pudutech.lidar.test_activity.ShowRadarActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LidarHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ \u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\rH\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/lidar/LidarHelper;", "", "()V", "TAG", "", "<set-?>", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "mAdapter", "getMAdapter", "()Lcom/pudutech/lidar/base/BaseLidarAdapter;", "canScanCloseBehind", "", "version", "Lcom/pudutech/lidar/LidarVersion;", "createLidar", "context", "Landroid/content/Context;", "lidarVersion", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "Lcom/pudutech/lidar/base/BaseLidar;", "startTestActivity", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LidarHelper {
    private static BaseLidarAdapter mAdapter;
    public static final LidarHelper INSTANCE = new LidarHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LidarVersion.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LidarVersion.HLS.ordinal()] = 1;
            $EnumSwitchMapping$0[LidarVersion.EAI_X4.ordinal()] = 2;
            $EnumSwitchMapping$0[LidarVersion.RPLIDAR_A3.ordinal()] = 3;
            $EnumSwitchMapping$0[LidarVersion.EAI_G4.ordinal()] = 4;
            $EnumSwitchMapping$0[LidarVersion.EAI_G6.ordinal()] = 5;
            $EnumSwitchMapping$0[LidarVersion.EAI_G6_UART.ordinal()] = 6;
            $EnumSwitchMapping$0[LidarVersion.RPLIDAR_S1.ordinal()] = 7;
            $EnumSwitchMapping$0[LidarVersion.RPLIDAR_S1_UART.ordinal()] = 8;
            $EnumSwitchMapping$0[LidarVersion.RPLIDAR_A2M7.ordinal()] = 9;
            $EnumSwitchMapping$0[LidarVersion.RPLIDAR_A2M7_UART.ordinal()] = 10;
            $EnumSwitchMapping$0[LidarVersion.EAI_T05.ordinal()] = 11;
            $EnumSwitchMapping$0[LidarVersion.EAI_TG30.ordinal()] = 12;
            $EnumSwitchMapping$0[LidarVersion.EAI_TG30_UART.ordinal()] = 13;
            $EnumSwitchMapping$0[LidarVersion.EAI_G7.ordinal()] = 14;
            $EnumSwitchMapping$0[LidarVersion.EAI_G7_UART.ordinal()] = 15;
            $EnumSwitchMapping$0[LidarVersion.EAI_TG30_SLAMWARE.ordinal()] = 16;
            $EnumSwitchMapping$0[LidarVersion.LTME_02A.ordinal()] = 17;
            $EnumSwitchMapping$0[LidarVersion.LDS_50C.ordinal()] = 18;
            $EnumSwitchMapping$0[LidarVersion.LDS_15D.ordinal()] = 19;
        }
    }

    private LidarHelper() {
    }

    public final BaseLidarAdapter getMAdapter() {
        return mAdapter;
    }

    private final BaseLidar createLidar(LidarVersion lidarVersion) {
        Pdlog.m3273d(TAG, "createLidar lidarVersion=" + lidarVersion);
        switch (WhenMappings.$EnumSwitchMapping$0[lidarVersion.ordinal()]) {
            case 1:
                return new HLS();
            case 2:
                return new EAI_X4();
            case 3:
                return new RPLIDAR_A3();
            case 4:
                return new EAI_G4();
            case 5:
            case 6:
                return new EAI_G6();
            case 7:
            case 8:
                return new RPLIDAR_S1();
            case 9:
            case 10:
                return new RPLIDAR_A2M7();
            case 11:
                return new EAI_T05();
            case 12:
            case 13:
                return new EAI_TG30();
            case 14:
            case 15:
                return new EAI_G7();
            case 16:
                return new EAI_TG30_SLAMWARE();
            case 17:
                return new LTME_02A();
            case 18:
                return new LDS_50C();
            case 19:
                return new LDS_15D();
            default:
                Pdlog.m3274e(TAG, "version=" + lidarVersion + " not support.");
                return null;
        }
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
        } else if (lidarVersion == LidarVersion.EAI_G6_UART || lidarVersion == LidarVersion.RPLIDAR_A2M7_UART || lidarVersion == LidarVersion.RPLIDAR_S1_UART || lidarVersion == LidarVersion.EAI_TG30_UART || lidarVersion == LidarVersion.EAI_G7_UART || lidarVersion == LidarVersion.LDS_15D) {
            SerialLidarAdapter serialLidarAdapter = new SerialLidarAdapter(context, lidarVersion, callback);
            serialLidarAdapter.setLidar((SerialLidar) createLidar);
            ethernetLidarAdapter = serialLidarAdapter;
        } else if (lidarVersion == LidarVersion.EAI_TG30_SLAMWARE) {
            SlamwareLidarAdapter slamwareLidarAdapter = new SlamwareLidarAdapter(context, lidarVersion, callback);
            slamwareLidarAdapter.setLidar((SlamwareLidar) createLidar);
            ethernetLidarAdapter = slamwareLidarAdapter;
        } else {
            SerialUSBLidarAdapter serialUSBLidarAdapter = new SerialUSBLidarAdapter(context, lidarVersion, callback);
            serialUSBLidarAdapter.setSerialModel(SerialUSB.INSTANCE.getCP2102(), SerialUSB.INSTANCE.getCH340());
            serialUSBLidarAdapter.setLidar((SerialLidar) createLidar);
            ethernetLidarAdapter = serialUSBLidarAdapter;
        }
        createLidar.setControlHandler(ethernetLidarAdapter.getControlHandler());
        mAdapter = ethernetLidarAdapter;
        return ethernetLidarAdapter;
    }

    public final void startTestActivity(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        CalibrationHelper.INSTANCE.setSecondLidar(false);
        Intent intent = new Intent(context, (Class<?>) ShowRadarActivity.class);
        ShowRadarActivity.INSTANCE.setLidar(mAdapter);
        context.startActivity(intent);
    }

    public final boolean canScanCloseBehind(LidarVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return version == LidarVersion.RPLIDAR_S1 || version == LidarVersion.EAI_TG30_UART || version == LidarVersion.EAI_TG30 || version == LidarVersion.EAI_T05 || version == LidarVersion.EAI_TG30_SLAMWARE || version == LidarVersion.LD_06;
    }
}
