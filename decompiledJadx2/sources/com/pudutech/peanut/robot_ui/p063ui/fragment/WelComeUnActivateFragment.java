package com.pudutech.peanut.robot_ui.p063ui.fragment;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.ext.view.NavigationExtKt;
import com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.AdVoicePlayManager;
import com.pudutech.peanut.robot_ui.p063ui.HomeActivity;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.WelComeDialogueActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeDialogMoveVm;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeUnActivateViewModel;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import com.pudutech.robot.module.report.task.ReportCustomerTask;
import com.pudutech.robot.module.voice.data.PlayEvent;
import java.lang.ref.WeakReference;
import java.util.HashMap;
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

/* compiled from: WelComeUnActivateFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\u0018\u0000 >2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001>B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0002J\u0012\u0010\"\u001a\u00020\u00182\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'H\u0016J$\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010.\u001a\u00020\u0018H\u0016J\b\u0010/\u001a\u00020\u0018H\u0016J(\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u000204H\u0016J(\u00107\u001a\u00020\u00182\u0006\u00108\u001a\u0002022\u0006\u00109\u001a\u0002042\u0006\u00106\u001a\u0002042\u0006\u0010:\u001a\u000202H\u0016J\b\u0010;\u001a\u00020\u0018H\u0016J\b\u0010<\u001a\u00020\u0018H\u0016J\b\u0010=\u001a\u00020\u0018H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006?"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeUnActivateFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnFaceDetectChangeListener;", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnPersonDetectionListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "animationView", "Landroid/widget/FrameLayout;", "handler", "Landroid/os/Handler;", "isGoActivity", "", "onFaceAnimationViewClick", "Lcom/pudutech/peanut/robot_ui/ui_utils/SingleClickListener;", "onWareAnimationViewClick", "weComeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeUnActivateViewModel;", "getWeComeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeUnActivateViewModel;", "weComeVm$delegate", "Lkotlin/Lazy;", "active", "", "getActivityState", "Landroidx/fragment/app/FragmentActivity;", "goSolicitActivity", "initData", "initVM", "initView", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDetach", "onFaceDetectResult", "flag", "", "yaw", "", "pitch", "distance", "onPersonDetection", SpeechUtility.TAG_RESOURCE_RESULT, "degree", "roteStatus", "onResume", "onStop", "stop", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeUnActivateFragment extends Fragment implements SolicitInfoManager.OnFaceDetectChangeListener, SolicitInfoManager.OnPersonDetectionListener {
    public static final int ACTIVATE_UP = 2;
    public static final int ALGORITHM_WAKE_UP = 7;
    public static final int AVOID_WAKE_UP = 6;
    public static final int BEGIN_WAKE = 10;
    public static final int CLOSE_WAKE = 9;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int POLLING_WAKE_UP = 8;
    public static final int WAKE_UP = 5;
    private static int count;
    private static boolean isJumpTo;
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

    public WelComeUnActivateFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.weComeVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(WelComeUnActivateViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$$special$$inlined$viewModels$2
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
        this.onFaceAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$onFaceAnimationViewClick$1
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
                WelComeUnActivateFragment.this.goSolicitActivity();
            }
        });
        this.onWareAnimationViewClick = new SingleClickListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$onWareAnimationViewClick$1
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
                WelComeUnActivateFragment.this.goSolicitActivity();
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
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        wakeup = false;
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.lowVolumeMode(simpleName);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        wakeup = false;
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.normalVolumeMode(simpleName);
    }

    private final void initView() {
        wakeup = false;
        View view = getView();
        this.animationView = view != null ? (FrameLayout) view.findViewById(C5508R.id.flInputText) : null;
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice)).addOnFaceClickListener(this.onWareAnimationViewClick);
        TextView tvGoShop = (TextView) _$_findCachedViewById(C5508R.id.tvGoShop);
        Intrinsics.checkExpressionValueIsNotNull(tvGoShop, "tvGoShop");
        ViewExtKt.onSingleClick(tvGoShop, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$initView$1
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
                Intent intent = new Intent(WelComeUnActivateFragment.this.getActivity(), (Class<?>) SolicitCustomerActivity.class);
                intent.putExtra("state", 1);
                WelComeUnActivateFragment.this.jumpAndFinish(intent);
            }
        });
        TextView tvTurnBack = (TextView) _$_findCachedViewById(C5508R.id.tvTurnBack);
        Intrinsics.checkExpressionValueIsNotNull(tvTurnBack, "tvTurnBack");
        ViewExtKt.onSingleClick(tvTurnBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$initView$2
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
                WelComeUnActivateFragment welComeUnActivateFragment = WelComeUnActivateFragment.this;
                welComeUnActivateFragment.jumpAndFinish(new Intent(welComeUnActivateFragment.getActivity(), (Class<?>) HomeActivity.class));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        isJumpTo = true;
        startActivity(intent);
        VoicePlayTasks.INSTANCE.finishStop();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private final void initVM() {
        Pdlog.m3273d(this.TAG, "initVM handler=" + this.handler);
        FragmentActivity requireActivity = requireActivity();
        if (requireActivity == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity");
        }
        WelComeDialogMoveVm welComeDialogMoveVm = ((WelComeDialogueActivity) requireActivity).getWelComeDialogMoveVm();
        String str = this.TAG;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity2, "requireActivity()");
        Pdlog.m3273d(str, requireActivity2.getViewModelStore(), welComeDialogMoveVm);
        welComeDialogMoveVm.getHandlerConnectionLD().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$initVM$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str2) {
                Pdlog.m3273d(WelComeUnActivateFragment.this.TAG, str2 + " handler=" + WelComeUnActivateFragment.this.handler);
                if (Intrinsics.areEqual(str2, WelComeDialogueActivity.CLEAR_HANDLER)) {
                    WelComeUnActivateFragment.this.stop();
                } else if (Intrinsics.areEqual(str2, WelComeDialogueActivity.RESTART_HANDLER)) {
                    WelComeUnActivateFragment.this.active();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentActivity getActivityState() {
        return getActivity();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        active();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void active() {
        this.handler.sendEmptyMessageDelayed(8, Long.parseLong(SpUtils.get(RobotContext.INSTANCE.getContext(), Constans.KEY_SOLICIT_VOICE_INTERVAL, "20")) * 1000);
        if (isJumpTo) {
            this.handler.sendEmptyMessageDelayed(10, 600L);
        } else {
            this.handler.sendEmptyMessage(10);
        }
        wakeup = false;
        isJumpTo = false;
        Pdlog.m3274e(this.TAG, "onResume");
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
            faceVideoView4.setState(1);
        }
        FaceVideoView faceVideoView5 = (FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim);
        if (faceVideoView5 != null) {
            faceVideoView5.playAnimation(SceneAnimationResources.INSTANCE.getWelCome());
        }
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.lowVolumeMode(simpleName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stop() {
        this.handler.removeMessages(8);
        this.handler.removeMessages(5);
        this.handler.removeMessages(9);
        this.handler.removeMessages(7);
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.faceViewVoice);
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.home_float_anim);
        if (faceVideoView2 != null) {
            faceVideoView2.stopPlay();
        }
        Pdlog.m3274e(this.TAG, "onStop");
        SolicitInfoManager.INSTANCE.removeFaceDetectListener(this);
        SolicitInfoManager.INSTANCE.removePersonDetectionListener(this);
        SolicitInfoManager.INSTANCE.stopSolicit();
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.normalVolumeMode(simpleName);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        stop();
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
    }

    /* compiled from: WelComeUnActivateFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeUnActivateFragment$Companion;", "", "()V", "ACTIVATE_UP", "", "ALGORITHM_WAKE_UP", "AVOID_WAKE_UP", "BEGIN_WAKE", "CLOSE_WAKE", "POLLING_WAKE_UP", "WAKE_UP", "count", "getCount", "()I", "setCount", "(I)V", "isJumpTo", "", "()Z", "setJumpTo", "(Z)V", "wakeup", "getWakeup", "setWakeup", "newInstance", "Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeUnActivateFragment;", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WelComeUnActivateFragment newInstance() {
            return new WelComeUnActivateFragment();
        }

        public final boolean getWakeup() {
            return WelComeUnActivateFragment.wakeup;
        }

        public final void setWakeup(boolean z) {
            WelComeUnActivateFragment.wakeup = z;
        }

        public final int getCount() {
            return WelComeUnActivateFragment.count;
        }

        public final void setCount(int i) {
            WelComeUnActivateFragment.count = i;
        }

        public final boolean isJumpTo() {
            return WelComeUnActivateFragment.isJumpTo;
        }

        public final void setJumpTo(boolean z) {
            WelComeUnActivateFragment.isJumpTo = z;
        }

        /* compiled from: WelComeUnActivateFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeUnActivateFragment$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "fragment", "Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeUnActivateFragment;", "(Lcom/pudutech/peanut/robot_ui/ui/fragment/WelComeUnActivateFragment;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<WelComeUnActivateFragment> mActivity;

            public WithoutLeakHandler(WelComeUnActivateFragment fragment) {
                Intrinsics.checkParameterIsNotNull(fragment, "fragment");
                this.mActivity = new WeakReference<>(fragment);
            }

            @Override // android.os.Handler
            public void handleMessage(final Message msg) {
                final WelComeUnActivateFragment welComeUnActivateFragment;
                int i;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                if (WelComeUnActivateFragment.INSTANCE.isJumpTo() || (welComeUnActivateFragment = this.mActivity.get()) == null || welComeUnActivateFragment.getActivityState() == null || (i = msg.what) == 2) {
                    return;
                }
                switch (i) {
                    case 5:
                        Pdlog.m3273d(welComeUnActivateFragment.TAG, "wake_u:" + WelComeUnActivateFragment.INSTANCE.getWakeup() + ' ');
                        if (WelComeUnActivateFragment.INSTANCE.getWakeup()) {
                            return;
                        }
                        String playAttractCustomer2 = VoicePlayTasks.INSTANCE.playAttractCustomer2(new Function3<PlayEvent, String, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$Companion$WithoutLeakHandler$handleMessage$$inlined$apply$lambda$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                    WelComeUnActivateFragment.this.handler.sendEmptyMessage(9);
                                }
                            }
                        });
                        Pdlog.m3273d(welComeUnActivateFragment.TAG, "content:" + playAttractCustomer2 + ' ');
                        FrameLayout frameLayout = welComeUnActivateFragment.animationView;
                        if (frameLayout != null) {
                            MarqueeTextView tvInputText = (MarqueeTextView) welComeUnActivateFragment._$_findCachedViewById(C5508R.id.tvInputText);
                            Intrinsics.checkExpressionValueIsNotNull(tvInputText, "tvInputText");
                            tvInputText.setText(playAttractCustomer2);
                            welComeUnActivateFragment.getWeComeVm().showView(frameLayout);
                        }
                        WelComeUnActivateFragment.INSTANCE.setWakeup(true);
                        welComeUnActivateFragment.handler.sendEmptyMessageDelayed(6, 8000L);
                        return;
                    case 6:
                        WelComeUnActivateFragment.INSTANCE.setWakeup(false);
                        return;
                    case 7:
                        Pdlog.m3274e(welComeUnActivateFragment.TAG, "is open  ALGORITHM_WAKE_UP");
                        if (WelComeUnActivateFragment.INSTANCE.getWakeup()) {
                            return;
                        }
                        welComeUnActivateFragment.handler.sendEmptyMessage(5);
                        return;
                    case 8:
                        long parseLong = Long.parseLong(SpUtils.get(RobotContext.INSTANCE.getContext(), Constans.KEY_SOLICIT_VOICE_INTERVAL, "20"));
                        welComeUnActivateFragment.handler.removeMessages(8);
                        welComeUnActivateFragment.handler.sendEmptyMessageDelayed(8, parseLong * 1000);
                        welComeUnActivateFragment.handler.sendEmptyMessage(7);
                        return;
                    case 9:
                        if (welComeUnActivateFragment.animationView != null) {
                            Pdlog.m3273d(welComeUnActivateFragment.TAG, "closeView");
                            welComeUnActivateFragment.getWeComeVm().closeView();
                            return;
                        }
                        return;
                    case 10:
                        Pdlog.m3273d(welComeUnActivateFragment.TAG, "START BEGIN_WAKE");
                        SolicitInfoManager.INSTANCE.startSolicit();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager.OnFaceDetectChangeListener
    public void onFaceDetectResult(int flag, double yaw, double pitch, double distance) {
        if (flag == 1) {
            Pdlog.m3274e(this.TAG, " flag : " + flag);
            requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.WelComeUnActivateFragment$onFaceDetectResult$$inlined$runOnUiThread$1
                @Override // java.lang.Runnable
                public final void run() {
                    WelComeUnActivateFragment.this.goSolicitActivity();
                    ReportCustomerTask.INSTANCE.trackingAwakeEvent();
                }
            });
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager.OnPersonDetectionListener
    public void onPersonDetection(int result, double degree, double distance, int roteStatus) {
        if (result == 1) {
            this.handler.sendEmptyMessage(7);
            ReportCustomerTask.INSTANCE.trackingCustomerFlowEvent();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goSolicitActivity() {
        if (!this.isGoActivity && getActivity() != null) {
            isJumpTo = true;
            VoicePlayTasks.INSTANCE.stop();
            NavigationExtKt.nav(this).navigate(C5508R.id.action_welComeActivateFragment_to_initNav_go);
        }
        this.isGoActivity = true;
    }
}
