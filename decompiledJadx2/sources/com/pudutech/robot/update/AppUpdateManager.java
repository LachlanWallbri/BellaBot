package com.pudutech.robot.update;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.dialog.ShowTipsDialog;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.lib_update.AppUpdateContext;
import com.pudutech.lib_update.UpdateManager;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.robot.update.AppUpdateManager;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: AppUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003./0B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\tJ6\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001dJI\u0010\u001f\u001a\u00020\u00132\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040!2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001d¢\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\fH\u0002J\u0010\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001b\u001a\u00020\u0011J\u0016\u0010'\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\fJ\u0006\u0010)\u001a\u00020\fJ,\u0010*\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\tJ:\u0010+\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00112\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001d2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001dJ\u0010\u0010-\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, m3961d2 = {"Lcom/pudutech/robot/update/AppUpdateManager;", "", "()V", "TAG", "", "UPDATE_HOST", "UPDATE_HOST_TEST", "cacheCb", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/update/AppUpdateManager$OnCheckResult;", "Lkotlin/collections/ArrayList;", "checked", "", "checking", "context", "Landroid/content/Context;", "versionResult", "Lcom/pudutech/lib_update/module/model/VerionResult;", "checkAppUpdate", "", "checkUpdate", "Lcom/pudutech/robot/update/AppUpdateManager$CheckUpdate;", "showDialog", "cb", "downloadAndShowDialog", "dialog", "Lcom/pudutech/disinfect/baselib/dialog/ShowTipsDialog;", "vr", "onFailed", "Lkotlin/Function0;", "onDownFinish", "downloadAndShowDialog2", "contents", "", "([Ljava/lang/String;Lcom/pudutech/disinfect/baselib/dialog/ShowTipsDialog;Lcom/pudutech/lib_update/module/model/VerionResult;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getUpdateHost", "test", "getUpdateLevel", "Lcom/pudutech/robot/update/AppUpdateManager$UpdateLevel;", "init", "isTest", "needTipRedPoint", "recheckAppUpdate", "showResult", "onUpdateClick", "startCheckFormServer", "CheckUpdate", "OnCheckResult", "UpdateLevel", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AppUpdateManager {
    private static boolean checked;
    private static boolean checking;
    private static Context context;
    private static VerionResult versionResult;
    public static final AppUpdateManager INSTANCE = new AppUpdateManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String UPDATE_HOST = UPDATE_HOST;
    private static final String UPDATE_HOST = UPDATE_HOST;
    private static final String UPDATE_HOST_TEST = UPDATE_HOST_TEST;
    private static final String UPDATE_HOST_TEST = UPDATE_HOST_TEST;
    private static final ArrayList<OnCheckResult> cacheCb = new ArrayList<>();

    /* compiled from: AppUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/update/AppUpdateManager$OnCheckResult;", "", "onFailed", "", "onSuccess", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnCheckResult {
        void onFailed();

        void onSuccess(VerionResult vr);
    }

    /* compiled from: AppUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/update/AppUpdateManager$UpdateLevel;", "", "(Ljava/lang/String;I)V", "normal", "advice", "major", "emergency", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum UpdateLevel {
        normal,
        advice,
        major,
        emergency
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UpdateLevel.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[UpdateLevel.normal.ordinal()] = 1;
            $EnumSwitchMapping$0[UpdateLevel.advice.ordinal()] = 2;
            $EnumSwitchMapping$0[UpdateLevel.major.ordinal()] = 3;
            $EnumSwitchMapping$0[UpdateLevel.emergency.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[UpdateLevel.values().length];
            $EnumSwitchMapping$1[UpdateLevel.normal.ordinal()] = 1;
            $EnumSwitchMapping$1[UpdateLevel.advice.ordinal()] = 2;
            $EnumSwitchMapping$1[UpdateLevel.major.ordinal()] = 3;
            $EnumSwitchMapping$1[UpdateLevel.emergency.ordinal()] = 4;
        }
    }

    private AppUpdateManager() {
    }

    public static final /* synthetic */ Context access$getContext$p(AppUpdateManager appUpdateManager) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void init(Context context2, boolean isTest) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
        String updateHost = getUpdateHost(isTest);
        Pdlog.m3273d(TAG, "App update isTest:" + isTest + ", url:" + updateHost);
        AppUpdateContext.init(context2, updateHost);
    }

    private final String getUpdateHost(boolean test) {
        return test ? UPDATE_HOST_TEST : UPDATE_HOST;
    }

    public static /* synthetic */ void checkAppUpdate$default(AppUpdateManager appUpdateManager, Context context2, CheckUpdate checkUpdate, boolean z, OnCheckResult onCheckResult, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            onCheckResult = (OnCheckResult) null;
        }
        appUpdateManager.checkAppUpdate(context2, checkUpdate, z, onCheckResult);
    }

    public final void checkAppUpdate(Context context2, CheckUpdate checkUpdate, boolean showDialog, final OnCheckResult cb) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(checkUpdate, "checkUpdate");
        Pdlog.m3273d(TAG, "checkAppUpdate : checked = " + checked + "; versionResult = " + versionResult);
        final ShowTipsDialog showTipsDialog = showDialog ? new ShowTipsDialog(context2) : null;
        if (showTipsDialog != null) {
            showTipsDialog.setCancelable(false);
        }
        if (checked) {
            VerionResult verionResult = versionResult;
            if (verionResult != null && showTipsDialog != null) {
                if (verionResult == null) {
                    Intrinsics.throwNpe();
                }
                showResult$default(this, showTipsDialog, verionResult, null, null, 12, null);
            }
            VerionResult verionResult2 = versionResult;
            if (verionResult2 == null) {
                if (cb != null) {
                    cb.onFailed();
                    return;
                }
                return;
            } else {
                if (cb != null) {
                    if (verionResult2 == null) {
                        Intrinsics.throwNpe();
                    }
                    cb.onSuccess(verionResult2);
                    return;
                }
                return;
            }
        }
        cacheCb.add(new OnCheckResult() { // from class: com.pudutech.robot.update.AppUpdateManager$checkAppUpdate$checkCb$1
            @Override // com.pudutech.robot.update.AppUpdateManager.OnCheckResult
            public void onSuccess(VerionResult vr) {
                Intrinsics.checkParameterIsNotNull(vr, "vr");
                ShowTipsDialog showTipsDialog2 = ShowTipsDialog.this;
                if (showTipsDialog2 != null && showTipsDialog2.isShowing()) {
                    AppUpdateManager.showResult$default(AppUpdateManager.INSTANCE, ShowTipsDialog.this, vr, null, null, 12, null);
                }
                AppUpdateManager.OnCheckResult onCheckResult = cb;
                if (onCheckResult != null) {
                    onCheckResult.onSuccess(vr);
                }
            }

            @Override // com.pudutech.robot.update.AppUpdateManager.OnCheckResult
            public void onFailed() {
                AppUpdateManager.OnCheckResult onCheckResult = cb;
                if (onCheckResult != null) {
                    onCheckResult.onFailed();
                }
            }
        });
        if (showTipsDialog != null) {
            showTipsDialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_LOADING_CLOSE());
        }
        if (showTipsDialog != null) {
            showTipsDialog.show();
        }
        startCheckFormServer(checkUpdate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showResult$default(AppUpdateManager appUpdateManager, ShowTipsDialog showTipsDialog, VerionResult verionResult, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = (Function0) null;
        }
        if ((i & 8) != 0) {
            function02 = (Function0) null;
        }
        appUpdateManager.showResult(showTipsDialog, verionResult, function0, function02);
    }

    public final void showResult(final ShowTipsDialog dialog, final VerionResult vr, final Function0<Unit> onUpdateClick, final Function0<Unit> onDownFinish) {
        String str;
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        final UpdateLevel updateLevel = getUpdateLevel(vr);
        Pdlog.m3273d(TAG, "showResult : dialog = " + dialog + "; vr = " + vr + "; ");
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String string = context2.getString(C5722R.string.pdStr27_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr27_1)");
        dialog.setTitle(string);
        Context context3 = context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String string2 = context3.getString(C5722R.string.pdStr27_1);
        Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.pdStr27_1)");
        dialog.setButTips(string2);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Context context4 = context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String string3 = context4.getString(C5722R.string.pdStr27_2);
        Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.pdStr27_2)");
        Object[] objArr = new Object[1];
        Version version = vr.getVersion();
        objArr[0] = version != null ? version.getVersion_name() : null;
        String format = String.format(string3, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        Version version2 = vr.getVersion();
        if (version2 == null || (str = version2.getVersion_description()) == null) {
            str = "";
        }
        dialog.setScrollTips(format, str);
        dialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.robot.update.AppUpdateManager$showResult$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (AppUpdateManager.UpdateLevel.this != AppUpdateManager.UpdateLevel.emergency && !NetStatusUtil.isWifi(AppUpdateManager.access$getContext$p(AppUpdateManager.INSTANCE))) {
                    ToastUtils.INSTANCE.showShortToast("请链接WiFi后继续更新");
                    return;
                }
                Function0 function0 = onUpdateClick;
                if (function0 == null) {
                    AppUpdateManager.INSTANCE.downloadAndShowDialog(dialog, vr, new Function0<Unit>() { // from class: com.pudutech.robot.update.AppUpdateManager$showResult$1.1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            String str2;
                            AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                            str2 = AppUpdateManager.TAG;
                            Pdlog.m3273d(str2, "showResult on Failed");
                        }
                    }, onDownFinish);
                } else {
                    function0.invoke();
                }
            }
        });
        if (updateLevel != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[updateLevel.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON_CLOSE());
                dialog.setCancelable(false);
                dialog.show();
                return;
            } else if (i == 4) {
                dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_TIPS_SCROLL_CONTENT_BOTTOM_AND_BUTTON());
                dialog.setCancelable(false);
                dialog.show();
                return;
            }
        }
        dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_TIPS_BOTTOM_BUTTON_AND_CLOSE());
        Context context5 = context;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String string4 = context5.getString(C5722R.string.pdStr27_3);
        Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.pdStr27_3)");
        dialog.setTips(string4);
        Context context6 = context;
        if (context6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        String string5 = context6.getString(C5722R.string.pdStr27_4);
        Intrinsics.checkExpressionValueIsNotNull(string5, "context.getString(R.string.pdStr27_4)");
        dialog.setButTips(string5);
        dialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.robot.update.AppUpdateManager$showResult$2
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
                ShowTipsDialog.this.dismiss();
            }
        });
        dialog.show();
    }

    public static /* synthetic */ void recheckAppUpdate$default(AppUpdateManager appUpdateManager, Context context2, CheckUpdate checkUpdate, boolean z, OnCheckResult onCheckResult, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            onCheckResult = (OnCheckResult) null;
        }
        appUpdateManager.recheckAppUpdate(context2, checkUpdate, z, onCheckResult);
    }

    public final void recheckAppUpdate(Context context2, CheckUpdate checkUpdate, boolean showDialog, OnCheckResult cb) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(checkUpdate, "checkUpdate");
        if (checking) {
            if (cb != null) {
                cacheCb.add(cb);
            }
            Pdlog.m3273d(TAG, "recheckAppUpdate : checking ");
            startCheckFormServer(checkUpdate);
            return;
        }
        checked = false;
        checkAppUpdate(context2, checkUpdate, showDialog, cb);
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
        if (updateLevel == null || (i = WhenMappings.$EnumSwitchMapping$1[updateLevel.ordinal()]) == 1) {
            return false;
        }
        if (i == 2 || i == 3 || i == 4) {
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void downloadAndShowDialog$default(AppUpdateManager appUpdateManager, ShowTipsDialog showTipsDialog, VerionResult verionResult, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 8) != 0) {
            function02 = (Function0) null;
        }
        appUpdateManager.downloadAndShowDialog(showTipsDialog, verionResult, function0, function02);
    }

    public final void downloadAndShowDialog(ShowTipsDialog dialog, VerionResult vr, Function0<Unit> onFailed, Function0<Unit> onDownFinish) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        Intrinsics.checkParameterIsNotNull(onFailed, "onFailed");
        Pdlog.m3273d(TAG, "downloadAndShowDialog : dialog = " + dialog + "; vr = " + vr + "; onFailed = " + onFailed + "; ");
        dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_PROGRESS());
        dialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.robot.update.AppUpdateManager$downloadAndShowDialog$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
        dialog.setProgress(String.valueOf(0));
        dialog.setCancelable(false);
        UpdateManager.INSTANCE.downloadApkAndInstallSilent(vr, new AppUpdateManager$downloadAndShowDialog$2(dialog, onDownFinish));
    }

    public static /* synthetic */ void downloadAndShowDialog2$default(AppUpdateManager appUpdateManager, String[] strArr, ShowTipsDialog showTipsDialog, VerionResult verionResult, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 16) != 0) {
            function02 = (Function0) null;
        }
        appUpdateManager.downloadAndShowDialog2(strArr, showTipsDialog, verionResult, function0, function02);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v6, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v3, types: [T, java.lang.Object, java.lang.String] */
    public final void downloadAndShowDialog2(String[] contents, ShowTipsDialog dialog, VerionResult vr, Function0<Unit> onFailed, Function0<Unit> onDownFinish) {
        Intrinsics.checkParameterIsNotNull(contents, "contents");
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        Intrinsics.checkParameterIsNotNull(onFailed, "onFailed");
        Pdlog.m3273d(TAG, "downloadAndShowDialog : dialog = " + dialog + "; vr = " + vr + "; onFailed = " + onFailed + "; ");
        dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_PROGRESS());
        dialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.robot.update.AppUpdateManager$downloadAndShowDialog2$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
        dialog.setProgress(String.valueOf(0));
        dialog.setCancelable(false);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        ?? string = context2.getString(C5722R.string.pdStr27_5);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr27_5)");
        objectRef.element = string;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Context context3 = context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        ?? string2 = context3.getString(C5722R.string.pdStr27_6);
        Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.pdStr27_6)");
        objectRef2.element = string2;
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        Context context4 = context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        ?? string3 = context4.getString(C5722R.string.pdStr27_4);
        Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.pdStr27_4)");
        objectRef3.element = string3;
        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
        Context context5 = context;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        ?? string4 = context5.getString(C5722R.string.pdStr27_7);
        Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.pdStr27_7)");
        objectRef4.element = string4;
        if (contents.length > 0) {
            objectRef.element = contents[0];
            objectRef2.element = contents[1];
            objectRef3.element = contents[2];
            objectRef4.element = contents[3];
        }
        UpdateManager.INSTANCE.downloadApkAndInstallSilent(vr, new AppUpdateManager$downloadAndShowDialog2$2(dialog, objectRef, objectRef2, objectRef3, onDownFinish, objectRef4));
    }

    private final void startCheckFormServer(CheckUpdate checkUpdate) {
        Pdlog.m3273d(TAG, "startCheckFormServer ");
        if (checking) {
            Pdlog.m3274e(TAG, "startCheckFormServer : is checking");
            return;
        }
        checking = true;
        CheckUpdateRequestParams checkUpdateRequestParams = new CheckUpdateRequestParams(checkUpdate.getVersionName(), String.valueOf(checkUpdate.getVersionCode()), checkUpdate.getMac(), checkUpdate.getProductName(), null, null, null, checkUpdate.getLanguage(), 112, null);
        Pdlog.m3273d(TAG, "startCheckFormServer param = " + checkUpdateRequestParams);
        UpdateManager.checkSoftWareVersion(checkUpdateRequestParams, new Function<VerionResult, Void>() { // from class: com.pudutech.robot.update.AppUpdateManager$startCheckFormServer$1
            @Override // io.reactivex.functions.Function
            public final Void apply(VerionResult versionResult2) {
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkParameterIsNotNull(versionResult2, "versionResult");
                AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                str = AppUpdateManager.TAG;
                Pdlog.m3273d(str, "startCheckFormServer : versionResult = " + versionResult2 + "; ");
                AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                AppUpdateManager.versionResult = versionResult2;
                AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checking = false;
                AppUpdateManager appUpdateManager4 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checked = true;
                AppUpdateManager appUpdateManager5 = AppUpdateManager.INSTANCE;
                arrayList = AppUpdateManager.cacheCb;
                Iterator<T> it = arrayList.iterator();
                while (it.hasNext()) {
                    ((AppUpdateManager.OnCheckResult) it.next()).onSuccess(versionResult2);
                }
                AppUpdateManager appUpdateManager6 = AppUpdateManager.INSTANCE;
                arrayList2 = AppUpdateManager.cacheCb;
                arrayList2.clear();
                return null;
            }
        }, new Function<Throwable, Void>() { // from class: com.pudutech.robot.update.AppUpdateManager$startCheckFormServer$2
            @Override // io.reactivex.functions.Function
            public final Void apply(Throwable it) {
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                AppUpdateManager appUpdateManager = AppUpdateManager.INSTANCE;
                str = AppUpdateManager.TAG;
                Pdlog.m3274e(str, "startCheckFormServer " + it.getMessage());
                AppUpdateManager appUpdateManager2 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checking = false;
                AppUpdateManager appUpdateManager3 = AppUpdateManager.INSTANCE;
                AppUpdateManager.checked = true;
                AppUpdateManager appUpdateManager4 = AppUpdateManager.INSTANCE;
                arrayList = AppUpdateManager.cacheCb;
                Iterator<T> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((AppUpdateManager.OnCheckResult) it2.next()).onFailed();
                }
                AppUpdateManager appUpdateManager5 = AppUpdateManager.INSTANCE;
                arrayList2 = AppUpdateManager.cacheCb;
                arrayList2.clear();
                return null;
            }
        });
    }

    /* compiled from: AppUpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/update/AppUpdateManager$CheckUpdate;", "", "mac", "", "language", "versionName", "versionCode", "", "productName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getLanguage", "()Ljava/lang/String;", "getMac", "getProductName", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "module_robot_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class CheckUpdate {
        private final String language;
        private final String mac;
        private final String productName;
        private final int versionCode;
        private final String versionName;

        public static /* synthetic */ CheckUpdate copy$default(CheckUpdate checkUpdate, String str, String str2, String str3, int i, String str4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = checkUpdate.mac;
            }
            if ((i2 & 2) != 0) {
                str2 = checkUpdate.language;
            }
            String str5 = str2;
            if ((i2 & 4) != 0) {
                str3 = checkUpdate.versionName;
            }
            String str6 = str3;
            if ((i2 & 8) != 0) {
                i = checkUpdate.versionCode;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                str4 = checkUpdate.productName;
            }
            return checkUpdate.copy(str, str5, str6, i3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMac() {
            return this.mac;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* renamed from: component3, reason: from getter */
        public final String getVersionName() {
            return this.versionName;
        }

        /* renamed from: component4, reason: from getter */
        public final int getVersionCode() {
            return this.versionCode;
        }

        /* renamed from: component5, reason: from getter */
        public final String getProductName() {
            return this.productName;
        }

        public final CheckUpdate copy(String mac, String language, String versionName, int versionCode, String productName) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(language, "language");
            Intrinsics.checkParameterIsNotNull(versionName, "versionName");
            Intrinsics.checkParameterIsNotNull(productName, "productName");
            return new CheckUpdate(mac, language, versionName, versionCode, productName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CheckUpdate)) {
                return false;
            }
            CheckUpdate checkUpdate = (CheckUpdate) other;
            return Intrinsics.areEqual(this.mac, checkUpdate.mac) && Intrinsics.areEqual(this.language, checkUpdate.language) && Intrinsics.areEqual(this.versionName, checkUpdate.versionName) && this.versionCode == checkUpdate.versionCode && Intrinsics.areEqual(this.productName, checkUpdate.productName);
        }

        public int hashCode() {
            String str = this.mac;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.language;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.versionName;
            int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.versionCode) * 31;
            String str4 = this.productName;
            return hashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "CheckUpdate(mac=" + this.mac + ", language=" + this.language + ", versionName=" + this.versionName + ", versionCode=" + this.versionCode + ", productName=" + this.productName + ")";
        }

        public CheckUpdate(String mac, String language, String versionName, int i, String productName) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(language, "language");
            Intrinsics.checkParameterIsNotNull(versionName, "versionName");
            Intrinsics.checkParameterIsNotNull(productName, "productName");
            this.mac = mac;
            this.language = language;
            this.versionName = versionName;
            this.versionCode = i;
            this.productName = productName;
        }

        public final String getMac() {
            return this.mac;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final String getVersionName() {
            return this.versionName;
        }

        public final int getVersionCode() {
            return this.versionCode;
        }

        public final String getProductName() {
            return this.productName;
        }
    }
}
