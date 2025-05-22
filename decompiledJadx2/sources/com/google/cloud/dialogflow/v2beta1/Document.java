package com.google.cloud.dialogflow.v2beta1;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Document extends GeneratedMessageV3 implements DocumentOrBuilder {
    public static final int CONTENT_FIELD_NUMBER = 6;
    public static final int CONTENT_URI_FIELD_NUMBER = 5;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int KNOWLEDGE_TYPES_FIELD_NUMBER = 4;
    public static final int MIME_TYPE_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int RAW_CONTENT_FIELD_NUMBER = 9;
    private static final long serialVersionUID = 0;
    private volatile Object displayName_;
    private int knowledgeTypesMemoizedSerializedSize;
    private List<Integer> knowledgeTypes_;
    private byte memoizedIsInitialized;
    private volatile Object mimeType_;
    private volatile Object name_;
    private int sourceCase_;
    private Object source_;
    private static final Internal.ListAdapter.Converter<Integer, KnowledgeType> knowledgeTypes_converter_ = new Internal.ListAdapter.Converter<Integer, KnowledgeType>() { // from class: com.google.cloud.dialogflow.v2beta1.Document.1
        @Override // com.google.protobuf.Internal.ListAdapter.Converter
        public KnowledgeType convert(Integer num) {
            KnowledgeType valueOf = KnowledgeType.valueOf(num.intValue());
            return valueOf == null ? KnowledgeType.UNRECOGNIZED : valueOf;
        }
    };
    private static final Document DEFAULT_INSTANCE = new Document();
    private static final Parser<Document> PARSER = new AbstractParser<Document>() { // from class: com.google.cloud.dialogflow.v2beta1.Document.2
        @Override // com.google.protobuf.Parser
        public Document parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Document(codedInputStream, extensionRegistryLite);
        }
    };

    private Document(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.sourceCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Document() {
        this.sourceCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.displayName_ = "";
        this.mimeType_ = "";
        this.knowledgeTypes_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Document();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Document(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.displayName_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
                            this.mimeType_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 32) {
                            int readEnum = codedInputStream.readEnum();
                            if (!(z2 & true)) {
                                this.knowledgeTypes_ = new ArrayList();
                                z2 |= true;
                            }
                            this.knowledgeTypes_.add(Integer.valueOf(readEnum));
                        } else if (readTag == 34) {
                            int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                int readEnum2 = codedInputStream.readEnum();
                                if (!(z2 & true)) {
                                    this.knowledgeTypes_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.knowledgeTypes_.add(Integer.valueOf(readEnum2));
                            }
                            codedInputStream.popLimit(pushLimit);
                        } else if (readTag == 42) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.sourceCase_ = 5;
                            this.source_ = readStringRequireUtf8;
                        } else if (readTag == 50) {
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            this.sourceCase_ = 6;
                            this.source_ = readStringRequireUtf82;
                        } else if (readTag == 74) {
                            this.sourceCase_ = 9;
                            this.source_ = codedInputStream.readBytes();
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
                    this.knowledgeTypes_ = Collections.unmodifiableList(this.knowledgeTypes_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DocumentProto.f1638x827fabb1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentProto.f1639xf064912f.ensureFieldAccessorsInitialized(Document.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum KnowledgeType implements ProtocolMessageEnum {
        KNOWLEDGE_TYPE_UNSPECIFIED(0),
        FAQ(1),
        EXTRACTIVE_QA(2),
        UNRECOGNIZED(-1);

        public static final int EXTRACTIVE_QA_VALUE = 2;
        public static final int FAQ_VALUE = 1;
        public static final int KNOWLEDGE_TYPE_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<KnowledgeType> internalValueMap = new Internal.EnumLiteMap<KnowledgeType>() { // from class: com.google.cloud.dialogflow.v2beta1.Document.KnowledgeType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public KnowledgeType findValueByNumber(int i) {
                return KnowledgeType.forNumber(i);
            }
        };
        private static final KnowledgeType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static KnowledgeType valueOf(int i) {
            return forNumber(i);
        }

        public static KnowledgeType forNumber(int i) {
            if (i == 0) {
                return KNOWLEDGE_TYPE_UNSPECIFIED;
            }
            if (i == 1) {
                return FAQ;
            }
            if (i != 2) {
                return null;
            }
            return EXTRACTIVE_QA;
        }

        public static Internal.EnumLiteMap<KnowledgeType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Document.getDescriptor().getEnumTypes().get(0);
        }

        public static KnowledgeType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        KnowledgeType(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum SourceCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        CONTENT_URI(5),
        CONTENT(6),
        RAW_CONTENT(9),
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
            if (i == 9) {
                return RAW_CONTENT;
            }
            if (i == 5) {
                return CONTENT_URI;
            }
            if (i != 6) {
                return null;
            }
            return CONTENT;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public SourceCase getSourceCase() {
        return SourceCase.forNumber(this.sourceCase_);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public String getMimeType() {
        Object obj = this.mimeType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.mimeType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public ByteString getMimeTypeBytes() {
        Object obj = this.mimeType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.mimeType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public List<KnowledgeType> getKnowledgeTypesList() {
        return new Internal.ListAdapter(this.knowledgeTypes_, knowledgeTypes_converter_);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public int getKnowledgeTypesCount() {
        return this.knowledgeTypes_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public KnowledgeType getKnowledgeTypes(int i) {
        return knowledgeTypes_converter_.convert(this.knowledgeTypes_.get(i));
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public List<Integer> getKnowledgeTypesValueList() {
        return this.knowledgeTypes_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public int getKnowledgeTypesValue(int i) {
        return this.knowledgeTypes_.get(i).intValue();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public String getContentUri() {
        String str = this.sourceCase_ == 5 ? this.source_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.sourceCase_ == 5) {
            this.source_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public ByteString getContentUriBytes() {
        String str = this.sourceCase_ == 5 ? this.source_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.sourceCase_ == 5) {
                this.source_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    @Deprecated
    public String getContent() {
        String str = this.sourceCase_ == 6 ? this.source_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.sourceCase_ == 6) {
            this.source_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    @Deprecated
    public ByteString getContentBytes() {
        String str = this.sourceCase_ == 6 ? this.source_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.sourceCase_ == 6) {
                this.source_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
    public ByteString getRawContent() {
        if (this.sourceCase_ == 9) {
            return (ByteString) this.source_;
        }
        return ByteString.EMPTY;
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
        getSerializedSize();
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.displayName_);
        }
        if (!getMimeTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.mimeType_);
        }
        if (getKnowledgeTypesList().size() > 0) {
            codedOutputStream.writeUInt32NoTag(34);
            codedOutputStream.writeUInt32NoTag(this.knowledgeTypesMemoizedSerializedSize);
        }
        for (int i = 0; i < this.knowledgeTypes_.size(); i++) {
            codedOutputStream.writeEnumNoTag(this.knowledgeTypes_.get(i).intValue());
        }
        if (this.sourceCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.source_);
        }
        if (this.sourceCase_ == 6) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.source_);
        }
        if (this.sourceCase_ == 9) {
            codedOutputStream.writeBytes(9, (ByteString) this.source_);
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
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.displayName_);
        }
        if (!getMimeTypeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.mimeType_);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.knowledgeTypes_.size(); i3++) {
            i2 += CodedOutputStream.computeEnumSizeNoTag(this.knowledgeTypes_.get(i3).intValue());
        }
        int i4 = computeStringSize + i2;
        if (!getKnowledgeTypesList().isEmpty()) {
            i4 = i4 + 1 + CodedOutputStream.computeUInt32SizeNoTag(i2);
        }
        this.knowledgeTypesMemoizedSerializedSize = i2;
        if (this.sourceCase_ == 5) {
            i4 += GeneratedMessageV3.computeStringSize(5, this.source_);
        }
        if (this.sourceCase_ == 6) {
            i4 += GeneratedMessageV3.computeStringSize(6, this.source_);
        }
        if (this.sourceCase_ == 9) {
            i4 += CodedOutputStream.computeBytesSize(9, (ByteString) this.source_);
        }
        int serializedSize = i4 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Document)) {
            return super.equals(obj);
        }
        Document document = (Document) obj;
        if (!getName().equals(document.getName()) || !getDisplayName().equals(document.getDisplayName()) || !getMimeType().equals(document.getMimeType()) || !this.knowledgeTypes_.equals(document.knowledgeTypes_) || !getSourceCase().equals(document.getSourceCase())) {
            return false;
        }
        int i = this.sourceCase_;
        if (i != 5) {
            if (i == 6) {
                if (!getContent().equals(document.getContent())) {
                    return false;
                }
            } else if (i == 9 && !getRawContent().equals(document.getRawContent())) {
                return false;
            }
        } else if (!getContentUri().equals(document.getContentUri())) {
            return false;
        }
        return this.unknownFields.equals(document.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int hashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode2 = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDisplayName().hashCode()) * 37) + 3) * 53) + getMimeType().hashCode();
        if (getKnowledgeTypesCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 4) * 53) + this.knowledgeTypes_.hashCode();
        }
        int i2 = this.sourceCase_;
        if (i2 == 5) {
            i = ((hashCode2 * 37) + 5) * 53;
            hashCode = getContentUri().hashCode();
        } else if (i2 == 6) {
            i = ((hashCode2 * 37) + 6) * 53;
            hashCode = getContent().hashCode();
        } else {
            if (i2 == 9) {
                i = ((hashCode2 * 37) + 9) * 53;
                hashCode = getRawContent().hashCode();
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

    public static Document parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Document parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Document parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Document parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Document parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Document parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Document parseFrom(InputStream inputStream) throws IOException {
        return (Document) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Document parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Document) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Document parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Document) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Document parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Document) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Document parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Document) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Document parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Document) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Document document) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(document);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DocumentOrBuilder {
        private int bitField0_;
        private Object displayName_;
        private List<Integer> knowledgeTypes_;
        private Object mimeType_;
        private Object name_;
        private int sourceCase_;
        private Object source_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DocumentProto.f1638x827fabb1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentProto.f1639xf064912f.ensureFieldAccessorsInitialized(Document.class, Builder.class);
        }

        private Builder() {
            this.sourceCase_ = 0;
            this.name_ = "";
            this.displayName_ = "";
            this.mimeType_ = "";
            this.knowledgeTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sourceCase_ = 0;
            this.name_ = "";
            this.displayName_ = "";
            this.mimeType_ = "";
            this.knowledgeTypes_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Document.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.displayName_ = "";
            this.mimeType_ = "";
            this.knowledgeTypes_ = Collections.emptyList();
            this.bitField0_ &= -2;
            this.sourceCase_ = 0;
            this.source_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DocumentProto.f1638x827fabb1;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Document getDefaultInstanceForType() {
            return Document.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Document build() {
            Document buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Document buildPartial() {
            Document document = new Document(this);
            int i = this.bitField0_;
            document.name_ = this.name_;
            document.displayName_ = this.displayName_;
            document.mimeType_ = this.mimeType_;
            if ((this.bitField0_ & 1) != 0) {
                this.knowledgeTypes_ = Collections.unmodifiableList(this.knowledgeTypes_);
                this.bitField0_ &= -2;
            }
            document.knowledgeTypes_ = this.knowledgeTypes_;
            if (this.sourceCase_ == 5) {
                document.source_ = this.source_;
            }
            if (this.sourceCase_ == 6) {
                document.source_ = this.source_;
            }
            if (this.sourceCase_ == 9) {
                document.source_ = this.source_;
            }
            document.sourceCase_ = this.sourceCase_;
            onBuilt();
            return document;
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
            if (message instanceof Document) {
                return mergeFrom((Document) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Document document) {
            if (document == Document.getDefaultInstance()) {
                return this;
            }
            if (!document.getName().isEmpty()) {
                this.name_ = document.name_;
                onChanged();
            }
            if (!document.getDisplayName().isEmpty()) {
                this.displayName_ = document.displayName_;
                onChanged();
            }
            if (!document.getMimeType().isEmpty()) {
                this.mimeType_ = document.mimeType_;
                onChanged();
            }
            if (!document.knowledgeTypes_.isEmpty()) {
                if (this.knowledgeTypes_.isEmpty()) {
                    this.knowledgeTypes_ = document.knowledgeTypes_;
                    this.bitField0_ &= -2;
                } else {
                    ensureKnowledgeTypesIsMutable();
                    this.knowledgeTypes_.addAll(document.knowledgeTypes_);
                }
                onChanged();
            }
            int i = C24023.f1633xe537e75e[document.getSourceCase().ordinal()];
            if (i == 1) {
                this.sourceCase_ = 5;
                this.source_ = document.source_;
                onChanged();
            } else if (i == 2) {
                this.sourceCase_ = 6;
                this.source_ = document.source_;
                onChanged();
            } else if (i == 3) {
                setRawContent(document.getRawContent());
            }
            mergeUnknownFields(document.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Document document = null;
            try {
                try {
                    Document document2 = (Document) Document.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (document2 != null) {
                        mergeFrom(document2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Document document3 = (Document) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        document = document3;
                        if (document != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (document != null) {
                    mergeFrom(document);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public SourceCase getSourceCase() {
            return SourceCase.forNumber(this.sourceCase_);
        }

        public Builder clearSource() {
            this.sourceCase_ = 0;
            this.source_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
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
            this.name_ = Document.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Document.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.displayName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDisplayName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.displayName_ = str;
            onChanged();
            return this;
        }

        public Builder clearDisplayName() {
            this.displayName_ = Document.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                Document.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public String getMimeType() {
            Object obj = this.mimeType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.mimeType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public ByteString getMimeTypeBytes() {
            Object obj = this.mimeType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mimeType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setMimeType(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.mimeType_ = str;
            onChanged();
            return this;
        }

        public Builder clearMimeType() {
            this.mimeType_ = Document.getDefaultInstance().getMimeType();
            onChanged();
            return this;
        }

        public Builder setMimeTypeBytes(ByteString byteString) {
            if (byteString != null) {
                Document.checkByteStringIsUtf8(byteString);
                this.mimeType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureKnowledgeTypesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.knowledgeTypes_ = new ArrayList(this.knowledgeTypes_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public List<KnowledgeType> getKnowledgeTypesList() {
            return new Internal.ListAdapter(this.knowledgeTypes_, Document.knowledgeTypes_converter_);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public int getKnowledgeTypesCount() {
            return this.knowledgeTypes_.size();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public KnowledgeType getKnowledgeTypes(int i) {
            return (KnowledgeType) Document.knowledgeTypes_converter_.convert(this.knowledgeTypes_.get(i));
        }

        public Builder setKnowledgeTypes(int i, KnowledgeType knowledgeType) {
            if (knowledgeType == null) {
                throw new NullPointerException();
            }
            ensureKnowledgeTypesIsMutable();
            this.knowledgeTypes_.set(i, Integer.valueOf(knowledgeType.getNumber()));
            onChanged();
            return this;
        }

        public Builder addKnowledgeTypes(KnowledgeType knowledgeType) {
            if (knowledgeType == null) {
                throw new NullPointerException();
            }
            ensureKnowledgeTypesIsMutable();
            this.knowledgeTypes_.add(Integer.valueOf(knowledgeType.getNumber()));
            onChanged();
            return this;
        }

        public Builder addAllKnowledgeTypes(Iterable<? extends KnowledgeType> iterable) {
            ensureKnowledgeTypesIsMutable();
            Iterator<? extends KnowledgeType> it = iterable.iterator();
            while (it.hasNext()) {
                this.knowledgeTypes_.add(Integer.valueOf(it.next().getNumber()));
            }
            onChanged();
            return this;
        }

        public Builder clearKnowledgeTypes() {
            this.knowledgeTypes_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public List<Integer> getKnowledgeTypesValueList() {
            return Collections.unmodifiableList(this.knowledgeTypes_);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public int getKnowledgeTypesValue(int i) {
            return this.knowledgeTypes_.get(i).intValue();
        }

        public Builder setKnowledgeTypesValue(int i, int i2) {
            ensureKnowledgeTypesIsMutable();
            this.knowledgeTypes_.set(i, Integer.valueOf(i2));
            onChanged();
            return this;
        }

        public Builder addKnowledgeTypesValue(int i) {
            ensureKnowledgeTypesIsMutable();
            this.knowledgeTypes_.add(Integer.valueOf(i));
            onChanged();
            return this;
        }

        public Builder addAllKnowledgeTypesValue(Iterable<Integer> iterable) {
            ensureKnowledgeTypesIsMutable();
            Iterator<Integer> it = iterable.iterator();
            while (it.hasNext()) {
                this.knowledgeTypes_.add(Integer.valueOf(it.next().intValue()));
            }
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public String getContentUri() {
            String str = this.sourceCase_ == 5 ? this.source_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.sourceCase_ == 5) {
                    this.source_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public ByteString getContentUriBytes() {
            String str = this.sourceCase_ == 5 ? this.source_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.sourceCase_ == 5) {
                    this.source_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setContentUri(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.sourceCase_ = 5;
            this.source_ = str;
            onChanged();
            return this;
        }

        public Builder clearContentUri() {
            if (this.sourceCase_ == 5) {
                this.sourceCase_ = 0;
                this.source_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setContentUriBytes(ByteString byteString) {
            if (byteString != null) {
                Document.checkByteStringIsUtf8(byteString);
                this.sourceCase_ = 5;
                this.source_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        @Deprecated
        public String getContent() {
            String str = this.sourceCase_ == 6 ? this.source_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.sourceCase_ == 6) {
                    this.source_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        @Deprecated
        public ByteString getContentBytes() {
            String str = this.sourceCase_ == 6 ? this.source_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.sourceCase_ == 6) {
                    this.source_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        @Deprecated
        public Builder setContent(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.sourceCase_ = 6;
            this.source_ = str;
            onChanged();
            return this;
        }

        @Deprecated
        public Builder clearContent() {
            if (this.sourceCase_ == 6) {
                this.sourceCase_ = 0;
                this.source_ = null;
                onChanged();
            }
            return this;
        }

        @Deprecated
        public Builder setContentBytes(ByteString byteString) {
            if (byteString != null) {
                Document.checkByteStringIsUtf8(byteString);
                this.sourceCase_ = 6;
                this.source_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.DocumentOrBuilder
        public ByteString getRawContent() {
            if (this.sourceCase_ == 9) {
                return (ByteString) this.source_;
            }
            return ByteString.EMPTY;
        }

        public Builder setRawContent(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            this.sourceCase_ = 9;
            this.source_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRawContent() {
            if (this.sourceCase_ == 9) {
                this.sourceCase_ = 0;
                this.source_ = null;
                onChanged();
            }
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
      
     */
    /* renamed from: com.google.cloud.dialogflow.v2beta1.Document$3 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C24023 {

        /* renamed from: $SwitchMap$com$google$cloud$dialogflow$v2beta1$Document$SourceCase */
        static final /* synthetic */ int[] f1633xe537e75e = new int[SourceCase.values().length];

        static {
            try {
                f1633xe537e75e[SourceCase.CONTENT_URI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1633xe537e75e[SourceCase.CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1633xe537e75e[SourceCase.RAW_CONTENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1633xe537e75e[SourceCase.SOURCE_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static Document getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Document> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Document> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Document getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
