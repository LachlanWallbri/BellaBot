package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract;
import com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.TurnBackActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.recycle.ToRecyclingPointActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.GoDishWashTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: ToRecyclingPointActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000á\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001G\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001vB\u0005¢\u0006\u0002\u0010\u0005J\b\u0010K\u001a\u00020\u001eH\u0002J&\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020 2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010O2\b\b\u0002\u0010P\u001a\u00020\u0018H\u0002J\b\u0010Q\u001a\u00020\u001eH\u0016J\b\u0010R\u001a\u00020SH\u0002J\b\u0010T\u001a\u00020\u001eH\u0002J\u0012\u0010U\u001a\u00020\u001e2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\u0010\u0010X\u001a\u00020\u001e2\u0006\u0010Y\u001a\u00020ZH\u0016J\b\u0010[\u001a\u00020\tH\u0016J\u0010\u0010\\\u001a\u00020\u001e2\u0006\u0010]\u001a\u00020\tH\u0002J\b\u0010^\u001a\u00020\u001eH\u0002J\b\u0010_\u001a\u00020\u001eH\u0002J\b\u0010`\u001a\u00020\u0018H\u0002J\b\u0010a\u001a\u00020\u0018H\u0002J\b\u0010b\u001a\u00020\u001eH\u0002J\b\u0010c\u001a\u00020\u001eH\u0002J\b\u0010d\u001a\u00020\u001eH\u0002J0\u0010e\u001a\u00020\u001e\"\u0004\b\u0000\u0010f2\f\u0010g\u001a\b\u0012\u0004\u0012\u0002Hf0\u001d2\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u0002Hf\u0012\u0004\u0012\u00020\u001e0?H\u0002J\u0010\u0010i\u001a\u00020\u001e2\u0006\u0010j\u001a\u00020\tH\u0002J\b\u0010k\u001a\u00020\u001eH\u0002J\b\u0010l\u001a\u00020\u001eH\u0002J\b\u0010m\u001a\u00020\u001eH\u0002J\b\u0010n\u001a\u00020\u001eH\u0002J\u0018\u0010o\u001a\u00020\u001e2\u0006\u0010j\u001a\u00020p2\u0006\u0010q\u001a\u00020rH\u0016J\u0018\u0010s\u001a\u00020\u001e2\u0006\u00102\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0016H\u0016J\b\u0010t\u001a\u00020\u001eH\u0002J\b\u0010u\u001a\u00020\u001eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u0013\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u0013\u001a\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010/\u001a4\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u001e\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R>\u00109\u001a2\u0012\u0013\u0012\u00110:¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110<¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\u001e00X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010>\u001a\u001d\u0012\u0013\u0012\u00110@¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\u001e0?X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0004\n\u0002\u0010HR\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006w"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/ToRecyclingPointActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/ToRecyclingPointVM;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointContract$ViewInterface;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_DIALOG", "", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "arriveVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "currentEventName", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointContract$TransferEvent;", "isComeFromRecyclePlate", "", "isRelease", "lastEventStatus", "leaveVoiceTask", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "mToRecyclingPointPresenter", "Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointPresenter;", "getMToRecyclingPointPresenter", "()Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointPresenter;", "mToRecyclingPointPresenter$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "onVoicePlayEvent", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "pauseVoiceTask", "recycleVoice", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/IRecycleVoice;", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/recycle/ToRecyclingPointActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/ToRecyclingPointActivity$singleBatteryListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "bindPresenter", "bindTtsPlayEvent", "voiceTask", "playType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "isOpen", "createObserver", "getOnTheWayAnim", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jumpAndFinish", "i", "Landroid/content/Intent;", "layoutId", "onPauseFeatureChange", "type", "playArriveVoiceOrTts", "playLeaveVoiceOrTts", "recycleArrivedIsOpen", "recycleLeaveIsOpen", "release", "releaseDataAndFinish", "resetCountdownLayout", "runTask", ExifInterface.GPS_DIRECTION_TRUE, "ioTask", "mainTask", "setCountdown", "time", "showOnArriveStatus", "showOnArrivingStatus", "showOnPauseStatus", "showOnTheWayStatus", "showTimeLeft", "", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "showTransferEvent", "startRecycleTaskActivity", "unBindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ToRecyclingPointActivity extends MyBaseVmActivity<ToRecyclingPointVM> implements AutoResumeCountDownContract.ViewInterface, ToRecyclingPointContract.ViewInterface {
    public static final String COME_FROM_RECYCLE_PLATE = "COME_FROM_RECYCLE_PLATE";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private HashMap _$_findViewCache;
    private TtsVoiceWrapperPlayer.VoiceTaskWrapper arriveVoiceTask;

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter;
    private String currentEventName;
    private ToRecyclingPointContract.TransferEvent currentEventStatus;
    private boolean isComeFromRecyclePlate;
    private boolean isRelease;
    private ToRecyclingPointContract.TransferEvent lastEventStatus;
    private TtsVoiceWrapperPlayer.VoiceTaskWrapper leaveVoiceTask;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private VoiceTask locationLostVoiceTask;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private final MotionEventHelper motionEventHelper;
    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceAnimationViewClick;
    private Function0<Unit> onFallDropCallBack;
    private Function0<Unit> onLostLocationLostCallback;
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private final Function1<PlayEvent, Unit> onVoicePlayEvent;
    private final VoiceTask pauseVoiceTask;
    private final ToRecyclingPointActivity$singleBatteryListener$1 singleBatteryListener;
    private final TouchSensorEventHelper touchSensorEventHelper;
    private final String TAG = "ToRecyclingPointActivity";
    private final IRecycleVoice recycleVoice = new RecycleVoice();

    /* renamed from: mToRecyclingPointPresenter$delegate, reason: from kotlin metadata */
    private final Lazy mToRecyclingPointPresenter = LazyKt.lazy(new Function0<ToRecyclingPointPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$mToRecyclingPointPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ToRecyclingPointPresenter invoke() {
            ToRecyclingPointPresenter toRecyclingPointPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(ToRecyclingPointPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(ToRecyclingPointPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                toRecyclingPointPresenter = new ToRecyclingPointPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(ToRecyclingPointPresenter.class).toString(), toRecyclingPointPresenter);
            } else {
                if (!(basePresenterInterface instanceof ToRecyclingPointPresenter)) {
                    basePresenterInterface = null;
                }
                toRecyclingPointPresenter = (ToRecyclingPointPresenter) basePresenterInterface;
            }
            if (toRecyclingPointPresenter != null) {
                return toRecyclingPointPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter");
        }
    });
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PlayEvent.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[PlayEvent.COMPLETION_ONCE.ordinal()] = 1;
            $EnumSwitchMapping$0[PlayEvent.STOP.ordinal()] = 2;
            $EnumSwitchMapping$0[PlayEvent.PLAYING.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[ToRecyclingPointContract.TransferEvent.values().length];
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.NO_DESTINATION.ordinal()] = 1;
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.APPROACHING.ordinal()] = 3;
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.ACTIVE.ordinal()] = 4;
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.PAUSE.ordinal()] = 5;
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.ARRIVAL.ordinal()] = 6;
            $EnumSwitchMapping$1[ToRecyclingPointContract.TransferEvent.DONE.ordinal()] = 7;
            $EnumSwitchMapping$2 = new int[PlayEvent.values().length];
            $EnumSwitchMapping$2[PlayEvent.COMPLETION_ONCE.ordinal()] = 1;
            $EnumSwitchMapping$2[PlayEvent.STOP.ordinal()] = 2;
            $EnumSwitchMapping$2[PlayEvent.PLAYING.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[AudioTrackUtils.AudioPlayEvent.values().length];
            $EnumSwitchMapping$3[AudioTrackUtils.AudioPlayEvent.COMPLETE.ordinal()] = 1;
            $EnumSwitchMapping$3[AudioTrackUtils.AudioPlayEvent.STOP.ordinal()] = 2;
            $EnumSwitchMapping$3[AudioTrackUtils.AudioPlayEvent.PLAYING.ordinal()] = 3;
        }
    }

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ToRecyclingPointPresenter getMToRecyclingPointPresenter() {
        return (ToRecyclingPointPresenter) this.mToRecyclingPointPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
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

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
    }

    public ToRecyclingPointActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$motionEventHelper$1$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$autoResumeCountDownPresenter$2
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
        this.locationLostVoiceTask = new VoiceTask(-1L, VoiceItem.voice17_1);
        this.pauseVoiceTask = new VoiceTask(-1L, VoiceItem.voice7_2);
        this.currentEventStatus = ToRecyclingPointContract.TransferEvent.ACTIVE;
        this.lastEventStatus = this.currentEventStatus;
        this.currentEventName = "";
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.touchSensorEventHelper = new TouchSensorEventHelper();
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                GoDishWashTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onErrorDialogShowStatus$1
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
                IRecycleVoice iRecycleVoice;
                int i;
                IRecycleVoice iRecycleVoice2;
                if (!z) {
                    iRecycleVoice = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                    return;
                }
                ToRecyclingPointActivity toRecyclingPointActivity = ToRecyclingPointActivity.this;
                i = toRecyclingPointActivity.TYPE_PAUSE_FEATURE_ERROR;
                toRecyclingPointActivity.onPauseFeatureChange(i);
                PeripheralsSceneUtil.INSTANCE.showRunError();
                iRecycleVoice2 = ToRecyclingPointActivity.this.recycleVoice;
                iRecycleVoice2.onPlayVoice();
                if (z2) {
                    GoDishWashTrack.INSTANCE.onEmergencyStop();
                }
            }
        };
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onFallDropCallBack$1
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
                GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Abnormal);
                ToRecyclingPointActivity.this.releaseDataAndFinish();
            }
        };
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onLostLocationLostCallback$1
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
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                    str2 = ToRecyclingPointActivity.this.TAG;
                    Pdlog.m3273d(str2, "onLostLocationLostCallback");
                    ((FaceVideoView) ToRecyclingPointActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    ToRecyclingPointActivity toRecyclingPointActivity = ToRecyclingPointActivity.this;
                    voiceTask = toRecyclingPointActivity.locationLostVoiceTask;
                    ToRecyclingPointActivity.bindTtsPlayEvent$default(toRecyclingPointActivity, voiceTask, null, false, 6, null);
                    ToRecyclingPointActivity toRecyclingPointActivity2 = ToRecyclingPointActivity.this;
                    i = toRecyclingPointActivity2.TYPE_PAUSE_FEATURE_ERROR;
                    toRecyclingPointActivity2.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    return;
                }
                if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                    GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                    mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                    mToRecyclingPointPresenter.actionCancelTask();
                    ToRecyclingPointActivity toRecyclingPointActivity3 = ToRecyclingPointActivity.this;
                    toRecyclingPointActivity3.jumpAndFinish(new Intent(toRecyclingPointActivity3, (Class<?>) LaserRunningLocationLostActivity.class));
                }
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onErrorClearCallback$1
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
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) ToRecyclingPointActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) ToRecyclingPointActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                    mToRecyclingPointPresenter.actionPause();
                }
            }
        };
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$locationLostTouchCancelCallback$1
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
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                ((FaceVideoView) ToRecyclingPointActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                ToRecyclingPointActivity toRecyclingPointActivity = ToRecyclingPointActivity.this;
                i = toRecyclingPointActivity.TYPE_PAUSE_FEATURE_ERROR;
                toRecyclingPointActivity.onPauseFeatureChange(i);
            }
        };
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onFaceAnimationViewClick$1
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
                ToRecyclingPointContract.TransferEvent transferEvent;
                ToRecyclingPointContract.TransferEvent transferEvent2;
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                ToRecyclingPointContract.TransferEvent transferEvent3;
                str = ToRecyclingPointActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                transferEvent = ToRecyclingPointActivity.this.currentEventStatus;
                sb.append(transferEvent);
                Pdlog.m3273d(str, sb.toString());
                transferEvent2 = ToRecyclingPointActivity.this.currentEventStatus;
                if (transferEvent2 != ToRecyclingPointContract.TransferEvent.ON_THE_WAY) {
                    transferEvent3 = ToRecyclingPointActivity.this.currentEventStatus;
                    if (transferEvent3 != ToRecyclingPointContract.TransferEvent.ACTIVE) {
                        return;
                    }
                }
                mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                mToRecyclingPointPresenter.actionPause();
            }
        }, 3, null);
        this.onVoicePlayEvent = new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$onVoicePlayEvent$1
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
            public final void invoke2(PlayEvent event) {
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                String str;
                IRecycleVoice iRecycleVoice;
                String str2;
                IRecycleVoice iRecycleVoice2;
                IRecycleVoice iRecycleVoice3;
                Intrinsics.checkParameterIsNotNull(event, "event");
                mDangerousAreaTipHelper = ToRecyclingPointActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(event);
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3275i(str, "event: " + event.name());
                int i = ToRecyclingPointActivity.WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
                if (i == 1) {
                    iRecycleVoice = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                } else if (i == 2) {
                    iRecycleVoice2 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice2.onStopVoice();
                } else if (i == 3) {
                    iRecycleVoice3 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice3.onPlayVoice();
                }
                str2 = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3275i(str2, "onVoicePlayEvent: " + event.name());
            }
        };
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.singleBatteryListener = new ToRecyclingPointActivity$singleBatteryListener$1(this);
    }

    /* compiled from: ToRecyclingPointActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/ToRecyclingPointActivity$Companion;", "", "()V", "COME_FROM_RECYCLE_PLATE", "", "recyclingIntent", "Landroid/content/Intent;", "ac", "Landroidx/appcompat/app/AppCompatActivity;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent recyclingIntent(AppCompatActivity ac) {
            Intrinsics.checkParameterIsNotNull(ac, "ac");
            return new Intent(ac, (Class<?>) ToRecyclingPointActivity.class);
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_to_recycling_point;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        this.isComeFromRecyclePlate = getIntent().getBooleanExtra("COME_FROM_RECYCLE_PLATE", false);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new ToRecyclingPointActivity$initView$1(null), 2, null);
        bindPresenter();
        showOnTheWayStatus();
        getMToRecyclingPointPresenter().actionGo();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        initListener();
    }

    private final void initListener() {
        RelativeLayout layoutContainer = (RelativeLayout) _$_findCachedViewById(C4188R.id.layoutContainer);
        Intrinsics.checkExpressionValueIsNotNull(layoutContainer, "layoutContainer");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(layoutContainer, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$1
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
                ToRecyclingPointContract.TransferEvent transferEvent;
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                transferEvent = ToRecyclingPointActivity.this.currentEventStatus;
                if (transferEvent == ToRecyclingPointContract.TransferEvent.APPROACHING) {
                    mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                    mToRecyclingPointPresenter.actionArrived();
                }
            }
        }, 3, null);
        FrameLayout cancel_ll = (FrameLayout) _$_findCachedViewById(C4188R.id.cancel_ll);
        Intrinsics.checkExpressionValueIsNotNull(cancel_ll, "cancel_ll");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(cancel_ll, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$2
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
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ToRecyclingPointActivity toRecyclingPointActivity = ToRecyclingPointActivity.this;
                i = toRecyclingPointActivity.TYPE_PAUSE_FEATURE_DIALOG;
                toRecyclingPointActivity.onPauseFeatureChange(i);
                if (Constans.INSTANCE.getRecycleExitSwitch()) {
                    ToRecyclingPointActivity.this.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$2.1
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
                            ToRecyclingPointPresenter mToRecyclingPointPresenter;
                            ToRecyclingPointPresenter mToRecyclingPointPresenter2;
                            if (z) {
                                str = ToRecyclingPointActivity.this.TAG;
                                Pdlog.m3273d(str, "onPauseEvenClick cancel_ll dialog onSure");
                                GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                                ToRecyclingPointActivity.this.dismissPasswordDialog();
                                mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                                mToRecyclingPointPresenter.actionCancelTask();
                                mToRecyclingPointPresenter2 = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                                mToRecyclingPointPresenter2.actionFinish();
                                ToRecyclingPointActivity.this.startRecycleTaskActivity();
                            }
                        }
                    }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$2.2
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
                            int i2;
                            ToRecyclingPointActivity toRecyclingPointActivity2 = ToRecyclingPointActivity.this;
                            i2 = ToRecyclingPointActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                            toRecyclingPointActivity2.onPauseFeatureChange(i2);
                        }
                    });
                    return;
                }
                ToRecyclingPointActivity toRecyclingPointActivity2 = ToRecyclingPointActivity.this;
                String string = toRecyclingPointActivity2.getString(C4188R.string.pdStr2_11);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_11)");
                toRecyclingPointActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$2.3
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
                        ToRecyclingPointPresenter mToRecyclingPointPresenter;
                        ToRecyclingPointPresenter mToRecyclingPointPresenter2;
                        str = ToRecyclingPointActivity.this.TAG;
                        Pdlog.m3273d(str, "onPauseEvenClick cancel_ll dialog onSure");
                        GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                        mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                        mToRecyclingPointPresenter.actionCancelTask();
                        mToRecyclingPointPresenter2 = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                        mToRecyclingPointPresenter2.actionFinish();
                        ToRecyclingPointActivity.this.startRecycleTaskActivity();
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$2.4
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
                        int i2;
                        ToRecyclingPointActivity toRecyclingPointActivity3 = ToRecyclingPointActivity.this;
                        i2 = ToRecyclingPointActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                        toRecyclingPointActivity3.onPauseFeatureChange(i2);
                    }
                });
            }
        }, 3, null);
        FrameLayout pause_layout = (FrameLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(pause_layout, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$3
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
                ToRecyclingPointContract.TransferEvent transferEvent;
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "pause_layout OnClickListener");
                transferEvent = ToRecyclingPointActivity.this.currentEventStatus;
                if (transferEvent != ToRecyclingPointContract.TransferEvent.DONE) {
                    mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                    mToRecyclingPointPresenter.actionActive();
                }
            }
        }, 3, null);
        TextView continue_tv = (TextView) _$_findCachedViewById(C4188R.id.continue_tv);
        Intrinsics.checkExpressionValueIsNotNull(continue_tv, "continue_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(continue_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$4
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
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                ToRecyclingPointPresenter mToRecyclingPointPresenter2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "continue_tv OnClickListener");
                mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                mToRecyclingPointPresenter.actionCancelTask();
                mToRecyclingPointPresenter2 = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                mToRecyclingPointPresenter2.actionFinish();
                ToRecyclingPointActivity.this.startRecycleTaskActivity();
            }
        }, 3, null);
        TextView turn_back_tv = (TextView) _$_findCachedViewById(C4188R.id.turn_back_tv);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_tv, "turn_back_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(turn_back_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$initListener$5
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "turn_back_tv OnClickListener");
                GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                Intent saveSceneId = IntentExtKt.saveSceneId(new Intent(ToRecyclingPointActivity.this, (Class<?>) TurnBackActivity.class), GoDishWashTrack.INSTANCE.getSessionId());
                saveSceneId.putExtra("COME_FROM_TRANSFER_DISHES", true);
                ToRecyclingPointActivity.this.jumpAndFinish(saveSceneId);
            }
        }, 3, null);
        setCountdown((int) (Constant.INSTANCE.getRecyclePauseTime() / 1000));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRecycleTaskActivity() {
        runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$startRecycleTaskActivity$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "startRecycleTaskActivity");
                ToRecyclingPointActivity.this.jumpAndFinish(new Intent(ToRecyclingPointActivity.this, (Class<?>) RecycleTaskActivity.class));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        getMToRecyclingPointPresenter().actionPauseNoTimer();
        getMToRecyclingPointPresenter().actionCancelTask();
        release();
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(i);
        finish();
    }

    private final void release() {
        Pdlog.m3273d(this.TAG, "release");
        this.isRelease = true;
        unBindPresenter();
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.ViewInterface
    public void showTransferEvent(String name, ToRecyclingPointContract.TransferEvent event) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.currentEventName = name;
        this.currentEventStatus = event;
        Pdlog.m3273d(this.TAG, "showTransferEvent " + name + " , " + event);
        if (!this.isComeFromRecyclePlate) {
            this.recycleVoice.recordPause(event == ToRecyclingPointContract.TransferEvent.PAUSE);
        }
        switch (event) {
            case NO_DESTINATION:
                GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.NoTarget);
                jumpAndFinish(new Intent(this, (Class<?>) RecycleTaskActivity.class));
                break;
            case ON_THE_WAY:
                GoDishWashTrack.INSTANCE.onMove();
                if (this.lastEventStatus != event) {
                    showOnTheWayStatus();
                    break;
                }
                break;
            case APPROACHING:
                if (this.lastEventStatus != event) {
                    this.recycleVoice.resetCount();
                    TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.leaveVoiceTask;
                    if (voiceTaskWrapper != null) {
                        TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper);
                    }
                    showOnArrivingStatus();
                    break;
                }
                break;
            case ACTIVE:
                GoDishWashTrack.INSTANCE.onMove();
                if (this.lastEventStatus != event) {
                    showOnTheWayStatus();
                    playLeaveVoiceOrTts();
                    break;
                }
                break;
            case PAUSE:
                GoDishWashTrack.INSTANCE.onPause();
                if (this.lastEventStatus != event) {
                    bindTtsPlayEvent$default(this, this.pauseVoiceTask, null, false, 6, null);
                    showOnPauseStatus();
                    break;
                }
                break;
            case ARRIVAL:
                GoDishWashTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                if (this.lastEventStatus != event) {
                    showOnArriveStatus();
                    break;
                }
                break;
            case DONE:
                startRecycleTaskActivity();
                break;
        }
        this.lastEventStatus = event;
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        GoDishWashTrack goDishWashTrack = GoDishWashTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@ToRecyclingPointAct…ty::class.java.simpleName");
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        goDishWashTrack.onCreateTask(simpleName, IntentExtKt.getSceneId$default(intent, null, 1, null));
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, this, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindPresenter$1
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
                ToRecyclingPointPresenter mToRecyclingPointPresenter;
                mToRecyclingPointPresenter = ToRecyclingPointActivity.this.getMToRecyclingPointPresenter();
                mToRecyclingPointPresenter.actionActive();
            }
        });
        ToRecyclingPointActivity toRecyclingPointActivity = this;
        getAutoResumeCountDownPresenter().replaceView(toRecyclingPointActivity);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        getMToRecyclingPointPresenter().replaceView(toRecyclingPointActivity);
        this.motionEventHelper.setOnTheWayAnimation(getOnTheWayAnim());
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(null, face_animation_view2, new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindPresenter$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }
        });
        this.motionEventHelper.setOnVoicePlayListener(this.onVoicePlayEvent);
        this.motionEventHelper.setMoveVoicePlayingLedTask(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindPresenter$3
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
                IRecycleVoice iRecycleVoice;
                IRecycleVoice iRecycleVoice2;
                if (z) {
                    iRecycleVoice2 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice2.onPlayVoice();
                } else {
                    iRecycleVoice = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                }
            }
        });
        this.motionEventHelper.setRobotScheduleListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindPresenter$4
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
                IRecycleVoice iRecycleVoice;
                IRecycleVoice iRecycleVoice2;
                if (z) {
                    iRecycleVoice2 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice2.forceStop();
                } else {
                    iRecycleVoice = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice.forcePlay();
                }
            }
        });
        this.touchSensorEventHelper.setOnVoiceStateListener(this.onVoicePlayEvent);
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new SimpleMusicPlayerCallbck() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindPresenter$5
            @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck, com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                String str;
                MotionEventHelper motionEventHelper2;
                super.onPrepared();
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3273d(str, "setMusicPlayerStateCallback onPrepared");
                motionEventHelper2 = ToRecyclingPointActivity.this.motionEventHelper;
                motionEventHelper2.stopMovingVoice();
            }
        });
        getMDangerousAreaTipHelper().bindLifecycle(this);
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        ToRecyclingPointActivity toRecyclingPointActivity = this;
        getAutoResumeCountDownPresenter().removeView(toRecyclingPointActivity);
        getMToRecyclingPointPresenter().removeView(toRecyclingPointActivity);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        this.touchSensorEventHelper.unBindPresent();
        this.motionEventHelper.unBind();
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.arriveVoiceTask;
        if (voiceTaskWrapper != null) {
            TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper);
        }
        this.recycleVoice.release();
    }

    private final void showOnPauseStatus() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(8);
        FrameLayout pause_layout = (FrameLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(0);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        this.motionEventHelper.setCanHandleMovingEvent(false);
        if (this.runningErrorHelper.isErrorShowing()) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            setCountdown((int) (Constant.INSTANCE.getRecyclePauseTime() / 1000));
        }
        PeripheralsSceneUtil.INSTANCE.showDeliveryPause();
    }

    private final void showOnTheWayStatus() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(8);
        FrameLayout pause_layout = (FrameLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(getOnTheWayAnim());
        this.touchSensorEventHelper.setCanHandle(true, true);
        this.motionEventHelper.setCanHandleMovingEvent(true);
        PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
        runTask(new Function0<String>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$showOnTheWayStatus$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String dashWashChosen = RobotMapManager.INSTANCE.getDashWashChosen();
                return dashWashChosen != null ? dashWashChosen : "";
            }
        }, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$showOnTheWayStatus$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                MotionEventHelper motionEventHelper;
                MotionEventHelper motionEventHelper2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ((FaceVideoView) ToRecyclingPointActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(it);
                motionEventHelper = ToRecyclingPointActivity.this.motionEventHelper;
                String recycleModeText = Constans.INSTANCE.getRecycleModeText();
                if (StringsKt.isBlank(recycleModeText)) {
                    recycleModeText = it;
                }
                motionEventHelper.setCurrentMovingText(recycleModeText);
                motionEventHelper2 = ToRecyclingPointActivity.this.motionEventHelper;
                motionEventHelper2.showLedScreenString();
                TextView target_name_tv = (TextView) ToRecyclingPointActivity.this._$_findCachedViewById(C4188R.id.target_name_tv);
                Intrinsics.checkExpressionValueIsNotNull(target_name_tv, "target_name_tv");
                target_name_tv.setText(it);
            }
        });
    }

    private final void showOnArrivingStatus() {
        Pdlog.m3273d(this.TAG, "showArrivingStatus");
        this.motionEventHelper.setCanHandleMovingEvent(false);
        FrameLayout pause_layout = (FrameLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(0);
        TextView continue_tv = (TextView) _$_findCachedViewById(C4188R.id.continue_tv);
        Intrinsics.checkExpressionValueIsNotNull(continue_tv, "continue_tv");
        ViewExtKt.gone(continue_tv);
        TextView turn_back_tv = (TextView) _$_findCachedViewById(C4188R.id.turn_back_tv);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_tv, "turn_back_tv");
        ViewExtKt.gone(turn_back_tv);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        PeripheralsSceneUtil.showDeliveryArriving$default(PeripheralsSceneUtil.INSTANCE, null, false, 3, null);
    }

    private final void showOnArriveStatus() {
        Pdlog.m3273d(this.TAG, "showOnArriveStatus");
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(0);
        FrameLayout pause_layout = (FrameLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        TextView continue_tv = (TextView) _$_findCachedViewById(C4188R.id.continue_tv);
        Intrinsics.checkExpressionValueIsNotNull(continue_tv, "continue_tv");
        ViewExtKt.show(continue_tv);
        TextView turn_back_tv = (TextView) _$_findCachedViewById(C4188R.id.turn_back_tv);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_tv, "turn_back_tv");
        ViewExtKt.show(turn_back_tv);
        this.motionEventHelper.setCanHandleMovingEvent(false);
        PeripheralsSceneUtil.INSTANCE.showDeliveryArrived();
        runTask(new Function0<String>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$showOnArriveStatus$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String dashWashChosen = RobotMapManager.INSTANCE.getDashWashChosen();
                return dashWashChosen != null ? dashWashChosen : "";
            }
        }, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$showOnArriveStatus$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                MotionEventHelper motionEventHelper;
                MotionEventHelper motionEventHelper2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                motionEventHelper = ToRecyclingPointActivity.this.motionEventHelper;
                String recycleModeText = Constans.INSTANCE.getRecycleModeText();
                if (!StringsKt.isBlank(recycleModeText)) {
                    it = recycleModeText;
                }
                motionEventHelper.setCurrentMovingText(it);
                motionEventHelper2 = ToRecyclingPointActivity.this.motionEventHelper;
                motionEventHelper2.showLedScreenString();
            }
        });
        playArriveVoiceOrTts();
    }

    private final <T> void runTask(Function0<? extends T> ioTask, Function1<? super T, Unit> mainTask) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new ToRecyclingPointActivity$runTask$1(ioTask, mainTask, null), 2, null);
    }

    private final FaceVideoAnimation getOnTheWayAnim() {
        return SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isRecyclePlateFace());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            getMToRecyclingPointPresenter().actionPause();
        } else if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            getMToRecyclingPointPresenter().actionPauseNoTimer();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            resetCountdownLayout();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.ViewInterface
    public void showTimeLeft(long time, AutoResumeCountDownContract.Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Pdlog.m3273d(this.TAG, "showTimeLeft " + time);
        setCountdown((int) time);
    }

    private final void setCountdown(int time) {
        try {
            TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr16_9);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr16_9)");
            Object[] objArr = {Integer.valueOf(time)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            countdown_tv.setText(format);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    private final void resetCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C4188R.string.pdStr16_39);
        Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr16_39)");
        Object[] objArr = new Object[0];
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        countdown_tv.setText(format);
    }

    private final boolean recycleLeaveIsOpen() {
        return TtsVoiceHelper.INSTANCE.isOpen(this, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE);
    }

    private final boolean recycleArrivedIsOpen() {
        return TtsVoiceHelper.INSTANCE.isOpen(this, TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE);
    }

    private final void playLeaveVoiceOrTts() {
        if (this.isComeFromRecyclePlate) {
            this.isComeFromRecyclePlate = false;
            Pdlog.m3275i(this.TAG, "playLeaveVoiceOrTts");
            this.recycleVoice.forceStop();
            this.recycleVoice.setPause(false);
            this.leaveVoiceTask = bindTtsPlayEvent(new VoiceTask(-1L, VoiceItem.voice34_7), TtsVoiceWrapperPlayer.PlayType.RECYCLE_TABLE_LEAVE, recycleLeaveIsOpen());
        }
    }

    private final void playArriveVoiceOrTts() {
        this.arriveVoiceTask = bindTtsPlayEvent(new VoiceTask(-1L, VoiceItem.voice34_1), TtsVoiceWrapperPlayer.PlayType.RECYCLE_POINT_ARRIVED, recycleArrivedIsOpen());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TtsVoiceWrapperPlayer.VoiceTaskWrapper bindTtsPlayEvent$default(ToRecyclingPointActivity toRecyclingPointActivity, VoiceTask voiceTask, TtsVoiceWrapperPlayer.PlayType playType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            playType = (TtsVoiceWrapperPlayer.PlayType) null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return toRecyclingPointActivity.bindTtsPlayEvent(voiceTask, playType, z);
    }

    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper bindTtsPlayEvent(VoiceTask voiceTask, TtsVoiceWrapperPlayer.PlayType playType, boolean isOpen) {
        voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindTtsPlayEvent$1
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                String str;
                IRecycleVoice iRecycleVoice;
                IRecycleVoice iRecycleVoice2;
                IRecycleVoice iRecycleVoice3;
                Intrinsics.checkParameterIsNotNull(event, "event");
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3275i(str, "bindTtsPlayEvent voiceTask event=" + event.name());
                int i = ToRecyclingPointActivity.WhenMappings.$EnumSwitchMapping$2[event.ordinal()];
                if (i == 1) {
                    iRecycleVoice = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                } else if (i == 2) {
                    iRecycleVoice2 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice2.onStopVoice();
                } else {
                    if (i != 3) {
                        return;
                    }
                    iRecycleVoice3 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice3.onPlayVoice();
                }
            }
        });
        if (playType == null || !isOpen) {
            VoicePlayer.INSTANCE.play(voiceTask);
            return new TtsVoiceWrapperPlayer.VoiceTaskWrapper(voiceTask, null, null, 6, null);
        }
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(voiceTask, playType, null, 4, null);
        TtsVoiceWrapperPlayer.INSTANCE.play(voiceTaskWrapper, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.ToRecyclingPointActivity$bindTtsPlayEvent$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                invoke2(audioPlayEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                String str;
                IRecycleVoice iRecycleVoice;
                IRecycleVoice iRecycleVoice2;
                IRecycleVoice iRecycleVoice3;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = ToRecyclingPointActivity.this.TAG;
                Pdlog.m3275i(str, "bindTtsPlayEvent voiceTaskWrapper event=" + it.name());
                int i = ToRecyclingPointActivity.WhenMappings.$EnumSwitchMapping$3[it.ordinal()];
                if (i == 1) {
                    iRecycleVoice = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                } else if (i == 2) {
                    iRecycleVoice2 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice2.onStopVoice();
                } else {
                    if (i != 3) {
                        return;
                    }
                    iRecycleVoice3 = ToRecyclingPointActivity.this.recycleVoice;
                    iRecycleVoice3.onPlayVoice();
                }
            }
        });
        return voiceTaskWrapper;
    }
}
