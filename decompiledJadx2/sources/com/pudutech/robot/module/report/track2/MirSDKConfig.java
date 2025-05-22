package com.pudutech.robot.module.report.track2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseConfig;
import com.pudutech.schedulerlib.ScheduleConstant;
import java.io.File;
import java.io.FileReader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: MirSDKConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0010\u001a\u00020\fJ\u0006\u0010\u0011\u001a\u00020\fJ\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\fJ\u0006\u0010\u0015\u001a\u00020\f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/MirSDKConfig;", "", "()V", "getPreferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "prefName", "", "readFile", "file", "uploadAll", "", "have", "", "uploadESPConfig", "uploadLegDetectionConfig", "uploadLocalizationLostThresholdConfig", "uploadMagnetFallDetConfig", "uploadMarkerCameraConfig", "uploadRGBDFallDetConfig", "uploadSlipConfig", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class MirSDKConfig {
    public static final MirSDKConfig INSTANCE = new MirSDKConfig();

    private MirSDKConfig() {
    }

    private final SharedPreferences getPreferences(Context context, String prefName) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(prefName, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.applicationConte…xt.MODE_PRIVATE\n        )");
        return sharedPreferences;
    }

    private final String readFile(String file) {
        return FilesKt.readText$default(new File(file), null, 1, null);
    }

    public final void uploadAll(Context context, boolean have) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            uploadRGBDFallDetConfig();
            uploadMagnetFallDetConfig(context, have);
            uploadESPConfig(context);
            uploadLegDetectionConfig();
            uploadSlipConfig();
            uploadMarkerCameraConfig(context);
            uploadLocalizationLostThresholdConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void uploadRGBDFallDetConfig() {
        try {
            int parseInt = new File("/sdcard/pudu/config/feasibal_segment.config").exists() ? Integer.parseInt(readFile("/sdcard/pudu/config/feasibal_segment.config")) : 1;
            if (parseInt == 0) {
                JSONObject jSONObject = new JSONObject(readFile(MapFilePathConfig.PRE_PARAM_PATH));
                if (jSONObject.has("det_drop")) {
                    ConfigKt.onRGBDFallDetConfig(TrackingReportManager.INSTANCE, new BaseConfig.DownRGBDConfig(parseInt, jSONObject.getInt("det_drop")));
                    return;
                } else {
                    TrackingReportManager.INSTANCE.throwOrLog("uploadRGBDFallDetConfig no det_drop");
                    return;
                }
            }
            if (parseInt == 1) {
                JSONObject jSONObject2 = new JSONObject(readFile(MapFilePathConfig.PRE_PARAMLINE_PATH));
                if (jSONObject2.has("det_drop")) {
                    ConfigKt.onRGBDFallDetConfig(TrackingReportManager.INSTANCE, new BaseConfig.DownRGBDConfig(parseInt, jSONObject2.getInt("det_drop")));
                    return;
                } else {
                    TrackingReportManager.INSTANCE.throwOrLog("uploadRGBDFallDetConfig no det_drop");
                    return;
                }
            }
            TrackingReportManager.INSTANCE.throwOrLog("uploadRGBDFallDetConfig algorithm:" + parseInt + ";There is no algorithm of this type");
        } catch (Exception e) {
            TrackingReportManager.INSTANCE.throwOrLog("uploadRGBDFallDetConfig = " + e);
        }
    }

    public final void uploadMagnetFallDetConfig(Context context, boolean have) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        ConfigKt.onMagnetFallDetConfig(TrackingReportManager.INSTANCE, new BaseConfig.MagnetFallDetConfig(have, getPreferences(context, "mirhardware").getBoolean("magic_enable", false) ? 1 : 0));
    }

    public final void uploadESPConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        ConfigKt.onESPConfig(TrackingReportManager.INSTANCE, new BaseConfig.ESPConfig(getPreferences(context, "mirhardware").getBoolean("esp_enable", true), getPreferences(context, "mirsdk").getInt(ScheduleConstant.PREFERENCE_KEY, 1)));
    }

    public final void uploadLegDetectionConfig() {
        try {
            FileReader fileReader = new FileReader("/sdcard/pudu/config/person_det_config.xml");
            Throwable th = (Throwable) null;
            try {
                XmlPullParser newPullParser = Xml.newPullParser();
                Intrinsics.checkExpressionValueIsNotNull(newPullParser, "Xml.newPullParser()");
                newPullParser.setInput(fileReader);
                String str = "";
                String str2 = "";
                String str3 = str2;
                String str4 = str3;
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    if (eventType == 2) {
                        if (Intrinsics.areEqual(newPullParser.getName(), "near_dist")) {
                            String nextText = newPullParser.nextText();
                            Intrinsics.checkExpressionValueIsNotNull(nextText, "xmlPullParser.nextText()");
                            str = nextText;
                        } else if (Intrinsics.areEqual(newPullParser.getName(), "max_dist")) {
                            String nextText2 = newPullParser.nextText();
                            Intrinsics.checkExpressionValueIsNotNull(nextText2, "xmlPullParser.nextText()");
                            str2 = nextText2;
                        } else if (Intrinsics.areEqual(newPullParser.getName(), "min_valid_angle")) {
                            String nextText3 = newPullParser.nextText();
                            Intrinsics.checkExpressionValueIsNotNull(nextText3, "xmlPullParser.nextText()");
                            str3 = nextText3;
                        } else if (Intrinsics.areEqual(newPullParser.getName(), "max_valid_angle")) {
                            String nextText4 = newPullParser.nextText();
                            Intrinsics.checkExpressionValueIsNotNull(nextText4, "xmlPullParser.nextText()");
                            str4 = nextText4;
                        }
                    }
                }
                ConfigKt.onLegDetectionConfig(TrackingReportManager.INSTANCE, new BaseConfig.LegDetectionConfig(str, str2, str3, str4));
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileReader, th);
            } finally {
            }
        } catch (Exception e) {
            TrackingReportManager.INSTANCE.throwOrLog("uploadLegDetectionConfig = " + e);
        }
    }

    public final void uploadSlipConfig() {
        try {
            JSONObject jSONObject = new JSONObject(readFile(MapFilePathConfig.LOCATION_CONFIG_PATH));
            if (jSONObject.has("use_sm_opt_angle") && jSONObject.has("slip_use_scan_match")) {
                ConfigKt.onSlipConfig(TrackingReportManager.INSTANCE, new BaseConfig.SlipConfig(jSONObject.getInt("use_sm_opt_angle"), jSONObject.getInt("slip_use_scan_match")));
            } else {
                TrackingReportManager.INSTANCE.throwOrLog("uploadSlipConfig no use_sm_opt_angle or no slip_use_scan_match");
            }
        } catch (Exception e) {
            TrackingReportManager.INSTANCE.throwOrLog("uploadSlipConfig = " + e);
        }
    }

    public final void uploadMarkerCameraConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        ConfigKt.onMarkerCameraConfig(TrackingReportManager.INSTANCE, new BaseConfig.MarkerCameraConfig(getPreferences(context, "mirhardware").getBoolean("mark_enable", true)));
    }

    public final void uploadLocalizationLostThresholdConfig() {
        try {
            JSONObject jSONObject = new JSONObject(readFile(MapFilePathConfig.LOCATION_CONFIG_PATH));
            if (jSONObject.has("locator_monitor.lost_particle_score_threshold") && jSONObject.has("locator_monitor.lost_particle_count_threshold")) {
                ConfigKt.onLocalizationLostThresholdConfig(TrackingReportManager.INSTANCE, new BaseConfig.LocalizationLostThresholdConfig(jSONObject.getDouble("locator_monitor.lost_particle_score_threshold"), jSONObject.getInt("locator_monitor.lost_particle_count_threshold")));
            } else {
                TrackingReportManager.INSTANCE.throwOrLog("uploadLocalizationLostThresholdConfig no locator_monitor.lost_particle_score_threshold or no locator_monitor.lost_particle_count_threshold");
            }
        } catch (Exception e) {
            TrackingReportManager.INSTANCE.throwOrLog("uploadLocalizationLostThresholdConfig = " + e);
        }
    }
}
