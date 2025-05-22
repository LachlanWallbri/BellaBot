package com.pudutech.bumblebee.robot_ui.p054ui.view;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.DishInfoListEditDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.MultiTableEditDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.FramesSequenceAnimation;
import com.pudutech.bumblebee.robot_ui.util.FaceAnimationUtil;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020\t2\b\u0010>\u001a\u0004\u0018\u00010?H\u0002J\b\u0010@\u001a\u00020\u0019H\u0002J\u0018\u0010A\u001a\u00020#2\u0006\u0010B\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000eH\u0002J\b\u0010D\u001a\u00020#H\u0002J\u0016\u0010E\u001a\u0012\u0012\u0004\u0012\u00020504j\b\u0012\u0004\u0012\u000205`6J\u0006\u0010F\u001a\u000205J\u0006\u0010G\u001a\u00020\tJ\u0006\u0010H\u001a\u00020\u0019J\u0010\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u000205H\u0002J\u0006\u0010K\u001a\u00020;J\u0006\u0010L\u001a\u00020\u0019J \u0010M\u001a\u00020\u00192\u0016\u0010N\u001a\u0012\u0012\u0004\u0012\u00020O04j\b\u0012\u0004\u0012\u00020O`6H\u0002J2\u0010P\u001a\u00020#2\u0016\u0010Q\u001a\u0012\u0012\u0004\u0012\u00020504j\b\u0012\u0004\u0012\u000205`62\b\u0010R\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010:\u001a\u00020;J\u0012\u0010S\u001a\u00020#2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010T\u001a\u00020#H\u0002J\u0010\u0010U\u001a\u00020\u00192\u0006\u0010V\u001a\u00020\tH\u0002J\b\u0010W\u001a\u00020#H\u0002J\b\u0010X\u001a\u00020#H\u0002J\b\u0010Y\u001a\u00020\u0019H\u0002J\u000e\u0010Z\u001a\u00020\u00192\u0006\u0010V\u001a\u00020\tJ\u000e\u0010[\u001a\u00020#2\u0006\u0010V\u001a\u00020\tJ\b\u0010\\\u001a\u00020#H\u0002J\u0014\u0010]\u001a\u00020#2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020#0\"J\u0010\u0010_\u001a\u00020#2\u0006\u0010>\u001a\u00020?H\u0002J\u0016\u0010`\u001a\u00020#2\u0006\u0010B\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020OJ\u001a\u0010`\u001a\u00020#2\b\u0010B\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010b\u001a\u00020\u0019J.\u0010c\u001a\u00020#2\u0006\u0010B\u001a\u00020\u000e2\u0016\u0010d\u001a\u0012\u0012\u0004\u0012\u00020O04j\b\u0012\u0004\u0012\u00020O`62\u0006\u0010=\u001a\u00020\tJ\b\u0010e\u001a\u00020#H\u0002J\u0006\u0010f\u001a\u00020#J\u0006\u0010g\u001a\u00020#J\u000e\u0010h\u001a\u00020#2\u0006\u0010i\u001a\u00020\u0019J\u0006\u0010j\u001a\u00020#R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u00100\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010%\"\u0004\b2\u0010'R\u001e\u00103\u001a\u0012\u0012\u0004\u0012\u00020504j\b\u0012\u0004\u0012\u000205`6X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u000104j\b\u0012\u0004\u0012\u00020\u0001`6X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0001X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006k"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/RobotDeliverTaskLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "CHECK_ANIMATION_END", "MAX_MULTI_TABEL_CONUT", "TAG", "", "kotlin.jvm.PlatformType", "animationDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "birthdayCrownImg", "Landroid/widget/ImageView;", "currentSelectIndex", "fram", "Lcom/pudutech/bumblebee/robot_ui/ui/view/FramesSequenceAnimation;", "inputTaskCount", "isAnimating", "", "isBirthdayTheme", "isLoadingImage", "isModifyMode", "()Z", "setModifyMode", "(Z)V", "isPlayingAnimation", "mOnAnimationFinishListener", "Lkotlin/Function0;", "", "getMOnAnimationFinishListener", "()Lkotlin/jvm/functions/Function0;", "setMOnAnimationFinishListener", "(Lkotlin/jvm/functions/Function0;)V", "mainHandler", "Landroid/os/Handler;", "onItemCloseClickListener", "Landroid/view/View$OnClickListener;", "onItemTextClickListener", "onItemTextLongTimeClickListener", "Landroid/view/View$OnLongClickListener;", "onMultiTableEditClickListener", "onTaskChangeListener", "getOnTaskChangeListener", "setOnTaskChangeListener", "palletDates", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "palletItemArray", "robotImageView", "rootView", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "adapterNameViewSize", "index", "textView", "Landroid/widget/TextView;", "checkAllTaskCount", "checkPalletDateInfoAndDelete", "name", "scid", "findNextSelect", "getAllTask", "getCurrentSelectPallet", "getInputTaskCount", "getIsAnimating", "getPalletText", "t", "getSortType", "hasTask", "hasTrayOrders", "foodInfo", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "initData", "list", "currentTaskId", "initView", "isAnimationEnd", "isTrayEnable", "i", "resetLayout", "resetPalletDate", "selectNextEmptyPallet", "selectTray", "setInputTaskCount", "setListener", "setOnAnimationFinishListener", "listener", "setSanCountTextSize", "setTaskName", "info", "multiTable", "setTrayOrders", "orders", "showAnimation", "start", "startShowAnimation", "switchBirthday", "boolean", "updateViewState", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotDeliverTaskLayout extends RelativeLayout {
    private final int CHECK_ANIMATION_END;
    private final int MAX_MULTI_TABEL_CONUT;
    private final String TAG;
    private HashMap _$_findViewCache;
    private AnimationDrawable animationDrawable;
    private ImageView birthdayCrownImg;
    private int currentSelectIndex;
    private FramesSequenceAnimation fram;
    private int inputTaskCount;
    private boolean isAnimating;
    private boolean isBirthdayTheme;
    private boolean isLoadingImage;
    private boolean isModifyMode;
    private boolean isPlayingAnimation;
    public Function0<Unit> mOnAnimationFinishListener;
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
    private SortType sortType;

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

    /* renamed from: isModifyMode, reason: from getter */
    public final boolean getIsModifyMode() {
        return this.isModifyMode;
    }

    public final void setModifyMode(boolean z) {
        this.isModifyMode = z;
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
        this.inputTaskCount = 4;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = SortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        this.CHECK_ANIMATION_END = 99;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$mainHandler$1
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
        this.onItemTextClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextClickListener$1
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
                            RobotDeliverTaskLayout.this.updateViewState();
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                            return;
                        }
                        return;
                    }
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity");
                    }
                    String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr2_39);
                    Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_39)");
                    MyBaseActivity.showTipDialog$default((MyBaseActivity) context2, string, null, null, RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr8_15), 6, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemTextLongTimeClickListener = new View.OnLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1
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
                            dishInfoListEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1.1
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
                                        View findViewById = ((RelativeLayout) arrayList5.get(intValue)).findViewById(C4188R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        ((TextView) findViewById).setText("");
                                        arrayList6 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList6.get(intValue)).getAllDestinations().clear();
                                    } else {
                                        arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList2.get(intValue)).getAllDestinations().get(0).setFoodInfo(it);
                                        arrayList3 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById2 = ((RelativeLayout) arrayList3.get(intValue)).findViewById(C4188R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                                        arrayList4 = RobotDeliverTaskLayout.this.palletDates;
                                        Object obj2 = arrayList4.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "palletDates[i]");
                                        palletText = robotDeliverTaskLayout.getPalletText((TrayModel) obj2);
                                        ((TextView) findViewById2).setText(palletText);
                                    }
                                    RobotDeliverTaskLayout.this.updateViewState();
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
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletItemArray;
                    View findViewById = ((RelativeLayout) arrayList.get(intValue)).findViewById(C4188R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText("");
                    arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                    ((TrayModel) arrayList2.get(intValue)).getAllDestinations().clear();
                    RobotDeliverTaskLayout.this.currentSelectIndex = intValue;
                    RobotDeliverTaskLayout.this.updateViewState();
                    Function0<Unit> onTaskChangeListener = RobotDeliverTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onMultiTableEditClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1
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
                    multiTableEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<DeliveryModel>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1.1
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
                            View findViewById = ((RelativeLayout) arrayList2.get(intValue)).findViewById(C4188R.id.task_name);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                            palletText = RobotDeliverTaskLayout.this.getPalletText(trayModel);
                            ((TextView) findViewById).setText(palletText);
                            RobotDeliverTaskLayout.this.updateViewState();
                        }
                    });
                    multiTableEditDialog.setDishInfo(trayModel.getAllDestinations());
                    multiTableEditDialog.show();
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
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
        this.inputTaskCount = 4;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = SortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        this.CHECK_ANIMATION_END = 99;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$mainHandler$1
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
        this.onItemTextClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextClickListener$1
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
                            RobotDeliverTaskLayout.this.updateViewState();
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                            return;
                        }
                        return;
                    }
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity");
                    }
                    String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr2_39);
                    Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_39)");
                    MyBaseActivity.showTipDialog$default((MyBaseActivity) context2, string, null, null, RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr8_15), 6, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemTextLongTimeClickListener = new View.OnLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1
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
                            dishInfoListEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1.1
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
                                        View findViewById = ((RelativeLayout) arrayList5.get(intValue)).findViewById(C4188R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        ((TextView) findViewById).setText("");
                                        arrayList6 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList6.get(intValue)).getAllDestinations().clear();
                                    } else {
                                        arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList2.get(intValue)).getAllDestinations().get(0).setFoodInfo(it);
                                        arrayList3 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById2 = ((RelativeLayout) arrayList3.get(intValue)).findViewById(C4188R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                                        arrayList4 = RobotDeliverTaskLayout.this.palletDates;
                                        Object obj2 = arrayList4.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "palletDates[i]");
                                        palletText = robotDeliverTaskLayout.getPalletText((TrayModel) obj2);
                                        ((TextView) findViewById2).setText(palletText);
                                    }
                                    RobotDeliverTaskLayout.this.updateViewState();
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
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletItemArray;
                    View findViewById = ((RelativeLayout) arrayList.get(intValue)).findViewById(C4188R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText("");
                    arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                    ((TrayModel) arrayList2.get(intValue)).getAllDestinations().clear();
                    RobotDeliverTaskLayout.this.currentSelectIndex = intValue;
                    RobotDeliverTaskLayout.this.updateViewState();
                    Function0<Unit> onTaskChangeListener = RobotDeliverTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onMultiTableEditClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1
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
                    multiTableEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<DeliveryModel>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1.1
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
                            View findViewById = ((RelativeLayout) arrayList2.get(intValue)).findViewById(C4188R.id.task_name);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                            palletText = RobotDeliverTaskLayout.this.getPalletText(trayModel);
                            ((TextView) findViewById).setText(palletText);
                            RobotDeliverTaskLayout.this.updateViewState();
                        }
                    });
                    multiTableEditDialog.setDishInfo(trayModel.getAllDestinations());
                    multiTableEditDialog.show();
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
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
        this.inputTaskCount = 4;
        this.MAX_MULTI_TABEL_CONUT = 20;
        this.sortType = SortType.AUTO;
        this.palletItemArray = new ArrayList<>();
        this.palletDates = new ArrayList<>();
        this.CHECK_ANIMATION_END = 99;
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$mainHandler$1
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
        this.onItemTextClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextClickListener$1
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
                            RobotDeliverTaskLayout.this.updateViewState();
                            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                            return;
                        }
                        return;
                    }
                    Context context2 = RobotDeliverTaskLayout.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity");
                    }
                    String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr2_39);
                    Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_39)");
                    MyBaseActivity.showTipDialog$default((MyBaseActivity) context2, string, null, null, RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr8_15), 6, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onItemTextLongTimeClickListener = new View.OnLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1
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
                            dishInfoListEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemTextLongTimeClickListener$1.1
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
                                        View findViewById = ((RelativeLayout) arrayList5.get(intValue)).findViewById(C4188R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        ((TextView) findViewById).setText("");
                                        arrayList6 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList6.get(intValue)).getAllDestinations().clear();
                                    } else {
                                        arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                                        ((TrayModel) arrayList2.get(intValue)).getAllDestinations().get(0).setFoodInfo(it);
                                        arrayList3 = RobotDeliverTaskLayout.this.palletItemArray;
                                        View findViewById2 = ((RelativeLayout) arrayList3.get(intValue)).findViewById(C4188R.id.task_name);
                                        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                                        RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                                        arrayList4 = RobotDeliverTaskLayout.this.palletDates;
                                        Object obj2 = arrayList4.get(intValue);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "palletDates[i]");
                                        palletText = robotDeliverTaskLayout.getPalletText((TrayModel) obj2);
                                        ((TextView) findViewById2).setText(palletText);
                                    }
                                    RobotDeliverTaskLayout.this.updateViewState();
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
        this.onItemCloseClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onItemCloseClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                Object tag = v.getTag();
                if (tag != null) {
                    int intValue = ((Integer) tag).intValue();
                    arrayList = RobotDeliverTaskLayout.this.palletItemArray;
                    View findViewById = ((RelativeLayout) arrayList.get(intValue)).findViewById(C4188R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText("");
                    arrayList2 = RobotDeliverTaskLayout.this.palletDates;
                    ((TrayModel) arrayList2.get(intValue)).getAllDestinations().clear();
                    RobotDeliverTaskLayout.this.currentSelectIndex = intValue;
                    RobotDeliverTaskLayout.this.updateViewState();
                    Function0<Unit> onTaskChangeListener = RobotDeliverTaskLayout.this.getOnTaskChangeListener();
                    if (onTaskChangeListener != null) {
                        onTaskChangeListener.invoke();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        };
        this.onMultiTableEditClickListener = new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1
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
                    multiTableEditDialog.setOnDateEditFinishListener(new Function1<ArrayList<DeliveryModel>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$onMultiTableEditClickListener$1.1
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
                            View findViewById = ((RelativeLayout) arrayList2.get(intValue)).findViewById(C4188R.id.task_name);
                            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[i].findV…TextView>(R.id.task_name)");
                            palletText = RobotDeliverTaskLayout.this.getPalletText(trayModel);
                            ((TextView) findViewById).setText(palletText);
                            RobotDeliverTaskLayout.this.updateViewState();
                        }
                    });
                    multiTableEditDialog.setDishInfo(trayModel.getAllDestinations());
                    multiTableEditDialog.show();
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
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
            TextView it = (TextView) relativeLayout.findViewById(C4188R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            it.setTag(Integer.valueOf(i));
            it.setOnClickListener(this.onItemTextClickListener);
            it.setOnLongClickListener(this.onItemTextLongTimeClickListener);
            ImageView it2 = (ImageView) relativeLayout.findViewById(C4188R.id.clear);
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            it2.setTag(Integer.valueOf(i));
            it2.setOnClickListener(this.onItemCloseClickListener);
            ImageView it3 = (ImageView) relativeLayout.findViewById(C4188R.id.edit_iv);
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            it3.setTag(Integer.valueOf(i));
            it3.setOnClickListener(this.onMultiTableEditClickListener);
            i = i2;
        }
    }

    public final void updateViewState() {
        Iterator<T> it = this.palletItemArray.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) next;
            if (i2 <= this.inputTaskCount && relativeLayout.getVisibility() != 0) {
                relativeLayout.setVisibility(0);
            }
            TextView textView = (TextView) relativeLayout.findViewById(C4188R.id.task_name);
            adapterNameViewSize(i, textView);
            ImageView clear = (ImageView) relativeLayout.findViewById(C4188R.id.clear);
            CardView cardView = (CardView) relativeLayout.findViewById(C4188R.id.card_view);
            if (!isTrayEnable(i)) {
                Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
                textView.setText("");
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.transparent));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f = 0;
                cardView.setCardElevation(f);
                cardView.setMaxCardElevation(f);
                textView.setBackgroundResource(C4188R.drawable.settings_tray_disable);
            } else if (i == this.currentSelectIndex) {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.theme_main_color));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                cardView.setCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                cardView.setMaxCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                textView.setBackgroundResource(0);
                textView.setTextColor(-1);
            } else {
                cardView.setCardBackgroundColor(getResources().getColor(C4188R.color.item_pallet_bg_normal));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f2 = 0;
                cardView.setCardElevation(f2);
                cardView.setMaxCardElevation(f2);
                textView.setBackgroundResource(0);
                textView.setTextColor(getContext().getColor(C4188R.color.font_color_1));
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
            TextView redTv = (TextView) relativeLayout.findViewById(C4188R.id.red_count_tv);
            ImageView editIv = (ImageView) relativeLayout.findViewById(C4188R.id.edit_iv);
            int i3 = 8;
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
            ImageView ivPalletTts = (ImageView) relativeLayout.findViewById(C4188R.id.iv_pallet_tts);
            Intrinsics.checkExpressionValueIsNotNull(ivPalletTts, "ivPalletTts");
            ImageView imageView = ivPalletTts;
            if ((this.isModifyMode || trayModel2.getPalletTtsScheme() == null) ? false : true) {
                i3 = 0;
            }
            imageView.setVisibility(i3);
            i = i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(C4188R.id.pallet_layout));
        constraintSet.clear(C4188R.id.layout_pallet_item_1, 6);
        constraintSet.clear(C4188R.id.layout_pallet_item_1, 7);
        constraintSet.clear(C4188R.id.layout_pallet_item_1, 3);
        constraintSet.clear(C4188R.id.layout_pallet_item_1, 4);
        if (this.inputTaskCount == 1) {
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 4, C4188R.id.layout_pallet_item_2, 3);
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 6, 0, 6);
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 7, 0, 7);
        } else {
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 4, C4188R.id.layout_pallet_item_2, 3);
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 6, 0, 6);
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 7, 0, 7);
            constraintSet.connect(C4188R.id.layout_pallet_item_1, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C4188R.id.pallet_layout));
    }

    private final void adapterNameViewSize(int index, TextView textView) {
        if (textView != null) {
            String obj = textView.getText().toString();
            textView.setTextSize(48.0f);
            TrayModel trayModel = this.palletDates.get(index);
            Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[index]");
            TrayModel trayModel2 = trayModel;
            if (!trayModel2.getAllDestinations().isEmpty()) {
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                UiUtils.adjustTvTextSize(textView, (int) context.getResources().getDimension(C4188R.dimen.delivery_arrive_robot_tray_item_tv_w), obj, 22);
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

    public final void switchBirthday(boolean r4) {
        this.isBirthdayTheme = r4;
        if (r4) {
            ImageView imageView = this.birthdayCrownImg;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("birthdayCrownImg");
            }
            imageView.setVisibility(0);
            RelativeLayout relativeLayout = this.rootView;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            relativeLayout.setBackgroundResource(C4188R.drawable.bg_home_birthday);
            return;
        }
        ImageView imageView2 = this.birthdayCrownImg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("birthdayCrownImg");
        }
        imageView2.setVisibility(8);
        RelativeLayout relativeLayout2 = this.rootView;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        relativeLayout2.setBackgroundResource(0);
    }

    public final void setInputTaskCount(int i) {
        this.inputTaskCount = i;
    }

    public final int getInputTaskCount() {
        return this.inputTaskCount;
    }

    public final boolean getIsAnimating() {
        return this.isAnimating;
    }

    public final void startShowAnimation() {
        this.isAnimating = true;
        resetLayout();
        showAnimation();
    }

    private final void showAnimation() {
        FramesSequenceAnimation framesSequenceAnimation = this.fram;
        if (framesSequenceAnimation == null) {
            if (this.isLoadingImage) {
                return;
            }
            this.isLoadingImage = true;
            FaceAnimationUtil.INSTANCE.getCacheImage(new Function1<HashMap<Integer, Bitmap>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$showAnimation$1
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
                    FramesSequenceAnimation framesSequenceAnimation2;
                    FramesSequenceAnimation framesSequenceAnimation3;
                    FramesSequenceAnimation framesSequenceAnimation4;
                    FramesSequenceAnimation framesSequenceAnimation5;
                    FramesSequenceAnimation framesSequenceAnimation6;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    RobotDeliverTaskLayout robotDeliverTaskLayout = RobotDeliverTaskLayout.this;
                    robotDeliverTaskLayout.fram = new FramesSequenceAnimation(RobotDeliverTaskLayout.access$getRobotImageView$p(robotDeliverTaskLayout), FaceAnimationUtil.INSTANCE.getRobotAnimation(), it);
                    framesSequenceAnimation2 = RobotDeliverTaskLayout.this.fram;
                    if (framesSequenceAnimation2 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation2.setOnAnimStopListener(new FramesSequenceAnimation.OnAnimationListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$showAnimation$1.1
                        @Override // com.pudutech.bumblebee.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationEnd(FramesSequenceAnimation animation) {
                            RobotDeliverTaskLayout.this.isPlayingAnimation = false;
                            RobotDeliverTaskLayout.this.getMOnAnimationFinishListener().invoke();
                            RobotDeliverTaskLayout.this.isAnimating = false;
                        }

                        @Override // com.pudutech.bumblebee.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStart(FramesSequenceAnimation animation) {
                            RobotDeliverTaskLayout.this.isPlayingAnimation = true;
                        }

                        @Override // com.pudutech.bumblebee.robot_ui.ui.view.FramesSequenceAnimation.OnAnimationListener
                        public void onAnimationStopOrCancel(FramesSequenceAnimation animation) {
                            RobotDeliverTaskLayout.this.isPlayingAnimation = false;
                            RobotDeliverTaskLayout.this.getMOnAnimationFinishListener().invoke();
                            RobotDeliverTaskLayout.this.isAnimating = false;
                        }
                    });
                    framesSequenceAnimation3 = RobotDeliverTaskLayout.this.fram;
                    if (framesSequenceAnimation3 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation3.setDuration(1000L);
                    framesSequenceAnimation4 = RobotDeliverTaskLayout.this.fram;
                    if (framesSequenceAnimation4 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation4.goBackStart();
                    framesSequenceAnimation5 = RobotDeliverTaskLayout.this.fram;
                    if (framesSequenceAnimation5 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation5.setGoBack(false);
                    framesSequenceAnimation6 = RobotDeliverTaskLayout.this.fram;
                    if (framesSequenceAnimation6 == null) {
                        Intrinsics.throwNpe();
                    }
                    framesSequenceAnimation6.start();
                    RobotDeliverTaskLayout.this.isLoadingImage = false;
                }
            });
            return;
        }
        if (framesSequenceAnimation == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation.setDuration(1000L);
        FramesSequenceAnimation framesSequenceAnimation2 = this.fram;
        if (framesSequenceAnimation2 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation2.goBackStart();
        FramesSequenceAnimation framesSequenceAnimation3 = this.fram;
        if (framesSequenceAnimation3 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation3.setGoBack(false);
        FramesSequenceAnimation framesSequenceAnimation4 = this.fram;
        if (framesSequenceAnimation4 == null) {
            Intrinsics.throwNpe();
        }
        framesSequenceAnimation4.start();
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
            TextView textView = (TextView) relativeLayout.findViewById(C4188R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText("");
        }
        resetPalletDate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void isAnimationEnd() {
        if (this.animationDrawable != null) {
            if (!Intrinsics.areEqual(r0.getFrame(r0.getNumberOfFrames() - 1), r0.getCurrent())) {
                this.mainHandler.sendEmptyMessageDelayed(this.CHECK_ANIMATION_END, 500L);
                return;
            }
            updateViewState();
            Function0<Unit> function0 = this.mOnAnimationFinishListener;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mOnAnimationFinishListener");
            }
            function0.invoke();
            this.isAnimating = false;
        }
    }

    public final Function0<Unit> getMOnAnimationFinishListener() {
        Function0<Unit> function0 = this.mOnAnimationFinishListener;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mOnAnimationFinishListener");
        }
        return function0;
    }

    public final void setMOnAnimationFinishListener(Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.mOnAnimationFinishListener = function0;
    }

    public final void setOnAnimationFinishListener(Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mOnAnimationFinishListener = listener;
    }

    public final void start() {
        resetLayout();
        updateViewState();
    }

    public static /* synthetic */ void initData$default(RobotDeliverTaskLayout robotDeliverTaskLayout, ArrayList arrayList, String str, SortType sortType, int i, Object obj) {
        if ((i & 4) != 0) {
            sortType = SortType.AUTO;
        }
        robotDeliverTaskLayout.initData(arrayList, str, sortType);
    }

    public final void initData(ArrayList<TrayModel> list, String currentTaskId, SortType sortType) {
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
            TrayModel trayModel = (TrayModel) obj;
            if (i > this.palletItemArray.size()) {
                return;
            }
            this.palletDates.set(i, trayModel);
            View findViewById = this.palletItemArray.get(i).findViewById(C4188R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[index].f…TextView>(R.id.task_name)");
            ((TextView) findViewById).setText(getPalletText(trayModel));
            if (currentTaskId != null && trayModel.getDeliveryModel(currentTaskId) != null) {
                this.currentSelectIndex = i;
            }
            i = i2;
        }
        updateViewState();
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

    public final SortType getSortType() {
        return this.sortType;
    }

    /* JADX INFO: Access modifiers changed from: private */
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
        trayModel2.getAllDestinations().add(new DeliveryModel(name, orders, null, null, 12, null));
        View findViewById = this.palletItemArray.get(this.currentSelectIndex).findViewById(C4188R.id.task_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[currentS…TextView>(R.id.task_name)");
        ((TextView) findViewById).setText(getPalletText(trayModel2));
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
            trayModel2.getAllDestinations().add(new DeliveryModel(name, arrayList, null, null, 12, null));
            View findViewById = this.palletItemArray.get(this.currentSelectIndex).findViewById(C4188R.id.task_name);
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
                    View findViewById2 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C4188R.id.task_name);
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
                trayModel4.getAllDestinations().add(new DeliveryModel(name, arrayList2, null, null, 12, null));
                View findViewById3 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C4188R.id.task_name);
                Intrinsics.checkExpressionValueIsNotNull(findViewById3, "palletItemArray[currentS…TextView>(R.id.task_name)");
                ((TextView) findViewById3).setText(getPalletText(trayModel4));
                checkPalletDateInfoAndDelete(name, info.getScid());
            }
        }
        if (!this.isPlayingAnimation) {
            updateViewState();
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
        this.sortType = Constans.INSTANCE.getSortType();
        TrayModel trayModel = this.palletDates.get(this.currentSelectIndex);
        Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[currentSelectIndex]");
        TrayModel trayModel2 = trayModel;
        if (!multiTable) {
            if (trayModel2.getAllDestinations().size() > 0) {
                DeliveryModel deliveryModel = trayModel2.getAllDestinations().get(0);
                Intrinsics.checkExpressionValueIsNotNull(deliveryModel, "trayModel.allDestinations[0]");
                DeliveryModel deliveryModel2 = deliveryModel;
                trayModel2.getAllDestinations().clear();
                deliveryModel2.setDestination(name);
                trayModel2.getAllDestinations().add(deliveryModel2);
            } else {
                trayModel2.getAllDestinations().add(new DeliveryModel(name, null, null, null, 12, null));
            }
            View findViewById = this.palletItemArray.get(this.currentSelectIndex).findViewById(C4188R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[currentS…TextView>(R.id.task_name)");
            ((TextView) findViewById).setText(getPalletText(trayModel2));
            findNextSelect();
        } else if (checkAllTaskCount()) {
            if (trayModel2.getDeliveryModel(name) != null) {
                Pdlog.m3273d(this.TAG, "setTaskName , has same name : " + name);
                return;
            }
            trayModel2.getAllDestinations().add(0, new DeliveryModel(name, null, null, null, 12, null));
            View findViewById2 = this.palletItemArray.get(this.currentSelectIndex).findViewById(C4188R.id.task_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "palletItemArray[currentS…TextView>(R.id.task_name)");
            ((TextView) findViewById2).setText(getPalletText(trayModel2));
        } else {
            Context context = getContext();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getContext().getString(C4188R.string.pdStr2_29);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr2_29)");
            Object[] objArr = {Integer.valueOf(this.MAX_MULTI_TABEL_CONUT)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(context, format, new Object[0]);
            return;
        }
        if (!this.isPlayingAnimation) {
            updateViewState();
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
                foodInfo.removeIf(new Predicate<InformationSystemContract.OrderInfo>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RobotDeliverTaskLayout$checkPalletDateInfoAndDelete$$inlined$forEachIndexed$lambda$1
                    @Override // java.util.function.Predicate
                    public final boolean test(InformationSystemContract.OrderInfo it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return Intrinsics.areEqual(it.getScid(), scid) && it.getOrderType() != InformationSystemContract.OrderInfo.Type.TrayOrder;
                    }
                });
                if (foodInfo.size() == 0) {
                    View findViewById = this.palletItemArray.get(i).findViewById(C4188R.id.task_name);
                    Intrinsics.checkExpressionValueIsNotNull(findViewById, "palletItemArray[index].f…TextView>(R.id.task_name)");
                    ((TextView) findViewById).setText((CharSequence) null);
                    this.palletDates.get(i).getAllDestinations().clear();
                } else {
                    View findViewById2 = this.palletItemArray.get(i).findViewById(C4188R.id.task_name);
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

    public final TrayModel getCurrentSelectPallet() {
        TrayModel trayModel = this.palletDates.get(this.currentSelectIndex);
        Intrinsics.checkExpressionValueIsNotNull(trayModel, "palletDates[currentSelectIndex]");
        return trayModel;
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

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTrayEnable(int i) {
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
                layoutInflater.inflate(C4188R.layout.layout_robot_deliver_task, this);
            }
        }
        View findViewById = findViewById(C4188R.id.root_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.root_view)");
        this.rootView = (RelativeLayout) findViewById;
        View findViewById2 = findViewById(C4188R.id.robot_img);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.robot_img)");
        this.robotImageView = (ImageView) findViewById2;
        View findViewById3 = findViewById(C4188R.id.birthday_crown_img);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(R.id.birthday_crown_img)");
        this.birthdayCrownImg = (ImageView) findViewById3;
        this.palletItemArray.add(findViewById(C4188R.id.layout_pallet_item_1));
        this.palletItemArray.add(findViewById(C4188R.id.layout_pallet_item_2));
        this.palletItemArray.add(findViewById(C4188R.id.layout_pallet_item_3));
        this.palletItemArray.add(findViewById(C4188R.id.layout_pallet_item_4));
        this.palletItemArray.add(findViewById(C4188R.id.layout_pallet_item_5));
        resetPalletDate();
        setListener();
    }
}
