package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
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
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class PostalAddress extends GeneratedMessageV3 implements PostalAddressOrBuilder {
    public static final int ADDRESS_LINES_FIELD_NUMBER = 9;
    public static final int ADMINISTRATIVE_AREA_FIELD_NUMBER = 6;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int LOCALITY_FIELD_NUMBER = 7;
    public static final int ORGANIZATION_FIELD_NUMBER = 11;
    public static final int POSTAL_CODE_FIELD_NUMBER = 4;
    public static final int RECIPIENTS_FIELD_NUMBER = 10;
    public static final int REGION_CODE_FIELD_NUMBER = 2;
    public static final int REVISION_FIELD_NUMBER = 1;
    public static final int SORTING_CODE_FIELD_NUMBER = 5;
    public static final int SUBLOCALITY_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    private LazyStringList addressLines_;
    private volatile Object administrativeArea_;
    private volatile Object languageCode_;
    private volatile Object locality_;
    private byte memoizedIsInitialized;
    private volatile Object organization_;
    private volatile Object postalCode_;
    private LazyStringList recipients_;
    private volatile Object regionCode_;
    private int revision_;
    private volatile Object sortingCode_;
    private volatile Object sublocality_;
    private static final PostalAddress DEFAULT_INSTANCE = new PostalAddress();
    private static final Parser<PostalAddress> PARSER = new AbstractParser<PostalAddress>() { // from class: com.google.type.PostalAddress.1
        @Override // com.google.protobuf.Parser
        public PostalAddress parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new PostalAddress(codedInputStream, extensionRegistryLite);
        }
    };

    private PostalAddress(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private PostalAddress() {
        this.memoizedIsInitialized = (byte) -1;
        this.regionCode_ = "";
        this.languageCode_ = "";
        this.postalCode_ = "";
        this.sortingCode_ = "";
        this.administrativeArea_ = "";
        this.locality_ = "";
        this.sublocality_ = "";
        this.addressLines_ = LazyStringArrayList.EMPTY;
        this.recipients_ = LazyStringArrayList.EMPTY;
        this.organization_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new PostalAddress();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0012. Please report as an issue. */
    private PostalAddress(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    switch (readTag) {
                        case 0:
                            z = true;
                        case 8:
                            this.revision_ = codedInputStream.readInt32();
                        case 18:
                            this.regionCode_ = codedInputStream.readStringRequireUtf8();
                        case 26:
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                        case 34:
                            this.postalCode_ = codedInputStream.readStringRequireUtf8();
                        case 42:
                            this.sortingCode_ = codedInputStream.readStringRequireUtf8();
                        case 50:
                            this.administrativeArea_ = codedInputStream.readStringRequireUtf8();
                        case 58:
                            this.locality_ = codedInputStream.readStringRequireUtf8();
                        case 66:
                            this.sublocality_ = codedInputStream.readStringRequireUtf8();
                        case 74:
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if ((i & 1) == 0) {
                                this.addressLines_ = new LazyStringArrayList();
                                i |= 1;
                            }
                            this.addressLines_.add(readStringRequireUtf8);
                        case 82:
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if ((i & 2) == 0) {
                                this.recipients_ = new LazyStringArrayList();
                                i |= 2;
                            }
                            this.recipients_.add(readStringRequireUtf82);
                        case 90:
                            this.organization_ = codedInputStream.readStringRequireUtf8();
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                z = true;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.addressLines_ = this.addressLines_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.recipients_ = this.recipients_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PostalAddressProto.internal_static_google_type_PostalAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PostalAddress.class, Builder.class);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getRevision() {
        return this.revision_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getRegionCode() {
        Object obj = this.regionCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.regionCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getRegionCodeBytes() {
        Object obj = this.regionCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.regionCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getPostalCode() {
        Object obj = this.postalCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.postalCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getPostalCodeBytes() {
        Object obj = this.postalCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.postalCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getSortingCode() {
        Object obj = this.sortingCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sortingCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getSortingCodeBytes() {
        Object obj = this.sortingCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sortingCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getAdministrativeArea() {
        Object obj = this.administrativeArea_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.administrativeArea_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getAdministrativeAreaBytes() {
        Object obj = this.administrativeArea_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.administrativeArea_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getLocality() {
        Object obj = this.locality_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.locality_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getLocalityBytes() {
        Object obj = this.locality_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.locality_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getSublocality() {
        Object obj = this.sublocality_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sublocality_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getSublocalityBytes() {
        Object obj = this.sublocality_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sublocality_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ProtocolStringList getAddressLinesList() {
        return this.addressLines_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getAddressLinesCount() {
        return this.addressLines_.size();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getAddressLines(int i) {
        return (String) this.addressLines_.get(i);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getAddressLinesBytes(int i) {
        return this.addressLines_.getByteString(i);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ProtocolStringList getRecipientsList() {
        return this.recipients_;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public int getRecipientsCount() {
        return this.recipients_.size();
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getRecipients(int i) {
        return (String) this.recipients_.get(i);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getRecipientsBytes(int i) {
        return this.recipients_.getByteString(i);
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public String getOrganization() {
        Object obj = this.organization_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.organization_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.type.PostalAddressOrBuilder
    public ByteString getOrganizationBytes() {
        Object obj = this.organization_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.organization_ = copyFromUtf8;
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
        int i = this.revision_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        if (!getRegionCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.regionCode_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
        }
        if (!getPostalCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.postalCode_);
        }
        if (!getSortingCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.sortingCode_);
        }
        if (!getAdministrativeAreaBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.administrativeArea_);
        }
        if (!getLocalityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.locality_);
        }
        if (!getSublocalityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.sublocality_);
        }
        for (int i2 = 0; i2 < this.addressLines_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.addressLines_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.recipients_.size(); i3++) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.recipients_.getRaw(i3));
        }
        if (!getOrganizationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.organization_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.revision_;
        int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) + 0 : 0;
        if (!getRegionCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.regionCode_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
        }
        if (!getPostalCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(4, this.postalCode_);
        }
        if (!getSortingCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(5, this.sortingCode_);
        }
        if (!getAdministrativeAreaBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(6, this.administrativeArea_);
        }
        if (!getLocalityBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(7, this.locality_);
        }
        if (!getSublocalityBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(8, this.sublocality_);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.addressLines_.size(); i4++) {
            i3 += computeStringSizeNoTag(this.addressLines_.getRaw(i4));
        }
        int size = computeInt32Size + i3 + (getAddressLinesList().size() * 1);
        int i5 = 0;
        for (int i6 = 0; i6 < this.recipients_.size(); i6++) {
            i5 += computeStringSizeNoTag(this.recipients_.getRaw(i6));
        }
        int size2 = size + i5 + (getRecipientsList().size() * 1);
        if (!getOrganizationBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(11, this.organization_);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostalAddress)) {
            return super.equals(obj);
        }
        PostalAddress postalAddress = (PostalAddress) obj;
        return getRevision() == postalAddress.getRevision() && getRegionCode().equals(postalAddress.getRegionCode()) && getLanguageCode().equals(postalAddress.getLanguageCode()) && getPostalCode().equals(postalAddress.getPostalCode()) && getSortingCode().equals(postalAddress.getSortingCode()) && getAdministrativeArea().equals(postalAddress.getAdministrativeArea()) && getLocality().equals(postalAddress.getLocality()) && getSublocality().equals(postalAddress.getSublocality()) && getAddressLinesList().equals(postalAddress.getAddressLinesList()) && getRecipientsList().equals(postalAddress.getRecipientsList()) && getOrganization().equals(postalAddress.getOrganization()) && this.unknownFields.equals(postalAddress.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRevision()) * 37) + 2) * 53) + getRegionCode().hashCode()) * 37) + 3) * 53) + getLanguageCode().hashCode()) * 37) + 4) * 53) + getPostalCode().hashCode()) * 37) + 5) * 53) + getSortingCode().hashCode()) * 37) + 6) * 53) + getAdministrativeArea().hashCode()) * 37) + 7) * 53) + getLocality().hashCode()) * 37) + 8) * 53) + getSublocality().hashCode();
        if (getAddressLinesCount() > 0) {
            hashCode = (((hashCode * 37) + 9) * 53) + getAddressLinesList().hashCode();
        }
        if (getRecipientsCount() > 0) {
            hashCode = (((hashCode * 37) + 10) * 53) + getRecipientsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 11) * 53) + getOrganization().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static PostalAddress parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static PostalAddress parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static PostalAddress parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static PostalAddress parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(InputStream inputStream) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PostalAddress parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PostalAddress parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PostalAddress parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PostalAddress parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PostalAddress postalAddress) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(postalAddress);
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
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PostalAddressOrBuilder {
        private LazyStringList addressLines_;
        private Object administrativeArea_;
        private int bitField0_;
        private Object languageCode_;
        private Object locality_;
        private Object organization_;
        private Object postalCode_;
        private LazyStringList recipients_;
        private Object regionCode_;
        private int revision_;
        private Object sortingCode_;
        private Object sublocality_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PostalAddress.class, Builder.class);
        }

        private Builder() {
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.organization_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.organization_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = PostalAddress.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.revision_ = 0;
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            this.organization_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PostalAddress getDefaultInstanceForType() {
            return PostalAddress.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public PostalAddress build() {
            PostalAddress buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public PostalAddress buildPartial() {
            PostalAddress postalAddress = new PostalAddress(this);
            int i = this.bitField0_;
            postalAddress.revision_ = this.revision_;
            postalAddress.regionCode_ = this.regionCode_;
            postalAddress.languageCode_ = this.languageCode_;
            postalAddress.postalCode_ = this.postalCode_;
            postalAddress.sortingCode_ = this.sortingCode_;
            postalAddress.administrativeArea_ = this.administrativeArea_;
            postalAddress.locality_ = this.locality_;
            postalAddress.sublocality_ = this.sublocality_;
            if ((this.bitField0_ & 1) != 0) {
                this.addressLines_ = this.addressLines_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            postalAddress.addressLines_ = this.addressLines_;
            if ((this.bitField0_ & 2) != 0) {
                this.recipients_ = this.recipients_.getUnmodifiableView();
                this.bitField0_ &= -3;
            }
            postalAddress.recipients_ = this.recipients_;
            postalAddress.organization_ = this.organization_;
            onBuilt();
            return postalAddress;
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
            if (message instanceof PostalAddress) {
                return mergeFrom((PostalAddress) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(PostalAddress postalAddress) {
            if (postalAddress == PostalAddress.getDefaultInstance()) {
                return this;
            }
            if (postalAddress.getRevision() != 0) {
                setRevision(postalAddress.getRevision());
            }
            if (!postalAddress.getRegionCode().isEmpty()) {
                this.regionCode_ = postalAddress.regionCode_;
                onChanged();
            }
            if (!postalAddress.getLanguageCode().isEmpty()) {
                this.languageCode_ = postalAddress.languageCode_;
                onChanged();
            }
            if (!postalAddress.getPostalCode().isEmpty()) {
                this.postalCode_ = postalAddress.postalCode_;
                onChanged();
            }
            if (!postalAddress.getSortingCode().isEmpty()) {
                this.sortingCode_ = postalAddress.sortingCode_;
                onChanged();
            }
            if (!postalAddress.getAdministrativeArea().isEmpty()) {
                this.administrativeArea_ = postalAddress.administrativeArea_;
                onChanged();
            }
            if (!postalAddress.getLocality().isEmpty()) {
                this.locality_ = postalAddress.locality_;
                onChanged();
            }
            if (!postalAddress.getSublocality().isEmpty()) {
                this.sublocality_ = postalAddress.sublocality_;
                onChanged();
            }
            if (!postalAddress.addressLines_.isEmpty()) {
                if (this.addressLines_.isEmpty()) {
                    this.addressLines_ = postalAddress.addressLines_;
                    this.bitField0_ &= -2;
                } else {
                    ensureAddressLinesIsMutable();
                    this.addressLines_.addAll(postalAddress.addressLines_);
                }
                onChanged();
            }
            if (!postalAddress.recipients_.isEmpty()) {
                if (this.recipients_.isEmpty()) {
                    this.recipients_ = postalAddress.recipients_;
                    this.bitField0_ &= -3;
                } else {
                    ensureRecipientsIsMutable();
                    this.recipients_.addAll(postalAddress.recipients_);
                }
                onChanged();
            }
            if (!postalAddress.getOrganization().isEmpty()) {
                this.organization_ = postalAddress.organization_;
                onChanged();
            }
            mergeUnknownFields(postalAddress.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            PostalAddress postalAddress = null;
            try {
                try {
                    PostalAddress postalAddress2 = (PostalAddress) PostalAddress.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (postalAddress2 != null) {
                        mergeFrom(postalAddress2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    PostalAddress postalAddress3 = (PostalAddress) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        postalAddress = postalAddress3;
                        if (postalAddress != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (postalAddress != null) {
                    mergeFrom(postalAddress);
                }
                throw th;
            }
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getRevision() {
            return this.revision_;
        }

        public Builder setRevision(int i) {
            this.revision_ = i;
            onChanged();
            return this;
        }

        public Builder clearRevision() {
            this.revision_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getRegionCode() {
            Object obj = this.regionCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.regionCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getRegionCodeBytes() {
            Object obj = this.regionCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.regionCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRegionCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.regionCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearRegionCode() {
            this.regionCode_ = PostalAddress.getDefaultInstance().getRegionCode();
            onChanged();
            return this;
        }

        public Builder setRegionCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.regionCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.languageCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getLanguageCodeBytes() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.languageCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLanguageCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.languageCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearLanguageCode() {
            this.languageCode_ = PostalAddress.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getPostalCode() {
            Object obj = this.postalCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.postalCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getPostalCodeBytes() {
            Object obj = this.postalCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.postalCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPostalCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.postalCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearPostalCode() {
            this.postalCode_ = PostalAddress.getDefaultInstance().getPostalCode();
            onChanged();
            return this;
        }

        public Builder setPostalCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.postalCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getSortingCode() {
            Object obj = this.sortingCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sortingCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getSortingCodeBytes() {
            Object obj = this.sortingCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sortingCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSortingCode(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.sortingCode_ = str;
            onChanged();
            return this;
        }

        public Builder clearSortingCode() {
            this.sortingCode_ = PostalAddress.getDefaultInstance().getSortingCode();
            onChanged();
            return this;
        }

        public Builder setSortingCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.sortingCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getAdministrativeArea() {
            Object obj = this.administrativeArea_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.administrativeArea_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getAdministrativeAreaBytes() {
            Object obj = this.administrativeArea_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.administrativeArea_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAdministrativeArea(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.administrativeArea_ = str;
            onChanged();
            return this;
        }

        public Builder clearAdministrativeArea() {
            this.administrativeArea_ = PostalAddress.getDefaultInstance().getAdministrativeArea();
            onChanged();
            return this;
        }

        public Builder setAdministrativeAreaBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.administrativeArea_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getLocality() {
            Object obj = this.locality_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.locality_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getLocalityBytes() {
            Object obj = this.locality_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.locality_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setLocality(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.locality_ = str;
            onChanged();
            return this;
        }

        public Builder clearLocality() {
            this.locality_ = PostalAddress.getDefaultInstance().getLocality();
            onChanged();
            return this;
        }

        public Builder setLocalityBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.locality_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getSublocality() {
            Object obj = this.sublocality_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sublocality_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getSublocalityBytes() {
            Object obj = this.sublocality_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sublocality_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSublocality(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.sublocality_ = str;
            onChanged();
            return this;
        }

        public Builder clearSublocality() {
            this.sublocality_ = PostalAddress.getDefaultInstance().getSublocality();
            onChanged();
            return this;
        }

        public Builder setSublocalityBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.sublocality_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureAddressLinesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.addressLines_ = new LazyStringArrayList(this.addressLines_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ProtocolStringList getAddressLinesList() {
            return this.addressLines_.getUnmodifiableView();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getAddressLinesCount() {
            return this.addressLines_.size();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getAddressLines(int i) {
            return (String) this.addressLines_.get(i);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getAddressLinesBytes(int i) {
            return this.addressLines_.getByteString(i);
        }

        public Builder setAddressLines(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureAddressLinesIsMutable();
            this.addressLines_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addAddressLines(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureAddressLinesIsMutable();
            this.addressLines_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllAddressLines(Iterable<String> iterable) {
            ensureAddressLinesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.addressLines_);
            onChanged();
            return this;
        }

        public Builder clearAddressLines() {
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addAddressLinesBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                ensureAddressLinesIsMutable();
                this.addressLines_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureRecipientsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.recipients_ = new LazyStringArrayList(this.recipients_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ProtocolStringList getRecipientsList() {
            return this.recipients_.getUnmodifiableView();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public int getRecipientsCount() {
            return this.recipients_.size();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getRecipients(int i) {
            return (String) this.recipients_.get(i);
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getRecipientsBytes(int i) {
            return this.recipients_.getByteString(i);
        }

        public Builder setRecipients(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureRecipientsIsMutable();
            this.recipients_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRecipients(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureRecipientsIsMutable();
            this.recipients_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRecipients(Iterable<String> iterable) {
            ensureRecipientsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.recipients_);
            onChanged();
            return this;
        }

        public Builder clearRecipients() {
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        public Builder addRecipientsBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                ensureRecipientsIsMutable();
                this.recipients_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public String getOrganization() {
            Object obj = this.organization_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.organization_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.type.PostalAddressOrBuilder
        public ByteString getOrganizationBytes() {
            Object obj = this.organization_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.organization_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setOrganization(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.organization_ = str;
            onChanged();
            return this;
        }

        public Builder clearOrganization() {
            this.organization_ = PostalAddress.getDefaultInstance().getOrganization();
            onChanged();
            return this;
        }

        public Builder setOrganizationBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.organization_ = byteString;
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

    public static PostalAddress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PostalAddress> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<PostalAddress> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public PostalAddress getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
