package com.pudutech.peanut.robot_ui.p063ui.fragment;

import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.disinfect.baselib.network.response.PromotionsBean;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0014¨\u0006\b"}, m3961d2 = {"com/pudutech/peanut/robot_ui/ui/fragment/PromotionsFragment$initView$1", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/disinfect/baselib/network/response/PromotionsBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "convert", "", "holder", "item", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PromotionsFragment$initView$1 extends BaseQuickAdapter<PromotionsBean, BaseViewHolder> {
    final /* synthetic */ PromotionsFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromotionsFragment$initView$1(PromotionsFragment promotionsFragment, int i, List list) {
        super(i, list);
        this.this$0 = promotionsFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final PromotionsBean item) {
        Resources resources;
        Resources resources2;
        Resources resources3;
        Resources resources4;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        Intrinsics.checkParameterIsNotNull(item, "item");
        View view = holder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        TextView textView = (TextView) view.findViewById(C5508R.id.tvName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "holder.itemView.tvName");
        textView.setText(item.getTitle());
        if (item.isSelect()) {
            View view2 = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "holder.itemView");
            ImageView imageView = (ImageView) view2.findViewById(C5508R.id.ivImg);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "holder.itemView.ivImg");
            imageView.setVisibility(0);
            if (holder.getLayoutPosition() != 0) {
                if (holder.getLayoutPosition() == this.this$0.mList.size() - 1) {
                    View view3 = holder.itemView;
                    Intrinsics.checkExpressionValueIsNotNull(view3, "holder.itemView");
                    ((FrameLayout) view3.findViewById(C5508R.id.flBg)).setBackgroundResource(C5508R.drawable.shape_0072ff_bottom_radius_8);
                } else {
                    FragmentActivity activity = this.this$0.getActivity();
                    if (activity != null && (resources4 = activity.getResources()) != null) {
                        int color = resources4.getColor(C5508R.color.theme_main_color);
                        View view4 = holder.itemView;
                        Intrinsics.checkExpressionValueIsNotNull(view4, "holder.itemView");
                        ((FrameLayout) view4.findViewById(C5508R.id.flBg)).setBackgroundColor(color);
                    }
                }
            } else {
                View view5 = holder.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view5, "holder.itemView");
                ((FrameLayout) view5.findViewById(C5508R.id.flBg)).setBackgroundResource(C5508R.drawable.shape_00ff72_top_radius_8);
            }
            FragmentActivity activity2 = this.this$0.getActivity();
            if (activity2 != null && (resources3 = activity2.getResources()) != null) {
                int color2 = resources3.getColor(C5508R.color.white);
                View view6 = holder.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view6, "holder.itemView");
                ((TextView) view6.findViewById(C5508R.id.tvName)).setTextColor(color2);
            }
        } else {
            View view7 = holder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view7, "holder.itemView");
            ImageView imageView2 = (ImageView) view7.findViewById(C5508R.id.ivImg);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "holder.itemView.ivImg");
            imageView2.setVisibility(8);
            FragmentActivity activity3 = this.this$0.getActivity();
            if (activity3 != null && (resources2 = activity3.getResources()) != null) {
                int color3 = resources2.getColor(C5508R.color.transparent00);
                View view8 = holder.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view8, "holder.itemView");
                ((FrameLayout) view8.findViewById(C5508R.id.flBg)).setBackgroundColor(color3);
            }
            FragmentActivity activity4 = this.this$0.getActivity();
            if (activity4 != null && (resources = activity4.getResources()) != null) {
                int color4 = resources.getColor(C5508R.color.c_a8a8a8);
                View view9 = holder.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view9, "holder.itemView");
                ((TextView) view9.findViewById(C5508R.id.tvName)).setTextColor(color4);
            }
        }
        View view10 = holder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view10, "holder.itemView");
        TextView textView2 = (TextView) view10.findViewById(C5508R.id.tvName);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "holder.itemView.tvName");
        ViewExtKt.onSingleClick(textView2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.PromotionsFragment$initView$1$convert$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view11) {
                invoke2(view11);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                List mData;
                Intrinsics.checkParameterIsNotNull(it, "it");
                mData = PromotionsFragment$initView$1.this.mData;
                Intrinsics.checkExpressionValueIsNotNull(mData, "mData");
                Iterator it2 = mData.iterator();
                while (it2.hasNext()) {
                    ((PromotionsBean) it2.next()).setSelect(false);
                }
                item.setSelect(true);
                PromotionsFragment$initView$1.this.this$0.setContent(item);
                PromotionsFragment$initView$1.this.notifyDataSetChanged();
            }
        });
    }
}
