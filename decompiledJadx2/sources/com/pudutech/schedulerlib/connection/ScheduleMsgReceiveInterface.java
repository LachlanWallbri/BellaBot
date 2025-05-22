package com.pudutech.schedulerlib.connection;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ScheduleMsgReceiveInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/ScheduleMsgReceiveInterface;", "", "decodeMsg", "", NotificationCompat.CATEGORY_MESSAGE, "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface ScheduleMsgReceiveInterface {
    void decodeMsg(byte[] msg);
}
