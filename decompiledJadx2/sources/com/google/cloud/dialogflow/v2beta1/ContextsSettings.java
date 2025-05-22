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
import com.google.cloud.dialogflow.v2beta1.ContextsClient;
import com.google.cloud.dialogflow.v2beta1.stub.ContextsStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class ContextsSettings extends ClientSettings<ContextsSettings> {
    public PagedCallSettings<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> listContextsSettings() {
        return ((ContextsStubSettings) getStubSettings()).listContextsSettings();
    }

    public UnaryCallSettings<GetContextRequest, Context> getContextSettings() {
        return ((ContextsStubSettings) getStubSettings()).getContextSettings();
    }

    public UnaryCallSettings<CreateContextRequest, Context> createContextSettings() {
        return ((ContextsStubSettings) getStubSettings()).createContextSettings();
    }

    public UnaryCallSettings<UpdateContextRequest, Context> updateContextSettings() {
        return ((ContextsStubSettings) getStubSettings()).updateContextSettings();
    }

    public UnaryCallSettings<DeleteContextRequest, Empty> deleteContextSettings() {
        return ((ContextsStubSettings) getStubSettings()).deleteContextSettings();
    }

    public UnaryCallSettings<DeleteAllContextsRequest, Empty> deleteAllContextsSettings() {
        return ((ContextsStubSettings) getStubSettings()).deleteAllContextsSettings();
    }

    public static final ContextsSettings create(ContextsStubSettings contextsStubSettings) throws IOException {
        return new Builder(contextsStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return ContextsStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return ContextsStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return ContextsStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return ContextsStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return ContextsStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return ContextsStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ContextsStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected ContextsSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<ContextsSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(ContextsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(ContextsStubSettings.newBuilder());
        }

        protected Builder(ContextsSettings contextsSettings) {
            super(contextsSettings.getStubSettings().toBuilder());
        }

        protected Builder(ContextsStubSettings.Builder builder) {
            super(builder);
        }

        public ContextsStubSettings.Builder getStubSettingsBuilder() {
            return (ContextsStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public PagedCallSettings.Builder<ListContextsRequest, ListContextsResponse, ContextsClient.ListContextsPagedResponse> listContextsSettings() {
            return getStubSettingsBuilder().listContextsSettings();
        }

        public UnaryCallSettings.Builder<GetContextRequest, Context> getContextSettings() {
            return getStubSettingsBuilder().getContextSettings();
        }

        public UnaryCallSettings.Builder<CreateContextRequest, Context> createContextSettings() {
            return getStubSettingsBuilder().createContextSettings();
        }

        public UnaryCallSettings.Builder<UpdateContextRequest, Context> updateContextSettings() {
            return getStubSettingsBuilder().updateContextSettings();
        }

        public UnaryCallSettings.Builder<DeleteContextRequest, Empty> deleteContextSettings() {
            return getStubSettingsBuilder().deleteContextSettings();
        }

        public UnaryCallSettings.Builder<DeleteAllContextsRequest, Empty> deleteAllContextsSettings() {
            return getStubSettingsBuilder().deleteAllContextsSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public ContextsSettings build() throws IOException {
            return new ContextsSettings(this);
        }
    }
}
