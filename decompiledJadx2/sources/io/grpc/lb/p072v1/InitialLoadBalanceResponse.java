package io.grpc.lb.p072v1;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InitialLoadBalanceResponse extends GeneratedMessageV3 implements InitialLoadBalanceResponseOrBuilder {
    public static final int CLIENT_STATS_REPORT_INTERVAL_FIELD_NUMBER = 2;
    public static final int LOAD_BALANCER_DELEGATE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private Duration clientStatsReportInterval_;
    private volatile Object loadBalancerDelegate_;
    private byte memoizedIsInitialized;
    private static final InitialLoadBalanceResponse DEFAULT_INSTANCE = new InitialLoadBalanceResponse();
    private static final Parser<InitialLoadBalanceResponse> PARSER = new AbstractParser<InitialLoadBalanceResponse>() { // from class: io.grpc.lb.v1.InitialLoadBalanceResponse.1
        @Override // com.google.protobuf.Parser
        public InitialLoadBalanceResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new InitialLoadBalanceResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private InitialLoadBalanceResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private InitialLoadBalanceResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.loadBalancerDelegate_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new InitialLoadBalanceResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private InitialLoadBalanceResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.loadBalancerDelegate_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                Duration.Builder builder = this.clientStatsReportInterval_ != null ? this.clientStatsReportInterval_.toBuilder() : null;
                                this.clientStatsReportInterval_ = (Duration) codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.clientStatsReportInterval_);
                                    this.clientStatsReportInterval_ = builder.buildPartial();
                                }
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_InitialLoadBalanceResponse_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.f8323x9023256c.ensureFieldAccessorsInitialized(InitialLoadBalanceResponse.class, Builder.class);
    }

    @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
    public String getLoadBalancerDelegate() {
        Object obj = this.loadBalancerDelegate_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.loadBalancerDelegate_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
    public ByteString getLoadBalancerDelegateBytes() {
        Object obj = this.loadBalancerDelegate_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.loadBalancerDelegate_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
    public boolean hasClientStatsReportInterval() {
        return this.clientStatsReportInterval_ != null;
    }

    @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
    public Duration getClientStatsReportInterval() {
        Duration duration = this.clientStatsReportInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
    public DurationOrBuilder getClientStatsReportIntervalOrBuilder() {
        return getClientStatsReportInterval();
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
        if (!getLoadBalancerDelegateBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.loadBalancerDelegate_);
        }
        if (this.clientStatsReportInterval_ != null) {
            codedOutputStream.writeMessage(2, getClientStatsReportInterval());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getLoadBalancerDelegateBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.loadBalancerDelegate_);
        if (this.clientStatsReportInterval_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getClientStatsReportInterval());
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InitialLoadBalanceResponse)) {
            return super.equals(obj);
        }
        InitialLoadBalanceResponse initialLoadBalanceResponse = (InitialLoadBalanceResponse) obj;
        if (getLoadBalancerDelegate().equals(initialLoadBalanceResponse.getLoadBalancerDelegate()) && hasClientStatsReportInterval() == initialLoadBalanceResponse.hasClientStatsReportInterval()) {
            return (!hasClientStatsReportInterval() || getClientStatsReportInterval().equals(initialLoadBalanceResponse.getClientStatsReportInterval())) && this.unknownFields.equals(initialLoadBalanceResponse.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLoadBalancerDelegate().hashCode();
        if (hasClientStatsReportInterval()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getClientStatsReportInterval().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static InitialLoadBalanceResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static InitialLoadBalanceResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static InitialLoadBalanceResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static InitialLoadBalanceResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static InitialLoadBalanceResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static InitialLoadBalanceResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static InitialLoadBalanceResponse parseFrom(InputStream inputStream) throws IOException {
        return (InitialLoadBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static InitialLoadBalanceResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (InitialLoadBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static InitialLoadBalanceResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (InitialLoadBalanceResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static InitialLoadBalanceResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (InitialLoadBalanceResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static InitialLoadBalanceResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (InitialLoadBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static InitialLoadBalanceResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (InitialLoadBalanceResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(InitialLoadBalanceResponse initialLoadBalanceResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(initialLoadBalanceResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InitialLoadBalanceResponseOrBuilder {
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> clientStatsReportIntervalBuilder_;
        private Duration clientStatsReportInterval_;
        private Object loadBalancerDelegate_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_InitialLoadBalanceResponse_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.f8323x9023256c.ensureFieldAccessorsInitialized(InitialLoadBalanceResponse.class, Builder.class);
        }

        private Builder() {
            this.loadBalancerDelegate_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.loadBalancerDelegate_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = InitialLoadBalanceResponse.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.loadBalancerDelegate_ = "";
            if (this.clientStatsReportIntervalBuilder_ == null) {
                this.clientStatsReportInterval_ = null;
            } else {
                this.clientStatsReportInterval_ = null;
                this.clientStatsReportIntervalBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_InitialLoadBalanceResponse_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public InitialLoadBalanceResponse getDefaultInstanceForType() {
            return InitialLoadBalanceResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public InitialLoadBalanceResponse build() {
            InitialLoadBalanceResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public InitialLoadBalanceResponse buildPartial() {
            InitialLoadBalanceResponse initialLoadBalanceResponse = new InitialLoadBalanceResponse(this);
            initialLoadBalanceResponse.loadBalancerDelegate_ = this.loadBalancerDelegate_;
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.clientStatsReportIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                initialLoadBalanceResponse.clientStatsReportInterval_ = this.clientStatsReportInterval_;
            } else {
                initialLoadBalanceResponse.clientStatsReportInterval_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return initialLoadBalanceResponse;
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
            if (message instanceof InitialLoadBalanceResponse) {
                return mergeFrom((InitialLoadBalanceResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(InitialLoadBalanceResponse initialLoadBalanceResponse) {
            if (initialLoadBalanceResponse == InitialLoadBalanceResponse.getDefaultInstance()) {
                return this;
            }
            if (!initialLoadBalanceResponse.getLoadBalancerDelegate().isEmpty()) {
                this.loadBalancerDelegate_ = initialLoadBalanceResponse.loadBalancerDelegate_;
                onChanged();
            }
            if (initialLoadBalanceResponse.hasClientStatsReportInterval()) {
                mergeClientStatsReportInterval(initialLoadBalanceResponse.getClientStatsReportInterval());
            }
            mergeUnknownFields(initialLoadBalanceResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            InitialLoadBalanceResponse initialLoadBalanceResponse = null;
            try {
                try {
                    InitialLoadBalanceResponse initialLoadBalanceResponse2 = (InitialLoadBalanceResponse) InitialLoadBalanceResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (initialLoadBalanceResponse2 != null) {
                        mergeFrom(initialLoadBalanceResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InitialLoadBalanceResponse initialLoadBalanceResponse3 = (InitialLoadBalanceResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        initialLoadBalanceResponse = initialLoadBalanceResponse3;
                        if (initialLoadBalanceResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (initialLoadBalanceResponse != null) {
                    mergeFrom(initialLoadBalanceResponse);
                }
                throw th;
            }
        }

        @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
        public String getLoadBalancerDelegate() {
            Object obj = this.loadBalancerDelegate_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.loadBalancerDelegate_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
        public ByteString getLoadBalancerDelegateBytes() {
            Object obj = this.loadBalancerDelegate_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.loadBalancerDelegate_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLoadBalancerDelegate(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.loadBalancerDelegate_ = str;
            onChanged();
            return this;
        }

        public Builder clearLoadBalancerDelegate() {
            this.loadBalancerDelegate_ = InitialLoadBalanceResponse.getDefaultInstance().getLoadBalancerDelegate();
            onChanged();
            return this;
        }

        public Builder setLoadBalancerDelegateBytes(ByteString byteString) {
            if (byteString != null) {
                InitialLoadBalanceResponse.checkByteStringIsUtf8(byteString);
                this.loadBalancerDelegate_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
        public boolean hasClientStatsReportInterval() {
            return (this.clientStatsReportIntervalBuilder_ == null && this.clientStatsReportInterval_ == null) ? false : true;
        }

        @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
        public Duration getClientStatsReportInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.clientStatsReportIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration = this.clientStatsReportInterval_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setClientStatsReportInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.clientStatsReportIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else {
                if (duration == null) {
                    throw new NullPointerException();
                }
                this.clientStatsReportInterval_ = duration;
                onChanged();
            }
            return this;
        }

        public Builder setClientStatsReportInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.clientStatsReportIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.clientStatsReportInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeClientStatsReportInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.clientStatsReportIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.clientStatsReportInterval_;
                if (duration2 != null) {
                    this.clientStatsReportInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.clientStatsReportInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearClientStatsReportInterval() {
            if (this.clientStatsReportIntervalBuilder_ == null) {
                this.clientStatsReportInterval_ = null;
                onChanged();
            } else {
                this.clientStatsReportInterval_ = null;
                this.clientStatsReportIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getClientStatsReportIntervalBuilder() {
            onChanged();
            return getClientStatsReportIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.p072v1.InitialLoadBalanceResponseOrBuilder
        public DurationOrBuilder getClientStatsReportIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.clientStatsReportIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.clientStatsReportInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getClientStatsReportIntervalFieldBuilder() {
            if (this.clientStatsReportIntervalBuilder_ == null) {
                this.clientStatsReportIntervalBuilder_ = new SingleFieldBuilderV3<>(getClientStatsReportInterval(), getParentForChildren(), isClean());
                this.clientStatsReportInterval_ = null;
            }
            return this.clientStatsReportIntervalBuilder_;
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

    public static InitialLoadBalanceResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<InitialLoadBalanceResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<InitialLoadBalanceResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public InitialLoadBalanceResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
