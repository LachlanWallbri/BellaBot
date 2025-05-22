package com.pudutech.bumblebee.robot_ui;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.presenter.BusinessContext;
import com.pudutech.bumblebee.presenter.net.IOTHubServerManager;
import com.pudutech.bumblebee.presenter.net.IotServerApiManager;
import com.pudutech.bumblebee.robot_ui.config.UrlManager;
import com.pudutech.bumblebee.robot_ui.manager.FaceSoundPlayManager;
import com.pudutech.bumblebee.robot_ui.manager.ShopIdManager;
import com.pudutech.bumblebee.robot_ui.manager.SystemSoundManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.track.TrackInit;
import com.pudutech.bumblebee.robot_ui.util.FaceAnimationUtil;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.ProcessUtils;
import com.pudutech.lib_update.AppUpdateContext;
import com.pudutech.location.AMapLocationManager;
import com.pudutech.location.AmapConfig;
import com.pudutech.location.net.NetWorkManager;
import com.pudutech.mpmodule.MusicPlayerApp;
import com.pudutech.voiceinteraction.component.utils.UrlOkManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotContext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0007J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0019\u001a\u00020\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/RobotContext;", "", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "locationManager", "Lcom/pudutech/location/AMapLocationManager;", "getLocationManager", "()Lcom/pudutech/location/AMapLocationManager;", "setLocationManager", "(Lcom/pudutech/location/AMapLocationManager;)V", "welcomeAcIsStart", "", "getWelcomeAcIsStart", "()Z", "setWelcomeAcIsStart", "(Z)V", "init", "", "ctx", "killMirSdk", "setCameraLEDIOLow", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RobotContext {
    public static final RobotContext INSTANCE = new RobotContext();
    public static Context context;
    private static AMapLocationManager locationManager;
    private static boolean welcomeAcIsStart;

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

    public final AMapLocationManager getLocationManager() {
        return locationManager;
    }

    public final void setLocationManager(AMapLocationManager aMapLocationManager) {
        locationManager = aMapLocationManager;
    }

    @JvmStatic
    public static final void init(Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Context applicationContext = ctx.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "ctx.applicationContext");
        context = applicationContext;
        if (ProcessUtils.isMainProcess(ctx)) {
            AmapConfig amapConfig = new AmapConfig();
            amapConfig.setOpenReport(false);
            amapConfig.setLocationHttpTimeout(90000L);
            Context context2 = context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            if (context2 != null) {
                locationManager = new AMapLocationManager((Application) context2, amapConfig);
                Pdlog.m3273d("RobotContext", "isMainProcess , init ... ");
                RobotContext robotContext = INSTANCE;
                Context context3 = context;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                robotContext.killMirSdk(context3);
                try {
                    Context context4 = context;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    AppUpdateContext.init(context4);
                    SystemSoundManager systemSoundManager = SystemSoundManager.INSTANCE;
                    Context context5 = context;
                    if (context5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    systemSoundManager.init(context5);
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
                    MusicPlayerApp.getInstance().setSharedParamsCallback(new MusicPlayerApp.ISharedParamsCallback() { // from class: com.pudutech.bumblebee.robot_ui.RobotContext$init$1
                        @Override // com.pudutech.mpmodule.MusicPlayerApp.ISharedParamsCallback
                        public final Context getContext() {
                            return RobotContext.INSTANCE.getContext();
                        }
                    });
                    Context context8 = context;
                    if (context8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    PlaySound.init(context8);
                    PlaySound.playEmptyVoice();
                    TtsVoiceWrapperPlayer.INSTANCE.init();
                    FaceAnimationUtil.INSTANCE.loadRobotAnimation();
                    IOTHubServerManager.INSTANCE.setTest(UrlManager.INSTANCE.isTest());
                    UrlOkManager.INSTANCE.setOkTestServer(UrlManager.INSTANCE.isTest());
                    IotServerApiManager.INSTANCE.setTest(UrlManager.INSTANCE.isTest());
                    NetWorkManager.INSTANCE.setTestServer(UrlManager.INSTANCE.isTest());
                    ShopIdManager shopIdManager = ShopIdManager.INSTANCE;
                    Context context9 = context;
                    if (context9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    shopIdManager.init(context9);
                    TrackInit trackInit = TrackInit.INSTANCE;
                    Context context10 = context;
                    if (context10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                    }
                    trackInit.init(context10);
                } catch (IOException unused) {
                    Pdlog.m3274e("RobotContext", "检查assets目录下是否存在证书");
                    return;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
            }
        }
        MusicPlayerApp.getInstance().setSharedParamsCallback(new MusicPlayerApp.ISharedParamsCallback() { // from class: com.pudutech.bumblebee.robot_ui.RobotContext$init$2
            @Override // com.pudutech.mpmodule.MusicPlayerApp.ISharedParamsCallback
            public final Context getContext() {
                return RobotContext.INSTANCE.getContext();
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

    public final void setCameraLEDIOLow() {
        if (!new File("/sys/class/gpio/gpio35").exists()) {
            Pair<Integer, String> execCommand = Tools.execCommand("chmod 777 /sys/class/gpio/export", true);
            Log.i("TEST", "chmod 777 " + ((Integer) execCommand.first) + ' ' + ((String) execCommand.second));
            FileWriter fileWriter = new FileWriter(new File("/sys/class/gpio/export"));
            fileWriter.write("35");
            fileWriter.close();
        }
        Tools.execCommand("chmod 777 /sys/class/gpio/gpio35/direction", true);
        FileWriter fileWriter2 = new FileWriter(new File("/sys/class/gpio/gpio35/direction"));
        fileWriter2.write("out");
        fileWriter2.close();
    }
}
