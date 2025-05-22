package com.google.cloud.texttospeech.v1beta1;

import com.google.cloud.texttospeech.v1beta1.AudioConfig;
import com.google.cloud.texttospeech.v1beta1.SynthesisInput;
import com.google.cloud.texttospeech.v1beta1.VoiceSelectionParams;
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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class SynthesizeSpeechRequest extends GeneratedMessageV3 implements SynthesizeSpeechRequestOrBuilder {
    public static final int AUDIO_CONFIG_FIELD_NUMBER = 3;
    public static final int ENABLE_TIME_POINTING_FIELD_NUMBER = 4;
    public static final int INPUT_FIELD_NUMBER = 1;
    public static final int VOICE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private AudioConfig audioConfig_;
    private int enableTimePointingMemoizedSerializedSize;
    private List<Integer> enableTimePointing_;
    private SynthesisInput input_;
    private byte memoizedIsInitialized;
    private VoiceSelectionParams voice_;
    private static final Internal.ListAdapter.Converter<Integer, TimepointType> enableTimePointing_converter_ = new Internal.ListAdapter.Converter<Integer, TimepointType>() { // from class: com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest.1
        @Override // com.google.protobuf.Internal.ListAdapter.Converter
        public TimepointType convert(Integer num) {
            TimepointType valueOf = TimepointType.valueOf(num.intValue());
            return valueOf == null ? TimepointType.UNRECOGNIZED : valueOf;
        }
    };
    private static final SynthesizeSpeechRequest DEFAULT_INSTANCE = new SynthesizeSpeechRequest();
    private static final Parser<SynthesizeSpeechRequest> PARSER = new AbstractParser<SynthesizeSpeechRequest>() { // from class: com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest.2
        @Override // com.google.protobuf.Parser
        public SynthesizeSpeechRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SynthesizeSpeechRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private SynthesizeSpeechRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SynthesizeSpeechRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.enableTimePointing_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SynthesizeSpeechRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SynthesizeSpeechRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            SynthesisInput.Builder builder = this.input_ != null ? this.input_.toBuilder() : null;
                            this.input_ = (SynthesisInput) codedInputStream.readMessage(SynthesisInput.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.input_);
                                this.input_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            VoiceSelectionParams.Builder builder2 = this.voice_ != null ? this.voice_.toBuilder() : null;
                            this.voice_ = (VoiceSelectionParams) codedInputStream.readMessage(VoiceSelectionParams.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.voice_);
                                this.voice_ = builder2.buildPartial();
                            }
                        } else if (readTag == 26) {
                            AudioConfig.Builder builder3 = this.audioConfig_ != null ? this.audioConfig_.toBuilder() : null;
                            this.audioConfig_ = (AudioConfig) codedInputStream.readMessage(AudioConfig.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.audioConfig_);
                                this.audioConfig_ = builder3.buildPartial();
                            }
                        } else if (readTag == 32) {
                            int readEnum = codedInputStream.readEnum();
                            boolean z3 = (z2 ? 1 : 0) & true;
                            z2 = z2;
                            if (!z3) {
                                this.enableTimePointing_ = new ArrayList();
                                z2 = (z2 ? 1 : 0) | true;
                            }
                            this.enableTimePointing_.add(Integer.valueOf(readEnum));
                        } else if (readTag == 34) {
                            int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            z2 = z2;
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                int readEnum2 = codedInputStream.readEnum();
                                if (!((z2 ? 1 : 0) & true)) {
                                    this.enableTimePointing_ = new ArrayList();
                                    z2 = (z2 ? 1 : 0) | true;
                                }
                                this.enableTimePointing_.add(Integer.valueOf(readEnum2));
                                z2 = z2;
                            }
                            codedInputStream.popLimit(pushLimit);
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
                if ((z2 ? 1 : 0) & true) {
                    this.enableTimePointing_ = Collections.unmodifiableList(this.enableTimePointing_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TextToSpeechProto.f1903x65d30b78;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1904x771b39f6.ensureFieldAccessorsInitialized(SynthesizeSpeechRequest.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum TimepointType implements ProtocolMessageEnum {
        TIMEPOINT_TYPE_UNSPECIFIED(0),
        SSML_MARK(1),
        UNRECOGNIZED(-1);

        public static final int SSML_MARK_VALUE = 1;
        public static final int TIMEPOINT_TYPE_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<TimepointType> internalValueMap = new Internal.EnumLiteMap<TimepointType>() { // from class: com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequest.TimepointType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TimepointType findValueByNumber(int i) {
                return TimepointType.forNumber(i);
            }
        };
        private static final TimepointType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static TimepointType valueOf(int i) {
            return forNumber(i);
        }

        public static TimepointType forNumber(int i) {
            if (i == 0) {
                return TIMEPOINT_TYPE_UNSPECIFIED;
            }
            if (i != 1) {
                return null;
            }
            return SSML_MARK;
        }

        public static Internal.EnumLiteMap<TimepointType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return SynthesizeSpeechRequest.getDescriptor().getEnumTypes().get(0);
        }

        public static TimepointType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        TimepointType(int i) {
            this.value = i;
        }
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public boolean hasInput() {
        return this.input_ != null;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public SynthesisInput getInput() {
        SynthesisInput synthesisInput = this.input_;
        return synthesisInput == null ? SynthesisInput.getDefaultInstance() : synthesisInput;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public SynthesisInputOrBuilder getInputOrBuilder() {
        return getInput();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public boolean hasVoice() {
        return this.voice_ != null;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public VoiceSelectionParams getVoice() {
        VoiceSelectionParams voiceSelectionParams = this.voice_;
        return voiceSelectionParams == null ? VoiceSelectionParams.getDefaultInstance() : voiceSelectionParams;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public VoiceSelectionParamsOrBuilder getVoiceOrBuilder() {
        return getVoice();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public boolean hasAudioConfig() {
        return this.audioConfig_ != null;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public AudioConfig getAudioConfig() {
        AudioConfig audioConfig = this.audioConfig_;
        return audioConfig == null ? AudioConfig.getDefaultInstance() : audioConfig;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public AudioConfigOrBuilder getAudioConfigOrBuilder() {
        return getAudioConfig();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public List<TimepointType> getEnableTimePointingList() {
        return new Internal.ListAdapter(this.enableTimePointing_, enableTimePointing_converter_);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public int getEnableTimePointingCount() {
        return this.enableTimePointing_.size();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public TimepointType getEnableTimePointing(int i) {
        return enableTimePointing_converter_.convert(this.enableTimePointing_.get(i));
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public List<Integer> getEnableTimePointingValueList() {
        return this.enableTimePointing_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
    public int getEnableTimePointingValue(int i) {
        return this.enableTimePointing_.get(i).intValue();
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
        getSerializedSize();
        if (this.input_ != null) {
            codedOutputStream.writeMessage(1, getInput());
        }
        if (this.voice_ != null) {
            codedOutputStream.writeMessage(2, getVoice());
        }
        if (this.audioConfig_ != null) {
            codedOutputStream.writeMessage(3, getAudioConfig());
        }
        if (getEnableTimePointingList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(34);
            codedOutputStream.writeUInt32NoTag(this.enableTimePointingMemoizedSerializedSize);
        }
        for (int i = 0; i < this.enableTimePointing_.size(); i++) {
            codedOutputStream.writeEnumNoTag(this.enableTimePointing_.get(i).intValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.input_ != null ? CodedOutputStream.computeMessageSize(1, getInput()) + 0 : 0;
        if (this.voice_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getVoice());
        }
        if (this.audioConfig_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, getAudioConfig());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.enableTimePointing_.size(); i3++) {
            i2 += CodedOutputStream.computeEnumSizeNoTag(this.enableTimePointing_.get(i3).intValue());
        }
        int i4 = computeMessageSize + i2;
        if (!getEnableTimePointingList().isEmpty()) {
            i4 = i4 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i2);
        }
        this.enableTimePointingMemoizedSerializedSize = i2;
        int serializedSize = i4 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SynthesizeSpeechRequest)) {
            return super.equals(obj);
        }
        SynthesizeSpeechRequest synthesizeSpeechRequest = (SynthesizeSpeechRequest) obj;
        if (hasInput() != synthesizeSpeechRequest.hasInput()) {
            return false;
        }
        if ((hasInput() && !getInput().equals(synthesizeSpeechRequest.getInput())) || hasVoice() != synthesizeSpeechRequest.hasVoice()) {
            return false;
        }
        if ((!hasVoice() || getVoice().equals(synthesizeSpeechRequest.getVoice())) && hasAudioConfig() == synthesizeSpeechRequest.hasAudioConfig()) {
            return (!hasAudioConfig() || getAudioConfig().equals(synthesizeSpeechRequest.getAudioConfig())) && this.enableTimePointing_.equals(synthesizeSpeechRequest.enableTimePointing_) && this.unknownFields.equals(synthesizeSpeechRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasInput()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getInput().hashCode();
        }
        if (hasVoice()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getVoice().hashCode();
        }
        if (hasAudioConfig()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getAudioConfig().hashCode();
        }
        if (getEnableTimePointingCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + this.enableTimePointing_.hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SynthesizeSpeechRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SynthesizeSpeechRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SynthesizeSpeechRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SynthesizeSpeechRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SynthesizeSpeechRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SynthesizeSpeechRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SynthesizeSpeechRequest parseFrom(InputStream inputStream) throws IOException {
        return (SynthesizeSpeechRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SynthesizeSpeechRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesizeSpeechRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SynthesizeSpeechRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SynthesizeSpeechRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesizeSpeechRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SynthesizeSpeechRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SynthesizeSpeechRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SynthesizeSpeechRequest synthesizeSpeechRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(synthesizeSpeechRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SynthesizeSpeechRequestOrBuilder {
        private SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> audioConfigBuilder_;
        private AudioConfig audioConfig_;
        private int bitField0_;
        private List<Integer> enableTimePointing_;
        private SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> inputBuilder_;
        private SynthesisInput input_;
        private SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> voiceBuilder_;
        private VoiceSelectionParams voice_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1903x65d30b78;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1904x771b39f6.ensureFieldAccessorsInitialized(SynthesizeSpeechRequest.class, Builder.class);
        }

        private Builder() {
            this.enableTimePointing_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.enableTimePointing_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SynthesizeSpeechRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.inputBuilder_ == null) {
                this.input_ = null;
            } else {
                this.input_ = null;
                this.inputBuilder_ = null;
            }
            if (this.voiceBuilder_ == null) {
                this.voice_ = null;
            } else {
                this.voice_ = null;
                this.voiceBuilder_ = null;
            }
            if (this.audioConfigBuilder_ == null) {
                this.audioConfig_ = null;
            } else {
                this.audioConfig_ = null;
                this.audioConfigBuilder_ = null;
            }
            this.enableTimePointing_ = Collections.emptyList();
            this.bitField0_ &= -2;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1903x65d30b78;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SynthesizeSpeechRequest getDefaultInstanceForType() {
            return SynthesizeSpeechRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesizeSpeechRequest build() {
            SynthesizeSpeechRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesizeSpeechRequest buildPartial() {
            SynthesizeSpeechRequest synthesizeSpeechRequest = new SynthesizeSpeechRequest(this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> singleFieldBuilderV3 = this.inputBuilder_;
            if (singleFieldBuilderV3 == null) {
                synthesizeSpeechRequest.input_ = this.input_;
            } else {
                synthesizeSpeechRequest.input_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV32 = this.voiceBuilder_;
            if (singleFieldBuilderV32 == null) {
                synthesizeSpeechRequest.voice_ = this.voice_;
            } else {
                synthesizeSpeechRequest.voice_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV33 = this.audioConfigBuilder_;
            if (singleFieldBuilderV33 == null) {
                synthesizeSpeechRequest.audioConfig_ = this.audioConfig_;
            } else {
                synthesizeSpeechRequest.audioConfig_ = singleFieldBuilderV33.build();
            }
            if ((this.bitField0_ & 1) != 0) {
                this.enableTimePointing_ = Collections.unmodifiableList(this.enableTimePointing_);
                this.bitField0_ &= -2;
            }
            synthesizeSpeechRequest.enableTimePointing_ = this.enableTimePointing_;
            onBuilt();
            return synthesizeSpeechRequest;
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
            if (message instanceof SynthesizeSpeechRequest) {
                return mergeFrom((SynthesizeSpeechRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SynthesizeSpeechRequest synthesizeSpeechRequest) {
            if (synthesizeSpeechRequest == SynthesizeSpeechRequest.getDefaultInstance()) {
                return this;
            }
            if (synthesizeSpeechRequest.hasInput()) {
                mergeInput(synthesizeSpeechRequest.getInput());
            }
            if (synthesizeSpeechRequest.hasVoice()) {
                mergeVoice(synthesizeSpeechRequest.getVoice());
            }
            if (synthesizeSpeechRequest.hasAudioConfig()) {
                mergeAudioConfig(synthesizeSpeechRequest.getAudioConfig());
            }
            if (!synthesizeSpeechRequest.enableTimePointing_.isEmpty()) {
                if (this.enableTimePointing_.isEmpty()) {
                    this.enableTimePointing_ = synthesizeSpeechRequest.enableTimePointing_;
                    this.bitField0_ &= -2;
                } else {
                    ensureEnableTimePointingIsMutable();
                    this.enableTimePointing_.addAll(synthesizeSpeechRequest.enableTimePointing_);
                }
                onChanged();
            }
            mergeUnknownFields(synthesizeSpeechRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SynthesizeSpeechRequest synthesizeSpeechRequest = null;
            try {
                try {
                    SynthesizeSpeechRequest synthesizeSpeechRequest2 = (SynthesizeSpeechRequest) SynthesizeSpeechRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (synthesizeSpeechRequest2 != null) {
                        mergeFrom(synthesizeSpeechRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SynthesizeSpeechRequest synthesizeSpeechRequest3 = (SynthesizeSpeechRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        synthesizeSpeechRequest = synthesizeSpeechRequest3;
                        if (synthesizeSpeechRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (synthesizeSpeechRequest != null) {
                    mergeFrom(synthesizeSpeechRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public boolean hasInput() {
            return (this.inputBuilder_ == null && this.input_ == null) ? false : true;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public SynthesisInput getInput() {
            SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> singleFieldBuilderV3 = this.inputBuilder_;
            if (singleFieldBuilderV3 == null) {
                SynthesisInput synthesisInput = this.input_;
                return synthesisInput == null ? SynthesisInput.getDefaultInstance() : synthesisInput;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setInput(SynthesisInput synthesisInput) {
            SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> singleFieldBuilderV3 = this.inputBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(synthesisInput);
            } else {
                if (synthesisInput == null) {
                    throw new NullPointerException();
                }
                this.input_ = synthesisInput;
                onChanged();
            }
            return this;
        }

        public Builder setInput(SynthesisInput.Builder builder) {
            SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> singleFieldBuilderV3 = this.inputBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.input_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeInput(SynthesisInput synthesisInput) {
            SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> singleFieldBuilderV3 = this.inputBuilder_;
            if (singleFieldBuilderV3 == null) {
                SynthesisInput synthesisInput2 = this.input_;
                if (synthesisInput2 != null) {
                    this.input_ = SynthesisInput.newBuilder(synthesisInput2).mergeFrom(synthesisInput).buildPartial();
                } else {
                    this.input_ = synthesisInput;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(synthesisInput);
            }
            return this;
        }

        public Builder clearInput() {
            if (this.inputBuilder_ == null) {
                this.input_ = null;
                onChanged();
            } else {
                this.input_ = null;
                this.inputBuilder_ = null;
            }
            return this;
        }

        public SynthesisInput.Builder getInputBuilder() {
            onChanged();
            return getInputFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public SynthesisInputOrBuilder getInputOrBuilder() {
            SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> singleFieldBuilderV3 = this.inputBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SynthesisInput synthesisInput = this.input_;
            return synthesisInput == null ? SynthesisInput.getDefaultInstance() : synthesisInput;
        }

        private SingleFieldBuilderV3<SynthesisInput, SynthesisInput.Builder, SynthesisInputOrBuilder> getInputFieldBuilder() {
            if (this.inputBuilder_ == null) {
                this.inputBuilder_ = new SingleFieldBuilderV3<>(getInput(), getParentForChildren(), isClean());
                this.input_ = null;
            }
            return this.inputBuilder_;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public boolean hasVoice() {
            return (this.voiceBuilder_ == null && this.voice_ == null) ? false : true;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public VoiceSelectionParams getVoice() {
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV3 = this.voiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                VoiceSelectionParams voiceSelectionParams = this.voice_;
                return voiceSelectionParams == null ? VoiceSelectionParams.getDefaultInstance() : voiceSelectionParams;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setVoice(VoiceSelectionParams voiceSelectionParams) {
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV3 = this.voiceBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(voiceSelectionParams);
            } else {
                if (voiceSelectionParams == null) {
                    throw new NullPointerException();
                }
                this.voice_ = voiceSelectionParams;
                onChanged();
            }
            return this;
        }

        public Builder setVoice(VoiceSelectionParams.Builder builder) {
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV3 = this.voiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.voice_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeVoice(VoiceSelectionParams voiceSelectionParams) {
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV3 = this.voiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                VoiceSelectionParams voiceSelectionParams2 = this.voice_;
                if (voiceSelectionParams2 != null) {
                    this.voice_ = VoiceSelectionParams.newBuilder(voiceSelectionParams2).mergeFrom(voiceSelectionParams).buildPartial();
                } else {
                    this.voice_ = voiceSelectionParams;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(voiceSelectionParams);
            }
            return this;
        }

        public Builder clearVoice() {
            if (this.voiceBuilder_ == null) {
                this.voice_ = null;
                onChanged();
            } else {
                this.voice_ = null;
                this.voiceBuilder_ = null;
            }
            return this;
        }

        public VoiceSelectionParams.Builder getVoiceBuilder() {
            onChanged();
            return getVoiceFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public VoiceSelectionParamsOrBuilder getVoiceOrBuilder() {
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV3 = this.voiceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            VoiceSelectionParams voiceSelectionParams = this.voice_;
            return voiceSelectionParams == null ? VoiceSelectionParams.getDefaultInstance() : voiceSelectionParams;
        }

        private SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> getVoiceFieldBuilder() {
            if (this.voiceBuilder_ == null) {
                this.voiceBuilder_ = new SingleFieldBuilderV3<>(getVoice(), getParentForChildren(), isClean());
                this.voice_ = null;
            }
            return this.voiceBuilder_;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public boolean hasAudioConfig() {
            return (this.audioConfigBuilder_ == null && this.audioConfig_ == null) ? false : true;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public AudioConfig getAudioConfig() {
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                AudioConfig audioConfig = this.audioConfig_;
                return audioConfig == null ? AudioConfig.getDefaultInstance() : audioConfig;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setAudioConfig(AudioConfig audioConfig) {
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(audioConfig);
            } else {
                if (audioConfig == null) {
                    throw new NullPointerException();
                }
                this.audioConfig_ = audioConfig;
                onChanged();
            }
            return this;
        }

        public Builder setAudioConfig(AudioConfig.Builder builder) {
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.audioConfig_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAudioConfig(AudioConfig audioConfig) {
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                AudioConfig audioConfig2 = this.audioConfig_;
                if (audioConfig2 != null) {
                    this.audioConfig_ = AudioConfig.newBuilder(audioConfig2).mergeFrom(audioConfig).buildPartial();
                } else {
                    this.audioConfig_ = audioConfig;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(audioConfig);
            }
            return this;
        }

        public Builder clearAudioConfig() {
            if (this.audioConfigBuilder_ == null) {
                this.audioConfig_ = null;
                onChanged();
            } else {
                this.audioConfig_ = null;
                this.audioConfigBuilder_ = null;
            }
            return this;
        }

        public AudioConfig.Builder getAudioConfigBuilder() {
            onChanged();
            return getAudioConfigFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public AudioConfigOrBuilder getAudioConfigOrBuilder() {
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            AudioConfig audioConfig = this.audioConfig_;
            return audioConfig == null ? AudioConfig.getDefaultInstance() : audioConfig;
        }

        private SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> getAudioConfigFieldBuilder() {
            if (this.audioConfigBuilder_ == null) {
                this.audioConfigBuilder_ = new SingleFieldBuilderV3<>(getAudioConfig(), getParentForChildren(), isClean());
                this.audioConfig_ = null;
            }
            return this.audioConfigBuilder_;
        }

        private void ensureEnableTimePointingIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.enableTimePointing_ = new ArrayList(this.enableTimePointing_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public List<TimepointType> getEnableTimePointingList() {
            return new Internal.ListAdapter(this.enableTimePointing_, SynthesizeSpeechRequest.enableTimePointing_converter_);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public int getEnableTimePointingCount() {
            return this.enableTimePointing_.size();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public TimepointType getEnableTimePointing(int i) {
            return (TimepointType) SynthesizeSpeechRequest.enableTimePointing_converter_.convert(this.enableTimePointing_.get(i));
        }

        public Builder setEnableTimePointing(int i, TimepointType timepointType) {
            if (timepointType == null) {
                throw new NullPointerException();
            }
            ensureEnableTimePointingIsMutable();
            this.enableTimePointing_.set(i, Integer.valueOf(timepointType.getNumber()));
            onChanged();
            return this;
        }

        public Builder addEnableTimePointing(TimepointType timepointType) {
            if (timepointType == null) {
                throw new NullPointerException();
            }
            ensureEnableTimePointingIsMutable();
            this.enableTimePointing_.add(Integer.valueOf(timepointType.getNumber()));
            onChanged();
            return this;
        }

        public Builder addAllEnableTimePointing(Iterable<? extends TimepointType> iterable) {
            ensureEnableTimePointingIsMutable();
            Iterator<? extends TimepointType> it = iterable.iterator();
            while (it.hasNext()) {
                this.enableTimePointing_.add(Integer.valueOf(it.next().getNumber()));
            }
            onChanged();
            return this;
        }

        public Builder clearEnableTimePointing() {
            this.enableTimePointing_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public List<Integer> getEnableTimePointingValueList() {
            return Collections.unmodifiableList(this.enableTimePointing_);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechRequestOrBuilder
        public int getEnableTimePointingValue(int i) {
            return this.enableTimePointing_.get(i).intValue();
        }

        public Builder setEnableTimePointingValue(int i, int i2) {
            ensureEnableTimePointingIsMutable();
            this.enableTimePointing_.set(i, Integer.valueOf(i2));
            onChanged();
            return this;
        }

        public Builder addEnableTimePointingValue(int i) {
            ensureEnableTimePointingIsMutable();
            this.enableTimePointing_.add(Integer.valueOf(i));
            onChanged();
            return this;
        }

        public Builder addAllEnableTimePointingValue(Iterable<Integer> iterable) {
            ensureEnableTimePointingIsMutable();
            Iterator<Integer> it = iterable.iterator();
            while (it.hasNext()) {
                this.enableTimePointing_.add(Integer.valueOf(it.next().intValue()));
            }
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

    public static SynthesizeSpeechRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SynthesizeSpeechRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SynthesizeSpeechRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SynthesizeSpeechRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
