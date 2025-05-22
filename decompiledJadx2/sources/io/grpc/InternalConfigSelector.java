package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.LoadBalancer;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class InternalConfigSelector {
    public static final Attributes.Key<InternalConfigSelector> KEY = Attributes.Key.create("io.grpc.config-selector");

    public abstract Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Result {
        private final Object config;

        @Nullable
        public ClientInterceptor interceptor;
        private final Status status;

        private Result(Status status, Object obj, ClientInterceptor clientInterceptor) {
            this.status = (Status) Preconditions.checkNotNull(status, "status");
            this.config = obj;
            this.interceptor = clientInterceptor;
        }

        public static Result forError(Status status) {
            Preconditions.checkArgument(!status.isOk(), "status is OK");
            return new Result(status, null, null);
        }

        public Status getStatus() {
            return this.status;
        }

        public Object getConfig() {
            return this.config;
        }

        @Nullable
        public ClientInterceptor getInterceptor() {
            return this.interceptor;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Builder {
            private Object config;
            private ClientInterceptor interceptor;

            private Builder() {
            }

            public Builder setConfig(Object obj) {
                this.config = Preconditions.checkNotNull(obj, "config");
                return this;
            }

            public Builder setInterceptor(ClientInterceptor clientInterceptor) {
                this.interceptor = (ClientInterceptor) Preconditions.checkNotNull(clientInterceptor, "interceptor");
                return this;
            }

            public Result build() {
                Preconditions.checkState(this.config != null, "config is not set");
                return new Result(Status.f8305OK, this.config, this.interceptor);
            }
        }
    }
}
