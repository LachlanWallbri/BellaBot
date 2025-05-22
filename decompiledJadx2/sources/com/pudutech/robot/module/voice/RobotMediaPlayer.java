package com.pudutech.robot.module.voice;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.voice.AudioTrackUtils;
import com.pudutech.robot.module.voice.RobotMediaPlayer;
import com.pudutech.robot.module.voice.data.VoiceDataSource;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: RobotMediaPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\nH\u0016J\"\u0010\u0013\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u000e\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u0017\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\fJ\b\u0010 \u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/RobotMediaPlayer;", "Landroid/media/MediaPlayer$OnCompletionListener;", "Landroid/media/MediaPlayer$OnErrorListener;", "()V", "TAG", "", "context", "Landroid/content/Context;", "mOnCompletionListener", "mediaPlayer", "Landroid/media/MediaPlayer;", "addOnCompletionListener", "", "l", "init", "isPlaying", "", "onCompletion", "mp", "onError", "what", "", "extra", "play", "dataSource", "Lcom/pudutech/robot/module/voice/data/VoiceDataSource;", "path", "playAsset", "setVolume", "volume", "", "stop", "volumeSlowDownFinish", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotMediaPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private final String TAG = "RobotMediaPlayer";
    private Context context;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer mediaPlayer;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AudioTrackUtils.AudioPlayEvent.values().length];

        static {
            $EnumSwitchMapping$0[AudioTrackUtils.AudioPlayEvent.STOP.ordinal()] = 1;
            $EnumSwitchMapping$0[AudioTrackUtils.AudioPlayEvent.COMPLETE.ordinal()] = 2;
            $EnumSwitchMapping$0[AudioTrackUtils.AudioPlayEvent.PLAYING.ordinal()] = 3;
        }
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mediaPlayer = new MediaPlayer();
        this.context = context;
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(3);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setAudioAttributes(builder.build());
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer2.setOnCompletionListener(this);
        MediaPlayer mediaPlayer3 = this.mediaPlayer;
        if (mediaPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer3.setOnErrorListener(this);
        MediaPlayer mediaPlayer4 = this.mediaPlayer;
        if (mediaPlayer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer4.setVolume(RobotVoicePlayer.INSTANCE.getVolume(), RobotVoicePlayer.INSTANCE.getVolume());
    }

    public final void play(VoiceDataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        Pdlog.m3273d(this.TAG, "play " + dataSource.getName() + " size=" + dataSource.getSize());
        try {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer.reset();
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.setDataSource(dataSource);
            MediaPlayer mediaPlayer3 = this.mediaPlayer;
            if (mediaPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer3.prepare();
            MediaPlayer mediaPlayer4 = this.mediaPlayer;
            if (mediaPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer4.start();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "playCustom error. please check the file. " + e);
        }
    }

    public final void addOnCompletionListener(MediaPlayer.OnCompletionListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        this.mOnCompletionListener = l;
    }

    public final void play(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Pdlog.m3273d(this.TAG, "play : path = " + path + "; ");
        if (StringsKt.endsWith$default(path, "pcm", false, 2, (Object) null)) {
            AudioTrackUtils.INSTANCE.play(path, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.robot.module.voice.RobotMediaPlayer$play$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                    invoke2(audioPlayEvent);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:8:0x0034, code lost:
                
                    r6 = r5.this$0.mOnCompletionListener;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                    String str;
                    MediaPlayer.OnCompletionListener onCompletionListener;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    str = RobotMediaPlayer.this.TAG;
                    Pdlog.m3273d(str, "AudioTrackUtils event " + it);
                    int i = RobotMediaPlayer.WhenMappings.$EnumSwitchMapping$0[it.ordinal()];
                    if (i == 1 || i != 2 || onCompletionListener == null) {
                        return;
                    }
                    onCompletionListener.onCompletion(null);
                }
            });
            return;
        }
        try {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer.reset();
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer2.setDataSource(path);
            MediaPlayer mediaPlayer3 = this.mediaPlayer;
            if (mediaPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer3.prepare();
            MediaPlayer mediaPlayer4 = this.mediaPlayer;
            if (mediaPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer4.start();
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "playAPath : " + Log.getStackTraceString(e));
        }
    }

    public final void stop() {
        Pdlog.m3273d(this.TAG, "stop ");
        volumeSlowDownFinish();
        AudioTrackUtils.INSTANCE.stop();
    }

    public final void playAsset(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        try {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            AssetManager assets = context.getAssets();
            if (assets != null) {
                Pdlog.m3273d(this.TAG, "play " + path);
                MediaPlayer mediaPlayer = this.mediaPlayer;
                if (mediaPlayer == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
                }
                mediaPlayer.reset();
                MediaPlayer mediaPlayer2 = this.mediaPlayer;
                if (mediaPlayer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
                }
                mediaPlayer2.setDataSource(assets.openFd(path));
                MediaPlayer mediaPlayer3 = this.mediaPlayer;
                if (mediaPlayer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
                }
                mediaPlayer3.prepare();
                MediaPlayer mediaPlayer4 = this.mediaPlayer;
                if (mediaPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
                }
                mediaPlayer4.start();
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "playAssets error. please check the file. " + e);
        }
    }

    public final boolean isPlaying() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        if (mediaPlayer.isPlaying()) {
            Pdlog.m3273d(this.TAG, "mediaPlayer isPlaying ");
            return true;
        }
        if (AudioTrackUtils.INSTANCE.isPlaying()) {
            Pdlog.m3273d(this.TAG, "AudioTrackUtils isPlaying ");
            return true;
        }
        Pdlog.m3273d(this.TAG, "isPlaying failed ");
        return false;
    }

    public final void setVolume(float volume) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer.setVolume(volume, volume);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        MediaPlayer.OnCompletionListener onCompletionListener = this.mOnCompletionListener;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(mp);
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Pdlog.m3274e(this.TAG, "onError : what = " + what + " , extra = " + extra);
        return false;
    }

    private final void volumeSlowDownFinish() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        if (mediaPlayer.isPlaying()) {
            IntProgression step = RangesKt.step(RangesKt.downTo(8, 2), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? first >= last : first <= last) {
                while (true) {
                    float f = first / 10.0f;
                    MediaPlayer mediaPlayer2 = this.mediaPlayer;
                    if (mediaPlayer2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
                    }
                    mediaPlayer2.setVolume(RobotVoicePlayer.INSTANCE.getVolume() * f, RobotVoicePlayer.INSTANCE.getVolume() * f);
                    Thread.sleep(80L);
                    MediaPlayer mediaPlayer3 = this.mediaPlayer;
                    if (mediaPlayer3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
                    }
                    if (!mediaPlayer3.isPlaying() || first == last) {
                        break;
                    } else {
                        first += step2;
                    }
                }
            }
            MediaPlayer mediaPlayer4 = this.mediaPlayer;
            if (mediaPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
            }
            mediaPlayer4.pause();
        }
        MediaPlayer mediaPlayer5 = this.mediaPlayer;
        if (mediaPlayer5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaPlayer");
        }
        mediaPlayer5.setVolume(RobotVoicePlayer.INSTANCE.getVolume(), RobotVoicePlayer.INSTANCE.getVolume());
    }
}
