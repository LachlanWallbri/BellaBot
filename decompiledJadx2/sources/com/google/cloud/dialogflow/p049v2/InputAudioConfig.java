package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.SpeechContext;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
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
/* loaded from: classes2.dex */
public final class InputAudioConfig extends GeneratedMessageV3 implements InputAudioConfigOrBuilder {
    public static final int AUDIO_ENCODING_FIELD_NUMBER = 1;
    public static final int ENABLE_WORD_INFO_FIELD_NUMBER = 13;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int MODEL_FIELD_NUMBER = 7;
    public static final int MODEL_VARIANT_FIELD_NUMBER = 10;
    public static final int PHRASE_HINTS_FIELD_NUMBER = 4;
    public static final int SAMPLE_RATE_HERTZ_FIELD_NUMBER = 2;
    public static final int SINGLE_UTTERANCE_FIELD_NUMBER = 8;
    public static final int SPEECH_CONTEXTS_FIELD_NUMBER = 11;
    private static final long serialVersionUID = 0;
    private int audioEncoding_;
    private boolean enableWordInfo_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private int modelVariant_;
    private volatile Object model_;
    private LazyStringList phraseHints_;
    private int sampleRateHertz_;
    private boolean singleUtterance_;
    private List<SpeechContext> speechContexts_;
    private static final InputAudioConfig DEFAULT_INSTANCE = new InputAudioConfig();
    private static final Parser<InputAudioConfig> PARSER = new AbstractParser<InputAudioConfig>() { // from class: com.google.cloud.dialogflow.v2.InputAudioConfig.1
        @Override // com.google.protobuf.Parser
        public InputAudioConfig parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new InputAudioConfig(codedInputStream, extensionRegistryLite);
        }
    };

    private InputAudioConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private InputAudioConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.audioEncoding_ = 0;
        this.languageCode_ = "";
        this.phraseHints_ = LazyStringArrayList.EMPTY;
        this.speechContexts_ = Collections.emptyList();
        this.model_ = "";
        this.modelVariant_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new InputAudioConfig();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private InputAudioConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.audioEncoding_ = codedInputStream.readEnum();
                        } else if (readTag == 16) {
                            this.sampleRateHertz_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 34) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.phraseHints_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.phraseHints_.add(readStringRequireUtf8);
                        } else if (readTag == 58) {
                            this.model_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 64) {
                            this.singleUtterance_ = codedInputStream.readBool();
                        } else if (readTag == 80) {
                            this.modelVariant_ = codedInputStream.readEnum();
                        } else if (readTag == 90) {
                            if ((i & 2) == 0) {
                                this.speechContexts_ = new ArrayList();
                                i |= 2;
                            }
                            this.speechContexts_.add(codedInputStream.readMessage(SpeechContext.parser(), extensionRegistryLite));
                        } else if (readTag == 104) {
                            this.enableWordInfo_ = codedInputStream.readBool();
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
                if ((i & 1) != 0) {
                    this.phraseHints_ = this.phraseHints_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.speechContexts_ = Collections.unmodifiableList(this.speechContexts_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AudioConfigProto.f1384x8a11aea5;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AudioConfigProto.f1385x200e6023.ensureFieldAccessorsInitialized(InputAudioConfig.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public int getAudioEncodingValue() {
        return this.audioEncoding_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public AudioEncoding getAudioEncoding() {
        AudioEncoding valueOf = AudioEncoding.valueOf(this.audioEncoding_);
        return valueOf == null ? AudioEncoding.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public int getSampleRateHertz() {
        return this.sampleRateHertz_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public boolean getEnableWordInfo() {
        return this.enableWordInfo_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    @Deprecated
    public ProtocolStringList getPhraseHintsList() {
        return this.phraseHints_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    @Deprecated
    public int getPhraseHintsCount() {
        return this.phraseHints_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    @Deprecated
    public String getPhraseHints(int i) {
        return (String) this.phraseHints_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    @Deprecated
    public ByteString getPhraseHintsBytes(int i) {
        return this.phraseHints_.getByteString(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public List<SpeechContext> getSpeechContextsList() {
        return this.speechContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public List<? extends SpeechContextOrBuilder> getSpeechContextsOrBuilderList() {
        return this.speechContexts_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public int getSpeechContextsCount() {
        return this.speechContexts_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public SpeechContext getSpeechContexts(int i) {
        return this.speechContexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public SpeechContextOrBuilder getSpeechContextsOrBuilder(int i) {
        return this.speechContexts_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public String getModel() {
        Object obj = this.model_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.model_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public ByteString getModelBytes() {
        Object obj = this.model_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.model_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public int getModelVariantValue() {
        return this.modelVariant_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public SpeechModelVariant getModelVariant() {
        SpeechModelVariant valueOf = SpeechModelVariant.valueOf(this.modelVariant_);
        return valueOf == null ? SpeechModelVariant.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
    public boolean getSingleUtterance() {
        return this.singleUtterance_;
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
        if (this.audioEncoding_ != AudioEncoding.AUDIO_ENCODING_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.audioEncoding_);
        }
        int i = this.sampleRateHertz_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
        }
        for (int i2 = 0; i2 < this.phraseHints_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.phraseHints_.getRaw(i2));
        }
        if (!getModelBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.model_);
        }
        boolean z = this.singleUtterance_;
        if (z) {
            codedOutputStream.writeBool(8, z);
        }
        if (this.modelVariant_ != SpeechModelVariant.SPEECH_MODEL_VARIANT_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(10, this.modelVariant_);
        }
        for (int i3 = 0; i3 < this.speechContexts_.size(); i3++) {
            codedOutputStream.writeMessage(11, this.speechContexts_.get(i3));
        }
        boolean z2 = this.enableWordInfo_;
        if (z2) {
            codedOutputStream.writeBool(13, z2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.audioEncoding_ != AudioEncoding.AUDIO_ENCODING_UNSPECIFIED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.audioEncoding_) + 0 : 0;
        int i2 = this.sampleRateHertz_;
        if (i2 != 0) {
            computeEnumSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.phraseHints_.size(); i4++) {
            i3 += computeStringSizeNoTag(this.phraseHints_.getRaw(i4));
        }
        int size = computeEnumSize + i3 + (getPhraseHintsList().size() * 1);
        if (!getModelBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(7, this.model_);
        }
        boolean z = this.singleUtterance_;
        if (z) {
            size += CodedOutputStream.computeBoolSize(8, z);
        }
        if (this.modelVariant_ != SpeechModelVariant.SPEECH_MODEL_VARIANT_UNSPECIFIED.getNumber()) {
            size += CodedOutputStream.computeEnumSize(10, this.modelVariant_);
        }
        for (int i5 = 0; i5 < this.speechContexts_.size(); i5++) {
            size += CodedOutputStream.computeMessageSize(11, this.speechContexts_.get(i5));
        }
        boolean z2 = this.enableWordInfo_;
        if (z2) {
            size += CodedOutputStream.computeBoolSize(13, z2);
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
        if (!(obj instanceof InputAudioConfig)) {
            return super.equals(obj);
        }
        InputAudioConfig inputAudioConfig = (InputAudioConfig) obj;
        return this.audioEncoding_ == inputAudioConfig.audioEncoding_ && getSampleRateHertz() == inputAudioConfig.getSampleRateHertz() && getLanguageCode().equals(inputAudioConfig.getLanguageCode()) && getEnableWordInfo() == inputAudioConfig.getEnableWordInfo() && getPhraseHintsList().equals(inputAudioConfig.getPhraseHintsList()) && getSpeechContextsList().equals(inputAudioConfig.getSpeechContextsList()) && getModel().equals(inputAudioConfig.getModel()) && this.modelVariant_ == inputAudioConfig.modelVariant_ && getSingleUtterance() == inputAudioConfig.getSingleUtterance() && this.unknownFields.equals(inputAudioConfig.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.audioEncoding_) * 37) + 2) * 53) + getSampleRateHertz()) * 37) + 3) * 53) + getLanguageCode().hashCode()) * 37) + 13) * 53) + Internal.hashBoolean(getEnableWordInfo());
        if (getPhraseHintsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getPhraseHintsList().hashCode();
        }
        if (getSpeechContextsCount() > 0) {
            hashCode = (((hashCode * 37) + 11) * 53) + getSpeechContextsList().hashCode();
        }
        int hashCode2 = (((((((((((((hashCode * 37) + 7) * 53) + getModel().hashCode()) * 37) + 10) * 53) + this.modelVariant_) * 37) + 8) * 53) + Internal.hashBoolean(getSingleUtterance())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static InputAudioConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static InputAudioConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static InputAudioConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static InputAudioConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static InputAudioConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static InputAudioConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static InputAudioConfig parseFrom(InputStream inputStream) throws IOException {
        return (InputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static InputAudioConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (InputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static InputAudioConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (InputAudioConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static InputAudioConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (InputAudioConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static InputAudioConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (InputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static InputAudioConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (InputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(InputAudioConfig inputAudioConfig) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(inputAudioConfig);
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
    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InputAudioConfigOrBuilder {
        private int audioEncoding_;
        private int bitField0_;
        private boolean enableWordInfo_;
        private Object languageCode_;
        private int modelVariant_;
        private Object model_;
        private LazyStringList phraseHints_;
        private int sampleRateHertz_;
        private boolean singleUtterance_;
        private RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> speechContextsBuilder_;
        private List<SpeechContext> speechContexts_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AudioConfigProto.f1384x8a11aea5;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AudioConfigProto.f1385x200e6023.ensureFieldAccessorsInitialized(InputAudioConfig.class, Builder.class);
        }

        private Builder() {
            this.audioEncoding_ = 0;
            this.languageCode_ = "";
            this.phraseHints_ = LazyStringArrayList.EMPTY;
            this.speechContexts_ = Collections.emptyList();
            this.model_ = "";
            this.modelVariant_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.audioEncoding_ = 0;
            this.languageCode_ = "";
            this.phraseHints_ = LazyStringArrayList.EMPTY;
            this.speechContexts_ = Collections.emptyList();
            this.model_ = "";
            this.modelVariant_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (InputAudioConfig.alwaysUseFieldBuilders) {
                getSpeechContextsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.audioEncoding_ = 0;
            this.sampleRateHertz_ = 0;
            this.languageCode_ = "";
            this.enableWordInfo_ = false;
            this.phraseHints_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.speechContexts_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.model_ = "";
            this.modelVariant_ = 0;
            this.singleUtterance_ = false;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AudioConfigProto.f1384x8a11aea5;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public InputAudioConfig getDefaultInstanceForType() {
            return InputAudioConfig.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public InputAudioConfig build() {
            InputAudioConfig buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public InputAudioConfig buildPartial() {
            InputAudioConfig inputAudioConfig = new InputAudioConfig(this);
            int i = this.bitField0_;
            inputAudioConfig.audioEncoding_ = this.audioEncoding_;
            inputAudioConfig.sampleRateHertz_ = this.sampleRateHertz_;
            inputAudioConfig.languageCode_ = this.languageCode_;
            inputAudioConfig.enableWordInfo_ = this.enableWordInfo_;
            if ((this.bitField0_ & 1) != 0) {
                this.phraseHints_ = this.phraseHints_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            inputAudioConfig.phraseHints_ = this.phraseHints_;
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                inputAudioConfig.speechContexts_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.speechContexts_ = Collections.unmodifiableList(this.speechContexts_);
                    this.bitField0_ &= -3;
                }
                inputAudioConfig.speechContexts_ = this.speechContexts_;
            }
            inputAudioConfig.model_ = this.model_;
            inputAudioConfig.modelVariant_ = this.modelVariant_;
            inputAudioConfig.singleUtterance_ = this.singleUtterance_;
            onBuilt();
            return inputAudioConfig;
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
            if (message instanceof InputAudioConfig) {
                return mergeFrom((InputAudioConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(InputAudioConfig inputAudioConfig) {
            if (inputAudioConfig == InputAudioConfig.getDefaultInstance()) {
                return this;
            }
            if (inputAudioConfig.audioEncoding_ != 0) {
                setAudioEncodingValue(inputAudioConfig.getAudioEncodingValue());
            }
            if (inputAudioConfig.getSampleRateHertz() != 0) {
                setSampleRateHertz(inputAudioConfig.getSampleRateHertz());
            }
            if (!inputAudioConfig.getLanguageCode().isEmpty()) {
                this.languageCode_ = inputAudioConfig.languageCode_;
                onChanged();
            }
            if (inputAudioConfig.getEnableWordInfo()) {
                setEnableWordInfo(inputAudioConfig.getEnableWordInfo());
            }
            if (!inputAudioConfig.phraseHints_.isEmpty()) {
                if (this.phraseHints_.isEmpty()) {
                    this.phraseHints_ = inputAudioConfig.phraseHints_;
                    this.bitField0_ &= -2;
                } else {
                    ensurePhraseHintsIsMutable();
                    this.phraseHints_.addAll(inputAudioConfig.phraseHints_);
                }
                onChanged();
            }
            if (this.speechContextsBuilder_ == null) {
                if (!inputAudioConfig.speechContexts_.isEmpty()) {
                    if (this.speechContexts_.isEmpty()) {
                        this.speechContexts_ = inputAudioConfig.speechContexts_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureSpeechContextsIsMutable();
                        this.speechContexts_.addAll(inputAudioConfig.speechContexts_);
                    }
                    onChanged();
                }
            } else if (!inputAudioConfig.speechContexts_.isEmpty()) {
                if (!this.speechContextsBuilder_.isEmpty()) {
                    this.speechContextsBuilder_.addAllMessages(inputAudioConfig.speechContexts_);
                } else {
                    this.speechContextsBuilder_.dispose();
                    this.speechContextsBuilder_ = null;
                    this.speechContexts_ = inputAudioConfig.speechContexts_;
                    this.bitField0_ &= -3;
                    this.speechContextsBuilder_ = InputAudioConfig.alwaysUseFieldBuilders ? getSpeechContextsFieldBuilder() : null;
                }
            }
            if (!inputAudioConfig.getModel().isEmpty()) {
                this.model_ = inputAudioConfig.model_;
                onChanged();
            }
            if (inputAudioConfig.modelVariant_ != 0) {
                setModelVariantValue(inputAudioConfig.getModelVariantValue());
            }
            if (inputAudioConfig.getSingleUtterance()) {
                setSingleUtterance(inputAudioConfig.getSingleUtterance());
            }
            mergeUnknownFields(inputAudioConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            InputAudioConfig inputAudioConfig = null;
            try {
                try {
                    InputAudioConfig inputAudioConfig2 = (InputAudioConfig) InputAudioConfig.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (inputAudioConfig2 != null) {
                        mergeFrom(inputAudioConfig2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InputAudioConfig inputAudioConfig3 = (InputAudioConfig) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        inputAudioConfig = inputAudioConfig3;
                        if (inputAudioConfig != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputAudioConfig != null) {
                    mergeFrom(inputAudioConfig);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public int getAudioEncodingValue() {
            return this.audioEncoding_;
        }

        public Builder setAudioEncodingValue(int i) {
            this.audioEncoding_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public AudioEncoding getAudioEncoding() {
            AudioEncoding valueOf = AudioEncoding.valueOf(this.audioEncoding_);
            return valueOf == null ? AudioEncoding.UNRECOGNIZED : valueOf;
        }

        public Builder setAudioEncoding(AudioEncoding audioEncoding) {
            if (audioEncoding == null) {
                throw new NullPointerException();
            }
            this.audioEncoding_ = audioEncoding.getNumber();
            onChanged();
            return this;
        }

        public Builder clearAudioEncoding() {
            this.audioEncoding_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public int getSampleRateHertz() {
            return this.sampleRateHertz_;
        }

        public Builder setSampleRateHertz(int i) {
            this.sampleRateHertz_ = i;
            onChanged();
            return this;
        }

        public Builder clearSampleRateHertz() {
            this.sampleRateHertz_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
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
            this.languageCode_ = InputAudioConfig.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                InputAudioConfig.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public boolean getEnableWordInfo() {
            return this.enableWordInfo_;
        }

        public Builder setEnableWordInfo(boolean z) {
            this.enableWordInfo_ = z;
            onChanged();
            return this;
        }

        public Builder clearEnableWordInfo() {
            this.enableWordInfo_ = false;
            onChanged();
            return this;
        }

        private void ensurePhraseHintsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.phraseHints_ = new LazyStringArrayList(this.phraseHints_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        @Deprecated
        public ProtocolStringList getPhraseHintsList() {
            return this.phraseHints_.getUnmodifiableView();
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        @Deprecated
        public int getPhraseHintsCount() {
            return this.phraseHints_.size();
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        @Deprecated
        public String getPhraseHints(int i) {
            return (String) this.phraseHints_.get(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        @Deprecated
        public ByteString getPhraseHintsBytes(int i) {
            return this.phraseHints_.getByteString(i);
        }

        @Deprecated
        public Builder setPhraseHints(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensurePhraseHintsIsMutable();
            this.phraseHints_.set(i, str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addPhraseHints(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensurePhraseHintsIsMutable();
            this.phraseHints_.add(str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllPhraseHints(Iterable<String> iterable) {
            ensurePhraseHintsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.phraseHints_);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearPhraseHints() {
            this.phraseHints_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addPhraseHintsBytes(ByteString byteString) {
            if (byteString != null) {
                InputAudioConfig.checkByteStringIsUtf8(byteString);
                ensurePhraseHintsIsMutable();
                this.phraseHints_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureSpeechContextsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.speechContexts_ = new ArrayList(this.speechContexts_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public List<SpeechContext> getSpeechContextsList() {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.speechContexts_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public int getSpeechContextsCount() {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.speechContexts_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public SpeechContext getSpeechContexts(int i) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.speechContexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSpeechContexts(int i, SpeechContext speechContext) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, speechContext);
            } else {
                if (speechContext == null) {
                    throw new NullPointerException();
                }
                ensureSpeechContextsIsMutable();
                this.speechContexts_.set(i, speechContext);
                onChanged();
            }
            return this;
        }

        public Builder setSpeechContexts(int i, SpeechContext.Builder builder) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechContextsIsMutable();
                this.speechContexts_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSpeechContexts(SpeechContext speechContext) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(speechContext);
            } else {
                if (speechContext == null) {
                    throw new NullPointerException();
                }
                ensureSpeechContextsIsMutable();
                this.speechContexts_.add(speechContext);
                onChanged();
            }
            return this;
        }

        public Builder addSpeechContexts(int i, SpeechContext speechContext) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, speechContext);
            } else {
                if (speechContext == null) {
                    throw new NullPointerException();
                }
                ensureSpeechContextsIsMutable();
                this.speechContexts_.add(i, speechContext);
                onChanged();
            }
            return this;
        }

        public Builder addSpeechContexts(SpeechContext.Builder builder) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechContextsIsMutable();
                this.speechContexts_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSpeechContexts(int i, SpeechContext.Builder builder) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechContextsIsMutable();
                this.speechContexts_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSpeechContexts(Iterable<? extends SpeechContext> iterable) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechContextsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.speechContexts_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSpeechContexts() {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.speechContexts_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSpeechContexts(int i) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechContextsIsMutable();
                this.speechContexts_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SpeechContext.Builder getSpeechContextsBuilder(int i) {
            return getSpeechContextsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public SpeechContextOrBuilder getSpeechContextsOrBuilder(int i) {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.speechContexts_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public List<? extends SpeechContextOrBuilder> getSpeechContextsOrBuilderList() {
            RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> repeatedFieldBuilderV3 = this.speechContextsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.speechContexts_);
        }

        public SpeechContext.Builder addSpeechContextsBuilder() {
            return getSpeechContextsFieldBuilder().addBuilder(SpeechContext.getDefaultInstance());
        }

        public SpeechContext.Builder addSpeechContextsBuilder(int i) {
            return getSpeechContextsFieldBuilder().addBuilder(i, SpeechContext.getDefaultInstance());
        }

        public List<SpeechContext.Builder> getSpeechContextsBuilderList() {
            return getSpeechContextsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SpeechContext, SpeechContext.Builder, SpeechContextOrBuilder> getSpeechContextsFieldBuilder() {
            if (this.speechContextsBuilder_ == null) {
                this.speechContextsBuilder_ = new RepeatedFieldBuilderV3<>(this.speechContexts_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.speechContexts_ = null;
            }
            return this.speechContextsBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public String getModel() {
            Object obj = this.model_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.model_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public ByteString getModelBytes() {
            Object obj = this.model_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.model_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setModel(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.model_ = str;
            onChanged();
            return this;
        }

        public Builder clearModel() {
            this.model_ = InputAudioConfig.getDefaultInstance().getModel();
            onChanged();
            return this;
        }

        public Builder setModelBytes(ByteString byteString) {
            if (byteString != null) {
                InputAudioConfig.checkByteStringIsUtf8(byteString);
                this.model_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public int getModelVariantValue() {
            return this.modelVariant_;
        }

        public Builder setModelVariantValue(int i) {
            this.modelVariant_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public SpeechModelVariant getModelVariant() {
            SpeechModelVariant valueOf = SpeechModelVariant.valueOf(this.modelVariant_);
            return valueOf == null ? SpeechModelVariant.UNRECOGNIZED : valueOf;
        }

        public Builder setModelVariant(SpeechModelVariant speechModelVariant) {
            if (speechModelVariant == null) {
                throw new NullPointerException();
            }
            this.modelVariant_ = speechModelVariant.getNumber();
            onChanged();
            return this;
        }

        public Builder clearModelVariant() {
            this.modelVariant_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.InputAudioConfigOrBuilder
        public boolean getSingleUtterance() {
            return this.singleUtterance_;
        }

        public Builder setSingleUtterance(boolean z) {
            this.singleUtterance_ = z;
            onChanged();
            return this;
        }

        public Builder clearSingleUtterance() {
            this.singleUtterance_ = false;
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

    public static InputAudioConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<InputAudioConfig> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<InputAudioConfig> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public InputAudioConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
