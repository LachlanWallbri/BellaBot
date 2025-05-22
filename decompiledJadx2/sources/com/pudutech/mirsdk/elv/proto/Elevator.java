package com.pudutech.mirsdk.elv.proto;

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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public final class Elevator {
    private static Descriptors.FileDescriptor descriptor;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CallElvAck_descriptor */
    private static final Descriptors.Descriptor f5792x19e1db3a;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CallElvAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5793xeec727b8;
    private static final Descriptors.Descriptor internal_static_com_pudutech_mirsdk_elv_proto_CallElv_descriptor;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CallElv_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5794x39601c77;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelElvAck_descriptor */
    private static final Descriptors.Descriptor f5795x5a99d19e;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelElvAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5796x35e47a1c;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelElv_descriptor */
    private static final Descriptors.Descriptor f5797x1cbd4315;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelElv_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5798x45d68493;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvEnteredAck_descriptor */
    private static final Descriptors.Descriptor f5799x956553c9;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvEnteredAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5800x1d8ca147;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvEntered_descriptor */
    private static final Descriptors.Descriptor f5801x2045e8ca;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvEntered_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5802x314ca548;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvLeftAck_descriptor */
    private static final Descriptors.Descriptor f5803xb58d425f;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvLeftAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5804xa5ff99dd;
    private static final Descriptors.Descriptor internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_descriptor;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5805x32e06472;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvState_descriptor */
    private static final Descriptors.Descriptor f5806x94909630;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvState_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5807xdde8cae;
    private static final Descriptors.Descriptor internal_static_com_pudutech_mirsdk_elv_proto_Elv_descriptor;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_Elv_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5808xe078e79;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_EnterElvAck_descriptor */
    private static final Descriptors.Descriptor f5809xe2e21578;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_EnterElvAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5810x218043f6;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_EnterElv_descriptor */
    private static final Descriptors.Descriptor f5811xff5d1d7b;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_EnterElv_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5812x734098f9;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_LeaveElvAck_descriptor */
    private static final Descriptors.Descriptor f5813x9b0ea779;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_LeaveElvAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5814x893f44f7;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_LeaveElv_descriptor */
    private static final Descriptors.Descriptor f5815x23d4b31a;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_LeaveElv_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5816x1bd41f98;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_PrepareRideAck_descriptor */
    private static final Descriptors.Descriptor f5817x9c9f4de8;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_PrepareRideAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5818xa4760c66;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_PrepareRide_descriptor */
    private static final Descriptors.Descriptor f5819x7e303b0b;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_PrepareRide_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5820x96252689;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_QueryElvResp_descriptor */
    private static final Descriptors.Descriptor f5821x74856efb;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_QueryElvResp_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5822x9bf6a79;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_QueryElv_descriptor */
    private static final Descriptors.Descriptor f5823x32cb0b6b;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_QueryElv_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5824x449996e9;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface CallElvAckOrBuilder extends MessageOrBuilder {
        String getFloor();

        ByteString getFloorBytes();

        ElvState getState();

        ElvStateOrBuilder getStateOrBuilder();

        boolean hasState();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface CallElvOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();

        String getDestFloor();

        ByteString getDestFloorBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface CancelElvAckOrBuilder extends MessageOrBuilder {
        String getFloor();

        ByteString getFloorBytes();

        ElvState getState();

        ElvStateOrBuilder getStateOrBuilder();

        boolean hasState();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface CancelElvOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ElvEnteredAckOrBuilder extends MessageOrBuilder {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ElvEnteredOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ElvLeftAckOrBuilder extends MessageOrBuilder {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ElvLeftOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ElvOrBuilder extends MessageOrBuilder {
        CallElv getCallElv();

        CallElvAck getCallElvAck();

        CallElvAckOrBuilder getCallElvAckOrBuilder();

        CallElvOrBuilder getCallElvOrBuilder();

        CancelElv getCancelElv();

        CancelElvAck getCancelElvAck();

        CancelElvAckOrBuilder getCancelElvAckOrBuilder();

        CancelElvOrBuilder getCancelElvOrBuilder();

        ElvEntered getElvEntered();

        ElvEnteredAck getElvEnteredAck();

        ElvEnteredAckOrBuilder getElvEnteredAckOrBuilder();

        ElvEnteredOrBuilder getElvEnteredOrBuilder();

        String getElvId();

        ByteString getElvIdBytes();

        ElvLeft getElvLeft();

        ElvLeftAck getElvLeftAck();

        ElvLeftAckOrBuilder getElvLeftAckOrBuilder();

        ElvLeftOrBuilder getElvLeftOrBuilder();

        EnterElv getEnterElv();

        EnterElvAck getEnterElvAck();

        EnterElvAckOrBuilder getEnterElvAckOrBuilder();

        EnterElvOrBuilder getEnterElvOrBuilder();

        LeaveElv getLeaveElv();

        LeaveElvAck getLeaveElvAck();

        LeaveElvAckOrBuilder getLeaveElvAckOrBuilder();

        LeaveElvOrBuilder getLeaveElvOrBuilder();

        Elv.MsgType getMsgType();

        int getMsgTypeValue();

        PrepareRide getPrepareRide();

        PrepareRideAck getPrepareRideAck();

        PrepareRideAckOrBuilder getPrepareRideAckOrBuilder();

        PrepareRideOrBuilder getPrepareRideOrBuilder();

        QueryElv getQueryElv();

        QueryElvOrBuilder getQueryElvOrBuilder();

        QueryElvResp getQueryElvResp();

        QueryElvRespOrBuilder getQueryElvRespOrBuilder();

        String getRobotId();

        ByteString getRobotIdBytes();

        long getSeq();

        long getTs();

        boolean hasCallElv();

        boolean hasCallElvAck();

        boolean hasCancelElv();

        boolean hasCancelElvAck();

        boolean hasElvEntered();

        boolean hasElvEnteredAck();

        boolean hasElvLeft();

        boolean hasElvLeftAck();

        boolean hasEnterElv();

        boolean hasEnterElvAck();

        boolean hasLeaveElv();

        boolean hasLeaveElvAck();

        boolean hasPrepareRide();

        boolean hasPrepareRideAck();

        boolean hasQueryElv();

        boolean hasQueryElvResp();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface ElvStateOrBuilder extends MessageOrBuilder {
        ElvState.State getState();

        int getStateValue();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface EnterElvAckOrBuilder extends MessageOrBuilder {
        double getDistance();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface EnterElvOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();

        int getEffTime();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface LeaveElvAckOrBuilder extends MessageOrBuilder {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface LeaveElvOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();

        int getEffTime();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface PrepareRideAckOrBuilder extends MessageOrBuilder {
        String getFloor();

        ByteString getFloorBytes();

        ElvState getState();

        ElvStateOrBuilder getStateOrBuilder();

        boolean hasState();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface PrepareRideOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface QueryElvOrBuilder extends MessageOrBuilder {
        String getCurrFloor();

        ByteString getCurrFloorBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public interface QueryElvRespOrBuilder extends MessageOrBuilder {
        String getFloort();

        ByteString getFloortBytes();

        ElvState getState();

        ElvStateOrBuilder getStateOrBuilder();

        boolean hasState();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Elevator() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class Elv extends GeneratedMessageV3 implements ElvOrBuilder {
        public static final int CALL_ELV_ACK_FIELD_NUMBER = 7;
        public static final int CALL_ELV_FIELD_NUMBER = 6;
        public static final int CANCEL_ELV_ACK_FIELD_NUMBER = 21;
        public static final int CANCEL_ELV_FIELD_NUMBER = 20;
        public static final int ELV_ENTERED_ACK_FIELD_NUMBER = 11;
        public static final int ELV_ENTERED_FIELD_NUMBER = 10;
        public static final int ELV_ID_FIELD_NUMBER = 1;
        public static final int ELV_LEFT_ACK_FIELD_NUMBER = 15;
        public static final int ELV_LEFT_FIELD_NUMBER = 14;
        public static final int ENTER_ELV_ACK_FIELD_NUMBER = 9;
        public static final int ENTER_ELV_FIELD_NUMBER = 8;
        public static final int LEAVE_ELV_ACK_FIELD_NUMBER = 13;
        public static final int LEAVE_ELV_FIELD_NUMBER = 12;
        public static final int MSG_TYPE_FIELD_NUMBER = 5;
        public static final int PREPARE_RIDE_ACK_FIELD_NUMBER = 19;
        public static final int PREPARE_RIDE_FIELD_NUMBER = 18;
        public static final int QUERY_ELV_FIELD_NUMBER = 16;
        public static final int QUERY_ELV_RESP_FIELD_NUMBER = 17;
        public static final int ROBOT_ID_FIELD_NUMBER = 2;
        public static final int SEQ_FIELD_NUMBER = 3;
        public static final int TS_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private CallElvAck callElvAck_;
        private CallElv callElv_;
        private CancelElvAck cancelElvAck_;
        private CancelElv cancelElv_;
        private ElvEnteredAck elvEnteredAck_;
        private ElvEntered elvEntered_;
        private volatile Object elvId_;
        private ElvLeftAck elvLeftAck_;
        private ElvLeft elvLeft_;
        private EnterElvAck enterElvAck_;
        private EnterElv enterElv_;
        private LeaveElvAck leaveElvAck_;
        private LeaveElv leaveElv_;
        private byte memoizedIsInitialized;
        private int msgType_;
        private PrepareRideAck prepareRideAck_;
        private PrepareRide prepareRide_;
        private QueryElvResp queryElvResp_;
        private QueryElv queryElv_;
        private volatile Object robotId_;
        private long seq_;
        private long ts_;
        private static final Elv DEFAULT_INSTANCE = new Elv();
        private static final Parser<Elv> PARSER = new AbstractParser<Elv>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.Elv.1
            @Override // com.google.protobuf.Parser
            public Elv parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Elv(codedInputStream, extensionRegistryLite);
            }
        };

        private Elv(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Elv() {
            this.memoizedIsInitialized = (byte) -1;
            this.elvId_ = "";
            this.robotId_ = "";
            this.seq_ = 0L;
            this.ts_ = 0L;
            this.msgType_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0012. Please report as an issue. */
        private Elv(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        switch (readTag) {
                            case 0:
                                z = true;
                            case 10:
                                this.elvId_ = codedInputStream.readStringRequireUtf8();
                            case 18:
                                this.robotId_ = codedInputStream.readStringRequireUtf8();
                            case 24:
                                this.seq_ = codedInputStream.readUInt64();
                            case 32:
                                this.ts_ = codedInputStream.readUInt64();
                            case 40:
                                this.msgType_ = codedInputStream.readEnum();
                            case 50:
                                CallElv.Builder builder = this.callElv_ != null ? this.callElv_.toBuilder() : null;
                                this.callElv_ = (CallElv) codedInputStream.readMessage(CallElv.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.callElv_);
                                    this.callElv_ = builder.buildPartial();
                                }
                            case 58:
                                CallElvAck.Builder builder2 = this.callElvAck_ != null ? this.callElvAck_.toBuilder() : null;
                                this.callElvAck_ = (CallElvAck) codedInputStream.readMessage(CallElvAck.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.callElvAck_);
                                    this.callElvAck_ = builder2.buildPartial();
                                }
                            case 66:
                                EnterElv.Builder builder3 = this.enterElv_ != null ? this.enterElv_.toBuilder() : null;
                                this.enterElv_ = (EnterElv) codedInputStream.readMessage(EnterElv.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.enterElv_);
                                    this.enterElv_ = builder3.buildPartial();
                                }
                            case 74:
                                EnterElvAck.Builder builder4 = this.enterElvAck_ != null ? this.enterElvAck_.toBuilder() : null;
                                this.enterElvAck_ = (EnterElvAck) codedInputStream.readMessage(EnterElvAck.parser(), extensionRegistryLite);
                                if (builder4 != null) {
                                    builder4.mergeFrom(this.enterElvAck_);
                                    this.enterElvAck_ = builder4.buildPartial();
                                }
                            case 82:
                                ElvEntered.Builder builder5 = this.elvEntered_ != null ? this.elvEntered_.toBuilder() : null;
                                this.elvEntered_ = (ElvEntered) codedInputStream.readMessage(ElvEntered.parser(), extensionRegistryLite);
                                if (builder5 != null) {
                                    builder5.mergeFrom(this.elvEntered_);
                                    this.elvEntered_ = builder5.buildPartial();
                                }
                            case 90:
                                ElvEnteredAck.Builder builder6 = this.elvEnteredAck_ != null ? this.elvEnteredAck_.toBuilder() : null;
                                this.elvEnteredAck_ = (ElvEnteredAck) codedInputStream.readMessage(ElvEnteredAck.parser(), extensionRegistryLite);
                                if (builder6 != null) {
                                    builder6.mergeFrom(this.elvEnteredAck_);
                                    this.elvEnteredAck_ = builder6.buildPartial();
                                }
                            case 98:
                                LeaveElv.Builder builder7 = this.leaveElv_ != null ? this.leaveElv_.toBuilder() : null;
                                this.leaveElv_ = (LeaveElv) codedInputStream.readMessage(LeaveElv.parser(), extensionRegistryLite);
                                if (builder7 != null) {
                                    builder7.mergeFrom(this.leaveElv_);
                                    this.leaveElv_ = builder7.buildPartial();
                                }
                            case 106:
                                LeaveElvAck.Builder builder8 = this.leaveElvAck_ != null ? this.leaveElvAck_.toBuilder() : null;
                                this.leaveElvAck_ = (LeaveElvAck) codedInputStream.readMessage(LeaveElvAck.parser(), extensionRegistryLite);
                                if (builder8 != null) {
                                    builder8.mergeFrom(this.leaveElvAck_);
                                    this.leaveElvAck_ = builder8.buildPartial();
                                }
                            case 114:
                                ElvLeft.Builder builder9 = this.elvLeft_ != null ? this.elvLeft_.toBuilder() : null;
                                this.elvLeft_ = (ElvLeft) codedInputStream.readMessage(ElvLeft.parser(), extensionRegistryLite);
                                if (builder9 != null) {
                                    builder9.mergeFrom(this.elvLeft_);
                                    this.elvLeft_ = builder9.buildPartial();
                                }
                            case 122:
                                ElvLeftAck.Builder builder10 = this.elvLeftAck_ != null ? this.elvLeftAck_.toBuilder() : null;
                                this.elvLeftAck_ = (ElvLeftAck) codedInputStream.readMessage(ElvLeftAck.parser(), extensionRegistryLite);
                                if (builder10 != null) {
                                    builder10.mergeFrom(this.elvLeftAck_);
                                    this.elvLeftAck_ = builder10.buildPartial();
                                }
                            case 130:
                                QueryElv.Builder builder11 = this.queryElv_ != null ? this.queryElv_.toBuilder() : null;
                                this.queryElv_ = (QueryElv) codedInputStream.readMessage(QueryElv.parser(), extensionRegistryLite);
                                if (builder11 != null) {
                                    builder11.mergeFrom(this.queryElv_);
                                    this.queryElv_ = builder11.buildPartial();
                                }
                            case 138:
                                QueryElvResp.Builder builder12 = this.queryElvResp_ != null ? this.queryElvResp_.toBuilder() : null;
                                this.queryElvResp_ = (QueryElvResp) codedInputStream.readMessage(QueryElvResp.parser(), extensionRegistryLite);
                                if (builder12 != null) {
                                    builder12.mergeFrom(this.queryElvResp_);
                                    this.queryElvResp_ = builder12.buildPartial();
                                }
                            case 146:
                                PrepareRide.Builder builder13 = this.prepareRide_ != null ? this.prepareRide_.toBuilder() : null;
                                this.prepareRide_ = (PrepareRide) codedInputStream.readMessage(PrepareRide.parser(), extensionRegistryLite);
                                if (builder13 != null) {
                                    builder13.mergeFrom(this.prepareRide_);
                                    this.prepareRide_ = builder13.buildPartial();
                                }
                            case 154:
                                PrepareRideAck.Builder builder14 = this.prepareRideAck_ != null ? this.prepareRideAck_.toBuilder() : null;
                                this.prepareRideAck_ = (PrepareRideAck) codedInputStream.readMessage(PrepareRideAck.parser(), extensionRegistryLite);
                                if (builder14 != null) {
                                    builder14.mergeFrom(this.prepareRideAck_);
                                    this.prepareRideAck_ = builder14.buildPartial();
                                }
                            case 162:
                                CancelElv.Builder builder15 = this.cancelElv_ != null ? this.cancelElv_.toBuilder() : null;
                                this.cancelElv_ = (CancelElv) codedInputStream.readMessage(CancelElv.parser(), extensionRegistryLite);
                                if (builder15 != null) {
                                    builder15.mergeFrom(this.cancelElv_);
                                    this.cancelElv_ = builder15.buildPartial();
                                }
                            case 170:
                                CancelElvAck.Builder builder16 = this.cancelElvAck_ != null ? this.cancelElvAck_.toBuilder() : null;
                                this.cancelElvAck_ = (CancelElvAck) codedInputStream.readMessage(CancelElvAck.parser(), extensionRegistryLite);
                                if (builder16 != null) {
                                    builder16.mergeFrom(this.cancelElvAck_);
                                    this.cancelElvAck_ = builder16.buildPartial();
                                }
                            default:
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    z = true;
                                }
                        }
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
            return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_Elv_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5808xe078e79.ensureFieldAccessorsInitialized(Elv.class, Builder.class);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public enum MsgType implements ProtocolMessageEnum {
            CallElv(0),
            CallElvAck(1),
            EnterElv(2),
            EnterElvAck(3),
            ElvEntered(4),
            ElvEnteredAck(5),
            LeaveElv(6),
            LeaveElvAck(7),
            ElvLeft(8),
            ElvLeftAck(9),
            QueryElv(10),
            QueryElvResp(11),
            PrepareRide(12),
            PrepareRideAck(13),
            CancelElv(14),
            CancelElvAck(15),
            UNRECOGNIZED(-1);

            public static final int CallElvAck_VALUE = 1;
            public static final int CallElv_VALUE = 0;
            public static final int CancelElvAck_VALUE = 15;
            public static final int CancelElv_VALUE = 14;
            public static final int ElvEnteredAck_VALUE = 5;
            public static final int ElvEntered_VALUE = 4;
            public static final int ElvLeftAck_VALUE = 9;
            public static final int ElvLeft_VALUE = 8;
            public static final int EnterElvAck_VALUE = 3;
            public static final int EnterElv_VALUE = 2;
            public static final int LeaveElvAck_VALUE = 7;
            public static final int LeaveElv_VALUE = 6;
            public static final int PrepareRideAck_VALUE = 13;
            public static final int PrepareRide_VALUE = 12;
            public static final int QueryElvResp_VALUE = 11;
            public static final int QueryElv_VALUE = 10;
            private final int value;
            private static final Internal.EnumLiteMap<MsgType> internalValueMap = new Internal.EnumLiteMap<MsgType>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.Elv.MsgType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public MsgType findValueByNumber(int i) {
                    return MsgType.forNumber(i);
                }
            };
            private static final MsgType[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static MsgType valueOf(int i) {
                return forNumber(i);
            }

            public static MsgType forNumber(int i) {
                switch (i) {
                    case 0:
                        return CallElv;
                    case 1:
                        return CallElvAck;
                    case 2:
                        return EnterElv;
                    case 3:
                        return EnterElvAck;
                    case 4:
                        return ElvEntered;
                    case 5:
                        return ElvEnteredAck;
                    case 6:
                        return LeaveElv;
                    case 7:
                        return LeaveElvAck;
                    case 8:
                        return ElvLeft;
                    case 9:
                        return ElvLeftAck;
                    case 10:
                        return QueryElv;
                    case 11:
                        return QueryElvResp;
                    case 12:
                        return PrepareRide;
                    case 13:
                        return PrepareRideAck;
                    case 14:
                        return CancelElv;
                    case 15:
                        return CancelElvAck;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<MsgType> internalGetValueMap() {
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
                return Elv.getDescriptor().getEnumTypes().get(0);
            }

            public static MsgType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            MsgType(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public String getElvId() {
            Object obj = this.elvId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.elvId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ByteString getElvIdBytes() {
            Object obj = this.elvId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.elvId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public String getRobotId() {
            Object obj = this.robotId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.robotId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ByteString getRobotIdBytes() {
            Object obj = this.robotId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.robotId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public long getSeq() {
            return this.seq_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public long getTs() {
            return this.ts_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public int getMsgTypeValue() {
            return this.msgType_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public MsgType getMsgType() {
            MsgType valueOf = MsgType.valueOf(this.msgType_);
            return valueOf == null ? MsgType.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasCallElv() {
            return this.callElv_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CallElv getCallElv() {
            CallElv callElv = this.callElv_;
            return callElv == null ? CallElv.getDefaultInstance() : callElv;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CallElvOrBuilder getCallElvOrBuilder() {
            return getCallElv();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasCallElvAck() {
            return this.callElvAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CallElvAck getCallElvAck() {
            CallElvAck callElvAck = this.callElvAck_;
            return callElvAck == null ? CallElvAck.getDefaultInstance() : callElvAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CallElvAckOrBuilder getCallElvAckOrBuilder() {
            return getCallElvAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasEnterElv() {
            return this.enterElv_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public EnterElv getEnterElv() {
            EnterElv enterElv = this.enterElv_;
            return enterElv == null ? EnterElv.getDefaultInstance() : enterElv;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public EnterElvOrBuilder getEnterElvOrBuilder() {
            return getEnterElv();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasEnterElvAck() {
            return this.enterElvAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public EnterElvAck getEnterElvAck() {
            EnterElvAck enterElvAck = this.enterElvAck_;
            return enterElvAck == null ? EnterElvAck.getDefaultInstance() : enterElvAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public EnterElvAckOrBuilder getEnterElvAckOrBuilder() {
            return getEnterElvAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasElvEntered() {
            return this.elvEntered_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvEntered getElvEntered() {
            ElvEntered elvEntered = this.elvEntered_;
            return elvEntered == null ? ElvEntered.getDefaultInstance() : elvEntered;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvEnteredOrBuilder getElvEnteredOrBuilder() {
            return getElvEntered();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasElvEnteredAck() {
            return this.elvEnteredAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvEnteredAck getElvEnteredAck() {
            ElvEnteredAck elvEnteredAck = this.elvEnteredAck_;
            return elvEnteredAck == null ? ElvEnteredAck.getDefaultInstance() : elvEnteredAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvEnteredAckOrBuilder getElvEnteredAckOrBuilder() {
            return getElvEnteredAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasLeaveElv() {
            return this.leaveElv_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public LeaveElv getLeaveElv() {
            LeaveElv leaveElv = this.leaveElv_;
            return leaveElv == null ? LeaveElv.getDefaultInstance() : leaveElv;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public LeaveElvOrBuilder getLeaveElvOrBuilder() {
            return getLeaveElv();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasLeaveElvAck() {
            return this.leaveElvAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public LeaveElvAck getLeaveElvAck() {
            LeaveElvAck leaveElvAck = this.leaveElvAck_;
            return leaveElvAck == null ? LeaveElvAck.getDefaultInstance() : leaveElvAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public LeaveElvAckOrBuilder getLeaveElvAckOrBuilder() {
            return getLeaveElvAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasElvLeft() {
            return this.elvLeft_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvLeft getElvLeft() {
            ElvLeft elvLeft = this.elvLeft_;
            return elvLeft == null ? ElvLeft.getDefaultInstance() : elvLeft;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvLeftOrBuilder getElvLeftOrBuilder() {
            return getElvLeft();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasElvLeftAck() {
            return this.elvLeftAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvLeftAck getElvLeftAck() {
            ElvLeftAck elvLeftAck = this.elvLeftAck_;
            return elvLeftAck == null ? ElvLeftAck.getDefaultInstance() : elvLeftAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public ElvLeftAckOrBuilder getElvLeftAckOrBuilder() {
            return getElvLeftAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasQueryElv() {
            return this.queryElv_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public QueryElv getQueryElv() {
            QueryElv queryElv = this.queryElv_;
            return queryElv == null ? QueryElv.getDefaultInstance() : queryElv;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public QueryElvOrBuilder getQueryElvOrBuilder() {
            return getQueryElv();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasQueryElvResp() {
            return this.queryElvResp_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public QueryElvResp getQueryElvResp() {
            QueryElvResp queryElvResp = this.queryElvResp_;
            return queryElvResp == null ? QueryElvResp.getDefaultInstance() : queryElvResp;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public QueryElvRespOrBuilder getQueryElvRespOrBuilder() {
            return getQueryElvResp();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasPrepareRide() {
            return this.prepareRide_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public PrepareRide getPrepareRide() {
            PrepareRide prepareRide = this.prepareRide_;
            return prepareRide == null ? PrepareRide.getDefaultInstance() : prepareRide;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public PrepareRideOrBuilder getPrepareRideOrBuilder() {
            return getPrepareRide();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasPrepareRideAck() {
            return this.prepareRideAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public PrepareRideAck getPrepareRideAck() {
            PrepareRideAck prepareRideAck = this.prepareRideAck_;
            return prepareRideAck == null ? PrepareRideAck.getDefaultInstance() : prepareRideAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public PrepareRideAckOrBuilder getPrepareRideAckOrBuilder() {
            return getPrepareRideAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasCancelElv() {
            return this.cancelElv_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CancelElv getCancelElv() {
            CancelElv cancelElv = this.cancelElv_;
            return cancelElv == null ? CancelElv.getDefaultInstance() : cancelElv;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CancelElvOrBuilder getCancelElvOrBuilder() {
            return getCancelElv();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public boolean hasCancelElvAck() {
            return this.cancelElvAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CancelElvAck getCancelElvAck() {
            CancelElvAck cancelElvAck = this.cancelElvAck_;
            return cancelElvAck == null ? CancelElvAck.getDefaultInstance() : cancelElvAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
        public CancelElvAckOrBuilder getCancelElvAckOrBuilder() {
            return getCancelElvAck();
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
            if (!getElvIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.elvId_);
            }
            if (!getRobotIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.robotId_);
            }
            long j = this.seq_;
            if (j != 0) {
                codedOutputStream.writeUInt64(3, j);
            }
            long j2 = this.ts_;
            if (j2 != 0) {
                codedOutputStream.writeUInt64(4, j2);
            }
            if (this.msgType_ != MsgType.CallElv.getNumber()) {
                codedOutputStream.writeEnum(5, this.msgType_);
            }
            if (this.callElv_ != null) {
                codedOutputStream.writeMessage(6, getCallElv());
            }
            if (this.callElvAck_ != null) {
                codedOutputStream.writeMessage(7, getCallElvAck());
            }
            if (this.enterElv_ != null) {
                codedOutputStream.writeMessage(8, getEnterElv());
            }
            if (this.enterElvAck_ != null) {
                codedOutputStream.writeMessage(9, getEnterElvAck());
            }
            if (this.elvEntered_ != null) {
                codedOutputStream.writeMessage(10, getElvEntered());
            }
            if (this.elvEnteredAck_ != null) {
                codedOutputStream.writeMessage(11, getElvEnteredAck());
            }
            if (this.leaveElv_ != null) {
                codedOutputStream.writeMessage(12, getLeaveElv());
            }
            if (this.leaveElvAck_ != null) {
                codedOutputStream.writeMessage(13, getLeaveElvAck());
            }
            if (this.elvLeft_ != null) {
                codedOutputStream.writeMessage(14, getElvLeft());
            }
            if (this.elvLeftAck_ != null) {
                codedOutputStream.writeMessage(15, getElvLeftAck());
            }
            if (this.queryElv_ != null) {
                codedOutputStream.writeMessage(16, getQueryElv());
            }
            if (this.queryElvResp_ != null) {
                codedOutputStream.writeMessage(17, getQueryElvResp());
            }
            if (this.prepareRide_ != null) {
                codedOutputStream.writeMessage(18, getPrepareRide());
            }
            if (this.prepareRideAck_ != null) {
                codedOutputStream.writeMessage(19, getPrepareRideAck());
            }
            if (this.cancelElv_ != null) {
                codedOutputStream.writeMessage(20, getCancelElv());
            }
            if (this.cancelElvAck_ != null) {
                codedOutputStream.writeMessage(21, getCancelElvAck());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getElvIdBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.elvId_);
            if (!getRobotIdBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(2, this.robotId_);
            }
            long j = this.seq_;
            if (j != 0) {
                computeStringSize += CodedOutputStream.computeUInt64Size(3, j);
            }
            long j2 = this.ts_;
            if (j2 != 0) {
                computeStringSize += CodedOutputStream.computeUInt64Size(4, j2);
            }
            if (this.msgType_ != MsgType.CallElv.getNumber()) {
                computeStringSize += CodedOutputStream.computeEnumSize(5, this.msgType_);
            }
            if (this.callElv_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(6, getCallElv());
            }
            if (this.callElvAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(7, getCallElvAck());
            }
            if (this.enterElv_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(8, getEnterElv());
            }
            if (this.enterElvAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(9, getEnterElvAck());
            }
            if (this.elvEntered_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(10, getElvEntered());
            }
            if (this.elvEnteredAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(11, getElvEnteredAck());
            }
            if (this.leaveElv_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(12, getLeaveElv());
            }
            if (this.leaveElvAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(13, getLeaveElvAck());
            }
            if (this.elvLeft_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(14, getElvLeft());
            }
            if (this.elvLeftAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(15, getElvLeftAck());
            }
            if (this.queryElv_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(16, getQueryElv());
            }
            if (this.queryElvResp_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(17, getQueryElvResp());
            }
            if (this.prepareRide_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(18, getPrepareRide());
            }
            if (this.prepareRideAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(19, getPrepareRideAck());
            }
            if (this.cancelElv_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(20, getCancelElv());
            }
            if (this.cancelElvAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(21, getCancelElvAck());
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
            if (!(obj instanceof Elv)) {
                return super.equals(obj);
            }
            Elv elv = (Elv) obj;
            boolean z = (((((getElvId().equals(elv.getElvId())) && getRobotId().equals(elv.getRobotId())) && (getSeq() > elv.getSeq() ? 1 : (getSeq() == elv.getSeq() ? 0 : -1)) == 0) && (getTs() > elv.getTs() ? 1 : (getTs() == elv.getTs() ? 0 : -1)) == 0) && this.msgType_ == elv.msgType_) && hasCallElv() == elv.hasCallElv();
            if (hasCallElv()) {
                z = z && getCallElv().equals(elv.getCallElv());
            }
            boolean z2 = z && hasCallElvAck() == elv.hasCallElvAck();
            if (hasCallElvAck()) {
                z2 = z2 && getCallElvAck().equals(elv.getCallElvAck());
            }
            boolean z3 = z2 && hasEnterElv() == elv.hasEnterElv();
            if (hasEnterElv()) {
                z3 = z3 && getEnterElv().equals(elv.getEnterElv());
            }
            boolean z4 = z3 && hasEnterElvAck() == elv.hasEnterElvAck();
            if (hasEnterElvAck()) {
                z4 = z4 && getEnterElvAck().equals(elv.getEnterElvAck());
            }
            boolean z5 = z4 && hasElvEntered() == elv.hasElvEntered();
            if (hasElvEntered()) {
                z5 = z5 && getElvEntered().equals(elv.getElvEntered());
            }
            boolean z6 = z5 && hasElvEnteredAck() == elv.hasElvEnteredAck();
            if (hasElvEnteredAck()) {
                z6 = z6 && getElvEnteredAck().equals(elv.getElvEnteredAck());
            }
            boolean z7 = z6 && hasLeaveElv() == elv.hasLeaveElv();
            if (hasLeaveElv()) {
                z7 = z7 && getLeaveElv().equals(elv.getLeaveElv());
            }
            boolean z8 = z7 && hasLeaveElvAck() == elv.hasLeaveElvAck();
            if (hasLeaveElvAck()) {
                z8 = z8 && getLeaveElvAck().equals(elv.getLeaveElvAck());
            }
            boolean z9 = z8 && hasElvLeft() == elv.hasElvLeft();
            if (hasElvLeft()) {
                z9 = z9 && getElvLeft().equals(elv.getElvLeft());
            }
            boolean z10 = z9 && hasElvLeftAck() == elv.hasElvLeftAck();
            if (hasElvLeftAck()) {
                z10 = z10 && getElvLeftAck().equals(elv.getElvLeftAck());
            }
            boolean z11 = z10 && hasQueryElv() == elv.hasQueryElv();
            if (hasQueryElv()) {
                z11 = z11 && getQueryElv().equals(elv.getQueryElv());
            }
            boolean z12 = z11 && hasQueryElvResp() == elv.hasQueryElvResp();
            if (hasQueryElvResp()) {
                z12 = z12 && getQueryElvResp().equals(elv.getQueryElvResp());
            }
            boolean z13 = z12 && hasPrepareRide() == elv.hasPrepareRide();
            if (hasPrepareRide()) {
                z13 = z13 && getPrepareRide().equals(elv.getPrepareRide());
            }
            boolean z14 = z13 && hasPrepareRideAck() == elv.hasPrepareRideAck();
            if (hasPrepareRideAck()) {
                z14 = z14 && getPrepareRideAck().equals(elv.getPrepareRideAck());
            }
            boolean z15 = z14 && hasCancelElv() == elv.hasCancelElv();
            if (hasCancelElv()) {
                z15 = z15 && getCancelElv().equals(elv.getCancelElv());
            }
            boolean z16 = z15 && hasCancelElvAck() == elv.hasCancelElvAck();
            if (hasCancelElvAck()) {
                z16 = z16 && getCancelElvAck().equals(elv.getCancelElvAck());
            }
            return z16 && this.unknownFields.equals(elv.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getElvId().hashCode()) * 37) + 2) * 53) + getRobotId().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getSeq())) * 37) + 4) * 53) + Internal.hashLong(getTs())) * 37) + 5) * 53) + this.msgType_;
            if (hasCallElv()) {
                hashCode = (((hashCode * 37) + 6) * 53) + getCallElv().hashCode();
            }
            if (hasCallElvAck()) {
                hashCode = (((hashCode * 37) + 7) * 53) + getCallElvAck().hashCode();
            }
            if (hasEnterElv()) {
                hashCode = (((hashCode * 37) + 8) * 53) + getEnterElv().hashCode();
            }
            if (hasEnterElvAck()) {
                hashCode = (((hashCode * 37) + 9) * 53) + getEnterElvAck().hashCode();
            }
            if (hasElvEntered()) {
                hashCode = (((hashCode * 37) + 10) * 53) + getElvEntered().hashCode();
            }
            if (hasElvEnteredAck()) {
                hashCode = (((hashCode * 37) + 11) * 53) + getElvEnteredAck().hashCode();
            }
            if (hasLeaveElv()) {
                hashCode = (((hashCode * 37) + 12) * 53) + getLeaveElv().hashCode();
            }
            if (hasLeaveElvAck()) {
                hashCode = (((hashCode * 37) + 13) * 53) + getLeaveElvAck().hashCode();
            }
            if (hasElvLeft()) {
                hashCode = (((hashCode * 37) + 14) * 53) + getElvLeft().hashCode();
            }
            if (hasElvLeftAck()) {
                hashCode = (((hashCode * 37) + 15) * 53) + getElvLeftAck().hashCode();
            }
            if (hasQueryElv()) {
                hashCode = (((hashCode * 37) + 16) * 53) + getQueryElv().hashCode();
            }
            if (hasQueryElvResp()) {
                hashCode = (((hashCode * 37) + 17) * 53) + getQueryElvResp().hashCode();
            }
            if (hasPrepareRide()) {
                hashCode = (((hashCode * 37) + 18) * 53) + getPrepareRide().hashCode();
            }
            if (hasPrepareRideAck()) {
                hashCode = (((hashCode * 37) + 19) * 53) + getPrepareRideAck().hashCode();
            }
            if (hasCancelElv()) {
                hashCode = (((hashCode * 37) + 20) * 53) + getCancelElv().hashCode();
            }
            if (hasCancelElvAck()) {
                hashCode = (((hashCode * 37) + 21) * 53) + getCancelElvAck().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static Elv parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Elv parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Elv parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Elv parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Elv parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Elv parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Elv parseFrom(InputStream inputStream) throws IOException {
            return (Elv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Elv parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Elv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Elv parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Elv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Elv parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Elv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Elv parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Elv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Elv parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Elv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Elv elv) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elv);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvOrBuilder {
            private SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> callElvAckBuilder_;
            private CallElvAck callElvAck_;
            private SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> callElvBuilder_;
            private CallElv callElv_;
            private SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> cancelElvAckBuilder_;
            private CancelElvAck cancelElvAck_;
            private SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> cancelElvBuilder_;
            private CancelElv cancelElv_;
            private SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> elvEnteredAckBuilder_;
            private ElvEnteredAck elvEnteredAck_;
            private SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> elvEnteredBuilder_;
            private ElvEntered elvEntered_;
            private Object elvId_;
            private SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> elvLeftAckBuilder_;
            private ElvLeftAck elvLeftAck_;
            private SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> elvLeftBuilder_;
            private ElvLeft elvLeft_;
            private SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> enterElvAckBuilder_;
            private EnterElvAck enterElvAck_;
            private SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> enterElvBuilder_;
            private EnterElv enterElv_;
            private SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> leaveElvAckBuilder_;
            private LeaveElvAck leaveElvAck_;
            private SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> leaveElvBuilder_;
            private LeaveElv leaveElv_;
            private int msgType_;
            private SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> prepareRideAckBuilder_;
            private PrepareRideAck prepareRideAck_;
            private SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> prepareRideBuilder_;
            private PrepareRide prepareRide_;
            private SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> queryElvBuilder_;
            private SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> queryElvRespBuilder_;
            private QueryElvResp queryElvResp_;
            private QueryElv queryElv_;
            private Object robotId_;
            private long seq_;
            private long ts_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_Elv_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5808xe078e79.ensureFieldAccessorsInitialized(Elv.class, Builder.class);
            }

            private Builder() {
                this.elvId_ = "";
                this.robotId_ = "";
                this.msgType_ = 0;
                this.callElv_ = null;
                this.callElvAck_ = null;
                this.enterElv_ = null;
                this.enterElvAck_ = null;
                this.elvEntered_ = null;
                this.elvEnteredAck_ = null;
                this.leaveElv_ = null;
                this.leaveElvAck_ = null;
                this.elvLeft_ = null;
                this.elvLeftAck_ = null;
                this.queryElv_ = null;
                this.queryElvResp_ = null;
                this.prepareRide_ = null;
                this.prepareRideAck_ = null;
                this.cancelElv_ = null;
                this.cancelElvAck_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.elvId_ = "";
                this.robotId_ = "";
                this.msgType_ = 0;
                this.callElv_ = null;
                this.callElvAck_ = null;
                this.enterElv_ = null;
                this.enterElvAck_ = null;
                this.elvEntered_ = null;
                this.elvEnteredAck_ = null;
                this.leaveElv_ = null;
                this.leaveElvAck_ = null;
                this.elvLeft_ = null;
                this.elvLeftAck_ = null;
                this.queryElv_ = null;
                this.queryElvResp_ = null;
                this.prepareRide_ = null;
                this.prepareRideAck_ = null;
                this.cancelElv_ = null;
                this.cancelElvAck_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Elv.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.elvId_ = "";
                this.robotId_ = "";
                this.seq_ = 0L;
                this.ts_ = 0L;
                this.msgType_ = 0;
                if (this.callElvBuilder_ == null) {
                    this.callElv_ = null;
                } else {
                    this.callElv_ = null;
                    this.callElvBuilder_ = null;
                }
                if (this.callElvAckBuilder_ == null) {
                    this.callElvAck_ = null;
                } else {
                    this.callElvAck_ = null;
                    this.callElvAckBuilder_ = null;
                }
                if (this.enterElvBuilder_ == null) {
                    this.enterElv_ = null;
                } else {
                    this.enterElv_ = null;
                    this.enterElvBuilder_ = null;
                }
                if (this.enterElvAckBuilder_ == null) {
                    this.enterElvAck_ = null;
                } else {
                    this.enterElvAck_ = null;
                    this.enterElvAckBuilder_ = null;
                }
                if (this.elvEnteredBuilder_ == null) {
                    this.elvEntered_ = null;
                } else {
                    this.elvEntered_ = null;
                    this.elvEnteredBuilder_ = null;
                }
                if (this.elvEnteredAckBuilder_ == null) {
                    this.elvEnteredAck_ = null;
                } else {
                    this.elvEnteredAck_ = null;
                    this.elvEnteredAckBuilder_ = null;
                }
                if (this.leaveElvBuilder_ == null) {
                    this.leaveElv_ = null;
                } else {
                    this.leaveElv_ = null;
                    this.leaveElvBuilder_ = null;
                }
                if (this.leaveElvAckBuilder_ == null) {
                    this.leaveElvAck_ = null;
                } else {
                    this.leaveElvAck_ = null;
                    this.leaveElvAckBuilder_ = null;
                }
                if (this.elvLeftBuilder_ == null) {
                    this.elvLeft_ = null;
                } else {
                    this.elvLeft_ = null;
                    this.elvLeftBuilder_ = null;
                }
                if (this.elvLeftAckBuilder_ == null) {
                    this.elvLeftAck_ = null;
                } else {
                    this.elvLeftAck_ = null;
                    this.elvLeftAckBuilder_ = null;
                }
                if (this.queryElvBuilder_ == null) {
                    this.queryElv_ = null;
                } else {
                    this.queryElv_ = null;
                    this.queryElvBuilder_ = null;
                }
                if (this.queryElvRespBuilder_ == null) {
                    this.queryElvResp_ = null;
                } else {
                    this.queryElvResp_ = null;
                    this.queryElvRespBuilder_ = null;
                }
                if (this.prepareRideBuilder_ == null) {
                    this.prepareRide_ = null;
                } else {
                    this.prepareRide_ = null;
                    this.prepareRideBuilder_ = null;
                }
                if (this.prepareRideAckBuilder_ == null) {
                    this.prepareRideAck_ = null;
                } else {
                    this.prepareRideAck_ = null;
                    this.prepareRideAckBuilder_ = null;
                }
                if (this.cancelElvBuilder_ == null) {
                    this.cancelElv_ = null;
                } else {
                    this.cancelElv_ = null;
                    this.cancelElvBuilder_ = null;
                }
                if (this.cancelElvAckBuilder_ == null) {
                    this.cancelElvAck_ = null;
                } else {
                    this.cancelElvAck_ = null;
                    this.cancelElvAckBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_Elv_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Elv getDefaultInstanceForType() {
                return Elv.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Elv build() {
                Elv buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Elv buildPartial() {
                Elv elv = new Elv(this);
                elv.elvId_ = this.elvId_;
                elv.robotId_ = this.robotId_;
                elv.seq_ = this.seq_;
                elv.ts_ = this.ts_;
                elv.msgType_ = this.msgType_;
                SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> singleFieldBuilderV3 = this.callElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    elv.callElv_ = this.callElv_;
                } else {
                    elv.callElv_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> singleFieldBuilderV32 = this.callElvAckBuilder_;
                if (singleFieldBuilderV32 == null) {
                    elv.callElvAck_ = this.callElvAck_;
                } else {
                    elv.callElvAck_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> singleFieldBuilderV33 = this.enterElvBuilder_;
                if (singleFieldBuilderV33 == null) {
                    elv.enterElv_ = this.enterElv_;
                } else {
                    elv.enterElv_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> singleFieldBuilderV34 = this.enterElvAckBuilder_;
                if (singleFieldBuilderV34 == null) {
                    elv.enterElvAck_ = this.enterElvAck_;
                } else {
                    elv.enterElvAck_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> singleFieldBuilderV35 = this.elvEnteredBuilder_;
                if (singleFieldBuilderV35 == null) {
                    elv.elvEntered_ = this.elvEntered_;
                } else {
                    elv.elvEntered_ = singleFieldBuilderV35.build();
                }
                SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> singleFieldBuilderV36 = this.elvEnteredAckBuilder_;
                if (singleFieldBuilderV36 == null) {
                    elv.elvEnteredAck_ = this.elvEnteredAck_;
                } else {
                    elv.elvEnteredAck_ = singleFieldBuilderV36.build();
                }
                SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> singleFieldBuilderV37 = this.leaveElvBuilder_;
                if (singleFieldBuilderV37 == null) {
                    elv.leaveElv_ = this.leaveElv_;
                } else {
                    elv.leaveElv_ = singleFieldBuilderV37.build();
                }
                SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> singleFieldBuilderV38 = this.leaveElvAckBuilder_;
                if (singleFieldBuilderV38 == null) {
                    elv.leaveElvAck_ = this.leaveElvAck_;
                } else {
                    elv.leaveElvAck_ = singleFieldBuilderV38.build();
                }
                SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> singleFieldBuilderV39 = this.elvLeftBuilder_;
                if (singleFieldBuilderV39 == null) {
                    elv.elvLeft_ = this.elvLeft_;
                } else {
                    elv.elvLeft_ = singleFieldBuilderV39.build();
                }
                SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> singleFieldBuilderV310 = this.elvLeftAckBuilder_;
                if (singleFieldBuilderV310 == null) {
                    elv.elvLeftAck_ = this.elvLeftAck_;
                } else {
                    elv.elvLeftAck_ = singleFieldBuilderV310.build();
                }
                SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> singleFieldBuilderV311 = this.queryElvBuilder_;
                if (singleFieldBuilderV311 == null) {
                    elv.queryElv_ = this.queryElv_;
                } else {
                    elv.queryElv_ = singleFieldBuilderV311.build();
                }
                SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> singleFieldBuilderV312 = this.queryElvRespBuilder_;
                if (singleFieldBuilderV312 == null) {
                    elv.queryElvResp_ = this.queryElvResp_;
                } else {
                    elv.queryElvResp_ = singleFieldBuilderV312.build();
                }
                SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> singleFieldBuilderV313 = this.prepareRideBuilder_;
                if (singleFieldBuilderV313 == null) {
                    elv.prepareRide_ = this.prepareRide_;
                } else {
                    elv.prepareRide_ = singleFieldBuilderV313.build();
                }
                SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> singleFieldBuilderV314 = this.prepareRideAckBuilder_;
                if (singleFieldBuilderV314 == null) {
                    elv.prepareRideAck_ = this.prepareRideAck_;
                } else {
                    elv.prepareRideAck_ = singleFieldBuilderV314.build();
                }
                SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> singleFieldBuilderV315 = this.cancelElvBuilder_;
                if (singleFieldBuilderV315 == null) {
                    elv.cancelElv_ = this.cancelElv_;
                } else {
                    elv.cancelElv_ = singleFieldBuilderV315.build();
                }
                SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> singleFieldBuilderV316 = this.cancelElvAckBuilder_;
                if (singleFieldBuilderV316 == null) {
                    elv.cancelElvAck_ = this.cancelElvAck_;
                } else {
                    elv.cancelElvAck_ = singleFieldBuilderV316.build();
                }
                onBuilt();
                return elv;
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
                if (message instanceof Elv) {
                    return mergeFrom((Elv) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Elv elv) {
                if (elv == Elv.getDefaultInstance()) {
                    return this;
                }
                if (!elv.getElvId().isEmpty()) {
                    this.elvId_ = elv.elvId_;
                    onChanged();
                }
                if (!elv.getRobotId().isEmpty()) {
                    this.robotId_ = elv.robotId_;
                    onChanged();
                }
                if (elv.getSeq() != 0) {
                    setSeq(elv.getSeq());
                }
                if (elv.getTs() != 0) {
                    setTs(elv.getTs());
                }
                if (elv.msgType_ != 0) {
                    setMsgTypeValue(elv.getMsgTypeValue());
                }
                if (elv.hasCallElv()) {
                    mergeCallElv(elv.getCallElv());
                }
                if (elv.hasCallElvAck()) {
                    mergeCallElvAck(elv.getCallElvAck());
                }
                if (elv.hasEnterElv()) {
                    mergeEnterElv(elv.getEnterElv());
                }
                if (elv.hasEnterElvAck()) {
                    mergeEnterElvAck(elv.getEnterElvAck());
                }
                if (elv.hasElvEntered()) {
                    mergeElvEntered(elv.getElvEntered());
                }
                if (elv.hasElvEnteredAck()) {
                    mergeElvEnteredAck(elv.getElvEnteredAck());
                }
                if (elv.hasLeaveElv()) {
                    mergeLeaveElv(elv.getLeaveElv());
                }
                if (elv.hasLeaveElvAck()) {
                    mergeLeaveElvAck(elv.getLeaveElvAck());
                }
                if (elv.hasElvLeft()) {
                    mergeElvLeft(elv.getElvLeft());
                }
                if (elv.hasElvLeftAck()) {
                    mergeElvLeftAck(elv.getElvLeftAck());
                }
                if (elv.hasQueryElv()) {
                    mergeQueryElv(elv.getQueryElv());
                }
                if (elv.hasQueryElvResp()) {
                    mergeQueryElvResp(elv.getQueryElvResp());
                }
                if (elv.hasPrepareRide()) {
                    mergePrepareRide(elv.getPrepareRide());
                }
                if (elv.hasPrepareRideAck()) {
                    mergePrepareRideAck(elv.getPrepareRideAck());
                }
                if (elv.hasCancelElv()) {
                    mergeCancelElv(elv.getCancelElv());
                }
                if (elv.hasCancelElvAck()) {
                    mergeCancelElvAck(elv.getCancelElvAck());
                }
                mergeUnknownFields(elv.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Elv elv = null;
                try {
                    try {
                        Elv elv2 = (Elv) Elv.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elv2 != null) {
                            mergeFrom(elv2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Elv elv3 = (Elv) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elv = elv3;
                            if (elv != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elv != null) {
                        mergeFrom(elv);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public String getElvId() {
                Object obj = this.elvId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.elvId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ByteString getElvIdBytes() {
                Object obj = this.elvId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.elvId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setElvId(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.elvId_ = str;
                onChanged();
                return this;
            }

            public Builder clearElvId() {
                this.elvId_ = Elv.getDefaultInstance().getElvId();
                onChanged();
                return this;
            }

            public Builder setElvIdBytes(ByteString byteString) {
                if (byteString != null) {
                    Elv.checkByteStringIsUtf8(byteString);
                    this.elvId_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public String getRobotId() {
                Object obj = this.robotId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.robotId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
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
                    throw new NullPointerException();
                }
                this.robotId_ = str;
                onChanged();
                return this;
            }

            public Builder clearRobotId() {
                this.robotId_ = Elv.getDefaultInstance().getRobotId();
                onChanged();
                return this;
            }

            public Builder setRobotIdBytes(ByteString byteString) {
                if (byteString != null) {
                    Elv.checkByteStringIsUtf8(byteString);
                    this.robotId_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public long getSeq() {
                return this.seq_;
            }

            public Builder setSeq(long j) {
                this.seq_ = j;
                onChanged();
                return this;
            }

            public Builder clearSeq() {
                this.seq_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public long getTs() {
                return this.ts_;
            }

            public Builder setTs(long j) {
                this.ts_ = j;
                onChanged();
                return this;
            }

            public Builder clearTs() {
                this.ts_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public int getMsgTypeValue() {
                return this.msgType_;
            }

            public Builder setMsgTypeValue(int i) {
                this.msgType_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public MsgType getMsgType() {
                MsgType valueOf = MsgType.valueOf(this.msgType_);
                return valueOf == null ? MsgType.UNRECOGNIZED : valueOf;
            }

            public Builder setMsgType(MsgType msgType) {
                if (msgType == null) {
                    throw new NullPointerException();
                }
                this.msgType_ = msgType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearMsgType() {
                this.msgType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasCallElv() {
                return (this.callElvBuilder_ == null && this.callElv_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CallElv getCallElv() {
                SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> singleFieldBuilderV3 = this.callElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallElv callElv = this.callElv_;
                    return callElv == null ? CallElv.getDefaultInstance() : callElv;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCallElv(CallElv callElv) {
                SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> singleFieldBuilderV3 = this.callElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(callElv);
                } else {
                    if (callElv == null) {
                        throw new NullPointerException();
                    }
                    this.callElv_ = callElv;
                    onChanged();
                }
                return this;
            }

            public Builder setCallElv(CallElv.Builder builder) {
                SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> singleFieldBuilderV3 = this.callElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.callElv_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCallElv(CallElv callElv) {
                SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> singleFieldBuilderV3 = this.callElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallElv callElv2 = this.callElv_;
                    if (callElv2 != null) {
                        this.callElv_ = CallElv.newBuilder(callElv2).mergeFrom(callElv).buildPartial();
                    } else {
                        this.callElv_ = callElv;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(callElv);
                }
                return this;
            }

            public Builder clearCallElv() {
                if (this.callElvBuilder_ == null) {
                    this.callElv_ = null;
                    onChanged();
                } else {
                    this.callElv_ = null;
                    this.callElvBuilder_ = null;
                }
                return this;
            }

            public CallElv.Builder getCallElvBuilder() {
                onChanged();
                return getCallElvFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CallElvOrBuilder getCallElvOrBuilder() {
                SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> singleFieldBuilderV3 = this.callElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CallElv callElv = this.callElv_;
                return callElv == null ? CallElv.getDefaultInstance() : callElv;
            }

            private SingleFieldBuilderV3<CallElv, CallElv.Builder, CallElvOrBuilder> getCallElvFieldBuilder() {
                if (this.callElvBuilder_ == null) {
                    this.callElvBuilder_ = new SingleFieldBuilderV3<>(getCallElv(), getParentForChildren(), isClean());
                    this.callElv_ = null;
                }
                return this.callElvBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasCallElvAck() {
                return (this.callElvAckBuilder_ == null && this.callElvAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CallElvAck getCallElvAck() {
                SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> singleFieldBuilderV3 = this.callElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallElvAck callElvAck = this.callElvAck_;
                    return callElvAck == null ? CallElvAck.getDefaultInstance() : callElvAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCallElvAck(CallElvAck callElvAck) {
                SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> singleFieldBuilderV3 = this.callElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(callElvAck);
                } else {
                    if (callElvAck == null) {
                        throw new NullPointerException();
                    }
                    this.callElvAck_ = callElvAck;
                    onChanged();
                }
                return this;
            }

            public Builder setCallElvAck(CallElvAck.Builder builder) {
                SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> singleFieldBuilderV3 = this.callElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.callElvAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCallElvAck(CallElvAck callElvAck) {
                SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> singleFieldBuilderV3 = this.callElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallElvAck callElvAck2 = this.callElvAck_;
                    if (callElvAck2 != null) {
                        this.callElvAck_ = CallElvAck.newBuilder(callElvAck2).mergeFrom(callElvAck).buildPartial();
                    } else {
                        this.callElvAck_ = callElvAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(callElvAck);
                }
                return this;
            }

            public Builder clearCallElvAck() {
                if (this.callElvAckBuilder_ == null) {
                    this.callElvAck_ = null;
                    onChanged();
                } else {
                    this.callElvAck_ = null;
                    this.callElvAckBuilder_ = null;
                }
                return this;
            }

            public CallElvAck.Builder getCallElvAckBuilder() {
                onChanged();
                return getCallElvAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CallElvAckOrBuilder getCallElvAckOrBuilder() {
                SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> singleFieldBuilderV3 = this.callElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CallElvAck callElvAck = this.callElvAck_;
                return callElvAck == null ? CallElvAck.getDefaultInstance() : callElvAck;
            }

            private SingleFieldBuilderV3<CallElvAck, CallElvAck.Builder, CallElvAckOrBuilder> getCallElvAckFieldBuilder() {
                if (this.callElvAckBuilder_ == null) {
                    this.callElvAckBuilder_ = new SingleFieldBuilderV3<>(getCallElvAck(), getParentForChildren(), isClean());
                    this.callElvAck_ = null;
                }
                return this.callElvAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasEnterElv() {
                return (this.enterElvBuilder_ == null && this.enterElv_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public EnterElv getEnterElv() {
                SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> singleFieldBuilderV3 = this.enterElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    EnterElv enterElv = this.enterElv_;
                    return enterElv == null ? EnterElv.getDefaultInstance() : enterElv;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setEnterElv(EnterElv enterElv) {
                SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> singleFieldBuilderV3 = this.enterElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(enterElv);
                } else {
                    if (enterElv == null) {
                        throw new NullPointerException();
                    }
                    this.enterElv_ = enterElv;
                    onChanged();
                }
                return this;
            }

            public Builder setEnterElv(EnterElv.Builder builder) {
                SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> singleFieldBuilderV3 = this.enterElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.enterElv_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeEnterElv(EnterElv enterElv) {
                SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> singleFieldBuilderV3 = this.enterElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    EnterElv enterElv2 = this.enterElv_;
                    if (enterElv2 != null) {
                        this.enterElv_ = EnterElv.newBuilder(enterElv2).mergeFrom(enterElv).buildPartial();
                    } else {
                        this.enterElv_ = enterElv;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(enterElv);
                }
                return this;
            }

            public Builder clearEnterElv() {
                if (this.enterElvBuilder_ == null) {
                    this.enterElv_ = null;
                    onChanged();
                } else {
                    this.enterElv_ = null;
                    this.enterElvBuilder_ = null;
                }
                return this;
            }

            public EnterElv.Builder getEnterElvBuilder() {
                onChanged();
                return getEnterElvFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public EnterElvOrBuilder getEnterElvOrBuilder() {
                SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> singleFieldBuilderV3 = this.enterElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                EnterElv enterElv = this.enterElv_;
                return enterElv == null ? EnterElv.getDefaultInstance() : enterElv;
            }

            private SingleFieldBuilderV3<EnterElv, EnterElv.Builder, EnterElvOrBuilder> getEnterElvFieldBuilder() {
                if (this.enterElvBuilder_ == null) {
                    this.enterElvBuilder_ = new SingleFieldBuilderV3<>(getEnterElv(), getParentForChildren(), isClean());
                    this.enterElv_ = null;
                }
                return this.enterElvBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasEnterElvAck() {
                return (this.enterElvAckBuilder_ == null && this.enterElvAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public EnterElvAck getEnterElvAck() {
                SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> singleFieldBuilderV3 = this.enterElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    EnterElvAck enterElvAck = this.enterElvAck_;
                    return enterElvAck == null ? EnterElvAck.getDefaultInstance() : enterElvAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setEnterElvAck(EnterElvAck enterElvAck) {
                SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> singleFieldBuilderV3 = this.enterElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(enterElvAck);
                } else {
                    if (enterElvAck == null) {
                        throw new NullPointerException();
                    }
                    this.enterElvAck_ = enterElvAck;
                    onChanged();
                }
                return this;
            }

            public Builder setEnterElvAck(EnterElvAck.Builder builder) {
                SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> singleFieldBuilderV3 = this.enterElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.enterElvAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeEnterElvAck(EnterElvAck enterElvAck) {
                SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> singleFieldBuilderV3 = this.enterElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    EnterElvAck enterElvAck2 = this.enterElvAck_;
                    if (enterElvAck2 != null) {
                        this.enterElvAck_ = EnterElvAck.newBuilder(enterElvAck2).mergeFrom(enterElvAck).buildPartial();
                    } else {
                        this.enterElvAck_ = enterElvAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(enterElvAck);
                }
                return this;
            }

            public Builder clearEnterElvAck() {
                if (this.enterElvAckBuilder_ == null) {
                    this.enterElvAck_ = null;
                    onChanged();
                } else {
                    this.enterElvAck_ = null;
                    this.enterElvAckBuilder_ = null;
                }
                return this;
            }

            public EnterElvAck.Builder getEnterElvAckBuilder() {
                onChanged();
                return getEnterElvAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public EnterElvAckOrBuilder getEnterElvAckOrBuilder() {
                SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> singleFieldBuilderV3 = this.enterElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                EnterElvAck enterElvAck = this.enterElvAck_;
                return enterElvAck == null ? EnterElvAck.getDefaultInstance() : enterElvAck;
            }

            private SingleFieldBuilderV3<EnterElvAck, EnterElvAck.Builder, EnterElvAckOrBuilder> getEnterElvAckFieldBuilder() {
                if (this.enterElvAckBuilder_ == null) {
                    this.enterElvAckBuilder_ = new SingleFieldBuilderV3<>(getEnterElvAck(), getParentForChildren(), isClean());
                    this.enterElvAck_ = null;
                }
                return this.enterElvAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasElvEntered() {
                return (this.elvEnteredBuilder_ == null && this.elvEntered_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvEntered getElvEntered() {
                SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> singleFieldBuilderV3 = this.elvEnteredBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvEntered elvEntered = this.elvEntered_;
                    return elvEntered == null ? ElvEntered.getDefaultInstance() : elvEntered;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setElvEntered(ElvEntered elvEntered) {
                SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> singleFieldBuilderV3 = this.elvEnteredBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvEntered);
                } else {
                    if (elvEntered == null) {
                        throw new NullPointerException();
                    }
                    this.elvEntered_ = elvEntered;
                    onChanged();
                }
                return this;
            }

            public Builder setElvEntered(ElvEntered.Builder builder) {
                SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> singleFieldBuilderV3 = this.elvEnteredBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.elvEntered_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeElvEntered(ElvEntered elvEntered) {
                SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> singleFieldBuilderV3 = this.elvEnteredBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvEntered elvEntered2 = this.elvEntered_;
                    if (elvEntered2 != null) {
                        this.elvEntered_ = ElvEntered.newBuilder(elvEntered2).mergeFrom(elvEntered).buildPartial();
                    } else {
                        this.elvEntered_ = elvEntered;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvEntered);
                }
                return this;
            }

            public Builder clearElvEntered() {
                if (this.elvEnteredBuilder_ == null) {
                    this.elvEntered_ = null;
                    onChanged();
                } else {
                    this.elvEntered_ = null;
                    this.elvEnteredBuilder_ = null;
                }
                return this;
            }

            public ElvEntered.Builder getElvEnteredBuilder() {
                onChanged();
                return getElvEnteredFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvEnteredOrBuilder getElvEnteredOrBuilder() {
                SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> singleFieldBuilderV3 = this.elvEnteredBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvEntered elvEntered = this.elvEntered_;
                return elvEntered == null ? ElvEntered.getDefaultInstance() : elvEntered;
            }

            private SingleFieldBuilderV3<ElvEntered, ElvEntered.Builder, ElvEnteredOrBuilder> getElvEnteredFieldBuilder() {
                if (this.elvEnteredBuilder_ == null) {
                    this.elvEnteredBuilder_ = new SingleFieldBuilderV3<>(getElvEntered(), getParentForChildren(), isClean());
                    this.elvEntered_ = null;
                }
                return this.elvEnteredBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasElvEnteredAck() {
                return (this.elvEnteredAckBuilder_ == null && this.elvEnteredAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvEnteredAck getElvEnteredAck() {
                SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> singleFieldBuilderV3 = this.elvEnteredAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvEnteredAck elvEnteredAck = this.elvEnteredAck_;
                    return elvEnteredAck == null ? ElvEnteredAck.getDefaultInstance() : elvEnteredAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setElvEnteredAck(ElvEnteredAck elvEnteredAck) {
                SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> singleFieldBuilderV3 = this.elvEnteredAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvEnteredAck);
                } else {
                    if (elvEnteredAck == null) {
                        throw new NullPointerException();
                    }
                    this.elvEnteredAck_ = elvEnteredAck;
                    onChanged();
                }
                return this;
            }

            public Builder setElvEnteredAck(ElvEnteredAck.Builder builder) {
                SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> singleFieldBuilderV3 = this.elvEnteredAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.elvEnteredAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeElvEnteredAck(ElvEnteredAck elvEnteredAck) {
                SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> singleFieldBuilderV3 = this.elvEnteredAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvEnteredAck elvEnteredAck2 = this.elvEnteredAck_;
                    if (elvEnteredAck2 != null) {
                        this.elvEnteredAck_ = ElvEnteredAck.newBuilder(elvEnteredAck2).mergeFrom(elvEnteredAck).buildPartial();
                    } else {
                        this.elvEnteredAck_ = elvEnteredAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvEnteredAck);
                }
                return this;
            }

            public Builder clearElvEnteredAck() {
                if (this.elvEnteredAckBuilder_ == null) {
                    this.elvEnteredAck_ = null;
                    onChanged();
                } else {
                    this.elvEnteredAck_ = null;
                    this.elvEnteredAckBuilder_ = null;
                }
                return this;
            }

            public ElvEnteredAck.Builder getElvEnteredAckBuilder() {
                onChanged();
                return getElvEnteredAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvEnteredAckOrBuilder getElvEnteredAckOrBuilder() {
                SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> singleFieldBuilderV3 = this.elvEnteredAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvEnteredAck elvEnteredAck = this.elvEnteredAck_;
                return elvEnteredAck == null ? ElvEnteredAck.getDefaultInstance() : elvEnteredAck;
            }

            private SingleFieldBuilderV3<ElvEnteredAck, ElvEnteredAck.Builder, ElvEnteredAckOrBuilder> getElvEnteredAckFieldBuilder() {
                if (this.elvEnteredAckBuilder_ == null) {
                    this.elvEnteredAckBuilder_ = new SingleFieldBuilderV3<>(getElvEnteredAck(), getParentForChildren(), isClean());
                    this.elvEnteredAck_ = null;
                }
                return this.elvEnteredAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasLeaveElv() {
                return (this.leaveElvBuilder_ == null && this.leaveElv_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public LeaveElv getLeaveElv() {
                SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> singleFieldBuilderV3 = this.leaveElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    LeaveElv leaveElv = this.leaveElv_;
                    return leaveElv == null ? LeaveElv.getDefaultInstance() : leaveElv;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setLeaveElv(LeaveElv leaveElv) {
                SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> singleFieldBuilderV3 = this.leaveElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(leaveElv);
                } else {
                    if (leaveElv == null) {
                        throw new NullPointerException();
                    }
                    this.leaveElv_ = leaveElv;
                    onChanged();
                }
                return this;
            }

            public Builder setLeaveElv(LeaveElv.Builder builder) {
                SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> singleFieldBuilderV3 = this.leaveElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.leaveElv_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeLeaveElv(LeaveElv leaveElv) {
                SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> singleFieldBuilderV3 = this.leaveElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    LeaveElv leaveElv2 = this.leaveElv_;
                    if (leaveElv2 != null) {
                        this.leaveElv_ = LeaveElv.newBuilder(leaveElv2).mergeFrom(leaveElv).buildPartial();
                    } else {
                        this.leaveElv_ = leaveElv;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(leaveElv);
                }
                return this;
            }

            public Builder clearLeaveElv() {
                if (this.leaveElvBuilder_ == null) {
                    this.leaveElv_ = null;
                    onChanged();
                } else {
                    this.leaveElv_ = null;
                    this.leaveElvBuilder_ = null;
                }
                return this;
            }

            public LeaveElv.Builder getLeaveElvBuilder() {
                onChanged();
                return getLeaveElvFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public LeaveElvOrBuilder getLeaveElvOrBuilder() {
                SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> singleFieldBuilderV3 = this.leaveElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                LeaveElv leaveElv = this.leaveElv_;
                return leaveElv == null ? LeaveElv.getDefaultInstance() : leaveElv;
            }

            private SingleFieldBuilderV3<LeaveElv, LeaveElv.Builder, LeaveElvOrBuilder> getLeaveElvFieldBuilder() {
                if (this.leaveElvBuilder_ == null) {
                    this.leaveElvBuilder_ = new SingleFieldBuilderV3<>(getLeaveElv(), getParentForChildren(), isClean());
                    this.leaveElv_ = null;
                }
                return this.leaveElvBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasLeaveElvAck() {
                return (this.leaveElvAckBuilder_ == null && this.leaveElvAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public LeaveElvAck getLeaveElvAck() {
                SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> singleFieldBuilderV3 = this.leaveElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    LeaveElvAck leaveElvAck = this.leaveElvAck_;
                    return leaveElvAck == null ? LeaveElvAck.getDefaultInstance() : leaveElvAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setLeaveElvAck(LeaveElvAck leaveElvAck) {
                SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> singleFieldBuilderV3 = this.leaveElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(leaveElvAck);
                } else {
                    if (leaveElvAck == null) {
                        throw new NullPointerException();
                    }
                    this.leaveElvAck_ = leaveElvAck;
                    onChanged();
                }
                return this;
            }

            public Builder setLeaveElvAck(LeaveElvAck.Builder builder) {
                SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> singleFieldBuilderV3 = this.leaveElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.leaveElvAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeLeaveElvAck(LeaveElvAck leaveElvAck) {
                SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> singleFieldBuilderV3 = this.leaveElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    LeaveElvAck leaveElvAck2 = this.leaveElvAck_;
                    if (leaveElvAck2 != null) {
                        this.leaveElvAck_ = LeaveElvAck.newBuilder(leaveElvAck2).mergeFrom(leaveElvAck).buildPartial();
                    } else {
                        this.leaveElvAck_ = leaveElvAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(leaveElvAck);
                }
                return this;
            }

            public Builder clearLeaveElvAck() {
                if (this.leaveElvAckBuilder_ == null) {
                    this.leaveElvAck_ = null;
                    onChanged();
                } else {
                    this.leaveElvAck_ = null;
                    this.leaveElvAckBuilder_ = null;
                }
                return this;
            }

            public LeaveElvAck.Builder getLeaveElvAckBuilder() {
                onChanged();
                return getLeaveElvAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public LeaveElvAckOrBuilder getLeaveElvAckOrBuilder() {
                SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> singleFieldBuilderV3 = this.leaveElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                LeaveElvAck leaveElvAck = this.leaveElvAck_;
                return leaveElvAck == null ? LeaveElvAck.getDefaultInstance() : leaveElvAck;
            }

            private SingleFieldBuilderV3<LeaveElvAck, LeaveElvAck.Builder, LeaveElvAckOrBuilder> getLeaveElvAckFieldBuilder() {
                if (this.leaveElvAckBuilder_ == null) {
                    this.leaveElvAckBuilder_ = new SingleFieldBuilderV3<>(getLeaveElvAck(), getParentForChildren(), isClean());
                    this.leaveElvAck_ = null;
                }
                return this.leaveElvAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasElvLeft() {
                return (this.elvLeftBuilder_ == null && this.elvLeft_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvLeft getElvLeft() {
                SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> singleFieldBuilderV3 = this.elvLeftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvLeft elvLeft = this.elvLeft_;
                    return elvLeft == null ? ElvLeft.getDefaultInstance() : elvLeft;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setElvLeft(ElvLeft elvLeft) {
                SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> singleFieldBuilderV3 = this.elvLeftBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvLeft);
                } else {
                    if (elvLeft == null) {
                        throw new NullPointerException();
                    }
                    this.elvLeft_ = elvLeft;
                    onChanged();
                }
                return this;
            }

            public Builder setElvLeft(ElvLeft.Builder builder) {
                SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> singleFieldBuilderV3 = this.elvLeftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.elvLeft_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeElvLeft(ElvLeft elvLeft) {
                SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> singleFieldBuilderV3 = this.elvLeftBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvLeft elvLeft2 = this.elvLeft_;
                    if (elvLeft2 != null) {
                        this.elvLeft_ = ElvLeft.newBuilder(elvLeft2).mergeFrom(elvLeft).buildPartial();
                    } else {
                        this.elvLeft_ = elvLeft;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvLeft);
                }
                return this;
            }

            public Builder clearElvLeft() {
                if (this.elvLeftBuilder_ == null) {
                    this.elvLeft_ = null;
                    onChanged();
                } else {
                    this.elvLeft_ = null;
                    this.elvLeftBuilder_ = null;
                }
                return this;
            }

            public ElvLeft.Builder getElvLeftBuilder() {
                onChanged();
                return getElvLeftFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvLeftOrBuilder getElvLeftOrBuilder() {
                SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> singleFieldBuilderV3 = this.elvLeftBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvLeft elvLeft = this.elvLeft_;
                return elvLeft == null ? ElvLeft.getDefaultInstance() : elvLeft;
            }

            private SingleFieldBuilderV3<ElvLeft, ElvLeft.Builder, ElvLeftOrBuilder> getElvLeftFieldBuilder() {
                if (this.elvLeftBuilder_ == null) {
                    this.elvLeftBuilder_ = new SingleFieldBuilderV3<>(getElvLeft(), getParentForChildren(), isClean());
                    this.elvLeft_ = null;
                }
                return this.elvLeftBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasElvLeftAck() {
                return (this.elvLeftAckBuilder_ == null && this.elvLeftAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvLeftAck getElvLeftAck() {
                SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> singleFieldBuilderV3 = this.elvLeftAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvLeftAck elvLeftAck = this.elvLeftAck_;
                    return elvLeftAck == null ? ElvLeftAck.getDefaultInstance() : elvLeftAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setElvLeftAck(ElvLeftAck elvLeftAck) {
                SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> singleFieldBuilderV3 = this.elvLeftAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvLeftAck);
                } else {
                    if (elvLeftAck == null) {
                        throw new NullPointerException();
                    }
                    this.elvLeftAck_ = elvLeftAck;
                    onChanged();
                }
                return this;
            }

            public Builder setElvLeftAck(ElvLeftAck.Builder builder) {
                SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> singleFieldBuilderV3 = this.elvLeftAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.elvLeftAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeElvLeftAck(ElvLeftAck elvLeftAck) {
                SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> singleFieldBuilderV3 = this.elvLeftAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvLeftAck elvLeftAck2 = this.elvLeftAck_;
                    if (elvLeftAck2 != null) {
                        this.elvLeftAck_ = ElvLeftAck.newBuilder(elvLeftAck2).mergeFrom(elvLeftAck).buildPartial();
                    } else {
                        this.elvLeftAck_ = elvLeftAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvLeftAck);
                }
                return this;
            }

            public Builder clearElvLeftAck() {
                if (this.elvLeftAckBuilder_ == null) {
                    this.elvLeftAck_ = null;
                    onChanged();
                } else {
                    this.elvLeftAck_ = null;
                    this.elvLeftAckBuilder_ = null;
                }
                return this;
            }

            public ElvLeftAck.Builder getElvLeftAckBuilder() {
                onChanged();
                return getElvLeftAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public ElvLeftAckOrBuilder getElvLeftAckOrBuilder() {
                SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> singleFieldBuilderV3 = this.elvLeftAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvLeftAck elvLeftAck = this.elvLeftAck_;
                return elvLeftAck == null ? ElvLeftAck.getDefaultInstance() : elvLeftAck;
            }

            private SingleFieldBuilderV3<ElvLeftAck, ElvLeftAck.Builder, ElvLeftAckOrBuilder> getElvLeftAckFieldBuilder() {
                if (this.elvLeftAckBuilder_ == null) {
                    this.elvLeftAckBuilder_ = new SingleFieldBuilderV3<>(getElvLeftAck(), getParentForChildren(), isClean());
                    this.elvLeftAck_ = null;
                }
                return this.elvLeftAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasQueryElv() {
                return (this.queryElvBuilder_ == null && this.queryElv_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public QueryElv getQueryElv() {
                SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> singleFieldBuilderV3 = this.queryElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    QueryElv queryElv = this.queryElv_;
                    return queryElv == null ? QueryElv.getDefaultInstance() : queryElv;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setQueryElv(QueryElv queryElv) {
                SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> singleFieldBuilderV3 = this.queryElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(queryElv);
                } else {
                    if (queryElv == null) {
                        throw new NullPointerException();
                    }
                    this.queryElv_ = queryElv;
                    onChanged();
                }
                return this;
            }

            public Builder setQueryElv(QueryElv.Builder builder) {
                SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> singleFieldBuilderV3 = this.queryElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.queryElv_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeQueryElv(QueryElv queryElv) {
                SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> singleFieldBuilderV3 = this.queryElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    QueryElv queryElv2 = this.queryElv_;
                    if (queryElv2 != null) {
                        this.queryElv_ = QueryElv.newBuilder(queryElv2).mergeFrom(queryElv).buildPartial();
                    } else {
                        this.queryElv_ = queryElv;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(queryElv);
                }
                return this;
            }

            public Builder clearQueryElv() {
                if (this.queryElvBuilder_ == null) {
                    this.queryElv_ = null;
                    onChanged();
                } else {
                    this.queryElv_ = null;
                    this.queryElvBuilder_ = null;
                }
                return this;
            }

            public QueryElv.Builder getQueryElvBuilder() {
                onChanged();
                return getQueryElvFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public QueryElvOrBuilder getQueryElvOrBuilder() {
                SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> singleFieldBuilderV3 = this.queryElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                QueryElv queryElv = this.queryElv_;
                return queryElv == null ? QueryElv.getDefaultInstance() : queryElv;
            }

            private SingleFieldBuilderV3<QueryElv, QueryElv.Builder, QueryElvOrBuilder> getQueryElvFieldBuilder() {
                if (this.queryElvBuilder_ == null) {
                    this.queryElvBuilder_ = new SingleFieldBuilderV3<>(getQueryElv(), getParentForChildren(), isClean());
                    this.queryElv_ = null;
                }
                return this.queryElvBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasQueryElvResp() {
                return (this.queryElvRespBuilder_ == null && this.queryElvResp_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public QueryElvResp getQueryElvResp() {
                SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> singleFieldBuilderV3 = this.queryElvRespBuilder_;
                if (singleFieldBuilderV3 == null) {
                    QueryElvResp queryElvResp = this.queryElvResp_;
                    return queryElvResp == null ? QueryElvResp.getDefaultInstance() : queryElvResp;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setQueryElvResp(QueryElvResp queryElvResp) {
                SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> singleFieldBuilderV3 = this.queryElvRespBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(queryElvResp);
                } else {
                    if (queryElvResp == null) {
                        throw new NullPointerException();
                    }
                    this.queryElvResp_ = queryElvResp;
                    onChanged();
                }
                return this;
            }

            public Builder setQueryElvResp(QueryElvResp.Builder builder) {
                SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> singleFieldBuilderV3 = this.queryElvRespBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.queryElvResp_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeQueryElvResp(QueryElvResp queryElvResp) {
                SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> singleFieldBuilderV3 = this.queryElvRespBuilder_;
                if (singleFieldBuilderV3 == null) {
                    QueryElvResp queryElvResp2 = this.queryElvResp_;
                    if (queryElvResp2 != null) {
                        this.queryElvResp_ = QueryElvResp.newBuilder(queryElvResp2).mergeFrom(queryElvResp).buildPartial();
                    } else {
                        this.queryElvResp_ = queryElvResp;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(queryElvResp);
                }
                return this;
            }

            public Builder clearQueryElvResp() {
                if (this.queryElvRespBuilder_ == null) {
                    this.queryElvResp_ = null;
                    onChanged();
                } else {
                    this.queryElvResp_ = null;
                    this.queryElvRespBuilder_ = null;
                }
                return this;
            }

            public QueryElvResp.Builder getQueryElvRespBuilder() {
                onChanged();
                return getQueryElvRespFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public QueryElvRespOrBuilder getQueryElvRespOrBuilder() {
                SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> singleFieldBuilderV3 = this.queryElvRespBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                QueryElvResp queryElvResp = this.queryElvResp_;
                return queryElvResp == null ? QueryElvResp.getDefaultInstance() : queryElvResp;
            }

            private SingleFieldBuilderV3<QueryElvResp, QueryElvResp.Builder, QueryElvRespOrBuilder> getQueryElvRespFieldBuilder() {
                if (this.queryElvRespBuilder_ == null) {
                    this.queryElvRespBuilder_ = new SingleFieldBuilderV3<>(getQueryElvResp(), getParentForChildren(), isClean());
                    this.queryElvResp_ = null;
                }
                return this.queryElvRespBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasPrepareRide() {
                return (this.prepareRideBuilder_ == null && this.prepareRide_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public PrepareRide getPrepareRide() {
                SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> singleFieldBuilderV3 = this.prepareRideBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PrepareRide prepareRide = this.prepareRide_;
                    return prepareRide == null ? PrepareRide.getDefaultInstance() : prepareRide;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPrepareRide(PrepareRide prepareRide) {
                SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> singleFieldBuilderV3 = this.prepareRideBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(prepareRide);
                } else {
                    if (prepareRide == null) {
                        throw new NullPointerException();
                    }
                    this.prepareRide_ = prepareRide;
                    onChanged();
                }
                return this;
            }

            public Builder setPrepareRide(PrepareRide.Builder builder) {
                SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> singleFieldBuilderV3 = this.prepareRideBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.prepareRide_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePrepareRide(PrepareRide prepareRide) {
                SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> singleFieldBuilderV3 = this.prepareRideBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PrepareRide prepareRide2 = this.prepareRide_;
                    if (prepareRide2 != null) {
                        this.prepareRide_ = PrepareRide.newBuilder(prepareRide2).mergeFrom(prepareRide).buildPartial();
                    } else {
                        this.prepareRide_ = prepareRide;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(prepareRide);
                }
                return this;
            }

            public Builder clearPrepareRide() {
                if (this.prepareRideBuilder_ == null) {
                    this.prepareRide_ = null;
                    onChanged();
                } else {
                    this.prepareRide_ = null;
                    this.prepareRideBuilder_ = null;
                }
                return this;
            }

            public PrepareRide.Builder getPrepareRideBuilder() {
                onChanged();
                return getPrepareRideFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public PrepareRideOrBuilder getPrepareRideOrBuilder() {
                SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> singleFieldBuilderV3 = this.prepareRideBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PrepareRide prepareRide = this.prepareRide_;
                return prepareRide == null ? PrepareRide.getDefaultInstance() : prepareRide;
            }

            private SingleFieldBuilderV3<PrepareRide, PrepareRide.Builder, PrepareRideOrBuilder> getPrepareRideFieldBuilder() {
                if (this.prepareRideBuilder_ == null) {
                    this.prepareRideBuilder_ = new SingleFieldBuilderV3<>(getPrepareRide(), getParentForChildren(), isClean());
                    this.prepareRide_ = null;
                }
                return this.prepareRideBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasPrepareRideAck() {
                return (this.prepareRideAckBuilder_ == null && this.prepareRideAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public PrepareRideAck getPrepareRideAck() {
                SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> singleFieldBuilderV3 = this.prepareRideAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PrepareRideAck prepareRideAck = this.prepareRideAck_;
                    return prepareRideAck == null ? PrepareRideAck.getDefaultInstance() : prepareRideAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setPrepareRideAck(PrepareRideAck prepareRideAck) {
                SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> singleFieldBuilderV3 = this.prepareRideAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(prepareRideAck);
                } else {
                    if (prepareRideAck == null) {
                        throw new NullPointerException();
                    }
                    this.prepareRideAck_ = prepareRideAck;
                    onChanged();
                }
                return this;
            }

            public Builder setPrepareRideAck(PrepareRideAck.Builder builder) {
                SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> singleFieldBuilderV3 = this.prepareRideAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.prepareRideAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergePrepareRideAck(PrepareRideAck prepareRideAck) {
                SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> singleFieldBuilderV3 = this.prepareRideAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    PrepareRideAck prepareRideAck2 = this.prepareRideAck_;
                    if (prepareRideAck2 != null) {
                        this.prepareRideAck_ = PrepareRideAck.newBuilder(prepareRideAck2).mergeFrom(prepareRideAck).buildPartial();
                    } else {
                        this.prepareRideAck_ = prepareRideAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(prepareRideAck);
                }
                return this;
            }

            public Builder clearPrepareRideAck() {
                if (this.prepareRideAckBuilder_ == null) {
                    this.prepareRideAck_ = null;
                    onChanged();
                } else {
                    this.prepareRideAck_ = null;
                    this.prepareRideAckBuilder_ = null;
                }
                return this;
            }

            public PrepareRideAck.Builder getPrepareRideAckBuilder() {
                onChanged();
                return getPrepareRideAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public PrepareRideAckOrBuilder getPrepareRideAckOrBuilder() {
                SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> singleFieldBuilderV3 = this.prepareRideAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                PrepareRideAck prepareRideAck = this.prepareRideAck_;
                return prepareRideAck == null ? PrepareRideAck.getDefaultInstance() : prepareRideAck;
            }

            private SingleFieldBuilderV3<PrepareRideAck, PrepareRideAck.Builder, PrepareRideAckOrBuilder> getPrepareRideAckFieldBuilder() {
                if (this.prepareRideAckBuilder_ == null) {
                    this.prepareRideAckBuilder_ = new SingleFieldBuilderV3<>(getPrepareRideAck(), getParentForChildren(), isClean());
                    this.prepareRideAck_ = null;
                }
                return this.prepareRideAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasCancelElv() {
                return (this.cancelElvBuilder_ == null && this.cancelElv_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CancelElv getCancelElv() {
                SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> singleFieldBuilderV3 = this.cancelElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelElv cancelElv = this.cancelElv_;
                    return cancelElv == null ? CancelElv.getDefaultInstance() : cancelElv;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCancelElv(CancelElv cancelElv) {
                SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> singleFieldBuilderV3 = this.cancelElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(cancelElv);
                } else {
                    if (cancelElv == null) {
                        throw new NullPointerException();
                    }
                    this.cancelElv_ = cancelElv;
                    onChanged();
                }
                return this;
            }

            public Builder setCancelElv(CancelElv.Builder builder) {
                SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> singleFieldBuilderV3 = this.cancelElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.cancelElv_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCancelElv(CancelElv cancelElv) {
                SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> singleFieldBuilderV3 = this.cancelElvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelElv cancelElv2 = this.cancelElv_;
                    if (cancelElv2 != null) {
                        this.cancelElv_ = CancelElv.newBuilder(cancelElv2).mergeFrom(cancelElv).buildPartial();
                    } else {
                        this.cancelElv_ = cancelElv;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(cancelElv);
                }
                return this;
            }

            public Builder clearCancelElv() {
                if (this.cancelElvBuilder_ == null) {
                    this.cancelElv_ = null;
                    onChanged();
                } else {
                    this.cancelElv_ = null;
                    this.cancelElvBuilder_ = null;
                }
                return this;
            }

            public CancelElv.Builder getCancelElvBuilder() {
                onChanged();
                return getCancelElvFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CancelElvOrBuilder getCancelElvOrBuilder() {
                SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> singleFieldBuilderV3 = this.cancelElvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CancelElv cancelElv = this.cancelElv_;
                return cancelElv == null ? CancelElv.getDefaultInstance() : cancelElv;
            }

            private SingleFieldBuilderV3<CancelElv, CancelElv.Builder, CancelElvOrBuilder> getCancelElvFieldBuilder() {
                if (this.cancelElvBuilder_ == null) {
                    this.cancelElvBuilder_ = new SingleFieldBuilderV3<>(getCancelElv(), getParentForChildren(), isClean());
                    this.cancelElv_ = null;
                }
                return this.cancelElvBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public boolean hasCancelElvAck() {
                return (this.cancelElvAckBuilder_ == null && this.cancelElvAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CancelElvAck getCancelElvAck() {
                SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> singleFieldBuilderV3 = this.cancelElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelElvAck cancelElvAck = this.cancelElvAck_;
                    return cancelElvAck == null ? CancelElvAck.getDefaultInstance() : cancelElvAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCancelElvAck(CancelElvAck cancelElvAck) {
                SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> singleFieldBuilderV3 = this.cancelElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(cancelElvAck);
                } else {
                    if (cancelElvAck == null) {
                        throw new NullPointerException();
                    }
                    this.cancelElvAck_ = cancelElvAck;
                    onChanged();
                }
                return this;
            }

            public Builder setCancelElvAck(CancelElvAck.Builder builder) {
                SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> singleFieldBuilderV3 = this.cancelElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.cancelElvAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCancelElvAck(CancelElvAck cancelElvAck) {
                SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> singleFieldBuilderV3 = this.cancelElvAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelElvAck cancelElvAck2 = this.cancelElvAck_;
                    if (cancelElvAck2 != null) {
                        this.cancelElvAck_ = CancelElvAck.newBuilder(cancelElvAck2).mergeFrom(cancelElvAck).buildPartial();
                    } else {
                        this.cancelElvAck_ = cancelElvAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(cancelElvAck);
                }
                return this;
            }

            public Builder clearCancelElvAck() {
                if (this.cancelElvAckBuilder_ == null) {
                    this.cancelElvAck_ = null;
                    onChanged();
                } else {
                    this.cancelElvAck_ = null;
                    this.cancelElvAckBuilder_ = null;
                }
                return this;
            }

            public CancelElvAck.Builder getCancelElvAckBuilder() {
                onChanged();
                return getCancelElvAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvOrBuilder
            public CancelElvAckOrBuilder getCancelElvAckOrBuilder() {
                SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> singleFieldBuilderV3 = this.cancelElvAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CancelElvAck cancelElvAck = this.cancelElvAck_;
                return cancelElvAck == null ? CancelElvAck.getDefaultInstance() : cancelElvAck;
            }

            private SingleFieldBuilderV3<CancelElvAck, CancelElvAck.Builder, CancelElvAckOrBuilder> getCancelElvAckFieldBuilder() {
                if (this.cancelElvAckBuilder_ == null) {
                    this.cancelElvAckBuilder_ = new SingleFieldBuilderV3<>(getCancelElvAck(), getParentForChildren(), isClean());
                    this.cancelElvAck_ = null;
                }
                return this.cancelElvAckBuilder_;
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

        public static Elv getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Elv> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Elv> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Elv getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class ElvState extends GeneratedMessageV3 implements ElvStateOrBuilder {
        private static final ElvState DEFAULT_INSTANCE = new ElvState();
        private static final Parser<ElvState> PARSER = new AbstractParser<ElvState>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.ElvState.1
            @Override // com.google.protobuf.Parser
            public ElvState parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvState(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int state_;

        private ElvState(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvState() {
            this.memoizedIsInitialized = (byte) -1;
            this.state_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvState(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.state_ = codedInputStream.readEnum();
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
            return Elevator.f5806x94909630;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5807xdde8cae.ensureFieldAccessorsInitialized(ElvState.class, Builder.class);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public enum State implements ProtocolMessageEnum {
            Free(0),
            RunUp(1),
            GoDown(2),
            UNRECOGNIZED(-1);

            public static final int Free_VALUE = 0;
            public static final int GoDown_VALUE = 2;
            public static final int RunUp_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.ElvState.State.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public State findValueByNumber(int i) {
                    return State.forNumber(i);
                }
            };
            private static final State[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static State valueOf(int i) {
                return forNumber(i);
            }

            public static State forNumber(int i) {
                if (i == 0) {
                    return Free;
                }
                if (i == 1) {
                    return RunUp;
                }
                if (i != 2) {
                    return null;
                }
                return GoDown;
            }

            public static Internal.EnumLiteMap<State> internalGetValueMap() {
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
                return ElvState.getDescriptor().getEnumTypes().get(0);
            }

            public static State valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            State(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvStateOrBuilder
        public int getStateValue() {
            return this.state_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvStateOrBuilder
        public State getState() {
            State valueOf = State.valueOf(this.state_);
            return valueOf == null ? State.UNRECOGNIZED : valueOf;
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
            if (this.state_ != State.Free.getNumber()) {
                codedOutputStream.writeEnum(1, this.state_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = (this.state_ != State.Free.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.state_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeEnumSize;
            return computeEnumSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ElvState)) {
                return super.equals(obj);
            }
            ElvState elvState = (ElvState) obj;
            return (this.state_ == elvState.state_) && this.unknownFields.equals(elvState.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.state_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ElvState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvState parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvState parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvState parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvState parseFrom(InputStream inputStream) throws IOException {
            return (ElvState) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvState parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvState) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvState) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvState) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvState parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvState) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvState parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvState) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvState elvState) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvState);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvStateOrBuilder {
            private int state_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5806x94909630;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5807xdde8cae.ensureFieldAccessorsInitialized(ElvState.class, Builder.class);
            }

            private Builder() {
                this.state_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvState.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.state_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5806x94909630;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvState getDefaultInstanceForType() {
                return ElvState.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvState build() {
                ElvState buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvState buildPartial() {
                ElvState elvState = new ElvState(this);
                elvState.state_ = this.state_;
                onBuilt();
                return elvState;
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
                if (message instanceof ElvState) {
                    return mergeFrom((ElvState) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvState elvState) {
                if (elvState == ElvState.getDefaultInstance()) {
                    return this;
                }
                if (elvState.state_ != 0) {
                    setStateValue(elvState.getStateValue());
                }
                mergeUnknownFields(elvState.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvState elvState = null;
                try {
                    try {
                        ElvState elvState2 = (ElvState) ElvState.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvState2 != null) {
                            mergeFrom(elvState2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvState elvState3 = (ElvState) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvState = elvState3;
                            if (elvState != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvState != null) {
                        mergeFrom(elvState);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvStateOrBuilder
            public int getStateValue() {
                return this.state_;
            }

            public Builder setStateValue(int i) {
                this.state_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvStateOrBuilder
            public State getState() {
                State valueOf = State.valueOf(this.state_);
                return valueOf == null ? State.UNRECOGNIZED : valueOf;
            }

            public Builder setState(State state) {
                if (state == null) {
                    throw new NullPointerException();
                }
                this.state_ = state.getNumber();
                onChanged();
                return this;
            }

            public Builder clearState() {
                this.state_ = 0;
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

        public static ElvState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvState> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvState> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvState getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class CallElv extends GeneratedMessageV3 implements CallElvOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        public static final int DEST_FLOOR_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private volatile Object destFloor_;
        private byte memoizedIsInitialized;
        private static final CallElv DEFAULT_INSTANCE = new CallElv();
        private static final Parser<CallElv> PARSER = new AbstractParser<CallElv>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.CallElv.1
            @Override // com.google.protobuf.Parser
            public CallElv parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CallElv(codedInputStream, extensionRegistryLite);
            }
        };

        private CallElv(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CallElv() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
            this.destFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CallElv(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    this.currFloor_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.destFloor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_CallElv_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5794x39601c77.ensureFieldAccessorsInitialized(CallElv.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
        public String getDestFloor() {
            Object obj = this.destFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.destFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
        public ByteString getDestFloorBytes() {
            Object obj = this.destFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.destFloor_ = copyFromUtf8;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            if (!getDestFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.destFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_);
            if (!getDestFloorBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(2, this.destFloor_);
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
            if (!(obj instanceof CallElv)) {
                return super.equals(obj);
            }
            CallElv callElv = (CallElv) obj;
            return ((getCurrFloor().equals(callElv.getCurrFloor())) && getDestFloor().equals(callElv.getDestFloor())) && this.unknownFields.equals(callElv.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 37) + 2) * 53) + getDestFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static CallElv parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CallElv parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CallElv parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CallElv parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CallElv parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CallElv parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CallElv parseFrom(InputStream inputStream) throws IOException {
            return (CallElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CallElv parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallElv parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CallElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CallElv parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallElv parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CallElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CallElv parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CallElv callElv) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(callElv);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallElvOrBuilder {
            private Object currFloor_;
            private Object destFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_CallElv_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5794x39601c77.ensureFieldAccessorsInitialized(CallElv.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CallElv.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                this.destFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_CallElv_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CallElv getDefaultInstanceForType() {
                return CallElv.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallElv build() {
                CallElv buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallElv buildPartial() {
                CallElv callElv = new CallElv(this);
                callElv.currFloor_ = this.currFloor_;
                callElv.destFloor_ = this.destFloor_;
                onBuilt();
                return callElv;
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
                if (message instanceof CallElv) {
                    return mergeFrom((CallElv) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CallElv callElv) {
                if (callElv == CallElv.getDefaultInstance()) {
                    return this;
                }
                if (!callElv.getCurrFloor().isEmpty()) {
                    this.currFloor_ = callElv.currFloor_;
                    onChanged();
                }
                if (!callElv.getDestFloor().isEmpty()) {
                    this.destFloor_ = callElv.destFloor_;
                    onChanged();
                }
                mergeUnknownFields(callElv.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CallElv callElv = null;
                try {
                    try {
                        CallElv callElv2 = (CallElv) CallElv.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (callElv2 != null) {
                            mergeFrom(callElv2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CallElv callElv3 = (CallElv) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            callElv = callElv3;
                            if (callElv != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (callElv != null) {
                        mergeFrom(callElv);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = CallElv.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    CallElv.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
            public String getDestFloor() {
                Object obj = this.destFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.destFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvOrBuilder
            public ByteString getDestFloorBytes() {
                Object obj = this.destFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.destFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDestFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.destFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearDestFloor() {
                this.destFloor_ = CallElv.getDefaultInstance().getDestFloor();
                onChanged();
                return this;
            }

            public Builder setDestFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    CallElv.checkByteStringIsUtf8(byteString);
                    this.destFloor_ = byteString;
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

        public static CallElv getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CallElv> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CallElv> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CallElv getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class CallElvAck extends GeneratedMessageV3 implements CallElvAckOrBuilder {
        public static final int FLOOR_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object floor_;
        private byte memoizedIsInitialized;
        private ElvState state_;
        private static final CallElvAck DEFAULT_INSTANCE = new CallElvAck();
        private static final Parser<CallElvAck> PARSER = new AbstractParser<CallElvAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.CallElvAck.1
            @Override // com.google.protobuf.Parser
            public CallElvAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CallElvAck(codedInputStream, extensionRegistryLite);
            }
        };

        private CallElvAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CallElvAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.floor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CallElvAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    ElvState.Builder builder = this.state_ != null ? this.state_.toBuilder() : null;
                                    this.state_ = (ElvState) codedInputStream.readMessage(ElvState.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.state_);
                                        this.state_ = builder.buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.floor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5792x19e1db3a;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5793xeec727b8.ensureFieldAccessorsInitialized(CallElvAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
        public boolean hasState() {
            return this.state_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
        public ElvState getState() {
            ElvState elvState = this.state_;
            return elvState == null ? ElvState.getDefaultInstance() : elvState;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
        public ElvStateOrBuilder getStateOrBuilder() {
            return getState();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
        public String getFloor() {
            Object obj = this.floor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.floor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
        public ByteString getFloorBytes() {
            Object obj = this.floor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.floor_ = copyFromUtf8;
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
            if (this.state_ != null) {
                codedOutputStream.writeMessage(1, getState());
            }
            if (!getFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.floor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.state_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getState()) : 0;
            if (!getFloorBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.floor_);
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
            if (!(obj instanceof CallElvAck)) {
                return super.equals(obj);
            }
            CallElvAck callElvAck = (CallElvAck) obj;
            boolean z = hasState() == callElvAck.hasState();
            if (hasState()) {
                z = z && getState().equals(callElvAck.getState());
            }
            return (z && getFloor().equals(callElvAck.getFloor())) && this.unknownFields.equals(callElvAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasState()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getState().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 2) * 53) + getFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CallElvAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CallElvAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CallElvAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CallElvAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CallElvAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CallElvAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CallElvAck parseFrom(InputStream inputStream) throws IOException {
            return (CallElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CallElvAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallElvAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CallElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CallElvAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallElvAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CallElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CallElvAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CallElvAck callElvAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(callElvAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallElvAckOrBuilder {
            private Object floor_;
            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> stateBuilder_;
            private ElvState state_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5792x19e1db3a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5793xeec727b8.ensureFieldAccessorsInitialized(CallElvAck.class, Builder.class);
            }

            private Builder() {
                this.state_ = null;
                this.floor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = null;
                this.floor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CallElvAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                this.floor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5792x19e1db3a;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CallElvAck getDefaultInstanceForType() {
                return CallElvAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallElvAck build() {
                CallElvAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallElvAck buildPartial() {
                CallElvAck callElvAck = new CallElvAck(this);
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    callElvAck.state_ = this.state_;
                } else {
                    callElvAck.state_ = singleFieldBuilderV3.build();
                }
                callElvAck.floor_ = this.floor_;
                onBuilt();
                return callElvAck;
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
                if (message instanceof CallElvAck) {
                    return mergeFrom((CallElvAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CallElvAck callElvAck) {
                if (callElvAck == CallElvAck.getDefaultInstance()) {
                    return this;
                }
                if (callElvAck.hasState()) {
                    mergeState(callElvAck.getState());
                }
                if (!callElvAck.getFloor().isEmpty()) {
                    this.floor_ = callElvAck.floor_;
                    onChanged();
                }
                mergeUnknownFields(callElvAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CallElvAck callElvAck = null;
                try {
                    try {
                        CallElvAck callElvAck2 = (CallElvAck) CallElvAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (callElvAck2 != null) {
                            mergeFrom(callElvAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CallElvAck callElvAck3 = (CallElvAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            callElvAck = callElvAck3;
                            if (callElvAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (callElvAck != null) {
                        mergeFrom(callElvAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
            public boolean hasState() {
                return (this.stateBuilder_ == null && this.state_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
            public ElvState getState() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState = this.state_;
                    return elvState == null ? ElvState.getDefaultInstance() : elvState;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvState);
                } else {
                    if (elvState == null) {
                        throw new NullPointerException();
                    }
                    this.state_ = elvState;
                    onChanged();
                }
                return this;
            }

            public Builder setState(ElvState.Builder builder) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.state_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState2 = this.state_;
                    if (elvState2 != null) {
                        this.state_ = ElvState.newBuilder(elvState2).mergeFrom(elvState).buildPartial();
                    } else {
                        this.state_ = elvState;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvState);
                }
                return this;
            }

            public Builder clearState() {
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                    onChanged();
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                return this;
            }

            public ElvState.Builder getStateBuilder() {
                onChanged();
                return getStateFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
            public ElvStateOrBuilder getStateOrBuilder() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvState elvState = this.state_;
                return elvState == null ? ElvState.getDefaultInstance() : elvState;
            }

            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> getStateFieldBuilder() {
                if (this.stateBuilder_ == null) {
                    this.stateBuilder_ = new SingleFieldBuilderV3<>(getState(), getParentForChildren(), isClean());
                    this.state_ = null;
                }
                return this.stateBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
            public String getFloor() {
                Object obj = this.floor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.floor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CallElvAckOrBuilder
            public ByteString getFloorBytes() {
                Object obj = this.floor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.floor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.floor_ = str;
                onChanged();
                return this;
            }

            public Builder clearFloor() {
                this.floor_ = CallElvAck.getDefaultInstance().getFloor();
                onChanged();
                return this;
            }

            public Builder setFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    CallElvAck.checkByteStringIsUtf8(byteString);
                    this.floor_ = byteString;
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

        public static CallElvAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CallElvAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CallElvAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CallElvAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class EnterElv extends GeneratedMessageV3 implements EnterElvOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        public static final int EFF_TIME_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private int effTime_;
        private byte memoizedIsInitialized;
        private static final EnterElv DEFAULT_INSTANCE = new EnterElv();
        private static final Parser<EnterElv> PARSER = new AbstractParser<EnterElv>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.EnterElv.1
            @Override // com.google.protobuf.Parser
            public EnterElv parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EnterElv(codedInputStream, extensionRegistryLite);
            }
        };

        private EnterElv(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnterElv() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
            this.effTime_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private EnterElv(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    this.currFloor_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.effTime_ = codedInputStream.readInt32();
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
            return Elevator.f5811xff5d1d7b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5812x734098f9.ensureFieldAccessorsInitialized(EnterElv.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvOrBuilder
        public int getEffTime() {
            return this.effTime_;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            int i = this.effTime_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_);
            int i2 = this.effTime_;
            if (i2 != 0) {
                computeStringSize += CodedOutputStream.computeInt32Size(2, i2);
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
            if (!(obj instanceof EnterElv)) {
                return super.equals(obj);
            }
            EnterElv enterElv = (EnterElv) obj;
            return ((getCurrFloor().equals(enterElv.getCurrFloor())) && getEffTime() == enterElv.getEffTime()) && this.unknownFields.equals(enterElv.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 37) + 2) * 53) + getEffTime()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static EnterElv parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static EnterElv parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static EnterElv parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static EnterElv parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnterElv parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static EnterElv parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static EnterElv parseFrom(InputStream inputStream) throws IOException {
            return (EnterElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static EnterElv parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnterElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EnterElv parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (EnterElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static EnterElv parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnterElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EnterElv parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (EnterElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static EnterElv parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnterElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(EnterElv enterElv) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(enterElv);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnterElvOrBuilder {
            private Object currFloor_;
            private int effTime_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5811xff5d1d7b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5812x734098f9.ensureFieldAccessorsInitialized(EnterElv.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = EnterElv.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                this.effTime_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5811xff5d1d7b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnterElv getDefaultInstanceForType() {
                return EnterElv.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnterElv build() {
                EnterElv buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnterElv buildPartial() {
                EnterElv enterElv = new EnterElv(this);
                enterElv.currFloor_ = this.currFloor_;
                enterElv.effTime_ = this.effTime_;
                onBuilt();
                return enterElv;
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
                if (message instanceof EnterElv) {
                    return mergeFrom((EnterElv) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(EnterElv enterElv) {
                if (enterElv == EnterElv.getDefaultInstance()) {
                    return this;
                }
                if (!enterElv.getCurrFloor().isEmpty()) {
                    this.currFloor_ = enterElv.currFloor_;
                    onChanged();
                }
                if (enterElv.getEffTime() != 0) {
                    setEffTime(enterElv.getEffTime());
                }
                mergeUnknownFields(enterElv.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                EnterElv enterElv = null;
                try {
                    try {
                        EnterElv enterElv2 = (EnterElv) EnterElv.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (enterElv2 != null) {
                            mergeFrom(enterElv2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        EnterElv enterElv3 = (EnterElv) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            enterElv = enterElv3;
                            if (enterElv != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (enterElv != null) {
                        mergeFrom(enterElv);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = EnterElv.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    EnterElv.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvOrBuilder
            public int getEffTime() {
                return this.effTime_;
            }

            public Builder setEffTime(int i) {
                this.effTime_ = i;
                onChanged();
                return this;
            }

            public Builder clearEffTime() {
                this.effTime_ = 0;
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

        public static EnterElv getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EnterElv> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<EnterElv> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EnterElv getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class EnterElvAck extends GeneratedMessageV3 implements EnterElvAckOrBuilder {
        public static final int DISTANCE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private double distance_;
        private byte memoizedIsInitialized;
        private static final EnterElvAck DEFAULT_INSTANCE = new EnterElvAck();
        private static final Parser<EnterElvAck> PARSER = new AbstractParser<EnterElvAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.EnterElvAck.1
            @Override // com.google.protobuf.Parser
            public EnterElvAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EnterElvAck(codedInputStream, extensionRegistryLite);
            }
        };

        private EnterElvAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnterElvAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.distance_ = 0.0d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private EnterElvAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 9) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.distance_ = codedInputStream.readDouble();
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
            return Elevator.f5809xe2e21578;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5810x218043f6.ensureFieldAccessorsInitialized(EnterElvAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvAckOrBuilder
        public double getDistance() {
            return this.distance_;
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
            double d = this.distance_;
            if (d != 0.0d) {
                codedOutputStream.writeDouble(1, d);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            double d = this.distance_;
            int computeDoubleSize = (d != 0.0d ? 0 + CodedOutputStream.computeDoubleSize(1, d) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeDoubleSize;
            return computeDoubleSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnterElvAck)) {
                return super.equals(obj);
            }
            EnterElvAck enterElvAck = (EnterElvAck) obj;
            return ((Double.doubleToLongBits(getDistance()) > Double.doubleToLongBits(enterElvAck.getDistance()) ? 1 : (Double.doubleToLongBits(getDistance()) == Double.doubleToLongBits(enterElvAck.getDistance()) ? 0 : -1)) == 0) && this.unknownFields.equals(enterElvAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getDistance()))) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static EnterElvAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static EnterElvAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static EnterElvAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static EnterElvAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnterElvAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static EnterElvAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static EnterElvAck parseFrom(InputStream inputStream) throws IOException {
            return (EnterElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static EnterElvAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnterElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EnterElvAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (EnterElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static EnterElvAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnterElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EnterElvAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (EnterElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static EnterElvAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnterElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(EnterElvAck enterElvAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(enterElvAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnterElvAckOrBuilder {
            private double distance_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5809xe2e21578;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5810x218043f6.ensureFieldAccessorsInitialized(EnterElvAck.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = EnterElvAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.distance_ = 0.0d;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5809xe2e21578;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public EnterElvAck getDefaultInstanceForType() {
                return EnterElvAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnterElvAck build() {
                EnterElvAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public EnterElvAck buildPartial() {
                EnterElvAck enterElvAck = new EnterElvAck(this);
                enterElvAck.distance_ = this.distance_;
                onBuilt();
                return enterElvAck;
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
                if (message instanceof EnterElvAck) {
                    return mergeFrom((EnterElvAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(EnterElvAck enterElvAck) {
                if (enterElvAck == EnterElvAck.getDefaultInstance()) {
                    return this;
                }
                if (enterElvAck.getDistance() != 0.0d) {
                    setDistance(enterElvAck.getDistance());
                }
                mergeUnknownFields(enterElvAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                EnterElvAck enterElvAck = null;
                try {
                    try {
                        EnterElvAck enterElvAck2 = (EnterElvAck) EnterElvAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (enterElvAck2 != null) {
                            mergeFrom(enterElvAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        EnterElvAck enterElvAck3 = (EnterElvAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            enterElvAck = enterElvAck3;
                            if (enterElvAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (enterElvAck != null) {
                        mergeFrom(enterElvAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.EnterElvAckOrBuilder
            public double getDistance() {
                return this.distance_;
            }

            public Builder setDistance(double d) {
                this.distance_ = d;
                onChanged();
                return this;
            }

            public Builder clearDistance() {
                this.distance_ = 0.0d;
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

        public static EnterElvAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EnterElvAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<EnterElvAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EnterElvAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class ElvEntered extends GeneratedMessageV3 implements ElvEnteredOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        private static final ElvEntered DEFAULT_INSTANCE = new ElvEntered();
        private static final Parser<ElvEntered> PARSER = new AbstractParser<ElvEntered>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.ElvEntered.1
            @Override // com.google.protobuf.Parser
            public ElvEntered parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvEntered(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private byte memoizedIsInitialized;

        private ElvEntered(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvEntered() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvEntered(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.currFloor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5801x2045e8ca;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5802x314ca548.ensureFieldAccessorsInitialized(ElvEntered.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvEnteredOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvEnteredOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ElvEntered)) {
                return super.equals(obj);
            }
            ElvEntered elvEntered = (ElvEntered) obj;
            return (getCurrFloor().equals(elvEntered.getCurrFloor())) && this.unknownFields.equals(elvEntered.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ElvEntered parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvEntered parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvEntered parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvEntered parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvEntered parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvEntered parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvEntered parseFrom(InputStream inputStream) throws IOException {
            return (ElvEntered) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvEntered parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvEntered) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvEntered parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvEntered) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvEntered parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvEntered) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvEntered parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvEntered) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvEntered parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvEntered) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvEntered elvEntered) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvEntered);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvEnteredOrBuilder {
            private Object currFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5801x2045e8ca;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5802x314ca548.ensureFieldAccessorsInitialized(ElvEntered.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvEntered.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5801x2045e8ca;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvEntered getDefaultInstanceForType() {
                return ElvEntered.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvEntered build() {
                ElvEntered buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvEntered buildPartial() {
                ElvEntered elvEntered = new ElvEntered(this);
                elvEntered.currFloor_ = this.currFloor_;
                onBuilt();
                return elvEntered;
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
                if (message instanceof ElvEntered) {
                    return mergeFrom((ElvEntered) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvEntered elvEntered) {
                if (elvEntered == ElvEntered.getDefaultInstance()) {
                    return this;
                }
                if (!elvEntered.getCurrFloor().isEmpty()) {
                    this.currFloor_ = elvEntered.currFloor_;
                    onChanged();
                }
                mergeUnknownFields(elvEntered.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvEntered elvEntered = null;
                try {
                    try {
                        ElvEntered elvEntered2 = (ElvEntered) ElvEntered.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvEntered2 != null) {
                            mergeFrom(elvEntered2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvEntered elvEntered3 = (ElvEntered) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvEntered = elvEntered3;
                            if (elvEntered != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvEntered != null) {
                        mergeFrom(elvEntered);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvEnteredOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvEnteredOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = ElvEntered.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    ElvEntered.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
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

        public static ElvEntered getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvEntered> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvEntered> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvEntered getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class ElvEnteredAck extends GeneratedMessageV3 implements ElvEnteredAckOrBuilder {
        private static final ElvEnteredAck DEFAULT_INSTANCE = new ElvEnteredAck();
        private static final Parser<ElvEnteredAck> PARSER = new AbstractParser<ElvEnteredAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.ElvEnteredAck.1
            @Override // com.google.protobuf.Parser
            public ElvEnteredAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvEnteredAck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private ElvEnteredAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvEnteredAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvEnteredAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 0 || !parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
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
            return Elevator.f5799x956553c9;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5800x1d8ca147.ensureFieldAccessorsInitialized(ElvEnteredAck.class, Builder.class);
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
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ElvEnteredAck) {
                return this.unknownFields.equals(((ElvEnteredAck) obj).unknownFields);
            }
            return super.equals(obj);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ElvEnteredAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvEnteredAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvEnteredAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvEnteredAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvEnteredAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvEnteredAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvEnteredAck parseFrom(InputStream inputStream) throws IOException {
            return (ElvEnteredAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvEnteredAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvEnteredAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvEnteredAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvEnteredAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvEnteredAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvEnteredAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvEnteredAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvEnteredAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvEnteredAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvEnteredAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvEnteredAck elvEnteredAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvEnteredAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvEnteredAckOrBuilder {
            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5799x956553c9;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5800x1d8ca147.ensureFieldAccessorsInitialized(ElvEnteredAck.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvEnteredAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5799x956553c9;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvEnteredAck getDefaultInstanceForType() {
                return ElvEnteredAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvEnteredAck build() {
                ElvEnteredAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvEnteredAck buildPartial() {
                ElvEnteredAck elvEnteredAck = new ElvEnteredAck(this);
                onBuilt();
                return elvEnteredAck;
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
                if (message instanceof ElvEnteredAck) {
                    return mergeFrom((ElvEnteredAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvEnteredAck elvEnteredAck) {
                if (elvEnteredAck == ElvEnteredAck.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(elvEnteredAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvEnteredAck elvEnteredAck = null;
                try {
                    try {
                        ElvEnteredAck elvEnteredAck2 = (ElvEnteredAck) ElvEnteredAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvEnteredAck2 != null) {
                            mergeFrom(elvEnteredAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvEnteredAck elvEnteredAck3 = (ElvEnteredAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvEnteredAck = elvEnteredAck3;
                            if (elvEnteredAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvEnteredAck != null) {
                        mergeFrom(elvEnteredAck);
                    }
                    throw th;
                }
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

        public static ElvEnteredAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvEnteredAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvEnteredAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvEnteredAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class LeaveElv extends GeneratedMessageV3 implements LeaveElvOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        public static final int EFF_TIME_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private int effTime_;
        private byte memoizedIsInitialized;
        private static final LeaveElv DEFAULT_INSTANCE = new LeaveElv();
        private static final Parser<LeaveElv> PARSER = new AbstractParser<LeaveElv>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.LeaveElv.1
            @Override // com.google.protobuf.Parser
            public LeaveElv parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LeaveElv(codedInputStream, extensionRegistryLite);
            }
        };

        private LeaveElv(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private LeaveElv() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
            this.effTime_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LeaveElv(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    this.currFloor_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.effTime_ = codedInputStream.readInt32();
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
            return Elevator.f5815x23d4b31a;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5816x1bd41f98.ensureFieldAccessorsInitialized(LeaveElv.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvOrBuilder
        public int getEffTime() {
            return this.effTime_;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            int i = this.effTime_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_);
            int i2 = this.effTime_;
            if (i2 != 0) {
                computeStringSize += CodedOutputStream.computeInt32Size(2, i2);
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
            if (!(obj instanceof LeaveElv)) {
                return super.equals(obj);
            }
            LeaveElv leaveElv = (LeaveElv) obj;
            return ((getCurrFloor().equals(leaveElv.getCurrFloor())) && getEffTime() == leaveElv.getEffTime()) && this.unknownFields.equals(leaveElv.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 37) + 2) * 53) + getEffTime()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static LeaveElv parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static LeaveElv parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LeaveElv parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static LeaveElv parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LeaveElv parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static LeaveElv parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LeaveElv parseFrom(InputStream inputStream) throws IOException {
            return (LeaveElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LeaveElv parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LeaveElv parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LeaveElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LeaveElv parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LeaveElv parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LeaveElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LeaveElv parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LeaveElv leaveElv) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(leaveElv);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LeaveElvOrBuilder {
            private Object currFloor_;
            private int effTime_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5815x23d4b31a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5816x1bd41f98.ensureFieldAccessorsInitialized(LeaveElv.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LeaveElv.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                this.effTime_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5815x23d4b31a;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LeaveElv getDefaultInstanceForType() {
                return LeaveElv.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LeaveElv build() {
                LeaveElv buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LeaveElv buildPartial() {
                LeaveElv leaveElv = new LeaveElv(this);
                leaveElv.currFloor_ = this.currFloor_;
                leaveElv.effTime_ = this.effTime_;
                onBuilt();
                return leaveElv;
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
                if (message instanceof LeaveElv) {
                    return mergeFrom((LeaveElv) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LeaveElv leaveElv) {
                if (leaveElv == LeaveElv.getDefaultInstance()) {
                    return this;
                }
                if (!leaveElv.getCurrFloor().isEmpty()) {
                    this.currFloor_ = leaveElv.currFloor_;
                    onChanged();
                }
                if (leaveElv.getEffTime() != 0) {
                    setEffTime(leaveElv.getEffTime());
                }
                mergeUnknownFields(leaveElv.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LeaveElv leaveElv = null;
                try {
                    try {
                        LeaveElv leaveElv2 = (LeaveElv) LeaveElv.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (leaveElv2 != null) {
                            mergeFrom(leaveElv2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        LeaveElv leaveElv3 = (LeaveElv) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            leaveElv = leaveElv3;
                            if (leaveElv != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (leaveElv != null) {
                        mergeFrom(leaveElv);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = LeaveElv.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    LeaveElv.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvOrBuilder
            public int getEffTime() {
                return this.effTime_;
            }

            public Builder setEffTime(int i) {
                this.effTime_ = i;
                onChanged();
                return this;
            }

            public Builder clearEffTime() {
                this.effTime_ = 0;
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

        public static LeaveElv getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LeaveElv> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LeaveElv> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LeaveElv getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class LeaveElvAck extends GeneratedMessageV3 implements LeaveElvAckOrBuilder {
        private static final LeaveElvAck DEFAULT_INSTANCE = new LeaveElvAck();
        private static final Parser<LeaveElvAck> PARSER = new AbstractParser<LeaveElvAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.LeaveElvAck.1
            @Override // com.google.protobuf.Parser
            public LeaveElvAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LeaveElvAck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private LeaveElvAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private LeaveElvAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LeaveElvAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 0 || !parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
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
            return Elevator.f5813x9b0ea779;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5814x893f44f7.ensureFieldAccessorsInitialized(LeaveElvAck.class, Builder.class);
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
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof LeaveElvAck) {
                return this.unknownFields.equals(((LeaveElvAck) obj).unknownFields);
            }
            return super.equals(obj);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static LeaveElvAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static LeaveElvAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LeaveElvAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static LeaveElvAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LeaveElvAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static LeaveElvAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LeaveElvAck parseFrom(InputStream inputStream) throws IOException {
            return (LeaveElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LeaveElvAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LeaveElvAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LeaveElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LeaveElvAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LeaveElvAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LeaveElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LeaveElvAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LeaveElvAck leaveElvAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(leaveElvAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LeaveElvAckOrBuilder {
            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5813x9b0ea779;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5814x893f44f7.ensureFieldAccessorsInitialized(LeaveElvAck.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LeaveElvAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5813x9b0ea779;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LeaveElvAck getDefaultInstanceForType() {
                return LeaveElvAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LeaveElvAck build() {
                LeaveElvAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LeaveElvAck buildPartial() {
                LeaveElvAck leaveElvAck = new LeaveElvAck(this);
                onBuilt();
                return leaveElvAck;
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
                if (message instanceof LeaveElvAck) {
                    return mergeFrom((LeaveElvAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LeaveElvAck leaveElvAck) {
                if (leaveElvAck == LeaveElvAck.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(leaveElvAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LeaveElvAck leaveElvAck = null;
                try {
                    try {
                        LeaveElvAck leaveElvAck2 = (LeaveElvAck) LeaveElvAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (leaveElvAck2 != null) {
                            mergeFrom(leaveElvAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        LeaveElvAck leaveElvAck3 = (LeaveElvAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            leaveElvAck = leaveElvAck3;
                            if (leaveElvAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (leaveElvAck != null) {
                        mergeFrom(leaveElvAck);
                    }
                    throw th;
                }
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

        public static LeaveElvAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LeaveElvAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LeaveElvAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LeaveElvAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class ElvLeft extends GeneratedMessageV3 implements ElvLeftOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        private static final ElvLeft DEFAULT_INSTANCE = new ElvLeft();
        private static final Parser<ElvLeft> PARSER = new AbstractParser<ElvLeft>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.ElvLeft.1
            @Override // com.google.protobuf.Parser
            public ElvLeft parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvLeft(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private byte memoizedIsInitialized;

        private ElvLeft(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvLeft() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvLeft(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.currFloor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5805x32e06472.ensureFieldAccessorsInitialized(ElvLeft.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvLeftOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvLeftOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ElvLeft)) {
                return super.equals(obj);
            }
            ElvLeft elvLeft = (ElvLeft) obj;
            return (getCurrFloor().equals(elvLeft.getCurrFloor())) && this.unknownFields.equals(elvLeft.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ElvLeft parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvLeft parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvLeft parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvLeft parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvLeft parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvLeft parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvLeft parseFrom(InputStream inputStream) throws IOException {
            return (ElvLeft) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvLeft parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvLeft) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvLeft parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvLeft) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvLeft parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvLeft) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvLeft parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvLeft) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvLeft parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvLeft) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvLeft elvLeft) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvLeft);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvLeftOrBuilder {
            private Object currFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5805x32e06472.ensureFieldAccessorsInitialized(ElvLeft.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvLeft.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvLeft getDefaultInstanceForType() {
                return ElvLeft.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvLeft build() {
                ElvLeft buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvLeft buildPartial() {
                ElvLeft elvLeft = new ElvLeft(this);
                elvLeft.currFloor_ = this.currFloor_;
                onBuilt();
                return elvLeft;
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
                if (message instanceof ElvLeft) {
                    return mergeFrom((ElvLeft) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvLeft elvLeft) {
                if (elvLeft == ElvLeft.getDefaultInstance()) {
                    return this;
                }
                if (!elvLeft.getCurrFloor().isEmpty()) {
                    this.currFloor_ = elvLeft.currFloor_;
                    onChanged();
                }
                mergeUnknownFields(elvLeft.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvLeft elvLeft = null;
                try {
                    try {
                        ElvLeft elvLeft2 = (ElvLeft) ElvLeft.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvLeft2 != null) {
                            mergeFrom(elvLeft2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvLeft elvLeft3 = (ElvLeft) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvLeft = elvLeft3;
                            if (elvLeft != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvLeft != null) {
                        mergeFrom(elvLeft);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvLeftOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.ElvLeftOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = ElvLeft.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    ElvLeft.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
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

        public static ElvLeft getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvLeft> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvLeft> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvLeft getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class ElvLeftAck extends GeneratedMessageV3 implements ElvLeftAckOrBuilder {
        private static final ElvLeftAck DEFAULT_INSTANCE = new ElvLeftAck();
        private static final Parser<ElvLeftAck> PARSER = new AbstractParser<ElvLeftAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.ElvLeftAck.1
            @Override // com.google.protobuf.Parser
            public ElvLeftAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvLeftAck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private ElvLeftAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvLeftAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvLeftAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (readTag == 0 || !parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
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
            return Elevator.f5803xb58d425f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5804xa5ff99dd.ensureFieldAccessorsInitialized(ElvLeftAck.class, Builder.class);
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
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int serializedSize = this.unknownFields.getSerializedSize() + 0;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ElvLeftAck) {
                return this.unknownFields.equals(((ElvLeftAck) obj).unknownFields);
            }
            return super.equals(obj);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ElvLeftAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvLeftAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvLeftAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvLeftAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvLeftAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvLeftAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvLeftAck parseFrom(InputStream inputStream) throws IOException {
            return (ElvLeftAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvLeftAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvLeftAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvLeftAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvLeftAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvLeftAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvLeftAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvLeftAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvLeftAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvLeftAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvLeftAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvLeftAck elvLeftAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvLeftAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvLeftAckOrBuilder {
            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5803xb58d425f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5804xa5ff99dd.ensureFieldAccessorsInitialized(ElvLeftAck.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvLeftAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5803xb58d425f;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvLeftAck getDefaultInstanceForType() {
                return ElvLeftAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvLeftAck build() {
                ElvLeftAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvLeftAck buildPartial() {
                ElvLeftAck elvLeftAck = new ElvLeftAck(this);
                onBuilt();
                return elvLeftAck;
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
                if (message instanceof ElvLeftAck) {
                    return mergeFrom((ElvLeftAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvLeftAck elvLeftAck) {
                if (elvLeftAck == ElvLeftAck.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(elvLeftAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvLeftAck elvLeftAck = null;
                try {
                    try {
                        ElvLeftAck elvLeftAck2 = (ElvLeftAck) ElvLeftAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvLeftAck2 != null) {
                            mergeFrom(elvLeftAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvLeftAck elvLeftAck3 = (ElvLeftAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvLeftAck = elvLeftAck3;
                            if (elvLeftAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvLeftAck != null) {
                        mergeFrom(elvLeftAck);
                    }
                    throw th;
                }
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

        public static ElvLeftAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvLeftAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvLeftAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvLeftAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class QueryElv extends GeneratedMessageV3 implements QueryElvOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        private static final QueryElv DEFAULT_INSTANCE = new QueryElv();
        private static final Parser<QueryElv> PARSER = new AbstractParser<QueryElv>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.QueryElv.1
            @Override // com.google.protobuf.Parser
            public QueryElv parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new QueryElv(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private byte memoizedIsInitialized;

        private QueryElv(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private QueryElv() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private QueryElv(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.currFloor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5823x32cb0b6b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5824x449996e9.ensureFieldAccessorsInitialized(QueryElv.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof QueryElv)) {
                return super.equals(obj);
            }
            QueryElv queryElv = (QueryElv) obj;
            return (getCurrFloor().equals(queryElv.getCurrFloor())) && this.unknownFields.equals(queryElv.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static QueryElv parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static QueryElv parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static QueryElv parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static QueryElv parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static QueryElv parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static QueryElv parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static QueryElv parseFrom(InputStream inputStream) throws IOException {
            return (QueryElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static QueryElv parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static QueryElv parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (QueryElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static QueryElv parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static QueryElv parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (QueryElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static QueryElv parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(QueryElv queryElv) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(queryElv);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QueryElvOrBuilder {
            private Object currFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5823x32cb0b6b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5824x449996e9.ensureFieldAccessorsInitialized(QueryElv.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = QueryElv.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5823x32cb0b6b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public QueryElv getDefaultInstanceForType() {
                return QueryElv.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public QueryElv build() {
                QueryElv buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public QueryElv buildPartial() {
                QueryElv queryElv = new QueryElv(this);
                queryElv.currFloor_ = this.currFloor_;
                onBuilt();
                return queryElv;
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
                if (message instanceof QueryElv) {
                    return mergeFrom((QueryElv) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(QueryElv queryElv) {
                if (queryElv == QueryElv.getDefaultInstance()) {
                    return this;
                }
                if (!queryElv.getCurrFloor().isEmpty()) {
                    this.currFloor_ = queryElv.currFloor_;
                    onChanged();
                }
                mergeUnknownFields(queryElv.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                QueryElv queryElv = null;
                try {
                    try {
                        QueryElv queryElv2 = (QueryElv) QueryElv.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (queryElv2 != null) {
                            mergeFrom(queryElv2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        QueryElv queryElv3 = (QueryElv) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            queryElv = queryElv3;
                            if (queryElv != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (queryElv != null) {
                        mergeFrom(queryElv);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = QueryElv.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    QueryElv.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
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

        public static QueryElv getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<QueryElv> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<QueryElv> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public QueryElv getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class QueryElvResp extends GeneratedMessageV3 implements QueryElvRespOrBuilder {
        public static final int FLOORT_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object floort_;
        private byte memoizedIsInitialized;
        private ElvState state_;
        private static final QueryElvResp DEFAULT_INSTANCE = new QueryElvResp();
        private static final Parser<QueryElvResp> PARSER = new AbstractParser<QueryElvResp>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.QueryElvResp.1
            @Override // com.google.protobuf.Parser
            public QueryElvResp parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new QueryElvResp(codedInputStream, extensionRegistryLite);
            }
        };

        private QueryElvResp(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private QueryElvResp() {
            this.memoizedIsInitialized = (byte) -1;
            this.floort_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private QueryElvResp(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    ElvState.Builder builder = this.state_ != null ? this.state_.toBuilder() : null;
                                    this.state_ = (ElvState) codedInputStream.readMessage(ElvState.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.state_);
                                        this.state_ = builder.buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.floort_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5821x74856efb;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5822x9bf6a79.ensureFieldAccessorsInitialized(QueryElvResp.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
        public boolean hasState() {
            return this.state_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
        public ElvState getState() {
            ElvState elvState = this.state_;
            return elvState == null ? ElvState.getDefaultInstance() : elvState;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
        public ElvStateOrBuilder getStateOrBuilder() {
            return getState();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
        public String getFloort() {
            Object obj = this.floort_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.floort_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
        public ByteString getFloortBytes() {
            Object obj = this.floort_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.floort_ = copyFromUtf8;
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
            if (this.state_ != null) {
                codedOutputStream.writeMessage(1, getState());
            }
            if (!getFloortBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.floort_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.state_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getState()) : 0;
            if (!getFloortBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.floort_);
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
            if (!(obj instanceof QueryElvResp)) {
                return super.equals(obj);
            }
            QueryElvResp queryElvResp = (QueryElvResp) obj;
            boolean z = hasState() == queryElvResp.hasState();
            if (hasState()) {
                z = z && getState().equals(queryElvResp.getState());
            }
            return (z && getFloort().equals(queryElvResp.getFloort())) && this.unknownFields.equals(queryElvResp.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasState()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getState().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 2) * 53) + getFloort().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static QueryElvResp parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static QueryElvResp parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static QueryElvResp parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static QueryElvResp parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static QueryElvResp parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static QueryElvResp parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static QueryElvResp parseFrom(InputStream inputStream) throws IOException {
            return (QueryElvResp) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static QueryElvResp parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryElvResp) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static QueryElvResp parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (QueryElvResp) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static QueryElvResp parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryElvResp) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static QueryElvResp parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (QueryElvResp) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static QueryElvResp parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryElvResp) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(QueryElvResp queryElvResp) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(queryElvResp);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QueryElvRespOrBuilder {
            private Object floort_;
            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> stateBuilder_;
            private ElvState state_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5821x74856efb;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5822x9bf6a79.ensureFieldAccessorsInitialized(QueryElvResp.class, Builder.class);
            }

            private Builder() {
                this.state_ = null;
                this.floort_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = null;
                this.floort_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = QueryElvResp.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                this.floort_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5821x74856efb;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public QueryElvResp getDefaultInstanceForType() {
                return QueryElvResp.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public QueryElvResp build() {
                QueryElvResp buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public QueryElvResp buildPartial() {
                QueryElvResp queryElvResp = new QueryElvResp(this);
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    queryElvResp.state_ = this.state_;
                } else {
                    queryElvResp.state_ = singleFieldBuilderV3.build();
                }
                queryElvResp.floort_ = this.floort_;
                onBuilt();
                return queryElvResp;
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
                if (message instanceof QueryElvResp) {
                    return mergeFrom((QueryElvResp) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(QueryElvResp queryElvResp) {
                if (queryElvResp == QueryElvResp.getDefaultInstance()) {
                    return this;
                }
                if (queryElvResp.hasState()) {
                    mergeState(queryElvResp.getState());
                }
                if (!queryElvResp.getFloort().isEmpty()) {
                    this.floort_ = queryElvResp.floort_;
                    onChanged();
                }
                mergeUnknownFields(queryElvResp.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                QueryElvResp queryElvResp = null;
                try {
                    try {
                        QueryElvResp queryElvResp2 = (QueryElvResp) QueryElvResp.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (queryElvResp2 != null) {
                            mergeFrom(queryElvResp2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        QueryElvResp queryElvResp3 = (QueryElvResp) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            queryElvResp = queryElvResp3;
                            if (queryElvResp != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (queryElvResp != null) {
                        mergeFrom(queryElvResp);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
            public boolean hasState() {
                return (this.stateBuilder_ == null && this.state_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
            public ElvState getState() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState = this.state_;
                    return elvState == null ? ElvState.getDefaultInstance() : elvState;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvState);
                } else {
                    if (elvState == null) {
                        throw new NullPointerException();
                    }
                    this.state_ = elvState;
                    onChanged();
                }
                return this;
            }

            public Builder setState(ElvState.Builder builder) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.state_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState2 = this.state_;
                    if (elvState2 != null) {
                        this.state_ = ElvState.newBuilder(elvState2).mergeFrom(elvState).buildPartial();
                    } else {
                        this.state_ = elvState;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvState);
                }
                return this;
            }

            public Builder clearState() {
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                    onChanged();
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                return this;
            }

            public ElvState.Builder getStateBuilder() {
                onChanged();
                return getStateFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
            public ElvStateOrBuilder getStateOrBuilder() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvState elvState = this.state_;
                return elvState == null ? ElvState.getDefaultInstance() : elvState;
            }

            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> getStateFieldBuilder() {
                if (this.stateBuilder_ == null) {
                    this.stateBuilder_ = new SingleFieldBuilderV3<>(getState(), getParentForChildren(), isClean());
                    this.state_ = null;
                }
                return this.stateBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
            public String getFloort() {
                Object obj = this.floort_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.floort_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.QueryElvRespOrBuilder
            public ByteString getFloortBytes() {
                Object obj = this.floort_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.floort_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFloort(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.floort_ = str;
                onChanged();
                return this;
            }

            public Builder clearFloort() {
                this.floort_ = QueryElvResp.getDefaultInstance().getFloort();
                onChanged();
                return this;
            }

            public Builder setFloortBytes(ByteString byteString) {
                if (byteString != null) {
                    QueryElvResp.checkByteStringIsUtf8(byteString);
                    this.floort_ = byteString;
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

        public static QueryElvResp getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<QueryElvResp> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<QueryElvResp> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public QueryElvResp getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class PrepareRide extends GeneratedMessageV3 implements PrepareRideOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        private static final PrepareRide DEFAULT_INSTANCE = new PrepareRide();
        private static final Parser<PrepareRide> PARSER = new AbstractParser<PrepareRide>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.PrepareRide.1
            @Override // com.google.protobuf.Parser
            public PrepareRide parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PrepareRide(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private byte memoizedIsInitialized;

        private PrepareRide(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PrepareRide() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PrepareRide(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.currFloor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5819x7e303b0b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5820x96252689.ensureFieldAccessorsInitialized(PrepareRide.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PrepareRide)) {
                return super.equals(obj);
            }
            PrepareRide prepareRide = (PrepareRide) obj;
            return (getCurrFloor().equals(prepareRide.getCurrFloor())) && this.unknownFields.equals(prepareRide.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static PrepareRide parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PrepareRide parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PrepareRide parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PrepareRide parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PrepareRide parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PrepareRide parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PrepareRide parseFrom(InputStream inputStream) throws IOException {
            return (PrepareRide) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PrepareRide parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrepareRide) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PrepareRide parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PrepareRide) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PrepareRide parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrepareRide) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PrepareRide parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PrepareRide) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PrepareRide parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrepareRide) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PrepareRide prepareRide) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prepareRide);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PrepareRideOrBuilder {
            private Object currFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5819x7e303b0b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5820x96252689.ensureFieldAccessorsInitialized(PrepareRide.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PrepareRide.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5819x7e303b0b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PrepareRide getDefaultInstanceForType() {
                return PrepareRide.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PrepareRide build() {
                PrepareRide buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PrepareRide buildPartial() {
                PrepareRide prepareRide = new PrepareRide(this);
                prepareRide.currFloor_ = this.currFloor_;
                onBuilt();
                return prepareRide;
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
                if (message instanceof PrepareRide) {
                    return mergeFrom((PrepareRide) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PrepareRide prepareRide) {
                if (prepareRide == PrepareRide.getDefaultInstance()) {
                    return this;
                }
                if (!prepareRide.getCurrFloor().isEmpty()) {
                    this.currFloor_ = prepareRide.currFloor_;
                    onChanged();
                }
                mergeUnknownFields(prepareRide.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PrepareRide prepareRide = null;
                try {
                    try {
                        PrepareRide prepareRide2 = (PrepareRide) PrepareRide.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (prepareRide2 != null) {
                            mergeFrom(prepareRide2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        PrepareRide prepareRide3 = (PrepareRide) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            prepareRide = prepareRide3;
                            if (prepareRide != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (prepareRide != null) {
                        mergeFrom(prepareRide);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = PrepareRide.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    PrepareRide.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
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

        public static PrepareRide getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PrepareRide> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PrepareRide> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PrepareRide getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class PrepareRideAck extends GeneratedMessageV3 implements PrepareRideAckOrBuilder {
        public static final int FLOOR_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object floor_;
        private byte memoizedIsInitialized;
        private ElvState state_;
        private static final PrepareRideAck DEFAULT_INSTANCE = new PrepareRideAck();
        private static final Parser<PrepareRideAck> PARSER = new AbstractParser<PrepareRideAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAck.1
            @Override // com.google.protobuf.Parser
            public PrepareRideAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PrepareRideAck(codedInputStream, extensionRegistryLite);
            }
        };

        private PrepareRideAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private PrepareRideAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.floor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PrepareRideAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    ElvState.Builder builder = this.state_ != null ? this.state_.toBuilder() : null;
                                    this.state_ = (ElvState) codedInputStream.readMessage(ElvState.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.state_);
                                        this.state_ = builder.buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.floor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5817x9c9f4de8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5818xa4760c66.ensureFieldAccessorsInitialized(PrepareRideAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
        public boolean hasState() {
            return this.state_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
        public ElvState getState() {
            ElvState elvState = this.state_;
            return elvState == null ? ElvState.getDefaultInstance() : elvState;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
        public ElvStateOrBuilder getStateOrBuilder() {
            return getState();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
        public String getFloor() {
            Object obj = this.floor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.floor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
        public ByteString getFloorBytes() {
            Object obj = this.floor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.floor_ = copyFromUtf8;
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
            if (this.state_ != null) {
                codedOutputStream.writeMessage(1, getState());
            }
            if (!getFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.floor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.state_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getState()) : 0;
            if (!getFloorBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.floor_);
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
            if (!(obj instanceof PrepareRideAck)) {
                return super.equals(obj);
            }
            PrepareRideAck prepareRideAck = (PrepareRideAck) obj;
            boolean z = hasState() == prepareRideAck.hasState();
            if (hasState()) {
                z = z && getState().equals(prepareRideAck.getState());
            }
            return (z && getFloor().equals(prepareRideAck.getFloor())) && this.unknownFields.equals(prepareRideAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasState()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getState().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 2) * 53) + getFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static PrepareRideAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static PrepareRideAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PrepareRideAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static PrepareRideAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PrepareRideAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static PrepareRideAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PrepareRideAck parseFrom(InputStream inputStream) throws IOException {
            return (PrepareRideAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PrepareRideAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrepareRideAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PrepareRideAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PrepareRideAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PrepareRideAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrepareRideAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PrepareRideAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PrepareRideAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PrepareRideAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PrepareRideAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PrepareRideAck prepareRideAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prepareRideAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PrepareRideAckOrBuilder {
            private Object floor_;
            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> stateBuilder_;
            private ElvState state_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5817x9c9f4de8;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5818xa4760c66.ensureFieldAccessorsInitialized(PrepareRideAck.class, Builder.class);
            }

            private Builder() {
                this.state_ = null;
                this.floor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = null;
                this.floor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PrepareRideAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                this.floor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5817x9c9f4de8;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PrepareRideAck getDefaultInstanceForType() {
                return PrepareRideAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PrepareRideAck build() {
                PrepareRideAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PrepareRideAck buildPartial() {
                PrepareRideAck prepareRideAck = new PrepareRideAck(this);
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    prepareRideAck.state_ = this.state_;
                } else {
                    prepareRideAck.state_ = singleFieldBuilderV3.build();
                }
                prepareRideAck.floor_ = this.floor_;
                onBuilt();
                return prepareRideAck;
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
                if (message instanceof PrepareRideAck) {
                    return mergeFrom((PrepareRideAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PrepareRideAck prepareRideAck) {
                if (prepareRideAck == PrepareRideAck.getDefaultInstance()) {
                    return this;
                }
                if (prepareRideAck.hasState()) {
                    mergeState(prepareRideAck.getState());
                }
                if (!prepareRideAck.getFloor().isEmpty()) {
                    this.floor_ = prepareRideAck.floor_;
                    onChanged();
                }
                mergeUnknownFields(prepareRideAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PrepareRideAck prepareRideAck = null;
                try {
                    try {
                        PrepareRideAck prepareRideAck2 = (PrepareRideAck) PrepareRideAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (prepareRideAck2 != null) {
                            mergeFrom(prepareRideAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        PrepareRideAck prepareRideAck3 = (PrepareRideAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            prepareRideAck = prepareRideAck3;
                            if (prepareRideAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (prepareRideAck != null) {
                        mergeFrom(prepareRideAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
            public boolean hasState() {
                return (this.stateBuilder_ == null && this.state_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
            public ElvState getState() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState = this.state_;
                    return elvState == null ? ElvState.getDefaultInstance() : elvState;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvState);
                } else {
                    if (elvState == null) {
                        throw new NullPointerException();
                    }
                    this.state_ = elvState;
                    onChanged();
                }
                return this;
            }

            public Builder setState(ElvState.Builder builder) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.state_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState2 = this.state_;
                    if (elvState2 != null) {
                        this.state_ = ElvState.newBuilder(elvState2).mergeFrom(elvState).buildPartial();
                    } else {
                        this.state_ = elvState;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvState);
                }
                return this;
            }

            public Builder clearState() {
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                    onChanged();
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                return this;
            }

            public ElvState.Builder getStateBuilder() {
                onChanged();
                return getStateFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
            public ElvStateOrBuilder getStateOrBuilder() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvState elvState = this.state_;
                return elvState == null ? ElvState.getDefaultInstance() : elvState;
            }

            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> getStateFieldBuilder() {
                if (this.stateBuilder_ == null) {
                    this.stateBuilder_ = new SingleFieldBuilderV3<>(getState(), getParentForChildren(), isClean());
                    this.state_ = null;
                }
                return this.stateBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
            public String getFloor() {
                Object obj = this.floor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.floor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.PrepareRideAckOrBuilder
            public ByteString getFloorBytes() {
                Object obj = this.floor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.floor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.floor_ = str;
                onChanged();
                return this;
            }

            public Builder clearFloor() {
                this.floor_ = PrepareRideAck.getDefaultInstance().getFloor();
                onChanged();
                return this;
            }

            public Builder setFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    PrepareRideAck.checkByteStringIsUtf8(byteString);
                    this.floor_ = byteString;
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

        public static PrepareRideAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PrepareRideAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PrepareRideAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PrepareRideAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class CancelElv extends GeneratedMessageV3 implements CancelElvOrBuilder {
        public static final int CURR_FLOOR_FIELD_NUMBER = 1;
        private static final CancelElv DEFAULT_INSTANCE = new CancelElv();
        private static final Parser<CancelElv> PARSER = new AbstractParser<CancelElv>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.CancelElv.1
            @Override // com.google.protobuf.Parser
            public CancelElv parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CancelElv(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object currFloor_;
        private byte memoizedIsInitialized;

        private CancelElv(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CancelElv() {
            this.memoizedIsInitialized = (byte) -1;
            this.currFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CancelElv(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag != 10) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.currFloor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5797x1cbd4315;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5798x45d68493.ensureFieldAccessorsInitialized(CancelElv.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvOrBuilder
        public String getCurrFloor() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvOrBuilder
        public ByteString getCurrFloorBytes() {
            Object obj = this.currFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currFloor_ = copyFromUtf8;
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
            if (!getCurrFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.currFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getCurrFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.currFloor_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CancelElv)) {
                return super.equals(obj);
            }
            CancelElv cancelElv = (CancelElv) obj;
            return (getCurrFloor().equals(cancelElv.getCurrFloor())) && this.unknownFields.equals(cancelElv.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCurrFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static CancelElv parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CancelElv parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CancelElv parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CancelElv parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CancelElv parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CancelElv parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CancelElv parseFrom(InputStream inputStream) throws IOException {
            return (CancelElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CancelElv parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelElv) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelElv parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CancelElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CancelElv parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelElv) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelElv parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CancelElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CancelElv parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelElv) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CancelElv cancelElv) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(cancelElv);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CancelElvOrBuilder {
            private Object currFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5797x1cbd4315;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5798x45d68493.ensureFieldAccessorsInitialized(CancelElv.class, Builder.class);
            }

            private Builder() {
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.currFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CancelElv.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5797x1cbd4315;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CancelElv getDefaultInstanceForType() {
                return CancelElv.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelElv build() {
                CancelElv buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelElv buildPartial() {
                CancelElv cancelElv = new CancelElv(this);
                cancelElv.currFloor_ = this.currFloor_;
                onBuilt();
                return cancelElv;
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
                if (message instanceof CancelElv) {
                    return mergeFrom((CancelElv) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CancelElv cancelElv) {
                if (cancelElv == CancelElv.getDefaultInstance()) {
                    return this;
                }
                if (!cancelElv.getCurrFloor().isEmpty()) {
                    this.currFloor_ = cancelElv.currFloor_;
                    onChanged();
                }
                mergeUnknownFields(cancelElv.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CancelElv cancelElv = null;
                try {
                    try {
                        CancelElv cancelElv2 = (CancelElv) CancelElv.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (cancelElv2 != null) {
                            mergeFrom(cancelElv2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CancelElv cancelElv3 = (CancelElv) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            cancelElv = cancelElv3;
                            if (cancelElv != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cancelElv != null) {
                        mergeFrom(cancelElv);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvOrBuilder
            public String getCurrFloor() {
                Object obj = this.currFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvOrBuilder
            public ByteString getCurrFloorBytes() {
                Object obj = this.currFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.currFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrFloor() {
                this.currFloor_ = CancelElv.getDefaultInstance().getCurrFloor();
                onChanged();
                return this;
            }

            public Builder setCurrFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    CancelElv.checkByteStringIsUtf8(byteString);
                    this.currFloor_ = byteString;
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

        public static CancelElv getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CancelElv> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CancelElv> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CancelElv getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static final class CancelElvAck extends GeneratedMessageV3 implements CancelElvAckOrBuilder {
        public static final int FLOOR_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object floor_;
        private byte memoizedIsInitialized;
        private ElvState state_;
        private static final CancelElvAck DEFAULT_INSTANCE = new CancelElvAck();
        private static final Parser<CancelElvAck> PARSER = new AbstractParser<CancelElvAck>() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAck.1
            @Override // com.google.protobuf.Parser
            public CancelElvAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CancelElvAck(codedInputStream, extensionRegistryLite);
            }
        };

        private CancelElvAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CancelElvAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.floor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CancelElvAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    ElvState.Builder builder = this.state_ != null ? this.state_.toBuilder() : null;
                                    this.state_ = (ElvState) codedInputStream.readMessage(ElvState.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.state_);
                                        this.state_ = builder.buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.floor_ = codedInputStream.readStringRequireUtf8();
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
            return Elevator.f5795x5a99d19e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Elevator.f5796x35e47a1c.ensureFieldAccessorsInitialized(CancelElvAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
        public boolean hasState() {
            return this.state_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
        public ElvState getState() {
            ElvState elvState = this.state_;
            return elvState == null ? ElvState.getDefaultInstance() : elvState;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
        public ElvStateOrBuilder getStateOrBuilder() {
            return getState();
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
        public String getFloor() {
            Object obj = this.floor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.floor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
        public ByteString getFloorBytes() {
            Object obj = this.floor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.floor_ = copyFromUtf8;
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
            if (this.state_ != null) {
                codedOutputStream.writeMessage(1, getState());
            }
            if (!getFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.floor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.state_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getState()) : 0;
            if (!getFloorBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.floor_);
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
            if (!(obj instanceof CancelElvAck)) {
                return super.equals(obj);
            }
            CancelElvAck cancelElvAck = (CancelElvAck) obj;
            boolean z = hasState() == cancelElvAck.hasState();
            if (hasState()) {
                z = z && getState().equals(cancelElvAck.getState());
            }
            return (z && getFloor().equals(cancelElvAck.getFloor())) && this.unknownFields.equals(cancelElvAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasState()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getState().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 2) * 53) + getFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CancelElvAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CancelElvAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CancelElvAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CancelElvAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CancelElvAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CancelElvAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CancelElvAck parseFrom(InputStream inputStream) throws IOException {
            return (CancelElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CancelElvAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelElvAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelElvAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CancelElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CancelElvAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelElvAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelElvAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CancelElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CancelElvAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelElvAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CancelElvAck cancelElvAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(cancelElvAck);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CancelElvAckOrBuilder {
            private Object floor_;
            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> stateBuilder_;
            private ElvState state_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Elevator.f5795x5a99d19e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return Elevator.f5796x35e47a1c.ensureFieldAccessorsInitialized(CancelElvAck.class, Builder.class);
            }

            private Builder() {
                this.state_ = null;
                this.floor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = null;
                this.floor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CancelElvAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                this.floor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return Elevator.f5795x5a99d19e;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CancelElvAck getDefaultInstanceForType() {
                return CancelElvAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelElvAck build() {
                CancelElvAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelElvAck buildPartial() {
                CancelElvAck cancelElvAck = new CancelElvAck(this);
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    cancelElvAck.state_ = this.state_;
                } else {
                    cancelElvAck.state_ = singleFieldBuilderV3.build();
                }
                cancelElvAck.floor_ = this.floor_;
                onBuilt();
                return cancelElvAck;
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
                if (message instanceof CancelElvAck) {
                    return mergeFrom((CancelElvAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CancelElvAck cancelElvAck) {
                if (cancelElvAck == CancelElvAck.getDefaultInstance()) {
                    return this;
                }
                if (cancelElvAck.hasState()) {
                    mergeState(cancelElvAck.getState());
                }
                if (!cancelElvAck.getFloor().isEmpty()) {
                    this.floor_ = cancelElvAck.floor_;
                    onChanged();
                }
                mergeUnknownFields(cancelElvAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CancelElvAck cancelElvAck = null;
                try {
                    try {
                        CancelElvAck cancelElvAck2 = (CancelElvAck) CancelElvAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (cancelElvAck2 != null) {
                            mergeFrom(cancelElvAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CancelElvAck cancelElvAck3 = (CancelElvAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            cancelElvAck = cancelElvAck3;
                            if (cancelElvAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cancelElvAck != null) {
                        mergeFrom(cancelElvAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
            public boolean hasState() {
                return (this.stateBuilder_ == null && this.state_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
            public ElvState getState() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState = this.state_;
                    return elvState == null ? ElvState.getDefaultInstance() : elvState;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvState);
                } else {
                    if (elvState == null) {
                        throw new NullPointerException();
                    }
                    this.state_ = elvState;
                    onChanged();
                }
                return this;
            }

            public Builder setState(ElvState.Builder builder) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.state_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeState(ElvState elvState) {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvState elvState2 = this.state_;
                    if (elvState2 != null) {
                        this.state_ = ElvState.newBuilder(elvState2).mergeFrom(elvState).buildPartial();
                    } else {
                        this.state_ = elvState;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvState);
                }
                return this;
            }

            public Builder clearState() {
                if (this.stateBuilder_ == null) {
                    this.state_ = null;
                    onChanged();
                } else {
                    this.state_ = null;
                    this.stateBuilder_ = null;
                }
                return this;
            }

            public ElvState.Builder getStateBuilder() {
                onChanged();
                return getStateFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
            public ElvStateOrBuilder getStateOrBuilder() {
                SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> singleFieldBuilderV3 = this.stateBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvState elvState = this.state_;
                return elvState == null ? ElvState.getDefaultInstance() : elvState;
            }

            private SingleFieldBuilderV3<ElvState, ElvState.Builder, ElvStateOrBuilder> getStateFieldBuilder() {
                if (this.stateBuilder_ == null) {
                    this.stateBuilder_ = new SingleFieldBuilderV3<>(getState(), getParentForChildren(), isClean());
                    this.state_ = null;
                }
                return this.stateBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
            public String getFloor() {
                Object obj = this.floor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.floor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.Elevator.CancelElvAckOrBuilder
            public ByteString getFloorBytes() {
                Object obj = this.floor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.floor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.floor_ = str;
                onChanged();
                return this;
            }

            public Builder clearFloor() {
                this.floor_ = CancelElvAck.getDefaultInstance().getFloor();
                onChanged();
                return this;
            }

            public Builder setFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    CancelElvAck.checkByteStringIsUtf8(byteString);
                    this.floor_ = byteString;
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

        public static CancelElvAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CancelElvAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CancelElvAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CancelElvAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u000eElevator.proto\u0012\u001dcom.pudutech.mirsdk.elv.proto\"\u0095\u000b\n\u0003Elv\u0012\u000e\n\u0006elv_id\u0018\u0001 \u0001(\t\u0012\u0010\n\brobot_id\u0018\u0002 \u0001(\t\u0012\u000b\n\u0003seq\u0018\u0003 \u0001(\u0004\u0012\n\n\u0002ts\u0018\u0004 \u0001(\u0004\u0012<\n\bmsg_type\u0018\u0005 \u0001(\u000e2*.com.pudutech.mirsdk.elv.proto.Elv.MsgType\u00128\n\bcall_elv\u0018\u0006 \u0001(\u000b2&.com.pudutech.mirsdk.elv.proto.CallElv\u0012?\n\fcall_elv_ack\u0018\u0007 \u0001(\u000b2).com.pudutech.mirsdk.elv.proto.CallElvAck\u0012:\n\tenter_elv\u0018\b \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.EnterElv\u0012A\n\renter_elv_ack\u0018\t \u0001(\u000b2*.com.pudutech.mirsdk.elv.proto.EnterElvAck\u0012>\n\u000belv_entered\u0018\n \u0001(\u000b2).com.pudutech.mirsdk.elv.proto.ElvEntered\u0012E\n\u000felv_entered_ack\u0018\u000b \u0001(\u000b2,.com.pudutech.mirsdk.elv.proto.ElvEnteredAck\u0012:\n\tleave_elv\u0018\f \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.LeaveElv\u0012A\n\rleave_elv_ack\u0018\r \u0001(\u000b2*.com.pudutech.mirsdk.elv.proto.LeaveElvAck\u00128\n\belv_left\u0018\u000e \u0001(\u000b2&.com.pudutech.mirsdk.elv.proto.ElvLeft\u0012?\n\felv_left_ack\u0018\u000f \u0001(\u000b2).com.pudutech.mirsdk.elv.proto.ElvLeftAck\u0012:\n\tquery_elv\u0018\u0010 \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.QueryElv\u0012C\n\u000equery_elv_resp\u0018\u0011 \u0001(\u000b2+.com.pudutech.mirsdk.elv.proto.QueryElvResp\u0012@\n\fprepare_ride\u0018\u0012 \u0001(\u000b2*.com.pudutech.mirsdk.elv.proto.PrepareRide\u0012G\n\u0010prepare_ride_ack\u0018\u0013 \u0001(\u000b2-.com.pudutech.mirsdk.elv.proto.PrepareRideAck\u0012<\n\ncancel_elv\u0018\u0014 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.CancelElv\u0012C\n\u000ecancel_elv_ack\u0018\u0015 \u0001(\u000b2+.com.pudutech.mirsdk.elv.proto.CancelElvAck\"\u008a\u0002\n\u0007MsgType\u0012\u000b\n\u0007CallElv\u0010\u0000\u0012\u000e\n\nCallElvAck\u0010\u0001\u0012\f\n\bEnterElv\u0010\u0002\u0012\u000f\n\u000bEnterElvAck\u0010\u0003\u0012\u000e\n\nElvEntered\u0010\u0004\u0012\u0011\n\rElvEnteredAck\u0010\u0005\u0012\f\n\bLeaveElv\u0010\u0006\u0012\u000f\n\u000bLeaveElvAck\u0010\u0007\u0012\u000b\n\u0007ElvLeft\u0010\b\u0012\u000e\n\nElvLeftAck\u0010\t\u0012\f\n\bQueryElv\u0010\n\u0012\u0010\n\fQueryElvResp\u0010\u000b\u0012\u000f\n\u000bPrepareRide\u0010\f\u0012\u0012\n\u000ePrepareRideAck\u0010\r\u0012\r\n\tCancelElv\u0010\u000e\u0012\u0010\n\fCancelElvAck\u0010\u000f\"r\n\bElvState\u0012<\n\u0005state\u0018\u0001 \u0001(\u000e2-.com.pudutech.mirsdk.elv.proto.ElvState.State\"(\n\u0005State\u0012\b\n\u0004Free\u0010\u0000\u0012\t\n\u0005RunUp\u0010\u0001\u0012\n\n\u0006GoDown\u0010\u0002\"1\n\u0007CallElv\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\u0012\u0012\n\ndest_floor\u0018\u0002 \u0001(\t\"S\n\nCallElvAck\u00126\n\u0005state\u0018\u0001 \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.ElvState\u0012\r\n\u0005floor\u0018\u0002 \u0001(\t\"0\n\bEnterElv\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\u0012\u0010\n\beff_time\u0018\u0002 \u0001(\u0005\"\u001f\n\u000bEnterElvAck\u0012\u0010\n\bdistance\u0018\u0001 \u0001(\u0001\" \n\nElvEntered\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\"\u000f\n\rElvEnteredAck\"0\n\bLeaveElv\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\u0012\u0010\n\beff_time\u0018\u0002 \u0001(\u0005\"\r\n\u000bLeaveElvAck\"\u001d\n\u0007ElvLeft\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\"\f\n\nElvLeftAck\"\u001e\n\bQueryElv\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\"V\n\fQueryElvResp\u00126\n\u0005state\u0018\u0001 \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.ElvState\u0012\u000e\n\u0006floort\u0018\u0002 \u0001(\t\"!\n\u000bPrepareRide\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\"W\n\u000ePrepareRideAck\u00126\n\u0005state\u0018\u0001 \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.ElvState\u0012\r\n\u0005floor\u0018\u0002 \u0001(\t\"\u001f\n\tCancelElv\u0012\u0012\n\ncurr_floor\u0018\u0001 \u0001(\t\"U\n\fCancelElvAck\u00126\n\u0005state\u0018\u0001 \u0001(\u000b2'.com.pudutech.mirsdk.elv.proto.ElvState\u0012\r\n\u0005floor\u0018\u0002 \u0001(\tb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.pudutech.mirsdk.elv.proto.Elevator.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Elevator.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_com_pudutech_mirsdk_elv_proto_Elv_descriptor = getDescriptor().getMessageTypes().get(0);
        f5808xe078e79 = new GeneratedMessageV3.FieldAccessorTable(internal_static_com_pudutech_mirsdk_elv_proto_Elv_descriptor, new String[]{"ElvId", "RobotId", "Seq", "Ts", "MsgType", "CallElv", "CallElvAck", "EnterElv", "EnterElvAck", "ElvEntered", "ElvEnteredAck", "LeaveElv", "LeaveElvAck", "ElvLeft", "ElvLeftAck", "QueryElv", "QueryElvResp", "PrepareRide", "PrepareRideAck", "CancelElv", "CancelElvAck"});
        f5806x94909630 = getDescriptor().getMessageTypes().get(1);
        f5807xdde8cae = new GeneratedMessageV3.FieldAccessorTable(f5806x94909630, new String[]{"State"});
        internal_static_com_pudutech_mirsdk_elv_proto_CallElv_descriptor = getDescriptor().getMessageTypes().get(2);
        f5794x39601c77 = new GeneratedMessageV3.FieldAccessorTable(internal_static_com_pudutech_mirsdk_elv_proto_CallElv_descriptor, new String[]{"CurrFloor", "DestFloor"});
        f5792x19e1db3a = getDescriptor().getMessageTypes().get(3);
        f5793xeec727b8 = new GeneratedMessageV3.FieldAccessorTable(f5792x19e1db3a, new String[]{"State", "Floor"});
        f5811xff5d1d7b = getDescriptor().getMessageTypes().get(4);
        f5812x734098f9 = new GeneratedMessageV3.FieldAccessorTable(f5811xff5d1d7b, new String[]{"CurrFloor", "EffTime"});
        f5809xe2e21578 = getDescriptor().getMessageTypes().get(5);
        f5810x218043f6 = new GeneratedMessageV3.FieldAccessorTable(f5809xe2e21578, new String[]{"Distance"});
        f5801x2045e8ca = getDescriptor().getMessageTypes().get(6);
        f5802x314ca548 = new GeneratedMessageV3.FieldAccessorTable(f5801x2045e8ca, new String[]{"CurrFloor"});
        f5799x956553c9 = getDescriptor().getMessageTypes().get(7);
        f5800x1d8ca147 = new GeneratedMessageV3.FieldAccessorTable(f5799x956553c9, new String[0]);
        f5815x23d4b31a = getDescriptor().getMessageTypes().get(8);
        f5816x1bd41f98 = new GeneratedMessageV3.FieldAccessorTable(f5815x23d4b31a, new String[]{"CurrFloor", "EffTime"});
        f5813x9b0ea779 = getDescriptor().getMessageTypes().get(9);
        f5814x893f44f7 = new GeneratedMessageV3.FieldAccessorTable(f5813x9b0ea779, new String[0]);
        internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_descriptor = getDescriptor().getMessageTypes().get(10);
        f5805x32e06472 = new GeneratedMessageV3.FieldAccessorTable(internal_static_com_pudutech_mirsdk_elv_proto_ElvLeft_descriptor, new String[]{"CurrFloor"});
        f5803xb58d425f = getDescriptor().getMessageTypes().get(11);
        f5804xa5ff99dd = new GeneratedMessageV3.FieldAccessorTable(f5803xb58d425f, new String[0]);
        f5823x32cb0b6b = getDescriptor().getMessageTypes().get(12);
        f5824x449996e9 = new GeneratedMessageV3.FieldAccessorTable(f5823x32cb0b6b, new String[]{"CurrFloor"});
        f5821x74856efb = getDescriptor().getMessageTypes().get(13);
        f5822x9bf6a79 = new GeneratedMessageV3.FieldAccessorTable(f5821x74856efb, new String[]{"State", "Floort"});
        f5819x7e303b0b = getDescriptor().getMessageTypes().get(14);
        f5820x96252689 = new GeneratedMessageV3.FieldAccessorTable(f5819x7e303b0b, new String[]{"CurrFloor"});
        f5817x9c9f4de8 = getDescriptor().getMessageTypes().get(15);
        f5818xa4760c66 = new GeneratedMessageV3.FieldAccessorTable(f5817x9c9f4de8, new String[]{"State", "Floor"});
        f5797x1cbd4315 = getDescriptor().getMessageTypes().get(16);
        f5798x45d68493 = new GeneratedMessageV3.FieldAccessorTable(f5797x1cbd4315, new String[]{"CurrFloor"});
        f5795x5a99d19e = getDescriptor().getMessageTypes().get(17);
        f5796x35e47a1c = new GeneratedMessageV3.FieldAccessorTable(f5795x5a99d19e, new String[]{"State", "Floor"});
    }
}
