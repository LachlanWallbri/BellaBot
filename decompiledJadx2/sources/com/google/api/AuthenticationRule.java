package com.google.api;

import com.google.api.AuthRequirement;
import com.google.api.OAuthRequirements;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
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
public final class AuthenticationRule extends GeneratedMessageV3 implements AuthenticationRuleOrBuilder {
    public static final int ALLOW_WITHOUT_CREDENTIAL_FIELD_NUMBER = 5;
    public static final int OAUTH_FIELD_NUMBER = 2;
    public static final int REQUIREMENTS_FIELD_NUMBER = 7;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private boolean allowWithoutCredential_;
    private byte memoizedIsInitialized;
    private OAuthRequirements oauth_;
    private List<AuthRequirement> requirements_;
    private volatile Object selector_;
    private static final AuthenticationRule DEFAULT_INSTANCE = new AuthenticationRule();
    private static final Parser<AuthenticationRule> PARSER = new AbstractParser<AuthenticationRule>() { // from class: com.google.api.AuthenticationRule.1
        @Override // com.google.protobuf.Parser
        public AuthenticationRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthenticationRule(codedInputStream, extensionRegistryLite);
        }
    };

    private AuthenticationRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AuthenticationRule() {
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.requirements_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new AuthenticationRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AuthenticationRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.selector_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            OAuthRequirements.Builder builder = this.oauth_ != null ? this.oauth_.toBuilder() : null;
                            this.oauth_ = (OAuthRequirements) codedInputStream.readMessage(OAuthRequirements.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.oauth_);
                                this.oauth_ = builder.buildPartial();
                            }
                        } else if (readTag == 40) {
                            this.allowWithoutCredential_ = codedInputStream.readBool();
                        } else if (readTag == 58) {
                            if (!(z2 & true)) {
                                this.requirements_ = new ArrayList();
                                z2 |= true;
                            }
                            this.requirements_.add(codedInputStream.readMessage(AuthRequirement.parser(), extensionRegistryLite));
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
                    this.requirements_ = Collections.unmodifiableList(this.requirements_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthenticationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationRule.class, Builder.class);
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public boolean hasOauth() {
        return this.oauth_ != null;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public OAuthRequirements getOauth() {
        OAuthRequirements oAuthRequirements = this.oauth_;
        return oAuthRequirements == null ? OAuthRequirements.getDefaultInstance() : oAuthRequirements;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public OAuthRequirementsOrBuilder getOauthOrBuilder() {
        return getOauth();
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public boolean getAllowWithoutCredential() {
        return this.allowWithoutCredential_;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public List<AuthRequirement> getRequirementsList() {
        return this.requirements_;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
        return this.requirements_;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public AuthRequirement getRequirements(int i) {
        return this.requirements_.get(i);
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public AuthRequirementOrBuilder getRequirementsOrBuilder(int i) {
        return this.requirements_.get(i);
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
        if (this.oauth_ != null) {
            codedOutputStream.writeMessage(2, getOauth());
        }
        boolean z = this.allowWithoutCredential_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        for (int i = 0; i < this.requirements_.size(); i++) {
            codedOutputStream.writeMessage(7, this.requirements_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        if (this.oauth_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getOauth());
        }
        boolean z = this.allowWithoutCredential_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(5, z);
        }
        for (int i2 = 0; i2 < this.requirements_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(7, this.requirements_.get(i2));
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
        if (!(obj instanceof AuthenticationRule)) {
            return super.equals(obj);
        }
        AuthenticationRule authenticationRule = (AuthenticationRule) obj;
        if (getSelector().equals(authenticationRule.getSelector()) && hasOauth() == authenticationRule.hasOauth()) {
            return (!hasOauth() || getOauth().equals(authenticationRule.getOauth())) && getAllowWithoutCredential() == authenticationRule.getAllowWithoutCredential() && getRequirementsList().equals(authenticationRule.getRequirementsList()) && this.unknownFields.equals(authenticationRule.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (hasOauth()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getOauth().hashCode();
        }
        int hashBoolean = (((hashCode * 37) + 5) * 53) + Internal.hashBoolean(getAllowWithoutCredential());
        if (getRequirementsCount() > 0) {
            hashBoolean = (((hashBoolean * 37) + 7) * 53) + getRequirementsList().hashCode();
        }
        int hashCode2 = (hashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AuthenticationRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static AuthenticationRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static AuthenticationRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static AuthenticationRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(InputStream inputStream) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthenticationRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthenticationRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthenticationRule authenticationRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authenticationRule);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticationRuleOrBuilder {
        private boolean allowWithoutCredential_;
        private int bitField0_;
        private SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> oauthBuilder_;
        private OAuthRequirements oauth_;
        private RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> requirementsBuilder_;
        private List<AuthRequirement> requirements_;
        private Object selector_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthenticationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.requirements_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.requirements_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (AuthenticationRule.alwaysUseFieldBuilders) {
                getRequirementsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            if (this.oauthBuilder_ == null) {
                this.oauth_ = null;
            } else {
                this.oauth_ = null;
                this.oauthBuilder_ = null;
            }
            this.allowWithoutCredential_ = false;
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.requirements_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public AuthenticationRule getDefaultInstanceForType() {
            return AuthenticationRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AuthenticationRule build() {
            AuthenticationRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AuthenticationRule buildPartial() {
            AuthenticationRule authenticationRule = new AuthenticationRule(this);
            int i = this.bitField0_;
            authenticationRule.selector_ = this.selector_;
            SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                authenticationRule.oauth_ = this.oauth_;
            } else {
                authenticationRule.oauth_ = singleFieldBuilderV3.build();
            }
            authenticationRule.allowWithoutCredential_ = this.allowWithoutCredential_;
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                authenticationRule.requirements_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.requirements_ = Collections.unmodifiableList(this.requirements_);
                    this.bitField0_ &= -2;
                }
                authenticationRule.requirements_ = this.requirements_;
            }
            onBuilt();
            return authenticationRule;
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
            if (message instanceof AuthenticationRule) {
                return mergeFrom((AuthenticationRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthenticationRule authenticationRule) {
            if (authenticationRule == AuthenticationRule.getDefaultInstance()) {
                return this;
            }
            if (!authenticationRule.getSelector().isEmpty()) {
                this.selector_ = authenticationRule.selector_;
                onChanged();
            }
            if (authenticationRule.hasOauth()) {
                mergeOauth(authenticationRule.getOauth());
            }
            if (authenticationRule.getAllowWithoutCredential()) {
                setAllowWithoutCredential(authenticationRule.getAllowWithoutCredential());
            }
            if (this.requirementsBuilder_ == null) {
                if (!authenticationRule.requirements_.isEmpty()) {
                    if (this.requirements_.isEmpty()) {
                        this.requirements_ = authenticationRule.requirements_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRequirementsIsMutable();
                        this.requirements_.addAll(authenticationRule.requirements_);
                    }
                    onChanged();
                }
            } else if (!authenticationRule.requirements_.isEmpty()) {
                if (!this.requirementsBuilder_.isEmpty()) {
                    this.requirementsBuilder_.addAllMessages(authenticationRule.requirements_);
                } else {
                    this.requirementsBuilder_.dispose();
                    this.requirementsBuilder_ = null;
                    this.requirements_ = authenticationRule.requirements_;
                    this.bitField0_ &= -2;
                    this.requirementsBuilder_ = AuthenticationRule.alwaysUseFieldBuilders ? getRequirementsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(authenticationRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AuthenticationRule authenticationRule = null;
            try {
                try {
                    AuthenticationRule authenticationRule2 = (AuthenticationRule) AuthenticationRule.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (authenticationRule2 != null) {
                        mergeFrom(authenticationRule2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    AuthenticationRule authenticationRule3 = (AuthenticationRule) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        authenticationRule = authenticationRule3;
                        if (authenticationRule != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (authenticationRule != null) {
                    mergeFrom(authenticationRule);
                }
                throw th;
            }
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
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
            this.selector_ = AuthenticationRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                AuthenticationRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public boolean hasOauth() {
            return (this.oauthBuilder_ == null && this.oauth_ == null) ? false : true;
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public OAuthRequirements getOauth() {
            SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                OAuthRequirements oAuthRequirements = this.oauth_;
                return oAuthRequirements == null ? OAuthRequirements.getDefaultInstance() : oAuthRequirements;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setOauth(OAuthRequirements oAuthRequirements) {
            SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(oAuthRequirements);
            } else {
                if (oAuthRequirements == null) {
                    throw new NullPointerException();
                }
                this.oauth_ = oAuthRequirements;
                onChanged();
            }
            return this;
        }

        public Builder setOauth(OAuthRequirements.Builder builder) {
            SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.oauth_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeOauth(OAuthRequirements oAuthRequirements) {
            SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                OAuthRequirements oAuthRequirements2 = this.oauth_;
                if (oAuthRequirements2 != null) {
                    this.oauth_ = OAuthRequirements.newBuilder(oAuthRequirements2).mergeFrom(oAuthRequirements).buildPartial();
                } else {
                    this.oauth_ = oAuthRequirements;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(oAuthRequirements);
            }
            return this;
        }

        public Builder clearOauth() {
            if (this.oauthBuilder_ == null) {
                this.oauth_ = null;
                onChanged();
            } else {
                this.oauth_ = null;
                this.oauthBuilder_ = null;
            }
            return this;
        }

        public OAuthRequirements.Builder getOauthBuilder() {
            onChanged();
            return getOauthFieldBuilder().getBuilder();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public OAuthRequirementsOrBuilder getOauthOrBuilder() {
            SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            OAuthRequirements oAuthRequirements = this.oauth_;
            return oAuthRequirements == null ? OAuthRequirements.getDefaultInstance() : oAuthRequirements;
        }

        private SingleFieldBuilderV3<OAuthRequirements, OAuthRequirements.Builder, OAuthRequirementsOrBuilder> getOauthFieldBuilder() {
            if (this.oauthBuilder_ == null) {
                this.oauthBuilder_ = new SingleFieldBuilderV3<>(getOauth(), getParentForChildren(), isClean());
                this.oauth_ = null;
            }
            return this.oauthBuilder_;
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public boolean getAllowWithoutCredential() {
            return this.allowWithoutCredential_;
        }

        public Builder setAllowWithoutCredential(boolean z) {
            this.allowWithoutCredential_ = z;
            onChanged();
            return this;
        }

        public Builder clearAllowWithoutCredential() {
            this.allowWithoutCredential_ = false;
            onChanged();
            return this;
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.requirements_ = new ArrayList(this.requirements_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public List<AuthRequirement> getRequirementsList() {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.requirements_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public int getRequirementsCount() {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requirements_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public AuthRequirement getRequirements(int i) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requirements_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRequirements(int i, AuthRequirement authRequirement) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authRequirement);
            } else {
                if (authRequirement == null) {
                    throw new NullPointerException();
                }
                ensureRequirementsIsMutable();
                this.requirements_.set(i, authRequirement);
                onChanged();
            }
            return this;
        }

        public Builder setRequirements(int i, AuthRequirement.Builder builder) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRequirements(AuthRequirement authRequirement) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authRequirement);
            } else {
                if (authRequirement == null) {
                    throw new NullPointerException();
                }
                ensureRequirementsIsMutable();
                this.requirements_.add(authRequirement);
                onChanged();
            }
            return this;
        }

        public Builder addRequirements(int i, AuthRequirement authRequirement) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authRequirement);
            } else {
                if (authRequirement == null) {
                    throw new NullPointerException();
                }
                ensureRequirementsIsMutable();
                this.requirements_.add(i, authRequirement);
                onChanged();
            }
            return this;
        }

        public Builder addRequirements(AuthRequirement.Builder builder) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRequirements(int i, AuthRequirement.Builder builder) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRequirements(Iterable<? extends AuthRequirement> iterable) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.requirements_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRequirements() {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.requirements_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRequirements(int i) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public AuthRequirement.Builder getRequirementsBuilder(int i) {
            return getRequirementsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public AuthRequirementOrBuilder getRequirementsOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requirements_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
            RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.requirements_);
        }

        public AuthRequirement.Builder addRequirementsBuilder() {
            return getRequirementsFieldBuilder().addBuilder(AuthRequirement.getDefaultInstance());
        }

        public AuthRequirement.Builder addRequirementsBuilder(int i) {
            return getRequirementsFieldBuilder().addBuilder(i, AuthRequirement.getDefaultInstance());
        }

        public List<AuthRequirement.Builder> getRequirementsBuilderList() {
            return getRequirementsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthRequirement, AuthRequirement.Builder, AuthRequirementOrBuilder> getRequirementsFieldBuilder() {
            if (this.requirementsBuilder_ == null) {
                this.requirementsBuilder_ = new RepeatedFieldBuilderV3<>(this.requirements_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.requirements_ = null;
            }
            return this.requirementsBuilder_;
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

    public static AuthenticationRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthenticationRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<AuthenticationRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public AuthenticationRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
