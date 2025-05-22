package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.peanut.robot_ui.C5508R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectLanguageAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0014J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectLanguageAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectLanguageItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "convert", "", "helper", "item", "getType", "", "code", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelectLanguageAdapter extends BaseQuickAdapter<SelectLanguageItem, BaseViewHolder> {
    private Context context;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectLanguageAdapter(Context context) {
        super(C5508R.layout.item_language_select_position);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, SelectLanguageItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = helper.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
        TextView textView = (TextView) helper.getView(C5508R.id.name_text);
        TextView tvType = (TextView) helper.getView(C5508R.id.tvType);
        RelativeLayout relativeLayout = (RelativeLayout) helper.getView(C5508R.id.rlBg);
        View icon = helper.getView(C5508R.id.select_ic);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(String.valueOf(item.getOp().getDisplayName()));
        Intrinsics.checkExpressionValueIsNotNull(tvType, "tvType");
        String locale = item.getOp().getLocale().toString();
        Intrinsics.checkExpressionValueIsNotNull(locale, "item.op.locale.toString()");
        tvType.setText(getType(locale));
        if (item.isSelect()) {
            relativeLayout.setBackgroundColor(this.context.getResources().getColor(C5508R.color.item_click));
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(8);
            relativeLayout.setBackgroundColor(this.context.getResources().getColor(C5508R.color.white));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    private final String getType(String code) {
        switch (code.hashCode()) {
            case 96646644:
                if (code.equals("en_US")) {
                    String string = this.context.getString(C5508R.string.langue_english);
                    Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.langue_english)");
                    return string;
                }
                String string2 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.langue_china)");
                return string2;
            case 96795103:
                if (code.equals("es_ES")) {
                    String string3 = this.context.getString(C5508R.string.langue_espan);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.langue_espan)");
                    return string3;
                }
                String string22 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string22, "context.getString(R.string.langue_china)");
                return string22;
            case 100876622:
                if (code.equals("ja_JP")) {
                    String string4 = this.context.getString(C5508R.string.langue_jpan);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.langue_jpan)");
                    return string4;
                }
                String string222 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string222, "context.getString(R.string.langue_china)");
                return string222;
            case 102217250:
                if (code.equals("ko_KR")) {
                    String string5 = this.context.getString(C5508R.string.langue_koren);
                    Intrinsics.checkExpressionValueIsNotNull(string5, "context.getString(R.string.langue_koren)");
                    return string5;
                }
                String string2222 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string2222, "context.getString(R.string.langue_china)");
                return string2222;
            case 115861276:
                if (code.equals("zh_CN")) {
                    String string6 = this.context.getString(C5508R.string.langue_china);
                    Intrinsics.checkExpressionValueIsNotNull(string6, "context.getString(R.string.langue_china)");
                    return string6;
                }
                String string22222 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string22222, "context.getString(R.string.langue_china)");
                return string22222;
            case 115861428:
                if (code.equals("zh_HK")) {
                    String string7 = this.context.getString(C5508R.string.langue_hk);
                    Intrinsics.checkExpressionValueIsNotNull(string7, "context.getString(R.string.langue_hk)");
                    return string7;
                }
                String string222222 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string222222, "context.getString(R.string.langue_china)");
                return string222222;
            default:
                String string2222222 = this.context.getString(C5508R.string.langue_china);
                Intrinsics.checkExpressionValueIsNotNull(string2222222, "context.getString(R.string.langue_china)");
                return string2222222;
        }
    }
}
