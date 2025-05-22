package com.pudutech.mirsdk.sdksafe;

import android.app.Activity;
import android.content.Context;
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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafe.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.SDKSafe$chkTask$1", m3970f = "SDKSafe.kt", m3971i = {0}, m3972l = {244}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class SDKSafe$chkTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Function2 $errorMsg;
    final /* synthetic */ String $node;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6460p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKSafe$chkTask$1(Activity activity, String str, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$activity = activity;
        this.$node = str;
        this.$errorMsg = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKSafe$chkTask$1 sDKSafe$chkTask$1 = new SDKSafe$chkTask$1(this.$activity, this.$node, this.$errorMsg, completion);
        sDKSafe$chkTask$1.f6460p$ = (CoroutineScope) obj;
        return sDKSafe$chkTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKSafe$chkTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6460p$;
            SDKSafe sDKSafe = SDKSafe.INSTANCE;
            Context applicationContext = this.$activity.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "activity.applicationContext");
            String str = this.$node;
            Function2<? super Integer, ? super String, Unit> function2 = this.$errorMsg;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (sDKSafe.chkNode(applicationContext, str, function2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
