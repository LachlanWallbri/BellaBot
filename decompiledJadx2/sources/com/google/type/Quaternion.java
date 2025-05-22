package com.google.type;

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
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class Quaternion extends GeneratedMessageV3 implements QuaternionOrBuilder {
    private static final Quaternion DEFAULT_INSTANCE = new Quaternion();
    private static final Parser<Quaternion> PARSER = new AbstractParser<Quaternion>() { // from class: com.google.type.Quaternion.1
        @Override // com.google.protobuf.Parser
        public Quaternion parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Quaternion(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int W_FIELD_NUMBER = 4;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 2;
    public static final int Z_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;

    /* renamed from: w_ */
    private double f2090w_;

    /* renamed from: x_ */
    private double f2091x_;

    /* renamed from: y_ */
    private double f2092y_;

    /* renamed from: z_ */
    private double f2093z_;

    private Quaternion(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Quaternion() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Quaternion();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Quaternion(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 9) {
                            this.f2091x_ = codedInputStream.readDouble();
                        } else if (readTag == 17) {
                            this.f2092y_ = codedInputStream.readDouble();
                        } else if (readTag == 25) {
                            this.f2093z_ = codedInputStream.readDouble();
                        } else if (readTag == 33) {
                            this.f2090w_ = codedInputStream.readDouble();
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
        return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return QuaternionProto.internal_static_google_type_Quaternion_fieldAccessorTable.ensureFieldAccessorsInitialized(Quaternion.class, Builder.class);
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getX() {
        return this.f2091x_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getY() {
        return this.f2092y_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getZ() {
        return this.f2093z_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getW() {
        return this.f2090w_;
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
        double d = this.f2091x_;
        if (d != 0.0d) {
            codedOutputStream.writeDouble(1, d);
        }
        double d2 = this.f2092y_;
        if (d2 != 0.0d) {
            codedOutputStream.writeDouble(2, d2);
        }
        double d3 = this.f2093z_;
        if (d3 != 0.0d) {
            codedOutputStream.writeDouble(3, d3);
        }
        double d4 = this.f2090w_;
        if (d4 != 0.0d) {
            codedOutputStream.writeDouble(4, d4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        double d = this.f2091x_;
        int computeDoubleSize = d != 0.0d ? 0 + CodedOutputStream.computeDoubleSize(1, d) : 0;
        double d2 = this.f2092y_;
        if (d2 != 0.0d) {
            computeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
        }
        double d3 = this.f2093z_;
        if (d3 != 0.0d) {
            computeDoubleSize += CodedOutputStream.computeDoubleSize(3, d3);
        }
        double d4 = this.f2090w_;
        if (d4 != 0.0d) {
            computeDoubleSize += CodedOutputStream.computeDoubleSize(4, d4);
        }
        int serializedSize = computeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Quaternion)) {
            return super.equals(obj);
        }
        Quaternion quaternion = (Quaternion) obj;
        return Double.doubleToLongBits(getX()) == Double.doubleToLongBits(quaternion.getX()) && Double.doubleToLongBits(getY()) == Double.doubleToLongBits(quaternion.getY()) && Double.doubleToLongBits(getZ()) == Double.doubleToLongBits(quaternion.getZ()) && Double.doubleToLongBits(getW()) == Double.doubleToLongBits(quaternion.getW()) && this.unknownFields.equals(quaternion.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getX()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getY()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getZ()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getW()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Quaternion parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Quaternion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Quaternion parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Quaternion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Quaternion parseFrom(InputStream inputStream) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Quaternion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Quaternion quaternion) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(quaternion);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QuaternionOrBuilder {

        /* renamed from: w_ */
        private double f2094w_;

        /* renamed from: x_ */
        private double f2095x_;

        /* renamed from: y_ */
        private double f2096y_;

        /* renamed from: z_ */
        private double f2097z_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuaternionProto.internal_static_google_type_Quaternion_fieldAccessorTable.ensureFieldAccessorsInitialized(Quaternion.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Quaternion.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.f2095x_ = 0.0d;
            this.f2096y_ = 0.0d;
            this.f2097z_ = 0.0d;
            this.f2094w_ = 0.0d;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Quaternion getDefaultInstanceForType() {
            return Quaternion.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Quaternion build() {
            Quaternion buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Quaternion buildPartial() {
            Quaternion quaternion = new Quaternion(this);
            quaternion.f2091x_ = this.f2095x_;
            quaternion.f2092y_ = this.f2096y_;
            quaternion.f2093z_ = this.f2097z_;
            quaternion.f2090w_ = this.f2094w_;
            onBuilt();
            return quaternion;
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
            if (message instanceof Quaternion) {
                return mergeFrom((Quaternion) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Quaternion quaternion) {
            if (quaternion == Quaternion.getDefaultInstance()) {
                return this;
            }
            if (quaternion.getX() != 0.0d) {
                setX(quaternion.getX());
            }
            if (quaternion.getY() != 0.0d) {
                setY(quaternion.getY());
            }
            if (quaternion.getZ() != 0.0d) {
                setZ(quaternion.getZ());
            }
            if (quaternion.getW() != 0.0d) {
                setW(quaternion.getW());
            }
            mergeUnknownFields(quaternion.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Quaternion quaternion = null;
            try {
                try {
                    Quaternion quaternion2 = (Quaternion) Quaternion.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (quaternion2 != null) {
                        mergeFrom(quaternion2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Quaternion quaternion3 = (Quaternion) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        quaternion = quaternion3;
                        if (quaternion != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (quaternion != null) {
                    mergeFrom(quaternion);
                }
                throw th;
            }
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getX() {
            return this.f2095x_;
        }

        public Builder setX(double d) {
            this.f2095x_ = d;
            onChanged();
            return this;
        }

        public Builder clearX() {
            this.f2095x_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getY() {
            return this.f2096y_;
        }

        public Builder setY(double d) {
            this.f2096y_ = d;
            onChanged();
            return this;
        }

        public Builder clearY() {
            this.f2096y_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getZ() {
            return this.f2097z_;
        }

        public Builder setZ(double d) {
            this.f2097z_ = d;
            onChanged();
            return this;
        }

        public Builder clearZ() {
            this.f2097z_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getW() {
            return this.f2094w_;
        }

        public Builder setW(double d) {
            this.f2094w_ = d;
            onChanged();
            return this;
        }

        public Builder clearW() {
            this.f2094w_ = 0.0d;
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

    public static Quaternion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Quaternion> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Quaternion> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Quaternion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
