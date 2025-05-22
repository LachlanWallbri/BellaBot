package com.pudutech.remotemaintenance;

import com.pudutech.remotemaintenance.aliyun.AliyunIoTManager;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.interf.IoTInterface;
import kotlin.Metadata;

/* compiled from: IoTManagerFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/IoTManagerFactory;", "", "()V", "getIoTManager", "Lcom/pudutech/remotemaintenance/interf/IoTInterface;", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class IoTManagerFactory {
    public static final IoTManagerFactory INSTANCE = new IoTManagerFactory();

    private IoTManagerFactory() {
    }

    public final IoTInterface<AliyunIoTMsg> getIoTManager() {
        return AliyunIoTManager.INSTANCE.getINSTANCE();
    }
}
