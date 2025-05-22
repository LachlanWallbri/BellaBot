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
public final class ListIntentsRequest extends GeneratedMessageV3 implements ListIntentsRequestOrBuilder {
    public static final int INTENT_VIEW_FIELD_NUMBER = 3;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 2;
    public static final int PAGE_SIZE_FIELD_NUMBER = 4;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 5;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int intentView_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private int pageSize_;
    private volatile Object pageToken_;
    private volatile Object parent_;
    private static final ListIntentsRequest DEFAULT_INSTANCE = new ListIntentsRequest();
    private static final Parser<ListIntentsRequest> PARSER = new AbstractParser<ListIntentsRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.ListIntentsRequest.1
        @Override // com.google.protobuf.Parser
        public ListIntentsRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListIntentsRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private ListIntentsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListIntentsRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.languageCode_ = "";
        this.intentView_ = 0;
        this.pageToken_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListIntentsRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ListIntentsRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.languageCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.intentView_ = codedInputStream.readEnum();
                            } else if (readTag == 32) {
                                this.pageSize_ = codedInputStream.readInt32();
                            } else if (readTag == 42) {
                                this.pageToken_ = codedInputStream.readStringRequireUtf8();
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
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return IntentProto.f1799x1e27faf6;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IntentProto.f1800x78bfcb74.ensureFieldAccessorsInitialized(ListIntentsRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public int getIntentViewValue() {
        return this.intentView_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public IntentView getIntentView() {
        IntentView valueOf = IntentView.valueOf(this.intentView_);
        return valueOf == null ? IntentView.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public int getPageSize() {
        return this.pageSize_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
    public String getPageToken() {
        Object obj = this.pageToken_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.pageToken_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
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
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.languageCode_);
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.intentView_);
        }
        int i = this.pageSize_;
        if (i != 0) {
            codedOutputStream.writeInt32(4, i);
        }
        if (!getPageTokenBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.pageToken_);
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
        if (!getLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.languageCode_);
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.intentView_);
        }
        int i2 = this.pageSize_;
        if (i2 != 0) {
            computeStringSize += CodedOutputStream.computeInt32Size(4, i2);
        }
        if (!getPageTokenBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.pageToken_);
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
        if (!(obj instanceof ListIntentsRequest)) {
            return super.equals(obj);
        }
        ListIntentsRequest listIntentsRequest = (ListIntentsRequest) obj;
        return getParent().equals(listIntentsRequest.getParent()) && getLanguageCode().equals(listIntentsRequest.getLanguageCode()) && this.intentView_ == listIntentsRequest.intentView_ && getPageSize() == listIntentsRequest.getPageSize() && getPageToken().equals(listIntentsRequest.getPageToken()) && this.unknownFields.equals(listIntentsRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode()) * 37) + 2) * 53) + getLanguageCode().hashCode()) * 37) + 3) * 53) + this.intentView_) * 37) + 4) * 53) + getPageSize()) * 37) + 5) * 53) + getPageToken().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static ListIntentsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListIntentsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListIntentsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListIntentsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListIntentsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListIntentsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListIntentsRequest parseFrom(InputStream inputStream) throws IOException {
        return (ListIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListIntentsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListIntentsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListIntentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListIntentsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListIntentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListIntentsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListIntentsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListIntentsRequest listIntentsRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listIntentsRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListIntentsRequestOrBuilder {
        private int intentView_;
        private Object languageCode_;
        private int pageSize_;
        private Object pageToken_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return IntentProto.f1799x1e27faf6;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IntentProto.f1800x78bfcb74.ensureFieldAccessorsInitialized(ListIntentsRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            this.pageToken_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ListIntentsRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            this.pageSize_ = 0;
            this.pageToken_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return IntentProto.f1799x1e27faf6;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListIntentsRequest getDefaultInstanceForType() {
            return ListIntentsRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListIntentsRequest build() {
            ListIntentsRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListIntentsRequest buildPartial() {
            ListIntentsRequest listIntentsRequest = new ListIntentsRequest(this);
            listIntentsRequest.parent_ = this.parent_;
            listIntentsRequest.languageCode_ = this.languageCode_;
            listIntentsRequest.intentView_ = this.intentView_;
            listIntentsRequest.pageSize_ = this.pageSize_;
            listIntentsRequest.pageToken_ = this.pageToken_;
            onBuilt();
            return listIntentsRequest;
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
            if (message instanceof ListIntentsRequest) {
                return mergeFrom((ListIntentsRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListIntentsRequest listIntentsRequest) {
            if (listIntentsRequest == ListIntentsRequest.getDefaultInstance()) {
                return this;
            }
            if (!listIntentsRequest.getParent().isEmpty()) {
                this.parent_ = listIntentsRequest.parent_;
                onChanged();
            }
            if (!listIntentsRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = listIntentsRequest.languageCode_;
                onChanged();
            }
            if (listIntentsRequest.intentView_ != 0) {
                setIntentViewValue(listIntentsRequest.getIntentViewValue());
            }
            if (listIntentsRequest.getPageSize() != 0) {
                setPageSize(listIntentsRequest.getPageSize());
            }
            if (!listIntentsRequest.getPageToken().isEmpty()) {
                this.pageToken_ = listIntentsRequest.pageToken_;
                onChanged();
            }
            mergeUnknownFields(listIntentsRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListIntentsRequest listIntentsRequest = null;
            try {
                try {
                    ListIntentsRequest listIntentsRequest2 = (ListIntentsRequest) ListIntentsRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listIntentsRequest2 != null) {
                        mergeFrom(listIntentsRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListIntentsRequest listIntentsRequest3 = (ListIntentsRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listIntentsRequest = listIntentsRequest3;
                        if (listIntentsRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listIntentsRequest != null) {
                    mergeFrom(listIntentsRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
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
            this.parent_ = ListIntentsRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                ListIntentsRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
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
            this.languageCode_ = ListIntentsRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                ListIntentsRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
        public int getIntentViewValue() {
            return this.intentView_;
        }

        public Builder setIntentViewValue(int i) {
            this.intentView_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
        public IntentView getIntentView() {
            IntentView valueOf = IntentView.valueOf(this.intentView_);
            return valueOf == null ? IntentView.UNRECOGNIZED : valueOf;
        }

        public Builder setIntentView(IntentView intentView) {
            if (intentView == null) {
                throw new NullPointerException();
            }
            this.intentView_ = intentView.getNumber();
            onChanged();
            return this;
        }

        public Builder clearIntentView() {
            this.intentView_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
        public String getPageToken() {
            Object obj = this.pageToken_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.pageToken_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ListIntentsRequestOrBuilder
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
            this.pageToken_ = ListIntentsRequest.getDefaultInstance().getPageToken();
            onChanged();
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            if (byteString != null) {
                ListIntentsRequest.checkByteStringIsUtf8(byteString);
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

    public static ListIntentsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListIntentsRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListIntentsRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListIntentsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
