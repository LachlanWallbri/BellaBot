package com.pudutech.robot.module.voice;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/robot/module/voice/VoicePackageManager$selectPkg$1$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.robot.module.voice.VoicePackageManager$selectPkg$1$invokeSuspend$$inlined$let$lambda$1 */
/* loaded from: classes6.dex */
final class C5699x3ff2c268 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7223p$;
    final /* synthetic */ VoicePackageManager$selectPkg$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5699x3ff2c268(Continuation continuation, VoicePackageManager$selectPkg$1 voicePackageManager$selectPkg$1) {
        super(2, continuation);
        this.this$0 = voicePackageManager$selectPkg$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5699x3ff2c268 c5699x3ff2c268 = new C5699x3ff2c268(completion, this.this$0);
        c5699x3ff2c268.f7223p$ = (CoroutineScope) obj;
        return c5699x3ff2c268;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5699x3ff2c268) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7223p$;
        ArrayList arrayList = new ArrayList();
        VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
        list = VoicePackageManager.allPackages;
        arrayList.addAll(list);
        this.this$0.$cb.invoke(arrayList);
        return Unit.INSTANCE;
    }
}
