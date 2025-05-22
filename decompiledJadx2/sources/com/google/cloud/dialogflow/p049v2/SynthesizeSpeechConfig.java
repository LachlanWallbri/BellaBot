package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.VoiceSelectionParams;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SynthesizeSpeechConfig extends GeneratedMessageV3 implements SynthesizeSpeechConfigOrBuilder {
    public static final int EFFECTS_PROFILE_ID_FIELD_NUMBER = 5;
    public static final int PITCH_FIELD_NUMBER = 2;
    public static final int SPEAKING_RATE_FIELD_NUMBER = 1;
    public static final int VOICE_FIELD_NUMBER = 4;
    public static final int VOLUME_GAIN_DB_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private LazyStringList effectsProfileId_;
    private byte memoizedIsInitialized;
    private double pitch_;
    private double speakingRate_;
    private VoiceSelectionParams voice_;
    private double volumeGainDb_;
    private static final SynthesizeSpeechConfig DEFAULT_INSTANCE = new SynthesizeSpeechConfig();
    private static final Parser<SynthesizeSpeechConfig> PARSER = new AbstractParser<SynthesizeSpeechConfig>() { // from class: com.google.cloud.dialogflow.v2.SynthesizeSpeechConfig.1
        @Override // com.google.protobuf.Parser
        public SynthesizeSpeechConfig parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SynthesizeSpeechConfig(codedInputStream, extensionRegistryLite);
        }
    };

    private SynthesizeSpeechConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SynthesizeSpeechConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.effectsProfileId_ = LazyStringArrayList.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SynthesizeSpeechConfig();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SynthesizeSpeechConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 9) {
                                this.speakingRate_ = codedInputStream.readDouble();
                            } else if (readTag == 17) {
                                this.pitch_ = codedInputStream.readDouble();
                            } else if (readTag == 25) {
                                this.volumeGainDb_ = codedInputStream.readDouble();
                            } else if (readTag == 34) {
                                VoiceSelectionParams.Builder builder = this.voice_ != null ? this.voice_.toBuilder() : null;
                                this.voice_ = (VoiceSelectionParams) codedInputStream.readMessage(VoiceSelectionParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.voice_);
                                    this.voice_ = builder.buildPartial();
                                }
                            } else if (readTag == 42) {
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
                    }
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
        return AudioConfigProto.f1392x8a530365;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AudioConfigProto.f1393xf70ef4e3.ensureFieldAccessorsInitialized(SynthesizeSpeechConfig.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public double getSpeakingRate() {
        return this.speakingRate_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public double getPitch() {
        return this.pitch_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public double getVolumeGainDb() {
        return this.volumeGainDb_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public ProtocolStringList getEffectsProfileIdList() {
        return this.effectsProfileId_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public int getEffectsProfileIdCount() {
        return this.effectsProfileId_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public String getEffectsProfileId(int i) {
        return (String) this.effectsProfileId_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public ByteString getEffectsProfileIdBytes(int i) {
        return this.effectsProfileId_.getByteString(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public boolean hasVoice() {
        return this.voice_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public VoiceSelectionParams getVoice() {
        VoiceSelectionParams voiceSelectionParams = this.voice_;
        return voiceSelectionParams == null ? VoiceSelectionParams.getDefaultInstance() : voiceSelectionParams;
    }

    @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
    public VoiceSelectionParamsOrBuilder getVoiceOrBuilder() {
        return getVoice();
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
        double d = this.speakingRate_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
        }
        double d2 = this.pitch_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(2, d2);
        }
        double d3 = this.volumeGainDb_;
        if (d3 != 0.0d) {
            codedOutputStream.writeDouble(3, d3);
        }
        if (this.voice_ != null) {
            codedOutputStream.writeMessage(4, getVoice());
        }
        for (int i = 0; i < this.effectsProfileId_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.effectsProfileId_.getRaw(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        double d = this.speakingRate_;
        int computeDoubleSize = d != 0.0d ? CodedOutputStream.computeDoubleSize(1, d) + 0 : 0;
        double d2 = this.pitch_;
        if (d2 != 0.0d) {
            computeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
        }
        double d3 = this.volumeGainDb_;
        if (d3 != 0.0d) {
            computeDoubleSize += CodedOutputStream.computeDoubleSize(3, d3);
        }
        if (this.voice_ != null) {
            computeDoubleSize += CodedOutputStream.computeMessageSize(4, getVoice());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.effectsProfileId_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.effectsProfileId_.getRaw(i3));
        }
        int size = computeDoubleSize + i2 + (getEffectsProfileIdList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SynthesizeSpeechConfig)) {
            return super.equals(obj);
        }
        SynthesizeSpeechConfig synthesizeSpeechConfig = (SynthesizeSpeechConfig) obj;
        if (Double.doubleToLongBits(getSpeakingRate()) == Double.doubleToLongBits(synthesizeSpeechConfig.getSpeakingRate()) && Double.doubleToLongBits(getPitch()) == Double.doubleToLongBits(synthesizeSpeechConfig.getPitch()) && Double.doubleToLongBits(getVolumeGainDb()) == Double.doubleToLongBits(synthesizeSpeechConfig.getVolumeGainDb()) && getEffectsProfileIdList().equals(synthesizeSpeechConfig.getEffectsProfileIdList()) && hasVoice() == synthesizeSpeechConfig.hasVoice()) {
            return (!hasVoice() || getVoice().equals(synthesizeSpeechConfig.getVoice())) && this.unknownFields.equals(synthesizeSpeechConfig.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getSpeakingRate()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getPitch()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getVolumeGainDb()));
        if (getEffectsProfileIdCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getEffectsProfileIdList().hashCode();
        }
        if (hasVoice()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getVoice().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SynthesizeSpeechConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SynthesizeSpeechConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SynthesizeSpeechConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SynthesizeSpeechConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SynthesizeSpeechConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SynthesizeSpeechConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SynthesizeSpeechConfig parseFrom(InputStream inputStream) throws IOException {
        return (SynthesizeSpeechConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SynthesizeSpeechConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesizeSpeechConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SynthesizeSpeechConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SynthesizeSpeechConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesizeSpeechConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SynthesizeSpeechConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SynthesizeSpeechConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SynthesizeSpeechConfig synthesizeSpeechConfig) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(synthesizeSpeechConfig);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SynthesizeSpeechConfigOrBuilder {
        private int bitField0_;
        private LazyStringList effectsProfileId_;
        private double pitch_;
        private double speakingRate_;
        private SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> voiceBuilder_;
        private VoiceSelectionParams voice_;
        private double volumeGainDb_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AudioConfigProto.f1392x8a530365;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AudioConfigProto.f1393xf70ef4e3.ensureFieldAccessorsInitialized(SynthesizeSpeechConfig.class, Builder.class);
        }

        private Builder() {
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = SynthesizeSpeechConfig.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.speakingRate_ = 0.0d;
            this.pitch_ = 0.0d;
            this.volumeGainDb_ = 0.0d;
            this.effectsProfileId_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            if (this.voiceBuilder_ == null) {
                this.voice_ = null;
            } else {
                this.voice_ = null;
                this.voiceBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AudioConfigProto.f1392x8a530365;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SynthesizeSpeechConfig getDefaultInstanceForType() {
            return SynthesizeSpeechConfig.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesizeSpeechConfig build() {
            SynthesizeSpeechConfig buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesizeSpeechConfig buildPartial() {
            SynthesizeSpeechConfig synthesizeSpeechConfig = new SynthesizeSpeechConfig(this);
            int i = this.bitField0_;
            synthesizeSpeechConfig.speakingRate_ = this.speakingRate_;
            synthesizeSpeechConfig.pitch_ = this.pitch_;
            synthesizeSpeechConfig.volumeGainDb_ = this.volumeGainDb_;
            if ((this.bitField0_ & 1) != 0) {
                this.effectsProfileId_ = this.effectsProfileId_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            synthesizeSpeechConfig.effectsProfileId_ = this.effectsProfileId_;
            SingleFieldBuilderV3<VoiceSelectionParams, VoiceSelectionParams.Builder, VoiceSelectionParamsOrBuilder> singleFieldBuilderV3 = this.voiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                synthesizeSpeechConfig.voice_ = this.voice_;
            } else {
                synthesizeSpeechConfig.voice_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return synthesizeSpeechConfig;
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
            if (message instanceof SynthesizeSpeechConfig) {
                return mergeFrom((SynthesizeSpeechConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SynthesizeSpeechConfig synthesizeSpeechConfig) {
            if (synthesizeSpeechConfig == SynthesizeSpeechConfig.getDefaultInstance()) {
                return this;
            }
            if (synthesizeSpeechConfig.getSpeakingRate() != 0.0d) {
                setSpeakingRate(synthesizeSpeechConfig.getSpeakingRate());
            }
            if (synthesizeSpeechConfig.getPitch() != 0.0d) {
                setPitch(synthesizeSpeechConfig.getPitch());
            }
            if (synthesizeSpeechConfig.getVolumeGainDb() != 0.0d) {
                setVolumeGainDb(synthesizeSpeechConfig.getVolumeGainDb());
            }
            if (!synthesizeSpeechConfig.effectsProfileId_.isEmpty()) {
                if (this.effectsProfileId_.isEmpty()) {
                    this.effectsProfileId_ = synthesizeSpeechConfig.effectsProfileId_;
                    this.bitField0_ &= -2;
                } else {
                    ensureEffectsProfileIdIsMutable();
                    this.effectsProfileId_.addAll(synthesizeSpeechConfig.effectsProfileId_);
                }
                onChanged();
            }
            if (synthesizeSpeechConfig.hasVoice()) {
                mergeVoice(synthesizeSpeechConfig.getVoice());
            }
            mergeUnknownFields(synthesizeSpeechConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SynthesizeSpeechConfig synthesizeSpeechConfig = null;
            try {
                try {
                    SynthesizeSpeechConfig synthesizeSpeechConfig2 = (SynthesizeSpeechConfig) SynthesizeSpeechConfig.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (synthesizeSpeechConfig2 != null) {
                        mergeFrom(synthesizeSpeechConfig2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SynthesizeSpeechConfig synthesizeSpeechConfig3 = (SynthesizeSpeechConfig) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        synthesizeSpeechConfig = synthesizeSpeechConfig3;
                        if (synthesizeSpeechConfig != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (synthesizeSpeechConfig != null) {
                    mergeFrom(synthesizeSpeechConfig);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
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

        private void ensureEffectsProfileIdIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.effectsProfileId_ = new LazyStringArrayList(this.effectsProfileId_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
        public ProtocolStringList getEffectsProfileIdList() {
            return this.effectsProfileId_.getUnmodifiableView();
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
        public int getEffectsProfileIdCount() {
            return this.effectsProfileId_.size();
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
        public String getEffectsProfileId(int i) {
            return (String) this.effectsProfileId_.get(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
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
                SynthesizeSpeechConfig.checkByteStringIsUtf8(byteString);
                ensureEffectsProfileIdIsMutable();
                this.effectsProfileId_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
        public boolean hasVoice() {
            return (this.voiceBuilder_ == null && this.voice_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfigOrBuilder
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

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static SynthesizeSpeechConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SynthesizeSpeechConfig> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SynthesizeSpeechConfig> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SynthesizeSpeechConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
