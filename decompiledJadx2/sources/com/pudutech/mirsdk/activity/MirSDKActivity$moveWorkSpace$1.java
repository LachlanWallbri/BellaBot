package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.function.C4946R;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$moveWorkSpace$1 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$moveWorkSpace$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        List list;
        String str;
        List list2;
        List list3;
        list = this.this$0.destinations;
        if (list != null) {
            str = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("showing dialog select size:");
            list2 = this.this$0.tableArray;
            sb.append(list2.size());
            Pdlog.m3275i(str, sb.toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(this.this$0);
            list3 = this.this$0.tableArray;
            Object[] array = list3.toArray(new String[0]);
            if (array != null) {
                AlertDialog create = builder.setSingleChoiceItems((CharSequence[]) array, -1, new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$1$dialog$1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        String str2;
                        List list4;
                        List list5;
                        MoveActionInterface moveActionInterface;
                        List list6;
                        str2 = MirSDKActivity$moveWorkSpace$1.this.this$0.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("select destination:");
                        list4 = MirSDKActivity$moveWorkSpace$1.this.this$0.tableArray;
                        sb2.append((String) list4.get(i));
                        Pdlog.m3275i(str2, sb2.toString());
                        dialogInterface.dismiss();
                        TextView task_id = (TextView) MirSDKActivity$moveWorkSpace$1.this.this$0._$_findCachedViewById(C4946R.id.task_id);
                        Intrinsics.checkExpressionValueIsNotNull(task_id, "task_id");
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("目标餐桌 ");
                        list5 = MirSDKActivity$moveWorkSpace$1.this.this$0.tableNameArray;
                        sb3.append((String) list5.get(i));
                        task_id.setText(sb3.toString());
                        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                        if (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) {
                            return;
                        }
                        list6 = MirSDKActivity$moveWorkSpace$1.this.this$0.tableNameArray;
                        moveActionInterface.goTo((String) list6.get(i), MoveTaskMode.Normal);
                    }
                }).create();
                Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…               }.create()");
                create.show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Toast.makeText(this.this$0, "destinations empty", 0).show();
    }
}
