package com.pudutech.bluetooth;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceNotFondException;", "Ljava/io/IOException;", NotificationCompat.CATEGORY_MESSAGE, "", "(Ljava/lang/String;)V", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeviceNotFondException extends IOException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceNotFondException(String msg) {
        super(msg);
        Intrinsics.checkParameterIsNotNull(msg, "msg");
    }
}
