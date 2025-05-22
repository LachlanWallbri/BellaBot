package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MultiTableEditAdapter;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiTableEditAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u000b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0002H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter$DishInfoEdit;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "onDeleteClickListener", "com/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter$onDeleteClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter$onDeleteClickListener$1;", "convert", "", "helper", "item", "getTextColor", "", "isClose", "", "DishInfoEdit", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MultiTableEditAdapter extends BaseQuickAdapter<DishInfoEdit, BaseViewHolder> {
    private Context context;
    private final MultiTableEditAdapter$onDeleteClickListener$1 onDeleteClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.pudutech.peanut.robot_ui.ui.adapter.MultiTableEditAdapter$onDeleteClickListener$1] */
    public MultiTableEditAdapter(Context context) {
        super(C5508R.layout.item_multi_table_edit);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.onDeleteClickListener = new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.MultiTableEditAdapter$onDeleteClickListener$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.MultiTableEditAdapter.DishInfoEdit");
                }
                MultiTableEditAdapter.DishInfoEdit dishInfoEdit = (MultiTableEditAdapter.DishInfoEdit) tag;
                if (MultiTableEditAdapter.this.getData().contains(dishInfoEdit)) {
                    MultiTableEditAdapter.this.getData().remove(dishInfoEdit);
                    MultiTableEditAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, DishInfoEdit item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C5508R.id.name_text);
        ImageView deletIv = (ImageView) helper.getView(C5508R.id.edit_delete_iv);
        Intrinsics.checkExpressionValueIsNotNull(deletIv, "deletIv");
        deletIv.setTag(item);
        deletIv.setOnClickListener(this.onDeleteClickListener);
        textView.setTextColor(getTextColor(item.isClose()));
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(item.getInfo().getDestination());
        textView.setTextSize(0, this.context.getResources().getDimension(C5508R.dimen.dialog_robot_tray_multi_table_edit_item_tv_s));
        UiUtils.adjustTvTextSize(textView, (int) this.context.getResources().getDimension(C5508R.dimen.dialog_robot_tray_multi_table_edit_item_tv_content_max_w), item.getInfo().getDestination(), 24);
    }

    private final int getTextColor(boolean isClose) {
        Context context;
        int i;
        if (isClose) {
            context = this.context;
            i = C5508R.color.font_color_1;
        } else {
            context = this.context;
            i = C5508R.color.white;
        }
        return context.getColor(i);
    }

    /* compiled from: MultiTableEditAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter$DishInfoEdit;", "", "info", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "isClose", "", "(Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;Z)V", "getInfo", "()Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "setInfo", "(Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;)V", "()Z", "setClose", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class DishInfoEdit {
        private DeliveryModel info;
        private boolean isClose;

        public static /* synthetic */ DishInfoEdit copy$default(DishInfoEdit dishInfoEdit, DeliveryModel deliveryModel, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                deliveryModel = dishInfoEdit.info;
            }
            if ((i & 2) != 0) {
                z = dishInfoEdit.isClose;
            }
            return dishInfoEdit.copy(deliveryModel, z);
        }

        /* renamed from: component1, reason: from getter */
        public final DeliveryModel getInfo() {
            return this.info;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsClose() {
            return this.isClose;
        }

        public final DishInfoEdit copy(DeliveryModel info, boolean isClose) {
            Intrinsics.checkParameterIsNotNull(info, "info");
            return new DishInfoEdit(info, isClose);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DishInfoEdit)) {
                return false;
            }
            DishInfoEdit dishInfoEdit = (DishInfoEdit) other;
            return Intrinsics.areEqual(this.info, dishInfoEdit.info) && this.isClose == dishInfoEdit.isClose;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            DeliveryModel deliveryModel = this.info;
            int hashCode = (deliveryModel != null ? deliveryModel.hashCode() : 0) * 31;
            boolean z = this.isClose;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public String toString() {
            return "DishInfoEdit(info=" + this.info + ", isClose=" + this.isClose + ")";
        }

        public DishInfoEdit(DeliveryModel info, boolean z) {
            Intrinsics.checkParameterIsNotNull(info, "info");
            this.info = info;
            this.isClose = z;
        }

        public final DeliveryModel getInfo() {
            return this.info;
        }

        public final void setInfo(DeliveryModel deliveryModel) {
            Intrinsics.checkParameterIsNotNull(deliveryModel, "<set-?>");
            this.info = deliveryModel;
        }

        public /* synthetic */ DishInfoEdit(DeliveryModel deliveryModel, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(deliveryModel, (i & 2) != 0 ? false : z);
        }

        public final boolean isClose() {
            return this.isClose;
        }

        public final void setClose(boolean z) {
            this.isClose = z;
        }
    }
}
