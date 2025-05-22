package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.view.View;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleTaskItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.RecycleTaskDialog;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: RecycleTaskActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class RecycleTaskActivity$initView$4 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ RecycleTaskActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecycleTaskActivity$initView$4(RecycleTaskActivity recycleTaskActivity) {
        super(1);
        this.this$0 = recycleTaskActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(View view) {
        invoke2(view);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(View it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        RecycleTaskDialog recycleTaskDialog = new RecycleTaskDialog(this.this$0);
        recycleTaskDialog.setHistory(false);
        recycleTaskDialog.setOnDelete(new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$initView$4$$special$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                RecycleTaskActivity.access$getTaskAdapter$p(RecycleTaskActivity$initView$4.this.this$0).delete(i);
            }
        });
        List<RecycleTaskItem> data = RecycleTaskActivity.access$getTaskAdapter$p(this.this$0).getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "taskAdapter.data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (((RecycleTaskItem) obj).getDestination().length() > 0) {
                arrayList.add(obj);
            }
        }
        recycleTaskDialog.setTasks(arrayList);
        recycleTaskDialog.show();
    }
}
