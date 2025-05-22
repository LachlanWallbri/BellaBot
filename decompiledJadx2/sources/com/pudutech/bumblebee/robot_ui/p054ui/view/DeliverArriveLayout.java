package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.ArriveDishInfoAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: DeliverArriveLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 A2\u00020\u0001:\u0002ABB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u001d\u001a\u00020\u0012J \u0010\u001e\u001a\u00020\t2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020 0\u001bj\b\u0012\u0004\u0012\u00020 `\u001cH\u0002J \u0010!\u001a\u00020\u00122\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020#0\u001bj\b\u0012\u0004\u0012\u00020#`\u001cH\u0002J\u0006\u0010$\u001a\u00020\u0015J\b\u0010%\u001a\u00020\u0015H\u0002J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020 H\u0002J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\tH\u0002J\u0010\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,H\u0002J(\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\f2\u0016\u0010/\u001a\u0012\u0012\u0004\u0012\u00020#0\u001bj\b\u0012\u0004\u0012\u00020#`\u001cH\u0002J\u0010\u00100\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,H\u0002J\u000e\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u00020\u00152\u0006\u00102\u001a\u000203J(\u00105\u001a\u00020\u00152\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002060\u001bj\b\u0012\u0004\u0012\u000206`\u001c2\u0006\u00107\u001a\u00020\fH\u0002J\u0018\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020,2\u0006\u0010:\u001a\u000206H\u0002J&\u0010;\u001a\u00020\u00152\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002060\u001bj\b\u0012\u0004\u0012\u000206`\u001c2\u0006\u00107\u001a\u00020\fJ&\u0010<\u001a\u00020\u00152\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002060\u001bj\b\u0012\u0004\u0012\u000206`\u001c2\u0006\u00107\u001a\u00020\fJ\u0010\u0010=\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\tH\u0002J\u0010\u0010>\u001a\u00020\u00152\b\b\u0001\u0010?\u001a\u00020\tJ\b\u0010@\u001a\u00020\u0015H\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u001bj\b\u0012\u0004\u0012\u00020\u0001`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverArriveLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "arriveDishInfoAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/ArriveDishInfoAdapter;", "currentTrayCount", "isOnArrivingStatus", "", "onArrivingLayoutClick", "Lkotlin/Function0;", "", "getOnArrivingLayoutClick", "()Lkotlin/jvm/functions/Function0;", "setOnArrivingLayoutClick", "(Lkotlin/jvm/functions/Function0;)V", "trayInfoViews", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getIsNeedRecycle", "getLeftTableCount", "list", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "hasTrayOrders", "foodInfo", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "hideView", "initView", "isTaskFinish", "deliveryModel", "isTrayEnable", "int", "setCountTextSize", "textView", "Landroid/widget/TextView;", "setDishInfoContent", TypedValues.Attributes.S_TARGET, "orderInfos", "setFootInfoCountTextSize", "setOnClickFinishListener", "onLazyClickListener", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "setOnClickOutletSelectListener", "setTayInfo", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "currentTableId", "setTrayText", "view", "t", "showArrivedLayout", "showArrivingLayout", "showTrayCount", "switchTheme", "theme", "updateRecycleLayout", "Companion", "THEME", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverArriveLayout extends RelativeLayout {
    public static final int THEME_BIRTHDAY = 2;
    public static final int THEME_DIRECTLY = 1;
    public static final int THEME_NORMAL = 0;
    private final String TAG;
    private HashMap _$_findViewCache;
    private ArriveDishInfoAdapter arriveDishInfoAdapter;
    private int currentTrayCount;
    private boolean isOnArrivingStatus;
    private Function0<Unit> onArrivingLayoutClick;
    private final ArrayList<RelativeLayout> trayInfoViews;

    /* compiled from: DeliverArriveLayout.kt */
    @Retention(RetentionPolicy.SOURCE)
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverArriveLayout$THEME;", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public @interface THEME {
    }

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

    public final Function0<Unit> getOnArrivingLayoutClick() {
        return this.onArrivingLayoutClick;
    }

    public final void setOnArrivingLayoutClick(Function0<Unit> function0) {
        this.onArrivingLayoutClick = function0;
    }

    public DeliverArriveLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        initView();
    }

    public DeliverArriveLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        initView();
    }

    public DeliverArriveLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        initView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initView() {
        Context context = getContext();
        if (context != null) {
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            LayoutInflater layoutInflater = (LayoutInflater) systemService;
            if (layoutInflater != null) {
                layoutInflater.inflate(C4188R.layout.layout_deliver_arrive, this);
            }
        }
        this.trayInfoViews.add(findViewById(C4188R.id.arrive_tray_1));
        this.trayInfoViews.add(findViewById(C4188R.id.arrive_tray_2));
        this.trayInfoViews.add(findViewById(C4188R.id.arrive_tray_3));
        this.trayInfoViews.add(findViewById(C4188R.id.arrive_tray_4));
        this.trayInfoViews.add(findViewById(C4188R.id.arrive_tray_5));
        setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverArriveLayout$initView$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                boolean z;
                String str;
                String str2;
                z = DeliverArriveLayout.this.isOnArrivingStatus;
                if (z) {
                    str2 = DeliverArriveLayout.this.TAG;
                    Pdlog.m3273d(str2, "onArrivingLayoutClick");
                    Function0<Unit> onArrivingLayoutClick = DeliverArriveLayout.this.getOnArrivingLayoutClick();
                    if (onArrivingLayoutClick != null) {
                        onArrivingLayoutClick.invoke();
                        return;
                    }
                    return;
                }
                str = DeliverArriveLayout.this.TAG;
                Pdlog.m3273d(str, "onArrivingLayoutClick failed , is Arrived status");
            }
        });
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.arriveDishInfoAdapter = new ArriveDishInfoAdapter(context2);
        RecyclerView deliver_dish_recycle_view = (RecyclerView) _$_findCachedViewById(C4188R.id.deliver_dish_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(deliver_dish_recycle_view, "deliver_dish_recycle_view");
        deliver_dish_recycle_view.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView deliver_dish_recycle_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.deliver_dish_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(deliver_dish_recycle_view2, "deliver_dish_recycle_view");
        ArriveDishInfoAdapter arriveDishInfoAdapter = this.arriveDishInfoAdapter;
        if (arriveDishInfoAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arriveDishInfoAdapter");
        }
        deliver_dish_recycle_view2.setAdapter(arriveDishInfoAdapter);
        updateRecycleLayout();
    }

    private final void updateRecycleLayout() {
        if (Constans.INSTANCE.getRecyclingPlateSwitch()) {
            Pdlog.m3273d(this.TAG, "updateRecycleLayout true");
            LinearLayout recycle_switch_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.recycle_switch_layout);
            Intrinsics.checkExpressionValueIsNotNull(recycle_switch_layout, "recycle_switch_layout");
            recycle_switch_layout.setVisibility(0);
            boolean z = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_deliver_recycle_plate_switch", false);
            Switch recycle_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.recycle_mode_switch);
            Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch, "recycle_mode_switch");
            recycle_mode_switch.setChecked(z);
            ((Switch) _$_findCachedViewById(C4188R.id.recycle_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverArriveLayout$updateRecycleLayout$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                    invoke(compoundButton, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(CompoundButton buttonView, boolean z2) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                    str = DeliverArriveLayout.this.TAG;
                    Pdlog.m3273d(str, "updateRecycleLayout recycle_mode_switch " + z2);
                    SpUtils.set(RobotContext.INSTANCE.getContext(), "key_deliver_recycle_plate_switch", z2);
                }
            }, 7, null));
        } else {
            Pdlog.m3273d(this.TAG, "updateRecycleLayout false");
            LinearLayout recycle_switch_layout2 = (LinearLayout) _$_findCachedViewById(C4188R.id.recycle_switch_layout);
            Intrinsics.checkExpressionValueIsNotNull(recycle_switch_layout2, "recycle_switch_layout");
            recycle_switch_layout2.setVisibility(8);
        }
        if (SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false)) {
            Button btn_finish = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
            Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
            btn_finish.setBackground(getContext().getDrawable(C4188R.drawable.lottery_btn));
            String string = getContext().getString(C4188R.string.pdStr7_110);
            Button btn_finish2 = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
            Intrinsics.checkExpressionValueIsNotNull(btn_finish2, "btn_finish");
            btn_finish2.setText(string);
            Context context = getContext();
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            UiUtils.adjustTvTextSize((Button) _$_findCachedViewById(C4188R.id.btn_finish), SizeUtils.dp2px(context, context2.getResources().getDimension(C4188R.dimen.deliver_arrive_btn_w)), string);
        }
    }

    public final void setOnClickFinishListener(OnLazyClickListener onLazyClickListener) {
        Intrinsics.checkParameterIsNotNull(onLazyClickListener, "onLazyClickListener");
        ((Button) _$_findCachedViewById(C4188R.id.btn_finish)).setOnClickListener(onLazyClickListener);
    }

    private final void setDishInfoContent(String target, ArrayList<InformationSystemContract.OrderInfo> orderInfos) {
        if (orderInfos != null && orderInfos.size() > 0) {
            CardView small_info_layout = (CardView) _$_findCachedViewById(C4188R.id.small_info_layout);
            Intrinsics.checkExpressionValueIsNotNull(small_info_layout, "small_info_layout");
            small_info_layout.setVisibility(0);
            CardView small_list_info_layout = (CardView) _$_findCachedViewById(C4188R.id.small_list_info_layout);
            Intrinsics.checkExpressionValueIsNotNull(small_list_info_layout, "small_list_info_layout");
            small_list_info_layout.setVisibility(0);
            CardView arrive_task_tip_layout = (CardView) _$_findCachedViewById(C4188R.id.arrive_task_tip_layout);
            Intrinsics.checkExpressionValueIsNotNull(arrive_task_tip_layout, "arrive_task_tip_layout");
            arrive_task_tip_layout.setVisibility(4);
            ((TextView) _$_findCachedViewById(C4188R.id.small_table_id)).setTextSize(2, 104.0f);
            TextView small_table_id = (TextView) _$_findCachedViewById(C4188R.id.small_table_id);
            Intrinsics.checkExpressionValueIsNotNull(small_table_id, "small_table_id");
            small_table_id.setText(target);
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.small_table_id), (int) context.getResources().getDimension(C4188R.dimen.deliver_arrive_small_info_id_w), target);
            ArriveDishInfoAdapter arriveDishInfoAdapter = this.arriveDishInfoAdapter;
            if (arriveDishInfoAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arriveDishInfoAdapter");
            }
            arriveDishInfoAdapter.setNewData(orderInfos);
            return;
        }
        CardView small_info_layout2 = (CardView) _$_findCachedViewById(C4188R.id.small_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(small_info_layout2, "small_info_layout");
        small_info_layout2.setVisibility(8);
        CardView small_list_info_layout2 = (CardView) _$_findCachedViewById(C4188R.id.small_list_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(small_list_info_layout2, "small_list_info_layout");
        small_list_info_layout2.setVisibility(8);
        CardView arrive_task_tip_layout2 = (CardView) _$_findCachedViewById(C4188R.id.arrive_task_tip_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_task_tip_layout2, "arrive_task_tip_layout");
        arrive_task_tip_layout2.setVisibility(0);
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.table_id);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        textView.setTextSize(0, context2.getResources().getDimension(C4188R.dimen.delivery_arrive_content_target_tv_s));
        TextView table_id = (TextView) _$_findCachedViewById(C4188R.id.table_id);
        Intrinsics.checkExpressionValueIsNotNull(table_id, "table_id");
        table_id.setText(target);
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.table_id), ((int) context3.getResources().getDimension(C4188R.dimen.delivery_arrive_content_ll_w)) - 50, target);
    }

    private final void setTayInfo(ArrayList<TrayModel> list, String currentTableId) {
        Pdlog.m3273d(this.TAG, "setTrayInfo = " + list + " , currentMovingText = " + currentTableId);
        ArrayList<InformationSystemContract.OrderInfo> arrayList = new ArrayList<>();
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
            TextView textView = (TextView) relativeLayout2.findViewById(C4188R.id.task_name);
            CardView cardView = (CardView) relativeLayout2.findViewById(C4188R.id.card_view);
            TextView redCountTv = (TextView) relativeLayout2.findViewById(C4188R.id.red_count_tv);
            int leftTableCount = getLeftTableCount(trayModel.getAllDestinations());
            if (leftTableCount > 1) {
                Intrinsics.checkExpressionValueIsNotNull(redCountTv, "redCountTv");
                redCountTv.setVisibility(0);
                redCountTv.setText(String.valueOf(leftTableCount));
            } else {
                Intrinsics.checkExpressionValueIsNotNull(redCountTv, "redCountTv");
                redCountTv.setVisibility(8);
            }
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            setTrayText(textView, trayModel);
            DeliveryModel current = trayModel.getCurrent();
            if (!isTrayEnable(i)) {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.transparent));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f = 0;
                cardView.setCardElevation(f);
                cardView.setMaxCardElevation(f);
                textView.setBackgroundResource(C4188R.drawable.settings_tray_disable);
            } else if (current != null && current.getStatus() != TaskStatus.DONE && current.getStatus() != TaskStatus.DONE_BEFORE_ARRIVAL) {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.theme_main_color));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                cardView.setCardElevation(SizeUtils.dp2px(getContext(), 2.0f));
                cardView.setMaxCardElevation(SizeUtils.dp2px(getContext(), 3.0f));
                Sdk27PropertiesKt.setTextColor(textView, -1);
                ArrayList<InformationSystemContract.OrderInfo> foodInfo = current.getFoodInfo();
                if (foodInfo != null) {
                    Iterator<T> it = foodInfo.iterator();
                    while (it.hasNext()) {
                        arrayList.add((InformationSystemContract.OrderInfo) it.next());
                    }
                }
                textView.setBackgroundResource(0);
            } else {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.item_pallet_bg_normal));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f2 = 0;
                cardView.setCardElevation(f2);
                cardView.setMaxCardElevation(f2);
                Sdk27PropertiesKt.setTextColor(textView, getResources().getColor(C4188R.color.font_color_1));
                textView.setBackgroundResource(0);
            }
            i = i2;
        }
        setDishInfoContent(currentTableId, arrayList);
    }

    private final boolean isTrayEnable(int r2) {
        return PalletCountHelper.INSTANCE.isPalletEnable(r2) || this.currentTrayCount == 1;
    }

    private final void setCountTextSize(TextView textView) {
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

    private final int getLeftTableCount(ArrayList<DeliveryModel> list) {
        int i = 0;
        for (DeliveryModel deliveryModel : list) {
            if (deliveryModel.getStatus() != TaskStatus.DONE_BEFORE_ARRIVAL && deliveryModel.getStatus() != TaskStatus.DONE) {
                i++;
            }
        }
        return i;
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

    /* JADX WARN: Removed duplicated region for block: B:19:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setTrayText(TextView view, TrayModel t) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        if (t.getAllDestinations().size() == 1) {
            DeliveryModel deliveryModel = t.getAllDestinations().get(0);
            Intrinsics.checkExpressionValueIsNotNull(deliveryModel, "t.allDestinations[0]");
            DeliveryModel deliveryModel2 = deliveryModel;
            if (!isTaskFinish(deliveryModel2)) {
                sb.append(deliveryModel2.getDestination());
                ArrayList<InformationSystemContract.OrderInfo> foodInfo = deliveryModel2.getFoodInfo();
                if (!(foodInfo == null || foodInfo.isEmpty())) {
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
                    view.setText(sb.toString());
                    Context context = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    view.setTextSize(0, context.getResources().getDimension(C4188R.dimen.delivery_arrive_robot_tray_item_tv_s));
                    Context context2 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    UiUtils.adjustTvTextSize(view, (int) context2.getResources().getDimension(C4188R.dimen.delivery_arrive_robot_tray_item_tv_w), sb.toString(), 22);
                    if (z) {
                        return;
                    }
                    setFootInfoCountTextSize(view);
                    return;
                }
            }
        } else if (t.getAllDestinations().size() > 1) {
            int i = 0;
            for (Object obj : t.getAllDestinations()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                DeliveryModel deliveryModel3 = (DeliveryModel) obj;
                if (!isTaskFinish(deliveryModel3)) {
                    if (sb.length() == 0) {
                        sb.append(deliveryModel3.getDestination());
                    } else {
                        sb.append(", ");
                        sb.append(deliveryModel3.getDestination());
                    }
                }
                i = i2;
            }
        }
        z = false;
        view.setText(sb.toString());
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        view.setTextSize(0, context3.getResources().getDimension(C4188R.dimen.delivery_arrive_robot_tray_item_tv_s));
        Context context22 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context22, "context");
        UiUtils.adjustTvTextSize(view, (int) context22.getResources().getDimension(C4188R.dimen.delivery_arrive_robot_tray_item_tv_w), sb.toString(), 22);
        if (z) {
        }
    }

    private final boolean isTaskFinish(DeliveryModel deliveryModel) {
        return deliveryModel.getStatus() == TaskStatus.DONE || deliveryModel.getStatus() == TaskStatus.DONE_BEFORE_ARRIVAL || deliveryModel.getStatus() == TaskStatus.CANCEL;
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

    public final boolean getIsNeedRecycle() {
        if (Constans.INSTANCE.getRecyclingPlateSwitch()) {
            Switch recycle_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.recycle_mode_switch);
            Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch, "recycle_mode_switch");
            if (recycle_mode_switch.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public final void switchTheme(int theme) {
        if (theme == 0) {
            showTrayCount(PalletCountHelper.INSTANCE.getCount());
            return;
        }
        if (theme == 1) {
            showTrayCount(1);
            return;
        }
        if (theme != 2) {
            return;
        }
        showTrayCount(1);
        setBackgroundColor(getContext().getColor(C4188R.color.theme_birthday_bg));
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.robot_layout)).setBackgroundResource(C4188R.drawable.bg_home_birthday);
        ((CardView) _$_findCachedViewById(C4188R.id.arrive_tip_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.table_birthday_bg));
        View childAt = ((CardView) _$_findCachedViewById(C4188R.id.arrive_tip_layout)).getChildAt(0);
        if (childAt == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        ((TextView) childAt).setTextColor(-1);
        ((CardView) _$_findCachedViewById(C4188R.id.arrive_task_tip_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.table_birthday_bg));
        ImageView birthday_crown_img = (ImageView) _$_findCachedViewById(C4188R.id.birthday_crown_img);
        Intrinsics.checkExpressionValueIsNotNull(birthday_crown_img, "birthday_crown_img");
        birthday_crown_img.setVisibility(0);
        ((TextView) _$_findCachedViewById(C4188R.id.recycle_iv)).setTextColor(-1);
        ((CardView) _$_findCachedViewById(C4188R.id.small_info_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.table_birthday_bg));
        ((CardView) _$_findCachedViewById(C4188R.id.small_list_info_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.table_birthday_bg));
        ArriveDishInfoAdapter arriveDishInfoAdapter = this.arriveDishInfoAdapter;
        if (arriveDishInfoAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arriveDishInfoAdapter");
        }
        arriveDishInfoAdapter.setBirthdayTheme(true);
    }

    private final void showTrayCount(int r8) {
        this.currentTrayCount = r8;
        int i = 0;
        for (Object obj : this.trayInfoViews) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) obj;
            if (i < r8) {
                relativeLayout.setVisibility(0);
            } else {
                relativeLayout.setVisibility(8);
            }
            i = i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(C4188R.id.tray_layout));
        constraintSet.clear(C4188R.id.arrive_tray_1, 1);
        constraintSet.clear(C4188R.id.arrive_tray_1, 2);
        constraintSet.clear(C4188R.id.arrive_tray_1, 3);
        constraintSet.clear(C4188R.id.arrive_tray_1, 4);
        if (r8 == 1) {
            constraintSet.connect(C4188R.id.arrive_tray_1, 4, C4188R.id.arrive_tray_2, 3);
            constraintSet.connect(C4188R.id.arrive_tray_1, 1, 0, 1);
            constraintSet.connect(C4188R.id.arrive_tray_1, 2, 0, 2);
        } else {
            constraintSet.connect(C4188R.id.arrive_tray_1, 4, C4188R.id.arrive_tray_2, 3);
            constraintSet.connect(C4188R.id.arrive_tray_1, 1, 0, 1);
            constraintSet.connect(C4188R.id.arrive_tray_1, 2, 0, 2);
            constraintSet.connect(C4188R.id.arrive_tray_1, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C4188R.id.tray_layout));
    }

    public final void showArrivingLayout(ArrayList<TrayModel> list, String currentTableId) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(currentTableId, "currentTableId");
        Pdlog.m3273d(this.TAG, "showArrivingLayout");
        setTayInfo(list, currentTableId);
        setVisibility(0);
        CardView arrive_tip_layout = (CardView) _$_findCachedViewById(C4188R.id.arrive_tip_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_tip_layout, "arrive_tip_layout");
        arrive_tip_layout.setVisibility(4);
        Button btn_finish = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
        Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
        btn_finish.setVisibility(8);
        this.isOnArrivingStatus = true;
    }

    public final void showArrivedLayout(ArrayList<TrayModel> list, String currentTableId) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(currentTableId, "currentTableId");
        Pdlog.m3273d(this.TAG, "showArrivedLayout");
        this.isOnArrivingStatus = false;
        setTayInfo(list, currentTableId);
        setVisibility(0);
        CardView arrive_tip_layout = (CardView) _$_findCachedViewById(C4188R.id.arrive_tip_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_tip_layout, "arrive_tip_layout");
        arrive_tip_layout.setVisibility(0);
        Button btn_finish = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
        Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
        btn_finish.setVisibility(0);
    }

    public final void hideView() {
        setVisibility(8);
    }

    public final void setOnClickOutletSelectListener(OnLazyClickListener onLazyClickListener) {
        Intrinsics.checkParameterIsNotNull(onLazyClickListener, "onLazyClickListener");
        ((TextView) _$_findCachedViewById(C4188R.id.tv_dish_port)).setOnClickListener(onLazyClickListener);
    }
}
