package com.google.cloud.texttospeech.p050v1.stub;

import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.texttospeech.p050v1.ListVoicesRequest;
import com.google.cloud.texttospeech.p050v1.ListVoicesResponse;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechRequest;
import com.google.cloud.texttospeech.p050v1.SynthesizeSpeechResponse;
import com.google.longrunning.stub.GrpcOperationsStub;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public class GrpcTextToSpeechStub extends TextToSpeechStub {
    private static final MethodDescriptor<ListVoicesRequest, ListVoicesResponse> listVoicesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.texttospeech.v1.TextToSpeech/ListVoices").setRequestMarshaller(ProtoUtils.marshaller(ListVoicesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListVoicesResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.cloud.texttospeech.v1.TextToSpeech/SynthesizeSpeech").setRequestMarshaller(ProtoUtils.marshaller(SynthesizeSpeechRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(SynthesizeSpeechResponse.getDefaultInstance())).build();
    private final BackgroundResource backgroundResources;
    private final GrpcStubCallableFactory callableFactory;
    private final UnaryCallable<ListVoicesRequest, ListVoicesResponse> listVoicesCallable;
    private final GrpcOperationsStub operationsStub;
    private final UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechCallable;

    public static final GrpcTextToSpeechStub create(TextToSpeechStubSettings textToSpeechStubSettings) throws IOException {
        return new GrpcTextToSpeechStub(textToSpeechStubSettings, ClientContext.create(textToSpeechStubSettings));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.texttospeech.v1.stub.TextToSpeechStubSettings] */
    public static final GrpcTextToSpeechStub create(ClientContext clientContext) throws IOException {
        return new GrpcTextToSpeechStub(TextToSpeechStubSettings.newBuilder().build(), clientContext);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.cloud.texttospeech.v1.stub.TextToSpeechStubSettings] */
    public static final GrpcTextToSpeechStub create(ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        return new GrpcTextToSpeechStub(TextToSpeechStubSettings.newBuilder().build(), clientContext, grpcStubCallableFactory);
    }

    protected GrpcTextToSpeechStub(TextToSpeechStubSettings textToSpeechStubSettings, ClientContext clientContext) throws IOException {
        this(textToSpeechStubSettings, clientContext, new GrpcTextToSpeechCallableFactory());
    }

    protected GrpcTextToSpeechStub(TextToSpeechStubSettings textToSpeechStubSettings, ClientContext clientContext, GrpcStubCallableFactory grpcStubCallableFactory) throws IOException {
        this.callableFactory = grpcStubCallableFactory;
        this.operationsStub = GrpcOperationsStub.create(clientContext, grpcStubCallableFactory);
        GrpcCallSettings build = GrpcCallSettings.newBuilder().setMethodDescriptor(listVoicesMethodDescriptor).build();
        GrpcCallSettings build2 = GrpcCallSettings.newBuilder().setMethodDescriptor(synthesizeSpeechMethodDescriptor).build();
        this.listVoicesCallable = grpcStubCallableFactory.createUnaryCallable(build, textToSpeechStubSettings.listVoicesSettings(), clientContext);
        this.synthesizeSpeechCallable = grpcStubCallableFactory.createUnaryCallable(build2, textToSpeechStubSettings.synthesizeSpeechSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    public GrpcOperationsStub getOperationsStub() {
        return this.operationsStub;
    }

    @Override // com.google.cloud.texttospeech.p050v1.stub.TextToSpeechStub
    public UnaryCallable<ListVoicesRequest, ListVoicesResponse> listVoicesCallable() {
        return this.listVoicesCallable;
    }

    @Override // com.google.cloud.texttospeech.p050v1.stub.TextToSpeechStub
    public UnaryCallable<SynthesizeSpeechRequest, SynthesizeSpeechResponse> synthesizeSpeechCallable() {
        return this.synthesizeSpeechCallable;
    }

    @Override // com.google.cloud.texttospeech.p050v1.stub.TextToSpeechStub, java.lang.AutoCloseable
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
