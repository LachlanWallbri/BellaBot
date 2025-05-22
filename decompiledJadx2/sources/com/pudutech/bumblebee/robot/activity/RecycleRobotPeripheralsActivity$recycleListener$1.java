package com.pudutech.bumblebee.robot.activity;

import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RecycleRobotPeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/RecycleRobotPeripheralsActivity$recycleListener$1", "Lcom/pudutech/bumblebee/robot/aidl/IRecycleRobotListener$Stub;", "cnt", "", "getCnt", "()I", "setCnt", "(I)V", "onNFCSignDetected", "", "id", "", "onRemoteDeviceMsg", NotificationCompat.CATEGORY_MESSAGE, "", "onRemoteDeviceResponseChecking", "snr", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RecycleRobotPeripheralsActivity$recycleListener$1 extends IRecycleRobotListener.Stub {
    private int cnt;
    final /* synthetic */ RecycleRobotPeripheralsActivity this$0;

    @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
    public void onRemoteDeviceMsg(byte[] msg) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecycleRobotPeripheralsActivity$recycleListener$1(RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity) {
        this.this$0 = recycleRobotPeripheralsActivity;
    }

    public final int getCnt() {
        return this.cnt;
    }

    public final void setCnt(int i) {
        this.cnt = i;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
    public void onNFCSignDetected(final String id) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onNFCSignDetected id=" + id);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$recycleListener$1$onNFCSignDetected$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView tvNFCRecord = (TextView) RecycleRobotPeripheralsActivity$recycleListener$1.this.this$0._$_findCachedViewById(C4144R.id.tvNFCRecord);
                Intrinsics.checkExpressionValueIsNotNull(tvNFCRecord, "tvNFCRecord");
                StringBuilder sb = new StringBuilder();
                sb.append("刷卡记录\n");
                sb.append(new Date());
                sb.append('\n');
                sb.append(id);
                sb.append('\n');
                RecycleRobotPeripheralsActivity$recycleListener$1 recycleRobotPeripheralsActivity$recycleListener$1 = RecycleRobotPeripheralsActivity$recycleListener$1.this;
                int cnt = recycleRobotPeripheralsActivity$recycleListener$1.getCnt();
                recycleRobotPeripheralsActivity$recycleListener$1.setCnt(cnt + 1);
                sb.append(cnt);
                tvNFCRecord.setText(sb.toString());
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
    public void onRemoteDeviceResponseChecking(final int snr) {
        String str;
        int i;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onLoraResponseChecking snr=" + snr);
        RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity = this.this$0;
        i = recycleRobotPeripheralsActivity.receiveCnt;
        recycleRobotPeripheralsActivity.receiveCnt = i + 1;
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$recycleListener$1$onRemoteDeviceResponseChecking$1
            @Override // java.lang.Runnable
            public final void run() {
                int i2;
                TextView tvLoraReceive = (TextView) RecycleRobotPeripheralsActivity$recycleListener$1.this.this$0._$_findCachedViewById(C4144R.id.tvLoraReceive);
                Intrinsics.checkExpressionValueIsNotNull(tvLoraReceive, "tvLoraReceive");
                tvLoraReceive.setText("应答接收\n" + new Date() + "\n snr=" + (snr / 10.0d));
                TextView tvReceiveCnt = (TextView) RecycleRobotPeripheralsActivity$recycleListener$1.this.this$0._$_findCachedViewById(C4144R.id.tvReceiveCnt);
                Intrinsics.checkExpressionValueIsNotNull(tvReceiveCnt, "tvReceiveCnt");
                StringBuilder sb = new StringBuilder();
                sb.append("接收计数: ");
                i2 = RecycleRobotPeripheralsActivity$recycleListener$1.this.this$0.receiveCnt;
                sb.append(i2);
                tvReceiveCnt.setText(sb.toString());
            }
        });
    }
}
