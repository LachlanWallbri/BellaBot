package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
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
public final class HttpBody extends GeneratedMessageV3 implements HttpBodyOrBuilder {
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 2;
    public static final int EXTENSIONS_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private volatile Object contentType_;
    private ByteString data_;
    private List<Any> extensions_;
    private byte memoizedIsInitialized;
    private static final HttpBody DEFAULT_INSTANCE = new HttpBody();
    private static final Parser<HttpBody> PARSER = new AbstractParser<HttpBody>() { // from class: com.google.api.HttpBody.1
        @Override // com.google.protobuf.Parser
        public HttpBody parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HttpBody(codedInputStream, extensionRegistryLite);
        }
    };

    private HttpBody(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HttpBody() {
        this.memoizedIsInitialized = (byte) -1;
        this.contentType_ = "";
        this.data_ = ByteString.EMPTY;
        this.extensions_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new HttpBody();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private HttpBody(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.contentType_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.data_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                if (!(z2 & true)) {
                                    this.extensions_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.extensions_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
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
                    this.extensions_ = Collections.unmodifiableList(this.extensions_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HttpBodyProto.internal_static_google_api_HttpBody_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpBody.class, Builder.class);
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public String getContentType() {
        Object obj = this.contentType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.contentType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public ByteString getContentTypeBytes() {
        Object obj = this.contentType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.contentType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public ByteString getData() {
        return this.data_;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public List<Any> getExtensionsList() {
        return this.extensions_;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
        return this.extensions_;
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public int getExtensionsCount() {
        return this.extensions_.size();
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public Any getExtensions(int i) {
        return this.extensions_.get(i);
    }

    @Override // com.google.api.HttpBodyOrBuilder
    public AnyOrBuilder getExtensionsOrBuilder(int i) {
        return this.extensions_.get(i);
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
        if (!getContentTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.contentType_);
        }
        if (!this.data_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.data_);
        }
        for (int i = 0; i < this.extensions_.size(); i++) {
            codedOutputStream.writeMessage(3, this.extensions_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getContentTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.contentType_) + 0 : 0;
        if (!this.data_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeBytesSize(2, this.data_);
        }
        for (int i2 = 0; i2 < this.extensions_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, this.extensions_.get(i2));
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
        if (!(obj instanceof HttpBody)) {
            return super.equals(obj);
        }
        HttpBody httpBody = (HttpBody) obj;
        return getContentType().equals(httpBody.getContentType()) && getData().equals(httpBody.getData()) && getExtensionsList().equals(httpBody.getExtensionsList()) && this.unknownFields.equals(httpBody.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getContentType().hashCode()) * 37) + 2) * 53) + getData().hashCode();
        if (getExtensionsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getExtensionsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static HttpBody parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static HttpBody parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HttpBody parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static HttpBody parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HttpBody parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static HttpBody parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HttpBody parseFrom(InputStream inputStream) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HttpBody parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpBody parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HttpBody parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HttpBody parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HttpBody parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpBody) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpBody httpBody) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(httpBody);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpBodyOrBuilder {
        private int bitField0_;
        private Object contentType_;
        private ByteString data_;
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> extensionsBuilder_;
        private List<Any> extensions_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HttpBodyProto.internal_static_google_api_HttpBody_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpBody.class, Builder.class);
        }

        private Builder() {
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            this.extensions_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            this.extensions_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (HttpBody.alwaysUseFieldBuilders) {
                getExtensionsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.contentType_ = "";
            this.data_ = ByteString.EMPTY;
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.extensions_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HttpBodyProto.internal_static_google_api_HttpBody_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HttpBody getDefaultInstanceForType() {
            return HttpBody.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HttpBody build() {
            HttpBody buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public HttpBody buildPartial() {
            HttpBody httpBody = new HttpBody(this);
            int i = this.bitField0_;
            httpBody.contentType_ = this.contentType_;
            httpBody.data_ = this.data_;
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                httpBody.extensions_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.extensions_ = Collections.unmodifiableList(this.extensions_);
                    this.bitField0_ &= -2;
                }
                httpBody.extensions_ = this.extensions_;
            }
            onBuilt();
            return httpBody;
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
            if (message instanceof HttpBody) {
                return mergeFrom((HttpBody) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HttpBody httpBody) {
            if (httpBody == HttpBody.getDefaultInstance()) {
                return this;
            }
            if (!httpBody.getContentType().isEmpty()) {
                this.contentType_ = httpBody.contentType_;
                onChanged();
            }
            if (httpBody.getData() != ByteString.EMPTY) {
                setData(httpBody.getData());
            }
            if (this.extensionsBuilder_ == null) {
                if (!httpBody.extensions_.isEmpty()) {
                    if (this.extensions_.isEmpty()) {
                        this.extensions_ = httpBody.extensions_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureExtensionsIsMutable();
                        this.extensions_.addAll(httpBody.extensions_);
                    }
                    onChanged();
                }
            } else if (!httpBody.extensions_.isEmpty()) {
                if (!this.extensionsBuilder_.isEmpty()) {
                    this.extensionsBuilder_.addAllMessages(httpBody.extensions_);
                } else {
                    this.extensionsBuilder_.dispose();
                    this.extensionsBuilder_ = null;
                    this.extensions_ = httpBody.extensions_;
                    this.bitField0_ &= -2;
                    this.extensionsBuilder_ = HttpBody.alwaysUseFieldBuilders ? getExtensionsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(httpBody.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            HttpBody httpBody = null;
            try {
                try {
                    HttpBody httpBody2 = (HttpBody) HttpBody.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (httpBody2 != null) {
                        mergeFrom(httpBody2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    HttpBody httpBody3 = (HttpBody) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        httpBody = httpBody3;
                        if (httpBody != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (httpBody != null) {
                    mergeFrom(httpBody);
                }
                throw th;
            }
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public String getContentType() {
            Object obj = this.contentType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.contentType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public ByteString getContentTypeBytes() {
            Object obj = this.contentType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.contentType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setContentType(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.contentType_ = str;
            onChanged();
            return this;
        }

        public Builder clearContentType() {
            this.contentType_ = HttpBody.getDefaultInstance().getContentType();
            onChanged();
            return this;
        }

        public Builder setContentTypeBytes(ByteString byteString) {
            if (byteString != null) {
                HttpBody.checkByteStringIsUtf8(byteString);
                this.contentType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public ByteString getData() {
            return this.data_;
        }

        public Builder setData(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.data_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearData() {
            this.data_ = HttpBody.getDefaultInstance().getData();
            onChanged();
            return this;
        }

        private void ensureExtensionsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.extensions_ = new ArrayList(this.extensions_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public List<Any> getExtensionsList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.extensions_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public int getExtensionsCount() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.extensions_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public Any getExtensions(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.extensions_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setExtensions(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureExtensionsIsMutable();
                this.extensions_.set(i, any);
                onChanged();
            }
            return this;
        }

        public Builder setExtensions(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addExtensions(Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureExtensionsIsMutable();
                this.extensions_.add(any);
                onChanged();
            }
            return this;
        }

        public Builder addExtensions(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureExtensionsIsMutable();
                this.extensions_.add(i, any);
                onChanged();
            }
            return this;
        }

        public Builder addExtensions(Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addExtensions(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllExtensions(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.extensions_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearExtensions() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.extensions_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeExtensions(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureExtensionsIsMutable();
                this.extensions_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Any.Builder getExtensionsBuilder(int i) {
            return getExtensionsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public AnyOrBuilder getExtensionsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.extensions_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.HttpBodyOrBuilder
        public List<? extends AnyOrBuilder> getExtensionsOrBuilderList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.extensionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.extensions_);
        }

        public Any.Builder addExtensionsBuilder() {
            return getExtensionsFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addExtensionsBuilder(int i) {
            return getExtensionsFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<Any.Builder> getExtensionsBuilderList() {
            return getExtensionsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getExtensionsFieldBuilder() {
            if (this.extensionsBuilder_ == null) {
                this.extensionsBuilder_ = new RepeatedFieldBuilderV3<>(this.extensions_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.extensions_ = null;
            }
            return this.extensionsBuilder_;
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

    public static HttpBody getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpBody> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<HttpBody> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public HttpBody getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
