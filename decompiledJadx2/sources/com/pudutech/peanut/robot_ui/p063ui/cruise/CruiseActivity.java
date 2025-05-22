package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bean.PlayBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.cruise.CruiseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.CruiseVm;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import com.pudutech.robot.module.report.task.ReportCruiseTask;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import com.warkiz.widget.SizeUtils;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CruiseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016*\u0001;\u0018\u0000 f2\u00020\u0001:\u0001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010B\u001a\u00020!H\u0002J\b\u0010C\u001a\u00020!H\u0002J\b\u0010D\u001a\u00020!H\u0002J\b\u0010E\u001a\u00020!H\u0002J\b\u0010F\u001a\u00020!H\u0002J\u0012\u0010G\u001a\u00020!2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J1\u0010J\u001a\u00020!2\u0006\u0010A\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020\u001c2\b\u0010H\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0002\u0010NJ\u0012\u0010O\u001a\u00020!2\b\u0010P\u001a\u0004\u0018\u00010QH\u0014J\b\u0010R\u001a\u00020!H\u0014J\b\u0010S\u001a\u00020!H\u0014J\u0010\u0010T\u001a\u00020!2\u0006\u0010U\u001a\u00020\u0007H\u0002J\b\u0010V\u001a\u00020!H\u0014J\b\u0010W\u001a\u00020!H\u0014J\b\u0010X\u001a\u00020!H\u0014J\u0010\u0010Y\u001a\u00020!2\u0006\u0010Z\u001a\u00020\u001cH\u0016J\b\u0010[\u001a\u00020!H\u0002J\b\u0010\\\u001a\u00020!H\u0002J\u0010\u0010]\u001a\u00020!2\u0006\u0010^\u001a\u00020\u0007H\u0002J\b\u0010_\u001a\u00020!H\u0002J\b\u0010`\u001a\u00020!H\u0002J\b\u0010a\u001a\u00020!H\u0002J\b\u0010b\u001a\u00020!H\u0002J\b\u0010c\u001a\u00020!H\u0002J\b\u0010d\u001a\u00020!H\u0002J\b\u0010e\u001a\u00020!H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020!0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010'\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0006\u0012\u0004\u0018\u00010!0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0006\u0012\u0004\u0018\u00010!0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R@\u00100\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020!\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0004\n\u0002\u0010<R\u000e\u0010=\u001a\u00020>X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006g"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "animationView", "Landroid/widget/FrameLayout;", AIUIConstant.KEY_CONTENT, "cruiseVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm;", "getCruiseVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm;", "cruiseVm$delegate", "Lkotlin/Lazy;", "currentCruiseId", "currentEventStatus", "Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm$CruiseStatus;", "currentPauseFeature", "handler", "Landroid/os/Handler;", "isFirstStart", "", "isRelease", "isStop", "jumpMethod", "Lkotlin/Function0;", "", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "locationLostTouchCancelCallback", "mCountDownTimer", "Landroid/os/CountDownTimer;", "onAIVoiceDialogDismiss", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "onAIVoiceDialogShow", "onCancelCruiseClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onCruiseAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onCruiseFaceAnimationViewClick", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onFaceAnimationViewClick", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "state", "bindPresenter", "genSchedulingDialog", "hideCountdownLayout", "initAiVoice", "initVM", "jumpAndFinish", "i", "Landroid/content/Intent;", "notifyBatteryInfo", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPauseFeatureChange", "type", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "playSolicitCruise", "release", "setCountdown", "time", "showCountdownLayout", "showOnPauseStatus", "showOnTheWayStatus", "startPauseCountDown", "stopPauseCountDown", "stopSolicitCruise", "unbindPresenter", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseActivity extends BatteryBaseActivity {
    public static final int CLOSE_WAKE = 9;
    public static final String CRUISE_ID_KEY = "CRUISE_ID_KEY";
    public static final String CRUISE_STATE = "CRUISE_STATE";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int POLLING_WAKE_UP = 8;
    public static final int WAKE_UP = 5;
    private HashMap _$_findViewCache;
    private FrameLayout animationView;
    private boolean isRelease;
    private boolean isStop;
    private Function0<Unit> jumpMethod;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private SchedulingDialog schedulingDialog;
    private int state;
    private final String TAG = getClass().getSimpleName();
    private Handler handler = new Companion.WithoutLeakHandler(this);

    /* renamed from: cruiseVm$delegate, reason: from kotlin metadata */
    private final Lazy cruiseVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(CruiseVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$$special$$inlined$viewModels$1
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
    private boolean isFirstStart = true;
    private int currentCruiseId = -1;
    private CruiseVm.CruiseStatus currentEventStatus = CruiseVm.CruiseStatus.Moving;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onErrorDialogShowStatus$1
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
            int i;
            if (z) {
                CruiseActivity cruiseActivity = CruiseActivity.this;
                i = cruiseActivity.TYPE_PAUSE_FEATURE_ERROR;
                cruiseActivity.onPauseFeatureChange(i);
            } else if (z2) {
                CruiseActivity.this.finish();
            }
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onLostLocationLostCallback$1
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
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            Pdlog.m3273d(CruiseActivity.this.TAG, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker) {
                Pdlog.m3273d(CruiseActivity.this.TAG, "onLostLocationLostCallback");
                RelativeLayout rlBg = (RelativeLayout) CruiseActivity.this._$_findCachedViewById(C5508R.id.rlBg);
                Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
                rlBg.setVisibility(8);
                locationLostDialog3 = CruiseActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    CruiseActivity cruiseActivity = CruiseActivity.this;
                    cruiseActivity.locationLostDialog = new LocationLostDialog(cruiseActivity);
                }
                locationLostDialog4 = CruiseActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                CruiseActivity cruiseActivity2 = CruiseActivity.this;
                i2 = cruiseActivity2.TYPE_PAUSE_FEATURE_ERROR;
                cruiseActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                CruiseActivity.this.getCruiseVm().cancel();
                CruiseActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onLostLocationLostCallback$1.1
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
                        CruiseActivity.this.jumpAndFinish(new Intent(CruiseActivity.this, (Class<?>) LaserRunningLocationLostActivity.class));
                    }
                };
                return;
            }
            if (locateCase == LocateCase.LaserMark) {
                Pdlog.m3273d(CruiseActivity.this.TAG, "onLostLocationLostCallback = LaserMark");
                RelativeLayout rlBg2 = (RelativeLayout) CruiseActivity.this._$_findCachedViewById(C5508R.id.rlBg);
                Intrinsics.checkExpressionValueIsNotNull(rlBg2, "rlBg");
                rlBg2.setVisibility(8);
                locationLostDialog = CruiseActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    CruiseActivity cruiseActivity3 = CruiseActivity.this;
                    cruiseActivity3.locationLostDialog = new LocationLostDialog(cruiseActivity3, "1");
                }
                locationLostDialog2 = CruiseActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                CruiseActivity cruiseActivity4 = CruiseActivity.this;
                i = cruiseActivity4.TYPE_PAUSE_FEATURE_ERROR;
                cruiseActivity4.onPauseFeatureChange(i);
            }
        }
    };
    private final Function0<Unit> locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$locationLostTouchCancelCallback$1
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
            Pdlog.m3273d(CruiseActivity.this.TAG, "locationLostTouchCancelCallback");
            ((FaceVideoView) CruiseActivity.this._$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            RelativeLayout rlBg = (RelativeLayout) CruiseActivity.this._$_findCachedViewById(C5508R.id.rlBg);
            Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
            rlBg.setVisibility(8);
            CruiseActivity cruiseActivity = CruiseActivity.this;
            i = cruiseActivity.TYPE_PAUSE_FEATURE_ERROR;
            cruiseActivity.onPauseFeatureChange(i);
        }
    };
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onFaceAnimationViewClick$1
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
            CruiseVm.CruiseStatus cruiseStatus;
            CruiseVm.CruiseStatus cruiseStatus2;
            String str = CruiseActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            cruiseStatus = CruiseActivity.this.currentEventStatus;
            sb.append(cruiseStatus);
            Pdlog.m3273d(str, sb.toString());
            cruiseStatus2 = CruiseActivity.this.currentEventStatus;
            if (cruiseStatus2 == CruiseVm.CruiseStatus.Moving) {
                CruiseActivity.this.getCruiseVm().pause();
            }
        }
    });
    private final SingleClickListener onCruiseAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCruiseAnimationViewClick$1
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
            CruiseVm.CruiseStatus cruiseStatus;
            CruiseVm.CruiseStatus cruiseStatus2;
            String str = CruiseActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            cruiseStatus = CruiseActivity.this.currentEventStatus;
            sb.append(cruiseStatus);
            Pdlog.m3273d(str, sb.toString());
            cruiseStatus2 = CruiseActivity.this.currentEventStatus;
            if (cruiseStatus2 == CruiseVm.CruiseStatus.Moving) {
                CruiseActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCruiseAnimationViewClick$1.1
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
                        VoicePlayTasks voicePlayTasks = VoicePlayTasks.INSTANCE;
                        if (voicePlayTasks != null) {
                            voicePlayTasks.stop();
                        }
                        CruiseActivity.this.jumpAndFinish(new Intent(CruiseActivity.this, (Class<?>) CruiseWelComeActivity.class));
                        ReportCruiseTask.INSTANCE.trackingInteractiveEvent();
                    }
                };
                CruiseVm cruiseVm = CruiseActivity.this.getCruiseVm();
                if (cruiseVm != null) {
                    cruiseVm.cancel();
                }
            }
        }
    });
    private final SingleClickListener onCruiseFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCruiseFaceAnimationViewClick$1
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
            CruiseVm.CruiseStatus cruiseStatus;
            CruiseVm.CruiseStatus cruiseStatus2;
            String str = CruiseActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            cruiseStatus = CruiseActivity.this.currentEventStatus;
            sb.append(cruiseStatus);
            Pdlog.m3273d(str, sb.toString());
            cruiseStatus2 = CruiseActivity.this.currentEventStatus;
            if (cruiseStatus2 == CruiseVm.CruiseStatus.Moving) {
                CruiseActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCruiseFaceAnimationViewClick$1.1
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
                        CruiseActivity.this.jumpAndFinish(new Intent(CruiseActivity.this, (Class<?>) CruiseWelComeActivity.class));
                        ReportCruiseTask.INSTANCE.trackingInteractiveEvent();
                    }
                };
                CruiseVm cruiseVm = CruiseActivity.this.getCruiseVm();
                if (cruiseVm != null) {
                    cruiseVm.cancel();
                }
            }
        }
    });
    private final OnLazyClickListener onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onPauseLayoutClick$1
        @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
        public void onSingleClick() {
            CruiseVm.CruiseStatus cruiseStatus;
            boolean z;
            String str = CruiseActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onPauseLayoutClick current status = ");
            cruiseStatus = CruiseActivity.this.currentEventStatus;
            sb.append(cruiseStatus);
            Pdlog.m3273d(str, sb.toString());
            z = CruiseActivity.this.isRelease;
            if (z) {
                Pdlog.m3274e(CruiseActivity.this.TAG, "goToCruise failed isRelease ");
            } else {
                CruiseActivity.this.getCruiseVm().active();
            }
        }
    };
    private final OnLazyVoiceClickListener onCancelCruiseClick = new CruiseActivity$onCancelCruiseClick$1(this);
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogDismiss = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onAIVoiceDialogDismiss$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionDialog voiceInteractionDialog) {
            invoke2(voiceInteractionDialog);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(VoiceInteractionDialog it) {
            CruiseVm.CruiseStatus cruiseStatus;
            CruiseVm.CruiseStatus cruiseStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            String str = CruiseActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogDismiss ");
            cruiseStatus = CruiseActivity.this.currentEventStatus;
            sb.append(cruiseStatus);
            Pdlog.m3273d(str, sb.toString());
            cruiseStatus2 = CruiseActivity.this.currentEventStatus;
            if (cruiseStatus2 == CruiseVm.CruiseStatus.Pause) {
                CruiseActivity cruiseActivity = CruiseActivity.this;
                i = cruiseActivity.TYPE_PAUSE_FEATURE_NORMAL;
                cruiseActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogShow = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onAIVoiceDialogShow$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionDialog voiceInteractionDialog) {
            invoke2(voiceInteractionDialog);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(VoiceInteractionDialog it) {
            CruiseVm.CruiseStatus cruiseStatus;
            CruiseVm.CruiseStatus cruiseStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            String str = CruiseActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogShow ");
            cruiseStatus = CruiseActivity.this.currentEventStatus;
            sb.append(cruiseStatus);
            Pdlog.m3273d(str, sb.toString());
            cruiseStatus2 = CruiseActivity.this.currentEventStatus;
            if (cruiseStatus2 == CruiseVm.CruiseStatus.Pause) {
                CruiseActivity cruiseActivity = CruiseActivity.this;
                i = cruiseActivity.TYPE_PAUSE_FEATURE_AIVOICE;
                cruiseActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_AIVOICE = 2;
    private final int TYPE_PAUSE_FEATURE_TOUCH = 3;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private int currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
    private final CruiseActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            CruiseVm.CruiseStatus cruiseStatus;
            Pdlog.m3273d(CruiseActivity.this.TAG, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                cruiseStatus = CruiseActivity.this.currentEventStatus;
                if (cruiseStatus == CruiseVm.CruiseStatus.Pause) {
                    CruiseVm cruiseVm = CruiseActivity.this.getCruiseVm();
                    if ((cruiseVm != null ? Boolean.valueOf(cruiseVm.isNotErrorMove()) : null).booleanValue()) {
                        CruiseActivity.this.startPauseCountDown();
                    }
                }
            }
        }
    };
    private String content = "";

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CruiseVm.CruiseStatus.values().length];

        static {
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Finish.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CruiseVm getCruiseVm() {
        return (CruiseVm) this.cruiseVm.getValue();
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

    /* JADX WARN: Type inference failed for: r0v14, types: [com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$robotSpeedListener$1] */
    public CruiseActivity() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        int cruiseId;
        super.onCreate(savedInstanceState);
        this.isRelease = false;
        setContentView(C5508R.layout.activity_cruise);
        this.animationView = (FrameLayout) findViewById(C5508R.id.flInputText);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new CruiseActivity$onCreate$1(null), 2, null);
        Pdlog.m3273d(this.TAG, "onCreate");
        Pdlog.m3273d(this.TAG, "App cruise starTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis())));
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
        this.state = getIntent().getIntExtra(CRUISE_STATE, 0);
        if (this.state == 0) {
            cruiseId = getIntent().getIntExtra("CRUISE_ID_KEY", -1);
        } else {
            cruiseId = Constans.INSTANCE.getCruiseId();
        }
        this.currentCruiseId = cruiseId;
        initAiVoice();
        bindPresenter();
        Pdlog.m3274e(this.TAG, "onCreate currentCruiseId is " + this.currentCruiseId);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_solicit_animation_view)).addOnFaceClickListener(this.onCruiseAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).addOnFaceClickListener(this.onCruiseFaceAnimationViewClick);
        ((RelativeLayout) _$_findCachedViewById(C5508R.id.pause_layout)).setOnClickListener(this.onPauseLayoutClick);
        ((LinearLayout) _$_findCachedViewById(C5508R.id.cancel_back_task)).setOnClickListener(this.onCancelCruiseClick);
        showOnTheWayStatus();
        if (this.currentCruiseId < 0) {
            Pdlog.m3274e(this.TAG, "onCreate currentCruiseId is -1 ???????");
            jumpAndFinish(new Intent(this, (Class<?>) CruiseSelectActivity.class));
            return;
        }
        setCountdown((int) (BusinessSetting.INSTANCE.getCruisePauseKeepTime_ms() / 1000));
        initVM();
        TextView textView = (TextView) _$_findCachedViewById(C5508R.id.tvStop);
        if (textView != null) {
            ViewExtKt.onSingleClick(textView, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCreate$2
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
                    CruiseVm.CruiseStatus cruiseStatus;
                    CruiseVm.CruiseStatus cruiseStatus2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    String str = CruiseActivity.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("tvStop is click ");
                    cruiseStatus = CruiseActivity.this.currentEventStatus;
                    sb.append(cruiseStatus);
                    Pdlog.m3274e(str, sb.toString());
                    cruiseStatus2 = CruiseActivity.this.currentEventStatus;
                    if (cruiseStatus2 == CruiseVm.CruiseStatus.Moving) {
                        CruiseActivity.this.getCruiseVm().pause();
                    }
                }
            });
        }
        final boolean cruiseSwitch = Constans.INSTANCE.getCruiseSwitch();
        getCruiseVm().getFaceLiveData().observe(this, new Observer<FaceVideoAnimation>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$onCreate$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(FaceVideoAnimation it) {
                if (cruiseSwitch) {
                    FaceVideoView faceVideoView = (FaceVideoView) CruiseActivity.this._$_findCachedViewById(C5508R.id.face_solicit_animation_view);
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    faceVideoView.playAnimation(it);
                } else {
                    FaceVideoView faceVideoView2 = (FaceVideoView) CruiseActivity.this._$_findCachedViewById(C5508R.id.face_animation_view);
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    faceVideoView2.playAnimation(it);
                }
            }
        });
    }

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM ");
        getCruiseVm().initMoveCruise(this.currentCruiseId);
        CruiseActivity cruiseActivity = this;
        getCruiseVm().getCruiseStatusState().observe(cruiseActivity, new Observer<CruiseVm.CruiseStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$initVM$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(CruiseVm.CruiseStatus cruiseStatus) {
                CruiseVm.CruiseStatus cruiseStatus2;
                LocationLostDialog locationLostDialog;
                CruiseVm.CruiseStatus cruiseStatus3;
                Function0 function0;
                Function0 function02;
                Pdlog.m3273d(CruiseActivity.this.TAG, "initVM " + cruiseStatus);
                if (cruiseStatus != null) {
                    int i = CruiseActivity.WhenMappings.$EnumSwitchMapping$0[cruiseStatus.ordinal()];
                    if (i == 1) {
                        cruiseStatus2 = CruiseActivity.this.currentEventStatus;
                        if (cruiseStatus2 != cruiseStatus) {
                            CruiseActivity.this.showOnTheWayStatus();
                        }
                    } else if (i == 2) {
                        locationLostDialog = CruiseActivity.this.locationLostDialog;
                        if (locationLostDialog != null) {
                            locationLostDialog.dismiss();
                        }
                        cruiseStatus3 = CruiseActivity.this.currentEventStatus;
                        if (cruiseStatus3 != cruiseStatus) {
                            CruiseActivity.this.showOnPauseStatus();
                        }
                    } else if (i == 3) {
                        function0 = CruiseActivity.this.jumpMethod;
                        if (function0 != null) {
                            function02 = CruiseActivity.this.jumpMethod;
                            if (function02 != null) {
                            }
                        } else {
                            CruiseActivity cruiseActivity2 = CruiseActivity.this;
                            cruiseActivity2.jumpAndFinish(new Intent(cruiseActivity2, (Class<?>) CruiseSelectActivity.class));
                        }
                    }
                }
                CruiseActivity.this.currentEventStatus = cruiseStatus;
            }
        });
        getCruiseVm().getMoveErrorHelperLiveData().observe(cruiseActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$initVM$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                RunningErrorHelper runningErrorHelper;
                if (moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = CruiseActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getCruiseVm().getOnSchedulingLiveData().observe(cruiseActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$initVM$3
            /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
            
                r6 = r5.this$0.schedulingDialog;
             */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onChanged(Boolean it) {
                SchedulingDialog schedulingDialog;
                SchedulingDialog schedulingDialog2;
                SchedulingDialog schedulingDialog3;
                Pdlog.m3273d(CruiseActivity.this.TAG, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    CruiseActivity.this.genSchedulingDialog();
                    schedulingDialog3 = CruiseActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = CruiseActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        getCruiseVm().getPlayState().observe(cruiseActivity, new Observer<PlayBean>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$initVM$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(PlayBean playBean) {
                Pdlog.m3273d(CruiseActivity.this.TAG, "playState " + playBean);
                if (playBean != null) {
                    if (Intrinsics.areEqual((Object) playBean.isPlay(), (Object) true)) {
                        CruiseActivity.this.content = playBean.getContent();
                        CruiseActivity.this.handler.sendEmptyMessage(5);
                    } else {
                        CruiseActivity.this.handler.removeMessages(9);
                        CruiseActivity.this.handler.sendEmptyMessage(9);
                    }
                }
            }
        });
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

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent i) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        VoicePlayTasks voicePlayTasks = VoicePlayTasks.INSTANCE;
        if (voicePlayTasks != null) {
            voicePlayTasks.finishStop();
        }
        startActivity(i);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnPauseStatus() {
        VoicePlayTasks.INSTANCE.stop();
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        this.isStop = true;
        showCountdownLayout();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
        stopSolicitCruise();
        startPauseCountDown();
        if (this.runningErrorHelper.isErrorShowing()) {
            hideCountdownLayout();
        }
        VoicePlayTasks.INSTANCE.finishStop();
        CruiseVm cruiseVm = getCruiseVm();
        if ((cruiseVm != null ? Boolean.valueOf(cruiseVm.isNotErrorMove()) : null).booleanValue()) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.STOP);
        }
        MusicPlayerHelper.getInstance().pausePlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWayStatus() {
        this.isStop = false;
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        hideErrorTipDialog();
        if (Constans.INSTANCE.getCruiseSwitch()) {
            playSolicitCruise();
        } else {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getCruiseDeliver());
        }
        LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.FORWARD);
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.CRUISE);
        stopPauseCountDown();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
        }
    }

    private final void release() {
        this.isRelease = true;
        MusicPlayerHelper.getInstance().release();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
        this.handler.removeCallbacksAndMessages(null);
        unbindPresenter();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$bindPresenter$1
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
                CruiseActivity.this.getCruiseVm().active();
            }
        });
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
        this.runningErrorHelper.unbind();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state != 2) {
            if (state == 4 && isCharging) {
                getCruiseVm().cancel();
                return;
            }
            return;
        }
        if (i != null) {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCountdown(int time) {
        try {
            int sp2px = SizeUtils.sp2px(this, 24.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr3_6);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr3_6)");
            Object[] objArr = {Integer.valueOf(time)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            int indexOf$default = StringsKt.indexOf$default((CharSequence) format, String.valueOf(time), 0, false, 6, (Object) null);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, sp2px, ColorStateList.valueOf(getColor(C5508R.color.theme_main_color)), null);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(textAppearanceSpan, indexOf$default, String.valueOf(time).length() + indexOf$default, 34);
            showCountdownLayout();
            TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            countdown_tv.setText(spannableStringBuilder);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    private final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(4);
    }

    private final void showCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        if (countdown_tv.getVisibility() != 0) {
            TextView countdown_tv2 = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
            countdown_tv2.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "App cruise onStop = " + new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis())));
        this.handler.removeMessages(8);
        this.handler.removeMessages(5);
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.isRelease = false;
        this.handler.sendEmptyMessageDelayed(8, 15000L);
        Pdlog.m3273d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
        this.handler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        if (!getCruiseVm().isNotErrorMove()) {
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
        RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        if (pause_layout.getVisibility() == 0) {
            TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            if (countdown_tv.getVisibility() != 0) {
                return;
            }
        }
        final long notCruisePauseKeepTime_ms = BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms();
        final long j = 1000;
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                Pdlog.m3273d(CruiseActivity.this.TAG, "showTimeLeft onFinish");
                CruiseVm cruiseVm = CruiseActivity.this.getCruiseVm();
                if (cruiseVm != null) {
                    cruiseVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                Pdlog.m3273d(CruiseActivity.this.TAG, "showTimeLeft " + millisUntilFinished);
                CruiseActivity.this.setCountdown((int) Math.ceil(((double) millisUntilFinished) / ((double) 1000)));
            }
        };
        CountDownTimer countDownTimer2 = this.mCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.start();
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void genSchedulingDialog() {
        if (this.schedulingDialog == null) {
            Pdlog.m3273d(this.TAG, "genSchedulingDialog ");
            this.schedulingDialog = new SchedulingDialog(this);
            SchedulingDialog schedulingDialog = this.schedulingDialog;
            if (schedulingDialog == null) {
                Intrinsics.throwNpe();
            }
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseActivity$genSchedulingDialog$1
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
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = CruiseActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    CruiseActivity.this.getCruiseVm().pause();
                }
            });
        }
    }

    private final void playSolicitCruise() {
        RelativeLayout rlBg = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlBg);
        Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
        rlBg.setVisibility(0);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView != null) {
            faceVideoView.setLoopPer(true);
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView2 != null) {
            faceVideoView2.playAnimation(SceneAnimationResources.INSTANCE.getCruiseSolicit());
        }
        FaceVideoView faceVideoView3 = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_solicit_animation_view);
        if (faceVideoView3 != null) {
            faceVideoView3.playAnimation(SceneAnimationResources.INSTANCE.getCruiseDeliver());
        }
    }

    private final void stopSolicitCruise() {
        RelativeLayout rlBg = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlBg);
        Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
        rlBg.setVisibility(8);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_solicit_animation_view);
        if (faceVideoView2 != null) {
            faceVideoView2.stopPlay();
        }
    }

    /* compiled from: CruiseActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity$Companion;", "", "()V", "CLOSE_WAKE", "", "CRUISE_ID_KEY", "", CruiseActivity.CRUISE_STATE, "POLLING_WAKE_UP", "WAKE_UP", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CruiseActivity newInstance() {
            return new CruiseActivity();
        }

        /* compiled from: CruiseActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity;", "(Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseActivity;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<CruiseActivity> mActivity;

            public WithoutLeakHandler(CruiseActivity fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Boolean bool;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                CruiseActivity cruiseActivity = this.mActivity.get();
                if (cruiseActivity != null) {
                    int i = msg.what;
                    if (i != 5) {
                        if (i != 8) {
                            if (i == 9 && cruiseActivity.animationView != null) {
                                Pdlog.m3273d(cruiseActivity.TAG, "closeView");
                                cruiseActivity.getCruiseVm().closeView();
                                return;
                            }
                            return;
                        }
                        cruiseActivity.handler.removeMessages(8);
                        cruiseActivity.handler.sendEmptyMessageDelayed(8, Constans.SOLICIT_WAKE_DEFAULT_INTERVAL);
                        return;
                    }
                    String str = cruiseActivity.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("WAKE_UP View ");
                    RelativeLayout relativeLayout = (RelativeLayout) cruiseActivity._$_findCachedViewById(C5508R.id.rlBg);
                    if (relativeLayout != null) {
                        bool = Boolean.valueOf(relativeLayout.getVisibility() == 8);
                    } else {
                        bool = null;
                    }
                    sb.append(bool);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                    RelativeLayout relativeLayout2 = (RelativeLayout) cruiseActivity._$_findCachedViewById(C5508R.id.rlBg);
                    if (relativeLayout2 != null) {
                        if (relativeLayout2.getVisibility() == 8) {
                            return;
                        }
                    }
                    FrameLayout frameLayout = (FrameLayout) cruiseActivity._$_findCachedViewById(C5508R.id.flInputText);
                    if (frameLayout != null) {
                        if (frameLayout.getVisibility() == 0) {
                            return;
                        }
                    }
                    FrameLayout frameLayout2 = cruiseActivity.animationView;
                    if (frameLayout2 != null) {
                        MarqueeTextView tvInputText = (MarqueeTextView) cruiseActivity._$_findCachedViewById(C5508R.id.tvInputText);
                        Intrinsics.checkExpressionValueIsNotNull(tvInputText, "tvInputText");
                        tvInputText.setText(cruiseActivity.content + RobotContext.INSTANCE.getContext().getString(C5508R.string.voice51_1));
                        cruiseActivity.getCruiseVm().showView(frameLayout2);
                    }
                }
            }
        }
    }
}
