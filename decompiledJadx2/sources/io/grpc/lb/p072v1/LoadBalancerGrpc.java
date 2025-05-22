package io.grpc.lb.p072v1;

import com.google.protobuf.Descriptors;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class LoadBalancerGrpc {
    private static final int METHODID_BALANCE_LOAD = 0;
    public static final String SERVICE_NAME = "grpc.lb.v1.LoadBalancer";
    private static volatile MethodDescriptor<LoadBalanceRequest, LoadBalanceResponse> getBalanceLoadMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private LoadBalancerGrpc() {
    }

    public static MethodDescriptor<LoadBalanceRequest, LoadBalanceResponse> getBalanceLoadMethod() {
        MethodDescriptor<LoadBalanceRequest, LoadBalanceResponse> methodDescriptor = getBalanceLoadMethod;
        if (methodDescriptor == null) {
            synchronized (LoadBalancerGrpc.class) {
                methodDescriptor = getBalanceLoadMethod;
                if (methodDescriptor == null) {
                    methodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "BalanceLoad")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoUtils.marshaller(LoadBalanceRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LoadBalanceResponse.getDefaultInstance())).setSchemaDescriptor(new LoadBalancerMethodDescriptorSupplier("BalanceLoad")).build();
                    getBalanceLoadMethod = methodDescriptor;
                }
            }
        }
        return methodDescriptor;
    }

    public static LoadBalancerStub newStub(Channel channel) {
        return (LoadBalancerStub) LoadBalancerStub.newStub(new AbstractStub.StubFactory<LoadBalancerStub>() { // from class: io.grpc.lb.v1.LoadBalancerGrpc.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public LoadBalancerStub newStub(Channel channel2, CallOptions callOptions) {
                return new LoadBalancerStub(channel2, callOptions);
            }
        }, channel);
    }

    public static LoadBalancerBlockingStub newBlockingStub(Channel channel) {
        return (LoadBalancerBlockingStub) LoadBalancerBlockingStub.newStub(new AbstractStub.StubFactory<LoadBalancerBlockingStub>() { // from class: io.grpc.lb.v1.LoadBalancerGrpc.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public LoadBalancerBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new LoadBalancerBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static LoadBalancerFutureStub newFutureStub(Channel channel) {
        return (LoadBalancerFutureStub) LoadBalancerFutureStub.newStub(new AbstractStub.StubFactory<LoadBalancerFutureStub>() { // from class: io.grpc.lb.v1.LoadBalancerGrpc.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public LoadBalancerFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new LoadBalancerFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class LoadBalancerImplBase implements BindableService {
        public StreamObserver<LoadBalanceRequest> balanceLoad(StreamObserver<LoadBalanceResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(LoadBalancerGrpc.getBalanceLoadMethod(), streamObserver);
        }

        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(LoadBalancerGrpc.getServiceDescriptor()).addMethod(LoadBalancerGrpc.getBalanceLoadMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(this, 0))).build();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LoadBalancerStub extends AbstractAsyncStub<LoadBalancerStub> {
        private LoadBalancerStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public LoadBalancerStub build(Channel channel, CallOptions callOptions) {
            return new LoadBalancerStub(channel, callOptions);
        }

        public StreamObserver<LoadBalanceRequest> balanceLoad(StreamObserver<LoadBalanceResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(LoadBalancerGrpc.getBalanceLoadMethod(), getCallOptions()), streamObserver);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LoadBalancerBlockingStub extends AbstractBlockingStub<LoadBalancerBlockingStub> {
        private LoadBalancerBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public LoadBalancerBlockingStub build(Channel channel, CallOptions callOptions) {
            return new LoadBalancerBlockingStub(channel, callOptions);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LoadBalancerFutureStub extends AbstractFutureStub<LoadBalancerFutureStub> {
        private LoadBalancerFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public LoadBalancerFutureStub build(Channel channel, CallOptions callOptions) {
            return new LoadBalancerFutureStub(channel, callOptions);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final LoadBalancerImplBase serviceImpl;

        MethodHandlers(LoadBalancerImplBase loadBalancerImplBase, int i) {
            this.serviceImpl = loadBalancerImplBase;
            this.methodId = i;
        }

        @Override // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            throw new AssertionError();
        }

        @Override // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                return (StreamObserver<Req>) this.serviceImpl.balanceLoad(streamObserver);
            }
            throw new AssertionError();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static abstract class LoadBalancerBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        LoadBalancerBaseDescriptorSupplier() {
        }

        @Override // io.grpc.protobuf.ProtoFileDescriptorSupplier
        public Descriptors.FileDescriptor getFileDescriptor() {
            return LoadBalancerProto.getDescriptor();
        }

        @Override // io.grpc.protobuf.ProtoServiceDescriptorSupplier
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("LoadBalancer");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LoadBalancerFileDescriptorSupplier extends LoadBalancerBaseDescriptorSupplier {
        LoadBalancerFileDescriptorSupplier() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LoadBalancerMethodDescriptorSupplier extends LoadBalancerBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        LoadBalancerMethodDescriptorSupplier(String str) {
            this.methodName = str;
        }

        @Override // io.grpc.protobuf.ProtoMethodDescriptorSupplier
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(this.methodName);
        }
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 == null) {
            synchronized (LoadBalancerGrpc.class) {
                serviceDescriptor2 = serviceDescriptor;
                if (serviceDescriptor2 == null) {
                    serviceDescriptor2 = ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new LoadBalancerFileDescriptorSupplier()).addMethod(getBalanceLoadMethod()).build();
                    serviceDescriptor = serviceDescriptor2;
                }
            }
        }
        return serviceDescriptor2;
    }
}
