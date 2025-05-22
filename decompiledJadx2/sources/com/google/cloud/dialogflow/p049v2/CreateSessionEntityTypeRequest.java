package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.SessionEntityType;
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
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class CreateSessionEntityTypeRequest extends GeneratedMessageV3 implements CreateSessionEntityTypeRequestOrBuilder {
    public static final int PARENT_FIELD_NUMBER = 1;
    public static final int SESSION_ENTITY_TYPE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private SessionEntityType sessionEntityType_;
    private static final CreateSessionEntityTypeRequest DEFAULT_INSTANCE = new CreateSessionEntityTypeRequest();
    private static final Parser<CreateSessionEntityTypeRequest> PARSER = new AbstractParser<CreateSessionEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2.CreateSessionEntityTypeRequest.1
        @Override // com.google.protobuf.Parser
        public CreateSessionEntityTypeRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CreateSessionEntityTypeRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private CreateSessionEntityTypeRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CreateSessionEntityTypeRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new CreateSessionEntityTypeRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateSessionEntityTypeRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.parent_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                SessionEntityType.Builder builder = this.sessionEntityType_ != null ? this.sessionEntityType_.toBuilder() : null;
                                this.sessionEntityType_ = (SessionEntityType) codedInputStream.readMessage(SessionEntityType.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.sessionEntityType_);
                                    this.sessionEntityType_ = builder.buildPartial();
                                }
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
        return SessionEntityTypeProto.f1532x9179b0fb;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionEntityTypeProto.f1533x9751ac79.ensureFieldAccessorsInitialized(CreateSessionEntityTypeRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
    public boolean hasSessionEntityType() {
        return this.sessionEntityType_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
    public SessionEntityType getSessionEntityType() {
        SessionEntityType sessionEntityType = this.sessionEntityType_;
        return sessionEntityType == null ? SessionEntityType.getDefaultInstance() : sessionEntityType;
    }

    @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
    public SessionEntityTypeOrBuilder getSessionEntityTypeOrBuilder() {
        return getSessionEntityType();
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
        if (!getParentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.parent_);
        }
        if (this.sessionEntityType_ != null) {
            codedOutputStream.writeMessage(2, getSessionEntityType());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getParentBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.parent_);
        if (this.sessionEntityType_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getSessionEntityType());
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
        if (!(obj instanceof CreateSessionEntityTypeRequest)) {
            return super.equals(obj);
        }
        CreateSessionEntityTypeRequest createSessionEntityTypeRequest = (CreateSessionEntityTypeRequest) obj;
        if (getParent().equals(createSessionEntityTypeRequest.getParent()) && hasSessionEntityType() == createSessionEntityTypeRequest.hasSessionEntityType()) {
            return (!hasSessionEntityType() || getSessionEntityType().equals(createSessionEntityTypeRequest.getSessionEntityType())) && this.unknownFields.equals(createSessionEntityTypeRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (hasSessionEntityType()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getSessionEntityType().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static CreateSessionEntityTypeRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static CreateSessionEntityTypeRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CreateSessionEntityTypeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static CreateSessionEntityTypeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CreateSessionEntityTypeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static CreateSessionEntityTypeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CreateSessionEntityTypeRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CreateSessionEntityTypeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateSessionEntityTypeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateSessionEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CreateSessionEntityTypeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateSessionEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateSessionEntityTypeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CreateSessionEntityTypeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateSessionEntityTypeRequest createSessionEntityTypeRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(createSessionEntityTypeRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateSessionEntityTypeRequestOrBuilder {
        private Object parent_;
        private SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> sessionEntityTypeBuilder_;
        private SessionEntityType sessionEntityType_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionEntityTypeProto.f1532x9179b0fb;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionEntityTypeProto.f1533x9751ac79.ensureFieldAccessorsInitialized(CreateSessionEntityTypeRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = CreateSessionEntityTypeRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            if (this.sessionEntityTypeBuilder_ == null) {
                this.sessionEntityType_ = null;
            } else {
                this.sessionEntityType_ = null;
                this.sessionEntityTypeBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionEntityTypeProto.f1532x9179b0fb;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CreateSessionEntityTypeRequest getDefaultInstanceForType() {
            return CreateSessionEntityTypeRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateSessionEntityTypeRequest build() {
            CreateSessionEntityTypeRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateSessionEntityTypeRequest buildPartial() {
            CreateSessionEntityTypeRequest createSessionEntityTypeRequest = new CreateSessionEntityTypeRequest(this);
            createSessionEntityTypeRequest.parent_ = this.parent_;
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                createSessionEntityTypeRequest.sessionEntityType_ = this.sessionEntityType_;
            } else {
                createSessionEntityTypeRequest.sessionEntityType_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return createSessionEntityTypeRequest;
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
            if (message instanceof CreateSessionEntityTypeRequest) {
                return mergeFrom((CreateSessionEntityTypeRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CreateSessionEntityTypeRequest createSessionEntityTypeRequest) {
            if (createSessionEntityTypeRequest == CreateSessionEntityTypeRequest.getDefaultInstance()) {
                return this;
            }
            if (!createSessionEntityTypeRequest.getParent().isEmpty()) {
                this.parent_ = createSessionEntityTypeRequest.parent_;
                onChanged();
            }
            if (createSessionEntityTypeRequest.hasSessionEntityType()) {
                mergeSessionEntityType(createSessionEntityTypeRequest.getSessionEntityType());
            }
            mergeUnknownFields(createSessionEntityTypeRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CreateSessionEntityTypeRequest createSessionEntityTypeRequest = null;
            try {
                try {
                    CreateSessionEntityTypeRequest createSessionEntityTypeRequest2 = (CreateSessionEntityTypeRequest) CreateSessionEntityTypeRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (createSessionEntityTypeRequest2 != null) {
                        mergeFrom(createSessionEntityTypeRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CreateSessionEntityTypeRequest createSessionEntityTypeRequest3 = (CreateSessionEntityTypeRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        createSessionEntityTypeRequest = createSessionEntityTypeRequest3;
                        if (createSessionEntityTypeRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (createSessionEntityTypeRequest != null) {
                    mergeFrom(createSessionEntityTypeRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
        public ByteString getParentBytes() {
            Object obj = this.parent_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.parent_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setParent(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.parent_ = str;
            onChanged();
            return this;
        }

        public Builder clearParent() {
            this.parent_ = CreateSessionEntityTypeRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                CreateSessionEntityTypeRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
        public boolean hasSessionEntityType() {
            return (this.sessionEntityTypeBuilder_ == null && this.sessionEntityType_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
        public SessionEntityType getSessionEntityType() {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                SessionEntityType sessionEntityType = this.sessionEntityType_;
                return sessionEntityType == null ? SessionEntityType.getDefaultInstance() : sessionEntityType;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSessionEntityType(SessionEntityType sessionEntityType) {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                this.sessionEntityType_ = sessionEntityType;
                onChanged();
            }
            return this;
        }

        public Builder setSessionEntityType(SessionEntityType.Builder builder) {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sessionEntityType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSessionEntityType(SessionEntityType sessionEntityType) {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                SessionEntityType sessionEntityType2 = this.sessionEntityType_;
                if (sessionEntityType2 != null) {
                    this.sessionEntityType_ = SessionEntityType.newBuilder(sessionEntityType2).mergeFrom(sessionEntityType).buildPartial();
                } else {
                    this.sessionEntityType_ = sessionEntityType;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sessionEntityType);
            }
            return this;
        }

        public Builder clearSessionEntityType() {
            if (this.sessionEntityTypeBuilder_ == null) {
                this.sessionEntityType_ = null;
                onChanged();
            } else {
                this.sessionEntityType_ = null;
                this.sessionEntityTypeBuilder_ = null;
            }
            return this;
        }

        public SessionEntityType.Builder getSessionEntityTypeBuilder() {
            onChanged();
            return getSessionEntityTypeFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.CreateSessionEntityTypeRequestOrBuilder
        public SessionEntityTypeOrBuilder getSessionEntityTypeOrBuilder() {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SessionEntityType sessionEntityType = this.sessionEntityType_;
            return sessionEntityType == null ? SessionEntityType.getDefaultInstance() : sessionEntityType;
        }

        private SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> getSessionEntityTypeFieldBuilder() {
            if (this.sessionEntityTypeBuilder_ == null) {
                this.sessionEntityTypeBuilder_ = new SingleFieldBuilderV3<>(getSessionEntityType(), getParentForChildren(), isClean());
                this.sessionEntityType_ = null;
            }
            return this.sessionEntityTypeBuilder_;
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

    public static CreateSessionEntityTypeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateSessionEntityTypeRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<CreateSessionEntityTypeRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public CreateSessionEntityTypeRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
