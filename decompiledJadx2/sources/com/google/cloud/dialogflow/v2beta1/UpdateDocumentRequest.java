package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Document;
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
/* loaded from: classes3.dex */
public final class UpdateDocumentRequest extends GeneratedMessageV3 implements UpdateDocumentRequestOrBuilder {
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private Document document_;
    private byte memoizedIsInitialized;
    private FieldMask updateMask_;
    private static final UpdateDocumentRequest DEFAULT_INSTANCE = new UpdateDocumentRequest();
    private static final Parser<UpdateDocumentRequest> PARSER = new AbstractParser<UpdateDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequest.1
        @Override // com.google.protobuf.Parser
        public UpdateDocumentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpdateDocumentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private UpdateDocumentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpdateDocumentRequest() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new UpdateDocumentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateDocumentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            Document.Builder builder = this.document_ != null ? this.document_.toBuilder() : null;
                            this.document_ = (Document) codedInputStream.readMessage(Document.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.document_);
                                this.document_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
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
        return DocumentProto.f1650x315d1d65;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1651xe75f0ee3.ensureFieldAccessorsInitialized(UpdateDocumentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
    public Document getDocument() {
        Document document = this.document_;
        return document == null ? Document.getDefaultInstance() : document;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
    public DocumentOrBuilder getDocumentOrBuilder() {
        return getDocument();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
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
        if (this.document_ != null) {
            codedOutputStream.writeMessage(1, getDocument());
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(2, getUpdateMask());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.document_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getDocument()) : 0;
        if (this.updateMask_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getUpdateMask());
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
        if (!(obj instanceof UpdateDocumentRequest)) {
            return super.equals(obj);
        }
        UpdateDocumentRequest updateDocumentRequest = (UpdateDocumentRequest) obj;
        if (hasDocument() != updateDocumentRequest.hasDocument()) {
            return false;
        }
        if ((!hasDocument() || getDocument().equals(updateDocumentRequest.getDocument())) && hasUpdateMask() == updateDocumentRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(updateDocumentRequest.getUpdateMask())) && this.unknownFields.equals(updateDocumentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasDocument()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getDocument().hashCode();
        }
        if (hasUpdateMask()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getUpdateMask().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static UpdateDocumentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static UpdateDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpdateDocumentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static UpdateDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpdateDocumentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static UpdateDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpdateDocumentRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpdateDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateDocumentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateDocumentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpdateDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateDocumentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateDocumentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpdateDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateDocumentRequest updateDocumentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateDocumentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateDocumentRequestOrBuilder {
        private SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> documentBuilder_;
        private Document document_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1650x315d1d65;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1651xe75f0ee3.ensureFieldAccessorsInitialized(UpdateDocumentRequest.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpdateDocumentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.documentBuilder_ == null) {
                this.document_ = null;
            } else {
                this.document_ = null;
                this.documentBuilder_ = null;
            }
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
            return DocumentProto.f1650x315d1d65;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateDocumentRequest getDefaultInstanceForType() {
            return UpdateDocumentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateDocumentRequest build() {
            UpdateDocumentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateDocumentRequest buildPartial() {
            UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest(this);
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 == null) {
                updateDocumentRequest.document_ = this.document_;
            } else {
                updateDocumentRequest.document_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                updateDocumentRequest.updateMask_ = this.updateMask_;
            } else {
                updateDocumentRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return updateDocumentRequest;
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
            if (message instanceof UpdateDocumentRequest) {
                return mergeFrom((UpdateDocumentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpdateDocumentRequest updateDocumentRequest) {
            if (updateDocumentRequest == UpdateDocumentRequest.getDefaultInstance()) {
                return this;
            }
            if (updateDocumentRequest.hasDocument()) {
                mergeDocument(updateDocumentRequest.getDocument());
            }
            if (updateDocumentRequest.hasUpdateMask()) {
                mergeUpdateMask(updateDocumentRequest.getUpdateMask());
            }
            mergeUnknownFields(updateDocumentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            UpdateDocumentRequest updateDocumentRequest = null;
            try {
                try {
                    UpdateDocumentRequest updateDocumentRequest2 = (UpdateDocumentRequest) UpdateDocumentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (updateDocumentRequest2 != null) {
                        mergeFrom(updateDocumentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    UpdateDocumentRequest updateDocumentRequest3 = (UpdateDocumentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        updateDocumentRequest = updateDocumentRequest3;
                        if (updateDocumentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (updateDocumentRequest != null) {
                    mergeFrom(updateDocumentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
        public boolean hasDocument() {
            return (this.documentBuilder_ == null && this.document_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
        public Document getDocument() {
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Document document = this.document_;
                return document == null ? Document.getDefaultInstance() : document;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setDocument(Document document) {
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(document);
            } else {
                if (document == null) {
                    throw new NullPointerException();
                }
                this.document_ = document;
                onChanged();
            }
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.document_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDocument(Document document) {
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Document document2 = this.document_;
                if (document2 != null) {
                    this.document_ = Document.newBuilder(document2).mergeFrom(document).buildPartial();
                } else {
                    this.document_ = document;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(document);
            }
            return this;
        }

        public Builder clearDocument() {
            if (this.documentBuilder_ == null) {
                this.document_ = null;
                onChanged();
            } else {
                this.document_ = null;
                this.documentBuilder_ = null;
            }
            return this;
        }

        public Document.Builder getDocumentBuilder() {
            onChanged();
            return getDocumentFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
        public DocumentOrBuilder getDocumentOrBuilder() {
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Document document = this.document_;
            return document == null ? Document.getDefaultInstance() : document;
        }

        private SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> getDocumentFieldBuilder() {
            if (this.documentBuilder_ == null) {
                this.documentBuilder_ = new SingleFieldBuilderV3<>(getDocument(), getParentForChildren(), isClean());
                this.document_ = null;
            }
            return this.documentBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateDocumentRequestOrBuilder
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

    public static UpdateDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateDocumentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<UpdateDocumentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public UpdateDocumentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
