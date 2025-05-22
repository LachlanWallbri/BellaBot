package com.pudutech.freeinstall_ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.disinfect.baselib.util.GlideUtils;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapEditSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0014R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR7\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0007\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapEditSelectAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/freeinstall_ui/adapter/MapEditSelectItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onItemAddClick", "Lkotlin/Function0;", "", "getOnItemAddClick", "()Lkotlin/jvm/functions/Function0;", "setOnItemAddClick", "(Lkotlin/jvm/functions/Function0;)V", "onItemEditClick", "Lkotlin/Function1;", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "Lkotlin/ParameterName;", "name", "mapInfo", "getOnItemEditClick", "()Lkotlin/jvm/functions/Function1;", "setOnItemEditClick", "(Lkotlin/jvm/functions/Function1;)V", "convert", "helper", "item", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapEditSelectAdapter extends BaseQuickAdapter<MapEditSelectItem, BaseViewHolder> {
    private Function0<Unit> onItemAddClick;
    private Function1<? super MapInfo, Unit> onItemEditClick;

    public MapEditSelectAdapter() {
        super(C5362R.layout.item_edit_select_map);
    }

    public final Function1<MapInfo, Unit> getOnItemEditClick() {
        return this.onItemEditClick;
    }

    public final void setOnItemEditClick(Function1<? super MapInfo, Unit> function1) {
        this.onItemEditClick = function1;
    }

    public final Function0<Unit> getOnItemAddClick() {
        return this.onItemAddClick;
    }

    public final void setOnItemAddClick(Function0<Unit> function0) {
        this.onItemAddClick = function0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final MapEditSelectItem item) {
        if (helper != null) {
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
                constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapEditSelectAdapter$convert$$inlined$let$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view5) {
                        Function0<Unit> onItemAddClick = MapEditSelectAdapter.this.getOnItemAddClick();
                        if (onItemAddClick != null) {
                            onItemAddClick.invoke();
                        }
                    }
                });
                return;
            }
            if (item == null || item.getMapInfo() == null) {
                return;
            }
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
            ImageView mapImage = (ImageView) helper.getView(C5362R.id.iv_map);
            ConstraintLayout constraintLayout2 = (ConstraintLayout) helper.getView(C5362R.id.ctl_map_edit);
            Intrinsics.checkExpressionValueIsNotNull(mapName, "mapName");
            MapInfo mapInfo = item.getMapInfo();
            if (mapInfo == null) {
                Intrinsics.throwNpe();
            }
            mapName.setText(mapInfo.getMapName());
            File mapFile = item.getMapFile();
            if (mapFile != null) {
                GlideUtils.Companion companion = GlideUtils.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(mapImage, "mapImage");
                companion.loadImageViewFormFile(mapFile, mapImage);
            }
            constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapEditSelectAdapter$convert$$inlined$let$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view9) {
                    Function1<MapInfo, Unit> onItemEditClick = MapEditSelectAdapter.this.getOnItemEditClick();
                    if (onItemEditClick != null) {
                        MapInfo mapInfo2 = item.getMapInfo();
                        if (mapInfo2 == null) {
                            Intrinsics.throwNpe();
                        }
                        onItemEditClick.invoke(mapInfo2);
                    }
                }
            });
        }
    }
}
