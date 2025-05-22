package com.google.cloud.dialogflow.v2beta1;

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
import com.google.cloud.dialogflow.v2beta1.DocumentsClient;
import com.google.cloud.dialogflow.v2beta1.stub.DocumentsStubSettings;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes3.dex */
public class DocumentsSettings extends ClientSettings<DocumentsSettings> {
    public PagedCallSettings<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> listDocumentsSettings() {
        return ((DocumentsStubSettings) getStubSettings()).listDocumentsSettings();
    }

    public UnaryCallSettings<GetDocumentRequest, Document> getDocumentSettings() {
        return ((DocumentsStubSettings) getStubSettings()).getDocumentSettings();
    }

    public UnaryCallSettings<CreateDocumentRequest, Operation> createDocumentSettings() {
        return ((DocumentsStubSettings) getStubSettings()).createDocumentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationSettings() {
        return ((DocumentsStubSettings) getStubSettings()).createDocumentOperationSettings();
    }

    public UnaryCallSettings<DeleteDocumentRequest, Operation> deleteDocumentSettings() {
        return ((DocumentsStubSettings) getStubSettings()).deleteDocumentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationSettings() {
        return ((DocumentsStubSettings) getStubSettings()).deleteDocumentOperationSettings();
    }

    public UnaryCallSettings<UpdateDocumentRequest, Operation> updateDocumentSettings() {
        return ((DocumentsStubSettings) getStubSettings()).updateDocumentSettings();
    }

    @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
    public OperationCallSettings<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationSettings() {
        return ((DocumentsStubSettings) getStubSettings()).updateDocumentOperationSettings();
    }

    public UnaryCallSettings<ReloadDocumentRequest, Operation> reloadDocumentSettings() {
        return ((DocumentsStubSettings) getStubSettings()).reloadDocumentSettings();
    }

    public static final DocumentsSettings create(DocumentsStubSettings documentsStubSettings) throws IOException {
        return new Builder(documentsStubSettings.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return DocumentsStubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return DocumentsStubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return DocumentsStubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return DocumentsStubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return DocumentsStubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return DocumentsStubSettings.defaultTransportChannelProvider();
    }

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return DocumentsStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected DocumentsSettings(Builder builder) throws IOException {
        super(builder);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder extends ClientSettings.Builder<DocumentsSettings, Builder> {
        static /* synthetic */ Builder access$000() {
            return createDefault();
        }

        protected Builder() throws IOException {
            this((ClientContext) null);
        }

        protected Builder(ClientContext clientContext) {
            super(DocumentsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(DocumentsStubSettings.newBuilder());
        }

        protected Builder(DocumentsSettings documentsSettings) {
            super(documentsSettings.getStubSettings().toBuilder());
        }

        protected Builder(DocumentsStubSettings.Builder builder) {
            super(builder);
        }

        public DocumentsStubSettings.Builder getStubSettingsBuilder() {
            return (DocumentsStubSettings.Builder) getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> apiFunction) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(getStubSettingsBuilder().unaryMethodSettingsBuilders(), apiFunction);
            return this;
        }

        public PagedCallSettings.Builder<ListDocumentsRequest, ListDocumentsResponse, DocumentsClient.ListDocumentsPagedResponse> listDocumentsSettings() {
            return getStubSettingsBuilder().listDocumentsSettings();
        }

        public UnaryCallSettings.Builder<GetDocumentRequest, Document> getDocumentSettings() {
            return getStubSettingsBuilder().getDocumentSettings();
        }

        public UnaryCallSettings.Builder<CreateDocumentRequest, Operation> createDocumentSettings() {
            return getStubSettingsBuilder().createDocumentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<CreateDocumentRequest, Document, KnowledgeOperationMetadata> createDocumentOperationSettings() {
            return getStubSettingsBuilder().createDocumentOperationSettings();
        }

        public UnaryCallSettings.Builder<DeleteDocumentRequest, Operation> deleteDocumentSettings() {
            return getStubSettingsBuilder().deleteDocumentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<DeleteDocumentRequest, Empty, KnowledgeOperationMetadata> deleteDocumentOperationSettings() {
            return getStubSettingsBuilder().deleteDocumentOperationSettings();
        }

        public UnaryCallSettings.Builder<UpdateDocumentRequest, Operation> updateDocumentSettings() {
            return getStubSettingsBuilder().updateDocumentSettings();
        }

        @BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
        public OperationCallSettings.Builder<UpdateDocumentRequest, Document, KnowledgeOperationMetadata> updateDocumentOperationSettings() {
            return getStubSettingsBuilder().updateDocumentOperationSettings();
        }

        public UnaryCallSettings.Builder<ReloadDocumentRequest, Operation> reloadDocumentSettings() {
            return getStubSettingsBuilder().reloadDocumentSettings();
        }

        @Override // com.google.api.gax.rpc.ClientSettings.Builder
        public DocumentsSettings build() throws IOException {
            return new DocumentsSettings(this);
        }
    }
}
