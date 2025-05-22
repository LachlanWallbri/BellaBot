package com.pudutech.peanut.robot_ui.p063ui.wifi;

import android.net.wifi.ScanResult;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.peanut.robot_ui.util.WifiUtils;
import java.util.List;
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

/* compiled from: WifiConnectViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.ui.wifi.WifiConnectViewModel$startGetWifiList$1", m3970f = "WifiConnectViewModel.kt", m3971i = {0, 1}, m3972l = {65, 67}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
final class WifiConnectViewModel$startGetWifiList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7056p$;
    final /* synthetic */ WifiConnectViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiConnectViewModel$startGetWifiList$1(WifiConnectViewModel wifiConnectViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = wifiConnectViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        WifiConnectViewModel$startGetWifiList$1 wifiConnectViewModel$startGetWifiList$1 = new WifiConnectViewModel$startGetWifiList$1(this.this$0, completion);
        wifiConnectViewModel$startGetWifiList$1.f7056p$ = (CoroutineScope) obj;
        return wifiConnectViewModel$startGetWifiList$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WifiConnectViewModel$startGetWifiList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003f A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0064 -> B:6:0x0016). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope;
        WifiConnectViewModel$startGetWifiList$1 wifiConnectViewModel$startGetWifiList$1;
        CoroutineScope coroutineScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            coroutineScope = this.f7056p$;
            wifiConnectViewModel$startGetWifiList$1 = this;
            wifiConnectViewModel$startGetWifiList$1.L$0 = coroutineScope;
            wifiConnectViewModel$startGetWifiList$1.label = 1;
            if (DelayKt.delay(1000L, wifiConnectViewModel$startGetWifiList$1) != obj2) {
            }
        } else if (i == 1) {
            coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            wifiConnectViewModel$startGetWifiList$1 = this;
            MutableLiveData<List<ScanResult>> wifiList = wifiConnectViewModel$startGetWifiList$1.this$0.getWifiList();
            WifiUtils wifiUtils = wifiConnectViewModel$startGetWifiList$1.this$0.getWifiUtils();
            wifiList.postValue(wifiUtils == null ? wifiUtils.getScanList() : null);
            wifiConnectViewModel$startGetWifiList$1.L$0 = coroutineScope2;
            wifiConnectViewModel$startGetWifiList$1.label = 2;
            if (DelayKt.delay(2000L, wifiConnectViewModel$startGetWifiList$1) == coroutine_suspended) {
            }
            CoroutineScope coroutineScope3 = coroutineScope2;
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope3;
            wifiConnectViewModel$startGetWifiList$1.L$0 = coroutineScope;
            wifiConnectViewModel$startGetWifiList$1.label = 1;
            if (DelayKt.delay(1000L, wifiConnectViewModel$startGetWifiList$1) != obj2) {
            }
        } else if (i == 2) {
            coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            wifiConnectViewModel$startGetWifiList$1 = this;
            CoroutineScope coroutineScope32 = coroutineScope2;
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope32;
            wifiConnectViewModel$startGetWifiList$1.L$0 = coroutineScope;
            wifiConnectViewModel$startGetWifiList$1.label = 1;
            if (DelayKt.delay(1000L, wifiConnectViewModel$startGetWifiList$1) != obj2) {
                return obj2;
            }
            Object obj3 = obj2;
            coroutineScope2 = coroutineScope;
            coroutine_suspended = obj3;
            MutableLiveData<List<ScanResult>> wifiList2 = wifiConnectViewModel$startGetWifiList$1.this$0.getWifiList();
            WifiUtils wifiUtils2 = wifiConnectViewModel$startGetWifiList$1.this$0.getWifiUtils();
            wifiList2.postValue(wifiUtils2 == null ? wifiUtils2.getScanList() : null);
            wifiConnectViewModel$startGetWifiList$1.L$0 = coroutineScope2;
            wifiConnectViewModel$startGetWifiList$1.label = 2;
            if (DelayKt.delay(2000L, wifiConnectViewModel$startGetWifiList$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            CoroutineScope coroutineScope322 = coroutineScope2;
            obj2 = coroutine_suspended;
            coroutineScope = coroutineScope322;
            wifiConnectViewModel$startGetWifiList$1.L$0 = coroutineScope;
            wifiConnectViewModel$startGetWifiList$1.label = 1;
            if (DelayKt.delay(1000L, wifiConnectViewModel$startGetWifiList$1) != obj2) {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
