package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class BatchDeleteEntityTypesRequest extends GeneratedMessageV3 implements BatchDeleteEntityTypesRequestOrBuilder {
    public static final int ENTITY_TYPE_NAMES_FIELD_NUMBER = 2;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private LazyStringList entityTypeNames_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final BatchDeleteEntityTypesRequest DEFAULT_INSTANCE = new BatchDeleteEntityTypesRequest();
    private static final Parser<BatchDeleteEntityTypesRequest> PARSER = new AbstractParser<BatchDeleteEntityTypesRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequest.1
        @Override // com.google.protobuf.Parser
        public BatchDeleteEntityTypesRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchDeleteEntityTypesRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchDeleteEntityTypesRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchDeleteEntityTypesRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.entityTypeNames_ = LazyStringArrayList.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchDeleteEntityTypesRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BatchDeleteEntityTypesRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!(z2 & true)) {
                                    this.entityTypeNames_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.entityTypeNames_.add(readStringRequireUtf8);
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
                    this.entityTypeNames_ = this.entityTypeNames_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EntityTypeProto.f1656xb35a14b2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1657x4d0a6930.ensureFieldAccessorsInitialized(BatchDeleteEntityTypesRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
    public ProtocolStringList getEntityTypeNamesList() {
        return this.entityTypeNames_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
    public int getEntityTypeNamesCount() {
        return this.entityTypeNames_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
    public String getEntityTypeNames(int i) {
        return (String) this.entityTypeNames_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
    public ByteString getEntityTypeNamesBytes(int i) {
        return this.entityTypeNames_.getByteString(i);
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
        for (int i = 0; i < this.entityTypeNames_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.entityTypeNames_.getRaw(i));
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
        int i2 = 0;
        for (int i3 = 0; i3 < this.entityTypeNames_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.entityTypeNames_.getRaw(i3));
        }
        int size = computeStringSize + i2 + (getEntityTypeNamesList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatchDeleteEntityTypesRequest)) {
            return super.equals(obj);
        }
        BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest = (BatchDeleteEntityTypesRequest) obj;
        return getParent().equals(batchDeleteEntityTypesRequest.getParent()) && getEntityTypeNamesList().equals(batchDeleteEntityTypesRequest.getEntityTypeNamesList()) && this.unknownFields.equals(batchDeleteEntityTypesRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (getEntityTypeNamesCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getEntityTypeNamesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static BatchDeleteEntityTypesRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchDeleteEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchDeleteEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchDeleteEntityTypesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchDeleteEntityTypesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchDeleteEntityTypesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchDeleteEntityTypesRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchDeleteEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchDeleteEntityTypesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchDeleteEntityTypesRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchDeleteEntityTypesRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchDeleteEntityTypesRequestOrBuilder {
        private int bitField0_;
        private LazyStringList entityTypeNames_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1656xb35a14b2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1657x4d0a6930.ensureFieldAccessorsInitialized(BatchDeleteEntityTypesRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.entityTypeNames_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.entityTypeNames_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BatchDeleteEntityTypesRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.entityTypeNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1656xb35a14b2;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchDeleteEntityTypesRequest getDefaultInstanceForType() {
            return BatchDeleteEntityTypesRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchDeleteEntityTypesRequest build() {
            BatchDeleteEntityTypesRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchDeleteEntityTypesRequest buildPartial() {
            BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest = new BatchDeleteEntityTypesRequest(this);
            int i = this.bitField0_;
            batchDeleteEntityTypesRequest.parent_ = this.parent_;
            if ((this.bitField0_ & 1) != 0) {
                this.entityTypeNames_ = this.entityTypeNames_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            batchDeleteEntityTypesRequest.entityTypeNames_ = this.entityTypeNames_;
            onBuilt();
            return batchDeleteEntityTypesRequest;
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
            if (message instanceof BatchDeleteEntityTypesRequest) {
                return mergeFrom((BatchDeleteEntityTypesRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest) {
            if (batchDeleteEntityTypesRequest == BatchDeleteEntityTypesRequest.getDefaultInstance()) {
                return this;
            }
            if (!batchDeleteEntityTypesRequest.getParent().isEmpty()) {
                this.parent_ = batchDeleteEntityTypesRequest.parent_;
                onChanged();
            }
            if (!batchDeleteEntityTypesRequest.entityTypeNames_.isEmpty()) {
                if (this.entityTypeNames_.isEmpty()) {
                    this.entityTypeNames_ = batchDeleteEntityTypesRequest.entityTypeNames_;
                    this.bitField0_ &= -2;
                } else {
                    ensureEntityTypeNamesIsMutable();
                    this.entityTypeNames_.addAll(batchDeleteEntityTypesRequest.entityTypeNames_);
                }
                onChanged();
            }
            mergeUnknownFields(batchDeleteEntityTypesRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest = null;
            try {
                try {
                    BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest2 = (BatchDeleteEntityTypesRequest) BatchDeleteEntityTypesRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchDeleteEntityTypesRequest2 != null) {
                        mergeFrom(batchDeleteEntityTypesRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchDeleteEntityTypesRequest batchDeleteEntityTypesRequest3 = (BatchDeleteEntityTypesRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchDeleteEntityTypesRequest = batchDeleteEntityTypesRequest3;
                        if (batchDeleteEntityTypesRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchDeleteEntityTypesRequest != null) {
                    mergeFrom(batchDeleteEntityTypesRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
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
            this.parent_ = BatchDeleteEntityTypesRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                BatchDeleteEntityTypesRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureEntityTypeNamesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entityTypeNames_ = new LazyStringArrayList(this.entityTypeNames_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
        public ProtocolStringList getEntityTypeNamesList() {
            return this.entityTypeNames_.getUnmodifiableView();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
        public int getEntityTypeNamesCount() {
            return this.entityTypeNames_.size();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
        public String getEntityTypeNames(int i) {
            return (String) this.entityTypeNames_.get(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchDeleteEntityTypesRequestOrBuilder
        public ByteString getEntityTypeNamesBytes(int i) {
            return this.entityTypeNames_.getByteString(i);
        }

        public Builder setEntityTypeNames(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureEntityTypeNamesIsMutable();
            this.entityTypeNames_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addEntityTypeNames(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureEntityTypeNamesIsMutable();
            this.entityTypeNames_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllEntityTypeNames(Iterable<String> iterable) {
            ensureEntityTypeNamesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.entityTypeNames_);
            onChanged();
            return this;
        }

        public Builder clearEntityTypeNames() {
            this.entityTypeNames_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addEntityTypeNamesBytes(ByteString byteString) {
            if (byteString != null) {
                BatchDeleteEntityTypesRequest.checkByteStringIsUtf8(byteString);
                ensureEntityTypeNamesIsMutable();
                this.entityTypeNames_.add(byteString);
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

    public static BatchDeleteEntityTypesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchDeleteEntityTypesRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchDeleteEntityTypesRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchDeleteEntityTypesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
