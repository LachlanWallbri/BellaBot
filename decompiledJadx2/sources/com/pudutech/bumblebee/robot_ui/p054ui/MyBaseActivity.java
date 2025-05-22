package com.pudutech.bumblebee.robot_ui.p054ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Process;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveEvent;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveListener;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.GeneralContract;
import com.pudutech.bumblebee.presenter.general_task.GeneralPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.config.UrlManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.RobotStatusManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.FinishInter;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ShowConfirmDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.FloatingView;
import com.pudutech.bumblebee.robot_ui.util.FlowCardHelper;
import com.pudutech.bumblebee.robot_ui.util.InputMethodUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBar;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.finishapp.AppStatusTracker;
import com.pudutech.location.view.SignalView;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.MoveKt;
import defpackage.preType;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import me.jessyan.autosize.internal.CustomAdapt;

/* compiled from: MyBaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000«\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0003\u001c\u001f\"\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0014J\u0006\u0010(\u001a\u00020%J\u0012\u0010)\u001a\u00020\u00162\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0012\u0010,\u001a\u00020\u00162\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020%H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u0016H\u0004J\u0006\u00103\u001a\u00020%J\b\u00104\u001a\u00020%H\u0002J\b\u00105\u001a\u00020%H\u0002J\b\u00106\u001a\u00020\u0016H\u0016J\b\u00107\u001a\u00020\u0016H\u0016J\u0012\u00108\u001a\u00020%2\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u00020%H\u0014J\b\u0010<\u001a\u00020%H\u0014J\b\u0010=\u001a\u00020%H\u0014J\b\u0010>\u001a\u00020%H\u0014J\u0010\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020\u0016H\u0016J\u0006\u0010A\u001a\u00020%J\u0010\u0010B\u001a\u00020%2\u0006\u0010C\u001a\u00020DH\u0004J\u0006\u0010E\u001a\u00020%J*\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u00020\u00072\f\u0010H\u001a\b\u0012\u0004\u0012\u00020%0I2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020%0IJ2\u0010K\u001a\u00020%2\u0006\u0010L\u001a\u00020\u00072\u0010\b\u0002\u0010M\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010I2\u0010\b\u0002\u0010N\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010IJ?\u0010O\u001a\u00020%2%\b\u0002\u0010P\u001a\u001f\u0012\u0013\u0012\u00110\u0016¢\u0006\f\bR\u0012\b\bS\u0012\u0004\b\b(T\u0012\u0004\u0012\u00020%\u0018\u00010Q2\u0010\b\u0002\u0010U\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010IJ4\u0010V\u001a\u0004\u0018\u00010\u000e2\u0006\u0010L\u001a\u00020\u00072\n\b\u0002\u0010W\u001a\u0004\u0018\u00010X2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010Z2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u0007J\b\u0010\\\u001a\u00020%H\u0002J\u0010\u0010]\u001a\u00020'2\u0006\u0010^\u001a\u00020'H\u0002J\u0010\u0010_\u001a\u00020'2\u0006\u0010^\u001a\u00020'H\u0003R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u0016X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010 R\u0010\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010#¨\u0006`"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lme/jessyan/autosize/internal/CustomAdapt;", "Lcom/pudutech/bumblebee/presenter/general_task/GeneralContract$ViewInterface;", "Lcom/pudutech/bumblebee/robot_ui/ui/FinishInter;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "checkPermissionDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "confirmDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ShowConfirmDialog;", "errorTipDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "generalPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/GeneralPresenter;", "getGeneralPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/GeneralPresenter;", "generalPresenter$delegate", "Lkotlin/Lazy;", "needReinitApp", "", "getNeedReinitApp", "()Z", "setNeedReinitApp", "(Z)V", "poseListener", "com/pudutech/bumblebee/robot_ui/ui/MyBaseActivity$poseListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity$poseListener$1;", "powerSaveListener", "com/pudutech/bumblebee/robot_ui/ui/MyBaseActivity$powerSaveListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity$powerSaveListener$1;", "spdListener", "com/pudutech/bumblebee/robot_ui/ui/MyBaseActivity$spdListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity$spdListener$1;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "dismissPasswordDialog", "dispatchKeyEvent", "event", "Landroid/view/KeyEvent;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "finish", "getSizeInDp", "", "hasActionBar", "hideErrorTipDialog", "hideNavigationBar", "hideNavigationBarWithoutBack", "isBaseOnWidth", "isBusyState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "resetScreenLight", "setBindSignal", "signalview", "Lcom/pudutech/location/view/SignalView;", "setScreenDark", "showConfirmDialog", AIUIConstant.KEY_CONTENT, "onSure", "Lkotlin/Function0;", "onCancel", "showErrorTipDialog", "tips", "onDismissCallback", "onHideClick", "showPasswordDialog", "onPermissionCheckResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "onCancelResult", "showTipDialog", "onShowListener", "Landroid/content/DialogInterface$OnShowListener;", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "btnText", "translucent", "updateBaseContext", "context", "updateResources", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class MyBaseActivity extends AppCompatActivity implements CustomAdapt, GeneralContract.ViewInterface, FinishInter {
    private HashMap _$_findViewCache;
    private ValidationDialog checkPermissionDialog;
    private ShowConfirmDialog confirmDialog;
    private ShowTipMsgDialog errorTipDialog;
    private boolean needReinitApp;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: generalPresenter$delegate, reason: from kotlin metadata */
    private final Lazy generalPresenter = LazyKt.lazy(new Function0<GeneralPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$generalPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GeneralPresenter invoke() {
            GeneralPresenter generalPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(GeneralPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(GeneralPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                generalPresenter = new GeneralPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(GeneralPresenter.class).toString(), generalPresenter);
            } else {
                if (!(basePresenterInterface instanceof GeneralPresenter)) {
                    basePresenterInterface = null;
                }
                generalPresenter = (GeneralPresenter) basePresenterInterface;
            }
            if (generalPresenter == null) {
                Intrinsics.throwNpe();
            }
            return generalPresenter;
        }
    });
    private MyBaseActivity$powerSaveListener$1 powerSaveListener = new PowerSaveListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$powerSaveListener$1
        @Override // com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveListener
        public void onEvent(PowerSaveEvent event) {
            String str;
            Intrinsics.checkParameterIsNotNull(event, "event");
            str = MyBaseActivity.this.TAG;
            Pdlog.m3275i(str, "PowerSaveEvenT=" + event);
            int i = MyBaseActivity.WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
            if (i == 1) {
                preType.onSleep(TrackingReportManager.INSTANCE, 2);
            } else {
                if (i != 2) {
                    return;
                }
                preType.onSleep(TrackingReportManager.INSTANCE, 1);
            }
        }
    };
    private final MyBaseActivity$spdListener$1 spdListener = new SpeedListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$spdListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
        public void onSpeed(double p0, double p1) {
            MoveKt.onMove(TrackingReportManager.INSTANCE, p0, p1);
        }
    };
    private final MyBaseActivity$poseListener$1 poseListener = new PoseListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$poseListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener
        public void onPose(double p0, double p1, double p2) {
            MoveKt.onPose(TrackingReportManager.INSTANCE, p0, p1, p2);
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PowerSaveEvent.values().length];

        static {
            $EnumSwitchMapping$0[PowerSaveEvent.STAND_BY.ordinal()] = 1;
            $EnumSwitchMapping$0[PowerSaveEvent.SLEEP.ordinal()] = 2;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final GeneralPresenter getGeneralPresenter() {
        return (GeneralPresenter) this.generalPresenter.getValue();
    }

    @Override // me.jessyan.autosize.internal.CustomAdapt
    public float getSizeInDp() {
        return 1280.0f;
    }

    protected final boolean hasActionBar() {
        return false;
    }

    @Override // me.jessyan.autosize.internal.CustomAdapt
    public boolean isBaseOnWidth() {
        return true;
    }

    public boolean isBusyState() {
        return true;
    }

    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        FinishInter.DefaultImpls.jumpAndFinish(this, intent);
    }

    public final boolean getNeedReinitApp() {
        return this.needReinitApp;
    }

    protected final void setNeedReinitApp(boolean z) {
        this.needReinitApp = z;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Intrinsics.areEqual(getClass().getSimpleName(), "Welcome")) {
            Pdlog.m3273d("MyBaseActivity", "AppStatusTracker size = " + AppStatusTracker.getActivityList().size() + " ,welcomeAcIsStart = " + RobotContext.INSTANCE.getWelcomeAcIsStart());
            if (RobotContext.INSTANCE.getWelcomeAcIsStart() && (!Intrinsics.areEqual("robot", InternalConstant.KEY_APP))) {
                AiVoiceManager.INSTANCE.release();
                RobotContext robotContext = RobotContext.INSTANCE;
                Context applicationContext = getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "this.applicationContext");
                robotContext.killMirSdk(applicationContext);
                Process.killProcess(Process.myPid());
            } else {
                RobotContext.INSTANCE.setWelcomeAcIsStart(true);
            }
        } else if (!RobotContext.INSTANCE.getWelcomeAcIsStart()) {
            RobotContext robotContext2 = RobotContext.INSTANCE;
            Context applicationContext2 = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "this.applicationContext");
            robotContext2.killMirSdk(applicationContext2);
            System.out.println((Object) " robot has not init , need restart wellcome activity");
            Pdlog.m3274e("MyBaseActivity", "need restart activity");
            startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
            finish();
            this.needReinitApp = true;
            return;
        }
        if (!hasActionBar()) {
            supportRequestWindowFeature(1);
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$onCreate$1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public final void onSystemUiVisibilityChange(int i) {
                    MyBaseActivity.this.translucent();
                }
            });
        }
        hideNavigationBarWithoutBack();
        InputMethodUtil.changeInputMethodIfNeed(this, "gokey");
        CoreDevices.INSTANCE.getPowerSaveTask().addListenerAllowSameClassName(this.powerSaveListener);
        SDK.INSTANCE.getSpeedListeners().addListenerAllowSameClassName(this.spdListener);
        SDK.INSTANCE.getPoseListeners().addListenerAllowSameClassName(this.poseListener);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev != null && ev.getAction() == 0) {
            Pdlog.m3273d(this.TAG, "dispatchTouchEvent ev = " + ev.getRawX() + ',' + ev.getRawY());
        }
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        return super.dispatchTouchEvent(ev);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CoreDevices.INSTANCE.getPowerSaveTask().removeListener(this.powerSaveListener);
        SDK.INSTANCE.getSpeedListeners().removeListener(this.spdListener);
        SDK.INSTANCE.getPoseListeners().removeListener(this.poseListener);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        translucent();
        RobotStatusManager.INSTANCE.isBusyState(isBusyState());
    }

    public final void translucent() {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
            decorView.setSystemUiVisibility(3846);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context newBase) {
        Intrinsics.checkParameterIsNotNull(newBase, "newBase");
        super.attachBaseContext(updateBaseContext(newBase));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        translucent();
    }

    private final Context updateBaseContext(Context context) {
        return Build.VERSION.SDK_INT >= 24 ? updateResources(context) : context;
    }

    private final Context updateResources(Context context) {
        Resources resources = context.getResources();
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        Intrinsics.checkExpressionValueIsNotNull(configuration, "configuration");
        configuration.setLocales(new LocaleList(locale));
        Context createConfigurationContext = context.createConfigurationContext(configuration);
        Intrinsics.checkExpressionValueIsNotNull(createConfigurationContext, "context.createConfigurationContext(configuration)");
        return createConfigurationContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showPasswordDialog$default(MyBaseActivity myBaseActivity, Function1 function1, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showPasswordDialog");
        }
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function0 = (Function0) null;
        }
        myBaseActivity.showPasswordDialog(function1, function0);
    }

    public final void showPasswordDialog(final Function1<? super Boolean, Unit> onPermissionCheckResult, final Function0<Unit> onCancelResult) {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new ValidationDialog(this);
        }
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog != null) {
            validationDialog.setMPasswordConfig(CollectionsKt.arrayListOf(Constans.INSTANCE.getSettingPassword(), "pudupw"));
            validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$showPasswordDialog$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }
            });
            validationDialog.setMClickCall(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$showPasswordDialog$$inlined$apply$lambda$2
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
                    Function0 function0 = onCancelResult;
                    if (function0 != null) {
                    }
                }
            });
            if (validationDialog.isShowing()) {
                return;
            }
            validationDialog.show();
        }
    }

    public final void dismissPasswordDialog() {
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog != null) {
            validationDialog.dismiss();
        }
    }

    public static /* synthetic */ ShowTipMsgDialog showTipDialog$default(MyBaseActivity myBaseActivity, String str, DialogInterface.OnShowListener onShowListener, DialogInterface.OnDismissListener onDismissListener, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showTipDialog");
        }
        if ((i & 2) != 0) {
            onShowListener = (DialogInterface.OnShowListener) null;
        }
        if ((i & 4) != 0) {
            onDismissListener = (DialogInterface.OnDismissListener) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        return myBaseActivity.showTipDialog(str, onShowListener, onDismissListener, str2);
    }

    public final ShowTipMsgDialog showTipDialog(String tips, DialogInterface.OnShowListener onShowListener, DialogInterface.OnDismissListener onDismissListener, String btnText) {
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        if (isFinishing() || isDestroyed()) {
            Pdlog.m3274e("MyBaseActivity", "showErrorTipDialog failed , ac is finish");
            return null;
        }
        ShowTipMsgDialog showTipMsgDialog = new ShowTipMsgDialog(this);
        showTipMsgDialog.showTipMsg(tips);
        if (onShowListener != null) {
            showTipMsgDialog.setOnShowListener(onShowListener);
        }
        if (onDismissListener != null) {
            showTipMsgDialog.setOnDismissListener(onDismissListener);
        }
        showTipMsgDialog.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$showTipDialog$3
            @Override // com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
            public void onDissmiss(ShowTipMsgDialog dailog) {
                Intrinsics.checkParameterIsNotNull(dailog, "dailog");
                dailog.dismiss();
            }
        });
        showTipMsgDialog.show();
        String str = btnText;
        if (!(str == null || str.length() == 0)) {
            showTipMsgDialog.changeBtnTv(btnText);
        }
        return showTipMsgDialog;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showErrorTipDialog$default(MyBaseActivity myBaseActivity, String str, Function0 function0, Function0 function02, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showErrorTipDialog");
        }
        if ((i & 2) != 0) {
            function0 = (Function0) null;
        }
        if ((i & 4) != 0) {
            function02 = (Function0) null;
        }
        myBaseActivity.showErrorTipDialog(str, function0, function02);
    }

    public final void showErrorTipDialog(String tips, final Function0<Unit> onDismissCallback, Function0<Unit> onHideClick) {
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        if (isFinishing() || isDestroyed()) {
            Pdlog.m3274e("MyBaseActivity", "showErrorTipDialog failed , ac is finish");
            return;
        }
        Pdlog.m3273d(getClass().getSimpleName(), "showErrorTipDialog " + tips);
        if (this.errorTipDialog == null) {
            this.errorTipDialog = new ShowTipMsgDialog(this);
        }
        ShowTipMsgDialog showTipMsgDialog = this.errorTipDialog;
        if (showTipMsgDialog == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog.showTipMsg(tips);
        ShowTipMsgDialog showTipMsgDialog2 = this.errorTipDialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        String string = getString(C4188R.string.pdStr8_10);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr8_10)");
        showTipMsgDialog2.setTitle(string);
        ShowTipMsgDialog showTipMsgDialog3 = this.errorTipDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$showErrorTipDialog$1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                Function0 function0 = Function0.this;
                if (function0 != null) {
                }
            }
        });
        ShowTipMsgDialog showTipMsgDialog4 = this.errorTipDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog4.setOnTitleClick(onHideClick);
        ShowTipMsgDialog showTipMsgDialog5 = this.errorTipDialog;
        if (showTipMsgDialog5 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog5.show();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.error_tips_1);
    }

    public final void hideErrorTipDialog() {
        ShowTipMsgDialog showTipMsgDialog = this.errorTipDialog;
        if (showTipMsgDialog != null) {
            showTipMsgDialog.dismiss();
        }
    }

    public final void showConfirmDialog(String r3, final Function0<Unit> onSure, final Function0<Unit> onCancel) {
        Intrinsics.checkParameterIsNotNull(r3, "content");
        Intrinsics.checkParameterIsNotNull(onSure, "onSure");
        Intrinsics.checkParameterIsNotNull(onCancel, "onCancel");
        if (isFinishing() || isDestroyed()) {
            Pdlog.m3274e("MyBaseActivity", "showErrorTipDialog failed , ac is finish");
            return;
        }
        if (this.confirmDialog == null) {
            this.confirmDialog = new ShowConfirmDialog(this);
        }
        ShowConfirmDialog showConfirmDialog = this.confirmDialog;
        if (showConfirmDialog != null) {
            showConfirmDialog.setContent(r3);
        }
        ShowConfirmDialog showConfirmDialog2 = this.confirmDialog;
        if (showConfirmDialog2 != null) {
            showConfirmDialog2.setBtnSure(getString(C4188R.string.pdStr8_1));
        }
        ShowConfirmDialog showConfirmDialog3 = this.confirmDialog;
        if (showConfirmDialog3 != null) {
            showConfirmDialog3.setBtnCancel(getString(C4188R.string.pdStr8_2));
        }
        ShowConfirmDialog showConfirmDialog4 = this.confirmDialog;
        if (showConfirmDialog4 != null) {
            showConfirmDialog4.setListener(new ShowConfirmDialog.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity$showConfirmDialog$1
                @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.ShowConfirmDialog.Callback
                public void onSure(Dialog dialog) {
                    ShowConfirmDialog showConfirmDialog5;
                    showConfirmDialog5 = MyBaseActivity.this.confirmDialog;
                    if (showConfirmDialog5 != null) {
                        showConfirmDialog5.dismiss();
                    }
                    onSure.invoke();
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.ShowConfirmDialog.Callback
                public void onCancel(Dialog dialog) {
                    ShowConfirmDialog showConfirmDialog5;
                    showConfirmDialog5 = MyBaseActivity.this.confirmDialog;
                    if (showConfirmDialog5 != null) {
                        showConfirmDialog5.dismiss();
                    }
                    onCancel.invoke();
                }
            });
        }
        ShowConfirmDialog showConfirmDialog5 = this.confirmDialog;
        if (showConfirmDialog5 != null) {
            showConfirmDialog5.show();
        }
    }

    public final void setScreenDark() {
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        window.getAttributes().screenBrightness = 0.1f;
        Window window2 = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window2, "window");
        Window window3 = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window3, "window");
        window2.setAttributes(window3.getAttributes());
    }

    public final void resetScreenLight() {
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        window.getAttributes().screenBrightness = -1.0f;
        Window window2 = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window2, "window");
        Window window3 = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window3, "window");
        window2.setAttributes(window3.getAttributes());
    }

    private final void hideNavigationBar() {
        NavigationBar.statusBarDisable(67043328, this);
    }

    private final void hideNavigationBarWithoutBack() {
        NavigationBar.statusBarDisable(62849024, this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event == null || event.getKeyCode() != 4) {
            return super.dispatchKeyEvent(event);
        }
        return true;
    }

    @Override // android.app.Activity
    public void finish() {
        overridePendingTransition(0, 0);
        super.finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        try {
            if (UrlManager.INSTANCE.isTest()) {
                FloatingView.get().attach(this);
                FloatingView.get().customView(C4188R.layout.layout_float_test_server);
                FloatingView.get().add();
                FloatingView floatingView = FloatingView.get();
                Intrinsics.checkExpressionValueIsNotNull(floatingView, "FloatingView.get()");
                TextView tv = (TextView) floatingView.getView().findViewById(C4188R.id.test_server_tv);
                Intrinsics.checkExpressionValueIsNotNull(tv, "tv");
                tv.setText(UrlManager.INSTANCE.getTestType().getClass().getSimpleName());
            }
        } catch (Exception e) {
            Pdlog.m3274e("MyBaseActivity", "onStart : " + Log.getStackTraceString(e));
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        try {
            if (UrlManager.INSTANCE.isTest()) {
                FloatingView.get().detach(this);
            }
        } catch (Exception e) {
            Pdlog.m3274e("MyBaseActivity", "onStop : " + Log.getStackTraceString(e));
        }
    }

    public final void setBindSignal(SignalView signalview) {
        Intrinsics.checkParameterIsNotNull(signalview, "signalview");
        FlowCardHelper.INSTANCE.setBindFlowCard(signalview, this);
    }
}
