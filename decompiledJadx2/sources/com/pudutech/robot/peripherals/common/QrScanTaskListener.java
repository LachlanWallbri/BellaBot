package com.pudutech.robot.peripherals.common;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* compiled from: QrCodeScanHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/QrScanTaskListener;", "", "onQrScanEvent", "", "event", "Lcom/pudutech/robot/peripherals/common/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface QrScanTaskListener {
    void onQrScanEvent(QrScanEvent event, String msg);
}
