package com.google.cloud.dialogflow.v2beta1;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.dialogflow.v2beta1.SessionEntityTypesClient;
import com.google.cloud.dialogflow.v2beta1.stub.SessionEntityTypesStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class SessionEntityTypesSettings extends ClientSettings<SessionEntityTypesSettings> {
    public PagedCallSettings<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesSettings() {
        return ((SessionEntityTypesStubSettings) getStubSettings()).listSessionEntityTypesSettings();
    }

    public UnaryCallSettings<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeSettings() {
        return ((SessionEntityTypesStubSettings) getStubSettings()).getSessionEntityTypeSettings();
    }

    public UnaryCallSettings<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeSettings() {
        return ((SessionEntityTypesStubSettings) getStubSettings()).createSessionEntityTypeSettings();
    }

    public UnaryCallSettings<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeSettings() {
        return ((SessionEntityTypesStubSettings) getStubSettings()).updateSessionEntityTypeSettings();
    }

    public UnaryCallSettings<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeSettings() {
        return ((SessionEntityTypesStubSettings) getStubSettings()).deleteSessionEntityTypeSettings();
    }

    public static final SessionEntityTypesSettings create(SessionEntityTypesStubSettings sessionEntityTypesStubSettings) throws IOException {
        return new Builder(sessionEntityTypesStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return SessionEntityTypesStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return SessionEntityTypesStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return SessionEntityTypesStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return SessionEntityTypesStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return SessionEntityTypesStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return SessionEntityTypesStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return SessionEntityTypesStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected SessionEntityTypesSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<SessionEntityTypesSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(SessionEntityTypesStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(SessionEntityTypesStubSettings.newBuilder());
        }

        protected Builder(SessionEntityTypesSettings sessionEntityTypesSettings) {
            super(sessionEntityTypesSettings.getStubSettings().toBuilder());
        }

        protected Builder(SessionEntityTypesStubSettings.Builder builder) {
            super(builder);
        }

        public SessionEntityTypesStubSettings.Builder getStubSettingsBuilder() {
            return (SessionEntityTypesStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public PagedCallSettings.Builder<ListSessionEntityTypesRequest, ListSessionEntityTypesResponse, SessionEntityTypesClient.ListSessionEntityTypesPagedResponse> listSessionEntityTypesSettings() {
            return getStubSettingsBuilder().listSessionEntityTypesSettings();
        }

        public UnaryCallSettings.Builder<GetSessionEntityTypeRequest, SessionEntityType> getSessionEntityTypeSettings() {
            return getStubSettingsBuilder().getSessionEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<CreateSessionEntityTypeRequest, SessionEntityType> createSessionEntityTypeSettings() {
            return getStubSettingsBuilder().createSessionEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<UpdateSessionEntityTypeRequest, SessionEntityType> updateSessionEntityTypeSettings() {
            return getStubSettingsBuilder().updateSessionEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<DeleteSessionEntityTypeRequest, Empty> deleteSessionEntityTypeSettings() {
            return getStubSettingsBuilder().deleteSessionEntityTypeSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public SessionEntityTypesSettings build() throws IOException {
            return new SessionEntityTypesSettings(this);
        }
    }
}
