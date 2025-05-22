package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.SpeechWordInfo;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class StreamingRecognitionResult extends GeneratedMessageV3 implements StreamingRecognitionResultOrBuilder {
    public static final int CONFIDENCE_FIELD_NUMBER = 4;
    public static final int IS_FINAL_FIELD_NUMBER = 3;
    public static final int MESSAGE_TYPE_FIELD_NUMBER = 1;
    public static final int SPEECH_END_OFFSET_FIELD_NUMBER = 8;
    public static final int SPEECH_WORD_INFO_FIELD_NUMBER = 7;
    public static final int STABILITY_FIELD_NUMBER = 6;
    public static final int TRANSCRIPT_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private float confidence_;
    private boolean isFinal_;
    private byte memoizedIsInitialized;
    private int messageType_;
    private Duration speechEndOffset_;
    private List<SpeechWordInfo> speechWordInfo_;
    private float stability_;
    private volatile Object transcript_;
    private static final StreamingRecognitionResult DEFAULT_INSTANCE = new StreamingRecognitionResult();
    private static final Parser<StreamingRecognitionResult> PARSER = new AbstractParser<StreamingRecognitionResult>() { // from class: com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResult.1
        @Override // com.google.protobuf.Parser
        public StreamingRecognitionResult parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new StreamingRecognitionResult(codedInputStream, extensionRegistryLite);
        }
    };

    private StreamingRecognitionResult(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private StreamingRecognitionResult() {
        this.memoizedIsInitialized = (byte) -1;
        this.messageType_ = 0;
        this.transcript_ = "";
        this.speechWordInfo_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new StreamingRecognitionResult();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private StreamingRecognitionResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 8) {
                                this.messageType_ = codedInputStream.readEnum();
                            } else if (readTag == 18) {
                                this.transcript_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.isFinal_ = codedInputStream.readBool();
                            } else if (readTag == 37) {
                                this.confidence_ = codedInputStream.readFloat();
                            } else if (readTag == 53) {
                                this.stability_ = codedInputStream.readFloat();
                            } else if (readTag == 58) {
                                if (!(z2 & true)) {
                                    this.speechWordInfo_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.speechWordInfo_.add(codedInputStream.readMessage(SpeechWordInfo.parser(), extensionRegistryLite));
                            } else if (readTag == 66) {
                                Duration.Builder builder = this.speechEndOffset_ != null ? this.speechEndOffset_.toBuilder() : null;
                                this.speechEndOffset_ = (Duration) codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.speechEndOffset_);
                                    this.speechEndOffset_ = builder.buildPartial();
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
                if (z2 & true) {
                    this.speechWordInfo_ = Collections.unmodifiableList(this.speechWordInfo_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProto.f1864xb0f95e7a;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1865x94c76af8.ensureFieldAccessorsInitialized(StreamingRecognitionResult.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum MessageType implements ProtocolMessageEnum {
        MESSAGE_TYPE_UNSPECIFIED(0),
        TRANSCRIPT(1),
        END_OF_SINGLE_UTTERANCE(2),
        UNRECOGNIZED(-1);

        public static final int END_OF_SINGLE_UTTERANCE_VALUE = 2;
        public static final int MESSAGE_TYPE_UNSPECIFIED_VALUE = 0;
        public static final int TRANSCRIPT_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<MessageType> internalValueMap = new Internal.EnumLiteMap<MessageType>() { // from class: com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResult.MessageType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MessageType findValueByNumber(int i) {
                return MessageType.forNumber(i);
            }
        };
        private static final MessageType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static MessageType valueOf(int i) {
            return forNumber(i);
        }

        public static MessageType forNumber(int i) {
            if (i == 0) {
                return MESSAGE_TYPE_UNSPECIFIED;
            }
            if (i == 1) {
                return TRANSCRIPT;
            }
            if (i != 2) {
                return null;
            }
            return END_OF_SINGLE_UTTERANCE;
        }

        public static Internal.EnumLiteMap<MessageType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return StreamingRecognitionResult.getDescriptor().getEnumTypes().get(0);
        }

        public static MessageType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        MessageType(int i) {
            this.value = i;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public int getMessageTypeValue() {
        return this.messageType_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public MessageType getMessageType() {
        MessageType valueOf = MessageType.valueOf(this.messageType_);
        return valueOf == null ? MessageType.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public String getTranscript() {
        Object obj = this.transcript_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.transcript_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public ByteString getTranscriptBytes() {
        Object obj = this.transcript_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.transcript_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public boolean getIsFinal() {
        return this.isFinal_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public float getConfidence() {
        return this.confidence_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public float getStability() {
        return this.stability_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public List<SpeechWordInfo> getSpeechWordInfoList() {
        return this.speechWordInfo_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public List<? extends SpeechWordInfoOrBuilder> getSpeechWordInfoOrBuilderList() {
        return this.speechWordInfo_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public int getSpeechWordInfoCount() {
        return this.speechWordInfo_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public SpeechWordInfo getSpeechWordInfo(int i) {
        return this.speechWordInfo_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public SpeechWordInfoOrBuilder getSpeechWordInfoOrBuilder(int i) {
        return this.speechWordInfo_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public boolean hasSpeechEndOffset() {
        return this.speechEndOffset_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public Duration getSpeechEndOffset() {
        Duration duration = this.speechEndOffset_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
    public DurationOrBuilder getSpeechEndOffsetOrBuilder() {
        return getSpeechEndOffset();
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
        if (this.messageType_ != MessageType.MESSAGE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.messageType_);
        }
        if (!getTranscriptBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.transcript_);
        }
        boolean z = this.isFinal_;
        if (z) {
            codedOutputStream.writeBool(3, z);
        }
        float f = this.confidence_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(4, f);
        }
        float f2 = this.stability_;
        if (f2 != 0.0f) {
            codedOutputStream.writeFloat(6, f2);
        }
        for (int i = 0; i < this.speechWordInfo_.size(); i++) {
            codedOutputStream.writeMessage(7, this.speechWordInfo_.get(i));
        }
        if (this.speechEndOffset_ != null) {
            codedOutputStream.writeMessage(8, getSpeechEndOffset());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.messageType_ != MessageType.MESSAGE_TYPE_UNSPECIFIED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.messageType_) + 0 : 0;
        if (!getTranscriptBytes().isEmpty()) {
            computeEnumSize += GeneratedMessageV3.computeStringSize(2, this.transcript_);
        }
        boolean z = this.isFinal_;
        if (z) {
            computeEnumSize += CodedOutputStream.computeBoolSize(3, z);
        }
        float f = this.confidence_;
        if (f != 0.0f) {
            computeEnumSize += CodedOutputStream.computeFloatSize(4, f);
        }
        float f2 = this.stability_;
        if (f2 != 0.0f) {
            computeEnumSize += CodedOutputStream.computeFloatSize(6, f2);
        }
        for (int i2 = 0; i2 < this.speechWordInfo_.size(); i2++) {
            computeEnumSize += CodedOutputStream.computeMessageSize(7, this.speechWordInfo_.get(i2));
        }
        if (this.speechEndOffset_ != null) {
            computeEnumSize += CodedOutputStream.computeMessageSize(8, getSpeechEndOffset());
        }
        int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StreamingRecognitionResult)) {
            return super.equals(obj);
        }
        StreamingRecognitionResult streamingRecognitionResult = (StreamingRecognitionResult) obj;
        if (this.messageType_ == streamingRecognitionResult.messageType_ && getTranscript().equals(streamingRecognitionResult.getTranscript()) && getIsFinal() == streamingRecognitionResult.getIsFinal() && Float.floatToIntBits(getConfidence()) == Float.floatToIntBits(streamingRecognitionResult.getConfidence()) && Float.floatToIntBits(getStability()) == Float.floatToIntBits(streamingRecognitionResult.getStability()) && getSpeechWordInfoList().equals(streamingRecognitionResult.getSpeechWordInfoList()) && hasSpeechEndOffset() == streamingRecognitionResult.hasSpeechEndOffset()) {
            return (!hasSpeechEndOffset() || getSpeechEndOffset().equals(streamingRecognitionResult.getSpeechEndOffset())) && this.unknownFields.equals(streamingRecognitionResult.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.messageType_) * 37) + 2) * 53) + getTranscript().hashCode()) * 37) + 3) * 53) + Internal.hashBoolean(getIsFinal())) * 37) + 4) * 53) + Float.floatToIntBits(getConfidence())) * 37) + 6) * 53) + Float.floatToIntBits(getStability());
        if (getSpeechWordInfoCount() > 0) {
            hashCode = (((hashCode * 37) + 7) * 53) + getSpeechWordInfoList().hashCode();
        }
        if (hasSpeechEndOffset()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getSpeechEndOffset().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static StreamingRecognitionResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static StreamingRecognitionResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static StreamingRecognitionResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static StreamingRecognitionResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static StreamingRecognitionResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static StreamingRecognitionResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static StreamingRecognitionResult parseFrom(InputStream inputStream) throws IOException {
        return (StreamingRecognitionResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static StreamingRecognitionResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamingRecognitionResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StreamingRecognitionResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StreamingRecognitionResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static StreamingRecognitionResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamingRecognitionResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static StreamingRecognitionResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StreamingRecognitionResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static StreamingRecognitionResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StreamingRecognitionResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(StreamingRecognitionResult streamingRecognitionResult) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(streamingRecognitionResult);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StreamingRecognitionResultOrBuilder {
        private int bitField0_;
        private float confidence_;
        private boolean isFinal_;
        private int messageType_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> speechEndOffsetBuilder_;
        private Duration speechEndOffset_;
        private RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> speechWordInfoBuilder_;
        private List<SpeechWordInfo> speechWordInfo_;
        private float stability_;
        private Object transcript_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.f1864xb0f95e7a;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1865x94c76af8.ensureFieldAccessorsInitialized(StreamingRecognitionResult.class, Builder.class);
        }

        private Builder() {
            this.messageType_ = 0;
            this.transcript_ = "";
            this.speechWordInfo_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.messageType_ = 0;
            this.transcript_ = "";
            this.speechWordInfo_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (StreamingRecognitionResult.alwaysUseFieldBuilders) {
                getSpeechWordInfoFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.messageType_ = 0;
            this.transcript_ = "";
            this.isFinal_ = false;
            this.confidence_ = 0.0f;
            this.stability_ = 0.0f;
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.speechWordInfo_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.speechEndOffsetBuilder_ == null) {
                this.speechEndOffset_ = null;
            } else {
                this.speechEndOffset_ = null;
                this.speechEndOffsetBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.f1864xb0f95e7a;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public StreamingRecognitionResult getDefaultInstanceForType() {
            return StreamingRecognitionResult.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StreamingRecognitionResult build() {
            StreamingRecognitionResult buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public StreamingRecognitionResult buildPartial() {
            StreamingRecognitionResult streamingRecognitionResult = new StreamingRecognitionResult(this);
            int i = this.bitField0_;
            streamingRecognitionResult.messageType_ = this.messageType_;
            streamingRecognitionResult.transcript_ = this.transcript_;
            streamingRecognitionResult.isFinal_ = this.isFinal_;
            streamingRecognitionResult.confidence_ = this.confidence_;
            streamingRecognitionResult.stability_ = this.stability_;
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                streamingRecognitionResult.speechWordInfo_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.speechWordInfo_ = Collections.unmodifiableList(this.speechWordInfo_);
                    this.bitField0_ &= -2;
                }
                streamingRecognitionResult.speechWordInfo_ = this.speechWordInfo_;
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.speechEndOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                streamingRecognitionResult.speechEndOffset_ = this.speechEndOffset_;
            } else {
                streamingRecognitionResult.speechEndOffset_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return streamingRecognitionResult;
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
            if (message instanceof StreamingRecognitionResult) {
                return mergeFrom((StreamingRecognitionResult) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(StreamingRecognitionResult streamingRecognitionResult) {
            if (streamingRecognitionResult == StreamingRecognitionResult.getDefaultInstance()) {
                return this;
            }
            if (streamingRecognitionResult.messageType_ != 0) {
                setMessageTypeValue(streamingRecognitionResult.getMessageTypeValue());
            }
            if (!streamingRecognitionResult.getTranscript().isEmpty()) {
                this.transcript_ = streamingRecognitionResult.transcript_;
                onChanged();
            }
            if (streamingRecognitionResult.getIsFinal()) {
                setIsFinal(streamingRecognitionResult.getIsFinal());
            }
            if (streamingRecognitionResult.getConfidence() != 0.0f) {
                setConfidence(streamingRecognitionResult.getConfidence());
            }
            if (streamingRecognitionResult.getStability() != 0.0f) {
                setStability(streamingRecognitionResult.getStability());
            }
            if (this.speechWordInfoBuilder_ == null) {
                if (!streamingRecognitionResult.speechWordInfo_.isEmpty()) {
                    if (this.speechWordInfo_.isEmpty()) {
                        this.speechWordInfo_ = streamingRecognitionResult.speechWordInfo_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSpeechWordInfoIsMutable();
                        this.speechWordInfo_.addAll(streamingRecognitionResult.speechWordInfo_);
                    }
                    onChanged();
                }
            } else if (!streamingRecognitionResult.speechWordInfo_.isEmpty()) {
                if (!this.speechWordInfoBuilder_.isEmpty()) {
                    this.speechWordInfoBuilder_.addAllMessages(streamingRecognitionResult.speechWordInfo_);
                } else {
                    this.speechWordInfoBuilder_.dispose();
                    this.speechWordInfoBuilder_ = null;
                    this.speechWordInfo_ = streamingRecognitionResult.speechWordInfo_;
                    this.bitField0_ &= -2;
                    this.speechWordInfoBuilder_ = StreamingRecognitionResult.alwaysUseFieldBuilders ? getSpeechWordInfoFieldBuilder() : null;
                }
            }
            if (streamingRecognitionResult.hasSpeechEndOffset()) {
                mergeSpeechEndOffset(streamingRecognitionResult.getSpeechEndOffset());
            }
            mergeUnknownFields(streamingRecognitionResult.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            StreamingRecognitionResult streamingRecognitionResult = null;
            try {
                try {
                    StreamingRecognitionResult streamingRecognitionResult2 = (StreamingRecognitionResult) StreamingRecognitionResult.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (streamingRecognitionResult2 != null) {
                        mergeFrom(streamingRecognitionResult2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    StreamingRecognitionResult streamingRecognitionResult3 = (StreamingRecognitionResult) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        streamingRecognitionResult = streamingRecognitionResult3;
                        if (streamingRecognitionResult != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (streamingRecognitionResult != null) {
                    mergeFrom(streamingRecognitionResult);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public int getMessageTypeValue() {
            return this.messageType_;
        }

        public Builder setMessageTypeValue(int i) {
            this.messageType_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public MessageType getMessageType() {
            MessageType valueOf = MessageType.valueOf(this.messageType_);
            return valueOf == null ? MessageType.UNRECOGNIZED : valueOf;
        }

        public Builder setMessageType(MessageType messageType) {
            if (messageType == null) {
                throw new NullPointerException();
            }
            this.messageType_ = messageType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearMessageType() {
            this.messageType_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public String getTranscript() {
            Object obj = this.transcript_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.transcript_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public ByteString getTranscriptBytes() {
            Object obj = this.transcript_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.transcript_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTranscript(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.transcript_ = str;
            onChanged();
            return this;
        }

        public Builder clearTranscript() {
            this.transcript_ = StreamingRecognitionResult.getDefaultInstance().getTranscript();
            onChanged();
            return this;
        }

        public Builder setTranscriptBytes(ByteString byteString) {
            if (byteString != null) {
                StreamingRecognitionResult.checkByteStringIsUtf8(byteString);
                this.transcript_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public boolean getIsFinal() {
            return this.isFinal_;
        }

        public Builder setIsFinal(boolean z) {
            this.isFinal_ = z;
            onChanged();
            return this;
        }

        public Builder clearIsFinal() {
            this.isFinal_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public float getStability() {
            return this.stability_;
        }

        public Builder setStability(float f) {
            this.stability_ = f;
            onChanged();
            return this;
        }

        public Builder clearStability() {
            this.stability_ = 0.0f;
            onChanged();
            return this;
        }

        private void ensureSpeechWordInfoIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.speechWordInfo_ = new ArrayList(this.speechWordInfo_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public List<SpeechWordInfo> getSpeechWordInfoList() {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.speechWordInfo_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public int getSpeechWordInfoCount() {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.speechWordInfo_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public SpeechWordInfo getSpeechWordInfo(int i) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.speechWordInfo_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSpeechWordInfo(int i, SpeechWordInfo speechWordInfo) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, speechWordInfo);
            } else {
                if (speechWordInfo == null) {
                    throw new NullPointerException();
                }
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.set(i, speechWordInfo);
                onChanged();
            }
            return this;
        }

        public Builder setSpeechWordInfo(int i, SpeechWordInfo.Builder builder) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSpeechWordInfo(SpeechWordInfo speechWordInfo) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(speechWordInfo);
            } else {
                if (speechWordInfo == null) {
                    throw new NullPointerException();
                }
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.add(speechWordInfo);
                onChanged();
            }
            return this;
        }

        public Builder addSpeechWordInfo(int i, SpeechWordInfo speechWordInfo) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, speechWordInfo);
            } else {
                if (speechWordInfo == null) {
                    throw new NullPointerException();
                }
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.add(i, speechWordInfo);
                onChanged();
            }
            return this;
        }

        public Builder addSpeechWordInfo(SpeechWordInfo.Builder builder) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSpeechWordInfo(int i, SpeechWordInfo.Builder builder) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSpeechWordInfo(Iterable<? extends SpeechWordInfo> iterable) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechWordInfoIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.speechWordInfo_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSpeechWordInfo() {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.speechWordInfo_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSpeechWordInfo(int i) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSpeechWordInfoIsMutable();
                this.speechWordInfo_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SpeechWordInfo.Builder getSpeechWordInfoBuilder(int i) {
            return getSpeechWordInfoFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public SpeechWordInfoOrBuilder getSpeechWordInfoOrBuilder(int i) {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.speechWordInfo_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public List<? extends SpeechWordInfoOrBuilder> getSpeechWordInfoOrBuilderList() {
            RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> repeatedFieldBuilderV3 = this.speechWordInfoBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.speechWordInfo_);
        }

        public SpeechWordInfo.Builder addSpeechWordInfoBuilder() {
            return getSpeechWordInfoFieldBuilder().addBuilder(SpeechWordInfo.getDefaultInstance());
        }

        public SpeechWordInfo.Builder addSpeechWordInfoBuilder(int i) {
            return getSpeechWordInfoFieldBuilder().addBuilder(i, SpeechWordInfo.getDefaultInstance());
        }

        public List<SpeechWordInfo.Builder> getSpeechWordInfoBuilderList() {
            return getSpeechWordInfoFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SpeechWordInfo, SpeechWordInfo.Builder, SpeechWordInfoOrBuilder> getSpeechWordInfoFieldBuilder() {
            if (this.speechWordInfoBuilder_ == null) {
                this.speechWordInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.speechWordInfo_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.speechWordInfo_ = null;
            }
            return this.speechWordInfoBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public boolean hasSpeechEndOffset() {
            return (this.speechEndOffsetBuilder_ == null && this.speechEndOffset_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public Duration getSpeechEndOffset() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.speechEndOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration = this.speechEndOffset_;
                return duration == null ? Duration.getDefaultInstance() : duration;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSpeechEndOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.speechEndOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(duration);
            } else {
                if (duration == null) {
                    throw new NullPointerException();
                }
                this.speechEndOffset_ = duration;
                onChanged();
            }
            return this;
        }

        public Builder setSpeechEndOffset(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.speechEndOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.speechEndOffset_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSpeechEndOffset(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.speechEndOffsetBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.speechEndOffset_;
                if (duration2 != null) {
                    this.speechEndOffset_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.speechEndOffset_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearSpeechEndOffset() {
            if (this.speechEndOffsetBuilder_ == null) {
                this.speechEndOffset_ = null;
                onChanged();
            } else {
                this.speechEndOffset_ = null;
                this.speechEndOffsetBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getSpeechEndOffsetBuilder() {
            onChanged();
            return getSpeechEndOffsetFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.StreamingRecognitionResultOrBuilder
        public DurationOrBuilder getSpeechEndOffsetOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.speechEndOffsetBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.speechEndOffset_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getSpeechEndOffsetFieldBuilder() {
            if (this.speechEndOffsetBuilder_ == null) {
                this.speechEndOffsetBuilder_ = new SingleFieldBuilderV3<>(getSpeechEndOffset(), getParentForChildren(), isClean());
                this.speechEndOffset_ = null;
            }
            return this.speechEndOffsetBuilder_;
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

    public static StreamingRecognitionResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StreamingRecognitionResult> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<StreamingRecognitionResult> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public StreamingRecognitionResult getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
