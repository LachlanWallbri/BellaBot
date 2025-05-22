package com.pudutech.bumblebee.robot.remote_device;

import android.util.Log;
import android.util.Pair;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Tools;
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
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.remote_device.LoRaChannelManager2$resetLoRa$1", m3970f = "LoRaChannelManager2.kt", m3971i = {0, 0, 0, 0}, m3972l = {DMErrorCode.ERROR_CMP_REGISTER_CONNECT_ERROR_EXIST}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "writer", "resetCmd", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes2.dex */
public final class LoRaChannelManager2$resetLoRa$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4794p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoRaChannelManager2$resetLoRa$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaChannelManager2$resetLoRa$1 loRaChannelManager2$resetLoRa$1 = new LoRaChannelManager2$resetLoRa$1(completion);
        loRaChannelManager2$resetLoRa$1.f4794p$ = (CoroutineScope) obj;
        return loRaChannelManager2$resetLoRa$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaChannelManager2$resetLoRa$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x00b8, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b3, code lost:
    
        if (r0 == 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00a6, code lost:
    
        if (r0 != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00a8, code lost:
    
        r0.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.FileWriter] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.FileWriter] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.FileWriter] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? r2;
        FileWriter fileWriter;
        ?? r0 = "LoRaChannelManager2";
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4794p$;
                    r2 = (FileWriter) 0;
                    try {
                        Log.i("LoRaChannelManager2", "resetLoRa() resetCmd = chmod 777 /sys/class/gpio/gpio132/value");
                        Pair<Integer, String> execCommand = Tools.execCommand("chmod 777 /sys/class/gpio/gpio132/value", true);
                        Log.i("LoRaChannelManager2", "resetLoRa() result first = " + ((Integer) execCommand.first) + "\tsecond = " + ((String) execCommand.second));
                        FileWriter fileWriter2 = new FileWriter(new File("/sys/class/gpio/gpio132/value"));
                        fileWriter2.write("1");
                        fileWriter2.flush();
                        this.L$0 = coroutineScope;
                        this.L$1 = fileWriter2;
                        this.L$2 = "chmod 777 /sys/class/gpio/gpio132/value";
                        this.L$3 = execCommand;
                        this.label = 1;
                        r0 = fileWriter2;
                        if (DelayKt.delay(100L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } catch (Exception e) {
                        e = e;
                        r0 = r2;
                        e.printStackTrace();
                        fileWriter = r0;
                    } catch (Throwable th) {
                        th = th;
                        if (r2 != 0) {
                            r2.close();
                        }
                        throw th;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    FileWriter fileWriter3 = (FileWriter) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    r0 = fileWriter3;
                }
                r0.write("0");
                r0.flush();
                fileWriter = r0;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            r2 = "LoRaChannelManager2";
        }
    }
}
