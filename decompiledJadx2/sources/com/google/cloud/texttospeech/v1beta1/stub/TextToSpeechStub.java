package com.google.cloud.texttospeech.v1beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.texttospeech.v1beta1.ListVoicesRequest;
import com.google.cloud.texttospeech.v1beta1.ListVoicesResponse;
import com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest;
import com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponse;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public abstract class TextToSpeechStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    public UnaryCallable<ListVoicesRequest, ListVoicesResponse> listVoicesCallable() {
        throw new UnsupportedOperationException("Not implemented: listVoicesCallable()");
    }

    public UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechCallable() {
        throw new UnsupportedOperationException("Not implemented: synthesizeSpeechCallable()");
    }
}
