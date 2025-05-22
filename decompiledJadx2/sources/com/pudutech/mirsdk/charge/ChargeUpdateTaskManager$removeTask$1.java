package com.pudutech.mirsdk.charge;

import com.pudutech.mirsdk.charge.ChargeUpdateTask;
import java.util.Map;
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
/* compiled from: ChargeUpdateTaskManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeUpdateTaskManager$removeTask$1", m3970f = "ChargeUpdateTaskManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class ChargeUpdateTaskManager$removeTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5762p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeUpdateTaskManager$removeTask$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeUpdateTaskManager$removeTask$1 chargeUpdateTaskManager$removeTask$1 = new ChargeUpdateTaskManager$removeTask$1(this.$mac, completion);
        chargeUpdateTaskManager$removeTask$1.f5762p$ = (CoroutineScope) obj;
        return chargeUpdateTaskManager$removeTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeUpdateTaskManager$removeTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Map map;
        Map map2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5762p$;
        ChargeUpdateTaskManager chargeUpdateTaskManager = ChargeUpdateTaskManager.INSTANCE;
        map = ChargeUpdateTaskManager.updateTaskHashMap;
        ChargeUpdateTask chargeUpdateTask = (ChargeUpdateTask) map.get(this.$mac);
        if (chargeUpdateTask != null) {
            chargeUpdateTask.setChargePileUpdateListener((ChargeUpdateTask.onChargePileUpdateListener) null);
        }
        ChargeUpdateTaskManager chargeUpdateTaskManager2 = ChargeUpdateTaskManager.INSTANCE;
        map2 = ChargeUpdateTaskManager.updateTaskHashMap;
        map2.remove(this.$mac);
        return Unit.INSTANCE;
    }
}
