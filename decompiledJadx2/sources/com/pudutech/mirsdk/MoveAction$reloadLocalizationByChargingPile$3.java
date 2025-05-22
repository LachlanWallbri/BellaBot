package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$reloadLocalizationByChargingPile$3", m3970f = "MoveAction.kt", m3971i = {0}, m3972l = {1901}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class MoveAction$reloadLocalizationByChargingPile$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $isLoadMap;
    final /* synthetic */ ChargingPileInfo $pile;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5548p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$reloadLocalizationByChargingPile$3(MoveAction moveAction, Ref.BooleanRef booleanRef, ChargingPileInfo chargingPileInfo, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$isLoadMap = booleanRef;
        this.$pile = chargingPileInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$reloadLocalizationByChargingPile$3 moveAction$reloadLocalizationByChargingPile$3 = new MoveAction$reloadLocalizationByChargingPile$3(this.this$0, this.$isLoadMap, this.$pile, completion);
        moveAction$reloadLocalizationByChargingPile$3.f5548p$ = (CoroutineScope) obj;
        return moveAction$reloadLocalizationByChargingPile$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$reloadLocalizationByChargingPile$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        AIDLConnection aIDLConnection;
        LocalizationInterface localizer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5548p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (this.$isLoadMap.element && CoroutineScopeKt.isActive(coroutineScope)) {
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(50L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add("charging_pile");
        double x = this.$pile.getPose().getX() + (Math.cos(this.$pile.getPose().getZ()) * 0.39d);
        double y = this.$pile.getPose().getY() + (Math.sin(this.$pile.getPose().getZ()) * 0.39d);
        double z = this.$pile.getPose().getZ() >= ((double) 0) ? this.$pile.getPose().getZ() - 3.141592653589793d : this.$pile.getPose().getZ();
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "reloadLocalizationByChargingPile x:" + x + " y:" + y + " z:" + z);
        arrayList2.add(new Vector3d(x, y, z));
        aIDLConnection = this.this$0.coreService;
        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
        if (mirCoreInterface != null && (localizer = mirCoreInterface.getLocalizer()) != null) {
            localizer.relocalizationByPoints(arrayList, arrayList2);
        }
        return Unit.INSTANCE;
    }
}
