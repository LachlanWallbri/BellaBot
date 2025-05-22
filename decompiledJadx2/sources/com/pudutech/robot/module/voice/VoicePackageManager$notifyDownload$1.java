package com.pudutech.robot.module.voice;

import com.pudutech.robot.module.voice.VoicePackageManager;
import java.util.ArrayList;
import java.util.Iterator;
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
/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$notifyDownload$1", m3970f = "VoicePackageManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class VoicePackageManager$notifyDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $v */
    final /* synthetic */ VoicePackageManager.DownloadVoice f7233$v;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7234p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoicePackageManager$notifyDownload$1(VoicePackageManager.DownloadVoice downloadVoice, Continuation continuation) {
        super(2, continuation);
        this.f7233$v = downloadVoice;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoicePackageManager$notifyDownload$1 voicePackageManager$notifyDownload$1 = new VoicePackageManager$notifyDownload$1(this.f7233$v, completion);
        voicePackageManager$notifyDownload$1.f7234p$ = (CoroutineScope) obj;
        return voicePackageManager$notifyDownload$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoicePackageManager$notifyDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ArrayList arrayList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7234p$;
        VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
        arrayList = VoicePackageManager.downloadListener;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((VoicePackageManager.OnDownloadListener) it.next()).onLoad(this.f7233$v);
        }
        return Unit.INSTANCE;
    }
}
