package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
import java.util.List;
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

/* compiled from: AddVirtualViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.AddVirtualViewModel$checkVirtualWall$1", m3970f = "AddVirtualViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class AddVirtualViewModel$checkVirtualWall$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $list;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5259p$;
    final /* synthetic */ AddVirtualViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddVirtualViewModel$checkVirtualWall$1(AddVirtualViewModel addVirtualViewModel, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = addVirtualViewModel;
        this.$list = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AddVirtualViewModel$checkVirtualWall$1 addVirtualViewModel$checkVirtualWall$1 = new AddVirtualViewModel$checkVirtualWall$1(this.this$0, this.$list, completion);
        addVirtualViewModel$checkVirtualWall$1.f5259p$ = (CoroutineScope) obj;
        return addVirtualViewModel$checkVirtualWall$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AddVirtualViewModel$checkVirtualWall$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5259p$;
        boolean[] checkVirtualWall = LocateMappingManager.INSTANCE.checkVirtualWall(this.$list);
        Pdlog.m3273d(this.this$0.getTAG(), "checkVirtualWall " + checkVirtualWall);
        this.this$0.getCheckVirtualLiveData().postValue(checkVirtualWall);
        return Unit.INSTANCE;
    }
}
