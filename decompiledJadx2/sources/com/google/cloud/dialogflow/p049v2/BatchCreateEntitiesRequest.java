package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.EntityType;
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
/* loaded from: classes2.dex */
public final class BatchCreateEntitiesRequest extends GeneratedMessageV3 implements BatchCreateEntitiesRequestOrBuilder {
    public static final int ENTITIES_FIELD_NUMBER = 2;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<EntityType.Entity> entities_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final BatchCreateEntitiesRequest DEFAULT_INSTANCE = new BatchCreateEntitiesRequest();
    private static final Parser<BatchCreateEntitiesRequest> PARSER = new AbstractParser<BatchCreateEntitiesRequest>() { // from class: com.google.cloud.dialogflow.v2.BatchCreateEntitiesRequest.1
        @Override // com.google.protobuf.Parser
        public BatchCreateEntitiesRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchCreateEntitiesRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchCreateEntitiesRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchCreateEntitiesRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.entities_ = Collections.emptyList();
        this.languageCode_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchCreateEntitiesRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BatchCreateEntitiesRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.parent_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                if (!(z2 & true)) {
                                    this.entities_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.entities_.add(codedInputStream.readMessage(EntityType.Entity.parser(), extensionRegistryLite));
                            } else if (readTag == 26) {
                                this.languageCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EntityTypeProto.f1413xffdd6adb;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1414x22cd8659.ensureFieldAccessorsInitialized(BatchCreateEntitiesRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public List<EntityType.Entity> getEntitiesList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public int getEntitiesCount() {
        return this.entities_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public EntityType.Entity getEntities(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public EntityType.EntityOrBuilder getEntitiesOrBuilder(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
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
        for (int i = 0; i < this.entities_.size(); i++) {
            codedOutputStream.writeMessage(2, this.entities_.get(i));
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
        int computeStringSize = !getParentBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.parent_) + 0 : 0;
        for (int i2 = 0; i2 < this.entities_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, this.entities_.get(i2));
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
        if (!(obj instanceof BatchCreateEntitiesRequest)) {
            return super.equals(obj);
        }
        BatchCreateEntitiesRequest batchCreateEntitiesRequest = (BatchCreateEntitiesRequest) obj;
        return getParent().equals(batchCreateEntitiesRequest.getParent()) && getEntitiesList().equals(batchCreateEntitiesRequest.getEntitiesList()) && getLanguageCode().equals(batchCreateEntitiesRequest.getLanguageCode()) && this.unknownFields.equals(batchCreateEntitiesRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (getEntitiesCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getEntitiesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 3) * 53) + getLanguageCode().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static BatchCreateEntitiesRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchCreateEntitiesRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchCreateEntitiesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchCreateEntitiesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchCreateEntitiesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchCreateEntitiesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchCreateEntitiesRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchCreateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchCreateEntitiesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchCreateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchCreateEntitiesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchCreateEntitiesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchCreateEntitiesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchCreateEntitiesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchCreateEntitiesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchCreateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchCreateEntitiesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchCreateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchCreateEntitiesRequest batchCreateEntitiesRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchCreateEntitiesRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchCreateEntitiesRequestOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> entitiesBuilder_;
        private List<EntityType.Entity> entities_;
        private Object languageCode_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1413xffdd6adb;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1414x22cd8659.ensureFieldAccessorsInitialized(BatchCreateEntitiesRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.entities_ = Collections.emptyList();
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.entities_ = Collections.emptyList();
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (BatchCreateEntitiesRequest.alwaysUseFieldBuilders) {
                getEntitiesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entities_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.languageCode_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1413xffdd6adb;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchCreateEntitiesRequest getDefaultInstanceForType() {
            return BatchCreateEntitiesRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchCreateEntitiesRequest build() {
            BatchCreateEntitiesRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchCreateEntitiesRequest buildPartial() {
            BatchCreateEntitiesRequest batchCreateEntitiesRequest = new BatchCreateEntitiesRequest(this);
            int i = this.bitField0_;
            batchCreateEntitiesRequest.parent_ = this.parent_;
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                batchCreateEntitiesRequest.entities_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                    this.bitField0_ &= -2;
                }
                batchCreateEntitiesRequest.entities_ = this.entities_;
            }
            batchCreateEntitiesRequest.languageCode_ = this.languageCode_;
            onBuilt();
            return batchCreateEntitiesRequest;
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
            if (message instanceof BatchCreateEntitiesRequest) {
                return mergeFrom((BatchCreateEntitiesRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchCreateEntitiesRequest batchCreateEntitiesRequest) {
            if (batchCreateEntitiesRequest == BatchCreateEntitiesRequest.getDefaultInstance()) {
                return this;
            }
            if (!batchCreateEntitiesRequest.getParent().isEmpty()) {
                this.parent_ = batchCreateEntitiesRequest.parent_;
                onChanged();
            }
            if (this.entitiesBuilder_ == null) {
                if (!batchCreateEntitiesRequest.entities_.isEmpty()) {
                    if (this.entities_.isEmpty()) {
                        this.entities_ = batchCreateEntitiesRequest.entities_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntitiesIsMutable();
                        this.entities_.addAll(batchCreateEntitiesRequest.entities_);
                    }
                    onChanged();
                }
            } else if (!batchCreateEntitiesRequest.entities_.isEmpty()) {
                if (!this.entitiesBuilder_.isEmpty()) {
                    this.entitiesBuilder_.addAllMessages(batchCreateEntitiesRequest.entities_);
                } else {
                    this.entitiesBuilder_.dispose();
                    this.entitiesBuilder_ = null;
                    this.entities_ = batchCreateEntitiesRequest.entities_;
                    this.bitField0_ &= -2;
                    this.entitiesBuilder_ = BatchCreateEntitiesRequest.alwaysUseFieldBuilders ? getEntitiesFieldBuilder() : null;
                }
            }
            if (!batchCreateEntitiesRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = batchCreateEntitiesRequest.languageCode_;
                onChanged();
            }
            mergeUnknownFields(batchCreateEntitiesRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchCreateEntitiesRequest batchCreateEntitiesRequest = null;
            try {
                try {
                    BatchCreateEntitiesRequest batchCreateEntitiesRequest2 = (BatchCreateEntitiesRequest) BatchCreateEntitiesRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchCreateEntitiesRequest2 != null) {
                        mergeFrom(batchCreateEntitiesRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchCreateEntitiesRequest batchCreateEntitiesRequest3 = (BatchCreateEntitiesRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchCreateEntitiesRequest = batchCreateEntitiesRequest3;
                        if (batchCreateEntitiesRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchCreateEntitiesRequest != null) {
                    mergeFrom(batchCreateEntitiesRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
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
            this.parent_ = BatchCreateEntitiesRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                BatchCreateEntitiesRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureEntitiesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entities_ = new ArrayList(this.entities_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public List<EntityType.Entity> getEntitiesList() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public int getEntitiesCount() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public EntityType.Entity getEntities(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEntities(int i, EntityType.Entity entity) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, entity);
            } else {
                if (entity == null) {
                    throw new NullPointerException();
                }
                ensureEntitiesIsMutable();
                this.entities_.set(i, entity);
                onChanged();
            }
            return this;
        }

        public Builder setEntities(int i, EntityType.Entity.Builder builder) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEntities(EntityType.Entity entity) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(entity);
            } else {
                if (entity == null) {
                    throw new NullPointerException();
                }
                ensureEntitiesIsMutable();
                this.entities_.add(entity);
                onChanged();
            }
            return this;
        }

        public Builder addEntities(int i, EntityType.Entity entity) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, entity);
            } else {
                if (entity == null) {
                    throw new NullPointerException();
                }
                ensureEntitiesIsMutable();
                this.entities_.add(i, entity);
                onChanged();
            }
            return this;
        }

        public Builder addEntities(EntityType.Entity.Builder builder) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEntities(int i, EntityType.Entity.Builder builder) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEntities(Iterable<? extends EntityType.Entity> iterable) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.entities_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEntities() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entities_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEntities(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public EntityType.Entity.Builder getEntitiesBuilder(int i) {
            return getEntitiesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public EntityType.EntityOrBuilder getEntitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entities_);
        }

        public EntityType.Entity.Builder addEntitiesBuilder() {
            return getEntitiesFieldBuilder().addBuilder(EntityType.Entity.getDefaultInstance());
        }

        public EntityType.Entity.Builder addEntitiesBuilder(int i) {
            return getEntitiesFieldBuilder().addBuilder(i, EntityType.Entity.getDefaultInstance());
        }

        public List<EntityType.Entity.Builder> getEntitiesBuilderList() {
            return getEntitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> getEntitiesFieldBuilder() {
            if (this.entitiesBuilder_ == null) {
                this.entitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.entities_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.entities_ = null;
            }
            return this.entitiesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchCreateEntitiesRequestOrBuilder
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
            this.languageCode_ = BatchCreateEntitiesRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                BatchCreateEntitiesRequest.checkByteStringIsUtf8(byteString);
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

    public static BatchCreateEntitiesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchCreateEntitiesRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchCreateEntitiesRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchCreateEntitiesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
