package com.pudutech.remotemaintenance.handler;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.remotemaintenance.bean.CIoTMsg;
import com.pudutech.remotemaintenance.interf.IoTInterface;
import kotlin.Metadata;

/* compiled from: IMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005J\u001d\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/IMsgHandler;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/remotemaintenance/bean/CIoTMsg;", "IoT", "Lcom/pudutech/remotemaintenance/interf/IoTInterface;", "", "execute", "", NotificationCompat.CATEGORY_MESSAGE, "iotInterface", "(Lcom/pudutech/remotemaintenance/bean/CIoTMsg;Lcom/pudutech/remotemaintenance/interf/IoTInterface;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface IMsgHandler<T extends CIoTMsg, IoT extends IoTInterface<T>> {
    void execute(T msg, IoT iotInterface);
}
