package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.single_test.ESP32TestActivity;
import com.pudutech.factory_test.test_pack.WifiUtil;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.ESP32TestActivity$runStepChecking$4", m3970f = "ESP32TestActivity.kt", m3971i = {0, 0, 0, 0}, m3972l = {TarConstants.VERSION_OFFSET}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "times", "index", "bytes"}, m3975s = {"L$0", "I$0", "I$1", "L$1"})
/* loaded from: classes.dex */
public final class ESP32TestActivity$runStepChecking$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $cnt;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5174p$;
    final /* synthetic */ ESP32TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESP32TestActivity$runStepChecking$4(ESP32TestActivity eSP32TestActivity, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eSP32TestActivity;
        this.$cnt = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESP32TestActivity$runStepChecking$4 eSP32TestActivity$runStepChecking$4 = new ESP32TestActivity$runStepChecking$4(this.this$0, this.$cnt, completion);
        eSP32TestActivity$runStepChecking$4.f5174p$ = (CoroutineScope) obj;
        return eSP32TestActivity$runStepChecking$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ESP32TestActivity$runStepChecking$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ESP32TestActivity$runStepChecking$4 eSP32TestActivity$runStepChecking$4;
        CoroutineScope coroutineScope;
        int i;
        byte b;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5174p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start sending");
            this.this$0.getKeyInfo().put("服务端", null);
            eSP32TestActivity$runStepChecking$4 = this;
            coroutineScope = coroutineScope2;
            i = 0;
            b = 0;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ?? r1 = this.I$1;
            int i3 = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            i = i3;
            b = r1;
            eSP32TestActivity$runStepChecking$4 = this;
        }
        while (true) {
            int i4 = i + 1;
            if (i < 50) {
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
                byte b2 = (byte) (b + 1);
                byte[] plus = ArraysKt.plus(bytes, b);
                eSP32TestActivity$runStepChecking$4.this$0.getEsp32().sendData(plus);
                str3 = eSP32TestActivity$runStepChecking$4.this$0.TAG;
                Pdlog.m3276v(str3, "send " + WifiUtil.INSTANCE.getMac() + " array.size=" + plus.length + " index=" + ((int) b2) + " times=" + i4);
                eSP32TestActivity$runStepChecking$4.L$0 = coroutineScope;
                eSP32TestActivity$runStepChecking$4.I$0 = i4;
                eSP32TestActivity$runStepChecking$4.I$1 = b2;
                eSP32TestActivity$runStepChecking$4.L$1 = plus;
                eSP32TestActivity$runStepChecking$4.label = 1;
                if (DelayKt.delay(100L, eSP32TestActivity$runStepChecking$4) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i = i4;
                b = b2;
            } else {
                eSP32TestActivity$runStepChecking$4.this$0.setOnReceive((Function1) null);
                eSP32TestActivity$runStepChecking$4.this$0.getKeyInfo().put("发送个数", "50");
                eSP32TestActivity$runStepChecking$4.this$0.getKeyInfo().put("接收个数", String.valueOf(eSP32TestActivity$runStepChecking$4.$cnt.element));
                eSP32TestActivity$runStepChecking$4.this$0.getKeyInfo().put("要求", "大于 30");
                str2 = eSP32TestActivity$runStepChecking$4.this$0.TAG;
                Pdlog.m3275i(str2, "end auto test. cnt=" + eSP32TestActivity$runStepChecking$4.$cnt.element);
                eSP32TestActivity$runStepChecking$4.this$0.setStep(eSP32TestActivity$runStepChecking$4.$cnt.element > 30 ? ESP32TestActivity.Step.SUCCESS : ESP32TestActivity.Step.FAIL);
                if (eSP32TestActivity$runStepChecking$4.this$0.getStep() == ESP32TestActivity.Step.FAIL) {
                    eSP32TestActivity$runStepChecking$4.this$0.getMTestItem().setFailDescription("接收到的数据返回次数较少");
                }
                eSP32TestActivity$runStepChecking$4.this$0.FSM();
                return Unit.INSTANCE;
            }
        }
    }
}
