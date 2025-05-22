package io.minio;

import io.minio.ObjectVersionArgs;
import io.minio.messages.Retention;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class SetObjectRetentionArgs extends ObjectVersionArgs {
    private boolean bypassGovernanceMode;
    private Retention config;

    public Retention config() {
        return this.config;
    }

    public boolean bypassGovernanceMode() {
        return this.bypassGovernanceMode;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectVersionArgs.Builder<Builder, SetObjectRetentionArgs> {
        private void validateConfig(Retention retention) {
            validateNotNull(retention, "retention configuration");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.ObjectArgs.Builder
        public void validate(SetObjectRetentionArgs setObjectRetentionArgs) {
            super.validate((Builder) setObjectRetentionArgs);
            validateConfig(setObjectRetentionArgs.config);
        }

        public Builder config(final Retention retention) {
            validateConfig(retention);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetObjectRetentionArgs$Builder$Li22NvOcYAOPwIGx9IdVrUAy_C0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetObjectRetentionArgs) obj).config = Retention.this;
                }
            });
            return this;
        }

        public Builder bypassGovernanceMode(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$SetObjectRetentionArgs$Builder$rD2mzr1PS_F163r22NJsU8F49uA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((SetObjectRetentionArgs) obj).bypassGovernanceMode = z;
                }
            });
            return this;
        }
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetObjectRetentionArgs) || !super.equals(obj)) {
            return false;
        }
        SetObjectRetentionArgs setObjectRetentionArgs = (SetObjectRetentionArgs) obj;
        return this.bypassGovernanceMode == setObjectRetentionArgs.bypassGovernanceMode && Objects.equals(this.config, setObjectRetentionArgs.config);
    }

    @Override // io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.config, Boolean.valueOf(this.bypassGovernanceMode));
    }
}
