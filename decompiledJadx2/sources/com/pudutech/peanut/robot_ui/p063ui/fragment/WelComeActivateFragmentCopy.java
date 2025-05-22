package com.pudutech.peanut.robot_ui.p063ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.pudutech.mirsdk.aidl.IPersonDetect;
import com.pudutech.mirsdk.aidl.PersonDetectListener;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.TouchListenerManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bean.WeComeBean;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.AdVoicePlayManager;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.ChatMultipleItem;
import com.pudutech.peanut.robot_ui.p063ui.adapter.WeComeAdapter;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeActivateViewModel;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import com.pudutech.robot.module.report.task.ReportCustomerTask;
import com.pudutech.robot.module.voice.data.PlayEvent;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: WelComeActivateFragmentCopy.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 L2\u00020\u00012\u00020\u0002:\u0001LB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0002J\b\u0010,\u001a\u00020*H\u0002J\u0010\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u000bH\u0002J\u0010\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0002J\u0012\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0010\u00105\u001a\u00020*2\u0006\u00106\u001a\u000207H\u0016J$\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\b\u0010>\u001a\u00020*H\u0016J\b\u0010?\u001a\u00020*H\u0016J\b\u0010@\u001a\u00020*H\u0016J\b\u0010A\u001a\u00020*H\u0016J\u0012\u0010B\u001a\u00020*2\b\u0010C\u001a\u0004\u0018\u00010DH\u0016J\u0010\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020\u0005H\u0002J\u0010\u0010G\u001a\u00020*2\u0006\u0010.\u001a\u00020\u000bH\u0002J\u0010\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020\u000bH\u0002J\u0010\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020\u001aH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&¨\u0006M"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeActivateFragmentCopy;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/mirsdkwrap/lib/robot/TouchListenerManager$OnTouchListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "animationView", "Landroid/widget/FrameLayout;", AIUIConstant.KEY_CONTENT, "count", "", "counts", "finishId", "finishState", "handler", "Landroid/os/Handler;", "isStop", "", "mAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/WeComeAdapter;", "mAnimation", "Landroid/view/animation/AlphaAnimation;", "mList", "", "Lcom/pudutech/peanut/robot_ui/ui/adapter/ChatMultipleItem;", "Lcom/pudutech/peanut/robot_ui/bean/WeComeBean;", "mShouldScroll", "mState", "mToPosition", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "starSpeak", "tvInputText", "Lcom/pudutech/peanut/robot_ui/widget/MarqueeTextView;", "weComeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel;", "getWeComeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel;", "weComeVm$delegate", "Lkotlin/Lazy;", "initData", "", "initVM", "initView", "jump", "state", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDetach", "onResume", "onStop", "onTouchListener", "event", "Landroid/view/MotionEvent;", "playAnimation", "json", "playAnimationMode", "smoothMoveToPosition", RequestParameters.POSITION, "updateMsg", "msgBean", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeActivateFragmentCopy extends Fragment implements TouchListenerManager.OnTouchListener {
    public static final int AINIMOE_ISOPEN = 13;
    public static final int ALGORITHM_WAKE_UP = 10;
    public static final int AVOID_WAKE_UP = 6;
    public static final int BEGIN_VOICE = 4;
    public static final int CLOSE_ACTIVATE = 9;
    public static final int CLOSE_WAKE = 7;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int OPEN_FACE = 12;
    public static final int WAKE_UP = 5;
    public static final int WS_WE_COME_MSG = 3;
    private static volatile boolean isRecording;
    private String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private FrameLayout animationView;
    private String content;
    private volatile int count;
    private volatile int counts;
    private int finishId;
    private int finishState;
    private Handler handler;
    private boolean isStop;
    private WeComeAdapter mAdapter;
    private AlphaAnimation mAnimation;
    private List<ChatMultipleItem<WeComeBean>> mList;
    private boolean mShouldScroll;
    private volatile int mState;
    private int mToPosition;
    private final SingleClickListener onFaceAnimationViewClick;
    private volatile int starSpeak;
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

    public WelComeActivateFragmentCopy() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.weComeVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(WelComeActivateViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$$special$$inlined$viewModels$2
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
        this.content = "";
        this.count = 1;
        this.finishId = -1;
        this.onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$onFaceAnimationViewClick$1
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
                int i;
                AiVoiceManager.INSTANCE.stopPlayVoice();
                WelComeActivateFragmentCopy.this.playAnimationMode(3);
                WelComeActivateFragmentCopy.INSTANCE.setRecording(false);
                WelComeActivateFragmentCopy.this.starSpeak = 0;
                i = WelComeActivateFragmentCopy.this.mState;
                if (i != 0) {
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(10);
                } else {
                    WelComeActivateFragmentCopy.this.finishState = 2;
                }
            }
        });
        this.counts = -1;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C5508R.layout.welcome_activate_fragment, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…agment, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Pdlog.m3274e(this.TAG, "onActivityCreated");
        if (this.mList.size() != 0) {
            WelComeActivateViewModel weComeVm = getWeComeVm();
            if (weComeVm != null) {
                weComeVm.resetVmState();
            }
            AiVoiceManager.INSTANCE.forceStop();
            this.handler.sendEmptyMessage(10);
        } else {
            VoicePlayTasks.INSTANCE.playCustomerArrive(new Function3<PlayEvent, String, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$onActivityCreated$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent, String str, Boolean bool) {
                    invoke2(playEvent, str, bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PlayEvent it, String str, Boolean bool) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (Intrinsics.areEqual((Object) bool, (Object) false) && it == PlayEvent.Finish) {
                        WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(10);
                    }
                }
            });
        }
        TouchListenerManager.INSTANCE.addTouchListener(this);
        initView();
        initData();
        initVM();
        playAnimationMode(3);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        isRecording = false;
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
        isRecording = false;
    }

    private final void initView() {
        View view = getView();
        this.animationView = view != null ? (FrameLayout) view.findViewById(C5508R.id.flInputText) : null;
        View view2 = getView();
        this.tvInputText = view2 != null ? (MarqueeTextView) view2.findViewById(C5508R.id.tvInputText) : null;
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).setLoopPer(true);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).playAnimation(SceneAnimationResources.INSTANCE.getSolicit());
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        this.mAdapter = new WeComeAdapter(this.mList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(this.mAdapter);
        ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initView$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                boolean z;
                int i;
                Intrinsics.checkParameterIsNotNull(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                z = WelComeActivateFragmentCopy.this.mShouldScroll;
                if (z && newState == 0) {
                    WelComeActivateFragmentCopy.this.mShouldScroll = false;
                    WelComeActivateFragmentCopy welComeActivateFragmentCopy = WelComeActivateFragmentCopy.this;
                    i = welComeActivateFragmentCopy.mToPosition;
                    welComeActivateFragmentCopy.smoothMoveToPosition(i);
                }
            }
        });
        TextView tvCoupon = (TextView) _$_findCachedViewById(C5508R.id.tvCoupon);
        Intrinsics.checkExpressionValueIsNotNull(tvCoupon, "tvCoupon");
        ViewExtKt.onSingleClick(tvCoupon, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initView$3
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
                WelComeActivateFragmentCopy.this.jump(2);
            }
        });
        TextView tvFeatured = (TextView) _$_findCachedViewById(C5508R.id.tvFeatured);
        Intrinsics.checkExpressionValueIsNotNull(tvFeatured, "tvFeatured");
        ViewExtKt.onSingleClick(tvFeatured, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initView$4
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
                WelComeActivateFragmentCopy.this.jump(1);
            }
        });
        TextView tvGoShops = (TextView) _$_findCachedViewById(C5508R.id.tvGoShops);
        Intrinsics.checkExpressionValueIsNotNull(tvGoShops, "tvGoShops");
        ViewExtKt.onSingleClick(tvGoShops, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initView$5
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
                WelComeActivateFragmentCopy.this.jump(3);
            }
        });
        LinearLayout llBack = (LinearLayout) _$_findCachedViewById(C5508R.id.llBack);
        Intrinsics.checkExpressionValueIsNotNull(llBack, "llBack");
        ViewExtKt.onSingleClick(llBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initView$6
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
                AiVoiceManager.INSTANCE.stopPlayVoice();
                WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playAnimationMode(int state) {
        this.mAnimation = (AlphaAnimation) null;
        ImageView imageView = (ImageView) _$_findCachedViewById(C5508R.id.ivVoice);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim);
        if (lottieAnimationView != null) {
            lottieAnimationView.pauseAnimation();
        }
        if (state == 1) {
            playAnimation("animation/animation_loading.json");
            return;
        }
        if (state == 2) {
            playAnimation("animation/animation_playing.json");
            return;
        }
        if (state == 3) {
            LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim);
            if (lottieAnimationView2 != null) {
                lottieAnimationView2.setVisibility(8);
            }
            ImageView imageView2 = (ImageView) _$_findCachedViewById(C5508R.id.ivVoice);
            if (imageView2 != null) {
                imageView2.setVisibility(0);
                return;
            }
            return;
        }
        if (state != 4) {
            if (state != 5) {
                return;
            }
            playAnimation("animation/animation_listen.json");
        } else {
            ImageView imageView3 = (ImageView) _$_findCachedViewById(C5508R.id.ivVoice);
            if (imageView3 != null) {
                imageView3.setVisibility(8);
            }
        }
    }

    private final void playAnimation(String json) {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim);
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(0);
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim);
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setAnimation(json);
        }
        LottieAnimationView lottieAnimationView3 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.voice_float_anim);
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.playAnimation();
        }
    }

    private final void initData() {
        if (this.mList.size() == 0) {
            Bundle bundle = new Bundle();
            bundle.putString(AIUIConstant.KEY_CONTENT, getString(C5508R.string.ai_welcome));
            bundle.putInt("type", 2);
            this.handler.sendMessageDelayed(this.handler.obtainMessage(3, bundle), 300L);
            Bundle bundle2 = new Bundle();
            bundle2.putString(AIUIConstant.KEY_CONTENT, getString(C5508R.string.ai_say_special_dishes));
            bundle2.putInt("type", 2);
            this.handler.sendMessageDelayed(this.handler.obtainMessage(3, bundle2), 600L);
            Bundle bundle3 = new Bundle();
            bundle3.putString(AIUIConstant.KEY_CONTENT, getString(C5508R.string.ai_say_go_shop));
            bundle3.putInt("type", 2);
            this.handler.sendMessageDelayed(this.handler.obtainMessage(3, bundle3), 1000L);
        }
    }

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM");
        getWeComeVm().getMsgModel().observe(getViewLifecycleOwner(), new Observer<WeComeBean>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initVM$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(WeComeBean msg) {
                int i;
                int i2;
                int i3;
                String str;
                String str2;
                MarqueeTextView marqueeTextView;
                Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "msgModel: " + msg.toString() + ' ');
                if (msg.getType() == 1) {
                    if ((msg.getContent().length() > 0) && (marqueeTextView = WelComeActivateFragmentCopy.this.tvInputText) != null) {
                        marqueeTextView.setText(msg.getContent());
                    }
                    if (Intrinsics.areEqual((Object) msg.isFinish(), (Object) true)) {
                        i3 = WelComeActivateFragmentCopy.this.finishId;
                        if (i3 != msg.getId()) {
                            String str3 = WelComeActivateFragmentCopy.this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("msg is equals : ");
                            str = WelComeActivateFragmentCopy.this.content;
                            sb.append(str);
                            sb.append(" msg:");
                            sb.append(msg.getContent());
                            Pdlog.m3274e(str3, sb.toString());
                            str2 = WelComeActivateFragmentCopy.this.content;
                            if (Intrinsics.areEqual(str2, msg.getContent())) {
                                return;
                            }
                            WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(7);
                            WelComeActivateFragmentCopy.this.finishId = msg.getId();
                            WelComeActivateFragmentCopy.this.content = msg.getContent();
                        }
                    }
                    WelComeActivateFragmentCopy.this.count = msg.getId();
                    i = WelComeActivateFragmentCopy.this.starSpeak;
                    if (i == 1) {
                        return;
                    }
                    String str4 = WelComeActivateFragmentCopy.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("starSpeak: ");
                    i2 = WelComeActivateFragmentCopy.this.starSpeak;
                    sb2.append(i2);
                    sb2.append(" msgModel: ");
                    sb2.append(msg.toString());
                    sb2.append(' ');
                    Pdlog.m3273d(str4, sb2.toString());
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(5);
                    WelComeActivateFragmentCopy.this.starSpeak = 1;
                    WelComeActivateFragmentCopy.this.playAnimationMode(4);
                    WelComeActivateFragmentCopy.this.playAnimationMode(5);
                    return;
                }
                if (msg.getType() == 2) {
                    WelComeActivateFragmentCopy.this.mState = 0;
                    WelComeActivateFragmentCopy.this.starSpeak = 1;
                    FrameLayout frameLayout = WelComeActivateFragmentCopy.this.animationView;
                    if (frameLayout != null && frameLayout.getVisibility() == 0) {
                        WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(7);
                        Thread.sleep(200L);
                    }
                    WelComeActivateFragmentCopy.this.playAnimationMode(2);
                    WelComeActivateFragmentCopy welComeActivateFragmentCopy = WelComeActivateFragmentCopy.this;
                    Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
                    welComeActivateFragmentCopy.updateMsg(msg);
                    ReportCustomerTask.INSTANCE.trackingBusinessInteractiveEvent();
                }
            }
        });
        getWeComeVm().getMVoiceChangeState().observe(getViewLifecycleOwner(), new Observer<Integer>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initVM$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                int i;
                int i2;
                int i3;
                Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "mVoiceChangeState: " + num + ' ');
                if (num != null && num.intValue() == 1) {
                    WelComeActivateFragmentCopy.this.handler.removeMessages(9);
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(9, 15000L);
                    WelComeActivateFragmentCopy.INSTANCE.setRecording(false);
                    i3 = WelComeActivateFragmentCopy.this.mState;
                    if (i3 != 0) {
                        WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(10);
                    } else {
                        WelComeActivateFragmentCopy.this.finishState = 2;
                    }
                    Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "startWakeUp: " + num + ' ');
                    WelComeActivateFragmentCopy.this.playAnimationMode(3);
                    FrameLayout frameLayout = WelComeActivateFragmentCopy.this.animationView;
                    if (frameLayout == null || frameLayout.getVisibility() != 0) {
                        MarqueeTextView marqueeTextView = WelComeActivateFragmentCopy.this.tvInputText;
                        if (marqueeTextView != null) {
                            marqueeTextView.setText("");
                            return;
                        }
                        return;
                    }
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(13, 1000L);
                    return;
                }
                if (num != null && num.intValue() == 2) {
                    WelComeActivateFragmentCopy.this.handler.removeMessages(9);
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(9, 15000L);
                    WelComeActivateFragmentCopy.INSTANCE.setRecording(false);
                    i2 = WelComeActivateFragmentCopy.this.mState;
                    if (i2 != 0) {
                        WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(10);
                    } else {
                        WelComeActivateFragmentCopy.this.finishState = 2;
                    }
                    Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "startWakeUp: " + num + ' ');
                    WelComeActivateFragmentCopy.this.playAnimationMode(3);
                    FrameLayout frameLayout2 = WelComeActivateFragmentCopy.this.animationView;
                    if (frameLayout2 == null || frameLayout2.getVisibility() != 0) {
                        MarqueeTextView marqueeTextView2 = WelComeActivateFragmentCopy.this.tvInputText;
                        if (marqueeTextView2 != null) {
                            marqueeTextView2.setText("");
                            return;
                        }
                        return;
                    }
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(13, 1000L);
                    return;
                }
                if (num != null && num.intValue() == 3) {
                    Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "stopAiRecording: " + num + ' ');
                    WelComeActivateFragmentCopy.INSTANCE.setRecording(false);
                    AiVoiceManager.INSTANCE.stopAiRecording();
                    return;
                }
                if (num != null && num.intValue() == 6) {
                    WelComeActivateFragmentCopy.this.handler.removeMessages(9);
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(9, 15000L);
                    VoicePlayTasks.INSTANCE.stop();
                    if (WelComeActivateFragmentCopy.INSTANCE.isRecording()) {
                        return;
                    }
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(6);
                    return;
                }
                if (num != null && num.intValue() == 5) {
                    WelComeActivateFragmentCopy.this.playAnimationMode(3);
                    FrameLayout frameLayout3 = WelComeActivateFragmentCopy.this.animationView;
                    if (frameLayout3 != null && frameLayout3.getVisibility() == 0) {
                        WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(13, 1000L);
                    }
                    i = WelComeActivateFragmentCopy.this.mState;
                    if (i != 0) {
                        WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(10);
                    } else {
                        WelComeActivateFragmentCopy.this.finishState = 2;
                    }
                    WelComeActivateFragmentCopy.this.handler.removeMessages(9);
                    WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(9, 15000L);
                    WelComeActivateFragmentCopy.INSTANCE.setRecording(false);
                }
            }
        });
        getWeComeVm().getMInstructionState().observe(getViewLifecycleOwner(), new Observer<Integer>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$initVM$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "mInstructionState: " + num + ' ');
                if (num != null && num.intValue() == 1) {
                    AiVoiceManager.INSTANCE.forceStop();
                    WelComeActivateFragmentCopy.this.jump(1);
                } else if (num != null && num.intValue() == 2) {
                    AiVoiceManager.INSTANCE.forceStop();
                    WelComeActivateFragmentCopy.this.jump(2);
                } else if (num != null && num.intValue() == 3) {
                    AiVoiceManager.INSTANCE.forceStop();
                    WelComeActivateFragmentCopy.this.jump(3);
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.isStop = false;
        this.starSpeak = 0;
        this.counts = -1;
        this.finishState = 0;
        AiVoiceManager.INSTANCE.startAiRecording();
        this.handler.sendEmptyMessageDelayed(12, 800L);
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.lowVolumeMode(simpleName);
        this.handler.removeMessages(9);
        this.handler.sendEmptyMessageDelayed(9, 15000L);
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setWhiteBg(true);
        if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
            MyStatusBarLayout myStatusBarLayout = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar);
            if (myStatusBarLayout != null) {
                myStatusBarLayout.onNetStatus(true);
                return;
            }
            return;
        }
        MyStatusBarLayout myStatusBarLayout2 = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar);
        if (myStatusBarLayout2 != null) {
            myStatusBarLayout2.onNetStatus(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        IPersonDetect personDetect;
        IPersonDetect personDetect2;
        super.onStop();
        this.handler.removeMessages(9);
        this.handler.removeCallbacksAndMessages(null);
        SDKInterface mirSdk = SolicitInfoManager.INSTANCE.getMirSdk();
        if (mirSdk != null && (personDetect2 = mirSdk.getPersonDetect()) != null) {
            personDetect2.stopDetect();
        }
        SDKInterface mirSdk2 = SolicitInfoManager.INSTANCE.getMirSdk();
        if (mirSdk2 == null || (personDetect = mirSdk2.getPersonDetect()) == null) {
            return;
        }
        personDetect.removePersonDetectListener("personSolicit");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        AiVoiceManager.INSTANCE.stopPlayVoice();
        AiVoiceManager.INSTANCE.stopAiRecording();
        this.handler.removeCallbacksAndMessages(null);
        WelComeActivateViewModel weComeVm = getWeComeVm();
        if (weComeVm != null) {
            weComeVm.removeTextListener();
        }
        TouchListenerManager.INSTANCE.removeTouchEventListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(int state) {
        Pdlog.m3273d(this.TAG, "stopAiRecording: jump: " + state + ' ');
        if (this.isStop) {
            return;
        }
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.normalVolumeMode(simpleName);
        AiVoiceManager.INSTANCE.stopPlayVoice();
        AiVoiceManager.INSTANCE.stopAiRecording();
        VoicePlayTasks.INSTANCE.finishStop();
        if (state == 1) {
            this.isStop = true;
            NavigationExtKt.nav(this).navigate(C5508R.id.action_Featured_to_initNav);
            ReportCustomerTask.INSTANCE.trackingFeaturDishesEvent();
        } else if (state == 2) {
            this.isStop = true;
            NavigationExtKt.nav(this).navigate(C5508R.id.action_Promotions_to_initNav);
            ReportCustomerTask.INSTANCE.trackingCouponEvent();
        } else {
            if (state != 3) {
                return;
            }
            this.isStop = true;
            Intent intent = new Intent(getActivity(), (Class<?>) SolicitCustomerActivity.class);
            intent.putExtra("state", 1);
            jumpAndFinish(intent);
            ReportCustomerTask.INSTANCE.trackingGoShopEvent();
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
            if (this.mList.size() != 0) {
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
        ((RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView)).scrollToPosition(position);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView != null ? recyclerView.getLayoutManager() : null;
        if (layoutManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(position, 0);
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

    @Override // com.pudutech.mirsdkwrap.lib.robot.TouchListenerManager.OnTouchListener
    public void onTouchListener(MotionEvent event) {
        Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            this.handler.removeMessages(9);
        } else if (valueOf != null && valueOf.intValue() == 1) {
            this.handler.removeMessages(9);
            this.handler.sendEmptyMessageDelayed(9, 15000L);
        }
    }

    /* compiled from: WelComeActivateFragmentCopy.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeActivateFragmentCopy$Companion;", "", "()V", "AINIMOE_ISOPEN", "", "ALGORITHM_WAKE_UP", "AVOID_WAKE_UP", "BEGIN_VOICE", "CLOSE_ACTIVATE", "CLOSE_WAKE", "OPEN_FACE", "WAKE_UP", "WS_WE_COME_MSG", "isRecording", "", "()Z", "setRecording", "(Z)V", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeActivateFragmentCopy;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WelComeActivateFragmentCopy newInstance() {
            return new WelComeActivateFragmentCopy();
        }

        public final boolean isRecording() {
            return WelComeActivateFragmentCopy.isRecording;
        }

        public final void setRecording(boolean z) {
            WelComeActivateFragmentCopy.isRecording = z;
        }

        /* compiled from: WelComeActivateFragmentCopy.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeActivateFragmentCopy$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeActivateFragmentCopy;", "(Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeActivateFragmentCopy;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<WelComeActivateFragmentCopy> mActivity;

            public WithoutLeakHandler(WelComeActivateFragmentCopy fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                IPersonDetect personDetect;
                IPersonDetect personDetect2;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                final WelComeActivateFragmentCopy welComeActivateFragmentCopy = this.mActivity.get();
                if (welComeActivateFragmentCopy != null) {
                    switch (msg.what) {
                        case 3:
                            Pdlog.m3273d(welComeActivateFragmentCopy.TAG, "receiveMsg: " + msg.obj.toString() + ' ');
                            try {
                                welComeActivateFragmentCopy.count++;
                                Object obj = msg.obj;
                                if (obj == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type android.os.Bundle");
                                }
                                Bundle bundle = (Bundle) obj;
                                if (bundle != null) {
                                    int i = welComeActivateFragmentCopy.count;
                                    int i2 = bundle.getInt("type");
                                    String string = bundle.getString(AIUIConstant.KEY_CONTENT);
                                    Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(\"content\")");
                                    welComeActivateFragmentCopy.updateMsg(new WeComeBean(i, i2, string, "", null, 16, null));
                                    return;
                                }
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        case 4:
                            welComeActivateFragmentCopy.content = "";
                            welComeActivateFragmentCopy.mState = 0;
                            welComeActivateFragmentCopy.starSpeak = 0;
                            welComeActivateFragmentCopy.finishState = 0;
                            welComeActivateFragmentCopy.handler.removeMessages(9);
                            WelComeActivateFragmentCopy.INSTANCE.setRecording(true);
                            welComeActivateFragmentCopy.playAnimationMode(3);
                            AiVoiceManager.INSTANCE.startAiRecording();
                            Pdlog.m3273d(welComeActivateFragmentCopy.TAG, "startWakeUp: 4 ");
                            AiVoiceManager.INSTANCE.startWakeUp(0, 0);
                            return;
                        case 5:
                            welComeActivateFragmentCopy.handler.removeMessages(9);
                            FrameLayout frameLayout = welComeActivateFragmentCopy.animationView;
                            if (frameLayout != null) {
                                welComeActivateFragmentCopy.getWeComeVm().showView(frameLayout, welComeActivateFragmentCopy.tvInputText);
                                return;
                            }
                            return;
                        case 6:
                            welComeActivateFragmentCopy.handler.removeMessages(9);
                            WelComeActivateFragmentCopy.INSTANCE.setRecording(true);
                            welComeActivateFragmentCopy.playAnimationMode(3);
                            Pdlog.m3273d(welComeActivateFragmentCopy.TAG, "AVOID_WAKE_UP : 6 ");
                            return;
                        case 7:
                            if (welComeActivateFragmentCopy.finishState == 1) {
                                return;
                            }
                            welComeActivateFragmentCopy.finishState = 1;
                            int i3 = welComeActivateFragmentCopy.count;
                            MarqueeTextView marqueeTextView = welComeActivateFragmentCopy.tvInputText;
                            welComeActivateFragmentCopy.updateMsg(new WeComeBean(i3, 1, String.valueOf(marqueeTextView != null ? marqueeTextView.getText() : null), "", null, 16, null));
                            welComeActivateFragmentCopy.playAnimationMode(1);
                            if (welComeActivateFragmentCopy.animationView != null) {
                                welComeActivateFragmentCopy.getWeComeVm().closeView();
                                return;
                            }
                            return;
                        case 8:
                        case 11:
                        default:
                            return;
                        case 9:
                            AiVoiceManager.INSTANCE.stopPlayVoice();
                            NavigationExtKt.nav(welComeActivateFragmentCopy).popBackStack();
                            return;
                        case 10:
                            VoicePlayTasks.INSTANCE.stop();
                            welComeActivateFragmentCopy.handler.sendEmptyMessage(4);
                            return;
                        case 12:
                            SDKInterface mirSdk = SolicitInfoManager.INSTANCE.getMirSdk();
                            if (mirSdk != null && (personDetect2 = mirSdk.getPersonDetect()) != null) {
                                personDetect2.startDetect(-0.7853981633974483d, 0.7853981633974483d, 1.5d);
                            }
                            SDKInterface mirSdk2 = SolicitInfoManager.INSTANCE.getMirSdk();
                            if (mirSdk2 == null || (personDetect = mirSdk2.getPersonDetect()) == null) {
                                return;
                            }
                            personDetect.addPersonDetectListener("personSolicit", new PersonDetectListener.Stub() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeActivateFragmentCopy$Companion$WithoutLeakHandler$handleMessage$1$3
                                @Override // com.pudutech.mirsdk.aidl.PersonDetectListener
                                public void onPersonDetection(int p0, int p1, double p2, double p3, double p4, double p5, double p6) {
                                    int i4;
                                    int i5;
                                    Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "onPersonDetection ALl :p0:" + p0 + " p1:" + p1 + ":p2:" + p2 + " p3:" + p3 + ":p4:" + p4 + " p5:" + p5);
                                    if (p0 == 1) {
                                        Pdlog.m3273d(WelComeActivateFragmentCopy.this.TAG, "onPersonDetection :p0:" + p0 + " p1:" + p1 + ":p2:" + p2 + " p3:" + p3 + ":p4:" + p4 + " p5:" + p5);
                                        if (WelComeActivateFragmentCopy.this.finishState == 2 && !WelComeActivateFragmentCopy.INSTANCE.isRecording()) {
                                            WelComeActivateFragmentCopy.this.handler.sendEmptyMessage(10);
                                        }
                                        WelComeActivateFragmentCopy welComeActivateFragmentCopy2 = WelComeActivateFragmentCopy.this;
                                        i4 = welComeActivateFragmentCopy2.counts;
                                        welComeActivateFragmentCopy2.counts = i4 + 1;
                                        i5 = WelComeActivateFragmentCopy.this.counts;
                                        if (i5 >= 8) {
                                            WelComeActivateFragmentCopy.this.handler.removeMessages(9);
                                            WelComeActivateFragmentCopy.this.handler.sendEmptyMessageDelayed(9, 15000L);
                                            WelComeActivateFragmentCopy.this.counts = 0;
                                        }
                                        WelComeActivateFragmentCopy.this.mState = 1;
                                    }
                                }
                            });
                            return;
                        case 13:
                            FrameLayout frameLayout2 = welComeActivateFragmentCopy.animationView;
                            if (frameLayout2 == null || frameLayout2.getVisibility() != 0) {
                                return;
                            }
                            welComeActivateFragmentCopy.handler.sendEmptyMessage(7);
                            return;
                    }
                }
            }
        }
    }
}
