package com.pudutech.peanut.robot_ui.p063ui.cruise;

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
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.cruise.CruiseSolicitActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.OnKeyChargingVm;
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

/* compiled from: CruiseSolicitActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011*\u0001\u001e\u0018\u0000 E2\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010+\u001a\u00020\u001aH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002J\b\u0010-\u001a\u00020\u001aH\u0002J\b\u0010.\u001a\u00020\u001aH\u0002J\b\u0010/\u001a\u00020\u001aH\u0002J\u0012\u00100\u001a\u00020\u001a2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0012\u00103\u001a\u00020\u001a2\b\u00104\u001a\u0004\u0018\u000105H\u0014J\b\u00106\u001a\u00020\u001aH\u0014J\u0010\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020\u001aH\u0014J\u0006\u0010:\u001a\u00020\u001aJ\u0010\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u0006H\u0002J\b\u0010=\u001a\u00020\u001aH\u0002J\b\u0010>\u001a\u00020\u001aH\u0002J\b\u0010?\u001a\u00020\u001aH\u0002J\b\u0010@\u001a\u00020\u001aH\u0002J\b\u0010A\u001a\u00020\u001aH\u0002J\b\u0010B\u001a\u00020\u001aH\u0002J\b\u0010C\u001a\u00020\u001aH\u0002J\b\u0010D\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\u0014\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'R\u000e\u0010*\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseSolicitActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "currentPauseFeature", "isRelease", "", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "mCountDownTimer", "Landroid/os/CountDownTimer;", "mFinishDownTimer", "mFinished", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "", "onLostLocationLostCallback", "Lkotlin/Function0;", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/cruise/CruiseSolicitActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseSolicitActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "solicitVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel;", "getSolicitVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel;", "solicitVm$delegate", "Lkotlin/Lazy;", "state", "bind", "genSchedulingDialog", "hideCountdownLayout", "initListener", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPauseFeatureChange", "type", "onResume", "release", "setCountdown", "toInt", "setStateModel", "showArrived", "showCancelDialog", "showCountdownLayout", "showOnTheWay", "showPause", "startPauseCountDown", "stopPauseCountDown", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseSolicitActivity extends MyBaseActivity {
    public static final String INTENT_STATE = "state";
    private HashMap _$_findViewCache;
    private boolean isRelease;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer mFinishDownTimer;
    private int mFinished;
    private SchedulingDialog schedulingDialog;
    private int state;
    private final String TAG = "SolicitActivity";

    /* renamed from: solicitVm$delegate, reason: from kotlin metadata */
    private final Lazy solicitVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(SolicitViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$$special$$inlined$viewModels$1
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
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$onLostLocationLostCallback$1
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
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            str = CruiseSolicitActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (RobotMapManager.INSTANCE.getLocateCase() == LocateCase.Marker) {
                locationLostDialog3 = CruiseSolicitActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    CruiseSolicitActivity cruiseSolicitActivity = CruiseSolicitActivity.this;
                    cruiseSolicitActivity.locationLostDialog = new LocationLostDialog(cruiseSolicitActivity);
                }
                locationLostDialog4 = CruiseSolicitActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                CruiseSolicitActivity cruiseSolicitActivity2 = CruiseSolicitActivity.this;
                i2 = cruiseSolicitActivity2.TYPE_PAUSE_FEATURE_ERROR;
                cruiseSolicitActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (RobotMapManager.INSTANCE.getLocateCase() == LocateCase.LaserMark) {
                str2 = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = CruiseSolicitActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    CruiseSolicitActivity cruiseSolicitActivity3 = CruiseSolicitActivity.this;
                    cruiseSolicitActivity3.locationLostDialog = new LocationLostDialog(cruiseSolicitActivity3, "1");
                }
                locationLostDialog2 = CruiseSolicitActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                CruiseSolicitActivity cruiseSolicitActivity4 = CruiseSolicitActivity.this;
                i = cruiseSolicitActivity4.TYPE_PAUSE_FEATURE_ERROR;
                cruiseSolicitActivity4.onPauseFeatureChange(i);
                return;
            }
            CruiseSolicitActivity cruiseSolicitActivity5 = CruiseSolicitActivity.this;
            cruiseSolicitActivity5.jumpAndFinish(new Intent(cruiseSolicitActivity5, (Class<?>) LaserRunningLocationLostActivity.class));
            solicitVm = CruiseSolicitActivity.this.getSolicitVm();
            solicitVm.cancel();
        }
    };
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$onErrorDialogShowStatus$1
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
            str = CruiseSolicitActivity.this.TAG;
            Pdlog.m3273d(str, "onErrorDialogShowStatus : show = " + z + "; ");
            if (z) {
                CruiseSolicitActivity cruiseSolicitActivity = CruiseSolicitActivity.this;
                i = cruiseSolicitActivity.TYPE_PAUSE_FEATURE_ERROR;
                cruiseSolicitActivity.onPauseFeatureChange(i);
            } else if (z2) {
                CruiseSolicitActivity.this.finish();
            }
        }
    };
    private final CruiseSolicitActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            SolicitViewModel solicitVm;
            str = CruiseSolicitActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                solicitVm = CruiseSolicitActivity.this.getSolicitVm();
                if (solicitVm.getMoveStatusState().getValue() == OnKeyChargingVm.MoveStatus.Pause) {
                    CruiseSolicitActivity.this.startPauseCountDown();
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

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$robotSpeedListener$1] */
    public CruiseSolicitActivity() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.cruise_solicit_activity);
        initView();
        bind();
        initListener();
        showOnTheWay();
    }

    private final void bind() {
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$bind$1
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
                solicitVm = CruiseSolicitActivity.this.getSolicitVm();
                solicitVm.active();
            }
        });
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
    }

    private final void initListener() {
        SolicitViewModel solicitVm = getSolicitVm();
        if (solicitVm != null) {
            solicitVm.setState(this.state);
        }
        SolicitViewModel solicitVm2 = getSolicitVm();
        if (solicitVm2 != null) {
            solicitVm2.initMoveByGroup();
        }
        CruiseSolicitActivity cruiseSolicitActivity = this;
        getSolicitVm().getMoveStatusState().observe(cruiseSolicitActivity, new Observer<SolicitViewModel.MoveStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(SolicitViewModel.MoveStatus moveStatus) {
                boolean z;
                String str;
                LocationLostDialog locationLostDialog;
                int i;
                String str2;
                int i2;
                int i3;
                z = CruiseSolicitActivity.this.isRelease;
                if (z) {
                    return;
                }
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "moveStatusState " + moveStatus);
                if (moveStatus == null) {
                    return;
                }
                int i4 = CruiseSolicitActivity.WhenMappings.$EnumSwitchMapping$0[moveStatus.ordinal()];
                if (i4 == 1) {
                    CruiseSolicitActivity.this.showOnTheWay();
                    return;
                }
                if (i4 == 2) {
                    locationLostDialog = CruiseSolicitActivity.this.locationLostDialog;
                    if (locationLostDialog != null) {
                        locationLostDialog.dismiss();
                    }
                    CruiseSolicitActivity.this.showPause();
                    return;
                }
                if (i4 == 3) {
                    i = CruiseSolicitActivity.this.state;
                    if (i == 0) {
                        Intent intent = new Intent(CruiseSolicitActivity.this, (Class<?>) CruiseActivity.class);
                        intent.putExtra(CruiseActivity.CRUISE_STATE, 1);
                        CruiseSolicitActivity.this.jumpAndFinish(intent);
                        return;
                    } else {
                        VoicePlayTasks.INSTANCE.playGreeterTurnBack();
                        CruiseSolicitActivity.this.jumpAndFinish(new Intent(CruiseSolicitActivity.this, (Class<?>) CruiseSolicitActivity.class));
                        return;
                    }
                }
                if (i4 != 4) {
                    return;
                }
                str2 = CruiseSolicitActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("moveStatusState finish state:");
                i2 = CruiseSolicitActivity.this.state;
                sb.append(i2);
                Pdlog.m3273d(str2, sb.toString());
                i3 = CruiseSolicitActivity.this.state;
                if (i3 != 0) {
                    return;
                }
                CruiseSolicitActivity.this.showArrived();
            }
        });
        getSolicitVm().getMoveErrorHelperLiveData().observe(cruiseSolicitActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                boolean z;
                RunningErrorHelper runningErrorHelper;
                z = CruiseSolicitActivity.this.isRelease;
                if (z || moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = CruiseSolicitActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getSolicitVm().getOnSchedulingLiveData().observe(cruiseSolicitActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$3
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
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    CruiseSolicitActivity.this.genSchedulingDialog();
                    schedulingDialog3 = CruiseSolicitActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = CruiseSolicitActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(C5508R.id.mainLayout)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$4
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
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "on pause click ");
                solicitVm3 = CruiseSolicitActivity.this.getSolicitVm();
                solicitVm3.active();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.on_the_way_layout)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$5
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
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "on_the_way_layout click ");
                solicitVm3 = CruiseSolicitActivity.this.getSolicitVm();
                solicitVm3.pause();
            }
        }));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.cancel_task)).setOnClickListener(new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$6
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
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "cancel_task click ");
                CruiseSolicitActivity.this.showCancelDialog();
            }
        }));
        TextView tvWelcome = (TextView) _$_findCachedViewById(C5508R.id.tvWelcome);
        Intrinsics.checkExpressionValueIsNotNull(tvWelcome, "tvWelcome");
        ViewExtKt.onSingleClick(tvWelcome, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$7
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
                Intent intent = new Intent(CruiseSolicitActivity.this, (Class<?>) CruiseActivity.class);
                intent.putExtra(CruiseActivity.CRUISE_STATE, 1);
                CruiseSolicitActivity.this.jumpAndFinish(intent);
            }
        });
        TextView tvDeliver = (TextView) _$_findCachedViewById(C5508R.id.tvDeliver);
        Intrinsics.checkExpressionValueIsNotNull(tvDeliver, "tvDeliver");
        ViewExtKt.onSingleClick(tvDeliver, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$initListener$8
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
                CruiseSolicitActivity.this.jumpAndFinish(new Intent(CruiseSolicitActivity.this, (Class<?>) CruiseGreeterMenuActivity.class));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCancelDialog() {
        onPauseFeatureChange(this.TYPE_PAUSE_FEATURE_DIALOG);
        String string = getString(C5508R.string.cancel_task_dialog_msg);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.cancel_task_dialog_msg)");
        showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$showCancelDialog$1
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
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "onCancelCruiseClick dialog onSure");
                solicitVm = CruiseSolicitActivity.this.getSolicitVm();
                solicitVm.cancel();
            }
        }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$showCancelDialog$2
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
                CruiseSolicitActivity cruiseSolicitActivity = CruiseSolicitActivity.this;
                i = cruiseSolicitActivity.TYPE_PAUSE_FEATURE_NORMAL;
                cruiseSolicitActivity.onPauseFeatureChange(i);
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
        this.mFinishDownTimer = new CountDownTimer(j, j2) { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$showArrived$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                String str;
                int i;
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "auto active ");
                i = CruiseSolicitActivity.this.mFinished;
                if (i == 0) {
                    Intent intent = new Intent(CruiseSolicitActivity.this, (Class<?>) CruiseActivity.class);
                    intent.putExtra(CruiseActivity.CRUISE_STATE, 1);
                    CruiseSolicitActivity.this.jumpAndFinish(intent);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                TextView textView = (TextView) CruiseSolicitActivity.this._$_findCachedViewById(C5508R.id.tvMin);
                if (textView != null) {
                    textView.setText(' ' + ((int) Math.ceil(millisUntilFinished / 1000)) + " s");
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
        boolean z = true;
        Pdlog.m3273d(this.TAG, "initView state:" + this.state);
        String usherOrTake = RobotMapManager.INSTANCE.getUsherOrTake(this.state);
        if (usherOrTake != null && usherOrTake.length() != 0) {
            z = false;
        }
        if (z) {
            if (this.state == 0) {
                ToastUtils.show(this, getString(C5508R.string.greeter_point_no_set), new Object[0]);
            }
            jumpAndFinish(new Intent(this, (Class<?>) CruiseWelComeActivity.class));
        }
        setStateModel();
    }

    private final void setStateModel() {
        int i = this.state;
        if (i == 0) {
            VoicePlayTasks.INSTANCE.playCustomerStartFollow();
            TextView tvContent = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
            Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
            tvContent.setText(getString(C5508R.string.please_fllow_me));
            TextView tvTitle = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle, "tvTitle");
            tvTitle.setText(getString(C5508R.string.going_front_desk));
            return;
        }
        if (i == 1) {
            TextView tvContent2 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
            Intrinsics.checkExpressionValueIsNotNull(tvContent2, "tvContent");
            tvContent2.setText(getString(C5508R.string.go_solicit_area));
            TextView tvTitle2 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle2, "tvTitle");
            tvTitle2.setText(getString(C5508R.string.going_solicit_area));
            return;
        }
        if (i == 2) {
            TextView tvContent3 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
            Intrinsics.checkExpressionValueIsNotNull(tvContent3, "tvContent");
            tvContent3.setText(getString(C5508R.string.go_linwei_point));
            TextView tvTitle3 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
            Intrinsics.checkExpressionValueIsNotNull(tvTitle3, "tvTitle");
            tvTitle3.setText(getString(C5508R.string.going_front_desk));
            return;
        }
        if (i != 3) {
            return;
        }
        TextView tvContent4 = (TextView) _$_findCachedViewById(C5508R.id.tvContent);
        Intrinsics.checkExpressionValueIsNotNull(tvContent4, "tvContent");
        tvContent4.setText(getString(C5508R.string.go_linwei_point));
        TextView tvTitle4 = (TextView) _$_findCachedViewById(C5508R.id.tvTitle);
        Intrinsics.checkExpressionValueIsNotNull(tvTitle4, "tvTitle");
        tvTitle4.setText(getString(C5508R.string.going_front_desk));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mFinished = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
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
        this.mFinished = 1;
        CountDownTimer countDownTimer = this.mFinishDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public final void release() {
        this.isRelease = true;
        this.runningErrorHelper.unbind();
        stopPauseCountDown();
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
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
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                SolicitViewModel solicitVm;
                solicitVm = CruiseSolicitActivity.this.getSolicitVm();
                if (solicitVm != null) {
                    solicitVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String str;
                str = CruiseSolicitActivity.this.TAG;
                Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                CruiseSolicitActivity.this.setCountdown((int) Math.ceil(((double) millisUntilFinished) / ((double) 1000)));
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
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseSolicitActivity$genSchedulingDialog$1
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
                    schedulingDialog2 = CruiseSolicitActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    solicitVm = CruiseSolicitActivity.this.getSolicitVm();
                    solicitVm.pause();
                }
            });
        }
    }
}
