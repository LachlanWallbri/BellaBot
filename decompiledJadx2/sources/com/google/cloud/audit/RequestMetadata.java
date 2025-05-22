package com.google.cloud.audit;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.rpc.context.AttributeContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class RequestMetadata extends GeneratedMessageV3 implements RequestMetadataOrBuilder {
    public static final int CALLER_IP_FIELD_NUMBER = 1;
    public static final int CALLER_NETWORK_FIELD_NUMBER = 3;
    public static final int CALLER_SUPPLIED_USER_AGENT_FIELD_NUMBER = 2;
    public static final int DESTINATION_ATTRIBUTES_FIELD_NUMBER = 8;
    public static final int REQUEST_ATTRIBUTES_FIELD_NUMBER = 7;
    private static final long serialVersionUID = 0;
    private volatile Object callerIp_;
    private volatile Object callerNetwork_;
    private volatile Object callerSuppliedUserAgent_;
    private AttributeContext.Peer destinationAttributes_;
    private byte memoizedIsInitialized;
    private AttributeContext.Request requestAttributes_;
    private static final RequestMetadata DEFAULT_INSTANCE = new RequestMetadata();
    private static final Parser<RequestMetadata> PARSER = new AbstractParser<RequestMetadata>() { // from class: com.google.cloud.audit.RequestMetadata.1
        @Override // com.google.protobuf.Parser
        public RequestMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RequestMetadata(codedInputStream, extensionRegistryLite);
        }
    };

    private RequestMetadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RequestMetadata() {
        this.memoizedIsInitialized = (byte) -1;
        this.callerIp_ = "";
        this.callerSuppliedUserAgent_ = "";
        this.callerNetwork_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new RequestMetadata();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RequestMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.callerIp_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.callerSuppliedUserAgent_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 26) {
                                if (readTag == 58) {
                                    AttributeContext.Request.Builder builder = this.requestAttributes_ != null ? this.requestAttributes_.toBuilder() : null;
                                    this.requestAttributes_ = (AttributeContext.Request) codedInputStream.readMessage(AttributeContext.Request.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.requestAttributes_);
                                        this.requestAttributes_ = builder.buildPartial();
                                    }
                                } else if (readTag == 66) {
                                    AttributeContext.Peer.Builder builder2 = this.destinationAttributes_ != null ? this.destinationAttributes_.toBuilder() : null;
                                    this.destinationAttributes_ = (AttributeContext.Peer) codedInputStream.readMessage(AttributeContext.Peer.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.destinationAttributes_);
                                        this.destinationAttributes_ = builder2.buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.callerNetwork_ = codedInputStream.readStringRequireUtf8();
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
        return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.f1352xfa976db3.ensureFieldAccessorsInitialized(RequestMetadata.class, Builder.class);
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public String getCallerIp() {
        Object obj = this.callerIp_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.callerIp_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public ByteString getCallerIpBytes() {
        Object obj = this.callerIp_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.callerIp_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public String getCallerSuppliedUserAgent() {
        Object obj = this.callerSuppliedUserAgent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.callerSuppliedUserAgent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public ByteString getCallerSuppliedUserAgentBytes() {
        Object obj = this.callerSuppliedUserAgent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.callerSuppliedUserAgent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public String getCallerNetwork() {
        Object obj = this.callerNetwork_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.callerNetwork_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public ByteString getCallerNetworkBytes() {
        Object obj = this.callerNetwork_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.callerNetwork_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public boolean hasRequestAttributes() {
        return this.requestAttributes_ != null;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public AttributeContext.Request getRequestAttributes() {
        AttributeContext.Request request = this.requestAttributes_;
        return request == null ? AttributeContext.Request.getDefaultInstance() : request;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public AttributeContext.RequestOrBuilder getRequestAttributesOrBuilder() {
        return getRequestAttributes();
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public boolean hasDestinationAttributes() {
        return this.destinationAttributes_ != null;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public AttributeContext.Peer getDestinationAttributes() {
        AttributeContext.Peer peer = this.destinationAttributes_;
        return peer == null ? AttributeContext.Peer.getDefaultInstance() : peer;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public AttributeContext.PeerOrBuilder getDestinationAttributesOrBuilder() {
        return getDestinationAttributes();
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
        if (!getCallerIpBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.callerIp_);
        }
        if (!getCallerSuppliedUserAgentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.callerSuppliedUserAgent_);
        }
        if (!getCallerNetworkBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.callerNetwork_);
        }
        if (this.requestAttributes_ != null) {
            codedOutputStream.writeMessage(7, getRequestAttributes());
        }
        if (this.destinationAttributes_ != null) {
            codedOutputStream.writeMessage(8, getDestinationAttributes());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getCallerIpBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.callerIp_);
        if (!getCallerSuppliedUserAgentBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.callerSuppliedUserAgent_);
        }
        if (!getCallerNetworkBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.callerNetwork_);
        }
        if (this.requestAttributes_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(7, getRequestAttributes());
        }
        if (this.destinationAttributes_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(8, getDestinationAttributes());
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
        if (!(obj instanceof RequestMetadata)) {
            return super.equals(obj);
        }
        RequestMetadata requestMetadata = (RequestMetadata) obj;
        if (!getCallerIp().equals(requestMetadata.getCallerIp()) || !getCallerSuppliedUserAgent().equals(requestMetadata.getCallerSuppliedUserAgent()) || !getCallerNetwork().equals(requestMetadata.getCallerNetwork()) || hasRequestAttributes() != requestMetadata.hasRequestAttributes()) {
            return false;
        }
        if ((!hasRequestAttributes() || getRequestAttributes().equals(requestMetadata.getRequestAttributes())) && hasDestinationAttributes() == requestMetadata.hasDestinationAttributes()) {
            return (!hasDestinationAttributes() || getDestinationAttributes().equals(requestMetadata.getDestinationAttributes())) && this.unknownFields.equals(requestMetadata.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCallerIp().hashCode()) * 37) + 2) * 53) + getCallerSuppliedUserAgent().hashCode()) * 37) + 3) * 53) + getCallerNetwork().hashCode();
        if (hasRequestAttributes()) {
            hashCode = (((hashCode * 37) + 7) * 53) + getRequestAttributes().hashCode();
        }
        if (hasDestinationAttributes()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getDestinationAttributes().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static RequestMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static RequestMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static RequestMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static RequestMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(InputStream inputStream) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RequestMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RequestMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RequestMetadata requestMetadata) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(requestMetadata);
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
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RequestMetadataOrBuilder {
        private Object callerIp_;
        private Object callerNetwork_;
        private Object callerSuppliedUserAgent_;
        private SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> destinationAttributesBuilder_;
        private AttributeContext.Peer destinationAttributes_;
        private SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> requestAttributesBuilder_;
        private AttributeContext.Request requestAttributes_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.f1352xfa976db3.ensureFieldAccessorsInitialized(RequestMetadata.class, Builder.class);
        }

        private Builder() {
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            this.callerNetwork_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            this.callerNetwork_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RequestMetadata.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.callerIp_ = "";
            this.callerSuppliedUserAgent_ = "";
            this.callerNetwork_ = "";
            if (this.requestAttributesBuilder_ == null) {
                this.requestAttributes_ = null;
            } else {
                this.requestAttributes_ = null;
                this.requestAttributesBuilder_ = null;
            }
            if (this.destinationAttributesBuilder_ == null) {
                this.destinationAttributes_ = null;
            } else {
                this.destinationAttributes_ = null;
                this.destinationAttributesBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_RequestMetadata_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RequestMetadata getDefaultInstanceForType() {
            return RequestMetadata.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RequestMetadata build() {
            RequestMetadata buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RequestMetadata buildPartial() {
            RequestMetadata requestMetadata = new RequestMetadata(this);
            requestMetadata.callerIp_ = this.callerIp_;
            requestMetadata.callerSuppliedUserAgent_ = this.callerSuppliedUserAgent_;
            requestMetadata.callerNetwork_ = this.callerNetwork_;
            SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> singleFieldBuilderV3 = this.requestAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                requestMetadata.requestAttributes_ = this.requestAttributes_;
            } else {
                requestMetadata.requestAttributes_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> singleFieldBuilderV32 = this.destinationAttributesBuilder_;
            if (singleFieldBuilderV32 == null) {
                requestMetadata.destinationAttributes_ = this.destinationAttributes_;
            } else {
                requestMetadata.destinationAttributes_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return requestMetadata;
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
            if (message instanceof RequestMetadata) {
                return mergeFrom((RequestMetadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RequestMetadata requestMetadata) {
            if (requestMetadata == RequestMetadata.getDefaultInstance()) {
                return this;
            }
            if (!requestMetadata.getCallerIp().isEmpty()) {
                this.callerIp_ = requestMetadata.callerIp_;
                onChanged();
            }
            if (!requestMetadata.getCallerSuppliedUserAgent().isEmpty()) {
                this.callerSuppliedUserAgent_ = requestMetadata.callerSuppliedUserAgent_;
                onChanged();
            }
            if (!requestMetadata.getCallerNetwork().isEmpty()) {
                this.callerNetwork_ = requestMetadata.callerNetwork_;
                onChanged();
            }
            if (requestMetadata.hasRequestAttributes()) {
                mergeRequestAttributes(requestMetadata.getRequestAttributes());
            }
            if (requestMetadata.hasDestinationAttributes()) {
                mergeDestinationAttributes(requestMetadata.getDestinationAttributes());
            }
            mergeUnknownFields(requestMetadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            RequestMetadata requestMetadata = null;
            try {
                try {
                    RequestMetadata requestMetadata2 = (RequestMetadata) RequestMetadata.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (requestMetadata2 != null) {
                        mergeFrom(requestMetadata2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    RequestMetadata requestMetadata3 = (RequestMetadata) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        requestMetadata = requestMetadata3;
                        if (requestMetadata != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (requestMetadata != null) {
                    mergeFrom(requestMetadata);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public String getCallerIp() {
            Object obj = this.callerIp_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.callerIp_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public ByteString getCallerIpBytes() {
            Object obj = this.callerIp_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.callerIp_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCallerIp(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.callerIp_ = str;
            onChanged();
            return this;
        }

        public Builder clearCallerIp() {
            this.callerIp_ = RequestMetadata.getDefaultInstance().getCallerIp();
            onChanged();
            return this;
        }

        public Builder setCallerIpBytes(ByteString byteString) {
            if (byteString != null) {
                RequestMetadata.checkByteStringIsUtf8(byteString);
                this.callerIp_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public String getCallerSuppliedUserAgent() {
            Object obj = this.callerSuppliedUserAgent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.callerSuppliedUserAgent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public ByteString getCallerSuppliedUserAgentBytes() {
            Object obj = this.callerSuppliedUserAgent_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.callerSuppliedUserAgent_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCallerSuppliedUserAgent(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.callerSuppliedUserAgent_ = str;
            onChanged();
            return this;
        }

        public Builder clearCallerSuppliedUserAgent() {
            this.callerSuppliedUserAgent_ = RequestMetadata.getDefaultInstance().getCallerSuppliedUserAgent();
            onChanged();
            return this;
        }

        public Builder setCallerSuppliedUserAgentBytes(ByteString byteString) {
            if (byteString != null) {
                RequestMetadata.checkByteStringIsUtf8(byteString);
                this.callerSuppliedUserAgent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public String getCallerNetwork() {
            Object obj = this.callerNetwork_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.callerNetwork_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public ByteString getCallerNetworkBytes() {
            Object obj = this.callerNetwork_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.callerNetwork_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setCallerNetwork(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.callerNetwork_ = str;
            onChanged();
            return this;
        }

        public Builder clearCallerNetwork() {
            this.callerNetwork_ = RequestMetadata.getDefaultInstance().getCallerNetwork();
            onChanged();
            return this;
        }

        public Builder setCallerNetworkBytes(ByteString byteString) {
            if (byteString != null) {
                RequestMetadata.checkByteStringIsUtf8(byteString);
                this.callerNetwork_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public boolean hasRequestAttributes() {
            return (this.requestAttributesBuilder_ == null && this.requestAttributes_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public AttributeContext.Request getRequestAttributes() {
            SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> singleFieldBuilderV3 = this.requestAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                AttributeContext.Request request = this.requestAttributes_;
                return request == null ? AttributeContext.Request.getDefaultInstance() : request;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRequestAttributes(AttributeContext.Request request) {
            SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> singleFieldBuilderV3 = this.requestAttributesBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(request);
            } else {
                if (request == null) {
                    throw new NullPointerException();
                }
                this.requestAttributes_ = request;
                onChanged();
            }
            return this;
        }

        public Builder setRequestAttributes(AttributeContext.Request.Builder builder) {
            SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> singleFieldBuilderV3 = this.requestAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.requestAttributes_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeRequestAttributes(AttributeContext.Request request) {
            SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> singleFieldBuilderV3 = this.requestAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                AttributeContext.Request request2 = this.requestAttributes_;
                if (request2 != null) {
                    this.requestAttributes_ = AttributeContext.Request.newBuilder(request2).mergeFrom(request).buildPartial();
                } else {
                    this.requestAttributes_ = request;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(request);
            }
            return this;
        }

        public Builder clearRequestAttributes() {
            if (this.requestAttributesBuilder_ == null) {
                this.requestAttributes_ = null;
                onChanged();
            } else {
                this.requestAttributes_ = null;
                this.requestAttributesBuilder_ = null;
            }
            return this;
        }

        public AttributeContext.Request.Builder getRequestAttributesBuilder() {
            onChanged();
            return getRequestAttributesFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public AttributeContext.RequestOrBuilder getRequestAttributesOrBuilder() {
            SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> singleFieldBuilderV3 = this.requestAttributesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            AttributeContext.Request request = this.requestAttributes_;
            return request == null ? AttributeContext.Request.getDefaultInstance() : request;
        }

        private SingleFieldBuilderV3<AttributeContext.Request, AttributeContext.Request.Builder, AttributeContext.RequestOrBuilder> getRequestAttributesFieldBuilder() {
            if (this.requestAttributesBuilder_ == null) {
                this.requestAttributesBuilder_ = new SingleFieldBuilderV3<>(getRequestAttributes(), getParentForChildren(), isClean());
                this.requestAttributes_ = null;
            }
            return this.requestAttributesBuilder_;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public boolean hasDestinationAttributes() {
            return (this.destinationAttributesBuilder_ == null && this.destinationAttributes_ == null) ? false : true;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public AttributeContext.Peer getDestinationAttributes() {
            SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> singleFieldBuilderV3 = this.destinationAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                AttributeContext.Peer peer = this.destinationAttributes_;
                return peer == null ? AttributeContext.Peer.getDefaultInstance() : peer;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setDestinationAttributes(AttributeContext.Peer peer) {
            SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> singleFieldBuilderV3 = this.destinationAttributesBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(peer);
            } else {
                if (peer == null) {
                    throw new NullPointerException();
                }
                this.destinationAttributes_ = peer;
                onChanged();
            }
            return this;
        }

        public Builder setDestinationAttributes(AttributeContext.Peer.Builder builder) {
            SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> singleFieldBuilderV3 = this.destinationAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.destinationAttributes_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDestinationAttributes(AttributeContext.Peer peer) {
            SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> singleFieldBuilderV3 = this.destinationAttributesBuilder_;
            if (singleFieldBuilderV3 == null) {
                AttributeContext.Peer peer2 = this.destinationAttributes_;
                if (peer2 != null) {
                    this.destinationAttributes_ = AttributeContext.Peer.newBuilder(peer2).mergeFrom(peer).buildPartial();
                } else {
                    this.destinationAttributes_ = peer;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(peer);
            }
            return this;
        }

        public Builder clearDestinationAttributes() {
            if (this.destinationAttributesBuilder_ == null) {
                this.destinationAttributes_ = null;
                onChanged();
            } else {
                this.destinationAttributes_ = null;
                this.destinationAttributesBuilder_ = null;
            }
            return this;
        }

        public AttributeContext.Peer.Builder getDestinationAttributesBuilder() {
            onChanged();
            return getDestinationAttributesFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public AttributeContext.PeerOrBuilder getDestinationAttributesOrBuilder() {
            SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> singleFieldBuilderV3 = this.destinationAttributesBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            AttributeContext.Peer peer = this.destinationAttributes_;
            return peer == null ? AttributeContext.Peer.getDefaultInstance() : peer;
        }

        private SingleFieldBuilderV3<AttributeContext.Peer, AttributeContext.Peer.Builder, AttributeContext.PeerOrBuilder> getDestinationAttributesFieldBuilder() {
            if (this.destinationAttributesBuilder_ == null) {
                this.destinationAttributesBuilder_ = new SingleFieldBuilderV3<>(getDestinationAttributes(), getParentForChildren(), isClean());
                this.destinationAttributes_ = null;
            }
            return this.destinationAttributesBuilder_;
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

    public static RequestMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestMetadata> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<RequestMetadata> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public RequestMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
