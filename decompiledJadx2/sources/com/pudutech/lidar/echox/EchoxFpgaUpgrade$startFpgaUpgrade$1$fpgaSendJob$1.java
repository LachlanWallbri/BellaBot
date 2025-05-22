package com.pudutech.lidar.echox;

import android.util.Log;
import java.util.ArrayList;
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
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: EchoxFpgaUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lidar.echox.EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1", m3970f = "EchoxFpgaUpgrade.kt", m3971i = {0}, m3972l = {62}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5456p$;
    final /* synthetic */ EchoxFpgaUpgrade$startFpgaUpgrade$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1(EchoxFpgaUpgrade$startFpgaUpgrade$1 echoxFpgaUpgrade$startFpgaUpgrade$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = echoxFpgaUpgrade$startFpgaUpgrade$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1 echoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1 = new EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1(this.this$0, completion);
        echoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1.f5456p$ = (CoroutineScope) obj;
        return echoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5456p$;
            str = this.this$0.this$0.TAG;
            Log.d(str, "fpgaSendJob: isActive = " + CoroutineScopeKt.isActive(coroutineScope));
            EchoxFpgaUpgrade echoxFpgaUpgrade = this.this$0.this$0;
            ArrayList<byte[]> arrayList = this.this$0.$upgradePackageList;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (echoxFpgaUpgrade.sendFpgaData(arrayList, this) == coroutine_suspended) {
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
