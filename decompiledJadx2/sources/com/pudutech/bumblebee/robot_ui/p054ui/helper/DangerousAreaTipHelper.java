package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.felhr.usbserial.FTDISerialDevice;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.resources.voice.VoiceItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DangerousAreaTipHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002\n\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0016J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u0019J\u0006\u0010\"\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BaseLifecycleObserver;", "()V", "TAG", "", "WHAT_VOICE", "", "isNeedMove", "", "mDefOnSpeedLimitAreaListener", "com/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper$mDefOnSpeedLimitAreaListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper$mDefOnSpeedLimitAreaListener$1;", "mHandler", "Landroid/os/Handler;", "mIntervalTime", "", "mIsInArea", "mRobotState", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "mVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "stateListener", "com/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper$stateListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper$stateListener$1;", "bindLifecycle", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "create", "destroy", "onStateChange", "event", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "startPlay", "stopPlay", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DangerousAreaTipHelper implements BaseLifecycleObserver {
    private boolean isNeedMove;
    private Handler mHandler;
    private boolean mIsInArea;
    private final String TAG = "DangerousAreaTipHelper";
    private long mIntervalTime = SolicitService.CAMERA_OPEN_TIME_OUT;
    private VoiceTask mVoiceTask = new VoiceTask(-1, VoiceItem.voice49_1);
    private final int WHAT_VOICE = 102;
    private final DangerousAreaTipHelper$mDefOnSpeedLimitAreaListener$1 mDefOnSpeedLimitAreaListener = new Function2<Boolean, Double, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.DangerousAreaTipHelper$mDefOnSpeedLimitAreaListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Double d) {
            invoke(bool.booleanValue(), d.doubleValue());
            return Unit.INSTANCE;
        }

        public void invoke(boolean isInArea, double maxLimitSpeed) {
            DangerousAreaTipHelper.this.mIsInArea = isInArea;
            if (DangerousAreaTipHelper.this.mIsInArea) {
                DangerousAreaTipHelper.this.startPlay();
            } else {
                DangerousAreaTipHelper.this.stopPlay();
            }
            Pdlog.m3273d(DangerousAreaTipHelper.this.TAG, "mDefOnSpeedLimitAreaListener mIsInArea = " + DangerousAreaTipHelper.this.mIsInArea + "  isInArea =" + isInArea);
        }
    };
    private RobotState mRobotState = RobotState.Idle;
    private DangerousAreaTipHelper$stateListener$1 stateListener = new DangerousAreaTipHelper$stateListener$1(this);

    /* JADX WARN: Type inference failed for: r0v4, types: [com.pudutech.bumblebee.robot_ui.ui.helper.DangerousAreaTipHelper$mDefOnSpeedLimitAreaListener$1] */
    public DangerousAreaTipHelper() {
        if (Constans.INSTANCE.isSpeedLimitArea()) {
            this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.DangerousAreaTipHelper.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg == null || msg.what != DangerousAreaTipHelper.this.WHAT_VOICE) {
                        return;
                    }
                    VoicePlayer.INSTANCE.play(DangerousAreaTipHelper.this.mVoiceTask);
                    if (DangerousAreaTipHelper.this.mIsInArea) {
                        sendEmptyMessageDelayed(DangerousAreaTipHelper.this.WHAT_VOICE, DangerousAreaTipHelper.this.mIntervalTime + FTDISerialDevice.FTDI_BAUDRATE_1200);
                    }
                    Pdlog.m3273d(DangerousAreaTipHelper.this.TAG, "handler sendEmptyMessageDelayed = " + DangerousAreaTipHelper.this.mIsInArea);
                }
            };
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {
        BaseLifecycleObserver.DefaultImpls.any(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        BaseLifecycleObserver.DefaultImpls.pause(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        BaseLifecycleObserver.DefaultImpls.resume(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        BaseLifecycleObserver.DefaultImpls.start(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        BaseLifecycleObserver.DefaultImpls.stop(this);
    }

    public final void bindLifecycle(AppCompatActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (Constans.INSTANCE.isSpeedLimitArea()) {
            activity.getLifecycle().addObserver(this);
        } else {
            Pdlog.m3273d(this.TAG, "bindLifecycle close speedLimit");
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void create() {
        BaseLifecycleObserver.DefaultImpls.create(this);
        this.mIntervalTime = Constans.INSTANCE.getSpeedLimitTime() * 1000;
        RobotMoveManager.INSTANCE.addOnSpeedLimitAreaChangeListener(this.mDefOnSpeedLimitAreaListener);
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.addOnStateChangeListeners(this.stateListener);
        }
        this.mIsInArea = RobotMoveManager.INSTANCE.isDangerArea();
        startPlay();
        Pdlog.m3273d(this.TAG, "create() mIntervalTime = " + this.mIntervalTime + " mIsInArea = " + this.mIsInArea);
    }

    public final void onStateChange(PlayEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event == PlayEvent.PLAYING) {
            stopPlay();
            Pdlog.m3273d(this.TAG, "onStateChange stopPlay " + event.name());
            return;
        }
        if (event == PlayEvent.COMPLETION_ONCE || event == PlayEvent.STOP) {
            if (!this.isNeedMove) {
                startPlay();
                Pdlog.m3273d(this.TAG, "onStateChange startPlay " + event.name());
            }
            Pdlog.m3273d(this.TAG, "onStateChange startPlay isNeedMove " + this.isNeedMove);
        }
    }

    public final void startPlay() {
        if (!this.mIsInArea) {
            Pdlog.m3273d(this.TAG, "startPlay() close mIsInArea = " + this.mIsInArea);
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(this.WHAT_VOICE)) {
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.removeMessages(this.WHAT_VOICE);
            }
            Pdlog.m3273d(this.TAG, "startPlay() removeMessages WHAT_VOICE = " + this.WHAT_VOICE);
        }
        Handler handler3 = this.mHandler;
        if (handler3 != null) {
            handler3.sendEmptyMessageDelayed(this.WHAT_VOICE, this.mIntervalTime);
        }
        Pdlog.m3273d(this.TAG, "startPlay() VoicePlayer.play");
    }

    public final void stopPlay() {
        Handler handler = this.mHandler;
        if (handler == null || !handler.hasMessages(this.WHAT_VOICE)) {
            return;
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeMessages(this.WHAT_VOICE);
        }
        Pdlog.m3273d(this.TAG, "stopPlay() removeMessages WHAT_VOICE = " + this.WHAT_VOICE);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void destroy() {
        BaseLifecycleObserver.DefaultImpls.destroy(this);
        stopPlay();
        VoicePlayer.INSTANCE.stop(this.mVoiceTask);
        this.mHandler = (Handler) null;
        RobotMoveManager.INSTANCE.removeOnSpeedLimitAreaChangeListener(this.mDefOnSpeedLimitAreaListener);
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.removeOnStateChangeListeners(this.stateListener);
        }
        Pdlog.m3273d(this.TAG, "destroy()");
    }
}
