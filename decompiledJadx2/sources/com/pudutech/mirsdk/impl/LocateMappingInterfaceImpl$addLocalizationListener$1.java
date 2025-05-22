package com.pudutech.mirsdk.impl;

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
import kotlinx.coroutines.DelayKt;

/* compiled from: LocateMappingInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.impl.LocateMappingInterfaceImpl$addLocalizationListener$1", m3970f = "LocateMappingInterfaceImpl.kt", m3971i = {0}, m3972l = {105}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class LocateMappingInterfaceImpl$addLocalizationListener$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6125p$;
    final /* synthetic */ LocateMappingInterfaceImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocateMappingInterfaceImpl$addLocalizationListener$1(LocateMappingInterfaceImpl locateMappingInterfaceImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = locateMappingInterfaceImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LocateMappingInterfaceImpl$addLocalizationListener$1 locateMappingInterfaceImpl$addLocalizationListener$1 = new LocateMappingInterfaceImpl$addLocalizationListener$1(this.this$0, completion);
        locateMappingInterfaceImpl$addLocalizationListener$1.f6125p$ = (CoroutineScope) obj;
        return locateMappingInterfaceImpl$addLocalizationListener$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LocateMappingInterfaceImpl$addLocalizationListener$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0035 -> B:5:0x0038). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        LocateMappingInterfaceImpl$addLocalizationListener$1 locateMappingInterfaceImpl$addLocalizationListener$1;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6125p$;
            locateMappingInterfaceImpl$addLocalizationListener$1 = this;
            z = locateMappingInterfaceImpl$addLocalizationListener$1.this$0.sendFlag;
            if (z) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            locateMappingInterfaceImpl$addLocalizationListener$1 = this;
            locateMappingInterfaceImpl$addLocalizationListener$1.this$0.sendRobotPose();
            z = locateMappingInterfaceImpl$addLocalizationListener$1.this$0.sendFlag;
            if (z) {
                locateMappingInterfaceImpl$addLocalizationListener$1.L$0 = coroutineScope;
                locateMappingInterfaceImpl$addLocalizationListener$1.label = 1;
                if (DelayKt.delay(300L, locateMappingInterfaceImpl$addLocalizationListener$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                locateMappingInterfaceImpl$addLocalizationListener$1.this$0.sendRobotPose();
                z = locateMappingInterfaceImpl$addLocalizationListener$1.this$0.sendFlag;
                if (z) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
