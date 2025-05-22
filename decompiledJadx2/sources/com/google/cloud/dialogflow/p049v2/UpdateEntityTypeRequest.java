package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.EntityType;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
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
public final class UpdateEntityTypeRequest extends GeneratedMessageV3 implements UpdateEntityTypeRequestOrBuilder {
    public static final int ENTITY_TYPE_FIELD_NUMBER = 1;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 2;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private EntityType entityType_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private FieldMask updateMask_;
    private static final UpdateEntityTypeRequest DEFAULT_INSTANCE = new UpdateEntityTypeRequest();
    private static final Parser<UpdateEntityTypeRequest> PARSER = new AbstractParser<UpdateEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2.UpdateEntityTypeRequest.1
        @Override // com.google.protobuf.Parser
        public UpdateEntityTypeRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpdateEntityTypeRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private UpdateEntityTypeRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpdateEntityTypeRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.languageCode_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new UpdateEntityTypeRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateEntityTypeRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            EntityType.Builder builder = this.entityType_ != null ? this.entityType_.toBuilder() : null;
                            this.entityType_ = (EntityType) codedInputStream.readMessage(EntityType.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.entityType_);
                                this.entityType_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
                            FieldMask.Builder builder2 = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                            this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.updateMask_);
                                this.updateMask_ = builder2.buildPartial();
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
        return EntityTypeProto.f1440x5e594aa0;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1441x8ba3d11e.ensureFieldAccessorsInitialized(UpdateEntityTypeRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public boolean hasEntityType() {
        return this.entityType_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public EntityType getEntityType() {
        EntityType entityType = this.entityType_;
        return entityType == null ? EntityType.getDefaultInstance() : entityType;
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public EntityTypeOrBuilder getEntityTypeOrBuilder() {
        return getEntityType();
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
    public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
        return getUpdateMask();
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
        if (this.entityType_ != null) {
            codedOutputStream.writeMessage(1, getEntityType());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(3, getUpdateMask());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.entityType_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getEntityType()) : 0;
        if (!getLanguageCodeBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, getUpdateMask());
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateEntityTypeRequest)) {
            return super.equals(obj);
        }
        UpdateEntityTypeRequest updateEntityTypeRequest = (UpdateEntityTypeRequest) obj;
        if (hasEntityType() != updateEntityTypeRequest.hasEntityType()) {
            return false;
        }
        if ((!hasEntityType() || getEntityType().equals(updateEntityTypeRequest.getEntityType())) && getLanguageCode().equals(updateEntityTypeRequest.getLanguageCode()) && hasUpdateMask() == updateEntityTypeRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(updateEntityTypeRequest.getUpdateMask())) && this.unknownFields.equals(updateEntityTypeRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasEntityType()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getEntityType().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 2) * 53) + getLanguageCode().hashCode();
        if (hasUpdateMask()) {
            hashCode2 = (((hashCode2 * 37) + 3) * 53) + getUpdateMask().hashCode();
        }
        int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static UpdateEntityTypeRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static UpdateEntityTypeRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpdateEntityTypeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static UpdateEntityTypeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpdateEntityTypeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static UpdateEntityTypeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpdateEntityTypeRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpdateEntityTypeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateEntityTypeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpdateEntityTypeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateEntityTypeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpdateEntityTypeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateEntityTypeRequest updateEntityTypeRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateEntityTypeRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateEntityTypeRequestOrBuilder {
        private SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> entityTypeBuilder_;
        private EntityType entityType_;
        private Object languageCode_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1440x5e594aa0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1441x8ba3d11e.ensureFieldAccessorsInitialized(UpdateEntityTypeRequest.class, Builder.class);
        }

        private Builder() {
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpdateEntityTypeRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.entityTypeBuilder_ == null) {
                this.entityType_ = null;
            } else {
                this.entityType_ = null;
                this.entityTypeBuilder_ = null;
            }
            this.languageCode_ = "";
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1440x5e594aa0;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateEntityTypeRequest getDefaultInstanceForType() {
            return UpdateEntityTypeRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateEntityTypeRequest build() {
            UpdateEntityTypeRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateEntityTypeRequest buildPartial() {
            UpdateEntityTypeRequest updateEntityTypeRequest = new UpdateEntityTypeRequest(this);
            SingleFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> singleFieldBuilderV3 = this.entityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                updateEntityTypeRequest.entityType_ = this.entityType_;
            } else {
                updateEntityTypeRequest.entityType_ = singleFieldBuilderV3.build();
            }
            updateEntityTypeRequest.languageCode_ = this.languageCode_;
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                updateEntityTypeRequest.updateMask_ = this.updateMask_;
            } else {
                updateEntityTypeRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return updateEntityTypeRequest;
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
            if (message instanceof UpdateEntityTypeRequest) {
                return mergeFrom((UpdateEntityTypeRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpdateEntityTypeRequest updateEntityTypeRequest) {
            if (updateEntityTypeRequest == UpdateEntityTypeRequest.getDefaultInstance()) {
                return this;
            }
            if (updateEntityTypeRequest.hasEntityType()) {
                mergeEntityType(updateEntityTypeRequest.getEntityType());
            }
            if (!updateEntityTypeRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = updateEntityTypeRequest.languageCode_;
                onChanged();
            }
            if (updateEntityTypeRequest.hasUpdateMask()) {
                mergeUpdateMask(updateEntityTypeRequest.getUpdateMask());
            }
            mergeUnknownFields(updateEntityTypeRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            UpdateEntityTypeRequest updateEntityTypeRequest = null;
            try {
                try {
                    UpdateEntityTypeRequest updateEntityTypeRequest2 = (UpdateEntityTypeRequest) UpdateEntityTypeRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (updateEntityTypeRequest2 != null) {
                        mergeFrom(updateEntityTypeRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    UpdateEntityTypeRequest updateEntityTypeRequest3 = (UpdateEntityTypeRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        updateEntityTypeRequest = updateEntityTypeRequest3;
                        if (updateEntityTypeRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (updateEntityTypeRequest != null) {
                    mergeFrom(updateEntityTypeRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
        public boolean hasEntityType() {
            return (this.entityTypeBuilder_ == null && this.entityType_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
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
            this.languageCode_ = UpdateEntityTypeRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                UpdateEntityTypeRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
        public FieldMask getUpdateMask() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask = this.updateMask_;
                return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setUpdateMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(fieldMask);
            } else {
                if (fieldMask == null) {
                    throw new NullPointerException();
                }
                this.updateMask_ = fieldMask;
                onChanged();
            }
            return this;
        }

        public Builder setUpdateMask(FieldMask.Builder builder) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.updateMask_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUpdateMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask2 = this.updateMask_;
                if (fieldMask2 != null) {
                    this.updateMask_ = FieldMask.newBuilder(fieldMask2).mergeFrom(fieldMask).buildPartial();
                } else {
                    this.updateMask_ = fieldMask;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fieldMask);
            }
            return this;
        }

        public Builder clearUpdateMask() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
                onChanged();
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        public FieldMask.Builder getUpdateMaskBuilder() {
            onChanged();
            return getUpdateMaskFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.UpdateEntityTypeRequestOrBuilder
        public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            FieldMask fieldMask = this.updateMask_;
            return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
        }

        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> getUpdateMaskFieldBuilder() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMaskBuilder_ = new SingleFieldBuilderV3<>(getUpdateMask(), getParentForChildren(), isClean());
                this.updateMask_ = null;
            }
            return this.updateMaskBuilder_;
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

    public static UpdateEntityTypeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateEntityTypeRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<UpdateEntityTypeRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public UpdateEntityTypeRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
