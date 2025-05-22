package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.business.ims.lora.Channel;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChannelSettingAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u001c\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR7\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/ChannelSettingAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "currentSelectPos", "", "getCurrentSelectPos", "()Ljava/lang/Integer;", "setCurrentSelectPos", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "onCheckedListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", RequestParameters.POSITION, "", "getOnCheckedListener", "()Lkotlin/jvm/functions/Function1;", "setOnCheckedListener", "(Lkotlin/jvm/functions/Function1;)V", "prevCheckedView", "Landroid/widget/CheckBox;", "convert", "holder", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ChannelSettingAdapter extends BaseQuickAdapter<Channel, BaseViewHolder> {
    private final Context context;
    private Integer currentSelectPos;
    private Function1<? super Integer, Unit> onCheckedListener;
    private CheckBox prevCheckedView;

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelSettingAdapter(Context context) {
        super(C4188R.layout.item_control_channel_info);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    public final Integer getCurrentSelectPos() {
        return this.currentSelectPos;
    }

    public final void setCurrentSelectPos(Integer num) {
        this.currentSelectPos = num;
    }

    public final Function1<Integer, Unit> getOnCheckedListener() {
        return this.onCheckedListener;
    }

    public final void setOnCheckedListener(Function1<? super Integer, Unit> function1) {
        this.onCheckedListener = function1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final Channel item) {
        if (holder != null) {
            TextView textView = (TextView) holder.getView(C4188R.id.tv_channel_name);
            if (textView != null) {
                textView.setText(String.valueOf(item != null ? Integer.valueOf(item.getChannelId()) : null));
            }
            final CheckBox checkBox = (CheckBox) holder.getView(C4188R.id.cb_channel);
            Integer num = this.currentSelectPos;
            if (num != null) {
                int intValue = num.intValue();
                Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkBox");
                checkBox.setChecked(intValue == holder.getLayoutPosition());
                if (intValue == holder.getLayoutPosition()) {
                    this.prevCheckedView = checkBox;
                }
            }
            holder.itemView.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.ChannelSettingAdapter$convert$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:9:0x0036, code lost:
                
                    r0 = r3.prevCheckedView;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2(View it) {
                    CheckBox checkBox2;
                    CheckBox checkBox3;
                    CheckBox checkBox4;
                    CheckBox checkBox5;
                    CheckBox checkBox6;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    CheckBox mCheckBox = (CheckBox) BaseViewHolder.this.itemView.findViewById(C4188R.id.cb_channel);
                    checkBox2 = this.prevCheckedView;
                    if (Intrinsics.areEqual(mCheckBox, checkBox2)) {
                        return;
                    }
                    checkBox3 = this.prevCheckedView;
                    if (checkBox3 != null) {
                        checkBox4 = this.prevCheckedView;
                        if ((!Intrinsics.areEqual(checkBox4, checkBox)) && checkBox5 != null) {
                            checkBox6 = this.prevCheckedView;
                            if ((checkBox6 != null ? Boolean.valueOf(checkBox6.isChecked()) : null) == null) {
                                Intrinsics.throwNpe();
                            }
                            checkBox5.setChecked(!r1.booleanValue());
                        }
                    }
                    Intrinsics.checkExpressionValueIsNotNull(mCheckBox, "mCheckBox");
                    mCheckBox.setChecked(!mCheckBox.isChecked());
                    Function1<Integer, Unit> onCheckedListener = this.getOnCheckedListener();
                    if (onCheckedListener != null) {
                        onCheckedListener.invoke(Integer.valueOf(BaseViewHolder.this.getLayoutPosition()));
                    }
                    this.prevCheckedView = mCheckBox;
                }
            }, 3, null));
        }
    }
}
