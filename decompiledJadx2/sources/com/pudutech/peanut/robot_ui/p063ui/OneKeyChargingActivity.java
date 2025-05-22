package com.pudutech.peanut.robot_ui.p063ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.enums.RobotState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.ChargeArriveState;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.OneKeyChargingActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.OnKeyChargingVm;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: OneKeyChargingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0085\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b*\u0001%\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010+\u001a\u00020\u0012H\u0002J\b\u0010,\u001a\u00020\u0012H\u0002J\b\u0010-\u001a\u00020\u0012H\u0002J\b\u0010.\u001a\u00020\u0012H\u0002J\b\u0010/\u001a\u00020\u0012H\u0002J\u0012\u00100\u001a\u00020\u00122\b\u00101\u001a\u0004\u0018\u000102H\u0016J1\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u00062\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u000f2\b\u00108\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u00109J\b\u0010:\u001a\u00020\u0012H\u0002J\u0012\u0010;\u001a\u00020\u00122\b\u0010<\u001a\u0004\u0018\u00010=H\u0014J\b\u0010>\u001a\u00020\u0012H\u0014J\u0010\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020\u0006H\u0002J\u0006\u0010A\u001a\u00020\u0012J\u0010\u0010B\u001a\u00020\u00122\u0006\u0010C\u001a\u00020\u0006H\u0002J\u0010\u0010D\u001a\u00020\u00122\u0006\u00104\u001a\u00020EH\u0002J\b\u0010F\u001a\u00020\u0012H\u0002J\b\u0010G\u001a\u00020\u0012H\u0002J\b\u0010H\u001a\u00020\u0012H\u0002J\b\u0010I\u001a\u00020\u0012H\u0002J\b\u0010J\u001a\u00020\u0012H\u0002J\b\u0010K\u001a\u00020\u0012H\u0002J\b\u0010L\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u0017\u001a4\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R\u0010\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/OneKeyChargingActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "currentPauseFeature", "handle", "Landroid/os/Handler;", "isRelease", "", "jumpMethod", "Lkotlin/Function0;", "", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "mCountDownTimer", "Landroid/os/CountDownTimer;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onLostLocationLostCallback", "oneKeyChargingVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/OnKeyChargingVm;", "getOneKeyChargingVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/OnKeyChargingVm;", "oneKeyChargingVm$delegate", "Lkotlin/Lazy;", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/OneKeyChargingActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/OneKeyChargingActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "bind", "genSchedulingDialog", "hideCountdownLayout", "initListener", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onArriveSuccess", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPauseFeatureChange", "type", "release", "setCountdown", "toInt", "showArriveErrorDialog", "Lcom/pudutech/mirsdkwrap/lib/enums/RobotState;", "showCancelDialog", "showCountdownLayout", "showOnTheWay", "showPause", "showStartChargingError", "startPauseCountDown", "stopPauseCountDown", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OneKeyChargingActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private boolean isRelease;
    private Function0<Unit> jumpMethod;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private SchedulingDialog schedulingDialog;
    private final String TAG = "OneKeyChargingActivity";

    /* renamed from: oneKeyChargingVm$delegate, reason: from kotlin metadata */
    private final Lazy oneKeyChargingVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(OnKeyChargingVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();
    private final Handler handle = new Handler();
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$onLostLocationLostCallback$1
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
            OnKeyChargingVm oneKeyChargingVm;
            String str2;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i;
            String str3;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            str = OneKeyChargingActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker) {
                str3 = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str3, "onLostLocationLostCallback");
                locationLostDialog3 = OneKeyChargingActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    OneKeyChargingActivity oneKeyChargingActivity = OneKeyChargingActivity.this;
                    oneKeyChargingActivity.locationLostDialog = new LocationLostDialog(oneKeyChargingActivity);
                }
                locationLostDialog4 = OneKeyChargingActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                OneKeyChargingActivity oneKeyChargingActivity2 = OneKeyChargingActivity.this;
                i2 = oneKeyChargingActivity2.TYPE_PAUSE_FEATURE_ERROR;
                oneKeyChargingActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                OneKeyChargingActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$onLostLocationLostCallback$1.1
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
                        OneKeyChargingActivity.this.jumpAndFinish(new Intent(OneKeyChargingActivity.this, (Class<?>) LaserRunningLocationLostActivity.class));
                    }
                };
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                oneKeyChargingVm.cancel();
            } else if (locateCase == LocateCase.LaserMark) {
                str2 = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = OneKeyChargingActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    OneKeyChargingActivity oneKeyChargingActivity3 = OneKeyChargingActivity.this;
                    oneKeyChargingActivity3.locationLostDialog = new LocationLostDialog(oneKeyChargingActivity3, "1");
                }
                locationLostDialog2 = OneKeyChargingActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                OneKeyChargingActivity oneKeyChargingActivity4 = OneKeyChargingActivity.this;
                i = oneKeyChargingActivity4.TYPE_PAUSE_FEATURE_ERROR;
                oneKeyChargingActivity4.onPauseFeatureChange(i);
            }
        }
    };
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$onErrorDialogShowStatus$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Boolean bool2) {
            invoke(bool.booleanValue(), bool2.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z, boolean z2) {
            String str;
            int i;
            str = OneKeyChargingActivity.this.TAG;
            Pdlog.m3273d(str, "onErrorDialogShowStatus : show = " + z + "; ");
            if (z) {
                OneKeyChargingActivity oneKeyChargingActivity = OneKeyChargingActivity.this;
                i = oneKeyChargingActivity.TYPE_PAUSE_FEATURE_ERROR;
                oneKeyChargingActivity.onPauseFeatureChange(i);
            } else if (z2) {
                OneKeyChargingActivity.this.finish();
            }
        }
    };
    private final OneKeyChargingActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            OnKeyChargingVm oneKeyChargingVm;
            str = OneKeyChargingActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (left > 0.05d || right > 0.05d) {
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                if (oneKeyChargingVm.getMoveStatusState().getValue() == OnKeyChargingVm.MoveStatus.Pause) {
                    OneKeyChargingActivity.this.startPauseCountDown();
                }
            }
        }
    };
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_AIVOICE = 2;
    private final int TYPE_PAUSE_FEATURE_TOUCH = 3;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private int currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RobotState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[RobotState.Success.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[OnKeyChargingVm.MoveStatus.values().length];
            $EnumSwitchMapping$1[OnKeyChargingVm.MoveStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$1[OnKeyChargingVm.MoveStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$1[OnKeyChargingVm.MoveStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$1[OnKeyChargingVm.MoveStatus.Finish.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[RobotState.values().length];
            $EnumSwitchMapping$2[RobotState.FailTrack.ordinal()] = 1;
            $EnumSwitchMapping$2[RobotState.FailOverTime.ordinal()] = 2;
            $EnumSwitchMapping$2[RobotState.FailStuck.ordinal()] = 3;
            $EnumSwitchMapping$2[RobotState.Success.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnKeyChargingVm getOneKeyChargingVm() {
        return (OnKeyChargingVm) this.oneKeyChargingVm.getValue();
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$robotSpeedListener$1] */
    public OneKeyChargingActivity() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_one_key_charging);
        initView();
        bind();
        initListener();
        showOnTheWay();
    }

    private final void bind() {
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$bind$1
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
                OnKeyChargingVm oneKeyChargingVm;
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                oneKeyChargingVm.active();
            }
        });
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
    }

    private final void initListener() {
        OneKeyChargingActivity oneKeyChargingActivity = this;
        getOneKeyChargingVm().getMoveStatusState().observe(oneKeyChargingActivity, new Observer<OnKeyChargingVm.MoveStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$initListener$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(OnKeyChargingVm.MoveStatus moveStatus) {
                boolean z;
                String str;
                LocationLostDialog locationLostDialog;
                Function0 function0;
                Function0 function02;
                String str2;
                OnKeyChargingVm oneKeyChargingVm;
                OnKeyChargingVm oneKeyChargingVm2;
                OnKeyChargingVm oneKeyChargingVm3;
                z = OneKeyChargingActivity.this.isRelease;
                if (z) {
                    return;
                }
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "moveStatusState " + moveStatus);
                if (moveStatus == null) {
                    return;
                }
                int i = OneKeyChargingActivity.WhenMappings.$EnumSwitchMapping$1[moveStatus.ordinal()];
                if (i == 1) {
                    OneKeyChargingActivity.this.showOnTheWay();
                    return;
                }
                if (i == 2) {
                    locationLostDialog = OneKeyChargingActivity.this.locationLostDialog;
                    if (locationLostDialog != null) {
                        locationLostDialog.dismiss();
                    }
                    OneKeyChargingActivity.this.showPause();
                    return;
                }
                if (i == 3) {
                    function0 = OneKeyChargingActivity.this.jumpMethod;
                    if (function0 != null) {
                        function02 = OneKeyChargingActivity.this.jumpMethod;
                        if (function02 != null) {
                            return;
                        }
                        return;
                    }
                    OneKeyChargingActivity.this.jumpAndFinish(new Intent(OneKeyChargingActivity.this, (Class<?>) HomeActivity.class));
                    return;
                }
                if (i != 4) {
                    return;
                }
                str2 = OneKeyChargingActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("moveStatusState finish ");
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                sb.append(oneKeyChargingVm.getArriveState());
                Pdlog.m3273d(str2, sb.toString());
                oneKeyChargingVm2 = OneKeyChargingActivity.this.getOneKeyChargingVm();
                ChargeArriveState arriveState = oneKeyChargingVm2.getArriveState();
                RobotState modeState = arriveState != null ? arriveState.getModeState() : null;
                if (modeState == null || OneKeyChargingActivity.WhenMappings.$EnumSwitchMapping$0[modeState.ordinal()] == 1) {
                    OneKeyChargingActivity.this.onArriveSuccess();
                    return;
                }
                OneKeyChargingActivity oneKeyChargingActivity2 = OneKeyChargingActivity.this;
                oneKeyChargingVm3 = oneKeyChargingActivity2.getOneKeyChargingVm();
                ChargeArriveState arriveState2 = oneKeyChargingVm3.getArriveState();
                RobotState modeState2 = arriveState2 != null ? arriveState2.getModeState() : null;
                if (modeState2 == null) {
                    Intrinsics.throwNpe();
                }
                oneKeyChargingActivity2.showArriveErrorDialog(modeState2);
            }
        });
        getOneKeyChargingVm().getMoveErrorHelperLiveData().observe(oneKeyChargingActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$initListener$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                boolean z;
                RunningErrorHelper runningErrorHelper;
                z = OneKeyChargingActivity.this.isRelease;
                if (z || moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = OneKeyChargingActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getOneKeyChargingVm().getOnSchedulingLiveData().observe(oneKeyChargingActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$initListener$3
            /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
            
                r6 = r5.this$0.schedulingDialog;
             */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onChanged(Boolean it) {
                String str;
                SchedulingDialog schedulingDialog;
                SchedulingDialog schedulingDialog2;
                SchedulingDialog schedulingDialog3;
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    OneKeyChargingActivity.this.genSchedulingDialog();
                    schedulingDialog3 = OneKeyChargingActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = OneKeyChargingActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(C5508R.id.mainLayout)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$initListener$4
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
                OnKeyChargingVm oneKeyChargingVm;
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "on pause click ");
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                oneKeyChargingVm.active();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$initListener$5
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
                OnKeyChargingVm oneKeyChargingVm;
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "on_the_way_layout click ");
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                oneKeyChargingVm.pause();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.cancel_task)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$initListener$6
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
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "cancel_task click ");
                OneKeyChargingActivity.this.showCancelDialog();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onArriveSuccess() {
        TextView tvArrive = (TextView) _$_findCachedViewById(C5508R.id.tvArrive);
        Intrinsics.checkExpressionValueIsNotNull(tvArrive, "tvArrive");
        tvArrive.setText(getString(C5508R.string.one_key_charging_start_charging));
        this.handle.postDelayed(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$onArriveSuccess$1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                if (OneKeyChargingActivity.this.isFinishing()) {
                    return;
                }
                z = OneKeyChargingActivity.this.isRelease;
                if (z) {
                    return;
                }
                OneKeyChargingActivity.this.showStartChargingError();
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showStartChargingError() {
        String string = getString(C5508R.string.start_charging_timeout);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.start_charging_timeout)");
        MyBaseActivity.showErrorTipDialog$default(this, string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$showStartChargingError$1
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
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "showArriveErrorDialog onDismissCallback ");
                OneKeyChargingActivity oneKeyChargingActivity = OneKeyChargingActivity.this;
                oneKeyChargingActivity.jumpAndFinish(new Intent(oneKeyChargingActivity, (Class<?>) HomeActivity.class));
            }
        }, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCancelDialog() {
        onPauseFeatureChange(this.TYPE_PAUSE_FEATURE_DIALOG);
        String string = getString(C5508R.string.cancel_task_dialog_msg);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.cancel_task_dialog_msg)");
        showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$showCancelDialog$1
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
                OnKeyChargingVm oneKeyChargingVm;
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "onCancelCruiseClick dialog onSure");
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                oneKeyChargingVm.cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$showCancelDialog$2
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
                int i;
                OneKeyChargingActivity oneKeyChargingActivity = OneKeyChargingActivity.this;
                i = oneKeyChargingActivity.TYPE_PAUSE_FEATURE_NORMAL;
                oneKeyChargingActivity.onPauseFeatureChange(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showArriveErrorDialog(RobotState state) {
        VoicePlayTasks.INSTANCE.stop();
        int i = WhenMappings.$EnumSwitchMapping$2[state.ordinal()];
        String str = "";
        if (i == 1) {
            str = getString(C5508R.string.one_key_failed_track);
        } else if (i == 2) {
            str = getString(C5508R.string.one_key_failed_track);
        } else if (i == 3) {
            str = getString(C5508R.string.one_key_failed_not_fined);
        }
        String msg = str;
        Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
        MyBaseActivity.showErrorTipDialog$default(this, msg, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$showArriveErrorDialog$1
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
                String str2;
                str2 = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str2, "showArriveErrorDialog onDismissCallback ");
                OneKeyChargingActivity oneKeyChargingActivity = OneKeyChargingActivity.this;
                oneKeyChargingActivity.jumpAndFinish(new Intent(oneKeyChargingActivity, (Class<?>) HomeActivity.class));
            }
        }, null, 4, null);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        VoicePlayTasks.INSTANCE.stop();
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPause() {
        VoicePlayTasks.INSTANCE.stop();
        Pdlog.m3273d(this.TAG, "showPause ");
        startPauseCountDown();
        LinearLayout on_the_way_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(8);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWay() {
        Pdlog.m3273d(this.TAG, "showOnTheWay ");
        LinearLayout on_the_way_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(0);
        stopPauseCountDown();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.playAnimation();
        }
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
    }

    public final void release() {
        this.isRelease = true;
        this.handle.removeCallbacksAndMessages(null);
        this.runningErrorHelper.unbind();
        stopPauseCountDown();
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        if (!getOneKeyChargingVm().isNotErrorMove()) {
            hideCountdownLayout();
            return;
        }
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mCountDownTimer = (CountDownTimer) null;
        }
        LinearLayout on_the_way_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        if (on_the_way_layout.getVisibility() == 8) {
            TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            if (countdown_tv.getVisibility() != 0) {
                return;
            }
        }
        final long notCruisePauseKeepTime_ms = BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms();
        final long j = 1000;
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                OnKeyChargingVm oneKeyChargingVm;
                oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                if (oneKeyChargingVm != null) {
                    oneKeyChargingVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String str;
                str = OneKeyChargingActivity.this.TAG;
                Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                OneKeyChargingActivity.this.setCountdown((int) Math.ceil(((double) millisUntilFinished) / ((double) 1000)));
            }
        };
        CountDownTimer countDownTimer2 = this.mCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCountdown(int toInt) {
        showCountdownLayout();
        TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C5508R.string.puase_countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.puase_countdown_tv)");
        Object[] objArr = {Integer.valueOf(toInt)};
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        countdown_tv.setText(format);
    }

    private final void stopPauseCountDown() {
        hideCountdownLayout();
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mCountDownTimer = (CountDownTimer) null;
        }
    }

    private final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(8);
    }

    private final void showCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            showCountdownLayout();
            startPauseCountDown();
        } else if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            stopPauseCountDown();
        } else if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            stopPauseCountDown();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            stopPauseCountDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void genSchedulingDialog() {
        if (this.schedulingDialog == null) {
            Pdlog.m3273d(this.TAG, "genSchedulingDialog ");
            this.schedulingDialog = new SchedulingDialog(this);
            SchedulingDialog schedulingDialog = this.schedulingDialog;
            if (schedulingDialog == null) {
                Intrinsics.throwNpe();
            }
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.OneKeyChargingActivity$genSchedulingDialog$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
                
                    r0 = r2.this$0.schedulingDialog;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2() {
                    SchedulingDialog schedulingDialog2;
                    OnKeyChargingVm oneKeyChargingVm;
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = OneKeyChargingActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    oneKeyChargingVm = OneKeyChargingActivity.this.getOneKeyChargingVm();
                    oneKeyChargingVm.pause();
                }
            });
        }
    }
}
