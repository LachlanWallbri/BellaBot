package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.stub.SessionsStub;
import com.google.cloud.dialogflow.v2beta1.stub.SessionsStubSettings;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class SessionsClient implements BackgroundResource {
    private final SessionsSettings settings;
    private final SessionsStub stub;

    public static final SessionsClient create() throws IOException {
        return create(SessionsSettings.newBuilder().build());
    }

    public static final SessionsClient create(SessionsSettings sessionsSettings) throws IOException {
        return new SessionsClient(sessionsSettings);
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public static final SessionsClient create(SessionsStub sessionsStub) {
        return new SessionsClient(sessionsStub);
    }

    protected SessionsClient(SessionsSettings sessionsSettings) throws IOException {
        this.settings = sessionsSettings;
        this.stub = ((SessionsStubSettings) sessionsSettings.getStubSettings()).createStub();
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    protected SessionsClient(SessionsStub sessionsStub) {
        this.settings = null;
        this.stub = sessionsStub;
    }

    public final SessionsSettings getSettings() {
        return this.settings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public SessionsStub getStub() {
        return this.stub;
    }

    public final DetectIntentResponse detectIntent(SessionName sessionName, QueryInput queryInput) {
        return detectIntent(DetectIntentRequest.newBuilder().setSession(sessionName == null ? null : sessionName.toString()).setQueryInput(queryInput).build());
    }

    public final DetectIntentResponse detectIntent(String str, QueryInput queryInput) {
        return detectIntent(DetectIntentRequest.newBuilder().setSession(str).setQueryInput(queryInput).build());
    }

    public final DetectIntentResponse detectIntent(DetectIntentRequest detectIntentRequest) {
        return detectIntentCallable().call(detectIntentRequest);
    }

    public final UnaryCallable<DetectIntentRequest, DetectIntentResponse> detectIntentCallable() {
        return this.stub.detectIntentCallable();
    }

    public final BidiStreamingCallable<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentCallable() {
        return this.stub.streamingDetectIntentCallable();
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
