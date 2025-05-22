package com.pudutech.peanut.robot_ui.bluetooth.print;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.text.TextUtils;
import com.pudutech.peanut.robot_ui.bluetooth.base.AppInfo;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BtService;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class PrintQueue {
    private static Context mContext;
    private static PrintQueue mInstance;
    private BluetoothAdapter mAdapter;
    private BtService mBtService;
    private ArrayList<byte[]> mQueue;

    private PrintQueue() {
    }

    public static PrintQueue getQueue(Context context) {
        if (mInstance == null) {
            mInstance = new PrintQueue();
        }
        if (mContext == null) {
            mContext = context;
        }
        return mInstance;
    }

    public synchronized void add(byte[] bArr) {
        if (this.mQueue == null) {
            this.mQueue = new ArrayList<>();
        }
        if (bArr != null) {
            this.mQueue.add(bArr);
        }
        print();
    }

    public synchronized void add(ArrayList<byte[]> arrayList) {
        if (this.mQueue == null) {
            this.mQueue = new ArrayList<>();
        }
        if (arrayList != null) {
            this.mQueue.addAll(arrayList);
        }
        print();
    }

    public synchronized void print() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().post(new PrintMsgEvent(3, "2"));
        }
        if (this.mQueue != null && this.mQueue.size() > 0) {
            if (this.mAdapter == null) {
                this.mAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.mBtService == null) {
                this.mBtService = new BtService(mContext);
            }
            if (this.mBtService.getState() != 3 && !TextUtils.isEmpty(AppInfo.btAddress)) {
                this.mBtService.connect(this.mAdapter.getRemoteDevice(AppInfo.btAddress));
            } else {
                while (this.mQueue.size() > 0) {
                    this.mBtService.write(this.mQueue.get(0));
                    this.mQueue.remove(0);
                }
                EventBus.getDefault().post(new PrintMsgEvent(3, "1"));
            }
        }
    }

    public void disconnect() {
        try {
            if (this.mBtService != null) {
                this.mBtService.stop();
                this.mBtService = null;
            }
            if (this.mAdapter != null) {
                this.mAdapter = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tryConnect() {
        try {
            if (TextUtils.isEmpty(AppInfo.btAddress)) {
                return;
            }
            if (this.mAdapter == null) {
                this.mAdapter = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.mAdapter == null) {
                return;
            }
            if (this.mBtService == null) {
                this.mBtService = new BtService(mContext);
            }
            if (this.mBtService.getState() == 3 || TextUtils.isEmpty(AppInfo.btAddress)) {
                return;
            }
            this.mBtService.connect(this.mAdapter.getRemoteDevice(AppInfo.btAddress));
        } catch (Error e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void write(byte[] bArr) {
        if (bArr != null) {
            try {
                if (bArr.length <= 0) {
                    return;
                }
                if (this.mAdapter == null) {
                    this.mAdapter = BluetoothAdapter.getDefaultAdapter();
                }
                if (this.mBtService == null) {
                    this.mBtService = new BtService(mContext);
                }
                if (this.mBtService.getState() != 3 && !TextUtils.isEmpty(AppInfo.btAddress)) {
                    this.mBtService.connect(this.mAdapter.getRemoteDevice(AppInfo.btAddress));
                } else {
                    this.mBtService.write(bArr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
