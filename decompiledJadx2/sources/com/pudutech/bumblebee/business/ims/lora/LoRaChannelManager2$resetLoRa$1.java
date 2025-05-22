package com.pudutech.bumblebee.business.ims.lora;

import java.io.File;
import java.io.FileWriter;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaChannelManager2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2$resetLoRa$1", m3970f = "LoRaChannelManager2.kt", m3971i = {0, 0}, m3972l = {536}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "writer"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
public final class LoRaChannelManager2$resetLoRa$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4597p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoRaChannelManager2$resetLoRa$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaChannelManager2$resetLoRa$1 loRaChannelManager2$resetLoRa$1 = new LoRaChannelManager2$resetLoRa$1(completion);
        loRaChannelManager2$resetLoRa$1.f4597p$ = (CoroutineScope) obj;
        return loRaChannelManager2$resetLoRa$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaChannelManager2$resetLoRa$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0070, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006b, code lost:
    
        if (r0 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0058, code lost:
    
        if (r0 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x005a, code lost:
    
        r0.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FileWriter fileWriter;
        FileWriter fileWriter2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4597p$;
            FileWriter fileWriter3 = (FileWriter) null;
            try {
                fileWriter = new FileWriter(new File("/sys/class/gpio/gpio132/value"));
            } catch (Exception e) {
                e = e;
                fileWriter2 = fileWriter3;
            } catch (Throwable th) {
                th = th;
                fileWriter = fileWriter3;
            }
            try {
                fileWriter.write("1");
                fileWriter.flush();
                this.L$0 = coroutineScope;
                this.L$1 = fileWriter;
                this.label = 1;
                if (DelayKt.delay(100L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                fileWriter2 = fileWriter;
            } catch (Exception e2) {
                e = e2;
                fileWriter2 = fileWriter;
                e.printStackTrace();
            } catch (Throwable th2) {
                th = th2;
                if (fileWriter != null) {
                }
                throw th;
            }
        } else if (i == 1) {
            fileWriter2 = (FileWriter) this.L$1;
            try {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th3) {
                    th = th3;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        fileWriter2.write("0");
        fileWriter2.flush();
    }
}
