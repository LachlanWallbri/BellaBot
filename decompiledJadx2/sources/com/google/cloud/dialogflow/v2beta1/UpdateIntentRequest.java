package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Intent;
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
public final class UpdateIntentRequest extends GeneratedMessageV3 implements UpdateIntentRequestOrBuilder {
    public static final int INTENT_FIELD_NUMBER = 1;
    public static final int INTENT_VIEW_FIELD_NUMBER = 4;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 2;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int intentView_;
    private Intent intent_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private FieldMask updateMask_;
    private static final UpdateIntentRequest DEFAULT_INSTANCE = new UpdateIntentRequest();
    private static final Parser<UpdateIntentRequest> PARSER = new AbstractParser<UpdateIntentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.UpdateIntentRequest.1
        @Override // com.google.protobuf.Parser
        public UpdateIntentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpdateIntentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private UpdateIntentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpdateIntentRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.languageCode_ = "";
        this.intentView_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new UpdateIntentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateIntentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Intent.Builder builder = this.intent_ != null ? this.intent_.toBuilder() : null;
                                this.intent_ = (Intent) codedInputStream.readMessage(Intent.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.intent_);
                                    this.intent_ = builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                this.languageCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                FieldMask.Builder builder2 = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                                this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.updateMask_);
                                    this.updateMask_ = builder2.buildPartial();
                                }
                            } else if (readTag == 32) {
                                this.intentView_ = codedInputStream.readEnum();
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
        return IntentProto.f1803x23637b66;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IntentProto.f1804x286bdbe4.ensureFieldAccessorsInitialized(UpdateIntentRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public boolean hasIntent() {
        return this.intent_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public Intent getIntent() {
        Intent intent = this.intent_;
        return intent == null ? Intent.getDefaultInstance() : intent;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public IntentOrBuilder getIntentOrBuilder() {
        return getIntent();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
        return getUpdateMask();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
    public int getIntentViewValue() {
        return this.intentView_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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
        if (this.intent_ != null) {
            codedOutputStream.writeMessage(1, getIntent());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(3, getUpdateMask());
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
        int computeMessageSize = this.intent_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getIntent()) : 0;
        if (!getLanguageCodeBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, getUpdateMask());
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            computeMessageSize += CodedOutputStream.computeEnumSize(4, this.intentView_);
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
        if (!(obj instanceof UpdateIntentRequest)) {
            return super.equals(obj);
        }
        UpdateIntentRequest updateIntentRequest = (UpdateIntentRequest) obj;
        if (hasIntent() != updateIntentRequest.hasIntent()) {
            return false;
        }
        if ((!hasIntent() || getIntent().equals(updateIntentRequest.getIntent())) && getLanguageCode().equals(updateIntentRequest.getLanguageCode()) && hasUpdateMask() == updateIntentRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(updateIntentRequest.getUpdateMask())) && this.intentView_ == updateIntentRequest.intentView_ && this.unknownFields.equals(updateIntentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasIntent()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getIntent().hashCode();
        }
        int hashCode2 = (((hashCode * 37) + 2) * 53) + getLanguageCode().hashCode();
        if (hasUpdateMask()) {
            hashCode2 = (((hashCode2 * 37) + 3) * 53) + getUpdateMask().hashCode();
        }
        int hashCode3 = (((((hashCode2 * 37) + 4) * 53) + this.intentView_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static UpdateIntentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static UpdateIntentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpdateIntentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static UpdateIntentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpdateIntentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static UpdateIntentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpdateIntentRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpdateIntentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateIntentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpdateIntentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateIntentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateIntentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpdateIntentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateIntentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateIntentRequest updateIntentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateIntentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateIntentRequestOrBuilder {
        private SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> intentBuilder_;
        private int intentView_;
        private Intent intent_;
        private Object languageCode_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return IntentProto.f1803x23637b66;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IntentProto.f1804x286bdbe4.ensureFieldAccessorsInitialized(UpdateIntentRequest.class, Builder.class);
        }

        private Builder() {
            this.languageCode_ = "";
            this.intentView_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.languageCode_ = "";
            this.intentView_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpdateIntentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.intentBuilder_ == null) {
                this.intent_ = null;
            } else {
                this.intent_ = null;
                this.intentBuilder_ = null;
            }
            this.languageCode_ = "";
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            this.intentView_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return IntentProto.f1803x23637b66;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateIntentRequest getDefaultInstanceForType() {
            return UpdateIntentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateIntentRequest build() {
            UpdateIntentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateIntentRequest buildPartial() {
            UpdateIntentRequest updateIntentRequest = new UpdateIntentRequest(this);
            SingleFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> singleFieldBuilderV3 = this.intentBuilder_;
            if (singleFieldBuilderV3 == null) {
                updateIntentRequest.intent_ = this.intent_;
            } else {
                updateIntentRequest.intent_ = singleFieldBuilderV3.build();
            }
            updateIntentRequest.languageCode_ = this.languageCode_;
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                updateIntentRequest.updateMask_ = this.updateMask_;
            } else {
                updateIntentRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            updateIntentRequest.intentView_ = this.intentView_;
            onBuilt();
            return updateIntentRequest;
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
            if (message instanceof UpdateIntentRequest) {
                return mergeFrom((UpdateIntentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpdateIntentRequest updateIntentRequest) {
            if (updateIntentRequest == UpdateIntentRequest.getDefaultInstance()) {
                return this;
            }
            if (updateIntentRequest.hasIntent()) {
                mergeIntent(updateIntentRequest.getIntent());
            }
            if (!updateIntentRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = updateIntentRequest.languageCode_;
                onChanged();
            }
            if (updateIntentRequest.hasUpdateMask()) {
                mergeUpdateMask(updateIntentRequest.getUpdateMask());
            }
            if (updateIntentRequest.intentView_ != 0) {
                setIntentViewValue(updateIntentRequest.getIntentViewValue());
            }
            mergeUnknownFields(updateIntentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            UpdateIntentRequest updateIntentRequest = null;
            try {
                try {
                    UpdateIntentRequest updateIntentRequest2 = (UpdateIntentRequest) UpdateIntentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (updateIntentRequest2 != null) {
                        mergeFrom(updateIntentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    UpdateIntentRequest updateIntentRequest3 = (UpdateIntentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        updateIntentRequest = updateIntentRequest3;
                        if (updateIntentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (updateIntentRequest != null) {
                    mergeFrom(updateIntentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
        public boolean hasIntent() {
            return (this.intentBuilder_ == null && this.intent_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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
            this.languageCode_ = UpdateIntentRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                UpdateIntentRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
        public int getIntentViewValue() {
            return this.intentView_;
        }

        public Builder setIntentViewValue(int i) {
            this.intentView_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateIntentRequestOrBuilder
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

    public static UpdateIntentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateIntentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<UpdateIntentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public UpdateIntentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
