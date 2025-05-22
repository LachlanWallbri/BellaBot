package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.EntityType;
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
/* loaded from: classes3.dex */
public final class CreateEntityTypeRequest extends GeneratedMessageV3 implements CreateEntityTypeRequestOrBuilder {
    public static final int ENTITY_TYPE_FIELD_NUMBER = 2;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private EntityType entityType_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final CreateEntityTypeRequest DEFAULT_INSTANCE = new CreateEntityTypeRequest();
    private static final Parser<CreateEntityTypeRequest> PARSER = new AbstractParser<CreateEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequest.1
        @Override // com.google.protobuf.Parser
        public CreateEntityTypeRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CreateEntityTypeRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private CreateEntityTypeRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CreateEntityTypeRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.languageCode_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new CreateEntityTypeRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateEntityTypeRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.parent_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            EntityType.Builder builder = this.entityType_ != null ? this.entityType_.toBuilder() : null;
                            this.entityType_ = (EntityType) codedInputStream.readMessage(EntityType.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.entityType_);
                                this.entityType_ = builder.buildPartial();
                            }
                        } else if (readTag == 26) {
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
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
        return EntityTypeProto.f1664x6ceb0c7a;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1665x532b18f8.ensureFieldAccessorsInitialized(CreateEntityTypeRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public boolean hasEntityType() {
        return this.entityType_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public EntityType getEntityType() {
        EntityType entityType = this.entityType_;
        return entityType == null ? EntityType.getDefaultInstance() : entityType;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public EntityTypeOrBuilder getEntityTypeOrBuilder() {
        return getEntityType();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
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
        if (this.entityType_ != null) {
            codedOutputStream.writeMessage(2, getEntityType());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
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
        if (this.entityType_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getEntityType());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
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
        if (!(obj instanceof CreateEntityTypeRequest)) {
            return super.equals(obj);
        }
        CreateEntityTypeRequest createEntityTypeRequest = (CreateEntityTypeRequest) obj;
        if (getParent().equals(createEntityTypeRequest.getParent()) && hasEntityType() == createEntityTypeRequest.hasEntityType()) {
            return (!hasEntityType() || getEntityType().equals(createEntityTypeRequest.getEntityType())) && getLanguageCode().equals(createEntityTypeRequest.getLanguageCode()) && this.unknownFields.equals(createEntityTypeRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (hasEntityType()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getEntityType().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 3) * 53) + getLanguageCode().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static CreateEntityTypeRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static CreateEntityTypeRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CreateEntityTypeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static CreateEntityTypeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CreateEntityTypeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static CreateEntityTypeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CreateEntityTypeRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CreateEntityTypeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateEntityTypeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CreateEntityTypeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateEntityTypeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CreateEntityTypeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateEntityTypeRequest createEntityTypeRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(createEntityTypeRequest);
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
    /* loaded from: classes3.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateEntityTypeRequestOrBuilder {
        private SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> entityTypeBuilder_;
        private EntityType entityType_;
        private Object languageCode_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1664x6ceb0c7a;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1665x532b18f8.ensureFieldAccessorsInitialized(CreateEntityTypeRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = CreateEntityTypeRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            if (this.entityTypeBuilder_ == null) {
                this.entityType_ = null;
            } else {
                this.entityType_ = null;
                this.entityTypeBuilder_ = null;
            }
            this.languageCode_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1664x6ceb0c7a;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CreateEntityTypeRequest getDefaultInstanceForType() {
            return CreateEntityTypeRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateEntityTypeRequest build() {
            CreateEntityTypeRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateEntityTypeRequest buildPartial() {
            CreateEntityTypeRequest createEntityTypeRequest = new CreateEntityTypeRequest(this);
            createEntityTypeRequest.parent_ = this.parent_;
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                createEntityTypeRequest.entityType_ = this.entityType_;
            } else {
                createEntityTypeRequest.entityType_ = singleFieldBuilderV3.build();
            }
            createEntityTypeRequest.languageCode_ = this.languageCode_;
            onBuilt();
            return createEntityTypeRequest;
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
            if (message instanceof CreateEntityTypeRequest) {
                return mergeFrom((CreateEntityTypeRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CreateEntityTypeRequest createEntityTypeRequest) {
            if (createEntityTypeRequest == CreateEntityTypeRequest.getDefaultInstance()) {
                return this;
            }
            if (!createEntityTypeRequest.getParent().isEmpty()) {
                this.parent_ = createEntityTypeRequest.parent_;
                onChanged();
            }
            if (createEntityTypeRequest.hasEntityType()) {
                mergeEntityType(createEntityTypeRequest.getEntityType());
            }
            if (!createEntityTypeRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = createEntityTypeRequest.languageCode_;
                onChanged();
            }
            mergeUnknownFields(createEntityTypeRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CreateEntityTypeRequest createEntityTypeRequest = null;
            try {
                try {
                    CreateEntityTypeRequest createEntityTypeRequest2 = (CreateEntityTypeRequest) CreateEntityTypeRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (createEntityTypeRequest2 != null) {
                        mergeFrom(createEntityTypeRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CreateEntityTypeRequest createEntityTypeRequest3 = (CreateEntityTypeRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        createEntityTypeRequest = createEntityTypeRequest3;
                        if (createEntityTypeRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (createEntityTypeRequest != null) {
                    mergeFrom(createEntityTypeRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
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
            this.parent_ = CreateEntityTypeRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                CreateEntityTypeRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
        public boolean hasEntityType() {
            return (this.entityTypeBuilder_ == null && this.entityType_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
        public EntityType getEntityType() {
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                EntityType entityType = this.entityType_;
                return entityType == null ? EntityType.getDefaultInstance() : entityType;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setEntityType(EntityType entityType) {
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(entityType);
            } else {
                if (entityType == null) {
                    throw new NullPointerException();
                }
                this.entityType_ = entityType;
                onChanged();
            }
            return this;
        }

        public Builder setEntityType(EntityType.Builder builder) {
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.entityType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEntityType(EntityType entityType) {
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                EntityType entityType2 = this.entityType_;
                if (entityType2 != null) {
                    this.entityType_ = EntityType.newBuilder(entityType2).mergeFrom(entityType).buildPartial();
                } else {
                    this.entityType_ = entityType;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(entityType);
            }
            return this;
        }

        public Builder clearEntityType() {
            if (this.entityTypeBuilder_ == null) {
                this.entityType_ = null;
                onChanged();
            } else {
                this.entityType_ = null;
                this.entityTypeBuilder_ = null;
            }
            return this;
        }

        public EntityType.Builder getEntityTypeBuilder() {
            onChanged();
            return getEntityTypeFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
        public EntityTypeOrBuilder getEntityTypeOrBuilder() {
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            EntityType entityType = this.entityType_;
            return entityType == null ? EntityType.getDefaultInstance() : entityType;
        }

        private SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> getEntityTypeFieldBuilder() {
            if (this.entityTypeBuilder_ == null) {
                this.entityTypeBuilder_ = new SingleFieldBuilderV3<>(getEntityType(), getParentForChildren(), isClean());
                this.entityType_ = null;
            }
            return this.entityTypeBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateEntityTypeRequestOrBuilder
        public ByteString getLanguageCodeBytes() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.languageCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLanguageCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.languageCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearLanguageCode() {
            this.languageCode_ = CreateEntityTypeRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                CreateEntityTypeRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
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

    public static CreateEntityTypeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateEntityTypeRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<CreateEntityTypeRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public CreateEntityTypeRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
