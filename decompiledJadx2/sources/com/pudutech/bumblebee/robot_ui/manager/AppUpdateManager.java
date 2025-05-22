package com.pudutech.bumblebee.robot_ui.manager;

import android.app.Activity;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.config.RobotInfo;
import com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowDownloadingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.App4GTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.AppUpdateTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.util.AppUtil;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import com.pudutech.lib_update.UpdateManager;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.resources.language.LanguageUtils;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002 !B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007J$\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0019\u001a\u00020\nJ\u0010\u0010\u001a\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007J<\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00162\b\b\u0002\u0010\u001e\u001a\u00020\nJ\b\u0010\u001f\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AppUpdateManager;", "", "()V", "TAG", "", "cacheCb", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/manager/AppUpdateManager$OnCheckResult;", "Lkotlin/collections/ArrayList;", "checked", "", "checking", "versionResult", "Lcom/pudutech/lib_update/module/model/VerionResult;", "checkAppUpdate", "", "cb", "downloadAndShowDialog", "activity", "Landroid/app/Activity;", "vr", "onFailed", "Lkotlin/Function0;", "getUpdateLevel", "Lcom/pudutech/bumblebee/robot_ui/manager/AppUpdateManager$UpdateLevel;", "needTipRedPoint", "recheckAppUpdate", "showUpdateResultDialog", "onUpdate", "onCancel", "canCancel", "startCheckFormServer", "OnCheckResult", "UpdateLevel", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AppUpdateManager {
    private static boolean checked;
    private static boolean checking;
    private static VerionResult versionResult;
    public static final AppUpdateManager INSTANCE = new AppUpdateManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ArrayList<OnCheckResult> cacheCb = new ArrayList<>();

    /* compiled from: AppUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AppUpdateManager$OnCheckResult;", "", "onFailed", "", "onSuccess", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface OnCheckResult {
        void onFailed();

        void onSuccess(VerionResult vr);
    }

    /* compiled from: AppUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AppUpdateManager$UpdateLevel;", "", "(Ljava/lang/String;I)V", "normal", "advice", "major", "emergency", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum UpdateLevel {
        normal,
        advice,
        major,
        emergency
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UpdateLevel.values().length];

        static {
            $EnumSwitchMapping$0[UpdateLevel.normal.ordinal()] = 1;
            $EnumSwitchMapping$0[UpdateLevel.advice.ordinal()] = 2;
            $EnumSwitchMapping$0[UpdateLevel.major.ordinal()] = 3;
            $EnumSwitchMapping$0[UpdateLevel.emergency.ordinal()] = 4;
        }
    }

    private AppUpdateManager() {
    }

    public final void checkAppUpdate(OnCheckResult cb) {
        Pdlog.m3273d(TAG, "checkAppUpdate : checked = " + checked + "; versionResult = " + versionResult);
        if (checked) {
            VerionResult verionResult = versionResult;
            if (verionResult == null) {
                if (cb != null) {
                    cb.onFailed();
                    return;
                }
                return;
            } else {
                if (cb != null) {
                    if (verionResult == null) {
                        Intrinsics.throwNpe();
                    }
                    cb.onSuccess(verionResult);
                    return;
                }
                return;
            }
        }
        if (cb != null) {
            cacheCb.add(cb);
        }
        startCheckFormServer();
    }

    public final void recheckAppUpdate(OnCheckResult cb) {
        if (checking) {
            if (cb != null) {
                cacheCb.add(cb);
            }
            startCheckFormServer();
        } else {
            checked = false;
            checkAppUpdate(cb);
        }
    }

    public final UpdateLevel getUpdateLevel(VerionResult vr) {
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        if (vr.getAvailable() && vr.getVersion() != null) {
            Version version = vr.getVersion();
            String level = version != null ? version.getLevel() : null;
            if (Intrinsics.areEqual(level, UpdateLevel.normal.name())) {
                return UpdateLevel.normal;
            }
            if (Intrinsics.areEqual(level, UpdateLevel.advice.name())) {
                return UpdateLevel.advice;
            }
            if (Intrinsics.areEqual(level, UpdateLevel.major.name())) {
                return UpdateLevel.major;
            }
            if (Intrinsics.areEqual(level, UpdateLevel.emergency.name())) {
                return UpdateLevel.emergency;
            }
        }
        return null;
    }

    public final boolean needTipRedPoint() {
        int i;
        VerionResult verionResult = versionResult;
        if (verionResult == null) {
            return false;
        }
        if (verionResult == null) {
            Intrinsics.throwNpe();
        }
        UpdateLevel updateLevel = getUpdateLevel(verionResult);
        if (updateLevel == null || (i = WhenMappings.$EnumSwitchMapping$0[updateLevel.ordinal()]) == 1) {
            return false;
        }
        if (i == 2 || i == 3 || i == 4) {
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ void showUpdateResultDialog$default(AppUpdateManager appUpdateManager, Activity activity, VerionResult verionResult, Function0 function0, Function0 function02, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            z = true;
        }
        appUpdateManager.showUpdateResultDialog(activity, verionResult, function0, function02, z);
    }

    public final void showUpdateResultDialog(final Activity activity, VerionResult vr, final Function0<Unit> onUpdate, final Function0<Unit> onCancel, final boolean canCancel) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        Intrinsics.checkParameterIsNotNull(onUpdate, "onUpdate");
        Intrinsics.checkParameterIsNotNull(onCancel, "onCancel");
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(TAG, "showUpdateResultDialog : ac is finish");
            return;
        }
        final AppUpdateTipMsgDialog appUpdateTipMsgDialog = new AppUpdateTipMsgDialog(activity, vr);
        appUpdateTipMsgDialog.setOnUpdateClick(new Function1<VerionResult, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager$showUpdateResultDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VerionResult verionResult) {
                invoke2(verionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VerionResult it) {
                String str;
                String str2;
                String str3;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (canCancel) {
                    if (NetStatusUtil.isWifi(activity.getApplicationContext())) {
                        appUpdateTipMsgDialog.dismiss();
                        onUpdate.invoke();
                        AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                        str3 = AppUpdateManager.TAG;
                        Pdlog.m3273d(str3, "showUpdateResultDialog wifi onUpdate");
                        return;
                    }
                    appUpdateTipMsgDialog.dismiss();
                    App4GTipDialog app4GTipDialog = new App4GTipDialog(activity);
                    app4GTipDialog.show();
                    app4GTipDialog.setOnClick(new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager$showUpdateResultDialog$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(int i) {
                            String str4;
                            String str5;
                            if (i == 1) {
                                onUpdate.invoke();
                                AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                                str5 = AppUpdateManager.TAG;
                                Pdlog.m3273d(str5, "showUpdateResultDialog app4gDiaolog onUpdate");
                                return;
                            }
                            onCancel.invoke();
                            AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                            str4 = AppUpdateManager.TAG;
                            Pdlog.m3273d(str4, "showUpdateResultDialog app4gDiaolog onCancel");
                        }
                    });
                    AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                    str2 = AppUpdateManager.TAG;
                    Pdlog.m3273d(str2, "showUpdateResultDialog app4gDiaolog.show");
                    return;
                }
                appUpdateTipMsgDialog.dismiss();
                onUpdate.invoke();
                AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                str = AppUpdateManager.TAG;
                Pdlog.m3273d(str, "showUpdateResultDialog force onUpdate");
            }
        });
        appUpdateTipMsgDialog.canCancel(canCancel);
        appUpdateTipMsgDialog.setOnCloseBtnClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager$showUpdateResultDialog$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Function0.this.invoke();
            }
        });
        appUpdateTipMsgDialog.show();
    }

    public final void downloadAndShowDialog(Activity activity, VerionResult vr, Function0<Unit> onFailed) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        Intrinsics.checkParameterIsNotNull(onFailed, "onFailed");
        if (activity.isFinishing() || activity.isDestroyed()) {
            Pdlog.m3274e(TAG, "downloadAndShowDialog : ac is finish");
            return;
        }
        Pdlog.m3273d(TAG, "downloadAndShowDialog : activity = " + activity + "; vr = " + vr + "; onFailed = " + onFailed + "; ");
        ShowDownloadingDialog showDownloadingDialog = new ShowDownloadingDialog(activity);
        showDownloadingDialog.showProgress("0");
        showDownloadingDialog.show();
        UpdateManager.INSTANCE.downloadApkAndInstallSilent(vr, new AppUpdateManager$downloadAndShowDialog$1(showDownloadingDialog, activity, onFailed, vr));
        Constans.INSTANCE.saveLastSoftwareVersion(AppUtil.INSTANCE.getVersionName(RobotContext.INSTANCE.getContext()), RobotInfo.INSTANCE.getAppUpdateProduct().getVerCode());
    }

    private final void startCheckFormServer() {
        Pdlog.m3273d(TAG, "startCheckFormServer ");
        if (checking) {
            Pdlog.m3274e(TAG, "startCheckFormServer : is checking");
            return;
        }
        checking = true;
        LanguageUtils languageUtils = new LanguageUtils(RobotContext.INSTANCE.getContext());
        RobotInfo.UpdateInfo appUpdateProduct = RobotInfo.INSTANCE.getAppUpdateProduct();
        String versionName = AppUtil.INSTANCE.getVersionName(RobotContext.INSTANCE.getContext());
        String verCode = appUpdateProduct.getVerCode();
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        CheckUpdateRequestParams checkUpdateRequestParams = new CheckUpdateRequestParams(versionName, verCode, mac, appUpdateProduct.getName(), null, null, null, (languageUtils.getCurrent().getLocale().getLanguage() + "-") + languageUtils.getCurrent().getLocale().getCountry(), 112, null);
        Pdlog.m3273d(TAG, "startCheckFormServer param = " + checkUpdateRequestParams);
        UpdateManager.INSTANCE.checkVersion(checkUpdateRequestParams, new Function1<VerionResult, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager$startCheckFormServer$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VerionResult verionResult) {
                invoke2(verionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VerionResult versionData) {
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkParameterIsNotNull(versionData, "versionData");
                AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                str = AppUpdateManager.TAG;
                Pdlog.m3273d(str, "startCheckFormServer : versionResult = " + versionData + "; ");
                AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                AppUpdateManager.versionResult = versionData;
                AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checking = false;
                AppUpdateManager appUpdateManager4 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checked = true;
                AppUpdateManager appUpdateManager5 = AppUpdateManager.INSTANCE;
                arrayList = AppUpdateManager.cacheCb;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((AppUpdateManager.OnCheckResult) it.next()).onSuccess(versionData);
                }
                AppUpdateManager appUpdateManager6 = AppUpdateManager.INSTANCE;
                arrayList2 = AppUpdateManager.cacheCb;
                arrayList2.clear();
            }
        }, new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager$startCheckFormServer$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                str = AppUpdateManager.TAG;
                Pdlog.m3274e(str, "startCheckFormServer " + Log.getStackTraceString(it));
                AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checking = false;
                AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checked = true;
                AppUpdateManager appUpdateManager4 = AppUpdateManager.INSTANCE;
                arrayList = AppUpdateManager.cacheCb;
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((AppUpdateManager.OnCheckResult) it2.next()).onFailed();
                }
                AppUpdateManager appUpdateManager5 = AppUpdateManager.INSTANCE;
                arrayList2 = AppUpdateManager.cacheCb;
                arrayList2.clear();
            }
        });
    }
}
