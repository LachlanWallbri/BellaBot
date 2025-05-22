package com.google.cloud.dialogflow.v2beta1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.DetectIntentRequest;
import com.google.cloud.dialogflow.v2beta1.DetectIntentResponse;
import com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentRequest;
import com.google.cloud.dialogflow.v2beta1.StreamingDetectIntentResponse;
import com.google.common.collect.ImmutableMap;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
/* loaded from: classes3.dex */
public class GrpcSessionsStub extends SessionsStub {
    private static final MethodDescriptor<DetectIntentRequest, DetectIntentResponse> detectIntentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.dialogflow.v2beta1.Sessions/DetectIntent").setRequestMarshaller(ProtoUtils.marshaller(DetectIntentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(DetectIntentResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName("google.cloud.dialogflow.v2beta1.Sessions/StreamingDetectIntent").setRequestMarshaller(ProtoUtils.marshaller(StreamingDetectIntentRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(StreamingDetectIntentResponse.getDefaultInstance())).build();
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<DetectIntentRequest, DetectIntentResponse> detectIntentCallable;
    private final BidiStreamingCallable<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentCallable;

    public static final GrpcSessionsStub create(SessionsStubSettings sessionsStubSettings) throws IOException {
        return new GrpcSessionsStub(sessionsStubSettings, ClientContext.create(sessionsStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.SessionsStubSettings] */
    public static final GrpcSessionsStub create(ClientContext clientContext) throws IOException {
        return new GrpcSessionsStub(SessionsStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.dialogflow.v2beta1.stub.SessionsStubSettings] */
    public static final GrpcSessionsStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcSessionsStub(SessionsStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcSessionsStub(SessionsStubSettings sessionsStubSettings, ClientContext clientContext) throws IOException {
        this(sessionsStubSettings, clientContext, new GrpcSessionsCallableFactory());
    }

    protected GrpcSessionsStub(SessionsStubSettings sessionsStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(detectIntentMethodDescriptor).setParamsExtractor(new RequestParamsExtractor<DetectIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.stub.GrpcSessionsStub.1
            @Override // com.google.api.gax.rpc.RequestParamsExtractor
            public Map<String, String> extract(DetectIntentRequest detectIntentRequest) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                builder.put("session", String.valueOf(detectIntentRequest.getSession()));
                return builder.build();
            }
        }).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(streamingDetectIntentMethodDescriptor).build();
        this.detectIntentCallable = grpcStubCallableFactory.createUnaryCallable(build, sessionsStubSettings.detectIntentSettings(), clientContext);
        this.streamingDetectIntentCallable = grpcStubCallableFactory.createBidiStreamingCallable(build2, sessionsStubSettings.streamingDetectIntentSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionsStub
    public UnaryCallable<DetectIntentRequest, DetectIntentResponse> detectIntentCallable() {
        return this.detectIntentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionsStub
    public BidiStreamingCallable<StreamingDetectIntentRequest, StreamingDetectIntentResponse> streamingDetectIntentCallable() {
        return this.streamingDetectIntentCallable;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.stub.SessionsStub, java.lang.AutoCloseable
    public final void close() {
        shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        this.backgroundResources.shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return this.backgroundResources.isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return this.backgroundResources.isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        this.backgroundResources.shutdownNow();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.backgroundResources.awaitTermination(j, timeUnit);
    }
}
