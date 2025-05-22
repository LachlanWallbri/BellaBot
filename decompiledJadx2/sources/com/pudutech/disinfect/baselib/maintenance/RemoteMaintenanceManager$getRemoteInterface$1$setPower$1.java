package com.pudutech.disinfect.baselib.maintenance;

import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface;
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

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$getRemoteInterface$1$setPower$1", m3970f = "RemoteMaintenanceManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class RemoteMaintenanceManager$getRemoteInterface$1$setPower$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $p0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5041p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteMaintenanceManager$getRemoteInterface$1$setPower$1(int i, Continuation continuation) {
        super(2, continuation);
        this.$p0 = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RemoteMaintenanceManager$getRemoteInterface$1$setPower$1 remoteMaintenanceManager$getRemoteInterface$1$setPower$1 = new RemoteMaintenanceManager$getRemoteInterface$1$setPower$1(this.$p0, completion);
        remoteMaintenanceManager$getRemoteInterface$1$setPower$1.f5041p$ = (CoroutineScope) obj;
        return remoteMaintenanceManager$getRemoteInterface$1$setPower$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMaintenanceManager$getRemoteInterface$1$setPower$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5041p$;
        try {
            RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
            aIDLConnection = RemoteMaintenanceManager.remoteMaintenanceService;
            RemoteMaintenanceInterface remoteMaintenanceInterface = (RemoteMaintenanceInterface) aIDLConnection.getInterface();
            if (remoteMaintenanceInterface != null) {
                remoteMaintenanceInterface.setPower(this.$p0);
            }
        } catch (Exception unused) {
        }
        return Unit.INSTANCE;
    }
}
