package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CompositeCallCredentials extends CallCredentials {
    private final CallCredentials credentials1;
    private final CallCredentials credentials2;

    @Override // io.grpc.CallCredentials
    public void thisUsesUnstableApi() {
    }

    public CompositeCallCredentials(CallCredentials callCredentials, CallCredentials callCredentials2) {
        this.credentials1 = (CallCredentials) Preconditions.checkNotNull(callCredentials, "creds1");
        this.credentials2 = (CallCredentials) Preconditions.checkNotNull(callCredentials2, "creds2");
    }

    @Override // io.grpc.CallCredentials
    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, CallCredentials.MetadataApplier metadataApplier) {
        this.credentials1.applyRequestMetadata(requestInfo, executor, new WrappingMetadataApplier(requestInfo, executor, metadataApplier, Context.current()));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class WrappingMetadataApplier extends CallCredentials.MetadataApplier {
        private final Executor appExecutor;
        private final Context context;
        private final CallCredentials.MetadataApplier delegate;
        private final CallCredentials.RequestInfo requestInfo;

        public WrappingMetadataApplier(CallCredentials.RequestInfo requestInfo, Executor executor, CallCredentials.MetadataApplier metadataApplier, Context context) {
            this.requestInfo = requestInfo;
            this.appExecutor = executor;
            this.delegate = (CallCredentials.MetadataApplier) Preconditions.checkNotNull(metadataApplier, "delegate");
            this.context = (Context) Preconditions.checkNotNull(context, "context");
        }

        @Override // io.grpc.CallCredentials.MetadataApplier
        public void apply(Metadata metadata) {
            Preconditions.checkNotNull(metadata, "headers");
            Context attach = this.context.attach();
            try {
                CompositeCallCredentials.this.credentials2.applyRequestMetadata(this.requestInfo, this.appExecutor, new CombiningMetadataApplier(this.delegate, metadata));
            } finally {
                this.context.detach(attach);
            }
        }

        @Override // io.grpc.CallCredentials.MetadataApplier
        public void fail(Status status) {
            this.delegate.fail(status);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class CombiningMetadataApplier extends CallCredentials.MetadataApplier {
        private final CallCredentials.MetadataApplier delegate;
        private final Metadata firstHeaders;

        public CombiningMetadataApplier(CallCredentials.MetadataApplier metadataApplier, Metadata metadata) {
            this.delegate = metadataApplier;
            this.firstHeaders = metadata;
        }

        @Override // io.grpc.CallCredentials.MetadataApplier
        public void apply(Metadata metadata) {
            Preconditions.checkNotNull(metadata, "headers");
            Metadata metadata2 = new Metadata();
            metadata2.merge(this.firstHeaders);
            metadata2.merge(metadata);
            this.delegate.apply(metadata2);
        }

        @Override // io.grpc.CallCredentials.MetadataApplier
        public void fail(Status status) {
            this.delegate.fail(status);
        }
    }
}
