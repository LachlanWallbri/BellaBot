package io.grpc;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ServerStreamTracer extends StreamTracer {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Factory {
        public abstract ServerStreamTracer newServerStreamTracer(String str, Metadata metadata);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class ServerCallInfo<ReqT, RespT> {
        public abstract Attributes getAttributes();

        @Nullable
        public abstract String getAuthority();

        public abstract MethodDescriptor<ReqT, RespT> getMethodDescriptor();
    }

    public Context filterContext(Context context) {
        return context;
    }

    @Deprecated
    public void serverCallStarted(ServerCall<?, ?> serverCall) {
    }

    public void serverCallStarted(ServerCallInfo<?, ?> serverCallInfo) {
        serverCallStarted(ReadOnlyServerCall.create(serverCallInfo));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Deprecated
    /* loaded from: classes7.dex */
    private static final class ReadOnlyServerCall<ReqT, RespT> extends ForwardingServerCall<ReqT, RespT> {
        private final ServerCallInfo<ReqT, RespT> callInfo;

        @Override // io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall, io.grpc.ServerCall
        public boolean isCancelled() {
            return false;
        }

        @Override // io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall, io.grpc.ServerCall
        public boolean isReady() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <ReqT, RespT> ReadOnlyServerCall<ReqT, RespT> create(ServerCallInfo<ReqT, RespT> serverCallInfo) {
            return new ReadOnlyServerCall<>(serverCallInfo);
        }

        private ReadOnlyServerCall(ServerCallInfo<ReqT, RespT> serverCallInfo) {
            this.callInfo = serverCallInfo;
        }

        @Override // io.grpc.ServerCall
        public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
            return this.callInfo.getMethodDescriptor();
        }

        @Override // io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall, io.grpc.ServerCall
        public Attributes getAttributes() {
            return this.callInfo.getAttributes();
        }

        @Override // io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall, io.grpc.ServerCall
        public String getAuthority() {
            return this.callInfo.getAuthority();
        }

        @Override // io.grpc.ForwardingServerCall, io.grpc.PartialForwardingServerCall
        protected ServerCall<ReqT, RespT> delegate() {
            throw new UnsupportedOperationException();
        }
    }
}
