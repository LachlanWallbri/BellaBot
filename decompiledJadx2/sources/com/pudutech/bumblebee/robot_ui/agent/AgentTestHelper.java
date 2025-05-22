package com.pudutech.bumblebee.robot_ui.agent;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ConfirmTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AgentTestHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BaseLifecycleObserver;", "()V", "TAG", "", "mActivity", "Landroid/app/Activity;", "mScope", "Lkotlinx/coroutines/CoroutineScope;", "mTipDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ConfirmTipDialog;", "tipCallBack", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "Lkotlin/ParameterName;", "name", "type", "", "bindLifecycle", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "clean", "creatThread", "destroy", "resume", "showTip", "start", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AgentTestHelper implements BaseLifecycleObserver {
    private Activity mActivity;
    private CoroutineScope mScope;
    private ConfirmTipDialog mTipDialog;
    private final String TAG = "AgentTestHelper";
    private Function1<? super AgentTestManager.TipType, Unit> tipCallBack = new Function1<AgentTestManager.TipType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper$tipCallBack$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AgentTestManager.TipType tipType) {
            invoke2(tipType);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(AgentTestManager.TipType it) {
            String str;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = AgentTestHelper.this.TAG;
            Pdlog.m3273d(str, "tipCallBack() = " + it);
            AgentTestHelper.this.showTip(it);
        }
    };

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {
        BaseLifecycleObserver.DefaultImpls.any(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        BaseLifecycleObserver.DefaultImpls.create(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        BaseLifecycleObserver.DefaultImpls.pause(this);
    }

    public final void bindLifecycle(AppCompatActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.mActivity = activity;
        activity.getLifecycle().addObserver(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void resume() {
        BaseLifecycleObserver.DefaultImpls.resume(this);
        Pdlog.m3273d(this.TAG, "resume() = " + AgentTestManager.INSTANCE.getMTipType());
        showTip(AgentTestManager.INSTANCE.getMTipType());
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void start() {
        BaseLifecycleObserver.DefaultImpls.start(this);
        AgentTestManager.INSTANCE.setOnTipCallBack(this.tipCallBack);
        Pdlog.m3273d(this.TAG, "start() = " + AgentTestManager.INSTANCE.getMTipType());
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void stop() {
        BaseLifecycleObserver.DefaultImpls.stop(this);
        AgentTestManager.INSTANCE.setOnTipCallBack((Function1) null);
        Pdlog.m3273d(this.TAG, "stop() = " + AgentTestManager.INSTANCE.getMTipType());
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.BaseLifecycleObserver
    public void destroy() {
        BaseLifecycleObserver.DefaultImpls.destroy(this);
        Activity activity = this.mActivity;
        if (activity != null && (activity instanceof AppCompatActivity)) {
            ((AppCompatActivity) activity).getLifecycle().removeObserver(this);
            Pdlog.m3273d(this.TAG, "lifecycle.removeObserver");
        }
        ConfirmTipDialog confirmTipDialog = this.mTipDialog;
        if (confirmTipDialog != null && confirmTipDialog.isShowing()) {
            confirmTipDialog.dismiss();
        }
        CoroutineScope coroutineScope = this.mScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.mScope = (CoroutineScope) null;
        this.mActivity = (Activity) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.String] */
    public final void showTip(final AgentTestManager.TipType type) {
        Activity activity;
        Pdlog.m3273d(this.TAG, "showTip() type = " + type);
        if (Intrinsics.areEqual(type, AgentTestManager.TipType.TipNo.INSTANCE)) {
            return;
        }
        if (this.mTipDialog == null && (activity = this.mActivity) != null) {
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            this.mTipDialog = new ConfirmTipDialog((Context) activity, true);
        }
        if (this.mTipDialog == null) {
            Pdlog.m3273d(this.TAG, "showTip() mTipDialog is null = " + type);
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        if (Intrinsics.areEqual(type, AgentTestManager.TipType.Tip12.INSTANCE)) {
            long timeDiff = (AgentTestManager.TWENTY_FOUR_HOUR - AgentTestManager.INSTANCE.getTimeDiff()) / 60;
            Pdlog.m3273d(this.TAG, "showTip() leftTime = " + timeDiff + " type = " + type);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String sting = CommonExtKt.getSting(C4188R.string.tested_remain_hours);
            Object[] objArr = {String.valueOf(timeDiff)};
            ?? format = String.format(sting, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            objectRef.element = format;
            final ConfirmTipDialog confirmTipDialog = this.mTipDialog;
            if (confirmTipDialog != null) {
                confirmTipDialog.setMsg((String) objectRef.element);
                confirmTipDialog.setTitle(CommonExtKt.getSting(C4188R.string.pdStr5_1));
                confirmTipDialog.setBtn1Text(CommonExtKt.getSting(C4188R.string.restore_factory));
                confirmTipDialog.setBtn2Text(CommonExtKt.getSting(C4188R.string.test_continue));
                confirmTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper$showTip$$inlined$apply$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        AgentTestHelper.this.clean();
                        str = AgentTestHelper.this.TAG;
                        Pdlog.m3273d(str, "showTip() Tip12 clean type = " + type);
                    }
                });
                confirmTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper$showTip$1$2
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
                        ConfirmTipDialog.this.dismiss();
                    }
                });
                confirmTipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper$showTip$$inlined$apply$lambda$2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        String str;
                        AgentTestManager.INSTANCE.setMTipType(AgentTestManager.TipType.TipNo.INSTANCE);
                        AgentTestManager.INSTANCE.setHaved12Tip(true);
                        str = AgentTestHelper.this.TAG;
                        Pdlog.m3273d(str, "showTip() Tip12  isHaved12Tip =" + AgentTestManager.INSTANCE.isHaved12Tip());
                    }
                });
            }
            ConfirmTipDialog confirmTipDialog2 = this.mTipDialog;
            if (confirmTipDialog2 != null) {
                confirmTipDialog2.show();
            }
            ConfirmTipDialog confirmTipDialog3 = this.mTipDialog;
            if (confirmTipDialog3 != null) {
                confirmTipDialog3.autoHide(10000L);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(type, AgentTestManager.TipType.Tip24.INSTANCE)) {
            objectRef.element = CommonExtKt.getSting(C4188R.string.test_expired_restore_factory);
            ConfirmTipDialog confirmTipDialog4 = this.mTipDialog;
            if (confirmTipDialog4 != null) {
                confirmTipDialog4.setMsg((String) objectRef.element);
                confirmTipDialog4.setTitle(CommonExtKt.getSting(C4188R.string.pdStr5_1));
                confirmTipDialog4.setBtn1Visibility(true);
                confirmTipDialog4.setBtn2Text(CommonExtKt.getSting(C4188R.string.delivery_alarm_iknow));
                confirmTipDialog4.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper$showTip$$inlined$apply$lambda$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        AgentTestHelper.this.clean();
                        str = AgentTestHelper.this.TAG;
                        Pdlog.m3273d(str, "showTip() Tip24 clean type = " + type);
                        AgentTestManager.INSTANCE.setMTipType(AgentTestManager.TipType.TipNo.INSTANCE);
                    }
                });
            }
            ConfirmTipDialog confirmTipDialog5 = this.mTipDialog;
            if (confirmTipDialog5 != null) {
                confirmTipDialog5.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clean() {
        creatThread();
        if (this.mActivity == null || this.mScope == null) {
            return;
        }
        AgentTestManager agentTestManager = AgentTestManager.INSTANCE;
        Activity activity = this.mActivity;
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Activity activity2 = activity;
        CoroutineScope coroutineScope = this.mScope;
        if (coroutineScope == null) {
            Intrinsics.throwNpe();
        }
        agentTestManager.cleanFactory(activity2, coroutineScope);
    }

    private final void creatThread() {
        if (this.mScope == null) {
            this.mScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            Pdlog.m3273d(this.TAG, "creatThread");
        }
    }
}
