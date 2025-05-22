package com.pudutech.robot.module.voice;

import com.pudutech.disinfect.baselib.network.response.VoicePackCloud;
import com.pudutech.light_network.download.DownloadManager;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VoicePackageManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.voice.VoicePackageManager$cancelDownload$1", m3970f = "VoicePackageManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class VoicePackageManager$cancelDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VoicePackageInfo $theOne;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7226p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoicePackageManager$cancelDownload$1(VoicePackageInfo voicePackageInfo, Continuation continuation) {
        super(2, continuation);
        this.$theOne = voicePackageInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoicePackageManager$cancelDownload$1 voicePackageManager$cancelDownload$1 = new VoicePackageManager$cancelDownload$1(this.$theOne, completion);
        voicePackageManager$cancelDownload$1.f7226p$ = (CoroutineScope) obj;
        return voicePackageManager$cancelDownload$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoicePackageManager$cancelDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        HashMap hashMap;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7226p$;
        VoicePackageManager voicePackageManager = VoicePackageManager.INSTANCE;
        hashMap = VoicePackageManager.cloudLib;
        VoicePackCloud voicePackCloud = (VoicePackCloud) hashMap.get(Boxing.boxLong(this.$theOne.getId()));
        if (voicePackCloud != null) {
            DownloadManager.getInstance().cancel(voicePackCloud.getUrl());
        }
        return Unit.INSTANCE;
    }
}
