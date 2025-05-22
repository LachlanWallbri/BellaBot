package com.google.cloud.texttospeech.v1beta1;

import com.google.cloud.texttospeech.v1beta1.AudioConfig;
import com.google.cloud.texttospeech.v1beta1.Timepoint;
import com.google.protobuf.AbstractMessageLite;
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
public final class SynthesizeSpeechResponse extends GeneratedMessageV3 implements SynthesizeSpeechResponseOrBuilder {
    public static final int AUDIO_CONFIG_FIELD_NUMBER = 4;
    public static final int AUDIO_CONTENT_FIELD_NUMBER = 1;
    private static final SynthesizeSpeechResponse DEFAULT_INSTANCE = new SynthesizeSpeechResponse();
    private static final Parser<SynthesizeSpeechResponse> PARSER = new AbstractParser<SynthesizeSpeechResponse>() { // from class: com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponse.1
        @Override // com.google.protobuf.Parser
        public SynthesizeSpeechResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SynthesizeSpeechResponse(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TIMEPOINTS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private AudioConfig audioConfig_;
    private ByteString audioContent_;
    private byte memoizedIsInitialized;
    private List<Timepoint> timepoints_;

    private SynthesizeSpeechResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SynthesizeSpeechResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.audioContent_ = ByteString.EMPTY;
        this.timepoints_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SynthesizeSpeechResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SynthesizeSpeechResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.audioContent_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                if (!(z2 & true)) {
                                    this.timepoints_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.timepoints_.add(codedInputStream.readMessage(Timepoint.parser(), extensionRegistryLite));
                            } else if (readTag == 34) {
                                AudioConfig.Builder builder = this.audioConfig_ != null ? this.audioConfig_.toBuilder() : null;
                                this.audioConfig_ = (AudioConfig) codedInputStream.readMessage(AudioConfig.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.audioConfig_);
                                    this.audioConfig_ = builder.buildPartial();
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
                    this.timepoints_ = Collections.unmodifiableList(this.timepoints_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TextToSpeechProto.f1905x61fa68f4;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1906x9bb5b72.ensureFieldAccessorsInitialized(SynthesizeSpeechResponse.class, Builder.class);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public ByteString getAudioContent() {
        return this.audioContent_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public List<Timepoint> getTimepointsList() {
        return this.timepoints_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public List<? extends TimepointOrBuilder> getTimepointsOrBuilderList() {
        return this.timepoints_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public int getTimepointsCount() {
        return this.timepoints_.size();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public Timepoint getTimepoints(int i) {
        return this.timepoints_.get(i);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public TimepointOrBuilder getTimepointsOrBuilder(int i) {
        return this.timepoints_.get(i);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public boolean hasAudioConfig() {
        return this.audioConfig_ != null;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public AudioConfig getAudioConfig() {
        AudioConfig audioConfig = this.audioConfig_;
        return audioConfig == null ? AudioConfig.getDefaultInstance() : audioConfig;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
    public AudioConfigOrBuilder getAudioConfigOrBuilder() {
        return getAudioConfig();
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
        if (!this.audioContent_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.audioContent_);
        }
        for (int i = 0; i < this.timepoints_.size(); i++) {
            codedOutputStream.writeMessage(2, this.timepoints_.get(i));
        }
        if (this.audioConfig_ != null) {
            codedOutputStream.writeMessage(4, getAudioConfig());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeBytesSize = !this.audioContent_.isEmpty() ? CodedOutputStream.computeBytesSize(1, this.audioContent_) + 0 : 0;
        for (int i2 = 0; i2 < this.timepoints_.size(); i2++) {
            computeBytesSize += CodedOutputStream.computeMessageSize(2, this.timepoints_.get(i2));
        }
        if (this.audioConfig_ != null) {
            computeBytesSize += CodedOutputStream.computeMessageSize(4, getAudioConfig());
        }
        int serializedSize = computeBytesSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SynthesizeSpeechResponse)) {
            return super.equals(obj);
        }
        SynthesizeSpeechResponse synthesizeSpeechResponse = (SynthesizeSpeechResponse) obj;
        if (getAudioContent().equals(synthesizeSpeechResponse.getAudioContent()) && getTimepointsList().equals(synthesizeSpeechResponse.getTimepointsList()) && hasAudioConfig() == synthesizeSpeechResponse.hasAudioConfig()) {
            return (!hasAudioConfig() || getAudioConfig().equals(synthesizeSpeechResponse.getAudioConfig())) && this.unknownFields.equals(synthesizeSpeechResponse.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAudioContent().hashCode();
        if (getTimepointsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getTimepointsList().hashCode();
        }
        if (hasAudioConfig()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getAudioConfig().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SynthesizeSpeechResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SynthesizeSpeechResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SynthesizeSpeechResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SynthesizeSpeechResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SynthesizeSpeechResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SynthesizeSpeechResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SynthesizeSpeechResponse parseFrom(InputStream inputStream) throws IOException {
        return (SynthesizeSpeechResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SynthesizeSpeechResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesizeSpeechResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SynthesizeSpeechResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SynthesizeSpeechResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynthesizeSpeechResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SynthesizeSpeechResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SynthesizeSpeechResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynthesizeSpeechResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SynthesizeSpeechResponse synthesizeSpeechResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(synthesizeSpeechResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SynthesizeSpeechResponseOrBuilder {
        private SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> audioConfigBuilder_;
        private AudioConfig audioConfig_;
        private ByteString audioContent_;
        private int bitField0_;
        private RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> timepointsBuilder_;
        private List<Timepoint> timepoints_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1905x61fa68f4;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1906x9bb5b72.ensureFieldAccessorsInitialized(SynthesizeSpeechResponse.class, Builder.class);
        }

        private Builder() {
            this.audioContent_ = ByteString.EMPTY;
            this.timepoints_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.audioContent_ = ByteString.EMPTY;
            this.timepoints_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SynthesizeSpeechResponse.alwaysUseFieldBuilders) {
                getTimepointsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.audioContent_ = ByteString.EMPTY;
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.timepoints_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.audioConfigBuilder_ == null) {
                this.audioConfig_ = null;
            } else {
                this.audioConfig_ = null;
                this.audioConfigBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1905x61fa68f4;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SynthesizeSpeechResponse getDefaultInstanceForType() {
            return SynthesizeSpeechResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesizeSpeechResponse build() {
            SynthesizeSpeechResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SynthesizeSpeechResponse buildPartial() {
            SynthesizeSpeechResponse synthesizeSpeechResponse = new SynthesizeSpeechResponse(this);
            int i = this.bitField0_;
            synthesizeSpeechResponse.audioContent_ = this.audioContent_;
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                synthesizeSpeechResponse.timepoints_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.timepoints_ = Collections.unmodifiableList(this.timepoints_);
                    this.bitField0_ &= -2;
                }
                synthesizeSpeechResponse.timepoints_ = this.timepoints_;
            }
            SingleFieldBuilderV3<AudioConfig, AudioConfig.Builder, AudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                synthesizeSpeechResponse.audioConfig_ = this.audioConfig_;
            } else {
                synthesizeSpeechResponse.audioConfig_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return synthesizeSpeechResponse;
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
            if (message instanceof SynthesizeSpeechResponse) {
                return mergeFrom((SynthesizeSpeechResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SynthesizeSpeechResponse synthesizeSpeechResponse) {
            if (synthesizeSpeechResponse == SynthesizeSpeechResponse.getDefaultInstance()) {
                return this;
            }
            if (synthesizeSpeechResponse.getAudioContent() != ByteString.EMPTY) {
                setAudioContent(synthesizeSpeechResponse.getAudioContent());
            }
            if (this.timepointsBuilder_ == null) {
                if (!synthesizeSpeechResponse.timepoints_.isEmpty()) {
                    if (this.timepoints_.isEmpty()) {
                        this.timepoints_ = synthesizeSpeechResponse.timepoints_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTimepointsIsMutable();
                        this.timepoints_.addAll(synthesizeSpeechResponse.timepoints_);
                    }
                    onChanged();
                }
            } else if (!synthesizeSpeechResponse.timepoints_.isEmpty()) {
                if (!this.timepointsBuilder_.isEmpty()) {
                    this.timepointsBuilder_.addAllMessages(synthesizeSpeechResponse.timepoints_);
                } else {
                    this.timepointsBuilder_.dispose();
                    this.timepointsBuilder_ = null;
                    this.timepoints_ = synthesizeSpeechResponse.timepoints_;
                    this.bitField0_ &= -2;
                    this.timepointsBuilder_ = SynthesizeSpeechResponse.alwaysUseFieldBuilders ? getTimepointsFieldBuilder() : null;
                }
            }
            if (synthesizeSpeechResponse.hasAudioConfig()) {
                mergeAudioConfig(synthesizeSpeechResponse.getAudioConfig());
            }
            mergeUnknownFields(synthesizeSpeechResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SynthesizeSpeechResponse synthesizeSpeechResponse = null;
            try {
                try {
                    SynthesizeSpeechResponse synthesizeSpeechResponse2 = (SynthesizeSpeechResponse) SynthesizeSpeechResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (synthesizeSpeechResponse2 != null) {
                        mergeFrom(synthesizeSpeechResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SynthesizeSpeechResponse synthesizeSpeechResponse3 = (SynthesizeSpeechResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        synthesizeSpeechResponse = synthesizeSpeechResponse3;
                        if (synthesizeSpeechResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (synthesizeSpeechResponse != null) {
                    mergeFrom(synthesizeSpeechResponse);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public ByteString getAudioContent() {
            return this.audioContent_;
        }

        public Builder setAudioContent(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.audioContent_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearAudioContent() {
            this.audioContent_ = SynthesizeSpeechResponse.getDefaultInstance().getAudioContent();
            onChanged();
            return this;
        }

        private void ensureTimepointsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.timepoints_ = new ArrayList(this.timepoints_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public List<Timepoint> getTimepointsList() {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.timepoints_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public int getTimepointsCount() {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.timepoints_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public Timepoint getTimepoints(int i) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.timepoints_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTimepoints(int i, Timepoint timepoint) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, timepoint);
            } else {
                if (timepoint == null) {
                    throw new NullPointerException();
                }
                ensureTimepointsIsMutable();
                this.timepoints_.set(i, timepoint);
                onChanged();
            }
            return this;
        }

        public Builder setTimepoints(int i, Timepoint.Builder builder) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTimepointsIsMutable();
                this.timepoints_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addTimepoints(Timepoint timepoint) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(timepoint);
            } else {
                if (timepoint == null) {
                    throw new NullPointerException();
                }
                ensureTimepointsIsMutable();
                this.timepoints_.add(timepoint);
                onChanged();
            }
            return this;
        }

        public Builder addTimepoints(int i, Timepoint timepoint) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, timepoint);
            } else {
                if (timepoint == null) {
                    throw new NullPointerException();
                }
                ensureTimepointsIsMutable();
                this.timepoints_.add(i, timepoint);
                onChanged();
            }
            return this;
        }

        public Builder addTimepoints(Timepoint.Builder builder) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTimepointsIsMutable();
                this.timepoints_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addTimepoints(int i, Timepoint.Builder builder) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTimepointsIsMutable();
                this.timepoints_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllTimepoints(Iterable<? extends Timepoint> iterable) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTimepointsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.timepoints_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTimepoints() {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.timepoints_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTimepoints(int i) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTimepointsIsMutable();
                this.timepoints_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Timepoint.Builder getTimepointsBuilder(int i) {
            return getTimepointsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public TimepointOrBuilder getTimepointsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.timepoints_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public List<? extends TimepointOrBuilder> getTimepointsOrBuilderList() {
            RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> repeatedFieldBuilderV3 = this.timepointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.timepoints_);
        }

        public Timepoint.Builder addTimepointsBuilder() {
            return getTimepointsFieldBuilder().addBuilder(Timepoint.getDefaultInstance());
        }

        public Timepoint.Builder addTimepointsBuilder(int i) {
            return getTimepointsFieldBuilder().addBuilder(i, Timepoint.getDefaultInstance());
        }

        public List<Timepoint.Builder> getTimepointsBuilderList() {
            return getTimepointsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Timepoint, Timepoint.Builder, TimepointOrBuilder> getTimepointsFieldBuilder() {
            if (this.timepointsBuilder_ == null) {
                this.timepointsBuilder_ = new RepeatedFieldBuilderV3<>(this.timepoints_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.timepoints_ = null;
            }
            return this.timepointsBuilder_;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
        public boolean hasAudioConfig() {
            return (this.audioConfigBuilder_ == null && this.audioConfig_ == null) ? false : true;
        }

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
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

        @Override // com.google.cloud.texttospeech.v1beta1.SynthesizeSpeechResponseOrBuilder
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

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static SynthesizeSpeechResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SynthesizeSpeechResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SynthesizeSpeechResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SynthesizeSpeechResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
