package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.os.Handler;
import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.CallHistoryData;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.CallHistoryAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CallHistoryManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliveryHistoryTaskDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u000128\u0010\u0002\u001a4\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00060\u0006 \u0005*\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0003¨\u0006\u00010\u0003¨\u0006\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "kotlin.jvm.PlatformType", "Lcom/chad/library/adapter/base/BaseViewHolder;", "view1", "Landroid/view/View;", RequestParameters.POSITION, "", "onItemChildClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliveryHistoryTaskDialog$initRv$2 implements BaseQuickAdapter.OnItemChildClickListener {
    final /* synthetic */ DeliveryHistoryTaskDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliveryHistoryTaskDialog$initRv$2(DeliveryHistoryTaskDialog deliveryHistoryTaskDialog) {
        this.this$0 = deliveryHistoryTaskDialog;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public final void onItemChildClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view1, int i) {
        Intrinsics.checkParameterIsNotNull(view1, "view1");
        if (view1.getId() == C4188R.id.iv_finish) {
            CallHistoryData it = DeliveryHistoryTaskDialog.access$getAdapter$p(this.this$0).getItem(i);
            if (it != null) {
                CallHistoryManager callHistoryManager = CallHistoryManager.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                callHistoryManager.removeHistory(it);
            }
            DeliveryHistoryTaskDialog.access$getAdapter$p(this.this$0).remove(i);
            CallHistoryManager.INSTANCE.asyncGetHistory(new Function1<List<? extends CallHistoryData>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initRv$2.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends CallHistoryData> list) {
                    invoke2((List<CallHistoryData>) list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final List<CallHistoryData> it2) {
                    Handler handler;
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    handler = DeliveryHistoryTaskDialog$initRv$2.this.this$0.mainHandle;
                    handler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog.initRv.2.2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (it2.size() == 6) {
                                DeliveryHistoryTaskDialog.access$getAdapter$p(DeliveryHistoryTaskDialog$initRv$2.this.this$0).addData((CallHistoryAdapter) it2.get(r1.size() - 1));
                            }
                        }
                    });
                }
            });
        }
    }
}
