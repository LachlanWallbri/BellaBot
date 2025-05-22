package com.pudutech.peanut.robot_ui.bluetooth.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintMsgEvent;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintQueue;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/* loaded from: classes5.dex */
public class BtService {
    private static final UUID MY_UUID = UUID.fromString("0001101-0000-1000-8000-00805F9B34FB");
    private static final String NAME = "BtService";
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_LISTEN = 1;
    public static final int STATE_NONE = 0;
    private static final String TAG = "BtService";
    private AcceptThread mAcceptThread;
    private ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    private Context mContext;
    private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
    private int mState = 0;

    public BtService(Context context) {
        this.mContext = context;
    }

    public synchronized int getState() {
        return this.mState;
    }

    private synchronized void setState(int i) {
        Log.d("BtService", "setState() " + this.mState + " -> " + i);
        this.mState = i;
        int i2 = this.mState;
        if (i2 == 1) {
            EventBus.getDefault().post(new PrintMsgEvent(1, RobotContext.context.getString(C5508R.string.bluetooth_waiting_connect)));
        } else if (i2 == 2) {
            EventBus.getDefault().post(new PrintMsgEvent(1, RobotContext.context.getString(C5508R.string.bluetooth_connecting)));
        } else if (i2 == 3) {
            EventBus.getDefault().post(new PrintMsgEvent(1, RobotContext.context.getString(C5508R.string.bluetooth_connected)));
        } else {
            EventBus.getDefault().post(new PrintMsgEvent(1, RobotContext.context.getString(C5508R.string.bluetooth_unconnect)));
        }
    }

    public synchronized void connect(BluetoothDevice bluetoothDevice) {
        Log.d("BtService", "connect to: " + bluetoothDevice);
        EventBus.getDefault().post(new PrintMsgEvent(2, RobotContext.context.getString(C5508R.string.bluetooth_connecting_device)));
        if (this.mState == 2 && this.mConnectThread != null) {
            this.mConnectThread.cancel();
            this.mConnectThread = null;
        }
        if (this.mConnectedThread != null) {
            this.mConnectedThread.cancel();
            this.mConnectedThread = null;
        }
        this.mConnectThread = new ConnectThread(bluetoothDevice);
        this.mConnectThread.start();
        setState(2);
    }

    public synchronized void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice, String str) {
        Log.d("BtService", "connected, Socket Type:" + str);
        if (this.mConnectThread != null) {
            this.mConnectThread.cancel();
            this.mConnectThread = null;
        }
        if (this.mConnectedThread != null) {
            this.mConnectedThread.cancel();
            this.mConnectedThread = null;
        }
        if (this.mAcceptThread != null) {
            this.mAcceptThread.cancel();
            this.mAcceptThread = null;
        }
        this.mConnectedThread = new ConnectedThread(bluetoothSocket, str);
        this.mConnectedThread.start();
        EventBus.getDefault().post(new PrintMsgEvent(2, RobotContext.context.getString(C5508R.string.bluetooth_connect_success)));
        setState(3);
        PrintQueue.getQueue(this.mContext).print();
    }

    public synchronized void stop() {
        Log.d("BtService", "stop");
        if (this.mConnectThread != null) {
            this.mConnectThread.cancel();
            this.mConnectThread = null;
        }
        if (this.mConnectedThread != null) {
            this.mConnectedThread.cancel();
            this.mConnectedThread = null;
        }
        if (this.mAcceptThread != null) {
            this.mAcceptThread.cancel();
            this.mAcceptThread = null;
        }
        setState(0);
    }

    public void write(byte[] bArr) {
        synchronized (this) {
            if (this.mState != 3) {
                return;
            }
            this.mConnectedThread.write(bArr);
        }
    }

    public void write(byte[] bArr, long j) {
        synchronized (this) {
            if (this.mState != 3) {
                return;
            }
            this.mConnectedThread.write(bArr, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionFailed() {
        EventBus.getDefault().post(new PrintMsgEvent(2, RobotContext.context.getString(C5508R.string.bluetooth_connect_fail_retry)));
        setState(0);
        start();
    }

    public synchronized void start() {
        Log.d("BtService", "start");
        if (this.mConnectThread != null) {
            this.mConnectThread.cancel();
            this.mConnectThread = null;
        }
        if (this.mConnectedThread != null) {
            this.mConnectedThread.cancel();
            this.mConnectedThread = null;
        }
        setState(1);
        if (this.mAcceptThread == null) {
            this.mAcceptThread = new AcceptThread();
            this.mAcceptThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionLost() {
        EventBus.getDefault().post(new PrintMsgEvent(2, RobotContext.context.getString(C5508R.string.bluetooth_connect_fail_cut)));
        setState(0);
        start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class AcceptThread extends Thread {
        private String mSocketType;
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            BluetoothServerSocket bluetoothServerSocket;
            try {
                bluetoothServerSocket = BtService.this.mAdapter.listenUsingRfcommWithServiceRecord("BtService", BtService.MY_UUID);
            } catch (Exception e) {
                Log.e("BtService", "Socket Type: " + this.mSocketType + "listen() failed", e);
                bluetoothServerSocket = null;
            }
            this.mmServerSocket = bluetoothServerSocket;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(6:12|13|(3:15|(1:25)(1:(1:20))|21)|26|27|21) */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
        
            android.util.Log.e("BtService", "Could not close unwanted socket", r0);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            try {
                Log.d("BtService", "Socket Type: " + this.mSocketType + "BEGIN mAcceptThread" + this);
                StringBuilder sb = new StringBuilder();
                sb.append("AcceptThread");
                sb.append(this.mSocketType);
                setName(sb.toString());
                while (BtService.this.mState != 3) {
                    try {
                        BluetoothSocket accept = this.mmServerSocket.accept();
                        if (accept != null) {
                            synchronized (BtService.this) {
                                int i = BtService.this.mState;
                                if (i != 0) {
                                    if (i == 1 || i == 2) {
                                        BtService.this.connected(accept, accept.getRemoteDevice(), this.mSocketType);
                                    } else if (i != 3) {
                                    }
                                }
                                accept.close();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("BtService", "Socket Type: " + this.mSocketType + "accept() failed", e);
                    }
                }
                Log.i("BtService", "END mAcceptThread, socket Type: " + this.mSocketType);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void cancel() {
            Log.d("BtService", "Socket Type" + this.mSocketType + "cancel " + this);
            try {
                this.mmServerSocket.close();
            } catch (Exception e) {
                Log.e("BtService", "Socket Type" + this.mSocketType + "close() of server failed", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ConnectThread extends Thread {
        private String mSocketType;
        private final BluetoothDevice mmDevice;
        private final BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice bluetoothDevice) {
            BluetoothSocket bluetoothSocket;
            this.mmDevice = bluetoothDevice;
            try {
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(BtService.MY_UUID);
            } catch (Exception e) {
                Log.e("BtService", "Socket Type: " + this.mSocketType + "create() failed", e);
                bluetoothSocket = null;
            }
            this.mmSocket = bluetoothSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Log.i("BtService", "BEGIN mConnectThread SocketType:" + this.mSocketType);
                setName("ConnectThread" + this.mSocketType);
                BtService.this.mAdapter.cancelDiscovery();
                try {
                    try {
                        this.mmSocket.connect();
                        synchronized (BtService.this) {
                            BtService.this.mConnectThread = null;
                        }
                        BtService.this.connected(this.mmSocket, this.mmDevice, this.mSocketType);
                    } catch (IOException e) {
                        Log.e("BtService", "unable to close() " + this.mSocketType + " socket during connection failure", e);
                        BtService.this.connectionFailed();
                    }
                } catch (Exception unused) {
                    this.mmSocket.close();
                    BtService.this.connectionFailed();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (Exception e) {
                Log.e("BtService", "close() of connect " + this.mSocketType + " socket failed", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private final BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket bluetoothSocket, String str) {
            InputStream inputStream;
            Log.d("BtService", "create ConnectedThread: " + str);
            this.mmSocket = bluetoothSocket;
            OutputStream outputStream = null;
            try {
                inputStream = bluetoothSocket.getInputStream();
                try {
                    outputStream = bluetoothSocket.getOutputStream();
                } catch (Exception e) {
                    e = e;
                    Log.e("BtService", "temp sockets not created", e);
                    this.mmInStream = inputStream;
                    this.mmOutStream = outputStream;
                }
            } catch (Exception e2) {
                e = e2;
                inputStream = null;
            }
            this.mmInStream = inputStream;
            this.mmOutStream = outputStream;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.i("BtService", "BEGIN mConnectedThread");
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    EventBus.getDefault().post(new BtMsgReadEvent(this.mmInStream.read(bArr), bArr));
                } catch (IOException e) {
                    Log.e("BtService", "disconnected", e);
                    BtService.this.connectionLost();
                    BtService.this.start();
                    return;
                } catch (Exception unused) {
                    BtService.this.connectionLost();
                    BtService.this.start();
                    return;
                }
            }
        }

        public void write(byte[] bArr) {
            try {
                this.mmOutStream.write(bArr);
            } catch (Exception e) {
                Log.e("BtService", "Exception during write", e);
            }
        }

        public void write(byte[] bArr, long j) {
            try {
                Thread.sleep(j);
                this.mmOutStream.write(bArr);
            } catch (Exception e) {
                Log.e("BtService", "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (Exception e) {
                Log.e("BtService", "close() of connect socket failed", e);
            }
        }
    }
}
