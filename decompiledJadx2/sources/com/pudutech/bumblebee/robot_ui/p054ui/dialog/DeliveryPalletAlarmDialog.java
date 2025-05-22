package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.VerticalCenterSpan;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.util.PalletTaskUtil;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: DeliveryPalletAlarmDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0002J \u0010\u0015\u001a\u00020\u00162\u0016\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u000ej\b\u0012\u0004\u0012\u00020\u0018`\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J&\u0010\"\u001a\u00020\u00142\u0016\u0010#\u001a\u0012\u0012\u0004\u0012\u00020$0\u000ej\b\u0012\u0004\u0012\u00020$`\u00102\u0006\u0010%\u001a\u00020\tJ\u0018\u0010&\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020!2\u0006\u0010'\u001a\u00020$H\u0002J\b\u0010(\u001a\u00020\u0014H\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DeliveryPalletAlarmDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResID", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentTrayCount", "mContext", "trayInfoViews", "Ljava/util/ArrayList;", "Landroid/widget/RelativeLayout;", "Lkotlin/collections/ArrayList;", "view", "Landroid/view/View;", "bindView", "", "hasTrayOrders", "", "foodInfo", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "init", "isTaskFinish", "deliveryModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "isTrayEnable", "int", "setFootInfoCountTextSize", "textView", "Landroid/widget/TextView;", "setTrayInfo", "list", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "currentTableId", "setTrayText", "t", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliveryPalletAlarmDialog extends Dialog {
    private final String TAG;
    private int currentTrayCount;
    private Context mContext;
    private final ArrayList<RelativeLayout> trayInfoViews;
    private View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryPalletAlarmDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryPalletAlarmDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        bindView();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window);
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void bindView() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_delivery_pallet_alarm, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ivery_pallet_alarm, null)");
        this.view = inflate;
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        if (view != null) {
            this.trayInfoViews.add(view.findViewById(C4188R.id.layout_pallet_alarm_item_1));
            this.trayInfoViews.add(view.findViewById(C4188R.id.layout_pallet_alarm_item_2));
            this.trayInfoViews.add(view.findViewById(C4188R.id.layout_pallet_alarm_item_3));
            this.trayInfoViews.add(view.findViewById(C4188R.id.layout_pallet_alarm_item_4));
        }
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        TextView textView = view2 != null ? (TextView) view2.findViewById(C4188R.id.alarm_get_btn) : null;
        Intrinsics.checkExpressionValueIsNotNull(textView, "view?.findViewById<TextView>(R.id.alarm_get_btn)");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(textView, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryPalletAlarmDialog$bindView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view3) {
                invoke2(view3);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                DeliveryPalletAlarmDialog.this.dismiss();
            }
        }, 3, null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        setContentView(view3);
    }

    public final void setTrayInfo(ArrayList<TrayModel> list, String currentTableId) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(currentTableId, "currentTableId");
        Pdlog.m3273d(this.TAG, "setTrayInfo = " + list + " , currentMovingText = " + currentTableId);
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            if (i > this.trayInfoViews.size() - 1) {
                Pdlog.m3274e(this.TAG, "setTrayInfo size more then " + this.trayInfoViews.size());
                return;
            }
            RelativeLayout relativeLayout = this.trayInfoViews.get(i);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "trayInfoViews[index]");
            RelativeLayout relativeLayout2 = relativeLayout;
            CardView cardView = (CardView) relativeLayout2.findViewById(C4188R.id.alarm_card_view);
            TextView textView = (TextView) relativeLayout2.findViewById(C4188R.id.alarm_task_name);
            LottieAnimationView animationLeft = (LottieAnimationView) relativeLayout2.findViewById(C4188R.id.animation_left);
            LottieAnimationView animationRight = (LottieAnimationView) relativeLayout2.findViewById(C4188R.id.animation_right);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            setTrayText(textView, trayModel);
            DeliveryModel current = trayModel.getCurrent();
            if (!isTrayEnable(i)) {
                Context context = this.mContext;
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                cardView.setCardBackgroundColor(context.getResources().getColor(C4188R.color.transparent));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f = 0;
                cardView.setCardElevation(f);
                cardView.setMaxCardElevation(f);
                textView.setBackgroundResource(C4188R.drawable.settings_tray_disable);
                Intrinsics.checkExpressionValueIsNotNull(animationLeft, "animationLeft");
                animationLeft.setVisibility(8);
                Intrinsics.checkExpressionValueIsNotNull(animationRight, "animationRight");
                animationRight.setVisibility(8);
                animationLeft.cancelAnimation();
                animationRight.cancelAnimation();
            } else if (current != null && !isTaskFinish(current)) {
                Context context2 = this.mContext;
                if (context2 == null) {
                    Intrinsics.throwNpe();
                }
                cardView.setCardBackgroundColor(context2.getResources().getColor(C4188R.color.theme_main_color));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                cardView.setCardElevation(SizeUtils.dp2px(getContext(), 3.0f));
                cardView.setMaxCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                Sdk27PropertiesKt.setTextColor(textView, -1);
                textView.setBackgroundResource(0);
                Intrinsics.checkExpressionValueIsNotNull(animationLeft, "animationLeft");
                animationLeft.setVisibility(0);
                Intrinsics.checkExpressionValueIsNotNull(animationRight, "animationRight");
                animationRight.setVisibility(0);
                animationLeft.playAnimation();
                animationRight.playAnimation();
            } else {
                Context context3 = getContext();
                if (context3 == null) {
                    Intrinsics.throwNpe();
                }
                cardView.setCardBackgroundColor(context3.getResources().getColor(C4188R.color.deliver_pause_tray_bg_normal));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f2 = 0;
                cardView.setCardElevation(f2);
                cardView.setMaxCardElevation(f2);
                Context context4 = this.mContext;
                if (context4 == null) {
                    Intrinsics.throwNpe();
                }
                Sdk27PropertiesKt.setTextColor(textView, context4.getResources().getColor(C4188R.color.deliver_pause_tray_font));
                textView.setBackgroundResource(0);
                Intrinsics.checkExpressionValueIsNotNull(animationLeft, "animationLeft");
                animationLeft.setVisibility(8);
                Intrinsics.checkExpressionValueIsNotNull(animationRight, "animationRight");
                animationRight.setVisibility(8);
                animationLeft.cancelAnimation();
                animationRight.cancelAnimation();
            }
            i = i2;
        }
    }

    private final boolean hasTrayOrders(ArrayList<InformationSystemContract.OrderInfo> foodInfo) {
        Iterator<T> it = foodInfo.iterator();
        while (it.hasNext()) {
            if (((InformationSystemContract.OrderInfo) it.next()).getOrderType() == InformationSystemContract.OrderInfo.Type.TrayOrder) {
                return true;
            }
        }
        return false;
    }

    private final void setTrayText(TextView view, TrayModel t) {
        String palletTaskName = PalletTaskUtil.INSTANCE.getPalletTaskName(t);
        view.setText(palletTaskName);
        UiUtils.adjustTvTextSize(view, 122, palletTaskName, 18);
        if (PalletTaskUtil.INSTANCE.getHasFootInfo()) {
            setFootInfoCountTextSize(view);
        }
    }

    private final void setFootInfoCountTextSize(TextView textView) {
        try {
            float textSize = textView.getTextSize();
            float f = textSize > 14.0f ? (float) (textSize * 0.7d) : 14.0f;
            if (f > textSize) {
                f = textSize;
            }
            String obj = textView.getText().toString();
            String str = obj;
            int length = str.length() - 1;
            while (true) {
                if (length < 0) {
                    length = -1;
                    break;
                }
                char charAt = str.charAt(length);
                char[] charArray = "(".toCharArray();
                Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
                if (charAt == charArray[0]) {
                    break;
                } else {
                    length--;
                }
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(obj);
            spannableStringBuilder.setSpan(new VerticalCenterSpan(f), length, obj.length(), 34);
            textView.setText(spannableStringBuilder);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    private final boolean isTaskFinish(DeliveryModel deliveryModel) {
        return deliveryModel.getStatus() == TaskStatus.DONE || deliveryModel.getStatus() == TaskStatus.DONE_BEFORE_ARRIVAL || deliveryModel.getStatus() == TaskStatus.CANCEL;
    }

    private final boolean isTrayEnable(int r2) {
        return PalletCountHelper.INSTANCE.isPalletEnable(r2) || this.currentTrayCount == 1;
    }
}
