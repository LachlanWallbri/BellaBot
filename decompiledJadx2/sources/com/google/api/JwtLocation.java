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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class JwtLocation extends GeneratedMessageV3 implements JwtLocationOrBuilder {
    public static final int HEADER_FIELD_NUMBER = 1;
    public static final int QUERY_FIELD_NUMBER = 2;
    public static final int VALUE_PREFIX_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int inCase_;
    private Object in_;
    private byte memoizedIsInitialized;
    private volatile Object valuePrefix_;
    private static final JwtLocation DEFAULT_INSTANCE = new JwtLocation();
    private static final Parser<JwtLocation> PARSER = new AbstractParser<JwtLocation>() { // from class: com.google.api.JwtLocation.1
        @Override // com.google.protobuf.Parser
        public JwtLocation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new JwtLocation(codedInputStream, extensionRegistryLite);
        }
    };

    private JwtLocation(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.inCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private JwtLocation() {
        this.inCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.valuePrefix_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new JwtLocation();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private JwtLocation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.inCase_ = 1;
                                this.in_ = readStringRequireUtf8;
                            } else if (readTag == 18) {
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                this.inCase_ = 2;
                                this.in_ = readStringRequireUtf82;
                            } else if (readTag == 26) {
                                this.valuePrefix_ = codedInputStream.readStringRequireUtf8();
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
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_JwtLocation_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_JwtLocation_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtLocation.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum InCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        HEADER(1),
        QUERY(2),
        IN_NOT_SET(0);

        private final int value;

        InCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static InCase valueOf(int i) {
            return forNumber(i);
        }

        public static InCase forNumber(int i) {
            if (i == 0) {
                return IN_NOT_SET;
            }
            if (i == 1) {
                return HEADER;
            }
            if (i != 2) {
                return null;
            }
            return QUERY;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public InCase getInCase() {
        return InCase.forNumber(this.inCase_);
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public boolean hasHeader() {
        return this.inCase_ == 1;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public String getHeader() {
        String str = this.inCase_ == 1 ? this.in_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.inCase_ == 1) {
            this.in_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public ByteString getHeaderBytes() {
        String str = this.inCase_ == 1 ? this.in_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.inCase_ == 1) {
                this.in_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public boolean hasQuery() {
        return this.inCase_ == 2;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public String getQuery() {
        String str = this.inCase_ == 2 ? this.in_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.inCase_ == 2) {
            this.in_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public ByteString getQueryBytes() {
        String str = this.inCase_ == 2 ? this.in_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.inCase_ == 2) {
                this.in_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public String getValuePrefix() {
        Object obj = this.valuePrefix_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.valuePrefix_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public ByteString getValuePrefixBytes() {
        Object obj = this.valuePrefix_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.valuePrefix_ = copyFromUtf8;
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
        if (this.inCase_ == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.in_);
        }
        if (this.inCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.in_);
        }
        if (!getValuePrefixBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.valuePrefix_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = this.inCase_ == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.in_) : 0;
        if (this.inCase_ == 2) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.in_);
        }
        if (!getValuePrefixBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.valuePrefix_);
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
        if (!(obj instanceof JwtLocation)) {
            return super.equals(obj);
        }
        JwtLocation jwtLocation = (JwtLocation) obj;
        if (!getValuePrefix().equals(jwtLocation.getValuePrefix()) || !getInCase().equals(jwtLocation.getInCase())) {
            return false;
        }
        int i = this.inCase_;
        if (i == 1) {
            if (!getHeader().equals(jwtLocation.getHeader())) {
                return false;
            }
        } else if (i == 2 && !getQuery().equals(jwtLocation.getQuery())) {
            return false;
        }
        return this.unknownFields.equals(jwtLocation.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 3) * 53) + getValuePrefix().hashCode();
        int i2 = this.inCase_;
        if (i2 == 1) {
            i = ((hashCode2 * 37) + 1) * 53;
            hashCode = getHeader().hashCode();
        } else {
            if (i2 == 2) {
                i = ((hashCode2 * 37) + 2) * 53;
                hashCode = getQuery().hashCode();
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

    public static JwtLocation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static JwtLocation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static JwtLocation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static JwtLocation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(InputStream inputStream) throws IOException {
        return (JwtLocation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static JwtLocation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JwtLocation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static JwtLocation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (JwtLocation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static JwtLocation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JwtLocation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (JwtLocation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static JwtLocation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (JwtLocation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(JwtLocation jwtLocation) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(jwtLocation);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements JwtLocationOrBuilder {
        private int inCase_;
        private Object in_;
        private Object valuePrefix_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_JwtLocation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_JwtLocation_fieldAccessorTable.ensureFieldAccessorsInitialized(JwtLocation.class, Builder.class);
        }

        private Builder() {
            this.inCase_ = 0;
            this.valuePrefix_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.inCase_ = 0;
            this.valuePrefix_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = JwtLocation.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.valuePrefix_ = "";
            this.inCase_ = 0;
            this.in_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_JwtLocation_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public JwtLocation getDefaultInstanceForType() {
            return JwtLocation.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public JwtLocation build() {
            JwtLocation buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public JwtLocation buildPartial() {
            JwtLocation jwtLocation = new JwtLocation(this);
            if (this.inCase_ == 1) {
                jwtLocation.in_ = this.in_;
            }
            if (this.inCase_ == 2) {
                jwtLocation.in_ = this.in_;
            }
            jwtLocation.valuePrefix_ = this.valuePrefix_;
            jwtLocation.inCase_ = this.inCase_;
            onBuilt();
            return jwtLocation;
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
            if (message instanceof JwtLocation) {
                return mergeFrom((JwtLocation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(JwtLocation jwtLocation) {
            if (jwtLocation == JwtLocation.getDefaultInstance()) {
                return this;
            }
            if (!jwtLocation.getValuePrefix().isEmpty()) {
                this.valuePrefix_ = jwtLocation.valuePrefix_;
                onChanged();
            }
            int i = C19872.$SwitchMap$com$google$api$JwtLocation$InCase[jwtLocation.getInCase().ordinal()];
            if (i == 1) {
                this.inCase_ = 1;
                this.in_ = jwtLocation.in_;
                onChanged();
            } else if (i == 2) {
                this.inCase_ = 2;
                this.in_ = jwtLocation.in_;
                onChanged();
            }
            mergeUnknownFields(jwtLocation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            JwtLocation jwtLocation = null;
            try {
                try {
                    JwtLocation jwtLocation2 = (JwtLocation) JwtLocation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (jwtLocation2 != null) {
                        mergeFrom(jwtLocation2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    JwtLocation jwtLocation3 = (JwtLocation) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        jwtLocation = jwtLocation3;
                        if (jwtLocation != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (jwtLocation != null) {
                    mergeFrom(jwtLocation);
                }
                throw th;
            }
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public InCase getInCase() {
            return InCase.forNumber(this.inCase_);
        }

        public Builder clearIn() {
            this.inCase_ = 0;
            this.in_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public boolean hasHeader() {
            return this.inCase_ == 1;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public String getHeader() {
            String str = this.inCase_ == 1 ? this.in_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.inCase_ == 1) {
                    this.in_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public ByteString getHeaderBytes() {
            String str = this.inCase_ == 1 ? this.in_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.inCase_ == 1) {
                    this.in_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setHeader(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.inCase_ = 1;
            this.in_ = str;
            onChanged();
            return this;
        }

        public Builder clearHeader() {
            if (this.inCase_ == 1) {
                this.inCase_ = 0;
                this.in_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setHeaderBytes(ByteString byteString) {
            if (byteString != null) {
                JwtLocation.checkByteStringIsUtf8(byteString);
                this.inCase_ = 1;
                this.in_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public boolean hasQuery() {
            return this.inCase_ == 2;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public String getQuery() {
            String str = this.inCase_ == 2 ? this.in_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.inCase_ == 2) {
                    this.in_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public ByteString getQueryBytes() {
            String str = this.inCase_ == 2 ? this.in_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.inCase_ == 2) {
                    this.in_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setQuery(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.inCase_ = 2;
            this.in_ = str;
            onChanged();
            return this;
        }

        public Builder clearQuery() {
            if (this.inCase_ == 2) {
                this.inCase_ = 0;
                this.in_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setQueryBytes(ByteString byteString) {
            if (byteString != null) {
                JwtLocation.checkByteStringIsUtf8(byteString);
                this.inCase_ = 2;
                this.in_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public String getValuePrefix() {
            Object obj = this.valuePrefix_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.valuePrefix_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public ByteString getValuePrefixBytes() {
            Object obj = this.valuePrefix_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.valuePrefix_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setValuePrefix(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.valuePrefix_ = str;
            onChanged();
            return this;
        }

        public Builder clearValuePrefix() {
            this.valuePrefix_ = JwtLocation.getDefaultInstance().getValuePrefix();
            onChanged();
            return this;
        }

        public Builder setValuePrefixBytes(ByteString byteString) {
            if (byteString != null) {
                JwtLocation.checkByteStringIsUtf8(byteString);
                this.valuePrefix_ = byteString;
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
    /* renamed from: com.google.api.JwtLocation$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19872 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$JwtLocation$InCase = new int[InCase.values().length];

        static {
            try {
                $SwitchMap$com$google$api$JwtLocation$InCase[InCase.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$JwtLocation$InCase[InCase.QUERY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$JwtLocation$InCase[InCase.IN_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static JwtLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<JwtLocation> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<JwtLocation> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public JwtLocation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
