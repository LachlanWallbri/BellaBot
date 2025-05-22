package com.google.geo.type;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.LatLng;
import com.google.type.LatLngOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class Viewport extends GeneratedMessageV3 implements ViewportOrBuilder {
    public static final int HIGH_FIELD_NUMBER = 2;
    public static final int LOW_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private LatLng high_;
    private LatLng low_;
    private byte memoizedIsInitialized;
    private static final Viewport DEFAULT_INSTANCE = new Viewport();
    private static final Parser<Viewport> PARSER = new AbstractParser<Viewport>() { // from class: com.google.geo.type.Viewport.1
        @Override // com.google.protobuf.Parser
        public Viewport parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Viewport(codedInputStream, extensionRegistryLite);
        }
    };

    private Viewport(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Viewport() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Viewport();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Viewport(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        LatLng.Builder builder;
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
                            builder = this.low_ != null ? this.low_.toBuilder() : null;
                            this.low_ = (LatLng) codedInputStream.readMessage(LatLng.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.low_);
                                this.low_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            builder = this.high_ != null ? this.high_.toBuilder() : null;
                            this.high_ = (LatLng) codedInputStream.readMessage(LatLng.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.high_);
                                this.high_ = builder.buildPartial();
                            }
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
        return ViewportProto.internal_static_google_geo_type_Viewport_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ViewportProto.internal_static_google_geo_type_Viewport_fieldAccessorTable.ensureFieldAccessorsInitialized(Viewport.class, Builder.class);
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasLow() {
        return this.low_ != null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getLow() {
        LatLng latLng = this.low_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLngOrBuilder getLowOrBuilder() {
        return getLow();
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public boolean hasHigh() {
        return this.high_ != null;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLng getHigh() {
        LatLng latLng = this.high_;
        return latLng == null ? LatLng.getDefaultInstance() : latLng;
    }

    @Override // com.google.geo.type.ViewportOrBuilder
    public LatLngOrBuilder getHighOrBuilder() {
        return getHigh();
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
        if (this.low_ != null) {
            codedOutputStream.writeMessage(1, getLow());
        }
        if (this.high_ != null) {
            codedOutputStream.writeMessage(2, getHigh());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.low_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getLow()) : 0;
        if (this.high_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getHigh());
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Viewport)) {
            return super.equals(obj);
        }
        Viewport viewport = (Viewport) obj;
        if (hasLow() != viewport.hasLow()) {
            return false;
        }
        if ((!hasLow() || getLow().equals(viewport.getLow())) && hasHigh() == viewport.hasHigh()) {
            return (!hasHigh() || getHigh().equals(viewport.getHigh())) && this.unknownFields.equals(viewport.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasLow()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getLow().hashCode();
        }
        if (hasHigh()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getHigh().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Viewport parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Viewport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Viewport parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Viewport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Viewport parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Viewport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Viewport parseFrom(InputStream inputStream) throws IOException {
        return (Viewport) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Viewport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Viewport) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Viewport parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Viewport) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Viewport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Viewport) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Viewport parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Viewport) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Viewport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Viewport) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Viewport viewport) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(viewport);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ViewportOrBuilder {
        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> highBuilder_;
        private LatLng high_;
        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> lowBuilder_;
        private LatLng low_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ViewportProto.internal_static_google_geo_type_Viewport_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ViewportProto.internal_static_google_geo_type_Viewport_fieldAccessorTable.ensureFieldAccessorsInitialized(Viewport.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Viewport.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.lowBuilder_ == null) {
                this.low_ = null;
            } else {
                this.low_ = null;
                this.lowBuilder_ = null;
            }
            if (this.highBuilder_ == null) {
                this.high_ = null;
            } else {
                this.high_ = null;
                this.highBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ViewportProto.internal_static_google_geo_type_Viewport_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Viewport getDefaultInstanceForType() {
            return Viewport.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Viewport build() {
            Viewport buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Viewport buildPartial() {
            Viewport viewport = new Viewport(this);
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                viewport.low_ = this.low_;
            } else {
                viewport.low_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV32 = this.highBuilder_;
            if (singleFieldBuilderV32 == null) {
                viewport.high_ = this.high_;
            } else {
                viewport.high_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return viewport;
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
            if (message instanceof Viewport) {
                return mergeFrom((Viewport) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Viewport viewport) {
            if (viewport == Viewport.getDefaultInstance()) {
                return this;
            }
            if (viewport.hasLow()) {
                mergeLow(viewport.getLow());
            }
            if (viewport.hasHigh()) {
                mergeHigh(viewport.getHigh());
            }
            mergeUnknownFields(viewport.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Viewport viewport = null;
            try {
                try {
                    Viewport viewport2 = (Viewport) Viewport.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (viewport2 != null) {
                        mergeFrom(viewport2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Viewport viewport3 = (Viewport) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        viewport = viewport3;
                        if (viewport != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (viewport != null) {
                    mergeFrom(viewport);
                }
                throw th;
            }
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasLow() {
            return (this.lowBuilder_ == null && this.low_ == null) ? false : true;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getLow() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng = this.low_;
                return latLng == null ? LatLng.getDefaultInstance() : latLng;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setLow(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(latLng);
            } else {
                if (latLng == null) {
                    throw new NullPointerException();
                }
                this.low_ = latLng;
                onChanged();
            }
            return this;
        }

        public Builder setLow(LatLng.Builder builder) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.low_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLow(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng2 = this.low_;
                if (latLng2 != null) {
                    this.low_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).buildPartial();
                } else {
                    this.low_ = latLng;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(latLng);
            }
            return this;
        }

        public Builder clearLow() {
            if (this.lowBuilder_ == null) {
                this.low_ = null;
                onChanged();
            } else {
                this.low_ = null;
                this.lowBuilder_ = null;
            }
            return this;
        }

        public LatLng.Builder getLowBuilder() {
            onChanged();
            return getLowFieldBuilder().getBuilder();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLngOrBuilder getLowOrBuilder() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.lowBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            LatLng latLng = this.low_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> getLowFieldBuilder() {
            if (this.lowBuilder_ == null) {
                this.lowBuilder_ = new SingleFieldBuilderV3<>(getLow(), getParentForChildren(), isClean());
                this.low_ = null;
            }
            return this.lowBuilder_;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public boolean hasHigh() {
            return (this.highBuilder_ == null && this.high_ == null) ? false : true;
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLng getHigh() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng = this.high_;
                return latLng == null ? LatLng.getDefaultInstance() : latLng;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setHigh(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(latLng);
            } else {
                if (latLng == null) {
                    throw new NullPointerException();
                }
                this.high_ = latLng;
                onChanged();
            }
            return this;
        }

        public Builder setHigh(LatLng.Builder builder) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.high_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeHigh(LatLng latLng) {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 == null) {
                LatLng latLng2 = this.high_;
                if (latLng2 != null) {
                    this.high_ = LatLng.newBuilder(latLng2).mergeFrom(latLng).buildPartial();
                } else {
                    this.high_ = latLng;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(latLng);
            }
            return this;
        }

        public Builder clearHigh() {
            if (this.highBuilder_ == null) {
                this.high_ = null;
                onChanged();
            } else {
                this.high_ = null;
                this.highBuilder_ = null;
            }
            return this;
        }

        public LatLng.Builder getHighBuilder() {
            onChanged();
            return getHighFieldBuilder().getBuilder();
        }

        @Override // com.google.geo.type.ViewportOrBuilder
        public LatLngOrBuilder getHighOrBuilder() {
            SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> singleFieldBuilderV3 = this.highBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            LatLng latLng = this.high_;
            return latLng == null ? LatLng.getDefaultInstance() : latLng;
        }

        private SingleFieldBuilderV3<LatLng, LatLng.Builder, LatLngOrBuilder> getHighFieldBuilder() {
            if (this.highBuilder_ == null) {
                this.highBuilder_ = new SingleFieldBuilderV3<>(getHigh(), getParentForChildren(), isClean());
                this.high_ = null;
            }
            return this.highBuilder_;
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

    public static Viewport getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Viewport> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Viewport> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Viewport getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
