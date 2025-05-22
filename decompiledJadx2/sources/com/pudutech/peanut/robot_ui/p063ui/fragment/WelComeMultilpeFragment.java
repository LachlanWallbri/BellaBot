package com.pudutech.peanut.robot_ui.p063ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.WeComeBean;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.AdVoicePlayManager;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.ChatMultipleItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.WeComeAdapter;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeActivateViewModel;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: WelComeMultilpeFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\tH\u0002J\u0010\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u00020#2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020#2\u0006\u0010/\u001a\u000200H\u0016J$\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u00107\u001a\u00020#H\u0016J\b\u00108\u001a\u00020#H\u0016J\b\u00109\u001a\u00020#H\u0016J\u0010\u0010:\u001a\u00020#2\u0006\u0010'\u001a\u00020\tH\u0002J\u0010\u0010;\u001a\u00020#2\u0006\u0010<\u001a\u00020\tH\u0002J\u0010\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020\u0017H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f¨\u0006@"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeMultilpeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "animationView", "Landroid/widget/FrameLayout;", "count", "", "finishId", "handler", "Landroid/os/Handler;", "isStarSpeak", "", "isStop", "mAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/WeComeAdapter;", "mAnimation", "Landroid/view/animation/AlphaAnimation;", "mList", "", "Lcom/pudutech/peanut/robot_ui/ui/adapter/ChatMultipleItem;", "Lcom/pudutech/peanut/robot_ui/bean/WeComeBean;", "mShouldScroll", "mToPosition", "tvInputText", "Lcom/pudutech/peanut/robot_ui/widget/MarqueeTextView;", "weComeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel;", "getWeComeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel;", "weComeVm$delegate", "Lkotlin/Lazy;", "initData", "", "initVM", "initView", "jump", "state", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDetach", "onResume", "playAnimationMode", "smoothMoveToPosition", RequestParameters.POSITION, "updateMsg", "msgBean", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeMultilpeFragment extends Fragment {
    public static final int AVOID_WAKE_UP = 6;
    public static final int BEGIN_VOICE = 4;
    public static final int CLOSE_ACTIVATE = 9;
    public static final int CLOSE_WAKE = 7;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int VOICE_WAKE_UP = 8;
    public static final int WAKE_UP = 5;
    public static final int WS_WE_COME_MSG = 3;
    private static volatile boolean wakeup;
    private String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private FrameLayout animationView;
    private volatile int count;
    private int finishId;
    private Handler handler;
    private boolean isStarSpeak;
    private boolean isStop;
    private WeComeAdapter mAdapter;
    private AlphaAnimation mAnimation;
    private List<ChatMultipleItem<WeComeBean>> mList;
    private boolean mShouldScroll;
    private int mToPosition;
    private MarqueeTextView tvInputText;

    /* renamed from: weComeVm$delegate, reason: from kotlin metadata */
    private final Lazy weComeVm;

    /* JADX INFO: Access modifiers changed from: private */
    public final WelComeActivateViewModel getWeComeVm() {
        return (WelComeActivateViewModel) this.weComeVm.getValue();
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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public WelComeMultilpeFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.weComeVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(WelComeActivateViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$$special$$inlined$viewModels$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, (Function0) null);
        this.handler = new Companion.WithoutLeakHandler(this);
        this.mList = new ArrayList();
        this.count = 1;
        this.finishId = -1;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C5508R.layout.welcome_multilpe_fragment, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…agment, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initVM();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.lowVolumeMode(simpleName);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.normalVolumeMode(simpleName);
    }

    private final void initView() {
        View view = getView();
        this.animationView = view != null ? (FrameLayout) view.findViewById(C5508R.id.flInputText) : null;
        View view2 = getView();
        this.tvInputText = view2 != null ? (MarqueeTextView) view2.findViewById(C5508R.id.tvInputText) : null;
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).setLoopPer(true);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).playAnimation(SceneAnimationResources.INSTANCE.getSolicit());
        this.mAdapter = new WeComeAdapter(this.mList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.mAdapter);
        ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initView$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                boolean z;
                int i;
                Intrinsics.checkParameterIsNotNull(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                z = WelComeMultilpeFragment.this.mShouldScroll;
                if (z && newState == 0) {
                    WelComeMultilpeFragment.this.mShouldScroll = false;
                    WelComeMultilpeFragment welComeMultilpeFragment = WelComeMultilpeFragment.this;
                    i = welComeMultilpeFragment.mToPosition;
                    welComeMultilpeFragment.smoothMoveToPosition(i);
                }
            }
        });
        TextView tvCoupon = (TextView) _$_findCachedViewById(C5508R.id.tvCoupon);
        Intrinsics.checkExpressionValueIsNotNull(tvCoupon, "tvCoupon");
        ViewExtKt.onSingleClick(tvCoupon, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initView$3
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
                WelComeMultilpeFragment.this.jump(2);
            }
        });
        TextView tvFeatured = (TextView) _$_findCachedViewById(C5508R.id.tvFeatured);
        Intrinsics.checkExpressionValueIsNotNull(tvFeatured, "tvFeatured");
        ViewExtKt.onSingleClick(tvFeatured, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initView$4
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
                WelComeMultilpeFragment.this.jump(1);
            }
        });
        TextView tvGoShops = (TextView) _$_findCachedViewById(C5508R.id.tvGoShops);
        Intrinsics.checkExpressionValueIsNotNull(tvGoShops, "tvGoShops");
        ViewExtKt.onSingleClick(tvGoShops, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initView$5
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
                WelComeMultilpeFragment.this.jump(3);
            }
        });
        AiVoiceManager.INSTANCE.startAiRecording();
        this.handler.sendEmptyMessageDelayed(4, SolicitService.CAMERA_OPEN_TIME_OUT);
        this.handler.sendEmptyMessageDelayed(9, 1201000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAnimationMode(int state) {
        this.mAnimation = (AlphaAnimation) null;
        ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).pauseAnimation();
        if (state == 1) {
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).setAnimation("animation/animation_loading.json");
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).playAnimation();
            return;
        }
        if (state == 2) {
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).setAnimation("animation/animation_playing.json");
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).playAnimation();
            return;
        }
        if (state == 3) {
            this.mAnimation = new AlphaAnimation(0.0f, 1.0f);
            AlphaAnimation alphaAnimation = this.mAnimation;
            if (alphaAnimation != null) {
                alphaAnimation.setDuration(300L);
            }
            AlphaAnimation alphaAnimation2 = this.mAnimation;
            if (alphaAnimation2 != null) {
                alphaAnimation2.setFillAfter(true);
            }
            ImageView ivVoice = (ImageView) _$_findCachedViewById(C5508R.id.ivVoice);
            Intrinsics.checkExpressionValueIsNotNull(ivVoice, "ivVoice");
            ivVoice.setAnimation(this.mAnimation);
            ((ImageView) _$_findCachedViewById(C5508R.id.ivVoice)).startAnimation(this.mAnimation);
            return;
        }
        if (state != 4) {
            if (state != 5) {
                return;
            }
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).setAnimation("animation/animation_playing.json");
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim)).playAnimation();
            return;
        }
        this.mAnimation = new AlphaAnimation(1.0f, 0.0f);
        AlphaAnimation alphaAnimation3 = this.mAnimation;
        if (alphaAnimation3 != null) {
            alphaAnimation3.setDuration(300L);
        }
        AlphaAnimation alphaAnimation4 = this.mAnimation;
        if (alphaAnimation4 != null) {
            alphaAnimation4.setFillAfter(true);
        }
        ImageView ivVoice2 = (ImageView) _$_findCachedViewById(C5508R.id.ivVoice);
        Intrinsics.checkExpressionValueIsNotNull(ivVoice2, "ivVoice");
        ivVoice2.setAnimation(this.mAnimation);
        ((ImageView) _$_findCachedViewById(C5508R.id.ivVoice)).startAnimation(this.mAnimation);
    }

    private final void initData() {
        Bundle bundle = new Bundle();
        bundle.putString(AIUIConstant.KEY_CONTENT, "欢迎光临");
        bundle.putInt("type", 2);
        this.handler.sendMessageDelayed(this.handler.obtainMessage(3, bundle), 300L);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AIUIConstant.KEY_CONTENT, "了解本店菜品，可以对我说“特色菜”");
        bundle2.putInt("type", 2);
        this.handler.sendMessageDelayed(this.handler.obtainMessage(3, bundle2), 600L);
        Bundle bundle3 = new Bundle();
        bundle3.putString(AIUIConstant.KEY_CONTENT, "需要就餐，您可以说“带我进店”,需要就餐，您可以说“带我进店”,需要就餐，您可以说“带我进店”");
        bundle3.putInt("type", 2);
        this.handler.sendMessageDelayed(this.handler.obtainMessage(3, bundle3), 1000L);
        this.handler.sendEmptyMessageDelayed(9, 1201000L);
    }

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM ");
        getWeComeVm().getMsgModel().observe(getViewLifecycleOwner(), new Observer<WeComeBean>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initVM$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(WeComeBean msg) {
                boolean z;
                MarqueeTextView marqueeTextView;
                int i;
                Pdlog.m3273d(WelComeMultilpeFragment.this.TAG, "msgModel: " + msg.toString() + ' ');
                if (msg.getType() == 1) {
                    WelComeMultilpeFragment.this.handler.sendEmptyMessage(8);
                    if (Intrinsics.areEqual((Object) msg.isFinish(), (Object) true)) {
                        i = WelComeMultilpeFragment.this.finishId;
                        if (i != msg.getId()) {
                            WelComeMultilpeFragment.this.handler.sendEmptyMessage(7);
                            WelComeMultilpeFragment.this.finishId = msg.getId();
                        }
                    }
                    if ((msg.getContent().length() > 0) && (marqueeTextView = WelComeMultilpeFragment.this.tvInputText) != null) {
                        marqueeTextView.setText(msg.getContent());
                    }
                    WelComeMultilpeFragment.this.count = msg.getId();
                    z = WelComeMultilpeFragment.this.isStarSpeak;
                    if (z) {
                        return;
                    }
                    WelComeMultilpeFragment.this.isStarSpeak = true;
                    WelComeMultilpeFragment.this.playAnimationMode(4);
                    WelComeMultilpeFragment.this.playAnimationMode(5);
                    return;
                }
                WelComeMultilpeFragment.this.isStarSpeak = false;
                WelComeMultilpeFragment.this.playAnimationMode(2);
                WelComeMultilpeFragment welComeMultilpeFragment = WelComeMultilpeFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
                welComeMultilpeFragment.updateMsg(msg);
            }
        });
        getWeComeVm().getMVoiceChangeState().observe(getViewLifecycleOwner(), new Observer<Integer>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initVM$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                Pdlog.m3273d(WelComeMultilpeFragment.this.TAG, "mVoiceChangeState: " + num + ' ');
                if (num != null && num.intValue() == 1) {
                    WelComeMultilpeFragment.INSTANCE.setWakeup(false);
                    Pdlog.m3273d(WelComeMultilpeFragment.this.TAG, "startWakeUp: " + num + ' ');
                    WelComeMultilpeFragment.this.playAnimationMode(3);
                    AiVoiceManager.INSTANCE.startAiRecording();
                    AiVoiceManager.INSTANCE.startWakeUp(0, 0);
                    MarqueeTextView marqueeTextView = WelComeMultilpeFragment.this.tvInputText;
                    if (marqueeTextView != null) {
                        marqueeTextView.setText("");
                        return;
                    }
                    return;
                }
                if (num != null && num.intValue() == 2) {
                    WelComeMultilpeFragment.INSTANCE.setWakeup(false);
                    Pdlog.m3273d(WelComeMultilpeFragment.this.TAG, "startWakeUp: " + num + ' ');
                    WelComeMultilpeFragment.this.playAnimationMode(3);
                    AiVoiceManager.INSTANCE.startAiRecording();
                    AiVoiceManager.INSTANCE.startWakeUp(0, 0);
                    MarqueeTextView marqueeTextView2 = WelComeMultilpeFragment.this.tvInputText;
                    if (marqueeTextView2 != null) {
                        marqueeTextView2.setText("");
                        return;
                    }
                    return;
                }
                if (num != null && num.intValue() == 3) {
                    Pdlog.m3273d(WelComeMultilpeFragment.this.TAG, "stopAiRecording: " + num + ' ');
                    AiVoiceManager.INSTANCE.stopAiRecording();
                }
            }
        });
        getWeComeVm().getMInstructionState().observe(getViewLifecycleOwner(), new Observer<Integer>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeMultilpeFragment$initVM$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                Pdlog.m3273d(WelComeMultilpeFragment.this.TAG, "mInstructionState: " + num + ' ');
                if (num != null && num.intValue() == 1) {
                    WelComeMultilpeFragment.this.jump(1);
                    return;
                }
                if (num != null && num.intValue() == 2) {
                    WelComeMultilpeFragment.this.jump(2);
                } else if (num != null && num.intValue() == 3) {
                    WelComeMultilpeFragment.this.jump(3);
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.isStop = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(int state) {
        Pdlog.m3273d(this.TAG, "stopAiRecording: jump: " + state + ' ');
        AiVoiceManager.INSTANCE.stopAiRecording();
        if (state == 1) {
            this.isStop = true;
            NavigationExtKt.nav(this).navigate(C5508R.id.action_Featured_to_initNav);
        } else if (state == 2) {
            this.isStop = true;
            NavigationExtKt.nav(this).navigate(C5508R.id.action_Promotions_to_initNav);
        } else {
            if (state != 3) {
                return;
            }
            Intent intent = new Intent(getActivity(), (Class<?>) SolicitCustomerActivity.class);
            intent.putExtra("state", 1);
            jumpAndFinish(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMsg(WeComeBean msgBean) {
        boolean z = false;
        Pdlog.m3273d(this.TAG, "updateMsg: " + msgBean.toString() + "  size:" + (this.mList.size() - 1) + " isStop:" + this.isStop);
        if (msgBean == null || this.isStop) {
            return;
        }
        if (msgBean.getType() == 1) {
            List<ChatMultipleItem<WeComeBean>> list = this.mList;
            if (list.get(list.size() - 1).getMData().getType() == 1) {
                int id = msgBean.getId();
                List<ChatMultipleItem<WeComeBean>> list2 = this.mList;
                if (id == list2.get(list2.size() - 1).data().getId()) {
                    List<ChatMultipleItem<WeComeBean>> list3 = this.mList;
                    list3.get(list3.size() - 1).data().setContent(msgBean.getContent());
                    z = true;
                }
            }
            if (!z) {
                this.mList.add(new ChatMultipleItem<>(2, new WeComeBean(msgBean.getId(), msgBean.getType(), msgBean.getContent(), "", null, 16, null)));
            }
        } else {
            List<ChatMultipleItem<WeComeBean>> list4 = this.mList;
            int id2 = msgBean.getId();
            int type = msgBean.getType();
            String content = msgBean.getContent();
            if (content == null) {
                content = "";
            }
            list4.add(new ChatMultipleItem<>(1, new WeComeBean(id2, type, content, "", null, 16, null)));
        }
        WeComeAdapter weComeAdapter = this.mAdapter;
        if (weComeAdapter != null) {
            weComeAdapter.notifyItemChanged(this.mList.size() - 1);
        }
        this.mToPosition = this.mList.size() - 1;
        smoothMoveToPosition(this.mToPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void smoothMoveToPosition(int position) {
        int childLayoutPosition = ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildLayoutPosition(((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildAt(0));
        int childLayoutPosition2 = ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildLayoutPosition(((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildAt(((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildCount() - 1));
        if (position < childLayoutPosition) {
            ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).smoothScrollToPosition(position);
            return;
        }
        if (position <= childLayoutPosition2) {
            int i = position - childLayoutPosition;
            if (i < 0 || i >= ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildCount()) {
                return;
            }
            ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).smoothScrollBy(0, ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).getChildAt(i).getTop());
            return;
        }
        ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).smoothScrollToPosition(position);
        this.mToPosition = position;
        this.mShouldScroll = true;
    }

    private final void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        startActivity(intent);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    /* compiled from: WelComeMultilpeFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeMultilpeFragment$Companion;", "", "()V", "AVOID_WAKE_UP", "", "BEGIN_VOICE", "CLOSE_ACTIVATE", "CLOSE_WAKE", "VOICE_WAKE_UP", "WAKE_UP", "WS_WE_COME_MSG", "wakeup", "", "getWakeup", "()Z", "setWakeup", "(Z)V", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeMultilpeFragment;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WelComeMultilpeFragment newInstance() {
            return new WelComeMultilpeFragment();
        }

        public final boolean getWakeup() {
            return WelComeMultilpeFragment.wakeup;
        }

        public final void setWakeup(boolean z) {
            WelComeMultilpeFragment.wakeup = z;
        }

        /* compiled from: WelComeMultilpeFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeMultilpeFragment$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeMultilpeFragment;", "(Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeMultilpeFragment;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<WelComeMultilpeFragment> mActivity;

            public WithoutLeakHandler(WelComeMultilpeFragment fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                WelComeMultilpeFragment welComeMultilpeFragment = this.mActivity.get();
                if (welComeMultilpeFragment != null) {
                    switch (msg.what) {
                        case 3:
                            Pdlog.m3273d(welComeMultilpeFragment.TAG, "receiveMsg: " + msg.obj.toString() + ' ');
                            try {
                                welComeMultilpeFragment.count++;
                                Object obj = msg.obj;
                                if (obj == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type android.os.Bundle");
                                }
                                Bundle bundle = (Bundle) obj;
                                if (bundle != null) {
                                    int i = welComeMultilpeFragment.count;
                                    int i2 = bundle.getInt("type");
                                    String string = bundle.getString(AIUIConstant.KEY_CONTENT);
                                    Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(\"content\")");
                                    welComeMultilpeFragment.updateMsg(new WeComeBean(i, i2, string, "", null, 16, null));
                                    return;
                                }
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        case 4:
                            welComeMultilpeFragment.playAnimationMode(3);
                            Pdlog.m3273d(welComeMultilpeFragment.TAG, "startWakeUp: 4 ");
                            AiVoiceManager.INSTANCE.startWakeUp(0, 0);
                            return;
                        case 5:
                            welComeMultilpeFragment.handler.removeMessages(9);
                            FrameLayout frameLayout = welComeMultilpeFragment.animationView;
                            if (frameLayout != null) {
                                welComeMultilpeFragment.getWeComeVm().showView(frameLayout, welComeMultilpeFragment.tvInputText);
                                return;
                            }
                            return;
                        case 6:
                            welComeMultilpeFragment.playAnimationMode(3);
                            Pdlog.m3273d(welComeMultilpeFragment.TAG, "AVOID_WAKE_UP : 6 ");
                            return;
                        case 7:
                            welComeMultilpeFragment.isStarSpeak = false;
                            int i3 = welComeMultilpeFragment.count;
                            MarqueeTextView marqueeTextView = welComeMultilpeFragment.tvInputText;
                            welComeMultilpeFragment.updateMsg(new WeComeBean(i3, 1, String.valueOf(marqueeTextView != null ? marqueeTextView.getText() : null), "", null, 16, null));
                            welComeMultilpeFragment.playAnimationMode(1);
                            welComeMultilpeFragment.handler.removeMessages(9);
                            welComeMultilpeFragment.handler.sendEmptyMessageDelayed(9, 1201000L);
                            if (welComeMultilpeFragment.animationView != null) {
                                welComeMultilpeFragment.getWeComeVm().closeView();
                                return;
                            }
                            return;
                        case 8:
                            if (WelComeMultilpeFragment.INSTANCE.getWakeup()) {
                                return;
                            }
                            welComeMultilpeFragment.handler.sendEmptyMessage(5);
                            WelComeMultilpeFragment.INSTANCE.setWakeup(true);
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }
}
