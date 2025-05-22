package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.View;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnLazyItemClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001ag\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022S\u0010\u0003\u001aO\u0012\u001b\u0012\u0019\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00010\u0004¨\u0006\f"}, m3961d2 = {"onSingleItemClick", "", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "block", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "adapter", "Landroid/view/View;", "view", "", RequestParameters.POSITION, "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class OnLazyItemClickListenerKt {
    public static final void onSingleItemClick(BaseQuickAdapter<?, ?> onSingleItemClick, final Function3<? super BaseQuickAdapter<?, ?>, ? super View, ? super Integer, Unit> block) {
        Intrinsics.checkParameterIsNotNull(onSingleItemClick, "$this$onSingleItemClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        onSingleItemClick.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListenerKt$sam$com_chad_library_adapter_base_BaseQuickAdapter_OnItemClickListener$0
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final /* synthetic */ void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intrinsics.checkExpressionValueIsNotNull(Function3.this.invoke(baseQuickAdapter, view, Integer.valueOf(i)), "invoke(...)");
            }
        });
    }
}
