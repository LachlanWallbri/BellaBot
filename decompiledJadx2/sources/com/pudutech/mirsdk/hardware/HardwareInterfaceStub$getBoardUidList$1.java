package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.serialize.BoardInfo;
import com.pudutech.mirsdk.hardware.serialize.HardwareBoard;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$getBoardUidList$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 1, 1}, m3972l = {1810, 1812}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking", "mainBoardUid"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$getBoardUidList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Ref.ObjectRef $list;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5910p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareInterfaceStub$getBoardUidList$1(Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.$list = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$getBoardUidList$1 hardwareInterfaceStub$getBoardUidList$1 = new HardwareInterfaceStub$getBoardUidList$1(this.$list, completion);
        hardwareInterfaceStub$getBoardUidList$1.f5910p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$getBoardUidList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HardwareInterfaceStub$getBoardUidList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5910p$;
            CANBus access$getCanBus$p = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
            byte m4528constructorimpl = UByte.m4528constructorimpl((byte) HardwareBoard.Main.getId());
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = access$getCanBus$p.getBoardUid-9i1MAxw(m4528constructorimpl, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(((List) this.$list.element).add(new BoardInfo(HardwareBoard.Tail.name(), (String) obj)));
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        String str = (String) obj;
        ((List) this.$list.element).add(new BoardInfo(HardwareBoard.Main.name(), str));
        CANBus access$getCanBus$p2 = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
        byte m4528constructorimpl2 = UByte.m4528constructorimpl((byte) HardwareBoard.Tail.getId());
        this.L$0 = coroutineScope;
        this.L$1 = str;
        this.label = 2;
        obj = access$getCanBus$p2.getBoardUid-9i1MAxw(m4528constructorimpl2, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Boxing.boxBoolean(((List) this.$list.element).add(new BoardInfo(HardwareBoard.Tail.name(), (String) obj)));
    }
}
