package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.recycle_task.RecycleContract;
import com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
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
import com.pudutech.bumblebee.robot_ui.p054ui.recycle.RecyclingPlatesActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.view.RecyclePlateArriveLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.RecycleTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RecyclingPlatesActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ù\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001P\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010T\u001a\u00020$H\u0002J&\u0010U\u001a\u00020\r2\u0006\u0010V\u001a\u00020&2\n\b\u0002\u0010W\u001a\u0004\u0018\u00010X2\b\b\u0002\u0010Y\u001a\u00020\u001cH\u0002J\b\u0010Z\u001a\u00020$H\u0016J\b\u0010[\u001a\u00020\\H\u0002J\u0012\u0010]\u001a\u00020$2\b\b\u0002\u0010^\u001a\u00020\u001cH\u0002J\b\u0010_\u001a\u00020$H\u0002J\u0012\u0010`\u001a\u00020$2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J\u0010\u0010c\u001a\u00020$2\u0006\u0010d\u001a\u00020eH\u0016J\b\u0010f\u001a\u00020\tH\u0016J\u0010\u0010g\u001a\u00020$2\u0006\u0010h\u001a\u00020\tH\u0002J\u0010\u0010i\u001a\u00020$2\u0006\u0010j\u001a\u00020\u001cH\u0016J\b\u0010k\u001a\u00020$H\u0002J\b\u0010l\u001a\u00020$H\u0002J\b\u0010m\u001a\u00020\u001cH\u0002J\b\u0010n\u001a\u00020\u001cH\u0002J\b\u0010o\u001a\u00020$H\u0002J\b\u0010p\u001a\u00020$H\u0002J\b\u0010q\u001a\u00020$H\u0002J4\u0010r\u001a\u00020$\"\u0004\b\u0000\u0010s2\f\u0010t\u001a\b\u0012\u0004\u0012\u0002Hs0#2\u0016\b\u0002\u0010u\u001a\u0010\u0012\u0004\u0012\u0002Hs\u0012\u0004\u0012\u00020$\u0018\u00010@H\u0002J\u0010\u0010v\u001a\u00020$2\u0006\u0010w\u001a\u00020\tH\u0002J\b\u0010x\u001a\u00020$H\u0002J\b\u0010y\u001a\u00020$H\u0002J\b\u0010z\u001a\u00020$H\u0002J\b\u0010{\u001a\u00020$H\u0002J<\u0010|\u001a\u00020$2\u0006\u0010}\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u00152\"\u0010~\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0019`\u001aH\u0016J\u001b\u0010\u007f\u001a\u00020$2\u0007\u0010w\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0016J\t\u0010\u0083\u0001\u001a\u00020$H\u0002J\u0012\u0010\u0084\u0001\u001a\u00020$2\u0007\u0010\u0085\u0001\u001a\u00020\tH\u0002J\t\u0010\u0086\u0001\u001a\u00020$H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0017\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020(8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u0013\u001a\u0004\b)\u0010*R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R@\u00100\u001a4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020$\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00109\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R>\u0010:\u001a2\u0012\u0013\u0012\u00110;¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(<\u0012\u0013\u0012\u00110=¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(>\u0012\u0004\u0012\u00020$01X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010?\u001a\u001d\u0012\u0013\u0012\u00110A¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(>\u0012\u0004\u0012\u00020$0@X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010C\u001a\u00020D8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bG\u0010\u0013\u001a\u0004\bE\u0010FR\u000e\u0010H\u001a\u00020IX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010L\u001a\u001d\u0012\u0013\u0012\u00110M¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020$0@X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u00020PX\u0082\u0004¢\u0006\u0004\n\u0002\u0010QR\u000e\u0010R\u001a\u00020SX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0087\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecyclingPlatesActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecyclingPlatesVM;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$ViewInterface;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_DIALOG", "", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "arriveVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleEvent;", "currentTaskId", "currentTaskMap", "Ljava/util/LinkedHashMap;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleViewModel;", "Lkotlin/collections/LinkedHashMap;", "isCancelTask", "", "isFirstStart", "isRelease", "isStartLeave", "lastEventStatus", "leaveVoiceTask", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "onVoicePlayEvent", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "pauseVoiceTask", "recyclePresenter", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecyclePresenter;", "getRecyclePresenter", "()Lcom/pudutech/bumblebee/presenter/recycle_task/RecyclePresenter;", "recyclePresenter$delegate", "recycleVoice", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/IRecycleVoice;", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "scheduleEventLister", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "triggerEvent", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/recycle/RecyclingPlatesActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecyclingPlatesActivity$singleBatteryListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "bindPresenter", "bindTtsPlayEvent", "voiceTask", "playType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "isOpen", "createObserver", "getOnTheWayAnim", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "goRecycleTable", "endLeave", "initListener", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jumpAndFinish", "i", "Landroid/content/Intent;", "layoutId", "onPauseFeatureChange", "type", "onWindowFocusChanged", "hasFocus", "playArriveVoiceOrTts", "playLeaveVoiceOrTts", "recycleArrivedIsOpen", "recycleLeaveIsOpen", "release", "releaseDataAndFinish", "resetCountdownLayout", "runTask", ExifInterface.GPS_DIRECTION_TRUE, "ioTask", "mainTask", "setCountdown", "time", "showOnArriveStatus", "showOnArrivingStatus", "showOnPauseStatus", "showOnTheWayStatus", "showRecycleEvent", "current", "all", "showTimeLeft", "", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "startRecycleTask", "startRecycleTaskActivity", "mode", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecyclingPlatesActivity extends MyBaseVmActivity<RecyclingPlatesVM> implements AutoResumeCountDownContract.ViewInterface, RecycleContract.ViewInterface {
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private HashMap _$_findViewCache;
    private TtsVoiceWrapperPlayer.VoiceTaskWrapper arriveVoiceTask;

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter;
    private RecycleContract.RecycleEvent currentEventStatus;
    private String currentTaskId;
    private LinkedHashMap<String, RecycleContract.RecycleViewModel> currentTaskMap;
    private boolean isCancelTask;
    private boolean isFirstStart;
    private boolean isRelease;
    private boolean isStartLeave;
    private RecycleContract.RecycleEvent lastEventStatus;
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
    private final Function1<ScheduleContract.TriggerEvent, Unit> scheduleEventLister;
    private final RecyclingPlatesActivity$singleBatteryListener$1 singleBatteryListener;
    private final TouchSensorEventHelper touchSensorEventHelper;
    private final String TAG = "RecyclePlateActivity";
    private final IRecycleVoice recycleVoice = new RecycleVoice();

    /* renamed from: recyclePresenter$delegate, reason: from kotlin metadata */
    private final Lazy recyclePresenter = LazyKt.lazy(new Function0<RecyclePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$recyclePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RecyclePresenter invoke() {
            RecyclePresenter recyclePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(RecyclePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(RecyclePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                recyclePresenter = new RecyclePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(RecyclePresenter.class).toString(), recyclePresenter);
            } else {
                if (!(basePresenterInterface instanceof RecyclePresenter)) {
                    basePresenterInterface = null;
                }
                recyclePresenter = (RecyclePresenter) basePresenterInterface;
            }
            if (recyclePresenter != null) {
                return recyclePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.recycle_task.RecyclePresenter");
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
            $EnumSwitchMapping$1 = new int[RecycleContract.RecycleEvent.values().length];
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.ON_THE_WAY.ordinal()] = 1;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.APPROACHING.ordinal()] = 2;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.ARRIVAL.ordinal()] = 3;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.ACTIVE.ordinal()] = 4;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.PAUSE.ordinal()] = 5;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.DONE.ordinal()] = 6;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.DONE_BEFORE_ARRIVAL.ordinal()] = 7;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.ALL_DONE.ordinal()] = 8;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.ALL_LEFT_CANCEL.ordinal()] = 9;
            $EnumSwitchMapping$1[RecycleContract.RecycleEvent.MODIFY.ordinal()] = 10;
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
    public final RecyclePresenter getRecyclePresenter() {
        return (RecyclePresenter) this.recyclePresenter.getValue();
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

    public RecyclingPlatesActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$$special$$inlined$apply$lambda$1
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
                RecycleContract.RecycleEvent recycleEvent;
                IRecycleVoice iRecycleVoice;
                PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
                recycleEvent = RecyclingPlatesActivity.this.currentEventStatus;
                if (recycleEvent != RecycleContract.RecycleEvent.PAUSE) {
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.forcePlay();
                }
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$autoResumeCountDownPresenter$2
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
        this.movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
        this.currentEventStatus = RecycleContract.RecycleEvent.ACTIVE;
        this.lastEventStatus = this.currentEventStatus;
        this.currentTaskId = "";
        this.isFirstStart = true;
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.touchSensorEventHelper = new TouchSensorEventHelper();
        this.scheduleEventLister = new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$scheduleEventLister$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }
        };
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                RecycleTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onErrorDialogShowStatus$1
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
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                    return;
                }
                RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                i = recyclingPlatesActivity.TYPE_PAUSE_FEATURE_ERROR;
                recyclingPlatesActivity.onPauseFeatureChange(i);
                PeripheralsSceneUtil.INSTANCE.showRunError();
                iRecycleVoice2 = RecyclingPlatesActivity.this.recycleVoice;
                iRecycleVoice2.onPlayVoice();
                if (z2) {
                    RecycleTrack.INSTANCE.onEmergencyStop();
                }
            }
        };
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onFaceAnimationViewClick$1
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
                RecycleContract.RecycleEvent recycleEvent;
                RecycleContract.RecycleEvent recycleEvent2;
                RecyclePresenter recyclePresenter;
                RecycleContract.RecycleEvent recycleEvent3;
                str = RecyclingPlatesActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                recycleEvent = RecyclingPlatesActivity.this.currentEventStatus;
                sb.append(recycleEvent);
                Pdlog.m3273d(str, sb.toString());
                recycleEvent2 = RecyclingPlatesActivity.this.currentEventStatus;
                if (recycleEvent2 != RecycleContract.RecycleEvent.ON_THE_WAY) {
                    recycleEvent3 = RecyclingPlatesActivity.this.currentEventStatus;
                    if (recycleEvent3 != RecycleContract.RecycleEvent.ACTIVE) {
                        return;
                    }
                }
                recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                recyclePresenter.actionPause();
            }
        }, 3, null);
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onFallDropCallBack$1
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
                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Abnormal);
                RecyclingPlatesActivity.this.releaseDataAndFinish();
            }
        };
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onLostLocationLostCallback$1
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
                RecyclePresenter recyclePresenter;
                LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                    str2 = RecyclingPlatesActivity.this.TAG;
                    Pdlog.m3273d(str2, "onLostLocationLostCallback");
                    ((FaceVideoView) RecyclingPlatesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                    voiceTask = recyclingPlatesActivity.locationLostVoiceTask;
                    RecyclingPlatesActivity.bindTtsPlayEvent$default(recyclingPlatesActivity, voiceTask, null, false, 6, null);
                    RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                    i = recyclingPlatesActivity2.TYPE_PAUSE_FEATURE_ERROR;
                    recyclingPlatesActivity2.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    return;
                }
                if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recyclePresenter.actionCancelAllTask();
                    RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                    Intent intent = new Intent(RecyclingPlatesActivity.this, (Class<?>) LaserRunningLocationLostActivity.class);
                    intent.putExtra(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, 7);
                    RecyclingPlatesActivity.this.jumpAndFinish(intent);
                }
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onErrorClearCallback$1
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
                RecyclePresenter recyclePresenter;
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) RecyclingPlatesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) RecyclingPlatesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recyclePresenter.actionPause();
                }
            }
        };
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$locationLostTouchCancelCallback$1
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
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                ((FaceVideoView) RecyclingPlatesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                i = recyclingPlatesActivity.TYPE_PAUSE_FEATURE_ERROR;
                recyclingPlatesActivity.onPauseFeatureChange(i);
            }
        };
        this.onVoicePlayEvent = new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$onVoicePlayEvent$1
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
                mDangerousAreaTipHelper = RecyclingPlatesActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(event);
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3275i(str, "event: " + event.name());
                int i = RecyclingPlatesActivity.WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
                if (i == 1) {
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                } else if (i == 2) {
                    iRecycleVoice2 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice2.onStopVoice();
                } else if (i == 3) {
                    iRecycleVoice3 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice3.onPlayVoice();
                }
                str2 = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3275i(str2, "onVoicePlayEvent: " + event.name());
            }
        };
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.singleBatteryListener = new RecyclingPlatesActivity$singleBatteryListener$1(this);
    }

    private final void startRecycleTaskActivity(int mode) {
        Pdlog.m3273d(this.TAG, "startRecycleTaskActivity " + mode);
        Intent intent = new Intent(this, (Class<?>) RecycleTaskActivity.class);
        intent.putExtra("MODE_TYPE", mode);
        jumpAndFinish(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        getRecyclePresenter().actionPauseNoTimer();
        getRecyclePresenter().actionCancelAllTask();
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

    private final void startRecycleTask() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RecyclingPlatesActivity$startRecycleTask$1(null), 2, null);
        ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
        Pdlog.m3273d(this.TAG, "startRecycleTask " + allTask);
        ArrayList<String> arrayList = new ArrayList<>();
        for (TrayModel trayModel : allTask) {
            if (!trayModel.getAllDestinations().isEmpty()) {
                arrayList.add(trayModel.getAllDestinations().get(0).getDestination());
            }
        }
        getRecyclePresenter().actionGo(arrayList, TableTaskManager.INSTANCE.getSortType());
        showOnTheWayStatus();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        RecycleTrack recycleTrack = RecycleTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this::class.java.simpleName");
        recycleTrack.onCreateTask(simpleName);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, this, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$bindPresenter$1
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
                RecyclePresenter recyclePresenter;
                recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                recyclePresenter.actionActive();
            }
        });
        RecyclingPlatesActivity recyclingPlatesActivity = this;
        getAutoResumeCountDownPresenter().replaceView(recyclingPlatesActivity);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        getRecyclePresenter().replaceView(recyclingPlatesActivity);
        this.motionEventHelper.setOnTheWayAnimation(getOnTheWayAnim());
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(null, face_animation_view2, this.scheduleEventLister);
        this.motionEventHelper.setOnVoicePlayListener(this.onVoicePlayEvent);
        this.motionEventHelper.setMoveVoicePlayingLedTask(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$bindPresenter$2
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
                    iRecycleVoice2 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice2.onPlayVoice();
                } else {
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                }
            }
        });
        this.motionEventHelper.setRobotScheduleListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$bindPresenter$3
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
                    iRecycleVoice2 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice2.forceStop();
                } else {
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.forcePlay();
                }
            }
        });
        this.touchSensorEventHelper.setOnVoiceStateListener(this.onVoicePlayEvent);
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new SimpleMusicPlayerCallbck() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$bindPresenter$4
            @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck, com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                String str;
                MotionEventHelper motionEventHelper2;
                super.onPrepared();
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "setMusicPlayerStateCallback onPrepared");
                motionEventHelper2 = RecyclingPlatesActivity.this.motionEventHelper;
                motionEventHelper2.stopMovingVoice();
            }
        });
        getMDangerousAreaTipHelper().bindLifecycle(this);
    }

    private final FaceVideoAnimation getOnTheWayAnim() {
        return SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isRecyclePlateFace());
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.touchSensorEventHelper.unBindPresent();
        this.motionEventHelper.unBind();
        this.runningErrorHelper.unbind();
        RecyclingPlatesActivity recyclingPlatesActivity = this;
        getAutoResumeCountDownPresenter().removeView(recyclingPlatesActivity);
        getRecyclePresenter().removeView(recyclingPlatesActivity);
        this.recycleVoice.release();
    }

    private final void showOnPauseStatus() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).hideView();
        View pause_layout = _$_findCachedViewById(C4188R.id.pause_layout);
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
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(getOnTheWayAnim());
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(getRecyclePresenter().getDestination());
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).hideView();
        View pause_layout = _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        this.touchSensorEventHelper.setCanHandle(true, true);
        this.motionEventHelper.setCanHandleMovingEvent(true);
        PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
        runTask$default(this, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$showOnTheWayStatus$1
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
                MotionEventHelper motionEventHelper;
                RecyclePresenter recyclePresenter;
                motionEventHelper = RecyclingPlatesActivity.this.motionEventHelper;
                String recycleModeText = Constans.INSTANCE.getRecycleModeText();
                if (recycleModeText.length() == 0) {
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recycleModeText = recyclePresenter.getDestination();
                }
                motionEventHelper.setCurrentMovingText(recycleModeText);
            }
        }, null, 2, null);
    }

    private final void showOnArrivingStatus() {
        Pdlog.m3273d(this.TAG, "showArrivingStatus");
        this.isStartLeave = true;
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        this.motionEventHelper.setCanHandleMovingEvent(false);
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).showArrivingView();
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).setTask(this.currentTaskId, this.currentTaskMap);
        View pause_layout = _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        PeripheralsSceneUtil.showDeliveryArriving$default(PeripheralsSceneUtil.INSTANCE, null, false, 3, null);
    }

    private final void showOnArriveStatus() {
        Pdlog.m3273d(this.TAG, "showOnArriveStatus");
        this.isStartLeave = true;
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).showView((int) (Constant.INSTANCE.isRecycleAutoComplete() ? Constant.INSTANCE.getRecycleCompleteTime() : -1L));
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).setTask(this.currentTaskId, this.currentTaskMap);
        View pause_layout = _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        this.motionEventHelper.setCanHandleMovingEvent(false);
        PeripheralsSceneUtil.INSTANCE.showDeliveryArrived();
        runTask$default(this, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$showOnArriveStatus$1
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
                MotionEventHelper motionEventHelper;
                MotionEventHelper motionEventHelper2;
                RecyclePresenter recyclePresenter;
                motionEventHelper = RecyclingPlatesActivity.this.motionEventHelper;
                String recycleModeText = Constans.INSTANCE.getRecycleModeText();
                if (recycleModeText.length() == 0) {
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recycleModeText = recyclePresenter.getDestination();
                }
                motionEventHelper.setCurrentMovingText(recycleModeText);
                motionEventHelper2 = RecyclingPlatesActivity.this.motionEventHelper;
                motionEventHelper2.showLedScreenString();
            }
        }, null, 2, null);
        playArriveVoiceOrTts();
    }

    static /* synthetic */ void runTask$default(RecyclingPlatesActivity recyclingPlatesActivity, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        recyclingPlatesActivity.runTask(function0, function1);
    }

    private final <T> void runTask(Function0<? extends T> ioTask, Function1<? super T, Unit> mainTask) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RecyclingPlatesActivity$runTask$1(ioTask, mainTask, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            getRecyclePresenter().actionPause();
        } else if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            getRecyclePresenter().actionPauseNoTimer();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            resetCountdownLayout();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.RecycleContract.ViewInterface
    public void showRecycleEvent(String current, RecycleContract.RecycleEvent event, LinkedHashMap<String, RecycleContract.RecycleViewModel> all) {
        Intrinsics.checkParameterIsNotNull(current, "current");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(all, "all");
        Pdlog.m3273d(this.TAG, "showRecycleEvent " + current + " , last event = " + this.currentEventStatus + " , new event = " + event + " , task " + all);
        this.currentTaskId = current;
        this.currentTaskMap = all;
        this.currentEventStatus = event;
        this.recycleVoice.recordPause(this.currentEventStatus == RecycleContract.RecycleEvent.PAUSE);
        switch (event) {
            case ON_THE_WAY:
                RecycleTrack.INSTANCE.onMove();
                if (this.lastEventStatus != event) {
                    showOnTheWayStatus();
                    playLeaveVoiceOrTts();
                    break;
                }
                break;
            case APPROACHING:
                if (this.lastEventStatus != event) {
                    showOnArrivingStatus();
                    TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.leaveVoiceTask;
                    if (voiceTaskWrapper != null) {
                        TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper);
                    }
                    this.recycleVoice.resetCount();
                    break;
                }
                break;
            case ARRIVAL:
                RecycleTrack.INSTANCE.onArrive(current);
                if (this.lastEventStatus != event) {
                    TableTaskManager.INSTANCE.finishTask(current);
                    showOnArriveStatus();
                    break;
                }
                break;
            case ACTIVE:
                RecycleTrack.INSTANCE.onMove();
                if (this.lastEventStatus != event) {
                    showOnTheWayStatus();
                    break;
                }
                break;
            case PAUSE:
                RecycleTrack.INSTANCE.onPause();
                if (this.lastEventStatus != event) {
                    bindTtsPlayEvent$default(this, this.pauseVoiceTask, null, false, 6, null);
                    showOnPauseStatus();
                    break;
                }
                break;
            case DONE:
                this.recycleVoice.resetCount();
                TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper2 = this.arriveVoiceTask;
                if (voiceTaskWrapper2 != null) {
                    TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper2);
                    break;
                }
                break;
            case DONE_BEFORE_ARRIVAL:
                RecycleTrack.INSTANCE.onFinishOne(current, BaseMoveTrackTask.FinishOneCause.InAdvance);
                break;
            case ALL_DONE:
                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                goRecycleTable$default(this, false, 1, null);
                break;
            case ALL_LEFT_CANCEL:
                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                startRecycleTaskActivity(0);
                break;
            case MODIFY:
                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LocalModification);
                startRecycleTaskActivity(1);
                break;
        }
        this.lastEventStatus = this.currentEventStatus;
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

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_recycle_plate;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        if (!TableTaskManager.INSTANCE.getHasTask()) {
            Pdlog.m3274e(this.TAG, "checkTableTask failed");
            startRecycleTaskActivity(0);
            return;
        }
        initListener();
        bindPresenter();
        startRecycleTask();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    private final void initListener() {
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).setContinueTaskClickListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$1
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
                String str2;
                RecyclePresenter recyclePresenter;
                String str3;
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "ContinueTaskClickListener");
                if (z) {
                    RecycleTrack recycleTrack = RecycleTrack.INSTANCE;
                    str3 = RecyclingPlatesActivity.this.currentTaskId;
                    recycleTrack.onFinishOne(str3, BaseMoveTrackTask.FinishOneCause.CountDown);
                } else {
                    RecycleTrack recycleTrack2 = RecycleTrack.INSTANCE;
                    str2 = RecyclingPlatesActivity.this.currentTaskId;
                    recycleTrack2.onFinishOne(str2, BaseMoveTrackTask.FinishOneCause.Click);
                }
                recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                recyclePresenter.actionFinish();
            }
        });
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).setGoRecycleTableClickListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$2
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
                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.GoToInventory);
                RecyclingPlatesActivity.goRecycleTable$default(RecyclingPlatesActivity.this, false, 1, null);
            }
        });
        ((RecyclePlateArriveLayout) _$_findCachedViewById(C4188R.id.arrive_layout)).setLayoutOnclickListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$3
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
                RecycleContract.RecycleEvent recycleEvent;
                RecyclePresenter recyclePresenter;
                recycleEvent = RecyclingPlatesActivity.this.currentEventStatus;
                if (recycleEvent == RecycleContract.RecycleEvent.APPROACHING) {
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recyclePresenter.actionArrived();
                }
            }
        });
        TextView go_recycle_table_ll = (TextView) _$_findCachedViewById(C4188R.id.go_recycle_table_ll);
        Intrinsics.checkExpressionValueIsNotNull(go_recycle_table_ll, "go_recycle_table_ll");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(go_recycle_table_ll, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$4
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
                RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                i = recyclingPlatesActivity.TYPE_PAUSE_FEATURE_DIALOG;
                recyclingPlatesActivity.onPauseFeatureChange(i);
                if (Constans.INSTANCE.getRecycleExitSwitch()) {
                    RecyclingPlatesActivity.this.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$4.1
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
                            if (z) {
                                str = RecyclingPlatesActivity.this.TAG;
                                Pdlog.m3273d(str, "onPauseEvenClick go_recycle_table_ll dialog onSure");
                                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.GoToInventory);
                                RecyclingPlatesActivity.this.dismissPasswordDialog();
                                RecyclingPlatesActivity.this.goRecycleTable(false);
                            }
                        }
                    }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$4.2
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
                            RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                            i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                            recyclingPlatesActivity2.onPauseFeatureChange(i2);
                        }
                    });
                    return;
                }
                RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                String string = recyclingPlatesActivity2.getString(C4188R.string.pdStr16_19);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr16_19)");
                recyclingPlatesActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$4.3
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
                        str = RecyclingPlatesActivity.this.TAG;
                        Pdlog.m3273d(str, "onPauseEvenClick go_recycle_table_ll dialog onSure");
                        RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.GoToInventory);
                        RecyclingPlatesActivity.this.goRecycleTable(false);
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$4.4
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
                        RecyclingPlatesActivity recyclingPlatesActivity3 = RecyclingPlatesActivity.this;
                        i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                        recyclingPlatesActivity3.onPauseFeatureChange(i2);
                    }
                });
            }
        }, 3, null);
        TextView tvPauseReturnBack = (TextView) _$_findCachedViewById(C4188R.id.tvPauseReturnBack);
        Intrinsics.checkExpressionValueIsNotNull(tvPauseReturnBack, "tvPauseReturnBack");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(tvPauseReturnBack, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$5
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
                RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                i = recyclingPlatesActivity.TYPE_PAUSE_FEATURE_DIALOG;
                recyclingPlatesActivity.onPauseFeatureChange(i);
                if (Constans.INSTANCE.getRecycleExitSwitch()) {
                    RecyclingPlatesActivity.this.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$5.1
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
                            if (z) {
                                str = RecyclingPlatesActivity.this.TAG;
                                Pdlog.m3273d(str, "onPauseEvenClick tvPauseReturnBack dialog onSure");
                                RecyclingPlatesActivity.this.dismissPasswordDialog();
                                RecyclingPlatesActivity.this.isCancelTask = true;
                                TableTaskManager.INSTANCE.clearAll();
                                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                                Intent saveSceneId = IntentExtKt.saveSceneId(new Intent(RecyclingPlatesActivity.this, (Class<?>) TurnBackActivity.class), RecycleTrack.INSTANCE.getSessionId());
                                saveSceneId.putExtra("COME_FROM_TRANSFER_DISHES", true);
                                RecyclingPlatesActivity.this.jumpAndFinish(saveSceneId);
                            }
                        }
                    }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$5.2
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
                            RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                            i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                            recyclingPlatesActivity2.onPauseFeatureChange(i2);
                        }
                    });
                    return;
                }
                RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                String string = recyclingPlatesActivity2.getString(C4188R.string.recycle_return_back_msg);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.recycle_return_back_msg)");
                recyclingPlatesActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$5.3
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
                        RecyclingPlatesActivity.this.isCancelTask = true;
                        TableTaskManager.INSTANCE.clearAll();
                        RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                        Intent saveSceneId = IntentExtKt.saveSceneId(new Intent(RecyclingPlatesActivity.this, (Class<?>) TurnBackActivity.class), RecycleTrack.INSTANCE.getSessionId());
                        saveSceneId.putExtra("COME_FROM_TRANSFER_DISHES", true);
                        RecyclingPlatesActivity.this.jumpAndFinish(saveSceneId);
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$5.4
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
                        RecyclingPlatesActivity recyclingPlatesActivity3 = RecyclingPlatesActivity.this;
                        i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                        recyclingPlatesActivity3.onPauseFeatureChange(i2);
                    }
                });
            }
        }, 3, null);
        TextView cancel_ll = (TextView) _$_findCachedViewById(C4188R.id.cancel_ll);
        Intrinsics.checkExpressionValueIsNotNull(cancel_ll, "cancel_ll");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(cancel_ll, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$6
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
                RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                i = recyclingPlatesActivity.TYPE_PAUSE_FEATURE_DIALOG;
                recyclingPlatesActivity.onPauseFeatureChange(i);
                if (Constans.INSTANCE.getRecycleExitSwitch()) {
                    RecyclingPlatesActivity.this.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$6.1
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
                            RecyclePresenter recyclePresenter;
                            RecyclePresenter recyclePresenter2;
                            if (z) {
                                str = RecyclingPlatesActivity.this.TAG;
                                Pdlog.m3273d(str, "onPauseEvenClick cancel_ll dialog onSure");
                                RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                                RecyclingPlatesActivity.this.dismissPasswordDialog();
                                RecyclingPlatesActivity.this.isCancelTask = true;
                                recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                                recyclePresenter.actionPauseNoTimer();
                                recyclePresenter2 = RecyclingPlatesActivity.this.getRecyclePresenter();
                                recyclePresenter2.actionCancelAllTask();
                            }
                        }
                    }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$6.2
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
                            RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                            i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                            recyclingPlatesActivity2.onPauseFeatureChange(i2);
                        }
                    });
                    return;
                }
                RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                String string = recyclingPlatesActivity2.getString(C4188R.string.pdStr2_11);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_11)");
                recyclingPlatesActivity2.showConfirmDialog(string, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$6.3
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
                        RecyclePresenter recyclePresenter;
                        RecyclePresenter recyclePresenter2;
                        str = RecyclingPlatesActivity.this.TAG;
                        Pdlog.m3273d(str, "onPauseEvenClick cancel_ll dialog onSure");
                        RecycleTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                        RecyclingPlatesActivity.this.isCancelTask = true;
                        recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                        recyclePresenter.actionPauseNoTimer();
                        recyclePresenter2 = RecyclingPlatesActivity.this.getRecyclePresenter();
                        recyclePresenter2.actionCancelAllTask();
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$6.4
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
                        RecyclingPlatesActivity recyclingPlatesActivity3 = RecyclingPlatesActivity.this;
                        i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                        recyclingPlatesActivity3.onPauseFeatureChange(i2);
                    }
                });
            }
        }, 3, null);
        TextView modify_ll = (TextView) _$_findCachedViewById(C4188R.id.modify_ll);
        Intrinsics.checkExpressionValueIsNotNull(modify_ll, "modify_ll");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(modify_ll, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$7
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
                RecyclePresenter recyclePresenter;
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "modify_ll onSingleClick");
                if (!Constans.INSTANCE.getRecycleExitSwitch()) {
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recyclePresenter.actionModify();
                } else {
                    RecyclingPlatesActivity recyclingPlatesActivity = RecyclingPlatesActivity.this;
                    i = recyclingPlatesActivity.TYPE_PAUSE_FEATURE_DIALOG;
                    recyclingPlatesActivity.onPauseFeatureChange(i);
                    RecyclingPlatesActivity.this.showPasswordDialog(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$7.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z) {
                            String str2;
                            RecyclePresenter recyclePresenter2;
                            if (z) {
                                str2 = RecyclingPlatesActivity.this.TAG;
                                Pdlog.m3273d(str2, "onPauseEvenClick modify_ll dialog onSure");
                                RecyclingPlatesActivity.this.dismissPasswordDialog();
                                recyclePresenter2 = RecyclingPlatesActivity.this.getRecyclePresenter();
                                recyclePresenter2.actionModify();
                            }
                        }
                    }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$7.2
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
                            RecyclingPlatesActivity recyclingPlatesActivity2 = RecyclingPlatesActivity.this;
                            i2 = RecyclingPlatesActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                            recyclingPlatesActivity2.onPauseFeatureChange(i2);
                        }
                    });
                }
            }
        }, 3, null);
        View pause_layout = _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(pause_layout, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$initListener$8
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
                RecycleContract.RecycleEvent recycleEvent;
                RecyclePresenter recyclePresenter;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3273d(str, "pause_layout OnClickListener");
                recycleEvent = RecyclingPlatesActivity.this.currentEventStatus;
                if (recycleEvent != RecycleContract.RecycleEvent.DONE) {
                    recyclePresenter = RecyclingPlatesActivity.this.getRecyclePresenter();
                    recyclePresenter.actionActive();
                }
            }
        }, 3, null);
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void goRecycleTable$default(RecyclingPlatesActivity recyclingPlatesActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        recyclingPlatesActivity.goRecycleTable(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goRecycleTable(boolean endLeave) {
        getRecyclePresenter().actionCancelAllTask();
        TableTaskManager.INSTANCE.clearAll();
        Intent saveSceneId = IntentExtKt.saveSceneId(ToRecyclingPointActivity.INSTANCE.recyclingIntent(this), RecycleTrack.INSTANCE.getSessionId());
        saveSceneId.putExtra("COME_FROM_RECYCLE_PLATE", endLeave);
        jumpAndFinish(saveSceneId);
    }

    private final boolean recycleArrivedIsOpen() {
        return TtsVoiceHelper.INSTANCE.isOpen(this, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE);
    }

    private final boolean recycleLeaveIsOpen() {
        return TtsVoiceHelper.INSTANCE.isOpen(this, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE);
    }

    private final void playLeaveVoiceOrTts() {
        if (this.isStartLeave) {
            this.leaveVoiceTask = bindTtsPlayEvent(new VoiceTask(-1L, VoiceItem.voice34_7), TtsVoiceWrapperPlayer.PlayType.RECYCLE_TABLE_LEAVE, recycleLeaveIsOpen());
        }
    }

    private final void playArriveVoiceOrTts() {
        this.arriveVoiceTask = bindTtsPlayEvent(new VoiceTask(-1L, VoiceItem.voice34_4), TtsVoiceWrapperPlayer.PlayType.RECYCLE_TABLE_ARRIVED, recycleArrivedIsOpen());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TtsVoiceWrapperPlayer.VoiceTaskWrapper bindTtsPlayEvent$default(RecyclingPlatesActivity recyclingPlatesActivity, VoiceTask voiceTask, TtsVoiceWrapperPlayer.PlayType playType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            playType = (TtsVoiceWrapperPlayer.PlayType) null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return recyclingPlatesActivity.bindTtsPlayEvent(voiceTask, playType, z);
    }

    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper bindTtsPlayEvent(VoiceTask voiceTask, TtsVoiceWrapperPlayer.PlayType playType, boolean isOpen) {
        voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$bindTtsPlayEvent$1
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                String str;
                IRecycleVoice iRecycleVoice;
                IRecycleVoice iRecycleVoice2;
                IRecycleVoice iRecycleVoice3;
                Intrinsics.checkParameterIsNotNull(event, "event");
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3275i(str, "bindTtsPlayEvent voiceTask event=" + event.name());
                int i = RecyclingPlatesActivity.WhenMappings.$EnumSwitchMapping$2[event.ordinal()];
                if (i == 1) {
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                } else if (i == 2) {
                    iRecycleVoice2 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice2.onStopVoice();
                } else {
                    if (i != 3) {
                        return;
                    }
                    iRecycleVoice3 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice3.onPlayVoice();
                }
            }
        });
        if (playType == null || !isOpen) {
            VoicePlayer.INSTANCE.play(voiceTask);
            return new TtsVoiceWrapperPlayer.VoiceTaskWrapper(voiceTask, null, null, 6, null);
        }
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(voiceTask, playType, null, 4, null);
        TtsVoiceWrapperPlayer.INSTANCE.play(voiceTaskWrapper, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecyclingPlatesActivity$bindTtsPlayEvent$2
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
                str = RecyclingPlatesActivity.this.TAG;
                Pdlog.m3275i(str, "bindTtsPlayEvent voiceTaskWrapper event=" + it.name());
                int i = RecyclingPlatesActivity.WhenMappings.$EnumSwitchMapping$3[it.ordinal()];
                if (i == 1) {
                    iRecycleVoice = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice.onStopVoice();
                } else if (i == 2) {
                    iRecycleVoice2 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice2.onStopVoice();
                } else {
                    if (i != 3) {
                        return;
                    }
                    iRecycleVoice3 = RecyclingPlatesActivity.this.recycleVoice;
                    iRecycleVoice3.onPlayVoice();
                }
            }
        });
        return voiceTaskWrapper;
    }
}
