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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.alts.internal.Endpoint;
import io.grpc.alts.internal.Identity;
import io.grpc.alts.internal.RpcProtocolVersions;
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
public final class StartClientHandshakeReq extends GeneratedMessageV3 implements StartClientHandshakeReqOrBuilder {
    public static final int APPLICATION_PROTOCOLS_FIELD_NUMBER = 2;
    public static final int HANDSHAKE_SECURITY_PROTOCOL_FIELD_NUMBER = 1;
    public static final int LOCAL_ENDPOINT_FIELD_NUMBER = 6;
    public static final int LOCAL_IDENTITY_FIELD_NUMBER = 5;
    public static final int MAX_FRAME_SIZE_FIELD_NUMBER = 10;
    public static final int RECORD_PROTOCOLS_FIELD_NUMBER = 3;
    public static final int REMOTE_ENDPOINT_FIELD_NUMBER = 7;
    public static final int RPC_VERSIONS_FIELD_NUMBER = 9;
    public static final int TARGET_IDENTITIES_FIELD_NUMBER = 4;
    public static final int TARGET_NAME_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    private LazyStringList applicationProtocols_;
    private int handshakeSecurityProtocol_;
    private Endpoint localEndpoint_;
    private Identity localIdentity_;
    private int maxFrameSize_;
    private byte memoizedIsInitialized;
    private LazyStringList recordProtocols_;
    private Endpoint remoteEndpoint_;
    private RpcProtocolVersions rpcVersions_;
    private List<Identity> targetIdentities_;
    private volatile Object targetName_;
    private static final StartClientHandshakeReq DEFAULT_INSTANCE = new StartClientHandshakeReq();
    private static final Parser<StartClientHandshakeReq> PARSER = new AbstractParser<StartClientHandshakeReq>() { // from class: io.grpc.alts.internal.StartClientHandshakeReq.1
        @Override // com.google.protobuf.Parser
        public StartClientHandshakeReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new StartClientHandshakeReq(codedInputStream, extensionRegistryLite);
        }
    };

    private StartClientHandshakeReq(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private StartClientHandshakeReq() {
        this.memoizedIsInitialized = (byte) -1;
        this.handshakeSecurityProtocol_ = 0;
        this.applicationProtocols_ = LazyStringArrayList.EMPTY;
        this.recordProtocols_ = LazyStringArrayList.EMPTY;
        this.targetIdentities_ = Collections.emptyList();
        this.targetName_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new StartClientHandshakeReq();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0013. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    private StartClientHandshakeReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    switch (readTag) {
                        case 0:
                            z = true;
                        case 8:
                            this.handshakeSecurityProtocol_ = codedInputStream.readEnum();
                        case 18:
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            int i = (c == true ? 1 : 0) & 1;
                            c = c;
                            if (i == 0) {
                                this.applicationProtocols_ = new LazyStringArrayList();
                                c = (c == true ? 1 : 0) | 1;
                            }
                            this.applicationProtocols_.add(readStringRequireUtf8);
                        case 26:
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            int i2 = (c == true ? 1 : 0) & 2;
                            c = c;
                            if (i2 == 0) {
                                this.recordProtocols_ = new LazyStringArrayList();
                                c = (c == true ? 1 : 0) | 2;
                            }
                            this.recordProtocols_.add(readStringRequireUtf82);
                        case 34:
                            int i3 = (c == true ? 1 : 0) & 4;
                            c = c;
                            if (i3 == 0) {
                                this.targetIdentities_ = new ArrayList();
                                c = (c == true ? 1 : 0) | 4;
                            }
                            this.targetIdentities_.add(codedInputStream.readMessage(Identity.parser(), extensionRegistryLite));
                        case 42:
                            Identity.Builder builder = this.localIdentity_ != null ? this.localIdentity_.toBuilder() : null;
                            this.localIdentity_ = (Identity) codedInputStream.readMessage(Identity.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.localIdentity_);
                                this.localIdentity_ = builder.buildPartial();
                            }
                        case 50:
                            Endpoint.Builder builder2 = this.localEndpoint_ != null ? this.localEndpoint_.toBuilder() : null;
                            this.localEndpoint_ = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.localEndpoint_);
                                this.localEndpoint_ = builder2.buildPartial();
                            }
                        case 58:
                            Endpoint.Builder builder3 = this.remoteEndpoint_ != null ? this.remoteEndpoint_.toBuilder() : null;
                            this.remoteEndpoint_ = (Endpoint) codedInputStream.readMessage(Endpoint.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.remoteEndpoint_);
                                this.remoteEndpoint_ = builder3.buildPartial();
                            }
                        case 66:
                            this.targetName_ = codedInputStream.readStringRequireUtf8();
                        case 74:
                            RpcProtocolVersions.Builder builder4 = this.rpcVersions_ != null ? this.rpcVersions_.toBuilder() : null;
                            this.rpcVersions_ = (RpcProtocolVersions) codedInputStream.readMessage(RpcProtocolVersions.parser(), extensionRegistryLite);
                            if (builder4 != null) {
                                builder4.mergeFrom(this.rpcVersions_);
                                this.rpcVersions_ = builder4.buildPartial();
                            }
                        case 80:
                            this.maxFrameSize_ = codedInputStream.readUInt32();
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (((c == true ? 1 : 0) & 1) != 0) {
                    this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                }
                if (((c == true ? 1 : 0) & 2) != 0) {
                    this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                }
                if (((c == true ? 1 : 0) & 4) != 0) {
                    this.targetIdentities_ = Collections.unmodifiableList(this.targetIdentities_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.f8313x5a712055.ensureFieldAccessorsInitialized(StartClientHandshakeReq.class, Builder.class);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getHandshakeSecurityProtocolValue() {
        return this.handshakeSecurityProtocol_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public HandshakeProtocol getHandshakeSecurityProtocol() {
        HandshakeProtocol valueOf = HandshakeProtocol.valueOf(this.handshakeSecurityProtocol_);
        return valueOf == null ? HandshakeProtocol.UNRECOGNIZED : valueOf;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ProtocolStringList getApplicationProtocolsList() {
        return this.applicationProtocols_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getApplicationProtocolsCount() {
        return this.applicationProtocols_.size();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public String getApplicationProtocols(int i) {
        return (String) this.applicationProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ByteString getApplicationProtocolsBytes(int i) {
        return this.applicationProtocols_.getByteString(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ProtocolStringList getRecordProtocolsList() {
        return this.recordProtocols_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getRecordProtocolsCount() {
        return this.recordProtocols_.size();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public String getRecordProtocols(int i) {
        return (String) this.recordProtocols_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ByteString getRecordProtocolsBytes(int i) {
        return this.recordProtocols_.getByteString(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public List<Identity> getTargetIdentitiesList() {
        return this.targetIdentities_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public List<? extends IdentityOrBuilder> getTargetIdentitiesOrBuilderList() {
        return this.targetIdentities_;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public int getTargetIdentitiesCount() {
        return this.targetIdentities_.size();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Identity getTargetIdentities(int i) {
        return this.targetIdentities_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public IdentityOrBuilder getTargetIdentitiesOrBuilder(int i) {
        return this.targetIdentities_.get(i);
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasLocalIdentity() {
        return this.localIdentity_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Identity getLocalIdentity() {
        Identity identity = this.localIdentity_;
        return identity == null ? Identity.getDefaultInstance() : identity;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public IdentityOrBuilder getLocalIdentityOrBuilder() {
        return getLocalIdentity();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasLocalEndpoint() {
        return this.localEndpoint_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Endpoint getLocalEndpoint() {
        Endpoint endpoint = this.localEndpoint_;
        return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public EndpointOrBuilder getLocalEndpointOrBuilder() {
        return getLocalEndpoint();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasRemoteEndpoint() {
        return this.remoteEndpoint_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public Endpoint getRemoteEndpoint() {
        Endpoint endpoint = this.remoteEndpoint_;
        return endpoint == null ? Endpoint.getDefaultInstance() : endpoint;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public EndpointOrBuilder getRemoteEndpointOrBuilder() {
        return getRemoteEndpoint();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public String getTargetName() {
        Object obj = this.targetName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.targetName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public ByteString getTargetNameBytes() {
        Object obj = this.targetName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.targetName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public boolean hasRpcVersions() {
        return this.rpcVersions_ != null;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public RpcProtocolVersions getRpcVersions() {
        RpcProtocolVersions rpcProtocolVersions = this.rpcVersions_;
        return rpcProtocolVersions == null ? RpcProtocolVersions.getDefaultInstance() : rpcProtocolVersions;
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
    public RpcProtocolVersionsOrBuilder getRpcVersionsOrBuilder() {
        return getRpcVersions();
    }

    @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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
        if (this.handshakeSecurityProtocol_ != HandshakeProtocol.HANDSHAKE_PROTOCOL_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.handshakeSecurityProtocol_);
        }
        for (int i = 0; i < this.applicationProtocols_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.applicationProtocols_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.recordProtocols_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.recordProtocols_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.targetIdentities_.size(); i3++) {
            codedOutputStream.writeMessage(4, this.targetIdentities_.get(i3));
        }
        if (this.localIdentity_ != null) {
            codedOutputStream.writeMessage(5, getLocalIdentity());
        }
        if (this.localEndpoint_ != null) {
            codedOutputStream.writeMessage(6, getLocalEndpoint());
        }
        if (this.remoteEndpoint_ != null) {
            codedOutputStream.writeMessage(7, getRemoteEndpoint());
        }
        if (!getTargetNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.targetName_);
        }
        if (this.rpcVersions_ != null) {
            codedOutputStream.writeMessage(9, getRpcVersions());
        }
        int i4 = this.maxFrameSize_;
        if (i4 != 0) {
            codedOutputStream.writeUInt32(10, i4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.handshakeSecurityProtocol_ != HandshakeProtocol.HANDSHAKE_PROTOCOL_UNSPECIFIED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.handshakeSecurityProtocol_) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.applicationProtocols_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.applicationProtocols_.getRaw(i3));
        }
        int size = computeEnumSize + i2 + (getApplicationProtocolsList().size() * 1);
        int i4 = 0;
        for (int i5 = 0; i5 < this.recordProtocols_.size(); i5++) {
            i4 += computeStringSizeNoTag(this.recordProtocols_.getRaw(i5));
        }
        int size2 = size + i4 + (getRecordProtocolsList().size() * 1);
        for (int i6 = 0; i6 < this.targetIdentities_.size(); i6++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.targetIdentities_.get(i6));
        }
        if (this.localIdentity_ != null) {
            size2 += CodedOutputStream.computeMessageSize(5, getLocalIdentity());
        }
        if (this.localEndpoint_ != null) {
            size2 += CodedOutputStream.computeMessageSize(6, getLocalEndpoint());
        }
        if (this.remoteEndpoint_ != null) {
            size2 += CodedOutputStream.computeMessageSize(7, getRemoteEndpoint());
        }
        if (!getTargetNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(8, this.targetName_);
        }
        if (this.rpcVersions_ != null) {
            size2 += CodedOutputStream.computeMessageSize(9, getRpcVersions());
        }
        int i7 = this.maxFrameSize_;
        if (i7 != 0) {
            size2 += CodedOutputStream.computeUInt32Size(10, i7);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StartClientHandshakeReq)) {
            return super.equals(obj);
        }
        StartClientHandshakeReq startClientHandshakeReq = (StartClientHandshakeReq) obj;
        if (this.handshakeSecurityProtocol_ != startClientHandshakeReq.handshakeSecurityProtocol_ || !getApplicationProtocolsList().equals(startClientHandshakeReq.getApplicationProtocolsList()) || !getRecordProtocolsList().equals(startClientHandshakeReq.getRecordProtocolsList()) || !getTargetIdentitiesList().equals(startClientHandshakeReq.getTargetIdentitiesList()) || hasLocalIdentity() != startClientHandshakeReq.hasLocalIdentity()) {
            return false;
        }
        if ((hasLocalIdentity() && !getLocalIdentity().equals(startClientHandshakeReq.getLocalIdentity())) || hasLocalEndpoint() != startClientHandshakeReq.hasLocalEndpoint()) {
            return false;
        }
        if ((hasLocalEndpoint() && !getLocalEndpoint().equals(startClientHandshakeReq.getLocalEndpoint())) || hasRemoteEndpoint() != startClientHandshakeReq.hasRemoteEndpoint()) {
            return false;
        }
        if ((!hasRemoteEndpoint() || getRemoteEndpoint().equals(startClientHandshakeReq.getRemoteEndpoint())) && getTargetName().equals(startClientHandshakeReq.getTargetName()) && hasRpcVersions() == startClientHandshakeReq.hasRpcVersions()) {
            return (!hasRpcVersions() || getRpcVersions().equals(startClientHandshakeReq.getRpcVersions())) && getMaxFrameSize() == startClientHandshakeReq.getMaxFrameSize() && this.unknownFields.equals(startClientHandshakeReq.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.handshakeSecurityProtocol_;
        if (getApplicationProtocolsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getApplicationProtocolsList().hashCode();
        }
        if (getRecordProtocolsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getRecordProtocolsList().hashCode();
        }
        if (getTargetIdentitiesCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getTargetIdentitiesList().hashCode();
        }
        if (hasLocalIdentity()) {
            hashCode = (((hashCode * 37) + 5) * 53) + getLocalIdentity().hashCode();
        }
        if (hasLocalEndpoint()) {
            hashCode = (((hashCode * 37) + 6) * 53) + getLocalEndpoint().hashCode();
        }
        if (hasRemoteEndpoint()) {
            hashCode = (((hashCode * 37) + 7) * 53) + getRemoteEndpoint().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 8) * 53) + getTargetName().hashCode();
        if (hasRpcVersions()) {
            hashCode2 = (((hashCode2 * 37) + 9) * 53) + getRpcVersions().hashCode();
        }
        int maxFrameSize = (((((hashCode2 * 37) + 10) * 53) + getMaxFrameSize()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = maxFrameSize;
        return maxFrameSize;
    }

    public static StartClientHandshakeReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static StartClientHandshakeReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static StartClientHandshakeReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static StartClientHandshakeReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(InputStream inputStream) throws IOException {
        return (StartClientHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static StartClientHandshakeReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StartClientHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StartClientHandshakeReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static StartClientHandshakeReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StartClientHandshakeReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StartClientHandshakeReq parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StartClientHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static StartClientHandshakeReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StartClientHandshakeReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StartClientHandshakeReq startClientHandshakeReq) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(startClientHandshakeReq);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StartClientHandshakeReqOrBuilder {
        private LazyStringList applicationProtocols_;
        private int bitField0_;
        private int handshakeSecurityProtocol_;
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> localEndpointBuilder_;
        private Endpoint localEndpoint_;
        private SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> localIdentityBuilder_;
        private Identity localIdentity_;
        private int maxFrameSize_;
        private LazyStringList recordProtocols_;
        private SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> remoteEndpointBuilder_;
        private Endpoint remoteEndpoint_;
        private SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> rpcVersionsBuilder_;
        private RpcProtocolVersions rpcVersions_;
        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> targetIdentitiesBuilder_;
        private List<Identity> targetIdentities_;
        private Object targetName_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.f8313x5a712055.ensureFieldAccessorsInitialized(StartClientHandshakeReq.class, Builder.class);
        }

        private Builder() {
            this.handshakeSecurityProtocol_ = 0;
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.targetIdentities_ = Collections.emptyList();
            this.targetName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.handshakeSecurityProtocol_ = 0;
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.targetIdentities_ = Collections.emptyList();
            this.targetName_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (StartClientHandshakeReq.alwaysUseFieldBuilders) {
                getTargetIdentitiesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.handshakeSecurityProtocol_ = 0;
            this.applicationProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.recordProtocols_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.targetIdentities_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.localIdentityBuilder_ == null) {
                this.localIdentity_ = null;
            } else {
                this.localIdentity_ = null;
                this.localIdentityBuilder_ = null;
            }
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
            this.targetName_ = "";
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
            return HandshakerProto.internal_static_grpc_gcp_StartClientHandshakeReq_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public StartClientHandshakeReq getDefaultInstanceForType() {
            return StartClientHandshakeReq.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StartClientHandshakeReq build() {
            StartClientHandshakeReq buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StartClientHandshakeReq buildPartial() {
            StartClientHandshakeReq startClientHandshakeReq = new StartClientHandshakeReq(this);
            int i = this.bitField0_;
            startClientHandshakeReq.handshakeSecurityProtocol_ = this.handshakeSecurityProtocol_;
            if ((this.bitField0_ & 1) != 0) {
                this.applicationProtocols_ = this.applicationProtocols_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            startClientHandshakeReq.applicationProtocols_ = this.applicationProtocols_;
            if ((this.bitField0_ & 2) != 0) {
                this.recordProtocols_ = this.recordProtocols_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            startClientHandshakeReq.recordProtocols_ = this.recordProtocols_;
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                startClientHandshakeReq.targetIdentities_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 4) != 0) {
                    this.targetIdentities_ = Collections.unmodifiableList(this.targetIdentities_);
                    this.bitField0_ &= -5;
                }
                startClientHandshakeReq.targetIdentities_ = this.targetIdentities_;
            }
            SingleFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> singleFieldBuilderV3 = this.localIdentityBuilder_;
            if (singleFieldBuilderV3 == null) {
                startClientHandshakeReq.localIdentity_ = this.localIdentity_;
            } else {
                startClientHandshakeReq.localIdentity_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV32 = this.localEndpointBuilder_;
            if (singleFieldBuilderV32 == null) {
                startClientHandshakeReq.localEndpoint_ = this.localEndpoint_;
            } else {
                startClientHandshakeReq.localEndpoint_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Endpoint, Endpoint.Builder, EndpointOrBuilder> singleFieldBuilderV33 = this.remoteEndpointBuilder_;
            if (singleFieldBuilderV33 == null) {
                startClientHandshakeReq.remoteEndpoint_ = this.remoteEndpoint_;
            } else {
                startClientHandshakeReq.remoteEndpoint_ = singleFieldBuilderV33.build();
            }
            startClientHandshakeReq.targetName_ = this.targetName_;
            SingleFieldBuilderV3<RpcProtocolVersions, RpcProtocolVersions.Builder, RpcProtocolVersionsOrBuilder> singleFieldBuilderV34 = this.rpcVersionsBuilder_;
            if (singleFieldBuilderV34 == null) {
                startClientHandshakeReq.rpcVersions_ = this.rpcVersions_;
            } else {
                startClientHandshakeReq.rpcVersions_ = singleFieldBuilderV34.build();
            }
            startClientHandshakeReq.maxFrameSize_ = this.maxFrameSize_;
            onBuilt();
            return startClientHandshakeReq;
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
            if (message instanceof StartClientHandshakeReq) {
                return mergeFrom((StartClientHandshakeReq) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(StartClientHandshakeReq startClientHandshakeReq) {
            if (startClientHandshakeReq == StartClientHandshakeReq.getDefaultInstance()) {
                return this;
            }
            if (startClientHandshakeReq.handshakeSecurityProtocol_ != 0) {
                setHandshakeSecurityProtocolValue(startClientHandshakeReq.getHandshakeSecurityProtocolValue());
            }
            if (!startClientHandshakeReq.applicationProtocols_.isEmpty()) {
                if (this.applicationProtocols_.isEmpty()) {
                    this.applicationProtocols_ = startClientHandshakeReq.applicationProtocols_;
                    this.bitField0_ &= -2;
                } else {
                    ensureApplicationProtocolsIsMutable();
                    this.applicationProtocols_.addAll(startClientHandshakeReq.applicationProtocols_);
                }
                onChanged();
            }
            if (!startClientHandshakeReq.recordProtocols_.isEmpty()) {
                if (this.recordProtocols_.isEmpty()) {
                    this.recordProtocols_ = startClientHandshakeReq.recordProtocols_;
                    this.bitField0_ &= -3;
                } else {
                    ensureRecordProtocolsIsMutable();
                    this.recordProtocols_.addAll(startClientHandshakeReq.recordProtocols_);
                }
                onChanged();
            }
            if (this.targetIdentitiesBuilder_ == null) {
                if (!startClientHandshakeReq.targetIdentities_.isEmpty()) {
                    if (this.targetIdentities_.isEmpty()) {
                        this.targetIdentities_ = startClientHandshakeReq.targetIdentities_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureTargetIdentitiesIsMutable();
                        this.targetIdentities_.addAll(startClientHandshakeReq.targetIdentities_);
                    }
                    onChanged();
                }
            } else if (!startClientHandshakeReq.targetIdentities_.isEmpty()) {
                if (!this.targetIdentitiesBuilder_.isEmpty()) {
                    this.targetIdentitiesBuilder_.addAllMessages(startClientHandshakeReq.targetIdentities_);
                } else {
                    this.targetIdentitiesBuilder_.dispose();
                    this.targetIdentitiesBuilder_ = null;
                    this.targetIdentities_ = startClientHandshakeReq.targetIdentities_;
                    this.bitField0_ &= -5;
                    this.targetIdentitiesBuilder_ = StartClientHandshakeReq.alwaysUseFieldBuilders ? getTargetIdentitiesFieldBuilder() : null;
                }
            }
            if (startClientHandshakeReq.hasLocalIdentity()) {
                mergeLocalIdentity(startClientHandshakeReq.getLocalIdentity());
            }
            if (startClientHandshakeReq.hasLocalEndpoint()) {
                mergeLocalEndpoint(startClientHandshakeReq.getLocalEndpoint());
            }
            if (startClientHandshakeReq.hasRemoteEndpoint()) {
                mergeRemoteEndpoint(startClientHandshakeReq.getRemoteEndpoint());
            }
            if (!startClientHandshakeReq.getTargetName().isEmpty()) {
                this.targetName_ = startClientHandshakeReq.targetName_;
                onChanged();
            }
            if (startClientHandshakeReq.hasRpcVersions()) {
                mergeRpcVersions(startClientHandshakeReq.getRpcVersions());
            }
            if (startClientHandshakeReq.getMaxFrameSize() != 0) {
                setMaxFrameSize(startClientHandshakeReq.getMaxFrameSize());
            }
            mergeUnknownFields(startClientHandshakeReq.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StartClientHandshakeReq startClientHandshakeReq = null;
            try {
                try {
                    StartClientHandshakeReq startClientHandshakeReq2 = (StartClientHandshakeReq) StartClientHandshakeReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (startClientHandshakeReq2 != null) {
                        mergeFrom(startClientHandshakeReq2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    StartClientHandshakeReq startClientHandshakeReq3 = (StartClientHandshakeReq) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        startClientHandshakeReq = startClientHandshakeReq3;
                        if (startClientHandshakeReq != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (startClientHandshakeReq != null) {
                    mergeFrom(startClientHandshakeReq);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getHandshakeSecurityProtocolValue() {
            return this.handshakeSecurityProtocol_;
        }

        public Builder setHandshakeSecurityProtocolValue(int i) {
            this.handshakeSecurityProtocol_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public HandshakeProtocol getHandshakeSecurityProtocol() {
            HandshakeProtocol valueOf = HandshakeProtocol.valueOf(this.handshakeSecurityProtocol_);
            return valueOf == null ? HandshakeProtocol.UNRECOGNIZED : valueOf;
        }

        public Builder setHandshakeSecurityProtocol(HandshakeProtocol handshakeProtocol) {
            if (handshakeProtocol == null) {
                throw new NullPointerException();
            }
            this.handshakeSecurityProtocol_ = handshakeProtocol.getNumber();
            onChanged();
            return this;
        }

        public Builder clearHandshakeSecurityProtocol() {
            this.handshakeSecurityProtocol_ = 0;
            onChanged();
            return this;
        }

        private void ensureApplicationProtocolsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.applicationProtocols_ = new LazyStringArrayList(this.applicationProtocols_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public ProtocolStringList getApplicationProtocolsList() {
            return this.applicationProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getApplicationProtocolsCount() {
            return this.applicationProtocols_.size();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public String getApplicationProtocols(int i) {
            return (String) this.applicationProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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
                StartClientHandshakeReq.checkByteStringIsUtf8(byteString);
                ensureApplicationProtocolsIsMutable();
                this.applicationProtocols_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureRecordProtocolsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.recordProtocols_ = new LazyStringArrayList(this.recordProtocols_);
                this.bitField0_ |= 2;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public ProtocolStringList getRecordProtocolsList() {
            return this.recordProtocols_.getUnmodifiableView();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getRecordProtocolsCount() {
            return this.recordProtocols_.size();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public String getRecordProtocols(int i) {
            return (String) this.recordProtocols_.get(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addRecordProtocolsBytes(ByteString byteString) {
            if (byteString != null) {
                StartClientHandshakeReq.checkByteStringIsUtf8(byteString);
                ensureRecordProtocolsIsMutable();
                this.recordProtocols_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureTargetIdentitiesIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.targetIdentities_ = new ArrayList(this.targetIdentities_);
                this.bitField0_ |= 4;
            }
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public List<Identity> getTargetIdentitiesList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.targetIdentities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public int getTargetIdentitiesCount() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.targetIdentities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public Identity getTargetIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.targetIdentities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTargetIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.set(i, identity);
                onChanged();
            }
            return this;
        }

        public Builder setTargetIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addTargetIdentities(Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(identity);
                onChanged();
            }
            return this;
        }

        public Builder addTargetIdentities(int i, Identity identity) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, identity);
            } else {
                if (identity == null) {
                    throw new NullPointerException();
                }
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(i, identity);
                onChanged();
            }
            return this;
        }

        public Builder addTargetIdentities(Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addTargetIdentities(int i, Identity.Builder builder) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllTargetIdentities(Iterable<? extends Identity> iterable) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.targetIdentities_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTargetIdentities() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.targetIdentities_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTargetIdentities(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTargetIdentitiesIsMutable();
                this.targetIdentities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Identity.Builder getTargetIdentitiesBuilder(int i) {
            return getTargetIdentitiesFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public IdentityOrBuilder getTargetIdentitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.targetIdentities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public List<? extends IdentityOrBuilder> getTargetIdentitiesOrBuilderList() {
            RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> repeatedFieldBuilderV3 = this.targetIdentitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.targetIdentities_);
        }

        public Identity.Builder addTargetIdentitiesBuilder() {
            return getTargetIdentitiesFieldBuilder().addBuilder(Identity.getDefaultInstance());
        }

        public Identity.Builder addTargetIdentitiesBuilder(int i) {
            return getTargetIdentitiesFieldBuilder().addBuilder(i, Identity.getDefaultInstance());
        }

        public List<Identity.Builder> getTargetIdentitiesBuilderList() {
            return getTargetIdentitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Identity, Identity.Builder, IdentityOrBuilder> getTargetIdentitiesFieldBuilder() {
            if (this.targetIdentitiesBuilder_ == null) {
                this.targetIdentitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.targetIdentities_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                this.targetIdentities_ = null;
            }
            return this.targetIdentitiesBuilder_;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasLocalIdentity() {
            return (this.localIdentityBuilder_ == null && this.localIdentity_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasLocalEndpoint() {
            return (this.localEndpointBuilder_ == null && this.localEndpoint_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasRemoteEndpoint() {
            return (this.remoteEndpointBuilder_ == null && this.remoteEndpoint_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public String getTargetName() {
            Object obj = this.targetName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.targetName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public ByteString getTargetNameBytes() {
            Object obj = this.targetName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTargetName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.targetName_ = str;
            onChanged();
            return this;
        }

        public Builder clearTargetName() {
            this.targetName_ = StartClientHandshakeReq.getDefaultInstance().getTargetName();
            onChanged();
            return this;
        }

        public Builder setTargetNameBytes(ByteString byteString) {
            if (byteString != null) {
                StartClientHandshakeReq.checkByteStringIsUtf8(byteString);
                this.targetName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
        public boolean hasRpcVersions() {
            return (this.rpcVersionsBuilder_ == null && this.rpcVersions_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

        @Override // io.grpc.alts.internal.StartClientHandshakeReqOrBuilder
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

    public static StartClientHandshakeReq getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StartClientHandshakeReq> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<StartClientHandshakeReq> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public StartClientHandshakeReq getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
