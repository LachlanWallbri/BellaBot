package io.grpc.alts.internal;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
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
public final class Endpoint extends GeneratedMessageV3 implements EndpointOrBuilder {
    public static final int IP_ADDRESS_FIELD_NUMBER = 1;
    public static final int PORT_FIELD_NUMBER = 2;
    public static final int PROTOCOL_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private volatile Object ipAddress_;
    private byte memoizedIsInitialized;
    private int port_;
    private int protocol_;
    private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
    private static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>() { // from class: io.grpc.alts.internal.Endpoint.1
        @Override // com.google.protobuf.Parser
        public Endpoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Endpoint(codedInputStream, extensionRegistryLite);
        }
    };

    private Endpoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Endpoint() {
        this.memoizedIsInitialized = (byte) -1;
        this.ipAddress_ = "";
        this.protocol_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Endpoint();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Endpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.ipAddress_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 16) {
                            this.port_ = codedInputStream.readInt32();
                        } else if (readTag == 24) {
                            this.protocol_ = codedInputStream.readEnum();
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
        return HandshakerProto.internal_static_grpc_gcp_Endpoint_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public String getIpAddress() {
        Object obj = this.ipAddress_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.ipAddress_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public ByteString getIpAddressBytes() {
        Object obj = this.ipAddress_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.ipAddress_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public int getPort() {
        return this.port_;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public int getProtocolValue() {
        return this.protocol_;
    }

    @Override // io.grpc.alts.internal.EndpointOrBuilder
    public NetworkProtocol getProtocol() {
        NetworkProtocol valueOf = NetworkProtocol.valueOf(this.protocol_);
        return valueOf == null ? NetworkProtocol.UNRECOGNIZED : valueOf;
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
        if (!getIpAddressBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.ipAddress_);
        }
        int i = this.port_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (this.protocol_ != NetworkProtocol.NETWORK_PROTOCOL_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.protocol_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getIpAddressBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.ipAddress_);
        int i2 = this.port_;
        if (i2 != 0) {
            computeStringSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (this.protocol_ != NetworkProtocol.NETWORK_PROTOCOL_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.protocol_);
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
        if (!(obj instanceof Endpoint)) {
            return super.equals(obj);
        }
        Endpoint endpoint = (Endpoint) obj;
        return getIpAddress().equals(endpoint.getIpAddress()) && getPort() == endpoint.getPort() && this.protocol_ == endpoint.protocol_ && this.unknownFields.equals(endpoint.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getIpAddress().hashCode()) * 37) + 2) * 53) + getPort()) * 37) + 3) * 53) + this.protocol_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Endpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Endpoint parseFrom(InputStream inputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Endpoint endpoint) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(endpoint);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EndpointOrBuilder {
        private Object ipAddress_;
        private int port_;
        private int protocol_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_Endpoint_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
        }

        private Builder() {
            this.ipAddress_ = "";
            this.protocol_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.ipAddress_ = "";
            this.protocol_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Endpoint.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.ipAddress_ = "";
            this.port_ = 0;
            this.protocol_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_Endpoint_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Endpoint getDefaultInstanceForType() {
            return Endpoint.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Endpoint build() {
            Endpoint buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Endpoint buildPartial() {
            Endpoint endpoint = new Endpoint(this);
            endpoint.ipAddress_ = this.ipAddress_;
            endpoint.port_ = this.port_;
            endpoint.protocol_ = this.protocol_;
            onBuilt();
            return endpoint;
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
            if (message instanceof Endpoint) {
                return mergeFrom((Endpoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Endpoint endpoint) {
            if (endpoint == Endpoint.getDefaultInstance()) {
                return this;
            }
            if (!endpoint.getIpAddress().isEmpty()) {
                this.ipAddress_ = endpoint.ipAddress_;
                onChanged();
            }
            if (endpoint.getPort() != 0) {
                setPort(endpoint.getPort());
            }
            if (endpoint.protocol_ != 0) {
                setProtocolValue(endpoint.getProtocolValue());
            }
            mergeUnknownFields(endpoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Endpoint endpoint = null;
            try {
                try {
                    Endpoint endpoint2 = (Endpoint) Endpoint.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (endpoint2 != null) {
                        mergeFrom(endpoint2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Endpoint endpoint3 = (Endpoint) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        endpoint = endpoint3;
                        if (endpoint != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (endpoint != null) {
                    mergeFrom(endpoint);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public String getIpAddress() {
            Object obj = this.ipAddress_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.ipAddress_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public ByteString getIpAddressBytes() {
            Object obj = this.ipAddress_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ipAddress_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setIpAddress(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.ipAddress_ = str;
            onChanged();
            return this;
        }

        public Builder clearIpAddress() {
            this.ipAddress_ = Endpoint.getDefaultInstance().getIpAddress();
            onChanged();
            return this;
        }

        public Builder setIpAddressBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                this.ipAddress_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public int getPort() {
            return this.port_;
        }

        public Builder setPort(int i) {
            this.port_ = i;
            onChanged();
            return this;
        }

        public Builder clearPort() {
            this.port_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public int getProtocolValue() {
            return this.protocol_;
        }

        public Builder setProtocolValue(int i) {
            this.protocol_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.EndpointOrBuilder
        public NetworkProtocol getProtocol() {
            NetworkProtocol valueOf = NetworkProtocol.valueOf(this.protocol_);
            return valueOf == null ? NetworkProtocol.UNRECOGNIZED : valueOf;
        }

        public Builder setProtocol(NetworkProtocol networkProtocol) {
            if (networkProtocol == null) {
                throw new NullPointerException();
            }
            this.protocol_ = networkProtocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearProtocol() {
            this.protocol_ = 0;
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

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Endpoint> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Endpoint getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
