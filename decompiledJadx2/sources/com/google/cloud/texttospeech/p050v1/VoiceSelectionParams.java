package com.google.cloud.texttospeech.p050v1;

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
public final class VoiceSelectionParams extends GeneratedMessageV3 implements VoiceSelectionParamsOrBuilder {
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    public static final int SSML_GENDER_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int ssmlGender_;
    private static final VoiceSelectionParams DEFAULT_INSTANCE = new VoiceSelectionParams();
    private static final Parser<VoiceSelectionParams> PARSER = new AbstractParser<VoiceSelectionParams>() { // from class: com.google.cloud.texttospeech.v1.VoiceSelectionParams.1
        @Override // com.google.protobuf.Parser
        public VoiceSelectionParams parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new VoiceSelectionParams(codedInputStream, extensionRegistryLite);
        }
    };

    private VoiceSelectionParams(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private VoiceSelectionParams() {
        this.memoizedIsInitialized = (byte) -1;
        this.languageCode_ = "";
        this.name_ = "";
        this.ssmlGender_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new VoiceSelectionParams();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private VoiceSelectionParams(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 24) {
                            this.ssmlGender_ = codedInputStream.readEnum();
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
        return TextToSpeechProto.f1891x59da05be;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1892x643e8e3c.ensureFieldAccessorsInitialized(VoiceSelectionParams.class, Builder.class);
    }

    @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
    public int getSsmlGenderValue() {
        return this.ssmlGender_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
    public SsmlVoiceGender getSsmlGender() {
        SsmlVoiceGender valueOf = SsmlVoiceGender.valueOf(this.ssmlGender_);
        return valueOf == null ? SsmlVoiceGender.UNRECOGNIZED : valueOf;
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
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.languageCode_);
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.name_);
        }
        if (this.ssmlGender_ != SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.ssmlGender_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getLanguageCodeBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.languageCode_);
        if (!getNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.name_);
        }
        if (this.ssmlGender_ != SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.ssmlGender_);
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
        if (!(obj instanceof VoiceSelectionParams)) {
            return super.equals(obj);
        }
        VoiceSelectionParams voiceSelectionParams = (VoiceSelectionParams) obj;
        return getLanguageCode().equals(voiceSelectionParams.getLanguageCode()) && getName().equals(voiceSelectionParams.getName()) && this.ssmlGender_ == voiceSelectionParams.ssmlGender_ && this.unknownFields.equals(voiceSelectionParams.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLanguageCode().hashCode()) * 37) + 2) * 53) + getName().hashCode()) * 37) + 3) * 53) + this.ssmlGender_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static VoiceSelectionParams parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static VoiceSelectionParams parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static VoiceSelectionParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static VoiceSelectionParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static VoiceSelectionParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static VoiceSelectionParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static VoiceSelectionParams parseFrom(InputStream inputStream) throws IOException {
        return (VoiceSelectionParams) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static VoiceSelectionParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (VoiceSelectionParams) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static VoiceSelectionParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (VoiceSelectionParams) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static VoiceSelectionParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (VoiceSelectionParams) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static VoiceSelectionParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (VoiceSelectionParams) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static VoiceSelectionParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (VoiceSelectionParams) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(VoiceSelectionParams voiceSelectionParams) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(voiceSelectionParams);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VoiceSelectionParamsOrBuilder {
        private Object languageCode_;
        private Object name_;
        private int ssmlGender_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1891x59da05be;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1892x643e8e3c.ensureFieldAccessorsInitialized(VoiceSelectionParams.class, Builder.class);
        }

        private Builder() {
            this.languageCode_ = "";
            this.name_ = "";
            this.ssmlGender_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.languageCode_ = "";
            this.name_ = "";
            this.ssmlGender_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = VoiceSelectionParams.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.languageCode_ = "";
            this.name_ = "";
            this.ssmlGender_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1891x59da05be;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public VoiceSelectionParams getDefaultInstanceForType() {
            return VoiceSelectionParams.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public VoiceSelectionParams build() {
            VoiceSelectionParams buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public VoiceSelectionParams buildPartial() {
            VoiceSelectionParams voiceSelectionParams = new VoiceSelectionParams(this);
            voiceSelectionParams.languageCode_ = this.languageCode_;
            voiceSelectionParams.name_ = this.name_;
            voiceSelectionParams.ssmlGender_ = this.ssmlGender_;
            onBuilt();
            return voiceSelectionParams;
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
            if (message instanceof VoiceSelectionParams) {
                return mergeFrom((VoiceSelectionParams) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(VoiceSelectionParams voiceSelectionParams) {
            if (voiceSelectionParams == VoiceSelectionParams.getDefaultInstance()) {
                return this;
            }
            if (!voiceSelectionParams.getLanguageCode().isEmpty()) {
                this.languageCode_ = voiceSelectionParams.languageCode_;
                onChanged();
            }
            if (!voiceSelectionParams.getName().isEmpty()) {
                this.name_ = voiceSelectionParams.name_;
                onChanged();
            }
            if (voiceSelectionParams.ssmlGender_ != 0) {
                setSsmlGenderValue(voiceSelectionParams.getSsmlGenderValue());
            }
            mergeUnknownFields(voiceSelectionParams.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            VoiceSelectionParams voiceSelectionParams = null;
            try {
                try {
                    VoiceSelectionParams voiceSelectionParams2 = (VoiceSelectionParams) VoiceSelectionParams.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (voiceSelectionParams2 != null) {
                        mergeFrom(voiceSelectionParams2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    VoiceSelectionParams voiceSelectionParams3 = (VoiceSelectionParams) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        voiceSelectionParams = voiceSelectionParams3;
                        if (voiceSelectionParams != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (voiceSelectionParams != null) {
                    mergeFrom(voiceSelectionParams);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
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
            this.languageCode_ = VoiceSelectionParams.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                VoiceSelectionParams.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = VoiceSelectionParams.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                VoiceSelectionParams.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
        public int getSsmlGenderValue() {
            return this.ssmlGender_;
        }

        public Builder setSsmlGenderValue(int i) {
            this.ssmlGender_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.p050v1.VoiceSelectionParamsOrBuilder
        public SsmlVoiceGender getSsmlGender() {
            SsmlVoiceGender valueOf = SsmlVoiceGender.valueOf(this.ssmlGender_);
            return valueOf == null ? SsmlVoiceGender.UNRECOGNIZED : valueOf;
        }

        public Builder setSsmlGender(SsmlVoiceGender ssmlVoiceGender) {
            if (ssmlVoiceGender == null) {
                throw new NullPointerException();
            }
            this.ssmlGender_ = ssmlVoiceGender.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSsmlGender() {
            this.ssmlGender_ = 0;
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

    public static VoiceSelectionParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<VoiceSelectionParams> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<VoiceSelectionParams> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public VoiceSelectionParams getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
