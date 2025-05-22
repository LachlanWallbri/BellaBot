package com.pudutech.peanut.robot_ui.p063ui.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.general_task.GeneralPresenter;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.config.UrlManager;
import com.pudutech.peanut.robot_ui.extend.RobotPeripheralsFactoryExtKt;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.CloseAppDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.ShowConfrimDialog;
import com.pudutech.peanut.robot_ui.p063ui.view.floatview.FloatingView;
import com.pudutech.peanut.robot_ui.util.InputMethodUtil;
import com.pudutech.peanut.robot_ui.util.NavigationBar;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.common.RobotShutDownManager;
import com.pudutech.robot.peripherals.common.ShutDownHelper;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import me.jessyan.autosize.internal.CustomAdapt;

/* compiled from: MyBaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008b\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u001a\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0012\u0010 \u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010#\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001dH\u0016J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u0011H\u0002J\u0006\u0010*\u001a\u00020\u001dJ\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020\u001dH\u0002J\b\u0010-\u001a\u00020\u0011H\u0016J\u0012\u0010.\u001a\u00020\u001d2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0012\u00101\u001a\u00020\u001d2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u00020\u001dH\u0014J\b\u00105\u001a\u00020\u001dH\u0014J\b\u00106\u001a\u00020\u001dH\u0014J\b\u00107\u001a\u00020\u001dH\u0014J\u0010\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0011H\u0016J\u0010\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020\u0005H\u0002J\u0006\u0010<\u001a\u00020\u001dJ\u0006\u0010=\u001a\u00020\u001dJ*\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u00052\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001d0A2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u001d0AJ2\u0010C\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\u00052\u0010\b\u0002\u0010E\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010A2\u0010\b\u0002\u0010F\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010AJ\b\u0010G\u001a\u00020\u001dH\u0002J4\u0010H\u001a\u0004\u0018\u00010\t2\u0006\u0010D\u001a\u00020\u00052\n\b\u0002\u0010I\u001a\u0004\u0018\u00010J2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u0005J\b\u0010N\u001a\u00020\u001dH\u0002J\u0010\u0010O\u001a\u00020\u001f2\u0006\u0010P\u001a\u00020\u001fH\u0002J\u0010\u0010Q\u001a\u00020\u001f2\u0006\u0010P\u001a\u00020\u001fH\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0011X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001b¨\u0006R"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lme/jessyan/autosize/internal/CustomAdapt;", "()V", "TAG", "", "confirmDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/ShowConfrimDialog;", "errorTipDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "generalPresenter", "Lcom/pudutech/peanut/presenter/general_task/GeneralPresenter;", "getGeneralPresenter", "()Lcom/pudutech/peanut/presenter/general_task/GeneralPresenter;", "generalPresenter$delegate", "Lkotlin/Lazy;", "isShutDown", "", "mShutDownDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/CloseAppDialog;", "needReinitApp", "getNeedReinitApp", "()Z", "setNeedReinitApp", "(Z)V", "shutdownEventListener", "com/pudutech/peanut/robot_ui/ui/base/MyBaseActivity$shutdownEventListener$1", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity$shutdownEventListener$1;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "dispatchKeyEvent", "event", "Landroid/view/KeyEvent;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "finish", "getSizeInDp", "", "hasActionBar", "hideErrorTipDialog", "hideNavigationBar", "hideNavigationBarWithoutBack", "isBaseOnWidth", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "reboot", "reason", "resetScreenLight", "setScreenDark", "showConfirmDialog", AIUIConstant.KEY_CONTENT, "onSure", "Lkotlin/Function0;", "onCancel", "showErrorTipDialog", "tips", "onDismissCallback", "onHideClick", "showShutDownDialog", "showTipDialog", "onShowListener", "Landroid/content/DialogInterface$OnShowListener;", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "btnText", "translucent", "updateBaseContext", "context", "updateResources", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class MyBaseActivity extends AppCompatActivity implements CustomAdapt {
    private HashMap _$_findViewCache;
    private ShowConfrimDialog confirmDialog;
    private ShowTipMsgDialog errorTipDialog;
    private boolean isShutDown;
    private CloseAppDialog mShutDownDialog;
    private boolean needReinitApp;
    private final String TAG = "MyBaseActivity";

    /* renamed from: generalPresenter$delegate, reason: from kotlin metadata */
    private final Lazy generalPresenter = LazyKt.lazy(new Function0<GeneralPresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$generalPresenter$2
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
    private final MyBaseActivity$shutdownEventListener$1 shutdownEventListener = new RobotShutDownManager.ShutDownListener() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$shutdownEventListener$1
        @Override // com.pudutech.robot.peripherals.common.RobotShutDownManager.ShutDownListener
        public void shutDownNotify() {
            MyBaseActivity.this.showShutDownDialog();
        }
    };

    private final boolean hasActionBar() {
        return false;
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

    @Override // me.jessyan.autosize.internal.CustomAdapt
    public boolean isBaseOnWidth() {
        return true;
    }

    public void jumpAndFinish(Intent intent) {
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
        if (!hasActionBar()) {
            supportRequestWindowFeature(1);
            Window window = getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "window");
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$onCreate$1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public final void onSystemUiVisibilityChange(int i) {
                    MyBaseActivity.this.translucent();
                }
            });
        }
        hideNavigationBarWithoutBack();
        InputMethodUtil.changeInputMethodIfNeed(this, "gokey");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        return super.dispatchTouchEvent(ev);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        NavigationBar.release();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        RobotShutDownManager.INSTANCE.addShutDownListener(this.shutdownEventListener);
        translucent();
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
        showTipMsgDialog.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$showTipDialog$3
            @Override // com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
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
        String string = getString(C5508R.string.pdStr8_10);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr8_10)");
        showTipMsgDialog2.setTitle(string);
        ShowTipMsgDialog showTipMsgDialog3 = this.errorTipDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$showErrorTipDialog$1
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
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.error_tips_1);
    }

    public final void hideErrorTipDialog() {
        ShowTipMsgDialog showTipMsgDialog;
        ShowTipMsgDialog showTipMsgDialog2 = this.errorTipDialog;
        if (showTipMsgDialog2 == null || !showTipMsgDialog2.isShowing() || (showTipMsgDialog = this.errorTipDialog) == null) {
            return;
        }
        showTipMsgDialog.dismiss();
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
            this.confirmDialog = new ShowConfrimDialog(this);
        }
        ShowConfrimDialog showConfrimDialog = this.confirmDialog;
        if (showConfrimDialog != null) {
            showConfrimDialog.setContent(r3);
        }
        ShowConfrimDialog showConfrimDialog2 = this.confirmDialog;
        if (showConfrimDialog2 != null) {
            showConfrimDialog2.setBtnSure(getString(C5508R.string.pdStr8_1));
        }
        ShowConfrimDialog showConfrimDialog3 = this.confirmDialog;
        if (showConfrimDialog3 != null) {
            showConfrimDialog3.setBtnCancel(getString(C5508R.string.pdStr8_2));
        }
        ShowConfrimDialog showConfrimDialog4 = this.confirmDialog;
        if (showConfrimDialog4 != null) {
            showConfrimDialog4.setListener(new ShowConfrimDialog.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$showConfirmDialog$1
                @Override // com.pudutech.peanut.robot_ui.ui.dialog.ShowConfrimDialog.Callback
                public void onSure(Dialog dialog) {
                    ShowConfrimDialog showConfrimDialog5;
                    showConfrimDialog5 = MyBaseActivity.this.confirmDialog;
                    if (showConfrimDialog5 != null) {
                        showConfrimDialog5.dismiss();
                    }
                    onSure.invoke();
                }

                @Override // com.pudutech.peanut.robot_ui.ui.dialog.ShowConfrimDialog.Callback
                public void onCancel(Dialog dialog) {
                    ShowConfrimDialog showConfrimDialog5;
                    showConfrimDialog5 = MyBaseActivity.this.confirmDialog;
                    if (showConfrimDialog5 != null) {
                        showConfrimDialog5.dismiss();
                    }
                    onCancel.invoke();
                }
            });
        }
        ShowConfrimDialog showConfrimDialog5 = this.confirmDialog;
        if (showConfrimDialog5 != null) {
            showConfrimDialog5.show();
        }
    }

    public final void showShutDownDialog() {
        if (isFinishing() || isDestroyed()) {
            Pdlog.m3274e("MyBaseActivity", "showErrorTipDialog failed , ac is finish");
            return;
        }
        if (this.mShutDownDialog == null) {
            this.mShutDownDialog = new CloseAppDialog(this);
            CloseAppDialog closeAppDialog = this.mShutDownDialog;
            if (closeAppDialog != null) {
                closeAppDialog.setOnShutDownClick(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity$showShutDownDialog$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        CloseAppDialog closeAppDialog2;
                        String str;
                        closeAppDialog2 = MyBaseActivity.this.mShutDownDialog;
                        if (closeAppDialog2 != null) {
                            closeAppDialog2.dismiss();
                        }
                        Constans constans = Constans.INSTANCE;
                        Integer power = BatteryInfoManager.INSTANCE.getPower();
                        constans.setChargingPower(power != null ? power.intValue() : 0);
                        if (z) {
                            str = MyBaseActivity.this.TAG;
                            Pdlog.m3273d(str, "onShutdownConfirm ");
                            if (!ShutDownHelper.INSTANCE.doCaseByReflect()) {
                                ShutDownHelper.INSTANCE.doCaseBySimulateInputEvent(RobotContext.INSTANCE.getContext());
                            }
                            RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).shutdown();
                            return;
                        }
                        MyBaseActivity.this.reboot("chongqi");
                    }
                });
            }
        }
        CloseAppDialog closeAppDialog2 = this.mShutDownDialog;
        if (closeAppDialog2 != null) {
            closeAppDialog2.show();
        }
    }

    public final void reboot(String reason) {
        Object systemService = getSystemService("power");
        if (!(systemService instanceof PowerManager)) {
            systemService = null;
        }
        PowerManager powerManager = (PowerManager) systemService;
        if (powerManager != null) {
            powerManager.reboot(reason);
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
        NavigationBar.statusBarDisable(67043328, getApplicationContext());
    }

    private final void hideNavigationBarWithoutBack() {
        NavigationBar.statusBarDisable(62849024, getApplicationContext());
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
                FloatingView.get().customView(C5508R.layout.layout_float_test_server);
                FloatingView.get().add();
            }
        } catch (Exception e) {
            Pdlog.m3274e("MyBaseActivity", "onStart : " + Log.getStackTraceString(e));
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mShutDownDialog = (CloseAppDialog) null;
        RobotShutDownManager.INSTANCE.removeShotDownListener(this.shutdownEventListener);
        try {
            if (UrlManager.INSTANCE.isTest()) {
                FloatingView.get().detach(this);
            }
        } catch (Exception e) {
            Pdlog.m3274e("MyBaseActivity", "onStop : " + Log.getStackTraceString(e));
        }
    }
}
