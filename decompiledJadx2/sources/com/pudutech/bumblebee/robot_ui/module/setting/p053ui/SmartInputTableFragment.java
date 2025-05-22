package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationIMEContract;
import com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationIMEPresenter;
import com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationModel;
import com.pudutech.bumblebee.presenter.delivery_task.input_method_task.WordModel;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SmartInputLetterWordAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SmartInputResultAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.widget.loopview.MessageHandler;
import com.pudutech.freeinstall_ui.utils.Constants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.anko.Sdk27PropertiesKt;
import org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: SmartInputTableFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020\u001dH\u0002J\b\u0010;\u001a\u000201H\u0002J\u0010\u0010<\u001a\u00020\u00052\u0006\u0010=\u001a\u00020\u0005H\u0002J\b\u0010>\u001a\u000201H\u0002J\u0010\u0010?\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u0007H\u0016J\b\u0010@\u001a\u000201H\u0002J\b\u0010A\u001a\u000201H\u0002J&\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010G2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\b\u0010J\u001a\u000201H\u0016J\b\u0010K\u001a\u000201H\u0016J\b\u0010L\u001a\u000201H\u0016J\b\u0010M\u001a\u000201H\u0016J\u001a\u0010N\u001a\u0002012\u0006\u0010O\u001a\u00020C2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\u0010\u0010P\u001a\u0002012\u0006\u0010Q\u001a\u00020RH\u0002J\b\u0010S\u001a\u000201H\u0002J\u0010\u0010T\u001a\u0002012\u0006\u0010U\u001a\u00020\u0007H\u0002J\u0010\u0010V\u001a\u0002012\u0006\u0010W\u001a\u00020\u0007H\u0016J \u0010X\u001a\u0002012\u0016\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020Z0)j\b\u0012\u0004\u0012\u00020Z`+H\u0016J \u0010[\u001a\u0002012\u0016\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020\\0)j\b\u0012\u0004\u0012\u00020\\`+H\u0016J\b\u0010]\u001a\u000201H\u0002J\b\u0010^\u001a\u000201H\u0002J\u0014\u0010_\u001a\u000201*\u00020*2\u0006\u0010`\u001a\u00020\\H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0014\u0010\u0011R\u001b\u0010\u0016\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0017\u0010\u0011R\u001b\u0010\u0019\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001a\u0010\u0011R$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\"\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010(\u001a\u0012\u0012\u0004\u0012\u00020*0)j\b\u0012\u0004\u0012\u00020*`+X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010,\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0004\u0012\u000201\u0018\u00010-X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u000e\u00106\u001a\u000207X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SmartInputTableFragment;", "Lcom/pudutech/bumblebee/robot_ui/ui/fragment/ISelectTableFragment;", "Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/DestinationIMEContract$ViewInterface;", "()V", "DELETE_TASK", "", "TAG", "", "currentInputType", "destinationIMEPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/DestinationIMEPresenter;", "getDestinationIMEPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/DestinationIMEPresenter;", "destinationIMEPresenter$delegate", "Lkotlin/Lazy;", "dp10ToPixel", "getDp10ToPixel", "()I", "dp10ToPixel$delegate", "dp12ToPixel", "getDp12ToPixel", "dp12ToPixel$delegate", "dp6ToPixel", "getDp6ToPixel", "dp6ToPixel$delegate", "dp8ToPixel", "getDp8ToPixel", "dp8ToPixel$delegate", ES6Iterator.VALUE_PROPERTY, "", "isBirthdayTheme", "()Z", "setBirthdayTheme", "(Z)V", "isShowAllGroup", "setShowAllGroup", "letterAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SmartInputLetterWordAdapter;", "mainHandler", "Landroid/os/Handler;", "numberViews", "Ljava/util/ArrayList;", "Landroid/widget/TextView;", "Lkotlin/collections/ArrayList;", "onSelectTable", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", Constants.POINT_TYPE_TABLE, "", "getOnSelectTable", "()Lkotlin/jvm/functions/Function1;", "setOnSelectTable", "(Lkotlin/jvm/functions/Function1;)V", "simpleResultAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SmartInputResultAdapter;", "unSelectableClickCnt", "checkSelectClick", "b", "clearAllInput", "convertDpToPixel", "dp", "deleteInputContext", "hasTable", "initResultView", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "onViewCreated", "view", "postDeleteTask", "time", "", "removeDeleteTask", "setInputText", "s", "showInputted", "string", "showResults", "list", "Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/DestinationModel;", "showWordModels", "Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/WordModel;", "switchInputType", "updateTheme", "changeStyle", "wordModel", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SmartInputTableFragment extends ISelectTableFragment implements DestinationIMEContract.ViewInterface {
    private HashMap _$_findViewCache;
    private int currentInputType;
    private boolean isBirthdayTheme;
    private boolean isShowAllGroup;
    private SmartInputLetterWordAdapter letterAdapter;
    private Function1<? super String, Unit> onSelectTable;
    private SmartInputResultAdapter simpleResultAdapter;
    private int unSelectableClickCnt;
    private final String TAG = "SmartInputTableFragment";
    private final int DELETE_TASK = MessageHandler.WHAT_SMOOTH_SCROLL_INERTIA;

    /* renamed from: destinationIMEPresenter$delegate, reason: from kotlin metadata */
    private final Lazy destinationIMEPresenter = LazyKt.lazy(new Function0<DestinationIMEPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$destinationIMEPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DestinationIMEPresenter invoke() {
            DestinationIMEPresenter destinationIMEPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DestinationIMEPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(DestinationIMEPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                destinationIMEPresenter = new DestinationIMEPresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(DestinationIMEPresenter.class).toString());
                if (!(basePresenterInterface instanceof DestinationIMEPresenter)) {
                    basePresenterInterface = null;
                }
                destinationIMEPresenter = (DestinationIMEPresenter) basePresenterInterface;
            }
            if (destinationIMEPresenter == null) {
                Intrinsics.throwNpe();
            }
            return destinationIMEPresenter;
        }
    });
    private final ArrayList<TextView> numberViews = new ArrayList<>();
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = SmartInputTableFragment.this.DELETE_TASK;
            if (i2 != i) {
                return true;
            }
            SmartInputTableFragment.this.deleteInputContext();
            SmartInputTableFragment.this.postDeleteTask(50L);
            return true;
        }
    });

    /* renamed from: dp10ToPixel$delegate, reason: from kotlin metadata */
    private final Lazy dp10ToPixel = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$dp10ToPixel$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Integer invoke() {
            return Integer.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final int invoke2() {
            int convertDpToPixel;
            convertDpToPixel = SmartInputTableFragment.this.convertDpToPixel(10);
            return convertDpToPixel;
        }
    });

    /* renamed from: dp12ToPixel$delegate, reason: from kotlin metadata */
    private final Lazy dp12ToPixel = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$dp12ToPixel$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Integer invoke() {
            return Integer.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final int invoke2() {
            int convertDpToPixel;
            convertDpToPixel = SmartInputTableFragment.this.convertDpToPixel(24);
            return convertDpToPixel;
        }
    });

    /* renamed from: dp8ToPixel$delegate, reason: from kotlin metadata */
    private final Lazy dp8ToPixel = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$dp8ToPixel$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Integer invoke() {
            return Integer.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final int invoke2() {
            int convertDpToPixel;
            convertDpToPixel = SmartInputTableFragment.this.convertDpToPixel(16);
            return convertDpToPixel;
        }
    });

    /* renamed from: dp6ToPixel$delegate, reason: from kotlin metadata */
    private final Lazy dp6ToPixel = LazyKt.lazy(new Function0<Integer>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$dp6ToPixel$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Integer invoke() {
            return Integer.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final int invoke2() {
            int convertDpToPixel;
            convertDpToPixel = SmartInputTableFragment.this.convertDpToPixel(12);
            return convertDpToPixel;
        }
    });

    private final DestinationIMEPresenter getDestinationIMEPresenter() {
        return (DestinationIMEPresenter) this.destinationIMEPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDp10ToPixel() {
        return ((Number) this.dp10ToPixel.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDp12ToPixel() {
        return ((Number) this.dp12ToPixel.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDp6ToPixel() {
        return ((Number) this.dp6ToPixel.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDp8ToPixel() {
        return ((Number) this.dp8ToPixel.getValue()).intValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public boolean hasTable(String table) {
        Intrinsics.checkParameterIsNotNull(table, "table");
        return false;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    /* renamed from: isBirthdayTheme, reason: from getter */
    public boolean getIsBirthdayTheme() {
        return this.isBirthdayTheme;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void setBirthdayTheme(boolean z) {
        if (this.isBirthdayTheme != z) {
            this.isBirthdayTheme = z;
            updateTheme();
        }
        clearAllInput();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    /* renamed from: isShowAllGroup, reason: from getter */
    public boolean getIsShowAllGroup() {
        return this.isShowAllGroup;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void setShowAllGroup(boolean z) {
        if (this.isShowAllGroup != z) {
            clearAllInput();
            getDestinationIMEPresenter().loadMap(z);
        }
        this.isShowAllGroup = z;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public Function1<String, Unit> getOnSelectTable() {
        return this.onSelectTable;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment
    public void setOnSelectTable(Function1<? super String, Unit> function1) {
        this.onSelectTable = function1;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_smart_input_table, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initResultView();
        getDestinationIMEPresenter().replaceView(this);
        updateTheme();
    }

    private final void updateTheme() {
        if (getView() != null || isAdded()) {
            if (getIsBirthdayTheme()) {
                for (TextView textView : this.numberViews) {
                    Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_birthday_bg);
                    Context context = getContext();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    textView.setTextColor(context.getColorStateList(C4188R.color.selector_smart_input_table_item_birthday_tv));
                }
                ImageView delete_tv = (ImageView) _$_findCachedViewById(C4188R.id.delete_tv);
                Intrinsics.checkExpressionValueIsNotNull(delete_tv, "delete_tv");
                Sdk27PropertiesKt.setBackgroundResource(delete_tv, C4188R.drawable.selector_smart_input_table_item_birthday_bg);
                ((ImageView) _$_findCachedViewById(C4188R.id.delete_tv)).setImageResource(C4188R.drawable.icon_home_keyboard_delete_pre);
                ImageButton number_letter_switch_tv = (ImageButton) _$_findCachedViewById(C4188R.id.number_letter_switch_tv);
                Intrinsics.checkExpressionValueIsNotNull(number_letter_switch_tv, "number_letter_switch_tv");
                Sdk27PropertiesKt.setBackgroundResource(number_letter_switch_tv, C4188R.drawable.selector_smart_input_table_item_birthday_bg);
                _$_findCachedViewById(C4188R.id.view_bg).setBackgroundResource(C4188R.drawable.shape_radius_8_3a3c3e);
                ((RecyclerView) _$_findCachedViewById(C4188R.id.search_simple_result_rv)).setBackgroundResource(C4188R.drawable.shape_radius_8_3a3c3e);
            } else {
                for (TextView textView2 : this.numberViews) {
                    Sdk27PropertiesKt.setBackgroundResource(textView2, C4188R.drawable.selector_smart_input_table_item_bg);
                    Context context2 = getContext();
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    textView2.setTextColor(context2.getColorStateList(C4188R.color.selector_smart_input_table_item_tv));
                }
                ImageView delete_tv2 = (ImageView) _$_findCachedViewById(C4188R.id.delete_tv);
                Intrinsics.checkExpressionValueIsNotNull(delete_tv2, "delete_tv");
                Sdk27PropertiesKt.setBackgroundResource(delete_tv2, C4188R.drawable.selector_smart_input_table_item_bg);
                ((ImageView) _$_findCachedViewById(C4188R.id.delete_tv)).setImageResource(C4188R.drawable.selector_smart_input_table_delete_icon);
                ImageButton number_letter_switch_tv2 = (ImageButton) _$_findCachedViewById(C4188R.id.number_letter_switch_tv);
                Intrinsics.checkExpressionValueIsNotNull(number_letter_switch_tv2, "number_letter_switch_tv");
                Sdk27PropertiesKt.setBackgroundResource(number_letter_switch_tv2, C4188R.drawable.selector_smart_input_table_item_bg);
                _$_findCachedViewById(C4188R.id.view_bg).setBackgroundResource(C4188R.drawable.shape_radius_8_white);
                ((RecyclerView) _$_findCachedViewById(C4188R.id.search_simple_result_rv)).setBackgroundResource(C4188R.drawable.shape_radius_8_white);
            }
            SmartInputResultAdapter smartInputResultAdapter = this.simpleResultAdapter;
            if (smartInputResultAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simpleResultAdapter");
            }
            smartInputResultAdapter.setBirthday(getIsBirthdayTheme());
            SmartInputLetterWordAdapter smartInputLetterWordAdapter = this.letterAdapter;
            if (smartInputLetterWordAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("letterAdapter");
            }
            smartInputLetterWordAdapter.setBirthday(getIsBirthdayTheme());
            SmartInputResultAdapter smartInputResultAdapter2 = this.simpleResultAdapter;
            if (smartInputResultAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simpleResultAdapter");
            }
            smartInputResultAdapter2.notifyDataSetChanged();
            SmartInputLetterWordAdapter smartInputLetterWordAdapter2 = this.letterAdapter;
            if (smartInputLetterWordAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("letterAdapter");
            }
            smartInputLetterWordAdapter2.notifyDataSetChanged();
        }
    }

    private final void initResultView() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.simpleResultAdapter = new SmartInputResultAdapter(context, C4188R.layout.item_smart_input_result);
        RecyclerView search_simple_result_rv = (RecyclerView) _$_findCachedViewById(C4188R.id.search_simple_result_rv);
        Intrinsics.checkExpressionValueIsNotNull(search_simple_result_rv, "search_simple_result_rv");
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        search_simple_result_rv.setLayoutManager(new LinearLayoutManager(context2, 0, false));
        RecyclerView search_simple_result_rv2 = (RecyclerView) _$_findCachedViewById(C4188R.id.search_simple_result_rv);
        Intrinsics.checkExpressionValueIsNotNull(search_simple_result_rv2, "search_simple_result_rv");
        SmartInputResultAdapter smartInputResultAdapter = this.simpleResultAdapter;
        if (smartInputResultAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("simpleResultAdapter");
        }
        search_simple_result_rv2.setAdapter(smartInputResultAdapter);
        ((RecyclerView) _$_findCachedViewById(C4188R.id.search_simple_result_rv)).addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initResultView$1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int dp10ToPixel;
                Intrinsics.checkParameterIsNotNull(outRect, "outRect");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Intrinsics.checkParameterIsNotNull(state, "state");
                dp10ToPixel = SmartInputTableFragment.this.getDp10ToPixel();
                outRect.left = dp10ToPixel;
            }
        });
        SmartInputResultAdapter smartInputResultAdapter2 = this.simpleResultAdapter;
        if (smartInputResultAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("simpleResultAdapter");
        }
        smartInputResultAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initResultView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, 0 == true ? 1 : 0);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                try {
                    Object obj = adapter.getData().get(position);
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationModel");
                    }
                    DestinationModel destinationModel = (DestinationModel) obj;
                    Function1<String, Unit> onSelectTable = SmartInputTableFragment.this.getOnSelectTable();
                    if (onSelectTable != null) {
                        onSelectTable.invoke(destinationModel.getContent());
                    }
                    SmartInputTableFragment.this.clearAllInput();
                } catch (Exception e) {
                    str = SmartInputTableFragment.this.TAG;
                    Pdlog.m3274e(str, "simpleResultAdapter onSingleItemClick : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearAllInput() {
        getDestinationIMEPresenter().clear();
    }

    private final void initView() {
        this.numberViews.clear();
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_0_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_1_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_2_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_3_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_4_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_5_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_6_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_7_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_8_tv));
        this.numberViews.add((TextView) _$_findCachedViewById(C4188R.id.number_9_tv));
        int i = 0;
        for (Object obj : this.numberViews) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((TextView) obj).setTag(Integer.valueOf(i));
            i = i2;
        }
        ((ImageView) _$_findCachedViewById(C4188R.id.delete_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initView$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }
        }, 3, null));
        ImageView delete_tv = (ImageView) _$_findCachedViewById(C4188R.id.delete_tv);
        Intrinsics.checkExpressionValueIsNotNull(delete_tv, "delete_tv");
        Sdk27CoroutinesListenersWithCoroutinesKt.onTouch$default(delete_tv, null, false, new SmartInputTableFragment$initView$3(this, null), 3, null);
        ((ImageButton) _$_findCachedViewById(C4188R.id.number_letter_switch_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                int i3;
                super.onSingleClick();
                SmartInputTableFragment smartInputTableFragment = SmartInputTableFragment.this;
                i3 = smartInputTableFragment.currentInputType;
                smartInputTableFragment.currentInputType = i3 == 0 ? 1 : 0;
                SmartInputTableFragment.this.switchInputType();
            }
        });
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        this.letterAdapter = new SmartInputLetterWordAdapter(context);
        RecyclerView letter_input_rv = (RecyclerView) _$_findCachedViewById(C4188R.id.letter_input_rv);
        Intrinsics.checkExpressionValueIsNotNull(letter_input_rv, "letter_input_rv");
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        letter_input_rv.setLayoutManager(new GridLayoutManager(context2, 3));
        RecyclerView letter_input_rv2 = (RecyclerView) _$_findCachedViewById(C4188R.id.letter_input_rv);
        Intrinsics.checkExpressionValueIsNotNull(letter_input_rv2, "letter_input_rv");
        SmartInputLetterWordAdapter smartInputLetterWordAdapter = this.letterAdapter;
        if (smartInputLetterWordAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("letterAdapter");
        }
        letter_input_rv2.setAdapter(smartInputLetterWordAdapter);
        SmartInputLetterWordAdapter smartInputLetterWordAdapter2 = this.letterAdapter;
        if (smartInputLetterWordAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("letterAdapter");
        }
        smartInputLetterWordAdapter2.setOnLetterSelect(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = SmartInputTableFragment.this.TAG;
                Pdlog.m3275i(str, "on letter select");
                SmartInputTableFragment.this.checkSelectClick(true);
                SmartInputTableFragment.this.setInputText(it);
            }
        });
        ((RecyclerView) _$_findCachedViewById(C4188R.id.letter_input_rv)).addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initView$6
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int dp8ToPixel;
                int dp12ToPixel;
                int dp6ToPixel;
                int dp8ToPixel2;
                int dp12ToPixel2;
                Intrinsics.checkParameterIsNotNull(outRect, "outRect");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Intrinsics.checkParameterIsNotNull(state, "state");
                if (parent.getChildAdapterPosition(view) % 3 == 0) {
                    dp12ToPixel2 = SmartInputTableFragment.this.getDp12ToPixel();
                    outRect.left = dp12ToPixel2;
                }
                if (parent.getChildAdapterPosition(view) % 3 == 1) {
                    dp8ToPixel2 = SmartInputTableFragment.this.getDp8ToPixel();
                    outRect.left = dp8ToPixel2;
                }
                if (parent.getChildAdapterPosition(view) % 3 == 2) {
                    dp6ToPixel = SmartInputTableFragment.this.getDp6ToPixel();
                    outRect.left = dp6ToPixel;
                }
                if (parent.getChildAdapterPosition(view) < 3) {
                    dp12ToPixel = SmartInputTableFragment.this.getDp12ToPixel();
                    outRect.top = dp12ToPixel;
                } else {
                    dp8ToPixel = SmartInputTableFragment.this.getDp8ToPixel();
                    outRect.top = dp8ToPixel;
                }
            }
        });
        SmartInputLetterWordAdapter smartInputLetterWordAdapter3 = this.letterAdapter;
        if (smartInputLetterWordAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("letterAdapter");
        }
        smartInputLetterWordAdapter3.setOnUnSelectableClick(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                SmartInputTableFragment.this.checkSelectClick(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteInputContext() {
        getDestinationIMEPresenter().backspace();
        getDestinationIMEPresenter().refreshAfterBackspace();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchInputType() {
        if (this.currentInputType == 0) {
            Iterator<T> it = this.numberViews.iterator();
            while (it.hasNext()) {
                ((TextView) it.next()).setVisibility(0);
            }
            RecyclerView letter_input_rv = (RecyclerView) _$_findCachedViewById(C4188R.id.letter_input_rv);
            Intrinsics.checkExpressionValueIsNotNull(letter_input_rv, "letter_input_rv");
            letter_input_rv.setVisibility(8);
            return;
        }
        int i = 0;
        for (Object obj : this.numberViews) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((TextView) obj).setVisibility(i == 0 ? 8 : 4);
            i = i2;
        }
        RecyclerView letter_input_rv2 = (RecyclerView) _$_findCachedViewById(C4188R.id.letter_input_rv);
        Intrinsics.checkExpressionValueIsNotNull(letter_input_rv2, "letter_input_rv");
        letter_input_rv2.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postDeleteTask(long time) {
        removeDeleteTask();
        this.mainHandler.sendEmptyMessageDelayed(this.DELETE_TASK, time);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeDeleteTask() {
        this.mainHandler.removeMessages(this.DELETE_TASK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setInputText(String s) {
        Pdlog.m3273d(this.TAG, "setInputText s=" + s);
        getDestinationIMEPresenter().input(s);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        int tableInputSmartDefaultType = Constans.INSTANCE.getTableInputSmartDefaultType();
        if (this.currentInputType != tableInputSmartDefaultType) {
            this.currentInputType = tableInputSmartDefaultType;
            switchInputType();
        }
        clearAllInput();
        getDestinationIMEPresenter().loadMap(getIsShowAllGroup());
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        removeDeleteTask();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy ");
        getDestinationIMEPresenter().removeView(this);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationIMEContract.ViewInterface
    public void showInputted(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Pdlog.m3276v(this.TAG, "showInputted string=" + string);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationIMEContract.ViewInterface
    public void showResults(ArrayList<DestinationModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3276v(this.TAG, "showResults : list = " + list + "; ");
        TextView tvSearchResultTips = (TextView) _$_findCachedViewById(C4188R.id.tvSearchResultTips);
        Intrinsics.checkExpressionValueIsNotNull(tvSearchResultTips, "tvSearchResultTips");
        tvSearchResultTips.setVisibility(list.isEmpty() ? 0 : 8);
        SmartInputResultAdapter smartInputResultAdapter = this.simpleResultAdapter;
        if (smartInputResultAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("simpleResultAdapter");
        }
        smartInputResultAdapter.setNewData(list);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.input_method_task.DestinationIMEContract.ViewInterface
    public void showWordModels(ArrayList<WordModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3276v(this.TAG, "showWordModels : list = " + list + "; ");
        final ArrayList arrayList = new ArrayList();
        for (final WordModel wordModel : list) {
            if (wordModel.isNumber()) {
                TextView textView = this.numberViews.get(Integer.parseInt(wordModel.getContent()));
                Intrinsics.checkExpressionValueIsNotNull(textView, "numberViews[it.content.toInt()]");
                TextView textView2 = textView;
                textView2.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$showWordModels$$inlined$forEach$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View view) {
                        Intrinsics.checkParameterIsNotNull(view, "view");
                        if (WordModel.this.isSelectable()) {
                            this.checkSelectClick(true);
                            this.setInputText(WordModel.this.getContent());
                        } else {
                            this.checkSelectClick(false);
                        }
                    }
                }, 3, null));
                changeStyle(textView2, wordModel);
            } else {
                arrayList.add(wordModel);
            }
        }
        SmartInputLetterWordAdapter smartInputLetterWordAdapter = this.letterAdapter;
        if (smartInputLetterWordAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("letterAdapter");
        }
        smartInputLetterWordAdapter.setNewData(CollectionsKt.sortedWith(arrayList, new Comparator<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SmartInputTableFragment$showWordModels$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((WordModel) t).getContent(), ((WordModel) t2).getContent());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkSelectClick(boolean b) {
        this.unSelectableClickCnt = !b ? this.unSelectableClickCnt + 1 : 0;
        if (this.unSelectableClickCnt > 1) {
            Toast.makeText(getContext(), C4188R.string.pdStr2_46, 0).show();
        }
    }

    private final void changeStyle(TextView textView, WordModel wordModel) {
        if (wordModel.isSelectable()) {
            if (getIsBirthdayTheme()) {
                Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_birthday_bg);
                Context context = textView.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                textView.setTextColor(context.getColorStateList(C4188R.color.selector_smart_input_table_item_birthday_tv));
                return;
            }
            Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_bg);
            Context context2 = textView.getContext();
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            textView.setTextColor(context2.getColorStateList(C4188R.color.selector_smart_input_table_item_tv));
            return;
        }
        if (getIsBirthdayTheme()) {
            Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_birthday_bg2);
            Context context3 = textView.getContext();
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            textView.setTextColor(context3.getColorStateList(C4188R.color.selector_smart_input_table_item_birthday_tv2));
            return;
        }
        Sdk27PropertiesKt.setBackgroundResource(textView, C4188R.drawable.selector_smart_input_table_item_bg_2);
        Context context4 = textView.getContext();
        if (context4 == null) {
            Intrinsics.throwNpe();
        }
        textView.setTextColor(context4.getColorStateList(C4188R.color.selector_smart_input_table_item_tv2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int convertDpToPixel(int dp) {
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context!!.resources");
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "context!!.resources.displayMetrics");
        return (int) (dp * displayMetrics.density);
    }
}
