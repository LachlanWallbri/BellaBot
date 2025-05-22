package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SpeedFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/module/setting/ui/SpeedFragment$cruiseSpeed$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class SpeedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $speed_index;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4887p$;
    final /* synthetic */ SpeedFragment$cruiseSpeed$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpeedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1(int i, Continuation continuation, SpeedFragment$cruiseSpeed$1 speedFragment$cruiseSpeed$1) {
        super(2, continuation);
        this.$speed_index = i;
        this.this$0 = speedFragment$cruiseSpeed$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SpeedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1 speedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1 = new SpeedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1(this.$speed_index, completion, this.this$0);
        speedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1.f4887p$ = (CoroutineScope) obj;
        return speedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SpeedFragment$cruiseSpeed$1$$special$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4887p$;
        RobotSpeedUtil.INSTANCE.setCruiseSpeedLevel(RobotContext.INSTANCE.getContext(), (String) this.this$0.$speedData.get(this.$speed_index));
        return Unit.INSTANCE;
    }
}
