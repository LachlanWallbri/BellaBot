package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
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
/* loaded from: classes2.dex */
public final class SpeechWordInfo extends GeneratedMessageV3 implements SpeechWordInfoOrBuilder {
    public static final int CONFIDENCE_FIELD_NUMBER = 4;
    public static final int END_OFFSET_FIELD_NUMBER = 2;
    public static final int START_OFFSET_FIELD_NUMBER = 1;
    public static final int WORD_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private float confidence_;
    private Duration endOffset_;
    private byte memoizedIsInitialized;
    private Duration startOffset_;
    private volatile Object word_;
    private static final SpeechWordInfo DEFAULT_INSTANCE = new SpeechWordInfo();
    private static final Parser<SpeechWordInfo> PARSER = new AbstractParser<SpeechWordInfo>() { // from class: com.google.cloud.dialogflow.v2.SpeechWordInfo.1
        @Override // com.google.protobuf.Parser
        public SpeechWordInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SpeechWordInfo(codedInputStream, extensionRegistryLite);
        }
    };

    private SpeechWordInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SpeechWordInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.word_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SpeechWordInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SpeechWordInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        Duration.Builder builder;
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
                                builder = this.startOffset_ != null ? this.startOffset_.toBuilder() : null;
                                this.startOffset_ = (Duration) codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.startOffset_);
                                    this.startOffset_ = builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                builder = this.endOffset_ != null ? this.endOffset_.toBuilder() : null;
                                this.endOffset_ = (Duration) codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.endOffset_);
                                    this.endOffset_ = builder.buildPartial();
                                }
                            } else if (readTag == 26) {
                                this.word_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 37) {
                                this.confidence_ = codedInputStream.readFloat();
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
        return AudioConfigProto.f1390xf3516719;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AudioConfigProto.f1391x39186497.ensureFieldAccessorsInitialized(SpeechWordInfo.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public String getWord() {
        Object obj = this.word_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.word_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public ByteString getWordBytes() {
        Object obj = this.word_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.word_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public boolean hasStartOffset() {
        return this.startOffset_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public Duration getStartOffset() {
        Duration duration = this.startOffset_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public DurationOrBuilder getStartOffsetOrBuilder() {
        return getStartOffset();
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public boolean hasEndOffset() {
        return this.endOffset_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public Duration getEndOffset() {
        Duration duration = this.endOffset_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public DurationOrBuilder getEndOffsetOrBuilder() {
        return getEndOffset();
    }

    @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
    public float getConfidence() {
        return this.confidence_;
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
        if (this.startOffset_ != null) {
            codedOutputStream.writeMessage(1, getStartOffset());
        }
        if (this.endOffset_ != null) {
            codedOutputStream.writeMessage(2, getEndOffset());
        }
        if (!getWordBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.word_);
        }
        float f = this.confidence_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(4, f);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.startOffset_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getStartOffset()) : 0;
        if (this.endOffset_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getEndOffset());
        }
        if (!getWordBytes().isEmpty()) {
            computeMessageSize += GeneratedMessageV3.computeStringSize(3, this.word_);
        }
        float f = this.confidence_;
        if (f != 0.0f) {
            computeMessageSize += CodedOutputStream.computeFloatSize(4, f);
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
        if (!(obj instanceof SpeechWordInfo)) {
            return super.equals(obj);
        }
        SpeechWordInfo speechWordInfo = (SpeechWordInfo) obj;
        if (!getWord().equals(speechWordInfo.getWord()) || hasStartOffset() != speechWordInfo.hasStartOffset()) {
            return false;
        }
        if ((!hasStartOffset() || getStartOffset().equals(speechWordInfo.getStartOffset())) && hasEndOffset() == speechWordInfo.hasEndOffset()) {
            return (!hasEndOffset() || getEndOffset().equals(speechWordInfo.getEndOffset())) && Float.floatToIntBits(getConfidence()) == Float.floatToIntBits(speechWordInfo.getConfidence()) && this.unknownFields.equals(speechWordInfo.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 3) * 53) + getWord().hashCode();
        if (hasStartOffset()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getStartOffset().hashCode();
        }
        if (hasEndOffset()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getEndOffset().hashCode();
        }
        int floatToIntBits = (((((hashCode * 37) + 4) * 53) + Float.floatToIntBits(getConfidence())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = floatToIntBits;
        return floatToIntBits;
    }

    public static SpeechWordInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SpeechWordInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SpeechWordInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SpeechWordInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SpeechWordInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SpeechWordInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SpeechWordInfo parseFrom(InputStream inputStream) throws IOException {
        return (SpeechWordInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SpeechWordInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SpeechWordInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SpeechWordInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SpeechWordInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SpeechWordInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SpeechWordInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SpeechWordInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SpeechWordInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SpeechWordInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SpeechWordInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SpeechWordInfo speechWordInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(speechWordInfo);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SpeechWordInfoOrBuilder {
        private float confidence_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> endOffsetBuilder_;
        private Duration endOffset_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> startOffsetBuilder_;
        private Duration startOffset_;
        private Object word_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AudioConfigProto.f1390xf3516719;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AudioConfigProto.f1391x39186497.ensureFieldAccessorsInitialized(SpeechWordInfo.class, Builder.class);
        }

        private Builder() {
            this.word_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.word_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SpeechWordInfo.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.word_ = "";
            if (this.startOffsetBuilder_ == null) {
                this.startOffset_ = null;
            } else {
                this.startOffset_ = null;
                this.startOffsetBuilder_ = null;
            }
            if (this.endOffsetBuilder_ == null) {
                this.endOffset_ = null;
            } else {
                this.endOffset_ = null;
                this.endOffsetBuilder_ = null;
            }
            this.confidence_ = 0.0f;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AudioConfigProto.f1390xf3516719;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SpeechWordInfo getDefaultInstanceForType() {
            return SpeechWordInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SpeechWordInfo build() {
            SpeechWordInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SpeechWordInfo buildPartial() {
            SpeechWordInfo speechWordInfo = new SpeechWordInfo(this);
            speechWordInfo.word_ = this.word_;
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.startOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                speechWordInfo.startOffset_ = this.startOffset_;
            } else {
                speechWordInfo.startOffset_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.endOffsetBuilder_;
            if (singleFieldBuilderV32 == null) {
                speechWordInfo.endOffset_ = this.endOffset_;
            } else {
                speechWordInfo.endOffset_ = singleFieldBuilderV32.build();
            }
            speechWordInfo.confidence_ = this.confidence_;
            onBuilt();
            return speechWordInfo;
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
            if (message instanceof SpeechWordInfo) {
                return mergeFrom((SpeechWordInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SpeechWordInfo speechWordInfo) {
            if (speechWordInfo == SpeechWordInfo.getDefaultInstance()) {
                return this;
            }
            if (!speechWordInfo.getWord().isEmpty()) {
                this.word_ = speechWordInfo.word_;
                onChanged();
            }
            if (speechWordInfo.hasStartOffset()) {
                mergeStartOffset(speechWordInfo.getStartOffset());
            }
            if (speechWordInfo.hasEndOffset()) {
                mergeEndOffset(speechWordInfo.getEndOffset());
            }
            if (speechWordInfo.getConfidence() != 0.0f) {
                setConfidence(speechWordInfo.getConfidence());
            }
            mergeUnknownFields(speechWordInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SpeechWordInfo speechWordInfo = null;
            try {
                try {
                    SpeechWordInfo speechWordInfo2 = (SpeechWordInfo) SpeechWordInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (speechWordInfo2 != null) {
                        mergeFrom(speechWordInfo2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SpeechWordInfo speechWordInfo3 = (SpeechWordInfo) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        speechWordInfo = speechWordInfo3;
                        if (speechWordInfo != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (speechWordInfo != null) {
                    mergeFrom(speechWordInfo);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public String getWord() {
            Object obj = this.word_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.word_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public ByteString getWordBytes() {
            Object obj = this.word_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.word_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setWord(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.word_ = str;
            onChanged();
            return this;
        }

        public Builder clearWord() {
            this.word_ = SpeechWordInfo.getDefaultInstance().getWord();
            onChanged();
            return this;
        }

        public Builder setWordBytes(ByteString byteString) {
            if (byteString != null) {
                SpeechWordInfo.checkByteStringIsUtf8(byteString);
                this.word_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public boolean hasStartOffset() {
            return (this.startOffsetBuilder_ == null && this.startOffset_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public Duration getStartOffset() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.startOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration = this.startOffset_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setStartOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.startOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else {
                if (duration == null) {
                    throw new NullPointerException();
                }
                this.startOffset_ = duration;
                onChanged();
            }
            return this;
        }

        public Builder setStartOffset(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.startOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.startOffset_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeStartOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.startOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.startOffset_;
                if (duration2 != null) {
                    this.startOffset_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.startOffset_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearStartOffset() {
            if (this.startOffsetBuilder_ == null) {
                this.startOffset_ = null;
                onChanged();
            } else {
                this.startOffset_ = null;
                this.startOffsetBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getStartOffsetBuilder() {
            onChanged();
            return getStartOffsetFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public DurationOrBuilder getStartOffsetOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.startOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.startOffset_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getStartOffsetFieldBuilder() {
            if (this.startOffsetBuilder_ == null) {
                this.startOffsetBuilder_ = new SingleFieldBuilderV3<>(getStartOffset(), getParentForChildren(), isClean());
                this.startOffset_ = null;
            }
            return this.startOffsetBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public boolean hasEndOffset() {
            return (this.endOffsetBuilder_ == null && this.endOffset_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public Duration getEndOffset() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration = this.endOffset_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setEndOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else {
                if (duration == null) {
                    throw new NullPointerException();
                }
                this.endOffset_ = duration;
                onChanged();
            }
            return this;
        }

        public Builder setEndOffset(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.endOffset_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeEndOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.endOffset_;
                if (duration2 != null) {
                    this.endOffset_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.endOffset_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearEndOffset() {
            if (this.endOffsetBuilder_ == null) {
                this.endOffset_ = null;
                onChanged();
            } else {
                this.endOffset_ = null;
                this.endOffsetBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getEndOffsetBuilder() {
            onChanged();
            return getEndOffsetFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public DurationOrBuilder getEndOffsetOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.endOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.endOffset_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getEndOffsetFieldBuilder() {
            if (this.endOffsetBuilder_ == null) {
                this.endOffsetBuilder_ = new SingleFieldBuilderV3<>(getEndOffset(), getParentForChildren(), isClean());
                this.endOffset_ = null;
            }
            return this.endOffsetBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SpeechWordInfoOrBuilder
        public float getConfidence() {
            return this.confidence_;
        }

        public Builder setConfidence(float f) {
            this.confidence_ = f;
            onChanged();
            return this;
        }

        public Builder clearConfidence() {
            this.confidence_ = 0.0f;
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

    public static SpeechWordInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SpeechWordInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SpeechWordInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SpeechWordInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
