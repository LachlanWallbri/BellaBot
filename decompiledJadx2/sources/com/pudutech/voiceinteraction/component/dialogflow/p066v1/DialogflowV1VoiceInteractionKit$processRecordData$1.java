package com.pudutech.voiceinteraction.component.dialogflow.p066v1;

import com.pudutech.base.Pdlog;
import java.io.File;
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
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DialogflowV1VoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.dialogflow.v1.DialogflowV1VoiceInteractionKit$processRecordData$1", m3970f = "DialogflowV1VoiceInteractionKit.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
public final class DialogflowV1VoiceInteractionKit$processRecordData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $data;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7575p$;
    final /* synthetic */ DialogflowV1VoiceInteractionKit this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DialogflowV1VoiceInteractionKit$processRecordData$1(DialogflowV1VoiceInteractionKit dialogflowV1VoiceInteractionKit, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dialogflowV1VoiceInteractionKit;
        this.$data = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DialogflowV1VoiceInteractionKit$processRecordData$1 dialogflowV1VoiceInteractionKit$processRecordData$1 = new DialogflowV1VoiceInteractionKit$processRecordData$1(this.this$0, this.$data, completion);
        dialogflowV1VoiceInteractionKit$processRecordData$1.f7575p$ = (CoroutineScope) obj;
        return dialogflowV1VoiceInteractionKit$processRecordData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DialogflowV1VoiceInteractionKit$processRecordData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean saveToPcmFile;
        boolean saveToAmrFile;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7575p$;
            File file = new File("/sdcard/pudu/files/dialogflow");
            if (!file.exists()) {
                file.mkdirs();
            }
            saveToPcmFile = this.this$0.saveToPcmFile(this.$data);
            if (!saveToPcmFile) {
                return Unit.INSTANCE;
            }
            File file2 = new File((file.getPath() + File.separator) + "output_pcm.pcm");
            if (file2.exists() && file2.isFile()) {
                saveToAmrFile = this.this$0.saveToAmrFile(file2);
                if (saveToAmrFile) {
                    this.this$0.queryByAudioFile();
                    Pdlog.m3273d("DialogflowV1VoiceInteractionKit", "processRecordData() success");
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
