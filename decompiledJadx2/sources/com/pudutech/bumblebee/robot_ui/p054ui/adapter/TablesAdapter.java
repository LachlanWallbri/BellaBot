package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TablesAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0016\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bJ\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", ES6Iterator.VALUE_PROPERTY, "", "isDarkTheme", "()Z", "setDarkTheme", "(Z)V", "onTouchListener", "Landroid/view/View$OnTouchListener;", "convert", "", "helper", "item", "getItemBg", "", "getTextColor", "setItemStatus", "v", "Landroid/view/View;", "isDown", "showItemClickAnimation", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TablesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    private boolean isDarkTheme;
    private final View.OnTouchListener onTouchListener;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TablesAdapter(Context context) {
        super(C4188R.layout.item_table_info);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.onTouchListener = new View.OnTouchListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.TablesAdapter$onTouchListener$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View v, MotionEvent event) {
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                int action = event.getAction();
                if (action == 0) {
                    TablesAdapter tablesAdapter = TablesAdapter.this;
                    Intrinsics.checkExpressionValueIsNotNull(v, "v");
                    tablesAdapter.setItemStatus(v, true);
                } else if (action == 1 || action == 3) {
                    TablesAdapter tablesAdapter2 = TablesAdapter.this;
                    Intrinsics.checkExpressionValueIsNotNull(v, "v");
                    tablesAdapter2.setItemStatus(v, false);
                }
                return false;
            }
        };
    }

    /* renamed from: isDarkTheme, reason: from getter */
    public final boolean getIsDarkTheme() {
        return this.isDarkTheme;
    }

    public final void setDarkTheme(boolean z) {
        this.isDarkTheme = z;
        notifyDataSetChanged();
    }

    public final void setItemStatus(View v, boolean isDown) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        if (isDown) {
            v.setScaleX(1.2f);
            v.setScaleY(1.2f);
            TextView textView = (TextView) v.findViewById(C4188R.id.tv_table_info);
            textView.setBackgroundResource(C4188R.drawable.shape_radius_8_blu);
            textView.setTextColor(-1);
            return;
        }
        v.setScaleX(1.0f);
        v.setScaleY(1.0f);
        TextView textView2 = (TextView) v.findViewById(C4188R.id.tv_table_info);
        textView2.setBackgroundResource(getItemBg());
        textView2.setTextColor(this.context.getColor(getTextColor()));
    }

    public final void showItemClickAnimation(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f);
        scaleAnimation.setDuration(100L);
        v.startAnimation(scaleAnimation);
    }

    private final int getItemBg() {
        return this.isDarkTheme ? C4188R.drawable.shape_radius_8_61666b : C4188R.drawable.shape_radius_8_cdd1d5;
    }

    private final int getTextColor() {
        return this.isDarkTheme ? C4188R.color.white : C4188R.color.font_color_1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, String item) {
        TextView textView = helper != null ? (TextView) helper.getView(C4188R.id.tv_table_info) : null;
        if (textView == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        textView.setBackgroundResource(getItemBg());
        float dimension = this.context.getResources().getDimension(C4188R.dimen.delivery_task_table_item_tv_s);
        float dimension2 = this.context.getResources().getDimension(C4188R.dimen.delivery_task_table_item_w);
        textView.setTextSize(0, dimension);
        textView.setTextColor(this.context.getColor(getTextColor()));
        textView.setText(item);
        UiUtils.adjustTvTextSize(textView, ((int) dimension2) - 10, item, 22);
        helper.itemView.setOnTouchListener(this.onTouchListener);
    }
}
