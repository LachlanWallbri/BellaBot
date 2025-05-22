package io.grpc.alts.internal;

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
import io.grpc.alts.internal.Identity;
import io.grpc.alts.internal.RpcProtocolVersions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HandshakerResult extends GeneratedMessageV3 implements HandshakerResultOrBuilder {
    public static final int APPLICATION_PROTOCOL_FIELD_NUMBER = 1;
    public static final int KEEP_CHANNEL_OPEN_FIELD_NUMBER = 6;
    public static final int KEY_DATA_FIELD_NUMBER = 3;
    public static final int LOCAL_IDENTITY_FIELD_NUMBER = 5;
    public static final int MAX_FRAME_SIZE_FIELD_NUMBER = 8;
    public static final int PEER_IDENTITY_FIELD_NUMBER = 4;
    public static final int PEER_RPC_VERSIONS_FIELD_NUMBER = 7;
    public static final int RECORD_PROTOCOL_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private volatile Object applicationProtocol_;
    private boolean keepChannelOpen_;
    private ByteString keyData_;
    private Identity localIdentity_;
    private int maxFrameSize_;
    private byte memoizedIsInitialized;
    private Identity peerIdentity_;
    private RpcProtocolVersions peerRpcVersions_;
    private volatile Object recordProtocol_;
    private static final HandshakerResult DEFAULT_INSTANCE = new HandshakerResult();
    private static final Parser<HandshakerResult> PARSER = new AbstractParser<HandshakerResult>() { // from class: io.grpc.alts.internal.HandshakerResult.1
        @Override // com.google.protobuf.Parser
        public HandshakerResult parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HandshakerResult(codedInputStream, extensionRegistryLite);
        }
    };

    private HandshakerResult(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HandshakerResult() {
        this.memoizedIsInitialized = (byte) -1;
        this.applicationProtocol_ = "";
        this.recordProtocol_ = "";
        this.keyData_ = ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new HandshakerResult();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private HandshakerResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.applicationProtocol_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.recordProtocol_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 26) {
                                if (readTag == 34) {
                                    Identity.Builder builder = this.peerIdentity_ != null ? this.peerIdentity_.toBuilder() : null;
                                    this.peerIdentity_ = (Identity) codedInputStream.readMessage(Identity.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.peerIdentity_);
                                        this.peerIdentity_ = builder.buildPartial();
                                    }
                                } else if (readTag == 42) {
                                    Identity.Builder builder2 = this.localIdentity_ != null ? this.localIdentity_.toBuilder() : null;
                                    this.localIdentity_ = (Identity) codedInputStream.readMessage(Identity.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.localIdentity_);
                                        this.localIdentity_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 48) {
                                    this.keepChannelOpen_ = codedInputStream.readBool();
                                } else if (readTag == 58) {
                                    RpcProtocolVersions.Builder builder3 = this.peerRpcVersions_ != null ? this.peerRpcVersions_.toBuilder() : null;
                                    this.peerRpcVersions_ = (RpcProtocolVersions) codedInputStream.readMessage(RpcProtocolVersions.parser(), extensionRegistryLite);
                                    if (builder3 != null) {
                                        builder3.mergeFrom(this.peerRpcVersions_);
                                        this.peerRpcVersions_ = builder3.buildPartial();
                                    }
                                } else if (readTag == 64) {
                                    this.maxFrameSize_ = codedInputStream.readUInt32();
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.keyData_ = codedInputStream.readBytes();
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
        return HandshakerProto.internal_static_grpc_gcp_HandshakerResult_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_HandshakerResult_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerResult.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public String getApplicationProtocol() {
        Object obj = this.applicationProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.applicationProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public ByteString getApplicationProtocolBytes() {
        Object obj = this.applicationProtocol_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.applicationProtocol_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public String getRecordProtocol() {
        Object obj = this.recordProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.recordProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public ByteString getRecordProtocolBytes() {
        Object obj = this.recordProtocol_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.recordProtocol_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public ByteString getKeyData() {
        return this.keyData_;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public boolean hasPeerIdentity() {
        return this.peerIdentity_ != null;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public Identity getPeerIdentity() {
        Identity identity = this.peerIdentity_;
        return identity == null ? Identity.getDefaultInstance() : identity;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public IdentityOrBuilder getPeerIdentityOrBuilder() {
        return getPeerIdentity();
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public boolean hasLocalIdentity() {
        return this.localIdentity_ != null;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public Identity getLocalIdentity() {
        Identity identity = this.localIdentity_;
        return identity == null ? Identity.getDefaultInstance() : identity;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public IdentityOrBuilder getLocalIdentityOrBuilder() {
        return getLocalIdentity();
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public boolean getKeepChannelOpen() {
        return this.keepChannelOpen_;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public boolean hasPeerRpcVersions() {
        return this.peerRpcVersions_ != null;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public RpcProtocolVersions getPeerRpcVersions() {
        RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
        return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public RpcProtocolVersionsOrBuilder getPeerRpcVersionsOrBuilder() {
        return getPeerRpcVersions();
    }

    @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
    public int getMaxFrameSize() {
        return this.maxFrameSize_;
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
        if (!getApplicationProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.applicationProtocol_);
        }
        if (!getRecordProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.recordProtocol_);
        }
        if (!this.keyData_.isEmpty()) {
            codedOutputStream.writeBytes(3, this.keyData_);
        }
        if (this.peerIdentity_ != null) {
            codedOutputStream.writeMessage(4, getPeerIdentity());
        }
        if (this.localIdentity_ != null) {
            codedOutputStream.writeMessage(5, getLocalIdentity());
        }
        boolean z = this.keepChannelOpen_;
        if (z) {
            codedOutputStream.writeBool(6, z);
        }
        if (this.peerRpcVersions_ != null) {
            codedOutputStream.writeMessage(7, getPeerRpcVersions());
        }
        int i = this.maxFrameSize_;
        if (i != 0) {
            codedOutputStream.writeUInt32(8, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getApplicationProtocolBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.applicationProtocol_);
        if (!getRecordProtocolBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.recordProtocol_);
        }
        if (!this.keyData_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeBytesSize(3, this.keyData_);
        }
        if (this.peerIdentity_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, getPeerIdentity());
        }
        if (this.localIdentity_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, getLocalIdentity());
        }
        boolean z = this.keepChannelOpen_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(6, z);
        }
        if (this.peerRpcVersions_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(7, getPeerRpcVersions());
        }
        int i2 = this.maxFrameSize_;
        if (i2 != 0) {
            computeStringSize += CodedOutputStream.computeUInt32Size(8, i2);
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
        if (!(obj instanceof HandshakerResult)) {
            return super.equals(obj);
        }
        HandshakerResult handshakerResult = (HandshakerResult) obj;
        if (!getApplicationProtocol().equals(handshakerResult.getApplicationProtocol()) || !getRecordProtocol().equals(handshakerResult.getRecordProtocol()) || !getKeyData().equals(handshakerResult.getKeyData()) || hasPeerIdentity() != handshakerResult.hasPeerIdentity()) {
            return false;
        }
        if ((hasPeerIdentity() && !getPeerIdentity().equals(handshakerResult.getPeerIdentity())) || hasLocalIdentity() != handshakerResult.hasLocalIdentity()) {
            return false;
        }
        if ((!hasLocalIdentity() || getLocalIdentity().equals(handshakerResult.getLocalIdentity())) && getKeepChannelOpen() == handshakerResult.getKeepChannelOpen() && hasPeerRpcVersions() == handshakerResult.hasPeerRpcVersions()) {
            return (!hasPeerRpcVersions() || getPeerRpcVersions().equals(handshakerResult.getPeerRpcVersions())) && getMaxFrameSize() == handshakerResult.getMaxFrameSize() && this.unknownFields.equals(handshakerResult.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getApplicationProtocol().hashCode()) * 37) + 2) * 53) + getRecordProtocol().hashCode()) * 37) + 3) * 53) + getKeyData().hashCode();
        if (hasPeerIdentity()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getPeerIdentity().hashCode();
        }
        if (hasLocalIdentity()) {
            hashCode = (((hashCode * 37) + 5) * 53) + getLocalIdentity().hashCode();
        }
        int hashBoolean = (((hashCode * 37) + 6) * 53) + Internal.hashBoolean(getKeepChannelOpen());
        if (hasPeerRpcVersions()) {
            hashBoolean = (((hashBoolean * 37) + 7) * 53) + getPeerRpcVersions().hashCode();
        }
        int maxFrameSize = (((((hashBoolean * 37) + 8) * 53) + getMaxFrameSize()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = maxFrameSize;
        return maxFrameSize;
    }

    public static HandshakerResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static HandshakerResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HandshakerResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static HandshakerResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HandshakerResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static HandshakerResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HandshakerResult parseFrom(InputStream inputStream) throws IOException {
        return (HandshakerResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HandshakerResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HandshakerResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HandshakerResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HandshakerResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HandshakerResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HandshakerResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HandshakerResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HandshakerResult handshakerResult) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(handshakerResult);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HandshakerResultOrBuilder {
        private Object applicationProtocol_;
        private boolean keepChannelOpen_;
        private ByteString keyData_;
        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> localIdentityBuilder_;
        private Identity localIdentity_;
        private int maxFrameSize_;
        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> peerIdentityBuilder_;
        private Identity peerIdentity_;
        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> peerRpcVersionsBuilder_;
        private RpcProtocolVersions peerRpcVersions_;
        private Object recordProtocol_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResult_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResult_fieldAccessorTable.ensureFieldAccessorsInitialized(HandshakerResult.class, Builder.class);
        }

        private Builder() {
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.keyData_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.keyData_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HandshakerResult.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.keyData_ = ByteString.EMPTY;
            if (this.peerIdentityBuilder_ == null) {
                this.peerIdentity_ = null;
            } else {
                this.peerIdentity_ = null;
                this.peerIdentityBuilder_ = null;
            }
            if (this.localIdentityBuilder_ == null) {
                this.localIdentity_ = null;
            } else {
                this.localIdentity_ = null;
                this.localIdentityBuilder_ = null;
            }
            this.keepChannelOpen_ = false;
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersions_ = null;
            } else {
                this.peerRpcVersions_ = null;
                this.peerRpcVersionsBuilder_ = null;
            }
            this.maxFrameSize_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_HandshakerResult_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HandshakerResult getDefaultInstanceForType() {
            return HandshakerResult.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HandshakerResult build() {
            HandshakerResult buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HandshakerResult buildPartial() {
            HandshakerResult handshakerResult = new HandshakerResult(this);
            handshakerResult.applicationProtocol_ = this.applicationProtocol_;
            handshakerResult.recordProtocol_ = this.recordProtocol_;
            handshakerResult.keyData_ = this.keyData_;
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.peerIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                handshakerResult.peerIdentity_ = this.peerIdentity_;
            } else {
                handshakerResult.peerIdentity_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV32 = this.localIdentityBuilder_;
            if (singleFieldBuilderV32 == null) {
                handshakerResult.localIdentity_ = this.localIdentity_;
            } else {
                handshakerResult.localIdentity_ = singleFieldBuilderV32.build();
            }
            handshakerResult.keepChannelOpen_ = this.keepChannelOpen_;
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV33 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV33 == null) {
                handshakerResult.peerRpcVersions_ = this.peerRpcVersions_;
            } else {
                handshakerResult.peerRpcVersions_ = singleFieldBuilderV33.build();
            }
            handshakerResult.maxFrameSize_ = this.maxFrameSize_;
            onBuilt();
            return handshakerResult;
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
            if (message instanceof HandshakerResult) {
                return mergeFrom((HandshakerResult) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HandshakerResult handshakerResult) {
            if (handshakerResult == HandshakerResult.getDefaultInstance()) {
                return this;
            }
            if (!handshakerResult.getApplicationProtocol().isEmpty()) {
                this.applicationProtocol_ = handshakerResult.applicationProtocol_;
                onChanged();
            }
            if (!handshakerResult.getRecordProtocol().isEmpty()) {
                this.recordProtocol_ = handshakerResult.recordProtocol_;
                onChanged();
            }
            if (handshakerResult.getKeyData() != ByteString.EMPTY) {
                setKeyData(handshakerResult.getKeyData());
            }
            if (handshakerResult.hasPeerIdentity()) {
                mergePeerIdentity(handshakerResult.getPeerIdentity());
            }
            if (handshakerResult.hasLocalIdentity()) {
                mergeLocalIdentity(handshakerResult.getLocalIdentity());
            }
            if (handshakerResult.getKeepChannelOpen()) {
                setKeepChannelOpen(handshakerResult.getKeepChannelOpen());
            }
            if (handshakerResult.hasPeerRpcVersions()) {
                mergePeerRpcVersions(handshakerResult.getPeerRpcVersions());
            }
            if (handshakerResult.getMaxFrameSize() != 0) {
                setMaxFrameSize(handshakerResult.getMaxFrameSize());
            }
            mergeUnknownFields(handshakerResult.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HandshakerResult handshakerResult = null;
            try {
                try {
                    HandshakerResult handshakerResult2 = (HandshakerResult) HandshakerResult.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (handshakerResult2 != null) {
                        mergeFrom(handshakerResult2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    HandshakerResult handshakerResult3 = (HandshakerResult) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        handshakerResult = handshakerResult3;
                        if (handshakerResult != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (handshakerResult != null) {
                    mergeFrom(handshakerResult);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public String getApplicationProtocol() {
            Object obj = this.applicationProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.applicationProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public ByteString getApplicationProtocolBytes() {
            Object obj = this.applicationProtocol_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.applicationProtocol_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setApplicationProtocol(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.applicationProtocol_ = str;
            onChanged();
            return this;
        }

        public Builder clearApplicationProtocol() {
            this.applicationProtocol_ = HandshakerResult.getDefaultInstance().getApplicationProtocol();
            onChanged();
            return this;
        }

        public Builder setApplicationProtocolBytes(ByteString byteString) {
            if (byteString != null) {
                HandshakerResult.checkByteStringIsUtf8(byteString);
                this.applicationProtocol_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public String getRecordProtocol() {
            Object obj = this.recordProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.recordProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public ByteString getRecordProtocolBytes() {
            Object obj = this.recordProtocol_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.recordProtocol_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRecordProtocol(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.recordProtocol_ = str;
            onChanged();
            return this;
        }

        public Builder clearRecordProtocol() {
            this.recordProtocol_ = HandshakerResult.getDefaultInstance().getRecordProtocol();
            onChanged();
            return this;
        }

        public Builder setRecordProtocolBytes(ByteString byteString) {
            if (byteString != null) {
                HandshakerResult.checkByteStringIsUtf8(byteString);
                this.recordProtocol_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public ByteString getKeyData() {
            return this.keyData_;
        }

        public Builder setKeyData(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.keyData_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearKeyData() {
            this.keyData_ = HandshakerResult.getDefaultInstance().getKeyData();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public boolean hasPeerIdentity() {
            return (this.peerIdentityBuilder_ == null && this.peerIdentity_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public Identity getPeerIdentity() {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.peerIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Identity identity = this.peerIdentity_;
                return identity == null ? Identity.getDefaultInstance() : identity;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setPeerIdentity(Identity identity) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.peerIdentityBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                this.peerIdentity_ = identity;
                onChanged();
            }
            return this;
        }

        public Builder setPeerIdentity(Identity.Builder builder) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.peerIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.peerIdentity_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePeerIdentity(Identity identity) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.peerIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Identity identity2 = this.peerIdentity_;
                if (identity2 != null) {
                    this.peerIdentity_ = Identity.newBuilder(identity2).mergeFrom(identity).buildPartial();
                } else {
                    this.peerIdentity_ = identity;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(identity);
            }
            return this;
        }

        public Builder clearPeerIdentity() {
            if (this.peerIdentityBuilder_ == null) {
                this.peerIdentity_ = null;
                onChanged();
            } else {
                this.peerIdentity_ = null;
                this.peerIdentityBuilder_ = null;
            }
            return this;
        }

        public Identity.Builder getPeerIdentityBuilder() {
            onChanged();
            return getPeerIdentityFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public IdentityOrBuilder getPeerIdentityOrBuilder() {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.peerIdentityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Identity identity = this.peerIdentity_;
            return identity == null ? Identity.getDefaultInstance() : identity;
        }

        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getPeerIdentityFieldBuilder() {
            if (this.peerIdentityBuilder_ == null) {
                this.peerIdentityBuilder_ = new SingleFieldBuilderV3<>(getPeerIdentity(), getParentForChildren(), isClean());
                this.peerIdentity_ = null;
            }
            return this.peerIdentityBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public boolean hasLocalIdentity() {
            return (this.localIdentityBuilder_ == null && this.localIdentity_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public Identity getLocalIdentity() {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Identity identity = this.localIdentity_;
                return identity == null ? Identity.getDefaultInstance() : identity;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setLocalIdentity(Identity identity) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                this.localIdentity_ = identity;
                onChanged();
            }
            return this;
        }

        public Builder setLocalIdentity(Identity.Builder builder) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.localIdentity_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLocalIdentity(Identity identity) {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                Identity identity2 = this.localIdentity_;
                if (identity2 != null) {
                    this.localIdentity_ = Identity.newBuilder(identity2).mergeFrom(identity).buildPartial();
                } else {
                    this.localIdentity_ = identity;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(identity);
            }
            return this;
        }

        public Builder clearLocalIdentity() {
            if (this.localIdentityBuilder_ == null) {
                this.localIdentity_ = null;
                onChanged();
            } else {
                this.localIdentity_ = null;
                this.localIdentityBuilder_ = null;
            }
            return this;
        }

        public Identity.Builder getLocalIdentityBuilder() {
            onChanged();
            return getLocalIdentityFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public IdentityOrBuilder getLocalIdentityOrBuilder() {
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Identity identity = this.localIdentity_;
            return identity == null ? Identity.getDefaultInstance() : identity;
        }

        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getLocalIdentityFieldBuilder() {
            if (this.localIdentityBuilder_ == null) {
                this.localIdentityBuilder_ = new SingleFieldBuilderV3<>(getLocalIdentity(), getParentForChildren(), isClean());
                this.localIdentity_ = null;
            }
            return this.localIdentityBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public boolean getKeepChannelOpen() {
            return this.keepChannelOpen_;
        }

        public Builder setKeepChannelOpen(boolean z) {
            this.keepChannelOpen_ = z;
            onChanged();
            return this;
        }

        public Builder clearKeepChannelOpen() {
            this.keepChannelOpen_ = false;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public boolean hasPeerRpcVersions() {
            return (this.peerRpcVersionsBuilder_ == null && this.peerRpcVersions_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public RpcProtocolVersions getPeerRpcVersions() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
                return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setPeerRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(rpcProtocolVersions);
            } else {
                if (rpcProtocolVersions == null) {
                    throw new NullPointerException();
                }
                this.peerRpcVersions_ = rpcProtocolVersions;
                onChanged();
            }
            return this;
        }

        public Builder setPeerRpcVersions(RpcProtocolVersions.Builder builder) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.peerRpcVersions_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePeerRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RpcProtocolVersions rpcProtocolVersions2 = this.peerRpcVersions_;
                if (rpcProtocolVersions2 != null) {
                    this.peerRpcVersions_ = RpcProtocolVersions.newBuilder(rpcProtocolVersions2).mergeFrom(rpcProtocolVersions).buildPartial();
                } else {
                    this.peerRpcVersions_ = rpcProtocolVersions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(rpcProtocolVersions);
            }
            return this;
        }

        public Builder clearPeerRpcVersions() {
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersions_ = null;
                onChanged();
            } else {
                this.peerRpcVersions_ = null;
                this.peerRpcVersionsBuilder_ = null;
            }
            return this;
        }

        public RpcProtocolVersions.Builder getPeerRpcVersionsBuilder() {
            onChanged();
            return getPeerRpcVersionsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public RpcProtocolVersionsOrBuilder getPeerRpcVersionsOrBuilder() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
            return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
        }

        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> getPeerRpcVersionsFieldBuilder() {
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersionsBuilder_ = new SingleFieldBuilderV3<>(getPeerRpcVersions(), getParentForChildren(), isClean());
                this.peerRpcVersions_ = null;
            }
            return this.peerRpcVersionsBuilder_;
        }

        @Override // io.grpc.alts.internal.HandshakerResultOrBuilder
        public int getMaxFrameSize() {
            return this.maxFrameSize_;
        }

        public Builder setMaxFrameSize(int i) {
            this.maxFrameSize_ = i;
            onChanged();
            return this;
        }

        public Builder clearMaxFrameSize() {
            this.maxFrameSize_ = 0;
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

    public static HandshakerResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HandshakerResult> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<HandshakerResult> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public HandshakerResult getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
