package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseConfig;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t\u001a\u0012\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u000b\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\r\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u000f\u001a\u0012\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0011\u001a\u0012\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0013\u001a\u0012\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0015¨\u0006\u0016"}, m3961d2 = {"onConfig", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "type", "", "configContent", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "onESPConfig", "config", "Lcom/pudutech/robot/module/report/track2/BaseConfig$ESPConfig;", "onLegDetectionConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$LegDetectionConfig;", "onLocalizationLostThresholdConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$LocalizationLostThresholdConfig;", "onMagnetFallDetConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$MagnetFallDetConfig;", "onMarkerCameraConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$MarkerCameraConfig;", "onRGBDFallDetConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$DownRGBDConfig;", "onSlipConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$SlipConfig;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ConfigKt {
    public static final void onRGBDFallDetConfig(TrackingReportManager onRGBDFallDetConfig, BaseConfig.DownRGBDConfig config) {
        Intrinsics.checkParameterIsNotNull(onRGBDFallDetConfig, "$this$onRGBDFallDetConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onRGBDFallDetConfig, ConfigType.DOWN_RGBD.name(), config);
    }

    public static final void onMagnetFallDetConfig(TrackingReportManager onMagnetFallDetConfig, BaseConfig.MagnetFallDetConfig config) {
        Intrinsics.checkParameterIsNotNull(onMagnetFallDetConfig, "$this$onMagnetFallDetConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onMagnetFallDetConfig, ConfigType.MAGNET_FALL_DET.name(), config);
    }

    public static final void onESPConfig(TrackingReportManager onESPConfig, BaseConfig.ESPConfig config) {
        Intrinsics.checkParameterIsNotNull(onESPConfig, "$this$onESPConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onESPConfig, ConfigType.ESP.name(), config);
    }

    public static final void onLegDetectionConfig(TrackingReportManager onLegDetectionConfig, BaseConfig.LegDetectionConfig config) {
        Intrinsics.checkParameterIsNotNull(onLegDetectionConfig, "$this$onLegDetectionConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onLegDetectionConfig, ConfigType.LEG_DETECTION.name(), config);
    }

    public static final void onSlipConfig(TrackingReportManager onSlipConfig, BaseConfig.SlipConfig config) {
        Intrinsics.checkParameterIsNotNull(onSlipConfig, "$this$onSlipConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onSlipConfig, ConfigType.SLIP.name(), config);
    }

    public static final void onMarkerCameraConfig(TrackingReportManager onMarkerCameraConfig, BaseConfig.MarkerCameraConfig config) {
        Intrinsics.checkParameterIsNotNull(onMarkerCameraConfig, "$this$onMarkerCameraConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onMarkerCameraConfig, ConfigType.MARKER_CAMERA.name(), config);
    }

    public static final void onLocalizationLostThresholdConfig(TrackingReportManager onLocalizationLostThresholdConfig, BaseConfig.LocalizationLostThresholdConfig config) {
        Intrinsics.checkParameterIsNotNull(onLocalizationLostThresholdConfig, "$this$onLocalizationLostThresholdConfig");
        Intrinsics.checkParameterIsNotNull(config, "config");
        onConfig(onLocalizationLostThresholdConfig, ConfigType.LOCALIZATION_LOST_THRESHOLD.name(), config);
    }

    public static final void onConfig(TrackingReportManager onConfig, String type, BaseConfig configContent) {
        Intrinsics.checkParameterIsNotNull(onConfig, "$this$onConfig");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(configContent, "configContent");
        PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.CONFIG.toString(), MapsKt.mapOf(TuplesKt.m3968to(TrackKey.CONFIG_TYPE, type), TuplesKt.m3968to(TrackKey.CONFIG_CONTENT, GsonUtils.toJson(configContent))), 0));
    }
}
