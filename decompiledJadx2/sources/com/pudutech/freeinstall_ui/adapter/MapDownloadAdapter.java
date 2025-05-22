package com.pudutech.freeinstall_ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.disinfect.baselib.network.response.RobotMapResp;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapDownloadAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020%H\u0016J\u0018\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020%H\u0016R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fRc\u0010\u0010\u001aK\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bRL\u0010\u001c\u001a4\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006."}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapDownloadAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/freeinstall_ui/adapter/MapDownloadAdapter$MapViewHolder;", "()V", "data", "", "Lcom/pudutech/disinfect/baselib/network/response/RobotMapResp;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "isShowCheck", "", "()Z", "setShowCheck", "(Z)V", "onErrorRetryListener", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "url", "isCheckShow", "", "getOnErrorRetryListener", "()Lkotlin/jvm/functions/Function3;", "setOnErrorRetryListener", "(Lkotlin/jvm/functions/Function3;)V", "onItemCheckListener", "Lkotlin/Function2;", "id", "isChecked", "getOnItemCheckListener", "()Lkotlin/jvm/functions/Function2;", "setOnItemCheckListener", "(Lkotlin/jvm/functions/Function2;)V", "getItemCount", "", "onBindViewHolder", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MapViewHolder", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapDownloadAdapter extends RecyclerView.Adapter<MapViewHolder> {
    private List<RobotMapResp> data;
    private boolean isShowCheck;
    private Function3<? super String, ? super Boolean, ? super RobotMapResp, Unit> onErrorRetryListener;
    private Function2<? super String, ? super Boolean, Unit> onItemCheckListener;

    public final List<RobotMapResp> getData() {
        return this.data;
    }

    public final void setData(List<RobotMapResp> list) {
        this.data = list;
    }

    /* compiled from: MapDownloadAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapDownloadAdapter$MapViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "cb_map_setting", "Landroid/widget/CheckBox;", "getCb_map_setting", "()Landroid/widget/CheckBox;", "rl_check_box", "Landroid/widget/RelativeLayout;", "getRl_check_box", "()Landroid/widget/RelativeLayout;", "tvMapName", "Landroid/widget/TextView;", "getTvMapName", "()Landroid/widget/TextView;", "tv_state", "getTv_state", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class MapViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cb_map_setting;
        private final RelativeLayout rl_check_box;
        private final TextView tvMapName;
        private final TextView tv_state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            View findViewById = itemView.findViewById(C5362R.id.tv_map_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.findViewById(R.id.tv_map_name)");
            this.tvMapName = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(C5362R.id.tv_state);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "itemView.findViewById(R.id.tv_state)");
            this.tv_state = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(C5362R.id.rl_check_box);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "itemView.findViewById(R.id.rl_check_box)");
            this.rl_check_box = (RelativeLayout) findViewById3;
            View findViewById4 = itemView.findViewById(C5362R.id.cb_map_setting);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "itemView.findViewById(R.id.cb_map_setting)");
            this.cb_map_setting = (CheckBox) findViewById4;
        }

        public final TextView getTvMapName() {
            return this.tvMapName;
        }

        public final TextView getTv_state() {
            return this.tv_state;
        }

        public final RelativeLayout getRl_check_box() {
            return this.rl_check_box;
        }

        public final CheckBox getCb_map_setting() {
            return this.cb_map_setting;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(C5362R.layout.item_map_download, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…_download, parent, false)");
        return new MapViewHolder(inflate);
    }

    /* renamed from: isShowCheck, reason: from getter */
    public final boolean getIsShowCheck() {
        return this.isShowCheck;
    }

    public final void setShowCheck(boolean z) {
        this.isShowCheck = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MapViewHolder holder, final int position) {
        final RobotMapResp robotMapResp;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        List<RobotMapResp> list = this.data;
        if (list == null || (robotMapResp = list.get(position)) == null) {
            return;
        }
        Context context = holder.getTvMapName().getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "holder.tvMapName.context");
        Resources resources = context.getResources();
        if (this.isShowCheck) {
            holder.getRl_check_box().setVisibility(0);
            holder.getTv_state().setText(resources.getString(C5362R.string.edit));
            holder.getTv_state().setTextColor(resources.getColor(C5362R.color.color_1cc33d));
        } else {
            holder.getRl_check_box().setVisibility(8);
            String state = robotMapResp != null ? robotMapResp.getState() : null;
            if (state != null) {
                int hashCode = state.hashCode();
                if (hashCode != 48) {
                    if (hashCode == 49) {
                        if (state.equals("1")) {
                            holder.getTv_state().setText("已下载");
                            holder.getTv_state().setTextColor(resources.getColor(C5362R.color.color_61666B));
                        }
                    } else if (hashCode == 1444 && state.equals("-1")) {
                        holder.getTv_state().setText("重试");
                        holder.getTv_state().setTextColor(resources.getColor(C5362R.color.color_FB313B));
                    }
                } else if (state.equals("0")) {
                    if ((robotMapResp != null ? robotMapResp.getProgress() : null) != null) {
                        holder.getTv_state().setText(robotMapResp != null ? robotMapResp.getProgress() : null);
                        holder.getTv_state().setTextColor(resources.getColor(C5362R.color.color_1cc33d));
                    }
                }
            }
            holder.getTv_state().setText("");
        }
        holder.getTvMapName().setText(robotMapResp.getName());
        final TextView tv_state = holder.getTv_state();
        final long j = 800;
        tv_state.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapDownloadAdapter$onBindViewHolder$$inlined$let$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(tv_state) > j || (tv_state instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(tv_state, currentTimeMillis);
                    Function3<String, Boolean, RobotMapResp, Unit> onErrorRetryListener = this.getOnErrorRetryListener();
                    if (onErrorRetryListener != null) {
                        onErrorRetryListener.invoke(robotMapResp.getUrl(), Boolean.valueOf(this.getIsShowCheck()), robotMapResp);
                    }
                }
            }
        });
        holder.getCb_map_setting().setChecked(robotMapResp.isSetting());
        final RelativeLayout rl_check_box = holder.getRl_check_box();
        rl_check_box.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapDownloadAdapter$onBindViewHolder$$inlined$let$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(rl_check_box) > j || (rl_check_box instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(rl_check_box, currentTimeMillis);
                    holder.getCb_map_setting().setChecked(!holder.getCb_map_setting().isChecked());
                    Function2<String, Boolean, Unit> onItemCheckListener = this.getOnItemCheckListener();
                    if (onItemCheckListener != null) {
                        onItemCheckListener.invoke(robotMapResp.getName(), Boolean.valueOf(holder.getCb_map_setting().isChecked()));
                    }
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<RobotMapResp> list = this.data;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final Function3<String, Boolean, RobotMapResp, Unit> getOnErrorRetryListener() {
        return this.onErrorRetryListener;
    }

    public final void setOnErrorRetryListener(Function3<? super String, ? super Boolean, ? super RobotMapResp, Unit> function3) {
        this.onErrorRetryListener = function3;
    }

    public final Function2<String, Boolean, Unit> getOnItemCheckListener() {
        return this.onItemCheckListener;
    }

    public final void setOnItemCheckListener(Function2<? super String, ? super Boolean, Unit> function2) {
        this.onItemCheckListener = function2;
    }
}
