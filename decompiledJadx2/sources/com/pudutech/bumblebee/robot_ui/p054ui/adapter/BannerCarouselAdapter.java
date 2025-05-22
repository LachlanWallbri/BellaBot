package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.disinfect.baselib.imageLoader.ImageLoader;
import com.youth.banner.adapter.BannerAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BannerCarouselAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0012B\u0015\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0016¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/BannerCarouselAdapter;", "Lcom/youth/banner/adapter/BannerAdapter;", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/BannerCarouselAdapter$BannerViewHolder;", "datas", "", "(Ljava/util/List;)V", "onBindView", "", "holder", "data", RequestParameters.POSITION, "", "size", "onCreateHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BannerViewHolder", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BannerCarouselAdapter extends BannerAdapter<String, BannerViewHolder> {
    public BannerCarouselAdapter(List<String> list) {
        super(list);
    }

    /* compiled from: BannerCarouselAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/BannerCarouselAdapter$BannerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "mImage", "Landroid/widget/ImageView;", "getMImage", "()Landroid/widget/ImageView;", "setMImage", "(Landroid/widget/ImageView;)V", "mView", "getMView", "()Landroid/view/View;", "setMView", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private View mView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BannerViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.mView = view;
            View findViewById = view.findViewById(C4188R.id.item_banner_iv);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.item_banner_iv)");
            this.mImage = (ImageView) findViewById;
        }

        public final View getMView() {
            return this.mView;
        }

        public final void setMView(View view) {
            Intrinsics.checkParameterIsNotNull(view, "<set-?>");
            this.mView = view;
        }

        public final ImageView getMImage() {
            return this.mImage;
        }

        public final void setMImage(ImageView imageView) {
            Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
            this.mImage = imageView;
        }
    }

    @Override // com.youth.banner.holder.IViewHolder
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(C4188R.layout.item_banner_adapter, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflate");
        return new BannerViewHolder(inflate);
    }

    @Override // com.youth.banner.holder.IViewHolder
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
        ImageLoader.getInstance().displayRounded(holder != null ? holder.getMImage() : null, data, 15);
    }
}
