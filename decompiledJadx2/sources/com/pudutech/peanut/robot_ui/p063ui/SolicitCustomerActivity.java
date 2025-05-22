package com.pudutech.peanut.robot_ui.p063ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.greeter.GreeterMenuActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.SolicitViewModel;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: SolicitCustomerActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010*\u0001\u001f\u0018\u0000 E2\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010,\u001a\u00020\u0010H\u0002J\b\u0010-\u001a\u00020\u0010H\u0002J\b\u0010.\u001a\u00020\u0010H\u0002J\b\u0010/\u001a\u00020\u0010H\u0002J\b\u00100\u001a\u00020\u0010H\u0002J\u0012\u00101\u001a\u00020\u00102\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0012\u00104\u001a\u00020\u00102\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u00107\u001a\u00020\u0010H\u0014J\u0010\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u0006H\u0002J\u0006\u0010:\u001a\u00020\u0010J\u0010\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u0006H\u0002J\b\u0010=\u001a\u00020\u0010H\u0002J\b\u0010>\u001a\u00020\u0010H\u0002J\b\u0010?\u001a\u00020\u0010H\u0002J\b\u0010@\u001a\u00020\u0010H\u0002J\b\u0010A\u001a\u00020\u0010H\u0002J\b\u0010B\u001a\u00020\u0010H\u0002J\b\u0010C\u001a\u00020\u0010H\u0002J\b\u0010D\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u0017\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b'\u0010(R\u000e\u0010+\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/SolicitCustomerActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "currentPauseFeature", "isRelease", "", "jumpMethod", "Lkotlin/Function0;", "", "jumpState", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "mCountDownTimer", "Landroid/os/CountDownTimer;", "mFinishDownTimer", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onLostLocationLostCallback", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/SolicitCustomerActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/SolicitCustomerActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "solicitVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel;", "getSolicitVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel;", "solicitVm$delegate", "Lkotlin/Lazy;", "state", "bind", "genSchedulingDialog", "hideCountdownLayout", "initListener", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPauseFeatureChange", "type", "release", "setCountdown", "toInt", "setStateModel", "showArrived", "showCancelDialog", "showCountdownLayout", "showOnTheWay", "showPause", "startPauseCountDown", "stopPauseCountDown", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SolicitCustomerActivity extends MyBaseActivity {
    public static final String INTENT_JUMP_STATE = "jumpSate";
    public static final String INTENT_STATE = "state";
    private HashMap _$_findViewCache;
    private boolean isRelease;
    private Function0<Unit> jumpMethod;
    private int jumpState;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer mFinishDownTimer;
    private SchedulingDialog schedulingDialog;
    private int state;
    private final String TAG = "SolicitCustomerActivity";

    /* renamed from: solicitVm$delegate, reason: from kotlin metadata */
    private final Lazy solicitVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(SolicitViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$$special$$inlined$viewModels$1
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
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$onLostLocationLostCallback$1
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
            SolicitViewModel solicitVm;
            String str2;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i;
            String str3;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            final LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            str = SolicitCustomerActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker) {
                str3 = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str3, "onLostLocationLostCallback");
                locationLostDialog3 = SolicitCustomerActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    SolicitCustomerActivity solicitCustomerActivity = SolicitCustomerActivity.this;
                    solicitCustomerActivity.locationLostDialog = new LocationLostDialog(solicitCustomerActivity);
                }
                locationLostDialog4 = SolicitCustomerActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                SolicitCustomerActivity solicitCustomerActivity2 = SolicitCustomerActivity.this;
                i2 = solicitCustomerActivity2.TYPE_PAUSE_FEATURE_ERROR;
                solicitCustomerActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                solicitVm = SolicitCustomerActivity.this.getSolicitVm();
                solicitVm.cancel();
                SolicitCustomerActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$onLostLocationLostCallback$1.1
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
                        String str4;
                        str4 = SolicitCustomerActivity.this.TAG;
                        Pdlog.m3273d(str4, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                        SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) LaserRunningLocationLostActivity.class));
                    }
                };
            } else if (locateCase == LocateCase.LaserMark) {
                str2 = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = SolicitCustomerActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    SolicitCustomerActivity solicitCustomerActivity3 = SolicitCustomerActivity.this;
                    solicitCustomerActivity3.locationLostDialog = new LocationLostDialog(solicitCustomerActivity3, "1");
                }
                locationLostDialog2 = SolicitCustomerActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                SolicitCustomerActivity solicitCustomerActivity4 = SolicitCustomerActivity.this;
                i = solicitCustomerActivity4.TYPE_PAUSE_FEATURE_ERROR;
                solicitCustomerActivity4.onPauseFeatureChange(i);
            }
        }
    };
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$onErrorDialogShowStatus$1
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
            str = SolicitCustomerActivity.this.TAG;
            Pdlog.m3273d(str, "onErrorDialogShowStatus : show = " + z + "; ");
            if (z) {
                SolicitCustomerActivity solicitCustomerActivity = SolicitCustomerActivity.this;
                i = solicitCustomerActivity.TYPE_PAUSE_FEATURE_ERROR;
                solicitCustomerActivity.onPauseFeatureChange(i);
            } else if (z2) {
                SolicitCustomerActivity.this.finish();
            }
        }
    };
    private final SolicitCustomerActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            SolicitViewModel solicitVm;
            SolicitViewModel solicitVm2;
            str = SolicitCustomerActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                solicitVm = SolicitCustomerActivity.this.getSolicitVm();
                if (solicitVm.getMoveStatusState().getValue() == SolicitViewModel.MoveStatus.Pause) {
                    solicitVm2 = SolicitCustomerActivity.this.getSolicitVm();
                    if ((solicitVm2 != null ? Boolean.valueOf(solicitVm2.isNotErrorMove()) : null).booleanValue()) {
                        SolicitCustomerActivity.this.startPauseCountDown();
                    }
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
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SolicitViewModel.MoveStatus.values().length];

        static {
            $EnumSwitchMapping$0[SolicitViewModel.MoveStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[SolicitViewModel.MoveStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$0[SolicitViewModel.MoveStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$0[SolicitViewModel.MoveStatus.Finish.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SolicitViewModel getSolicitVm() {
        return (SolicitViewModel) this.solicitVm.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$robotSpeedListener$1] */
    public SolicitCustomerActivity() {
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.solicit_customer_activity);
        initView();
        bind();
        initListener();
        showOnTheWay();
    }

    private final void bind() {
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$bind$1
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
                SolicitViewModel solicitVm;
                solicitVm = SolicitCustomerActivity.this.getSolicitVm();
                solicitVm.active();
            }
        });
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
    }

    private final void initListener() {
        this.state = getIntent().getIntExtra("state", 0);
        SolicitViewModel solicitVm = getSolicitVm();
        if (solicitVm != null) {
            solicitVm.setState(this.state);
        }
        SolicitViewModel solicitVm2 = getSolicitVm();
        if (solicitVm2 != null) {
            solicitVm2.initMoveByGroup();
        }
        SolicitCustomerActivity solicitCustomerActivity = this;
        getSolicitVm().getMoveStatusState().observe(solicitCustomerActivity, new Observer<SolicitViewModel.MoveStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(SolicitViewModel.MoveStatus moveStatus) {
                boolean z;
                String str;
                LocationLostDialog locationLostDialog;
                Function0 function0;
                int i;
                int i2;
                int i3;
                Function0 function02;
                String str2;
                int i4;
                int i5;
                z = SolicitCustomerActivity.this.isRelease;
                if (z) {
                    return;
                }
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "moveStatusState " + moveStatus);
                if (moveStatus == null) {
                    return;
                }
                int i6 = SolicitCustomerActivity.WhenMappings.$EnumSwitchMapping$0[moveStatus.ordinal()];
                if (i6 == 1) {
                    SolicitCustomerActivity.this.showOnTheWay();
                    return;
                }
                if (i6 == 2) {
                    locationLostDialog = SolicitCustomerActivity.this.locationLostDialog;
                    if (locationLostDialog != null) {
                        locationLostDialog.dismiss();
                    }
                    SolicitCustomerActivity.this.showPause();
                    return;
                }
                if (i6 == 3) {
                    function0 = SolicitCustomerActivity.this.jumpMethod;
                    if (function0 != null) {
                        function02 = SolicitCustomerActivity.this.jumpMethod;
                        if (function02 == null) {
                            Intrinsics.throwNpe();
                        }
                        function02.invoke();
                        return;
                    }
                    i = SolicitCustomerActivity.this.state;
                    if (i != 2) {
                        i2 = SolicitCustomerActivity.this.state;
                        if (i2 != 0) {
                            i3 = SolicitCustomerActivity.this.jumpState;
                            if (i3 != 1) {
                                VoicePlayTasks.INSTANCE.playGreeterTurnBack();
                                SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) SolicitCustomerActivity.class));
                                return;
                            }
                        }
                    }
                    SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) HomeActivity.class));
                    return;
                }
                if (i6 != 4) {
                    return;
                }
                str2 = SolicitCustomerActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("moveStatusState finish state:");
                i4 = SolicitCustomerActivity.this.state;
                sb.append(i4);
                Pdlog.m3273d(str2, sb.toString());
                i5 = SolicitCustomerActivity.this.state;
                if (i5 == 0) {
                    SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) WelComeDialogueActivity.class));
                    return;
                }
                if (i5 == 1) {
                    SolicitCustomerActivity.this.showArrived();
                } else if (i5 == 2 || i5 == 3) {
                    SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) GreeterMenuActivity.class));
                }
            }
        });
        getSolicitVm().getMoveErrorHelperLiveData().observe(solicitCustomerActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                boolean z;
                RunningErrorHelper runningErrorHelper;
                z = SolicitCustomerActivity.this.isRelease;
                if (z || moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = SolicitCustomerActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getSolicitVm().getOnSchedulingLiveData().observe(solicitCustomerActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$3
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
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    SolicitCustomerActivity.this.genSchedulingDialog();
                    schedulingDialog3 = SolicitCustomerActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = SolicitCustomerActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(C5508R.id.mainLayout)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$4
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
                SolicitViewModel solicitVm3;
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "on pause click ");
                solicitVm3 = SolicitCustomerActivity.this.getSolicitVm();
                solicitVm3.active();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$5
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
                SolicitViewModel solicitVm3;
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "on_the_way_layout click ");
                solicitVm3 = SolicitCustomerActivity.this.getSolicitVm();
                solicitVm3.pause();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.cancel_task)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$6
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
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "cancel_task click ");
                SolicitCustomerActivity.this.showCancelDialog();
            }
        }));
        TextView tvWelcome = (TextView) _$_findCachedViewById(C5508R.id.tvWelcome);
        Intrinsics.checkExpressionValueIsNotNull(tvWelcome, "tvWelcome");
        ViewExtKt.onSingleClick(tvWelcome, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$7
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
                SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) SolicitCustomerActivity.class));
            }
        });
        TextView tvDeliver = (TextView) _$_findCachedViewById(C5508R.id.tvDeliver);
        Intrinsics.checkExpressionValueIsNotNull(tvDeliver, "tvDeliver");
        ViewExtKt.onSingleClick(tvDeliver, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$initListener$8
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
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intent intent = new Intent(SolicitCustomerActivity.this, (Class<?>) GreeterMenuActivity.class);
                i = SolicitCustomerActivity.this.state;
                if (i != 2) {
                    intent.putExtra("state", 1);
                }
                SolicitCustomerActivity.this.jumpAndFinish(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCancelDialog() {
        onPauseFeatureChange(this.TYPE_PAUSE_FEATURE_DIALOG);
        String string = getString(C5508R.string.cancel_task_dialog_msg);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.cancel_task_dialog_msg)");
        showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$showCancelDialog$1
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
                SolicitViewModel solicitVm;
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "onCancelCruiseClick dialog onSure");
                solicitVm = SolicitCustomerActivity.this.getSolicitVm();
                solicitVm.cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$showCancelDialog$2
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
                SolicitCustomerActivity solicitCustomerActivity = SolicitCustomerActivity.this;
                i = solicitCustomerActivity.TYPE_PAUSE_FEATURE_NORMAL;
                solicitCustomerActivity.onPauseFeatureChange(i);
            }
        });
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPause() {
        Pdlog.m3273d(this.TAG, "showPause ");
        VoicePlayTasks.INSTANCE.stop();
        startPauseCountDown();
        LinearLayout on_the_way_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(8);
        RelativeLayout rlArrive = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlArrive);
        Intrinsics.checkExpressionValueIsNotNull(rlArrive, "rlArrive");
        rlArrive.setVisibility(8);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
        SolicitViewModel solicitVm = getSolicitVm();
        if ((solicitVm != null ? Boolean.valueOf(solicitVm.isNotErrorMove()) : null).booleanValue()) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.STOP);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWay() {
        Pdlog.m3273d(this.TAG, "showOnTheWay ");
        LinearLayout on_the_way_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(0);
        RelativeLayout rlArrive = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlArrive);
        Intrinsics.checkExpressionValueIsNotNull(rlArrive, "rlArrive");
        rlArrive.setVisibility(8);
        stopPauseCountDown();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.playAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showArrived() {
        Pdlog.m3273d(this.TAG, "showArrived");
        VoicePlayTasks.INSTANCE.playCustomerEndFollow();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setWhiteBg(true);
        LinearLayout on_the_way_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(8);
        RelativeLayout rlArrive = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlArrive);
        Intrinsics.checkExpressionValueIsNotNull(rlArrive, "rlArrive");
        rlArrive.setVisibility(0);
        stopPauseCountDown();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
        final long j = 20000;
        final long j2 = 1000;
        this.mFinishDownTimer = new CountDownTimer(j, j2) { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$showArrived$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                String str;
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "auto active ");
                SolicitCustomerActivity.this.jumpAndFinish(new Intent(SolicitCustomerActivity.this, (Class<?>) SolicitCustomerActivity.class));
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                TextView textView = (TextView) SolicitCustomerActivity.this._$_findCachedViewById(C5508R.id.tvWelcome);
                if (textView != null) {
                    textView.setText(SolicitCustomerActivity.this.getResources().getString(C5508R.string.continue_solicit) + "   " + ((int) Math.ceil(millisUntilFinished / 1000)));
                }
            }
        };
        CountDownTimer countDownTimer = this.mFinishDownTimer;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    private final void initView() {
        this.state = getIntent().getIntExtra("state", 0);
        this.jumpState = getIntent().getIntExtra(INTENT_JUMP_STATE, 0);
        boolean z = true;
        Pdlog.m3273d(this.TAG, "initView state:" + this.state);
        String usherOrTake = RobotMapManager.INSTANCE.getUsherOrTake(this.state);
        Pdlog.m3273d(this.TAG, "initView mName:" + usherOrTake);
        String str = usherOrTake;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z) {
            if (this.state == 0) {
                ToastUtils.show(this, getString(C5508R.string.greeter_point_no_set), new Object[0]);
            } else {
                ToastUtils.show(this, getString(C5508R.string.usher_point_no_set), new Object[0]);
            }
            jumpAndFinish(new Intent(this, (Class<?>) HomeActivity.class));
        }
        setStateModel();
    }

    private final void setStateModel() {
        int i = this.state;
        if (i == 0) {
            TextView tvContent = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
            Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
            tvContent.setText(getString(C5508R.string.go_solicit_area));
            TextView tvTitle = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
            tvTitle.setText(getString(C5508R.string.going_solicit_area));
            return;
        }
        if (i == 1) {
            if (this.jumpState == 1) {
                TextView tvContent2 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
                Intrinsics.checkExpressionValueIsNotNull(tvContent2, "tvContent");
                tvContent2.setText(getString(C5508R.string.go_linwei_point));
                TextView tvTitle2 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
                Intrinsics.checkExpressionValueIsNotNull(tvTitle2, "tvTitle");
                tvTitle2.setText(getString(C5508R.string.going_front_desk));
                return;
            }
            VoicePlayTasks.INSTANCE.playCustomerStartFollow();
            TextView tvContent3 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
            Intrinsics.checkExpressionValueIsNotNull(tvContent3, "tvContent");
            tvContent3.setText(getString(C5508R.string.please_fllow_me));
            TextView tvTitle3 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle3, "tvTitle");
            tvTitle3.setText(getString(C5508R.string.going_front_desk));
            return;
        }
        if (i == 2) {
            TextView tvContent4 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
            Intrinsics.checkExpressionValueIsNotNull(tvContent4, "tvContent");
            tvContent4.setText(getString(C5508R.string.go_linwei_point));
            TextView tvTitle4 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle4, "tvTitle");
            tvTitle4.setText(getString(C5508R.string.going_front_desk));
            return;
        }
        if (i != 3) {
            return;
        }
        TextView tvContent5 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
        Intrinsics.checkExpressionValueIsNotNull(tvContent5, "tvContent");
        tvContent5.setText(getString(C5508R.string.go_linwei_point));
        TextView tvTitle5 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle5, "tvTitle");
        tvTitle5.setText(getString(C5508R.string.going_front_desk));
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.on_the_way_ani);
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
        CountDownTimer countDownTimer = this.mFinishDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public final void release() {
        this.isRelease = true;
        this.runningErrorHelper.unbind();
        stopPauseCountDown();
        VoicePlayTasks.INSTANCE.finishStop();
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        if (!getSolicitVm().isNotErrorMove()) {
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
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                SolicitViewModel solicitVm;
                solicitVm = SolicitCustomerActivity.this.getSolicitVm();
                if (solicitVm != null) {
                    solicitVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String str;
                str = SolicitCustomerActivity.this.TAG;
                Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                SolicitCustomerActivity.this.setCountdown((int) Math.ceil(((double) millisUntilFinished) / ((double) 1000)));
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
        } else {
            if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
                stopPauseCountDown();
                return;
            }
            if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
                stopPauseCountDown();
            } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
                stopPauseCountDown();
                hideCountdownLayout();
            }
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
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.SolicitCustomerActivity$genSchedulingDialog$1
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
                    SolicitViewModel solicitVm;
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = SolicitCustomerActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    solicitVm = SolicitCustomerActivity.this.getSolicitVm();
                    solicitVm.pause();
                }
            });
        }
    }
}
