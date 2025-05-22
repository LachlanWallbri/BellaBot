package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectRecycleTaskAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\r\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectRecycleTaskAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "setContext", "onCloseClickListener", "com/pudutech/peanut/robot_ui/ui/adapter/SelectRecycleTaskAdapter$onCloseClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectRecycleTaskAdapter$onCloseClickListener$1;", "convert", "", "helper", "item", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelectRecycleTaskAdapter extends BaseQuickAdapter<TrayModel, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private final SelectRecycleTaskAdapter$onCloseClickListener$1 onCloseClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.pudutech.peanut.robot_ui.ui.adapter.SelectRecycleTaskAdapter$onCloseClickListener$1] */
    public SelectRecycleTaskAdapter(Context context) {
        super(C5508R.layout.item_home_recycle_task);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "SelectRecycleTaskAdapter";
        this.onCloseClickListener = new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.SelectRecycleTaskAdapter$onCloseClickListener$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.presenter.delivery_task.TrayModel");
                }
                TrayModel trayModel = (TrayModel) tag;
                SelectRecycleTaskAdapter.this.getData().remove(trayModel);
                str = SelectRecycleTaskAdapter.this.TAG;
                Pdlog.m3273d(str, "onCloseClickListener " + trayModel);
                SelectRecycleTaskAdapter.this.notifyDataSetChanged();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TrayModel item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C5508R.id.task_tv);
        ImageView closeIv = (ImageView) helper.getView(C5508R.id.close_iv);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(item.getAllDestinations().get(0).getDestination());
        Intrinsics.checkExpressionValueIsNotNull(closeIv, "closeIv");
        closeIv.setTag(item);
        closeIv.setOnClickListener(this.onCloseClickListener);
    }
}
