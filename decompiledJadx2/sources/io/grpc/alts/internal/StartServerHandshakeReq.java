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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import io.grpc.alts.internal.Endpoint;
import io.grpc.alts.internal.RpcProtocolVersions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class StartServerHandshakeReq extends GeneratedMessageV3 implements StartServerHandshakeReqOrBuilder {
    public static final int APPLICATION_PROTOCOLS_FIELD_NUMBER = 1;
    public static final int HANDSHAKE_PARAMETERS_FIELD_NUMBER = 2;
    public static final int IN_BYTES_FIELD_NUMBER = 3;
    public static final int LOCAL_ENDPOINT_FIELD_NUMBER = 4;
    public static final int MAX_FRAME_SIZE_FIELD_NUMBER = 7;
    public static final int REMOTE_ENDPOINT_FIELD_NUMBER = 5;
    public static final int RPC_VERSIONS_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private LazyStringList applicationProtocols_;
    private MapField<Integer, ServerHandshakeParameters> handshakeParameters_;
    private ByteString inBytes_;
    private Endpoint localEndpoint_;
    private int maxFrameSize_;
    private byte memoizedIsInitialized;
    private Endpoint remoteEndpoint_;
    private RpcProtocolVersions rpcVersions_;
    private static final StartServerHandshakeReq DEFAULT_INSTANCE = new StartServerHandshakeReq();
    private static final Parser<StartServerHandshakeReq> PARSER = new AbstractParser<StartServerHandshakeReq>() { // from class: io.grpc.alts.internal.StartServerHandshakeReq.1
        @Override // com.google.protobuf.Parser
        public StartServerHandshakeReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new StartServerHandshakeReq(codedInputStream, extensionRegistryLite);
        }
    };

    private StartServerHandshakeReq(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private StartServerHandshakeReq() {
        this.memoizedIsInitialized = (byte) -1;
        this.applicationProtocols_ = LazyStringArrayList.EMPTY;
        this.inBytes_ = ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new StartServerHandshakeReq();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    private StartServerHandshakeReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        char c = 0;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            int i = (c == true ? 1 : 0) & 1;
                            c = c;
                            if (i == 0) {
                                this.applicationProtocols_ = new LazyStringArrayList();
                                c = (c == true ? 1 : 0) | 1;
                            }
                            this.applicationProtocols_.add(readStringRequireUtf8);
                        } else if (readTag == 18) {
                            int i2 = (c == true ? 1 : 0) & 2;
                            c = c;
                            if (i2 == 0) {
                                this.handshakeParameters_ = MapField.newMapField(HandshakeParametersDefaultEntryHolder.defaultEntry);
                                c = (c == true ? 1 : 0) | 2;
                            }
                            MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(HandshakeParametersDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.handshakeParameters_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                        } else if (readTag != 26) {
                            if (readTag == 34) {
                                Endpoint.Builder builder = this.localEndpoint_ != null ? this.localEndpoint_.toBuilder() : null;
                                this.localEndpoint_ = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.localEndpoint_);
                                    this.localEndpoint_ = builder.buildPartial();
                                }
                            } else if (readTag == 42) {
                                Endpoint.Builder builder2 = this.remoteEndpoint_ != null ? this.remoteEndpoint_.toBuilder() : null;
                                this.remoteEndpoint_ = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.remoteEndpoint_);
                                    this.remoteEndpoint_ = builder2.buildPartial();
                                }
                            } else if (readTag == 50) {
                                RpcProtocolVersions.Builder builder3 = this.rpcVersions_ != null ? this.rpcVersions_.toBuilder() : null;
                                this.rpcVersions_ = (RpcProtocolVersions) codedInputStream.readMessage(RpcProtocolVersions.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.rpcVersions_);
                                    this.rpcVersions_ = builder3.buildPartial();
                                }
                            } else if (readTag == 56) {
                                this.maxFrameSize_ = codedInputStream.readUInt32();
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        } else {
                            this.inBytes_ = codedInputStream.readBytes();
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (((c == true ? 1 : 0) & 1) != 0) {
                    this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_StartServerHandshakeReq_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetHandshakeParameters();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.f8316x96f875dd.ensureFieldAccessorsInitialized(StartServerHandshakeReq.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public ProtocolStringList getApplicationProtocolsList() {
        return this.applicationProtocols_;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public int getApplicationProtocolsCount() {
        return this.applicationProtocols_.size();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public String getApplicationProtocols(int i) {
        return (String) this.applicationProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public ByteString getApplicationProtocolsBytes(int i) {
        return this.applicationProtocols_.getByteString(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class HandshakeParametersDefaultEntryHolder {
        static final MapEntry<Integer, ServerHandshakeParameters> defaultEntry = MapEntry.newDefaultInstance(HandshakerProto.f8314xb30523ad, WireFormat.FieldType.INT32, 0, WireFormat.FieldType.MESSAGE, ServerHandshakeParameters.getDefaultInstance());

        private HandshakeParametersDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<Integer, ServerHandshakeParameters> internalGetHandshakeParameters() {
        MapField<Integer, ServerHandshakeParameters> mapField = this.handshakeParameters_;
        return mapField == null ? MapField.emptyMapField(HandshakeParametersDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public int getHandshakeParametersCount() {
        return internalGetHandshakeParameters().getMap().size();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public boolean containsHandshakeParameters(int i) {
        return internalGetHandshakeParameters().getMap().containsKey(Integer.valueOf(i));
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    @Deprecated
    public Map<Integer, ServerHandshakeParameters> getHandshakeParameters() {
        return getHandshakeParametersMap();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public Map<Integer, ServerHandshakeParameters> getHandshakeParametersMap() {
        return internalGetHandshakeParameters().getMap();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public ServerHandshakeParameters getHandshakeParametersOrDefault(int i, ServerHandshakeParameters serverHandshakeParameters) {
        Map<Integer, ServerHandshakeParameters> map = internalGetHandshakeParameters().getMap();
        return map.containsKey(Integer.valueOf(i)) ? map.get(Integer.valueOf(i)) : serverHandshakeParameters;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public ServerHandshakeParameters getHandshakeParametersOrThrow(int i) {
        Map<Integer, ServerHandshakeParameters> map = internalGetHandshakeParameters().getMap();
        if (!map.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException();
        }
        return map.get(Integer.valueOf(i));
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public ByteString getInBytes() {
        return this.inBytes_;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public boolean hasLocalEndpoint() {
        return this.localEndpoint_ != null;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public Endpoint getLocalEndpoint() {
        Endpoint endpoint = this.localEndpoint_;
        return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public EndpointOrBuilder getLocalEndpointOrBuilder() {
        return getLocalEndpoint();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public boolean hasRemoteEndpoint() {
        return this.remoteEndpoint_ != null;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public Endpoint getRemoteEndpoint() {
        Endpoint endpoint = this.remoteEndpoint_;
        return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public EndpointOrBuilder getRemoteEndpointOrBuilder() {
        return getRemoteEndpoint();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public boolean hasRpcVersions() {
        return this.rpcVersions_ != null;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public RpcProtocolVersions getRpcVersions() {
        RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
        return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
    public RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder() {
        return getRpcVersions();
    }

    @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
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
        for (int i = 0; i < this.applicationProtocols_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.applicationProtocols_.getRaw(i));
        }
        GeneratedMessageV3.serializeIntegerMapTo(codedOutputStream, internalGetHandshakeParameters(), HandshakeParametersDefaultEntryHolder.defaultEntry, 2);
        if (!this.inBytes_.isEmpty()) {
            codedOutputStream.writeBytes(3, this.inBytes_);
        }
        if (this.localEndpoint_ != null) {
            codedOutputStream.writeMessage(4, getLocalEndpoint());
        }
        if (this.remoteEndpoint_ != null) {
            codedOutputStream.writeMessage(5, getRemoteEndpoint());
        }
        if (this.rpcVersions_ != null) {
            codedOutputStream.writeMessage(6, getRpcVersions());
        }
        int i2 = this.maxFrameSize_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(7, i2);
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
        for (int i3 = 0; i3 < this.applicationProtocols_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.applicationProtocols_.getRaw(i3));
        }
        int size = 0 + i2 + (getApplicationProtocolsList().size() * 1);
        for (Map.Entry<Integer, ServerHandshakeParameters> entry : internalGetHandshakeParameters().getMap().entrySet()) {
            size += CodedOutputStream.computeMessageSize(2, HandshakeParametersDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        if (!this.inBytes_.isEmpty()) {
            size += CodedOutputStream.computeBytesSize(3, this.inBytes_);
        }
        if (this.localEndpoint_ != null) {
            size += CodedOutputStream.computeMessageSize(4, getLocalEndpoint());
        }
        if (this.remoteEndpoint_ != null) {
            size += CodedOutputStream.computeMessageSize(5, getRemoteEndpoint());
        }
        if (this.rpcVersions_ != null) {
            size += CodedOutputStream.computeMessageSize(6, getRpcVersions());
        }
        int i4 = this.maxFrameSize_;
        if (i4 != 0) {
            size += CodedOutputStream.computeUInt32Size(7, i4);
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
        if (!(obj instanceof StartServerHandshakeReq)) {
            return super.equals(obj);
        }
        StartServerHandshakeReq startServerHandshakeReq = (StartServerHandshakeReq) obj;
        if (!getApplicationProtocolsList().equals(startServerHandshakeReq.getApplicationProtocolsList()) || !internalGetHandshakeParameters().equals(startServerHandshakeReq.internalGetHandshakeParameters()) || !getInBytes().equals(startServerHandshakeReq.getInBytes()) || hasLocalEndpoint() != startServerHandshakeReq.hasLocalEndpoint()) {
            return false;
        }
        if ((hasLocalEndpoint() && !getLocalEndpoint().equals(startServerHandshakeReq.getLocalEndpoint())) || hasRemoteEndpoint() != startServerHandshakeReq.hasRemoteEndpoint()) {
            return false;
        }
        if ((!hasRemoteEndpoint() || getRemoteEndpoint().equals(startServerHandshakeReq.getRemoteEndpoint())) && hasRpcVersions() == startServerHandshakeReq.hasRpcVersions()) {
            return (!hasRpcVersions() || getRpcVersions().equals(startServerHandshakeReq.getRpcVersions())) && getMaxFrameSize() == startServerHandshakeReq.getMaxFrameSize() && this.unknownFields.equals(startServerHandshakeReq.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getApplicationProtocolsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getApplicationProtocolsList().hashCode();
        }
        if (!internalGetHandshakeParameters().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 2) * 53) + internalGetHandshakeParameters().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 3) * 53) + getInBytes().hashCode();
        if (hasLocalEndpoint()) {
            hashCode2 = (((hashCode2 * 37) + 4) * 53) + getLocalEndpoint().hashCode();
        }
        if (hasRemoteEndpoint()) {
            hashCode2 = (((hashCode2 * 37) + 5) * 53) + getRemoteEndpoint().hashCode();
        }
        if (hasRpcVersions()) {
            hashCode2 = (((hashCode2 * 37) + 6) * 53) + getRpcVersions().hashCode();
        }
        int maxFrameSize = (((((hashCode2 * 37) + 7) * 53) + getMaxFrameSize()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = maxFrameSize;
        return maxFrameSize;
    }

    public static StartServerHandshakeReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static StartServerHandshakeReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static StartServerHandshakeReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static StartServerHandshakeReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static StartServerHandshakeReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static StartServerHandshakeReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static StartServerHandshakeReq parseFrom(InputStream inputStream) throws IOException {
        return (StartServerHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static StartServerHandshakeReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StartServerHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StartServerHandshakeReq parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StartServerHandshakeReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static StartServerHandshakeReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StartServerHandshakeReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StartServerHandshakeReq parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StartServerHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static StartServerHandshakeReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StartServerHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StartServerHandshakeReq startServerHandshakeReq) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(startServerHandshakeReq);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StartServerHandshakeReqOrBuilder {
        private LazyStringList applicationProtocols_;
        private int bitField0_;
        private MapField<Integer, ServerHandshakeParameters> handshakeParameters_;
        private ByteString inBytes_;
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> localEndpointBuilder_;
        private Endpoint localEndpoint_;
        private int maxFrameSize_;
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> remoteEndpointBuilder_;
        private Endpoint remoteEndpoint_;
        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> rpcVersionsBuilder_;
        private RpcProtocolVersions rpcVersions_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_StartServerHandshakeReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetHandshakeParameters();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableHandshakeParameters();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.f8316x96f875dd.ensureFieldAccessorsInitialized(StartServerHandshakeReq.class, Builder.class);
        }

        private Builder() {
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.inBytes_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.inBytes_ = ByteString.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = StartServerHandshakeReq.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            internalGetMutableHandshakeParameters().clear();
            this.inBytes_ = ByteString.EMPTY;
            if (this.localEndpointBuilder_ == null) {
                this.localEndpoint_ = null;
            } else {
                this.localEndpoint_ = null;
                this.localEndpointBuilder_ = null;
            }
            if (this.remoteEndpointBuilder_ == null) {
                this.remoteEndpoint_ = null;
            } else {
                this.remoteEndpoint_ = null;
                this.remoteEndpointBuilder_ = null;
            }
            if (this.rpcVersionsBuilder_ == null) {
                this.rpcVersions_ = null;
            } else {
                this.rpcVersions_ = null;
                this.rpcVersionsBuilder_ = null;
            }
            this.maxFrameSize_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_StartServerHandshakeReq_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public StartServerHandshakeReq getDefaultInstanceForType() {
            return StartServerHandshakeReq.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StartServerHandshakeReq build() {
            StartServerHandshakeReq buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StartServerHandshakeReq buildPartial() {
            StartServerHandshakeReq startServerHandshakeReq = new StartServerHandshakeReq(this);
            if ((this.bitField0_ & 1) != 0) {
                this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            startServerHandshakeReq.applicationProtocols_ = this.applicationProtocols_;
            startServerHandshakeReq.handshakeParameters_ = internalGetHandshakeParameters();
            startServerHandshakeReq.handshakeParameters_.makeImmutable();
            startServerHandshakeReq.inBytes_ = this.inBytes_;
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                startServerHandshakeReq.localEndpoint_ = this.localEndpoint_;
            } else {
                startServerHandshakeReq.localEndpoint_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV32 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV32 == null) {
                startServerHandshakeReq.remoteEndpoint_ = this.remoteEndpoint_;
            } else {
                startServerHandshakeReq.remoteEndpoint_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV33 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV33 == null) {
                startServerHandshakeReq.rpcVersions_ = this.rpcVersions_;
            } else {
                startServerHandshakeReq.rpcVersions_ = singleFieldBuilderV33.build();
            }
            startServerHandshakeReq.maxFrameSize_ = this.maxFrameSize_;
            onBuilt();
            return startServerHandshakeReq;
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
            if (message instanceof StartServerHandshakeReq) {
                return mergeFrom((StartServerHandshakeReq) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(StartServerHandshakeReq startServerHandshakeReq) {
            if (startServerHandshakeReq == StartServerHandshakeReq.getDefaultInstance()) {
                return this;
            }
            if (!startServerHandshakeReq.applicationProtocols_.isEmpty()) {
                if (this.applicationProtocols_.isEmpty()) {
                    this.applicationProtocols_ = startServerHandshakeReq.applicationProtocols_;
                    this.bitField0_ &= -2;
                } else {
                    ensureApplicationProtocolsIsMutable();
                    this.applicationProtocols_.addAll(startServerHandshakeReq.applicationProtocols_);
                }
                onChanged();
            }
            internalGetMutableHandshakeParameters().mergeFrom(startServerHandshakeReq.internalGetHandshakeParameters());
            if (startServerHandshakeReq.getInBytes() != ByteString.EMPTY) {
                setInBytes(startServerHandshakeReq.getInBytes());
            }
            if (startServerHandshakeReq.hasLocalEndpoint()) {
                mergeLocalEndpoint(startServerHandshakeReq.getLocalEndpoint());
            }
            if (startServerHandshakeReq.hasRemoteEndpoint()) {
                mergeRemoteEndpoint(startServerHandshakeReq.getRemoteEndpoint());
            }
            if (startServerHandshakeReq.hasRpcVersions()) {
                mergeRpcVersions(startServerHandshakeReq.getRpcVersions());
            }
            if (startServerHandshakeReq.getMaxFrameSize() != 0) {
                setMaxFrameSize(startServerHandshakeReq.getMaxFrameSize());
            }
            mergeUnknownFields(startServerHandshakeReq.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StartServerHandshakeReq startServerHandshakeReq = null;
            try {
                try {
                    StartServerHandshakeReq startServerHandshakeReq2 = (StartServerHandshakeReq) StartServerHandshakeReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (startServerHandshakeReq2 != null) {
                        mergeFrom(startServerHandshakeReq2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    StartServerHandshakeReq startServerHandshakeReq3 = (StartServerHandshakeReq) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        startServerHandshakeReq = startServerHandshakeReq3;
                        if (startServerHandshakeReq != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (startServerHandshakeReq != null) {
                    mergeFrom(startServerHandshakeReq);
                }
                throw th;
            }
        }

        private void ensureApplicationProtocolsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.applicationProtocols_ = new LazyStringArrayList(this.applicationProtocols_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public ProtocolStringList getApplicationProtocolsList() {
            return this.applicationProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public int getApplicationProtocolsCount() {
            return this.applicationProtocols_.size();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public String getApplicationProtocols(int i) {
            return (String) this.applicationProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public ByteString getApplicationProtocolsBytes(int i) {
            return this.applicationProtocols_.getByteString(i);
        }

        public Builder setApplicationProtocols(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addApplicationProtocols(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureApplicationProtocolsIsMutable();
            this.applicationProtocols_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllApplicationProtocols(Iterable<String> iterable) {
            ensureApplicationProtocolsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.applicationProtocols_);
            onChanged();
            return this;
        }

        public Builder clearApplicationProtocols() {
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addApplicationProtocolsBytes(ByteString byteString) {
            if (byteString != null) {
                StartServerHandshakeReq.checkByteStringIsUtf8(byteString);
                ensureApplicationProtocolsIsMutable();
                this.applicationProtocols_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<Integer, ServerHandshakeParameters> internalGetHandshakeParameters() {
            MapField<Integer, ServerHandshakeParameters> mapField = this.handshakeParameters_;
            return mapField == null ? MapField.emptyMapField(HandshakeParametersDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<Integer, ServerHandshakeParameters> internalGetMutableHandshakeParameters() {
            onChanged();
            if (this.handshakeParameters_ == null) {
                this.handshakeParameters_ = MapField.newMapField(HandshakeParametersDefaultEntryHolder.defaultEntry);
            }
            if (!this.handshakeParameters_.isMutable()) {
                this.handshakeParameters_ = this.handshakeParameters_.copy();
            }
            return this.handshakeParameters_;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public int getHandshakeParametersCount() {
            return internalGetHandshakeParameters().getMap().size();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public boolean containsHandshakeParameters(int i) {
            return internalGetHandshakeParameters().getMap().containsKey(Integer.valueOf(i));
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        @Deprecated
        public Map<Integer, ServerHandshakeParameters> getHandshakeParameters() {
            return getHandshakeParametersMap();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public Map<Integer, ServerHandshakeParameters> getHandshakeParametersMap() {
            return internalGetHandshakeParameters().getMap();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public ServerHandshakeParameters getHandshakeParametersOrDefault(int i, ServerHandshakeParameters serverHandshakeParameters) {
            Map<Integer, ServerHandshakeParameters> map = internalGetHandshakeParameters().getMap();
            return map.containsKey(Integer.valueOf(i)) ? map.get(Integer.valueOf(i)) : serverHandshakeParameters;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public ServerHandshakeParameters getHandshakeParametersOrThrow(int i) {
            Map<Integer, ServerHandshakeParameters> map = internalGetHandshakeParameters().getMap();
            if (!map.containsKey(Integer.valueOf(i))) {
                throw new IllegalArgumentException();
            }
            return map.get(Integer.valueOf(i));
        }

        public Builder clearHandshakeParameters() {
            internalGetMutableHandshakeParameters().getMutableMap().clear();
            return this;
        }

        public Builder removeHandshakeParameters(int i) {
            internalGetMutableHandshakeParameters().getMutableMap().remove(Integer.valueOf(i));
            return this;
        }

        @Deprecated
        public Map<Integer, ServerHandshakeParameters> getMutableHandshakeParameters() {
            return internalGetMutableHandshakeParameters().getMutableMap();
        }

        public Builder putHandshakeParameters(int i, ServerHandshakeParameters serverHandshakeParameters) {
            if (serverHandshakeParameters == null) {
                throw new NullPointerException();
            }
            internalGetMutableHandshakeParameters().getMutableMap().put(Integer.valueOf(i), serverHandshakeParameters);
            return this;
        }

        public Builder putAllHandshakeParameters(Map<Integer, ServerHandshakeParameters> map) {
            internalGetMutableHandshakeParameters().getMutableMap().putAll(map);
            return this;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public ByteString getInBytes() {
            return this.inBytes_;
        }

        public Builder setInBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.inBytes_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearInBytes() {
            this.inBytes_ = StartServerHandshakeReq.getDefaultInstance().getInBytes();
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public boolean hasLocalEndpoint() {
            return (this.localEndpointBuilder_ == null && this.localEndpoint_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public Endpoint getLocalEndpoint() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                Endpoint endpoint = this.localEndpoint_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setLocalEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(endpoint);
            } else {
                if (endpoint == null) {
                    throw new NullPointerException();
                }
                this.localEndpoint_ = endpoint;
                onChanged();
            }
            return this;
        }

        public Builder setLocalEndpoint(Endpoint.Builder builder) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.localEndpoint_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLocalEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                Endpoint endpoint2 = this.localEndpoint_;
                if (endpoint2 != null) {
                    this.localEndpoint_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                } else {
                    this.localEndpoint_ = endpoint;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(endpoint);
            }
            return this;
        }

        public Builder clearLocalEndpoint() {
            if (this.localEndpointBuilder_ == null) {
                this.localEndpoint_ = null;
                onChanged();
            } else {
                this.localEndpoint_ = null;
                this.localEndpointBuilder_ = null;
            }
            return this;
        }

        public Endpoint.Builder getLocalEndpointBuilder() {
            onChanged();
            return getLocalEndpointFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public EndpointOrBuilder getLocalEndpointOrBuilder() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.localEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Endpoint endpoint = this.localEndpoint_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getLocalEndpointFieldBuilder() {
            if (this.localEndpointBuilder_ == null) {
                this.localEndpointBuilder_ = new SingleFieldBuilderV3<>(getLocalEndpoint(), getParentForChildren(), isClean());
                this.localEndpoint_ = null;
            }
            return this.localEndpointBuilder_;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public boolean hasRemoteEndpoint() {
            return (this.remoteEndpointBuilder_ == null && this.remoteEndpoint_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public Endpoint getRemoteEndpoint() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                Endpoint endpoint = this.remoteEndpoint_;
                return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRemoteEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(endpoint);
            } else {
                if (endpoint == null) {
                    throw new NullPointerException();
                }
                this.remoteEndpoint_ = endpoint;
                onChanged();
            }
            return this;
        }

        public Builder setRemoteEndpoint(Endpoint.Builder builder) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.remoteEndpoint_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRemoteEndpoint(Endpoint endpoint) {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 == null) {
                Endpoint endpoint2 = this.remoteEndpoint_;
                if (endpoint2 != null) {
                    this.remoteEndpoint_ = Endpoint.newBuilder(endpoint2).mergeFrom(endpoint).buildPartial();
                } else {
                    this.remoteEndpoint_ = endpoint;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(endpoint);
            }
            return this;
        }

        public Builder clearRemoteEndpoint() {
            if (this.remoteEndpointBuilder_ == null) {
                this.remoteEndpoint_ = null;
                onChanged();
            } else {
                this.remoteEndpoint_ = null;
                this.remoteEndpointBuilder_ = null;
            }
            return this;
        }

        public Endpoint.Builder getRemoteEndpointBuilder() {
            onChanged();
            return getRemoteEndpointFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public EndpointOrBuilder getRemoteEndpointOrBuilder() {
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV3 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Endpoint endpoint = this.remoteEndpoint_;
            return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
        }

        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> getRemoteEndpointFieldBuilder() {
            if (this.remoteEndpointBuilder_ == null) {
                this.remoteEndpointBuilder_ = new SingleFieldBuilderV3<>(getRemoteEndpoint(), getParentForChildren(), isClean());
                this.remoteEndpoint_ = null;
            }
            return this.remoteEndpointBuilder_;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public boolean hasRpcVersions() {
            return (this.rpcVersionsBuilder_ == null && this.rpcVersions_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public RpcProtocolVersions getRpcVersions() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
                return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(rpcProtocolVersions);
            } else {
                if (rpcProtocolVersions == null) {
                    throw new NullPointerException();
                }
                this.rpcVersions_ = rpcProtocolVersions;
                onChanged();
            }
            return this;
        }

        public Builder setRpcVersions(RpcProtocolVersions.Builder builder) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.rpcVersions_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRpcVersions(RpcProtocolVersions rpcProtocolVersions) {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                RpcProtocolVersions rpcProtocolVersions2 = this.rpcVersions_;
                if (rpcProtocolVersions2 != null) {
                    this.rpcVersions_ = RpcProtocolVersions.newBuilder(rpcProtocolVersions2).mergeFrom(rpcProtocolVersions).buildPartial();
                } else {
                    this.rpcVersions_ = rpcProtocolVersions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(rpcProtocolVersions);
            }
            return this;
        }

        public Builder clearRpcVersions() {
            if (this.rpcVersionsBuilder_ == null) {
                this.rpcVersions_ = null;
                onChanged();
            } else {
                this.rpcVersions_ = null;
                this.rpcVersionsBuilder_ = null;
            }
            return this;
        }

        public RpcProtocolVersions.Builder getRpcVersionsBuilder() {
            onChanged();
            return getRpcVersionsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
        public RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder() {
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV3 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
            return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
        }

        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> getRpcVersionsFieldBuilder() {
            if (this.rpcVersionsBuilder_ == null) {
                this.rpcVersionsBuilder_ = new SingleFieldBuilderV3<>(getRpcVersions(), getParentForChildren(), isClean());
                this.rpcVersions_ = null;
            }
            return this.rpcVersionsBuilder_;
        }

        @Override // io.grpc.alts.internal.StartServerHandshakeReqOrBuilder
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

    public static StartServerHandshakeReq getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StartServerHandshakeReq> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<StartServerHandshakeReq> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public StartServerHandshakeReq getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
