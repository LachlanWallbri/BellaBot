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

/* compiled from: AliyunReportMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunReportMsgHandler;", "Lcom/pudutech/remotemaintenance/handler/aliyun/AliyunAbstractMsgHandler;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "action", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "iotInterface", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunReportMsgHandler extends AliyunAbstractMsgHandler {
    private final String TAG;

    public AliyunReportMsgHandler() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.remotemaintenance.handler.AbstractMsgHandler
    public void action(AliyunIoTMsg msg, AliyunIoTManager iotInterface) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(iotInterface, "iotInterface");
        Pdlog.m3273d(this.TAG, "msg[" + msg + ']');
        String sessionId = msg.getSessionId();
        Integer flag = msg.getFlag();
        if (flag != null && flag.intValue() == 1) {
            Integer num = msg.getNum();
            if (num == null || num.intValue() == 0) {
                return;
            }
            AliyunIoTMsg reportMsg = IoTManagerFactory.INSTANCE.getIoTManager().getReportMsg();
            if (reportMsg == null) {
                reportMsg = new AliyunIoTMsg();
            }
            reportMsg.setType(AliyunMsgType.RRPC.getType());
            reportMsg.setInstruct(MsgType.REPORT_STATE.getType());
            reportMsg.setSessionId(sessionId);
            reportMsg.setMac(SystemTool.INSTANCE.getMac(App.INSTANCE.getInstance()));
            IoTManagerFactory.INSTANCE.getIoTManager().setReportMsg(reportMsg);
            iotInterface.startReport(num.intValue());
            return;
        }
        if (flag != null && flag.intValue() == 0) {
            iotInterface.stopReport();
        }
    }
}
