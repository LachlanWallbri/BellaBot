package com.pudutech.mirsdk.charge;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.charge.ChargeFinder;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import java.util.Map;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChargeFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeFinder$doCheckPileTask$2", m3970f = "ChargeFinder.kt", m3971i = {0, 0, 1, 1}, m3972l = {192, 208}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "count", "$this$launch", "count"}, m3975s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes5.dex */
public final class ChargeFinder$doCheckPileTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5734p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeFinder$doCheckPileTask$2(String str, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeFinder$doCheckPileTask$2 chargeFinder$doCheckPileTask$2 = new ChargeFinder$doCheckPileTask$2(this.$mac, completion);
        chargeFinder$doCheckPileTask$2.f5734p$ = (CoroutineScope) obj;
        return chargeFinder$doCheckPileTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeFinder$doCheckPileTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0059 -> B:13:0x005c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        int i;
        ChargeFinder$doCheckPileTask$2 chargeFinder$doCheckPileTask$2;
        boolean z;
        ChargingPile chargingPile;
        Map map;
        Map map2;
        String str;
        String str2;
        MoveAction moveAction;
        Map map3;
        boolean z2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5734p$;
            i = 0;
            chargeFinder$doCheckPileTask$2 = this;
            if (i < 5) {
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                int i3 = this.I$0;
                ResultKt.throwOnFailure(obj);
                ChargeFinder.INSTANCE.startScanNearDevice();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        i = this.I$0;
        coroutineScope = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        chargeFinder$doCheckPileTask$2 = this;
        i++;
        if (i < 5 && CoroutineScopeKt.isActive(coroutineScope)) {
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            z2 = ChargeFinder.isRecCheckMessage;
            if (!z2) {
                ChargeMessageCenter.INSTANCE.sendCheckPile(chargeFinder$doCheckPileTask$2.$mac);
                chargeFinder$doCheckPileTask$2.L$0 = coroutineScope;
                chargeFinder$doCheckPileTask$2.I$0 = i;
                chargeFinder$doCheckPileTask$2.label = 1;
                if (DelayKt.delay(500L, chargeFinder$doCheckPileTask$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i++;
                if (i < 5) {
                    ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
                    z2 = ChargeFinder.isRecCheckMessage;
                    if (!z2) {
                    }
                }
            }
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
            z = ChargeFinder.isRecCheckMessage;
            if (!z) {
                ChargeFinder chargeFinder4 = ChargeFinder.INSTANCE;
                chargingPile = ChargeFinder.nearDevice;
                if (chargingPile != null) {
                    ChargeFinder chargeFinder5 = ChargeFinder.INSTANCE;
                    map3 = ChargeFinder.filterList;
                    map3.put(chargeFinder$doCheckPileTask$2.$mac, chargingPile);
                }
                ChargeFinder chargeFinder6 = ChargeFinder.INSTANCE;
                ChargeFinder.nearDevice = (ChargingPile) null;
                ChargeFinder chargeFinder7 = ChargeFinder.INSTANCE;
                map = ChargeFinder.filterList;
                int size = map.size();
                ChargeFinder chargeFinder8 = ChargeFinder.INSTANCE;
                map2 = ChargeFinder.checkPileList;
                if (size >= map2.size()) {
                    ChargeFinder chargeFinder9 = ChargeFinder.INSTANCE;
                    str2 = ChargeFinder.TAG;
                    Pdlog.m3273d(str2, "doCheckPileTask is over but not found device");
                    ChargeFinder chargeFinder10 = ChargeFinder.INSTANCE;
                    ChargeFinder.foundStep = ChargeFinder.FoundChargeStep.Idle;
                    ChargeFinder chargeFinder11 = ChargeFinder.INSTANCE;
                    moveAction = ChargeFinder.moveAction;
                    if (moveAction != null) {
                        moveAction.notifyBTChargePileArrivedState(false);
                    }
                    ChargeFinder.INSTANCE.disconnectChargePile();
                } else {
                    ChargeFinder chargeFinder12 = ChargeFinder.INSTANCE;
                    str = ChargeFinder.TAG;
                    Pdlog.m3273d(str, "doCheckPileTask found next device");
                    ChargeFinder.INSTANCE.disconnectChargePile();
                    chargeFinder$doCheckPileTask$2.L$0 = coroutineScope;
                    chargeFinder$doCheckPileTask$2.I$0 = i;
                    chargeFinder$doCheckPileTask$2.label = 2;
                    if (DelayKt.delay(200L, chargeFinder$doCheckPileTask$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ChargeFinder.INSTANCE.startScanNearDevice();
                }
            }
        }
        return Unit.INSTANCE;
    }
}
