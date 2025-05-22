package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bean.WeComeBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeComeAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0014J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0003H\u0002R+\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/WeComeAdapter;", "Lcom/chad/library/adapter/base/BaseMultiItemQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/ChatMultipleItem;", "Lcom/pudutech/peanut/robot_ui/bean/WeComeBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "mList", "", "(Ljava/util/List;)V", "mCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "message", "", "convert", "helper", "item", "setMeData", "chat", "setOtherData", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WeComeAdapter extends BaseMultiItemQuickAdapter<ChatMultipleItem<WeComeBean>, BaseViewHolder> {
    private Function1<? super WeComeBean, Unit> mCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeComeAdapter(List<ChatMultipleItem<WeComeBean>> mList) {
        super(mList);
        Intrinsics.checkParameterIsNotNull(mList, "mList");
        addItemType(1, C5508R.layout.item_wecome_view);
        addItemType(2, C5508R.layout.item_wecome_me);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, ChatMultipleItem<WeComeBean> item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item.getMItemType() == 1) {
            setOtherData(helper, item.getMData());
        } else {
            setMeData(helper, item.getMData());
        }
    }

    private final void setOtherData(BaseViewHolder helper, WeComeBean chat) {
        if (helper != null) {
            TextView tvMsg = (TextView) helper.getView(C5508R.id.tvChatInfo);
            TextView tvHead = (TextView) helper.getView(C5508R.id.tvChatHead);
            ImageView imageView = (ImageView) helper.getView(C5508R.id.ivChatImage);
            Intrinsics.checkExpressionValueIsNotNull(tvHead, "tvHead");
            tvHead.setText(RobotContext.INSTANCE.getContext().getString(C5508R.string.give_birth));
            String content = chat != null ? chat.getContent() : null;
            boolean z = true;
            if (content == null || content.length() == 0) {
                Intrinsics.checkExpressionValueIsNotNull(tvMsg, "tvMsg");
                tvMsg.setVisibility(8);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(tvMsg, "tvMsg");
                tvMsg.setVisibility(0);
                tvMsg.setText(chat.getContent());
            }
            if (imageView != null) {
                String img = chat != null ? chat.getImg() : null;
                if (img != null && img.length() != 0) {
                    z = false;
                }
                if (z) {
                    imageView.setVisibility(8);
                    return;
                }
                imageView.setVisibility(0);
                RequestOptions bitmapTransform = RequestOptions.bitmapTransform(new RoundedCorners(20));
                Intrinsics.checkExpressionValueIsNotNull(bitmapTransform, "RequestOptions.bitmapTransform(RoundedCorners(20))");
                Intrinsics.checkExpressionValueIsNotNull(Glide.with(this.mContext).load(chat.getImg()).placeholder(C5508R.mipmap.schedulerlib_ic_launcher).thumbnail(0.5f).apply((BaseRequestOptions<?>) bitmapTransform).into(imageView), "Glide.with(mContext)\n   …             .into(image)");
            }
        }
    }

    private final void setMeData(BaseViewHolder helper, WeComeBean chat) {
        if (helper != null) {
            TextView tvMsg = (TextView) helper.getView(C5508R.id.tvChatMeInfo);
            ImageView imageView = (ImageView) helper.getView(C5508R.id.ivChatImage);
            String content = chat != null ? chat.getContent() : null;
            boolean z = true;
            if (content == null || content.length() == 0) {
                Intrinsics.checkExpressionValueIsNotNull(tvMsg, "tvMsg");
                tvMsg.setVisibility(8);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(tvMsg, "tvMsg");
                tvMsg.setVisibility(0);
                tvMsg.setText(chat.getContent());
            }
            if (imageView != null) {
                String img = chat != null ? chat.getImg() : null;
                if (img != null && img.length() != 0) {
                    z = false;
                }
                if (z) {
                    imageView.setVisibility(8);
                    return;
                }
                imageView.setVisibility(0);
                RequestOptions bitmapTransform = RequestOptions.bitmapTransform(new RoundedCorners(20));
                Intrinsics.checkExpressionValueIsNotNull(bitmapTransform, "RequestOptions.bitmapTransform(RoundedCorners(20))");
                Intrinsics.checkExpressionValueIsNotNull(Glide.with(this.mContext).load(chat.getImg()).placeholder(C5508R.mipmap.schedulerlib_ic_launcher).thumbnail(0.5f).apply((BaseRequestOptions<?>) bitmapTransform).into(imageView), "Glide.with(mContext)\n   …             .into(image)");
            }
        }
    }
}
