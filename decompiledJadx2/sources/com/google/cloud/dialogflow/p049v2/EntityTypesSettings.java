package com.google.cloud.dialogflow.p049v2;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.dialogflow.p049v2.EntityTypesClient;
import com.google.cloud.dialogflow.p049v2.stub.EntityTypesStubSettings;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class EntityTypesSettings extends ClientSettings<EntityTypesSettings> {
    public PagedCallSettings<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).listEntityTypesSettings();
    }

    public UnaryCallSettings<GetEntityTypeRequest, EntityType> getEntityTypeSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).getEntityTypeSettings();
    }

    public UnaryCallSettings<CreateEntityTypeRequest, EntityType> createEntityTypeSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).createEntityTypeSettings();
    }

    public UnaryCallSettings<UpdateEntityTypeRequest, EntityType> updateEntityTypeSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).updateEntityTypeSettings();
    }

    public UnaryCallSettings<DeleteEntityTypeRequest, Empty> deleteEntityTypeSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).deleteEntityTypeSettings();
    }

    public UnaryCallSettings<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchUpdateEntityTypesSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchUpdateEntityTypesOperationSettings();
    }

    public UnaryCallSettings<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchDeleteEntityTypesSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchDeleteEntityTypesOperationSettings();
    }

    public UnaryCallSettings<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchCreateEntitiesSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchCreateEntitiesOperationSettings();
    }

    public UnaryCallSettings<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchUpdateEntitiesSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchUpdateEntitiesOperationSettings();
    }

    public UnaryCallSettings<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchDeleteEntitiesSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationSettings() {
        return ((EntityTypesStubSettings) getStubSettings()).batchDeleteEntitiesOperationSettings();
    }

    public static final EntityTypesSettings create(EntityTypesStubSettings entityTypesStubSettings) throws IOException {
        return new Builder(entityTypesStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return EntityTypesStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return EntityTypesStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return EntityTypesStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return EntityTypesStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return EntityTypesStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return EntityTypesStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return EntityTypesStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected EntityTypesSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder extends ClientSettings.Builder<EntityTypesSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(EntityTypesStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(EntityTypesStubSettings.newBuilder());
        }

        protected Builder(EntityTypesSettings entityTypesSettings) {
            super(entityTypesSettings.getStubSettings().toBuilder());
        }

        protected Builder(EntityTypesStubSettings.Builder builder) {
            super(builder);
        }

        public EntityTypesStubSettings.Builder getStubSettingsBuilder() {
            return (EntityTypesStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public PagedCallSettings.Builder<ListEntityTypesRequest, ListEntityTypesResponse, EntityTypesClient.ListEntityTypesPagedResponse> listEntityTypesSettings() {
            return getStubSettingsBuilder().listEntityTypesSettings();
        }

        public UnaryCallSettings.Builder<GetEntityTypeRequest, EntityType> getEntityTypeSettings() {
            return getStubSettingsBuilder().getEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<CreateEntityTypeRequest, EntityType> createEntityTypeSettings() {
            return getStubSettingsBuilder().createEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<UpdateEntityTypeRequest, EntityType> updateEntityTypeSettings() {
            return getStubSettingsBuilder().updateEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<DeleteEntityTypeRequest, Empty> deleteEntityTypeSettings() {
            return getStubSettingsBuilder().deleteEntityTypeSettings();
        }

        public UnaryCallSettings.Builder<BatchUpdateEntityTypesRequest, Operation> batchUpdateEntityTypesSettings() {
            return getStubSettingsBuilder().batchUpdateEntityTypesSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchUpdateEntityTypesRequest, BatchUpdateEntityTypesResponse, Struct> batchUpdateEntityTypesOperationSettings() {
            return getStubSettingsBuilder().batchUpdateEntityTypesOperationSettings();
        }

        public UnaryCallSettings.Builder<BatchDeleteEntityTypesRequest, Operation> batchDeleteEntityTypesSettings() {
            return getStubSettingsBuilder().batchDeleteEntityTypesSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchDeleteEntityTypesRequest, Empty, Struct> batchDeleteEntityTypesOperationSettings() {
            return getStubSettingsBuilder().batchDeleteEntityTypesOperationSettings();
        }

        public UnaryCallSettings.Builder<BatchCreateEntitiesRequest, Operation> batchCreateEntitiesSettings() {
            return getStubSettingsBuilder().batchCreateEntitiesSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchCreateEntitiesRequest, Empty, Struct> batchCreateEntitiesOperationSettings() {
            return getStubSettingsBuilder().batchCreateEntitiesOperationSettings();
        }

        public UnaryCallSettings.Builder<BatchUpdateEntitiesRequest, Operation> batchUpdateEntitiesSettings() {
            return getStubSettingsBuilder().batchUpdateEntitiesSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchUpdateEntitiesRequest, Empty, Struct> batchUpdateEntitiesOperationSettings() {
            return getStubSettingsBuilder().batchUpdateEntitiesOperationSettings();
        }

        public UnaryCallSettings.Builder<BatchDeleteEntitiesRequest, Operation> batchDeleteEntitiesSettings() {
            return getStubSettingsBuilder().batchDeleteEntitiesSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<BatchDeleteEntitiesRequest, Empty, Struct> batchDeleteEntitiesOperationSettings() {
            return getStubSettingsBuilder().batchDeleteEntitiesOperationSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public EntityTypesSettings build() throws IOException {
            return new EntityTypesSettings(this);
        }
    }
}
