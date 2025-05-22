package com.pudutech.peanut.robot_ui.p063ui.cruise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.AdVoicePlayManager;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeUnActivateViewModel;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import com.pudutech.robot.module.report.task.ReportCustomerTask;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: CruiseWelComeUnActivateFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\u0018\u0000 92\u00020\u00012\u00020\u00022\u00020\u0003:\u00019B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010\u001e\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#H\u0016J$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010*\u001a\u00020\u0018H\u0016J\b\u0010+\u001a\u00020\u0018H\u0016J(\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u000200H\u0016J(\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020.2\u0006\u00105\u001a\u0002002\u0006\u00102\u001a\u0002002\u0006\u00106\u001a\u00020.H\u0016J\b\u00107\u001a\u00020\u0018H\u0016J\b\u00108\u001a\u00020\u0018H\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006:"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeUnActivateFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnFaceDetectChangeListener;", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnPersonDetectionListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "animationView", "Landroid/widget/FrameLayout;", "handler", "Landroid/os/Handler;", "isGoActivity", "", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onWareAnimationViewClick", "weComeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeUnActivateViewModel;", "getWeComeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeUnActivateViewModel;", "weComeVm$delegate", "Lkotlin/Lazy;", "initData", "", "initVM", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDetach", "onFaceDetectResult", "flag", "", "yaw", "", "pitch", "distance", "onPersonDetection", SpeechUtility.TAG_RESOURCE_RESULT, "degree", "roteStatus", "onResume", "onStop", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseWelComeUnActivateFragment extends Fragment implements SolicitInfoManager.OnFaceDetectChangeListener, SolicitInfoManager.OnPersonDetectionListener {
    public static final int ACTIVATE_UP = 2;
    public static final int ALGORITHM_WAKE_UP = 7;
    public static final int AVOID_WAKE_UP = 6;
    public static final int CLOSE_WAKE = 9;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int POLLING_WAKE_UP = 8;
    public static final int WAKE_UP = 5;
    private static boolean wakeup;
    private String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private FrameLayout animationView;
    private Handler handler;
    private boolean isGoActivity;
    private final SingleClickListener onFaceAnimationViewClick;
    private final SingleClickListener onWareAnimationViewClick;

    /* renamed from: weComeVm$delegate, reason: from kotlin metadata */
    private final Lazy weComeVm;

    /* JADX INFO: Access modifiers changed from: private */
    public final WelComeUnActivateViewModel getWeComeVm() {
        return (WelComeUnActivateViewModel) this.weComeVm.getValue();
    }

    private final void initData() {
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

    public CruiseWelComeUnActivateFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeUnActivateFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.weComeVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(WelComeUnActivateViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeUnActivateFragment$$special$$inlined$viewModels$2
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
        this.onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeUnActivateFragment$onFaceAnimationViewClick$1
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
                CruiseWelComeUnActivateFragment.this.handler.sendEmptyMessage(2);
            }
        });
        this.onWareAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeUnActivateFragment$onWareAnimationViewClick$1
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
                CruiseWelComeUnActivateFragment.this.handler.sendEmptyMessage(2);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C5508R.layout.welcome_unactivate_fragment, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…agment, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initVM();
        SolicitInfoManager.INSTANCE.addFaceDetectListener(this);
        SolicitInfoManager.INSTANCE.addPersonDetectionListener(this);
        SolicitInfoManager.INSTANCE.startSolicit();
    }

    private final void initView() {
        View view = getView();
        this.animationView = view != null ? (FrameLayout) view.findViewById(C5508R.id.flInputText) : null;
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).addOnFaceClickListener(this.onWareAnimationViewClick);
        TextView tvGoShop = (TextView) _$_findCachedViewById(C5508R.id.tvGoShop);
        Intrinsics.checkExpressionValueIsNotNull(tvGoShop, "tvGoShop");
        ViewExtKt.onSingleClick(tvGoShop, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeUnActivateFragment$initView$1
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
                CruiseWelComeUnActivateFragment.this.jumpAndFinish(new Intent(CruiseWelComeUnActivateFragment.this.getActivity(), (Class<?>) CruiseSolicitActivity.class));
            }
        });
        TextView tvTurnBack = (TextView) _$_findCachedViewById(C5508R.id.tvTurnBack);
        Intrinsics.checkExpressionValueIsNotNull(tvTurnBack, "tvTurnBack");
        ViewExtKt.onSingleClick(tvTurnBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.cruise.CruiseWelComeUnActivateFragment$initView$2
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
                Intent intent = new Intent(CruiseWelComeUnActivateFragment.this.getActivity(), (Class<?>) CruiseActivity.class);
                intent.putExtra(CruiseActivity.CRUISE_STATE, 1);
                CruiseWelComeUnActivateFragment.this.jumpAndFinish(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        VoicePlayTasks.INSTANCE.finishStop();
        startActivity(intent);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
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

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM ");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.handler.sendEmptyMessageDelayed(8, 12000L);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView != null) {
            faceVideoView.setLoopPer(true);
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView2 != null) {
            faceVideoView2.playAnimation(SceneAnimationResources.INSTANCE.getSolicit());
        }
        this.isGoActivity = false;
        FaceVideoView faceVideoView3 = (FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim);
        if (faceVideoView3 != null) {
            faceVideoView3.setLoopPer(true);
        }
        FaceVideoView faceVideoView4 = (FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim);
        if (faceVideoView4 != null) {
            faceVideoView4.playAnimation(SceneAnimationResources.INSTANCE.getWelCome());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.handler.removeMessages(8);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim);
        if (faceVideoView2 != null) {
            faceVideoView2.stopPlay();
        }
        SolicitInfoManager.INSTANCE.removeFaceDetectListener(this);
        SolicitInfoManager.INSTANCE.removePersonDetectionListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim);
        if (faceVideoView2 != null) {
            faceVideoView2.stopPlay();
        }
        SolicitInfoManager.INSTANCE.stopSolicit();
    }

    /* compiled from: CruiseWelComeUnActivateFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeUnActivateFragment$Companion;", "", "()V", "ACTIVATE_UP", "", "ALGORITHM_WAKE_UP", "AVOID_WAKE_UP", "CLOSE_WAKE", "POLLING_WAKE_UP", "WAKE_UP", "wakeup", "", "getWakeup", "()Z", "setWakeup", "(Z)V", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeUnActivateFragment;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CruiseWelComeUnActivateFragment newInstance() {
            return new CruiseWelComeUnActivateFragment();
        }

        public final boolean getWakeup() {
            return CruiseWelComeUnActivateFragment.wakeup;
        }

        public final void setWakeup(boolean z) {
            CruiseWelComeUnActivateFragment.wakeup = z;
        }

        /* compiled from: CruiseWelComeUnActivateFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeUnActivateFragment$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeUnActivateFragment;", "(Lcom/pudutech/peanut/robot_ui/ui/cruise/CruiseWelComeUnActivateFragment;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<CruiseWelComeUnActivateFragment> mActivity;

            public WithoutLeakHandler(CruiseWelComeUnActivateFragment fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                CruiseWelComeUnActivateFragment cruiseWelComeUnActivateFragment = this.mActivity.get();
                if (cruiseWelComeUnActivateFragment != null) {
                    int i = msg.what;
                    if (i != 2) {
                        switch (i) {
                            case 5:
                                Pdlog.m3273d(cruiseWelComeUnActivateFragment.TAG, "wake_u:" + CruiseWelComeUnActivateFragment.INSTANCE.getWakeup() + ' ');
                                if (CruiseWelComeUnActivateFragment.INSTANCE.getWakeup()) {
                                    return;
                                }
                                VoicePlayTasks.playAttractCustomer2$default(VoicePlayTasks.INSTANCE, null, 1, null);
                                FrameLayout frameLayout = cruiseWelComeUnActivateFragment.animationView;
                                if (frameLayout != null) {
                                    MarqueeTextView tvInputText = (MarqueeTextView) cruiseWelComeUnActivateFragment._$_findCachedViewById(C5508R.id.tvInputText);
                                    Intrinsics.checkExpressionValueIsNotNull(tvInputText, "tvInputText");
                                    tvInputText.setText(cruiseWelComeUnActivateFragment.getString(C5508R.string.voice50_2));
                                    cruiseWelComeUnActivateFragment.getWeComeVm().showView(frameLayout);
                                }
                                cruiseWelComeUnActivateFragment.handler.sendEmptyMessageDelayed(9, SolicitService.CAMERA_OPEN_TIME_OUT);
                                CruiseWelComeUnActivateFragment.INSTANCE.setWakeup(true);
                                cruiseWelComeUnActivateFragment.handler.sendEmptyMessageDelayed(6, 5000L);
                                return;
                            case 6:
                                CruiseWelComeUnActivateFragment.INSTANCE.setWakeup(false);
                                return;
                            case 7:
                                if (CruiseWelComeUnActivateFragment.INSTANCE.getWakeup()) {
                                    return;
                                }
                                cruiseWelComeUnActivateFragment.handler.sendEmptyMessage(5);
                                return;
                            case 8:
                                cruiseWelComeUnActivateFragment.handler.removeMessages(8);
                                cruiseWelComeUnActivateFragment.handler.sendEmptyMessageDelayed(8, 12000L);
                                cruiseWelComeUnActivateFragment.handler.sendEmptyMessage(7);
                                return;
                            case 9:
                                if (cruiseWelComeUnActivateFragment.animationView != null) {
                                    Pdlog.m3273d(cruiseWelComeUnActivateFragment.TAG, "closeView");
                                    cruiseWelComeUnActivateFragment.getWeComeVm().closeView();
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                    if (!cruiseWelComeUnActivateFragment.isGoActivity) {
                        NavigationExtKt.nav(cruiseWelComeUnActivateFragment).navigate(C5508R.id.action_welComeActivateFragment_to_initNav);
                    }
                    cruiseWelComeUnActivateFragment.isGoActivity = true;
                }
            }
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager.OnFaceDetectChangeListener
    public void onFaceDetectResult(int flag, double yaw, double pitch, double distance) {
        if (flag == 1) {
            this.handler.sendEmptyMessage(2);
            ReportCustomerTask.INSTANCE.trackingAwakeEvent();
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager.OnPersonDetectionListener
    public void onPersonDetection(int result, double degree, double distance, int roteStatus) {
        if (result == 1) {
            this.handler.sendEmptyMessage(7);
            ReportCustomerTask.INSTANCE.trackingCustomerFlowEvent();
        }
    }
}
