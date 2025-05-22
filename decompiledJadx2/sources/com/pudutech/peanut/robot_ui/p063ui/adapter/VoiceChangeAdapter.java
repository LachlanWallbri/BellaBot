package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.VoiceBean;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.adapter.VoiceChangeAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceChangeAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0006R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/VoiceChangeAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/bean/VoiceBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "setContext", "mItemClickListener", "Lcom/pudutech/peanut/robot_ui/ui/adapter/VoiceChangeAdapter$ItemClickListener;", "convert", "", "helper", "item", "setItemClickListener", "ItemClickListener", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class VoiceChangeAdapter extends BaseQuickAdapter<VoiceBean, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private ItemClickListener mItemClickListener;

    /* compiled from: VoiceChangeAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/VoiceChangeAdapter$ItemClickListener;", "", "itemClick", "", "item", "Lcom/pudutech/peanut/robot_ui/bean/VoiceBean;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ItemClickListener {
        void itemClick(VoiceBean item);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceChangeAdapter(Context context) {
        super(C5508R.layout.item_recycle_voice_change);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "RecycleArriveTaskAdapter";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder helper, final VoiceBean item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView tvName = (TextView) helper.getView(C5508R.id.tvName);
        RelativeLayout rlBg = (RelativeLayout) helper.getView(C5508R.id.rlBg);
        ImageView imageView = (ImageView) helper.getView(C5508R.id.ivSelect);
        String content = item.getContent();
        if (content != null) {
            Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
            tvName.setText(content);
        }
        if (Intrinsics.areEqual((Object) item.isSelect(), (Object) true)) {
            Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
            rlBg.setBackground(this.context.getResources().getDrawable(C5508R.drawable.blue_select_bg));
            imageView.setImageResource(C5508R.drawable.ic_icon_radio_selected);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(rlBg, "rlBg");
            rlBg.setBackground(this.context.getResources().getDrawable(C5508R.drawable.shape_radius_8_white));
            imageView.setImageResource(C5508R.drawable.ic_icon_radio_unselect);
        }
        ViewExtKt.onSingleClick(rlBg, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.VoiceChangeAdapter$convert$$inlined$let$lambda$1
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

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                VoiceChangeAdapter.ItemClickListener itemClickListener;
                Intrinsics.checkParameterIsNotNull(it, "it");
                itemClickListener = VoiceChangeAdapter.this.mItemClickListener;
                if (itemClickListener != null) {
                    itemClickListener.itemClick(item);
                }
                List<VoiceBean> data = VoiceChangeAdapter.this.getData();
                if (data != null) {
                    for (VoiceBean voiceBean : data) {
                        voiceBean.setSelect(Boolean.valueOf(Intrinsics.areEqual(item.getContent(), voiceBean.getContent())));
                    }
                }
                VoiceChangeAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public final void setItemClickListener(ItemClickListener mItemClickListener) {
        Intrinsics.checkParameterIsNotNull(mItemClickListener, "mItemClickListener");
        this.mItemClickListener = mItemClickListener;
    }
}
