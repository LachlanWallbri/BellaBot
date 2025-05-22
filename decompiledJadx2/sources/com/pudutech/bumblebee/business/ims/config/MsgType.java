package com.pudutech.bumblebee.business.ims.config;

import androidx.core.view.PointerIconCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: MsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u001d\b\u0086\u0001\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/config/MsgType;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "Call", "CancelCall", "RequestCallPointList", "SyncRobotState", "RequestTask", "AddDevice", "SetChannel", "SyncMap", "BeeperCardCall", "BroadcastControl", "SyncTaskState", "QueryMapVersion", "DeleteDevice", "ResetCentralControl", "HasUnallocatedTask", "AddBeeperBroadcastControl", "CheckMapSyncStatus", "ReportCurrentTime", "SyncTaskPartitionTable", "CheckTaskPartitionTableSyncStatus", "DeleteTaskPartitionTable", "EnableTaskPartition", "DisableTaskPartition", "UNKNOWN", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum MsgType {
    Call(1001),
    CancelCall(1002),
    RequestCallPointList(1003),
    SyncRobotState(1004),
    RequestTask(1005),
    AddDevice(PointerIconCompat.TYPE_CELL),
    SetChannel(PointerIconCompat.TYPE_CROSSHAIR),
    SyncMap(PointerIconCompat.TYPE_TEXT),
    BeeperCardCall(PointerIconCompat.TYPE_VERTICAL_TEXT),
    BroadcastControl(PointerIconCompat.TYPE_ALIAS),
    SyncTaskState(PointerIconCompat.TYPE_NO_DROP),
    QueryMapVersion(PointerIconCompat.TYPE_ALL_SCROLL),
    DeleteDevice(PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW),
    ResetCentralControl(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW),
    HasUnallocatedTask(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW),
    AddBeeperBroadcastControl(PointerIconCompat.TYPE_ZOOM_IN),
    CheckMapSyncStatus(PointerIconCompat.TYPE_ZOOM_OUT),
    ReportCurrentTime(2002),
    SyncTaskPartitionTable(3001),
    CheckTaskPartitionTableSyncStatus(3002),
    DeleteTaskPartitionTable(3003),
    EnableTaskPartition(3004),
    DisableTaskPartition(3005),
    UNKNOWN(-1);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int type;

    MsgType(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }

    /* compiled from: MsgType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/config/MsgType$Companion;", "", "()V", "typeOf", "Lcom/pudutech/bumblebee/business/ims/config/MsgType;", "type", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MsgType typeOf(int type) {
            for (MsgType msgType : MsgType.values()) {
                if (type == msgType.getType()) {
                    return msgType;
                }
            }
            return MsgType.UNKNOWN;
        }
    }
}
