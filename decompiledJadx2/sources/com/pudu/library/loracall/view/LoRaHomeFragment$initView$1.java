package com.pudu.library.loracall.view;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.loracall.library.C3965R;
import com.pudu.loracall.library.databinding.LoraTableContentItemBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaHomeFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Landroidx/databinding/ViewDataBinding;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaHomeFragment$initView$1 extends Lambda implements Function1<ViewDataBinding, Unit> {
    final /* synthetic */ LoRaHomeFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaHomeFragment$initView$1(LoRaHomeFragment loRaHomeFragment) {
        super(1);
        this.this$0 = loRaHomeFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ViewDataBinding viewDataBinding) {
        invoke2(viewDataBinding);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ViewDataBinding it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        LoraTableContentItemBinding loraTableContentItemBinding = (LoraTableContentItemBinding) it;
        final TableMatchBean item = loraTableContentItemBinding.getItem();
        if (item == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(item, "itemBinding.item!!");
        loraTableContentItemBinding.itemLayoutView.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$initView$1.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogFragment hintPop;
                DialogFragment hintPop2;
                DialogFragment hintPop3;
                String value = LoRaHomeFragment$initView$1.this.this$0.getViewModel().getDevAdder().getValue();
                if (value == null || value.length() == 0) {
                    String string = LoRaHomeFragment$initView$1.this.this$0.getString(C3965R.string.lora_not_checked_id);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.lora_not_checked_id)");
                    KotlinUtilsKt.showToast(string);
                    return;
                }
                if (LoRaHomeFragment$initView$1.this.this$0.getViewModel().getDevExist()) {
                    LoRaHomeFragment loRaHomeFragment = LoRaHomeFragment$initView$1.this.this$0;
                    String string2 = LoRaHomeFragment$initView$1.this.this$0.getString(C3965R.string.lora_call_bound_table);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.lora_call_bound_table)");
                    hintPop3 = loRaHomeFragment.getHintPop(string2, new Function1<View, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment.initView.1.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                            invoke2(view2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(View it2) {
                            Intrinsics.checkParameterIsNotNull(it2, "it");
                            LoRaHomeFragment$initView$1.this.this$0.getViewModel().saveTable(item);
                        }
                    });
                    hintPop3.show(LoRaHomeFragment$initView$1.this.this$0.getChildFragmentManager(), (String) null);
                    return;
                }
                if (item.getDevAdder().length() > 0) {
                    LoRaHomeFragment loRaHomeFragment2 = LoRaHomeFragment$initView$1.this.this$0;
                    String string3 = LoRaHomeFragment$initView$1.this.this$0.getString(C3965R.string.lora_replace_table);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.lora_replace_table)");
                    hintPop2 = loRaHomeFragment2.getHintPop(string3, new Function1<View, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment.initView.1.1.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                            invoke2(view2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(View it2) {
                            Intrinsics.checkParameterIsNotNull(it2, "it");
                            LoRaHomeFragment$initView$1.this.this$0.getViewModel().saveTable(item);
                        }
                    });
                    hintPop2.show(LoRaHomeFragment$initView$1.this.this$0.getChildFragmentManager(), (String) null);
                    return;
                }
                LoRaHomeFragment loRaHomeFragment3 = LoRaHomeFragment$initView$1.this.this$0;
                String string4 = LoRaHomeFragment$initView$1.this.this$0.getString(C3965R.string.lora_bind_table);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.lora_bind_table)");
                hintPop = loRaHomeFragment3.getHintPop(string4, new Function1<View, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment.initView.1.1.3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                        invoke2(view2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View it2) {
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        LoRaHomeFragment$initView$1.this.this$0.getViewModel().saveTable(item);
                    }
                });
                hintPop.show(LoRaHomeFragment$initView$1.this.this$0.getChildFragmentManager(), (String) null);
            }
        });
    }
}
