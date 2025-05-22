package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleArriveTaskAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleArriveTaskAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "getContext", "()Landroid/content/Context;", "setContext", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleArriveTaskAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private final String TAG;
    private Context context;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecycleArriveTaskAdapter(Context context) {
        super(C4188R.layout.item_recycle_arrive_task);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "RecycleArriveTaskAdapter";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, String item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C4188R.id.task_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(item);
    }
}
