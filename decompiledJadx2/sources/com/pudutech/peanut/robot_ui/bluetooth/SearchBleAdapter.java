package com.pudutech.peanut.robot_ui.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bluetooth.base.AppInfo;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class SearchBleAdapter extends BaseAdapter {
    public boolean isConnect;
    private String mConnectedDeviceAddress;
    private Context mContext;
    private ArrayList<BluetoothDevice> mDevices;
    private LayoutInflater mInflater;
    public int state = 0;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SearchBleAdapter(Context context, ArrayList<BluetoothDevice> arrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDevices = arrayList == null ? new ArrayList<>() : arrayList;
        this.mConnectedDeviceAddress = PrintUtil.getDefaultBluethoothDeviceAddress(context);
    }

    public ArrayList<BluetoothDevice> getDevices() {
        return this.mDevices;
    }

    public void clearDevices() {
        this.state = 0;
        ArrayList<BluetoothDevice> arrayList = this.mDevices;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void setDevices(ArrayList<BluetoothDevice> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        this.mDevices = arrayList;
        notifyDataSetChanged();
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        ArrayList<BluetoothDevice> arrayList = this.mDevices;
        if (arrayList != null) {
            this.mDevices = sortByBond(arrayList);
        }
        super.notifyDataSetChanged();
    }

    private ArrayList<BluetoothDevice> sortByBond(ArrayList<BluetoothDevice> arrayList) {
        if (arrayList == null) {
            return null;
        }
        if (arrayList.size() < 2) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            BluetoothDevice bluetoothDevice = arrayList.get(i);
            if (bluetoothDevice.getBondState() == 12) {
                arrayList2.add(bluetoothDevice);
            } else {
                arrayList3.add(bluetoothDevice);
            }
        }
        arrayList.clear();
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList2.clear();
        arrayList3.clear();
        return arrayList;
    }

    public void setConnectedDeviceAddress(String str) {
        this.mConnectedDeviceAddress = str;
    }

    public String getDeviceAddress() {
        return this.mConnectedDeviceAddress;
    }

    public void addDevices(ArrayList<BluetoothDevice> arrayList) {
        if (arrayList == null) {
            return;
        }
        Iterator<BluetoothDevice> it = arrayList.iterator();
        while (it.hasNext()) {
            addDevices(it.next());
        }
    }

    public void addDevices(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.mDevices.contains(bluetoothDevice)) {
            return;
        }
        this.mDevices.add(bluetoothDevice);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mDevices.size();
    }

    @Override // android.widget.Adapter
    public BluetoothDevice getItem(int i) {
        return this.mDevices.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = this.mInflater.inflate(C5508R.layout.adapter_bt_device, viewGroup, false);
            viewHolder = new ViewHolder();
            if (view != null) {
                view.setTag(viewHolder);
            }
        }
        viewHolder.name = (TextView) view.findViewById(C5508R.id.txt_adapter_bt_name);
        viewHolder.address = (TextView) view.findViewById(C5508R.id.txt_adapter_bt_address);
        viewHolder.bond = (TextView) view.findViewById(C5508R.id.btn_adapter_bt_has_bond);
        BluetoothDevice bluetoothDevice = this.mDevices.get(i);
        String name = bluetoothDevice.getName() == null ? "未知设备" : bluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = "未知设备";
        }
        viewHolder.name.setText(name);
        String address = bluetoothDevice.getAddress() == null ? "未知地址" : bluetoothDevice.getAddress();
        if (TextUtils.isEmpty(address)) {
            address = "未知地址";
        }
        viewHolder.address.setText(address);
        int i2 = 8;
        int i3 = 16;
        if (AppInfo.density != 0.0f) {
            i2 = (int) (8 * AppInfo.density);
            i3 = (int) (16 * AppInfo.density);
        }
        if (bluetoothDevice.getBondState() == 12) {
            if (address.equals(this.mConnectedDeviceAddress)) {
                this.isConnect = true;
                this.state = 1;
                PrintUtil.setDefaultBluetoothDeviceAddress(this.mContext, bluetoothDevice.getAddress());
                viewHolder.bond.setText("已连接");
                viewHolder.bond.setTextColor(this.mContext.getResources().getColor(C5508R.color.theme_main_color));
            } else {
                viewHolder.bond.setTextColor(this.mContext.getResources().getColor(C5508R.color.theme_main_color));
                viewHolder.bond.setText("已配对");
            }
        } else {
            viewHolder.bond.setTextColor(this.mContext.getResources().getColor(C5508R.color.font_color_1));
            viewHolder.bond.setText("未配对");
        }
        viewHolder.bond.setPadding(i3, i2, i3, i2);
        return view;
    }

    /* loaded from: classes5.dex */
    static class ViewHolder {
        TextView address;
        TextView bond;
        TextView name;

        ViewHolder() {
        }
    }
}
