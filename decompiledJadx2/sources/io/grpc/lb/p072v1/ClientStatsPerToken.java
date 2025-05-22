package io.grpc.lb.p072v1;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ClientStatsPerToken extends GeneratedMessageV3 implements ClientStatsPerTokenOrBuilder {
    public static final int LOAD_BALANCE_TOKEN_FIELD_NUMBER = 1;
    public static final int NUM_CALLS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private volatile Object loadBalanceToken_;
    private byte memoizedIsInitialized;
    private long numCalls_;
    private static final ClientStatsPerToken DEFAULT_INSTANCE = new ClientStatsPerToken();
    private static final Parser<ClientStatsPerToken> PARSER = new AbstractParser<ClientStatsPerToken>() { // from class: io.grpc.lb.v1.ClientStatsPerToken.1
        @Override // com.google.protobuf.Parser
        public ClientStatsPerToken parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ClientStatsPerToken(codedInputStream, extensionRegistryLite);
        }
    };

    private ClientStatsPerToken(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ClientStatsPerToken() {
        this.memoizedIsInitialized = (byte) -1;
        this.loadBalanceToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ClientStatsPerToken();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ClientStatsPerToken(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.loadBalanceToken_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.numCalls_ = codedInputStream.readInt64();
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
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
        return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStatsPerToken_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.f8321x8baae209.ensureFieldAccessorsInitialized(ClientStatsPerToken.class, Builder.class);
    }

    @Override // io.grpc.lb.p072v1.ClientStatsPerTokenOrBuilder
    public String getLoadBalanceToken() {
        Object obj = this.loadBalanceToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.loadBalanceToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsPerTokenOrBuilder
    public ByteString getLoadBalanceTokenBytes() {
        Object obj = this.loadBalanceToken_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.loadBalanceToken_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsPerTokenOrBuilder
    public long getNumCalls() {
        return this.numCalls_;
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
        if (!getLoadBalanceTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.loadBalanceToken_);
        }
        long j = this.numCalls_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getLoadBalanceTokenBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.loadBalanceToken_);
        long j = this.numCalls_;
        if (j != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(2, j);
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
        if (!(obj instanceof ClientStatsPerToken)) {
            return super.equals(obj);
        }
        ClientStatsPerToken clientStatsPerToken = (ClientStatsPerToken) obj;
        return getLoadBalanceToken().equals(clientStatsPerToken.getLoadBalanceToken()) && getNumCalls() == clientStatsPerToken.getNumCalls() && this.unknownFields.equals(clientStatsPerToken.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLoadBalanceToken().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getNumCalls())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static ClientStatsPerToken parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ClientStatsPerToken parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ClientStatsPerToken parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ClientStatsPerToken parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ClientStatsPerToken parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ClientStatsPerToken parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ClientStatsPerToken parseFrom(InputStream inputStream) throws IOException {
        return (ClientStatsPerToken) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ClientStatsPerToken parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientStatsPerToken) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClientStatsPerToken parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ClientStatsPerToken) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ClientStatsPerToken parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientStatsPerToken) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClientStatsPerToken parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ClientStatsPerToken) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ClientStatsPerToken parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientStatsPerToken) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ClientStatsPerToken clientStatsPerToken) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(clientStatsPerToken);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClientStatsPerTokenOrBuilder {
        private Object loadBalanceToken_;
        private long numCalls_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStatsPerToken_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.f8321x8baae209.ensureFieldAccessorsInitialized(ClientStatsPerToken.class, Builder.class);
        }

        private Builder() {
            this.loadBalanceToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.loadBalanceToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ClientStatsPerToken.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.loadBalanceToken_ = "";
            this.numCalls_ = 0L;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStatsPerToken_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ClientStatsPerToken getDefaultInstanceForType() {
            return ClientStatsPerToken.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ClientStatsPerToken build() {
            ClientStatsPerToken buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ClientStatsPerToken buildPartial() {
            ClientStatsPerToken clientStatsPerToken = new ClientStatsPerToken(this);
            clientStatsPerToken.loadBalanceToken_ = this.loadBalanceToken_;
            clientStatsPerToken.numCalls_ = this.numCalls_;
            onBuilt();
            return clientStatsPerToken;
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
            if (message instanceof ClientStatsPerToken) {
                return mergeFrom((ClientStatsPerToken) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ClientStatsPerToken clientStatsPerToken) {
            if (clientStatsPerToken == ClientStatsPerToken.getDefaultInstance()) {
                return this;
            }
            if (!clientStatsPerToken.getLoadBalanceToken().isEmpty()) {
                this.loadBalanceToken_ = clientStatsPerToken.loadBalanceToken_;
                onChanged();
            }
            if (clientStatsPerToken.getNumCalls() != 0) {
                setNumCalls(clientStatsPerToken.getNumCalls());
            }
            mergeUnknownFields(clientStatsPerToken.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ClientStatsPerToken clientStatsPerToken = null;
            try {
                try {
                    ClientStatsPerToken clientStatsPerToken2 = (ClientStatsPerToken) ClientStatsPerToken.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (clientStatsPerToken2 != null) {
                        mergeFrom(clientStatsPerToken2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ClientStatsPerToken clientStatsPerToken3 = (ClientStatsPerToken) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        clientStatsPerToken = clientStatsPerToken3;
                        if (clientStatsPerToken != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (clientStatsPerToken != null) {
                    mergeFrom(clientStatsPerToken);
                }
                throw th;
            }
        }

        @Override // io.grpc.lb.p072v1.ClientStatsPerTokenOrBuilder
        public String getLoadBalanceToken() {
            Object obj = this.loadBalanceToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.loadBalanceToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.lb.p072v1.ClientStatsPerTokenOrBuilder
        public ByteString getLoadBalanceTokenBytes() {
            Object obj = this.loadBalanceToken_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.loadBalanceToken_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLoadBalanceToken(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.loadBalanceToken_ = str;
            onChanged();
            return this;
        }

        public Builder clearLoadBalanceToken() {
            this.loadBalanceToken_ = ClientStatsPerToken.getDefaultInstance().getLoadBalanceToken();
            onChanged();
            return this;
        }

        public Builder setLoadBalanceTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ClientStatsPerToken.checkByteStringIsUtf8(byteString);
                this.loadBalanceToken_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.lb.p072v1.ClientStatsPerTokenOrBuilder
        public long getNumCalls() {
            return this.numCalls_;
        }

        public Builder setNumCalls(long j) {
            this.numCalls_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumCalls() {
            this.numCalls_ = 0L;
            onChanged();
            return this;
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

    public static ClientStatsPerToken getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ClientStatsPerToken> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ClientStatsPerToken> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ClientStatsPerToken getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
