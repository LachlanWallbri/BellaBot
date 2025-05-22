package com.pudutech.mirsdkwrap.lib.robot;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.robot.peripherals.BuildConfig;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010F\u001a\u00020\n2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0010H\u0002J\u0017\u0010J\u001a\u00020K2\b\u00101\u001a\u0004\u0018\u000102H\u0000¢\u0006\u0002\bLJ\u0006\u0010M\u001a\u00020KJ\u0006\u0010N\u001a\u00020KJ\u000e\u0010O\u001a\u00020\n2\u0006\u0010P\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR&\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R&\u0010\u0016\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR&\u0010\u001b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR&\u0010\u001e\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R$\u0010!\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R&\u0010$\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR \u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R(\u0010*\u001a\u0004\u0018\u00010\u00102\b\u0010\t\u001a\u0004\u0018\u00010\u00108F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u0010/\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b/\u0010\u0013\"\u0004\b0\u0010\u0015R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R&\u00103\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\r\"\u0004\b5\u0010\u000fR&\u00106\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\r\"\u0004\b8\u0010\u000fR&\u0010:\u001a\u0002092\u0006\u0010\t\u001a\u0002098F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R \u0010?\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0018R@\u0010C\u001a\u0012\u0012\u0004\u0012\u00020\u00040Aj\b\u0012\u0004\u0012\u00020\u0004`B2\u0016\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00040Aj\b\u0012\u0004\u0012\u00020\u0004`B8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010E¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/RobotConfig;", "", "()V", "KEY_BUMPER_STATE", "", "KEY_ENABLE_BLOCK_REPLAN", "KEY_PATH_LOCKED_TIME", "KEY_REPLAN_TIME", "TAG", ES6Iterator.VALUE_PROPERTY, "", "aroundLevel", "getAroundLevel", "()I", "setAroundLevel", "(I)V", "", "coverAround", "getCoverAround", "()Z", "setCoverAround", "(Z)V", "cruiseSpeedLevel", "getCruiseSpeedLevel", "()Ljava/lang/String;", "setCruiseSpeedLevel", "(Ljava/lang/String;)V", "deliverSpeedLevel", "getDeliverSpeedLevel", "setDeliverSpeedLevel", "enableBlockReplan", "getEnableBlockReplan", "setEnableBlockReplan", "faceDetectorSwitch", "getFaceDetectorSwitch", "setFaceDetectorSwitch", "goHomeSpeedLevel", "getGoHomeSpeedLevel", "setGoHomeSpeedLevel", "<set-?>", "hardwareVersion", "getHardwareVersion", "installMode", "getInstallMode", "()Ljava/lang/Boolean;", "setInstallMode", "(Ljava/lang/Boolean;)V", "isOpenBumper", "setOpenBumper", "mirSdk", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", RobotConfig.KEY_PATH_LOCKED_TIME, "getPathLockedTime", "setPathLockedTime", RobotConfig.KEY_REPLAN_TIME, "getRePlanWaitTime", "setRePlanWaitTime", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "smoothRunAndStopMode", "getSmoothRunAndStopMode", "()Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "setSmoothRunAndStopMode", "(Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;)V", "softVersion", "getSoftVersion", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "speedLevels", "getSpeedLevels", "()Ljava/util/ArrayList;", "controlWheelErrorEvent", "var1", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "var2", "init", "", "init$module_robot_mirsdk_wrapper_release", "initBlockRoadSetting", "initBumperState", "setBumperState", "isUploadError", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotConfig {
    public static final String KEY_BUMPER_STATE = "bumperState";
    public static final String KEY_ENABLE_BLOCK_REPLAN = "blockEnableReplan";
    public static final String KEY_PATH_LOCKED_TIME = "pathLockedTime";
    public static final String KEY_REPLAN_TIME = "rePlanWaitTime";
    private static int aroundLevel;
    private static boolean coverAround;
    private static boolean enableBlockReplan;
    private static boolean faceDetectorSwitch;
    private static SDKInterface mirSdk;
    public static final RobotConfig INSTANCE = new RobotConfig();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String softVersion = "";
    private static String hardwareVersion = "";
    private static String cruiseSpeedLevel = Constans.KEY_DEFAULT_SPEED_CONFIG;
    private static String deliverSpeedLevel = Constans.KEY_DEFAULT_SPEED_CONFIG;
    private static String goHomeSpeedLevel = Constans.KEY_DEFAULT_SPEED_CONFIG;
    private static ArrayList<String> speedLevels = CollectionsKt.arrayListOf(Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
    private static SmoothMode smoothRunAndStopMode = SmoothMode.NoSmooth;
    private static int rePlanWaitTime = 30;
    private static int pathLockedTime = 180;

    private RobotConfig() {
    }

    public final void init$module_robot_mirsdk_wrapper_release(SDKInterface mirSdk2) {
        Pdlog.m3273d(TAG, "init : mirSdk = " + mirSdk2 + "; ");
        mirSdk = mirSdk2;
    }

    public final String getSoftVersion() {
        return PackageUtil.INSTANCE.getSoftVersion();
    }

    public final String getHardwareVersion() {
        String str;
        try {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface == null || (str = sDKInterface.getHardwareVersion()) == null) {
                str = "";
            }
            Pdlog.m3273d(TAG, "getHardwareVersion=" + str);
            hardwareVersion = str;
            return str;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "hardwareVersion : " + Log.getStackTraceString(e));
            return hardwareVersion;
        }
    }

    public final void setCruiseSpeedLevel(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotConfig$cruiseSpeedLevel$1$1(value, null), 3, null);
        cruiseSpeedLevel = value;
        Pdlog.m3273d(TAG, "setCruiseSpeedLevel=" + value);
    }

    public final String getCruiseSpeedLevel() {
        String str;
        MoveActionInterface moveActionInterface;
        try {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null || (str = moveActionInterface.getSpeedLevel(MoveMode.Cruise)) == null) {
                str = "0";
            }
            Pdlog.m3273d(TAG, "getCruiseSpeedLevel=" + str);
            cruiseSpeedLevel = str;
            return str;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "cruiseSpeedLevel : " + Log.getStackTraceString(e));
            return cruiseSpeedLevel;
        }
    }

    public final void setDeliverSpeedLevel(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotConfig$deliverSpeedLevel$1$1(value, null), 3, null);
        deliverSpeedLevel = value;
        Pdlog.m3273d(TAG, "setDeliverSpeedLevel=" + value);
    }

    public final String getDeliverSpeedLevel() {
        String str;
        MoveActionInterface moveActionInterface;
        try {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null || (str = moveActionInterface.getSpeedLevel(MoveMode.Direct)) == null) {
                str = "0";
            }
            Pdlog.m3273d(TAG, "getDeliverSpeedLevel=" + str);
            deliverSpeedLevel = str;
            return str;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "deliverSpeedLevel : " + Log.getStackTraceString(e));
            return deliverSpeedLevel;
        }
    }

    public final void setGoHomeSpeedLevel(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotConfig$goHomeSpeedLevel$1$1(value, null), 3, null);
        goHomeSpeedLevel = value;
        Pdlog.m3273d(TAG, "setGoHomeSpeedLevel=" + value);
    }

    public final String getGoHomeSpeedLevel() {
        String str;
        MoveActionInterface moveActionInterface;
        try {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null || (str = moveActionInterface.getSpeedLevel(MoveMode.GoHome)) == null) {
                str = "0";
            }
            Pdlog.m3273d(TAG, "getGoHomeSpeedLevel=" + str);
            goHomeSpeedLevel = str;
            return str;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "goHomeSpeedLevel : " + Log.getStackTraceString(e));
            return goHomeSpeedLevel;
        }
    }

    public final ArrayList<String> getSpeedLevels() {
        MoveActionInterface moveActionInterface;
        try {
            SDKInterface sDKInterface = mirSdk;
            String[] speedLevels2 = (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) ? null : moveActionInterface.getSpeedLevels();
            Pdlog.m3273d(TAG, "setSpeedLevels=" + speedLevels2);
            if (speedLevels2 == null) {
                return speedLevels;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            CollectionsKt.addAll(arrayList, speedLevels2);
            return arrayList;
        } catch (Exception unused) {
            return speedLevels;
        }
    }

    private final void setInstallMode(Boolean bool) {
        if (bool != null) {
            bool.booleanValue();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotConfig$installMode$$inlined$let$lambda$1(null, bool), 3, null);
        }
    }

    public final Boolean getInstallMode() {
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            return Boolean.valueOf(sDKInterface.getInstallMode());
        }
        return null;
    }

    public final void setCoverAround(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotConfig$coverAround$1(z, null), 3, null);
        coverAround = z;
    }

    public final boolean getCoverAround() {
        MoveActionInterface moveActionInterface;
        try {
            SDKInterface sDKInterface = mirSdk;
            boolean coverAround2 = (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) ? false : moveActionInterface.getCoverAround();
            coverAround = coverAround2;
            return coverAround2;
        } catch (Exception unused) {
            return coverAround;
        }
    }

    public final boolean getFaceDetectorSwitch() {
        return faceDetectorSwitch;
    }

    public final void setFaceDetectorSwitch(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotConfig$faceDetectorSwitch$1(z, null), 3, null);
        faceDetectorSwitch = z;
        Pdlog.m3273d(TAG, "setFaceDetectorSwitch=" + z);
    }

    public final void setSmoothRunAndStopMode(SmoothMode value) {
        MoveActionInterface moveActionInterface;
        Intrinsics.checkParameterIsNotNull(value, "value");
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
            moveActionInterface.setSmoothRunAndStopMode(value);
        }
        smoothRunAndStopMode = value;
        Pdlog.m3273d(TAG, "setSmoothRunAndStopMode=" + value);
    }

    public final SmoothMode getSmoothRunAndStopMode() {
        SmoothMode smoothMode;
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null || (smoothMode = moveActionInterface.getSmoothRunAndStopMode()) == null) {
            smoothMode = SmoothMode.NoSmooth;
        }
        Pdlog.m3273d(TAG, "getSmoothRunAndStopMode=" + smoothMode);
        return smoothMode;
    }

    public final void setOpenBumper(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_BUMPER_STATE, Boolean.valueOf(z));
    }

    public final boolean isOpenBumper() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_BUMPER_STATE, false);
    }

    private final int controlWheelErrorEvent(WheelError var1, boolean var2) {
        int controlWheelErrorEvent = MirSdkManager.INSTANCE.controlWheelErrorEvent(var1, var2);
        Pdlog.m3273d(TAG, "controlWheelErrorEvent()##result:" + controlWheelErrorEvent);
        return controlWheelErrorEvent;
    }

    public final void initBumperState() {
        try {
            boolean z = !isOpenBumper();
            int controlWheelErrorEvent = controlWheelErrorEvent(WheelError.BumpSwitchReset, z);
            Pdlog.m3273d(TAG, "initBumperState() openBumper：" + z + "###eventResult：" + controlWheelErrorEvent);
        } catch (Exception e) {
            Pdlog.m3273d(TAG, e.toString());
        }
    }

    public final int setBumperState(boolean isUploadError) {
        return controlWheelErrorEvent(WheelError.BumpSwitchReset, isUploadError);
    }

    public final int getAroundLevel() {
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = mirSdk;
        Double valueOf = (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) ? null : Double.valueOf(moveActionInterface.getTrayDis());
        Pdlog.m3273d(TAG, "aroundLevel trayDis: " + valueOf);
        if (Intrinsics.areEqual(valueOf, 0.05d)) {
            return 1;
        }
        return Intrinsics.areEqual(valueOf, 0.1d) ? 2 : 0;
    }

    public final void setAroundLevel(int i) {
        MoveActionInterface moveActionInterface;
        MoveActionInterface moveActionInterface2;
        MoveActionInterface moveActionInterface3;
        Pdlog.m3273d(TAG, "aroundLevel set: " + i);
        aroundLevel = i;
        if (i == 1) {
            SDKInterface sDKInterface = mirSdk;
            if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
                return;
            }
            moveActionInterface.updateTrayDis(0.05d);
            return;
        }
        if (i == 2) {
            SDKInterface sDKInterface2 = mirSdk;
            if (sDKInterface2 == null || (moveActionInterface2 = sDKInterface2.getMoveActionInterface()) == null) {
                return;
            }
            moveActionInterface2.updateTrayDis(0.1d);
            return;
        }
        SDKInterface sDKInterface3 = mirSdk;
        if (sDKInterface3 == null || (moveActionInterface3 = sDKInterface3.getMoveActionInterface()) == null) {
            return;
        }
        moveActionInterface3.updateTrayDis(0.0d);
    }

    public final void initBlockRoadSetting() {
        RobotMoveManager.INSTANCE.enableReplan(getEnableBlockReplan());
        RobotMoveManager.INSTANCE.setReplanWaitTime(getRePlanWaitTime());
        RobotMoveManager.INSTANCE.setRoadBlockTime(getPathLockedTime());
    }

    public final void setEnableBlockReplan(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_ENABLE_BLOCK_REPLAN, Boolean.valueOf(z));
        Pdlog.m3273d(TAG, "set blockEnableReplan=" + z);
        RobotMoveManager.INSTANCE.enableReplan(z);
        enableBlockReplan = z;
    }

    public final boolean getEnableBlockReplan() {
        boolean z = MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_ENABLE_BLOCK_REPLAN, false);
        Pdlog.m3273d(TAG, "is blockEnableReplan=" + z);
        return z;
    }

    public final void setRePlanWaitTime(int i) {
        if (rePlanWaitTime != i) {
            MMKVManager.INSTANCE.getINSTANCE().set(KEY_REPLAN_TIME, Integer.valueOf(i));
            Pdlog.m3273d(TAG, "setRePlanWaitTime=" + i);
            rePlanWaitTime = i;
        }
        RobotMoveManager.INSTANCE.setReplanWaitTime(rePlanWaitTime);
    }

    public final int getRePlanWaitTime() {
        int i = MMKVManager.INSTANCE.getINSTANCE().getInt(KEY_REPLAN_TIME, 30);
        Pdlog.m3273d(TAG, "getRePlanWaitTime=" + i);
        return i;
    }

    public final void setPathLockedTime(int i) {
        if (pathLockedTime != i) {
            MMKVManager.INSTANCE.getINSTANCE().set(KEY_PATH_LOCKED_TIME, Integer.valueOf(i));
            Pdlog.m3273d(TAG, "setPathLockedTime=" + i);
            pathLockedTime = i;
        }
        RobotMoveManager.INSTANCE.setRoadBlockTime(pathLockedTime);
    }

    public final int getPathLockedTime() {
        int i = MMKVManager.INSTANCE.getINSTANCE().getInt(KEY_PATH_LOCKED_TIME, 180);
        Pdlog.m3273d(TAG, "getPathLockedTime=" + i);
        return i;
    }
}
