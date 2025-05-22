package com.pudutech.robot.module.openapi;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.FileUtil;
import com.pudutech.robot.module.openapi.remoteapi.RobotGetCurrentMapProvider;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.resp.RespRobotMapBody;
import java.util.ArrayList;
import java.util.Iterator;
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

/* compiled from: RobotOpenApiManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.openapi.RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10", m3970f = "RobotOpenApiManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MsgContext $msgContext;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7190p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10(MsgContext msgContext, Continuation continuation) {
        super(2, continuation);
        this.$msgContext = msgContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10 robotOpenApiManager$onOpenSdkEventListener$1$onEvent$10 = new RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10(this.$msgContext, completion);
        robotOpenApiManager$onOpenSdkEventListener$1$onEvent$10.f7190p$ = (CoroutineScope) obj;
        return robotOpenApiManager$onOpenSdkEventListener$1$onEvent$10;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenApiManager$onOpenSdkEventListener$1$onEvent$10) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7190p$;
        try {
            ArrayList arrayList = (ArrayList) RobotOpenApiManager.access$getRemoteApiProvidersMap$p(RobotOpenApiManager.INSTANCE).get(RobotGetCurrentMapProvider.class.getName());
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                Intrinsics.checkExpressionValueIsNotNull(it, "it.iterator()");
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.openapi.remoteapi.RobotGetCurrentMapProvider");
                    }
                    String compress = FileUtil.INSTANCE.compress(((RobotGetCurrentMapProvider) next).getCurrentMap());
                    RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
                    MsgContext msgContext = this.$msgContext;
                    if (compress == null) {
                        compress = "";
                    }
                    RobotOpenSdk.responseMsg$default(robotOpenSdk, msgContext, new RespRobotMapBody(compress), null, 4, null);
                    if (Boxing.boxBoolean(true).booleanValue()) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            RobotOpenApiManager robotOpenApiManager = RobotOpenApiManager.INSTANCE;
            str = RobotOpenApiManager.TAG;
            Pdlog.m3274e(str, "replayMapData : " + Log.getStackTraceString(e));
            RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, this.$msgContext, new RespRobotMapBody(""), null, 4, null);
        }
        return Unit.INSTANCE;
    }
}
