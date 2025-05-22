package com.pudutech.freeinstall_ui.viewmodel;

import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
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
/* compiled from: EditMapViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.freeinstall_ui.viewmodel.EditMapViewModel$reinitAlgoModules$1", m3970f = "EditMapViewModel.kt", m3971i = {0, 0}, m3972l = {351}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "it"}, m3975s = {"L$0", "I$2"})
/* loaded from: classes2.dex */
public final class EditMapViewModel$reinitAlgoModules$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5272p$;
    final /* synthetic */ EditMapViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditMapViewModel$reinitAlgoModules$1(EditMapViewModel editMapViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = editMapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EditMapViewModel$reinitAlgoModules$1 editMapViewModel$reinitAlgoModules$1 = new EditMapViewModel$reinitAlgoModules$1(this.this$0, completion);
        editMapViewModel$reinitAlgoModules$1.f5272p$ = (CoroutineScope) obj;
        return editMapViewModel$reinitAlgoModules$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EditMapViewModel$reinitAlgoModules$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0057 -> B:5:0x005a). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        CoroutineScope coroutineScope;
        EditMapViewModel$reinitAlgoModules$1 editMapViewModel$reinitAlgoModules$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 5;
            i2 = 0;
            coroutineScope = this.f5272p$;
            editMapViewModel$reinitAlgoModules$1 = this;
            if (i2 < i) {
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = this.I$2;
            i = this.I$1;
            i2 = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            editMapViewModel$reinitAlgoModules$1 = this;
            i2++;
            if (i2 < i) {
                int intValue = Boxing.boxInt(i2).intValue();
                if (editMapViewModel$reinitAlgoModules$1.this$0.getLoadLocateFinish()) {
                    RobotMapManager.INSTANCE.reinitAlgoModules();
                    return Unit.INSTANCE;
                }
                editMapViewModel$reinitAlgoModules$1.L$0 = coroutineScope;
                editMapViewModel$reinitAlgoModules$1.I$0 = i2;
                editMapViewModel$reinitAlgoModules$1.I$1 = i;
                editMapViewModel$reinitAlgoModules$1.I$2 = intValue;
                editMapViewModel$reinitAlgoModules$1.label = 1;
                if (DelayKt.delay(1000L, editMapViewModel$reinitAlgoModules$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i2++;
                if (i2 < i) {
                    editMapViewModel$reinitAlgoModules$1.this$0.getTimeOutLiveData().postValue(Boxing.boxBoolean(true));
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
