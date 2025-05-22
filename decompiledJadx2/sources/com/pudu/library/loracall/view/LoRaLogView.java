package com.pudu.library.loracall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.base.BaseAdapter;
import com.pudu.loracall.library.C3965R;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaLogView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudu/library/loracall/view/LoRaLogView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "clearBut", "Landroid/widget/Button;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaLogView extends FrameLayout {
    private final Button clearBut;
    private final RecyclerView recyclerView;
    private final View view;

    public LoRaLogView(Context context) {
        this(context, null, 0, 6, null);
    }

    public LoRaLogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ LoRaLogView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LoRaLogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        View inflate = LayoutInflater.from(context).inflate(C3965R.layout.lora_log_layout, (ViewGroup) this, true);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont…a_log_layout, this, true)");
        this.view = inflate;
        View findViewById = this.view.findViewById(C3965R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.recyclerView)");
        this.recyclerView = (RecyclerView) findViewById;
        View findViewById2 = this.view.findViewById(C3965R.id.clearBut);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.clearBut)");
        this.clearBut = (Button) findViewById2;
        final BaseAdapter baseAdapter = new BaseAdapter(C3965R.layout.lora_log_item, null, null, null, 14, null);
        this.recyclerView.setAdapter(baseAdapter);
        this.recyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        final LinkedList linkedList = new LinkedList();
        baseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.pudu.library.loracall.view.LoRaLogView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int positionStart, int itemCount) {
                try {
                    if (LoRaLogView.this.getRecyclerView().canScrollVertically(1)) {
                        return;
                    }
                    LoRaLogView.this.getRecyclerView().scrollToPosition(baseAdapter.getItemCount() - 1);
                } catch (Exception e) {
                    KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.view.LoRaLogView$1$onItemRangeInserted$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return KotlinUtilsKt.stackTraceToString(e);
                        }
                    }, 1, null);
                }
            }
        });
        this.clearBut.setOnClickListener(new View.OnClickListener() { // from class: com.pudu.library.loracall.view.LoRaLogView.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                linkedList.clear();
                baseAdapter.submitList(CollectionsKt.toList(linkedList));
            }
        });
        if (context instanceof LifecycleOwner) {
            KotlinUtilsKt.getLogList().observe((LifecycleOwner) context, new Observer<String>() { // from class: com.pudu.library.loracall.view.LoRaLogView.3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(String str) {
                    if (linkedList.size() >= 500) {
                        linkedList.removeFirst();
                    }
                    linkedList.add(str);
                    baseAdapter.submitList(CollectionsKt.toList(linkedList));
                }
            });
        }
    }

    public final View getView() {
        return this.view;
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }
}
