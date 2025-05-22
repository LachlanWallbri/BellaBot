package com.pudutech.mirsdk;

import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.utils.CommandUtils;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DmesgWorker.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.DmesgWorker$dmesgWork$1", m3970f = "DmesgWorker.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class DmesgWorker$dmesgWork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5518p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DmesgWorker$dmesgWork$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DmesgWorker$dmesgWork$1 dmesgWorker$dmesgWork$1 = new DmesgWorker$dmesgWork$1(completion);
        dmesgWorker$dmesgWork$1.f5518p$ = (CoroutineScope) obj;
        return dmesgWorker$dmesgWork$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DmesgWorker$dmesgWork$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5518p$;
        DmesgWorker dmesgWorker = DmesgWorker.INSTANCE;
        DmesgWorker.dmesgJobFinished = false;
        try {
            DmesgWorker.INSTANCE.checkDmesgFile();
            DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss.SSS");
            LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
            StringBuilder sb = new StringBuilder();
            DmesgWorker dmesgWorker2 = DmesgWorker.INSTANCE;
            str3 = DmesgWorker.dmesgDir;
            sb.append(str3);
            sb.append("/dmesg-");
            sb.append(ofPattern.format(now));
            sb.append(".info");
            String sb2 = sb.toString();
            DmesgWorker dmesgWorker3 = DmesgWorker.INSTANCE;
            str4 = DmesgWorker.TAG;
            Pdlog.m3273d(str4, "write new dmesg info --> " + sb2);
            CommandUtils.INSTANCE.executeCommand("dmesg -T > " + sb2);
            CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
        } catch (Exception e) {
            DmesgWorker dmesgWorker4 = DmesgWorker.INSTANCE;
            str = DmesgWorker.TAG;
            Pdlog.m3274e(str, "write new dmesg info failed, " + e.getMessage());
        }
        DmesgWorker dmesgWorker5 = DmesgWorker.INSTANCE;
        DmesgWorker.dmesgJobFinished = true;
        DmesgWorker dmesgWorker6 = DmesgWorker.INSTANCE;
        str2 = DmesgWorker.TAG;
        Pdlog.m3273d(str2, "SDKRobotState.Error happens, end dmesg job.");
        return Unit.INSTANCE;
    }
}
