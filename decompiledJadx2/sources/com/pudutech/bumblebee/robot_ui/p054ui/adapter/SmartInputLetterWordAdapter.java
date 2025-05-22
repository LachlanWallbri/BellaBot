package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.delivery_task.input_method_task.WordModel;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: SmartInputLetterWordAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0002H\u0014J\u0014\u0010,\u001a\u00020\u0016*\u00020-2\u0006\u0010.\u001a\u00020\u0002H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R7\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR7\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010#\u001a\u0004\b&\u0010'¨\u0006/"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SmartInputLetterWordAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/WordModel;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "setContext", "isBirthday", "", "()Z", "setBirthday", "(Z)V", "onLetterSelect", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "word", "", "getOnLetterSelect", "()Lkotlin/jvm/functions/Function1;", "setOnLetterSelect", "(Lkotlin/jvm/functions/Function1;)V", "onUnSelectableClick", "getOnUnSelectableClick", "setOnUnSelectableClick", "tvMaxW", "", "getTvMaxW", "()I", "tvMaxW$delegate", "Lkotlin/Lazy;", "tvSize", "", "getTvSize", "()F", "tvSize$delegate", "convert", "helper", "item", "changeStyle", "Landroid/widget/TextView;", "wordModel", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SmartInputLetterWordAdapter extends BaseQuickAdapter<WordModel, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private boolean isBirthday;
    private Function1<? super String, Unit> onLetterSelect;
    private Function1<? super String, Unit> onUnSelectableClick;

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
    public SmartInputLetterWordAdapter(Context context) {
        super(C4188R.layout.item_smart_input_letter_word);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "SmartInputLetterWordAdapter";
        this.tvSize = LazyKt.lazy(new Function0<Float>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SmartInputLetterWordAdapter$tvSize$2
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
                return SmartInputLetterWordAdapter.this.getContext().getResources().getDimension(C4188R.dimen.item_smart_input_letter_tv_s);
            }
        });
        this.tvMaxW = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SmartInputLetterWordAdapter$tvMaxW$2
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
                return (int) SmartInputLetterWordAdapter.this.getContext().getResources().getDimension(C4188R.dimen.item_smart_input_letter_w);
            }
        });
    }

    public final Function1<String, Unit> getOnLetterSelect() {
        return this.onLetterSelect;
    }

    public final void setOnLetterSelect(Function1<? super String, Unit> function1) {
        this.onLetterSelect = function1;
    }

    public final Function1<String, Unit> getOnUnSelectableClick() {
        return this.onUnSelectableClick;
    }

    public final void setOnUnSelectableClick(Function1<? super String, Unit> function1) {
        this.onUnSelectableClick = function1;
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
    public void convert(BaseViewHolder helper, final WordModel item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Pdlog.m3276v(this.TAG, "convert helper=" + helper + "  item=" + item);
        TextView view = (TextView) helper.getView(C4188R.id.content_tv);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        view.setTextSize(getTvSize());
        view.setText(item.getContent());
        view.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.SmartInputLetterWordAdapter$convert$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = SmartInputLetterWordAdapter.this.TAG;
                Pdlog.m3273d(str, "click " + item.getContent());
                if (item.isSelectable()) {
                    Function1<String, Unit> onLetterSelect = SmartInputLetterWordAdapter.this.getOnLetterSelect();
                    if (onLetterSelect != null) {
                        onLetterSelect.invoke(item.getContent());
                        return;
                    }
                    return;
                }
                Function1<String, Unit> onUnSelectableClick = SmartInputLetterWordAdapter.this.getOnUnSelectableClick();
                if (onUnSelectableClick != null) {
                    onUnSelectableClick.invoke(item.getContent());
                }
            }
        }, 3, null));
        changeStyle(view, item);
    }

    private final void changeStyle(TextView textView, WordModel wordModel) {
        if (wordModel.isSelectable()) {
            if (this.isBirthday) {
                Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_birthday_bg);
                Context context = textView.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                textView.setTextColor(context.getColorStateList(C4188R.color.selector_smart_input_table_item_birthday_tv));
                return;
            }
            Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_letter_item_bg);
            Context context2 = textView.getContext();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            textView.setTextColor(context2.getColorStateList(C4188R.color.selector_smart_input_table_item_tv));
            return;
        }
        if (this.isBirthday) {
            Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_birthday_bg2);
            Context context3 = textView.getContext();
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            textView.setTextColor(context3.getColorStateList(C4188R.color.selector_smart_input_table_item_birthday_tv2));
            return;
        }
        Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_letter_item_bg_2);
        Context context4 = textView.getContext();
        if (context4 == null) {
            Intrinsics.throwNpe();
        }
        textView.setTextColor(context4.getColorStateList(C4188R.color.selector_smart_input_table_item_tv2));
    }
}
