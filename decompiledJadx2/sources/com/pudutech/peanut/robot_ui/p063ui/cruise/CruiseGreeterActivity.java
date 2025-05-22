package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.cruise.CruiseGreeterActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.greeter.GreeterMenuActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.OnTheWayLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.pudutech.peanut.robot_ui.viewmodel.GreeterVm;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import com.warkiz.widget.SizeUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
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

/* compiled from: CruiseGreeterActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¹\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f*\u0001?\u0018\u0000 h2\u00020\u0001:\u0001hB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010G\u001a\u00020#H\u0002J\b\u0010H\u001a\u00020#H\u0002J\b\u0010I\u001a\u00020#H\u0002J\b\u0010J\u001a\u00020#H\u0002J\b\u0010K\u001a\u00020#H\u0002J\b\u0010L\u001a\u00020#H\u0002J\b\u0010M\u001a\u00020#H\u0002J\b\u0010N\u001a\u00020\u001cH\u0002J\u0012\u0010O\u001a\u00020#2\b\u0010P\u001a\u0004\u0018\u00010QH\u0016J1\u0010R\u001a\u00020#2\u0006\u0010E\u001a\u00020\u00062\b\u0010S\u001a\u0004\u0018\u00010T2\u0006\u0010U\u001a\u00020\u001c2\b\u0010V\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010WJ\u0012\u0010X\u001a\u00020#2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0014J\b\u0010[\u001a\u00020#H\u0014J\u0010\u0010\\\u001a\u00020#2\u0006\u0010]\u001a\u00020\u0006H\u0002J\b\u0010^\u001a\u00020#H\u0002J\u0010\u0010_\u001a\u00020#2\u0006\u0010`\u001a\u00020\u0006H\u0002J\b\u0010a\u001a\u00020#H\u0002J\b\u0010b\u001a\u00020#H\u0002J\b\u0010c\u001a\u00020#H\u0002J\b\u0010d\u001a\u00020#H\u0002J\b\u0010e\u001a\u00020#H\u0002J\b\u0010f\u001a\u00020#H\u0002J\b\u0010g\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010'\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0006\u0012\u0004\u0018\u00010#0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0006\u0012\u0004\u0018\u00010#0(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010-\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020#\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00106\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00109\u001a\u00020:8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\u0010\u001a\u0004\b;\u0010<R\u0010\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006i"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseGreeterActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "arriveLayout", "Landroid/view/View;", "getArriveLayout", "()Landroid/view/View;", "arriveLayout$delegate", "Lkotlin/Lazy;", "currentEventStatus", "Lcom/pudutech/peanut/robot_ui/viewmodel/GreeterVm$GreeterStatus;", "currentPauseFeature", "functionClickTime", "", "greeterVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/GreeterVm;", "getGreeterVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/GreeterVm;", "greeterVm$delegate", "isFirstVoicePlay", "", "isPersenterRun", "isRelease", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "mCountDownTimer", "Landroid/os/CountDownTimer;", "mFinishDownTimer", "onAIVoiceDialogDismiss", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "onAIVoiceDialogShow", "onCancelGreeterBackClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onGoToWelcomeAreaClick", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "onTheWayLayout", "Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "getOnTheWayLayout", "()Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "onTheWayLayout$delegate", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/cruise/CruiseGreeterActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseGreeterActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "state", TypedValues.Attributes.S_TARGET, "bindPresenter", "genSchedulingDialog", "hideCountdownLayout", "hideOnTheWayLayout", "initAiVoice", "initView", "initVm", "isShowGreeterFace", "jumpAndFinish", "intent", "Landroid/content/Intent;", "notifyBatteryInfo", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPauseFeatureChange", "type", "release", "setCountdown", "time", "showArrive", "showCountdownLayout", "showOnPause", "showOnTheWay", "startPauseCountDown", "stopPauseCountDown", "unBindPresenter", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseGreeterActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private long functionClickTime;
    private boolean isFirstVoicePlay;
    private boolean isPersenterRun;
    private boolean isRelease;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer mFinishDownTimer;
    private SchedulingDialog schedulingDialog;
    private int state;
    private String target;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TABLE_NAME = TABLE_NAME;
    private static final String TABLE_NAME = TABLE_NAME;
    private final String TAG = "GreeterActivity";

    /* renamed from: greeterVm$delegate, reason: from kotlin metadata */
    private final Lazy greeterVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(GreeterVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$$special$$inlined$viewModels$1
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

    /* renamed from: arriveLayout$delegate, reason: from kotlin metadata */
    private final Lazy arriveLayout = LazyKt.lazy(new Function0<View>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$arriveLayout$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final View invoke() {
            View inflate = ((ViewStub) CruiseGreeterActivity.this.findViewById(C5508R.id.greeter_arrive_layout)).inflate();
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$arriveLayout$2.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                }
            });
            ((TextView) CruiseGreeterActivity.this._$_findCachedViewById(C5508R.id.greeter_arrive_finish_btn)).setOnClickListener(new SingleVoiceClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$arriveLayout$2.2
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
                    GreeterVm greeterVm;
                    greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                    greeterVm.active();
                }
            }));
            return inflate;
        }
    });
    private GreeterVm.GreeterStatus currentEventStatus = GreeterVm.GreeterStatus.Moving;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onErrorDialogShowStatus$1
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
                CruiseGreeterActivity cruiseGreeterActivity = CruiseGreeterActivity.this;
                i = cruiseGreeterActivity.TYPE_PAUSE_FEATURE_ERROR;
                cruiseGreeterActivity.onPauseFeatureChange(i);
            } else if (z2) {
                CruiseGreeterActivity.this.finish();
            }
        }
    };
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onFaceAnimationViewClick$1
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
            GreeterVm.GreeterStatus greeterStatus;
            GreeterVm.GreeterStatus greeterStatus2;
            GreeterVm greeterVm;
            str = CruiseGreeterActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            greeterStatus = CruiseGreeterActivity.this.currentEventStatus;
            sb.append(greeterStatus);
            Pdlog.m3273d(str, sb.toString());
            greeterStatus2 = CruiseGreeterActivity.this.currentEventStatus;
            if (greeterStatus2 == GreeterVm.GreeterStatus.Moving) {
                greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                greeterVm.pause();
            }
        }
    });
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogDismiss = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onAIVoiceDialogDismiss$1
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
            String str;
            GreeterVm.GreeterStatus greeterStatus;
            GreeterVm.GreeterStatus greeterStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = CruiseGreeterActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogDismiss ");
            greeterStatus = CruiseGreeterActivity.this.currentEventStatus;
            sb.append(greeterStatus);
            Pdlog.m3273d(str, sb.toString());
            greeterStatus2 = CruiseGreeterActivity.this.currentEventStatus;
            if (greeterStatus2 == GreeterVm.GreeterStatus.Pause) {
                CruiseGreeterActivity cruiseGreeterActivity = CruiseGreeterActivity.this;
                i = cruiseGreeterActivity.TYPE_PAUSE_FEATURE_NORMAL;
                cruiseGreeterActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogShow = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onAIVoiceDialogShow$1
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
            String str;
            GreeterVm.GreeterStatus greeterStatus;
            GreeterVm.GreeterStatus greeterStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = CruiseGreeterActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogShow ");
            greeterStatus = CruiseGreeterActivity.this.currentEventStatus;
            sb.append(greeterStatus);
            Pdlog.m3273d(str, sb.toString());
            greeterStatus2 = CruiseGreeterActivity.this.currentEventStatus;
            if (greeterStatus2 == GreeterVm.GreeterStatus.Pause) {
                CruiseGreeterActivity cruiseGreeterActivity = CruiseGreeterActivity.this;
                i = cruiseGreeterActivity.TYPE_PAUSE_FEATURE_AIVOICE;
                cruiseGreeterActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final OnLazyVoiceClickListener onCancelGreeterBackClick = new CruiseGreeterActivity$onCancelGreeterBackClick$1(this);
    private final OnLazyVoiceClickListener onGoToWelcomeAreaClick = new CruiseGreeterActivity$onGoToWelcomeAreaClick$1(this);
    private final OnLazyClickListener onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onPauseLayoutClick$1
        @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
        public void onSingleClick() {
            String str;
            GreeterVm.GreeterStatus greeterStatus;
            boolean z;
            long j;
            GreeterVm greeterVm;
            String str2;
            String str3;
            str = CruiseGreeterActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onPauseLayoutClick current status = ");
            greeterStatus = CruiseGreeterActivity.this.currentEventStatus;
            sb.append(greeterStatus);
            Pdlog.m3273d(str, sb.toString());
            z = CruiseGreeterActivity.this.isRelease;
            if (z) {
                str3 = CruiseGreeterActivity.this.TAG;
                Pdlog.m3274e(str3, "goToCruise failed isRelease ");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            j = CruiseGreeterActivity.this.functionClickTime;
            if (currentTimeMillis - j < 1200) {
                str2 = CruiseGreeterActivity.this.TAG;
                Pdlog.m3274e(str2, "onLayoutClick click too fast");
            } else {
                greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                greeterVm.active();
            }
        }
    };
    private final Function0<Unit> locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$locationLostTouchCancelCallback$1
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
            int i;
            str = CruiseGreeterActivity.this.TAG;
            Pdlog.m3273d(str, "locationLostTouchCancelCallback");
            CruiseGreeterActivity.this.hideOnTheWayLayout();
            CruiseGreeterActivity cruiseGreeterActivity = CruiseGreeterActivity.this;
            i = cruiseGreeterActivity.TYPE_PAUSE_FEATURE_ERROR;
            cruiseGreeterActivity.onPauseFeatureChange(i);
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$onLostLocationLostCallback$1
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
            GreeterVm greeterVm;
            String str2;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            str = CruiseGreeterActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback");
            if (RobotMapManager.INSTANCE.getLocateCase() == LocateCase.Marker) {
                locationLostDialog3 = CruiseGreeterActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    CruiseGreeterActivity cruiseGreeterActivity = CruiseGreeterActivity.this;
                    cruiseGreeterActivity.locationLostDialog = new LocationLostDialog(cruiseGreeterActivity);
                }
                locationLostDialog4 = CruiseGreeterActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                CruiseGreeterActivity cruiseGreeterActivity2 = CruiseGreeterActivity.this;
                i2 = cruiseGreeterActivity2.TYPE_PAUSE_FEATURE_ERROR;
                cruiseGreeterActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (RobotMapManager.INSTANCE.getLocateCase() == LocateCase.LaserMark) {
                str2 = CruiseGreeterActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = CruiseGreeterActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    CruiseGreeterActivity cruiseGreeterActivity3 = CruiseGreeterActivity.this;
                    cruiseGreeterActivity3.locationLostDialog = new LocationLostDialog(cruiseGreeterActivity3, "1");
                }
                locationLostDialog2 = CruiseGreeterActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                CruiseGreeterActivity cruiseGreeterActivity4 = CruiseGreeterActivity.this;
                i = cruiseGreeterActivity4.TYPE_PAUSE_FEATURE_ERROR;
                cruiseGreeterActivity4.onPauseFeatureChange(i);
                return;
            }
            greeterVm = CruiseGreeterActivity.this.getGreeterVm();
            greeterVm.cancel();
            CruiseGreeterActivity cruiseGreeterActivity5 = CruiseGreeterActivity.this;
            cruiseGreeterActivity5.jumpAndFinish(new Intent(cruiseGreeterActivity5, (Class<?>) LaserRunningLocationLostActivity.class));
        }
    };

    /* renamed from: onTheWayLayout$delegate, reason: from kotlin metadata */
    private final Lazy onTheWayLayout = LazyKt.lazy(new CruiseGreeterActivity$onTheWayLayout$2(this));
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_AIVOICE = 2;
    private final int TYPE_PAUSE_FEATURE_TOUCH = 3;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private int currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
    private final CruiseGreeterActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            GreeterVm.GreeterStatus greeterStatus;
            str = CruiseGreeterActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                greeterStatus = CruiseGreeterActivity.this.currentEventStatus;
                if (greeterStatus == GreeterVm.GreeterStatus.Pause) {
                    CruiseGreeterActivity.this.startPauseCountDown();
                }
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[GreeterVm.GreeterStatus.values().length];

        static {
            $EnumSwitchMapping$0[GreeterVm.GreeterStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[GreeterVm.GreeterStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$0[GreeterVm.GreeterStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$0[GreeterVm.GreeterStatus.Arrive.ordinal()] = 4;
            $EnumSwitchMapping$0[GreeterVm.GreeterStatus.Finish.ordinal()] = 5;
        }
    }

    private final View getArriveLayout() {
        return (View) this.arriveLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GreeterVm getGreeterVm() {
        return (GreeterVm) this.greeterVm.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnTheWayLayout getOnTheWayLayout() {
        return (OnTheWayLayout) this.onTheWayLayout.getValue();
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

    /* JADX WARN: Type inference failed for: r0v35, types: [com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$robotSpeedListener$1] */
    public CruiseGreeterActivity() {
    }

    /* compiled from: CruiseGreeterActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseGreeterActivity$Companion;", "", "()V", CruiseGreeterActivity.TABLE_NAME, "", "getTABLE_NAME", "()Ljava/lang/String;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTABLE_NAME() {
            return CruiseGreeterActivity.TABLE_NAME;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_greeter);
        this.target = getIntent().getStringExtra(TABLE_NAME);
        initView();
        bindPresenter();
        initVm();
        initAiVoice();
        showOnTheWay();
        VoicePlayTasks.INSTANCE.playGreeterStart();
        this.state = getIntent().getIntExtra("state", 0);
        if (this.state == 0) {
            ((ImageView) _$_findCachedViewById(C5508R.id.ivImg)).setImageDrawable(getResources().getDrawable(C5508R.drawable.icon_pause_welcomearea));
            TextView tvDesc = (TextView) _$_findCachedViewById(C5508R.id.tvDesc);
            Intrinsics.checkExpressionValueIsNotNull(tvDesc, "tvDesc");
            tvDesc.setText(getString(C5508R.string.go_linwei));
        } else {
            ((ImageView) _$_findCachedViewById(C5508R.id.ivImg)).setImageDrawable(getResources().getDrawable(C5508R.drawable.ic_icon_guest_press));
            TextView tvDesc2 = (TextView) _$_findCachedViewById(C5508R.id.tvDesc);
            Intrinsics.checkExpressionValueIsNotNull(tvDesc2, "tvDesc");
            tvDesc2.setText(getString(C5508R.string.go_lanke));
        }
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initVm() {
        String str = this.target;
        if (str == null || StringsKt.isBlank(str)) {
            Pdlog.m3274e(this.TAG, "onCreate target is null ??? ");
            jumpAndFinish(new Intent(this, (Class<?>) GreeterMenuActivity.class));
        } else {
            GreeterVm greeterVm = getGreeterVm();
            String str2 = this.target;
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            greeterVm.initGreeterName(str2);
        }
        CruiseGreeterActivity cruiseGreeterActivity = this;
        getGreeterVm().getGreeterStatusState().observe(cruiseGreeterActivity, new Observer<GreeterVm.GreeterStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$initVm$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(GreeterVm.GreeterStatus greeterStatus) {
                String str3;
                GreeterVm.GreeterStatus greeterStatus2;
                boolean isShowGreeterFace;
                OnTheWayLayout onTheWayLayout;
                String str4;
                LocationLostDialog locationLostDialog;
                GreeterVm.GreeterStatus greeterStatus3;
                str3 = CruiseGreeterActivity.this.TAG;
                Pdlog.m3273d(str3, "greeterStatusState  " + greeterStatus);
                if (greeterStatus != null) {
                    int i = CruiseGreeterActivity.WhenMappings.$EnumSwitchMapping$0[greeterStatus.ordinal()];
                    if (i == 1) {
                        greeterStatus2 = CruiseGreeterActivity.this.currentEventStatus;
                        if (greeterStatus != greeterStatus2) {
                            CruiseGreeterActivity.this.showOnTheWay();
                            CruiseGreeterActivity.this.stopPauseCountDown();
                        }
                        isShowGreeterFace = CruiseGreeterActivity.this.isShowGreeterFace();
                        if (!isShowGreeterFace) {
                            onTheWayLayout = CruiseGreeterActivity.this.getOnTheWayLayout();
                            str4 = CruiseGreeterActivity.this.target;
                            if (str4 == null) {
                                str4 = "";
                            }
                            onTheWayLayout.setTarget(str4);
                        }
                    } else if (i == 2) {
                        locationLostDialog = CruiseGreeterActivity.this.locationLostDialog;
                        if (locationLostDialog != null) {
                            locationLostDialog.dismiss();
                        }
                        greeterStatus3 = CruiseGreeterActivity.this.currentEventStatus;
                        if (greeterStatus != greeterStatus3) {
                            CruiseGreeterActivity.this.showOnPause();
                        }
                    } else if (i == 3) {
                        Intent intent = new Intent(CruiseGreeterActivity.this, (Class<?>) CruiseActivity.class);
                        intent.putExtra(CruiseActivity.CRUISE_STATE, 1);
                        CruiseGreeterActivity.this.jumpAndFinish(intent);
                    } else if (i == 4) {
                        CruiseGreeterActivity.this.showArrive();
                        VoicePlayTasks.INSTANCE.playGreeterArrive();
                    } else if (i == 5) {
                        VoicePlayTasks.INSTANCE.playGreeterTurnBack();
                        Intent intent2 = new Intent(CruiseGreeterActivity.this, (Class<?>) CruiseActivity.class);
                        intent2.putExtra(CruiseActivity.CRUISE_STATE, 1);
                        CruiseGreeterActivity.this.jumpAndFinish(intent2);
                    }
                }
                CruiseGreeterActivity.this.currentEventStatus = greeterStatus;
            }
        });
        getGreeterVm().getMoveErrorHelperLiveData().observe(cruiseGreeterActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$initVm$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                RunningErrorHelper runningErrorHelper;
                if (moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = CruiseGreeterActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getGreeterVm().getOnSchedulingLiveData().observe(cruiseGreeterActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$initVm$3
            /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
            
                r6 = r5.this$0.schedulingDialog;
             */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onChanged(Boolean it) {
                String str3;
                SchedulingDialog schedulingDialog;
                SchedulingDialog schedulingDialog2;
                SchedulingDialog schedulingDialog3;
                str3 = CruiseGreeterActivity.this.TAG;
                Pdlog.m3273d(str3, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    CruiseGreeterActivity.this.genSchedulingDialog();
                    schedulingDialog3 = CruiseGreeterActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = CruiseGreeterActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isShowGreeterFace() {
        return Constans.INSTANCE.getGreeterFaceSwitch();
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

    private final void initView() {
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
        musicPlayerHelper.setOpenBirthdaySwitch(false);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        ((ConstraintLayout) _$_findCachedViewById(C5508R.id.base_layout)).setOnClickListener(this.onPauseLayoutClick);
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.cancel_back_task)).setOnClickListener(this.onCancelGreeterBackClick);
        ((LinearLayout) _$_findCachedViewById(C5508R.id.go_to_welcome_area)).setOnClickListener(this.onGoToWelcomeAreaClick);
    }

    private final void bindPresenter() {
        CruiseGreeterActivity cruiseGreeterActivity = this;
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(cruiseGreeterActivity);
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
        SpUtils.get(cruiseGreeterActivity, "key_lattice_guide_table", "");
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$bindPresenter$1
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
                GreeterVm greeterVm;
                greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                greeterVm.active();
            }
        });
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        super.jumpAndFinish(intent);
        release();
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    private final void release() {
        this.isRelease = true;
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
        unBindPresenter();
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        this.runningErrorHelper.unbind();
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWay() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        if (isShowGreeterFace()) {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.goUsher());
        } else {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            String str = this.target;
            if (str != null) {
                getOnTheWayLayout().showLayout(str);
            }
        }
        LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.FORWARD);
        if (!this.isFirstVoicePlay) {
            this.isFirstVoicePlay = true;
        }
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.SPECIAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideOnTheWayLayout() {
        if (isShowGreeterFace()) {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
        } else {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            getOnTheWayLayout().hideLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnPause() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        showCountdownLayout();
        hideOnTheWayLayout();
        if (this.runningErrorHelper.isErrorShowing()) {
            hideCountdownLayout();
        }
        LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.STOP);
        MusicPlayerHelper.getInstance().pausePlay();
        startPauseCountDown();
        VoicePlayTasks.INSTANCE.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showArrive() {
        Pdlog.m3273d(this.TAG, "showArrive ");
        getArriveLayout().setVisibility(0);
        TextView greeter_arrive_info_tv = (TextView) _$_findCachedViewById(C5508R.id.greeter_arrive_info_tv);
        Intrinsics.checkExpressionValueIsNotNull(greeter_arrive_info_tv, "greeter_arrive_info_tv");
        String value = getGreeterVm().getCurrentDes().getValue();
        greeter_arrive_info_tv.setText(value != null ? value : "");
        TextView textView = (TextView) _$_findCachedViewById(C5508R.id.greeter_arrive_info_tv);
        String value2 = getGreeterVm().getCurrentDes().getValue();
        UiUtils.adjustTvTextSize(textView, 1000, value2 != null ? value2 : "");
        hideOnTheWayLayout();
        LightPlayManager.INSTANCE.playGuideArrive();
        MusicPlayerHelper.getInstance().pausePlay();
        final long j = 5000;
        final long j2 = 1000;
        this.mFinishDownTimer = new CountDownTimer(j, j2) { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$showArrive$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                String str;
                GreeterVm greeterVm;
                str = CruiseGreeterActivity.this.TAG;
                Pdlog.m3273d(str, "auto active ");
                greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                if (greeterVm != null) {
                    greeterVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                TextView textView2 = (TextView) CruiseGreeterActivity.this._$_findCachedViewById(C5508R.id.greeter_arrive_finish_btn);
                if (textView2 != null) {
                    textView2.setText(CruiseGreeterActivity.this.getResources().getString(C5508R.string.pdStr25_11) + "   " + ((int) Math.ceil(millisUntilFinished / 1000)));
                }
            }
        };
        CountDownTimer countDownTimer = this.mFinishDownTimer;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCountdown(int time) {
        try {
            int sp2px = SizeUtils.sp2px(this, 24.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr25_15);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr25_15)");
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 2) {
            if (i != null) {
                ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
                return;
            }
            return;
        }
        if (state == 4 && model != null && isCharging) {
            getGreeterVm().cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        if (!getGreeterVm().isNotErrorMove()) {
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
        if (!((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).isPlayLostLocation()) {
            TextView countdown_tv = (TextView) _$_findCachedViewById(C5508R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            if (countdown_tv.getVisibility() != 0) {
                return;
            }
        }
        final long notCruisePauseKeepTime_ms = BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms();
        final long j = 1000;
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                GreeterVm greeterVm;
                greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                if (greeterVm != null) {
                    greeterVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String str;
                str = CruiseGreeterActivity.this.TAG;
                Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                long j2 = (long) 1000;
                long j3 = millisUntilFinished / j2;
                if (j3 == BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / j2) {
                    CruiseGreeterActivity.this.setCountdown((int) j3);
                } else {
                    CruiseGreeterActivity.this.setCountdown((int) (j3 + 1));
                }
            }
        };
        CountDownTimer countDownTimer2 = this.mCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPauseCountDown() {
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
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseGreeterActivity$genSchedulingDialog$1
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
                    GreeterVm greeterVm;
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = CruiseGreeterActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    greeterVm = CruiseGreeterActivity.this.getGreeterVm();
                    greeterVm.pause();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
        CountDownTimer countDownTimer = this.mFinishDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
