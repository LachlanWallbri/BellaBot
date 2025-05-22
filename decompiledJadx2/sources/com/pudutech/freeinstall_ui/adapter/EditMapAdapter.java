package com.pudutech.freeinstall_ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EditMapAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003&'(B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001d\u001a\u00020\fH\u0002J\b\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0016J\u0018\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\fH\u0016J\u0018\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\fH\u0016R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fRL\u0010\u0010\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006)"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/EditMapAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "data", "", "Lcom/pudutech/freeinstall_ui/adapter/MapPointItem;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "isTopOrBottomOrCenter", "", "()I", "setTopOrBottomOrCenter", "(I)V", "onItemClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "item", "pos", "", "getOnItemClickListener", "()Lkotlin/jvm/functions/Function2;", "setOnItemClickListener", "(Lkotlin/jvm/functions/Function2;)V", "getCurrentStyle", RequestParameters.POSITION, "itemViewType", "getItemCount", "getItemViewType", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "MapItemViewHolder", "MapTitleViewHolder", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EditMapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int MAP_SETTING_ITEM = 2;
    public static final int MAP_SETTING_ITEM_TITLE = 1;
    private List<MapPointItem> data;
    private int isTopOrBottomOrCenter = -1;
    private Function2<? super MapPointItem, ? super Integer, Unit> onItemClickListener;

    public final List<MapPointItem> getData() {
        return this.data;
    }

    public final void setData(List<MapPointItem> list) {
        this.data = list;
    }

    public final Function2<MapPointItem, Integer, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function2<? super MapPointItem, ? super Integer, Unit> function2) {
        this.onItemClickListener = function2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        if (viewType == 1) {
            View inflate = from.inflate(C5362R.layout.item_map_edit_item_title, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(R…tem_title, parent, false)");
            return new MapTitleViewHolder(inflate);
        }
        View inflate2 = from.inflate(C5362R.layout.item_map_edit_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "layoutInflater.inflate(R…edit_item, parent, false)");
        return new MapItemViewHolder(inflate2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        List<MapPointItem> list = this.data;
        if (list == null || list.isEmpty()) {
            return super.getItemViewType(position);
        }
        List<MapPointItem> list2 = this.data;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        return list2.get(position).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MapPointItem> list = this.data;
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
        List<MapPointItem> list = this.data;
        if (list == null || list.isEmpty()) {
            return;
        }
        List<MapPointItem> list2 = this.data;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        MapPointItem mapPointItem = list2.get(position);
        if (holder instanceof MapTitleViewHolder) {
            ((MapTitleViewHolder) holder).bindData(mapPointItem, position);
            return;
        }
        if (holder instanceof MapItemViewHolder) {
            List<MapPointItem> list3 = this.data;
            if (list3 == null) {
                Intrinsics.throwNpe();
            }
            this.isTopOrBottomOrCenter = getCurrentStyle(position, list3, getItemViewType(position));
            ((MapItemViewHolder) holder).bindData(mapPointItem, position, this.isTopOrBottomOrCenter, this.onItemClickListener);
        }
    }

    private final int getCurrentStyle(int position, List<MapPointItem> data, int itemViewType) {
        if (getItemViewType(position - 1) != itemViewType) {
            return -1;
        }
        int i = position + 1;
        if (i == data.size()) {
            return 1;
        }
        if (data == null) {
            Intrinsics.throwNpe();
        }
        return (i >= data.size() || getItemViewType(i) == itemViewType) ? 0 : 1;
    }

    /* compiled from: EditMapAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/EditMapAdapter$MapTitleViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "iv_must_select", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getIv_must_select", "()Landroid/widget/ImageView;", "tv_point_title", "Landroid/widget/TextView;", "getTv_point_title", "()Landroid/widget/TextView;", "bindData", "", "data", "Lcom/pudutech/freeinstall_ui/adapter/MapPointItem;", RequestParameters.POSITION, "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class MapTitleViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_must_select;
        private final TextView tv_point_title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapTitleViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.tv_point_title = (TextView) view.findViewById(C5362R.id.tv_point_title);
            this.iv_must_select = (ImageView) view.findViewById(C5362R.id.iv_must_select);
        }

        public final TextView getTv_point_title() {
            return this.tv_point_title;
        }

        public final ImageView getIv_must_select() {
            return this.iv_must_select;
        }

        public final void bindData(MapPointItem data, int position) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            TextView tv_point_title = this.tv_point_title;
            Intrinsics.checkExpressionValueIsNotNull(tv_point_title, "tv_point_title");
            tv_point_title.setText(data.getName());
            if (data.getTitleMust()) {
                ImageView iv_must_select = this.iv_must_select;
                Intrinsics.checkExpressionValueIsNotNull(iv_must_select, "iv_must_select");
                iv_must_select.setVisibility(0);
            } else {
                ImageView iv_must_select2 = this.iv_must_select;
                Intrinsics.checkExpressionValueIsNotNull(iv_must_select2, "iv_must_select");
                iv_must_select2.setVisibility(8);
            }
        }
    }

    /* compiled from: EditMapAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JX\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001528\u0010\u0017\u001a4\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0018J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0015R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/EditMapAdapter$MapItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "iv_point_type", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getIv_point_type", "()Landroid/widget/ImageView;", "tv_point_state", "Landroid/widget/TextView;", "getTv_point_state", "()Landroid/widget/TextView;", "tv_point_type", "getTv_point_type", "bindData", "", "data", "Lcom/pudutech/freeinstall_ui/adapter/MapPointItem;", RequestParameters.POSITION, "", "isTopOrBottomOrCenter", "onItemClickListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "item", "pos", "setIcon", "pointType", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class MapItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_point_type;
        private final TextView tv_point_state;
        private final TextView tv_point_type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapItemViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.iv_point_type = (ImageView) view.findViewById(C5362R.id.iv_point_type);
            this.tv_point_type = (TextView) view.findViewById(C5362R.id.tv_point_type);
            this.tv_point_state = (TextView) view.findViewById(C5362R.id.tv_point_state);
        }

        public final ImageView getIv_point_type() {
            return this.iv_point_type;
        }

        public final TextView getTv_point_type() {
            return this.tv_point_type;
        }

        public final TextView getTv_point_state() {
            return this.tv_point_state;
        }

        public final void bindData(final MapPointItem data, final int position, int isTopOrBottomOrCenter, final Function2<? super MapPointItem, ? super Integer, Unit> onItemClickListener) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            TextView tv_point_type = this.tv_point_type;
            Intrinsics.checkExpressionValueIsNotNull(tv_point_type, "tv_point_type");
            tv_point_type.setText(data.getName());
            this.tv_point_state.setTextColor(Color.parseColor(data.isSetting() ? "#1cc33d" : "#8d8d8d"));
            if (data.getPointType() == 7) {
                TextView tv_point_state = this.tv_point_state;
                Intrinsics.checkExpressionValueIsNotNull(tv_point_state, "tv_point_state");
                tv_point_state.setText("");
            } else if (data.isSetting()) {
                TextView tv_point_state2 = this.tv_point_state;
                Intrinsics.checkExpressionValueIsNotNull(tv_point_state2, "tv_point_state");
                View itemView = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
                tv_point_state2.setText(itemView.getContext().getString(C5362R.string.has_set));
            } else {
                TextView tv_point_state3 = this.tv_point_state;
                Intrinsics.checkExpressionValueIsNotNull(tv_point_state3, "tv_point_state");
                View itemView2 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(itemView2, "itemView");
                tv_point_state3.setText(itemView2.getContext().getString(C5362R.string.un_set));
            }
            setIcon(data.getPointType());
            final View view = this.itemView;
            final long j = 800;
            view.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.EditMapAdapter$MapItemViewHolder$bindData$$inlined$singleClick$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - ExtandsKt.getLastClickTime(view) > j || (view instanceof Checkable)) {
                        ExtandsKt.setLastClickTime(view, currentTimeMillis);
                        View view3 = view;
                        Function2 function2 = onItemClickListener;
                        if (function2 != null) {
                        }
                    }
                }
            });
        }

        public final void setIcon(int pointType) {
            switch (pointType) {
                case 1:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_meal_point);
                    return;
                case 2:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_door_point);
                    return;
                case 3:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_table_point);
                    return;
                case 4:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_cruise_path);
                    return;
                case 5:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_station_point);
                    return;
                case 6:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_charge_dill);
                    return;
                case 7:
                    this.iv_point_type.setImageResource(C5362R.drawable.pic_expand_map);
                    return;
                default:
                    return;
            }
        }
    }
}
