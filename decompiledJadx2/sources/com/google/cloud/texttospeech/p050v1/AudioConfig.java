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
public final class AudioConfig extends GeneratedMessageV3 implements AudioConfigOrBuilder {
    public static final int AUDIO_ENCODING_FIELD_NUMBER = 1;
    public static final int EFFECTS_PROFILE_ID_FIELD_NUMBER = 6;
    public static final int PITCH_FIELD_NUMBER = 3;
    public static final int SAMPLE_RATE_HERTZ_FIELD_NUMBER = 5;
    public static final int SPEAKING_RATE_FIELD_NUMBER = 2;
    public static final int VOLUME_GAIN_DB_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private int audioEncoding_;
    private LazyStringList effectsProfileId_;
    private byte memoizedIsInitialized;
    private double pitch_;
    private int sampleRateHertz_;
    private double speakingRate_;
    private double volumeGainDb_;
    private static final AudioConfig DEFAULT_INSTANCE = new AudioConfig();
    private static final Parser<AudioConfig> PARSER = new AbstractParser<AudioConfig>() { // from class: com.google.cloud.texttospeech.v1.AudioConfig.1
        @Override // com.google.protobuf.Parser
        public AudioConfig parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AudioConfig(codedInputStream, extensionRegistryLite);
        }
    };

    private AudioConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AudioConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.audioEncoding_ = 0;
        this.effectsProfileId_ = LazyStringArrayList.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new AudioConfig();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AudioConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 8) {
                            this.audioEncoding_ = codedInputStream.readEnum();
                        } else if (readTag == 17) {
                            this.speakingRate_ = codedInputStream.readDouble();
                        } else if (readTag == 25) {
                            this.pitch_ = codedInputStream.readDouble();
                        } else if (readTag == 33) {
                            this.volumeGainDb_ = codedInputStream.readDouble();
                        } else if (readTag == 40) {
                            this.sampleRateHertz_ = codedInputStream.readInt32();
                        } else if (readTag == 50) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.effectsProfileId_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.effectsProfileId_.add(readStringRequireUtf8);
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
                    this.effectsProfileId_ = this.effectsProfileId_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TextToSpeechProto.f1879xfb31ae06;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1880x5aad6e84.ensureFieldAccessorsInitialized(AudioConfig.class, Builder.class);
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public int getAudioEncodingValue() {
        return this.audioEncoding_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public AudioEncoding getAudioEncoding() {
        AudioEncoding valueOf = AudioEncoding.valueOf(this.audioEncoding_);
        return valueOf == null ? AudioEncoding.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public double getSpeakingRate() {
        return this.speakingRate_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public double getPitch() {
        return this.pitch_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public double getVolumeGainDb() {
        return this.volumeGainDb_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public int getSampleRateHertz() {
        return this.sampleRateHertz_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public ProtocolStringList getEffectsProfileIdList() {
        return this.effectsProfileId_;
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public int getEffectsProfileIdCount() {
        return this.effectsProfileId_.size();
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public String getEffectsProfileId(int i) {
        return (String) this.effectsProfileId_.get(i);
    }

    @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
    public ByteString getEffectsProfileIdBytes(int i) {
        return this.effectsProfileId_.getByteString(i);
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
        double d = this.speakingRate_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(2, d);
        }
        double d2 = this.pitch_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(3, d2);
        }
        double d3 = this.volumeGainDb_;
        if (d3 != 0.0d) {
            codedOutputStream.writeDouble(4, d3);
        }
        int i = this.sampleRateHertz_;
        if (i != 0) {
            codedOutputStream.writeInt32(5, i);
        }
        for (int i2 = 0; i2 < this.effectsProfileId_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.effectsProfileId_.getRaw(i2));
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
        double d = this.speakingRate_;
        if (d != 0.0d) {
            computeEnumSize += CodedOutputStream.computeDoubleSize(2, d);
        }
        double d2 = this.pitch_;
        if (d2 != 0.0d) {
            computeEnumSize += CodedOutputStream.computeDoubleSize(3, d2);
        }
        double d3 = this.volumeGainDb_;
        if (d3 != 0.0d) {
            computeEnumSize += CodedOutputStream.computeDoubleSize(4, d3);
        }
        int i2 = this.sampleRateHertz_;
        if (i2 != 0) {
            computeEnumSize += CodedOutputStream.computeInt32Size(5, i2);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.effectsProfileId_.size(); i4++) {
            i3 += computeStringSizeNoTag(this.effectsProfileId_.getRaw(i4));
        }
        int size = computeEnumSize + i3 + (getEffectsProfileIdList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioConfig)) {
            return super.equals(obj);
        }
        AudioConfig audioConfig = (AudioConfig) obj;
        return this.audioEncoding_ == audioConfig.audioEncoding_ && Double.doubleToLongBits(getSpeakingRate()) == Double.doubleToLongBits(audioConfig.getSpeakingRate()) && Double.doubleToLongBits(getPitch()) == Double.doubleToLongBits(audioConfig.getPitch()) && Double.doubleToLongBits(getVolumeGainDb()) == Double.doubleToLongBits(audioConfig.getVolumeGainDb()) && getSampleRateHertz() == audioConfig.getSampleRateHertz() && getEffectsProfileIdList().equals(audioConfig.getEffectsProfileIdList()) && this.unknownFields.equals(audioConfig.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.audioEncoding_) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getSpeakingRate()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getPitch()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getVolumeGainDb()))) * 37) + 5) * 53) + getSampleRateHertz();
        if (getEffectsProfileIdCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getEffectsProfileIdList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AudioConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static AudioConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AudioConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static AudioConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AudioConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static AudioConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AudioConfig parseFrom(InputStream inputStream) throws IOException {
        return (AudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AudioConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AudioConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AudioConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AudioConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AudioConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AudioConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AudioConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AudioConfig audioConfig) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(audioConfig);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AudioConfigOrBuilder {
        private int audioEncoding_;
        private int bitField0_;
        private LazyStringList effectsProfileId_;
        private double pitch_;
        private int sampleRateHertz_;
        private double speakingRate_;
        private double volumeGainDb_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1879xfb31ae06;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1880x5aad6e84.ensureFieldAccessorsInitialized(AudioConfig.class, Builder.class);
        }

        private Builder() {
            this.audioEncoding_ = 0;
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.audioEncoding_ = 0;
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = AudioConfig.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.audioEncoding_ = 0;
            this.speakingRate_ = 0.0d;
            this.pitch_ = 0.0d;
            this.volumeGainDb_ = 0.0d;
            this.sampleRateHertz_ = 0;
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1879xfb31ae06;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public AudioConfig getDefaultInstanceForType() {
            return AudioConfig.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AudioConfig build() {
            AudioConfig buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AudioConfig buildPartial() {
            AudioConfig audioConfig = new AudioConfig(this);
            int i = this.bitField0_;
            audioConfig.audioEncoding_ = this.audioEncoding_;
            audioConfig.speakingRate_ = this.speakingRate_;
            audioConfig.pitch_ = this.pitch_;
            audioConfig.volumeGainDb_ = this.volumeGainDb_;
            audioConfig.sampleRateHertz_ = this.sampleRateHertz_;
            if ((this.bitField0_ & 1) != 0) {
                this.effectsProfileId_ = this.effectsProfileId_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            audioConfig.effectsProfileId_ = this.effectsProfileId_;
            onBuilt();
            return audioConfig;
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
            if (message instanceof AudioConfig) {
                return mergeFrom((AudioConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AudioConfig audioConfig) {
            if (audioConfig == AudioConfig.getDefaultInstance()) {
                return this;
            }
            if (audioConfig.audioEncoding_ != 0) {
                setAudioEncodingValue(audioConfig.getAudioEncodingValue());
            }
            if (audioConfig.getSpeakingRate() != 0.0d) {
                setSpeakingRate(audioConfig.getSpeakingRate());
            }
            if (audioConfig.getPitch() != 0.0d) {
                setPitch(audioConfig.getPitch());
            }
            if (audioConfig.getVolumeGainDb() != 0.0d) {
                setVolumeGainDb(audioConfig.getVolumeGainDb());
            }
            if (audioConfig.getSampleRateHertz() != 0) {
                setSampleRateHertz(audioConfig.getSampleRateHertz());
            }
            if (!audioConfig.effectsProfileId_.isEmpty()) {
                if (this.effectsProfileId_.isEmpty()) {
                    this.effectsProfileId_ = audioConfig.effectsProfileId_;
                    this.bitField0_ &= -2;
                } else {
                    ensureEffectsProfileIdIsMutable();
                    this.effectsProfileId_.addAll(audioConfig.effectsProfileId_);
                }
                onChanged();
            }
            mergeUnknownFields(audioConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AudioConfig audioConfig = null;
            try {
                try {
                    AudioConfig audioConfig2 = (AudioConfig) AudioConfig.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (audioConfig2 != null) {
                        mergeFrom(audioConfig2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    AudioConfig audioConfig3 = (AudioConfig) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        audioConfig = audioConfig3;
                        if (audioConfig != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (audioConfig != null) {
                    mergeFrom(audioConfig);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public int getAudioEncodingValue() {
            return this.audioEncoding_;
        }

        public Builder setAudioEncodingValue(int i) {
            this.audioEncoding_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
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

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public double getSpeakingRate() {
            return this.speakingRate_;
        }

        public Builder setSpeakingRate(double d) {
            this.speakingRate_ = d;
            onChanged();
            return this;
        }

        public Builder clearSpeakingRate() {
            this.speakingRate_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public double getPitch() {
            return this.pitch_;
        }

        public Builder setPitch(double d) {
            this.pitch_ = d;
            onChanged();
            return this;
        }

        public Builder clearPitch() {
            this.pitch_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public double getVolumeGainDb() {
            return this.volumeGainDb_;
        }

        public Builder setVolumeGainDb(double d) {
            this.volumeGainDb_ = d;
            onChanged();
            return this;
        }

        public Builder clearVolumeGainDb() {
            this.volumeGainDb_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
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

        private void ensureEffectsProfileIdIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.effectsProfileId_ = new LazyStringArrayList(this.effectsProfileId_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public ProtocolStringList getEffectsProfileIdList() {
            return this.effectsProfileId_.getUnmodifiableView();
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public int getEffectsProfileIdCount() {
            return this.effectsProfileId_.size();
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public String getEffectsProfileId(int i) {
            return (String) this.effectsProfileId_.get(i);
        }

        @Override // com.google.cloud.texttospeech.p050v1.AudioConfigOrBuilder
        public ByteString getEffectsProfileIdBytes(int i) {
            return this.effectsProfileId_.getByteString(i);
        }

        public Builder setEffectsProfileId(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureEffectsProfileIdIsMutable();
            this.effectsProfileId_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addEffectsProfileId(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureEffectsProfileIdIsMutable();
            this.effectsProfileId_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllEffectsProfileId(Iterable<String> iterable) {
            ensureEffectsProfileIdIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.effectsProfileId_);
            onChanged();
            return this;
        }

        public Builder clearEffectsProfileId() {
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addEffectsProfileIdBytes(ByteString byteString) {
            if (byteString != null) {
                AudioConfig.checkByteStringIsUtf8(byteString);
                ensureEffectsProfileIdIsMutable();
                this.effectsProfileId_.add(byteString);
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

    public static AudioConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AudioConfig> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<AudioConfig> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public AudioConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
