package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorContract;
import com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorPresenter;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.GeneralVoiceProperty;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.advertise.AdSceneConfig;
import com.pudutech.bumblebee.robot_ui.advertise.AdVm;
import com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment;
import com.pudutech.bumblebee.robot_ui.advertise.AdvertiseVm;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.config.RobotInfo;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.PosterDisplayDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LockMachineHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.task.PeripheralsTrack;
import com.pudutech.bumblebee.robot_ui.track.task.VoiceInteractionTrack;
import com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM;
import com.pudutech.bumblebee.robot_ui.viewmodel.GreeterFaceVM;
import com.pudutech.bumblebee.robot_ui.widget.BubbleBarrage;
import com.pudutech.bumblebee.robot_ui.widget.LineWaveVoiceView;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.robot.module.report.track2.WakeUpType;
import com.pudutech.voiceinteraction.component.cmd.CmdBean;
import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: GreeterFaceActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0002O`\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010q\u001a\u00020\u001aH\u0002J\b\u0010r\u001a\u00020\u001aH\u0016J\b\u0010s\u001a\u00020\u001aH\u0002J\b\u0010t\u001a\u00020\u001aH\u0002J\b\u0010u\u001a\u00020\u001aH\u0002J\b\u0010v\u001a\u00020\u001aH\u0002J\u0012\u0010w\u001a\u00020\u001a2\b\u0010x\u001a\u0004\u0018\u00010yH\u0016J\b\u0010z\u001a\u00020\u001aH\u0002J\b\u0010{\u001a\u00020\u000eH\u0002J\b\u0010|\u001a\u00020\u000eH\u0016J\u0010\u0010}\u001a\u00020\u001a2\u0006\u0010~\u001a\u00020\u007fH\u0016J\t\u0010\u0080\u0001\u001a\u00020\u000bH\u0016J\t\u0010\u0081\u0001\u001a\u00020\u001aH\u0002J\t\u0010\u0082\u0001\u001a\u00020\u001aH\u0014J0\u0010\u0083\u0001\u001a\u00020\u001a2\u0007\u0010\u0084\u0001\u001a\u00020\u000b2\b\u0010\u0085\u0001\u001a\u00030\u0086\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\b\u0010\u0088\u0001\u001a\u00030\u0086\u0001H\u0016J\t\u0010\u0089\u0001\u001a\u00020\u001aH\u0014J\t\u0010\u008a\u0001\u001a\u00020\u001aH\u0014J\t\u0010\u008b\u0001\u001a\u00020\u001aH\u0002J\u0012\u0010\u008c\u0001\u001a\u00020\u001a2\u0007\u0010\u008d\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u008e\u0001\u001a\u00020\u001aH\u0002J\t\u0010\u008f\u0001\u001a\u00020\u001aH\u0002J\u0012\u0010\u0090\u0001\u001a\u00020\u001a2\u0007\u0010Z\u001a\u00030\u0091\u0001H\u0016J\u0011\u0010\u0092\u0001\u001a\u00020\u001a2\u0006\u0010U\u001a\u00020\tH\u0002J\t\u0010\u0093\u0001\u001a\u00020\u001aH\u0002J\t\u0010\u0094\u0001\u001a\u00020\u001aH\u0002J\t\u0010\u0095\u0001\u001a\u00020\u001aH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0014j\b\u0012\u0004\u0012\u00020\t`\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010!\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010#\u0012\u0004\u0012\u00020\u001a0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010 \u001a\u0004\b*\u0010+R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010 \u001a\u0004\b/\u00100R\u000e\u00102\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010 \u001a\u0004\b9\u0010:R\u0014\u0010<\u001a\b\u0012\u0002\b\u0003\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010?\u001a\u00020@8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u0010 \u001a\u0004\bA\u0010BR\u000e\u0010D\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010HX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010K\u001a\u0004\u0018\u00010LX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u00020OX\u0082\u000e¢\u0006\u0004\n\u0002\u0010PR\u001c\u0010Q\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a\u0018\u00010\"X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010R\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\bS\u0012\b\bT\u0012\u0004\b\b(U\u0012\u0004\u0012\u00020\u000e0\"X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010V\u001a2\u0012\u0013\u0012\u00110W¢\u0006\f\bS\u0012\b\bT\u0012\u0004\b\b(X\u0012\u0013\u0012\u00110Y¢\u0006\f\bS\u0012\b\bT\u0012\u0004\b\b(Z\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\\\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001a0]X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010^\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u00020`X\u0082\u0004¢\u0006\u0004\n\u0002\u0010aR\u001a\u0010b\u001a\u000e\u0012\u0004\u0012\u00020c\u0012\u0004\u0012\u00020\u001a0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u0004\u0018\u00010eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020gX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010h\u001a\u0004\u0018\u00010iX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u001a\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001a0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010o\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010p\u0012\u0004\u0012\u00020\u001a0\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0096\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/GreeterFaceVM;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/greeter_task/FaceDetectorContract$ViewInterface;", "()V", "COUNT_DOWN_TIME", "", "TAG", "", "WHAT_GREETER_TYPE", "", "WHAT_LOCK_MOTOR", "aiVoiceShow", "", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "bubbleBarrage", "Lcom/pudutech/bumblebee/robot_ui/widget/BubbleBarrage;", "bubbles", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getBubbles", "()Ljava/util/ArrayList;", "businessResponseListener", "Lkotlin/Function2;", "", "callSetVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "getCallSetVM", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "callSetVM$delegate", "Lkotlin/Lazy;", "cmdRequestListener", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "countdown", "Lio/reactivex/disposables/Disposable;", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "faceDetectorPresenter", "Lcom/pudutech/bumblebee/presenter/greeter_task/FaceDetectorPresenter;", "getFaceDetectorPresenter", "()Lcom/pudutech/bumblebee/presenter/greeter_task/FaceDetectorPresenter;", "faceDetectorPresenter$delegate", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "isLooperPlay", "isOnDestroy", "isPlaying", "isStopGreeterVoice", "isUserFace", "mAdVm", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "getMAdVm", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "mAdVm$delegate", "mAdverBaseFragment", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseFragment;", "mAdverType", "mAgentTestHelper", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "getMAgentTestHelper", "()Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "mAgentTestHelper$delegate", "mCurScope", "Lkotlinx/coroutines/CoroutineScope;", "mGreeterTypeInterval", "mLockMachineHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/LockMachineHelper;", "mLockMotorCount", "mLockMotorInterval", "mPosterDisplayDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/PosterDisplayDialog;", "mState", "mainHandler", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity$mainHandler$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity$mainHandler$1;", "onAnimationShowListener", "onTaskGreeterListener", "Lkotlin/ParameterName;", "name", "destination", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "onceFace", "resultAnswerListener", "Lkotlin/Function3;", "resultQuestionListener", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity$singleBatteryListener$1;", "statusChangeListener", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "tipDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "voiceTaskWrapper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "getVoiceTaskWrapper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "setVoiceTaskWrapper", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;)V", "volumeChangeListener", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "bindPresenter", "createObserver", "dissVoice", "initAiVoice", "initAllData", "initData", "initView", "saveInstanceState", "Landroid/os/Bundle;", "initViews", "isAdVideo", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "layoutId", "lockedClose", "onDestroy", "onFaceDetectResult", "p0", "p1", "", "p2", "p3", "onResume", "onStop", "playFaceAnimation", "playFaceDetectVoice", "angle", "release", "showAdver", "showIdleEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "startGo", "startGreeterVoice", "stopGreeterVoice", "unbindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterFaceActivity extends MyBaseVmActivity<GreeterFaceVM> implements IdleMoveContract.ViewInterface, FaceDetectorContract.ViewInterface {
    private HashMap _$_findViewCache;
    private boolean aiVoiceShow;
    private BubbleBarrage<String> bubbleBarrage;
    private Disposable countdown;
    private FaceVideoView faceAnimationView;
    private boolean isLooperPlay;
    private boolean isOnDestroy;
    private boolean isPlaying;
    private boolean isStopGreeterVoice;
    private boolean isUserFace;
    private AdverBaseFragment<?> mAdverBaseFragment;
    private LockMachineHelper mLockMachineHelper;
    private PosterDisplayDialog mPosterDisplayDialog;
    private int mState;
    private int onceFace;
    private ShowTipMsgDialog tipDialog;
    private TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper;
    private final String TAG = "GreeterFaceActivity";
    private final long COUNT_DOWN_TIME = 10;
    private final ArrayList<String> bubbles = new ArrayList<>();
    private final int WHAT_GREETER_TYPE = 104;
    private final int WHAT_LOCK_MOTOR = 106;
    private long mLockMotorInterval = 1000;
    private int mLockMotorCount = 1;
    private long mGreeterTypeInterval = 5000;

    /* renamed from: mAdVm$delegate, reason: from kotlin metadata */
    private final Lazy mAdVm = LazyKt.lazy(new Function0<AdVm>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$mAdVm$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AdVm invoke() {
            GreeterFaceActivity greeterFaceActivity = GreeterFaceActivity.this;
            ViewModel viewModel = new ViewModelProvider(greeterFaceActivity, new ViewModelProvider.AndroidViewModelFactory(greeterFaceActivity.getApplication())).get(AdVm.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
            return (AdVm) ((BaseViewModel) viewModel);
        }
    });

    /* renamed from: callSetVM$delegate, reason: from kotlin metadata */
    private final Lazy callSetVM = LazyKt.lazy(new Function0<CallSettingVM>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$callSetVM$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CallSettingVM invoke() {
            GreeterFaceActivity greeterFaceActivity = GreeterFaceActivity.this;
            ViewModel viewModel = new ViewModelProvider(greeterFaceActivity, new ViewModelProvider.AndroidViewModelFactory(greeterFaceActivity.getApplication())).get(CallSettingVM.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
            return (CallSettingVM) ((BaseViewModel) viewModel);
        }
    });
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$onTouchSensorPlaceListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            invoke2(place, event);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            Intrinsics.checkParameterIsNotNull(place, "place");
            Intrinsics.checkParameterIsNotNull(event, "event");
            PeripheralsTrack peripheralsTrack = PeripheralsTrack.INSTANCE;
            int ordinal = place.ordinal();
            int value = event.getValue();
            String simpleName = GreeterFaceActivity.this.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@GreeterFaceActivity::class.java.simpleName");
            PeripheralsTrack.onTouchPeripherals$default(peripheralsTrack, ordinal, value, simpleName, null, 8, null);
        }
    };
    private final Function1<Boolean, Unit> onAnimationShowListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$onAnimationShowListener$1
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
            AdVm mAdVm;
            AdVm mAdVm2;
            AdVm mAdVm3;
            if (z) {
                mAdVm2 = GreeterFaceActivity.this.getMAdVm();
                mAdVm2.controlAdPlay(false);
                mAdVm3 = GreeterFaceActivity.this.getMAdVm();
                mAdVm3.controlAdShow(false);
                GreeterFaceActivity.this.stopGreeterVoice();
                return;
            }
            mAdVm = GreeterFaceActivity.this.getMAdVm();
            mAdVm.controlAdPlay(true);
        }
    };
    private CoroutineScope mCurScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    /* renamed from: mAgentTestHelper$delegate, reason: from kotlin metadata */
    private final Lazy mAgentTestHelper = LazyKt.lazy(new Function0<AgentTestHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$mAgentTestHelper$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AgentTestHelper invoke() {
            return new AgentTestHelper();
        }
    });
    private GreeterFaceActivity$mainHandler$1 mainHandler = new GreeterFaceActivity$mainHandler$1(this, Looper.getMainLooper());

    /* renamed from: faceDetectorPresenter$delegate, reason: from kotlin metadata */
    private final Lazy faceDetectorPresenter = LazyKt.lazy(new Function0<FaceDetectorPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$faceDetectorPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FaceDetectorPresenter invoke() {
            FaceDetectorPresenter faceDetectorPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(FaceDetectorPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(FaceDetectorPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                faceDetectorPresenter = new FaceDetectorPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(FaceDetectorPresenter.class).toString(), faceDetectorPresenter);
            } else {
                if (!(basePresenterInterface instanceof FaceDetectorPresenter)) {
                    basePresenterInterface = null;
                }
                faceDetectorPresenter = (FaceDetectorPresenter) basePresenterInterface;
            }
            if (faceDetectorPresenter == null) {
                Intrinsics.throwNpe();
            }
            return faceDetectorPresenter;
        }
    });
    private final Function1<String, Boolean> onTaskGreeterListener = new Function1<String, Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$onTaskGreeterListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(invoke2(str));
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(final String destination) {
            String str;
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            str = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str, "onTaskGreeterListener destination: " + destination);
            GreeterFaceActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$onTaskGreeterListener$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    GreeterFaceActivity.this.startGo(destination);
                }
            });
            return true;
        }
    };
    private final Function1<WakeupInfo, Unit> wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$wakeupListener$1
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
            boolean z;
            String str;
            String str2;
            Disposable disposable;
            AdVm mAdVm;
            VoiceInteractionTrack voiceInteractionTrack = VoiceInteractionTrack.INSTANCE;
            String simpleName = GreeterFaceActivity.this.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@GreeterFaceActivity::class.java.simpleName");
            VoiceInteractionTrack.onCreateTask$default(voiceInteractionTrack, simpleName, null, 2, null);
            VoiceInteractionTrack voiceInteractionTrack2 = VoiceInteractionTrack.INSTANCE;
            z = GreeterFaceActivity.this.isUserFace;
            voiceInteractionTrack2.onWakeup(z ? WakeUpType.FACE : WakeUpType.VOICE);
            str = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str, "wakeupListener wakeupinfo:" + wakeupInfo + ' ');
            GreeterFaceActivity.this.stopGreeterVoice();
            str2 = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str2, "wakeupListener停止播放：");
            disposable = GreeterFaceActivity.this.countdown;
            if (disposable != null) {
                disposable.dispose();
            }
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, new GeneralVoiceProperty(0L, AiVoiceManager.INSTANCE.getWAKEUP_VOICE(), 3)));
            TextView tv_response = (TextView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.tv_response);
            Intrinsics.checkExpressionValueIsNotNull(tv_response, "tv_response");
            tv_response.setText("");
            TextView tv_request = (TextView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.tv_request);
            Intrinsics.checkExpressionValueIsNotNull(tv_request, "tv_request");
            tv_request.setText("");
            RelativeLayout rlt_dialogue = (RelativeLayout) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.rlt_dialogue);
            Intrinsics.checkExpressionValueIsNotNull(rlt_dialogue, "rlt_dialogue");
            rlt_dialogue.setVisibility(0);
            LineWaveVoiceView lineWaveVoiceView = (LineWaveVoiceView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.lineWaveVoiceView);
            Intrinsics.checkExpressionValueIsNotNull(lineWaveVoiceView, "lineWaveVoiceView");
            lineWaveVoiceView.setVisibility(0);
            LottieAnimationView animation_loading = (LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading);
            Intrinsics.checkExpressionValueIsNotNull(animation_loading, "animation_loading");
            animation_loading.setVisibility(8);
            ((LineWaveVoiceView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.lineWaveVoiceView)).startRecord();
            mAdVm = GreeterFaceActivity.this.getMAdVm();
            mAdVm.controlAdPlay(false);
            MusicPlayerHelper.getInstance().pausePlay();
        }
    };
    private final Function2<String, Boolean, Unit> resultQuestionListener = new Function2<String, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$resultQuestionListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
            invoke(str, bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(String str, boolean z) {
            String str2;
            str2 = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str2, "resultQuestionListener question:" + str + ", b:" + z + ' ');
            VoiceInteractionTrack.INSTANCE.onQuestion(str != null ? str : "");
            TextView tv_request = (TextView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.tv_request);
            Intrinsics.checkExpressionValueIsNotNull(tv_request, "tv_request");
            tv_request.setText(str);
        }
    };
    private final Function3<String, String, Integer, Unit> resultAnswerListener = new Function3<String, String, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$resultAnswerListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num) {
            invoke(str, str2, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(String str, String str2, int i) {
            String str3;
            int i2;
            str3 = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str3, "resultAnswerListener answer:" + str + " ,json:" + str2 + ",state:" + i);
            VoiceInteractionTrack.INSTANCE.onAnswer(str != null ? str : "");
            TextView tv_response = (TextView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.tv_response);
            Intrinsics.checkExpressionValueIsNotNull(tv_response, "tv_response");
            tv_response.setText(str);
            i2 = GreeterFaceActivity.this.mState;
            if (i2 != -3) {
                GreeterFaceActivity.this.mState = i;
            }
        }
    };
    private final Function2<String, String, Unit> businessResponseListener = new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$businessResponseListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
            invoke2(str, str2);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String imgUrl, String text) {
            boolean z;
            int i;
            PosterDisplayDialog posterDisplayDialog;
            PosterDisplayDialog posterDisplayDialog2;
            Activity mActivity;
            PosterDisplayDialog posterDisplayDialog3;
            String str;
            Intrinsics.checkParameterIsNotNull(imgUrl, "imgUrl");
            Intrinsics.checkParameterIsNotNull(text, "text");
            z = GreeterFaceActivity.this.isOnDestroy;
            if (z) {
                GreeterFaceActivity.this.mState = -3;
                str = GreeterFaceActivity.this.TAG;
                Pdlog.m3274e(str, "mActivity isFinishing");
            }
            i = GreeterFaceActivity.this.mState;
            if (i != -3) {
                posterDisplayDialog = GreeterFaceActivity.this.mPosterDisplayDialog;
                if (posterDisplayDialog == null) {
                    GreeterFaceActivity greeterFaceActivity = GreeterFaceActivity.this;
                    mActivity = greeterFaceActivity.getMActivity();
                    greeterFaceActivity.mPosterDisplayDialog = new PosterDisplayDialog(mActivity);
                    posterDisplayDialog3 = GreeterFaceActivity.this.mPosterDisplayDialog;
                    if (posterDisplayDialog3 != null) {
                        posterDisplayDialog3.setOnClose(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$businessResponseListener$1.1
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
                                GreeterFaceActivity.this.dissVoice();
                            }
                        });
                    }
                }
                posterDisplayDialog2 = GreeterFaceActivity.this.mPosterDisplayDialog;
                if (posterDisplayDialog2 == null || posterDisplayDialog2.isShowing()) {
                    return;
                }
                posterDisplayDialog2.show();
                posterDisplayDialog2.setDisplayData(imgUrl, text);
            }
        }
    };
    private final Function1<VoiceInteractionState, Unit> statusChangeListener = new Function1<VoiceInteractionState, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$statusChangeListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionState voiceInteractionState) {
            invoke2(voiceInteractionState);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(VoiceInteractionState it) {
            String str;
            Disposable disposable;
            int i;
            boolean z;
            FaceDetectorPresenter faceDetectorPresenter;
            long j;
            boolean z2;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str, "statusChangeListener state:" + it + ' ');
            switch (it) {
                case Idle:
                    GreeterFaceActivity.this.isPlaying = false;
                    disposable = GreeterFaceActivity.this.countdown;
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    i = GreeterFaceActivity.this.mState;
                    if (i != 1) {
                        GreeterFaceActivity greeterFaceActivity = GreeterFaceActivity.this;
                        CountdownUtil countdownUtil = CountdownUtil.INSTANCE;
                        j = GreeterFaceActivity.this.COUNT_DOWN_TIME;
                        greeterFaceActivity.countdown = countdownUtil.createCountDown(j).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$statusChangeListener$1.1
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Long l) {
                            }
                        }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$statusChangeListener$1.2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                            }
                        }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$statusChangeListener$1.3
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                GreeterFaceActivity.this.dissVoice();
                            }
                        });
                    }
                    z = GreeterFaceActivity.this.isUserFace;
                    if (z) {
                        GreeterFaceActivity.this.aiVoiceShow = false;
                        AiVoiceManager.INSTANCE.stopAiRecording();
                        faceDetectorPresenter = GreeterFaceActivity.this.getFaceDetectorPresenter();
                        faceDetectorPresenter.actionActive();
                        return;
                    }
                    return;
                case Eos:
                    LineWaveVoiceView lineWaveVoiceView = (LineWaveVoiceView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.lineWaveVoiceView);
                    Intrinsics.checkExpressionValueIsNotNull(lineWaveVoiceView, "lineWaveVoiceView");
                    lineWaveVoiceView.setVisibility(8);
                    LottieAnimationView animation_loading = (LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading);
                    Intrinsics.checkExpressionValueIsNotNull(animation_loading, "animation_loading");
                    animation_loading.setVisibility(0);
                    ((LineWaveVoiceView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.lineWaveVoiceView)).stopRecord();
                    ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).setAnimation("animation/loading.json");
                    LottieAnimationView animation_loading2 = (LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading);
                    Intrinsics.checkExpressionValueIsNotNull(animation_loading2, "animation_loading");
                    animation_loading2.setRepeatCount(1);
                    ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).playAnimation();
                    ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).addAnimatorListener(new Animator.AnimatorListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$statusChangeListener$1.4
                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationRepeat(Animator animation) {
                            ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).setAnimation("animation/loading2.json");
                            LottieAnimationView animation_loading3 = (LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading);
                            Intrinsics.checkExpressionValueIsNotNull(animation_loading3, "animation_loading");
                            animation_loading3.setRepeatCount(-1);
                            ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).playAnimation();
                        }
                    });
                    return;
                case PlayCompleted:
                case Recording:
                case BosTimeout:
                default:
                    return;
                case ErrorNetWork:
                    TextView tv_request = (TextView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.tv_request);
                    Intrinsics.checkExpressionValueIsNotNull(tv_request, "tv_request");
                    tv_request.setText(GreeterFaceActivity.this.getString(C4188R.string.pdStr14_7));
                    LottieAnimationView animation_loading3 = (LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading);
                    Intrinsics.checkExpressionValueIsNotNull(animation_loading3, "animation_loading");
                    animation_loading3.setVisibility(8);
                    VoiceInteractionTrack.INSTANCE.onError(VoiceInteractionState.ErrorNetWork.getState(), VoiceInteractionState.ErrorNetWork.name());
                    return;
                case Unknown:
                    VoiceInteractionTrack.INSTANCE.onError(VoiceInteractionState.Unknown.getState(), VoiceInteractionState.Unknown.name());
                    return;
                case ErrorAudioError:
                    VoiceInteractionTrack.INSTANCE.onError(VoiceInteractionState.ErrorAudioError.getState(), VoiceInteractionState.ErrorAudioError.name());
                    return;
                case Speaking:
                    z2 = GreeterFaceActivity.this.isPlaying;
                    if (z2) {
                        return;
                    }
                    GreeterFaceActivity.this.isPlaying = true;
                    ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).removeAllAnimatorListeners();
                    ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).setAnimation("animation/playing.json");
                    LottieAnimationView animation_loading4 = (LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading);
                    Intrinsics.checkExpressionValueIsNotNull(animation_loading4, "animation_loading");
                    animation_loading4.setRepeatCount(-1);
                    ((LottieAnimationView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.animation_loading)).playAnimation();
                    return;
            }
        }
    };
    private final Function1<Integer, Unit> volumeChangeListener = new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$volumeChangeListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            String str;
            str = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str, "volumeChangeListener volume: " + i);
            ((LineWaveVoiceView) GreeterFaceActivity.this._$_findCachedViewById(C4188R.id.lineWaveVoiceView)).setVolume(((float) i) / 20.0f);
        }
    };
    private final Function1<CmdBean, Unit> cmdRequestListener = new Function1<CmdBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$cmdRequestListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CmdBean cmdBean) {
            invoke2(cmdBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CmdBean cmdBean) {
            String str;
            str = GreeterFaceActivity.this.TAG;
            Pdlog.m3273d(str, "cmdRequestListener cmdBean:" + cmdBean + ' ');
        }
    };
    private int mAdverType = -1;

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$idleMovePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IdleMovePresenter invoke() {
            IdleMovePresenter idleMovePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(IdleMovePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                idleMovePresenter = new IdleMovePresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
                if (!(basePresenterInterface instanceof IdleMovePresenter)) {
                    basePresenterInterface = null;
                }
                idleMovePresenter = (IdleMovePresenter) basePresenterInterface;
            }
            if (idleMovePresenter == null) {
                Intrinsics.throwNpe();
            }
            return idleMovePresenter;
        }
    });
    private final GreeterFaceActivity$singleBatteryListener$1 singleBatteryListener = new GreeterFaceActivity$singleBatteryListener$1(this);

    private final CallSettingVM getCallSetVM() {
        return (CallSettingVM) this.callSetVM.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FaceDetectorPresenter getFaceDetectorPresenter() {
        return (FaceDetectorPresenter) this.faceDetectorPresenter.getValue();
    }

    private final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AdVm getMAdVm() {
        return (AdVm) this.mAdVm.getValue();
    }

    private final AgentTestHelper getMAgentTestHelper() {
        return (AgentTestHelper) this.mAgentTestHelper.getValue();
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

    public final ArrayList<String> getBubbles() {
        return this.bubbles;
    }

    public final TtsVoiceWrapperPlayer.VoiceTaskWrapper getVoiceTaskWrapper() {
        return this.voiceTaskWrapper;
    }

    public final void setVoiceTaskWrapper(TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper) {
        this.voiceTaskWrapper = voiceTaskWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dissVoice() {
        this.onceFace = 0;
        ((LottieAnimationView) _$_findCachedViewById(C4188R.id.animation_loading)).cancelAnimation();
        RelativeLayout rlt_dialogue = (RelativeLayout) _$_findCachedViewById(C4188R.id.rlt_dialogue);
        Intrinsics.checkExpressionValueIsNotNull(rlt_dialogue, "rlt_dialogue");
        rlt_dialogue.setVisibility(8);
        LottieAnimationView animation_loading = (LottieAnimationView) _$_findCachedViewById(C4188R.id.animation_loading);
        Intrinsics.checkExpressionValueIsNotNull(animation_loading, "animation_loading");
        animation_loading.setVisibility(8);
        startGreeterVoice();
        getMAdVm().controlAdPlay(true);
        if (!isAdVideo()) {
            MusicPlayerHelper.getInstance().startPlay(ModeEnum.GREETER);
        }
        Pdlog.m3273d(this.TAG, "statusChangeListener--语音进行重新的播报");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGo(String destination) {
        if (BatteryInfoManager.INSTANCE.isCharging()) {
            ShowTipMsgDialog showTipMsgDialog = this.tipDialog;
            if (showTipMsgDialog != null) {
                showTipMsgDialog.dismiss();
            }
            String string = getString(C4188R.string.pdStr25_17);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
            this.tipDialog = MyBaseVmActivity.showTipDialog$default(this, string, null, null, null, 14, null);
            return;
        }
        String str = destination;
        if (str == null || StringsKt.isBlank(str)) {
            ShowTipMsgDialog showTipMsgDialog2 = this.tipDialog;
            if (showTipMsgDialog2 != null) {
                showTipMsgDialog2.dismiss();
            }
            String string2 = getString(C4188R.string.pdStr25_6);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr25_6)");
            this.tipDialog = MyBaseVmActivity.showTipDialog$default(this, string2, null, null, null, 14, null);
            return;
        }
        Intent intent = new Intent(this, (Class<?>) GreeterActivity.class);
        intent.putStringArrayListExtra(GreeterActivity.INSTANCE.getTABLE_NAME(), CollectionsKt.arrayListOf(destination));
        jumpAndFinish(intent);
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_greeter_face;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        if (getNeedReinitApp()) {
            Pdlog.m3277w(this.TAG, "need re_init app");
            return;
        }
        this.isUserFace = RobotInfo.INSTANCE.getMonocularCamera() != 0;
        SceneRecord.INSTANCE.saveScene(this, SceneRecord.Scene.GREETER_FACE);
        AdvertiseVm.reqNetAdList$default(((GreeterFaceVM) getMViewModel()).getMAdverVm(), AdSceneConfig.SOLICITING_PASSENGERS_MODE, false, 2, null);
        initData();
        initViews();
        initAllData();
        this.mainHandler.sendEmptyMessageDelayed(this.WHAT_LOCK_MOTOR, this.mLockMotorInterval);
        CoreDevices.INSTANCE.getRgbd().turnOn(false);
        this.mLockMachineHelper = new LockMachineHelper(this, this);
        LockMachineHelper lockMachineHelper = this.mLockMachineHelper;
        if (lockMachineHelper != null) {
            getLifecycle().addObserver(lockMachineHelper);
            lockMachineHelper.setOnHavedLocked(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$initView$$inlined$let$lambda$1
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
                    if (z) {
                        GreeterFaceActivity.this.lockedClose();
                    }
                }
            });
            Pdlog.m3273d(this.TAG, "setOnHavedLocked:" + lockMachineHelper);
        }
        getMAgentTestHelper().bindLifecycle(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        ((GreeterFaceVM) getMViewModel()).getMAdverVm().get_adverDataLd().observe(this, new GreeterFaceActivity$createObserver$$inlined$observe$1(this));
        getMAdVm().controlAdPlay(true);
        Pdlog.m3273d(this.TAG, "createObserver mAdVm: " + getMAdVm());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isAdVideo() {
        boolean z = this.mAdverType == 1 || ((GreeterFaceVM) getMViewModel()).getMAdverVm().getAdverType(AdSceneConfig.SOLICITING_PASSENGERS_MODE) == 1;
        Pdlog.m3273d(this.TAG, "isAdVideo() result =" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAdver() {
        ConstraintLayout greeter_face_no_ad = (ConstraintLayout) _$_findCachedViewById(C4188R.id.greeter_face_no_ad);
        Intrinsics.checkExpressionValueIsNotNull(greeter_face_no_ad, "greeter_face_no_ad");
        ViewExtKt.gone(greeter_face_no_ad);
        FrameLayout adver_contain = (FrameLayout) _$_findCachedViewById(C4188R.id.adver_contain);
        Intrinsics.checkExpressionValueIsNotNull(adver_contain, "adver_contain");
        ViewExtKt.show(adver_contain);
        this.isUserFace = false;
        getFaceDetectorPresenter().removeView(this);
        getFaceDetectorPresenter().actionClose();
        AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
        this.bubbles.clear();
        BubbleBarrage<String> bubbleBarrage = this.bubbleBarrage;
        if (bubbleBarrage != null) {
            bubbleBarrage.destroyView();
        }
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
        if (isAdVideo()) {
            stopGreeterVoice();
            MusicPlayerHelper.getInstance().stopPlay();
        }
        Pdlog.m3273d(this.TAG, "showAdver()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void lockedClose() {
        MusicPlayerHelper.getInstance().pausePlay();
        AiVoiceManager.INSTANCE.cancelTTS();
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.removeVolumeChangeListener(this.volumeChangeListener);
        AiVoiceManager.INSTANCE.removeStatusListener(this.statusChangeListener);
        AiVoiceManager.INSTANCE.removeResultQuestionListener(this.resultQuestionListener);
        AiVoiceManager.INSTANCE.removeResultAnswerListener(this.resultAnswerListener);
        AiVoiceManager.INSTANCE.removeBusinessResponseListener(this.businessResponseListener);
        AiVoiceManager.INSTANCE.removeCmdResponseListener(this.cmdRequestListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        getFaceDetectorPresenter().removeView(this);
        getFaceDetectorPresenter().actionClose();
        VoicePlayer.INSTANCE.stop();
        stopGreeterVoice();
        getMAdVm().controlAdPlay(false);
        CoreDevices.INSTANCE.getRgbd().turnOn(false);
    }

    private final void initData() {
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            this.bubbles.add(getResources().getString(C4188R.string.pdStr14_2));
            this.bubbles.add(getResources().getString(C4188R.string.pdStr14_4));
        } else {
            this.bubbles.add(getResources().getString(C4188R.string.greeter_bella_hello));
            this.bubbles.add(getResources().getString(C4188R.string.greeter_ask_from));
        }
        boolean z = true;
        this.bubbles.add(1, getResources().getString(C4188R.string.pdStr14_3));
        if (!Constans.INSTANCE.getGreeterFaceDefaultVoiceSwitch() && LanguageUtils.INSTANCE.isZh()) {
            z = false;
        }
        this.isLooperPlay = z;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    private final void initAllData() {
        Pdlog.m3273d(this.TAG, "initAllData ");
        initAiVoice();
        bindPresenter();
        getIdleMovePresenter().actionIDLE();
        getIdleMovePresenter().actionTimerCount(false);
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.setWorkMode(WorkMode.Guide);
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.addVolumeChangeListener(this.volumeChangeListener);
        AiVoiceManager.INSTANCE.addStatusListener(this.statusChangeListener);
        AiVoiceManager.INSTANCE.addResultQuestionListener(this.resultQuestionListener);
        AiVoiceManager.INSTANCE.addResultAnswerListener(this.resultAnswerListener);
        AiVoiceManager.INSTANCE.addBusinessResponseListener(this.businessResponseListener);
        AiVoiceManager.INSTANCE.addCmdResponseListener(this.cmdRequestListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGreeterVoice() {
        if (this.isLooperPlay && !isAdVideo()) {
            this.mainHandler.sendEmptyMessageDelayed(this.WHAT_GREETER_TYPE, this.mGreeterTypeInterval);
        }
        this.isStopGreeterVoice = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopGreeterVoice() {
        GreeterFaceActivity$mainHandler$1 greeterFaceActivity$mainHandler$1;
        Pdlog.m3273d(this.TAG, "stopGreeterVoice--停止语音的播报");
        this.isStopGreeterVoice = true;
        GreeterFaceActivity$mainHandler$1 greeterFaceActivity$mainHandler$12 = this.mainHandler;
        if ((greeterFaceActivity$mainHandler$12 != null ? Boolean.valueOf(greeterFaceActivity$mainHandler$12.hasMessages(this.WHAT_GREETER_TYPE)) : null).booleanValue() && (greeterFaceActivity$mainHandler$1 = this.mainHandler) != null) {
            greeterFaceActivity$mainHandler$1.removeMessages(this.WHAT_GREETER_TYPE);
        }
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.voiceTaskWrapper;
        if (voiceTaskWrapper != null) {
            TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper);
        }
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter ");
        this.mGreeterTypeInterval = TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.GREETER_TYPE) * 1000;
        startGreeterVoice();
        GreeterFaceActivity greeterFaceActivity = this;
        getIdleMovePresenter().replaceView(greeterFaceActivity);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.singleBatteryListener.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        this.singleBatteryListener.showPowerEvent(BatteryInfoManager.INSTANCE.getPowerEvent());
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
        this.touchSensorEventHelper.setOnVoiceStateListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$bindPresenter$1
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == PlayEvent.COMPLETION_ONCE) {
                    GreeterFaceActivity.this.startGreeterVoice();
                }
            }
        });
        this.touchSensorEventHelper.setOnShowFaceStateListener(new Function1<TouchSensorEventHelper.State, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$bindPresenter$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorEventHelper.State it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorEventHelper.State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }
        });
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        this.touchSensorEventHelper.setCanHandle(true, true);
        this.touchSensorEventHelper.setOnAnimationShowListener(this.onAnimationShowListener);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        AiVoiceTriggerHelper.INSTANCE.setOnTaskGreeterListener(this.onTaskGreeterListener);
        if (this.isUserFace) {
            getFaceDetectorPresenter().replaceView(greeterFaceActivity);
            getFaceDetectorPresenter().actionActive();
        } else {
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
        }
        MusicPlayerHelper.getInstance().reset();
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.GREETER);
        playFaceAnimation();
    }

    private final void initViews() {
        if (Constans.INSTANCE.isGreeterGuideTextOpen()) {
            this.bubbleBarrage = new BubbleBarrage().init(this, (LinearLayout) _$_findCachedViewById(C4188R.id.llt_bubblebarrage), C4188R.layout.layout_bubble_barrage_item).setIntervalTime(2000).setVisibleCount(3).setItemMargin(20).setOnBarrageLoadListener(new BubbleBarrage.OnBarrageLoadListener<String>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$initViews$1
                @Override // com.pudutech.bumblebee.robot_ui.widget.BubbleBarrage.OnBarrageLoadListener
                public void loadBarrage(View view, List<String> data, int index) {
                    String str;
                    if (view != null) {
                        TextView tvContent = (TextView) view.findViewById(C4188R.id.tv_content);
                        Intrinsics.checkExpressionValueIsNotNull(tvContent, "tvContent");
                        if (data == null || (str = data.get(index)) == null) {
                            str = "";
                        }
                        tvContent.setText(str);
                    }
                }
            }).start(this.bubbles, 1000);
        }
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.rlt_to_welcome)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$initViews$2
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
                ShowTipMsgDialog showTipMsgDialog;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (BatteryInfoManager.INSTANCE.isCharging()) {
                    showTipMsgDialog = GreeterFaceActivity.this.tipDialog;
                    if (showTipMsgDialog != null) {
                        showTipMsgDialog.dismiss();
                    }
                    GreeterFaceActivity greeterFaceActivity = GreeterFaceActivity.this;
                    String string = greeterFaceActivity.getString(C4188R.string.pdStr25_17);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr25_17)");
                    greeterFaceActivity.tipDialog = MyBaseVmActivity.showTipDialog$default(greeterFaceActivity, string, null, null, null, 14, null);
                    return;
                }
                GreeterFaceActivity.this.jumpAndFinish(new Intent(GreeterFaceActivity.this, (Class<?>) GotoWelcomeAreaActivity.class));
            }
        }, 3, null));
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.rlt_into_greeter)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$initViews$3
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                GreeterFaceActivity.this.jumpAndFinish(new Intent(GreeterFaceActivity.this, (Class<?>) GreeterMenuActivity.class));
            }
        }, 3, null));
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        PeripheralsSceneUtil.INSTANCE.setPlayMode(PeripheralsSceneUtil.Mode.Welcome);
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showIdleEvent event=" + event);
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorContract.ViewInterface
    public void onFaceDetectResult(int p0, double p1, double p2, double p3) {
        Pdlog.m3273d(this.TAG, "onFaceDetectResult : p0 = " + p0 + "; p1 = " + p1 + "; p2 = " + p2 + "; p3 = " + p3 + "; aiVoiceShow  = " + this.aiVoiceShow);
        if (p0 <= 0 || this.aiVoiceShow) {
            return;
        }
        this.touchSensorEventHelper.setCanHandle(false, true);
        playFaceDetectVoice(0);
    }

    private final void playFaceDetectVoice(int angle) {
        Pdlog.m3273d(this.TAG, "playFaceDetectVoice : angle = " + angle + "; ");
        this.aiVoiceShow = true;
        getFaceDetectorPresenter().actionClose();
        AiVoiceManager.INSTANCE.startAiRecording(true);
        Pdlog.m3273d(this.TAG, "playFaceDetectVoice onceFace：" + this.onceFace);
        if (this.isLooperPlay) {
            this.touchSensorEventHelper.setCanHandle(true, true);
            AiVoiceManager.INSTANCE.startWakeUp(0, 0);
            stopGreeterVoice();
            Pdlog.m3273d(this.TAG, "playFaceDetectVoice停止播放：");
            return;
        }
        int i = this.onceFace;
        if (i == 0) {
            startGreeterVoice();
            this.mainHandler.sendEmptyMessage(this.WHAT_GREETER_TYPE);
            this.onceFace = 3;
        } else {
            this.onceFace = i - 1;
            this.touchSensorEventHelper.setCanHandle(true, true);
            AiVoiceManager.INSTANCE.startWakeUp(0, 0);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity
    public boolean isBusyState() {
        Boolean value = getCallSetVM().getGreeterPointCanCallSwitchLD().getValue();
        if (value == null) {
            value = false;
        }
        return !value.booleanValue();
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter ");
        this.isOnDestroy = true;
        getIdleMovePresenter().removeView(this);
        this.touchSensorEventHelper.unBindPresent();
        MusicPlayerHelper.getInstance().stopPlay();
        AiVoiceManager.INSTANCE.cancelTTS();
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.removeVolumeChangeListener(this.volumeChangeListener);
        AiVoiceManager.INSTANCE.removeStatusListener(this.statusChangeListener);
        AiVoiceManager.INSTANCE.removeResultQuestionListener(this.resultQuestionListener);
        AiVoiceManager.INSTANCE.removeResultAnswerListener(this.resultAnswerListener);
        AiVoiceManager.INSTANCE.removeBusinessResponseListener(this.businessResponseListener);
        AiVoiceManager.INSTANCE.removeCmdResponseListener(this.cmdRequestListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        VoicePlayer.INSTANCE.stop();
        Disposable disposable = this.countdown;
        if (disposable != null) {
            disposable.dispose();
        }
        this.mainHandler.removeCallbacksAndMessages(null);
        getFaceDetectorPresenter().actionClose();
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        BubbleBarrage<String> bubbleBarrage = this.bubbleBarrage;
        if (bubbleBarrage != null) {
            bubbleBarrage.destroyView();
        }
        AiVoiceTriggerHelper.INSTANCE.setOnTaskGreeterListener((Function1) null);
        CoreDevices.INSTANCE.getRgbd().turnOn(true);
        GreeterFaceActivity$mainHandler$1 greeterFaceActivity$mainHandler$1 = this.mainHandler;
        if (greeterFaceActivity$mainHandler$1 != null && greeterFaceActivity$mainHandler$1.hasMessages(this.WHAT_LOCK_MOTOR)) {
            this.mainHandler.removeMessages(this.WHAT_LOCK_MOTOR);
        }
        stopGreeterVoice();
        Pdlog.m3273d(this.TAG, "onDestroy:停止语音播报");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    private final void release() {
        unbindPresenter();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        BuildersKt__Builders_commonKt.launch$default(this.mCurScope, null, null, new GreeterFaceActivity$onStop$1(null), 3, null);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    private final void playFaceAnimation() {
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getGreeterFace());
    }
}
