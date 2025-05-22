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
public final class Server extends GeneratedMessageV3 implements ServerOrBuilder {
    public static final int DROP_FIELD_NUMBER = 4;
    public static final int IP_ADDRESS_FIELD_NUMBER = 1;
    public static final int LOAD_BALANCE_TOKEN_FIELD_NUMBER = 3;
    public static final int PORT_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private boolean drop_;
    private ByteString ipAddress_;
    private volatile Object loadBalanceToken_;
    private byte memoizedIsInitialized;
    private int port_;
    private static final Server DEFAULT_INSTANCE = new Server();
    private static final Parser<Server> PARSER = new AbstractParser<Server>() { // from class: io.grpc.lb.v1.Server.1
        @Override // com.google.protobuf.Parser
        public Server parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Server(codedInputStream, extensionRegistryLite);
        }
    };

    private Server(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Server() {
        this.memoizedIsInitialized = (byte) -1;
        this.ipAddress_ = ByteString.EMPTY;
        this.loadBalanceToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Server();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Server(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.ipAddress_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            this.port_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            this.loadBalanceToken_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 32) {
                            this.drop_ = codedInputStream.readBool();
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
        return LoadBalancerProto.internal_static_grpc_lb_v1_Server_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_Server_fieldAccessorTable.ensureFieldAccessorsInitialized(Server.class, Builder.class);
    }

    @Override // io.grpc.lb.p072v1.ServerOrBuilder
    public ByteString getIpAddress() {
        return this.ipAddress_;
    }

    @Override // io.grpc.lb.p072v1.ServerOrBuilder
    public int getPort() {
        return this.port_;
    }

    @Override // io.grpc.lb.p072v1.ServerOrBuilder
    public String getLoadBalanceToken() {
        Object obj = this.loadBalanceToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.loadBalanceToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.lb.p072v1.ServerOrBuilder
    public ByteString getLoadBalanceTokenBytes() {
        Object obj = this.loadBalanceToken_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.loadBalanceToken_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.lb.p072v1.ServerOrBuilder
    public boolean getDrop() {
        return this.drop_;
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
        if (!this.ipAddress_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.ipAddress_);
        }
        int i = this.port_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (!getLoadBalanceTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.loadBalanceToken_);
        }
        boolean z = this.drop_;
        if (z) {
            codedOutputStream.writeBool(4, z);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeBytesSize = this.ipAddress_.isEmpty() ? 0 : 0 + CodedOutputStream.computeBytesSize(1, this.ipAddress_);
        int i2 = this.port_;
        if (i2 != 0) {
            computeBytesSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (!getLoadBalanceTokenBytes().isEmpty()) {
            computeBytesSize += GeneratedMessageV3.computeStringSize(3, this.loadBalanceToken_);
        }
        boolean z = this.drop_;
        if (z) {
            computeBytesSize += CodedOutputStream.computeBoolSize(4, z);
        }
        int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Server)) {
            return super.equals(obj);
        }
        Server server = (Server) obj;
        return getIpAddress().equals(server.getIpAddress()) && getPort() == server.getPort() && getLoadBalanceToken().equals(server.getLoadBalanceToken()) && getDrop() == server.getDrop() && this.unknownFields.equals(server.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getIpAddress().hashCode()) * 37) + 2) * 53) + getPort()) * 37) + 3) * 53) + getLoadBalanceToken().hashCode()) * 37) + 4) * 53) + Internal.hashBoolean(getDrop())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Server parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Server parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Server parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Server parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Server parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Server parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Server parseFrom(InputStream inputStream) throws IOException {
        return (Server) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Server parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Server) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Server parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Server) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Server parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Server) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Server parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Server) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Server parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Server) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Server server) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(server);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerOrBuilder {
        private boolean drop_;
        private ByteString ipAddress_;
        private Object loadBalanceToken_;
        private int port_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_Server_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_Server_fieldAccessorTable.ensureFieldAccessorsInitialized(Server.class, Builder.class);
        }

        private Builder() {
            this.ipAddress_ = ByteString.EMPTY;
            this.loadBalanceToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.ipAddress_ = ByteString.EMPTY;
            this.loadBalanceToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Server.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.ipAddress_ = ByteString.EMPTY;
            this.port_ = 0;
            this.loadBalanceToken_ = "";
            this.drop_ = false;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_Server_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Server getDefaultInstanceForType() {
            return Server.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Server build() {
            Server buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Server buildPartial() {
            Server server = new Server(this);
            server.ipAddress_ = this.ipAddress_;
            server.port_ = this.port_;
            server.loadBalanceToken_ = this.loadBalanceToken_;
            server.drop_ = this.drop_;
            onBuilt();
            return server;
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
            if (message instanceof Server) {
                return mergeFrom((Server) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Server server) {
            if (server == Server.getDefaultInstance()) {
                return this;
            }
            if (server.getIpAddress() != ByteString.EMPTY) {
                setIpAddress(server.getIpAddress());
            }
            if (server.getPort() != 0) {
                setPort(server.getPort());
            }
            if (!server.getLoadBalanceToken().isEmpty()) {
                this.loadBalanceToken_ = server.loadBalanceToken_;
                onChanged();
            }
            if (server.getDrop()) {
                setDrop(server.getDrop());
            }
            mergeUnknownFields(server.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Server server = null;
            try {
                try {
                    Server server2 = (Server) Server.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (server2 != null) {
                        mergeFrom(server2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Server server3 = (Server) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        server = server3;
                        if (server != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (server != null) {
                    mergeFrom(server);
                }
                throw th;
            }
        }

        @Override // io.grpc.lb.p072v1.ServerOrBuilder
        public ByteString getIpAddress() {
            return this.ipAddress_;
        }

        public Builder setIpAddress(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.ipAddress_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearIpAddress() {
            this.ipAddress_ = Server.getDefaultInstance().getIpAddress();
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.p072v1.ServerOrBuilder
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

        @Override // io.grpc.lb.p072v1.ServerOrBuilder
        public String getLoadBalanceToken() {
            Object obj = this.loadBalanceToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.loadBalanceToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.lb.p072v1.ServerOrBuilder
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
            this.loadBalanceToken_ = Server.getDefaultInstance().getLoadBalanceToken();
            onChanged();
            return this;
        }

        public Builder setLoadBalanceTokenBytes(ByteString byteString) {
            if (byteString != null) {
                Server.checkByteStringIsUtf8(byteString);
                this.loadBalanceToken_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.lb.p072v1.ServerOrBuilder
        public boolean getDrop() {
            return this.drop_;
        }

        public Builder setDrop(boolean z) {
            this.drop_ = z;
            onChanged();
            return this;
        }

        public Builder clearDrop() {
            this.drop_ = false;
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

    public static Server getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Server> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Server> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Server getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
