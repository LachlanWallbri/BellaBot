package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.KnowledgeBase;
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
public final class UpdateKnowledgeBaseRequest extends GeneratedMessageV3 implements UpdateKnowledgeBaseRequestOrBuilder {
    public static final int KNOWLEDGE_BASE_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private KnowledgeBase knowledgeBase_;
    private byte memoizedIsInitialized;
    private FieldMask updateMask_;
    private static final UpdateKnowledgeBaseRequest DEFAULT_INSTANCE = new UpdateKnowledgeBaseRequest();
    private static final Parser<UpdateKnowledgeBaseRequest> PARSER = new AbstractParser<UpdateKnowledgeBaseRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequest.1
        @Override // com.google.protobuf.Parser
        public UpdateKnowledgeBaseRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UpdateKnowledgeBaseRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private UpdateKnowledgeBaseRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private UpdateKnowledgeBaseRequest() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new UpdateKnowledgeBaseRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private UpdateKnowledgeBaseRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            KnowledgeBase.Builder builder = this.knowledgeBase_ != null ? this.knowledgeBase_.toBuilder() : null;
                            this.knowledgeBase_ = (KnowledgeBase) codedInputStream.readMessage(KnowledgeBase.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.knowledgeBase_);
                                this.knowledgeBase_ = builder.buildPartial();
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
        return KnowledgeBaseProto.f1817x39d72703;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return KnowledgeBaseProto.f1818xbffc9a81.ensureFieldAccessorsInitialized(UpdateKnowledgeBaseRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
    public boolean hasKnowledgeBase() {
        return this.knowledgeBase_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
    public KnowledgeBase getKnowledgeBase() {
        KnowledgeBase knowledgeBase = this.knowledgeBase_;
        return knowledgeBase == null ? KnowledgeBase.getDefaultInstance() : knowledgeBase;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
    public KnowledgeBaseOrBuilder getKnowledgeBaseOrBuilder() {
        return getKnowledgeBase();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
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
        if (this.knowledgeBase_ != null) {
            codedOutputStream.writeMessage(1, getKnowledgeBase());
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
        int computeMessageSize = this.knowledgeBase_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getKnowledgeBase()) : 0;
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
        if (!(obj instanceof UpdateKnowledgeBaseRequest)) {
            return super.equals(obj);
        }
        UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest = (UpdateKnowledgeBaseRequest) obj;
        if (hasKnowledgeBase() != updateKnowledgeBaseRequest.hasKnowledgeBase()) {
            return false;
        }
        if ((!hasKnowledgeBase() || getKnowledgeBase().equals(updateKnowledgeBaseRequest.getKnowledgeBase())) && hasUpdateMask() == updateKnowledgeBaseRequest.hasUpdateMask()) {
            return (!hasUpdateMask() || getUpdateMask().equals(updateKnowledgeBaseRequest.getUpdateMask())) && this.unknownFields.equals(updateKnowledgeBaseRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasKnowledgeBase()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getKnowledgeBase().hashCode();
        }
        if (hasUpdateMask()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getUpdateMask().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static UpdateKnowledgeBaseRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateKnowledgeBaseRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateKnowledgeBaseRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UpdateKnowledgeBaseRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateKnowledgeBaseRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UpdateKnowledgeBaseRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateKnowledgeBaseRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateKnowledgeBaseRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateKnowledgeBaseRequestOrBuilder {
        private SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> knowledgeBaseBuilder_;
        private KnowledgeBase knowledgeBase_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return KnowledgeBaseProto.f1817x39d72703;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return KnowledgeBaseProto.f1818xbffc9a81.ensureFieldAccessorsInitialized(UpdateKnowledgeBaseRequest.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = UpdateKnowledgeBaseRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.knowledgeBaseBuilder_ == null) {
                this.knowledgeBase_ = null;
            } else {
                this.knowledgeBase_ = null;
                this.knowledgeBaseBuilder_ = null;
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
            return KnowledgeBaseProto.f1817x39d72703;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateKnowledgeBaseRequest getDefaultInstanceForType() {
            return UpdateKnowledgeBaseRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateKnowledgeBaseRequest build() {
            UpdateKnowledgeBaseRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public UpdateKnowledgeBaseRequest buildPartial() {
            UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest = new UpdateKnowledgeBaseRequest(this);
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                updateKnowledgeBaseRequest.knowledgeBase_ = this.knowledgeBase_;
            } else {
                updateKnowledgeBaseRequest.knowledgeBase_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                updateKnowledgeBaseRequest.updateMask_ = this.updateMask_;
            } else {
                updateKnowledgeBaseRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return updateKnowledgeBaseRequest;
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
            if (message instanceof UpdateKnowledgeBaseRequest) {
                return mergeFrom((UpdateKnowledgeBaseRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest) {
            if (updateKnowledgeBaseRequest == UpdateKnowledgeBaseRequest.getDefaultInstance()) {
                return this;
            }
            if (updateKnowledgeBaseRequest.hasKnowledgeBase()) {
                mergeKnowledgeBase(updateKnowledgeBaseRequest.getKnowledgeBase());
            }
            if (updateKnowledgeBaseRequest.hasUpdateMask()) {
                mergeUpdateMask(updateKnowledgeBaseRequest.getUpdateMask());
            }
            mergeUnknownFields(updateKnowledgeBaseRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest = null;
            try {
                try {
                    UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest2 = (UpdateKnowledgeBaseRequest) UpdateKnowledgeBaseRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (updateKnowledgeBaseRequest2 != null) {
                        mergeFrom(updateKnowledgeBaseRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    UpdateKnowledgeBaseRequest updateKnowledgeBaseRequest3 = (UpdateKnowledgeBaseRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        updateKnowledgeBaseRequest = updateKnowledgeBaseRequest3;
                        if (updateKnowledgeBaseRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (updateKnowledgeBaseRequest != null) {
                    mergeFrom(updateKnowledgeBaseRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
        public boolean hasKnowledgeBase() {
            return (this.knowledgeBaseBuilder_ == null && this.knowledgeBase_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
        public KnowledgeBase getKnowledgeBase() {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                KnowledgeBase knowledgeBase = this.knowledgeBase_;
                return knowledgeBase == null ? KnowledgeBase.getDefaultInstance() : knowledgeBase;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setKnowledgeBase(KnowledgeBase knowledgeBase) {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(knowledgeBase);
            } else {
                if (knowledgeBase == null) {
                    throw new NullPointerException();
                }
                this.knowledgeBase_ = knowledgeBase;
                onChanged();
            }
            return this;
        }

        public Builder setKnowledgeBase(KnowledgeBase.Builder builder) {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.knowledgeBase_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeKnowledgeBase(KnowledgeBase knowledgeBase) {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 == null) {
                KnowledgeBase knowledgeBase2 = this.knowledgeBase_;
                if (knowledgeBase2 != null) {
                    this.knowledgeBase_ = KnowledgeBase.newBuilder(knowledgeBase2).mergeFrom(knowledgeBase).buildPartial();
                } else {
                    this.knowledgeBase_ = knowledgeBase;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(knowledgeBase);
            }
            return this;
        }

        public Builder clearKnowledgeBase() {
            if (this.knowledgeBaseBuilder_ == null) {
                this.knowledgeBase_ = null;
                onChanged();
            } else {
                this.knowledgeBase_ = null;
                this.knowledgeBaseBuilder_ = null;
            }
            return this;
        }

        public KnowledgeBase.Builder getKnowledgeBaseBuilder() {
            onChanged();
            return getKnowledgeBaseFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
        public KnowledgeBaseOrBuilder getKnowledgeBaseOrBuilder() {
            SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> singleFieldBuilderV3 = this.knowledgeBaseBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            KnowledgeBase knowledgeBase = this.knowledgeBase_;
            return knowledgeBase == null ? KnowledgeBase.getDefaultInstance() : knowledgeBase;
        }

        private SingleFieldBuilderV3<KnowledgeBase, KnowledgeBase.Builder, KnowledgeBaseOrBuilder> getKnowledgeBaseFieldBuilder() {
            if (this.knowledgeBaseBuilder_ == null) {
                this.knowledgeBaseBuilder_ = new SingleFieldBuilderV3<>(getKnowledgeBase(), getParentForChildren(), isClean());
                this.knowledgeBase_ = null;
            }
            return this.knowledgeBaseBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.v2beta1.UpdateKnowledgeBaseRequestOrBuilder
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

    public static UpdateKnowledgeBaseRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateKnowledgeBaseRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<UpdateKnowledgeBaseRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public UpdateKnowledgeBaseRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
