package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: PalletTtsTaskNameAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/PalletTtsTaskNameAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "selectedPalletTtsScheme", "getSelectedPalletTtsScheme", "()Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;", "setSelectedPalletTtsScheme", "(Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;)V", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PalletTtsTaskNameAdapter extends BaseQuickAdapter<PalletTtsScheme, BaseViewHolder> {
    private Context context;
    private PalletTtsScheme selectedPalletTtsScheme;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PalletTtsTaskNameAdapter(Context context) {
        super(C4188R.layout.item_pallet_tts_scheme);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final PalletTtsScheme getSelectedPalletTtsScheme() {
        return this.selectedPalletTtsScheme;
    }

    public final void setSelectedPalletTtsScheme(PalletTtsScheme palletTtsScheme) {
        this.selectedPalletTtsScheme = palletTtsScheme;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, PalletTtsScheme item) {
        String str;
        String replace;
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tv = (TextView) helper.getView(C4188R.id.title_scheme);
        View view = helper.getView(C4188R.id.indicator_scheme);
        Regex regex = new Regex("\r|\n+");
        Intrinsics.checkExpressionValueIsNotNull(tv, "tv");
        String taskName = item.getTaskName();
        if (taskName == null || (replace = regex.replace(taskName, " ")) == null) {
            str = null;
        } else {
            if (replace == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            str = StringsKt.trim((CharSequence) replace).toString();
        }
        tv.setText(str);
        if (!Intrinsics.areEqual(this.selectedPalletTtsScheme, item)) {
            Sdk27PropertiesKt.setTextColor(tv, this.context.getColor(C4188R.color.font_color_1));
            view.setBackgroundColor(this.context.getColor(C4188R.color.transparent));
        } else {
            Sdk27PropertiesKt.setTextColor(tv, this.context.getColor(C4188R.color.c_0072FF));
            view.setBackgroundColor(this.context.getColor(C4188R.color.c_0072FF));
        }
    }
}
