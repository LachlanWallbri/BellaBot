package com.google.api;

import com.google.api.JwtLocation;
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
public final class AuthProvider extends GeneratedMessageV3 implements AuthProviderOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 4;
    public static final int AUTHORIZATION_URL_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int ISSUER_FIELD_NUMBER = 2;
    public static final int JWKS_URI_FIELD_NUMBER = 3;
    public static final int JWT_LOCATIONS_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private volatile Object audiences_;
    private volatile Object authorizationUrl_;
    private volatile Object id_;
    private volatile Object issuer_;
    private volatile Object jwksUri_;
    private List<JwtLocation> jwtLocations_;
    private byte memoizedIsInitialized;
    private static final AuthProvider DEFAULT_INSTANCE = new AuthProvider();
    private static final Parser<AuthProvider> PARSER = new AbstractParser<AuthProvider>() { // from class: com.google.api.AuthProvider.1
        @Override // com.google.protobuf.Parser
        public AuthProvider parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthProvider(codedInputStream, extensionRegistryLite);
        }
    };

    private AuthProvider(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private AuthProvider() {
        this.memoizedIsInitialized = (byte) -1;
        this.id_ = "";
        this.issuer_ = "";
        this.jwksUri_ = "";
        this.audiences_ = "";
        this.authorizationUrl_ = "";
        this.jwtLocations_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new AuthProvider();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AuthProvider(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.id_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.issuer_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.jwksUri_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.audiences_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.authorizationUrl_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 50) {
                                if (!(z2 & true)) {
                                    this.jwtLocations_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.jwtLocations_.add(codedInputStream.readMessage(JwtLocation.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.jwtLocations_ = Collections.unmodifiableList(this.jwtLocations_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_AuthProvider_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getId() {
        Object obj = this.id_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.id_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getIdBytes() {
        Object obj = this.id_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.id_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getIssuer() {
        Object obj = this.issuer_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.issuer_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getIssuerBytes() {
        Object obj = this.issuer_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.issuer_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getJwksUri() {
        Object obj = this.jwksUri_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.jwksUri_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getJwksUriBytes() {
        Object obj = this.jwksUri_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.jwksUri_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getAudiences() {
        Object obj = this.audiences_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.audiences_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getAudiencesBytes() {
        Object obj = this.audiences_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.audiences_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public String getAuthorizationUrl() {
        Object obj = this.authorizationUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.authorizationUrl_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public ByteString getAuthorizationUrlBytes() {
        Object obj = this.authorizationUrl_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.authorizationUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public List<JwtLocation> getJwtLocationsList() {
        return this.jwtLocations_;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public List<? extends JwtLocationOrBuilder> getJwtLocationsOrBuilderList() {
        return this.jwtLocations_;
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public int getJwtLocationsCount() {
        return this.jwtLocations_.size();
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public JwtLocation getJwtLocations(int i) {
        return this.jwtLocations_.get(i);
    }

    @Override // com.google.api.AuthProviderOrBuilder
    public JwtLocationOrBuilder getJwtLocationsOrBuilder(int i) {
        return this.jwtLocations_.get(i);
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
        if (!getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.id_);
        }
        if (!getIssuerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.issuer_);
        }
        if (!getJwksUriBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.jwksUri_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.audiences_);
        }
        if (!getAuthorizationUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.authorizationUrl_);
        }
        for (int i = 0; i < this.jwtLocations_.size(); i++) {
            codedOutputStream.writeMessage(6, this.jwtLocations_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.id_) + 0 : 0;
        if (!getIssuerBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.issuer_);
        }
        if (!getJwksUriBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.jwksUri_);
        }
        if (!getAudiencesBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(4, this.audiences_);
        }
        if (!getAuthorizationUrlBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.authorizationUrl_);
        }
        for (int i2 = 0; i2 < this.jwtLocations_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(6, this.jwtLocations_.get(i2));
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
        if (!(obj instanceof AuthProvider)) {
            return super.equals(obj);
        }
        AuthProvider authProvider = (AuthProvider) obj;
        return getId().equals(authProvider.getId()) && getIssuer().equals(authProvider.getIssuer()) && getJwksUri().equals(authProvider.getJwksUri()) && getAudiences().equals(authProvider.getAudiences()) && getAuthorizationUrl().equals(authProvider.getAuthorizationUrl()) && getJwtLocationsList().equals(authProvider.getJwtLocationsList()) && this.unknownFields.equals(authProvider.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getId().hashCode()) * 37) + 2) * 53) + getIssuer().hashCode()) * 37) + 3) * 53) + getJwksUri().hashCode()) * 37) + 4) * 53) + getAudiences().hashCode()) * 37) + 5) * 53) + getAuthorizationUrl().hashCode();
        if (getJwtLocationsCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getJwtLocationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AuthProvider parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static AuthProvider parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static AuthProvider parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static AuthProvider parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(InputStream inputStream) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthProvider parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthProvider authProvider) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authProvider);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuthProviderOrBuilder {
        private Object audiences_;
        private Object authorizationUrl_;
        private int bitField0_;
        private Object id_;
        private Object issuer_;
        private Object jwksUri_;
        private RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> jwtLocationsBuilder_;
        private List<JwtLocation> jwtLocations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthProvider_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthProvider.class, Builder.class);
        }

        private Builder() {
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            this.jwtLocations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            this.jwtLocations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (AuthProvider.alwaysUseFieldBuilders) {
                getJwtLocationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.id_ = "";
            this.issuer_ = "";
            this.jwksUri_ = "";
            this.audiences_ = "";
            this.authorizationUrl_ = "";
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.jwtLocations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthProvider_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public AuthProvider getDefaultInstanceForType() {
            return AuthProvider.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AuthProvider build() {
            AuthProvider buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public AuthProvider buildPartial() {
            AuthProvider authProvider = new AuthProvider(this);
            int i = this.bitField0_;
            authProvider.id_ = this.id_;
            authProvider.issuer_ = this.issuer_;
            authProvider.jwksUri_ = this.jwksUri_;
            authProvider.audiences_ = this.audiences_;
            authProvider.authorizationUrl_ = this.authorizationUrl_;
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                authProvider.jwtLocations_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.jwtLocations_ = Collections.unmodifiableList(this.jwtLocations_);
                    this.bitField0_ &= -2;
                }
                authProvider.jwtLocations_ = this.jwtLocations_;
            }
            onBuilt();
            return authProvider;
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
            if (message instanceof AuthProvider) {
                return mergeFrom((AuthProvider) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthProvider authProvider) {
            if (authProvider == AuthProvider.getDefaultInstance()) {
                return this;
            }
            if (!authProvider.getId().isEmpty()) {
                this.id_ = authProvider.id_;
                onChanged();
            }
            if (!authProvider.getIssuer().isEmpty()) {
                this.issuer_ = authProvider.issuer_;
                onChanged();
            }
            if (!authProvider.getJwksUri().isEmpty()) {
                this.jwksUri_ = authProvider.jwksUri_;
                onChanged();
            }
            if (!authProvider.getAudiences().isEmpty()) {
                this.audiences_ = authProvider.audiences_;
                onChanged();
            }
            if (!authProvider.getAuthorizationUrl().isEmpty()) {
                this.authorizationUrl_ = authProvider.authorizationUrl_;
                onChanged();
            }
            if (this.jwtLocationsBuilder_ == null) {
                if (!authProvider.jwtLocations_.isEmpty()) {
                    if (this.jwtLocations_.isEmpty()) {
                        this.jwtLocations_ = authProvider.jwtLocations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureJwtLocationsIsMutable();
                        this.jwtLocations_.addAll(authProvider.jwtLocations_);
                    }
                    onChanged();
                }
            } else if (!authProvider.jwtLocations_.isEmpty()) {
                if (!this.jwtLocationsBuilder_.isEmpty()) {
                    this.jwtLocationsBuilder_.addAllMessages(authProvider.jwtLocations_);
                } else {
                    this.jwtLocationsBuilder_.dispose();
                    this.jwtLocationsBuilder_ = null;
                    this.jwtLocations_ = authProvider.jwtLocations_;
                    this.bitField0_ &= -2;
                    this.jwtLocationsBuilder_ = AuthProvider.alwaysUseFieldBuilders ? getJwtLocationsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(authProvider.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            AuthProvider authProvider = null;
            try {
                try {
                    AuthProvider authProvider2 = (AuthProvider) AuthProvider.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (authProvider2 != null) {
                        mergeFrom(authProvider2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    AuthProvider authProvider3 = (AuthProvider) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        authProvider = authProvider3;
                        if (authProvider != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (authProvider != null) {
                    mergeFrom(authProvider);
                }
                throw th;
            }
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getId() {
            Object obj = this.id_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.id_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.id_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setId(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.id_ = str;
            onChanged();
            return this;
        }

        public Builder clearId() {
            this.id_ = AuthProvider.getDefaultInstance().getId();
            onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.id_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getIssuer() {
            Object obj = this.issuer_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.issuer_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getIssuerBytes() {
            Object obj = this.issuer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.issuer_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setIssuer(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.issuer_ = str;
            onChanged();
            return this;
        }

        public Builder clearIssuer() {
            this.issuer_ = AuthProvider.getDefaultInstance().getIssuer();
            onChanged();
            return this;
        }

        public Builder setIssuerBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.issuer_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getJwksUri() {
            Object obj = this.jwksUri_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.jwksUri_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getJwksUriBytes() {
            Object obj = this.jwksUri_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.jwksUri_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setJwksUri(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.jwksUri_ = str;
            onChanged();
            return this;
        }

        public Builder clearJwksUri() {
            this.jwksUri_ = AuthProvider.getDefaultInstance().getJwksUri();
            onChanged();
            return this;
        }

        public Builder setJwksUriBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.jwksUri_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getAudiences() {
            Object obj = this.audiences_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.audiences_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getAudiencesBytes() {
            Object obj = this.audiences_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.audiences_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAudiences(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.audiences_ = str;
            onChanged();
            return this;
        }

        public Builder clearAudiences() {
            this.audiences_ = AuthProvider.getDefaultInstance().getAudiences();
            onChanged();
            return this;
        }

        public Builder setAudiencesBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.audiences_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public String getAuthorizationUrl() {
            Object obj = this.authorizationUrl_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.authorizationUrl_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public ByteString getAuthorizationUrlBytes() {
            Object obj = this.authorizationUrl_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.authorizationUrl_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAuthorizationUrl(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.authorizationUrl_ = str;
            onChanged();
            return this;
        }

        public Builder clearAuthorizationUrl() {
            this.authorizationUrl_ = AuthProvider.getDefaultInstance().getAuthorizationUrl();
            onChanged();
            return this;
        }

        public Builder setAuthorizationUrlBytes(ByteString byteString) {
            if (byteString != null) {
                AuthProvider.checkByteStringIsUtf8(byteString);
                this.authorizationUrl_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureJwtLocationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.jwtLocations_ = new ArrayList(this.jwtLocations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public List<JwtLocation> getJwtLocationsList() {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.jwtLocations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public int getJwtLocationsCount() {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.jwtLocations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public JwtLocation getJwtLocations(int i) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.jwtLocations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setJwtLocations(int i, JwtLocation jwtLocation) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, jwtLocation);
            } else {
                if (jwtLocation == null) {
                    throw new NullPointerException();
                }
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.set(i, jwtLocation);
                onChanged();
            }
            return this;
        }

        public Builder setJwtLocations(int i, JwtLocation.Builder builder) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addJwtLocations(JwtLocation jwtLocation) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(jwtLocation);
            } else {
                if (jwtLocation == null) {
                    throw new NullPointerException();
                }
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.add(jwtLocation);
                onChanged();
            }
            return this;
        }

        public Builder addJwtLocations(int i, JwtLocation jwtLocation) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, jwtLocation);
            } else {
                if (jwtLocation == null) {
                    throw new NullPointerException();
                }
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.add(i, jwtLocation);
                onChanged();
            }
            return this;
        }

        public Builder addJwtLocations(JwtLocation.Builder builder) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addJwtLocations(int i, JwtLocation.Builder builder) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllJwtLocations(Iterable<? extends JwtLocation> iterable) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureJwtLocationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.jwtLocations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearJwtLocations() {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.jwtLocations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeJwtLocations(int i) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureJwtLocationsIsMutable();
                this.jwtLocations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public JwtLocation.Builder getJwtLocationsBuilder(int i) {
            return getJwtLocationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public JwtLocationOrBuilder getJwtLocationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.jwtLocations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.AuthProviderOrBuilder
        public List<? extends JwtLocationOrBuilder> getJwtLocationsOrBuilderList() {
            RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> repeatedFieldBuilderV3 = this.jwtLocationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.jwtLocations_);
        }

        public JwtLocation.Builder addJwtLocationsBuilder() {
            return getJwtLocationsFieldBuilder().addBuilder(JwtLocation.getDefaultInstance());
        }

        public JwtLocation.Builder addJwtLocationsBuilder(int i) {
            return getJwtLocationsFieldBuilder().addBuilder(i, JwtLocation.getDefaultInstance());
        }

        public List<JwtLocation.Builder> getJwtLocationsBuilderList() {
            return getJwtLocationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<JwtLocation, JwtLocation.Builder, JwtLocationOrBuilder> getJwtLocationsFieldBuilder() {
            if (this.jwtLocationsBuilder_ == null) {
                this.jwtLocationsBuilder_ = new RepeatedFieldBuilderV3<>(this.jwtLocations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.jwtLocations_ = null;
            }
            return this.jwtLocationsBuilder_;
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

    public static AuthProvider getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthProvider> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<AuthProvider> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public AuthProvider getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
