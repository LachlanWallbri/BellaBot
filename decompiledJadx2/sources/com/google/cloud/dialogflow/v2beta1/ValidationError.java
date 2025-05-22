package com.google.cloud.dialogflow.v2beta1;

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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ValidationError extends GeneratedMessageV3 implements ValidationErrorOrBuilder {
    public static final int ENTRIES_FIELD_NUMBER = 3;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 4;
    public static final int SEVERITY_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private LazyStringList entries_;
    private volatile Object errorMessage_;
    private byte memoizedIsInitialized;
    private int severity_;
    private static final ValidationError DEFAULT_INSTANCE = new ValidationError();
    private static final Parser<ValidationError> PARSER = new AbstractParser<ValidationError>() { // from class: com.google.cloud.dialogflow.v2beta1.ValidationError.1
        @Override // com.google.protobuf.Parser
        public ValidationError parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ValidationError(codedInputStream, extensionRegistryLite);
        }
    };

    private ValidationError(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ValidationError() {
        this.memoizedIsInitialized = (byte) -1;
        this.severity_ = 0;
        this.entries_ = LazyStringArrayList.EMPTY;
        this.errorMessage_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ValidationError();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ValidationError(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 8) {
                            this.severity_ = codedInputStream.readEnum();
                        } else if (readTag == 26) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.entries_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.entries_.add(readStringRequireUtf8);
                        } else if (readTag == 34) {
                            this.errorMessage_ = codedInputStream.readStringRequireUtf8();
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
                    this.entries_ = this.entries_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ValidationResultProto.f1868xfb63ac41;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ValidationResultProto.f1869xabc701bf.ensureFieldAccessorsInitialized(ValidationError.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum Severity implements ProtocolMessageEnum {
        SEVERITY_UNSPECIFIED(0),
        INFO(1),
        WARNING(2),
        ERROR(3),
        CRITICAL(4),
        UNRECOGNIZED(-1);

        public static final int CRITICAL_VALUE = 4;
        public static final int ERROR_VALUE = 3;
        public static final int INFO_VALUE = 1;
        public static final int SEVERITY_UNSPECIFIED_VALUE = 0;
        public static final int WARNING_VALUE = 2;
        private final int value;
        private static final Internal.EnumLiteMap<Severity> internalValueMap = new Internal.EnumLiteMap<Severity>() { // from class: com.google.cloud.dialogflow.v2beta1.ValidationError.Severity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Severity findValueByNumber(int i) {
                return Severity.forNumber(i);
            }
        };
        private static final Severity[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static Severity valueOf(int i) {
            return forNumber(i);
        }

        public static Severity forNumber(int i) {
            if (i == 0) {
                return SEVERITY_UNSPECIFIED;
            }
            if (i == 1) {
                return INFO;
            }
            if (i == 2) {
                return WARNING;
            }
            if (i == 3) {
                return ERROR;
            }
            if (i != 4) {
                return null;
            }
            return CRITICAL;
        }

        public static Internal.EnumLiteMap<Severity> internalGetValueMap() {
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
            return ValidationError.getDescriptor().getEnumTypes().get(0);
        }

        public static Severity valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        Severity(int i) {
            this.value = i;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public int getSeverityValue() {
        return this.severity_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public Severity getSeverity() {
        Severity valueOf = Severity.valueOf(this.severity_);
        return valueOf == null ? Severity.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public ProtocolStringList getEntriesList() {
        return this.entries_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public int getEntriesCount() {
        return this.entries_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public String getEntries(int i) {
        return (String) this.entries_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public ByteString getEntriesBytes(int i) {
        return this.entries_.getByteString(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public String getErrorMessage() {
        Object obj = this.errorMessage_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.errorMessage_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
    public ByteString getErrorMessageBytes() {
        Object obj = this.errorMessage_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.errorMessage_ = copyFromUtf8;
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
        if (this.severity_ != Severity.SEVERITY_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(1, this.severity_);
        }
        for (int i = 0; i < this.entries_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.entries_.getRaw(i));
        }
        if (!getErrorMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.errorMessage_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = this.severity_ != Severity.SEVERITY_UNSPECIFIED.getNumber() ? CodedOutputStream.computeEnumSize(1, this.severity_) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.entries_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.entries_.getRaw(i3));
        }
        int size = computeEnumSize + i2 + (getEntriesList().size() * 1);
        if (!getErrorMessageBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(4, this.errorMessage_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValidationError)) {
            return super.equals(obj);
        }
        ValidationError validationError = (ValidationError) obj;
        return this.severity_ == validationError.severity_ && getEntriesList().equals(validationError.getEntriesList()) && getErrorMessage().equals(validationError.getErrorMessage()) && this.unknownFields.equals(validationError.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.severity_;
        if (getEntriesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getEntriesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 4) * 53) + getErrorMessage().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ValidationError parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ValidationError parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ValidationError parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ValidationError parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ValidationError parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ValidationError parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ValidationError parseFrom(InputStream inputStream) throws IOException {
        return (ValidationError) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ValidationError parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValidationError) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ValidationError parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ValidationError) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ValidationError parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValidationError) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ValidationError parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ValidationError) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ValidationError parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ValidationError) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ValidationError validationError) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(validationError);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValidationErrorOrBuilder {
        private int bitField0_;
        private LazyStringList entries_;
        private Object errorMessage_;
        private int severity_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ValidationResultProto.f1868xfb63ac41;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ValidationResultProto.f1869xabc701bf.ensureFieldAccessorsInitialized(ValidationError.class, Builder.class);
        }

        private Builder() {
            this.severity_ = 0;
            this.entries_ = LazyStringArrayList.EMPTY;
            this.errorMessage_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.severity_ = 0;
            this.entries_ = LazyStringArrayList.EMPTY;
            this.errorMessage_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ValidationError.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.severity_ = 0;
            this.entries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.errorMessage_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ValidationResultProto.f1868xfb63ac41;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ValidationError getDefaultInstanceForType() {
            return ValidationError.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ValidationError build() {
            ValidationError buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ValidationError buildPartial() {
            ValidationError validationError = new ValidationError(this);
            int i = this.bitField0_;
            validationError.severity_ = this.severity_;
            if ((this.bitField0_ & 1) != 0) {
                this.entries_ = this.entries_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            validationError.entries_ = this.entries_;
            validationError.errorMessage_ = this.errorMessage_;
            onBuilt();
            return validationError;
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
            if (message instanceof ValidationError) {
                return mergeFrom((ValidationError) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ValidationError validationError) {
            if (validationError == ValidationError.getDefaultInstance()) {
                return this;
            }
            if (validationError.severity_ != 0) {
                setSeverityValue(validationError.getSeverityValue());
            }
            if (!validationError.entries_.isEmpty()) {
                if (this.entries_.isEmpty()) {
                    this.entries_ = validationError.entries_;
                    this.bitField0_ &= -2;
                } else {
                    ensureEntriesIsMutable();
                    this.entries_.addAll(validationError.entries_);
                }
                onChanged();
            }
            if (!validationError.getErrorMessage().isEmpty()) {
                this.errorMessage_ = validationError.errorMessage_;
                onChanged();
            }
            mergeUnknownFields(validationError.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ValidationError validationError = null;
            try {
                try {
                    ValidationError validationError2 = (ValidationError) ValidationError.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (validationError2 != null) {
                        mergeFrom(validationError2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ValidationError validationError3 = (ValidationError) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        validationError = validationError3;
                        if (validationError != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (validationError != null) {
                    mergeFrom(validationError);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public int getSeverityValue() {
            return this.severity_;
        }

        public Builder setSeverityValue(int i) {
            this.severity_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public Severity getSeverity() {
            Severity valueOf = Severity.valueOf(this.severity_);
            return valueOf == null ? Severity.UNRECOGNIZED : valueOf;
        }

        public Builder setSeverity(Severity severity) {
            if (severity == null) {
                throw new NullPointerException();
            }
            this.severity_ = severity.getNumber();
            onChanged();
            return this;
        }

        public Builder clearSeverity() {
            this.severity_ = 0;
            onChanged();
            return this;
        }

        private void ensureEntriesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entries_ = new LazyStringArrayList(this.entries_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public ProtocolStringList getEntriesList() {
            return this.entries_.getUnmodifiableView();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public int getEntriesCount() {
            return this.entries_.size();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public String getEntries(int i) {
            return (String) this.entries_.get(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public ByteString getEntriesBytes(int i) {
            return this.entries_.getByteString(i);
        }

        public Builder setEntries(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureEntriesIsMutable();
            this.entries_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addEntries(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureEntriesIsMutable();
            this.entries_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllEntries(Iterable<String> iterable) {
            ensureEntriesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.entries_);
            onChanged();
            return this;
        }

        public Builder clearEntries() {
            this.entries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addEntriesBytes(ByteString byteString) {
            if (byteString != null) {
                ValidationError.checkByteStringIsUtf8(byteString);
                ensureEntriesIsMutable();
                this.entries_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public String getErrorMessage() {
            Object obj = this.errorMessage_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.errorMessage_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ValidationErrorOrBuilder
        public ByteString getErrorMessageBytes() {
            Object obj = this.errorMessage_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.errorMessage_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setErrorMessage(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.errorMessage_ = str;
            onChanged();
            return this;
        }

        public Builder clearErrorMessage() {
            this.errorMessage_ = ValidationError.getDefaultInstance().getErrorMessage();
            onChanged();
            return this;
        }

        public Builder setErrorMessageBytes(ByteString byteString) {
            if (byteString != null) {
                ValidationError.checkByteStringIsUtf8(byteString);
                this.errorMessage_ = byteString;
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

    public static ValidationError getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ValidationError> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ValidationError> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ValidationError getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
