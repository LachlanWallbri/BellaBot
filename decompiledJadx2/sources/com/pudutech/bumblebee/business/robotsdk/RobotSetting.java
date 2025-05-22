package com.pudutech.bumblebee.business.robotsdk;

import android.content.Context;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RobotSetting.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R(\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR(\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R(\u0010\"\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R&\u0010%\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u000b\"\u0004\b'\u0010\rR$\u0010)\u001a\u00020(2\u0006\u0010\b\u001a\u00020(8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\u00020(2\u0006\u0010\b\u001a\u00020(8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R(\u00101\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010\u0010\"\u0004\b3\u0010\u0012R0\u00106\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001052\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001058F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u00108¨\u00069"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/RobotSetting;", "", "()V", "KEY_AUTO_SLEEP_DELAY", "", "KEY_PALLET_CHECK_EMPTY_DELAY", "TIME_INFINITE", "", ES6Iterator.VALUE_PROPERTY, "autoSleepDelay", "getAutoSleepDelay", "()J", "setAutoSleepDelay", "(J)V", "cruiseSpeedLevel", "getCruiseSpeedLevel", "()Ljava/lang/String;", "setCruiseSpeedLevel", "(Ljava/lang/String;)V", "deliverSpeedLevel", "getDeliverSpeedLevel", "setDeliverSpeedLevel", "", "enableBlockReplan", "getEnableBlockReplan", "()Z", "setEnableBlockReplan", "(Z)V", "faceDetectorSwitch", "getFaceDetectorSwitch", "setFaceDetectorSwitch", "goHomeSpeedLevel", "getGoHomeSpeedLevel", "setGoHomeSpeedLevel", "greeterSpeedLevel", "getGreeterSpeedLevel", "setGreeterSpeedLevel", "palletCheckEmptyDelay", "getPalletCheckEmptyDelay", "setPalletCheckEmptyDelay", "", RobotConfig.KEY_PATH_LOCKED_TIME, "getPathLockedTime", "()I", "setPathLockedTime", "(I)V", RobotConfig.KEY_REPLAN_TIME, "getRePlanWaitTime", "setRePlanWaitTime", "recyclingSpeedLevel", "getRecyclingSpeedLevel", "setRecyclingSpeedLevel", "<set-?>", "", "speedLevels", "getSpeedLevels", "()Ljava/util/List;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotSetting {
    public static final String KEY_AUTO_SLEEP_DELAY = "auto_sleep_delay";
    public static final String KEY_PALLET_CHECK_EMPTY_DELAY = "pallet_check_empty_delay";
    public static final long TIME_INFINITE = -1;
    private static boolean faceDetectorSwitch;
    private static List<String> speedLevels;
    public static final RobotSetting INSTANCE = new RobotSetting();
    private static long palletCheckEmptyDelay = 5000;
    private static long autoSleepDelay = 300000;

    private RobotSetting() {
    }

    public final void setCruiseSpeedLevel(String str) {
        if (str != null) {
            RobotConfig.INSTANCE.setCruiseSpeedLevel(str);
        }
    }

    public final String getCruiseSpeedLevel() {
        return RobotConfig.INSTANCE.getCruiseSpeedLevel();
    }

    public final void setDeliverSpeedLevel(String str) {
        if (str != null) {
            RobotConfig.INSTANCE.setDeliverSpeedLevel(str);
        }
    }

    public final String getDeliverSpeedLevel() {
        return RobotConfig.INSTANCE.getDeliverSpeedLevel();
    }

    public final void setGoHomeSpeedLevel(String str) {
        if (str != null) {
            RobotConfig.INSTANCE.setGoHomeSpeedLevel(str);
        }
    }

    public final String getGoHomeSpeedLevel() {
        return RobotConfig.INSTANCE.getGoHomeSpeedLevel();
    }

    public final void setGreeterSpeedLevel(String str) {
        if (str != null) {
            RobotConfig.INSTANCE.setDeliverSpeedLevel(str);
        }
    }

    public final String getGreeterSpeedLevel() {
        return RobotConfig.INSTANCE.getDeliverSpeedLevel();
    }

    public final void setRecyclingSpeedLevel(String str) {
        if (str != null) {
            RobotConfig.INSTANCE.setDeliverSpeedLevel(str);
        }
    }

    public final String getRecyclingSpeedLevel() {
        return RobotConfig.INSTANCE.getDeliverSpeedLevel();
    }

    public final List<String> getSpeedLevels() {
        ArrayList<String> speedLevels2 = RobotConfig.INSTANCE.getSpeedLevels();
        ArrayList arrayList = new ArrayList();
        for (Object obj : speedLevels2) {
            float parseFloat = Float.parseFloat((String) obj);
            if (parseFloat >= 0.2f && parseFloat <= 1.2f) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final boolean getFaceDetectorSwitch() {
        return faceDetectorSwitch;
    }

    public final void setFaceDetectorSwitch(boolean z) {
        RobotConfig.INSTANCE.setFaceDetectorSwitch(z);
        faceDetectorSwitch = z;
    }

    public final long getPalletCheckEmptyDelay() {
        return SpUtils.get((Context) BaseApplication.INSTANCE.getInstance(), KEY_PALLET_CHECK_EMPTY_DELAY, 5000L);
    }

    public final void setPalletCheckEmptyDelay(long j) {
        palletCheckEmptyDelay = j;
        SpUtils.set(BaseApplication.INSTANCE.getInstance(), KEY_PALLET_CHECK_EMPTY_DELAY, j);
    }

    public final long getAutoSleepDelay() {
        return SpUtils.get((Context) BaseApplication.INSTANCE.getInstance(), KEY_AUTO_SLEEP_DELAY, 300000L);
    }

    public final void setAutoSleepDelay(long j) {
        autoSleepDelay = j;
        SpUtils.set(BaseApplication.INSTANCE.getInstance(), KEY_AUTO_SLEEP_DELAY, j);
    }

    public final void setEnableBlockReplan(boolean z) {
        RobotConfig.INSTANCE.setEnableBlockReplan(z);
    }

    public final boolean getEnableBlockReplan() {
        return RobotConfig.INSTANCE.getEnableBlockReplan();
    }

    public final void setRePlanWaitTime(int i) {
        RobotConfig.INSTANCE.setRePlanWaitTime(i);
    }

    public final int getRePlanWaitTime() {
        return RobotConfig.INSTANCE.getRePlanWaitTime();
    }

    public final void setPathLockedTime(int i) {
        RobotConfig.INSTANCE.setPathLockedTime(i);
    }

    public final int getPathLockedTime() {
        return RobotConfig.INSTANCE.getPathLockedTime();
    }
}
