package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.information_system_task.InformationSystemContract;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.adapter.ArriveDishInfoAdapter;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import com.pudutech.peanut.robot_ui.widget.slide.LeftSlideLayout;
import com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: DeliverArriveLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 [2\u00020\u0001:\u0002[\\B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u00107\u001a\u00020\u0013J \u00108\u001a\u00020\t2\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020:0-j\b\u0012\u0004\u0012\u00020:`.H\u0002J \u0010;\u001a\u00020\u00132\u0016\u0010<\u001a\u0012\u0012\u0004\u0012\u00020=0-j\b\u0012\u0004\u0012\u00020=`.H\u0002J\u0006\u0010>\u001a\u00020\u001bJ\b\u0010?\u001a\u00020\u001bH\u0002J\u0010\u0010@\u001a\u00020\u00132\u0006\u0010A\u001a\u00020:H\u0002J\u0010\u0010B\u001a\u00020\u00132\u0006\u0010C\u001a\u00020\tH\u0002J\u0006\u0010D\u001a\u00020\u001bJ\u0010\u0010E\u001a\u00020\u001b2\u0006\u0010F\u001a\u000202H\u0002J(\u0010G\u001a\u00020\u001b2\u0006\u0010H\u001a\u00020\f2\u0016\u0010I\u001a\u0012\u0012\u0004\u0012\u00020=0-j\b\u0012\u0004\u0012\u00020=`.H\u0002J\u0010\u0010J\u001a\u00020\u001b2\u0006\u0010F\u001a\u000202H\u0002J\u000e\u0010K\u001a\u00020\u001b2\u0006\u0010L\u001a\u00020MJ(\u0010N\u001a\u00020\u001b2\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020O0-j\b\u0012\u0004\u0012\u00020O`.2\u0006\u0010P\u001a\u00020\fH\u0002J\u0018\u0010Q\u001a\u00020\u001b2\u0006\u0010R\u001a\u0002022\u0006\u0010S\u001a\u00020OH\u0002J&\u0010T\u001a\u00020\u001b2\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020O0-j\b\u0012\u0004\u0012\u00020O`.2\u0006\u0010P\u001a\u00020\fJ&\u0010U\u001a\u00020\u001b2\u0016\u00109\u001a\u0012\u0012\u0004\u0012\u00020O0-j\b\u0012\u0004\u0012\u00020O`.2\u0006\u0010P\u001a\u00020\fJ\u0010\u0010V\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\tH\u0002J\u0006\u0010W\u001a\u00020\u001bJ\u0010\u0010X\u001a\u00020\u001b2\b\b\u0001\u0010Y\u001a\u00020\tJ\b\u0010Z\u001a\u00020\u001bH\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u0012\u0012\u0004\u0012\u00020\u00010-j\b\u0012\u0004\u0012\u00020\u0001`.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006]"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/DeliverArriveLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "arriveDishInfoAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/ArriveDishInfoAdapter;", "currentMode", "currentTrayCount", "isOnArrivingStatus", "", "llConfirm", "Landroid/widget/LinearLayout;", "mSlideContainerLayout", "Lcom/pudutech/peanut/robot_ui/widget/slide/SlideContainerLayout;", "mTarget", "onArrivingLayoutClick", "Lkotlin/Function0;", "", "getOnArrivingLayoutClick", "()Lkotlin/jvm/functions/Function0;", "setOnArrivingLayoutClick", "(Lkotlin/jvm/functions/Function0;)V", "scaleAnimation", "Landroid/view/animation/ScaleAnimation;", "getScaleAnimation", "()Landroid/view/animation/ScaleAnimation;", "setScaleAnimation", "(Landroid/view/animation/ScaleAnimation;)V", "scaleOtherAnimation", "getScaleOtherAnimation", "setScaleOtherAnimation", "selectView", "Landroid/view/View;", "state", "trayInfoViews", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "tvContent", "Landroidx/appcompat/widget/AppCompatTextView;", "tvNo", "Landroid/widget/TextView;", "tvRed", "tvReturn", "tvType", "tvYes", "getIsNeedRecycle", "getLeftTableCount", "list", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "hasTrayOrders", "foodInfo", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "hideView", "initView", "isTaskFinish", "deliveryModel", "isTrayEnable", "int", "removeAnimation", "setCountTextSize", "textView", "setDishInfoContent", TypedValues.Attributes.S_TARGET, "orderInfos", "setFootInfoCountTextSize", "setOnClickFinishListener", "onLazyClickListener", "Lcom/pudutech/peanut/robot_ui/listener/OnLazyClickListener;", "setTayInfo", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "currentTableId", "setTrayText", "view", "t", "showArrivedLayout", "showArrivingLayout", "showTrayCount", "stopScaleAnim", "switchTheme", "theme", "updateRecycleLayout", "Companion", "THEME", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliverArriveLayout extends RelativeLayout {
    public static final int THEME_BIRTHDAY = 2;
    public static final int THEME_DIRECTLY = 1;
    public static final int THEME_NORMAL = 0;
    private final String TAG;
    private HashMap _$_findViewCache;
    private ArriveDishInfoAdapter arriveDishInfoAdapter;
    private int currentMode;
    private int currentTrayCount;
    private boolean isOnArrivingStatus;
    private LinearLayout llConfirm;
    private SlideContainerLayout mSlideContainerLayout;
    private String mTarget;
    private Function0<Unit> onArrivingLayoutClick;
    private ScaleAnimation scaleAnimation;
    private ScaleAnimation scaleOtherAnimation;
    private View selectView;
    private int state;
    private final ArrayList<RelativeLayout> trayInfoViews;
    private AppCompatTextView tvContent;
    private TextView tvNo;
    private TextView tvRed;
    private TextView tvReturn;
    private TextView tvType;
    private TextView tvYes;

    /* compiled from: DeliverArriveLayout.kt */
    @Retention(RetentionPolicy.SOURCE)
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/DeliverArriveLayout$THEME;", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
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

    public final ScaleAnimation getScaleAnimation() {
        return this.scaleAnimation;
    }

    public final void setScaleAnimation(ScaleAnimation scaleAnimation) {
        this.scaleAnimation = scaleAnimation;
    }

    public final ScaleAnimation getScaleOtherAnimation() {
        return this.scaleOtherAnimation;
    }

    public final void setScaleOtherAnimation(ScaleAnimation scaleAnimation) {
        this.scaleOtherAnimation = scaleAnimation;
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
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), C5508R.anim.btn_scale);
        if (loadAnimation == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.animation.ScaleAnimation");
        }
        this.scaleAnimation = (ScaleAnimation) loadAnimation;
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getContext(), C5508R.anim.btn_other_scale);
        if (loadAnimation2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.animation.ScaleAnimation");
        }
        this.scaleOtherAnimation = (ScaleAnimation) loadAnimation2;
        Context context = getContext();
        if (context != null) {
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            LayoutInflater layoutInflater = (LayoutInflater) systemService;
            if (layoutInflater != null) {
                layoutInflater.inflate(C5508R.layout.layout_deliver_arrive, this);
            }
        }
        this.tvReturn = (TextView) findViewById(C5508R.id.tvReturn);
        this.tvRed = (TextView) findViewById(C5508R.id.tvRed);
        this.tvType = (TextView) findViewById(C5508R.id.tvType);
        this.tvYes = (TextView) findViewById(C5508R.id.tvYes);
        this.tvNo = (TextView) findViewById(C5508R.id.tvNo);
        this.llConfirm = (LinearLayout) findViewById(C5508R.id.llConfirm);
        this.tvContent = (AppCompatTextView) findViewById(C5508R.id.tvContent);
        this.mSlideContainerLayout = (SlideContainerLayout) findViewById(C5508R.id.layout_slider_container);
        SlideContainerLayout slideContainerLayout = this.mSlideContainerLayout;
        if (slideContainerLayout != null) {
            View findViewById = findViewById(C5508R.id.layout_right_slider);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.layout_right_slider)");
            slideContainerLayout.addSlideView((LeftSlideLayout) findViewById);
        }
        this.trayInfoViews.add(findViewById(C5508R.id.arrive_tray_1));
        this.trayInfoViews.add(findViewById(C5508R.id.arrive_tray_2));
        this.trayInfoViews.add(findViewById(C5508R.id.arrive_tray_3));
        this.trayInfoViews.add(findViewById(C5508R.id.arrive_tray_4));
        this.trayInfoViews.add(findViewById(C5508R.id.arrive_tray_5));
        setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.DeliverArriveLayout$initView$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
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
        TextView textView = this.tvYes;
        if (textView != null) {
            ViewExtKt.onSingleClick(textView, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.DeliverArriveLayout$initView$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    LinearLayout linearLayout;
                    AppCompatTextView appCompatTextView;
                    TextView textView2;
                    TextView textView3;
                    TextView textView4;
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    linearLayout = DeliverArriveLayout.this.llConfirm;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(8);
                    }
                    appCompatTextView = DeliverArriveLayout.this.tvContent;
                    if (appCompatTextView != null) {
                        appCompatTextView.setVisibility(8);
                    }
                    textView2 = DeliverArriveLayout.this.tvRed;
                    if (textView2 != null) {
                        textView2.setVisibility(0);
                    }
                    textView3 = DeliverArriveLayout.this.tvType;
                    if (textView3 != null) {
                        textView3.setText(DeliverArriveLayout.this.getContext().getString(C5508R.string.desk_have_return));
                    }
                    textView4 = DeliverArriveLayout.this.tvType;
                    if (textView4 != null) {
                        Context context2 = DeliverArriveLayout.this.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                        textView4.setTextColor(context2.getResources().getColor(C5508R.color.low_power_color));
                    }
                    DeliverArriveLayout.this.state = 1;
                    str = DeliverArriveLayout.this.mTarget;
                    if (str != null) {
                        TableTaskManager.INSTANCE.setReturnTask(str);
                    }
                }
            });
        }
        TextView textView2 = this.tvNo;
        if (textView2 != null) {
            ViewExtKt.onSingleClick(textView2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.DeliverArriveLayout$initView$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    TextView textView3;
                    LinearLayout linearLayout;
                    AppCompatTextView appCompatTextView;
                    AppCompatTextView appCompatTextView2;
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    textView3 = DeliverArriveLayout.this.tvReturn;
                    if (textView3 != null) {
                        textView3.setVisibility(0);
                    }
                    linearLayout = DeliverArriveLayout.this.llConfirm;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(8);
                    }
                    appCompatTextView = DeliverArriveLayout.this.tvContent;
                    if (appCompatTextView != null) {
                        str = DeliverArriveLayout.this.mTarget;
                        appCompatTextView.setText(str);
                    }
                    appCompatTextView2 = DeliverArriveLayout.this.tvContent;
                    if (appCompatTextView2 != null) {
                        appCompatTextView2.setVisibility(0);
                    }
                    DeliverArriveLayout.this.state = 0;
                }
            });
        }
        TextView textView3 = this.tvReturn;
        if (textView3 != null) {
            ViewExtKt.onSingleClick(textView3, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.view.DeliverArriveLayout$initView$4
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    AppCompatTextView appCompatTextView;
                    TextView textView4;
                    LinearLayout linearLayout;
                    AppCompatTextView appCompatTextView2;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    appCompatTextView = DeliverArriveLayout.this.tvContent;
                    if (appCompatTextView != null) {
                        appCompatTextView.setVisibility(0);
                    }
                    textView4 = DeliverArriveLayout.this.tvReturn;
                    if (textView4 != null) {
                        textView4.setVisibility(8);
                    }
                    linearLayout = DeliverArriveLayout.this.llConfirm;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(0);
                    }
                    appCompatTextView2 = DeliverArriveLayout.this.tvContent;
                    if (appCompatTextView2 != null) {
                        appCompatTextView2.setText(DeliverArriveLayout.this.getContext().getString(C5508R.string.is_return_pea));
                    }
                    DeliverArriveLayout.this.state = 0;
                }
            });
        }
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.arriveDishInfoAdapter = new ArriveDishInfoAdapter(context2);
        RecyclerView deliver_dish_recycle_view = (RecyclerView) _$_findCachedViewById(C5508R.id.deliver_dish_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(deliver_dish_recycle_view, "deliver_dish_recycle_view");
        deliver_dish_recycle_view.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView deliver_dish_recycle_view2 = (RecyclerView) _$_findCachedViewById(C5508R.id.deliver_dish_recycle_view);
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
            LinearLayout recycle_switch_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.recycle_switch_layout);
            Intrinsics.checkExpressionValueIsNotNull(recycle_switch_layout, "recycle_switch_layout");
            recycle_switch_layout.setVisibility(0);
            boolean z = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_deliver_recycle_plate_switch", false);
            Switch recycle_mode_switch = (Switch) _$_findCachedViewById(C5508R.id.recycle_mode_switch);
            Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch, "recycle_mode_switch");
            recycle_mode_switch.setChecked(z);
            ((Switch) _$_findCachedViewById(C5508R.id.recycle_mode_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.view.DeliverArriveLayout$updateRecycleLayout$1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    String str;
                    str = DeliverArriveLayout.this.TAG;
                    Pdlog.m3273d(str, "updateRecycleLayout recycle_mode_switch " + z2);
                    SpUtils.set(RobotContext.INSTANCE.getContext(), "key_deliver_recycle_plate_switch", z2);
                }
            });
        } else {
            Pdlog.m3273d(this.TAG, "updateRecycleLayout false");
            LinearLayout recycle_switch_layout2 = (LinearLayout) _$_findCachedViewById(C5508R.id.recycle_switch_layout);
            Intrinsics.checkExpressionValueIsNotNull(recycle_switch_layout2, "recycle_switch_layout");
            recycle_switch_layout2.setVisibility(8);
        }
        if (SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false)) {
            Button btn_finish = (Button) _$_findCachedViewById(C5508R.id.btn_finish);
            Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
            btn_finish.setBackground(getContext().getDrawable(C5508R.drawable.lottery_btn));
            String string = getContext().getString(C5508R.string.pdStr7_110);
            Button btn_finish2 = (Button) _$_findCachedViewById(C5508R.id.btn_finish);
            Intrinsics.checkExpressionValueIsNotNull(btn_finish2, "btn_finish");
            btn_finish2.setText(string);
            Context context = getContext();
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            UiUtils.adjustTvTextSize((Button) _$_findCachedViewById(C5508R.id.btn_finish), SizeUtils.dp2px(context, context2.getResources().getDimension(C5508R.dimen.deliver_arrive_btn_w)), string);
        }
    }

    public final void setOnClickFinishListener(OnLazyClickListener onLazyClickListener) {
        Intrinsics.checkParameterIsNotNull(onLazyClickListener, "onLazyClickListener");
        ((Button) _$_findCachedViewById(C5508R.id.btn_finish)).setOnClickListener(onLazyClickListener);
    }

    private final void setDishInfoContent(String target, ArrayList<InformationSystemContract.OrderInfo> orderInfos) {
        if (orderInfos != null && orderInfos.size() > 0) {
            CardView small_info_layout = (CardView) _$_findCachedViewById(C5508R.id.small_info_layout);
            Intrinsics.checkExpressionValueIsNotNull(small_info_layout, "small_info_layout");
            small_info_layout.setVisibility(0);
            CardView small_list_info_layout = (CardView) _$_findCachedViewById(C5508R.id.small_list_info_layout);
            Intrinsics.checkExpressionValueIsNotNull(small_list_info_layout, "small_list_info_layout");
            small_list_info_layout.setVisibility(0);
            CardView arrive_task_tip_layout = (CardView) _$_findCachedViewById(C5508R.id.arrive_task_tip_layout);
            Intrinsics.checkExpressionValueIsNotNull(arrive_task_tip_layout, "arrive_task_tip_layout");
            arrive_task_tip_layout.setVisibility(4);
            ((TextView) _$_findCachedViewById(C5508R.id.small_table_id)).setTextSize(2, 104.0f);
            TextView small_table_id = (TextView) _$_findCachedViewById(C5508R.id.small_table_id);
            Intrinsics.checkExpressionValueIsNotNull(small_table_id, "small_table_id");
            small_table_id.setText(target);
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C5508R.id.small_table_id), (int) context.getResources().getDimension(C5508R.dimen.deliver_arrive_small_info_id_w), target);
            ArriveDishInfoAdapter arriveDishInfoAdapter = this.arriveDishInfoAdapter;
            if (arriveDishInfoAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arriveDishInfoAdapter");
            }
            arriveDishInfoAdapter.setNewData(orderInfos);
            return;
        }
        CardView small_info_layout2 = (CardView) _$_findCachedViewById(C5508R.id.small_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(small_info_layout2, "small_info_layout");
        small_info_layout2.setVisibility(8);
        CardView small_list_info_layout2 = (CardView) _$_findCachedViewById(C5508R.id.small_list_info_layout);
        Intrinsics.checkExpressionValueIsNotNull(small_list_info_layout2, "small_list_info_layout");
        small_list_info_layout2.setVisibility(8);
        CardView arrive_task_tip_layout2 = (CardView) _$_findCachedViewById(C5508R.id.arrive_task_tip_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_task_tip_layout2, "arrive_task_tip_layout");
        arrive_task_tip_layout2.setVisibility(0);
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(C5508R.id.table_id);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        appCompatTextView.setTextSize(0, context2.getResources().getDimension(C5508R.dimen.delivery_arrive_content_target_tv_s));
        this.mTarget = target;
        AppCompatTextView appCompatTextView2 = this.tvContent;
        if (appCompatTextView2 != null) {
            appCompatTextView2.setText(target);
        }
        AppCompatTextView table_id = (AppCompatTextView) _$_findCachedViewById(C5508R.id.table_id);
        Intrinsics.checkExpressionValueIsNotNull(table_id, "table_id");
        table_id.setText(target);
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        UiUtils.adjustTvTextSize((AppCompatTextView) _$_findCachedViewById(C5508R.id.table_id), ((int) context3.getResources().getDimension(C5508R.dimen.delivery_arrive_content_ll_w)) - 50, target);
    }

    private final void setTayInfo(ArrayList<TrayModel> list, String currentTableId) {
        View view;
        int i = 1;
        Pdlog.m3273d(this.TAG, "setTrayInfo = " + list + " , currentMovingText = " + currentTableId);
        ArrayList<InformationSystemContract.OrderInfo> arrayList = new ArrayList<>();
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            if (i2 > this.trayInfoViews.size() - i) {
                String str = this.TAG;
                Object[] objArr = new Object[i];
                objArr[0] = "setTrayInfo size more then " + this.trayInfoViews.size();
                Pdlog.m3274e(str, objArr);
                return;
            }
            RelativeLayout relativeLayout = this.trayInfoViews.get(i2);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "trayInfoViews[index]");
            RelativeLayout relativeLayout2 = relativeLayout;
            TextView textView = (TextView) relativeLayout2.findViewById(C5508R.id.task_name);
            CardView cardView = (CardView) relativeLayout2.findViewById(C5508R.id.card_view);
            TextView redCountTv = (TextView) relativeLayout2.findViewById(C5508R.id.red_count_tv);
            int leftTableCount = getLeftTableCount(trayModel.getAllDestinations());
            if (leftTableCount > i) {
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
            if (this.currentMode == 2) {
                cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.theme_main_color));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                cardView.setCardElevation(SizeUtils.dp2px(getContext(), 3.0f));
                cardView.setMaxCardElevation(SizeUtils.dp2px(getContext(), 5.0f));
                Sdk27PropertiesKt.setTextColor(textView, -1);
                textView.setBackgroundResource(0);
            } else if (!isTrayEnable(i2)) {
                cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.transparent));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f = 0;
                cardView.setCardElevation(f);
                cardView.setMaxCardElevation(f);
                textView.setBackgroundResource(C5508R.drawable.settings_tray_disable);
            } else if (current != null && current.getStatus() != DeliveryModel.TaskStatus.DONE && current.getStatus() != DeliveryModel.TaskStatus.ADVANCE_DONE) {
                cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.theme_main_color));
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
                this.selectView = relativeLayout2;
                ScaleAnimation scaleAnimation = this.scaleOtherAnimation;
                if (scaleAnimation != null && (view = this.selectView) != null) {
                    view.startAnimation(scaleAnimation);
                }
                textView.setBackgroundResource(0);
            } else {
                cardView.setCardBackgroundColor(getResources().getColor(C5508R.color.item_pallet_bg_normal));
                Intrinsics.checkExpressionValueIsNotNull(cardView, "cardView");
                float f2 = 0;
                cardView.setCardElevation(f2);
                cardView.setMaxCardElevation(f2);
                Sdk27PropertiesKt.setTextColor(textView, getResources().getColor(C5508R.color.font_color_1));
                textView.setBackgroundResource(0);
            }
            i2 = i3;
            i = 1;
        }
        LinearLayout linearLayout = this.llConfirm;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        AppCompatTextView appCompatTextView = this.tvContent;
        if (appCompatTextView != null) {
            appCompatTextView.setVisibility(0);
        }
        TextView textView2 = this.tvRed;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        TextView textView3 = this.tvReturn;
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        SlideContainerLayout slideContainerLayout = this.mSlideContainerLayout;
        if (slideContainerLayout != null) {
            slideContainerLayout.reset();
        }
        TextView textView4 = this.tvType;
        if (textView4 != null) {
            textView4.setText(getContext().getString(C5508R.string.pdStr2_14));
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
            if (deliveryModel.getStatus() != DeliveryModel.TaskStatus.ADVANCE_DONE && deliveryModel.getStatus() != DeliveryModel.TaskStatus.DONE) {
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
                    view.setTextSize(0, context.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_s));
                    Context context2 = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    UiUtils.adjustTvTextSize(view, (int) context2.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_w), sb.toString(), 22);
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
        view.setTextSize(0, context3.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_s));
        Context context22 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context22, "context");
        UiUtils.adjustTvTextSize(view, (int) context22.getResources().getDimension(C5508R.dimen.delivery_arrive_robot_tray_item_tv_w), sb.toString(), 22);
        if (z) {
        }
    }

    private final boolean isTaskFinish(DeliveryModel deliveryModel) {
        return deliveryModel.getStatus() == DeliveryModel.TaskStatus.DONE || deliveryModel.getStatus() == DeliveryModel.TaskStatus.ADVANCE_DONE || deliveryModel.getStatus() == DeliveryModel.TaskStatus.CANCEL;
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
            Switch recycle_mode_switch = (Switch) _$_findCachedViewById(C5508R.id.recycle_mode_switch);
            Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch, "recycle_mode_switch");
            if (recycle_mode_switch.isChecked()) {
                return true;
            }
        }
        return false;
    }

    public final void switchTheme(int theme) {
        this.currentMode = theme;
        if (theme == 0) {
            TextView textView = this.tvType;
            if (textView != null) {
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                textView.setTextColor(context.getResources().getColor(C5508R.color.font_color_1));
            }
            showTrayCount(PalletCountHelper.INSTANCE.getCount());
            SlideContainerLayout layout_slider_container = (SlideContainerLayout) _$_findCachedViewById(C5508R.id.layout_slider_container);
            Intrinsics.checkExpressionValueIsNotNull(layout_slider_container, "layout_slider_container");
            layout_slider_container.setVisibility(8);
            FrameLayout flBirth = (FrameLayout) _$_findCachedViewById(C5508R.id.flBirth);
            Intrinsics.checkExpressionValueIsNotNull(flBirth, "flBirth");
            flBirth.setVisibility(8);
            ImageView birthday_crown_img = (ImageView) _$_findCachedViewById(C5508R.id.birthday_crown_img);
            Intrinsics.checkExpressionValueIsNotNull(birthday_crown_img, "birthday_crown_img");
            birthday_crown_img.setVisibility(8);
            ImageView robot_img = (ImageView) _$_findCachedViewById(C5508R.id.robot_img);
            Intrinsics.checkExpressionValueIsNotNull(robot_img, "robot_img");
            robot_img.setVisibility(0);
            LottieAnimationView birth_fall_anim = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
            Intrinsics.checkExpressionValueIsNotNull(birth_fall_anim, "birth_fall_anim");
            birth_fall_anim.setVisibility(8);
            LottieAnimationView birth_float_anim = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
            Intrinsics.checkExpressionValueIsNotNull(birth_float_anim, "birth_float_anim");
            birth_float_anim.setVisibility(8);
            return;
        }
        if (theme == 1) {
            TextView textView2 = this.tvType;
            if (textView2 != null) {
                Context context2 = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                textView2.setTextColor(context2.getResources().getColor(C5508R.color.font_color_1));
            }
            showTrayCount(1);
            SlideContainerLayout layout_slider_container2 = (SlideContainerLayout) _$_findCachedViewById(C5508R.id.layout_slider_container);
            Intrinsics.checkExpressionValueIsNotNull(layout_slider_container2, "layout_slider_container");
            layout_slider_container2.setVisibility(8);
            FrameLayout flBirth2 = (FrameLayout) _$_findCachedViewById(C5508R.id.flBirth);
            Intrinsics.checkExpressionValueIsNotNull(flBirth2, "flBirth");
            flBirth2.setVisibility(8);
            ImageView birthday_crown_img2 = (ImageView) _$_findCachedViewById(C5508R.id.birthday_crown_img);
            Intrinsics.checkExpressionValueIsNotNull(birthday_crown_img2, "birthday_crown_img");
            birthday_crown_img2.setVisibility(8);
            ImageView robot_img2 = (ImageView) _$_findCachedViewById(C5508R.id.robot_img);
            Intrinsics.checkExpressionValueIsNotNull(robot_img2, "robot_img");
            robot_img2.setVisibility(0);
            LottieAnimationView birth_fall_anim2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
            Intrinsics.checkExpressionValueIsNotNull(birth_fall_anim2, "birth_fall_anim");
            birth_fall_anim2.setVisibility(8);
            LottieAnimationView birth_float_anim2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
            Intrinsics.checkExpressionValueIsNotNull(birth_float_anim2, "birth_float_anim");
            birth_float_anim2.setVisibility(8);
            return;
        }
        if (theme != 2) {
            return;
        }
        TextView textView3 = this.tvType;
        if (textView3 != null) {
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            textView3.setTextColor(context3.getResources().getColor(C5508R.color.white));
        }
        showTrayCount(1);
        setBackgroundColor(getContext().getColor(C5508R.color.theme_birthday_bg));
        ((CardView) _$_findCachedViewById(C5508R.id.arrive_tip_layout)).setCardBackgroundColor(getContext().getColor(C5508R.color.table_birthday_bg));
        SlideContainerLayout layout_slider_container3 = (SlideContainerLayout) _$_findCachedViewById(C5508R.id.layout_slider_container);
        Intrinsics.checkExpressionValueIsNotNull(layout_slider_container3, "layout_slider_container");
        layout_slider_container3.setVisibility(8);
        View childAt = ((CardView) _$_findCachedViewById(C5508R.id.arrive_tip_layout)).getChildAt(0);
        if (childAt == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        ((TextView) childAt).setTextColor(-1);
        ((CardView) _$_findCachedViewById(C5508R.id.arrive_task_tip_layout)).setCardBackgroundColor(getContext().getColor(C5508R.color.table_birthday_bg));
        ImageView birthday_crown_img3 = (ImageView) _$_findCachedViewById(C5508R.id.birthday_crown_img);
        Intrinsics.checkExpressionValueIsNotNull(birthday_crown_img3, "birthday_crown_img");
        birthday_crown_img3.setVisibility(0);
        FrameLayout flBirth3 = (FrameLayout) _$_findCachedViewById(C5508R.id.flBirth);
        Intrinsics.checkExpressionValueIsNotNull(flBirth3, "flBirth");
        flBirth3.setVisibility(0);
        ImageView robot_img3 = (ImageView) _$_findCachedViewById(C5508R.id.robot_img);
        Intrinsics.checkExpressionValueIsNotNull(robot_img3, "robot_img");
        robot_img3.setVisibility(4);
        LottieAnimationView birth_fall_anim3 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
        Intrinsics.checkExpressionValueIsNotNull(birth_fall_anim3, "birth_fall_anim");
        birth_fall_anim3.setVisibility(0);
        LottieAnimationView birth_float_anim3 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
        Intrinsics.checkExpressionValueIsNotNull(birth_float_anim3, "birth_float_anim");
        birth_float_anim3.setVisibility(0);
        ((LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim)).playAnimation();
        ((LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim)).playAnimation();
        ((LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_cake_anim)).playAnimation();
        ((TextView) _$_findCachedViewById(C5508R.id.recycle_iv)).setTextColor(-1);
        ((CardView) _$_findCachedViewById(C5508R.id.small_info_layout)).setCardBackgroundColor(getContext().getColor(C5508R.color.table_birthday_bg));
        ((CardView) _$_findCachedViewById(C5508R.id.small_list_info_layout)).setCardBackgroundColor(getContext().getColor(C5508R.color.table_birthday_bg));
        ArriveDishInfoAdapter arriveDishInfoAdapter = this.arriveDishInfoAdapter;
        if (arriveDishInfoAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arriveDishInfoAdapter");
        }
        arriveDishInfoAdapter.setBirthdayTheme(true);
    }

    private final void showTrayCount(int r12) {
        this.currentTrayCount = r12;
        int i = 0;
        for (Object obj : this.trayInfoViews) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout relativeLayout = (RelativeLayout) obj;
            if (i < r12) {
                relativeLayout.setVisibility(0);
            } else {
                relativeLayout.setVisibility(8);
            }
            i = i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(C5508R.id.tray_layout));
        constraintSet.clear(C5508R.id.arrive_tray_1, 1);
        constraintSet.clear(C5508R.id.arrive_tray_1, 2);
        constraintSet.clear(C5508R.id.arrive_tray_1, 3);
        constraintSet.clear(C5508R.id.arrive_tray_1, 4);
        if (r12 == 1) {
            constraintSet.connect(C5508R.id.arrive_tray_1, 3, 0, 3, 75);
            constraintSet.connect(C5508R.id.arrive_tray_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.arrive_tray_1, 2, 0, 2);
        } else {
            constraintSet.connect(C5508R.id.arrive_tray_1, 4, C5508R.id.arrive_tray_2, 3);
            constraintSet.connect(C5508R.id.arrive_tray_1, 1, 0, 1);
            constraintSet.connect(C5508R.id.arrive_tray_1, 2, 0, 2);
            constraintSet.connect(C5508R.id.arrive_tray_1, 3, 0, 3);
        }
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(C5508R.id.tray_layout));
    }

    public final void showArrivingLayout(ArrayList<TrayModel> list, String currentTableId) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Intrinsics.checkParameterIsNotNull(currentTableId, "currentTableId");
        Pdlog.m3273d(this.TAG, "showArrivingLayout");
        setTayInfo(list, currentTableId);
        setVisibility(0);
        CardView arrive_tip_layout = (CardView) _$_findCachedViewById(C5508R.id.arrive_tip_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_tip_layout, "arrive_tip_layout");
        arrive_tip_layout.setVisibility(4);
        Button btn_finish = (Button) _$_findCachedViewById(C5508R.id.btn_finish);
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
        CardView arrive_tip_layout = (CardView) _$_findCachedViewById(C5508R.id.arrive_tip_layout);
        Intrinsics.checkExpressionValueIsNotNull(arrive_tip_layout, "arrive_tip_layout");
        arrive_tip_layout.setVisibility(0);
        Button btn_finish = (Button) _$_findCachedViewById(C5508R.id.btn_finish);
        Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
        btn_finish.setVisibility(0);
        ScaleAnimation scaleAnimation = this.scaleAnimation;
        if (scaleAnimation != null) {
            ((Button) _$_findCachedViewById(C5508R.id.btn_finish)).startAnimation(scaleAnimation);
        }
    }

    public final void stopScaleAnim() {
        View view;
        Button button;
        if (this.scaleAnimation != null && (button = (Button) _$_findCachedViewById(C5508R.id.btn_finish)) != null) {
            button.clearAnimation();
        }
        if (this.scaleOtherAnimation == null || (view = this.selectView) == null) {
            return;
        }
        view.clearAnimation();
    }

    public final void hideView() {
        setVisibility(8);
    }

    public final void removeAnimation() {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_cake_anim);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
        if (lottieAnimationView2 != null && lottieAnimationView2.isAnimating()) {
            lottieAnimationView2.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView3 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
        if (lottieAnimationView3 == null || !lottieAnimationView3.isAnimating()) {
            return;
        }
        lottieAnimationView3.cancelAnimation();
    }
}
