package com.google.cloud.dialogflow.p049v2;

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
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class OriginalDetectIntentRequest extends GeneratedMessageV3 implements OriginalDetectIntentRequestOrBuilder {
    private static final OriginalDetectIntentRequest DEFAULT_INSTANCE = new OriginalDetectIntentRequest();
    private static final Parser<OriginalDetectIntentRequest> PARSER = new AbstractParser<OriginalDetectIntentRequest>() { // from class: com.google.cloud.dialogflow.v2.OriginalDetectIntentRequest.1
        @Override // com.google.protobuf.Parser
        public OriginalDetectIntentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new OriginalDetectIntentRequest(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PAYLOAD_FIELD_NUMBER = 3;
    public static final int SOURCE_FIELD_NUMBER = 1;
    public static final int VERSION_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private Struct payload_;
    private volatile Object source_;
    private volatile Object version_;

    private OriginalDetectIntentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private OriginalDetectIntentRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.source_ = "";
        this.version_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new OriginalDetectIntentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private OriginalDetectIntentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.source_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.version_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
                            Struct.Builder builder = this.payload_ != null ? this.payload_.toBuilder() : null;
                            this.payload_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.payload_);
                                this.payload_ = builder.buildPartial();
                            }
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
        return WebhookProto.f1572x53a1a2ca;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebhookProto.f1573x924e5f48.ensureFieldAccessorsInitialized(OriginalDetectIntentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public String getSource() {
        Object obj = this.source_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.source_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public ByteString getSourceBytes() {
        Object obj = this.source_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.source_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public String getVersion() {
        Object obj = this.version_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.version_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public ByteString getVersionBytes() {
        Object obj = this.version_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.version_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public boolean hasPayload() {
        return this.payload_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public Struct getPayload() {
        Struct struct = this.payload_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
    public StructOrBuilder getPayloadOrBuilder() {
        return getPayload();
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
        if (!getSourceBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.source_);
        }
        if (!getVersionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.version_);
        }
        if (this.payload_ != null) {
            codedOutputStream.writeMessage(3, getPayload());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getSourceBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.source_);
        if (!getVersionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.version_);
        }
        if (this.payload_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, getPayload());
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
        if (!(obj instanceof OriginalDetectIntentRequest)) {
            return super.equals(obj);
        }
        OriginalDetectIntentRequest originalDetectIntentRequest = (OriginalDetectIntentRequest) obj;
        if (getSource().equals(originalDetectIntentRequest.getSource()) && getVersion().equals(originalDetectIntentRequest.getVersion()) && hasPayload() == originalDetectIntentRequest.hasPayload()) {
            return (!hasPayload() || getPayload().equals(originalDetectIntentRequest.getPayload())) && this.unknownFields.equals(originalDetectIntentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSource().hashCode()) * 37) + 2) * 53) + getVersion().hashCode();
        if (hasPayload()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getPayload().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static OriginalDetectIntentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static OriginalDetectIntentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static OriginalDetectIntentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static OriginalDetectIntentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static OriginalDetectIntentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static OriginalDetectIntentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static OriginalDetectIntentRequest parseFrom(InputStream inputStream) throws IOException {
        return (OriginalDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static OriginalDetectIntentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (OriginalDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OriginalDetectIntentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (OriginalDetectIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static OriginalDetectIntentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (OriginalDetectIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OriginalDetectIntentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (OriginalDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static OriginalDetectIntentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (OriginalDetectIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(OriginalDetectIntentRequest originalDetectIntentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(originalDetectIntentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OriginalDetectIntentRequestOrBuilder {
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> payloadBuilder_;
        private Struct payload_;
        private Object source_;
        private Object version_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WebhookProto.f1572x53a1a2ca;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WebhookProto.f1573x924e5f48.ensureFieldAccessorsInitialized(OriginalDetectIntentRequest.class, Builder.class);
        }

        private Builder() {
            this.source_ = "";
            this.version_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.source_ = "";
            this.version_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = OriginalDetectIntentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.source_ = "";
            this.version_ = "";
            if (this.payloadBuilder_ == null) {
                this.payload_ = null;
            } else {
                this.payload_ = null;
                this.payloadBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return WebhookProto.f1572x53a1a2ca;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public OriginalDetectIntentRequest getDefaultInstanceForType() {
            return OriginalDetectIntentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public OriginalDetectIntentRequest build() {
            OriginalDetectIntentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public OriginalDetectIntentRequest buildPartial() {
            OriginalDetectIntentRequest originalDetectIntentRequest = new OriginalDetectIntentRequest(this);
            originalDetectIntentRequest.source_ = this.source_;
            originalDetectIntentRequest.version_ = this.version_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                originalDetectIntentRequest.payload_ = this.payload_;
            } else {
                originalDetectIntentRequest.payload_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return originalDetectIntentRequest;
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
            if (message instanceof OriginalDetectIntentRequest) {
                return mergeFrom((OriginalDetectIntentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(OriginalDetectIntentRequest originalDetectIntentRequest) {
            if (originalDetectIntentRequest == OriginalDetectIntentRequest.getDefaultInstance()) {
                return this;
            }
            if (!originalDetectIntentRequest.getSource().isEmpty()) {
                this.source_ = originalDetectIntentRequest.source_;
                onChanged();
            }
            if (!originalDetectIntentRequest.getVersion().isEmpty()) {
                this.version_ = originalDetectIntentRequest.version_;
                onChanged();
            }
            if (originalDetectIntentRequest.hasPayload()) {
                mergePayload(originalDetectIntentRequest.getPayload());
            }
            mergeUnknownFields(originalDetectIntentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            OriginalDetectIntentRequest originalDetectIntentRequest = null;
            try {
                try {
                    OriginalDetectIntentRequest originalDetectIntentRequest2 = (OriginalDetectIntentRequest) OriginalDetectIntentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (originalDetectIntentRequest2 != null) {
                        mergeFrom(originalDetectIntentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    OriginalDetectIntentRequest originalDetectIntentRequest3 = (OriginalDetectIntentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        originalDetectIntentRequest = originalDetectIntentRequest3;
                        if (originalDetectIntentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (originalDetectIntentRequest != null) {
                    mergeFrom(originalDetectIntentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public String getSource() {
            Object obj = this.source_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.source_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public ByteString getSourceBytes() {
            Object obj = this.source_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.source_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSource(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.source_ = str;
            onChanged();
            return this;
        }

        public Builder clearSource() {
            this.source_ = OriginalDetectIntentRequest.getDefaultInstance().getSource();
            onChanged();
            return this;
        }

        public Builder setSourceBytes(ByteString byteString) {
            if (byteString != null) {
                OriginalDetectIntentRequest.checkByteStringIsUtf8(byteString);
                this.source_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public String getVersion() {
            Object obj = this.version_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.version_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public ByteString getVersionBytes() {
            Object obj = this.version_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.version_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setVersion(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.version_ = str;
            onChanged();
            return this;
        }

        public Builder clearVersion() {
            this.version_ = OriginalDetectIntentRequest.getDefaultInstance().getVersion();
            onChanged();
            return this;
        }

        public Builder setVersionBytes(ByteString byteString) {
            if (byteString != null) {
                OriginalDetectIntentRequest.checkByteStringIsUtf8(byteString);
                this.version_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public boolean hasPayload() {
            return (this.payloadBuilder_ == null && this.payload_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public Struct getPayload() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.payload_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setPayload(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.payload_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setPayload(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.payload_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergePayload(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.payload_;
                if (struct2 != null) {
                    this.payload_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.payload_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearPayload() {
            if (this.payloadBuilder_ == null) {
                this.payload_ = null;
                onChanged();
            } else {
                this.payload_ = null;
                this.payloadBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getPayloadBuilder() {
            onChanged();
            return getPayloadFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.OriginalDetectIntentRequestOrBuilder
        public StructOrBuilder getPayloadOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.payloadBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.payload_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getPayloadFieldBuilder() {
            if (this.payloadBuilder_ == null) {
                this.payloadBuilder_ = new SingleFieldBuilderV3<>(getPayload(), getParentForChildren(), isClean());
                this.payload_ = null;
            }
            return this.payloadBuilder_;
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

    public static OriginalDetectIntentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<OriginalDetectIntentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<OriginalDetectIntentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public OriginalDetectIntentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
