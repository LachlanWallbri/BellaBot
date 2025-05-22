package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import java.util.concurrent.atomic.AtomicBoolean;
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

/* compiled from: PersonDetectService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.PersonDetectService$startDetect$1", m3970f = "PersonDetectService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class PersonDetectService$startDetect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $distance;
    final /* synthetic */ double $endDegree;
    final /* synthetic */ double $startDegree;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5564p$;
    final /* synthetic */ PersonDetectService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersonDetectService$startDetect$1(PersonDetectService personDetectService, double d, double d2, double d3, Continuation continuation) {
        super(2, continuation);
        this.this$0 = personDetectService;
        this.$startDegree = d;
        this.$endDegree = d2;
        this.$distance = d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PersonDetectService$startDetect$1 personDetectService$startDetect$1 = new PersonDetectService$startDetect$1(this.this$0, this.$startDegree, this.$endDegree, this.$distance, completion);
        personDetectService$startDetect$1.f5564p$ = (CoroutineScope) obj;
        return personDetectService$startDetect$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PersonDetectService$startDetect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        AtomicBoolean atomicBoolean;
        AIDLConnection aIDLConnection2;
        AIDLConnection aIDLConnection3;
        AIDLConnection aIDLConnection4;
        MirCoreInterface mirCoreInterface;
        MirCoreInterface mirCoreInterface2;
        Vector3d vector3d;
        MirCoreInterface mirCoreInterface3;
        String str;
        PersonDetectService$personDetectListener$1 personDetectService$personDetectListener$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5564p$;
        String tag = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("startDetect startDegree=");
        sb.append(this.$startDegree);
        sb.append(",endDegree=");
        sb.append(this.$endDegree);
        sb.append(",distance=");
        sb.append(this.$distance);
        sb.append(" coreService=");
        aIDLConnection = this.this$0.coreService;
        sb.append(aIDLConnection);
        Pdlog.m3273d(tag, sb.toString());
        atomicBoolean = this.this$0.isClosePersonDetect;
        atomicBoolean.set(false);
        aIDLConnection2 = this.this$0.coreService;
        if (aIDLConnection2 != null && (mirCoreInterface3 = (MirCoreInterface) aIDLConnection2.getInterface()) != null) {
            str = this.this$0.LISTENER_NAME;
            personDetectService$personDetectListener$1 = this.this$0.personDetectListener;
            mirCoreInterface3.addPersonListener(str, personDetectService$personDetectListener$1);
        }
        aIDLConnection3 = this.this$0.coreService;
        if (aIDLConnection3 != null && (mirCoreInterface2 = (MirCoreInterface) aIDLConnection3.getInterface()) != null) {
            vector3d = this.this$0.robotPose;
            mirCoreInterface2.setInitRobotPose(vector3d);
        }
        aIDLConnection4 = this.this$0.coreService;
        if (aIDLConnection4 != null && (mirCoreInterface = (MirCoreInterface) aIDLConnection4.getInterface()) != null) {
            mirCoreInterface.enablePersonDetectInRange(true, this.$startDegree, this.$endDegree, this.$distance);
        }
        Pdlog.m3273d(this.this$0.getTAG(), "startDetect end--------");
        return Unit.INSTANCE;
    }
}
