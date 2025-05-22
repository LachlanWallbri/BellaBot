package com.pudutech.mirsdk.elv.proto;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
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
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;

/* loaded from: classes5.dex */
public final class ElevatorProtocol {
    private static Descriptors.FileDescriptor descriptor;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_AckResult_descriptor */
    private static final Descriptors.Descriptor f5825xff036044;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_AckResult_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5826x7b4002c2;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CallTargetFloor_descriptor */
    private static final Descriptors.Descriptor f5827xe7c5712d;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CallTargetFloor_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5828xbb3b1aab;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelFloorReqAck_descriptor */
    private static final Descriptors.Descriptor f5829x36dd9e8d;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelFloorReqAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5830x137fe80b;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelFloorReq_descriptor */
    private static final Descriptors.Descriptor f5831xf51b3486;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_CancelFloorReq_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5832x4fe87504;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ConnectReqAck_descriptor */
    private static final Descriptors.Descriptor f5833x7ebc5c55;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ConnectReqAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5834xf8c85dd3;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ConnectReq_descriptor */
    private static final Descriptors.Descriptor f5835x67828bbe;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ConnectReq_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5836x1c01143c;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_DisconnectReqAck_descriptor */
    private static final Descriptors.Descriptor f5837xbddbaf6b;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_DisconnectReqAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5838x96c63ae9;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_DisconnectReq_descriptor */
    private static final Descriptors.Descriptor f5839x6ccc0068;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_DisconnectReq_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5840x40083ee6;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvArrivedAck_descriptor */
    private static final Descriptors.Descriptor f5841xbb23143f;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvArrivedAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5842xe1158bbd;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvArrived_descriptor */
    private static final Descriptors.Descriptor f5843x7c66ec14;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvArrived_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5844x8582be92;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvMessage_descriptor */
    private static final Descriptors.Descriptor f5845x5c476c3a;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_ElvMessage_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5846xbf0bb8b8;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_GoFloorReqAck_descriptor */
    private static final Descriptors.Descriptor f5847x77d6b47b;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_GoFloorReqAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5848xc0332ff9;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_GoFloorReq_descriptor */
    private static final Descriptors.Descriptor f5849xf3e59558;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_GoFloorReq_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5850x2e75e3d6;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_HeartBeatAck_descriptor */
    private static final Descriptors.Descriptor f5851x53acd6a5;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_HeartBeatAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5852xd0018823;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_HeartBeat_descriptor */
    private static final Descriptors.Descriptor f5853xfe5cb36e;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_HeartBeat_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5854x94d08bec;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateReqAck_descriptor */
    private static final Descriptors.Descriptor f5855x7f0de;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateReqAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5856xe7df595c;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateReq_descriptor */
    private static final Descriptors.Descriptor f5857xb2d56bd5;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateReq_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5858x2899ed53;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateStateReqAck_descriptor */
    private static final Descriptors.Descriptor f5859xbad24db7;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateStateReqAck_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5860x7e8fcd35;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateStateReq_descriptor */
    private static final Descriptors.Descriptor f5861x7dc6e59c;

    /* renamed from: internal_static_com_pudutech_mirsdk_elv_proto_UpdateStateReq_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5862xc34b01a;

    /* loaded from: classes5.dex */
    public interface AckResultOrBuilder extends MessageOrBuilder {
        AckResult.AckCode getCode();

        int getCodeValue();

        String getCurRobotId();

        ByteString getCurRobotIdBytes();
    }

    /* loaded from: classes5.dex */
    public interface CallTargetFloorOrBuilder extends MessageOrBuilder {
        String getTargetFloor();

        ByteString getTargetFloorBytes();
    }

    /* loaded from: classes5.dex */
    public interface CancelFloorReqAckOrBuilder extends MessageOrBuilder {
        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface CancelFloorReqOrBuilder extends MessageOrBuilder {
    }

    /* loaded from: classes5.dex */
    public interface ConnectReqAckOrBuilder extends MessageOrBuilder {
        String getElvVer();

        ByteString getElvVerBytes();

        String getHardwareType();

        ByteString getHardwareTypeBytes();

        int getProtocolVer();

        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        int getWeight();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface ConnectReqOrBuilder extends MessageOrBuilder {
        int getWeight();
    }

    /* loaded from: classes5.dex */
    public interface DisconnectReqAckOrBuilder extends MessageOrBuilder {
        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface DisconnectReqOrBuilder extends MessageOrBuilder {
    }

    /* loaded from: classes5.dex */
    public interface ElvArrivedAckOrBuilder extends MessageOrBuilder {
        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface ElvArrivedOrBuilder extends MessageOrBuilder {
        String getArrivedFloor();

        ByteString getArrivedFloorBytes();

        int getEffTime();
    }

    /* loaded from: classes5.dex */
    public interface ElvMessageOrBuilder extends MessageOrBuilder {
        CallTargetFloor getCallTargetFloor();

        CallTargetFloorOrBuilder getCallTargetFloorOrBuilder();

        CancelFloorReq getCancelFloorReq();

        CancelFloorReqAck getCancelFloorReqAck();

        CancelFloorReqAckOrBuilder getCancelFloorReqAckOrBuilder();

        CancelFloorReqOrBuilder getCancelFloorReqOrBuilder();

        ConnectReq getConnectReq();

        ConnectReqAck getConnectReqAck();

        ConnectReqAckOrBuilder getConnectReqAckOrBuilder();

        ConnectReqOrBuilder getConnectReqOrBuilder();

        DisconnectReq getDisconnectReq();

        DisconnectReqAck getDisconnectReqAck();

        DisconnectReqAckOrBuilder getDisconnectReqAckOrBuilder();

        DisconnectReqOrBuilder getDisconnectReqOrBuilder();

        ElvArrived getElvArrived();

        ElvArrivedAck getElvArrivedAck();

        ElvArrivedAckOrBuilder getElvArrivedAckOrBuilder();

        ElvArrivedOrBuilder getElvArrivedOrBuilder();

        String getElvId();

        ByteString getElvIdBytes();

        GoFloorReq getGoFloorReq();

        GoFloorReqAck getGoFloorReqAck();

        GoFloorReqAckOrBuilder getGoFloorReqAckOrBuilder();

        GoFloorReqOrBuilder getGoFloorReqOrBuilder();

        HeartBeat getHeartBeat();

        HeartBeatAck getHeartBeatAck();

        HeartBeatAckOrBuilder getHeartBeatAckOrBuilder();

        HeartBeatOrBuilder getHeartBeatOrBuilder();

        ElvMessage.MsgType getMsgType();

        int getMsgTypeValue();

        String getRobotId();

        ByteString getRobotIdBytes();

        long getSeq();

        long getTs();

        UpdateReq getUpdateReq();

        UpdateReqAck getUpdateReqAck();

        UpdateReqAckOrBuilder getUpdateReqAckOrBuilder();

        UpdateReqOrBuilder getUpdateReqOrBuilder();

        UpdateStateReq getUpdateStateReq();

        UpdateStateReqAck getUpdateStateReqAck();

        UpdateStateReqAckOrBuilder getUpdateStateReqAckOrBuilder();

        UpdateStateReqOrBuilder getUpdateStateReqOrBuilder();

        boolean hasCallTargetFloor();

        boolean hasCancelFloorReq();

        boolean hasCancelFloorReqAck();

        boolean hasConnectReq();

        boolean hasConnectReqAck();

        boolean hasDisconnectReq();

        boolean hasDisconnectReqAck();

        boolean hasElvArrived();

        boolean hasElvArrivedAck();

        boolean hasGoFloorReq();

        boolean hasGoFloorReqAck();

        boolean hasHeartBeat();

        boolean hasHeartBeatAck();

        boolean hasUpdateReq();

        boolean hasUpdateReqAck();

        boolean hasUpdateStateReq();

        boolean hasUpdateStateReqAck();
    }

    /* loaded from: classes5.dex */
    public interface GoFloorReqAckOrBuilder extends MessageOrBuilder {
        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface GoFloorReqOrBuilder extends MessageOrBuilder {
        GoFloorReq.Action getAction();

        int getActionValue();

        String getCurFloor();

        ByteString getCurFloorBytes();

        String getDestFloor();

        ByteString getDestFloorBytes();

        String getTargetFloor();

        ByteString getTargetFloorBytes();
    }

    /* loaded from: classes5.dex */
    public interface HeartBeatAckOrBuilder extends MessageOrBuilder {
        String getDestFloor();

        ByteString getDestFloorBytes();

        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface HeartBeatOrBuilder extends MessageOrBuilder {
        String getDestFloor();

        ByteString getDestFloorBytes();

        HeartBeat.RobotState getState();

        int getStateValue();
    }

    /* loaded from: classes5.dex */
    public interface UpdateReqAckOrBuilder extends MessageOrBuilder {
        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface UpdateReqOrBuilder extends MessageOrBuilder {
        String getTargetElvVer();

        ByteString getTargetElvVerBytes();
    }

    /* loaded from: classes5.dex */
    public interface UpdateStateReqAckOrBuilder extends MessageOrBuilder {
        String getElvVer();

        ByteString getElvVerBytes();

        int getErrCode();

        String getHardwareType();

        ByteString getHardwareTypeBytes();

        int getProgress();

        int getProtocolVer();

        AckResult getResult();

        AckResultOrBuilder getResultOrBuilder();

        UpdateStateReqAck.UpdateState getState();

        int getStateValue();

        String getTargetElvVer();

        ByteString getTargetElvVerBytes();

        int getTotal();

        boolean hasResult();
    }

    /* loaded from: classes5.dex */
    public interface UpdateStateReqOrBuilder extends MessageOrBuilder {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ElevatorProtocol() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    /* loaded from: classes5.dex */
    public static final class ElvMessage extends GeneratedMessageV3 implements ElvMessageOrBuilder {
        public static final int CALL_TARGET_FLOOR_FIELD_NUMBER = 22;
        public static final int CANCEL_FLOOR_REQ_ACK_FIELD_NUMBER = 17;
        public static final int CANCEL_FLOOR_REQ_FIELD_NUMBER = 16;
        public static final int CONNECT_REQ_ACK_FIELD_NUMBER = 7;
        public static final int CONNECT_REQ_FIELD_NUMBER = 6;
        public static final int DISCONNECT_REQ_ACK_FIELD_NUMBER = 9;
        public static final int DISCONNECT_REQ_FIELD_NUMBER = 8;
        public static final int ELV_ARRIVED_ACK_FIELD_NUMBER = 15;
        public static final int ELV_ARRIVED_FIELD_NUMBER = 14;
        public static final int ELV_ID_FIELD_NUMBER = 1;
        public static final int GO_FLOOR_REQ_ACK_FIELD_NUMBER = 13;
        public static final int GO_FLOOR_REQ_FIELD_NUMBER = 12;
        public static final int HEART_BEAT_ACK_FIELD_NUMBER = 11;
        public static final int HEART_BEAT_FIELD_NUMBER = 10;
        public static final int MSG_TYPE_FIELD_NUMBER = 5;
        public static final int ROBOT_ID_FIELD_NUMBER = 2;
        public static final int SEQ_FIELD_NUMBER = 3;
        public static final int TS_FIELD_NUMBER = 4;
        public static final int UPDATE_REQ_ACK_FIELD_NUMBER = 19;
        public static final int UPDATE_REQ_FIELD_NUMBER = 18;
        public static final int UPDATE_STATE_REQ_ACK_FIELD_NUMBER = 21;
        public static final int UPDATE_STATE_REQ_FIELD_NUMBER = 20;
        private static final long serialVersionUID = 0;
        private CallTargetFloor callTargetFloor_;
        private CancelFloorReqAck cancelFloorReqAck_;
        private CancelFloorReq cancelFloorReq_;
        private ConnectReqAck connectReqAck_;
        private ConnectReq connectReq_;
        private DisconnectReqAck disconnectReqAck_;
        private DisconnectReq disconnectReq_;
        private ElvArrivedAck elvArrivedAck_;
        private ElvArrived elvArrived_;
        private volatile Object elvId_;
        private GoFloorReqAck goFloorReqAck_;
        private GoFloorReq goFloorReq_;
        private HeartBeatAck heartBeatAck_;
        private HeartBeat heartBeat_;
        private byte memoizedIsInitialized;
        private int msgType_;
        private volatile Object robotId_;
        private long seq_;
        private long ts_;
        private UpdateReqAck updateReqAck_;
        private UpdateReq updateReq_;
        private UpdateStateReqAck updateStateReqAck_;
        private UpdateStateReq updateStateReq_;
        private static final ElvMessage DEFAULT_INSTANCE = new ElvMessage();
        private static final Parser<ElvMessage> PARSER = new AbstractParser<ElvMessage>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessage.1
            @Override // com.google.protobuf.Parser
            public ElvMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvMessage(codedInputStream, extensionRegistryLite);
            }
        };

        private ElvMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvMessage() {
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
        private ElvMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                ConnectReq.Builder builder = this.connectReq_ != null ? this.connectReq_.toBuilder() : null;
                                this.connectReq_ = (ConnectReq) codedInputStream.readMessage(ConnectReq.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.connectReq_);
                                    this.connectReq_ = builder.buildPartial();
                                }
                            case 58:
                                ConnectReqAck.Builder builder2 = this.connectReqAck_ != null ? this.connectReqAck_.toBuilder() : null;
                                this.connectReqAck_ = (ConnectReqAck) codedInputStream.readMessage(ConnectReqAck.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.connectReqAck_);
                                    this.connectReqAck_ = builder2.buildPartial();
                                }
                            case 66:
                                DisconnectReq.Builder builder3 = this.disconnectReq_ != null ? this.disconnectReq_.toBuilder() : null;
                                this.disconnectReq_ = (DisconnectReq) codedInputStream.readMessage(DisconnectReq.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.disconnectReq_);
                                    this.disconnectReq_ = builder3.buildPartial();
                                }
                            case 74:
                                DisconnectReqAck.Builder builder4 = this.disconnectReqAck_ != null ? this.disconnectReqAck_.toBuilder() : null;
                                this.disconnectReqAck_ = (DisconnectReqAck) codedInputStream.readMessage(DisconnectReqAck.parser(), extensionRegistryLite);
                                if (builder4 != null) {
                                    builder4.mergeFrom(this.disconnectReqAck_);
                                    this.disconnectReqAck_ = builder4.buildPartial();
                                }
                            case 82:
                                HeartBeat.Builder builder5 = this.heartBeat_ != null ? this.heartBeat_.toBuilder() : null;
                                this.heartBeat_ = (HeartBeat) codedInputStream.readMessage(HeartBeat.parser(), extensionRegistryLite);
                                if (builder5 != null) {
                                    builder5.mergeFrom(this.heartBeat_);
                                    this.heartBeat_ = builder5.buildPartial();
                                }
                            case 90:
                                HeartBeatAck.Builder builder6 = this.heartBeatAck_ != null ? this.heartBeatAck_.toBuilder() : null;
                                this.heartBeatAck_ = (HeartBeatAck) codedInputStream.readMessage(HeartBeatAck.parser(), extensionRegistryLite);
                                if (builder6 != null) {
                                    builder6.mergeFrom(this.heartBeatAck_);
                                    this.heartBeatAck_ = builder6.buildPartial();
                                }
                            case 98:
                                GoFloorReq.Builder builder7 = this.goFloorReq_ != null ? this.goFloorReq_.toBuilder() : null;
                                this.goFloorReq_ = (GoFloorReq) codedInputStream.readMessage(GoFloorReq.parser(), extensionRegistryLite);
                                if (builder7 != null) {
                                    builder7.mergeFrom(this.goFloorReq_);
                                    this.goFloorReq_ = builder7.buildPartial();
                                }
                            case 106:
                                GoFloorReqAck.Builder builder8 = this.goFloorReqAck_ != null ? this.goFloorReqAck_.toBuilder() : null;
                                this.goFloorReqAck_ = (GoFloorReqAck) codedInputStream.readMessage(GoFloorReqAck.parser(), extensionRegistryLite);
                                if (builder8 != null) {
                                    builder8.mergeFrom(this.goFloorReqAck_);
                                    this.goFloorReqAck_ = builder8.buildPartial();
                                }
                            case 114:
                                ElvArrived.Builder builder9 = this.elvArrived_ != null ? this.elvArrived_.toBuilder() : null;
                                this.elvArrived_ = (ElvArrived) codedInputStream.readMessage(ElvArrived.parser(), extensionRegistryLite);
                                if (builder9 != null) {
                                    builder9.mergeFrom(this.elvArrived_);
                                    this.elvArrived_ = builder9.buildPartial();
                                }
                            case 122:
                                ElvArrivedAck.Builder builder10 = this.elvArrivedAck_ != null ? this.elvArrivedAck_.toBuilder() : null;
                                this.elvArrivedAck_ = (ElvArrivedAck) codedInputStream.readMessage(ElvArrivedAck.parser(), extensionRegistryLite);
                                if (builder10 != null) {
                                    builder10.mergeFrom(this.elvArrivedAck_);
                                    this.elvArrivedAck_ = builder10.buildPartial();
                                }
                            case 130:
                                CancelFloorReq.Builder builder11 = this.cancelFloorReq_ != null ? this.cancelFloorReq_.toBuilder() : null;
                                this.cancelFloorReq_ = (CancelFloorReq) codedInputStream.readMessage(CancelFloorReq.parser(), extensionRegistryLite);
                                if (builder11 != null) {
                                    builder11.mergeFrom(this.cancelFloorReq_);
                                    this.cancelFloorReq_ = builder11.buildPartial();
                                }
                            case 138:
                                CancelFloorReqAck.Builder builder12 = this.cancelFloorReqAck_ != null ? this.cancelFloorReqAck_.toBuilder() : null;
                                this.cancelFloorReqAck_ = (CancelFloorReqAck) codedInputStream.readMessage(CancelFloorReqAck.parser(), extensionRegistryLite);
                                if (builder12 != null) {
                                    builder12.mergeFrom(this.cancelFloorReqAck_);
                                    this.cancelFloorReqAck_ = builder12.buildPartial();
                                }
                            case 146:
                                UpdateReq.Builder builder13 = this.updateReq_ != null ? this.updateReq_.toBuilder() : null;
                                this.updateReq_ = (UpdateReq) codedInputStream.readMessage(UpdateReq.parser(), extensionRegistryLite);
                                if (builder13 != null) {
                                    builder13.mergeFrom(this.updateReq_);
                                    this.updateReq_ = builder13.buildPartial();
                                }
                            case 154:
                                UpdateReqAck.Builder builder14 = this.updateReqAck_ != null ? this.updateReqAck_.toBuilder() : null;
                                this.updateReqAck_ = (UpdateReqAck) codedInputStream.readMessage(UpdateReqAck.parser(), extensionRegistryLite);
                                if (builder14 != null) {
                                    builder14.mergeFrom(this.updateReqAck_);
                                    this.updateReqAck_ = builder14.buildPartial();
                                }
                            case 162:
                                UpdateStateReq.Builder builder15 = this.updateStateReq_ != null ? this.updateStateReq_.toBuilder() : null;
                                this.updateStateReq_ = (UpdateStateReq) codedInputStream.readMessage(UpdateStateReq.parser(), extensionRegistryLite);
                                if (builder15 != null) {
                                    builder15.mergeFrom(this.updateStateReq_);
                                    this.updateStateReq_ = builder15.buildPartial();
                                }
                            case 170:
                                UpdateStateReqAck.Builder builder16 = this.updateStateReqAck_ != null ? this.updateStateReqAck_.toBuilder() : null;
                                this.updateStateReqAck_ = (UpdateStateReqAck) codedInputStream.readMessage(UpdateStateReqAck.parser(), extensionRegistryLite);
                                if (builder16 != null) {
                                    builder16.mergeFrom(this.updateStateReqAck_);
                                    this.updateStateReqAck_ = builder16.buildPartial();
                                }
                            case 178:
                                CallTargetFloor.Builder builder17 = this.callTargetFloor_ != null ? this.callTargetFloor_.toBuilder() : null;
                                this.callTargetFloor_ = (CallTargetFloor) codedInputStream.readMessage(CallTargetFloor.parser(), extensionRegistryLite);
                                if (builder17 != null) {
                                    builder17.mergeFrom(this.callTargetFloor_);
                                    this.callTargetFloor_ = builder17.buildPartial();
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
            return ElevatorProtocol.f5845x5c476c3a;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5846xbf0bb8b8.ensureFieldAccessorsInitialized(ElvMessage.class, Builder.class);
        }

        /* loaded from: classes5.dex */
        public enum MsgType implements ProtocolMessageEnum {
            ConnectReq(0),
            ConnectReqAck(1),
            DisconnectReq(2),
            DisconnectReqAck(3),
            HeartBeat(4),
            HeartBeatAck(5),
            GoFloorReq(6),
            GoFloorReqAck(7),
            ElvArrived(8),
            ElvArrivedAck(9),
            CancelFloorReq(10),
            CancelFloorReqAck(11),
            UpdateReq(12),
            UpdateReqAck(13),
            UpdateStateReq(14),
            UpdateStateReqAck(15),
            CallTargetFloor(16),
            UNRECOGNIZED(-1);

            public static final int CallTargetFloor_VALUE = 16;
            public static final int CancelFloorReqAck_VALUE = 11;
            public static final int CancelFloorReq_VALUE = 10;
            public static final int ConnectReqAck_VALUE = 1;
            public static final int ConnectReq_VALUE = 0;
            public static final int DisconnectReqAck_VALUE = 3;
            public static final int DisconnectReq_VALUE = 2;
            public static final int ElvArrivedAck_VALUE = 9;
            public static final int ElvArrived_VALUE = 8;
            public static final int GoFloorReqAck_VALUE = 7;
            public static final int GoFloorReq_VALUE = 6;
            public static final int HeartBeatAck_VALUE = 5;
            public static final int HeartBeat_VALUE = 4;
            public static final int UpdateReqAck_VALUE = 13;
            public static final int UpdateReq_VALUE = 12;
            public static final int UpdateStateReqAck_VALUE = 15;
            public static final int UpdateStateReq_VALUE = 14;
            private final int value;
            private static final Internal.EnumLiteMap<MsgType> internalValueMap = new Internal.EnumLiteMap<MsgType>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessage.MsgType.1
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
                        return ConnectReq;
                    case 1:
                        return ConnectReqAck;
                    case 2:
                        return DisconnectReq;
                    case 3:
                        return DisconnectReqAck;
                    case 4:
                        return HeartBeat;
                    case 5:
                        return HeartBeatAck;
                    case 6:
                        return GoFloorReq;
                    case 7:
                        return GoFloorReqAck;
                    case 8:
                        return ElvArrived;
                    case 9:
                        return ElvArrivedAck;
                    case 10:
                        return CancelFloorReq;
                    case 11:
                        return CancelFloorReqAck;
                    case 12:
                        return UpdateReq;
                    case 13:
                        return UpdateReqAck;
                    case 14:
                        return UpdateStateReq;
                    case 15:
                        return UpdateStateReqAck;
                    case 16:
                        return CallTargetFloor;
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
                return ElvMessage.getDescriptor().getEnumTypes().get(0);
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

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public String getElvId() {
            Object obj = this.elvId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.elvId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ByteString getElvIdBytes() {
            Object obj = this.elvId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.elvId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public String getRobotId() {
            Object obj = this.robotId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.robotId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ByteString getRobotIdBytes() {
            Object obj = this.robotId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.robotId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public long getSeq() {
            return this.seq_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public long getTs() {
            return this.ts_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public int getMsgTypeValue() {
            return this.msgType_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public MsgType getMsgType() {
            MsgType valueOf = MsgType.valueOf(this.msgType_);
            return valueOf == null ? MsgType.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasConnectReq() {
            return this.connectReq_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ConnectReq getConnectReq() {
            ConnectReq connectReq = this.connectReq_;
            return connectReq == null ? ConnectReq.getDefaultInstance() : connectReq;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ConnectReqOrBuilder getConnectReqOrBuilder() {
            return getConnectReq();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasConnectReqAck() {
            return this.connectReqAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ConnectReqAck getConnectReqAck() {
            ConnectReqAck connectReqAck = this.connectReqAck_;
            return connectReqAck == null ? ConnectReqAck.getDefaultInstance() : connectReqAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ConnectReqAckOrBuilder getConnectReqAckOrBuilder() {
            return getConnectReqAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasDisconnectReq() {
            return this.disconnectReq_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public DisconnectReq getDisconnectReq() {
            DisconnectReq disconnectReq = this.disconnectReq_;
            return disconnectReq == null ? DisconnectReq.getDefaultInstance() : disconnectReq;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public DisconnectReqOrBuilder getDisconnectReqOrBuilder() {
            return getDisconnectReq();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasDisconnectReqAck() {
            return this.disconnectReqAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public DisconnectReqAck getDisconnectReqAck() {
            DisconnectReqAck disconnectReqAck = this.disconnectReqAck_;
            return disconnectReqAck == null ? DisconnectReqAck.getDefaultInstance() : disconnectReqAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public DisconnectReqAckOrBuilder getDisconnectReqAckOrBuilder() {
            return getDisconnectReqAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasHeartBeat() {
            return this.heartBeat_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public HeartBeat getHeartBeat() {
            HeartBeat heartBeat = this.heartBeat_;
            return heartBeat == null ? HeartBeat.getDefaultInstance() : heartBeat;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public HeartBeatOrBuilder getHeartBeatOrBuilder() {
            return getHeartBeat();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasHeartBeatAck() {
            return this.heartBeatAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public HeartBeatAck getHeartBeatAck() {
            HeartBeatAck heartBeatAck = this.heartBeatAck_;
            return heartBeatAck == null ? HeartBeatAck.getDefaultInstance() : heartBeatAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public HeartBeatAckOrBuilder getHeartBeatAckOrBuilder() {
            return getHeartBeatAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasGoFloorReq() {
            return this.goFloorReq_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public GoFloorReq getGoFloorReq() {
            GoFloorReq goFloorReq = this.goFloorReq_;
            return goFloorReq == null ? GoFloorReq.getDefaultInstance() : goFloorReq;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public GoFloorReqOrBuilder getGoFloorReqOrBuilder() {
            return getGoFloorReq();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasGoFloorReqAck() {
            return this.goFloorReqAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public GoFloorReqAck getGoFloorReqAck() {
            GoFloorReqAck goFloorReqAck = this.goFloorReqAck_;
            return goFloorReqAck == null ? GoFloorReqAck.getDefaultInstance() : goFloorReqAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public GoFloorReqAckOrBuilder getGoFloorReqAckOrBuilder() {
            return getGoFloorReqAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasElvArrived() {
            return this.elvArrived_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ElvArrived getElvArrived() {
            ElvArrived elvArrived = this.elvArrived_;
            return elvArrived == null ? ElvArrived.getDefaultInstance() : elvArrived;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ElvArrivedOrBuilder getElvArrivedOrBuilder() {
            return getElvArrived();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasElvArrivedAck() {
            return this.elvArrivedAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ElvArrivedAck getElvArrivedAck() {
            ElvArrivedAck elvArrivedAck = this.elvArrivedAck_;
            return elvArrivedAck == null ? ElvArrivedAck.getDefaultInstance() : elvArrivedAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public ElvArrivedAckOrBuilder getElvArrivedAckOrBuilder() {
            return getElvArrivedAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasCancelFloorReq() {
            return this.cancelFloorReq_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public CancelFloorReq getCancelFloorReq() {
            CancelFloorReq cancelFloorReq = this.cancelFloorReq_;
            return cancelFloorReq == null ? CancelFloorReq.getDefaultInstance() : cancelFloorReq;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public CancelFloorReqOrBuilder getCancelFloorReqOrBuilder() {
            return getCancelFloorReq();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasCancelFloorReqAck() {
            return this.cancelFloorReqAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public CancelFloorReqAck getCancelFloorReqAck() {
            CancelFloorReqAck cancelFloorReqAck = this.cancelFloorReqAck_;
            return cancelFloorReqAck == null ? CancelFloorReqAck.getDefaultInstance() : cancelFloorReqAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public CancelFloorReqAckOrBuilder getCancelFloorReqAckOrBuilder() {
            return getCancelFloorReqAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasUpdateReq() {
            return this.updateReq_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateReq getUpdateReq() {
            UpdateReq updateReq = this.updateReq_;
            return updateReq == null ? UpdateReq.getDefaultInstance() : updateReq;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateReqOrBuilder getUpdateReqOrBuilder() {
            return getUpdateReq();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasUpdateReqAck() {
            return this.updateReqAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateReqAck getUpdateReqAck() {
            UpdateReqAck updateReqAck = this.updateReqAck_;
            return updateReqAck == null ? UpdateReqAck.getDefaultInstance() : updateReqAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateReqAckOrBuilder getUpdateReqAckOrBuilder() {
            return getUpdateReqAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasUpdateStateReq() {
            return this.updateStateReq_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateStateReq getUpdateStateReq() {
            UpdateStateReq updateStateReq = this.updateStateReq_;
            return updateStateReq == null ? UpdateStateReq.getDefaultInstance() : updateStateReq;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateStateReqOrBuilder getUpdateStateReqOrBuilder() {
            return getUpdateStateReq();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasUpdateStateReqAck() {
            return this.updateStateReqAck_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateStateReqAck getUpdateStateReqAck() {
            UpdateStateReqAck updateStateReqAck = this.updateStateReqAck_;
            return updateStateReqAck == null ? UpdateStateReqAck.getDefaultInstance() : updateStateReqAck;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public UpdateStateReqAckOrBuilder getUpdateStateReqAckOrBuilder() {
            return getUpdateStateReqAck();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public boolean hasCallTargetFloor() {
            return this.callTargetFloor_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public CallTargetFloor getCallTargetFloor() {
            CallTargetFloor callTargetFloor = this.callTargetFloor_;
            return callTargetFloor == null ? CallTargetFloor.getDefaultInstance() : callTargetFloor;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
        public CallTargetFloorOrBuilder getCallTargetFloorOrBuilder() {
            return getCallTargetFloor();
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
            if (this.msgType_ != MsgType.ConnectReq.getNumber()) {
                codedOutputStream.writeEnum(5, this.msgType_);
            }
            if (this.connectReq_ != null) {
                codedOutputStream.writeMessage(6, getConnectReq());
            }
            if (this.connectReqAck_ != null) {
                codedOutputStream.writeMessage(7, getConnectReqAck());
            }
            if (this.disconnectReq_ != null) {
                codedOutputStream.writeMessage(8, getDisconnectReq());
            }
            if (this.disconnectReqAck_ != null) {
                codedOutputStream.writeMessage(9, getDisconnectReqAck());
            }
            if (this.heartBeat_ != null) {
                codedOutputStream.writeMessage(10, getHeartBeat());
            }
            if (this.heartBeatAck_ != null) {
                codedOutputStream.writeMessage(11, getHeartBeatAck());
            }
            if (this.goFloorReq_ != null) {
                codedOutputStream.writeMessage(12, getGoFloorReq());
            }
            if (this.goFloorReqAck_ != null) {
                codedOutputStream.writeMessage(13, getGoFloorReqAck());
            }
            if (this.elvArrived_ != null) {
                codedOutputStream.writeMessage(14, getElvArrived());
            }
            if (this.elvArrivedAck_ != null) {
                codedOutputStream.writeMessage(15, getElvArrivedAck());
            }
            if (this.cancelFloorReq_ != null) {
                codedOutputStream.writeMessage(16, getCancelFloorReq());
            }
            if (this.cancelFloorReqAck_ != null) {
                codedOutputStream.writeMessage(17, getCancelFloorReqAck());
            }
            if (this.updateReq_ != null) {
                codedOutputStream.writeMessage(18, getUpdateReq());
            }
            if (this.updateReqAck_ != null) {
                codedOutputStream.writeMessage(19, getUpdateReqAck());
            }
            if (this.updateStateReq_ != null) {
                codedOutputStream.writeMessage(20, getUpdateStateReq());
            }
            if (this.updateStateReqAck_ != null) {
                codedOutputStream.writeMessage(21, getUpdateStateReqAck());
            }
            if (this.callTargetFloor_ != null) {
                codedOutputStream.writeMessage(22, getCallTargetFloor());
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
            if (this.msgType_ != MsgType.ConnectReq.getNumber()) {
                computeStringSize += CodedOutputStream.computeEnumSize(5, this.msgType_);
            }
            if (this.connectReq_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(6, getConnectReq());
            }
            if (this.connectReqAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(7, getConnectReqAck());
            }
            if (this.disconnectReq_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(8, getDisconnectReq());
            }
            if (this.disconnectReqAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(9, getDisconnectReqAck());
            }
            if (this.heartBeat_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(10, getHeartBeat());
            }
            if (this.heartBeatAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(11, getHeartBeatAck());
            }
            if (this.goFloorReq_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(12, getGoFloorReq());
            }
            if (this.goFloorReqAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(13, getGoFloorReqAck());
            }
            if (this.elvArrived_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(14, getElvArrived());
            }
            if (this.elvArrivedAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(15, getElvArrivedAck());
            }
            if (this.cancelFloorReq_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(16, getCancelFloorReq());
            }
            if (this.cancelFloorReqAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(17, getCancelFloorReqAck());
            }
            if (this.updateReq_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(18, getUpdateReq());
            }
            if (this.updateReqAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(19, getUpdateReqAck());
            }
            if (this.updateStateReq_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(20, getUpdateStateReq());
            }
            if (this.updateStateReqAck_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(21, getUpdateStateReqAck());
            }
            if (this.callTargetFloor_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(22, getCallTargetFloor());
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
            if (!(obj instanceof ElvMessage)) {
                return super.equals(obj);
            }
            ElvMessage elvMessage = (ElvMessage) obj;
            boolean z = (((((getElvId().equals(elvMessage.getElvId())) && getRobotId().equals(elvMessage.getRobotId())) && (getSeq() > elvMessage.getSeq() ? 1 : (getSeq() == elvMessage.getSeq() ? 0 : -1)) == 0) && (getTs() > elvMessage.getTs() ? 1 : (getTs() == elvMessage.getTs() ? 0 : -1)) == 0) && this.msgType_ == elvMessage.msgType_) && hasConnectReq() == elvMessage.hasConnectReq();
            if (hasConnectReq()) {
                z = z && getConnectReq().equals(elvMessage.getConnectReq());
            }
            boolean z2 = z && hasConnectReqAck() == elvMessage.hasConnectReqAck();
            if (hasConnectReqAck()) {
                z2 = z2 && getConnectReqAck().equals(elvMessage.getConnectReqAck());
            }
            boolean z3 = z2 && hasDisconnectReq() == elvMessage.hasDisconnectReq();
            if (hasDisconnectReq()) {
                z3 = z3 && getDisconnectReq().equals(elvMessage.getDisconnectReq());
            }
            boolean z4 = z3 && hasDisconnectReqAck() == elvMessage.hasDisconnectReqAck();
            if (hasDisconnectReqAck()) {
                z4 = z4 && getDisconnectReqAck().equals(elvMessage.getDisconnectReqAck());
            }
            boolean z5 = z4 && hasHeartBeat() == elvMessage.hasHeartBeat();
            if (hasHeartBeat()) {
                z5 = z5 && getHeartBeat().equals(elvMessage.getHeartBeat());
            }
            boolean z6 = z5 && hasHeartBeatAck() == elvMessage.hasHeartBeatAck();
            if (hasHeartBeatAck()) {
                z6 = z6 && getHeartBeatAck().equals(elvMessage.getHeartBeatAck());
            }
            boolean z7 = z6 && hasGoFloorReq() == elvMessage.hasGoFloorReq();
            if (hasGoFloorReq()) {
                z7 = z7 && getGoFloorReq().equals(elvMessage.getGoFloorReq());
            }
            boolean z8 = z7 && hasGoFloorReqAck() == elvMessage.hasGoFloorReqAck();
            if (hasGoFloorReqAck()) {
                z8 = z8 && getGoFloorReqAck().equals(elvMessage.getGoFloorReqAck());
            }
            boolean z9 = z8 && hasElvArrived() == elvMessage.hasElvArrived();
            if (hasElvArrived()) {
                z9 = z9 && getElvArrived().equals(elvMessage.getElvArrived());
            }
            boolean z10 = z9 && hasElvArrivedAck() == elvMessage.hasElvArrivedAck();
            if (hasElvArrivedAck()) {
                z10 = z10 && getElvArrivedAck().equals(elvMessage.getElvArrivedAck());
            }
            boolean z11 = z10 && hasCancelFloorReq() == elvMessage.hasCancelFloorReq();
            if (hasCancelFloorReq()) {
                z11 = z11 && getCancelFloorReq().equals(elvMessage.getCancelFloorReq());
            }
            boolean z12 = z11 && hasCancelFloorReqAck() == elvMessage.hasCancelFloorReqAck();
            if (hasCancelFloorReqAck()) {
                z12 = z12 && getCancelFloorReqAck().equals(elvMessage.getCancelFloorReqAck());
            }
            boolean z13 = z12 && hasUpdateReq() == elvMessage.hasUpdateReq();
            if (hasUpdateReq()) {
                z13 = z13 && getUpdateReq().equals(elvMessage.getUpdateReq());
            }
            boolean z14 = z13 && hasUpdateReqAck() == elvMessage.hasUpdateReqAck();
            if (hasUpdateReqAck()) {
                z14 = z14 && getUpdateReqAck().equals(elvMessage.getUpdateReqAck());
            }
            boolean z15 = z14 && hasUpdateStateReq() == elvMessage.hasUpdateStateReq();
            if (hasUpdateStateReq()) {
                z15 = z15 && getUpdateStateReq().equals(elvMessage.getUpdateStateReq());
            }
            boolean z16 = z15 && hasUpdateStateReqAck() == elvMessage.hasUpdateStateReqAck();
            if (hasUpdateStateReqAck()) {
                z16 = z16 && getUpdateStateReqAck().equals(elvMessage.getUpdateStateReqAck());
            }
            boolean z17 = z16 && hasCallTargetFloor() == elvMessage.hasCallTargetFloor();
            if (hasCallTargetFloor()) {
                z17 = z17 && getCallTargetFloor().equals(elvMessage.getCallTargetFloor());
            }
            return z17 && this.unknownFields.equals(elvMessage.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getElvId().hashCode()) * 37) + 2) * 53) + getRobotId().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getSeq())) * 37) + 4) * 53) + Internal.hashLong(getTs())) * 37) + 5) * 53) + this.msgType_;
            if (hasConnectReq()) {
                hashCode = (((hashCode * 37) + 6) * 53) + getConnectReq().hashCode();
            }
            if (hasConnectReqAck()) {
                hashCode = (((hashCode * 37) + 7) * 53) + getConnectReqAck().hashCode();
            }
            if (hasDisconnectReq()) {
                hashCode = (((hashCode * 37) + 8) * 53) + getDisconnectReq().hashCode();
            }
            if (hasDisconnectReqAck()) {
                hashCode = (((hashCode * 37) + 9) * 53) + getDisconnectReqAck().hashCode();
            }
            if (hasHeartBeat()) {
                hashCode = (((hashCode * 37) + 10) * 53) + getHeartBeat().hashCode();
            }
            if (hasHeartBeatAck()) {
                hashCode = (((hashCode * 37) + 11) * 53) + getHeartBeatAck().hashCode();
            }
            if (hasGoFloorReq()) {
                hashCode = (((hashCode * 37) + 12) * 53) + getGoFloorReq().hashCode();
            }
            if (hasGoFloorReqAck()) {
                hashCode = (((hashCode * 37) + 13) * 53) + getGoFloorReqAck().hashCode();
            }
            if (hasElvArrived()) {
                hashCode = (((hashCode * 37) + 14) * 53) + getElvArrived().hashCode();
            }
            if (hasElvArrivedAck()) {
                hashCode = (((hashCode * 37) + 15) * 53) + getElvArrivedAck().hashCode();
            }
            if (hasCancelFloorReq()) {
                hashCode = (((hashCode * 37) + 16) * 53) + getCancelFloorReq().hashCode();
            }
            if (hasCancelFloorReqAck()) {
                hashCode = (((hashCode * 37) + 17) * 53) + getCancelFloorReqAck().hashCode();
            }
            if (hasUpdateReq()) {
                hashCode = (((hashCode * 37) + 18) * 53) + getUpdateReq().hashCode();
            }
            if (hasUpdateReqAck()) {
                hashCode = (((hashCode * 37) + 19) * 53) + getUpdateReqAck().hashCode();
            }
            if (hasUpdateStateReq()) {
                hashCode = (((hashCode * 37) + 20) * 53) + getUpdateStateReq().hashCode();
            }
            if (hasUpdateStateReqAck()) {
                hashCode = (((hashCode * 37) + 21) * 53) + getUpdateStateReqAck().hashCode();
            }
            if (hasCallTargetFloor()) {
                hashCode = (((hashCode * 37) + 22) * 53) + getCallTargetFloor().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ElvMessage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvMessage parseFrom(InputStream inputStream) throws IOException {
            return (ElvMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvMessage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvMessage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvMessage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvMessage elvMessage) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvMessage);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvMessageOrBuilder {
            private SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> callTargetFloorBuilder_;
            private CallTargetFloor callTargetFloor_;
            private SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> cancelFloorReqAckBuilder_;
            private CancelFloorReqAck cancelFloorReqAck_;
            private SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> cancelFloorReqBuilder_;
            private CancelFloorReq cancelFloorReq_;
            private SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> connectReqAckBuilder_;
            private ConnectReqAck connectReqAck_;
            private SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> connectReqBuilder_;
            private ConnectReq connectReq_;
            private SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> disconnectReqAckBuilder_;
            private DisconnectReqAck disconnectReqAck_;
            private SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> disconnectReqBuilder_;
            private DisconnectReq disconnectReq_;
            private SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> elvArrivedAckBuilder_;
            private ElvArrivedAck elvArrivedAck_;
            private SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> elvArrivedBuilder_;
            private ElvArrived elvArrived_;
            private Object elvId_;
            private SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> goFloorReqAckBuilder_;
            private GoFloorReqAck goFloorReqAck_;
            private SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> goFloorReqBuilder_;
            private GoFloorReq goFloorReq_;
            private SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> heartBeatAckBuilder_;
            private HeartBeatAck heartBeatAck_;
            private SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> heartBeatBuilder_;
            private HeartBeat heartBeat_;
            private int msgType_;
            private Object robotId_;
            private long seq_;
            private long ts_;
            private SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> updateReqAckBuilder_;
            private UpdateReqAck updateReqAck_;
            private SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> updateReqBuilder_;
            private UpdateReq updateReq_;
            private SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> updateStateReqAckBuilder_;
            private UpdateStateReqAck updateStateReqAck_;
            private SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> updateStateReqBuilder_;
            private UpdateStateReq updateStateReq_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5845x5c476c3a;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5846xbf0bb8b8.ensureFieldAccessorsInitialized(ElvMessage.class, Builder.class);
            }

            private Builder() {
                this.elvId_ = "";
                this.robotId_ = "";
                this.msgType_ = 0;
                this.connectReq_ = null;
                this.connectReqAck_ = null;
                this.disconnectReq_ = null;
                this.disconnectReqAck_ = null;
                this.heartBeat_ = null;
                this.heartBeatAck_ = null;
                this.goFloorReq_ = null;
                this.goFloorReqAck_ = null;
                this.elvArrived_ = null;
                this.elvArrivedAck_ = null;
                this.cancelFloorReq_ = null;
                this.cancelFloorReqAck_ = null;
                this.updateReq_ = null;
                this.updateReqAck_ = null;
                this.updateStateReq_ = null;
                this.updateStateReqAck_ = null;
                this.callTargetFloor_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.elvId_ = "";
                this.robotId_ = "";
                this.msgType_ = 0;
                this.connectReq_ = null;
                this.connectReqAck_ = null;
                this.disconnectReq_ = null;
                this.disconnectReqAck_ = null;
                this.heartBeat_ = null;
                this.heartBeatAck_ = null;
                this.goFloorReq_ = null;
                this.goFloorReqAck_ = null;
                this.elvArrived_ = null;
                this.elvArrivedAck_ = null;
                this.cancelFloorReq_ = null;
                this.cancelFloorReqAck_ = null;
                this.updateReq_ = null;
                this.updateReqAck_ = null;
                this.updateStateReq_ = null;
                this.updateStateReqAck_ = null;
                this.callTargetFloor_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvMessage.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.elvId_ = "";
                this.robotId_ = "";
                this.seq_ = 0L;
                this.ts_ = 0L;
                this.msgType_ = 0;
                if (this.connectReqBuilder_ == null) {
                    this.connectReq_ = null;
                } else {
                    this.connectReq_ = null;
                    this.connectReqBuilder_ = null;
                }
                if (this.connectReqAckBuilder_ == null) {
                    this.connectReqAck_ = null;
                } else {
                    this.connectReqAck_ = null;
                    this.connectReqAckBuilder_ = null;
                }
                if (this.disconnectReqBuilder_ == null) {
                    this.disconnectReq_ = null;
                } else {
                    this.disconnectReq_ = null;
                    this.disconnectReqBuilder_ = null;
                }
                if (this.disconnectReqAckBuilder_ == null) {
                    this.disconnectReqAck_ = null;
                } else {
                    this.disconnectReqAck_ = null;
                    this.disconnectReqAckBuilder_ = null;
                }
                if (this.heartBeatBuilder_ == null) {
                    this.heartBeat_ = null;
                } else {
                    this.heartBeat_ = null;
                    this.heartBeatBuilder_ = null;
                }
                if (this.heartBeatAckBuilder_ == null) {
                    this.heartBeatAck_ = null;
                } else {
                    this.heartBeatAck_ = null;
                    this.heartBeatAckBuilder_ = null;
                }
                if (this.goFloorReqBuilder_ == null) {
                    this.goFloorReq_ = null;
                } else {
                    this.goFloorReq_ = null;
                    this.goFloorReqBuilder_ = null;
                }
                if (this.goFloorReqAckBuilder_ == null) {
                    this.goFloorReqAck_ = null;
                } else {
                    this.goFloorReqAck_ = null;
                    this.goFloorReqAckBuilder_ = null;
                }
                if (this.elvArrivedBuilder_ == null) {
                    this.elvArrived_ = null;
                } else {
                    this.elvArrived_ = null;
                    this.elvArrivedBuilder_ = null;
                }
                if (this.elvArrivedAckBuilder_ == null) {
                    this.elvArrivedAck_ = null;
                } else {
                    this.elvArrivedAck_ = null;
                    this.elvArrivedAckBuilder_ = null;
                }
                if (this.cancelFloorReqBuilder_ == null) {
                    this.cancelFloorReq_ = null;
                } else {
                    this.cancelFloorReq_ = null;
                    this.cancelFloorReqBuilder_ = null;
                }
                if (this.cancelFloorReqAckBuilder_ == null) {
                    this.cancelFloorReqAck_ = null;
                } else {
                    this.cancelFloorReqAck_ = null;
                    this.cancelFloorReqAckBuilder_ = null;
                }
                if (this.updateReqBuilder_ == null) {
                    this.updateReq_ = null;
                } else {
                    this.updateReq_ = null;
                    this.updateReqBuilder_ = null;
                }
                if (this.updateReqAckBuilder_ == null) {
                    this.updateReqAck_ = null;
                } else {
                    this.updateReqAck_ = null;
                    this.updateReqAckBuilder_ = null;
                }
                if (this.updateStateReqBuilder_ == null) {
                    this.updateStateReq_ = null;
                } else {
                    this.updateStateReq_ = null;
                    this.updateStateReqBuilder_ = null;
                }
                if (this.updateStateReqAckBuilder_ == null) {
                    this.updateStateReqAck_ = null;
                } else {
                    this.updateStateReqAck_ = null;
                    this.updateStateReqAckBuilder_ = null;
                }
                if (this.callTargetFloorBuilder_ == null) {
                    this.callTargetFloor_ = null;
                } else {
                    this.callTargetFloor_ = null;
                    this.callTargetFloorBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5845x5c476c3a;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvMessage getDefaultInstanceForType() {
                return ElvMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvMessage build() {
                ElvMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvMessage buildPartial() {
                ElvMessage elvMessage = new ElvMessage(this);
                elvMessage.elvId_ = this.elvId_;
                elvMessage.robotId_ = this.robotId_;
                elvMessage.seq_ = this.seq_;
                elvMessage.ts_ = this.ts_;
                elvMessage.msgType_ = this.msgType_;
                SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> singleFieldBuilderV3 = this.connectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    elvMessage.connectReq_ = this.connectReq_;
                } else {
                    elvMessage.connectReq_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> singleFieldBuilderV32 = this.connectReqAckBuilder_;
                if (singleFieldBuilderV32 == null) {
                    elvMessage.connectReqAck_ = this.connectReqAck_;
                } else {
                    elvMessage.connectReqAck_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> singleFieldBuilderV33 = this.disconnectReqBuilder_;
                if (singleFieldBuilderV33 == null) {
                    elvMessage.disconnectReq_ = this.disconnectReq_;
                } else {
                    elvMessage.disconnectReq_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> singleFieldBuilderV34 = this.disconnectReqAckBuilder_;
                if (singleFieldBuilderV34 == null) {
                    elvMessage.disconnectReqAck_ = this.disconnectReqAck_;
                } else {
                    elvMessage.disconnectReqAck_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> singleFieldBuilderV35 = this.heartBeatBuilder_;
                if (singleFieldBuilderV35 == null) {
                    elvMessage.heartBeat_ = this.heartBeat_;
                } else {
                    elvMessage.heartBeat_ = singleFieldBuilderV35.build();
                }
                SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> singleFieldBuilderV36 = this.heartBeatAckBuilder_;
                if (singleFieldBuilderV36 == null) {
                    elvMessage.heartBeatAck_ = this.heartBeatAck_;
                } else {
                    elvMessage.heartBeatAck_ = singleFieldBuilderV36.build();
                }
                SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> singleFieldBuilderV37 = this.goFloorReqBuilder_;
                if (singleFieldBuilderV37 == null) {
                    elvMessage.goFloorReq_ = this.goFloorReq_;
                } else {
                    elvMessage.goFloorReq_ = singleFieldBuilderV37.build();
                }
                SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> singleFieldBuilderV38 = this.goFloorReqAckBuilder_;
                if (singleFieldBuilderV38 == null) {
                    elvMessage.goFloorReqAck_ = this.goFloorReqAck_;
                } else {
                    elvMessage.goFloorReqAck_ = singleFieldBuilderV38.build();
                }
                SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> singleFieldBuilderV39 = this.elvArrivedBuilder_;
                if (singleFieldBuilderV39 == null) {
                    elvMessage.elvArrived_ = this.elvArrived_;
                } else {
                    elvMessage.elvArrived_ = singleFieldBuilderV39.build();
                }
                SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> singleFieldBuilderV310 = this.elvArrivedAckBuilder_;
                if (singleFieldBuilderV310 == null) {
                    elvMessage.elvArrivedAck_ = this.elvArrivedAck_;
                } else {
                    elvMessage.elvArrivedAck_ = singleFieldBuilderV310.build();
                }
                SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> singleFieldBuilderV311 = this.cancelFloorReqBuilder_;
                if (singleFieldBuilderV311 == null) {
                    elvMessage.cancelFloorReq_ = this.cancelFloorReq_;
                } else {
                    elvMessage.cancelFloorReq_ = singleFieldBuilderV311.build();
                }
                SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> singleFieldBuilderV312 = this.cancelFloorReqAckBuilder_;
                if (singleFieldBuilderV312 == null) {
                    elvMessage.cancelFloorReqAck_ = this.cancelFloorReqAck_;
                } else {
                    elvMessage.cancelFloorReqAck_ = singleFieldBuilderV312.build();
                }
                SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> singleFieldBuilderV313 = this.updateReqBuilder_;
                if (singleFieldBuilderV313 == null) {
                    elvMessage.updateReq_ = this.updateReq_;
                } else {
                    elvMessage.updateReq_ = singleFieldBuilderV313.build();
                }
                SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> singleFieldBuilderV314 = this.updateReqAckBuilder_;
                if (singleFieldBuilderV314 == null) {
                    elvMessage.updateReqAck_ = this.updateReqAck_;
                } else {
                    elvMessage.updateReqAck_ = singleFieldBuilderV314.build();
                }
                SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> singleFieldBuilderV315 = this.updateStateReqBuilder_;
                if (singleFieldBuilderV315 == null) {
                    elvMessage.updateStateReq_ = this.updateStateReq_;
                } else {
                    elvMessage.updateStateReq_ = singleFieldBuilderV315.build();
                }
                SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> singleFieldBuilderV316 = this.updateStateReqAckBuilder_;
                if (singleFieldBuilderV316 == null) {
                    elvMessage.updateStateReqAck_ = this.updateStateReqAck_;
                } else {
                    elvMessage.updateStateReqAck_ = singleFieldBuilderV316.build();
                }
                SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> singleFieldBuilderV317 = this.callTargetFloorBuilder_;
                if (singleFieldBuilderV317 == null) {
                    elvMessage.callTargetFloor_ = this.callTargetFloor_;
                } else {
                    elvMessage.callTargetFloor_ = singleFieldBuilderV317.build();
                }
                onBuilt();
                return elvMessage;
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
                if (message instanceof ElvMessage) {
                    return mergeFrom((ElvMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvMessage elvMessage) {
                if (elvMessage == ElvMessage.getDefaultInstance()) {
                    return this;
                }
                if (!elvMessage.getElvId().isEmpty()) {
                    this.elvId_ = elvMessage.elvId_;
                    onChanged();
                }
                if (!elvMessage.getRobotId().isEmpty()) {
                    this.robotId_ = elvMessage.robotId_;
                    onChanged();
                }
                if (elvMessage.getSeq() != 0) {
                    setSeq(elvMessage.getSeq());
                }
                if (elvMessage.getTs() != 0) {
                    setTs(elvMessage.getTs());
                }
                if (elvMessage.msgType_ != 0) {
                    setMsgTypeValue(elvMessage.getMsgTypeValue());
                }
                if (elvMessage.hasConnectReq()) {
                    mergeConnectReq(elvMessage.getConnectReq());
                }
                if (elvMessage.hasConnectReqAck()) {
                    mergeConnectReqAck(elvMessage.getConnectReqAck());
                }
                if (elvMessage.hasDisconnectReq()) {
                    mergeDisconnectReq(elvMessage.getDisconnectReq());
                }
                if (elvMessage.hasDisconnectReqAck()) {
                    mergeDisconnectReqAck(elvMessage.getDisconnectReqAck());
                }
                if (elvMessage.hasHeartBeat()) {
                    mergeHeartBeat(elvMessage.getHeartBeat());
                }
                if (elvMessage.hasHeartBeatAck()) {
                    mergeHeartBeatAck(elvMessage.getHeartBeatAck());
                }
                if (elvMessage.hasGoFloorReq()) {
                    mergeGoFloorReq(elvMessage.getGoFloorReq());
                }
                if (elvMessage.hasGoFloorReqAck()) {
                    mergeGoFloorReqAck(elvMessage.getGoFloorReqAck());
                }
                if (elvMessage.hasElvArrived()) {
                    mergeElvArrived(elvMessage.getElvArrived());
                }
                if (elvMessage.hasElvArrivedAck()) {
                    mergeElvArrivedAck(elvMessage.getElvArrivedAck());
                }
                if (elvMessage.hasCancelFloorReq()) {
                    mergeCancelFloorReq(elvMessage.getCancelFloorReq());
                }
                if (elvMessage.hasCancelFloorReqAck()) {
                    mergeCancelFloorReqAck(elvMessage.getCancelFloorReqAck());
                }
                if (elvMessage.hasUpdateReq()) {
                    mergeUpdateReq(elvMessage.getUpdateReq());
                }
                if (elvMessage.hasUpdateReqAck()) {
                    mergeUpdateReqAck(elvMessage.getUpdateReqAck());
                }
                if (elvMessage.hasUpdateStateReq()) {
                    mergeUpdateStateReq(elvMessage.getUpdateStateReq());
                }
                if (elvMessage.hasUpdateStateReqAck()) {
                    mergeUpdateStateReqAck(elvMessage.getUpdateStateReqAck());
                }
                if (elvMessage.hasCallTargetFloor()) {
                    mergeCallTargetFloor(elvMessage.getCallTargetFloor());
                }
                mergeUnknownFields(elvMessage.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvMessage elvMessage = null;
                try {
                    try {
                        ElvMessage elvMessage2 = (ElvMessage) ElvMessage.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvMessage2 != null) {
                            mergeFrom(elvMessage2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvMessage elvMessage3 = (ElvMessage) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvMessage = elvMessage3;
                            if (elvMessage != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvMessage != null) {
                        mergeFrom(elvMessage);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public String getElvId() {
                Object obj = this.elvId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.elvId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
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
                this.elvId_ = ElvMessage.getDefaultInstance().getElvId();
                onChanged();
                return this;
            }

            public Builder setElvIdBytes(ByteString byteString) {
                if (byteString != null) {
                    ElvMessage.checkByteStringIsUtf8(byteString);
                    this.elvId_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public String getRobotId() {
                Object obj = this.robotId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.robotId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
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
                this.robotId_ = ElvMessage.getDefaultInstance().getRobotId();
                onChanged();
                return this;
            }

            public Builder setRobotIdBytes(ByteString byteString) {
                if (byteString != null) {
                    ElvMessage.checkByteStringIsUtf8(byteString);
                    this.robotId_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
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

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
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

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public int getMsgTypeValue() {
                return this.msgType_;
            }

            public Builder setMsgTypeValue(int i) {
                this.msgType_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
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

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasConnectReq() {
                return (this.connectReqBuilder_ == null && this.connectReq_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ConnectReq getConnectReq() {
                SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> singleFieldBuilderV3 = this.connectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ConnectReq connectReq = this.connectReq_;
                    return connectReq == null ? ConnectReq.getDefaultInstance() : connectReq;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setConnectReq(ConnectReq connectReq) {
                SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> singleFieldBuilderV3 = this.connectReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(connectReq);
                } else {
                    if (connectReq == null) {
                        throw new NullPointerException();
                    }
                    this.connectReq_ = connectReq;
                    onChanged();
                }
                return this;
            }

            public Builder setConnectReq(ConnectReq.Builder builder) {
                SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> singleFieldBuilderV3 = this.connectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.connectReq_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeConnectReq(ConnectReq connectReq) {
                SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> singleFieldBuilderV3 = this.connectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ConnectReq connectReq2 = this.connectReq_;
                    if (connectReq2 != null) {
                        this.connectReq_ = ConnectReq.newBuilder(connectReq2).mergeFrom(connectReq).buildPartial();
                    } else {
                        this.connectReq_ = connectReq;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(connectReq);
                }
                return this;
            }

            public Builder clearConnectReq() {
                if (this.connectReqBuilder_ == null) {
                    this.connectReq_ = null;
                    onChanged();
                } else {
                    this.connectReq_ = null;
                    this.connectReqBuilder_ = null;
                }
                return this;
            }

            public ConnectReq.Builder getConnectReqBuilder() {
                onChanged();
                return getConnectReqFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ConnectReqOrBuilder getConnectReqOrBuilder() {
                SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> singleFieldBuilderV3 = this.connectReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ConnectReq connectReq = this.connectReq_;
                return connectReq == null ? ConnectReq.getDefaultInstance() : connectReq;
            }

            private SingleFieldBuilderV3<ConnectReq, ConnectReq.Builder, ConnectReqOrBuilder> getConnectReqFieldBuilder() {
                if (this.connectReqBuilder_ == null) {
                    this.connectReqBuilder_ = new SingleFieldBuilderV3<>(getConnectReq(), getParentForChildren(), isClean());
                    this.connectReq_ = null;
                }
                return this.connectReqBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasConnectReqAck() {
                return (this.connectReqAckBuilder_ == null && this.connectReqAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ConnectReqAck getConnectReqAck() {
                SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> singleFieldBuilderV3 = this.connectReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ConnectReqAck connectReqAck = this.connectReqAck_;
                    return connectReqAck == null ? ConnectReqAck.getDefaultInstance() : connectReqAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setConnectReqAck(ConnectReqAck connectReqAck) {
                SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> singleFieldBuilderV3 = this.connectReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(connectReqAck);
                } else {
                    if (connectReqAck == null) {
                        throw new NullPointerException();
                    }
                    this.connectReqAck_ = connectReqAck;
                    onChanged();
                }
                return this;
            }

            public Builder setConnectReqAck(ConnectReqAck.Builder builder) {
                SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> singleFieldBuilderV3 = this.connectReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.connectReqAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeConnectReqAck(ConnectReqAck connectReqAck) {
                SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> singleFieldBuilderV3 = this.connectReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ConnectReqAck connectReqAck2 = this.connectReqAck_;
                    if (connectReqAck2 != null) {
                        this.connectReqAck_ = ConnectReqAck.newBuilder(connectReqAck2).mergeFrom(connectReqAck).buildPartial();
                    } else {
                        this.connectReqAck_ = connectReqAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(connectReqAck);
                }
                return this;
            }

            public Builder clearConnectReqAck() {
                if (this.connectReqAckBuilder_ == null) {
                    this.connectReqAck_ = null;
                    onChanged();
                } else {
                    this.connectReqAck_ = null;
                    this.connectReqAckBuilder_ = null;
                }
                return this;
            }

            public ConnectReqAck.Builder getConnectReqAckBuilder() {
                onChanged();
                return getConnectReqAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ConnectReqAckOrBuilder getConnectReqAckOrBuilder() {
                SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> singleFieldBuilderV3 = this.connectReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ConnectReqAck connectReqAck = this.connectReqAck_;
                return connectReqAck == null ? ConnectReqAck.getDefaultInstance() : connectReqAck;
            }

            private SingleFieldBuilderV3<ConnectReqAck, ConnectReqAck.Builder, ConnectReqAckOrBuilder> getConnectReqAckFieldBuilder() {
                if (this.connectReqAckBuilder_ == null) {
                    this.connectReqAckBuilder_ = new SingleFieldBuilderV3<>(getConnectReqAck(), getParentForChildren(), isClean());
                    this.connectReqAck_ = null;
                }
                return this.connectReqAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasDisconnectReq() {
                return (this.disconnectReqBuilder_ == null && this.disconnectReq_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public DisconnectReq getDisconnectReq() {
                SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> singleFieldBuilderV3 = this.disconnectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    DisconnectReq disconnectReq = this.disconnectReq_;
                    return disconnectReq == null ? DisconnectReq.getDefaultInstance() : disconnectReq;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setDisconnectReq(DisconnectReq disconnectReq) {
                SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> singleFieldBuilderV3 = this.disconnectReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(disconnectReq);
                } else {
                    if (disconnectReq == null) {
                        throw new NullPointerException();
                    }
                    this.disconnectReq_ = disconnectReq;
                    onChanged();
                }
                return this;
            }

            public Builder setDisconnectReq(DisconnectReq.Builder builder) {
                SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> singleFieldBuilderV3 = this.disconnectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.disconnectReq_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeDisconnectReq(DisconnectReq disconnectReq) {
                SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> singleFieldBuilderV3 = this.disconnectReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    DisconnectReq disconnectReq2 = this.disconnectReq_;
                    if (disconnectReq2 != null) {
                        this.disconnectReq_ = DisconnectReq.newBuilder(disconnectReq2).mergeFrom(disconnectReq).buildPartial();
                    } else {
                        this.disconnectReq_ = disconnectReq;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(disconnectReq);
                }
                return this;
            }

            public Builder clearDisconnectReq() {
                if (this.disconnectReqBuilder_ == null) {
                    this.disconnectReq_ = null;
                    onChanged();
                } else {
                    this.disconnectReq_ = null;
                    this.disconnectReqBuilder_ = null;
                }
                return this;
            }

            public DisconnectReq.Builder getDisconnectReqBuilder() {
                onChanged();
                return getDisconnectReqFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public DisconnectReqOrBuilder getDisconnectReqOrBuilder() {
                SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> singleFieldBuilderV3 = this.disconnectReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                DisconnectReq disconnectReq = this.disconnectReq_;
                return disconnectReq == null ? DisconnectReq.getDefaultInstance() : disconnectReq;
            }

            private SingleFieldBuilderV3<DisconnectReq, DisconnectReq.Builder, DisconnectReqOrBuilder> getDisconnectReqFieldBuilder() {
                if (this.disconnectReqBuilder_ == null) {
                    this.disconnectReqBuilder_ = new SingleFieldBuilderV3<>(getDisconnectReq(), getParentForChildren(), isClean());
                    this.disconnectReq_ = null;
                }
                return this.disconnectReqBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasDisconnectReqAck() {
                return (this.disconnectReqAckBuilder_ == null && this.disconnectReqAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public DisconnectReqAck getDisconnectReqAck() {
                SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> singleFieldBuilderV3 = this.disconnectReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    DisconnectReqAck disconnectReqAck = this.disconnectReqAck_;
                    return disconnectReqAck == null ? DisconnectReqAck.getDefaultInstance() : disconnectReqAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setDisconnectReqAck(DisconnectReqAck disconnectReqAck) {
                SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> singleFieldBuilderV3 = this.disconnectReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(disconnectReqAck);
                } else {
                    if (disconnectReqAck == null) {
                        throw new NullPointerException();
                    }
                    this.disconnectReqAck_ = disconnectReqAck;
                    onChanged();
                }
                return this;
            }

            public Builder setDisconnectReqAck(DisconnectReqAck.Builder builder) {
                SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> singleFieldBuilderV3 = this.disconnectReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.disconnectReqAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeDisconnectReqAck(DisconnectReqAck disconnectReqAck) {
                SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> singleFieldBuilderV3 = this.disconnectReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    DisconnectReqAck disconnectReqAck2 = this.disconnectReqAck_;
                    if (disconnectReqAck2 != null) {
                        this.disconnectReqAck_ = DisconnectReqAck.newBuilder(disconnectReqAck2).mergeFrom(disconnectReqAck).buildPartial();
                    } else {
                        this.disconnectReqAck_ = disconnectReqAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(disconnectReqAck);
                }
                return this;
            }

            public Builder clearDisconnectReqAck() {
                if (this.disconnectReqAckBuilder_ == null) {
                    this.disconnectReqAck_ = null;
                    onChanged();
                } else {
                    this.disconnectReqAck_ = null;
                    this.disconnectReqAckBuilder_ = null;
                }
                return this;
            }

            public DisconnectReqAck.Builder getDisconnectReqAckBuilder() {
                onChanged();
                return getDisconnectReqAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public DisconnectReqAckOrBuilder getDisconnectReqAckOrBuilder() {
                SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> singleFieldBuilderV3 = this.disconnectReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                DisconnectReqAck disconnectReqAck = this.disconnectReqAck_;
                return disconnectReqAck == null ? DisconnectReqAck.getDefaultInstance() : disconnectReqAck;
            }

            private SingleFieldBuilderV3<DisconnectReqAck, DisconnectReqAck.Builder, DisconnectReqAckOrBuilder> getDisconnectReqAckFieldBuilder() {
                if (this.disconnectReqAckBuilder_ == null) {
                    this.disconnectReqAckBuilder_ = new SingleFieldBuilderV3<>(getDisconnectReqAck(), getParentForChildren(), isClean());
                    this.disconnectReqAck_ = null;
                }
                return this.disconnectReqAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasHeartBeat() {
                return (this.heartBeatBuilder_ == null && this.heartBeat_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public HeartBeat getHeartBeat() {
                SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> singleFieldBuilderV3 = this.heartBeatBuilder_;
                if (singleFieldBuilderV3 == null) {
                    HeartBeat heartBeat = this.heartBeat_;
                    return heartBeat == null ? HeartBeat.getDefaultInstance() : heartBeat;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setHeartBeat(HeartBeat heartBeat) {
                SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> singleFieldBuilderV3 = this.heartBeatBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(heartBeat);
                } else {
                    if (heartBeat == null) {
                        throw new NullPointerException();
                    }
                    this.heartBeat_ = heartBeat;
                    onChanged();
                }
                return this;
            }

            public Builder setHeartBeat(HeartBeat.Builder builder) {
                SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> singleFieldBuilderV3 = this.heartBeatBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.heartBeat_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeHeartBeat(HeartBeat heartBeat) {
                SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> singleFieldBuilderV3 = this.heartBeatBuilder_;
                if (singleFieldBuilderV3 == null) {
                    HeartBeat heartBeat2 = this.heartBeat_;
                    if (heartBeat2 != null) {
                        this.heartBeat_ = HeartBeat.newBuilder(heartBeat2).mergeFrom(heartBeat).buildPartial();
                    } else {
                        this.heartBeat_ = heartBeat;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(heartBeat);
                }
                return this;
            }

            public Builder clearHeartBeat() {
                if (this.heartBeatBuilder_ == null) {
                    this.heartBeat_ = null;
                    onChanged();
                } else {
                    this.heartBeat_ = null;
                    this.heartBeatBuilder_ = null;
                }
                return this;
            }

            public HeartBeat.Builder getHeartBeatBuilder() {
                onChanged();
                return getHeartBeatFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public HeartBeatOrBuilder getHeartBeatOrBuilder() {
                SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> singleFieldBuilderV3 = this.heartBeatBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                HeartBeat heartBeat = this.heartBeat_;
                return heartBeat == null ? HeartBeat.getDefaultInstance() : heartBeat;
            }

            private SingleFieldBuilderV3<HeartBeat, HeartBeat.Builder, HeartBeatOrBuilder> getHeartBeatFieldBuilder() {
                if (this.heartBeatBuilder_ == null) {
                    this.heartBeatBuilder_ = new SingleFieldBuilderV3<>(getHeartBeat(), getParentForChildren(), isClean());
                    this.heartBeat_ = null;
                }
                return this.heartBeatBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasHeartBeatAck() {
                return (this.heartBeatAckBuilder_ == null && this.heartBeatAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public HeartBeatAck getHeartBeatAck() {
                SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> singleFieldBuilderV3 = this.heartBeatAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    HeartBeatAck heartBeatAck = this.heartBeatAck_;
                    return heartBeatAck == null ? HeartBeatAck.getDefaultInstance() : heartBeatAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setHeartBeatAck(HeartBeatAck heartBeatAck) {
                SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> singleFieldBuilderV3 = this.heartBeatAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(heartBeatAck);
                } else {
                    if (heartBeatAck == null) {
                        throw new NullPointerException();
                    }
                    this.heartBeatAck_ = heartBeatAck;
                    onChanged();
                }
                return this;
            }

            public Builder setHeartBeatAck(HeartBeatAck.Builder builder) {
                SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> singleFieldBuilderV3 = this.heartBeatAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.heartBeatAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeHeartBeatAck(HeartBeatAck heartBeatAck) {
                SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> singleFieldBuilderV3 = this.heartBeatAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    HeartBeatAck heartBeatAck2 = this.heartBeatAck_;
                    if (heartBeatAck2 != null) {
                        this.heartBeatAck_ = HeartBeatAck.newBuilder(heartBeatAck2).mergeFrom(heartBeatAck).buildPartial();
                    } else {
                        this.heartBeatAck_ = heartBeatAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(heartBeatAck);
                }
                return this;
            }

            public Builder clearHeartBeatAck() {
                if (this.heartBeatAckBuilder_ == null) {
                    this.heartBeatAck_ = null;
                    onChanged();
                } else {
                    this.heartBeatAck_ = null;
                    this.heartBeatAckBuilder_ = null;
                }
                return this;
            }

            public HeartBeatAck.Builder getHeartBeatAckBuilder() {
                onChanged();
                return getHeartBeatAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public HeartBeatAckOrBuilder getHeartBeatAckOrBuilder() {
                SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> singleFieldBuilderV3 = this.heartBeatAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                HeartBeatAck heartBeatAck = this.heartBeatAck_;
                return heartBeatAck == null ? HeartBeatAck.getDefaultInstance() : heartBeatAck;
            }

            private SingleFieldBuilderV3<HeartBeatAck, HeartBeatAck.Builder, HeartBeatAckOrBuilder> getHeartBeatAckFieldBuilder() {
                if (this.heartBeatAckBuilder_ == null) {
                    this.heartBeatAckBuilder_ = new SingleFieldBuilderV3<>(getHeartBeatAck(), getParentForChildren(), isClean());
                    this.heartBeatAck_ = null;
                }
                return this.heartBeatAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasGoFloorReq() {
                return (this.goFloorReqBuilder_ == null && this.goFloorReq_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public GoFloorReq getGoFloorReq() {
                SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> singleFieldBuilderV3 = this.goFloorReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    GoFloorReq goFloorReq = this.goFloorReq_;
                    return goFloorReq == null ? GoFloorReq.getDefaultInstance() : goFloorReq;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setGoFloorReq(GoFloorReq goFloorReq) {
                SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> singleFieldBuilderV3 = this.goFloorReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(goFloorReq);
                } else {
                    if (goFloorReq == null) {
                        throw new NullPointerException();
                    }
                    this.goFloorReq_ = goFloorReq;
                    onChanged();
                }
                return this;
            }

            public Builder setGoFloorReq(GoFloorReq.Builder builder) {
                SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> singleFieldBuilderV3 = this.goFloorReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.goFloorReq_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeGoFloorReq(GoFloorReq goFloorReq) {
                SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> singleFieldBuilderV3 = this.goFloorReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    GoFloorReq goFloorReq2 = this.goFloorReq_;
                    if (goFloorReq2 != null) {
                        this.goFloorReq_ = GoFloorReq.newBuilder(goFloorReq2).mergeFrom(goFloorReq).buildPartial();
                    } else {
                        this.goFloorReq_ = goFloorReq;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(goFloorReq);
                }
                return this;
            }

            public Builder clearGoFloorReq() {
                if (this.goFloorReqBuilder_ == null) {
                    this.goFloorReq_ = null;
                    onChanged();
                } else {
                    this.goFloorReq_ = null;
                    this.goFloorReqBuilder_ = null;
                }
                return this;
            }

            public GoFloorReq.Builder getGoFloorReqBuilder() {
                onChanged();
                return getGoFloorReqFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public GoFloorReqOrBuilder getGoFloorReqOrBuilder() {
                SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> singleFieldBuilderV3 = this.goFloorReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                GoFloorReq goFloorReq = this.goFloorReq_;
                return goFloorReq == null ? GoFloorReq.getDefaultInstance() : goFloorReq;
            }

            private SingleFieldBuilderV3<GoFloorReq, GoFloorReq.Builder, GoFloorReqOrBuilder> getGoFloorReqFieldBuilder() {
                if (this.goFloorReqBuilder_ == null) {
                    this.goFloorReqBuilder_ = new SingleFieldBuilderV3<>(getGoFloorReq(), getParentForChildren(), isClean());
                    this.goFloorReq_ = null;
                }
                return this.goFloorReqBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasGoFloorReqAck() {
                return (this.goFloorReqAckBuilder_ == null && this.goFloorReqAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public GoFloorReqAck getGoFloorReqAck() {
                SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> singleFieldBuilderV3 = this.goFloorReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    GoFloorReqAck goFloorReqAck = this.goFloorReqAck_;
                    return goFloorReqAck == null ? GoFloorReqAck.getDefaultInstance() : goFloorReqAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setGoFloorReqAck(GoFloorReqAck goFloorReqAck) {
                SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> singleFieldBuilderV3 = this.goFloorReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(goFloorReqAck);
                } else {
                    if (goFloorReqAck == null) {
                        throw new NullPointerException();
                    }
                    this.goFloorReqAck_ = goFloorReqAck;
                    onChanged();
                }
                return this;
            }

            public Builder setGoFloorReqAck(GoFloorReqAck.Builder builder) {
                SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> singleFieldBuilderV3 = this.goFloorReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.goFloorReqAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeGoFloorReqAck(GoFloorReqAck goFloorReqAck) {
                SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> singleFieldBuilderV3 = this.goFloorReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    GoFloorReqAck goFloorReqAck2 = this.goFloorReqAck_;
                    if (goFloorReqAck2 != null) {
                        this.goFloorReqAck_ = GoFloorReqAck.newBuilder(goFloorReqAck2).mergeFrom(goFloorReqAck).buildPartial();
                    } else {
                        this.goFloorReqAck_ = goFloorReqAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(goFloorReqAck);
                }
                return this;
            }

            public Builder clearGoFloorReqAck() {
                if (this.goFloorReqAckBuilder_ == null) {
                    this.goFloorReqAck_ = null;
                    onChanged();
                } else {
                    this.goFloorReqAck_ = null;
                    this.goFloorReqAckBuilder_ = null;
                }
                return this;
            }

            public GoFloorReqAck.Builder getGoFloorReqAckBuilder() {
                onChanged();
                return getGoFloorReqAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public GoFloorReqAckOrBuilder getGoFloorReqAckOrBuilder() {
                SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> singleFieldBuilderV3 = this.goFloorReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                GoFloorReqAck goFloorReqAck = this.goFloorReqAck_;
                return goFloorReqAck == null ? GoFloorReqAck.getDefaultInstance() : goFloorReqAck;
            }

            private SingleFieldBuilderV3<GoFloorReqAck, GoFloorReqAck.Builder, GoFloorReqAckOrBuilder> getGoFloorReqAckFieldBuilder() {
                if (this.goFloorReqAckBuilder_ == null) {
                    this.goFloorReqAckBuilder_ = new SingleFieldBuilderV3<>(getGoFloorReqAck(), getParentForChildren(), isClean());
                    this.goFloorReqAck_ = null;
                }
                return this.goFloorReqAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasElvArrived() {
                return (this.elvArrivedBuilder_ == null && this.elvArrived_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ElvArrived getElvArrived() {
                SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> singleFieldBuilderV3 = this.elvArrivedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvArrived elvArrived = this.elvArrived_;
                    return elvArrived == null ? ElvArrived.getDefaultInstance() : elvArrived;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setElvArrived(ElvArrived elvArrived) {
                SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> singleFieldBuilderV3 = this.elvArrivedBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvArrived);
                } else {
                    if (elvArrived == null) {
                        throw new NullPointerException();
                    }
                    this.elvArrived_ = elvArrived;
                    onChanged();
                }
                return this;
            }

            public Builder setElvArrived(ElvArrived.Builder builder) {
                SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> singleFieldBuilderV3 = this.elvArrivedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.elvArrived_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeElvArrived(ElvArrived elvArrived) {
                SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> singleFieldBuilderV3 = this.elvArrivedBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvArrived elvArrived2 = this.elvArrived_;
                    if (elvArrived2 != null) {
                        this.elvArrived_ = ElvArrived.newBuilder(elvArrived2).mergeFrom(elvArrived).buildPartial();
                    } else {
                        this.elvArrived_ = elvArrived;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvArrived);
                }
                return this;
            }

            public Builder clearElvArrived() {
                if (this.elvArrivedBuilder_ == null) {
                    this.elvArrived_ = null;
                    onChanged();
                } else {
                    this.elvArrived_ = null;
                    this.elvArrivedBuilder_ = null;
                }
                return this;
            }

            public ElvArrived.Builder getElvArrivedBuilder() {
                onChanged();
                return getElvArrivedFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ElvArrivedOrBuilder getElvArrivedOrBuilder() {
                SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> singleFieldBuilderV3 = this.elvArrivedBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvArrived elvArrived = this.elvArrived_;
                return elvArrived == null ? ElvArrived.getDefaultInstance() : elvArrived;
            }

            private SingleFieldBuilderV3<ElvArrived, ElvArrived.Builder, ElvArrivedOrBuilder> getElvArrivedFieldBuilder() {
                if (this.elvArrivedBuilder_ == null) {
                    this.elvArrivedBuilder_ = new SingleFieldBuilderV3<>(getElvArrived(), getParentForChildren(), isClean());
                    this.elvArrived_ = null;
                }
                return this.elvArrivedBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasElvArrivedAck() {
                return (this.elvArrivedAckBuilder_ == null && this.elvArrivedAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ElvArrivedAck getElvArrivedAck() {
                SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> singleFieldBuilderV3 = this.elvArrivedAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvArrivedAck elvArrivedAck = this.elvArrivedAck_;
                    return elvArrivedAck == null ? ElvArrivedAck.getDefaultInstance() : elvArrivedAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setElvArrivedAck(ElvArrivedAck elvArrivedAck) {
                SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> singleFieldBuilderV3 = this.elvArrivedAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(elvArrivedAck);
                } else {
                    if (elvArrivedAck == null) {
                        throw new NullPointerException();
                    }
                    this.elvArrivedAck_ = elvArrivedAck;
                    onChanged();
                }
                return this;
            }

            public Builder setElvArrivedAck(ElvArrivedAck.Builder builder) {
                SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> singleFieldBuilderV3 = this.elvArrivedAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.elvArrivedAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeElvArrivedAck(ElvArrivedAck elvArrivedAck) {
                SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> singleFieldBuilderV3 = this.elvArrivedAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ElvArrivedAck elvArrivedAck2 = this.elvArrivedAck_;
                    if (elvArrivedAck2 != null) {
                        this.elvArrivedAck_ = ElvArrivedAck.newBuilder(elvArrivedAck2).mergeFrom(elvArrivedAck).buildPartial();
                    } else {
                        this.elvArrivedAck_ = elvArrivedAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(elvArrivedAck);
                }
                return this;
            }

            public Builder clearElvArrivedAck() {
                if (this.elvArrivedAckBuilder_ == null) {
                    this.elvArrivedAck_ = null;
                    onChanged();
                } else {
                    this.elvArrivedAck_ = null;
                    this.elvArrivedAckBuilder_ = null;
                }
                return this;
            }

            public ElvArrivedAck.Builder getElvArrivedAckBuilder() {
                onChanged();
                return getElvArrivedAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public ElvArrivedAckOrBuilder getElvArrivedAckOrBuilder() {
                SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> singleFieldBuilderV3 = this.elvArrivedAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ElvArrivedAck elvArrivedAck = this.elvArrivedAck_;
                return elvArrivedAck == null ? ElvArrivedAck.getDefaultInstance() : elvArrivedAck;
            }

            private SingleFieldBuilderV3<ElvArrivedAck, ElvArrivedAck.Builder, ElvArrivedAckOrBuilder> getElvArrivedAckFieldBuilder() {
                if (this.elvArrivedAckBuilder_ == null) {
                    this.elvArrivedAckBuilder_ = new SingleFieldBuilderV3<>(getElvArrivedAck(), getParentForChildren(), isClean());
                    this.elvArrivedAck_ = null;
                }
                return this.elvArrivedAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasCancelFloorReq() {
                return (this.cancelFloorReqBuilder_ == null && this.cancelFloorReq_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public CancelFloorReq getCancelFloorReq() {
                SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelFloorReq cancelFloorReq = this.cancelFloorReq_;
                    return cancelFloorReq == null ? CancelFloorReq.getDefaultInstance() : cancelFloorReq;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCancelFloorReq(CancelFloorReq cancelFloorReq) {
                SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(cancelFloorReq);
                } else {
                    if (cancelFloorReq == null) {
                        throw new NullPointerException();
                    }
                    this.cancelFloorReq_ = cancelFloorReq;
                    onChanged();
                }
                return this;
            }

            public Builder setCancelFloorReq(CancelFloorReq.Builder builder) {
                SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.cancelFloorReq_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCancelFloorReq(CancelFloorReq cancelFloorReq) {
                SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelFloorReq cancelFloorReq2 = this.cancelFloorReq_;
                    if (cancelFloorReq2 != null) {
                        this.cancelFloorReq_ = CancelFloorReq.newBuilder(cancelFloorReq2).mergeFrom(cancelFloorReq).buildPartial();
                    } else {
                        this.cancelFloorReq_ = cancelFloorReq;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(cancelFloorReq);
                }
                return this;
            }

            public Builder clearCancelFloorReq() {
                if (this.cancelFloorReqBuilder_ == null) {
                    this.cancelFloorReq_ = null;
                    onChanged();
                } else {
                    this.cancelFloorReq_ = null;
                    this.cancelFloorReqBuilder_ = null;
                }
                return this;
            }

            public CancelFloorReq.Builder getCancelFloorReqBuilder() {
                onChanged();
                return getCancelFloorReqFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public CancelFloorReqOrBuilder getCancelFloorReqOrBuilder() {
                SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CancelFloorReq cancelFloorReq = this.cancelFloorReq_;
                return cancelFloorReq == null ? CancelFloorReq.getDefaultInstance() : cancelFloorReq;
            }

            private SingleFieldBuilderV3<CancelFloorReq, CancelFloorReq.Builder, CancelFloorReqOrBuilder> getCancelFloorReqFieldBuilder() {
                if (this.cancelFloorReqBuilder_ == null) {
                    this.cancelFloorReqBuilder_ = new SingleFieldBuilderV3<>(getCancelFloorReq(), getParentForChildren(), isClean());
                    this.cancelFloorReq_ = null;
                }
                return this.cancelFloorReqBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasCancelFloorReqAck() {
                return (this.cancelFloorReqAckBuilder_ == null && this.cancelFloorReqAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public CancelFloorReqAck getCancelFloorReqAck() {
                SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelFloorReqAck cancelFloorReqAck = this.cancelFloorReqAck_;
                    return cancelFloorReqAck == null ? CancelFloorReqAck.getDefaultInstance() : cancelFloorReqAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCancelFloorReqAck(CancelFloorReqAck cancelFloorReqAck) {
                SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(cancelFloorReqAck);
                } else {
                    if (cancelFloorReqAck == null) {
                        throw new NullPointerException();
                    }
                    this.cancelFloorReqAck_ = cancelFloorReqAck;
                    onChanged();
                }
                return this;
            }

            public Builder setCancelFloorReqAck(CancelFloorReqAck.Builder builder) {
                SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.cancelFloorReqAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCancelFloorReqAck(CancelFloorReqAck cancelFloorReqAck) {
                SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CancelFloorReqAck cancelFloorReqAck2 = this.cancelFloorReqAck_;
                    if (cancelFloorReqAck2 != null) {
                        this.cancelFloorReqAck_ = CancelFloorReqAck.newBuilder(cancelFloorReqAck2).mergeFrom(cancelFloorReqAck).buildPartial();
                    } else {
                        this.cancelFloorReqAck_ = cancelFloorReqAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(cancelFloorReqAck);
                }
                return this;
            }

            public Builder clearCancelFloorReqAck() {
                if (this.cancelFloorReqAckBuilder_ == null) {
                    this.cancelFloorReqAck_ = null;
                    onChanged();
                } else {
                    this.cancelFloorReqAck_ = null;
                    this.cancelFloorReqAckBuilder_ = null;
                }
                return this;
            }

            public CancelFloorReqAck.Builder getCancelFloorReqAckBuilder() {
                onChanged();
                return getCancelFloorReqAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public CancelFloorReqAckOrBuilder getCancelFloorReqAckOrBuilder() {
                SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> singleFieldBuilderV3 = this.cancelFloorReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CancelFloorReqAck cancelFloorReqAck = this.cancelFloorReqAck_;
                return cancelFloorReqAck == null ? CancelFloorReqAck.getDefaultInstance() : cancelFloorReqAck;
            }

            private SingleFieldBuilderV3<CancelFloorReqAck, CancelFloorReqAck.Builder, CancelFloorReqAckOrBuilder> getCancelFloorReqAckFieldBuilder() {
                if (this.cancelFloorReqAckBuilder_ == null) {
                    this.cancelFloorReqAckBuilder_ = new SingleFieldBuilderV3<>(getCancelFloorReqAck(), getParentForChildren(), isClean());
                    this.cancelFloorReqAck_ = null;
                }
                return this.cancelFloorReqAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasUpdateReq() {
                return (this.updateReqBuilder_ == null && this.updateReq_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateReq getUpdateReq() {
                SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> singleFieldBuilderV3 = this.updateReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateReq updateReq = this.updateReq_;
                    return updateReq == null ? UpdateReq.getDefaultInstance() : updateReq;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setUpdateReq(UpdateReq updateReq) {
                SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> singleFieldBuilderV3 = this.updateReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(updateReq);
                } else {
                    if (updateReq == null) {
                        throw new NullPointerException();
                    }
                    this.updateReq_ = updateReq;
                    onChanged();
                }
                return this;
            }

            public Builder setUpdateReq(UpdateReq.Builder builder) {
                SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> singleFieldBuilderV3 = this.updateReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.updateReq_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeUpdateReq(UpdateReq updateReq) {
                SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> singleFieldBuilderV3 = this.updateReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateReq updateReq2 = this.updateReq_;
                    if (updateReq2 != null) {
                        this.updateReq_ = UpdateReq.newBuilder(updateReq2).mergeFrom(updateReq).buildPartial();
                    } else {
                        this.updateReq_ = updateReq;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(updateReq);
                }
                return this;
            }

            public Builder clearUpdateReq() {
                if (this.updateReqBuilder_ == null) {
                    this.updateReq_ = null;
                    onChanged();
                } else {
                    this.updateReq_ = null;
                    this.updateReqBuilder_ = null;
                }
                return this;
            }

            public UpdateReq.Builder getUpdateReqBuilder() {
                onChanged();
                return getUpdateReqFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateReqOrBuilder getUpdateReqOrBuilder() {
                SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> singleFieldBuilderV3 = this.updateReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UpdateReq updateReq = this.updateReq_;
                return updateReq == null ? UpdateReq.getDefaultInstance() : updateReq;
            }

            private SingleFieldBuilderV3<UpdateReq, UpdateReq.Builder, UpdateReqOrBuilder> getUpdateReqFieldBuilder() {
                if (this.updateReqBuilder_ == null) {
                    this.updateReqBuilder_ = new SingleFieldBuilderV3<>(getUpdateReq(), getParentForChildren(), isClean());
                    this.updateReq_ = null;
                }
                return this.updateReqBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasUpdateReqAck() {
                return (this.updateReqAckBuilder_ == null && this.updateReqAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateReqAck getUpdateReqAck() {
                SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> singleFieldBuilderV3 = this.updateReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateReqAck updateReqAck = this.updateReqAck_;
                    return updateReqAck == null ? UpdateReqAck.getDefaultInstance() : updateReqAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setUpdateReqAck(UpdateReqAck updateReqAck) {
                SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> singleFieldBuilderV3 = this.updateReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(updateReqAck);
                } else {
                    if (updateReqAck == null) {
                        throw new NullPointerException();
                    }
                    this.updateReqAck_ = updateReqAck;
                    onChanged();
                }
                return this;
            }

            public Builder setUpdateReqAck(UpdateReqAck.Builder builder) {
                SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> singleFieldBuilderV3 = this.updateReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.updateReqAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeUpdateReqAck(UpdateReqAck updateReqAck) {
                SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> singleFieldBuilderV3 = this.updateReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateReqAck updateReqAck2 = this.updateReqAck_;
                    if (updateReqAck2 != null) {
                        this.updateReqAck_ = UpdateReqAck.newBuilder(updateReqAck2).mergeFrom(updateReqAck).buildPartial();
                    } else {
                        this.updateReqAck_ = updateReqAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(updateReqAck);
                }
                return this;
            }

            public Builder clearUpdateReqAck() {
                if (this.updateReqAckBuilder_ == null) {
                    this.updateReqAck_ = null;
                    onChanged();
                } else {
                    this.updateReqAck_ = null;
                    this.updateReqAckBuilder_ = null;
                }
                return this;
            }

            public UpdateReqAck.Builder getUpdateReqAckBuilder() {
                onChanged();
                return getUpdateReqAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateReqAckOrBuilder getUpdateReqAckOrBuilder() {
                SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> singleFieldBuilderV3 = this.updateReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UpdateReqAck updateReqAck = this.updateReqAck_;
                return updateReqAck == null ? UpdateReqAck.getDefaultInstance() : updateReqAck;
            }

            private SingleFieldBuilderV3<UpdateReqAck, UpdateReqAck.Builder, UpdateReqAckOrBuilder> getUpdateReqAckFieldBuilder() {
                if (this.updateReqAckBuilder_ == null) {
                    this.updateReqAckBuilder_ = new SingleFieldBuilderV3<>(getUpdateReqAck(), getParentForChildren(), isClean());
                    this.updateReqAck_ = null;
                }
                return this.updateReqAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasUpdateStateReq() {
                return (this.updateStateReqBuilder_ == null && this.updateStateReq_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateStateReq getUpdateStateReq() {
                SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> singleFieldBuilderV3 = this.updateStateReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateStateReq updateStateReq = this.updateStateReq_;
                    return updateStateReq == null ? UpdateStateReq.getDefaultInstance() : updateStateReq;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setUpdateStateReq(UpdateStateReq updateStateReq) {
                SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> singleFieldBuilderV3 = this.updateStateReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(updateStateReq);
                } else {
                    if (updateStateReq == null) {
                        throw new NullPointerException();
                    }
                    this.updateStateReq_ = updateStateReq;
                    onChanged();
                }
                return this;
            }

            public Builder setUpdateStateReq(UpdateStateReq.Builder builder) {
                SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> singleFieldBuilderV3 = this.updateStateReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.updateStateReq_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeUpdateStateReq(UpdateStateReq updateStateReq) {
                SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> singleFieldBuilderV3 = this.updateStateReqBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateStateReq updateStateReq2 = this.updateStateReq_;
                    if (updateStateReq2 != null) {
                        this.updateStateReq_ = UpdateStateReq.newBuilder(updateStateReq2).mergeFrom(updateStateReq).buildPartial();
                    } else {
                        this.updateStateReq_ = updateStateReq;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(updateStateReq);
                }
                return this;
            }

            public Builder clearUpdateStateReq() {
                if (this.updateStateReqBuilder_ == null) {
                    this.updateStateReq_ = null;
                    onChanged();
                } else {
                    this.updateStateReq_ = null;
                    this.updateStateReqBuilder_ = null;
                }
                return this;
            }

            public UpdateStateReq.Builder getUpdateStateReqBuilder() {
                onChanged();
                return getUpdateStateReqFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateStateReqOrBuilder getUpdateStateReqOrBuilder() {
                SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> singleFieldBuilderV3 = this.updateStateReqBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UpdateStateReq updateStateReq = this.updateStateReq_;
                return updateStateReq == null ? UpdateStateReq.getDefaultInstance() : updateStateReq;
            }

            private SingleFieldBuilderV3<UpdateStateReq, UpdateStateReq.Builder, UpdateStateReqOrBuilder> getUpdateStateReqFieldBuilder() {
                if (this.updateStateReqBuilder_ == null) {
                    this.updateStateReqBuilder_ = new SingleFieldBuilderV3<>(getUpdateStateReq(), getParentForChildren(), isClean());
                    this.updateStateReq_ = null;
                }
                return this.updateStateReqBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasUpdateStateReqAck() {
                return (this.updateStateReqAckBuilder_ == null && this.updateStateReqAck_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateStateReqAck getUpdateStateReqAck() {
                SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> singleFieldBuilderV3 = this.updateStateReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateStateReqAck updateStateReqAck = this.updateStateReqAck_;
                    return updateStateReqAck == null ? UpdateStateReqAck.getDefaultInstance() : updateStateReqAck;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setUpdateStateReqAck(UpdateStateReqAck updateStateReqAck) {
                SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> singleFieldBuilderV3 = this.updateStateReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(updateStateReqAck);
                } else {
                    if (updateStateReqAck == null) {
                        throw new NullPointerException();
                    }
                    this.updateStateReqAck_ = updateStateReqAck;
                    onChanged();
                }
                return this;
            }

            public Builder setUpdateStateReqAck(UpdateStateReqAck.Builder builder) {
                SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> singleFieldBuilderV3 = this.updateStateReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.updateStateReqAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeUpdateStateReqAck(UpdateStateReqAck updateStateReqAck) {
                SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> singleFieldBuilderV3 = this.updateStateReqAckBuilder_;
                if (singleFieldBuilderV3 == null) {
                    UpdateStateReqAck updateStateReqAck2 = this.updateStateReqAck_;
                    if (updateStateReqAck2 != null) {
                        this.updateStateReqAck_ = UpdateStateReqAck.newBuilder(updateStateReqAck2).mergeFrom(updateStateReqAck).buildPartial();
                    } else {
                        this.updateStateReqAck_ = updateStateReqAck;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(updateStateReqAck);
                }
                return this;
            }

            public Builder clearUpdateStateReqAck() {
                if (this.updateStateReqAckBuilder_ == null) {
                    this.updateStateReqAck_ = null;
                    onChanged();
                } else {
                    this.updateStateReqAck_ = null;
                    this.updateStateReqAckBuilder_ = null;
                }
                return this;
            }

            public UpdateStateReqAck.Builder getUpdateStateReqAckBuilder() {
                onChanged();
                return getUpdateStateReqAckFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public UpdateStateReqAckOrBuilder getUpdateStateReqAckOrBuilder() {
                SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> singleFieldBuilderV3 = this.updateStateReqAckBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                UpdateStateReqAck updateStateReqAck = this.updateStateReqAck_;
                return updateStateReqAck == null ? UpdateStateReqAck.getDefaultInstance() : updateStateReqAck;
            }

            private SingleFieldBuilderV3<UpdateStateReqAck, UpdateStateReqAck.Builder, UpdateStateReqAckOrBuilder> getUpdateStateReqAckFieldBuilder() {
                if (this.updateStateReqAckBuilder_ == null) {
                    this.updateStateReqAckBuilder_ = new SingleFieldBuilderV3<>(getUpdateStateReqAck(), getParentForChildren(), isClean());
                    this.updateStateReqAck_ = null;
                }
                return this.updateStateReqAckBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public boolean hasCallTargetFloor() {
                return (this.callTargetFloorBuilder_ == null && this.callTargetFloor_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public CallTargetFloor getCallTargetFloor() {
                SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> singleFieldBuilderV3 = this.callTargetFloorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallTargetFloor callTargetFloor = this.callTargetFloor_;
                    return callTargetFloor == null ? CallTargetFloor.getDefaultInstance() : callTargetFloor;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCallTargetFloor(CallTargetFloor callTargetFloor) {
                SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> singleFieldBuilderV3 = this.callTargetFloorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(callTargetFloor);
                } else {
                    if (callTargetFloor == null) {
                        throw new NullPointerException();
                    }
                    this.callTargetFloor_ = callTargetFloor;
                    onChanged();
                }
                return this;
            }

            public Builder setCallTargetFloor(CallTargetFloor.Builder builder) {
                SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> singleFieldBuilderV3 = this.callTargetFloorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.callTargetFloor_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCallTargetFloor(CallTargetFloor callTargetFloor) {
                SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> singleFieldBuilderV3 = this.callTargetFloorBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallTargetFloor callTargetFloor2 = this.callTargetFloor_;
                    if (callTargetFloor2 != null) {
                        this.callTargetFloor_ = CallTargetFloor.newBuilder(callTargetFloor2).mergeFrom(callTargetFloor).buildPartial();
                    } else {
                        this.callTargetFloor_ = callTargetFloor;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(callTargetFloor);
                }
                return this;
            }

            public Builder clearCallTargetFloor() {
                if (this.callTargetFloorBuilder_ == null) {
                    this.callTargetFloor_ = null;
                    onChanged();
                } else {
                    this.callTargetFloor_ = null;
                    this.callTargetFloorBuilder_ = null;
                }
                return this;
            }

            public CallTargetFloor.Builder getCallTargetFloorBuilder() {
                onChanged();
                return getCallTargetFloorFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvMessageOrBuilder
            public CallTargetFloorOrBuilder getCallTargetFloorOrBuilder() {
                SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> singleFieldBuilderV3 = this.callTargetFloorBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CallTargetFloor callTargetFloor = this.callTargetFloor_;
                return callTargetFloor == null ? CallTargetFloor.getDefaultInstance() : callTargetFloor;
            }

            private SingleFieldBuilderV3<CallTargetFloor, CallTargetFloor.Builder, CallTargetFloorOrBuilder> getCallTargetFloorFieldBuilder() {
                if (this.callTargetFloorBuilder_ == null) {
                    this.callTargetFloorBuilder_ = new SingleFieldBuilderV3<>(getCallTargetFloor(), getParentForChildren(), isClean());
                    this.callTargetFloor_ = null;
                }
                return this.callTargetFloorBuilder_;
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

        public static ElvMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvMessage> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvMessage> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class AckResult extends GeneratedMessageV3 implements AckResultOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        public static final int CUR_ROBOT_ID_FIELD_NUMBER = 2;
        private static final AckResult DEFAULT_INSTANCE = new AckResult();
        private static final Parser<AckResult> PARSER = new AbstractParser<AckResult>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResult.1
            @Override // com.google.protobuf.Parser
            public AckResult parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new AckResult(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int code_;
        private volatile Object curRobotId_;
        private byte memoizedIsInitialized;

        private AckResult(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private AckResult() {
            this.memoizedIsInitialized = (byte) -1;
            this.code_ = 0;
            this.curRobotId_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private AckResult(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.code_ = codedInputStream.readEnum();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.curRobotId_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5825xff036044;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5826x7b4002c2.ensureFieldAccessorsInitialized(AckResult.class, Builder.class);
        }

        /* loaded from: classes5.dex */
        public enum AckCode implements ProtocolMessageEnum {
            Success(0),
            NoConnected(1),
            InWorking(2),
            InPause(3),
            OtherFail(4),
            Updating(5),
            UNRECOGNIZED(-1);

            public static final int InPause_VALUE = 3;
            public static final int InWorking_VALUE = 2;
            public static final int NoConnected_VALUE = 1;
            public static final int OtherFail_VALUE = 4;
            public static final int Success_VALUE = 0;
            public static final int Updating_VALUE = 5;
            private final int value;
            private static final Internal.EnumLiteMap<AckCode> internalValueMap = new Internal.EnumLiteMap<AckCode>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResult.AckCode.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AckCode findValueByNumber(int i) {
                    return AckCode.forNumber(i);
                }
            };
            private static final AckCode[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static AckCode valueOf(int i) {
                return forNumber(i);
            }

            public static AckCode forNumber(int i) {
                if (i == 0) {
                    return Success;
                }
                if (i == 1) {
                    return NoConnected;
                }
                if (i == 2) {
                    return InWorking;
                }
                if (i == 3) {
                    return InPause;
                }
                if (i == 4) {
                    return OtherFail;
                }
                if (i != 5) {
                    return null;
                }
                return Updating;
            }

            public static Internal.EnumLiteMap<AckCode> internalGetValueMap() {
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
                return AckResult.getDescriptor().getEnumTypes().get(0);
            }

            public static AckCode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            AckCode(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
        public int getCodeValue() {
            return this.code_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
        public AckCode getCode() {
            AckCode valueOf = AckCode.valueOf(this.code_);
            return valueOf == null ? AckCode.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
        public String getCurRobotId() {
            Object obj = this.curRobotId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.curRobotId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
        public ByteString getCurRobotIdBytes() {
            Object obj = this.curRobotId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.curRobotId_ = copyFromUtf8;
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
            if (this.code_ != AckCode.Success.getNumber()) {
                codedOutputStream.writeEnum(1, this.code_);
            }
            if (!getCurRobotIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.curRobotId_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeEnumSize = this.code_ != AckCode.Success.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.code_) : 0;
            if (!getCurRobotIdBytes().isEmpty()) {
                computeEnumSize += GeneratedMessageV3.computeStringSize(2, this.curRobotId_);
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
            if (!(obj instanceof AckResult)) {
                return super.equals(obj);
            }
            AckResult ackResult = (AckResult) obj;
            return ((this.code_ == ackResult.code_) && getCurRobotId().equals(ackResult.getCurRobotId())) && this.unknownFields.equals(ackResult.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.code_) * 37) + 2) * 53) + getCurRobotId().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static AckResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static AckResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static AckResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static AckResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static AckResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static AckResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static AckResult parseFrom(InputStream inputStream) throws IOException {
            return (AckResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static AckResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AckResult) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AckResult parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AckResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static AckResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AckResult) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static AckResult parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AckResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static AckResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AckResult) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AckResult ackResult) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(ackResult);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AckResultOrBuilder {
            private int code_;
            private Object curRobotId_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5825xff036044;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5826x7b4002c2.ensureFieldAccessorsInitialized(AckResult.class, Builder.class);
            }

            private Builder() {
                this.code_ = 0;
                this.curRobotId_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.code_ = 0;
                this.curRobotId_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = AckResult.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.code_ = 0;
                this.curRobotId_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5825xff036044;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public AckResult getDefaultInstanceForType() {
                return AckResult.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AckResult build() {
                AckResult buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public AckResult buildPartial() {
                AckResult ackResult = new AckResult(this);
                ackResult.code_ = this.code_;
                ackResult.curRobotId_ = this.curRobotId_;
                onBuilt();
                return ackResult;
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
                if (message instanceof AckResult) {
                    return mergeFrom((AckResult) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(AckResult ackResult) {
                if (ackResult == AckResult.getDefaultInstance()) {
                    return this;
                }
                if (ackResult.code_ != 0) {
                    setCodeValue(ackResult.getCodeValue());
                }
                if (!ackResult.getCurRobotId().isEmpty()) {
                    this.curRobotId_ = ackResult.curRobotId_;
                    onChanged();
                }
                mergeUnknownFields(ackResult.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                AckResult ackResult = null;
                try {
                    try {
                        AckResult ackResult2 = (AckResult) AckResult.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (ackResult2 != null) {
                            mergeFrom(ackResult2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        AckResult ackResult3 = (AckResult) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            ackResult = ackResult3;
                            if (ackResult != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (ackResult != null) {
                        mergeFrom(ackResult);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
            public int getCodeValue() {
                return this.code_;
            }

            public Builder setCodeValue(int i) {
                this.code_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
            public AckCode getCode() {
                AckCode valueOf = AckCode.valueOf(this.code_);
                return valueOf == null ? AckCode.UNRECOGNIZED : valueOf;
            }

            public Builder setCode(AckCode ackCode) {
                if (ackCode == null) {
                    throw new NullPointerException();
                }
                this.code_ = ackCode.getNumber();
                onChanged();
                return this;
            }

            public Builder clearCode() {
                this.code_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
            public String getCurRobotId() {
                Object obj = this.curRobotId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.curRobotId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.AckResultOrBuilder
            public ByteString getCurRobotIdBytes() {
                Object obj = this.curRobotId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.curRobotId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurRobotId(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.curRobotId_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurRobotId() {
                this.curRobotId_ = AckResult.getDefaultInstance().getCurRobotId();
                onChanged();
                return this;
            }

            public Builder setCurRobotIdBytes(ByteString byteString) {
                if (byteString != null) {
                    AckResult.checkByteStringIsUtf8(byteString);
                    this.curRobotId_ = byteString;
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

        public static AckResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AckResult> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<AckResult> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public AckResult getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class ConnectReq extends GeneratedMessageV3 implements ConnectReqOrBuilder {
        private static final ConnectReq DEFAULT_INSTANCE = new ConnectReq();
        private static final Parser<ConnectReq> PARSER = new AbstractParser<ConnectReq>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReq.1
            @Override // com.google.protobuf.Parser
            public ConnectReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ConnectReq(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int WEIGHT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int weight_;

        private ConnectReq(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ConnectReq() {
            this.memoizedIsInitialized = (byte) -1;
            this.weight_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ConnectReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.weight_ = codedInputStream.readInt32();
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
            return ElevatorProtocol.f5835x67828bbe;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5836x1c01143c.ensureFieldAccessorsInitialized(ConnectReq.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqOrBuilder
        public int getWeight() {
            return this.weight_;
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
            int i = this.weight_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.weight_;
            int computeInt32Size = (i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeInt32Size;
            return computeInt32Size;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ConnectReq)) {
                return super.equals(obj);
            }
            ConnectReq connectReq = (ConnectReq) obj;
            return (getWeight() == connectReq.getWeight()) && this.unknownFields.equals(connectReq.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getWeight()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ConnectReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ConnectReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ConnectReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ConnectReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ConnectReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ConnectReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ConnectReq parseFrom(InputStream inputStream) throws IOException {
            return (ConnectReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ConnectReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ConnectReq parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConnectReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ConnectReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ConnectReq parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConnectReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ConnectReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConnectReq connectReq) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(connectReq);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConnectReqOrBuilder {
            private int weight_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5835x67828bbe;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5836x1c01143c.ensureFieldAccessorsInitialized(ConnectReq.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ConnectReq.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.weight_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5835x67828bbe;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ConnectReq getDefaultInstanceForType() {
                return ConnectReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ConnectReq build() {
                ConnectReq buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ConnectReq buildPartial() {
                ConnectReq connectReq = new ConnectReq(this);
                connectReq.weight_ = this.weight_;
                onBuilt();
                return connectReq;
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
                if (message instanceof ConnectReq) {
                    return mergeFrom((ConnectReq) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ConnectReq connectReq) {
                if (connectReq == ConnectReq.getDefaultInstance()) {
                    return this;
                }
                if (connectReq.getWeight() != 0) {
                    setWeight(connectReq.getWeight());
                }
                mergeUnknownFields(connectReq.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ConnectReq connectReq = null;
                try {
                    try {
                        ConnectReq connectReq2 = (ConnectReq) ConnectReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (connectReq2 != null) {
                            mergeFrom(connectReq2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ConnectReq connectReq3 = (ConnectReq) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            connectReq = connectReq3;
                            if (connectReq != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (connectReq != null) {
                        mergeFrom(connectReq);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqOrBuilder
            public int getWeight() {
                return this.weight_;
            }

            public Builder setWeight(int i) {
                this.weight_ = i;
                onChanged();
                return this;
            }

            public Builder clearWeight() {
                this.weight_ = 0;
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

        public static ConnectReq getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConnectReq> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ConnectReq> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ConnectReq getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class ConnectReqAck extends GeneratedMessageV3 implements ConnectReqAckOrBuilder {
        public static final int ELV_VER_FIELD_NUMBER = 3;
        public static final int HARDWARE_TYPE_FIELD_NUMBER = 5;
        public static final int PROTOCOL_VER_FIELD_NUMBER = 4;
        public static final int RESULT_FIELD_NUMBER = 1;
        public static final int WEIGHT_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object elvVer_;
        private volatile Object hardwareType_;
        private byte memoizedIsInitialized;
        private int protocolVer_;
        private AckResult result_;
        private int weight_;
        private static final ConnectReqAck DEFAULT_INSTANCE = new ConnectReqAck();
        private static final Parser<ConnectReqAck> PARSER = new AbstractParser<ConnectReqAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAck.1
            @Override // com.google.protobuf.Parser
            public ConnectReqAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ConnectReqAck(codedInputStream, extensionRegistryLite);
            }
        };

        private ConnectReqAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ConnectReqAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.weight_ = 0;
            this.elvVer_ = "";
            this.protocolVer_ = 0;
            this.hardwareType_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ConnectReqAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                    this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.result_);
                                        this.result_ = builder.buildPartial();
                                    }
                                } else if (readTag == 16) {
                                    this.weight_ = codedInputStream.readInt32();
                                } else if (readTag == 26) {
                                    this.elvVer_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 32) {
                                    this.protocolVer_ = codedInputStream.readInt32();
                                } else if (readTag != 42) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.hardwareType_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5833x7ebc5c55;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5834xf8c85dd3.ensureFieldAccessorsInitialized(ConnectReqAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public int getWeight() {
            return this.weight_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public String getElvVer() {
            Object obj = this.elvVer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.elvVer_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public ByteString getElvVerBytes() {
            Object obj = this.elvVer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.elvVer_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public int getProtocolVer() {
            return this.protocolVer_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public String getHardwareType() {
            Object obj = this.hardwareType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.hardwareType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
        public ByteString getHardwareTypeBytes() {
            Object obj = this.hardwareType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.hardwareType_ = copyFromUtf8;
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            int i = this.weight_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            if (!getElvVerBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.elvVer_);
            }
            int i2 = this.protocolVer_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(4, i2);
            }
            if (!getHardwareTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.hardwareType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0;
            int i2 = this.weight_;
            if (i2 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(2, i2);
            }
            if (!getElvVerBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(3, this.elvVer_);
            }
            int i3 = this.protocolVer_;
            if (i3 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(4, i3);
            }
            if (!getHardwareTypeBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(5, this.hardwareType_);
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
            if (!(obj instanceof ConnectReqAck)) {
                return super.equals(obj);
            }
            ConnectReqAck connectReqAck = (ConnectReqAck) obj;
            boolean z = hasResult() == connectReqAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(connectReqAck.getResult());
            }
            return ((((z && getWeight() == connectReqAck.getWeight()) && getElvVer().equals(connectReqAck.getElvVer())) && getProtocolVer() == connectReqAck.getProtocolVer()) && getHardwareType().equals(connectReqAck.getHardwareType())) && this.unknownFields.equals(connectReqAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int weight = (((((((((((((((((hashCode * 37) + 2) * 53) + getWeight()) * 37) + 3) * 53) + getElvVer().hashCode()) * 37) + 4) * 53) + getProtocolVer()) * 37) + 5) * 53) + getHardwareType().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = weight;
            return weight;
        }

        public static ConnectReqAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ConnectReqAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ConnectReqAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ConnectReqAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ConnectReqAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ConnectReqAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ConnectReqAck parseFrom(InputStream inputStream) throws IOException {
            return (ConnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ConnectReqAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ConnectReqAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConnectReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ConnectReqAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ConnectReqAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ConnectReqAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConnectReqAck connectReqAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(connectReqAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConnectReqAckOrBuilder {
            private Object elvVer_;
            private Object hardwareType_;
            private int protocolVer_;
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;
            private int weight_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5833x7ebc5c55;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5834xf8c85dd3.ensureFieldAccessorsInitialized(ConnectReqAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                this.elvVer_ = "";
                this.hardwareType_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                this.elvVer_ = "";
                this.hardwareType_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ConnectReqAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                this.weight_ = 0;
                this.elvVer_ = "";
                this.protocolVer_ = 0;
                this.hardwareType_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5833x7ebc5c55;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ConnectReqAck getDefaultInstanceForType() {
                return ConnectReqAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ConnectReqAck build() {
                ConnectReqAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ConnectReqAck buildPartial() {
                ConnectReqAck connectReqAck = new ConnectReqAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    connectReqAck.result_ = this.result_;
                } else {
                    connectReqAck.result_ = singleFieldBuilderV3.build();
                }
                connectReqAck.weight_ = this.weight_;
                connectReqAck.elvVer_ = this.elvVer_;
                connectReqAck.protocolVer_ = this.protocolVer_;
                connectReqAck.hardwareType_ = this.hardwareType_;
                onBuilt();
                return connectReqAck;
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
                if (message instanceof ConnectReqAck) {
                    return mergeFrom((ConnectReqAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ConnectReqAck connectReqAck) {
                if (connectReqAck == ConnectReqAck.getDefaultInstance()) {
                    return this;
                }
                if (connectReqAck.hasResult()) {
                    mergeResult(connectReqAck.getResult());
                }
                if (connectReqAck.getWeight() != 0) {
                    setWeight(connectReqAck.getWeight());
                }
                if (!connectReqAck.getElvVer().isEmpty()) {
                    this.elvVer_ = connectReqAck.elvVer_;
                    onChanged();
                }
                if (connectReqAck.getProtocolVer() != 0) {
                    setProtocolVer(connectReqAck.getProtocolVer());
                }
                if (!connectReqAck.getHardwareType().isEmpty()) {
                    this.hardwareType_ = connectReqAck.hardwareType_;
                    onChanged();
                }
                mergeUnknownFields(connectReqAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ConnectReqAck connectReqAck = null;
                try {
                    try {
                        ConnectReqAck connectReqAck2 = (ConnectReqAck) ConnectReqAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (connectReqAck2 != null) {
                            mergeFrom(connectReqAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ConnectReqAck connectReqAck3 = (ConnectReqAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            connectReqAck = connectReqAck3;
                            if (connectReqAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (connectReqAck != null) {
                        mergeFrom(connectReqAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public int getWeight() {
                return this.weight_;
            }

            public Builder setWeight(int i) {
                this.weight_ = i;
                onChanged();
                return this;
            }

            public Builder clearWeight() {
                this.weight_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public String getElvVer() {
                Object obj = this.elvVer_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.elvVer_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public ByteString getElvVerBytes() {
                Object obj = this.elvVer_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.elvVer_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setElvVer(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.elvVer_ = str;
                onChanged();
                return this;
            }

            public Builder clearElvVer() {
                this.elvVer_ = ConnectReqAck.getDefaultInstance().getElvVer();
                onChanged();
                return this;
            }

            public Builder setElvVerBytes(ByteString byteString) {
                if (byteString != null) {
                    ConnectReqAck.checkByteStringIsUtf8(byteString);
                    this.elvVer_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public int getProtocolVer() {
                return this.protocolVer_;
            }

            public Builder setProtocolVer(int i) {
                this.protocolVer_ = i;
                onChanged();
                return this;
            }

            public Builder clearProtocolVer() {
                this.protocolVer_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public String getHardwareType() {
                Object obj = this.hardwareType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.hardwareType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ConnectReqAckOrBuilder
            public ByteString getHardwareTypeBytes() {
                Object obj = this.hardwareType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.hardwareType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setHardwareType(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.hardwareType_ = str;
                onChanged();
                return this;
            }

            public Builder clearHardwareType() {
                this.hardwareType_ = ConnectReqAck.getDefaultInstance().getHardwareType();
                onChanged();
                return this;
            }

            public Builder setHardwareTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    ConnectReqAck.checkByteStringIsUtf8(byteString);
                    this.hardwareType_ = byteString;
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

        public static ConnectReqAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConnectReqAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ConnectReqAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ConnectReqAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class DisconnectReq extends GeneratedMessageV3 implements DisconnectReqOrBuilder {
        private static final DisconnectReq DEFAULT_INSTANCE = new DisconnectReq();
        private static final Parser<DisconnectReq> PARSER = new AbstractParser<DisconnectReq>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReq.1
            @Override // com.google.protobuf.Parser
            public DisconnectReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DisconnectReq(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private DisconnectReq(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DisconnectReq() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DisconnectReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
            return ElevatorProtocol.f5839x6ccc0068;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5840x40083ee6.ensureFieldAccessorsInitialized(DisconnectReq.class, Builder.class);
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
            if (obj instanceof DisconnectReq) {
                return this.unknownFields.equals(((DisconnectReq) obj).unknownFields);
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

        public static DisconnectReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static DisconnectReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DisconnectReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static DisconnectReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DisconnectReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static DisconnectReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DisconnectReq parseFrom(InputStream inputStream) throws IOException {
            return (DisconnectReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DisconnectReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DisconnectReq parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DisconnectReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DisconnectReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DisconnectReq parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DisconnectReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DisconnectReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DisconnectReq disconnectReq) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(disconnectReq);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DisconnectReqOrBuilder {
            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5839x6ccc0068;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5840x40083ee6.ensureFieldAccessorsInitialized(DisconnectReq.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DisconnectReq.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5839x6ccc0068;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DisconnectReq getDefaultInstanceForType() {
                return DisconnectReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DisconnectReq build() {
                DisconnectReq buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DisconnectReq buildPartial() {
                DisconnectReq disconnectReq = new DisconnectReq(this);
                onBuilt();
                return disconnectReq;
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
                if (message instanceof DisconnectReq) {
                    return mergeFrom((DisconnectReq) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DisconnectReq disconnectReq) {
                if (disconnectReq == DisconnectReq.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(disconnectReq.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                DisconnectReq disconnectReq = null;
                try {
                    try {
                        DisconnectReq disconnectReq2 = (DisconnectReq) DisconnectReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (disconnectReq2 != null) {
                            mergeFrom(disconnectReq2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        DisconnectReq disconnectReq3 = (DisconnectReq) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            disconnectReq = disconnectReq3;
                            if (disconnectReq != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (disconnectReq != null) {
                        mergeFrom(disconnectReq);
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

        public static DisconnectReq getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DisconnectReq> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DisconnectReq> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DisconnectReq getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class DisconnectReqAck extends GeneratedMessageV3 implements DisconnectReqAckOrBuilder {
        private static final DisconnectReqAck DEFAULT_INSTANCE = new DisconnectReqAck();
        private static final Parser<DisconnectReqAck> PARSER = new AbstractParser<DisconnectReqAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAck.1
            @Override // com.google.protobuf.Parser
            public DisconnectReqAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DisconnectReqAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private AckResult result_;

        private DisconnectReqAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DisconnectReqAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DisconnectReqAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.result_);
                                    this.result_ = builder.buildPartial();
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
            return ElevatorProtocol.f5837xbddbaf6b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5838x96c63ae9.ensureFieldAccessorsInitialized(DisconnectReqAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeMessageSize;
            return computeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DisconnectReqAck)) {
                return super.equals(obj);
            }
            DisconnectReqAck disconnectReqAck = (DisconnectReqAck) obj;
            boolean z = hasResult() == disconnectReqAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(disconnectReqAck.getResult());
            }
            return z && this.unknownFields.equals(disconnectReqAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static DisconnectReqAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static DisconnectReqAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DisconnectReqAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static DisconnectReqAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DisconnectReqAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static DisconnectReqAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DisconnectReqAck parseFrom(InputStream inputStream) throws IOException {
            return (DisconnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DisconnectReqAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DisconnectReqAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DisconnectReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DisconnectReqAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DisconnectReqAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DisconnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DisconnectReqAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DisconnectReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DisconnectReqAck disconnectReqAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(disconnectReqAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DisconnectReqAckOrBuilder {
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5837xbddbaf6b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5838x96c63ae9.ensureFieldAccessorsInitialized(DisconnectReqAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DisconnectReqAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5837xbddbaf6b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DisconnectReqAck getDefaultInstanceForType() {
                return DisconnectReqAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DisconnectReqAck build() {
                DisconnectReqAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DisconnectReqAck buildPartial() {
                DisconnectReqAck disconnectReqAck = new DisconnectReqAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    disconnectReqAck.result_ = this.result_;
                } else {
                    disconnectReqAck.result_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return disconnectReqAck;
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
                if (message instanceof DisconnectReqAck) {
                    return mergeFrom((DisconnectReqAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DisconnectReqAck disconnectReqAck) {
                if (disconnectReqAck == DisconnectReqAck.getDefaultInstance()) {
                    return this;
                }
                if (disconnectReqAck.hasResult()) {
                    mergeResult(disconnectReqAck.getResult());
                }
                mergeUnknownFields(disconnectReqAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                DisconnectReqAck disconnectReqAck = null;
                try {
                    try {
                        DisconnectReqAck disconnectReqAck2 = (DisconnectReqAck) DisconnectReqAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (disconnectReqAck2 != null) {
                            mergeFrom(disconnectReqAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        DisconnectReqAck disconnectReqAck3 = (DisconnectReqAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            disconnectReqAck = disconnectReqAck3;
                            if (disconnectReqAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (disconnectReqAck != null) {
                        mergeFrom(disconnectReqAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.DisconnectReqAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
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

        public static DisconnectReqAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DisconnectReqAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DisconnectReqAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DisconnectReqAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class HeartBeat extends GeneratedMessageV3 implements HeartBeatOrBuilder {
        public static final int DESTFLOOR_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object destFloor_;
        private byte memoizedIsInitialized;
        private int state_;
        private static final HeartBeat DEFAULT_INSTANCE = new HeartBeat();
        private static final Parser<HeartBeat> PARSER = new AbstractParser<HeartBeat>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeat.1
            @Override // com.google.protobuf.Parser
            public HeartBeat parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HeartBeat(codedInputStream, extensionRegistryLite);
            }
        };

        private HeartBeat(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HeartBeat() {
            this.memoizedIsInitialized = (byte) -1;
            this.state_ = 0;
            this.destFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private HeartBeat(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.state_ = codedInputStream.readEnum();
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
            return ElevatorProtocol.f5853xfe5cb36e;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5854x94d08bec.ensureFieldAccessorsInitialized(HeartBeat.class, Builder.class);
        }

        /* loaded from: classes5.dex */
        public enum RobotState implements ProtocolMessageEnum {
            CallElv(0),
            WaitElv(1),
            EnterElv(2),
            InElv(3),
            LeaveElv(4),
            NoNeedElv(5),
            UNRECOGNIZED(-1);

            public static final int CallElv_VALUE = 0;
            public static final int EnterElv_VALUE = 2;
            public static final int InElv_VALUE = 3;
            public static final int LeaveElv_VALUE = 4;
            public static final int NoNeedElv_VALUE = 5;
            public static final int WaitElv_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<RobotState> internalValueMap = new Internal.EnumLiteMap<RobotState>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeat.RobotState.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public RobotState findValueByNumber(int i) {
                    return RobotState.forNumber(i);
                }
            };
            private static final RobotState[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static RobotState valueOf(int i) {
                return forNumber(i);
            }

            public static RobotState forNumber(int i) {
                if (i == 0) {
                    return CallElv;
                }
                if (i == 1) {
                    return WaitElv;
                }
                if (i == 2) {
                    return EnterElv;
                }
                if (i == 3) {
                    return InElv;
                }
                if (i == 4) {
                    return LeaveElv;
                }
                if (i != 5) {
                    return null;
                }
                return NoNeedElv;
            }

            public static Internal.EnumLiteMap<RobotState> internalGetValueMap() {
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
                return HeartBeat.getDescriptor().getEnumTypes().get(0);
            }

            public static RobotState valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            RobotState(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
        public int getStateValue() {
            return this.state_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
        public RobotState getState() {
            RobotState valueOf = RobotState.valueOf(this.state_);
            return valueOf == null ? RobotState.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
        public String getDestFloor() {
            Object obj = this.destFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.destFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
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
            if (this.state_ != RobotState.CallElv.getNumber()) {
                codedOutputStream.writeEnum(1, this.state_);
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
            int computeEnumSize = this.state_ != RobotState.CallElv.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.state_) : 0;
            if (!getDestFloorBytes().isEmpty()) {
                computeEnumSize += GeneratedMessageV3.computeStringSize(2, this.destFloor_);
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
            if (!(obj instanceof HeartBeat)) {
                return super.equals(obj);
            }
            HeartBeat heartBeat = (HeartBeat) obj;
            return ((this.state_ == heartBeat.state_) && getDestFloor().equals(heartBeat.getDestFloor())) && this.unknownFields.equals(heartBeat.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.state_) * 37) + 2) * 53) + getDestFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static HeartBeat parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static HeartBeat parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HeartBeat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static HeartBeat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HeartBeat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static HeartBeat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HeartBeat parseFrom(InputStream inputStream) throws IOException {
            return (HeartBeat) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HeartBeat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (HeartBeat) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeartBeat parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (HeartBeat) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HeartBeat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (HeartBeat) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeartBeat parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (HeartBeat) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HeartBeat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (HeartBeat) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(HeartBeat heartBeat) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(heartBeat);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeartBeatOrBuilder {
            private Object destFloor_;
            private int state_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5853xfe5cb36e;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5854x94d08bec.ensureFieldAccessorsInitialized(HeartBeat.class, Builder.class);
            }

            private Builder() {
                this.state_ = 0;
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.state_ = 0;
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HeartBeat.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.state_ = 0;
                this.destFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5853xfe5cb36e;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public HeartBeat getDefaultInstanceForType() {
                return HeartBeat.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public HeartBeat build() {
                HeartBeat buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public HeartBeat buildPartial() {
                HeartBeat heartBeat = new HeartBeat(this);
                heartBeat.state_ = this.state_;
                heartBeat.destFloor_ = this.destFloor_;
                onBuilt();
                return heartBeat;
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
                if (message instanceof HeartBeat) {
                    return mergeFrom((HeartBeat) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HeartBeat heartBeat) {
                if (heartBeat == HeartBeat.getDefaultInstance()) {
                    return this;
                }
                if (heartBeat.state_ != 0) {
                    setStateValue(heartBeat.getStateValue());
                }
                if (!heartBeat.getDestFloor().isEmpty()) {
                    this.destFloor_ = heartBeat.destFloor_;
                    onChanged();
                }
                mergeUnknownFields(heartBeat.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                HeartBeat heartBeat = null;
                try {
                    try {
                        HeartBeat heartBeat2 = (HeartBeat) HeartBeat.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (heartBeat2 != null) {
                            mergeFrom(heartBeat2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        HeartBeat heartBeat3 = (HeartBeat) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            heartBeat = heartBeat3;
                            if (heartBeat != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (heartBeat != null) {
                        mergeFrom(heartBeat);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
            public int getStateValue() {
                return this.state_;
            }

            public Builder setStateValue(int i) {
                this.state_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
            public RobotState getState() {
                RobotState valueOf = RobotState.valueOf(this.state_);
                return valueOf == null ? RobotState.UNRECOGNIZED : valueOf;
            }

            public Builder setState(RobotState robotState) {
                if (robotState == null) {
                    throw new NullPointerException();
                }
                this.state_ = robotState.getNumber();
                onChanged();
                return this;
            }

            public Builder clearState() {
                this.state_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
            public String getDestFloor() {
                Object obj = this.destFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.destFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatOrBuilder
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
                this.destFloor_ = HeartBeat.getDefaultInstance().getDestFloor();
                onChanged();
                return this;
            }

            public Builder setDestFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    HeartBeat.checkByteStringIsUtf8(byteString);
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

        public static HeartBeat getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeartBeat> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<HeartBeat> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HeartBeat getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class HeartBeatAck extends GeneratedMessageV3 implements HeartBeatAckOrBuilder {
        public static final int DESTFLOOR_FIELD_NUMBER = 2;
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object destFloor_;
        private byte memoizedIsInitialized;
        private AckResult result_;
        private static final HeartBeatAck DEFAULT_INSTANCE = new HeartBeatAck();
        private static final Parser<HeartBeatAck> PARSER = new AbstractParser<HeartBeatAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAck.1
            @Override // com.google.protobuf.Parser
            public HeartBeatAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HeartBeatAck(codedInputStream, extensionRegistryLite);
            }
        };

        private HeartBeatAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HeartBeatAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.destFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private HeartBeatAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                    this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.result_);
                                        this.result_ = builder.buildPartial();
                                    }
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.destFloor_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5851x53acd6a5;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5852xd0018823.ensureFieldAccessorsInitialized(HeartBeatAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
        public String getDestFloor() {
            Object obj = this.destFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.destFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
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
            int computeMessageSize = this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0;
            if (!getDestFloorBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(2, this.destFloor_);
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
            if (!(obj instanceof HeartBeatAck)) {
                return super.equals(obj);
            }
            HeartBeatAck heartBeatAck = (HeartBeatAck) obj;
            boolean z = hasResult() == heartBeatAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(heartBeatAck.getResult());
            }
            return (z && getDestFloor().equals(heartBeatAck.getDestFloor())) && this.unknownFields.equals(heartBeatAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int hashCode2 = (((((hashCode * 37) + 2) * 53) + getDestFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static HeartBeatAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static HeartBeatAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HeartBeatAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static HeartBeatAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HeartBeatAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static HeartBeatAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HeartBeatAck parseFrom(InputStream inputStream) throws IOException {
            return (HeartBeatAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HeartBeatAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (HeartBeatAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeartBeatAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (HeartBeatAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HeartBeatAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (HeartBeatAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HeartBeatAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (HeartBeatAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HeartBeatAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (HeartBeatAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(HeartBeatAck heartBeatAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(heartBeatAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeartBeatAckOrBuilder {
            private Object destFloor_;
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5851x53acd6a5;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5852xd0018823.ensureFieldAccessorsInitialized(HeartBeatAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HeartBeatAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                this.destFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5851x53acd6a5;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public HeartBeatAck getDefaultInstanceForType() {
                return HeartBeatAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public HeartBeatAck build() {
                HeartBeatAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public HeartBeatAck buildPartial() {
                HeartBeatAck heartBeatAck = new HeartBeatAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    heartBeatAck.result_ = this.result_;
                } else {
                    heartBeatAck.result_ = singleFieldBuilderV3.build();
                }
                heartBeatAck.destFloor_ = this.destFloor_;
                onBuilt();
                return heartBeatAck;
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
                if (message instanceof HeartBeatAck) {
                    return mergeFrom((HeartBeatAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HeartBeatAck heartBeatAck) {
                if (heartBeatAck == HeartBeatAck.getDefaultInstance()) {
                    return this;
                }
                if (heartBeatAck.hasResult()) {
                    mergeResult(heartBeatAck.getResult());
                }
                if (!heartBeatAck.getDestFloor().isEmpty()) {
                    this.destFloor_ = heartBeatAck.destFloor_;
                    onChanged();
                }
                mergeUnknownFields(heartBeatAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                HeartBeatAck heartBeatAck = null;
                try {
                    try {
                        HeartBeatAck heartBeatAck2 = (HeartBeatAck) HeartBeatAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (heartBeatAck2 != null) {
                            mergeFrom(heartBeatAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        HeartBeatAck heartBeatAck3 = (HeartBeatAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            heartBeatAck = heartBeatAck3;
                            if (heartBeatAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (heartBeatAck != null) {
                        mergeFrom(heartBeatAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
            public String getDestFloor() {
                Object obj = this.destFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.destFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.HeartBeatAckOrBuilder
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
                this.destFloor_ = HeartBeatAck.getDefaultInstance().getDestFloor();
                onChanged();
                return this;
            }

            public Builder setDestFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    HeartBeatAck.checkByteStringIsUtf8(byteString);
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

        public static HeartBeatAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeartBeatAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<HeartBeatAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HeartBeatAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class GoFloorReq extends GeneratedMessageV3 implements GoFloorReqOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 2;
        public static final int CURFLOOR_FIELD_NUMBER = 3;
        public static final int DESTFLOOR_FIELD_NUMBER = 4;
        public static final int TARGETFLOOR_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int action_;
        private volatile Object curFloor_;
        private volatile Object destFloor_;
        private byte memoizedIsInitialized;
        private volatile Object targetFloor_;
        private static final GoFloorReq DEFAULT_INSTANCE = new GoFloorReq();
        private static final Parser<GoFloorReq> PARSER = new AbstractParser<GoFloorReq>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReq.1
            @Override // com.google.protobuf.Parser
            public GoFloorReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GoFloorReq(codedInputStream, extensionRegistryLite);
            }
        };

        private GoFloorReq(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GoFloorReq() {
            this.memoizedIsInitialized = (byte) -1;
            this.targetFloor_ = "";
            this.action_ = 0;
            this.curFloor_ = "";
            this.destFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private GoFloorReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.targetFloor_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.action_ = codedInputStream.readEnum();
                            } else if (readTag == 26) {
                                this.curFloor_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 34) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.destFloor_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5849xf3e59558;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5850x2e75e3d6.ensureFieldAccessorsInitialized(GoFloorReq.class, Builder.class);
        }

        /* loaded from: classes5.dex */
        public enum Action implements ProtocolMessageEnum {
            EnterElv(0),
            LeaveElv(1),
            UNRECOGNIZED(-1);

            public static final int EnterElv_VALUE = 0;
            public static final int LeaveElv_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<Action> internalValueMap = new Internal.EnumLiteMap<Action>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReq.Action.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Action findValueByNumber(int i) {
                    return Action.forNumber(i);
                }
            };
            private static final Action[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static Action valueOf(int i) {
                return forNumber(i);
            }

            public static Action forNumber(int i) {
                if (i == 0) {
                    return EnterElv;
                }
                if (i != 1) {
                    return null;
                }
                return LeaveElv;
            }

            public static Internal.EnumLiteMap<Action> internalGetValueMap() {
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
                return GoFloorReq.getDescriptor().getEnumTypes().get(0);
            }

            public static Action valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            Action(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public String getTargetFloor() {
            Object obj = this.targetFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.targetFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public ByteString getTargetFloorBytes() {
            Object obj = this.targetFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetFloor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public int getActionValue() {
            return this.action_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public Action getAction() {
            Action valueOf = Action.valueOf(this.action_);
            return valueOf == null ? Action.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public String getCurFloor() {
            Object obj = this.curFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.curFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public ByteString getCurFloorBytes() {
            Object obj = this.curFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.curFloor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
        public String getDestFloor() {
            Object obj = this.destFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.destFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
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
            if (!getTargetFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.targetFloor_);
            }
            if (this.action_ != Action.EnterElv.getNumber()) {
                codedOutputStream.writeEnum(2, this.action_);
            }
            if (!getCurFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.curFloor_);
            }
            if (!getDestFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.destFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getTargetFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.targetFloor_);
            if (this.action_ != Action.EnterElv.getNumber()) {
                computeStringSize += CodedOutputStream.computeEnumSize(2, this.action_);
            }
            if (!getCurFloorBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(3, this.curFloor_);
            }
            if (!getDestFloorBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(4, this.destFloor_);
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
            if (!(obj instanceof GoFloorReq)) {
                return super.equals(obj);
            }
            GoFloorReq goFloorReq = (GoFloorReq) obj;
            return ((((getTargetFloor().equals(goFloorReq.getTargetFloor())) && this.action_ == goFloorReq.action_) && getCurFloor().equals(goFloorReq.getCurFloor())) && getDestFloor().equals(goFloorReq.getDestFloor())) && this.unknownFields.equals(goFloorReq.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTargetFloor().hashCode()) * 37) + 2) * 53) + this.action_) * 37) + 3) * 53) + getCurFloor().hashCode()) * 37) + 4) * 53) + getDestFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static GoFloorReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static GoFloorReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GoFloorReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static GoFloorReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GoFloorReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static GoFloorReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GoFloorReq parseFrom(InputStream inputStream) throws IOException {
            return (GoFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GoFloorReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GoFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GoFloorReq parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GoFloorReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GoFloorReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GoFloorReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GoFloorReq parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GoFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GoFloorReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GoFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GoFloorReq goFloorReq) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(goFloorReq);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GoFloorReqOrBuilder {
            private int action_;
            private Object curFloor_;
            private Object destFloor_;
            private Object targetFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5849xf3e59558;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5850x2e75e3d6.ensureFieldAccessorsInitialized(GoFloorReq.class, Builder.class);
            }

            private Builder() {
                this.targetFloor_ = "";
                this.action_ = 0;
                this.curFloor_ = "";
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.targetFloor_ = "";
                this.action_ = 0;
                this.curFloor_ = "";
                this.destFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GoFloorReq.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.targetFloor_ = "";
                this.action_ = 0;
                this.curFloor_ = "";
                this.destFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5849xf3e59558;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public GoFloorReq getDefaultInstanceForType() {
                return GoFloorReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GoFloorReq build() {
                GoFloorReq buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GoFloorReq buildPartial() {
                GoFloorReq goFloorReq = new GoFloorReq(this);
                goFloorReq.targetFloor_ = this.targetFloor_;
                goFloorReq.action_ = this.action_;
                goFloorReq.curFloor_ = this.curFloor_;
                goFloorReq.destFloor_ = this.destFloor_;
                onBuilt();
                return goFloorReq;
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
                if (message instanceof GoFloorReq) {
                    return mergeFrom((GoFloorReq) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GoFloorReq goFloorReq) {
                if (goFloorReq == GoFloorReq.getDefaultInstance()) {
                    return this;
                }
                if (!goFloorReq.getTargetFloor().isEmpty()) {
                    this.targetFloor_ = goFloorReq.targetFloor_;
                    onChanged();
                }
                if (goFloorReq.action_ != 0) {
                    setActionValue(goFloorReq.getActionValue());
                }
                if (!goFloorReq.getCurFloor().isEmpty()) {
                    this.curFloor_ = goFloorReq.curFloor_;
                    onChanged();
                }
                if (!goFloorReq.getDestFloor().isEmpty()) {
                    this.destFloor_ = goFloorReq.destFloor_;
                    onChanged();
                }
                mergeUnknownFields(goFloorReq.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                GoFloorReq goFloorReq = null;
                try {
                    try {
                        GoFloorReq goFloorReq2 = (GoFloorReq) GoFloorReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (goFloorReq2 != null) {
                            mergeFrom(goFloorReq2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        GoFloorReq goFloorReq3 = (GoFloorReq) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            goFloorReq = goFloorReq3;
                            if (goFloorReq != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (goFloorReq != null) {
                        mergeFrom(goFloorReq);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public String getTargetFloor() {
                Object obj = this.targetFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.targetFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public ByteString getTargetFloorBytes() {
                Object obj = this.targetFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.targetFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTargetFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.targetFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearTargetFloor() {
                this.targetFloor_ = GoFloorReq.getDefaultInstance().getTargetFloor();
                onChanged();
                return this;
            }

            public Builder setTargetFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    GoFloorReq.checkByteStringIsUtf8(byteString);
                    this.targetFloor_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public int getActionValue() {
                return this.action_;
            }

            public Builder setActionValue(int i) {
                this.action_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public Action getAction() {
                Action valueOf = Action.valueOf(this.action_);
                return valueOf == null ? Action.UNRECOGNIZED : valueOf;
            }

            public Builder setAction(Action action) {
                if (action == null) {
                    throw new NullPointerException();
                }
                this.action_ = action.getNumber();
                onChanged();
                return this;
            }

            public Builder clearAction() {
                this.action_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public String getCurFloor() {
                Object obj = this.curFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.curFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public ByteString getCurFloorBytes() {
                Object obj = this.curFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.curFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.curFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurFloor() {
                this.curFloor_ = GoFloorReq.getDefaultInstance().getCurFloor();
                onChanged();
                return this;
            }

            public Builder setCurFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    GoFloorReq.checkByteStringIsUtf8(byteString);
                    this.curFloor_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
            public String getDestFloor() {
                Object obj = this.destFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.destFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqOrBuilder
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
                this.destFloor_ = GoFloorReq.getDefaultInstance().getDestFloor();
                onChanged();
                return this;
            }

            public Builder setDestFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    GoFloorReq.checkByteStringIsUtf8(byteString);
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

        public static GoFloorReq getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GoFloorReq> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<GoFloorReq> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public GoFloorReq getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class GoFloorReqAck extends GeneratedMessageV3 implements GoFloorReqAckOrBuilder {
        private static final GoFloorReqAck DEFAULT_INSTANCE = new GoFloorReqAck();
        private static final Parser<GoFloorReqAck> PARSER = new AbstractParser<GoFloorReqAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAck.1
            @Override // com.google.protobuf.Parser
            public GoFloorReqAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GoFloorReqAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private AckResult result_;

        private GoFloorReqAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GoFloorReqAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private GoFloorReqAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.result_);
                                    this.result_ = builder.buildPartial();
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
            return ElevatorProtocol.f5847x77d6b47b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5848xc0332ff9.ensureFieldAccessorsInitialized(GoFloorReqAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeMessageSize;
            return computeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GoFloorReqAck)) {
                return super.equals(obj);
            }
            GoFloorReqAck goFloorReqAck = (GoFloorReqAck) obj;
            boolean z = hasResult() == goFloorReqAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(goFloorReqAck.getResult());
            }
            return z && this.unknownFields.equals(goFloorReqAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static GoFloorReqAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static GoFloorReqAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GoFloorReqAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static GoFloorReqAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GoFloorReqAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static GoFloorReqAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GoFloorReqAck parseFrom(InputStream inputStream) throws IOException {
            return (GoFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GoFloorReqAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GoFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GoFloorReqAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GoFloorReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GoFloorReqAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GoFloorReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GoFloorReqAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GoFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GoFloorReqAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GoFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GoFloorReqAck goFloorReqAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(goFloorReqAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GoFloorReqAckOrBuilder {
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5847x77d6b47b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5848xc0332ff9.ensureFieldAccessorsInitialized(GoFloorReqAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GoFloorReqAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5847x77d6b47b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public GoFloorReqAck getDefaultInstanceForType() {
                return GoFloorReqAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GoFloorReqAck build() {
                GoFloorReqAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GoFloorReqAck buildPartial() {
                GoFloorReqAck goFloorReqAck = new GoFloorReqAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    goFloorReqAck.result_ = this.result_;
                } else {
                    goFloorReqAck.result_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return goFloorReqAck;
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
                if (message instanceof GoFloorReqAck) {
                    return mergeFrom((GoFloorReqAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GoFloorReqAck goFloorReqAck) {
                if (goFloorReqAck == GoFloorReqAck.getDefaultInstance()) {
                    return this;
                }
                if (goFloorReqAck.hasResult()) {
                    mergeResult(goFloorReqAck.getResult());
                }
                mergeUnknownFields(goFloorReqAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                GoFloorReqAck goFloorReqAck = null;
                try {
                    try {
                        GoFloorReqAck goFloorReqAck2 = (GoFloorReqAck) GoFloorReqAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (goFloorReqAck2 != null) {
                            mergeFrom(goFloorReqAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        GoFloorReqAck goFloorReqAck3 = (GoFloorReqAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            goFloorReqAck = goFloorReqAck3;
                            if (goFloorReqAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (goFloorReqAck != null) {
                        mergeFrom(goFloorReqAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.GoFloorReqAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
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

        public static GoFloorReqAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GoFloorReqAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<GoFloorReqAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public GoFloorReqAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class ElvArrived extends GeneratedMessageV3 implements ElvArrivedOrBuilder {
        public static final int ARRIVEDFLOOR_FIELD_NUMBER = 1;
        public static final int EFF_TIME_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object arrivedFloor_;
        private int effTime_;
        private byte memoizedIsInitialized;
        private static final ElvArrived DEFAULT_INSTANCE = new ElvArrived();
        private static final Parser<ElvArrived> PARSER = new AbstractParser<ElvArrived>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrived.1
            @Override // com.google.protobuf.Parser
            public ElvArrived parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvArrived(codedInputStream, extensionRegistryLite);
            }
        };

        private ElvArrived(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvArrived() {
            this.memoizedIsInitialized = (byte) -1;
            this.arrivedFloor_ = "";
            this.effTime_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvArrived(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.arrivedFloor_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5843x7c66ec14;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5844x8582be92.ensureFieldAccessorsInitialized(ElvArrived.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedOrBuilder
        public String getArrivedFloor() {
            Object obj = this.arrivedFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.arrivedFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedOrBuilder
        public ByteString getArrivedFloorBytes() {
            Object obj = this.arrivedFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.arrivedFloor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedOrBuilder
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
            if (!getArrivedFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.arrivedFloor_);
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
            int computeStringSize = getArrivedFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.arrivedFloor_);
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
            if (!(obj instanceof ElvArrived)) {
                return super.equals(obj);
            }
            ElvArrived elvArrived = (ElvArrived) obj;
            return ((getArrivedFloor().equals(elvArrived.getArrivedFloor())) && getEffTime() == elvArrived.getEffTime()) && this.unknownFields.equals(elvArrived.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getArrivedFloor().hashCode()) * 37) + 2) * 53) + getEffTime()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static ElvArrived parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvArrived parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvArrived parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvArrived parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvArrived parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvArrived parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvArrived parseFrom(InputStream inputStream) throws IOException {
            return (ElvArrived) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvArrived parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvArrived) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvArrived parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvArrived) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvArrived parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvArrived) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvArrived parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvArrived) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvArrived parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvArrived) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvArrived elvArrived) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvArrived);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvArrivedOrBuilder {
            private Object arrivedFloor_;
            private int effTime_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5843x7c66ec14;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5844x8582be92.ensureFieldAccessorsInitialized(ElvArrived.class, Builder.class);
            }

            private Builder() {
                this.arrivedFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.arrivedFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvArrived.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.arrivedFloor_ = "";
                this.effTime_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5843x7c66ec14;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvArrived getDefaultInstanceForType() {
                return ElvArrived.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvArrived build() {
                ElvArrived buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvArrived buildPartial() {
                ElvArrived elvArrived = new ElvArrived(this);
                elvArrived.arrivedFloor_ = this.arrivedFloor_;
                elvArrived.effTime_ = this.effTime_;
                onBuilt();
                return elvArrived;
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
                if (message instanceof ElvArrived) {
                    return mergeFrom((ElvArrived) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvArrived elvArrived) {
                if (elvArrived == ElvArrived.getDefaultInstance()) {
                    return this;
                }
                if (!elvArrived.getArrivedFloor().isEmpty()) {
                    this.arrivedFloor_ = elvArrived.arrivedFloor_;
                    onChanged();
                }
                if (elvArrived.getEffTime() != 0) {
                    setEffTime(elvArrived.getEffTime());
                }
                mergeUnknownFields(elvArrived.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvArrived elvArrived = null;
                try {
                    try {
                        ElvArrived elvArrived2 = (ElvArrived) ElvArrived.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvArrived2 != null) {
                            mergeFrom(elvArrived2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvArrived elvArrived3 = (ElvArrived) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvArrived = elvArrived3;
                            if (elvArrived != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvArrived != null) {
                        mergeFrom(elvArrived);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedOrBuilder
            public String getArrivedFloor() {
                Object obj = this.arrivedFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.arrivedFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedOrBuilder
            public ByteString getArrivedFloorBytes() {
                Object obj = this.arrivedFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.arrivedFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setArrivedFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.arrivedFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearArrivedFloor() {
                this.arrivedFloor_ = ElvArrived.getDefaultInstance().getArrivedFloor();
                onChanged();
                return this;
            }

            public Builder setArrivedFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    ElvArrived.checkByteStringIsUtf8(byteString);
                    this.arrivedFloor_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedOrBuilder
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

        public static ElvArrived getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvArrived> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvArrived> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvArrived getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class ElvArrivedAck extends GeneratedMessageV3 implements ElvArrivedAckOrBuilder {
        private static final ElvArrivedAck DEFAULT_INSTANCE = new ElvArrivedAck();
        private static final Parser<ElvArrivedAck> PARSER = new AbstractParser<ElvArrivedAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAck.1
            @Override // com.google.protobuf.Parser
            public ElvArrivedAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ElvArrivedAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private AckResult result_;

        private ElvArrivedAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ElvArrivedAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ElvArrivedAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.result_);
                                    this.result_ = builder.buildPartial();
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
            return ElevatorProtocol.f5841xbb23143f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5842xe1158bbd.ensureFieldAccessorsInitialized(ElvArrivedAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeMessageSize;
            return computeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ElvArrivedAck)) {
                return super.equals(obj);
            }
            ElvArrivedAck elvArrivedAck = (ElvArrivedAck) obj;
            boolean z = hasResult() == elvArrivedAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(elvArrivedAck.getResult());
            }
            return z && this.unknownFields.equals(elvArrivedAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ElvArrivedAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ElvArrivedAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ElvArrivedAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ElvArrivedAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ElvArrivedAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ElvArrivedAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ElvArrivedAck parseFrom(InputStream inputStream) throws IOException {
            return (ElvArrivedAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ElvArrivedAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvArrivedAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvArrivedAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ElvArrivedAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ElvArrivedAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvArrivedAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ElvArrivedAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ElvArrivedAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ElvArrivedAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ElvArrivedAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ElvArrivedAck elvArrivedAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(elvArrivedAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ElvArrivedAckOrBuilder {
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5841xbb23143f;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5842xe1158bbd.ensureFieldAccessorsInitialized(ElvArrivedAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ElvArrivedAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5841xbb23143f;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ElvArrivedAck getDefaultInstanceForType() {
                return ElvArrivedAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvArrivedAck build() {
                ElvArrivedAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ElvArrivedAck buildPartial() {
                ElvArrivedAck elvArrivedAck = new ElvArrivedAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    elvArrivedAck.result_ = this.result_;
                } else {
                    elvArrivedAck.result_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return elvArrivedAck;
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
                if (message instanceof ElvArrivedAck) {
                    return mergeFrom((ElvArrivedAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ElvArrivedAck elvArrivedAck) {
                if (elvArrivedAck == ElvArrivedAck.getDefaultInstance()) {
                    return this;
                }
                if (elvArrivedAck.hasResult()) {
                    mergeResult(elvArrivedAck.getResult());
                }
                mergeUnknownFields(elvArrivedAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ElvArrivedAck elvArrivedAck = null;
                try {
                    try {
                        ElvArrivedAck elvArrivedAck2 = (ElvArrivedAck) ElvArrivedAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (elvArrivedAck2 != null) {
                            mergeFrom(elvArrivedAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ElvArrivedAck elvArrivedAck3 = (ElvArrivedAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            elvArrivedAck = elvArrivedAck3;
                            if (elvArrivedAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (elvArrivedAck != null) {
                        mergeFrom(elvArrivedAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.ElvArrivedAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
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

        public static ElvArrivedAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ElvArrivedAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ElvArrivedAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ElvArrivedAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class CancelFloorReq extends GeneratedMessageV3 implements CancelFloorReqOrBuilder {
        private static final CancelFloorReq DEFAULT_INSTANCE = new CancelFloorReq();
        private static final Parser<CancelFloorReq> PARSER = new AbstractParser<CancelFloorReq>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReq.1
            @Override // com.google.protobuf.Parser
            public CancelFloorReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CancelFloorReq(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private CancelFloorReq(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CancelFloorReq() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CancelFloorReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
            return ElevatorProtocol.f5831xf51b3486;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5832x4fe87504.ensureFieldAccessorsInitialized(CancelFloorReq.class, Builder.class);
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
            if (obj instanceof CancelFloorReq) {
                return this.unknownFields.equals(((CancelFloorReq) obj).unknownFields);
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

        public static CancelFloorReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CancelFloorReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CancelFloorReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CancelFloorReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CancelFloorReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CancelFloorReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CancelFloorReq parseFrom(InputStream inputStream) throws IOException {
            return (CancelFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CancelFloorReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelFloorReq parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CancelFloorReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CancelFloorReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelFloorReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelFloorReq parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CancelFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CancelFloorReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelFloorReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CancelFloorReq cancelFloorReq) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(cancelFloorReq);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CancelFloorReqOrBuilder {
            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5831xf51b3486;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5832x4fe87504.ensureFieldAccessorsInitialized(CancelFloorReq.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CancelFloorReq.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5831xf51b3486;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CancelFloorReq getDefaultInstanceForType() {
                return CancelFloorReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelFloorReq build() {
                CancelFloorReq buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelFloorReq buildPartial() {
                CancelFloorReq cancelFloorReq = new CancelFloorReq(this);
                onBuilt();
                return cancelFloorReq;
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
                if (message instanceof CancelFloorReq) {
                    return mergeFrom((CancelFloorReq) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CancelFloorReq cancelFloorReq) {
                if (cancelFloorReq == CancelFloorReq.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(cancelFloorReq.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CancelFloorReq cancelFloorReq = null;
                try {
                    try {
                        CancelFloorReq cancelFloorReq2 = (CancelFloorReq) CancelFloorReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (cancelFloorReq2 != null) {
                            mergeFrom(cancelFloorReq2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CancelFloorReq cancelFloorReq3 = (CancelFloorReq) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            cancelFloorReq = cancelFloorReq3;
                            if (cancelFloorReq != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cancelFloorReq != null) {
                        mergeFrom(cancelFloorReq);
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

        public static CancelFloorReq getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CancelFloorReq> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CancelFloorReq> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CancelFloorReq getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class CancelFloorReqAck extends GeneratedMessageV3 implements CancelFloorReqAckOrBuilder {
        private static final CancelFloorReqAck DEFAULT_INSTANCE = new CancelFloorReqAck();
        private static final Parser<CancelFloorReqAck> PARSER = new AbstractParser<CancelFloorReqAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAck.1
            @Override // com.google.protobuf.Parser
            public CancelFloorReqAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CancelFloorReqAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private AckResult result_;

        private CancelFloorReqAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CancelFloorReqAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CancelFloorReqAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.result_);
                                    this.result_ = builder.buildPartial();
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
            return ElevatorProtocol.f5829x36dd9e8d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5830x137fe80b.ensureFieldAccessorsInitialized(CancelFloorReqAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeMessageSize;
            return computeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CancelFloorReqAck)) {
                return super.equals(obj);
            }
            CancelFloorReqAck cancelFloorReqAck = (CancelFloorReqAck) obj;
            boolean z = hasResult() == cancelFloorReqAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(cancelFloorReqAck.getResult());
            }
            return z && this.unknownFields.equals(cancelFloorReqAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CancelFloorReqAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CancelFloorReqAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CancelFloorReqAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CancelFloorReqAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CancelFloorReqAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CancelFloorReqAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CancelFloorReqAck parseFrom(InputStream inputStream) throws IOException {
            return (CancelFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CancelFloorReqAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelFloorReqAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CancelFloorReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CancelFloorReqAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelFloorReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CancelFloorReqAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CancelFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CancelFloorReqAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CancelFloorReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CancelFloorReqAck cancelFloorReqAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(cancelFloorReqAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CancelFloorReqAckOrBuilder {
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5829x36dd9e8d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5830x137fe80b.ensureFieldAccessorsInitialized(CancelFloorReqAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CancelFloorReqAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5829x36dd9e8d;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CancelFloorReqAck getDefaultInstanceForType() {
                return CancelFloorReqAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelFloorReqAck build() {
                CancelFloorReqAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CancelFloorReqAck buildPartial() {
                CancelFloorReqAck cancelFloorReqAck = new CancelFloorReqAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    cancelFloorReqAck.result_ = this.result_;
                } else {
                    cancelFloorReqAck.result_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return cancelFloorReqAck;
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
                if (message instanceof CancelFloorReqAck) {
                    return mergeFrom((CancelFloorReqAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CancelFloorReqAck cancelFloorReqAck) {
                if (cancelFloorReqAck == CancelFloorReqAck.getDefaultInstance()) {
                    return this;
                }
                if (cancelFloorReqAck.hasResult()) {
                    mergeResult(cancelFloorReqAck.getResult());
                }
                mergeUnknownFields(cancelFloorReqAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CancelFloorReqAck cancelFloorReqAck = null;
                try {
                    try {
                        CancelFloorReqAck cancelFloorReqAck2 = (CancelFloorReqAck) CancelFloorReqAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (cancelFloorReqAck2 != null) {
                            mergeFrom(cancelFloorReqAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CancelFloorReqAck cancelFloorReqAck3 = (CancelFloorReqAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            cancelFloorReqAck = cancelFloorReqAck3;
                            if (cancelFloorReqAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cancelFloorReqAck != null) {
                        mergeFrom(cancelFloorReqAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CancelFloorReqAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
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

        public static CancelFloorReqAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CancelFloorReqAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CancelFloorReqAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CancelFloorReqAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class UpdateReq extends GeneratedMessageV3 implements UpdateReqOrBuilder {
        private static final UpdateReq DEFAULT_INSTANCE = new UpdateReq();
        private static final Parser<UpdateReq> PARSER = new AbstractParser<UpdateReq>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReq.1
            @Override // com.google.protobuf.Parser
            public UpdateReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateReq(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TARGET_ELV_VER_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object targetElvVer_;

        private UpdateReq(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateReq() {
            this.memoizedIsInitialized = (byte) -1;
            this.targetElvVer_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.targetElvVer_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5857xb2d56bd5;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5858x2899ed53.ensureFieldAccessorsInitialized(UpdateReq.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqOrBuilder
        public String getTargetElvVer() {
            Object obj = this.targetElvVer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.targetElvVer_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqOrBuilder
        public ByteString getTargetElvVerBytes() {
            Object obj = this.targetElvVer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetElvVer_ = copyFromUtf8;
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
            if (!getTargetElvVerBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.targetElvVer_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getTargetElvVerBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.targetElvVer_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UpdateReq)) {
                return super.equals(obj);
            }
            UpdateReq updateReq = (UpdateReq) obj;
            return (getTargetElvVer().equals(updateReq.getTargetElvVer())) && this.unknownFields.equals(updateReq.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTargetElvVer().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static UpdateReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateReq parseFrom(InputStream inputStream) throws IOException {
            return (UpdateReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateReq parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateReq parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateReq updateReq) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateReq);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateReqOrBuilder {
            private Object targetElvVer_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5857xb2d56bd5;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5858x2899ed53.ensureFieldAccessorsInitialized(UpdateReq.class, Builder.class);
            }

            private Builder() {
                this.targetElvVer_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.targetElvVer_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateReq.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.targetElvVer_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5857xb2d56bd5;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateReq getDefaultInstanceForType() {
                return UpdateReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateReq build() {
                UpdateReq buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateReq buildPartial() {
                UpdateReq updateReq = new UpdateReq(this);
                updateReq.targetElvVer_ = this.targetElvVer_;
                onBuilt();
                return updateReq;
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
                if (message instanceof UpdateReq) {
                    return mergeFrom((UpdateReq) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateReq updateReq) {
                if (updateReq == UpdateReq.getDefaultInstance()) {
                    return this;
                }
                if (!updateReq.getTargetElvVer().isEmpty()) {
                    this.targetElvVer_ = updateReq.targetElvVer_;
                    onChanged();
                }
                mergeUnknownFields(updateReq.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                UpdateReq updateReq = null;
                try {
                    try {
                        UpdateReq updateReq2 = (UpdateReq) UpdateReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (updateReq2 != null) {
                            mergeFrom(updateReq2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        UpdateReq updateReq3 = (UpdateReq) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            updateReq = updateReq3;
                            if (updateReq != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (updateReq != null) {
                        mergeFrom(updateReq);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqOrBuilder
            public String getTargetElvVer() {
                Object obj = this.targetElvVer_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.targetElvVer_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqOrBuilder
            public ByteString getTargetElvVerBytes() {
                Object obj = this.targetElvVer_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.targetElvVer_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTargetElvVer(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.targetElvVer_ = str;
                onChanged();
                return this;
            }

            public Builder clearTargetElvVer() {
                this.targetElvVer_ = UpdateReq.getDefaultInstance().getTargetElvVer();
                onChanged();
                return this;
            }

            public Builder setTargetElvVerBytes(ByteString byteString) {
                if (byteString != null) {
                    UpdateReq.checkByteStringIsUtf8(byteString);
                    this.targetElvVer_ = byteString;
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

        public static UpdateReq getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateReq> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateReq> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateReq getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class UpdateReqAck extends GeneratedMessageV3 implements UpdateReqAckOrBuilder {
        private static final UpdateReqAck DEFAULT_INSTANCE = new UpdateReqAck();
        private static final Parser<UpdateReqAck> PARSER = new AbstractParser<UpdateReqAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAck.1
            @Override // com.google.protobuf.Parser
            public UpdateReqAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateReqAck(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private AckResult result_;

        private UpdateReqAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateReqAck() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateReqAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.result_);
                                    this.result_ = builder.buildPartial();
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
            return ElevatorProtocol.f5855x7f0de;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5856xe7df595c.ensureFieldAccessorsInitialized(UpdateReqAck.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeMessageSize;
            return computeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UpdateReqAck)) {
                return super.equals(obj);
            }
            UpdateReqAck updateReqAck = (UpdateReqAck) obj;
            boolean z = hasResult() == updateReqAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(updateReqAck.getResult());
            }
            return z && this.unknownFields.equals(updateReqAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static UpdateReqAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateReqAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateReqAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateReqAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateReqAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateReqAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateReqAck parseFrom(InputStream inputStream) throws IOException {
            return (UpdateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateReqAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateReqAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateReqAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateReqAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateReqAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateReqAck updateReqAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateReqAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateReqAckOrBuilder {
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5855x7f0de;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5856xe7df595c.ensureFieldAccessorsInitialized(UpdateReqAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateReqAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5855x7f0de;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateReqAck getDefaultInstanceForType() {
                return UpdateReqAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateReqAck build() {
                UpdateReqAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateReqAck buildPartial() {
                UpdateReqAck updateReqAck = new UpdateReqAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    updateReqAck.result_ = this.result_;
                } else {
                    updateReqAck.result_ = singleFieldBuilderV3.build();
                }
                onBuilt();
                return updateReqAck;
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
                if (message instanceof UpdateReqAck) {
                    return mergeFrom((UpdateReqAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateReqAck updateReqAck) {
                if (updateReqAck == UpdateReqAck.getDefaultInstance()) {
                    return this;
                }
                if (updateReqAck.hasResult()) {
                    mergeResult(updateReqAck.getResult());
                }
                mergeUnknownFields(updateReqAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                UpdateReqAck updateReqAck = null;
                try {
                    try {
                        UpdateReqAck updateReqAck2 = (UpdateReqAck) UpdateReqAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (updateReqAck2 != null) {
                            mergeFrom(updateReqAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        UpdateReqAck updateReqAck3 = (UpdateReqAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            updateReqAck = updateReqAck3;
                            if (updateReqAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (updateReqAck != null) {
                        mergeFrom(updateReqAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateReqAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
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

        public static UpdateReqAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateReqAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateReqAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateReqAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class UpdateStateReqAck extends GeneratedMessageV3 implements UpdateStateReqAckOrBuilder {
        public static final int ELV_VER_FIELD_NUMBER = 6;
        public static final int ERR_CODE_FIELD_NUMBER = 3;
        public static final int HARDWARE_TYPE_FIELD_NUMBER = 9;
        public static final int PROGRESS_FIELD_NUMBER = 4;
        public static final int PROTOCOL_VER_FIELD_NUMBER = 7;
        public static final int RESULT_FIELD_NUMBER = 1;
        public static final int STATE_FIELD_NUMBER = 2;
        public static final int TARGET_ELV_VER_FIELD_NUMBER = 8;
        public static final int TOTAL_FIELD_NUMBER = 5;
        private static final long serialVersionUID = 0;
        private volatile Object elvVer_;
        private int errCode_;
        private volatile Object hardwareType_;
        private byte memoizedIsInitialized;
        private int progress_;
        private int protocolVer_;
        private AckResult result_;
        private int state_;
        private volatile Object targetElvVer_;
        private int total_;
        private static final UpdateStateReqAck DEFAULT_INSTANCE = new UpdateStateReqAck();
        private static final Parser<UpdateStateReqAck> PARSER = new AbstractParser<UpdateStateReqAck>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAck.1
            @Override // com.google.protobuf.Parser
            public UpdateStateReqAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateStateReqAck(codedInputStream, extensionRegistryLite);
            }
        };

        private UpdateStateReqAck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateStateReqAck() {
            this.memoizedIsInitialized = (byte) -1;
            this.state_ = 0;
            this.errCode_ = 0;
            this.progress_ = 0;
            this.total_ = 0;
            this.elvVer_ = "";
            this.protocolVer_ = 0;
            this.targetElvVer_ = "";
            this.hardwareType_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateStateReqAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                AckResult.Builder builder = this.result_ != null ? this.result_.toBuilder() : null;
                                this.result_ = (AckResult) codedInputStream.readMessage(AckResult.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.result_);
                                    this.result_ = builder.buildPartial();
                                }
                            } else if (readTag == 16) {
                                this.state_ = codedInputStream.readEnum();
                            } else if (readTag == 24) {
                                this.errCode_ = codedInputStream.readInt32();
                            } else if (readTag == 32) {
                                this.progress_ = codedInputStream.readInt32();
                            } else if (readTag == 40) {
                                this.total_ = codedInputStream.readInt32();
                            } else if (readTag == 50) {
                                this.elvVer_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 56) {
                                this.protocolVer_ = codedInputStream.readInt32();
                            } else if (readTag == 66) {
                                this.targetElvVer_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 74) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.hardwareType_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5859xbad24db7;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5860x7e8fcd35.ensureFieldAccessorsInitialized(UpdateStateReqAck.class, Builder.class);
        }

        /* loaded from: classes5.dex */
        public enum UpdateState implements ProtocolMessageEnum {
            Free(0),
            Downloading_File(1),
            Extract_Files(2),
            Flash_Key(3),
            Flash_Rfid(4),
            Flash_Master(5),
            Restart(6),
            Update_Fail(7),
            UNRECOGNIZED(-1);

            public static final int Downloading_File_VALUE = 1;
            public static final int Extract_Files_VALUE = 2;
            public static final int Flash_Key_VALUE = 3;
            public static final int Flash_Master_VALUE = 5;
            public static final int Flash_Rfid_VALUE = 4;
            public static final int Free_VALUE = 0;
            public static final int Restart_VALUE = 6;
            public static final int Update_Fail_VALUE = 7;
            private final int value;
            private static final Internal.EnumLiteMap<UpdateState> internalValueMap = new Internal.EnumLiteMap<UpdateState>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAck.UpdateState.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public UpdateState findValueByNumber(int i) {
                    return UpdateState.forNumber(i);
                }
            };
            private static final UpdateState[] VALUES = values();

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
            public final int getNumber() {
                if (this == UNRECOGNIZED) {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                return this.value;
            }

            @Deprecated
            public static UpdateState valueOf(int i) {
                return forNumber(i);
            }

            public static UpdateState forNumber(int i) {
                switch (i) {
                    case 0:
                        return Free;
                    case 1:
                        return Downloading_File;
                    case 2:
                        return Extract_Files;
                    case 3:
                        return Flash_Key;
                    case 4:
                        return Flash_Rfid;
                    case 5:
                        return Flash_Master;
                    case 6:
                        return Restart;
                    case 7:
                        return Update_Fail;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<UpdateState> internalGetValueMap() {
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
                return UpdateStateReqAck.getDescriptor().getEnumTypes().get(0);
            }

            public static UpdateState valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
                if (enumValueDescriptor.getIndex() == -1) {
                    return UNRECOGNIZED;
                }
                return VALUES[enumValueDescriptor.getIndex()];
            }

            UpdateState(int i) {
                this.value = i;
            }
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public boolean hasResult() {
            return this.result_ != null;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public AckResult getResult() {
            AckResult ackResult = this.result_;
            return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public AckResultOrBuilder getResultOrBuilder() {
            return getResult();
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public int getStateValue() {
            return this.state_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public UpdateState getState() {
            UpdateState valueOf = UpdateState.valueOf(this.state_);
            return valueOf == null ? UpdateState.UNRECOGNIZED : valueOf;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public int getErrCode() {
            return this.errCode_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public int getProgress() {
            return this.progress_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public int getTotal() {
            return this.total_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public String getElvVer() {
            Object obj = this.elvVer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.elvVer_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public ByteString getElvVerBytes() {
            Object obj = this.elvVer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.elvVer_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public int getProtocolVer() {
            return this.protocolVer_;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public String getTargetElvVer() {
            Object obj = this.targetElvVer_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.targetElvVer_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public ByteString getTargetElvVerBytes() {
            Object obj = this.targetElvVer_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetElvVer_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public String getHardwareType() {
            Object obj = this.hardwareType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.hardwareType_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
        public ByteString getHardwareTypeBytes() {
            Object obj = this.hardwareType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.hardwareType_ = copyFromUtf8;
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
            if (this.result_ != null) {
                codedOutputStream.writeMessage(1, getResult());
            }
            if (this.state_ != UpdateState.Free.getNumber()) {
                codedOutputStream.writeEnum(2, this.state_);
            }
            int i = this.errCode_;
            if (i != 0) {
                codedOutputStream.writeInt32(3, i);
            }
            int i2 = this.progress_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(4, i2);
            }
            int i3 = this.total_;
            if (i3 != 0) {
                codedOutputStream.writeInt32(5, i3);
            }
            if (!getElvVerBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.elvVer_);
            }
            int i4 = this.protocolVer_;
            if (i4 != 0) {
                codedOutputStream.writeInt32(7, i4);
            }
            if (!getTargetElvVerBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 8, this.targetElvVer_);
            }
            if (!getHardwareTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 9, this.hardwareType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.result_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getResult()) : 0;
            if (this.state_ != UpdateState.Free.getNumber()) {
                computeMessageSize += CodedOutputStream.computeEnumSize(2, this.state_);
            }
            int i2 = this.errCode_;
            if (i2 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(3, i2);
            }
            int i3 = this.progress_;
            if (i3 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(4, i3);
            }
            int i4 = this.total_;
            if (i4 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(5, i4);
            }
            if (!getElvVerBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(6, this.elvVer_);
            }
            int i5 = this.protocolVer_;
            if (i5 != 0) {
                computeMessageSize += CodedOutputStream.computeInt32Size(7, i5);
            }
            if (!getTargetElvVerBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(8, this.targetElvVer_);
            }
            if (!getHardwareTypeBytes().isEmpty()) {
                computeMessageSize += GeneratedMessageV3.computeStringSize(9, this.hardwareType_);
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
            if (!(obj instanceof UpdateStateReqAck)) {
                return super.equals(obj);
            }
            UpdateStateReqAck updateStateReqAck = (UpdateStateReqAck) obj;
            boolean z = hasResult() == updateStateReqAck.hasResult();
            if (hasResult()) {
                z = z && getResult().equals(updateStateReqAck.getResult());
            }
            return ((((((((z && this.state_ == updateStateReqAck.state_) && getErrCode() == updateStateReqAck.getErrCode()) && getProgress() == updateStateReqAck.getProgress()) && getTotal() == updateStateReqAck.getTotal()) && getElvVer().equals(updateStateReqAck.getElvVer())) && getProtocolVer() == updateStateReqAck.getProtocolVer()) && getTargetElvVer().equals(updateStateReqAck.getTargetElvVer())) && getHardwareType().equals(updateStateReqAck.getHardwareType())) && this.unknownFields.equals(updateStateReqAck.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (hasResult()) {
                hashCode = (((hashCode * 37) + 1) * 53) + getResult().hashCode();
            }
            int errCode = (((((((((((((((((((((((((((((((((hashCode * 37) + 2) * 53) + this.state_) * 37) + 3) * 53) + getErrCode()) * 37) + 4) * 53) + getProgress()) * 37) + 5) * 53) + getTotal()) * 37) + 6) * 53) + getElvVer().hashCode()) * 37) + 7) * 53) + getProtocolVer()) * 37) + 8) * 53) + getTargetElvVer().hashCode()) * 37) + 9) * 53) + getHardwareType().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = errCode;
            return errCode;
        }

        public static UpdateStateReqAck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateStateReqAck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateStateReqAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateStateReqAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateStateReqAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateStateReqAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateStateReqAck parseFrom(InputStream inputStream) throws IOException {
            return (UpdateStateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateStateReqAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateStateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateStateReqAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateStateReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateStateReqAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateStateReqAck) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateStateReqAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateStateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateStateReqAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateStateReqAck) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateStateReqAck updateStateReqAck) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateStateReqAck);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateStateReqAckOrBuilder {
            private Object elvVer_;
            private int errCode_;
            private Object hardwareType_;
            private int progress_;
            private int protocolVer_;
            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> resultBuilder_;
            private AckResult result_;
            private int state_;
            private Object targetElvVer_;
            private int total_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5859xbad24db7;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5860x7e8fcd35.ensureFieldAccessorsInitialized(UpdateStateReqAck.class, Builder.class);
            }

            private Builder() {
                this.result_ = null;
                this.state_ = 0;
                this.elvVer_ = "";
                this.targetElvVer_ = "";
                this.hardwareType_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.result_ = null;
                this.state_ = 0;
                this.elvVer_ = "";
                this.targetElvVer_ = "";
                this.hardwareType_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateStateReqAck.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                this.state_ = 0;
                this.errCode_ = 0;
                this.progress_ = 0;
                this.total_ = 0;
                this.elvVer_ = "";
                this.protocolVer_ = 0;
                this.targetElvVer_ = "";
                this.hardwareType_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5859xbad24db7;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateStateReqAck getDefaultInstanceForType() {
                return UpdateStateReqAck.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateStateReqAck build() {
                UpdateStateReqAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateStateReqAck buildPartial() {
                UpdateStateReqAck updateStateReqAck = new UpdateStateReqAck(this);
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    updateStateReqAck.result_ = this.result_;
                } else {
                    updateStateReqAck.result_ = singleFieldBuilderV3.build();
                }
                updateStateReqAck.state_ = this.state_;
                updateStateReqAck.errCode_ = this.errCode_;
                updateStateReqAck.progress_ = this.progress_;
                updateStateReqAck.total_ = this.total_;
                updateStateReqAck.elvVer_ = this.elvVer_;
                updateStateReqAck.protocolVer_ = this.protocolVer_;
                updateStateReqAck.targetElvVer_ = this.targetElvVer_;
                updateStateReqAck.hardwareType_ = this.hardwareType_;
                onBuilt();
                return updateStateReqAck;
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
                if (message instanceof UpdateStateReqAck) {
                    return mergeFrom((UpdateStateReqAck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateStateReqAck updateStateReqAck) {
                if (updateStateReqAck == UpdateStateReqAck.getDefaultInstance()) {
                    return this;
                }
                if (updateStateReqAck.hasResult()) {
                    mergeResult(updateStateReqAck.getResult());
                }
                if (updateStateReqAck.state_ != 0) {
                    setStateValue(updateStateReqAck.getStateValue());
                }
                if (updateStateReqAck.getErrCode() != 0) {
                    setErrCode(updateStateReqAck.getErrCode());
                }
                if (updateStateReqAck.getProgress() != 0) {
                    setProgress(updateStateReqAck.getProgress());
                }
                if (updateStateReqAck.getTotal() != 0) {
                    setTotal(updateStateReqAck.getTotal());
                }
                if (!updateStateReqAck.getElvVer().isEmpty()) {
                    this.elvVer_ = updateStateReqAck.elvVer_;
                    onChanged();
                }
                if (updateStateReqAck.getProtocolVer() != 0) {
                    setProtocolVer(updateStateReqAck.getProtocolVer());
                }
                if (!updateStateReqAck.getTargetElvVer().isEmpty()) {
                    this.targetElvVer_ = updateStateReqAck.targetElvVer_;
                    onChanged();
                }
                if (!updateStateReqAck.getHardwareType().isEmpty()) {
                    this.hardwareType_ = updateStateReqAck.hardwareType_;
                    onChanged();
                }
                mergeUnknownFields(updateStateReqAck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                UpdateStateReqAck updateStateReqAck = null;
                try {
                    try {
                        UpdateStateReqAck updateStateReqAck2 = (UpdateStateReqAck) UpdateStateReqAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (updateStateReqAck2 != null) {
                            mergeFrom(updateStateReqAck2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        UpdateStateReqAck updateStateReqAck3 = (UpdateStateReqAck) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            updateStateReqAck = updateStateReqAck3;
                            if (updateStateReqAck != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (updateStateReqAck != null) {
                        mergeFrom(updateStateReqAck);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public boolean hasResult() {
                return (this.resultBuilder_ == null && this.result_ == null) ? false : true;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public AckResult getResult() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult = this.result_;
                    return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(ackResult);
                } else {
                    if (ackResult == null) {
                        throw new NullPointerException();
                    }
                    this.result_ = ackResult;
                    onChanged();
                }
                return this;
            }

            public Builder setResult(AckResult.Builder builder) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.result_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeResult(AckResult ackResult) {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 == null) {
                    AckResult ackResult2 = this.result_;
                    if (ackResult2 != null) {
                        this.result_ = AckResult.newBuilder(ackResult2).mergeFrom(ackResult).buildPartial();
                    } else {
                        this.result_ = ackResult;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(ackResult);
                }
                return this;
            }

            public Builder clearResult() {
                if (this.resultBuilder_ == null) {
                    this.result_ = null;
                    onChanged();
                } else {
                    this.result_ = null;
                    this.resultBuilder_ = null;
                }
                return this;
            }

            public AckResult.Builder getResultBuilder() {
                onChanged();
                return getResultFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public AckResultOrBuilder getResultOrBuilder() {
                SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> singleFieldBuilderV3 = this.resultBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                AckResult ackResult = this.result_;
                return ackResult == null ? AckResult.getDefaultInstance() : ackResult;
            }

            private SingleFieldBuilderV3<AckResult, AckResult.Builder, AckResultOrBuilder> getResultFieldBuilder() {
                if (this.resultBuilder_ == null) {
                    this.resultBuilder_ = new SingleFieldBuilderV3<>(getResult(), getParentForChildren(), isClean());
                    this.result_ = null;
                }
                return this.resultBuilder_;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public int getStateValue() {
                return this.state_;
            }

            public Builder setStateValue(int i) {
                this.state_ = i;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public UpdateState getState() {
                UpdateState valueOf = UpdateState.valueOf(this.state_);
                return valueOf == null ? UpdateState.UNRECOGNIZED : valueOf;
            }

            public Builder setState(UpdateState updateState) {
                if (updateState == null) {
                    throw new NullPointerException();
                }
                this.state_ = updateState.getNumber();
                onChanged();
                return this;
            }

            public Builder clearState() {
                this.state_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public int getErrCode() {
                return this.errCode_;
            }

            public Builder setErrCode(int i) {
                this.errCode_ = i;
                onChanged();
                return this;
            }

            public Builder clearErrCode() {
                this.errCode_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public int getProgress() {
                return this.progress_;
            }

            public Builder setProgress(int i) {
                this.progress_ = i;
                onChanged();
                return this;
            }

            public Builder clearProgress() {
                this.progress_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public int getTotal() {
                return this.total_;
            }

            public Builder setTotal(int i) {
                this.total_ = i;
                onChanged();
                return this;
            }

            public Builder clearTotal() {
                this.total_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public String getElvVer() {
                Object obj = this.elvVer_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.elvVer_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public ByteString getElvVerBytes() {
                Object obj = this.elvVer_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.elvVer_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setElvVer(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.elvVer_ = str;
                onChanged();
                return this;
            }

            public Builder clearElvVer() {
                this.elvVer_ = UpdateStateReqAck.getDefaultInstance().getElvVer();
                onChanged();
                return this;
            }

            public Builder setElvVerBytes(ByteString byteString) {
                if (byteString != null) {
                    UpdateStateReqAck.checkByteStringIsUtf8(byteString);
                    this.elvVer_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public int getProtocolVer() {
                return this.protocolVer_;
            }

            public Builder setProtocolVer(int i) {
                this.protocolVer_ = i;
                onChanged();
                return this;
            }

            public Builder clearProtocolVer() {
                this.protocolVer_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public String getTargetElvVer() {
                Object obj = this.targetElvVer_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.targetElvVer_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public ByteString getTargetElvVerBytes() {
                Object obj = this.targetElvVer_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.targetElvVer_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTargetElvVer(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.targetElvVer_ = str;
                onChanged();
                return this;
            }

            public Builder clearTargetElvVer() {
                this.targetElvVer_ = UpdateStateReqAck.getDefaultInstance().getTargetElvVer();
                onChanged();
                return this;
            }

            public Builder setTargetElvVerBytes(ByteString byteString) {
                if (byteString != null) {
                    UpdateStateReqAck.checkByteStringIsUtf8(byteString);
                    this.targetElvVer_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public String getHardwareType() {
                Object obj = this.hardwareType_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.hardwareType_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReqAckOrBuilder
            public ByteString getHardwareTypeBytes() {
                Object obj = this.hardwareType_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.hardwareType_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setHardwareType(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.hardwareType_ = str;
                onChanged();
                return this;
            }

            public Builder clearHardwareType() {
                this.hardwareType_ = UpdateStateReqAck.getDefaultInstance().getHardwareType();
                onChanged();
                return this;
            }

            public Builder setHardwareTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    UpdateStateReqAck.checkByteStringIsUtf8(byteString);
                    this.hardwareType_ = byteString;
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

        public static UpdateStateReqAck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateStateReqAck> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateStateReqAck> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateStateReqAck getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class UpdateStateReq extends GeneratedMessageV3 implements UpdateStateReqOrBuilder {
        private static final UpdateStateReq DEFAULT_INSTANCE = new UpdateStateReq();
        private static final Parser<UpdateStateReq> PARSER = new AbstractParser<UpdateStateReq>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.UpdateStateReq.1
            @Override // com.google.protobuf.Parser
            public UpdateStateReq parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdateStateReq(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;

        private UpdateStateReq(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private UpdateStateReq() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateStateReq(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
            return ElevatorProtocol.f5861x7dc6e59c;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5862xc34b01a.ensureFieldAccessorsInitialized(UpdateStateReq.class, Builder.class);
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
            if (obj instanceof UpdateStateReq) {
                return this.unknownFields.equals(((UpdateStateReq) obj).unknownFields);
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

        public static UpdateStateReq parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static UpdateStateReq parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static UpdateStateReq parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static UpdateStateReq parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdateStateReq parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static UpdateStateReq parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdateStateReq parseFrom(InputStream inputStream) throws IOException {
            return (UpdateStateReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static UpdateStateReq parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateStateReq) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateStateReq parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdateStateReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static UpdateStateReq parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateStateReq) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static UpdateStateReq parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdateStateReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static UpdateStateReq parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateStateReq) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UpdateStateReq updateStateReq) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(updateStateReq);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateStateReqOrBuilder {
            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5861x7dc6e59c;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5862xc34b01a.ensureFieldAccessorsInitialized(UpdateStateReq.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateStateReq.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5861x7dc6e59c;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateStateReq getDefaultInstanceForType() {
                return UpdateStateReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateStateReq build() {
                UpdateStateReq buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateStateReq buildPartial() {
                UpdateStateReq updateStateReq = new UpdateStateReq(this);
                onBuilt();
                return updateStateReq;
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
                if (message instanceof UpdateStateReq) {
                    return mergeFrom((UpdateStateReq) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdateStateReq updateStateReq) {
                if (updateStateReq == UpdateStateReq.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(updateStateReq.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                UpdateStateReq updateStateReq = null;
                try {
                    try {
                        UpdateStateReq updateStateReq2 = (UpdateStateReq) UpdateStateReq.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (updateStateReq2 != null) {
                            mergeFrom(updateStateReq2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        UpdateStateReq updateStateReq3 = (UpdateStateReq) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            updateStateReq = updateStateReq3;
                            if (updateStateReq != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (updateStateReq != null) {
                        mergeFrom(updateStateReq);
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

        public static UpdateStateReq getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UpdateStateReq> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateStateReq> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateStateReq getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* loaded from: classes5.dex */
    public static final class CallTargetFloor extends GeneratedMessageV3 implements CallTargetFloorOrBuilder {
        private static final CallTargetFloor DEFAULT_INSTANCE = new CallTargetFloor();
        private static final Parser<CallTargetFloor> PARSER = new AbstractParser<CallTargetFloor>() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CallTargetFloor.1
            @Override // com.google.protobuf.Parser
            public CallTargetFloor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CallTargetFloor(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TARGETFLOOR_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object targetFloor_;

        private CallTargetFloor(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CallTargetFloor() {
            this.memoizedIsInitialized = (byte) -1;
            this.targetFloor_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CallTargetFloor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.targetFloor_ = codedInputStream.readStringRequireUtf8();
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
            return ElevatorProtocol.f5827xe7c5712d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ElevatorProtocol.f5828xbb3b1aab.ensureFieldAccessorsInitialized(CallTargetFloor.class, Builder.class);
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CallTargetFloorOrBuilder
        public String getTargetFloor() {
            Object obj = this.targetFloor_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.targetFloor_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CallTargetFloorOrBuilder
        public ByteString getTargetFloorBytes() {
            Object obj = this.targetFloor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetFloor_ = copyFromUtf8;
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
            if (!getTargetFloorBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.targetFloor_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (getTargetFloorBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.targetFloor_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeStringSize;
            return computeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CallTargetFloor)) {
                return super.equals(obj);
            }
            CallTargetFloor callTargetFloor = (CallTargetFloor) obj;
            return (getTargetFloor().equals(callTargetFloor.getTargetFloor())) && this.unknownFields.equals(callTargetFloor.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTargetFloor().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static CallTargetFloor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CallTargetFloor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CallTargetFloor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CallTargetFloor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CallTargetFloor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CallTargetFloor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CallTargetFloor parseFrom(InputStream inputStream) throws IOException {
            return (CallTargetFloor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CallTargetFloor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallTargetFloor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallTargetFloor parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CallTargetFloor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CallTargetFloor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallTargetFloor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallTargetFloor parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CallTargetFloor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CallTargetFloor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallTargetFloor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CallTargetFloor callTargetFloor) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(callTargetFloor);
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

        /* loaded from: classes5.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallTargetFloorOrBuilder {
            private Object targetFloor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ElevatorProtocol.f5827xe7c5712d;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ElevatorProtocol.f5828xbb3b1aab.ensureFieldAccessorsInitialized(CallTargetFloor.class, Builder.class);
            }

            private Builder() {
                this.targetFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.targetFloor_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CallTargetFloor.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.targetFloor_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ElevatorProtocol.f5827xe7c5712d;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CallTargetFloor getDefaultInstanceForType() {
                return CallTargetFloor.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallTargetFloor build() {
                CallTargetFloor buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallTargetFloor buildPartial() {
                CallTargetFloor callTargetFloor = new CallTargetFloor(this);
                callTargetFloor.targetFloor_ = this.targetFloor_;
                onBuilt();
                return callTargetFloor;
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
                if (message instanceof CallTargetFloor) {
                    return mergeFrom((CallTargetFloor) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CallTargetFloor callTargetFloor) {
                if (callTargetFloor == CallTargetFloor.getDefaultInstance()) {
                    return this;
                }
                if (!callTargetFloor.getTargetFloor().isEmpty()) {
                    this.targetFloor_ = callTargetFloor.targetFloor_;
                    onChanged();
                }
                mergeUnknownFields(callTargetFloor.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CallTargetFloor callTargetFloor = null;
                try {
                    try {
                        CallTargetFloor callTargetFloor2 = (CallTargetFloor) CallTargetFloor.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (callTargetFloor2 != null) {
                            mergeFrom(callTargetFloor2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CallTargetFloor callTargetFloor3 = (CallTargetFloor) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            callTargetFloor = callTargetFloor3;
                            if (callTargetFloor != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (callTargetFloor != null) {
                        mergeFrom(callTargetFloor);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CallTargetFloorOrBuilder
            public String getTargetFloor() {
                Object obj = this.targetFloor_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.targetFloor_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.mirsdk.elv.proto.ElevatorProtocol.CallTargetFloorOrBuilder
            public ByteString getTargetFloorBytes() {
                Object obj = this.targetFloor_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.targetFloor_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTargetFloor(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.targetFloor_ = str;
                onChanged();
                return this;
            }

            public Builder clearTargetFloor() {
                this.targetFloor_ = CallTargetFloor.getDefaultInstance().getTargetFloor();
                onChanged();
                return this;
            }

            public Builder setTargetFloorBytes(ByteString byteString) {
                if (byteString != null) {
                    CallTargetFloor.checkByteStringIsUtf8(byteString);
                    this.targetFloor_ = byteString;
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

        public static CallTargetFloor getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CallTargetFloor> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CallTargetFloor> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CallTargetFloor getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0016ElevatorProtocol.proto\u0012\u001dcom.pudutech.mirsdk.elv.proto\"\u0084\r\n\nElvMessage\u0012\u000e\n\u0006elv_id\u0018\u0001 \u0001(\t\u0012\u0010\n\brobot_id\u0018\u0002 \u0001(\t\u0012\u000b\n\u0003seq\u0018\u0003 \u0001(\u0004\u0012\n\n\u0002ts\u0018\u0004 \u0001(\u0004\u0012C\n\bmsg_type\u0018\u0005 \u0001(\u000e21.com.pudutech.mirsdk.elv.proto.ElvMessage.MsgType\u0012>\n\u000bconnect_req\u0018\u0006 \u0001(\u000b2).com.pudutech.mirsdk.elv.proto.ConnectReq\u0012E\n\u000fconnect_req_ack\u0018\u0007 \u0001(\u000b2,.com.pudutech.mirsdk.elv.proto.ConnectReqAck\u0012D\n\u000edisconnect_req\u0018\b \u0001(\u000b2,.com.pudutech.mirsdk.elv.proto.DisconnectReq\u0012K\n\u0012disconnect_req_ack\u0018\t \u0001(\u000b2/.com.pudutech.mirsdk.elv.proto.DisconnectReqAck\u0012<\n\nheart_beat\u0018\n \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.HeartBeat\u0012C\n\u000eheart_beat_ack\u0018\u000b \u0001(\u000b2+.com.pudutech.mirsdk.elv.proto.HeartBeatAck\u0012?\n\fgo_floor_req\u0018\f \u0001(\u000b2).com.pudutech.mirsdk.elv.proto.GoFloorReq\u0012F\n\u0010go_floor_req_ack\u0018\r \u0001(\u000b2,.com.pudutech.mirsdk.elv.proto.GoFloorReqAck\u0012>\n\u000belv_arrived\u0018\u000e \u0001(\u000b2).com.pudutech.mirsdk.elv.proto.ElvArrived\u0012E\n\u000felv_arrived_ack\u0018\u000f \u0001(\u000b2,.com.pudutech.mirsdk.elv.proto.ElvArrivedAck\u0012G\n\u0010cancel_floor_req\u0018\u0010 \u0001(\u000b2-.com.pudutech.mirsdk.elv.proto.CancelFloorReq\u0012N\n\u0014cancel_floor_req_ack\u0018\u0011 \u0001(\u000b20.com.pudutech.mirsdk.elv.proto.CancelFloorReqAck\u0012<\n\nupdate_req\u0018\u0012 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.UpdateReq\u0012C\n\u000eupdate_req_ack\u0018\u0013 \u0001(\u000b2+.com.pudutech.mirsdk.elv.proto.UpdateReqAck\u0012G\n\u0010update_state_req\u0018\u0014 \u0001(\u000b2-.com.pudutech.mirsdk.elv.proto.UpdateStateReq\u0012N\n\u0014update_state_req_ack\u0018\u0015 \u0001(\u000b20.com.pudutech.mirsdk.elv.proto.UpdateStateReqAck\u0012I\n\u0011call_target_floor\u0018\u0016 \u0001(\u000b2..com.pudutech.mirsdk.elv.proto.CallTargetFloor\"È\u0002\n\u0007MsgType\u0012\u000e\n\nConnectReq\u0010\u0000\u0012\u0011\n\rConnectReqAck\u0010\u0001\u0012\u0011\n\rDisconnectReq\u0010\u0002\u0012\u0014\n\u0010DisconnectReqAck\u0010\u0003\u0012\r\n\tHeartBeat\u0010\u0004\u0012\u0010\n\fHeartBeatAck\u0010\u0005\u0012\u000e\n\nGoFloorReq\u0010\u0006\u0012\u0011\n\rGoFloorReqAck\u0010\u0007\u0012\u000e\n\nElvArrived\u0010\b\u0012\u0011\n\rElvArrivedAck\u0010\t\u0012\u0012\n\u000eCancelFloorReq\u0010\n\u0012\u0015\n\u0011CancelFloorReqAck\u0010\u000b\u0012\r\n\tUpdateReq\u0010\f\u0012\u0010\n\fUpdateReqAck\u0010\r\u0012\u0012\n\u000eUpdateStateReq\u0010\u000e\u0012\u0015\n\u0011UpdateStateReqAck\u0010\u000f\u0012\u0013\n\u000fCallTargetFloor\u0010\u0010\"Ã\u0001\n\tAckResult\u0012>\n\u0004code\u0018\u0001 \u0001(\u000e20.com.pudutech.mirsdk.elv.proto.AckResult.AckCode\u0012\u0014\n\fcur_robot_id\u0018\u0002 \u0001(\t\"`\n\u0007AckCode\u0012\u000b\n\u0007Success\u0010\u0000\u0012\u000f\n\u000bNoConnected\u0010\u0001\u0012\r\n\tInWorking\u0010\u0002\u0012\u000b\n\u0007InPause\u0010\u0003\u0012\r\n\tOtherFail\u0010\u0004\u0012\f\n\bUpdating\u0010\u0005\"\u001c\n\nConnectReq\u0012\u000e\n\u0006weight\u0018\u0001 \u0001(\u0005\"\u0097\u0001\n\rConnectReqAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\u0012\u000e\n\u0006weight\u0018\u0002 \u0001(\u0005\u0012\u000f\n\u0007elv_ver\u0018\u0003 \u0001(\t\u0012\u0014\n\fprotocol_ver\u0018\u0004 \u0001(\u0005\u0012\u0015\n\rhardware_type\u0018\u0005 \u0001(\t\"\u000f\n\rDisconnectReq\"L\n\u0010DisconnectReqAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\"À\u0001\n\tHeartBeat\u0012B\n\u0005state\u0018\u0001 \u0001(\u000e23.com.pudutech.mirsdk.elv.proto.HeartBeat.RobotState\u0012\u0011\n\tdestFloor\u0018\u0002 \u0001(\t\"\\\n\nRobotState\u0012\u000b\n\u0007CallElv\u0010\u0000\u0012\u000b\n\u0007WaitElv\u0010\u0001\u0012\f\n\bEnterElv\u0010\u0002\u0012\t\n\u0005InElv\u0010\u0003\u0012\f\n\bLeaveElv\u0010\u0004\u0012\r\n\tNoNeedElv\u0010\u0005\"[\n\fHeartBeatAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\u0012\u0011\n\tdestFloor\u0018\u0002 \u0001(\t\"®\u0001\n\nGoFloorReq\u0012\u0013\n\u000btargetFloor\u0018\u0001 \u0001(\t\u0012@\n\u0006action\u0018\u0002 \u0001(\u000e20.com.pudutech.mirsdk.elv.proto.GoFloorReq.Action\u0012\u0010\n\bcurFloor\u0018\u0003 \u0001(\t\u0012\u0011\n\tdestFloor\u0018\u0004 \u0001(\t\"$\n\u0006Action\u0012\f\n\bEnterElv\u0010\u0000\u0012\f\n\bLeaveElv\u0010\u0001\"I\n\rGoFloorReqAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\"4\n\nElvArrived\u0012\u0014\n\farrivedFloor\u0018\u0001 \u0001(\t\u0012\u0010\n\beff_time\u0018\u0002 \u0001(\u0005\"I\n\rElvArrivedAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\"\u0010\n\u000eCancelFloorReq\"M\n\u0011CancelFloorReqAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\"#\n\tUpdateReq\u0012\u0016\n\u000etarget_elv_ver\u0018\u0001 \u0001(\t\"H\n\fUpdateReqAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\"µ\u0003\n\u0011UpdateStateReqAck\u00128\n\u0006result\u0018\u0001 \u0001(\u000b2(.com.pudutech.mirsdk.elv.proto.AckResult\u0012K\n\u0005state\u0018\u0002 \u0001(\u000e2<.com.pudutech.mirsdk.elv.proto.UpdateStateReqAck.UpdateState\u0012\u0010\n\berr_code\u0018\u0003 \u0001(\u0005\u0012\u0010\n\bprogress\u0018\u0004 \u0001(\u0005\u0012\r\n\u0005total\u0018\u0005 \u0001(\u0005\u0012\u000f\n\u0007elv_ver\u0018\u0006 \u0001(\t\u0012\u0014\n\fprotocol_ver\u0018\u0007 \u0001(\u0005\u0012\u0016\n\u000etarget_elv_ver\u0018\b \u0001(\t\u0012\u0015\n\rhardware_type\u0018\t \u0001(\t\"\u008f\u0001\n\u000bUpdateState\u0012\b\n\u0004Free\u0010\u0000\u0012\u0014\n\u0010Downloading_File\u0010\u0001\u0012\u0011\n\rExtract_Files\u0010\u0002\u0012\r\n\tFlash_Key\u0010\u0003\u0012\u000e\n\nFlash_Rfid\u0010\u0004\u0012\u0010\n\fFlash_Master\u0010\u0005\u0012\u000b\n\u0007Restart\u0010\u0006\u0012\u000f\n\u000bUpdate_Fail\u0010\u0007\"\u0010\n\u000eUpdateStateReq\"&\n\u000fCallTargetFloor\u0012\u0013\n\u000btargetFloor\u0018\u0001 \u0001(\tb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.pudutech.mirsdk.elv.proto.ElevatorProtocol.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ElevatorProtocol.descriptor = fileDescriptor;
                return null;
            }
        });
        f5845x5c476c3a = getDescriptor().getMessageTypes().get(0);
        f5846xbf0bb8b8 = new GeneratedMessageV3.FieldAccessorTable(f5845x5c476c3a, new String[]{"ElvId", "RobotId", "Seq", "Ts", "MsgType", "ConnectReq", "ConnectReqAck", "DisconnectReq", "DisconnectReqAck", "HeartBeat", "HeartBeatAck", "GoFloorReq", "GoFloorReqAck", "ElvArrived", "ElvArrivedAck", "CancelFloorReq", "CancelFloorReqAck", "UpdateReq", "UpdateReqAck", "UpdateStateReq", "UpdateStateReqAck", "CallTargetFloor"});
        f5825xff036044 = getDescriptor().getMessageTypes().get(1);
        f5826x7b4002c2 = new GeneratedMessageV3.FieldAccessorTable(f5825xff036044, new String[]{AttributeLayout.ATTRIBUTE_CODE, "CurRobotId"});
        f5835x67828bbe = getDescriptor().getMessageTypes().get(2);
        f5836x1c01143c = new GeneratedMessageV3.FieldAccessorTable(f5835x67828bbe, new String[]{"Weight"});
        f5833x7ebc5c55 = getDescriptor().getMessageTypes().get(3);
        f5834xf8c85dd3 = new GeneratedMessageV3.FieldAccessorTable(f5833x7ebc5c55, new String[]{"Result", "Weight", "ElvVer", "ProtocolVer", "HardwareType"});
        f5839x6ccc0068 = getDescriptor().getMessageTypes().get(4);
        f5840x40083ee6 = new GeneratedMessageV3.FieldAccessorTable(f5839x6ccc0068, new String[0]);
        f5837xbddbaf6b = getDescriptor().getMessageTypes().get(5);
        f5838x96c63ae9 = new GeneratedMessageV3.FieldAccessorTable(f5837xbddbaf6b, new String[]{"Result"});
        f5853xfe5cb36e = getDescriptor().getMessageTypes().get(6);
        f5854x94d08bec = new GeneratedMessageV3.FieldAccessorTable(f5853xfe5cb36e, new String[]{"State", "DestFloor"});
        f5851x53acd6a5 = getDescriptor().getMessageTypes().get(7);
        f5852xd0018823 = new GeneratedMessageV3.FieldAccessorTable(f5851x53acd6a5, new String[]{"Result", "DestFloor"});
        f5849xf3e59558 = getDescriptor().getMessageTypes().get(8);
        f5850x2e75e3d6 = new GeneratedMessageV3.FieldAccessorTable(f5849xf3e59558, new String[]{"TargetFloor", JsonDocumentFields.ACTION, "CurFloor", "DestFloor"});
        f5847x77d6b47b = getDescriptor().getMessageTypes().get(9);
        f5848xc0332ff9 = new GeneratedMessageV3.FieldAccessorTable(f5847x77d6b47b, new String[]{"Result"});
        f5843x7c66ec14 = getDescriptor().getMessageTypes().get(10);
        f5844x8582be92 = new GeneratedMessageV3.FieldAccessorTable(f5843x7c66ec14, new String[]{"ArrivedFloor", "EffTime"});
        f5841xbb23143f = getDescriptor().getMessageTypes().get(11);
        f5842xe1158bbd = new GeneratedMessageV3.FieldAccessorTable(f5841xbb23143f, new String[]{"Result"});
        f5831xf51b3486 = getDescriptor().getMessageTypes().get(12);
        f5832x4fe87504 = new GeneratedMessageV3.FieldAccessorTable(f5831xf51b3486, new String[0]);
        f5829x36dd9e8d = getDescriptor().getMessageTypes().get(13);
        f5830x137fe80b = new GeneratedMessageV3.FieldAccessorTable(f5829x36dd9e8d, new String[]{"Result"});
        f5857xb2d56bd5 = getDescriptor().getMessageTypes().get(14);
        f5858x2899ed53 = new GeneratedMessageV3.FieldAccessorTable(f5857xb2d56bd5, new String[]{"TargetElvVer"});
        f5855x7f0de = getDescriptor().getMessageTypes().get(15);
        f5856xe7df595c = new GeneratedMessageV3.FieldAccessorTable(f5855x7f0de, new String[]{"Result"});
        f5859xbad24db7 = getDescriptor().getMessageTypes().get(16);
        f5860x7e8fcd35 = new GeneratedMessageV3.FieldAccessorTable(f5859xbad24db7, new String[]{"Result", "State", "ErrCode", "Progress", "Total", "ElvVer", "ProtocolVer", "TargetElvVer", "HardwareType"});
        f5861x7dc6e59c = getDescriptor().getMessageTypes().get(17);
        f5862xc34b01a = new GeneratedMessageV3.FieldAccessorTable(f5861x7dc6e59c, new String[0]);
        f5827xe7c5712d = getDescriptor().getMessageTypes().get(18);
        f5828xbb3b1aab = new GeneratedMessageV3.FieldAccessorTable(f5827xe7c5712d, new String[]{"TargetFloor"});
    }
}
