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
import com.google.cloud.dialogflow.v2beta1.KnowledgeBasesClient;
import com.google.cloud.dialogflow.v2beta1.stub.KnowledgeBasesStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class KnowledgeBasesSettings extends ClientSettings<KnowledgeBasesSettings> {
    public PagedCallSettings<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesSettings() {
        return ((KnowledgeBasesStubSettings) getStubSettings()).listKnowledgeBasesSettings();
    }

    public UnaryCallSettings<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseSettings() {
        return ((KnowledgeBasesStubSettings) getStubSettings()).getKnowledgeBaseSettings();
    }

    public UnaryCallSettings<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseSettings() {
        return ((KnowledgeBasesStubSettings) getStubSettings()).createKnowledgeBaseSettings();
    }

    public UnaryCallSettings<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseSettings() {
        return ((KnowledgeBasesStubSettings) getStubSettings()).deleteKnowledgeBaseSettings();
    }

    public UnaryCallSettings<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseSettings() {
        return ((KnowledgeBasesStubSettings) getStubSettings()).updateKnowledgeBaseSettings();
    }

    public static final KnowledgeBasesSettings create(KnowledgeBasesStubSettings knowledgeBasesStubSettings) throws IOException {
        return new Builder(knowledgeBasesStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return KnowledgeBasesStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return KnowledgeBasesStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return KnowledgeBasesStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return KnowledgeBasesStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return KnowledgeBasesStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return KnowledgeBasesStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return KnowledgeBasesStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected KnowledgeBasesSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<KnowledgeBasesSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(KnowledgeBasesStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(KnowledgeBasesStubSettings.newBuilder());
        }

        protected Builder(KnowledgeBasesSettings knowledgeBasesSettings) {
            super(knowledgeBasesSettings.getStubSettings().toBuilder());
        }

        protected Builder(KnowledgeBasesStubSettings.Builder builder) {
            super(builder);
        }

        public KnowledgeBasesStubSettings.Builder getStubSettingsBuilder() {
            return (KnowledgeBasesStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public PagedCallSettings.Builder<ListKnowledgeBasesRequest, ListKnowledgeBasesResponse, KnowledgeBasesClient.ListKnowledgeBasesPagedResponse> listKnowledgeBasesSettings() {
            return getStubSettingsBuilder().listKnowledgeBasesSettings();
        }

        public UnaryCallSettings.Builder<GetKnowledgeBaseRequest, KnowledgeBase> getKnowledgeBaseSettings() {
            return getStubSettingsBuilder().getKnowledgeBaseSettings();
        }

        public UnaryCallSettings.Builder<CreateKnowledgeBaseRequest, KnowledgeBase> createKnowledgeBaseSettings() {
            return getStubSettingsBuilder().createKnowledgeBaseSettings();
        }

        public UnaryCallSettings.Builder<DeleteKnowledgeBaseRequest, Empty> deleteKnowledgeBaseSettings() {
            return getStubSettingsBuilder().deleteKnowledgeBaseSettings();
        }

        public UnaryCallSettings.Builder<UpdateKnowledgeBaseRequest, KnowledgeBase> updateKnowledgeBaseSettings() {
            return getStubSettingsBuilder().updateKnowledgeBaseSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public KnowledgeBasesSettings build() throws IOException {
            return new KnowledgeBasesSettings(this);
        }
    }
}
