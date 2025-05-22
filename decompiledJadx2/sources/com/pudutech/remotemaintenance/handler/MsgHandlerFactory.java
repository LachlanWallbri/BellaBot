package com.pudutech.remotemaintenance.handler;

import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.aliyun.config.MsgType;
import com.pudutech.remotemaintenance.config.IoTConfig;
import com.pudutech.remotemaintenance.handler.aliyun.AliyunExecShellCmdMsgHandler;
import com.pudutech.remotemaintenance.handler.aliyun.AliyunFileOperationMsgHandler;
import com.pudutech.remotemaintenance.handler.aliyun.AliyunMapSyncMsgHandler;
import com.pudutech.remotemaintenance.handler.aliyun.AliyunReportMsgHandler;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgHandlerFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0005R&\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/MsgHandlerFactory;", "", "()V", "handlers", "", "", "Lcom/pudutech/remotemaintenance/handler/IMsgHandler;", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "getHandler", "msgType", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MsgHandlerFactory {
    public static final MsgHandlerFactory INSTANCE = new MsgHandlerFactory();
    private static final Map<String, IMsgHandler<AliyunIoTMsg, AliyunIoTManager>> handlers = new LinkedHashMap();

    static {
        if (Intrinsics.areEqual(IoTConfig.INSTANCE.getIOT_PROGRAM(), IoTConfig.IOT_PROGRAM_ALIYUN)) {
            handlers.put(MsgType.REPORT_STATE.getType(), new AliyunReportMsgHandler());
            handlers.put(MsgType.MAP_SYNC.getType(), new AliyunMapSyncMsgHandler());
            handlers.put(MsgType.EXEC_SHELL_CMD.getType(), new AliyunExecShellCmdMsgHandler());
            handlers.put(MsgType.FILE_OPERATION.getType(), new AliyunFileOperationMsgHandler());
        }
    }

    private MsgHandlerFactory() {
    }

    public final IMsgHandler<AliyunIoTMsg, AliyunIoTManager> getHandler(String msgType) {
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        return handlers.get(msgType);
    }
}
