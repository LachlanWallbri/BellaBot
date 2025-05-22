package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannelProvider;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ServerProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ServerBuilder<?> builderForPort(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int priority();

    public static ServerProvider provider() {
        ServerProvider provider = ServerRegistry.getDefaultRegistry().provider();
        if (provider != null) {
            return provider;
        }
        throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NewServerBuilderResult newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        return NewServerBuilderResult.error("ServerCredentials are unsupported");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class NewServerBuilderResult {
        private final String error;
        private final ServerBuilder<?> serverBuilder;

        private NewServerBuilderResult(ServerBuilder<?> serverBuilder, String str) {
            this.serverBuilder = serverBuilder;
            this.error = str;
        }

        public static NewServerBuilderResult serverBuilder(ServerBuilder<?> serverBuilder) {
            return new NewServerBuilderResult((ServerBuilder) Preconditions.checkNotNull(serverBuilder), null);
        }

        public static NewServerBuilderResult error(String str) {
            return new NewServerBuilderResult(null, (String) Preconditions.checkNotNull(str));
        }

        public ServerBuilder<?> getServerBuilder() {
            return this.serverBuilder;
        }

        public String getError() {
            return this.error;
        }
    }
}
