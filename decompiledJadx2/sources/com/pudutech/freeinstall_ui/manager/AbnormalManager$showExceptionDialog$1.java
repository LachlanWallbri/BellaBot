package com.pudutech.freeinstall_ui.manager;

import android.app.Activity;
import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.base.KtxFreeInstallActivityManager;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbnormalManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.manager.AbnormalManager$showExceptionDialog$1", m3970f = "AbnormalManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class AbnormalManager$showExceptionDialog$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5241p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbnormalManager$showExceptionDialog$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AbnormalManager$showExceptionDialog$1 abnormalManager$showExceptionDialog$1 = new AbnormalManager$showExceptionDialog$1(completion);
        abnormalManager$showExceptionDialog$1.f5241p$ = (CoroutineScope) obj;
        return abnormalManager$showExceptionDialog$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AbnormalManager$showExceptionDialog$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CommonDialog commonDialog;
        Activity activity;
        CommonDialog commonDialog2;
        Activity activity2;
        CommonDialog commonDialog3;
        CommonDialog commonDialog4;
        CommonDialog commonDialog5;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5241p$;
            Pdlog.m3273d(AbnormalManager.TAG, "showExceptionDialog");
            AbnormalManager abnormalManager = AbnormalManager.INSTANCE;
            commonDialog = AbnormalManager.sensorDialog;
            if (commonDialog != null && commonDialog.isShowing()) {
                return Unit.INSTANCE;
            }
            Activity currentActivity = KtxFreeInstallActivityManager.INSTANCE.getCurrentActivity();
            if (currentActivity == null) {
                return Unit.INSTANCE;
            }
            WeakReference weakReference = new WeakReference(currentActivity);
            Activity activity3 = (Activity) weakReference.get();
            if (activity3 == null || activity3.isFinishing() || (activity = (Activity) weakReference.get()) == null || activity.isDestroyed()) {
                return Unit.INSTANCE;
            }
            Pdlog.m3273d(AbnormalManager.TAG, "showExceptionDialog  传感器异常");
            AbnormalManager abnormalManager2 = AbnormalManager.INSTANCE;
            Object obj2 = weakReference.get();
            if (obj2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(obj2, "weakReference.get()!!");
            CommonDialog.Builder builder = new CommonDialog.Builder((Context) obj2);
            String string = AppContext.INSTANCE.getContext().getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getString(R.string.tips)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = AppContext.INSTANCE.getContext().getString(C5362R.string.sensor_exception);
            Intrinsics.checkExpressionValueIsNotNull(string2, "AppContext.context.getSt….string.sensor_exception)");
            CommonDialog.Builder minContent = title.setMinContent(string2);
            String string3 = AppContext.INSTANCE.getContext().getString(C5362R.string.restart);
            Intrinsics.checkExpressionValueIsNotNull(string3, "AppContext.context.getString(R.string.restart)");
            AbnormalManager.sensorDialog = minContent.setBtRight(string3).setClose(false).create();
            AbnormalManager abnormalManager3 = AbnormalManager.INSTANCE;
            commonDialog2 = AbnormalManager.sensorDialog;
            if (commonDialog2 != null) {
                commonDialog2.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.manager.AbnormalManager$showExceptionDialog$1.1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        CommonDialog commonDialog6;
                        AbnormalManager abnormalManager4 = AbnormalManager.INSTANCE;
                        commonDialog6 = AbnormalManager.sensorDialog;
                        if (commonDialog6 != null) {
                            commonDialog6.dismiss();
                        }
                        AbnormalManager abnormalManager5 = AbnormalManager.INSTANCE;
                        AbnormalManager.sensorDialog = (CommonDialog) null;
                        Pdlog.m3273d(AbnormalManager.TAG, "重启系统");
                        AbnormalManager.INSTANCE.restartApp();
                    }
                });
            }
            Activity activity4 = (Activity) weakReference.get();
            if (activity4 == null || activity4.isFinishing() || (activity2 = (Activity) weakReference.get()) == null || activity2.isDestroyed()) {
                return Unit.INSTANCE;
            }
            AbnormalManager abnormalManager4 = AbnormalManager.INSTANCE;
            commonDialog3 = AbnormalManager.sensorDialog;
            if (commonDialog3 != null) {
                commonDialog3.setCanceledOnTouchOutside(false);
            }
            AbnormalManager abnormalManager5 = AbnormalManager.INSTANCE;
            commonDialog4 = AbnormalManager.sensorDialog;
            if (commonDialog4 != null) {
                commonDialog4.setCancelable(false);
            }
            AbnormalManager abnormalManager6 = AbnormalManager.INSTANCE;
            commonDialog5 = AbnormalManager.sensorDialog;
            if (commonDialog5 != null) {
                commonDialog5.show();
            }
            AbnormalManager abnormalManager7 = AbnormalManager.INSTANCE;
            AbnormalManager.hasShowExceptionDialog = true;
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
