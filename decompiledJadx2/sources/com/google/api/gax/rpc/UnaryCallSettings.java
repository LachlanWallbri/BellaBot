package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public class UnaryCallSettings<RequestT, ResponseT> {
    private final RetrySettings retrySettings;
    private final ImmutableSet<StatusCode.Code> retryableCodes;

    public final Set<StatusCode.Code> getRetryableCodes() {
        return this.retryableCodes;
    }

    public final RetrySettings getRetrySettings() {
        return this.retrySettings;
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newUnaryCallSettingsBuilder() {
        return new Builder<>();
    }

    public Builder<RequestT, ResponseT> toBuilder() {
        return new Builder<>(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UnaryCallSettings(Builder<RequestT, ResponseT> builder) {
        this.retryableCodes = ImmutableSet.copyOf((Collection) ((Builder) builder).retryableCodes);
        this.retrySettings = ((Builder) builder).retrySettingsBuilder.build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("retryableCodes", this.retryableCodes).add("retrySettings", this.retrySettings).toString();
    }

    public int hashCode() {
        RetrySettings retrySettings = this.retrySettings;
        int hashCode = ((retrySettings == null ? 0 : retrySettings.hashCode()) + 43) * 43;
        ImmutableSet<StatusCode.Code> immutableSet = this.retryableCodes;
        return hashCode + (immutableSet != null ? immutableSet.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnaryCallSettings unaryCallSettings = (UnaryCallSettings) obj;
        return this.retrySettings.equals(unaryCallSettings.retrySettings) && this.retryableCodes.equals(unaryCallSettings.retryableCodes);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT> {
        private RetrySettings.Builder retrySettingsBuilder;
        private Set<StatusCode.Code> retryableCodes;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
            this.retryableCodes = Sets.newHashSet();
            this.retrySettingsBuilder = RetrySettings.newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(UnaryCallSettings<RequestT, ResponseT> unaryCallSettings) {
            setRetryableCodes(((UnaryCallSettings) unaryCallSettings).retryableCodes);
            setRetrySettings(unaryCallSettings.getRetrySettings());
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> set) {
            this.retryableCodes = Sets.newHashSet(set);
            return this;
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(StatusCode.Code... codeArr) {
            setRetryableCodes(Sets.newHashSet(codeArr));
            return this;
        }

        public RetrySettings.Builder retrySettings() {
            return this.retrySettingsBuilder;
        }

        public Builder<RequestT, ResponseT> setRetrySettings(RetrySettings retrySettings) {
            this.retrySettingsBuilder = retrySettings.toBuilder();
            return this;
        }

        public Builder<RequestT, ResponseT> setSimpleTimeoutNoRetries(Duration duration) {
            setRetryableCodes(new StatusCode.Code[0]);
            setRetrySettings(RetrySettings.newBuilder().setTotalTimeout(duration).setInitialRetryDelay(Duration.ZERO).setRetryDelayMultiplier(1.0d).setMaxRetryDelay(Duration.ZERO).setInitialRpcTimeout(duration).setRpcTimeoutMultiplier(1.0d).setMaxRpcTimeout(duration).setMaxAttempts(1).build());
            return this;
        }

        public Set<StatusCode.Code> getRetryableCodes() {
            return this.retryableCodes;
        }

        public RetrySettings getRetrySettings() {
            return this.retrySettingsBuilder.build();
        }

        public UnaryCallSettings<RequestT, ResponseT> build() {
            return new UnaryCallSettings<>(this);
        }
    }
}
