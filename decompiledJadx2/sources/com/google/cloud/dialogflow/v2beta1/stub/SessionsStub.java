package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.DetectIntentRequest;
import com.google.cloud.dialogflow.v2beta1.DetectIntentResponse;
import com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequest;
import com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentResponse;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public abstract class SessionsStub implements BackgroundResource {
    @Override // java.lang.AutoCloseable
    public abstract void close();

    public UnaryCallable<DetectIntentRequest, DetectIntentResponse> detectIntentCallable() {
        throw new UnsupportedOperationException("Not implemented: detectIntentCallable()");
    }

    public BidiStreamingCallable<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentCallable() {
        throw new UnsupportedOperationException("Not implemented: streamingDetectIntentCallable()");
    }
}
