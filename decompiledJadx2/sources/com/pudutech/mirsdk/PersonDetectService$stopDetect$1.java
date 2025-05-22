package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import java.util.concurrent.atomic.AtomicBoolean;
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

/* compiled from: PersonDetectService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.PersonDetectService$stopDetect$1", m3970f = "PersonDetectService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class PersonDetectService$stopDetect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5565p$;
    final /* synthetic */ PersonDetectService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersonDetectService$stopDetect$1(PersonDetectService personDetectService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = personDetectService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PersonDetectService$stopDetect$1 personDetectService$stopDetect$1 = new PersonDetectService$stopDetect$1(this.this$0, completion);
        personDetectService$stopDetect$1.f5565p$ = (CoroutineScope) obj;
        return personDetectService$stopDetect$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PersonDetectService$stopDetect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AtomicBoolean atomicBoolean;
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        MirCoreInterface mirCoreInterface;
        String str;
        MirCoreInterface mirCoreInterface2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5565p$;
        Pdlog.m3273d(this.this$0.getTAG(), "stopDetect start------");
        atomicBoolean = this.this$0.isStartPersonDetect;
        atomicBoolean.set(false);
        aIDLConnection = this.this$0.coreService;
        if (aIDLConnection != null && (mirCoreInterface2 = (MirCoreInterface) aIDLConnection.getInterface()) != null) {
            mirCoreInterface2.enablePersonDetectInRange(false, 0.0d, 0.0d, 0.0d);
        }
        aIDLConnection2 = this.this$0.coreService;
        if (aIDLConnection2 != null && (mirCoreInterface = (MirCoreInterface) aIDLConnection2.getInterface()) != null) {
            str = this.this$0.LISTENER_NAME;
            mirCoreInterface.removePersonListener(str);
        }
        Pdlog.m3273d(this.this$0.getTAG(), "stopDetect end------");
        return Unit.INSTANCE;
    }
}
