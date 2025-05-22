package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.LocateCameraCalibListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "i", "", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$onCreate$1$dialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ List $cruiseIDs;
    final /* synthetic */ MirSDKActivity$onCreate$1 this$0;

    MirSDKActivity$onCreate$1$dialog$1(MirSDKActivity$onCreate$1 mirSDKActivity$onCreate$1, List list) {
        this.this$0 = mirSDKActivity$onCreate$1;
        this.$cruiseIDs = list;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        String str;
        MoveActionInterface moveActionInterface;
        str = this.this$0.this$0.TAG;
        Pdlog.m3275i(str, "select cruise id:" + ((String) this.$cruiseIDs.get(i)));
        dialogInterface.dismiss();
        TextView task_id = (TextView) this.this$0.this$0._$_findCachedViewById(C4946R.id.task_id);
        Intrinsics.checkExpressionValueIsNotNull(task_id, "task_id");
        task_id.setText("巡航路径 " + i);
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
            moveActionInterface.goCruisePath(i, MoveTaskMode.Normal, null);
        }
        SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface2 != null) {
            sDKInterface2.calibrationMarkerCamera(i, new BinderC48541());
        }
    }

    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onCreate$1$dialog$1$1", "Lcom/pudutech/mirsdk/aidl/LocateCameraCalibListener$Stub;", "calibResult", "", "p0", "", "rpy", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "xyz", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$1$dialog$1$1 */
    /* loaded from: classes4.dex */
    public static final class BinderC48541 extends LocateCameraCalibListener.Stub {
        BinderC48541() {
        }

        @Override // com.pudutech.mirsdk.aidl.LocateCameraCalibListener
        public void calibResult(final boolean p0, final Vector3d rpy, final Vector3d xyz) {
            MoveActionInterface moveActionInterface;
            Intrinsics.checkParameterIsNotNull(rpy, "rpy");
            Intrinsics.checkParameterIsNotNull(xyz, "xyz");
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
                moveActionInterface.pause();
            }
            MirSDKActivity$onCreate$1$dialog$1.this.this$0.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$1$dialog$1$1$calibResult$1
                @Override // java.lang.Runnable
                public final void run() {
                    TextView tx_calib_state = (TextView) MirSDKActivity$onCreate$1$dialog$1.this.this$0.this$0._$_findCachedViewById(C4946R.id.tx_calib_state);
                    Intrinsics.checkExpressionValueIsNotNull(tx_calib_state, "tx_calib_state");
                    tx_calib_state.setText(p0 ? "Success" : "Fail");
                    TextView tx_calib_rpy = (TextView) MirSDKActivity$onCreate$1$dialog$1.this.this$0.this$0._$_findCachedViewById(C4946R.id.tx_calib_rpy);
                    Intrinsics.checkExpressionValueIsNotNull(tx_calib_rpy, "tx_calib_rpy");
                    tx_calib_rpy.setText('(' + rpy.getX() + ", " + rpy.getY() + ", " + rpy.getZ() + ')');
                    TextView tx_calib_xyz = (TextView) MirSDKActivity$onCreate$1$dialog$1.this.this$0.this$0._$_findCachedViewById(C4946R.id.tx_calib_xyz);
                    Intrinsics.checkExpressionValueIsNotNull(tx_calib_xyz, "tx_calib_xyz");
                    tx_calib_xyz.setText('(' + xyz.getX() + ", " + xyz.getY() + ", " + xyz.getZ() + ')');
                }
            });
        }
    }
}
