package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ReceiveChannel;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Combine.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$onReceive$1", "kotlinx/coroutines/flow/internal/CombineKt$combineInternal$2$$special$$inlined$onReceive$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$invokeSuspend$$inlined$select$lambda$2 */
/* loaded from: classes2.dex */
public final class C7979x2d5a9c56 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel[] $channels$inlined;
    final /* synthetic */ int $i$inlined;
    final /* synthetic */ Boolean[] $isClosed$inlined;
    final /* synthetic */ Object[] $latestValues$inlined;
    final /* synthetic */ Ref.IntRef $nonClosed$inlined;
    final /* synthetic */ Function2 $onReceive;
    final /* synthetic */ Ref.IntRef $remainingNulls$inlined;
    final /* synthetic */ int $size$inlined;
    Object L$0;
    int label;
    private Object p$0;
    final /* synthetic */ CombineKt$combineInternal$2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C7979x2d5a9c56(Function2 function2, Continuation continuation, int i, CombineKt$combineInternal$2 combineKt$combineInternal$2, int i2, Boolean[] boolArr, ReceiveChannel[] receiveChannelArr, Object[] objArr, Ref.IntRef intRef, Ref.IntRef intRef2) {
        super(2, continuation);
        this.$onReceive = function2;
        this.$i$inlined = i;
        this.this$0 = combineKt$combineInternal$2;
        this.$size$inlined = i2;
        this.$isClosed$inlined = boolArr;
        this.$channels$inlined = receiveChannelArr;
        this.$latestValues$inlined = objArr;
        this.$remainingNulls$inlined = intRef;
        this.$nonClosed$inlined = intRef2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        C7979x2d5a9c56 c7979x2d5a9c56 = new C7979x2d5a9c56(this.$onReceive, continuation, this.$i$inlined, this.this$0, this.$size$inlined, this.$isClosed$inlined, this.$channels$inlined, this.$latestValues$inlined, this.$remainingNulls$inlined, this.$nonClosed$inlined);
        c7979x2d5a9c56.p$0 = obj;
        return c7979x2d5a9c56;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((C7979x2d5a9c56) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.p$0;
            if (obj2 != null) {
                Function2 function2 = this.$onReceive;
                this.L$0 = obj2;
                this.label = 1;
                if (function2.invoke(obj2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.$isClosed$inlined[this.$i$inlined] = Boxing.boxBoolean(true);
                r4.element--;
                int i2 = this.$nonClosed$inlined.element;
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
