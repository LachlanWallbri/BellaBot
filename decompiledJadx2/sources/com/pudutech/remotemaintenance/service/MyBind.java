package com.pudutech.remotemaintenance.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.IoTManagerFactory;
import com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener;
import com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RemoteMaintenanceService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\rH\u0016J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\u0012\u0010\u001b\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0018H\u0016J\u0012\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010%\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002JU\u0010&\u001a\u00020\u00162\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0018H\u0002¢\u0006\u0002\u0010'R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/remotemaintenance/service/MyBind;", "Lcom/pudutech/remotemaintenance/aidl/RemoteMaintenanceInterface$Stub;", "service", "Lcom/pudutech/remotemaintenance/service/RemoteMaintenanceService;", "(Lcom/pudutech/remotemaintenance/service/RemoteMaintenanceService;)V", "iotMapSyncMsg", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "iotMapSyncMsgObj", "Lcom/alibaba/fastjson/JSONObject;", "kotlin.jvm.PlatformType", "iotReportMsg", "iotReportMsgObj", "remoteMaintenanceListener", "Lcom/pudutech/remotemaintenance/aidl/IRemoteMaintenanceListener;", "getRemoteMaintenanceListener", "()Lcom/pudutech/remotemaintenance/aidl/IRemoteMaintenanceListener;", "setRemoteMaintenanceListener", "(Lcom/pudutech/remotemaintenance/aidl/IRemoteMaintenanceListener;)V", "init", "Lcom/pudutech/remotemaintenance/aidl/RemoteMaintenanceInterface;", "listener", "setCoordinateOrientation", "", "xCoordinate", "", "yCoordinate", "orientation", "setMapPath", "mapPath", "", "setPower", "power", "", "setSpeed", "speed", "setTask", "task", "updateIoTMapSyncMsg", "updateIoTReportMsg", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class MyBind extends RemoteMaintenanceInterface.Stub {
    private final AliyunIoTMsg iotMapSyncMsg;
    private final JSONObject iotMapSyncMsgObj;
    private final AliyunIoTMsg iotReportMsg;
    private final JSONObject iotReportMsgObj;
    private IRemoteMaintenanceListener remoteMaintenanceListener;
    private final RemoteMaintenanceService service;

    public MyBind(RemoteMaintenanceService service) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        Intrinsics.checkParameterIsNotNull(service, "service");
        this.service = service;
        AliyunIoTMsg reportMsg = IoTManagerFactory.INSTANCE.getIoTManager().getReportMsg();
        reportMsg = reportMsg == null ? new AliyunIoTMsg() : reportMsg;
        this.iotReportMsg = reportMsg;
        String content = reportMsg.getContent();
        if (!(content == null || content.length() == 0)) {
            jSONObject = JSON.parseObject(this.iotReportMsg.getContent());
        } else {
            jSONObject = new JSONObject();
        }
        this.iotReportMsgObj = jSONObject;
        AliyunIoTMsg mapSyncMsg = IoTManagerFactory.INSTANCE.getIoTManager().getMapSyncMsg();
        mapSyncMsg = mapSyncMsg == null ? new AliyunIoTMsg() : mapSyncMsg;
        this.iotMapSyncMsg = mapSyncMsg;
        String content2 = mapSyncMsg.getContent();
        if (!(content2 == null || content2.length() == 0)) {
            jSONObject2 = JSON.parseObject(this.iotMapSyncMsg.getContent());
        } else {
            jSONObject2 = new JSONObject();
        }
        this.iotMapSyncMsgObj = jSONObject2;
    }

    public final IRemoteMaintenanceListener getRemoteMaintenanceListener() {
        return this.remoteMaintenanceListener;
    }

    public final void setRemoteMaintenanceListener(IRemoteMaintenanceListener iRemoteMaintenanceListener) {
        this.remoteMaintenanceListener = iRemoteMaintenanceListener;
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public RemoteMaintenanceInterface init(IRemoteMaintenanceListener listener) {
        IRemoteMaintenanceListener iRemoteMaintenanceListener;
        this.remoteMaintenanceListener = listener;
        int connectState = this.service.getConnectState();
        if (connectState != -1) {
            if (connectState == 0) {
                IRemoteMaintenanceListener iRemoteMaintenanceListener2 = this.remoteMaintenanceListener;
                if (iRemoteMaintenanceListener2 != null) {
                    iRemoteMaintenanceListener2.onConnecting();
                }
            } else if (connectState == 1 && (iRemoteMaintenanceListener = this.remoteMaintenanceListener) != null) {
                iRemoteMaintenanceListener.onConnected();
            }
        } else if (this.service.getErrCode() != null && this.service.getErrMsg() != null) {
            IRemoteMaintenanceListener iRemoteMaintenanceListener3 = this.remoteMaintenanceListener;
            if (iRemoteMaintenanceListener3 != null) {
                Integer errCode = this.service.getErrCode();
                if (errCode == null) {
                    Intrinsics.throwNpe();
                }
                iRemoteMaintenanceListener3.onConnectFailure(errCode.intValue(), this.service.getErrMsg());
            }
        } else {
            IRemoteMaintenanceListener iRemoteMaintenanceListener4 = this.remoteMaintenanceListener;
            if (iRemoteMaintenanceListener4 != null) {
                iRemoteMaintenanceListener4.onConnectFailure(-100, "first connect");
            }
        }
        return this;
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setPower(int power) {
        updateIoTReportMsg$default(this, Integer.valueOf(power), null, null, null, null, null, 62, null);
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setSpeed(double speed) {
        updateIoTReportMsg$default(this, null, Double.valueOf(speed), null, null, null, null, 61, null);
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setTask(String task) {
        updateIoTReportMsg$default(this, null, null, task, null, null, null, 59, null);
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setCoordinateOrientation(double xCoordinate, double yCoordinate, double orientation) {
        updateIoTReportMsg$default(this, null, null, null, Double.valueOf(xCoordinate), Double.valueOf(yCoordinate), Double.valueOf(orientation), 7, null);
    }

    @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
    public void setMapPath(String mapPath) {
        Pdlog.m3273d(RemoteMaintenanceService.TAG, "setMapPath() mapPath = " + mapPath);
        updateIoTMapSyncMsg(mapPath);
    }

    static /* synthetic */ void updateIoTReportMsg$default(MyBind myBind, Integer num, Double d, String str, Double d2, Double d3, Double d4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            d = (Double) null;
        }
        Double d5 = d;
        if ((i & 4) != 0) {
            str = (String) null;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            d2 = (Double) null;
        }
        Double d6 = d2;
        if ((i & 16) != 0) {
            d3 = (Double) null;
        }
        Double d7 = d3;
        if ((i & 32) != 0) {
            d4 = (Double) null;
        }
        myBind.updateIoTReportMsg(num, d5, str2, d6, d7, d4);
    }

    private final void updateIoTReportMsg(Integer power, Double speed, String task, Double xCoordinate, Double yCoordinate, Double orientation) {
        if (power != null) {
            power.intValue();
            JSONObject iotReportMsgObj = this.iotReportMsgObj;
            Intrinsics.checkExpressionValueIsNotNull(iotReportMsgObj, "iotReportMsgObj");
            iotReportMsgObj.put((JSONObject) "power", (String) power);
        }
        if (speed != null) {
            speed.doubleValue();
            JSONObject iotReportMsgObj2 = this.iotReportMsgObj;
            Intrinsics.checkExpressionValueIsNotNull(iotReportMsgObj2, "iotReportMsgObj");
            iotReportMsgObj2.put((JSONObject) "speed", (String) speed);
        }
        if (task != null) {
            JSONObject iotReportMsgObj3 = this.iotReportMsgObj;
            Intrinsics.checkExpressionValueIsNotNull(iotReportMsgObj3, "iotReportMsgObj");
            iotReportMsgObj3.put((JSONObject) "task", task);
        }
        if (xCoordinate != null) {
            xCoordinate.doubleValue();
            JSONObject iotReportMsgObj4 = this.iotReportMsgObj;
            Intrinsics.checkExpressionValueIsNotNull(iotReportMsgObj4, "iotReportMsgObj");
            iotReportMsgObj4.put((JSONObject) "xCoordinate", (String) xCoordinate);
        }
        if (yCoordinate != null) {
            yCoordinate.doubleValue();
            JSONObject iotReportMsgObj5 = this.iotReportMsgObj;
            Intrinsics.checkExpressionValueIsNotNull(iotReportMsgObj5, "iotReportMsgObj");
            iotReportMsgObj5.put((JSONObject) "yCoordinate", (String) yCoordinate);
        }
        if (orientation != null) {
            orientation.doubleValue();
            JSONObject iotReportMsgObj6 = this.iotReportMsgObj;
            Intrinsics.checkExpressionValueIsNotNull(iotReportMsgObj6, "iotReportMsgObj");
            iotReportMsgObj6.put((JSONObject) "orientation", (String) orientation);
        }
        this.iotReportMsg.setContent(this.iotReportMsgObj.toString());
        IoTManagerFactory.INSTANCE.getIoTManager().setReportMsg(this.iotReportMsg);
    }

    private final void updateIoTMapSyncMsg(String mapPath) {
        boolean z = true;
        Pdlog.m3273d(RemoteMaintenanceService.TAG, "updateIoTMapSyncMsg() mapPath=" + mapPath);
        String str = mapPath;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MyBind$updateIoTMapSyncMsg$1(this, mapPath, null), 3, null);
    }
}
