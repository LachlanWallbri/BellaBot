package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.AppSettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.DeliverySettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.SolicitSettingActivity;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.OneKeyChargingActivity;
import com.pudutech.peanut.robot_ui.p063ui.RowNumberActivity;
import com.pudutech.peanut.robot_ui.p063ui.SolicitCustomerActivity;
import com.pudutech.peanut.robot_ui.p063ui.TurnBackActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.FunctionDialogAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.FunctionItem;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.cruise.CruiseSelectActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import com.pudutech.peanut.robot_ui.p063ui.greeter.GreeterMenuActivity;
import com.pudutech.peanut.robot_ui.p063ui.view.MaxHeightRecyclerView;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.widget.tablayout.SlidingScaleTabLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeSettingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u000234B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0006H\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020$H\u0002J\b\u0010.\u001a\u00020$H\u0016J\b\u0010/\u001a\u00020$H\u0016J\u0010\u00100\u001a\u00020$2\u0006\u00101\u001a\u00020\u0006H\u0002J\b\u00102\u001a\u00020$H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\n0\n0\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000¨\u00065"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResID", "", "(Landroid/content/Context;I)V", "AUTO_CLOSE", "TAG", "", "functionDialogAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/FunctionDialogAdapter;", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/FunctionItem;", "Lkotlin/collections/ArrayList;", "getList", "()Ljava/util/ArrayList;", "mainHandle", "Landroid/os/Handler;", "onHomeSettingDialogClickListener", "Lcom/pudutech/peanut/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;", "getOnHomeSettingDialogClickListener", "()Lcom/pudutech/peanut/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;", "setOnHomeSettingDialogClickListener", "(Lcom/pudutech/peanut/robot_ui/ui/dialog/OnHomeSettingDialogClickListener;)V", "titles", "", "kotlin.jvm.PlatformType", "[Ljava/lang/String;", "view", "Landroid/view/View;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "getData", "type", "getFunAcIntent", "Landroid/content/Intent;", "fType", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "init", "initClickListener", "initTabLayout", "onAttachedToWindow", "onDetachedFromWindow", "selectPosition", RequestParameters.POSITION, "show", "FunctionType", "MyViewPagerAdapter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HomeSettingDialog extends Dialog {
    private final int AUTO_CLOSE;
    private final String TAG;
    private FunctionDialogAdapter functionDialogAdapter;
    private final ArrayList<FunctionItem> list;
    private final Handler mainHandle;
    private OnHomeSettingDialogClickListener onHomeSettingDialogClickListener;
    private final String[] titles;
    private View view;
    private ViewPager viewPager;

    /* compiled from: HomeSettingDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0015\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "", "(Ljava/lang/String;I)V", "RETURN_BACK", "DELIVERY_MODE", "RECYCLE_PLATE_MODE", "CRUISE_MODE", "SPECIAL_MODE", "DIRECT_MODE", "MUSIC_MODE", "BIRTHDAY_MODE", "SETTING", "GREETER_MODE", "DOOR_WELCOME", "USHER_MODE", "AREA_CRUISE_MODE", "DISTRICT_SETTING", "MANUAL_MODE", "RETURN_WASHROOM", "CRUISE_RETURN", "RETURN_SETTING", "ROW_NUMBER_MODE", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
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
        GREETER_MODE,
        DOOR_WELCOME,
        USHER_MODE,
        AREA_CRUISE_MODE,
        DISTRICT_SETTING,
        MANUAL_MODE,
        RETURN_WASHROOM,
        CRUISE_RETURN,
        RETURN_SETTING,
        ROW_NUMBER_MODE
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
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.titles = new String[]{getContext().getString(C5508R.string.welcome), context2.getResources().getString(C5508R.string.deliver_food), getContext().getString(C5508R.string.return_die), getContext().getString(C5508R.string.advertising)};
        this.AUTO_CLOSE = 11;
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$mainHandle$1
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
        this.list = new ArrayList<>();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeSettingDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "HomeSettingDialog";
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        this.titles = new String[]{getContext().getString(C5508R.string.welcome), context2.getResources().getString(C5508R.string.deliver_food), getContext().getString(C5508R.string.return_die), getContext().getString(C5508R.string.advertising)};
        this.AUTO_CLOSE = 11;
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$mainHandle$1
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
        this.list = new ArrayList<>();
        init(context);
    }

    private final void init(Context context) {
        HomeSettingDialogKt.mContext = context;
        build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getFunAcIntent(FunctionType fType) {
        switch (fType) {
            case RETURN_BACK:
                return new Intent(getContext(), (Class<?>) TurnBackActivity.class);
            case DELIVERY_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 0);
            case RECYCLE_PLATE_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 6);
            case DOOR_WELCOME:
                return new Intent(getContext(), (Class<?>) SolicitCustomerActivity.class);
            case CRUISE_MODE:
                return new Intent(getContext(), (Class<?>) CruiseSelectActivity.class);
            case USHER_MODE:
                return new Intent(getContext(), (Class<?>) GreeterMenuActivity.class).putExtra("MODE_TYPE", 5);
            case DIRECT_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 4);
            case MUSIC_MODE:
            default:
                return null;
            case BIRTHDAY_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 2);
            case SETTING:
                return new Intent(getContext(), (Class<?>) DeliverySettingActivity.class);
            case DISTRICT_SETTING:
                return new Intent(getContext(), (Class<?>) SolicitSettingActivity.class);
            case ROW_NUMBER_MODE:
                return new Intent(getContext(), (Class<?>) RowNumberActivity.class);
        }
    }

    public final ArrayList<FunctionItem> getList() {
        return this.list;
    }

    private final void getData(int type) {
        LinearLayoutManager linearLayoutManager;
        Context context;
        Context context2;
        this.list.clear();
        if (type == 0) {
            ArrayList<FunctionItem> arrayList = this.list;
            FunctionType functionType = FunctionType.USHER_MODE;
            int i = C5508R.drawable.select_home_dailog_wecome2;
            String string = getContext().getString(C5508R.string.usher_mode);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.usher_mode)");
            arrayList.add(new FunctionItem(functionType, i, string));
            ArrayList<FunctionItem> arrayList2 = this.list;
            FunctionType functionType2 = FunctionType.DOOR_WELCOME;
            int i2 = C5508R.drawable.select_home_dailog_wecome1;
            String string2 = getContext().getString(C5508R.string.solicits);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.solicits)");
            arrayList2.add(new FunctionItem(functionType2, i2, string2));
            ArrayList<FunctionItem> arrayList3 = this.list;
            FunctionType functionType3 = FunctionType.ROW_NUMBER_MODE;
            int i3 = C5508R.drawable.select_home_row_number;
            String string3 = getContext().getString(C5508R.string.row_number);
            Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.row_number)");
            arrayList3.add(new FunctionItem(functionType3, i3, string3));
            ArrayList<FunctionItem> arrayList4 = this.list;
            FunctionType functionType4 = FunctionType.DISTRICT_SETTING;
            int i4 = C5508R.drawable.select_home_dailog_dining6;
            String string4 = getContext().getString(C5508R.string.district_setting);
            Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.district_setting)");
            arrayList4.add(new FunctionItem(functionType4, i4, string4));
        } else if (type == 1) {
            ArrayList<FunctionItem> arrayList5 = this.list;
            FunctionType functionType5 = FunctionType.DELIVERY_MODE;
            int i5 = C5508R.drawable.select_home_dailog_dining1;
            String string5 = getContext().getString(C5508R.string.deliver_food);
            Intrinsics.checkExpressionValueIsNotNull(string5, "context.getString(R.string.deliver_food)");
            arrayList5.add(new FunctionItem(functionType5, i5, string5));
            ArrayList<FunctionItem> arrayList6 = this.list;
            FunctionType functionType6 = FunctionType.CRUISE_MODE;
            int i6 = C5508R.drawable.select_home_dailog_dining2;
            context2 = HomeSettingDialogKt.mContext;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            String string6 = context2.getString(C5508R.string.pdStr16_154);
            Intrinsics.checkExpressionValueIsNotNull(string6, "mContext!!.getString(R.string.pdStr16_154)");
            arrayList6.add(new FunctionItem(functionType6, i6, string6));
            ArrayList<FunctionItem> arrayList7 = this.list;
            FunctionType functionType7 = FunctionType.BIRTHDAY_MODE;
            int i7 = C5508R.drawable.select_home_dailog_dining4;
            String string7 = getContext().getString(C5508R.string.birthday_mode);
            Intrinsics.checkExpressionValueIsNotNull(string7, "context.getString(R.string.birthday_mode)");
            arrayList7.add(new FunctionItem(functionType7, i7, string7));
            ArrayList<FunctionItem> arrayList8 = this.list;
            FunctionType functionType8 = FunctionType.DIRECT_MODE;
            int i8 = C5508R.drawable.select_home_dailog_dining5;
            String string8 = getContext().getString(C5508R.string.arrive_mode);
            Intrinsics.checkExpressionValueIsNotNull(string8, "context.getString(R.string.arrive_mode)");
            arrayList8.add(new FunctionItem(functionType8, i8, string8));
            ArrayList<FunctionItem> arrayList9 = this.list;
            FunctionType functionType9 = FunctionType.SETTING;
            int i9 = C5508R.drawable.select_home_dailog_dining6;
            String string9 = getContext().getString(C5508R.string.deliver_setting);
            Intrinsics.checkExpressionValueIsNotNull(string9, "context.getString(R.string.deliver_setting)");
            arrayList9.add(new FunctionItem(functionType9, i9, string9));
        } else if (type == 2) {
            ArrayList<FunctionItem> arrayList10 = this.list;
            FunctionType functionType10 = FunctionType.MANUAL_MODE;
            int i10 = C5508R.drawable.select_home_dailog_reback1;
            String string10 = getContext().getString(C5508R.string.manual_mode);
            Intrinsics.checkExpressionValueIsNotNull(string10, "context.getString(R.string.manual_mode)");
            arrayList10.add(new FunctionItem(functionType10, i10, string10));
            ArrayList<FunctionItem> arrayList11 = this.list;
            FunctionType functionType11 = FunctionType.RETURN_WASHROOM;
            int i11 = C5508R.drawable.select_home_dailog_reback2;
            String string11 = getContext().getString(C5508R.string.return_washroom);
            Intrinsics.checkExpressionValueIsNotNull(string11, "context.getString(R.string.return_washroom)");
            arrayList11.add(new FunctionItem(functionType11, i11, string11));
            ArrayList<FunctionItem> arrayList12 = this.list;
            FunctionType functionType12 = FunctionType.CRUISE_RETURN;
            int i12 = C5508R.drawable.select_home_dailog_reback3;
            String string12 = getContext().getString(C5508R.string.curise_return);
            Intrinsics.checkExpressionValueIsNotNull(string12, "context.getString(R.string.curise_return)");
            arrayList12.add(new FunctionItem(functionType12, i12, string12));
            ArrayList<FunctionItem> arrayList13 = this.list;
            FunctionType functionType13 = FunctionType.RETURN_SETTING;
            int i13 = C5508R.drawable.select_home_dailog_dining6;
            String string13 = getContext().getString(C5508R.string.return_setting);
            Intrinsics.checkExpressionValueIsNotNull(string13, "context.getString(R.string.return_setting)");
            arrayList13.add(new FunctionItem(functionType13, i13, string13));
        }
        MaxHeightRecyclerView fun_rv = (MaxHeightRecyclerView) findViewById(C5508R.id.fun_rv);
        Intrinsics.checkExpressionValueIsNotNull(fun_rv, "fun_rv");
        if (this.list.size() > 2) {
            linearLayoutManager = new GridLayoutManager(getContext(), 4, 1, false);
        } else {
            linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        }
        fun_rv.setLayoutManager(linearLayoutManager);
        if (this.functionDialogAdapter != null) {
            this.functionDialogAdapter = (FunctionDialogAdapter) null;
        }
        context = HomeSettingDialogKt.mContext;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.functionDialogAdapter = new FunctionDialogAdapter(context);
        MaxHeightRecyclerView fun_rv2 = (MaxHeightRecyclerView) findViewById(C5508R.id.fun_rv);
        Intrinsics.checkExpressionValueIsNotNull(fun_rv2, "fun_rv");
        fun_rv2.setAdapter(this.functionDialogAdapter);
        FunctionDialogAdapter functionDialogAdapter = this.functionDialogAdapter;
        if (functionDialogAdapter == null) {
            Intrinsics.throwNpe();
        }
        functionDialogAdapter.setNewData(this.list);
        FunctionDialogAdapter functionDialogAdapter2 = this.functionDialogAdapter;
        if (functionDialogAdapter2 == null) {
            Intrinsics.throwNpe();
        }
        functionDialogAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$getData$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                String str2;
                Intent funAcIntent;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                try {
                    Object obj = adapter.getData().get(position);
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.FunctionItem");
                    }
                    FunctionItem functionItem = (FunctionItem) obj;
                    if (functionItem.getType() == HomeSettingDialog.FunctionType.MUSIC_MODE) {
                        MusicPlayerHelper.getInstance().gotoMusicPlayer(HomeSettingDialog.this.getContext());
                    }
                    str2 = HomeSettingDialog.this.TAG;
                    Pdlog.m3273d(str2, "onSingleItemClick : item = " + functionItem + ' ');
                    OnHomeSettingDialogClickListener onHomeSettingDialogClickListener = HomeSettingDialog.this.getOnHomeSettingDialogClickListener();
                    if (onHomeSettingDialogClickListener != null) {
                        HomeSettingDialog.FunctionType type2 = functionItem.getType();
                        funAcIntent = HomeSettingDialog.this.getFunAcIntent(functionItem.getType());
                        onHomeSettingDialogClickListener.onFunClick(type2, funAcIntent);
                    }
                    HomeSettingDialog.this.dismiss();
                } catch (Exception e) {
                    str = HomeSettingDialog.this.TAG;
                    Pdlog.m3274e(str, "onSingleItemClick : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.layout_setting_entrance_dialog, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ng_entrance_dialog, null)");
        this.view = inflate;
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById = view.findViewById(C5508R.id.viewpager);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.viewpager)");
        this.viewPager = (ViewPager) findViewById;
        requestWindowFeature(1);
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$build$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                HomeSettingDialog.this.dismiss();
            }
        });
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        setContentView(view3);
        initTabLayout();
        setCanceledOnTouchOutside(true);
        initClickListener();
    }

    private final void initClickListener() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById = view.findViewById(C5508R.id.tvChargeCallBack);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.tvChargeCallBack)");
        ViewExtKt.onSingleClick((TextView) findViewById, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$initClickListener$1
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
                String str;
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = HomeSettingDialog.this.TAG;
                boolean z = true;
                Pdlog.m3273d(str, "charge click ");
                List<String> chargingPiles = RobotMapManager.INSTANCE.getChargingPiles();
                if (chargingPiles != null && !chargingPiles.isEmpty()) {
                    z = false;
                }
                if (z) {
                    context3 = HomeSettingDialogKt.mContext;
                    context4 = HomeSettingDialogKt.mContext;
                    if (context4 == null) {
                        Intrinsics.throwNpe();
                    }
                    ToastUtils.show(context3, context4.getString(C5508R.string.toast_tip_not_find_piles), new Object[0]);
                    return;
                }
                context = HomeSettingDialogKt.mContext;
                if (context instanceof MyBaseActivity) {
                    context2 = HomeSettingDialogKt.mContext;
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    ((MyBaseActivity) context2).jumpAndFinish(new Intent(HomeSettingDialog.this.getContext(), (Class<?>) OneKeyChargingActivity.class));
                }
                HomeSettingDialog.this.dismiss();
            }
        });
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById2 = view2.findViewById(C5508R.id.flSetting);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.flSetting)");
        ViewExtKt.onSingleClick((FrameLayout) findViewById2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$initClickListener$2
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
                String str;
                Context context;
                Context context2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = HomeSettingDialog.this.TAG;
                Pdlog.m3273d(str, "allSetting click ");
                context = HomeSettingDialogKt.mContext;
                if (context instanceof MyBaseActivity) {
                    context2 = HomeSettingDialogKt.mContext;
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    ((MyBaseActivity) context2).jumpAndFinish(new Intent(HomeSettingDialog.this.getContext(), (Class<?>) AppSettingActivity.class));
                }
                HomeSettingDialog.this.dismiss();
            }
        });
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById3 = view3.findViewById(C5508R.id.tvReturn);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.tvReturn)");
        ViewExtKt.onSingleClick((TextView) findViewById3, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$initClickListener$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view4) {
                invoke2(view4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                String str;
                Context context;
                Context context2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = HomeSettingDialog.this.TAG;
                Pdlog.m3273d(str, "goHome click ");
                context = HomeSettingDialogKt.mContext;
                if (context instanceof MyBaseActivity) {
                    context2 = HomeSettingDialogKt.mContext;
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    ((MyBaseActivity) context2).jumpAndFinish(new Intent(HomeSettingDialog.this.getContext(), (Class<?>) TurnBackActivity.class));
                }
                HomeSettingDialog.this.dismiss();
            }
        });
    }

    private final void initTabLayout() {
        ViewPager viewPager = this.viewPager;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager.setAdapter(new MyViewPagerAdapter());
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager2.setOffscreenPageLimit(3);
        SlidingScaleTabLayout slidingScaleTabLayout = (SlidingScaleTabLayout) findViewById(C5508R.id.tablayout);
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        slidingScaleTabLayout.setViewPager(viewPager3, this.titles);
        ((SlidingScaleTabLayout) findViewById(C5508R.id.tablayout)).setmTabsContainer(new SlidingScaleTabLayout.TabSelectionListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.HomeSettingDialog$initTabLayout$1
            @Override // com.pudutech.peanut.robot_ui.widget.tablayout.SlidingScaleTabLayout.TabSelectionListener
            public final void selection(int i) {
                HomeSettingDialog.this.selectPosition(i);
            }
        });
        ViewPager viewPager4 = this.viewPager;
        if (viewPager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager4.setCurrentItem(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectPosition(int position) {
        Pdlog.m3273d(this.TAG, "selectPosition " + position);
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
        getData(position);
    }

    /* compiled from: HomeSettingDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0012\u0010\f\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u001a\u0010\u000f\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u001c\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\nH\u0016¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog$MyViewPagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "()V", "destroyItem", "", "container", "Landroid/view/ViewGroup;", RequestParameters.POSITION, "", "object", "", "getCount", "getItemPosition", "getPageTitle", "", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "o", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class MyViewPagerAdapter extends PagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 4;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object o) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(o, "o");
            return view == o;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object object) {
            Intrinsics.checkParameterIsNotNull(object, "object");
            Object tag = ((View) object).getTag();
            if (tag != null) {
                return ((Integer) tag).intValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            return "标题位置" + position;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup container, int position) {
            Context context;
            Intrinsics.checkParameterIsNotNull(container, "container");
            context = HomeSettingDialogKt.mContext;
            TextView textView = new TextView(context);
            textView.setTag(Integer.valueOf(position));
            container.addView(textView);
            return textView;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup container, int position, Object object) {
            Intrinsics.checkParameterIsNotNull(container, "container");
            Intrinsics.checkParameterIsNotNull(object, "object");
            container.removeView((View) object);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow");
        this.mainHandle.sendEmptyMessageDelayed(this.AUTO_CLOSE, 35000L);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow");
        this.mainHandle.removeMessages(this.AUTO_CLOSE);
    }

    @Override // android.app.Dialog
    public void show() {
        getData(0);
        SlidingScaleTabLayout tablayout = (SlidingScaleTabLayout) findViewById(C5508R.id.tablayout);
        Intrinsics.checkExpressionValueIsNotNull(tablayout, "tablayout");
        tablayout.setCurrentTab(0);
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
}
