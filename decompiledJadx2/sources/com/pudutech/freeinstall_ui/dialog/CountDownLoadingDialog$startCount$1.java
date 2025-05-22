package com.pudutech.freeinstall_ui.dialog;

import android.widget.TextView;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CountDownLoadingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.dialog.CountDownLoadingDialog$startCount$1", m3970f = "CountDownLoadingDialog.kt", m3971i = {0}, m3972l = {38}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
public final class CountDownLoadingDialog$startCount$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5239p$;
    final /* synthetic */ CountDownLoadingDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountDownLoadingDialog$startCount$1(CountDownLoadingDialog countDownLoadingDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = countDownLoadingDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CountDownLoadingDialog$startCount$1 countDownLoadingDialog$startCount$1 = new CountDownLoadingDialog$startCount$1(this.this$0, completion);
        countDownLoadingDialog$startCount$1.f5239p$ = (CoroutineScope) obj;
        return countDownLoadingDialog$startCount$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CountDownLoadingDialog$startCount$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0040  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0035 -> B:5:0x0038). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CountDownLoadingDialog$startCount$1 countDownLoadingDialog$startCount$1;
        boolean z;
        boolean z2;
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5239p$;
            countDownLoadingDialog$startCount$1 = this;
            z = countDownLoadingDialog$startCount$1.this$0.stopCountDown;
            if (z) {
            }
        } else if (i2 == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            countDownLoadingDialog$startCount$1 = this;
            z2 = countDownLoadingDialog$startCount$1.this$0.stopCountDown;
            if (!z2) {
                return Unit.INSTANCE;
            }
            CountDownLoadingDialog countDownLoadingDialog = countDownLoadingDialog$startCount$1.this$0;
            i = countDownLoadingDialog.hasWaitTime;
            countDownLoadingDialog.hasWaitTime = i + 1;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45751(null), 2, null);
            z = countDownLoadingDialog$startCount$1.this$0.stopCountDown;
            if (z) {
                countDownLoadingDialog$startCount$1.L$0 = coroutineScope;
                countDownLoadingDialog$startCount$1.label = 1;
                if (DelayKt.delay(1000L, countDownLoadingDialog$startCount$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z2 = countDownLoadingDialog$startCount$1.this$0.stopCountDown;
                if (!z2) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CountDownLoadingDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.dialog.CountDownLoadingDialog$startCount$1$1", m3970f = "CountDownLoadingDialog.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.freeinstall_ui.dialog.CountDownLoadingDialog$startCount$1$1 */
    /* loaded from: classes3.dex */
    public static final class C45751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5240p$;

        C45751(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45751 c45751 = new C45751(completion);
            c45751.f5240p$ = (CoroutineScope) obj;
            return c45751;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5240p$;
            TextView tv_has_wait_time = (TextView) CountDownLoadingDialog$startCount$1.this.this$0._$_findCachedViewById(C5362R.id.tv_has_wait_time);
            Intrinsics.checkExpressionValueIsNotNull(tv_has_wait_time, "tv_has_wait_time");
            i = CountDownLoadingDialog$startCount$1.this.this$0.hasWaitTime;
            tv_has_wait_time.setText(String.valueOf(i));
            return Unit.INSTANCE;
        }
    }
}
