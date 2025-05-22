package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.StreamingCallSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.dialogflow.v2beta1.stub.SessionsStubSettings;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class SessionsSettings extends ClientSettings<SessionsSettings> {
    public UnaryCallSettings<DetectIntentRequest, DetectIntentResponse> detectIntentSettings() {
        return ((SessionsStubSettings) getStubSettings()).detectIntentSettings();
    }

    public StreamingCallSettings<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentSettings() {
        return ((SessionsStubSettings) getStubSettings()).streamingDetectIntentSettings();
    }

    public static final SessionsSettings create(SessionsStubSettings sessionsStubSettings) throws IOException {
        return new Builder(sessionsStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return SessionsStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return SessionsStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return SessionsStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return SessionsStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return SessionsStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return SessionsStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return SessionsStubSettings.defaultApiClientHeaderProviderBuilder();
    }

    public static Builder newBuilder() {
        return Builder.access$000();
    }

    public static Builder newBuilder(ClientContext clientContext) {
        return new Builder(clientContext);
    }

    @Override // com.google.api.gax.rpc.ClientSettings
    public Builder toBuilder() {
        return new Builder(this);
    }

    protected SessionsSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<SessionsSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(SessionsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(SessionsStubSettings.newBuilder());
        }

        protected Builder(SessionsSettings sessionsSettings) {
            super(sessionsSettings.getStubSettings().toBuilder());
        }

        protected Builder(SessionsStubSettings.Builder builder) {
            super(builder);
        }

        public SessionsStubSettings.Builder getStubSettingsBuilder() {
            return (SessionsStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public UnaryCallSettings.Builder<DetectIntentRequest, DetectIntentResponse> detectIntentSettings() {
            return getStubSettingsBuilder().detectIntentSettings();
        }

        public StreamingCallSettings.Builder<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentSettings() {
            return getStubSettingsBuilder().streamingDetectIntentSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public SessionsSettings build() throws IOException {
            return new SessionsSettings(this);
        }
    }
}
