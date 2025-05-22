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
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.NewMapViewModel$checkFinishMappingMarkerVisible$1", m3970f = "NewMapViewModel.kt", m3971i = {0, 0, 0}, m3972l = {59}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "time", "isCheck"}, m3975s = {"L$0", "J$0", "L$1"})
/* loaded from: classes2.dex */
public final class NewMapViewModel$checkFinishMappingMarkerVisible$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5293p$;
    final /* synthetic */ NewMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewMapViewModel$checkFinishMappingMarkerVisible$1(NewMapViewModel newMapViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = newMapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NewMapViewModel$checkFinishMappingMarkerVisible$1 newMapViewModel$checkFinishMappingMarkerVisible$1 = new NewMapViewModel$checkFinishMappingMarkerVisible$1(this.this$0, completion);
        newMapViewModel$checkFinishMappingMarkerVisible$1.f5293p$ = (CoroutineScope) obj;
        return newMapViewModel$checkFinishMappingMarkerVisible$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NewMapViewModel$checkFinishMappingMarkerVisible$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0039  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00c5 -> B:5:0x00c8). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long j;
        CoroutineScope coroutineScope;
        NewMapViewModel$checkFinishMappingMarkerVisible$1 newMapViewModel$checkFinishMappingMarkerVisible$1;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5293p$;
            j = 0;
            this.this$0.setIdentify(true);
            coroutineScope = coroutineScope2;
            newMapViewModel$checkFinishMappingMarkerVisible$1 = this;
            if (newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.getIsIdentify()) {
            }
        } else if (i == 1) {
            j = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            newMapViewModel$checkFinishMappingMarkerVisible$1 = this;
            j += 500;
            if (newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.getIsIdentify()) {
                Boolean checkFinishMappingMarkerVisible = LocateMappingManager.INSTANCE.checkFinishMappingMarkerVisible();
                str = newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.TAG;
                Pdlog.m3273d(str, "checkFinishMappingMarkerVisible -- isCheck " + checkFinishMappingMarkerVisible + " --time " + j);
                if (j <= 5000 && Intrinsics.areEqual(checkFinishMappingMarkerVisible, Boxing.boxBoolean(true))) {
                    newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.closeCheck();
                    newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.finishMap();
                    return Unit.INSTANCE;
                }
                if (j == 5000) {
                    newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.getCheckMarkerLiveData().postValue(Boxing.boxBoolean(false));
                } else if (Intrinsics.areEqual(checkFinishMappingMarkerVisible, Boxing.boxBoolean(true))) {
                    newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.getCheckMarkerLiveData().postValue(Boxing.boxBoolean(true));
                    newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.finishMap();
                    newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.closeCheck();
                    return Unit.INSTANCE;
                }
                newMapViewModel$checkFinishMappingMarkerVisible$1.L$0 = coroutineScope;
                newMapViewModel$checkFinishMappingMarkerVisible$1.J$0 = j;
                newMapViewModel$checkFinishMappingMarkerVisible$1.L$1 = checkFinishMappingMarkerVisible;
                newMapViewModel$checkFinishMappingMarkerVisible$1.label = 1;
                if (DelayKt.delay(500L, newMapViewModel$checkFinishMappingMarkerVisible$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                j += 500;
                if (newMapViewModel$checkFinishMappingMarkerVisible$1.this$0.getIsIdentify()) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
