package com.pudutech.peanut.robot_ui.p063ui;

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
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.TurnBackActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.BeeperCallHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.SimpleMusicPlayerCallbck;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.GoHomeVm;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TurnBackActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015*\u0001:\u0018\u0000 d2\u00020\u0001:\u0001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010@\u001a\u00020!H\u0002J\b\u0010A\u001a\u00020!H\u0002J\b\u0010B\u001a\u00020!H\u0002J\b\u0010C\u001a\u00020!H\u0002J\b\u0010D\u001a\u00020!H\u0002J\u0012\u0010E\u001a\u00020!2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J1\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020\u00072\b\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010L\u001a\u00020\u001b2\b\u0010F\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0002\u0010MJ\u0012\u0010N\u001a\u00020!2\b\u0010O\u001a\u0004\u0018\u00010PH\u0014J\b\u0010Q\u001a\u00020!H\u0014J\b\u0010R\u001a\u00020!H\u0014J\u0010\u0010S\u001a\u00020!2\u0006\u0010T\u001a\u00020\u0007H\u0002J\b\u0010U\u001a\u00020!H\u0014J\b\u0010V\u001a\u00020!H\u0014J\b\u0010W\u001a\u00020!H\u0014J\u0010\u0010X\u001a\u00020!2\u0006\u0010Y\u001a\u00020\u001bH\u0016J\b\u0010Z\u001a\u00020!H\u0002J\u0010\u0010[\u001a\u00020!2\u0006\u0010\\\u001a\u00020\u0007H\u0002J\b\u0010]\u001a\u00020!H\u0002J\b\u0010^\u001a\u00020!H\u0002J\b\u0010_\u001a\u00020!H\u0002J\u0010\u0010`\u001a\u00020!2\u0006\u0010\\\u001a\u00020\u0013H\u0002J\b\u0010a\u001a\u00020!H\u0002J\b\u0010b\u001a\u00020!H\u0002J\b\u0010c\u001a\u00020!H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020!0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010(\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0006\u0012\u0004\u0018\u00010!0)X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0006\u0012\u0004\u0018\u00010!0)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010.\u001a4\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020!\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00106\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0004\n\u0002\u0010;R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006e"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/TurnBackActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "beeperCallHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "currentEventStatus", "Lcom/pudutech/peanut/robot_ui/viewmodel/GoHomeVm$BackHomeStatus;", "currentPauseFeature", "deliveryType", "functionClickTime", "", "goHomeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/GoHomeVm;", "getGoHomeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/GoHomeVm;", "goHomeVm$delegate", "Lkotlin/Lazy;", "isComeFromTransferDishes", "", "isFirstStart", "isPersenterRun", "isRelease", "jumpMethod", "Lkotlin/Function0;", "", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "locationLostTouchCancelCallback", "mCountDownTimer", "Landroid/os/CountDownTimer;", "needPlayerThanks", "onAIVoiceDialogDismiss", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "onAIVoiceDialogShow", "onCancelTurnBackClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyVoiceClickListener;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/TurnBackActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/TurnBackActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "bindPresenter", "genSchedulingDialog", "hideCountdownLayout", "initAiVoice", "initVm", "jumpAndFinish", "i", "Landroid/content/Intent;", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPauseFeatureChange", "type", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "setCountdown", "time", "showCountdownLayout", "showOnPauseStatus", "showOnTheWayStatus", "showTimeLeft", "startPauseCountDown", "stopPauseCountDown", "unbindPresenter", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TurnBackActivity extends BatteryBaseActivity {
    public static final String COME_FROM_TRANSFER_DISHES = "COME_FROM_TRANSFER_DISHES";
    public static final int DELIVERY_TYPE = 1;
    public static final String ROBOT_TYPE = "ROBOT_TYPE";
    public static final String SHOW_THANKS = "SHOW_THANKS";
    private HashMap _$_findViewCache;
    private int deliveryType;
    private long functionClickTime;
    private boolean isComeFromTransferDishes;
    private boolean isPersenterRun;
    private boolean isRelease;
    private Function0<Unit> jumpMethod;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private boolean needPlayerThanks;
    private SchedulingDialog schedulingDialog;
    private final String TAG = getClass().getSimpleName();
    private boolean isFirstStart = true;

    /* renamed from: goHomeVm$delegate, reason: from kotlin metadata */
    private final Lazy goHomeVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(GoHomeVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$$special$$inlined$viewModels$1
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
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private GoHomeVm.BackHomeStatus currentEventStatus = GoHomeVm.BackHomeStatus.Moving;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onErrorDialogShowStatus$1
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
                TurnBackActivity turnBackActivity = TurnBackActivity.this;
                i = turnBackActivity.TYPE_PAUSE_FEATURE_ERROR;
                turnBackActivity.onPauseFeatureChange(i);
            } else if (z2) {
                TurnBackActivity.this.finish();
            }
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onLostLocationLostCallback$1
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
            GoHomeVm goHomeVm;
            String str2;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i;
            String str3;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i2;
            LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            str = TurnBackActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker) {
                str3 = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str3, "onLostLocationLostCallback");
                locationLostDialog3 = TurnBackActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    TurnBackActivity turnBackActivity = TurnBackActivity.this;
                    turnBackActivity.locationLostDialog = new LocationLostDialog(turnBackActivity);
                }
                locationLostDialog4 = TurnBackActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                TurnBackActivity turnBackActivity2 = TurnBackActivity.this;
                i2 = turnBackActivity2.TYPE_PAUSE_FEATURE_ERROR;
                turnBackActivity2.onPauseFeatureChange(i2);
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                goHomeVm = TurnBackActivity.this.getGoHomeVm();
                goHomeVm.cancel();
                TurnBackActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onLostLocationLostCallback$1.1
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
                        TurnBackActivity.this.jumpAndFinish(new Intent(TurnBackActivity.this, (Class<?>) LaserRunningLocationLostActivity.class));
                    }
                };
            } else if (locateCase == LocateCase.LaserMark) {
                str2 = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = TurnBackActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    TurnBackActivity turnBackActivity3 = TurnBackActivity.this;
                    turnBackActivity3.locationLostDialog = new LocationLostDialog(turnBackActivity3, "1");
                }
                locationLostDialog2 = TurnBackActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                TurnBackActivity turnBackActivity4 = TurnBackActivity.this;
                i = turnBackActivity4.TYPE_PAUSE_FEATURE_ERROR;
                turnBackActivity4.onPauseFeatureChange(i);
            }
        }
    };
    private final Function0<Unit> locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$locationLostTouchCancelCallback$1
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
            str = TurnBackActivity.this.TAG;
            Pdlog.m3273d(str, "locationLostTouchCancelCallback");
            ((FaceVideoView) TurnBackActivity.this._$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            TurnBackActivity turnBackActivity = TurnBackActivity.this;
            i = turnBackActivity.TYPE_PAUSE_FEATURE_ERROR;
            turnBackActivity.onPauseFeatureChange(i);
        }
    };
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onFaceAnimationViewClick$1
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
            GoHomeVm.BackHomeStatus backHomeStatus;
            GoHomeVm.BackHomeStatus backHomeStatus2;
            boolean z;
            GoHomeVm goHomeVm;
            String str2;
            str = TurnBackActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            backHomeStatus = TurnBackActivity.this.currentEventStatus;
            sb.append(backHomeStatus);
            Pdlog.m3273d(str, sb.toString());
            backHomeStatus2 = TurnBackActivity.this.currentEventStatus;
            if (backHomeStatus2 == GoHomeVm.BackHomeStatus.Moving) {
                z = TurnBackActivity.this.isPersenterRun;
                if (!z) {
                    str2 = TurnBackActivity.this.TAG;
                    Pdlog.m3274e(str2, "onFaceAnimationViewClick false , isPersenterRun = false");
                } else {
                    goHomeVm = TurnBackActivity.this.getGoHomeVm();
                    goHomeVm.pause();
                }
            }
        }
    });
    private final OnLazyClickListener onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onPauseLayoutClick$1
        @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
        public void onSingleClick() {
            String str;
            GoHomeVm.BackHomeStatus backHomeStatus;
            boolean z;
            long j;
            GoHomeVm goHomeVm;
            String str2;
            String str3;
            str = TurnBackActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onPauseLayoutClick current status = ");
            backHomeStatus = TurnBackActivity.this.currentEventStatus;
            sb.append(backHomeStatus);
            Pdlog.m3273d(str, sb.toString());
            z = TurnBackActivity.this.isRelease;
            if (z) {
                str3 = TurnBackActivity.this.TAG;
                Pdlog.m3274e(str3, "goToCruise failed isRelease ");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            j = TurnBackActivity.this.functionClickTime;
            if (currentTimeMillis - j < 1200) {
                str2 = TurnBackActivity.this.TAG;
                Pdlog.m3274e(str2, "onLayoutClick click too fast");
            } else {
                goHomeVm = TurnBackActivity.this.getGoHomeVm();
                goHomeVm.active();
            }
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogDismiss = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onAIVoiceDialogDismiss$1
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
            GoHomeVm.BackHomeStatus backHomeStatus;
            GoHomeVm.BackHomeStatus backHomeStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = TurnBackActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogDismiss ");
            backHomeStatus = TurnBackActivity.this.currentEventStatus;
            sb.append(backHomeStatus);
            Pdlog.m3273d(str, sb.toString());
            backHomeStatus2 = TurnBackActivity.this.currentEventStatus;
            if (backHomeStatus2 == GoHomeVm.BackHomeStatus.Pause) {
                TurnBackActivity turnBackActivity = TurnBackActivity.this;
                i = turnBackActivity.TYPE_PAUSE_FEATURE_NORMAL;
                turnBackActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogShow = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$onAIVoiceDialogShow$1
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
            GoHomeVm.BackHomeStatus backHomeStatus;
            GoHomeVm.BackHomeStatus backHomeStatus2;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = TurnBackActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogShow ");
            backHomeStatus = TurnBackActivity.this.currentEventStatus;
            sb.append(backHomeStatus);
            Pdlog.m3273d(str, sb.toString());
            backHomeStatus2 = TurnBackActivity.this.currentEventStatus;
            if (backHomeStatus2 == GoHomeVm.BackHomeStatus.Pause) {
                TurnBackActivity turnBackActivity = TurnBackActivity.this;
                i = turnBackActivity.TYPE_PAUSE_FEATURE_AIVOICE;
                turnBackActivity.onPauseFeatureChange(i);
            }
        }
    };
    private final OnLazyVoiceClickListener onCancelTurnBackClick = new TurnBackActivity$onCancelTurnBackClick$1(this);
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_AIVOICE = 2;
    private final int TYPE_PAUSE_FEATURE_TOUCH = 3;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private int currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
    private final TurnBackActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            GoHomeVm.BackHomeStatus backHomeStatus;
            GoHomeVm goHomeVm;
            str = TurnBackActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                backHomeStatus = TurnBackActivity.this.currentEventStatus;
                if (backHomeStatus == GoHomeVm.BackHomeStatus.Pause) {
                    goHomeVm = TurnBackActivity.this.getGoHomeVm();
                    if ((goHomeVm != null ? Boolean.valueOf(goHomeVm.isNotErrorMove()) : null).booleanValue()) {
                        TurnBackActivity.this.startPauseCountDown();
                    }
                }
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[GoHomeVm.BackHomeStatus.values().length];

        static {
            $EnumSwitchMapping$0[GoHomeVm.BackHomeStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[GoHomeVm.BackHomeStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$0[GoHomeVm.BackHomeStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$0[GoHomeVm.BackHomeStatus.Finish.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GoHomeVm getGoHomeVm() {
        return (GoHomeVm) this.goHomeVm.getValue();
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

    /* JADX WARN: Type inference failed for: r0v7, types: [com.pudutech.peanut.robot_ui.ui.TurnBackActivity$robotSpeedListener$1] */
    public TurnBackActivity() {
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C5508R.layout.activity_turn_back);
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(true);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new TurnBackActivity$onCreate$1(null), 2, null);
        initAiVoice();
        bindPresenter();
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
        musicPlayerHelper.setOpenBirthdaySwitch(false);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        ((LinearLayout) _$_findCachedViewById(C5508R.id.pause_layout)).setOnClickListener(this.onPauseLayoutClick);
        ((LinearLayout) _$_findCachedViewById(C5508R.id.cancel_back_task)).setOnClickListener(this.onCancelTurnBackClick);
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        this.needPlayerThanks = getIntent().getBooleanExtra("SHOW_THANKS", false);
        this.deliveryType = getIntent().getIntExtra(ROBOT_TYPE, 0);
        this.isComeFromTransferDishes = getIntent().getBooleanExtra("COME_FROM_TRANSFER_DISHES", false);
        showOnTheWayStatus();
        initVm();
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            showCountdownLayout();
            startPauseCountDown();
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            stopPauseCountDown();
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            stopPauseCountDown();
        } else if (type != this.TYPE_PAUSE_FEATURE_TOUCH && type == this.TYPE_PAUSE_FEATURE_ERROR) {
            stopPauseCountDown();
            AiVoiceManager.INSTANCE.stopAiRecording();
            hideCountdownLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnPauseStatus() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        LinearLayout pause_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(0);
        View turn_back_fill_in_layout = _$_findCachedViewById(C5508R.id.turn_back_fill_in_layout);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
        turn_back_fill_in_layout.setVisibility(8);
        this.beeperCallHelper.stopReceiverCallTask();
        startPauseCountDown();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
        if (this.runningErrorHelper.isErrorShowing()) {
            hideCountdownLayout();
        }
        VoicePlayTasks.INSTANCE.finishStop();
        GoHomeVm goHomeVm = getGoHomeVm();
        if ((goHomeVm != null ? Boolean.valueOf(goHomeVm.isNotErrorMove()) : null).booleanValue()) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.STOP);
        }
        MusicPlayerHelper.getInstance().pausePlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWayStatus() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        View turn_back_fill_in_layout = _$_findCachedViewById(C5508R.id.turn_back_fill_in_layout);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
        turn_back_fill_in_layout.setVisibility(8);
        LinearLayout pause_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        hideErrorTipDialog();
        if (this.needPlayerThanks) {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getThanks());
        }
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getDeliver());
        LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.FORWARD);
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.DELIVERY_AND_RETURNING);
        if (this.needPlayerThanks) {
            VoicePlayTasks.playDeliveryFinish$default(VoicePlayTasks.INSTANCE, null, 1, null);
            LightPlayManager.INSTANCE.playThanks();
        }
        boolean z = this.isComeFromTransferDishes;
        this.needPlayerThanks = false;
        this.isComeFromTransferDishes = false;
        this.beeperCallHelper.receiverCallTask(true);
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent i) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(i);
        finish();
    }

    private final void release() {
        this.isRelease = true;
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
        getGoHomeVm().destory();
        MusicPlayerHelper.getInstance().release();
        unbindPresenter();
    }

    private final void initVm() {
        TurnBackActivity turnBackActivity = this;
        getGoHomeVm().getBackHomeStatusState().observe(turnBackActivity, new Observer<GoHomeVm.BackHomeStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(GoHomeVm.BackHomeStatus backHomeStatus) {
                String str;
                GoHomeVm.BackHomeStatus backHomeStatus2;
                LocationLostDialog locationLostDialog;
                GoHomeVm.BackHomeStatus backHomeStatus3;
                Function0 function0;
                int i;
                Function0 function02;
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "backHomeStatusState  " + backHomeStatus);
                TurnBackActivity.this.isPersenterRun = true;
                if (backHomeStatus != null) {
                    int i2 = TurnBackActivity.WhenMappings.$EnumSwitchMapping$0[backHomeStatus.ordinal()];
                    if (i2 == 1) {
                        backHomeStatus2 = TurnBackActivity.this.currentEventStatus;
                        if (backHomeStatus != backHomeStatus2) {
                            TurnBackActivity.this.showOnTheWayStatus();
                        }
                    } else if (i2 == 2) {
                        locationLostDialog = TurnBackActivity.this.locationLostDialog;
                        if (locationLostDialog != null) {
                            locationLostDialog.dismiss();
                        }
                        backHomeStatus3 = TurnBackActivity.this.currentEventStatus;
                        if (backHomeStatus != backHomeStatus3) {
                            TurnBackActivity.this.showOnPauseStatus();
                        }
                    } else if (i2 == 3 || i2 == 4) {
                        function0 = TurnBackActivity.this.jumpMethod;
                        if (function0 != null) {
                            function02 = TurnBackActivity.this.jumpMethod;
                            if (function02 == null) {
                                Intrinsics.throwNpe();
                            }
                            function02.invoke();
                        } else {
                            Intent intent = new Intent(TurnBackActivity.this, (Class<?>) HomeActivity.class);
                            i = TurnBackActivity.this.deliveryType;
                            intent.putExtra(TurnBackActivity.ROBOT_TYPE, i);
                            TurnBackActivity.this.jumpAndFinish(intent);
                        }
                    }
                }
                TurnBackActivity.this.currentEventStatus = backHomeStatus;
            }
        });
        getGoHomeVm().getMoveErrorHelperLiveData().observe(turnBackActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                RunningErrorHelper runningErrorHelper;
                if (moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = TurnBackActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getGoHomeVm().getOnSchedulingLiveData().observe(turnBackActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$3
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
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    TurnBackActivity.this.genSchedulingDialog();
                    schedulingDialog3 = TurnBackActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = TurnBackActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        getGoHomeVm().getOnFillState().observe(turnBackActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                String str;
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    View turn_back_fill_in_layout = TurnBackActivity.this._$_findCachedViewById(C5508R.id.turn_back_fill_in_layout);
                    Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
                    turn_back_fill_in_layout.setVisibility(0);
                } else {
                    View turn_back_fill_in_layout2 = TurnBackActivity.this._$_findCachedViewById(C5508R.id.turn_back_fill_in_layout);
                    Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout2, "turn_back_fill_in_layout");
                    turn_back_fill_in_layout2.setVisibility(8);
                }
            }
        });
        getGoHomeVm().getOnStopBack().observe(turnBackActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                String str;
                int i;
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "onStopBack no TurnBack");
                TurnBackActivity turnBackActivity2 = TurnBackActivity.this;
                ToastUtils.show(turnBackActivity2, turnBackActivity2.getString(C5508R.string.please_setting_back_part), new Object[0]);
                Intent intent = new Intent(TurnBackActivity.this, (Class<?>) HomeActivity.class);
                i = TurnBackActivity.this.deliveryType;
                intent.putExtra(TurnBackActivity.ROBOT_TYPE, i);
                TurnBackActivity.this.jumpAndFinish(intent);
            }
        });
        getGoHomeVm().getFaceLiveData().observe(turnBackActivity, new Observer<FaceVideoAnimation>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(FaceVideoAnimation it) {
                FaceVideoView faceVideoView = (FaceVideoView) TurnBackActivity.this._$_findCachedViewById(C5508R.id.face_animation_view);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                faceVideoView.playAnimation(it);
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.btn_go_to_task_ui)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$initVm$7
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                GoHomeVm goHomeVm;
                goHomeVm = TurnBackActivity.this.getGoHomeVm();
                if (goHomeVm != null) {
                    goHomeVm.cancel();
                }
            }
        });
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$bindPresenter$1
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
                GoHomeVm goHomeVm;
                goHomeVm = TurnBackActivity.this.getGoHomeVm();
                goHomeVm.active();
            }
        });
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, true, 2, null);
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new SimpleMusicPlayerCallbck() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$bindPresenter$2
            @Override // com.pudutech.peanut.robot_ui.p063ui.helper.SimpleMusicPlayerCallbck, com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                String str;
                super.onPrepared();
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "setMusicPlayerStateCallback onPrepared");
            }
        });
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
        this.runningErrorHelper.unbind();
        this.beeperCallHelper.unbind();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state != 2) {
            if (state == 4 && isCharging) {
                getGoHomeVm().pause();
                return;
            }
            return;
        }
        if (i != null) {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
        }
    }

    private final void showTimeLeft(long time) {
        Pdlog.m3273d(this.TAG, "showTimeLeft " + time);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCountdown(int time) {
        try {
            int sp2px = SizeUtils.sp2px(this, 24.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr13_5);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr13_5)");
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        if (!getGoHomeVm().isNotErrorMove()) {
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
        LinearLayout pause_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.pause_layout);
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
        this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$startPauseCountDown$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                GoHomeVm goHomeVm;
                goHomeVm = TurnBackActivity.this.getGoHomeVm();
                if (goHomeVm != null) {
                    goHomeVm.active();
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                String str;
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                TurnBackActivity.this.setCountdown((int) Math.ceil(((double) millisUntilFinished) / ((double) 1000)));
            }
        };
        CountDownTimer countDownTimer2 = this.mCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.start();
        }
    }

    private final void stopPauseCountDown() {
        Pdlog.m3273d(this.TAG, "stopPauseCountDown ");
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
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.TurnBackActivity$genSchedulingDialog$1
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
                    GoHomeVm goHomeVm;
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = TurnBackActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    goHomeVm = TurnBackActivity.this.getGoHomeVm();
                    goHomeVm.pause();
                }
            });
        }
    }
}
