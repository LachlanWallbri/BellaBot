package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.CompositeCallCredentials;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.MetadataApplierImpl;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class CallCredentialsApplyingTransportFactory implements ClientTransportFactory {
    private final Executor appExecutor;
    private final CallCredentials channelCallCredentials;
    private final ClientTransportFactory delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallCredentialsApplyingTransportFactory(ClientTransportFactory clientTransportFactory, CallCredentials callCredentials, Executor executor) {
        this.delegate = (ClientTransportFactory) Preconditions.checkNotNull(clientTransportFactory, "delegate");
        this.channelCallCredentials = callCredentials;
        this.appExecutor = (Executor) Preconditions.checkNotNull(executor, "appExecutor");
    }

    @Override // io.grpc.internal.ClientTransportFactory
    public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
        return new CallCredentialsApplyingTransport(this.delegate.newClientTransport(socketAddress, clientTransportOptions, channelLogger), clientTransportOptions.getAuthority());
    }

    @Override // io.grpc.internal.ClientTransportFactory
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.delegate.getScheduledExecutorService();
    }

    @Override // io.grpc.internal.ClientTransportFactory
    public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class CallCredentialsApplyingTransport extends ForwardingConnectionClientTransport {
        private final String authority;
        private final ConnectionClientTransport delegate;
        private Status savedShutdownNowStatus;
        private Status savedShutdownStatus;
        private volatile Status shutdownStatus;
        private final AtomicInteger pendingApplier = new AtomicInteger(-2147483647);
        private final MetadataApplierImpl.MetadataApplierListener applierListener = new MetadataApplierImpl.MetadataApplierListener() { // from class: io.grpc.internal.CallCredentialsApplyingTransportFactory.CallCredentialsApplyingTransport.1
            @Override // io.grpc.internal.MetadataApplierImpl.MetadataApplierListener
            public void onComplete() {
                if (CallCredentialsApplyingTransport.this.pendingApplier.decrementAndGet() == 0) {
                    CallCredentialsApplyingTransport.this.maybeShutdown();
                }
            }
        };

        CallCredentialsApplyingTransport(ConnectionClientTransport connectionClientTransport, String str) {
            this.delegate = (ConnectionClientTransport) Preconditions.checkNotNull(connectionClientTransport, "delegate");
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport
        protected ConnectionClientTransport delegate() {
            return this.delegate;
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ClientTransport
        public ClientStream newStream(final MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, final CallOptions callOptions) {
            CallCredentials credentials = callOptions.getCredentials();
            if (credentials == null) {
                credentials = CallCredentialsApplyingTransportFactory.this.channelCallCredentials;
            } else if (CallCredentialsApplyingTransportFactory.this.channelCallCredentials != null) {
                credentials = new CompositeCallCredentials(CallCredentialsApplyingTransportFactory.this.channelCallCredentials, credentials);
            }
            if (credentials != null) {
                MetadataApplierImpl metadataApplierImpl = new MetadataApplierImpl(this.delegate, methodDescriptor, metadata, callOptions, this.applierListener);
                if (this.pendingApplier.incrementAndGet() > 0) {
                    this.applierListener.onComplete();
                    return new FailingClientStream(this.shutdownStatus);
                }
                try {
                    credentials.applyRequestMetadata(new CallCredentials.RequestInfo() { // from class: io.grpc.internal.CallCredentialsApplyingTransportFactory.CallCredentialsApplyingTransport.2
                        @Override // io.grpc.CallCredentials.RequestInfo
                        public MethodDescriptor<?, ?> getMethodDescriptor() {
                            return methodDescriptor;
                        }

                        @Override // io.grpc.CallCredentials.RequestInfo
                        public SecurityLevel getSecurityLevel() {
                            return (SecurityLevel) MoreObjects.firstNonNull(CallCredentialsApplyingTransport.this.delegate.getAttributes().get(GrpcAttributes.ATTR_SECURITY_LEVEL), SecurityLevel.NONE);
                        }

                        @Override // io.grpc.CallCredentials.RequestInfo
                        public String getAuthority() {
                            return (String) MoreObjects.firstNonNull(callOptions.getAuthority(), CallCredentialsApplyingTransport.this.authority);
                        }

                        @Override // io.grpc.CallCredentials.RequestInfo
                        public Attributes getTransportAttrs() {
                            return CallCredentialsApplyingTransport.this.delegate.getAttributes();
                        }
                    }, (Executor) MoreObjects.firstNonNull(callOptions.getExecutor(), CallCredentialsApplyingTransportFactory.this.appExecutor), metadataApplierImpl);
                } catch (Throwable th) {
                    metadataApplierImpl.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(th));
                }
                return metadataApplierImpl.returnStream();
            }
            if (this.pendingApplier.get() >= 0) {
                return new FailingClientStream(this.shutdownStatus);
            }
            return this.delegate.newStream(methodDescriptor, metadata, callOptions);
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ManagedClientTransport
        public void shutdown(Status status) {
            Preconditions.checkNotNull(status, "status");
            synchronized (this) {
                if (this.pendingApplier.get() < 0) {
                    this.shutdownStatus = status;
                    this.pendingApplier.addAndGet(Integer.MAX_VALUE);
                    if (this.pendingApplier.get() != 0) {
                        this.savedShutdownStatus = status;
                    } else {
                        super.shutdown(status);
                    }
                }
            }
        }

        @Override // io.grpc.internal.ForwardingConnectionClientTransport, io.grpc.internal.ManagedClientTransport
        public void shutdownNow(Status status) {
            Preconditions.checkNotNull(status, "status");
            synchronized (this) {
                if (this.pendingApplier.get() < 0) {
                    this.shutdownStatus = status;
                    this.pendingApplier.addAndGet(Integer.MAX_VALUE);
                } else if (this.savedShutdownNowStatus != null) {
                    return;
                }
                if (this.pendingApplier.get() != 0) {
                    this.savedShutdownNowStatus = status;
                } else {
                    super.shutdownNow(status);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void maybeShutdown() {
            synchronized (this) {
                if (this.pendingApplier.get() != 0) {
                    return;
                }
                Status status = this.savedShutdownStatus;
                Status status2 = this.savedShutdownNowStatus;
                this.savedShutdownStatus = null;
                this.savedShutdownNowStatus = null;
                if (status != null) {
                    super.shutdown(status);
                }
                if (status2 != null) {
                    super.shutdownNow(status2);
                }
            }
        }
    }
}
