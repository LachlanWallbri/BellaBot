package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.disinfect.baselib.network.response.FeaturedBean;
import com.pudutech.peanut.robot_ui.C5508R;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromotionsAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0014J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0003H\u0002R+\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/PromotionsAdapter;", "Lcom/chad/library/adapter/base/BaseMultiItemQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/PromotionMultipleItem;", "Lcom/pudutech/disinfect/baselib/network/response/FeaturedBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "mList", "", "(Ljava/util/List;)V", "mCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "message", "", "convert", "helper", "item", "setPromotionsData", "mPromotion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PromotionsAdapter extends BaseMultiItemQuickAdapter<PromotionMultipleItem<FeaturedBean>, BaseViewHolder> {
    private Function1<? super FeaturedBean, Unit> mCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromotionsAdapter(List<PromotionMultipleItem<FeaturedBean>> mList) {
        super(mList);
        Intrinsics.checkParameterIsNotNull(mList, "mList");
        addItemType(1, C5508R.layout.item_promotions_one);
        addItemType(2, C5508R.layout.item_promotions_two);
        addItemType(2, C5508R.layout.item_promotions_three);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, PromotionMultipleItem<FeaturedBean> item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        setPromotionsData(helper, item.getMData());
    }

    private final void setPromotionsData(BaseViewHolder helper, FeaturedBean mPromotion) {
        if (helper != null) {
            TextView tvName = (TextView) helper.getView(C5508R.id.tvName);
            TextView tvPrice = (TextView) helper.getView(C5508R.id.tvPrice);
            ImageView imageView = (ImageView) helper.getView(C5508R.id.ivImg);
            if (mPromotion != null) {
                String name = mPromotion.getName();
                if (name != null) {
                    Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
                    tvName.setText(name);
                }
                double price = mPromotion.getPrice();
                Intrinsics.checkExpressionValueIsNotNull(tvPrice, "tvPrice");
                tvPrice.setText(String.valueOf(price));
                Glide.with(this.mContext).load(mPromotion.getImage_url()).skipMemoryCache(true).thumbnail(0.5f).into(imageView);
            }
        }
    }
}
