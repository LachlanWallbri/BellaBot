package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bluetooth.SearchBleAdapter;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BtUtil;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintQueue;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;
import com.pudutech.peanut.robot_ui.bluetooth.util.ToastUtil;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlueToothSearchDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0012\u0010'\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\b\u0010(\u001a\u00020#H\u0002J\u0010\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020-J\n\u0010.\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010.\u001a\u0004\u0018\u00010\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u00100\u001a\u00020#H\u0002J\u0010\u00100\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u00101\u001a\u00020#2\u0006\u00102\u001a\u000203H\u0002J\u0012\u00104\u001a\u00020#2\b\u00105\u001a\u0004\u0018\u000103H\u0016J0\u00106\u001a\u00020#2\f\u00107\u001a\b\u0012\u0002\b\u0003\u0018\u0001082\b\u00109\u001a\u0004\u0018\u0001032\u0006\u0010:\u001a\u00020\b2\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020#H\u0002J\u000e\u0010>\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010?\u001a\u00020#H\u0016J\u0006\u0010@\u001a\u00020#J\u0006\u0010A\u001a\u00020#R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006B"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/BlueToothSearchDialog;", "Landroid/app/Dialog;", "Landroid/widget/AdapterView$OnItemClickListener;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "flCancel", "Landroid/widget/FrameLayout;", "layout_my_status_bar", "Lcom/pudutech/peanut/robot_ui/ui/view/MyStatusBarLayout;", "lv_searchblt", "Landroid/widget/ListView;", "mBtReceiver", "Landroid/content/BroadcastReceiver;", "mCountDownTimer", "Landroid/os/CountDownTimer;", "mHandler", "Landroid/os/Handler;", "searchBleAdapter", "Lcom/pudutech/peanut/robot_ui/bluetooth/SearchBleAdapter;", "state", "tv_summary", "Landroid/widget/TextView;", "tv_title", "btBondStatusChange", "", "intent", "Landroid/content/Intent;", "btFoundDevice", "btStatusChanged", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "connectBlt", "bluetoothDevice", "Landroid/bluetooth/BluetoothDevice;", "getConnect", "", "getPrinterName", "dName", "init", "initView", "mView", "Landroid/view/View;", "onClick", "view", "onItemClick", "p0", "Landroid/widget/AdapterView;", "p1", RequestParameters.POSITION, "p3", "", "searchDeviceOrOpenBluetooth", "setStatusBarView", "show", "startSearch", "unregister", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class BlueToothSearchDialog extends Dialog implements AdapterView.OnItemClickListener, View.OnClickListener {
    private final String TAG;
    private Context _context;
    private BluetoothAdapter bluetoothAdapter;
    private FrameLayout flCancel;
    private MyStatusBarLayout layout_my_status_bar;
    private ListView lv_searchblt;
    private BroadcastReceiver mBtReceiver;
    private CountDownTimer mCountDownTimer;
    private final Handler mHandler;
    private SearchBleAdapter searchBleAdapter;
    private int state;
    private TextView tv_summary;
    private TextView tv_title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlueToothSearchDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.mBtReceiver = new BroadcastReceiver() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$mBtReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Intrinsics.checkParameterIsNotNull(context2, "context");
                Intrinsics.checkParameterIsNotNull(intent, "intent");
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_FINISHED", action)) {
                    BlueToothSearchDialog.this.searchDeviceOrOpenBluetooth();
                    return;
                }
                if (Intrinsics.areEqual("android.bluetooth.adapter.action.STATE_CHANGED", action)) {
                    BlueToothSearchDialog.this.btStatusChanged(intent);
                } else if (Intrinsics.areEqual("android.bluetooth.device.action.FOUND", action)) {
                    BlueToothSearchDialog.this.btFoundDevice(intent);
                } else if (Intrinsics.areEqual("android.bluetooth.device.action.BOND_STATE_CHANGED", action)) {
                    BlueToothSearchDialog.this.btBondStatusChange(intent);
                }
            }
        };
        this.mHandler = new Handler() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$mHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                BlueToothSearchDialog.this.searchDeviceOrOpenBluetooth();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlueToothSearchDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.mBtReceiver = new BroadcastReceiver() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$mBtReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Intrinsics.checkParameterIsNotNull(context2, "context");
                Intrinsics.checkParameterIsNotNull(intent, "intent");
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_FINISHED", action)) {
                    BlueToothSearchDialog.this.searchDeviceOrOpenBluetooth();
                    return;
                }
                if (Intrinsics.areEqual("android.bluetooth.adapter.action.STATE_CHANGED", action)) {
                    BlueToothSearchDialog.this.btStatusChanged(intent);
                } else if (Intrinsics.areEqual("android.bluetooth.device.action.FOUND", action)) {
                    BlueToothSearchDialog.this.btFoundDevice(intent);
                } else if (Intrinsics.areEqual("android.bluetooth.device.action.BOND_STATE_CHANGED", action)) {
                    BlueToothSearchDialog.this.btBondStatusChange(intent);
                }
            }
        };
        this.mHandler = new Handler() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$mHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                BlueToothSearchDialog.this.searchDeviceOrOpenBluetooth();
            }
        };
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View viewLayout = getLayoutInflater().inflate(C5508R.layout.blue_tooth_search, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(C5508R.style.popwin_anim);
            setContentView(viewLayout);
        }
        setCancelable(true);
        Intrinsics.checkExpressionValueIsNotNull(viewLayout, "viewLayout");
        initView(viewLayout);
        BtUtil.registerBluetoothReceiver(this.mBtReceiver, getContext());
    }

    private final void initView(View mView) {
        View findViewById = mView.findViewById(C5508R.id.lv_searchblt);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ListView");
        }
        this.lv_searchblt = (ListView) findViewById;
        View findViewById2 = mView.findViewById(C5508R.id.tv_title);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.tv_title = (TextView) findViewById2;
        View findViewById3 = mView.findViewById(C5508R.id.tv_summary);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.tv_summary = (TextView) findViewById3;
        View findViewById4 = mView.findViewById(C5508R.id.flCancel);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout");
        }
        this.flCancel = (FrameLayout) findViewById4;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.searchBleAdapter = new SearchBleAdapter(getContext(), null);
        ListView listView = this.lv_searchblt;
        if (listView != null) {
            listView.setAdapter((ListAdapter) this.searchBleAdapter);
        }
        init();
        searchDeviceOrOpenBluetooth();
        ListView listView2 = this.lv_searchblt;
        if (listView2 != null) {
            listView2.setOnItemClickListener(this);
        }
        TextView textView = this.tv_title;
        if (textView != null) {
            textView.setOnClickListener(this);
        }
        TextView textView2 = this.tv_summary;
        if (textView2 != null) {
            textView2.setOnClickListener(this);
        }
        FrameLayout frameLayout = this.flCancel;
        if (frameLayout != null) {
            ViewExtKt.onSingleClick(frameLayout, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$initView$1
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
                    BlueToothSearchDialog.this.dismiss();
                }
            });
        }
    }

    public final boolean getConnect() {
        SearchBleAdapter searchBleAdapter = this.searchBleAdapter;
        if (searchBleAdapter != null) {
            return searchBleAdapter.isConnect;
        }
        return false;
    }

    private final void init() {
        if (!BtUtil.isOpen(this.bluetoothAdapter)) {
            TextView textView = this.tv_summary;
            if (textView == null) {
                Intrinsics.throwNpe();
            }
            textView.setText(getContext().getString(C5508R.string.langyaxitongguanbi));
            return;
        }
        if (!PrintUtil.isBondPrinter(getContext(), this.bluetoothAdapter)) {
            TextView textView2 = this.tv_summary;
            if (textView2 == null) {
                Intrinsics.throwNpe();
            }
            textView2.setText(getContext().getString(C5508R.string.dianjihoulangya));
            return;
        }
        TextUtils.isEmpty(PrintUtil.getDefaultBluethoothDeviceAddress(getContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void btStatusChanged(Intent intent) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            Intrinsics.throwNpe();
        }
        if (bluetoothAdapter.getState() == 10) {
            BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
            if (bluetoothAdapter2 == null) {
                Intrinsics.throwNpe();
            }
            bluetoothAdapter2.enable();
        }
        BluetoothAdapter bluetoothAdapter3 = this.bluetoothAdapter;
        if (bluetoothAdapter3 == null) {
            Intrinsics.throwNpe();
        }
        if (bluetoothAdapter3.getState() == 12) {
            searchDeviceOrOpenBluetooth();
        }
    }

    private final String getPrinterName() {
        String defaultBluetoothDeviceName = PrintUtil.getDefaultBluetoothDeviceName(getContext());
        return TextUtils.isEmpty(defaultBluetoothDeviceName) ? "未知设备" : defaultBluetoothDeviceName;
    }

    private final String getPrinterName(String dName) {
        return TextUtils.isEmpty(dName) ? "未知设备" : dName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void searchDeviceOrOpenBluetooth() {
        if (BtUtil.isOpen(this.bluetoothAdapter)) {
            BtUtil.searchDevices(this.bluetoothAdapter);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mCountDownTimer = (CountDownTimer) null;
        }
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
            window4.setLayout(-1, -2);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> p0, View p1, int position, long p3) {
        SearchBleAdapter searchBleAdapter = this.searchBleAdapter;
        if (searchBleAdapter == null) {
            return;
        }
        if (searchBleAdapter == null) {
            Intrinsics.throwNpe();
        }
        final BluetoothDevice item = searchBleAdapter.getItem(position);
        if (item != null) {
            String address = item.getAddress() == null ? "未知地址" : item.getAddress();
            if (item.getBondState() == 12) {
                SearchBleAdapter searchBleAdapter2 = this.searchBleAdapter;
                if (Intrinsics.areEqual(address, searchBleAdapter2 != null ? searchBleAdapter2.getDeviceAddress() : null)) {
                    ToastUtil.showToast(getContext(), "已连接");
                    return;
                }
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            StringBuilder sb = new StringBuilder();
            sb.append("绑定");
            sb.append(getPrinterName(item != null ? item.getName() : null));
            sb.append("?");
            builder.setTitle(sb.toString()).setMessage("点击确认绑定蓝牙设备").setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$onItemClick$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setPositiveButton("确认", new DialogInterface.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$onItemClick$2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BluetoothAdapter bluetoothAdapter;
                    try {
                        bluetoothAdapter = BlueToothSearchDialog.this.bluetoothAdapter;
                        BtUtil.cancelDiscovery(bluetoothAdapter);
                        if (item.getBondState() == 12) {
                            BlueToothSearchDialog.this.connectBlt(item);
                        } else {
                            BluetoothDevice.class.getMethod("createBond", new Class[0]).invoke(item, new Object[0]);
                        }
                        PrintQueue.getQueue(BlueToothSearchDialog.this.getContext()).disconnect();
                        item.getName();
                    } catch (Exception e) {
                        e.printStackTrace();
                        PrintUtil.setDefaultBluetoothDeviceAddress(BlueToothSearchDialog.this.getContext(), "");
                        PrintUtil.setDefaultBluetoothDeviceName(BlueToothSearchDialog.this.getContext(), "");
                        ToastUtil.showToast(BlueToothSearchDialog.this.getContext(), "蓝牙绑定失败,请重试");
                    }
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connectBlt(BluetoothDevice bluetoothDevice) {
        SearchBleAdapter searchBleAdapter = this.searchBleAdapter;
        if (searchBleAdapter != null) {
            if (searchBleAdapter == null) {
                Intrinsics.throwNpe();
            }
            searchBleAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        init();
        SearchBleAdapter searchBleAdapter2 = this.searchBleAdapter;
        if (searchBleAdapter2 == null) {
            Intrinsics.throwNpe();
        }
        searchBleAdapter2.notifyDataSetChanged();
        PrintUtil.setDefaultBluetoothDeviceAddress(getContext(), bluetoothDevice.getAddress());
        PrintUtil.setDefaultBluetoothDeviceName(getContext(), bluetoothDevice.getName());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        int i = C5508R.id.tv_title;
        if (valueOf != null && valueOf.intValue() == i) {
            return;
        }
        int i2 = C5508R.id.tv_summary;
        if (valueOf != null && valueOf.intValue() == i2) {
            searchDeviceOrOpenBluetooth();
        }
    }

    public final void unregister() {
        BtUtil.unregisterBluetoothReceiver(this.mBtReceiver, getContext());
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.mCountDownTimer = (CountDownTimer) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void btFoundDevice(Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        Log.d("1", "!");
        if (this.bluetoothAdapter == null || bluetoothDevice == null) {
            return;
        }
        String name = bluetoothDevice.getName() == null ? "未知设备" : bluetoothDevice.getName();
        if (Intrinsics.areEqual(name, "未知设备")) {
            return;
        }
        SearchBleAdapter searchBleAdapter = this.searchBleAdapter;
        if (searchBleAdapter == null) {
            Intrinsics.throwNpe();
        }
        searchBleAdapter.addDevices(bluetoothDevice);
        Log.d("未知设备", name);
        Log.d("1", "!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void btBondStatusChange(Intent intent) {
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        Intrinsics.checkExpressionValueIsNotNull(device, "device");
        switch (device.getBondState()) {
            case 10:
                Log.d(this.TAG, "取消配对");
                return;
            case 11:
                Log.d(this.TAG, "正在配对......");
                return;
            case 12:
                Log.d(this.TAG, "完成配对");
                this.mHandler.sendEmptyMessageDelayed(1, 500L);
                return;
            default:
                return;
        }
    }

    public final void startSearch() {
        this.state = 0;
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mCountDownTimer = (CountDownTimer) null;
        }
        final long j = 10000000;
        final long j2 = 6000;
        this.mCountDownTimer = new CountDownTimer(j, j2) { // from class: com.pudutech.peanut.robot_ui.ui.dialog.BlueToothSearchDialog$startSearch$1
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                int i;
                int i2;
                SearchBleAdapter searchBleAdapter;
                SearchBleAdapter searchBleAdapter2;
                MyStatusBarLayout myStatusBarLayout;
                MyStatusBarLayout myStatusBarLayout2;
                i = BlueToothSearchDialog.this.state;
                if (i >= 2) {
                    searchBleAdapter2 = BlueToothSearchDialog.this.searchBleAdapter;
                    if (searchBleAdapter2 == null || searchBleAdapter2.state != 0) {
                        myStatusBarLayout = BlueToothSearchDialog.this.layout_my_status_bar;
                        if (myStatusBarLayout != null) {
                            myStatusBarLayout.setBlueToothConnect(true);
                        }
                    } else {
                        myStatusBarLayout2 = BlueToothSearchDialog.this.layout_my_status_bar;
                        if (myStatusBarLayout2 != null) {
                            myStatusBarLayout2.setBlueToothConnect(false);
                        }
                    }
                }
                BlueToothSearchDialog blueToothSearchDialog = BlueToothSearchDialog.this;
                i2 = blueToothSearchDialog.state;
                blueToothSearchDialog.state = i2 + 1;
                searchBleAdapter = BlueToothSearchDialog.this.searchBleAdapter;
                if (searchBleAdapter != null) {
                    searchBleAdapter.clearDevices();
                }
                BlueToothSearchDialog.this.searchDeviceOrOpenBluetooth();
            }
        };
        CountDownTimer countDownTimer2 = this.mCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.start();
        }
    }

    public final void setStatusBarView(MyStatusBarLayout layout_my_status_bar) {
        Intrinsics.checkParameterIsNotNull(layout_my_status_bar, "layout_my_status_bar");
        this.layout_my_status_bar = layout_my_status_bar;
    }
}
