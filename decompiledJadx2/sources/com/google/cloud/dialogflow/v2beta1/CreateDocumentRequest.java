package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Document;
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
public final class CreateDocumentRequest extends GeneratedMessageV3 implements CreateDocumentRequestOrBuilder {
    public static final int DOCUMENT_FIELD_NUMBER = 2;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private Document document_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final CreateDocumentRequest DEFAULT_INSTANCE = new CreateDocumentRequest();
    private static final Parser<CreateDocumentRequest> PARSER = new AbstractParser<CreateDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.CreateDocumentRequest.1
        @Override // com.google.protobuf.Parser
        public CreateDocumentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CreateDocumentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private CreateDocumentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CreateDocumentRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new CreateDocumentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateDocumentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Document.Builder builder = this.document_ != null ? this.document_.toBuilder() : null;
                                this.document_ = (Document) codedInputStream.readMessage(Document.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.document_);
                                    this.document_ = builder.buildPartial();
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
        return DocumentProto.f1634xc876dbd8;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1635xba98aa56.ensureFieldAccessorsInitialized(CreateDocumentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
    public Document getDocument() {
        Document document = this.document_;
        return document == null ? Document.getDefaultInstance() : document;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
    public DocumentOrBuilder getDocumentOrBuilder() {
        return getDocument();
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
        if (this.document_ != null) {
            codedOutputStream.writeMessage(2, getDocument());
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
        if (this.document_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getDocument());
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
        if (!(obj instanceof CreateDocumentRequest)) {
            return super.equals(obj);
        }
        CreateDocumentRequest createDocumentRequest = (CreateDocumentRequest) obj;
        if (getParent().equals(createDocumentRequest.getParent()) && hasDocument() == createDocumentRequest.hasDocument()) {
            return (!hasDocument() || getDocument().equals(createDocumentRequest.getDocument())) && this.unknownFields.equals(createDocumentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (hasDocument()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getDocument().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static CreateDocumentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static CreateDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static CreateDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static CreateDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CreateDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CreateDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CreateDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateDocumentRequest createDocumentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(createDocumentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateDocumentRequestOrBuilder {
        private SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> documentBuilder_;
        private Document document_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1634xc876dbd8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1635xba98aa56.ensureFieldAccessorsInitialized(CreateDocumentRequest.class, Builder.class);
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
            boolean unused = CreateDocumentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            if (this.documentBuilder_ == null) {
                this.document_ = null;
            } else {
                this.document_ = null;
                this.documentBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentProto.f1634xc876dbd8;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CreateDocumentRequest getDefaultInstanceForType() {
            return CreateDocumentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateDocumentRequest build() {
            CreateDocumentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateDocumentRequest buildPartial() {
            CreateDocumentRequest createDocumentRequest = new CreateDocumentRequest(this);
            createDocumentRequest.parent_ = this.parent_;
            SingleFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> singleFieldBuilderV3 = this.documentBuilder_;
            if (singleFieldBuilderV3 == null) {
                createDocumentRequest.document_ = this.document_;
            } else {
                createDocumentRequest.document_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return createDocumentRequest;
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
            if (message instanceof CreateDocumentRequest) {
                return mergeFrom((CreateDocumentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CreateDocumentRequest createDocumentRequest) {
            if (createDocumentRequest == CreateDocumentRequest.getDefaultInstance()) {
                return this;
            }
            if (!createDocumentRequest.getParent().isEmpty()) {
                this.parent_ = createDocumentRequest.parent_;
                onChanged();
            }
            if (createDocumentRequest.hasDocument()) {
                mergeDocument(createDocumentRequest.getDocument());
            }
            mergeUnknownFields(createDocumentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CreateDocumentRequest createDocumentRequest = null;
            try {
                try {
                    CreateDocumentRequest createDocumentRequest2 = (CreateDocumentRequest) CreateDocumentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (createDocumentRequest2 != null) {
                        mergeFrom(createDocumentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CreateDocumentRequest createDocumentRequest3 = (CreateDocumentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        createDocumentRequest = createDocumentRequest3;
                        if (createDocumentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (createDocumentRequest != null) {
                    mergeFrom(createDocumentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
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
            this.parent_ = CreateDocumentRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                CreateDocumentRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
        public boolean hasDocument() {
            return (this.documentBuilder_ == null && this.document_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.CreateDocumentRequestOrBuilder
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

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static CreateDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateDocumentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<CreateDocumentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public CreateDocumentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
