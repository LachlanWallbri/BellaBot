package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.KnowledgeBase;
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
public final class CreateKnowledgeBaseRequest extends GeneratedMessageV3 implements CreateKnowledgeBaseRequestOrBuilder {
    public static final int KNOWLEDGE_BASE_FIELD_NUMBER = 2;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private KnowledgeBase knowledgeBase_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final CreateKnowledgeBaseRequest DEFAULT_INSTANCE = new CreateKnowledgeBaseRequest();
    private static final Parser<CreateKnowledgeBaseRequest> PARSER = new AbstractParser<CreateKnowledgeBaseRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequest.1
        @Override // com.google.protobuf.Parser
        public CreateKnowledgeBaseRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CreateKnowledgeBaseRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private CreateKnowledgeBaseRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CreateKnowledgeBaseRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new CreateKnowledgeBaseRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateKnowledgeBaseRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                KnowledgeBase.Builder builder = this.knowledgeBase_ != null ? this.knowledgeBase_.toBuilder() : null;
                                this.knowledgeBase_ = (KnowledgeBase) codedInputStream.readMessage(KnowledgeBase.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.knowledgeBase_);
                                    this.knowledgeBase_ = builder.buildPartial();
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
        return KnowledgeBaseProto.f1805x27617870;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return KnowledgeBaseProto.f1806x4bc92eee.ensureFieldAccessorsInitialized(CreateKnowledgeBaseRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
    public boolean hasKnowledgeBase() {
        return this.knowledgeBase_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
    public KnowledgeBase getKnowledgeBase() {
        KnowledgeBase knowledgeBase = this.knowledgeBase_;
        return knowledgeBase == null ? KnowledgeBase.getDefaultInstance() : knowledgeBase;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
    public KnowledgeBaseOrBuilder getKnowledgeBaseOrBuilder() {
        return getKnowledgeBase();
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
        if (this.knowledgeBase_ != null) {
            codedOutputStream.writeMessage(2, getKnowledgeBase());
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
        if (this.knowledgeBase_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getKnowledgeBase());
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
        if (!(obj instanceof CreateKnowledgeBaseRequest)) {
            return super.equals(obj);
        }
        CreateKnowledgeBaseRequest createKnowledgeBaseRequest = (CreateKnowledgeBaseRequest) obj;
        if (getParent().equals(createKnowledgeBaseRequest.getParent()) && hasKnowledgeBase() == createKnowledgeBaseRequest.hasKnowledgeBase()) {
            return (!hasKnowledgeBase() || getKnowledgeBase().equals(createKnowledgeBaseRequest.getKnowledgeBase())) && this.unknownFields.equals(createKnowledgeBaseRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (hasKnowledgeBase()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getKnowledgeBase().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static CreateKnowledgeBaseRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static CreateKnowledgeBaseRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CreateKnowledgeBaseRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static CreateKnowledgeBaseRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CreateKnowledgeBaseRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static CreateKnowledgeBaseRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CreateKnowledgeBaseRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CreateKnowledgeBaseRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateKnowledgeBaseRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateKnowledgeBaseRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CreateKnowledgeBaseRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateKnowledgeBaseRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateKnowledgeBaseRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CreateKnowledgeBaseRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateKnowledgeBaseRequest createKnowledgeBaseRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(createKnowledgeBaseRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateKnowledgeBaseRequestOrBuilder {
        private SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> knowledgeBaseBuilder_;
        private KnowledgeBase knowledgeBase_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return KnowledgeBaseProto.f1805x27617870;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return KnowledgeBaseProto.f1806x4bc92eee.ensureFieldAccessorsInitialized(CreateKnowledgeBaseRequest.class, Builder.class);
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
            boolean unused = CreateKnowledgeBaseRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            if (this.knowledgeBaseBuilder_ == null) {
                this.knowledgeBase_ = null;
            } else {
                this.knowledgeBase_ = null;
                this.knowledgeBaseBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return KnowledgeBaseProto.f1805x27617870;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CreateKnowledgeBaseRequest getDefaultInstanceForType() {
            return CreateKnowledgeBaseRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateKnowledgeBaseRequest build() {
            CreateKnowledgeBaseRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateKnowledgeBaseRequest buildPartial() {
            CreateKnowledgeBaseRequest createKnowledgeBaseRequest = new CreateKnowledgeBaseRequest(this);
            createKnowledgeBaseRequest.parent_ = this.parent_;
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                createKnowledgeBaseRequest.knowledgeBase_ = this.knowledgeBase_;
            } else {
                createKnowledgeBaseRequest.knowledgeBase_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return createKnowledgeBaseRequest;
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
            if (message instanceof CreateKnowledgeBaseRequest) {
                return mergeFrom((CreateKnowledgeBaseRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CreateKnowledgeBaseRequest createKnowledgeBaseRequest) {
            if (createKnowledgeBaseRequest == CreateKnowledgeBaseRequest.getDefaultInstance()) {
                return this;
            }
            if (!createKnowledgeBaseRequest.getParent().isEmpty()) {
                this.parent_ = createKnowledgeBaseRequest.parent_;
                onChanged();
            }
            if (createKnowledgeBaseRequest.hasKnowledgeBase()) {
                mergeKnowledgeBase(createKnowledgeBaseRequest.getKnowledgeBase());
            }
            mergeUnknownFields(createKnowledgeBaseRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CreateKnowledgeBaseRequest createKnowledgeBaseRequest = null;
            try {
                try {
                    CreateKnowledgeBaseRequest createKnowledgeBaseRequest2 = (CreateKnowledgeBaseRequest) CreateKnowledgeBaseRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (createKnowledgeBaseRequest2 != null) {
                        mergeFrom(createKnowledgeBaseRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CreateKnowledgeBaseRequest createKnowledgeBaseRequest3 = (CreateKnowledgeBaseRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        createKnowledgeBaseRequest = createKnowledgeBaseRequest3;
                        if (createKnowledgeBaseRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (createKnowledgeBaseRequest != null) {
                    mergeFrom(createKnowledgeBaseRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
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
            this.parent_ = CreateKnowledgeBaseRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                CreateKnowledgeBaseRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
        public boolean hasKnowledgeBase() {
            return (this.knowledgeBaseBuilder_ == null && this.knowledgeBase_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
        public KnowledgeBase getKnowledgeBase() {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                KnowledgeBase knowledgeBase = this.knowledgeBase_;
                return knowledgeBase == null ? KnowledgeBase.getDefaultInstance() : knowledgeBase;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setKnowledgeBase(KnowledgeBase knowledgeBase) {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(knowledgeBase);
            } else {
                if (knowledgeBase == null) {
                    throw new NullPointerException();
                }
                this.knowledgeBase_ = knowledgeBase;
                onChanged();
            }
            return this;
        }

        public Builder setKnowledgeBase(KnowledgeBase.Builder builder) {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.knowledgeBase_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeKnowledgeBase(KnowledgeBase knowledgeBase) {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                KnowledgeBase knowledgeBase2 = this.knowledgeBase_;
                if (knowledgeBase2 != null) {
                    this.knowledgeBase_ = KnowledgeBase.newBuilder(knowledgeBase2).mergeFrom(knowledgeBase).buildPartial();
                } else {
                    this.knowledgeBase_ = knowledgeBase;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(knowledgeBase);
            }
            return this;
        }

        public Builder clearKnowledgeBase() {
            if (this.knowledgeBaseBuilder_ == null) {
                this.knowledgeBase_ = null;
                onChanged();
            } else {
                this.knowledgeBase_ = null;
                this.knowledgeBaseBuilder_ = null;
            }
            return this;
        }

        public KnowledgeBase.Builder getKnowledgeBaseBuilder() {
            onChanged();
            return getKnowledgeBaseFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateKnowledgeBaseRequestOrBuilder
        public KnowledgeBaseOrBuilder getKnowledgeBaseOrBuilder() {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            KnowledgeBase knowledgeBase = this.knowledgeBase_;
            return knowledgeBase == null ? KnowledgeBase.getDefaultInstance() : knowledgeBase;
        }

        private SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> getKnowledgeBaseFieldBuilder() {
            if (this.knowledgeBaseBuilder_ == null) {
                this.knowledgeBaseBuilder_ = new SingleFieldBuilderV3<>(getKnowledgeBase(), getParentForChildren(), isClean());
                this.knowledgeBase_ = null;
            }
            return this.knowledgeBaseBuilder_;
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

    public static CreateKnowledgeBaseRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateKnowledgeBaseRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<CreateKnowledgeBaseRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public CreateKnowledgeBaseRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
