package com.pudutech.peanut.robot_ui.module.charging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.base.ScreenLedManager;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.CheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserCheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.CheckPermissionDialog;
import com.pudutech.peanut.robot_ui.p063ui.HomeActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.util.AppUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.widget.CircleImageView;
import com.pudutech.peanut.robot_ui.widget.random_layout.AnimatedRandomLayout;
import com.pudutech.peanut.robot_ui.widget.random_layout.WaveLoadingView;
import com.pudutech.robot.module.report.task.ReportCharging;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: RobotChargingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 52\u00020\u0001:\u00015B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0002J\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u001bJ\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0004H\u0002J1\u0010 \u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u00042\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010%J\u0012\u0010&\u001a\u00020\u001b2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u001bH\u0014J\b\u0010*\u001a\u00020\u001bH\u0014J\b\u0010+\u001a\u00020\u001bH\u0014J\b\u0010,\u001a\u00020\u001bH\u0002J\u0018\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0004H\u0002J\u0010\u00100\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\u001bH\u0002J\u0010\u00102\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u0004H\u0002J\b\u00103\u001a\u00020\u001bH\u0002J\b\u00104\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/charging/RobotChargingActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "FinishState", "", "PLAY_ERROR_VOICE", "TAG", "", "chargeReport", "Lcom/pudutech/robot/module/report/task/ReportCharging;", "checkPermissionDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/CheckPermissionDialog;", "currentPower", "errorTipTitleClickCount", "errorTipTitleClickTime", "", "isFinish", "", "isFromFreeInstall", "isLostLaserLocation", "isLostMarkerLocation", "isLostRunningLocation", "isNeedRecheckLocation", "mainHandler", "Landroid/os/Handler;", "state", "changeTitleIfNeed", "", "p", "dismissSoftKeyboard", "initView", "isChangeLight", "notifyBatteryInfo", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStart", "onStop", "playErrorVoice", "showChargerError", NotificationCompat.CATEGORY_MESSAGE, "code", "showChargerEvent", "showCheckPermissionDialog", "showLedLight", "showStopPileDialog", "stopErrorVoice", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotChargingActivity extends BatteryBaseActivity {
    public static final String FROM_FREE_INSTALL = "from_free_install";
    public static final String LOST_LOCATION_IN_LASER_CASE = "LOST_LOCATION_IN_LASER_CASE";
    public static final String LOST_LOCATION_IN_LASER_CASE_RUNNING = "LOST_LOCATION_IN_LASER_CASE_RUNNING";
    public static final String LOST_LOCATION_IN_MARKER_CASE = "LOST_LOCATION_IN_MARKER_CASE";
    public static final String NEED_RECHECK_LOCATION = "NEED_RECHECK_LOCATION";
    private int FinishState;
    private HashMap _$_findViewCache;
    private CheckPermissionDialog checkPermissionDialog;
    private int currentPower;
    private int errorTipTitleClickCount;
    private long errorTipTitleClickTime;
    private boolean isFinish;
    private boolean isFromFreeInstall;
    private boolean isLostLaserLocation;
    private boolean isLostMarkerLocation;
    private boolean isLostRunningLocation;
    private boolean isNeedRecheckLocation;
    private int state;
    private final String TAG = "RobotChargingActivity";
    private final int PLAY_ERROR_VOICE = 100;
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2;
            int i3 = message.what;
            i = RobotChargingActivity.this.PLAY_ERROR_VOICE;
            if (i3 != i) {
                return true;
            }
            i2 = RobotChargingActivity.this.FinishState;
            if (i2 != 0) {
                return true;
            }
            RobotChargingActivity.this.playErrorVoice();
            return true;
        }
    });
    private final ReportCharging chargeReport = new ReportCharging();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ChargeState.values().length];

        static {
            $EnumSwitchMapping$0[ChargeState.CharingUsePile.ordinal()] = 1;
            $EnumSwitchMapping$0[ChargeState.ChargeFullUsePile.ordinal()] = 2;
            $EnumSwitchMapping$0[ChargeState.Charging.ordinal()] = 3;
            $EnumSwitchMapping$0[ChargeState.ChargeFull.ordinal()] = 4;
            $EnumSwitchMapping$0[ChargeState.Idle.ordinal()] = 5;
            $EnumSwitchMapping$0[ChargeState.StopChargeUsePile.ordinal()] = 6;
            $EnumSwitchMapping$0[ChargeState.ChargeErrorContact.ordinal()] = 7;
            $EnumSwitchMapping$0[ChargeState.ErrorBatteryPackComm.ordinal()] = 8;
            $EnumSwitchMapping$0[ChargeState.ErrorOverVolt.ordinal()] = 9;
            $EnumSwitchMapping$0[ChargeState.ErrorOverElectric.ordinal()] = 10;
            $EnumSwitchMapping$0[ChargeState.ErrorOverTemperature.ordinal()] = 11;
            $EnumSwitchMapping$0[ChargeState.ErrorOverTime.ordinal()] = 12;
            $EnumSwitchMapping$0[ChargeState.ErrorChargePileFail.ordinal()] = 13;
        }
    }

    private final void showLedLight(int i) {
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChargeActivity(true);
        setContentView(C5508R.layout.activity_layout_charging);
        initView();
        dismissSoftKeyboard();
        ScreenLedManager.INSTANCE.stopScreen();
        this.state = getIntent().getIntExtra("state", 0);
        Pdlog.m3274e(this.TAG, "stopScreen activity: " + this);
    }

    public final void initView() {
        this.isNeedRecheckLocation = getIntent().getBooleanExtra("NEED_RECHECK_LOCATION", false);
        this.isLostMarkerLocation = getIntent().getBooleanExtra("LOST_LOCATION_IN_MARKER_CASE", false);
        this.isLostLaserLocation = getIntent().getBooleanExtra("LOST_LOCATION_IN_LASER_CASE", false);
        this.isLostRunningLocation = getIntent().getBooleanExtra("LOST_LOCATION_IN_LASER_CASE_RUNNING", false);
        this.isFromFreeInstall = getIntent().getBooleanExtra(FROM_FREE_INSTALL, false);
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).setRegularity(10, 10);
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).setItemShowCount(10);
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).setLooperDuration(2);
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).setDefaultDruation(2000);
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).setWaveLoadingView((WaveLoadingView) _$_findCachedViewById(C5508R.id.waveLoadingView));
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).setOnCreateItemViewListener(new AnimatedRandomLayout.OnCreateItemViewListener() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$initView$1
            @Override // com.pudutech.peanut.robot_ui.widget.random_layout.AnimatedRandomLayout.OnCreateItemViewListener
            public int getCount() {
                return 10;
            }

            @Override // com.pudutech.peanut.robot_ui.widget.random_layout.AnimatedRandomLayout.OnCreateItemViewListener
            public View createItemView(int position, View convertView) {
                if (convertView != null) {
                    return convertView;
                }
                CircleImageView circleImageView = new CircleImageView(RobotChargingActivity.this);
                circleImageView.setImageResource(C5508R.drawable.oldgreen);
                circleImageView.setColorFilter(RobotChargingActivity.this.getResources().getColor(C5508R.color.charging, null));
                return circleImageView;
            }
        });
        if (BatteryInfoManager.INSTANCE.getChargingType(BatteryInfoManager.INSTANCE.getCurrentBatteryEvent()) == BatteryInfoManager.ChargingType.ChargingPile) {
            RelativeLayout base_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.base_layout);
            Intrinsics.checkExpressionValueIsNotNull(base_layout, "base_layout");
            ViewExtKt.onSingleClick(base_layout, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$initView$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    RobotChargingActivity.this.showStopPileDialog();
                }
            });
        }
        this.chargeReport.recodeStartCharging();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        ((AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud)).start();
        this.isFinish = false;
        this.FinishState = 0;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AnimatedRandomLayout animatedRandomLayout = (AnimatedRandomLayout) _$_findCachedViewById(C5508R.id.rl_cloud);
        if (animatedRandomLayout != null) {
            animatedRandomLayout.stop();
        }
        this.FinishState = 1;
        Pdlog.m3274e(this.TAG, "startScreen activity: " + this);
        ScreenLedManager.INSTANCE.startScreen();
        this.mainHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        stopErrorVoice();
        this.FinishState = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showStopPileDialog() {
        String string = getString(C5508R.string.dialog_auto_charging_exit);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_auto_charging_exit)");
        showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$showStopPileDialog$1
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
                String str;
                str = RobotChargingActivity.this.TAG;
                Pdlog.m3273d(str, "showStopPileDialog dialog onSure");
                MirSdkManager.INSTANCE.suspendChargingUsingPile();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$showStopPileDialog$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }

    public final void dismissSoftKeyboard() {
        View peekDecorView = getWindow().peekDecorView();
        if (peekDecorView != null) {
            Object systemService = getSystemService("input_method");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            }
            ((InputMethodManager) systemService).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showChargerEvent(ChargeState model) {
        Pdlog.m3275i(this.TAG, "showChargerEvent model=" + model);
        ReportHelper.INSTANCE.reportCharge(model);
        switch (WhenMappings.$EnumSwitchMapping$0[model.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return;
            case 5:
            case 6:
                this.chargeReport.report();
                stopErrorVoice();
                if (this.isFinish) {
                    return;
                }
                this.isFinish = true;
                Pdlog.m3273d(this.TAG, "is second jump");
                if (this.isNeedRecheckLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isNeedRecheckLocation");
                    AnkoInternals.internalStartActivity(this, SelfCheckActivity.class, new Pair[0]);
                } else if (this.isLostMarkerLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isLostMarkerLocation");
                    if (this.state == 1) {
                        Intent intent = new Intent(this, (Class<?>) CheckLocationActivity.class);
                        intent.putExtra("state", 1);
                        startActivity(intent);
                    } else {
                        AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[0]);
                    }
                } else if (this.isLostLaserLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isLostLaserLocation state:" + this.state);
                    if (this.state == 1) {
                        Intent intent2 = new Intent(this, (Class<?>) LaserCheckLocationActivity.class);
                        intent2.putExtra("state", 1);
                        startActivity(intent2);
                    } else {
                        AnkoInternals.internalStartActivity(this, LaserCheckLocationActivity.class, new Pair[0]);
                    }
                } else if (this.isLostRunningLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isLostRunningLocation");
                    AnkoInternals.internalStartActivity(this, LaserRunningLocationLostActivity.class, new Pair[0]);
                } else if (!this.isFromFreeInstall) {
                    AnkoInternals.internalStartActivity(this, HomeActivity.class, new Pair[0]);
                }
                finish();
                return;
            case 7:
                String string = getString(C5508R.string.pdStr6_3);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr6_3)");
                showChargerError(string, 8001);
                return;
            case 8:
                String string2 = getString(C5508R.string.pdStr6_4);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr6_4)");
                showChargerError(string2, 8002);
                return;
            case 9:
                String string3 = getString(C5508R.string.pdStr6_5);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr6_5)");
                showChargerError(string3, 8003);
                return;
            case 10:
                String string4 = getString(C5508R.string.pdStr6_6);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr6_6)");
                showChargerError(string4, 8004);
                return;
            case 11:
                String string5 = getString(C5508R.string.pdStr6_7);
                Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.pdStr6_7)");
                showChargerError(string5, 8005);
                return;
            case 12:
                String string6 = getString(C5508R.string.pdStr6_8);
                Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.pdStr6_8)");
                showChargerError(string6, 8006);
                return;
            case 13:
                String string7 = getString(C5508R.string.go_charge_fail);
                Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.go_charge_fail)");
                showChargerError(string7, 8008);
                return;
            default:
                String string8 = getString(C5508R.string.pdStr6_9);
                Intrinsics.checkExpressionValueIsNotNull(string8, "getString(R.string.pdStr6_9)");
                showChargerError(string8, 8007);
                return;
        }
    }

    private final void showChargerError(String msg, int code) {
        showErrorTipDialog(msg + " ERROR:" + code, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$showChargerError$1
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
                RobotChargingActivity.this.showChargerEvent(BatteryInfoManager.INSTANCE.getCurrentBatteryEvent());
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$showChargerError$2
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
                long j;
                int i;
                int i2;
                long currentTimeMillis = System.currentTimeMillis();
                j = RobotChargingActivity.this.errorTipTitleClickTime;
                if (currentTimeMillis - j > 1000) {
                    RobotChargingActivity.this.errorTipTitleClickCount = 0;
                } else {
                    i = RobotChargingActivity.this.errorTipTitleClickCount;
                    if (i > 4) {
                        RobotChargingActivity.this.showCheckPermissionDialog();
                        RobotChargingActivity.this.errorTipTitleClickCount = 0;
                    } else {
                        RobotChargingActivity robotChargingActivity = RobotChargingActivity.this;
                        i2 = robotChargingActivity.errorTipTitleClickCount;
                        robotChargingActivity.errorTipTitleClickCount = i2 + 1;
                    }
                }
                RobotChargingActivity.this.errorTipTitleClickTime = System.currentTimeMillis();
            }
        });
        playErrorVoice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCheckPermissionDialog() {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new CheckPermissionDialog(this);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        CheckPermissionDialog checkPermissionDialog = this.checkPermissionDialog;
        if (checkPermissionDialog == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.charging.RobotChargingActivity$showCheckPermissionDialog$1
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
                str = RobotChargingActivity.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPermissionCheckResult " + z);
                if (z) {
                    checkPermissionDialog2 = RobotChargingActivity.this.checkPermissionDialog;
                    if (checkPermissionDialog2 != null) {
                        checkPermissionDialog2.dismiss();
                    }
                    if (SpUtils.get((Context) RobotChargingActivity.this, "isFactoryrobot_server_ac_key", false)) {
                        MirSdkManager.INSTANCE.closeAuthMirSdk();
                    }
                    AppUtil.startDebugFunction(RobotChargingActivity.this);
                }
            }
        });
        CheckPermissionDialog checkPermissionDialog2 = this.checkPermissionDialog;
        if (checkPermissionDialog2 == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playErrorVoice() {
        stopErrorVoice();
        PlaySound.playChargingVoice();
        this.mainHandler.sendEmptyMessageDelayed(this.PLAY_ERROR_VOICE, 15000L);
    }

    private final void stopErrorVoice() {
        this.mainHandler.removeMessages(this.PLAY_ERROR_VOICE);
        this.mainHandler.removeCallbacksAndMessages(null);
    }

    private final void changeTitleIfNeed(int p) {
        if (p != this.currentPower) {
            if (isChangeLight(p)) {
                showLedLight(p);
            }
            this.currentPower = p;
            if (this.currentPower >= 100) {
                TextView title_tv = (TextView) _$_findCachedViewById(C5508R.id.title_tv);
                Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
                title_tv.setText(getString(C5508R.string.pdStr6_10));
            } else {
                TextView title_tv2 = (TextView) _$_findCachedViewById(C5508R.id.title_tv);
                Intrinsics.checkExpressionValueIsNotNull(title_tv2, "title_tv");
                title_tv2.setText(getString(C5508R.string.pdStr6_1));
            }
        }
    }

    private final boolean isChangeLight(int p) {
        int i = this.currentPower;
        return i == 0 || (i <= 19 && p >= 20) || ((this.currentPower >= 20 && p <= 19) || ((this.currentPower <= 39 && p >= 40) || ((this.currentPower >= 40 && p <= 39) || ((this.currentPower <= 59 && p >= 60) || ((this.currentPower >= 60 && p <= 59) || ((this.currentPower <= 79 && p >= 80) || ((this.currentPower >= 80 && p <= 79) || ((this.currentPower == 100 && p <= 99) || (this.currentPower <= 99 && p == 100)))))))));
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state != 2) {
            if (state == 3 && model != null) {
                showChargerEvent(model);
                return;
            }
            return;
        }
        if (i != null) {
            i.intValue();
            Pdlog.m3275i(this.TAG, "showPowerChange =" + i);
            WaveLoadingView waveLoadingView = (WaveLoadingView) _$_findCachedViewById(C5508R.id.waveLoadingView);
            Intrinsics.checkExpressionValueIsNotNull(waveLoadingView, "waveLoadingView");
            waveLoadingView.setProgressValue(i.intValue());
            WaveLoadingView waveLoadingView2 = (WaveLoadingView) _$_findCachedViewById(C5508R.id.waveLoadingView);
            Intrinsics.checkExpressionValueIsNotNull(waveLoadingView2, "waveLoadingView");
            waveLoadingView2.setCenterTitle(String.valueOf(i.intValue()));
            changeTitleIfNeed(i.intValue());
        }
    }
}
