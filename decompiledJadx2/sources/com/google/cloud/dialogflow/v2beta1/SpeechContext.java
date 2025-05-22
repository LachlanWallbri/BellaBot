package com.google.cloud.dialogflow.v2beta1;

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
public final class SpeechContext extends GeneratedMessageV3 implements SpeechContextOrBuilder {
    public static final int BOOST_FIELD_NUMBER = 2;
    private static final SpeechContext DEFAULT_INSTANCE = new SpeechContext();
    private static final Parser<SpeechContext> PARSER = new AbstractParser<SpeechContext>() { // from class: com.google.cloud.dialogflow.v2beta1.SpeechContext.1
        @Override // com.google.protobuf.Parser
        public SpeechContext parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SpeechContext(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PHRASES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private float boost_;
    private byte memoizedIsInitialized;
    private LazyStringList phrases_;

    private SpeechContext(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SpeechContext() {
        this.memoizedIsInitialized = (byte) -1;
        this.phrases_ = LazyStringArrayList.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SpeechContext();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SpeechContext(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!(z2 & true)) {
                                    this.phrases_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.phrases_.add(readStringRequireUtf8);
                            } else if (readTag == 21) {
                                this.boost_ = codedInputStream.readFloat();
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
                if (z2 & true) {
                    this.phrases_ = this.phrases_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AudioConfigProto.f1607x38d160a3;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AudioConfigProto.f1608xc733421.ensureFieldAccessorsInitialized(SpeechContext.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
    public ProtocolStringList getPhrasesList() {
        return this.phrases_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
    public int getPhrasesCount() {
        return this.phrases_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
    public String getPhrases(int i) {
        return (String) this.phrases_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
    public ByteString getPhrasesBytes(int i) {
        return this.phrases_.getByteString(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
    public float getBoost() {
        return this.boost_;
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
        for (int i = 0; i < this.phrases_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.phrases_.getRaw(i));
        }
        float f = this.boost_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(2, f);
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
        for (int i3 = 0; i3 < this.phrases_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.phrases_.getRaw(i3));
        }
        int size = 0 + i2 + (getPhrasesList().size() * 1);
        float f = this.boost_;
        if (f != 0.0f) {
            size += CodedOutputStream.computeFloatSize(2, f);
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
        if (!(obj instanceof SpeechContext)) {
            return super.equals(obj);
        }
        SpeechContext speechContext = (SpeechContext) obj;
        return getPhrasesList().equals(speechContext.getPhrasesList()) && Float.floatToIntBits(getBoost()) == Float.floatToIntBits(speechContext.getBoost()) && this.unknownFields.equals(speechContext.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getPhrasesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getPhrasesList().hashCode();
        }
        int floatToIntBits = (((((hashCode * 37) + 2) * 53) + Float.floatToIntBits(getBoost())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = floatToIntBits;
        return floatToIntBits;
    }

    public static SpeechContext parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SpeechContext parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SpeechContext parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SpeechContext parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SpeechContext parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SpeechContext parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SpeechContext parseFrom(InputStream inputStream) throws IOException {
        return (SpeechContext) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SpeechContext parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SpeechContext) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SpeechContext parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SpeechContext) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SpeechContext parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SpeechContext) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SpeechContext parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SpeechContext) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SpeechContext parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SpeechContext) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SpeechContext speechContext) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(speechContext);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SpeechContextOrBuilder {
        private int bitField0_;
        private float boost_;
        private LazyStringList phrases_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AudioConfigProto.f1607x38d160a3;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AudioConfigProto.f1608xc733421.ensureFieldAccessorsInitialized(SpeechContext.class, Builder.class);
        }

        private Builder() {
            this.phrases_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.phrases_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SpeechContext.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.phrases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.boost_ = 0.0f;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AudioConfigProto.f1607x38d160a3;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SpeechContext getDefaultInstanceForType() {
            return SpeechContext.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SpeechContext build() {
            SpeechContext buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SpeechContext buildPartial() {
            SpeechContext speechContext = new SpeechContext(this);
            if ((this.bitField0_ & 1) != 0) {
                this.phrases_ = this.phrases_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            speechContext.phrases_ = this.phrases_;
            speechContext.boost_ = this.boost_;
            onBuilt();
            return speechContext;
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
            if (message instanceof SpeechContext) {
                return mergeFrom((SpeechContext) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SpeechContext speechContext) {
            if (speechContext == SpeechContext.getDefaultInstance()) {
                return this;
            }
            if (!speechContext.phrases_.isEmpty()) {
                if (this.phrases_.isEmpty()) {
                    this.phrases_ = speechContext.phrases_;
                    this.bitField0_ &= -2;
                } else {
                    ensurePhrasesIsMutable();
                    this.phrases_.addAll(speechContext.phrases_);
                }
                onChanged();
            }
            if (speechContext.getBoost() != 0.0f) {
                setBoost(speechContext.getBoost());
            }
            mergeUnknownFields(speechContext.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SpeechContext speechContext = null;
            try {
                try {
                    SpeechContext speechContext2 = (SpeechContext) SpeechContext.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (speechContext2 != null) {
                        mergeFrom(speechContext2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SpeechContext speechContext3 = (SpeechContext) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        speechContext = speechContext3;
                        if (speechContext != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (speechContext != null) {
                    mergeFrom(speechContext);
                }
                throw th;
            }
        }

        private void ensurePhrasesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.phrases_ = new LazyStringArrayList(this.phrases_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
        public ProtocolStringList getPhrasesList() {
            return this.phrases_.getUnmodifiableView();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
        public int getPhrasesCount() {
            return this.phrases_.size();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
        public String getPhrases(int i) {
            return (String) this.phrases_.get(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
        public ByteString getPhrasesBytes(int i) {
            return this.phrases_.getByteString(i);
        }

        public Builder setPhrases(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensurePhrasesIsMutable();
            this.phrases_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addPhrases(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensurePhrasesIsMutable();
            this.phrases_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllPhrases(Iterable<String> iterable) {
            ensurePhrasesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.phrases_);
            onChanged();
            return this;
        }

        public Builder clearPhrases() {
            this.phrases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addPhrasesBytes(ByteString byteString) {
            if (byteString != null) {
                SpeechContext.checkByteStringIsUtf8(byteString);
                ensurePhrasesIsMutable();
                this.phrases_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SpeechContextOrBuilder
        public float getBoost() {
            return this.boost_;
        }

        public Builder setBoost(float f) {
            this.boost_ = f;
            onChanged();
            return this;
        }

        public Builder clearBoost() {
            this.boost_ = 0.0f;
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

    public static SpeechContext getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SpeechContext> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SpeechContext> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SpeechContext getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
