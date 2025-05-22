package com.pudutech.bumblebee.business.ims.handler;

import com.pudutech.bumblebee.business.ims.config.MsgType;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgHandlerFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0005R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/handler/MsgHandlerFactory;", "", "()V", "HANDLERS", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/business/ims/config/MsgType;", "Lcom/pudutech/bumblebee/business/ims/handler/IMsgHandler;", "Lkotlin/collections/HashMap;", "findHandlerByMsgType", "msgType", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MsgHandlerFactory {
    public static final MsgHandlerFactory INSTANCE = new MsgHandlerFactory();
    private static final HashMap<MsgType, IMsgHandler> HANDLERS = new HashMap<>();

    static {
        HANDLERS.put(MsgType.AddBeeperBroadcastControl, new AddBeeperBroadcastControlMsgHandler());
        HANDLERS.put(MsgType.AddDevice, new AddDeviceMsgHandler());
        HANDLERS.put(MsgType.BeeperCardCall, new BeeperCardCallMsgHandler());
        HANDLERS.put(MsgType.Call, new CallMsgHandler());
        HANDLERS.put(MsgType.CancelCall, new CancelCallMsgHandler());
        HANDLERS.put(MsgType.BroadcastControl, new CentralControlBroadcastMsgHandler());
        HANDLERS.put(MsgType.CheckMapSyncStatus, new CheckMapSyncStatusMsgHandler());
        HANDLERS.put(MsgType.CheckTaskPartitionTableSyncStatus, new CheckTaskPartitionTableSyncStatusMsgHandler());
        HANDLERS.put(MsgType.DeleteDevice, new DeleteDeviceMsgHandler());
        HANDLERS.put(MsgType.HasUnallocatedTask, new HasUnallocatedTaskMsgHandler());
        HANDLERS.put(MsgType.QueryMapVersion, new QueryMapVersionMsgHandler());
        HANDLERS.put(MsgType.SyncRobotState, new ReportRobotStatusMsgHandler());
        HANDLERS.put(MsgType.SyncTaskState, new ReportTaskStatusMsgHandler());
        HANDLERS.put(MsgType.RequestTask, new RequestTaskMsgHandler());
        HANDLERS.put(MsgType.ResetCentralControl, new ResetCentralControlMsgHandler());
        HANDLERS.put(MsgType.SetChannel, new SetChannelMsgHandler());
        HANDLERS.put(MsgType.SyncMap, new SyncMapMsgHandler());
    }

    private MsgHandlerFactory() {
    }

    public final IMsgHandler findHandlerByMsgType(MsgType msgType) {
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        return HANDLERS.get(msgType);
    }
}
