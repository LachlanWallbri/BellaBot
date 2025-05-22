package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingContract;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.SettingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.CruiseSelectActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.SpecialModeSelectorActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.TurnBackActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.FunctionDialogAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.FunctionItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.greeter.GreeterFaceActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.recycle.RecycleTaskActivity;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.mpmodule.MusicPlayerHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeSettingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00016B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u00100\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\"H\u0016J\b\u00102\u001a\u00020\"H\u0016J\b\u00103\u001a\u00020\"H\u0016J\n\u00104\u001a\u000205*\u00020.R\u000e\u0010\t\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000¨\u00067"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "Landroid/app/Dialog;", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$ViewInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResID", "", "(Landroid/content/Context;I)V", "AUTO_CLOSE", "TAG", "", "functionDialogAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/FunctionDialogAdapter;", "functionSettingPresenter", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "getFunctionSettingPresenter", "()Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "functionSettingPresenter$delegate", "Lkotlin/Lazy;", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "mContext", "mainHandle", "Landroid/os/Handler;", "onHomeSettingDialogClickListener", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;", "getOnHomeSettingDialogClickListener", "()Lcom/pudutech/bumblebee/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;", "setOnHomeSettingDialogClickListener", "(Lcom/pudutech/bumblebee/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;)V", "view", "Landroid/view/View;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "dismiss", "dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "getData", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/FunctionItem;", "getFunAcIntent", "Landroid/content/Intent;", "fType", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "init", "initRecycleView", "onAttachedToWindow", "onDetachedFromWindow", "show", "toExternal", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$FunctionType;", "FunctionType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class HomeSettingDialog extends Dialog implements FunctionSettingContract.ViewInterface {
    private final int AUTO_CLOSE;
    private final String TAG;
    private FunctionDialogAdapter functionDialogAdapter;

    /* renamed from: functionSettingPresenter$delegate, reason: from kotlin metadata */
    private final Lazy functionSettingPresenter;
    private ItemTouchHelper itemTouchHelper;
    private Context mContext;
    private final Handler mainHandle;
    private OnHomeSettingDialogClickListener onHomeSettingDialogClickListener;
    private View view;

    /* compiled from: HomeSettingDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "", "(Ljava/lang/String;I)V", "RETURN_BACK", "DELIVERY_MODE", "RECYCLE_PLATE_MODE", "CRUISE_MODE", "SPECIAL_MODE", "DIRECT_MODE", "MUSIC_MODE", "BIRTHDAY_MODE", "SETTING", "GREETER_MODE", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum FunctionType {
        RETURN_BACK,
        DELIVERY_MODE,
        RECYCLE_PLATE_MODE,
        CRUISE_MODE,
        SPECIAL_MODE,
        DIRECT_MODE,
        MUSIC_MODE,
        BIRTHDAY_MODE,
        SETTING,
        GREETER_MODE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FunctionSettingPresenter getFunctionSettingPresenter() {
        return (FunctionSettingPresenter) this.functionSettingPresenter.getValue();
    }

    public static final /* synthetic */ FunctionDialogAdapter access$getFunctionDialogAdapter$p(HomeSettingDialog homeSettingDialog) {
        FunctionDialogAdapter functionDialogAdapter = homeSettingDialog.functionDialogAdapter;
        if (functionDialogAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        return functionDialogAdapter;
    }

    public final OnHomeSettingDialogClickListener getOnHomeSettingDialogClickListener() {
        return this.onHomeSettingDialogClickListener;
    }

    public final void setOnHomeSettingDialogClickListener(OnHomeSettingDialogClickListener onHomeSettingDialogClickListener) {
        this.onHomeSettingDialogClickListener = onHomeSettingDialogClickListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeSettingDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "HomeSettingDialog";
        this.functionSettingPresenter = LazyKt.lazy(HomeSettingDialog$functionSettingPresenter$2.INSTANCE);
        this.AUTO_CLOSE = 11;
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.HomeSettingDialog$mainHandle$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                String str;
                int i2 = message.what;
                i = HomeSettingDialog.this.AUTO_CLOSE;
                if (i2 == i) {
                    str = HomeSettingDialog.this.TAG;
                    Pdlog.m3273d(str, "AUTO_CLOSE");
                    HomeSettingDialog.this.dismiss();
                }
                return true;
            }
        });
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeSettingDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "HomeSettingDialog";
        this.functionSettingPresenter = LazyKt.lazy(HomeSettingDialog$functionSettingPresenter$2.INSTANCE);
        this.AUTO_CLOSE = 11;
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.HomeSettingDialog$mainHandle$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i2;
                String str;
                int i22 = message.what;
                i2 = HomeSettingDialog.this.AUTO_CLOSE;
                if (i22 == i2) {
                    str = HomeSettingDialog.this.TAG;
                    Pdlog.m3273d(str, "AUTO_CLOSE");
                    HomeSettingDialog.this.dismiss();
                }
                return true;
            }
        });
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        build();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Intrinsics.checkParameterIsNotNull(ev, "ev");
        this.mainHandle.removeMessages(this.AUTO_CLOSE);
        this.mainHandle.sendEmptyMessageDelayed(this.AUTO_CLOSE, 15000L);
        return super.dispatchTouchEvent(ev);
    }

    private final void initRecycleView() {
        LinearLayoutManager linearLayoutManager;
        List<FunctionItem> data = getData();
        RecyclerView fun_rv = (RecyclerView) findViewById(C4188R.id.fun_rv);
        Intrinsics.checkExpressionValueIsNotNull(fun_rv, "fun_rv");
        if (data.size() > 5) {
            linearLayoutManager = new GridLayoutManager(getContext(), 3, 1, false);
        } else {
            linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        }
        fun_rv.setLayoutManager(linearLayoutManager);
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.functionDialogAdapter = new FunctionDialogAdapter(context);
        FunctionDialogAdapter functionDialogAdapter = this.functionDialogAdapter;
        if (functionDialogAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        this.itemTouchHelper = new ItemTouchHelper(new ItemDragAndSwipeCallback(functionDialogAdapter));
        ItemTouchHelper itemTouchHelper = this.itemTouchHelper;
        if (itemTouchHelper != null) {
            itemTouchHelper.attachToRecyclerView((RecyclerView) findViewById(C4188R.id.fun_rv));
        }
        FunctionDialogAdapter functionDialogAdapter2 = this.functionDialogAdapter;
        if (functionDialogAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        ItemTouchHelper itemTouchHelper2 = this.itemTouchHelper;
        if (itemTouchHelper2 == null) {
            Intrinsics.throwNpe();
        }
        functionDialogAdapter2.enableDragItem(itemTouchHelper2);
        FunctionDialogAdapter functionDialogAdapter3 = this.functionDialogAdapter;
        if (functionDialogAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        functionDialogAdapter3.setOnItemDragListener(new OnItemDragListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.HomeSettingDialog$initRecycleView$1
            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragMoving(RecyclerView.ViewHolder p0, int p1, RecyclerView.ViewHolder p2, int p3) {
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                Intrinsics.checkParameterIsNotNull(p2, "p2");
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragStart(RecyclerView.ViewHolder p0, int p1) {
                Intrinsics.checkParameterIsNotNull(p0, "p0");
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragEnd(RecyclerView.ViewHolder p0, int p1) {
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                functionSettingPresenter = HomeSettingDialog.this.getFunctionSettingPresenter();
                List<FunctionItem> data2 = HomeSettingDialog.access$getFunctionDialogAdapter$p(HomeSettingDialog.this).getData();
                Intrinsics.checkExpressionValueIsNotNull(data2, "functionDialogAdapter.data");
                List<FunctionItem> list = data2;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(HomeSettingDialog.this.toExternal(((FunctionItem) it.next()).getType()));
                }
                functionSettingPresenter.updateOrderedFunctions(arrayList);
            }
        });
        RecyclerView fun_rv2 = (RecyclerView) findViewById(C4188R.id.fun_rv);
        Intrinsics.checkExpressionValueIsNotNull(fun_rv2, "fun_rv");
        FunctionDialogAdapter functionDialogAdapter4 = this.functionDialogAdapter;
        if (functionDialogAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        fun_rv2.setAdapter(functionDialogAdapter4);
        FunctionDialogAdapter functionDialogAdapter5 = this.functionDialogAdapter;
        if (functionDialogAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        functionDialogAdapter5.setNewData(data);
        FunctionDialogAdapter functionDialogAdapter6 = this.functionDialogAdapter;
        if (functionDialogAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionDialogAdapter");
        }
        functionDialogAdapter6.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.HomeSettingDialog$initRecycleView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, 0 == true ? 1 : 0);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                String str2;
                Intent funAcIntent;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                try {
                    Object obj = adapter.getData().get(position);
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.FunctionItem");
                    }
                    FunctionItem functionItem = (FunctionItem) obj;
                    if (functionItem.getType() == HomeSettingDialog.FunctionType.MUSIC_MODE) {
                        MusicPlayerHelper.getInstance().gotoMusicPlayer(HomeSettingDialog.this.getContext());
                    }
                    str2 = HomeSettingDialog.this.TAG;
                    Pdlog.m3273d(str2, "onSingleItemClick : item = " + functionItem + ' ');
                    OnHomeSettingDialogClickListener onHomeSettingDialogClickListener = HomeSettingDialog.this.getOnHomeSettingDialogClickListener();
                    if (onHomeSettingDialogClickListener != null) {
                        HomeSettingDialog.FunctionType type = functionItem.getType();
                        funAcIntent = HomeSettingDialog.this.getFunAcIntent(functionItem.getType());
                        onHomeSettingDialogClickListener.onFunClick(type, funAcIntent);
                    }
                    HomeSettingDialog.this.dismiss();
                } catch (Exception e) {
                    str = HomeSettingDialog.this.TAG;
                    Pdlog.m3274e(str, "onSingleItemClick : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getFunAcIntent(FunctionType fType) {
        switch (fType) {
            case RETURN_BACK:
                return new Intent(getContext(), (Class<?>) TurnBackActivity.class);
            case DELIVERY_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 0);
            case RECYCLE_PLATE_MODE:
                return new Intent(getContext(), (Class<?>) RecycleTaskActivity.class).putExtra("MODE_TYPE", 0);
            case CRUISE_MODE:
                return new Intent(getContext(), (Class<?>) CruiseSelectActivity.class);
            case SPECIAL_MODE:
                return new Intent(getContext(), (Class<?>) SpecialModeSelectorActivity.class);
            case DIRECT_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 4);
            case MUSIC_MODE:
            default:
                return null;
            case BIRTHDAY_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 2);
            case GREETER_MODE:
                return new Intent(getContext(), (Class<?>) GreeterFaceActivity.class);
            case SETTING:
                return new Intent(getContext(), (Class<?>) SettingActivity.class);
        }
    }

    private final List<FunctionItem> getData() {
        FunctionItem functionItem;
        List<FunctionSettingContract.FunctionType> loadOrderedFunctions = getFunctionSettingPresenter().loadOrderedFunctions();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(loadOrderedFunctions, 10));
        Iterator<T> it = loadOrderedFunctions.iterator();
        while (it.hasNext()) {
            switch ((FunctionSettingContract.FunctionType) it.next()) {
                case DELIVER_FUNCTION:
                    FunctionType functionType = FunctionType.DELIVERY_MODE;
                    int i = C4188R.drawable.icon_home_deliverymode;
                    Context context = this.mContext;
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    String string = context.getString(C4188R.string.pdStr2_1);
                    Intrinsics.checkExpressionValueIsNotNull(string, "mContext!!.getString(R.string.pdStr2_1)");
                    functionItem = new FunctionItem(functionType, i, string);
                    break;
                case CRUISE_FUNCTION:
                    FunctionType functionType2 = FunctionType.CRUISE_MODE;
                    int i2 = C4188R.drawable.icon_home_cruisemode;
                    Context context2 = this.mContext;
                    if (context2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string2 = context2.getString(C4188R.string.pdStr3_1);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "mContext!!.getString(R.string.pdStr3_1)");
                    functionItem = new FunctionItem(functionType2, i2, string2);
                    break;
                case DIRECT_DELIVER_FUNCTION:
                    FunctionType functionType3 = FunctionType.DIRECT_MODE;
                    int i3 = C4188R.drawable.icon_home_directmode;
                    Context context3 = this.mContext;
                    if (context3 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string3 = context3.getString(C4188R.string.pdStr4_1);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "mContext!!.getString(R.string.pdStr4_1)");
                    functionItem = new FunctionItem(functionType3, i3, string3);
                    break;
                case GREETER_FUNCTION:
                    FunctionType functionType4 = FunctionType.GREETER_MODE;
                    int i4 = C4188R.drawable.icon_home_welcomemode;
                    Context context4 = this.mContext;
                    if (context4 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string4 = context4.getString(C4188R.string.pdStr25_4);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "mContext!!.getString(R.string.pdStr25_4)");
                    functionItem = new FunctionItem(functionType4, i4, string4);
                    break;
                case SPECIAL_FUNCTION:
                    FunctionType functionType5 = FunctionType.SPECIAL_MODE;
                    int i5 = C4188R.drawable.icon_home_specialmode;
                    Context context5 = this.mContext;
                    if (context5 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string5 = context5.getString(C4188R.string.pdStr17_1);
                    Intrinsics.checkExpressionValueIsNotNull(string5, "mContext!!.getString(R.string.pdStr17_1)");
                    functionItem = new FunctionItem(functionType5, i5, string5);
                    break;
                case RETURN_FUNCTION:
                    FunctionType functionType6 = FunctionType.RETURN_BACK;
                    int i6 = C4188R.drawable.icon_home_return;
                    Context context6 = this.mContext;
                    if (context6 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string6 = context6.getString(C4188R.string.pdStr2_9);
                    Intrinsics.checkExpressionValueIsNotNull(string6, "mContext!!.getString(R.string.pdStr2_9)");
                    functionItem = new FunctionItem(functionType6, i6, string6);
                    break;
                case BIRTHDAY_FUNCTION:
                    FunctionType functionType7 = FunctionType.BIRTHDAY_MODE;
                    int i7 = C4188R.drawable.icon_home_bdaymode;
                    Context context7 = this.mContext;
                    if (context7 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string7 = context7.getString(C4188R.string.pdStr9_1);
                    Intrinsics.checkExpressionValueIsNotNull(string7, "mContext!!.getString(R.string.pdStr9_1)");
                    functionItem = new FunctionItem(functionType7, i7, string7);
                    break;
                case SETTING:
                    FunctionType functionType8 = FunctionType.SETTING;
                    int i8 = C4188R.drawable.icon_home_settings;
                    Context context8 = this.mContext;
                    if (context8 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string8 = context8.getString(C4188R.string.pdStr7_1);
                    Intrinsics.checkExpressionValueIsNotNull(string8, "mContext!!.getString(R.string.pdStr7_1)");
                    functionItem = new FunctionItem(functionType8, i8, string8);
                    break;
                case MUSIC_FUNCTION:
                    FunctionType functionType9 = FunctionType.MUSIC_MODE;
                    int i9 = C4188R.drawable.icon_home_music;
                    Context context9 = this.mContext;
                    if (context9 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string9 = context9.getString(C4188R.string.pdStr10_1);
                    Intrinsics.checkExpressionValueIsNotNull(string9, "mContext!!.getString(R.string.pdStr10_1)");
                    functionItem = new FunctionItem(functionType9, i9, string9);
                    break;
                case RECYCLE_FUNCTION:
                    FunctionType functionType10 = FunctionType.RECYCLE_PLATE_MODE;
                    int i10 = C4188R.drawable.icon_home_dishmode;
                    Context context10 = this.mContext;
                    if (context10 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string10 = context10.getString(C4188R.string.pdStr16_1);
                    Intrinsics.checkExpressionValueIsNotNull(string10, "mContext!!.getString(R.string.pdStr16_1)");
                    functionItem = new FunctionItem(functionType10, i10, string10);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            arrayList.add(functionItem);
        }
        return arrayList;
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.layout_setting_entrance_dialog, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ng_entrance_dialog, null)");
        this.view = inflate;
        requestWindowFeature(1);
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.HomeSettingDialog$build$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                HomeSettingDialog.this.dismiss();
            }
        }, 3, null));
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        setContentView(view2);
        setCanceledOnTouchOutside(true);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow");
        this.mainHandle.sendEmptyMessageDelayed(this.AUTO_CLOSE, 15000L);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow");
        this.mainHandle.removeMessages(this.AUTO_CLOSE);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        getFunctionSettingPresenter().detachView(this);
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void show() {
        getFunctionSettingPresenter().attachView(this);
        initRecycleView();
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-1, -1);
        }
    }

    public final FunctionSettingContract.FunctionType toExternal(FunctionType toExternal) {
        Intrinsics.checkParameterIsNotNull(toExternal, "$this$toExternal");
        switch (toExternal) {
            case RETURN_BACK:
                return FunctionSettingContract.FunctionType.RETURN_FUNCTION;
            case DELIVERY_MODE:
                return FunctionSettingContract.FunctionType.DELIVER_FUNCTION;
            case CRUISE_MODE:
                return FunctionSettingContract.FunctionType.CRUISE_FUNCTION;
            case SPECIAL_MODE:
                return FunctionSettingContract.FunctionType.SPECIAL_FUNCTION;
            case DIRECT_MODE:
                return FunctionSettingContract.FunctionType.DIRECT_DELIVER_FUNCTION;
            case MUSIC_MODE:
                return FunctionSettingContract.FunctionType.MUSIC_FUNCTION;
            case BIRTHDAY_MODE:
                return FunctionSettingContract.FunctionType.BIRTHDAY_FUNCTION;
            case SETTING:
                return FunctionSettingContract.FunctionType.SETTING;
            case GREETER_MODE:
                return FunctionSettingContract.FunctionType.GREETER_FUNCTION;
            case RECYCLE_PLATE_MODE:
                return FunctionSettingContract.FunctionType.RECYCLE_FUNCTION;
            default:
                return FunctionSettingContract.FunctionType.DELIVER_FUNCTION;
        }
    }
}
