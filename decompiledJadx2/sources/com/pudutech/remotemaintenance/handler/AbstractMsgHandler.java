package com.pudutech.remotemaintenance.handler;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.remotemaintenance.bean.CIoTMsg;
import com.pudutech.remotemaintenance.interf.IoTInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AbstractMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u000bJ\u001d\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000b¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/handler/AbstractMsgHandler;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/remotemaintenance/bean/CIoTMsg;", "IoT", "Lcom/pudutech/remotemaintenance/interf/IoTInterface;", "Lcom/pudutech/remotemaintenance/handler/IMsgHandler;", "()V", "action", "", NotificationCompat.CATEGORY_MESSAGE, "iotInterface", "(Lcom/pudutech/remotemaintenance/bean/CIoTMsg;Lcom/pudutech/remotemaintenance/interf/IoTInterface;)V", "execute", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class AbstractMsgHandler<T extends CIoTMsg, IoT extends IoTInterface<T>> implements IMsgHandler<T, IoT> {
    public abstract void action(T msg, IoT iotInterface);

    @Override // com.pudutech.remotemaintenance.handler.IMsgHandler
    public void execute(T msg, IoT iotInterface) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(iotInterface, "iotInterface");
        action(msg, iotInterface);
    }
}
