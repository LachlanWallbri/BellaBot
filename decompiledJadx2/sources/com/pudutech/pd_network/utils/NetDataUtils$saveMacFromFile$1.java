package com.pudutech.pd_network.utils;

import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetDataUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.utils.NetDataUtils$saveMacFromFile$1", m3970f = "NetDataUtils.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class NetDataUtils$saveMacFromFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    final /* synthetic */ String $path;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6873p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetDataUtils$saveMacFromFile$1(String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.$path = str;
        this.$mac = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        NetDataUtils$saveMacFromFile$1 netDataUtils$saveMacFromFile$1 = new NetDataUtils$saveMacFromFile$1(this.$path, this.$mac, completion);
        netDataUtils$saveMacFromFile$1.f6873p$ = (CoroutineScope) obj;
        return netDataUtils$saveMacFromFile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NetDataUtils$saveMacFromFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6873p$;
        try {
            File file = new File(this.$path);
            if (file.exists() && file.isDirectory()) {
                file.delete();
            }
            if (!file.exists()) {
                String parent = file.getParent();
                if (parent != null) {
                    File file2 = new File(parent);
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                }
                file.createNewFile();
                FilesKt.writeText(file, this.$mac, Charsets.UTF_8);
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                NetDataUtils netDataUtils = NetDataUtils.INSTANCE;
                str2 = NetDataUtils.TAG;
                netWorkLog.mo3278d(str2, "write success");
            }
        } catch (Exception e) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            NetDataUtils netDataUtils2 = NetDataUtils.INSTANCE;
            str = NetDataUtils.TAG;
            netWorkLog2.mo3278d(str, "write fail : " + e.getMessage());
        }
        return Unit.INSTANCE;
    }
}
