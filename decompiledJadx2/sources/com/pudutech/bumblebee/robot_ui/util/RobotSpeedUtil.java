package com.pudutech.bumblebee.robot_ui.util;

import android.content.Context;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotSpeedUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0004J\u0016\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0004J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0004J\u0016\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0004J\u0016\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/RobotSpeedUtil;", "", "()V", "DEFAULT_VALUE", "", "KEY_CRUISE_SPEED", "KEY_DELIVER_SPEED", "KEY_GO_HOME_SPEED", "KEY_GREETER_SPEED", "KEY_RECYCLE_PLATE_SPEED", "getCruiseSpeedLevel", "context", "Landroid/content/Context;", "getDeliverSpeedLevel", "getGoHomeSpeedLevel", "getGreeterSpeedLevel", "getRecyclingSpeedLevel", "isSteadyRepeatLast", "", "setCruiseSpeedLevel", "", "level", "setDeliverSpeedLevel", "setGoHomeSpeedLevel", "setGreeterSpeedLevel", "setRecyclingSpeedLevel", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotSpeedUtil {
    private static final String DEFAULT_VALUE = "0.7";
    public static final RobotSpeedUtil INSTANCE = new RobotSpeedUtil();
    private static final String KEY_CRUISE_SPEED = "key_cruise_speed";
    private static final String KEY_DELIVER_SPEED = "key_deliver_speed";
    private static final String KEY_GO_HOME_SPEED = "key_go_home_speed";
    private static final String KEY_GREETER_SPEED = "key_greeter_speed";
    private static final String KEY_RECYCLE_PLATE_SPEED = "key_recycle_plate_speed";

    private RobotSpeedUtil() {
    }

    public final String getCruiseSpeedLevel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = SpUtils.get(context, KEY_CRUISE_SPEED, "");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            str = RobotSetting.INSTANCE.getCruiseSpeedLevel();
        }
        String str3 = str;
        return str3 == null || str3.length() == 0 ? DEFAULT_VALUE : str;
    }

    public final void setCruiseSpeedLevel(Context context, String level) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(level, "level");
        SpUtils.set(context, KEY_CRUISE_SPEED, level);
        RobotSetting.INSTANCE.setCruiseSpeedLevel(level);
    }

    public final String getDeliverSpeedLevel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = SpUtils.get(context, KEY_DELIVER_SPEED, "");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            str = RobotSetting.INSTANCE.getDeliverSpeedLevel();
        }
        String str3 = str;
        return str3 == null || str3.length() == 0 ? DEFAULT_VALUE : str;
    }

    public final void setDeliverSpeedLevel(Context context, String level) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(level, "level");
        SpUtils.set(context, KEY_DELIVER_SPEED, level);
        RobotSetting.INSTANCE.setDeliverSpeedLevel(level);
    }

    public final String getGoHomeSpeedLevel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = SpUtils.get(context, KEY_GO_HOME_SPEED, "");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            str = RobotSetting.INSTANCE.getGoHomeSpeedLevel();
        }
        String str3 = str;
        return str3 == null || str3.length() == 0 ? DEFAULT_VALUE : str;
    }

    public final void setGoHomeSpeedLevel(Context context, String level) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(level, "level");
        SpUtils.set(context, KEY_GO_HOME_SPEED, level);
        RobotSetting.INSTANCE.setGoHomeSpeedLevel(level);
    }

    public final String getGreeterSpeedLevel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = SpUtils.get(context, KEY_GREETER_SPEED, "");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            str = RobotSetting.INSTANCE.getGreeterSpeedLevel();
        }
        String str3 = str;
        return str3 == null || str3.length() == 0 ? DEFAULT_VALUE : str;
    }

    public final void setGreeterSpeedLevel(Context context, String level) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(level, "level");
        SpUtils.set(context, KEY_GREETER_SPEED, level);
        RobotSetting.INSTANCE.setGreeterSpeedLevel(level);
    }

    public final String getRecyclingSpeedLevel(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = SpUtils.get(context, KEY_RECYCLE_PLATE_SPEED, "");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            str = RobotSetting.INSTANCE.getRecyclingSpeedLevel();
        }
        String str3 = str;
        return str3 == null || str3.length() == 0 ? DEFAULT_VALUE : str;
    }

    public final void setRecyclingSpeedLevel(Context context, String level) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(level, "level");
        SpUtils.set(context, KEY_RECYCLE_PLATE_SPEED, level);
        RobotSetting.INSTANCE.setRecyclingSpeedLevel(level);
    }

    public final boolean isSteadyRepeatLast() {
        return Constans.INSTANCE.getSteadyModeType() == 2;
    }
}
