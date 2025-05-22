package com.pudutech.remotemaintenance.aliyun;

import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.ReportTask;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.config.ConnectState;
import com.pudutech.remotemaintenance.listener.ConnectStateCallback;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AliyunIoTManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$callbackConnectState$1", m3970f = "AliyunIoTManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class AliyunIoTManager$callbackConnectState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $autoReconnect;
    final /* synthetic */ ConnectState $connectState;
    final /* synthetic */ int $errCode;
    final /* synthetic */ String $errMsg;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7085p$;
    final /* synthetic */ AliyunIoTManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliyunIoTManager$callbackConnectState$1(AliyunIoTManager aliyunIoTManager, ConnectState connectState, int i, String str, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliyunIoTManager;
        this.$connectState = connectState;
        this.$errCode = i;
        this.$errMsg = str;
        this.$autoReconnect = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliyunIoTManager$callbackConnectState$1 aliyunIoTManager$callbackConnectState$1 = new AliyunIoTManager$callbackConnectState$1(this.this$0, this.$connectState, this.$errCode, this.$errMsg, this.$autoReconnect, completion);
        aliyunIoTManager$callbackConnectState$1.f7085p$ = (CoroutineScope) obj;
        return aliyunIoTManager$callbackConnectState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliyunIoTManager$callbackConnectState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ConnectStateCallback connectStateCallback;
        ConnectStateCallback connectStateCallback2;
        ReportTask reportTask;
        ReportTask reportTask2;
        ConnectStateCallback connectStateCallback3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            int i = AliyunIoTManager.WhenMappings.$EnumSwitchMapping$0[this.$connectState.ordinal()];
            if (i == 1) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "正在连接");
                connectStateCallback = this.this$0.connectStateCallback;
                if (connectStateCallback != null) {
                    connectStateCallback.onConnecting();
                }
            } else if (i == 2) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "连接成功");
                connectStateCallback2 = this.this$0.connectStateCallback;
                if (connectStateCallback2 != null) {
                    connectStateCallback2.onConnected();
                }
                reportTask = this.this$0.reportTask;
                if (reportTask == null) {
                    this.this$0.reportTask = new ReportTask(this.this$0);
                }
                reportTask2 = this.this$0.reportTask;
                if (reportTask2 != null) {
                    reportTask2.start();
                }
            } else if (i == 3) {
                this.this$0.reportTask = (ReportTask) null;
                if (this.$errCode == -2) {
                    Pdlog.m3277w(AliyunIoTManager.TAG, "主动断开连接无需重连及回调连接失败状态");
                    return Unit.INSTANCE;
                }
                Pdlog.m3273d(AliyunIoTManager.TAG, "连接失败");
                connectStateCallback3 = this.this$0.connectStateCallback;
                if (connectStateCallback3 != null) {
                    connectStateCallback3.onConnectFailure(this.$errCode, this.$errMsg);
                }
                if (this.$autoReconnect) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C56371(null), 3, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AliyunIoTManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$callbackConnectState$1$1", m3970f = "AliyunIoTManager.kt", m3971i = {0}, m3972l = {222}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$callbackConnectState$1$1 */
    /* loaded from: classes.dex */
    public static final class C56371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7086p$;

        C56371(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56371 c56371 = new C56371(completion);
            c56371.f7086p$ = (CoroutineScope) obj;
            return c56371;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = this.f7086p$;
                this.label = 1;
                if (DelayKt.delay(8000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AliyunIoTManager$callbackConnectState$1.this.this$0.reconnect(true);
            return Unit.INSTANCE;
        }
    }
}
