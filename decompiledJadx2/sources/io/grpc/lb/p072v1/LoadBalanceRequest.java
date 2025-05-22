package io.grpc.lb.p072v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.lb.p072v1.ClientStats;
import io.grpc.lb.p072v1.InitialLoadBalanceRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class LoadBalanceRequest extends GeneratedMessageV3 implements LoadBalanceRequestOrBuilder {
    public static final int CLIENT_STATS_FIELD_NUMBER = 2;
    public static final int INITIAL_REQUEST_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int loadBalanceRequestTypeCase_;
    private Object loadBalanceRequestType_;
    private byte memoizedIsInitialized;
    private static final LoadBalanceRequest DEFAULT_INSTANCE = new LoadBalanceRequest();
    private static final Parser<LoadBalanceRequest> PARSER = new AbstractParser<LoadBalanceRequest>() { // from class: io.grpc.lb.v1.LoadBalanceRequest.1
        @Override // com.google.protobuf.Parser
        public LoadBalanceRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new LoadBalanceRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private LoadBalanceRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.loadBalanceRequestTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private LoadBalanceRequest() {
        this.loadBalanceRequestTypeCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new LoadBalanceRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private LoadBalanceRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            InitialLoadBalanceRequest.Builder builder = this.loadBalanceRequestTypeCase_ == 1 ? ((InitialLoadBalanceRequest) this.loadBalanceRequestType_).toBuilder() : null;
                            this.loadBalanceRequestType_ = codedInputStream.readMessage(InitialLoadBalanceRequest.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom((InitialLoadBalanceRequest) this.loadBalanceRequestType_);
                                this.loadBalanceRequestType_ = builder.buildPartial();
                            }
                            this.loadBalanceRequestTypeCase_ = 1;
                        } else if (readTag == 18) {
                            ClientStats.Builder builder2 = this.loadBalanceRequestTypeCase_ == 2 ? ((ClientStats) this.loadBalanceRequestType_).toBuilder() : null;
                            this.loadBalanceRequestType_ = codedInputStream.readMessage(ClientStats.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom((ClientStats) this.loadBalanceRequestType_);
                                this.loadBalanceRequestType_ = builder2.buildPartial();
                            }
                            this.loadBalanceRequestTypeCase_ = 2;
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalanceRequest.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum LoadBalanceRequestTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        INITIAL_REQUEST(1),
        CLIENT_STATS(2),
        LOADBALANCEREQUESTTYPE_NOT_SET(0);

        private final int value;

        LoadBalanceRequestTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static LoadBalanceRequestTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static LoadBalanceRequestTypeCase forNumber(int i) {
            if (i == 0) {
                return LOADBALANCEREQUESTTYPE_NOT_SET;
            }
            if (i == 1) {
                return INITIAL_REQUEST;
            }
            if (i != 2) {
                return null;
            }
            return CLIENT_STATS;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public LoadBalanceRequestTypeCase getLoadBalanceRequestTypeCase() {
        return LoadBalanceRequestTypeCase.forNumber(this.loadBalanceRequestTypeCase_);
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public boolean hasInitialRequest() {
        return this.loadBalanceRequestTypeCase_ == 1;
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public InitialLoadBalanceRequest getInitialRequest() {
        if (this.loadBalanceRequestTypeCase_ == 1) {
            return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
        }
        return InitialLoadBalanceRequest.getDefaultInstance();
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public InitialLoadBalanceRequestOrBuilder getInitialRequestOrBuilder() {
        if (this.loadBalanceRequestTypeCase_ == 1) {
            return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
        }
        return InitialLoadBalanceRequest.getDefaultInstance();
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public boolean hasClientStats() {
        return this.loadBalanceRequestTypeCase_ == 2;
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public ClientStats getClientStats() {
        if (this.loadBalanceRequestTypeCase_ == 2) {
            return (ClientStats) this.loadBalanceRequestType_;
        }
        return ClientStats.getDefaultInstance();
    }

    @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
    public ClientStatsOrBuilder getClientStatsOrBuilder() {
        if (this.loadBalanceRequestTypeCase_ == 2) {
            return (ClientStats) this.loadBalanceRequestType_;
        }
        return ClientStats.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.loadBalanceRequestTypeCase_ == 1) {
            codedOutputStream.writeMessage(1, (InitialLoadBalanceRequest) this.loadBalanceRequestType_);
        }
        if (this.loadBalanceRequestTypeCase_ == 2) {
            codedOutputStream.writeMessage(2, (ClientStats) this.loadBalanceRequestType_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.loadBalanceRequestTypeCase_ == 1 ? 0 + CodedOutputStream.computeMessageSize(1, (InitialLoadBalanceRequest) this.loadBalanceRequestType_) : 0;
        if (this.loadBalanceRequestTypeCase_ == 2) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, (ClientStats) this.loadBalanceRequestType_);
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LoadBalanceRequest)) {
            return super.equals(obj);
        }
        LoadBalanceRequest loadBalanceRequest = (LoadBalanceRequest) obj;
        if (!getLoadBalanceRequestTypeCase().equals(loadBalanceRequest.getLoadBalanceRequestTypeCase())) {
            return false;
        }
        int i = this.loadBalanceRequestTypeCase_;
        if (i == 1) {
            if (!getInitialRequest().equals(loadBalanceRequest.getInitialRequest())) {
                return false;
            }
        } else if (i == 2 && !getClientStats().equals(loadBalanceRequest.getClientStats())) {
            return false;
        }
        return this.unknownFields.equals(loadBalanceRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.loadBalanceRequestTypeCase_;
        if (i2 == 1) {
            i = ((hashCode2 * 37) + 1) * 53;
            hashCode = getInitialRequest().hashCode();
        } else {
            if (i2 == 2) {
                i = ((hashCode2 * 37) + 2) * 53;
                hashCode = getClientStats().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        hashCode2 = i + hashCode;
        int hashCode32 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static LoadBalanceRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static LoadBalanceRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static LoadBalanceRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static LoadBalanceRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(InputStream inputStream) throws IOException {
        return (LoadBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static LoadBalanceRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LoadBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (LoadBalanceRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static LoadBalanceRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LoadBalanceRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static LoadBalanceRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (LoadBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static LoadBalanceRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LoadBalanceRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LoadBalanceRequest loadBalanceRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(loadBalanceRequest);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LoadBalanceRequestOrBuilder {
        private SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> clientStatsBuilder_;
        private SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> initialRequestBuilder_;
        private int loadBalanceRequestTypeCase_;
        private Object loadBalanceRequestType_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(LoadBalanceRequest.class, Builder.class);
        }

        private Builder() {
            this.loadBalanceRequestTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.loadBalanceRequestTypeCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = LoadBalanceRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.loadBalanceRequestTypeCase_ = 0;
            this.loadBalanceRequestType_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_LoadBalanceRequest_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LoadBalanceRequest getDefaultInstanceForType() {
            return LoadBalanceRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public LoadBalanceRequest build() {
            LoadBalanceRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public LoadBalanceRequest buildPartial() {
            LoadBalanceRequest loadBalanceRequest = new LoadBalanceRequest(this);
            if (this.loadBalanceRequestTypeCase_ == 1) {
                SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
                if (singleFieldBuilderV3 == null) {
                    loadBalanceRequest.loadBalanceRequestType_ = this.loadBalanceRequestType_;
                } else {
                    loadBalanceRequest.loadBalanceRequestType_ = singleFieldBuilderV3.build();
                }
            }
            if (this.loadBalanceRequestTypeCase_ == 2) {
                SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV32 = this.clientStatsBuilder_;
                if (singleFieldBuilderV32 == null) {
                    loadBalanceRequest.loadBalanceRequestType_ = this.loadBalanceRequestType_;
                } else {
                    loadBalanceRequest.loadBalanceRequestType_ = singleFieldBuilderV32.build();
                }
            }
            loadBalanceRequest.loadBalanceRequestTypeCase_ = this.loadBalanceRequestTypeCase_;
            onBuilt();
            return loadBalanceRequest;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo4275clone() {
            return (Builder) super.mo4275clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof LoadBalanceRequest) {
                return mergeFrom((LoadBalanceRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(LoadBalanceRequest loadBalanceRequest) {
            if (loadBalanceRequest == LoadBalanceRequest.getDefaultInstance()) {
                return this;
            }
            int i = C61912.f8319x86e8948b[loadBalanceRequest.getLoadBalanceRequestTypeCase().ordinal()];
            if (i == 1) {
                mergeInitialRequest(loadBalanceRequest.getInitialRequest());
            } else if (i == 2) {
                mergeClientStats(loadBalanceRequest.getClientStats());
            }
            mergeUnknownFields(loadBalanceRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            LoadBalanceRequest loadBalanceRequest = null;
            try {
                try {
                    LoadBalanceRequest loadBalanceRequest2 = (LoadBalanceRequest) LoadBalanceRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (loadBalanceRequest2 != null) {
                        mergeFrom(loadBalanceRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    LoadBalanceRequest loadBalanceRequest3 = (LoadBalanceRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        loadBalanceRequest = loadBalanceRequest3;
                        if (loadBalanceRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (loadBalanceRequest != null) {
                    mergeFrom(loadBalanceRequest);
                }
                throw th;
            }
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public LoadBalanceRequestTypeCase getLoadBalanceRequestTypeCase() {
            return LoadBalanceRequestTypeCase.forNumber(this.loadBalanceRequestTypeCase_);
        }

        public Builder clearLoadBalanceRequestType() {
            this.loadBalanceRequestTypeCase_ = 0;
            this.loadBalanceRequestType_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public boolean hasInitialRequest() {
            return this.loadBalanceRequestTypeCase_ == 1;
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public InitialLoadBalanceRequest getInitialRequest() {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
                }
                return InitialLoadBalanceRequest.getDefaultInstance();
            }
            if (this.loadBalanceRequestTypeCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return InitialLoadBalanceRequest.getDefaultInstance();
        }

        public Builder setInitialRequest(InitialLoadBalanceRequest initialLoadBalanceRequest) {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(initialLoadBalanceRequest);
            } else {
                if (initialLoadBalanceRequest == null) {
                    throw new NullPointerException();
                }
                this.loadBalanceRequestType_ = initialLoadBalanceRequest;
                onChanged();
            }
            this.loadBalanceRequestTypeCase_ = 1;
            return this;
        }

        public Builder setInitialRequest(InitialLoadBalanceRequest.Builder builder) {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceRequestType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.loadBalanceRequestTypeCase_ = 1;
            return this;
        }

        public Builder mergeInitialRequest(InitialLoadBalanceRequest initialLoadBalanceRequest) {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3 = this.initialRequestBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ == 1 && this.loadBalanceRequestType_ != InitialLoadBalanceRequest.getDefaultInstance()) {
                    this.loadBalanceRequestType_ = InitialLoadBalanceRequest.newBuilder((InitialLoadBalanceRequest) this.loadBalanceRequestType_).mergeFrom(initialLoadBalanceRequest).buildPartial();
                } else {
                    this.loadBalanceRequestType_ = initialLoadBalanceRequest;
                }
                onChanged();
            } else {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(initialLoadBalanceRequest);
                }
                this.initialRequestBuilder_.setMessage(initialLoadBalanceRequest);
            }
            this.loadBalanceRequestTypeCase_ = 1;
            return this;
        }

        public Builder clearInitialRequest() {
            if (this.initialRequestBuilder_ == null) {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    this.loadBalanceRequestTypeCase_ = 0;
                    this.loadBalanceRequestType_ = null;
                    onChanged();
                }
            } else {
                if (this.loadBalanceRequestTypeCase_ == 1) {
                    this.loadBalanceRequestTypeCase_ = 0;
                    this.loadBalanceRequestType_ = null;
                }
                this.initialRequestBuilder_.clear();
            }
            return this;
        }

        public InitialLoadBalanceRequest.Builder getInitialRequestBuilder() {
            return getInitialRequestFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public InitialLoadBalanceRequestOrBuilder getInitialRequestOrBuilder() {
            SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> singleFieldBuilderV3;
            if (this.loadBalanceRequestTypeCase_ == 1 && (singleFieldBuilderV3 = this.initialRequestBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.loadBalanceRequestTypeCase_ == 1) {
                return (InitialLoadBalanceRequest) this.loadBalanceRequestType_;
            }
            return InitialLoadBalanceRequest.getDefaultInstance();
        }

        private SingleFieldBuilderV3<InitialLoadBalanceRequest, InitialLoadBalanceRequest.Builder, InitialLoadBalanceRequestOrBuilder> getInitialRequestFieldBuilder() {
            if (this.initialRequestBuilder_ == null) {
                if (this.loadBalanceRequestTypeCase_ != 1) {
                    this.loadBalanceRequestType_ = InitialLoadBalanceRequest.getDefaultInstance();
                }
                this.initialRequestBuilder_ = new SingleFieldBuilderV3<>((InitialLoadBalanceRequest) this.loadBalanceRequestType_, getParentForChildren(), isClean());
                this.loadBalanceRequestType_ = null;
            }
            this.loadBalanceRequestTypeCase_ = 1;
            onChanged();
            return this.initialRequestBuilder_;
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public boolean hasClientStats() {
            return this.loadBalanceRequestTypeCase_ == 2;
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public ClientStats getClientStats() {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    return (ClientStats) this.loadBalanceRequestType_;
                }
                return ClientStats.getDefaultInstance();
            }
            if (this.loadBalanceRequestTypeCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return ClientStats.getDefaultInstance();
        }

        public Builder setClientStats(ClientStats clientStats) {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(clientStats);
            } else {
                if (clientStats == null) {
                    throw new NullPointerException();
                }
                this.loadBalanceRequestType_ = clientStats;
                onChanged();
            }
            this.loadBalanceRequestTypeCase_ = 2;
            return this;
        }

        public Builder setClientStats(ClientStats.Builder builder) {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.loadBalanceRequestType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.loadBalanceRequestTypeCase_ = 2;
            return this;
        }

        public Builder mergeClientStats(ClientStats clientStats) {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3 = this.clientStatsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.loadBalanceRequestTypeCase_ == 2 && this.loadBalanceRequestType_ != ClientStats.getDefaultInstance()) {
                    this.loadBalanceRequestType_ = ClientStats.newBuilder((ClientStats) this.loadBalanceRequestType_).mergeFrom(clientStats).buildPartial();
                } else {
                    this.loadBalanceRequestType_ = clientStats;
                }
                onChanged();
            } else {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(clientStats);
                }
                this.clientStatsBuilder_.setMessage(clientStats);
            }
            this.loadBalanceRequestTypeCase_ = 2;
            return this;
        }

        public Builder clearClientStats() {
            if (this.clientStatsBuilder_ == null) {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    this.loadBalanceRequestTypeCase_ = 0;
                    this.loadBalanceRequestType_ = null;
                    onChanged();
                }
            } else {
                if (this.loadBalanceRequestTypeCase_ == 2) {
                    this.loadBalanceRequestTypeCase_ = 0;
                    this.loadBalanceRequestType_ = null;
                }
                this.clientStatsBuilder_.clear();
            }
            return this;
        }

        public ClientStats.Builder getClientStatsBuilder() {
            return getClientStatsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.p072v1.LoadBalanceRequestOrBuilder
        public ClientStatsOrBuilder getClientStatsOrBuilder() {
            SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> singleFieldBuilderV3;
            if (this.loadBalanceRequestTypeCase_ == 2 && (singleFieldBuilderV3 = this.clientStatsBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.loadBalanceRequestTypeCase_ == 2) {
                return (ClientStats) this.loadBalanceRequestType_;
            }
            return ClientStats.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ClientStats, ClientStats.Builder, ClientStatsOrBuilder> getClientStatsFieldBuilder() {
            if (this.clientStatsBuilder_ == null) {
                if (this.loadBalanceRequestTypeCase_ != 2) {
                    this.loadBalanceRequestType_ = ClientStats.getDefaultInstance();
                }
                this.clientStatsBuilder_ = new SingleFieldBuilderV3<>((ClientStats) this.loadBalanceRequestType_, getParentForChildren(), isClean());
                this.loadBalanceRequestType_ = null;
            }
            this.loadBalanceRequestTypeCase_ = 2;
            onChanged();
            return this.clientStatsBuilder_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.lb.v1.LoadBalanceRequest$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C61912 {

        /* renamed from: $SwitchMap$io$grpc$lb$v1$LoadBalanceRequest$LoadBalanceRequestTypeCase */
        static final /* synthetic */ int[] f8319x86e8948b = new int[LoadBalanceRequestTypeCase.values().length];

        static {
            try {
                f8319x86e8948b[LoadBalanceRequestTypeCase.INITIAL_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8319x86e8948b[LoadBalanceRequestTypeCase.CLIENT_STATS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8319x86e8948b[LoadBalanceRequestTypeCase.LOADBALANCEREQUESTTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static LoadBalanceRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LoadBalanceRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<LoadBalanceRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public LoadBalanceRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
