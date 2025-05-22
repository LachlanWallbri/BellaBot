package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

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
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.greeter_task.GreeterContract;
import com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.GreeterTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.warkiz.widget.SizeUtils;
import java.util.ArrayList;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: GreeterActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002\u0018P\u0018\u0000 v2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001vB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010[\u001a\u00020#H\u0002J\b\u0010\\\u001a\u00020#H\u0002J\b\u0010]\u001a\u00020#H\u0002J\b\u0010^\u001a\u00020#H\u0002J\u0010\u0010_\u001a\u00020#2\u0006\u0010`\u001a\u00020aH\u0016J\u0012\u0010b\u001a\u00020#2\b\u0010c\u001a\u0004\u0018\u00010dH\u0014J\u0010\u0010e\u001a\u00020#2\u0006\u0010f\u001a\u00020\bH\u0002J\b\u0010g\u001a\u00020#H\u0002J\b\u0010h\u001a\u00020#H\u0002J\u0010\u0010i\u001a\u00020#2\u0006\u0010j\u001a\u00020\bH\u0002J\u0010\u0010k\u001a\u00020#2\u0006\u0010l\u001a\u00020\u0006H\u0002J\b\u0010m\u001a\u00020#H\u0002J\u0018\u0010n\u001a\u00020#2\u0006\u0010l\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u0014H\u0016J\b\u0010o\u001a\u00020#H\u0002J\b\u0010p\u001a\u00020#H\u0002J\u0018\u0010q\u001a\u00020#2\u0006\u0010j\u001a\u00020r2\u0006\u0010s\u001a\u00020tH\u0016J\b\u0010u\u001a\u00020#H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020(8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u0012\u001a\u0004\b)\u0010*R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u0012\u001a\u0004\b0\u00101R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00105\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R@\u00106\u001a4\u0012\u0013\u0012\u00110 ¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110 ¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020#\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010@\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010C\u001a2\u0012\u0013\u0012\u00110D¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110F¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(G\u0012\u0004\u0012\u00020#07X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010K\u001a\u001d\u0012\u0013\u0012\u00110M¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020#0LX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u00020PX\u0082\u0004¢\u0006\u0004\n\u0002\u0010QR\"\u0010R\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010Sj\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`TX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020VX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u0004\u0018\u00010XX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010Y\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010Z\u0012\u0004\u0012\u00020#0LX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006w"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewInterface;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewEvent;", "currentPauseFeature", "customLedScreenString", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity$functionButton$1;", "greeterPresenter", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterPresenter;", "getGreeterPresenter", "()Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterPresenter;", "greeterPresenter$delegate", "isFirstVoicePlay", "", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "mCurTarget", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "getMovingLoopVoiceTask", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "movingLoopVoiceTask$delegate", "onCancelGreeterBackClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onGoToWelcomeAreaClick", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "pauseVoiceTask", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "scheduleEventLister", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "triggerEvent", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity$singleBatteryListener$1;", TypedValues.Attributes.S_TARGET, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "bindPresenter", "hideCountdownLayout", "initAiVoice", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPauseFeatureChange", "type", "release", "releaseDataAndFinish", "setCountdown", "time", "showArrive", "des", "showCountdownLayout", "showMovingEvent", "showOnPause", "showOnTheWay", "showTimeLeft", "", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "unBindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterActivity extends MyBaseActivity implements AutoResumeCountDownContract.ViewInterface, GreeterContract.ViewInterface {
    private final int TYPE_PAUSE_FEATURE_AIVOICE;
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private final int TYPE_PAUSE_FEATURE_TOUCH;
    private HashMap _$_findViewCache;
    private GreeterContract.ViewEvent currentEventStatus;
    private int currentPauseFeature;
    private String customLedScreenString;
    private final GreeterActivity$functionButton$1 functionButton;
    private boolean isFirstVoicePlay;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private VoiceTask locationLostVoiceTask;
    private String mCurTarget;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private final MotionEventHelper motionEventHelper;

    /* renamed from: movingLoopVoiceTask$delegate, reason: from kotlin metadata */
    private final Lazy movingLoopVoiceTask;
    private final OnLazyVoiceClickListener onCancelGreeterBackClick;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceAnimationViewClick;
    private Function0<Unit> onFallDropCallBack;
    private final OnLazyVoiceClickListener onGoToWelcomeAreaClick;
    private Function0<Unit> onLostLocationLostCallback;
    private final OnLazyClickListener onPauseLayoutClick;
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private final VoiceTask pauseVoiceTask;
    private final RunningErrorHelper runningErrorHelper;
    private final Function1<ScheduleContract.TriggerEvent, Unit> scheduleEventLister;
    private final GreeterActivity$singleBatteryListener$1 singleBatteryListener;
    private ArrayList<String> target;
    private final TouchSensorEventHelper touchSensorEventHelper;
    private VoiceInteractionDialog voiceInteractionDialog;
    private final Function1<WakeupInfo, Unit> wakeupListener;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TABLE_NAME = TABLE_NAME;
    private static final String TABLE_NAME = TABLE_NAME;
    private static final String KEY_GREETE_MODE = KEY_GREETE_MODE;
    private static final String KEY_GREETE_MODE = KEY_GREETE_MODE;
    private final String TAG = "GreeterActivity";

    /* renamed from: greeterPresenter$delegate, reason: from kotlin metadata */
    private final Lazy greeterPresenter = LazyKt.lazy(new Function0<GreeterPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$greeterPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GreeterPresenter invoke() {
            GreeterPresenter greeterPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(GreeterPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(GreeterPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                greeterPresenter = new GreeterPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(GreeterPresenter.class).toString(), greeterPresenter);
            } else {
                if (!(basePresenterInterface instanceof GreeterPresenter)) {
                    basePresenterInterface = null;
                }
                greeterPresenter = (GreeterPresenter) basePresenterInterface;
            }
            if (greeterPresenter == null) {
                Intrinsics.throwNpe();
            }
            return greeterPresenter;
        }
    });

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$autoResumeCountDownPresenter$2
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
    public final GreeterPresenter getGreeterPresenter() {
        return (GreeterPresenter) this.greeterPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
    }

    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper getMovingLoopVoiceTask() {
        return (TtsVoiceWrapperPlayer.VoiceTaskWrapper) this.movingLoopVoiceTask.getValue();
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

    /* JADX WARN: Type inference failed for: r2v1, types: [com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$functionButton$1] */
    public GreeterActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$motionEventHelper$1$1
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
        this.customLedScreenString = "";
        this.functionButton = new FunctionButtonListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$functionButton$1
            @Override // com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener
            public void onClick() {
            }
        };
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.movingLoopVoiceTask = LazyKt.lazy(new Function0<TtsVoiceWrapperPlayer.VoiceTaskWrapper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$movingLoopVoiceTask$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TtsVoiceWrapperPlayer.VoiceTaskWrapper invoke() {
                return new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(1L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(Long.valueOf(TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE) * 1000), VoiceItem.voice32_2)}), TtsVoiceWrapperPlayer.PlayType.GREETER_GUIDE, null, 4, null);
            }
        });
        this.pauseVoiceTask = new VoiceTask(-1L, VoiceItem.voice7_2);
        this.currentEventStatus = GreeterContract.ViewEvent.ON_THE_WAY;
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onErrorDialogShowStatus$1
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
                    GreeterActivity greeterActivity = GreeterActivity.this;
                    i = greeterActivity.TYPE_PAUSE_FEATURE_ERROR;
                    greeterActivity.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.showRunError();
                    if (z2) {
                        GreeterTrack.INSTANCE.onEmergencyStop();
                    }
                }
            }
        };
        this.scheduleEventLister = new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$scheduleEventLister$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == ScheduleContract.TriggerEvent.AVOID) {
                    GreeterTrack.INSTANCE.onStartScheduling();
                } else if (it == ScheduleContract.TriggerEvent.NORMAL) {
                    GreeterTrack.INSTANCE.onStopScheduling();
                }
            }
        };
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                GreeterTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onFallDropCallBack$1
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
                GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Abnormal);
                GreeterActivity.this.releaseDataAndFinish();
            }
        };
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onFaceAnimationViewClick$1
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
                GreeterContract.ViewEvent viewEvent;
                GreeterContract.ViewEvent viewEvent2;
                GreeterPresenter greeterPresenter;
                GreeterContract.ViewEvent viewEvent3;
                str = GreeterActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                viewEvent = GreeterActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = GreeterActivity.this.currentEventStatus;
                if (viewEvent2 != GreeterContract.ViewEvent.ON_THE_WAY) {
                    viewEvent3 = GreeterActivity.this.currentEventStatus;
                    if (viewEvent3 != GreeterContract.ViewEvent.ACTIVE) {
                        return;
                    }
                }
                greeterPresenter = GreeterActivity.this.getGreeterPresenter();
                greeterPresenter.actionPause();
            }
        }, 3, null);
        this.wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$wakeupListener$1
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
                GreeterContract.ViewEvent viewEvent;
                GreeterContract.ViewEvent viewEvent2;
                int i;
                VoiceInteractionDialog voiceInteractionDialog3;
                GreeterTrack.INSTANCE.onWakeup();
                voiceInteractionDialog = GreeterActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog == null) {
                    GreeterActivity greeterActivity = GreeterActivity.this;
                    greeterActivity.voiceInteractionDialog = new VoiceInteractionDialog(greeterActivity);
                    voiceInteractionDialog3 = GreeterActivity.this.voiceInteractionDialog;
                    if (voiceInteractionDialog3 != null) {
                        voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$wakeupListener$1.1
                            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                            public void onDismiss() {
                                String str2;
                                GreeterContract.ViewEvent viewEvent3;
                                GreeterContract.ViewEvent viewEvent4;
                                int i2;
                                str2 = GreeterActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("wakeupListener onDismiss ");
                                viewEvent3 = GreeterActivity.this.currentEventStatus;
                                sb.append(viewEvent3);
                                Pdlog.m3273d(str2, sb.toString());
                                viewEvent4 = GreeterActivity.this.currentEventStatus;
                                if (viewEvent4 == GreeterContract.ViewEvent.PAUSE) {
                                    GreeterActivity greeterActivity2 = GreeterActivity.this;
                                    i2 = GreeterActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                                    greeterActivity2.onPauseFeatureChange(i2);
                                }
                            }
                        });
                    }
                }
                voiceInteractionDialog2 = GreeterActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                    return;
                }
                voiceInteractionDialog2.show();
                str = GreeterActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("wakeupListener show ");
                viewEvent = GreeterActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = GreeterActivity.this.currentEventStatus;
                if (viewEvent2 == GreeterContract.ViewEvent.PAUSE) {
                    GreeterActivity greeterActivity2 = GreeterActivity.this;
                    i = greeterActivity2.TYPE_PAUSE_FEATURE_AIVOICE;
                    greeterActivity2.onPauseFeatureChange(i);
                }
            }
        };
        this.onCancelGreeterBackClick = new GreeterActivity$onCancelGreeterBackClick$1(this);
        this.onGoToWelcomeAreaClick = new GreeterActivity$onGoToWelcomeAreaClick$1(this);
        this.onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onPauseLayoutClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                GreeterContract.ViewEvent viewEvent;
                GreeterContract.ViewEvent viewEvent2;
                GreeterPresenter greeterPresenter;
                str = GreeterActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onPauseLayoutClick current status = ");
                viewEvent = GreeterActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = GreeterActivity.this.currentEventStatus;
                if (viewEvent2 == GreeterContract.ViewEvent.PAUSE) {
                    greeterPresenter = GreeterActivity.this.getGreeterPresenter();
                    greeterPresenter.actionActive();
                } else {
                    GreeterActivity.this.showOnTheWay();
                }
            }
        };
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$locationLostTouchCancelCallback$1
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
                str = GreeterActivity.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                ((FaceVideoView) GreeterActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                GreeterActivity greeterActivity = GreeterActivity.this;
                i = greeterActivity.TYPE_PAUSE_FEATURE_ERROR;
                greeterActivity.onPauseFeatureChange(i);
            }
        };
        this.mCurTarget = "";
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onLostLocationLostCallback$1
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
                GreeterPresenter greeterPresenter;
                str = GreeterActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback");
                if (CheckLocationHelper.INSTANCE.getLocateCase() == LocateCase.Marker || CheckLocationHelper.INSTANCE.getLocateCase() == LocateCase.LaserMark) {
                    ((FaceVideoView) GreeterActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    voiceTask = GreeterActivity.this.locationLostVoiceTask;
                    voicePlayer.play(voiceTask);
                    GreeterActivity greeterActivity = GreeterActivity.this;
                    i = greeterActivity.TYPE_PAUSE_FEATURE_ERROR;
                    greeterActivity.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    return;
                }
                GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                greeterPresenter = GreeterActivity.this.getGreeterPresenter();
                greeterPresenter.actionCancelTask();
                GreeterActivity greeterActivity2 = GreeterActivity.this;
                greeterActivity2.jumpAndFinish(new Intent(greeterActivity2, (Class<?>) LaserRunningLocationLostActivity.class));
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$onErrorClearCallback$1
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
                GreeterPresenter greeterPresenter;
                str = GreeterActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) GreeterActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) GreeterActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    greeterPresenter = GreeterActivity.this.getGreeterPresenter();
                    greeterPresenter.actionPause();
                }
            }
        };
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_AIVOICE = 2;
        this.TYPE_PAUSE_FEATURE_TOUCH = 3;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
        this.singleBatteryListener = new GreeterActivity$singleBatteryListener$1(this);
    }

    /* compiled from: GreeterActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterActivity$Companion;", "", "()V", "KEY_GREETE_MODE", "", "getKEY_GREETE_MODE", "()Ljava/lang/String;", GreeterActivity.TABLE_NAME, "getTABLE_NAME", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTABLE_NAME() {
            return GreeterActivity.TABLE_NAME;
        }

        public final String getKEY_GREETE_MODE() {
            return GreeterActivity.KEY_GREETE_MODE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_greeter);
        this.target = getIntent().getStringArrayListExtra(TABLE_NAME);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new GreeterActivity$onCreate$1(null), 2, null);
        initView();
        bindPresenter();
        PeripheralsSceneUtil.INSTANCE.stopAll();
        ArrayList<String> arrayList = this.target;
        if (arrayList == null || (arrayList != null && arrayList != null && arrayList.size() == 0)) {
            Pdlog.m3274e(this.TAG, "onCreate target is null ??? ");
            jumpAndFinish(new Intent(this, (Class<?>) GreeterMenuActivity.class));
        } else {
            GreeterPresenter greeterPresenter = getGreeterPresenter();
            ArrayList<String> arrayList2 = this.target;
            if (arrayList2 == null) {
                Intrinsics.throwNpe();
            }
            greeterPresenter.actionUsher(arrayList2);
        }
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
            getGreeterPresenter().actionPause();
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            getGreeterPresenter().actionPauseNoTimer();
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            getGreeterPresenter().actionPauseNoTimer();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            this.touchSensorEventHelper.stopCurrentAnimation();
        } else if (type == this.TYPE_PAUSE_FEATURE_TOUCH) {
            getGreeterPresenter().actionPauseNoTimer();
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
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        ((ConstraintLayout) _$_findCachedViewById(C4188R.id.base_layout)).setOnClickListener(this.onPauseLayoutClick);
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        ((LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task)).setOnClickListener(this.onCancelGreeterBackClick);
        ((LinearLayout) _$_findCachedViewById(C4188R.id.go_to_welcome_area)).setOnClickListener(this.onGoToWelcomeAreaClick);
        _$_findCachedViewById(C4188R.id.greeter_arrive_layout).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.greeter_arrive_finish_btn)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$initView$2
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
                String str;
                GreeterPresenter greeterPresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                GreeterTrack greeterTrack = GreeterTrack.INSTANCE;
                str = GreeterActivity.this.mCurTarget;
                greeterTrack.onFinishOne(str, BaseMoveTrackTask.FinishOneCause.Click);
                greeterPresenter = GreeterActivity.this.getGreeterPresenter();
                greeterPresenter.actionUsherDone();
            }
        }, 3, null));
    }

    private final void bindPresenter() {
        GreeterTrack greeterTrack = GreeterTrack.INSTANCE;
        ArrayList<String> arrayList = this.target;
        int size = arrayList != null ? arrayList.size() : 0;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@GreeterActivity::class.java.simpleName");
        greeterTrack.onCreateTask(size, simpleName);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        this.touchSensorEventHelper.setOnVoiceStateListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$bindPresenter$1
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
                MotionEventHelper motionEventHelper;
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == PlayEvent.COMPLETION_ONCE || it == PlayEvent.STOP) {
                    motionEventHelper = GreeterActivity.this.motionEventHelper;
                    motionEventHelper.playMovingVoice();
                }
                mDangerousAreaTipHelper = GreeterActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        GreeterActivity greeterActivity = this;
        getGreeterPresenter().replaceView(greeterActivity);
        getAutoResumeCountDownPresenter().replaceView(greeterActivity);
        this.motionEventHelper.setOnTheWayAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isLeadingFace()));
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask = getMovingLoopVoiceTask();
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(movingLoopVoiceTask, face_animation_view2, this.scheduleEventLister);
        this.motionEventHelper.setOnVoicePlayListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$bindPresenter$2
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
                mDangerousAreaTipHelper = GreeterActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.customLedScreenString = SpUtils.get(this, "key_lattice_guide_table", "");
        this.motionEventHelper.setCurrentMovingText(this.customLedScreenString.length() == 0 ? getGreeterPresenter().getDestination() : this.customLedScreenString);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, this, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$bindPresenter$3
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
                GreeterPresenter greeterPresenter;
                greeterPresenter = GreeterActivity.this.getGreeterPresenter();
                greeterPresenter.actionActive();
            }
        });
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.singleBatteryListener.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        getMDangerousAreaTipHelper().bindLifecycle(this);
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        GreeterPresenter greeterPresenter = getGreeterPresenter();
        if (greeterPresenter != null) {
            greeterPresenter.actionPauseNoTimer();
        }
        GreeterPresenter greeterPresenter2 = getGreeterPresenter();
        if (greeterPresenter2 != null) {
            greeterPresenter2.actionCancelTask();
        }
        release();
        finish();
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

    private final void release() {
        VoiceInteractionDialog voiceInteractionDialog = this.voiceInteractionDialog;
        if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
            voiceInteractionDialog.dismiss();
        }
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        unBindPresenter();
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        this.touchSensorEventHelper.unBindPresent();
        GreeterActivity greeterActivity = this;
        getGreeterPresenter().removeView(greeterActivity);
        this.motionEventHelper.unBind();
        getAutoResumeCountDownPresenter().removeView(greeterActivity);
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWay() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        AiVoiceManager.INSTANCE.stopAiRecording();
        this.touchSensorEventHelper.setCanHandle(true, true);
        RelativeLayout pause_info_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_info_layout, "pause_info_layout");
        pause_info_layout.setVisibility(8);
        LinearLayout cancel_back_task = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task);
        Intrinsics.checkExpressionValueIsNotNull(cancel_back_task, "cancel_back_task");
        cancel_back_task.setVisibility(8);
        LinearLayout go_to_welcome_area = (LinearLayout) _$_findCachedViewById(C4188R.id.go_to_welcome_area);
        Intrinsics.checkExpressionValueIsNotNull(go_to_welcome_area, "go_to_welcome_area");
        go_to_welcome_area.setVisibility(8);
        View greeter_arrive_layout = _$_findCachedViewById(C4188R.id.greeter_arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(greeter_arrive_layout, "greeter_arrive_layout");
        greeter_arrive_layout.setVisibility(8);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isLeadingFace()));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(this.mCurTarget);
        PeripheralsSceneUtil.INSTANCE.showGreeterOnTheWay();
        if (!this.isFirstVoicePlay) {
            VoiceTask voiceTask = new VoiceTask(-1L, VoiceItem.voice32_2);
            voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterActivity$showOnTheWay$1
                @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                public void onStateChange(PlayEvent event) {
                    MotionEventHelper motionEventHelper;
                    Intrinsics.checkParameterIsNotNull(event, "event");
                    if (event == PlayEvent.COMPLETION_ONCE) {
                        motionEventHelper = GreeterActivity.this.motionEventHelper;
                        motionEventHelper.setCanHandleMovingEvent(true);
                    }
                }
            });
            VoicePlayer.INSTANCE.play(voiceTask);
            this.isFirstVoicePlay = true;
        } else {
            this.motionEventHelper.setCanHandleMovingEvent(true);
        }
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.GUIDE);
    }

    private final void showOnPause() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        showCountdownLayout();
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        View greeter_arrive_layout = _$_findCachedViewById(C4188R.id.greeter_arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(greeter_arrive_layout, "greeter_arrive_layout");
        greeter_arrive_layout.setVisibility(8);
        RelativeLayout pause_info_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_info_layout, "pause_info_layout");
        pause_info_layout.setVisibility(0);
        LinearLayout cancel_back_task = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task);
        Intrinsics.checkExpressionValueIsNotNull(cancel_back_task, "cancel_back_task");
        cancel_back_task.setVisibility(0);
        LinearLayout go_to_welcome_area = (LinearLayout) _$_findCachedViewById(C4188R.id.go_to_welcome_area);
        Intrinsics.checkExpressionValueIsNotNull(go_to_welcome_area, "go_to_welcome_area");
        go_to_welcome_area.setVisibility(0);
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

    private final void showArrive(String des) {
        Pdlog.m3273d(this.TAG, "showArrive ");
        this.motionEventHelper.setCanHandleMovingEvent(false);
        View greeter_arrive_layout = _$_findCachedViewById(C4188R.id.greeter_arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(greeter_arrive_layout, "greeter_arrive_layout");
        greeter_arrive_layout.setVisibility(0);
        RelativeLayout pause_info_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_info_layout, "pause_info_layout");
        pause_info_layout.setVisibility(8);
        LinearLayout cancel_back_task = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task);
        Intrinsics.checkExpressionValueIsNotNull(cancel_back_task, "cancel_back_task");
        cancel_back_task.setVisibility(8);
        LinearLayout go_to_welcome_area = (LinearLayout) _$_findCachedViewById(C4188R.id.go_to_welcome_area);
        Intrinsics.checkExpressionValueIsNotNull(go_to_welcome_area, "go_to_welcome_area");
        go_to_welcome_area.setVisibility(8);
        TextView greeter_arrive_info_tv = (TextView) _$_findCachedViewById(C4188R.id.greeter_arrive_info_tv);
        Intrinsics.checkExpressionValueIsNotNull(greeter_arrive_info_tv, "greeter_arrive_info_tv");
        greeter_arrive_info_tv.setText(des);
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.greeter_arrive_info_tv), 1000, des);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        MusicPlayerHelper.getInstance().pausePlay();
        PeripheralsSceneUtil.INSTANCE.showGreeterArrive();
        PeripheralsSceneUtil.INSTANCE.showLedString(des);
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.ViewInterface
    public void showMovingEvent(String des, GreeterContract.ViewEvent event) {
        String str;
        Intrinsics.checkParameterIsNotNull(des, "des");
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.mCurTarget = des;
        Pdlog.m3273d(this.TAG, "showMovingEvent des: " + des + " ; event：" + event + " ;currentEventStatus = " + this.currentEventStatus);
        String str2 = "";
        switch (event) {
            case ON_THE_WAY:
                GreeterTrack.INSTANCE.onMove();
                if (this.currentEventStatus != event) {
                    showOnTheWay();
                } else if (!Constans.INSTANCE.isLeadingFace()) {
                    ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(this.mCurTarget);
                    Pdlog.m3273d(this.TAG, "showMovingEvent", "首次进入：" + this.mCurTarget);
                }
                this.motionEventHelper.setCurrentMovingText(this.customLedScreenString.length() == 0 ? des : this.customLedScreenString);
                if (!StringsKt.isBlank(des)) {
                    str = "正在前往 " + des;
                    str2 = str;
                    break;
                }
                break;
            case ACTIVE:
                GreeterTrack.INSTANCE.onMove();
                GreeterContract.ViewEvent viewEvent = this.currentEventStatus;
                if (viewEvent != event && viewEvent != GreeterContract.ViewEvent.ON_THE_WAY) {
                    showOnTheWay();
                }
                GreeterContract.ViewEvent viewEvent2 = this.currentEventStatus;
                if (!StringsKt.isBlank(des)) {
                    str = "正在前往 " + des;
                    str2 = str;
                    break;
                }
                break;
            case PAUSE:
                GreeterTrack.INSTANCE.onPause();
                if (this.currentEventStatus != event) {
                    VoicePlayer.INSTANCE.play(this.pauseVoiceTask);
                    showOnPause();
                    break;
                }
                break;
            case ARRIVAL_DESTINATION:
                GreeterTrack.INSTANCE.onArrive(des);
                showArrive(des);
                TtsVoiceWrapperPlayer.play$default(TtsVoiceWrapperPlayer.INSTANCE, new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(-1L, VoiceItem.voice32_3), TtsVoiceWrapperPlayer.PlayType.GUIDE_ARRIVAL, null, 4, null), null, 2, null);
                break;
            case CANCEL:
                GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                jumpAndFinish(new Intent(this, (Class<?>) GreeterMenuActivity.class));
                break;
            case NOT_FIND_TARGET:
                GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.NoTarget);
                ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C4188R.string.pdStr25_16), new Object[0]);
                jumpAndFinish(new Intent(this, (Class<?>) GreeterMenuActivity.class));
                break;
            case ARRIVAL_DESTINATION_COUNTDOWN_DONE:
                GreeterTrack.INSTANCE.onFinishOne(des, BaseMoveTrackTask.FinishOneCause.CountDown);
                break;
            case DONE_ALL:
                GreeterTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                Intent putExtra = new Intent(this, (Class<?>) GotoWelcomeAreaActivity.class).putExtra(KEY_GREETE_MODE, true);
                Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(this@GreeterActiv…ra(KEY_GREETE_MODE, true)");
                jumpAndFinish(IntentExtKt.saveSceneId(putExtra, GreeterTrack.INSTANCE.getSessionId()));
                break;
        }
        TextView text_main_des = (TextView) _$_findCachedViewById(C4188R.id.text_main_des);
        Intrinsics.checkExpressionValueIsNotNull(text_main_des, "text_main_des");
        text_main_des.setText(str2);
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
            String string = getString(C4188R.string.pdStr25_15);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr25_15)");
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
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        if (countdown_tv.getVisibility() != 0) {
            TextView countdown_tv2 = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
            countdown_tv2.setVisibility(0);
        }
    }
}
