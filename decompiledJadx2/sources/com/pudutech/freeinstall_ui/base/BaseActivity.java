package com.pudutech.freeinstall_ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.dialog.CheckPermissionDialog;
import com.pudutech.freeinstall_ui.dialog.CloseAppDialog;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.dialog.LoadingDialog;
import com.pudutech.freeinstall_ui.utils.AppUtil;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.module_project_common.statusBar.StatusBaseActivity;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.common.RobotShutDownManager;
import com.pudutech.robot.peripherals.common.ShutDownHelper;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.hola.HolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: BaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010*\u0001.\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u00100\u001a\u00020'J\b\u00101\u001a\u00020'H\u0016J\u0006\u00102\u001a\u00020'J\u0012\u00103\u001a\u00020\n2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0012\u00106\u001a\u00020\n2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u00109\u001a\u00020'H\u0016J\u0012\u0010:\u001a\u00020'2\b\u0010;\u001a\u0004\u0018\u00010<H\u0014J\b\u0010=\u001a\u00020'H\u0014J\b\u0010>\u001a\u00020'H\u0014J\b\u0010?\u001a\u00020'H\u0014J\b\u0010@\u001a\u00020'H\u0014J\b\u0010A\u001a\u00020'H\u0002J\u0010\u0010B\u001a\u00020'2\u0006\u0010C\u001a\u00020\u0006H\u0002J\u0006\u0010D\u001a\u00020'J\b\u0010E\u001a\u00020'H\u0002J\u0010\u0010F\u001a\u00020'2\u0006\u0010G\u001a\u00020\u0006H\u0016J\u001a\u0010H\u001a\u00020'2\b\b\u0002\u0010G\u001a\u00020\u00062\b\b\u0002\u0010I\u001a\u00020\nJ\b\u0010J\u001a\u00020'H\u0002J\b\u0010K\u001a\u00020'H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000Rä\u0001\u0010(\u001ab\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\n¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020'\u0018\u00010\u001f2f\u0010\u001e\u001ab\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\n¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020'\u0018\u00010\u001f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/¨\u0006L"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/base/BaseActivity;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Lcom/pudutech/module_project_common/statusBar/StatusBaseActivity;", "()V", "TAG", "", "checkPermissionDialog", "Lcom/pudutech/freeinstall_ui/dialog/CheckPermissionDialog;", "isDev", "", "isOnResume", "()Z", "setOnResume", "(Z)V", "isShutDown", "lastHitTimestamp", "", "loadingDialog", "Lcom/pudutech/freeinstall_ui/dialog/LoadingDialog;", "getLoadingDialog", "()Lcom/pudutech/freeinstall_ui/dialog/LoadingDialog;", "setLoadingDialog", "(Lcom/pudutech/freeinstall_ui/dialog/LoadingDialog;)V", "lowPowerDialog", "Lcom/pudutech/freeinstall_ui/dialog/CommonDialog;", "mDevHitCountdown", "", "mShutDownDialog", "Lcom/pudutech/freeinstall_ui/dialog/CloseAppDialog;", ES6Iterator.VALUE_PROPERTY, "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "state", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "model", "isCharging", "i", "", "notifyBatteryInfo", "getNotifyBatteryInfo", "()Lkotlin/jvm/functions/Function4;", "setNotifyBatteryInfo", "(Lkotlin/jvm/functions/Function4;)V", "shutdownEventListener", "com/pudutech/freeinstall_ui/base/BaseActivity$shutdownEventListener$1", "Lcom/pudutech/freeinstall_ui/base/BaseActivity$shutdownEventListener$1;", "addShutDownListener", "dismissLoading", "dismissLoadingDialog", "dispatchKeyEvent", "event", "Landroid/view/KeyEvent;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "jumpChargeActivity", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStop", "openDevMode", "reboot", "reason", "removeShutDownListener", "showCheckPermissionDialog", "showLoading", "message", "showLoadingDialog", "closeVisible", "showLowPowerDialog", "showShutDownDialog", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class BaseActivity<T extends BaseViewModel> extends StatusBaseActivity<T> {
    private HashMap _$_findViewCache;
    private CheckPermissionDialog checkPermissionDialog;
    private boolean isDev;
    private boolean isShutDown;
    private long lastHitTimestamp;
    private LoadingDialog loadingDialog;
    private CommonDialog lowPowerDialog;
    private CloseAppDialog mShutDownDialog;
    private final String TAG = "BaseActivity";
    private boolean isOnResume = true;
    private final BaseActivity$shutdownEventListener$1 shutdownEventListener = new RobotShutDownManager.ShutDownListener() { // from class: com.pudutech.freeinstall_ui.base.BaseActivity$shutdownEventListener$1
        @Override // com.pudutech.robot.peripherals.common.RobotShutDownManager.ShutDownListener
        public void shutDownNotify() {
            BaseActivity.this.showShutDownDialog();
        }
    };
    private int mDevHitCountdown = 17;

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
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

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void dismissLoading() {
    }

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public void setNotifyBatteryInfo(Function4<? super Integer, ? super ChargeState, ? super Boolean, ? super Integer, Unit> function4) {
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void showLoading(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    public final LoadingDialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public final void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    /* renamed from: isOnResume, reason: from getter */
    public final boolean getIsOnResume() {
        return this.isOnResume;
    }

    public final void setOnResume(boolean z) {
        this.isOnResume = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KtxFreeInstallActivityManager.INSTANCE.pushActivity(this);
    }

    public static /* synthetic */ void showLoadingDialog$default(BaseActivity baseActivity, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showLoadingDialog");
        }
        if ((i & 1) != 0) {
            str = baseActivity.getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(str, "getString(R.string.tips)");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        baseActivity.showLoadingDialog(str, z);
    }

    public final void showLoadingDialog(String message, boolean closeVisible) {
        Dialog dialog;
        Intrinsics.checkParameterIsNotNull(message, "message");
        Pdlog.m3273d(this.TAG, "showLoadingDialog " + message);
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog();
        }
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.setTitle(message);
        }
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 != null) {
            loadingDialog2.closeVisible(closeVisible);
        }
        LoadingDialog loadingDialog3 = this.loadingDialog;
        if (loadingDialog3 == null || (dialog = loadingDialog3.getDialog()) == null || !dialog.isShowing()) {
            LoadingDialog loadingDialog4 = this.loadingDialog;
            if ((loadingDialog4 != null && loadingDialog4.isAdded()) || isFinishing() || isDestroyed()) {
                return;
            }
            Pdlog.m3273d(this.TAG, "showLoadingDialog loading");
            LoadingDialog loadingDialog5 = this.loadingDialog;
            if (loadingDialog5 != null) {
                loadingDialog5.showDialog(getSupportFragmentManager(), "loading");
            }
        }
    }

    public final void dismissLoadingDialog() {
        Dialog dialog;
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null && (dialog = loadingDialog.getDialog()) != null && dialog.isShowing()) {
            Pdlog.m3273d(this.TAG, "dismissLoadingDialog");
            LoadingDialog loadingDialog2 = this.loadingDialog;
            if (loadingDialog2 != null) {
                loadingDialog2.dismissDialog();
            }
        }
        this.loadingDialog = (LoadingDialog) null;
    }

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public void jumpChargeActivity() {
        super.jumpChargeActivity();
        if (this.isOnResume) {
            Pdlog.m3273d(this.TAG, "jumpChargeActivity");
            AppContext.StartActivityListener listener = AppContext.INSTANCE.getListener();
            if (listener != null) {
                listener.startToChargingActivity(AppContext.INSTANCE.getContext());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.isOnResume = true;
        addShutDownListener();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        CloseAppDialog closeAppDialog;
        super.onStop();
        this.isOnResume = false;
        CloseAppDialog closeAppDialog2 = this.mShutDownDialog;
        if (closeAppDialog2 != null && closeAppDialog2.isShowing() && (closeAppDialog = this.mShutDownDialog) != null) {
            closeAppDialog.dismiss();
        }
        this.mShutDownDialog = (CloseAppDialog) null;
        removeShutDownListener();
    }

    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity
    public Function4<Integer, ChargeState, Boolean, Integer, Unit> getNotifyBatteryInfo() {
        return new Function4<Integer, ChargeState, Boolean, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.base.BaseActivity$notifyBatteryInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, ChargeState chargeState, Boolean bool, Integer num2) {
                invoke(num.intValue(), chargeState, bool.booleanValue(), num2);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, ChargeState chargeState, boolean z, Integer num) {
                if (BaseActivity.this.getIsOnResume() && i == 1 && !z) {
                    BaseActivity.this.showLowPowerDialog();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLowPowerDialog() {
        if (isFinishing() || isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showLowPowerDialog failed , ac is finish");
            return;
        }
        CommonDialog commonDialog = this.lowPowerDialog;
        if (commonDialog == null || !commonDialog.isShowing()) {
            if (this.lowPowerDialog == null) {
                CommonDialog.Builder builder = new CommonDialog.Builder(this);
                String string = getString(C5362R.string.tips);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips)");
                CommonDialog.Builder title = builder.setTitle(string);
                String string2 = getString(C5362R.string.low_power);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.low_power)");
                CommonDialog.Builder minContent = title.setMinContent(string2);
                String string3 = getString(C5362R.string.confirm_free);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.confirm_free)");
                this.lowPowerDialog = minContent.setBtRight(string3).setClose(false).create();
                final CommonDialog commonDialog2 = this.lowPowerDialog;
                if (commonDialog2 != null) {
                    commonDialog2.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.base.BaseActivity$showLowPowerDialog$$inlined$let$lambda$1
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
                            String str;
                            CommonDialog.this.dismiss();
                            str = this.TAG;
                            Pdlog.m3273d(str, "showLowPowerDialog:btRightClick");
                        }
                    });
                }
            }
            CommonDialog commonDialog3 = this.lowPowerDialog;
            if (commonDialog3 != null) {
                commonDialog3.show();
            }
        }
    }

    public final void addShutDownListener() {
        RobotShutDownManager.INSTANCE.addShutDownListener(this.shutdownEventListener);
    }

    public final void removeShutDownListener() {
        RobotShutDownManager.INSTANCE.removeShotDownListener(this.shutdownEventListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showShutDownDialog() {
        if (isFinishing() || isDestroyed()) {
            Pdlog.m3274e(this.TAG, "showShutDownDialog failed , ac is finish");
            return;
        }
        if (this.mShutDownDialog == null) {
            this.mShutDownDialog = new CloseAppDialog(this);
            CloseAppDialog closeAppDialog = this.mShutDownDialog;
            if (closeAppDialog != null) {
                closeAppDialog.setOnShutDownClick(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.base.BaseActivity$showShutDownDialog$1
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
                        Object obj;
                        closeAppDialog2 = BaseActivity.this.mShutDownDialog;
                        if (closeAppDialog2 != null) {
                            closeAppDialog2.dismiss();
                        }
                        if (z) {
                            str = BaseActivity.this.TAG;
                            Pdlog.m3273d(str, "onShutdownConfirm ");
                            if (!ShutDownHelper.INSTANCE.doCaseByReflect()) {
                                ShutDownHelper.INSTANCE.doCaseBySimulateInputEvent(AppContext.INSTANCE.getContext());
                            }
                            RobotPeripheralsFactory robotPeripheralsFactory = RobotPeripheralsFactory.INSTANCE;
                            if (Intrinsics.areEqual(IPeanutRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
                                Object instance = FirefoxRobotPeripherals.Companion.getINSTANCE();
                                if (instance == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
                                }
                                obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance);
                            } else if (Intrinsics.areEqual(IPeanutRobotPeripherals.class, IPeanutRobotPeripherals.class)) {
                                Object instance2 = PeanutRobotPeripherals.Companion.getINSTANCE();
                                if (instance2 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
                                }
                                obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance2);
                            } else if (Intrinsics.areEqual(IPeanutRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
                                Object instance3 = DisinfectRobotPeripherals.Companion.getINSTANCE();
                                if (instance3 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
                                }
                                obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance3);
                            } else {
                                if (!Intrinsics.areEqual(IPeanutRobotPeripherals.class, IHolaBotPeripherals.class)) {
                                    throw new IllegalArgumentException("getRobotPeripherals " + IPeanutRobotPeripherals.class + " not find ");
                                }
                                Object instance4 = HolaBotPeripherals.Companion.getINSTANCE();
                                if (instance4 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
                                }
                                obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance4);
                            }
                            ((IPeanutRobotPeripherals) obj).shutdown();
                            return;
                        }
                        BaseActivity.this.reboot("chongqi");
                    }
                });
            }
        }
        CloseAppDialog closeAppDialog2 = this.mShutDownDialog;
        if (closeAppDialog2 != null) {
            closeAppDialog2.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.module_project_common.statusBar.StatusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonDialog commonDialog;
        CloseAppDialog closeAppDialog;
        super.onDestroy();
        dismissLoadingDialog();
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.release();
        }
        this.loadingDialog = (LoadingDialog) null;
        CloseAppDialog closeAppDialog2 = this.mShutDownDialog;
        if (closeAppDialog2 != null && closeAppDialog2.isShowing() && (closeAppDialog = this.mShutDownDialog) != null) {
            closeAppDialog.dismiss();
        }
        this.mShutDownDialog = (CloseAppDialog) null;
        CommonDialog commonDialog2 = this.lowPowerDialog;
        if (commonDialog2 != null && commonDialog2.isShowing() && (commonDialog = this.lowPowerDialog) != null) {
            commonDialog.dismiss();
        }
        this.lowPowerDialog = (CommonDialog) null;
        KtxFreeInstallActivityManager.INSTANCE.popActivity(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event == null || event.getKeyCode() != 4) {
            return super.dispatchKeyEvent(event);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Integer valueOf = ev != null ? Integer.valueOf(ev.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            openDevMode();
        }
        return super.dispatchTouchEvent(ev);
    }

    private final void openDevMode() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.lastHitTimestamp;
        if (elapsedRealtime - j > 500 && j != 0) {
            this.mDevHitCountdown = 17;
            this.lastHitTimestamp = SystemClock.elapsedRealtime();
            return;
        }
        this.lastHitTimestamp = SystemClock.elapsedRealtime();
        if (this.isDev) {
            this.mDevHitCountdown = 17;
            this.isDev = false;
        }
        int i = this.mDevHitCountdown;
        if (i > 0) {
            this.mDevHitCountdown = i - 1;
            int i2 = this.mDevHitCountdown;
            if (i2 == 0) {
                Pdlog.m3277w(this.TAG, "go to debug activity");
                showCheckPermissionDialog();
                this.isDev = true;
            } else {
                if (i2 <= 0 || i2 >= 5) {
                    return;
                }
                Pdlog.m3277w(this.TAG, "go to debug activity");
            }
        }
    }

    private final void showCheckPermissionDialog() {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new CheckPermissionDialog(this);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        CheckPermissionDialog checkPermissionDialog = this.checkPermissionDialog;
        if (checkPermissionDialog == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.freeinstall_ui.base.BaseActivity$showCheckPermissionDialog$1
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
                String str;
                CheckPermissionDialog checkPermissionDialog2;
                str = BaseActivity.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPermissionCheckResult " + z);
                if (z) {
                    checkPermissionDialog2 = BaseActivity.this.checkPermissionDialog;
                    if (checkPermissionDialog2 != null) {
                        checkPermissionDialog2.dismiss();
                    }
                    if (SpUtils.get((Context) BaseActivity.this, "isFactoryrobot_server_ac_key", false)) {
                        MirSdkManager.INSTANCE.closeAuthMirSdk();
                    }
                    AppUtil.startDebugFunction(BaseActivity.this);
                }
            }
        });
        if (isFinishing() || isDestroyed()) {
            return;
        }
        CheckPermissionDialog checkPermissionDialog2 = this.checkPermissionDialog;
        if (checkPermissionDialog2 == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog2.show();
    }
}
