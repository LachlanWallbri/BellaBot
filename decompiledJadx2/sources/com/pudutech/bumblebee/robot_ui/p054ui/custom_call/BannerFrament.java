package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallImgBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.BannerCarouselAdapter;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.ScreenUtils;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BannerFrament.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002\".\u0018\u00002\u00020\u0001:\u0001CB\u0005¢\u0006\u0002\u0010\u0002J\b\u00102\u001a\u00020\nH\u0016J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016J\u0012\u00107\u001a\u0002042\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010:\u001a\u000204H\u0016J\u001a\u0010;\u001a\u0002042\u0006\u00105\u001a\u0002062\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010<\u001a\u000204H\u0002J\b\u0010=\u001a\u000204H\u0016J\b\u0010>\u001a\u000204H\u0014J\b\u0010?\u001a\u000204H\u0014J\u0010\u0010@\u001a\u0002042\u0006\u0010A\u001a\u00020BH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0016\u001a\u00060\u0017R\u00020\u0000X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R(\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010#R\u001a\u0010$\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0011\"\u0004\b&\u0010\u0013R\u001a\u0010'\u001a\u00020(X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament;", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BaseCustomCallFragment;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "WHAT_C_DOWM", "", "WHAT_NO_ACTION", "WHAT_SCREEN_DIMS", "isStart", "", "mCDownTime", "getMCDownTime", "()I", "setMCDownTime", "(I)V", "mCustomCallImgBean", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallImgBean;", "mDownHandler", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$DownHandler;", "getMDownHandler", "()Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$DownHandler;", "mFragBannerView", "Lcom/youth/banner/Banner;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/BannerCarouselAdapter;", "getMFragBannerView", "()Lcom/youth/banner/Banner;", "setMFragBannerView", "(Lcom/youth/banner/Banner;)V", "mLcdScreenTouchListener", "com/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$mLcdScreenTouchListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$mLcdScreenTouchListener$1;", "mNoActionTime", "getMNoActionTime", "setMNoActionTime", "mScreenDimsTime", "", "getMScreenDimsTime", "()J", "setMScreenDimsTime", "(J)V", "mSpdListener", "com/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$mSpdListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$mSpdListener$1;", "mStartScreenBrightness", "", "getLayoutId", "initview", "", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onViewCreated", "recoverScreenLight", "setData", "start", "stop", "updateCustomCallContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "DownHandler", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BannerFrament extends BaseCustomCallFragment {
    private HashMap _$_findViewCache;
    private boolean isStart;
    private CustomCallImgBean mCustomCallImgBean;
    private Banner<String, BannerCarouselAdapter> mFragBannerView;
    private String TAG = "BannerFrament";
    private final int WHAT_C_DOWM = 2;
    private final int WHAT_NO_ACTION = 3;
    private final int WHAT_SCREEN_DIMS = 4;
    private int mCDownTime = 10;
    private int mNoActionTime = 10;
    private long mScreenDimsTime = 10000;
    private final DownHandler mDownHandler = new DownHandler();
    private float mStartScreenBrightness = 0.9f;
    private final BannerFrament$mSpdListener$1 mSpdListener = new BannerFrament$mSpdListener$1(this);
    private final BannerFrament$mLcdScreenTouchListener$1 mLcdScreenTouchListener = new BannerFrament$mLcdScreenTouchListener$1(this);

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

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.TAG = str;
    }

    public final Banner<String, BannerCarouselAdapter> getMFragBannerView() {
        return this.mFragBannerView;
    }

    public final void setMFragBannerView(Banner<String, BannerCarouselAdapter> banner) {
        this.mFragBannerView = banner;
    }

    protected final int getMCDownTime() {
        return this.mCDownTime;
    }

    protected final void setMCDownTime(int i) {
        this.mCDownTime = i;
    }

    protected final int getMNoActionTime() {
        return this.mNoActionTime;
    }

    protected final void setMNoActionTime(int i) {
        this.mNoActionTime = i;
    }

    protected final long getMScreenDimsTime() {
        return this.mScreenDimsTime;
    }

    protected final void setMScreenDimsTime(long j) {
        this.mScreenDimsTime = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DownHandler getMDownHandler() {
        return this.mDownHandler;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDK.INSTANCE.getSpeedListeners().addListener(this.mSpdListener);
        Peripherals.INSTANCE.getLcd().addListener(this.mLcdScreenTouchListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: BannerFrament.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$DownHandler;", "Landroid/os/Handler;", "(Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament;)V", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final class DownHandler extends Handler {
        public DownHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Function2<CustomCallState, CustomCallOperationType, Unit> onActionState;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            int i = msg.what;
            if (i != BannerFrament.this.WHAT_C_DOWM) {
                if (i != BannerFrament.this.WHAT_NO_ACTION) {
                    if (i != BannerFrament.this.WHAT_SCREEN_DIMS || BannerFrament.this.getMScreenDimsTime() == -1 || (onActionState = BannerFrament.this.getOnActionState()) == null) {
                        return;
                    }
                    onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                    return;
                }
                Pdlog.m3273d(BannerFrament.this.getTAG(), "WHAT_NO_ACTION---" + BannerFrament.this.getMNoActionTime());
                if (BannerFrament.this.getMNoActionTime() != -1) {
                    ScreenUtils.setScreenLight(BannerFrament.this.getMActivity(), 0.0f);
                    Pdlog.m3273d(BannerFrament.this.getTAG(), "WHAT_NO_ACTION");
                    BannerFrament.this.getMDownHandler().sendEmptyMessageDelayed(BannerFrament.this.WHAT_SCREEN_DIMS, BannerFrament.this.getMScreenDimsTime());
                    return;
                }
                return;
            }
            ImageView frag_banner_close = (ImageView) BannerFrament.this._$_findCachedViewById(C4188R.id.frag_banner_close);
            Intrinsics.checkExpressionValueIsNotNull(frag_banner_close, "frag_banner_close");
            frag_banner_close.setVisibility(0);
            BannerFrament.this.getMDownHandler().sendEmptyMessageDelayed(BannerFrament.this.WHAT_NO_ACTION, BannerFrament.this.getMNoActionTime() * 1000);
        }
    }

    protected void start() {
        ImageView imageView;
        int i = this.mCDownTime;
        if (i == -1 || i == 0) {
            if (this.mCDownTime == 0 && (imageView = (ImageView) _$_findCachedViewById(C4188R.id.frag_banner_close)) != null) {
                imageView.setVisibility(0);
            }
            this.mDownHandler.sendEmptyMessageDelayed(this.WHAT_NO_ACTION, this.mNoActionTime * 1000);
            Pdlog.m3273d(this.TAG, "start没有操作显示");
            return;
        }
        this.mDownHandler.sendEmptyMessageDelayed(this.WHAT_C_DOWM, i * 1000);
        Pdlog.m3273d(this.TAG, "start:WHAT_C_DOWM--" + this.mCDownTime);
    }

    protected void stop() {
        if (this.mDownHandler.hasMessages(this.WHAT_C_DOWM)) {
            this.mDownHandler.removeMessages(this.WHAT_C_DOWM);
        }
        if (this.mDownHandler.hasMessages(this.WHAT_C_DOWM)) {
            this.mDownHandler.removeMessages(this.WHAT_C_DOWM);
        }
        if (this.mDownHandler.hasMessages(this.WHAT_SCREEN_DIMS)) {
            this.mDownHandler.removeMessages(this.WHAT_SCREEN_DIMS);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public int getLayoutId() {
        return C4188R.layout.fragment_banner;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment
    public void updateCustomCallContent(ICustomCallBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.mCustomCallImgBean = (CustomCallImgBean) content;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.mFragBannerView = (Banner) view.findViewById(C4188R.id.frag_banner_view);
        super.onViewCreated(view, savedInstanceState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recoverScreenLight() {
        Activity mActivity = getMActivity();
        if (mActivity != null) {
            Window window = mActivity.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "it.window");
            float f = window.getAttributes().screenBrightness;
            Pdlog.m3273d(this.TAG, "recoverScreenLight start :" + f + "######" + this.mStartScreenBrightness);
            float f2 = this.mStartScreenBrightness;
            if (f2 != f) {
                ScreenUtils.setScreenLight(mActivity, f2);
                Pdlog.m3273d(this.TAG, "recoverScreenLight change :" + f + "######" + this.mStartScreenBrightness);
            } else {
                Pdlog.m3273d(this.TAG, "recoverScreenLight unChange:" + f + "######" + this.mStartScreenBrightness);
            }
            if (this.mDownHandler.hasMessages(this.WHAT_NO_ACTION)) {
                this.mDownHandler.removeMessages(this.WHAT_NO_ACTION);
            }
            if (this.mDownHandler.hasMessages(this.WHAT_SCREEN_DIMS)) {
                this.mDownHandler.removeMessages(this.WHAT_SCREEN_DIMS);
            }
            this.mDownHandler.sendEmptyMessageDelayed(this.WHAT_NO_ACTION, this.mNoActionTime * 1000);
            Pdlog.m3273d(this.TAG, "recoverScreenLight:点击了：" + f);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void initview(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Banner<String, BannerCarouselAdapter> banner = this.mFragBannerView;
        if (banner != null) {
            FragmentActivity activity = getActivity();
            banner.setIndicator(new CircleIndicator(activity != null ? activity.getApplicationContext() : null));
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(C4188R.id.frag_banner_close);
        if (imageView != null) {
            imageView.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.BannerFrament$initview$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = BannerFrament.this.getOnActionState();
                    if (onActionState != null) {
                        onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                    }
                    Pdlog.m3273d(BannerFrament.this.getTAG(), "OnClickClose");
                }
            }, 3, null));
        }
        Banner<String, BannerCarouselAdapter> banner2 = this.mFragBannerView;
        if (banner2 != null) {
            banner2.addBannerLifecycleObserver(this);
        }
        Function1<Boolean, Unit> showStateBar = getShowStateBar();
        if (showStateBar != null) {
            showStateBar.invoke(true);
        }
        Banner<String, BannerCarouselAdapter> banner3 = this.mFragBannerView;
        if (banner3 != null) {
            banner3.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.BannerFrament$initview$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    BannerFrament.this.recoverScreenLight();
                    Pdlog.m3273d(BannerFrament.this.getTAG(), "setOnTouchListener");
                }
            }, 3, null));
        }
        Pdlog.m3273d(this.TAG, "initview");
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void setData() {
        List<String> urls;
        CustomCallImgBean customCallImgBean = this.mCustomCallImgBean;
        if (customCallImgBean == null || (urls = customCallImgBean.getUrls()) == null) {
            return;
        }
        Integer cancelBtnTime = customCallImgBean.getCancelBtnTime();
        this.mCDownTime = cancelBtnTime != null ? cancelBtnTime.intValue() : 0;
        Integer showTimeout = customCallImgBean.getShowTimeout();
        this.mNoActionTime = showTimeout != null ? showTimeout.intValue() : 0;
        Integer switchTime = customCallImgBean.getSwitchTime();
        if (switchTime != null) {
            int intValue = switchTime.intValue();
            if (intValue > 0) {
                Banner<String, BannerCarouselAdapter> banner = this.mFragBannerView;
                if (banner != null) {
                    banner.setLoopTime(intValue * 1000);
                }
            } else {
                Banner<String, BannerCarouselAdapter> banner2 = this.mFragBannerView;
                if (banner2 != null) {
                    banner2.isAutoLoop(false);
                }
            }
        }
        if (urls.size() == 1) {
            Banner<String, BannerCarouselAdapter> banner3 = this.mFragBannerView;
            if (banner3 != null) {
                banner3.isAutoLoop(false);
            }
            Banner<String, BannerCarouselAdapter> banner4 = this.mFragBannerView;
            if (banner4 != null) {
                banner4.setIndicatorWidth(0, 0);
            }
            Banner<String, BannerCarouselAdapter> banner5 = this.mFragBannerView;
            if (banner5 != null) {
                banner5.setIndicatorHeight(0);
            }
        }
        BannerCarouselAdapter bannerCarouselAdapter = new BannerCarouselAdapter(urls);
        Banner<String, BannerCarouselAdapter> banner6 = this.mFragBannerView;
        if (banner6 != null) {
            banner6.setAdapter(bannerCarouselAdapter);
        }
        this.isStart = true;
        start();
        Pdlog.m3273d(this.TAG, "updateCustomCallContent--" + String.valueOf(this.mCustomCallImgBean));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.isStart = false;
        SDK.INSTANCE.getSpeedListeners().removeListener(this.mSpdListener);
        Peripherals.INSTANCE.getLcd().removeListener(this.mLcdScreenTouchListener);
        stop();
    }
}
