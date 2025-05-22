package com.google.cloud.texttospeech.v1beta1.stub;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.texttospeech.v1beta1.ListVoicesRequest;
import com.google.cloud.texttospeech.v1beta1.ListVoicesResponse;
import com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest;
import com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponse;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class TextToSpeechStubSettings extends StubSettings<TextToSpeechStubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ImmutableList.builder().add((ImmutableList.Builder) "https://www.googleapis.com/auth/cloud-platform").build();
    private final UnaryCallSettings<ListVoicesRequest, ListVoicesResponse> listVoicesSettings;
    private final UnaryCallSettings<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings;

    public static String getDefaultEndpoint() {
        return "texttospeech.googleapis.com:443";
    }

    public UnaryCallSettings<ListVoicesRequest, ListVoicesResponse> listVoicesSettings() {
        return this.listVoicesSettings;
    }

    public UnaryCallSettings<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings() {
        return this.synthesizeSpeechSettings;
    }

    @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
    public TextToSpeechStub createStub() throws IOException {
        if (getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcTextToSpeechStub.create(this);
        }
        throw new UnsupportedOperationException(String.format("Transport not supported: %s", getTransportChannelProvider().getTransportName()));
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return InstantiatingExecutorProvider.newBuilder();
    }

    public static List<String> getDefaultServiceScopes() {
        return DEFAULT_SERVICE_SCOPES;
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return GoogleCredentialsProvider.newBuilder().setScopesToApply(DEFAULT_SERVICE_SCOPES);
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return InstantiatingGrpcChannelProvider.newBuilder().setMaxInboundMessageSize(Integer.MAX_VALUE);
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return defaultGrpcTransportProviderBuilder().build();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(TextToSpeechStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
    }

    public static Builder newBuilder() {
        return Builder.access$000();
    }

    public static Builder newBuilder(ClientContext clientContext) {
        return new Builder(clientContext);
    }

    @Override // com.google.api.gax.rpc.StubSettings
    public Builder toBuilder() {
        return new Builder(this);
    }

    protected TextToSpeechStubSettings(Builder builder) throws IOException {
        super(builder);
        this.listVoicesSettings = builder.listVoicesSettings().build();
        this.synthesizeSpeechSettings = builder.synthesizeSpeechSettings().build();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends StubSettings.Builder<TextToSpeechStubSettings, Builder> {
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;
        private final UnaryCallSettings.Builder<ListVoicesRequest, ListVoicesResponse> listVoicesSettings;
        private final UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings;
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;

        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        static {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.put("retry_policy_0_codes", ImmutableSet.copyOf((Collection) Lists.newArrayList(StatusCode.Code.UNAVAILABLE, StatusCode.Code.DEADLINE_EXCEEDED)));
            RETRYABLE_CODE_DEFINITIONS = builder.build();
            ImmutableMap.Builder builder2 = ImmutableMap.builder();
            builder2.put("retry_policy_0_params", RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(100L)).setRetryDelayMultiplier(1.3d).setMaxRetryDelay(Duration.ofMillis(60000L)).setInitialRpcTimeout(Duration.ofMillis(300000L)).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(Duration.ofMillis(300000L)).setTotalTimeout(Duration.ofMillis(300000L)).build());
            RETRY_PARAM_DEFINITIONS = builder2.build();
        }

        protected Builder() {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.listVoicesSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.synthesizeSpeechSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m636of((UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse>) this.listVoicesSettings, this.synthesizeSpeechSettings);
            initDefaults(this);
        }

        protected Builder(TextToSpeechStubSettings textToSpeechStubSettings) {
            super(textToSpeechStubSettings);
            this.listVoicesSettings = textToSpeechStubSettings.listVoicesSettings.toBuilder();
            this.synthesizeSpeechSettings = textToSpeechStubSettings.synthesizeSpeechSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.m636of((UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse>) this.listVoicesSettings, this.synthesizeSpeechSettings);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext) null);
            builder.setTransportChannelProvider(TextToSpeechStubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(TextToSpeechStubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(TextToSpeechStubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(TextToSpeechStubSettings.getDefaultEndpoint());
            return initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.listVoicesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("retry_policy_0_codes")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("retry_policy_0_params"));
            builder.synthesizeSpeechSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("retry_policy_0_codes")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("retry_policy_0_params"));
            return builder;
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, apiFunction);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public UnaryCallSettings.Builder<ListVoicesRequest, ListVoicesResponse> listVoicesSettings() {
            return this.listVoicesSettings;
        }

        public UnaryCallSettings.Builder<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechSettings() {
            return this.synthesizeSpeechSettings;
        }

        @Override // com.google.api.gax.rpc.StubSettings.Builder
        public StubSettings<Builder> build() throws IOException {
            return new TextToSpeechStubSettings(this);
        }
    }
}
