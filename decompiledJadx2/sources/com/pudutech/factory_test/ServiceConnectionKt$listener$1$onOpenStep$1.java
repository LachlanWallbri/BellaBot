package com.pudutech.factory_test;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
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

/* compiled from: ServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.ServiceConnectionKt$listener$1$onOpenStep$1", m3970f = "ServiceConnection.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
final class ServiceConnectionKt$listener$1$onOpenStep$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5160p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionKt$listener$1$onOpenStep$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ServiceConnectionKt$listener$1$onOpenStep$1 serviceConnectionKt$listener$1$onOpenStep$1 = new ServiceConnectionKt$listener$1$onOpenStep$1(completion);
        serviceConnectionKt$listener$1$onOpenStep$1.f5160p$ = (CoroutineScope) obj;
        return serviceConnectionKt$listener$1$onOpenStep$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ServiceConnectionKt$listener$1$onOpenStep$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        boolean z;
        boolean z2;
        String str2;
        MachineInfo machineInfo;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ServiceConnectionKt.isHdInterfaceInitDone = true;
        str = ServiceConnectionKt.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        sb.append((hdInterface == null || (machineInfo = hdInterface.getMachineInfo()) == null) ? null : machineInfo.getProductType());
        sb.append(' ');
        sb.append(ServiceConnectionKt.getHdInterface());
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        z = ServiceConnectionKt.isHdInterfaceInitDone;
        if (z) {
            z2 = ServiceConnectionKt.isRbServiceConnected;
            if (z2) {
                str2 = ServiceConnectionKt.TAG;
                Pdlog.m3275i(str2, "open " + ServiceConnectionKt.getRbInterface());
                RobotInterface rbInterface = ServiceConnectionKt.getRbInterface();
                if (rbInterface != null) {
                    rbInterface.open();
                }
            }
        }
        return Unit.INSTANCE;
    }
}
