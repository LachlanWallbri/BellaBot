package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.CallHistoryData;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallHistoryAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0014R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/CallHistoryAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/bean/CallHistoryData;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "getDateFormat", "()Ljava/text/SimpleDateFormat;", "dateFormat$delegate", "Lkotlin/Lazy;", "convert", "", "helper", "item", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CallHistoryAdapter extends BaseQuickAdapter<CallHistoryData, BaseViewHolder> {

    /* renamed from: dateFormat$delegate, reason: from kotlin metadata */
    private final Lazy dateFormat;

    private final SimpleDateFormat getDateFormat() {
        return (SimpleDateFormat) this.dateFormat.getValue();
    }

    public CallHistoryAdapter() {
        super(C4188R.layout.item_call_history);
        this.dateFormat = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<SimpleDateFormat>() { // from class: com.pudutech.bumblebee.robot_ui.ui.adapter.CallHistoryAdapter$dateFormat$2
            @Override // kotlin.jvm.functions.Function0
            public final SimpleDateFormat invoke() {
                return new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, CallHistoryData item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        helper.setText(C4188R.id.tv_table_num, item.getDestination());
        helper.setText(C4188R.id.tv_time, getDateFormat().format(Long.valueOf(item.getCompleteTime())));
        helper.addOnClickListener(C4188R.id.iv_finish);
    }
}
