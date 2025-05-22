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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbnormalManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.manager.AbnormalManager$showLostDialog$1", m3970f = "AbnormalManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class AbnormalManager$showLostDialog$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5242p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbnormalManager$showLostDialog$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AbnormalManager$showLostDialog$1 abnormalManager$showLostDialog$1 = new AbnormalManager$showLostDialog$1(completion);
        abnormalManager$showLostDialog$1.f5242p$ = (CoroutineScope) obj;
        return abnormalManager$showLostDialog$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AbnormalManager$showLostDialog$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Activity currentActivity;
        boolean z;
        Activity activity;
        CommonDialog commonDialog;
        Activity activity2;
        CommonDialog commonDialog2;
        CommonDialog commonDialog3;
        CommonDialog commonDialog4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5242p$;
        try {
            Pdlog.m3273d(AbnormalManager.TAG, "showLostDialog");
            currentActivity = KtxFreeInstallActivityManager.INSTANCE.getCurrentActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentActivity == null) {
            return Unit.INSTANCE;
        }
        WeakReference weakReference = new WeakReference(currentActivity);
        Pdlog.m3273d(AbnormalManager.TAG, "定位丢失 ----" + ((Activity) weakReference.get()));
        AbnormalManager abnormalManager = AbnormalManager.INSTANCE;
        z = AbnormalManager.hasShowLocateDialog;
        if (z) {
            return Unit.INSTANCE;
        }
        Activity activity3 = (Activity) weakReference.get();
        if (activity3 != null && !activity3.isFinishing() && (activity = (Activity) weakReference.get()) != null && !activity.isDestroyed()) {
            AbnormalManager abnormalManager2 = AbnormalManager.INSTANCE;
            commonDialog = AbnormalManager.locateDialog;
            if (commonDialog != null && commonDialog.isShowing()) {
                return Unit.INSTANCE;
            }
            AbnormalManager abnormalManager3 = AbnormalManager.INSTANCE;
            Object obj2 = weakReference.get();
            if (obj2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(obj2, "weakReference.get()!!");
            CommonDialog.Builder builder = new CommonDialog.Builder((Context) obj2);
            String string = AppContext.INSTANCE.getContext().getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getString(R.string.tips)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = AppContext.INSTANCE.getContext().getString(C5362R.string.locate_lost);
            Intrinsics.checkExpressionValueIsNotNull(string2, "AppContext.context.getString(R.string.locate_lost)");
            AbnormalManager.locateDialog = title.setMinContent(string2).setClose(false).create();
            Activity activity4 = (Activity) weakReference.get();
            if (activity4 != null && !activity4.isFinishing() && (activity2 = (Activity) weakReference.get()) != null && !activity2.isDestroyed()) {
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("--- ");
                Object obj3 = weakReference.get();
                if (obj3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(obj3, "weakReference.get()!!");
                sb.append(((Activity) obj3).isFinishing());
                objArr[0] = sb.toString();
                Pdlog.m3273d(AbnormalManager.TAG, objArr);
                AbnormalManager abnormalManager4 = AbnormalManager.INSTANCE;
                commonDialog2 = AbnormalManager.locateDialog;
                if (commonDialog2 != null) {
                    commonDialog2.setCanceledOnTouchOutside(false);
                }
                AbnormalManager abnormalManager5 = AbnormalManager.INSTANCE;
                commonDialog3 = AbnormalManager.locateDialog;
                if (commonDialog3 != null) {
                    commonDialog3.setCancelable(false);
                }
                AbnormalManager abnormalManager6 = AbnormalManager.INSTANCE;
                commonDialog4 = AbnormalManager.locateDialog;
                if (commonDialog4 != null) {
                    commonDialog4.show();
                }
                AbnormalManager abnormalManager7 = AbnormalManager.INSTANCE;
                AbnormalManager.hasShowLocateDialog = true;
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
