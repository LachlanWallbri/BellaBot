package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.EntityType;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class EntityTypeBatch extends GeneratedMessageV3 implements EntityTypeBatchOrBuilder {
    public static final int ENTITY_TYPES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<EntityType> entityTypes_;
    private byte memoizedIsInitialized;
    private static final EntityTypeBatch DEFAULT_INSTANCE = new EntityTypeBatch();
    private static final Parser<EntityTypeBatch> PARSER = new AbstractParser<EntityTypeBatch>() { // from class: com.google.cloud.dialogflow.v2beta1.EntityTypeBatch.1
        @Override // com.google.protobuf.Parser
        public EntityTypeBatch parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EntityTypeBatch(codedInputStream, extensionRegistryLite);
        }
    };

    private EntityTypeBatch(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private EntityTypeBatch() {
        this.memoizedIsInitialized = (byte) -1;
        this.entityTypes_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new EntityTypeBatch();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EntityTypeBatch(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (!(z2 & true)) {
                                    this.entityTypes_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.entityTypes_.add(codedInputStream.readMessage(EntityType.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.entityTypes_ = Collections.unmodifiableList(this.entityTypes_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EntityTypeProto.f1668x8a8bc113;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1669x8bbe2491.ensureFieldAccessorsInitialized(EntityTypeBatch.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
    public List<EntityType> getEntityTypesList() {
        return this.entityTypes_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
    public List<? extends EntityTypeOrBuilder> getEntityTypesOrBuilderList() {
        return this.entityTypes_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
    public int getEntityTypesCount() {
        return this.entityTypes_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
    public EntityType getEntityTypes(int i) {
        return this.entityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
    public EntityTypeOrBuilder getEntityTypesOrBuilder(int i) {
        return this.entityTypes_.get(i);
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
        for (int i = 0; i < this.entityTypes_.size(); i++) {
            codedOutputStream.writeMessage(1, this.entityTypes_.get(i));
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
        for (int i3 = 0; i3 < this.entityTypes_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.entityTypes_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EntityTypeBatch)) {
            return super.equals(obj);
        }
        EntityTypeBatch entityTypeBatch = (EntityTypeBatch) obj;
        return getEntityTypesList().equals(entityTypeBatch.getEntityTypesList()) && this.unknownFields.equals(entityTypeBatch.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getEntityTypesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getEntityTypesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static EntityTypeBatch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static EntityTypeBatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static EntityTypeBatch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static EntityTypeBatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static EntityTypeBatch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static EntityTypeBatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static EntityTypeBatch parseFrom(InputStream inputStream) throws IOException {
        return (EntityTypeBatch) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EntityTypeBatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EntityTypeBatch) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EntityTypeBatch parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EntityTypeBatch) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EntityTypeBatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EntityTypeBatch) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EntityTypeBatch parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EntityTypeBatch) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static EntityTypeBatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EntityTypeBatch) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EntityTypeBatch entityTypeBatch) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(entityTypeBatch);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EntityTypeBatchOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> entityTypesBuilder_;
        private List<EntityType> entityTypes_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1668x8a8bc113;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1669x8bbe2491.ensureFieldAccessorsInitialized(EntityTypeBatch.class, Builder.class);
        }

        private Builder() {
            this.entityTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.entityTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (EntityTypeBatch.alwaysUseFieldBuilders) {
                getEntityTypesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entityTypes_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1668x8a8bc113;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EntityTypeBatch getDefaultInstanceForType() {
            return EntityTypeBatch.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public EntityTypeBatch build() {
            EntityTypeBatch buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public EntityTypeBatch buildPartial() {
            EntityTypeBatch entityTypeBatch = new EntityTypeBatch(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.entityTypes_ = Collections.unmodifiableList(this.entityTypes_);
                    this.bitField0_ &= -2;
                }
                entityTypeBatch.entityTypes_ = this.entityTypes_;
            } else {
                entityTypeBatch.entityTypes_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return entityTypeBatch;
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
            if (message instanceof EntityTypeBatch) {
                return mergeFrom((EntityTypeBatch) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(EntityTypeBatch entityTypeBatch) {
            if (entityTypeBatch == EntityTypeBatch.getDefaultInstance()) {
                return this;
            }
            if (this.entityTypesBuilder_ == null) {
                if (!entityTypeBatch.entityTypes_.isEmpty()) {
                    if (this.entityTypes_.isEmpty()) {
                        this.entityTypes_ = entityTypeBatch.entityTypes_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntityTypesIsMutable();
                        this.entityTypes_.addAll(entityTypeBatch.entityTypes_);
                    }
                    onChanged();
                }
            } else if (!entityTypeBatch.entityTypes_.isEmpty()) {
                if (!this.entityTypesBuilder_.isEmpty()) {
                    this.entityTypesBuilder_.addAllMessages(entityTypeBatch.entityTypes_);
                } else {
                    this.entityTypesBuilder_.dispose();
                    this.entityTypesBuilder_ = null;
                    this.entityTypes_ = entityTypeBatch.entityTypes_;
                    this.bitField0_ &= -2;
                    this.entityTypesBuilder_ = EntityTypeBatch.alwaysUseFieldBuilders ? getEntityTypesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(entityTypeBatch.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            EntityTypeBatch entityTypeBatch = null;
            try {
                try {
                    EntityTypeBatch entityTypeBatch2 = (EntityTypeBatch) EntityTypeBatch.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (entityTypeBatch2 != null) {
                        mergeFrom(entityTypeBatch2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    EntityTypeBatch entityTypeBatch3 = (EntityTypeBatch) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        entityTypeBatch = entityTypeBatch3;
                        if (entityTypeBatch != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (entityTypeBatch != null) {
                    mergeFrom(entityTypeBatch);
                }
                throw th;
            }
        }

        private void ensureEntityTypesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entityTypes_ = new ArrayList(this.entityTypes_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
        public List<EntityType> getEntityTypesList() {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entityTypes_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
        public int getEntityTypesCount() {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entityTypes_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
        public EntityType getEntityTypes(int i) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEntityTypes(int i, EntityType entityType) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, entityType);
            } else {
                if (entityType == null) {
                    throw new NullPointerException();
                }
                ensureEntityTypesIsMutable();
                this.entityTypes_.set(i, entityType);
                onChanged();
            }
            return this;
        }

        public Builder setEntityTypes(int i, EntityType.Builder builder) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntityTypesIsMutable();
                this.entityTypes_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEntityTypes(EntityType entityType) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(entityType);
            } else {
                if (entityType == null) {
                    throw new NullPointerException();
                }
                ensureEntityTypesIsMutable();
                this.entityTypes_.add(entityType);
                onChanged();
            }
            return this;
        }

        public Builder addEntityTypes(int i, EntityType entityType) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, entityType);
            } else {
                if (entityType == null) {
                    throw new NullPointerException();
                }
                ensureEntityTypesIsMutable();
                this.entityTypes_.add(i, entityType);
                onChanged();
            }
            return this;
        }

        public Builder addEntityTypes(EntityType.Builder builder) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntityTypesIsMutable();
                this.entityTypes_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEntityTypes(int i, EntityType.Builder builder) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntityTypesIsMutable();
                this.entityTypes_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEntityTypes(Iterable<? extends EntityType> iterable) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntityTypesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.entityTypes_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEntityTypes() {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entityTypes_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEntityTypes(int i) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntityTypesIsMutable();
                this.entityTypes_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public EntityType.Builder getEntityTypesBuilder(int i) {
            return getEntityTypesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
        public EntityTypeOrBuilder getEntityTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeBatchOrBuilder
        public List<? extends EntityTypeOrBuilder> getEntityTypesOrBuilderList() {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entityTypes_);
        }

        public EntityType.Builder addEntityTypesBuilder() {
            return getEntityTypesFieldBuilder().addBuilder(EntityType.getDefaultInstance());
        }

        public EntityType.Builder addEntityTypesBuilder(int i) {
            return getEntityTypesFieldBuilder().addBuilder(i, EntityType.getDefaultInstance());
        }

        public List<EntityType.Builder> getEntityTypesBuilderList() {
            return getEntityTypesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> getEntityTypesFieldBuilder() {
            if (this.entityTypesBuilder_ == null) {
                this.entityTypesBuilder_ = new RepeatedFieldBuilderV3<>(this.entityTypes_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.entityTypes_ = null;
            }
            return this.entityTypesBuilder_;
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

    public static EntityTypeBatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EntityTypeBatch> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<EntityTypeBatch> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public EntityTypeBatch getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
