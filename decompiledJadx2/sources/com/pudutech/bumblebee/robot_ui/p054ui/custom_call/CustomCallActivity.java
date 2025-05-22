package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.pudu.demo.video.VideoPreLoader;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallImgBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallQrcodeBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallVideoBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.TurnBackActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FullLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CallHistoryManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.bumblebee.robot_ui.util.FragmentUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomCallActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 I2\u00020\u00012\u00020\u0002:\u0001IB\u0005¢\u0006\u0002\u0010\u0003J\b\u00100\u001a\u00020\u001aH\u0002J\b\u00101\u001a\u00020\u001aH\u0002J\b\u00102\u001a\u00020\u001aH\u0002J\b\u00103\u001a\u00020\u001aH\u0002J\u0010\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u00020'H\u0016J\u0012\u00106\u001a\u00020\u001a2\b\u00107\u001a\u0004\u0018\u000108H\u0014J\b\u00109\u001a\u00020\u001aH\u0002J\u0006\u0010:\u001a\u00020\u001aJ\u0010\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u0010H\u0002J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u0007H\u0002J\b\u0010?\u001a\u00020\u001aH\u0002J\u0010\u0010@\u001a\u00020\u001a2\u0006\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020\u001aH\u0016J\u0010\u0010D\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020\u0007H\u0016J\u0010\u0010F\u001a\u00020\u001a2\u0006\u0010A\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001a0 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u001a0 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001a0 X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010&\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010'\u0012\u0004\u0012\u00020\u001a\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R(\u0010,\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u001a\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+¨\u0006J"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/CustomCallActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "TAG", "", "TYPE_IMG", "", "TYPE_QRCODE", "TYPE_VIDEO", "TYPE_WALK", "cancelWaitDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FullLoadDialog;", "cancelWaitTimeoutDisposable", "Lio/reactivex/disposables/Disposable;", "currentFragment", "Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/BaseCustomCallFragment;", "currentTask", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "mVideoLoad", "Lcom/pudu/demo/video/VideoPreLoader;", "nextTask", "onActionState", "Lkotlin/Function2;", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "", "getOnActionState", "()Lkotlin/jvm/functions/Function2;", "setOnActionState", "(Lkotlin/jvm/functions/Function2;)V", "onCallListener", "Lkotlin/Function1;", "onCancelListener", "Lkotlin/Function0;", "onCompleteListener", "onContentListener", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "onFinish", "Landroid/content/Intent;", "getOnFinish", "()Lkotlin/jvm/functions/Function1;", "setOnFinish", "(Lkotlin/jvm/functions/Function1;)V", "showStateBar", "", "getShowStateBar", "setShowStateBar", "bindPresenter", "createCancelTimeout", "hideCancelWaitDialog", "initView", "jumpAndFinish", "intent", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "release", "releaseDataAndFinish", "replaceFragment", "fragment", "selectFragment", "fragmentType", "showCancelWaitDialog", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "unBindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomCallActivity extends MyBaseActivity implements BatteryContract2.ViewInterface {
    public static final long CANCEL_WAIT_TIMEOUT = 5;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_CUSTOM_CALL_TARGET = "EXTRA_CUSTOM_CALL_TARGET";
    private final int TYPE_WALK;
    private HashMap _$_findViewCache;
    private FullLoadDialog cancelWaitDialog;
    private Disposable cancelWaitTimeoutDisposable;
    private BaseCustomCallFragment currentFragment;
    private CustomCallTargetBean currentTask;
    private VideoPreLoader mVideoLoad;
    private CustomCallTargetBean nextTask;
    private final String TAG = "CustomBeeperActivity";
    private Function1<? super Intent, Unit> onFinish = new Function1<Intent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$onFinish$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Intent intent) {
            if (intent != null) {
                CustomCallActivity.this.jumpAndFinish(intent);
            }
        }
    };
    private Function2<? super CustomCallState, ? super CustomCallOperationType, Unit> onActionState = new Function2<CustomCallState, CustomCallOperationType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$onActionState$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(CustomCallState customCallState, CustomCallOperationType customCallOperationType) {
            invoke2(customCallState, customCallOperationType);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CustomCallState state, CustomCallOperationType type) {
            String str;
            CustomCallTargetBean customCallTargetBean;
            String str2;
            CustomCallTargetBean customCallTargetBean2;
            CustomCallTargetBean customCallTargetBean3;
            CustomCallTargetBean customCallTargetBean4;
            CustomCallTargetBean customCallTargetBean5;
            CustomCallTargetBean customCallTargetBean6;
            int i;
            String destination;
            CustomCallTargetBean customCallTargetBean7;
            CustomCallTargetBean customCallTargetBean8;
            int i2;
            int i3;
            int i4;
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(type, "type");
            str = CustomCallActivity.this.TAG;
            Pdlog.m3273d(str, "onActionState state: " + state + ",type: " + type);
            CustomCallHelper.INSTANCE.notificationCustomCall(state, type);
            if (state == CustomCallState.Arrived) {
                customCallTargetBean7 = CustomCallActivity.this.currentTask;
                if (customCallTargetBean7 == null || customCallTargetBean7.getCallType() != 0) {
                    return;
                }
                customCallTargetBean8 = CustomCallActivity.this.currentTask;
                ICustomCallBean content = customCallTargetBean8 != null ? customCallTargetBean8.getContent() : null;
                if (content instanceof CustomCallImgBean) {
                    CustomCallActivity customCallActivity = CustomCallActivity.this;
                    i4 = customCallActivity.TYPE_IMG;
                    customCallActivity.selectFragment(i4);
                    return;
                } else if (content instanceof CustomCallQrcodeBean) {
                    CustomCallActivity customCallActivity2 = CustomCallActivity.this;
                    i3 = customCallActivity2.TYPE_QRCODE;
                    customCallActivity2.selectFragment(i3);
                    return;
                } else {
                    if (content instanceof CustomCallVideoBean) {
                        CustomCallActivity customCallActivity3 = CustomCallActivity.this;
                        i2 = customCallActivity3.TYPE_VIDEO;
                        customCallActivity3.selectFragment(i2);
                        return;
                    }
                    return;
                }
            }
            if (state == CustomCallState.Complete) {
                customCallTargetBean = CustomCallActivity.this.currentTask;
                int callType = customCallTargetBean != null ? customCallTargetBean.getCallType() : 2;
                str2 = CustomCallActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onActionState callType:");
                sb.append(callType);
                sb.append(",currentTask:");
                customCallTargetBean2 = CustomCallActivity.this.currentTask;
                sb.append(customCallTargetBean2);
                sb.append(",nextTask:");
                customCallTargetBean3 = CustomCallActivity.this.nextTask;
                sb.append(customCallTargetBean3);
                Pdlog.m3273d(str2, sb.toString());
                customCallTargetBean4 = CustomCallActivity.this.currentTask;
                if (customCallTargetBean4 != null && (destination = customCallTargetBean4.getDestination()) != null) {
                    CallHistoryManager.INSTANCE.addHistory(destination);
                }
                CustomCallActivity customCallActivity4 = CustomCallActivity.this;
                customCallTargetBean5 = customCallActivity4.nextTask;
                customCallActivity4.currentTask = customCallTargetBean5;
                CustomCallActivity.this.nextTask = (CustomCallTargetBean) null;
                customCallTargetBean6 = CustomCallActivity.this.currentTask;
                if (customCallTargetBean6 != null) {
                    CustomCallActivity customCallActivity5 = CustomCallActivity.this;
                    i = customCallActivity5.TYPE_WALK;
                    customCallActivity5.selectFragment(i);
                    return;
                }
                Intent intent = new Intent(CustomCallActivity.this, (Class<?>) TurnBackActivity.class);
                if (callType == 1) {
                    intent = new Intent(CustomCallActivity.this, (Class<?>) DeliverTaskEditActivity.class);
                    intent.putExtra("MODE_TYPE", 0);
                } else {
                    Intent putExtra = intent.putExtra("SHOW_THANKS", true);
                    Intrinsics.checkExpressionValueIsNotNull(putExtra, "intent.putExtra(\n       …rue\n                    )");
                    IntentExtKt.saveSceneId$default(putExtra, TrackType.CALL_DIRECT, false, 2, null);
                }
                CustomCallActivity.this.jumpAndFinish(intent);
            }
        }
    };
    private Function1<? super Boolean, Unit> showStateBar = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$showStateBar$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            MyStatusBarLayout layout_my_status_bar = (MyStatusBarLayout) CustomCallActivity.this._$_findCachedViewById(C4188R.id.layout_my_status_bar);
            Intrinsics.checkExpressionValueIsNotNull(layout_my_status_bar, "layout_my_status_bar");
            layout_my_status_bar.setVisibility(z ? 0 : 8);
        }
    };
    private Function1<? super CustomCallTargetBean, Unit> onCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$onCallListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CustomCallTargetBean customCallTargetBean) {
            invoke2(customCallTargetBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CustomCallTargetBean it) {
            String str;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = CustomCallActivity.this.TAG;
            Pdlog.m3273d(str, "onCallListener task: " + it);
        }
    };
    private Function0<Unit> onCancelListener = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$onCancelListener$1
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
            BaseCustomCallFragment baseCustomCallFragment;
            BaseCustomCallFragment baseCustomCallFragment2;
            BaseCustomCallFragment baseCustomCallFragment3;
            str = CustomCallActivity.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onCancelListener currentFragment: ");
            baseCustomCallFragment = CustomCallActivity.this.currentFragment;
            sb.append(baseCustomCallFragment);
            Pdlog.m3273d(str, sb.toString());
            baseCustomCallFragment2 = CustomCallActivity.this.currentFragment;
            if (baseCustomCallFragment2 != null && (baseCustomCallFragment2 instanceof VideoPlayFragment)) {
                baseCustomCallFragment3 = CustomCallActivity.this.currentFragment;
                if (baseCustomCallFragment3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.custom_call.VideoPlayFragment");
                }
                ((VideoPlayFragment) baseCustomCallFragment3).videoViewPause();
            }
            CustomCallActivity.this.showCancelWaitDialog();
        }
    };
    private Function1<? super ICustomCallBean, Unit> onContentListener = new Function1<ICustomCallBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$onContentListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ICustomCallBean iCustomCallBean) {
            invoke2(iCustomCallBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ICustomCallBean it) {
            String str;
            CustomCallTargetBean customCallTargetBean;
            int i;
            int i2;
            int i3;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = CustomCallActivity.this.TAG;
            Pdlog.m3273d(str, "onContentListener content: " + it);
            customCallTargetBean = CustomCallActivity.this.currentTask;
            if (customCallTargetBean != null) {
                customCallTargetBean.setContent(it);
            }
            if (it instanceof CustomCallImgBean) {
                CustomCallActivity customCallActivity = CustomCallActivity.this;
                i3 = customCallActivity.TYPE_IMG;
                customCallActivity.selectFragment(i3);
            } else if (it instanceof CustomCallQrcodeBean) {
                CustomCallActivity customCallActivity2 = CustomCallActivity.this;
                i2 = customCallActivity2.TYPE_QRCODE;
                customCallActivity2.selectFragment(i2);
            } else if (it instanceof CustomCallVideoBean) {
                CustomCallActivity customCallActivity3 = CustomCallActivity.this;
                i = customCallActivity3.TYPE_VIDEO;
                customCallActivity3.selectFragment(i);
            }
        }
    };
    private Function1<? super CustomCallTargetBean, Unit> onCompleteListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$onCompleteListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CustomCallTargetBean customCallTargetBean) {
            invoke2(customCallTargetBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CustomCallTargetBean customCallTargetBean) {
            String str;
            BaseCustomCallFragment baseCustomCallFragment;
            str = CustomCallActivity.this.TAG;
            Pdlog.m3273d(str, "onCompleteListener task:" + customCallTargetBean + ' ');
            CustomCallActivity.this.nextTask = customCallTargetBean;
            baseCustomCallFragment = CustomCallActivity.this.currentFragment;
            if (baseCustomCallFragment != null) {
                baseCustomCallFragment.completeCustomCall();
            }
        }
    };
    private final int TYPE_QRCODE = 1;
    private final int TYPE_IMG = 2;
    private final int TYPE_VIDEO = 3;

    private final void initView() {
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

    /* compiled from: CustomCallActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/custom_call/CustomCallActivity$Companion;", "", "()V", "CANCEL_WAIT_TIMEOUT", "", CustomCallActivity.EXTRA_CUSTOM_CALL_TARGET, "", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "task", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent createIntent(Context context, CustomCallTargetBean task) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(task, "task");
            Intent intent = new Intent(context, (Class<?>) CustomCallActivity.class);
            intent.putExtra(CustomCallActivity.EXTRA_CUSTOM_CALL_TARGET, task);
            return intent;
        }
    }

    public final Function1<Intent, Unit> getOnFinish() {
        return this.onFinish;
    }

    public final void setOnFinish(Function1<? super Intent, Unit> function1) {
        this.onFinish = function1;
    }

    public final Function2<CustomCallState, CustomCallOperationType, Unit> getOnActionState() {
        return this.onActionState;
    }

    public final void setOnActionState(Function2<? super CustomCallState, ? super CustomCallOperationType, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onActionState = function2;
    }

    public final Function1<Boolean, Unit> getShowStateBar() {
        return this.showStateBar;
    }

    public final void setShowStateBar(Function1<? super Boolean, Unit> function1) {
        this.showStateBar = function1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_custom_call);
        this.currentTask = (CustomCallTargetBean) getIntent().getParcelableExtra(EXTRA_CUSTOM_CALL_TARGET);
        initView();
        selectFragment(this.TYPE_WALK);
        bindPresenter();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectFragment(int fragmentType) {
        ICustomCallBean content;
        BaseCustomCallFragment baseCustomCallFragment;
        ICustomCallBean content2;
        BaseCustomCallFragment baseCustomCallFragment2;
        ICustomCallBean content3;
        BaseCustomCallFragment baseCustomCallFragment3;
        BaseCustomCallFragment baseCustomCallFragment4;
        BaseCustomCallFragment baseCustomCallFragment5 = this.currentFragment;
        if (baseCustomCallFragment5 != null) {
            baseCustomCallFragment5.setOnActionState((Function2) null);
        }
        BaseCustomCallFragment baseCustomCallFragment6 = this.currentFragment;
        if (baseCustomCallFragment6 != null) {
            baseCustomCallFragment6.setOnFinish((Function1) null);
        }
        BaseCustomCallFragment baseCustomCallFragment7 = this.currentFragment;
        if (baseCustomCallFragment7 != null) {
            baseCustomCallFragment7.setShowStateBar((Function1) null);
        }
        BaseCustomCallFragment baseCustomCallFragment8 = this.currentFragment;
        if (baseCustomCallFragment8 != null) {
            baseCustomCallFragment8.release();
        }
        if (fragmentType == this.TYPE_WALK) {
            this.currentFragment = new WalkFragment();
            CustomCallTargetBean customCallTargetBean = this.currentTask;
            if (customCallTargetBean != null && (baseCustomCallFragment4 = this.currentFragment) != null) {
                baseCustomCallFragment4.updateCustomCallContent(customCallTargetBean);
            }
            try {
                CustomCallTargetBean customCallTargetBean2 = this.currentTask;
                if (customCallTargetBean2 != null) {
                    if (customCallTargetBean2.getContent() instanceof CustomCallVideoBean) {
                        Context applicationContext = getApplicationContext();
                        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "this.applicationContext");
                        ICustomCallBean content4 = customCallTargetBean2.getContent();
                        if (content4 != null) {
                            this.mVideoLoad = new VideoPreLoader(applicationContext, ((CustomCallVideoBean) content4).getUrls());
                            VideoPreLoader videoPreLoader = this.mVideoLoad;
                            if (videoPreLoader == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mVideoLoad");
                            }
                            videoPreLoader.reqPreLoader();
                            Pdlog.m3274e(this.TAG, "selectFragment--开始预加载 ");
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallVideoBean");
                        }
                    } else {
                        Pdlog.m3274e(this.TAG, "selectFragment--类型转换出错 :" + customCallTargetBean2.getContent());
                    }
                }
            } catch (Exception unused) {
                Pdlog.m3274e(this.TAG, "selectFragment--TYPE_WALK 视频预加载出错");
            }
        } else if (fragmentType == this.TYPE_QRCODE) {
            this.currentFragment = new PayResultFragment();
            CustomCallTargetBean customCallTargetBean3 = this.currentTask;
            if (customCallTargetBean3 != null && (content3 = customCallTargetBean3.getContent()) != null && (baseCustomCallFragment3 = this.currentFragment) != null) {
                baseCustomCallFragment3.updateCustomCallContent(content3);
            }
        } else if (fragmentType == this.TYPE_IMG) {
            this.currentFragment = new BannerFrament();
            CustomCallTargetBean customCallTargetBean4 = this.currentTask;
            if (customCallTargetBean4 != null && (content2 = customCallTargetBean4.getContent()) != null && (baseCustomCallFragment2 = this.currentFragment) != null) {
                baseCustomCallFragment2.updateCustomCallContent(content2);
            }
        } else if (fragmentType == this.TYPE_VIDEO) {
            this.currentFragment = new VideoPlayFragment();
            CustomCallTargetBean customCallTargetBean5 = this.currentTask;
            if (customCallTargetBean5 != null && (content = customCallTargetBean5.getContent()) != null && (baseCustomCallFragment = this.currentFragment) != null) {
                baseCustomCallFragment.updateCustomCallContent(content);
            }
            try {
                VideoPreLoader videoPreLoader2 = this.mVideoLoad;
                if (videoPreLoader2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mVideoLoad");
                }
                if (videoPreLoader2 != null) {
                    videoPreLoader2.setCancel(true);
                }
                Pdlog.m3274e(this.TAG, "selectFragment--setCancel");
            } catch (Exception unused2) {
                Pdlog.m3274e(this.TAG, "selectFragment--setCancel 视频取消错误");
            }
        }
        BaseCustomCallFragment baseCustomCallFragment9 = this.currentFragment;
        if (baseCustomCallFragment9 != null) {
            baseCustomCallFragment9.setOnActionState(this.onActionState);
        }
        BaseCustomCallFragment baseCustomCallFragment10 = this.currentFragment;
        if (baseCustomCallFragment10 != null) {
            baseCustomCallFragment10.setOnFinish(this.onFinish);
        }
        BaseCustomCallFragment baseCustomCallFragment11 = this.currentFragment;
        if (baseCustomCallFragment11 != null) {
            baseCustomCallFragment11.setShowStateBar(this.showStateBar);
        }
        BaseCustomCallFragment baseCustomCallFragment12 = this.currentFragment;
        if (baseCustomCallFragment12 != null) {
            replaceFragment(baseCustomCallFragment12);
        }
    }

    private final void replaceFragment(BaseCustomCallFragment fragment) {
        FragmentUtils.replace(getSupportFragmentManager(), fragment, C4188R.id.custom_call_fragment_container);
    }

    private final void bindPresenter() {
        CustomCallHelper.INSTANCE.addCallListener(this.onCallListener);
        CustomCallHelper.INSTANCE.addCancelListener(this.onCancelListener);
        CustomCallHelper.INSTANCE.addContentListener(this.onContentListener);
        CustomCallHelper.INSTANCE.addCompleteListener(this.onCompleteListener);
    }

    private final void unBindPresenter() {
        CustomCallHelper.INSTANCE.removeCallListener(this.onCallListener);
        CustomCallHelper.INSTANCE.removeCancelListener(this.onCancelListener);
        CustomCallHelper.INSTANCE.removeContentListener(this.onContentListener);
        CustomCallHelper.INSTANCE.removeCompleteListener(this.onCompleteListener);
    }

    private final void release() {
        BaseCustomCallFragment baseCustomCallFragment = this.currentFragment;
        if (baseCustomCallFragment != null) {
            baseCustomCallFragment.setOnActionState((Function2) null);
        }
        BaseCustomCallFragment baseCustomCallFragment2 = this.currentFragment;
        if (baseCustomCallFragment2 != null) {
            baseCustomCallFragment2.setOnFinish((Function1) null);
        }
        BaseCustomCallFragment baseCustomCallFragment3 = this.currentFragment;
        if (baseCustomCallFragment3 != null) {
            baseCustomCallFragment3.setShowStateBar((Function1) null);
        }
        BaseCustomCallFragment baseCustomCallFragment4 = this.currentFragment;
        if (baseCustomCallFragment4 != null) {
            baseCustomCallFragment4.release();
        }
        this.currentFragment = (BaseCustomCallFragment) null;
        unBindPresenter();
        Disposable disposable = this.cancelWaitTimeoutDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public final void releaseDataAndFinish() {
        release();
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        super.jumpAndFinish(intent);
        release();
        startActivity(intent);
        finish();
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showPowerEvent " + model);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3275i(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            jumpAndFinish(new Intent(this, (Class<?>) RobotChargingActivity.class));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
        Pdlog.m3273d(this.TAG, "showLowerNotify");
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        Pdlog.m3273d(this.TAG, "showPowerChange : i = " + i);
        ((MyStatusBarLayout) _$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCancelWaitDialog() {
        if (this.cancelWaitDialog == null) {
            this.cancelWaitDialog = new FullLoadDialog(this);
            FullLoadDialog fullLoadDialog = this.cancelWaitDialog;
            if (fullLoadDialog != null) {
                String string = getString(C4188R.string.custom_call_cancel_tip);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.custom_call_cancel_tip)");
                fullLoadDialog.setContent(string);
            }
        }
        FullLoadDialog fullLoadDialog2 = this.cancelWaitDialog;
        if (fullLoadDialog2 == null || fullLoadDialog2.isShowing()) {
            return;
        }
        fullLoadDialog2.show();
        createCancelTimeout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideCancelWaitDialog() {
        FullLoadDialog fullLoadDialog = this.cancelWaitDialog;
        if (fullLoadDialog == null || !fullLoadDialog.isShowing()) {
            return;
        }
        fullLoadDialog.dismiss();
    }

    private final void createCancelTimeout() {
        this.cancelWaitTimeoutDisposable = CountdownUtil.INSTANCE.createCountDown(5L).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$createCancelTimeout$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Long l) {
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$createCancelTimeout$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.CustomCallActivity$createCancelTimeout$3
            @Override // io.reactivex.functions.Action
            public final void run() {
                CustomCallActivity.this.hideCancelWaitDialog();
                CustomCallActivity customCallActivity = CustomCallActivity.this;
                customCallActivity.jumpAndFinish(new Intent(customCallActivity, (Class<?>) TurnBackActivity.class));
            }
        });
    }
}
