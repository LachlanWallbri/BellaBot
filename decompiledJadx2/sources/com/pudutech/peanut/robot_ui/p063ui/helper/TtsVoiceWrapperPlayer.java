package com.pudutech.peanut.robot_ui.p063ui.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.robot.module.voice.data.VoicePlayTask;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsVoiceWrapperPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001f B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\rJ5\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152%\b\u0002\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fJ\b\u0010\u0019\u001a\u00020\rH\u0002J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0015J\b\u0010\u001e\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer;", "", "()V", "KEY_PLAY_CRUISES_TTS", "", "TAG", "", "intervalTime", "", "mainHandler", "Landroid/os/Handler;", "onVoicePlayerPlay", "Lkotlin/Function0;", "", "ttsPlayerListener", "Lkotlin/Function1;", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "Lkotlin/ParameterName;", "name", "event", "voiceTaskWrapper", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "init", "play", "listener", "release", "startIntervalVoice", TransferTable.COLUMN_KEY, "stop", "it", "stopIntervalTask", "PlayType", "VoiceTaskWrapper", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsVoiceWrapperPlayer {
    private static final int KEY_PLAY_CRUISES_TTS = 1001;
    private static final String TAG = "TtsVoiceWrapperPlayer";
    private static Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> ttsPlayerListener;
    private static VoiceTaskWrapper voiceTaskWrapper;
    public static final TtsVoiceWrapperPlayer INSTANCE = new TtsVoiceWrapperPlayer();
    private static final Function0<Unit> onVoicePlayerPlay = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceWrapperPlayer$onVoicePlayerPlay$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            TtsVoiceWrapperPlayer.INSTANCE.release();
        }
    };
    private static long intervalTime = -1;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            if (message.what != 1001) {
                return true;
            }
            TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                    invoke2(audioPlayEvent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AudioTrackUtils.AudioPlayEvent event) {
                    Function1 function1;
                    Intrinsics.checkParameterIsNotNull(event, "event");
                    TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer = TtsVoiceWrapperPlayer.INSTANCE;
                    function1 = TtsVoiceWrapperPlayer.ttsPlayerListener;
                    if (function1 != null) {
                    }
                    if (event == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                        TtsVoiceWrapperPlayer.INSTANCE.startIntervalVoice(1001);
                    }
                }
            });
            return true;
        }
    });

    /* compiled from: TtsVoiceWrapperPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "", "(Ljava/lang/String;I)V", "NORMAL", "CRUISE", "DELIVER_ARRIVED", "GREETER_GUIDE", "GREETER_DONE", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum PlayType {
        NORMAL,
        CRUISE,
        DELIVER_ARRIVED,
        GREETER_GUIDE,
        GREETER_DONE
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
        }
    }

    public final void init() {
    }

    private TtsVoiceWrapperPlayer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void play$default(TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer, VoiceTaskWrapper voiceTaskWrapper2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceWrapperPlayer.play(voiceTaskWrapper2, function1);
    }

    public final void play(VoiceTaskWrapper voiceTaskWrapper2, Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(voiceTaskWrapper2, "voiceTaskWrapper");
        ttsPlayerListener = listener;
        if (voiceTaskWrapper2.getType() == PlayType.NORMAL) {
            TtsVoiceHelper.INSTANCE.stopCruiseTts();
            return;
        }
        if (voiceTaskWrapper2.getType() == PlayType.CRUISE) {
            int i = WhenMappings.$EnumSwitchMapping$0[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE).ordinal()];
            if (i == 1) {
                Pdlog.m3273d(TAG, "play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                return;
            } else {
                if (i != 2) {
                    return;
                }
                Pdlog.m3273d(TAG, "play TtsVoice");
                release();
                voiceTaskWrapper = voiceTaskWrapper2;
                return;
            }
        }
        if (voiceTaskWrapper2.getType() == PlayType.DELIVER_ARRIVED) {
            int i2 = WhenMappings.$EnumSwitchMapping$1[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE).ordinal()];
            if (i2 == 1) {
                Pdlog.m3273d(TAG, "DELIVER_ARRIVED : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
            } else {
                if (i2 != 2) {
                    return;
                }
                Pdlog.m3273d(TAG, "play TtsVoice");
                release();
                TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE, listener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startIntervalVoice(int key) {
        Pdlog.m3273d(TAG, "startIntervalVoice " + key);
        if (intervalTime <= 0) {
            Pdlog.m3274e(TAG, "startIntervalVoice key = " + key + "  failed, time is < 0");
            return;
        }
        stopIntervalTask();
        mainHandler.sendEmptyMessageDelayed(key, intervalTime);
    }

    private final void stopIntervalTask() {
        mainHandler.removeMessages(1001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release() {
        ttsPlayerListener = (Function1) null;
        intervalTime = -1L;
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        stopIntervalTask();
        voiceTaskWrapper = (VoiceTaskWrapper) null;
    }

    public final void stop(VoiceTaskWrapper it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        it.getVoiceTask();
        release();
    }

    /* compiled from: TtsVoiceWrapperPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "", "voiceTask", "Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "type", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "(Lcom/pudutech/robot/module/voice/data/VoicePlayTask;Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;)V", "getType", "()Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "setType", "(Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;)V", "getVoiceTask", "()Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "setVoiceTask", "(Lcom/pudutech/robot/module/voice/data/VoicePlayTask;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class VoiceTaskWrapper {
        private PlayType type;
        private VoicePlayTask voiceTask;

        public VoiceTaskWrapper() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ VoiceTaskWrapper copy$default(VoiceTaskWrapper voiceTaskWrapper, VoicePlayTask voicePlayTask, PlayType playType, int i, Object obj) {
            if ((i & 1) != 0) {
                voicePlayTask = voiceTaskWrapper.voiceTask;
            }
            if ((i & 2) != 0) {
                playType = voiceTaskWrapper.type;
            }
            return voiceTaskWrapper.copy(voicePlayTask, playType);
        }

        /* renamed from: component1, reason: from getter */
        public final VoicePlayTask getVoiceTask() {
            return this.voiceTask;
        }

        /* renamed from: component2, reason: from getter */
        public final PlayType getType() {
            return this.type;
        }

        public final VoiceTaskWrapper copy(VoicePlayTask voiceTask, PlayType type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new VoiceTaskWrapper(voiceTask, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VoiceTaskWrapper)) {
                return false;
            }
            VoiceTaskWrapper voiceTaskWrapper = (VoiceTaskWrapper) other;
            return Intrinsics.areEqual(this.voiceTask, voiceTaskWrapper.voiceTask) && Intrinsics.areEqual(this.type, voiceTaskWrapper.type);
        }

        public int hashCode() {
            VoicePlayTask voicePlayTask = this.voiceTask;
            int hashCode = (voicePlayTask != null ? voicePlayTask.hashCode() : 0) * 31;
            PlayType playType = this.type;
            return hashCode + (playType != null ? playType.hashCode() : 0);
        }

        public String toString() {
            return "VoiceTaskWrapper(voiceTask=" + this.voiceTask + ", type=" + this.type + ")";
        }

        public VoiceTaskWrapper(VoicePlayTask voicePlayTask, PlayType type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.voiceTask = voicePlayTask;
            this.type = type;
        }

        public /* synthetic */ VoiceTaskWrapper(VoicePlayTask voicePlayTask, PlayType playType, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? (VoicePlayTask) null : voicePlayTask, (i & 2) != 0 ? PlayType.NORMAL : playType);
        }

        public final VoicePlayTask getVoiceTask() {
            return this.voiceTask;
        }

        public final void setVoiceTask(VoicePlayTask voicePlayTask) {
            this.voiceTask = voicePlayTask;
        }

        public final PlayType getType() {
            return this.type;
        }

        public final void setType(PlayType playType) {
            Intrinsics.checkParameterIsNotNull(playType, "<set-?>");
            this.type = playType;
        }
    }
}
