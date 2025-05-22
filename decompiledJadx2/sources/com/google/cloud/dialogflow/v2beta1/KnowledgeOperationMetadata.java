package com.google.cloud.dialogflow.v2beta1;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class KnowledgeOperationMetadata extends GeneratedMessageV3 implements KnowledgeOperationMetadataOrBuilder {
    private static final KnowledgeOperationMetadata DEFAULT_INSTANCE = new KnowledgeOperationMetadata();
    private static final Parser<KnowledgeOperationMetadata> PARSER = new AbstractParser<KnowledgeOperationMetadata>() { // from class: com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadata.1
        @Override // com.google.protobuf.Parser
        public KnowledgeOperationMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new KnowledgeOperationMetadata(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int STATE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int state_;

    private KnowledgeOperationMetadata(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private KnowledgeOperationMetadata() {
        this.memoizedIsInitialized = (byte) -1;
        this.state_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new KnowledgeOperationMetadata();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private KnowledgeOperationMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.state_ = codedInputStream.readEnum();
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
        return DocumentProto.f1642x18a88854;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1643x15841ad2.ensureFieldAccessorsInitialized(KnowledgeOperationMetadata.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum State implements ProtocolMessageEnum {
        STATE_UNSPECIFIED(0),
        PENDING(1),
        RUNNING(2),
        DONE(3),
        UNRECOGNIZED(-1);

        public static final int DONE_VALUE = 3;
        public static final int PENDING_VALUE = 1;
        public static final int RUNNING_VALUE = 2;
        public static final int STATE_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadata.State.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public State findValueByNumber(int i) {
                return State.forNumber(i);
            }
        };
        private static final State[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static State valueOf(int i) {
            return forNumber(i);
        }

        public static State forNumber(int i) {
            if (i == 0) {
                return STATE_UNSPECIFIED;
            }
            if (i == 1) {
                return PENDING;
            }
            if (i == 2) {
                return RUNNING;
            }
            if (i != 3) {
                return null;
            }
            return DONE;
        }

        public static Internal.EnumLiteMap<State> internalGetValueMap() {
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
            return KnowledgeOperationMetadata.getDescriptor().getEnumTypes().get(0);
        }

        public static State valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        State(int i) {
            this.value = i;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadataOrBuilder
    public int getStateValue() {
        return this.state_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadataOrBuilder
    public State getState() {
        State valueOf = State.valueOf(this.state_);
        return valueOf == null ? State.UNRECOGNIZED : valueOf;
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
        if (this.state_ != State.STATE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.state_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = (this.state_ != State.STATE_UNSPECIFIED.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.state_) : 0) + this.unknownFields.getSerializedSize();
        this.memoizedSize = computeEnumSize;
        return computeEnumSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KnowledgeOperationMetadata)) {
            return super.equals(obj);
        }
        KnowledgeOperationMetadata knowledgeOperationMetadata = (KnowledgeOperationMetadata) obj;
        return this.state_ == knowledgeOperationMetadata.state_ && this.unknownFields.equals(knowledgeOperationMetadata.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.state_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static KnowledgeOperationMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static KnowledgeOperationMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static KnowledgeOperationMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static KnowledgeOperationMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static KnowledgeOperationMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static KnowledgeOperationMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static KnowledgeOperationMetadata parseFrom(InputStream inputStream) throws IOException {
        return (KnowledgeOperationMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static KnowledgeOperationMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KnowledgeOperationMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static KnowledgeOperationMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KnowledgeOperationMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static KnowledgeOperationMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KnowledgeOperationMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static KnowledgeOperationMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KnowledgeOperationMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static KnowledgeOperationMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KnowledgeOperationMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KnowledgeOperationMetadata knowledgeOperationMetadata) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(knowledgeOperationMetadata);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements KnowledgeOperationMetadataOrBuilder {
        private int state_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1642x18a88854;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1643x15841ad2.ensureFieldAccessorsInitialized(KnowledgeOperationMetadata.class, Builder.class);
        }

        private Builder() {
            this.state_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.state_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = KnowledgeOperationMetadata.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.state_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentProto.f1642x18a88854;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public KnowledgeOperationMetadata getDefaultInstanceForType() {
            return KnowledgeOperationMetadata.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public KnowledgeOperationMetadata build() {
            KnowledgeOperationMetadata buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public KnowledgeOperationMetadata buildPartial() {
            KnowledgeOperationMetadata knowledgeOperationMetadata = new KnowledgeOperationMetadata(this);
            knowledgeOperationMetadata.state_ = this.state_;
            onBuilt();
            return knowledgeOperationMetadata;
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
            if (message instanceof KnowledgeOperationMetadata) {
                return mergeFrom((KnowledgeOperationMetadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(KnowledgeOperationMetadata knowledgeOperationMetadata) {
            if (knowledgeOperationMetadata == KnowledgeOperationMetadata.getDefaultInstance()) {
                return this;
            }
            if (knowledgeOperationMetadata.state_ != 0) {
                setStateValue(knowledgeOperationMetadata.getStateValue());
            }
            mergeUnknownFields(knowledgeOperationMetadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            KnowledgeOperationMetadata knowledgeOperationMetadata = null;
            try {
                try {
                    KnowledgeOperationMetadata knowledgeOperationMetadata2 = (KnowledgeOperationMetadata) KnowledgeOperationMetadata.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (knowledgeOperationMetadata2 != null) {
                        mergeFrom(knowledgeOperationMetadata2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    KnowledgeOperationMetadata knowledgeOperationMetadata3 = (KnowledgeOperationMetadata) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        knowledgeOperationMetadata = knowledgeOperationMetadata3;
                        if (knowledgeOperationMetadata != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (knowledgeOperationMetadata != null) {
                    mergeFrom(knowledgeOperationMetadata);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadataOrBuilder
        public int getStateValue() {
            return this.state_;
        }

        public Builder setStateValue(int i) {
            this.state_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.KnowledgeOperationMetadataOrBuilder
        public State getState() {
            State valueOf = State.valueOf(this.state_);
            return valueOf == null ? State.UNRECOGNIZED : valueOf;
        }

        public Builder setState(State state) {
            if (state == null) {
                throw new NullPointerException();
            }
            this.state_ = state.getNumber();
            onChanged();
            return this;
        }

        public Builder clearState() {
            this.state_ = 0;
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

    public static KnowledgeOperationMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KnowledgeOperationMetadata> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<KnowledgeOperationMetadata> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public KnowledgeOperationMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
