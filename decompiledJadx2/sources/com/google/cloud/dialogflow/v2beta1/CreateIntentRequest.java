package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Intent;
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
public final class CreateIntentRequest extends GeneratedMessageV3 implements CreateIntentRequestOrBuilder {
    public static final int INTENT_FIELD_NUMBER = 2;
    public static final int INTENT_VIEW_FIELD_NUMBER = 4;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int intentView_;
    private Intent intent_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final CreateIntentRequest DEFAULT_INSTANCE = new CreateIntentRequest();
    private static final Parser<CreateIntentRequest> PARSER = new AbstractParser<CreateIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.CreateIntentRequest.1
        @Override // com.google.protobuf.Parser
        public CreateIntentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CreateIntentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private CreateIntentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private CreateIntentRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.languageCode_ = "";
        this.intentView_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new CreateIntentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private CreateIntentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        } else if (readTag == 18) {
                            Intent.Builder builder = this.intent_ != null ? this.intent_.toBuilder() : null;
                            this.intent_ = (Intent) codedInputStream.readMessage(Intent.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.intent_);
                                this.intent_ = builder.buildPartial();
                            }
                        } else if (readTag == 26) {
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 32) {
                            this.intentView_ = codedInputStream.readEnum();
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
        return IntentProto.f1697x5ab03a99;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IntentProto.f1698xec2bb817.ensureFieldAccessorsInitialized(CreateIntentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public boolean hasIntent() {
        return this.intent_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public Intent getIntent() {
        Intent intent = this.intent_;
        return intent == null ? Intent.getDefaultInstance() : intent;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public IntentOrBuilder getIntentOrBuilder() {
        return getIntent();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public int getIntentViewValue() {
        return this.intentView_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
    public IntentView getIntentView() {
        IntentView valueOf = IntentView.valueOf(this.intentView_);
        return valueOf == null ? IntentView.UNRECOGNIZED : valueOf;
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
        if (this.intent_ != null) {
            codedOutputStream.writeMessage(2, getIntent());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.intentView_);
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
        if (this.intent_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getIntent());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.intentView_);
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
        if (!(obj instanceof CreateIntentRequest)) {
            return super.equals(obj);
        }
        CreateIntentRequest createIntentRequest = (CreateIntentRequest) obj;
        if (getParent().equals(createIntentRequest.getParent()) && hasIntent() == createIntentRequest.hasIntent()) {
            return (!hasIntent() || getIntent().equals(createIntentRequest.getIntent())) && getLanguageCode().equals(createIntentRequest.getLanguageCode()) && this.intentView_ == createIntentRequest.intentView_ && this.unknownFields.equals(createIntentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (hasIntent()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getIntent().hashCode();
        }
        int hashCode2 = (((((((((hashCode * 37) + 3) * 53) + getLanguageCode().hashCode()) * 37) + 4) * 53) + this.intentView_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static CreateIntentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static CreateIntentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static CreateIntentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static CreateIntentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CreateIntentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static CreateIntentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CreateIntentRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static CreateIntentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateIntentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static CreateIntentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static CreateIntentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static CreateIntentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateIntentRequest createIntentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(createIntentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CreateIntentRequestOrBuilder {
        private SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> intentBuilder_;
        private int intentView_;
        private Intent intent_;
        private Object languageCode_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return IntentProto.f1697x5ab03a99;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IntentProto.f1698xec2bb817.ensureFieldAccessorsInitialized(CreateIntentRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = CreateIntentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            if (this.intentBuilder_ == null) {
                this.intent_ = null;
            } else {
                this.intent_ = null;
                this.intentBuilder_ = null;
            }
            this.languageCode_ = "";
            this.intentView_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return IntentProto.f1697x5ab03a99;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CreateIntentRequest getDefaultInstanceForType() {
            return CreateIntentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateIntentRequest build() {
            CreateIntentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public CreateIntentRequest buildPartial() {
            CreateIntentRequest createIntentRequest = new CreateIntentRequest(this);
            createIntentRequest.parent_ = this.parent_;
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                createIntentRequest.intent_ = this.intent_;
            } else {
                createIntentRequest.intent_ = singleFieldBuilderV3.build();
            }
            createIntentRequest.languageCode_ = this.languageCode_;
            createIntentRequest.intentView_ = this.intentView_;
            onBuilt();
            return createIntentRequest;
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
            if (message instanceof CreateIntentRequest) {
                return mergeFrom((CreateIntentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(CreateIntentRequest createIntentRequest) {
            if (createIntentRequest == CreateIntentRequest.getDefaultInstance()) {
                return this;
            }
            if (!createIntentRequest.getParent().isEmpty()) {
                this.parent_ = createIntentRequest.parent_;
                onChanged();
            }
            if (createIntentRequest.hasIntent()) {
                mergeIntent(createIntentRequest.getIntent());
            }
            if (!createIntentRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = createIntentRequest.languageCode_;
                onChanged();
            }
            if (createIntentRequest.intentView_ != 0) {
                setIntentViewValue(createIntentRequest.getIntentViewValue());
            }
            mergeUnknownFields(createIntentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CreateIntentRequest createIntentRequest = null;
            try {
                try {
                    CreateIntentRequest createIntentRequest2 = (CreateIntentRequest) CreateIntentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (createIntentRequest2 != null) {
                        mergeFrom(createIntentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CreateIntentRequest createIntentRequest3 = (CreateIntentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        createIntentRequest = createIntentRequest3;
                        if (createIntentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (createIntentRequest != null) {
                    mergeFrom(createIntentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
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
            this.parent_ = CreateIntentRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                CreateIntentRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
        public boolean hasIntent() {
            return (this.intentBuilder_ == null && this.intent_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
        public Intent getIntent() {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Intent intent = this.intent_;
                return intent == null ? Intent.getDefaultInstance() : intent;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setIntent(Intent intent) {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(intent);
            } else {
                if (intent == null) {
                    throw new NullPointerException();
                }
                this.intent_ = intent;
                onChanged();
            }
            return this;
        }

        public Builder setIntent(Intent.Builder builder) {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.intent_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeIntent(Intent intent) {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                Intent intent2 = this.intent_;
                if (intent2 != null) {
                    this.intent_ = Intent.newBuilder(intent2).mergeFrom(intent).buildPartial();
                } else {
                    this.intent_ = intent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(intent);
            }
            return this;
        }

        public Builder clearIntent() {
            if (this.intentBuilder_ == null) {
                this.intent_ = null;
                onChanged();
            } else {
                this.intent_ = null;
                this.intentBuilder_ = null;
            }
            return this;
        }

        public Intent.Builder getIntentBuilder() {
            onChanged();
            return getIntentFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
        public IntentOrBuilder getIntentOrBuilder() {
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Intent intent = this.intent_;
            return intent == null ? Intent.getDefaultInstance() : intent;
        }

        private SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> getIntentFieldBuilder() {
            if (this.intentBuilder_ == null) {
                this.intentBuilder_ = new SingleFieldBuilderV3<>(getIntent(), getParentForChildren(), isClean());
                this.intent_ = null;
            }
            return this.intentBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
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
            this.languageCode_ = CreateIntentRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                CreateIntentRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
        public int getIntentViewValue() {
            return this.intentView_;
        }

        public Builder setIntentViewValue(int i) {
            this.intentView_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.CreateIntentRequestOrBuilder
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

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static CreateIntentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateIntentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<CreateIntentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public CreateIntentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
