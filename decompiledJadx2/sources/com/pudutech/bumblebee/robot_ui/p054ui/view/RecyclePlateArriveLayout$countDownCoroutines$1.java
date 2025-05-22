package com.pudutech.bumblebee.robot_ui.p054ui.view;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: RecyclePlateArriveLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$countDownCoroutines$1", m3970f = "RecyclePlateArriveLayout.kt", m3971i = {0, 0, 1, 1}, m3972l = {139, 140}, m3973m = "invokeSuspend", m3974n = {"$this$flow", "i", "$this$flow", "i"}, m3975s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes4.dex */
final class RecyclePlateArriveLayout$countDownCoroutines$1 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $total;
    int I$0;
    int I$1;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private FlowCollector f4956p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclePlateArriveLayout$countDownCoroutines$1(int i, Continuation continuation) {
        super(2, continuation);
        this.$total = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RecyclePlateArriveLayout$countDownCoroutines$1 recyclePlateArriveLayout$countDownCoroutines$1 = new RecyclePlateArriveLayout$countDownCoroutines$1(this.$total, completion);
        recyclePlateArriveLayout$countDownCoroutines$1.f4956p$ = (FlowCollector) obj;
        return recyclePlateArriveLayout$countDownCoroutines$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super Integer> flowCollector, Continuation<? super Unit> continuation) {
        return ((RecyclePlateArriveLayout$countDownCoroutines$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0069 -> B:6:0x006c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        int i;
        int i2;
        Object obj2;
        RecyclePlateArriveLayout$countDownCoroutines$1 recyclePlateArriveLayout$countDownCoroutines$1;
        Object obj3;
        FlowCollector flowCollector2;
        int i3;
        int i4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = this.label;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = this.f4956p$;
            i = this.$total;
            i2 = 0;
            obj2 = coroutine_suspended;
            recyclePlateArriveLayout$countDownCoroutines$1 = this;
            if (i >= i2) {
            }
        } else if (i5 == 1) {
            i4 = this.I$1;
            i3 = this.I$0;
            flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj3 = coroutine_suspended;
            recyclePlateArriveLayout$countDownCoroutines$1 = this;
            recyclePlateArriveLayout$countDownCoroutines$1.L$0 = flowCollector2;
            recyclePlateArriveLayout$countDownCoroutines$1.I$0 = i3;
            recyclePlateArriveLayout$countDownCoroutines$1.I$1 = i4;
            recyclePlateArriveLayout$countDownCoroutines$1.label = 2;
            if (DelayKt.delay(1000L, recyclePlateArriveLayout$countDownCoroutines$1) != obj3) {
            }
        } else {
            if (i5 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i4 = this.I$1;
            i3 = this.I$0;
            FlowCollector flowCollector3 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector3;
            obj2 = coroutine_suspended;
            recyclePlateArriveLayout$countDownCoroutines$1 = this;
            int i6 = i3 - 1;
            i2 = i4;
            i = i6;
            if (i >= i2) {
                Integer boxInt = Boxing.boxInt(i);
                recyclePlateArriveLayout$countDownCoroutines$1.L$0 = flowCollector;
                recyclePlateArriveLayout$countDownCoroutines$1.I$0 = i;
                recyclePlateArriveLayout$countDownCoroutines$1.I$1 = i2;
                recyclePlateArriveLayout$countDownCoroutines$1.label = 1;
                if (flowCollector.emit(boxInt, recyclePlateArriveLayout$countDownCoroutines$1) == obj2) {
                    return obj2;
                }
                Object obj4 = obj2;
                flowCollector2 = flowCollector;
                obj3 = obj4;
                int i7 = i2;
                i3 = i;
                i4 = i7;
                recyclePlateArriveLayout$countDownCoroutines$1.L$0 = flowCollector2;
                recyclePlateArriveLayout$countDownCoroutines$1.I$0 = i3;
                recyclePlateArriveLayout$countDownCoroutines$1.I$1 = i4;
                recyclePlateArriveLayout$countDownCoroutines$1.label = 2;
                if (DelayKt.delay(1000L, recyclePlateArriveLayout$countDownCoroutines$1) != obj3) {
                    return obj3;
                }
                FlowCollector flowCollector4 = flowCollector2;
                obj2 = obj3;
                flowCollector = flowCollector4;
                int i62 = i3 - 1;
                i2 = i4;
                i = i62;
                if (i >= i2) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
