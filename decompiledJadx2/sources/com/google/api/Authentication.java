package com.google.api;

import com.google.api.AuthProvider;
import com.google.api.AuthenticationRule;
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
public final class Authentication extends GeneratedMessageV3 implements AuthenticationOrBuilder {
    private static final Authentication DEFAULT_INSTANCE = new Authentication();
    private static final Parser<Authentication> PARSER = new AbstractParser<Authentication>() { // from class: com.google.api.Authentication.1
        @Override // com.google.protobuf.Parser
        public Authentication parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Authentication(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    public static final int RULES_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<AuthProvider> providers_;
    private List<AuthenticationRule> rules_;

    private Authentication(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Authentication() {
        this.memoizedIsInitialized = (byte) -1;
        this.rules_ = Collections.emptyList();
        this.providers_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Authentication();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Authentication(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 26) {
                            if ((i & 1) == 0) {
                                this.rules_ = new ArrayList();
                                i |= 1;
                            }
                            this.rules_.add(codedInputStream.readMessage(AuthenticationRule.parser(), extensionRegistryLite));
                        } else if (readTag == 34) {
                            if ((i & 2) == 0) {
                                this.providers_ = new ArrayList();
                                i |= 2;
                            }
                            this.providers_.add(codedInputStream.readMessage(AuthProvider.parser(), extensionRegistryLite));
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
                if ((i & 1) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                }
                if ((i & 2) != 0) {
                    this.providers_ = Collections.unmodifiableList(this.providers_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_Authentication_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_Authentication_fieldAccessorTable.ensureFieldAccessorsInitialized(Authentication.class, Builder.class);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthenticationRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthenticationRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public int getProvidersCount() {
        return this.providers_.size();
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthProvider getProviders(int i) {
        return this.providers_.get(i);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthProviderOrBuilder getProvidersOrBuilder(int i) {
        return this.providers_.get(i);
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
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(3, this.rules_.get(i));
        }
        for (int i2 = 0; i2 < this.providers_.size(); i2++) {
            codedOutputStream.writeMessage(4, this.providers_.get(i2));
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
        for (int i3 = 0; i3 < this.rules_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, this.rules_.get(i3));
        }
        for (int i4 = 0; i4 < this.providers_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, this.providers_.get(i4));
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
        if (!(obj instanceof Authentication)) {
            return super.equals(obj);
        }
        Authentication authentication = (Authentication) obj;
        return getRulesList().equals(authentication.getRulesList()) && getProvidersList().equals(authentication.getProvidersList()) && this.unknownFields.equals(authentication.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getRulesList().hashCode();
        }
        if (getProvidersCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getProvidersList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Authentication parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Authentication parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Authentication parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Authentication parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Authentication parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Authentication parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Authentication parseFrom(InputStream inputStream) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Authentication parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Authentication) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Authentication authentication) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authentication);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthenticationOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> providersBuilder_;
        private List<AuthProvider> providers_;
        private RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> rulesBuilder_;
        private List<AuthenticationRule> rules_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_Authentication_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_Authentication_fieldAccessorTable.ensureFieldAccessorsInitialized(Authentication.class, Builder.class);
        }

        private Builder() {
            this.rules_ = Collections.emptyList();
            this.providers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.rules_ = Collections.emptyList();
            this.providers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Authentication.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
                getProvidersFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV32 = this.providersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.providers_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_Authentication_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Authentication getDefaultInstanceForType() {
            return Authentication.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Authentication build() {
            Authentication buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Authentication buildPartial() {
            Authentication authentication = new Authentication(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -2;
                }
                authentication.rules_ = this.rules_;
            } else {
                authentication.rules_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV32 = this.providersBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                authentication.providers_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.providers_ = Collections.unmodifiableList(this.providers_);
                    this.bitField0_ &= -3;
                }
                authentication.providers_ = this.providers_;
            }
            onBuilt();
            return authentication;
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
            if (message instanceof Authentication) {
                return mergeFrom((Authentication) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Authentication authentication) {
            if (authentication == Authentication.getDefaultInstance()) {
                return this;
            }
            if (this.rulesBuilder_ == null) {
                if (!authentication.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = authentication.rules_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(authentication.rules_);
                    }
                    onChanged();
                }
            } else if (!authentication.rules_.isEmpty()) {
                if (!this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.addAllMessages(authentication.rules_);
                } else {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = authentication.rules_;
                    this.bitField0_ &= -2;
                    this.rulesBuilder_ = Authentication.alwaysUseFieldBuilders ? getRulesFieldBuilder() : null;
                }
            }
            if (this.providersBuilder_ == null) {
                if (!authentication.providers_.isEmpty()) {
                    if (this.providers_.isEmpty()) {
                        this.providers_ = authentication.providers_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureProvidersIsMutable();
                        this.providers_.addAll(authentication.providers_);
                    }
                    onChanged();
                }
            } else if (!authentication.providers_.isEmpty()) {
                if (!this.providersBuilder_.isEmpty()) {
                    this.providersBuilder_.addAllMessages(authentication.providers_);
                } else {
                    this.providersBuilder_.dispose();
                    this.providersBuilder_ = null;
                    this.providers_ = authentication.providers_;
                    this.bitField0_ &= -3;
                    this.providersBuilder_ = Authentication.alwaysUseFieldBuilders ? getProvidersFieldBuilder() : null;
                }
            }
            mergeUnknownFields(authentication.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Authentication authentication = null;
            try {
                try {
                    Authentication authentication2 = (Authentication) Authentication.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (authentication2 != null) {
                        mergeFrom(authentication2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Authentication authentication3 = (Authentication) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        authentication = authentication3;
                        if (authentication != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (authentication != null) {
                    mergeFrom(authentication);
                }
                throw th;
            }
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<AuthenticationRule> getRulesList() {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public int getRulesCount() {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthenticationRule getRules(int i) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, AuthenticationRule authenticationRule) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authenticationRule);
            } else {
                if (authenticationRule == null) {
                    throw new NullPointerException();
                }
                ensureRulesIsMutable();
                this.rules_.set(i, authenticationRule);
                onChanged();
            }
            return this;
        }

        public Builder setRules(int i, AuthenticationRule.Builder builder) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(AuthenticationRule authenticationRule) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authenticationRule);
            } else {
                if (authenticationRule == null) {
                    throw new NullPointerException();
                }
                ensureRulesIsMutable();
                this.rules_.add(authenticationRule);
                onChanged();
            }
            return this;
        }

        public Builder addRules(int i, AuthenticationRule authenticationRule) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authenticationRule);
            } else {
                if (authenticationRule == null) {
                    throw new NullPointerException();
                }
                ensureRulesIsMutable();
                this.rules_.add(i, authenticationRule);
                onChanged();
            }
            return this;
        }

        public Builder addRules(AuthenticationRule.Builder builder) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, AuthenticationRule.Builder builder) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> iterable) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public AuthenticationRule.Builder getRulesBuilder(int i) {
            return getRulesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthenticationRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public AuthenticationRule.Builder addRulesBuilder() {
            return getRulesFieldBuilder().addBuilder(AuthenticationRule.getDefaultInstance());
        }

        public AuthenticationRule.Builder addRulesBuilder(int i) {
            return getRulesFieldBuilder().addBuilder(i, AuthenticationRule.getDefaultInstance());
        }

        public List<AuthenticationRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthenticationRule, AuthenticationRule.Builder, AuthenticationRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        private void ensureProvidersIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.providers_ = new ArrayList(this.providers_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<AuthProvider> getProvidersList() {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.providers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public int getProvidersCount() {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.providers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthProvider getProviders(int i) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.providers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProviders(int i, AuthProvider authProvider) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authProvider);
            } else {
                if (authProvider == null) {
                    throw new NullPointerException();
                }
                ensureProvidersIsMutable();
                this.providers_.set(i, authProvider);
                onChanged();
            }
            return this;
        }

        public Builder setProviders(int i, AuthProvider.Builder builder) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProviders(AuthProvider authProvider) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authProvider);
            } else {
                if (authProvider == null) {
                    throw new NullPointerException();
                }
                ensureProvidersIsMutable();
                this.providers_.add(authProvider);
                onChanged();
            }
            return this;
        }

        public Builder addProviders(int i, AuthProvider authProvider) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authProvider);
            } else {
                if (authProvider == null) {
                    throw new NullPointerException();
                }
                ensureProvidersIsMutable();
                this.providers_.add(i, authProvider);
                onChanged();
            }
            return this;
        }

        public Builder addProviders(AuthProvider.Builder builder) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProviders(int i, AuthProvider.Builder builder) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> iterable) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.providers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearProviders() {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.providers_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeProviders(int i) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public AuthProvider.Builder getProvidersBuilder(int i) {
            return getProvidersFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthProviderOrBuilder getProvidersOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.providers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
            RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.providers_);
        }

        public AuthProvider.Builder addProvidersBuilder() {
            return getProvidersFieldBuilder().addBuilder(AuthProvider.getDefaultInstance());
        }

        public AuthProvider.Builder addProvidersBuilder(int i) {
            return getProvidersFieldBuilder().addBuilder(i, AuthProvider.getDefaultInstance());
        }

        public List<AuthProvider.Builder> getProvidersBuilderList() {
            return getProvidersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthProvider, AuthProvider.Builder, AuthProviderOrBuilder> getProvidersFieldBuilder() {
            if (this.providersBuilder_ == null) {
                this.providersBuilder_ = new RepeatedFieldBuilderV3<>(this.providers_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.providers_ = null;
            }
            return this.providersBuilder_;
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

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Authentication> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Authentication> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Authentication getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
