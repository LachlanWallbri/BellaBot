package com.pudutech.peanut.robot_ui.manager;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.gson.Gson;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.lib_update.PuduSystemVersionManager;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.util.SystemProperty;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.peanut.presenter.utils.WifiUtil;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.RobotInfo;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent;
import io.reactivex.functions.Consumer;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SystemUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002-.B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J3\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152#\u0010\u0016\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00120\u0017J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0004J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0012H\u0016J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0012H\u0016J\u000e\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0004J\u0010\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u001cH\u0002J\u0006\u0010)\u001a\u00020\u0012J\b\u0010*\u001a\u00020\u0012H\u0002J\u0016\u0010+\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0004J\b\u0010,\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/SystemUpdateManager;", "Lcom/pudutech/lib_update/listener/IShowProgress;", "()V", "TAG", "", "gson", "Lcom/google/gson/Gson;", "handler", "Landroid/os/Handler;", "isRequestFromServer", "", "netWorkChangeReceiver", "Lcom/pudutech/peanut/robot_ui/manager/SystemUpdateManager$NetWorkChangeReceiver;", "retryCount", "", "checkAppSysVersion", "systemVersionCode", "checkFromServer", "", "checkLocalSystemFile", "context", "Landroid/content/Context;", "sysUpdateFilePathCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "path", "getUpdateCount", "Lcom/pudutech/peanut/robot_ui/manager/SystemUpdateManager$SystemUpdateCount;", "onFail", C3898x.f4338g, "", "onFinish", "onProgress", "downloadInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "onStartProgress", "requestNewUpdateFileIfNeed", "filePath", "saveUpdateCount", "countObj", "startCheckFromServer", "startNetReceiver", "startSystemUpdate", "stopNetReceiverIfNeed", "NetWorkChangeReceiver", "SystemUpdateCount", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SystemUpdateManager implements IShowProgress {
    private static final String TAG = "SystemUpdateManager";
    private static boolean isRequestFromServer;
    private static NetWorkChangeReceiver netWorkChangeReceiver;
    private static int retryCount;
    public static final SystemUpdateManager INSTANCE = new SystemUpdateManager();
    private static final Gson gson = new Gson();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public void onProgress(DownloadInfo downloadInfo) {
        Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
    }

    private SystemUpdateManager() {
    }

    public final void startCheckFromServer() {
        Pdlog.m3273d(TAG, "startCheckFromServer");
        if (RobotInfo.INSTANCE.isPeanutBot()) {
            if (checkAppSysVersion(Integer.parseInt(new SystemProperty(RobotContext.INSTANCE.getContext()).getProperty("ro.pudutech.version_code")))) {
                if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
                    checkFromServer();
                    return;
                } else {
                    startNetReceiver();
                    return;
                }
            }
            Pdlog.m3273d(TAG, "startCheckFromServer do not need update");
            return;
        }
        Pdlog.m3274e(TAG, "getRobotType wrong");
    }

    private final void startNetReceiver() {
        Pdlog.m3273d(TAG, "netWorkChangeReceiver");
        if (netWorkChangeReceiver != null) {
            netWorkChangeReceiver = new NetWorkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            try {
                RobotContext.INSTANCE.getContext().registerReceiver(netWorkChangeReceiver, intentFilter);
                return;
            } catch (Throwable th) {
                Pdlog.m3274e(TAG, Log.getStackTraceString(th));
                return;
            }
        }
        Pdlog.m3273d(TAG, "netWorkChangeReceiver is exist");
    }

    private final void stopNetReceiverIfNeed() {
        Pdlog.m3273d(TAG, "netWorkChangeReceiver " + netWorkChangeReceiver);
        NetWorkChangeReceiver netWorkChangeReceiver2 = netWorkChangeReceiver;
        if (netWorkChangeReceiver2 != null) {
            try {
                RobotContext.INSTANCE.getContext().unregisterReceiver(netWorkChangeReceiver2);
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "stopNetReceiverIfNeed");
                Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkFromServer() {
        SystemProperty systemProperty = new SystemProperty(RobotContext.INSTANCE.getContext());
        String property = systemProperty.getProperty("ro.pudutech.version_name");
        String property2 = systemProperty.getProperty("ro.pudutech.version_code");
        String property3 = systemProperty.getProperty("ro.build.id");
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        String str = mac;
        Pdlog.m3273d(TAG, "startCheck sysVersionName = " + property + " , sysVersionCode = " + property2 + " , mac = " + str);
        RobotInfo.UpdateInfo systemUpdateInfo = RobotInfo.INSTANCE.getSystemUpdateInfo();
        StringBuilder sb = new StringBuilder();
        sb.append("checkFromServer systemUpdateInfo = ");
        sb.append(systemUpdateInfo);
        Pdlog.m3273d(TAG, sb.toString());
        if (systemUpdateInfo == null) {
            Pdlog.m3274e(TAG, "systemUpdateInfo is null");
        } else {
            PuduSystemVersionManager.INSTANCE.checkSystemUpdate(str, property, property2, systemUpdateInfo.getName(), systemUpdateInfo.getVersionName(), systemUpdateInfo.getVerCode(), "9.1.2", property3, this);
        }
    }

    public final void checkLocalSystemFile(Context context, final Function1<? super String, Unit> sysUpdateFilePathCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(sysUpdateFilePathCallback, "sysUpdateFilePathCallback");
        try {
            String property = new SystemProperty(context).getProperty("ro.pudutech.version_code");
            String str = property;
            if (str == null || StringsKt.isBlank(str)) {
                Pdlog.m3273d(TAG, "checkLocalSystemFile sysVersionCode is empty");
                sysUpdateFilePathCallback.invoke("");
            } else if (checkAppSysVersion(Integer.parseInt(property))) {
                PuduSystemVersionManager.INSTANCE.checkLocalSystemFile(Integer.parseInt(property)).subscribe(new Consumer<String>() { // from class: com.pudutech.peanut.robot_ui.manager.SystemUpdateManager$checkLocalSystemFile$p$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(String str2) {
                        Pdlog.m3273d("SystemUpdateManager", "checkLocalAndUpdate = " + str2);
                        Function1.this.invoke(str2);
                    }
                }, new Consumer<Throwable>() { // from class: com.pudutech.peanut.robot_ui.manager.SystemUpdateManager$checkLocalSystemFile$p$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        Function1.this.invoke("");
                        Pdlog.m3274e("SystemUpdateManager", "checkLocalAndUpdate error");
                        Pdlog.m3274e("SystemUpdateManager", Log.getStackTraceString(th));
                    }
                });
            } else {
                Pdlog.m3273d(TAG, "do not need update");
                sysUpdateFilePathCallback.invoke("");
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "checkLocalSystemFile error");
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    private final boolean checkAppSysVersion(int systemVersionCode) {
        RobotInfo.UpdateInfo systemUpdateInfo = RobotInfo.INSTANCE.getSystemUpdateInfo();
        if (systemUpdateInfo == null) {
            return false;
        }
        int parseInt = Integer.parseInt(systemUpdateInfo.getVerCode());
        Pdlog.m3273d(TAG, "checkAppNeedSysVersion  systemVersionCode = " + systemVersionCode + " , appSysVersionCode = " + parseInt);
        return parseInt > systemVersionCode;
    }

    public final boolean startSystemUpdate(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Pdlog.m3273d(TAG, "startSystemUpdate");
        SystemUpdateCount updateCount = getUpdateCount(path);
        if (updateCount.getCount() >= 3) {
            Pdlog.m3274e(TAG, "startSystemUpdate , update more then 3 ??????");
            return false;
        }
        updateCount.setCount(updateCount.getCount() + 1);
        saveUpdateCount(updateCount);
        try {
            PuduSystemVersionManager.INSTANCE.installSystemUpdate(context, path);
            return true;
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(th));
            return false;
        }
    }

    public final SystemUpdateCount getUpdateCount(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_system_update_count", "");
        Pdlog.m3273d(TAG, "getUpdateCount " + path + " , last count = " + str);
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return new SystemUpdateCount(path, 0);
        }
        try {
            SystemUpdateCount countObj = (SystemUpdateCount) gson.fromJson(str, SystemUpdateCount.class);
            if (Intrinsics.areEqual(countObj.getPath(), path)) {
                Intrinsics.checkExpressionValueIsNotNull(countObj, "countObj");
                return countObj;
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "getUpdateCount error");
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
        return new SystemUpdateCount(path, 0);
    }

    public final void requestNewUpdateFileIfNeed(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
        String name = new File(filePath).getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "File(filePath).name");
        String versionCodeFormFileName = puduSystemVersionManager.getVersionCodeFormFileName(name);
        RobotInfo.UpdateInfo systemUpdateInfo = RobotInfo.INSTANCE.getSystemUpdateInfo();
        if (systemUpdateInfo != null) {
            Pdlog.m3273d(TAG, "requestNewUpdateFileIfNeed file code = " + versionCodeFormFileName + " , SYSTEM_VERSION_CODE = " + systemUpdateInfo.getVerCode());
            if (!Intrinsics.areEqual(systemUpdateInfo.getVerCode(), versionCodeFormFileName)) {
                startCheckFromServer();
            }
        }
    }

    private final void saveUpdateCount(SystemUpdateCount countObj) {
        String toJson = gson.toJson(countObj);
        Pdlog.m3273d(TAG, "saveUpdateCount " + toJson);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(toJson, "toJson");
        SpUtils.set(context, "key_system_update_count", toJson);
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFail(Throwable e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        Pdlog.m3274e(TAG, "SystemUpdateManager onFail");
        Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        int i = retryCount;
        if (i < 3) {
            retryCount = i + 1;
            handler.postDelayed(new Runnable() { // from class: com.pudutech.peanut.robot_ui.manager.SystemUpdateManager$onFail$1
                @Override // java.lang.Runnable
                public final void run() {
                    SystemUpdateManager.INSTANCE.checkFromServer();
                }
            }, SolicitService.CAMERA_OPEN_TIME_OUT);
        } else {
            isRequestFromServer = true;
            stopNetReceiverIfNeed();
        }
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFinish() {
        Pdlog.m3273d(TAG, "onFinish");
        isRequestFromServer = true;
        stopNetReceiverIfNeed();
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onStartProgress() {
        Pdlog.m3273d(TAG, "onStartProgress");
    }

    /* compiled from: SystemUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/SystemUpdateManager$NetWorkChangeReceiver;", "Lcom/pudutech/peanut/robot_ui/util/NetWorkChangeEvent;", "()V", "onNetworkChange", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        @Override // com.pudutech.peanut.robot_ui.util.NetWorkChangeEvent
        public void onNetworkChange() {
            if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
                Pdlog.m3273d(NetWorkChangeEvent.TAG, "NetWorkChangeReceiver onNetworkChange isConnected");
                SystemUpdateManager.INSTANCE.checkFromServer();
            } else {
                Pdlog.m3273d(NetWorkChangeEvent.TAG, "NetWorkChangeReceiver onNetworkChange disConnected");
            }
        }
    }

    /* compiled from: SystemUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/SystemUpdateManager$SystemUpdateCount;", "", "path", "", "count", "", "(Ljava/lang/String;I)V", "getCount", "()I", "setCount", "(I)V", "getPath", "()Ljava/lang/String;", "setPath", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class SystemUpdateCount {
        private int count;
        private String path;

        public static /* synthetic */ SystemUpdateCount copy$default(SystemUpdateCount systemUpdateCount, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = systemUpdateCount.path;
            }
            if ((i2 & 2) != 0) {
                i = systemUpdateCount.count;
            }
            return systemUpdateCount.copy(str, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* renamed from: component2, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        public final SystemUpdateCount copy(String path, int count) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return new SystemUpdateCount(path, count);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SystemUpdateCount)) {
                return false;
            }
            SystemUpdateCount systemUpdateCount = (SystemUpdateCount) other;
            return Intrinsics.areEqual(this.path, systemUpdateCount.path) && this.count == systemUpdateCount.count;
        }

        public int hashCode() {
            String str = this.path;
            return ((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.count);
        }

        public String toString() {
            return "SystemUpdateCount(path=" + this.path + ", count=" + this.count + ")";
        }

        public SystemUpdateCount(String path, int i) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            this.path = path;
            this.count = i;
        }

        public final int getCount() {
            return this.count;
        }

        public final String getPath() {
            return this.path;
        }

        public final void setCount(int i) {
            this.count = i;
        }

        public final void setPath(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.path = str;
        }
    }
}
