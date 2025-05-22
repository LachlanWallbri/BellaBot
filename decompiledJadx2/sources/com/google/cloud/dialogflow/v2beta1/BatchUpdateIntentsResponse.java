package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.Intent;
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
public final class BatchUpdateIntentsResponse extends GeneratedMessageV3 implements BatchUpdateIntentsResponseOrBuilder {
    public static final int INTENTS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Intent> intents_;
    private byte memoizedIsInitialized;
    private static final BatchUpdateIntentsResponse DEFAULT_INSTANCE = new BatchUpdateIntentsResponse();
    private static final Parser<BatchUpdateIntentsResponse> PARSER = new AbstractParser<BatchUpdateIntentsResponse>() { // from class: com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponse.1
        @Override // com.google.protobuf.Parser
        public BatchUpdateIntentsResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchUpdateIntentsResponse(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchUpdateIntentsResponse(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchUpdateIntentsResponse() {
        this.memoizedIsInitialized = (byte) -1;
        this.intents_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchUpdateIntentsResponse();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BatchUpdateIntentsResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.intents_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.intents_.add(codedInputStream.readMessage(Intent.parser(), extensionRegistryLite));
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
                    this.intents_ = Collections.unmodifiableList(this.intents_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return IntentProto.f1695xd9e43d77;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IntentProto.f1696x1395fcf5.ensureFieldAccessorsInitialized(BatchUpdateIntentsResponse.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
    public List<Intent> getIntentsList() {
        return this.intents_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
    public List<? extends IntentOrBuilder> getIntentsOrBuilderList() {
        return this.intents_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
    public int getIntentsCount() {
        return this.intents_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
    public Intent getIntents(int i) {
        return this.intents_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
    public IntentOrBuilder getIntentsOrBuilder(int i) {
        return this.intents_.get(i);
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
        for (int i = 0; i < this.intents_.size(); i++) {
            codedOutputStream.writeMessage(1, this.intents_.get(i));
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
        for (int i3 = 0; i3 < this.intents_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.intents_.get(i3));
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
        if (!(obj instanceof BatchUpdateIntentsResponse)) {
            return super.equals(obj);
        }
        BatchUpdateIntentsResponse batchUpdateIntentsResponse = (BatchUpdateIntentsResponse) obj;
        return getIntentsList().equals(batchUpdateIntentsResponse.getIntentsList()) && this.unknownFields.equals(batchUpdateIntentsResponse.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getIntentsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getIntentsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static BatchUpdateIntentsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchUpdateIntentsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchUpdateIntentsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchUpdateIntentsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchUpdateIntentsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchUpdateIntentsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchUpdateIntentsResponse parseFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateIntentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateIntentsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateIntentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateIntentsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateIntentsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateIntentsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateIntentsResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateIntentsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchUpdateIntentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchUpdateIntentsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateIntentsResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchUpdateIntentsResponse batchUpdateIntentsResponse) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchUpdateIntentsResponse);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchUpdateIntentsResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> intentsBuilder_;
        private List<Intent> intents_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return IntentProto.f1695xd9e43d77;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IntentProto.f1696x1395fcf5.ensureFieldAccessorsInitialized(BatchUpdateIntentsResponse.class, Builder.class);
        }

        private Builder() {
            this.intents_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.intents_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (BatchUpdateIntentsResponse.alwaysUseFieldBuilders) {
                getIntentsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.intents_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return IntentProto.f1695xd9e43d77;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchUpdateIntentsResponse getDefaultInstanceForType() {
            return BatchUpdateIntentsResponse.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateIntentsResponse build() {
            BatchUpdateIntentsResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateIntentsResponse buildPartial() {
            BatchUpdateIntentsResponse batchUpdateIntentsResponse = new BatchUpdateIntentsResponse(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.intents_ = Collections.unmodifiableList(this.intents_);
                    this.bitField0_ &= -2;
                }
                batchUpdateIntentsResponse.intents_ = this.intents_;
            } else {
                batchUpdateIntentsResponse.intents_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return batchUpdateIntentsResponse;
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
            if (message instanceof BatchUpdateIntentsResponse) {
                return mergeFrom((BatchUpdateIntentsResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchUpdateIntentsResponse batchUpdateIntentsResponse) {
            if (batchUpdateIntentsResponse == BatchUpdateIntentsResponse.getDefaultInstance()) {
                return this;
            }
            if (this.intentsBuilder_ == null) {
                if (!batchUpdateIntentsResponse.intents_.isEmpty()) {
                    if (this.intents_.isEmpty()) {
                        this.intents_ = batchUpdateIntentsResponse.intents_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureIntentsIsMutable();
                        this.intents_.addAll(batchUpdateIntentsResponse.intents_);
                    }
                    onChanged();
                }
            } else if (!batchUpdateIntentsResponse.intents_.isEmpty()) {
                if (!this.intentsBuilder_.isEmpty()) {
                    this.intentsBuilder_.addAllMessages(batchUpdateIntentsResponse.intents_);
                } else {
                    this.intentsBuilder_.dispose();
                    this.intentsBuilder_ = null;
                    this.intents_ = batchUpdateIntentsResponse.intents_;
                    this.bitField0_ &= -2;
                    this.intentsBuilder_ = BatchUpdateIntentsResponse.alwaysUseFieldBuilders ? getIntentsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(batchUpdateIntentsResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchUpdateIntentsResponse batchUpdateIntentsResponse = null;
            try {
                try {
                    BatchUpdateIntentsResponse batchUpdateIntentsResponse2 = (BatchUpdateIntentsResponse) BatchUpdateIntentsResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchUpdateIntentsResponse2 != null) {
                        mergeFrom(batchUpdateIntentsResponse2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchUpdateIntentsResponse batchUpdateIntentsResponse3 = (BatchUpdateIntentsResponse) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchUpdateIntentsResponse = batchUpdateIntentsResponse3;
                        if (batchUpdateIntentsResponse != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchUpdateIntentsResponse != null) {
                    mergeFrom(batchUpdateIntentsResponse);
                }
                throw th;
            }
        }

        private void ensureIntentsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.intents_ = new ArrayList(this.intents_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
        public List<Intent> getIntentsList() {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.intents_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
        public int getIntentsCount() {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.intents_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
        public Intent getIntents(int i) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.intents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setIntents(int i, Intent intent) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, intent);
            } else {
                if (intent == null) {
                    throw new NullPointerException();
                }
                ensureIntentsIsMutable();
                this.intents_.set(i, intent);
                onChanged();
            }
            return this;
        }

        public Builder setIntents(int i, Intent.Builder builder) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIntentsIsMutable();
                this.intents_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addIntents(Intent intent) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(intent);
            } else {
                if (intent == null) {
                    throw new NullPointerException();
                }
                ensureIntentsIsMutable();
                this.intents_.add(intent);
                onChanged();
            }
            return this;
        }

        public Builder addIntents(int i, Intent intent) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, intent);
            } else {
                if (intent == null) {
                    throw new NullPointerException();
                }
                ensureIntentsIsMutable();
                this.intents_.add(i, intent);
                onChanged();
            }
            return this;
        }

        public Builder addIntents(Intent.Builder builder) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIntentsIsMutable();
                this.intents_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addIntents(int i, Intent.Builder builder) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIntentsIsMutable();
                this.intents_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllIntents(Iterable<? extends Intent> iterable) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIntentsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.intents_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearIntents() {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.intents_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeIntents(int i) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIntentsIsMutable();
                this.intents_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Intent.Builder getIntentsBuilder(int i) {
            return getIntentsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
        public IntentOrBuilder getIntentsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.intents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.BatchUpdateIntentsResponseOrBuilder
        public List<? extends IntentOrBuilder> getIntentsOrBuilderList() {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.intents_);
        }

        public Intent.Builder addIntentsBuilder() {
            return getIntentsFieldBuilder().addBuilder(Intent.getDefaultInstance());
        }

        public Intent.Builder addIntentsBuilder(int i) {
            return getIntentsFieldBuilder().addBuilder(i, Intent.getDefaultInstance());
        }

        public List<Intent.Builder> getIntentsBuilderList() {
            return getIntentsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> getIntentsFieldBuilder() {
            if (this.intentsBuilder_ == null) {
                this.intentsBuilder_ = new RepeatedFieldBuilderV3<>(this.intents_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.intents_ = null;
            }
            return this.intentsBuilder_;
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

    public static BatchUpdateIntentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchUpdateIntentsResponse> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchUpdateIntentsResponse> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchUpdateIntentsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
