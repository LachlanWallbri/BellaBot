package com.pudutech.bumblebee.robot_ui.advertise;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.disinfect.baselib.imageLoader.ImageLoader;
import com.youth.banner.adapter.BannerAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverBannerAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001dB\u001d\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002J,\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0017H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBannerAdapter;", "Lcom/youth/banner/adapter/BannerAdapter;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverImageBean;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBannerAdapter$BannerViewHolder;", "datas", "", "activity", "Landroid/app/Activity;", "(Ljava/util/List;Landroid/app/Activity;)V", "TAG", "", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "load", "", "img", "Landroid/widget/ImageView;", "data", "onBindView", "holder", RequestParameters.POSITION, "", "size", "onCreateHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BannerViewHolder", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdverBannerAdapter extends BannerAdapter<AdverImageBean, BannerViewHolder> {
    private final String TAG;
    private Activity activity;

    public final Activity getActivity() {
        return this.activity;
    }

    public final void setActivity(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "<set-?>");
        this.activity = activity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdverBannerAdapter(List<AdverImageBean> list, Activity activity) {
        super(list);
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.activity = activity;
        this.TAG = "AdverBannerAdapter";
    }

    /* compiled from: AdverBannerAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBannerAdapter$BannerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "mImage", "Landroid/widget/ImageView;", "getMImage", "()Landroid/widget/ImageView;", "setMImage", "(Landroid/widget/ImageView;)V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BannerViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            View findViewById = view.findViewById(C4188R.id.item_adver_banner_iv);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.item_adver_banner_iv)");
            this.mImage = (ImageView) findViewById;
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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(C4188R.layout.item_adver_banner_adapter, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflate");
        return new BannerViewHolder(inflate);
    }

    @Override // com.youth.banner.holder.IViewHolder
    public void onBindView(BannerViewHolder holder, AdverImageBean data, int position, int size) {
        load(holder != null ? holder.getMImage() : null, data);
    }

    public final void load(ImageView img, final AdverImageBean data) {
        String str;
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (data == null || (str = data.getUrl()) == null) {
            str = "";
        }
        imageLoader.display(img, str, C4188R.drawable.bl_load_error, C4188R.drawable.bl_load_error, new RequestListener<Drawable>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdverBannerAdapter$load$1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                AdverImageBean adverImageBean = AdverImageBean.this;
                if (adverImageBean == null) {
                    return false;
                }
                adverImageBean.setLoadCompleted(true);
                return false;
            }
        });
    }
}
