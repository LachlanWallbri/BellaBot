package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Document;
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
public final class ListDocumentsResponse extends GeneratedMessageV3 implements ListDocumentsResponseOrBuilder {
    public static final int DOCUMENTS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<Document> documents_;
    private byte memoizedIsInitialized;
    private volatile Object nextPageToken_;
    private static final ListDocumentsResponse DEFAULT_INSTANCE = new ListDocumentsResponse();
    private static final Parser<ListDocumentsResponse> PARSER = new AbstractParser<ListDocumentsResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.ListDocumentsResponse.1
        @Override // com.google.protobuf.Parser
        public ListDocumentsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListDocumentsResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private ListDocumentsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListDocumentsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.documents_ = Collections.emptyList();
        this.nextPageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListDocumentsResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListDocumentsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.documents_ = new ArrayList();
                                z2 |= true;
                            }
                            this.documents_.add(codedInputStream.readMessage(Document.parser(), extensionRegistryLite));
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
                    this.documents_ = Collections.unmodifiableList(this.documents_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentProto.f1646x878ad635;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1647x67eef7b3.ensureFieldAccessorsInitialized(ListDocumentsResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
    public List<Document> getDocumentsList() {
        return this.documents_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
    public List<? extends DocumentOrBuilder> getDocumentsOrBuilderList() {
        return this.documents_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
    public int getDocumentsCount() {
        return this.documents_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
    public Document getDocuments(int i) {
        return this.documents_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
    public DocumentOrBuilder getDocumentsOrBuilder(int i) {
        return this.documents_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
    public String getNextPageToken() {
        Object obj = this.nextPageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.nextPageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
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
        for (int i = 0; i < this.documents_.size(); i++) {
            codedOutputStream.writeMessage(1, this.documents_.get(i));
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
        for (int i3 = 0; i3 < this.documents_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.documents_.get(i3));
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
        if (!(obj instanceof ListDocumentsResponse)) {
            return super.equals(obj);
        }
        ListDocumentsResponse listDocumentsResponse = (ListDocumentsResponse) obj;
        return getDocumentsList().equals(listDocumentsResponse.getDocumentsList()) && getNextPageToken().equals(listDocumentsResponse.getNextPageToken()) && this.unknownFields.equals(listDocumentsResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getDocumentsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getDocumentsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getNextPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListDocumentsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListDocumentsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListDocumentsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListDocumentsResponse listDocumentsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listDocumentsResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListDocumentsResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> documentsBuilder_;
        private List<Document> documents_;
        private Object nextPageToken_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1646x878ad635;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1647x67eef7b3.ensureFieldAccessorsInitialized(ListDocumentsResponse.class, Builder.class);
        }

        private Builder() {
            this.documents_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.documents_ = Collections.emptyList();
            this.nextPageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListDocumentsResponse.alwaysUseFieldBuilders) {
                getDocumentsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.documents_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.nextPageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentProto.f1646x878ad635;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListDocumentsResponse getDefaultInstanceForType() {
            return ListDocumentsResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListDocumentsResponse build() {
            ListDocumentsResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListDocumentsResponse buildPartial() {
            ListDocumentsResponse listDocumentsResponse = new ListDocumentsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.documents_ = Collections.unmodifiableList(this.documents_);
                    this.bitField0_ &= -2;
                }
                listDocumentsResponse.documents_ = this.documents_;
            } else {
                listDocumentsResponse.documents_ = repeatedFieldBuilderV3.build();
            }
            listDocumentsResponse.nextPageToken_ = this.nextPageToken_;
            onBuilt();
            return listDocumentsResponse;
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
            if (message instanceof ListDocumentsResponse) {
                return mergeFrom((ListDocumentsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListDocumentsResponse listDocumentsResponse) {
            if (listDocumentsResponse == ListDocumentsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.documentsBuilder_ == null) {
                if (!listDocumentsResponse.documents_.isEmpty()) {
                    if (this.documents_.isEmpty()) {
                        this.documents_ = listDocumentsResponse.documents_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureDocumentsIsMutable();
                        this.documents_.addAll(listDocumentsResponse.documents_);
                    }
                    onChanged();
                }
            } else if (!listDocumentsResponse.documents_.isEmpty()) {
                if (!this.documentsBuilder_.isEmpty()) {
                    this.documentsBuilder_.addAllMessages(listDocumentsResponse.documents_);
                } else {
                    this.documentsBuilder_.dispose();
                    this.documentsBuilder_ = null;
                    this.documents_ = listDocumentsResponse.documents_;
                    this.bitField0_ &= -2;
                    this.documentsBuilder_ = ListDocumentsResponse.alwaysUseFieldBuilders ? getDocumentsFieldBuilder() : null;
                }
            }
            if (!listDocumentsResponse.getNextPageToken().isEmpty()) {
                this.nextPageToken_ = listDocumentsResponse.nextPageToken_;
                onChanged();
            }
            mergeUnknownFields(listDocumentsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListDocumentsResponse listDocumentsResponse = null;
            try {
                try {
                    ListDocumentsResponse listDocumentsResponse2 = (ListDocumentsResponse) ListDocumentsResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listDocumentsResponse2 != null) {
                        mergeFrom(listDocumentsResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListDocumentsResponse listDocumentsResponse3 = (ListDocumentsResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listDocumentsResponse = listDocumentsResponse3;
                        if (listDocumentsResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listDocumentsResponse != null) {
                    mergeFrom(listDocumentsResponse);
                }
                throw th;
            }
        }

        private void ensureDocumentsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.documents_ = new ArrayList(this.documents_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
        public List<Document> getDocumentsList() {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.documents_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
        public int getDocumentsCount() {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.documents_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
        public Document getDocuments(int i) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.documents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setDocuments(int i, Document document) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, document);
            } else {
                if (document == null) {
                    throw new NullPointerException();
                }
                ensureDocumentsIsMutable();
                this.documents_.set(i, document);
                onChanged();
            }
            return this;
        }

        public Builder setDocuments(int i, Document.Builder builder) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDocumentsIsMutable();
                this.documents_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addDocuments(Document document) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(document);
            } else {
                if (document == null) {
                    throw new NullPointerException();
                }
                ensureDocumentsIsMutable();
                this.documents_.add(document);
                onChanged();
            }
            return this;
        }

        public Builder addDocuments(int i, Document document) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, document);
            } else {
                if (document == null) {
                    throw new NullPointerException();
                }
                ensureDocumentsIsMutable();
                this.documents_.add(i, document);
                onChanged();
            }
            return this;
        }

        public Builder addDocuments(Document.Builder builder) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDocumentsIsMutable();
                this.documents_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addDocuments(int i, Document.Builder builder) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDocumentsIsMutable();
                this.documents_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllDocuments(Iterable<? extends Document> iterable) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDocumentsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.documents_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearDocuments() {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.documents_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeDocuments(int i) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureDocumentsIsMutable();
                this.documents_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Document.Builder getDocumentsBuilder(int i) {
            return getDocumentsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
        public DocumentOrBuilder getDocumentsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.documents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
        public List<? extends DocumentOrBuilder> getDocumentsOrBuilderList() {
            RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> repeatedFieldBuilderV3 = this.documentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.documents_);
        }

        public Document.Builder addDocumentsBuilder() {
            return getDocumentsFieldBuilder().addBuilder(Document.getDefaultInstance());
        }

        public Document.Builder addDocumentsBuilder(int i) {
            return getDocumentsFieldBuilder().addBuilder(i, Document.getDefaultInstance());
        }

        public List<Document.Builder> getDocumentsBuilderList() {
            return getDocumentsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Document, Document.Builder, DocumentOrBuilder> getDocumentsFieldBuilder() {
            if (this.documentsBuilder_ == null) {
                this.documentsBuilder_ = new RepeatedFieldBuilderV3<>(this.documents_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.documents_ = null;
            }
            return this.documentsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
        public String getNextPageToken() {
            Object obj = this.nextPageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.nextPageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsResponseOrBuilder
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
            this.nextPageToken_ = ListDocumentsResponse.getDefaultInstance().getNextPageToken();
            onChanged();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListDocumentsResponse.checkByteStringIsUtf8(byteString);
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

    public static ListDocumentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListDocumentsResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListDocumentsResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListDocumentsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
