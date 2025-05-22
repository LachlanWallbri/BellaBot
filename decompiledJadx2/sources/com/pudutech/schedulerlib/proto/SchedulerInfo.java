package com.pudutech.schedulerlib.proto;

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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class SchedulerInfo {
    private static Descriptors.FileDescriptor descriptor;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_CurrentRobotInfo_descriptor */
    private static final Descriptors.Descriptor f7451x60829f49;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_CurrentRobotInfo_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f7452xaa666cc7;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_MulticastProtocol_descriptor */
    private static final Descriptors.Descriptor f7453xba5b9344;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_MulticastProtocol_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f7454xb535c2;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_Pose2D_descriptor */
    private static final Descriptors.Descriptor f7455x69e44b65;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_Pose2D_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f7456xf4d83ce3;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_RobotScheduleInfo_descriptor */
    private static final Descriptors.Descriptor f7457xd7fbc525;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_RobotScheduleInfo_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f7458xe7b9f6a3;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_ScheduleInfo_descriptor */
    private static final Descriptors.Descriptor f7459x5d813963;

    /* renamed from: internal_static_com_pudutech_schedulerlib_proto_ScheduleInfo_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f7460x31e4ce1;

    /* loaded from: classes7.dex */
    public interface ChargeScheduleInfoOrBuilder extends MessageOrBuilder {
        int getBatteryLevel();

        String getBestRobot();

        ByteString getBestRobotBytes();

        int getMinDeliver();

        int getMode();

        Pose2D getTaskPoint();

        Pose2DOrBuilder getTaskPointOrBuilder();

        boolean hasTaskPoint();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface CurrentRobotInfoOrBuilder extends MessageOrBuilder {
        RobotScheduleInfo getData();

        RobotScheduleInfoOrBuilder getDataOrBuilder();

        String getType();

        ByteString getTypeBytes();

        boolean hasData();
    }

    /* loaded from: classes7.dex */
    public interface MotionInfoOrBuilder extends MessageOrBuilder {
        int getV();

        int getW();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface MulticastProtocolOrBuilder extends MessageOrBuilder {
        String getIp();

        ByteString getIpBytes();

        int getMsgId();

        long getMsgSeq();

        String getOriginIp();

        ByteString getOriginIpBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface Pose2DOrBuilder extends MessageOrBuilder {
        float getX();

        float getY();

        float getYaw();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface RobotScheduleInfoOrBuilder extends MessageOrBuilder {
        int getAvoidNodeId();

        String getAvoidRobotId();

        ByteString getAvoidRobotIdBytes();

        int getAvoidTrackId();

        Pose2D getFinalGoal();

        Pose2DOrBuilder getFinalGoalOrBuilder();

        long getMsgId();

        String getMsgType();

        ByteString getMsgTypeBytes();

        Pose2D getNextGoal();

        Pose2DOrBuilder getNextGoalOrBuilder();

        Pose2D getPose();

        Pose2DOrBuilder getPoseOrBuilder();

        String getRefRobotId();

        ByteString getRefRobotIdBytes();

        String getRobotId();

        ByteString getRobotIdBytes();

        RobotScheduleInfo.SchedulingMode getSchedulingMode();

        int getSchedulingModeValue();

        boolean getTopoOdd();

        int getTopologyPath(int i);

        int getTopologyPathCount();

        List<Integer> getTopologyPathList();

        Pose2D getVirtualGoal();

        Pose2DOrBuilder getVirtualGoalOrBuilder();

        boolean hasFinalGoal();

        boolean hasNextGoal();

        boolean hasPose();

        boolean hasVirtualGoal();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface ScheduleInfoOrBuilder extends MessageOrBuilder {
        CurrentRobotInfo getData();

        CurrentRobotInfoOrBuilder getDataOrBuilder();

        String getMapFlag();

        ByteString getMapFlagBytes();

        boolean hasData();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SchedulerInfo() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class Pose2D extends GeneratedMessageV3 implements Pose2DOrBuilder {
        private static final Pose2D DEFAULT_INSTANCE = new Pose2D();
        private static final Parser<Pose2D> PARSER = new AbstractParser<Pose2D>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2D.1
            @Override // com.google.protobuf.Parser
            public Pose2D parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Pose2D(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int X_FIELD_NUMBER = 1;
        public static final int YAW_FIELD_NUMBER = 3;
        public static final int Y_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        /* renamed from: x_ */
        private float f7465x_;

        /* renamed from: y_ */
        private float f7466y_;
        private float yaw_;

        private Pose2D(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Pose2D() {
            this.memoizedIsInitialized = (byte) -1;
            this.f7465x_ = 0.0f;
            this.f7466y_ = 0.0f;
            this.yaw_ = 0.0f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Pose2D(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 13) {
                                this.f7465x_ = codedInputStream.readFloat();
                            } else if (readTag == 21) {
                                this.f7466y_ = codedInputStream.readFloat();
                            } else if (readTag != 29) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.yaw_ = codedInputStream.readFloat();
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
            return SchedulerInfo.f7455x69e44b65;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.f7456xf4d83ce3.ensureFieldAccessorsInitialized(Pose2D.class, Builder.class);
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2DOrBuilder
        public float getX() {
            return this.f7465x_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2DOrBuilder
        public float getY() {
            return this.f7466y_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2DOrBuilder
        public float getYaw() {
            return this.yaw_;
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
            float f = this.f7465x_;
            if (f != 0.0f) {
                codedOutputStream.writeFloat(1, f);
            }
            float f2 = this.f7466y_;
            if (f2 != 0.0f) {
                codedOutputStream.writeFloat(2, f2);
            }
            float f3 = this.yaw_;
            if (f3 != 0.0f) {
                codedOutputStream.writeFloat(3, f3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            float f = this.f7465x_;
            int computeFloatSize = f != 0.0f ? 0 + CodedOutputStream.computeFloatSize(1, f) : 0;
            float f2 = this.f7466y_;
            if (f2 != 0.0f) {
                computeFloatSize += CodedOutputStream.computeFloatSize(2, f2);
            }
            float f3 = this.yaw_;
            if (f3 != 0.0f) {
                computeFloatSize += CodedOutputStream.computeFloatSize(3, f3);
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
            if (!(obj instanceof Pose2D)) {
                return super.equals(obj);
            }
            Pose2D pose2D = (Pose2D) obj;
            return (((Float.floatToIntBits(getX()) == Float.floatToIntBits(pose2D.getX())) && Float.floatToIntBits(getY()) == Float.floatToIntBits(pose2D.getY())) && Float.floatToIntBits(getYaw()) == Float.floatToIntBits(pose2D.getYaw())) && this.unknownFields.equals(pose2D.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Float.floatToIntBits(getX())) * 37) + 2) * 53) + Float.floatToIntBits(getY())) * 37) + 3) * 53) + Float.floatToIntBits(getYaw())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Pose2D parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Pose2D parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Pose2D parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Pose2D parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Pose2D parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Pose2D parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Pose2D parseFrom(InputStream inputStream) throws IOException {
            return (Pose2D) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Pose2D parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Pose2D) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Pose2D parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Pose2D) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Pose2D parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Pose2D) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Pose2D parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Pose2D) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Pose2D parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Pose2D) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Pose2D pose2D) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(pose2D);
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
          classes.dex
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Pose2DOrBuilder {

            /* renamed from: x_ */
            private float f7467x_;

            /* renamed from: y_ */
            private float f7468y_;
            private float yaw_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.f7455x69e44b65;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.f7456xf4d83ce3.ensureFieldAccessorsInitialized(Pose2D.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Pose2D.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.f7467x_ = 0.0f;
                this.f7468y_ = 0.0f;
                this.yaw_ = 0.0f;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.f7455x69e44b65;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Pose2D getDefaultInstanceForType() {
                return Pose2D.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Pose2D build() {
                Pose2D buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Pose2D buildPartial() {
                Pose2D pose2D = new Pose2D(this);
                pose2D.f7465x_ = this.f7467x_;
                pose2D.f7466y_ = this.f7468y_;
                pose2D.yaw_ = this.yaw_;
                onBuilt();
                return pose2D;
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
                if (message instanceof Pose2D) {
                    return mergeFrom((Pose2D) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Pose2D pose2D) {
                if (pose2D == Pose2D.getDefaultInstance()) {
                    return this;
                }
                if (pose2D.getX() != 0.0f) {
                    setX(pose2D.getX());
                }
                if (pose2D.getY() != 0.0f) {
                    setY(pose2D.getY());
                }
                if (pose2D.getYaw() != 0.0f) {
                    setYaw(pose2D.getYaw());
                }
                mergeUnknownFields(pose2D.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Pose2D pose2D = null;
                try {
                    try {
                        Pose2D pose2D2 = (Pose2D) Pose2D.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (pose2D2 != null) {
                            mergeFrom(pose2D2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Pose2D pose2D3 = (Pose2D) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            pose2D = pose2D3;
                            if (pose2D != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (pose2D != null) {
                        mergeFrom(pose2D);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2DOrBuilder
            public float getX() {
                return this.f7467x_;
            }

            public Builder setX(float f) {
                this.f7467x_ = f;
                onChanged();
                return this;
            }

            public Builder clearX() {
                this.f7467x_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2DOrBuilder
            public float getY() {
                return this.f7468y_;
            }

            public Builder setY(float f) {
                this.f7468y_ = f;
                onChanged();
                return this;
            }

            public Builder clearY() {
                this.f7468y_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.Pose2DOrBuilder
            public float getYaw() {
                return this.yaw_;
            }

            public Builder setYaw(float f) {
                this.yaw_ = f;
                onChanged();
                return this;
            }

            public Builder clearYaw() {
                this.yaw_ = 0.0f;
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

        public static Pose2D getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Pose2D> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Pose2D> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Pose2D getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes7.dex */
    public static final class ChargeScheduleInfo extends GeneratedMessageV3 implements ChargeScheduleInfoOrBuilder {
        public static final int BATTERY_LEVEL_FIELD_NUMBER = 2;
        public static final int BEST_ROBOT_FIELD_NUMBER = 5;
        public static final int MIN_DELIVER_FIELD_NUMBER = 3;
        public static final int MODE_FIELD_NUMBER = 1;
        public static final int TASK_POINT_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private int batteryLevel_;
        private volatile Object bestRobot_;
        private byte memoizedIsInitialized;
        private int minDeliver_;
        private int mode_;
        private Pose2D taskPoint_;
        private static final ChargeScheduleInfo DEFAULT_INSTANCE = new ChargeScheduleInfo();
        private static final Parser<ChargeScheduleInfo> PARSER = new AbstractParser<ChargeScheduleInfo>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfo.1
            @Override // com.google.protobuf.Parser
            public ChargeScheduleInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ChargeScheduleInfo(codedInputStream, extensionRegistryLite);
            }
        };

        private ChargeScheduleInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ChargeScheduleInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.mode_ = 0;
            this.batteryLevel_ = 0;
            this.minDeliver_ = 0;
            this.bestRobot_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ChargeScheduleInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.mode_ = codedInputStream.readSInt32();
                                } else if (readTag == 16) {
                                    this.batteryLevel_ = codedInputStream.readSInt32();
                                } else if (readTag == 24) {
                                    this.minDeliver_ = codedInputStream.readSInt32();
                                } else if (readTag == 34) {
                                    Pose2D.Builder builder = this.taskPoint_ != null ? this.taskPoint_.toBuilder() : null;
                                    this.taskPoint_ = (Pose2D) codedInputStream.readMessage(Pose2D.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.taskPoint_);
                                        this.taskPoint_ = builder.buildPartial();
                                    }
                                } else if (readTag != 42) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bestRobot_ = codedInputStream.readStringRequireUtf8();
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
            return SchedulerInfo.f7457xd7fbc525;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.f7458xe7b9f6a3.ensureFieldAccessorsInitialized(ChargeScheduleInfo.class, Builder.class);
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public int getMode() {
            return this.mode_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public int getBatteryLevel() {
            return this.batteryLevel_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public int getMinDeliver() {
            return this.minDeliver_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public boolean hasTaskPoint() {
            return this.taskPoint_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public Pose2D getTaskPoint() {
            Pose2D pose2D = this.taskPoint_;
            return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public Pose2DOrBuilder getTaskPointOrBuilder() {
            return getTaskPoint();
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public String getBestRobot() {
            Object obj = this.bestRobot_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.bestRobot_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
        public ByteString getBestRobotBytes() {
            Object obj = this.bestRobot_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bestRobot_ = copyFromUtf8;
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
            int i = this.mode_;
            if (i != 0) {
                codedOutputStream.writeSInt32(1, i);
            }
            int i2 = this.batteryLevel_;
            if (i2 != 0) {
                codedOutputStream.writeSInt32(2, i2);
            }
            int i3 = this.minDeliver_;
            if (i3 != 0) {
                codedOutputStream.writeSInt32(3, i3);
            }
            if (this.taskPoint_ != null) {
                codedOutputStream.writeMessage(4, getTaskPoint());
            }
            if (!getBestRobotBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.bestRobot_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.mode_;
            int computeSInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeSInt32Size(1, i2) : 0;
            int i3 = this.batteryLevel_;
            if (i3 != 0) {
                computeSInt32Size += CodedOutputStream.computeSInt32Size(2, i3);
            }
            int i4 = this.minDeliver_;
            if (i4 != 0) {
                computeSInt32Size += CodedOutputStream.computeSInt32Size(3, i4);
            }
            if (this.taskPoint_ != null) {
                computeSInt32Size += CodedOutputStream.computeMessageSize(4, getTaskPoint());
            }
            if (!getBestRobotBytes().isEmpty()) {
                computeSInt32Size += GeneratedMessageV3.computeStringSize(5, this.bestRobot_);
            }
            int serializedSize = computeSInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ChargeScheduleInfo)) {
                return super.equals(obj);
            }
            ChargeScheduleInfo chargeScheduleInfo = (ChargeScheduleInfo) obj;
            boolean z = (((getMode() == chargeScheduleInfo.getMode()) && getBatteryLevel() == chargeScheduleInfo.getBatteryLevel()) && getMinDeliver() == chargeScheduleInfo.getMinDeliver()) && hasTaskPoint() == chargeScheduleInfo.hasTaskPoint();
            if (hasTaskPoint()) {
                z = z && getTaskPoint().equals(chargeScheduleInfo.getTaskPoint());
            }
            return (z && getBestRobot().equals(chargeScheduleInfo.getBestRobot())) && this.unknownFields.equals(chargeScheduleInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMode()) * 37) + 2) * 53) + getBatteryLevel()) * 37) + 3) * 53) + getMinDeliver();
            if (hasTaskPoint()) {
                hashCode = (((hashCode * 37) + 4) * 53) + getTaskPoint().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 5) * 53) + getBestRobot().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ChargeScheduleInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ChargeScheduleInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ChargeScheduleInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ChargeScheduleInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ChargeScheduleInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ChargeScheduleInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ChargeScheduleInfo parseFrom(InputStream inputStream) throws IOException {
            return (ChargeScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ChargeScheduleInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChargeScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ChargeScheduleInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ChargeScheduleInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ChargeScheduleInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChargeScheduleInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ChargeScheduleInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ChargeScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ChargeScheduleInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChargeScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ChargeScheduleInfo chargeScheduleInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(chargeScheduleInfo);
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

        /* loaded from: classes7.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChargeScheduleInfoOrBuilder {
            private int batteryLevel_;
            private Object bestRobot_;
            private int minDeliver_;
            private int mode_;
            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> taskPointBuilder_;
            private Pose2D taskPoint_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.f7457xd7fbc525;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.f7458xe7b9f6a3.ensureFieldAccessorsInitialized(ChargeScheduleInfo.class, Builder.class);
            }

            private Builder() {
                this.taskPoint_ = null;
                this.bestRobot_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.taskPoint_ = null;
                this.bestRobot_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ChargeScheduleInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mode_ = 0;
                this.batteryLevel_ = 0;
                this.minDeliver_ = 0;
                if (this.taskPointBuilder_ == null) {
                    this.taskPoint_ = null;
                } else {
                    this.taskPoint_ = null;
                    this.taskPointBuilder_ = null;
                }
                this.bestRobot_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.f7457xd7fbc525;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ChargeScheduleInfo getDefaultInstanceForType() {
                return ChargeScheduleInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChargeScheduleInfo build() {
                ChargeScheduleInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChargeScheduleInfo buildPartial() {
                ChargeScheduleInfo chargeScheduleInfo = new ChargeScheduleInfo(this);
                chargeScheduleInfo.mode_ = this.mode_;
                chargeScheduleInfo.batteryLevel_ = this.batteryLevel_;
                chargeScheduleInfo.minDeliver_ = this.minDeliver_;
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.taskPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    chargeScheduleInfo.taskPoint_ = this.taskPoint_;
                } else {
                    chargeScheduleInfo.taskPoint_ = singleFieldBuilderV3.build();
                }
                chargeScheduleInfo.bestRobot_ = this.bestRobot_;
                onBuilt();
                return chargeScheduleInfo;
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
                if (message instanceof ChargeScheduleInfo) {
                    return mergeFrom((ChargeScheduleInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ChargeScheduleInfo chargeScheduleInfo) {
                if (chargeScheduleInfo == ChargeScheduleInfo.getDefaultInstance()) {
                    return this;
                }
                if (chargeScheduleInfo.getMode() != 0) {
                    setMode(chargeScheduleInfo.getMode());
                }
                if (chargeScheduleInfo.getBatteryLevel() != 0) {
                    setBatteryLevel(chargeScheduleInfo.getBatteryLevel());
                }
                if (chargeScheduleInfo.getMinDeliver() != 0) {
                    setMinDeliver(chargeScheduleInfo.getMinDeliver());
                }
                if (chargeScheduleInfo.hasTaskPoint()) {
                    mergeTaskPoint(chargeScheduleInfo.getTaskPoint());
                }
                if (!chargeScheduleInfo.getBestRobot().isEmpty()) {
                    this.bestRobot_ = chargeScheduleInfo.bestRobot_;
                    onChanged();
                }
                mergeUnknownFields(chargeScheduleInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ChargeScheduleInfo chargeScheduleInfo = null;
                try {
                    try {
                        ChargeScheduleInfo chargeScheduleInfo2 = (ChargeScheduleInfo) ChargeScheduleInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (chargeScheduleInfo2 != null) {
                            mergeFrom(chargeScheduleInfo2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ChargeScheduleInfo chargeScheduleInfo3 = (ChargeScheduleInfo) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            chargeScheduleInfo = chargeScheduleInfo3;
                            if (chargeScheduleInfo != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (chargeScheduleInfo != null) {
                        mergeFrom(chargeScheduleInfo);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public int getMode() {
                return this.mode_;
            }

            public Builder setMode(int i) {
                this.mode_ = i;
                onChanged();
                return this;
            }

            public Builder clearMode() {
                this.mode_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public int getBatteryLevel() {
                return this.batteryLevel_;
            }

            public Builder setBatteryLevel(int i) {
                this.batteryLevel_ = i;
                onChanged();
                return this;
            }

            public Builder clearBatteryLevel() {
                this.batteryLevel_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public int getMinDeliver() {
                return this.minDeliver_;
            }

            public Builder setMinDeliver(int i) {
                this.minDeliver_ = i;
                onChanged();
                return this;
            }

            public Builder clearMinDeliver() {
                this.minDeliver_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public boolean hasTaskPoint() {
                return (this.taskPointBuilder_ == null && this.taskPoint_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public Pose2D getTaskPoint() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.taskPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D = this.taskPoint_;
                    return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTaskPoint(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.taskPointBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(pose2D);
                } else {
                    if (pose2D == null) {
                        throw new NullPointerException();
                    }
                    this.taskPoint_ = pose2D;
                    onChanged();
                }
                return this;
            }

            public Builder setTaskPoint(Pose2D.Builder builder) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.taskPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.taskPoint_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTaskPoint(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.taskPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D2 = this.taskPoint_;
                    if (pose2D2 != null) {
                        this.taskPoint_ = Pose2D.newBuilder(pose2D2).mergeFrom(pose2D).buildPartial();
                    } else {
                        this.taskPoint_ = pose2D;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pose2D);
                }
                return this;
            }

            public Builder clearTaskPoint() {
                if (this.taskPointBuilder_ == null) {
                    this.taskPoint_ = null;
                    onChanged();
                } else {
                    this.taskPoint_ = null;
                    this.taskPointBuilder_ = null;
                }
                return this;
            }

            public Pose2D.Builder getTaskPointBuilder() {
                onChanged();
                return getTaskPointFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public Pose2DOrBuilder getTaskPointOrBuilder() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.taskPointBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Pose2D pose2D = this.taskPoint_;
                return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
            }

            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> getTaskPointFieldBuilder() {
                if (this.taskPointBuilder_ == null) {
                    this.taskPointBuilder_ = new SingleFieldBuilderV3<>(getTaskPoint(), getParentForChildren(), isClean());
                    this.taskPoint_ = null;
                }
                return this.taskPointBuilder_;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public String getBestRobot() {
                Object obj = this.bestRobot_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.bestRobot_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ChargeScheduleInfoOrBuilder
            public ByteString getBestRobotBytes() {
                Object obj = this.bestRobot_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bestRobot_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setBestRobot(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bestRobot_ = str;
                onChanged();
                return this;
            }

            public Builder clearBestRobot() {
                this.bestRobot_ = ChargeScheduleInfo.getDefaultInstance().getBestRobot();
                onChanged();
                return this;
            }

            public Builder setBestRobotBytes(ByteString byteString) {
                if (byteString != null) {
                    ChargeScheduleInfo.checkByteStringIsUtf8(byteString);
                    this.bestRobot_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
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

        public static ChargeScheduleInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ChargeScheduleInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ChargeScheduleInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ChargeScheduleInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class RobotScheduleInfo extends GeneratedMessageV3 implements RobotScheduleInfoOrBuilder {
        public static final int AVOID_NODE_ID_FIELD_NUMBER = 9;
        public static final int AVOID_ROBOT_ID_FIELD_NUMBER = 7;
        public static final int AVOID_TRACK_ID_FIELD_NUMBER = 10;
        public static final int FINAL_GOAL_FIELD_NUMBER = 12;
        public static final int MSG_ID_FIELD_NUMBER = 2;
        public static final int MSG_TYPE_FIELD_NUMBER = 3;
        public static final int NEXT_GOAL_FIELD_NUMBER = 11;
        public static final int POSE_FIELD_NUMBER = 14;
        public static final int REF_ROBOT_ID_FIELD_NUMBER = 6;
        public static final int ROBOT_ID_FIELD_NUMBER = 1;
        public static final int SCHEDULING_MODE_FIELD_NUMBER = 8;
        public static final int TOPOLOGY_PATH_FIELD_NUMBER = 5;
        public static final int TOPO_ODD_FIELD_NUMBER = 4;
        public static final int VIRTUAL_GOAL_FIELD_NUMBER = 13;
        private static final long serialVersionUID = 0;
        private int avoidNodeId_;
        private volatile Object avoidRobotId_;
        private int avoidTrackId_;
        private int bitField0_;
        private Pose2D finalGoal_;
        private byte memoizedIsInitialized;
        private long msgId_;
        private volatile Object msgType_;
        private Pose2D nextGoal_;
        private Pose2D pose_;
        private volatile Object refRobotId_;
        private volatile Object robotId_;
        private int schedulingMode_;
        private boolean topoOdd_;
        private int topologyPathMemoizedSerializedSize;
        private List<Integer> topologyPath_;
        private Pose2D virtualGoal_;
        private static final RobotScheduleInfo DEFAULT_INSTANCE = new RobotScheduleInfo();
        private static final Parser<RobotScheduleInfo> PARSER = new AbstractParser<RobotScheduleInfo>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfo.1
            @Override // com.google.protobuf.Parser
            public RobotScheduleInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RobotScheduleInfo(codedInputStream, extensionRegistryLite);
            }
        };

        private RobotScheduleInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.topologyPathMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
        }

        private RobotScheduleInfo() {
            this.topologyPathMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.robotId_ = "";
            this.msgId_ = 0L;
            this.msgType_ = "";
            this.topoOdd_ = false;
            this.topologyPath_ = Collections.emptyList();
            this.refRobotId_ = "";
            this.avoidRobotId_ = "";
            this.schedulingMode_ = 0;
            this.avoidNodeId_ = 0;
            this.avoidTrackId_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0015. Please report as an issue. */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r4v3 */
        private RobotScheduleInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (true) {
                int i2 = 16;
                ?? r4 = 16;
                if (z) {
                    return;
                }
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                            case 10:
                                this.robotId_ = codedInputStream.readStringRequireUtf8();
                            case 16:
                                this.msgId_ = codedInputStream.readUInt64();
                            case 26:
                                this.msgType_ = codedInputStream.readStringRequireUtf8();
                            case 32:
                                this.topoOdd_ = codedInputStream.readBool();
                            case 40:
                                if ((i & 16) != 16) {
                                    this.topologyPath_ = new ArrayList();
                                    i |= 16;
                                }
                                this.topologyPath_.add(Integer.valueOf(codedInputStream.readUInt32()));
                            case 42:
                                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 16) != 16 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.topologyPath_ = new ArrayList();
                                    i |= 16;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.topologyPath_.add(Integer.valueOf(codedInputStream.readUInt32()));
                                }
                                codedInputStream.popLimit(pushLimit);
                                break;
                            case 50:
                                this.refRobotId_ = codedInputStream.readStringRequireUtf8();
                            case 58:
                                this.avoidRobotId_ = codedInputStream.readStringRequireUtf8();
                            case 64:
                                this.schedulingMode_ = codedInputStream.readEnum();
                            case 72:
                                this.avoidNodeId_ = codedInputStream.readUInt32();
                            case 80:
                                this.avoidTrackId_ = codedInputStream.readUInt32();
                            case 90:
                                Pose2D.Builder builder = this.nextGoal_ != null ? this.nextGoal_.toBuilder() : null;
                                Pose2D pose2D = (Pose2D) codedInputStream.readMessage(Pose2D.parser(), extensionRegistryLite);
                                this.nextGoal_ = pose2D;
                                if (builder != null) {
                                    builder.mergeFrom(pose2D);
                                    this.nextGoal_ = builder.buildPartial();
                                }
                            case 98:
                                Pose2D.Builder builder2 = this.finalGoal_ != null ? this.finalGoal_.toBuilder() : null;
                                Pose2D pose2D2 = (Pose2D) codedInputStream.readMessage(Pose2D.parser(), extensionRegistryLite);
                                this.finalGoal_ = pose2D2;
                                if (builder2 != null) {
                                    builder2.mergeFrom(pose2D2);
                                    this.finalGoal_ = builder2.buildPartial();
                                }
                            case 106:
                                Pose2D.Builder builder3 = this.virtualGoal_ != null ? this.virtualGoal_.toBuilder() : null;
                                Pose2D pose2D3 = (Pose2D) codedInputStream.readMessage(Pose2D.parser(), extensionRegistryLite);
                                this.virtualGoal_ = pose2D3;
                                if (builder3 != null) {
                                    builder3.mergeFrom(pose2D3);
                                    this.virtualGoal_ = builder3.buildPartial();
                                }
                            case 114:
                                Pose2D.Builder builder4 = this.pose_ != null ? this.pose_.toBuilder() : null;
                                Pose2D pose2D4 = (Pose2D) codedInputStream.readMessage(Pose2D.parser(), extensionRegistryLite);
                                this.pose_ = pose2D4;
                                if (builder4 != null) {
                                    builder4.mergeFrom(pose2D4);
                                    this.pose_ = builder4.buildPartial();
                                }
                            default:
                                r4 = parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag);
                                if (r4 == 0) {
                                    z = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 16) == r4) {
                        this.topologyPath_ = Collections.unmodifiableList(this.topologyPath_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SchedulerInfo.f7457xd7fbc525;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.f7458xe7b9f6a3.ensureFieldAccessorsInitialized(RobotScheduleInfo.class, Builder.class);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public enum SchedulingMode implements ProtocolMessageEnum {
            Free(0),
            Normal(1),
            Arrived(2),
            Avoid(3),
            AvoidNormal(4),
            AvoidWait(5),
            Error(6),
            AvoidLeft(7),
            AvoidRight(8),
            NormalChange(9),
            AvoidChange(10),
            NormalRight(11),
            UNRECOGNIZED(-1);

            public static final int Arrived_VALUE = 2;
            public static final int AvoidChange_VALUE = 10;
            public static final int AvoidLeft_VALUE = 7;
            public static final int AvoidNormal_VALUE = 4;
            public static final int AvoidRight_VALUE = 8;
            public static final int AvoidWait_VALUE = 5;
            public static final int Avoid_VALUE = 3;
            public static final int Error_VALUE = 6;
            public static final int Free_VALUE = 0;
            public static final int NormalChange_VALUE = 9;
            public static final int NormalRight_VALUE = 11;
            public static final int Normal_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<SchedulingMode> internalValueMap = new Internal.EnumLiteMap<SchedulingMode>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfo.SchedulingMode.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public SchedulingMode findValueByNumber(int i) {
                    return SchedulingMode.forNumber(i);
                }
            };
            private static final SchedulingMode[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static SchedulingMode valueOf(int i) {
                return forNumber(i);
            }

            public static SchedulingMode forNumber(int i) {
                switch (i) {
                    case 0:
                        return Free;
                    case 1:
                        return Normal;
                    case 2:
                        return Arrived;
                    case 3:
                        return Avoid;
                    case 4:
                        return AvoidNormal;
                    case 5:
                        return AvoidWait;
                    case 6:
                        return Error;
                    case 7:
                        return AvoidLeft;
                    case 8:
                        return AvoidRight;
                    case 9:
                        return NormalChange;
                    case 10:
                        return AvoidChange;
                    case 11:
                        return NormalRight;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<SchedulingMode> internalGetValueMap() {
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
                return RobotScheduleInfo.getDescriptor().getEnumTypes().get(0);
            }

            public static SchedulingMode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            SchedulingMode(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public String getRobotId() {
            Object obj = this.robotId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.robotId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public ByteString getRobotIdBytes() {
            Object obj = this.robotId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.robotId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public long getMsgId() {
            return this.msgId_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public String getMsgType() {
            Object obj = this.msgType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.msgType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public ByteString getMsgTypeBytes() {
            Object obj = this.msgType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.msgType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public boolean getTopoOdd() {
            return this.topoOdd_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public List<Integer> getTopologyPathList() {
            return this.topologyPath_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public int getTopologyPathCount() {
            return this.topologyPath_.size();
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public int getTopologyPath(int i) {
            return this.topologyPath_.get(i).intValue();
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public String getRefRobotId() {
            Object obj = this.refRobotId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.refRobotId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public ByteString getRefRobotIdBytes() {
            Object obj = this.refRobotId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.refRobotId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public String getAvoidRobotId() {
            Object obj = this.avoidRobotId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.avoidRobotId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public ByteString getAvoidRobotIdBytes() {
            Object obj = this.avoidRobotId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.avoidRobotId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public int getSchedulingModeValue() {
            return this.schedulingMode_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public SchedulingMode getSchedulingMode() {
            SchedulingMode valueOf = SchedulingMode.valueOf(this.schedulingMode_);
            return valueOf == null ? SchedulingMode.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public int getAvoidNodeId() {
            return this.avoidNodeId_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public int getAvoidTrackId() {
            return this.avoidTrackId_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public boolean hasNextGoal() {
            return this.nextGoal_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2D getNextGoal() {
            Pose2D pose2D = this.nextGoal_;
            return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2DOrBuilder getNextGoalOrBuilder() {
            return getNextGoal();
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public boolean hasFinalGoal() {
            return this.finalGoal_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2D getFinalGoal() {
            Pose2D pose2D = this.finalGoal_;
            return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2DOrBuilder getFinalGoalOrBuilder() {
            return getFinalGoal();
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public boolean hasVirtualGoal() {
            return this.virtualGoal_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2D getVirtualGoal() {
            Pose2D pose2D = this.virtualGoal_;
            return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2DOrBuilder getVirtualGoalOrBuilder() {
            return getVirtualGoal();
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public boolean hasPose() {
            return this.pose_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2D getPose() {
            Pose2D pose2D = this.pose_;
            return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
        public Pose2DOrBuilder getPoseOrBuilder() {
            return getPose();
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
            if (!getRobotIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.robotId_);
            }
            long j = this.msgId_;
            if (j != 0) {
                codedOutputStream.writeUInt64(2, j);
            }
            if (!getMsgTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.msgType_);
            }
            boolean z = this.topoOdd_;
            if (z) {
                codedOutputStream.writeBool(4, z);
            }
            if (getTopologyPathList().size() > 0) {
                codedOutputStream.writeUInt32NoTag(42);
                codedOutputStream.writeUInt32NoTag(this.topologyPathMemoizedSerializedSize);
            }
            for (int i = 0; i < this.topologyPath_.size(); i++) {
                codedOutputStream.writeUInt32NoTag(this.topologyPath_.get(i).intValue());
            }
            if (!getRefRobotIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.refRobotId_);
            }
            if (!getAvoidRobotIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 7, this.avoidRobotId_);
            }
            if (this.schedulingMode_ != SchedulingMode.Free.getNumber()) {
                codedOutputStream.writeEnum(8, this.schedulingMode_);
            }
            int i2 = this.avoidNodeId_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(9, i2);
            }
            int i3 = this.avoidTrackId_;
            if (i3 != 0) {
                codedOutputStream.writeUInt32(10, i3);
            }
            if (this.nextGoal_ != null) {
                codedOutputStream.writeMessage(11, getNextGoal());
            }
            if (this.finalGoal_ != null) {
                codedOutputStream.writeMessage(12, getFinalGoal());
            }
            if (this.virtualGoal_ != null) {
                codedOutputStream.writeMessage(13, getVirtualGoal());
            }
            if (this.pose_ != null) {
                codedOutputStream.writeMessage(14, getPose());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !getRobotIdBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.robotId_) + 0 : 0;
            long j = this.msgId_;
            if (j != 0) {
                computeStringSize += CodedOutputStream.computeUInt64Size(2, j);
            }
            if (!getMsgTypeBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(3, this.msgType_);
            }
            boolean z = this.topoOdd_;
            if (z) {
                computeStringSize += CodedOutputStream.computeBoolSize(4, z);
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.topologyPath_.size(); i3++) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(this.topologyPath_.get(i3).intValue());
            }
            int i4 = computeStringSize + i2;
            if (!getTopologyPathList().isEmpty()) {
                i4 = i4 + 1 + CodedOutputStream.computeInt32SizeNoTag(i2);
            }
            this.topologyPathMemoizedSerializedSize = i2;
            if (!getRefRobotIdBytes().isEmpty()) {
                i4 += GeneratedMessageV3.computeStringSize(6, this.refRobotId_);
            }
            if (!getAvoidRobotIdBytes().isEmpty()) {
                i4 += GeneratedMessageV3.computeStringSize(7, this.avoidRobotId_);
            }
            if (this.schedulingMode_ != SchedulingMode.Free.getNumber()) {
                i4 += CodedOutputStream.computeEnumSize(8, this.schedulingMode_);
            }
            int i5 = this.avoidNodeId_;
            if (i5 != 0) {
                i4 += CodedOutputStream.computeUInt32Size(9, i5);
            }
            int i6 = this.avoidTrackId_;
            if (i6 != 0) {
                i4 += CodedOutputStream.computeUInt32Size(10, i6);
            }
            if (this.nextGoal_ != null) {
                i4 += CodedOutputStream.computeMessageSize(11, getNextGoal());
            }
            if (this.finalGoal_ != null) {
                i4 += CodedOutputStream.computeMessageSize(12, getFinalGoal());
            }
            if (this.virtualGoal_ != null) {
                i4 += CodedOutputStream.computeMessageSize(13, getVirtualGoal());
            }
            if (this.pose_ != null) {
                i4 += CodedOutputStream.computeMessageSize(14, getPose());
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
            if (!(obj instanceof RobotScheduleInfo)) {
                return super.equals(obj);
            }
            RobotScheduleInfo robotScheduleInfo = (RobotScheduleInfo) obj;
            boolean z = ((((((((((getRobotId().equals(robotScheduleInfo.getRobotId())) && (getMsgId() > robotScheduleInfo.getMsgId() ? 1 : (getMsgId() == robotScheduleInfo.getMsgId() ? 0 : -1)) == 0) && getMsgType().equals(robotScheduleInfo.getMsgType())) && getTopoOdd() == robotScheduleInfo.getTopoOdd()) && getTopologyPathList().equals(robotScheduleInfo.getTopologyPathList())) && getRefRobotId().equals(robotScheduleInfo.getRefRobotId())) && getAvoidRobotId().equals(robotScheduleInfo.getAvoidRobotId())) && this.schedulingMode_ == robotScheduleInfo.schedulingMode_) && getAvoidNodeId() == robotScheduleInfo.getAvoidNodeId()) && getAvoidTrackId() == robotScheduleInfo.getAvoidTrackId()) && hasNextGoal() == robotScheduleInfo.hasNextGoal();
            if (hasNextGoal()) {
                z = z && getNextGoal().equals(robotScheduleInfo.getNextGoal());
            }
            boolean z2 = z && hasFinalGoal() == robotScheduleInfo.hasFinalGoal();
            if (hasFinalGoal()) {
                z2 = z2 && getFinalGoal().equals(robotScheduleInfo.getFinalGoal());
            }
            boolean z3 = z2 && hasVirtualGoal() == robotScheduleInfo.hasVirtualGoal();
            if (hasVirtualGoal()) {
                z3 = z3 && getVirtualGoal().equals(robotScheduleInfo.getVirtualGoal());
            }
            boolean z4 = z3 && hasPose() == robotScheduleInfo.hasPose();
            if (hasPose()) {
                z4 = z4 && getPose().equals(robotScheduleInfo.getPose());
            }
            return z4 && this.unknownFields.equals(robotScheduleInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRobotId().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getMsgId())) * 37) + 3) * 53) + getMsgType().hashCode()) * 37) + 4) * 53) + Internal.hashBoolean(getTopoOdd());
            if (getTopologyPathCount() > 0) {
                hashCode = (((hashCode * 37) + 5) * 53) + getTopologyPathList().hashCode();
            }
            int hashCode2 = (((((((((((((((((((hashCode * 37) + 6) * 53) + getRefRobotId().hashCode()) * 37) + 7) * 53) + getAvoidRobotId().hashCode()) * 37) + 8) * 53) + this.schedulingMode_) * 37) + 9) * 53) + getAvoidNodeId()) * 37) + 10) * 53) + getAvoidTrackId();
            if (hasNextGoal()) {
                hashCode2 = (((hashCode2 * 37) + 11) * 53) + getNextGoal().hashCode();
            }
            if (hasFinalGoal()) {
                hashCode2 = (((hashCode2 * 37) + 12) * 53) + getFinalGoal().hashCode();
            }
            if (hasVirtualGoal()) {
                hashCode2 = (((hashCode2 * 37) + 13) * 53) + getVirtualGoal().hashCode();
            }
            if (hasPose()) {
                hashCode2 = (((hashCode2 * 37) + 14) * 53) + getPose().hashCode();
            }
            int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }

        public static RobotScheduleInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RobotScheduleInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RobotScheduleInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RobotScheduleInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RobotScheduleInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RobotScheduleInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RobotScheduleInfo parseFrom(InputStream inputStream) throws IOException {
            return (RobotScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RobotScheduleInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RobotScheduleInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RobotScheduleInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RobotScheduleInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotScheduleInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RobotScheduleInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RobotScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RobotScheduleInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RobotScheduleInfo robotScheduleInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(robotScheduleInfo);
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
          classes.dex
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RobotScheduleInfoOrBuilder {
            private int avoidNodeId_;
            private Object avoidRobotId_;
            private int avoidTrackId_;
            private int bitField0_;
            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> finalGoalBuilder_;
            private Pose2D finalGoal_;
            private long msgId_;
            private Object msgType_;
            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> nextGoalBuilder_;
            private Pose2D nextGoal_;
            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> poseBuilder_;
            private Pose2D pose_;
            private Object refRobotId_;
            private Object robotId_;
            private int schedulingMode_;
            private boolean topoOdd_;
            private List<Integer> topologyPath_;
            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> virtualGoalBuilder_;
            private Pose2D virtualGoal_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.f7457xd7fbc525;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.f7458xe7b9f6a3.ensureFieldAccessorsInitialized(RobotScheduleInfo.class, Builder.class);
            }

            private Builder() {
                this.robotId_ = "";
                this.msgType_ = "";
                this.topologyPath_ = Collections.emptyList();
                this.refRobotId_ = "";
                this.avoidRobotId_ = "";
                this.schedulingMode_ = 0;
                this.nextGoal_ = null;
                this.finalGoal_ = null;
                this.virtualGoal_ = null;
                this.pose_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.robotId_ = "";
                this.msgType_ = "";
                this.topologyPath_ = Collections.emptyList();
                this.refRobotId_ = "";
                this.avoidRobotId_ = "";
                this.schedulingMode_ = 0;
                this.nextGoal_ = null;
                this.finalGoal_ = null;
                this.virtualGoal_ = null;
                this.pose_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RobotScheduleInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.robotId_ = "";
                this.msgId_ = 0L;
                this.msgType_ = "";
                this.topoOdd_ = false;
                this.topologyPath_ = Collections.emptyList();
                this.bitField0_ &= -17;
                this.refRobotId_ = "";
                this.avoidRobotId_ = "";
                this.schedulingMode_ = 0;
                this.avoidNodeId_ = 0;
                this.avoidTrackId_ = 0;
                if (this.nextGoalBuilder_ == null) {
                    this.nextGoal_ = null;
                } else {
                    this.nextGoal_ = null;
                    this.nextGoalBuilder_ = null;
                }
                if (this.finalGoalBuilder_ == null) {
                    this.finalGoal_ = null;
                } else {
                    this.finalGoal_ = null;
                    this.finalGoalBuilder_ = null;
                }
                if (this.virtualGoalBuilder_ == null) {
                    this.virtualGoal_ = null;
                } else {
                    this.virtualGoal_ = null;
                    this.virtualGoalBuilder_ = null;
                }
                if (this.poseBuilder_ == null) {
                    this.pose_ = null;
                } else {
                    this.pose_ = null;
                    this.poseBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.f7457xd7fbc525;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public RobotScheduleInfo getDefaultInstanceForType() {
                return RobotScheduleInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RobotScheduleInfo build() {
                RobotScheduleInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RobotScheduleInfo buildPartial() {
                RobotScheduleInfo robotScheduleInfo = new RobotScheduleInfo(this);
                robotScheduleInfo.robotId_ = this.robotId_;
                robotScheduleInfo.msgId_ = this.msgId_;
                robotScheduleInfo.msgType_ = this.msgType_;
                robotScheduleInfo.topoOdd_ = this.topoOdd_;
                if ((this.bitField0_ & 16) == 16) {
                    this.topologyPath_ = Collections.unmodifiableList(this.topologyPath_);
                    this.bitField0_ &= -17;
                }
                robotScheduleInfo.topologyPath_ = this.topologyPath_;
                robotScheduleInfo.refRobotId_ = this.refRobotId_;
                robotScheduleInfo.avoidRobotId_ = this.avoidRobotId_;
                robotScheduleInfo.schedulingMode_ = this.schedulingMode_;
                robotScheduleInfo.avoidNodeId_ = this.avoidNodeId_;
                robotScheduleInfo.avoidTrackId_ = this.avoidTrackId_;
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.nextGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    robotScheduleInfo.nextGoal_ = this.nextGoal_;
                } else {
                    robotScheduleInfo.nextGoal_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV32 = this.finalGoalBuilder_;
                if (singleFieldBuilderV32 == null) {
                    robotScheduleInfo.finalGoal_ = this.finalGoal_;
                } else {
                    robotScheduleInfo.finalGoal_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV33 = this.virtualGoalBuilder_;
                if (singleFieldBuilderV33 == null) {
                    robotScheduleInfo.virtualGoal_ = this.virtualGoal_;
                } else {
                    robotScheduleInfo.virtualGoal_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV34 = this.poseBuilder_;
                if (singleFieldBuilderV34 == null) {
                    robotScheduleInfo.pose_ = this.pose_;
                } else {
                    robotScheduleInfo.pose_ = singleFieldBuilderV34.build();
                }
                robotScheduleInfo.bitField0_ = 0;
                onBuilt();
                return robotScheduleInfo;
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
                if (message instanceof RobotScheduleInfo) {
                    return mergeFrom((RobotScheduleInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RobotScheduleInfo robotScheduleInfo) {
                if (robotScheduleInfo == RobotScheduleInfo.getDefaultInstance()) {
                    return this;
                }
                if (!robotScheduleInfo.getRobotId().isEmpty()) {
                    this.robotId_ = robotScheduleInfo.robotId_;
                    onChanged();
                }
                if (robotScheduleInfo.getMsgId() != 0) {
                    setMsgId(robotScheduleInfo.getMsgId());
                }
                if (!robotScheduleInfo.getMsgType().isEmpty()) {
                    this.msgType_ = robotScheduleInfo.msgType_;
                    onChanged();
                }
                if (robotScheduleInfo.getTopoOdd()) {
                    setTopoOdd(robotScheduleInfo.getTopoOdd());
                }
                if (!robotScheduleInfo.topologyPath_.isEmpty()) {
                    if (this.topologyPath_.isEmpty()) {
                        this.topologyPath_ = robotScheduleInfo.topologyPath_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureTopologyPathIsMutable();
                        this.topologyPath_.addAll(robotScheduleInfo.topologyPath_);
                    }
                    onChanged();
                }
                if (!robotScheduleInfo.getRefRobotId().isEmpty()) {
                    this.refRobotId_ = robotScheduleInfo.refRobotId_;
                    onChanged();
                }
                if (!robotScheduleInfo.getAvoidRobotId().isEmpty()) {
                    this.avoidRobotId_ = robotScheduleInfo.avoidRobotId_;
                    onChanged();
                }
                if (robotScheduleInfo.schedulingMode_ != 0) {
                    setSchedulingModeValue(robotScheduleInfo.getSchedulingModeValue());
                }
                if (robotScheduleInfo.getAvoidNodeId() != 0) {
                    setAvoidNodeId(robotScheduleInfo.getAvoidNodeId());
                }
                if (robotScheduleInfo.getAvoidTrackId() != 0) {
                    setAvoidTrackId(robotScheduleInfo.getAvoidTrackId());
                }
                if (robotScheduleInfo.hasNextGoal()) {
                    mergeNextGoal(robotScheduleInfo.getNextGoal());
                }
                if (robotScheduleInfo.hasFinalGoal()) {
                    mergeFinalGoal(robotScheduleInfo.getFinalGoal());
                }
                if (robotScheduleInfo.hasVirtualGoal()) {
                    mergeVirtualGoal(robotScheduleInfo.getVirtualGoal());
                }
                if (robotScheduleInfo.hasPose()) {
                    mergePose(robotScheduleInfo.getPose());
                }
                mergeUnknownFields(robotScheduleInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RobotScheduleInfo robotScheduleInfo = null;
                try {
                    try {
                        RobotScheduleInfo robotScheduleInfo2 = (RobotScheduleInfo) RobotScheduleInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (robotScheduleInfo2 != null) {
                            mergeFrom(robotScheduleInfo2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        RobotScheduleInfo robotScheduleInfo3 = (RobotScheduleInfo) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            robotScheduleInfo = robotScheduleInfo3;
                            if (robotScheduleInfo != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (robotScheduleInfo != null) {
                        mergeFrom(robotScheduleInfo);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public String getRobotId() {
                Object obj = this.robotId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.robotId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public ByteString getRobotIdBytes() {
                Object obj = this.robotId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.robotId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setRobotId(String str) {
                if (str == null) {
                    throw null;
                }
                this.robotId_ = str;
                onChanged();
                return this;
            }

            public Builder clearRobotId() {
                this.robotId_ = RobotScheduleInfo.getDefaultInstance().getRobotId();
                onChanged();
                return this;
            }

            public Builder setRobotIdBytes(ByteString byteString) {
                if (byteString != null) {
                    RobotScheduleInfo.checkByteStringIsUtf8(byteString);
                    this.robotId_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public long getMsgId() {
                return this.msgId_;
            }

            public Builder setMsgId(long j) {
                this.msgId_ = j;
                onChanged();
                return this;
            }

            public Builder clearMsgId() {
                this.msgId_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public String getMsgType() {
                Object obj = this.msgType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.msgType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public ByteString getMsgTypeBytes() {
                Object obj = this.msgType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.msgType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMsgType(String str) {
                if (str == null) {
                    throw null;
                }
                this.msgType_ = str;
                onChanged();
                return this;
            }

            public Builder clearMsgType() {
                this.msgType_ = RobotScheduleInfo.getDefaultInstance().getMsgType();
                onChanged();
                return this;
            }

            public Builder setMsgTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    RobotScheduleInfo.checkByteStringIsUtf8(byteString);
                    this.msgType_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public boolean getTopoOdd() {
                return this.topoOdd_;
            }

            public Builder setTopoOdd(boolean z) {
                this.topoOdd_ = z;
                onChanged();
                return this;
            }

            public Builder clearTopoOdd() {
                this.topoOdd_ = false;
                onChanged();
                return this;
            }

            private void ensureTopologyPathIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.topologyPath_ = new ArrayList(this.topologyPath_);
                    this.bitField0_ |= 16;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public List<Integer> getTopologyPathList() {
                return Collections.unmodifiableList(this.topologyPath_);
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public int getTopologyPathCount() {
                return this.topologyPath_.size();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public int getTopologyPath(int i) {
                return this.topologyPath_.get(i).intValue();
            }

            public Builder setTopologyPath(int i, int i2) {
                ensureTopologyPathIsMutable();
                this.topologyPath_.set(i, Integer.valueOf(i2));
                onChanged();
                return this;
            }

            public Builder addTopologyPath(int i) {
                ensureTopologyPathIsMutable();
                this.topologyPath_.add(Integer.valueOf(i));
                onChanged();
                return this;
            }

            public Builder addAllTopologyPath(Iterable<? extends Integer> iterable) {
                ensureTopologyPathIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.topologyPath_);
                onChanged();
                return this;
            }

            public Builder clearTopologyPath() {
                this.topologyPath_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public String getRefRobotId() {
                Object obj = this.refRobotId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.refRobotId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public ByteString getRefRobotIdBytes() {
                Object obj = this.refRobotId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.refRobotId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setRefRobotId(String str) {
                if (str == null) {
                    throw null;
                }
                this.refRobotId_ = str;
                onChanged();
                return this;
            }

            public Builder clearRefRobotId() {
                this.refRobotId_ = RobotScheduleInfo.getDefaultInstance().getRefRobotId();
                onChanged();
                return this;
            }

            public Builder setRefRobotIdBytes(ByteString byteString) {
                if (byteString != null) {
                    RobotScheduleInfo.checkByteStringIsUtf8(byteString);
                    this.refRobotId_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public String getAvoidRobotId() {
                Object obj = this.avoidRobotId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.avoidRobotId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public ByteString getAvoidRobotIdBytes() {
                Object obj = this.avoidRobotId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.avoidRobotId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAvoidRobotId(String str) {
                if (str == null) {
                    throw null;
                }
                this.avoidRobotId_ = str;
                onChanged();
                return this;
            }

            public Builder clearAvoidRobotId() {
                this.avoidRobotId_ = RobotScheduleInfo.getDefaultInstance().getAvoidRobotId();
                onChanged();
                return this;
            }

            public Builder setAvoidRobotIdBytes(ByteString byteString) {
                if (byteString != null) {
                    RobotScheduleInfo.checkByteStringIsUtf8(byteString);
                    this.avoidRobotId_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public int getSchedulingModeValue() {
                return this.schedulingMode_;
            }

            public Builder setSchedulingModeValue(int i) {
                this.schedulingMode_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public SchedulingMode getSchedulingMode() {
                SchedulingMode valueOf = SchedulingMode.valueOf(this.schedulingMode_);
                return valueOf == null ? SchedulingMode.UNRECOGNIZED : valueOf;
            }

            public Builder setSchedulingMode(SchedulingMode schedulingMode) {
                if (schedulingMode == null) {
                    throw null;
                }
                this.schedulingMode_ = schedulingMode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearSchedulingMode() {
                this.schedulingMode_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public int getAvoidNodeId() {
                return this.avoidNodeId_;
            }

            public Builder setAvoidNodeId(int i) {
                this.avoidNodeId_ = i;
                onChanged();
                return this;
            }

            public Builder clearAvoidNodeId() {
                this.avoidNodeId_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public int getAvoidTrackId() {
                return this.avoidTrackId_;
            }

            public Builder setAvoidTrackId(int i) {
                this.avoidTrackId_ = i;
                onChanged();
                return this;
            }

            public Builder clearAvoidTrackId() {
                this.avoidTrackId_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public boolean hasNextGoal() {
                return (this.nextGoalBuilder_ == null && this.nextGoal_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2D getNextGoal() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.nextGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D = this.nextGoal_;
                    return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setNextGoal(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.nextGoalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(pose2D);
                } else {
                    if (pose2D == null) {
                        throw null;
                    }
                    this.nextGoal_ = pose2D;
                    onChanged();
                }
                return this;
            }

            public Builder setNextGoal(Pose2D.Builder builder) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.nextGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.nextGoal_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeNextGoal(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.nextGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D2 = this.nextGoal_;
                    if (pose2D2 != null) {
                        this.nextGoal_ = Pose2D.newBuilder(pose2D2).mergeFrom(pose2D).buildPartial();
                    } else {
                        this.nextGoal_ = pose2D;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pose2D);
                }
                return this;
            }

            public Builder clearNextGoal() {
                if (this.nextGoalBuilder_ == null) {
                    this.nextGoal_ = null;
                    onChanged();
                } else {
                    this.nextGoal_ = null;
                    this.nextGoalBuilder_ = null;
                }
                return this;
            }

            public Pose2D.Builder getNextGoalBuilder() {
                onChanged();
                return getNextGoalFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2DOrBuilder getNextGoalOrBuilder() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.nextGoalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Pose2D pose2D = this.nextGoal_;
                return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
            }

            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> getNextGoalFieldBuilder() {
                if (this.nextGoalBuilder_ == null) {
                    this.nextGoalBuilder_ = new SingleFieldBuilderV3<>(getNextGoal(), getParentForChildren(), isClean());
                    this.nextGoal_ = null;
                }
                return this.nextGoalBuilder_;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public boolean hasFinalGoal() {
                return (this.finalGoalBuilder_ == null && this.finalGoal_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2D getFinalGoal() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.finalGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D = this.finalGoal_;
                    return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setFinalGoal(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.finalGoalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(pose2D);
                } else {
                    if (pose2D == null) {
                        throw null;
                    }
                    this.finalGoal_ = pose2D;
                    onChanged();
                }
                return this;
            }

            public Builder setFinalGoal(Pose2D.Builder builder) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.finalGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.finalGoal_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeFinalGoal(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.finalGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D2 = this.finalGoal_;
                    if (pose2D2 != null) {
                        this.finalGoal_ = Pose2D.newBuilder(pose2D2).mergeFrom(pose2D).buildPartial();
                    } else {
                        this.finalGoal_ = pose2D;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pose2D);
                }
                return this;
            }

            public Builder clearFinalGoal() {
                if (this.finalGoalBuilder_ == null) {
                    this.finalGoal_ = null;
                    onChanged();
                } else {
                    this.finalGoal_ = null;
                    this.finalGoalBuilder_ = null;
                }
                return this;
            }

            public Pose2D.Builder getFinalGoalBuilder() {
                onChanged();
                return getFinalGoalFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2DOrBuilder getFinalGoalOrBuilder() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.finalGoalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Pose2D pose2D = this.finalGoal_;
                return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
            }

            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> getFinalGoalFieldBuilder() {
                if (this.finalGoalBuilder_ == null) {
                    this.finalGoalBuilder_ = new SingleFieldBuilderV3<>(getFinalGoal(), getParentForChildren(), isClean());
                    this.finalGoal_ = null;
                }
                return this.finalGoalBuilder_;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public boolean hasVirtualGoal() {
                return (this.virtualGoalBuilder_ == null && this.virtualGoal_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2D getVirtualGoal() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.virtualGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D = this.virtualGoal_;
                    return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setVirtualGoal(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.virtualGoalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(pose2D);
                } else {
                    if (pose2D == null) {
                        throw null;
                    }
                    this.virtualGoal_ = pose2D;
                    onChanged();
                }
                return this;
            }

            public Builder setVirtualGoal(Pose2D.Builder builder) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.virtualGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.virtualGoal_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeVirtualGoal(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.virtualGoalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D2 = this.virtualGoal_;
                    if (pose2D2 != null) {
                        this.virtualGoal_ = Pose2D.newBuilder(pose2D2).mergeFrom(pose2D).buildPartial();
                    } else {
                        this.virtualGoal_ = pose2D;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pose2D);
                }
                return this;
            }

            public Builder clearVirtualGoal() {
                if (this.virtualGoalBuilder_ == null) {
                    this.virtualGoal_ = null;
                    onChanged();
                } else {
                    this.virtualGoal_ = null;
                    this.virtualGoalBuilder_ = null;
                }
                return this;
            }

            public Pose2D.Builder getVirtualGoalBuilder() {
                onChanged();
                return getVirtualGoalFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2DOrBuilder getVirtualGoalOrBuilder() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.virtualGoalBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Pose2D pose2D = this.virtualGoal_;
                return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
            }

            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> getVirtualGoalFieldBuilder() {
                if (this.virtualGoalBuilder_ == null) {
                    this.virtualGoalBuilder_ = new SingleFieldBuilderV3<>(getVirtualGoal(), getParentForChildren(), isClean());
                    this.virtualGoal_ = null;
                }
                return this.virtualGoalBuilder_;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public boolean hasPose() {
                return (this.poseBuilder_ == null && this.pose_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2D getPose() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D = this.pose_;
                    return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPose(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(pose2D);
                } else {
                    if (pose2D == null) {
                        throw null;
                    }
                    this.pose_ = pose2D;
                    onChanged();
                }
                return this;
            }

            public Builder setPose(Pose2D.Builder builder) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.pose_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePose(Pose2D pose2D) {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Pose2D pose2D2 = this.pose_;
                    if (pose2D2 != null) {
                        this.pose_ = Pose2D.newBuilder(pose2D2).mergeFrom(pose2D).buildPartial();
                    } else {
                        this.pose_ = pose2D;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(pose2D);
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

            public Pose2D.Builder getPoseBuilder() {
                onChanged();
                return getPoseFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.RobotScheduleInfoOrBuilder
            public Pose2DOrBuilder getPoseOrBuilder() {
                SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> singleFieldBuilderV3 = this.poseBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Pose2D pose2D = this.pose_;
                return pose2D == null ? Pose2D.getDefaultInstance() : pose2D;
            }

            private SingleFieldBuilderV3<Pose2D, Pose2D.Builder, Pose2DOrBuilder> getPoseFieldBuilder() {
                if (this.poseBuilder_ == null) {
                    this.poseBuilder_ = new SingleFieldBuilderV3<>(getPose(), getParentForChildren(), isClean());
                    this.pose_ = null;
                }
                return this.poseBuilder_;
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

        public static RobotScheduleInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RobotScheduleInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<RobotScheduleInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RobotScheduleInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes7.dex */
    public static final class MotionInfo extends GeneratedMessageV3 implements MotionInfoOrBuilder {
        private static final MotionInfo DEFAULT_INSTANCE = new MotionInfo();
        private static final Parser<MotionInfo> PARSER = new AbstractParser<MotionInfo>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.MotionInfo.1
            @Override // com.google.protobuf.Parser
            public MotionInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MotionInfo(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int V_FIELD_NUMBER = 1;
        public static final int W_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        /* renamed from: v_ */
        private int f7461v_;

        /* renamed from: w_ */
        private int f7462w_;

        private MotionInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MotionInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.f7461v_ = 0;
            this.f7462w_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MotionInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.f7461v_ = codedInputStream.readSInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.f7462w_ = codedInputStream.readSInt32();
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
            return SchedulerInfo.access$2700();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.access$2800().ensureFieldAccessorsInitialized(MotionInfo.class, Builder.class);
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MotionInfoOrBuilder
        public int getV() {
            return this.f7461v_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MotionInfoOrBuilder
        public int getW() {
            return this.f7462w_;
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
            int i = this.f7461v_;
            if (i != 0) {
                codedOutputStream.writeSInt32(1, i);
            }
            int i2 = this.f7462w_;
            if (i2 != 0) {
                codedOutputStream.writeSInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.f7461v_;
            int computeSInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeSInt32Size(1, i2) : 0;
            int i3 = this.f7462w_;
            if (i3 != 0) {
                computeSInt32Size += CodedOutputStream.computeSInt32Size(2, i3);
            }
            int serializedSize = computeSInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MotionInfo)) {
                return super.equals(obj);
            }
            MotionInfo motionInfo = (MotionInfo) obj;
            return ((getV() == motionInfo.getV()) && getW() == motionInfo.getW()) && this.unknownFields.equals(motionInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getV()) * 37) + 2) * 53) + getW()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static MotionInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MotionInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MotionInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MotionInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MotionInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MotionInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MotionInfo parseFrom(InputStream inputStream) throws IOException {
            return (MotionInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MotionInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MotionInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MotionInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MotionInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MotionInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MotionInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MotionInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MotionInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MotionInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MotionInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MotionInfo motionInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(motionInfo);
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

        /* loaded from: classes7.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MotionInfoOrBuilder {

            /* renamed from: v_ */
            private int f7463v_;

            /* renamed from: w_ */
            private int f7464w_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.access$2700();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.access$2800().ensureFieldAccessorsInitialized(MotionInfo.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MotionInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.f7463v_ = 0;
                this.f7464w_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.access$2700();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MotionInfo getDefaultInstanceForType() {
                return MotionInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MotionInfo build() {
                MotionInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MotionInfo buildPartial() {
                MotionInfo motionInfo = new MotionInfo(this);
                motionInfo.f7461v_ = this.f7463v_;
                motionInfo.f7462w_ = this.f7464w_;
                onBuilt();
                return motionInfo;
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
                if (message instanceof MotionInfo) {
                    return mergeFrom((MotionInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MotionInfo motionInfo) {
                if (motionInfo == MotionInfo.getDefaultInstance()) {
                    return this;
                }
                if (motionInfo.getV() != 0) {
                    setV(motionInfo.getV());
                }
                if (motionInfo.getW() != 0) {
                    setW(motionInfo.getW());
                }
                mergeUnknownFields(motionInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                MotionInfo motionInfo = null;
                try {
                    try {
                        MotionInfo motionInfo2 = (MotionInfo) MotionInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (motionInfo2 != null) {
                            mergeFrom(motionInfo2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        MotionInfo motionInfo3 = (MotionInfo) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            motionInfo = motionInfo3;
                            if (motionInfo != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (motionInfo != null) {
                        mergeFrom(motionInfo);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MotionInfoOrBuilder
            public int getV() {
                return this.f7463v_;
            }

            public Builder setV(int i) {
                this.f7463v_ = i;
                onChanged();
                return this;
            }

            public Builder clearV() {
                this.f7463v_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MotionInfoOrBuilder
            public int getW() {
                return this.f7464w_;
            }

            public Builder setW(int i) {
                this.f7464w_ = i;
                onChanged();
                return this;
            }

            public Builder clearW() {
                this.f7464w_ = 0;
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

        public static MotionInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MotionInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MotionInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MotionInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class CurrentRobotInfo extends GeneratedMessageV3 implements CurrentRobotInfoOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        private static final CurrentRobotInfo DEFAULT_INSTANCE = new CurrentRobotInfo();
        private static final Parser<CurrentRobotInfo> PARSER = new AbstractParser<CurrentRobotInfo>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfo.1
            @Override // com.google.protobuf.Parser
            public CurrentRobotInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CurrentRobotInfo(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private RobotScheduleInfo data_;
        private byte memoizedIsInitialized;
        private volatile Object type_;

        private CurrentRobotInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CurrentRobotInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.type_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CurrentRobotInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
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
                                    this.type_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    RobotScheduleInfo.Builder builder = this.data_ != null ? this.data_.toBuilder() : null;
                                    RobotScheduleInfo robotScheduleInfo = (RobotScheduleInfo) codedInputStream.readMessage(RobotScheduleInfo.parser(), extensionRegistryLite);
                                    this.data_ = robotScheduleInfo;
                                    if (builder != null) {
                                        builder.mergeFrom(robotScheduleInfo);
                                        this.data_ = builder.buildPartial();
                                    }
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SchedulerInfo.f7451x60829f49;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.f7452xaa666cc7.ensureFieldAccessorsInitialized(CurrentRobotInfo.class, Builder.class);
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.type_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
        public boolean hasData() {
            return this.data_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
        public RobotScheduleInfo getData() {
            RobotScheduleInfo robotScheduleInfo = this.data_;
            return robotScheduleInfo == null ? RobotScheduleInfo.getDefaultInstance() : robotScheduleInfo;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
        public RobotScheduleInfoOrBuilder getDataOrBuilder() {
            return getData();
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
            if (!getTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
            }
            if (this.data_ != null) {
                codedOutputStream.writeMessage(2, getData());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getTypeBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.type_);
            if (this.data_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(2, getData());
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
            if (!(obj instanceof CurrentRobotInfo)) {
                return super.equals(obj);
            }
            CurrentRobotInfo currentRobotInfo = (CurrentRobotInfo) obj;
            boolean z = (getType().equals(currentRobotInfo.getType())) && hasData() == currentRobotInfo.hasData();
            if (hasData()) {
                z = z && getData().equals(currentRobotInfo.getData());
            }
            return z && this.unknownFields.equals(currentRobotInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode();
            if (hasData()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getData().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CurrentRobotInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CurrentRobotInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CurrentRobotInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CurrentRobotInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CurrentRobotInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CurrentRobotInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CurrentRobotInfo parseFrom(InputStream inputStream) throws IOException {
            return (CurrentRobotInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CurrentRobotInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CurrentRobotInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CurrentRobotInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CurrentRobotInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CurrentRobotInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CurrentRobotInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CurrentRobotInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CurrentRobotInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CurrentRobotInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CurrentRobotInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CurrentRobotInfo currentRobotInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(currentRobotInfo);
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
          classes.dex
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CurrentRobotInfoOrBuilder {
            private SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> dataBuilder_;
            private RobotScheduleInfo data_;
            private Object type_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.f7451x60829f49;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.f7452xaa666cc7.ensureFieldAccessorsInitialized(CurrentRobotInfo.class, Builder.class);
            }

            private Builder() {
                this.type_ = "";
                this.data_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.type_ = "";
                this.data_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CurrentRobotInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.type_ = "";
                if (this.dataBuilder_ == null) {
                    this.data_ = null;
                } else {
                    this.data_ = null;
                    this.dataBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.f7451x60829f49;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CurrentRobotInfo getDefaultInstanceForType() {
                return CurrentRobotInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CurrentRobotInfo build() {
                CurrentRobotInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CurrentRobotInfo buildPartial() {
                CurrentRobotInfo currentRobotInfo = new CurrentRobotInfo(this);
                currentRobotInfo.type_ = this.type_;
                SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    currentRobotInfo.data_ = this.data_;
                } else {
                    currentRobotInfo.data_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return currentRobotInfo;
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
                if (message instanceof CurrentRobotInfo) {
                    return mergeFrom((CurrentRobotInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CurrentRobotInfo currentRobotInfo) {
                if (currentRobotInfo == CurrentRobotInfo.getDefaultInstance()) {
                    return this;
                }
                if (!currentRobotInfo.getType().isEmpty()) {
                    this.type_ = currentRobotInfo.type_;
                    onChanged();
                }
                if (currentRobotInfo.hasData()) {
                    mergeData(currentRobotInfo.getData());
                }
                mergeUnknownFields(currentRobotInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CurrentRobotInfo currentRobotInfo = null;
                try {
                    try {
                        CurrentRobotInfo currentRobotInfo2 = (CurrentRobotInfo) CurrentRobotInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (currentRobotInfo2 != null) {
                            mergeFrom(currentRobotInfo2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CurrentRobotInfo currentRobotInfo3 = (CurrentRobotInfo) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            currentRobotInfo = currentRobotInfo3;
                            if (currentRobotInfo != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (currentRobotInfo != null) {
                        mergeFrom(currentRobotInfo);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
            public String getType() {
                Object obj = this.type_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.type_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
            public ByteString getTypeBytes() {
                Object obj = this.type_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.type_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setType(String str) {
                if (str == null) {
                    throw null;
                }
                this.type_ = str;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = CurrentRobotInfo.getDefaultInstance().getType();
                onChanged();
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    CurrentRobotInfo.checkByteStringIsUtf8(byteString);
                    this.type_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
            public boolean hasData() {
                return (this.dataBuilder_ == null && this.data_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
            public RobotScheduleInfo getData() {
                SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RobotScheduleInfo robotScheduleInfo = this.data_;
                    return robotScheduleInfo == null ? RobotScheduleInfo.getDefaultInstance() : robotScheduleInfo;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setData(RobotScheduleInfo robotScheduleInfo) {
                SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(robotScheduleInfo);
                } else {
                    if (robotScheduleInfo == null) {
                        throw null;
                    }
                    this.data_ = robotScheduleInfo;
                    onChanged();
                }
                return this;
            }

            public Builder setData(RobotScheduleInfo.Builder builder) {
                SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.data_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeData(RobotScheduleInfo robotScheduleInfo) {
                SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RobotScheduleInfo robotScheduleInfo2 = this.data_;
                    if (robotScheduleInfo2 != null) {
                        this.data_ = RobotScheduleInfo.newBuilder(robotScheduleInfo2).mergeFrom(robotScheduleInfo).buildPartial();
                    } else {
                        this.data_ = robotScheduleInfo;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(robotScheduleInfo);
                }
                return this;
            }

            public Builder clearData() {
                if (this.dataBuilder_ == null) {
                    this.data_ = null;
                    onChanged();
                } else {
                    this.data_ = null;
                    this.dataBuilder_ = null;
                }
                return this;
            }

            public RobotScheduleInfo.Builder getDataBuilder() {
                onChanged();
                return getDataFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.CurrentRobotInfoOrBuilder
            public RobotScheduleInfoOrBuilder getDataOrBuilder() {
                SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RobotScheduleInfo robotScheduleInfo = this.data_;
                return robotScheduleInfo == null ? RobotScheduleInfo.getDefaultInstance() : robotScheduleInfo;
            }

            private SingleFieldBuilderV3<RobotScheduleInfo, RobotScheduleInfo.Builder, RobotScheduleInfoOrBuilder> getDataFieldBuilder() {
                if (this.dataBuilder_ == null) {
                    this.dataBuilder_ = new SingleFieldBuilderV3<>(getData(), getParentForChildren(), isClean());
                    this.data_ = null;
                }
                return this.dataBuilder_;
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

        public static CurrentRobotInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CurrentRobotInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CurrentRobotInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CurrentRobotInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class ScheduleInfo extends GeneratedMessageV3 implements ScheduleInfoOrBuilder {
        public static final int DATA_FIELD_NUMBER = 2;
        public static final int MAP_FLAG_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private CurrentRobotInfo data_;
        private volatile Object mapFlag_;
        private byte memoizedIsInitialized;
        private static final ScheduleInfo DEFAULT_INSTANCE = new ScheduleInfo();
        private static final Parser<ScheduleInfo> PARSER = new AbstractParser<ScheduleInfo>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfo.1
            @Override // com.google.protobuf.Parser
            public ScheduleInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ScheduleInfo(codedInputStream, extensionRegistryLite);
            }
        };

        private ScheduleInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ScheduleInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.mapFlag_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ScheduleInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
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
                                    this.mapFlag_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    CurrentRobotInfo.Builder builder = this.data_ != null ? this.data_.toBuilder() : null;
                                    CurrentRobotInfo currentRobotInfo = (CurrentRobotInfo) codedInputStream.readMessage(CurrentRobotInfo.parser(), extensionRegistryLite);
                                    this.data_ = currentRobotInfo;
                                    if (builder != null) {
                                        builder.mergeFrom(currentRobotInfo);
                                        this.data_ = builder.buildPartial();
                                    }
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SchedulerInfo.f7459x5d813963;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.f7460x31e4ce1.ensureFieldAccessorsInitialized(ScheduleInfo.class, Builder.class);
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
        public String getMapFlag() {
            Object obj = this.mapFlag_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.mapFlag_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
        public ByteString getMapFlagBytes() {
            Object obj = this.mapFlag_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mapFlag_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
        public boolean hasData() {
            return this.data_ != null;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
        public CurrentRobotInfo getData() {
            CurrentRobotInfo currentRobotInfo = this.data_;
            return currentRobotInfo == null ? CurrentRobotInfo.getDefaultInstance() : currentRobotInfo;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
        public CurrentRobotInfoOrBuilder getDataOrBuilder() {
            return getData();
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
            if (!getMapFlagBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.mapFlag_);
            }
            if (this.data_ != null) {
                codedOutputStream.writeMessage(2, getData());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getMapFlagBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.mapFlag_);
            if (this.data_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(2, getData());
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
            if (!(obj instanceof ScheduleInfo)) {
                return super.equals(obj);
            }
            ScheduleInfo scheduleInfo = (ScheduleInfo) obj;
            boolean z = (getMapFlag().equals(scheduleInfo.getMapFlag())) && hasData() == scheduleInfo.hasData();
            if (hasData()) {
                z = z && getData().equals(scheduleInfo.getData());
            }
            return z && this.unknownFields.equals(scheduleInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMapFlag().hashCode();
            if (hasData()) {
                hashCode = (((hashCode * 37) + 2) * 53) + getData().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ScheduleInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ScheduleInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ScheduleInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ScheduleInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ScheduleInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ScheduleInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ScheduleInfo parseFrom(InputStream inputStream) throws IOException {
            return (ScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ScheduleInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ScheduleInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ScheduleInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ScheduleInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScheduleInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ScheduleInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ScheduleInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScheduleInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ScheduleInfo scheduleInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(scheduleInfo);
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
          classes.dex
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScheduleInfoOrBuilder {
            private SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> dataBuilder_;
            private CurrentRobotInfo data_;
            private Object mapFlag_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.f7459x5d813963;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.f7460x31e4ce1.ensureFieldAccessorsInitialized(ScheduleInfo.class, Builder.class);
            }

            private Builder() {
                this.mapFlag_ = "";
                this.data_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.mapFlag_ = "";
                this.data_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ScheduleInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mapFlag_ = "";
                if (this.dataBuilder_ == null) {
                    this.data_ = null;
                } else {
                    this.data_ = null;
                    this.dataBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.f7459x5d813963;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ScheduleInfo getDefaultInstanceForType() {
                return ScheduleInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ScheduleInfo build() {
                ScheduleInfo buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ScheduleInfo buildPartial() {
                ScheduleInfo scheduleInfo = new ScheduleInfo(this);
                scheduleInfo.mapFlag_ = this.mapFlag_;
                SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    scheduleInfo.data_ = this.data_;
                } else {
                    scheduleInfo.data_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return scheduleInfo;
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
                if (message instanceof ScheduleInfo) {
                    return mergeFrom((ScheduleInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ScheduleInfo scheduleInfo) {
                if (scheduleInfo == ScheduleInfo.getDefaultInstance()) {
                    return this;
                }
                if (!scheduleInfo.getMapFlag().isEmpty()) {
                    this.mapFlag_ = scheduleInfo.mapFlag_;
                    onChanged();
                }
                if (scheduleInfo.hasData()) {
                    mergeData(scheduleInfo.getData());
                }
                mergeUnknownFields(scheduleInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ScheduleInfo scheduleInfo = null;
                try {
                    try {
                        ScheduleInfo scheduleInfo2 = (ScheduleInfo) ScheduleInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (scheduleInfo2 != null) {
                            mergeFrom(scheduleInfo2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ScheduleInfo scheduleInfo3 = (ScheduleInfo) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            scheduleInfo = scheduleInfo3;
                            if (scheduleInfo != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (scheduleInfo != null) {
                        mergeFrom(scheduleInfo);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
            public String getMapFlag() {
                Object obj = this.mapFlag_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mapFlag_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
            public ByteString getMapFlagBytes() {
                Object obj = this.mapFlag_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mapFlag_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMapFlag(String str) {
                if (str == null) {
                    throw null;
                }
                this.mapFlag_ = str;
                onChanged();
                return this;
            }

            public Builder clearMapFlag() {
                this.mapFlag_ = ScheduleInfo.getDefaultInstance().getMapFlag();
                onChanged();
                return this;
            }

            public Builder setMapFlagBytes(ByteString byteString) {
                if (byteString != null) {
                    ScheduleInfo.checkByteStringIsUtf8(byteString);
                    this.mapFlag_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
            public boolean hasData() {
                return (this.dataBuilder_ == null && this.data_ == null) ? false : true;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
            public CurrentRobotInfo getData() {
                SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CurrentRobotInfo currentRobotInfo = this.data_;
                    return currentRobotInfo == null ? CurrentRobotInfo.getDefaultInstance() : currentRobotInfo;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setData(CurrentRobotInfo currentRobotInfo) {
                SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(currentRobotInfo);
                } else {
                    if (currentRobotInfo == null) {
                        throw null;
                    }
                    this.data_ = currentRobotInfo;
                    onChanged();
                }
                return this;
            }

            public Builder setData(CurrentRobotInfo.Builder builder) {
                SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.data_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeData(CurrentRobotInfo currentRobotInfo) {
                SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CurrentRobotInfo currentRobotInfo2 = this.data_;
                    if (currentRobotInfo2 != null) {
                        this.data_ = CurrentRobotInfo.newBuilder(currentRobotInfo2).mergeFrom(currentRobotInfo).buildPartial();
                    } else {
                        this.data_ = currentRobotInfo;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(currentRobotInfo);
                }
                return this;
            }

            public Builder clearData() {
                if (this.dataBuilder_ == null) {
                    this.data_ = null;
                    onChanged();
                } else {
                    this.data_ = null;
                    this.dataBuilder_ = null;
                }
                return this;
            }

            public CurrentRobotInfo.Builder getDataBuilder() {
                onChanged();
                return getDataFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.ScheduleInfoOrBuilder
            public CurrentRobotInfoOrBuilder getDataOrBuilder() {
                SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> singleFieldBuilderV3 = this.dataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CurrentRobotInfo currentRobotInfo = this.data_;
                return currentRobotInfo == null ? CurrentRobotInfo.getDefaultInstance() : currentRobotInfo;
            }

            private SingleFieldBuilderV3<CurrentRobotInfo, CurrentRobotInfo.Builder, CurrentRobotInfoOrBuilder> getDataFieldBuilder() {
                if (this.dataBuilder_ == null) {
                    this.dataBuilder_ = new SingleFieldBuilderV3<>(getData(), getParentForChildren(), isClean());
                    this.data_ = null;
                }
                return this.dataBuilder_;
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

        public static ScheduleInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScheduleInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ScheduleInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ScheduleInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class MulticastProtocol extends GeneratedMessageV3 implements MulticastProtocolOrBuilder {
        public static final int IP_FIELD_NUMBER = 2;
        public static final int MSG_ID_FIELD_NUMBER = 1;
        public static final int MSG_SEQ_FIELD_NUMBER = 4;
        public static final int ORIGIN_IP_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private volatile Object ip_;
        private byte memoizedIsInitialized;
        private int msgId_;
        private long msgSeq_;
        private volatile Object originIp_;
        private static final MulticastProtocol DEFAULT_INSTANCE = new MulticastProtocol();
        private static final Parser<MulticastProtocol> PARSER = new AbstractParser<MulticastProtocol>() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocol.1
            @Override // com.google.protobuf.Parser
            public MulticastProtocol parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MulticastProtocol(codedInputStream, extensionRegistryLite);
            }
        };

        private MulticastProtocol(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MulticastProtocol() {
            this.memoizedIsInitialized = (byte) -1;
            this.msgId_ = 0;
            this.ip_ = "";
            this.originIp_ = "";
            this.msgSeq_ = 0L;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MulticastProtocol(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.msgId_ = codedInputStream.readInt32();
                            } else if (readTag == 18) {
                                this.ip_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.originIp_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 32) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.msgSeq_ = codedInputStream.readUInt64();
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
            return SchedulerInfo.f7453xba5b9344;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SchedulerInfo.f7454xb535c2.ensureFieldAccessorsInitialized(MulticastProtocol.class, Builder.class);
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
        public int getMsgId() {
            return this.msgId_;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
        public String getIp() {
            Object obj = this.ip_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.ip_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
        public ByteString getIpBytes() {
            Object obj = this.ip_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ip_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
        public String getOriginIp() {
            Object obj = this.originIp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.originIp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
        public ByteString getOriginIpBytes() {
            Object obj = this.originIp_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.originIp_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
        public long getMsgSeq() {
            return this.msgSeq_;
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
            int i = this.msgId_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (!getIpBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.ip_);
            }
            if (!getOriginIpBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.originIp_);
            }
            long j = this.msgSeq_;
            if (j != 0) {
                codedOutputStream.writeUInt64(4, j);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.msgId_;
            int computeInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0;
            if (!getIpBytes().isEmpty()) {
                computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.ip_);
            }
            if (!getOriginIpBytes().isEmpty()) {
                computeInt32Size += GeneratedMessageV3.computeStringSize(3, this.originIp_);
            }
            long j = this.msgSeq_;
            if (j != 0) {
                computeInt32Size += CodedOutputStream.computeUInt64Size(4, j);
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
            if (!(obj instanceof MulticastProtocol)) {
                return super.equals(obj);
            }
            MulticastProtocol multicastProtocol = (MulticastProtocol) obj;
            return ((((getMsgId() == multicastProtocol.getMsgId()) && getIp().equals(multicastProtocol.getIp())) && getOriginIp().equals(multicastProtocol.getOriginIp())) && (getMsgSeq() > multicastProtocol.getMsgSeq() ? 1 : (getMsgSeq() == multicastProtocol.getMsgSeq() ? 0 : -1)) == 0) && this.unknownFields.equals(multicastProtocol.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMsgId()) * 37) + 2) * 53) + getIp().hashCode()) * 37) + 3) * 53) + getOriginIp().hashCode()) * 37) + 4) * 53) + Internal.hashLong(getMsgSeq())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static MulticastProtocol parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MulticastProtocol parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MulticastProtocol parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MulticastProtocol parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MulticastProtocol parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MulticastProtocol parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MulticastProtocol parseFrom(InputStream inputStream) throws IOException {
            return (MulticastProtocol) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MulticastProtocol parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MulticastProtocol) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MulticastProtocol parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MulticastProtocol) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MulticastProtocol parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MulticastProtocol) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MulticastProtocol parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MulticastProtocol) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MulticastProtocol parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MulticastProtocol) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MulticastProtocol multicastProtocol) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(multicastProtocol);
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
          classes.dex
          classes6.dex
          classes7.dex
         */
        /* loaded from: classes2.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MulticastProtocolOrBuilder {
            private Object ip_;
            private int msgId_;
            private long msgSeq_;
            private Object originIp_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return SchedulerInfo.f7453xba5b9344;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return SchedulerInfo.f7454xb535c2.ensureFieldAccessorsInitialized(MulticastProtocol.class, Builder.class);
            }

            private Builder() {
                this.ip_ = "";
                this.originIp_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.ip_ = "";
                this.originIp_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MulticastProtocol.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.msgId_ = 0;
                this.ip_ = "";
                this.originIp_ = "";
                this.msgSeq_ = 0L;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return SchedulerInfo.f7453xba5b9344;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MulticastProtocol getDefaultInstanceForType() {
                return MulticastProtocol.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MulticastProtocol build() {
                MulticastProtocol buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MulticastProtocol buildPartial() {
                MulticastProtocol multicastProtocol = new MulticastProtocol(this);
                multicastProtocol.msgId_ = this.msgId_;
                multicastProtocol.ip_ = this.ip_;
                multicastProtocol.originIp_ = this.originIp_;
                multicastProtocol.msgSeq_ = this.msgSeq_;
                onBuilt();
                return multicastProtocol;
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
                if (message instanceof MulticastProtocol) {
                    return mergeFrom((MulticastProtocol) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MulticastProtocol multicastProtocol) {
                if (multicastProtocol == MulticastProtocol.getDefaultInstance()) {
                    return this;
                }
                if (multicastProtocol.getMsgId() != 0) {
                    setMsgId(multicastProtocol.getMsgId());
                }
                if (!multicastProtocol.getIp().isEmpty()) {
                    this.ip_ = multicastProtocol.ip_;
                    onChanged();
                }
                if (!multicastProtocol.getOriginIp().isEmpty()) {
                    this.originIp_ = multicastProtocol.originIp_;
                    onChanged();
                }
                if (multicastProtocol.getMsgSeq() != 0) {
                    setMsgSeq(multicastProtocol.getMsgSeq());
                }
                mergeUnknownFields(multicastProtocol.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                MulticastProtocol multicastProtocol = null;
                try {
                    try {
                        MulticastProtocol multicastProtocol2 = (MulticastProtocol) MulticastProtocol.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (multicastProtocol2 != null) {
                            mergeFrom(multicastProtocol2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        MulticastProtocol multicastProtocol3 = (MulticastProtocol) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            multicastProtocol = multicastProtocol3;
                            if (multicastProtocol != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (multicastProtocol != null) {
                        mergeFrom(multicastProtocol);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
            public int getMsgId() {
                return this.msgId_;
            }

            public Builder setMsgId(int i) {
                this.msgId_ = i;
                onChanged();
                return this;
            }

            public Builder clearMsgId() {
                this.msgId_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
            public String getIp() {
                Object obj = this.ip_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.ip_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
            public ByteString getIpBytes() {
                Object obj = this.ip_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ip_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setIp(String str) {
                if (str == null) {
                    throw null;
                }
                this.ip_ = str;
                onChanged();
                return this;
            }

            public Builder clearIp() {
                this.ip_ = MulticastProtocol.getDefaultInstance().getIp();
                onChanged();
                return this;
            }

            public Builder setIpBytes(ByteString byteString) {
                if (byteString != null) {
                    MulticastProtocol.checkByteStringIsUtf8(byteString);
                    this.ip_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
            public String getOriginIp() {
                Object obj = this.originIp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.originIp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
            public ByteString getOriginIpBytes() {
                Object obj = this.originIp_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.originIp_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setOriginIp(String str) {
                if (str == null) {
                    throw null;
                }
                this.originIp_ = str;
                onChanged();
                return this;
            }

            public Builder clearOriginIp() {
                this.originIp_ = MulticastProtocol.getDefaultInstance().getOriginIp();
                onChanged();
                return this;
            }

            public Builder setOriginIpBytes(ByteString byteString) {
                if (byteString != null) {
                    MulticastProtocol.checkByteStringIsUtf8(byteString);
                    this.originIp_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.schedulerlib.proto.SchedulerInfo.MulticastProtocolOrBuilder
            public long getMsgSeq() {
                return this.msgSeq_;
            }

            public Builder setMsgSeq(long j) {
                this.msgSeq_ = j;
                onChanged();
                return this;
            }

            public Builder clearMsgSeq() {
                this.msgSeq_ = 0L;
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

        public static MulticastProtocol getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MulticastProtocol> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MulticastProtocol> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MulticastProtocol getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0013SchedulerInfo.proto\u0012\u001fcom.pudutech.schedulerlib.proto\"+\n\u0006Pose2D\u0012\t\n\u0001x\u0018\u0001 \u0001(\u0002\u0012\t\n\u0001y\u0018\u0002 \u0001(\u0002\u0012\u000b\n\u0003yaw\u0018\u0003 \u0001(\u0002\"×\u0005\n\u0011RobotScheduleInfo\u0012\u0010\n\brobot_id\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006msg_id\u0018\u0002 \u0001(\u0004\u0012\u0010\n\bmsg_type\u0018\u0003 \u0001(\t\u0012\u0010\n\btopo_odd\u0018\u0004 \u0001(\b\u0012\u0015\n\rtopology_path\u0018\u0005 \u0003(\r\u0012\u0014\n\fref_robot_id\u0018\u0006 \u0001(\t\u0012\u0016\n\u000eavoid_robot_id\u0018\u0007 \u0001(\t\u0012Z\n\u000fscheduling_mode\u0018\b \u0001(\u000e2A.com.pudutech.schedulerlib.proto.RobotScheduleInfo.SchedulingMode\u0012\u0015\n\ravoid_node_id\u0018\t \u0001(\r\u0012\u0016\n\u000eavoid_track_id\u0018\n \u0001(\r\u0012:\n\tnext_goal\u0018\u000b \u0001(\u000b2'.com.pudutech.schedulerlib.proto.Pose2D\u0012;\n\nfinal_goal\u0018\f \u0001(\u000b2'.com.pudutech.schedulerlib.proto.Pose2D\u0012=\n\fvirtual_goal\u0018\r \u0001(\u000b2'.com.pudutech.schedulerlib.proto.Pose2D\u00125\n\u0004pose\u0018\u000e \u0001(\u000b2'.com.pudutech.schedulerlib.proto.Pose2D\"¼\u0001\n\u000eSchedulingMode\u0012\b\n\u0004Free\u0010\u0000\u0012\n\n\u0006Normal\u0010\u0001\u0012\u000b\n\u0007Arrived\u0010\u0002\u0012\t\n\u0005Avoid\u0010\u0003\u0012\u000f\n\u000bAvoidNormal\u0010\u0004\u0012\r\n\tAvoidWait\u0010\u0005\u0012\t\n\u0005Error\u0010\u0006\u0012\r\n\tAvoidLeft\u0010\u0007\u0012\u000e\n\nAvoidRight\u0010\b\u0012\u0010\n\fNormalChange\u0010\t\u0012\u000f\n\u000bAvoidChange\u0010\n\u0012\u000f\n\u000bNormalRight\u0010\u000b\"b\n\u0010CurrentRobotInfo\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012@\n\u0004data\u0018\u0002 \u0001(\u000b22.com.pudutech.schedulerlib.proto.RobotScheduleInfo\"a\n\fScheduleInfo\u0012\u0010\n\bmap_flag\u0018\u0001 \u0001(\t\u0012?\n\u0004data\u0018\u0002 \u0001(\u000b21.com.pudutech.schedulerlib.proto.CurrentRobotInfo\"S\n\u0011MulticastProtocol\u0012\u000e\n\u0006msg_id\u0018\u0001 \u0001(\u0005\u0012\n\n\u0002ip\u0018\u0002 \u0001(\t\u0012\u0011\n\torigin_ip\u0018\u0003 \u0001(\t\u0012\u000f\n\u0007msg_seq\u0018\u0004 \u0001(\u0004b\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.pudutech.schedulerlib.proto.SchedulerInfo.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = SchedulerInfo.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        f7455x69e44b65 = descriptor2;
        f7456xf4d83ce3 = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"X", "Y", "Yaw"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        f7457xd7fbc525 = descriptor3;
        f7458xe7b9f6a3 = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"RobotId", "MsgId", "MsgType", "TopoOdd", "TopologyPath", "RefRobotId", "AvoidRobotId", "SchedulingMode", "AvoidNodeId", "AvoidTrackId", "NextGoal", "FinalGoal", "VirtualGoal", "Pose"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        f7451x60829f49 = descriptor4;
        f7452xaa666cc7 = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Type", "Data"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        f7459x5d813963 = descriptor5;
        f7460x31e4ce1 = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"MapFlag", "Data"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        f7453xba5b9344 = descriptor6;
        f7454xb535c2 = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"MsgId", "Ip", "OriginIp", "MsgSeq"});
    }
}
