package com.pudutech.robot.opensdk;

import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import java.util.ArrayList;
import java.util.Iterator;
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
/* compiled from: RobotOpenSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.opensdk.RobotOpenSdk$setIotEnable$1", m3970f = "RobotOpenSdk.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
public final class RobotOpenSdk$setIotEnable$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $b */
    final /* synthetic */ boolean f7241$b;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7242p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenSdk$setIotEnable$1(boolean z, Continuation continuation) {
        super(2, continuation);
        this.f7241$b = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenSdk$setIotEnable$1 robotOpenSdk$setIotEnable$1 = new RobotOpenSdk$setIotEnable$1(this.f7241$b, completion);
        robotOpenSdk$setIotEnable$1.f7242p$ = (CoroutineScope) obj;
        return robotOpenSdk$setIotEnable$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenSdk$setIotEnable$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ArrayList arrayList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7242p$;
        RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
        str = RobotOpenSdk.TAG;
        Pdlog.m3273d(str, "notifyConnectState: " + RobotOpenSdk.INSTANCE.getConnectState());
        RobotOpenSdk robotOpenSdk2 = RobotOpenSdk.INSTANCE;
        arrayList = RobotOpenSdk.connectStateListeners;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((IRemoteConnectStateListener) it.next()).onConnectState(this.f7241$b ? RobotOpenSdk.INSTANCE.getConnectState() : RemoteConnectState.DISCONNECTED);
        }
        return Unit.INSTANCE;
    }
}
