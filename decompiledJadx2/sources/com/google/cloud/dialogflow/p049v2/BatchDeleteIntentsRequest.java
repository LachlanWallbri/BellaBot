package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.Intent;
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
/* loaded from: classes2.dex */
public final class BatchDeleteIntentsRequest extends GeneratedMessageV3 implements BatchDeleteIntentsRequestOrBuilder {
    public static final int INTENTS_FIELD_NUMBER = 2;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Intent> intents_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private static final BatchDeleteIntentsRequest DEFAULT_INSTANCE = new BatchDeleteIntentsRequest();
    private static final Parser<BatchDeleteIntentsRequest> PARSER = new AbstractParser<BatchDeleteIntentsRequest>() { // from class: com.google.cloud.dialogflow.v2.BatchDeleteIntentsRequest.1
        @Override // com.google.protobuf.Parser
        public BatchDeleteIntentsRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchDeleteIntentsRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchDeleteIntentsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchDeleteIntentsRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.intents_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchDeleteIntentsRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private BatchDeleteIntentsRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.parent_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            if (!(z2 & true)) {
                                this.intents_ = new ArrayList();
                                z2 |= true;
                            }
                            this.intents_.add(codedInputStream.readMessage(Intent.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.intents_ = Collections.unmodifiableList(this.intents_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return IntentProto.f1446x2104498c;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IntentProto.f1447x9987240a.ensureFieldAccessorsInitialized(BatchDeleteIntentsRequest.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
    public List<Intent> getIntentsList() {
        return this.intents_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
    public List<? extends IntentOrBuilder> getIntentsOrBuilderList() {
        return this.intents_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
    public int getIntentsCount() {
        return this.intents_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
    public Intent getIntents(int i) {
        return this.intents_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
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
        if (!getParentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.parent_);
        }
        for (int i = 0; i < this.intents_.size(); i++) {
            codedOutputStream.writeMessage(2, this.intents_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getParentBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.parent_) + 0 : 0;
        for (int i2 = 0; i2 < this.intents_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, this.intents_.get(i2));
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
        if (!(obj instanceof BatchDeleteIntentsRequest)) {
            return super.equals(obj);
        }
        BatchDeleteIntentsRequest batchDeleteIntentsRequest = (BatchDeleteIntentsRequest) obj;
        return getParent().equals(batchDeleteIntentsRequest.getParent()) && getIntentsList().equals(batchDeleteIntentsRequest.getIntentsList()) && this.unknownFields.equals(batchDeleteIntentsRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode();
        if (getIntentsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getIntentsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static BatchDeleteIntentsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchDeleteIntentsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchDeleteIntentsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchDeleteIntentsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchDeleteIntentsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchDeleteIntentsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchDeleteIntentsRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchDeleteIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchDeleteIntentsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchDeleteIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchDeleteIntentsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchDeleteIntentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchDeleteIntentsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchDeleteIntentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchDeleteIntentsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchDeleteIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchDeleteIntentsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchDeleteIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchDeleteIntentsRequest batchDeleteIntentsRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchDeleteIntentsRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchDeleteIntentsRequestOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> intentsBuilder_;
        private List<Intent> intents_;
        private Object parent_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return IntentProto.f1446x2104498c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IntentProto.f1447x9987240a.ensureFieldAccessorsInitialized(BatchDeleteIntentsRequest.class, Builder.class);
        }

        private Builder() {
            this.parent_ = "";
            this.intents_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.parent_ = "";
            this.intents_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (BatchDeleteIntentsRequest.alwaysUseFieldBuilders) {
                getIntentsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
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
            return IntentProto.f1446x2104498c;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchDeleteIntentsRequest getDefaultInstanceForType() {
            return BatchDeleteIntentsRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchDeleteIntentsRequest build() {
            BatchDeleteIntentsRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchDeleteIntentsRequest buildPartial() {
            BatchDeleteIntentsRequest batchDeleteIntentsRequest = new BatchDeleteIntentsRequest(this);
            int i = this.bitField0_;
            batchDeleteIntentsRequest.parent_ = this.parent_;
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                batchDeleteIntentsRequest.intents_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.intents_ = Collections.unmodifiableList(this.intents_);
                    this.bitField0_ &= -2;
                }
                batchDeleteIntentsRequest.intents_ = this.intents_;
            }
            onBuilt();
            return batchDeleteIntentsRequest;
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
            if (message instanceof BatchDeleteIntentsRequest) {
                return mergeFrom((BatchDeleteIntentsRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchDeleteIntentsRequest batchDeleteIntentsRequest) {
            if (batchDeleteIntentsRequest == BatchDeleteIntentsRequest.getDefaultInstance()) {
                return this;
            }
            if (!batchDeleteIntentsRequest.getParent().isEmpty()) {
                this.parent_ = batchDeleteIntentsRequest.parent_;
                onChanged();
            }
            if (this.intentsBuilder_ == null) {
                if (!batchDeleteIntentsRequest.intents_.isEmpty()) {
                    if (this.intents_.isEmpty()) {
                        this.intents_ = batchDeleteIntentsRequest.intents_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureIntentsIsMutable();
                        this.intents_.addAll(batchDeleteIntentsRequest.intents_);
                    }
                    onChanged();
                }
            } else if (!batchDeleteIntentsRequest.intents_.isEmpty()) {
                if (!this.intentsBuilder_.isEmpty()) {
                    this.intentsBuilder_.addAllMessages(batchDeleteIntentsRequest.intents_);
                } else {
                    this.intentsBuilder_.dispose();
                    this.intentsBuilder_ = null;
                    this.intents_ = batchDeleteIntentsRequest.intents_;
                    this.bitField0_ &= -2;
                    this.intentsBuilder_ = BatchDeleteIntentsRequest.alwaysUseFieldBuilders ? getIntentsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(batchDeleteIntentsRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchDeleteIntentsRequest batchDeleteIntentsRequest = null;
            try {
                try {
                    BatchDeleteIntentsRequest batchDeleteIntentsRequest2 = (BatchDeleteIntentsRequest) BatchDeleteIntentsRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchDeleteIntentsRequest2 != null) {
                        mergeFrom(batchDeleteIntentsRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchDeleteIntentsRequest batchDeleteIntentsRequest3 = (BatchDeleteIntentsRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchDeleteIntentsRequest = batchDeleteIntentsRequest3;
                        if (batchDeleteIntentsRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchDeleteIntentsRequest != null) {
                    mergeFrom(batchDeleteIntentsRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
        public ByteString getParentBytes() {
            Object obj = this.parent_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.parent_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setParent(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.parent_ = str;
            onChanged();
            return this;
        }

        public Builder clearParent() {
            this.parent_ = BatchDeleteIntentsRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                BatchDeleteIntentsRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureIntentsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.intents_ = new ArrayList(this.intents_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
        public List<Intent> getIntentsList() {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.intents_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
        public int getIntentsCount() {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.intents_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
        public IntentOrBuilder getIntentsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Intent, Intent.Builder, IntentOrBuilder> repeatedFieldBuilderV3 = this.intentsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.intents_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchDeleteIntentsRequestOrBuilder
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

    public static BatchDeleteIntentsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchDeleteIntentsRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchDeleteIntentsRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchDeleteIntentsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
