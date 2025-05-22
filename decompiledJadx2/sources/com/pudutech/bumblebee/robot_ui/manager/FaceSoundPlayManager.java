package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceSoundPlayManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u00162\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0002J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0004J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0006\u0010%\u001a\u00020\u001dJ\b\u0010&\u001a\u00020\u001dH\u0002J\u0010\u0010'\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R-\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0016`\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/FaceSoundPlayManager;", "", "()V", "PLAY", "", "RELEASE", "RELEASE_ALL", "START", "TAG", "", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "currentPlayId", "handlerThread", "Landroid/os/HandlerThread;", "mediaPlayerMap", "Ljava/util/HashMap;", "Landroid/media/MediaPlayer;", "Lkotlin/collections/HashMap;", "getMediaPlayerMap", "()Ljava/util/HashMap;", "playHandler", "Landroid/os/Handler;", "createHandlerIfNeed", "", "createHandlerThreadIfNeed", "getMediaPlayer", "resId", "playId", "play", "playMediaPlayer", "release", "releaseAll", "releaseAllTask", "start", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class FaceSoundPlayManager {
    public static final int PLAY = 101;
    public static final int RELEASE = 102;
    public static final int RELEASE_ALL = 103;
    public static final int START = 104;
    private static Context context;
    private static int currentPlayId;
    private static HandlerThread handlerThread;
    private static Handler playHandler;
    public static final FaceSoundPlayManager INSTANCE = new FaceSoundPlayManager();
    private static final String TAG = FaceSoundPlayManager.class.getSimpleName();
    private static final HashMap<Integer, MediaPlayer> mediaPlayerMap = new HashMap<>();

    private FaceSoundPlayManager() {
    }

    public final HashMap<Integer, MediaPlayer> getMediaPlayerMap() {
        return mediaPlayerMap;
    }

    public final Context getContext() {
        return context;
    }

    public final void setContext(Context context2) {
        context = context2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playMediaPlayer(int resId) {
        synchronized (mediaPlayerMap) {
            currentPlayId++;
            if (currentPlayId > 1000) {
                currentPlayId = 0;
            }
            MediaPlayer mediaPlayer = INSTANCE.getMediaPlayer(resId, currentPlayId);
            if (mediaPlayer == null) {
                return;
            }
            mediaPlayerMap.put(Integer.valueOf(currentPlayId), mediaPlayer);
            mediaPlayer.prepareAsync();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void play(int resId) {
        createHandlerThreadIfNeed();
        createHandlerIfNeed();
        Message.obtain(playHandler, 101, Integer.valueOf(resId)).sendToTarget();
    }

    private final void createHandlerThreadIfNeed() {
        if (handlerThread == null) {
            handlerThread = new HandlerThread("btn_sound_media");
            HandlerThread handlerThread2 = handlerThread;
            if (handlerThread2 == null) {
                Intrinsics.throwNpe();
            }
            handlerThread2.start();
        }
    }

    private final void createHandlerIfNeed() {
        if (playHandler == null) {
            HandlerThread handlerThread2 = handlerThread;
            if (handlerThread2 == null) {
                Intrinsics.throwNpe();
            }
            final Looper looper = handlerThread2.getLooper();
            playHandler = new Handler(looper) { // from class: com.pudutech.bumblebee.robot_ui.manager.FaceSoundPlayManager$createHandlerIfNeed$1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    switch (msg.what) {
                        case 101:
                            FaceSoundPlayManager faceSoundPlayManager = FaceSoundPlayManager.INSTANCE;
                            Object obj = msg.obj;
                            if (obj == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                            }
                            faceSoundPlayManager.playMediaPlayer(((Integer) obj).intValue());
                            return;
                        case 102:
                            FaceSoundPlayManager faceSoundPlayManager2 = FaceSoundPlayManager.INSTANCE;
                            Object obj2 = msg.obj;
                            if (obj2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                            }
                            faceSoundPlayManager2.release(((Integer) obj2).intValue());
                            return;
                        case 103:
                            FaceSoundPlayManager.INSTANCE.releaseAllTask();
                            return;
                        case 104:
                            FaceSoundPlayManager faceSoundPlayManager3 = FaceSoundPlayManager.INSTANCE;
                            Object obj3 = msg.obj;
                            if (obj3 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                            }
                            faceSoundPlayManager3.start(((Integer) obj3).intValue());
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void start(int resId) {
        synchronized (mediaPlayerMap) {
            MediaPlayer mediaPlayer = mediaPlayerMap.get(Integer.valueOf(resId));
            if (mediaPlayer != null) {
                mediaPlayer.start();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final MediaPlayer getMediaPlayer(final int resId, final int playId) {
        try {
            Context context2 = context;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            AssetFileDescriptor openRawResourceFd = context2.getResources().openRawResourceFd(resId);
            if (openRawResourceFd == null) {
                Pdlog.m3273d(TAG, "unable to get sound file descriptor ");
            }
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.FaceSoundPlayManager$getMediaPlayer$1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    String str;
                    Handler handler;
                    FaceSoundPlayManager faceSoundPlayManager = FaceSoundPlayManager.INSTANCE;
                    str = FaceSoundPlayManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("OnCompletionListener ");
                    sb.append(resId);
                    sb.append("  ");
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb.append(currentThread.getName());
                    Pdlog.m3273d(str, sb.toString());
                    FaceSoundPlayManager faceSoundPlayManager2 = FaceSoundPlayManager.INSTANCE;
                    handler = FaceSoundPlayManager.playHandler;
                    Message.obtain(handler, 102, Integer.valueOf(playId)).sendToTarget();
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.FaceSoundPlayManager$getMediaPlayer$2
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    String str;
                    Handler handler;
                    FaceSoundPlayManager faceSoundPlayManager = FaceSoundPlayManager.INSTANCE;
                    str = FaceSoundPlayManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("OnPreparedListener ");
                    sb.append(resId);
                    sb.append("   ");
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb.append(currentThread.getName());
                    Pdlog.m3273d(str, sb.toString());
                    FaceSoundPlayManager faceSoundPlayManager2 = FaceSoundPlayManager.INSTANCE;
                    handler = FaceSoundPlayManager.playHandler;
                    Message.obtain(handler, 104, Integer.valueOf(playId)).sendToTarget();
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(openRawResourceFd, "openRawResourceFd");
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            mediaPlayer.setAudioStreamType(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE());
            return mediaPlayer;
        } catch (Throwable th) {
            Pdlog.m3274e(TAG, th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release(int resId) {
        synchronized (mediaPlayerMap) {
            try {
                MediaPlayer mediaPlayer = mediaPlayerMap.get(Integer.valueOf(resId));
                mediaPlayerMap.remove(Integer.valueOf(resId));
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                if (mediaPlayer != null) {
                    mediaPlayer.reset();
                }
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    Unit unit = Unit.INSTANCE;
                }
            } catch (Exception e) {
                Pdlog.m3274e(TAG, e);
                Unit unit2 = Unit.INSTANCE;
            }
        }
    }

    public final void releaseAll() {
        Handler handler = playHandler;
        if (handler != null) {
            Message.obtain(handler, 103).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseAllTask() {
        synchronized (mediaPlayerMap) {
            Iterator<Map.Entry<Integer, MediaPlayer>> it = mediaPlayerMap.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().release();
            }
            mediaPlayerMap.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
