package com.pudutech.peanut.robot_ui.p063ui.greeter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.greeter.GotoWelcomeAreaActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeVm;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import com.warkiz.widget.SizeUtils;
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
import kotlin.text.StringsKt;

/* compiled from: GotoWelcomeAreaActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¥\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r*\u0001+\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u00107\u001a\u00020\u0016H\u0002J\b\u00108\u001a\u00020\u0016H\u0002J\b\u00109\u001a\u00020\u0016H\u0002J\b\u0010:\u001a\u00020\u0016H\u0002J\b\u0010;\u001a\u00020\u0016H\u0002J\b\u0010<\u001a\u00020\u0016H\u0002J\u0012\u0010=\u001a\u00020\u00162\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J1\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u00062\b\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020\u00102\b\u0010E\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010FJ\u0012\u0010G\u001a\u00020\u00162\b\u0010H\u001a\u0004\u0018\u00010IH\u0014J\b\u0010J\u001a\u00020\u0016H\u0014J\u0010\u0010K\u001a\u00020\u00162\u0006\u0010L\u001a\u00020\u0006H\u0002J\b\u0010M\u001a\u00020\u0016H\u0002J\u0010\u0010N\u001a\u00020\u00162\u0006\u0010O\u001a\u00020\u0006H\u0002J\b\u0010P\u001a\u00020\u0016H\u0002J\b\u0010Q\u001a\u00020\u0016H\u0002J\b\u0010R\u001a\u00020\u0016H\u0002J\b\u0010S\u001a\u00020\u0016H\u0002J\b\u0010T\u001a\u00020\u0016H\u0002J\b\u0010U\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\u001f\u001a4\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0016\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b3\u00104¨\u0006V"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/greeter/GotoWelcomeAreaActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "currentEventStatus", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeVm$WelComeStatus;", "currentPauseFeature", "functionClickTime", "", "isPersenterRun", "", "isRelease", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "mCountDownTimer", "Landroid/os/CountDownTimer;", "onAIVoiceDialogDismiss", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "onAIVoiceDialogShow", "onCancelGreeterBackClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/greeter/GotoWelcomeAreaActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/greeter/GotoWelcomeAreaActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "welComeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeVm;", "getWelComeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeVm;", "welComeVm$delegate", "Lkotlin/Lazy;", "bindPresenter", "genSchedulingDialog", "hideCountdownLayout", "initAiVoice", "initView", "initVm", "jumpAndFinish", "intent", "Landroid/content/Intent;", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPauseFeatureChange", "type", "release", "setCountdown", "time", "showCountdownLayout", "showOnPause", "showOnTheWay", "startPauseCountDown", "stopPauseCountDown", "unBindPresenter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GotoWelcomeAreaActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private long functionClickTime;
    private boolean isPersenterRun;
    private boolean isRelease;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private SchedulingDialog schedulingDialog;
    private final String TAG = "GotoWelcomeAreaActivity";
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();

    /* renamed from: welComeVm$delegate, reason: from kotlin metadata */
    private final Lazy welComeVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(WelComeVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$$special$$inlined$viewModels$1
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
    private WelComeVm.WelComeStatus currentEventStatus = WelComeVm.WelComeStatus.Moving;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onErrorDialogShowStatus$1
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
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_ERROR;
                gotoWelcomeAreaActivity.onPauseFeatureChange(i);
            } else if (z2) {
                GotoWelcomeAreaActivity.this.finish();
            }
        }
    };
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onFaceAnimationViewClick$1
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
            WelComeVm.WelComeStatus welComeStatus;
            WelComeVm.WelComeStatus welComeStatus2;
            WelComeVm welComeVm;
            str = GotoWelcomeAreaActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            welComeStatus = GotoWelcomeAreaActivity.this.currentEventStatus;
            sb.append(welComeStatus);
            Pdlog.m3273d(str, sb.toString());
            welComeStatus2 = GotoWelcomeAreaActivity.this.currentEventStatus;
            if (welComeStatus2 == WelComeVm.WelComeStatus.Moving) {
                welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
                welComeVm.pause();
            }
        }
    });
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogDismiss = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onAIVoiceDialogDismiss$1
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
            WelComeVm.WelComeStatus welComeStatus;
            WelComeVm.WelComeStatus welComeStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = GotoWelcomeAreaActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogDismiss ");
            welComeStatus = GotoWelcomeAreaActivity.this.currentEventStatus;
            sb.append(welComeStatus);
            Pdlog.m3273d(str, sb.toString());
            welComeStatus2 = GotoWelcomeAreaActivity.this.currentEventStatus;
            if (welComeStatus2 == WelComeVm.WelComeStatus.Pause) {
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_NORMAL;
                gotoWelcomeAreaActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogShow = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onAIVoiceDialogShow$1
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
            WelComeVm.WelComeStatus welComeStatus;
            WelComeVm.WelComeStatus welComeStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = GotoWelcomeAreaActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogShow ");
            welComeStatus = GotoWelcomeAreaActivity.this.currentEventStatus;
            sb.append(welComeStatus);
            Pdlog.m3273d(str, sb.toString());
            welComeStatus2 = GotoWelcomeAreaActivity.this.currentEventStatus;
            if (welComeStatus2 == WelComeVm.WelComeStatus.Pause) {
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_AIVOICE;
                gotoWelcomeAreaActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final OnLazyVoiceClickListener onCancelGreeterBackClick = new GotoWelcomeAreaActivity$onCancelGreeterBackClick$1(this);
    private final OnLazyClickListener onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onPauseLayoutClick$1
        @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
        public void onSingleClick() {
            String str;
            WelComeVm.WelComeStatus welComeStatus;
            boolean z;
            long j;
            WelComeVm welComeVm;
            String str2;
            String str3;
            str = GotoWelcomeAreaActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onPauseLayoutClick current status = ");
            welComeStatus = GotoWelcomeAreaActivity.this.currentEventStatus;
            sb.append(welComeStatus);
            Pdlog.m3273d(str, sb.toString());
            z = GotoWelcomeAreaActivity.this.isRelease;
            if (z) {
                str3 = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3274e(str3, "goToCruise failed isRelease ");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            j = GotoWelcomeAreaActivity.this.functionClickTime;
            if (currentTimeMillis - j < 1200) {
                str2 = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3274e(str2, "onLayoutClick click too fast");
            } else {
                welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
                welComeVm.active();
            }
        }
    };
    private final Function0<Unit> locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$locationLostTouchCancelCallback$1
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
            str = GotoWelcomeAreaActivity.this.TAG;
            Pdlog.m3273d(str, "locationLostTouchCancelCallback");
            ((FaceVideoView) GotoWelcomeAreaActivity.this._$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
            i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_ERROR;
            gotoWelcomeAreaActivity.onPauseFeatureChange(i);
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onLostLocationLostCallback$1
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
            WelComeVm welComeVm;
            String str2;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            str = GotoWelcomeAreaActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback");
            if (RobotMapManager.INSTANCE.getLocateCase() == LocateCase.Marker) {
                locationLostDialog3 = GotoWelcomeAreaActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                    gotoWelcomeAreaActivity.locationLostDialog = new LocationLostDialog(gotoWelcomeAreaActivity);
                }
                locationLostDialog4 = GotoWelcomeAreaActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = GotoWelcomeAreaActivity.this;
                i2 = gotoWelcomeAreaActivity2.TYPE_PAUSE_FEATURE_ERROR;
                gotoWelcomeAreaActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (RobotMapManager.INSTANCE.getLocateCase() == LocateCase.LaserMark) {
                str2 = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = GotoWelcomeAreaActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    GotoWelcomeAreaActivity gotoWelcomeAreaActivity3 = GotoWelcomeAreaActivity.this;
                    gotoWelcomeAreaActivity3.locationLostDialog = new LocationLostDialog(gotoWelcomeAreaActivity3, "1");
                }
                locationLostDialog2 = GotoWelcomeAreaActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity4 = GotoWelcomeAreaActivity.this;
                i = gotoWelcomeAreaActivity4.TYPE_PAUSE_FEATURE_ERROR;
                gotoWelcomeAreaActivity4.onPauseFeatureChange(i);
                return;
            }
            welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
            welComeVm.cancel();
            GotoWelcomeAreaActivity gotoWelcomeAreaActivity5 = GotoWelcomeAreaActivity.this;
            gotoWelcomeAreaActivity5.jumpAndFinish(new Intent(gotoWelcomeAreaActivity5, (Class<?>) LaserRunningLocationLostActivity.class));
        }
    };
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_AIVOICE = 2;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private int currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
    private final GotoWelcomeAreaActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            WelComeVm.WelComeStatus welComeStatus;
            WelComeVm welComeVm;
            str = GotoWelcomeAreaActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                welComeStatus = GotoWelcomeAreaActivity.this.currentEventStatus;
                if (welComeStatus == WelComeVm.WelComeStatus.Pause) {
                    welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
                    if ((welComeVm != null ? Boolean.valueOf(welComeVm.isNotErrorMove()) : null).booleanValue()) {
                        GotoWelcomeAreaActivity.this.startPauseCountDown();
                    }
                }
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[WelComeVm.WelComeStatus.values().length];

        static {
            $EnumSwitchMapping$0[WelComeVm.WelComeStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[WelComeVm.WelComeStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$0[WelComeVm.WelComeStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$0[WelComeVm.WelComeStatus.NoPoint.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WelComeVm getWelComeVm() {
        return (WelComeVm) this.welComeVm.getValue();
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

    /* JADX WARN: Type inference failed for: r0v26, types: [com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$robotSpeedListener$1] */
    public GotoWelcomeAreaActivity() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_go_to_welcome_area);
        initView();
        bindPresenter();
        initVm();
        initAiVoice();
        showOnTheWay();
    }

    private final void initVm() {
        getWelComeVm().initWelComeName();
        GotoWelcomeAreaActivity gotoWelcomeAreaActivity = this;
        getWelComeVm().getWelComeStatusState().observe(gotoWelcomeAreaActivity, new Observer<WelComeVm.WelComeStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$initVm$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(WelComeVm.WelComeStatus it) {
                String str;
                WelComeVm.WelComeStatus welComeStatus;
                LocationLostDialog locationLostDialog;
                WelComeVm.WelComeStatus welComeStatus2;
                str = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3273d(str, "greeterStatusState  " + it);
                GotoWelcomeAreaActivity.this.isPersenterRun = true;
                if (it != null) {
                    int i = GotoWelcomeAreaActivity.WhenMappings.$EnumSwitchMapping$0[it.ordinal()];
                    if (i == 1) {
                        welComeStatus = GotoWelcomeAreaActivity.this.currentEventStatus;
                        if (it != welComeStatus) {
                            GotoWelcomeAreaActivity.this.showOnTheWay();
                        }
                    } else if (i == 2) {
                        locationLostDialog = GotoWelcomeAreaActivity.this.locationLostDialog;
                        if (locationLostDialog != null) {
                            locationLostDialog.dismiss();
                        }
                        welComeStatus2 = GotoWelcomeAreaActivity.this.currentEventStatus;
                        if (it != welComeStatus2) {
                            GotoWelcomeAreaActivity.this.showOnPause();
                        }
                    } else if (i == 3) {
                        GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = GotoWelcomeAreaActivity.this;
                        gotoWelcomeAreaActivity2.jumpAndFinish(new Intent(gotoWelcomeAreaActivity2, (Class<?>) GreeterMenuActivity.class));
                    } else if (i == 4) {
                        ToastUtils.show(RobotContext.INSTANCE.getContext(), GotoWelcomeAreaActivity.this.getString(C5508R.string.pdStr25_16), new Object[0]);
                        GotoWelcomeAreaActivity gotoWelcomeAreaActivity3 = GotoWelcomeAreaActivity.this;
                        gotoWelcomeAreaActivity3.jumpAndFinish(new Intent(gotoWelcomeAreaActivity3, (Class<?>) GreeterMenuActivity.class));
                    }
                }
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity4 = GotoWelcomeAreaActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                gotoWelcomeAreaActivity4.currentEventStatus = it;
            }
        });
        getWelComeVm().getMoveErrorHelperLiveData().observe(gotoWelcomeAreaActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$initVm$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                RunningErrorHelper runningErrorHelper;
                if (moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = GotoWelcomeAreaActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getWelComeVm().getOnSchedulingLiveData().observe(gotoWelcomeAreaActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$initVm$3
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
                str = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    GotoWelcomeAreaActivity.this.genSchedulingDialog();
                    schedulingDialog3 = GotoWelcomeAreaActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = GotoWelcomeAreaActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        getWelComeVm().getFaceLiveData().observe(gotoWelcomeAreaActivity, new Observer<FaceVideoAnimation>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$initVm$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(FaceVideoAnimation it) {
                FaceVideoView faceVideoView = (FaceVideoView) GotoWelcomeAreaActivity.this._$_findCachedViewById(C5508R.id.face_animation_view);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                faceVideoView.playAnimation(it);
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
            AiVoiceManager.INSTANCE.startAiRecording();
        } else if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            stopPauseCountDown();
            AiVoiceManager.INSTANCE.stopAiRecording();
        } else if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            stopPauseCountDown();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            stopPauseCountDown();
            AiVoiceManager.INSTANCE.stopAiRecording();
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
    }

    private final void bindPresenter() {
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$bindPresenter$1
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
                WelComeVm welComeVm;
                welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
                welComeVm.active();
            }
        });
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.addonDialogDismiss(this.onAIVoiceDialogDismiss);
        AiVoiceManager.INSTANCE.addonDialogShow(this.onAIVoiceDialogShow);
        AiVoiceManager.INSTANCE.attachActivity(this);
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
        AiVoiceManager.INSTANCE.removeonDialogDismiss(this.onAIVoiceDialogDismiss);
        AiVoiceManager.INSTANCE.removeonDialogShow(this.onAIVoiceDialogShow);
        AiVoiceManager.INSTANCE.forceStop();
        AiVoiceManager.INSTANCE.detachActivity();
        unBindPresenter();
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
        this.runningErrorHelper.unbind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWay() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        AiVoiceManager.INSTANCE.forceStop();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getDeliver());
        LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.FORWARD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnPause() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        showCountdownLayout();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
        if (this.runningErrorHelper.isErrorShowing()) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            hideCountdownLayout();
        } else {
            AiVoiceManager.INSTANCE.startAiRecording();
        }
        WelComeVm welComeVm = getWelComeVm();
        if ((welComeVm != null ? Boolean.valueOf(welComeVm.isNotErrorMove()) : null).booleanValue()) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.STOP);
        }
        startPauseCountDown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCountdown(int time) {
        try {
            int sp2px = SizeUtils.sp2px(this, 24.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr25_20);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr25_20)");
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
            getWelComeVm().cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        if (!getWelComeVm().isNotErrorMove()) {
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
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                WelComeVm welComeVm;
                welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
                if (welComeVm != null) {
                    welComeVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String str;
                str = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                GotoWelcomeAreaActivity.this.setCountdown((int) ((millisUntilFinished / ((long) 1000)) + 1));
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
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.greeter.GotoWelcomeAreaActivity$genSchedulingDialog$1
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
                    WelComeVm welComeVm;
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = GotoWelcomeAreaActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    welComeVm = GotoWelcomeAreaActivity.this.getWelComeVm();
                    welComeVm.pause();
                }
            });
        }
    }
}
