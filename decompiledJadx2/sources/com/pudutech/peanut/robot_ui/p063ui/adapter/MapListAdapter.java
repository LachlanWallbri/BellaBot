package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.view.MapView;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MapListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002!\"B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0019H\u0016R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nRL\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MapListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapListAdapter$MapItemViewHolder;", "()V", "data", "", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapItemInfo;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "onItemSelected", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "mapName", "", "isChecked", "", "getOnItemSelected", "()Lkotlin/jvm/functions/Function2;", "setOnItemSelected", "(Lkotlin/jvm/functions/Function2;)V", "getItemCount", "", "onBindViewHolder", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "MapItemViewHolder", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapListAdapter extends RecyclerView.Adapter<MapItemViewHolder> {
    public static final int MAP_SETTING_ITEM = 2;
    public static final int MAP_SETTING_ITEM_TITLE = 1;
    public static final int MAP_SETTING_POINT_ITEM = 4;
    public static final int MAP_SETTING_POINT_ITEM_TITLE = 3;
    private List<MapItemInfo> data;
    private Function2<? super String, ? super Boolean, Unit> onItemSelected;

    public final List<MapItemInfo> getData() {
        return this.data;
    }

    public final void setData(List<MapItemInfo> list) {
        this.data = list;
    }

    public final Function2<String, Boolean, Unit> getOnItemSelected() {
        return this.onItemSelected;
    }

    public final void setOnItemSelected(Function2<? super String, ? super Boolean, Unit> function2) {
        this.onItemSelected = function2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MapItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(C5508R.layout.item_map_setting_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(R…ting_item, parent, false)");
        return new MapItemViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MapItemInfo> list = this.data;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010f  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(final MapItemViewHolder holder, final int position) {
        String str;
        final MapViewData mapData;
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
        List<MapItemInfo> list3 = this.data;
        if (list3 == null) {
            Intrinsics.throwNpe();
        }
        if (list3.size() == 1) {
            holder.itemView.setBackgroundResource(C5508R.drawable.shape_radius_8_white);
        } else if (position == 0) {
            holder.itemView.setBackgroundResource(C5508R.drawable.shape_only_top_radius);
        } else {
            int i = position + 1;
            List<MapItemInfo> list4 = this.data;
            if (list4 == null) {
                Intrinsics.throwNpe();
            }
            if (i == list4.size()) {
                holder.itemView.setBackgroundResource(C5508R.drawable.shape_only_bottom_radius);
            } else {
                holder.itemView.setBackgroundResource(C5508R.color.white);
            }
        }
        TextView tvMapName = holder.getTvMapName();
        Intrinsics.checkExpressionValueIsNotNull(tvMapName, "tvMapName");
        if (!StringsKt.isBlank(mapItemInfo.getName())) {
            String name = mapItemInfo.getName();
            if (!(name == null || name.length() == 0)) {
                str = mapItemInfo.getName();
                tvMapName.setText(str);
                final ImageView iv_expand = holder.getIv_expand();
                final long j = 800;
                iv_expand.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$onBindViewHolder$$inlined$with$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - ViewExtKt.getLastClickTime(iv_expand) > j || (iv_expand instanceof Checkable)) {
                            ViewExtKt.setLastClickTime(iv_expand, currentTimeMillis);
                            if (!mapItemInfo.isExpand()) {
                                int i2 = position;
                                if (this.getData() == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (i2 != r3.size() - 1) {
                                    View view_split_bottom = holder.getView_split_bottom();
                                    Intrinsics.checkExpressionValueIsNotNull(view_split_bottom, "view_split_bottom");
                                    view_split_bottom.setVisibility(0);
                                }
                                View view_split_top = holder.getView_split_top();
                                Intrinsics.checkExpressionValueIsNotNull(view_split_top, "view_split_top");
                                view_split_top.setVisibility(0);
                                RelativeLayout map_layout = holder.getMap_layout();
                                Intrinsics.checkExpressionValueIsNotNull(map_layout, "map_layout");
                                map_layout.setVisibility(0);
                                final MapViewData mapData2 = mapItemInfo.getMapData();
                                if (mapData2 != null) {
                                    holder.getIv_map().post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$onBindViewHolder$$inlined$with$lambda$1.1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            holder.getIv_map().setMapData(MapViewData.this.getMaxX(), MapViewData.this.getMaxY(), MapViewData.this.getMinX(), MapViewData.this.getMinY(), MapViewData.this.getMap());
                                        }
                                    });
                                }
                            } else {
                                View view_split_bottom2 = holder.getView_split_bottom();
                                Intrinsics.checkExpressionValueIsNotNull(view_split_bottom2, "view_split_bottom");
                                view_split_bottom2.setVisibility(8);
                                View view_split_top2 = holder.getView_split_top();
                                Intrinsics.checkExpressionValueIsNotNull(view_split_top2, "view_split_top");
                                view_split_top2.setVisibility(8);
                                RelativeLayout map_layout2 = holder.getMap_layout();
                                Intrinsics.checkExpressionValueIsNotNull(map_layout2, "map_layout");
                                map_layout2.setVisibility(8);
                            }
                            mapItemInfo.setExpand(!r7.isExpand());
                            holder.getIv_expand().setImageResource(mapItemInfo.isExpand() ? C5508R.drawable.ic_map_open : C5508R.drawable.ic_map_close);
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                        }
                    }
                });
                if (!mapItemInfo.isExpand()) {
                    List<MapItemInfo> list5 = this.data;
                    if (list5 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (position != list5.size() - 1) {
                        View view_split_bottom = holder.getView_split_bottom();
                        Intrinsics.checkExpressionValueIsNotNull(view_split_bottom, "view_split_bottom");
                        view_split_bottom.setVisibility(0);
                    }
                    View view_split_top = holder.getView_split_top();
                    Intrinsics.checkExpressionValueIsNotNull(view_split_top, "view_split_top");
                    view_split_top.setVisibility(0);
                    RelativeLayout map_layout = holder.getMap_layout();
                    Intrinsics.checkExpressionValueIsNotNull(map_layout, "map_layout");
                    map_layout.setVisibility(0);
                    final MapViewData mapData2 = mapItemInfo.getMapData();
                    if (mapData2 != null) {
                        holder.getIv_map().post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$$special$$inlined$let$lambda$2
                            @Override // java.lang.Runnable
                            public final void run() {
                                holder.getIv_map().setMapData(MapViewData.this.getMaxX(), MapViewData.this.getMaxY(), MapViewData.this.getMinX(), MapViewData.this.getMinY(), MapViewData.this.getMap());
                            }
                        });
                    }
                } else {
                    View view_split_bottom2 = holder.getView_split_bottom();
                    Intrinsics.checkExpressionValueIsNotNull(view_split_bottom2, "view_split_bottom");
                    view_split_bottom2.setVisibility(8);
                    View view_split_top2 = holder.getView_split_top();
                    Intrinsics.checkExpressionValueIsNotNull(view_split_top2, "view_split_top");
                    view_split_top2.setVisibility(8);
                    RelativeLayout map_layout2 = holder.getMap_layout();
                    Intrinsics.checkExpressionValueIsNotNull(map_layout2, "map_layout");
                    map_layout2.setVisibility(8);
                }
                mapData = mapItemInfo.getMapData();
                if (mapData != null) {
                    holder.getIv_map().post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$$special$$inlined$let$lambda$3
                        @Override // java.lang.Runnable
                        public final void run() {
                            holder.getIv_map().setMapData(MapViewData.this.getMaxX(), MapViewData.this.getMaxY(), MapViewData.this.getMinX(), MapViewData.this.getMinY(), MapViewData.this.getMap());
                        }
                    });
                }
                holder.getIv_expand().setImageResource(!mapItemInfo.isExpand() ? C5508R.drawable.ic_map_open : C5508R.drawable.ic_map_close);
                CheckBox cb_map_setting = holder.getCb_map_setting();
                Intrinsics.checkExpressionValueIsNotNull(cb_map_setting, "cb_map_setting");
                cb_map_setting.setChecked(mapItemInfo.isCheck());
                final RelativeLayout rl_check_box = holder.getRl_check_box();
                final long j2 = 800;
                rl_check_box.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$onBindViewHolder$$inlined$with$lambda$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - ViewExtKt.getLastClickTime(rl_check_box) > j2 || (rl_check_box instanceof Checkable)) {
                            ViewExtKt.setLastClickTime(rl_check_box, currentTimeMillis);
                            Function2<String, Boolean, Unit> onItemSelected = this.getOnItemSelected();
                            if (onItemSelected != null) {
                                String name2 = mapItemInfo.getName();
                                CheckBox cb_map_setting2 = holder.getCb_map_setting();
                                Intrinsics.checkExpressionValueIsNotNull(cb_map_setting2, "cb_map_setting");
                                onItemSelected.invoke(name2, Boolean.valueOf(!cb_map_setting2.isChecked()));
                            }
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                        }
                    }
                });
            }
        }
        str = "暂无名称";
        tvMapName.setText(str);
        final View iv_expand2 = holder.getIv_expand();
        final long j3 = 800;
        iv_expand2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$onBindViewHolder$$inlined$with$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ViewExtKt.getLastClickTime(iv_expand2) > j3 || (iv_expand2 instanceof Checkable)) {
                    ViewExtKt.setLastClickTime(iv_expand2, currentTimeMillis);
                    if (!mapItemInfo.isExpand()) {
                        int i2 = position;
                        if (this.getData() == null) {
                            Intrinsics.throwNpe();
                        }
                        if (i2 != r3.size() - 1) {
                            View view_split_bottom3 = holder.getView_split_bottom();
                            Intrinsics.checkExpressionValueIsNotNull(view_split_bottom3, "view_split_bottom");
                            view_split_bottom3.setVisibility(0);
                        }
                        View view_split_top3 = holder.getView_split_top();
                        Intrinsics.checkExpressionValueIsNotNull(view_split_top3, "view_split_top");
                        view_split_top3.setVisibility(0);
                        RelativeLayout map_layout3 = holder.getMap_layout();
                        Intrinsics.checkExpressionValueIsNotNull(map_layout3, "map_layout");
                        map_layout3.setVisibility(0);
                        final MapViewData mapData22 = mapItemInfo.getMapData();
                        if (mapData22 != null) {
                            holder.getIv_map().post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$onBindViewHolder$$inlined$with$lambda$1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    holder.getIv_map().setMapData(MapViewData.this.getMaxX(), MapViewData.this.getMaxY(), MapViewData.this.getMinX(), MapViewData.this.getMinY(), MapViewData.this.getMap());
                                }
                            });
                        }
                    } else {
                        View view_split_bottom22 = holder.getView_split_bottom();
                        Intrinsics.checkExpressionValueIsNotNull(view_split_bottom22, "view_split_bottom");
                        view_split_bottom22.setVisibility(8);
                        View view_split_top22 = holder.getView_split_top();
                        Intrinsics.checkExpressionValueIsNotNull(view_split_top22, "view_split_top");
                        view_split_top22.setVisibility(8);
                        RelativeLayout map_layout22 = holder.getMap_layout();
                        Intrinsics.checkExpressionValueIsNotNull(map_layout22, "map_layout");
                        map_layout22.setVisibility(8);
                    }
                    mapItemInfo.setExpand(!r7.isExpand());
                    holder.getIv_expand().setImageResource(mapItemInfo.isExpand() ? C5508R.drawable.ic_map_open : C5508R.drawable.ic_map_close);
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            }
        });
        if (!mapItemInfo.isExpand()) {
        }
        mapData = mapItemInfo.getMapData();
        if (mapData != null) {
        }
        holder.getIv_expand().setImageResource(!mapItemInfo.isExpand() ? C5508R.drawable.ic_map_open : C5508R.drawable.ic_map_close);
        CheckBox cb_map_setting2 = holder.getCb_map_setting();
        Intrinsics.checkExpressionValueIsNotNull(cb_map_setting2, "cb_map_setting");
        cb_map_setting2.setChecked(mapItemInfo.isCheck());
        final View rl_check_box2 = holder.getRl_check_box();
        final long j22 = 800;
        rl_check_box2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MapListAdapter$onBindViewHolder$$inlined$with$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ViewExtKt.getLastClickTime(rl_check_box2) > j22 || (rl_check_box2 instanceof Checkable)) {
                    ViewExtKt.setLastClickTime(rl_check_box2, currentTimeMillis);
                    Function2<String, Boolean, Unit> onItemSelected = this.getOnItemSelected();
                    if (onItemSelected != null) {
                        String name2 = mapItemInfo.getName();
                        CheckBox cb_map_setting22 = holder.getCb_map_setting();
                        Intrinsics.checkExpressionValueIsNotNull(cb_map_setting22, "cb_map_setting");
                        onItemSelected.invoke(name2, Boolean.valueOf(!cb_map_setting22.isChecked()));
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            }
        });
    }

    /* compiled from: MapListAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n \u0007*\u0004\u0018\u00010\u000f0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n \u0007*\u0004\u0018\u00010\u00130\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00130\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0019\u0010\u0018\u001a\n \u0007*\u0004\u0018\u00010\u00190\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u001f\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001e¨\u0006!"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MapListAdapter$MapItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "cb_map_setting", "Landroid/widget/CheckBox;", "kotlin.jvm.PlatformType", "getCb_map_setting", "()Landroid/widget/CheckBox;", "iv_expand", "Landroid/widget/ImageView;", "getIv_expand", "()Landroid/widget/ImageView;", "iv_map", "Lcom/pudutech/peanut/robot_ui/ui/view/MapView;", "getIv_map", "()Lcom/pudutech/peanut/robot_ui/ui/view/MapView;", "map_layout", "Landroid/widget/RelativeLayout;", "getMap_layout", "()Landroid/widget/RelativeLayout;", "rl_check_box", "getRl_check_box", "tvMapName", "Landroid/widget/TextView;", "getTvMapName", "()Landroid/widget/TextView;", "view_split_bottom", "getView_split_bottom", "()Landroid/view/View;", "view_split_top", "getView_split_top", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class MapItemViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cb_map_setting;
        private final ImageView iv_expand;
        private final MapView iv_map;
        private final RelativeLayout map_layout;
        private final RelativeLayout rl_check_box;
        private final TextView tvMapName;
        private final View view_split_bottom;
        private final View view_split_top;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapItemViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.tvMapName = (TextView) view.findViewById(C5508R.id.tv_map_name);
            this.cb_map_setting = (CheckBox) view.findViewById(C5508R.id.cb_map_setting);
            this.iv_expand = (ImageView) view.findViewById(C5508R.id.iv_expand);
            this.iv_map = (MapView) view.findViewById(C5508R.id.iv_map);
            this.view_split_top = view.findViewById(C5508R.id.view_split_top);
            this.view_split_bottom = view.findViewById(C5508R.id.view_split_bottom);
            this.rl_check_box = (RelativeLayout) view.findViewById(C5508R.id.rl_check_box);
            this.map_layout = (RelativeLayout) view.findViewById(C5508R.id.map_layout);
        }

        public final TextView getTvMapName() {
            return this.tvMapName;
        }

        public final CheckBox getCb_map_setting() {
            return this.cb_map_setting;
        }

        public final ImageView getIv_expand() {
            return this.iv_expand;
        }

        public final MapView getIv_map() {
            return this.iv_map;
        }

        public final View getView_split_top() {
            return this.view_split_top;
        }

        public final View getView_split_bottom() {
            return this.view_split_bottom;
        }

        public final RelativeLayout getRl_check_box() {
            return this.rl_check_box;
        }

        public final RelativeLayout getMap_layout() {
            return this.map_layout;
        }
    }
}
