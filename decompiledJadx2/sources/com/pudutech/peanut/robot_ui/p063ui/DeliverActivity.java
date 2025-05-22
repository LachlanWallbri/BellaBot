package com.pudutech.peanut.robot_ui.p063ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import androidx.activity.ComponentActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.delivery_task.remote_task.RemoteDeliveryContract;
import com.pudutech.peanut.presenter.delivery_task.remote_task.RemoteDeliveryPresenter;
import com.pudutech.peanut.presenter.performance.MovePerformance;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.SchedulingDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.BeeperCallHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.SimpleMusicPlayerCallbck;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.DeliverArriveLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.DeliverPauseLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.LotteryResultLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.OnTheWayLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoAnimation;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.LotteryUtil;
import com.pudutech.peanut.robot_ui.viewmodel.DeliverVm;
import com.pudutech.robot.module.report.protocol.bean.MovingTaskType;
import com.pudutech.robot.module.voice.data.PlayEvent;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ý\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019*\u0001Z\u0018\u0000 \u0098\u00012\u00020\u00012\u00020\u0002:\u0002\u0098\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010`\u001a\u00020-H\u0002J\b\u0010a\u001a\u00020-H\u0002J\b\u0010b\u001a\u00020-H\u0002J\b\u0010c\u001a\u00020dH\u0002J\b\u0010e\u001a\u00020-H\u0002J\b\u0010f\u001a\u00020-H\u0002J\b\u0010g\u001a\u00020&H\u0002J\u0012\u0010h\u001a\u00020-2\b\u0010i\u001a\u0004\u0018\u00010jH\u0016J1\u0010k\u001a\u00020-2\u0006\u0010l\u001a\u00020\b2\b\u0010m\u001a\u0004\u0018\u00010n2\u0006\u0010o\u001a\u00020&2\b\u0010i\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010pJ\b\u0010q\u001a\u00020-H\u0002J\u0012\u0010r\u001a\u00020-2\b\u0010s\u001a\u0004\u0018\u00010tH\u0014J\b\u0010u\u001a\u00020-H\u0014J\b\u0010v\u001a\u00020-H\u0014J\u001a\u0010w\u001a\u00020-2\u0006\u0010x\u001a\u00020\b2\b\b\u0002\u0010y\u001a\u00020&H\u0002J)\u0010z\u001a\u00020&2\u0006\u0010{\u001a\u00020|2\u0017\u0010}\u001a\u0013\u0012\u0004\u0012\u00020\u007f0~j\t\u0012\u0004\u0012\u00020\u007f`\u0080\u0001H\u0016J\t\u0010\u0081\u0001\u001a\u00020-H\u0014J\t\u0010\u0082\u0001\u001a\u00020-H\u0014J\t\u0010\u0083\u0001\u001a\u00020-H\u0014J\u0012\u0010\u0084\u0001\u001a\u00020-2\u0007\u0010\u0085\u0001\u001a\u00020&H\u0016J\u0012\u0010\u0086\u0001\u001a\u00020-2\u0007\u0010\u0087\u0001\u001a\u00020&H\u0002J\t\u0010\u0088\u0001\u001a\u00020-H\u0002J\t\u0010\u0089\u0001\u001a\u00020-H\u0002J\t\u0010\u008a\u0001\u001a\u00020-H\u0002J\t\u0010\u008b\u0001\u001a\u00020-H\u0002J\t\u0010\u008c\u0001\u001a\u00020-H\u0002J\t\u0010\u008d\u0001\u001a\u00020-H\u0002J\t\u0010\u008e\u0001\u001a\u00020-H\u0002J\u0012\u0010\u008f\u0001\u001a\u00020-2\u0007\u0010\u0090\u0001\u001a\u00020\u0005H\u0002J\t\u0010\u0091\u0001\u001a\u00020-H\u0002J\t\u0010\u0092\u0001\u001a\u00020-H\u0002J\u0012\u0010\u0093\u0001\u001a\u00020-2\u0007\u0010\u0094\u0001\u001a\u00020\bH\u0002J\t\u0010\u0095\u0001\u001a\u00020-H\u0002J\t\u0010\u0096\u0001\u001a\u00020-H\u0002J\t\u0010\u0097\u0001\u001a\u00020-H\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u0019\u001a\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010+\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u0019\u001a\u0004\b4\u00105R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u0010\u0012\u0004\u0012\u00020;\u0012\u0006\u0012\u0004\u0018\u00010-0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010<\u001a\u0010\u0012\u0004\u0012\u00020;\u0012\u0006\u0012\u0004\u0018\u00010-0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020&0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010A\u001a4\u0012\u0013\u0012\u00110&¢\u0006\f\bC\u0012\b\bD\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110&¢\u0006\f\bC\u0012\b\bD\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020-\u0018\u00010BX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010I\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020@X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020LX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010O\u001a\u00020P8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bS\u0010\u0019\u001a\u0004\bQ\u0010RR\u001b\u0010T\u001a\u00020U8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bX\u0010\u0019\u001a\u0004\bV\u0010WR\u0010\u0010Y\u001a\u00020ZX\u0082\u0004¢\u0006\u0004\n\u0002\u0010[R\u000e\u0010\\\u001a\u00020]X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010^\u001a\u0004\u0018\u00010_X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0099\u0001"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/DeliverActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "Lcom/pudutech/peanut/presenter/delivery_task/remote_task/RemoteDeliveryContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "advance", "beeperCallHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "currentEventStatus", "Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm$DeliverStatus;", "currentMode", "currentPauseFeature", "deliverArriverLayout", "Lcom/pudutech/peanut/robot_ui/ui/view/DeliverArriveLayout;", "getDeliverArriverLayout", "()Lcom/pudutech/peanut/robot_ui/ui/view/DeliverArriveLayout;", "deliverArriverLayout$delegate", "Lkotlin/Lazy;", "deliverPauseLayout", "Lcom/pudutech/peanut/robot_ui/ui/view/DeliverPauseLayout;", "getDeliverPauseLayout", "()Lcom/pudutech/peanut/robot_ui/ui/view/DeliverPauseLayout;", "deliverPauseLayout$delegate", "deliverVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm;", "getDeliverVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm;", "deliverVm$delegate", "finishState", "isDeliverActive", "", "isFirstStart", "isLocationError", "isModify", "isRelease", "jumpMethod", "Lkotlin/Function0;", "", "lastEventIsDone", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "locationLostTouchCancelCallback", "lotteryResultLayout", "Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout;", "getLotteryResultLayout", "()Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout;", "lotteryResultLayout$delegate", "mCountDownTimer", "Landroid/os/CountDownTimer;", "onAIVoiceDialogDismiss", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "onAIVoiceDialogShow", "onAiVoiceFinish", "onArrivingLayoutClick", "onDeliverFinishClick", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onLostLocationLostCallback", "onLotteryBtnClick", "onLotteryLayoutHide", "Lcom/pudutech/peanut/robot_ui/ui/view/LotteryResultLayout$OnLayoutHideListener;", "onPauseEvenClick", "Lcom/pudutech/peanut/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "onTheWayLayout", "Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "getOnTheWayLayout", "()Lcom/pudutech/peanut/robot_ui/ui/view/OnTheWayLayout;", "onTheWayLayout$delegate", "remoteDeliveryPresenter", "Lcom/pudutech/peanut/presenter/delivery_task/remote_task/RemoteDeliveryPresenter;", "getRemoteDeliveryPresenter", "()Lcom/pudutech/peanut/presenter/delivery_task/remote_task/RemoteDeliveryPresenter;", "remoteDeliveryPresenter$delegate", "robotSpeedListener", "com/pudutech/peanut/robot_ui/ui/DeliverActivity$robotSpeedListener$1", "Lcom/pudutech/peanut/robot_ui/ui/DeliverActivity$robotSpeedListener$1;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "schedulingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "JumpTarge", "bindPresenter", "genSchedulingDialog", "getOnTheWayAni", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoAnimation;", "hideOnTheWayLayout", "initAiVoice", "isShowDeliverFace", "jumpAndFinish", "i", "Landroid/content/Intent;", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onClickDeliverFinish", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPauseFeatureChange", "type", "isSetPause", "onReceiveRemoteModifyTask", "sortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "allTrays", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "playArrivedFinish", "isAdvance", "playArrivedVoice", "playDeliveryArriveLooperTip", "release", "setReport", "showDeliverArrivedStatus", "showDeliverArrivingStatus", "showDeliverPauseStatus", "showOnTheWayLayout", TypedValues.Attributes.S_TARGET, "showOnTheWayStatus", "startDeliverTask", "startDeliverTaskEditActivity", "mode", "startPauseCountDown", "stopPauseCountDown", "unbindPresenter", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliverActivity extends BatteryBaseActivity implements RemoteDeliveryContract.ViewInterface {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DELIVER_MODE = "DELIVER_MODE";
    public static final int MODE_BIRTHDAY = 1;
    public static final int MODE_CALL_DIRECT = 4;
    public static final int MODE_DIRECT = 2;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_SPECIAL = 3;
    private static int modifyState;
    private HashMap _$_findViewCache;
    private int advance;
    private int currentMode;
    private int finishState;
    private boolean isLocationError;
    private boolean isModify;
    private boolean isRelease;
    private Function0<Unit> jumpMethod;
    private boolean lastEventIsDone;
    private LocationLostDialog locationLostDialog;
    private CountDownTimer mCountDownTimer;
    private SchedulingDialog schedulingDialog;
    private final String TAG = getClass().getSimpleName();
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();

    /* renamed from: remoteDeliveryPresenter$delegate, reason: from kotlin metadata */
    private final Lazy remoteDeliveryPresenter = LazyKt.lazy(new Function0<RemoteDeliveryPresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$remoteDeliveryPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RemoteDeliveryPresenter invoke() {
            RemoteDeliveryPresenter remoteDeliveryPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(RemoteDeliveryPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(RemoteDeliveryPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                remoteDeliveryPresenter = new RemoteDeliveryPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(RemoteDeliveryPresenter.class).toString(), remoteDeliveryPresenter);
            } else {
                if (!(basePresenterInterface instanceof RemoteDeliveryPresenter)) {
                    basePresenterInterface = null;
                }
                remoteDeliveryPresenter = (RemoteDeliveryPresenter) basePresenterInterface;
            }
            if (remoteDeliveryPresenter != null) {
                return remoteDeliveryPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.presenter.delivery_task.remote_task.RemoteDeliveryPresenter");
        }
    });
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();

    /* renamed from: deliverVm$delegate, reason: from kotlin metadata */
    private final Lazy deliverVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(DeliverVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$$special$$inlined$viewModels$1
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
    private final Function0<Boolean> onAiVoiceFinish = new Function0<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onAiVoiceFinish$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            DeliverVm.DeliverStatus deliverStatus;
            deliverStatus = DeliverActivity.this.currentEventStatus;
            if (deliverStatus != DeliverVm.DeliverStatus.Arrive) {
                return true;
            }
            DeliverActivity.this.onClickDeliverFinish();
            return true;
        }
    };
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onErrorDialogShowStatus$1
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
            String str;
            int i;
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "onErrorDialogShowStatus " + z);
            if (z) {
                DeliverActivity deliverActivity = DeliverActivity.this;
                i = deliverActivity.TYPE_PAUSE_FEATURE_ERROR;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
            } else if (z2) {
                DeliverActivity.this.finish();
            }
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onLostLocationLostCallback$1
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
            DeliverVm deliverVm;
            String str2;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            int i2;
            String str3;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            int i3;
            LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker) {
                str3 = DeliverActivity.this.TAG;
                Pdlog.m3273d(str3, "onLostLocationLostCallback");
                locationLostDialog3 = DeliverActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    DeliverActivity deliverActivity = DeliverActivity.this;
                    deliverActivity.locationLostDialog = new LocationLostDialog(deliverActivity);
                }
                locationLostDialog4 = DeliverActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                }
                DeliverActivity deliverActivity2 = DeliverActivity.this;
                i3 = deliverActivity2.TYPE_PAUSE_FEATURE_ERROR;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i3, false, 2, null);
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                DeliverActivity.this.isLocationError = true;
                i = DeliverActivity.this.currentMode;
                if (i == 3) {
                    TableTaskManager.INSTANCE.clearAll();
                }
                final Intent intent = new Intent(DeliverActivity.this, (Class<?>) LaserRunningLocationLostActivity.class);
                DeliverActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onLostLocationLostCallback$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        DeliverActivity.this.jumpAndFinish(intent);
                    }
                };
                deliverVm = DeliverActivity.this.getDeliverVm();
                deliverVm.cancel();
                return;
            }
            if (locateCase == LocateCase.LaserMark) {
                str2 = DeliverActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = DeliverActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    DeliverActivity deliverActivity3 = DeliverActivity.this;
                    deliverActivity3.locationLostDialog = new LocationLostDialog(deliverActivity3, "1");
                }
                locationLostDialog2 = DeliverActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
                DeliverActivity deliverActivity4 = DeliverActivity.this;
                i2 = deliverActivity4.TYPE_PAUSE_FEATURE_ERROR;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity4, i2, false, 2, null);
            }
        }
    };
    private DeliverVm.DeliverStatus currentEventStatus = DeliverVm.DeliverStatus.Moving;
    private boolean isDeliverActive = true;
    private final OnLazyClickListener onDeliverFinishClick = new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onDeliverFinishClick$1
        @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
        public void onSingleClick() {
            String str;
            DeliverArriveLayout deliverArriverLayout;
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "onDeliverFinishClick");
            deliverArriverLayout = DeliverActivity.this.getDeliverArriverLayout();
            if (deliverArriverLayout != null) {
                deliverArriverLayout.stopScaleAnim();
            }
            DeliverActivity.this.finishState = 0;
            DeliverActivity.this.onClickDeliverFinish();
        }
    };
    private final OnLazyClickListener onLotteryBtnClick = new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onLotteryBtnClick$1
        @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
        public void onSingleClick() {
            String str;
            LotteryResultLayout lotteryResultLayout;
            int calculationResult = LotteryUtil.calculationResult();
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "showLotteryResult " + calculationResult);
            lotteryResultLayout = DeliverActivity.this.getLotteryResultLayout();
            lotteryResultLayout.showLotteryResult(calculationResult > -1, calculationResult);
        }
    };
    private final LotteryResultLayout.OnLayoutHideListener onLotteryLayoutHide = new LotteryResultLayout.OnLayoutHideListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onLotteryLayoutHide$1
        @Override // com.pudutech.peanut.robot_ui.ui.view.LotteryResultLayout.OnLayoutHideListener
        public void onLayoutHide() {
            String str;
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "onLotteryResultLayoutHide");
            DeliverActivity.this.onClickDeliverFinish();
        }
    };
    private final DeliverPauseLayout.OnEvenClickListener onPauseEvenClick = new DeliverActivity$onPauseEvenClick$1(this);
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onFaceAnimationViewClick$1
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
            DeliverVm.DeliverStatus deliverStatus;
            DeliverVm.DeliverStatus deliverStatus2;
            DeliverVm deliverVm;
            DeliverVm.DeliverStatus deliverStatus3;
            str = DeliverActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            deliverStatus = DeliverActivity.this.currentEventStatus;
            sb.append(deliverStatus);
            Pdlog.m3273d(str, sb.toString());
            deliverStatus2 = DeliverActivity.this.currentEventStatus;
            if (deliverStatus2 != DeliverVm.DeliverStatus.Moving) {
                deliverStatus3 = DeliverActivity.this.currentEventStatus;
                if (deliverStatus3 != DeliverVm.DeliverStatus.Approaching) {
                    return;
                }
            }
            deliverVm = DeliverActivity.this.getDeliverVm();
            deliverVm.pause();
        }
    });
    private final Function0<Unit> onArrivingLayoutClick = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onArrivingLayoutClick$1
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
            DeliverVm.DeliverStatus deliverStatus;
            DeliverVm.DeliverStatus deliverStatus2;
            DeliverVm deliverVm;
            str = DeliverActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onArrivingLayoutClick current status = ");
            deliverStatus = DeliverActivity.this.currentEventStatus;
            sb.append(deliverStatus);
            Pdlog.m3273d(str, sb.toString());
            deliverStatus2 = DeliverActivity.this.currentEventStatus;
            if (deliverStatus2 == DeliverVm.DeliverStatus.Approaching) {
                deliverVm = DeliverActivity.this.getDeliverVm();
                deliverVm.pause();
            }
        }
    };

    /* renamed from: deliverArriverLayout$delegate, reason: from kotlin metadata */
    private final Lazy deliverArriverLayout = LazyKt.lazy(new Function0<DeliverArriveLayout>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$deliverArriverLayout$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeliverArriveLayout invoke() {
            OnLazyClickListener onLazyClickListener;
            Function0<Unit> function0;
            int i;
            OnLazyClickListener onLazyClickListener2;
            View inflate = ((ViewStub) DeliverActivity.this.findViewById(C5508R.id.arrive_layout_stub)).inflate();
            if (inflate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.view.DeliverArriveLayout");
            }
            DeliverArriveLayout deliverArriveLayout = (DeliverArriveLayout) inflate;
            if (SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false)) {
                onLazyClickListener2 = DeliverActivity.this.onLotteryBtnClick;
                deliverArriveLayout.setOnClickFinishListener(onLazyClickListener2);
            } else {
                onLazyClickListener = DeliverActivity.this.onDeliverFinishClick;
                deliverArriveLayout.setOnClickFinishListener(onLazyClickListener);
            }
            function0 = DeliverActivity.this.onArrivingLayoutClick;
            deliverArriveLayout.setOnArrivingLayoutClick(function0);
            i = DeliverActivity.this.currentMode;
            if (i == 1) {
                deliverArriveLayout.switchTheme(2);
            } else {
                deliverArriveLayout.switchTheme(0);
            }
            return deliverArriveLayout;
        }
    });

    /* renamed from: deliverPauseLayout$delegate, reason: from kotlin metadata */
    private final Lazy deliverPauseLayout = LazyKt.lazy(new Function0<DeliverPauseLayout>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$deliverPauseLayout$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeliverPauseLayout invoke() {
            DeliverPauseLayout.OnEvenClickListener onEvenClickListener;
            int i;
            int i2;
            int i3;
            View inflate = ((ViewStub) DeliverActivity.this.findViewById(C5508R.id.pause_layout_stub)).inflate();
            if (inflate != null) {
                DeliverPauseLayout deliverPauseLayout = (DeliverPauseLayout) inflate;
                onEvenClickListener = DeliverActivity.this.onPauseEvenClick;
                deliverPauseLayout.setOnEvenClickListener(onEvenClickListener);
                i = DeliverActivity.this.currentMode;
                if (i != 1) {
                    i2 = DeliverActivity.this.currentMode;
                    if (i2 != 2) {
                        i3 = DeliverActivity.this.currentMode;
                        if (i3 != 4) {
                            deliverPauseLayout.switchTheme(0);
                        }
                    }
                    deliverPauseLayout.switchTheme(1);
                } else {
                    deliverPauseLayout.switchTheme(2);
                }
                return deliverPauseLayout;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.view.DeliverPauseLayout");
        }
    });

    /* renamed from: onTheWayLayout$delegate, reason: from kotlin metadata */
    private final Lazy onTheWayLayout = LazyKt.lazy(new DeliverActivity$onTheWayLayout$2(this));

    /* renamed from: lotteryResultLayout$delegate, reason: from kotlin metadata */
    private final Lazy lotteryResultLayout = LazyKt.lazy(new Function0<LotteryResultLayout>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$lotteryResultLayout$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LotteryResultLayout invoke() {
            LotteryResultLayout.OnLayoutHideListener onLayoutHideListener;
            View inflate = ((ViewStub) DeliverActivity.this.findViewById(C5508R.id.lottery_result_layout)).inflate();
            if (inflate != null) {
                LotteryResultLayout lotteryResultLayout = (LotteryResultLayout) inflate;
                onLayoutHideListener = DeliverActivity.this.onLotteryLayoutHide;
                lotteryResultLayout.setHideListener(onLayoutHideListener);
                return lotteryResultLayout;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.view.LotteryResultLayout");
        }
    });
    private boolean isFirstStart = true;
    private final Function0<Unit> locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$locationLostTouchCancelCallback$1
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
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "locationLostTouchCancelCallback");
            ((FaceVideoView) DeliverActivity.this._$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            DeliverActivity deliverActivity = DeliverActivity.this;
            i = deliverActivity.TYPE_PAUSE_FEATURE_ERROR;
            DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogDismiss = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onAIVoiceDialogDismiss$1
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
            boolean z;
            DeliverVm.DeliverStatus deliverStatus;
            boolean z2;
            String str2;
            int i;
            String str3;
            int i2;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = DeliverActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogDismiss  ");
            z = DeliverActivity.this.isDeliverActive;
            sb.append(z);
            Pdlog.m3273d(str, sb.toString());
            deliverStatus = DeliverActivity.this.currentEventStatus;
            if (deliverStatus == DeliverVm.DeliverStatus.Arrive) {
                str3 = DeliverActivity.this.TAG;
                Pdlog.m3273d(str3, "onDialogDismiss is ARRIVAL");
                DeliverActivity.this.playDeliveryArriveLooperTip();
                DeliverActivity deliverActivity = DeliverActivity.this;
                i2 = deliverActivity.TYPE_PAUSE_FEATURE_NORMAL;
                deliverActivity.onPauseFeatureChange(i2, false);
                return;
            }
            z2 = DeliverActivity.this.isDeliverActive;
            if (z2) {
                return;
            }
            str2 = DeliverActivity.this.TAG;
            Pdlog.m3273d(str2, "onDialogDismiss is not ARRIVAL");
            DeliverActivity deliverActivity2 = DeliverActivity.this;
            i = deliverActivity2.TYPE_PAUSE_FEATURE_NORMAL;
            DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i, false, 2, null);
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogShow = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onAIVoiceDialogShow$1
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
            boolean z;
            DeliverVm.DeliverStatus deliverStatus;
            boolean z2;
            String str2;
            int i;
            String str3;
            int i2;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = DeliverActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDialogShow  ");
            z = DeliverActivity.this.isDeliverActive;
            sb.append(z);
            Pdlog.m3273d(str, sb.toString());
            deliverStatus = DeliverActivity.this.currentEventStatus;
            if (deliverStatus == DeliverVm.DeliverStatus.Arrive) {
                str3 = DeliverActivity.this.TAG;
                Pdlog.m3273d(str3, "onDialogShow is ARRIVAL");
                VoicePlayTasks.INSTANCE.stop();
                DeliverActivity deliverActivity = DeliverActivity.this;
                i2 = deliverActivity.TYPE_PAUSE_FEATURE_AIVOICE;
                deliverActivity.onPauseFeatureChange(i2, false);
                return;
            }
            z2 = DeliverActivity.this.isDeliverActive;
            if (z2) {
                return;
            }
            str2 = DeliverActivity.this.TAG;
            Pdlog.m3273d(str2, "onDialogShow is not ARRIVAL");
            DeliverActivity deliverActivity2 = DeliverActivity.this;
            i = deliverActivity2.TYPE_PAUSE_FEATURE_AIVOICE;
            DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i, false, 2, null);
        }
    };
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_AIVOICE = 2;
    private final int TYPE_PAUSE_FEATURE_TOUCH = 3;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private int currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
    private final DeliverActivity$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            String str;
            DeliverVm.DeliverStatus deliverStatus;
            DeliverVm deliverVm;
            str = DeliverActivity.this.TAG;
            Pdlog.m3273d(str, "robotSpeedListener onMove left = " + left + " , right = " + right);
            if (Math.abs(left) > 0.05d || Math.abs(right) > 0.05d) {
                deliverStatus = DeliverActivity.this.currentEventStatus;
                if (deliverStatus == DeliverVm.DeliverStatus.Pause) {
                    deliverVm = DeliverActivity.this.getDeliverVm();
                    if ((deliverVm != null ? Boolean.valueOf(deliverVm.isNotErrorMove()) : null).booleanValue()) {
                        DeliverActivity.this.startPauseCountDown();
                    }
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final DeliverArriveLayout getDeliverArriverLayout() {
        return (DeliverArriveLayout) this.deliverArriverLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeliverPauseLayout getDeliverPauseLayout() {
        return (DeliverPauseLayout) this.deliverPauseLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeliverVm getDeliverVm() {
        return (DeliverVm) this.deliverVm.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LotteryResultLayout getLotteryResultLayout() {
        return (LotteryResultLayout) this.lotteryResultLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnTheWayLayout getOnTheWayLayout() {
        return (OnTheWayLayout) this.onTheWayLayout.getValue();
    }

    private final RemoteDeliveryPresenter getRemoteDeliveryPresenter() {
        return (RemoteDeliveryPresenter) this.remoteDeliveryPresenter.getValue();
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

    /* JADX WARN: Type inference failed for: r0v23, types: [com.pudutech.peanut.robot_ui.ui.DeliverActivity$robotSpeedListener$1] */
    public DeliverActivity() {
    }

    /* compiled from: DeliverActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/DeliverActivity$Companion;", "", "()V", "DELIVER_MODE", "", "MODE_BIRTHDAY", "", "MODE_CALL_DIRECT", "MODE_DIRECT", "MODE_NORMAL", "MODE_SPECIAL", "modifyState", "getModifyState", "()I", "setModifyState", "(I)V", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getModifyState() {
            return DeliverActivity.modifyState;
        }

        public final void setModifyState(int i) {
            DeliverActivity.modifyState = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClickDeliverFinish() {
        if (this.isRelease) {
            Pdlog.m3274e(this.TAG, "goToCruise failed isRelease ");
        } else if (Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch()) {
            playArrivedFinish(true);
        } else {
            getDeliverVm().active();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C5508R.layout.activity_deliver);
        this.currentMode = getIntent().getIntExtra("DELIVER_MODE", 0);
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
        if (!TableTaskManager.INSTANCE.getHasTask()) {
            Pdlog.m3274e(this.TAG, "checkTableTask failed");
            startDeliverTaskEditActivity(0);
            return;
        }
        setReport();
        initAiVoice();
        bindPresenter();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        startDeliverTask();
    }

    private final void setReport() {
        int i = this.currentMode;
        if (i == 0) {
            getDeliverVm().setReportType(MovingTaskType.Delivery);
            return;
        }
        if (i == 1) {
            getDeliverVm().setReportType(MovingTaskType.BIRTHDAY);
            return;
        }
        if (i == 2) {
            getDeliverVm().setReportType(MovingTaskType.DIRECT);
        } else if (i == 3) {
            getDeliverVm().setReportType(MovingTaskType.SPECIAL);
        } else {
            if (i != 4) {
                return;
            }
            getDeliverVm().setReportType(MovingTaskType.CALL_DIRECT);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void onPauseFeatureChange$default(DeliverActivity deliverActivity, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        deliverActivity.onPauseFeatureChange(i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type, boolean isSetPause) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            DeliverPauseLayout deliverPauseLayout = getDeliverPauseLayout();
            if (deliverPauseLayout != null) {
                deliverPauseLayout.showCountdownLayout();
            }
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
            getDeliverPauseLayout().hideCountdownLayout();
        }
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
    }

    private final void startDeliverTask() {
        Pdlog.m3273d(this.TAG, "startDeliverTask");
        getDeliverVm().initTask(TableTaskManager.INSTANCE.getAllTask(), TableTaskManager.INSTANCE.getSortType());
        DeliverActivity deliverActivity = this;
        getDeliverVm().getDeliverStatusState().observe(deliverActivity, new Observer<DeliverVm.DeliverStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$startDeliverTask$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(DeliverVm.DeliverStatus deliverStatus) {
                String str;
                DeliverVm.DeliverStatus deliverStatus2;
                LocationLostDialog locationLostDialog;
                DeliverVm.DeliverStatus deliverStatus3;
                int i;
                Function0 function0;
                Function0 function02;
                DeliverVm.DeliverStatus deliverStatus4;
                boolean isShowDeliverFace;
                OnTheWayLayout onTheWayLayout;
                DeliverVm deliverVm;
                String str2;
                String str3;
                Function0 function03;
                int i2;
                boolean z;
                boolean z2;
                int i3;
                int i4;
                Function0 function04;
                Function0 function05;
                Function0 function06;
                Function0 function07;
                Function0 function08;
                Function0 function09;
                Function0 function010;
                Function0 function011;
                int i5;
                int i6;
                int i7;
                int i8;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "startDeliverTask " + deliverStatus);
                deliverStatus2 = DeliverActivity.this.currentEventStatus;
                if (deliverStatus2 == DeliverVm.DeliverStatus.FinishOne) {
                    DeliverActivity.this.lastEventIsDone = true;
                }
                if (deliverStatus != null) {
                    switch (deliverStatus) {
                        case Pause:
                            locationLostDialog = DeliverActivity.this.locationLostDialog;
                            if (locationLostDialog != null) {
                                locationLostDialog.dismiss();
                            }
                            deliverStatus3 = DeliverActivity.this.currentEventStatus;
                            if (deliverStatus3 != deliverStatus) {
                                DeliverActivity.this.showDeliverPauseStatus();
                                break;
                            }
                            break;
                        case Cancel:
                            i = DeliverActivity.this.advance;
                            if (i != 1) {
                                TableTaskManager.INSTANCE.cancelAllTask();
                                function0 = DeliverActivity.this.jumpMethod;
                                if (function0 != null) {
                                    function02 = DeliverActivity.this.jumpMethod;
                                    if (function02 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    function02.invoke();
                                    break;
                                } else {
                                    DeliverActivity.this.JumpTarge();
                                    break;
                                }
                            }
                            break;
                        case Moving:
                            deliverStatus4 = DeliverActivity.this.currentEventStatus;
                            if (deliverStatus4 != deliverStatus) {
                                DeliverActivity.this.showOnTheWayStatus();
                                DeliverActivity.this.stopPauseCountDown();
                            }
                            isShowDeliverFace = DeliverActivity.this.isShowDeliverFace();
                            if (!isShowDeliverFace) {
                                DeliverActivity.this.hideErrorTipDialog();
                                onTheWayLayout = DeliverActivity.this.getOnTheWayLayout();
                                deliverVm = DeliverActivity.this.getDeliverVm();
                                Destination value = deliverVm.getCurrentDes().getValue();
                                if (value == null || (str2 = value.getName()) == null) {
                                    str2 = "";
                                }
                                onTheWayLayout.setTarget(str2);
                                break;
                            }
                            break;
                        case AllTaskFinish:
                            str3 = DeliverActivity.this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("jumpMethod ");
                            function03 = DeliverActivity.this.jumpMethod;
                            sb.append(function03);
                            Pdlog.m3273d(str3, sb.toString());
                            i2 = DeliverActivity.this.finishState;
                            if (i2 != 1) {
                                z = DeliverActivity.this.isLocationError;
                                if (z) {
                                    function06 = DeliverActivity.this.jumpMethod;
                                    if (function06 != null) {
                                        function07 = DeliverActivity.this.jumpMethod;
                                        if (function07 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        function07.invoke();
                                        return;
                                    }
                                    DeliverActivity.this.JumpTarge();
                                    return;
                                }
                                z2 = DeliverActivity.this.isModify;
                                if (z2) {
                                    function04 = DeliverActivity.this.jumpMethod;
                                    if (function04 != null) {
                                        function05 = DeliverActivity.this.jumpMethod;
                                        if (function05 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        function05.invoke();
                                        return;
                                    }
                                }
                                i3 = DeliverActivity.this.currentMode;
                                if (i3 != 2) {
                                    i4 = DeliverActivity.this.currentMode;
                                    if (i4 != 4) {
                                        Intent intent = new Intent(DeliverActivity.this, (Class<?>) TurnBackActivity.class);
                                        intent.putExtra("SHOW_THANKS", !Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch());
                                        intent.putExtra(TurnBackActivity.ROBOT_TYPE, 1);
                                        DeliverActivity.this.jumpAndFinish(intent);
                                        break;
                                    }
                                }
                                DeliverActivity.this.JumpTarge();
                                return;
                            }
                            TableTaskManager.INSTANCE.cancelAllTask();
                            function08 = DeliverActivity.this.jumpMethod;
                            if (function08 != null) {
                                function09 = DeliverActivity.this.jumpMethod;
                                if (function09 == null) {
                                    Intrinsics.throwNpe();
                                }
                                function09.invoke();
                                return;
                            }
                            DeliverActivity.this.JumpTarge();
                            return;
                        case AllLeftCancel:
                            function010 = DeliverActivity.this.jumpMethod;
                            if (function010 != null) {
                                function011 = DeliverActivity.this.jumpMethod;
                                if (function011 == null) {
                                    Intrinsics.throwNpe();
                                }
                                function011.invoke();
                                break;
                            } else {
                                TableTaskManager.INSTANCE.cancelAllTask();
                                DeliverActivity.this.startDeliverTaskEditActivity(0);
                                break;
                            }
                        case Arrive:
                            DeliverActivity.this.stopPauseCountDown();
                            i5 = DeliverActivity.this.currentMode;
                            if (i5 != 2) {
                                i6 = DeliverActivity.this.currentMode;
                                if (i6 != 4) {
                                    DeliverActivity.this.showDeliverArrivedStatus();
                                    break;
                                }
                            }
                            break;
                        case FinishOne:
                            DeliverActivity.this.stopPauseCountDown();
                            break;
                        case Approaching:
                            i7 = DeliverActivity.this.currentMode;
                            if (i7 != 2) {
                                i8 = DeliverActivity.this.currentMode;
                                if (i8 != 4) {
                                    DeliverActivity.this.showDeliverArrivingStatus();
                                    break;
                                }
                            }
                            break;
                    }
                }
                DeliverActivity.this.currentEventStatus = deliverStatus;
            }
        });
        getDeliverVm().getMoveErrorHelperLiveData().observe(deliverActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$startDeliverTask$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                RunningErrorHelper runningErrorHelper;
                if (moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = DeliverActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        getDeliverVm().getOnSchedulingLiveData().observe(deliverActivity, new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$startDeliverTask$3
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
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onSchedulingLiveData " + it);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.booleanValue()) {
                    DeliverActivity.this.genSchedulingDialog();
                    schedulingDialog3 = DeliverActivity.this.schedulingDialog;
                    if (schedulingDialog3 != null) {
                        schedulingDialog3.show();
                        return;
                    }
                    return;
                }
                schedulingDialog = DeliverActivity.this.schedulingDialog;
                if (schedulingDialog == null || !schedulingDialog.isShowing() || schedulingDialog2 == null) {
                    return;
                }
                schedulingDialog2.dismiss();
            }
        });
        getDeliverVm().getFaceLiveData().observe(deliverActivity, new Observer<FaceVideoAnimation>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$startDeliverTask$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(FaceVideoAnimation it) {
                FaceVideoView faceVideoView = (FaceVideoView) DeliverActivity.this._$_findCachedViewById(C5508R.id.face_animation_view);
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                faceVideoView.playAnimation(it);
            }
        });
        showOnTheWayStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void JumpTarge() {
        if (this.currentMode == 1) {
            jumpAndFinish(new Intent(this, (Class<?>) HomeActivity.class));
        } else {
            startDeliverTaskEditActivity(0);
        }
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
        MusicPlayerHelper.getInstance().release();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
        unbindPresenter();
        stopPauseCountDown();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$bindPresenter$1
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
                DeliverVm deliverVm;
                deliverVm = DeliverActivity.this.getDeliverVm();
                deliverVm.active();
            }
        });
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 4, null);
        this.beeperCallHelper.setOnCancelCallListener(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$bindPresenter$2
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
                String str;
                DeliverVm deliverVm;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "bindPresenter : beeperCallHelper onCancelCallListener");
                deliverVm = DeliverActivity.this.getDeliverVm();
                deliverVm.cancel();
                DeliverActivity.this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$bindPresenter$2.1
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
                        DeliverActivity.this.jumpAndFinish(new Intent(DeliverActivity.this, (Class<?>) TurnBackActivity.class));
                    }
                };
            }
        });
        isShowDeliverFace();
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new SimpleMusicPlayerCallbck() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$bindPresenter$3
            @Override // com.pudutech.peanut.robot_ui.p063ui.helper.SimpleMusicPlayerCallbck, com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                String str;
                super.onPrepared();
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "setMusicPlayerStateCallback onPrepared");
            }
        });
        getRemoteDeliveryPresenter().replaceView(this);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionAccomplished(this.onAiVoiceFinish);
        RobotMoveManager.INSTANCE.addRobotSpeedListener(this.robotSpeedListener);
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        this.runningErrorHelper.unbind();
        this.beeperCallHelper.unbind();
        getRemoteDeliveryPresenter().removeView(this);
        RobotMoveManager.INSTANCE.removeRobotSpeedListener(this.robotSpeedListener);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionAccomplished(this.onAiVoiceFinish);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnTheWayStatus() {
        String str;
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        hideErrorTipDialog();
        LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.FORWARD);
        Destination value = getDeliverVm().getCurrentDes().getValue();
        if (value == null || (str = value.getName()) == null) {
            str = "";
        }
        showOnTheWayLayout(str);
        this.isDeliverActive = true;
        int i = this.currentMode;
        if (i != 3) {
            if (i == 1) {
                MusicPlayerHelper.getInstance().startPlay(ModeEnum.BIRTHDAY);
                return;
            } else {
                MusicPlayerHelper.getInstance().startPlay(ModeEnum.DELIVERY_AND_RETURNING);
                return;
            }
        }
        String str2 = SpUtils.get(this, "key_spaces_mode_select_music", "");
        String str3 = str2;
        if (!(str3 == null || StringsKt.isBlank(str3))) {
            Music music = new Music();
            music.setPath(str2);
            Pdlog.m3273d(this.TAG, "MODE_SPECIAL music is " + str2);
            MusicPlayerHelper.getInstance().playSinleSong(music);
            return;
        }
        Pdlog.m3274e(this.TAG, "MODE_SPECIAL music is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isShowDeliverFace() {
        return Constans.INSTANCE.getDeliverFaceSwitch() && this.currentMode != 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeliverPauseStatus() {
        String str;
        Pdlog.m3273d(this.TAG, "showDeliverPauseStatus");
        this.isDeliverActive = false;
        getDeliverPauseLayout().showView();
        DeliverPauseLayout deliverPauseLayout = getDeliverPauseLayout();
        ArrayList<TrayModel> trays = getDeliverVm().getTrays();
        Destination value = getDeliverVm().getCurrentDes().getValue();
        if (value == null || (str = value.getName()) == null) {
            str = "";
        }
        deliverPauseLayout.setTrayInfo(trays, str);
        hideOnTheWayLayout();
        getDeliverArriverLayout().hideView();
        if (this.runningErrorHelper.isErrorShowing()) {
            getDeliverPauseLayout().hideCountdownLayout();
        }
        VoicePlayTasks.INSTANCE.stop();
        DeliverVm deliverVm = getDeliverVm();
        if ((deliverVm != null ? Boolean.valueOf(deliverVm.isNotErrorMove()) : null).booleanValue()) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.STOP);
        }
        MusicPlayerHelper.getInstance().pausePlay();
        startPauseCountDown();
    }

    private final void showOnTheWayLayout(String target) {
        if (this.lastEventIsDone && !Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch()) {
            playArrivedFinish(false);
            this.lastEventIsDone = false;
        } else {
            if (isShowDeliverFace()) {
                ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(getOnTheWayAni());
                return;
            }
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            hideErrorTipDialog();
            getOnTheWayLayout().showLayout(target);
        }
    }

    private final void hideOnTheWayLayout() {
        if (isShowDeliverFace()) {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
        } else {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
            getOnTheWayLayout().hideLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeliverArrivingStatus() {
        String str;
        Pdlog.m3273d(this.TAG, "showDeliverArrivingStatus");
        hideOnTheWayLayout();
        getDeliverPauseLayout().hideView();
        DeliverArriveLayout deliverArriverLayout = getDeliverArriverLayout();
        ArrayList<TrayModel> trays = getDeliverVm().getTrays();
        Destination value = getDeliverVm().getCurrentDes().getValue();
        if (value == null || (str = value.getName()) == null) {
            str = "";
        }
        deliverArriverLayout.showArrivingLayout(trays, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeliverArrivedStatus() {
        String str;
        String str2;
        String str3;
        Pdlog.m3273d(this.TAG, "showDeliverArrivedStatus");
        hideOnTheWayLayout();
        TableTaskManager tableTaskManager = TableTaskManager.INSTANCE;
        Destination value = getDeliverVm().getCurrentDes().getValue();
        if (value == null || (str = value.getName()) == null) {
            str = "";
        }
        tableTaskManager.setFinishTask(str);
        getDeliverPauseLayout().hideView();
        DeliverArriveLayout deliverArriverLayout = getDeliverArriverLayout();
        ArrayList<TrayModel> trays = getDeliverVm().getTrays();
        Destination value2 = getDeliverVm().getCurrentDes().getValue();
        if (value2 == null || (str2 = value2.getName()) == null) {
            str2 = "";
        }
        deliverArriverLayout.showArrivedLayout(trays, str2);
        LotteryResultLayout lotteryResultLayout = getLotteryResultLayout();
        Destination value3 = getDeliverVm().getCurrentDes().getValue();
        if (value3 == null || (str3 = value3.getName()) == null) {
            str3 = "";
        }
        lotteryResultLayout.setDestination(str3);
        playArrivedVoice();
        LightPlayManager.INSTANCE.playArrive();
        if (this.currentMode == 3) {
            String str4 = SpUtils.get(this, "key_spaces_mode_select_music", "");
            String str5 = str4;
            if (!(str5 == null || StringsKt.isBlank(str5))) {
                Music music = new Music();
                music.setPath(str4);
                Pdlog.m3273d(this.TAG, "MODE_SPECIAL music is " + str4);
                MusicPlayerHelper.getInstance().playSinleSong(music);
                return;
            }
            Pdlog.m3274e(this.TAG, "MODE_SPECIAL music is null");
            return;
        }
        MusicPlayerHelper.getInstance().startPlay(this.currentMode == 1 ? ModeEnum.BIRTHDAY : ModeEnum.ARRIVED);
    }

    private final void playArrivedVoice() {
        String name;
        String str = "";
        String str2 = SpUtils.get(this, "key_spaces_mode_select_arrive_voice", "");
        if (this.currentMode == 3) {
            String str3 = str2;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                try {
                    Integer.parseInt(str2);
                    playDeliveryArriveLooperTip();
                    return;
                } catch (Exception e) {
                    Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
                }
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (IndexedValue indexedValue : CollectionsKt.withIndex(getDeliverVm().getTrays())) {
            DeliveryModel current = ((TrayModel) indexedValue.getValue()).getCurrent();
            if ((current != null ? current.getStatus() : null) == DeliveryModel.TaskStatus.ARRIVE) {
                arrayList.add(Integer.valueOf(indexedValue.getIndex()));
            }
        }
        VoicePlayTasks voicePlayTasks = VoicePlayTasks.INSTANCE;
        Destination value = getDeliverVm().getCurrentDes().getValue();
        if (value != null && (name = value.getName()) != null) {
            str = name;
        }
        voicePlayTasks.playDeliveryArrive(str, arrayList);
        playDeliveryArriveLooperTip();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playDeliveryArriveLooperTip() {
        if (this.currentMode != 1) {
            VoicePlayTasks.INSTANCE.playDeliveryArriveLooperTip();
        }
    }

    private final FaceVideoAnimation getOnTheWayAni() {
        if (this.currentMode == 1) {
            return SceneAnimationResources.INSTANCE.getDeliver();
        }
        return SceneAnimationResources.INSTANCE.getDeliver();
    }

    private final void playArrivedFinish(final boolean isAdvance) {
        String str;
        if (isShowDeliverFace() || isAdvance) {
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getThanks());
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(getOnTheWayAni());
        } else {
            hideErrorTipDialog();
            ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getThanks());
            OnTheWayLayout onTheWayLayout = getOnTheWayLayout();
            Destination value = getDeliverVm().getCurrentDes().getValue();
            if (value == null || (str = value.getName()) == null) {
                str = "";
            }
            onTheWayLayout.showLayout(str);
        }
        VoicePlayTasks.INSTANCE.playDeliveryFinish(new Function3<PlayEvent, String, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$playArrivedFinish$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent, String str2, Boolean bool) {
                invoke2(playEvent, str2, bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PlayEvent it, String str2, Boolean bool) {
                String str3;
                DeliverVm deliverVm;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (isAdvance) {
                    if (it == PlayEvent.Finish || it == PlayEvent.Stop) {
                        str3 = DeliverActivity.this.TAG;
                        Pdlog.m3273d(str3, "playArrivedFinish voice finish");
                        deliverVm = DeliverActivity.this.getDeliverVm();
                        deliverVm.active();
                    }
                }
            }
        });
        LightPlayManager.INSTANCE.playThanks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDeliverTaskEditActivity(int mode) {
        Pdlog.m3273d(this.TAG, "startDeliverTaskEditActivity " + mode);
        Intent intent = new Intent(this, (Class<?>) DeliverTaskEditActivity.class);
        intent.putExtra("MODE_TYPE", mode);
        jumpAndFinish(intent);
    }

    @Override // com.pudutech.peanut.presenter.delivery_task.remote_task.RemoteDeliveryContract.ViewInterface
    public boolean onReceiveRemoteModifyTask(MoveSortType sortType, ArrayList<TrayModel> allTrays) {
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Intrinsics.checkParameterIsNotNull(allTrays, "allTrays");
        Pdlog.m3273d(this.TAG, "onReceiveRemoteModifyTask : sortType = " + sortType + "; allTrays = " + allTrays + "; ");
        if (this.currentEventStatus != DeliverVm.DeliverStatus.Moving) {
            return false;
        }
        Iterator<T> it = allTrays.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (((TrayModel) it.next()).getAllDestinations().size() > 0) {
                z = false;
            }
        }
        if (z) {
            Pdlog.m3273d(this.TAG, "onReceiveRemoteModifyTask : clearAll");
            TableTaskManager.INSTANCE.cancelAllTask();
            getDeliverVm().cancel();
            this.jumpMethod = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$onReceiveRemoteModifyTask$2
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
                    DeliverActivity deliverActivity = DeliverActivity.this;
                    deliverActivity.jumpAndFinish(new Intent(deliverActivity, (Class<?>) TurnBackActivity.class));
                }
            };
        } else {
            TableTaskManager.setAllTask$default(TableTaskManager.INSTANCE, allTrays, sortType, MovePerformance.NORMAL, false, false, 24, null);
            startDeliverTask();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        AiVoiceTriggerHelper.INSTANCE.setOnMissionAccomplished((Function0) null);
        if (getDeliverPauseLayout() != null) {
            getDeliverPauseLayout().removeAnimation();
        }
        if (getDeliverArriverLayout() != null) {
            getDeliverArriverLayout().removeAnimation();
        }
        SchedulingDialog schedulingDialog = this.schedulingDialog;
        if (schedulingDialog != null) {
            schedulingDialog.dismiss();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state != 2) {
            if (state == 4 && isCharging) {
                getDeliverVm().cancel();
                return;
            }
            return;
        }
        if (i != null) {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
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
        this.isModify = false;
        modifyState = 0;
        this.finishState = 0;
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPauseCountDown() {
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mCountDownTimer = (CountDownTimer) null;
        }
        if (!getDeliverVm().isNotErrorMove()) {
            DeliverPauseLayout deliverPauseLayout = getDeliverPauseLayout();
            if (deliverPauseLayout != null) {
                deliverPauseLayout.hideCountdownLayout();
                return;
            }
            return;
        }
        if (getDeliverPauseLayout().getVisibility() != 0 || getDeliverPauseLayout().countDownIsShow()) {
            final long notCruisePauseKeepTime_ms = BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms();
            final long j = 1000;
            this.mCountDownTimer = new CountDownTimer(notCruisePauseKeepTime_ms, j) { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$startPauseCountDown$1
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    DeliverVm deliverVm;
                    deliverVm = DeliverActivity.this.getDeliverVm();
                    deliverVm.active();
                }

                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                    String str;
                    DeliverPauseLayout deliverPauseLayout2;
                    DeliverPauseLayout deliverPauseLayout3;
                    str = DeliverActivity.this.TAG;
                    Pdlog.m3273d(str, "showTimeLeft " + millisUntilFinished);
                    long j2 = (long) 1000;
                    long j3 = millisUntilFinished / j2;
                    if (j3 == BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / j2) {
                        deliverPauseLayout3 = DeliverActivity.this.getDeliverPauseLayout();
                        deliverPauseLayout3.setCountdown(j3);
                    } else {
                        deliverPauseLayout2 = DeliverActivity.this.getDeliverPauseLayout();
                        deliverPauseLayout2.setCountdown(j3 + 1);
                    }
                }
            };
            CountDownTimer countDownTimer2 = this.mCountDownTimer;
            if (countDownTimer2 != null) {
                countDownTimer2.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPauseCountDown() {
        DeliverPauseLayout deliverPauseLayout = getDeliverPauseLayout();
        if (deliverPauseLayout != null) {
            deliverPauseLayout.hideCountdownLayout();
        }
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
            schedulingDialog.setOnRootLayoutClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverActivity$genSchedulingDialog$1
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
                    DeliverVm deliverVm;
                    SchedulingDialog schedulingDialog3;
                    schedulingDialog2 = DeliverActivity.this.schedulingDialog;
                    if (schedulingDialog2 != null && schedulingDialog2.isShowing() && schedulingDialog3 != null) {
                        schedulingDialog3.dismiss();
                    }
                    deliverVm = DeliverActivity.this.getDeliverVm();
                    deliverVm.pause();
                }
            });
        }
    }
}
