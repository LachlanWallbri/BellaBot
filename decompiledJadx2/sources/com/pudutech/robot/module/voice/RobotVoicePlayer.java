package com.pudutech.robot.module.voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.voice.RobotVoicePlayer;
import com.pudutech.robot.module.voice.data.BaseVoice;
import com.pudutech.robot.module.voice.data.CombineVoices;
import com.pudutech.robot.module.voice.data.PlayEvent;
import com.pudutech.robot.module.voice.data.SingleVoice;
import com.pudutech.robot.module.voice.data.VoiceDataSource;
import com.pudutech.robot.module.voice.data.VoicePlayMode;
import com.pudutech.robot.module.voice.data.VoicePlayTask;
import com.pudutech.robot.module.voice.data.VoiceSubItem;
import com.pudutech.robot.module.voice.utils.SoundPlayUtils;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RobotVoicePlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010'\u001a\u00020\u0012J\b\u0010(\u001a\u00020\u0014H\u0002J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0004J\b\u0010+\u001a\u00020\u0012H\u0002J\u000e\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004J\u0016\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u00020\u0012J\u0010\u00103\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0012\u00104\u001a\u00020\u00122\b\u00105\u001a\u0004\u0018\u000106H\u0016J\u0010\u00107\u001a\u00020\u00122\u0006\u00108\u001a\u000209H\u0002J\u000e\u00107\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\tJ\u0010\u0010;\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0004H\u0002J\u0010\u0010<\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0004H\u0002J\b\u0010=\u001a\u00020\u0012H\u0002J\u0010\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020@H\u0002J\b\u0010A\u001a\u00020\u0012H\u0002J\b\u0010B\u001a\u00020\u0012H\u0002J+\u0010C\u001a\u00020\u00122#\u0010D\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rJ\b\u0010E\u001a\u00020\u0012H\u0002J\u0006\u0010F\u001a\u00020\u0012J\u000e\u0010G\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\tJ\u000e\u0010H\u001a\u00020\u00122\u0006\u00100\u001a\u000201R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R+\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\"\u001a\u00020!2\u0006\u0010 \u001a\u00020!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006I"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/RobotVoicePlayer;", "Landroid/media/MediaPlayer$OnCompletionListener;", "()V", "TAG", "", AIUIConstant.KEY_CONTENT, "context", "Landroid/content/Context;", "curPlayTask", "Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "delayPlayHandler", "Landroid/os/Handler;", "globalPlayEventListener", "Lkotlin/Function1;", "Lcom/pudutech/robot/module/voice/data/PlayEvent;", "Lkotlin/ParameterName;", "name", "event", "", "isCombineVoice", "", "isFirstDelay", "modePlayTask", "modePrePlayTask", "robotMediaPlayer", "Lcom/pudutech/robot/module/voice/RobotMediaPlayer;", "stepHandler", "thread", "Landroid/os/HandlerThread;", "tmpModePlayTask", "voiceDataSourceHelper", "Lcom/pudutech/robot/module/voice/VoiceDataSourceHelper;", ES6Iterator.VALUE_PROPERTY, "", "volume", "getVolume", "()F", "setVolume", "(F)V", "breakUpTask", "canSwitchTask", "checkFileExist", "itemName", "finishTask", "getPathFormDefault", "path", "init", "appContext", "locale", "Ljava/util/Locale;", "mActivityFinishStop", "notifyPlayEvent", "onCompletion", "mp", "Landroid/media/MediaPlayer;", "play", "voice", "Lcom/pudutech/robot/module/voice/data/BaseVoice;", "task", "playAPath", "playAssets", "playCurVoice", "playCustom", "dataSource", "Lcom/pudutech/robot/module/voice/data/VoiceDataSource;", "removeAllHandler", "removePlayHandler", "setGlobalPlayEventListener", "l", "startPlayIfNeed", "stop", "stopVoiceTask", "switchDefResources", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotVoicePlayer implements MediaPlayer.OnCompletionListener {
    private static Context context;
    private static VoicePlayTask curPlayTask;
    private static Handler delayPlayHandler;
    private static Function1<? super PlayEvent, Unit> globalPlayEventListener;
    private static volatile boolean isCombineVoice;
    private static boolean isFirstDelay;
    private static VoicePlayTask modePlayTask;
    private static VoicePlayTask modePrePlayTask;
    private static Handler stepHandler;
    private static VoicePlayTask tmpModePlayTask;
    public static final RobotVoicePlayer INSTANCE = new RobotVoicePlayer();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final HandlerThread thread = new HandlerThread("RobotVoices");
    private static final VoiceDataSourceHelper voiceDataSourceHelper = new VoiceDataSourceHelper();
    private static final RobotMediaPlayer robotMediaPlayer = new RobotMediaPlayer();
    private static volatile String content = "";
    private static float volume = 1.0f;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[VoicePlayMode.values().length];

        static {
            $EnumSwitchMapping$0[VoicePlayMode.Mode.ordinal()] = 1;
            $EnumSwitchMapping$0[VoicePlayMode.Temp.ordinal()] = 2;
        }
    }

    private RobotVoicePlayer() {
    }

    public final float getVolume() {
        return volume;
    }

    public final void setVolume(float f) {
        Pdlog.m3273d(TAG, "set volume=" + f);
        if (f > 1.0d) {
            f = 1.0f;
        } else if (f < 0) {
            f = 0.0f;
        }
        volume = f;
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$volume$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    float f2;
                    RobotMediaPlayer robotMediaPlayer2;
                    float f3;
                    RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                    str = RobotVoicePlayer.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("mediaPlayer set volume=");
                    RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                    f2 = RobotVoicePlayer.volume;
                    sb.append(f2);
                    Pdlog.m3275i(str, sb.toString());
                    RobotVoicePlayer robotVoicePlayer3 = RobotVoicePlayer.INSTANCE;
                    robotMediaPlayer2 = RobotVoicePlayer.robotMediaPlayer;
                    RobotVoicePlayer robotVoicePlayer4 = RobotVoicePlayer.INSTANCE;
                    f3 = RobotVoicePlayer.volume;
                    robotMediaPlayer2.setVolume(f3);
                }
            });
        }
    }

    public final void init(Context appContext, Locale locale) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3273d(TAG, "init " + locale);
        VoicePackageManager.INSTANCE.init(appContext);
        robotMediaPlayer.init(appContext);
        robotMediaPlayer.addOnCompletionListener(this);
        context = appContext;
        SoundPlayUtils.init(appContext);
        switchDefResources(locale);
        if (!thread.isAlive()) {
            thread.start();
        }
        delayPlayHandler = new Handler(thread.getLooper());
        stepHandler = new Handler(thread.getLooper());
        voiceDataSourceHelper.loadCustomVoiceToMemory$module_robot_voice_release();
    }

    public final void play(final VoicePlayTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Pdlog.m3273d(TAG, "play : task = " + task + "; ");
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$play$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    VoicePlayTask voicePlayTask;
                    String str2;
                    VoicePlayTask voicePlayTask2;
                    VoicePlayTask voicePlayTask3;
                    VoicePlayTask voicePlayTask4;
                    String str3;
                    String str4;
                    RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                    str = RobotVoicePlayer.TAG;
                    Pdlog.m3273d(str, "start play ");
                    VoicePlayTask voicePlayTask5 = VoicePlayTask.this;
                    RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                    voicePlayTask = RobotVoicePlayer.curPlayTask;
                    if (!Intrinsics.areEqual(voicePlayTask5, voicePlayTask)) {
                        VoicePlayTask voicePlayTask6 = VoicePlayTask.this;
                        RobotVoicePlayer robotVoicePlayer3 = RobotVoicePlayer.INSTANCE;
                        voicePlayTask2 = RobotVoicePlayer.modePlayTask;
                        if (!Intrinsics.areEqual(voicePlayTask6, voicePlayTask2)) {
                            VoicePlayTask voicePlayTask7 = VoicePlayTask.this;
                            RobotVoicePlayer robotVoicePlayer4 = RobotVoicePlayer.INSTANCE;
                            voicePlayTask3 = RobotVoicePlayer.tmpModePlayTask;
                            if (!Intrinsics.areEqual(voicePlayTask7, voicePlayTask3)) {
                                VoicePlayTask voicePlayTask8 = VoicePlayTask.this;
                                RobotVoicePlayer robotVoicePlayer5 = RobotVoicePlayer.INSTANCE;
                                voicePlayTask4 = RobotVoicePlayer.modePrePlayTask;
                                if (!Intrinsics.areEqual(voicePlayTask8, voicePlayTask4)) {
                                    int i = RobotVoicePlayer.WhenMappings.$EnumSwitchMapping$0[VoicePlayTask.this.getMode().ordinal()];
                                    if (i == 1) {
                                        RobotVoicePlayer robotVoicePlayer6 = RobotVoicePlayer.INSTANCE;
                                        RobotVoicePlayer.modePrePlayTask = VoicePlayTask.this;
                                    } else if (i == 2) {
                                        RobotVoicePlayer robotVoicePlayer7 = RobotVoicePlayer.INSTANCE;
                                        RobotVoicePlayer.tmpModePlayTask = VoicePlayTask.this;
                                    }
                                    RobotVoicePlayer robotVoicePlayer8 = RobotVoicePlayer.INSTANCE;
                                    str3 = RobotVoicePlayer.TAG;
                                    Pdlog.m3273d(str3, "startPlayIfNeed ");
                                    RobotVoicePlayer.INSTANCE.startPlayIfNeed();
                                    RobotVoicePlayer robotVoicePlayer9 = RobotVoicePlayer.INSTANCE;
                                    str4 = RobotVoicePlayer.TAG;
                                    Pdlog.m3273d(str4, "passPlayIfNeed ");
                                    return;
                                }
                            }
                        }
                    }
                    RobotVoicePlayer robotVoicePlayer10 = RobotVoicePlayer.INSTANCE;
                    str2 = RobotVoicePlayer.TAG;
                    Pdlog.m3273d(str2, "has same voice");
                    if (VoicePlayTask.this.getMode() == VoicePlayMode.Mode) {
                        RobotVoicePlayer robotVoicePlayer11 = RobotVoicePlayer.INSTANCE;
                        RobotVoicePlayer.modePrePlayTask = (VoicePlayTask) null;
                    }
                }
            });
        }
    }

    public final boolean checkFileExist(String itemName) {
        Intrinsics.checkParameterIsNotNull(itemName, "itemName");
        return voiceDataSourceHelper.checkFileExist(itemName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlayIfNeed() {
        if (robotMediaPlayer.isPlaying()) {
            return;
        }
        if (canSwitchTask()) {
            Pdlog.m3273d(TAG, "startPlayIfNeed canSwitchTask");
            VoicePlayTask voicePlayTask = tmpModePlayTask;
            if (voicePlayTask != null) {
                curPlayTask = voicePlayTask;
                playCurVoice();
                return;
            }
            VoicePlayTask voicePlayTask2 = modePrePlayTask;
            if (voicePlayTask2 != null) {
                modePlayTask = voicePlayTask2;
                curPlayTask = voicePlayTask2;
                modePrePlayTask = (VoicePlayTask) null;
                playCurVoice();
                return;
            }
            if (modePlayTask != null && (!Intrinsics.areEqual(r0, curPlayTask))) {
                curPlayTask = modePlayTask;
                playCurVoice();
                return;
            } else {
                if (curPlayTask != null) {
                    playCurVoice();
                    return;
                }
                return;
            }
        }
        playCurVoice();
    }

    private final void playCurVoice() {
        VoicePlayTask voicePlayTask = curPlayTask;
        if (voicePlayTask == null) {
            return;
        }
        if (voicePlayTask == null) {
            Intrinsics.throwNpe();
        }
        BaseVoice curVoice = voicePlayTask.getCurVoice();
        Pdlog.m3273d(TAG, "curVoice data " + curVoice);
        if (curVoice != null) {
            if ((curVoice instanceof SingleVoice) && curVoice.canPlay()) {
                play(curVoice);
                return;
            }
            if (curVoice instanceof CombineVoices) {
                CombineVoices combineVoices = (CombineVoices) curVoice;
                if (!combineVoices.hasNext()) {
                    combineVoices.finishOne();
                    if (curVoice.canPlay()) {
                        play(curVoice);
                        return;
                    }
                } else {
                    play(curVoice);
                    return;
                }
            }
        }
        VoicePlayTask voicePlayTask2 = curPlayTask;
        if (voicePlayTask2 == null) {
            Intrinsics.throwNpe();
        }
        BaseVoice nextPlayVoice = voicePlayTask2.getNextPlayVoice();
        if (nextPlayVoice == null) {
            Pdlog.m3273d(TAG, "playCurVoice finish " + curPlayTask);
            finishTask();
            startPlayIfNeed();
            return;
        }
        if ((nextPlayVoice instanceof SingleVoice) && nextPlayVoice.canPlay()) {
            play(nextPlayVoice);
            return;
        }
        if ((nextPlayVoice instanceof CombineVoices) && nextPlayVoice.canPlay() && ((CombineVoices) nextPlayVoice).hasNext()) {
            play(nextPlayVoice);
        } else {
            finishTask();
            startPlayIfNeed();
        }
    }

    private final void removePlayHandler() {
        isFirstDelay = false;
        Handler handler = delayPlayHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayPlayHandler");
        }
        handler.removeCallbacksAndMessages(null);
    }

    private final void finishTask() {
        Pdlog.m3273d(TAG, "finishTask ");
        notifyPlayEvent(PlayEvent.Finish);
        if (Intrinsics.areEqual(curPlayTask, modePlayTask)) {
            modePlayTask = (VoicePlayTask) null;
        }
        if (Intrinsics.areEqual(curPlayTask, tmpModePlayTask)) {
            tmpModePlayTask = (VoicePlayTask) null;
        }
        curPlayTask = (VoicePlayTask) null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r15v2, types: [T, com.pudutech.robot.module.voice.data.VoiceSubItem] */
    /* JADX WARN: Type inference failed for: r15v30, types: [T, com.pudutech.robot.module.voice.data.VoiceSubItem] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.pudutech.robot.module.voice.RobotVoicePlayer$sam$java_lang_Runnable$0] */
    /* JADX WARN: Type inference failed for: r4v11, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r4v16, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r4v18, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r9v1, types: [T, com.pudutech.robot.module.voice.data.VoiceSubItem] */
    private final void play(BaseVoice voice) {
        boolean z;
        long j;
        Pdlog.m3273d(TAG, "play : voice = " + voice + "; ");
        if (voice.isFirstVoice()) {
            voice.addPlayCount();
            j = voice.getDelayPlay();
            z = false;
        } else {
            z = true;
            j = 0;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Function0) 0;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = (VoiceSubItem) 0;
        if (voice instanceof CombineVoices) {
            isCombineVoice = true;
            objectRef2.element = ((CombineVoices) voice).getNextPlayVoiceItem();
        } else if (voice instanceof SingleVoice) {
            isCombineVoice = false;
            objectRef2.element = ((SingleVoice) voice).getPlayVoiceItem();
        } else {
            isCombineVoice = false;
        }
        if (((VoiceSubItem) objectRef2.element) == null) {
            Pdlog.m3274e(TAG, "play : nextPlayVoiceItem is null ??");
        } else {
            content = ((VoiceSubItem) objectRef2.element).getVoiceName();
            j += ((VoiceSubItem) objectRef2.element).getDelayPlay();
            final String voicePath = ((VoiceSubItem) objectRef2.element).getVoicePath();
            Pdlog.m3273d(TAG, "play : nextPlayVoiceItem = " + ((VoiceSubItem) objectRef2.element) + "; ");
            if (!StringsKt.isBlank(voicePath)) {
                objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$play$2
                    /* JADX INFO: Access modifiers changed from: package-private */
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
                        String str;
                        RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                        RobotVoicePlayer.isFirstDelay = false;
                        RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                        str = RobotVoicePlayer.TAG;
                        Pdlog.m3274e(str, "nextPlayVoiceItem data:" + ((VoiceSubItem) Ref.ObjectRef.this.element));
                        RobotVoicePlayer.INSTANCE.playAPath(voicePath);
                    }
                };
            } else if (voiceDataSourceHelper.checkCustom$module_robot_voice_release()) {
                final VoiceDataSource customRandom$module_robot_voice_release = voiceDataSourceHelper.getCustomRandom$module_robot_voice_release(((VoiceSubItem) objectRef2.element).getVoiceName());
                if (customRandom$module_robot_voice_release != null) {
                    objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$play$$inlined$let$lambda$1
                        /* JADX INFO: Access modifiers changed from: package-private */
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
                            String str;
                            RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                            RobotVoicePlayer.isFirstDelay = false;
                            RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                            str = RobotVoicePlayer.TAG;
                            Pdlog.m3274e(str, "nextPlayVoiceItem data:" + ((VoiceSubItem) objectRef2.element));
                            RobotVoicePlayer.INSTANCE.playCustom(VoiceDataSource.this);
                        }
                    };
                }
            } else {
                final String defaultRandom$module_robot_voice_release = voiceDataSourceHelper.getDefaultRandom$module_robot_voice_release(((VoiceSubItem) objectRef2.element).getVoiceName());
                if (defaultRandom$module_robot_voice_release != null) {
                    objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$play$$inlined$let$lambda$2
                        /* JADX INFO: Access modifiers changed from: package-private */
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
                            String str;
                            RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                            RobotVoicePlayer.isFirstDelay = false;
                            RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                            str = RobotVoicePlayer.TAG;
                            Pdlog.m3274e(str, "nextPlayVoiceItem data:" + ((VoiceSubItem) objectRef2.element));
                            RobotVoicePlayer.INSTANCE.playAssets(defaultRandom$module_robot_voice_release);
                        }
                    };
                }
            }
        }
        if (((Function0) objectRef.element) != null) {
            removePlayHandler();
            if (j != 0) {
                if (!z) {
                    isFirstDelay = true;
                }
                Handler handler = delayPlayHandler;
                if (handler == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("delayPlayHandler");
                }
                final Function0 function0 = (Function0) objectRef.element;
                if (function0 != null) {
                    function0 = new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$sam$java_lang_Runnable$0
                        @Override // java.lang.Runnable
                        public final /* synthetic */ void run() {
                            Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                        }
                    };
                }
                handler.postDelayed((Runnable) function0, j);
                return;
            }
            Function0 function02 = (Function0) objectRef.element;
            if (function02 != null) {
                return;
            }
            return;
        }
        Pdlog.m3274e(TAG, "play : playMethod is null ");
        startPlayIfNeed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyPlayEvent(PlayEvent event) {
        Function3<PlayEvent, String, Boolean, Unit> playListener;
        Pdlog.m3273d(TAG, "notifyPlayEvent : event = " + event + "; content: " + content);
        VoicePlayTask voicePlayTask = curPlayTask;
        if (voicePlayTask != null && (playListener = voicePlayTask.getPlayListener()) != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RobotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1(playListener, null, event), 2, null);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RobotVoicePlayer$notifyPlayEvent$2(event, null), 2, null);
    }

    public final void setGlobalPlayEventListener(Function1<? super PlayEvent, Unit> l) {
        globalPlayEventListener = l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playCustom(VoiceDataSource dataSource) {
        Pdlog.m3273d(TAG, "playCustom " + dataSource.getName() + " size=" + dataSource.getSize());
        try {
            notifyPlayEvent(PlayEvent.Playing);
            robotMediaPlayer.play(dataSource);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playCustom error. please check the file. " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAssets(String path) {
        Pdlog.m3273d(TAG, "playAssets: " + path);
        try {
            notifyPlayEvent(PlayEvent.Playing);
            robotMediaPlayer.playAsset(path);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playAssets error. please check the file. " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAPath(String path) {
        Pdlog.m3273d(TAG, "playAPath : path = " + path + "; ");
        try {
            notifyPlayEvent(PlayEvent.Playing);
            robotMediaPlayer.play(path);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playAPath : " + Log.getStackTraceString(e));
        }
    }

    public final String getPathFormDefault(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return voiceDataSourceHelper.getPathFromDefault(path);
    }

    private final boolean canSwitchTask() {
        BaseVoice curVoice;
        Pdlog.m3273d(TAG, "canSwitchTask " + curPlayTask);
        VoicePlayTask voicePlayTask = curPlayTask;
        if (voicePlayTask == null || (curVoice = voicePlayTask.getCurVoice()) == null || !(curVoice instanceof CombineVoices) || !((CombineVoices) curVoice).hasNext()) {
            return true;
        }
        if (!isFirstDelay) {
            return false;
        }
        INSTANCE.removePlayHandler();
        return true;
    }

    public final void switchDefResources(Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3273d(TAG, "loadResources");
        VoiceDataSourceHelper voiceDataSourceHelper2 = voiceDataSourceHelper;
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        voiceDataSourceHelper2.loadDefaultVoice$module_robot_voice_release(context2, locale);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        Pdlog.m3273d(TAG, "onCompletion ");
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$onCompletion$1
                @Override // java.lang.Runnable
                public final void run() {
                    RobotVoicePlayer.INSTANCE.startPlayIfNeed();
                }
            });
        }
        notifyPlayEvent(PlayEvent.OnceFinish);
    }

    public final void stopVoiceTask(final VoicePlayTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Pdlog.m3273d(TAG, "stopVoiceTask");
        Handler handler = stepHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = stepHandler;
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$stopVoiceTask$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    VoicePlayTask voicePlayTask;
                    VoicePlayTask voicePlayTask2;
                    VoicePlayTask voicePlayTask3;
                    VoicePlayTask voicePlayTask4;
                    RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                    str = RobotVoicePlayer.TAG;
                    Pdlog.m3273d(str, "stopVoiceTask : task = " + VoicePlayTask.this + "; ");
                    VoicePlayTask voicePlayTask5 = VoicePlayTask.this;
                    RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                    voicePlayTask = RobotVoicePlayer.tmpModePlayTask;
                    if (Intrinsics.areEqual(voicePlayTask5, voicePlayTask)) {
                        RobotVoicePlayer robotVoicePlayer3 = RobotVoicePlayer.INSTANCE;
                        RobotVoicePlayer.tmpModePlayTask = (VoicePlayTask) null;
                    }
                    VoicePlayTask voicePlayTask6 = VoicePlayTask.this;
                    RobotVoicePlayer robotVoicePlayer4 = RobotVoicePlayer.INSTANCE;
                    voicePlayTask2 = RobotVoicePlayer.modePlayTask;
                    if (Intrinsics.areEqual(voicePlayTask6, voicePlayTask2)) {
                        RobotVoicePlayer robotVoicePlayer5 = RobotVoicePlayer.INSTANCE;
                        RobotVoicePlayer.modePlayTask = (VoicePlayTask) null;
                    }
                    VoicePlayTask voicePlayTask7 = VoicePlayTask.this;
                    RobotVoicePlayer robotVoicePlayer6 = RobotVoicePlayer.INSTANCE;
                    voicePlayTask3 = RobotVoicePlayer.modePrePlayTask;
                    if (Intrinsics.areEqual(voicePlayTask7, voicePlayTask3)) {
                        RobotVoicePlayer robotVoicePlayer7 = RobotVoicePlayer.INSTANCE;
                        RobotVoicePlayer.modePrePlayTask = (VoicePlayTask) null;
                    }
                    VoicePlayTask voicePlayTask8 = VoicePlayTask.this;
                    RobotVoicePlayer robotVoicePlayer8 = RobotVoicePlayer.INSTANCE;
                    voicePlayTask4 = RobotVoicePlayer.curPlayTask;
                    if (Intrinsics.areEqual(voicePlayTask8, voicePlayTask4)) {
                        RobotVoicePlayer.INSTANCE.stop();
                    }
                }
            });
        }
    }

    public final void breakUpTask() {
        Pdlog.m3273d(TAG, "breakUpTask ");
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$breakUpTask$1
                @Override // java.lang.Runnable
                public final void run() {
                    RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                    VoicePlayTask voicePlayTask = (VoicePlayTask) null;
                    RobotVoicePlayer.modePrePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.modePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer3 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.curPlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer4 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.tmpModePlayTask = voicePlayTask;
                }
            });
        }
    }

    public final void stop() {
        Pdlog.m3273d(TAG, "stop ");
        removeAllHandler();
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$stop$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    RobotMediaPlayer robotMediaPlayer2;
                    RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                    str = RobotVoicePlayer.TAG;
                    Pdlog.m3273d(str, "start stop ");
                    RobotVoicePlayer.INSTANCE.notifyPlayEvent(PlayEvent.Stop);
                    RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                    VoicePlayTask voicePlayTask = (VoicePlayTask) null;
                    RobotVoicePlayer.modePrePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer3 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.modePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer4 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.curPlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer5 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.tmpModePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer6 = RobotVoicePlayer.INSTANCE;
                    robotMediaPlayer2 = RobotVoicePlayer.robotMediaPlayer;
                    robotMediaPlayer2.stop();
                }
            });
        }
    }

    public final void mActivityFinishStop() {
        Pdlog.m3273d(TAG, "stop ");
        removeAllHandler();
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.RobotVoicePlayer$mActivityFinishStop$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    RobotMediaPlayer robotMediaPlayer2;
                    RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
                    str = RobotVoicePlayer.TAG;
                    Pdlog.m3273d(str, "start stop ");
                    RobotVoicePlayer.INSTANCE.notifyPlayEvent(PlayEvent.Stop);
                    RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
                    VoicePlayTask voicePlayTask = (VoicePlayTask) null;
                    RobotVoicePlayer.modePrePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer3 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.modePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer4 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.curPlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer5 = RobotVoicePlayer.INSTANCE;
                    RobotVoicePlayer.tmpModePlayTask = voicePlayTask;
                    RobotVoicePlayer robotVoicePlayer6 = RobotVoicePlayer.INSTANCE;
                    robotMediaPlayer2 = RobotVoicePlayer.robotMediaPlayer;
                    robotMediaPlayer2.stop();
                    RobotVoicePlayer.INSTANCE.removeAllHandler();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeAllHandler() {
        isFirstDelay = false;
        Handler handler = delayPlayHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayPlayHandler");
        }
        handler.removeCallbacksAndMessages(null);
        Handler handler2 = stepHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
    }
}
