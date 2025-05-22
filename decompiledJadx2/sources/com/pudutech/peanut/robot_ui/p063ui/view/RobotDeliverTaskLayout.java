package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.information_system_task.InformationSystemContract;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.DishInfoListEditDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.MultiTableEditDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.FramesSequenceAnimation;
import com.pudutech.peanut.robot_ui.util.FaceAnimationUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: RobotDeliverTaskLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\"\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\t2\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020\u001aH\u0002J\b\u0010:\u001a\u00020\u001aH\u0002J\u0018\u0010;\u001a\u00020'2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000eH\u0002J\b\u0010>\u001a\u00020'H\u0002J\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020.0-j\b\u0012\u0004\u0012\u00020.`/J\u0006\u0010@\u001a\u00020\tJ\u0010\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020.H\u0002J\u0006\u0010C\u001a\u000204J\u0006\u0010D\u001a\u00020\u001aJ \u0010E\u001a\u00020\u001a2\u0016\u0010F\u001a\u0012\u0012\u0004\u0012\u00020G0-j\b\u0012\u0004\u0012\u00020G`/H\u0002J2\u0010H\u001a\u00020'2\u0016\u0010I\u001a\u0012\u0012\u0004\u0012\u00020.0-j\b\u0012\u0004\u0012\u00020.`/2\b\u0010J\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u00103\u001a\u000204J\u0012\u0010K\u001a\u00020'2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010L\u001a\u00020'H\u0002J\u0010\u0010M\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020\tH\u0002J\u0006\u0010O\u001a\u00020'J\b\u0010P\u001a\u00020'H\u0002J\b\u0010Q\u001a\u00020'H\u0002J\b\u0010R\u001a\u00020\u001aH\u0002J\u000e\u0010S\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020\tJ\u000e\u0010T\u001a\u00020'2\u0006\u0010N\u001a\u00020\tJ\b\u0010U\u001a\u00020'H\u0002J\u0010\u0010V\u001a\u00020'2\u0006\u00107\u001a\u000208H\u0002J\u0016\u0010W\u001a\u00020'2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010X\u001a\u00020GJ\u001a\u0010W\u001a\u00020'2\b\u0010<\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010Y\u001a\u00020\u001aJ.\u0010Z\u001a\u00020'2\u0006\u0010<\u001a\u00020\u000e2\u0016\u0010[\u001a\u0012\u0012\u0004\u0012\u00020G0-j\b\u0012\u0004\u0012\u00020G`/2\u0006\u00106\u001a\u00020\tJ\b\u0010\\\u001a\u00020'H\u0002J\u0006\u0010]\u001a\u00020'J\u0006\u0010^\u001a\u00020'J\u000e\u0010_\u001a\u00020'2\u0006\u0010`\u001a\u00020\u001aJ\u0012\u0010a\u001a\u00020'2\b\b\u0002\u00109\u001a\u00020\u001aH\u0002R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010%\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010,\u001a\u0012\u0012\u0004\u0012\u00020.0-j\b\u0012\u0004\u0012\u00020.`/X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00100\u001a\u0012\u0012\u0004\u0012\u00020\u00010-j\b\u0012\u0004\u0012\u00020\u0001`/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0001X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/RobotDeliverTaskLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "CHECK_ANIMATION_END", "MAX_MULTI_TABEL_CONUT", "TAG", "", "kotlin.jvm.PlatformType", "animationDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "birthdayCrownImg", "Landroid/widget/ImageView;", "currentSelectIndex", "currentSelectText", "fram", "Lcom/pudutech/peanut/robot_ui/ui/view/FramesSequenceAnimation;", "inputTaskCount", "isBirthdayTheme", "", "isLoadingImage", "isPlayingAnimation", "mainHandler", "Landroid/os/Handler;", "onItemCloseClickListener", "Landroid/view/View$OnClickListener;", "onItemTextClickListener", "onItemTextLongTimeClickListener", "Landroid/view/View$OnLongClickListener;", "onMultiTableEditClickListener", "onTaskChangeListener", "Lkotlin/Function0;", "", "getOnTaskChangeListener", "()Lkotlin/jvm/functions/Function0;", "setOnTaskChangeListener", "(Lkotlin/jvm/functions/Function0;)V", "palletDates", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "palletItemArray", "robotImageView", "rootView", "sortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "adapterNameViewSize", "index", "textView", "Landroid/widget/TextView;", "isSelect", "checkAllTaskCount", "checkPalletDateInfoAndDelete", "name", "scid", "findNextSelect", "getAllTask", "getInputTaskCount", "getPalletText", "t", "getSortType", "hasTask", "hasTrayOrders", "foodInfo", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "initData", "list", "currentTaskId", "initView", "isAnimationEnd", "isTrayEnable", "i", "removeAnimation", "resetLayout", "resetPalletDate", "selectNextEmptyPallet", "selectTray", "setInputTaskCount", "setListener", "setSanCountTextSize", "setTaskName", "info", "multiTable", "setTrayOrders", "orders", "showAnimation", "start", "startShowAnimation", "switchBirthday", "boolean", "updateViewState", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotDeliverTaskLayout extends RelativeLayout {
    private final int CHECK_ANIMATION_END;
    private final int MAX_MULTI_TABEL_CONUT;
    private final String TAG;
    private HashMap _$_findViewCache;
    private AnimationDrawable animationDrawable;
    private ImageView birthdayCrownImg;
    private int currentSelectIndex;
    private int currentSelectText;
    private FramesSequenceAnimation fram;
    private int inputTaskCount;
    private boolean isBirthdayTheme;
    private boolean isLoadingImage;
    private boolean isPlayingAnimation;
    private final Handler mainHandler;
    private final View.OnClickListener onItemCloseClickListener;
    private final View.OnClickListener onItemTextClickListener;
    private final View.OnLongClickListener onItemTextLongTimeClickListener;
    private final View.OnClickListener onMultiTableEditClickListener;
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

    public static final /* synthetic */ ImageView access$getRobotImageView$p(RobotDeliverTaskLayout robotDeliverTaskLayout) {
        ImageView imageView = robotDeliverTaskLayout.robotImageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("robotImageView");
        }
        return imageView;
    }

    public final Function0<Unit> getOnTaskChangeListener() {
        return this.onTaskChangeListener;
    }

    public final void setOnTaskChangeListener(Function0<Unit> function0) {
        this.onTaskChangeListener = function0;
    }

    public RobotDeliverTaskLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.inputTaskCount = 3;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = MoveSortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        this.CHECK_ANIMATION_END = 99;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2 = message.what;
                i = RobotDeliverTaskLayout.this.CHECK_ANIMATION_END;
                if (i2 != i) {
                    return true;
                }
                RobotDeliverTaskLayout.this.isAnimationEnd();
                return true;
            }
        });
        this.onItemTextClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                boolean isTrayEnable;
                int i;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    isTrayEnable = RobotDeliverTaskLayout.this.isTrayEnable(intValue);
                    if (isTrayEnable) {
                        i = RobotDeliverTaskLayout.this.currentSelectIndex;
                        if (intValue != i) {
                            RobotDeliverTaskLayout.this.currentSelectIndex = intValue;
                            RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                            return;
                        }
                        return;
                    }
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_39);
                    Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_39)");
                    MyBaseActivity.showTipDialog$default((MyBaseActivity) context2, string, null, null, RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr8_15), 6, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemTextLongTimeClickListener = new View.OnLongClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View v) {
                boolean isTrayEnable;
                ArrayList arrayList;
                boolean z;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    final int intValue = ((Integer) tag).intValue();
                    isTrayEnable = RobotDeliverTaskLayout.this.isTrayEnable(intValue);
                    if (!isTrayEnable) {
                        return true;
                    }
                    arrayList = RobotDeliverTaskLayout.this.palletDates;
                    Object obj = arrayList.get(intValue);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "palletDates[i]");
                    TrayModel trayModel = (TrayModel) obj;
                    if (trayModel.getAllDestinations().size() == 1) {
                        ArrayList<InformationSystemContract.OrderInfo> foodInfo = trayModel.getAllDestinations().get(0).getFoodInfo();
                        if (!(foodInfo == null || foodInfo.isEmpty())) {
                            Context context2 = RobotDeliverTaskLayout.this.getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                            DishInfoListEditDialog dishInfoListEditDialog = new DishInfoListEditDialog(context2);
                            z = RobotDeliverTaskLayout.this.isBirthdayTheme;
                            dishInfoListEditDialog.switchBirthdayTheme(z);
                            ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = trayModel.getAllDestinations().get(0).getFoodInfo();
                            if (foodInfo2 == null) {
                                Intrinsics.throwNpe();
                            }
                            dishInfoListEditDialog.setDishInfo(foodInfo2);
                            dishInfoListEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(ArrayList<InformationSystemContract.OrderInfo> arrayList2) {
                                    invoke2(arrayList2);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(ArrayList<InformationSystemContract.OrderInfo> it) {
                                    ArrayList arrayList2;
                                    ArrayList arrayList3;
                                    ArrayList arrayList4;
                                    String palletText;
                                    ArrayList arrayList5;
                                    ArrayList arrayList6;
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    if (it.isEmpty()) {
                                        arrayList5 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById = ((RelativeLayout) arrayList5.get(intValue)).findViewById(C5508R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        ((TextView) findViewById).setText("");
                                        arrayList6 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList6.get(intValue)).getAllDestinations().clear();
                                    } else {
                                        arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList2.get(intValue)).getAllDestinations().get(0).setFoodInfo(it);
                                        arrayList3 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById2 = ((RelativeLayout) arrayList3.get(intValue)).findViewById(C5508R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                                        arrayList4 = RobotDeliverTaskLayout.this.palletDates;
                                        Object obj2 = arrayList4.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "palletDates[i]");
                                        palletText = robotDeliverTaskLayout.getPalletText((TrayModel) obj2);
                                        ((TextView) findViewById2).setText(palletText);
                                    }
                                    RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                                }
                            });
                            dishInfoListEditDialog.show();
                            return true;
                        }
                    }
                    return false;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletItemArray;
                    View findViewById = ((RelativeLayout) arrayList.get(intValue)).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText("");
                    arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                    ((TrayModel) arrayList2.get(intValue)).getAllDestinations().clear();
                    RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                    Function0<Unit> onTaskChangeListener = RobotDeliverTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onMultiTableEditClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    final int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletDates;
                    Object obj = arrayList.get(intValue);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "palletDates[i]");
                    final TrayModel trayModel = (TrayModel) obj;
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    MultiTableEditDialog multiTableEditDialog = new MultiTableEditDialog(context2);
                    multiTableEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<DeliveryModel>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<DeliveryModel> arrayList2) {
                            invoke2(arrayList2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ArrayList<DeliveryModel> it) {
                            ArrayList arrayList2;
                            String palletText;
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            trayModel.getAllDestinations().clear();
                            trayModel.getAllDestinations().addAll(it);
                            arrayList2 = RobotDeliverTaskLayout.this.palletItemArray;
                            View findViewById = ((RelativeLayout) arrayList2.get(intValue)).findViewById(C5508R.id.task_name);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                            palletText = RobotDeliverTaskLayout.this.getPalletText(trayModel);
                            ((TextView) findViewById).setText(palletText);
                            RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                        }
                    });
                    multiTableEditDialog.setDishInfo(trayModel.getAllDestinations());
                    multiTableEditDialog.show();
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        initView(getContext());
    }

    public RobotDeliverTaskLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.inputTaskCount = 3;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = MoveSortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        this.CHECK_ANIMATION_END = 99;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2 = message.what;
                i = RobotDeliverTaskLayout.this.CHECK_ANIMATION_END;
                if (i2 != i) {
                    return true;
                }
                RobotDeliverTaskLayout.this.isAnimationEnd();
                return true;
            }
        });
        this.onItemTextClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                boolean isTrayEnable;
                int i;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    isTrayEnable = RobotDeliverTaskLayout.this.isTrayEnable(intValue);
                    if (isTrayEnable) {
                        i = RobotDeliverTaskLayout.this.currentSelectIndex;
                        if (intValue != i) {
                            RobotDeliverTaskLayout.this.currentSelectIndex = intValue;
                            RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                            return;
                        }
                        return;
                    }
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_39);
                    Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_39)");
                    MyBaseActivity.showTipDialog$default((MyBaseActivity) context2, string, null, null, RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr8_15), 6, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemTextLongTimeClickListener = new View.OnLongClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View v) {
                boolean isTrayEnable;
                ArrayList arrayList;
                boolean z;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    final int intValue = ((Integer) tag).intValue();
                    isTrayEnable = RobotDeliverTaskLayout.this.isTrayEnable(intValue);
                    if (!isTrayEnable) {
                        return true;
                    }
                    arrayList = RobotDeliverTaskLayout.this.palletDates;
                    Object obj = arrayList.get(intValue);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "palletDates[i]");
                    TrayModel trayModel = (TrayModel) obj;
                    if (trayModel.getAllDestinations().size() == 1) {
                        ArrayList<InformationSystemContract.OrderInfo> foodInfo = trayModel.getAllDestinations().get(0).getFoodInfo();
                        if (!(foodInfo == null || foodInfo.isEmpty())) {
                            Context context2 = RobotDeliverTaskLayout.this.getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                            DishInfoListEditDialog dishInfoListEditDialog = new DishInfoListEditDialog(context2);
                            z = RobotDeliverTaskLayout.this.isBirthdayTheme;
                            dishInfoListEditDialog.switchBirthdayTheme(z);
                            ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = trayModel.getAllDestinations().get(0).getFoodInfo();
                            if (foodInfo2 == null) {
                                Intrinsics.throwNpe();
                            }
                            dishInfoListEditDialog.setDishInfo(foodInfo2);
                            dishInfoListEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(ArrayList<InformationSystemContract.OrderInfo> arrayList2) {
                                    invoke2(arrayList2);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(ArrayList<InformationSystemContract.OrderInfo> it) {
                                    ArrayList arrayList2;
                                    ArrayList arrayList3;
                                    ArrayList arrayList4;
                                    String palletText;
                                    ArrayList arrayList5;
                                    ArrayList arrayList6;
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    if (it.isEmpty()) {
                                        arrayList5 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById = ((RelativeLayout) arrayList5.get(intValue)).findViewById(C5508R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        ((TextView) findViewById).setText("");
                                        arrayList6 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList6.get(intValue)).getAllDestinations().clear();
                                    } else {
                                        arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList2.get(intValue)).getAllDestinations().get(0).setFoodInfo(it);
                                        arrayList3 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById2 = ((RelativeLayout) arrayList3.get(intValue)).findViewById(C5508R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                                        arrayList4 = RobotDeliverTaskLayout.this.palletDates;
                                        Object obj2 = arrayList4.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "palletDates[i]");
                                        palletText = robotDeliverTaskLayout.getPalletText((TrayModel) obj2);
                                        ((TextView) findViewById2).setText(palletText);
                                    }
                                    RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                                }
                            });
                            dishInfoListEditDialog.show();
                            return true;
                        }
                    }
                    return false;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletItemArray;
                    View findViewById = ((RelativeLayout) arrayList.get(intValue)).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText("");
                    arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                    ((TrayModel) arrayList2.get(intValue)).getAllDestinations().clear();
                    RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                    Function0<Unit> onTaskChangeListener = RobotDeliverTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onMultiTableEditClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    final int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletDates;
                    Object obj = arrayList.get(intValue);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "palletDates[i]");
                    final TrayModel trayModel = (TrayModel) obj;
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    MultiTableEditDialog multiTableEditDialog = new MultiTableEditDialog(context2);
                    multiTableEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<DeliveryModel>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<DeliveryModel> arrayList2) {
                            invoke2(arrayList2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ArrayList<DeliveryModel> it) {
                            ArrayList arrayList2;
                            String palletText;
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            trayModel.getAllDestinations().clear();
                            trayModel.getAllDestinations().addAll(it);
                            arrayList2 = RobotDeliverTaskLayout.this.palletItemArray;
                            View findViewById = ((RelativeLayout) arrayList2.get(intValue)).findViewById(C5508R.id.task_name);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                            palletText = RobotDeliverTaskLayout.this.getPalletText(trayModel);
                            ((TextView) findViewById).setText(palletText);
                            RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                        }
                    });
                    multiTableEditDialog.setDishInfo(trayModel.getAllDestinations());
                    multiTableEditDialog.show();
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        initView(getContext());
    }

    public RobotDeliverTaskLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.inputTaskCount = 3;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = MoveSortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        this.CHECK_ANIMATION_END = 99;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i2;
                int i22 = message.what;
                i2 = RobotDeliverTaskLayout.this.CHECK_ANIMATION_END;
                if (i22 != i2) {
                    return true;
                }
                RobotDeliverTaskLayout.this.isAnimationEnd();
                return true;
            }
        });
        this.onItemTextClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                boolean isTrayEnable;
                int i2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    isTrayEnable = RobotDeliverTaskLayout.this.isTrayEnable(intValue);
                    if (isTrayEnable) {
                        i2 = RobotDeliverTaskLayout.this.currentSelectIndex;
                        if (intValue != i2) {
                            RobotDeliverTaskLayout.this.currentSelectIndex = intValue;
                            RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                            return;
                        }
                        return;
                    }
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_39);
                    Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_39)");
                    MyBaseActivity.showTipDialog$default((MyBaseActivity) context2, string, null, null, RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr8_15), 6, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemTextLongTimeClickListener = new View.OnLongClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View v) {
                boolean isTrayEnable;
                ArrayList arrayList;
                boolean z;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    final int intValue = ((Integer) tag).intValue();
                    isTrayEnable = RobotDeliverTaskLayout.this.isTrayEnable(intValue);
                    if (!isTrayEnable) {
                        return true;
                    }
                    arrayList = RobotDeliverTaskLayout.this.palletDates;
                    Object obj = arrayList.get(intValue);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "palletDates[i]");
                    TrayModel trayModel = (TrayModel) obj;
                    if (trayModel.getAllDestinations().size() == 1) {
                        ArrayList<InformationSystemContract.OrderInfo> foodInfo = trayModel.getAllDestinations().get(0).getFoodInfo();
                        if (!(foodInfo == null || foodInfo.isEmpty())) {
                            Context context2 = RobotDeliverTaskLayout.this.getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                            DishInfoListEditDialog dishInfoListEditDialog = new DishInfoListEditDialog(context2);
                            z = RobotDeliverTaskLayout.this.isBirthdayTheme;
                            dishInfoListEditDialog.switchBirthdayTheme(z);
                            ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = trayModel.getAllDestinations().get(0).getFoodInfo();
                            if (foodInfo2 == null) {
                                Intrinsics.throwNpe();
                            }
                            dishInfoListEditDialog.setDishInfo(foodInfo2);
                            dishInfoListEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(ArrayList<InformationSystemContract.OrderInfo> arrayList2) {
                                    invoke2(arrayList2);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(ArrayList<InformationSystemContract.OrderInfo> it) {
                                    ArrayList arrayList2;
                                    ArrayList arrayList3;
                                    ArrayList arrayList4;
                                    String palletText;
                                    ArrayList arrayList5;
                                    ArrayList arrayList6;
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    if (it.isEmpty()) {
                                        arrayList5 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById = ((RelativeLayout) arrayList5.get(intValue)).findViewById(C5508R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        ((TextView) findViewById).setText("");
                                        arrayList6 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList6.get(intValue)).getAllDestinations().clear();
                                    } else {
                                        arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList2.get(intValue)).getAllDestinations().get(0).setFoodInfo(it);
                                        arrayList3 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById2 = ((RelativeLayout) arrayList3.get(intValue)).findViewById(C5508R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                                        arrayList4 = RobotDeliverTaskLayout.this.palletDates;
                                        Object obj2 = arrayList4.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "palletDates[i]");
                                        palletText = robotDeliverTaskLayout.getPalletText((TrayModel) obj2);
                                        ((TextView) findViewById2).setText(palletText);
                                    }
                                    RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                                }
                            });
                            dishInfoListEditDialog.show();
                            return true;
                        }
                    }
                    return false;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletItemArray;
                    View findViewById = ((RelativeLayout) arrayList.get(intValue)).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText("");
                    arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                    ((TrayModel) arrayList2.get(intValue)).getAllDestinations().clear();
                    RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                    Function0<Unit> onTaskChangeListener = RobotDeliverTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onMultiTableEditClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    final int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletDates;
                    Object obj = arrayList.get(intValue);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "palletDates[i]");
                    final TrayModel trayModel = (TrayModel) obj;
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    MultiTableEditDialog multiTableEditDialog = new MultiTableEditDialog(context2);
                    multiTableEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<DeliveryModel>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<DeliveryModel> arrayList2) {
                            invoke2(arrayList2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ArrayList<DeliveryModel> it) {
                            ArrayList arrayList2;
                            String palletText;
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            trayModel.getAllDestinations().clear();
                            trayModel.getAllDestinations().addAll(it);
                            arrayList2 = RobotDeliverTaskLayout.this.palletItemArray;
                            View findViewById = ((RelativeLayout) arrayList2.get(intValue)).findViewById(C5508R.id.task_name);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                            palletText = RobotDeliverTaskLayout.this.getPalletText(trayModel);
                            ((TextView) findViewById).setText(palletText);
                            RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                        }
                    });
                    multiTableEditDialog.setDishInfo(trayModel.getAllDestinations());
                    multiTableEditDialog.show();
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
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
            TextView it = (TextView) relativeLayout.findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            it.setTag(Integer.valueOf(i));
            it.setOnClickListener(this.onItemTextClickListener);
            it.setOnLongClickListener(this.onItemTextLongTimeClickListener);
            ImageView it2 = (ImageView) relativeLayout.findViewById(C5508R.id.clear);
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            it2.setTag(Integer.valueOf(i));
            it2.setOnClickListener(this.onItemCloseClickListener);
            ImageView it3 = (ImageView) relativeLayout.findViewById(C5508R.id.edit_iv);
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            it3.setTag(Integer.valueOf(i));
            it3.setOnClickListener(this.onMultiTableEditClickListener);
            i = i2;
        }
    }

    public static /* synthetic */ void updateViewState$default(RobotDeliverTaskLayout robotDeliverTaskLayout, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        robotDeliverTaskLayout.updateViewState(z);
    }

    private final void updateViewState(boolean isSelect) {
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
            TextView textView = (TextView) relativeLayout.findViewById(C5508R.id.task_name);
            if (i == this.currentSelectText) {
                adapterNameViewSize(i, textView, isSelect);
            }
            ImageView clear = (ImageView) relativeLayout.findViewById(C5508R.id.clear);
            CardView cardView = (CardView) relativeLayout.findViewById(C5508R.id.card_view);
            if (!isTrayEnable(i)) {
                Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
                textView.setText("");
                cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.transparent));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f = 0;
                cardView.setCardElevation(f);
                cardView.setMaxCardElevation(f);
                textView.setBackgroundResource(C5508R.drawable.settings_tray_disable);
                cardView.setTag(0);
            } else if (i == this.currentSelectIndex) {
                cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.theme_main_color));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                cardView.setCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                cardView.setMaxCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                textView.setBackgroundResource(0);
                textView.setTextColor(-1);
                cardView.setTag(1);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                Object tag = cardView.getTag();
                if (tag != null && Intrinsics.areEqual(tag, (Object) 1)) {
                    cardView.setTag(0);
                    cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.item_pallet_bg_normal));
                    float f2 = 0;
                    cardView.setCardElevation(f2);
                    cardView.setMaxCardElevation(f2);
                    textView.setBackgroundResource(0);
                    textView.setTextColor(getContext().getColor(C5508R.color.font_color_1));
                }
                if (!isSelect) {
                    cardView.setTag(0);
                    cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.item_pallet_bg_normal));
                    float f3 = 0;
                    cardView.setCardElevation(f3);
                    cardView.setMaxCardElevation(f3);
                    textView.setBackgroundResource(0);
                    textView.setTextColor(getContext().getColor(C5508R.color.font_color_1));
                }
            }
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            CharSequence text = textView.getText();
            if (text == null || StringsKt.isBlank(text)) {
                Intrinsics.checkExpressionValueIsNotNull(clear, "clear");
                clear.setVisibility(4);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(clear, "clear");
                clear.setVisibility(0);
            }
            TrayModel trayModel = this.palletDates.get(i);
            Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[index]");
            TrayModel trayModel2 = trayModel;
            TextView redTv = (TextView) relativeLayout.findViewById(C5508R.id.red_count_tv);
            ImageView editIv = (ImageView) relativeLayout.findViewById(C5508R.id.edit_iv);
            if (trayModel2.getAllDestinations().size() > 1) {
                Intrinsics.checkExpressionValueIsNotNull(redTv, "redTv");
                redTv.setVisibility(0);
                redTv.setText(String.valueOf(trayModel2.getAllDestinations().size()));
                Intrinsics.checkExpressionValueIsNotNull(editIv, "editIv");
                editIv.setVisibility(0);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(redTv, "redTv");
                redTv.setVisibility(8);
                Intrinsics.checkExpressionValueIsNotNull(editIv, "editIv");
                editIv.setVisibility(4);
            }
            i = i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(C5508R.id.pallet_layout));
        constraintSet.clear(C5508R.id.layout_pallet_item_1, 1);
        constraintSet.clear(C5508R.id.layout_pallet_item_1, 2);
        constraintSet.clear(C5508R.id.layout_pallet_item_1, 3);
        constraintSet.clear(C5508R.id.layout_pallet_item_1, 4);
        if (this.inputTaskCount == 1) {
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 2, 0, 2);
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 3, 0, 3, 75);
        } else {
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 4, C5508R.id.layout_pallet_item_2, 3);
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 2, 0, 2);
            constraintSet.connect(C5508R.id.layout_pallet_item_1, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C5508R.id.pallet_layout));
    }

    private final void adapterNameViewSize(int index, TextView textView, boolean isSelect) {
        Float valueOf = textView != null ? Float.valueOf(textView.getTextSize()) : null;
        if (isSelect) {
            if ((String.valueOf(textView != null ? textView.getText() : null).length() > 0) && Intrinsics.areEqual(valueOf, 36.0f)) {
                return;
            }
        }
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
                UiUtils.adjustTvTextSize(textView, (int) context2.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_w), obj, 48);
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

    public final void switchBirthday(boolean r6) {
        this.isBirthdayTheme = r6;
        if (r6) {
            ImageView imageView = this.birthdayCrownImg;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("birthdayCrownImg");
            }
            imageView.setVisibility(0);
            ImageView robot_img = (ImageView) _$_findCachedViewById(C5508R.id.robot_img);
            Intrinsics.checkExpressionValueIsNotNull(robot_img, "robot_img");
            robot_img.setVisibility(8);
            FrameLayout flBirth = (FrameLayout) _$_findCachedViewById(C5508R.id.flBirth);
            Intrinsics.checkExpressionValueIsNotNull(flBirth, "flBirth");
            flBirth.setVisibility(0);
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_cake_anim)).playAnimation();
            return;
        }
        ImageView robot_img2 = (ImageView) _$_findCachedViewById(C5508R.id.robot_img);
        Intrinsics.checkExpressionValueIsNotNull(robot_img2, "robot_img");
        robot_img2.setVisibility(0);
        FrameLayout flBirth2 = (FrameLayout) _$_findCachedViewById(C5508R.id.flBirth);
        Intrinsics.checkExpressionValueIsNotNull(flBirth2, "flBirth");
        flBirth2.setVisibility(8);
        ImageView imageView2 = this.birthdayCrownImg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayCrownImg");
        }
        imageView2.setVisibility(8);
    }

    public final void setInputTaskCount(int i) {
        this.inputTaskCount = i;
    }

    public final int getInputTaskCount() {
        return this.inputTaskCount;
    }

    public final void startShowAnimation() {
        resetLayout();
        showAnimation();
    }

    private final void showAnimation() {
        if (this.fram != null) {
            this.fram = (FramesSequenceAnimation) null;
        }
        if (this.isLoadingImage) {
            return;
        }
        this.isLoadingImage = true;
        FaceAnimationUtil.INSTANCE.getCacheImage(new Function1<HashMap<Integer, Bitmap>, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$showAnimation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HashMap<Integer, Bitmap> hashMap) {
                invoke2(hashMap);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HashMap<Integer, Bitmap> it) {
                FramesSequenceAnimation framesSequenceAnimation;
                FramesSequenceAnimation framesSequenceAnimation2;
                FramesSequenceAnimation framesSequenceAnimation3;
                FramesSequenceAnimation framesSequenceAnimation4;
                FramesSequenceAnimation framesSequenceAnimation5;
                Intrinsics.checkParameterIsNotNull(it, "it");
                RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                robotDeliverTaskLayout.fram = new FramesSequenceAnimation(RobotDeliverTaskLayout.access$getRobotImageView$p(robotDeliverTaskLayout), FaceAnimationUtil.INSTANCE.getRobotAnimation(), it);
                framesSequenceAnimation = RobotDeliverTaskLayout.this.fram;
                if (framesSequenceAnimation == null) {
                    Intrinsics.throwNpe();
                }
                framesSequenceAnimation.setOnAnimStopListener(new FramesSequenceAnimation.OnAnimationListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$showAnimation$1.1
                    @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                    public void onAnimationEnd(FramesSequenceAnimation animation) {
                        RobotDeliverTaskLayout.this.isPlayingAnimation = false;
                        RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                    }

                    @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                    public void onAnimationStart(FramesSequenceAnimation animation) {
                        RobotDeliverTaskLayout.this.isPlayingAnimation = true;
                    }

                    @Override // com.pudutech.peanut.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                    public void onAnimationStopOrCancel(FramesSequenceAnimation animation) {
                        RobotDeliverTaskLayout.this.isPlayingAnimation = false;
                        RobotDeliverTaskLayout.updateViewState$default(RobotDeliverTaskLayout.this, false, 1, null);
                    }
                });
                framesSequenceAnimation2 = RobotDeliverTaskLayout.this.fram;
                if (framesSequenceAnimation2 == null) {
                    Intrinsics.throwNpe();
                }
                framesSequenceAnimation2.setDuration(1000L);
                framesSequenceAnimation3 = RobotDeliverTaskLayout.this.fram;
                if (framesSequenceAnimation3 == null) {
                    Intrinsics.throwNpe();
                }
                framesSequenceAnimation3.goBackStart();
                framesSequenceAnimation4 = RobotDeliverTaskLayout.this.fram;
                if (framesSequenceAnimation4 == null) {
                    Intrinsics.throwNpe();
                }
                framesSequenceAnimation4.setGoBack(false);
                framesSequenceAnimation5 = RobotDeliverTaskLayout.this.fram;
                if (framesSequenceAnimation5 == null) {
                    Intrinsics.throwNpe();
                }
                framesSequenceAnimation5.start();
                RobotDeliverTaskLayout.this.isLoadingImage = false;
            }
        });
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
            TextView textView = (TextView) relativeLayout.findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText("");
        }
        resetPalletDate();
    }

    public final void isAnimationEnd() {
        if (this.animationDrawable != null) {
            if (!Intrinsics.areEqual(r0.getFrame(r0.getNumberOfFrames() - 1), r0.getCurrent())) {
                this.mainHandler.sendEmptyMessageDelayed(this.CHECK_ANIMATION_END, 500L);
            } else {
                updateViewState$default(this, false, 1, null);
            }
        }
    }

    public final void start() {
        resetLayout();
        updateViewState$default(this, false, 1, null);
    }

    public static /* synthetic */ void initData$default(RobotDeliverTaskLayout robotDeliverTaskLayout, ArrayList arrayList, String str, MoveSortType moveSortType, int i, Object obj) {
        if ((i & 4) != 0) {
            moveSortType = MoveSortType.AUTO;
        }
        robotDeliverTaskLayout.initData(arrayList, str, moveSortType);
    }

    public final void initData(ArrayList<TrayModel> list, String currentTaskId, MoveSortType sortType) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Pdlog.m3273d(this.TAG, "initData : list = " + list + " ; currentTaskId = " + currentTaskId);
        resetPalletDate();
        this.sortType = sortType;
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            if (i > this.palletItemArray.size()) {
                return;
            }
            this.palletDates.set(i, trayModel);
            View findViewById = this.palletItemArray.get(i).findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[index].f…TextView>(R.id.task_name)");
            ((TextView) findViewById).setText(getPalletText(trayModel));
            if (currentTaskId != null && trayModel.getDeliveryModel(currentTaskId) != null) {
                this.currentSelectIndex = i;
            }
            i = i2;
        }
        updateViewState$default(this, false, 1, null);
    }

    public final boolean selectTray(int i) {
        if (i + 1 > this.inputTaskCount || !isTrayEnable(i)) {
            return false;
        }
        if (this.currentSelectIndex != i) {
            this.currentSelectIndex = i;
            updateViewState$default(this, false, 1, null);
        }
        return true;
    }

    public final MoveSortType getSortType() {
        return this.sortType;
    }

    public final String getPalletText(TrayModel t) {
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
        ((TextView) findViewById).setText(getPalletText(trayModel2));
        findNextSelect();
        updateViewState$default(this, false, 1, null);
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
            ((TextView) findViewById).setText(getPalletText(trayModel2));
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
                    ((TextView) findViewById2).setText(getPalletText(trayModel2));
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
                ((TextView) findViewById3).setText(getPalletText(trayModel4));
                checkPalletDateInfoAndDelete(name, info.getScid());
            }
        }
        if (!this.isPlayingAnimation) {
            updateViewState$default(this, false, 1, null);
        }
        Function0<Unit> function0 = this.onTaskChangeListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public static /* synthetic */ void setTaskName$default(RobotDeliverTaskLayout robotDeliverTaskLayout, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        robotDeliverTaskLayout.setTaskName(str, z);
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
        this.currentSelectText = this.currentSelectIndex;
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
            ((TextView) findViewById).setText(getPalletText(trayModel2));
            findNextSelect();
        } else if (checkAllTaskCount()) {
            if (trayModel2.getDeliveryModel(name) != null) {
                Pdlog.m3273d(this.TAG, "setTaskName , has same name : " + name);
                return;
            }
            trayModel2.getAllDestinations().add(0, new DeliveryModel(name, null, null, null, null, 28, null));
            View findViewById2 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C5508R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[currentS…TextView>(R.id.task_name)");
            ((TextView) findViewById2).setText(getPalletText(trayModel2));
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
        if (!this.isPlayingAnimation) {
            updateViewState(true);
        }
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
                foodInfo.removeIf(new Predicate<InformationSystemContract.OrderInfo>() { // from class: com.pudutech.peanut.robot_ui.ui.view.RobotDeliverTaskLayout$checkPalletDateInfoAndDelete$$inlined$forEachIndexed$lambda$1
                    @Override // java.util.function.Predicate
                    public final boolean test(InformationSystemContract.OrderInfo it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return Intrinsics.areEqual(it.getScid(), scid) && it.getOrderType() != InformationSystemContract.OrderInfo.Type.TrayOrder;
                    }
                });
                if (foodInfo.size() == 0) {
                    View findViewById = this.palletItemArray.get(i).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[index].f…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText((CharSequence) null);
                    this.palletDates.get(i).getAllDestinations().clear();
                } else {
                    View findViewById2 = this.palletItemArray.get(i).findViewById(C5508R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[index].f…TextView>(R.id.task_name)");
                    ((TextView) findViewById2).setText(getPalletText(trayModel));
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
        RobotDeliverTaskLayout robotDeliverTaskLayout = this;
        Iterator<T> it = robotDeliverTaskLayout.palletDates.iterator();
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
            if (((TrayModel) next).getAllDestinations().isEmpty() && i < robotDeliverTaskLayout.inputTaskCount && robotDeliverTaskLayout.isTrayEnable(i)) {
                break;
            }
            i = i2;
        }
        if (i <= -1) {
            return false;
        }
        this.currentSelectIndex = i;
        updateViewState$default(this, false, 1, null);
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

    public final boolean isTrayEnable(int i) {
        return PalletCountHelper.INSTANCE.isPalletEnable(i) || this.inputTaskCount == 1;
    }

    public final void removeAnimation() {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_cake_anim);
        if (lottieAnimationView == null || !lottieAnimationView.isAnimating()) {
            return;
        }
        lottieAnimationView.cancelAnimation();
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
                layoutInflater.inflate(C5508R.layout.layout_robot_deliver_task, this);
            }
        }
        View findViewById = findViewById(C5508R.id.root_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.root_view)");
        this.rootView = (RelativeLayout) findViewById;
        View findViewById2 = findViewById(C5508R.id.robot_img);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.robot_img)");
        this.robotImageView = (ImageView) findViewById2;
        View findViewById3 = findViewById(C5508R.id.birthday_crown_img);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(R.id.birthday_crown_img)");
        this.birthdayCrownImg = (ImageView) findViewById3;
        this.palletItemArray.add(findViewById(C5508R.id.layout_pallet_item_1));
        this.palletItemArray.add(findViewById(C5508R.id.layout_pallet_item_2));
        this.palletItemArray.add(findViewById(C5508R.id.layout_pallet_item_3));
        this.palletItemArray.add(findViewById(C5508R.id.layout_pallet_item_4));
        this.palletItemArray.add(findViewById(C5508R.id.layout_pallet_item_5));
        resetPalletDate();
        setListener();
    }
}
