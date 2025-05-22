package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.IntentBatch;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FieldMaskOrBuilder;
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
public final class BatchUpdateIntentsRequest extends GeneratedMessageV3 implements BatchUpdateIntentsRequestOrBuilder {
    public static final int INTENT_BATCH_INLINE_FIELD_NUMBER = 3;
    public static final int INTENT_BATCH_URI_FIELD_NUMBER = 2;
    public static final int INTENT_VIEW_FIELD_NUMBER = 6;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 4;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int intentBatchCase_;
    private Object intentBatch_;
    private int intentView_;
    private volatile Object languageCode_;
    private byte memoizedIsInitialized;
    private volatile Object parent_;
    private FieldMask updateMask_;
    private static final BatchUpdateIntentsRequest DEFAULT_INSTANCE = new BatchUpdateIntentsRequest();
    private static final Parser<BatchUpdateIntentsRequest> PARSER = new AbstractParser<BatchUpdateIntentsRequest>() { // from class: com.google.cloud.dialogflow.v2.BatchUpdateIntentsRequest.1
        @Override // com.google.protobuf.Parser
        public BatchUpdateIntentsRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BatchUpdateIntentsRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private BatchUpdateIntentsRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.intentBatchCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private BatchUpdateIntentsRequest() {
        this.intentBatchCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.parent_ = "";
        this.languageCode_ = "";
        this.intentView_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BatchUpdateIntentsRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BatchUpdateIntentsRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.parent_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag != 18) {
                            if (readTag == 26) {
                                IntentBatch.Builder builder = this.intentBatchCase_ == 3 ? ((IntentBatch) this.intentBatch_).toBuilder() : null;
                                this.intentBatch_ = codedInputStream.readMessage(IntentBatch.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((IntentBatch) this.intentBatch_);
                                    this.intentBatch_ = builder.buildPartial();
                                }
                                this.intentBatchCase_ = 3;
                            } else if (readTag == 34) {
                                this.languageCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                FieldMask.Builder builder2 = this.updateMask_ != null ? this.updateMask_.toBuilder() : null;
                                this.updateMask_ = (FieldMask) codedInputStream.readMessage(FieldMask.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.updateMask_);
                                    this.updateMask_ = builder2.buildPartial();
                                }
                            } else if (readTag == 48) {
                                this.intentView_ = codedInputStream.readEnum();
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        } else {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.intentBatchCase_ = 2;
                            this.intentBatch_ = readStringRequireUtf8;
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
        return IntentProto.f1448x7e21b42e;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return IntentProto.f1449xb2e8ccac.ensureFieldAccessorsInitialized(BatchUpdateIntentsRequest.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum IntentBatchCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        INTENT_BATCH_URI(2),
        INTENT_BATCH_INLINE(3),
        INTENTBATCH_NOT_SET(0);

        private final int value;

        IntentBatchCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static IntentBatchCase valueOf(int i) {
            return forNumber(i);
        }

        public static IntentBatchCase forNumber(int i) {
            if (i == 0) {
                return INTENTBATCH_NOT_SET;
            }
            if (i == 2) {
                return INTENT_BATCH_URI;
            }
            if (i != 3) {
                return null;
            }
            return INTENT_BATCH_INLINE;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public IntentBatchCase getIntentBatchCase() {
        return IntentBatchCase.forNumber(this.intentBatchCase_);
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public String getParent() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.parent_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public ByteString getParentBytes() {
        Object obj = this.parent_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parent_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public String getIntentBatchUri() {
        String str = this.intentBatchCase_ == 2 ? this.intentBatch_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.intentBatchCase_ == 2) {
            this.intentBatch_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public ByteString getIntentBatchUriBytes() {
        String str = this.intentBatchCase_ == 2 ? this.intentBatch_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.intentBatchCase_ == 2) {
                this.intentBatch_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public boolean hasIntentBatchInline() {
        return this.intentBatchCase_ == 3;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public IntentBatch getIntentBatchInline() {
        if (this.intentBatchCase_ == 3) {
            return (IntentBatch) this.intentBatch_;
        }
        return IntentBatch.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public IntentBatchOrBuilder getIntentBatchInlineOrBuilder() {
        if (this.intentBatchCase_ == 3) {
            return (IntentBatch) this.intentBatch_;
        }
        return IntentBatch.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public FieldMask getUpdateMask() {
        FieldMask fieldMask = this.updateMask_;
        return fieldMask == null ? FieldMask.getDefaultInstance() : fieldMask;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public FieldMaskOrBuilder getUpdateMaskOrBuilder() {
        return getUpdateMask();
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public int getIntentViewValue() {
        return this.intentView_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
    public IntentView getIntentView() {
        IntentView valueOf = IntentView.valueOf(this.intentView_);
        return valueOf == null ? IntentView.UNRECOGNIZED : valueOf;
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
        if (this.intentBatchCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.intentBatch_);
        }
        if (this.intentBatchCase_ == 3) {
            codedOutputStream.writeMessage(3, (IntentBatch) this.intentBatch_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            codedOutputStream.writeMessage(5, getUpdateMask());
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(6, this.intentView_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getParentBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.parent_);
        if (this.intentBatchCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.intentBatch_);
        }
        if (this.intentBatchCase_ == 3) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (IntentBatch) this.intentBatch_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.languageCode_);
        }
        if (this.updateMask_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, getUpdateMask());
        }
        if (this.intentView_ != IntentView.INTENT_VIEW_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(6, this.intentView_);
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
        if (!(obj instanceof BatchUpdateIntentsRequest)) {
            return super.equals(obj);
        }
        BatchUpdateIntentsRequest batchUpdateIntentsRequest = (BatchUpdateIntentsRequest) obj;
        if (!getParent().equals(batchUpdateIntentsRequest.getParent()) || !getLanguageCode().equals(batchUpdateIntentsRequest.getLanguageCode()) || hasUpdateMask() != batchUpdateIntentsRequest.hasUpdateMask()) {
            return false;
        }
        if ((hasUpdateMask() && !getUpdateMask().equals(batchUpdateIntentsRequest.getUpdateMask())) || this.intentView_ != batchUpdateIntentsRequest.intentView_ || !getIntentBatchCase().equals(batchUpdateIntentsRequest.getIntentBatchCase())) {
            return false;
        }
        int i = this.intentBatchCase_;
        if (i == 2) {
            if (!getIntentBatchUri().equals(batchUpdateIntentsRequest.getIntentBatchUri())) {
                return false;
            }
        } else if (i == 3 && !getIntentBatchInline().equals(batchUpdateIntentsRequest.getIntentBatchInline())) {
            return false;
        }
        return this.unknownFields.equals(batchUpdateIntentsRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getParent().hashCode()) * 37) + 4) * 53) + getLanguageCode().hashCode();
        if (hasUpdateMask()) {
            hashCode2 = (((hashCode2 * 37) + 5) * 53) + getUpdateMask().hashCode();
        }
        int i2 = (((hashCode2 * 37) + 6) * 53) + this.intentView_;
        int i3 = this.intentBatchCase_;
        if (i3 == 2) {
            i = ((i2 * 37) + 2) * 53;
            hashCode = getIntentBatchUri().hashCode();
        } else {
            if (i3 == 3) {
                i = ((i2 * 37) + 3) * 53;
                hashCode = getIntentBatchInline().hashCode();
            }
            int hashCode3 = (i2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }
        i2 = i + hashCode;
        int hashCode32 = (i2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode32;
        return hashCode32;
    }

    public static BatchUpdateIntentsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BatchUpdateIntentsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BatchUpdateIntentsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BatchUpdateIntentsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BatchUpdateIntentsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BatchUpdateIntentsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BatchUpdateIntentsRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateIntentsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateIntentsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchUpdateIntentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BatchUpdateIntentsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateIntentsRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BatchUpdateIntentsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchUpdateIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BatchUpdateIntentsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchUpdateIntentsRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatchUpdateIntentsRequest batchUpdateIntentsRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(batchUpdateIntentsRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BatchUpdateIntentsRequestOrBuilder {
        private int intentBatchCase_;
        private SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> intentBatchInlineBuilder_;
        private Object intentBatch_;
        private int intentView_;
        private Object languageCode_;
        private Object parent_;
        private SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> updateMaskBuilder_;
        private FieldMask updateMask_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return IntentProto.f1448x7e21b42e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return IntentProto.f1449xb2e8ccac.ensureFieldAccessorsInitialized(BatchUpdateIntentsRequest.class, Builder.class);
        }

        private Builder() {
            this.intentBatchCase_ = 0;
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.intentBatchCase_ = 0;
            this.parent_ = "";
            this.languageCode_ = "";
            this.intentView_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BatchUpdateIntentsRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.parent_ = "";
            this.languageCode_ = "";
            if (this.updateMaskBuilder_ == null) {
                this.updateMask_ = null;
            } else {
                this.updateMask_ = null;
                this.updateMaskBuilder_ = null;
            }
            this.intentView_ = 0;
            this.intentBatchCase_ = 0;
            this.intentBatch_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return IntentProto.f1448x7e21b42e;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BatchUpdateIntentsRequest getDefaultInstanceForType() {
            return BatchUpdateIntentsRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateIntentsRequest build() {
            BatchUpdateIntentsRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BatchUpdateIntentsRequest buildPartial() {
            BatchUpdateIntentsRequest batchUpdateIntentsRequest = new BatchUpdateIntentsRequest(this);
            batchUpdateIntentsRequest.parent_ = this.parent_;
            if (this.intentBatchCase_ == 2) {
                batchUpdateIntentsRequest.intentBatch_ = this.intentBatch_;
            }
            if (this.intentBatchCase_ == 3) {
                SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> singleFieldBuilderV3 = this.intentBatchInlineBuilder_;
                if (singleFieldBuilderV3 == null) {
                    batchUpdateIntentsRequest.intentBatch_ = this.intentBatch_;
                } else {
                    batchUpdateIntentsRequest.intentBatch_ = singleFieldBuilderV3.build();
                }
            }
            batchUpdateIntentsRequest.languageCode_ = this.languageCode_;
            SingleFieldBuilderV3<FieldMask, FieldMask.Builder, FieldMaskOrBuilder> singleFieldBuilderV32 = this.updateMaskBuilder_;
            if (singleFieldBuilderV32 == null) {
                batchUpdateIntentsRequest.updateMask_ = this.updateMask_;
            } else {
                batchUpdateIntentsRequest.updateMask_ = singleFieldBuilderV32.build();
            }
            batchUpdateIntentsRequest.intentView_ = this.intentView_;
            batchUpdateIntentsRequest.intentBatchCase_ = this.intentBatchCase_;
            onBuilt();
            return batchUpdateIntentsRequest;
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
            if (message instanceof BatchUpdateIntentsRequest) {
                return mergeFrom((BatchUpdateIntentsRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BatchUpdateIntentsRequest batchUpdateIntentsRequest) {
            if (batchUpdateIntentsRequest == BatchUpdateIntentsRequest.getDefaultInstance()) {
                return this;
            }
            if (!batchUpdateIntentsRequest.getParent().isEmpty()) {
                this.parent_ = batchUpdateIntentsRequest.parent_;
                onChanged();
            }
            if (!batchUpdateIntentsRequest.getLanguageCode().isEmpty()) {
                this.languageCode_ = batchUpdateIntentsRequest.languageCode_;
                onChanged();
            }
            if (batchUpdateIntentsRequest.hasUpdateMask()) {
                mergeUpdateMask(batchUpdateIntentsRequest.getUpdateMask());
            }
            if (batchUpdateIntentsRequest.intentView_ != 0) {
                setIntentViewValue(batchUpdateIntentsRequest.getIntentViewValue());
            }
            int i = C21762.f1397xa0237cf4[batchUpdateIntentsRequest.getIntentBatchCase().ordinal()];
            if (i == 1) {
                this.intentBatchCase_ = 2;
                this.intentBatch_ = batchUpdateIntentsRequest.intentBatch_;
                onChanged();
            } else if (i == 2) {
                mergeIntentBatchInline(batchUpdateIntentsRequest.getIntentBatchInline());
            }
            mergeUnknownFields(batchUpdateIntentsRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BatchUpdateIntentsRequest batchUpdateIntentsRequest = null;
            try {
                try {
                    BatchUpdateIntentsRequest batchUpdateIntentsRequest2 = (BatchUpdateIntentsRequest) BatchUpdateIntentsRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (batchUpdateIntentsRequest2 != null) {
                        mergeFrom(batchUpdateIntentsRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BatchUpdateIntentsRequest batchUpdateIntentsRequest3 = (BatchUpdateIntentsRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        batchUpdateIntentsRequest = batchUpdateIntentsRequest3;
                        if (batchUpdateIntentsRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (batchUpdateIntentsRequest != null) {
                    mergeFrom(batchUpdateIntentsRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public IntentBatchCase getIntentBatchCase() {
            return IntentBatchCase.forNumber(this.intentBatchCase_);
        }

        public Builder clearIntentBatch() {
            this.intentBatchCase_ = 0;
            this.intentBatch_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public String getParent() {
            Object obj = this.parent_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.parent_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
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
            this.parent_ = BatchUpdateIntentsRequest.getDefaultInstance().getParent();
            onChanged();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateIntentsRequest.checkByteStringIsUtf8(byteString);
                this.parent_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public String getIntentBatchUri() {
            String str = this.intentBatchCase_ == 2 ? this.intentBatch_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.intentBatchCase_ == 2) {
                    this.intentBatch_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public ByteString getIntentBatchUriBytes() {
            String str = this.intentBatchCase_ == 2 ? this.intentBatch_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.intentBatchCase_ == 2) {
                    this.intentBatch_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setIntentBatchUri(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.intentBatchCase_ = 2;
            this.intentBatch_ = str;
            onChanged();
            return this;
        }

        public Builder clearIntentBatchUri() {
            if (this.intentBatchCase_ == 2) {
                this.intentBatchCase_ = 0;
                this.intentBatch_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setIntentBatchUriBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateIntentsRequest.checkByteStringIsUtf8(byteString);
                this.intentBatchCase_ = 2;
                this.intentBatch_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public boolean hasIntentBatchInline() {
            return this.intentBatchCase_ == 3;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public IntentBatch getIntentBatchInline() {
            SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> singleFieldBuilderV3 = this.intentBatchInlineBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.intentBatchCase_ == 3) {
                    return (IntentBatch) this.intentBatch_;
                }
                return IntentBatch.getDefaultInstance();
            }
            if (this.intentBatchCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return IntentBatch.getDefaultInstance();
        }

        public Builder setIntentBatchInline(IntentBatch intentBatch) {
            SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> singleFieldBuilderV3 = this.intentBatchInlineBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(intentBatch);
            } else {
                if (intentBatch == null) {
                    throw new NullPointerException();
                }
                this.intentBatch_ = intentBatch;
                onChanged();
            }
            this.intentBatchCase_ = 3;
            return this;
        }

        public Builder setIntentBatchInline(IntentBatch.Builder builder) {
            SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> singleFieldBuilderV3 = this.intentBatchInlineBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.intentBatch_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.intentBatchCase_ = 3;
            return this;
        }

        public Builder mergeIntentBatchInline(IntentBatch intentBatch) {
            SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> singleFieldBuilderV3 = this.intentBatchInlineBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.intentBatchCase_ == 3 && this.intentBatch_ != IntentBatch.getDefaultInstance()) {
                    this.intentBatch_ = IntentBatch.newBuilder((IntentBatch) this.intentBatch_).mergeFrom(intentBatch).buildPartial();
                } else {
                    this.intentBatch_ = intentBatch;
                }
                onChanged();
            } else {
                if (this.intentBatchCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(intentBatch);
                }
                this.intentBatchInlineBuilder_.setMessage(intentBatch);
            }
            this.intentBatchCase_ = 3;
            return this;
        }

        public Builder clearIntentBatchInline() {
            if (this.intentBatchInlineBuilder_ == null) {
                if (this.intentBatchCase_ == 3) {
                    this.intentBatchCase_ = 0;
                    this.intentBatch_ = null;
                    onChanged();
                }
            } else {
                if (this.intentBatchCase_ == 3) {
                    this.intentBatchCase_ = 0;
                    this.intentBatch_ = null;
                }
                this.intentBatchInlineBuilder_.clear();
            }
            return this;
        }

        public IntentBatch.Builder getIntentBatchInlineBuilder() {
            return getIntentBatchInlineFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public IntentBatchOrBuilder getIntentBatchInlineOrBuilder() {
            SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> singleFieldBuilderV3;
            if (this.intentBatchCase_ == 3 && (singleFieldBuilderV3 = this.intentBatchInlineBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.intentBatchCase_ == 3) {
                return (IntentBatch) this.intentBatch_;
            }
            return IntentBatch.getDefaultInstance();
        }

        private SingleFieldBuilderV3<IntentBatch, IntentBatch.Builder, IntentBatchOrBuilder> getIntentBatchInlineFieldBuilder() {
            if (this.intentBatchInlineBuilder_ == null) {
                if (this.intentBatchCase_ != 3) {
                    this.intentBatch_ = IntentBatch.getDefaultInstance();
                }
                this.intentBatchInlineBuilder_ = new SingleFieldBuilderV3<>((IntentBatch) this.intentBatch_, getParentForChildren(), isClean());
                this.intentBatch_ = null;
            }
            this.intentBatchCase_ = 3;
            onChanged();
            return this.intentBatchInlineBuilder_;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
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
            this.languageCode_ = BatchUpdateIntentsRequest.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                BatchUpdateIntentsRequest.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public boolean hasUpdateMask() {
            return (this.updateMaskBuilder_ == null && this.updateMask_ == null) ? false : true;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
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

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public int getIntentViewValue() {
            return this.intentView_;
        }

        public Builder setIntentViewValue(int i) {
            this.intentView_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.p049v2.BatchUpdateIntentsRequestOrBuilder
        public IntentView getIntentView() {
            IntentView valueOf = IntentView.valueOf(this.intentView_);
            return valueOf == null ? IntentView.UNRECOGNIZED : valueOf;
        }

        public Builder setIntentView(IntentView intentView) {
            if (intentView == null) {
                throw new NullPointerException();
            }
            this.intentView_ = intentView.getNumber();
            onChanged();
            return this;
        }

        public Builder clearIntentView() {
            this.intentView_ = 0;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.cloud.dialogflow.v2.BatchUpdateIntentsRequest$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C21762 {

        /* renamed from: $SwitchMap$com$google$cloud$dialogflow$v2$BatchUpdateIntentsRequest$IntentBatchCase */
        static final /* synthetic */ int[] f1397xa0237cf4 = new int[IntentBatchCase.values().length];

        static {
            try {
                f1397xa0237cf4[IntentBatchCase.INTENT_BATCH_URI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1397xa0237cf4[IntentBatchCase.INTENT_BATCH_INLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1397xa0237cf4[IntentBatchCase.INTENTBATCH_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static BatchUpdateIntentsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchUpdateIntentsRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BatchUpdateIntentsRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BatchUpdateIntentsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
