package com.google.cloud.texttospeech.v1beta1;

import com.google.cloud.texttospeech.v1beta1.Voice;
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
public final class ListVoicesResponse extends GeneratedMessageV3 implements ListVoicesResponseOrBuilder {
    private static final ListVoicesResponse DEFAULT_INSTANCE = new ListVoicesResponse();
    private static final Parser<ListVoicesResponse> PARSER = new AbstractParser<ListVoicesResponse>() { // from class: com.google.cloud.texttospeech.v1beta1.ListVoicesResponse.1
        @Override // com.google.protobuf.Parser
        public ListVoicesResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListVoicesResponse(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VOICES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Voice> voices_;

    private ListVoicesResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListVoicesResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.voices_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ListVoicesResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListVoicesResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (!(z2 & true)) {
                                    this.voices_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.voices_.add(codedInputStream.readMessage(Voice.parser(), extensionRegistryLite));
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
                    this.voices_ = Collections.unmodifiableList(this.voices_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TextToSpeechProto.f1899xdaf2a641;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TextToSpeechProto.f1900xf1bbfbbf.ensureFieldAccessorsInitialized(ListVoicesResponse.class, Builder.class);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
    public List<Voice> getVoicesList() {
        return this.voices_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
    public List<? extends VoiceOrBuilder> getVoicesOrBuilderList() {
        return this.voices_;
    }

    @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
    public int getVoicesCount() {
        return this.voices_.size();
    }

    @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
    public Voice getVoices(int i) {
        return this.voices_.get(i);
    }

    @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
    public VoiceOrBuilder getVoicesOrBuilder(int i) {
        return this.voices_.get(i);
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
        for (int i = 0; i < this.voices_.size(); i++) {
            codedOutputStream.writeMessage(1, this.voices_.get(i));
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
        for (int i3 = 0; i3 < this.voices_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.voices_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListVoicesResponse)) {
            return super.equals(obj);
        }
        ListVoicesResponse listVoicesResponse = (ListVoicesResponse) obj;
        return getVoicesList().equals(listVoicesResponse.getVoicesList()) && this.unknownFields.equals(listVoicesResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getVoicesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getVoicesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ListVoicesResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListVoicesResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListVoicesResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListVoicesResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListVoicesResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListVoicesResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListVoicesResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListVoicesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListVoicesResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListVoicesResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListVoicesResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListVoicesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListVoicesResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListVoicesResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListVoicesResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListVoicesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListVoicesResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListVoicesResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListVoicesResponse listVoicesResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(listVoicesResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListVoicesResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> voicesBuilder_;
        private List<Voice> voices_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TextToSpeechProto.f1899xdaf2a641;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TextToSpeechProto.f1900xf1bbfbbf.ensureFieldAccessorsInitialized(ListVoicesResponse.class, Builder.class);
        }

        private Builder() {
            this.voices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.voices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ListVoicesResponse.alwaysUseFieldBuilders) {
                getVoicesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.voices_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TextToSpeechProto.f1899xdaf2a641;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ListVoicesResponse getDefaultInstanceForType() {
            return ListVoicesResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListVoicesResponse build() {
            ListVoicesResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ListVoicesResponse buildPartial() {
            ListVoicesResponse listVoicesResponse = new ListVoicesResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.voices_ = Collections.unmodifiableList(this.voices_);
                    this.bitField0_ &= -2;
                }
                listVoicesResponse.voices_ = this.voices_;
            } else {
                listVoicesResponse.voices_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return listVoicesResponse;
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
            if (message instanceof ListVoicesResponse) {
                return mergeFrom((ListVoicesResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListVoicesResponse listVoicesResponse) {
            if (listVoicesResponse == ListVoicesResponse.getDefaultInstance()) {
                return this;
            }
            if (this.voicesBuilder_ == null) {
                if (!listVoicesResponse.voices_.isEmpty()) {
                    if (this.voices_.isEmpty()) {
                        this.voices_ = listVoicesResponse.voices_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureVoicesIsMutable();
                        this.voices_.addAll(listVoicesResponse.voices_);
                    }
                    onChanged();
                }
            } else if (!listVoicesResponse.voices_.isEmpty()) {
                if (!this.voicesBuilder_.isEmpty()) {
                    this.voicesBuilder_.addAllMessages(listVoicesResponse.voices_);
                } else {
                    this.voicesBuilder_.dispose();
                    this.voicesBuilder_ = null;
                    this.voices_ = listVoicesResponse.voices_;
                    this.bitField0_ &= -2;
                    this.voicesBuilder_ = ListVoicesResponse.alwaysUseFieldBuilders ? getVoicesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(listVoicesResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ListVoicesResponse listVoicesResponse = null;
            try {
                try {
                    ListVoicesResponse listVoicesResponse2 = (ListVoicesResponse) ListVoicesResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (listVoicesResponse2 != null) {
                        mergeFrom(listVoicesResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ListVoicesResponse listVoicesResponse3 = (ListVoicesResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        listVoicesResponse = listVoicesResponse3;
                        if (listVoicesResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (listVoicesResponse != null) {
                    mergeFrom(listVoicesResponse);
                }
                throw th;
            }
        }

        private void ensureVoicesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.voices_ = new ArrayList(this.voices_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
        public List<Voice> getVoicesList() {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.voices_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
        public int getVoicesCount() {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.voices_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
        public Voice getVoices(int i) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.voices_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setVoices(int i, Voice voice) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, voice);
            } else {
                if (voice == null) {
                    throw new NullPointerException();
                }
                ensureVoicesIsMutable();
                this.voices_.set(i, voice);
                onChanged();
            }
            return this;
        }

        public Builder setVoices(int i, Voice.Builder builder) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVoicesIsMutable();
                this.voices_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addVoices(Voice voice) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(voice);
            } else {
                if (voice == null) {
                    throw new NullPointerException();
                }
                ensureVoicesIsMutable();
                this.voices_.add(voice);
                onChanged();
            }
            return this;
        }

        public Builder addVoices(int i, Voice voice) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, voice);
            } else {
                if (voice == null) {
                    throw new NullPointerException();
                }
                ensureVoicesIsMutable();
                this.voices_.add(i, voice);
                onChanged();
            }
            return this;
        }

        public Builder addVoices(Voice.Builder builder) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVoicesIsMutable();
                this.voices_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addVoices(int i, Voice.Builder builder) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVoicesIsMutable();
                this.voices_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllVoices(Iterable<? extends Voice> iterable) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVoicesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.voices_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearVoices() {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.voices_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeVoices(int i) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureVoicesIsMutable();
                this.voices_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Voice.Builder getVoicesBuilder(int i) {
            return getVoicesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
        public VoiceOrBuilder getVoicesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.voices_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.texttospeech.v1beta1.ListVoicesResponseOrBuilder
        public List<? extends VoiceOrBuilder> getVoicesOrBuilderList() {
            RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> repeatedFieldBuilderV3 = this.voicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.voices_);
        }

        public Voice.Builder addVoicesBuilder() {
            return getVoicesFieldBuilder().addBuilder(Voice.getDefaultInstance());
        }

        public Voice.Builder addVoicesBuilder(int i) {
            return getVoicesFieldBuilder().addBuilder(i, Voice.getDefaultInstance());
        }

        public List<Voice.Builder> getVoicesBuilderList() {
            return getVoicesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Voice, Voice.Builder, VoiceOrBuilder> getVoicesFieldBuilder() {
            if (this.voicesBuilder_ == null) {
                this.voicesBuilder_ = new RepeatedFieldBuilderV3<>(this.voices_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.voices_ = null;
            }
            return this.voicesBuilder_;
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

    public static ListVoicesResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListVoicesResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ListVoicesResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ListVoicesResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
