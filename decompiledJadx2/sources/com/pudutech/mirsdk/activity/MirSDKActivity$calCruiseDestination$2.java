package com.pudutech.mirsdk.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.mirsdk.activity.debug.CruisePathAdapter;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$calCruiseDestination$2 implements View.OnClickListener {
    final /* synthetic */ Ref.ObjectRef $adapter;
    final /* synthetic */ Ref.ObjectRef $dialog;
    final /* synthetic */ int $flag;
    final /* synthetic */ Ref.ObjectRef $targets;
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$calCruiseDestination$2(MirSDKActivity mirSDKActivity, Ref.ObjectRef objectRef, int i, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3) {
        this.this$0 = mirSDKActivity;
        this.$targets = objectRef;
        this.$flag = i;
        this.$adapter = objectRef2;
        this.$dialog = objectRef3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MoveActionInterface moveActionInterface;
        if (((List) this.$targets.element).size() != 2 && this.$flag == 1) {
            Toast.makeText(this.this$0, "一次只能选两个目标", 0).show();
            return;
        }
        if (this.$flag == 1) {
            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
            List<Vector3d> calCruiseRoute = sDKInterface != null ? sDKInterface.calCruiseRoute((String) ((List) this.$targets.element).get(0), (String) ((List) this.$targets.element).get(1)) : null;
            ArrayList arrayList = new ArrayList();
            if (calCruiseRoute != null) {
                for (Vector3d vector3d : calCruiseRoute) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('(');
                    sb.append(vector3d.getX());
                    sb.append(',');
                    sb.append(vector3d.getY());
                    sb.append(')');
                    arrayList.add(sb.toString());
                }
            }
            this.this$0.getCalCruisePaths().add(((String) ((List) this.$targets.element).get(0)) + "-->" + ((String) ((List) this.$targets.element).get(1)) + ':' + arrayList.toString());
            ((CruisePathAdapter) this.$adapter.element).notifyDataSetChanged();
            return;
        }
        if (((List) this.$targets.element).size() == 0) {
            Toast.makeText(this.this$0, "没有选择目标", 0).show();
            return;
        }
        TextView task_id = (TextView) this.this$0._$_findCachedViewById(C4946R.id.task_id);
        Intrinsics.checkExpressionValueIsNotNull(task_id, "task_id");
        task_id.setText("目标餐桌 " + ((List) this.$targets.element));
        SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface2 != null && (moveActionInterface = sDKInterface2.getMoveActionInterface()) != null) {
            moveActionInterface.goCruiseByFixedDestinations((List) this.$targets.element);
        }
        ((AlertDialog) this.$dialog.element).dismiss();
    }
}
