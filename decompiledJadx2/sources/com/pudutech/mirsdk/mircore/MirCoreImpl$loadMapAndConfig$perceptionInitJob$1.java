package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreImpl$loadMapAndConfig$perceptionInitJob$1", m3970f = "MirCoreImpl.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MirCoreImpl$loadMapAndConfig$perceptionInitJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $initModuleList;
    final /* synthetic */ String $pdmap;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6170p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirCoreImpl$loadMapAndConfig$perceptionInitJob$1(String str, List list, Continuation continuation) {
        super(2, continuation);
        this.$pdmap = str;
        this.$initModuleList = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirCoreImpl$loadMapAndConfig$perceptionInitJob$1 mirCoreImpl$loadMapAndConfig$perceptionInitJob$1 = new MirCoreImpl$loadMapAndConfig$perceptionInitJob$1(this.$pdmap, this.$initModuleList, completion);
        mirCoreImpl$loadMapAndConfig$perceptionInitJob$1.f6170p$ = (CoroutineScope) obj;
        return mirCoreImpl$loadMapAndConfig$perceptionInitJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirCoreImpl$loadMapAndConfig$perceptionInitJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MachineModel machineModel;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6170p$;
        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
        machineModel = MirCoreImpl.machineType;
        if (machineModel == MachineModel.BellaBot) {
            Perception.INSTANCE.setTopoTrackPath(MirCoreImpl.INSTANCE.passFilePathtoPerception(this.$pdmap));
        }
        this.$initModuleList.remove(MirCoreImpl.CoreInitModules.Perception);
        return Unit.INSTANCE;
    }
}
