package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract;
import com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInPresenter;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.GoToWelcomeTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.warkiz.widget.SizeUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
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

/* compiled from: GotoWelcomeAreaActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ù\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001T\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\\\u001a\u00020'H\u0002J\b\u0010]\u001a\u00020'H\u0002J\b\u0010^\u001a\u00020'H\u0002J\b\u0010_\u001a\u00020'H\u0002J\b\u0010`\u001a\u00020$H\u0016J\u0010\u0010a\u001a\u00020'2\u0006\u0010b\u001a\u00020cH\u0016J\u0012\u0010d\u001a\u00020'2\b\u0010e\u001a\u0004\u0018\u00010fH\u0014J\u001a\u0010g\u001a\u00020'2\b\u0010h\u001a\u0004\u0018\u00010\u00072\u0006\u0010i\u001a\u00020$H\u0016J\u0010\u0010j\u001a\u00020'2\u0006\u0010k\u001a\u00020\tH\u0002J\b\u0010l\u001a\u00020'H\u0002J\b\u0010m\u001a\u00020'H\u0002J\u0010\u0010n\u001a\u00020'2\u0006\u0010o\u001a\u00020\tH\u0002J\u0010\u0010p\u001a\u00020'2\u0006\u0010q\u001a\u00020$H\u0002J\b\u0010r\u001a\u00020'H\u0002J\u0010\u0010s\u001a\u00020'2\u0006\u0010F\u001a\u00020!H\u0016J\b\u0010t\u001a\u00020'H\u0002J\b\u0010u\u001a\u00020'H\u0002J\b\u0010v\u001a\u00020'H\u0002J\u0018\u0010w\u001a\u00020'2\u0006\u0010o\u001a\u00020x2\u0006\u0010y\u001a\u00020zH\u0016J\b\u0010{\u001a\u00020'H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0013\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0013\u001a\u0004\b,\u0010-R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00105\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R@\u00106\u001a4\u0012\u0013\u0012\u00110$¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110$¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020'\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020'0&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010?\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010B\u001a2\u0012\u0013\u0012\u00110C¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110E¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020'07X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020IX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010J\u001a\u001d\u0012\u0013\u0012\u00110L¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(M\u0012\u0004\u0012\u00020'0KX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010N\u001a\u0004\u0018\u00010O8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bR\u0010\u0013\u001a\u0004\bP\u0010QR\u0010\u0010S\u001a\u00020TX\u0082\u0004¢\u0006\u0004\n\u0002\u0010UR\u000e\u0010V\u001a\u00020WX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010X\u001a\u0004\u0018\u00010YX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010Z\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010[\u0012\u0004\u0012\u00020'0KX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006|"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GotoWelcomeAreaActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInContract$ViewInterface;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "backToWaitAreaPresenter", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaPresenter;", "getBackToWaitAreaPresenter", "()Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaPresenter;", "backToWaitAreaPresenter$delegate", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "callSetVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "getCallSetVM", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "callSetVM$delegate", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewEvent;", "currentPauseFeature", "isNeedPwdPro", "", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "onCancelGreeterBackClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "pauseVoiceTask", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "scheduleEventLister", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "triggerEvent", "scheduleFillInPresenter", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter;", "getScheduleFillInPresenter", "()Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter;", "scheduleFillInPresenter$delegate", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/greeter/GotoWelcomeAreaActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GotoWelcomeAreaActivity$singleBatteryListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "bindPresenter", "hideCountdownLayout", "initAiVoice", "initView", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFillIn", "destination", "isFillIn", "onPauseFeatureChange", "type", "release", "releaseDataAndFinish", "setCountdown", "time", "setPauseLayout", "isShow", "showCountdownLayout", "showGoHomeEvent", "showOnPause", "showOnTheWay", "showScheduleFill", "showTimeLeft", "", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GotoWelcomeAreaActivity extends MyBaseActivity implements AutoResumeCountDownContract.ViewInterface, BackToWaitAreaContract.ViewInterface, ScheduleFillInContract.ViewInterface {
    private final int TYPE_PAUSE_FEATURE_AIVOICE;
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private final int TYPE_PAUSE_FEATURE_TOUCH;
    private HashMap _$_findViewCache;
    private final BeeperCallHelper beeperCallHelper;

    /* renamed from: callSetVM$delegate, reason: from kotlin metadata */
    private final Lazy callSetVM;
    private BackToWaitAreaContract.ViewEvent currentEventStatus;
    private int currentPauseFeature;
    private boolean isNeedPwdPro;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private VoiceTask locationLostVoiceTask;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private final MotionEventHelper motionEventHelper;
    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask;
    private final OnLazyVoiceClickListener onCancelGreeterBackClick;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceAnimationViewClick;
    private Function0<Unit> onFallDropCallBack;
    private Function0<Unit> onLostLocationLostCallback;
    private final OnLazyClickListener onPauseLayoutClick;
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private final VoiceTask pauseVoiceTask;
    private final RunningErrorHelper runningErrorHelper;
    private final Function1<ScheduleContract.TriggerEvent, Unit> scheduleEventLister;

    /* renamed from: scheduleFillInPresenter$delegate, reason: from kotlin metadata */
    private final Lazy scheduleFillInPresenter;
    private final GotoWelcomeAreaActivity$singleBatteryListener$1 singleBatteryListener;
    private final TouchSensorEventHelper touchSensorEventHelper;
    private VoiceInteractionDialog voiceInteractionDialog;
    private final Function1<WakeupInfo, Unit> wakeupListener;
    private final String TAG = "GotoWelcomeAreaActivity";

    /* renamed from: backToWaitAreaPresenter$delegate, reason: from kotlin metadata */
    private final Lazy backToWaitAreaPresenter = LazyKt.lazy(new Function0<BackToWaitAreaPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$backToWaitAreaPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BackToWaitAreaPresenter invoke() {
            BackToWaitAreaPresenter backToWaitAreaPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(BackToWaitAreaPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(BackToWaitAreaPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                backToWaitAreaPresenter = new BackToWaitAreaPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(BackToWaitAreaPresenter.class).toString(), backToWaitAreaPresenter);
            } else {
                if (!(basePresenterInterface instanceof BackToWaitAreaPresenter)) {
                    basePresenterInterface = null;
                }
                backToWaitAreaPresenter = (BackToWaitAreaPresenter) basePresenterInterface;
            }
            if (backToWaitAreaPresenter == null) {
                Intrinsics.throwNpe();
            }
            return backToWaitAreaPresenter;
        }
    });

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$autoResumeCountDownPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AutoResumeCountDownPresenter invoke() {
            AutoResumeCountDownPresenter autoResumeCountDownPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                autoResumeCountDownPresenter = new AutoResumeCountDownPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class).toString(), autoResumeCountDownPresenter);
            } else {
                if (!(basePresenterInterface instanceof AutoResumeCountDownPresenter)) {
                    basePresenterInterface = null;
                }
                autoResumeCountDownPresenter = (AutoResumeCountDownPresenter) basePresenterInterface;
            }
            if (autoResumeCountDownPresenter == null) {
                Intrinsics.throwNpe();
            }
            return autoResumeCountDownPresenter;
        }
    });

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BackToWaitAreaPresenter getBackToWaitAreaPresenter() {
        return (BackToWaitAreaPresenter) this.backToWaitAreaPresenter.getValue();
    }

    private final CallSettingVM getCallSetVM() {
        return (CallSettingVM) this.callSetVM.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ScheduleFillInPresenter getScheduleFillInPresenter() {
        return (ScheduleFillInPresenter) this.scheduleFillInPresenter.getValue();
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

    public GotoWelcomeAreaActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$motionEventHelper$1$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PeripheralsSceneUtil.INSTANCE.showGreeterOnTheWay();
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.runningErrorHelper = new RunningErrorHelper();
        this.touchSensorEventHelper = new TouchSensorEventHelper();
        this.locationLostVoiceTask = new VoiceTask(-1L, VoiceItem.voice17_1);
        this.movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.pauseVoiceTask = new VoiceTask(-1L, VoiceItem.voice7_2);
        this.currentEventStatus = BackToWaitAreaContract.ViewEvent.ON_THE_WAY;
        this.scheduleFillInPresenter = LazyKt.lazy(new Function0<ScheduleFillInPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$scheduleFillInPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ScheduleFillInPresenter invoke() {
                ScheduleFillInPresenter scheduleFillInPresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(ScheduleFillInPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(ScheduleFillInPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    scheduleFillInPresenter = new ScheduleFillInPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(ScheduleFillInPresenter.class).toString(), scheduleFillInPresenter);
                } else {
                    if (!(basePresenterInterface instanceof ScheduleFillInPresenter)) {
                        basePresenterInterface = null;
                    }
                    scheduleFillInPresenter = (ScheduleFillInPresenter) basePresenterInterface;
                }
                return scheduleFillInPresenter;
            }
        });
        this.callSetVM = LazyKt.lazy(new Function0<CallSettingVM>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$callSetVM$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CallSettingVM invoke() {
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                ViewModel viewModel = new ViewModelProvider(gotoWelcomeAreaActivity, new ViewModelProvider.AndroidViewModelFactory(gotoWelcomeAreaActivity.getApplication())).get(CallSettingVM.class);
                Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
                return (CallSettingVM) ((BaseViewModel) viewModel);
            }
        });
        this.beeperCallHelper = new BeeperCallHelper();
        this.wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$wakeupListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WakeupInfo wakeupInfo) {
                invoke2(wakeupInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WakeupInfo wakeupInfo) {
                VoiceInteractionDialog voiceInteractionDialog;
                VoiceInteractionDialog voiceInteractionDialog2;
                String str;
                BackToWaitAreaContract.ViewEvent viewEvent;
                BackToWaitAreaContract.ViewEvent viewEvent2;
                int i;
                VoiceInteractionDialog voiceInteractionDialog3;
                GoToWelcomeTrack.INSTANCE.onWakeup();
                voiceInteractionDialog = GotoWelcomeAreaActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog == null) {
                    GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                    gotoWelcomeAreaActivity.voiceInteractionDialog = new VoiceInteractionDialog(gotoWelcomeAreaActivity);
                    voiceInteractionDialog3 = GotoWelcomeAreaActivity.this.voiceInteractionDialog;
                    if (voiceInteractionDialog3 != null) {
                        voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$wakeupListener$1.1
                            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                            public void onDismiss() {
                                String str2;
                                BackToWaitAreaContract.ViewEvent viewEvent3;
                                BackToWaitAreaContract.ViewEvent viewEvent4;
                                int i2;
                                str2 = GotoWelcomeAreaActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("wakeupListener onDismiss ");
                                viewEvent3 = GotoWelcomeAreaActivity.this.currentEventStatus;
                                sb.append(viewEvent3);
                                Pdlog.m3273d(str2, sb.toString());
                                viewEvent4 = GotoWelcomeAreaActivity.this.currentEventStatus;
                                if (viewEvent4 == BackToWaitAreaContract.ViewEvent.PAUSE) {
                                    GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = GotoWelcomeAreaActivity.this;
                                    i2 = GotoWelcomeAreaActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                                    gotoWelcomeAreaActivity2.onPauseFeatureChange(i2);
                                }
                            }
                        });
                    }
                }
                voiceInteractionDialog2 = GotoWelcomeAreaActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                    return;
                }
                voiceInteractionDialog2.show();
                str = GotoWelcomeAreaActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("wakeupListener show ");
                viewEvent = GotoWelcomeAreaActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = GotoWelcomeAreaActivity.this.currentEventStatus;
                if (viewEvent2 == BackToWaitAreaContract.ViewEvent.PAUSE) {
                    GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = GotoWelcomeAreaActivity.this;
                    i = gotoWelcomeAreaActivity2.TYPE_PAUSE_FEATURE_AIVOICE;
                    gotoWelcomeAreaActivity2.onPauseFeatureChange(i);
                }
            }
        };
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onErrorDialogShowStatus$1
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
                    PeripheralsSceneUtil.INSTANCE.showRunError();
                    if (z2) {
                        GoToWelcomeTrack.INSTANCE.onEmergencyStop();
                    }
                }
            }
        };
        this.scheduleEventLister = new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$scheduleEventLister$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == ScheduleContract.TriggerEvent.AVOID) {
                    GoToWelcomeTrack.INSTANCE.onStartScheduling();
                } else if (it == ScheduleContract.TriggerEvent.NORMAL) {
                    GoToWelcomeTrack.INSTANCE.onStopScheduling();
                }
            }
        };
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                GoToWelcomeTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onFallDropCallBack$1
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
                GoToWelcomeTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Abnormal);
                GotoWelcomeAreaActivity.this.releaseDataAndFinish();
            }
        };
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onFaceAnimationViewClick$1
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
                BackToWaitAreaContract.ViewEvent viewEvent;
                BackToWaitAreaContract.ViewEvent viewEvent2;
                BackToWaitAreaPresenter backToWaitAreaPresenter;
                BackToWaitAreaContract.ViewEvent viewEvent3;
                str = GotoWelcomeAreaActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                viewEvent = GotoWelcomeAreaActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = GotoWelcomeAreaActivity.this.currentEventStatus;
                if (viewEvent2 != BackToWaitAreaContract.ViewEvent.ON_THE_WAY) {
                    viewEvent3 = GotoWelcomeAreaActivity.this.currentEventStatus;
                    if (viewEvent3 != BackToWaitAreaContract.ViewEvent.ACTIVE) {
                        return;
                    }
                }
                backToWaitAreaPresenter = GotoWelcomeAreaActivity.this.getBackToWaitAreaPresenter();
                backToWaitAreaPresenter.actionPause();
            }
        }, 3, null);
        this.onCancelGreeterBackClick = new GotoWelcomeAreaActivity$onCancelGreeterBackClick$1(this);
        this.onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onPauseLayoutClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                BackToWaitAreaContract.ViewEvent viewEvent;
                BackToWaitAreaPresenter backToWaitAreaPresenter;
                str = GotoWelcomeAreaActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onPauseLayoutClick current status = ");
                viewEvent = GotoWelcomeAreaActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                backToWaitAreaPresenter = GotoWelcomeAreaActivity.this.getBackToWaitAreaPresenter();
                backToWaitAreaPresenter.actionActive();
            }
        };
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$locationLostTouchCancelCallback$1
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
                ((FaceVideoView) GotoWelcomeAreaActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_ERROR;
                gotoWelcomeAreaActivity.onPauseFeatureChange(i);
            }
        };
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onLostLocationLostCallback$1
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
                VoiceTask voiceTask;
                int i;
                BackToWaitAreaPresenter backToWaitAreaPresenter;
                str = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback");
                if (CheckLocationHelper.INSTANCE.getLocateCase() == LocateCase.Marker || CheckLocationHelper.INSTANCE.getLocateCase() == LocateCase.LaserMark) {
                    ((FaceVideoView) GotoWelcomeAreaActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    voiceTask = GotoWelcomeAreaActivity.this.locationLostVoiceTask;
                    voicePlayer.play(voiceTask);
                    GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                    i = gotoWelcomeAreaActivity.TYPE_PAUSE_FEATURE_ERROR;
                    gotoWelcomeAreaActivity.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    return;
                }
                GoToWelcomeTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                backToWaitAreaPresenter = GotoWelcomeAreaActivity.this.getBackToWaitAreaPresenter();
                backToWaitAreaPresenter.actionCancelTask();
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = GotoWelcomeAreaActivity.this;
                gotoWelcomeAreaActivity2.jumpAndFinish(new Intent(gotoWelcomeAreaActivity2, (Class<?>) LaserRunningLocationLostActivity.class));
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$onErrorClearCallback$1
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
                BackToWaitAreaPresenter backToWaitAreaPresenter;
                str = GotoWelcomeAreaActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) GotoWelcomeAreaActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) GotoWelcomeAreaActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    backToWaitAreaPresenter = GotoWelcomeAreaActivity.this.getBackToWaitAreaPresenter();
                    backToWaitAreaPresenter.actionPause();
                }
            }
        };
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_AIVOICE = 2;
        this.TYPE_PAUSE_FEATURE_TOUCH = 3;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
        this.singleBatteryListener = new GotoWelcomeAreaActivity$singleBatteryListener$1(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_go_to_welcome_area);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new GotoWelcomeAreaActivity$onCreate$1(null), 2, null);
        boolean booleanExtra = getIntent().getBooleanExtra(GreeterActivity.INSTANCE.getKEY_GREETE_MODE(), false);
        this.isNeedPwdPro = booleanExtra && Constans.INSTANCE.getGreeterExitSwitch();
        Pdlog.m3273d(this.TAG, "isGreeter:" + booleanExtra + "####isNeedPwdPro:" + this.isNeedPwdPro);
        initView();
        bindPresenter();
        PeripheralsSceneUtil.INSTANCE.stopAll();
        BackToWaitAreaContract.PresenterInterface.DefaultImpls.actionGoWaitArea$default(getBackToWaitAreaPresenter(), null, 1, null);
        initAiVoice();
        showOnTheWay();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            getBackToWaitAreaPresenter().actionPause();
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            getBackToWaitAreaPresenter().actionPauseNoTimer();
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            getBackToWaitAreaPresenter().actionPauseNoTimer();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            this.touchSensorEventHelper.stopCurrentAnimation();
        } else if (type == this.TYPE_PAUSE_FEATURE_TOUCH) {
            getBackToWaitAreaPresenter().actionPauseNoTimer();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            hideCountdownLayout();
        }
    }

    private final void initView() {
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
        musicPlayerHelper.setOpenBirthdaySwitch(false);
        PeripheralsSceneUtil.INSTANCE.setPlayMode(PeripheralsSceneUtil.Mode.Welcome);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        ((ConstraintLayout) _$_findCachedViewById(C4188R.id.base_layout)).setOnClickListener(this.onPauseLayoutClick);
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        ((LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task)).setOnClickListener(this.onCancelGreeterBackClick);
        _$_findCachedViewById(C4188R.id.goto_areat_schedule_fill).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.btn_go_to_task_ui)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                ScheduleFillInPresenter scheduleFillInPresenter;
                super.onSingleClick();
                scheduleFillInPresenter = GotoWelcomeAreaActivity.this.getScheduleFillInPresenter();
                if (scheduleFillInPresenter != null) {
                    scheduleFillInPresenter.quitFillIn();
                }
                GotoWelcomeAreaActivity gotoWelcomeAreaActivity = GotoWelcomeAreaActivity.this;
                gotoWelcomeAreaActivity.jumpAndFinish(new Intent(gotoWelcomeAreaActivity, (Class<?>) GreeterMenuActivity.class));
            }
        });
    }

    private final void bindPresenter() {
        GotoWelcomeAreaActivity gotoWelcomeAreaActivity = this;
        BeeperCallHelper.bind$default(this.beeperCallHelper, gotoWelcomeAreaActivity, false, false, 6, null);
        GoToWelcomeTrack goToWelcomeTrack = GoToWelcomeTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@GotoWelcomeAreaActi…ty::class.java.simpleName");
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        goToWelcomeTrack.onCreateTask(simpleName, IntentExtKt.getSceneId$default(intent, null, 1, null));
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        GotoWelcomeAreaActivity gotoWelcomeAreaActivity2 = this;
        getBackToWaitAreaPresenter().replaceView(gotoWelcomeAreaActivity2);
        getAutoResumeCountDownPresenter().replaceView(gotoWelcomeAreaActivity2);
        this.motionEventHelper.setOnTheWayAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isLeadingFace()));
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(voiceTaskWrapper, face_animation_view2, this.scheduleEventLister);
        this.motionEventHelper.setCurrentMovingText(SpUtils.get(this, "key_lattice_welcome_area", ""));
        this.motionEventHelper.setOnVoicePlayListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$bindPresenter$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent) {
                invoke2(playEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PlayEvent it) {
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                Intrinsics.checkParameterIsNotNull(it, "it");
                mDangerousAreaTipHelper = GotoWelcomeAreaActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, gotoWelcomeAreaActivity, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GotoWelcomeAreaActivity$bindPresenter$2
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
                BackToWaitAreaPresenter backToWaitAreaPresenter;
                backToWaitAreaPresenter = GotoWelcomeAreaActivity.this.getBackToWaitAreaPresenter();
                backToWaitAreaPresenter.actionActive();
            }
        });
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.singleBatteryListener.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        getMDangerousAreaTipHelper().bindLifecycle(this);
        ScheduleFillInPresenter scheduleFillInPresenter = getScheduleFillInPresenter();
        if (scheduleFillInPresenter != null) {
            scheduleFillInPresenter.replaceView(gotoWelcomeAreaActivity2);
        }
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        super.jumpAndFinish(intent);
        release();
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public boolean isBusyState() {
        Boolean value = getCallSetVM().getGreeterPointCanCallSwitchLD().getValue();
        if (value == null) {
            value = false;
        }
        return !value.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        BackToWaitAreaPresenter backToWaitAreaPresenter = getBackToWaitAreaPresenter();
        if (backToWaitAreaPresenter != null) {
            backToWaitAreaPresenter.actionPauseNoTimer();
        }
        BackToWaitAreaPresenter backToWaitAreaPresenter2 = getBackToWaitAreaPresenter();
        if (backToWaitAreaPresenter2 != null) {
            backToWaitAreaPresenter2.actionCancelTask();
        }
        release();
        finish();
    }

    private final void release() {
        VoiceInteractionDialog voiceInteractionDialog = this.voiceInteractionDialog;
        if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
            voiceInteractionDialog.dismiss();
        }
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        MusicPlayerHelper.getInstance().stopPlay();
        MusicPlayerHelper.getInstance().reset();
        unBindPresenter();
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        this.touchSensorEventHelper.unBindPresent();
        GotoWelcomeAreaActivity gotoWelcomeAreaActivity = this;
        getBackToWaitAreaPresenter().removeView(gotoWelcomeAreaActivity);
        this.motionEventHelper.unBind();
        getAutoResumeCountDownPresenter().removeView(gotoWelcomeAreaActivity);
        ScheduleFillInPresenter scheduleFillInPresenter = getScheduleFillInPresenter();
        if (scheduleFillInPresenter != null) {
            scheduleFillInPresenter.removeView(gotoWelcomeAreaActivity);
        }
        BackToWaitAreaPresenter.INSTANCE.setMIsFillIn(false);
    }

    private final void showOnTheWay() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        View goto_areat_schedule_fill = _$_findCachedViewById(C4188R.id.goto_areat_schedule_fill);
        Intrinsics.checkExpressionValueIsNotNull(goto_areat_schedule_fill, "goto_areat_schedule_fill");
        goto_areat_schedule_fill.setVisibility(8);
        AiVoiceManager.INSTANCE.stopAiRecording();
        this.touchSensorEventHelper.setCanHandle(true, true);
        this.motionEventHelper.setCanHandleMovingEvent(true);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isLeadingFace()));
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        String string = getString(C4188R.string.pdStr25_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_1)");
        faceVideoView.setTarget(string);
        PeripheralsSceneUtil.INSTANCE.showGreeterOnTheWay();
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.GUIDE_BACK);
    }

    private final void showScheduleFill() {
        Pdlog.m3273d(this.TAG, "showScheduleFill");
        setPauseLayout(false);
        View goto_areat_schedule_fill = _$_findCachedViewById(C4188R.id.goto_areat_schedule_fill);
        Intrinsics.checkExpressionValueIsNotNull(goto_areat_schedule_fill, "goto_areat_schedule_fill");
        goto_areat_schedule_fill.setVisibility(0);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        AiVoiceManager.INSTANCE.stopAiRecording();
        this.motionEventHelper.setCanHandleMovingEvent(false);
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        MusicPlayerHelper.getInstance().pausePlay();
    }

    private final void showOnPause() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        showCountdownLayout();
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        this.motionEventHelper.setCanHandleMovingEvent(false);
        if (this.runningErrorHelper.isErrorShowing()) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            hideCountdownLayout();
        } else {
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        }
        PeripheralsSceneUtil.INSTANCE.showGreeterPause();
        MusicPlayerHelper.getInstance().pausePlay();
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract.ViewInterface
    public void showGoHomeEvent(BackToWaitAreaContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showMovingEvent：" + event + " ;currentEventStatus = " + this.currentEventStatus);
        switch (event) {
            case ON_THE_WAY:
                GoToWelcomeTrack.INSTANCE.onMove();
                if (this.currentEventStatus != event) {
                    showOnTheWay();
                    break;
                }
                break;
            case ACTIVE:
                GoToWelcomeTrack.INSTANCE.onMove();
                BackToWaitAreaContract.ViewEvent viewEvent = this.currentEventStatus;
                if (viewEvent != event && viewEvent != BackToWaitAreaContract.ViewEvent.ON_THE_WAY) {
                    showOnTheWay();
                    break;
                }
                break;
            case PAUSE:
                GoToWelcomeTrack.INSTANCE.onPause();
                if (this.currentEventStatus != event) {
                    VoicePlayer.INSTANCE.play(this.pauseVoiceTask);
                    showOnPause();
                    break;
                }
                break;
            case DONE:
                GoToWelcomeTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                jumpAndFinish(new Intent(this, (Class<?>) GreeterMenuActivity.class));
                break;
            case TEMPORARY_POINT:
                GoToWelcomeTrack.INSTANCE.onPause();
                showScheduleFill();
                break;
            case NO_DINING_OUTLET:
                GoToWelcomeTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.NoTarget);
                ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C4188R.string.pdStr25_16), new Object[0]);
                jumpAndFinish(new Intent(this, (Class<?>) GreeterMenuActivity.class));
                break;
        }
        this.currentEventStatus = event;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.ViewInterface
    public void showTimeLeft(long time, AutoResumeCountDownContract.Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Pdlog.m3273d(this.TAG, "showTimeLeft " + time);
        setCountdown((int) time);
    }

    private final void setCountdown(int time) {
        try {
            int sp2px = SizeUtils.sp2px(this, 24.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr25_20);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr25_20)");
            Object[] objArr = new Object[1];
            objArr[0] = LanguageUtils.INSTANCE.isALaBo() ? String.valueOf(time) : Integer.valueOf(time);
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            int indexOf$default = StringsKt.indexOf$default((CharSequence) format, String.valueOf(time), 0, false, 6, (Object) null);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, sp2px, ColorStateList.valueOf(getColor(C4188R.color.theme_main_color)), null);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(textAppearanceSpan, indexOf$default, String.valueOf(time).length() + indexOf$default, 34);
            showCountdownLayout();
            TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            countdown_tv.setText(spannableStringBuilder);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    private final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(4);
    }

    private final void showCountdownLayout() {
        setPauseLayout(true);
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        if (countdown_tv.getVisibility() != 0) {
            TextView countdown_tv2 = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
            countdown_tv2.setVisibility(0);
        }
    }

    private final void setPauseLayout(boolean isShow) {
        if (isShow) {
            RelativeLayout pause_info_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_info_layout);
            Intrinsics.checkExpressionValueIsNotNull(pause_info_layout, "pause_info_layout");
            ViewExtKt.isShowView(pause_info_layout);
            LinearLayout cancel_back_task = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task);
            Intrinsics.checkExpressionValueIsNotNull(cancel_back_task, "cancel_back_task");
            ViewExtKt.isShowView(cancel_back_task);
            TextView pause_info_tip = (TextView) _$_findCachedViewById(C4188R.id.pause_info_tip);
            Intrinsics.checkExpressionValueIsNotNull(pause_info_tip, "pause_info_tip");
            ViewExtKt.isShowView(pause_info_tip);
            return;
        }
        RelativeLayout pause_info_layout2 = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_info_layout2, "pause_info_layout");
        ViewExtKt.isGoneView(pause_info_layout2);
        LinearLayout cancel_back_task2 = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task);
        Intrinsics.checkExpressionValueIsNotNull(cancel_back_task2, "cancel_back_task");
        ViewExtKt.isGoneView(cancel_back_task2);
        TextView pause_info_tip2 = (TextView) _$_findCachedViewById(C4188R.id.pause_info_tip);
        Intrinsics.checkExpressionValueIsNotNull(pause_info_tip2, "pause_info_tip");
        ViewExtKt.isGoneView(pause_info_tip2);
    }

    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract.ViewInterface
    public void onFillIn(String destination, boolean isFillIn) {
        Pdlog.m3273d(this.TAG, "onFillIn()  destination = " + destination + " isFillIn = " + isFillIn);
        BackToWaitAreaPresenter.INSTANCE.setMIsFillIn(isFillIn);
        if (isFillIn) {
            if (TextUtils.isEmpty(destination)) {
                return;
            }
            BackToWaitAreaContract.PresenterInterface.DefaultImpls.actionGoWaitArea$default(getBackToWaitAreaPresenter(), null, 1, null);
        } else {
            View goto_areat_schedule_fill = _$_findCachedViewById(C4188R.id.goto_areat_schedule_fill);
            Intrinsics.checkExpressionValueIsNotNull(goto_areat_schedule_fill, "goto_areat_schedule_fill");
            if (goto_areat_schedule_fill.isShown()) {
                getBackToWaitAreaPresenter().onDone();
            }
        }
    }
}
