package com.google.api;

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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class BackendRule extends GeneratedMessageV3 implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    public static final int DISABLE_AUTH_FIELD_NUMBER = 8;
    public static final int JWT_AUDIENCE_FIELD_NUMBER = 7;
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    public static final int OPERATION_DEADLINE_FIELD_NUMBER = 5;
    public static final int PATH_TRANSLATION_FIELD_NUMBER = 6;
    public static final int PROTOCOL_FIELD_NUMBER = 9;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object address_;
    private int authenticationCase_;
    private Object authentication_;
    private double deadline_;
    private byte memoizedIsInitialized;
    private double minDeadline_;
    private double operationDeadline_;
    private int pathTranslation_;
    private volatile Object protocol_;
    private volatile Object selector_;
    private static final BackendRule DEFAULT_INSTANCE = new BackendRule();
    private static final Parser<BackendRule> PARSER = new AbstractParser<BackendRule>() { // from class: com.google.api.BackendRule.1
        @Override // com.google.protobuf.Parser
        public BackendRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BackendRule(codedInputStream, extensionRegistryLite);
        }
    };

    private BackendRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.authenticationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private BackendRule() {
        this.authenticationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.address_ = "";
        this.pathTranslation_ = 0;
        this.protocol_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BackendRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BackendRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.selector_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.address_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 25) {
                            this.deadline_ = codedInputStream.readDouble();
                        } else if (readTag == 33) {
                            this.minDeadline_ = codedInputStream.readDouble();
                        } else if (readTag == 41) {
                            this.operationDeadline_ = codedInputStream.readDouble();
                        } else if (readTag == 48) {
                            this.pathTranslation_ = codedInputStream.readEnum();
                        } else if (readTag == 58) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.authenticationCase_ = 7;
                            this.authentication_ = readStringRequireUtf8;
                        } else if (readTag == 64) {
                            this.authenticationCase_ = 8;
                            this.authentication_ = Boolean.valueOf(codedInputStream.readBool());
                        } else if (readTag == 74) {
                            this.protocol_ = codedInputStream.readStringRequireUtf8();
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
        return BackendProto.internal_static_google_api_BackendRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum PathTranslation implements ProtocolMessageEnum {
        PATH_TRANSLATION_UNSPECIFIED(0),
        CONSTANT_ADDRESS(1),
        APPEND_PATH_TO_ADDRESS(2),
        UNRECOGNIZED(-1);

        public static final int APPEND_PATH_TO_ADDRESS_VALUE = 2;
        public static final int CONSTANT_ADDRESS_VALUE = 1;
        public static final int PATH_TRANSLATION_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<PathTranslation> internalValueMap = new Internal.EnumLiteMap<PathTranslation>() { // from class: com.google.api.BackendRule.PathTranslation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PathTranslation findValueByNumber(int i) {
                return PathTranslation.forNumber(i);
            }
        };
        private static final PathTranslation[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static PathTranslation valueOf(int i) {
            return forNumber(i);
        }

        public static PathTranslation forNumber(int i) {
            if (i == 0) {
                return PATH_TRANSLATION_UNSPECIFIED;
            }
            if (i == 1) {
                return CONSTANT_ADDRESS;
            }
            if (i != 2) {
                return null;
            }
            return APPEND_PATH_TO_ADDRESS;
        }

        public static Internal.EnumLiteMap<PathTranslation> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return BackendRule.getDescriptor().getEnumTypes().get(0);
        }

        public static PathTranslation valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        PathTranslation(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum AuthenticationCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        JWT_AUDIENCE(7),
        DISABLE_AUTH(8),
        AUTHENTICATION_NOT_SET(0);

        private final int value;

        AuthenticationCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static AuthenticationCase valueOf(int i) {
            return forNumber(i);
        }

        public static AuthenticationCase forNumber(int i) {
            if (i == 0) {
                return AUTHENTICATION_NOT_SET;
            }
            if (i == 7) {
                return JWT_AUDIENCE;
            }
            if (i != 8) {
                return null;
            }
            return DISABLE_AUTH;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public AuthenticationCase getAuthenticationCase() {
        return AuthenticationCase.forNumber(this.authenticationCase_);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getAddress() {
        Object obj = this.address_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.address_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getAddressBytes() {
        Object obj = this.address_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.address_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getDeadline() {
        return this.deadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getMinDeadline() {
        return this.minDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getOperationDeadline() {
        return this.operationDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public int getPathTranslationValue() {
        return this.pathTranslation_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public PathTranslation getPathTranslation() {
        PathTranslation valueOf = PathTranslation.valueOf(this.pathTranslation_);
        return valueOf == null ? PathTranslation.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean hasJwtAudience() {
        return this.authenticationCase_ == 7;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getJwtAudience() {
        String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.authenticationCase_ == 7) {
            this.authentication_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getJwtAudienceBytes() {
        String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.authenticationCase_ == 7) {
                this.authentication_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean hasDisableAuth() {
        return this.authenticationCase_ == 8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean getDisableAuth() {
        if (this.authenticationCase_ == 8) {
            return ((Boolean) this.authentication_).booleanValue();
        }
        return false;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getProtocol() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.protocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getProtocolBytes() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.protocol_ = copyFromUtf8;
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
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (!getAddressBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.address_);
        }
        double d = this.deadline_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(3, d);
        }
        double d2 = this.minDeadline_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(4, d2);
        }
        double d3 = this.operationDeadline_;
        if (d3 != 0.0d) {
            codedOutputStream.writeDouble(5, d3);
        }
        if (this.pathTranslation_ != PathTranslation.PATH_TRANSLATION_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(6, this.pathTranslation_);
        }
        if (this.authenticationCase_ == 7) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.authentication_);
        }
        if (this.authenticationCase_ == 8) {
            codedOutputStream.writeBool(8, ((Boolean) this.authentication_).booleanValue());
        }
        if (!getProtocolBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.protocol_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getSelectorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.selector_);
        if (!getAddressBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.address_);
        }
        double d = this.deadline_;
        if (d != 0.0d) {
            computeStringSize += CodedOutputStream.computeDoubleSize(3, d);
        }
        double d2 = this.minDeadline_;
        if (d2 != 0.0d) {
            computeStringSize += CodedOutputStream.computeDoubleSize(4, d2);
        }
        double d3 = this.operationDeadline_;
        if (d3 != 0.0d) {
            computeStringSize += CodedOutputStream.computeDoubleSize(5, d3);
        }
        if (this.pathTranslation_ != PathTranslation.PATH_TRANSLATION_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(6, this.pathTranslation_);
        }
        if (this.authenticationCase_ == 7) {
            computeStringSize += GeneratedMessageV3.computeStringSize(7, this.authentication_);
        }
        if (this.authenticationCase_ == 8) {
            computeStringSize += CodedOutputStream.computeBoolSize(8, ((Boolean) this.authentication_).booleanValue());
        }
        if (!getProtocolBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(9, this.protocol_);
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
        if (!(obj instanceof BackendRule)) {
            return super.equals(obj);
        }
        BackendRule backendRule = (BackendRule) obj;
        if (!getSelector().equals(backendRule.getSelector()) || !getAddress().equals(backendRule.getAddress()) || Double.doubleToLongBits(getDeadline()) != Double.doubleToLongBits(backendRule.getDeadline()) || Double.doubleToLongBits(getMinDeadline()) != Double.doubleToLongBits(backendRule.getMinDeadline()) || Double.doubleToLongBits(getOperationDeadline()) != Double.doubleToLongBits(backendRule.getOperationDeadline()) || this.pathTranslation_ != backendRule.pathTranslation_ || !getProtocol().equals(backendRule.getProtocol()) || !getAuthenticationCase().equals(backendRule.getAuthenticationCase())) {
            return false;
        }
        int i = this.authenticationCase_;
        if (i == 7) {
            if (!getJwtAudience().equals(backendRule.getJwtAudience())) {
                return false;
            }
        } else if (i == 8 && getDisableAuth() != backendRule.getDisableAuth()) {
            return false;
        }
        return this.unknownFields.equals(backendRule.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 2) * 53) + getAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getDeadline()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getMinDeadline()))) * 37) + 5) * 53) + Internal.hashLong(Double.doubleToLongBits(getOperationDeadline()))) * 37) + 6) * 53) + this.pathTranslation_) * 37) + 9) * 53) + getProtocol().hashCode();
        int i2 = this.authenticationCase_;
        if (i2 == 7) {
            i = ((hashCode2 * 37) + 7) * 53;
            hashCode = getJwtAudience().hashCode();
        } else {
            if (i2 == 8) {
                i = ((hashCode2 * 37) + 8) * 53;
                hashCode = Internal.hashBoolean(getDisableAuth());
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

    public static BackendRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BackendRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BackendRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BackendRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BackendRule parseFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BackendRule backendRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(backendRule);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BackendRuleOrBuilder {
        private Object address_;
        private int authenticationCase_;
        private Object authentication_;
        private double deadline_;
        private double minDeadline_;
        private double operationDeadline_;
        private int pathTranslation_;
        private Object protocol_;
        private Object selector_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
        }

        private Builder() {
            this.authenticationCase_ = 0;
            this.selector_ = "";
            this.address_ = "";
            this.pathTranslation_ = 0;
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.authenticationCase_ = 0;
            this.selector_ = "";
            this.address_ = "";
            this.pathTranslation_ = 0;
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BackendRule.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.address_ = "";
            this.deadline_ = 0.0d;
            this.minDeadline_ = 0.0d;
            this.operationDeadline_ = 0.0d;
            this.pathTranslation_ = 0;
            this.protocol_ = "";
            this.authenticationCase_ = 0;
            this.authentication_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BackendRule getDefaultInstanceForType() {
            return BackendRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BackendRule build() {
            BackendRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BackendRule buildPartial() {
            BackendRule backendRule = new BackendRule(this);
            backendRule.selector_ = this.selector_;
            backendRule.address_ = this.address_;
            backendRule.deadline_ = this.deadline_;
            backendRule.minDeadline_ = this.minDeadline_;
            backendRule.operationDeadline_ = this.operationDeadline_;
            backendRule.pathTranslation_ = this.pathTranslation_;
            if (this.authenticationCase_ == 7) {
                backendRule.authentication_ = this.authentication_;
            }
            if (this.authenticationCase_ == 8) {
                backendRule.authentication_ = this.authentication_;
            }
            backendRule.protocol_ = this.protocol_;
            backendRule.authenticationCase_ = this.authenticationCase_;
            onBuilt();
            return backendRule;
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
            if (message instanceof BackendRule) {
                return mergeFrom((BackendRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BackendRule backendRule) {
            if (backendRule == BackendRule.getDefaultInstance()) {
                return this;
            }
            if (!backendRule.getSelector().isEmpty()) {
                this.selector_ = backendRule.selector_;
                onChanged();
            }
            if (!backendRule.getAddress().isEmpty()) {
                this.address_ = backendRule.address_;
                onChanged();
            }
            if (backendRule.getDeadline() != 0.0d) {
                setDeadline(backendRule.getDeadline());
            }
            if (backendRule.getMinDeadline() != 0.0d) {
                setMinDeadline(backendRule.getMinDeadline());
            }
            if (backendRule.getOperationDeadline() != 0.0d) {
                setOperationDeadline(backendRule.getOperationDeadline());
            }
            if (backendRule.pathTranslation_ != 0) {
                setPathTranslationValue(backendRule.getPathTranslationValue());
            }
            if (!backendRule.getProtocol().isEmpty()) {
                this.protocol_ = backendRule.protocol_;
                onChanged();
            }
            int i = C19602.$SwitchMap$com$google$api$BackendRule$AuthenticationCase[backendRule.getAuthenticationCase().ordinal()];
            if (i == 1) {
                this.authenticationCase_ = 7;
                this.authentication_ = backendRule.authentication_;
                onChanged();
            } else if (i == 2) {
                setDisableAuth(backendRule.getDisableAuth());
            }
            mergeUnknownFields(backendRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            BackendRule backendRule = null;
            try {
                try {
                    BackendRule backendRule2 = (BackendRule) BackendRule.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (backendRule2 != null) {
                        mergeFrom(backendRule2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    BackendRule backendRule3 = (BackendRule) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        backendRule = backendRule3;
                        if (backendRule != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (backendRule != null) {
                    mergeFrom(backendRule);
                }
                throw th;
            }
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public AuthenticationCase getAuthenticationCase() {
            return AuthenticationCase.forNumber(this.authenticationCase_);
        }

        public Builder clearAuthentication() {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.selector_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSelector(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.selector_ = str;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = BackendRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                BackendRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getAddress() {
            Object obj = this.address_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.address_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.address_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAddress(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.address_ = str;
            onChanged();
            return this;
        }

        public Builder clearAddress() {
            this.address_ = BackendRule.getDefaultInstance().getAddress();
            onChanged();
            return this;
        }

        public Builder setAddressBytes(ByteString byteString) {
            if (byteString != null) {
                BackendRule.checkByteStringIsUtf8(byteString);
                this.address_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getDeadline() {
            return this.deadline_;
        }

        public Builder setDeadline(double d) {
            this.deadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearDeadline() {
            this.deadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getMinDeadline() {
            return this.minDeadline_;
        }

        public Builder setMinDeadline(double d) {
            this.minDeadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearMinDeadline() {
            this.minDeadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getOperationDeadline() {
            return this.operationDeadline_;
        }

        public Builder setOperationDeadline(double d) {
            this.operationDeadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearOperationDeadline() {
            this.operationDeadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public int getPathTranslationValue() {
            return this.pathTranslation_;
        }

        public Builder setPathTranslationValue(int i) {
            this.pathTranslation_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public PathTranslation getPathTranslation() {
            PathTranslation valueOf = PathTranslation.valueOf(this.pathTranslation_);
            return valueOf == null ? PathTranslation.UNRECOGNIZED : valueOf;
        }

        public Builder setPathTranslation(PathTranslation pathTranslation) {
            if (pathTranslation == null) {
                throw new NullPointerException();
            }
            this.pathTranslation_ = pathTranslation.getNumber();
            onChanged();
            return this;
        }

        public Builder clearPathTranslation() {
            this.pathTranslation_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean hasJwtAudience() {
            return this.authenticationCase_ == 7;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getJwtAudience() {
            String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.authenticationCase_ == 7) {
                    this.authentication_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getJwtAudienceBytes() {
            String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.authenticationCase_ == 7) {
                    this.authentication_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setJwtAudience(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.authenticationCase_ = 7;
            this.authentication_ = str;
            onChanged();
            return this;
        }

        public Builder clearJwtAudience() {
            if (this.authenticationCase_ == 7) {
                this.authenticationCase_ = 0;
                this.authentication_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setJwtAudienceBytes(ByteString byteString) {
            if (byteString != null) {
                BackendRule.checkByteStringIsUtf8(byteString);
                this.authenticationCase_ = 7;
                this.authentication_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean hasDisableAuth() {
            return this.authenticationCase_ == 8;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean getDisableAuth() {
            if (this.authenticationCase_ == 8) {
                return ((Boolean) this.authentication_).booleanValue();
            }
            return false;
        }

        public Builder setDisableAuth(boolean z) {
            this.authenticationCase_ = 8;
            this.authentication_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearDisableAuth() {
            if (this.authenticationCase_ == 8) {
                this.authenticationCase_ = 0;
                this.authentication_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getProtocol() {
            Object obj = this.protocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.protocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getProtocolBytes() {
            Object obj = this.protocol_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.protocol_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setProtocol(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.protocol_ = str;
            onChanged();
            return this;
        }

        public Builder clearProtocol() {
            this.protocol_ = BackendRule.getDefaultInstance().getProtocol();
            onChanged();
            return this;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            if (byteString != null) {
                BackendRule.checkByteStringIsUtf8(byteString);
                this.protocol_ = byteString;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.api.BackendRule$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19602 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$BackendRule$AuthenticationCase = new int[AuthenticationCase.values().length];

        static {
            try {
                $SwitchMap$com$google$api$BackendRule$AuthenticationCase[AuthenticationCase.JWT_AUDIENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$BackendRule$AuthenticationCase[AuthenticationCase.DISABLE_AUTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$BackendRule$AuthenticationCase[AuthenticationCase.AUTHENTICATION_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackendRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BackendRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BackendRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
