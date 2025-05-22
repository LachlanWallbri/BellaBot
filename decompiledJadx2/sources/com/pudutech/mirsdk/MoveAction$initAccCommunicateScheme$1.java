package com.pudutech.mirsdk;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.map.elements.AccessControlPoint;
import com.pudutech.mirsdk.movetask.AccessControlTask;
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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$initAccCommunicateScheme$1", m3970f = "MoveAction.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class MoveAction$initAccCommunicateScheme$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5537p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$initAccCommunicateScheme$1(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$initAccCommunicateScheme$1 moveAction$initAccCommunicateScheme$1 = new MoveAction$initAccCommunicateScheme$1(this.this$0, completion);
        moveAction$initAccCommunicateScheme$1.f5537p$ = (CoroutineScope) obj;
        return moveAction$initAccCommunicateScheme$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$initAccCommunicateScheme$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        AccessControlServer accessControlServer;
        String str2;
        List list;
        List list2;
        AccessControlTask accessControlTask;
        List<AccessControlPoint> list3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5537p$;
        String string = SDKConfig.INSTANCE.getPreferences().getString("access_control_server", AccessControlServer.XinYiLian.name());
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "last select accessServer is " + string + ' ');
        AccessControlTask.Companion companion = AccessControlTask.INSTANCE;
        if (Intrinsics.areEqual(string, AccessControlServer.XinYiLian.name())) {
            accessControlServer = AccessControlServer.XinYiLian;
        } else if (Intrinsics.areEqual(string, AccessControlServer.CommCat.name())) {
            accessControlServer = AccessControlServer.CommCat;
        } else if (Intrinsics.areEqual(string, AccessControlServer.Lora.name())) {
            accessControlServer = AccessControlServer.Lora;
        } else if (Intrinsics.areEqual(string, AccessControlServer.ROS2.name())) {
            accessControlServer = AccessControlServer.ROS2;
        } else if (Intrinsics.areEqual(string, AccessControlServer.YouDian.name())) {
            accessControlServer = AccessControlServer.YouDian;
        } else {
            accessControlServer = Intrinsics.areEqual(string, AccessControlServer.YouDianTest.name()) ? AccessControlServer.YouDianTest : AccessControlServer.XinYiLian;
        }
        companion.setAccessConnectionType(accessControlServer);
        str2 = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("current map has access control ");
        list = this.this$0.accessControlPoints;
        sb.append(list.size());
        sb.append(" acc server ");
        sb.append(string);
        Pdlog.m3273d(str2, sb.toString());
        list2 = this.this$0.accessControlPoints;
        if (!list2.isEmpty()) {
            accessControlTask = this.this$0.accessControlTask;
            Context processContext = SDKConfig.INSTANCE.getProcessContext();
            list3 = this.this$0.accessControlPoints;
            accessControlTask.initClient(processContext, list3);
        }
        return Unit.INSTANCE;
    }
}
