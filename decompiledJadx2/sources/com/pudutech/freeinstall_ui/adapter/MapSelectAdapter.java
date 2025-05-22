package com.pudutech.freeinstall_ui.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0014R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR7\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R7\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/freeinstall_ui/adapter/MapSelectItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onItemAddClick", "Lkotlin/Function0;", "", "getOnItemAddClick", "()Lkotlin/jvm/functions/Function0;", "setOnItemAddClick", "(Lkotlin/jvm/functions/Function0;)V", "onItemEditClick", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "mapSelectItem", "getOnItemEditClick", "()Lkotlin/jvm/functions/Function1;", "setOnItemEditClick", "(Lkotlin/jvm/functions/Function1;)V", "onItemSelectClick", "getOnItemSelectClick", "setOnItemSelectClick", "convert", "helper", "item", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapSelectAdapter extends BaseQuickAdapter<MapSelectItem, BaseViewHolder> {
    private Function0<Unit> onItemAddClick;
    private Function1<? super MapSelectItem, Unit> onItemEditClick;
    private Function1<? super MapSelectItem, Unit> onItemSelectClick;

    public MapSelectAdapter() {
        super(C5362R.layout.item_select_map);
    }

    public final Function1<MapSelectItem, Unit> getOnItemEditClick() {
        return this.onItemEditClick;
    }

    public final void setOnItemEditClick(Function1<? super MapSelectItem, Unit> function1) {
        this.onItemEditClick = function1;
    }

    public final Function1<MapSelectItem, Unit> getOnItemSelectClick() {
        return this.onItemSelectClick;
    }

    public final void setOnItemSelectClick(Function1<? super MapSelectItem, Unit> function1) {
        this.onItemSelectClick = function1;
    }

    public final Function0<Unit> getOnItemAddClick() {
        return this.onItemAddClick;
    }

    public final void setOnItemAddClick(Function0<Unit> function0) {
        this.onItemAddClick = function0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final MapSelectItem item) {
        if (item == null || helper == null) {
            return;
        }
        if (helper.getAdapterPosition() == 0) {
            ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(C5362R.id.ctl_map_add);
            View view = helper.getView(C5362R.id.iv_map);
            Intrinsics.checkExpressionValueIsNotNull(view, "it.getView<ImageView>(R.id.iv_map)");
            ((ImageView) view).setVisibility(8);
            View view2 = helper.getView(C5362R.id.ctl_map_select);
            Intrinsics.checkExpressionValueIsNotNull(view2, "it.getView<ConstraintLayout>(R.id.ctl_map_select)");
            ((ConstraintLayout) view2).setVisibility(8);
            View view3 = helper.getView(C5362R.id.ctl_map_edit);
            Intrinsics.checkExpressionValueIsNotNull(view3, "it.getView<ConstraintLayout>(R.id.ctl_map_edit)");
            ((ConstraintLayout) view3).setVisibility(8);
            View view4 = helper.getView(C5362R.id.ctl_map_add);
            Intrinsics.checkExpressionValueIsNotNull(view4, "it.getView<ConstraintLayout>(R.id.ctl_map_add)");
            ((ConstraintLayout) view4).setVisibility(0);
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapSelectAdapter$convert$$inlined$let$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view5) {
                    Function0<Unit> onItemAddClick = MapSelectAdapter.this.getOnItemAddClick();
                    if (onItemAddClick != null) {
                        onItemAddClick.invoke();
                    }
                }
            });
            return;
        }
        if (item.getMapInfo() != null) {
            View view5 = helper.getView(C5362R.id.ctl_map_add);
            Intrinsics.checkExpressionValueIsNotNull(view5, "it.getView<ConstraintLayout>(R.id.ctl_map_add)");
            ((ConstraintLayout) view5).setVisibility(8);
            View view6 = helper.getView(C5362R.id.iv_map);
            Intrinsics.checkExpressionValueIsNotNull(view6, "it.getView<ImageView>(R.id.iv_map)");
            ((ImageView) view6).setVisibility(0);
            View view7 = helper.getView(C5362R.id.ctl_map_select);
            Intrinsics.checkExpressionValueIsNotNull(view7, "it.getView<ConstraintLayout>(R.id.ctl_map_select)");
            ((ConstraintLayout) view7).setVisibility(0);
            View view8 = helper.getView(C5362R.id.ctl_map_edit);
            Intrinsics.checkExpressionValueIsNotNull(view8, "it.getView<ConstraintLayout>(R.id.ctl_map_edit)");
            ((ConstraintLayout) view8).setVisibility(0);
            TextView mapName = (TextView) helper.getView(C5362R.id.tv_map_name);
            ImageView imageView = (ImageView) helper.getView(C5362R.id.iv_map);
            ImageView imageView2 = (ImageView) helper.getView(C5362R.id.iv_select_map);
            ConstraintLayout constraintLayout2 = (ConstraintLayout) helper.getView(C5362R.id.ctl_map_edit);
            ConstraintLayout constraintLayout3 = (ConstraintLayout) helper.getView(C5362R.id.ctl_map_select);
            Intrinsics.checkExpressionValueIsNotNull(mapName, "mapName");
            mapName.setText(item.getMapInfo().getMapName());
            File mapFile = item.getMapFile();
            if (mapFile != null) {
                imageView.setImageURI(Uri.fromFile(mapFile));
            }
            if (item.isSelect()) {
                imageView2.setImageResource(C5362R.drawable.icon_map_select);
            } else {
                imageView2.setImageResource(C5362R.drawable.icon_map_unselect);
            }
            constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapSelectAdapter$convert$$inlined$let$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view9) {
                    Function1<MapSelectItem, Unit> onItemEditClick = MapSelectAdapter.this.getOnItemEditClick();
                    if (onItemEditClick != null) {
                        onItemEditClick.invoke(item);
                    }
                }
            });
            constraintLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapSelectAdapter$convert$$inlined$let$lambda$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view9) {
                    Function1<MapSelectItem, Unit> onItemSelectClick = MapSelectAdapter.this.getOnItemSelectClick();
                    if (onItemSelectClick != null) {
                        onItemSelectClick.invoke(item);
                    }
                }
            });
        }
    }
}
