package com.pudutech.bumblebee.robot_ui.module.check_self;

import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModel;
import com.airbnb.lottie.LottieAnimationView;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.activate_task.ActivateContract;
import com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter;
import com.pudutech.bumblebee.presenter.initialization_task.InitStepViewModel;
import com.pudutech.bumblebee.presenter.initialization_task.InitializationContract;
import com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.InitAppManager;
import com.pudutech.bumblebee.robot_ui.manager.SystemUpdateManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.CheckLocationActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserCheckLocationActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.SayHiAnimationActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.RobotActiveActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LockMachineDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.UIDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BluetoothHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.util.FlowCardHelper;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.location.AMapLocationManager;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: SelfCheckActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010'\u001a\u00020\u0019H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002J\b\u0010)\u001a\u00020\u0019H\u0002J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020\u0019H\u0002J\b\u0010/\u001a\u00020\u0019H\u0002J\b\u00100\u001a\u00020\u0019H\u0002J\b\u00101\u001a\u00020\u0019H\u0002J\b\u00102\u001a\u00020\u0019H\u0002J\u0010\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020\tH\u0002J\u0010\u00105\u001a\u00020\u00192\u0006\u00106\u001a\u000207H\u0016J\u0012\u00108\u001a\u00020\u00192\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u00020\u0019H\u0014J\b\u0010<\u001a\u00020\u0019H\u0002J\u001a\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u0010\u0010B\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\tH\u0016J\b\u0010D\u001a\u00020\u0019H\u0002J\b\u0010E\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/check_self/SelfCheckActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$ViewInterface;", "()V", "TAG", "", "TIMEOUT", "", "_hasPosition", "", "Ljava/lang/Boolean;", "activatePresenter", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter;", "getActivatePresenter", "()Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter;", "activatePresenter$delegate", "Lkotlin/Lazy;", "escapeDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog;", "initStepListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitStepViewModel;", "Lkotlin/ParameterName;", "name", "b", "", "isExecuteAgentTest", "isSelfCheckFinish", "isSyncFinish", "locationDone", "locationStartCheck", "", "lockDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LockMachineDialog;", "lockRobotVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/LockRobotVM;", "mainHandler", "Landroid/os/Handler;", "onLocationInitListener", "bindPresenter", "checkActivate", "checkSystemAndFinish", "downAndInstallApp", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "finishJum", "gotoActiveActivity", "initFinishAndJump", "initObserver", "jumpLastResult", "jumpToCharging", "jumpToFailed", "isTimeout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onInitStepFinish", "showActiveStatus", "status", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "showLocalActiveResult", SpeechUtility.TAG_RESOURCE_RESULT, "startCheckInit", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelfCheckActivity extends MyBaseActivity implements ActivateContract.ViewInterface {
    private HashMap _$_findViewCache;
    private Boolean _hasPosition;
    private UIDialog escapeDialog;
    private final Function1<InitStepViewModel, Unit> initStepListener;
    private boolean isExecuteAgentTest;
    private boolean isSelfCheckFinish;
    private boolean locationDone;
    private LockMachineDialog lockDialog;
    private final LockRobotVM lockRobotVM;
    private final Handler mainHandler;
    private final Function1<Boolean, Unit> onLocationInitListener;
    private final String TAG = "SelfCheckActivity";

    /* renamed from: activatePresenter$delegate, reason: from kotlin metadata */
    private final Lazy activatePresenter = LazyKt.lazy(new Function0<ActivatePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$activatePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivatePresenter invoke() {
            return new ActivatePresenter();
        }
    });
    private final int TIMEOUT = 101;
    private long locationStartCheck = -1;
    private boolean isSyncFinish = true;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocateCase.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$0[LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$0[LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[EscapeStatus.values().length];
            $EnumSwitchMapping$1[EscapeStatus.UNLOCKED.ordinal()] = 1;
            $EnumSwitchMapping$1[EscapeStatus.LOCKED.ordinal()] = 2;
            $EnumSwitchMapping$1[EscapeStatus.EXCEPTION_OF_ESCAPE.ordinal()] = 3;
        }
    }

    private final ActivatePresenter getActivatePresenter() {
        return (ActivatePresenter) this.activatePresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.ViewInterface
    public void showLocalActiveResult(boolean result) {
    }

    public SelfCheckActivity() {
        Application instance = BaseApp.INSTANCE.getINSTANCE();
        if (instance == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.disinfect.baselib.base.BaseApp");
        }
        ViewModel viewModel = ((BaseApp) instance).getAppViewModelProvider().get(LockRobotVM.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "(BaseApp.INSTANCE as Bas…(LockRobotVM::class.java)");
        this.lockRobotVM = (LockRobotVM) viewModel;
        this.initStepListener = new Function1<InitStepViewModel, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$initStepListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InitStepViewModel initStepViewModel) {
                invoke2(initStepViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InitStepViewModel viewModel2) {
                String str;
                String str2;
                String str3;
                Intrinsics.checkParameterIsNotNull(viewModel2, "viewModel");
                str = SelfCheckActivity.this.TAG;
                Pdlog.m3273d(str, "initStepListener() InitStepViewModel = " + viewModel2);
                if (viewModel2.getState() == StepState.Fail) {
                    str3 = SelfCheckActivity.this.TAG;
                    Pdlog.m3274e(str3, "initStepListener check self fail :" + viewModel2.getDescription());
                    SelfCheckActivity.this.jumpToFailed(false);
                    return;
                }
                if (viewModel2.getStep() == InitStep.Finish) {
                    SelfCheckActivity.this.onInitStepFinish();
                    str2 = SelfCheckActivity.this.TAG;
                    Pdlog.m3274e(str2, "initStepListener check InitStep.Finish :" + viewModel2.getDescription());
                }
            }
        };
        this.onLocationInitListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$onLocationInitListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                String str;
                Boolean bool;
                long j;
                String str2;
                Handler handler;
                String str3;
                str = SelfCheckActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onLocationInitListener _hasPosition:");
                bool = SelfCheckActivity.this._hasPosition;
                sb.append(bool);
                sb.append(" ,hasPosition:");
                sb.append(z);
                Pdlog.m3273d(str, sb.toString());
                if (z) {
                    SelfCheckActivity.this._hasPosition = Boolean.valueOf(z);
                    SelfCheckActivity.this.jumpLastResult();
                    str3 = SelfCheckActivity.this.TAG;
                    Pdlog.m3273d(str3, "onLocationInitListener location success hasPosition =" + z);
                    return;
                }
                j = SelfCheckActivity.this.locationStartCheck;
                if (j + 5000 > SystemClock.elapsedRealtime()) {
                    handler = SelfCheckActivity.this.mainHandler;
                    handler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$onLocationInitListener$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            String str4;
                            CheckLocationHelper.INSTANCE.checkLocationInitResult();
                            str4 = SelfCheckActivity.this.TAG;
                            Pdlog.m3273d(str4, "onLocationInitListener 不足5s  hasPosition =" + z);
                        }
                    }, 500L);
                    return;
                }
                SelfCheckActivity.this._hasPosition = false;
                SelfCheckActivity.this.jumpLastResult();
                str2 = SelfCheckActivity.this.TAG;
                Pdlog.m3273d(str2, "onLocationInitListener location failure hasPosition =" + z);
            }
        };
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                String str;
                int i2 = message.what;
                i = SelfCheckActivity.this.TIMEOUT;
                if (i2 == i) {
                    str = SelfCheckActivity.this.TAG;
                    Pdlog.m3274e(str, "check time out ");
                    SelfCheckActivity.this.jumpToFailed(true);
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpLastResult() {
        Pdlog.m3273d(this.TAG, "jumpLastResult()  _hasPosition =" + this._hasPosition + ",lockRobotVM.escapeStatusLD.value:" + this.lockRobotVM.getEscapeStatusLD().getValue() + ",AgentTestManager.isHaveTestData:" + AgentTestManager.INSTANCE.isHaveTestData());
        if (this._hasPosition != null) {
            if (this.lockRobotVM.getEscapeStatusLD().getValue() == EscapeStatus.UNLOCKED || AgentTestManager.INSTANCE.isHaveTestData()) {
                if (Intrinsics.areEqual((Object) this._hasPosition, (Object) false)) {
                    if (BatteryInfoManager.INSTANCE.isCharging()) {
                        Pdlog.m3273d(this.TAG, "jumpLastResult() showLocationInitDone isCharging");
                        jumpToCharging();
                        return;
                    }
                    unBindPresenter();
                    PeripheralsSceneUtil.INSTANCE.stopAll();
                    int i = WhenMappings.$EnumSwitchMapping$0[CheckLocationHelper.INSTANCE.getLocateCase().ordinal()];
                    if (i == 1) {
                        AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[0]);
                    } else if (i != 2 && i != 3) {
                        Pdlog.m3274e(this.TAG, "jumpLastResult()  :LocateCase wrong " + CheckLocationHelper.INSTANCE.getLocateCase());
                        AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[0]);
                    } else {
                        AnkoInternals.internalStartActivity(this, LaserCheckLocationActivity.class, new Pair[0]);
                    }
                    finish();
                    return;
                }
                if (Intrinsics.areEqual((Object) this._hasPosition, (Object) true)) {
                    this.mainHandler.removeMessages(this.TIMEOUT);
                    if (this.locationDone) {
                        return;
                    }
                    this.locationDone = true;
                    unBindPresenter();
                    PeripheralsSceneUtil.INSTANCE.stopAll();
                    AnkoInternals.internalStartActivity(this, SayHiAnimationActivity.class, new Pair[0]);
                    Pdlog.m3273d(this.TAG, "jumpLastResult() locationDone = " + this.locationDone);
                    finish();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        if (getIntent() != null) {
            this.isExecuteAgentTest = getIntent().getBooleanExtra(AgentTestManager.KEY_AGENT_TEST_DATA, false);
        }
        if (AgentTestManager.INSTANCE.isHaveTestData() && !this.isExecuteAgentTest) {
            gotoActiveActivity();
            Pdlog.m3273d(this.TAG, "onCreate() 直接进入商家测试");
            return;
        }
        setContentView(C4188R.layout.activity_layout_selfcheck);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C4188R.id.animation_view);
        if (lottieAnimationView != null) {
            lottieAnimationView.playAnimation();
        }
        bindPresenter();
        if (AgentTestManager.INSTANCE.isHaveTestData()) {
            if (this.isExecuteAgentTest) {
                startCheckInit();
                Pdlog.m3273d(this.TAG, "onCreate() 存在测试数据，直接进入自检");
            } else {
                checkActivate();
                Pdlog.m3273d(this.TAG, "onCreate() 激活检测");
            }
        } else {
            checkActivate();
            Pdlog.m3273d(this.TAG, "onCreate() 激活检测");
        }
        AMapLocationManager locationManager = RobotContext.INSTANCE.getLocationManager();
        if (locationManager != null) {
            locationManager.startLocation();
        }
        initObserver();
    }

    private final void initObserver() {
        if (AgentTestManager.INSTANCE.isHaveTestData()) {
            return;
        }
        this.lockRobotVM.getEscapeStatusLD().observe(this, new SelfCheckActivity$initObserver$$inlined$observe$1(this));
        this.lockRobotVM.start();
    }

    private final void checkActivate() {
        Pdlog.m3273d(this.TAG, "checkActivate");
        getActivatePresenter().checkActiveStatus(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkParameterIsNotNull(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Pdlog.m3273d(this.TAG, "onConfigurationChanged");
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        getActivatePresenter().replaceView(this);
    }

    private final void startCheckInit() {
        this.mainHandler.sendEmptyMessageDelayed(this.TIMEOUT, 480000L);
        CheckLocationHelper.INSTANCE.addLocationInitDoneListener(this.onLocationInitListener);
        Pdlog.m3273d(this.TAG, "showActiveStatus : startCheckInit  come");
        if (InitAppManager.INSTANCE.hasFailed()) {
            jumpToFailed(false);
            Pdlog.m3273d(this.TAG, "showActiveStatus : startCheckInit  hasFailed");
        } else if (InitAppManager.INSTANCE.isFinish()) {
            onInitStepFinish();
            Pdlog.m3273d(this.TAG, "showActiveStatus : startCheckInit  onInitStepFinish");
        } else {
            InitAppManager.INSTANCE.addInitStepListener(this.initStepListener);
            InitAppManager.INSTANCE.setOnFontUpdateListener(new Function1<LEDScreenFontUpdateContract.UpdateEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$startCheckInit$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LEDScreenFontUpdateContract.UpdateEvent updateEvent) {
                    invoke2(updateEvent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LEDScreenFontUpdateContract.UpdateEvent it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (it == LEDScreenFontUpdateContract.UpdateEvent.UPDATING) {
                        TextView init_desc_tv = (TextView) SelfCheckActivity.this._$_findCachedViewById(C4188R.id.init_desc_tv);
                        Intrinsics.checkExpressionValueIsNotNull(init_desc_tv, "init_desc_tv");
                        init_desc_tv.setVisibility(0);
                    } else {
                        TextView init_desc_tv2 = (TextView) SelfCheckActivity.this._$_findCachedViewById(C4188R.id.init_desc_tv);
                        Intrinsics.checkExpressionValueIsNotNull(init_desc_tv2, "init_desc_tv");
                        init_desc_tv2.setVisibility(4);
                    }
                }
            });
            Pdlog.m3273d(this.TAG, "showActiveStatus : startCheckInit  addInitStepListener");
        }
        InitAppManager.INSTANCE.setOnComponentInitDone(new Function1<InitializationContract.Component, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$startCheckInit$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InitializationContract.Component component) {
                invoke2(component);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InitializationContract.Component it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == InitializationContract.Component.LED) {
                    PeripheralsSceneUtil.INSTANCE.appInit();
                }
            }
        });
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        InitAppManager.INSTANCE.removeInitStepListener(this.initStepListener);
        CheckLocationHelper.INSTANCE.removeLocationInitDoneListener(this.onLocationInitListener);
        Function1<? super InitializationContract.Component, Unit> function1 = (Function1) null;
        InitAppManager.INSTANCE.setOnComponentInitDone(function1);
        InitAppManager.INSTANCE.setOnFontUpdateListener(function1);
        PeripheralsSceneUtil.INSTANCE.stopAll();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LockMachineDialog lockMachineDialog;
        UIDialog uIDialog;
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C4188R.id.animation_view);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            ((LottieAnimationView) _$_findCachedViewById(C4188R.id.animation_view)).cancelAnimation();
            ((LottieAnimationView) _$_findCachedViewById(C4188R.id.animation_view)).addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$onDestroy$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                }
            });
        }
        AMapLocationManager locationManager = RobotContext.INSTANCE.getLocationManager();
        if (locationManager != null) {
            locationManager.stopLocation();
        }
        RobotContext.INSTANCE.setLocationManager((AMapLocationManager) null);
        this.mainHandler.removeMessages(this.TIMEOUT);
        UIDialog uIDialog2 = this.escapeDialog;
        if (uIDialog2 != null && uIDialog2.isShowing() && (uIDialog = this.escapeDialog) != null) {
            uIDialog.dismiss();
        }
        LockMachineDialog lockMachineDialog2 = this.lockDialog;
        if (lockMachineDialog2 == null || !lockMachineDialog2.isShowing() || (lockMachineDialog = this.lockDialog) == null) {
            return;
        }
        lockMachineDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpToFailed(boolean isTimeout) {
        this.mainHandler.removeMessages(this.TIMEOUT);
        unBindPresenter();
        SelfCheckResultActivity.INSTANCE.startIsTimeOut(isTimeout, this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onInitStepFinish() {
        TtsVoiceHelper.INSTANCE.init();
        FlowCardHelper.INSTANCE.init();
        BluetoothHelper.INSTANCE.enable(false);
        this.mainHandler.removeMessages(this.TIMEOUT);
        if (!Intrinsics.areEqual("robot", "mock")) {
            AiVoiceManager.INSTANCE.init(RobotContext.INSTANCE.getContext());
        }
        AppUpdateManager.INSTANCE.checkAppUpdate(new SelfCheckActivity$onInitStepFinish$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downAndInstallApp(VerionResult vr) {
        Pdlog.m3273d(this.TAG, "downAndInstallApp ");
        AppUpdateManager.INSTANCE.downloadAndShowDialog(this, vr, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity$downAndInstallApp$1
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
                str = SelfCheckActivity.this.TAG;
                Pdlog.m3273d(str, "onSuccess , download failed");
                SelfCheckActivity.this.checkSystemAndFinish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkSystemAndFinish() {
        SystemUpdateManager.INSTANCE.checkLocalSystemFile(this, new SelfCheckActivity$checkSystemAndFinish$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initFinishAndJump() {
        Pdlog.m3273d(this.TAG, "initFinishAndJump");
        this.isSelfCheckFinish = true;
        finishJum();
    }

    private final void finishJum() {
        Pdlog.m3274e(this.TAG, Boolean.valueOf(this.isSyncFinish), Boolean.valueOf(this.isSelfCheckFinish));
        if (this.isSelfCheckFinish && this.isSyncFinish) {
            if (BatteryInfoManager.INSTANCE.isCharging()) {
                jumpToCharging();
                return;
            }
            this.locationStartCheck = SystemClock.elapsedRealtime();
            CheckLocationHelper.INSTANCE.checkLocationInitResult();
            Pdlog.m3273d(this.TAG, "finishJum checkLocationInitResult ");
        }
    }

    private final void jumpToCharging() {
        Pdlog.m3275i(this.TAG, "jump to Charging");
        this.mainHandler.removeMessages(this.TIMEOUT);
        unBindPresenter();
        Intent intent = new Intent(this, (Class<?>) RobotChargingActivity.class);
        intent.putExtra("NEED_RECHECK_LOCATION", true);
        startActivity(intent);
        finish();
    }

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.ViewInterface
    public void showActiveStatus(ActivateContract.RobotActiveStatus status, ActivateContract.ActiveRobotInfo info) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(this.TAG, "showActiveStatus : status = " + status + "; ");
        if (status == ActivateContract.RobotActiveStatus.ACTIVE || status == ActivateContract.RobotActiveStatus.TESTING) {
            startCheckInit();
            Pdlog.m3273d(this.TAG, "showActiveStatus : startCheckInit");
        } else {
            gotoActiveActivity();
        }
    }

    private final void gotoActiveActivity() {
        startActivity(new Intent(this, (Class<?>) RobotActiveActivity.class));
        Pdlog.m3273d(this.TAG, "showActiveStatus : RobotActiveActivity open");
        finish();
    }
}
