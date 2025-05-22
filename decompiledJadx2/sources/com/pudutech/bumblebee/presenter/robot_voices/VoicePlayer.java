package com.pudutech.bumblebee.presenter.robot_voices;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.IPlayControl;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.disinfect.baselib.network.response.TtsVoiceInfo;
import com.pudutech.resources.voice.VoiceItem;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: VoicePlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u0007J\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020\u0012J\b\u0010.\u001a\u00020\u0012H\u0002J\b\u0010/\u001a\u00020\u0012H\u0016J\u0018\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\u0004H\u0016J\b\u00103\u001a\u00020\u0012H\u0016J\b\u00104\u001a\u00020\u0012H\u0002J\u000e\u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020\tJ\u0010\u00104\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u0004H\u0002J\u0010\u00107\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u0004H\u0002J\u0010\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020;H\u0002J(\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u0010=\u001a\u00020>2\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002J\u0010\u0010@\u001a\u00020\u00122\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u0004H\u0002J\u0010\u0010D\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u0004H\u0002J\u0010\u0010E\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u0004H\u0002J\u0006\u0010F\u001a\u00020\u0012J\u000e\u0010F\u001a\u00020\u00122\u0006\u00105\u001a\u00020\tJ\b\u0010G\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R$\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020#@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006H"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayer;", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl$IPlayStatusListener;", "()V", "TAG", "", "context", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "current", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "filePath", "index", "", "mediaPlayerControl", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl;", "mode", "onPublicPlayMethodCalled", "Lkotlin/Function0;", "", "getOnPublicPlayMethodCalled", "()Lkotlin/jvm/functions/Function0;", "setOnPublicPlayMethodCalled", "(Lkotlin/jvm/functions/Function0;)V", "playHandler", "Landroid/os/Handler;", "playerHelper", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper;", "stepHandler", "thread", "Landroid/os/HandlerThread;", "getThread", "()Landroid/os/HandlerThread;", "ttsAudioTrackControl", "ttsMediaPlayerControl", ES6Iterator.VALUE_PROPERTY, "", "volume", "getVolume", "()F", "setVolume", "(F)V", "init", "appContext", "isPlay", "", "loadResources", ES6Iterator.NEXT_METHOD, "onCompletion", "onError", "what", "error", "onStop", "play", "voiceTask", "name", "playAssetsPath", "path", "playCustom", "dataSource", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "playDefaultVoice", "property", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceProperty;", "playMethod", "playMerchantTts", "ttsVoiceInfo", "Lcom/pudutech/disinfect/baselib/network/response/TtsVoiceInfo;", "playTtsAudioTrack", "playTtsFilePath", "playTtsPath", "stop", "volumeDownControl", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoicePlayer implements IPlayControl.IPlayStatusListener {
    private static WeakReference<Context> context;
    private static VoiceTask current;
    private static int index;
    private static IPlayControl mediaPlayerControl;
    private static VoiceTask mode;
    private static Function0<Unit> onPublicPlayMethodCalled;
    private static Handler playHandler;
    private static Handler stepHandler;
    private static IPlayControl ttsAudioTrackControl;
    private static IPlayControl ttsMediaPlayerControl;
    public static final VoicePlayer INSTANCE = new VoicePlayer();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static float volume = 1.0f;
    private static String filePath = "";
    private static final HandlerThread thread = new HandlerThread("RobotVoices");
    private static final VoicePlayerHelper playerHelper = new VoicePlayerHelper();

    private VoicePlayer() {
    }

    public static final /* synthetic */ IPlayControl access$getMediaPlayerControl$p(VoicePlayer voicePlayer) {
        IPlayControl iPlayControl = mediaPlayerControl;
        if (iPlayControl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
        }
        return iPlayControl;
    }

    public static final /* synthetic */ Handler access$getPlayHandler$p(VoicePlayer voicePlayer) {
        Handler handler = playHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playHandler");
        }
        return handler;
    }

    public static final /* synthetic */ IPlayControl access$getTtsAudioTrackControl$p(VoicePlayer voicePlayer) {
        IPlayControl iPlayControl = ttsAudioTrackControl;
        if (iPlayControl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
        }
        return iPlayControl;
    }

    public static final /* synthetic */ IPlayControl access$getTtsMediaPlayerControl$p(VoicePlayer voicePlayer) {
        IPlayControl iPlayControl = ttsMediaPlayerControl;
        if (iPlayControl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
        }
        return iPlayControl;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl.IPlayStatusListener
    public void onCompletion() {
        Handler handler = playHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playHandler");
        }
        handler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$onCompletion$1
            @Override // java.lang.Runnable
            public final void run() {
                VoicePlayer.INSTANCE.next();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl.IPlayStatusListener
    public void onError(int what, String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Pdlog.m3274e(TAG, "onEvent " + what + ' ' + error);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl.IPlayStatusListener
    public void onStop() {
        Pdlog.m3273d(TAG, "onStop: ");
    }

    public final Function0<Unit> getOnPublicPlayMethodCalled() {
        return onPublicPlayMethodCalled;
    }

    public final void setOnPublicPlayMethodCalled(Function0<Unit> function0) {
        onPublicPlayMethodCalled = function0;
    }

    public final float getVolume() {
        return volume;
    }

    public final void setVolume(float f) {
        Pdlog.m3275i(TAG, "set volume=" + f);
        if (f > 1.0d) {
            volume = 1.0f;
        } else if (f < 0) {
            volume = 0.0f;
        } else {
            volume = f;
        }
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$volume$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    float f2;
                    float f3;
                    float f4;
                    float f5;
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    str = VoicePlayer.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("mediaPlayer set volume=");
                    VoicePlayer voicePlayer2 = VoicePlayer.INSTANCE;
                    f2 = VoicePlayer.volume;
                    sb.append(f2);
                    Pdlog.m3275i(str, sb.toString());
                    IPlayControl access$getMediaPlayerControl$p = VoicePlayer.access$getMediaPlayerControl$p(VoicePlayer.INSTANCE);
                    VoicePlayer voicePlayer3 = VoicePlayer.INSTANCE;
                    f3 = VoicePlayer.volume;
                    access$getMediaPlayerControl$p.setVolume(f3);
                    IPlayControl access$getTtsMediaPlayerControl$p = VoicePlayer.access$getTtsMediaPlayerControl$p(VoicePlayer.INSTANCE);
                    VoicePlayer voicePlayer4 = VoicePlayer.INSTANCE;
                    f4 = VoicePlayer.volume;
                    access$getTtsMediaPlayerControl$p.setVolume(f4);
                    IPlayControl access$getTtsAudioTrackControl$p = VoicePlayer.access$getTtsAudioTrackControl$p(VoicePlayer.INSTANCE);
                    VoicePlayer voicePlayer5 = VoicePlayer.INSTANCE;
                    f5 = VoicePlayer.volume;
                    access$getTtsAudioTrackControl$p.setVolume(f5);
                }
            });
        }
    }

    public final HandlerThread getThread() {
        return thread;
    }

    public final void init(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Pdlog.m3275i(TAG, "init " + appContext);
        mediaPlayerControl = new MediaPlayControl(3);
        IPlayControl iPlayControl = mediaPlayerControl;
        if (iPlayControl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
        }
        VoicePlayer voicePlayer = this;
        iPlayControl.setPlayStatusListener(voicePlayer);
        IPlayControl iPlayControl2 = mediaPlayerControl;
        if (iPlayControl2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
        }
        iPlayControl2.setVolume(volume);
        ttsMediaPlayerControl = new MediaPlayControl(4);
        IPlayControl iPlayControl3 = ttsMediaPlayerControl;
        if (iPlayControl3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
        }
        iPlayControl3.setPlayStatusListener(voicePlayer);
        IPlayControl iPlayControl4 = ttsMediaPlayerControl;
        if (iPlayControl4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
        }
        iPlayControl4.setVolume(volume);
        ttsAudioTrackControl = new AudioTrackControl(4);
        IPlayControl iPlayControl5 = ttsAudioTrackControl;
        if (iPlayControl5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
        }
        iPlayControl5.setPlayStatusListener(voicePlayer);
        IPlayControl iPlayControl6 = ttsAudioTrackControl;
        if (iPlayControl6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
        }
        iPlayControl6.setVolume(volume);
        context = new WeakReference<>(appContext);
        loadResources();
        if (!thread.isAlive()) {
            thread.start();
        }
        playHandler = new Handler(thread.getLooper());
        stepHandler = new Handler(thread.getLooper());
        playerHelper.loadCustomVoiceToMemory();
    }

    public final void loadResources() {
        Pdlog.m3275i(TAG, "loadResources");
        WeakReference<Context> weakReference = context;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Context context2 = weakReference.get();
        if (context2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(context2, "context.get() ?: return");
            filePath = playerHelper.loadDefaultVoice(context2);
        }
    }

    public final void stop(final VoiceTask voiceTask) {
        Intrinsics.checkParameterIsNotNull(voiceTask, "voiceTask");
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("set stop voiceTask=");
        sb.append(voiceTask.getList());
        sb.append("  current=");
        VoiceTask voiceTask2 = current;
        sb.append(voiceTask2 != null ? voiceTask2.getList() : null);
        sb.append(",stepHandler:");
        sb.append(stepHandler);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$stop$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str2;
                    VoiceTask voiceTask3;
                    VoiceTask voiceTask4;
                    VoiceTask voiceTask5;
                    String str3;
                    VoiceTask voiceTask6;
                    Listener listener;
                    VoiceTask voiceTask7;
                    VoiceTask voiceTask8;
                    VoiceTask voiceTask9;
                    VoiceTask voiceTask10;
                    Listener listener2;
                    Listener listener3;
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    str2 = VoicePlayer.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("stop ");
                    sb2.append(VoiceTask.this.getList());
                    sb2.append(" current=");
                    VoicePlayer voicePlayer2 = VoicePlayer.INSTANCE;
                    voiceTask3 = VoicePlayer.current;
                    sb2.append(voiceTask3 != null ? voiceTask3.getList() : null);
                    sb2.append(" in step handler");
                    objArr2[0] = sb2.toString();
                    Pdlog.m3275i(str2, objArr2);
                    VoicePlayer voicePlayer3 = VoicePlayer.INSTANCE;
                    voiceTask4 = VoicePlayer.current;
                    if (Intrinsics.areEqual(voiceTask4, VoiceTask.this)) {
                        VoicePlayer.INSTANCE.volumeDownControl();
                        VoicePlayer.access$getPlayHandler$p(VoicePlayer.INSTANCE).removeMessages(0);
                        VoicePlayer voicePlayer4 = VoicePlayer.INSTANCE;
                        voiceTask7 = VoicePlayer.current;
                        if (voiceTask7 != null && (listener3 = voiceTask7.getListener()) != null) {
                            listener3.onStateChange(PlayEvent.STOP);
                        }
                        VoicePlayer voicePlayer5 = VoicePlayer.INSTANCE;
                        voiceTask8 = VoicePlayer.current;
                        if ((voiceTask8 != null ? voiceTask8.getType() : null) == VoiceType.TEMP) {
                            VoicePlayer voicePlayer6 = VoicePlayer.INSTANCE;
                            VoicePlayer.index = 0;
                            VoicePlayer voicePlayer7 = VoicePlayer.INSTANCE;
                            VoicePlayer voicePlayer8 = VoicePlayer.INSTANCE;
                            voiceTask9 = VoicePlayer.mode;
                            VoicePlayer.current = voiceTask9;
                            VoicePlayer voicePlayer9 = VoicePlayer.INSTANCE;
                            voiceTask10 = VoicePlayer.current;
                            if (voiceTask10 != null && (listener2 = voiceTask10.getListener()) != null) {
                                listener2.onStateChange(PlayEvent.RESUME);
                            }
                            VoicePlayer.INSTANCE.play();
                            return;
                        }
                        VoicePlayer voicePlayer10 = VoicePlayer.INSTANCE;
                        VoiceTask voiceTask11 = (VoiceTask) null;
                        VoicePlayer.mode = voiceTask11;
                        VoicePlayer voicePlayer11 = VoicePlayer.INSTANCE;
                        VoicePlayer.current = voiceTask11;
                        return;
                    }
                    VoiceTask voiceTask12 = VoiceTask.this;
                    VoicePlayer voicePlayer12 = VoicePlayer.INSTANCE;
                    voiceTask5 = VoicePlayer.mode;
                    if (Intrinsics.areEqual(voiceTask12, voiceTask5)) {
                        VoicePlayer voicePlayer13 = VoicePlayer.INSTANCE;
                        voiceTask6 = VoicePlayer.mode;
                        if (voiceTask6 != null && (listener = voiceTask6.getListener()) != null) {
                            listener.onStateChange(PlayEvent.STOP);
                        }
                        VoicePlayer voicePlayer14 = VoicePlayer.INSTANCE;
                        VoicePlayer.mode = (VoiceTask) null;
                        return;
                    }
                    VoicePlayer voicePlayer15 = VoicePlayer.INSTANCE;
                    str3 = VoicePlayer.TAG;
                    Pdlog.m3277w(str3, VoiceTask.this + " not playing task");
                }
            });
        }
    }

    public final void stop() {
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("set stop all. current=");
        VoiceTask voiceTask = current;
        sb.append(voiceTask != null ? voiceTask.getList() : null);
        sb.append(",stepHandler:");
        sb.append(stepHandler);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$stop$2
                @Override // java.lang.Runnable
                public final void run() {
                    String str2;
                    VoiceTask voiceTask2;
                    VoiceTask voiceTask3;
                    VoiceTask voiceTask4;
                    VoiceTask voiceTask5;
                    Listener listener;
                    Listener listener2;
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    str2 = VoicePlayer.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("stop all current=");
                    VoicePlayer voicePlayer2 = VoicePlayer.INSTANCE;
                    voiceTask2 = VoicePlayer.current;
                    sb2.append(voiceTask2 != null ? voiceTask2.getList() : null);
                    sb2.append(" in stepHandler");
                    objArr2[0] = sb2.toString();
                    Pdlog.m3275i(str2, objArr2);
                    VoicePlayer.INSTANCE.volumeDownControl();
                    VoicePlayer.access$getPlayHandler$p(VoicePlayer.INSTANCE).removeMessages(0);
                    VoicePlayer voicePlayer3 = VoicePlayer.INSTANCE;
                    voiceTask3 = VoicePlayer.mode;
                    if (voiceTask3 != null && (listener2 = voiceTask3.getListener()) != null) {
                        listener2.onStateChange(PlayEvent.STOP);
                    }
                    VoicePlayer voicePlayer4 = VoicePlayer.INSTANCE;
                    VoiceTask voiceTask6 = (VoiceTask) null;
                    VoicePlayer.mode = voiceTask6;
                    VoicePlayer voicePlayer5 = VoicePlayer.INSTANCE;
                    voiceTask4 = VoicePlayer.current;
                    if ((voiceTask4 != null ? voiceTask4.getType() : null) == VoiceType.TEMP) {
                        VoicePlayer voicePlayer6 = VoicePlayer.INSTANCE;
                        voiceTask5 = VoicePlayer.current;
                        if (voiceTask5 != null && (listener = voiceTask5.getListener()) != null) {
                            listener.onStateChange(PlayEvent.STOP);
                        }
                    }
                    VoicePlayer voicePlayer7 = VoicePlayer.INSTANCE;
                    VoicePlayer.current = voiceTask6;
                }
            });
        }
    }

    public final void play(final VoiceTask voiceTask) {
        Intrinsics.checkParameterIsNotNull(voiceTask, "voiceTask");
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("set play ");
        sb.append(voiceTask.getType());
        sb.append(" task=");
        sb.append(voiceTask.getList());
        sb.append("  size=");
        sb.append(voiceTask.getList().size());
        sb.append("   current=");
        VoiceTask voiceTask2 = current;
        sb.append(voiceTask2 != null ? voiceTask2.getList() : null);
        sb.append(",stepHandler:");
        sb.append(stepHandler);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        Function0<Unit> function0 = onPublicPlayMethodCalled;
        if (function0 != null) {
            function0.invoke();
        }
        Handler handler = stepHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$play$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str2;
                    VoiceTask voiceTask3;
                    VoiceTask voiceTask4;
                    String str3;
                    VoiceTask voiceTask5;
                    VoiceTask voiceTask6;
                    String str4;
                    VoiceTask voiceTask7;
                    VoiceTask voiceTask8;
                    VoiceTask voiceTask9;
                    Listener listener;
                    VoiceTask voiceTask10;
                    Listener listener2;
                    VoiceTask voiceTask11;
                    Listener listener3;
                    VoicePlayer.INSTANCE.volumeDownControl();
                    VoicePlayer.access$getPlayHandler$p(VoicePlayer.INSTANCE).removeMessages(0);
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    str2 = VoicePlayer.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("step handler play mode=");
                    VoicePlayer voicePlayer2 = VoicePlayer.INSTANCE;
                    voiceTask3 = VoicePlayer.mode;
                    sb2.append(voiceTask3 != null ? voiceTask3.getType() : null);
                    sb2.append(' ');
                    VoicePlayer voicePlayer3 = VoicePlayer.INSTANCE;
                    voiceTask4 = VoicePlayer.mode;
                    sb2.append(voiceTask4 != null ? voiceTask4.getList() : null);
                    objArr2[0] = sb2.toString();
                    Pdlog.m3275i(str2, objArr2);
                    VoicePlayer voicePlayer4 = VoicePlayer.INSTANCE;
                    str3 = VoicePlayer.TAG;
                    Object[] objArr3 = new Object[1];
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("step handler play current=");
                    VoicePlayer voicePlayer5 = VoicePlayer.INSTANCE;
                    voiceTask5 = VoicePlayer.current;
                    sb3.append(voiceTask5 != null ? voiceTask5.getType() : null);
                    sb3.append(' ');
                    VoicePlayer voicePlayer6 = VoicePlayer.INSTANCE;
                    voiceTask6 = VoicePlayer.current;
                    sb3.append(voiceTask6 != null ? voiceTask6.getList() : null);
                    objArr3[0] = sb3.toString();
                    Pdlog.m3275i(str3, objArr3);
                    VoicePlayer voicePlayer7 = VoicePlayer.INSTANCE;
                    str4 = VoicePlayer.TAG;
                    Object[] objArr4 = new Object[1];
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("step handler play newTask=");
                    VoiceTask voiceTask12 = VoiceTask.this;
                    sb4.append(voiceTask12 != null ? voiceTask12.getType() : null);
                    sb4.append(' ');
                    VoiceTask voiceTask13 = VoiceTask.this;
                    sb4.append(voiceTask13 != null ? voiceTask13.getList() : null);
                    objArr4[0] = sb4.toString();
                    Pdlog.m3275i(str4, objArr4);
                    if (VoiceTask.this.getType() == VoiceType.MODE) {
                        VoicePlayer voicePlayer8 = VoicePlayer.INSTANCE;
                        VoicePlayer.mode = VoiceTask.this;
                        VoicePlayer voicePlayer9 = VoicePlayer.INSTANCE;
                        voiceTask11 = VoicePlayer.current;
                        if (voiceTask11 != null && (listener3 = voiceTask11.getListener()) != null) {
                            listener3.onStateChange(PlayEvent.STOP);
                        }
                    } else {
                        VoicePlayer voicePlayer10 = VoicePlayer.INSTANCE;
                        voiceTask7 = VoicePlayer.current;
                        if ((voiceTask7 != null ? voiceTask7.getType() : null) == VoiceType.MODE) {
                            VoicePlayer voicePlayer11 = VoicePlayer.INSTANCE;
                            voiceTask10 = VoicePlayer.current;
                            if (voiceTask10 != null && (listener2 = voiceTask10.getListener()) != null) {
                                listener2.onStateChange(PlayEvent.PAUSE);
                            }
                        } else {
                            VoicePlayer voicePlayer12 = VoicePlayer.INSTANCE;
                            voiceTask8 = VoicePlayer.current;
                            if ((voiceTask8 != null ? voiceTask8.getType() : null) == VoiceType.TEMP) {
                                VoicePlayer voicePlayer13 = VoicePlayer.INSTANCE;
                                voiceTask9 = VoicePlayer.current;
                                if (voiceTask9 != null && (listener = voiceTask9.getListener()) != null) {
                                    listener.onStateChange(PlayEvent.STOP);
                                }
                            }
                        }
                    }
                    VoicePlayer voicePlayer14 = VoicePlayer.INSTANCE;
                    VoicePlayer.current = VoiceTask.this;
                    VoicePlayer voicePlayer15 = VoicePlayer.INSTANCE;
                    VoicePlayer.index = 0;
                    VoicePlayer.INSTANCE.play();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r1v24, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$sam$i$java_lang_Runnable$0] */
    /* JADX WARN: Type inference failed for: r5v18, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r5v2, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r5v21, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r5v7, types: [T, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r5v9, types: [T, kotlin.jvm.functions.Function0] */
    public final void play() {
        VoiceTask voiceTask;
        Listener listener;
        ArrayList<Property> list;
        VoiceTask voiceTask2 = current;
        ArrayList<Property> list2 = voiceTask2 != null ? voiceTask2.getList() : null;
        ArrayList<Property> arrayList = list2;
        if (arrayList == null || arrayList.isEmpty()) {
            String str = TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("current=");
            sb.append(current);
            sb.append(" size=");
            VoiceTask voiceTask3 = current;
            sb.append((voiceTask3 == null || (list = voiceTask3.getList()) == null) ? null : Integer.valueOf(list.size()));
            objArr[0] = sb.toString();
            Pdlog.m3277w(str, objArr);
        }
        if (list2 != null) {
            if (index == 0 && (voiceTask = current) != null && (listener = voiceTask.getListener()) != null) {
                listener.onStateChange(PlayEvent.PLAYING);
            }
            String str2 = TAG;
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("play appoint=");
            sb2.append(index);
            sb2.append(" type=");
            VoiceTask voiceTask4 = current;
            sb2.append(voiceTask4 != null ? voiceTask4.getType() : null);
            objArr2[0] = sb2.toString();
            Pdlog.m3275i(str2, objArr2);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (Function0) 0;
            Property property = list2.get(index);
            if (property instanceof VoiceProperty) {
                Property property2 = list2.get(index);
                if (property2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices.VoiceProperty");
                }
                VoiceProperty voiceProperty = (VoiceProperty) property2;
                VoicePlayerHelper.VoiceType checkCustom = playerHelper.checkCustom();
                if (Intrinsics.areEqual(checkCustom, VoicePlayerHelper.VoiceType.MerchantTtsVoice.INSTANCE)) {
                    List mutableListOf = CollectionsKt.mutableListOf(VoiceItem.voice7_2.name(), VoiceItem.voice49_1.name());
                    VoiceItem item = voiceProperty.getItem();
                    if (CollectionsKt.contains(mutableListOf, item != null ? item.name() : null)) {
                        objectRef.element = INSTANCE.playDefaultVoice(voiceProperty, (Function0) objectRef.element);
                    } else {
                        VoicePlayerHelper voicePlayerHelper = playerHelper;
                        VoiceItem item2 = voiceProperty.getItem();
                        final TtsVoiceInfo merchantTtsRandom = voicePlayerHelper.getMerchantTtsRandom(item2 != null ? item2.name() : null, voiceProperty.getAppoint());
                        if (merchantTtsRandom != null) {
                            objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$play$2$1$1
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
                                    VoicePlayer.INSTANCE.playMerchantTts(TtsVoiceInfo.this);
                                }
                            };
                        }
                    }
                } else if (Intrinsics.areEqual(checkCustom, VoicePlayerHelper.VoiceType.DefaultVoice.INSTANCE)) {
                    objectRef.element = INSTANCE.playDefaultVoice(voiceProperty, (Function0) objectRef.element);
                } else if (Intrinsics.areEqual(checkCustom, VoicePlayerHelper.VoiceType.CustomVoice.INSTANCE)) {
                    VoicePlayerHelper voicePlayerHelper2 = playerHelper;
                    VoiceItem item3 = voiceProperty.getItem();
                    final VoiceDataSource customRandom = voicePlayerHelper2.getCustomRandom(item3 != null ? item3.name() : null, voiceProperty.getAppoint());
                    if (customRandom != null) {
                        objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$$special$$inlined$let$lambda$1
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
                                VoicePlayer.INSTANCE.playCustom(customRandom);
                            }
                        };
                    }
                }
            } else if (property instanceof GeneralVoiceProperty) {
                Property property3 = list2.get(index);
                if (property3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices.GeneralVoiceProperty");
                }
                final GeneralVoiceProperty generalVoiceProperty = (GeneralVoiceProperty) property3;
                objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$play$2$3
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
                        String item4 = GeneralVoiceProperty.this.getItem();
                        if (GeneralVoiceProperty.this.getSize() > 1) {
                            int nextInt = Random.INSTANCE.nextInt(GeneralVoiceProperty.this.getSize()) + 1;
                            String item5 = GeneralVoiceProperty.this.getItem();
                            if (item5 != null) {
                                item4 = item5 + '_' + nextInt + ".mp3";
                            } else {
                                item4 = null;
                            }
                        }
                        if (item4 != null) {
                            VoicePlayer.INSTANCE.playAssetsPath(item4);
                        }
                    }
                };
            } else if (property instanceof TtsVoiceProperty) {
                Property property4 = list2.get(index);
                if (property4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices.TtsVoiceProperty");
                }
                final TtsVoiceProperty ttsVoiceProperty = (TtsVoiceProperty) property4;
                objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$play$2$4
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
                        String ttsPath = TtsVoiceProperty.this.getTtsPath();
                        if (ttsPath != null) {
                            VoicePlayer.INSTANCE.playTtsPath(ttsPath);
                        }
                    }
                };
            }
            if (((Function0) objectRef.element) != null) {
                long timeLag_ms = list2.get(index).getTimeLag_ms();
                if (timeLag_ms > 0) {
                    Pdlog.m3275i(TAG, "this item need delay " + timeLag_ms);
                    Handler handler = playHandler;
                    if (handler == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("playHandler");
                    }
                    final Function0 function0 = (Function0) objectRef.element;
                    if (function0 != null) {
                        function0 = new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$sam$i$java_lang_Runnable$0
                            @Override // java.lang.Runnable
                            public final /* synthetic */ void run() {
                                Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                            }
                        };
                    }
                    handler.postDelayed((Runnable) function0, timeLag_ms);
                    return;
                }
                Function0 function02 = (Function0) objectRef.element;
                if (function02 == null) {
                    Intrinsics.throwNpe();
                }
                function02.invoke();
                return;
            }
            INSTANCE.next();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, kotlin.jvm.functions.Function0] */
    private final Function0<Unit> playDefaultVoice(VoiceProperty property, Function0<Unit> playMethod) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = playMethod;
        VoicePlayerHelper voicePlayerHelper = playerHelper;
        VoiceItem item = property.getItem();
        final String defaultRandom = voicePlayerHelper.getDefaultRandom(item != null ? item.name() : null, property.getAppoint());
        if (defaultRandom != null) {
            objectRef.element = (Function0) new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$playDefaultVoice$$inlined$let$lambda$1
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
                    VoicePlayer.INSTANCE.play(defaultRandom);
                }
            };
        }
        return (Function0) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void next() {
        Listener listener;
        VoiceTask voiceTask = current;
        if (voiceTask != null) {
            index++;
            Pdlog.m3275i(TAG, "play next=" + index + " list.size=" + voiceTask.getList().size() + " type=" + voiceTask.getType());
            if (index >= voiceTask.getList().size()) {
                index = 0;
                Listener listener2 = voiceTask.getListener();
                if (listener2 != null) {
                    listener2.onStateChange(PlayEvent.COMPLETION_ONCE);
                }
                if (voiceTask.getType() == VoiceType.TEMP) {
                    current = mode;
                    VoiceTask voiceTask2 = current;
                    if (voiceTask2 != null && (listener = voiceTask2.getListener()) != null) {
                        listener.onStateChange(PlayEvent.RESUME);
                    }
                }
                VoiceTask voiceTask3 = mode;
                if (voiceTask3 != null) {
                    Pdlog.m3275i(TAG, "next loop");
                    Handler handler = playHandler;
                    if (handler == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("playHandler");
                    }
                    handler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer$next$1$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            VoicePlayer.INSTANCE.play();
                        }
                    }, voiceTask3.getLoopTime_ms());
                    return;
                }
                return;
            }
            INSTANCE.play();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playCustom(VoiceDataSource dataSource) {
        Pdlog.m3273d(TAG, "playCustom " + dataSource.getName() + " size=" + dataSource.getSize());
        try {
            IPlayControl iPlayControl = mediaPlayerControl;
            if (iPlayControl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
            }
            iPlayControl.playDataSource(dataSource);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playCustom error. please check the file. " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void play(String name) {
        try {
            String str = filePath + File.separator + name;
            Pdlog.m3273d(TAG, "play " + str);
            playAssetsPath(str);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "play error. please check the file. " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAssetsPath(String path) {
        AssetManager assets;
        try {
            WeakReference<Context> weakReference = context;
            if (weakReference == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            Context context2 = weakReference.get();
            if (context2 != null && (assets = context2.getAssets()) != null) {
                Pdlog.m3273d(TAG, "playAssetsPath path: " + path);
                IPlayControl iPlayControl = mediaPlayerControl;
                if (iPlayControl == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
                }
                AssetFileDescriptor openFd = assets.openFd(path);
                Intrinsics.checkExpressionValueIsNotNull(openFd, "it.openFd(path)");
                iPlayControl.playAssets(openFd);
                return;
            }
            Pdlog.m3274e(TAG, "playAssetsPath context is null");
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playAssetsPath error. please check the file. " + e);
        }
    }

    private final void playTtsFilePath(String path) {
        try {
            Pdlog.m3273d(TAG, "playFilePath path: " + path);
            IPlayControl iPlayControl = ttsMediaPlayerControl;
            if (iPlayControl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
            }
            iPlayControl.playFile(path);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playFilePath error. please check the file. " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playMerchantTts(TtsVoiceInfo ttsVoiceInfo) {
        Pdlog.m3273d(TAG, "playMerchantTts ttsVoiceInfo:" + ttsVoiceInfo + ' ');
        String path = ttsVoiceInfo.getPath();
        if (path == null) {
            path = "";
        }
        playTtsPath(path);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playTtsPath(String path) {
        Pdlog.m3273d(TAG, "play tts, path: " + path);
        if (StringsKt.endsWith$default(path, ".mp3", false, 2, (Object) null)) {
            playTtsFilePath(path);
        } else {
            playTtsAudioTrack(path);
        }
    }

    private final void playTtsAudioTrack(String path) {
        try {
            IPlayControl iPlayControl = ttsAudioTrackControl;
            if (iPlayControl == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
            }
            iPlayControl.playFile(path);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "playTtsAudioTrack error. please check the file. " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void volumeDownControl() {
        IPlayControl iPlayControl = mediaPlayerControl;
        if (iPlayControl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
        }
        float f = 10.0f;
        int i = 1;
        if (iPlayControl.getIsPlay()) {
            IntProgression step = RangesKt.step(RangesKt.downTo(8, 2), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? first >= last : first <= last) {
                while (true) {
                    float f2 = first / f;
                    IPlayControl iPlayControl2 = mediaPlayerControl;
                    if (iPlayControl2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
                    }
                    iPlayControl2.setVolume(volume * f2);
                    String str = TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("stop and voice down. scale=");
                    sb.append(f2);
                    sb.append(" volume=");
                    sb.append(volume);
                    sb.append("  task=");
                    VoiceTask voiceTask = current;
                    sb.append(voiceTask != null ? voiceTask.getList() : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3275i(str, objArr);
                    Thread.sleep(80L);
                    IPlayControl iPlayControl3 = mediaPlayerControl;
                    if (iPlayControl3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
                    }
                    if (!iPlayControl3.getIsPlay()) {
                        Pdlog.m3275i(TAG, "playing was done");
                        break;
                    } else {
                        if (first == last) {
                            break;
                        }
                        first += step2;
                        f = 10.0f;
                    }
                }
            }
            IPlayControl iPlayControl4 = mediaPlayerControl;
            if (iPlayControl4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
            }
            iPlayControl4.stop();
        }
        IPlayControl iPlayControl5 = ttsMediaPlayerControl;
        if (iPlayControl5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
        }
        if (iPlayControl5.getIsPlay()) {
            IntProgression step3 = RangesKt.step(RangesKt.downTo(8, 2), 2);
            int first2 = step3.getFirst();
            int last2 = step3.getLast();
            int step4 = step3.getStep();
            if (step4 < 0 ? first2 >= last2 : first2 <= last2) {
                while (true) {
                    float f3 = first2 / 10.0f;
                    IPlayControl iPlayControl6 = ttsMediaPlayerControl;
                    if (iPlayControl6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
                    }
                    iPlayControl6.setVolume(volume * f3);
                    String str2 = TAG;
                    Object[] objArr2 = new Object[i];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("stop and voice down. scale=");
                    sb2.append(f3);
                    sb2.append(" volume=");
                    sb2.append(volume);
                    sb2.append("  task=");
                    VoiceTask voiceTask2 = current;
                    sb2.append(voiceTask2 != null ? voiceTask2.getList() : null);
                    objArr2[0] = sb2.toString();
                    Pdlog.m3275i(str2, objArr2);
                    Thread.sleep(80L);
                    IPlayControl iPlayControl7 = ttsMediaPlayerControl;
                    if (iPlayControl7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
                    }
                    if (!iPlayControl7.getIsPlay()) {
                        Pdlog.m3275i(TAG, "playing was done");
                        break;
                    } else {
                        if (first2 == last2) {
                            break;
                        }
                        first2 += step4;
                        i = 1;
                    }
                }
            }
            IPlayControl iPlayControl8 = ttsMediaPlayerControl;
            if (iPlayControl8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
            }
            iPlayControl8.stop();
        }
        IPlayControl iPlayControl9 = ttsAudioTrackControl;
        if (iPlayControl9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
        }
        if (iPlayControl9.getIsPlay()) {
            IntProgression step5 = RangesKt.step(RangesKt.downTo(8, 2), 2);
            int first3 = step5.getFirst();
            int last3 = step5.getLast();
            int step6 = step5.getStep();
            if (step6 < 0 ? first3 >= last3 : first3 <= last3) {
                while (true) {
                    float f4 = first3 / 10.0f;
                    IPlayControl iPlayControl10 = ttsAudioTrackControl;
                    if (iPlayControl10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
                    }
                    iPlayControl10.setVolume(volume * f4);
                    String str3 = TAG;
                    Object[] objArr3 = new Object[1];
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("stop and voice down. scale=");
                    sb3.append(f4);
                    sb3.append(" volume=");
                    sb3.append(volume);
                    sb3.append("  task=");
                    VoiceTask voiceTask3 = current;
                    sb3.append(voiceTask3 != null ? voiceTask3.getList() : null);
                    objArr3[0] = sb3.toString();
                    Pdlog.m3275i(str3, objArr3);
                    Thread.sleep(80L);
                    if (first3 == last3) {
                        break;
                    } else {
                        first3 += step6;
                    }
                }
            }
            IPlayControl iPlayControl11 = ttsAudioTrackControl;
            if (iPlayControl11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
            }
            iPlayControl11.stop();
        }
        IPlayControl iPlayControl12 = mediaPlayerControl;
        if (iPlayControl12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
        }
        iPlayControl12.setVolume(volume);
        IPlayControl iPlayControl13 = ttsMediaPlayerControl;
        if (iPlayControl13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
        }
        iPlayControl13.setVolume(volume);
        IPlayControl iPlayControl14 = ttsAudioTrackControl;
        if (iPlayControl14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
        }
        iPlayControl14.setVolume(volume);
    }

    public final boolean isPlay() {
        IPlayControl iPlayControl = mediaPlayerControl;
        if (iPlayControl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayerControl");
        }
        if (!iPlayControl.getIsPlay()) {
            IPlayControl iPlayControl2 = ttsMediaPlayerControl;
            if (iPlayControl2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsMediaPlayerControl");
            }
            if (!iPlayControl2.getIsPlay()) {
                IPlayControl iPlayControl3 = ttsAudioTrackControl;
                if (iPlayControl3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsAudioTrackControl");
                }
                if (!iPlayControl3.getIsPlay()) {
                    return false;
                }
            }
        }
        return true;
    }
}
