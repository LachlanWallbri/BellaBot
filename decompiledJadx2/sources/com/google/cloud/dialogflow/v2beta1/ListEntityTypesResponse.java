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
public final class ListEntityTypesResponse extends GeneratedMessageV3 implements ListEntityTypesResponseOrBuilder {
    public static final int ENTITY_TYPES_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<EntityType> entityTypes_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private static final ListEntityTypesResponse DEFAULT_INSTANCE = new ListEntityTypesResponse();
    private static final Parser<ListEntityTypesResponse> PARSER = new AbstractParser<ListEntityTypesResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponse.1
        @Override // com.google.protobuf.Parser
        public ListEntityTypesResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListEntityTypesResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListEntityTypesResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListEntityTypesResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.entityTypes_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListEntityTypesResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListEntityTypesResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (!(z2 & true)) {
                                this.entityTypes_ = new ArrayList();
                                z2 |= true;
                            }
                            this.entityTypes_.add(codedInputStream.readMessage(EntityType.parser(), extensionRegistryLite));
                        } else if (readTag == 18) {
                            this.nextPageToken_ = codedInputStream.readStringRequireUtf8();
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
                    this.entityTypes_ = Collections.unmodifiableList(this.entityTypes_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EntityTypeProto.f1678x3cfa5d7;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1679x89430555.ensureFieldAccessorsInitialized(ListEntityTypesResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public List<EntityType> getEntityTypesList() {
        return this.entityTypes_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public List<? extends EntityTypeOrBuilder> getEntityTypesOrBuilderList() {
        return this.entityTypes_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public int getEntityTypesCount() {
        return this.entityTypes_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public EntityType getEntityTypes(int i) {
        return this.entityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public EntityTypeOrBuilder getEntityTypesOrBuilder(int i) {
        return this.entityTypes_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nextPageToken_ = copyFromUtf8;
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
        for (int i = 0; i < this.entityTypes_.size(); i++) {
            codedOutputStream.writeMessage(1, this.entityTypes_.get(i));
        }
        if (!getNextPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.nextPageToken_);
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
        if (!getNextPageTokenBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.nextPageToken_);
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
        if (!(obj instanceof ListEntityTypesResponse)) {
            return super.equals(obj);
        }
        ListEntityTypesResponse listEntityTypesResponse = (ListEntityTypesResponse) obj;
        return getEntityTypesList().equals(listEntityTypesResponse.getEntityTypesList()) && getNextPageToken().equals(listEntityTypesResponse.getNextPageToken()) && this.unknownFields.equals(listEntityTypesResponse.unknownFields);
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
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListEntityTypesResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListEntityTypesResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListEntityTypesResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListEntityTypesResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListEntityTypesResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListEntityTypesResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListEntityTypesResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListEntityTypesResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListEntityTypesResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListEntityTypesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListEntityTypesResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListEntityTypesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListEntityTypesResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListEntityTypesResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListEntityTypesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListEntityTypesResponse listEntityTypesResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listEntityTypesResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListEntityTypesResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> entityTypesBuilder_;
        private List<EntityType> entityTypes_;
        private Object nextPageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1678x3cfa5d7;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1679x89430555.ensureFieldAccessorsInitialized(ListEntityTypesResponse.class, Builder.class);
        }

        private Builder() {
            this.entityTypes_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.entityTypes_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListEntityTypesResponse.alwaysUseFieldBuilders) {
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
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1678x3cfa5d7;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListEntityTypesResponse getDefaultInstanceForType() {
            return ListEntityTypesResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListEntityTypesResponse build() {
            ListEntityTypesResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListEntityTypesResponse buildPartial() {
            ListEntityTypesResponse listEntityTypesResponse = new ListEntityTypesResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.entityTypes_ = Collections.unmodifiableList(this.entityTypes_);
                    this.bitField0_ &= -2;
                }
                listEntityTypesResponse.entityTypes_ = this.entityTypes_;
            } else {
                listEntityTypesResponse.entityTypes_ = repeatedFieldBuilderV3.build();
            }
            listEntityTypesResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listEntityTypesResponse;
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
            if (message instanceof ListEntityTypesResponse) {
                return mergeFrom((ListEntityTypesResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListEntityTypesResponse listEntityTypesResponse) {
            if (listEntityTypesResponse == ListEntityTypesResponse.getDefaultInstance()) {
                return this;
            }
            if (this.entityTypesBuilder_ == null) {
                if (!listEntityTypesResponse.entityTypes_.isEmpty()) {
                    if (this.entityTypes_.isEmpty()) {
                        this.entityTypes_ = listEntityTypesResponse.entityTypes_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntityTypesIsMutable();
                        this.entityTypes_.addAll(listEntityTypesResponse.entityTypes_);
                    }
                    onChanged();
                }
            } else if (!listEntityTypesResponse.entityTypes_.isEmpty()) {
                if (!this.entityTypesBuilder_.isEmpty()) {
                    this.entityTypesBuilder_.addAllMessages(listEntityTypesResponse.entityTypes_);
                } else {
                    this.entityTypesBuilder_.dispose();
                    this.entityTypesBuilder_ = null;
                    this.entityTypes_ = listEntityTypesResponse.entityTypes_;
                    this.bitField0_ &= -2;
                    this.entityTypesBuilder_ = ListEntityTypesResponse.alwaysUseFieldBuilders ? getEntityTypesFieldBuilder() : null;
                }
            }
            if (!listEntityTypesResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listEntityTypesResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listEntityTypesResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListEntityTypesResponse listEntityTypesResponse = null;
            try {
                try {
                    ListEntityTypesResponse listEntityTypesResponse2 = (ListEntityTypesResponse) ListEntityTypesResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listEntityTypesResponse2 != null) {
                        mergeFrom(listEntityTypesResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListEntityTypesResponse listEntityTypesResponse3 = (ListEntityTypesResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listEntityTypesResponse = listEntityTypesResponse3;
                        if (listEntityTypesResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listEntityTypesResponse != null) {
                    mergeFrom(listEntityTypesResponse);
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

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
        public List<EntityType> getEntityTypesList() {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entityTypes_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
        public int getEntityTypesCount() {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entityTypes_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
        public EntityTypeOrBuilder getEntityTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<EntityType, EntityType.Builder, EntityTypeOrBuilder> repeatedFieldBuilderV3 = this.entityTypesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entityTypes_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListEntityTypesResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            Object obj = this.nextPageToken_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.nextPageToken_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNextPageToken(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.nextPageToken_ = str;
            onChanged();
            return this;
        }

        public Builder clearNextPageToken() {
            this.nextPageToken_ = ListEntityTypesResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListEntityTypesResponse.checkByteStringIsUtf8(byteString);
                this.nextPageToken_ = byteString;
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

    public static ListEntityTypesResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListEntityTypesResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListEntityTypesResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListEntityTypesResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
