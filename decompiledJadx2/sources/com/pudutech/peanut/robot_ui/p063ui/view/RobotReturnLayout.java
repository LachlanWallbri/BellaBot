package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.information_system_task.InformationSystemContract;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.TaskModel;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: RobotReturnLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\b\u0010+\u001a\u00020\u0014H\u0002J\u0018\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0002J\b\u0010/\u001a\u00020\u0018H\u0002J\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` J\u0006\u00101\u001a\u00020\tJ\u0010\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\u001fH\u0002J\u0006\u00104\u001a\u00020&J\u0006\u00105\u001a\u00020\u0014J \u00106\u001a\u00020\u00142\u0016\u00107\u001a\u0012\u0012\u0004\u0012\u0002080\u001ej\b\u0012\u0004\u0012\u000208` H\u0002J2\u00109\u001a\u00020\u00182\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u00020;0\u001ej\b\u0012\u0004\u0012\u00020;` 2\b\u0010<\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010%\u001a\u00020&J\u0012\u0010=\u001a\u00020\u00182\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\tH\u0002J\b\u0010@\u001a\u00020\u0018H\u0002J\b\u0010A\u001a\u00020\u0018H\u0002J\b\u0010B\u001a\u00020\u0014H\u0002J\u000e\u0010C\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\tJ\u000e\u0010D\u001a\u00020\u00182\u0006\u0010?\u001a\u00020\tJ\b\u0010E\u001a\u00020\u0018H\u0002J\u0010\u0010F\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*H\u0002J\u0016\u0010G\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\r2\u0006\u0010H\u001a\u000208J\u001a\u0010G\u001a\u00020\u00182\b\u0010-\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010I\u001a\u00020\u0014J.\u0010J\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\r2\u0016\u0010K\u001a\u0012\u0012\u0004\u0012\u0002080\u001ej\b\u0012\u0004\u0012\u000208` 2\u0006\u0010(\u001a\u00020\tJ\u0006\u0010L\u001a\u00020\u0018J\u0006\u0010M\u001a\u00020\u0018J\b\u0010N\u001a\u00020\u0018H\u0002J \u0010N\u001a\u00020\u00182\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u00020;0\u001ej\b\u0012\u0004\u0012\u00020;` H\u0002R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u001ej\b\u0012\u0004\u0012\u00020\u0001` X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0001X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/RobotReturnLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "MAX_MULTI_TABEL_CONUT", "TAG", "", "kotlin.jvm.PlatformType", "currentSelectIndex", "fram", "Lcom/pudutech/peanut/robot_ui/ui/view/FramesSequenceAnimation;", "inputTaskCount", "isBirthdayTheme", "", "isLoadingImage", "onTaskChangeListener", "Lkotlin/Function0;", "", "getOnTaskChangeListener", "()Lkotlin/jvm/functions/Function0;", "setOnTaskChangeListener", "(Lkotlin/jvm/functions/Function0;)V", "palletDates", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "palletItemArray", "robotImageView", "Landroid/widget/ImageView;", "rootView", "sortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "adapterNameViewSize", "index", "textView", "Landroid/widget/TextView;", "checkAllTaskCount", "checkPalletDateInfoAndDelete", "name", "scid", "findNextSelect", "getAllTask", "getInputTaskCount", "getPalletText", "t", "getSortType", "hasTask", "hasTrayOrders", "foodInfo", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "initData", "list", "Lcom/pudutech/peanut/robot_ui/bean/TaskModel;", "currentTaskId", "initView", "isTrayEnable", "i", "resetLayout", "resetPalletDate", "selectNextEmptyPallet", "selectTray", "setInputTaskCount", "setListener", "setSanCountTextSize", "setTaskName", "info", "multiTable", "setTrayOrders", "orders", "start", "startShowAnimation", "updateViewState", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotReturnLayout extends RelativeLayout {
    private final int MAX_MULTI_TABEL_CONUT;
    private final String TAG;
    private HashMap _$_findViewCache;
    private int currentSelectIndex;
    private FramesSequenceAnimation fram;
    private int inputTaskCount;
    private boolean isBirthdayTheme;
    private boolean isLoadingImage;
    private Function0<Unit> onTaskChangeListener;
    private final ArrayList<TrayModel> palletDates;
    private final ArrayList<RelativeLayout> palletItemArray;
    private ImageView robotImageView;
    private RelativeLayout rootView;
    private MoveSortType sortType;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final Function0<Unit> getOnTaskChangeListener() {
        return this.onTaskChangeListener;
    }

    public final void setOnTaskChangeListener(Function0<Unit> function0) {
        this.onTaskChangeListener = function0;
    }

    public RobotReturnLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.inputTaskCount = 3;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = MoveSortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        initView(getContext());
    }

    public RobotReturnLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.inputTaskCount = 3;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = MoveSortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        initView(getContext());
    }

    public RobotReturnLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.inputTaskCount = 3;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = MoveSortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        initView(getContext());
    }

    private final void resetPalletDate() {
        this.palletDates.clear();
        int size = this.palletItemArray.size();
        for (int i = 0; i < size; i++) {
            this.palletDates.add(new TrayModel());
        }
    }

    private final void setListener() {
        int i = 0;
        for (Object obj : this.palletItemArray) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) obj;
            relativeLayout.setTag(Integer.valueOf(i));
            View findViewById = relativeLayout.findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "item.findViewById<MarqueeTextView>(R.id.task_name)");
            ((MarqueeTextView) findViewById).setTag(Integer.valueOf(i));
            i = i2;
        }
    }

    private final void updateViewState(ArrayList<TaskModel> list) {
        int i = 0;
        for (Object obj : this.palletItemArray) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) obj;
            if (i2 <= this.inputTaskCount && relativeLayout.getVisibility() != 0) {
                relativeLayout.setVisibility(0);
            }
            MarqueeTextView marqueeTextView = (MarqueeTextView) relativeLayout.findViewById(C5508R.id.task_name);
            LinearLayout llReturnBg = (LinearLayout) relativeLayout.findViewById(C5508R.id.llReturnBg);
            ImageView imageView = (ImageView) relativeLayout.findViewById(C5508R.id.ivImg);
            TextView tvName = (TextView) relativeLayout.findViewById(C5508R.id.tvName);
            adapterNameViewSize(i, marqueeTextView);
            if (list.size() > i) {
                int isReturn = list.get(i).isReturn();
                if (isReturn == 1) {
                    Intrinsics.checkExpressionValueIsNotNull(llReturnBg, "llReturnBg");
                    llReturnBg.setVisibility(0);
                    llReturnBg.setBackgroundResource(C5508R.drawable.ic_icon_refund_bg);
                    Context context = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    imageView.setImageDrawable(context.getResources().getDrawable(C5508R.drawable.ic_icon_refund));
                    Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
                    tvName.setText(getContext().getString(C5508R.string.have_return_dish));
                } else if (isReturn == 3) {
                    Intrinsics.checkExpressionValueIsNotNull(llReturnBg, "llReturnBg");
                    llReturnBg.setVisibility(0);
                    Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
                    tvName.setText(getContext().getString(C5508R.string.peisong_cancel));
                    llReturnBg.setBackgroundResource(C5508R.drawable.ic_icon_delivered_bg);
                    Context context2 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    imageView.setImageDrawable(context2.getResources().getDrawable(C5508R.drawable.ic_icon_cancel));
                } else if (isReturn == 5) {
                    Intrinsics.checkExpressionValueIsNotNull(llReturnBg, "llReturnBg");
                    llReturnBg.setVisibility(0);
                    llReturnBg.setBackgroundResource(C5508R.drawable.icon_delivered_bg);
                    Context context3 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                    imageView.setImageDrawable(context3.getResources().getDrawable(C5508R.drawable.ic_icon_delivered));
                    Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
                    tvName.setText(getContext().getString(C5508R.string.pdStr2_14));
                } else {
                    Intrinsics.checkExpressionValueIsNotNull(llReturnBg, "llReturnBg");
                    llReturnBg.setVisibility(8);
                }
                Intrinsics.checkExpressionValueIsNotNull(this.palletDates.get(i), "palletDates[index]");
            }
            i = i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(C5508R.id.pallet_layout));
        constraintSet.clear(C5508R.id.layout_return_item_1, 1);
        constraintSet.clear(C5508R.id.layout_return_item_1, 2);
        constraintSet.clear(C5508R.id.layout_return_item_1, 3);
        constraintSet.clear(C5508R.id.layout_return_item_1, 4);
        if (this.inputTaskCount == 1) {
            constraintSet.connect(C5508R.id.layout_return_item_1, 4, C5508R.id.layout_return_item_2, 3);
            constraintSet.connect(C5508R.id.layout_return_item_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.layout_return_item_1, 2, 0, 2);
        } else {
            constraintSet.connect(C5508R.id.layout_return_item_1, 4, C5508R.id.layout_return_item_2, 3);
            constraintSet.connect(C5508R.id.layout_return_item_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.layout_return_item_1, 2, 0, 2);
            constraintSet.connect(C5508R.id.layout_return_item_1, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C5508R.id.pallet_layout));
    }

    private final void updateViewState() {
        int i = 0;
        for (Object obj : this.palletItemArray) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) obj;
            if (i2 <= this.inputTaskCount && relativeLayout.getVisibility() != 0) {
                relativeLayout.setVisibility(0);
            }
            MarqueeTextView marqueeTextView = (MarqueeTextView) relativeLayout.findViewById(C5508R.id.task_name);
            adapterNameViewSize(i, marqueeTextView);
            Intrinsics.checkExpressionValueIsNotNull(this.palletDates.get(i), "palletDates[index]");
            i = i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(C5508R.id.pallet_layout));
        constraintSet.clear(C5508R.id.layout_return_item_1, 1);
        constraintSet.clear(C5508R.id.layout_return_item_1, 2);
        constraintSet.clear(C5508R.id.layout_return_item_1, 3);
        constraintSet.clear(C5508R.id.layout_return_item_1, 4);
        if (this.inputTaskCount == 1) {
            constraintSet.connect(C5508R.id.layout_return_item_1, 4, C5508R.id.layout_return_item_2, 3);
            constraintSet.connect(C5508R.id.layout_return_item_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.layout_return_item_1, 2, 0, 2);
        } else {
            constraintSet.connect(C5508R.id.layout_return_item_1, 4, C5508R.id.layout_return_item_2, 3);
            constraintSet.connect(C5508R.id.layout_return_item_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.layout_return_item_1, 2, 0, 2);
            constraintSet.connect(C5508R.id.layout_return_item_1, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C5508R.id.pallet_layout));
    }

    private final void adapterNameViewSize(int index, TextView textView) {
        if (textView != null) {
            String obj = textView.getText().toString();
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            textView.setTextSize(context.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_s));
            TrayModel trayModel = this.palletDates.get(index);
            Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[index]");
            TrayModel trayModel2 = trayModel;
            if (!trayModel2.getAllDestinations().isEmpty()) {
                Context context2 = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                UiUtils.adjustTvTextSize(textView, (int) context2.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_w), obj, 22);
                if (trayModel2.getAllDestinations().size() == 1) {
                    ArrayList<InformationSystemContract.OrderInfo> foodInfo = trayModel2.getAllDestinations().get(0).getFoodInfo();
                    if ((foodInfo != null ? foodInfo.size() : 0) > 0) {
                        setSanCountTextSize(textView);
                    }
                }
            }
        }
    }

    private final void setSanCountTextSize(TextView textView) {
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

    public final void setInputTaskCount(int i) {
        this.inputTaskCount = i;
    }

    public final int getInputTaskCount() {
        return this.inputTaskCount;
    }

    public final void startShowAnimation() {
        resetLayout();
    }

    private final void resetLayout() {
        this.currentSelectIndex = 0;
        if (!isTrayEnable(this.currentSelectIndex)) {
            findNextSelect();
        }
        for (RelativeLayout relativeLayout : this.palletItemArray) {
            if (relativeLayout.getVisibility() == 0) {
                relativeLayout.setVisibility(8);
            }
            MarqueeTextView textView = (MarqueeTextView) relativeLayout.findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText("");
        }
        resetPalletDate();
    }

    public final void start() {
        resetLayout();
        updateViewState();
    }

    public static /* synthetic */ void initData$default(RobotReturnLayout robotReturnLayout, ArrayList arrayList, String str, MoveSortType moveSortType, int i, Object obj) {
        if ((i & 4) != 0) {
            moveSortType = MoveSortType.AUTO;
        }
        robotReturnLayout.initData(arrayList, str, moveSortType);
    }

    public final void initData(ArrayList<TaskModel> list, String currentTaskId, MoveSortType sortType) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        int i = 0;
        Pdlog.m3273d(this.TAG, "initData : list = " + list + " ; currentTaskId = " + currentTaskId);
        resetPalletDate();
        this.sortType = sortType;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TaskModel taskModel = (TaskModel) obj;
            if (i > this.palletItemArray.size()) {
                return;
            }
            this.palletDates.set(i, taskModel.getTrayModel());
            View findViewById = this.palletItemArray.get(i).findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[index].f…TextView>(R.id.task_name)");
            ((MarqueeTextView) findViewById).setText(taskModel.getContent());
            if (currentTaskId != null && taskModel.getTrayModel().getDeliveryModel(currentTaskId) != null) {
                this.currentSelectIndex = i;
            }
            i = i2;
        }
        updateViewState(list);
    }

    public final boolean selectTray(int i) {
        if (i + 1 > this.inputTaskCount || !isTrayEnable(i)) {
            return false;
        }
        if (this.currentSelectIndex == i) {
            return true;
        }
        this.currentSelectIndex = i;
        updateViewState();
        return true;
    }

    public final MoveSortType getSortType() {
        return this.sortType;
    }

    private final String getPalletText(TrayModel t) {
        StringBuilder sb = new StringBuilder();
        if (t.getAllDestinations().size() == 1) {
            DeliveryModel deliveryModel = t.getAllDestinations().get(0);
            Intrinsics.checkExpressionValueIsNotNull(deliveryModel, "t.allDestinations[0]");
            DeliveryModel deliveryModel2 = deliveryModel;
            sb.append(deliveryModel2.getDestination());
            ArrayList<InformationSystemContract.OrderInfo> foodInfo = deliveryModel2.getFoodInfo();
            if (((foodInfo == null || foodInfo.isEmpty()) ? 1 : 0) == 0) {
                ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = deliveryModel2.getFoodInfo();
                if (foodInfo2 == null) {
                    Intrinsics.throwNpe();
                }
                if (hasTrayOrders(foodInfo2)) {
                    sb.append("(");
                    sb.append("...");
                    sb.append(")");
                } else {
                    sb.append("(x");
                    ArrayList<InformationSystemContract.OrderInfo> foodInfo3 = deliveryModel2.getFoodInfo();
                    if (foodInfo3 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(foodInfo3.size());
                    sb.append(")");
                }
            }
        } else if (t.getAllDestinations().size() > 1) {
            for (Object obj : t.getAllDestinations()) {
                int i = r2 + 1;
                if (r2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                DeliveryModel deliveryModel3 = (DeliveryModel) obj;
                if (r2 == 0) {
                    sb.append(deliveryModel3.getDestination());
                } else {
                    sb.append(", ");
                    sb.append(deliveryModel3.getDestination());
                }
                r2 = i;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "text.toString()");
        return sb2;
    }

    public final void setTrayOrders(String name, ArrayList<InformationSystemContract.OrderInfo> orders, int index) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(orders, "orders");
        if (index >= 0) {
            this.currentSelectIndex = index;
        }
        TrayModel trayModel = this.palletDates.get(this.currentSelectIndex);
        Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[currentSelectIndex]");
        TrayModel trayModel2 = trayModel;
        trayModel2.getAllDestinations().clear();
        trayModel2.getAllDestinations().add(new DeliveryModel(name, orders, null, null, null, 28, null));
        View findViewById = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[currentS…TextView>(R.id.task_name)");
        ((MarqueeTextView) findViewById).setText(getPalletText(trayModel2));
        findNextSelect();
        updateViewState();
        Function0<Unit> function0 = this.onTaskChangeListener;
        if (function0 != null) {
            function0.invoke();
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

    public final void setTaskName(String name, InformationSystemContract.OrderInfo info) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(info, "info");
        TrayModel trayModel = this.palletDates.get(this.currentSelectIndex);
        Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[currentSelectIndex]");
        TrayModel trayModel2 = trayModel;
        if (!trayModel2.getAllDestinations().isEmpty()) {
            ArrayList<InformationSystemContract.OrderInfo> foodInfo = trayModel2.getAllDestinations().get(0).getFoodInfo();
            if (foodInfo == null) {
                foodInfo = new ArrayList<>();
            }
            if (hasTrayOrders(foodInfo)) {
                trayModel2.getAllDestinations().clear();
            }
        }
        if (trayModel2.getAllDestinations().isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(info);
            trayModel2.getAllDestinations().add(new DeliveryModel(name, arrayList, null, null, null, 28, null));
            View findViewById = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[currentS…TextView>(R.id.task_name)");
            ((MarqueeTextView) findViewById).setText(getPalletText(trayModel2));
            checkPalletDateInfoAndDelete(name, info.getScid());
        } else {
            if (trayModel2.getAllDestinations().size() == 1 && Intrinsics.areEqual(trayModel2.getAllDestinations().get(0).getDestination(), name)) {
                ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = trayModel2.getAllDestinations().get(0).getFoodInfo();
                if (!(foodInfo2 == null || foodInfo2.isEmpty())) {
                    ArrayList<InformationSystemContract.OrderInfo> foodInfo3 = trayModel2.getAllDestinations().get(0).getFoodInfo();
                    if (foodInfo3 == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean z = false;
                    for (InformationSystemContract.OrderInfo orderInfo : foodInfo3) {
                        if (!z) {
                            z = Intrinsics.areEqual(orderInfo.getScid(), info.getScid());
                        }
                    }
                    if (z) {
                        Pdlog.m3273d(this.TAG, "setTaskName same " + info);
                        return;
                    }
                    ArrayList<InformationSystemContract.OrderInfo> foodInfo4 = trayModel2.getAllDestinations().get(0).getFoodInfo();
                    if (foodInfo4 != null) {
                        foodInfo4.add(info);
                    }
                    View findViewById2 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[currentS…TextView>(R.id.task_name)");
                    ((MarqueeTextView) findViewById2).setText(getPalletText(trayModel2));
                    checkPalletDateInfoAndDelete(name, info.getScid());
                }
            }
            if (selectNextEmptyPallet()) {
                TrayModel trayModel3 = this.palletDates.get(this.currentSelectIndex);
                Intrinsics.checkExpressionValueIsNotNull(trayModel3, "palletDates[currentSelectIndex]");
                TrayModel trayModel4 = trayModel3;
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(info);
                trayModel4.getAllDestinations().add(new DeliveryModel(name, arrayList2, null, null, null, 28, null));
                View findViewById3 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
                Intrinsics.checkExpressionValueIsNotNull(findViewById3, "palletItemArray[currentS…TextView>(R.id.task_name)");
                ((MarqueeTextView) findViewById3).setText(getPalletText(trayModel4));
                checkPalletDateInfoAndDelete(name, info.getScid());
            }
        }
        updateViewState();
        Function0<Unit> function0 = this.onTaskChangeListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public static /* synthetic */ void setTaskName$default(RobotReturnLayout robotReturnLayout, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        robotReturnLayout.setTaskName(str, z);
    }

    public final void setTaskName(String name, boolean multiTable) {
        String str = name;
        if (str == null || StringsKt.isBlank(str)) {
            Pdlog.m3274e(this.TAG, "setTaskName is null " + name);
            return;
        }
        this.sortType = MoveSortType.AUTO;
        TrayModel trayModel = this.palletDates.get(this.currentSelectIndex);
        Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[currentSelectIndex]");
        TrayModel trayModel2 = trayModel;
        if (!multiTable) {
            if (!trayModel2.getAllDestinations().isEmpty()) {
                DeliveryModel deliveryModel = trayModel2.getAllDestinations().get(0);
                Intrinsics.checkExpressionValueIsNotNull(deliveryModel, "trayModel.allDestinations[0]");
                DeliveryModel deliveryModel2 = deliveryModel;
                trayModel2.getAllDestinations().clear();
                deliveryModel2.setDestination(name);
                trayModel2.getAllDestinations().add(deliveryModel2);
            } else {
                trayModel2.getAllDestinations().add(new DeliveryModel(name, null, null, null, null, 28, null));
            }
            View findViewById = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[currentS…TextView>(R.id.task_name)");
            ((MarqueeTextView) findViewById).setText(getPalletText(trayModel2));
            findNextSelect();
        } else if (checkAllTaskCount()) {
            if (trayModel2.getDeliveryModel(name) != null) {
                Pdlog.m3273d(this.TAG, "setTaskName , has same name : " + name);
                return;
            }
            trayModel2.getAllDestinations().add(0, new DeliveryModel(name, null, null, null, null, 28, null));
            View findViewById2 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[currentS…TextView>(R.id.task_name)");
            ((MarqueeTextView) findViewById2).setText(getPalletText(trayModel2));
        } else {
            Context context = getContext();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getContext().getString(C5508R.string.pdStr2_29);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr2_29)");
            Object[] objArr = {Integer.valueOf(this.MAX_MULTI_TABEL_CONUT)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(context, format, new Object[0]);
            return;
        }
        updateViewState();
        Function0<Unit> function0 = this.onTaskChangeListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    private final boolean checkAllTaskCount() {
        Iterator<T> it = this.palletDates.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((TrayModel) it.next()).getAllDestinations().size();
            if (i >= this.MAX_MULTI_TABEL_CONUT) {
                return false;
            }
        }
        return true;
    }

    private final void checkPalletDateInfoAndDelete(final String name, final String scid) {
        ArrayList<InformationSystemContract.OrderInfo> foodInfo;
        int i = 0;
        for (Object obj : this.palletDates) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            if (i != this.currentSelectIndex && (!trayModel.getAllDestinations().isEmpty()) && Intrinsics.areEqual(trayModel.getAllDestinations().get(0).getDestination(), name) && (foodInfo = trayModel.getAllDestinations().get(0).getFoodInfo()) != null) {
                if (foodInfo == null) {
                    Intrinsics.throwNpe();
                }
                foodInfo.removeIf(new Predicate<InformationSystemContract.OrderInfo>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotReturnLayout$checkPalletDateInfoAndDelete$$inlined$forEachIndexed$lambda$1
                    @Override // java.util.function.Predicate
                    public final boolean test(InformationSystemContract.OrderInfo it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return Intrinsics.areEqual(it.getScid(), scid) && it.getOrderType() != InformationSystemContract.OrderInfo.Type.TrayOrder;
                    }
                });
                if (foodInfo.size() == 0) {
                    View findViewById = this.palletItemArray.get(i).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[index].f…TextView>(R.id.task_name)");
                    ((MarqueeTextView) findViewById).setText((CharSequence) null);
                    this.palletDates.get(i).getAllDestinations().clear();
                } else {
                    View findViewById2 = this.palletItemArray.get(i).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[index].f…TextView>(R.id.task_name)");
                    ((MarqueeTextView) findViewById2).setText(getPalletText(trayModel));
                }
            }
            i = i2;
        }
    }

    public final ArrayList<TrayModel> getAllTask() {
        return this.palletDates;
    }

    public final boolean hasTask() {
        ArrayList<TrayModel> allTask = getAllTask();
        if ((allTask instanceof Collection) && allTask.isEmpty()) {
            return false;
        }
        Iterator<T> it = allTask.iterator();
        while (it.hasNext()) {
            if (!((TrayModel) it.next()).getAllDestinations().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private final boolean selectNextEmptyPallet() {
        RobotReturnLayout robotReturnLayout = this;
        Iterator<T> it = robotReturnLayout.palletDates.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TrayModel) next).getAllDestinations().isEmpty() && i < robotReturnLayout.inputTaskCount && robotReturnLayout.isTrayEnable(i)) {
                break;
            }
            i = i2;
        }
        if (i <= -1) {
            return false;
        }
        this.currentSelectIndex = i;
        updateViewState();
        return true;
    }

    private final void findNextSelect() {
        int i = this.currentSelectIndex + 1;
        int i2 = this.inputTaskCount;
        if (i >= i2) {
            return;
        }
        while (i < i2) {
            if (isTrayEnable(i)) {
                this.currentSelectIndex = i;
                return;
            }
            i++;
        }
    }

    private final boolean isTrayEnable(int i) {
        return PalletCountHelper.INSTANCE.isPalletEnable(i) || this.inputTaskCount == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initView(Context context) {
        if (context != null) {
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            LayoutInflater layoutInflater = (LayoutInflater) systemService;
            if (layoutInflater != null) {
                layoutInflater.inflate(C5508R.layout.layout_robot_return_task, this);
            }
        }
        View findViewById = findViewById(C5508R.id.root_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.root_view)");
        this.rootView = (RelativeLayout) findViewById;
        View findViewById2 = findViewById(C5508R.id.robot_img);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.robot_img)");
        this.robotImageView = (ImageView) findViewById2;
        this.palletItemArray.add(findViewById(C5508R.id.layout_return_item_1));
        this.palletItemArray.add(findViewById(C5508R.id.layout_return_item_2));
        this.palletItemArray.add(findViewById(C5508R.id.layout_return_item_3));
        this.palletItemArray.add(findViewById(C5508R.id.layout_return_item_4));
        this.palletItemArray.add(findViewById(C5508R.id.layout_return_item_5));
        resetPalletDate();
        setListener();
    }
}
