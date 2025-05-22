package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract;
import com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.resources.voice.VoiceItem;
import com.warkiz.widget.SizeUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: TransferDishesActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¥\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001.\u0018\u0000 [2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001[B\u0005¢\u0006\u0002\u0010\u0004J\b\u00106\u001a\u00020\u0018H\u0002J\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u00020\u0018H\u0002J\b\u0010:\u001a\u00020\u0018H\u0002J\u0010\u0010;\u001a\u00020\u00182\u0006\u0010<\u001a\u00020=H\u0016J\u0012\u0010>\u001a\u00020\u00182\b\u0010?\u001a\u0004\u0018\u00010@H\u0014J\b\u0010A\u001a\u00020\u0018H\u0014J\b\u0010B\u001a\u00020\u0018H\u0014J\u0010\u0010C\u001a\u00020\u00182\u0006\u0010D\u001a\u00020\bH\u0002J\b\u0010E\u001a\u00020\u0018H\u0014J\b\u0010F\u001a\u00020\u0018H\u0014J\b\u0010G\u001a\u00020\u0018H\u0014J\u0010\u0010H\u001a\u00020\u00182\u0006\u0010I\u001a\u00020\u0014H\u0016J\b\u0010J\u001a\u00020\u0018H\u0002J\b\u0010K\u001a\u00020\u0018H\u0002J\u0010\u0010L\u001a\u00020\u00182\u0006\u0010M\u001a\u00020\bH\u0002J\b\u0010N\u001a\u00020\u0018H\u0002J\b\u0010O\u001a\u00020\u0018H\u0002J\b\u0010P\u001a\u00020\u0018H\u0002J\b\u0010Q\u001a\u00020\u0018H\u0002J\u0018\u0010R\u001a\u00020\u00182\u0006\u0010M\u001a\u00020S2\u0006\u0010T\u001a\u00020UH\u0016J \u0010V\u001a\u00020\u00182\u0006\u0010D\u001a\u0002082\u0006\u0010#\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u0012H\u0016J\u0010\u0010X\u001a\u00020\u00182\u0006\u0010Y\u001a\u00020\bH\u0002J\b\u0010Z\u001a\u00020\u0018H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010 \u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u0018\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/R\u000e\u00100\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u0010\u001a\u0004\b3\u00104¨\u0006\\"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/TransferDishesActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$ViewInterface;", "()V", "TAG", "", "TYPE_PAUSE_FEATURE_DIALOG", "", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$TransferEvent;", "isComeFromRecyclePlate", "", "isFirstStart", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "pauseVoiceTask", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/TransferDishesActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/TransferDishesActivity$singleBatteryListener$1;", "targetType", "transferDishesPresenter", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesPresenter;", "getTransferDishesPresenter", "()Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesPresenter;", "transferDishesPresenter$delegate", "bindPresenter", "getTarget", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$DestinationType;", "hideCountdownLayout", "initView", "jumpAndFinish", "i", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPauseFeatureChange", "type", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "releaseDataAndFinish", "setCountdown", "time", "showCountdownLayout", "showOnArriveStatus", "showOnPauseStatus", "showOnTheWayStatus", "showTimeLeft", "", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "showTransferEvent", "event", "startDeliverTaskEditActivity", "mode", "unBindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TransferDishesActivity extends MyBaseActivity implements AutoResumeCountDownContract.ViewInterface, TransferDishesContract.ViewInterface {
    public static final String COME_FROM_RECYCLE_PLATE = "COME_FROM_RECYCLE_PLATE";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_TARGET_TYPE = "target_type";
    public static final int TARGET_TYPE_RECYCLE_TABLE = 0;
    public static final int TARGET_TYPE_WASHING_ROOM = 1;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private HashMap _$_findViewCache;
    private boolean isComeFromRecyclePlate;
    private int targetType;
    private final String TAG = "TransferDishesActivity";

    /* renamed from: transferDishesPresenter$delegate, reason: from kotlin metadata */
    private final Lazy transferDishesPresenter = LazyKt.lazy(new Function0<TransferDishesPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$transferDishesPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TransferDishesPresenter invoke() {
            TransferDishesPresenter transferDishesPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(TransferDishesPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(TransferDishesPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                transferDishesPresenter = new TransferDishesPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(TransferDishesPresenter.class).toString(), transferDishesPresenter);
            } else {
                if (!(basePresenterInterface instanceof TransferDishesPresenter)) {
                    basePresenterInterface = null;
                }
                transferDishesPresenter = (TransferDishesPresenter) basePresenterInterface;
            }
            if (transferDishesPresenter != null) {
                return transferDishesPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter");
        }
    });
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();
    private final MotionEventHelper motionEventHelper = new MotionEventHelper();

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$autoResumeCountDownPresenter$2
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
    private VoiceTask locationLostVoiceTask = new VoiceTask(-1, VoiceItem.voice17_1);
    private final VoiceTask pauseVoiceTask = new VoiceTask(-1, VoiceItem.voice7_2);
    private TransferDishesContract.TransferEvent currentEventStatus = TransferDishesContract.TransferEvent.ON_THE_WAY;
    private boolean isFirstStart = true;
    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$onErrorDialogShowStatus$1
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
            int i;
            if (z) {
                TransferDishesActivity transferDishesActivity = TransferDishesActivity.this;
                i = transferDishesActivity.TYPE_PAUSE_FEATURE_ERROR;
                transferDishesActivity.onPauseFeatureChange(i);
                PeripheralsSceneUtil.INSTANCE.showRunError();
            }
        }
    };
    private Function0<Unit> onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$onFallDropCallBack$1
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
            TransferDishesActivity.this.releaseDataAndFinish();
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$onLostLocationLostCallback$1
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
            TransferDishesPresenter transferDishesPresenter;
            LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
            str = TransferDishesActivity.this.TAG;
            Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                str2 = TransferDishesActivity.this.TAG;
                Pdlog.m3273d(str2, "onLostLocationLostCallback");
                ((FaceVideoView) TransferDishesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                voiceTask = TransferDishesActivity.this.locationLostVoiceTask;
                voicePlayer.play(voiceTask);
                PeripheralsSceneUtil.INSTANCE.lostLocation();
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionCancelTask();
                TransferDishesActivity transferDishesActivity = TransferDishesActivity.this;
                transferDishesActivity.jumpAndFinish(new Intent(transferDishesActivity, (Class<?>) LaserRunningLocationLostActivity.class));
            }
        }
    };
    private Function0<Unit> onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$onErrorClearCallback$1
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
            TransferDishesPresenter transferDishesPresenter;
            str = TransferDishesActivity.this.TAG;
            Pdlog.m3273d(str, "onErrorClearCallback");
            if (((FaceVideoView) TransferDishesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                ((FaceVideoView) TransferDishesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionPause();
            }
        }
    };
    private final Function0<Unit> locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$locationLostTouchCancelCallback$1
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
            int i;
            str = TransferDishesActivity.this.TAG;
            Pdlog.m3273d(str, "locationLostTouchCancelCallback");
            ((FaceVideoView) TransferDishesActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
            TransferDishesActivity transferDishesActivity = TransferDishesActivity.this;
            i = transferDishesActivity.TYPE_PAUSE_FEATURE_ERROR;
            transferDishesActivity.onPauseFeatureChange(i);
        }
    };
    private final SingleClickListener onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$onFaceAnimationViewClick$1
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
            TransferDishesContract.TransferEvent transferEvent;
            TransferDishesContract.TransferEvent transferEvent2;
            TransferDishesPresenter transferDishesPresenter;
            TransferDishesContract.TransferEvent transferEvent3;
            str = TransferDishesActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onFaceAnimationViewClick current status = ");
            transferEvent = TransferDishesActivity.this.currentEventStatus;
            sb.append(transferEvent);
            Pdlog.m3273d(str, sb.toString());
            transferEvent2 = TransferDishesActivity.this.currentEventStatus;
            if (transferEvent2 != TransferDishesContract.TransferEvent.ON_THE_WAY) {
                transferEvent3 = TransferDishesActivity.this.currentEventStatus;
                if (transferEvent3 != TransferDishesContract.TransferEvent.ACTIVE) {
                    return;
                }
            }
            transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
            transferDishesPresenter.actionPause();
        }
    }, 3, null);
    private final int TYPE_PAUSE_FEATURE_DIALOG = 1;
    private final int TYPE_PAUSE_FEATURE_ERROR = 4;
    private final TransferDishesActivity$singleBatteryListener$1 singleBatteryListener = new TransferDishesActivity$singleBatteryListener$1(this);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TransferDishesContract.TransferEvent.values().length];

        static {
            $EnumSwitchMapping$0[TransferDishesContract.TransferEvent.NO_DESTINATION.ordinal()] = 1;
            $EnumSwitchMapping$0[TransferDishesContract.TransferEvent.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$0[TransferDishesContract.TransferEvent.ACTIVE.ordinal()] = 3;
            $EnumSwitchMapping$0[TransferDishesContract.TransferEvent.PAUSE.ordinal()] = 4;
            $EnumSwitchMapping$0[TransferDishesContract.TransferEvent.ARRIVAL.ordinal()] = 5;
        }
    }

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TransferDishesPresenter getTransferDishesPresenter() {
        return (TransferDishesPresenter) this.transferDishesPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    /* compiled from: TransferDishesActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/TransferDishesActivity$Companion;", "", "()V", "COME_FROM_RECYCLE_PLATE", "", "KEY_TARGET_TYPE", "TARGET_TYPE_RECYCLE_TABLE", "", "TARGET_TYPE_WASHING_ROOM", "getGoRecycleTableIntent", "Landroid/content/Intent;", "ac", "Landroidx/appcompat/app/AppCompatActivity;", "getGoWashRoomIntent", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent getGoWashRoomIntent(AppCompatActivity ac) {
            Intrinsics.checkParameterIsNotNull(ac, "ac");
            Intent intent = new Intent(ac, (Class<?>) TransferDishesActivity.class);
            intent.putExtra(TransferDishesActivity.KEY_TARGET_TYPE, 1);
            return intent;
        }

        public final Intent getGoRecycleTableIntent(AppCompatActivity ac) {
            Intrinsics.checkParameterIsNotNull(ac, "ac");
            Intent intent = new Intent(ac, (Class<?>) TransferDishesActivity.class);
            intent.putExtra(TransferDishesActivity.KEY_TARGET_TYPE, 0);
            return intent;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_transfer_dishes);
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
        this.targetType = getIntent().getIntExtra(KEY_TARGET_TYPE, 0);
        this.isComeFromRecyclePlate = getIntent().getBooleanExtra("COME_FROM_RECYCLE_PLATE", false);
        Pdlog.m3273d(this.TAG, "onCreate targetType = " + this.targetType);
        initView();
        bindPresenter();
        showOnTheWayStatus();
        getTransferDishesPresenter().actionGo(getTarget());
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    private final void initView() {
        String string;
        String string2;
        ((LinearLayout) _$_findCachedViewById(C4188R.id.cancel_ll)).setOnClickListener(new TransferDishesActivity$initView$1(this));
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.on_the_way_layout)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$initView$2
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                VoiceTask voiceTask;
                TransferDishesPresenter transferDishesPresenter;
                str = TransferDishesActivity.this.TAG;
                Pdlog.m3273d(str, "on_the_way_layout OnClickListener");
                VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                voiceTask = TransferDishesActivity.this.pauseVoiceTask;
                voicePlayer.play(voiceTask);
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionPause();
            }
        });
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$initView$3
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                TransferDishesPresenter transferDishesPresenter;
                str = TransferDishesActivity.this.TAG;
                Pdlog.m3273d(str, "pause_layout OnClickListener");
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionActive();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.continue_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                TransferDishesPresenter transferDishesPresenter;
                str = TransferDishesActivity.this.TAG;
                Pdlog.m3273d(str, "continue_tv OnClickListener");
                TransferDishesActivity.this.startDeliverTaskEditActivity(6);
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionFinish();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.turn_back_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                TransferDishesPresenter transferDishesPresenter;
                str = TransferDishesActivity.this.TAG;
                Pdlog.m3273d(str, "turn_back_tv OnClickListener");
                Intent intent = new Intent(TransferDishesActivity.this, (Class<?>) TurnBackActivity.class);
                intent.putExtra("COME_FROM_TRANSFER_DISHES", true);
                TransferDishesActivity.this.jumpAndFinish(intent);
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionFinish();
            }
        });
        setCountdown((int) (BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000));
        TextView target_name_tv = (TextView) _$_findCachedViewById(C4188R.id.target_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(target_name_tv, "target_name_tv");
        if (this.targetType == 0) {
            string = getString(C4188R.string.pdStr16_24);
        } else {
            string = getString(C4188R.string.pdStr16_22);
        }
        target_name_tv.setText(string);
        TextView on_the_way_title_tv = (TextView) _$_findCachedViewById(C4188R.id.on_the_way_title_tv);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_title_tv, "on_the_way_title_tv");
        if (this.targetType == 0) {
            string2 = getString(C4188R.string.pdStr16_21);
        } else {
            string2 = getString(C4188R.string.pdStr16_25);
        }
        on_the_way_title_tv.setText(string2);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
    }

    private final TransferDishesContract.DestinationType getTarget() {
        int i = this.targetType;
        if (i == 0) {
            return TransferDishesContract.DestinationType.TRANSFER_STATION;
        }
        if (i == 1) {
            return TransferDishesContract.DestinationType.DISHWASHING_ROOM;
        }
        return TransferDishesContract.DestinationType.TRANSFER_STATION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDeliverTaskEditActivity(int mode) {
        Pdlog.m3273d(this.TAG, "startDeliverTaskEditActivity " + mode);
        Intent intent = new Intent(this, (Class<?>) DeliverTaskEditActivity.class);
        intent.putExtra("MODE_TYPE", mode);
        jumpAndFinish(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        TransferDishesPresenter transferDishesPresenter = getTransferDishesPresenter();
        if (transferDishesPresenter != null) {
            transferDishesPresenter.actionPauseNoTimer();
        }
        TransferDishesPresenter transferDishesPresenter2 = getTransferDishesPresenter();
        if (transferDishesPresenter2 != null) {
            transferDishesPresenter2.actionCancelTask();
        }
        release();
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(i);
        finish();
    }

    private final void release() {
        Pdlog.m3273d(this.TAG, "release");
        unBindPresenter();
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.ViewInterface
    public void showTransferEvent(TransferDishesContract.DestinationType type, String name, TransferDishesContract.TransferEvent event) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showTransferEvent " + type + " , " + name + " , " + event);
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            Intent intent = new Intent(this, (Class<?>) DeliverTaskEditActivity.class);
            intent.putExtra("SHOW_STATUS_KEY", this.targetType != 0 ? 3 : 4);
            intent.putExtra("MODE_TYPE", 6);
            jumpAndFinish(intent);
        } else if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    if (this.currentEventStatus != event) {
                        showOnPauseStatus();
                    }
                } else if (i == 5 && this.currentEventStatus != event) {
                    showOnArriveStatus();
                }
            } else if (this.currentEventStatus != event) {
                showOnTheWayStatus();
            }
        } else if (this.currentEventStatus != event) {
            showOnTheWayStatus();
        }
        this.currentEventStatus = event;
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, this, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$bindPresenter$1
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
                TransferDishesPresenter transferDishesPresenter;
                transferDishesPresenter = TransferDishesActivity.this.getTransferDishesPresenter();
                transferDishesPresenter.actionActive();
            }
        });
        TransferDishesActivity transferDishesActivity = this;
        getAutoResumeCountDownPresenter().replaceView(transferDishesActivity);
        getTransferDishesPresenter().replaceView(transferDishesActivity);
        this.motionEventHelper.setOnTheWayAnimation((FaceVideoAnimation) null);
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        motionEventHelper.bind(voiceTaskWrapper, face_animation_view, new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.TransferDishesActivity$bindPresenter$2
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
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        TransferDishesActivity transferDishesActivity = this;
        getAutoResumeCountDownPresenter().removeView(transferDishesActivity);
        getTransferDishesPresenter().removeView(transferDishesActivity);
        this.motionEventHelper.unBind();
    }

    private final void showOnPauseStatus() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        showCountdownLayout();
        RelativeLayout on_the_way_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(8);
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(8);
        RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(0);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        this.motionEventHelper.setCanHandleMovingEvent(false);
        PeripheralsSceneUtil.INSTANCE.showTurnBackPause();
    }

    private final void showOnTheWayStatus() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        RelativeLayout on_the_way_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(0);
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(8);
        RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        this.motionEventHelper.setCanHandleMovingEvent(true);
        PeripheralsSceneUtil.INSTANCE.showTurnBackOnTheWay();
        if (this.isComeFromRecyclePlate) {
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice34_5));
        }
        this.isComeFromRecyclePlate = false;
    }

    private final void showOnArriveStatus() {
        Pdlog.m3273d(this.TAG, "showOnArriveStatus");
        RelativeLayout on_the_way_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.on_the_way_layout);
        Intrinsics.checkExpressionValueIsNotNull(on_the_way_layout, "on_the_way_layout");
        on_the_way_layout.setVisibility(8);
        ConstraintLayout arrive_layout = (ConstraintLayout) _$_findCachedViewById(C4188R.id.arrive_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_layout, "arrive_layout");
        arrive_layout.setVisibility(0);
        RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
        Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
        pause_layout.setVisibility(8);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        this.motionEventHelper.setCanHandleMovingEvent(false);
        VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice34_1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            getTransferDishesPresenter().actionPause();
        } else if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            getTransferDishesPresenter().actionPauseNoTimer();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            hideCountdownLayout();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.ViewInterface
    public void showTimeLeft(long time, AutoResumeCountDownContract.Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Pdlog.m3273d(this.TAG, "showTimeLeft " + time);
        setCountdown((int) time);
    }

    private final void setCountdown(int time) {
        try {
            int sp2px = SizeUtils.sp2px(this, 22.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr16_9);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr16_9)");
            Object[] objArr = {Integer.valueOf(time)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            int indexOf$default = StringsKt.indexOf$default((CharSequence) format, String.valueOf(time), 0, false, 6, (Object) null);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, sp2px, ColorStateList.valueOf(getColor(C4188R.color.theme_main_color)), null);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(textAppearanceSpan, indexOf$default, String.valueOf(time).length() + indexOf$default, 34);
            showCountdownLayout();
            TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            countdown_tv.setText(spannableStringBuilder);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    private final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(4);
    }

    private final void showCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        if (countdown_tv.getVisibility() != 0) {
            TextView countdown_tv2 = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
            countdown_tv2.setVisibility(0);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
