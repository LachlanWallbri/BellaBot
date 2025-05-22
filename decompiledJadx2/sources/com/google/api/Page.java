package com.google.api;

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
public final class Page extends GeneratedMessageV3 implements PageOrBuilder {
    public static final int CONTENT_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int SUBPAGES_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private volatile Object content_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private List<Page> subpages_;
    private static final Page DEFAULT_INSTANCE = new Page();
    private static final Parser<Page> PARSER = new AbstractParser<Page>() { // from class: com.google.api.Page.1
        @Override // com.google.protobuf.Parser
        public Page parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Page(codedInputStream, extensionRegistryLite);
        }
    };

    private Page(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Page() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.content_ = "";
        this.subpages_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Page();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Page(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.content_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                if (!(z2 & true)) {
                                    this.subpages_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.subpages_.add(codedInputStream.readMessage(parser(), extensionRegistryLite));
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
                    this.subpages_ = Collections.unmodifiableList(this.subpages_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Page_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
    }

    @Override // com.google.api.PageOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.PageOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.PageOrBuilder
    public String getContent() {
        Object obj = this.content_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.content_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.PageOrBuilder
    public ByteString getContentBytes() {
        Object obj = this.content_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.content_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.PageOrBuilder
    public List<Page> getSubpagesList() {
        return this.subpages_;
    }

    @Override // com.google.api.PageOrBuilder
    public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
        return this.subpages_;
    }

    @Override // com.google.api.PageOrBuilder
    public int getSubpagesCount() {
        return this.subpages_.size();
    }

    @Override // com.google.api.PageOrBuilder
    public Page getSubpages(int i) {
        return this.subpages_.get(i);
    }

    @Override // com.google.api.PageOrBuilder
    public PageOrBuilder getSubpagesOrBuilder(int i) {
        return this.subpages_.get(i);
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
        if (!getContentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.content_);
        }
        for (int i = 0; i < this.subpages_.size(); i++) {
            codedOutputStream.writeMessage(3, this.subpages_.get(i));
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
        if (!getContentBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.content_);
        }
        for (int i2 = 0; i2 < this.subpages_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, this.subpages_.get(i2));
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
        if (!(obj instanceof Page)) {
            return super.equals(obj);
        }
        Page page = (Page) obj;
        return getName().equals(page.getName()) && getContent().equals(page.getContent()) && getSubpagesList().equals(page.getSubpagesList()) && this.unknownFields.equals(page.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getContent().hashCode();
        if (getSubpagesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getSubpagesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Page parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Page parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Page parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Page parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Page parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Page parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Page parseFrom(InputStream inputStream) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Page parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Page parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Page) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Page parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Page) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Page parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Page parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Page page) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(page);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PageOrBuilder {
        private int bitField0_;
        private Object content_;
        private Object name_;
        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> subpagesBuilder_;
        private List<Page> subpages_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.content_ = "";
            this.subpages_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.content_ = "";
            this.subpages_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Page.alwaysUseFieldBuilders) {
                getSubpagesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.content_ = "";
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Page getDefaultInstanceForType() {
            return Page.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Page build() {
            Page buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Page buildPartial() {
            Page page = new Page(this);
            int i = this.bitField0_;
            page.name_ = this.name_;
            page.content_ = this.content_;
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                page.subpages_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.subpages_ = Collections.unmodifiableList(this.subpages_);
                    this.bitField0_ &= -2;
                }
                page.subpages_ = this.subpages_;
            }
            onBuilt();
            return page;
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
            if (message instanceof Page) {
                return mergeFrom((Page) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Page page) {
            if (page == Page.getDefaultInstance()) {
                return this;
            }
            if (!page.getName().isEmpty()) {
                this.name_ = page.name_;
                onChanged();
            }
            if (!page.getContent().isEmpty()) {
                this.content_ = page.content_;
                onChanged();
            }
            if (this.subpagesBuilder_ == null) {
                if (!page.subpages_.isEmpty()) {
                    if (this.subpages_.isEmpty()) {
                        this.subpages_ = page.subpages_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSubpagesIsMutable();
                        this.subpages_.addAll(page.subpages_);
                    }
                    onChanged();
                }
            } else if (!page.subpages_.isEmpty()) {
                if (!this.subpagesBuilder_.isEmpty()) {
                    this.subpagesBuilder_.addAllMessages(page.subpages_);
                } else {
                    this.subpagesBuilder_.dispose();
                    this.subpagesBuilder_ = null;
                    this.subpages_ = page.subpages_;
                    this.bitField0_ &= -2;
                    this.subpagesBuilder_ = Page.alwaysUseFieldBuilders ? getSubpagesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(page.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Page page = null;
            try {
                try {
                    Page page2 = (Page) Page.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (page2 != null) {
                        mergeFrom(page2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Page page3 = (Page) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        page = page3;
                        if (page != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (page != null) {
                    mergeFrom(page);
                }
                throw th;
            }
        }

        @Override // com.google.api.PageOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.PageOrBuilder
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
            this.name_ = Page.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Page.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.PageOrBuilder
        public String getContent() {
            Object obj = this.content_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.content_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.PageOrBuilder
        public ByteString getContentBytes() {
            Object obj = this.content_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.content_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setContent(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.content_ = str;
            onChanged();
            return this;
        }

        public Builder clearContent() {
            this.content_ = Page.getDefaultInstance().getContent();
            onChanged();
            return this;
        }

        public Builder setContentBytes(ByteString byteString) {
            if (byteString != null) {
                Page.checkByteStringIsUtf8(byteString);
                this.content_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureSubpagesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.subpages_ = new ArrayList(this.subpages_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.PageOrBuilder
        public List<Page> getSubpagesList() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.subpages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.PageOrBuilder
        public int getSubpagesCount() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.PageOrBuilder
        public Page getSubpages(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSubpages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, page);
            } else {
                if (page == null) {
                    throw new NullPointerException();
                }
                ensureSubpagesIsMutable();
                this.subpages_.set(i, page);
                onChanged();
            }
            return this;
        }

        public Builder setSubpages(int i, Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSubpages(Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(page);
            } else {
                if (page == null) {
                    throw new NullPointerException();
                }
                ensureSubpagesIsMutable();
                this.subpages_.add(page);
                onChanged();
            }
            return this;
        }

        public Builder addSubpages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, page);
            } else {
                if (page == null) {
                    throw new NullPointerException();
                }
                ensureSubpagesIsMutable();
                this.subpages_.add(i, page);
                onChanged();
            }
            return this;
        }

        public Builder addSubpages(Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSubpages(int i, Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSubpages(Iterable<? extends Page> iterable) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.subpages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSubpages() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSubpages(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getSubpagesBuilder(int i) {
            return getSubpagesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.PageOrBuilder
        public PageOrBuilder getSubpagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.PageOrBuilder
        public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.subpages_);
        }

        public Builder addSubpagesBuilder() {
            return getSubpagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public Builder addSubpagesBuilder(int i) {
            return getSubpagesFieldBuilder().addBuilder(i, Page.getDefaultInstance());
        }

        public List<Builder> getSubpagesBuilderList() {
            return getSubpagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> getSubpagesFieldBuilder() {
            if (this.subpagesBuilder_ == null) {
                this.subpagesBuilder_ = new RepeatedFieldBuilderV3<>(this.subpages_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.subpages_ = null;
            }
            return this.subpagesBuilder_;
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

    public static Page getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Page> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Page> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Page getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
