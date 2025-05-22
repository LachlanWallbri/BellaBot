package com.pudutech.disinfect.baselib.maintenance;

import android.app.Application;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$connect$1", m3970f = "RemoteMaintenanceManager.kt", m3971i = {0}, m3972l = {223}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class RemoteMaintenanceManager$connect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5038p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteMaintenanceManager$connect$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RemoteMaintenanceManager$connect$1 remoteMaintenanceManager$connect$1 = new RemoteMaintenanceManager$connect$1(completion);
        remoteMaintenanceManager$connect$1.f5038p$ = (CoroutineScope) obj;
        return remoteMaintenanceManager$connect$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMaintenanceManager$connect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5038p$;
                Pdlog.m3273d("RemoteMaintenanceManager", "RemoteMaintenanceService opening.");
                RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
                aIDLConnection = RemoteMaintenanceManager.remoteMaintenanceService;
                Application appContext = KtxKt.getAppContext();
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = AIDLConnection.connect$default(aIDLConnection, appContext, null, this, 2, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!((Boolean) obj).booleanValue()) {
            Pdlog.m3277w("RemoteMaintenanceManager", "RemoteMaintenanceService open failed.");
            return Unit.INSTANCE;
        }
        Pdlog.m3273d("RemoteMaintenanceManager", "RemoteMaintenanceService open successful.");
        RemoteMaintenanceManager remoteMaintenanceManager2 = RemoteMaintenanceManager.INSTANCE;
        aIDLConnection2 = RemoteMaintenanceManager.remoteMaintenanceService;
        RemoteMaintenanceInterface remoteMaintenanceInterface = (RemoteMaintenanceInterface) aIDLConnection2.getInterface();
        if (remoteMaintenanceInterface != null) {
            remoteMaintenanceInterface.init(new IRemoteMaintenanceListener.Stub() { // from class: com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$connect$1.1
                @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
                public void onConnecting() {
                    Pdlog.m3273d("RemoteMaintenanceManager", "RemoteMaintenanceService connecting.");
                }

                @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
                public void onConnected() {
                    Pdlog.m3273d("RemoteMaintenanceManager", "RemoteMaintenanceService connected.");
                    RemoteMaintenanceManager remoteMaintenanceManager3 = RemoteMaintenanceManager.INSTANCE;
                    RemoteMaintenanceManager.isConnection = true;
                }

                @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
                public void onConnectFailure(int errCode, String errMsg) {
                    Pdlog.m3273d("RemoteMaintenanceManager", "RemoteMaintenanceService connect failure. errCode[" + errCode + "], errMsg[" + errMsg + ']');
                    RemoteMaintenanceManager remoteMaintenanceManager3 = RemoteMaintenanceManager.INSTANCE;
                    RemoteMaintenanceManager.isConnection = false;
                }
            });
        }
        return Unit.INSTANCE;
    }
}
