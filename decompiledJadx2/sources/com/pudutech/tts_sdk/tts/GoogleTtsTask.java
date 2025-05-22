package com.pudutech.tts_sdk.tts;

import android.content.Context;
import android.util.Log;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.p050v1.AudioEncoding;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechRequest;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.p050v1.TextToSpeechClient;
import com.google.cloud.texttospeech.p050v1.TextToSpeechSettings;
import com.pudutech.base.Pdlog;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.voiceinteraction.component.C5767R;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: GoogleTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0004H\u0002J5\u0010\u001b\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2#\u0010\u001c\u001a\u001f\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001dH\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\u0011\u0010#\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010$R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, m3961d2 = {"Lcom/pudutech/tts_sdk/tts/GoogleTtsTask;", "Lcom/pudutech/tts_sdk/tts/TtsInterface;", "()V", "DIALOG_FLOW_CONFIG_DIRECTORY", "", "LANGUAGE_CONFIG_NAME", "LOCK", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "TAG", "c", "Lkotlinx/coroutines/CoroutineScope;", "client", "Lcom/google/cloud/texttospeech/v1/TextToSpeechClient;", "context", "Landroid/content/Context;", "languageMap", "", "genTtsFile", "", "text", "filePath", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "extraParam", "getAudioEncoding", "Lcom/google/cloud/texttospeech/v1/AudioEncoding;", "getVioceConfigName", "init", "initCallback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "code", "release", "updateLanguageConfig", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class GoogleTtsTask implements TtsInterface {
    private CoroutineScope c;
    private TextToSpeechClient client;
    private Context context;
    private final String TAG = "GoogleTtsTask";
    private final Map<String, String> languageMap = new LinkedHashMap();
    private final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private final String DIALOG_FLOW_CONFIG_DIRECTORY = "/sdcard/pudu/custom/dialogflowconfig";
    private final String LANGUAGE_CONFIG_NAME = "language_config.json";

    @Override // com.pudutech.tts_sdk.tts.TtsInterface
    public void init(Context context, Function1<? super Integer, Unit> initCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.c = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        try {
            InputStream openRawResource = context.getResources().openRawResource(C5767R.raw.googlekey);
            Intrinsics.checkExpressionValueIsNotNull(openRawResource, "context.resources.openRawResource(R.raw.googlekey)");
            GoogleCredentials fromStream = GoogleCredentials.fromStream(openRawResource);
            Intrinsics.checkExpressionValueIsNotNull(fromStream, "GoogleCredentials.fromStream(stream)");
            TextToSpeechSettings.Builder newBuilder = TextToSpeechSettings.newBuilder();
            Intrinsics.checkExpressionValueIsNotNull(newBuilder, "TextToSpeechSettings.newBuilder()");
            UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings = newBuilder.synthesizeSpeechSettings();
            Intrinsics.checkExpressionValueIsNotNull(synthesizeSpeechSettings, "settingBuilder\n         …ynthesizeSpeechSettings()");
            UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings2 = newBuilder.synthesizeSpeechSettings();
            Intrinsics.checkExpressionValueIsNotNull(synthesizeSpeechSettings2, "settingBuilder.synthesizeSpeechSettings()");
            synthesizeSpeechSettings.setRetrySettings(synthesizeSpeechSettings2.getRetrySettings().toBuilder().setTotalTimeout(Duration.ofSeconds(TtsConfig.INSTANCE.getGoogleTtsTimeout())).setLogicalTimeout(Duration.ofSeconds(TtsConfig.INSTANCE.getGoogleTtsTimeout())).build());
            this.client = TextToSpeechClient.create(newBuilder.setCredentialsProvider(FixedCredentialsProvider.create(fromStream)).build());
            CoroutineScope coroutineScope = this.c;
            if (coroutineScope == null) {
                Intrinsics.throwUninitializedPropertyAccessException("c");
            }
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new GoogleTtsTask$init$1(this, null), 3, null);
            Pdlog.m3273d("GoogleTtsTask", "GoogleTtsTask initTTS success");
            if (initCallback != null) {
                initCallback.invoke(0);
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "GoogleTtsTask init: " + Log.getStackTraceString(e));
            if (initCallback != null) {
                initCallback.invoke(-1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AudioEncoding getAudioEncoding() {
        if (Intrinsics.areEqual(TtsConfig.INSTANCE.getGoogleTtsFileType(), "pcm")) {
            return AudioEncoding.LINEAR16;
        }
        return AudioEncoding.MP3;
    }

    @Override // com.pudutech.tts_sdk.tts.TtsInterface
    public void genTtsFile(String text, String filePath, OnTtsListener onTtsListener, String extraParam) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        Intrinsics.checkParameterIsNotNull(extraParam, "extraParam");
        if (this.client == null) {
            onTtsListener.onError(-1, "google tts not init");
            return;
        }
        CoroutineScope coroutineScope = this.c;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("c");
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new GoogleTtsTask$genTtsFile$1(this, text, filePath, extraParam, onTtsListener, null), 3, null);
    }

    @Override // com.pudutech.tts_sdk.tts.TtsInterface
    public void release() {
        TextToSpeechClient textToSpeechClient = this.client;
        if (textToSpeechClient != null) {
            textToSpeechClient.close();
        }
        CoroutineScope coroutineScope = this.c;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("c");
        }
        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object updateLanguageConfig(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new GoogleTtsTask$updateLanguageConfig$2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getVioceConfigName() {
        try {
            this.LOCK.readLock().lock();
            String str = this.languageMap.get(TtsConfig.INSTANCE.getGoogleTtsVoiceLanguage());
            if (str == null) {
                str = TtsConfig.INSTANCE.getGoogleTtsVoiceName();
            }
            return str;
        } finally {
            this.LOCK.readLock().unlock();
        }
    }
}
