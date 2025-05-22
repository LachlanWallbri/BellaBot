package com.google.type;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class Color extends GeneratedMessageV3 implements ColorOrBuilder {
    public static final int ALPHA_FIELD_NUMBER = 4;
    public static final int BLUE_FIELD_NUMBER = 3;
    public static final int GREEN_FIELD_NUMBER = 2;
    public static final int RED_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private FloatValue alpha_;
    private float blue_;
    private float green_;
    private byte memoizedIsInitialized;
    private float red_;
    private static final Color DEFAULT_INSTANCE = new Color();
    private static final Parser<Color> PARSER = new AbstractParser<Color>() { // from class: com.google.type.Color.1
        @Override // com.google.protobuf.Parser
        public Color parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Color(codedInputStream, extensionRegistryLite);
        }
    };

    private Color(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Color() {
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Color();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Color(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 13) {
                            this.red_ = codedInputStream.readFloat();
                        } else if (readTag == 21) {
                            this.green_ = codedInputStream.readFloat();
                        } else if (readTag == 29) {
                            this.blue_ = codedInputStream.readFloat();
                        } else if (readTag == 34) {
                            FloatValue.Builder builder = this.alpha_ != null ? this.alpha_.toBuilder() : null;
                            this.alpha_ = (FloatValue) codedInputStream.readMessage(FloatValue.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.alpha_);
                                this.alpha_ = builder.buildPartial();
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
        return ColorProto.internal_static_google_type_Color_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
    }

    @Override // com.google.type.ColorOrBuilder
    public float getRed() {
        return this.red_;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getGreen() {
        return this.green_;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getBlue() {
        return this.blue_;
    }

    @Override // com.google.type.ColorOrBuilder
    public boolean hasAlpha() {
        return this.alpha_ != null;
    }

    @Override // com.google.type.ColorOrBuilder
    public FloatValue getAlpha() {
        FloatValue floatValue = this.alpha_;
        return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
    }

    @Override // com.google.type.ColorOrBuilder
    public FloatValueOrBuilder getAlphaOrBuilder() {
        return getAlpha();
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
        float f = this.red_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(1, f);
        }
        float f2 = this.green_;
        if (f2 != 0.0f) {
            codedOutputStream.writeFloat(2, f2);
        }
        float f3 = this.blue_;
        if (f3 != 0.0f) {
            codedOutputStream.writeFloat(3, f3);
        }
        if (this.alpha_ != null) {
            codedOutputStream.writeMessage(4, getAlpha());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        float f = this.red_;
        int computeFloatSize = f != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, f) : 0;
        float f2 = this.green_;
        if (f2 != 0.0f) {
            computeFloatSize += CodedOutputStream.computeFloatSize(2, f2);
        }
        float f3 = this.blue_;
        if (f3 != 0.0f) {
            computeFloatSize += CodedOutputStream.computeFloatSize(3, f3);
        }
        if (this.alpha_ != null) {
            computeFloatSize += CodedOutputStream.computeMessageSize(4, getAlpha());
        }
        int serializedSize = computeFloatSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Color)) {
            return super.equals(obj);
        }
        Color color = (Color) obj;
        if (Float.floatToIntBits(getRed()) == Float.floatToIntBits(color.getRed()) && Float.floatToIntBits(getGreen()) == Float.floatToIntBits(color.getGreen()) && Float.floatToIntBits(getBlue()) == Float.floatToIntBits(color.getBlue()) && hasAlpha() == color.hasAlpha()) {
            return (!hasAlpha() || getAlpha().equals(color.getAlpha())) && this.unknownFields.equals(color.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Float.floatToIntBits(getRed())) * 37) + 2) * 53) + Float.floatToIntBits(getGreen())) * 37) + 3) * 53) + Float.floatToIntBits(getBlue());
        if (hasAlpha()) {
            hashCode = (((hashCode * 37) + 4) * 53) + getAlpha().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Color parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Color parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Color parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Color parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Color parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Color parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Color parseFrom(InputStream inputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Color parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Color parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Color parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Color parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Color parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Color color) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(color);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ColorOrBuilder {
        private SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> alphaBuilder_;
        private FloatValue alpha_;
        private float blue_;
        private float green_;
        private float red_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Color.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.red_ = 0.0f;
            this.green_ = 0.0f;
            this.blue_ = 0.0f;
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Color getDefaultInstanceForType() {
            return Color.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Color build() {
            Color buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Color buildPartial() {
            Color color = new Color(this);
            color.red_ = this.red_;
            color.green_ = this.green_;
            color.blue_ = this.blue_;
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                color.alpha_ = this.alpha_;
            } else {
                color.alpha_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return color;
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
            if (message instanceof Color) {
                return mergeFrom((Color) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Color color) {
            if (color == Color.getDefaultInstance()) {
                return this;
            }
            if (color.getRed() != 0.0f) {
                setRed(color.getRed());
            }
            if (color.getGreen() != 0.0f) {
                setGreen(color.getGreen());
            }
            if (color.getBlue() != 0.0f) {
                setBlue(color.getBlue());
            }
            if (color.hasAlpha()) {
                mergeAlpha(color.getAlpha());
            }
            mergeUnknownFields(color.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Color color = null;
            try {
                try {
                    Color color2 = (Color) Color.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (color2 != null) {
                        mergeFrom(color2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Color color3 = (Color) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        color = color3;
                        if (color != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (color != null) {
                    mergeFrom(color);
                }
                throw th;
            }
        }

        @Override // com.google.type.ColorOrBuilder
        public float getRed() {
            return this.red_;
        }

        public Builder setRed(float f) {
            this.red_ = f;
            onChanged();
            return this;
        }

        public Builder clearRed() {
            this.red_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public float getGreen() {
            return this.green_;
        }

        public Builder setGreen(float f) {
            this.green_ = f;
            onChanged();
            return this;
        }

        public Builder clearGreen() {
            this.green_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public float getBlue() {
            return this.blue_;
        }

        public Builder setBlue(float f) {
            this.blue_ = f;
            onChanged();
            return this;
        }

        public Builder clearBlue() {
            this.blue_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public boolean hasAlpha() {
            return (this.alphaBuilder_ == null && this.alpha_ == null) ? false : true;
        }

        @Override // com.google.type.ColorOrBuilder
        public FloatValue getAlpha() {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                FloatValue floatValue = this.alpha_;
                return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setAlpha(FloatValue floatValue) {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(floatValue);
            } else {
                if (floatValue == null) {
                    throw new NullPointerException();
                }
                this.alpha_ = floatValue;
                onChanged();
            }
            return this;
        }

        public Builder setAlpha(FloatValue.Builder builder) {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.alpha_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAlpha(FloatValue floatValue) {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                FloatValue floatValue2 = this.alpha_;
                if (floatValue2 != null) {
                    this.alpha_ = FloatValue.newBuilder(floatValue2).mergeFrom(floatValue).buildPartial();
                } else {
                    this.alpha_ = floatValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(floatValue);
            }
            return this;
        }

        public Builder clearAlpha() {
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
                onChanged();
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        public FloatValue.Builder getAlphaBuilder() {
            onChanged();
            return getAlphaFieldBuilder().getBuilder();
        }

        @Override // com.google.type.ColorOrBuilder
        public FloatValueOrBuilder getAlphaOrBuilder() {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            FloatValue floatValue = this.alpha_;
            return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
        }

        private SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> getAlphaFieldBuilder() {
            if (this.alphaBuilder_ == null) {
                this.alphaBuilder_ = new SingleFieldBuilderV3<>(getAlpha(), getParentForChildren(), isClean());
                this.alpha_ = null;
            }
            return this.alphaBuilder_;
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

    public static Color getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Color> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Color> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Color getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
