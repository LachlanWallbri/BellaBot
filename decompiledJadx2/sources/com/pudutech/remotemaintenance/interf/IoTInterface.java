package com.pudutech.remotemaintenance.interf;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.remotemaintenance.bean.CIoTMsg;
import com.pudutech.remotemaintenance.listener.ConnectStateCallback;
import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: IoTInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\u000f\u0010\u0007\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\bJ\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0017\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0017\u0010\u0014\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0005H&¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/interf/IoTInterface;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/remotemaintenance/bean/CIoTMsg;", "", MqttServiceConstants.CONNECT_ACTION, "", MqttServiceConstants.DISCONNECT_ACTION, "getMapSyncMsg", "()Lcom/pudutech/remotemaintenance/bean/CIoTMsg;", "getReportMsg", "init", "connectStateCallback", "Lcom/pudutech/remotemaintenance/listener/ConnectStateCallback;", "sendMsg", NotificationCompat.CATEGORY_MESSAGE, "(Lcom/pudutech/remotemaintenance/bean/CIoTMsg;)V", "setMapSyncMsg", "setNetworkAvailable", "isNetworkAvailable", "", "setReportMsg", "startReport", "count", "", "stopReport", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface IoTInterface<T extends CIoTMsg> {
    void connect();

    void disconnect();

    T getMapSyncMsg();

    T getReportMsg();

    IoTInterface<T> init(ConnectStateCallback connectStateCallback);

    void sendMsg(T msg);

    void setMapSyncMsg(T msg);

    void setNetworkAvailable(boolean isNetworkAvailable);

    void setReportMsg(T msg);

    void startReport(int count);

    void stopReport();
}
