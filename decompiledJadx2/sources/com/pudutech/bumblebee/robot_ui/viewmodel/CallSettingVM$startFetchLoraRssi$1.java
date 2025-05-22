package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.manager.LoRaConnectState;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LoRaSignalDialog;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$startFetchLoraRssi$1", m3970f = "CallSettingVM.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {436, 456}, m3973m = "invokeSuspend", m3974n = {"$this$launch", AUserTrack.UTKEY_START_TIME, "$this$launch", AUserTrack.UTKEY_START_TIME, "rssi"}, m3975s = {"L$0", "J$0", "L$0", "J$0", "L$1"})
/* loaded from: classes4.dex */
public final class CallSettingVM$startFetchLoraRssi$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4981p$;
    final /* synthetic */ CallSettingVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingVM$startFetchLoraRssi$1(CallSettingVM callSettingVM, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callSettingVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CallSettingVM$startFetchLoraRssi$1 callSettingVM$startFetchLoraRssi$1 = new CallSettingVM$startFetchLoraRssi$1(this.this$0, completion);
        callSettingVM$startFetchLoraRssi$1.f4981p$ = (CoroutineScope) obj;
        return callSettingVM$startFetchLoraRssi$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallSettingVM$startFetchLoraRssi$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00e3 -> B:7:0x0039). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CallSettingVM$startFetchLoraRssi$1 callSettingVM$startFetchLoraRssi$1;
        String str;
        MutableLiveData mutableLiveData;
        LoRaSignalDialog.SignType signType;
        Object obj2;
        MutableLiveData mutableLiveData2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4981p$;
        } else if (i == 1) {
            long j = this.J$0;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            long j2 = j;
            Object obj3 = coroutine_suspended;
            callSettingVM$startFetchLoraRssi$1 = this;
            Integer num = (Integer) obj;
            str = callSettingVM$startFetchLoraRssi$1.this$0.TAG;
            Pdlog.m3275i(str, "startFetchLoraRssi.value > " + num);
            if (!Intrinsics.areEqual(callSettingVM$startFetchLoraRssi$1.this$0.getLoraConnectStateLD().getValue(), LoRaConnectState.Login.INSTANCE)) {
                mutableLiveData2 = callSettingVM$startFetchLoraRssi$1.this$0._loraRssiLD;
                mutableLiveData2.postValue(LoRaSignalDialog.SignType.None);
            } else if (num != null && num.intValue() < 0) {
                mutableLiveData = callSettingVM$startFetchLoraRssi$1.this$0._loraRssiLD;
                if (num.intValue() <= -85) {
                    signType = num.intValue() > -95 ? LoRaSignalDialog.SignType.Middle : LoRaSignalDialog.SignType.Weak;
                } else {
                    signType = LoRaSignalDialog.SignType.Strong;
                }
                mutableLiveData.postValue(signType);
                long currentTimeMillis = 2000 - (System.currentTimeMillis() - j2);
                callSettingVM$startFetchLoraRssi$1.L$0 = coroutineScope2;
                callSettingVM$startFetchLoraRssi$1.J$0 = j2;
                callSettingVM$startFetchLoraRssi$1.L$1 = num;
                callSettingVM$startFetchLoraRssi$1.label = 2;
                if (DelayKt.delay(currentTimeMillis, callSettingVM$startFetchLoraRssi$1) == obj3) {
                    return obj3;
                }
            }
            coroutineScope = coroutineScope2;
            obj2 = obj3;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                long currentTimeMillis2 = System.currentTimeMillis();
                CallSettingVM$startFetchLoraRssi$1$rssi$1 callSettingVM$startFetchLoraRssi$1$rssi$1 = new CallSettingVM$startFetchLoraRssi$1$rssi$1(callSettingVM$startFetchLoraRssi$1, null);
                callSettingVM$startFetchLoraRssi$1.L$0 = coroutineScope;
                callSettingVM$startFetchLoraRssi$1.J$0 = currentTimeMillis2;
                callSettingVM$startFetchLoraRssi$1.label = 1;
                Object withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2000L, callSettingVM$startFetchLoraRssi$1$rssi$1, callSettingVM$startFetchLoraRssi$1);
                if (withTimeoutOrNull == obj2) {
                    return obj2;
                }
                Object obj4 = obj2;
                coroutineScope2 = coroutineScope;
                obj = withTimeoutOrNull;
                j2 = currentTimeMillis2;
                obj3 = obj4;
                Integer num2 = (Integer) obj;
                str = callSettingVM$startFetchLoraRssi$1.this$0.TAG;
                Pdlog.m3275i(str, "startFetchLoraRssi.value > " + num2);
                if (!Intrinsics.areEqual(callSettingVM$startFetchLoraRssi$1.this$0.getLoraConnectStateLD().getValue(), LoRaConnectState.Login.INSTANCE)) {
                }
                coroutineScope = coroutineScope2;
                obj2 = obj3;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j3 = this.J$0;
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
        }
        obj2 = coroutine_suspended;
        callSettingVM$startFetchLoraRssi$1 = this;
        if (CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
