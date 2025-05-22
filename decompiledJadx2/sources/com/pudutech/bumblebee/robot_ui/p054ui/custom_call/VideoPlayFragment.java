package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.VideoView;
import androidx.core.app.NotificationCompat;
import com.danikula.videocache.CacheListener;
import com.iflytek.aiui.AIUIConstant;
import com.pudu.demo.video.VideoProxyHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallVideoBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.VideoPlayFragment;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPlayFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002:\u0002CDB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\u0005H\u0016J\u0010\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u0011H\u0016J$\u00101\u001a\u00020-2\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u00010\u00072\u0006\u00105\u001a\u00020\u0005H\u0016J\b\u00106\u001a\u00020-H\u0016J\b\u00107\u001a\u00020-H\u0016J\b\u00108\u001a\u00020-H\u0016J\b\u00109\u001a\u00020-H\u0016J\b\u0010:\u001a\u00020-H\u0016J\b\u0010;\u001a\u00020-H\u0002J\b\u0010<\u001a\u00020-H\u0002J\u0010\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020\u000eH\u0002J\u0010\u0010>\u001a\u00020-2\u0006\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020-H\u0002J\u0006\u0010B\u001a\u00020-R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001f\u0010\u0018\u001a\u00060\u0019R\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0018\u00010'R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000¨\u0006E"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment;", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BaseCustomCallFragment;", "Lcom/danikula/videocache/CacheListener;", "()V", "REPLAY_TOTAL_TIMES", "", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "WHAT_PSB", "isHavedAdd", "", "mCloseShowCount", "mCloseView", "Landroid/view/View;", "mCustomCallVideoBean", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallVideoBean;", "getMCustomCallVideoBean", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallVideoBean;", "setMCustomCallVideoBean", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallVideoBean;)V", "mDefOnCompletionListener", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment$DefOnCompletionListener;", "getMDefOnCompletionListener", "()Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment$DefOnCompletionListener;", "mDefOnCompletionListener$delegate", "Lkotlin/Lazy;", "mFragVideoSbar", "Landroid/widget/SeekBar;", "mReplayTimes", "mRootContain", "Landroid/view/ViewGroup;", "mUrl", "mVideoCount", "mVideoCur", "mVideoProgressUpdater", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment$VideoProgressUpdater;", "mVideoProxyHelper", "Lcom/pudu/demo/video/VideoProxyHelper;", "mVideoView", "Landroid/widget/VideoView;", "checkCachedState", "", "getLayoutId", "initview", "view", "onCacheAvailable", "cacheFile", "Ljava/io/File;", "url", "percentsAvailable", "onDestroy", "onDetach", "onPause", "onResume", "setData", "setVideoData", "startVideo", "isRegisterCacheListener", "updateCustomCallContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "updateDsiplayUi", "videoViewPause", "DefOnCompletionListener", "VideoProgressUpdater", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VideoPlayFragment extends BaseCustomCallFragment implements CacheListener {
    private HashMap _$_findViewCache;
    private boolean isHavedAdd;
    private View mCloseView;
    private CustomCallVideoBean mCustomCallVideoBean;
    private SeekBar mFragVideoSbar;
    private ViewGroup mRootContain;
    private String mUrl;
    private int mVideoCur;
    private VideoProgressUpdater mVideoProgressUpdater;
    private VideoProxyHelper mVideoProxyHelper;
    private VideoView mVideoView;
    private String TAG = "VideoPlayFragment";
    private final int WHAT_PSB = 1;
    private int mCloseShowCount = -1;
    private int REPLAY_TOTAL_TIMES = 10;
    private int mReplayTimes = 1;
    private int mVideoCount = 1;

    /* renamed from: mDefOnCompletionListener$delegate, reason: from kotlin metadata */
    private final Lazy mDefOnCompletionListener = LazyKt.lazy(new Function0<DefOnCompletionListener>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.VideoPlayFragment$mDefOnCompletionListener$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VideoPlayFragment.DefOnCompletionListener invoke() {
            return new VideoPlayFragment.DefOnCompletionListener();
        }
    });

    private final DefOnCompletionListener getMDefOnCompletionListener() {
        return (DefOnCompletionListener) this.mDefOnCompletionListener.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment, com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment, com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment, com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ SeekBar access$getMFragVideoSbar$p(VideoPlayFragment videoPlayFragment) {
        SeekBar seekBar = videoPlayFragment.mFragVideoSbar;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragVideoSbar");
        }
        return seekBar;
    }

    public static final /* synthetic */ String access$getMUrl$p(VideoPlayFragment videoPlayFragment) {
        String str = videoPlayFragment.mUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUrl");
        }
        return str;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public int getLayoutId() {
        return C4188R.layout.fragment_video_play;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void initview(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Function1<Boolean, Unit> showStateBar = getShowStateBar();
        if (showStateBar != null) {
            showStateBar.invoke(true);
        }
        View findViewById = view.findViewById(C4188R.id.frag_video_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.frag_video_view)");
        this.mVideoView = (VideoView) findViewById;
        this.mRootContain = (ViewGroup) view.findViewById(C4188R.id.frag_video_root);
        View findViewById2 = view.findViewById(C4188R.id.frag_video_sbar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.frag_video_sbar)");
        this.mFragVideoSbar = (SeekBar) findViewById2;
        SeekBar seekBar = this.mFragVideoSbar;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragVideoSbar");
        }
        seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.VideoPlayFragment$initview$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return true;
            }
        });
        SeekBar seekBar2 = this.mFragVideoSbar;
        if (seekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragVideoSbar");
        }
        seekBar2.setClickable(false);
        this.mCloseView = view.findViewById(C4188R.id.frag_video_close);
        Activity mActivity = getMActivity();
        if (mActivity != null) {
            Context applicationContext = mActivity.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "it.applicationContext");
            this.mVideoProxyHelper = new VideoProxyHelper(applicationContext);
        }
        this.mVideoProgressUpdater = new VideoProgressUpdater();
        View view2 = this.mCloseView;
        if (view2 != null) {
            view2.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.VideoPlayFragment$initview$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view3) {
                    invoke2(view3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = VideoPlayFragment.this.getOnActionState();
                    if (onActionState != null) {
                        onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                    }
                }
            }, 3, null));
        }
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setOnCompletionListener(getMDefOnCompletionListener());
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView2.setOnErrorListener(new VideoPlayFragment$initview$4(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VideoPlayFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment$DefOnCompletionListener;", "Landroid/media/MediaPlayer$OnCompletionListener;", "(Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment;)V", "onCompletion", "", "mp", "Landroid/media/MediaPlayer;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final class DefOnCompletionListener implements MediaPlayer.OnCompletionListener {
        public DefOnCompletionListener() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mp) {
            String access$getMUrl$p;
            List<String> urls;
            View view;
            if (!VideoPlayFragment.this.isHavedAdd) {
                if (VideoPlayFragment.this.mCloseShowCount != -1 && VideoPlayFragment.this.mReplayTimes == VideoPlayFragment.this.mCloseShowCount && (view = VideoPlayFragment.this.mCloseView) != null) {
                    view.setVisibility(0);
                }
                VideoPlayFragment.this.mReplayTimes++;
                VideoPlayFragment.this.isHavedAdd = true;
                Pdlog.m3273d(VideoPlayFragment.this.getTAG(), "setOnCompletionListener进来--mReplayTimes：" + VideoPlayFragment.this.mReplayTimes + "###REPLAY_TOTAL_TIMES：" + VideoPlayFragment.this.REPLAY_TOTAL_TIMES);
                if (VideoPlayFragment.this.REPLAY_TOTAL_TIMES == -1 || VideoPlayFragment.this.mReplayTimes <= VideoPlayFragment.this.REPLAY_TOTAL_TIMES) {
                    if (VideoPlayFragment.this.mVideoCount != 1) {
                        if (VideoPlayFragment.this.mVideoCur >= VideoPlayFragment.this.mVideoCount - 1) {
                            VideoPlayFragment.this.mVideoCur = -1;
                        }
                        VideoPlayFragment.this.mVideoCur++;
                        VideoPlayFragment videoPlayFragment = VideoPlayFragment.this;
                        CustomCallVideoBean mCustomCallVideoBean = videoPlayFragment.getMCustomCallVideoBean();
                        if (mCustomCallVideoBean == null || (urls = mCustomCallVideoBean.getUrls()) == null || (access$getMUrl$p = urls.get(VideoPlayFragment.this.mVideoCur)) == null) {
                            access$getMUrl$p = VideoPlayFragment.access$getMUrl$p(VideoPlayFragment.this);
                        }
                        videoPlayFragment.mUrl = access$getMUrl$p;
                        VideoPlayFragment.this.checkCachedState();
                        VideoPlayFragment.access$getMFragVideoSbar$p(VideoPlayFragment.this).setProgress(0);
                        VideoPlayFragment.this.startVideo();
                        Pdlog.m3273d(VideoPlayFragment.this.getTAG(), "多条视频mUrl：" + VideoPlayFragment.access$getMUrl$p(VideoPlayFragment.this));
                    } else {
                        VideoPlayFragment.access$getMFragVideoSbar$p(VideoPlayFragment.this).setProgress(0);
                        VideoPlayFragment.this.startVideo(false);
                        Pdlog.m3273d(VideoPlayFragment.this.getTAG(), "重复一条视频的播放");
                    }
                    Pdlog.m3274e(VideoPlayFragment.this.getTAG(), "setOnCompletionListener--REPLAY_TOTAL_TIMES：" + VideoPlayFragment.this.REPLAY_TOTAL_TIMES + "---mReplayTimes:" + VideoPlayFragment.this.mReplayTimes + "---mCloseShowCount:" + VideoPlayFragment.this.mCloseShowCount);
                    return;
                }
                Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = VideoPlayFragment.this.getOnActionState();
                if (onActionState != null) {
                    onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                }
                Pdlog.m3273d(VideoPlayFragment.this.getTAG(), "setOnCompletionListener完成--mReplayTimes：" + VideoPlayFragment.this.mReplayTimes);
                return;
            }
            Pdlog.m3273d(VideoPlayFragment.this.getTAG(), "setOnCompletionListener视频已经播放次数已经加了:" + VideoPlayFragment.this.mReplayTimes);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void setData() {
        View view;
        CustomCallVideoBean customCallVideoBean = this.mCustomCallVideoBean;
        if (customCallVideoBean != null) {
            Integer playCount = customCallVideoBean.getPlayCount();
            this.REPLAY_TOTAL_TIMES = playCount != null ? playCount.intValue() : -1;
            Integer cancelBtnTime = customCallVideoBean.getCancelBtnTime();
            this.mCloseShowCount = cancelBtnTime != null ? cancelBtnTime.intValue() : -1;
            List<String> urls = customCallVideoBean.getUrls();
            if (urls != null && urls.size() > 0) {
                this.mVideoCount = urls.size();
                this.mUrl = urls.get(this.mVideoCur);
                checkCachedState();
                setVideoData();
                Pdlog.m3273d(this.TAG, "updateCustomCallContent--- " + String.valueOf(this.mCustomCallVideoBean));
            }
            if (this.mCloseShowCount != 0 || (view = this.mCloseView) == null) {
                return;
            }
            view.setVisibility(0);
            return;
        }
        Pdlog.m3273d(this.TAG, "updateCustomCallContent---mCustomCallVideoBean is null");
    }

    protected final CustomCallVideoBean getMCustomCallVideoBean() {
        return this.mCustomCallVideoBean;
    }

    protected final void setMCustomCallVideoBean(CustomCallVideoBean customCallVideoBean) {
        this.mCustomCallVideoBean = customCallVideoBean;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment
    public void updateCustomCallContent(ICustomCallBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.mCustomCallVideoBean = (CustomCallVideoBean) content;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startVideo(boolean isRegisterCacheListener) {
        VideoProxyHelper videoProxyHelper = this.mVideoProxyHelper;
        if (videoProxyHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
        }
        String str = this.mUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUrl");
        }
        String proxyUrl$default = VideoProxyHelper.getProxyUrl$default(videoProxyHelper, str, null, 2, null);
        VideoProxyHelper videoProxyHelper2 = this.mVideoProxyHelper;
        if (videoProxyHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
        }
        String str2 = this.mUrl;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUrl");
        }
        boolean isCached = videoProxyHelper2.isCached(str2);
        if (isRegisterCacheListener && !isCached) {
            VideoProxyHelper videoProxyHelper3 = this.mVideoProxyHelper;
            if (videoProxyHelper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
            }
            VideoPlayFragment videoPlayFragment = this;
            String str3 = this.mUrl;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUrl");
            }
            videoProxyHelper3.registerCacheListener(videoPlayFragment, str3);
        }
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setVideoPath(proxyUrl$default);
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startVideo() {
        startVideo(true);
    }

    private final void setVideoData() {
        startVideo();
        VideoProgressUpdater videoProgressUpdater = this.mVideoProgressUpdater;
        if (videoProgressUpdater != null) {
            videoProgressUpdater.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkCachedState() {
        VideoProxyHelper videoProxyHelper = this.mVideoProxyHelper;
        if (videoProxyHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
        }
        String str = this.mUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUrl");
        }
        if (videoProxyHelper.isCached(str)) {
            SeekBar seekBar = this.mFragVideoSbar;
            if (seekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFragVideoSbar");
            }
            if (seekBar != null) {
                seekBar.setSecondaryProgress(100);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.resume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.pause();
    }

    public final void videoViewPause() {
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.pause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        List<String> urls;
        super.onDestroy();
        VideoProgressUpdater videoProgressUpdater = this.mVideoProgressUpdater;
        if (videoProgressUpdater != null) {
            videoProgressUpdater.stop();
        }
        CustomCallVideoBean customCallVideoBean = this.mCustomCallVideoBean;
        if (customCallVideoBean != null && (urls = customCallVideoBean.getUrls()) != null) {
            for (String str : urls) {
                VideoProxyHelper videoProxyHelper = this.mVideoProxyHelper;
                if (videoProxyHelper == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
                }
                VideoPlayFragment videoPlayFragment = this;
                String str2 = this.mUrl;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUrl");
                }
                videoProxyHelper.unregisterCacheListener(videoPlayFragment, str2);
            }
        }
        VideoProxyHelper videoProxyHelper2 = this.mVideoProxyHelper;
        if (videoProxyHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
        }
        videoProxyHelper2.onDestory();
        this.mVideoProgressUpdater = (VideoProgressUpdater) null;
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setOnCompletionListener(null);
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView2.stopPlayback();
        VideoView videoView3 = this.mVideoView;
        if (videoView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView3.suspend();
        ViewGroup viewGroup = this.mRootContain;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    @Override // com.danikula.videocache.CacheListener
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
        SeekBar seekBar = this.mFragVideoSbar;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragVideoSbar");
        }
        if (seekBar != null) {
            seekBar.setSecondaryProgress(percentsAvailable);
        }
        if (percentsAvailable == 50 || percentsAvailable == 100) {
            Pdlog.m3273d(this.TAG, "onCacheAvailable--", "file path:" + cacheFile + "---url:" + url + "---" + percentsAvailable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDsiplayUi() {
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        int intValue = (videoView != null ? Integer.valueOf(videoView.getCurrentPosition()) : null).intValue() * 100;
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        int intValue2 = intValue / (videoView2 != null ? Integer.valueOf(videoView2.getDuration()) : null).intValue();
        SeekBar seekBar = this.mFragVideoSbar;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragVideoSbar");
        }
        if (seekBar != null) {
            seekBar.setProgress(intValue2);
        }
        if (!this.isHavedAdd || intValue2 >= 30) {
            return;
        }
        this.isHavedAdd = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VideoPlayFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment$VideoProgressUpdater;", "Landroid/os/Handler;", "(Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/VideoPlayFragment;)V", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "start", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final class VideoProgressUpdater extends Handler {
        public VideoProgressUpdater() {
            super(Looper.myLooper());
        }

        public final void start() {
            sendEmptyMessageDelayed(VideoPlayFragment.this.WHAT_PSB, 500L);
        }

        public final void stop() {
            if (hasMessages(VideoPlayFragment.this.WHAT_PSB)) {
                removeMessages(VideoPlayFragment.this.WHAT_PSB);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            if (msg.what == VideoPlayFragment.this.WHAT_PSB) {
                VideoPlayFragment.this.updateDsiplayUi();
                sendEmptyMessageDelayed(VideoPlayFragment.this.WHAT_PSB, 500L);
            }
        }
    }
}
