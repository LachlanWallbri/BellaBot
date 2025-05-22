package com.pudutech.tts_sdk.tts;

import android.util.Log;
import com.google.cloud.texttospeech.p050v1.AudioConfig;
import com.google.cloud.texttospeech.p050v1.AudioEncoding;
import com.google.cloud.texttospeech.p050v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.p050v1.SynthesisInput;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.p050v1.TextToSpeechClient;
import com.google.cloud.texttospeech.p050v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import com.loc.C3898x;
import com.pudutech.base.FileUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.tts_sdk.TtsConfig;
import java.io.File;
import java.io.FileOutputStream;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: GoogleTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.tts_sdk.tts.GoogleTtsTask$genTtsFile$1", m3970f = "GoogleTtsTask.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, m3972l = {110, 118}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "it", "input", "voiceBuilder", "audioConfig", "response", "audioContents", "outputFile", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1"})
/* loaded from: classes7.dex */
public final class GoogleTtsTask$genTtsFile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $extraParam;
    final /* synthetic */ String $filePath;
    final /* synthetic */ OnTtsListener $onTtsListener;
    final /* synthetic */ String $text;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7512p$;
    final /* synthetic */ GoogleTtsTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GoogleTtsTask$genTtsFile$1(GoogleTtsTask googleTtsTask, String str, String str2, String str3, OnTtsListener onTtsListener, Continuation continuation) {
        super(2, continuation);
        this.this$0 = googleTtsTask;
        this.$text = str;
        this.$filePath = str2;
        this.$extraParam = str3;
        this.$onTtsListener = onTtsListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GoogleTtsTask$genTtsFile$1 googleTtsTask$genTtsFile$1 = new GoogleTtsTask$genTtsFile$1(this.this$0, this.$text, this.$filePath, this.$extraParam, this.$onTtsListener, completion);
        googleTtsTask$genTtsFile$1.f7512p$ = (CoroutineScope) obj;
        return googleTtsTask$genTtsFile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GoogleTtsTask$genTtsFile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        TextToSpeechClient textToSpeechClient;
        String vioceConfigName;
        String str2;
        AudioEncoding audioEncoding;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f7512p$;
                textToSpeechClient = this.this$0.client;
                if (textToSpeechClient != null) {
                    SynthesisInput build = SynthesisInput.newBuilder().setText(this.$text).build();
                    Intrinsics.checkExpressionValueIsNotNull(build, "SynthesisInput.newBuilde…                 .build()");
                    VoiceSelectionParams.Builder voiceBuilder = VoiceSelectionParams.newBuilder().setSsmlGender(SsmlVoiceGender.FEMALE);
                    Intrinsics.checkExpressionValueIsNotNull(voiceBuilder, "voiceBuilder");
                    vioceConfigName = this.this$0.getVioceConfigName();
                    voiceBuilder.setName(vioceConfigName);
                    voiceBuilder.setLanguageCode(TtsConfig.INSTANCE.getGoogleTtsVoiceLanguage());
                    str2 = this.this$0.TAG;
                    Pdlog.m3273d(str2, "googleTtsVoiceName:" + TtsConfig.INSTANCE.getGoogleTtsVoiceName() + " googleTtsVoiceLanguage:" + TtsConfig.INSTANCE.getGoogleTtsVoiceLanguage() + " text:" + this.$text + " filePath:" + this.$filePath + " extraParam:" + this.$extraParam);
                    AudioConfig.Builder newBuilder = AudioConfig.newBuilder();
                    audioEncoding = this.this$0.getAudioEncoding();
                    AudioConfig build2 = newBuilder.setAudioEncoding(audioEncoding).build();
                    SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(build, voiceBuilder.build(), build2);
                    str3 = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("gen tts success ");
                    sb.append(this.$text);
                    Pdlog.m3273d(str3, sb.toString());
                    Intrinsics.checkExpressionValueIsNotNull(response, "response");
                    ByteString audioContent = response.getAudioContent();
                    File file = new File(this.$filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    FileUtil.createOrExistsFile(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
                    Throwable th = (Throwable) null;
                    try {
                        fileOutputStream.write(audioContent.toByteArray());
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, th);
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1 googleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1 = new GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1(null, this);
                        this.L$0 = coroutineScope;
                        this.L$1 = textToSpeechClient;
                        this.L$2 = build;
                        this.L$3 = voiceBuilder;
                        this.L$4 = build2;
                        this.L$5 = response;
                        this.L$6 = audioContent;
                        this.L$7 = file;
                        this.label = 1;
                        if (BuildersKt.withContext(main, googleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } finally {
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3274e(str, "genTtsFile: " + Log.getStackTraceString(e));
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C57562 c57562 = new C57562(e, null);
            this.L$0 = coroutineScope;
            this.L$1 = e;
            this.label = 2;
            if (BuildersKt.withContext(main2, c57562, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: GoogleTtsTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.tts_sdk.tts.GoogleTtsTask$genTtsFile$1$2", m3970f = "GoogleTtsTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.tts_sdk.tts.GoogleTtsTask$genTtsFile$1$2 */
    /* loaded from: classes7.dex */
    public static final class C57562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f7513$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7514p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C57562(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f7513$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57562 c57562 = new C57562(this.f7513$e, completion);
            c57562.f7514p$ = (CoroutineScope) obj;
            return c57562;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57562) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7514p$;
            OnTtsListener onTtsListener = GoogleTtsTask$genTtsFile$1.this.$onTtsListener;
            String message = this.f7513$e.getMessage();
            if (message == null) {
                message = "";
            }
            onTtsListener.onError(-1, message);
            return Unit.INSTANCE;
        }
    }
}
