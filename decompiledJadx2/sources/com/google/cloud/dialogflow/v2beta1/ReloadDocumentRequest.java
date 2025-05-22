package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.GcsSource;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class ReloadDocumentRequest extends GeneratedMessageV3 implements ReloadDocumentRequestOrBuilder {
    public static final int GCS_SOURCE_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int sourceCase_;
    private Object source_;
    private static final ReloadDocumentRequest DEFAULT_INSTANCE = new ReloadDocumentRequest();
    private static final Parser<ReloadDocumentRequest> PARSER = new AbstractParser<ReloadDocumentRequest>() { // from class: com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest.1
        @Override // com.google.protobuf.Parser
        public ReloadDocumentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ReloadDocumentRequest(codedInputStream, extensionRegistryLite);
        }
    };

    private ReloadDocumentRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.sourceCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ReloadDocumentRequest() {
        this.sourceCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ReloadDocumentRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ReloadDocumentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
                            GcsSource.Builder builder = this.sourceCase_ == 3 ? ((GcsSource) this.source_).toBuilder() : null;
                            this.source_ = codedInputStream.readMessage(GcsSource.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom((GcsSource) this.source_);
                                this.source_ = builder.buildPartial();
                            }
                            this.sourceCase_ = 3;
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
        return DocumentProto.f1648xe1bb0235;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1649x553323b3.ensureFieldAccessorsInitialized(ReloadDocumentRequest.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum SourceCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        GCS_SOURCE(3),
        SOURCE_NOT_SET(0);

        private final int value;

        SourceCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static SourceCase valueOf(int i) {
            return forNumber(i);
        }

        public static SourceCase forNumber(int i) {
            if (i == 0) {
                return SOURCE_NOT_SET;
            }
            if (i != 3) {
                return null;
            }
            return GCS_SOURCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
    public SourceCase getSourceCase() {
        return SourceCase.forNumber(this.sourceCase_);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
    public boolean hasGcsSource() {
        return this.sourceCase_ == 3;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
    public GcsSource getGcsSource() {
        if (this.sourceCase_ == 3) {
            return (GcsSource) this.source_;
        }
        return GcsSource.getDefaultInstance();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
    public GcsSourceOrBuilder getGcsSourceOrBuilder() {
        if (this.sourceCase_ == 3) {
            return (GcsSource) this.source_;
        }
        return GcsSource.getDefaultInstance();
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
        if (this.sourceCase_ == 3) {
            codedOutputStream.writeMessage(3, (GcsSource) this.source_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getNameBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        if (this.sourceCase_ == 3) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (GcsSource) this.source_);
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
        if (!(obj instanceof ReloadDocumentRequest)) {
            return super.equals(obj);
        }
        ReloadDocumentRequest reloadDocumentRequest = (ReloadDocumentRequest) obj;
        if (getName().equals(reloadDocumentRequest.getName()) && getSourceCase().equals(reloadDocumentRequest.getSourceCase())) {
            return (this.sourceCase_ != 3 || getGcsSource().equals(reloadDocumentRequest.getGcsSource())) && this.unknownFields.equals(reloadDocumentRequest.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (this.sourceCase_ == 3) {
            hashCode = (((hashCode * 37) + 3) * 53) + getGcsSource().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ReloadDocumentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ReloadDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ReloadDocumentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ReloadDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ReloadDocumentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ReloadDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ReloadDocumentRequest parseFrom(InputStream inputStream) throws IOException {
        return (ReloadDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ReloadDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ReloadDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ReloadDocumentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ReloadDocumentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ReloadDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ReloadDocumentRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ReloadDocumentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ReloadDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ReloadDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ReloadDocumentRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ReloadDocumentRequest reloadDocumentRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(reloadDocumentRequest);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ReloadDocumentRequestOrBuilder {
        private SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> gcsSourceBuilder_;
        private Object name_;
        private int sourceCase_;
        private Object source_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1648xe1bb0235;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1649x553323b3.ensureFieldAccessorsInitialized(ReloadDocumentRequest.class, Builder.class);
        }

        private Builder() {
            this.sourceCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sourceCase_ = 0;
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ReloadDocumentRequest.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.sourceCase_ = 0;
            this.source_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentProto.f1648xe1bb0235;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ReloadDocumentRequest getDefaultInstanceForType() {
            return ReloadDocumentRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ReloadDocumentRequest build() {
            ReloadDocumentRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ReloadDocumentRequest buildPartial() {
            ReloadDocumentRequest reloadDocumentRequest = new ReloadDocumentRequest(this);
            reloadDocumentRequest.name_ = this.name_;
            if (this.sourceCase_ == 3) {
                SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> singleFieldBuilderV3 = this.gcsSourceBuilder_;
                if (singleFieldBuilderV3 == null) {
                    reloadDocumentRequest.source_ = this.source_;
                } else {
                    reloadDocumentRequest.source_ = singleFieldBuilderV3.build();
                }
            }
            reloadDocumentRequest.sourceCase_ = this.sourceCase_;
            onBuilt();
            return reloadDocumentRequest;
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
            if (message instanceof ReloadDocumentRequest) {
                return mergeFrom((ReloadDocumentRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ReloadDocumentRequest reloadDocumentRequest) {
            if (reloadDocumentRequest == ReloadDocumentRequest.getDefaultInstance()) {
                return this;
            }
            if (!reloadDocumentRequest.getName().isEmpty()) {
                this.name_ = reloadDocumentRequest.name_;
                onChanged();
            }
            if (C25302.f1820xab553a4a[reloadDocumentRequest.getSourceCase().ordinal()] == 1) {
                mergeGcsSource(reloadDocumentRequest.getGcsSource());
            }
            mergeUnknownFields(reloadDocumentRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ReloadDocumentRequest reloadDocumentRequest = null;
            try {
                try {
                    ReloadDocumentRequest reloadDocumentRequest2 = (ReloadDocumentRequest) ReloadDocumentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (reloadDocumentRequest2 != null) {
                        mergeFrom(reloadDocumentRequest2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ReloadDocumentRequest reloadDocumentRequest3 = (ReloadDocumentRequest) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        reloadDocumentRequest = reloadDocumentRequest3;
                        if (reloadDocumentRequest != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (reloadDocumentRequest != null) {
                    mergeFrom(reloadDocumentRequest);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
        public SourceCase getSourceCase() {
            return SourceCase.forNumber(this.sourceCase_);
        }

        public Builder clearSource() {
            this.sourceCase_ = 0;
            this.source_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
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
            this.name_ = ReloadDocumentRequest.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                ReloadDocumentRequest.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
        public boolean hasGcsSource() {
            return this.sourceCase_ == 3;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
        public GcsSource getGcsSource() {
            SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> singleFieldBuilderV3 = this.gcsSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.sourceCase_ == 3) {
                    return (GcsSource) this.source_;
                }
                return GcsSource.getDefaultInstance();
            }
            if (this.sourceCase_ == 3) {
                return singleFieldBuilderV3.getMessage();
            }
            return GcsSource.getDefaultInstance();
        }

        public Builder setGcsSource(GcsSource gcsSource) {
            SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> singleFieldBuilderV3 = this.gcsSourceBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(gcsSource);
            } else {
                if (gcsSource == null) {
                    throw new NullPointerException();
                }
                this.source_ = gcsSource;
                onChanged();
            }
            this.sourceCase_ = 3;
            return this;
        }

        public Builder setGcsSource(GcsSource.Builder builder) {
            SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> singleFieldBuilderV3 = this.gcsSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.source_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.sourceCase_ = 3;
            return this;
        }

        public Builder mergeGcsSource(GcsSource gcsSource) {
            SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> singleFieldBuilderV3 = this.gcsSourceBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.sourceCase_ == 3 && this.source_ != GcsSource.getDefaultInstance()) {
                    this.source_ = GcsSource.newBuilder((GcsSource) this.source_).mergeFrom(gcsSource).buildPartial();
                } else {
                    this.source_ = gcsSource;
                }
                onChanged();
            } else {
                if (this.sourceCase_ == 3) {
                    singleFieldBuilderV3.mergeFrom(gcsSource);
                }
                this.gcsSourceBuilder_.setMessage(gcsSource);
            }
            this.sourceCase_ = 3;
            return this;
        }

        public Builder clearGcsSource() {
            if (this.gcsSourceBuilder_ == null) {
                if (this.sourceCase_ == 3) {
                    this.sourceCase_ = 0;
                    this.source_ = null;
                    onChanged();
                }
            } else {
                if (this.sourceCase_ == 3) {
                    this.sourceCase_ = 0;
                    this.source_ = null;
                }
                this.gcsSourceBuilder_.clear();
            }
            return this;
        }

        public GcsSource.Builder getGcsSourceBuilder() {
            return getGcsSourceFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequestOrBuilder
        public GcsSourceOrBuilder getGcsSourceOrBuilder() {
            SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> singleFieldBuilderV3;
            if (this.sourceCase_ == 3 && (singleFieldBuilderV3 = this.gcsSourceBuilder_) != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (this.sourceCase_ == 3) {
                return (GcsSource) this.source_;
            }
            return GcsSource.getDefaultInstance();
        }

        private SingleFieldBuilderV3<GcsSource, GcsSource.Builder, GcsSourceOrBuilder> getGcsSourceFieldBuilder() {
            if (this.gcsSourceBuilder_ == null) {
                if (this.sourceCase_ != 3) {
                    this.source_ = GcsSource.getDefaultInstance();
                }
                this.gcsSourceBuilder_ = new SingleFieldBuilderV3<>((GcsSource) this.source_, getParentForChildren(), isClean());
                this.source_ = null;
            }
            this.sourceCase_ = 3;
            onChanged();
            return this.gcsSourceBuilder_;
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
    /* renamed from: com.google.cloud.dialogflow.v2beta1.ReloadDocumentRequest$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C25302 {

        /* renamed from: $SwitchMap$com$google$cloud$dialogflow$v2beta1$ReloadDocumentRequest$SourceCase */
        static final /* synthetic */ int[] f1820xab553a4a = new int[SourceCase.values().length];

        static {
            try {
                f1820xab553a4a[SourceCase.GCS_SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1820xab553a4a[SourceCase.SOURCE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ReloadDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ReloadDocumentRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ReloadDocumentRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ReloadDocumentRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
