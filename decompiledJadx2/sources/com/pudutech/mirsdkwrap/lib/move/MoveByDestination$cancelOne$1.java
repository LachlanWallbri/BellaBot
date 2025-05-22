package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.move.bean.CurrentDestinationTask;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelOne$1", m3970f = "MoveByDestination.kt", m3971i = {0, 1, 2, 2, 3, 3}, m3972l = {75, 77, 79, 84}, m3973m = "invokeSuspend", m3974n = {"$this$runAsyn", "$this$runAsyn", "$this$runAsyn", "t", "$this$runAsyn", "t"}, m3975s = {"L$0", "L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class MoveByDestination$cancelOne$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6557p$;
    final /* synthetic */ MoveByDestination this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination$cancelOne$1(MoveByDestination moveByDestination, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByDestination;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByDestination$cancelOne$1 moveByDestination$cancelOne$1 = new MoveByDestination$cancelOne$1(this.this$0, completion);
        moveByDestination$cancelOne$1.f6557p$ = (CoroutineScope) obj;
        return moveByDestination$cancelOne$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByDestination$cancelOne$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009d A[RETURN] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.pudutech.mirsdkwrap.lib.move.bean.CurrentDestinationTask, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CurrentDestinationTask currentDestinationTask;
        Ref.ObjectRef objectRef;
        ?? r5;
        MainCoroutineDispatcher main;
        C53401 c53401;
        MoveByDestination moveByDestination;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6557p$;
            currentDestinationTask = this.this$0.currentTask;
            if (currentDestinationTask == null) {
                return Unit.INSTANCE;
            }
            MoveByDestination moveByDestination2 = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (moveByDestination2.cancelStatus(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Ref.ObjectRef objectRef2 = (Ref.ObjectRef) this.L$1;
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    objectRef = objectRef2;
                    coroutineScope = coroutineScope2;
                    this.this$0.currentTask = (CurrentDestinationTask) null;
                    moveByDestination = this.this$0;
                    this.L$0 = coroutineScope;
                    this.L$1 = objectRef;
                    this.label = 4;
                    if (moveByDestination.doNextTask(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                objectRef = new Ref.ObjectRef();
                r5 = this.this$0.currentTask;
                if (r5 == 0) {
                    Intrinsics.throwNpe();
                }
                objectRef.element = r5;
                main = Dispatchers.getMain();
                c53401 = new C53401(objectRef, null);
                this.L$0 = coroutineScope;
                this.L$1 = objectRef;
                this.label = 3;
                if (BuildersKt.withContext(main, c53401, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.this$0.currentTask = (CurrentDestinationTask) null;
                moveByDestination = this.this$0;
                this.L$0 = coroutineScope;
                this.L$1 = objectRef;
                this.label = 4;
                if (moveByDestination.doNextTask(this) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        MoveByDestination moveByDestination3 = this.this$0;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (moveByDestination3.genMoveReport(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        objectRef = new Ref.ObjectRef();
        r5 = this.this$0.currentTask;
        if (r5 == 0) {
        }
        objectRef.element = r5;
        main = Dispatchers.getMain();
        c53401 = new C53401(objectRef, null);
        this.L$0 = coroutineScope;
        this.L$1 = objectRef;
        this.label = 3;
        if (BuildersKt.withContext(main, c53401, this) == coroutine_suspended) {
        }
        this.this$0.currentTask = (CurrentDestinationTask) null;
        moveByDestination = this.this$0;
        this.L$0 = coroutineScope;
        this.L$1 = objectRef;
        this.label = 4;
        if (moveByDestination.doNextTask(this) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveByDestination.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelOne$1$1", m3970f = "MoveByDestination.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelOne$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $t */
        final /* synthetic */ Ref.ObjectRef f6558$t;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6559p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C53401(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.f6558$t = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53401 c53401 = new C53401(this.f6558$t, completion);
            c53401.f6559p$ = (CoroutineScope) obj;
            return c53401;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ArrayList arrayList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6559p$;
            MoveByDestinationStateListener onMoveStateListener = MoveByDestination$cancelOne$1.this.this$0.getOnMoveStateListener();
            if (onMoveStateListener == null) {
                return null;
            }
            ArrayList<Destination> arrayListOf = CollectionsKt.arrayListOf(((CurrentDestinationTask) this.f6558$t.element).getTask().getDestination());
            arrayList = MoveByDestination$cancelOne$1.this.this$0.destinationTasks;
            onMoveStateListener.onCancel(arrayListOf, true ^ arrayList.isEmpty());
            return Unit.INSTANCE;
        }
    }
}
