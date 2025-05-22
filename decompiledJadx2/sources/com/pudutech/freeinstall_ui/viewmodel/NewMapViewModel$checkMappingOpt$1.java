package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_wrapper.LocateMappingManager;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NewMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.NewMapViewModel$checkMappingOpt$1", m3970f = "NewMapViewModel.kt", m3971i = {0, 0, 0}, m3972l = {106}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "finish", "process"}, m3975s = {"L$0", "I$0", "L$1"})
/* loaded from: classes2.dex */
public final class NewMapViewModel$checkMappingOpt$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5294p$;
    final /* synthetic */ NewMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewMapViewModel$checkMappingOpt$1(NewMapViewModel newMapViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = newMapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NewMapViewModel$checkMappingOpt$1 newMapViewModel$checkMappingOpt$1 = new NewMapViewModel$checkMappingOpt$1(this.this$0, completion);
        newMapViewModel$checkMappingOpt$1.f5294p$ = (CoroutineScope) obj;
        return newMapViewModel$checkMappingOpt$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NewMapViewModel$checkMappingOpt$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        int i;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5294p$;
            i = 0;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (i == 0) {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "setMapSuccess");
            Boolean checkMappingOpt = LocateMappingManager.INSTANCE.checkMappingOpt();
            if (checkMappingOpt != null && checkMappingOpt.booleanValue()) {
                str2 = this.this$0.TAG;
                Pdlog.m3273d(str2, "setMapSuccess---process" + checkMappingOpt);
                this.this$0.getMapProcessLiveData().postValue(Boxing.boxBoolean(true));
                i = 1;
            } else {
                this.L$0 = coroutineScope;
                this.I$0 = i;
                this.L$1 = checkMappingOpt;
                this.label = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
