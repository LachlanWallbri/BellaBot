package com.pudutech.mirsdk.movetask;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$startMoveAction$2", m3970f = "ElevatorTask.kt", m3971i = {0, 0, 0}, m3972l = {180}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "wifiService", "lastSize"}, m3975s = {"L$0", "L$1", "I$0"})
/* loaded from: classes6.dex */
final class ElevatorTask$startMoveAction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6382p$;
    final /* synthetic */ ElevatorTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$startMoveAction$2(ElevatorTask elevatorTask, Continuation continuation) {
        super(2, continuation);
        this.this$0 = elevatorTask;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$startMoveAction$2 elevatorTask$startMoveAction$2 = new ElevatorTask$startMoveAction$2(this.this$0, completion);
        elevatorTask$startMoveAction$2.f6382p$ = (CoroutineScope) obj;
        return elevatorTask$startMoveAction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$startMoveAction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004d A[Catch: Exception -> 0x0067, TryCatch #0 {Exception -> 0x0067, blocks: (B:9:0x005e, B:10:0x0043, B:12:0x004d, B:16:0x006a, B:18:0x0095, B:21:0x00d4, B:22:0x00db), top: B:8:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006a A[Catch: Exception -> 0x0067, TryCatch #0 {Exception -> 0x0067, blocks: (B:9:0x005e, B:10:0x0043, B:12:0x004d, B:16:0x006a, B:18:0x0095, B:21:0x00d4, B:22:0x00db), top: B:8:0x005e }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x005b -> B:8:0x005e). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Exception e;
        ElevatorTask$startMoveAction$2 elevatorTask$startMoveAction$2;
        WifiManager wifiManager;
        int i;
        Object obj2;
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
        } catch (Exception e2) {
            e = e2;
            elevatorTask$startMoveAction$2 = this;
        }
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6382p$;
            Object systemService = ElevatorTask.access$getContext_$p(this.this$0).getSystemService("wifi");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
            }
            wifiManager = (WifiManager) systemService;
            wifiManager.startScan();
            i = -1;
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope2;
            elevatorTask$startMoveAction$2 = this;
            if (wifiManager.getScanResults().size() != i) {
            }
        } else if (i2 == 1) {
            int i3 = this.I$0;
            wifiManager = (WifiManager) this.L$1;
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            elevatorTask$startMoveAction$2 = this;
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope3;
            try {
                i = wifiManager.getScanResults().size();
            } catch (Exception e3) {
                e = e3;
                Pdlog.m3277w(elevatorTask$startMoveAction$2.this$0.TAG, "get Wifi SSID failed " + e.getMessage());
                return Unit.INSTANCE;
            }
            if (wifiManager.getScanResults().size() != i) {
                elevatorTask$startMoveAction$2.L$0 = coroutineScope;
                elevatorTask$startMoveAction$2.L$1 = wifiManager;
                elevatorTask$startMoveAction$2.I$0 = i;
                elevatorTask$startMoveAction$2.label = 1;
                if (DelayKt.delay(200L, elevatorTask$startMoveAction$2) == obj2) {
                    return obj2;
                }
                i = wifiManager.getScanResults().size();
                if (wifiManager.getScanResults().size() != i) {
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    ElevatorTask elevatorTask = elevatorTask$startMoveAction$2.this$0;
                    Intrinsics.checkExpressionValueIsNotNull(wifiInfo, "wifiInfo");
                    String ssid = wifiInfo.getSSID();
                    Intrinsics.checkExpressionValueIsNotNull(ssid, "wifiInfo.ssid");
                    elevatorTask.currentSSIDName = ssid;
                    ElevatorTask elevatorTask2 = elevatorTask$startMoveAction$2.this$0;
                    String ssid2 = wifiInfo.getSSID();
                    Intrinsics.checkExpressionValueIsNotNull(ssid2, "wifiInfo.ssid");
                    int length = ElevatorTask.access$getCurrentSSIDName$p(elevatorTask$startMoveAction$2.this$0).length() - 1;
                    if (ssid2 != null) {
                        String substring = ssid2.substring(1, length);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        elevatorTask2.currentSSID = substring;
                        Pdlog.m3273d(elevatorTask$startMoveAction$2.this$0.TAG, "current wifi when init " + ElevatorTask.access$getCurrentSSID$p(elevatorTask$startMoveAction$2.this$0) + " SSID " + ElevatorTask.access$getCurrentSSIDName$p(elevatorTask$startMoveAction$2.this$0));
                        return Unit.INSTANCE;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
