package com.pudutech.bumblebee.robot_ui.advertise;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import com.danikula.videocache.CacheListener;
import com.iflytek.aiui.AIUIConstant;
import com.pudu.demo.video.VideoProxyHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment;
import com.pudutech.bumblebee.robot_ui.advertise.AdverConfig;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdVideoFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002KLB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0016J\b\u0010,\u001a\u00020*H\u0016J\u0012\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\fH\u0016J$\u00101\u001a\u00020*2\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00105\u001a\u00020\fH\u0016J\b\u00106\u001a\u00020*H\u0016J\b\u00107\u001a\u00020*H\u0016J\b\u00108\u001a\u00020*H\u0016J\b\u00109\u001a\u00020*H\u0016J\u001a\u0010:\u001a\u00020*2\u0006\u0010;\u001a\u00020<2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u0010=\u001a\u00020*H\u0002J\b\u0010>\u001a\u00020*H\u0002J\u0010\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020(H\u0002J\b\u0010A\u001a\u00020*H\u0002J\u0010\u0010B\u001a\u00020*2\u0006\u0010@\u001a\u00020(H\u0016J\u0010\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020\u000eH\u0002J\u0010\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020*H\u0002J\b\u0010I\u001a\u00020*H\u0002J\b\u0010J\u001a\u00020*H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0014\u001a\u00060\u0015R\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0018\u00010\"R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseFragment;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "Lcom/danikula/videocache/CacheListener;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "TIME_INTERVALE", "", "WHAT_PSB", "", "isPrepared", "", "mAdVideoInfo", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoInfoBean;", "mAdverVideoBean", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverVideoBean;", "mCurrentPosition", "mDefOnCompletionListener", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment$DefOnCompletionListener;", "getMDefOnCompletionListener", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment$DefOnCompletionListener;", "mDefOnCompletionListener$delegate", "Lkotlin/Lazy;", "mIsPause", "mMediaPlayer", "Landroid/media/MediaPlayer;", "mRootContain", "Landroid/view/ViewGroup;", "mVideoCount", "mVideoCur", "mVideoProgressUpdater", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment$VideoProgressUpdater;", "mVideoProxyHelper", "Lcom/pudu/demo/video/VideoProxyHelper;", "mVideoView", "Landroid/widget/VideoView;", "mVolume", "", "checkCachedState", "", "createObserver", "initData", "initView", "savedInstanceState", "Landroid/os/Bundle;", "layoutId", "onCacheAvailable", "cacheFile", "Ljava/io/File;", "url", "percentsAvailable", "onDestroy", "onDetach", "onPause", "onResume", "onViewCreated", "view", "Landroid/view/View;", "playPause", "playResume", "setMediaVolume", "volume", "setVideoData", "setVolume", "startVideo", "isRegisterCacheListener", "updateAdverContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "updateDsiplayUi", "videoPause", "videoResume", "DefOnCompletionListener", "VideoProgressUpdater", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdVideoFragment extends AdverBaseFragment<AdVm> implements CacheListener {
    private HashMap _$_findViewCache;
    private boolean isPrepared;
    private AdVideoInfoBean mAdVideoInfo;
    private AdverVideoBean mAdverVideoBean;
    private int mCurrentPosition;
    private MediaPlayer mMediaPlayer;
    private ViewGroup mRootContain;
    private int mVideoCur;
    private VideoProgressUpdater mVideoProgressUpdater;
    private VideoProxyHelper mVideoProxyHelper;
    private VideoView mVideoView;
    private final String TAG = "AdVideoFragment";
    private final int WHAT_PSB = 1;
    private int mVideoCount = 1;

    /* renamed from: mDefOnCompletionListener$delegate, reason: from kotlin metadata */
    private final Lazy mDefOnCompletionListener = LazyKt.lazy(new Function0<DefOnCompletionListener>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$mDefOnCompletionListener$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AdVideoFragment.DefOnCompletionListener invoke() {
            return new AdVideoFragment.DefOnCompletionListener();
        }
    });
    private volatile boolean mIsPause = true;
    private float mVolume = 1.0f;
    private final long TIME_INTERVALE = 10000;

    private final DefOnCompletionListener getMDefOnCompletionListener() {
        return (DefOnCompletionListener) this.mDefOnCompletionListener.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment, com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment, com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
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

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment, com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ AdVideoInfoBean access$getMAdVideoInfo$p(AdVideoFragment adVideoFragment) {
        AdVideoInfoBean adVideoInfoBean = adVideoFragment.mAdVideoInfo;
        if (adVideoInfoBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        return adVideoInfoBean;
    }

    public static final /* synthetic */ VideoView access$getMVideoView$p(AdVideoFragment adVideoFragment) {
        VideoView videoView = adVideoFragment.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        return videoView;
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public int layoutId() {
        return C4188R.layout.fragment_adver_video;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        View findViewById = view.findViewById(C4188R.id.adver_video_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.adver_video_view)");
        this.mVideoView = (VideoView) findViewById;
        this.mRootContain = (ViewGroup) view.findViewById(C4188R.id.adver_video_root);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void initView(Bundle savedInstanceState) {
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setZOrderOnTop(true);
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView2.setZOrderMediaOverlay(true);
        this.mVideoProgressUpdater = new VideoProgressUpdater();
        this.mVideoProxyHelper = new VideoProxyHelper(BaseApplication.INSTANCE.getInstance());
        _$_findCachedViewById(C4188R.id.adver_video_view_click).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function1<AdverConfig.TaskType, Unit> onOutsideTask = AdVideoFragment.this.getOnOutsideTask();
                if (onOutsideTask != null) {
                    onOutsideTask.invoke(AdverConfig.TaskType.TaskScreen.INSTANCE);
                }
                if (!Intrinsics.areEqual(AdVideoFragment.this.getMSceneConfig() != null ? r4.getId() : null, AdSceneConfig.SOLICITING_PASSENGERS_MODE.getId())) {
                    LinearLayout adver_right_root = (LinearLayout) AdVideoFragment.this._$_findCachedViewById(C4188R.id.adver_right_root);
                    Intrinsics.checkExpressionValueIsNotNull(adver_right_root, "adver_right_root");
                    ViewExtKt.isShowView(adver_right_root);
                }
                Pdlog.m3273d(AdVideoFragment.this.getTAG(), "setOnClickListener() adver_video_view_click TaskScreen");
            }
        });
        VideoView videoView3 = this.mVideoView;
        if (videoView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView3.setOnCompletionListener(getMDefOnCompletionListener());
        VideoView videoView4 = this.mVideoView;
        if (videoView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView4.setOnErrorListener(new AdVideoFragment$initView$2(this));
        VideoView videoView5 = this.mVideoView;
        if (videoView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView5.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$initView$3
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                MediaPlayer mediaPlayer2;
                float f;
                float f2;
                AdVideoFragment.this.mMediaPlayer = mediaPlayer;
                AdVideoFragment.this.isPrepared = true;
                String tag = AdVideoFragment.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("setOnPreparedListener() mMediaPlayer =");
                mediaPlayer2 = AdVideoFragment.this.mMediaPlayer;
                sb.append(mediaPlayer2);
                Pdlog.m3273d(tag, sb.toString());
                f = AdVideoFragment.this.mVolume;
                if (f != 1.0f) {
                    AdVideoFragment adVideoFragment = AdVideoFragment.this;
                    f2 = adVideoFragment.mVolume;
                    adVideoFragment.setMediaVolume(f2);
                }
            }
        });
        initComListener();
        Pdlog.m3273d(getTAG(), "initView()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startVideo(boolean isRegisterCacheListener) {
        VideoProxyHelper videoProxyHelper = this.mVideoProxyHelper;
        if (videoProxyHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
        }
        AdVideoInfoBean adVideoInfoBean = this.mAdVideoInfo;
        if (adVideoInfoBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        String url = adVideoInfoBean.getUrl();
        AdVideoInfoBean adVideoInfoBean2 = this.mAdVideoInfo;
        if (adVideoInfoBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        String proxyUrl = videoProxyHelper.getProxyUrl(url, adVideoInfoBean2.getMd5());
        VideoProxyHelper videoProxyHelper2 = this.mVideoProxyHelper;
        if (videoProxyHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
        }
        AdVideoInfoBean adVideoInfoBean3 = this.mAdVideoInfo;
        if (adVideoInfoBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        boolean isCached = videoProxyHelper2.isCached(adVideoInfoBean3.getUrl());
        if (isRegisterCacheListener || !isCached) {
            VideoProxyHelper videoProxyHelper3 = this.mVideoProxyHelper;
            if (videoProxyHelper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
            }
            AdVideoFragment adVideoFragment = this;
            AdVideoInfoBean adVideoInfoBean4 = this.mAdVideoInfo;
            if (adVideoInfoBean4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
            }
            videoProxyHelper3.registerCacheListener(adVideoFragment, adVideoInfoBean4.getUrl());
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("startVideo() registerCacheListener mUrl =");
            AdVideoInfoBean adVideoInfoBean5 = this.mAdVideoInfo;
            if (adVideoInfoBean5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
            }
            sb.append(adVideoInfoBean5);
            objArr[0] = sb.toString();
            Pdlog.m3273d(tag, objArr);
        }
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setVideoPath(proxyUrl);
        String tag2 = getTAG();
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("startVideo()  fullyCached =");
        sb2.append(isCached);
        sb2.append(" proxyUrl =");
        sb2.append(proxyUrl);
        sb2.append(" mUrl =");
        AdVideoInfoBean adVideoInfoBean6 = this.mAdVideoInfo;
        if (adVideoInfoBean6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        sb2.append(adVideoInfoBean6);
        objArr2[0] = sb2.toString();
        Pdlog.m3273d(tag2, objArr2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void createObserver() {
        AdVideoFragment adVideoFragment = this;
        ((AdVm) getMViewModel()).getControlAdPlay().observe(adVideoFragment, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$createObserver$$inlined$observe$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean booleanValue = ((Boolean) t).booleanValue();
                Pdlog.m3273d(AdVideoFragment.this.getTAG(), "createObserver controlAdPlay:" + booleanValue + ' ');
                if (booleanValue) {
                    AdVideoFragment.this.playResume();
                } else {
                    AdVideoFragment.this.playPause();
                }
            }
        });
        ((AdVm) getMViewModel()).getControlAdShow().observe(adVideoFragment, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$createObserver$$inlined$observe$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean booleanValue = ((Boolean) t).booleanValue();
                Pdlog.m3273d(AdVideoFragment.this.getTAG(), "createObserver controlAdShow:" + booleanValue + ' ');
                if (booleanValue) {
                    ViewExtKt.show(AdVideoFragment.access$getMVideoView$p(AdVideoFragment.this));
                } else {
                    ViewExtKt.gone(AdVideoFragment.access$getMVideoView$p(AdVideoFragment.this));
                }
            }
        });
        Pdlog.m3273d(getTAG(), "createObserver mViewModel: " + ((AdVm) getMViewModel()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0058, code lost:
    
        if (r0 != null) goto L13;
     */
    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initData() {
        super.initData();
        AdSceneConfig mSceneConfig = getMSceneConfig();
        if (mSceneConfig != null) {
            showOutsideAdScene(mSceneConfig);
        }
        AdverVideoBean adverVideoBean = this.mAdverVideoBean;
        if (adverVideoBean != null) {
            if (adverVideoBean.getUrls().size() > 0) {
                this.mVideoCount = adverVideoBean.getUrls().size();
                this.mAdVideoInfo = adverVideoBean.getUrls().get(this.mVideoCur);
                checkCachedState();
                setVideoData();
                Pdlog.m3273d(getTAG(), "initData() mAdverVideoBean is null mAdverVideoBean =" + this.mAdverVideoBean);
            }
        }
        Pdlog.m3273d(getTAG(), "initData()  mAdverVideoBean is null");
        Unit unit = Unit.INSTANCE;
        Pdlog.m3273d(getTAG(), "initData() mAdverVideoBean = " + this.mAdverVideoBean);
    }

    private final void setVideoData() {
        Pdlog.m3273d(getTAG(), "setVideoData()");
        startVideo(true);
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
        AdVideoInfoBean adVideoInfoBean = this.mAdVideoInfo;
        if (adVideoInfoBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        if (videoProxyHelper.isCached(adVideoInfoBean.getUrl())) {
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("checkCachedState() 100 mUrl =");
            AdVideoInfoBean adVideoInfoBean2 = this.mAdVideoInfo;
            if (adVideoInfoBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
            }
            sb.append(adVideoInfoBean2);
            objArr[0] = sb.toString();
            Pdlog.m3273d(tag, objArr);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment
    public void updateAdverContent(AdverBaseBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        if (content instanceof AdverVideoBean) {
            this.mAdverVideoBean = (AdverVideoBean) content;
        }
        Pdlog.m3273d(getTAG(), "updateAdverContent() mAdverVideoBean =" + this.mAdverVideoBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playPause() {
        Pdlog.m3273d(getTAG(), "playPause() come  mIsPause =" + this.mIsPause + " mActivity = " + getMActivity());
        if (this.mIsPause) {
            return;
        }
        requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$playPause$$inlined$runOnUiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                AdVideoFragment.this.videoPause();
                String tag = AdVideoFragment.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("playPause() mCurrentPosition =");
                i = AdVideoFragment.this.mCurrentPosition;
                sb.append(i);
                Pdlog.m3273d(tag, sb.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void videoPause() {
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        this.mCurrentPosition = videoView.getCurrentPosition();
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView2.pause();
        this.mIsPause = true;
        Pdlog.m3273d(getTAG(), "videoPause() mIsPause =" + this.mIsPause);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playResume() {
        Pdlog.m3273d(getTAG(), "playResume() mIsPause =" + this.mIsPause + " mActivity = " + getMActivity());
        if (this.mIsPause) {
            requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdVideoFragment$playResume$$inlined$runOnUiThread$1
                @Override // java.lang.Runnable
                public final void run() {
                    AdVideoFragment.this.videoResume();
                }
            });
            Pdlog.m3273d(getTAG(), "playResume() mCurrentPosition =" + this.mCurrentPosition);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void videoResume() {
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        ViewExtKt.show(videoView);
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView2.seekTo(this.mCurrentPosition);
        VideoView videoView3 = this.mVideoView;
        if (videoView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView3.start();
        this.mIsPause = false;
    }

    @Override // com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment
    public void setVolume(float volume) {
        setMediaVolume(volume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMediaVolume(float volume) {
        try {
            if (this.mVolume != volume) {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(volume, volume);
                }
                Pdlog.m3273d(getTAG(), "setMediaVolume() success =" + this.mVolume);
            }
        } catch (Exception e) {
            String tag = getTAG();
            e.printStackTrace();
            Pdlog.m3274e(tag, "setMediaVolume()", Unit.INSTANCE);
        }
        this.mVolume = volume;
        Pdlog.m3273d(getTAG(), "setMediaVolume() mVolume =" + this.mVolume);
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(getTAG(), "onResume()");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(getTAG(), "onPause()");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView.setVisibility(8);
        Pdlog.m3273d(getTAG(), "onDetach()");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        List<AdVideoInfoBean> urls;
        super.onDestroy();
        VideoProgressUpdater videoProgressUpdater = this.mVideoProgressUpdater;
        if (videoProgressUpdater != null) {
            videoProgressUpdater.stop();
        }
        AdverVideoBean adverVideoBean = this.mAdverVideoBean;
        if (adverVideoBean != null && (urls = adverVideoBean.getUrls()) != null) {
            for (AdVideoInfoBean adVideoInfoBean : urls) {
                VideoProxyHelper videoProxyHelper = this.mVideoProxyHelper;
                if (videoProxyHelper == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mVideoProxyHelper");
                }
                AdVideoFragment adVideoFragment = this;
                AdVideoInfoBean adVideoInfoBean2 = this.mAdVideoInfo;
                if (adVideoInfoBean2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
                }
                videoProxyHelper.unregisterCacheListener(adVideoFragment, adVideoInfoBean2.getUrl());
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
        videoView2.setOnErrorListener(null);
        VideoView videoView3 = this.mVideoView;
        if (videoView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView3.setOnPreparedListener(null);
        VideoView videoView4 = this.mVideoView;
        if (videoView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView4.stopPlayback();
        VideoView videoView5 = this.mVideoView;
        if (videoView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        videoView5.suspend();
        this.mMediaPlayer = (MediaPlayer) null;
        ViewGroup viewGroup = this.mRootContain;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        Pdlog.m3273d(getTAG(), "onDestroy()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AdVideoFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment$DefOnCompletionListener;", "Landroid/media/MediaPlayer$OnCompletionListener;", "(Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment;)V", "onCompletion", "", "mp", "Landroid/media/MediaPlayer;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class DefOnCompletionListener implements MediaPlayer.OnCompletionListener {
        public DefOnCompletionListener() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mp) {
            AdVideoInfoBean access$getMAdVideoInfo$p;
            List<AdVideoInfoBean> urls;
            AdVideoFragment.this.isPrepared = false;
            Pdlog.m3273d(AdVideoFragment.this.getTAG(), "onCompletion() comeIn mVideoCur =" + AdVideoFragment.this.mVideoCur);
            AdVideoFragment.this.mCurrentPosition = 0;
            if (AdVideoFragment.this.mVideoCount == 1) {
                AdVideoFragment.this.startVideo(false);
                AdVideoFragment.this.videoResume();
                Pdlog.m3273d(AdVideoFragment.this.getTAG(), "重复一条视频的播放");
            } else {
                if (AdVideoFragment.this.mVideoCur >= AdVideoFragment.this.mVideoCount - 1) {
                    AdVideoFragment.this.mVideoCur = -1;
                }
                AdVideoFragment.this.mVideoCur++;
                AdVideoFragment adVideoFragment = AdVideoFragment.this;
                AdverVideoBean adverVideoBean = adVideoFragment.mAdverVideoBean;
                if (adverVideoBean == null || (urls = adverVideoBean.getUrls()) == null || (access$getMAdVideoInfo$p = urls.get(AdVideoFragment.this.mVideoCur)) == null) {
                    access$getMAdVideoInfo$p = AdVideoFragment.access$getMAdVideoInfo$p(AdVideoFragment.this);
                }
                adVideoFragment.mAdVideoInfo = access$getMAdVideoInfo$p;
                AdVideoFragment.this.checkCachedState();
                AdVideoFragment.this.startVideo(true);
                AdVideoFragment.this.videoResume();
                Pdlog.m3273d(AdVideoFragment.this.getTAG(), "多条视频mUrl：" + AdVideoFragment.access$getMAdVideoInfo$p(AdVideoFragment.this));
            }
            Pdlog.m3273d(AdVideoFragment.this.getTAG(), "onCompletion() end mVideoCur =" + AdVideoFragment.this.mVideoCur);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AdVideoFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0083\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment$VideoProgressUpdater;", "Landroid/os/Handler;", "(Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoFragment;)V", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "start", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class VideoProgressUpdater extends Handler {
        public VideoProgressUpdater() {
            super(Looper.getMainLooper());
        }

        public final void start() {
            if (hasMessages(AdVideoFragment.this.WHAT_PSB)) {
                removeMessages(AdVideoFragment.this.WHAT_PSB);
            }
            sendEmptyMessageDelayed(AdVideoFragment.this.WHAT_PSB, AdVideoFragment.this.TIME_INTERVALE);
        }

        public final void stop() {
            if (hasMessages(AdVideoFragment.this.WHAT_PSB)) {
                removeMessages(AdVideoFragment.this.WHAT_PSB);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            if (msg.what == AdVideoFragment.this.WHAT_PSB) {
                AdVideoFragment.this.updateDsiplayUi();
                sendEmptyMessageDelayed(AdVideoFragment.this.WHAT_PSB, AdVideoFragment.this.TIME_INTERVALE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDsiplayUi() {
        VideoView videoView = this.mVideoView;
        if (videoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        int currentPosition = videoView.getCurrentPosition() * 100;
        VideoView videoView2 = this.mVideoView;
        if (videoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mVideoView");
        }
        int duration = currentPosition / videoView2.getDuration();
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("updateDsiplayUi() mUrl =");
        AdVideoInfoBean adVideoInfoBean = this.mAdVideoInfo;
        if (adVideoInfoBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdVideoInfo");
        }
        sb.append(adVideoInfoBean);
        sb.append("  playProgress =");
        sb.append(duration);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
    }

    @Override // com.danikula.videocache.CacheListener
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
        if (percentsAvailable == 0 || percentsAvailable == 20 || percentsAvailable == 50 || percentsAvailable == 80 || percentsAvailable == 100) {
            Pdlog.m3273d(getTAG(), "onCacheAvailable()", "file path:" + cacheFile + "---url:" + url + "---" + percentsAvailable);
        }
    }
}
