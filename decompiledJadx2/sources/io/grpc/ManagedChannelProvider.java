package io.grpc;

import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ManagedChannelProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForAddress(String str, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> builderForTarget(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int priority();

    public static ManagedChannelProvider provider() {
        ManagedChannelProvider provider = ManagedChannelRegistry.getDefaultRegistry().provider();
        if (provider != null) {
            return provider;
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NewChannelBuilderResult newChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return NewChannelBuilderResult.error("ChannelCredentials are unsupported");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class NewChannelBuilderResult {
        private final ManagedChannelBuilder<?> channelBuilder;
        private final String error;

        private NewChannelBuilderResult(ManagedChannelBuilder<?> managedChannelBuilder, String str) {
            this.channelBuilder = managedChannelBuilder;
            this.error = str;
        }

        public static NewChannelBuilderResult channelBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
            return new NewChannelBuilderResult((ManagedChannelBuilder) Preconditions.checkNotNull(managedChannelBuilder), null);
        }

        public static NewChannelBuilderResult error(String str) {
            return new NewChannelBuilderResult(null, (String) Preconditions.checkNotNull(str));
        }

        public ManagedChannelBuilder<?> getChannelBuilder() {
            return this.channelBuilder;
        }

        public String getError() {
            return this.error;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }
}
