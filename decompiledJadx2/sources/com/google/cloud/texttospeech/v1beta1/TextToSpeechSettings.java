package com.google.cloud.texttospeech.v1beta1;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.texttospeech.v1beta1.stub.TextToSpeechStubSettings;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class TextToSpeechSettings extends ClientSettings<TextToSpeechSettings> {
    public UnaryCallSettings<ListVoicesRequest, ListVoicesResponse> listVoicesSettings() {
        return ((TextToSpeechStubSettings) getStubSettings()).listVoicesSettings();
    }

    public UnaryCallSettings<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings() {
        return ((TextToSpeechStubSettings) getStubSettings()).synthesizeSpeechSettings();
    }

    public static final TextToSpeechSettings create(TextToSpeechStubSettings textToSpeechStubSettings) throws IOException {
        return new Builder(textToSpeechStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return TextToSpeechStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return TextToSpeechStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return TextToSpeechStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return TextToSpeechStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return TextToSpeechStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return TextToSpeechStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return TextToSpeechStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected TextToSpeechSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<TextToSpeechSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(TextToSpeechStubSettings.newBuilder(clientContext));
        }

        protected Builder(TextToSpeechSettings textToSpeechSettings) {
            super(textToSpeechSettings.getStubSettings().toBuilder());
        }

        protected Builder(TextToSpeechStubSettings.Builder builder) {
            super(builder);
        }

        private static Builder createDefault() {
            return new Builder(TextToSpeechStubSettings.newBuilder());
        }

        public TextToSpeechStubSettings.Builder getStubSettingsBuilder() {
            return (TextToSpeechStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public UnaryCallSettings.Builder<ListVoicesRequest, ListVoicesResponse> listVoicesSettings() {
            return getStubSettingsBuilder().listVoicesSettings();
        }

        public UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings() {
            return getStubSettingsBuilder().synthesizeSpeechSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public TextToSpeechSettings build() throws IOException {
            return new TextToSpeechSettings(this);
        }
    }
}
