package com.pudutech.peanut.robot_ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.lib_update.AppUpdateContext;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.module_robot_selfcheck.ExtandsKt;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.peanut.presenter.BusinessContext;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.presenter.net.ServerApiManager;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bluetooth.base.AppInfo;
import com.pudutech.peanut.robot_ui.config.UrlManager;
import com.pudutech.peanut.robot_ui.extend.SupportedLocaleExtKt;
import com.pudutech.peanut.robot_ui.manager.AdVoicePlayManager;
import com.pudutech.peanut.robot_ui.manager.FaceSoundPlayManager;
import com.pudutech.peanut.robot_ui.manager.RobotPeripheralsManager;
import com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.SayHiAnimationActivity;
import com.pudutech.peanut.robot_ui.p063ui.wifi.WifiConnectActivity;
import com.pudutech.peanut.robot_ui.util.AppUtil;
import com.pudutech.peanut.robot_ui.util.ProcessUtils;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.SupportedLocale;
import com.pudutech.resources.resource.ResUtil;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.task.ReportAppUpgrade;
import com.pudutech.robot.module.voice.RobotVoicePlayer;
import com.pudutech.robot.module.voice.data.PlayEvent;
import com.pudutech.voiceinteraction.component.utils.UrlOkManager;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RobotContext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000G\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n*\u0005\r\u0010\u0013\u0016\u0019\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001cH\u0002J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0007H\u0007J\b\u0010&\u001a\u00020\"H\u0002J\b\u0010'\u001a\u00020\"H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\b\u0010)\u001a\u00020\"H\u0002J\b\u0010*\u001a\u00020\"H\u0002J\u000e\u0010+\u001a\u00020\"2\u0006\u0010\u0006\u001a\u00020\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006,"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/RobotContext;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "onPowerChangeListener", "com/pudutech/peanut/robot_ui/RobotContext$onPowerChangeListener$1", "Lcom/pudutech/peanut/robot_ui/RobotContext$onPowerChangeListener$1;", "onRobotMoveTargetChangeListener", "com/pudutech/peanut/robot_ui/RobotContext$onRobotMoveTargetChangeListener$1", "Lcom/pudutech/peanut/robot_ui/RobotContext$onRobotMoveTargetChangeListener$1;", "robotPoseListener", "com/pudutech/peanut/robot_ui/RobotContext$robotPoseListener$1", "Lcom/pudutech/peanut/robot_ui/RobotContext$robotPoseListener$1;", "robotSpeedListener", "com/pudutech/peanut/robot_ui/RobotContext$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/RobotContext$robotSpeedListener$1;", "switchMapResultListener", "com/pudutech/peanut/robot_ui/RobotContext$switchMapResultListener$1", "Lcom/pudutech/peanut/robot_ui/RobotContext$switchMapResultListener$1;", "welcomeAcIsStart", "", "getWelcomeAcIsStart", "()Z", "setWelcomeAcIsStart", "(Z)V", "enableCPU", "", "onOrOff", "init", "ctx", "initFreeInstallData", "initRemoteMaintenance", "initReport", "initTrackingReport", "initVoice", "killMirSdk", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotContext {
    public static Context context;
    private static boolean welcomeAcIsStart;
    public static final RobotContext INSTANCE = new RobotContext();
    private static final String TAG = RobotContext.class.getSimpleName();
    private static final RobotContext$onPowerChangeListener$1 onPowerChangeListener = new BatteryInfoManager.OnPowerChangeListener() { // from class: com.pudutech.peanut.robot_ui.RobotContext$onPowerChangeListener$1
        @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnPowerChangeListener
        public void onPowerChanger(int power) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setPower(power);
        }
    };
    private static final RobotContext$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.RobotContext$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setSpeed(left);
        }
    };
    private static final RobotContext$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.peanut.robot_ui.RobotContext$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(double x, double y, double yaw) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setCoordinateOrientation(x, y, yaw);
        }
    };
    private static final RobotContext$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.peanut.robot_ui.RobotContext$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setMapPath(RobotMapManager.INSTANCE.getCurrentMapAtlasPath());
        }
    };
    private static final RobotContext$onRobotMoveTargetChangeListener$1 onRobotMoveTargetChangeListener = new RobotMoveManager.OnRobotMoveTargetChangeListener() { // from class: com.pudutech.peanut.robot_ui.RobotContext$onRobotMoveTargetChangeListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.OnRobotMoveTargetChangeListener
        public void onMovingTarget(String i) {
            String str;
            Intrinsics.checkParameterIsNotNull(i, "i");
            RobotContext robotContext = RobotContext.INSTANCE;
            str = RobotContext.TAG;
            Pdlog.m3273d(str, "onMovingTarget " + i);
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setTask(i);
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PlayEvent.values().length];

        static {
            $EnumSwitchMapping$0[PlayEvent.Playing.ordinal()] = 1;
            $EnumSwitchMapping$0[PlayEvent.OnceFinish.ordinal()] = 2;
            $EnumSwitchMapping$0[PlayEvent.Stop.ordinal()] = 3;
        }
    }

    private RobotContext() {
    }

    public final Context getContext() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final boolean getWelcomeAcIsStart() {
        return welcomeAcIsStart;
    }

    public final void setWelcomeAcIsStart(boolean z) {
        welcomeAcIsStart = z;
    }

    @JvmStatic
    public static final void init(Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Context applicationContext = ctx.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "ctx.applicationContext");
        context = applicationContext;
        if (ProcessUtils.isMainProcess(ctx)) {
            Pdlog.m3273d("RobotContext", "isMainProcess , init ... ");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotContext$init$1(null), 3, null);
            RobotContext robotContext = INSTANCE;
            Context context2 = context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            robotContext.killMirSdk(context2);
            try {
                Context context3 = context;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                String appMetaData = AppUtil.getAppMetaData(context3, "Install_Channel");
                Context context4 = context;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                AppUpdateContext.init(context4, UrlManager.INSTANCE.getUpdateHost(), appMetaData);
                ResUtil resUtil = ResUtil.INSTANCE;
                Context context5 = context;
                if (context5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                resUtil.init(context5);
                UrlOkManager.INSTANCE.setOkTestServer(UrlManager.INSTANCE.getIS_AI_SERVER_TEST());
                FaceSoundPlayManager faceSoundPlayManager = FaceSoundPlayManager.INSTANCE;
                Context context6 = context;
                if (context6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                faceSoundPlayManager.setContext(context6);
                BusinessContext businessContext = BusinessContext.INSTANCE;
                Context context7 = context;
                if (context7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                businessContext.init(context7);
                NetWorkApiManager.INSTANCE.setTestServer(UrlManager.INSTANCE.isTest());
                BusinessSetting businessSetting = BusinessSetting.INSTANCE;
                Context context8 = context;
                if (context8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                businessSetting.initConfig(context8);
                ServerApiManager serverApiManager = ServerApiManager.INSTANCE;
                Context context9 = context;
                if (context9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                serverApiManager.init(context9);
                ServerApiManager.INSTANCE.setTestServer(UrlManager.INSTANCE.getIS_API_SEVER_TEST());
                INSTANCE.initReport();
                INSTANCE.initTrackingReport();
                RobotInitProcessor instance = RobotInitProcessor.INSTANCE.getINSTANCE();
                Context context10 = context;
                if (context10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                instance.init(context10);
                Context context11 = context;
                if (context11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                AppInfo.init(context11);
                RobotPeripheralsManager.INSTANCE.init();
                ExtandsKt.setSupportedLocaleList(SupportedLocaleExtKt.getList(SupportedLocale.INSTANCE));
                INSTANCE.initFreeInstallData();
            } catch (IOException unused) {
                Pdlog.m3274e("RobotContext", "检查assets目录下是否存在证书");
                return;
            }
        }
        AppContext.INSTANCE.setListener(new AppContext.StartActivityListener() { // from class: com.pudutech.peanut.robot_ui.RobotContext$init$2
            @Override // com.pudutech.freeinstall_ui.AppContext.StartActivityListener
            public void startWifiConnectActivity(Context context12) {
                Intrinsics.checkParameterIsNotNull(context12, "context");
                context12.startActivity(new Intent(context12, (Class<?>) WifiConnectActivity.class));
            }

            @Override // com.pudutech.freeinstall_ui.AppContext.StartActivityListener
            public void startDeliActivity(Context context12) {
                Intrinsics.checkParameterIsNotNull(context12, "context");
                context12.startActivity(new Intent(context12, (Class<?>) SayHiAnimationActivity.class));
            }

            @Override // com.pudutech.freeinstall_ui.AppContext.StartActivityListener
            public void startSayHiAnimationActivity(Context context12) {
                Intrinsics.checkParameterIsNotNull(context12, "context");
                context12.startActivity(new Intent(context12, (Class<?>) SayHiAnimationActivity.class));
            }

            @Override // com.pudutech.freeinstall_ui.AppContext.StartActivityListener
            public void startToChargingActivity(Context context12) {
                Intrinsics.checkParameterIsNotNull(context12, "context");
                Intent intent = new Intent(context12, (Class<?>) RobotChargingActivity.class);
                intent.putExtra(RobotChargingActivity.FROM_FREE_INSTALL, true);
                context12.startActivity(intent);
            }
        });
    }

    private final void initFreeInstallData() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotContext$initFreeInstallData$1(null), 3, null);
    }

    private final void initVoice() {
        RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Context context3 = context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        robotVoicePlayer.init(context2, new LanguageUtils(context3).getCurrent().getLocale());
        RobotVoicePlayer.INSTANCE.setGlobalPlayEventListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.peanut.robot_ui.RobotContext$initVoice$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent) {
                invoke2(playEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PlayEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                int i = RobotContext.WhenMappings.$EnumSwitchMapping$0[it.ordinal()];
                if (i == 1) {
                    AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
                    String simpleName = RobotContext.INSTANCE.getClass().getSimpleName();
                    Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
                    adVoicePlayManager.lowVolumeMode(simpleName);
                    return;
                }
                if (i == 2 || i == 3) {
                    AdVoicePlayManager adVoicePlayManager2 = AdVoicePlayManager.INSTANCE;
                    String simpleName2 = RobotContext.INSTANCE.getClass().getSimpleName();
                    Intrinsics.checkExpressionValueIsNotNull(simpleName2, "javaClass.simpleName");
                    adVoicePlayManager2.normalVolumeMode(simpleName2);
                }
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotContext$initVoice$2(null), 3, null);
    }

    private final void initReport() {
        PuduReportManager puduReportManager = PuduReportManager.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        puduReportManager.init(context2, UrlManager.INSTANCE.getIS_REPORT_SERVER_TEST());
        PuduReportManager.INSTANCE.setReportDateProvide(new PuduReportManager.IReportDataProvide() { // from class: com.pudutech.peanut.robot_ui.RobotContext$initReport$1
            @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
            public String getMac() {
                String mac = WifiUtil.INSTANCE.getMac();
                return mac != null ? mac : "";
            }

            @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
            public String getHardVersion() {
                return RobotConfig.INSTANCE.getHardwareVersion();
            }

            @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
            public double getPowerPercent() {
                if (BatteryInfoManager.INSTANCE.getPower() != null) {
                    return r0.intValue();
                }
                return 0.0d;
            }

            @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
            public boolean isCharging() {
                return BatteryInfoManager.INSTANCE.isCharging();
            }

            @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
            public boolean isWorking() {
                return RobotMoveManager.INSTANCE.isWorking();
            }
        });
        new ReportAppUpgrade().report();
    }

    private final void initTrackingReport() {
        TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        trackingReportManager.init(context2, UrlManager.INSTANCE.getIS_REPORT_SERVER_TEST());
        TrackingReportManager.INSTANCE.setReportDateProvide(new TrackingReportManager.IReportDataProvide() { // from class: com.pudutech.peanut.robot_ui.RobotContext$initTrackingReport$1
            @Override // com.pudutech.robot.module.report.TrackingReportManager.IReportDataProvide
            public String getMac() {
                String mac = WifiUtil.INSTANCE.getMac();
                return mac != null ? mac : "";
            }

            @Override // com.pudutech.robot.module.report.TrackingReportManager.IReportDataProvide
            public String getHardVersion() {
                return RobotConfig.INSTANCE.getHardwareVersion();
            }

            @Override // com.pudutech.robot.module.report.TrackingReportManager.IReportDataProvide
            public double getPowerPercent() {
                if (BatteryInfoManager.INSTANCE.getPower() != null) {
                    return r0.intValue();
                }
                return 0.0d;
            }

            @Override // com.pudutech.robot.module.report.TrackingReportManager.IReportDataProvide
            public boolean isCharging() {
                return BatteryInfoManager.INSTANCE.isCharging();
            }
        });
    }

    public final void killMirSdk(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Object systemService = context2.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            Pdlog.m3273d("MyBaseActivity", "runningAppProcessInfo = " + runningAppProcessInfo.processName);
            if (Intrinsics.areEqual("com.pudutech.mirsdk", runningAppProcessInfo.processName)) {
                Pdlog.m3273d("MyBaseActivity", "kill = " + runningAppProcessInfo.processName + " ; pid = " + runningAppProcessInfo.pid);
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    private final void initRemoteMaintenance() {
        if (Intrinsics.areEqual("peanut", "mock")) {
            return;
        }
        RemoteMaintenanceManager.INSTANCE.start();
        BatteryInfoManager.INSTANCE.addPowerChangeListener(onPowerChangeListener);
        RobotMoveManager.INSTANCE.addRobotSpeedListener(robotSpeedListener);
        RobotMoveManager.INSTANCE.addRobotPoseListener(robotPoseListener);
        RobotMapManager.INSTANCE.addSwitchMapResultListener(switchMapResultListener);
        RobotMoveManager.INSTANCE.addOnRobotMoveTargetChangeListener(onRobotMoveTargetChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableCPU(boolean onOrOff) {
        Pdlog.m3273d(TAG, "enableCPU onOrOff=" + onOrOff);
        try {
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu5/online", true);
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu4/online", true);
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu3/online", true);
        } catch (Exception e) {
            Pdlog.m3277w(TAG, "check your device support enableCPU or not. " + e);
        }
    }
}
