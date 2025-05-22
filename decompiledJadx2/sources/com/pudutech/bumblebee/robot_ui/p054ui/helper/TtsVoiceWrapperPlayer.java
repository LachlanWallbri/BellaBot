package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsVoiceWrapperPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002()B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0006\u0010\u001c\u001a\u00020\u0010J5\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182%\b\u0002\u0010\u001e\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012J=\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u000b2%\b\u0002\u0010\u001e\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012J\u0006\u0010 \u001a\u00020\u0010J\b\u0010!\u001a\u00020\u0010H\u0002J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0004H\u0002J\u000e\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0018J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer;", "", "()V", "KEY_PLAY_CRUISES_TTS", "", "KEY_PLAY_CRUISE_STAY_POINT_TTS", "KEY_PLAY_GREETER_GUIDE_TTS", "KEY_PLAY_GREETER_TTS", "TAG", "", "intervalTime", "", "mainHandler", "Landroid/os/Handler;", "onVoicePlayerPlay", "Lkotlin/Function0;", "", "ttsPlayerListener", "Lkotlin/Function1;", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "Lkotlin/ParameterName;", "name", "event", "voiceTaskWrapper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "getTaskInterval", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "init", "play", "listener", "playCruiseStayPointVoice", "rePlayCruiseStayPointVoice", "release", "startIntervalVoice", TransferTable.COLUMN_KEY, "startNowVoice", "stop", "it", "stopIntervalTask", "PlayType", "VoiceTaskWrapper", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceWrapperPlayer {
    private static final int KEY_PLAY_CRUISES_TTS = 1001;
    private static final int KEY_PLAY_CRUISE_STAY_POINT_TTS = 1004;
    private static final int KEY_PLAY_GREETER_GUIDE_TTS = 1003;
    private static final int KEY_PLAY_GREETER_TTS = 1002;
    private static final String TAG = "TtsVoiceWrapperPlayer";
    private static Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> ttsPlayerListener;
    private static VoiceTaskWrapper voiceTaskWrapper;
    public static final TtsVoiceWrapperPlayer INSTANCE = new TtsVoiceWrapperPlayer();
    private static final Function0<Unit> onVoicePlayerPlay = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$onVoicePlayerPlay$1
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
    private static final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1.1
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
                case 1002:
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.GREETER_TYPE, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1.2
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
                                TtsVoiceWrapperPlayer.INSTANCE.startIntervalVoice(1002);
                            }
                        }
                    });
                    return true;
                case 1003:
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1.3
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
                                TtsVoiceWrapperPlayer.INSTANCE.startIntervalVoice(1003);
                            }
                        }
                    });
                    return true;
                case 1004:
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$mainHandler$1.4
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
                                TtsVoiceWrapperPlayer.INSTANCE.startIntervalVoice(1004);
                            }
                        }
                    });
                    return true;
                default:
                    return true;
            }
        }
    });

    /* compiled from: TtsVoiceWrapperPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "", "(Ljava/lang/String;I)V", "NORMAL", "CRUISE", "DELIVER_ARRIVED", "GREETER_DONE", "GREETER_TYPE", "GREETER_GUIDE", "SPECIAL_MODE_ARRIVE", "CRUISE_STAY_POINT", "BIRTHDAY_MODE", "GUIDE_ARRIVAL", "RECYCLE_POINT_ARRIVED", "RECYCLE_TABLE_LEAVE", "RECYCLE_TABLE_ARRIVED", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum PlayType {
        NORMAL,
        CRUISE,
        DELIVER_ARRIVED,
        GREETER_DONE,
        GREETER_TYPE,
        GREETER_GUIDE,
        SPECIAL_MODE_ARRIVE,
        CRUISE_STAY_POINT,
        BIRTHDAY_MODE,
        GUIDE_ARRIVAL,
        RECYCLE_POINT_ARRIVED,
        RECYCLE_TABLE_LEAVE,
        RECYCLE_TABLE_ARRIVED
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$10;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$2[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$2[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$3[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$3[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$4[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$4[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$5 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$5[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$5[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$6 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$6[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$6[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$7 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$7[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$7[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$8 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$8[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$8[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$9 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$9[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$9[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$10 = new int[TtsVoiceHelper.TtsVoiceOpenType.values().length];
            $EnumSwitchMapping$10[TtsVoiceHelper.TtsVoiceOpenType.CLOSE.ordinal()] = 1;
            $EnumSwitchMapping$10[TtsVoiceHelper.TtsVoiceOpenType.OPEN.ordinal()] = 2;
            $EnumSwitchMapping$11 = new int[PlayType.values().length];
            $EnumSwitchMapping$11[PlayType.NORMAL.ordinal()] = 1;
            $EnumSwitchMapping$11[PlayType.SPECIAL_MODE_ARRIVE.ordinal()] = 2;
            $EnumSwitchMapping$11[PlayType.GREETER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$11[PlayType.GREETER_DONE.ordinal()] = 4;
            $EnumSwitchMapping$11[PlayType.CRUISE.ordinal()] = 5;
            $EnumSwitchMapping$11[PlayType.DELIVER_ARRIVED.ordinal()] = 6;
            $EnumSwitchMapping$11[PlayType.BIRTHDAY_MODE.ordinal()] = 7;
            $EnumSwitchMapping$11[PlayType.GREETER_GUIDE.ordinal()] = 8;
            $EnumSwitchMapping$11[PlayType.GUIDE_ARRIVAL.ordinal()] = 9;
            $EnumSwitchMapping$11[PlayType.RECYCLE_TABLE_LEAVE.ordinal()] = 10;
            $EnumSwitchMapping$11[PlayType.RECYCLE_POINT_ARRIVED.ordinal()] = 11;
            $EnumSwitchMapping$11[PlayType.RECYCLE_TABLE_ARRIVED.ordinal()] = 12;
        }
    }

    private TtsVoiceWrapperPlayer() {
    }

    public final void init() {
        VoicePlayer.INSTANCE.setOnPublicPlayMethodCalled(onVoicePlayerPlay);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void play$default(TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer, VoiceTaskWrapper voiceTaskWrapper2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceWrapperPlayer.play(voiceTaskWrapper2, function1);
    }

    public final void play(VoiceTaskWrapper voiceTaskWrapper2, final Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(voiceTaskWrapper2, "voiceTaskWrapper");
        ttsPlayerListener = listener;
        switch (voiceTaskWrapper2.getType()) {
            case NORMAL:
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                VoiceTask voiceTask = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer.play(voiceTask);
                return;
            case SPECIAL_MODE_ARRIVE:
                int i = WhenMappings.$EnumSwitchMapping$0[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE).ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "SPECIAL_MODE_ARRIVE : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer2 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask2 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask2 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer2.play(voiceTask2);
                return;
            case GREETER_TYPE:
                TtsVoiceHelper.TtsVoiceOpenType checkTtsOpenType = TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.GREETER_TYPE);
                voiceTaskWrapper = voiceTaskWrapper2;
                int i2 = WhenMappings.$EnumSwitchMapping$1[checkTtsOpenType.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.GREETER_TYPE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer3 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask3 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask3 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer3.play(voiceTask3.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$play$1
                    @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                    public void onStateChange(PlayEvent event) {
                        Function1 function1;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        Pdlog.m3273d("TtsVoiceWrapperPlayer", "playFaceDetectVoice onStateChange : event = " + event + "; ");
                        if (event == PlayEvent.COMPLETION_ONCE) {
                            Function1 function12 = Function1.this;
                            if (function12 != null) {
                                return;
                            }
                            return;
                        }
                        if (event != PlayEvent.PLAYING || (function1 = Function1.this) == null) {
                            return;
                        }
                    }
                }));
                return;
            case GREETER_DONE:
                int i3 = WhenMappings.$EnumSwitchMapping$2[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.GREETER_TYPE).ordinal()];
                if (i3 != 1) {
                    if (i3 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    if (mainHandler.hasMessages(1003)) {
                        return;
                    }
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.GREETER_TYPE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer4 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask4 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask4 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer4.play(voiceTask4.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceWrapperPlayer$play$2
                    @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                    public void onStateChange(PlayEvent event) {
                        Function1 function1;
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        Pdlog.m3273d("TtsVoiceWrapperPlayer", "playFaceDetectVoice onStateChange : event = " + event + "; ");
                        if (event != PlayEvent.COMPLETION_ONCE || (function1 = Function1.this) == null) {
                            return;
                        }
                    }
                }));
                VoicePlayer.INSTANCE.stop();
                return;
            case CRUISE:
                int i4 = WhenMappings.$EnumSwitchMapping$3[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE).ordinal()];
                if (i4 == 1) {
                    Pdlog.m3273d(TAG, "play VoicePlayer");
                    TtsVoiceHelper.INSTANCE.stopCruiseTts();
                    VoicePlayer voicePlayer5 = VoicePlayer.INSTANCE;
                    VoiceTask voiceTask5 = voiceTaskWrapper2.getVoiceTask();
                    if (voiceTask5 == null) {
                        Intrinsics.throwNpe();
                    }
                    voicePlayer5.play(voiceTask5);
                    return;
                }
                if (i4 != 2) {
                    return;
                }
                Pdlog.m3273d(TAG, "play TtsVoice");
                VoicePlayer.INSTANCE.stop();
                release();
                voiceTaskWrapper = voiceTaskWrapper2;
                VoiceTask voiceTask6 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask6 == null) {
                    Intrinsics.throwNpe();
                }
                long taskInterval = getTaskInterval(voiceTask6);
                if (taskInterval <= 0) {
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE, listener);
                    return;
                } else {
                    intervalTime = taskInterval;
                    startIntervalVoice(1001);
                    return;
                }
            case DELIVER_ARRIVED:
                if (voiceTaskWrapper2.getTtsConfigData() != null) {
                    Pdlog.m3273d(TAG, "play pallet TtsVoice,ttsConfigData: " + voiceTaskWrapper2.getTtsConfigData());
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    TtsVoiceHelper.TtsConfigData ttsConfigData = voiceTaskWrapper2.getTtsConfigData();
                    if (ttsConfigData == null) {
                        Intrinsics.throwNpe();
                    }
                    ttsVoiceHelper.playPcm(ttsConfigData, listener);
                    return;
                }
                int i5 = WhenMappings.$EnumSwitchMapping$4[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE).ordinal()];
                if (i5 != 1) {
                    if (i5 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "DELIVER_ARRIVED : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer6 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask7 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask7 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer6.play(voiceTask7);
                return;
            case BIRTHDAY_MODE:
                int i6 = WhenMappings.$EnumSwitchMapping$5[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.BIRTHDAY_TYPE).ordinal()];
                if (i6 != 1) {
                    if (i6 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.BIRTHDAY_TYPE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "BIRTHDAY_MODE : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer7 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask8 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask8 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer7.play(voiceTask8);
                return;
            case GREETER_GUIDE:
                int i7 = WhenMappings.$EnumSwitchMapping$6[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE).ordinal()];
                if (i7 == 1) {
                    Pdlog.m3273d(TAG, "play VoicePlayer");
                    TtsVoiceHelper.INSTANCE.stopCruiseTts();
                    VoicePlayer voicePlayer8 = VoicePlayer.INSTANCE;
                    VoiceTask voiceTask9 = voiceTaskWrapper2.getVoiceTask();
                    if (voiceTask9 == null) {
                        Intrinsics.throwNpe();
                    }
                    voicePlayer8.play(voiceTask9);
                    return;
                }
                if (i7 != 2) {
                    return;
                }
                Pdlog.m3273d(TAG, "play TtsVoice");
                VoicePlayer.INSTANCE.stop();
                release();
                voiceTaskWrapper = voiceTaskWrapper2;
                VoiceTask voiceTask10 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask10 == null) {
                    Intrinsics.throwNpe();
                }
                long taskInterval2 = getTaskInterval(voiceTask10);
                if (taskInterval2 <= 0) {
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE, listener);
                    return;
                } else {
                    intervalTime = taskInterval2;
                    startIntervalVoice(1003);
                    return;
                }
            case GUIDE_ARRIVAL:
                int i8 = WhenMappings.$EnumSwitchMapping$7[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.GUIDE_ARRIVAL_TYPE).ordinal()];
                if (i8 != 1) {
                    if (i8 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.GUIDE_ARRIVAL_TYPE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "DELIVER_ARRIVED : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer9 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask11 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask11 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer9.play(voiceTask11);
                return;
            case RECYCLE_TABLE_LEAVE:
                int i9 = WhenMappings.$EnumSwitchMapping$8[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE).ordinal()];
                if (i9 != 1) {
                    if (i9 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "RECYCLE_TABLE_LEAVE : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer10 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask12 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask12 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer10.play(voiceTask12);
                return;
            case RECYCLE_POINT_ARRIVED:
                int i10 = WhenMappings.$EnumSwitchMapping$9[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE).ordinal()];
                if (i10 != 1) {
                    if (i10 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "RECYCLE_POINT_ARRIVED : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer11 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask13 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask13 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer11.play(voiceTask13);
                return;
            case RECYCLE_TABLE_ARRIVED:
                int i11 = WhenMappings.$EnumSwitchMapping$10[TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE).ordinal()];
                if (i11 != 1) {
                    if (i11 != 2) {
                        return;
                    }
                    Pdlog.m3273d(TAG, "play TtsVoice");
                    VoicePlayer.INSTANCE.stop();
                    release();
                    TtsVoiceHelper.INSTANCE.playTtsVoice(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE, listener);
                    return;
                }
                Pdlog.m3273d(TAG, "RECYCLE_TABLE_ARRIVED : play VoicePlayer");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                VoicePlayer voicePlayer12 = VoicePlayer.INSTANCE;
                VoiceTask voiceTask14 = voiceTaskWrapper2.getVoiceTask();
                if (voiceTask14 == null) {
                    Intrinsics.throwNpe();
                }
                voicePlayer12.play(voiceTask14);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playCruiseStayPointVoice$default(TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer, VoiceTaskWrapper voiceTaskWrapper2, long j, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceWrapperPlayer.playCruiseStayPointVoice(voiceTaskWrapper2, j, function1);
    }

    public final void playCruiseStayPointVoice(VoiceTaskWrapper voiceTaskWrapper2, long intervalTime2, Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(voiceTaskWrapper2, "voiceTaskWrapper");
        VoicePlayer.INSTANCE.stop();
        release();
        ttsPlayerListener = listener;
        voiceTaskWrapper = voiceTaskWrapper2;
        if (intervalTime2 <= 0) {
            TtsVoiceHelper.playTtsVoice$default(TtsVoiceHelper.INSTANCE, TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE, null, 2, null);
        } else {
            intervalTime = intervalTime2;
            startNowVoice(1004);
        }
    }

    public final void rePlayCruiseStayPointVoice() {
        startIntervalVoice(1004);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startIntervalVoice(int key) {
        Pdlog.m3273d(TAG, "startIntervalVoice " + key);
        if (intervalTime <= 0) {
            Pdlog.m3274e(TAG, "startIntervalVoice key = " + key + "  failed, time is < 0");
            return;
        }
        stopIntervalTask(key);
        mainHandler.sendEmptyMessageDelayed(key, intervalTime);
    }

    private final void startNowVoice(int key) {
        Pdlog.m3273d(TAG, "startNowVoice " + key);
        stopIntervalTask(1001);
        stopIntervalTask(1004);
        mainHandler.sendEmptyMessage(key);
    }

    private final void stopIntervalTask(int key) {
        mainHandler.removeMessages(key);
    }

    private final long getTaskInterval(VoiceTask voiceTask) {
        if (voiceTask.getLoopTime_ms() <= 0) {
            return -1L;
        }
        return voiceTask.getLoopTime_ms() + voiceTask.getList().get(0).getTimeLag_ms();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release() {
        ttsPlayerListener = (Function1) null;
        intervalTime = -1L;
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        mainHandler.removeCallbacksAndMessages(null);
        voiceTaskWrapper = (VoiceTaskWrapper) null;
    }

    public final void stop(VoiceTaskWrapper it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        VoiceTask voiceTask = it.getVoiceTask();
        if (voiceTask != null) {
            VoicePlayer.INSTANCE.stop(voiceTask);
        }
        release();
    }

    /* compiled from: TtsVoiceWrapperPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "type", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "ttsConfigData", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "(Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;)V", "getTtsConfigData", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "setTtsConfigData", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;)V", "getType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;", "setType", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$PlayType;)V", "getVoiceTask", "()Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "setVoiceTask", "(Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final /* data */ class VoiceTaskWrapper {
        private TtsVoiceHelper.TtsConfigData ttsConfigData;
        private PlayType type;
        private VoiceTask voiceTask;

        public VoiceTaskWrapper() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ VoiceTaskWrapper copy$default(VoiceTaskWrapper voiceTaskWrapper, VoiceTask voiceTask, PlayType playType, TtsVoiceHelper.TtsConfigData ttsConfigData, int i, Object obj) {
            if ((i & 1) != 0) {
                voiceTask = voiceTaskWrapper.voiceTask;
            }
            if ((i & 2) != 0) {
                playType = voiceTaskWrapper.type;
            }
            if ((i & 4) != 0) {
                ttsConfigData = voiceTaskWrapper.ttsConfigData;
            }
            return voiceTaskWrapper.copy(voiceTask, playType, ttsConfigData);
        }

        /* renamed from: component1, reason: from getter */
        public final VoiceTask getVoiceTask() {
            return this.voiceTask;
        }

        /* renamed from: component2, reason: from getter */
        public final PlayType getType() {
            return this.type;
        }

        /* renamed from: component3, reason: from getter */
        public final TtsVoiceHelper.TtsConfigData getTtsConfigData() {
            return this.ttsConfigData;
        }

        public final VoiceTaskWrapper copy(VoiceTask voiceTask, PlayType type, TtsVoiceHelper.TtsConfigData ttsConfigData) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new VoiceTaskWrapper(voiceTask, type, ttsConfigData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VoiceTaskWrapper)) {
                return false;
            }
            VoiceTaskWrapper voiceTaskWrapper = (VoiceTaskWrapper) other;
            return Intrinsics.areEqual(this.voiceTask, voiceTaskWrapper.voiceTask) && Intrinsics.areEqual(this.type, voiceTaskWrapper.type) && Intrinsics.areEqual(this.ttsConfigData, voiceTaskWrapper.ttsConfigData);
        }

        public int hashCode() {
            VoiceTask voiceTask = this.voiceTask;
            int hashCode = (voiceTask != null ? voiceTask.hashCode() : 0) * 31;
            PlayType playType = this.type;
            int hashCode2 = (hashCode + (playType != null ? playType.hashCode() : 0)) * 31;
            TtsVoiceHelper.TtsConfigData ttsConfigData = this.ttsConfigData;
            return hashCode2 + (ttsConfigData != null ? ttsConfigData.hashCode() : 0);
        }

        public String toString() {
            return "VoiceTaskWrapper(voiceTask=" + this.voiceTask + ", type=" + this.type + ", ttsConfigData=" + this.ttsConfigData + ")";
        }

        public VoiceTaskWrapper(VoiceTask voiceTask, PlayType type, TtsVoiceHelper.TtsConfigData ttsConfigData) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.voiceTask = voiceTask;
            this.type = type;
            this.ttsConfigData = ttsConfigData;
        }

        public /* synthetic */ VoiceTaskWrapper(VoiceTask voiceTask, PlayType playType, TtsVoiceHelper.TtsConfigData ttsConfigData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? (VoiceTask) null : voiceTask, (i & 2) != 0 ? PlayType.NORMAL : playType, (i & 4) != 0 ? (TtsVoiceHelper.TtsConfigData) null : ttsConfigData);
        }

        public final VoiceTask getVoiceTask() {
            return this.voiceTask;
        }

        public final void setVoiceTask(VoiceTask voiceTask) {
            this.voiceTask = voiceTask;
        }

        public final PlayType getType() {
            return this.type;
        }

        public final void setType(PlayType playType) {
            Intrinsics.checkParameterIsNotNull(playType, "<set-?>");
            this.type = playType;
        }

        public final TtsVoiceHelper.TtsConfigData getTtsConfigData() {
            return this.ttsConfigData;
        }

        public final void setTtsConfigData(TtsVoiceHelper.TtsConfigData ttsConfigData) {
            this.ttsConfigData = ttsConfigData;
        }
    }
}
