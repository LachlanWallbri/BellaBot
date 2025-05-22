package com.pudutech.mirsdk.activity;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
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
public final class MirSDKActivity$onCreate$2 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onCreate$2(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        List<String> cruiseIDs = MoveAction.INSTANCE.getCruiseIDs();
        if (!cruiseIDs.isEmpty()) {
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "showing dialog select size:" + cruiseIDs.size());
            AlertDialog.Builder builder = new AlertDialog.Builder(this.this$0);
            Object[] array = cruiseIDs.toArray(new String[0]);
            if (array != null) {
                AlertDialog create = builder.setSingleChoiceItems((CharSequence[]) array, -1, new MirSDKActivity$onCreate$2$dialog$1(this, cruiseIDs)).create();
                Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…               }.create()");
                create.show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Toast.makeText(this.this$0, "no cruise path", 0).show();
    }
}
