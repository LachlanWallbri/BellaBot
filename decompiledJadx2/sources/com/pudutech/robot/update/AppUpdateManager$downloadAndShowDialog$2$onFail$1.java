package com.pudutech.robot.update;

import com.pudutech.disinfect.baselib.dialog.ShowTipsDialog;
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
/* compiled from: AppUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.update.AppUpdateManager$downloadAndShowDialog$2$onFail$1", m3970f = "AppUpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class AppUpdateManager$downloadAndShowDialog$2$onFail$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7372p$;
    final /* synthetic */ AppUpdateManager$downloadAndShowDialog$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppUpdateManager$downloadAndShowDialog$2$onFail$1(AppUpdateManager$downloadAndShowDialog$2 appUpdateManager$downloadAndShowDialog$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = appUpdateManager$downloadAndShowDialog$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AppUpdateManager$downloadAndShowDialog$2$onFail$1 appUpdateManager$downloadAndShowDialog$2$onFail$1 = new AppUpdateManager$downloadAndShowDialog$2$onFail$1(this.this$0, completion);
        appUpdateManager$downloadAndShowDialog$2$onFail$1.f7372p$ = (CoroutineScope) obj;
        return appUpdateManager$downloadAndShowDialog$2$onFail$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppUpdateManager$downloadAndShowDialog$2$onFail$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7372p$;
        if (this.this$0.$dialog.isShowing()) {
            this.this$0.$dialog.setDialogType(ShowTipsDialog.INSTANCE.getTITLE_TIPS_AND_BOTTOM_BUTTON());
            if (this.this$0.getIsDownFinish()) {
                ShowTipsDialog showTipsDialog = this.this$0.$dialog;
                String string = AppUpdateManager.access$getContext$p(AppUpdateManager.INSTANCE).getString(C5722R.string.pdStr27_5);
                Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr27_5)");
                showTipsDialog.setTips(string);
            } else {
                ShowTipsDialog showTipsDialog2 = this.this$0.$dialog;
                String string2 = AppUpdateManager.access$getContext$p(AppUpdateManager.INSTANCE).getString(C5722R.string.pdStr27_6);
                Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.pdStr27_6)");
                showTipsDialog2.setTips(string2);
            }
            ShowTipsDialog showTipsDialog3 = this.this$0.$dialog;
            String string3 = AppUpdateManager.access$getContext$p(AppUpdateManager.INSTANCE).getString(C5722R.string.pdStr27_4);
            Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.pdStr27_4)");
            showTipsDialog3.setButTips(string3);
            this.this$0.$dialog.setOnBtnClickListener(new Function0<Unit>() { // from class: com.pudutech.robot.update.AppUpdateManager$downloadAndShowDialog$2$onFail$1.1
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
                    AppUpdateManager$downloadAndShowDialog$2$onFail$1.this.this$0.$dialog.dismiss();
                }
            });
        }
        return Unit.INSTANCE;
    }
}
