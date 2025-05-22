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
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$getRemoteInterface$1$setCoordinateOrientation$1", m3970f = "RemoteMaintenanceManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* renamed from: com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$getRemoteInterface$1$setCoordinateOrientation$1 */
/* loaded from: classes4.dex */
final class C4442x487fbab9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $p0;
    final /* synthetic */ double $p1;
    final /* synthetic */ double $p2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5039p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4442x487fbab9(double d, double d2, double d3, Continuation continuation) {
        super(2, continuation);
        this.$p0 = d;
        this.$p1 = d2;
        this.$p2 = d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4442x487fbab9 c4442x487fbab9 = new C4442x487fbab9(this.$p0, this.$p1, this.$p2, completion);
        c4442x487fbab9.f5039p$ = (CoroutineScope) obj;
        return c4442x487fbab9;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4442x487fbab9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5039p$;
        try {
            RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
            aIDLConnection = RemoteMaintenanceManager.remoteMaintenanceService;
            RemoteMaintenanceInterface remoteMaintenanceInterface = (RemoteMaintenanceInterface) aIDLConnection.getInterface();
            if (remoteMaintenanceInterface != null) {
                remoteMaintenanceInterface.setCoordinateOrientation(this.$p0, this.$p1, this.$p2);
            }
        } catch (Exception unused) {
        }
        return Unit.INSTANCE;
    }
}
