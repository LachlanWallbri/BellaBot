package com.pudutech.disinfect.baselib.maintenance;

import android.content.ComponentName;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.remotemaintenance.BuildConfig;
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
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$runRemoteMaintenanceApp$1", m3970f = "RemoteMaintenanceManager.kt", m3971i = {0, 0, 1, 1, 1, 1}, m3972l = {190, 204}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "isRunning", "$this$launch", "isRunning", "componentName", "launchIntent"}, m3975s = {"L$0", "Z$0", "L$0", "Z$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class RemoteMaintenanceManager$runRemoteMaintenanceApp$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5044p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteMaintenanceManager$runRemoteMaintenanceApp$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RemoteMaintenanceManager$runRemoteMaintenanceApp$1 remoteMaintenanceManager$runRemoteMaintenanceApp$1 = new RemoteMaintenanceManager$runRemoteMaintenanceApp$1(completion);
        remoteMaintenanceManager$runRemoteMaintenanceApp$1.f5044p$ = (CoroutineScope) obj;
        return remoteMaintenanceManager$runRemoteMaintenanceApp$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMaintenanceManager$runRemoteMaintenanceApp$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                boolean z = this.Z$0;
                ResultKt.throwOnFailure(obj);
                RemoteMaintenanceManager.INSTANCE.connect();
                return Unit.INSTANCE;
            }
            if (i == 2) {
                boolean z2 = this.Z$0;
                ResultKt.throwOnFailure(obj);
                RemoteMaintenanceManager.INSTANCE.connect();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5044p$;
        boolean appIsRunning = PackageUtil.INSTANCE.appIsRunning(BuildConfig.APPLICATION_ID);
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("远程维护助手");
        sb.append(appIsRunning ? "正在运行" : "未运行");
        objArr[0] = sb.toString();
        Pdlog.m3273d("RemoteMaintenanceManager", objArr);
        if (appIsRunning) {
            this.L$0 = coroutineScope;
            this.Z$0 = appIsRunning;
            this.label = 1;
            if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            RemoteMaintenanceManager.INSTANCE.connect();
            return Unit.INSTANCE;
        }
        Pdlog.m3273d("RemoteMaintenanceManager", "启动远程维护助手");
        ComponentName componentName = new ComponentName(BuildConfig.APPLICATION_ID, "com.pudutech.remotemaintenance.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addCategory("android.intent.category.DEFAULT");
        KtxKt.getAppContext().startActivity(intent);
        this.L$0 = coroutineScope;
        this.Z$0 = appIsRunning;
        this.L$1 = componentName;
        this.L$2 = intent;
        this.label = 2;
        if (DelayKt.delay(2000L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        RemoteMaintenanceManager.INSTANCE.connect();
        return Unit.INSTANCE;
    }
}
