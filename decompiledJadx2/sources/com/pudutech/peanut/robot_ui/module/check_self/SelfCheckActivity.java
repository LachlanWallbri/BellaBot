package com.pudutech.peanut.robot_ui.module.check_self;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.airbnb.lottie.LottieAnimationView;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.activity.CreateMapNoticeActivity;
import com.pudutech.freeinstall_ui.activity.SelectMapSettingActivity;
import com.pudutech.freeinstall_wrapper.MapingFuntionManager;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_robot_selfcheck.oss.MapUpdateManager;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.activate_task.ActivateContract;
import com.pudutech.peanut.presenter.activate_task.ActivatePresenter;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.manager.AppUpdateManager;
import com.pudutech.peanut.robot_ui.manager.InitRobotAppManager;
import com.pudutech.peanut.robot_ui.manager.SystemUpdateManager;
import com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.CheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserCheckLocationActivity;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.SayHiAnimationActivity;
import com.pudutech.peanut.robot_ui.p063ui.RobotActiveActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.viewmodel.CheckInitViewModel;
import com.pudutech.peanut.robot_ui.viewmodel.LocationAndMapModel;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: SelfCheckActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0002J\b\u0010#\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020 H\u0002J\b\u0010(\u001a\u00020 H\u0002J\u0010\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020\u0018H\u0002J\u0010\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020-H\u0016J\u0012\u0010.\u001a\u00020 2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020 H\u0014J\b\u00102\u001a\u00020 H\u0002J\b\u00103\u001a\u00020 H\u0014J\b\u00104\u001a\u00020 H\u0014J\b\u00105\u001a\u00020 H\u0014J\u001a\u00106\u001a\u00020 2\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\u0010\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020\u0018H\u0016J\b\u0010=\u001a\u00020 H\u0002J\b\u0010>\u001a\u00020 H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000b\u001a\u0004\b\u001c\u0010\u001d¨\u0006?"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/check_self/SelfCheckActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ViewInterface;", "()V", "TAG", "", "activatePresenter", "Lcom/pudutech/peanut/presenter/activate_task/ActivatePresenter;", "getActivatePresenter", "()Lcom/pudutech/peanut/presenter/activate_task/ActivatePresenter;", "activatePresenter$delegate", "Lkotlin/Lazy;", "checkInitViewModel", "Lcom/pudutech/peanut/robot_ui/viewmodel/CheckInitViewModel;", "getCheckInitViewModel", "()Lcom/pudutech/peanut/robot_ui/viewmodel/CheckInitViewModel;", "checkInitViewModel$delegate", "initStatus", "Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$InitStatus;", "getInitStatus", "()Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$InitStatus;", "setInitStatus", "(Lcom/pudutech/peanut/robot_ui/manager/InitRobotAppManager$InitStatus;)V", "isSelfCheckFinish", "", "isSyncFinish", "locationAndMapModel", "Lcom/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel;", "getLocationAndMapModel", "()Lcom/pudutech/peanut/robot_ui/viewmodel/LocationAndMapModel;", "locationAndMapModel$delegate", "allFinishJump", "", "bindPresenter", "checkActivate", "checkSystemAndFinish", "downAndInstallApp", "vr", "Lcom/pudutech/lib_update/module/model/VerionResult;", "finishJum", "initFinishAndJump", "jumpToFailed", "isTimeout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onInitStepFinish", "onResume", "onStart", "onStop", "showActiveStatus", "status", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/peanut/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "showLocalActiveResult", SpeechUtility.TAG_RESOURCE_RESULT, "startCheckInit", "unBindPresenter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckActivity extends MyBaseActivity implements ActivateContract.ViewInterface {
    private HashMap _$_findViewCache;
    private InitRobotAppManager.InitStatus initStatus;
    private boolean isSelfCheckFinish;
    private boolean isSyncFinish;
    private final String TAG = "SelfCheckActivity";

    /* renamed from: activatePresenter$delegate, reason: from kotlin metadata */
    private final Lazy activatePresenter = LazyKt.lazy(new Function0<ActivatePresenter>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$activatePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivatePresenter invoke() {
            ActivatePresenter activatePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(ActivatePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(ActivatePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                activatePresenter = new ActivatePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(ActivatePresenter.class).toString(), activatePresenter);
            } else {
                if (!(basePresenterInterface instanceof ActivatePresenter)) {
                    basePresenterInterface = null;
                }
                activatePresenter = (ActivatePresenter) basePresenterInterface;
            }
            if (activatePresenter != null) {
                return activatePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.presenter.activate_task.ActivatePresenter");
        }
    });

    /* renamed from: checkInitViewModel$delegate, reason: from kotlin metadata */
    private final Lazy checkInitViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(CheckInitViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$$special$$inlined$viewModels$1
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

    /* renamed from: locationAndMapModel$delegate, reason: from kotlin metadata */
    private final Lazy locationAndMapModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(LocationAndMapModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$$special$$inlined$viewModels$4
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$$special$$inlined$viewModels$3
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

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[InitRobotAppManager.InitStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[InitRobotAppManager.InitStatus.EmptyMap.ordinal()] = 1;
            $EnumSwitchMapping$0[InitRobotAppManager.InitStatus.NoTopMap.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[InitRobotAppManager.InitStatus.values().length];
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.INIT.ordinal()] = 2;
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.FAILED.ordinal()] = 3;
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.SUCCESS.ordinal()] = 4;
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.EmptyMap.ordinal()] = 5;
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.NoTopMap.ordinal()] = 6;
            $EnumSwitchMapping$1[InitRobotAppManager.InitStatus.TIMEOUT.ordinal()] = 7;
            $EnumSwitchMapping$2 = new int[LocateCase.values().length];
            $EnumSwitchMapping$2[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$2[LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$2[LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$2[LocateCase.LaserMark.ordinal()] = 4;
        }
    }

    private final ActivatePresenter getActivatePresenter() {
        return (ActivatePresenter) this.activatePresenter.getValue();
    }

    private final CheckInitViewModel getCheckInitViewModel() {
        return (CheckInitViewModel) this.checkInitViewModel.getValue();
    }

    private final LocationAndMapModel getLocationAndMapModel() {
        return (LocationAndMapModel) this.locationAndMapModel.getValue();
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

    @Override // com.pudutech.peanut.presenter.activate_task.ActivateContract.ViewInterface
    public void showLocalActiveResult(boolean result) {
    }

    public SelfCheckActivity() {
    }

    public final InitRobotAppManager.InitStatus getInitStatus() {
        return this.initStatus;
    }

    public final void setInitStatus(InitRobotAppManager.InitStatus initStatus) {
        this.initStatus = initStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_layout_selfcheck);
        Pdlog.m3273d(this.TAG, "onCreate");
        Pdlog.m3274e(this.TAG, "POWER = " + Constans.INSTANCE.getChargingPower());
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.animation_view);
        if (lottieAnimationView != null) {
            lottieAnimationView.playAnimation();
        }
        bindPresenter();
        checkActivate();
        TtsVoiceHelper.INSTANCE.init();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SelfCheckActivity$onCreate$1(null), 3, null);
    }

    private final void checkActivate() {
        Pdlog.m3273d(this.TAG, "checkActivate ");
        getActivatePresenter().checkActiveStatus(this);
        MapUpdateManager.INSTANCE.setOnSyncFinishListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$checkActivate$1
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
                Pdlog.m3273d(str, "onSyncFinishListener ");
                SelfCheckActivity.this.isSyncFinish = true;
                SelfCheckActivity.this.finishJum();
            }
        });
    }

    private final void allFinishJump() {
        InitRobotAppManager.InitStatus initStatus = this.initStatus;
        if (initStatus != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[initStatus.ordinal()];
            if (i == 1) {
                Pdlog.m3273d(this.TAG, "EmptyMap");
                MapingFuntionManager.INSTANCE.open();
                startActivity(new Intent(this, (Class<?>) CreateMapNoticeActivity.class));
                unBindPresenter();
                finish();
                return;
            }
            if (i == 2) {
                Pdlog.m3273d(this.TAG, "NoToPoMap");
                MapingFuntionManager.INSTANCE.open();
                Intent intent = new Intent(this, (Class<?>) SelectMapSettingActivity.class);
                intent.putExtra("from", 2);
                startActivity(intent);
                unBindPresenter();
                finish();
                return;
            }
        }
        finishJum();
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
        getCheckInitViewModel().getInitResult().observe(this, new Observer<InitRobotAppManager.InitStatus>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$startCheckInit$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(InitRobotAppManager.InitStatus initStatus) {
                String str;
                str = SelfCheckActivity.this.TAG;
                Pdlog.m3273d(str, "initStatus " + initStatus);
                SelfCheckActivity.this.setInitStatus(initStatus);
                if (initStatus == null) {
                    return;
                }
                switch (initStatus) {
                    case IDLE:
                    case INIT:
                    default:
                        return;
                    case FAILED:
                        SelfCheckActivity.this.jumpToFailed(false);
                        return;
                    case SUCCESS:
                    case EmptyMap:
                    case NoTopMap:
                        SelfCheckActivity.this.onInitStepFinish();
                        return;
                    case TIMEOUT:
                        SelfCheckActivity.this.jumpToFailed(true);
                        return;
                }
            }
        });
        getCheckInitViewModel().checkMirInit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LottieAnimationView animation_view = (LottieAnimationView) _$_findCachedViewById(C5508R.id.animation_view);
        Intrinsics.checkExpressionValueIsNotNull(animation_view, "animation_view");
        if (animation_view.isAnimating()) {
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.animation_view)).cancelAnimation();
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.animation_view)).addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$onDestroy$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                }
            });
        }
        MapUpdateManager.INSTANCE.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpToFailed(boolean isTimeout) {
        unBindPresenter();
        SelfCheckResultActivity.INSTANCE.startIsTimeOut(isTimeout, this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onInitStepFinish() {
        AppUpdateManager.INSTANCE.checkAppUpdate(new SelfCheckActivity$onInitStepFinish$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downAndInstallApp(VerionResult vr) {
        Pdlog.m3273d(this.TAG, "downAndInstallApp ");
        AppUpdateManager.INSTANCE.downloadAndShowDialog(this, vr, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$downAndInstallApp$1
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3274e(this.TAG, "onStop");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3274e(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3274e(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkSystemAndFinish() {
        SystemUpdateManager.INSTANCE.checkLocalSystemFile(this, new SelfCheckActivity$checkSystemAndFinish$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initFinishAndJump() {
        this.isSelfCheckFinish = true;
        Pdlog.m3273d(this.TAG, "initFinishAndJump");
        allFinishJump();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishJum() {
        Pdlog.m3274e(this.TAG, Boolean.valueOf(this.isSyncFinish), Boolean.valueOf(this.isSelfCheckFinish));
        if (this.isSelfCheckFinish && this.isSyncFinish) {
            getLocationAndMapModel().getLocationInitStatus();
            getLocationAndMapModel().getCheckLocationInit().observe(this, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckActivity$finishJum$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Boolean bool) {
                    String str;
                    String str2;
                    str = SelfCheckActivity.this.TAG;
                    Pdlog.m3273d(str, "checkLocationInit " + bool);
                    if (bool == null) {
                        return;
                    }
                    if (!bool.booleanValue()) {
                        SelfCheckActivity.this.unBindPresenter();
                        int i = SelfCheckActivity.WhenMappings.$EnumSwitchMapping$2[RobotMapManager.INSTANCE.getLocateCase().ordinal()];
                        if (i == 1) {
                            Intent intent = new Intent(SelfCheckActivity.this, (Class<?>) CheckLocationActivity.class);
                            intent.putExtra("state", 1);
                            SelfCheckActivity.this.startActivity(intent);
                        } else if (i == 2 || i == 3) {
                            Intent intent2 = new Intent(SelfCheckActivity.this, (Class<?>) LaserCheckLocationActivity.class);
                            intent2.putExtra("state", 1);
                            SelfCheckActivity.this.startActivity(intent2);
                        } else if (i != 4) {
                            str2 = SelfCheckActivity.this.TAG;
                            Pdlog.m3274e(str2, "onLocationInitListener  :LocateCase wrong " + RobotMapManager.INSTANCE.getLocateCase());
                            Intent intent3 = new Intent(SelfCheckActivity.this, (Class<?>) CheckLocationActivity.class);
                            intent3.putExtra("state", 1);
                            SelfCheckActivity.this.startActivity(intent3);
                        } else {
                            Intent intent4 = new Intent(SelfCheckActivity.this, (Class<?>) CheckLocationActivity.class);
                            intent4.putExtra("state", 1);
                            intent4.putExtra("type", 1);
                            SelfCheckActivity.this.startActivity(intent4);
                        }
                        SelfCheckActivity.this.finish();
                        return;
                    }
                    SelfCheckActivity.this.unBindPresenter();
                    AnkoInternals.internalStartActivity(SelfCheckActivity.this, SayHiAnimationActivity.class, new Pair[0]);
                    SelfCheckActivity.this.finish();
                }
            });
        }
    }

    @Override // com.pudutech.peanut.presenter.activate_task.ActivateContract.ViewInterface
    public void showActiveStatus(ActivateContract.RobotActiveStatus status, ActivateContract.ActiveRobotInfo info) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(this.TAG, "showActiveStatus : status = " + status + "; ");
        if (status == ActivateContract.RobotActiveStatus.ACTIVE || status == ActivateContract.RobotActiveStatus.TESTING) {
            MirSdkManager.INSTANCE.notifyMapFinish();
            startCheckInit();
        } else {
            MirSdkManager.INSTANCE.setActive(false);
            startActivity(new Intent(this, (Class<?>) RobotActiveActivity.class));
            finish();
        }
    }
}
