package com.pudutech.pd_network.utils;

import com.pudutech.lib_update.util.ShellUtils;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetDataUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.utils.NetDataUtils$btMac$2$1", m3970f = "NetDataUtils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class NetDataUtils$btMac$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6872p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetDataUtils$btMac$2$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NetDataUtils$btMac$2$1 netDataUtils$btMac$2$1 = new NetDataUtils$btMac$2$1(completion);
        netDataUtils$btMac$2$1.f6872p$ = (CoroutineScope) obj;
        return netDataUtils$btMac$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((NetDataUtils$btMac$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6872p$;
            Process proc = Runtime.getRuntime().exec(new String[]{ShellUtils.COMMAND_SU, "-c", "settings get secure bluetooth_address"});
            NetDataUtils netDataUtils = NetDataUtils.INSTANCE;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            netDataUtils.waitFor(5L, timeUnit, proc);
            InputStream inputStream = proc.getInputStream();
            StringBuilder sb = new StringBuilder();
            InputStream inputStream2 = inputStream;
            Throwable th = (Throwable) null;
            try {
                Scanner scanner = new Scanner(inputStream2);
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine());
                    sb.append("\n");
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(inputStream2, th);
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
                NetDataUtils netDataUtils2 = NetDataUtils.INSTANCE;
                NetDataUtils.btMac = sb2;
                return sb2;
            } finally {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
