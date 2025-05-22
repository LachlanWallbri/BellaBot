package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.SynthesizeSpeechConfig;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class OutputAudioConfig extends GeneratedMessageV3 implements OutputAudioConfigOrBuilder {
    public static final int AUDIO_ENCODING_FIELD_NUMBER = 1;
    private static final OutputAudioConfig DEFAULT_INSTANCE = new OutputAudioConfig();
    private static final Parser<OutputAudioConfig> PARSER = new AbstractParser<OutputAudioConfig>() { // from class: com.google.cloud.dialogflow.v2.OutputAudioConfig.1
        @Override // com.google.protobuf.Parser
        public OutputAudioConfig parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new OutputAudioConfig(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SAMPLE_RATE_HERTZ_FIELD_NUMBER = 2;
    public static final int SYNTHESIZE_SPEECH_CONFIG_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int audioEncoding_;
    private byte memoizedIsInitialized;
    private int sampleRateHertz_;
    private SynthesizeSpeechConfig synthesizeSpeechConfig_;

    private OutputAudioConfig(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private OutputAudioConfig() {
        this.memoizedIsInitialized = (byte) -1;
        this.audioEncoding_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new OutputAudioConfig();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private OutputAudioConfig(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 8) {
                            this.audioEncoding_ = codedInputStream.readEnum();
                        } else if (readTag == 16) {
                            this.sampleRateHertz_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            SynthesizeSpeechConfig.Builder builder = this.synthesizeSpeechConfig_ != null ? this.synthesizeSpeechConfig_.toBuilder() : null;
                            this.synthesizeSpeechConfig_ = (SynthesizeSpeechConfig) codedInputStream.readMessage(SynthesizeSpeechConfig.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.synthesizeSpeechConfig_);
                                this.synthesizeSpeechConfig_ = builder.buildPartial();
                            }
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
        return AudioConfigProto.f1386xdbc4e372;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AudioConfigProto.f1387xb01a77f0.ensureFieldAccessorsInitialized(OutputAudioConfig.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
    public int getAudioEncodingValue() {
        return this.audioEncoding_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
    public OutputAudioEncoding getAudioEncoding() {
        OutputAudioEncoding valueOf = OutputAudioEncoding.valueOf(this.audioEncoding_);
        return valueOf == null ? OutputAudioEncoding.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
    public int getSampleRateHertz() {
        return this.sampleRateHertz_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
    public boolean hasSynthesizeSpeechConfig() {
        return this.synthesizeSpeechConfig_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
    public SynthesizeSpeechConfig getSynthesizeSpeechConfig() {
        SynthesizeSpeechConfig synthesizeSpeechConfig = this.synthesizeSpeechConfig_;
        return synthesizeSpeechConfig == null ? SynthesizeSpeechConfig.getDefaultInstance() : synthesizeSpeechConfig;
    }

    @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
    public SynthesizeSpeechConfigOrBuilder getSynthesizeSpeechConfigOrBuilder() {
        return getSynthesizeSpeechConfig();
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
        if (this.audioEncoding_ != OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.audioEncoding_);
        }
        int i = this.sampleRateHertz_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        if (this.synthesizeSpeechConfig_ != null) {
            codedOutputStream.writeMessage(3, getSynthesizeSpeechConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.audioEncoding_ != OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_UNSPECIFIED.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.audioEncoding_) : 0;
        int i2 = this.sampleRateHertz_;
        if (i2 != 0) {
            computeEnumSize += CodedOutputStream.computeInt32Size(2, i2);
        }
        if (this.synthesizeSpeechConfig_ != null) {
            computeEnumSize += CodedOutputStream.computeMessageSize(3, getSynthesizeSpeechConfig());
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
        if (!(obj instanceof OutputAudioConfig)) {
            return super.equals(obj);
        }
        OutputAudioConfig outputAudioConfig = (OutputAudioConfig) obj;
        if (this.audioEncoding_ == outputAudioConfig.audioEncoding_ && getSampleRateHertz() == outputAudioConfig.getSampleRateHertz() && hasSynthesizeSpeechConfig() == outputAudioConfig.hasSynthesizeSpeechConfig()) {
            return (!hasSynthesizeSpeechConfig() || getSynthesizeSpeechConfig().equals(outputAudioConfig.getSynthesizeSpeechConfig())) && this.unknownFields.equals(outputAudioConfig.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.audioEncoding_) * 37) + 2) * 53) + getSampleRateHertz();
        if (hasSynthesizeSpeechConfig()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getSynthesizeSpeechConfig().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static OutputAudioConfig parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static OutputAudioConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static OutputAudioConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static OutputAudioConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static OutputAudioConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static OutputAudioConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static OutputAudioConfig parseFrom(InputStream inputStream) throws IOException {
        return (OutputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static OutputAudioConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (OutputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OutputAudioConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (OutputAudioConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static OutputAudioConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (OutputAudioConfig) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static OutputAudioConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (OutputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static OutputAudioConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (OutputAudioConfig) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(OutputAudioConfig outputAudioConfig) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(outputAudioConfig);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements OutputAudioConfigOrBuilder {
        private int audioEncoding_;
        private int sampleRateHertz_;
        private SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> synthesizeSpeechConfigBuilder_;
        private SynthesizeSpeechConfig synthesizeSpeechConfig_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AudioConfigProto.f1386xdbc4e372;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AudioConfigProto.f1387xb01a77f0.ensureFieldAccessorsInitialized(OutputAudioConfig.class, Builder.class);
        }

        private Builder() {
            this.audioEncoding_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.audioEncoding_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = OutputAudioConfig.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.audioEncoding_ = 0;
            this.sampleRateHertz_ = 0;
            if (this.synthesizeSpeechConfigBuilder_ == null) {
                this.synthesizeSpeechConfig_ = null;
            } else {
                this.synthesizeSpeechConfig_ = null;
                this.synthesizeSpeechConfigBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AudioConfigProto.f1386xdbc4e372;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public OutputAudioConfig getDefaultInstanceForType() {
            return OutputAudioConfig.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public OutputAudioConfig build() {
            OutputAudioConfig buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public OutputAudioConfig buildPartial() {
            OutputAudioConfig outputAudioConfig = new OutputAudioConfig(this);
            outputAudioConfig.audioEncoding_ = this.audioEncoding_;
            outputAudioConfig.sampleRateHertz_ = this.sampleRateHertz_;
            SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> singleFieldBuilderV3 = this.synthesizeSpeechConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                outputAudioConfig.synthesizeSpeechConfig_ = this.synthesizeSpeechConfig_;
            } else {
                outputAudioConfig.synthesizeSpeechConfig_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return outputAudioConfig;
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
            if (message instanceof OutputAudioConfig) {
                return mergeFrom((OutputAudioConfig) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(OutputAudioConfig outputAudioConfig) {
            if (outputAudioConfig == OutputAudioConfig.getDefaultInstance()) {
                return this;
            }
            if (outputAudioConfig.audioEncoding_ != 0) {
                setAudioEncodingValue(outputAudioConfig.getAudioEncodingValue());
            }
            if (outputAudioConfig.getSampleRateHertz() != 0) {
                setSampleRateHertz(outputAudioConfig.getSampleRateHertz());
            }
            if (outputAudioConfig.hasSynthesizeSpeechConfig()) {
                mergeSynthesizeSpeechConfig(outputAudioConfig.getSynthesizeSpeechConfig());
            }
            mergeUnknownFields(outputAudioConfig.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            OutputAudioConfig outputAudioConfig = null;
            try {
                try {
                    OutputAudioConfig outputAudioConfig2 = (OutputAudioConfig) OutputAudioConfig.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (outputAudioConfig2 != null) {
                        mergeFrom(outputAudioConfig2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    OutputAudioConfig outputAudioConfig3 = (OutputAudioConfig) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        outputAudioConfig = outputAudioConfig3;
                        if (outputAudioConfig != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (outputAudioConfig != null) {
                    mergeFrom(outputAudioConfig);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
        public int getAudioEncodingValue() {
            return this.audioEncoding_;
        }

        public Builder setAudioEncodingValue(int i) {
            this.audioEncoding_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
        public OutputAudioEncoding getAudioEncoding() {
            OutputAudioEncoding valueOf = OutputAudioEncoding.valueOf(this.audioEncoding_);
            return valueOf == null ? OutputAudioEncoding.UNRECOGNIZED : valueOf;
        }

        public Builder setAudioEncoding(OutputAudioEncoding outputAudioEncoding) {
            if (outputAudioEncoding == null) {
                throw new NullPointerException();
            }
            this.audioEncoding_ = outputAudioEncoding.getNumber();
            onChanged();
            return this;
        }

        public Builder clearAudioEncoding() {
            this.audioEncoding_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
        public boolean hasSynthesizeSpeechConfig() {
            return (this.synthesizeSpeechConfigBuilder_ == null && this.synthesizeSpeechConfig_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
        public SynthesizeSpeechConfig getSynthesizeSpeechConfig() {
            SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> singleFieldBuilderV3 = this.synthesizeSpeechConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                SynthesizeSpeechConfig synthesizeSpeechConfig = this.synthesizeSpeechConfig_;
                return synthesizeSpeechConfig == null ? SynthesizeSpeechConfig.getDefaultInstance() : synthesizeSpeechConfig;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSynthesizeSpeechConfig(SynthesizeSpeechConfig synthesizeSpeechConfig) {
            SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> singleFieldBuilderV3 = this.synthesizeSpeechConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(synthesizeSpeechConfig);
            } else {
                if (synthesizeSpeechConfig == null) {
                    throw new NullPointerException();
                }
                this.synthesizeSpeechConfig_ = synthesizeSpeechConfig;
                onChanged();
            }
            return this;
        }

        public Builder setSynthesizeSpeechConfig(SynthesizeSpeechConfig.Builder builder) {
            SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> singleFieldBuilderV3 = this.synthesizeSpeechConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.synthesizeSpeechConfig_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSynthesizeSpeechConfig(SynthesizeSpeechConfig synthesizeSpeechConfig) {
            SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> singleFieldBuilderV3 = this.synthesizeSpeechConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                SynthesizeSpeechConfig synthesizeSpeechConfig2 = this.synthesizeSpeechConfig_;
                if (synthesizeSpeechConfig2 != null) {
                    this.synthesizeSpeechConfig_ = SynthesizeSpeechConfig.newBuilder(synthesizeSpeechConfig2).mergeFrom(synthesizeSpeechConfig).buildPartial();
                } else {
                    this.synthesizeSpeechConfig_ = synthesizeSpeechConfig;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(synthesizeSpeechConfig);
            }
            return this;
        }

        public Builder clearSynthesizeSpeechConfig() {
            if (this.synthesizeSpeechConfigBuilder_ == null) {
                this.synthesizeSpeechConfig_ = null;
                onChanged();
            } else {
                this.synthesizeSpeechConfig_ = null;
                this.synthesizeSpeechConfigBuilder_ = null;
            }
            return this;
        }

        public SynthesizeSpeechConfig.Builder getSynthesizeSpeechConfigBuilder() {
            onChanged();
            return getSynthesizeSpeechConfigFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.OutputAudioConfigOrBuilder
        public SynthesizeSpeechConfigOrBuilder getSynthesizeSpeechConfigOrBuilder() {
            SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> singleFieldBuilderV3 = this.synthesizeSpeechConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SynthesizeSpeechConfig synthesizeSpeechConfig = this.synthesizeSpeechConfig_;
            return synthesizeSpeechConfig == null ? SynthesizeSpeechConfig.getDefaultInstance() : synthesizeSpeechConfig;
        }

        private SingleFieldBuilderV3<SynthesizeSpeechConfig, SynthesizeSpeechConfig.Builder, SynthesizeSpeechConfigOrBuilder> getSynthesizeSpeechConfigFieldBuilder() {
            if (this.synthesizeSpeechConfigBuilder_ == null) {
                this.synthesizeSpeechConfigBuilder_ = new SingleFieldBuilderV3<>(getSynthesizeSpeechConfig(), getParentForChildren(), isClean());
                this.synthesizeSpeechConfig_ = null;
            }
            return this.synthesizeSpeechConfigBuilder_;
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

    public static OutputAudioConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<OutputAudioConfig> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<OutputAudioConfig> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public OutputAudioConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
