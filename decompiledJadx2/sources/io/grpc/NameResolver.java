package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class NameResolver {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Factory {
        public abstract String getDefaultScheme();

        public abstract NameResolver newNameResolver(URI uri, Args args);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Listener {
        void onAddresses(List<EquivalentAddressGroup> list, Attributes attributes);

        void onError(Status status);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface ResolutionResultAttr {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class ServiceConfigParser {
        public abstract ConfigOrError parseServiceConfig(Map<String, ?> map);
    }

    public abstract String getServiceAuthority();

    public void refresh() {
    }

    public abstract void shutdown();

    public void start(final Listener listener) {
        if (listener instanceof Listener2) {
            start((Listener2) listener);
        } else {
            start(new Listener2() { // from class: io.grpc.NameResolver.1
                @Override // io.grpc.NameResolver.Listener2, io.grpc.NameResolver.Listener
                public void onError(Status status) {
                    listener.onError(status);
                }

                @Override // io.grpc.NameResolver.Listener2
                public void onResult(ResolutionResult resolutionResult) {
                    listener.onAddresses(resolutionResult.getAddresses(), resolutionResult.getAttributes());
                }
            });
        }
    }

    public void start(Listener2 listener2) {
        start((Listener) listener2);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Listener2 implements Listener {
        @Override // io.grpc.NameResolver.Listener
        public abstract void onError(Status status);

        public abstract void onResult(ResolutionResult resolutionResult);

        @Override // io.grpc.NameResolver.Listener
        @Deprecated
        public final void onAddresses(List<EquivalentAddressGroup> list, Attributes attributes) {
            onResult(ResolutionResult.newBuilder().setAddresses(list).setAttributes(attributes).build());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Args {

        @Nullable
        private final ChannelLogger channelLogger;
        private final int defaultPort;

        @Nullable
        private final Executor executor;
        private final ProxyDetector proxyDetector;

        @Nullable
        private final ScheduledExecutorService scheduledExecutorService;
        private final ServiceConfigParser serviceConfigParser;
        private final SynchronizationContext syncContext;

        private Args(Integer num, ProxyDetector proxyDetector, SynchronizationContext synchronizationContext, ServiceConfigParser serviceConfigParser, @Nullable ScheduledExecutorService scheduledExecutorService, @Nullable ChannelLogger channelLogger, @Nullable Executor executor) {
            this.defaultPort = ((Integer) Preconditions.checkNotNull(num, "defaultPort not set")).intValue();
            this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(proxyDetector, "proxyDetector not set");
            this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(synchronizationContext, "syncContext not set");
            this.serviceConfigParser = (ServiceConfigParser) Preconditions.checkNotNull(serviceConfigParser, "serviceConfigParser not set");
            this.scheduledExecutorService = scheduledExecutorService;
            this.channelLogger = channelLogger;
            this.executor = executor;
        }

        public int getDefaultPort() {
            return this.defaultPort;
        }

        public ProxyDetector getProxyDetector() {
            return this.proxyDetector;
        }

        public SynchronizationContext getSynchronizationContext() {
            return this.syncContext;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
            if (scheduledExecutorService != null) {
                return scheduledExecutorService;
            }
            throw new IllegalStateException("ScheduledExecutorService not set in Builder");
        }

        public ServiceConfigParser getServiceConfigParser() {
            return this.serviceConfigParser;
        }

        public ChannelLogger getChannelLogger() {
            ChannelLogger channelLogger = this.channelLogger;
            if (channelLogger != null) {
                return channelLogger;
            }
            throw new IllegalStateException("ChannelLogger is not set in Builder");
        }

        @Nullable
        public Executor getOffloadExecutor() {
            return this.executor;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("defaultPort", this.defaultPort).add("proxyDetector", this.proxyDetector).add("syncContext", this.syncContext).add("serviceConfigParser", this.serviceConfigParser).add("scheduledExecutorService", this.scheduledExecutorService).add("channelLogger", this.channelLogger).add("executor", this.executor).toString();
        }

        public Builder toBuilder() {
            Builder builder = new Builder();
            builder.setDefaultPort(this.defaultPort);
            builder.setProxyDetector(this.proxyDetector);
            builder.setSynchronizationContext(this.syncContext);
            builder.setServiceConfigParser(this.serviceConfigParser);
            builder.setScheduledExecutorService(this.scheduledExecutorService);
            builder.setChannelLogger(this.channelLogger);
            builder.setOffloadExecutor(this.executor);
            return builder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Builder {
            private ChannelLogger channelLogger;
            private Integer defaultPort;
            private Executor executor;
            private ProxyDetector proxyDetector;
            private ScheduledExecutorService scheduledExecutorService;
            private ServiceConfigParser serviceConfigParser;
            private SynchronizationContext syncContext;

            Builder() {
            }

            public Builder setDefaultPort(int i) {
                this.defaultPort = Integer.valueOf(i);
                return this;
            }

            public Builder setProxyDetector(ProxyDetector proxyDetector) {
                this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(proxyDetector);
                return this;
            }

            public Builder setSynchronizationContext(SynchronizationContext synchronizationContext) {
                this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(synchronizationContext);
                return this;
            }

            public Builder setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
                this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService);
                return this;
            }

            public Builder setServiceConfigParser(ServiceConfigParser serviceConfigParser) {
                this.serviceConfigParser = (ServiceConfigParser) Preconditions.checkNotNull(serviceConfigParser);
                return this;
            }

            public Builder setChannelLogger(ChannelLogger channelLogger) {
                this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger);
                return this;
            }

            public Builder setOffloadExecutor(Executor executor) {
                this.executor = executor;
                return this;
            }

            public Args build() {
                return new Args(this.defaultPort, this.proxyDetector, this.syncContext, this.serviceConfigParser, this.scheduledExecutorService, this.channelLogger, this.executor);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ResolutionResult {
        private final List<EquivalentAddressGroup> addresses;
        private final Attributes attributes;

        @Nullable
        private final ConfigOrError serviceConfig;

        ResolutionResult(List<EquivalentAddressGroup> list, Attributes attributes, ConfigOrError configOrError) {
            this.addresses = Collections.unmodifiableList(new ArrayList(list));
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes, "attributes");
            this.serviceConfig = configOrError;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addresses).setAttributes(this.attributes).setServiceConfig(this.serviceConfig);
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addresses;
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        @Nullable
        public ConfigOrError getServiceConfig() {
            return this.serviceConfig;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("addresses", this.addresses).add("attributes", this.attributes).add("serviceConfig", this.serviceConfig).toString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResolutionResult)) {
                return false;
            }
            ResolutionResult resolutionResult = (ResolutionResult) obj;
            return Objects.equal(this.addresses, resolutionResult.addresses) && Objects.equal(this.attributes, resolutionResult.attributes) && Objects.equal(this.serviceConfig, resolutionResult.serviceConfig);
        }

        public int hashCode() {
            return Objects.hashCode(this.addresses, this.attributes, this.serviceConfig);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Builder {
            private List<EquivalentAddressGroup> addresses = Collections.emptyList();
            private Attributes attributes = Attributes.EMPTY;

            @Nullable
            private ConfigOrError serviceConfig;

            Builder() {
            }

            public Builder setAddresses(List<EquivalentAddressGroup> list) {
                this.addresses = list;
                return this;
            }

            public Builder setAttributes(Attributes attributes) {
                this.attributes = attributes;
                return this;
            }

            public Builder setServiceConfig(@Nullable ConfigOrError configOrError) {
                this.serviceConfig = configOrError;
                return this;
            }

            public ResolutionResult build() {
                return new ResolutionResult(this.addresses, this.attributes, this.serviceConfig);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ConfigOrError {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Object config;
        private final Status status;

        public static ConfigOrError fromConfig(Object obj) {
            return new ConfigOrError(obj);
        }

        public static ConfigOrError fromError(Status status) {
            return new ConfigOrError(status);
        }

        private ConfigOrError(Object obj) {
            this.config = Preconditions.checkNotNull(obj, "config");
            this.status = null;
        }

        private ConfigOrError(Status status) {
            this.config = null;
            this.status = (Status) Preconditions.checkNotNull(status, "status");
            Preconditions.checkArgument(!status.isOk(), "cannot use OK status: %s", status);
        }

        @Nullable
        public Object getConfig() {
            return this.config;
        }

        @Nullable
        public Status getError() {
            return this.status;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ConfigOrError configOrError = (ConfigOrError) obj;
            return Objects.equal(this.status, configOrError.status) && Objects.equal(this.config, configOrError.config);
        }

        public int hashCode() {
            return Objects.hashCode(this.status, this.config);
        }

        public String toString() {
            if (this.config != null) {
                return MoreObjects.toStringHelper(this).add("config", this.config).toString();
            }
            return MoreObjects.toStringHelper(this).add("error", this.status).toString();
        }
    }
}
