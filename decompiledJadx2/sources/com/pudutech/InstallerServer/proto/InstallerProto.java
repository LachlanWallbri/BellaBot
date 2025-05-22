package com.pudutech.installerserver.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public final class InstallerProto {
    private static Descriptors.FileDescriptor descriptor;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_Command_descriptor */
    private static final Descriptors.Descriptor f5339xd7dcf794;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_Command_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5340x1df54a12;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_InstallerProtocol_descriptor */
    private static final Descriptors.Descriptor f5341xacfbd03f;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_InstallerProtocol_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5342xbc7247bd;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_LidarPolar_descriptor */
    private static final Descriptors.Descriptor f5343xcc9c8fd7;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_LidarPolar_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5344x5385ef55;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_LidarScan_descriptor */
    private static final Descriptors.Descriptor f5345x46e694ca;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_LidarScan_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5346xb2815148;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_RobotPose_descriptor */
    private static final Descriptors.Descriptor f5347x32c4da84;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_RobotPose_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5348xc033d02;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_Vector3_descriptor */
    private static final Descriptors.Descriptor f5349x8887ca6f;

    /* renamed from: internal_static_com_pudutech_installerserver_proto_Vector3_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5350x7e3911ed;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface CommandOrBuilder extends MessageOrBuilder {
        boolean getShow();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface InstallerProtocolOrBuilder extends MessageOrBuilder {
        Command getCmd();

        CommandOrBuilder getCmdOrBuilder();

        int getMainVersion();

        RobotPose getPose();

        RobotPoseOrBuilder getPoseOrBuilder();

        int getPro();

        LidarScan getScan();

        LidarScanOrBuilder getScanOrBuilder();

        InstallerProtocol.ProType getType();

        int getTypeValue();

        boolean hasCmd();

        boolean hasPose();

        boolean hasScan();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface LidarPolarOrBuilder extends MessageOrBuilder {
        int getX();

        int getY();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface LidarScanOrBuilder extends MessageOrBuilder {
        LidarPolar getScans(int i);

        int getScansCount();

        List<LidarPolar> getScansList();

        LidarPolarOrBuilder getScansOrBuilder(int i);

        List<? extends LidarPolarOrBuilder> getScansOrBuilderList();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface RobotPoseOrBuilder extends MessageOrBuilder {
        Vector3 getDirection();

        Vector3OrBuilder getDirectionOrBuilder();

        Vector3 getPosition();

        Vector3OrBuilder getPositionOrBuilder();

        boolean hasDirection();

        boolean hasPosition();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface Vector3OrBuilder extends MessageOrBuilder {
        double getX();

        double getY();

        double getZ();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private InstallerProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class LidarPolar extends GeneratedMessageV3 implements LidarPolarOrBuilder {
        private static final LidarPolar DEFAULT_INSTANCE = new LidarPolar();
        private static final Parser<LidarPolar> PARSER = new AbstractParser<LidarPolar>() { // from class: com.pudutech.installerserver.proto.InstallerProto.LidarPolar.1
            @Override // com.google.protobuf.Parser
            public LidarPolar parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LidarPolar(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int X_FIELD_NUMBER = 1;
        public static final int Y_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        /* renamed from: x_ */
        private int f5351x_;

        /* renamed from: y_ */
        private int f5352y_;

        private LidarPolar(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private LidarPolar() {
            this.memoizedIsInitialized = (byte) -1;
            this.f5351x_ = 0;
            this.f5352y_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LidarPolar(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 8) {
                                    this.f5351x_ = codedInputStream.readInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.f5352y_ = codedInputStream.readInt32();
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
            return InstallerProto.f5343xcc9c8fd7;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return InstallerProto.f5344x5385ef55.ensureFieldAccessorsInitialized(LidarPolar.class, Builder.class);
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarPolarOrBuilder
        public int getX() {
            return this.f5351x_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarPolarOrBuilder
        public int getY() {
            return this.f5352y_;
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
            int i = this.f5351x_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            int i2 = this.f5352y_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f5351x_;
            int computeInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0;
            int i3 = this.f5352y_;
            if (i3 != 0) {
                computeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
            }
            int serializedSize = computeInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LidarPolar)) {
                return super.equals(obj);
            }
            LidarPolar lidarPolar = (LidarPolar) obj;
            return ((getX() == lidarPolar.getX()) && getY() == lidarPolar.getY()) && this.unknownFields.equals(lidarPolar.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getX()) * 37) + 2) * 53) + getY()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static LidarPolar parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static LidarPolar parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LidarPolar parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static LidarPolar parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LidarPolar parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static LidarPolar parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LidarPolar parseFrom(InputStream inputStream) throws IOException {
            return (LidarPolar) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LidarPolar parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LidarPolar) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LidarPolar parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LidarPolar) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LidarPolar parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LidarPolar) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LidarPolar parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LidarPolar) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LidarPolar parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LidarPolar) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LidarPolar lidarPolar) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(lidarPolar);
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
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LidarPolarOrBuilder {

            /* renamed from: x_ */
            private int f5353x_;

            /* renamed from: y_ */
            private int f5354y_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return InstallerProto.f5343xcc9c8fd7;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return InstallerProto.f5344x5385ef55.ensureFieldAccessorsInitialized(LidarPolar.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LidarPolar.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.f5353x_ = 0;
                this.f5354y_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InstallerProto.f5343xcc9c8fd7;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LidarPolar getDefaultInstanceForType() {
                return LidarPolar.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LidarPolar build() {
                LidarPolar buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LidarPolar buildPartial() {
                LidarPolar lidarPolar = new LidarPolar(this);
                lidarPolar.f5351x_ = this.f5353x_;
                lidarPolar.f5352y_ = this.f5354y_;
                onBuilt();
                return lidarPolar;
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
                if (message instanceof LidarPolar) {
                    return mergeFrom((LidarPolar) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LidarPolar lidarPolar) {
                if (lidarPolar == LidarPolar.getDefaultInstance()) {
                    return this;
                }
                if (lidarPolar.getX() != 0) {
                    setX(lidarPolar.getX());
                }
                if (lidarPolar.getY() != 0) {
                    setY(lidarPolar.getY());
                }
                mergeUnknownFields(lidarPolar.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LidarPolar lidarPolar = null;
                try {
                    try {
                        LidarPolar lidarPolar2 = (LidarPolar) LidarPolar.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (lidarPolar2 != null) {
                            mergeFrom(lidarPolar2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        LidarPolar lidarPolar3 = (LidarPolar) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            lidarPolar = lidarPolar3;
                            if (lidarPolar != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (lidarPolar != null) {
                        mergeFrom(lidarPolar);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarPolarOrBuilder
            public int getX() {
                return this.f5353x_;
            }

            public Builder setX(int i) {
                this.f5353x_ = i;
                onChanged();
                return this;
            }

            public Builder clearX() {
                this.f5353x_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarPolarOrBuilder
            public int getY() {
                return this.f5354y_;
            }

            public Builder setY(int i) {
                this.f5354y_ = i;
                onChanged();
                return this;
            }

            public Builder clearY() {
                this.f5354y_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static LidarPolar getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LidarPolar> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LidarPolar> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LidarPolar getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class LidarScan extends GeneratedMessageV3 implements LidarScanOrBuilder {
        private static final LidarScan DEFAULT_INSTANCE = new LidarScan();
        private static final Parser<LidarScan> PARSER = new AbstractParser<LidarScan>() { // from class: com.pudutech.installerserver.proto.InstallerProto.LidarScan.1
            @Override // com.google.protobuf.Parser
            public LidarScan parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LidarScan(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SCANS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<LidarPolar> scans_;

        private LidarScan(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private LidarScan() {
            this.memoizedIsInitialized = (byte) -1;
            this.scans_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private LidarScan(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                if (z) {
                    break;
                }
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    if (!(z2 & true)) {
                                        this.scans_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.scans_.add(codedInputStream.readMessage(LidarPolar.parser(), extensionRegistryLite));
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.scans_ = Collections.unmodifiableList(this.scans_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InstallerProto.f5345x46e694ca;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return InstallerProto.f5346xb2815148.ensureFieldAccessorsInitialized(LidarScan.class, Builder.class);
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
        public List<LidarPolar> getScansList() {
            return this.scans_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
        public List<? extends LidarPolarOrBuilder> getScansOrBuilderList() {
            return this.scans_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
        public int getScansCount() {
            return this.scans_.size();
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
        public LidarPolar getScans(int i) {
            return this.scans_.get(i);
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
        public LidarPolarOrBuilder getScansOrBuilder(int i) {
            return this.scans_.get(i);
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
            for (int i = 0; i < this.scans_.size(); i++) {
                codedOutputStream.writeMessage(1, this.scans_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.scans_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.scans_.get(i3));
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LidarScan)) {
                return super.equals(obj);
            }
            LidarScan lidarScan = (LidarScan) obj;
            return (getScansList().equals(lidarScan.getScansList())) && this.unknownFields.equals(lidarScan.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getScansCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getScansList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static LidarScan parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static LidarScan parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LidarScan parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static LidarScan parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LidarScan parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static LidarScan parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LidarScan parseFrom(InputStream inputStream) throws IOException {
            return (LidarScan) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LidarScan parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LidarScan) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LidarScan parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LidarScan) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LidarScan parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LidarScan) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LidarScan parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LidarScan) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LidarScan parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LidarScan) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LidarScan lidarScan) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(lidarScan);
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
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LidarScanOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> scansBuilder_;
            private List<LidarPolar> scans_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return InstallerProto.f5345x46e694ca;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return InstallerProto.f5346xb2815148.ensureFieldAccessorsInitialized(LidarScan.class, Builder.class);
            }

            private Builder() {
                this.scans_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.scans_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (LidarScan.alwaysUseFieldBuilders) {
                    getScansFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.scans_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InstallerProto.f5345x46e694ca;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LidarScan getDefaultInstanceForType() {
                return LidarScan.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LidarScan build() {
                LidarScan buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LidarScan buildPartial() {
                LidarScan lidarScan = new LidarScan(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.scans_ = Collections.unmodifiableList(this.scans_);
                        this.bitField0_ &= -2;
                    }
                    lidarScan.scans_ = this.scans_;
                } else {
                    lidarScan.scans_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return lidarScan;
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
                if (message instanceof LidarScan) {
                    return mergeFrom((LidarScan) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LidarScan lidarScan) {
                if (lidarScan == LidarScan.getDefaultInstance()) {
                    return this;
                }
                if (this.scansBuilder_ == null) {
                    if (!lidarScan.scans_.isEmpty()) {
                        if (this.scans_.isEmpty()) {
                            this.scans_ = lidarScan.scans_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureScansIsMutable();
                            this.scans_.addAll(lidarScan.scans_);
                        }
                        onChanged();
                    }
                } else if (!lidarScan.scans_.isEmpty()) {
                    if (!this.scansBuilder_.isEmpty()) {
                        this.scansBuilder_.addAllMessages(lidarScan.scans_);
                    } else {
                        this.scansBuilder_.dispose();
                        this.scansBuilder_ = null;
                        this.scans_ = lidarScan.scans_;
                        this.bitField0_ &= -2;
                        this.scansBuilder_ = LidarScan.alwaysUseFieldBuilders ? getScansFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(lidarScan.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LidarScan lidarScan = null;
                try {
                    try {
                        LidarScan lidarScan2 = (LidarScan) LidarScan.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (lidarScan2 != null) {
                            mergeFrom(lidarScan2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        LidarScan lidarScan3 = (LidarScan) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            lidarScan = lidarScan3;
                            if (lidarScan != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (lidarScan != null) {
                        mergeFrom(lidarScan);
                    }
                    throw th;
                }
            }

            private void ensureScansIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.scans_ = new ArrayList(this.scans_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
            public List<LidarPolar> getScansList() {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.scans_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
            public int getScansCount() {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.scans_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
            public LidarPolar getScans(int i) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.scans_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setScans(int i, LidarPolar lidarPolar) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, lidarPolar);
                } else {
                    if (lidarPolar == null) {
                        throw new NullPointerException();
                    }
                    ensureScansIsMutable();
                    this.scans_.set(i, lidarPolar);
                    onChanged();
                }
                return this;
            }

            public Builder setScans(int i, LidarPolar.Builder builder) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureScansIsMutable();
                    this.scans_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addScans(LidarPolar lidarPolar) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(lidarPolar);
                } else {
                    if (lidarPolar == null) {
                        throw new NullPointerException();
                    }
                    ensureScansIsMutable();
                    this.scans_.add(lidarPolar);
                    onChanged();
                }
                return this;
            }

            public Builder addScans(int i, LidarPolar lidarPolar) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, lidarPolar);
                } else {
                    if (lidarPolar == null) {
                        throw new NullPointerException();
                    }
                    ensureScansIsMutable();
                    this.scans_.add(i, lidarPolar);
                    onChanged();
                }
                return this;
            }

            public Builder addScans(LidarPolar.Builder builder) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureScansIsMutable();
                    this.scans_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addScans(int i, LidarPolar.Builder builder) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureScansIsMutable();
                    this.scans_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllScans(Iterable<? extends LidarPolar> iterable) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureScansIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.scans_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearScans() {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.scans_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeScans(int i) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureScansIsMutable();
                    this.scans_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public LidarPolar.Builder getScansBuilder(int i) {
                return getScansFieldBuilder().getBuilder(i);
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
            public LidarPolarOrBuilder getScansOrBuilder(int i) {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.scans_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.LidarScanOrBuilder
            public List<? extends LidarPolarOrBuilder> getScansOrBuilderList() {
                RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> repeatedFieldBuilderV3 = this.scansBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.scans_);
            }

            public LidarPolar.Builder addScansBuilder() {
                return getScansFieldBuilder().addBuilder(LidarPolar.getDefaultInstance());
            }

            public LidarPolar.Builder addScansBuilder(int i) {
                return getScansFieldBuilder().addBuilder(i, LidarPolar.getDefaultInstance());
            }

            public List<LidarPolar.Builder> getScansBuilderList() {
                return getScansFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<LidarPolar, LidarPolar.Builder, LidarPolarOrBuilder> getScansFieldBuilder() {
                if (this.scansBuilder_ == null) {
                    this.scansBuilder_ = new RepeatedFieldBuilderV3<>(this.scans_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                    this.scans_ = null;
                }
                return this.scansBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static LidarScan getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LidarScan> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LidarScan> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LidarScan getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class Vector3 extends GeneratedMessageV3 implements Vector3OrBuilder {
        private static final Vector3 DEFAULT_INSTANCE = new Vector3();
        private static final Parser<Vector3> PARSER = new AbstractParser<Vector3>() { // from class: com.pudutech.installerserver.proto.InstallerProto.Vector3.1
            @Override // com.google.protobuf.Parser
            public Vector3 parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Vector3(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int X_FIELD_NUMBER = 1;
        public static final int Y_FIELD_NUMBER = 2;
        public static final int Z_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        /* renamed from: x_ */
        private double f5355x_;

        /* renamed from: y_ */
        private double f5356y_;

        /* renamed from: z_ */
        private double f5357z_;

        private Vector3(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Vector3() {
            this.memoizedIsInitialized = (byte) -1;
            this.f5355x_ = 0.0d;
            this.f5356y_ = 0.0d;
            this.f5357z_ = 0.0d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Vector3(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.f5355x_ = codedInputStream.readDouble();
                            } else if (readTag == 17) {
                                this.f5356y_ = codedInputStream.readDouble();
                            } else if (readTag != 25) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.f5357z_ = codedInputStream.readDouble();
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
            return InstallerProto.f5349x8887ca6f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return InstallerProto.f5350x7e3911ed.ensureFieldAccessorsInitialized(Vector3.class, Builder.class);
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.Vector3OrBuilder
        public double getX() {
            return this.f5355x_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.Vector3OrBuilder
        public double getY() {
            return this.f5356y_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.Vector3OrBuilder
        public double getZ() {
            return this.f5357z_;
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
            double d = this.f5355x_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            double d2 = this.f5356y_;
            if (d2 != 0.0d) {
                codedOutputStream.writeDouble(2, d2);
            }
            double d3 = this.f5357z_;
            if (d3 != 0.0d) {
                codedOutputStream.writeDouble(3, d3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.f5355x_;
            int computeDoubleSize = d != 0.0d ? 0 + CodedOutputStream.computeDoubleSize(1, d) : 0;
            double d2 = this.f5356y_;
            if (d2 != 0.0d) {
                computeDoubleSize += CodedOutputStream.computeDoubleSize(2, d2);
            }
            double d3 = this.f5357z_;
            if (d3 != 0.0d) {
                computeDoubleSize += CodedOutputStream.computeDoubleSize(3, d3);
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
            if (!(obj instanceof Vector3)) {
                return super.equals(obj);
            }
            Vector3 vector3 = (Vector3) obj;
            return ((((Double.doubleToLongBits(getX()) > Double.doubleToLongBits(vector3.getX()) ? 1 : (Double.doubleToLongBits(getX()) == Double.doubleToLongBits(vector3.getX()) ? 0 : -1)) == 0) && (Double.doubleToLongBits(getY()) > Double.doubleToLongBits(vector3.getY()) ? 1 : (Double.doubleToLongBits(getY()) == Double.doubleToLongBits(vector3.getY()) ? 0 : -1)) == 0) && (Double.doubleToLongBits(getZ()) > Double.doubleToLongBits(vector3.getZ()) ? 1 : (Double.doubleToLongBits(getZ()) == Double.doubleToLongBits(vector3.getZ()) ? 0 : -1)) == 0) && this.unknownFields.equals(vector3.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getX()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getY()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getZ()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Vector3 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Vector3 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Vector3 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Vector3 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Vector3 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Vector3 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Vector3 parseFrom(InputStream inputStream) throws IOException {
            return (Vector3) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Vector3 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Vector3) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Vector3 parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Vector3) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Vector3 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Vector3) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Vector3 parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Vector3) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Vector3 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Vector3) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Vector3 vector3) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(vector3);
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
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Vector3OrBuilder {

            /* renamed from: x_ */
            private double f5358x_;

            /* renamed from: y_ */
            private double f5359y_;

            /* renamed from: z_ */
            private double f5360z_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return InstallerProto.f5349x8887ca6f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return InstallerProto.f5350x7e3911ed.ensureFieldAccessorsInitialized(Vector3.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Vector3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.f5358x_ = 0.0d;
                this.f5359y_ = 0.0d;
                this.f5360z_ = 0.0d;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InstallerProto.f5349x8887ca6f;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Vector3 getDefaultInstanceForType() {
                return Vector3.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Vector3 build() {
                Vector3 buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Vector3 buildPartial() {
                Vector3 vector3 = new Vector3(this);
                vector3.f5355x_ = this.f5358x_;
                vector3.f5356y_ = this.f5359y_;
                vector3.f5357z_ = this.f5360z_;
                onBuilt();
                return vector3;
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
                if (message instanceof Vector3) {
                    return mergeFrom((Vector3) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Vector3 vector3) {
                if (vector3 == Vector3.getDefaultInstance()) {
                    return this;
                }
                if (vector3.getX() != 0.0d) {
                    setX(vector3.getX());
                }
                if (vector3.getY() != 0.0d) {
                    setY(vector3.getY());
                }
                if (vector3.getZ() != 0.0d) {
                    setZ(vector3.getZ());
                }
                mergeUnknownFields(vector3.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Vector3 vector3 = null;
                try {
                    try {
                        Vector3 vector32 = (Vector3) Vector3.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (vector32 != null) {
                            mergeFrom(vector32);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Vector3 vector33 = (Vector3) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            vector3 = vector33;
                            if (vector3 != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (vector3 != null) {
                        mergeFrom(vector3);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.Vector3OrBuilder
            public double getX() {
                return this.f5358x_;
            }

            public Builder setX(double d) {
                this.f5358x_ = d;
                onChanged();
                return this;
            }

            public Builder clearX() {
                this.f5358x_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.Vector3OrBuilder
            public double getY() {
                return this.f5359y_;
            }

            public Builder setY(double d) {
                this.f5359y_ = d;
                onChanged();
                return this;
            }

            public Builder clearY() {
                this.f5359y_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.Vector3OrBuilder
            public double getZ() {
                return this.f5360z_;
            }

            public Builder setZ(double d) {
                this.f5360z_ = d;
                onChanged();
                return this;
            }

            public Builder clearZ() {
                this.f5360z_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Vector3 getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Vector3> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Vector3> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Vector3 getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class RobotPose extends GeneratedMessageV3 implements RobotPoseOrBuilder {
        public static final int DIRECTION_FIELD_NUMBER = 2;
        public static final int POSITION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private Vector3 direction_;
        private byte memoizedIsInitialized;
        private Vector3 position_;
        private static final RobotPose DEFAULT_INSTANCE = new RobotPose();
        private static final Parser<RobotPose> PARSER = new AbstractParser<RobotPose>() { // from class: com.pudutech.installerserver.proto.InstallerProto.RobotPose.1
            @Override // com.google.protobuf.Parser
            public RobotPose parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RobotPose(codedInputStream, extensionRegistryLite);
            }
        };

        private RobotPose(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private RobotPose() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private RobotPose(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            Vector3.Builder builder;
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
                                builder = this.position_ != null ? this.position_.toBuilder() : null;
                                this.position_ = (Vector3) codedInputStream.readMessage(Vector3.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.position_);
                                    this.position_ = builder.buildPartial();
                                }
                            } else if (readTag != 18) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                builder = this.direction_ != null ? this.direction_.toBuilder() : null;
                                this.direction_ = (Vector3) codedInputStream.readMessage(Vector3.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.direction_);
                                    this.direction_ = builder.buildPartial();
                                }
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
            return InstallerProto.f5347x32c4da84;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return InstallerProto.f5348xc033d02.ensureFieldAccessorsInitialized(RobotPose.class, Builder.class);
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
        public boolean hasPosition() {
            return this.position_ != null;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
        public Vector3 getPosition() {
            Vector3 vector3 = this.position_;
            return vector3 == null ? Vector3.getDefaultInstance() : vector3;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
        public Vector3OrBuilder getPositionOrBuilder() {
            return getPosition();
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
        public boolean hasDirection() {
            return this.direction_ != null;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
        public Vector3 getDirection() {
            Vector3 vector3 = this.direction_;
            return vector3 == null ? Vector3.getDefaultInstance() : vector3;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
        public Vector3OrBuilder getDirectionOrBuilder() {
            return getDirection();
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
            if (this.position_ != null) {
                codedOutputStream.writeMessage(1, getPosition());
            }
            if (this.direction_ != null) {
                codedOutputStream.writeMessage(2, getDirection());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.position_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getPosition()) : 0;
            if (this.direction_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, getDirection());
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
            if (!(obj instanceof RobotPose)) {
                return super.equals(obj);
            }
            RobotPose robotPose = (RobotPose) obj;
            boolean z = hasPosition() == robotPose.hasPosition();
            if (hasPosition()) {
                z = z && getPosition().equals(robotPose.getPosition());
            }
            boolean z2 = z && hasDirection() == robotPose.hasDirection();
            if (hasDirection()) {
                z2 = z2 && getDirection().equals(robotPose.getDirection());
            }
            return z2 && this.unknownFields.equals(robotPose.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasPosition()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getPosition().hashCode();
            }
            if (hasDirection()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getDirection().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static RobotPose parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RobotPose parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RobotPose parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RobotPose parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RobotPose parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RobotPose parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RobotPose parseFrom(InputStream inputStream) throws IOException {
            return (RobotPose) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RobotPose parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotPose) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RobotPose parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RobotPose) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RobotPose parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotPose) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RobotPose parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RobotPose) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RobotPose parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotPose) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RobotPose robotPose) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(robotPose);
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
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RobotPoseOrBuilder {
            private SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> directionBuilder_;
            private Vector3 direction_;
            private SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> positionBuilder_;
            private Vector3 position_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return InstallerProto.f5347x32c4da84;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return InstallerProto.f5348xc033d02.ensureFieldAccessorsInitialized(RobotPose.class, Builder.class);
            }

            private Builder() {
                this.position_ = null;
                this.direction_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.position_ = null;
                this.direction_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RobotPose.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.positionBuilder_ == null) {
                    this.position_ = null;
                } else {
                    this.position_ = null;
                    this.positionBuilder_ = null;
                }
                if (this.directionBuilder_ == null) {
                    this.direction_ = null;
                } else {
                    this.direction_ = null;
                    this.directionBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InstallerProto.f5347x32c4da84;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public RobotPose getDefaultInstanceForType() {
                return RobotPose.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RobotPose build() {
                RobotPose buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RobotPose buildPartial() {
                RobotPose robotPose = new RobotPose(this);
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.positionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    robotPose.position_ = this.position_;
                } else {
                    robotPose.position_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV32 = this.directionBuilder_;
                if (singleFieldBuilderV32 == null) {
                    robotPose.direction_ = this.direction_;
                } else {
                    robotPose.direction_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return robotPose;
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
                if (message instanceof RobotPose) {
                    return mergeFrom((RobotPose) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RobotPose robotPose) {
                if (robotPose == RobotPose.getDefaultInstance()) {
                    return this;
                }
                if (robotPose.hasPosition()) {
                    mergePosition(robotPose.getPosition());
                }
                if (robotPose.hasDirection()) {
                    mergeDirection(robotPose.getDirection());
                }
                mergeUnknownFields(robotPose.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RobotPose robotPose = null;
                try {
                    try {
                        RobotPose robotPose2 = (RobotPose) RobotPose.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (robotPose2 != null) {
                            mergeFrom(robotPose2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        RobotPose robotPose3 = (RobotPose) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            robotPose = robotPose3;
                            if (robotPose != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (robotPose != null) {
                        mergeFrom(robotPose);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
            public boolean hasPosition() {
                return (this.positionBuilder_ == null && this.position_ == null) ? false : true;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
            public Vector3 getPosition() {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.positionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Vector3 vector3 = this.position_;
                    return vector3 == null ? Vector3.getDefaultInstance() : vector3;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPosition(Vector3 vector3) {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.positionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(vector3);
                } else {
                    if (vector3 == null) {
                        throw new NullPointerException();
                    }
                    this.position_ = vector3;
                    onChanged();
                }
                return this;
            }

            public Builder setPosition(Vector3.Builder builder) {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.positionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.position_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePosition(Vector3 vector3) {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.positionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Vector3 vector32 = this.position_;
                    if (vector32 != null) {
                        this.position_ = Vector3.newBuilder(vector32).mergeFrom(vector3).buildPartial();
                    } else {
                        this.position_ = vector3;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(vector3);
                }
                return this;
            }

            public Builder clearPosition() {
                if (this.positionBuilder_ == null) {
                    this.position_ = null;
                    onChanged();
                } else {
                    this.position_ = null;
                    this.positionBuilder_ = null;
                }
                return this;
            }

            public Vector3.Builder getPositionBuilder() {
                onChanged();
                return getPositionFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
            public Vector3OrBuilder getPositionOrBuilder() {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.positionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Vector3 vector3 = this.position_;
                return vector3 == null ? Vector3.getDefaultInstance() : vector3;
            }

            private SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> getPositionFieldBuilder() {
                if (this.positionBuilder_ == null) {
                    this.positionBuilder_ = new SingleFieldBuilderV3<>(getPosition(), getParentForChildren(), isClean());
                    this.position_ = null;
                }
                return this.positionBuilder_;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
            public boolean hasDirection() {
                return (this.directionBuilder_ == null && this.direction_ == null) ? false : true;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
            public Vector3 getDirection() {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.directionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Vector3 vector3 = this.direction_;
                    return vector3 == null ? Vector3.getDefaultInstance() : vector3;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setDirection(Vector3 vector3) {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.directionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(vector3);
                } else {
                    if (vector3 == null) {
                        throw new NullPointerException();
                    }
                    this.direction_ = vector3;
                    onChanged();
                }
                return this;
            }

            public Builder setDirection(Vector3.Builder builder) {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.directionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.direction_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeDirection(Vector3 vector3) {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.directionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Vector3 vector32 = this.direction_;
                    if (vector32 != null) {
                        this.direction_ = Vector3.newBuilder(vector32).mergeFrom(vector3).buildPartial();
                    } else {
                        this.direction_ = vector3;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(vector3);
                }
                return this;
            }

            public Builder clearDirection() {
                if (this.directionBuilder_ == null) {
                    this.direction_ = null;
                    onChanged();
                } else {
                    this.direction_ = null;
                    this.directionBuilder_ = null;
                }
                return this;
            }

            public Vector3.Builder getDirectionBuilder() {
                onChanged();
                return getDirectionFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.RobotPoseOrBuilder
            public Vector3OrBuilder getDirectionOrBuilder() {
                SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> singleFieldBuilderV3 = this.directionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Vector3 vector3 = this.direction_;
                return vector3 == null ? Vector3.getDefaultInstance() : vector3;
            }

            private SingleFieldBuilderV3<Vector3, Vector3.Builder, Vector3OrBuilder> getDirectionFieldBuilder() {
                if (this.directionBuilder_ == null) {
                    this.directionBuilder_ = new SingleFieldBuilderV3<>(getDirection(), getParentForChildren(), isClean());
                    this.direction_ = null;
                }
                return this.directionBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static RobotPose getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RobotPose> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<RobotPose> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RobotPose getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class Command extends GeneratedMessageV3 implements CommandOrBuilder {
        private static final Command DEFAULT_INSTANCE = new Command();
        private static final Parser<Command> PARSER = new AbstractParser<Command>() { // from class: com.pudutech.installerserver.proto.InstallerProto.Command.1
            @Override // com.google.protobuf.Parser
            public Command parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Command(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SHOW_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private boolean show_;

        private Command(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Command() {
            this.memoizedIsInitialized = (byte) -1;
            this.show_ = false;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Command(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 8) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.show_ = codedInputStream.readBool();
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
            return InstallerProto.f5339xd7dcf794;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return InstallerProto.f5340x1df54a12.ensureFieldAccessorsInitialized(Command.class, Builder.class);
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.CommandOrBuilder
        public boolean getShow() {
            return this.show_;
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
            boolean z = this.show_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.show_;
            int computeBoolSize = (z ? 0 + CodedOutputStream.computeBoolSize(1, z) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeBoolSize;
            return computeBoolSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Command)) {
                return super.equals(obj);
            }
            Command command = (Command) obj;
            return (getShow() == command.getShow()) && this.unknownFields.equals(command.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getShow())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Command parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Command parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Command parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Command parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Command parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Command parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Command parseFrom(InputStream inputStream) throws IOException {
            return (Command) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Command parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Command) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Command parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Command) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Command parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Command) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Command parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Command) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Command parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Command) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Command command) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(command);
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
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CommandOrBuilder {
            private boolean show_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return InstallerProto.f5339xd7dcf794;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return InstallerProto.f5340x1df54a12.ensureFieldAccessorsInitialized(Command.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Command.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.show_ = false;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InstallerProto.f5339xd7dcf794;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Command getDefaultInstanceForType() {
                return Command.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Command build() {
                Command buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Command buildPartial() {
                Command command = new Command(this);
                command.show_ = this.show_;
                onBuilt();
                return command;
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
                if (message instanceof Command) {
                    return mergeFrom((Command) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Command command) {
                if (command == Command.getDefaultInstance()) {
                    return this;
                }
                if (command.getShow()) {
                    setShow(command.getShow());
                }
                mergeUnknownFields(command.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Command command = null;
                try {
                    try {
                        Command command2 = (Command) Command.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (command2 != null) {
                            mergeFrom(command2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Command command3 = (Command) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            command = command3;
                            if (command != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (command != null) {
                        mergeFrom(command);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.CommandOrBuilder
            public boolean getShow() {
                return this.show_;
            }

            public Builder setShow(boolean z) {
                this.show_ = z;
                onChanged();
                return this;
            }

            public Builder clearShow() {
                this.show_ = false;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Command getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Command> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Command> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Command getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class InstallerProtocol extends GeneratedMessageV3 implements InstallerProtocolOrBuilder {
        public static final int CMD_FIELD_NUMBER = 4;
        public static final int MAIN_VERSION_FIELD_NUMBER = 6;
        public static final int POSE_FIELD_NUMBER = 3;
        public static final int PRO_FIELD_NUMBER = 5;
        public static final int SCAN_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private Command cmd_;
        private int mainVersion_;
        private byte memoizedIsInitialized;
        private RobotPose pose_;
        private int pro_;
        private LidarScan scan_;
        private int type_;
        private static final InstallerProtocol DEFAULT_INSTANCE = new InstallerProtocol();
        private static final Parser<InstallerProtocol> PARSER = new AbstractParser<InstallerProtocol>() { // from class: com.pudutech.installerserver.proto.InstallerProto.InstallerProtocol.1
            @Override // com.google.protobuf.Parser
            public InstallerProtocol parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new InstallerProtocol(codedInputStream, extensionRegistryLite);
            }
        };

        private InstallerProtocol(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private InstallerProtocol() {
            this.memoizedIsInitialized = (byte) -1;
            this.type_ = 0;
            this.pro_ = 0;
            this.mainVersion_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private InstallerProtocol(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 8) {
                                if (readTag == 18) {
                                    LidarScan.Builder builder = this.scan_ != null ? this.scan_.toBuilder() : null;
                                    this.scan_ = (LidarScan) codedInputStream.readMessage(LidarScan.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.scan_);
                                        this.scan_ = builder.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    RobotPose.Builder builder2 = this.pose_ != null ? this.pose_.toBuilder() : null;
                                    this.pose_ = (RobotPose) codedInputStream.readMessage(RobotPose.parser(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.mergeFrom(this.pose_);
                                        this.pose_ = builder2.buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    Command.Builder builder3 = this.cmd_ != null ? this.cmd_.toBuilder() : null;
                                    this.cmd_ = (Command) codedInputStream.readMessage(Command.parser(), extensionRegistryLite);
                                    if (builder3 != null) {
                                        builder3.mergeFrom(this.cmd_);
                                        this.cmd_ = builder3.buildPartial();
                                    }
                                } else if (readTag == 40) {
                                    this.pro_ = codedInputStream.readInt32();
                                } else if (readTag != 48) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.mainVersion_ = codedInputStream.readInt32();
                                }
                            } else {
                                this.type_ = codedInputStream.readEnum();
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
            return InstallerProto.f5341xacfbd03f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return InstallerProto.f5342xbc7247bd.ensureFieldAccessorsInitialized(InstallerProtocol.class, Builder.class);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public enum ProType implements ProtocolMessageEnum {
            Lidar(0),
            Pose(1),
            Cmd(2),
            Product(3),
            UNRECOGNIZED(-1);

            public static final int Cmd_VALUE = 2;
            public static final int Lidar_VALUE = 0;
            public static final int Pose_VALUE = 1;
            public static final int Product_VALUE = 3;
            private final int value;
            private static final Internal.EnumLiteMap<ProType> internalValueMap = new Internal.EnumLiteMap<ProType>() { // from class: com.pudutech.installerserver.proto.InstallerProto.InstallerProtocol.ProType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ProType findValueByNumber(int i) {
                    return ProType.forNumber(i);
                }
            };
            private static final ProType[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static ProType valueOf(int i) {
                return forNumber(i);
            }

            public static ProType forNumber(int i) {
                if (i == 0) {
                    return Lidar;
                }
                if (i == 1) {
                    return Pose;
                }
                if (i == 2) {
                    return Cmd;
                }
                if (i != 3) {
                    return null;
                }
                return Product;
            }

            public static Internal.EnumLiteMap<ProType> internalGetValueMap() {
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
                return InstallerProtocol.getDescriptor().getEnumTypes().get(0);
            }

            public static ProType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            ProType(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public ProType getType() {
            ProType valueOf = ProType.valueOf(this.type_);
            return valueOf == null ? ProType.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public boolean hasScan() {
            return this.scan_ != null;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public LidarScan getScan() {
            LidarScan lidarScan = this.scan_;
            return lidarScan == null ? LidarScan.getDefaultInstance() : lidarScan;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public LidarScanOrBuilder getScanOrBuilder() {
            return getScan();
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public boolean hasPose() {
            return this.pose_ != null;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public RobotPose getPose() {
            RobotPose robotPose = this.pose_;
            return robotPose == null ? RobotPose.getDefaultInstance() : robotPose;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public RobotPoseOrBuilder getPoseOrBuilder() {
            return getPose();
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public boolean hasCmd() {
            return this.cmd_ != null;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public Command getCmd() {
            Command command = this.cmd_;
            return command == null ? Command.getDefaultInstance() : command;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public CommandOrBuilder getCmdOrBuilder() {
            return getCmd();
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public int getPro() {
            return this.pro_;
        }

        @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
        public int getMainVersion() {
            return this.mainVersion_;
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
            if (this.type_ != ProType.Lidar.getNumber()) {
                codedOutputStream.writeEnum(1, this.type_);
            }
            if (this.scan_ != null) {
                codedOutputStream.writeMessage(2, getScan());
            }
            if (this.pose_ != null) {
                codedOutputStream.writeMessage(3, getPose());
            }
            if (this.cmd_ != null) {
                codedOutputStream.writeMessage(4, getCmd());
            }
            int i = this.pro_;
            if (i != 0) {
                codedOutputStream.writeInt32(5, i);
            }
            int i2 = this.mainVersion_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(6, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.type_ != ProType.Lidar.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.type_) : 0;
            if (this.scan_ != null) {
                computeEnumSize += CodedOutputStream.computeMessageSize(2, getScan());
            }
            if (this.pose_ != null) {
                computeEnumSize += CodedOutputStream.computeMessageSize(3, getPose());
            }
            if (this.cmd_ != null) {
                computeEnumSize += CodedOutputStream.computeMessageSize(4, getCmd());
            }
            int i2 = this.pro_;
            if (i2 != 0) {
                computeEnumSize += CodedOutputStream.computeInt32Size(5, i2);
            }
            int i3 = this.mainVersion_;
            if (i3 != 0) {
                computeEnumSize += CodedOutputStream.computeInt32Size(6, i3);
            }
            int serializedSize = computeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof InstallerProtocol)) {
                return super.equals(obj);
            }
            InstallerProtocol installerProtocol = (InstallerProtocol) obj;
            boolean z = (this.type_ == installerProtocol.type_) && hasScan() == installerProtocol.hasScan();
            if (hasScan()) {
                z = z && getScan().equals(installerProtocol.getScan());
            }
            boolean z2 = z && hasPose() == installerProtocol.hasPose();
            if (hasPose()) {
                z2 = z2 && getPose().equals(installerProtocol.getPose());
            }
            boolean z3 = z2 && hasCmd() == installerProtocol.hasCmd();
            if (hasCmd()) {
                z3 = z3 && getCmd().equals(installerProtocol.getCmd());
            }
            return ((z3 && getPro() == installerProtocol.getPro()) && getMainVersion() == installerProtocol.getMainVersion()) && this.unknownFields.equals(installerProtocol.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.type_;
            if (hasScan()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getScan().hashCode();
            }
            if (hasPose()) {
                hashCode = (((hashCode * 37) + 3) * 53) + getPose().hashCode();
            }
            if (hasCmd()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getCmd().hashCode();
            }
            int pro = (((((((((hashCode * 37) + 5) * 53) + getPro()) * 37) + 6) * 53) + getMainVersion()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = pro;
            return pro;
        }

        public static InstallerProtocol parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static InstallerProtocol parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static InstallerProtocol parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static InstallerProtocol parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static InstallerProtocol parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static InstallerProtocol parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static InstallerProtocol parseFrom(InputStream inputStream) throws IOException {
            return (InstallerProtocol) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static InstallerProtocol parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InstallerProtocol) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InstallerProtocol parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (InstallerProtocol) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static InstallerProtocol parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InstallerProtocol) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static InstallerProtocol parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (InstallerProtocol) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static InstallerProtocol parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InstallerProtocol) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(InstallerProtocol installerProtocol) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(installerProtocol);
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
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements InstallerProtocolOrBuilder {
            private SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> cmdBuilder_;
            private Command cmd_;
            private int mainVersion_;
            private SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> poseBuilder_;
            private RobotPose pose_;
            private int pro_;
            private SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> scanBuilder_;
            private LidarScan scan_;
            private int type_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return InstallerProto.f5341xacfbd03f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return InstallerProto.f5342xbc7247bd.ensureFieldAccessorsInitialized(InstallerProtocol.class, Builder.class);
            }

            private Builder() {
                this.type_ = 0;
                this.scan_ = null;
                this.pose_ = null;
                this.cmd_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.type_ = 0;
                this.scan_ = null;
                this.pose_ = null;
                this.cmd_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = InstallerProtocol.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.type_ = 0;
                if (this.scanBuilder_ == null) {
                    this.scan_ = null;
                } else {
                    this.scan_ = null;
                    this.scanBuilder_ = null;
                }
                if (this.poseBuilder_ == null) {
                    this.pose_ = null;
                } else {
                    this.pose_ = null;
                    this.poseBuilder_ = null;
                }
                if (this.cmdBuilder_ == null) {
                    this.cmd_ = null;
                } else {
                    this.cmd_ = null;
                    this.cmdBuilder_ = null;
                }
                this.pro_ = 0;
                this.mainVersion_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InstallerProto.f5341xacfbd03f;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public InstallerProtocol getDefaultInstanceForType() {
                return InstallerProtocol.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InstallerProtocol build() {
                InstallerProtocol buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InstallerProtocol buildPartial() {
                InstallerProtocol installerProtocol = new InstallerProtocol(this);
                installerProtocol.type_ = this.type_;
                SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> singleFieldBuilderV3 = this.scanBuilder_;
                if (singleFieldBuilderV3 == null) {
                    installerProtocol.scan_ = this.scan_;
                } else {
                    installerProtocol.scan_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> singleFieldBuilderV32 = this.poseBuilder_;
                if (singleFieldBuilderV32 == null) {
                    installerProtocol.pose_ = this.pose_;
                } else {
                    installerProtocol.pose_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> singleFieldBuilderV33 = this.cmdBuilder_;
                if (singleFieldBuilderV33 == null) {
                    installerProtocol.cmd_ = this.cmd_;
                } else {
                    installerProtocol.cmd_ = singleFieldBuilderV33.build();
                }
                installerProtocol.pro_ = this.pro_;
                installerProtocol.mainVersion_ = this.mainVersion_;
                onBuilt();
                return installerProtocol;
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
                if (message instanceof InstallerProtocol) {
                    return mergeFrom((InstallerProtocol) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(InstallerProtocol installerProtocol) {
                if (installerProtocol == InstallerProtocol.getDefaultInstance()) {
                    return this;
                }
                if (installerProtocol.type_ != 0) {
                    setTypeValue(installerProtocol.getTypeValue());
                }
                if (installerProtocol.hasScan()) {
                    mergeScan(installerProtocol.getScan());
                }
                if (installerProtocol.hasPose()) {
                    mergePose(installerProtocol.getPose());
                }
                if (installerProtocol.hasCmd()) {
                    mergeCmd(installerProtocol.getCmd());
                }
                if (installerProtocol.getPro() != 0) {
                    setPro(installerProtocol.getPro());
                }
                if (installerProtocol.getMainVersion() != 0) {
                    setMainVersion(installerProtocol.getMainVersion());
                }
                mergeUnknownFields(installerProtocol.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                InstallerProtocol installerProtocol = null;
                try {
                    try {
                        InstallerProtocol installerProtocol2 = (InstallerProtocol) InstallerProtocol.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (installerProtocol2 != null) {
                            mergeFrom(installerProtocol2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        InstallerProtocol installerProtocol3 = (InstallerProtocol) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            installerProtocol = installerProtocol3;
                            if (installerProtocol != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (installerProtocol != null) {
                        mergeFrom(installerProtocol);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public int getTypeValue() {
                return this.type_;
            }

            public Builder setTypeValue(int i) {
                this.type_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public ProType getType() {
                ProType valueOf = ProType.valueOf(this.type_);
                return valueOf == null ? ProType.UNRECOGNIZED : valueOf;
            }

            public Builder setType(ProType proType) {
                if (proType == null) {
                    throw new NullPointerException();
                }
                this.type_ = proType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public boolean hasScan() {
                return (this.scanBuilder_ == null && this.scan_ == null) ? false : true;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public LidarScan getScan() {
                SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> singleFieldBuilderV3 = this.scanBuilder_;
                if (singleFieldBuilderV3 == null) {
                    LidarScan lidarScan = this.scan_;
                    return lidarScan == null ? LidarScan.getDefaultInstance() : lidarScan;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setScan(LidarScan lidarScan) {
                SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> singleFieldBuilderV3 = this.scanBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(lidarScan);
                } else {
                    if (lidarScan == null) {
                        throw new NullPointerException();
                    }
                    this.scan_ = lidarScan;
                    onChanged();
                }
                return this;
            }

            public Builder setScan(LidarScan.Builder builder) {
                SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> singleFieldBuilderV3 = this.scanBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.scan_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeScan(LidarScan lidarScan) {
                SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> singleFieldBuilderV3 = this.scanBuilder_;
                if (singleFieldBuilderV3 == null) {
                    LidarScan lidarScan2 = this.scan_;
                    if (lidarScan2 != null) {
                        this.scan_ = LidarScan.newBuilder(lidarScan2).mergeFrom(lidarScan).buildPartial();
                    } else {
                        this.scan_ = lidarScan;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(lidarScan);
                }
                return this;
            }

            public Builder clearScan() {
                if (this.scanBuilder_ == null) {
                    this.scan_ = null;
                    onChanged();
                } else {
                    this.scan_ = null;
                    this.scanBuilder_ = null;
                }
                return this;
            }

            public LidarScan.Builder getScanBuilder() {
                onChanged();
                return getScanFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public LidarScanOrBuilder getScanOrBuilder() {
                SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> singleFieldBuilderV3 = this.scanBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                LidarScan lidarScan = this.scan_;
                return lidarScan == null ? LidarScan.getDefaultInstance() : lidarScan;
            }

            private SingleFieldBuilderV3<LidarScan, LidarScan.Builder, LidarScanOrBuilder> getScanFieldBuilder() {
                if (this.scanBuilder_ == null) {
                    this.scanBuilder_ = new SingleFieldBuilderV3<>(getScan(), getParentForChildren(), isClean());
                    this.scan_ = null;
                }
                return this.scanBuilder_;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public boolean hasPose() {
                return (this.poseBuilder_ == null && this.pose_ == null) ? false : true;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public RobotPose getPose() {
                SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RobotPose robotPose = this.pose_;
                    return robotPose == null ? RobotPose.getDefaultInstance() : robotPose;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPose(RobotPose robotPose) {
                SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(robotPose);
                } else {
                    if (robotPose == null) {
                        throw new NullPointerException();
                    }
                    this.pose_ = robotPose;
                    onChanged();
                }
                return this;
            }

            public Builder setPose(RobotPose.Builder builder) {
                SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pose_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePose(RobotPose robotPose) {
                SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RobotPose robotPose2 = this.pose_;
                    if (robotPose2 != null) {
                        this.pose_ = RobotPose.newBuilder(robotPose2).mergeFrom(robotPose).buildPartial();
                    } else {
                        this.pose_ = robotPose;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(robotPose);
                }
                return this;
            }

            public Builder clearPose() {
                if (this.poseBuilder_ == null) {
                    this.pose_ = null;
                    onChanged();
                } else {
                    this.pose_ = null;
                    this.poseBuilder_ = null;
                }
                return this;
            }

            public RobotPose.Builder getPoseBuilder() {
                onChanged();
                return getPoseFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public RobotPoseOrBuilder getPoseOrBuilder() {
                SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RobotPose robotPose = this.pose_;
                return robotPose == null ? RobotPose.getDefaultInstance() : robotPose;
            }

            private SingleFieldBuilderV3<RobotPose, RobotPose.Builder, RobotPoseOrBuilder> getPoseFieldBuilder() {
                if (this.poseBuilder_ == null) {
                    this.poseBuilder_ = new SingleFieldBuilderV3<>(getPose(), getParentForChildren(), isClean());
                    this.pose_ = null;
                }
                return this.poseBuilder_;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public boolean hasCmd() {
                return (this.cmdBuilder_ == null && this.cmd_ == null) ? false : true;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public Command getCmd() {
                SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> singleFieldBuilderV3 = this.cmdBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Command command = this.cmd_;
                    return command == null ? Command.getDefaultInstance() : command;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCmd(Command command) {
                SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> singleFieldBuilderV3 = this.cmdBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(command);
                } else {
                    if (command == null) {
                        throw new NullPointerException();
                    }
                    this.cmd_ = command;
                    onChanged();
                }
                return this;
            }

            public Builder setCmd(Command.Builder builder) {
                SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> singleFieldBuilderV3 = this.cmdBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.cmd_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCmd(Command command) {
                SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> singleFieldBuilderV3 = this.cmdBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Command command2 = this.cmd_;
                    if (command2 != null) {
                        this.cmd_ = Command.newBuilder(command2).mergeFrom(command).buildPartial();
                    } else {
                        this.cmd_ = command;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(command);
                }
                return this;
            }

            public Builder clearCmd() {
                if (this.cmdBuilder_ == null) {
                    this.cmd_ = null;
                    onChanged();
                } else {
                    this.cmd_ = null;
                    this.cmdBuilder_ = null;
                }
                return this;
            }

            public Command.Builder getCmdBuilder() {
                onChanged();
                return getCmdFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public CommandOrBuilder getCmdOrBuilder() {
                SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> singleFieldBuilderV3 = this.cmdBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Command command = this.cmd_;
                return command == null ? Command.getDefaultInstance() : command;
            }

            private SingleFieldBuilderV3<Command, Command.Builder, CommandOrBuilder> getCmdFieldBuilder() {
                if (this.cmdBuilder_ == null) {
                    this.cmdBuilder_ = new SingleFieldBuilderV3<>(getCmd(), getParentForChildren(), isClean());
                    this.cmd_ = null;
                }
                return this.cmdBuilder_;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public int getPro() {
                return this.pro_;
            }

            public Builder setPro(int i) {
                this.pro_ = i;
                onChanged();
                return this;
            }

            public Builder clearPro() {
                this.pro_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.installerserver.proto.InstallerProto.InstallerProtocolOrBuilder
            public int getMainVersion() {
                return this.mainVersion_;
            }

            public Builder setMainVersion(int i) {
                this.mainVersion_ = i;
                onChanged();
                return this;
            }

            public Builder clearMainVersion() {
                this.mainVersion_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static InstallerProtocol getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<InstallerProtocol> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<InstallerProtocol> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public InstallerProtocol getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0014InstallerProto.proto\u0012\"com.pudutech.installerserver.proto\"\"\n\nLidarPolar\u0012\t\n\u0001x\u0018\u0001 \u0001(\u0005\u0012\t\n\u0001y\u0018\u0002 \u0001(\u0005\"J\n\tLidarScan\u0012=\n\u0005scans\u0018\u0001 \u0003(\u000b2..com.pudutech.installerserver.proto.LidarPolar\"*\n\u0007Vector3\u0012\t\n\u0001x\u0018\u0001 \u0001(\u0001\u0012\t\n\u0001y\u0018\u0002 \u0001(\u0001\u0012\t\n\u0001z\u0018\u0003 \u0001(\u0001\"\u008a\u0001\n\tRobotPose\u0012=\n\bposition\u0018\u0001 \u0001(\u000b2+.com.pudutech.installerserver.proto.Vector3\u0012>\n\tdirection\u0018\u0002 \u0001(\u000b2+.com.pudutech.installerserver.proto.Vector3\"\u0017\n\u0007Command\u0012\f\n\u0004show\u0018\u0001 \u0001(\b\"í\u0002\n\u0011InstallerProtocol\u0012K\n\u0004type\u0018\u0001 \u0001(\u000e2=.com.pudutech.installerserver.proto.InstallerProtocol.ProType\u0012;\n\u0004scan\u0018\u0002 \u0001(\u000b2-.com.pudutech.installerserver.proto.LidarScan\u0012;\n\u0004pose\u0018\u0003 \u0001(\u000b2-.com.pudutech.installerserver.proto.RobotPose\u00128\n\u0003cmd\u0018\u0004 \u0001(\u000b2+.com.pudutech.installerserver.proto.Command\u0012\u000b\n\u0003pro\u0018\u0005 \u0001(\u0005\u0012\u0014\n\fmain_version\u0018\u0006 \u0001(\u0005\"4\n\u0007ProType\u0012\t\n\u0005Lidar\u0010\u0000\u0012\b\n\u0004Pose\u0010\u0001\u0012\u0007\n\u0003Cmd\u0010\u0002\u0012\u000b\n\u0007Product\u0010\u0003b\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.pudutech.installerserver.proto.InstallerProto.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = InstallerProto.descriptor = fileDescriptor;
                return null;
            }
        });
        f5343xcc9c8fd7 = getDescriptor().getMessageTypes().get(0);
        f5344x5385ef55 = new GeneratedMessageV3.FieldAccessorTable(f5343xcc9c8fd7, new String[]{"X", "Y"});
        f5345x46e694ca = getDescriptor().getMessageTypes().get(1);
        f5346xb2815148 = new GeneratedMessageV3.FieldAccessorTable(f5345x46e694ca, new String[]{"Scans"});
        f5349x8887ca6f = getDescriptor().getMessageTypes().get(2);
        f5350x7e3911ed = new GeneratedMessageV3.FieldAccessorTable(f5349x8887ca6f, new String[]{"X", "Y", "Z"});
        f5347x32c4da84 = getDescriptor().getMessageTypes().get(3);
        f5348xc033d02 = new GeneratedMessageV3.FieldAccessorTable(f5347x32c4da84, new String[]{"Position", "Direction"});
        f5339xd7dcf794 = getDescriptor().getMessageTypes().get(4);
        f5340x1df54a12 = new GeneratedMessageV3.FieldAccessorTable(f5339xd7dcf794, new String[]{"Show"});
        f5341xacfbd03f = getDescriptor().getMessageTypes().get(5);
        f5342xbc7247bd = new GeneratedMessageV3.FieldAccessorTable(f5341xacfbd03f, new String[]{"Type", "Scan", "Pose", "Cmd", "Pro", "MainVersion"});
    }
}
