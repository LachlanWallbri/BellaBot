package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.EventInput;
import com.google.cloud.dialogflow.p049v2.InputAudioConfig;
import com.google.cloud.dialogflow.p049v2.TextInput;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class QueryInput extends GeneratedMessageV3 implements QueryInputOrBuilder {
    public static final int AUDIO_CONFIG_FIELD_NUMBER = 1;
    public static final int EVENT_FIELD_NUMBER = 3;
    public static final int TEXT_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private int inputCase_;
    private Object input_;
    private byte memoizedIsInitialized;
    private static final QueryInput DEFAULT_INSTANCE = new QueryInput();
    private static final Parser<QueryInput> PARSER = new AbstractParser<QueryInput>() { // from class: com.google.cloud.dialogflow.v2.QueryInput.1
        @Override // com.google.protobuf.Parser
        public QueryInput parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new QueryInput(codedInputStream, extensionRegistryLite);
        }
    };

    private QueryInput(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.inputCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private QueryInput() {
        this.inputCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new QueryInput();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private QueryInput(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            InputAudioConfig.Builder builder = this.inputCase_ == 1 ? ((InputAudioConfig) this.input_).toBuilder() : null;
                            this.input_ = codedInputStream.readMessage(InputAudioConfig.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom((InputAudioConfig) this.input_);
                                this.input_ = builder.buildPartial();
                            }
                            this.inputCase_ = 1;
                        } else if (readTag == 18) {
                            TextInput.Builder builder2 = this.inputCase_ == 2 ? ((TextInput) this.input_).toBuilder() : null;
                            this.input_ = codedInputStream.readMessage(TextInput.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom((TextInput) this.input_);
                                this.input_ = builder2.buildPartial();
                            }
                            this.inputCase_ = 2;
                        } else if (readTag == 26) {
                            EventInput.Builder builder3 = this.inputCase_ == 3 ? ((EventInput) this.input_).toBuilder() : null;
                            this.input_ = codedInputStream.readMessage(EventInput.parser(), extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom((EventInput) this.input_);
                                this.input_ = builder3.buildPartial();
                            }
                            this.inputCase_ = 3;
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
        return SessionProto.internal_static_google_cloud_dialogflow_v2_QueryInput_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1551x7cb69c0f.ensureFieldAccessorsInitialized(QueryInput.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum InputCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        AUDIO_CONFIG(1),
        TEXT(2),
        EVENT(3),
        INPUT_NOT_SET(0);

        private final int value;

        InputCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static InputCase valueOf(int i) {
            return forNumber(i);
        }

        public static InputCase forNumber(int i) {
            if (i == 0) {
                return INPUT_NOT_SET;
            }
            if (i == 1) {
                return AUDIO_CONFIG;
            }
            if (i == 2) {
                return TEXT;
            }
            if (i != 3) {
                return null;
            }
            return EVENT;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public InputCase getInputCase() {
        return InputCase.forNumber(this.inputCase_);
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public boolean hasAudioConfig() {
        return this.inputCase_ == 1;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public InputAudioConfig getAudioConfig() {
        if (this.inputCase_ == 1) {
            return (InputAudioConfig) this.input_;
        }
        return InputAudioConfig.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public InputAudioConfigOrBuilder getAudioConfigOrBuilder() {
        if (this.inputCase_ == 1) {
            return (InputAudioConfig) this.input_;
        }
        return InputAudioConfig.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public boolean hasText() {
        return this.inputCase_ == 2;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public TextInput getText() {
        if (this.inputCase_ == 2) {
            return (TextInput) this.input_;
        }
        return TextInput.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public TextInputOrBuilder getTextOrBuilder() {
        if (this.inputCase_ == 2) {
            return (TextInput) this.input_;
        }
        return TextInput.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public boolean hasEvent() {
        return this.inputCase_ == 3;
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public EventInput getEvent() {
        if (this.inputCase_ == 3) {
            return (EventInput) this.input_;
        }
        return EventInput.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
    public EventInputOrBuilder getEventOrBuilder() {
        if (this.inputCase_ == 3) {
            return (EventInput) this.input_;
        }
        return EventInput.getDefaultInstance();
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
        if (this.inputCase_ == 1) {
            codedOutputStream.writeMessage(1, (InputAudioConfig) this.input_);
        }
        if (this.inputCase_ == 2) {
            codedOutputStream.writeMessage(2, (TextInput) this.input_);
        }
        if (this.inputCase_ == 3) {
            codedOutputStream.writeMessage(3, (EventInput) this.input_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.inputCase_ == 1 ? 0 + CodedOutputStream.computeMessageSize(1, (InputAudioConfig) this.input_) : 0;
        if (this.inputCase_ == 2) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, (TextInput) this.input_);
        }
        if (this.inputCase_ == 3) {
            computeMessageSize += CodedOutputStream.computeMessageSize(3, (EventInput) this.input_);
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
        if (!(obj instanceof QueryInput)) {
            return super.equals(obj);
        }
        QueryInput queryInput = (QueryInput) obj;
        if (!getInputCase().equals(queryInput.getInputCase())) {
            return false;
        }
        int i = this.inputCase_;
        if (i != 1) {
            if (i == 2) {
                if (!getText().equals(queryInput.getText())) {
                    return false;
                }
            } else if (i == 3 && !getEvent().equals(queryInput.getEvent())) {
                return false;
            }
        } else if (!getAudioConfig().equals(queryInput.getAudioConfig())) {
            return false;
        }
        return this.unknownFields.equals(queryInput.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = 779 + getDescriptor().hashCode();
        int i2 = this.inputCase_;
        if (i2 == 1) {
            i = ((hashCode2 * 37) + 1) * 53;
            hashCode = getAudioConfig().hashCode();
        } else if (i2 == 2) {
            i = ((hashCode2 * 37) + 2) * 53;
            hashCode = getText().hashCode();
        } else {
            if (i2 == 3) {
                i = ((hashCode2 * 37) + 3) * 53;
                hashCode = getEvent().hashCode();
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

    public static QueryInput parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static QueryInput parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static QueryInput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static QueryInput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static QueryInput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static QueryInput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static QueryInput parseFrom(InputStream inputStream) throws IOException {
        return (QueryInput) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static QueryInput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryInput) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryInput parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (QueryInput) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static QueryInput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryInput) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QueryInput parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (QueryInput) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static QueryInput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QueryInput) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QueryInput queryInput) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(queryInput);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QueryInputOrBuilder {
        private SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> audioConfigBuilder_;
        private SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> eventBuilder_;
        private int inputCase_;
        private Object input_;
        private SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> textBuilder_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.internal_static_google_cloud_dialogflow_v2_QueryInput_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1551x7cb69c0f.ensureFieldAccessorsInitialized(QueryInput.class, Builder.class);
        }

        private Builder() {
            this.inputCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.inputCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = QueryInput.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.inputCase_ = 0;
            this.input_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.internal_static_google_cloud_dialogflow_v2_QueryInput_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public QueryInput getDefaultInstanceForType() {
            return QueryInput.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QueryInput build() {
            QueryInput buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public QueryInput buildPartial() {
            QueryInput queryInput = new QueryInput(this);
            if (this.inputCase_ == 1) {
                SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    queryInput.input_ = this.input_;
                } else {
                    queryInput.input_ = singleFieldBuilderV3.build();
                }
            }
            if (this.inputCase_ == 2) {
                SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> singleFieldBuilderV32 = this.textBuilder_;
                if (singleFieldBuilderV32 == null) {
                    queryInput.input_ = this.input_;
                } else {
                    queryInput.input_ = singleFieldBuilderV32.build();
                }
            }
            if (this.inputCase_ == 3) {
                SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV33 = this.eventBuilder_;
                if (singleFieldBuilderV33 == null) {
                    queryInput.input_ = this.input_;
                } else {
                    queryInput.input_ = singleFieldBuilderV33.build();
                }
            }
            queryInput.inputCase_ = this.inputCase_;
            onBuilt();
            return queryInput;
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
            if (message instanceof QueryInput) {
                return mergeFrom((QueryInput) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(QueryInput queryInput) {
            if (queryInput == QueryInput.getDefaultInstance()) {
                return this;
            }
            int i = C22762.$SwitchMap$com$google$cloud$dialogflow$v2$QueryInput$InputCase[queryInput.getInputCase().ordinal()];
            if (i == 1) {
                mergeAudioConfig(queryInput.getAudioConfig());
            } else if (i == 2) {
                mergeText(queryInput.getText());
            } else if (i == 3) {
                mergeEvent(queryInput.getEvent());
            }
            mergeUnknownFields(queryInput.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            QueryInput queryInput = null;
            try {
                try {
                    QueryInput queryInput2 = (QueryInput) QueryInput.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (queryInput2 != null) {
                        mergeFrom(queryInput2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    QueryInput queryInput3 = (QueryInput) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        queryInput = queryInput3;
                        if (queryInput != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (queryInput != null) {
                    mergeFrom(queryInput);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public InputCase getInputCase() {
            return InputCase.forNumber(this.inputCase_);
        }

        public Builder clearInput() {
            this.inputCase_ = 0;
            this.input_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public boolean hasAudioConfig() {
            return this.inputCase_ == 1;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public InputAudioConfig getAudioConfig() {
            SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.inputCase_ == 1) {
                    return (InputAudioConfig) this.input_;
                }
                return InputAudioConfig.getDefaultInstance();
            }
            if (this.inputCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return InputAudioConfig.getDefaultInstance();
        }

        public Builder setAudioConfig(InputAudioConfig inputAudioConfig) {
            SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(inputAudioConfig);
            } else {
                if (inputAudioConfig == null) {
                    throw new NullPointerException();
                }
                this.input_ = inputAudioConfig;
                onChanged();
            }
            this.inputCase_ = 1;
            return this;
        }

        public Builder setAudioConfig(InputAudioConfig.Builder builder) {
            SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.input_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.inputCase_ = 1;
            return this;
        }

        public Builder mergeAudioConfig(InputAudioConfig inputAudioConfig) {
            SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> singleFieldBuilderV3 = this.audioConfigBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.inputCase_ == 1 && this.input_ != InputAudioConfig.getDefaultInstance()) {
                    this.input_ = InputAudioConfig.newBuilder((InputAudioConfig) this.input_).mergeFrom(inputAudioConfig).buildPartial();
                } else {
                    this.input_ = inputAudioConfig;
                }
                onChanged();
            } else {
                if (this.inputCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(inputAudioConfig);
                }
                this.audioConfigBuilder_.setMessage(inputAudioConfig);
            }
            this.inputCase_ = 1;
            return this;
        }

        public Builder clearAudioConfig() {
            if (this.audioConfigBuilder_ == null) {
                if (this.inputCase_ == 1) {
                    this.inputCase_ = 0;
                    this.input_ = null;
                    onChanged();
                }
            } else {
                if (this.inputCase_ == 1) {
                    this.inputCase_ = 0;
                    this.input_ = null;
                }
                this.audioConfigBuilder_.clear();
            }
            return this;
        }

        public InputAudioConfig.Builder getAudioConfigBuilder() {
            return getAudioConfigFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public InputAudioConfigOrBuilder getAudioConfigOrBuilder() {
            SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> singleFieldBuilderV3;
            if (this.inputCase_ == 1 && (singleFieldBuilderV3 = this.audioConfigBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.inputCase_ == 1) {
                return (InputAudioConfig) this.input_;
            }
            return InputAudioConfig.getDefaultInstance();
        }

        private SingleFieldBuilderV3<InputAudioConfig, InputAudioConfig.Builder, InputAudioConfigOrBuilder> getAudioConfigFieldBuilder() {
            if (this.audioConfigBuilder_ == null) {
                if (this.inputCase_ != 1) {
                    this.input_ = InputAudioConfig.getDefaultInstance();
                }
                this.audioConfigBuilder_ = new SingleFieldBuilderV3<>((InputAudioConfig) this.input_, getParentForChildren(), isClean());
                this.input_ = null;
            }
            this.inputCase_ = 1;
            onChanged();
            return this.audioConfigBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public boolean hasText() {
            return this.inputCase_ == 2;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public TextInput getText() {
            SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> singleFieldBuilderV3 = this.textBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.inputCase_ == 2) {
                    return (TextInput) this.input_;
                }
                return TextInput.getDefaultInstance();
            }
            if (this.inputCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return TextInput.getDefaultInstance();
        }

        public Builder setText(TextInput textInput) {
            SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> singleFieldBuilderV3 = this.textBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(textInput);
            } else {
                if (textInput == null) {
                    throw new NullPointerException();
                }
                this.input_ = textInput;
                onChanged();
            }
            this.inputCase_ = 2;
            return this;
        }

        public Builder setText(TextInput.Builder builder) {
            SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> singleFieldBuilderV3 = this.textBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.input_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.inputCase_ = 2;
            return this;
        }

        public Builder mergeText(TextInput textInput) {
            SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> singleFieldBuilderV3 = this.textBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.inputCase_ == 2 && this.input_ != TextInput.getDefaultInstance()) {
                    this.input_ = TextInput.newBuilder((TextInput) this.input_).mergeFrom(textInput).buildPartial();
                } else {
                    this.input_ = textInput;
                }
                onChanged();
            } else {
                if (this.inputCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(textInput);
                }
                this.textBuilder_.setMessage(textInput);
            }
            this.inputCase_ = 2;
            return this;
        }

        public Builder clearText() {
            if (this.textBuilder_ == null) {
                if (this.inputCase_ == 2) {
                    this.inputCase_ = 0;
                    this.input_ = null;
                    onChanged();
                }
            } else {
                if (this.inputCase_ == 2) {
                    this.inputCase_ = 0;
                    this.input_ = null;
                }
                this.textBuilder_.clear();
            }
            return this;
        }

        public TextInput.Builder getTextBuilder() {
            return getTextFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public TextInputOrBuilder getTextOrBuilder() {
            SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> singleFieldBuilderV3;
            if (this.inputCase_ == 2 && (singleFieldBuilderV3 = this.textBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.inputCase_ == 2) {
                return (TextInput) this.input_;
            }
            return TextInput.getDefaultInstance();
        }

        private SingleFieldBuilderV3<TextInput, TextInput.Builder, TextInputOrBuilder> getTextFieldBuilder() {
            if (this.textBuilder_ == null) {
                if (this.inputCase_ != 2) {
                    this.input_ = TextInput.getDefaultInstance();
                }
                this.textBuilder_ = new SingleFieldBuilderV3<>((TextInput) this.input_, getParentForChildren(), isClean());
                this.input_ = null;
            }
            this.inputCase_ = 2;
            onChanged();
            return this.textBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public boolean hasEvent() {
            return this.inputCase_ == 3;
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public EventInput getEvent() {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.inputCase_ == 3) {
                    return (EventInput) this.input_;
                }
                return EventInput.getDefaultInstance();
            }
            if (this.inputCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return EventInput.getDefaultInstance();
        }

        public Builder setEvent(EventInput eventInput) {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(eventInput);
            } else {
                if (eventInput == null) {
                    throw new NullPointerException();
                }
                this.input_ = eventInput;
                onChanged();
            }
            this.inputCase_ = 3;
            return this;
        }

        public Builder setEvent(EventInput.Builder builder) {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.input_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.inputCase_ = 3;
            return this;
        }

        public Builder mergeEvent(EventInput eventInput) {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3 = this.eventBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.inputCase_ == 3 && this.input_ != EventInput.getDefaultInstance()) {
                    this.input_ = EventInput.newBuilder((EventInput) this.input_).mergeFrom(eventInput).buildPartial();
                } else {
                    this.input_ = eventInput;
                }
                onChanged();
            } else {
                if (this.inputCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(eventInput);
                }
                this.eventBuilder_.setMessage(eventInput);
            }
            this.inputCase_ = 3;
            return this;
        }

        public Builder clearEvent() {
            if (this.eventBuilder_ == null) {
                if (this.inputCase_ == 3) {
                    this.inputCase_ = 0;
                    this.input_ = null;
                    onChanged();
                }
            } else {
                if (this.inputCase_ == 3) {
                    this.inputCase_ = 0;
                    this.input_ = null;
                }
                this.eventBuilder_.clear();
            }
            return this;
        }

        public EventInput.Builder getEventBuilder() {
            return getEventFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.QueryInputOrBuilder
        public EventInputOrBuilder getEventOrBuilder() {
            SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> singleFieldBuilderV3;
            if (this.inputCase_ == 3 && (singleFieldBuilderV3 = this.eventBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.inputCase_ == 3) {
                return (EventInput) this.input_;
            }
            return EventInput.getDefaultInstance();
        }

        private SingleFieldBuilderV3<EventInput, EventInput.Builder, EventInputOrBuilder> getEventFieldBuilder() {
            if (this.eventBuilder_ == null) {
                if (this.inputCase_ != 3) {
                    this.input_ = EventInput.getDefaultInstance();
                }
                this.eventBuilder_ = new SingleFieldBuilderV3<>((EventInput) this.input_, getParentForChildren(), isClean());
                this.input_ = null;
            }
            this.inputCase_ = 3;
            onChanged();
            return this.eventBuilder_;
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
    /* renamed from: com.google.cloud.dialogflow.v2.QueryInput$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C22762 {
        static final /* synthetic */ int[] $SwitchMap$com$google$cloud$dialogflow$v2$QueryInput$InputCase = new int[InputCase.values().length];

        static {
            try {
                $SwitchMap$com$google$cloud$dialogflow$v2$QueryInput$InputCase[InputCase.AUDIO_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$cloud$dialogflow$v2$QueryInput$InputCase[InputCase.TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$cloud$dialogflow$v2$QueryInput$InputCase[InputCase.EVENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$cloud$dialogflow$v2$QueryInput$InputCase[InputCase.INPUT_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static QueryInput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QueryInput> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<QueryInput> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public QueryInput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
