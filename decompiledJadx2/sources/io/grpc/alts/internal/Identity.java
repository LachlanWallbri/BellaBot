package io.grpc.alts.internal;

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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Identity extends GeneratedMessageV3 implements IdentityOrBuilder {
    public static final int ATTRIBUTES_FIELD_NUMBER = 3;
    public static final int HOSTNAME_FIELD_NUMBER = 2;
    public static final int SERVICE_ACCOUNT_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private MapField<String, String> attributes_;
    private int identityOneofCase_;
    private Object identityOneof_;
    private byte memoizedIsInitialized;
    private static final Identity DEFAULT_INSTANCE = new Identity();
    private static final Parser<Identity> PARSER = new AbstractParser<Identity>() { // from class: io.grpc.alts.internal.Identity.1
        @Override // com.google.protobuf.Parser
        public Identity parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Identity(codedInputStream, extensionRegistryLite);
        }
    };

    private Identity(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.identityOneofCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Identity() {
        this.identityOneofCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Identity();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Identity(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.identityOneofCase_ = 1;
                            this.identityOneof_ = readStringRequireUtf8;
                        } else if (readTag == 18) {
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            this.identityOneofCase_ = 2;
                            this.identityOneof_ = readStringRequireUtf82;
                        } else if (readTag == 26) {
                            if (!(z2 & true)) {
                                this.attributes_ = MapField.newMapField(AttributesDefaultEntryHolder.defaultEntry);
                                z2 |= true;
                            }
                            MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(AttributesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.attributes_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return HandshakerProto.internal_static_grpc_gcp_Identity_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 3) {
            return internalGetAttributes();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HandshakerProto.internal_static_grpc_gcp_Identity_fieldAccessorTable.ensureFieldAccessorsInitialized(Identity.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum IdentityOneofCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        SERVICE_ACCOUNT(1),
        HOSTNAME(2),
        IDENTITYONEOF_NOT_SET(0);

        private final int value;

        IdentityOneofCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static IdentityOneofCase valueOf(int i) {
            return forNumber(i);
        }

        public static IdentityOneofCase forNumber(int i) {
            if (i == 0) {
                return IDENTITYONEOF_NOT_SET;
            }
            if (i == 1) {
                return SERVICE_ACCOUNT;
            }
            if (i != 2) {
                return null;
            }
            return HOSTNAME;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public IdentityOneofCase getIdentityOneofCase() {
        return IdentityOneofCase.forNumber(this.identityOneofCase_);
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public String getServiceAccount() {
        String str = this.identityOneofCase_ == 1 ? this.identityOneof_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.identityOneofCase_ == 1) {
            this.identityOneof_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public ByteString getServiceAccountBytes() {
        String str = this.identityOneofCase_ == 1 ? this.identityOneof_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.identityOneofCase_ == 1) {
                this.identityOneof_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public String getHostname() {
        String str = this.identityOneofCase_ == 2 ? this.identityOneof_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.identityOneofCase_ == 2) {
            this.identityOneof_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public ByteString getHostnameBytes() {
        String str = this.identityOneofCase_ == 2 ? this.identityOneof_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.identityOneofCase_ == 2) {
                this.identityOneof_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class AttributesDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(HandshakerProto.internal_static_grpc_gcp_Identity_AttributesEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private AttributesDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetAttributes() {
        MapField<String, String> mapField = this.attributes_;
        return mapField == null ? MapField.emptyMapField(AttributesDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public int getAttributesCount() {
        return internalGetAttributes().getMap().size();
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public boolean containsAttributes(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        return internalGetAttributes().getMap().containsKey(str);
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    @Deprecated
    public Map<String, String> getAttributes() {
        return getAttributesMap();
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public Map<String, String> getAttributesMap() {
        return internalGetAttributes().getMap();
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public String getAttributesOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetAttributes().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // io.grpc.alts.internal.IdentityOrBuilder
    public String getAttributesOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetAttributes().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str);
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
        if (this.identityOneofCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.identityOneof_);
        }
        if (this.identityOneofCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.identityOneof_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetAttributes(), AttributesDefaultEntryHolder.defaultEntry, 3);
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = this.identityOneofCase_ == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.identityOneof_) : 0;
        if (this.identityOneofCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.identityOneof_);
        }
        for (Map.Entry<String, String> entry : internalGetAttributes().getMap().entrySet()) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, AttributesDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof Identity)) {
            return super.equals(obj);
        }
        Identity identity = (Identity) obj;
        if (!internalGetAttributes().equals(identity.internalGetAttributes()) || !getIdentityOneofCase().equals(identity.getIdentityOneofCase())) {
            return false;
        }
        int i = this.identityOneofCase_;
        if (i == 1) {
            if (!getServiceAccount().equals(identity.getServiceAccount())) {
                return false;
            }
        } else if (i == 2 && !getHostname().equals(identity.getHostname())) {
            return false;
        }
        return this.unknownFields.equals(identity.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = 779 + getDescriptor().hashCode();
        if (!internalGetAttributes().getMap().isEmpty()) {
            hashCode2 = (((hashCode2 * 37) + 3) * 53) + internalGetAttributes().hashCode();
        }
        int i2 = this.identityOneofCase_;
        if (i2 == 1) {
            i = ((hashCode2 * 37) + 1) * 53;
            hashCode = getServiceAccount().hashCode();
        } else {
            if (i2 == 2) {
                i = ((hashCode2 * 37) + 2) * 53;
                hashCode = getHostname().hashCode();
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

    public static Identity parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Identity parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Identity parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Identity parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Identity parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Identity parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Identity parseFrom(InputStream inputStream) throws IOException {
        return (Identity) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Identity parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Identity) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Identity parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Identity) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Identity parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Identity) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Identity parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Identity) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Identity parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Identity) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Identity identity) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(identity);
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
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IdentityOrBuilder {
        private MapField<String, String> attributes_;
        private int bitField0_;
        private int identityOneofCase_;
        private Object identityOneof_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HandshakerProto.internal_static_grpc_gcp_Identity_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetAttributes();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 3) {
                return internalGetMutableAttributes();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HandshakerProto.internal_static_grpc_gcp_Identity_fieldAccessorTable.ensureFieldAccessorsInitialized(Identity.class, Builder.class);
        }

        private Builder() {
            this.identityOneofCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.identityOneofCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Identity.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            internalGetMutableAttributes().clear();
            this.identityOneofCase_ = 0;
            this.identityOneof_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HandshakerProto.internal_static_grpc_gcp_Identity_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Identity getDefaultInstanceForType() {
            return Identity.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Identity build() {
            Identity buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Identity buildPartial() {
            Identity identity = new Identity(this);
            int i = this.bitField0_;
            if (this.identityOneofCase_ == 1) {
                identity.identityOneof_ = this.identityOneof_;
            }
            if (this.identityOneofCase_ == 2) {
                identity.identityOneof_ = this.identityOneof_;
            }
            identity.attributes_ = internalGetAttributes();
            identity.attributes_.makeImmutable();
            identity.identityOneofCase_ = this.identityOneofCase_;
            onBuilt();
            return identity;
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
            if (message instanceof Identity) {
                return mergeFrom((Identity) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Identity identity) {
            if (identity == Identity.getDefaultInstance()) {
                return this;
            }
            internalGetMutableAttributes().mergeFrom(identity.internalGetAttributes());
            int i = C60182.$SwitchMap$io$grpc$alts$internal$Identity$IdentityOneofCase[identity.getIdentityOneofCase().ordinal()];
            if (i == 1) {
                this.identityOneofCase_ = 1;
                this.identityOneof_ = identity.identityOneof_;
                onChanged();
            } else if (i == 2) {
                this.identityOneofCase_ = 2;
                this.identityOneof_ = identity.identityOneof_;
                onChanged();
            }
            mergeUnknownFields(identity.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Identity identity = null;
            try {
                try {
                    Identity identity2 = (Identity) Identity.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (identity2 != null) {
                        mergeFrom(identity2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Identity identity3 = (Identity) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        identity = identity3;
                        if (identity != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (identity != null) {
                    mergeFrom(identity);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public IdentityOneofCase getIdentityOneofCase() {
            return IdentityOneofCase.forNumber(this.identityOneofCase_);
        }

        public Builder clearIdentityOneof() {
            this.identityOneofCase_ = 0;
            this.identityOneof_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public String getServiceAccount() {
            String str = this.identityOneofCase_ == 1 ? this.identityOneof_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.identityOneofCase_ == 1) {
                    this.identityOneof_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public ByteString getServiceAccountBytes() {
            String str = this.identityOneofCase_ == 1 ? this.identityOneof_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.identityOneofCase_ == 1) {
                    this.identityOneof_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setServiceAccount(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.identityOneofCase_ = 1;
            this.identityOneof_ = str;
            onChanged();
            return this;
        }

        public Builder clearServiceAccount() {
            if (this.identityOneofCase_ == 1) {
                this.identityOneofCase_ = 0;
                this.identityOneof_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setServiceAccountBytes(ByteString byteString) {
            if (byteString != null) {
                Identity.checkByteStringIsUtf8(byteString);
                this.identityOneofCase_ = 1;
                this.identityOneof_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public String getHostname() {
            String str = this.identityOneofCase_ == 2 ? this.identityOneof_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.identityOneofCase_ == 2) {
                    this.identityOneof_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public ByteString getHostnameBytes() {
            String str = this.identityOneofCase_ == 2 ? this.identityOneof_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.identityOneofCase_ == 2) {
                    this.identityOneof_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setHostname(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.identityOneofCase_ = 2;
            this.identityOneof_ = str;
            onChanged();
            return this;
        }

        public Builder clearHostname() {
            if (this.identityOneofCase_ == 2) {
                this.identityOneofCase_ = 0;
                this.identityOneof_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setHostnameBytes(ByteString byteString) {
            if (byteString != null) {
                Identity.checkByteStringIsUtf8(byteString);
                this.identityOneofCase_ = 2;
                this.identityOneof_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, String> internalGetAttributes() {
            MapField<String, String> mapField = this.attributes_;
            return mapField == null ? MapField.emptyMapField(AttributesDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableAttributes() {
            onChanged();
            if (this.attributes_ == null) {
                this.attributes_ = MapField.newMapField(AttributesDefaultEntryHolder.defaultEntry);
            }
            if (!this.attributes_.isMutable()) {
                this.attributes_ = this.attributes_.copy();
            }
            return this.attributes_;
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public int getAttributesCount() {
            return internalGetAttributes().getMap().size();
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public boolean containsAttributes(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            return internalGetAttributes().getMap().containsKey(str);
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        @Deprecated
        public Map<String, String> getAttributes() {
            return getAttributesMap();
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public Map<String, String> getAttributesMap() {
            return internalGetAttributes().getMap();
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public String getAttributesOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetAttributes().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // io.grpc.alts.internal.IdentityOrBuilder
        public String getAttributesOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetAttributes().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str);
        }

        public Builder clearAttributes() {
            internalGetMutableAttributes().getMutableMap().clear();
            return this;
        }

        public Builder removeAttributes(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            internalGetMutableAttributes().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableAttributes() {
            return internalGetMutableAttributes().getMutableMap();
        }

        public Builder putAttributes(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (str2 == null) {
                throw new NullPointerException();
            }
            internalGetMutableAttributes().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllAttributes(Map<String, String> map) {
            internalGetMutableAttributes().getMutableMap().putAll(map);
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
      classes6.dex
     */
    /* renamed from: io.grpc.alts.internal.Identity$2 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C60182 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$alts$internal$Identity$IdentityOneofCase = new int[IdentityOneofCase.values().length];

        static {
            try {
                $SwitchMap$io$grpc$alts$internal$Identity$IdentityOneofCase[IdentityOneofCase.SERVICE_ACCOUNT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$Identity$IdentityOneofCase[IdentityOneofCase.HOSTNAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$alts$internal$Identity$IdentityOneofCase[IdentityOneofCase.IDENTITYONEOF_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static Identity getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Identity> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Identity> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Identity getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
