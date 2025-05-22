package com.pudutech.robot.update;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.gson.Gson;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.network.manager.NetWorkChangeEvent;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.lib_update.PuduSystemVersionManager;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.util.SystemProperty;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.mirsdk.SolicitService;
import io.reactivex.functions.Consumer;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SystemUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003DEFB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u000fH\u0002J\b\u0010*\u001a\u00020\u0015H\u0002J3\u0010+\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062#\u0010,\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00150-J\u000e\u0010/\u001a\u0002002\u0006\u0010.\u001a\u00020\u0004J\u0016\u00101\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00102\u001a\u00020#J\u0010\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u00020\u0015H\u0016J\u0010\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u0013H\u0016J\b\u00109\u001a\u00020\u0015H\u0016J\u0006\u0010:\u001a\u00020\u0015J\u000e\u0010;\u001a\u00020\u00152\u0006\u0010<\u001a\u00020\u0004J\u0010\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u000200H\u0002J@\u0010?\u001a\u00020\u001528\u0010\r\u001a4\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u000eJ\u0006\u0010@\u001a\u00020\u0015J\b\u0010A\u001a\u00020\u0015H\u0002J\u0016\u0010B\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0004J\b\u0010C\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000RN\u0010\r\u001a6\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006G"}, m3961d2 = {"Lcom/pudutech/robot/update/SystemUpdateManager;", "Lcom/pudutech/lib_update/listener/IShowProgress;", "()V", "TAG", "", "context", "Landroid/content/Context;", "gson", "Lcom/google/gson/Gson;", "handler", "Landroid/os/Handler;", "isRequestFromServer", "", "mSystemUpdateChangeListener", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "state", "Lcom/pudutech/light_network/download/DownloadInfo;", "mDownloadInfo", "", "getMSystemUpdateChangeListener", "()Lkotlin/jvm/functions/Function2;", "setMSystemUpdateChangeListener", "(Lkotlin/jvm/functions/Function2;)V", "mandatoryUpdate", "getMandatoryUpdate", "()Z", "setMandatoryUpdate", "(Z)V", "netWorkChangeReceiver", "Lcom/pudutech/robot/update/SystemUpdateManager$NetWorkChangeReceiver;", "retryCount", "systemUpdate", "Lcom/pudutech/robot/update/SystemUpdateManager$SystemUpdate;", "getSystemUpdate", "()Lcom/pudutech/robot/update/SystemUpdateManager$SystemUpdate;", "setSystemUpdate", "(Lcom/pudutech/robot/update/SystemUpdateManager$SystemUpdate;)V", "checkAppSysVersion", "systemVersionCode", "checkFromServer", "checkLocalSystemFile", "sysUpdateFilePathCallback", "Lkotlin/Function1;", "path", "getUpdateCount", "Lcom/pudutech/robot/update/SystemUpdateManager$SystemUpdateCount;", "init", "systemName", "onFail", C3898x.f4338g, "", "onFinish", "onProgress", "downloadInfo", "onStartProgress", "release", "requestNewUpdateFileIfNeed", "filePath", "saveUpdateCount", "countObj", "setSystemCallBack", "startCheckFromServer", "startNetReceiver", "startSystemUpdate", "stopNetReceiverIfNeed", "NetWorkChangeReceiver", "SystemUpdate", "SystemUpdateCount", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SystemUpdateManager implements IShowProgress {
    private static final String TAG = "SystemUpdateManager";
    private static Context context;
    private static boolean isRequestFromServer;
    private static Function2<? super Integer, ? super DownloadInfo, Unit> mSystemUpdateChangeListener;
    private static boolean mandatoryUpdate;
    private static NetWorkChangeReceiver netWorkChangeReceiver;
    private static int retryCount;
    private static SystemUpdate systemUpdate;
    public static final SystemUpdateManager INSTANCE = new SystemUpdateManager();
    private static final Gson gson = new Gson();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private SystemUpdateManager() {
    }

    public static final /* synthetic */ Context access$getContext$p(SystemUpdateManager systemUpdateManager) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final Function2<Integer, DownloadInfo, Unit> getMSystemUpdateChangeListener() {
        return mSystemUpdateChangeListener;
    }

    public final void setMSystemUpdateChangeListener(Function2<? super Integer, ? super DownloadInfo, Unit> function2) {
        mSystemUpdateChangeListener = function2;
    }

    public final boolean getMandatoryUpdate() {
        return mandatoryUpdate;
    }

    public final void setMandatoryUpdate(boolean z) {
        mandatoryUpdate = z;
    }

    public final SystemUpdate getSystemUpdate() {
        return systemUpdate;
    }

    public final void setSystemUpdate(SystemUpdate systemUpdate2) {
        systemUpdate = systemUpdate2;
    }

    public final void init(Context context2, SystemUpdate systemName) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(systemName, "systemName");
        systemUpdate = systemName;
        context = context2;
        mandatoryUpdate = systemName.getMandatoryUpdate();
    }

    public final void startCheckFromServer() {
        Pdlog.m3273d(TAG, "startCheckFromServer");
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String property = new SystemProperty(context2).getProperty("ro.pudutech.version_code");
        if (property.length() == 0) {
            return;
        }
        if (checkAppSysVersion(Integer.parseInt(property))) {
            Context context3 = context;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            if (NetStatusUtil.isConnected(context3)) {
                checkFromServer();
                return;
            } else {
                startNetReceiver();
                return;
            }
        }
        Pdlog.m3273d(TAG, "startCheckFromServer do not need update");
    }

    private final void startNetReceiver() {
        Pdlog.m3273d(TAG, "netWorkChangeReceiver");
        if (netWorkChangeReceiver != null) {
            netWorkChangeReceiver = new NetWorkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            try {
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                context2.registerReceiver(netWorkChangeReceiver, intentFilter);
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
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                context2.unregisterReceiver(netWorkChangeReceiver2);
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "stopNetReceiverIfNeed");
                Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkFromServer() {
        String appVersionCode;
        String appVersionName;
        String productName;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        SystemProperty systemProperty = new SystemProperty(context2);
        String property = systemProperty.getProperty("ro.pudutech.version_name");
        String property2 = systemProperty.getProperty("ro.pudutech.version_code");
        String property3 = systemProperty.getProperty("ro.build.id");
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        Pdlog.m3273d(TAG, "startCheck sysVersionName = " + property + " , sysVersionCode = " + property2 + " , mac = " + mac + " buildId=" + property3);
        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
        String mac2 = WifiUtil.INSTANCE.getMac();
        String str = mac2 != null ? mac2 : "";
        SystemUpdate systemUpdate2 = systemUpdate;
        String str2 = (systemUpdate2 == null || (productName = systemUpdate2.getProductName()) == null) ? "" : productName;
        SystemUpdate systemUpdate3 = systemUpdate;
        String str3 = (systemUpdate3 == null || (appVersionName = systemUpdate3.getAppVersionName()) == null) ? "" : appVersionName;
        SystemUpdate systemUpdate4 = systemUpdate;
        puduSystemVersionManager.checkSystemUpdate(str, property, property2, str2, str3, (systemUpdate4 == null || (appVersionCode = systemUpdate4.getAppVersionCode()) == null) ? "" : appVersionCode, "1.0", property3, this);
    }

    public final void checkLocalSystemFile(Context context2, final Function1<? super String, Unit> sysUpdateFilePathCallback) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(sysUpdateFilePathCallback, "sysUpdateFilePathCallback");
        try {
            String property = new SystemProperty(context2).getProperty("ro.pudutech.version_code");
            String str = property;
            if (str == null || StringsKt.isBlank(str)) {
                Pdlog.m3273d(TAG, "checkLocalSystemFile sysVersionCode is empty");
                sysUpdateFilePathCallback.invoke("");
            } else if (checkAppSysVersion(Integer.parseInt(property))) {
                PuduSystemVersionManager.INSTANCE.checkLocalSystemFile(Integer.parseInt(property)).subscribe(new Consumer<String>() { // from class: com.pudutech.robot.update.SystemUpdateManager$checkLocalSystemFile$p$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(String str2) {
                        Pdlog.m3273d("SystemUpdateManager", "checkLocalAndUpdate = " + str2);
                        Function1.this.invoke(str2);
                    }
                }, new Consumer<Throwable>() { // from class: com.pudutech.robot.update.SystemUpdateManager$checkLocalSystemFile$p$2
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
        String appVersionCode;
        SystemUpdate systemUpdate2 = systemUpdate;
        int parseInt = (systemUpdate2 == null || (appVersionCode = systemUpdate2.getAppVersionCode()) == null) ? 0 : Integer.parseInt(appVersionCode);
        Pdlog.m3273d(TAG, "checkAppNeedSysVersion  systemVersionCode = " + systemVersionCode + " , appSysVersionCode = " + parseInt);
        return parseInt > systemVersionCode;
    }

    public final boolean startSystemUpdate(Context context2, String path) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
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
            PuduSystemVersionManager.INSTANCE.installSystemUpdate(context2, path);
            return true;
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(th));
            return false;
        }
    }

    public final SystemUpdateCount getUpdateCount(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String str = SpUtils.get(context2, "key_system_update_count", "");
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
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("requestNewUpdateFileIfNeed file code = ");
        sb.append(versionCodeFormFileName);
        sb.append(" , SYSTEM_VERSION_CODE = ");
        SystemUpdate systemUpdate2 = systemUpdate;
        sb.append(systemUpdate2 != null ? systemUpdate2.getSystemVersionCode() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        if (!Intrinsics.areEqual(systemUpdate != null ? r1.getAppVersionCode() : null, versionCodeFormFileName)) {
            startCheckFromServer();
        }
    }

    private final void saveUpdateCount(SystemUpdateCount countObj) {
        String toJson = gson.toJson(countObj);
        Pdlog.m3273d(TAG, "saveUpdateCount " + toJson);
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Intrinsics.checkExpressionValueIsNotNull(toJson, "toJson");
        SpUtils.set(context2, "key_system_update_count", toJson);
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onFail(Throwable e) {
        Function2<? super Integer, ? super DownloadInfo, Unit> function2;
        Intrinsics.checkParameterIsNotNull(e, "e");
        Pdlog.m3274e(TAG, "SystemUpdateManager onFail");
        Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        if (mandatoryUpdate && (function2 = mSystemUpdateChangeListener) != null) {
            function2.invoke(4, null);
        }
        int i = retryCount;
        if (i < 3) {
            retryCount = i + 1;
            handler.postDelayed(new Runnable() { // from class: com.pudutech.robot.update.SystemUpdateManager$onFail$1
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
        Function2<? super Integer, ? super DownloadInfo, Unit> function2;
        if (mandatoryUpdate && (function2 = mSystemUpdateChangeListener) != null) {
            function2.invoke(3, null);
        }
        Pdlog.m3273d(TAG, "onFinish");
        isRequestFromServer = true;
        stopNetReceiverIfNeed();
    }

    public void onProgress(DownloadInfo downloadInfo) {
        Function2<? super Integer, ? super DownloadInfo, Unit> function2;
        Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
        if (!mandatoryUpdate || (function2 = mSystemUpdateChangeListener) == null) {
            return;
        }
        function2.invoke(2, downloadInfo);
    }

    @Override // com.pudutech.lib_update.listener.IShowProgress
    public void onStartProgress() {
        Function2<? super Integer, ? super DownloadInfo, Unit> function2;
        Pdlog.m3273d(TAG, "onStartProgress");
        if (!mandatoryUpdate || (function2 = mSystemUpdateChangeListener) == null) {
            return;
        }
        function2.invoke(1, null);
    }

    public final void setSystemCallBack(Function2<? super Integer, ? super DownloadInfo, Unit> mSystemUpdateChangeListener2) {
        Intrinsics.checkParameterIsNotNull(mSystemUpdateChangeListener2, "mSystemUpdateChangeListener");
        mSystemUpdateChangeListener = mSystemUpdateChangeListener2;
    }

    public final void release() {
        mSystemUpdateChangeListener = (Function2) null;
    }

    /* compiled from: SystemUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/robot/update/SystemUpdateManager$NetWorkChangeReceiver;", "Lcom/pudutech/disinfect/baselib/network/manager/NetWorkChangeEvent;", "()V", "onNetworkChange", "", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class NetWorkChangeReceiver extends NetWorkChangeEvent {
        @Override // com.pudutech.disinfect.baselib.network.manager.NetWorkChangeEvent
        public void onNetworkChange() {
            if (NetStatusUtil.isConnected(SystemUpdateManager.access$getContext$p(SystemUpdateManager.INSTANCE))) {
                Pdlog.m3273d(NetWorkChangeEvent.TAG, "NetWorkChangeReceiver onNetworkChange isConnected");
                SystemUpdateManager.INSTANCE.checkFromServer();
            } else {
                Pdlog.m3273d(NetWorkChangeEvent.TAG, "NetWorkChangeReceiver onNetworkChange disConnected");
            }
        }
    }

    /* compiled from: SystemUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/update/SystemUpdateManager$SystemUpdateCount;", "", "path", "", "count", "", "(Ljava/lang/String;I)V", "getCount", "()I", "setCount", "(I)V", "getPath", "()Ljava/lang/String;", "setPath", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
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
            return ((str != null ? str.hashCode() : 0) * 31) + this.count;
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

    /* compiled from: SystemUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, m3961d2 = {"Lcom/pudutech/robot/update/SystemUpdateManager$SystemUpdate;", "", "mac", "", "systemVersionName", "systemVersionCode", "productName", "appVersionName", "appVersionCode", "mandatoryUpdate", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAppVersionCode", "()Ljava/lang/String;", "getAppVersionName", "getMac", "getMandatoryUpdate", "()Z", "getProductName", "getSystemVersionCode", "getSystemVersionName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class SystemUpdate {
        private final String appVersionCode;
        private final String appVersionName;
        private final String mac;
        private final boolean mandatoryUpdate;
        private final String productName;
        private final String systemVersionCode;
        private final String systemVersionName;

        public static /* synthetic */ SystemUpdate copy$default(SystemUpdate systemUpdate, String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = systemUpdate.mac;
            }
            if ((i & 2) != 0) {
                str2 = systemUpdate.systemVersionName;
            }
            String str7 = str2;
            if ((i & 4) != 0) {
                str3 = systemUpdate.systemVersionCode;
            }
            String str8 = str3;
            if ((i & 8) != 0) {
                str4 = systemUpdate.productName;
            }
            String str9 = str4;
            if ((i & 16) != 0) {
                str5 = systemUpdate.appVersionName;
            }
            String str10 = str5;
            if ((i & 32) != 0) {
                str6 = systemUpdate.appVersionCode;
            }
            String str11 = str6;
            if ((i & 64) != 0) {
                z = systemUpdate.mandatoryUpdate;
            }
            return systemUpdate.copy(str, str7, str8, str9, str10, str11, z);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMac() {
            return this.mac;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSystemVersionName() {
            return this.systemVersionName;
        }

        /* renamed from: component3, reason: from getter */
        public final String getSystemVersionCode() {
            return this.systemVersionCode;
        }

        /* renamed from: component4, reason: from getter */
        public final String getProductName() {
            return this.productName;
        }

        /* renamed from: component5, reason: from getter */
        public final String getAppVersionName() {
            return this.appVersionName;
        }

        /* renamed from: component6, reason: from getter */
        public final String getAppVersionCode() {
            return this.appVersionCode;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getMandatoryUpdate() {
            return this.mandatoryUpdate;
        }

        public final SystemUpdate copy(String mac, String systemVersionName, String systemVersionCode, String productName, String appVersionName, String appVersionCode, boolean mandatoryUpdate) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(systemVersionName, "systemVersionName");
            Intrinsics.checkParameterIsNotNull(systemVersionCode, "systemVersionCode");
            Intrinsics.checkParameterIsNotNull(productName, "productName");
            Intrinsics.checkParameterIsNotNull(appVersionName, "appVersionName");
            Intrinsics.checkParameterIsNotNull(appVersionCode, "appVersionCode");
            return new SystemUpdate(mac, systemVersionName, systemVersionCode, productName, appVersionName, appVersionCode, mandatoryUpdate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SystemUpdate)) {
                return false;
            }
            SystemUpdate systemUpdate = (SystemUpdate) other;
            return Intrinsics.areEqual(this.mac, systemUpdate.mac) && Intrinsics.areEqual(this.systemVersionName, systemUpdate.systemVersionName) && Intrinsics.areEqual(this.systemVersionCode, systemUpdate.systemVersionCode) && Intrinsics.areEqual(this.productName, systemUpdate.productName) && Intrinsics.areEqual(this.appVersionName, systemUpdate.appVersionName) && Intrinsics.areEqual(this.appVersionCode, systemUpdate.appVersionCode) && this.mandatoryUpdate == systemUpdate.mandatoryUpdate;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.mac;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.systemVersionName;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.systemVersionCode;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.productName;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.appVersionName;
            int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.appVersionCode;
            int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
            boolean z = this.mandatoryUpdate;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode6 + i;
        }

        public String toString() {
            return "SystemUpdate(mac=" + this.mac + ", systemVersionName=" + this.systemVersionName + ", systemVersionCode=" + this.systemVersionCode + ", productName=" + this.productName + ", appVersionName=" + this.appVersionName + ", appVersionCode=" + this.appVersionCode + ", mandatoryUpdate=" + this.mandatoryUpdate + ")";
        }

        public SystemUpdate(String mac, String systemVersionName, String systemVersionCode, String productName, String appVersionName, String appVersionCode, boolean z) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(systemVersionName, "systemVersionName");
            Intrinsics.checkParameterIsNotNull(systemVersionCode, "systemVersionCode");
            Intrinsics.checkParameterIsNotNull(productName, "productName");
            Intrinsics.checkParameterIsNotNull(appVersionName, "appVersionName");
            Intrinsics.checkParameterIsNotNull(appVersionCode, "appVersionCode");
            this.mac = mac;
            this.systemVersionName = systemVersionName;
            this.systemVersionCode = systemVersionCode;
            this.productName = productName;
            this.appVersionName = appVersionName;
            this.appVersionCode = appVersionCode;
            this.mandatoryUpdate = z;
        }

        public final String getMac() {
            return this.mac;
        }

        public final String getSystemVersionName() {
            return this.systemVersionName;
        }

        public final String getSystemVersionCode() {
            return this.systemVersionCode;
        }

        public final String getProductName() {
            return this.productName;
        }

        public final String getAppVersionName() {
            return this.appVersionName;
        }

        public final String getAppVersionCode() {
            return this.appVersionCode;
        }

        public /* synthetic */ SystemUpdate(String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, str5, str6, (i & 64) != 0 ? false : z);
        }

        public final boolean getMandatoryUpdate() {
            return this.mandatoryUpdate;
        }
    }
}
