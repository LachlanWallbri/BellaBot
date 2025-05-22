package com.pudutech.remotemaintenance.handler.aliyun;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.App;
import com.pudutech.remotemaintenance.IoTManagerFactory;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.aliyun.config.AliyunMsgType;
import com.pudutech.remotemaintenance.aliyun.config.MsgType;
import com.pudutech.remotemaintenance.utils.SystemTool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AliyunMapSyncMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunMapSyncMsgHandler;", "Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunAbstractMsgHandler;", "()V", "TAG", "", "action", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "iotInterface", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunMapSyncMsgHandler extends AliyunAbstractMsgHandler {
    private final String TAG;

    public AliyunMapSyncMsgHandler() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
    }

    @Override // com.pudutech.remotemaintenance.handler.AbstractMsgHandler
    public void action(AliyunIoTMsg msg, AliyunIoTManager iotInterface) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(iotInterface, "iotInterface");
        Pdlog.m3273d(this.TAG, "msg[" + msg + ']');
        String sessionId = msg.getSessionId();
        AliyunIoTMsg mapSyncMsg = IoTManagerFactory.INSTANCE.getIoTManager().getMapSyncMsg();
        if (mapSyncMsg == null) {
            mapSyncMsg = new AliyunIoTMsg();
        }
        mapSyncMsg.setType(AliyunMsgType.RRPC.getType());
        mapSyncMsg.setInstruct(MsgType.MAP_SYNC.getType());
        mapSyncMsg.setSessionId(sessionId);
        mapSyncMsg.setMac(SystemTool.INSTANCE.getMac(App.INSTANCE.getInstance()));
        IoTManagerFactory.INSTANCE.getIoTManager().setMapSyncMsg(mapSyncMsg);
        iotInterface.sendMsg(mapSyncMsg);
    }
}
