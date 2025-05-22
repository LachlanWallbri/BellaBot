package com.google.cloud.texttospeech.p050v1;

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
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class SynthesisInput extends GeneratedMessageV3 implements SynthesisInputOrBuilder {
    private static final SynthesisInput DEFAULT_INSTANCE = new SynthesisInput();
    private static final Parser<SynthesisInput> PARSER = new AbstractParser<SynthesisInput>() { // from class: com.google.cloud.texttospeech.v1.SynthesisInput.1
        @Override // com.google.protobuf.Parser
        public SynthesisInput parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SynthesisInput(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SSML_FIELD_NUMBER = 2;
    public static final int TEXT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int inputSourceCase_;
    private Object inputSource_;
    private byte memoizedIsInitialized;

    private SynthesisInput(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.inputSourceCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private SynthesisInput() {
        this.inputSourceCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SynthesisInput();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SynthesisInput(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.inputSourceCase_ = 1;
                            this.inputSource_ = readStringRequireUtf8;
                        } else if (readTag == 18) {
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            this.inputSourceCase_ = 2;
                            this.inputSource_ = readStringRequireUtf82;
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
        return TextToSpeechProto.f1885x3a0ef608;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1886xd44b9486.ensureFieldAccessorsInitialized(SynthesisInput.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum InputSourceCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        TEXT(1),
        SSML(2),
        INPUTSOURCE_NOT_SET(0);

        private final int value;

        InputSourceCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static InputSourceCase valueOf(int i) {
            return forNumber(i);
        }

        public static InputSourceCase forNumber(int i) {
            if (i == 0) {
                return INPUTSOURCE_NOT_SET;
            }
            if (i == 1) {
                return TEXT;
            }
            if (i != 2) {
                return null;
            }
            return SSML;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public InputSourceCase getInputSourceCase() {
        return InputSourceCase.forNumber(this.inputSourceCase_);
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public boolean hasText() {
        return this.inputSourceCase_ == 1;
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public String getText() {
        String str = this.inputSourceCase_ == 1 ? this.inputSource_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.inputSourceCase_ == 1) {
            this.inputSource_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public ByteString getTextBytes() {
        String str = this.inputSourceCase_ == 1 ? this.inputSource_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.inputSourceCase_ == 1) {
                this.inputSource_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public boolean hasSsml() {
        return this.inputSourceCase_ == 2;
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public String getSsml() {
        String str = this.inputSourceCase_ == 2 ? this.inputSource_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.inputSourceCase_ == 2) {
            this.inputSource_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
    public ByteString getSsmlBytes() {
        String str = this.inputSourceCase_ == 2 ? this.inputSource_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.inputSourceCase_ == 2) {
                this.inputSource_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
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
        if (this.inputSourceCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.inputSource_);
        }
        if (this.inputSourceCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.inputSource_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = this.inputSourceCase_ == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.inputSource_) : 0;
        if (this.inputSourceCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.inputSource_);
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
        if (!(obj instanceof SynthesisInput)) {
            return super.equals(obj);
        }
        SynthesisInput synthesisInput = (SynthesisInput) obj;
        if (!getInputSourceCase().equals(synthesisInput.getInputSourceCase())) {
            return false;
        }
        int i = this.inputSourceCase_;
        if (i == 1) {
            if (!getText().equals(synthesisInput.getText())) {
                return false;
            }
        } else if (i == 2 && !getSsml().equals(synthesisInput.getSsml())) {
            return false;
        }
        return this.unknownFields.equals(synthesisInput.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.inputSourceCase_;
        if (i2 == 1) {
            i = ((hashCode2 * 37) + 1) * 53;
            hashCode = getText().hashCode();
        } else {
            if (i2 == 2) {
                i = ((hashCode2 * 37) + 2) * 53;
                hashCode = getSsml().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        hashCode2 = i + hashCode;
        int hashCode32 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static SynthesisInput parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SynthesisInput parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SynthesisInput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SynthesisInput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SynthesisInput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SynthesisInput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SynthesisInput parseFrom(InputStream inputStream) throws IOException {
        return (SynthesisInput) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SynthesisInput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesisInput) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesisInput parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SynthesisInput) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SynthesisInput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesisInput) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesisInput parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SynthesisInput) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SynthesisInput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesisInput) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SynthesisInput synthesisInput) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(synthesisInput);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SynthesisInputOrBuilder {
        private int inputSourceCase_;
        private Object inputSource_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1885x3a0ef608;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1886xd44b9486.ensureFieldAccessorsInitialized(SynthesisInput.class, Builder.class);
        }

        private Builder() {
            this.inputSourceCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.inputSourceCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SynthesisInput.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.inputSourceCase_ = 0;
            this.inputSource_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1885x3a0ef608;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SynthesisInput getDefaultInstanceForType() {
            return SynthesisInput.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesisInput build() {
            SynthesisInput buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesisInput buildPartial() {
            SynthesisInput synthesisInput = new SynthesisInput(this);
            if (this.inputSourceCase_ == 1) {
                synthesisInput.inputSource_ = this.inputSource_;
            }
            if (this.inputSourceCase_ == 2) {
                synthesisInput.inputSource_ = this.inputSource_;
            }
            synthesisInput.inputSourceCase_ = this.inputSourceCase_;
            onBuilt();
            return synthesisInput;
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
            if (message instanceof SynthesisInput) {
                return mergeFrom((SynthesisInput) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SynthesisInput synthesisInput) {
            if (synthesisInput == SynthesisInput.getDefaultInstance()) {
                return this;
            }
            int i = C26382.f1878x56971a0d[synthesisInput.getInputSourceCase().ordinal()];
            if (i == 1) {
                this.inputSourceCase_ = 1;
                this.inputSource_ = synthesisInput.inputSource_;
                onChanged();
            } else if (i == 2) {
                this.inputSourceCase_ = 2;
                this.inputSource_ = synthesisInput.inputSource_;
                onChanged();
            }
            mergeUnknownFields(synthesisInput.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SynthesisInput synthesisInput = null;
            try {
                try {
                    SynthesisInput synthesisInput2 = (SynthesisInput) SynthesisInput.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (synthesisInput2 != null) {
                        mergeFrom(synthesisInput2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SynthesisInput synthesisInput3 = (SynthesisInput) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        synthesisInput = synthesisInput3;
                        if (synthesisInput != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (synthesisInput != null) {
                    mergeFrom(synthesisInput);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public InputSourceCase getInputSourceCase() {
            return InputSourceCase.forNumber(this.inputSourceCase_);
        }

        public Builder clearInputSource() {
            this.inputSourceCase_ = 0;
            this.inputSource_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public boolean hasText() {
            return this.inputSourceCase_ == 1;
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public String getText() {
            String str = this.inputSourceCase_ == 1 ? this.inputSource_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.inputSourceCase_ == 1) {
                    this.inputSource_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public ByteString getTextBytes() {
            String str = this.inputSourceCase_ == 1 ? this.inputSource_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.inputSourceCase_ == 1) {
                    this.inputSource_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setText(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.inputSourceCase_ = 1;
            this.inputSource_ = str;
            onChanged();
            return this;
        }

        public Builder clearText() {
            if (this.inputSourceCase_ == 1) {
                this.inputSourceCase_ = 0;
                this.inputSource_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setTextBytes(ByteString byteString) {
            if (byteString != null) {
                SynthesisInput.checkByteStringIsUtf8(byteString);
                this.inputSourceCase_ = 1;
                this.inputSource_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public boolean hasSsml() {
            return this.inputSourceCase_ == 2;
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public String getSsml() {
            String str = this.inputSourceCase_ == 2 ? this.inputSource_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.inputSourceCase_ == 2) {
                    this.inputSource_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.texttospeech.p050v1.SynthesisInputOrBuilder
        public ByteString getSsmlBytes() {
            String str = this.inputSourceCase_ == 2 ? this.inputSource_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.inputSourceCase_ == 2) {
                    this.inputSource_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setSsml(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.inputSourceCase_ = 2;
            this.inputSource_ = str;
            onChanged();
            return this;
        }

        public Builder clearSsml() {
            if (this.inputSourceCase_ == 2) {
                this.inputSourceCase_ = 0;
                this.inputSource_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setSsmlBytes(ByteString byteString) {
            if (byteString != null) {
                SynthesisInput.checkByteStringIsUtf8(byteString);
                this.inputSourceCase_ = 2;
                this.inputSource_ = byteString;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.cloud.texttospeech.v1.SynthesisInput$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C26382 {

        /* renamed from: $SwitchMap$com$google$cloud$texttospeech$v1$SynthesisInput$InputSourceCase */
        static final /* synthetic */ int[] f1878x56971a0d = new int[InputSourceCase.values().length];

        static {
            try {
                f1878x56971a0d[InputSourceCase.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1878x56971a0d[InputSourceCase.SSML.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1878x56971a0d[InputSourceCase.INPUTSOURCE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static SynthesisInput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SynthesisInput> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SynthesisInput> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SynthesisInput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
