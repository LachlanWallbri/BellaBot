package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import com.pudutech.bumblebee.robot_ui.p054ui.view.RobotDeliverTaskLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeliverTaskEditActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "Landroid/widget/PopupWindow;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$showPalletTtsWindow$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showPalletTtsWindow$1$invokeSuspend$$inlined$let$lambda$1 */
/* loaded from: classes3.dex */
public final class C4293x698a3188 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PopupWindow>, Object> {
    final /* synthetic */ List $list;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4899p$;
    final /* synthetic */ DeliverTaskEditActivity$showPalletTtsWindow$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4293x698a3188(List list, Continuation continuation, DeliverTaskEditActivity$showPalletTtsWindow$1 deliverTaskEditActivity$showPalletTtsWindow$1) {
        super(2, continuation);
        this.$list = list;
        this.this$0 = deliverTaskEditActivity$showPalletTtsWindow$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4293x698a3188 c4293x698a3188 = new C4293x698a3188(this.$list, completion, this.this$0);
        c4293x698a3188.f4899p$ = (CoroutineScope) obj;
        return c4293x698a3188;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PopupWindow> continuation) {
        return ((C4293x698a3188) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PopupWindow popupWindow;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4899p$;
            PalletTtsScheme palletTtsScheme = (PalletTtsScheme) ((RobotDeliverTaskLayout) this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getCurrentSelectPallet().getPalletTtsScheme();
            if (palletTtsScheme == null) {
                View pwContentView = this.this$0.$pwContentView;
                Intrinsics.checkExpressionValueIsNotNull(pwContentView, "pwContentView");
                View childAt = ((RadioGroup) pwContentView.findViewById(C4188R.id.rg_pallet_tts_scheme)).getChildAt(0);
                if (childAt == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioButton");
                }
                ((RadioButton) childAt).setChecked(true);
            }
            int i = 0;
            for (Object obj2 : this.$list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PalletTtsScheme palletTtsScheme2 = (PalletTtsScheme) obj2;
                int intValue = Boxing.boxInt(i).intValue();
                if (intValue < 4) {
                    View pwContentView2 = this.this$0.$pwContentView;
                    Intrinsics.checkExpressionValueIsNotNull(pwContentView2, "pwContentView");
                    View childAt2 = ((RadioGroup) pwContentView2.findViewById(C4188R.id.rg_pallet_tts_scheme)).getChildAt(intValue + 1);
                    if (childAt2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioButton");
                    }
                    RadioButton radioButton = (RadioButton) childAt2;
                    radioButton.setText(palletTtsScheme2.getTaskName());
                    radioButton.setVisibility(0);
                    radioButton.setChecked(palletTtsScheme != null && palletTtsScheme.getMid() == palletTtsScheme2.getMid());
                }
                i = i2;
            }
            View pwContentView3 = this.this$0.$pwContentView;
            Intrinsics.checkExpressionValueIsNotNull(pwContentView3, "pwContentView");
            ((RadioGroup) pwContentView3.findViewById(C4188R.id.rg_pallet_tts_scheme)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showPalletTtsWindow$1$invokeSuspend$$inlined$let$lambda$1.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup, int i3) {
                    if (i3 == C4188R.id.rbt1) {
                        ((RobotDeliverTaskLayout) C4293x698a3188.this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getCurrentSelectPallet().setPalletTtsScheme(null);
                    } else if (i3 == C4188R.id.rbt2) {
                        ((RobotDeliverTaskLayout) C4293x698a3188.this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getCurrentSelectPallet().setPalletTtsScheme(C4293x698a3188.this.$list.get(0));
                    } else if (i3 == C4188R.id.rbt3) {
                        ((RobotDeliverTaskLayout) C4293x698a3188.this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getCurrentSelectPallet().setPalletTtsScheme(C4293x698a3188.this.$list.get(1));
                    } else if (i3 == C4188R.id.rbt4) {
                        ((RobotDeliverTaskLayout) C4293x698a3188.this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getCurrentSelectPallet().setPalletTtsScheme(C4293x698a3188.this.$list.get(2));
                    } else if (i3 == C4188R.id.rbt5) {
                        ((RobotDeliverTaskLayout) C4293x698a3188.this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getCurrentSelectPallet().setPalletTtsScheme(C4293x698a3188.this.$list.get(3));
                    }
                    ((RobotDeliverTaskLayout) C4293x698a3188.this.this$0.this$0._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).updateViewState();
                }
            });
            this.this$0.this$0.popupWindowPalletTts = new PopupWindow();
            popupWindow = this.this$0.this$0.popupWindowPalletTts;
            if (popupWindow == null) {
                return null;
            }
            popupWindow.setContentView(this.this$0.$pwContentView);
            popupWindow.setWidth(-2);
            popupWindow.setHeight(-2);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(false);
            ((Button) this.this$0.this$0._$_findCachedViewById(C4188R.id.pallet_tts_btn)).getGlobalVisibleRect(new Rect());
            popupWindow.getContentView().measure(0, 0);
            Button pallet_tts_btn = (Button) this.this$0.this$0._$_findCachedViewById(C4188R.id.pallet_tts_btn);
            Intrinsics.checkExpressionValueIsNotNull(pallet_tts_btn, "pallet_tts_btn");
            int i3 = (-pallet_tts_btn.getHeight()) / 2;
            View contentView = popupWindow.getContentView();
            Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
            int measuredHeight = i3 - (contentView.getMeasuredHeight() / 2);
            View contentView2 = popupWindow.getContentView();
            Intrinsics.checkExpressionValueIsNotNull(contentView2, "contentView");
            int i4 = -contentView2.getMeasuredWidth();
            Resources resources = this.this$0.this$0.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            Configuration config = resources.getConfiguration();
            Intrinsics.checkExpressionValueIsNotNull(config, "config");
            if (config.getLayoutDirection() == 1) {
                View findViewById = popupWindow.getContentView().findViewById(C4188R.id.triangle);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "contentView.findViewById<ImageView>(R.id.triangle)");
                ((ImageView) findViewById).setRotation(180.0f);
                i4 = 0;
            }
            popupWindow.showAsDropDown((Button) this.this$0.this$0._$_findCachedViewById(C4188R.id.pallet_tts_btn), i4, measuredHeight);
            return popupWindow;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
