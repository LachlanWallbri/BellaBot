package com.pudutech.schedulerlib.connection;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$checkWifi$1", m3970f = "MulticastConnection.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class MulticastConnection$checkWifi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7434p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MulticastConnection$checkWifi$1(MulticastConnection multicastConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$checkWifi$1 multicastConnection$checkWifi$1 = new MulticastConnection$checkWifi$1(this.this$0, completion);
        multicastConnection$checkWifi$1.f7434p$ = (CoroutineScope) obj;
        return multicastConnection$checkWifi$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MulticastConnection$checkWifi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Object systemService = MulticastConnection.access$getApplication$p(this.this$0).getSystemService("wifi");
            if (systemService != null) {
                WifiManager wifiManager = (WifiManager) systemService;
                this.this$0.localIp = "0.0.0.0";
                boolean z = true;
                if (!wifiManager.isWifiEnabled()) {
                    str4 = this.this$0.TAG;
                    Pdlog.m3273d(str4, "WIFI not enabled, Please check");
                } else {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    Intrinsics.checkExpressionValueIsNotNull(connectionInfo, "mWifiManager.connectionInfo");
                    int ipAddress = connectionInfo.getIpAddress();
                    if (ipAddress == 0) {
                        str3 = this.this$0.TAG;
                        Pdlog.m3273d(str3, "wifi not login, get ip is 0.0.0.0");
                    } else {
                        MulticastConnection multicastConnection = this.this$0;
                        String str5 = (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
                        Intrinsics.checkExpressionValueIsNotNull(str5, "StringBuilder().append(i…              .toString()");
                        multicastConnection.localIp = str5;
                        str = this.this$0.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("host ip ");
                        str2 = this.this$0.localIp;
                        sb.append(str2);
                        Pdlog.m3273d(str, sb.toString());
                        return Boxing.boxBoolean(z);
                    }
                }
                z = false;
                return Boxing.boxBoolean(z);
            }
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
