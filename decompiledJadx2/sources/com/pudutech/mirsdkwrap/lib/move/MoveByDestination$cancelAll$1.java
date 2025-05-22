package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.move.bean.CurrentDestinationTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveDestinationTask;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelAll$1", m3970f = "MoveByDestination.kt", m3971i = {0, 0, 1, 1, 2, 2, 2, 2}, m3972l = {121, 122, 136}, m3973m = "invokeSuspend", m3974n = {"$this$runAsyn", "t", "$this$runAsyn", "t", "$this$runAsyn", "t", "cancelT", "list"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes6.dex */
public final class MoveByDestination$cancelAll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6555p$;
    final /* synthetic */ MoveByDestination this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination$cancelAll$1(MoveByDestination moveByDestination, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByDestination;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByDestination$cancelAll$1 moveByDestination$cancelAll$1 = new MoveByDestination$cancelAll$1(this.this$0, completion);
        moveByDestination$cancelAll$1.f6555p$ = (CoroutineScope) obj;
        return moveByDestination$cancelAll$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByDestination$cancelAll$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ba A[LOOP:0: B:17:0x00b4->B:19:0x00ba, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0106 A[RETURN] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, java.util.ArrayList] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CurrentDestinationTask currentDestinationTask;
        ArrayList arrayList;
        ArrayList arrayList2;
        MainCoroutineDispatcher main;
        C53392 c53392;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6555p$;
            currentDestinationTask = this.this$0.currentTask;
            this.this$0.currentTask = (CurrentDestinationTask) null;
            MoveByDestination moveByDestination = this.this$0;
            this.L$0 = coroutineScope;
            this.L$1 = currentDestinationTask;
            this.label = 1;
            if (moveByDestination.cancelStatus(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.destroy$module_robot_mirsdk_wrapper_release();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                currentDestinationTask = (CurrentDestinationTask) this.L$1;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope2;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = new ArrayList();
                if (currentDestinationTask != null) {
                    Boxing.boxBoolean(((ArrayList) objectRef.element).add(currentDestinationTask.getTask().getDestination()));
                }
                arrayList = this.this$0.destinationTasks;
                ArrayList<MoveDestinationTask> arrayList3 = arrayList;
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                for (MoveDestinationTask moveDestinationTask : arrayList3) {
                    this.this$0.runOnUi(new C5338x8911cb2b(moveDestinationTask, null, this));
                    arrayList4.add(moveDestinationTask.getDestination());
                }
                ArrayList arrayList5 = arrayList4;
                ((ArrayList) objectRef.element).addAll(arrayList5);
                arrayList2 = this.this$0.destinationTasks;
                arrayList2.clear();
                main = Dispatchers.getMain();
                c53392 = new C53392(objectRef, null);
                this.L$0 = coroutineScope;
                this.L$1 = currentDestinationTask;
                this.L$2 = objectRef;
                this.L$3 = arrayList5;
                this.label = 3;
                if (BuildersKt.withContext(main, c53392, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.this$0.destroy$module_robot_mirsdk_wrapper_release();
                return Unit.INSTANCE;
            }
            currentDestinationTask = (CurrentDestinationTask) this.L$1;
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
        }
        MoveByDestination moveByDestination2 = this.this$0;
        this.L$0 = coroutineScope;
        this.L$1 = currentDestinationTask;
        this.label = 2;
        if (moveByDestination2.genMoveReport(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ArrayList();
        if (currentDestinationTask != null) {
        }
        arrayList = this.this$0.destinationTasks;
        ArrayList<MoveDestinationTask> arrayList32 = arrayList;
        ArrayList arrayList42 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList32, 10));
        while (r4.hasNext()) {
        }
        ArrayList arrayList52 = arrayList42;
        ((ArrayList) objectRef2.element).addAll(arrayList52);
        arrayList2 = this.this$0.destinationTasks;
        arrayList2.clear();
        main = Dispatchers.getMain();
        c53392 = new C53392(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = currentDestinationTask;
        this.L$2 = objectRef2;
        this.L$3 = arrayList52;
        this.label = 3;
        if (BuildersKt.withContext(main, c53392, this) == coroutine_suspended) {
        }
        this.this$0.destroy$module_robot_mirsdk_wrapper_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveByDestination.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelAll$1$2", m3970f = "MoveByDestination.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelAll$1$2 */
    /* loaded from: classes6.dex */
    public static final class C53392 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $cancelT;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6556p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C53392(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$cancelT = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53392 c53392 = new C53392(this.$cancelT, completion);
            c53392.f6556p$ = (CoroutineScope) obj;
            return c53392;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53392) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = this.f6556p$;
            MoveByDestinationStateListener onMoveStateListener = MoveByDestination$cancelAll$1.this.this$0.getOnMoveStateListener();
            if (onMoveStateListener != null) {
                ArrayList<Destination> arrayList2 = (ArrayList) this.$cancelT.element;
                arrayList = MoveByDestination$cancelAll$1.this.this$0.destinationTasks;
                onMoveStateListener.onCancel(arrayList2, !arrayList.isEmpty());
            }
            MoveByDestinationStateListener onMoveStateListener2 = MoveByDestination$cancelAll$1.this.this$0.getOnMoveStateListener();
            if (onMoveStateListener2 == null) {
                return null;
            }
            onMoveStateListener2.onDone(null);
            return Unit.INSTANCE;
        }
    }
}
