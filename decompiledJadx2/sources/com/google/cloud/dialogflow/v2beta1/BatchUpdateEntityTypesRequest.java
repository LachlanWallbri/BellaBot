package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.EntityTypeBatch;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
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
public final class BatchUpdateEntityTypesRequest extends GeneratedMessageV3 implements BatchUpdateEntityTypesRequestOrBuilder {
    public static final int ENTITY_TYPE_BATCH_INLINE_FIELD_NUMBER = 3;
    public static final int ENTITY_TYPE_BATCH_URI_FIELD_NUMBER = 2;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 4;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int entityTypeBatchCase_;
    private Object entityTypeBatch_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private FieldMask updateMask_;
    private static final BatchUpdateEntityTypesRequest DEFAULT_INSTANCE = new BatchUpdateEntityTypesRequest();
    private static final Parser<BatchUpdateEntityTypesRequest> PARSER = new AbstractParser<BatchUpdateEntityTypesRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequest.1
        @Override // com.google.protobuf.Parser
        public BatchUpdateEntityTypesRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchUpdateEntityTypesRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchUpdateEntityTypesRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.entityTypeBatchCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchUpdateEntityTypesRequest() {
        this.entityTypeBatchCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.languageCode_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchUpdateEntityTypesRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BatchUpdateEntityTypesRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        } else if (readTag != 18) {
                            if (readTag == 26) {
                                EntityTypeBatch.Builder builder = this.entityTypeBatchCase_ == 3 ? ((EntityTypeBatch) this.entityTypeBatch_).toBuilder() : null;
                                this.entityTypeBatch_ = codedInputStream.readMessage(EntityTypeBatch.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((EntityTypeBatch) this.entityTypeBatch_);
                                    this.entityTypeBatch_ = builder.buildPartial();
                                }
                                this.entityTypeBatchCase_ = 3;
                            } else if (readTag == 34) {
                                this.languageCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                FieldMask.Builder builder2 = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                                this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.updateMask_);
                                    this.updateMask_ = builder2.buildPartial();
                                }
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        } else {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.entityTypeBatchCase_ = 2;
                            this.entityTypeBatch_ = readStringRequireUtf8;
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
        return EntityTypeProto.f1660x189d5e54;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1661x1642f0d2.ensureFieldAccessorsInitialized(BatchUpdateEntityTypesRequest.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum EntityTypeBatchCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        ENTITY_TYPE_BATCH_URI(2),
        ENTITY_TYPE_BATCH_INLINE(3),
        ENTITYTYPEBATCH_NOT_SET(0);

        private final int value;

        EntityTypeBatchCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static EntityTypeBatchCase valueOf(int i) {
            return forNumber(i);
        }

        public static EntityTypeBatchCase forNumber(int i) {
            if (i == 0) {
                return ENTITYTYPEBATCH_NOT_SET;
            }
            if (i == 2) {
                return ENTITY_TYPE_BATCH_URI;
            }
            if (i != 3) {
                return null;
            }
            return ENTITY_TYPE_BATCH_INLINE;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public EntityTypeBatchCase getEntityTypeBatchCase() {
        return EntityTypeBatchCase.forNumber(this.entityTypeBatchCase_);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public String getEntityTypeBatchUri() {
        String str = this.entityTypeBatchCase_ == 2 ? this.entityTypeBatch_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.entityTypeBatchCase_ == 2) {
            this.entityTypeBatch_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public ByteString getEntityTypeBatchUriBytes() {
        String str = this.entityTypeBatchCase_ == 2 ? this.entityTypeBatch_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.entityTypeBatchCase_ == 2) {
                this.entityTypeBatch_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public boolean hasEntityTypeBatchInline() {
        return this.entityTypeBatchCase_ == 3;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public EntityTypeBatch getEntityTypeBatchInline() {
        if (this.entityTypeBatchCase_ == 3) {
            return (EntityTypeBatch) this.entityTypeBatch_;
        }
        return EntityTypeBatch.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public EntityTypeBatchOrBuilder getEntityTypeBatchInlineOrBuilder() {
        if (this.entityTypeBatchCase_ == 3) {
            return (EntityTypeBatch) this.entityTypeBatch_;
        }
        return EntityTypeBatch.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
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
        if (!getParentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.parent_);
        }
        if (this.entityTypeBatchCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.entityTypeBatch_);
        }
        if (this.entityTypeBatchCase_ == 3) {
            codedOutputStream.writeMessage(3, (EntityTypeBatch) this.entityTypeBatch_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(5, getUpdateMask());
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
        if (this.entityTypeBatchCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.entityTypeBatch_);
        }
        if (this.entityTypeBatchCase_ == 3) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (EntityTypeBatch) this.entityTypeBatch_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, getUpdateMask());
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
        if (!(obj instanceof BatchUpdateEntityTypesRequest)) {
            return super.equals(obj);
        }
        BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest = (BatchUpdateEntityTypesRequest) obj;
        if (!getParent().equals(batchUpdateEntityTypesRequest.getParent()) || !getLanguageCode().equals(batchUpdateEntityTypesRequest.getLanguageCode()) || hasUpdateMask() != batchUpdateEntityTypesRequest.hasUpdateMask()) {
            return false;
        }
        if ((hasUpdateMask() && !getUpdateMask().equals(batchUpdateEntityTypesRequest.getUpdateMask())) || !getEntityTypeBatchCase().equals(batchUpdateEntityTypesRequest.getEntityTypeBatchCase())) {
            return false;
        }
        int i = this.entityTypeBatchCase_;
        if (i == 2) {
            if (!getEntityTypeBatchUri().equals(batchUpdateEntityTypesRequest.getEntityTypeBatchUri())) {
                return false;
            }
        } else if (i == 3 && !getEntityTypeBatchInline().equals(batchUpdateEntityTypesRequest.getEntityTypeBatchInline())) {
            return false;
        }
        return this.unknownFields.equals(batchUpdateEntityTypesRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode()) * 37) + 4) * 53) + getLanguageCode().hashCode();
        if (hasUpdateMask()) {
            hashCode2 = (((hashCode2 * 37) + 5) * 53) + getUpdateMask().hashCode();
        }
        int i2 = this.entityTypeBatchCase_;
        if (i2 == 2) {
            i = ((hashCode2 * 37) + 2) * 53;
            hashCode = getEntityTypeBatchUri().hashCode();
        } else {
            if (i2 == 3) {
                i = ((hashCode2 * 37) + 3) * 53;
                hashCode = getEntityTypeBatchInline().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        hashCode2 = i + hashCode;
        int hashCode32 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static BatchUpdateEntityTypesRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateEntityTypesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateEntityTypesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateEntityTypesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateEntityTypesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchUpdateEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchUpdateEntityTypesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchUpdateEntityTypesRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchUpdateEntityTypesRequestOrBuilder {
        private int entityTypeBatchCase_;
        private SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> entityTypeBatchInlineBuilder_;
        private Object entityTypeBatch_;
        private Object languageCode_;
        private Object parent_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1660x189d5e54;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1661x1642f0d2.ensureFieldAccessorsInitialized(BatchUpdateEntityTypesRequest.class, Builder.class);
        }

        private Builder() {
            this.entityTypeBatchCase_ = 0;
            this.parent_ = "";
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.entityTypeBatchCase_ = 0;
            this.parent_ = "";
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BatchUpdateEntityTypesRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.languageCode_ = "";
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            this.entityTypeBatchCase_ = 0;
            this.entityTypeBatch_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1660x189d5e54;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchUpdateEntityTypesRequest getDefaultInstanceForType() {
            return BatchUpdateEntityTypesRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateEntityTypesRequest build() {
            BatchUpdateEntityTypesRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateEntityTypesRequest buildPartial() {
            BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest = new BatchUpdateEntityTypesRequest(this);
            batchUpdateEntityTypesRequest.parent_ = this.parent_;
            if (this.entityTypeBatchCase_ == 2) {
                batchUpdateEntityTypesRequest.entityTypeBatch_ = this.entityTypeBatch_;
            }
            if (this.entityTypeBatchCase_ == 3) {
                SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> singleFieldBuilderV3 = this.entityTypeBatchInlineBuilder_;
                if (singleFieldBuilderV3 == null) {
                    batchUpdateEntityTypesRequest.entityTypeBatch_ = this.entityTypeBatch_;
                } else {
                    batchUpdateEntityTypesRequest.entityTypeBatch_ = singleFieldBuilderV3.build();
                }
            }
            batchUpdateEntityTypesRequest.languageCode_ = this.languageCode_;
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                batchUpdateEntityTypesRequest.updateMask_ = this.updateMask_;
            } else {
                batchUpdateEntityTypesRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            batchUpdateEntityTypesRequest.entityTypeBatchCase_ = this.entityTypeBatchCase_;
            onBuilt();
            return batchUpdateEntityTypesRequest;
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
            if (message instanceof BatchUpdateEntityTypesRequest) {
                return mergeFrom((BatchUpdateEntityTypesRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest) {
            if (batchUpdateEntityTypesRequest == BatchUpdateEntityTypesRequest.getDefaultInstance()) {
                return this;
            }
            if (!batchUpdateEntityTypesRequest.getParent().isEmpty()) {
                this.parent_ = batchUpdateEntityTypesRequest.parent_;
                onChanged();
            }
            if (!batchUpdateEntityTypesRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = batchUpdateEntityTypesRequest.languageCode_;
                onChanged();
            }
            if (batchUpdateEntityTypesRequest.hasUpdateMask()) {
                mergeUpdateMask(batchUpdateEntityTypesRequest.getUpdateMask());
            }
            int i = C23752.f1615x6332bd8f[batchUpdateEntityTypesRequest.getEntityTypeBatchCase().ordinal()];
            if (i == 1) {
                this.entityTypeBatchCase_ = 2;
                this.entityTypeBatch_ = batchUpdateEntityTypesRequest.entityTypeBatch_;
                onChanged();
            } else if (i == 2) {
                mergeEntityTypeBatchInline(batchUpdateEntityTypesRequest.getEntityTypeBatchInline());
            }
            mergeUnknownFields(batchUpdateEntityTypesRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest = null;
            try {
                try {
                    BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest2 = (BatchUpdateEntityTypesRequest) BatchUpdateEntityTypesRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchUpdateEntityTypesRequest2 != null) {
                        mergeFrom(batchUpdateEntityTypesRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchUpdateEntityTypesRequest batchUpdateEntityTypesRequest3 = (BatchUpdateEntityTypesRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchUpdateEntityTypesRequest = batchUpdateEntityTypesRequest3;
                        if (batchUpdateEntityTypesRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchUpdateEntityTypesRequest != null) {
                    mergeFrom(batchUpdateEntityTypesRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public EntityTypeBatchCase getEntityTypeBatchCase() {
            return EntityTypeBatchCase.forNumber(this.entityTypeBatchCase_);
        }

        public Builder clearEntityTypeBatch() {
            this.entityTypeBatchCase_ = 0;
            this.entityTypeBatch_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
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
            this.parent_ = BatchUpdateEntityTypesRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateEntityTypesRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public String getEntityTypeBatchUri() {
            String str = this.entityTypeBatchCase_ == 2 ? this.entityTypeBatch_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.entityTypeBatchCase_ == 2) {
                    this.entityTypeBatch_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public ByteString getEntityTypeBatchUriBytes() {
            String str = this.entityTypeBatchCase_ == 2 ? this.entityTypeBatch_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.entityTypeBatchCase_ == 2) {
                    this.entityTypeBatch_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setEntityTypeBatchUri(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.entityTypeBatchCase_ = 2;
            this.entityTypeBatch_ = str;
            onChanged();
            return this;
        }

        public Builder clearEntityTypeBatchUri() {
            if (this.entityTypeBatchCase_ == 2) {
                this.entityTypeBatchCase_ = 0;
                this.entityTypeBatch_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setEntityTypeBatchUriBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateEntityTypesRequest.checkByteStringIsUtf8(byteString);
                this.entityTypeBatchCase_ = 2;
                this.entityTypeBatch_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public boolean hasEntityTypeBatchInline() {
            return this.entityTypeBatchCase_ == 3;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public EntityTypeBatch getEntityTypeBatchInline() {
            SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> singleFieldBuilderV3 = this.entityTypeBatchInlineBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.entityTypeBatchCase_ == 3) {
                    return (EntityTypeBatch) this.entityTypeBatch_;
                }
                return EntityTypeBatch.getDefaultInstance();
            }
            if (this.entityTypeBatchCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return EntityTypeBatch.getDefaultInstance();
        }

        public Builder setEntityTypeBatchInline(EntityTypeBatch entityTypeBatch) {
            SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> singleFieldBuilderV3 = this.entityTypeBatchInlineBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(entityTypeBatch);
            } else {
                if (entityTypeBatch == null) {
                    throw new NullPointerException();
                }
                this.entityTypeBatch_ = entityTypeBatch;
                onChanged();
            }
            this.entityTypeBatchCase_ = 3;
            return this;
        }

        public Builder setEntityTypeBatchInline(EntityTypeBatch.Builder builder) {
            SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> singleFieldBuilderV3 = this.entityTypeBatchInlineBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.entityTypeBatch_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.entityTypeBatchCase_ = 3;
            return this;
        }

        public Builder mergeEntityTypeBatchInline(EntityTypeBatch entityTypeBatch) {
            SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> singleFieldBuilderV3 = this.entityTypeBatchInlineBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.entityTypeBatchCase_ == 3 && this.entityTypeBatch_ != EntityTypeBatch.getDefaultInstance()) {
                    this.entityTypeBatch_ = EntityTypeBatch.newBuilder((EntityTypeBatch) this.entityTypeBatch_).mergeFrom(entityTypeBatch).buildPartial();
                } else {
                    this.entityTypeBatch_ = entityTypeBatch;
                }
                onChanged();
            } else {
                if (this.entityTypeBatchCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(entityTypeBatch);
                }
                this.entityTypeBatchInlineBuilder_.setMessage(entityTypeBatch);
            }
            this.entityTypeBatchCase_ = 3;
            return this;
        }

        public Builder clearEntityTypeBatchInline() {
            if (this.entityTypeBatchInlineBuilder_ == null) {
                if (this.entityTypeBatchCase_ == 3) {
                    this.entityTypeBatchCase_ = 0;
                    this.entityTypeBatch_ = null;
                    onChanged();
                }
            } else {
                if (this.entityTypeBatchCase_ == 3) {
                    this.entityTypeBatchCase_ = 0;
                    this.entityTypeBatch_ = null;
                }
                this.entityTypeBatchInlineBuilder_.clear();
            }
            return this;
        }

        public EntityTypeBatch.Builder getEntityTypeBatchInlineBuilder() {
            return getEntityTypeBatchInlineFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public EntityTypeBatchOrBuilder getEntityTypeBatchInlineOrBuilder() {
            SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> singleFieldBuilderV3;
            if (this.entityTypeBatchCase_ == 3 && (singleFieldBuilderV3 = this.entityTypeBatchInlineBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.entityTypeBatchCase_ == 3) {
                return (EntityTypeBatch) this.entityTypeBatch_;
            }
            return EntityTypeBatch.getDefaultInstance();
        }

        private SingleFieldBuilderV3<EntityTypeBatch, EntityTypeBatch.Builder, EntityTypeBatchOrBuilder> getEntityTypeBatchInlineFieldBuilder() {
            if (this.entityTypeBatchInlineBuilder_ == null) {
                if (this.entityTypeBatchCase_ != 3) {
                    this.entityTypeBatch_ = EntityTypeBatch.getDefaultInstance();
                }
                this.entityTypeBatchInlineBuilder_ = new SingleFieldBuilderV3<>((EntityTypeBatch) this.entityTypeBatch_, getParentForChildren(), isClean());
                this.entityTypeBatch_ = null;
            }
            this.entityTypeBatchCase_ = 3;
            onChanged();
            return this.entityTypeBatchInlineBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
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
            this.languageCode_ = BatchUpdateEntityTypesRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateEntityTypesRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequestOrBuilder
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.cloud.dialogflow.v2beta1.BatchUpdateEntityTypesRequest$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C23752 {

        /* renamed from: $SwitchMap$com$google$cloud$dialogflow$v2beta1$BatchUpdateEntityTypesRequest$EntityTypeBatchCase */
        static final /* synthetic */ int[] f1615x6332bd8f = new int[EntityTypeBatchCase.values().length];

        static {
            try {
                f1615x6332bd8f[EntityTypeBatchCase.ENTITY_TYPE_BATCH_URI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1615x6332bd8f[EntityTypeBatchCase.ENTITY_TYPE_BATCH_INLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1615x6332bd8f[EntityTypeBatchCase.ENTITYTYPEBATCH_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static BatchUpdateEntityTypesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchUpdateEntityTypesRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchUpdateEntityTypesRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchUpdateEntityTypesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
