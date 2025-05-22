package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.single_test.WifiTestActivity2;
import com.pudutech.factory_test.test_pack.WifiUtil;
import java.net.DatagramPacket;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WifiTestActivity2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.WifiTestActivity2$runStepChecking$2", m3970f = "WifiTestActivity2.kt", m3971i = {0, 0, 0}, m3972l = {161}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "times", "index"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
public final class WifiTestActivity2$runStepChecking$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $cnt;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5209p$;
    final /* synthetic */ WifiTestActivity2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiTestActivity2$runStepChecking$2(WifiTestActivity2 wifiTestActivity2, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = wifiTestActivity2;
        this.$cnt = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        WifiTestActivity2$runStepChecking$2 wifiTestActivity2$runStepChecking$2 = new WifiTestActivity2$runStepChecking$2(this.this$0, this.$cnt, completion);
        wifiTestActivity2$runStepChecking$2.f5209p$ = (CoroutineScope) obj;
        return wifiTestActivity2$runStepChecking$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WifiTestActivity2$runStepChecking$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5209p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start sending");
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            Ref.ByteRef byteRef = new Ref.ByteRef();
            byteRef.element = (byte) 0;
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C45361 c45361 = new C45361(intRef, byteRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = intRef;
            this.L$2 = byteRef;
            this.label = 1;
            if (BuildersKt.withContext(io2, c45361, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.getKeyInfo().put("接收次数", String.valueOf(this.$cnt.element));
        this.this$0.getKeyInfo().put("合格要求", String.valueOf(20));
        str2 = this.this$0.TAG;
        Pdlog.m3275i(str2, "check receiveBack.cnt=" + this.$cnt.element);
        str3 = this.this$0.TAG;
        Pdlog.m3275i(str3, "end auto test. cnt=" + this.$cnt.element);
        if (!this.this$0.checkRSSIOK()) {
            this.this$0.setStep(WifiTestActivity2.Step.FAIL);
            this.this$0.getMTestItem().setFailDescription("信号强度不达标");
        } else if (this.$cnt.element < 20) {
            this.this$0.setStep(WifiTestActivity2.Step.FAIL);
            if (this.this$0.getKeyInfo().get("服务端mac") == null) {
                this.this$0.getMTestItem().setFailDescription("没有服务端");
            } else {
                this.this$0.getMTestItem().setFailDescription("接收到回复的数据次数太少");
            }
        } else {
            this.this$0.setStep(WifiTestActivity2.Step.SUCCESS);
        }
        this.this$0.FSM();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: WifiTestActivity2.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.WifiTestActivity2$runStepChecking$2$1", m3970f = "WifiTestActivity2.kt", m3971i = {0, 0}, m3972l = {170}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "bytes"}, m3975s = {"L$0", "L$1"})
    /* renamed from: com.pudutech.factory_test.single_test.WifiTestActivity2$runStepChecking$2$1 */
    /* loaded from: classes.dex */
    public static final class C45361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ByteRef $index;
        final /* synthetic */ Ref.IntRef $times;
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5210p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C45361(Ref.IntRef intRef, Ref.ByteRef byteRef, Continuation continuation) {
            super(2, continuation);
            this.$times = intRef;
            this.$index = byteRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45361 c45361 = new C45361(this.$times, this.$index, completion);
            c45361.f5210p$ = (CoroutineScope) obj;
            return c45361;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                coroutineScope = this.f5210p$;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            do {
                Ref.IntRef intRef = this.$times;
                int i2 = intRef.element;
                intRef.element = i2 + 1;
                if (i2 < 100) {
                    String mac = WifiUtil.INSTANCE.getMac();
                    if (mac == null) {
                        Intrinsics.throwNpe();
                    }
                    Charset charset = Charsets.UTF_8;
                    if (mac == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    byte[] bytes = mac.getBytes(charset);
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                    Ref.ByteRef byteRef = this.$index;
                    byte b = byteRef.element;
                    byteRef.element = (byte) (b + 1);
                    byte[] plus = ArraysKt.plus(bytes, b);
                    try {
                        WifiTestActivity2$runStepChecking$2.this.this$0.getSendSocket().send(new DatagramPacket(plus, plus.length, WifiTestActivity2$runStepChecking$2.this.this$0.getAddress(), WifiTestActivity2$runStepChecking$2.this.this$0.getBROADCAST_PORT()));
                        str2 = WifiTestActivity2$runStepChecking$2.this.this$0.TAG;
                        Pdlog.m3276v(str2, "send " + WifiUtil.INSTANCE.getMac() + " array.size=" + plus.length + " index=" + ((int) this.$index.element) + " times=" + this.$times.element);
                    } catch (Exception e) {
                        str = WifiTestActivity2$runStepChecking$2.this.this$0.TAG;
                        Pdlog.m3277w(str, String.valueOf(e));
                    }
                    this.L$0 = coroutineScope;
                    this.L$1 = plus;
                    this.label = 1;
                } else {
                    WifiTestActivity2$runStepChecking$2.this.this$0.setOnReceive((Function1) null);
                    return Unit.INSTANCE;
                }
            } while (DelayKt.delay(100L, this) != coroutine_suspended);
            return coroutine_suspended;
        }
    }
}
