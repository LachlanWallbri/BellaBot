package com.pudutech.bumblebee.robot_ui.p054ui.view.videoface;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.FileProvider;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: FaceVideoView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002noB\u0011\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u001b\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB#\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u000e\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020\u001aJ\b\u0010@\u001a\u000203H\u0002J\b\u0010A\u001a\u00020BH\u0002J\n\u0010C\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010D\u001a\u000203H\u0002J\b\u0010E\u001a\u000203H\u0002J\b\u0010F\u001a\u000203H\u0002J\u0010\u0010G\u001a\u00020(2\u0006\u0010H\u001a\u00020\u001dH\u0002J\u0006\u0010I\u001a\u00020(J\u001a\u0010J\u001a\u0002032\b\u0010K\u001a\u0004\u0018\u0001002\u0006\u0010L\u001a\u00020\u000eH\u0016J\u0012\u0010M\u001a\u0002032\b\u0010K\u001a\u0004\u0018\u000100H\u0016J\"\u0010N\u001a\u00020(2\b\u0010K\u001a\u0004\u0018\u0001002\u0006\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020\u000eH\u0016J\u0012\u0010Q\u001a\u0002032\b\u0010K\u001a\u0004\u0018\u000100H\u0016J\u0012\u0010R\u001a\u00020(2\b\u0010S\u001a\u0004\u0018\u00010TH\u0016J\u0006\u0010U\u001a\u000203J\u000e\u0010V\u001a\u0002032\u0006\u0010W\u001a\u00020XJ\b\u0010Y\u001a\u000203H\u0002J\b\u0010Z\u001a\u000203H\u0002J\u000e\u0010[\u001a\u0002032\u0006\u0010?\u001a\u00020\u001aJ\b\u0010\\\u001a\u000203H\u0002J\u000e\u0010]\u001a\u0002032\u0006\u0010^\u001a\u00020\u0017J\u0018\u0010_\u001a\u0002032\u0006\u0010`\u001a\u00020!2\u0006\u0010a\u001a\u00020\u0017H\u0002J\b\u0010b\u001a\u000203H\u0002J\b\u0010c\u001a\u000203H\u0002J\u0006\u0010d\u001a\u000203J*\u0010e\u001a\u0002032\b\u0010f\u001a\u0004\u0018\u00010g2\u0006\u0010h\u001a\u00020\u000e2\u0006\u0010i\u001a\u00020\u000e2\u0006\u0010j\u001a\u00020\u000eH\u0016J\u0012\u0010k\u001a\u0002032\b\u0010f\u001a\u0004\u0018\u00010gH\u0016J\u0012\u0010l\u001a\u0002032\b\u0010f\u001a\u0004\u0018\u00010gH\u0016J\u0010\u0010m\u001a\u0002032\u0006\u0010a\u001a\u00020\u0017H\u0002R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u001a0\u0019j\b\u0012\u0004\u0012\u00020\u001a`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u00101\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u000e\u00108\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010;\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u00105\"\u0004\b=\u00107¨\u0006p"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "Landroid/widget/RelativeLayout;", "Landroid/view/SurfaceHolder$Callback;", "Landroid/media/MediaPlayer$OnPreparedListener;", "Landroid/media/MediaPlayer$OnCompletionListener;", "Landroid/media/MediaPlayer$OnErrorListener;", "Landroid/media/MediaPlayer$OnBufferingUpdateListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "CHECK_AND_RESTART", "MEDIA_PAUSE", "MEDIA_PLAY", "MEDIA_RELEASE", "MEDIA_RESET", "MEDIA_RESET_START", "TAG", "", "animationClickListener", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "Lkotlin/collections/ArrayList;", "currentFaceVideoData", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoData;", "currentPlayHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$AnimationPlayHelper;", "currentSceneLayout", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$SceneLayout;", "errorRetryCount", "handlerThread", "Landroid/os/HandlerThread;", "hideFirstIv", "Ljava/lang/Runnable;", "isPlaying", "", "isPrepare", "isSetSf", "locationLostStopTouchJob", "Lkotlinx/coroutines/Job;", "mediaHandler", "Landroid/os/Handler;", "mediaPlayer", "Landroid/media/MediaPlayer;", "onPlayFinishListener", "Lkotlin/Function0;", "", "getOnPlayFinishListener", "()Lkotlin/jvm/functions/Function0;", "setOnPlayFinishListener", "(Lkotlin/jvm/functions/Function0;)V", "resetAndStartRunnable", "retryRunnable", "tempPlayHelper", "touchLostLocationCancelCallback", "getTouchLostLocationCancelCallback", "setTouchLostLocationCancelCallback", "addOnFaceClickListener", "listener", "cancelTouchStopLocationJab", "getFirstIv", "Landroid/view/View;", "getNextPlayVideoData", "initMediaPlayer", "initThreadIfNeed", "initView", "isPlayFaceVideoDataSame", "fvd", "isPlayLostLocation", "onBufferingUpdate", "mp", "percent", "onCompletion", "onError", "what", "extra", "onPrepared", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "pauseAnimation", "playAnimation", "anim", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "playAnimationTask", "release", "removeOnFaceClickListener", "removeRetryTask", "setTarget", TypedValues.Attributes.S_TARGET, "showSceneLayout", "sceneLayout", AIUIConstant.KEY_TAG, "startDelayedResetAndStart", "stopDelayedResetAndStart", "stopPlay", "surfaceChanged", "holder", "Landroid/view/SurfaceHolder;", "format", "width", "height", "surfaceCreated", "surfaceDestroyed", "updateSceneLayout", "AnimationPlayHelper", "SceneLayout", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FaceVideoView extends RelativeLayout implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener {
    private final int CHECK_AND_RESTART;
    private final int MEDIA_PAUSE;
    private final int MEDIA_PLAY;
    private final int MEDIA_RELEASE;
    private final int MEDIA_RESET;
    private final int MEDIA_RESET_START;
    private final String TAG;
    private HashMap _$_findViewCache;
    private final ArrayList<SingleClickListener> animationClickListener;
    private volatile FaceVideoData currentFaceVideoData;
    private AnimationPlayHelper currentPlayHelper;
    private SceneLayout currentSceneLayout;
    private int errorRetryCount;
    private HandlerThread handlerThread;
    private final Runnable hideFirstIv;
    private boolean isPlaying;
    private boolean isPrepare;
    private volatile boolean isSetSf;
    private Job locationLostStopTouchJob;
    private Handler mediaHandler;
    private MediaPlayer mediaPlayer;
    private Function0<Unit> onPlayFinishListener;
    private final Runnable resetAndStartRunnable;
    private final Runnable retryRunnable;
    private AnimationPlayHelper tempPlayHelper;
    private Function0<Unit> touchLostLocationCancelCallback;

    /* compiled from: FaceVideoView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$SceneLayout;", "", "(Ljava/lang/String;I)V", "None", "LostLocation", "Schedule", "turnAround", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum SceneLayout {
        None,
        LostLocation,
        Schedule,
        turnAround
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SceneLayout.values().length];

        static {
            $EnumSwitchMapping$0[SceneLayout.None.ordinal()] = 1;
            $EnumSwitchMapping$0[SceneLayout.LostLocation.ordinal()] = 2;
            $EnumSwitchMapping$0[SceneLayout.Schedule.ordinal()] = 3;
            $EnumSwitchMapping$0[SceneLayout.turnAround.ordinal()] = 4;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

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

    public final Function0<Unit> getTouchLostLocationCancelCallback() {
        return this.touchLostLocationCancelCallback;
    }

    public final void setTouchLostLocationCancelCallback(Function0<Unit> function0) {
        this.touchLostLocationCancelCallback = function0;
    }

    public final Function0<Unit> getOnPlayFinishListener() {
        return this.onPlayFinishListener;
    }

    public final void setOnPlayFinishListener(Function0<Unit> function0) {
        this.onPlayFinishListener = function0;
    }

    public FaceVideoView(Context context) {
        super(context);
        this.TAG = "FaceVideoView";
        this.currentSceneLayout = SceneLayout.None;
        this.animationClickListener = new ArrayList<>();
        this.retryRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$retryRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoView.this.playAnimationTask();
            }
        };
        this.resetAndStartRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$resetAndStartRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoData faceVideoData;
                Handler handler;
                int i;
                faceVideoData = FaceVideoView.this.currentFaceVideoData;
                if (faceVideoData == null) {
                    return;
                }
                FaceVideoView.this.isPrepare = false;
                handler = FaceVideoView.this.mediaHandler;
                if (handler != null) {
                    i = FaceVideoView.this.MEDIA_RESET_START;
                    handler.sendEmptyMessage(i);
                }
            }
        };
        this.MEDIA_PLAY = 101;
        this.MEDIA_RESET_START = 103;
        this.MEDIA_RESET = 104;
        this.MEDIA_RELEASE = 105;
        this.MEDIA_PAUSE = 106;
        this.CHECK_AND_RESTART = 1001;
        this.hideFirstIv = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$hideFirstIv$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                if (FaceVideoView.this.getContext() == null) {
                    return;
                }
                str = FaceVideoView.this.TAG;
                Pdlog.m3273d(str, "onPrepared iv gone");
                FaceVideoView.this.updateSceneLayout("hideFirstIv");
                ImageView iv_around_video = (ImageView) FaceVideoView.this._$_findCachedViewById(C4188R.id.iv_around_video);
                Intrinsics.checkExpressionValueIsNotNull(iv_around_video, "iv_around_video");
                iv_around_video.setVisibility(8);
                ImageView first_iv = (ImageView) FaceVideoView.this._$_findCachedViewById(C4188R.id.first_iv);
                Intrinsics.checkExpressionValueIsNotNull(first_iv, "first_iv");
                first_iv.setVisibility(8);
            }
        };
        initView();
    }

    public FaceVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "FaceVideoView";
        this.currentSceneLayout = SceneLayout.None;
        this.animationClickListener = new ArrayList<>();
        this.retryRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$retryRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoView.this.playAnimationTask();
            }
        };
        this.resetAndStartRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$resetAndStartRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoData faceVideoData;
                Handler handler;
                int i;
                faceVideoData = FaceVideoView.this.currentFaceVideoData;
                if (faceVideoData == null) {
                    return;
                }
                FaceVideoView.this.isPrepare = false;
                handler = FaceVideoView.this.mediaHandler;
                if (handler != null) {
                    i = FaceVideoView.this.MEDIA_RESET_START;
                    handler.sendEmptyMessage(i);
                }
            }
        };
        this.MEDIA_PLAY = 101;
        this.MEDIA_RESET_START = 103;
        this.MEDIA_RESET = 104;
        this.MEDIA_RELEASE = 105;
        this.MEDIA_PAUSE = 106;
        this.CHECK_AND_RESTART = 1001;
        this.hideFirstIv = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$hideFirstIv$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                if (FaceVideoView.this.getContext() == null) {
                    return;
                }
                str = FaceVideoView.this.TAG;
                Pdlog.m3273d(str, "onPrepared iv gone");
                FaceVideoView.this.updateSceneLayout("hideFirstIv");
                ImageView iv_around_video = (ImageView) FaceVideoView.this._$_findCachedViewById(C4188R.id.iv_around_video);
                Intrinsics.checkExpressionValueIsNotNull(iv_around_video, "iv_around_video");
                iv_around_video.setVisibility(8);
                ImageView first_iv = (ImageView) FaceVideoView.this._$_findCachedViewById(C4188R.id.first_iv);
                Intrinsics.checkExpressionValueIsNotNull(first_iv, "first_iv");
                first_iv.setVisibility(8);
            }
        };
        initView();
    }

    public FaceVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "FaceVideoView";
        this.currentSceneLayout = SceneLayout.None;
        this.animationClickListener = new ArrayList<>();
        this.retryRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$retryRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoView.this.playAnimationTask();
            }
        };
        this.resetAndStartRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$resetAndStartRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoData faceVideoData;
                Handler handler;
                int i2;
                faceVideoData = FaceVideoView.this.currentFaceVideoData;
                if (faceVideoData == null) {
                    return;
                }
                FaceVideoView.this.isPrepare = false;
                handler = FaceVideoView.this.mediaHandler;
                if (handler != null) {
                    i2 = FaceVideoView.this.MEDIA_RESET_START;
                    handler.sendEmptyMessage(i2);
                }
            }
        };
        this.MEDIA_PLAY = 101;
        this.MEDIA_RESET_START = 103;
        this.MEDIA_RESET = 104;
        this.MEDIA_RELEASE = 105;
        this.MEDIA_PAUSE = 106;
        this.CHECK_AND_RESTART = 1001;
        this.hideFirstIv = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$hideFirstIv$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                if (FaceVideoView.this.getContext() == null) {
                    return;
                }
                str = FaceVideoView.this.TAG;
                Pdlog.m3273d(str, "onPrepared iv gone");
                FaceVideoView.this.updateSceneLayout("hideFirstIv");
                ImageView iv_around_video = (ImageView) FaceVideoView.this._$_findCachedViewById(C4188R.id.iv_around_video);
                Intrinsics.checkExpressionValueIsNotNull(iv_around_video, "iv_around_video");
                iv_around_video.setVisibility(8);
                ImageView first_iv = (ImageView) FaceVideoView.this._$_findCachedViewById(C4188R.id.first_iv);
                Intrinsics.checkExpressionValueIsNotNull(first_iv, "first_iv");
                first_iv.setVisibility(8);
            }
        };
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View getFirstIv() {
        if (this.currentSceneLayout.ordinal() == SceneLayout.turnAround.ordinal()) {
            ImageView iv_around_video = (ImageView) _$_findCachedViewById(C4188R.id.iv_around_video);
            Intrinsics.checkExpressionValueIsNotNull(iv_around_video, "iv_around_video");
            return iv_around_video;
        }
        ImageView first_iv = (ImageView) _$_findCachedViewById(C4188R.id.first_iv);
        Intrinsics.checkExpressionValueIsNotNull(first_iv, "first_iv");
        return first_iv;
    }

    private final void initThreadIfNeed() {
        if (this.handlerThread == null) {
            this.handlerThread = new HandlerThread("FaceVideoView");
            HandlerThread handlerThread = this.handlerThread;
            if (handlerThread != null) {
                handlerThread.start();
            }
            HandlerThread handlerThread2 = this.handlerThread;
            if (handlerThread2 == null) {
                Intrinsics.throwNpe();
            }
            this.mediaHandler = new Handler(handlerThread2.getLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$initThreadIfNeed$1
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    String str;
                    String str2;
                    Runnable runnable;
                    int i;
                    int i2;
                    int i3;
                    int i4;
                    int i5;
                    int i6;
                    String str3;
                    String str4;
                    MediaPlayer mediaPlayer;
                    MediaPlayer mediaPlayer2;
                    MediaPlayer mediaPlayer3;
                    MediaPlayer mediaPlayer4;
                    MediaPlayer mediaPlayer5;
                    FaceVideoData faceVideoData;
                    MediaPlayer mediaPlayer6;
                    MediaPlayer mediaPlayer7;
                    FaceVideoData faceVideoData2;
                    MediaPlayer mediaPlayer8;
                    FaceVideoData faceVideoData3;
                    MediaPlayer mediaPlayer9;
                    MediaPlayer mediaPlayer10;
                    String str5;
                    Runnable runnable2;
                    FaceVideoView.AnimationPlayHelper animationPlayHelper;
                    String str6;
                    FaceVideoView.AnimationPlayHelper animationPlayHelper2;
                    str = FaceVideoView.this.TAG;
                    Pdlog.m3273d(str, "mediaHandler " + message.what);
                    try {
                        FaceVideoView faceVideoView = FaceVideoView.this;
                        runnable = FaceVideoView.this.hideFirstIv;
                        faceVideoView.removeCallbacks(runnable);
                        int i7 = message.what;
                        i = FaceVideoView.this.MEDIA_PLAY;
                        if (i7 == i) {
                            mediaPlayer10 = FaceVideoView.this.mediaPlayer;
                            if (mediaPlayer10 != null) {
                                mediaPlayer10.start();
                            }
                            str5 = FaceVideoView.this.TAG;
                            Pdlog.m3273d(str5, "mediaHandler start");
                            FaceVideoView faceVideoView2 = FaceVideoView.this;
                            runnable2 = FaceVideoView.this.hideFirstIv;
                            faceVideoView2.postDelayed(runnable2, 100L);
                            animationPlayHelper = FaceVideoView.this.tempPlayHelper;
                            if (animationPlayHelper != null) {
                                animationPlayHelper.setPlaying(true);
                            }
                            str6 = FaceVideoView.this.TAG;
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("tempPlayHelper MEDIA_PLAY ");
                            animationPlayHelper2 = FaceVideoView.this.tempPlayHelper;
                            sb.append(animationPlayHelper2 != null ? Boolean.valueOf(animationPlayHelper2.getIsPlaying()) : null);
                            objArr[0] = sb.toString();
                            Pdlog.m3273d(str6, objArr);
                        } else {
                            i2 = FaceVideoView.this.MEDIA_RESET_START;
                            if (i7 == i2) {
                                faceVideoData = FaceVideoView.this.currentFaceVideoData;
                                if (faceVideoData == null) {
                                    return true;
                                }
                                FaceVideoView.this.initMediaPlayer();
                                mediaPlayer6 = FaceVideoView.this.mediaPlayer;
                                if (mediaPlayer6 != null) {
                                    mediaPlayer6.reset();
                                }
                                if (SceneAnimationResources.INSTANCE.isCustom()) {
                                    faceVideoData3 = FaceVideoView.this.currentFaceVideoData;
                                    if (faceVideoData3 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    Uri uriForFile = FileProvider.getUriForFile(FaceVideoView.this.getContext(), "com.pudutech.bumblebee.FileProvider", new File(faceVideoData3.getPath()));
                                    mediaPlayer9 = FaceVideoView.this.mediaPlayer;
                                    if (mediaPlayer9 != null) {
                                        mediaPlayer9.setDataSource(FaceVideoView.this.getContext(), uriForFile);
                                    }
                                } else {
                                    mediaPlayer7 = FaceVideoView.this.mediaPlayer;
                                    if (mediaPlayer7 != null) {
                                        Context context = FaceVideoView.this.getContext();
                                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                                        AssetManager assets = context.getAssets();
                                        faceVideoData2 = FaceVideoView.this.currentFaceVideoData;
                                        if (faceVideoData2 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        mediaPlayer7.setDataSource(assets.openFd(faceVideoData2.getPath()));
                                    }
                                }
                                mediaPlayer8 = FaceVideoView.this.mediaPlayer;
                                if (mediaPlayer8 != null) {
                                    mediaPlayer8.prepareAsync();
                                }
                            } else {
                                i3 = FaceVideoView.this.MEDIA_RESET;
                                if (i7 == i3) {
                                    mediaPlayer3 = FaceVideoView.this.mediaPlayer;
                                    if (mediaPlayer3 != null) {
                                        mediaPlayer3.pause();
                                    }
                                    mediaPlayer4 = FaceVideoView.this.mediaPlayer;
                                    if (mediaPlayer4 != null) {
                                        mediaPlayer4.stop();
                                    }
                                    mediaPlayer5 = FaceVideoView.this.mediaPlayer;
                                    if (mediaPlayer5 != null) {
                                        mediaPlayer5.reset();
                                    }
                                } else {
                                    i4 = FaceVideoView.this.MEDIA_RELEASE;
                                    if (i7 == i4) {
                                        mediaPlayer2 = FaceVideoView.this.mediaPlayer;
                                        if (mediaPlayer2 != null) {
                                            mediaPlayer2.release();
                                        }
                                        FaceVideoView.this.mediaPlayer = (MediaPlayer) null;
                                    } else {
                                        i5 = FaceVideoView.this.MEDIA_PAUSE;
                                        if (i7 == i5) {
                                            mediaPlayer = FaceVideoView.this.mediaPlayer;
                                            if (mediaPlayer != null) {
                                                mediaPlayer.pause();
                                            }
                                        } else {
                                            i6 = FaceVideoView.this.CHECK_AND_RESTART;
                                            if (i7 == i6) {
                                                str3 = FaceVideoView.this.TAG;
                                                Pdlog.m3274e(str3, "CHECK_AND_RESTART : check timeout need restart animation");
                                                try {
                                                    FaceVideoView.this.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$initThreadIfNeed$1.1
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            View firstIv;
                                                            firstIv = FaceVideoView.this.getFirstIv();
                                                            firstIv.setVisibility(0);
                                                            FaceVideoView.this.stopDelayedResetAndStart();
                                                            FaceVideoView.this.startDelayedResetAndStart();
                                                        }
                                                    });
                                                } catch (Exception e) {
                                                    str4 = FaceVideoView.this.TAG;
                                                    Pdlog.m3274e(str4, "initThreadIfNeed : " + Log.getStackTraceString(e));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        str2 = FaceVideoView.this.TAG;
                        Pdlog.m3274e(str2, "mediaHandler " + message.what + " : " + Log.getStackTraceString(e2));
                    }
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSceneLayout(String tag) {
        AnimationPlayHelper animationPlayHelper = this.tempPlayHelper;
        if (animationPlayHelper != null) {
            if (animationPlayHelper == null) {
                Intrinsics.throwNpe();
            }
            showSceneLayout(animationPlayHelper.getAnim().getSceneLayout(), tag);
        } else {
            AnimationPlayHelper animationPlayHelper2 = this.currentPlayHelper;
            if (animationPlayHelper2 != null) {
                showSceneLayout(animationPlayHelper2.getAnim().getSceneLayout(), tag);
            }
        }
    }

    public final void playAnimation(FaceVideoAnimation anim) {
        MediaPlayer mediaPlayer;
        Intrinsics.checkParameterIsNotNull(anim, "anim");
        Pdlog.m3273d(this.TAG, "playAnimation " + anim);
        initThreadIfNeed();
        if (getVisibility() != 0) {
            if (anim.getSceneLayout().ordinal() == SceneLayout.turnAround.ordinal()) {
                ImageView iv_around_video = (ImageView) _$_findCachedViewById(C4188R.id.iv_around_video);
                Intrinsics.checkExpressionValueIsNotNull(iv_around_video, "iv_around_video");
                iv_around_video.setVisibility(0);
            } else {
                ImageView first_iv = (ImageView) _$_findCachedViewById(C4188R.id.first_iv);
                Intrinsics.checkExpressionValueIsNotNull(first_iv, "first_iv");
                first_iv.setVisibility(0);
            }
            setVisibility(0);
            Pdlog.m3273d(this.TAG, "getFirstIv is VISIBLE");
        }
        this.errorRetryCount = 0;
        removeRetryTask();
        if (anim.isTemp()) {
            this.tempPlayHelper = new AnimationPlayHelper(anim);
        } else {
            this.currentPlayHelper = new AnimationPlayHelper(anim);
            if (this.tempPlayHelper != null && (mediaPlayer = this.mediaPlayer) != null && mediaPlayer.isPlaying()) {
                Pdlog.m3273d(this.TAG, "playAnimation : has temp player , do not play now");
                return;
            }
        }
        showSceneLayout(anim.getSceneLayout(), "playAnimationInit");
        playAnimationTask();
    }

    public final void pauseAnimation() {
        Handler handler = this.mediaHandler;
        if (handler != null) {
            handler.removeMessages(this.CHECK_AND_RESTART);
        }
        Handler handler2 = this.mediaHandler;
        if (handler2 != null) {
            handler2.sendEmptyMessage(this.MEDIA_PAUSE);
        }
    }

    public final boolean isPlayLostLocation() {
        FaceVideoAnimation anim;
        if (getVisibility() == 0) {
            AnimationPlayHelper animationPlayHelper = this.currentPlayHelper;
            if (((animationPlayHelper == null || (anim = animationPlayHelper.getAnim()) == null) ? null : anim.getSceneLayout()) == SceneLayout.LostLocation) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAnimationTask() {
        int i;
        if (this.isSetSf) {
            FaceVideoData nextPlayVideoData = getNextPlayVideoData();
            if (nextPlayVideoData == null) {
                Function0<Unit> function0 = this.onPlayFinishListener;
                if (function0 != null) {
                    function0.invoke();
                }
                stopPlay();
                return;
            }
            try {
                if (!isPlayFaceVideoDataSame(nextPlayVideoData)) {
                    Pdlog.m3273d(this.TAG, "playAnimationTask , not same , nextFaceVideoData = " + nextPlayVideoData);
                    getFirstIv().setVisibility(0);
                    stopDelayedResetAndStart();
                    startDelayedResetAndStart();
                } else if (this.isPrepare) {
                    Handler handler = this.mediaHandler;
                    if (handler != null) {
                        handler.sendEmptyMessageDelayed(this.CHECK_AND_RESTART, 5000L);
                    }
                    Handler handler2 = this.mediaHandler;
                    if (handler2 != null) {
                        handler2.sendEmptyMessage(this.MEDIA_PLAY);
                    }
                }
                this.isPlaying = true;
                this.currentFaceVideoData = nextPlayVideoData;
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "playAnimationTask : " + Log.getStackTraceString(e));
                if ((e instanceof IOException) && (i = this.errorRetryCount) < 30) {
                    this.errorRetryCount = i + 1;
                    removeRetryTask();
                    this.isPlaying = false;
                    this.isPrepare = false;
                    this.currentFaceVideoData = (FaceVideoData) null;
                    postDelayed(this.retryRunnable, 100 * this.errorRetryCount);
                    return;
                }
                stopPlay();
            }
        }
    }

    private final void removeRetryTask() {
        removeCallbacks(this.retryRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initMediaPlayer() {
        MediaPlayer mediaPlayer;
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 == null) {
                Intrinsics.throwNpe();
            }
            mediaPlayer2.setVolume(0.0f, 0.0f);
            MediaPlayer mediaPlayer3 = this.mediaPlayer;
            if (mediaPlayer3 == null) {
                Intrinsics.throwNpe();
            }
            mediaPlayer3.setOnPreparedListener(this);
            MediaPlayer mediaPlayer4 = this.mediaPlayer;
            if (mediaPlayer4 == null) {
                Intrinsics.throwNpe();
            }
            mediaPlayer4.setOnErrorListener(this);
            MediaPlayer mediaPlayer5 = this.mediaPlayer;
            if (mediaPlayer5 == null) {
                Intrinsics.throwNpe();
            }
            mediaPlayer5.setOnBufferingUpdateListener(this);
            MediaPlayer mediaPlayer6 = this.mediaPlayer;
            if (mediaPlayer6 == null) {
                Intrinsics.throwNpe();
            }
            mediaPlayer6.setOnCompletionListener(this);
            if (this.isSetSf) {
                SurfaceView animation_sf_view = (SurfaceView) _$_findCachedViewById(C4188R.id.animation_sf_view);
                Intrinsics.checkExpressionValueIsNotNull(animation_sf_view, "animation_sf_view");
                SurfaceHolder holder = animation_sf_view.getHolder();
                Intrinsics.checkExpressionValueIsNotNull(holder, "animation_sf_view.holder");
                Surface surface = holder.getSurface();
                Intrinsics.checkExpressionValueIsNotNull(surface, "animation_sf_view.holder.surface");
                if (!surface.isValid() || (mediaPlayer = this.mediaPlayer) == null) {
                    return;
                }
                SurfaceView animation_sf_view2 = (SurfaceView) _$_findCachedViewById(C4188R.id.animation_sf_view);
                Intrinsics.checkExpressionValueIsNotNull(animation_sf_view2, "animation_sf_view");
                mediaPlayer.setDisplay(animation_sf_view2.getHolder());
            }
        }
    }

    private final FaceVideoData getNextPlayVideoData() {
        FaceVideoAnimation anim;
        FaceVideoData nextFaceVideoData;
        FaceVideoAnimation anim2;
        AnimationPlayHelper animationPlayHelper = this.tempPlayHelper;
        SceneLayout sceneLayout = null;
        if (animationPlayHelper != null) {
            if (animationPlayHelper != null && !animationPlayHelper.getIsPlaying()) {
                AnimationPlayHelper animationPlayHelper2 = this.tempPlayHelper;
                if ((animationPlayHelper2 != null ? animationPlayHelper2.getCurrent() : null) == null) {
                    AnimationPlayHelper animationPlayHelper3 = this.tempPlayHelper;
                    if (animationPlayHelper3 == null) {
                        Intrinsics.throwNpe();
                    }
                    nextFaceVideoData = animationPlayHelper3.getNextFaceVideoData();
                } else {
                    String str = this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("temp not play, keep current ");
                    AnimationPlayHelper animationPlayHelper4 = this.tempPlayHelper;
                    if (animationPlayHelper4 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(animationPlayHelper4.getCurrent());
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                    AnimationPlayHelper animationPlayHelper5 = this.tempPlayHelper;
                    if (animationPlayHelper5 == null) {
                        Intrinsics.throwNpe();
                    }
                    nextFaceVideoData = animationPlayHelper5.getCurrent();
                }
            } else {
                AnimationPlayHelper animationPlayHelper6 = this.tempPlayHelper;
                if (animationPlayHelper6 == null) {
                    Intrinsics.throwNpe();
                }
                nextFaceVideoData = animationPlayHelper6.getNextFaceVideoData();
            }
            if (nextFaceVideoData != null) {
                AnimationPlayHelper animationPlayHelper7 = this.tempPlayHelper;
                if (animationPlayHelper7 != null && (anim2 = animationPlayHelper7.getAnim()) != null) {
                    sceneLayout = anim2.getSceneLayout();
                }
                if (sceneLayout != null) {
                    showSceneLayout(sceneLayout, "tempPlayHelper");
                }
                return nextFaceVideoData;
            }
            this.tempPlayHelper = (AnimationPlayHelper) null;
        }
        AnimationPlayHelper animationPlayHelper8 = this.currentPlayHelper;
        SceneLayout sceneLayout2 = (animationPlayHelper8 == null || (anim = animationPlayHelper8.getAnim()) == null) ? null : anim.getSceneLayout();
        if (sceneLayout2 != null) {
            showSceneLayout(sceneLayout2, "currentPlayHelper");
        }
        AnimationPlayHelper animationPlayHelper9 = this.currentPlayHelper;
        if (animationPlayHelper9 != null) {
            return animationPlayHelper9.getNextFaceVideoData();
        }
        return null;
    }

    private final boolean isPlayFaceVideoDataSame(FaceVideoData fvd) {
        FaceVideoData faceVideoData = this.currentFaceVideoData;
        return Intrinsics.areEqual(faceVideoData != null ? faceVideoData.getPath() : null, fvd.getPath());
    }

    public final void stopPlay() {
        FaceVideoAnimation anim;
        FaceVideoAnimation anim2;
        Pdlog.m3273d(this.TAG, "stopPlay");
        Handler handler = this.mediaHandler;
        if (handler != null) {
            handler.removeMessages(this.CHECK_AND_RESTART);
        }
        if (getVisibility() == 8 && !this.isPlaying) {
            Pdlog.m3273d(this.TAG, "stopPlay : do not need");
            return;
        }
        AnimationPlayHelper animationPlayHelper = this.currentPlayHelper;
        if (animationPlayHelper != null && (anim2 = animationPlayHelper.getAnim()) != null) {
            anim2.onFaceVideoStop();
        }
        AnimationPlayHelper animationPlayHelper2 = this.tempPlayHelper;
        if (animationPlayHelper2 != null && (anim = animationPlayHelper2.getAnim()) != null) {
            anim.onFaceVideoStop();
        }
        removeRetryTask();
        stopDelayedResetAndStart();
        this.isPlaying = false;
        this.isPrepare = false;
        this.currentFaceVideoData = (FaceVideoData) null;
        AnimationPlayHelper animationPlayHelper3 = (AnimationPlayHelper) null;
        this.currentPlayHelper = animationPlayHelper3;
        this.tempPlayHelper = animationPlayHelper3;
        setVisibility(8);
        Handler handler2 = this.mediaHandler;
        if (handler2 != null) {
            handler2.sendEmptyMessage(this.MEDIA_RESET);
        }
        this.currentSceneLayout = SceneLayout.None;
    }

    private final void initView() {
        LayoutInflater.from(getContext()).inflate(C4188R.layout.layout_face_video, this);
        ((SurfaceView) _$_findCachedViewById(C4188R.id.animation_sf_view)).setZOrderMediaOverlay(true);
        SurfaceView animation_sf_view = (SurfaceView) _$_findCachedViewById(C4188R.id.animation_sf_view);
        Intrinsics.checkExpressionValueIsNotNull(animation_sf_view, "animation_sf_view");
        animation_sf_view.getHolder().setType(3);
        SurfaceView animation_sf_view2 = (SurfaceView) _$_findCachedViewById(C4188R.id.animation_sf_view);
        Intrinsics.checkExpressionValueIsNotNull(animation_sf_view2, "animation_sf_view");
        animation_sf_view2.getHolder().addCallback(this);
        setVisibility(8);
        if (SceneAnimationResources.INSTANCE.isCustom()) {
            ((ImageView) _$_findCachedViewById(C4188R.id.first_iv)).setImageURI(FileProvider.getUriForFile(getContext(), "com.pudutech.bumblebee.FileProvider", new File(SceneAnimationResources.INSTANCE.getSDCARD_PATH() + "/animation_first.png")));
        }
        setOnClickListener(new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:3:0x0009, code lost:
            
                r0 = r2.this$0.tempPlayHelper;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                FaceVideoView.AnimationPlayHelper animationPlayHelper;
                FaceVideoView.AnimationPlayHelper animationPlayHelper2;
                FaceVideoAnimation anim;
                ArrayList arrayList;
                FaceVideoView.AnimationPlayHelper animationPlayHelper3;
                FaceVideoView.AnimationPlayHelper animationPlayHelper4;
                FaceVideoAnimation anim2;
                animationPlayHelper = FaceVideoView.this.tempPlayHelper;
                if (animationPlayHelper == null || animationPlayHelper3 == null || !animationPlayHelper3.getIsPlaying()) {
                    animationPlayHelper2 = FaceVideoView.this.currentPlayHelper;
                    if (animationPlayHelper2 != null && (anim = animationPlayHelper2.getAnim()) != null && anim.onClick()) {
                        return;
                    }
                } else {
                    animationPlayHelper4 = FaceVideoView.this.tempPlayHelper;
                    if (animationPlayHelper4 != null && (anim2 = animationPlayHelper4.getAnim()) != null && anim2.onClick()) {
                        return;
                    }
                }
                arrayList = FaceVideoView.this.animationClickListener;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((SingleClickListener) it.next()).getOnSingleClick().invoke();
                }
            }
        }, 3, null));
    }

    private final void showSceneLayout(SceneLayout sceneLayout, String tag) {
        this.currentSceneLayout = sceneLayout;
        int i = WhenMappings.$EnumSwitchMapping$0[sceneLayout.ordinal()];
        if (i == 1) {
            TextView schedule_tv = (TextView) _$_findCachedViewById(C4188R.id.schedule_tv);
            Intrinsics.checkExpressionValueIsNotNull(schedule_tv, "schedule_tv");
            schedule_tv.setVisibility(8);
            LinearLayout lost_location_tip = (LinearLayout) _$_findCachedViewById(C4188R.id.lost_location_tip);
            Intrinsics.checkExpressionValueIsNotNull(lost_location_tip, "lost_location_tip");
            lost_location_tip.setVisibility(8);
            TextView tv_seat_no_video = (TextView) _$_findCachedViewById(C4188R.id.tv_seat_no_video);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no_video, "tv_seat_no_video");
            tv_seat_no_video.setVisibility(8);
        } else if (i == 2) {
            LinearLayout lost_location_tip2 = (LinearLayout) _$_findCachedViewById(C4188R.id.lost_location_tip);
            Intrinsics.checkExpressionValueIsNotNull(lost_location_tip2, "lost_location_tip");
            lost_location_tip2.setVisibility(0);
            TextView schedule_tv2 = (TextView) _$_findCachedViewById(C4188R.id.schedule_tv);
            Intrinsics.checkExpressionValueIsNotNull(schedule_tv2, "schedule_tv");
            schedule_tv2.setVisibility(8);
            TextView tv_seat_no_video2 = (TextView) _$_findCachedViewById(C4188R.id.tv_seat_no_video);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no_video2, "tv_seat_no_video");
            tv_seat_no_video2.setVisibility(8);
        } else if (i == 3) {
            TextView schedule_tv3 = (TextView) _$_findCachedViewById(C4188R.id.schedule_tv);
            Intrinsics.checkExpressionValueIsNotNull(schedule_tv3, "schedule_tv");
            schedule_tv3.setVisibility(0);
            LinearLayout lost_location_tip3 = (LinearLayout) _$_findCachedViewById(C4188R.id.lost_location_tip);
            Intrinsics.checkExpressionValueIsNotNull(lost_location_tip3, "lost_location_tip");
            lost_location_tip3.setVisibility(8);
            TextView tv_seat_no_video3 = (TextView) _$_findCachedViewById(C4188R.id.tv_seat_no_video);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no_video3, "tv_seat_no_video");
            tv_seat_no_video3.setVisibility(8);
        } else if (i == 4) {
            TextView schedule_tv4 = (TextView) _$_findCachedViewById(C4188R.id.schedule_tv);
            Intrinsics.checkExpressionValueIsNotNull(schedule_tv4, "schedule_tv");
            schedule_tv4.setVisibility(8);
            LinearLayout lost_location_tip4 = (LinearLayout) _$_findCachedViewById(C4188R.id.lost_location_tip);
            Intrinsics.checkExpressionValueIsNotNull(lost_location_tip4, "lost_location_tip");
            lost_location_tip4.setVisibility(8);
            TextView tv_seat_no_video4 = (TextView) _$_findCachedViewById(C4188R.id.tv_seat_no_video);
            Intrinsics.checkExpressionValueIsNotNull(tv_seat_no_video4, "tv_seat_no_video");
            tv_seat_no_video4.setVisibility(0);
        }
        Pdlog.m3273d(this.TAG, "showSceneLayout-->" + tag + " name = " + this.currentSceneLayout.name());
    }

    public final void setTarget(String target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Pdlog.m3273d(this.TAG, "setTarget : target = " + target + "; ");
        ((TextView) _$_findCachedViewById(C4188R.id.tv_seat_no_video)).setTextSize(2, 180.0f);
        TextView tv_seat_no_video = (TextView) _$_findCachedViewById(C4188R.id.tv_seat_no_video);
        Intrinsics.checkExpressionValueIsNotNull(tv_seat_no_video, "tv_seat_no_video");
        tv_seat_no_video.setText(target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDelayedResetAndStart() {
        postDelayed(this.resetAndStartRunnable, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopDelayedResetAndStart() {
        removeCallbacks(this.resetAndStartRunnable);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Pdlog.m3273d(this.TAG, "surfaceChanged " + width + ' ' + height);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        Pdlog.m3273d(this.TAG, "surfaceDestroyed");
        this.isSetSf = false;
        release();
    }

    private final void release() {
        Pdlog.m3273d(this.TAG, "release ");
        removeRetryTask();
        stopDelayedResetAndStart();
        this.isPlaying = false;
        this.isPrepare = false;
        this.currentFaceVideoData = (FaceVideoData) null;
        AnimationPlayHelper animationPlayHelper = (AnimationPlayHelper) null;
        this.currentPlayHelper = animationPlayHelper;
        this.tempPlayHelper = animationPlayHelper;
        setVisibility(8);
        this.currentSceneLayout = SceneLayout.None;
        Handler handler = this.mediaHandler;
        if (handler != null) {
            handler.sendEmptyMessage(this.MEDIA_RELEASE);
        }
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        this.mediaHandler = (Handler) null;
        this.handlerThread = (HandlerThread) null;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Pdlog.m3273d(this.TAG, "surfaceCreated");
        this.isSetSf = true;
        if (this.currentPlayHelper == null && this.tempPlayHelper == null) {
            return;
        }
        try {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setDisplay(holder);
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "surfaceCreated : " + Log.getStackTraceString(e));
        }
        playAnimationTask();
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mp) {
        Pdlog.m3273d(this.TAG, "onPrepared " + this.isPlaying);
        post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$onPrepared$1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                String str;
                Handler handler;
                int i;
                FaceVideoView.this.isPrepare = true;
                z = FaceVideoView.this.isPlaying;
                if (z) {
                    SurfaceView animation_sf_view = (SurfaceView) FaceVideoView.this._$_findCachedViewById(C4188R.id.animation_sf_view);
                    Intrinsics.checkExpressionValueIsNotNull(animation_sf_view, "animation_sf_view");
                    SurfaceHolder holder = animation_sf_view.getHolder();
                    Intrinsics.checkExpressionValueIsNotNull(holder, "animation_sf_view.holder");
                    Surface surface = holder.getSurface();
                    Intrinsics.checkExpressionValueIsNotNull(surface, "animation_sf_view.holder.surface");
                    if (surface.isValid()) {
                        try {
                            handler = FaceVideoView.this.mediaHandler;
                            if (handler != null) {
                                i = FaceVideoView.this.MEDIA_PLAY;
                                handler.sendEmptyMessage(i);
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            str = FaceVideoView.this.TAG;
                            Pdlog.m3274e(str, "onPrepared : " + Log.getStackTraceString(e));
                            FaceVideoView.this.stopPlay();
                            return;
                        }
                    }
                }
                FaceVideoView.this.stopPlay();
            }
        });
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        Handler handler = this.mediaHandler;
        if (handler != null) {
            handler.removeMessages(this.CHECK_AND_RESTART);
        }
        post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoView$onCompletion$1
            /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
            
                r0 = r4.this$0.tempPlayHelper;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                FaceVideoView.AnimationPlayHelper animationPlayHelper;
                FaceVideoView.AnimationPlayHelper animationPlayHelper2;
                String str;
                FaceVideoAnimation anim;
                FaceVideoView.AnimationPlayHelper animationPlayHelper3;
                FaceVideoView.AnimationPlayHelper animationPlayHelper4;
                String str2;
                FaceVideoAnimation anim2;
                animationPlayHelper = FaceVideoView.this.tempPlayHelper;
                if (animationPlayHelper == null || animationPlayHelper3 == null || !animationPlayHelper3.getIsPlaying()) {
                    animationPlayHelper2 = FaceVideoView.this.currentPlayHelper;
                    if (animationPlayHelper2 != null && (anim = animationPlayHelper2.getAnim()) != null) {
                        anim.onAnimationFinish();
                    }
                    str = FaceVideoView.this.TAG;
                    Pdlog.m3273d(str, "currentPlayHelper onCompletion");
                } else {
                    animationPlayHelper4 = FaceVideoView.this.tempPlayHelper;
                    if (animationPlayHelper4 != null && (anim2 = animationPlayHelper4.getAnim()) != null) {
                        anim2.onAnimationFinish();
                    }
                    str2 = FaceVideoView.this.TAG;
                    Pdlog.m3273d(str2, "tempPlayHelper onCompletion");
                }
                FaceVideoView.this.playAnimationTask();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FaceVideoView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u0004\u0018\u00010\bJ\u0006\u0010\u0015\u001a\u00020\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView$AnimationPlayHelper;", "", "anim", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "(Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;)V", "getAnim", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "current", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoData;", "getCurrent", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoData;", "setCurrent", "(Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoData;)V", "index", "", "isPlaying", "", "()Z", "setPlaying", "(Z)V", "getNextFaceVideoData", "reset", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class AnimationPlayHelper {
        private final FaceVideoAnimation anim;
        private FaceVideoData current;
        private int index;
        private boolean isPlaying;

        public AnimationPlayHelper(FaceVideoAnimation anim) {
            Intrinsics.checkParameterIsNotNull(anim, "anim");
            this.anim = anim;
            this.index = -1;
        }

        public final FaceVideoAnimation getAnim() {
            return this.anim;
        }

        /* renamed from: isPlaying, reason: from getter */
        public final boolean getIsPlaying() {
            return this.isPlaying;
        }

        public final void setPlaying(boolean z) {
            this.isPlaying = z;
        }

        public final FaceVideoData getCurrent() {
            return this.current;
        }

        public final void setCurrent(FaceVideoData faceVideoData) {
            this.current = faceVideoData;
        }

        public final FaceVideoData getNextFaceVideoData() {
            if (this.anim.getPlayMode() == FaceVideoPlayMode.Random) {
                this.current = this.anim.getVideoDatas().get(Random.INSTANCE.nextInt(0, this.anim.getVideoDatas().size()));
                return this.current;
            }
            this.index++;
            if (this.anim.getVideoDatas().size() <= this.index) {
                if (this.anim.isLooper()) {
                    this.index = 0;
                } else {
                    this.current = (FaceVideoData) null;
                    return null;
                }
            }
            FaceVideoData faceVideoData = this.anim.getVideoDatas().get(this.index);
            Intrinsics.checkExpressionValueIsNotNull(faceVideoData, "anim.videoDatas[index]");
            FaceVideoData faceVideoData2 = faceVideoData;
            this.current = faceVideoData2;
            if (faceVideoData2.isLooper() || faceVideoData2.getReplayTimes() > 0) {
                this.index--;
                if (faceVideoData2.getReplayTimes() > 0) {
                    faceVideoData2.setReplayTimes(faceVideoData2.getReplayTimes() - 1);
                }
            }
            return faceVideoData2;
        }

        public final void reset() {
            this.index = -1;
        }
    }

    public final void addOnFaceClickListener(SingleClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.animationClickListener.contains(listener)) {
            return;
        }
        this.animationClickListener.add(listener);
    }

    public final void removeOnFaceClickListener(SingleClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.animationClickListener.remove(listener);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Job launch$default;
        if (this.currentSceneLayout == SceneLayout.LostLocation) {
            Pdlog.m3273d(this.TAG, "onTouchEvent : LostLocation event = " + event + "; ");
            Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
            if (valueOf != null && valueOf.intValue() == 5) {
                Pdlog.m3273d(this.TAG, "onTouchEvent : start " + event + ' ' + this.locationLostStopTouchJob);
                if (this.locationLostStopTouchJob == null) {
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new FaceVideoView$onTouchEvent$1(this, null), 3, null);
                    this.locationLostStopTouchJob = launch$default;
                }
            } else if ((valueOf != null && valueOf.intValue() == 6) || (valueOf != null && valueOf.intValue() == 3)) {
                Pdlog.m3273d(this.TAG, "onTouchEvent : stop " + event);
                cancelTouchStopLocationJab();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private final void cancelTouchStopLocationJab() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new FaceVideoView$cancelTouchStopLocationJab$1(this, null), 3, null);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Pdlog.m3274e(this.TAG, "onError : " + what + " : " + extra);
        return false;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Pdlog.m3273d(this.TAG, "onBufferingUpdate : percent = " + percent + "; ");
    }
}
