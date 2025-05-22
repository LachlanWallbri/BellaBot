package com.pudutech.peanut.robot_ui.p063ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.AppSettingActivity;
import com.pudutech.peanut.robot_ui.p063ui.CustomerModeActivity;
import com.pudutech.peanut.robot_ui.p063ui.DeliverTaskEditActivity;
import com.pudutech.peanut.robot_ui.p063ui.RowNumberActivity;
import com.pudutech.peanut.robot_ui.p063ui.adapter.HomeFuncAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.HomeFunctionItem;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.cruise.CruiseSelectActivity;
import com.pudutech.peanut.robot_ui.p063ui.greeter.GreeterMenuActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeFuncFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\f¨\u0006$"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/HomeFuncFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "mContext", "Landroid/content/Context;", "mHomeFuncAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/HomeFuncAdapter;", "mIndexFlag", "", "Ljava/lang/Integer;", "getFunAcIntent", "Landroid/content/Intent;", "fType", "Lcom/pudutech/peanut/robot_ui/ui/fragment/HomeFuncFragment$FunctionType;", "initDataByIndex", "", "Lcom/pudutech/peanut/robot_ui/ui/adapter/HomeFunctionItem;", "(Ljava/lang/Integer;)Ljava/util/List;", "initView", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "FunctionType", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HomeFuncFragment extends Fragment {
    public static final int FUNC_SPAN_COUNT = 4;
    public static final String INDEX_FLAG = "INDEX_FLAG";
    public static final String MODE_TYPE = "MODE_TYPE";
    private HashMap _$_findViewCache;
    private Context mContext;
    private HomeFuncAdapter mHomeFuncAdapter;
    private String TAG = getClass().getSimpleName();
    private Integer mIndexFlag = 0;

    /* compiled from: HomeFuncFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/fragment/HomeFuncFragment$FunctionType;", "", "(Ljava/lang/String;I)V", "DOOR_WELCOME", "USHER_MODE", "ROW_NUMBER_MODE", "CRUISE_MODE", "DELIVERY_MODE", "BIRTHDAY_MODE", "SETTING", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum FunctionType {
        DOOR_WELCOME,
        USHER_MODE,
        ROW_NUMBER_MODE,
        CRUISE_MODE,
        DELIVERY_MODE,
        BIRTHDAY_MODE,
        SETTING
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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        this.mContext = context;
        Bundle arguments = getArguments();
        this.mIndexFlag = arguments != null ? Integer.valueOf(arguments.getInt(INDEX_FLAG)) : null;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C5508R.layout.fragment_home_func, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…e_func, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private final void initView() {
        RecyclerView funcRv = (RecyclerView) _$_findCachedViewById(C5508R.id.funcRv);
        Intrinsics.checkExpressionValueIsNotNull(funcRv, "funcRv");
        funcRv.setLayoutManager(new GridLayoutManager(this.mContext, 4, 1, false));
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.mHomeFuncAdapter = new HomeFuncAdapter(context);
        RecyclerView funcRv2 = (RecyclerView) _$_findCachedViewById(C5508R.id.funcRv);
        Intrinsics.checkExpressionValueIsNotNull(funcRv2, "funcRv");
        funcRv2.setAdapter(this.mHomeFuncAdapter);
        HomeFuncAdapter homeFuncAdapter = this.mHomeFuncAdapter;
        if (homeFuncAdapter == null) {
            Intrinsics.throwNpe();
        }
        homeFuncAdapter.setNewData(initDataByIndex(this.mIndexFlag));
        HomeFuncAdapter homeFuncAdapter2 = this.mHomeFuncAdapter;
        if (homeFuncAdapter2 == null) {
            Intrinsics.throwNpe();
        }
        homeFuncAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.fragment.HomeFuncFragment$initView$1
            /* JADX WARN: Code restructure failed: missing block: B:5:0x0020, code lost:
            
                r2 = r1.this$0.getFunAcIntent(r2.getType());
             */
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyItemClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Context context2;
                Intent funAcIntent;
                Context context3;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Object obj = adapter.getData().get(position);
                if (obj != null) {
                    HomeFunctionItem homeFunctionItem = (HomeFunctionItem) obj;
                    context2 = HomeFuncFragment.this.mContext;
                    if (!(context2 instanceof MyBaseActivity) || funAcIntent == null) {
                        return;
                    }
                    context3 = HomeFuncFragment.this.mContext;
                    if (context3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.base.MyBaseActivity");
                    }
                    ((MyBaseActivity) context3).jumpAndFinish(funAcIntent);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.HomeFunctionItem");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getFunAcIntent(FunctionType fType) {
        switch (fType) {
            case DOOR_WELCOME:
                return new Intent(getContext(), (Class<?>) CustomerModeActivity.class);
            case USHER_MODE:
                return new Intent(getContext(), (Class<?>) GreeterMenuActivity.class).putExtra("MODE_TYPE", 5);
            case ROW_NUMBER_MODE:
                return new Intent(getContext(), (Class<?>) RowNumberActivity.class);
            case CRUISE_MODE:
                return new Intent(getContext(), (Class<?>) CruiseSelectActivity.class);
            case DELIVERY_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 0);
            case BIRTHDAY_MODE:
                return new Intent(getContext(), (Class<?>) DeliverTaskEditActivity.class).putExtra("MODE_TYPE", 2);
            case SETTING:
                return new Intent(getContext(), (Class<?>) AppSettingActivity.class);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final List<HomeFunctionItem> initDataByIndex(Integer mIndexFlag) {
        ArrayList arrayList = new ArrayList();
        if (mIndexFlag != null && mIndexFlag.intValue() == 0) {
            FunctionType functionType = FunctionType.DOOR_WELCOME;
            int i = C5508R.drawable.select_home_dailog_wecome1;
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwNpe();
            }
            String string = context.getString(C5508R.string.solicits);
            Intrinsics.checkExpressionValueIsNotNull(string, "mContext!!.getString(R.string.solicits)");
            arrayList.add(new HomeFunctionItem(functionType, i, string));
            FunctionType functionType2 = FunctionType.USHER_MODE;
            int i2 = C5508R.drawable.select_home_dailog_wecome2;
            Context context2 = this.mContext;
            if (context2 == null) {
                Intrinsics.throwNpe();
            }
            String string2 = context2.getString(C5508R.string.usher_mode);
            Intrinsics.checkExpressionValueIsNotNull(string2, "mContext!!.getString(R.string.usher_mode)");
            arrayList.add(new HomeFunctionItem(functionType2, i2, string2));
            FunctionType functionType3 = FunctionType.CRUISE_MODE;
            int i3 = C5508R.drawable.select_home_dailog_dining2;
            Context context3 = this.mContext;
            if (context3 == null) {
                Intrinsics.throwNpe();
            }
            String string3 = context3.getString(C5508R.string.pdStr16_154);
            Intrinsics.checkExpressionValueIsNotNull(string3, "mContext!!.getString(R.string.pdStr16_154)");
            arrayList.add(new HomeFunctionItem(functionType3, i3, string3));
            FunctionType functionType4 = FunctionType.DELIVERY_MODE;
            int i4 = C5508R.drawable.select_home_dailog_dining1;
            Context context4 = this.mContext;
            if (context4 == null) {
                Intrinsics.throwNpe();
            }
            String string4 = context4.getString(C5508R.string.deliver_food);
            Intrinsics.checkExpressionValueIsNotNull(string4, "mContext!!.getString(R.string.deliver_food)");
            arrayList.add(new HomeFunctionItem(functionType4, i4, string4));
            FunctionType functionType5 = FunctionType.BIRTHDAY_MODE;
            int i5 = C5508R.drawable.select_home_dailog_dining4;
            Context context5 = this.mContext;
            if (context5 == null) {
                Intrinsics.throwNpe();
            }
            String string5 = context5.getString(C5508R.string.birthday_mode);
            Intrinsics.checkExpressionValueIsNotNull(string5, "mContext!!.getString(R.string.birthday_mode)");
            arrayList.add(new HomeFunctionItem(functionType5, i5, string5));
            FunctionType functionType6 = FunctionType.SETTING;
            int i6 = C5508R.drawable.select_home_dailog_dining6;
            Context context6 = this.mContext;
            if (context6 == null) {
                Intrinsics.throwNpe();
            }
            String string6 = context6.getString(C5508R.string.home_setting);
            Intrinsics.checkExpressionValueIsNotNull(string6, "mContext!!.getString(R.string.home_setting)");
            arrayList.add(new HomeFunctionItem(functionType6, i6, string6));
        }
        return arrayList;
    }
}
