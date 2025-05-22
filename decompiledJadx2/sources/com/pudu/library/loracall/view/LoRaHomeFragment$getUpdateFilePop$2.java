package com.pudu.library.loracall.view;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.base.BaseAdapter;
import com.pudu.library.loracall.base.BasePop;
import com.pudu.library.loracall.utils.UpdateUtils;
import com.pudu.loracall.library.C3965R;
import com.pudu.loracall.library.databinding.LoraUpdatePopBinding;
import com.pudu.loracall.library.databinding.LoraUpdatePopItemBinding;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaHomeFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "Lcom/pudu/loracall/library/databinding/LoraUpdatePopBinding;", "pop", "Lcom/pudu/library/loracall/base/BasePop;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaHomeFragment$getUpdateFilePop$2 extends Lambda implements Function2<LoraUpdatePopBinding, BasePop<LoraUpdatePopBinding>, Unit> {
    final /* synthetic */ LoRaHomeFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaHomeFragment$getUpdateFilePop$2(LoRaHomeFragment loRaHomeFragment) {
        super(2);
        this.this$0 = loRaHomeFragment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoRaHomeFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Landroidx/databinding/ViewDataBinding;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateFilePop$2$1 */
    /* loaded from: classes4.dex */
    public static final class C39461 extends Lambda implements Function1<ViewDataBinding, Unit> {
        final /* synthetic */ BasePop $pop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C39461(BasePop basePop) {
            super(1);
            this.$pop = basePop;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ViewDataBinding viewDataBinding) {
            invoke2(viewDataBinding);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ViewDataBinding it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            LoraUpdatePopItemBinding loraUpdatePopItemBinding = (LoraUpdatePopItemBinding) it;
            final File item = loraUpdatePopItemBinding.getItem();
            if (item == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(item, "binding.item!!");
            TextView textView = loraUpdatePopItemBinding.nameView;
            Intrinsics.checkExpressionValueIsNotNull(textView, "binding.nameView");
            textView.setText(FilesKt.getNameWithoutExtension(item));
            loraUpdatePopItemBinding.installBut.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment.getUpdateFilePop.2.1.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoRaClient companion = LoRaClient.INSTANCE.getInstance();
                    String absolutePath = item.getAbsolutePath();
                    Intrinsics.checkExpressionValueIsNotNull(absolutePath, "item.absolutePath");
                    companion.startUpdate(absolutePath, new Function1<Boolean, Unit>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment.getUpdateFilePop.2.1.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z) {
                            DialogFragment updateProgressPop;
                            if (z) {
                                C39461.this.$pop.dismiss();
                                LoRaHomeFragment$getUpdateFilePop$2.this.this$0.getViewModel().getLoraProgress().postValue(0);
                                updateProgressPop = LoRaHomeFragment$getUpdateFilePop$2.this.this$0.getUpdateProgressPop();
                                updateProgressPop.show(LoRaHomeFragment$getUpdateFilePop$2.this.this$0.getChildFragmentManager(), "progressPop");
                                return;
                            }
                            String string = LoRaHomeFragment$getUpdateFilePop$2.this.this$0.getString(C3965R.string.lora_update_failed);
                            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.lora_update_failed)");
                            KotlinUtilsKt.showToast(string);
                        }
                    });
                }
            });
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(LoraUpdatePopBinding loraUpdatePopBinding, BasePop<LoraUpdatePopBinding> basePop) {
        invoke2(loraUpdatePopBinding, basePop);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(LoraUpdatePopBinding receiver, final BasePop<LoraUpdatePopBinding> pop) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(pop, "pop");
        BaseAdapter baseAdapter = new BaseAdapter(C3965R.layout.lora_update_pop_item, null, null, null, 14, null);
        RecyclerView recyclerView = receiver.recyclerView;
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        recyclerView.setAdapter(baseAdapter);
        baseAdapter.setBindHolder(new C39461(pop));
        baseAdapter.submitList(UpdateUtils.INSTANCE.getUpdateList());
        receiver.closeBut.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateFilePop$2.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BasePop.this.dismiss();
            }
        });
    }
}
