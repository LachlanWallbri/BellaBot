package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract;
import com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.TurnBackTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
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
import kotlin.TypeCastException;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ó\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001W\u0018\u0000 ~2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001~B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010_\u001a\u00020*H\u0002J\b\u0010`\u001a\u00020*H\u0002J\b\u0010a\u001a\u00020*H\u0002J\b\u0010b\u001a\u00020\"H\u0016J\u0010\u0010c\u001a\u00020*2\u0006\u0010d\u001a\u00020eH\u0016J\u0012\u0010f\u001a\u00020*2\b\u0010g\u001a\u0004\u0018\u00010hH\u0014J\b\u0010i\u001a\u00020*H\u0014J\b\u0010j\u001a\u00020*H\u0014J\u0010\u0010k\u001a\u00020*2\u0006\u0010l\u001a\u00020\tH\u0002J\b\u0010m\u001a\u00020*H\u0014J\b\u0010n\u001a\u00020*H\u0014J\b\u0010o\u001a\u00020*H\u0014J\u0010\u0010p\u001a\u00020*2\u0006\u0010q\u001a\u00020\"H\u0016J\b\u0010r\u001a\u00020*H\u0002J\b\u0010s\u001a\u00020*H\u0002J\u0010\u0010t\u001a\u00020*2\u0006\u0010u\u001a\u00020\tH\u0002J\b\u0010v\u001a\u00020*H\u0002J\u0010\u0010w\u001a\u00020*2\u0006\u0010O\u001a\u00020\u0017H\u0016J\b\u0010x\u001a\u00020*H\u0002J\b\u0010y\u001a\u00020*H\u0002J\u0018\u0010z\u001a\u00020*2\u0006\u0010u\u001a\u00020\u001b2\u0006\u0010{\u001a\u00020|H\u0016J\b\u0010}\u001a\u00020*H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0013\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u0013\u001a\u0004\b/\u00100R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020*0:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010<\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010=\u001a4\u0012\u0013\u0012\u00110\"¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\"¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(B\u0012\u0004\u0012\u00020*\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010F\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010I\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020*0:X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010K\u001a2\u0012\u0013\u0012\u00110L¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110N¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(O\u0012\u0004\u0012\u00020*0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020RX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010S\u001a\u001d\u0012\u0013\u0012\u00110T¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(U\u0012\u0004\u0012\u00020*0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u00020WX\u0082\u0004¢\u0006\u0004\n\u0002\u0010XR\u000e\u0010Y\u001a\u00020ZX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010[\u001a\u0004\u0018\u00010\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010]\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010^\u0012\u0004\u0012\u00020*0:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u007f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/TurnBackActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomeContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomeContract$ViewEvent;", "currentMode", "currentPauseFeature", "functionClickTime", "", "goHomePresenter", "Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomePresenter;", "getGoHomePresenter", "()Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomePresenter;", "goHomePresenter$delegate", "isBirthdayMode", "", "isComeFromTransferDishes", "isFirstStart", "isNeedPwdPro", "isPersenterRun", "isRelease", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "needPlayerThanks", "onCancelTurnBackClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "onTouchSensorAnimationListener", "isShow", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "pauseVoiceTask", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "scheduleEventLister", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "triggerEvent", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/TurnBackActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/TurnBackActivity$singleBatteryListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "bindPresenter", "hideCountdownLayout", "initAiVoice", "isBusyState", "jumpAndFinish", "i", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPauseFeatureChange", "type", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "releaseDataAndFinish", "setCountdown", "time", "showCountdownLayout", "showGoHomeEvent", "showOnPauseStatus", "showOnTheWayStatus", "showTimeLeft", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "unbindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TurnBackActivity extends MyBaseActivity implements GoHomeContract.ViewInterface, AutoResumeCountDownContract.ViewInterface {
    public static final String COME_FROM_TRANSFER_DISHES = "COME_FROM_TRANSFER_DISHES";
    public static final String CUSTOM_OUTLET = "CUSTOM_OUTLET";
    public static final String KEY_MODE = "modeKey";
    public static final String SHOW_THANKS = "SHOW_THANKS";
    private final int TYPE_PAUSE_FEATURE_AIVOICE;
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private final int TYPE_PAUSE_FEATURE_TOUCH;
    private HashMap _$_findViewCache;

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter;
    private final BeeperCallHelper beeperCallHelper;
    private GoHomeContract.ViewEvent currentEventStatus;
    private int currentMode;
    private int currentPauseFeature;
    private long functionClickTime;
    private boolean isBirthdayMode;
    private boolean isComeFromTransferDishes;
    private boolean isNeedPwdPro;
    private boolean isPersenterRun;
    private boolean isRelease;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private VoiceTask locationLostVoiceTask;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private final MotionEventHelper motionEventHelper;
    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask;
    private boolean needPlayerThanks;
    private final OnLazyVoiceClickListener onCancelTurnBackClick;
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceAnimationViewClick;
    private Function0<Unit> onFallDropCallBack;
    private Function0<Unit> onLostLocationLostCallback;
    private final OnLazyClickListener onPauseLayoutClick;
    private final Function1<Boolean, Unit> onTouchSensorAnimationListener;
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private final VoiceTask pauseVoiceTask;
    private final Function1<ScheduleContract.TriggerEvent, Unit> scheduleEventLister;
    private final TurnBackActivity$singleBatteryListener$1 singleBatteryListener;
    private VoiceInteractionDialog voiceInteractionDialog;
    private final Function1<WakeupInfo, Unit> wakeupListener;
    private final String TAG = getClass().getSimpleName();
    private boolean isFirstStart = true;

    /* renamed from: goHomePresenter$delegate, reason: from kotlin metadata */
    private final Lazy goHomePresenter = LazyKt.lazy(new Function0<GoHomePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$goHomePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GoHomePresenter invoke() {
            GoHomePresenter goHomePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(GoHomePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(GoHomePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                goHomePresenter = new GoHomePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(GoHomePresenter.class).toString(), goHomePresenter);
            } else {
                if (!(basePresenterInterface instanceof GoHomePresenter)) {
                    basePresenterInterface = null;
                }
                goHomePresenter = (GoHomePresenter) basePresenterInterface;
            }
            if (goHomePresenter != null) {
                return goHomePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter");
        }
    });
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GoHomePresenter getGoHomePresenter() {
        return (GoHomePresenter) this.goHomePresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
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

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public boolean isBusyState() {
        return false;
    }

    public TurnBackActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$motionEventHelper$1$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PeripheralsSceneUtil.INSTANCE.showTurnBackOnTheWay();
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$autoResumeCountDownPresenter$2
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
                if (autoResumeCountDownPresenter != null) {
                    return autoResumeCountDownPresenter;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter");
            }
        });
        this.beeperCallHelper = new BeeperCallHelper();
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
        this.pauseVoiceTask = new VoiceTask(-1L, VoiceItem.voice7_2);
        this.locationLostVoiceTask = new VoiceTask(-1L, VoiceItem.voice17_1);
        this.currentMode = -1;
        this.currentEventStatus = GoHomeContract.ViewEvent.ON_THE_WAY;
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onErrorDialogShowStatus$1
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
                    PeripheralsSceneUtil.INSTANCE.showRunError();
                    if (z2) {
                        TurnBackTrack.INSTANCE.onEmergencyStop();
                    }
                }
            }
        };
        this.scheduleEventLister = new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$scheduleEventLister$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == ScheduleContract.TriggerEvent.AVOID) {
                    TurnBackTrack.INSTANCE.onStartScheduling();
                } else if (it == ScheduleContract.TriggerEvent.NORMAL) {
                    TurnBackTrack.INSTANCE.onStopScheduling();
                }
            }
        };
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                TurnBackTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onFallDropCallBack$1
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
                TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Abnormal);
                TurnBackActivity.this.releaseDataAndFinish();
            }
        };
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onLostLocationLostCallback$1
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
                String str2;
                VoiceTask voiceTask;
                int i;
                GoHomePresenter goHomePresenter;
                LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                    str2 = TurnBackActivity.this.TAG;
                    Pdlog.m3273d(str2, "onLostLocationLostCallback");
                    ((FaceVideoView) TurnBackActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    voiceTask = TurnBackActivity.this.locationLostVoiceTask;
                    voicePlayer.play(voiceTask);
                    TurnBackActivity turnBackActivity = TurnBackActivity.this;
                    i = turnBackActivity.TYPE_PAUSE_FEATURE_ERROR;
                    turnBackActivity.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    return;
                }
                if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                    TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                    goHomePresenter = TurnBackActivity.this.getGoHomePresenter();
                    goHomePresenter.actionCancelTask();
                    TurnBackActivity turnBackActivity2 = TurnBackActivity.this;
                    turnBackActivity2.jumpAndFinish(new Intent(turnBackActivity2, (Class<?>) LaserRunningLocationLostActivity.class));
                }
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onErrorClearCallback$1
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
                GoHomePresenter goHomePresenter;
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) TurnBackActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) TurnBackActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    goHomePresenter = TurnBackActivity.this.getGoHomePresenter();
                    goHomePresenter.actionPause();
                }
            }
        };
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$locationLostTouchCancelCallback$1
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
                ((FaceVideoView) TurnBackActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                TurnBackActivity turnBackActivity = TurnBackActivity.this;
                i = turnBackActivity.TYPE_PAUSE_FEATURE_ERROR;
                turnBackActivity.onPauseFeatureChange(i);
            }
        };
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onFaceAnimationViewClick$1
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
                GoHomeContract.ViewEvent viewEvent;
                GoHomeContract.ViewEvent viewEvent2;
                boolean z;
                GoHomePresenter goHomePresenter;
                String str2;
                GoHomeContract.ViewEvent viewEvent3;
                str = TurnBackActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                viewEvent = TurnBackActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = TurnBackActivity.this.currentEventStatus;
                if (viewEvent2 != GoHomeContract.ViewEvent.ON_THE_WAY) {
                    viewEvent3 = TurnBackActivity.this.currentEventStatus;
                    if (viewEvent3 != GoHomeContract.ViewEvent.ACTIVE) {
                        return;
                    }
                }
                z = TurnBackActivity.this.isPersenterRun;
                if (!z) {
                    str2 = TurnBackActivity.this.TAG;
                    Pdlog.m3274e(str2, "onFaceAnimationViewClick false , isPersenterRun = false");
                } else {
                    goHomePresenter = TurnBackActivity.this.getGoHomePresenter();
                    goHomePresenter.actionPause();
                }
            }
        }, 3, null);
        this.onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onPauseLayoutClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                GoHomeContract.ViewEvent viewEvent;
                boolean z;
                long j;
                GoHomePresenter goHomePresenter;
                String str2;
                String str3;
                str = TurnBackActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onPauseLayoutClick current status = ");
                viewEvent = TurnBackActivity.this.currentEventStatus;
                sb.append(viewEvent);
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
                    goHomePresenter = TurnBackActivity.this.getGoHomePresenter();
                    goHomePresenter.actionActive();
                }
            }
        };
        this.wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$wakeupListener$1
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
                GoHomeContract.ViewEvent viewEvent;
                GoHomeContract.ViewEvent viewEvent2;
                int i;
                VoiceInteractionDialog voiceInteractionDialog3;
                TurnBackTrack.INSTANCE.onWakeup();
                voiceInteractionDialog = TurnBackActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog == null) {
                    TurnBackActivity turnBackActivity = TurnBackActivity.this;
                    turnBackActivity.voiceInteractionDialog = new VoiceInteractionDialog(turnBackActivity);
                    voiceInteractionDialog3 = TurnBackActivity.this.voiceInteractionDialog;
                    if (voiceInteractionDialog3 != null) {
                        voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$wakeupListener$1.1
                            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                            public void onDismiss() {
                                String str2;
                                GoHomeContract.ViewEvent viewEvent3;
                                GoHomeContract.ViewEvent viewEvent4;
                                int i2;
                                str2 = TurnBackActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("wakeupListener onDismiss ");
                                viewEvent3 = TurnBackActivity.this.currentEventStatus;
                                sb.append(viewEvent3);
                                Pdlog.m3273d(str2, sb.toString());
                                viewEvent4 = TurnBackActivity.this.currentEventStatus;
                                if (viewEvent4 == GoHomeContract.ViewEvent.PAUSE) {
                                    TurnBackActivity turnBackActivity2 = TurnBackActivity.this;
                                    i2 = TurnBackActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                                    turnBackActivity2.onPauseFeatureChange(i2);
                                }
                            }
                        });
                    }
                }
                voiceInteractionDialog2 = TurnBackActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                    return;
                }
                voiceInteractionDialog2.show();
                str = TurnBackActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("wakeupListener show ");
                viewEvent = TurnBackActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = TurnBackActivity.this.currentEventStatus;
                if (viewEvent2 == GoHomeContract.ViewEvent.PAUSE) {
                    TurnBackActivity turnBackActivity2 = TurnBackActivity.this;
                    i = turnBackActivity2.TYPE_PAUSE_FEATURE_AIVOICE;
                    turnBackActivity2.onPauseFeatureChange(i);
                }
            }
        };
        this.onCancelTurnBackClick = new TurnBackActivity$onCancelTurnBackClick$1(this);
        this.onTouchSensorAnimationListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onTouchSensorAnimationListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                GoHomeContract.ViewEvent viewEvent;
                GoHomeContract.ViewEvent viewEvent2;
                int i;
                int i2;
                str = TurnBackActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onTouchSensorAnimationListener ");
                sb.append(z);
                sb.append(" , current status = ");
                viewEvent = TurnBackActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = TurnBackActivity.this.currentEventStatus;
                if (viewEvent2 == GoHomeContract.ViewEvent.PAUSE) {
                    if (z) {
                        TurnBackActivity turnBackActivity = TurnBackActivity.this;
                        i2 = turnBackActivity.TYPE_PAUSE_FEATURE_TOUCH;
                        turnBackActivity.onPauseFeatureChange(i2);
                    } else {
                        TurnBackActivity turnBackActivity2 = TurnBackActivity.this;
                        i = turnBackActivity2.TYPE_PAUSE_FEATURE_NORMAL;
                        turnBackActivity2.onPauseFeatureChange(i);
                    }
                }
            }
        };
        this.onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$onCustomCallListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CustomCallTargetBean customCallTargetBean) {
                invoke2(customCallTargetBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CustomCallTargetBean it) {
                String str;
                GoHomePresenter goHomePresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "onCustomCallListener task: " + it);
                goHomePresenter = TurnBackActivity.this.getGoHomePresenter();
                goHomePresenter.actionCancelTask();
                TurnBackActivity.this.jumpAndFinish(CustomCallActivity.Companion.createIntent(TurnBackActivity.this, it));
            }
        };
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_AIVOICE = 2;
        this.TYPE_PAUSE_FEATURE_TOUCH = 3;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
        this.singleBatteryListener = new TurnBackActivity$singleBatteryListener$1(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C4188R.layout.activity_turn_back);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TurnBackActivity$onCreate$1(null), 2, null);
        Intent intent = getIntent();
        this.currentMode = intent != null ? intent.getIntExtra(KEY_MODE, -1) : -1;
        this.isNeedPwdPro = this.currentMode == 0 && Constans.INSTANCE.getDeliverExitSwitch();
        Pdlog.m3273d(this.TAG, "currentMode:" + this.currentMode + "######isNeedPwdPro:" + this.isNeedPwdPro);
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(true);
        PeripheralsSceneUtil.INSTANCE.setPlayMode(PeripheralsSceneUtil.Mode.Back);
        PeripheralsSceneUtil.INSTANCE.stopAll();
        initAiVoice();
        this.isBirthdayMode = this.currentMode == 1;
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
        musicPlayerHelper.setOpenBirthdaySwitch(false);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout)).setOnClickListener(this.onPauseLayoutClick);
        ((LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task)).setOnClickListener(this.onCancelTurnBackClick);
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        this.needPlayerThanks = getIntent().getBooleanExtra("SHOW_THANKS", false);
        this.isComeFromTransferDishes = getIntent().getBooleanExtra("COME_FROM_TRANSFER_DISHES", false);
        showOnTheWayStatus();
        bindPresenter();
        RobotMapManager.INSTANCE.setCustomOutlet(getIntent().getStringExtra(CUSTOM_OUTLET));
        GoHomeContract.PresenterInterface.DefaultImpls.actionGoHome$default(getGoHomePresenter(), null, 1, null);
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            getGoHomePresenter().actionPause();
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            getGoHomePresenter().actionPauseNoTimer();
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            getGoHomePresenter().actionPauseNoTimer();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            this.touchSensorEventHelper.stopCurrentAnimation();
        } else if (type == this.TYPE_PAUSE_FEATURE_TOUCH) {
            getGoHomePresenter().actionPauseNoTimer();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            hideCountdownLayout();
        }
    }

    private final void showOnPauseStatus() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(0);
        this.currentEventStatus = GoHomeContract.ViewEvent.PAUSE;
        this.beeperCallHelper.stopReceiverCallTask();
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
        PeripheralsSceneUtil.INSTANCE.showTurnBackPause();
        MusicPlayerHelper.getInstance().pausePlay();
    }

    private final void showOnTheWayStatus() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        AiVoiceManager.INSTANCE.stopAiRecording();
        hideErrorTipDialog();
        this.touchSensorEventHelper.setCanHandle(true, true);
        if (this.needPlayerThanks) {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getThanks());
        }
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isReturnFace()));
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        String string = getString(C4188R.string.pdStr2_9);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_9)");
        faceVideoView.setTarget(string);
        this.motionEventHelper.setCanHandleMovingEvent(true);
        PeripheralsSceneUtil.INSTANCE.showTurnBackOnTheWay();
        MusicPlayerHelper.getInstance().startPlay(this.isBirthdayMode ? ModeEnum.BIRTHDAY_BACK : ModeEnum.DELIVERY_AND_RETURNING);
        if (this.needPlayerThanks) {
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice10_2));
            PeripheralsSceneUtil.INSTANCE.showDeliveryArrivedFinish();
        }
        if (this.isComeFromTransferDishes) {
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice34_2));
        }
        this.needPlayerThanks = false;
        this.isComeFromTransferDishes = false;
        this.beeperCallHelper.receiverCallTask(true);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        GoHomePresenter goHomePresenter = getGoHomePresenter();
        if (goHomePresenter != null) {
            goHomePresenter.actionPauseNoTimer();
        }
        GoHomePresenter goHomePresenter2 = getGoHomePresenter();
        if (goHomePresenter2 != null) {
            goHomePresenter2.actionCancelTask();
        }
        release();
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(i);
        finish();
    }

    private final void release() {
        this.isRelease = true;
        VoiceInteractionDialog voiceInteractionDialog = this.voiceInteractionDialog;
        if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
            voiceInteractionDialog.dismiss();
        }
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        MusicPlayerHelper.getInstance().release();
        unbindPresenter();
        RobotMapManager.INSTANCE.setCustomOutlet("");
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        TurnBackTrack turnBackTrack = TurnBackTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@TurnBackActivity::class.java.simpleName");
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        turnBackTrack.onCreateTask(simpleName, IntentExtKt.getSceneId$default(intent, null, 1, null));
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.singleBatteryListener.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        TurnBackActivity turnBackActivity = this;
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, turnBackActivity, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$bindPresenter$1
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
                GoHomePresenter goHomePresenter;
                goHomePresenter = TurnBackActivity.this.getGoHomePresenter();
                goHomePresenter.actionActive();
            }
        });
        TurnBackActivity turnBackActivity2 = this;
        getAutoResumeCountDownPresenter().replaceView(turnBackActivity2);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        this.touchSensorEventHelper.setOnAnimationShowListener(this.onTouchSensorAnimationListener);
        this.touchSensorEventHelper.setOnVoiceStateListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$bindPresenter$2
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
                mDangerousAreaTipHelper = TurnBackActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        getGoHomePresenter().replaceView(turnBackActivity2);
        this.motionEventHelper.setOnTheWayAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isReturnFace()));
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(voiceTaskWrapper, face_animation_view2, this.scheduleEventLister);
        this.motionEventHelper.setCurrentMovingText(SpUtils.get(this, "key_lattice_turn_back", ""));
        this.motionEventHelper.setOnVoicePlayListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$bindPresenter$3
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
                mDangerousAreaTipHelper = TurnBackActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        BeeperCallHelper.bind$default(this.beeperCallHelper, turnBackActivity, false, true, 2, null);
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new SimpleMusicPlayerCallbck() { // from class: com.pudutech.bumblebee.robot_ui.ui.TurnBackActivity$bindPresenter$4
            @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck, com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                String str;
                MotionEventHelper motionEventHelper2;
                super.onPrepared();
                str = TurnBackActivity.this.TAG;
                Pdlog.m3273d(str, "setMusicPlayerStateCallback onPrepared");
                motionEventHelper2 = TurnBackActivity.this.motionEventHelper;
                motionEventHelper2.stopMovingVoice();
            }
        });
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
        getMDangerousAreaTipHelper().bindLifecycle(this);
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        this.touchSensorEventHelper.unBindPresent();
        GoHomePresenter.INSTANCE.setMIsFillIn(false);
        TurnBackActivity turnBackActivity = this;
        getGoHomePresenter().removeView(turnBackActivity);
        this.motionEventHelper.unBind();
        this.beeperCallHelper.unbind();
        getAutoResumeCountDownPresenter().removeView(turnBackActivity);
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
    }

    @Override // com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract.ViewInterface
    public void showGoHomeEvent(GoHomeContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showGoHomeEvent：" + event + " ;currentEventStatus = " + this.currentEventStatus);
        this.isPersenterRun = true;
        switch (event) {
            case ON_THE_WAY:
                TurnBackTrack.INSTANCE.onMove();
                Pdlog.m3273d(this.TAG, "showGoHomeEvent：----->ON_THE_WAY");
                if (this.currentEventStatus != event) {
                    showOnTheWayStatus();
                    break;
                }
                break;
            case ACTIVE:
                TurnBackTrack.INSTANCE.onMove();
                Pdlog.m3273d(this.TAG, "showGoHomeEvent：----->ACTIVE");
                GoHomeContract.ViewEvent viewEvent = this.currentEventStatus;
                if (viewEvent != event && viewEvent != GoHomeContract.ViewEvent.ON_THE_WAY) {
                    showOnTheWayStatus();
                    break;
                }
                break;
            case PAUSE:
                TurnBackTrack.INSTANCE.onPause();
                Pdlog.m3273d(this.TAG, "showGoHomeEvent：----->PAUSE");
                if (this.currentEventStatus != event) {
                    VoicePlayer.INSTANCE.play(this.pauseVoiceTask);
                    showOnPauseStatus();
                    break;
                }
                break;
            case NO_DINING_OUTLET:
                TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.NoTarget);
                Pdlog.m3273d(this.TAG, "showGoHomeEvent：----->NO_DINING_OUTLET ");
                TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
                Intent intent = new Intent(this, (Class<?>) DeliverTaskEditActivity.class);
                intent.putExtra("SHOW_STATUS_KEY", 2).putExtra(CUSTOM_OUTLET, intent.getStringExtra(CUSTOM_OUTLET));
                jumpAndFinish(intent);
                break;
            case DONE:
                TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                Pdlog.m3273d(this.TAG, "showGoHomeEvent：----->DONE ");
                Intent putExtra = new Intent(this, (Class<?>) DeliverTaskEditActivity.class).putExtra(CUSTOM_OUTLET, getIntent().getStringExtra(CUSTOM_OUTLET));
                Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(this@TurnBackActi…ringExtra(CUSTOM_OUTLET))");
                jumpAndFinish(IntentExtKt.saveSceneId(putExtra, ""));
                break;
            case CANCEL:
                TurnBackTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                Pdlog.m3273d(this.TAG, "showGoHomeEvent：----->CANCEL ");
                Intent putExtra2 = new Intent(this, (Class<?>) DeliverTaskEditActivity.class).putExtra(CUSTOM_OUTLET, getIntent().getStringExtra(CUSTOM_OUTLET));
                Intrinsics.checkExpressionValueIsNotNull(putExtra2, "Intent(this@TurnBackActi…ET)\n                    )");
                jumpAndFinish(putExtra2);
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
            String string = getString(C4188R.string.pdStr13_5);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr13_5)");
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
            Pdlog.m3274e(this.TAG, "setCountdown###text: " + ((Object) spannableStringBuilder));
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "setCountdown", Log.getStackTraceString(e));
        }
    }

    private final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(4);
    }

    private final void showCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        if (countdown_tv.getVisibility() != 0) {
            TextView countdown_tv2 = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
            countdown_tv2.setVisibility(0);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
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

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
