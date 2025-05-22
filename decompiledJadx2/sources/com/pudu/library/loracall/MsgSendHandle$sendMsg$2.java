package com.pudu.library.loracall;

import java.util.concurrent.CopyOnWriteArrayList;
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
/* compiled from: MsgSendHandle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudu.library.loracall.MsgSendHandle$sendMsg$2", m3970f = "MsgSendHandle.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class MsgSendHandle$sendMsg$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SlipMsgBean $slipMsg;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4376p$;
    final /* synthetic */ MsgSendHandle this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MsgSendHandle$sendMsg$2(MsgSendHandle msgSendHandle, SlipMsgBean slipMsgBean, Continuation continuation) {
        super(2, continuation);
        this.this$0 = msgSendHandle;
        this.$slipMsg = slipMsgBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MsgSendHandle$sendMsg$2 msgSendHandle$sendMsg$2 = new MsgSendHandle$sendMsg$2(this.this$0, this.$slipMsg, completion);
        msgSendHandle$sendMsg$2.f4376p$ = (CoroutineScope) obj;
        return msgSendHandle$sendMsg$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MsgSendHandle$sendMsg$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2;
        Object obj2;
        CopyOnWriteArrayList copyOnWriteArrayList3;
        Object obj3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4376p$;
        copyOnWriteArrayList = this.this$0.msgQueue;
        if (copyOnWriteArrayList.isEmpty()) {
            obj2 = this.this$0.lock;
            synchronized (obj2) {
                copyOnWriteArrayList3 = this.this$0.msgQueue;
                copyOnWriteArrayList3.add(this.$slipMsg);
                obj3 = this.this$0.lock;
                obj3.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
        } else {
            copyOnWriteArrayList2 = this.this$0.msgQueue;
            copyOnWriteArrayList2.add(this.$slipMsg);
        }
        return Unit.INSTANCE;
    }
}
