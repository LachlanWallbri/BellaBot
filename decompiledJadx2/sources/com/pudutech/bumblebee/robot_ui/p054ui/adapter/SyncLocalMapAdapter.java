package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.view.View;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MapSyncStatusView;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncLocalMapAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0014RN\u0010\u0005\u001a6\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SyncLocalMapAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SyncLocalMapData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "localMapOnClickListener", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", RequestParameters.POSITION, "mapData", "", "getLocalMapOnClickListener", "()Lkotlin/jvm/functions/Function2;", "setLocalMapOnClickListener", "(Lkotlin/jvm/functions/Function2;)V", "convert", "holder", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SyncLocalMapAdapter extends BaseQuickAdapter<SyncLocalMapData, BaseViewHolder> {
    private Function2<? super Integer, ? super SyncLocalMapData, Unit> localMapOnClickListener;

    public SyncLocalMapAdapter() {
        super(C4188R.layout.item_sync_local_map);
    }

    public final Function2<Integer, SyncLocalMapData, Unit> getLocalMapOnClickListener() {
        return this.localMapOnClickListener;
    }

    public final void setLocalMapOnClickListener(Function2<? super Integer, ? super SyncLocalMapData, Unit> function2) {
        this.localMapOnClickListener = function2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final SyncLocalMapData item) {
        if (holder != null) {
            View view = holder.getView(C4188R.id.tv_map_name);
            Intrinsics.checkExpressionValueIsNotNull(view, "getView<TextView>(R.id.tv_map_name)");
            ((TextView) view).setText(item != null ? item.getMapName() : null);
            MapSyncStatusView mapSyncStatusView = (MapSyncStatusView) holder.getView(C4188R.id.map_status);
            mapSyncStatusView.setStatus(item != null ? item.getMapStatus() : null);
            mapSyncStatusView.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SyncLocalMapAdapter$convert$$inlined$run$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function2<Integer, SyncLocalMapData, Unit> localMapOnClickListener = this.getLocalMapOnClickListener();
                    if (localMapOnClickListener != null) {
                        localMapOnClickListener.invoke(Integer.valueOf(BaseViewHolder.this.getLayoutPosition()), item);
                    }
                }
            }, 3, null));
        }
    }
}
