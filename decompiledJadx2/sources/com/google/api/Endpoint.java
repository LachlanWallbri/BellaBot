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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class Endpoint extends GeneratedMessageV3 implements EndpointOrBuilder {
    public static final int ALIASES_FIELD_NUMBER = 2;
    public static final int ALLOW_CORS_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TARGET_FIELD_NUMBER = 101;
    private static final long serialVersionUID = 0;
    private LazyStringList aliases_;
    private boolean allowCors_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object target_;
    private static final Endpoint DEFAULT_INSTANCE = new Endpoint();
    private static final Parser<Endpoint> PARSER = new AbstractParser<Endpoint>() { // from class: com.google.api.Endpoint.1
        @Override // com.google.protobuf.Parser
        public Endpoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Endpoint(codedInputStream, extensionRegistryLite);
        }
    };

    private Endpoint(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Endpoint() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.aliases_ = LazyStringArrayList.EMPTY;
        this.target_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Endpoint();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Endpoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.aliases_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.aliases_.add(readStringRequireUtf8);
                        } else if (readTag == 40) {
                            this.allowCors_ = codedInputStream.readBool();
                        } else if (readTag == 810) {
                            this.target_ = codedInputStream.readStringRequireUtf8();
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
                    this.aliases_ = this.aliases_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return EndpointProto.internal_static_google_api_Endpoint_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public ProtocolStringList getAliasesList() {
        return this.aliases_;
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public int getAliasesCount() {
        return this.aliases_.size();
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public String getAliases(int i) {
        return (String) this.aliases_.get(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    @Deprecated
    public ByteString getAliasesBytes(int i) {
        return this.aliases_.getByteString(i);
    }

    @Override // com.google.api.EndpointOrBuilder
    public String getTarget() {
        Object obj = this.target_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.target_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.EndpointOrBuilder
    public ByteString getTargetBytes() {
        Object obj = this.target_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.target_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.EndpointOrBuilder
    public boolean getAllowCors() {
        return this.allowCors_;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.aliases_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.aliases_.getRaw(i));
        }
        boolean z = this.allowCors_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        if (!getTargetBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 101, this.target_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.aliases_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.aliases_.getRaw(i3));
        }
        int size = computeStringSize + i2 + (getAliasesList().size() * 1);
        boolean z = this.allowCors_;
        if (z) {
            size += CodedOutputStream.computeBoolSize(5, z);
        }
        if (!getTargetBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(101, this.target_);
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
        if (!(obj instanceof Endpoint)) {
            return super.equals(obj);
        }
        Endpoint endpoint = (Endpoint) obj;
        return getName().equals(endpoint.getName()) && getAliasesList().equals(endpoint.getAliasesList()) && getTarget().equals(endpoint.getTarget()) && getAllowCors() == endpoint.getAllowCors() && this.unknownFields.equals(endpoint.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (getAliasesCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getAliasesList().hashCode();
        }
        int hashCode2 = (((((((((hashCode * 37) + 101) * 53) + getTarget().hashCode()) * 37) + 5) * 53) + Internal.hashBoolean(getAllowCors())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Endpoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Endpoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Endpoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Endpoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Endpoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Endpoint parseFrom(InputStream inputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Endpoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Endpoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Endpoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Endpoint endpoint) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(endpoint);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EndpointOrBuilder {
        private LazyStringList aliases_;
        private boolean allowCors_;
        private int bitField0_;
        private Object name_;
        private Object target_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EndpointProto.internal_static_google_api_Endpoint_fieldAccessorTable.ensureFieldAccessorsInitialized(Endpoint.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.target_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.target_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Endpoint.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.target_ = "";
            this.allowCors_ = false;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EndpointProto.internal_static_google_api_Endpoint_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Endpoint getDefaultInstanceForType() {
            return Endpoint.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Endpoint build() {
            Endpoint buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Endpoint buildPartial() {
            Endpoint endpoint = new Endpoint(this);
            int i = this.bitField0_;
            endpoint.name_ = this.name_;
            if ((this.bitField0_ & 1) != 0) {
                this.aliases_ = this.aliases_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            endpoint.aliases_ = this.aliases_;
            endpoint.target_ = this.target_;
            endpoint.allowCors_ = this.allowCors_;
            onBuilt();
            return endpoint;
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
            if (message instanceof Endpoint) {
                return mergeFrom((Endpoint) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Endpoint endpoint) {
            if (endpoint == Endpoint.getDefaultInstance()) {
                return this;
            }
            if (!endpoint.getName().isEmpty()) {
                this.name_ = endpoint.name_;
                onChanged();
            }
            if (!endpoint.aliases_.isEmpty()) {
                if (this.aliases_.isEmpty()) {
                    this.aliases_ = endpoint.aliases_;
                    this.bitField0_ &= -2;
                } else {
                    ensureAliasesIsMutable();
                    this.aliases_.addAll(endpoint.aliases_);
                }
                onChanged();
            }
            if (!endpoint.getTarget().isEmpty()) {
                this.target_ = endpoint.target_;
                onChanged();
            }
            if (endpoint.getAllowCors()) {
                setAllowCors(endpoint.getAllowCors());
            }
            mergeUnknownFields(endpoint.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Endpoint endpoint = null;
            try {
                try {
                    Endpoint endpoint2 = (Endpoint) Endpoint.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (endpoint2 != null) {
                        mergeFrom(endpoint2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Endpoint endpoint3 = (Endpoint) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        endpoint = endpoint3;
                        if (endpoint != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (endpoint != null) {
                    mergeFrom(endpoint);
                }
                throw th;
            }
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = Endpoint.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureAliasesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.aliases_ = new LazyStringArrayList(this.aliases_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public ProtocolStringList getAliasesList() {
            return this.aliases_.getUnmodifiableView();
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public int getAliasesCount() {
            return this.aliases_.size();
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public String getAliases(int i) {
            return (String) this.aliases_.get(i);
        }

        @Override // com.google.api.EndpointOrBuilder
        @Deprecated
        public ByteString getAliasesBytes(int i) {
            return this.aliases_.getByteString(i);
        }

        @Deprecated
        public Builder setAliases(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureAliasesIsMutable();
            this.aliases_.set(i, str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAliases(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureAliasesIsMutable();
            this.aliases_.add(str);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAllAliases(Iterable<String> iterable) {
            ensureAliasesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.aliases_);
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearAliases() {
            this.aliases_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder addAliasesBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                ensureAliasesIsMutable();
                this.aliases_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.EndpointOrBuilder
        public String getTarget() {
            Object obj = this.target_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.target_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.EndpointOrBuilder
        public ByteString getTargetBytes() {
            Object obj = this.target_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.target_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setTarget(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.target_ = str;
            onChanged();
            return this;
        }

        public Builder clearTarget() {
            this.target_ = Endpoint.getDefaultInstance().getTarget();
            onChanged();
            return this;
        }

        public Builder setTargetBytes(ByteString byteString) {
            if (byteString != null) {
                Endpoint.checkByteStringIsUtf8(byteString);
                this.target_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.EndpointOrBuilder
        public boolean getAllowCors() {
            return this.allowCors_;
        }

        public Builder setAllowCors(boolean z) {
            this.allowCors_ = z;
            onChanged();
            return this;
        }

        public Builder clearAllowCors() {
            this.allowCors_ = false;
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

    public static Endpoint getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Endpoint> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Endpoint> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Endpoint getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
