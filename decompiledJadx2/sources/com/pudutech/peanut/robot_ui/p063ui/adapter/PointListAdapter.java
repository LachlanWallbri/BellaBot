package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PointListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002'(B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001e\u001a\u00020\fH\u0002J\b\u0010\u001f\u001a\u00020\fH\u0016J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\fH\u0016J\u0018\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\fH\u0016J\u0018\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\fH\u0016R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fRL\u0010\u0010\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006)"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/PointListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "data", "", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapItemInfo;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "isTopOrBottomOrCenter", "", "()I", "setTopOrBottomOrCenter", "(I)V", "onItemSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "mapItemInfo", "", "isChecked", "", "getOnItemSelected", "()Lkotlin/jvm/functions/Function2;", "setOnItemSelected", "(Lkotlin/jvm/functions/Function2;)V", "getCurrentStyle", RequestParameters.POSITION, "itemViewType", "getItemCount", "getItemViewType", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MapPointItemViewHolder", "MapPointTitleViewHolder", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PointListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MapItemInfo> data;
    private int isTopOrBottomOrCenter = -1;
    private Function2<? super MapItemInfo, ? super Boolean, Unit> onItemSelected;

    public final List<MapItemInfo> getData() {
        return this.data;
    }

    public final void setData(List<MapItemInfo> list) {
        this.data = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (viewType == 3) {
            View inflate = from.inflate(C5508R.layout.item_map_setting_point_title, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(R…int_title, parent, false)");
            return new MapPointTitleViewHolder(inflate);
        }
        View inflate2 = from.inflate(C5508R.layout.item_map_setting_point_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "layoutInflater.inflate(R…oint_item, parent, false)");
        return new MapPointItemViewHolder(inflate2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        List<MapItemInfo> list = this.data;
        if (list == null || list.isEmpty()) {
            return super.getItemViewType(position);
        }
        List<MapItemInfo> list2 = this.data;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        return list2.get(position).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MapItemInfo> list = this.data;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* renamed from: isTopOrBottomOrCenter, reason: from getter */
    public final int getIsTopOrBottomOrCenter() {
        return this.isTopOrBottomOrCenter;
    }

    public final void setTopOrBottomOrCenter(int i) {
        this.isTopOrBottomOrCenter = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        List<MapItemInfo> list = this.data;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<MapItemInfo> list2 = this.data;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        final MapItemInfo mapItemInfo = list2.get(position);
        if (holder instanceof MapPointTitleViewHolder) {
            ((MapPointTitleViewHolder) holder).bindData(mapItemInfo, position);
            return;
        }
        if (holder instanceof MapPointItemViewHolder) {
            List<MapItemInfo> list3 = this.data;
            if (list3 == null) {
                Intrinsics.throwNpe();
            }
            this.isTopOrBottomOrCenter = getCurrentStyle(position, list3, getItemViewType(position));
            final MapPointItemViewHolder mapPointItemViewHolder = (MapPointItemViewHolder) holder;
            TextView tv_point_name = mapPointItemViewHolder.getTv_point_name();
            Intrinsics.checkExpressionValueIsNotNull(tv_point_name, "tv_point_name");
            String name = mapItemInfo.getName();
            if (name == null) {
                name = "暂无名称";
            }
            tv_point_name.setText(name);
            CheckBox cb_map_point = mapPointItemViewHolder.getCb_map_point();
            Intrinsics.checkExpressionValueIsNotNull(cb_map_point, "cb_map_point");
            cb_map_point.setChecked(mapItemInfo.isCheck());
            final View view = mapPointItemViewHolder.itemView;
            final long j = 800;
            view.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.PointListAdapter$onBindViewHolder$$inlined$apply$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - ViewExtKt.getLastClickTime(view) > j || (view instanceof Checkable)) {
                        ViewExtKt.setLastClickTime(view, currentTimeMillis);
                        View view3 = view;
                        Pdlog.m3274e("adapter", this.getOnItemSelected());
                        Function2<MapItemInfo, Boolean, Unit> onItemSelected = this.getOnItemSelected();
                        if (onItemSelected != null) {
                            MapItemInfo mapItemInfo2 = mapItemInfo;
                            CheckBox cb_map_point2 = mapPointItemViewHolder.getCb_map_point();
                            Intrinsics.checkExpressionValueIsNotNull(cb_map_point2, "cb_map_point");
                            onItemSelected.invoke(mapItemInfo2, Boolean.valueOf(true ^ cb_map_point2.isChecked()));
                        }
                        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    }
                }
            });
            int i = this.isTopOrBottomOrCenter;
            if (i == -1) {
                mapPointItemViewHolder.itemView.setBackgroundResource(C5508R.drawable.shape_only_top_radius);
                return;
            }
            if (i == 1) {
                mapPointItemViewHolder.itemView.setBackgroundResource(C5508R.drawable.shape_only_bottom_radius);
            } else if (i == 2) {
                mapPointItemViewHolder.itemView.setBackgroundResource(C5508R.drawable.shape_radius_8_white);
            } else {
                mapPointItemViewHolder.itemView.setBackgroundResource(C5508R.color.white);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int getCurrentStyle(int position, List<MapItemInfo> data, int itemViewType) {
        boolean z;
        boolean z2 = getItemViewType(position + (-1)) != itemViewType;
        int i = position + 1;
        if (i != data.size()) {
            if (data == null) {
                Intrinsics.throwNpe();
            }
            if (i >= data.size() || getItemViewType(i) == itemViewType) {
                z = false;
                if (!z2 && z) {
                    return 2;
                }
                if (z2) {
                    return z ? 1 : 0;
                }
                return -1;
            }
        }
        z = true;
        if (!z2) {
        }
        if (z2) {
        }
    }

    /* compiled from: PointListAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/PointListAdapter$MapPointTitleViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "tv_map_point_title", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getTv_map_point_title", "()Landroid/widget/TextView;", "bindData", "", "data", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapItemInfo;", RequestParameters.POSITION, "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class MapPointTitleViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_map_point_title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapPointTitleViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.tv_map_point_title = (TextView) view.findViewById(C5508R.id.tv_map_point_title);
        }

        public final TextView getTv_map_point_title() {
            return this.tv_map_point_title;
        }

        public final void bindData(MapItemInfo data, int position) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            TextView tv_map_point_title = this.tv_map_point_title;
            Intrinsics.checkExpressionValueIsNotNull(tv_map_point_title, "tv_map_point_title");
            tv_map_point_title.setText(data.getName());
        }
    }

    /* compiled from: PointListAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JX\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u001328\u0010\u0015\u001a4\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0016R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/PointListAdapter$MapPointItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "cb_map_point", "Landroid/widget/CheckBox;", "kotlin.jvm.PlatformType", "getCb_map_point", "()Landroid/widget/CheckBox;", "tv_point_name", "Landroid/widget/TextView;", "getTv_point_name", "()Landroid/widget/TextView;", "bindData", "", "data", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapItemInfo;", RequestParameters.POSITION, "", "isTopOrBottomOrCenter", "onItemSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "mapItemInfo", "", "isChecked", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class MapPointItemViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cb_map_point;
        private final TextView tv_point_name;

        public final void bindData(MapItemInfo data, int position, int isTopOrBottomOrCenter, Function2<? super MapItemInfo, ? super Boolean, Unit> onItemSelected) {
            Intrinsics.checkParameterIsNotNull(data, "data");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapPointItemViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.tv_point_name = (TextView) view.findViewById(C5508R.id.tv_point_name);
            this.cb_map_point = (CheckBox) view.findViewById(C5508R.id.cb_map_point);
        }

        public final TextView getTv_point_name() {
            return this.tv_point_name;
        }

        public final CheckBox getCb_map_point() {
            return this.cb_map_point;
        }
    }

    public final Function2<MapItemInfo, Boolean, Unit> getOnItemSelected() {
        return this.onItemSelected;
    }

    public final void setOnItemSelected(Function2<? super MapItemInfo, ? super Boolean, Unit> function2) {
        this.onItemSelected = function2;
    }
}
