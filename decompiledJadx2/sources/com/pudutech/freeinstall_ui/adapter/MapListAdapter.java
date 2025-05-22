package com.pudutech.freeinstall_ui.adapter;

import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.freeinstall_ui.utils.ExtandsKt;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: MapListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0014R7\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR7\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "onCopyListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "data", "", "getOnCopyListener", "()Lkotlin/jvm/functions/Function1;", "setOnCopyListener", "(Lkotlin/jvm/functions/Function1;)V", "onEditListener", "getOnEditListener", "setOnEditListener", "convert", "helper", "item", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MapListAdapter extends BaseQuickAdapter<MapInfo, BaseViewHolder> {
    private Function1<? super MapInfo, Unit> onCopyListener;
    private Function1<? super MapInfo, Unit> onEditListener;

    public MapListAdapter() {
        super(C5362R.layout.item_edit_map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, final MapInfo item) {
        if (item == null || helper == null) {
            return;
        }
        TextView textView = (TextView) helper.getView(C5362R.id.tv_map_name);
        TextView textView2 = (TextView) helper.getView(C5362R.id.tv_edit);
        TextView textView3 = (TextView) helper.getView(C5362R.id.tv_create_copy);
        if (textView != null) {
            textView.setText(item.getMapName());
        }
        final TextView textView4 = textView3;
        final long j = 800;
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapListAdapter$convert$$inlined$let$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView4) > j || (textView4 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView4, currentTimeMillis);
                    Function1<MapInfo, Unit> onCopyListener = this.getOnCopyListener();
                    if (onCopyListener != null) {
                        onCopyListener.invoke(item);
                    }
                }
            }
        });
        final TextView textView5 = textView2;
        final long j2 = 800;
        textView5.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.adapter.MapListAdapter$convert$$inlined$let$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(textView5) > j2 || (textView5 instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(textView5, currentTimeMillis);
                    Function1<MapInfo, Unit> onEditListener = this.getOnEditListener();
                    if (onEditListener != null) {
                        onEditListener.invoke(item);
                    }
                }
            }
        });
    }

    public final Function1<MapInfo, Unit> getOnCopyListener() {
        return this.onCopyListener;
    }

    public final void setOnCopyListener(Function1<? super MapInfo, Unit> function1) {
        this.onCopyListener = function1;
    }

    public final Function1<MapInfo, Unit> getOnEditListener() {
        return this.onEditListener;
    }

    public final void setOnEditListener(Function1<? super MapInfo, Unit> function1) {
        this.onEditListener = function1;
    }
}
