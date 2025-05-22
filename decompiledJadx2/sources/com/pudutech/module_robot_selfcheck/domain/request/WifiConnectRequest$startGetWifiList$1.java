package com.pudutech.module_robot_selfcheck.domain.request;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: WifiConnectRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.domain.request.WifiConnectRequest$startGetWifiList$1", m3970f = "WifiConnectRequest.kt", m3971i = {0}, m3972l = {62}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class WifiConnectRequest$startGetWifiList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6744p$;
    final /* synthetic */ WifiConnectRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiConnectRequest$startGetWifiList$1(WifiConnectRequest wifiConnectRequest, Continuation continuation) {
        super(2, continuation);
        this.this$0 = wifiConnectRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        WifiConnectRequest$startGetWifiList$1 wifiConnectRequest$startGetWifiList$1 = new WifiConnectRequest$startGetWifiList$1(this.this$0, completion);
        wifiConnectRequest$startGetWifiList$1.f6744p$ = (CoroutineScope) obj;
        return wifiConnectRequest$startGetWifiList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WifiConnectRequest$startGetWifiList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002d -> B:5:0x0030). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L1c
            if (r1 != r2) goto L14
            java.lang.Object r1 = r5.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r5
            goto L30
        L14:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L1c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineScope r6 = r5.f6744p$
            r1 = r6
            r6 = r5
        L23:
            r3 = 2000(0x7d0, double:9.88E-321)
            r6.L$0 = r1
            r6.label = r2
            java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r6)
            if (r3 != r0) goto L30
            return r0
        L30:
            com.pudutech.module_robot_selfcheck.domain.request.WifiConnectRequest r3 = r6.this$0
            androidx.lifecycle.MutableLiveData r3 = r3.getWifiList()
            com.pudutech.module_robot_selfcheck.domain.request.WifiConnectRequest r4 = r6.this$0
            com.pudutech.disinfect.baselib.util.WifiUtils r4 = r4.getWifiUtils()
            if (r4 == 0) goto L43
            java.util.List r4 = r4.getScanList()
            goto L44
        L43:
            r4 = 0
        L44:
            r3.postValue(r4)
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pudutech.module_robot_selfcheck.domain.request.WifiConnectRequest$startGetWifiList$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
