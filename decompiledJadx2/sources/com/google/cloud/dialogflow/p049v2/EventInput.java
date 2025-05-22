package com.google.cloud.dialogflow.p049v2;

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
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class EventInput extends GeneratedMessageV3 implements EventInputOrBuilder {
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PARAMETERS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private Struct parameters_;
    private static final EventInput DEFAULT_INSTANCE = new EventInput();
    private static final Parser<EventInput> PARSER = new AbstractParser<EventInput>() { // from class: com.google.cloud.dialogflow.v2.EventInput.1
        @Override // com.google.protobuf.Parser
        public EventInput parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EventInput(codedInputStream, extensionRegistryLite);
        }
    };

    private EventInput(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private EventInput() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.languageCode_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new EventInput();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private EventInput(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            Struct.Builder builder = this.parameters_ != null ? this.parameters_.toBuilder() : null;
                            this.parameters_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.parameters_);
                                this.parameters_ = builder.buildPartial();
                            }
                        } else if (readTag == 26) {
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
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
        return SessionProto.internal_static_google_cloud_dialogflow_v2_EventInput_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProto.f1550xe1721fe1.ensureFieldAccessorsInitialized(EventInput.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public boolean hasParameters() {
        return this.parameters_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public Struct getParameters() {
        Struct struct = this.parameters_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public StructOrBuilder getParametersOrBuilder() {
        return getParameters();
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.parameters_ != null) {
            codedOutputStream.writeMessage(2, getParameters());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getNameBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        if (this.parameters_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getParameters());
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventInput)) {
            return super.equals(obj);
        }
        EventInput eventInput = (EventInput) obj;
        if (getName().equals(eventInput.getName()) && hasParameters() == eventInput.hasParameters()) {
            return (!hasParameters() || getParameters().equals(eventInput.getParameters())) && getLanguageCode().equals(eventInput.getLanguageCode()) && this.unknownFields.equals(eventInput.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasParameters()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getParameters().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 3) * 53) + getLanguageCode().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static EventInput parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static EventInput parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static EventInput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static EventInput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static EventInput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static EventInput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static EventInput parseFrom(InputStream inputStream) throws IOException {
        return (EventInput) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EventInput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventInput) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventInput parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EventInput) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EventInput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventInput) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EventInput parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EventInput) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static EventInput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EventInput) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EventInput eventInput) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(eventInput);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EventInputOrBuilder {
        private Object languageCode_;
        private Object name_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> parametersBuilder_;
        private Struct parameters_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProto.internal_static_google_cloud_dialogflow_v2_EventInput_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProto.f1550xe1721fe1.ensureFieldAccessorsInitialized(EventInput.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.languageCode_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = EventInput.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            if (this.parametersBuilder_ == null) {
                this.parameters_ = null;
            } else {
                this.parameters_ = null;
                this.parametersBuilder_ = null;
            }
            this.languageCode_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProto.internal_static_google_cloud_dialogflow_v2_EventInput_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EventInput getDefaultInstanceForType() {
            return EventInput.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public EventInput build() {
            EventInput buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public EventInput buildPartial() {
            EventInput eventInput = new EventInput(this);
            eventInput.name_ = this.name_;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                eventInput.parameters_ = this.parameters_;
            } else {
                eventInput.parameters_ = singleFieldBuilderV3.build();
            }
            eventInput.languageCode_ = this.languageCode_;
            onBuilt();
            return eventInput;
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
            if (message instanceof EventInput) {
                return mergeFrom((EventInput) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(EventInput eventInput) {
            if (eventInput == EventInput.getDefaultInstance()) {
                return this;
            }
            if (!eventInput.getName().isEmpty()) {
                this.name_ = eventInput.name_;
                onChanged();
            }
            if (eventInput.hasParameters()) {
                mergeParameters(eventInput.getParameters());
            }
            if (!eventInput.getLanguageCode().isEmpty()) {
                this.languageCode_ = eventInput.languageCode_;
                onChanged();
            }
            mergeUnknownFields(eventInput.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            EventInput eventInput = null;
            try {
                try {
                    EventInput eventInput2 = (EventInput) EventInput.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (eventInput2 != null) {
                        mergeFrom(eventInput2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    EventInput eventInput3 = (EventInput) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        eventInput = eventInput3;
                        if (eventInput != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (eventInput != null) {
                    mergeFrom(eventInput);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = EventInput.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                EventInput.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public boolean hasParameters() {
            return (this.parametersBuilder_ == null && this.parameters_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public Struct getParameters() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct = this.parameters_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setParameters(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.parameters_ = struct;
                onChanged();
            }
            return this;
        }

        public Builder setParameters(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.parameters_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeParameters(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.parameters_;
                if (struct2 != null) {
                    this.parameters_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.parameters_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearParameters() {
            if (this.parametersBuilder_ == null) {
                this.parameters_ = null;
                onChanged();
            } else {
                this.parameters_ = null;
                this.parametersBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getParametersBuilder() {
            onChanged();
            return getParametersFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public StructOrBuilder getParametersOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.parametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.parameters_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getParametersFieldBuilder() {
            if (this.parametersBuilder_ == null) {
                this.parametersBuilder_ = new SingleFieldBuilderV3<>(getParameters(), getParentForChildren(), isClean());
                this.parameters_ = null;
            }
            return this.parametersBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.EventInputOrBuilder
        public ByteString getLanguageCodeBytes() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.languageCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLanguageCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.languageCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearLanguageCode() {
            this.languageCode_ = EventInput.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                EventInput.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
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

    public static EventInput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EventInput> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<EventInput> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public EventInput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
