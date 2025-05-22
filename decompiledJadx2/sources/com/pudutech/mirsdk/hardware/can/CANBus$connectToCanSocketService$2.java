package com.pudutech.mirsdk.hardware.can;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.util.Log;
import com.pudutech.base.Pdlog;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$connectToCanSocketService$2", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {657}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class CANBus$connectToCanSocketService$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ long $current_time;
    final /* synthetic */ LocalSocket $socket;
    final /* synthetic */ boolean $use_can_service_usb;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6023p$;
    final /* synthetic */ CANBus this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$connectToCanSocketService$2(CANBus cANBus, LocalSocket localSocket, boolean z, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
        this.$socket = localSocket;
        this.$use_can_service_usb = z;
        this.$current_time = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$connectToCanSocketService$2 cANBus$connectToCanSocketService$2 = new CANBus$connectToCanSocketService$2(this.this$0, this.$socket, this.$use_can_service_usb, this.$current_time, completion);
        cANBus$connectToCanSocketService$2.f6023p$ = (CoroutineScope) obj;
        return cANBus$connectToCanSocketService$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((CANBus$connectToCanSocketService$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6023p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            try {
                LocalSocket localSocket = this.$socket;
                StringBuilder sb = new StringBuilder();
                sb.append("/pudu/can_service");
                sb.append(this.$use_can_service_usb ? Boxing.boxLong(this.$current_time) : "");
                localSocket.connect(new LocalSocketAddress(sb.toString()));
                this.this$0.socketOutputStream = this.$socket.getOutputStream();
                this.this$0.socketInputStream = this.$socket.getInputStream();
                str2 = this.this$0.TAG;
                Pdlog.m3273d(str2, "connect succeed");
                this.this$0.isConnect = true;
                break;
            } catch (IOException e) {
                str = this.this$0.TAG;
                Pdlog.m3274e(str, "connect fail", Log.getStackTraceString(e));
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Boxing.boxBoolean(true);
    }
}
