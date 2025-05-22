package com.pudutech.peanut.robot_ui.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bluetooth.base.AppInfo;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintMsgEvent;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;
import com.pudutech.peanut.robot_ui.bluetooth.util.ToastUtil;
import de.greenrobot.event.EventBus;

/* loaded from: classes5.dex */
public class MainActivity extends BluetoothActivity implements View.OnClickListener {
    BluetoothAdapter mAdapter;
    TextView tv_blueadress;
    TextView tv_bluename;
    boolean mBtEnable = true;
    int PERMISSION_REQUEST_COARSE_LOCATION = 2;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C5508R.layout.activity_mains);
        this.tv_bluename = (TextView) findViewById(C5508R.id.tv_bluename);
        this.tv_blueadress = (TextView) findViewById(C5508R.id.tv_blueadress);
        findViewById(C5508R.id.button4).setOnClickListener(this);
        findViewById(C5508R.id.button5).setOnClickListener(this);
        findViewById(C5508R.id.button6).setOnClickListener(this);
        findViewById(C5508R.id.button).setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, this.PERMISSION_REQUEST_COARSE_LOCATION);
        }
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity, com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btStatusChanged(Intent intent) {
        super.btStatusChanged(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C5508R.id.button4) {
            startActivity(new Intent(this, (Class<?>) SearchBluetoothActivity.class));
            return;
        }
        if (id == C5508R.id.button5) {
            if (TextUtils.isEmpty(AppInfo.btAddress)) {
                ToastUtil.showToast(this, "请连接蓝牙...");
                startActivity(new Intent(this, (Class<?>) SearchBluetoothActivity.class));
                return;
            } else if (this.mAdapter.getState() == 10) {
                this.mAdapter.enable();
                ToastUtil.showToast(this, "蓝牙被关闭请打开...");
                return;
            } else {
                ToastUtil.showToast(this, "打印测试...");
                Intent intent = new Intent(getApplicationContext(), (Class<?>) BtService.class);
                intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                startService(intent);
                return;
            }
        }
        if (id == C5508R.id.button6) {
            if (TextUtils.isEmpty(AppInfo.btAddress)) {
                ToastUtil.showToast(this, "请连接蓝牙...");
                startActivity(new Intent(this, (Class<?>) SearchBluetoothActivity.class));
            } else {
                ToastUtil.showToast(this, "打印测试...");
                Intent intent2 = new Intent(getApplicationContext(), (Class<?>) BtService.class);
                intent2.setAction(PrintUtil.ACTION_PRINT_TEST_TWO);
                startService(intent2);
            }
            if (TextUtils.isEmpty(AppInfo.btAddress)) {
                ToastUtil.showToast(this, "请连接蓝牙...");
                startActivity(new Intent(this, (Class<?>) SearchBluetoothActivity.class));
                return;
            } else {
                ToastUtil.showToast(this, "打印图片...");
                Intent intent3 = new Intent(getApplicationContext(), (Class<?>) BtService.class);
                intent3.setAction(PrintUtil.ACTION_PRINT_BITMAP);
                startService(intent3);
                return;
            }
        }
        if (id == C5508R.id.button) {
            if (TextUtils.isEmpty(AppInfo.btAddress)) {
                ToastUtil.showToast(this, "请连接蓝牙...");
                startActivity(new Intent(this, (Class<?>) SearchBluetoothActivity.class));
            } else {
                ToastUtil.showToast(this, "打印图片...");
                Intent intent4 = new Intent(getApplicationContext(), (Class<?>) BtService.class);
                intent4.setAction(PrintUtil.ACTION_PRINT_BITMAP);
                startService(intent4);
            }
        }
    }

    public void onEventMainThread(PrintMsgEvent printMsgEvent) {
        if (printMsgEvent.type == 2) {
            ToastUtil.showToast(this, printMsgEvent.msg);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
