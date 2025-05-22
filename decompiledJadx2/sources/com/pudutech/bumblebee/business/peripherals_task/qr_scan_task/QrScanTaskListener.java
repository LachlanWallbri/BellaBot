package com.pudutech.bumblebee.business.peripherals_task.qr_scan_task;

import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.business.base.BaseListener;
import kotlin.Metadata;

/* compiled from: QrScanTaskListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTaskListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onQrScanEvent", "", "event", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface QrScanTaskListener extends BaseListener {
    void onQrScanEvent(QrScanEvent event, String msg);
}
