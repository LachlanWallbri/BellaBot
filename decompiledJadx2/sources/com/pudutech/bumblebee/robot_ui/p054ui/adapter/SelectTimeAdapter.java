package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectTimeAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0014J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J&\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectTimeAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectTimeItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "data", "", "(Ljava/util/List;)V", "selectItem", "convert", "", "helper", "item", "convertTimeString", "", "context", "Landroid/content/Context;", "timeMillis", "", "onBindViewHolder", "holder", RequestParameters.POSITION, "", "payloads", "", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectTimeAdapter extends BaseQuickAdapter<SelectTimeItem, BaseViewHolder> {
    private SelectTimeItem selectItem;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((BaseViewHolder) viewHolder, i, (List<Object>) list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectTimeAdapter(final List<SelectTimeItem> data) {
        super(C4188R.layout.item_time_select, data);
        Object obj;
        Intrinsics.checkParameterIsNotNull(data, "data");
        Iterator<T> it = data.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((SelectTimeItem) obj).getTimeMillis() == RobotSetting.INSTANCE.getAutoSleepDelay()) {
                    break;
                }
            }
        }
        this.selectItem = (SelectTimeItem) obj;
        SelectTimeItem selectTimeItem = this.selectItem;
        if (selectTimeItem != null) {
            selectTimeItem.setSelect(true);
        }
        setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SelectTimeAdapter.2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, int i) {
                SelectTimeItem selectTimeItem2 = (SelectTimeItem) data.get(i);
                if (selectTimeItem2.isSelect()) {
                    return;
                }
                SelectTimeItem selectTimeItem3 = SelectTimeAdapter.this.selectItem;
                if (selectTimeItem3 != null) {
                    selectTimeItem3.setSelect(false);
                }
                selectTimeItem2.setSelect(true);
                SelectTimeAdapter selectTimeAdapter = SelectTimeAdapter.this;
                selectTimeAdapter.notifyItemChanged(CollectionsKt.indexOf((List<? extends SelectTimeItem>) data, selectTimeAdapter.selectItem), 4);
                SelectTimeAdapter.this.notifyItemChanged(data.indexOf(selectTimeItem2), 4);
                SelectTimeAdapter.this.selectItem = selectTimeItem2;
                RobotSetting.INSTANCE.setAutoSleepDelay(selectTimeItem2.getTimeMillis());
                CoreDevices.INSTANCE.getPowerSaveTask().resetTimer();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, SelectTimeItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        int i = C4188R.id.time;
        View view = helper.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "helper.itemView");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "helper.itemView.context");
        helper.setText(i, convertTimeString(context, item.getTimeMillis()));
        helper.setGone(C4188R.id.f4801cb, item.isSelect());
    }

    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(payloads, "payloads");
        super.onBindViewHolder((SelectTimeAdapter) holder, position, payloads);
        if (payloads.contains(4)) {
            holder.setGone(C4188R.id.f4801cb, getData().get(position).isSelect());
        }
    }

    private final String convertTimeString(Context context, long timeMillis) {
        if (timeMillis == -1) {
            String string = context.getString(C4188R.string.never);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.never)");
            return string;
        }
        if (timeMillis < 60000) {
            return (timeMillis / 1000) + ' ' + context.getString(C4188R.string.unit_second);
        }
        return (timeMillis / 60000) + ' ' + context.getString(C4188R.string.unit_minute);
    }
}
