package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByGroup.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByGroup$cancel$1", m3970f = "MoveByGroup.kt", m3971i = {0, 1, 2}, m3972l = {109, 110, 111}, m3973m = "invokeSuspend", m3974n = {"$this$runAsyn", "$this$runAsyn", "$this$runAsyn"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class MoveByGroup$cancel$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6575p$;
    final /* synthetic */ MoveByGroup this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByGroup$cancel$1(MoveByGroup moveByGroup, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByGroup;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByGroup$cancel$1 moveByGroup$cancel$1 = new MoveByGroup$cancel$1(this.this$0, completion);
        moveByGroup$cancel$1.f6575p$ = (CoroutineScope) obj;
        return moveByGroup$cancel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByGroup$cancel$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0069 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        MainCoroutineDispatcher main;
        C53431 c53431;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6575p$;
            MoveByGroup moveByGroup = this.this$0;
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (moveByGroup.cancelStatus(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
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
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                main = Dispatchers.getMain();
                c53431 = new C53431(null);
                this.L$0 = coroutineScope;
                this.label = 3;
                if (BuildersKt.withContext(main, c53431, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.this$0.destroy$module_robot_mirsdk_wrapper_release();
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        MoveByGroup moveByGroup2 = this.this$0;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (moveByGroup2.genMoveReport(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        main = Dispatchers.getMain();
        c53431 = new C53431(null);
        this.L$0 = coroutineScope;
        this.label = 3;
        if (BuildersKt.withContext(main, c53431, this) == coroutine_suspended) {
        }
        this.this$0.destroy$module_robot_mirsdk_wrapper_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveByGroup.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByGroup$cancel$1$1", m3970f = "MoveByGroup.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveByGroup$cancel$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6576p$;

        C53431(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53431 c53431 = new C53431(completion);
            c53431.f6576p$ = (CoroutineScope) obj;
            return c53431;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6576p$;
            MoveByGroupStateListener onMoveStateListener = MoveByGroup$cancel$1.this.this$0.getOnMoveStateListener();
            if (onMoveStateListener == null) {
                return null;
            }
            onMoveStateListener.onCancel();
            return Unit.INSTANCE;
        }
    }
}
