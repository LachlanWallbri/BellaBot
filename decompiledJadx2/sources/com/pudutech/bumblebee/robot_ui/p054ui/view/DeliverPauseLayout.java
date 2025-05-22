package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.DeliverPauseLayout;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.warkiz.widget.SizeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: DeliverPauseLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 ?2\u00020\u0001:\u0003?@AB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J \u0010\u001e\u001a\u00020\t2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020 0\u0018j\b\u0012\u0004\u0012\u00020 `\u0019H\u0002J \u0010!\u001a\u00020\"2\u0016\u0010#\u001a\u0012\u0012\u0004\u0012\u00020$0\u0018j\b\u0012\u0004\u0012\u00020$`\u0019H\u0002J\u0006\u0010%\u001a\u00020\u001bJ\u0006\u0010&\u001a\u00020\u001bJ\b\u0010'\u001a\u00020\u001bH\u0002J\u0010\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020 H\u0002J\u0010\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\tH\u0002J\u000e\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u0010J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u000200H\u0002J&\u00101\u001a\u00020\u001b2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u0002020\u0018j\b\u0012\u0004\u0012\u000202`\u00192\u0006\u00103\u001a\u00020\fJ\u0018\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u0002002\u0006\u00106\u001a\u000202H\u0002J\u000e\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\"J\b\u00109\u001a\u00020\u001bH\u0002J\u000e\u0010:\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\"J\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\tH\u0002J\u0006\u0010<\u001a\u00020\u001bJ\u0010\u0010=\u001a\u00020\u001b2\b\b\u0001\u0010>\u001a\u00020\tR\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0018j\b\u0012\u0004\u0012\u00020\u0001`\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "currentTrayCount", "functionClickTime", "", "onEvenClickListener", "Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "getOnEvenClickListener", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "setOnEvenClickListener", "(Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;)V", "trayInfoViews", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "changeBtnToBirthday", "", "viewGroup", "Landroid/view/ViewGroup;", "getLeftTableCount", "list", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "hasTrayOrders", "", "foodInfo", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "hideCountdownLayout", "hideView", "initView", "isTaskFinish", "deliveryModel", "isTrayEnable", "int", "setCountdown", "st", "setFootInfoCountTextSize", "textView", "Landroid/widget/TextView;", "setTrayInfo", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "currentTableId", "setTrayText", "view", "t", "showCompleteImmediatelyBtn", "visibleOrGone", "showCountdownLayout", "showEditBtn", "showTrayCount", "showView", "switchTheme", "theme", "Companion", "OnEvenClickListener", "THEME", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverPauseLayout extends RelativeLayout {
    public static final int THEME_BIRTHDAY = 2;
    public static final int THEME_DIRECTLY = 1;
    public static final int THEME_NORMAL = 0;
    private final String TAG;
    private HashMap _$_findViewCache;
    private int currentTrayCount;
    private long functionClickTime;
    private OnEvenClickListener onEvenClickListener;
    private final ArrayList<RelativeLayout> trayInfoViews;

    /* compiled from: DeliverPauseLayout.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "", "onCancelAllClick", "", "onCancelOneClick", "onLayoutClick", "onModifyClick", "onTurnBackClick", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnEvenClickListener {
        void onCancelAllClick();

        void onCancelOneClick();

        void onLayoutClick();

        void onModifyClick();

        void onTurnBackClick();
    }

    /* compiled from: DeliverPauseLayout.kt */
    @Retention(RetentionPolicy.SOURCE)
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$THEME;", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
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

    public final OnEvenClickListener getOnEvenClickListener() {
        return this.onEvenClickListener;
    }

    public final void setOnEvenClickListener(OnEvenClickListener onEvenClickListener) {
        this.onEvenClickListener = onEvenClickListener;
    }

    public DeliverPauseLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        initView();
    }

    public DeliverPauseLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        initView();
    }

    public DeliverPauseLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.trayInfoViews = new ArrayList<>();
        initView();
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
        ((CardView) _$_findCachedViewById(C4188R.id.pause_info_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.table_birthday_bg));
        ((TextView) _$_findCachedViewById(C4188R.id.tv_status)).setTextColor(-1);
        ImageView birthday_bg_img1 = (ImageView) _$_findCachedViewById(C4188R.id.birthday_bg_img1);
        Intrinsics.checkExpressionValueIsNotNull(birthday_bg_img1, "birthday_bg_img1");
        birthday_bg_img1.setVisibility(0);
        ImageView birthday_bg_img2 = (ImageView) _$_findCachedViewById(C4188R.id.birthday_bg_img2);
        Intrinsics.checkExpressionValueIsNotNull(birthday_bg_img2, "birthday_bg_img2");
        birthday_bg_img2.setVisibility(0);
        LinearLayout modify_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.modify_ll);
        Intrinsics.checkExpressionValueIsNotNull(modify_ll, "modify_ll");
        changeBtnToBirthday(modify_ll);
        LinearLayout cancel_current = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_current);
        Intrinsics.checkExpressionValueIsNotNull(cancel_current, "cancel_current");
        changeBtnToBirthday(cancel_current);
        LinearLayout cancel_all = (LinearLayout) _$_findCachedViewById(C4188R.id.cancel_all);
        Intrinsics.checkExpressionValueIsNotNull(cancel_all, "cancel_all");
        changeBtnToBirthday(cancel_all);
        LinearLayout back_task = (LinearLayout) _$_findCachedViewById(C4188R.id.back_task);
        Intrinsics.checkExpressionValueIsNotNull(back_task, "back_task");
        changeBtnToBirthday(back_task);
    }

    private final void changeBtnToBirthday(ViewGroup viewGroup) {
        View childAt = viewGroup.getChildAt(1);
        if (childAt instanceof TextView) {
            ((TextView) childAt).setTextColor(-1);
        }
        viewGroup.setBackgroundResource(C4188R.drawable.select_deliver_birthday_pause_item);
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
        constraintSet.clear(C4188R.id.tray_1_layout, 1);
        constraintSet.clear(C4188R.id.tray_1_layout, 2);
        constraintSet.clear(C4188R.id.tray_1_layout, 3);
        constraintSet.clear(C4188R.id.tray_1_layout, 4);
        if (r8 == 1) {
            constraintSet.connect(C4188R.id.tray_1_layout, 4, C4188R.id.tray_2_layout, 3);
            constraintSet.connect(C4188R.id.tray_1_layout, 1, 0, 1);
            constraintSet.connect(C4188R.id.tray_1_layout, 2, 0, 2);
        } else {
            constraintSet.connect(C4188R.id.tray_1_layout, 4, C4188R.id.tray_2_layout, 3);
            constraintSet.connect(C4188R.id.tray_1_layout, 1, 0, 1);
            constraintSet.connect(C4188R.id.tray_1_layout, 2, 0, 2);
            constraintSet.connect(C4188R.id.tray_1_layout, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C4188R.id.tray_layout));
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
                layoutInflater.inflate(C4188R.layout.layout_deliver_pause, this);
            }
        }
        this.trayInfoViews.add(findViewById(C4188R.id.tray_1_layout));
        this.trayInfoViews.add(findViewById(C4188R.id.tray_2_layout));
        this.trayInfoViews.add(findViewById(C4188R.id.tray_3_layout));
        this.trayInfoViews.add(findViewById(C4188R.id.tray_4_layout));
        this.trayInfoViews.add(findViewById(C4188R.id.tray_5_layout));
        ((ViewGroup) findViewById(C4188R.id.modify_ll)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = DeliverPauseLayout.this.TAG;
                Pdlog.m3273d(str, "modify_ll click");
                DeliverPauseLayout.this.functionClickTime = System.currentTimeMillis();
                DeliverPauseLayout.OnEvenClickListener onEvenClickListener = DeliverPauseLayout.this.getOnEvenClickListener();
                if (onEvenClickListener != null) {
                    onEvenClickListener.onModifyClick();
                }
            }
        });
        ((ViewGroup) findViewById(C4188R.id.cancel_current)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = DeliverPauseLayout.this.TAG;
                Pdlog.m3273d(str, "cancel_current click");
                DeliverPauseLayout.this.functionClickTime = System.currentTimeMillis();
                DeliverPauseLayout.OnEvenClickListener onEvenClickListener = DeliverPauseLayout.this.getOnEvenClickListener();
                if (onEvenClickListener != null) {
                    onEvenClickListener.onCancelOneClick();
                }
            }
        });
        ((ViewGroup) findViewById(C4188R.id.cancel_all)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = DeliverPauseLayout.this.TAG;
                Pdlog.m3273d(str, "cancel_all click");
                DeliverPauseLayout.this.functionClickTime = System.currentTimeMillis();
                DeliverPauseLayout.OnEvenClickListener onEvenClickListener = DeliverPauseLayout.this.getOnEvenClickListener();
                if (onEvenClickListener != null) {
                    onEvenClickListener.onCancelAllClick();
                }
            }
        });
        ((ViewGroup) findViewById(C4188R.id.back_task)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = DeliverPauseLayout.this.TAG;
                Pdlog.m3273d(str, "back_task click");
                DeliverPauseLayout.this.functionClickTime = System.currentTimeMillis();
                DeliverPauseLayout.OnEvenClickListener onEvenClickListener = DeliverPauseLayout.this.getOnEvenClickListener();
                if (onEvenClickListener != null) {
                    onEvenClickListener.onTurnBackClick();
                }
            }
        });
        setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout$initView$5
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                long j;
                String str2;
                str = DeliverPauseLayout.this.TAG;
                Pdlog.m3273d(str, "onLayoutClick");
                long currentTimeMillis = System.currentTimeMillis();
                j = DeliverPauseLayout.this.functionClickTime;
                if (currentTimeMillis - j < 1200) {
                    str2 = DeliverPauseLayout.this.TAG;
                    Pdlog.m3274e(str2, "onLayoutClick click too fast");
                } else {
                    DeliverPauseLayout.OnEvenClickListener onEvenClickListener = DeliverPauseLayout.this.getOnEvenClickListener();
                    if (onEvenClickListener != null) {
                        onEvenClickListener.onLayoutClick();
                    }
                }
            }
        });
        setCountdown(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms() / 1000);
    }

    public final void showView() {
        setVisibility(0);
        Pdlog.m3273d(this.TAG, "showView()");
    }

    public final void hideView() {
        setVisibility(8);
        Pdlog.m3273d(this.TAG, "hideView()");
    }

    public final void setTrayInfo(ArrayList<TrayModel> list, String currentTableId) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(currentTableId, "currentTableId");
        Pdlog.m3273d(this.TAG, "setTrayInfo = " + list + " , currentMovingText = " + currentTableId);
        showCountdownLayout();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getContext().getString(C4188R.string.pdStr2_30);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr2_30)");
        Object[] objArr = {currentTableId};
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        TextView target_name_tv = (TextView) _$_findCachedViewById(C4188R.id.target_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(target_name_tv, "target_name_tv");
        target_name_tv.setText(format);
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.target_name_tv);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        textView.setTextSize(0, context.getResources().getDimension(C4188R.dimen.delivery_pause_content_ll_target_tv_s));
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.target_name_tv), ((int) context2.getResources().getDimension(C4188R.dimen.delivery_pause_content_ll_target_tv_max_w)) - 50, format);
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
            CardView cardView = (CardView) relativeLayout2.findViewById(C4188R.id.card_view);
            TextView textView2 = (TextView) relativeLayout2.findViewById(C4188R.id.name_text);
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
            Intrinsics.checkExpressionValueIsNotNull(textView2, "textView");
            setTrayText(textView2, trayModel);
            DeliveryModel current = trayModel.getCurrent();
            if (!isTrayEnable(i)) {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.transparent));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f = 0;
                cardView.setCardElevation(f);
                cardView.setMaxCardElevation(f);
                textView2.setBackgroundResource(C4188R.drawable.settings_tray_disable);
            } else if (current != null && !isTaskFinish(current)) {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.theme_main_color));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                cardView.setCardElevation(SizeUtils.dp2px(getContext(), 3.0f));
                cardView.setMaxCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                Sdk27PropertiesKt.setTextColor(textView2, -1);
                textView2.setBackgroundResource(0);
            } else {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.deliver_pause_tray_bg_normal));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f2 = 0;
                cardView.setCardElevation(f2);
                cardView.setMaxCardElevation(f2);
                Sdk27PropertiesKt.setTextColor(textView2, getResources().getColor(C4188R.color.deliver_pause_tray_font));
                textView2.setBackgroundResource(0);
            }
            i = i2;
        }
    }

    private final boolean isTrayEnable(int r2) {
        return PalletCountHelper.INSTANCE.isPalletEnable(r2) || this.currentTrayCount == 1;
    }

    private final int getLeftTableCount(ArrayList<DeliveryModel> list) {
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!isTaskFinish((DeliveryModel) it.next())) {
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

    /* JADX WARN: Removed duplicated region for block: B:19:0x00fa  */
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
                    view.setTextSize(0, context.getResources().getDimension(C4188R.dimen.delivery_pause_robot_tray_item_tv_s));
                    UiUtils.adjustTvTextSize(view, 122, sb.toString(), 18);
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
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        view.setTextSize(0, context2.getResources().getDimension(C4188R.dimen.delivery_pause_robot_tray_item_tv_s));
        UiUtils.adjustTvTextSize(view, 122, sb.toString(), 18);
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

    public final void setCountdown(long st) {
        try {
            int sp2px = SizeUtils.sp2px(getContext(), 38.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getContext().getString(C4188R.string.pdStr2_5);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr2_5)");
            Object[] objArr = new Object[1];
            objArr[0] = LanguageUtils.INSTANCE.isALaBo() ? String.valueOf(st) : Integer.valueOf((int) st);
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            int indexOf$default = StringsKt.indexOf$default((CharSequence) format, String.valueOf(st), 0, false, 6, (Object) null);
            Typeface typeface = Typeface.MONOSPACE;
            Intrinsics.checkExpressionValueIsNotNull(typeface, "Typeface.MONOSPACE");
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan("monospace", typeface.getStyle() | 1, sp2px, ColorStateList.valueOf(getContext().getColor(C4188R.color.theme_main_color)), null);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(textAppearanceSpan, indexOf$default, String.valueOf(st).length() + indexOf$default, 34);
            showCountdownLayout();
            TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            countdown_tv.setText(spannableStringBuilder);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    public final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(4);
    }

    public final void showEditBtn(boolean visibleOrGone) {
        View findViewById = findViewById(C4188R.id.modify_ll);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<ViewGroup>(R.id.modify_ll)");
        ViewExtKt.visibleOrGone(findViewById, visibleOrGone);
    }

    public final void showCompleteImmediatelyBtn(boolean visibleOrGone) {
        View findViewById = findViewById(C4188R.id.cancel_current);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<ViewGroup>(R.id.cancel_current)");
        ViewExtKt.visibleOrGone(findViewById, visibleOrGone);
    }

    private final void showCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        if (countdown_tv.getVisibility() != 0) {
            TextView countdown_tv2 = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
            countdown_tv2.setVisibility(0);
        }
    }
}
