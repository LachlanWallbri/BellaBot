package com.google.cloud.texttospeech.v1beta1;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.texttospeech.v1beta1.stub.TextToSpeechStub;
import com.google.cloud.texttospeech.v1beta1.stub.TextToSpeechStubSettings;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class TextToSpeechClient implements BackgroundResource {
    private final TextToSpeechSettings settings;
    private final TextToSpeechStub stub;

    public static final TextToSpeechClient create() throws IOException {
        return create(TextToSpeechSettings.newBuilder().build());
    }

    public static final TextToSpeechClient create(TextToSpeechSettings textToSpeechSettings) throws IOException {
        return new TextToSpeechClient(textToSpeechSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final TextToSpeechClient create(TextToSpeechStub textToSpeechStub) {
        return new TextToSpeechClient(textToSpeechStub);
    }

    protected TextToSpeechClient(TextToSpeechSettings textToSpeechSettings) throws IOException {
        this.settings = textToSpeechSettings;
        this.stub = ((TextToSpeechStubSettings) textToSpeechSettings.getStubSettings()).createStub();
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected TextToSpeechClient(TextToSpeechStub textToSpeechStub) {
        this.settings = null;
        this.stub = textToSpeechStub;
    }

    public final TextToSpeechSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public TextToSpeechStub getStub() {
        return this.stub;
    }

    public final ListVoicesResponse listVoices(String str) {
        return listVoices(ListVoicesRequest.newBuilder().setLanguageCode(str).build());
    }

    public final ListVoicesResponse listVoices(ListVoicesRequest listVoicesRequest) {
        return listVoicesCallable().call(listVoicesRequest);
    }

    public final UnaryCallable<ListVoicesRequest, ListVoicesResponse> listVoicesCallable() {
        return this.stub.listVoicesCallable();
    }

    public final SynthesizeSpeechResponse synthesizeSpeech(SynthesisInput synthesisInput, VoiceSelectionParams voiceSelectionParams, AudioConfig audioConfig) {
        return synthesizeSpeech(SynthesizeSpeechRequest.newBuilder().setInput(synthesisInput).setVoice(voiceSelectionParams).setAudioConfig(audioConfig).build());
    }

    public final SynthesizeSpeechResponse synthesizeSpeech(SynthesizeSpeechRequest synthesizeSpeechRequest) {
        return synthesizeSpeechCallable().call(synthesizeSpeechRequest);
    }

    public final UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechCallable() {
        return this.stub.synthesizeSpeechCallable();
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.stub.close();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        this.stub.shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return this.stub.isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return this.stub.isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        this.stub.shutdownNow();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.stub.awaitTermination(j, timeUnit);
    }
}
