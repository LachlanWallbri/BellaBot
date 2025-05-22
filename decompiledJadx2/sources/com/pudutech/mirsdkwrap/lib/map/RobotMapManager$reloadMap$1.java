package com.pudutech.mirsdkwrap.lib.map;

import com.google.gson.Gson;
import com.pudutech.mirsdk.aidl.SDKInterface;
import java.util.HashMap;
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

/* compiled from: RobotMapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.map.RobotMapManager$reloadMap$1", m3970f = "RobotMapManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class RobotMapManager$reloadMap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    final /* synthetic */ Ref.ObjectRef $realFloor;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6525p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RobotMapManager$reloadMap$1(Ref.ObjectRef objectRef, String str, Continuation continuation) {
        super(2, continuation);
        this.$realFloor = objectRef;
        this.$name = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMapManager$reloadMap$1 robotMapManager$reloadMap$1 = new RobotMapManager$reloadMap$1(this.$realFloor, this.$name, completion);
        robotMapManager$reloadMap$1.f6525p$ = (CoroutineScope) obj;
        return robotMapManager$reloadMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMapManager$reloadMap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SDKInterface sDKInterface;
        Gson gson;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6525p$;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = hashMap;
        hashMap2.put("floor", (String) this.$realFloor.element);
        hashMap2.put("name", this.$name);
        RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
        sDKInterface = RobotMapManager.mirSdk;
        if (sDKInterface != null) {
            RobotMapManager robotMapManager2 = RobotMapManager.INSTANCE;
            gson = RobotMapManager.gson;
            sDKInterface.reloadUseMap(gson.toJson(hashMap));
        }
        return Unit.INSTANCE;
    }
}
