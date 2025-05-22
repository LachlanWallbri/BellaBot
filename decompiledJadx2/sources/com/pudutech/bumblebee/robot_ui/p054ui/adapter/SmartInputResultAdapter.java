package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationModel;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.CustomViewPropertiesKt;

/* compiled from: SmartInputResultAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0002H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001a¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SmartInputResultAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/DestinationModel;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "itemLayout", "", "(Landroid/content/Context;I)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "isBirthday", "", "()Z", "setBirthday", "(Z)V", "tvMaxW", "getTvMaxW", "()I", "tvMaxW$delegate", "Lkotlin/Lazy;", "tvSize", "", "getTvSize", "()F", "tvSize$delegate", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SmartInputResultAdapter extends BaseQuickAdapter<DestinationModel, BaseViewHolder> {
    private Context context;
    private boolean isBirthday;

    /* renamed from: tvMaxW$delegate, reason: from kotlin metadata */
    private final Lazy tvMaxW;

    /* renamed from: tvSize$delegate, reason: from kotlin metadata */
    private final Lazy tvSize;

    private final int getTvMaxW() {
        return ((Number) this.tvMaxW.getValue()).intValue();
    }

    private final float getTvSize() {
        return ((Number) this.tvSize.getValue()).floatValue();
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartInputResultAdapter(Context context, int i) {
        super(i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.tvSize = LazyKt.lazy(new Function0<Float>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SmartInputResultAdapter$tvSize$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Float invoke() {
                return Float.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final float invoke2() {
                return SmartInputResultAdapter.this.getContext().getResources().getDimension(C4188R.dimen.item_smart_input_result_tv_s);
            }
        });
        this.tvMaxW = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SmartInputResultAdapter$tvMaxW$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                return (int) SmartInputResultAdapter.this.getContext().getResources().getDimension(C4188R.dimen.item_smart_input_result_w);
            }
        });
    }

    /* renamed from: isBirthday, reason: from getter */
    public final boolean getIsBirthday() {
        return this.isBirthday;
    }

    public final void setBirthday(boolean z) {
        this.isBirthday = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, DestinationModel item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView view = (TextView) helper.getView(C4188R.id.content_tv);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        view.setTextSize(45.0f);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(item.getContent());
        if (this.isBirthday) {
            view.setBackgroundResource(C4188R.drawable.shape_radius_8_61666b);
            CustomViewPropertiesKt.setTextColorResource(view, C4188R.color.white);
        } else {
            view.setBackgroundResource(C4188R.drawable.shape_radius_8_cdd1d5);
            CustomViewPropertiesKt.setTextColorResource(view, C4188R.color.font_color_1);
        }
        Iterator<T> it = item.getInputtedChars().iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.context.getColor(C4188R.color.theme_main_color)), ((Number) pair.getFirst()).intValue(), ((Number) pair.getSecond()).intValue(), 34);
        }
        view.setText(spannableStringBuilder);
    }
}
