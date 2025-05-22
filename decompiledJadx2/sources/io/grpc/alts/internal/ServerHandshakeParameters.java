package io.grpc.alts.internal;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.Identity;
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
public final class ServerHandshakeParameters extends GeneratedMessageV3 implements ServerHandshakeParametersOrBuilder {
    public static final int LOCAL_IDENTITIES_FIELD_NUMBER = 2;
    public static final int RECORD_PROTOCOLS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Identity> localIdentities_;
    private byte memoizedIsInitialized;
    private LazyStringList recordProtocols_;
    private static final ServerHandshakeParameters DEFAULT_INSTANCE = new ServerHandshakeParameters();
    private static final Parser<ServerHandshakeParameters> PARSER = new AbstractParser<ServerHandshakeParameters>() { // from class: io.grpc.alts.internal.ServerHandshakeParameters.1
        @Override // com.google.protobuf.Parser
        public ServerHandshakeParameters parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ServerHandshakeParameters(codedInputStream, extensionRegistryLite);
        }
    };

    private ServerHandshakeParameters(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServerHandshakeParameters() {
        this.memoizedIsInitialized = (byte) -1;
        this.recordProtocols_ = LazyStringArrayList.EMPTY;
        this.localIdentities_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ServerHandshakeParameters();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ServerHandshakeParameters(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.recordProtocols_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.recordProtocols_.add(readStringRequireUtf8);
                        } else if (readTag == 18) {
                            if ((i & 2) == 0) {
                                this.localIdentities_ = new ArrayList();
                                i |= 2;
                            }
                            this.localIdentities_.add(codedInputStream.readMessage(Identity.parser(), extensionRegistryLite));
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
                if ((i & 1) != 0) {
                    this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.localIdentities_ = Collections.unmodifiableList(this.localIdentities_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.f8312x4fd0c46b.ensureFieldAccessorsInitialized(ServerHandshakeParameters.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public ProtocolStringList getRecordProtocolsList() {
        return this.recordProtocols_;
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public int getRecordProtocolsCount() {
        return this.recordProtocols_.size();
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public String getRecordProtocols(int i) {
        return (String) this.recordProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public ByteString getRecordProtocolsBytes(int i) {
        return this.recordProtocols_.getByteString(i);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public List<Identity> getLocalIdentitiesList() {
        return this.localIdentities_;
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public List<? extends IdentityOrBuilder> getLocalIdentitiesOrBuilderList() {
        return this.localIdentities_;
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public int getLocalIdentitiesCount() {
        return this.localIdentities_.size();
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public Identity getLocalIdentities(int i) {
        return this.localIdentities_.get(i);
    }

    @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
    public IdentityOrBuilder getLocalIdentitiesOrBuilder(int i) {
        return this.localIdentities_.get(i);
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
        for (int i = 0; i < this.recordProtocols_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.recordProtocols_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.localIdentities_.size(); i2++) {
            codedOutputStream.writeMessage(2, this.localIdentities_.get(i2));
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
        for (int i3 = 0; i3 < this.recordProtocols_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.recordProtocols_.getRaw(i3));
        }
        int size = i2 + 0 + (getRecordProtocolsList().size() * 1);
        for (int i4 = 0; i4 < this.localIdentities_.size(); i4++) {
            size += CodedOutputStream.computeMessageSize(2, this.localIdentities_.get(i4));
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServerHandshakeParameters)) {
            return super.equals(obj);
        }
        ServerHandshakeParameters serverHandshakeParameters = (ServerHandshakeParameters) obj;
        return getRecordProtocolsList().equals(serverHandshakeParameters.getRecordProtocolsList()) && getLocalIdentitiesList().equals(serverHandshakeParameters.getLocalIdentitiesList()) && this.unknownFields.equals(serverHandshakeParameters.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getRecordProtocolsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getRecordProtocolsList().hashCode();
        }
        if (getLocalIdentitiesCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getLocalIdentitiesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ServerHandshakeParameters parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ServerHandshakeParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ServerHandshakeParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ServerHandshakeParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(InputStream inputStream) throws IOException {
        return (ServerHandshakeParameters) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ServerHandshakeParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServerHandshakeParameters) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ServerHandshakeParameters) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ServerHandshakeParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServerHandshakeParameters) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServerHandshakeParameters parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ServerHandshakeParameters) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ServerHandshakeParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServerHandshakeParameters) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ServerHandshakeParameters serverHandshakeParameters) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(serverHandshakeParameters);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServerHandshakeParametersOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> localIdentitiesBuilder_;
        private List<Identity> localIdentities_;
        private LazyStringList recordProtocols_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.f8312x4fd0c46b.ensureFieldAccessorsInitialized(ServerHandshakeParameters.class, Builder.class);
        }

        private Builder() {
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.localIdentities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.localIdentities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ServerHandshakeParameters.alwaysUseFieldBuilders) {
                getLocalIdentitiesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.localIdentities_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_ServerHandshakeParameters_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ServerHandshakeParameters getDefaultInstanceForType() {
            return ServerHandshakeParameters.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ServerHandshakeParameters build() {
            ServerHandshakeParameters buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ServerHandshakeParameters buildPartial() {
            ServerHandshakeParameters serverHandshakeParameters = new ServerHandshakeParameters(this);
            if ((this.bitField0_ & 1) != 0) {
                this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            serverHandshakeParameters.recordProtocols_ = this.recordProtocols_;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                serverHandshakeParameters.localIdentities_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.localIdentities_ = Collections.unmodifiableList(this.localIdentities_);
                    this.bitField0_ &= -3;
                }
                serverHandshakeParameters.localIdentities_ = this.localIdentities_;
            }
            onBuilt();
            return serverHandshakeParameters;
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
            if (message instanceof ServerHandshakeParameters) {
                return mergeFrom((ServerHandshakeParameters) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ServerHandshakeParameters serverHandshakeParameters) {
            if (serverHandshakeParameters == ServerHandshakeParameters.getDefaultInstance()) {
                return this;
            }
            if (!serverHandshakeParameters.recordProtocols_.isEmpty()) {
                if (this.recordProtocols_.isEmpty()) {
                    this.recordProtocols_ = serverHandshakeParameters.recordProtocols_;
                    this.bitField0_ &= -2;
                } else {
                    ensureRecordProtocolsIsMutable();
                    this.recordProtocols_.addAll(serverHandshakeParameters.recordProtocols_);
                }
                onChanged();
            }
            if (this.localIdentitiesBuilder_ == null) {
                if (!serverHandshakeParameters.localIdentities_.isEmpty()) {
                    if (this.localIdentities_.isEmpty()) {
                        this.localIdentities_ = serverHandshakeParameters.localIdentities_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureLocalIdentitiesIsMutable();
                        this.localIdentities_.addAll(serverHandshakeParameters.localIdentities_);
                    }
                    onChanged();
                }
            } else if (!serverHandshakeParameters.localIdentities_.isEmpty()) {
                if (!this.localIdentitiesBuilder_.isEmpty()) {
                    this.localIdentitiesBuilder_.addAllMessages(serverHandshakeParameters.localIdentities_);
                } else {
                    this.localIdentitiesBuilder_.dispose();
                    this.localIdentitiesBuilder_ = null;
                    this.localIdentities_ = serverHandshakeParameters.localIdentities_;
                    this.bitField0_ &= -3;
                    this.localIdentitiesBuilder_ = ServerHandshakeParameters.alwaysUseFieldBuilders ? getLocalIdentitiesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(serverHandshakeParameters.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ServerHandshakeParameters serverHandshakeParameters = null;
            try {
                try {
                    ServerHandshakeParameters serverHandshakeParameters2 = (ServerHandshakeParameters) ServerHandshakeParameters.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (serverHandshakeParameters2 != null) {
                        mergeFrom(serverHandshakeParameters2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ServerHandshakeParameters serverHandshakeParameters3 = (ServerHandshakeParameters) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        serverHandshakeParameters = serverHandshakeParameters3;
                        if (serverHandshakeParameters != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (serverHandshakeParameters != null) {
                    mergeFrom(serverHandshakeParameters);
                }
                throw th;
            }
        }

        private void ensureRecordProtocolsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.recordProtocols_ = new LazyStringArrayList(this.recordProtocols_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public ProtocolStringList getRecordProtocolsList() {
            return this.recordProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public int getRecordProtocolsCount() {
            return this.recordProtocols_.size();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public String getRecordProtocols(int i) {
            return (String) this.recordProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public ByteString getRecordProtocolsBytes(int i) {
            return this.recordProtocols_.getByteString(i);
        }

        public Builder setRecordProtocols(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureRecordProtocolsIsMutable();
            this.recordProtocols_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRecordProtocols(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureRecordProtocolsIsMutable();
            this.recordProtocols_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRecordProtocols(Iterable<String> iterable) {
            ensureRecordProtocolsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.recordProtocols_);
            onChanged();
            return this;
        }

        public Builder clearRecordProtocols() {
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addRecordProtocolsBytes(ByteString byteString) {
            if (byteString != null) {
                ServerHandshakeParameters.checkByteStringIsUtf8(byteString);
                ensureRecordProtocolsIsMutable();
                this.recordProtocols_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureLocalIdentitiesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.localIdentities_ = new ArrayList(this.localIdentities_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public List<Identity> getLocalIdentitiesList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.localIdentities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public int getLocalIdentitiesCount() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.localIdentities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public Identity getLocalIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.localIdentities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLocalIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.set(i, identity);
                onChanged();
            }
            return this;
        }

        public Builder setLocalIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLocalIdentities(Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(identity);
                onChanged();
            }
            return this;
        }

        public Builder addLocalIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(i, identity);
                onChanged();
            }
            return this;
        }

        public Builder addLocalIdentities(Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLocalIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLocalIdentities(Iterable<? extends Identity> iterable) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.localIdentities_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLocalIdentities() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.localIdentities_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLocalIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLocalIdentitiesIsMutable();
                this.localIdentities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Identity.Builder getLocalIdentitiesBuilder(int i) {
            return getLocalIdentitiesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public IdentityOrBuilder getLocalIdentitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.localIdentities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.alts.internal.ServerHandshakeParametersOrBuilder
        public List<? extends IdentityOrBuilder> getLocalIdentitiesOrBuilderList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.localIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.localIdentities_);
        }

        public Identity.Builder addLocalIdentitiesBuilder() {
            return getLocalIdentitiesFieldBuilder().addBuilder(Identity.getDefaultInstance());
        }

        public Identity.Builder addLocalIdentitiesBuilder(int i) {
            return getLocalIdentitiesFieldBuilder().addBuilder(i, Identity.getDefaultInstance());
        }

        public List<Identity.Builder> getLocalIdentitiesBuilderList() {
            return getLocalIdentitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getLocalIdentitiesFieldBuilder() {
            if (this.localIdentitiesBuilder_ == null) {
                this.localIdentitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.localIdentities_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.localIdentities_ = null;
            }
            return this.localIdentitiesBuilder_;
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

    public static ServerHandshakeParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServerHandshakeParameters> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ServerHandshakeParameters> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ServerHandshakeParameters getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
