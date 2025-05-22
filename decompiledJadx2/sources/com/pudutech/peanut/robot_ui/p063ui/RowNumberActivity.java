package com.pudutech.peanut.robot_ui.p063ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.response.TableBean;
import com.pudutech.disinfect.baselib.network.response.TableGroupBean;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bluetooth.BluetoothController;
import com.pudutech.peanut.robot_ui.bluetooth.BtService;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;
import com.pudutech.peanut.robot_ui.bluetooth.util.ToastUtil;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.AdVoicePlayManager;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.manager.MqttManager;
import com.pudutech.peanut.robot_ui.p063ui.adapter.GridSpacingItemDecoration;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TableRowAdapter;
import com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.BlueToothSearchDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RowNumberActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u001b\u0018\u0000 D2\u00020\u0001:\u0001DB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010'\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010(\u001a\u00020$H\u0007J\u0006\u0010)\u001a\u00020$J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0002J\u0012\u0010,\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u0012\u0010-\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J1\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000100H\u0016¢\u0006\u0002\u00106J\u0012\u00107\u001a\u00020$2\b\u00108\u001a\u0004\u0018\u000109H\u0014J\b\u0010:\u001a\u00020$H\u0014J\b\u0010;\u001a\u00020$H\u0014J\b\u0010<\u001a\u00020$H\u0014J\b\u0010=\u001a\u00020$H\u0002J\u0016\u0010>\u001a\u00020$2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@H\u0002J\b\u0010B\u001a\u00020$H\u0002J\b\u0010C\u001a\u00020$H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 ¨\u0006E"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BlueToothBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "handler", "Landroid/os/Handler;", "homeSettingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog;", "mAdapter", "Landroid/bluetooth/BluetoothAdapter;", "getMAdapter", "()Landroid/bluetooth/BluetoothAdapter;", "setMAdapter", "(Landroid/bluetooth/BluetoothAdapter;)V", "mCode", "mReturnDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/BlueToothSearchDialog;", "mTableAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TableRowAdapter;", "getMTableAdapter", "()Lcom/pudutech/peanut/robot_ui/ui/adapter/TableRowAdapter;", "setMTableAdapter", "(Lcom/pudutech/peanut/robot_ui/ui/adapter/TableRowAdapter;)V", "mTableName", "onHomeSettingDialogClickListener", "com/pudutech/peanut/robot_ui/ui/RowNumberActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity$onHomeSettingDialogClickListener$1;", "rowNumberVM", "Lcom/pudutech/peanut/robot_ui/viewmodel/RowNumberVM;", "getRowNumberVM", "()Lcom/pudutech/peanut/robot_ui/viewmodel/RowNumberVM;", "rowNumberVM$delegate", "Lkotlin/Lazy;", "btBondStatusChange", "", "intent", "Landroid/content/Intent;", "btStatusChanged", "getTableNumber", "init", "initVM", "initView", "jump", "jumpAndFinish", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onStart", "setBlueIcon", "setData", "mDatas", "", "Lcom/pudutech/disinfect/baselib/network/response/TableGroupBean;", "showBlueToothDialog", "showSettingDialog", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RowNumberActivity extends BlueToothBaseActivity {
    public static final int VOICE_NOTIFY = 4;
    public static final int VOICE_OPEN = 6;
    public static final int VOICE_REPEAT = 5;
    private static int repeat;
    private HashMap _$_findViewCache;
    private HomeSettingDialog homeSettingDialog;
    private BluetoothAdapter mAdapter;
    private String mCode;
    private BlueToothSearchDialog mReturnDialog;
    private TableRowAdapter mTableAdapter;
    private String mTableName;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String text = "";
    private final String TAG = getClass().getSimpleName();
    private Handler handler = new Companion.WithoutLeakHandler(this);

    /* renamed from: rowNumberVM$delegate, reason: from kotlin metadata */
    private final Lazy rowNumberVM = new ViewModelLazy(Reflection.getOrCreateKotlinClass(RowNumberVM.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private final RowNumberActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new OnHomeSettingDialogClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$onHomeSettingDialogClickListener$1
        @Override // com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener
        public void onFunClick(HomeSettingDialog.FunctionType type, Intent intent) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type == HomeSettingDialog.FunctionType.ROW_NUMBER_MODE || intent == null) {
                return;
            }
            RowNumberActivity.this.jump(intent);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final RowNumberVM getRowNumberVM() {
        return (RowNumberVM) this.rowNumberVM.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.pudutech.peanut.robot_ui.ui.RowNumberActivity$onHomeSettingDialogClickListener$1] */
    public RowNumberActivity() {
    }

    public final BluetoothAdapter getMAdapter() {
        return this.mAdapter;
    }

    public final void setMAdapter(BluetoothAdapter bluetoothAdapter) {
        this.mAdapter = bluetoothAdapter;
    }

    public final TableRowAdapter getMTableAdapter() {
        return this.mTableAdapter;
    }

    public final void setMTableAdapter(TableRowAdapter tableRowAdapter) {
        this.mTableAdapter = tableRowAdapter;
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C5508R.layout.activity_row_number);
        initView();
        initVM();
        this.mReturnDialog = new BlueToothSearchDialog(this);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RowNumberActivity$onCreate$1(this, null), 3, null);
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initVM() {
        if (Constans.INSTANCE.getShopInfo().getShop_id() == 0) {
            getTableNumber();
        } else {
            getRowNumberVM().getGroupTable(Constans.INSTANCE.getShopInfo().getShop_id());
        }
        RowNumberActivity rowNumberActivity = this;
        getRowNumberVM().getMGroupData().observe(rowNumberActivity, new Observer<List<? extends TableGroupBean>>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initVM$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends TableGroupBean> list) {
                onChanged2((List<TableGroupBean>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<TableGroupBean> it) {
                RowNumberActivity rowNumberActivity2 = RowNumberActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                rowNumberActivity2.setData(it);
            }
        });
        getRowNumberVM().getMTabeData().observe(rowNumberActivity, new Observer<TableBean>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initVM$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(TableBean tableBean) {
                String str;
                String queue_id = tableBean.getQueue_id();
                if (queue_id == null || queue_id.length() == 0) {
                    return;
                }
                String waiting_number = tableBean.getWaiting_number();
                if (waiting_number == null || waiting_number.length() == 0) {
                    return;
                }
                Intent intent = new Intent(RowNumberActivity.this.getApplicationContext(), (Class<?>) BtService.class);
                intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                StringBuilder sb = new StringBuilder();
                str = RowNumberActivity.this.mTableName;
                sb.append(str);
                sb.append(tableBean.getQueue_id());
                sb.append("号");
                intent.putExtra(PrintUtil.TABLE_NAME, sb.toString());
                intent.putExtra(PrintUtil.TABLE_NUMBER, tableBean.getWaiting_number());
                intent.putExtra(PrintUtil.SHOP_NAME, Constans.INSTANCE.getShopInfo().getName());
                intent.putExtra(PrintUtil.SHOP_URL, Constans.INSTANCE.getShopInfo().getUrl());
                RowNumberActivity.this.startService(intent);
                Intent intent2 = new Intent(RowNumberActivity.this, (Class<?>) PrintTableActivity.class);
                intent2.putExtra(Constants.POINT_TYPE_TABLE, tableBean.getQueue_id());
                intent2.putExtra("number", tableBean.getWaiting_number());
                RowNumberActivity.this.startActivity(intent2);
            }
        });
    }

    public final void getTableNumber() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RowNumberActivity$getTableNumber$1(this, null), 3, null);
    }

    private final void initView() {
        ImageView ivBack = (ImageView) _$_findCachedViewById(C5508R.id.ivBack);
        Intrinsics.checkExpressionValueIsNotNull(ivBack, "ivBack");
        ViewExtKt.onSingleClick(ivBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initView$1
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                RowNumberActivity.this.jumpAndFinish(null);
            }
        });
        RowNumberActivity rowNumberActivity = this;
        this.mTableAdapter = new TableRowAdapter(rowNumberActivity);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C5508R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) rowNumberActivity, 2, 1, false);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(5, 16, true));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(this.mTableAdapter);
        TableRowAdapter tableRowAdapter = this.mTableAdapter;
        if (tableRowAdapter != null) {
            tableRowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initView$3
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, int i) {
                    List<TableGroupBean> data;
                    TableGroupBean tableGroupBean;
                    List<TableGroupBean> data2;
                    TableRowAdapter mTableAdapter = RowNumberActivity.this.getMTableAdapter();
                    if (mTableAdapter != null && (data2 = mTableAdapter.getData()) != null) {
                        Iterator<T> it = data2.iterator();
                        while (it.hasNext()) {
                            ((TableGroupBean) it.next()).setSelect(false);
                        }
                    }
                    TableRowAdapter mTableAdapter2 = RowNumberActivity.this.getMTableAdapter();
                    if (mTableAdapter2 != null && (data = mTableAdapter2.getData()) != null && (tableGroupBean = data.get(i)) != null) {
                        RowNumberActivity.this.mCode = tableGroupBean.getCode();
                        RowNumberActivity.this.mTableName = tableGroupBean.getName();
                        tableGroupBean.setSelect(true);
                    }
                    TableRowAdapter mTableAdapter3 = RowNumberActivity.this.getMTableAdapter();
                    if (mTableAdapter3 != null) {
                        mTableAdapter3.notifyDataSetChanged();
                    }
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
        }
        ImageView setting_info = (ImageView) _$_findCachedViewById(C5508R.id.setting_info);
        Intrinsics.checkExpressionValueIsNotNull(setting_info, "setting_info");
        ViewExtKt.onSingleClick(setting_info, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initView$4
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                RowNumberActivity.this.showSettingDialog();
            }
        });
        FrameLayout flBooth = (FrameLayout) _$_findCachedViewById(C5508R.id.flBooth);
        Intrinsics.checkExpressionValueIsNotNull(flBooth, "flBooth");
        ViewExtKt.onSingleClick(flBooth, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initView$5
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                RowNumberActivity.this.showBlueToothDialog();
            }
        });
        TextView tvGet = (TextView) _$_findCachedViewById(C5508R.id.tvGet);
        Intrinsics.checkExpressionValueIsNotNull(tvGet, "tvGet");
        ViewExtKt.onSingleClick(tvGet, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$initView$6
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
                String str;
                RowNumberVM rowNumberVM;
                String str2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (PrintUtil.isBondPrinter(RowNumberActivity.this.getApplicationContext(), BluetoothAdapter.getDefaultAdapter())) {
                    str = RowNumberActivity.this.mCode;
                    String str3 = str;
                    if (!(str3 == null || str3.length() == 0)) {
                        rowNumberVM = RowNumberActivity.this.getRowNumberVM();
                        int shop_id = Constans.INSTANCE.getShopInfo().getShop_id();
                        str2 = RowNumberActivity.this.mCode;
                        if (str2 == null) {
                            Intrinsics.throwNpe();
                        }
                        rowNumberVM.getTableNumber(shop_id, str2);
                        return;
                    }
                    RowNumberActivity rowNumberActivity2 = RowNumberActivity.this;
                    ToastUtil.showToastCenter(rowNumberActivity2, rowNumberActivity2.getString(C5508R.string.pleas_number_eat));
                    return;
                }
                RowNumberActivity rowNumberActivity3 = RowNumberActivity.this;
                ToastUtil.showToastCenter(rowNumberActivity3, rowNumberActivity3.getString(C5508R.string.please_connect_print));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBlueIcon() {
        if (PrintUtil.isBondPrinter(getApplicationContext(), BluetoothAdapter.getDefaultAdapter())) {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(true);
        } else {
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(false);
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        setBlueIcon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
            HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
            if (homeSettingDialog2 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$showSettingDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    String str;
                    str = RowNumberActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog OnDismissListener");
                }
            });
            HomeSettingDialog homeSettingDialog3 = this.homeSettingDialog;
            if (homeSettingDialog3 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog3.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$showSettingDialog$2
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    String str;
                    str = RowNumberActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog setOnShowListener");
                }
            });
        }
        HomeSettingDialog homeSettingDialog4 = this.homeSettingDialog;
        if (homeSettingDialog4 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog4.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showBlueToothDialog() {
        if (this.mReturnDialog == null) {
            this.mReturnDialog = new BlueToothSearchDialog(this);
            BlueToothSearchDialog blueToothSearchDialog = this.mReturnDialog;
            if (blueToothSearchDialog != null) {
                MyStatusBarLayout layout_my_status_bar = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar);
                Intrinsics.checkExpressionValueIsNotNull(layout_my_status_bar, "layout_my_status_bar");
                blueToothSearchDialog.setStatusBarView(layout_my_status_bar);
            }
        }
        BlueToothSearchDialog blueToothSearchDialog2 = this.mReturnDialog;
        if (blueToothSearchDialog2 != null) {
            blueToothSearchDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.RowNumberActivity$showBlueToothDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    BlueToothSearchDialog blueToothSearchDialog3;
                    blueToothSearchDialog3 = RowNumberActivity.this.mReturnDialog;
                    if (blueToothSearchDialog3 != null) {
                        blueToothSearchDialog3.startSearch();
                    }
                    RowNumberActivity.this.setBlueIcon();
                }
            });
        }
        BlueToothSearchDialog blueToothSearchDialog3 = this.mReturnDialog;
        if (blueToothSearchDialog3 != null) {
            blueToothSearchDialog3.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        Pdlog.m3273d(this.TAG, "jump");
        if (intent != null) {
            startActivity(intent);
        }
        overridePendingTransition(0, 0);
        finish();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        jump(intent);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        BluetoothController.INSTANCE.init(this);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
        BlueToothSearchDialog blueToothSearchDialog = this.mReturnDialog;
        if (blueToothSearchDialog != null) {
            blueToothSearchDialog.unregister();
        }
        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        adVoicePlayManager.normalVolumeMode(simpleName);
        MqttManager.INSTANCE.closeMqtt();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btStatusChanged(Intent intent) {
        super.btStatusChanged(intent);
        init();
    }

    public final void init() {
        if (this.mAdapter == null) {
            this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (this.mAdapter == null) {
            ToastUtil.showToast(this, getString(C5508R.string.device_no_bluetooth));
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(false);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("activity.mAdapter.getState()");
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        sb.append(bluetoothAdapter != null ? Integer.valueOf(bluetoothAdapter.getState()) : null);
        Log.d("activity.mAdapter.getState()", sb.toString());
        BluetoothAdapter bluetoothAdapter2 = this.mAdapter;
        if (bluetoothAdapter2 != null && !bluetoothAdapter2.isEnabled()) {
            BluetoothAdapter bluetoothAdapter3 = this.mAdapter;
            if (bluetoothAdapter3 != null && bluetoothAdapter3.getState() == 10) {
                BluetoothAdapter bluetoothAdapter4 = this.mAdapter;
                if (bluetoothAdapter4 != null) {
                    bluetoothAdapter4.enable();
                }
            } else {
                ToastUtil.showToast(this, getString(C5508R.string.bluetooth_no_open));
                ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(false);
                return;
            }
        }
        RowNumberActivity rowNumberActivity = this;
        if (TextUtils.isEmpty(PrintUtil.getDefaultBluethoothDeviceAddress(rowNumberActivity))) {
            ToastUtil.showToast(rowNumberActivity, getString(C5508R.string.bluetooth_no_binding));
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setData(List<TableGroupBean> mDatas) {
        TableRowAdapter tableRowAdapter = this.mTableAdapter;
        if (tableRowAdapter != null) {
            tableRowAdapter.setNewData(mDatas);
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BlueToothBaseActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btBondStatusChange(Intent intent) {
        super.btBondStatusChange(intent);
        BluetoothDevice bluetoothDevice = intent != null ? (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") : null;
        Integer valueOf = bluetoothDevice != null ? Integer.valueOf(bluetoothDevice.getBondState()) : null;
        if (valueOf != null && valueOf.intValue() == 11) {
            Log.d("BlueToothTestActivity", "正在配对......");
            return;
        }
        if (valueOf != null && valueOf.intValue() == 12) {
            Log.d("BlueToothTestActivity", "完成配对");
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(true);
        } else if (valueOf != null && valueOf.intValue() == 10) {
            Log.d("BlueToothTestActivity", "断开连接");
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBlueToothConnect(false);
        } else if (valueOf != null && valueOf.intValue() == 10) {
            Log.d("BlueToothTestActivity", "取消配对");
        }
    }

    /* compiled from: RowNumberActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity$Companion;", "", "()V", "VOICE_NOTIFY", "", "VOICE_OPEN", "VOICE_REPEAT", "repeat", "getRepeat", "()I", "setRepeat", "(I)V", "text", "", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "WithoutLeakHandler", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getText() {
            return RowNumberActivity.text;
        }

        public final void setText(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            RowNumberActivity.text = str;
        }

        /* compiled from: RowNumberActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity$Companion$WithoutLeakHandler;", "Landroid/os/Handler;", "activity", "Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity;", "(Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity;)V", "mActivity", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        private static final class WithoutLeakHandler extends Handler {
            private WeakReference<RowNumberActivity> mActivity;

            public WithoutLeakHandler(RowNumberActivity activity) {
                Intrinsics.checkParameterIsNotNull(activity, "activity");
                this.mActivity = new WeakReference<>(activity);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                super.handleMessage(msg);
                RowNumberActivity rowNumberActivity = this.mActivity.get();
                if (rowNumberActivity != null) {
                    int i = msg.what;
                    if (i == 4) {
                        RowNumberActivity.INSTANCE.setRepeat(0);
                        AdVoicePlayManager adVoicePlayManager = AdVoicePlayManager.INSTANCE;
                        String simpleName = rowNumberActivity.getClass().getSimpleName();
                        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
                        adVoicePlayManager.lowVolumeMode(simpleName);
                        rowNumberActivity.handler.removeMessages(5);
                        String text = RowNumberActivity.INSTANCE.getText();
                        if (text != null) {
                            AiVoiceManager.INSTANCE.playTtsVoice(text);
                        }
                        Companion companion = RowNumberActivity.INSTANCE;
                        companion.setRepeat(companion.getRepeat() + 1);
                        rowNumberActivity.handler.sendEmptyMessageDelayed(5, SolicitService.CAMERA_OPEN_TIME_OUT);
                        return;
                    }
                    if (i != 5) {
                        return;
                    }
                    if (RowNumberActivity.INSTANCE.getRepeat() < 3) {
                        String text2 = RowNumberActivity.INSTANCE.getText();
                        if (text2 != null) {
                            AiVoiceManager.INSTANCE.playTtsVoice(text2);
                        }
                        Companion companion2 = RowNumberActivity.INSTANCE;
                        companion2.setRepeat(companion2.getRepeat() + 1);
                        rowNumberActivity.handler.sendEmptyMessageDelayed(5, SolicitService.CAMERA_OPEN_TIME_OUT);
                        return;
                    }
                    AdVoicePlayManager adVoicePlayManager2 = AdVoicePlayManager.INSTANCE;
                    String simpleName2 = rowNumberActivity.getClass().getSimpleName();
                    Intrinsics.checkExpressionValueIsNotNull(simpleName2, "javaClass.simpleName");
                    adVoicePlayManager2.normalVolumeMode(simpleName2);
                }
            }
        }

        public final int getRepeat() {
            return RowNumberActivity.repeat;
        }

        public final void setRepeat(int i) {
            RowNumberActivity.repeat = i;
        }
    }
}
