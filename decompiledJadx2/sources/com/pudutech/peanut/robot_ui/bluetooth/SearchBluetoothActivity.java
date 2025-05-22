package com.pudutech.peanut.robot_ui.bluetooth;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BtUtil;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintQueue;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;
import com.pudutech.peanut.robot_ui.bluetooth.util.ToastUtil;

/* loaded from: classes5.dex */
public class SearchBluetoothActivity extends BluetoothActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private BluetoothAdapter bluetoothAdapter;
    private ListView lv_searchblt;
    private SearchBleAdapter searchBleAdapter;
    private TextView tv_summary;
    private TextView tv_title;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C5508R.layout.activity_searchbooth);
        this.lv_searchblt = (ListView) findViewById(C5508R.id.lv_searchblt);
        this.tv_title = (TextView) findViewById(C5508R.id.tv_title);
        this.tv_summary = (TextView) findViewById(C5508R.id.tv_summary);
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.searchBleAdapter = new SearchBleAdapter(this, null);
        this.lv_searchblt.setAdapter((ListAdapter) this.searchBleAdapter);
        init();
        searchDeviceOrOpenBluetooth();
        this.lv_searchblt.setOnItemClickListener(this);
        this.tv_title.setOnClickListener(this);
        this.tv_summary.setOnClickListener(this);
    }

    private void init() {
        if (!BtUtil.isOpen(this.bluetoothAdapter)) {
            this.tv_title.setText(C5508R.string.bluetooth_unconnect_printer);
            this.tv_summary.setText(C5508R.string.bluetooth_close_enable);
            return;
        }
        if (!PrintUtil.isBondPrinter(this, this.bluetoothAdapter)) {
            this.tv_title.setText(C5508R.string.bluetooth_unconnect_printer);
            this.tv_summary.setText(C5508R.string.bluetooth_search_printer);
            return;
        }
        this.tv_title.setText(getPrinterName() + getString(C5508R.string.bluetooth_connected));
        String defaultBluethoothDeviceAddress = PrintUtil.getDefaultBluethoothDeviceAddress(this);
        if (TextUtils.isEmpty(defaultBluethoothDeviceAddress)) {
            defaultBluethoothDeviceAddress = getString(C5508R.string.bluetooth_search_printer);
        }
        this.tv_summary.setText(defaultBluethoothDeviceAddress);
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btStatusChanged(Intent intent) {
        if (this.bluetoothAdapter.getState() == 10) {
            this.bluetoothAdapter.enable();
        }
        if (this.bluetoothAdapter.getState() == 12) {
            searchDeviceOrOpenBluetooth();
        }
    }

    private String getPrinterName() {
        String defaultBluetoothDeviceName = PrintUtil.getDefaultBluetoothDeviceName(this);
        return TextUtils.isEmpty(defaultBluetoothDeviceName) ? getString(C5508R.string.unkonwn_device) : defaultBluetoothDeviceName;
    }

    private String getPrinterName(String str) {
        return TextUtils.isEmpty(str) ? getString(C5508R.string.unkonwn_device) : str;
    }

    private void searchDeviceOrOpenBluetooth() {
        if (BtUtil.isOpen(this.bluetoothAdapter)) {
            BtUtil.searchDevices(this.bluetoothAdapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        BtUtil.cancelDiscovery(this.bluetoothAdapter);
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btStartDiscovery(Intent intent) {
        this.tv_title.setText(C5508R.string.bluetooth_searching_device);
        this.tv_summary.setText("");
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btFinishDiscovery(Intent intent) {
        this.tv_title.setText(C5508R.string.search_finish);
        this.tv_summary.setText(C5508R.string.search_replay);
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btFoundDevice(Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        Log.d("1", "!");
        if (this.bluetoothAdapter == null || bluetoothDevice == null) {
            return;
        }
        this.searchBleAdapter.addDevices(bluetoothDevice);
        Log.d(getString(C5508R.string.unkonwn_device), bluetoothDevice.getName() == null ? getString(C5508R.string.unkonwn_device) : bluetoothDevice.getName());
        Log.d("1", "!");
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btBondStatusChange(Intent intent) {
        super.btBondStatusChange(intent);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        switch (bluetoothDevice.getBondState()) {
            case 10:
                Log.d("BlueToothTestActivity", "取消配对");
                return;
            case 11:
                Log.d("BlueToothTestActivity", "正在配对......");
                return;
            case 12:
                Log.d("BlueToothTestActivity", "完成配对");
                connectBlt(bluetoothDevice);
                return;
            default:
                return;
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        final BluetoothDevice item;
        SearchBleAdapter searchBleAdapter = this.searchBleAdapter;
        if (searchBleAdapter == null || (item = searchBleAdapter.getItem(i)) == null) {
            return;
        }
        new AlertDialog.Builder(this).setTitle(getString(C5508R.string.bind) + getPrinterName(item.getName()) + "?").setMessage(getString(C5508R.string.click_bind_device)).setNegativeButton(getString(C5508R.string.pdStr10_67), new DialogInterface.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.bluetooth.SearchBluetoothActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(getString(C5508R.string.pdStr10_66), new DialogInterface.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.bluetooth.SearchBluetoothActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                try {
                    BtUtil.cancelDiscovery(SearchBluetoothActivity.this.bluetoothAdapter);
                    if (item.getBondState() == 12) {
                        SearchBluetoothActivity.this.connectBlt(item);
                    } else {
                        BluetoothDevice.class.getMethod("createBond", new Class[0]).invoke(item, new Object[0]);
                    }
                    PrintQueue.getQueue(SearchBluetoothActivity.this.getApplicationContext()).disconnect();
                    item.getName();
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.setDefaultBluetoothDeviceAddress(SearchBluetoothActivity.this.getApplicationContext(), "");
                    PrintUtil.setDefaultBluetoothDeviceName(SearchBluetoothActivity.this.getApplicationContext(), "");
                    SearchBluetoothActivity searchBluetoothActivity = SearchBluetoothActivity.this;
                    ToastUtil.showToast(searchBluetoothActivity, searchBluetoothActivity.getString(C5508R.string.bluetooth_fail_retry));
                }
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectBlt(BluetoothDevice bluetoothDevice) {
        SearchBleAdapter searchBleAdapter = this.searchBleAdapter;
        if (searchBleAdapter != null) {
            searchBleAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        init();
        this.searchBleAdapter.notifyDataSetChanged();
        PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), bluetoothDevice.getAddress());
        PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), bluetoothDevice.getName());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != C5508R.id.tv_title && id == C5508R.id.tv_summary) {
            searchDeviceOrOpenBluetooth();
        }
    }
}
