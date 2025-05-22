package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.SessionEntityType;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
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
/* loaded from: classes3.dex */
public final class UpdateSessionEntityTypeRequest extends GeneratedMessageV3 implements UpdateSessionEntityTypeRequestOrBuilder {
    private static final UpdateSessionEntityTypeRequest DEFAULT_INSTANCE = new UpdateSessionEntityTypeRequest();
    private static final Parser<UpdateSessionEntityTypeRequest> PARSER = new AbstractParser<UpdateSessionEntityTypeRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequest.1
        @Override // com.google.protobuf.Parser
        public UpdateSessionEntityTypeRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpdateSessionEntityTypeRequest(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SESSION_ENTITY_TYPE_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private SessionEntityType sessionEntityType_;
    private FieldMask updateMask_;

    private UpdateSessionEntityTypeRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpdateSessionEntityTypeRequest() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new UpdateSessionEntityTypeRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateSessionEntityTypeRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            SessionEntityType.Builder builder = this.sessionEntityType_ != null ? this.sessionEntityType_.toBuilder() : null;
                            this.sessionEntityType_ = (SessionEntityType) codedInputStream.readMessage(SessionEntityType.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.sessionEntityType_);
                                this.sessionEntityType_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            FieldMask.Builder builder2 = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                            this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.updateMask_);
                                this.updateMask_ = builder2.buildPartial();
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
        return SessionEntityTypeProto.f1834x3e696007;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionEntityTypeProto.f1835xa0578f85.ensureFieldAccessorsInitialized(UpdateSessionEntityTypeRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
    public boolean hasSessionEntityType() {
        return this.sessionEntityType_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
    public SessionEntityType getSessionEntityType() {
        SessionEntityType sessionEntityType = this.sessionEntityType_;
        return sessionEntityType == null ? SessionEntityType.getDefaultInstance() : sessionEntityType;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
    public SessionEntityTypeOrBuilder getSessionEntityTypeOrBuilder() {
        return getSessionEntityType();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
    public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
        return getUpdateMask();
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
        if (this.sessionEntityType_ != null) {
            codedOutputStream.writeMessage(1, getSessionEntityType());
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(2, getUpdateMask());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.sessionEntityType_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getSessionEntityType()) : 0;
        if (this.updateMask_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getUpdateMask());
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
        if (!(obj instanceof UpdateSessionEntityTypeRequest)) {
            return super.equals(obj);
        }
        UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest = (UpdateSessionEntityTypeRequest) obj;
        if (hasSessionEntityType() != updateSessionEntityTypeRequest.hasSessionEntityType()) {
            return false;
        }
        if ((!hasSessionEntityType() || getSessionEntityType().equals(updateSessionEntityTypeRequest.getSessionEntityType())) && hasUpdateMask() == updateSessionEntityTypeRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(updateSessionEntityTypeRequest.getUpdateMask())) && this.unknownFields.equals(updateSessionEntityTypeRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasSessionEntityType()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSessionEntityType().hashCode();
        }
        if (hasUpdateMask()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getUpdateMask().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static UpdateSessionEntityTypeRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateSessionEntityTypeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateSessionEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpdateSessionEntityTypeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateSessionEntityTypeRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpdateSessionEntityTypeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateSessionEntityTypeRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateSessionEntityTypeRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateSessionEntityTypeRequestOrBuilder {
        private SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> sessionEntityTypeBuilder_;
        private SessionEntityType sessionEntityType_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionEntityTypeProto.f1834x3e696007;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionEntityTypeProto.f1835xa0578f85.ensureFieldAccessorsInitialized(UpdateSessionEntityTypeRequest.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpdateSessionEntityTypeRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.sessionEntityTypeBuilder_ == null) {
                this.sessionEntityType_ = null;
            } else {
                this.sessionEntityType_ = null;
                this.sessionEntityTypeBuilder_ = null;
            }
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionEntityTypeProto.f1834x3e696007;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateSessionEntityTypeRequest getDefaultInstanceForType() {
            return UpdateSessionEntityTypeRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateSessionEntityTypeRequest build() {
            UpdateSessionEntityTypeRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateSessionEntityTypeRequest buildPartial() {
            UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest = new UpdateSessionEntityTypeRequest(this);
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                updateSessionEntityTypeRequest.sessionEntityType_ = this.sessionEntityType_;
            } else {
                updateSessionEntityTypeRequest.sessionEntityType_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                updateSessionEntityTypeRequest.updateMask_ = this.updateMask_;
            } else {
                updateSessionEntityTypeRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return updateSessionEntityTypeRequest;
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
            if (message instanceof UpdateSessionEntityTypeRequest) {
                return mergeFrom((UpdateSessionEntityTypeRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest) {
            if (updateSessionEntityTypeRequest == UpdateSessionEntityTypeRequest.getDefaultInstance()) {
                return this;
            }
            if (updateSessionEntityTypeRequest.hasSessionEntityType()) {
                mergeSessionEntityType(updateSessionEntityTypeRequest.getSessionEntityType());
            }
            if (updateSessionEntityTypeRequest.hasUpdateMask()) {
                mergeUpdateMask(updateSessionEntityTypeRequest.getUpdateMask());
            }
            mergeUnknownFields(updateSessionEntityTypeRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest = null;
            try {
                try {
                    UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest2 = (UpdateSessionEntityTypeRequest) UpdateSessionEntityTypeRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (updateSessionEntityTypeRequest2 != null) {
                        mergeFrom(updateSessionEntityTypeRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    UpdateSessionEntityTypeRequest updateSessionEntityTypeRequest3 = (UpdateSessionEntityTypeRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        updateSessionEntityTypeRequest = updateSessionEntityTypeRequest3;
                        if (updateSessionEntityTypeRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (updateSessionEntityTypeRequest != null) {
                    mergeFrom(updateSessionEntityTypeRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
        public boolean hasSessionEntityType() {
            return (this.sessionEntityTypeBuilder_ == null && this.sessionEntityType_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
        public SessionEntityType getSessionEntityType() {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                SessionEntityType sessionEntityType = this.sessionEntityType_;
                return sessionEntityType == null ? SessionEntityType.getDefaultInstance() : sessionEntityType;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setSessionEntityType(SessionEntityType sessionEntityType) {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sessionEntityType);
            } else {
                if (sessionEntityType == null) {
                    throw new NullPointerException();
                }
                this.sessionEntityType_ = sessionEntityType;
                onChanged();
            }
            return this;
        }

        public Builder setSessionEntityType(SessionEntityType.Builder builder) {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sessionEntityType_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSessionEntityType(SessionEntityType sessionEntityType) {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 == null) {
                SessionEntityType sessionEntityType2 = this.sessionEntityType_;
                if (sessionEntityType2 != null) {
                    this.sessionEntityType_ = SessionEntityType.newBuilder(sessionEntityType2).mergeFrom(sessionEntityType).buildPartial();
                } else {
                    this.sessionEntityType_ = sessionEntityType;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sessionEntityType);
            }
            return this;
        }

        public Builder clearSessionEntityType() {
            if (this.sessionEntityTypeBuilder_ == null) {
                this.sessionEntityType_ = null;
                onChanged();
            } else {
                this.sessionEntityType_ = null;
                this.sessionEntityTypeBuilder_ = null;
            }
            return this;
        }

        public SessionEntityType.Builder getSessionEntityTypeBuilder() {
            onChanged();
            return getSessionEntityTypeFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
        public SessionEntityTypeOrBuilder getSessionEntityTypeOrBuilder() {
            SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> singleFieldBuilderV3 = this.sessionEntityTypeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SessionEntityType sessionEntityType = this.sessionEntityType_;
            return sessionEntityType == null ? SessionEntityType.getDefaultInstance() : sessionEntityType;
        }

        private SingleFieldBuilderV3<SessionEntityType, SessionEntityType.Builder, SessionEntityTypeOrBuilder> getSessionEntityTypeFieldBuilder() {
            if (this.sessionEntityTypeBuilder_ == null) {
                this.sessionEntityTypeBuilder_ = new SingleFieldBuilderV3<>(getSessionEntityType(), getParentForChildren(), isClean());
                this.sessionEntityType_ = null;
            }
            return this.sessionEntityTypeBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
        public FieldMask getUpdateMask() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask = this.updateMask_;
                return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setUpdateMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(fieldMask);
            } else {
                if (fieldMask == null) {
                    throw new NullPointerException();
                }
                this.updateMask_ = fieldMask;
                onChanged();
            }
            return this;
        }

        public Builder setUpdateMask(FieldMask.Builder builder) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.updateMask_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUpdateMask(FieldMask fieldMask) {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 == null) {
                FieldMask fieldMask2 = this.updateMask_;
                if (fieldMask2 != null) {
                    this.updateMask_ = FieldMask.newBuilder(fieldMask2).mergeFrom(fieldMask).buildPartial();
                } else {
                    this.updateMask_ = fieldMask;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fieldMask);
            }
            return this;
        }

        public Builder clearUpdateMask() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
                onChanged();
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            return this;
        }

        public FieldMask.Builder getUpdateMaskBuilder() {
            onChanged();
            return getUpdateMaskFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateSessionEntityTypeRequestOrBuilder
        public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV3 = this.updateMaskBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            FieldMask fieldMask = this.updateMask_;
            return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
        }

        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> getUpdateMaskFieldBuilder() {
            if (this.updateMaskBuilder_ == null) {
                this.updateMaskBuilder_ = new SingleFieldBuilderV3<>(getUpdateMask(), getParentForChildren(), isClean());
                this.updateMask_ = null;
            }
            return this.updateMaskBuilder_;
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

    public static UpdateSessionEntityTypeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateSessionEntityTypeRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<UpdateSessionEntityTypeRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public UpdateSessionEntityTypeRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
