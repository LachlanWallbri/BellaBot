package com.google.cloud.dialogflow.p049v2;

import com.google.cloud.dialogflow.p049v2.ValidationError;
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
public final class ValidationResult extends GeneratedMessageV3 implements ValidationResultOrBuilder {
    private static final ValidationResult DEFAULT_INSTANCE = new ValidationResult();
    private static final Parser<ValidationResult> PARSER = new AbstractParser<ValidationResult>() { // from class: com.google.cloud.dialogflow.v2.ValidationResult.1
        @Override // com.google.protobuf.Parser
        public ValidationResult parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ValidationResult(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALIDATION_ERRORS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<ValidationError> validationErrors_;

    private ValidationResult(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ValidationResult() {
        this.memoizedIsInitialized = (byte) -1;
        this.validationErrors_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ValidationResult();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ValidationResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.validationErrors_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.validationErrors_.add(codedInputStream.readMessage(ValidationError.parser(), extensionRegistryLite));
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
                    this.validationErrors_ = Collections.unmodifiableList(this.validationErrors_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ValidationResultProto.f1570x4604803d;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ValidationResultProto.f1571xb14219bb.ensureFieldAccessorsInitialized(ValidationResult.class, Builder.class);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
    public List<ValidationError> getValidationErrorsList() {
        return this.validationErrors_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
    public List<? extends ValidationErrorOrBuilder> getValidationErrorsOrBuilderList() {
        return this.validationErrors_;
    }

    @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
    public int getValidationErrorsCount() {
        return this.validationErrors_.size();
    }

    @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
    public ValidationError getValidationErrors(int i) {
        return this.validationErrors_.get(i);
    }

    @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
    public ValidationErrorOrBuilder getValidationErrorsOrBuilder(int i) {
        return this.validationErrors_.get(i);
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
        for (int i = 0; i < this.validationErrors_.size(); i++) {
            codedOutputStream.writeMessage(1, this.validationErrors_.get(i));
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
        for (int i3 = 0; i3 < this.validationErrors_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.validationErrors_.get(i3));
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
        if (!(obj instanceof ValidationResult)) {
            return super.equals(obj);
        }
        ValidationResult validationResult = (ValidationResult) obj;
        return getValidationErrorsList().equals(validationResult.getValidationErrorsList()) && this.unknownFields.equals(validationResult.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getValidationErrorsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getValidationErrorsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ValidationResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ValidationResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ValidationResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ValidationResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ValidationResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ValidationResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ValidationResult parseFrom(InputStream inputStream) throws IOException {
        return (ValidationResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ValidationResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValidationResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ValidationResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ValidationResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ValidationResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValidationResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ValidationResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ValidationResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ValidationResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValidationResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ValidationResult validationResult) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(validationResult);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValidationResultOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> validationErrorsBuilder_;
        private List<ValidationError> validationErrors_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ValidationResultProto.f1570x4604803d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ValidationResultProto.f1571xb14219bb.ensureFieldAccessorsInitialized(ValidationResult.class, Builder.class);
        }

        private Builder() {
            this.validationErrors_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.validationErrors_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ValidationResult.alwaysUseFieldBuilders) {
                getValidationErrorsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.validationErrors_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ValidationResultProto.f1570x4604803d;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ValidationResult getDefaultInstanceForType() {
            return ValidationResult.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ValidationResult build() {
            ValidationResult buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ValidationResult buildPartial() {
            ValidationResult validationResult = new ValidationResult(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.validationErrors_ = Collections.unmodifiableList(this.validationErrors_);
                    this.bitField0_ &= -2;
                }
                validationResult.validationErrors_ = this.validationErrors_;
            } else {
                validationResult.validationErrors_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return validationResult;
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
            if (message instanceof ValidationResult) {
                return mergeFrom((ValidationResult) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ValidationResult validationResult) {
            if (validationResult == ValidationResult.getDefaultInstance()) {
                return this;
            }
            if (this.validationErrorsBuilder_ == null) {
                if (!validationResult.validationErrors_.isEmpty()) {
                    if (this.validationErrors_.isEmpty()) {
                        this.validationErrors_ = validationResult.validationErrors_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureValidationErrorsIsMutable();
                        this.validationErrors_.addAll(validationResult.validationErrors_);
                    }
                    onChanged();
                }
            } else if (!validationResult.validationErrors_.isEmpty()) {
                if (!this.validationErrorsBuilder_.isEmpty()) {
                    this.validationErrorsBuilder_.addAllMessages(validationResult.validationErrors_);
                } else {
                    this.validationErrorsBuilder_.dispose();
                    this.validationErrorsBuilder_ = null;
                    this.validationErrors_ = validationResult.validationErrors_;
                    this.bitField0_ &= -2;
                    this.validationErrorsBuilder_ = ValidationResult.alwaysUseFieldBuilders ? getValidationErrorsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(validationResult.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ValidationResult validationResult = null;
            try {
                try {
                    ValidationResult validationResult2 = (ValidationResult) ValidationResult.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (validationResult2 != null) {
                        mergeFrom(validationResult2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ValidationResult validationResult3 = (ValidationResult) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        validationResult = validationResult3;
                        if (validationResult != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (validationResult != null) {
                    mergeFrom(validationResult);
                }
                throw th;
            }
        }

        private void ensureValidationErrorsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.validationErrors_ = new ArrayList(this.validationErrors_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
        public List<ValidationError> getValidationErrorsList() {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.validationErrors_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
        public int getValidationErrorsCount() {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.validationErrors_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
        public ValidationError getValidationErrors(int i) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.validationErrors_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setValidationErrors(int i, ValidationError validationError) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, validationError);
            } else {
                if (validationError == null) {
                    throw new NullPointerException();
                }
                ensureValidationErrorsIsMutable();
                this.validationErrors_.set(i, validationError);
                onChanged();
            }
            return this;
        }

        public Builder setValidationErrors(int i, ValidationError.Builder builder) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValidationErrorsIsMutable();
                this.validationErrors_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addValidationErrors(ValidationError validationError) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(validationError);
            } else {
                if (validationError == null) {
                    throw new NullPointerException();
                }
                ensureValidationErrorsIsMutable();
                this.validationErrors_.add(validationError);
                onChanged();
            }
            return this;
        }

        public Builder addValidationErrors(int i, ValidationError validationError) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, validationError);
            } else {
                if (validationError == null) {
                    throw new NullPointerException();
                }
                ensureValidationErrorsIsMutable();
                this.validationErrors_.add(i, validationError);
                onChanged();
            }
            return this;
        }

        public Builder addValidationErrors(ValidationError.Builder builder) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValidationErrorsIsMutable();
                this.validationErrors_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addValidationErrors(int i, ValidationError.Builder builder) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValidationErrorsIsMutable();
                this.validationErrors_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllValidationErrors(Iterable<? extends ValidationError> iterable) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValidationErrorsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.validationErrors_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearValidationErrors() {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.validationErrors_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeValidationErrors(int i) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValidationErrorsIsMutable();
                this.validationErrors_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public ValidationError.Builder getValidationErrorsBuilder(int i) {
            return getValidationErrorsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
        public ValidationErrorOrBuilder getValidationErrorsOrBuilder(int i) {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.validationErrors_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.p049v2.ValidationResultOrBuilder
        public List<? extends ValidationErrorOrBuilder> getValidationErrorsOrBuilderList() {
            RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> repeatedFieldBuilderV3 = this.validationErrorsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.validationErrors_);
        }

        public ValidationError.Builder addValidationErrorsBuilder() {
            return getValidationErrorsFieldBuilder().addBuilder(ValidationError.getDefaultInstance());
        }

        public ValidationError.Builder addValidationErrorsBuilder(int i) {
            return getValidationErrorsFieldBuilder().addBuilder(i, ValidationError.getDefaultInstance());
        }

        public List<ValidationError.Builder> getValidationErrorsBuilderList() {
            return getValidationErrorsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ValidationError, ValidationError.Builder, ValidationErrorOrBuilder> getValidationErrorsFieldBuilder() {
            if (this.validationErrorsBuilder_ == null) {
                this.validationErrorsBuilder_ = new RepeatedFieldBuilderV3<>(this.validationErrors_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.validationErrors_ = null;
            }
            return this.validationErrorsBuilder_;
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

    public static ValidationResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ValidationResult> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ValidationResult> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ValidationResult getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
