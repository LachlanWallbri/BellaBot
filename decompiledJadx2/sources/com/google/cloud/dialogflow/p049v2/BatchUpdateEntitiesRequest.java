package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.EntityType;
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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
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
public final class BatchUpdateEntitiesRequest extends GeneratedMessageV3 implements BatchUpdateEntitiesRequestOrBuilder {
    public static final int ENTITIES_FIELD_NUMBER = 2;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private List<EntityType.Entity> entities_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private FieldMask updateMask_;
    private static final BatchUpdateEntitiesRequest DEFAULT_INSTANCE = new BatchUpdateEntitiesRequest();
    private static final Parser<BatchUpdateEntitiesRequest> PARSER = new AbstractParser<BatchUpdateEntitiesRequest>() { // from class: com.google.cloud.dialogflow.v2.BatchUpdateEntitiesRequest.1
        @Override // com.google.protobuf.Parser
        public BatchUpdateEntitiesRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchUpdateEntitiesRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchUpdateEntitiesRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchUpdateEntitiesRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.entities_ = Collections.emptyList();
        this.languageCode_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchUpdateEntitiesRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BatchUpdateEntitiesRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        } else if (readTag == 34) {
                            FieldMask.Builder builder = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                            this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.updateMask_);
                                this.updateMask_ = builder.buildPartial();
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
                if (z2 & true) {
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EntityTypeProto.f1419x68c3ac68;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1420x4f93eae6.ensureFieldAccessorsInitialized(BatchUpdateEntitiesRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public List<EntityType.Entity> getEntitiesList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public int getEntitiesCount() {
        return this.entities_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public EntityType.Entity getEntities(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public EntityType.EntityOrBuilder getEntitiesOrBuilder(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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
        for (int i = 0; i < this.entities_.size(); i++) {
            codedOutputStream.writeMessage(2, this.entities_.get(i));
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(4, getUpdateMask());
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
        if (this.updateMask_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, getUpdateMask());
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
        if (!(obj instanceof BatchUpdateEntitiesRequest)) {
            return super.equals(obj);
        }
        BatchUpdateEntitiesRequest batchUpdateEntitiesRequest = (BatchUpdateEntitiesRequest) obj;
        if (getParent().equals(batchUpdateEntitiesRequest.getParent()) && getEntitiesList().equals(batchUpdateEntitiesRequest.getEntitiesList()) && getLanguageCode().equals(batchUpdateEntitiesRequest.getLanguageCode()) && hasUpdateMask() == batchUpdateEntitiesRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(batchUpdateEntitiesRequest.getUpdateMask())) && this.unknownFields.equals(batchUpdateEntitiesRequest.unknownFields);
        }
        return false;
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
        int hashCode2 = (((hashCode * 37) + 3) * 53) + getLanguageCode().hashCode();
        if (hasUpdateMask()) {
            hashCode2 = (((hashCode2 * 37) + 4) * 53) + getUpdateMask().hashCode();
        }
        int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static BatchUpdateEntitiesRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchUpdateEntitiesRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchUpdateEntitiesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchUpdateEntitiesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchUpdateEntitiesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchUpdateEntitiesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchUpdateEntitiesRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateEntitiesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateEntitiesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateEntitiesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateEntitiesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateEntitiesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateEntitiesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchUpdateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchUpdateEntitiesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateEntitiesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchUpdateEntitiesRequest batchUpdateEntitiesRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchUpdateEntitiesRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchUpdateEntitiesRequestOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> entitiesBuilder_;
        private List<EntityType.Entity> entities_;
        private Object languageCode_;
        private Object parent_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1419x68c3ac68;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1420x4f93eae6.ensureFieldAccessorsInitialized(BatchUpdateEntitiesRequest.class, Builder.class);
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
            if (BatchUpdateEntitiesRequest.alwaysUseFieldBuilders) {
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
            return EntityTypeProto.f1419x68c3ac68;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchUpdateEntitiesRequest getDefaultInstanceForType() {
            return BatchUpdateEntitiesRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateEntitiesRequest build() {
            BatchUpdateEntitiesRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateEntitiesRequest buildPartial() {
            BatchUpdateEntitiesRequest batchUpdateEntitiesRequest = new BatchUpdateEntitiesRequest(this);
            int i = this.bitField0_;
            batchUpdateEntitiesRequest.parent_ = this.parent_;
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                batchUpdateEntitiesRequest.entities_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                    this.bitField0_ &= -2;
                }
                batchUpdateEntitiesRequest.entities_ = this.entities_;
            }
            batchUpdateEntitiesRequest.languageCode_ = this.languageCode_;
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                batchUpdateEntitiesRequest.updateMask_ = this.updateMask_;
            } else {
                batchUpdateEntitiesRequest.updateMask_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return batchUpdateEntitiesRequest;
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
            if (message instanceof BatchUpdateEntitiesRequest) {
                return mergeFrom((BatchUpdateEntitiesRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchUpdateEntitiesRequest batchUpdateEntitiesRequest) {
            if (batchUpdateEntitiesRequest == BatchUpdateEntitiesRequest.getDefaultInstance()) {
                return this;
            }
            if (!batchUpdateEntitiesRequest.getParent().isEmpty()) {
                this.parent_ = batchUpdateEntitiesRequest.parent_;
                onChanged();
            }
            if (this.entitiesBuilder_ == null) {
                if (!batchUpdateEntitiesRequest.entities_.isEmpty()) {
                    if (this.entities_.isEmpty()) {
                        this.entities_ = batchUpdateEntitiesRequest.entities_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntitiesIsMutable();
                        this.entities_.addAll(batchUpdateEntitiesRequest.entities_);
                    }
                    onChanged();
                }
            } else if (!batchUpdateEntitiesRequest.entities_.isEmpty()) {
                if (!this.entitiesBuilder_.isEmpty()) {
                    this.entitiesBuilder_.addAllMessages(batchUpdateEntitiesRequest.entities_);
                } else {
                    this.entitiesBuilder_.dispose();
                    this.entitiesBuilder_ = null;
                    this.entities_ = batchUpdateEntitiesRequest.entities_;
                    this.bitField0_ &= -2;
                    this.entitiesBuilder_ = BatchUpdateEntitiesRequest.alwaysUseFieldBuilders ? getEntitiesFieldBuilder() : null;
                }
            }
            if (!batchUpdateEntitiesRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = batchUpdateEntitiesRequest.languageCode_;
                onChanged();
            }
            if (batchUpdateEntitiesRequest.hasUpdateMask()) {
                mergeUpdateMask(batchUpdateEntitiesRequest.getUpdateMask());
            }
            mergeUnknownFields(batchUpdateEntitiesRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchUpdateEntitiesRequest batchUpdateEntitiesRequest = null;
            try {
                try {
                    BatchUpdateEntitiesRequest batchUpdateEntitiesRequest2 = (BatchUpdateEntitiesRequest) BatchUpdateEntitiesRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchUpdateEntitiesRequest2 != null) {
                        mergeFrom(batchUpdateEntitiesRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchUpdateEntitiesRequest batchUpdateEntitiesRequest3 = (BatchUpdateEntitiesRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchUpdateEntitiesRequest = batchUpdateEntitiesRequest3;
                        if (batchUpdateEntitiesRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchUpdateEntitiesRequest != null) {
                    mergeFrom(batchUpdateEntitiesRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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
            this.parent_ = BatchUpdateEntitiesRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateEntitiesRequest.checkByteStringIsUtf8(byteString);
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
        public List<EntityType.Entity> getEntitiesList() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
        public int getEntitiesCount() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
        public EntityType.EntityOrBuilder getEntitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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
            this.languageCode_ = BatchUpdateEntitiesRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateEntitiesRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateEntitiesRequestOrBuilder
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

    public static BatchUpdateEntitiesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchUpdateEntitiesRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchUpdateEntitiesRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchUpdateEntitiesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
