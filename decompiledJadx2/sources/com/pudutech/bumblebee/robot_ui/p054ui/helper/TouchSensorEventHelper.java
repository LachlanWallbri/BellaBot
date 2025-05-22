package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.app.Dialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorPresenter;
import com.pudutech.bumblebee.robot_ui.advertise.AdverConfig;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FaceAnimationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.resources.voice.VoiceItem;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: TouchSensorEventHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b*\u0002\u001d \u0018\u00002\u00020\u0001:\u0001OB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010=\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\r2\u0006\u0010>\u001a\u00020\tJ\u000e\u0010=\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010?\u001a\u00020\u00172\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010@\u001a\u00020\u00172\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020\u00172\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010D\u001a\u00020\u00172\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010E\u001a\u00020\u00172\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020B2\u0006\u0010A\u001a\u00020BH\u0002J\u0018\u0010J\u001a\u00020\u00172\u0006\u0010K\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007J\u0018\u0010L\u001a\u00020\u00172\u0006\u0010*\u001a\u00020'2\u0006\u0010,\u001a\u00020+H\u0016J\u0006\u0010M\u001a\u00020\u0017J\u0006\u0010N\u001a\u00020\u0017R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R(\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bRL\u0010%\u001a4\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110+¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0017\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R(\u00101\u001a\u0010\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0019\"\u0004\b4\u0010\u001bR\u001b\u00105\u001a\u0002068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b7\u00108R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "canHandle", "", "dialogActivity", "Landroidx/fragment/app/FragmentActivity;", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "faceDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FaceAnimationDialog;", "faceState", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$State;", "getFaceState", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$State;", "setFaceState", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$State;)V", "hasOtherAni", "onAnimationShowListener", "Lkotlin/Function1;", "", "getOnAnimationShowListener", "()Lkotlin/jvm/functions/Function1;", "setOnAnimationShowListener", "(Lkotlin/jvm/functions/Function1;)V", "onFaceAnimationFinish", "com/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$onFaceAnimationFinish$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$onFaceAnimationFinish$1;", "onFaceClickListener", "com/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$onFaceClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$onFaceClickListener$1;", "onShowFaceStateListener", "getOnShowFaceStateListener", "setOnShowFaceStateListener", "onTouchSensorPlaceListener", "Lkotlin/Function2;", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "Lkotlin/ParameterName;", "name", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "getOnTouchSensorPlaceListener", "()Lkotlin/jvm/functions/Function2;", "setOnTouchSensorPlaceListener", "(Lkotlin/jvm/functions/Function2;)V", "onVoiceStateListener", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "getOnVoiceStateListener", "setOnVoiceStateListener", "touchSensorPresenter", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorPresenter;", "getTouchSensorPresenter", "()Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorPresenter;", "touchSensorPresenter$delegate", "Lkotlin/Lazy;", "voicePlayerListener", "Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;", "bindPresenter", "activity", "playAngerAnimation", "playFaceIfNeed", "animation", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "playHeadHappyAnimation", "playLeftEarHappyAnimation", "playRightEarHappyAnimation", "sayTouched", "item", "Lcom/pudutech/resources/voice/VoiceItem;", "setAnimationListener", "setCanHandle", "can", "showTouched", "stopCurrentAnimation", "unBindPresent", "State", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TouchSensorEventHelper implements TouchSensorContract.ViewInterface {
    private boolean canHandle;
    private FragmentActivity dialogActivity;
    private FaceVideoView faceAnimationView;
    private FaceAnimationDialog faceDialog;
    private boolean hasOtherAni;
    private Function1<? super Boolean, Unit> onAnimationShowListener;
    private Function1<? super State, Unit> onShowFaceStateListener;
    private Function2<? super TouchSensorContract.Place, ? super TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private Function1<? super PlayEvent, Unit> onVoiceStateListener;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: touchSensorPresenter$delegate, reason: from kotlin metadata */
    private final Lazy touchSensorPresenter = LazyKt.lazy(new Function0<TouchSensorPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TouchSensorEventHelper$touchSensorPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TouchSensorPresenter invoke() {
            TouchSensorPresenter touchSensorPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(TouchSensorPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(TouchSensorPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                touchSensorPresenter = new TouchSensorPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(TouchSensorPresenter.class).toString(), touchSensorPresenter);
            } else {
                if (!(basePresenterInterface instanceof TouchSensorPresenter)) {
                    basePresenterInterface = null;
                }
                touchSensorPresenter = (TouchSensorPresenter) basePresenterInterface;
            }
            if (touchSensorPresenter != null) {
                return touchSensorPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorPresenter");
        }
    });
    private State faceState = State.START_FACE;
    private final Listener voicePlayerListener = new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TouchSensorEventHelper$voicePlayerListener$1
        @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
        public void onStateChange(PlayEvent event) {
            String str;
            TouchSensorPresenter touchSensorPresenter;
            TouchSensorPresenter touchSensorPresenter2;
            Intrinsics.checkParameterIsNotNull(event, "event");
            str = TouchSensorEventHelper.this.TAG;
            Pdlog.m3273d(str, "playBtnVoice voice event=" + event);
            Function1<PlayEvent, Unit> onVoiceStateListener = TouchSensorEventHelper.this.getOnVoiceStateListener();
            if (onVoiceStateListener != null) {
                onVoiceStateListener.invoke(event);
            }
            if (event == PlayEvent.PLAYING) {
                touchSensorPresenter2 = TouchSensorEventHelper.this.getTouchSensorPresenter();
                touchSensorPresenter2.actionIgnoreWhenSaying(true);
            } else if (event == PlayEvent.COMPLETION_ONCE || event == PlayEvent.STOP) {
                touchSensorPresenter = TouchSensorEventHelper.this.getTouchSensorPresenter();
                touchSensorPresenter.actionIgnoreWhenSaying(!Constans.INSTANCE.getTouchSwitch());
            }
        }
    };
    private final TouchSensorEventHelper$onFaceClickListener$1 onFaceClickListener = new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TouchSensorEventHelper$onFaceClickListener$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public Boolean invoke() {
            String str;
            boolean z;
            boolean z2;
            boolean z3;
            String str2;
            if (AdverConfig.INSTANCE.isCruiseAdver()) {
                str2 = TouchSensorEventHelper.this.TAG;
                Pdlog.m3273d(str2, "onFaceClickListener adver no click");
                return true;
            }
            str = TouchSensorEventHelper.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceClickListener ");
            z = TouchSensorEventHelper.this.canHandle;
            sb.append(z);
            Pdlog.m3273d(str, sb.toString());
            z2 = TouchSensorEventHelper.this.canHandle;
            if (!z2) {
                return false;
            }
            z3 = TouchSensorEventHelper.this.hasOtherAni;
            if (z3) {
                return false;
            }
            TouchSensorEventHelper.this.stopCurrentAnimation();
            TouchSensorEventHelper.this.setFaceState(TouchSensorEventHelper.State.START_FACE);
            Function1<Boolean, Unit> onAnimationShowListener = TouchSensorEventHelper.this.getOnAnimationShowListener();
            if (onAnimationShowListener != null) {
                onAnimationShowListener.invoke(false);
            }
            Function1<TouchSensorEventHelper.State, Unit> onShowFaceStateListener = TouchSensorEventHelper.this.getOnShowFaceStateListener();
            if (onShowFaceStateListener != null) {
                onShowFaceStateListener.invoke(TouchSensorEventHelper.State.START_FACE);
            }
            return true;
        }
    };
    private final TouchSensorEventHelper$onFaceAnimationFinish$1 onFaceAnimationFinish = new Function1<FaceVideoAnimation, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TouchSensorEventHelper$onFaceAnimationFinish$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FaceVideoAnimation faceVideoAnimation) {
            invoke2(faceVideoAnimation);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(FaceVideoAnimation animation) {
            String str;
            boolean z;
            boolean z2;
            Intrinsics.checkParameterIsNotNull(animation, "animation");
            str = TouchSensorEventHelper.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onAnimationFinish ");
            z = TouchSensorEventHelper.this.canHandle;
            sb.append(z);
            Pdlog.m3273d(str, sb.toString());
            z2 = TouchSensorEventHelper.this.canHandle;
            if (z2) {
                TouchSensorEventHelper.this.setFaceState(TouchSensorEventHelper.State.FINISH_FACE);
                Function1<Boolean, Unit> onAnimationShowListener = TouchSensorEventHelper.this.getOnAnimationShowListener();
                if (onAnimationShowListener != null) {
                    onAnimationShowListener.invoke(false);
                }
                Function1<TouchSensorEventHelper.State, Unit> onShowFaceStateListener = TouchSensorEventHelper.this.getOnShowFaceStateListener();
                if (onShowFaceStateListener != null) {
                    onShowFaceStateListener.invoke(TouchSensorEventHelper.State.FINISH_FACE);
                }
            }
        }
    };

    /* compiled from: TouchSensorEventHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper$State;", "", "(Ljava/lang/String;I)V", "START_FACE", "FACE_DOING", "FINISH_FACE", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum State {
        START_FACE,
        FACE_DOING,
        FINISH_FACE
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TouchSensorContract.Place.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[TouchSensorContract.Place.LEFT_EAR.ordinal()] = 1;
            $EnumSwitchMapping$0[TouchSensorContract.Place.RIGHT_EAR.ordinal()] = 2;
            $EnumSwitchMapping$0[TouchSensorContract.Place.HEAD.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$1[TouchSensorContract.Event.ANGER.ordinal()] = 1;
            $EnumSwitchMapping$1[TouchSensorContract.Event.ANGER_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$2[TouchSensorContract.Event.HAPPY.ordinal()] = 1;
            $EnumSwitchMapping$2[TouchSensorContract.Event.HAPPY_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$2[TouchSensorContract.Event.HAPPY_LEVEL3.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$3[TouchSensorContract.Event.HAPPY.ordinal()] = 1;
            $EnumSwitchMapping$3[TouchSensorContract.Event.HAPPY_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$3[TouchSensorContract.Event.HAPPY_LEVEL3.ordinal()] = 3;
            $EnumSwitchMapping$4 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$4[TouchSensorContract.Event.HAPPY.ordinal()] = 1;
            $EnumSwitchMapping$4[TouchSensorContract.Event.HAPPY_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$4[TouchSensorContract.Event.HAPPY_LEVEL3.ordinal()] = 3;
        }
    }

    public final TouchSensorPresenter getTouchSensorPresenter() {
        return (TouchSensorPresenter) this.touchSensorPresenter.getValue();
    }

    public final Function1<Boolean, Unit> getOnAnimationShowListener() {
        return this.onAnimationShowListener;
    }

    public final void setOnAnimationShowListener(Function1<? super Boolean, Unit> function1) {
        this.onAnimationShowListener = function1;
    }

    public final Function1<State, Unit> getOnShowFaceStateListener() {
        return this.onShowFaceStateListener;
    }

    public final void setOnShowFaceStateListener(Function1<? super State, Unit> function1) {
        this.onShowFaceStateListener = function1;
    }

    public final Function1<PlayEvent, Unit> getOnVoiceStateListener() {
        return this.onVoiceStateListener;
    }

    public final void setOnVoiceStateListener(Function1<? super PlayEvent, Unit> function1) {
        this.onVoiceStateListener = function1;
    }

    public final State getFaceState() {
        return this.faceState;
    }

    public final void setFaceState(State state) {
        Intrinsics.checkParameterIsNotNull(state, "<set-?>");
        this.faceState = state;
    }

    public final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> getOnTouchSensorPlaceListener() {
        return this.onTouchSensorPlaceListener;
    }

    public final void setOnTouchSensorPlaceListener(Function2<? super TouchSensorContract.Place, ? super TouchSensorContract.Event, Unit> function2) {
        this.onTouchSensorPlaceListener = function2;
    }

    public final void stopCurrentAnimation() {
        Dialog dialog;
        FaceAnimationDialog faceAnimationDialog;
        Pdlog.m3273d(this.TAG, "stopCurrentAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
        FaceAnimationDialog faceAnimationDialog2 = this.faceDialog;
        if (faceAnimationDialog2 != null && (dialog = faceAnimationDialog2.getDialog()) != null && dialog.isShowing() && (faceAnimationDialog = this.faceDialog) != null) {
            faceAnimationDialog.dismissAllowingStateLoss();
        }
        VoicePlayer.INSTANCE.stop();
        Function1<? super PlayEvent, Unit> function1 = this.onVoiceStateListener;
        if (function1 != null) {
            function1.invoke(PlayEvent.STOP);
        }
    }

    public static /* synthetic */ void setCanHandle$default(TouchSensorEventHelper touchSensorEventHelper, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        touchSensorEventHelper.setCanHandle(z, z2);
    }

    public final void setCanHandle(boolean can, boolean hasOtherAni) {
        this.canHandle = can;
        this.hasOtherAni = hasOtherAni;
        if (this.canHandle) {
            getTouchSensorPresenter().actionResetCount();
        }
    }

    public final void bindPresenter(FaceVideoView faceAnimationView) {
        Intrinsics.checkParameterIsNotNull(faceAnimationView, "faceAnimationView");
        Pdlog.m3273d(this.TAG, "bindPresenter");
        this.faceAnimationView = faceAnimationView;
        getTouchSensorPresenter().replaceView((TouchSensorContract.ViewInterface) this);
        getTouchSensorPresenter().actionIgnoreWhenSaying(!Constans.INSTANCE.getTouchSwitch());
    }

    public final void bindPresenter(FaceAnimationDialog faceDialog, FragmentActivity activity) {
        Intrinsics.checkParameterIsNotNull(faceDialog, "faceDialog");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Pdlog.m3273d(this.TAG, "bindPresenter");
        this.faceDialog = faceDialog;
        this.dialogActivity = activity;
        getTouchSensorPresenter().replaceView((TouchSensorContract.ViewInterface) this);
        getTouchSensorPresenter().actionIgnoreWhenSaying(!Constans.INSTANCE.getTouchSwitch());
    }

    public final void unBindPresent() {
        Pdlog.m3273d(this.TAG, "unBindPresent");
        this.canHandle = false;
        getTouchSensorPresenter().removeView(this);
        getTouchSensorPresenter().cleanCache();
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView != null) {
            faceVideoView.setOnPlayFinishListener((Function0) null);
        }
        this.faceAnimationView = (FaceVideoView) null;
        Function1 function1 = (Function1) null;
        this.onAnimationShowListener = function1;
        this.onShowFaceStateListener = function1;
        this.onVoiceStateListener = function1;
        this.onTouchSensorPlaceListener = (Function2) null;
        this.faceDialog = (FaceAnimationDialog) null;
        this.dialogActivity = (FragmentActivity) null;
    }

    @Override // com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract.ViewInterface
    public void sayTouched(VoiceItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Pdlog.m3273d(this.TAG, "sayTouched " + item);
        if (!this.canHandle) {
            Pdlog.m3274e(this.TAG, "sayTouched failed , canHandle is failed");
        } else {
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, item).withListener(this.voicePlayerListener));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract.ViewInterface
    public void showTouched(TouchSensorContract.Place place, TouchSensorContract.Event event) {
        Intrinsics.checkParameterIsNotNull(place, "place");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showTouched place = " + place + " ; event = " + event);
        if (!this.canHandle) {
            Pdlog.m3274e(this.TAG, "showTouched failed , canHandle is failed");
            return;
        }
        Function2<? super TouchSensorContract.Place, ? super TouchSensorContract.Event, Unit> function2 = this.onTouchSensorPlaceListener;
        if (function2 != null) {
            function2.invoke(place, event);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[place.ordinal()];
        if (i == 1) {
            playLeftEarHappyAnimation(event);
        } else if (i == 2) {
            playRightEarHappyAnimation(event);
        } else if (i == 3) {
            playHeadHappyAnimation(event);
        }
        playAngerAnimation(event);
    }

    private final void playFaceIfNeed(FaceVideoAnimation animation) {
        if (!this.canHandle) {
            Pdlog.m3274e(this.TAG, "playFaceIfNeed failed , canHandle is failed");
            return;
        }
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView != null) {
            if (faceVideoView.getContext() == null) {
                Pdlog.m3274e(this.TAG, "playFaceIfNeed failed , faceAnimationView context is null");
                return;
            }
            this.faceState = State.FACE_DOING;
            Function1<? super Boolean, Unit> function1 = this.onAnimationShowListener;
            if (function1 != null) {
                function1.invoke(true);
            }
            Function1<? super State, Unit> function12 = this.onShowFaceStateListener;
            if (function12 != null) {
                function12.invoke(State.FACE_DOING);
            }
            faceVideoView.playAnimation(animation);
        }
        FaceAnimationDialog faceAnimationDialog = this.faceDialog;
        if (faceAnimationDialog != null) {
            FragmentActivity fragmentActivity = this.dialogActivity;
            if (fragmentActivity != null) {
                if (fragmentActivity == null) {
                    Intrinsics.throwNpe();
                }
                if (!fragmentActivity.isDestroyed()) {
                    this.faceState = State.FACE_DOING;
                    Function1<? super Boolean, Unit> function13 = this.onAnimationShowListener;
                    if (function13 != null) {
                        function13.invoke(true);
                    }
                    Function1<? super State, Unit> function14 = this.onShowFaceStateListener;
                    if (function14 != null) {
                        function14.invoke(State.FACE_DOING);
                    }
                    Dialog dialog = faceAnimationDialog.getDialog();
                    if (dialog == null || !dialog.isShowing()) {
                        FragmentActivity fragmentActivity2 = this.dialogActivity;
                        if (fragmentActivity2 == null) {
                            Intrinsics.throwNpe();
                        }
                        FragmentManager supportFragmentManager = fragmentActivity2.getSupportFragmentManager();
                        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "dialogActivity!!.supportFragmentManager");
                        faceAnimationDialog.show(supportFragmentManager, "face_animation_dialog");
                    }
                    faceAnimationDialog.playAnimation(animation);
                    return;
                }
            }
            Pdlog.m3274e(this.TAG, "playFaceIfNeed failed , dialogActivity not resume");
        }
    }

    private final void playAngerAnimation(TouchSensorContract.Event event) {
        Pdlog.m3273d(this.TAG, "playAngerAnimation");
        PeripheralsSceneUtil.INSTANCE.showTouchAngry(event);
        int i = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "touch angery");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getAngry()));
        } else {
            if (i != 2) {
                return;
            }
            Pdlog.m3273d(this.TAG, "touch angery2");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getVeryAngry()));
        }
    }

    private final void playLeftEarHappyAnimation(TouchSensorContract.Event event) {
        PeripheralsSceneUtil.INSTANCE.showEarTouchEvent(true, event);
        int i = WhenMappings.$EnumSwitchMapping$2[event.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "left ear happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getLittleHappy()));
        } else if (i == 2) {
            Pdlog.m3273d(this.TAG, "left ear very happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getTouchLeftEar()));
        } else {
            if (i != 3) {
                return;
            }
            Pdlog.m3273d(this.TAG, "left ear very happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getVeryHappy()));
        }
    }

    private final void playRightEarHappyAnimation(TouchSensorContract.Event event) {
        PeripheralsSceneUtil.INSTANCE.showEarTouchEvent(false, event);
        int i = WhenMappings.$EnumSwitchMapping$3[event.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "right ear happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getLittleHappy()));
        } else if (i == 2) {
            Pdlog.m3273d(this.TAG, "right ear very happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getTouchRightEar()));
        } else {
            if (i != 3) {
                return;
            }
            Pdlog.m3273d(this.TAG, "right ear very happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getVeryHappy()));
        }
    }

    private final void playHeadHappyAnimation(TouchSensorContract.Event event) {
        PeripheralsSceneUtil.INSTANCE.showHeadTouchEvent(event);
        int i = WhenMappings.$EnumSwitchMapping$4[event.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "head happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getLittleHappy()));
        } else if (i == 2) {
            Pdlog.m3273d(this.TAG, "head very happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getLittleHappy()));
        } else {
            if (i != 3) {
                return;
            }
            Pdlog.m3273d(this.TAG, "head very happy");
            playFaceIfNeed(setAnimationListener(SceneAnimationResources.INSTANCE.getVeryHappy()));
        }
    }

    private final FaceVideoAnimation setAnimationListener(FaceVideoAnimation animation) {
        return animation.setOnAnimationClick(this.onFaceClickListener).setOnAnimationFinish(this.onFaceAnimationFinish);
    }
}
