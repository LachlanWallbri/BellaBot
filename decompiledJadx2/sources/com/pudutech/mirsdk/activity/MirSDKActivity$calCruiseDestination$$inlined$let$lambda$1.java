package com.pudutech.mirsdk.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.pudutech.mirsdk.activity.debug.CruisePathAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/pudutech/mirsdk/activity/MirSDKActivity$calCruiseDestination$1$1$1", "com/pudutech/mirsdk/activity/MirSDKActivity$$special$$inlined$forEach$lambda$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$calCruiseDestination$$inlined$let$lambda$1 implements View.OnClickListener {
    final /* synthetic */ Ref.ObjectRef $adapter$inlined;
    final /* synthetic */ Ref.ObjectRef $button;
    final /* synthetic */ int $flag$inlined;
    final /* synthetic */ Ref.ObjectRef $flowLayout$inlined;
    final /* synthetic */ Ref.ObjectRef $targets$inlined;
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$calCruiseDestination$$inlined$let$lambda$1(Ref.ObjectRef objectRef, MirSDKActivity mirSDKActivity, int i, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Ref.ObjectRef objectRef4) {
        this.$button = objectRef;
        this.this$0 = mirSDKActivity;
        this.$flag$inlined = i;
        this.$targets$inlined = objectRef2;
        this.$adapter$inlined = objectRef3;
        this.$flowLayout$inlined = objectRef4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public final void onClick(View it) {
        if (this.$flag$inlined == 1) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (it.isSelected()) {
                ((List) this.$targets$inlined.element).remove(((Button) this.$button.element).getText().toString());
            } else {
                if (((List) this.$targets$inlined.element).size() >= 2) {
                    Toast.makeText(this.this$0, "已经选择了两个目标点了", 0).show();
                    return;
                }
                ((List) this.$targets$inlined.element).add(((Button) this.$button.element).getText().toString());
            }
            it.setSelected(!it.isSelected());
            return;
        }
        String obj = ((Button) this.$button.element).getText().toString();
        if (((List) this.$targets$inlined.element).size() > 0 && Intrinsics.areEqual((String) ((List) this.$targets$inlined.element).get(((List) this.$targets$inlined.element).size() - 1), obj)) {
            Toast.makeText(this.this$0, "起始点与终点不能相同", 0).show();
            return;
        }
        ((List) this.$targets$inlined.element).add(obj);
        StringBuilder sb = new StringBuilder();
        for (IndexedValue indexedValue : CollectionsKt.withIndex((List) this.$targets$inlined.element)) {
            if (indexedValue.getIndex() == ((List) this.$targets$inlined.element).size() - 1) {
                sb.append((String) indexedValue.getValue());
            } else {
                sb.append(((String) indexedValue.getValue()) + "-->");
            }
        }
        this.this$0.getTargetDestination().clear();
        List targetDestination = this.this$0.getTargetDestination();
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "path.toString()");
        targetDestination.add(sb2);
        sb.setLength(0);
        ((CruisePathAdapter) this.$adapter$inlined.element).notifyDataSetChanged();
    }
}
