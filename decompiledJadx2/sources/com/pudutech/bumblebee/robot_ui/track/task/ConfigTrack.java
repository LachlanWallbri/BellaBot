package com.pudutech.bumblebee.robot_ui.track.task;

import android.content.Context;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.robot.module.report.track2.MirSDKConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConfigTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/ConfigTrack;", "", "()V", "uploadConfig", "", "context", "Landroid/content/Context;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ConfigTrack {
    public static final ConfigTrack INSTANCE = new ConfigTrack();

    private ConfigTrack() {
    }

    public final void uploadConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        MirSDKConfig.INSTANCE.uploadRGBDFallDetConfig();
        MirSDKConfig.INSTANCE.uploadMagnetFallDetConfig(context, MachineInfoHelper.INSTANCE.getMagicSensor() == MachineInfo.MagicSensor.Support);
        MirSDKConfig.INSTANCE.uploadESPConfig(context);
        MirSDKConfig.INSTANCE.uploadSlipConfig();
        MirSDKConfig.INSTANCE.uploadMarkerCameraConfig(context);
        MirSDKConfig.INSTANCE.uploadLocalizationLostThresholdConfig();
    }
}
