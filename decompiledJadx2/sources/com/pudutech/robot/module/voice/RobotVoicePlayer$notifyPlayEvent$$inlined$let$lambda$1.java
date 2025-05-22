package com.pudutech.robot.module.voice;

import com.pudutech.robot.module.voice.data.PlayEvent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotVoicePlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/robot/module/voice/RobotVoicePlayer$notifyPlayEvent$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlayEvent $event$inlined;
    final /* synthetic */ Function3 $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7221p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1(Function3 function3, Continuation continuation, PlayEvent playEvent) {
        super(2, continuation);
        this.$it = function3;
        this.$event$inlined = playEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1 robotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1 = new RobotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1(this.$it, completion, this.$event$inlined);
        robotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1.f7221p$ = (CoroutineScope) obj;
        return robotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotVoicePlayer$notifyPlayEvent$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        boolean z;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7221p$;
        Function3 function3 = this.$it;
        PlayEvent playEvent = this.$event$inlined;
        RobotVoicePlayer robotVoicePlayer = RobotVoicePlayer.INSTANCE;
        str = RobotVoicePlayer.content;
        RobotVoicePlayer robotVoicePlayer2 = RobotVoicePlayer.INSTANCE;
        z = RobotVoicePlayer.isCombineVoice;
        function3.invoke(playEvent, str, Boxing.boxBoolean(z));
        return Unit.INSTANCE;
    }
}
