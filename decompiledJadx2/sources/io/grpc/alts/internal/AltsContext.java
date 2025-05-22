package io.grpc.alts.internal;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import io.grpc.alts.internal.RpcProtocolVersions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsContext extends GeneratedMessageV3 implements AltsContextOrBuilder {
    public static final int APPLICATION_PROTOCOL_FIELD_NUMBER = 1;
    public static final int LOCAL_SERVICE_ACCOUNT_FIELD_NUMBER = 5;
    public static final int PEER_ATTRIBUTES_FIELD_NUMBER = 7;
    public static final int PEER_RPC_VERSIONS_FIELD_NUMBER = 6;
    public static final int PEER_SERVICE_ACCOUNT_FIELD_NUMBER = 4;
    public static final int RECORD_PROTOCOL_FIELD_NUMBER = 2;
    public static final int SECURITY_LEVEL_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private volatile Object applicationProtocol_;
    private volatile Object localServiceAccount_;
    private byte memoizedIsInitialized;
    private MapField<String, String> peerAttributes_;
    private RpcProtocolVersions peerRpcVersions_;
    private volatile Object peerServiceAccount_;
    private volatile Object recordProtocol_;
    private int securityLevel_;
    private static final AltsContext DEFAULT_INSTANCE = new AltsContext();
    private static final Parser<AltsContext> PARSER = new AbstractParser<AltsContext>() { // from class: io.grpc.alts.internal.AltsContext.1
        @Override // com.google.protobuf.Parser
        public AltsContext parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AltsContext(codedInputStream, extensionRegistryLite);
        }
    };

    private AltsContext(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AltsContext() {
        this.memoizedIsInitialized = (byte) -1;
        this.applicationProtocol_ = "";
        this.recordProtocol_ = "";
        this.securityLevel_ = 0;
        this.peerServiceAccount_ = "";
        this.localServiceAccount_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new AltsContext();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AltsContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.applicationProtocol_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.recordProtocol_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.securityLevel_ = codedInputStream.readEnum();
                            } else if (readTag == 34) {
                                this.peerServiceAccount_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.localServiceAccount_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 50) {
                                RpcProtocolVersions.Builder builder = this.peerRpcVersions_ != null ? this.peerRpcVersions_.toBuilder() : null;
                                this.peerRpcVersions_ = (RpcProtocolVersions) codedInputStream.readMessage(RpcProtocolVersions.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.peerRpcVersions_);
                                    this.peerRpcVersions_ = builder.buildPartial();
                                }
                            } else if (readTag == 58) {
                                if (!(z2 & true)) {
                                    this.peerAttributes_ = MapField.newMapField(PeerAttributesDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(PeerAttributesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.peerAttributes_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return AltsContextProto.internal_static_grpc_gcp_AltsContext_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 7) {
            return internalGetPeerAttributes();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AltsContextProto.internal_static_grpc_gcp_AltsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(AltsContext.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getApplicationProtocol() {
        Object obj = this.applicationProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.applicationProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getApplicationProtocolBytes() {
        Object obj = this.applicationProtocol_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.applicationProtocol_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getRecordProtocol() {
        Object obj = this.recordProtocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.recordProtocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getRecordProtocolBytes() {
        Object obj = this.recordProtocol_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.recordProtocol_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public int getSecurityLevelValue() {
        return this.securityLevel_;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public SecurityLevel getSecurityLevel() {
        SecurityLevel valueOf = SecurityLevel.valueOf(this.securityLevel_);
        return valueOf == null ? SecurityLevel.UNRECOGNIZED : valueOf;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getPeerServiceAccount() {
        Object obj = this.peerServiceAccount_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.peerServiceAccount_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getPeerServiceAccountBytes() {
        Object obj = this.peerServiceAccount_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.peerServiceAccount_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getLocalServiceAccount() {
        Object obj = this.localServiceAccount_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.localServiceAccount_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public ByteString getLocalServiceAccountBytes() {
        Object obj = this.localServiceAccount_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.localServiceAccount_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public boolean hasPeerRpcVersions() {
        return this.peerRpcVersions_ != null;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public RpcProtocolVersions getPeerRpcVersions() {
        RpcProtocolVersions rpcProtocolVersions = this.peerRpcVersions_;
        return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public RpcProtocolVersionsOrBuilder getPeerRpcVersionsOrBuilder() {
        return getPeerRpcVersions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PeerAttributesDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(AltsContextProto.f8307xd245ccec, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private PeerAttributesDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetPeerAttributes() {
        MapField<String, String> mapField = this.peerAttributes_;
        return mapField == null ? MapField.emptyMapField(PeerAttributesDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public int getPeerAttributesCount() {
        return internalGetPeerAttributes().getMap().size();
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public boolean containsPeerAttributes(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        return internalGetPeerAttributes().getMap().containsKey(str);
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    @Deprecated
    public Map<String, String> getPeerAttributes() {
        return getPeerAttributesMap();
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public Map<String, String> getPeerAttributesMap() {
        return internalGetPeerAttributes().getMap();
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getPeerAttributesOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetPeerAttributes().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // io.grpc.alts.internal.AltsContextOrBuilder
    public String getPeerAttributesOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetPeerAttributes().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str);
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
        if (this.securityLevel_ != SecurityLevel.SECURITY_NONE.getNumber()) {
            codedOutputStream.writeEnum(3, this.securityLevel_);
        }
        if (!getPeerServiceAccountBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.peerServiceAccount_);
        }
        if (!getLocalServiceAccountBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.localServiceAccount_);
        }
        if (this.peerRpcVersions_ != null) {
            codedOutputStream.writeMessage(6, getPeerRpcVersions());
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetPeerAttributes(), PeerAttributesDefaultEntryHolder.defaultEntry, 7);
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
        if (this.securityLevel_ != SecurityLevel.SECURITY_NONE.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.securityLevel_);
        }
        if (!getPeerServiceAccountBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.peerServiceAccount_);
        }
        if (!getLocalServiceAccountBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.localServiceAccount_);
        }
        if (this.peerRpcVersions_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(6, getPeerRpcVersions());
        }
        for (Map.Entry<String, String> entry : internalGetPeerAttributes().getMap().entrySet()) {
            computeStringSize += CodedOutputStream.computeMessageSize(7, PeerAttributesDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof AltsContext)) {
            return super.equals(obj);
        }
        AltsContext altsContext = (AltsContext) obj;
        if (getApplicationProtocol().equals(altsContext.getApplicationProtocol()) && getRecordProtocol().equals(altsContext.getRecordProtocol()) && this.securityLevel_ == altsContext.securityLevel_ && getPeerServiceAccount().equals(altsContext.getPeerServiceAccount()) && getLocalServiceAccount().equals(altsContext.getLocalServiceAccount()) && hasPeerRpcVersions() == altsContext.hasPeerRpcVersions()) {
            return (!hasPeerRpcVersions() || getPeerRpcVersions().equals(altsContext.getPeerRpcVersions())) && internalGetPeerAttributes().equals(altsContext.internalGetPeerAttributes()) && this.unknownFields.equals(altsContext.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getApplicationProtocol().hashCode()) * 37) + 2) * 53) + getRecordProtocol().hashCode()) * 37) + 3) * 53) + this.securityLevel_) * 37) + 4) * 53) + getPeerServiceAccount().hashCode()) * 37) + 5) * 53) + getLocalServiceAccount().hashCode();
        if (hasPeerRpcVersions()) {
            hashCode = (((hashCode * 37) + 6) * 53) + getPeerRpcVersions().hashCode();
        }
        if (!internalGetPeerAttributes().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 7) * 53) + internalGetPeerAttributes().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AltsContext parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static AltsContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AltsContext parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static AltsContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AltsContext parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static AltsContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AltsContext parseFrom(InputStream inputStream) throws IOException {
        return (AltsContext) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AltsContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AltsContext) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AltsContext parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AltsContext) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AltsContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AltsContext) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AltsContext parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AltsContext) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AltsContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AltsContext) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AltsContext altsContext) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(altsContext);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AltsContextOrBuilder {
        private Object applicationProtocol_;
        private int bitField0_;
        private Object localServiceAccount_;
        private MapField<String, String> peerAttributes_;
        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> peerRpcVersionsBuilder_;
        private RpcProtocolVersions peerRpcVersions_;
        private Object peerServiceAccount_;
        private Object recordProtocol_;
        private int securityLevel_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AltsContextProto.internal_static_grpc_gcp_AltsContext_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 7) {
                return internalGetPeerAttributes();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 7) {
                return internalGetMutablePeerAttributes();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AltsContextProto.internal_static_grpc_gcp_AltsContext_fieldAccessorTable.ensureFieldAccessorsInitialized(AltsContext.class, Builder.class);
        }

        private Builder() {
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.securityLevel_ = 0;
            this.peerServiceAccount_ = "";
            this.localServiceAccount_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.securityLevel_ = 0;
            this.peerServiceAccount_ = "";
            this.localServiceAccount_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AltsContext.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.applicationProtocol_ = "";
            this.recordProtocol_ = "";
            this.securityLevel_ = 0;
            this.peerServiceAccount_ = "";
            this.localServiceAccount_ = "";
            if (this.peerRpcVersionsBuilder_ == null) {
                this.peerRpcVersions_ = null;
            } else {
                this.peerRpcVersions_ = null;
                this.peerRpcVersionsBuilder_ = null;
            }
            internalGetMutablePeerAttributes().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AltsContextProto.internal_static_grpc_gcp_AltsContext_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public AltsContext getDefaultInstanceForType() {
            return AltsContext.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AltsContext build() {
            AltsContext buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AltsContext buildPartial() {
            AltsContext altsContext = new AltsContext(this);
            int i = this.bitField0_;
            altsContext.applicationProtocol_ = this.applicationProtocol_;
            altsContext.recordProtocol_ = this.recordProtocol_;
            altsContext.securityLevel_ = this.securityLevel_;
            altsContext.peerServiceAccount_ = this.peerServiceAccount_;
            altsContext.localServiceAccount_ = this.localServiceAccount_;
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.peerRpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                altsContext.peerRpcVersions_ = this.peerRpcVersions_;
            } else {
                altsContext.peerRpcVersions_ = singleFieldBuilderV3.build();
            }
            altsContext.peerAttributes_ = internalGetPeerAttributes();
            altsContext.peerAttributes_.makeImmutable();
            onBuilt();
            return altsContext;
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
            if (message instanceof AltsContext) {
                return mergeFrom((AltsContext) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AltsContext altsContext) {
            if (altsContext == AltsContext.getDefaultInstance()) {
                return this;
            }
            if (!altsContext.getApplicationProtocol().isEmpty()) {
                this.applicationProtocol_ = altsContext.applicationProtocol_;
                onChanged();
            }
            if (!altsContext.getRecordProtocol().isEmpty()) {
                this.recordProtocol_ = altsContext.recordProtocol_;
                onChanged();
            }
            if (altsContext.securityLevel_ != 0) {
                setSecurityLevelValue(altsContext.getSecurityLevelValue());
            }
            if (!altsContext.getPeerServiceAccount().isEmpty()) {
                this.peerServiceAccount_ = altsContext.peerServiceAccount_;
                onChanged();
            }
            if (!altsContext.getLocalServiceAccount().isEmpty()) {
                this.localServiceAccount_ = altsContext.localServiceAccount_;
                onChanged();
            }
            if (altsContext.hasPeerRpcVersions()) {
                mergePeerRpcVersions(altsContext.getPeerRpcVersions());
            }
            internalGetMutablePeerAttributes().mergeFrom(altsContext.internalGetPeerAttributes());
            mergeUnknownFields(altsContext.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AltsContext altsContext = null;
            try {
                try {
                    AltsContext altsContext2 = (AltsContext) AltsContext.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (altsContext2 != null) {
                        mergeFrom(altsContext2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    AltsContext altsContext3 = (AltsContext) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        altsContext = altsContext3;
                        if (altsContext != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (altsContext != null) {
                    mergeFrom(altsContext);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getApplicationProtocol() {
            Object obj = this.applicationProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.applicationProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
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
            this.applicationProtocol_ = AltsContext.getDefaultInstance().getApplicationProtocol();
            onChanged();
            return this;
        }

        public Builder setApplicationProtocolBytes(ByteString byteString) {
            if (byteString != null) {
                AltsContext.checkByteStringIsUtf8(byteString);
                this.applicationProtocol_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getRecordProtocol() {
            Object obj = this.recordProtocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.recordProtocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
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
            this.recordProtocol_ = AltsContext.getDefaultInstance().getRecordProtocol();
            onChanged();
            return this;
        }

        public Builder setRecordProtocolBytes(ByteString byteString) {
            if (byteString != null) {
                AltsContext.checkByteStringIsUtf8(byteString);
                this.recordProtocol_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public int getSecurityLevelValue() {
            return this.securityLevel_;
        }

        public Builder setSecurityLevelValue(int i) {
            this.securityLevel_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public SecurityLevel getSecurityLevel() {
            SecurityLevel valueOf = SecurityLevel.valueOf(this.securityLevel_);
            return valueOf == null ? SecurityLevel.UNRECOGNIZED : valueOf;
        }

        public Builder setSecurityLevel(SecurityLevel securityLevel) {
            if (securityLevel == null) {
                throw new NullPointerException();
            }
            this.securityLevel_ = securityLevel.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSecurityLevel() {
            this.securityLevel_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getPeerServiceAccount() {
            Object obj = this.peerServiceAccount_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.peerServiceAccount_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public ByteString getPeerServiceAccountBytes() {
            Object obj = this.peerServiceAccount_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.peerServiceAccount_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPeerServiceAccount(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.peerServiceAccount_ = str;
            onChanged();
            return this;
        }

        public Builder clearPeerServiceAccount() {
            this.peerServiceAccount_ = AltsContext.getDefaultInstance().getPeerServiceAccount();
            onChanged();
            return this;
        }

        public Builder setPeerServiceAccountBytes(ByteString byteString) {
            if (byteString != null) {
                AltsContext.checkByteStringIsUtf8(byteString);
                this.peerServiceAccount_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getLocalServiceAccount() {
            Object obj = this.localServiceAccount_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.localServiceAccount_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public ByteString getLocalServiceAccountBytes() {
            Object obj = this.localServiceAccount_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.localServiceAccount_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLocalServiceAccount(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.localServiceAccount_ = str;
            onChanged();
            return this;
        }

        public Builder clearLocalServiceAccount() {
            this.localServiceAccount_ = AltsContext.getDefaultInstance().getLocalServiceAccount();
            onChanged();
            return this;
        }

        public Builder setLocalServiceAccountBytes(ByteString byteString) {
            if (byteString != null) {
                AltsContext.checkByteStringIsUtf8(byteString);
                this.localServiceAccount_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public boolean hasPeerRpcVersions() {
            return (this.peerRpcVersionsBuilder_ == null && this.peerRpcVersions_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
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

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
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

        private MapField<String, String> internalGetPeerAttributes() {
            MapField<String, String> mapField = this.peerAttributes_;
            return mapField == null ? MapField.emptyMapField(PeerAttributesDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutablePeerAttributes() {
            onChanged();
            if (this.peerAttributes_ == null) {
                this.peerAttributes_ = MapField.newMapField(PeerAttributesDefaultEntryHolder.defaultEntry);
            }
            if (!this.peerAttributes_.isMutable()) {
                this.peerAttributes_ = this.peerAttributes_.copy();
            }
            return this.peerAttributes_;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public int getPeerAttributesCount() {
            return internalGetPeerAttributes().getMap().size();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public boolean containsPeerAttributes(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            return internalGetPeerAttributes().getMap().containsKey(str);
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        @Deprecated
        public Map<String, String> getPeerAttributes() {
            return getPeerAttributesMap();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public Map<String, String> getPeerAttributesMap() {
            return internalGetPeerAttributes().getMap();
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getPeerAttributesOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetPeerAttributes().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // io.grpc.alts.internal.AltsContextOrBuilder
        public String getPeerAttributesOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetPeerAttributes().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str);
        }

        public Builder clearPeerAttributes() {
            internalGetMutablePeerAttributes().getMutableMap().clear();
            return this;
        }

        public Builder removePeerAttributes(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            internalGetMutablePeerAttributes().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutablePeerAttributes() {
            return internalGetMutablePeerAttributes().getMutableMap();
        }

        public Builder putPeerAttributes(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (str2 == null) {
                throw new NullPointerException();
            }
            internalGetMutablePeerAttributes().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllPeerAttributes(Map<String, String> map) {
            internalGetMutablePeerAttributes().getMutableMap().putAll(map);
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

    public static AltsContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AltsContext> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<AltsContext> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public AltsContext getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
