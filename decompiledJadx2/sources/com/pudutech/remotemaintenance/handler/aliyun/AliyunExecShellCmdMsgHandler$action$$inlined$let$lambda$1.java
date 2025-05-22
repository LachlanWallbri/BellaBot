package com.pudutech.remotemaintenance.handler.aliyun;

import android.util.Pair;
import com.alibaba.fastjson.JSONObject;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.aliyun.config.AliyunMsgType;
import com.pudutech.remotemaintenance.aliyun.config.MsgType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AliyunExecShellCmdMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/remotemaintenance/handler/aliyun/AliyunExecShellCmdMsgHandler$action$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $cmd$inlined;
    final /* synthetic */ String $command;
    final /* synthetic */ AliyunIoTManager $iotInterface$inlined;
    final /* synthetic */ boolean $isSu$inlined;
    final /* synthetic */ String $sessionId$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7091p$;
    final /* synthetic */ AliyunExecShellCmdMsgHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1(String str, Continuation continuation, AliyunExecShellCmdMsgHandler aliyunExecShellCmdMsgHandler, boolean z, String str2, String str3, AliyunIoTManager aliyunIoTManager) {
        super(2, continuation);
        this.$command = str;
        this.this$0 = aliyunExecShellCmdMsgHandler;
        this.$isSu$inlined = z;
        this.$sessionId$inlined = str2;
        this.$cmd$inlined = str3;
        this.$iotInterface$inlined = aliyunIoTManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1 aliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1 = new AliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1(this.$command, completion, this.this$0, this.$isSu$inlined, this.$sessionId$inlined, this.$cmd$inlined, this.$iotInterface$inlined);
        aliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1.f7091p$ = (CoroutineScope) obj;
        return aliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunExecShellCmdMsgHandler$action$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        Pair execCommand;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7091p$;
                z = AliyunExecShellCmdMsgHandler.isExecutingCmd;
                if (z) {
                    this.this$0.stopExecCmd();
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (DelayKt.delay(100L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AliyunExecShellCmdMsgHandler.isExecutingCmd = true;
            execCommand = this.this$0.execCommand(this.$command, this.$isSu$inlined);
            AliyunExecShellCmdMsgHandler.isExecutingCmd = false;
            Pdlog.m3273d(AliyunExecShellCmdMsgHandler.TAG, "result[" + execCommand + ']');
            AliyunIoTMsg aliyunIoTMsg = new AliyunIoTMsg();
            aliyunIoTMsg.setSessionId(this.$sessionId$inlined);
            aliyunIoTMsg.setType(AliyunMsgType.RRPC.getType());
            aliyunIoTMsg.setInstruct(MsgType.EXEC_SHELL_CMD.getType());
            Integer num = (Integer) execCommand.first;
            String str = (String) execCommand.second;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put((JSONObject) "ret", (String) num);
            jSONObject.put((JSONObject) "cmd", this.$cmd$inlined);
            jSONObject.put((JSONObject) "data", str);
            aliyunIoTMsg.setContent(jSONObject.toString());
            this.$iotInterface$inlined.sendMsg(aliyunIoTMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
