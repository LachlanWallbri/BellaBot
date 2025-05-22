package com.google.cloud.dialogflow.v2beta1;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ListDocumentsRequest extends GeneratedMessageV3 implements ListDocumentsRequestOrBuilder {
    public static final int PAGE_SIZE_FIELD_NUMBER = 2;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int pageSize_;
    private volatile Object pageToken_;
    private volatile Object parent_;
    private static final ListDocumentsRequest DEFAULT_INSTANCE = new ListDocumentsRequest();
    private static final Parser<ListDocumentsRequest> PARSER = new AbstractParser<ListDocumentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.ListDocumentsRequest.1
        @Override // com.google.protobuf.Parser
        public ListDocumentsRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListDocumentsRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private ListDocumentsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListDocumentsRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.pageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListDocumentsRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListDocumentsRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        } else if (readTag == 16) {
                            this.pageSize_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            this.pageToken_ = codedInputStream.readStringRequireUtf8();
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
        return DocumentProto.f1644x7fcf7217;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1645x2fd29195.ensureFieldAccessorsInitialized(ListDocumentsRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
    public int getPageSize() {
        return this.pageSize_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
    public String getPageToken() {
        Object obj = this.pageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.pageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
    public ByteString getPageTokenBytes() {
        Object obj = this.pageToken_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.pageToken_ = copyFromUtf8;
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
        int i = this.pageSize_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (!getPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.pageToken_);
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
        int i2 = this.pageSize_;
        if (i2 != 0) {
            computeStringSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (!getPageTokenBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.pageToken_);
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
        if (!(obj instanceof ListDocumentsRequest)) {
            return super.equals(obj);
        }
        ListDocumentsRequest listDocumentsRequest = (ListDocumentsRequest) obj;
        return getParent().equals(listDocumentsRequest.getParent()) && getPageSize() == listDocumentsRequest.getPageSize() && getPageToken().equals(listDocumentsRequest.getPageToken()) && this.unknownFields.equals(listDocumentsRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode()) * 37) + 2) * 53) + getPageSize()) * 37) + 3) * 53) + getPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static ListDocumentsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListDocumentsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListDocumentsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListDocumentsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListDocumentsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListDocumentsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListDocumentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListDocumentsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListDocumentsRequest listDocumentsRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listDocumentsRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListDocumentsRequestOrBuilder {
        private int pageSize_;
        private Object pageToken_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1644x7fcf7217;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1645x2fd29195.ensureFieldAccessorsInitialized(ListDocumentsRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ListDocumentsRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.pageSize_ = 0;
            this.pageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentProto.f1644x7fcf7217;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListDocumentsRequest getDefaultInstanceForType() {
            return ListDocumentsRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListDocumentsRequest build() {
            ListDocumentsRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListDocumentsRequest buildPartial() {
            ListDocumentsRequest listDocumentsRequest = new ListDocumentsRequest(this);
            listDocumentsRequest.parent_ = this.parent_;
            listDocumentsRequest.pageSize_ = this.pageSize_;
            listDocumentsRequest.pageToken_ = this.pageToken_;
            onBuilt();
            return listDocumentsRequest;
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
            if (message instanceof ListDocumentsRequest) {
                return mergeFrom((ListDocumentsRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListDocumentsRequest listDocumentsRequest) {
            if (listDocumentsRequest == ListDocumentsRequest.getDefaultInstance()) {
                return this;
            }
            if (!listDocumentsRequest.getParent().isEmpty()) {
                this.parent_ = listDocumentsRequest.parent_;
                onChanged();
            }
            if (listDocumentsRequest.getPageSize() != 0) {
                setPageSize(listDocumentsRequest.getPageSize());
            }
            if (!listDocumentsRequest.getPageToken().isEmpty()) {
                this.pageToken_ = listDocumentsRequest.pageToken_;
                onChanged();
            }
            mergeUnknownFields(listDocumentsRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListDocumentsRequest listDocumentsRequest = null;
            try {
                try {
                    ListDocumentsRequest listDocumentsRequest2 = (ListDocumentsRequest) ListDocumentsRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listDocumentsRequest2 != null) {
                        mergeFrom(listDocumentsRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListDocumentsRequest listDocumentsRequest3 = (ListDocumentsRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listDocumentsRequest = listDocumentsRequest3;
                        if (listDocumentsRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listDocumentsRequest != null) {
                    mergeFrom(listDocumentsRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
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
            this.parent_ = ListDocumentsRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                ListDocumentsRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
        public int getPageSize() {
            return this.pageSize_;
        }

        public Builder setPageSize(int i) {
            this.pageSize_ = i;
            onChanged();
            return this;
        }

        public Builder clearPageSize() {
            this.pageSize_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
        public String getPageToken() {
            Object obj = this.pageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.pageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListDocumentsRequestOrBuilder
        public ByteString getPageTokenBytes() {
            Object obj = this.pageToken_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.pageToken_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPageToken(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.pageToken_ = str;
            onChanged();
            return this;
        }

        public Builder clearPageToken() {
            this.pageToken_ = ListDocumentsRequest.getDefaultInstance().getPageToken();
            onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListDocumentsRequest.checkByteStringIsUtf8(byteString);
                this.pageToken_ = byteString;
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

    public static ListDocumentsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListDocumentsRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListDocumentsRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListDocumentsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
