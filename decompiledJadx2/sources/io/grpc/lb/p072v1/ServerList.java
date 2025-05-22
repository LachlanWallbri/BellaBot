package io.grpc.lb.p072v1;

import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.lb.p072v1.Server;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServerList extends GeneratedMessageV3 implements ServerListOrBuilder {
    private static final ServerList DEFAULT_INSTANCE = new ServerList();
    private static final Parser<ServerList> PARSER = new AbstractParser<ServerList>() { // from class: io.grpc.lb.v1.ServerList.1
        @Override // com.google.protobuf.Parser
        public ServerList parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ServerList(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SERVERS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Server> servers_;

    private ServerList(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServerList() {
        this.memoizedIsInitialized = (byte) -1;
        this.servers_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ServerList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ServerList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!(z2 & true)) {
                                    this.servers_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.servers_.add(codedInputStream.readMessage(Server.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.servers_ = Collections.unmodifiableList(this.servers_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerList.class, Builder.class);
    }

    @Override // io.grpc.lb.p072v1.ServerListOrBuilder
    public List<Server> getServersList() {
        return this.servers_;
    }

    @Override // io.grpc.lb.p072v1.ServerListOrBuilder
    public List<? extends ServerOrBuilder> getServersOrBuilderList() {
        return this.servers_;
    }

    @Override // io.grpc.lb.p072v1.ServerListOrBuilder
    public int getServersCount() {
        return this.servers_.size();
    }

    @Override // io.grpc.lb.p072v1.ServerListOrBuilder
    public Server getServers(int i) {
        return this.servers_.get(i);
    }

    @Override // io.grpc.lb.p072v1.ServerListOrBuilder
    public ServerOrBuilder getServersOrBuilder(int i) {
        return this.servers_.get(i);
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
        for (int i = 0; i < this.servers_.size(); i++) {
            codedOutputStream.writeMessage(1, this.servers_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.servers_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.servers_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServerList)) {
            return super.equals(obj);
        }
        ServerList serverList = (ServerList) obj;
        return getServersList().equals(serverList.getServersList()) && this.unknownFields.equals(serverList.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getServersCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getServersList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ServerList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ServerList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ServerList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ServerList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ServerList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ServerList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ServerList parseFrom(InputStream inputStream) throws IOException {
        return (ServerList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ServerList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServerList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerList parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ServerList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ServerList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServerList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerList parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ServerList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ServerList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServerList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ServerList serverList) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(serverList);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerListOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> serversBuilder_;
        private List<Server> servers_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerList.class, Builder.class);
        }

        private Builder() {
            this.servers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.servers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ServerList.alwaysUseFieldBuilders) {
                getServersFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.servers_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ServerList_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ServerList getDefaultInstanceForType() {
            return ServerList.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ServerList build() {
            ServerList buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ServerList buildPartial() {
            ServerList serverList = new ServerList(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.servers_ = Collections.unmodifiableList(this.servers_);
                    this.bitField0_ &= -2;
                }
                serverList.servers_ = this.servers_;
            } else {
                serverList.servers_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return serverList;
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
            if (message instanceof ServerList) {
                return mergeFrom((ServerList) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ServerList serverList) {
            if (serverList == ServerList.getDefaultInstance()) {
                return this;
            }
            if (this.serversBuilder_ == null) {
                if (!serverList.servers_.isEmpty()) {
                    if (this.servers_.isEmpty()) {
                        this.servers_ = serverList.servers_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureServersIsMutable();
                        this.servers_.addAll(serverList.servers_);
                    }
                    onChanged();
                }
            } else if (!serverList.servers_.isEmpty()) {
                if (!this.serversBuilder_.isEmpty()) {
                    this.serversBuilder_.addAllMessages(serverList.servers_);
                } else {
                    this.serversBuilder_.dispose();
                    this.serversBuilder_ = null;
                    this.servers_ = serverList.servers_;
                    this.bitField0_ &= -2;
                    this.serversBuilder_ = ServerList.alwaysUseFieldBuilders ? getServersFieldBuilder() : null;
                }
            }
            mergeUnknownFields(serverList.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ServerList serverList = null;
            try {
                try {
                    ServerList serverList2 = (ServerList) ServerList.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (serverList2 != null) {
                        mergeFrom(serverList2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ServerList serverList3 = (ServerList) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        serverList = serverList3;
                        if (serverList != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (serverList != null) {
                    mergeFrom(serverList);
                }
                throw th;
            }
        }

        private void ensureServersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.servers_ = new ArrayList(this.servers_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.lb.p072v1.ServerListOrBuilder
        public List<Server> getServersList() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.servers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.lb.p072v1.ServerListOrBuilder
        public int getServersCount() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.servers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.lb.p072v1.ServerListOrBuilder
        public Server getServers(int i) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.servers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setServers(int i, Server server) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, server);
            } else {
                if (server == null) {
                    throw new NullPointerException();
                }
                ensureServersIsMutable();
                this.servers_.set(i, server);
                onChanged();
            }
            return this;
        }

        public Builder setServers(int i, Server.Builder builder) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addServers(Server server) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(server);
            } else {
                if (server == null) {
                    throw new NullPointerException();
                }
                ensureServersIsMutable();
                this.servers_.add(server);
                onChanged();
            }
            return this;
        }

        public Builder addServers(int i, Server server) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, server);
            } else {
                if (server == null) {
                    throw new NullPointerException();
                }
                ensureServersIsMutable();
                this.servers_.add(i, server);
                onChanged();
            }
            return this;
        }

        public Builder addServers(Server.Builder builder) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addServers(int i, Server.Builder builder) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllServers(Iterable<? extends Server> iterable) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.servers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearServers() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.servers_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeServers(int i) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureServersIsMutable();
                this.servers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Server.Builder getServersBuilder(int i) {
            return getServersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.lb.p072v1.ServerListOrBuilder
        public ServerOrBuilder getServersOrBuilder(int i) {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.servers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.lb.p072v1.ServerListOrBuilder
        public List<? extends ServerOrBuilder> getServersOrBuilderList() {
            RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> repeatedFieldBuilderV3 = this.serversBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.servers_);
        }

        public Server.Builder addServersBuilder() {
            return getServersFieldBuilder().addBuilder(Server.getDefaultInstance());
        }

        public Server.Builder addServersBuilder(int i) {
            return getServersFieldBuilder().addBuilder(i, Server.getDefaultInstance());
        }

        public List<Server.Builder> getServersBuilderList() {
            return getServersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Server, Server.Builder, ServerOrBuilder> getServersFieldBuilder() {
            if (this.serversBuilder_ == null) {
                this.serversBuilder_ = new RepeatedFieldBuilderV3<>(this.servers_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.servers_ = null;
            }
            return this.serversBuilder_;
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

    public static ServerList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServerList> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ServerList> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ServerList getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
