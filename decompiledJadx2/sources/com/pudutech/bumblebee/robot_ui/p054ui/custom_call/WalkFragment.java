package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract;
import com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.LongDoublePressConstraintLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.task.DeliveryTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VerticalAlignTextSpan;
import com.pudutech.bumblebee.robot_ui.util.BeeperUtils;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: WalkFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010F\u001a\u000201H\u0002J\b\u0010G\u001a\u000201H\u0002J\b\u0010H\u001a\u000201H\u0002J\b\u0010I\u001a\u00020\u0006H\u0016J\b\u0010J\u001a\u000201H\u0002J\b\u0010K\u001a\u000201H\u0002J\b\u0010L\u001a\u000201H\u0002J\u0010\u0010M\u001a\u0002012\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u0002012\u0006\u0010Q\u001a\u00020RH\u0002J\u0010\u0010S\u001a\u0002012\u0006\u0010T\u001a\u00020\u0006H\u0002J\u001a\u0010U\u001a\u0002012\u0006\u0010N\u001a\u00020O2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\b\u0010X\u001a\u000201H\u0002J\b\u0010Y\u001a\u000201H\u0002J\b\u0010Z\u001a\u000201H\u0016J\b\u0010[\u001a\u000201H\u0002J\b\u0010\\\u001a\u000201H\u0016J\u0010\u0010]\u001a\u0002012\u0006\u0010C\u001a\u00020\nH\u0002J\b\u0010^\u001a\u000201H\u0002J\u0010\u0010_\u001a\u0002012\u0006\u0010T\u001a\u00020\u001bH\u0016J\b\u0010`\u001a\u000201H\u0002J\u0018\u0010a\u001a\u0002012\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020eH\u0016J\u0010\u0010f\u001a\u0002012\u0006\u0010C\u001a\u00020\nH\u0002J\b\u0010g\u001a\u000201H\u0002J\b\u0010h\u001a\u000201H\u0002J\u0010\u0010i\u001a\u0002012\u0006\u0010j\u001a\u00020kH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0012\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u0012\u001a\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\n\u0012\u0004\u0012\u000201\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R@\u00102\u001a4\u0012\u0013\u0012\u00110$¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0013\u0012\u00110$¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(7\u0012\u0004\u0012\u000201\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010;\u001a\n\u0012\u0004\u0012\u000201\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\u0012\u001a\u0004\b>\u0010?R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006l"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/WalkFragment;", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BaseCustomCallFragment;", "Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "()V", "STATE_ARRIVE", "", "STATE_PAUSE", "STATE_RUN", "TAG", "", "arrivalVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "arriveLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getArriveLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "arriveLayout$delegate", "Lkotlin/Lazy;", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "cancelWaitTimeoutDisposable", "Lio/reactivex/disposables/Disposable;", "currentState", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "customCallRunPresenter", "Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunPresenter;", "getCustomCallRunPresenter", "()Lcom/pudutech/bumblebee/presenter/custom_call_task/CustomCallRunPresenter;", "customCallRunPresenter$delegate", "customCallTargetBean", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "isUserCancel", "", "locationLostVoiceTask", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "onErrorClearCallback", "Lkotlin/Function0;", "", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "pauseLayout", "Landroid/widget/RelativeLayout;", "getPauseLayout", "()Landroid/widget/RelativeLayout;", "pauseLayout$delegate", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", TypedValues.Attributes.S_TARGET, "voicePlayerHelper", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper;", "bindPresenter", "cancelTask", "createCancelTimeout", "getLayoutId", "hideCancelBackBtn", "hidePauseLayout", "hideWalkLayout", "initview", "view", "Landroid/view/View;", "jump", "intent", "Landroid/content/Intent;", "onUIChange", "state", "onViewCreated", "savedInstanceState", "Landroid/os/Bundle;", "pauseNoTimeTask", "pauseTask", "release", "resumeTask", "setData", "setWalkTarget", "showCancelBackBtn", "showOnTheWayState", "showPauseLayout", "showTimeLeft", "time", "", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "showWalkLayout", "startTask", "unBindPresenter", "updateCustomCallContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class WalkFragment extends BaseCustomCallFragment implements CustomCallRunContract.ViewInterface, AutoResumeCountDownContract.ViewInterface {
    private final int STATE_ARRIVE;
    private final int STATE_PAUSE;
    private final int STATE_RUN;
    private HashMap _$_findViewCache;
    private final VoiceTask arrivalVoiceTask;

    /* renamed from: arriveLayout$delegate, reason: from kotlin metadata */
    private final Lazy arriveLayout;
    private Disposable cancelWaitTimeoutDisposable;
    private DeliveryContract.DeliveryEvent currentState;
    private CustomCallTargetBean customCallTargetBean;
    private boolean isUserCancel;
    private final VoiceTask locationLostVoiceTask;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private final MotionEventHelper motionEventHelper;
    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceViewClick;
    private Function0<Unit> onFallDropCallBack;
    private Function0<Unit> onLostLocationLostCallback;

    /* renamed from: pauseLayout$delegate, reason: from kotlin metadata */
    private final Lazy pauseLayout;
    private final RunningErrorHelper runningErrorHelper;
    private String target;
    private final VoicePlayerHelper voicePlayerHelper;
    private final String TAG = "WalkFragment";

    /* renamed from: customCallRunPresenter$delegate, reason: from kotlin metadata */
    private final Lazy customCallRunPresenter = LazyKt.lazy(new Function0<CustomCallRunPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$customCallRunPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CustomCallRunPresenter invoke() {
            CustomCallRunPresenter customCallRunPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(CustomCallRunPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(CustomCallRunPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                customCallRunPresenter = new CustomCallRunPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(CustomCallRunPresenter.class).toString(), customCallRunPresenter);
            } else {
                if (!(basePresenterInterface instanceof CustomCallRunPresenter)) {
                    basePresenterInterface = null;
                }
                customCallRunPresenter = (CustomCallRunPresenter) basePresenterInterface;
            }
            if (customCallRunPresenter != null) {
                return customCallRunPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunPresenter");
        }
    });

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$autoResumeCountDownPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AutoResumeCountDownPresenter invoke() {
            AutoResumeCountDownPresenter autoResumeCountDownPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                autoResumeCountDownPresenter = new AutoResumeCountDownPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class).toString(), autoResumeCountDownPresenter);
            } else {
                if (!(basePresenterInterface instanceof AutoResumeCountDownPresenter)) {
                    basePresenterInterface = null;
                }
                autoResumeCountDownPresenter = (AutoResumeCountDownPresenter) basePresenterInterface;
            }
            if (autoResumeCountDownPresenter != null) {
                return autoResumeCountDownPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter");
        }
    });

    private final ConstraintLayout getArriveLayout() {
        return (ConstraintLayout) this.arriveLayout.getValue();
    }

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    private final CustomCallRunPresenter getCustomCallRunPresenter() {
        return (CustomCallRunPresenter) this.customCallRunPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
    }

    private final RelativeLayout getPauseLayout() {
        return (RelativeLayout) this.pauseLayout.getValue();
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

    public WalkFragment() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$motionEventHelper$1$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.target = "";
        this.locationLostVoiceTask = new VoiceTask(-1L, VoiceItem.voice17_1);
        this.movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
        this.arrivalVoiceTask = new VoiceTask(-1L, VoiceItem.voice8_4);
        this.voicePlayerHelper = new VoicePlayerHelper();
        this.runningErrorHelper = new RunningErrorHelper();
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$onErrorDialogShowStatus$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Boolean bool2) {
                invoke(bool.booleanValue(), bool2.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, boolean z2) {
                String str;
                DeliveryContract.DeliveryEvent deliveryEvent;
                int i;
                str = WalkFragment.this.TAG;
                Pdlog.m3273d(str, "onErrorDialogShowStatus " + z);
                if (z) {
                    if (z2) {
                        DeliveryTrack.INSTANCE.onEmergencyStop();
                    }
                    PeripheralsSceneUtil.INSTANCE.showRunError();
                    deliveryEvent = WalkFragment.this.currentState;
                    if (deliveryEvent != DeliveryContract.DeliveryEvent.DONE) {
                        WalkFragment walkFragment = WalkFragment.this;
                        i = walkFragment.STATE_PAUSE;
                        walkFragment.onUIChange(i);
                    }
                }
            }
        };
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$onLostLocationLostCallback$1
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
                String str;
                String str2;
                VoiceTask voiceTask;
                int i;
                LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
                str = WalkFragment.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                    str2 = WalkFragment.this.TAG;
                    Pdlog.m3273d(str2, "onLostLocationLostCallback");
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    voiceTask = WalkFragment.this.locationLostVoiceTask;
                    voicePlayer.play(voiceTask);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    WalkFragment walkFragment = WalkFragment.this;
                    i = walkFragment.STATE_PAUSE;
                    walkFragment.onUIChange(i);
                    ((FaceVideoView) WalkFragment.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    return;
                }
                if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = WalkFragment.this.getOnActionState();
                    if (onActionState != null) {
                        onActionState.invoke(CustomCallState.Cancel, CustomCallOperationType.user);
                    }
                    DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                    Intent intent = new Intent(WalkFragment.this.getContext(), (Class<?>) LaserRunningLocationLostActivity.class);
                    intent.putExtra(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, 1);
                    WalkFragment.this.jump(intent);
                }
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$onErrorClearCallback$1
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
                String str;
                DeliveryContract.DeliveryEvent deliveryEvent;
                int i;
                str = WalkFragment.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                deliveryEvent = WalkFragment.this.currentState;
                if (deliveryEvent == DeliveryContract.DeliveryEvent.DONE || !((FaceVideoView) WalkFragment.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    return;
                }
                WalkFragment.this.pauseTask();
                WalkFragment walkFragment = WalkFragment.this;
                i = walkFragment.STATE_PAUSE;
                walkFragment.onUIChange(i);
            }
        };
        this.arriveLayout = LazyKt.lazy(new WalkFragment$arriveLayout$2(this));
        this.pauseLayout = LazyKt.lazy(new WalkFragment$pauseLayout$2(this));
        this.onFaceViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$onFaceViewClick$1
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
                String str;
                str = WalkFragment.this.TAG;
                Pdlog.m3273d(str, "onFaceViewClick");
                WalkFragment.this.pauseTask();
            }
        }, 3, null);
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$onFallDropCallBack$1
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
                String str;
                WalkFragment.this.cancelTask();
                if (WalkFragment.this.getActivity() != null && (WalkFragment.this.getActivity() instanceof CustomCallActivity)) {
                    FragmentActivity activity = WalkFragment.this.getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity");
                    }
                    ((CustomCallActivity) activity).releaseDataAndFinish();
                }
                str = WalkFragment.this.TAG;
                Pdlog.m3274e(str, "onFallDropCallBack");
            }
        };
        this.STATE_PAUSE = 1;
        this.STATE_ARRIVE = 2;
    }

    private final void hideWalkLayout() {
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showWalkLayout(String target) {
        this.target = target;
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getOnTheWayAround());
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(target);
    }

    private final void setWalkTarget(String target) {
        this.target = target;
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(target);
    }

    private final void hidePauseLayout() {
        getPauseLayout().setVisibility(8);
    }

    private final void showPauseLayout() {
        getPauseLayout().setVisibility(0);
        Context context = getContext();
        if (context != null) {
            TextView tv_title = (TextView) _$_findCachedViewById(C4188R.id.tv_title);
            Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = context.getString(C4188R.string.pdStr2_30);
            Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(R.string.pdStr2_30)");
            Object[] objArr = new Object[1];
            CustomCallTargetBean customCallTargetBean = this.customCallTargetBean;
            objArr[0] = customCallTargetBean != null ? customCallTargetBean.getDestination() : null;
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            tv_title.setText(format);
            TextView textView = (TextView) _$_findCachedViewById(C4188R.id.desc_tv);
            if (textView != null) {
                Context context2 = getContext();
                textView.setText(context2 != null ? context2.getString(C4188R.string.pdStr16_39) : null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCancelBackBtn() {
        LinearLayout custom_call_cancel_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.custom_call_cancel_ll);
        Intrinsics.checkExpressionValueIsNotNull(custom_call_cancel_ll, "custom_call_cancel_ll");
        custom_call_cancel_ll.setVisibility(0);
        LinearLayout custom_call_back_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.custom_call_back_ll);
        Intrinsics.checkExpressionValueIsNotNull(custom_call_back_ll, "custom_call_back_ll");
        custom_call_back_ll.setVisibility(0);
    }

    private final void hideCancelBackBtn() {
        LinearLayout custom_call_cancel_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.custom_call_cancel_ll);
        Intrinsics.checkExpressionValueIsNotNull(custom_call_cancel_ll, "custom_call_cancel_ll");
        custom_call_cancel_ll.setVisibility(8);
        LinearLayout custom_call_back_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.custom_call_back_ll);
        Intrinsics.checkExpressionValueIsNotNull(custom_call_back_ll, "custom_call_back_ll");
        custom_call_back_ll.setVisibility(8);
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        startTask();
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public int getLayoutId() {
        return C4188R.layout.fragment_custom_call_walk;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void initview(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        ((LongDoublePressConstraintLayout) _$_findCachedViewById(C4188R.id.long_double_press_cl)).setOnLongDoublePressListener(new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$initview$1
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
                WalkFragment.this.showCancelBackBtn();
            }
        });
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceViewClick);
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void setData() {
        super.setData();
        bindPresenter();
    }

    private final void startTask() {
        DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@WalkFragment::class.java.simpleName");
        deliveryTrack.onCreateTask(4, simpleName, CallFromType.OPEN_API);
        CustomCallTargetBean customCallTargetBean = this.customCallTargetBean;
        if (customCallTargetBean != null) {
            onUIChange(this.STATE_RUN);
            CustomCallRunContract.PresenterInterface.DefaultImpls.actionInitTask$default(getCustomCallRunPresenter(), CollectionsKt.arrayListOf(customCallTargetBean.getDestination()), null, null, 6, null);
            getCustomCallRunPresenter().actionActive();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeTask() {
        getCustomCallRunPresenter().actionActive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pauseTask() {
        getCustomCallRunPresenter().actionPause();
    }

    private final void pauseNoTimeTask() {
        getCustomCallRunPresenter().actionPauseNoTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelTask() {
        getCustomCallRunPresenter().actionPauseNoTimer();
        getCustomCallRunPresenter().actionCancelAll();
    }

    private final void bindPresenter() {
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, getActivity(), new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$bindPresenter$1
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
                WalkFragment.this.resumeTask();
            }
        });
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        motionEventHelper.bind(null, face_animation_view, new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$bindPresenter$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }
        });
        this.motionEventHelper.setOnTheWayAnimation(SceneAnimationResources.INSTANCE.getOnTheWayAround());
        this.motionEventHelper.setOnVoicePlayListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$bindPresenter$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent) {
                invoke2(playEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PlayEvent it) {
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                Intrinsics.checkParameterIsNotNull(it, "it");
                mDangerousAreaTipHelper = WalkFragment.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        WalkFragment walkFragment = this;
        getAutoResumeCountDownPresenter().replaceView(walkFragment);
        getCustomCallRunPresenter().replaceView(walkFragment);
        if (getMActivity() != null && (getMActivity() instanceof AppCompatActivity)) {
            DangerousAreaTipHelper mDangerousAreaTipHelper = getMDangerousAreaTipHelper();
            Activity mActivity = getMActivity();
            if (mActivity == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            }
            mDangerousAreaTipHelper.bindLifecycle((AppCompatActivity) mActivity);
            return;
        }
        Pdlog.m3273d(this.TAG, "mDangerousAreaTipHelper bind failure");
    }

    private final void unBindPresenter() {
        Disposable disposable = this.cancelWaitTimeoutDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.runningErrorHelper.unbind();
        this.motionEventHelper.unBind();
        WalkFragment walkFragment = this;
        getAutoResumeCountDownPresenter().removeView(walkFragment);
        getCustomCallRunPresenter().removeView(walkFragment);
        MusicPlayerHelper.getInstance().release();
    }

    @Override // com.pudutech.bumblebee.presenter.custom_call_task.CustomCallRunContract.ViewInterface
    public void showOnTheWayState(DeliveryContract.DeliveryEvent state) {
        Function2<CustomCallState, CustomCallOperationType, Unit> onActionState;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3273d(this.TAG, "showOnTheWayState state: " + state);
        switch (state) {
            case ACTIVE:
                DeliveryTrack.INSTANCE.onMove();
                if (this.currentState != state) {
                    onUIChange(this.STATE_RUN);
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState2 = getOnActionState();
                    if (onActionState2 != null) {
                        onActionState2.invoke(CustomCallState.Arriving, CustomCallOperationType.user);
                        break;
                    }
                }
                break;
            case ON_THE_WAY:
                DeliveryTrack.INSTANCE.onMove();
                PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
                break;
            case APPROACHING:
                PeripheralsSceneUtil.showDeliveryArriving$default(PeripheralsSceneUtil.INSTANCE, null, false, 3, null);
                break;
            case PAUSE:
                DeliveryTrack.INSTANCE.onPause();
                if (this.currentState != state) {
                    onUIChange(this.STATE_PAUSE);
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState3 = getOnActionState();
                    if (onActionState3 != null) {
                        onActionState3.invoke(CustomCallState.Pause, CustomCallOperationType.user);
                    }
                }
                PeripheralsSceneUtil.INSTANCE.showDeliveryPause();
                break;
            case ARRIVAL:
                DeliveryTrack.INSTANCE.onArrive(this.target);
                Function2<CustomCallState, CustomCallOperationType, Unit> onActionState4 = getOnActionState();
                if (onActionState4 != null) {
                    onActionState4.invoke(CustomCallState.Arrived, CustomCallOperationType.user);
                }
                PeripheralsSceneUtil.INSTANCE.showDeliveryArrived();
                break;
            case DONE:
                DeliveryTrack.INSTANCE.onFinishOne(this.target, BaseMoveTrackTask.FinishOneCause.Normal);
                PeripheralsSceneUtil.INSTANCE.stopAll();
                break;
            case ALL_DONE:
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                CustomCallTargetBean customCallTargetBean = this.customCallTargetBean;
                if (customCallTargetBean != null && customCallTargetBean.getCallType() == 2 && !this.isUserCancel) {
                    onUIChange(this.STATE_ARRIVE);
                    break;
                } else {
                    CustomCallTargetBean customCallTargetBean2 = this.customCallTargetBean;
                    if (customCallTargetBean2 != null && customCallTargetBean2.getCallType() == 1 && (onActionState = getOnActionState()) != null) {
                        onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                        break;
                    }
                }
                break;
        }
        this.currentState = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        unBindPresenter();
        Function1<Intent, Unit> onFinish = getOnFinish();
        if (onFinish != null) {
            onFinish.invoke(intent);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment
    public void updateCustomCallContent(ICustomCallBean content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.customCallTargetBean = (CustomCallTargetBean) content;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.custom_call.BaseCustomCallFragment
    public void release() {
        unBindPresenter();
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.ViewInterface
    public void showTimeLeft(long time, AutoResumeCountDownContract.Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.desc_tv);
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            Context context = getContext();
            sb.append(context != null ? context.getString(C4188R.string.pdStr16_39) : null);
            sb.append(' ');
            sb.append(time);
            sb.append('S');
            textView.setText(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUIChange(int state) {
        if (state == this.STATE_RUN) {
            CustomCallTargetBean customCallTargetBean = this.customCallTargetBean;
            if (customCallTargetBean != null) {
                showWalkLayout(customCallTargetBean.getDestination());
            }
            hidePauseLayout();
            hideCancelBackBtn();
            this.motionEventHelper.setCanHandleMovingEvent(true);
            Function1<Boolean, Unit> showStateBar = getShowStateBar();
            if (showStateBar != null) {
                showStateBar.invoke(false);
            }
            MusicPlayerHelper.getInstance().startPlay(ModeEnum.DELIVERY_AND_RETURNING);
            return;
        }
        if (state == this.STATE_PAUSE) {
            hideWalkLayout();
            showPauseLayout();
            this.motionEventHelper.setCanHandleMovingEvent(false);
            Function1<Boolean, Unit> showStateBar2 = getShowStateBar();
            if (showStateBar2 != null) {
                showStateBar2.invoke(true);
            }
            MusicPlayerHelper.getInstance().pausePlay();
            return;
        }
        if (state == this.STATE_ARRIVE) {
            hideWalkLayout();
            getArriveLayout().setVisibility(0);
            createCancelTimeout();
            if (this.voicePlayerHelper.checkFileExist(VoiceItem.voice35_1)) {
                VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice35_1));
            } else {
                VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice7_1));
            }
            Pdlog.m3273d(this.TAG, "onUIChange--STATE_ARRIVE");
            this.motionEventHelper.setCanHandleMovingEvent(false);
            Function1<Boolean, Unit> showStateBar3 = getShowStateBar();
            if (showStateBar3 != null) {
                showStateBar3.invoke(true);
            }
            MusicPlayerHelper.getInstance().startPlay(ModeEnum.ARRIVED);
        }
    }

    private final void createCancelTimeout() {
        if (BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch()) {
            long getAutoCompleteCallTime = BeeperUtils.INSTANCE.getGetAutoCompleteCallTime() / 1000;
            this.cancelWaitTimeoutDisposable = CountdownUtil.INSTANCE.createCountDown(getAutoCompleteCallTime).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$createCancelTimeout$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Long l) {
                    String str = WalkFragment.this.getString(C4188R.string.pdStr2_15) + " (" + l + "s)";
                    SpannableString spannableString = new SpannableString(str);
                    Button complete_btn = (Button) WalkFragment.this._$_findCachedViewById(C4188R.id.complete_btn);
                    Intrinsics.checkExpressionValueIsNotNull(complete_btn, "complete_btn");
                    int textSize = (int) ((complete_btn.getTextSize() / 3) * 2);
                    spannableString.setSpan(new AbsoluteSizeSpan(textSize), WalkFragment.this.getString(C4188R.string.pdStr2_15).length(), str.length(), 34);
                    spannableString.setSpan(new VerticalAlignTextSpan(textSize, -3355444), WalkFragment.this.getString(C4188R.string.pdStr2_15).length(), str.length(), 34);
                    Button complete_btn2 = (Button) WalkFragment.this._$_findCachedViewById(C4188R.id.complete_btn);
                    Intrinsics.checkExpressionValueIsNotNull(complete_btn2, "complete_btn");
                    complete_btn2.setText(spannableString);
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$createCancelTimeout$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                }
            }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.WalkFragment$createCancelTimeout$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    String str;
                    Function2<CustomCallState, CustomCallOperationType, Unit> onActionState = WalkFragment.this.getOnActionState();
                    if (onActionState != null) {
                        onActionState.invoke(CustomCallState.Complete, CustomCallOperationType.user);
                    }
                    str = WalkFragment.this.TAG;
                    Pdlog.m3273d(str, "createCancelTimeout--完成");
                }
            });
            Pdlog.m3273d(this.TAG, "createCancelTimeout--时间：" + getAutoCompleteCallTime);
        }
        Pdlog.m3273d(this.TAG, "createCancelTimeout--开关状态" + BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch());
    }
}
