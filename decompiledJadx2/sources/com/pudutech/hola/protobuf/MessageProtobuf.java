package com.pudutech.hola.protobuf;

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
  classes5.dex
 */
/* loaded from: classes.dex */
public final class MessageProtobuf {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\tmsg.proto\"ê\u0003\n\u0003Msg\u0012\r\n\u0005msgId\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007msgType\u0018\u0002 \u0001(\u0005\u0012\u000e\n\u0006sender\u0018\u0003 \u0001(\t\u0012\u0010\n\breceiver\u0018\u0004 \u0001(\t\u0012\u0011\n\ttimestamp\u0018\u0005 \u0001(\u0003\u0012\u0013\n\u0004task\u0018\u0006 \u0001(\u000b2\u0005.Task\u0012\u001b\n\btaskList\u0018\u0007 \u0001(\u000b2\t.TaskList\u0012\u001d\n\tcallPoint\u0018\b \u0001(\u000b2\n.CallPoint\u0012%\n\rcallPointList\u0018\t \u0001(\u000b2\u000e.CallPointList\u0012\u0019\n\u0007channel\u0018\n \u0001(\u000b2\b.Channel\u0012!\n\u000bchannelList\u0018\u000b \u0001(\u000b2\f.ChannelList\u0012+\n\u0010broadcastControl\u0018\f \u0001(\u000b2\u0011.BroadcastControl\u0012-\n\u0011robotStatusReport\u0018\r \u0001(\u000b2\u0012.RobotStatusReport\u0012\u0012\n\nmapVersion\u0018\u000e \u0001(\t\u0012\u0014\n\fmapFrameHead\u0018\u000f \u0001(\t\u0012\u0014\n\fmapFrameNext\u0018\u0010 \u0001(\t\u0012\u0012\n\nisFrameEnd\u0018\u0011 \u0001(\b\u0012\u001a\n\u0012hasUnallocatedTask\u0018\u0012 \u0001(\b\u0012\f\n\u0004resp\u0018\u0013 \u0001(\b\"%\n\u0004Task\u0012\u000e\n\u0006taskId\u0018\u0001 \u0001(\t\u0012\r\n\u0005state\u0018\u0002 \u0001(\u0005\"\u001f\n\bTaskList\u0012\u0013\n\u0004task\u0018\u0001 \u0003(\u000b2\u0005.Task\".\n\tCallPoint\u0012\u0013\n\u000bcallPointId\u0018\u0001 \u0001(\t\u0012\f\n\u0004type\u0018\u0002 \u0001(\u0005\".\n\rCallPointList\u0012\u001d\n\tcallPoint\u0018\u0001 \u0003(\u000b2\n.CallPoint\"\u001c\n\u0007Channel\u0012\u0011\n\tchannelId\u0018\u0001 \u0001(\u0005\"(\n\u000bChannelList\u0012\u0019\n\u0007channel\u0018\u0001 \u0003(\u000b2\b.Channel\"K\n\u0010BroadcastControl\u0012\u000e\n\u0006enable\u0018\u0001 \u0001(\b\u0012\u000b\n\u0003mac\u0018\u0002 \u0001(\t\u0012\u001a\n\u0012centralControlCode\u0018\u0003 \u0001(\t\"'\n\u0011RobotStatusReport\u0012\u0012\n\nrobotState\u0018\u0001 \u0001(\u0005B-\n\u001acom.pudutech.hola.protobufB\u000fMessageProtobufb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_BroadcastControl_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_BroadcastControl_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_CallPointList_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_CallPointList_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_CallPoint_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_CallPoint_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_ChannelList_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_ChannelList_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Channel_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Channel_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Msg_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Msg_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_RobotStatusReport_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_RobotStatusReport_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_TaskList_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_TaskList_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_Task_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_Task_fieldAccessorTable;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface BroadcastControlOrBuilder extends MessageOrBuilder {
        String getCentralControlCode();

        ByteString getCentralControlCodeBytes();

        boolean getEnable();

        String getMac();

        ByteString getMacBytes();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface CallPointListOrBuilder extends MessageOrBuilder {
        CallPoint getCallPoint(int i);

        int getCallPointCount();

        List<CallPoint> getCallPointList();

        CallPointOrBuilder getCallPointOrBuilder(int i);

        List<? extends CallPointOrBuilder> getCallPointOrBuilderList();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface CallPointOrBuilder extends MessageOrBuilder {
        String getCallPointId();

        ByteString getCallPointIdBytes();

        int getType();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface ChannelListOrBuilder extends MessageOrBuilder {
        Channel getChannel(int i);

        int getChannelCount();

        List<Channel> getChannelList();

        ChannelOrBuilder getChannelOrBuilder(int i);

        List<? extends ChannelOrBuilder> getChannelOrBuilderList();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface ChannelOrBuilder extends MessageOrBuilder {
        int getChannelId();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface MsgOrBuilder extends MessageOrBuilder {
        BroadcastControl getBroadcastControl();

        BroadcastControlOrBuilder getBroadcastControlOrBuilder();

        CallPoint getCallPoint();

        CallPointList getCallPointList();

        CallPointListOrBuilder getCallPointListOrBuilder();

        CallPointOrBuilder getCallPointOrBuilder();

        Channel getChannel();

        ChannelList getChannelList();

        ChannelListOrBuilder getChannelListOrBuilder();

        ChannelOrBuilder getChannelOrBuilder();

        boolean getHasUnallocatedTask();

        boolean getIsFrameEnd();

        String getMapFrameHead();

        ByteString getMapFrameHeadBytes();

        String getMapFrameNext();

        ByteString getMapFrameNextBytes();

        String getMapVersion();

        ByteString getMapVersionBytes();

        String getMsgId();

        ByteString getMsgIdBytes();

        int getMsgType();

        String getReceiver();

        ByteString getReceiverBytes();

        boolean getResp();

        RobotStatusReport getRobotStatusReport();

        RobotStatusReportOrBuilder getRobotStatusReportOrBuilder();

        String getSender();

        ByteString getSenderBytes();

        Task getTask();

        TaskList getTaskList();

        TaskListOrBuilder getTaskListOrBuilder();

        TaskOrBuilder getTaskOrBuilder();

        long getTimestamp();

        boolean hasBroadcastControl();

        boolean hasCallPoint();

        boolean hasCallPointList();

        boolean hasChannel();

        boolean hasChannelList();

        boolean hasRobotStatusReport();

        boolean hasTask();

        boolean hasTaskList();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface RobotStatusReportOrBuilder extends MessageOrBuilder {
        int getRobotState();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface TaskListOrBuilder extends MessageOrBuilder {
        Task getTask(int i);

        int getTaskCount();

        List<Task> getTaskList();

        TaskOrBuilder getTaskOrBuilder(int i);

        List<? extends TaskOrBuilder> getTaskOrBuilderList();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface TaskOrBuilder extends MessageOrBuilder {
        int getState();

        String getTaskId();

        ByteString getTaskIdBytes();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private MessageProtobuf() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class Msg extends GeneratedMessageV3 implements MsgOrBuilder {
        public static final int BROADCASTCONTROL_FIELD_NUMBER = 12;
        public static final int CALLPOINTLIST_FIELD_NUMBER = 9;
        public static final int CALLPOINT_FIELD_NUMBER = 8;
        public static final int CHANNELLIST_FIELD_NUMBER = 11;
        public static final int CHANNEL_FIELD_NUMBER = 10;
        public static final int HASUNALLOCATEDTASK_FIELD_NUMBER = 18;
        public static final int ISFRAMEEND_FIELD_NUMBER = 17;
        public static final int MAPFRAMEHEAD_FIELD_NUMBER = 15;
        public static final int MAPFRAMENEXT_FIELD_NUMBER = 16;
        public static final int MAPVERSION_FIELD_NUMBER = 14;
        public static final int MSGID_FIELD_NUMBER = 1;
        public static final int MSGTYPE_FIELD_NUMBER = 2;
        public static final int RECEIVER_FIELD_NUMBER = 4;
        public static final int RESP_FIELD_NUMBER = 19;
        public static final int ROBOTSTATUSREPORT_FIELD_NUMBER = 13;
        public static final int SENDER_FIELD_NUMBER = 3;
        public static final int TASKLIST_FIELD_NUMBER = 7;
        public static final int TASK_FIELD_NUMBER = 6;
        public static final int TIMESTAMP_FIELD_NUMBER = 5;
        private static final long serialVersionUID = 0;
        private BroadcastControl broadcastControl_;
        private CallPointList callPointList_;
        private CallPoint callPoint_;
        private ChannelList channelList_;
        private Channel channel_;
        private boolean hasUnallocatedTask_;
        private boolean isFrameEnd_;
        private volatile Object mapFrameHead_;
        private volatile Object mapFrameNext_;
        private volatile Object mapVersion_;
        private byte memoizedIsInitialized;
        private volatile Object msgId_;
        private int msgType_;
        private volatile Object receiver_;
        private boolean resp_;
        private RobotStatusReport robotStatusReport_;
        private volatile Object sender_;
        private TaskList taskList_;
        private Task task_;
        private long timestamp_;
        private static final Msg DEFAULT_INSTANCE = new Msg();
        private static final Parser<Msg> PARSER = new AbstractParser<Msg>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.Msg.1
            @Override // com.google.protobuf.Parser
            public Msg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Msg(codedInputStream, extensionRegistryLite);
            }
        };

        private Msg(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Msg() {
            this.memoizedIsInitialized = (byte) -1;
            this.msgId_ = "";
            this.sender_ = "";
            this.receiver_ = "";
            this.mapVersion_ = "";
            this.mapFrameHead_ = "";
            this.mapFrameNext_ = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Msg();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0012. Please report as an issue. */
        private Msg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        switch (readTag) {
                            case 0:
                                z = true;
                            case 10:
                                this.msgId_ = codedInputStream.readStringRequireUtf8();
                            case 16:
                                this.msgType_ = codedInputStream.readInt32();
                            case 26:
                                this.sender_ = codedInputStream.readStringRequireUtf8();
                            case 34:
                                this.receiver_ = codedInputStream.readStringRequireUtf8();
                            case 40:
                                this.timestamp_ = codedInputStream.readInt64();
                            case 50:
                                Task.Builder builder = this.task_ != null ? this.task_.toBuilder() : null;
                                Task task = (Task) codedInputStream.readMessage(Task.parser(), extensionRegistryLite);
                                this.task_ = task;
                                if (builder != null) {
                                    builder.mergeFrom(task);
                                    this.task_ = builder.buildPartial();
                                }
                            case 58:
                                TaskList.Builder builder2 = this.taskList_ != null ? this.taskList_.toBuilder() : null;
                                TaskList taskList = (TaskList) codedInputStream.readMessage(TaskList.parser(), extensionRegistryLite);
                                this.taskList_ = taskList;
                                if (builder2 != null) {
                                    builder2.mergeFrom(taskList);
                                    this.taskList_ = builder2.buildPartial();
                                }
                            case 66:
                                CallPoint.Builder builder3 = this.callPoint_ != null ? this.callPoint_.toBuilder() : null;
                                CallPoint callPoint = (CallPoint) codedInputStream.readMessage(CallPoint.parser(), extensionRegistryLite);
                                this.callPoint_ = callPoint;
                                if (builder3 != null) {
                                    builder3.mergeFrom(callPoint);
                                    this.callPoint_ = builder3.buildPartial();
                                }
                            case 74:
                                CallPointList.Builder builder4 = this.callPointList_ != null ? this.callPointList_.toBuilder() : null;
                                CallPointList callPointList = (CallPointList) codedInputStream.readMessage(CallPointList.parser(), extensionRegistryLite);
                                this.callPointList_ = callPointList;
                                if (builder4 != null) {
                                    builder4.mergeFrom(callPointList);
                                    this.callPointList_ = builder4.buildPartial();
                                }
                            case 82:
                                Channel.Builder builder5 = this.channel_ != null ? this.channel_.toBuilder() : null;
                                Channel channel = (Channel) codedInputStream.readMessage(Channel.parser(), extensionRegistryLite);
                                this.channel_ = channel;
                                if (builder5 != null) {
                                    builder5.mergeFrom(channel);
                                    this.channel_ = builder5.buildPartial();
                                }
                            case 90:
                                ChannelList.Builder builder6 = this.channelList_ != null ? this.channelList_.toBuilder() : null;
                                ChannelList channelList = (ChannelList) codedInputStream.readMessage(ChannelList.parser(), extensionRegistryLite);
                                this.channelList_ = channelList;
                                if (builder6 != null) {
                                    builder6.mergeFrom(channelList);
                                    this.channelList_ = builder6.buildPartial();
                                }
                            case 98:
                                BroadcastControl.Builder builder7 = this.broadcastControl_ != null ? this.broadcastControl_.toBuilder() : null;
                                BroadcastControl broadcastControl = (BroadcastControl) codedInputStream.readMessage(BroadcastControl.parser(), extensionRegistryLite);
                                this.broadcastControl_ = broadcastControl;
                                if (builder7 != null) {
                                    builder7.mergeFrom(broadcastControl);
                                    this.broadcastControl_ = builder7.buildPartial();
                                }
                            case 106:
                                RobotStatusReport.Builder builder8 = this.robotStatusReport_ != null ? this.robotStatusReport_.toBuilder() : null;
                                RobotStatusReport robotStatusReport = (RobotStatusReport) codedInputStream.readMessage(RobotStatusReport.parser(), extensionRegistryLite);
                                this.robotStatusReport_ = robotStatusReport;
                                if (builder8 != null) {
                                    builder8.mergeFrom(robotStatusReport);
                                    this.robotStatusReport_ = builder8.buildPartial();
                                }
                            case 114:
                                this.mapVersion_ = codedInputStream.readStringRequireUtf8();
                            case 122:
                                this.mapFrameHead_ = codedInputStream.readStringRequireUtf8();
                            case 130:
                                this.mapFrameNext_ = codedInputStream.readStringRequireUtf8();
                            case 136:
                                this.isFrameEnd_ = codedInputStream.readBool();
                            case 144:
                                this.hasUnallocatedTask_ = codedInputStream.readBool();
                            case 152:
                                this.resp_ = codedInputStream.readBool();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MessageProtobuf.internal_static_Msg_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_Msg_fieldAccessorTable.ensureFieldAccessorsInitialized(Msg.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public String getMsgId() {
            Object obj = this.msgId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.msgId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ByteString getMsgIdBytes() {
            Object obj = this.msgId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.msgId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public int getMsgType() {
            return this.msgType_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public String getSender() {
            Object obj = this.sender_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.sender_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ByteString getSenderBytes() {
            Object obj = this.sender_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sender_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public String getReceiver() {
            Object obj = this.receiver_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.receiver_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ByteString getReceiverBytes() {
            Object obj = this.receiver_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.receiver_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasTask() {
            return this.task_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public Task getTask() {
            Task task = this.task_;
            return task == null ? Task.getDefaultInstance() : task;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public TaskOrBuilder getTaskOrBuilder() {
            return getTask();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasTaskList() {
            return this.taskList_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public TaskList getTaskList() {
            TaskList taskList = this.taskList_;
            return taskList == null ? TaskList.getDefaultInstance() : taskList;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public TaskListOrBuilder getTaskListOrBuilder() {
            return getTaskList();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasCallPoint() {
            return this.callPoint_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public CallPoint getCallPoint() {
            CallPoint callPoint = this.callPoint_;
            return callPoint == null ? CallPoint.getDefaultInstance() : callPoint;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public CallPointOrBuilder getCallPointOrBuilder() {
            return getCallPoint();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasCallPointList() {
            return this.callPointList_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public CallPointList getCallPointList() {
            CallPointList callPointList = this.callPointList_;
            return callPointList == null ? CallPointList.getDefaultInstance() : callPointList;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public CallPointListOrBuilder getCallPointListOrBuilder() {
            return getCallPointList();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasChannel() {
            return this.channel_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public Channel getChannel() {
            Channel channel = this.channel_;
            return channel == null ? Channel.getDefaultInstance() : channel;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ChannelOrBuilder getChannelOrBuilder() {
            return getChannel();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasChannelList() {
            return this.channelList_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ChannelList getChannelList() {
            ChannelList channelList = this.channelList_;
            return channelList == null ? ChannelList.getDefaultInstance() : channelList;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ChannelListOrBuilder getChannelListOrBuilder() {
            return getChannelList();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasBroadcastControl() {
            return this.broadcastControl_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public BroadcastControl getBroadcastControl() {
            BroadcastControl broadcastControl = this.broadcastControl_;
            return broadcastControl == null ? BroadcastControl.getDefaultInstance() : broadcastControl;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public BroadcastControlOrBuilder getBroadcastControlOrBuilder() {
            return getBroadcastControl();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean hasRobotStatusReport() {
            return this.robotStatusReport_ != null;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public RobotStatusReport getRobotStatusReport() {
            RobotStatusReport robotStatusReport = this.robotStatusReport_;
            return robotStatusReport == null ? RobotStatusReport.getDefaultInstance() : robotStatusReport;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public RobotStatusReportOrBuilder getRobotStatusReportOrBuilder() {
            return getRobotStatusReport();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public String getMapVersion() {
            Object obj = this.mapVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.mapVersion_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ByteString getMapVersionBytes() {
            Object obj = this.mapVersion_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mapVersion_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public String getMapFrameHead() {
            Object obj = this.mapFrameHead_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.mapFrameHead_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ByteString getMapFrameHeadBytes() {
            Object obj = this.mapFrameHead_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mapFrameHead_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public String getMapFrameNext() {
            Object obj = this.mapFrameNext_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.mapFrameNext_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public ByteString getMapFrameNextBytes() {
            Object obj = this.mapFrameNext_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mapFrameNext_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean getIsFrameEnd() {
            return this.isFrameEnd_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean getHasUnallocatedTask() {
            return this.hasUnallocatedTask_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
        public boolean getResp() {
            return this.resp_;
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
            if (!getMsgIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.msgId_);
            }
            int i = this.msgType_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            if (!getSenderBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.sender_);
            }
            if (!getReceiverBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.receiver_);
            }
            long j = this.timestamp_;
            if (j != 0) {
                codedOutputStream.writeInt64(5, j);
            }
            if (this.task_ != null) {
                codedOutputStream.writeMessage(6, getTask());
            }
            if (this.taskList_ != null) {
                codedOutputStream.writeMessage(7, getTaskList());
            }
            if (this.callPoint_ != null) {
                codedOutputStream.writeMessage(8, getCallPoint());
            }
            if (this.callPointList_ != null) {
                codedOutputStream.writeMessage(9, getCallPointList());
            }
            if (this.channel_ != null) {
                codedOutputStream.writeMessage(10, getChannel());
            }
            if (this.channelList_ != null) {
                codedOutputStream.writeMessage(11, getChannelList());
            }
            if (this.broadcastControl_ != null) {
                codedOutputStream.writeMessage(12, getBroadcastControl());
            }
            if (this.robotStatusReport_ != null) {
                codedOutputStream.writeMessage(13, getRobotStatusReport());
            }
            if (!getMapVersionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 14, this.mapVersion_);
            }
            if (!getMapFrameHeadBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 15, this.mapFrameHead_);
            }
            if (!getMapFrameNextBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 16, this.mapFrameNext_);
            }
            boolean z = this.isFrameEnd_;
            if (z) {
                codedOutputStream.writeBool(17, z);
            }
            boolean z2 = this.hasUnallocatedTask_;
            if (z2) {
                codedOutputStream.writeBool(18, z2);
            }
            boolean z3 = this.resp_;
            if (z3) {
                codedOutputStream.writeBool(19, z3);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getMsgIdBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.msgId_);
            int i2 = this.msgType_;
            if (i2 != 0) {
                computeStringSize += CodedOutputStream.computeInt32Size(2, i2);
            }
            if (!getSenderBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(3, this.sender_);
            }
            if (!getReceiverBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(4, this.receiver_);
            }
            long j = this.timestamp_;
            if (j != 0) {
                computeStringSize += CodedOutputStream.computeInt64Size(5, j);
            }
            if (this.task_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(6, getTask());
            }
            if (this.taskList_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(7, getTaskList());
            }
            if (this.callPoint_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(8, getCallPoint());
            }
            if (this.callPointList_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(9, getCallPointList());
            }
            if (this.channel_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(10, getChannel());
            }
            if (this.channelList_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(11, getChannelList());
            }
            if (this.broadcastControl_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(12, getBroadcastControl());
            }
            if (this.robotStatusReport_ != null) {
                computeStringSize += CodedOutputStream.computeMessageSize(13, getRobotStatusReport());
            }
            if (!getMapVersionBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(14, this.mapVersion_);
            }
            if (!getMapFrameHeadBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(15, this.mapFrameHead_);
            }
            if (!getMapFrameNextBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(16, this.mapFrameNext_);
            }
            boolean z = this.isFrameEnd_;
            if (z) {
                computeStringSize += CodedOutputStream.computeBoolSize(17, z);
            }
            boolean z2 = this.hasUnallocatedTask_;
            if (z2) {
                computeStringSize += CodedOutputStream.computeBoolSize(18, z2);
            }
            boolean z3 = this.resp_;
            if (z3) {
                computeStringSize += CodedOutputStream.computeBoolSize(19, z3);
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
            if (!(obj instanceof Msg)) {
                return super.equals(obj);
            }
            Msg msg = (Msg) obj;
            if (!getMsgId().equals(msg.getMsgId()) || getMsgType() != msg.getMsgType() || !getSender().equals(msg.getSender()) || !getReceiver().equals(msg.getReceiver()) || getTimestamp() != msg.getTimestamp() || hasTask() != msg.hasTask()) {
                return false;
            }
            if ((hasTask() && !getTask().equals(msg.getTask())) || hasTaskList() != msg.hasTaskList()) {
                return false;
            }
            if ((hasTaskList() && !getTaskList().equals(msg.getTaskList())) || hasCallPoint() != msg.hasCallPoint()) {
                return false;
            }
            if ((hasCallPoint() && !getCallPoint().equals(msg.getCallPoint())) || hasCallPointList() != msg.hasCallPointList()) {
                return false;
            }
            if ((hasCallPointList() && !getCallPointList().equals(msg.getCallPointList())) || hasChannel() != msg.hasChannel()) {
                return false;
            }
            if ((hasChannel() && !getChannel().equals(msg.getChannel())) || hasChannelList() != msg.hasChannelList()) {
                return false;
            }
            if ((hasChannelList() && !getChannelList().equals(msg.getChannelList())) || hasBroadcastControl() != msg.hasBroadcastControl()) {
                return false;
            }
            if ((!hasBroadcastControl() || getBroadcastControl().equals(msg.getBroadcastControl())) && hasRobotStatusReport() == msg.hasRobotStatusReport()) {
                return (!hasRobotStatusReport() || getRobotStatusReport().equals(msg.getRobotStatusReport())) && getMapVersion().equals(msg.getMapVersion()) && getMapFrameHead().equals(msg.getMapFrameHead()) && getMapFrameNext().equals(msg.getMapFrameNext()) && getIsFrameEnd() == msg.getIsFrameEnd() && getHasUnallocatedTask() == msg.getHasUnallocatedTask() && getResp() == msg.getResp() && this.unknownFields.equals(msg.unknownFields);
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMsgId().hashCode()) * 37) + 2) * 53) + getMsgType()) * 37) + 3) * 53) + getSender().hashCode()) * 37) + 4) * 53) + getReceiver().hashCode()) * 37) + 5) * 53) + Internal.hashLong(getTimestamp());
            if (hasTask()) {
                hashCode = (((hashCode * 37) + 6) * 53) + getTask().hashCode();
            }
            if (hasTaskList()) {
                hashCode = (((hashCode * 37) + 7) * 53) + getTaskList().hashCode();
            }
            if (hasCallPoint()) {
                hashCode = (((hashCode * 37) + 8) * 53) + getCallPoint().hashCode();
            }
            if (hasCallPointList()) {
                hashCode = (((hashCode * 37) + 9) * 53) + getCallPointList().hashCode();
            }
            if (hasChannel()) {
                hashCode = (((hashCode * 37) + 10) * 53) + getChannel().hashCode();
            }
            if (hasChannelList()) {
                hashCode = (((hashCode * 37) + 11) * 53) + getChannelList().hashCode();
            }
            if (hasBroadcastControl()) {
                hashCode = (((hashCode * 37) + 12) * 53) + getBroadcastControl().hashCode();
            }
            if (hasRobotStatusReport()) {
                hashCode = (((hashCode * 37) + 13) * 53) + getRobotStatusReport().hashCode();
            }
            int hashCode2 = (((((((((((((((((((((((((hashCode * 37) + 14) * 53) + getMapVersion().hashCode()) * 37) + 15) * 53) + getMapFrameHead().hashCode()) * 37) + 16) * 53) + getMapFrameNext().hashCode()) * 37) + 17) * 53) + Internal.hashBoolean(getIsFrameEnd())) * 37) + 18) * 53) + Internal.hashBoolean(getHasUnallocatedTask())) * 37) + 19) * 53) + Internal.hashBoolean(getResp())) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static Msg parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Msg parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Msg parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Msg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Msg parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Msg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Msg parseFrom(InputStream inputStream) throws IOException {
            return (Msg) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Msg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Msg) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Msg parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Msg) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Msg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Msg) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Msg parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Msg) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Msg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Msg) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Msg msg) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(msg);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MsgOrBuilder {
            private SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> broadcastControlBuilder_;
            private BroadcastControl broadcastControl_;
            private SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> callPointBuilder_;
            private SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> callPointListBuilder_;
            private CallPointList callPointList_;
            private CallPoint callPoint_;
            private SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> channelBuilder_;
            private SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> channelListBuilder_;
            private ChannelList channelList_;
            private Channel channel_;
            private boolean hasUnallocatedTask_;
            private boolean isFrameEnd_;
            private Object mapFrameHead_;
            private Object mapFrameNext_;
            private Object mapVersion_;
            private Object msgId_;
            private int msgType_;
            private Object receiver_;
            private boolean resp_;
            private SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> robotStatusReportBuilder_;
            private RobotStatusReport robotStatusReport_;
            private Object sender_;
            private SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> taskBuilder_;
            private SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> taskListBuilder_;
            private TaskList taskList_;
            private Task task_;
            private long timestamp_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_Msg_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_Msg_fieldAccessorTable.ensureFieldAccessorsInitialized(Msg.class, Builder.class);
            }

            private Builder() {
                this.msgId_ = "";
                this.sender_ = "";
                this.receiver_ = "";
                this.mapVersion_ = "";
                this.mapFrameHead_ = "";
                this.mapFrameNext_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.msgId_ = "";
                this.sender_ = "";
                this.receiver_ = "";
                this.mapVersion_ = "";
                this.mapFrameHead_ = "";
                this.mapFrameNext_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Msg.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.msgId_ = "";
                this.msgType_ = 0;
                this.sender_ = "";
                this.receiver_ = "";
                this.timestamp_ = 0L;
                if (this.taskBuilder_ == null) {
                    this.task_ = null;
                } else {
                    this.task_ = null;
                    this.taskBuilder_ = null;
                }
                if (this.taskListBuilder_ == null) {
                    this.taskList_ = null;
                } else {
                    this.taskList_ = null;
                    this.taskListBuilder_ = null;
                }
                if (this.callPointBuilder_ == null) {
                    this.callPoint_ = null;
                } else {
                    this.callPoint_ = null;
                    this.callPointBuilder_ = null;
                }
                if (this.callPointListBuilder_ == null) {
                    this.callPointList_ = null;
                } else {
                    this.callPointList_ = null;
                    this.callPointListBuilder_ = null;
                }
                if (this.channelBuilder_ == null) {
                    this.channel_ = null;
                } else {
                    this.channel_ = null;
                    this.channelBuilder_ = null;
                }
                if (this.channelListBuilder_ == null) {
                    this.channelList_ = null;
                } else {
                    this.channelList_ = null;
                    this.channelListBuilder_ = null;
                }
                if (this.broadcastControlBuilder_ == null) {
                    this.broadcastControl_ = null;
                } else {
                    this.broadcastControl_ = null;
                    this.broadcastControlBuilder_ = null;
                }
                if (this.robotStatusReportBuilder_ == null) {
                    this.robotStatusReport_ = null;
                } else {
                    this.robotStatusReport_ = null;
                    this.robotStatusReportBuilder_ = null;
                }
                this.mapVersion_ = "";
                this.mapFrameHead_ = "";
                this.mapFrameNext_ = "";
                this.isFrameEnd_ = false;
                this.hasUnallocatedTask_ = false;
                this.resp_ = false;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_Msg_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Msg getDefaultInstanceForType() {
                return Msg.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Msg build() {
                Msg buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Msg buildPartial() {
                Msg msg = new Msg(this);
                msg.msgId_ = this.msgId_;
                msg.msgType_ = this.msgType_;
                msg.sender_ = this.sender_;
                msg.receiver_ = this.receiver_;
                msg.timestamp_ = this.timestamp_;
                SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> singleFieldBuilderV3 = this.taskBuilder_;
                if (singleFieldBuilderV3 == null) {
                    msg.task_ = this.task_;
                } else {
                    msg.task_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> singleFieldBuilderV32 = this.taskListBuilder_;
                if (singleFieldBuilderV32 == null) {
                    msg.taskList_ = this.taskList_;
                } else {
                    msg.taskList_ = singleFieldBuilderV32.build();
                }
                SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> singleFieldBuilderV33 = this.callPointBuilder_;
                if (singleFieldBuilderV33 == null) {
                    msg.callPoint_ = this.callPoint_;
                } else {
                    msg.callPoint_ = singleFieldBuilderV33.build();
                }
                SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> singleFieldBuilderV34 = this.callPointListBuilder_;
                if (singleFieldBuilderV34 == null) {
                    msg.callPointList_ = this.callPointList_;
                } else {
                    msg.callPointList_ = singleFieldBuilderV34.build();
                }
                SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> singleFieldBuilderV35 = this.channelBuilder_;
                if (singleFieldBuilderV35 == null) {
                    msg.channel_ = this.channel_;
                } else {
                    msg.channel_ = singleFieldBuilderV35.build();
                }
                SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> singleFieldBuilderV36 = this.channelListBuilder_;
                if (singleFieldBuilderV36 == null) {
                    msg.channelList_ = this.channelList_;
                } else {
                    msg.channelList_ = singleFieldBuilderV36.build();
                }
                SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> singleFieldBuilderV37 = this.broadcastControlBuilder_;
                if (singleFieldBuilderV37 == null) {
                    msg.broadcastControl_ = this.broadcastControl_;
                } else {
                    msg.broadcastControl_ = singleFieldBuilderV37.build();
                }
                SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> singleFieldBuilderV38 = this.robotStatusReportBuilder_;
                if (singleFieldBuilderV38 == null) {
                    msg.robotStatusReport_ = this.robotStatusReport_;
                } else {
                    msg.robotStatusReport_ = singleFieldBuilderV38.build();
                }
                msg.mapVersion_ = this.mapVersion_;
                msg.mapFrameHead_ = this.mapFrameHead_;
                msg.mapFrameNext_ = this.mapFrameNext_;
                msg.isFrameEnd_ = this.isFrameEnd_;
                msg.hasUnallocatedTask_ = this.hasUnallocatedTask_;
                msg.resp_ = this.resp_;
                onBuilt();
                return msg;
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
                if (message instanceof Msg) {
                    return mergeFrom((Msg) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Msg msg) {
                if (msg == Msg.getDefaultInstance()) {
                    return this;
                }
                if (!msg.getMsgId().isEmpty()) {
                    this.msgId_ = msg.msgId_;
                    onChanged();
                }
                if (msg.getMsgType() != 0) {
                    setMsgType(msg.getMsgType());
                }
                if (!msg.getSender().isEmpty()) {
                    this.sender_ = msg.sender_;
                    onChanged();
                }
                if (!msg.getReceiver().isEmpty()) {
                    this.receiver_ = msg.receiver_;
                    onChanged();
                }
                if (msg.getTimestamp() != 0) {
                    setTimestamp(msg.getTimestamp());
                }
                if (msg.hasTask()) {
                    mergeTask(msg.getTask());
                }
                if (msg.hasTaskList()) {
                    mergeTaskList(msg.getTaskList());
                }
                if (msg.hasCallPoint()) {
                    mergeCallPoint(msg.getCallPoint());
                }
                if (msg.hasCallPointList()) {
                    mergeCallPointList(msg.getCallPointList());
                }
                if (msg.hasChannel()) {
                    mergeChannel(msg.getChannel());
                }
                if (msg.hasChannelList()) {
                    mergeChannelList(msg.getChannelList());
                }
                if (msg.hasBroadcastControl()) {
                    mergeBroadcastControl(msg.getBroadcastControl());
                }
                if (msg.hasRobotStatusReport()) {
                    mergeRobotStatusReport(msg.getRobotStatusReport());
                }
                if (!msg.getMapVersion().isEmpty()) {
                    this.mapVersion_ = msg.mapVersion_;
                    onChanged();
                }
                if (!msg.getMapFrameHead().isEmpty()) {
                    this.mapFrameHead_ = msg.mapFrameHead_;
                    onChanged();
                }
                if (!msg.getMapFrameNext().isEmpty()) {
                    this.mapFrameNext_ = msg.mapFrameNext_;
                    onChanged();
                }
                if (msg.getIsFrameEnd()) {
                    setIsFrameEnd(msg.getIsFrameEnd());
                }
                if (msg.getHasUnallocatedTask()) {
                    setHasUnallocatedTask(msg.getHasUnallocatedTask());
                }
                if (msg.getResp()) {
                    setResp(msg.getResp());
                }
                mergeUnknownFields(msg.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Msg msg = null;
                try {
                    try {
                        Msg msg2 = (Msg) Msg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (msg2 != null) {
                            mergeFrom(msg2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Msg msg3 = (Msg) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            msg = msg3;
                            if (msg != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (msg != null) {
                        mergeFrom(msg);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public String getMsgId() {
                Object obj = this.msgId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.msgId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ByteString getMsgIdBytes() {
                Object obj = this.msgId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.msgId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMsgId(String str) {
                if (str == null) {
                    throw null;
                }
                this.msgId_ = str;
                onChanged();
                return this;
            }

            public Builder clearMsgId() {
                this.msgId_ = Msg.getDefaultInstance().getMsgId();
                onChanged();
                return this;
            }

            public Builder setMsgIdBytes(ByteString byteString) {
                if (byteString != null) {
                    Msg.checkByteStringIsUtf8(byteString);
                    this.msgId_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public int getMsgType() {
                return this.msgType_;
            }

            public Builder setMsgType(int i) {
                this.msgType_ = i;
                onChanged();
                return this;
            }

            public Builder clearMsgType() {
                this.msgType_ = 0;
                onChanged();
                return this;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public String getSender() {
                Object obj = this.sender_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.sender_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ByteString getSenderBytes() {
                Object obj = this.sender_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.sender_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setSender(String str) {
                if (str == null) {
                    throw null;
                }
                this.sender_ = str;
                onChanged();
                return this;
            }

            public Builder clearSender() {
                this.sender_ = Msg.getDefaultInstance().getSender();
                onChanged();
                return this;
            }

            public Builder setSenderBytes(ByteString byteString) {
                if (byteString != null) {
                    Msg.checkByteStringIsUtf8(byteString);
                    this.sender_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public String getReceiver() {
                Object obj = this.receiver_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.receiver_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ByteString getReceiverBytes() {
                Object obj = this.receiver_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.receiver_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setReceiver(String str) {
                if (str == null) {
                    throw null;
                }
                this.receiver_ = str;
                onChanged();
                return this;
            }

            public Builder clearReceiver() {
                this.receiver_ = Msg.getDefaultInstance().getReceiver();
                onChanged();
                return this;
            }

            public Builder setReceiverBytes(ByteString byteString) {
                if (byteString != null) {
                    Msg.checkByteStringIsUtf8(byteString);
                    this.receiver_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public long getTimestamp() {
                return this.timestamp_;
            }

            public Builder setTimestamp(long j) {
                this.timestamp_ = j;
                onChanged();
                return this;
            }

            public Builder clearTimestamp() {
                this.timestamp_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasTask() {
                return (this.taskBuilder_ == null && this.task_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public Task getTask() {
                SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> singleFieldBuilderV3 = this.taskBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Task task = this.task_;
                    return task == null ? Task.getDefaultInstance() : task;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTask(Task task) {
                SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> singleFieldBuilderV3 = this.taskBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(task);
                } else {
                    if (task == null) {
                        throw null;
                    }
                    this.task_ = task;
                    onChanged();
                }
                return this;
            }

            public Builder setTask(Task.Builder builder) {
                SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> singleFieldBuilderV3 = this.taskBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.task_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTask(Task task) {
                SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> singleFieldBuilderV3 = this.taskBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Task task2 = this.task_;
                    if (task2 != null) {
                        this.task_ = Task.newBuilder(task2).mergeFrom(task).buildPartial();
                    } else {
                        this.task_ = task;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(task);
                }
                return this;
            }

            public Builder clearTask() {
                if (this.taskBuilder_ == null) {
                    this.task_ = null;
                    onChanged();
                } else {
                    this.task_ = null;
                    this.taskBuilder_ = null;
                }
                return this;
            }

            public Task.Builder getTaskBuilder() {
                onChanged();
                return getTaskFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public TaskOrBuilder getTaskOrBuilder() {
                SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> singleFieldBuilderV3 = this.taskBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Task task = this.task_;
                return task == null ? Task.getDefaultInstance() : task;
            }

            private SingleFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> getTaskFieldBuilder() {
                if (this.taskBuilder_ == null) {
                    this.taskBuilder_ = new SingleFieldBuilderV3<>(getTask(), getParentForChildren(), isClean());
                    this.task_ = null;
                }
                return this.taskBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasTaskList() {
                return (this.taskListBuilder_ == null && this.taskList_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public TaskList getTaskList() {
                SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> singleFieldBuilderV3 = this.taskListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    TaskList taskList = this.taskList_;
                    return taskList == null ? TaskList.getDefaultInstance() : taskList;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setTaskList(TaskList taskList) {
                SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> singleFieldBuilderV3 = this.taskListBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(taskList);
                } else {
                    if (taskList == null) {
                        throw null;
                    }
                    this.taskList_ = taskList;
                    onChanged();
                }
                return this;
            }

            public Builder setTaskList(TaskList.Builder builder) {
                SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> singleFieldBuilderV3 = this.taskListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.taskList_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeTaskList(TaskList taskList) {
                SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> singleFieldBuilderV3 = this.taskListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    TaskList taskList2 = this.taskList_;
                    if (taskList2 != null) {
                        this.taskList_ = TaskList.newBuilder(taskList2).mergeFrom(taskList).buildPartial();
                    } else {
                        this.taskList_ = taskList;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(taskList);
                }
                return this;
            }

            public Builder clearTaskList() {
                if (this.taskListBuilder_ == null) {
                    this.taskList_ = null;
                    onChanged();
                } else {
                    this.taskList_ = null;
                    this.taskListBuilder_ = null;
                }
                return this;
            }

            public TaskList.Builder getTaskListBuilder() {
                onChanged();
                return getTaskListFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public TaskListOrBuilder getTaskListOrBuilder() {
                SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> singleFieldBuilderV3 = this.taskListBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                TaskList taskList = this.taskList_;
                return taskList == null ? TaskList.getDefaultInstance() : taskList;
            }

            private SingleFieldBuilderV3<TaskList, TaskList.Builder, TaskListOrBuilder> getTaskListFieldBuilder() {
                if (this.taskListBuilder_ == null) {
                    this.taskListBuilder_ = new SingleFieldBuilderV3<>(getTaskList(), getParentForChildren(), isClean());
                    this.taskList_ = null;
                }
                return this.taskListBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasCallPoint() {
                return (this.callPointBuilder_ == null && this.callPoint_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public CallPoint getCallPoint() {
                SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> singleFieldBuilderV3 = this.callPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallPoint callPoint = this.callPoint_;
                    return callPoint == null ? CallPoint.getDefaultInstance() : callPoint;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCallPoint(CallPoint callPoint) {
                SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> singleFieldBuilderV3 = this.callPointBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(callPoint);
                } else {
                    if (callPoint == null) {
                        throw null;
                    }
                    this.callPoint_ = callPoint;
                    onChanged();
                }
                return this;
            }

            public Builder setCallPoint(CallPoint.Builder builder) {
                SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> singleFieldBuilderV3 = this.callPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.callPoint_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCallPoint(CallPoint callPoint) {
                SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> singleFieldBuilderV3 = this.callPointBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallPoint callPoint2 = this.callPoint_;
                    if (callPoint2 != null) {
                        this.callPoint_ = CallPoint.newBuilder(callPoint2).mergeFrom(callPoint).buildPartial();
                    } else {
                        this.callPoint_ = callPoint;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(callPoint);
                }
                return this;
            }

            public Builder clearCallPoint() {
                if (this.callPointBuilder_ == null) {
                    this.callPoint_ = null;
                    onChanged();
                } else {
                    this.callPoint_ = null;
                    this.callPointBuilder_ = null;
                }
                return this;
            }

            public CallPoint.Builder getCallPointBuilder() {
                onChanged();
                return getCallPointFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public CallPointOrBuilder getCallPointOrBuilder() {
                SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> singleFieldBuilderV3 = this.callPointBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CallPoint callPoint = this.callPoint_;
                return callPoint == null ? CallPoint.getDefaultInstance() : callPoint;
            }

            private SingleFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> getCallPointFieldBuilder() {
                if (this.callPointBuilder_ == null) {
                    this.callPointBuilder_ = new SingleFieldBuilderV3<>(getCallPoint(), getParentForChildren(), isClean());
                    this.callPoint_ = null;
                }
                return this.callPointBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasCallPointList() {
                return (this.callPointListBuilder_ == null && this.callPointList_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public CallPointList getCallPointList() {
                SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> singleFieldBuilderV3 = this.callPointListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallPointList callPointList = this.callPointList_;
                    return callPointList == null ? CallPointList.getDefaultInstance() : callPointList;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setCallPointList(CallPointList callPointList) {
                SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> singleFieldBuilderV3 = this.callPointListBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(callPointList);
                } else {
                    if (callPointList == null) {
                        throw null;
                    }
                    this.callPointList_ = callPointList;
                    onChanged();
                }
                return this;
            }

            public Builder setCallPointList(CallPointList.Builder builder) {
                SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> singleFieldBuilderV3 = this.callPointListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.callPointList_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeCallPointList(CallPointList callPointList) {
                SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> singleFieldBuilderV3 = this.callPointListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    CallPointList callPointList2 = this.callPointList_;
                    if (callPointList2 != null) {
                        this.callPointList_ = CallPointList.newBuilder(callPointList2).mergeFrom(callPointList).buildPartial();
                    } else {
                        this.callPointList_ = callPointList;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(callPointList);
                }
                return this;
            }

            public Builder clearCallPointList() {
                if (this.callPointListBuilder_ == null) {
                    this.callPointList_ = null;
                    onChanged();
                } else {
                    this.callPointList_ = null;
                    this.callPointListBuilder_ = null;
                }
                return this;
            }

            public CallPointList.Builder getCallPointListBuilder() {
                onChanged();
                return getCallPointListFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public CallPointListOrBuilder getCallPointListOrBuilder() {
                SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> singleFieldBuilderV3 = this.callPointListBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                CallPointList callPointList = this.callPointList_;
                return callPointList == null ? CallPointList.getDefaultInstance() : callPointList;
            }

            private SingleFieldBuilderV3<CallPointList, CallPointList.Builder, CallPointListOrBuilder> getCallPointListFieldBuilder() {
                if (this.callPointListBuilder_ == null) {
                    this.callPointListBuilder_ = new SingleFieldBuilderV3<>(getCallPointList(), getParentForChildren(), isClean());
                    this.callPointList_ = null;
                }
                return this.callPointListBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasChannel() {
                return (this.channelBuilder_ == null && this.channel_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public Channel getChannel() {
                SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> singleFieldBuilderV3 = this.channelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Channel channel = this.channel_;
                    return channel == null ? Channel.getDefaultInstance() : channel;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setChannel(Channel channel) {
                SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> singleFieldBuilderV3 = this.channelBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(channel);
                } else {
                    if (channel == null) {
                        throw null;
                    }
                    this.channel_ = channel;
                    onChanged();
                }
                return this;
            }

            public Builder setChannel(Channel.Builder builder) {
                SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> singleFieldBuilderV3 = this.channelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.channel_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeChannel(Channel channel) {
                SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> singleFieldBuilderV3 = this.channelBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Channel channel2 = this.channel_;
                    if (channel2 != null) {
                        this.channel_ = Channel.newBuilder(channel2).mergeFrom(channel).buildPartial();
                    } else {
                        this.channel_ = channel;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(channel);
                }
                return this;
            }

            public Builder clearChannel() {
                if (this.channelBuilder_ == null) {
                    this.channel_ = null;
                    onChanged();
                } else {
                    this.channel_ = null;
                    this.channelBuilder_ = null;
                }
                return this;
            }

            public Channel.Builder getChannelBuilder() {
                onChanged();
                return getChannelFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ChannelOrBuilder getChannelOrBuilder() {
                SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> singleFieldBuilderV3 = this.channelBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Channel channel = this.channel_;
                return channel == null ? Channel.getDefaultInstance() : channel;
            }

            private SingleFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> getChannelFieldBuilder() {
                if (this.channelBuilder_ == null) {
                    this.channelBuilder_ = new SingleFieldBuilderV3<>(getChannel(), getParentForChildren(), isClean());
                    this.channel_ = null;
                }
                return this.channelBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasChannelList() {
                return (this.channelListBuilder_ == null && this.channelList_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ChannelList getChannelList() {
                SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> singleFieldBuilderV3 = this.channelListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ChannelList channelList = this.channelList_;
                    return channelList == null ? ChannelList.getDefaultInstance() : channelList;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setChannelList(ChannelList channelList) {
                SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> singleFieldBuilderV3 = this.channelListBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(channelList);
                } else {
                    if (channelList == null) {
                        throw null;
                    }
                    this.channelList_ = channelList;
                    onChanged();
                }
                return this;
            }

            public Builder setChannelList(ChannelList.Builder builder) {
                SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> singleFieldBuilderV3 = this.channelListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.channelList_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeChannelList(ChannelList channelList) {
                SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> singleFieldBuilderV3 = this.channelListBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ChannelList channelList2 = this.channelList_;
                    if (channelList2 != null) {
                        this.channelList_ = ChannelList.newBuilder(channelList2).mergeFrom(channelList).buildPartial();
                    } else {
                        this.channelList_ = channelList;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(channelList);
                }
                return this;
            }

            public Builder clearChannelList() {
                if (this.channelListBuilder_ == null) {
                    this.channelList_ = null;
                    onChanged();
                } else {
                    this.channelList_ = null;
                    this.channelListBuilder_ = null;
                }
                return this;
            }

            public ChannelList.Builder getChannelListBuilder() {
                onChanged();
                return getChannelListFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ChannelListOrBuilder getChannelListOrBuilder() {
                SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> singleFieldBuilderV3 = this.channelListBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ChannelList channelList = this.channelList_;
                return channelList == null ? ChannelList.getDefaultInstance() : channelList;
            }

            private SingleFieldBuilderV3<ChannelList, ChannelList.Builder, ChannelListOrBuilder> getChannelListFieldBuilder() {
                if (this.channelListBuilder_ == null) {
                    this.channelListBuilder_ = new SingleFieldBuilderV3<>(getChannelList(), getParentForChildren(), isClean());
                    this.channelList_ = null;
                }
                return this.channelListBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasBroadcastControl() {
                return (this.broadcastControlBuilder_ == null && this.broadcastControl_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public BroadcastControl getBroadcastControl() {
                SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> singleFieldBuilderV3 = this.broadcastControlBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BroadcastControl broadcastControl = this.broadcastControl_;
                    return broadcastControl == null ? BroadcastControl.getDefaultInstance() : broadcastControl;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setBroadcastControl(BroadcastControl broadcastControl) {
                SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> singleFieldBuilderV3 = this.broadcastControlBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(broadcastControl);
                } else {
                    if (broadcastControl == null) {
                        throw null;
                    }
                    this.broadcastControl_ = broadcastControl;
                    onChanged();
                }
                return this;
            }

            public Builder setBroadcastControl(BroadcastControl.Builder builder) {
                SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> singleFieldBuilderV3 = this.broadcastControlBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.broadcastControl_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeBroadcastControl(BroadcastControl broadcastControl) {
                SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> singleFieldBuilderV3 = this.broadcastControlBuilder_;
                if (singleFieldBuilderV3 == null) {
                    BroadcastControl broadcastControl2 = this.broadcastControl_;
                    if (broadcastControl2 != null) {
                        this.broadcastControl_ = BroadcastControl.newBuilder(broadcastControl2).mergeFrom(broadcastControl).buildPartial();
                    } else {
                        this.broadcastControl_ = broadcastControl;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(broadcastControl);
                }
                return this;
            }

            public Builder clearBroadcastControl() {
                if (this.broadcastControlBuilder_ == null) {
                    this.broadcastControl_ = null;
                    onChanged();
                } else {
                    this.broadcastControl_ = null;
                    this.broadcastControlBuilder_ = null;
                }
                return this;
            }

            public BroadcastControl.Builder getBroadcastControlBuilder() {
                onChanged();
                return getBroadcastControlFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public BroadcastControlOrBuilder getBroadcastControlOrBuilder() {
                SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> singleFieldBuilderV3 = this.broadcastControlBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                BroadcastControl broadcastControl = this.broadcastControl_;
                return broadcastControl == null ? BroadcastControl.getDefaultInstance() : broadcastControl;
            }

            private SingleFieldBuilderV3<BroadcastControl, BroadcastControl.Builder, BroadcastControlOrBuilder> getBroadcastControlFieldBuilder() {
                if (this.broadcastControlBuilder_ == null) {
                    this.broadcastControlBuilder_ = new SingleFieldBuilderV3<>(getBroadcastControl(), getParentForChildren(), isClean());
                    this.broadcastControl_ = null;
                }
                return this.broadcastControlBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean hasRobotStatusReport() {
                return (this.robotStatusReportBuilder_ == null && this.robotStatusReport_ == null) ? false : true;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public RobotStatusReport getRobotStatusReport() {
                SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> singleFieldBuilderV3 = this.robotStatusReportBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RobotStatusReport robotStatusReport = this.robotStatusReport_;
                    return robotStatusReport == null ? RobotStatusReport.getDefaultInstance() : robotStatusReport;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public Builder setRobotStatusReport(RobotStatusReport robotStatusReport) {
                SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> singleFieldBuilderV3 = this.robotStatusReportBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(robotStatusReport);
                } else {
                    if (robotStatusReport == null) {
                        throw null;
                    }
                    this.robotStatusReport_ = robotStatusReport;
                    onChanged();
                }
                return this;
            }

            public Builder setRobotStatusReport(RobotStatusReport.Builder builder) {
                SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> singleFieldBuilderV3 = this.robotStatusReportBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.robotStatusReport_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeRobotStatusReport(RobotStatusReport robotStatusReport) {
                SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> singleFieldBuilderV3 = this.robotStatusReportBuilder_;
                if (singleFieldBuilderV3 == null) {
                    RobotStatusReport robotStatusReport2 = this.robotStatusReport_;
                    if (robotStatusReport2 != null) {
                        this.robotStatusReport_ = RobotStatusReport.newBuilder(robotStatusReport2).mergeFrom(robotStatusReport).buildPartial();
                    } else {
                        this.robotStatusReport_ = robotStatusReport;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(robotStatusReport);
                }
                return this;
            }

            public Builder clearRobotStatusReport() {
                if (this.robotStatusReportBuilder_ == null) {
                    this.robotStatusReport_ = null;
                    onChanged();
                } else {
                    this.robotStatusReport_ = null;
                    this.robotStatusReportBuilder_ = null;
                }
                return this;
            }

            public RobotStatusReport.Builder getRobotStatusReportBuilder() {
                onChanged();
                return getRobotStatusReportFieldBuilder().getBuilder();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public RobotStatusReportOrBuilder getRobotStatusReportOrBuilder() {
                SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> singleFieldBuilderV3 = this.robotStatusReportBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                RobotStatusReport robotStatusReport = this.robotStatusReport_;
                return robotStatusReport == null ? RobotStatusReport.getDefaultInstance() : robotStatusReport;
            }

            private SingleFieldBuilderV3<RobotStatusReport, RobotStatusReport.Builder, RobotStatusReportOrBuilder> getRobotStatusReportFieldBuilder() {
                if (this.robotStatusReportBuilder_ == null) {
                    this.robotStatusReportBuilder_ = new SingleFieldBuilderV3<>(getRobotStatusReport(), getParentForChildren(), isClean());
                    this.robotStatusReport_ = null;
                }
                return this.robotStatusReportBuilder_;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public String getMapVersion() {
                Object obj = this.mapVersion_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mapVersion_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ByteString getMapVersionBytes() {
                Object obj = this.mapVersion_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mapVersion_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMapVersion(String str) {
                if (str == null) {
                    throw null;
                }
                this.mapVersion_ = str;
                onChanged();
                return this;
            }

            public Builder clearMapVersion() {
                this.mapVersion_ = Msg.getDefaultInstance().getMapVersion();
                onChanged();
                return this;
            }

            public Builder setMapVersionBytes(ByteString byteString) {
                if (byteString != null) {
                    Msg.checkByteStringIsUtf8(byteString);
                    this.mapVersion_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public String getMapFrameHead() {
                Object obj = this.mapFrameHead_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mapFrameHead_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ByteString getMapFrameHeadBytes() {
                Object obj = this.mapFrameHead_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mapFrameHead_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMapFrameHead(String str) {
                if (str == null) {
                    throw null;
                }
                this.mapFrameHead_ = str;
                onChanged();
                return this;
            }

            public Builder clearMapFrameHead() {
                this.mapFrameHead_ = Msg.getDefaultInstance().getMapFrameHead();
                onChanged();
                return this;
            }

            public Builder setMapFrameHeadBytes(ByteString byteString) {
                if (byteString != null) {
                    Msg.checkByteStringIsUtf8(byteString);
                    this.mapFrameHead_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public String getMapFrameNext() {
                Object obj = this.mapFrameNext_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mapFrameNext_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public ByteString getMapFrameNextBytes() {
                Object obj = this.mapFrameNext_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mapFrameNext_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMapFrameNext(String str) {
                if (str == null) {
                    throw null;
                }
                this.mapFrameNext_ = str;
                onChanged();
                return this;
            }

            public Builder clearMapFrameNext() {
                this.mapFrameNext_ = Msg.getDefaultInstance().getMapFrameNext();
                onChanged();
                return this;
            }

            public Builder setMapFrameNextBytes(ByteString byteString) {
                if (byteString != null) {
                    Msg.checkByteStringIsUtf8(byteString);
                    this.mapFrameNext_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean getIsFrameEnd() {
                return this.isFrameEnd_;
            }

            public Builder setIsFrameEnd(boolean z) {
                this.isFrameEnd_ = z;
                onChanged();
                return this;
            }

            public Builder clearIsFrameEnd() {
                this.isFrameEnd_ = false;
                onChanged();
                return this;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean getHasUnallocatedTask() {
                return this.hasUnallocatedTask_;
            }

            public Builder setHasUnallocatedTask(boolean z) {
                this.hasUnallocatedTask_ = z;
                onChanged();
                return this;
            }

            public Builder clearHasUnallocatedTask() {
                this.hasUnallocatedTask_ = false;
                onChanged();
                return this;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.MsgOrBuilder
            public boolean getResp() {
                return this.resp_;
            }

            public Builder setResp(boolean z) {
                this.resp_ = z;
                onChanged();
                return this;
            }

            public Builder clearResp() {
                this.resp_ = false;
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

        public static Msg getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Msg> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Msg> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Msg getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class Task extends GeneratedMessageV3 implements TaskOrBuilder {
        private static final Task DEFAULT_INSTANCE = new Task();
        private static final Parser<Task> PARSER = new AbstractParser<Task>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.Task.1
            @Override // com.google.protobuf.Parser
            public Task parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Task(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int STATE_FIELD_NUMBER = 2;
        public static final int TASKID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int state_;
        private volatile Object taskId_;

        private Task(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Task() {
            this.memoizedIsInitialized = (byte) -1;
            this.taskId_ = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Task();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Task(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.taskId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.state_ = codedInputStream.readInt32();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MessageProtobuf.internal_static_Task_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_Task_fieldAccessorTable.ensureFieldAccessorsInitialized(Task.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskOrBuilder
        public String getTaskId() {
            Object obj = this.taskId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.taskId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskOrBuilder
        public ByteString getTaskIdBytes() {
            Object obj = this.taskId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.taskId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskOrBuilder
        public int getState() {
            return this.state_;
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
            if (!getTaskIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.taskId_);
            }
            int i = this.state_;
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
            int computeStringSize = getTaskIdBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.taskId_);
            int i2 = this.state_;
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
            if (!(obj instanceof Task)) {
                return super.equals(obj);
            }
            Task task = (Task) obj;
            return getTaskId().equals(task.getTaskId()) && getState() == task.getState() && this.unknownFields.equals(task.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTaskId().hashCode()) * 37) + 2) * 53) + getState()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Task parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Task parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Task parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Task parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Task parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Task parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Task parseFrom(InputStream inputStream) throws IOException {
            return (Task) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Task parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Task) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Task parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Task) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Task parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Task) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Task parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Task) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Task parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Task) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Task task) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(task);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TaskOrBuilder {
            private int state_;
            private Object taskId_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_Task_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_Task_fieldAccessorTable.ensureFieldAccessorsInitialized(Task.class, Builder.class);
            }

            private Builder() {
                this.taskId_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.taskId_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Task.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.taskId_ = "";
                this.state_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_Task_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Task getDefaultInstanceForType() {
                return Task.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Task build() {
                Task buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Task buildPartial() {
                Task task = new Task(this);
                task.taskId_ = this.taskId_;
                task.state_ = this.state_;
                onBuilt();
                return task;
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
                if (message instanceof Task) {
                    return mergeFrom((Task) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Task task) {
                if (task == Task.getDefaultInstance()) {
                    return this;
                }
                if (!task.getTaskId().isEmpty()) {
                    this.taskId_ = task.taskId_;
                    onChanged();
                }
                if (task.getState() != 0) {
                    setState(task.getState());
                }
                mergeUnknownFields(task.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Task task = null;
                try {
                    try {
                        Task task2 = (Task) Task.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (task2 != null) {
                            mergeFrom(task2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Task task3 = (Task) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            task = task3;
                            if (task != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (task != null) {
                        mergeFrom(task);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskOrBuilder
            public String getTaskId() {
                Object obj = this.taskId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.taskId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskOrBuilder
            public ByteString getTaskIdBytes() {
                Object obj = this.taskId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.taskId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTaskId(String str) {
                if (str == null) {
                    throw null;
                }
                this.taskId_ = str;
                onChanged();
                return this;
            }

            public Builder clearTaskId() {
                this.taskId_ = Task.getDefaultInstance().getTaskId();
                onChanged();
                return this;
            }

            public Builder setTaskIdBytes(ByteString byteString) {
                if (byteString != null) {
                    Task.checkByteStringIsUtf8(byteString);
                    this.taskId_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskOrBuilder
            public int getState() {
                return this.state_;
            }

            public Builder setState(int i) {
                this.state_ = i;
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
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Task getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Task> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Task> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Task getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class TaskList extends GeneratedMessageV3 implements TaskListOrBuilder {
        private static final TaskList DEFAULT_INSTANCE = new TaskList();
        private static final Parser<TaskList> PARSER = new AbstractParser<TaskList>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.TaskList.1
            @Override // com.google.protobuf.Parser
            public TaskList parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TaskList(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TASK_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<Task> task_;

        private TaskList(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TaskList() {
            this.memoizedIsInitialized = (byte) -1;
            this.task_ = Collections.emptyList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new TaskList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private TaskList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
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
                                    if (!(z2 & true)) {
                                        this.task_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.task_.add(codedInputStream.readMessage(Task.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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
                        this.task_ = Collections.unmodifiableList(this.task_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MessageProtobuf.internal_static_TaskList_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_TaskList_fieldAccessorTable.ensureFieldAccessorsInitialized(TaskList.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
        public List<Task> getTaskList() {
            return this.task_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
        public List<? extends TaskOrBuilder> getTaskOrBuilderList() {
            return this.task_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
        public int getTaskCount() {
            return this.task_.size();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
        public Task getTask(int i) {
            return this.task_.get(i);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
        public TaskOrBuilder getTaskOrBuilder(int i) {
            return this.task_.get(i);
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
            for (int i = 0; i < this.task_.size(); i++) {
                codedOutputStream.writeMessage(1, this.task_.get(i));
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
            for (int i3 = 0; i3 < this.task_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.task_.get(i3));
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
            if (!(obj instanceof TaskList)) {
                return super.equals(obj);
            }
            TaskList taskList = (TaskList) obj;
            return getTaskList().equals(taskList.getTaskList()) && this.unknownFields.equals(taskList.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getTaskCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getTaskList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static TaskList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static TaskList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TaskList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static TaskList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TaskList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static TaskList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TaskList parseFrom(InputStream inputStream) throws IOException {
            return (TaskList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TaskList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TaskList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TaskList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TaskList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TaskList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TaskList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TaskList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TaskList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TaskList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TaskList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TaskList taskList) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(taskList);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TaskListOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> taskBuilder_;
            private List<Task> task_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_TaskList_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_TaskList_fieldAccessorTable.ensureFieldAccessorsInitialized(TaskList.class, Builder.class);
            }

            private Builder() {
                this.task_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.task_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (TaskList.alwaysUseFieldBuilders) {
                    getTaskFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.task_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_TaskList_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public TaskList getDefaultInstanceForType() {
                return TaskList.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public TaskList build() {
                TaskList buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public TaskList buildPartial() {
                TaskList taskList = new TaskList(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.task_ = Collections.unmodifiableList(this.task_);
                        this.bitField0_ &= -2;
                    }
                    taskList.task_ = this.task_;
                } else {
                    taskList.task_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return taskList;
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
                if (message instanceof TaskList) {
                    return mergeFrom((TaskList) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TaskList taskList) {
                if (taskList == TaskList.getDefaultInstance()) {
                    return this;
                }
                if (this.taskBuilder_ == null) {
                    if (!taskList.task_.isEmpty()) {
                        if (this.task_.isEmpty()) {
                            this.task_ = taskList.task_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureTaskIsMutable();
                            this.task_.addAll(taskList.task_);
                        }
                        onChanged();
                    }
                } else if (!taskList.task_.isEmpty()) {
                    if (!this.taskBuilder_.isEmpty()) {
                        this.taskBuilder_.addAllMessages(taskList.task_);
                    } else {
                        this.taskBuilder_.dispose();
                        this.taskBuilder_ = null;
                        this.task_ = taskList.task_;
                        this.bitField0_ &= -2;
                        this.taskBuilder_ = TaskList.alwaysUseFieldBuilders ? getTaskFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(taskList.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TaskList taskList = null;
                try {
                    try {
                        TaskList taskList2 = (TaskList) TaskList.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (taskList2 != null) {
                            mergeFrom(taskList2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        TaskList taskList3 = (TaskList) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            taskList = taskList3;
                            if (taskList != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (taskList != null) {
                        mergeFrom(taskList);
                    }
                    throw th;
                }
            }

            private void ensureTaskIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.task_ = new ArrayList(this.task_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
            public List<Task> getTaskList() {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.task_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
            public int getTaskCount() {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.task_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
            public Task getTask(int i) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.task_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setTask(int i, Task task) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, task);
                } else {
                    if (task == null) {
                        throw null;
                    }
                    ensureTaskIsMutable();
                    this.task_.set(i, task);
                    onChanged();
                }
                return this;
            }

            public Builder setTask(int i, Task.Builder builder) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTaskIsMutable();
                    this.task_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addTask(Task task) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(task);
                } else {
                    if (task == null) {
                        throw null;
                    }
                    ensureTaskIsMutable();
                    this.task_.add(task);
                    onChanged();
                }
                return this;
            }

            public Builder addTask(int i, Task task) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, task);
                } else {
                    if (task == null) {
                        throw null;
                    }
                    ensureTaskIsMutable();
                    this.task_.add(i, task);
                    onChanged();
                }
                return this;
            }

            public Builder addTask(Task.Builder builder) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTaskIsMutable();
                    this.task_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addTask(int i, Task.Builder builder) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTaskIsMutable();
                    this.task_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllTask(Iterable<? extends Task> iterable) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTaskIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.task_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearTask() {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.task_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeTask(int i) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureTaskIsMutable();
                    this.task_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Task.Builder getTaskBuilder(int i) {
                return getTaskFieldBuilder().getBuilder(i);
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
            public TaskOrBuilder getTaskOrBuilder(int i) {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.task_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.TaskListOrBuilder
            public List<? extends TaskOrBuilder> getTaskOrBuilderList() {
                RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> repeatedFieldBuilderV3 = this.taskBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.task_);
            }

            public Task.Builder addTaskBuilder() {
                return getTaskFieldBuilder().addBuilder(Task.getDefaultInstance());
            }

            public Task.Builder addTaskBuilder(int i) {
                return getTaskFieldBuilder().addBuilder(i, Task.getDefaultInstance());
            }

            public List<Task.Builder> getTaskBuilderList() {
                return getTaskFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Task, Task.Builder, TaskOrBuilder> getTaskFieldBuilder() {
                if (this.taskBuilder_ == null) {
                    this.taskBuilder_ = new RepeatedFieldBuilderV3<>(this.task_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.task_ = null;
                }
                return this.taskBuilder_;
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

        public static TaskList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TaskList> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<TaskList> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public TaskList getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class CallPoint extends GeneratedMessageV3 implements CallPointOrBuilder {
        public static final int CALLPOINTID_FIELD_NUMBER = 1;
        private static final CallPoint DEFAULT_INSTANCE = new CallPoint();
        private static final Parser<CallPoint> PARSER = new AbstractParser<CallPoint>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.CallPoint.1
            @Override // com.google.protobuf.Parser
            public CallPoint parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CallPoint(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TYPE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object callPointId_;
        private byte memoizedIsInitialized;
        private int type_;

        private CallPoint(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CallPoint() {
            this.memoizedIsInitialized = (byte) -1;
            this.callPointId_ = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new CallPoint();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CallPoint(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.callPointId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.type_ = codedInputStream.readInt32();
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
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MessageProtobuf.internal_static_CallPoint_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_CallPoint_fieldAccessorTable.ensureFieldAccessorsInitialized(CallPoint.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointOrBuilder
        public String getCallPointId() {
            Object obj = this.callPointId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.callPointId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointOrBuilder
        public ByteString getCallPointIdBytes() {
            Object obj = this.callPointId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.callPointId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointOrBuilder
        public int getType() {
            return this.type_;
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
            if (!getCallPointIdBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.callPointId_);
            }
            int i = this.type_;
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
            int computeStringSize = getCallPointIdBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.callPointId_);
            int i2 = this.type_;
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
            if (!(obj instanceof CallPoint)) {
                return super.equals(obj);
            }
            CallPoint callPoint = (CallPoint) obj;
            return getCallPointId().equals(callPoint.getCallPointId()) && getType() == callPoint.getType() && this.unknownFields.equals(callPoint.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getCallPointId().hashCode()) * 37) + 2) * 53) + getType()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static CallPoint parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CallPoint parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CallPoint parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CallPoint parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CallPoint parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CallPoint parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CallPoint parseFrom(InputStream inputStream) throws IOException {
            return (CallPoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CallPoint parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallPoint) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallPoint parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CallPoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CallPoint parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallPoint) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallPoint parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CallPoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CallPoint parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallPoint) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CallPoint callPoint) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(callPoint);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallPointOrBuilder {
            private Object callPointId_;
            private int type_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_CallPoint_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_CallPoint_fieldAccessorTable.ensureFieldAccessorsInitialized(CallPoint.class, Builder.class);
            }

            private Builder() {
                this.callPointId_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.callPointId_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CallPoint.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.callPointId_ = "";
                this.type_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_CallPoint_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CallPoint getDefaultInstanceForType() {
                return CallPoint.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallPoint build() {
                CallPoint buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallPoint buildPartial() {
                CallPoint callPoint = new CallPoint(this);
                callPoint.callPointId_ = this.callPointId_;
                callPoint.type_ = this.type_;
                onBuilt();
                return callPoint;
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
                if (message instanceof CallPoint) {
                    return mergeFrom((CallPoint) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CallPoint callPoint) {
                if (callPoint == CallPoint.getDefaultInstance()) {
                    return this;
                }
                if (!callPoint.getCallPointId().isEmpty()) {
                    this.callPointId_ = callPoint.callPointId_;
                    onChanged();
                }
                if (callPoint.getType() != 0) {
                    setType(callPoint.getType());
                }
                mergeUnknownFields(callPoint.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CallPoint callPoint = null;
                try {
                    try {
                        CallPoint callPoint2 = (CallPoint) CallPoint.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (callPoint2 != null) {
                            mergeFrom(callPoint2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CallPoint callPoint3 = (CallPoint) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            callPoint = callPoint3;
                            if (callPoint != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (callPoint != null) {
                        mergeFrom(callPoint);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointOrBuilder
            public String getCallPointId() {
                Object obj = this.callPointId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.callPointId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointOrBuilder
            public ByteString getCallPointIdBytes() {
                Object obj = this.callPointId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.callPointId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCallPointId(String str) {
                if (str == null) {
                    throw null;
                }
                this.callPointId_ = str;
                onChanged();
                return this;
            }

            public Builder clearCallPointId() {
                this.callPointId_ = CallPoint.getDefaultInstance().getCallPointId();
                onChanged();
                return this;
            }

            public Builder setCallPointIdBytes(ByteString byteString) {
                if (byteString != null) {
                    CallPoint.checkByteStringIsUtf8(byteString);
                    this.callPointId_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointOrBuilder
            public int getType() {
                return this.type_;
            }

            public Builder setType(int i) {
                this.type_ = i;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = 0;
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

        public static CallPoint getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CallPoint> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CallPoint> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CallPoint getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class CallPointList extends GeneratedMessageV3 implements CallPointListOrBuilder {
        public static final int CALLPOINT_FIELD_NUMBER = 1;
        private static final CallPointList DEFAULT_INSTANCE = new CallPointList();
        private static final Parser<CallPointList> PARSER = new AbstractParser<CallPointList>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.CallPointList.1
            @Override // com.google.protobuf.Parser
            public CallPointList parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CallPointList(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private List<CallPoint> callPoint_;
        private byte memoizedIsInitialized;

        private CallPointList(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CallPointList() {
            this.memoizedIsInitialized = (byte) -1;
            this.callPoint_ = Collections.emptyList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new CallPointList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private CallPointList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
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
                                    if (!(z2 & true)) {
                                        this.callPoint_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.callPoint_.add(codedInputStream.readMessage(CallPoint.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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
                        this.callPoint_ = Collections.unmodifiableList(this.callPoint_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MessageProtobuf.internal_static_CallPointList_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_CallPointList_fieldAccessorTable.ensureFieldAccessorsInitialized(CallPointList.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
        public List<CallPoint> getCallPointList() {
            return this.callPoint_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
        public List<? extends CallPointOrBuilder> getCallPointOrBuilderList() {
            return this.callPoint_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
        public int getCallPointCount() {
            return this.callPoint_.size();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
        public CallPoint getCallPoint(int i) {
            return this.callPoint_.get(i);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
        public CallPointOrBuilder getCallPointOrBuilder(int i) {
            return this.callPoint_.get(i);
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
            for (int i = 0; i < this.callPoint_.size(); i++) {
                codedOutputStream.writeMessage(1, this.callPoint_.get(i));
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
            for (int i3 = 0; i3 < this.callPoint_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.callPoint_.get(i3));
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
            if (!(obj instanceof CallPointList)) {
                return super.equals(obj);
            }
            CallPointList callPointList = (CallPointList) obj;
            return getCallPointList().equals(callPointList.getCallPointList()) && this.unknownFields.equals(callPointList.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getCallPointCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getCallPointList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static CallPointList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CallPointList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CallPointList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CallPointList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CallPointList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CallPointList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CallPointList parseFrom(InputStream inputStream) throws IOException {
            return (CallPointList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CallPointList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallPointList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallPointList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CallPointList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CallPointList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallPointList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CallPointList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CallPointList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CallPointList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallPointList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CallPointList callPointList) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(callPointList);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallPointListOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> callPointBuilder_;
            private List<CallPoint> callPoint_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_CallPointList_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_CallPointList_fieldAccessorTable.ensureFieldAccessorsInitialized(CallPointList.class, Builder.class);
            }

            private Builder() {
                this.callPoint_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.callPoint_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (CallPointList.alwaysUseFieldBuilders) {
                    getCallPointFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.callPoint_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_CallPointList_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CallPointList getDefaultInstanceForType() {
                return CallPointList.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallPointList build() {
                CallPointList buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CallPointList buildPartial() {
                CallPointList callPointList = new CallPointList(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.callPoint_ = Collections.unmodifiableList(this.callPoint_);
                        this.bitField0_ &= -2;
                    }
                    callPointList.callPoint_ = this.callPoint_;
                } else {
                    callPointList.callPoint_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return callPointList;
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
                if (message instanceof CallPointList) {
                    return mergeFrom((CallPointList) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CallPointList callPointList) {
                if (callPointList == CallPointList.getDefaultInstance()) {
                    return this;
                }
                if (this.callPointBuilder_ == null) {
                    if (!callPointList.callPoint_.isEmpty()) {
                        if (this.callPoint_.isEmpty()) {
                            this.callPoint_ = callPointList.callPoint_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureCallPointIsMutable();
                            this.callPoint_.addAll(callPointList.callPoint_);
                        }
                        onChanged();
                    }
                } else if (!callPointList.callPoint_.isEmpty()) {
                    if (!this.callPointBuilder_.isEmpty()) {
                        this.callPointBuilder_.addAllMessages(callPointList.callPoint_);
                    } else {
                        this.callPointBuilder_.dispose();
                        this.callPointBuilder_ = null;
                        this.callPoint_ = callPointList.callPoint_;
                        this.bitField0_ &= -2;
                        this.callPointBuilder_ = CallPointList.alwaysUseFieldBuilders ? getCallPointFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(callPointList.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CallPointList callPointList = null;
                try {
                    try {
                        CallPointList callPointList2 = (CallPointList) CallPointList.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (callPointList2 != null) {
                            mergeFrom(callPointList2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        CallPointList callPointList3 = (CallPointList) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            callPointList = callPointList3;
                            if (callPointList != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (callPointList != null) {
                        mergeFrom(callPointList);
                    }
                    throw th;
                }
            }

            private void ensureCallPointIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.callPoint_ = new ArrayList(this.callPoint_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
            public List<CallPoint> getCallPointList() {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.callPoint_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
            public int getCallPointCount() {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.callPoint_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
            public CallPoint getCallPoint(int i) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.callPoint_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setCallPoint(int i, CallPoint callPoint) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, callPoint);
                } else {
                    if (callPoint == null) {
                        throw null;
                    }
                    ensureCallPointIsMutable();
                    this.callPoint_.set(i, callPoint);
                    onChanged();
                }
                return this;
            }

            public Builder setCallPoint(int i, CallPoint.Builder builder) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallPointIsMutable();
                    this.callPoint_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addCallPoint(CallPoint callPoint) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(callPoint);
                } else {
                    if (callPoint == null) {
                        throw null;
                    }
                    ensureCallPointIsMutable();
                    this.callPoint_.add(callPoint);
                    onChanged();
                }
                return this;
            }

            public Builder addCallPoint(int i, CallPoint callPoint) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, callPoint);
                } else {
                    if (callPoint == null) {
                        throw null;
                    }
                    ensureCallPointIsMutable();
                    this.callPoint_.add(i, callPoint);
                    onChanged();
                }
                return this;
            }

            public Builder addCallPoint(CallPoint.Builder builder) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallPointIsMutable();
                    this.callPoint_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addCallPoint(int i, CallPoint.Builder builder) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallPointIsMutable();
                    this.callPoint_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllCallPoint(Iterable<? extends CallPoint> iterable) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallPointIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.callPoint_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearCallPoint() {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.callPoint_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeCallPoint(int i) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallPointIsMutable();
                    this.callPoint_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public CallPoint.Builder getCallPointBuilder(int i) {
                return getCallPointFieldBuilder().getBuilder(i);
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
            public CallPointOrBuilder getCallPointOrBuilder(int i) {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.callPoint_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.CallPointListOrBuilder
            public List<? extends CallPointOrBuilder> getCallPointOrBuilderList() {
                RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> repeatedFieldBuilderV3 = this.callPointBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.callPoint_);
            }

            public CallPoint.Builder addCallPointBuilder() {
                return getCallPointFieldBuilder().addBuilder(CallPoint.getDefaultInstance());
            }

            public CallPoint.Builder addCallPointBuilder(int i) {
                return getCallPointFieldBuilder().addBuilder(i, CallPoint.getDefaultInstance());
            }

            public List<CallPoint.Builder> getCallPointBuilderList() {
                return getCallPointFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<CallPoint, CallPoint.Builder, CallPointOrBuilder> getCallPointFieldBuilder() {
                if (this.callPointBuilder_ == null) {
                    this.callPointBuilder_ = new RepeatedFieldBuilderV3<>(this.callPoint_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.callPoint_ = null;
                }
                return this.callPointBuilder_;
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

        public static CallPointList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CallPointList> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CallPointList> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CallPointList getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class Channel extends GeneratedMessageV3 implements ChannelOrBuilder {
        public static final int CHANNELID_FIELD_NUMBER = 1;
        private static final Channel DEFAULT_INSTANCE = new Channel();
        private static final Parser<Channel> PARSER = new AbstractParser<Channel>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.Channel.1
            @Override // com.google.protobuf.Parser
            public Channel parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Channel(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private int channelId_;
        private byte memoizedIsInitialized;

        private Channel(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Channel() {
            this.memoizedIsInitialized = (byte) -1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Channel();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Channel(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.channelId_ = codedInputStream.readInt32();
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
            return MessageProtobuf.internal_static_Channel_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_Channel_fieldAccessorTable.ensureFieldAccessorsInitialized(Channel.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelOrBuilder
        public int getChannelId() {
            return this.channelId_;
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
            int i = this.channelId_;
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
            int i2 = this.channelId_;
            int computeInt32Size = (i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeInt32Size;
            return computeInt32Size;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Channel)) {
                return super.equals(obj);
            }
            Channel channel = (Channel) obj;
            return getChannelId() == channel.getChannelId() && this.unknownFields.equals(channel.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getChannelId()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Channel parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Channel parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Channel parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Channel parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Channel parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Channel parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Channel parseFrom(InputStream inputStream) throws IOException {
            return (Channel) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Channel parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Channel) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Channel parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Channel) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Channel parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Channel) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Channel parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Channel) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Channel parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Channel) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Channel channel) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(channel);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChannelOrBuilder {
            private int channelId_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_Channel_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_Channel_fieldAccessorTable.ensureFieldAccessorsInitialized(Channel.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Channel.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.channelId_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_Channel_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Channel getDefaultInstanceForType() {
                return Channel.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Channel build() {
                Channel buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Channel buildPartial() {
                Channel channel = new Channel(this);
                channel.channelId_ = this.channelId_;
                onBuilt();
                return channel;
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
                if (message instanceof Channel) {
                    return mergeFrom((Channel) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Channel channel) {
                if (channel == Channel.getDefaultInstance()) {
                    return this;
                }
                if (channel.getChannelId() != 0) {
                    setChannelId(channel.getChannelId());
                }
                mergeUnknownFields(channel.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Channel channel = null;
                try {
                    try {
                        Channel channel2 = (Channel) Channel.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (channel2 != null) {
                            mergeFrom(channel2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Channel channel3 = (Channel) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            channel = channel3;
                            if (channel != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (channel != null) {
                        mergeFrom(channel);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelOrBuilder
            public int getChannelId() {
                return this.channelId_;
            }

            public Builder setChannelId(int i) {
                this.channelId_ = i;
                onChanged();
                return this;
            }

            public Builder clearChannelId() {
                this.channelId_ = 0;
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

        public static Channel getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Channel> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Channel> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Channel getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class ChannelList extends GeneratedMessageV3 implements ChannelListOrBuilder {
        public static final int CHANNEL_FIELD_NUMBER = 1;
        private static final ChannelList DEFAULT_INSTANCE = new ChannelList();
        private static final Parser<ChannelList> PARSER = new AbstractParser<ChannelList>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.ChannelList.1
            @Override // com.google.protobuf.Parser
            public ChannelList parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ChannelList(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private List<Channel> channel_;
        private byte memoizedIsInitialized;

        private ChannelList(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ChannelList() {
            this.memoizedIsInitialized = (byte) -1;
            this.channel_ = Collections.emptyList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new ChannelList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ChannelList(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw null;
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
                                    if (!(z2 & true)) {
                                        this.channel_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.channel_.add(codedInputStream.readMessage(Channel.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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
                        this.channel_ = Collections.unmodifiableList(this.channel_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MessageProtobuf.internal_static_ChannelList_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_ChannelList_fieldAccessorTable.ensureFieldAccessorsInitialized(ChannelList.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
        public List<Channel> getChannelList() {
            return this.channel_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
        public List<? extends ChannelOrBuilder> getChannelOrBuilderList() {
            return this.channel_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
        public int getChannelCount() {
            return this.channel_.size();
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
        public Channel getChannel(int i) {
            return this.channel_.get(i);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
        public ChannelOrBuilder getChannelOrBuilder(int i) {
            return this.channel_.get(i);
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
            for (int i = 0; i < this.channel_.size(); i++) {
                codedOutputStream.writeMessage(1, this.channel_.get(i));
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
            for (int i3 = 0; i3 < this.channel_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.channel_.get(i3));
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
            if (!(obj instanceof ChannelList)) {
                return super.equals(obj);
            }
            ChannelList channelList = (ChannelList) obj;
            return getChannelList().equals(channelList.getChannelList()) && this.unknownFields.equals(channelList.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            if (getChannelCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getChannelList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static ChannelList parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ChannelList parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ChannelList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ChannelList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ChannelList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ChannelList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ChannelList parseFrom(InputStream inputStream) throws IOException {
            return (ChannelList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ChannelList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChannelList) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ChannelList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ChannelList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ChannelList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChannelList) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ChannelList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ChannelList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ChannelList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ChannelList) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ChannelList channelList) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(channelList);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChannelListOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> channelBuilder_;
            private List<Channel> channel_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_ChannelList_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_ChannelList_fieldAccessorTable.ensureFieldAccessorsInitialized(ChannelList.class, Builder.class);
            }

            private Builder() {
                this.channel_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.channel_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (ChannelList.alwaysUseFieldBuilders) {
                    getChannelFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.channel_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_ChannelList_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ChannelList getDefaultInstanceForType() {
                return ChannelList.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChannelList build() {
                ChannelList buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChannelList buildPartial() {
                ChannelList channelList = new ChannelList(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.channel_ = Collections.unmodifiableList(this.channel_);
                        this.bitField0_ &= -2;
                    }
                    channelList.channel_ = this.channel_;
                } else {
                    channelList.channel_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return channelList;
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
                if (message instanceof ChannelList) {
                    return mergeFrom((ChannelList) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ChannelList channelList) {
                if (channelList == ChannelList.getDefaultInstance()) {
                    return this;
                }
                if (this.channelBuilder_ == null) {
                    if (!channelList.channel_.isEmpty()) {
                        if (this.channel_.isEmpty()) {
                            this.channel_ = channelList.channel_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureChannelIsMutable();
                            this.channel_.addAll(channelList.channel_);
                        }
                        onChanged();
                    }
                } else if (!channelList.channel_.isEmpty()) {
                    if (!this.channelBuilder_.isEmpty()) {
                        this.channelBuilder_.addAllMessages(channelList.channel_);
                    } else {
                        this.channelBuilder_.dispose();
                        this.channelBuilder_ = null;
                        this.channel_ = channelList.channel_;
                        this.bitField0_ &= -2;
                        this.channelBuilder_ = ChannelList.alwaysUseFieldBuilders ? getChannelFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(channelList.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ChannelList channelList = null;
                try {
                    try {
                        ChannelList channelList2 = (ChannelList) ChannelList.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (channelList2 != null) {
                            mergeFrom(channelList2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ChannelList channelList3 = (ChannelList) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            channelList = channelList3;
                            if (channelList != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (channelList != null) {
                        mergeFrom(channelList);
                    }
                    throw th;
                }
            }

            private void ensureChannelIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.channel_ = new ArrayList(this.channel_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
            public List<Channel> getChannelList() {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.channel_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
            public int getChannelCount() {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.channel_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
            public Channel getChannel(int i) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.channel_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setChannel(int i, Channel channel) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, channel);
                } else {
                    if (channel == null) {
                        throw null;
                    }
                    ensureChannelIsMutable();
                    this.channel_.set(i, channel);
                    onChanged();
                }
                return this;
            }

            public Builder setChannel(int i, Channel.Builder builder) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureChannelIsMutable();
                    this.channel_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addChannel(Channel channel) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(channel);
                } else {
                    if (channel == null) {
                        throw null;
                    }
                    ensureChannelIsMutable();
                    this.channel_.add(channel);
                    onChanged();
                }
                return this;
            }

            public Builder addChannel(int i, Channel channel) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, channel);
                } else {
                    if (channel == null) {
                        throw null;
                    }
                    ensureChannelIsMutable();
                    this.channel_.add(i, channel);
                    onChanged();
                }
                return this;
            }

            public Builder addChannel(Channel.Builder builder) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureChannelIsMutable();
                    this.channel_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addChannel(int i, Channel.Builder builder) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureChannelIsMutable();
                    this.channel_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllChannel(Iterable<? extends Channel> iterable) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureChannelIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.channel_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearChannel() {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.channel_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeChannel(int i) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureChannelIsMutable();
                    this.channel_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Channel.Builder getChannelBuilder(int i) {
                return getChannelFieldBuilder().getBuilder(i);
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
            public ChannelOrBuilder getChannelOrBuilder(int i) {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.channel_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.ChannelListOrBuilder
            public List<? extends ChannelOrBuilder> getChannelOrBuilderList() {
                RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> repeatedFieldBuilderV3 = this.channelBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.channel_);
            }

            public Channel.Builder addChannelBuilder() {
                return getChannelFieldBuilder().addBuilder(Channel.getDefaultInstance());
            }

            public Channel.Builder addChannelBuilder(int i) {
                return getChannelFieldBuilder().addBuilder(i, Channel.getDefaultInstance());
            }

            public List<Channel.Builder> getChannelBuilderList() {
                return getChannelFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Channel, Channel.Builder, ChannelOrBuilder> getChannelFieldBuilder() {
                if (this.channelBuilder_ == null) {
                    this.channelBuilder_ = new RepeatedFieldBuilderV3<>(this.channel_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.channel_ = null;
                }
                return this.channelBuilder_;
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

        public static ChannelList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ChannelList> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ChannelList> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ChannelList getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class BroadcastControl extends GeneratedMessageV3 implements BroadcastControlOrBuilder {
        public static final int CENTRALCONTROLCODE_FIELD_NUMBER = 3;
        public static final int ENABLE_FIELD_NUMBER = 1;
        public static final int MAC_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object centralControlCode_;
        private boolean enable_;
        private volatile Object mac_;
        private byte memoizedIsInitialized;
        private static final BroadcastControl DEFAULT_INSTANCE = new BroadcastControl();
        private static final Parser<BroadcastControl> PARSER = new AbstractParser<BroadcastControl>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControl.1
            @Override // com.google.protobuf.Parser
            public BroadcastControl parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new BroadcastControl(codedInputStream, extensionRegistryLite);
            }
        };

        private BroadcastControl(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private BroadcastControl() {
            this.memoizedIsInitialized = (byte) -1;
            this.mac_ = "";
            this.centralControlCode_ = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new BroadcastControl();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BroadcastControl(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.enable_ = codedInputStream.readBool();
                            } else if (readTag == 18) {
                                this.mac_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.centralControlCode_ = codedInputStream.readStringRequireUtf8();
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
            return MessageProtobuf.internal_static_BroadcastControl_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_BroadcastControl_fieldAccessorTable.ensureFieldAccessorsInitialized(BroadcastControl.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
        public boolean getEnable() {
            return this.enable_;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
        public String getMac() {
            Object obj = this.mac_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.mac_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
        public ByteString getMacBytes() {
            Object obj = this.mac_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mac_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
        public String getCentralControlCode() {
            Object obj = this.centralControlCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.centralControlCode_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
        public ByteString getCentralControlCodeBytes() {
            Object obj = this.centralControlCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.centralControlCode_ = copyFromUtf8;
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
            boolean z = this.enable_;
            if (z) {
                codedOutputStream.writeBool(1, z);
            }
            if (!getMacBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.mac_);
            }
            if (!getCentralControlCodeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.centralControlCode_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            boolean z = this.enable_;
            int computeBoolSize = z ? 0 + CodedOutputStream.computeBoolSize(1, z) : 0;
            if (!getMacBytes().isEmpty()) {
                computeBoolSize += GeneratedMessageV3.computeStringSize(2, this.mac_);
            }
            if (!getCentralControlCodeBytes().isEmpty()) {
                computeBoolSize += GeneratedMessageV3.computeStringSize(3, this.centralControlCode_);
            }
            int serializedSize = computeBoolSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BroadcastControl)) {
                return super.equals(obj);
            }
            BroadcastControl broadcastControl = (BroadcastControl) obj;
            return getEnable() == broadcastControl.getEnable() && getMac().equals(broadcastControl.getMac()) && getCentralControlCode().equals(broadcastControl.getCentralControlCode()) && this.unknownFields.equals(broadcastControl.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashBoolean(getEnable())) * 37) + 2) * 53) + getMac().hashCode()) * 37) + 3) * 53) + getCentralControlCode().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static BroadcastControl parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static BroadcastControl parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static BroadcastControl parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static BroadcastControl parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static BroadcastControl parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static BroadcastControl parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static BroadcastControl parseFrom(InputStream inputStream) throws IOException {
            return (BroadcastControl) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static BroadcastControl parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BroadcastControl) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BroadcastControl parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BroadcastControl) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static BroadcastControl parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BroadcastControl) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static BroadcastControl parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (BroadcastControl) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static BroadcastControl parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BroadcastControl) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(BroadcastControl broadcastControl) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(broadcastControl);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BroadcastControlOrBuilder {
            private Object centralControlCode_;
            private boolean enable_;
            private Object mac_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_BroadcastControl_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_BroadcastControl_fieldAccessorTable.ensureFieldAccessorsInitialized(BroadcastControl.class, Builder.class);
            }

            private Builder() {
                this.mac_ = "";
                this.centralControlCode_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.mac_ = "";
                this.centralControlCode_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BroadcastControl.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.enable_ = false;
                this.mac_ = "";
                this.centralControlCode_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_BroadcastControl_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public BroadcastControl getDefaultInstanceForType() {
                return BroadcastControl.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BroadcastControl build() {
                BroadcastControl buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BroadcastControl buildPartial() {
                BroadcastControl broadcastControl = new BroadcastControl(this);
                broadcastControl.enable_ = this.enable_;
                broadcastControl.mac_ = this.mac_;
                broadcastControl.centralControlCode_ = this.centralControlCode_;
                onBuilt();
                return broadcastControl;
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
                if (message instanceof BroadcastControl) {
                    return mergeFrom((BroadcastControl) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(BroadcastControl broadcastControl) {
                if (broadcastControl == BroadcastControl.getDefaultInstance()) {
                    return this;
                }
                if (broadcastControl.getEnable()) {
                    setEnable(broadcastControl.getEnable());
                }
                if (!broadcastControl.getMac().isEmpty()) {
                    this.mac_ = broadcastControl.mac_;
                    onChanged();
                }
                if (!broadcastControl.getCentralControlCode().isEmpty()) {
                    this.centralControlCode_ = broadcastControl.centralControlCode_;
                    onChanged();
                }
                mergeUnknownFields(broadcastControl.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                BroadcastControl broadcastControl = null;
                try {
                    try {
                        BroadcastControl broadcastControl2 = (BroadcastControl) BroadcastControl.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (broadcastControl2 != null) {
                            mergeFrom(broadcastControl2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        BroadcastControl broadcastControl3 = (BroadcastControl) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            broadcastControl = broadcastControl3;
                            if (broadcastControl != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (broadcastControl != null) {
                        mergeFrom(broadcastControl);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
            public boolean getEnable() {
                return this.enable_;
            }

            public Builder setEnable(boolean z) {
                this.enable_ = z;
                onChanged();
                return this;
            }

            public Builder clearEnable() {
                this.enable_ = false;
                onChanged();
                return this;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
            public String getMac() {
                Object obj = this.mac_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mac_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
            public ByteString getMacBytes() {
                Object obj = this.mac_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mac_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMac(String str) {
                if (str == null) {
                    throw null;
                }
                this.mac_ = str;
                onChanged();
                return this;
            }

            public Builder clearMac() {
                this.mac_ = BroadcastControl.getDefaultInstance().getMac();
                onChanged();
                return this;
            }

            public Builder setMacBytes(ByteString byteString) {
                if (byteString != null) {
                    BroadcastControl.checkByteStringIsUtf8(byteString);
                    this.mac_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
            public String getCentralControlCode() {
                Object obj = this.centralControlCode_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.centralControlCode_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.BroadcastControlOrBuilder
            public ByteString getCentralControlCodeBytes() {
                Object obj = this.centralControlCode_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.centralControlCode_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCentralControlCode(String str) {
                if (str == null) {
                    throw null;
                }
                this.centralControlCode_ = str;
                onChanged();
                return this;
            }

            public Builder clearCentralControlCode() {
                this.centralControlCode_ = BroadcastControl.getDefaultInstance().getCentralControlCode();
                onChanged();
                return this;
            }

            public Builder setCentralControlCodeBytes(ByteString byteString) {
                if (byteString != null) {
                    BroadcastControl.checkByteStringIsUtf8(byteString);
                    this.centralControlCode_ = byteString;
                    onChanged();
                    return this;
                }
                throw null;
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

        public static BroadcastControl getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BroadcastControl> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<BroadcastControl> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BroadcastControl getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static final class RobotStatusReport extends GeneratedMessageV3 implements RobotStatusReportOrBuilder {
        private static final RobotStatusReport DEFAULT_INSTANCE = new RobotStatusReport();
        private static final Parser<RobotStatusReport> PARSER = new AbstractParser<RobotStatusReport>() { // from class: com.pudutech.hola.protobuf.MessageProtobuf.RobotStatusReport.1
            @Override // com.google.protobuf.Parser
            public RobotStatusReport parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RobotStatusReport(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int ROBOTSTATE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int robotState_;

        private RobotStatusReport(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private RobotStatusReport() {
            this.memoizedIsInitialized = (byte) -1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new RobotStatusReport();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private RobotStatusReport(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.robotState_ = codedInputStream.readInt32();
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
            return MessageProtobuf.internal_static_RobotStatusReport_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MessageProtobuf.internal_static_RobotStatusReport_fieldAccessorTable.ensureFieldAccessorsInitialized(RobotStatusReport.class, Builder.class);
        }

        @Override // com.pudutech.hola.protobuf.MessageProtobuf.RobotStatusReportOrBuilder
        public int getRobotState() {
            return this.robotState_;
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
            int i = this.robotState_;
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
            int i2 = this.robotState_;
            int computeInt32Size = (i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = computeInt32Size;
            return computeInt32Size;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RobotStatusReport)) {
                return super.equals(obj);
            }
            RobotStatusReport robotStatusReport = (RobotStatusReport) obj;
            return getRobotState() == robotStatusReport.getRobotState() && this.unknownFields.equals(robotStatusReport.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRobotState()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static RobotStatusReport parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RobotStatusReport parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RobotStatusReport parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RobotStatusReport parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RobotStatusReport parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RobotStatusReport parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RobotStatusReport parseFrom(InputStream inputStream) throws IOException {
            return (RobotStatusReport) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RobotStatusReport parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotStatusReport) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RobotStatusReport parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RobotStatusReport) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RobotStatusReport parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotStatusReport) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RobotStatusReport parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RobotStatusReport) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RobotStatusReport parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RobotStatusReport) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RobotStatusReport robotStatusReport) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(robotStatusReport);
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
          classes5.dex
         */
        /* loaded from: classes.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RobotStatusReportOrBuilder {
            private int robotState_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MessageProtobuf.internal_static_RobotStatusReport_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MessageProtobuf.internal_static_RobotStatusReport_fieldAccessorTable.ensureFieldAccessorsInitialized(RobotStatusReport.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RobotStatusReport.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.robotState_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MessageProtobuf.internal_static_RobotStatusReport_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public RobotStatusReport getDefaultInstanceForType() {
                return RobotStatusReport.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RobotStatusReport build() {
                RobotStatusReport buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RobotStatusReport buildPartial() {
                RobotStatusReport robotStatusReport = new RobotStatusReport(this);
                robotStatusReport.robotState_ = this.robotState_;
                onBuilt();
                return robotStatusReport;
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
                if (message instanceof RobotStatusReport) {
                    return mergeFrom((RobotStatusReport) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RobotStatusReport robotStatusReport) {
                if (robotStatusReport == RobotStatusReport.getDefaultInstance()) {
                    return this;
                }
                if (robotStatusReport.getRobotState() != 0) {
                    setRobotState(robotStatusReport.getRobotState());
                }
                mergeUnknownFields(robotStatusReport.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                RobotStatusReport robotStatusReport = null;
                try {
                    try {
                        RobotStatusReport robotStatusReport2 = (RobotStatusReport) RobotStatusReport.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (robotStatusReport2 != null) {
                            mergeFrom(robotStatusReport2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        RobotStatusReport robotStatusReport3 = (RobotStatusReport) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            robotStatusReport = robotStatusReport3;
                            if (robotStatusReport != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (robotStatusReport != null) {
                        mergeFrom(robotStatusReport);
                    }
                    throw th;
                }
            }

            @Override // com.pudutech.hola.protobuf.MessageProtobuf.RobotStatusReportOrBuilder
            public int getRobotState() {
                return this.robotState_;
            }

            public Builder setRobotState(int i) {
                this.robotState_ = i;
                onChanged();
                return this;
            }

            public Builder clearRobotState() {
                this.robotState_ = 0;
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

        public static RobotStatusReport getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RobotStatusReport> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<RobotStatusReport> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RobotStatusReport getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_Msg_descriptor = descriptor2;
        internal_static_Msg_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"MsgId", "MsgType", "Sender", "Receiver", "Timestamp", "Task", "TaskList", "CallPoint", "CallPointList", "Channel", "ChannelList", "BroadcastControl", "RobotStatusReport", "MapVersion", "MapFrameHead", "MapFrameNext", "IsFrameEnd", "HasUnallocatedTask", "Resp"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_Task_descriptor = descriptor3;
        internal_static_Task_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"TaskId", "State"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_TaskList_descriptor = descriptor4;
        internal_static_TaskList_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Task"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_CallPoint_descriptor = descriptor5;
        internal_static_CallPoint_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"CallPointId", "Type"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_CallPointList_descriptor = descriptor6;
        internal_static_CallPointList_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"CallPoint"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_Channel_descriptor = descriptor7;
        internal_static_Channel_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"ChannelId"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_ChannelList_descriptor = descriptor8;
        internal_static_ChannelList_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Channel"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_BroadcastControl_descriptor = descriptor9;
        internal_static_BroadcastControl_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Enable", "Mac", "CentralControlCode"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_RobotStatusReport_descriptor = descriptor10;
        internal_static_RobotStatusReport_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"RobotState"});
    }
}
