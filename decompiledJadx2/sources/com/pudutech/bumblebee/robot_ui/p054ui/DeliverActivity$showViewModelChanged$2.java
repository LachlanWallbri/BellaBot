package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.bumblebee.presenter.delivery_task.ViewModel;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BluetoothHelper;
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
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showViewModelChanged$2", m3970f = "DeliverActivity.kt", m3971i = {0}, m3972l = {1473}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
public final class DeliverActivity$showViewModelChanged$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ViewModel $model;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4895p$;
    final /* synthetic */ DeliverActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverActivity$showViewModelChanged$2(DeliverActivity deliverActivity, ViewModel viewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deliverActivity;
        this.$model = viewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DeliverActivity$showViewModelChanged$2 deliverActivity$showViewModelChanged$2 = new DeliverActivity$showViewModelChanged$2(this.this$0, this.$model, completion);
        deliverActivity$showViewModelChanged$2.f4895p$ = (CoroutineScope) obj;
        return deliverActivity$showViewModelChanged$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeliverActivity$showViewModelChanged$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DeliverActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showViewModelChanged$2$1", m3970f = "DeliverActivity.kt", m3971i = {0}, m3972l = {1474}, m3973m = "invokeSuspend", m3974n = {"$this$withContext"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showViewModelChanged$2$1 */
    /* loaded from: classes3.dex */
    public static final class C42861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4896p$;

        C42861(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C42861 c42861 = new C42861(completion);
            c42861.f4896p$ = (CoroutineScope) obj;
            return c42861;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C42861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            BluetoothHelper mBluetoothHelper;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4896p$;
                mBluetoothHelper = DeliverActivity$showViewModelChanged$2.this.this$0.getMBluetoothHelper();
                String destination = DeliverActivity$showViewModelChanged$2.this.$model.getDestination();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (mBluetoothHelper.connect(destination, this) == coroutine_suspended) {
                    return coroutine_suspended;
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4895p$;
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C42861 c42861 = new C42861(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(io2, c42861, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.showDeliverArrivedStatus(this.$model);
        return Unit.INSTANCE;
    }
}
