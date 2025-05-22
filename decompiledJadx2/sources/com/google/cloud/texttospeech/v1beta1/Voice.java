package com.google.cloud.texttospeech.v1beta1;

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
public final class Voice extends GeneratedMessageV3 implements VoiceOrBuilder {
    public static final int LANGUAGE_CODES_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    public static final int NATURAL_SAMPLE_RATE_HERTZ_FIELD_NUMBER = 4;
    public static final int SSML_GENDER_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private LazyStringList languageCodes_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int naturalSampleRateHertz_;
    private int ssmlGender_;
    private static final Voice DEFAULT_INSTANCE = new Voice();
    private static final Parser<Voice> PARSER = new AbstractParser<Voice>() { // from class: com.google.cloud.texttospeech.v1beta1.Voice.1
        @Override // com.google.protobuf.Parser
        public Voice parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Voice(codedInputStream, extensionRegistryLite);
        }
    };

    private Voice(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Voice() {
        this.memoizedIsInitialized = (byte) -1;
        this.languageCodes_ = LazyStringArrayList.EMPTY;
        this.name_ = "";
        this.ssmlGender_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Voice();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Voice(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.languageCodes_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.languageCodes_.add(readStringRequireUtf8);
                        } else if (readTag == 18) {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 24) {
                            this.ssmlGender_ = codedInputStream.readEnum();
                        } else if (readTag == 32) {
                            this.naturalSampleRateHertz_ = codedInputStream.readInt32();
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
                    this.languageCodes_ = this.languageCodes_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TextToSpeechProto.f1911x938a7b69;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1912x3e6028e7.ensureFieldAccessorsInitialized(Voice.class, Builder.class);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public ProtocolStringList getLanguageCodesList() {
        return this.languageCodes_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public int getLanguageCodesCount() {
        return this.languageCodes_.size();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public String getLanguageCodes(int i) {
        return (String) this.languageCodes_.get(i);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public ByteString getLanguageCodesBytes(int i) {
        return this.languageCodes_.getByteString(i);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public int getSsmlGenderValue() {
        return this.ssmlGender_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public SsmlVoiceGender getSsmlGender() {
        SsmlVoiceGender valueOf = SsmlVoiceGender.valueOf(this.ssmlGender_);
        return valueOf == null ? SsmlVoiceGender.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
    public int getNaturalSampleRateHertz() {
        return this.naturalSampleRateHertz_;
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
        for (int i = 0; i < this.languageCodes_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.languageCodes_.getRaw(i));
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.name_);
        }
        if (this.ssmlGender_ != SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.ssmlGender_);
        }
        int i2 = this.naturalSampleRateHertz_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(4, i2);
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
        for (int i3 = 0; i3 < this.languageCodes_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.languageCodes_.getRaw(i3));
        }
        int size = 0 + i2 + (getLanguageCodesList().size() * 1);
        if (!getNameBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(2, this.name_);
        }
        if (this.ssmlGender_ != SsmlVoiceGender.SSML_VOICE_GENDER_UNSPECIFIED.getNumber()) {
            size += CodedOutputStream.computeEnumSize(3, this.ssmlGender_);
        }
        int i4 = this.naturalSampleRateHertz_;
        if (i4 != 0) {
            size += CodedOutputStream.computeInt32Size(4, i4);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Voice)) {
            return super.equals(obj);
        }
        Voice voice = (Voice) obj;
        return getLanguageCodesList().equals(voice.getLanguageCodesList()) && getName().equals(voice.getName()) && this.ssmlGender_ == voice.ssmlGender_ && getNaturalSampleRateHertz() == voice.getNaturalSampleRateHertz() && this.unknownFields.equals(voice.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getLanguageCodesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getLanguageCodesList().hashCode();
        }
        int hashCode2 = (((((((((((((hashCode * 37) + 2) * 53) + getName().hashCode()) * 37) + 3) * 53) + this.ssmlGender_) * 37) + 4) * 53) + getNaturalSampleRateHertz()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Voice parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Voice parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Voice parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Voice parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Voice parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Voice parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Voice parseFrom(InputStream inputStream) throws IOException {
        return (Voice) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Voice parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Voice) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Voice parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Voice) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Voice parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Voice) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Voice parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Voice) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Voice parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Voice) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Voice voice) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(voice);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VoiceOrBuilder {
        private int bitField0_;
        private LazyStringList languageCodes_;
        private Object name_;
        private int naturalSampleRateHertz_;
        private int ssmlGender_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1911x938a7b69;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1912x3e6028e7.ensureFieldAccessorsInitialized(Voice.class, Builder.class);
        }

        private Builder() {
            this.languageCodes_ = LazyStringArrayList.EMPTY;
            this.name_ = "";
            this.ssmlGender_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.languageCodes_ = LazyStringArrayList.EMPTY;
            this.name_ = "";
            this.ssmlGender_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Voice.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.languageCodes_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.name_ = "";
            this.ssmlGender_ = 0;
            this.naturalSampleRateHertz_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1911x938a7b69;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Voice getDefaultInstanceForType() {
            return Voice.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Voice build() {
            Voice buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Voice buildPartial() {
            Voice voice = new Voice(this);
            if ((this.bitField0_ & 1) != 0) {
                this.languageCodes_ = this.languageCodes_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            voice.languageCodes_ = this.languageCodes_;
            voice.name_ = this.name_;
            voice.ssmlGender_ = this.ssmlGender_;
            voice.naturalSampleRateHertz_ = this.naturalSampleRateHertz_;
            onBuilt();
            return voice;
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
            if (message instanceof Voice) {
                return mergeFrom((Voice) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Voice voice) {
            if (voice == Voice.getDefaultInstance()) {
                return this;
            }
            if (!voice.languageCodes_.isEmpty()) {
                if (this.languageCodes_.isEmpty()) {
                    this.languageCodes_ = voice.languageCodes_;
                    this.bitField0_ &= -2;
                } else {
                    ensureLanguageCodesIsMutable();
                    this.languageCodes_.addAll(voice.languageCodes_);
                }
                onChanged();
            }
            if (!voice.getName().isEmpty()) {
                this.name_ = voice.name_;
                onChanged();
            }
            if (voice.ssmlGender_ != 0) {
                setSsmlGenderValue(voice.getSsmlGenderValue());
            }
            if (voice.getNaturalSampleRateHertz() != 0) {
                setNaturalSampleRateHertz(voice.getNaturalSampleRateHertz());
            }
            mergeUnknownFields(voice.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Voice voice = null;
            try {
                try {
                    Voice voice2 = (Voice) Voice.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (voice2 != null) {
                        mergeFrom(voice2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Voice voice3 = (Voice) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        voice = voice3;
                        if (voice != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (voice != null) {
                    mergeFrom(voice);
                }
                throw th;
            }
        }

        private void ensureLanguageCodesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.languageCodes_ = new LazyStringArrayList(this.languageCodes_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public ProtocolStringList getLanguageCodesList() {
            return this.languageCodes_.getUnmodifiableView();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public int getLanguageCodesCount() {
            return this.languageCodes_.size();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public String getLanguageCodes(int i) {
            return (String) this.languageCodes_.get(i);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public ByteString getLanguageCodesBytes(int i) {
            return this.languageCodes_.getByteString(i);
        }

        public Builder setLanguageCodes(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureLanguageCodesIsMutable();
            this.languageCodes_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addLanguageCodes(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureLanguageCodesIsMutable();
            this.languageCodes_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllLanguageCodes(Iterable<String> iterable) {
            ensureLanguageCodesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.languageCodes_);
            onChanged();
            return this;
        }

        public Builder clearLanguageCodes() {
            this.languageCodes_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addLanguageCodesBytes(ByteString byteString) {
            if (byteString != null) {
                Voice.checkByteStringIsUtf8(byteString);
                ensureLanguageCodesIsMutable();
                this.languageCodes_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
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
            this.name_ = Voice.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Voice.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public int getSsmlGenderValue() {
            return this.ssmlGender_;
        }

        public Builder setSsmlGenderValue(int i) {
            this.ssmlGender_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
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

        @Override // com.google.cloud.texttospeech.v1beta1.VoiceOrBuilder
        public int getNaturalSampleRateHertz() {
            return this.naturalSampleRateHertz_;
        }

        public Builder setNaturalSampleRateHertz(int i) {
            this.naturalSampleRateHertz_ = i;
            onChanged();
            return this;
        }

        public Builder clearNaturalSampleRateHertz() {
            this.naturalSampleRateHertz_ = 0;
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

    public static Voice getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Voice> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Voice> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Voice getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
