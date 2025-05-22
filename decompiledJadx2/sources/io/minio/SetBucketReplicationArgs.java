package io.minio;

import io.minio.BucketArgs;
import io.minio.messages.ReplicationConfiguration;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetBucketReplicationArgs extends BucketArgs {
    private ReplicationConfiguration config;
    private String objectLockToken;

    public ReplicationConfiguration config() {
        return this.config;
    }

    public String objectLockToken() {
        return this.objectLockToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, SetBucketReplicationArgs> {
        private void validateConfig(ReplicationConfiguration replicationConfiguration) {
            validateNotNull(replicationConfiguration, "replication configuration");
        }

        private void validateObjectLockToken(String str) {
            validateNullOrNotEmptyString(str, "object lock token");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(SetBucketReplicationArgs setBucketReplicationArgs) {
            super.validate((Builder) setBucketReplicationArgs);
            validateConfig(setBucketReplicationArgs.config);
            validateObjectLockToken(setBucketReplicationArgs.objectLockToken);
        }

        public Builder config(final ReplicationConfiguration replicationConfiguration) {
            validateConfig(replicationConfiguration);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketReplicationArgs$Builder$cQiqcE3OvSJMcqT8nEZugqmvzko
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketReplicationArgs) obj).config = ReplicationConfiguration.this;
                }
            });
            return this;
        }

        public Builder objectLockToken(final String str) {
            validateObjectLockToken(str);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetBucketReplicationArgs$Builder$zXbvyNSKWkj2POBdzpNGovDGlVs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetBucketReplicationArgs) obj).objectLockToken = str;
                }
            });
            return this;
        }
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetBucketReplicationArgs) || !super.equals(obj)) {
            return false;
        }
        SetBucketReplicationArgs setBucketReplicationArgs = (SetBucketReplicationArgs) obj;
        return Objects.equals(this.config, setBucketReplicationArgs.config) && Objects.equals(this.objectLockToken, setBucketReplicationArgs.objectLockToken);
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config, this.objectLockToken);
    }
}
