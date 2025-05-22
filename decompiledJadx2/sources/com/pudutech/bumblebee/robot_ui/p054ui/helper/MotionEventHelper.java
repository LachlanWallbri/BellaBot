package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.motion_task.MotionContract;
import com.pudutech.bumblebee.presenter.motion_task.MotionPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.schedule_task.SchedulePresenter;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: MotionEventHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004*\u000429?B\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J=\u0010b\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\u0015\u001a\u00020\u00162#\u0010Y\u001a\u001f\u0012\u0013\u0012\u00110X¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(Z\u0012\u0004\u0012\u00020\t\u0018\u00010\"J\b\u0010c\u001a\u00020\tH\u0002J\u0006\u0010d\u001a\u00020\tJ\b\u0010e\u001a\u00020\tH\u0002J\u0010\u0010f\u001a\u00020\t2\u0006\u0010g\u001a\u00020-H\u0002J\u0010\u0010h\u001a\u00020I2\u0006\u0010i\u001a\u00020IH\u0002J\u000e\u0010j\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u0018J\u000e\u0010k\u001a\u00020\t2\u0006\u0010l\u001a\u00020\u0018J\u0006\u0010m\u001a\u00020\tJ\u0010\u0010n\u001a\u00020\t2\u0006\u0010P\u001a\u00020\u000fH\u0016J\u0010\u0010o\u001a\u00020\t2\u0006\u0010T\u001a\u00020\u0018H\u0002J\u001a\u0010p\u001a\u00020\t2\u0006\u0010Z\u001a\u00020X2\b\u0010q\u001a\u0004\u0018\u00010rH\u0016J\b\u0010s\u001a\u00020\tH\u0002J\u0006\u0010t\u001a\u00020\tJ\u0006\u0010u\u001a\u00020\tR\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR7\u0010!\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\t\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010 \u001a\u0004\b.\u0010/R\u0010\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103R\u000e\u00104\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00105\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010 \u001a\u0004\b6\u0010/R\u0010\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0004\n\u0002\u0010:R\u000e\u0010;\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@R\u0010\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0004\n\u0002\u0010CR7\u0010D\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(E\u0012\u0004\u0012\u00020\t\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010'\"\u0004\bG\u0010)R\u001c\u0010H\u001a\u0004\u0018\u00010IX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR7\u0010N\u001a\u001f\u0012\u0013\u0012\u00110O¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020\t\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010'\"\u0004\bR\u0010)R7\u0010S\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(T\u0012\u0004\u0012\u00020\t\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010'\"\u0004\bV\u0010)R\u000e\u0010W\u001a\u00020XX\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010Y\u001a\u001f\u0012\u0013\u0012\u00110X¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(Z\u0012\u0004\u0012\u00020\t\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010'\"\u0004\b\\\u0010)R\u001b\u0010]\u001a\u00020^8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\ba\u0010 \u001a\u0004\b_\u0010`¨\u0006v"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "Lcom/pudutech/bumblebee/presenter/motion_task/MotionContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "actionExitObstruct", "Lkotlin/Function0;", "", "getActionExitObstruct", "()Lkotlin/jvm/functions/Function0;", "setActionExitObstruct", "(Lkotlin/jvm/functions/Function0;)V", "currentMoveEvent", "Lcom/pudutech/bumblebee/presenter/motion_task/MotionContract$Event;", "currentMovingText", "getCurrentMovingText", "()Ljava/lang/String;", "setCurrentMovingText", "(Ljava/lang/String;)V", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "isCanHandle", "", "isCanPlayObstructedVideo", "isNeedRestartMovingVoice", "motionPresenter", "Lcom/pudutech/bumblebee/presenter/motion_task/MotionPresenter;", "getMotionPresenter", "()Lcom/pudutech/bumblebee/presenter/motion_task/MotionPresenter;", "motionPresenter$delegate", "Lkotlin/Lazy;", "moveVoicePlayingLedTask", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "getMoveVoicePlayingLedTask", "()Lkotlin/jvm/functions/Function1;", "setMoveVoicePlayingLedTask", "(Lkotlin/jvm/functions/Function1;)V", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "obstructedVoice1", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "getObstructedVoice1", "()Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "obstructedVoice1$delegate", "obstructedVoice1Listener", "com/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$obstructedVoice1Listener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$obstructedVoice1Listener$1;", "obstructedVoice1Playing", "obstructedVoice2", "getObstructedVoice2", "obstructedVoice2$delegate", "obstructedVoice2Listener", "com/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$obstructedVoice2Listener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$obstructedVoice2Listener$1;", "obstructedVoice2Playing", "obstructedVoicePlayCount", "", "onFaceAnimationFinish", "com/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$onFaceAnimationFinish$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$onFaceAnimationFinish$1;", "onFaceAnimationStop", "com/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$onFaceAnimationStop$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$onFaceAnimationStop$1;", "onPlayFaceVideo", "isPlay", "getOnPlayFaceVideo", "setOnPlayFaceVideo", "onTheWayAnimation", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "getOnTheWayAnimation", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "setOnTheWayAnimation", "(Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;)V", "onVoicePlayListener", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "event", "getOnVoicePlayListener", "setOnVoicePlayListener", "robotScheduleListener", "isAvoid", "getRobotScheduleListener", "setRobotScheduleListener", "robotScheduleStatus", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "scheduleEventLister", "triggerEvent", "getScheduleEventLister", "setScheduleEventLister", "schedulePresenter", "Lcom/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter;", "getSchedulePresenter", "()Lcom/pudutech/bumblebee/presenter/schedule_task/SchedulePresenter;", "schedulePresenter$delegate", "bind", "playAngryFaceAnimation", "playMovingVoice", "playMovingVoiceIfNeed", "playVoice", "voiceTask", "setAnimationListener", "animation", "setCanHandleMovingEvent", "setCanPlayObstructedVideo", "isCan", "showLedScreenString", "showMotionEvent", "showScheduleAnimation", "showScheduleEvent", "model", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$Model;", "stopAllVoice", "stopMovingVoice", "unBind", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MotionEventHelper implements MotionContract.ViewInterface, ScheduleContract.ViewInterface {
    private Function0<Unit> actionExitObstruct;
    private MotionContract.Event currentMoveEvent;
    private FaceVideoView faceAnimationView;
    private boolean isCanHandle;
    private boolean isNeedRestartMovingVoice;
    private Function1<? super Boolean, Unit> moveVoicePlayingLedTask;
    private TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask;
    private boolean obstructedVoice1Playing;
    private boolean obstructedVoice2Playing;
    private int obstructedVoicePlayCount;
    private Function1<? super Boolean, Unit> onPlayFaceVideo;
    private Function1<? super PlayEvent, Unit> onVoicePlayListener;
    private Function1<? super Boolean, Unit> robotScheduleListener;
    private Function1<? super ScheduleContract.TriggerEvent, Unit> scheduleEventLister;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: motionPresenter$delegate, reason: from kotlin metadata */
    private final Lazy motionPresenter = LazyKt.lazy(new Function0<MotionPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$motionPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MotionPresenter invoke() {
            MotionPresenter motionPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MotionPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(MotionPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                motionPresenter = new MotionPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(MotionPresenter.class).toString(), motionPresenter);
            } else {
                if (!(basePresenterInterface instanceof MotionPresenter)) {
                    basePresenterInterface = null;
                }
                motionPresenter = (MotionPresenter) basePresenterInterface;
            }
            if (motionPresenter != null) {
                return motionPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.motion_task.MotionPresenter");
        }
    });

    /* renamed from: schedulePresenter$delegate, reason: from kotlin metadata */
    private final Lazy schedulePresenter = LazyKt.lazy(new Function0<SchedulePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$schedulePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SchedulePresenter invoke() {
            SchedulePresenter schedulePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(SchedulePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(SchedulePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                schedulePresenter = new SchedulePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(SchedulePresenter.class).toString(), schedulePresenter);
            } else {
                if (!(basePresenterInterface instanceof SchedulePresenter)) {
                    basePresenterInterface = null;
                }
                schedulePresenter = (SchedulePresenter) basePresenterInterface;
            }
            if (schedulePresenter != null) {
                return schedulePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.schedule_task.SchedulePresenter");
        }
    });
    private boolean isCanPlayObstructedVideo = true;

    /* renamed from: obstructedVoice1$delegate, reason: from kotlin metadata */
    private final Lazy obstructedVoice1 = LazyKt.lazy(new Function0<VoiceTask>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$obstructedVoice1$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VoiceTask invoke() {
            return new VoiceTask(5000L, VoiceItem.voice13_1);
        }
    });

    /* renamed from: obstructedVoice2$delegate, reason: from kotlin metadata */
    private final Lazy obstructedVoice2 = LazyKt.lazy(new Function0<VoiceTask>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$obstructedVoice2$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VoiceTask invoke() {
            return new VoiceTask(4000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(1000L, VoiceItem.voice13_2)});
        }
    });
    private String currentMovingText = "";
    private FaceVideoAnimation onTheWayAnimation = SceneAnimationResources.INSTANCE.getOnTheWay();
    private ScheduleContract.TriggerEvent robotScheduleStatus = ScheduleContract.TriggerEvent.NORMAL;
    private final MotionEventHelper$obstructedVoice1Listener$1 obstructedVoice1Listener = new MotionEventHelper$obstructedVoice1Listener$1(this);
    private final MotionEventHelper$obstructedVoice2Listener$1 obstructedVoice2Listener = new MotionEventHelper$obstructedVoice2Listener$1(this);
    private final MotionEventHelper$onFaceAnimationFinish$1 onFaceAnimationFinish = new Function1<FaceVideoAnimation, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$onFaceAnimationFinish$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FaceVideoAnimation faceVideoAnimation) {
            invoke2(faceVideoAnimation);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(FaceVideoAnimation animation) {
            String str;
            Intrinsics.checkParameterIsNotNull(animation, "animation");
            str = MotionEventHelper.this.TAG;
            Pdlog.m3273d(str, "onFaceAnimationFinish()");
            Function1<Boolean, Unit> onPlayFaceVideo = MotionEventHelper.this.getOnPlayFaceVideo();
            if (onPlayFaceVideo != null) {
                onPlayFaceVideo.invoke(false);
            }
        }
    };
    private final MotionEventHelper$onFaceAnimationStop$1 onFaceAnimationStop = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$onFaceAnimationStop$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2() {
            String str;
            str = MotionEventHelper.this.TAG;
            Pdlog.m3273d(str, "onFaceAnimationStop()");
            Function1<Boolean, Unit> onPlayFaceVideo = MotionEventHelper.this.getOnPlayFaceVideo();
            if (onPlayFaceVideo != null) {
                onPlayFaceVideo.invoke(false);
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MotionContract.Event.MOVE_FORWARD.ordinal()] = 1;
            $EnumSwitchMapping$0[MotionContract.Event.TURN_LEFT.ordinal()] = 2;
            $EnumSwitchMapping$0[MotionContract.Event.BRAKE.ordinal()] = 3;
            $EnumSwitchMapping$0[MotionContract.Event.TURN_RIGHT.ordinal()] = 4;
            $EnumSwitchMapping$0[MotionContract.Event.OBSTRUCTED.ordinal()] = 5;
            $EnumSwitchMapping$0[MotionContract.Event.NO_OBSTRUCTED.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[ScheduleContract.TriggerEvent.values().length];
            $EnumSwitchMapping$1[ScheduleContract.TriggerEvent.AVOID.ordinal()] = 1;
            $EnumSwitchMapping$1[ScheduleContract.TriggerEvent.NORMAL.ordinal()] = 2;
        }
    }

    private final MotionPresenter getMotionPresenter() {
        return (MotionPresenter) this.motionPresenter.getValue();
    }

    private final VoiceTask getObstructedVoice1() {
        return (VoiceTask) this.obstructedVoice1.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VoiceTask getObstructedVoice2() {
        return (VoiceTask) this.obstructedVoice2.getValue();
    }

    private final SchedulePresenter getSchedulePresenter() {
        return (SchedulePresenter) this.schedulePresenter.getValue();
    }

    public final String getCurrentMovingText() {
        return this.currentMovingText;
    }

    public final void setCurrentMovingText(String str) {
        this.currentMovingText = str;
    }

    public final FaceVideoAnimation getOnTheWayAnimation() {
        return this.onTheWayAnimation;
    }

    public final void setOnTheWayAnimation(FaceVideoAnimation faceVideoAnimation) {
        this.onTheWayAnimation = faceVideoAnimation;
    }

    public final Function1<Boolean, Unit> getMoveVoicePlayingLedTask() {
        return this.moveVoicePlayingLedTask;
    }

    public final void setMoveVoicePlayingLedTask(Function1<? super Boolean, Unit> function1) {
        this.moveVoicePlayingLedTask = function1;
    }

    public final Function1<PlayEvent, Unit> getOnVoicePlayListener() {
        return this.onVoicePlayListener;
    }

    public final void setOnVoicePlayListener(Function1<? super PlayEvent, Unit> function1) {
        this.onVoicePlayListener = function1;
    }

    public final Function1<Boolean, Unit> getRobotScheduleListener() {
        return this.robotScheduleListener;
    }

    public final void setRobotScheduleListener(Function1<? super Boolean, Unit> function1) {
        this.robotScheduleListener = function1;
    }

    public final Function0<Unit> getActionExitObstruct() {
        return this.actionExitObstruct;
    }

    public final void setActionExitObstruct(Function0<Unit> function0) {
        this.actionExitObstruct = function0;
    }

    public final Function1<Boolean, Unit> getOnPlayFaceVideo() {
        return this.onPlayFaceVideo;
    }

    public final void setOnPlayFaceVideo(Function1<? super Boolean, Unit> function1) {
        this.onPlayFaceVideo = function1;
    }

    public final Function1<ScheduleContract.TriggerEvent, Unit> getScheduleEventLister() {
        return this.scheduleEventLister;
    }

    public final void setScheduleEventLister(Function1<? super ScheduleContract.TriggerEvent, Unit> function1) {
        this.scheduleEventLister = function1;
    }

    @Override // com.pudutech.bumblebee.presenter.motion_task.MotionContract.ViewInterface
    public void showMotionEvent(MotionContract.Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        boolean z = true;
        Pdlog.m3273d(this.TAG, "showMotionEvent " + event);
        if (this.robotScheduleStatus == ScheduleContract.TriggerEvent.AVOID) {
            Pdlog.m3273d(this.TAG, "showMotionEvent : robotScheduleStatus is AVOID ");
            return;
        }
        switch (event) {
            case MOVE_FORWARD:
                if (this.currentMoveEvent != event) {
                    PeripheralsSceneUtil.INSTANCE.showMoveForward();
                    String str = this.currentMovingText;
                    if (str != null && !StringsKt.isBlank(str)) {
                        z = false;
                    }
                    if (!z) {
                        showLedScreenString();
                        break;
                    } else {
                        PeripheralsSceneUtil.INSTANCE.resetLedScreen();
                        break;
                    }
                }
                break;
            case TURN_LEFT:
                if (this.currentMoveEvent != event) {
                    PeripheralsSceneUtil.INSTANCE.showMoveLeft();
                    break;
                }
                break;
            case BRAKE:
                if (this.currentMoveEvent != event) {
                    PeripheralsSceneUtil.INSTANCE.showMoveBrake();
                    break;
                }
                break;
            case TURN_RIGHT:
                if (this.currentMoveEvent != event) {
                    PeripheralsSceneUtil.INSTANCE.showMoveRight();
                    break;
                }
                break;
            case OBSTRUCTED:
                Pdlog.m3273d(this.TAG, "OBSTRUCTED TASK");
                if (!this.obstructedVoice1Playing && !this.obstructedVoice2Playing) {
                    Pdlog.m3273d(this.TAG, "OBSTRUCTED TASK , voice is not play , need play obstructedVoice1");
                    playVoice(getObstructedVoice1().withListener(this.obstructedVoice1Listener));
                } else {
                    this.isNeedRestartMovingVoice = false;
                }
                PeripheralsSceneUtil.INSTANCE.showMoveObstructed();
                break;
            case NO_OBSTRUCTED:
                Pdlog.m3273d(this.TAG, "NO_OBSTRUCTED TASK");
                this.obstructedVoicePlayCount = 0;
                if (this.isCanHandle) {
                    if (this.obstructedVoice1Playing || this.obstructedVoice2Playing) {
                        Pdlog.m3273d(this.TAG, "NO_OBSTRUCTED TASK , isNeedRestartMovingVoice = true");
                        this.isNeedRestartMovingVoice = true;
                    } else {
                        playMovingVoice();
                    }
                    if (this.robotScheduleStatus != ScheduleContract.TriggerEvent.AVOID) {
                        Function0<Unit> function0 = this.actionExitObstruct;
                        if (function0 != null) {
                            function0.invoke();
                        }
                        FaceVideoView faceVideoView = this.faceAnimationView;
                        if (faceVideoView != null) {
                            faceVideoView.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$showMotionEvent$1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    boolean z2;
                                    String str2;
                                    boolean z3;
                                    FaceVideoView faceVideoView2;
                                    FaceVideoView faceVideoView3;
                                    z2 = MotionEventHelper.this.isCanHandle;
                                    if (!z2) {
                                        str2 = MotionEventHelper.this.TAG;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("showMotionEvent: don't play animation, isCanHandle:");
                                        z3 = MotionEventHelper.this.isCanHandle;
                                        sb.append(z3);
                                        Pdlog.m3273d(str2, sb.toString());
                                        return;
                                    }
                                    if (MotionEventHelper.this.getOnTheWayAnimation() == null) {
                                        faceVideoView3 = MotionEventHelper.this.faceAnimationView;
                                        if (faceVideoView3 != null) {
                                            faceVideoView3.stopPlay();
                                            return;
                                        }
                                        return;
                                    }
                                    faceVideoView2 = MotionEventHelper.this.faceAnimationView;
                                    if (faceVideoView2 != null) {
                                        FaceVideoAnimation onTheWayAnimation = MotionEventHelper.this.getOnTheWayAnimation();
                                        if (onTheWayAnimation == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        faceVideoView2.playAnimation(onTheWayAnimation);
                                    }
                                }
                            });
                            break;
                        }
                    }
                }
                break;
        }
        this.currentMoveEvent = event;
    }

    public final void playMovingVoice() {
        Pdlog.m3273d(this.TAG, "playMovingVoice");
        this.isNeedRestartMovingVoice = false;
        if (this.robotScheduleStatus != ScheduleContract.TriggerEvent.AVOID) {
            if (this.movingLoopVoiceTask == null) {
                VoicePlayer.INSTANCE.stop();
                return;
            }
            playMovingVoiceIfNeed();
            if (this.isCanHandle) {
                PeripheralsSceneUtil.INSTANCE.showMoveForward();
            }
        }
    }

    private final void playMovingVoiceIfNeed() {
        VoiceTask voiceTask;
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("playMovingVoiceIfNeed,");
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        sb.append((voiceTaskWrapper == null || (voiceTask = voiceTaskWrapper.getVoiceTask()) == null) ? null : Long.valueOf(voiceTask.getLoopTime_ms()));
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper2 = this.movingLoopVoiceTask;
        if (voiceTaskWrapper2 != null) {
            if (this.moveVoicePlayingLedTask != null) {
                VoiceTask voiceTask2 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask2 != null) {
                    voiceTask2.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$playMovingVoiceIfNeed$$inlined$let$lambda$1
                        @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                        public void onStateChange(PlayEvent event) {
                            Function1<Boolean, Unit> moveVoicePlayingLedTask;
                            Intrinsics.checkParameterIsNotNull(event, "event");
                            if ((event == PlayEvent.STOP || event == PlayEvent.COMPLETION_ONCE) && (moveVoicePlayingLedTask = MotionEventHelper.this.getMoveVoicePlayingLedTask()) != null) {
                                moveVoicePlayingLedTask.invoke(false);
                            }
                        }
                    });
                }
                Function1<? super Boolean, Unit> function1 = this.moveVoicePlayingLedTask;
                if (function1 != null) {
                    function1.invoke(true);
                }
                TtsVoiceWrapperPlayer.INSTANCE.play(voiceTaskWrapper2, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$playMovingVoiceIfNeed$$inlined$let$lambda$2
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
                        Function1<Boolean, Unit> moveVoicePlayingLedTask;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        if ((it == AudioTrackUtils.AudioPlayEvent.COMPLETE || it == AudioTrackUtils.AudioPlayEvent.STOP) && (moveVoicePlayingLedTask = MotionEventHelper.this.getMoveVoicePlayingLedTask()) != null) {
                            moveVoicePlayingLedTask.invoke(false);
                        }
                    }
                });
                return;
            }
            TtsVoiceWrapperPlayer.play$default(TtsVoiceWrapperPlayer.INSTANCE, voiceTaskWrapper2, null, 2, null);
        }
    }

    public final void showLedScreenString() {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "showLedScreenString " + this.currentMovingText);
        String str = this.currentMovingText;
        if (str != null && !StringsKt.isBlank(str)) {
            z = false;
        }
        if (z) {
            return;
        }
        PeripheralsSceneUtil peripheralsSceneUtil = PeripheralsSceneUtil.INSTANCE;
        String str2 = this.currentMovingText;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        peripheralsSceneUtil.showLedString(str2);
    }

    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract.ViewInterface
    public void showScheduleEvent(ScheduleContract.TriggerEvent triggerEvent, ScheduleContract.Model model) {
        Intrinsics.checkParameterIsNotNull(triggerEvent, "triggerEvent");
        Pdlog.m3273d(this.TAG, "showScheduleEvent triggerEvent = " + triggerEvent + " , model = " + model);
        if (this.robotScheduleStatus == triggerEvent) {
            Pdlog.m3274e(this.TAG, "showScheduleEvent , current status not change");
            return;
        }
        this.robotScheduleStatus = triggerEvent;
        this.currentMoveEvent = MotionContract.Event.NO_OBSTRUCTED;
        Function1<? super ScheduleContract.TriggerEvent, Unit> function1 = this.scheduleEventLister;
        if (function1 != null) {
            function1.invoke(triggerEvent);
        }
        int i = WhenMappings.$EnumSwitchMapping$1[triggerEvent.ordinal()];
        if (i == 1) {
            showScheduleAnimation(true);
        } else {
            if (i != 2) {
                return;
            }
            showScheduleAnimation(false);
        }
    }

    private final void showScheduleAnimation(boolean isAvoid) {
        if (!this.isCanHandle) {
            Pdlog.m3273d(this.TAG, "showScheduleAnimation failed , canHandle is false");
            return;
        }
        Pdlog.m3273d(this.TAG, "showScheduleAnimation isAvoid = " + isAvoid);
        Function1<? super Boolean, Unit> function1 = this.robotScheduleListener;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(isAvoid));
        }
        if (isAvoid) {
            FaceVideoView faceVideoView = this.faceAnimationView;
            if (faceVideoView != null) {
                faceVideoView.playAnimation(setAnimationListener(SceneAnimationResources.INSTANCE.getSchedule()));
            }
            stopAllVoice();
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice20_1));
            PeripheralsSceneUtil.INSTANCE.showMoveSchedule();
            return;
        }
        FaceVideoAnimation faceVideoAnimation = this.onTheWayAnimation;
        if (faceVideoAnimation == null) {
            FaceVideoView faceVideoView2 = this.faceAnimationView;
            if (faceVideoView2 != null) {
                faceVideoView2.stopPlay();
            }
        } else {
            FaceVideoView faceVideoView3 = this.faceAnimationView;
            if (faceVideoView3 != null) {
                if (faceVideoAnimation == null) {
                    Intrinsics.throwNpe();
                }
                faceVideoView3.playAnimation(faceVideoAnimation);
            }
        }
        PeripheralsSceneUtil.INSTANCE.showMoveStopSchedule();
        if (this.movingLoopVoiceTask == null) {
            VoicePlayer.INSTANCE.stop();
        } else {
            playMovingVoiceIfNeed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAngryFaceAnimation() {
        FaceVideoView faceVideoView;
        if (this.isCanHandle && this.currentMoveEvent == MotionContract.Event.OBSTRUCTED && this.isCanPlayObstructedVideo && (faceVideoView = this.faceAnimationView) != null) {
            faceVideoView.playAnimation(setAnimationListener(SceneAnimationResources.INSTANCE.getAngry()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playVoice(VoiceTask voiceTask) {
        if (!this.isCanHandle) {
            Pdlog.m3274e(this.TAG, "playVoice failed , isCanHandle is false");
        } else {
            VoicePlayer.INSTANCE.play(voiceTask);
        }
    }

    public final void setCanHandleMovingEvent(boolean b) {
        Pdlog.m3273d(this.TAG, "setCanHandleMovingEvent");
        if (b != this.isCanHandle) {
            this.isCanHandle = b;
            Pdlog.m3273d(this.TAG, "setCanHandleMovingEvent success ,  " + this.isCanHandle);
            if (this.isCanHandle) {
                playMovingVoiceIfNeed();
            } else {
                stopAllVoice();
                this.robotScheduleStatus = ScheduleContract.TriggerEvent.NORMAL;
                Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.IDLE);
            }
        }
        this.currentMoveEvent = (MotionContract.Event) null;
    }

    private final void stopAllVoice() {
        Pdlog.m3273d(this.TAG, "stopAllVoice");
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        if (voiceTaskWrapper != null) {
            TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper);
        }
        VoicePlayer.INSTANCE.stop(getObstructedVoice1());
        VoicePlayer.INSTANCE.stop(getObstructedVoice2());
    }

    public final void bind(TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask, FaceVideoView faceAnimationView, Function1<? super ScheduleContract.TriggerEvent, Unit> scheduleEventLister) {
        Intrinsics.checkParameterIsNotNull(faceAnimationView, "faceAnimationView");
        Pdlog.m3273d(this.TAG, "bind");
        this.movingLoopVoiceTask = movingLoopVoiceTask;
        this.faceAnimationView = faceAnimationView;
        this.scheduleEventLister = scheduleEventLister;
        getMotionPresenter().replaceView((MotionContract.ViewInterface) this);
        getSchedulePresenter().replaceView(this);
    }

    public final void unBind() {
        Pdlog.m3273d(this.TAG, "unBind");
        this.faceAnimationView = (FaceVideoView) null;
        MotionEventHelper motionEventHelper = this;
        getMotionPresenter().removeView(motionEventHelper);
        getSchedulePresenter().removeView(motionEventHelper);
        this.scheduleEventLister = (Function1) null;
        stopAllVoice();
    }

    public final void stopMovingVoice() {
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        if (voiceTaskWrapper != null) {
            TtsVoiceWrapperPlayer.INSTANCE.stop(voiceTaskWrapper);
        }
        this.movingLoopVoiceTask = (TtsVoiceWrapperPlayer.VoiceTaskWrapper) null;
    }

    public final void setCanPlayObstructedVideo(boolean isCan) {
        this.isCanPlayObstructedVideo = isCan;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FaceVideoAnimation setAnimationListener(FaceVideoAnimation animation) {
        Function1<? super Boolean, Unit> function1 = this.onPlayFaceVideo;
        if (function1 != null) {
            function1.invoke(true);
        }
        return animation.setOnAnimationFinish(this.onFaceAnimationFinish).setOnFaceVideoStopListener(this.onFaceAnimationStop);
    }
}
