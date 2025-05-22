package com.pudutech.recyclebot.robot.nfc;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import com.acs.smartcard.Features;
import com.acs.smartcard.PinModify;
import com.acs.smartcard.PinProperties;
import com.acs.smartcard.ReadKeyOption;
import com.acs.smartcard.Reader;
import com.acs.smartcard.TlvProperties;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* loaded from: classes2.dex */
public class RFIDReader {
    private static final String ACTION_USB_PERMISSION = "com.android.nfc.USB_PERMISSION";
    private Context mContext;
    private UsbManager mManager;
    private PendingIntent mPermissionIntent;
    private Reader mReader;
    private int mSlot;
    private static final String[] powerActionStrings = {"Power Down", "Cold Reset", "Warm Reset"};
    private static final String[] stateStrings = {"Unknown", "Absent", "Present", "Swallowed", "Powered", "Negotiable", "Specific"};
    private static final String[] featureStrings = {"FEATURE_UNKNOWN", "FEATURE_VERIFY_PIN_START", "FEATURE_VERIFY_PIN_FINISH", "FEATURE_MODIFY_PIN_START", "FEATURE_MODIFY_PIN_FINISH", "FEATURE_GET_KEY_PRESSED", "FEATURE_VERIFY_PIN_DIRECT", "FEATURE_MODIFY_PIN_DIRECT", "FEATURE_MCT_READER_DIRECT", "FEATURE_MCT_UNIVERSAL", "FEATURE_IFD_PIN_PROPERTIES", "FEATURE_ABORT", "FEATURE_SET_SPE_MESSAGE", "FEATURE_VERIFY_PIN_DIRECT_APP_ID", "FEATURE_MODIFY_PIN_DIRECT_APP_ID", "FEATURE_WRITE_DISPLAY", "FEATURE_GET_KEY", "FEATURE_IFD_DISPLAY_PROPERTIES", "FEATURE_GET_TLV_PROPERTIES", "FEATURE_CCID_ESC_COMMAND"};
    private static final String[] propertyStrings = {"Unknown", "wLcdLayout", "bEntryValidationCondition", "bTimeOut2", "wLcdMaxCharacters", "wLcdMaxLines", "bMinPINSize", "bMaxPINSize", "sFirmwareID", "bPPDUSupport", "dwMaxAPDUDataSize", "wIdVendor", "wIdProduct"};
    private final String TAG = "NFC";
    private Features mFeatures = new Features();
    private PinModify mPinModify = new PinModify();
    private ReadKeyOption mReadKeyOption = new ReadKeyOption();
    private OnUIDListener mUIDListener = null;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.pudutech.recyclebot.robot.nfc.RFIDReader.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (RFIDReader.ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    RFIDReader.this.logMsg("ACTION_USB_PERMISSION...");
                    if (RFIDReader.this.findNFCDevice()) {
                        if (!intent.getBooleanExtra("permission", false)) {
                            RFIDReader.this.logMsg("Permission denied for device ");
                        } else {
                            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(UtilityConfig.KEY_DEVICE_INFO);
                            RFIDReader.this.logMsg("Opening reader");
                            new OpenTask().execute(usbDevice);
                        }
                    }
                }
                return;
            }
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                synchronized (this) {
                    RFIDReader.this.logMsg("ACTION_USB_DEVICE_DETACHED...");
                    if (!RFIDReader.this.findNFCDevice()) {
                        RFIDReader.this.logMsg("Closing reader...");
                        new CloseTask().execute(new Void[0]);
                    }
                }
                return;
            }
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                synchronized (this) {
                    RFIDReader.this.logMsg("ACTION_USB_DEVICE_ATTACHED...");
                    if (RFIDReader.this.findNFCDevice()) {
                        Pdlog.m3273d("NFC", "NFC device attached");
                        RFIDReader.this.startConnectThread();
                    }
                }
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public interface OnUIDListener {
        void onSwipe(byte[] bArr, int i);
    }

    public void open(Context context, OnUIDListener onUIDListener) {
        this.mContext = context;
        this.mManager = (UsbManager) context.getSystemService("usb");
        this.mUIDListener = onUIDListener;
        Reader reader = new Reader(this.mManager);
        this.mReader = reader;
        reader.setOnStateChangeListener(new Reader.OnStateChangeListener() { // from class: com.pudutech.recyclebot.robot.nfc.RFIDReader.1
            @Override // com.acs.smartcard.Reader.OnStateChangeListener
            public void onStateChange(int i, int i2, int i3) {
                if (i2 < 0 || i2 > 6) {
                    i2 = 0;
                }
                if (i3 < 0 || i3 > 6) {
                    i3 = 0;
                }
                String str = "Slot " + i + ": " + RFIDReader.stateStrings[i2] + " -> " + RFIDReader.stateStrings[i3];
                RFIDReader.this.mSlot = i;
                if (i2 == 1 && i3 == 2) {
                    RFIDReader.this.logMsg(str);
                    RFIDReader rFIDReader = RFIDReader.this;
                    rFIDReader.getUID(rFIDReader.mSlot);
                }
            }
        });
        this.mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_USB_PERMISSION);
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
        byte[] bArr = {Byte.MIN_VALUE, BinaryMemcacheOpcodes.GATKQ, 0, 0, 8, -1, -1, -1, -1, -1, -1, -1, -1};
        this.mPinModify.setTimeOut(0);
        this.mPinModify.setTimeOut2(0);
        this.mPinModify.setFormatString(0);
        this.mPinModify.setPinBlockString(8);
        this.mPinModify.setPinLengthFormat(0);
        this.mPinModify.setInsertionOffsetOld(0);
        this.mPinModify.setInsertionOffsetNew(0);
        this.mPinModify.setPinMaxExtraDigit(1032);
        this.mPinModify.setConfirmPin(1);
        this.mPinModify.setEntryValidationCondition(3);
        this.mPinModify.setNumberMessage(2);
        this.mPinModify.setLangId(1033);
        this.mPinModify.setMsgIndex1(0);
        this.mPinModify.setMsgIndex2(1);
        this.mPinModify.setMsgIndex3(0);
        this.mPinModify.setTeoPrologue(0, 0);
        this.mPinModify.setTeoPrologue(1, 0);
        this.mPinModify.setTeoPrologue(2, 0);
        this.mPinModify.setData(bArr, 13);
        this.mReadKeyOption.setTimeOut(0);
        this.mReadKeyOption.setPinMaxExtraDigit(1032);
        this.mReadKeyOption.setKeyReturnCondition(1);
        this.mReadKeyOption.setEchoLcdStartPosition(0);
        this.mReadKeyOption.setEchoLcdMode(1);
        startConnectThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startConnectThread() {
        new Thread(new Runnable() { // from class: com.pudutech.recyclebot.robot.nfc.RFIDReader.2
            @Override // java.lang.Runnable
            public void run() {
                boolean z = false;
                while (!z) {
                    Iterator<UsbDevice> it = RFIDReader.this.mManager.getDeviceList().values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        UsbDevice next = it.next();
                        if (RFIDReader.this.mReader.isSupported(next)) {
                            RFIDReader.this.mManager.requestPermission(next, RFIDReader.this.mPermissionIntent);
                            Pdlog.m3273d("NFC", "find NFC device, product name", next.getProductName());
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        return;
                    }
                    Pdlog.m3273d("NFC", "not nfc device, need recheck");
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception unused) {
                        Pdlog.m3274e("NFC", "connect nfc device thread exception");
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class SetProtocolParams {
        public int preferredProtocols;
        public int slotNum;

        private SetProtocolParams() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class SetProtocolResult {
        public int activeProtocol;

        /* renamed from: e */
        public Exception f7078e;

        private SetProtocolResult() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class SetProtocolTask extends AsyncTask<SetProtocolParams, Void, SetProtocolResult> {
        private SetProtocolTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public SetProtocolResult doInBackground(SetProtocolParams... setProtocolParamsArr) {
            SetProtocolResult setProtocolResult = new SetProtocolResult();
            try {
                setProtocolResult.activeProtocol = RFIDReader.this.mReader.setProtocol(setProtocolParamsArr[0].slotNum, setProtocolParamsArr[0].preferredProtocols);
            } catch (Exception e) {
                setProtocolResult.f7078e = e;
            }
            return setProtocolResult;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(SetProtocolResult setProtocolResult) {
            String str;
            if (setProtocolResult.f7078e != null) {
                RFIDReader.this.logMsg(setProtocolResult.f7078e.toString());
                return;
            }
            int i = setProtocolResult.activeProtocol;
            if (i == 1) {
                str = "Active Protocol: T=0";
            } else if (i == 2) {
                str = "Active Protocol: T=1";
            } else {
                str = "Active Protocol: Unknown";
            }
            RFIDReader.this.logMsg(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class TransmitParams {
        public String commandString;
        public int controlCode;
        public int slotNum;

        private TransmitParams() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class TransmitProgress {
        public byte[] command;
        public int commandLength;
        public int controlCode;

        /* renamed from: e */
        public Exception f7079e;
        public byte[] response;
        public int responseLength;

        private TransmitProgress() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class TransmitTask extends AsyncTask<TransmitParams, TransmitProgress, Void> {
        private TransmitTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(TransmitParams... transmitParamsArr) {
            int i = 0;
            while (true) {
                int indexOf = transmitParamsArr[0].commandString.indexOf(10, i);
                byte[] byteArray = indexOf >= 0 ? RFIDReader.this.toByteArray(transmitParamsArr[0].commandString.substring(i, indexOf)) : RFIDReader.this.toByteArray(transmitParamsArr[0].commandString.substring(i));
                int i2 = indexOf + 1;
                byte[] bArr = new byte[65538];
                TransmitProgress transmitProgress = new TransmitProgress();
                transmitProgress.controlCode = transmitParamsArr[0].controlCode;
                try {
                    int transmit = transmitParamsArr[0].controlCode < 0 ? RFIDReader.this.mReader.transmit(transmitParamsArr[0].slotNum, byteArray, byteArray.length, bArr, 65538) : RFIDReader.this.mReader.control(transmitParamsArr[0].slotNum, transmitParamsArr[0].controlCode, byteArray, byteArray.length, bArr, 65538);
                    transmitProgress.command = byteArray;
                    transmitProgress.commandLength = byteArray.length;
                    transmitProgress.response = bArr;
                    transmitProgress.responseLength = transmit;
                    transmitProgress.f7079e = null;
                } catch (Exception e) {
                    transmitProgress.command = null;
                    transmitProgress.commandLength = 0;
                    transmitProgress.response = null;
                    transmitProgress.responseLength = 0;
                    transmitProgress.f7079e = e;
                }
                publishProgress(transmitProgress);
                if (indexOf < 0) {
                    return null;
                }
                i = i2;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(TransmitProgress... transmitProgressArr) {
            RFIDReader.this.logMsg("print transmit");
            if (transmitProgressArr[0].f7079e != null) {
                RFIDReader.this.logMsg(transmitProgressArr[0].f7079e.toString());
                return;
            }
            RFIDReader.this.logMsg("Command:");
            RFIDReader.this.logBuffer(transmitProgressArr[0].command, transmitProgressArr[0].commandLength);
            RFIDReader.this.logMsg("Response:");
            RFIDReader.this.logBuffer(transmitProgressArr[0].response, transmitProgressArr[0].responseLength);
            if (transmitProgressArr[0].responseLength == 6 && RFIDReader.this.mUIDListener != null) {
                RFIDReader.this.mUIDListener.onSwipe(transmitProgressArr[0].response, transmitProgressArr[0].responseLength);
            }
            if (transmitProgressArr[0].response == null || transmitProgressArr[0].responseLength <= 0) {
                return;
            }
            if (transmitProgressArr[0].controlCode == 3400) {
                RFIDReader.this.mFeatures.fromByteArray(transmitProgressArr[0].response, transmitProgressArr[0].responseLength);
                RFIDReader.this.logMsg("Features:");
                for (int i = 1; i <= 19; i++) {
                    int controlCode = RFIDReader.this.mFeatures.getControlCode(i);
                    if (controlCode >= 0) {
                        RFIDReader.this.logMsg("Control Code: " + controlCode + " (" + RFIDReader.featureStrings[i] + ")");
                    }
                }
            }
            int controlCode2 = RFIDReader.this.mFeatures.getControlCode(10);
            if (controlCode2 >= 0 && transmitProgressArr[0].controlCode == controlCode2) {
                PinProperties pinProperties = new PinProperties(transmitProgressArr[0].response, transmitProgressArr[0].responseLength);
                RFIDReader.this.logMsg("PIN Properties:");
                RFIDReader.this.logMsg("LCD Layout: " + RFIDReader.this.toHexString(pinProperties.getLcdLayout()));
                RFIDReader.this.logMsg("Entry Validation Condition: " + RFIDReader.this.toHexString(pinProperties.getEntryValidationCondition()));
                RFIDReader.this.logMsg("Timeout 2: " + RFIDReader.this.toHexString(pinProperties.getTimeOut2()));
            }
            int controlCode3 = RFIDReader.this.mFeatures.getControlCode(18);
            if (controlCode3 < 0 || transmitProgressArr[0].controlCode != controlCode3) {
                return;
            }
            TlvProperties tlvProperties = new TlvProperties(transmitProgressArr[0].response, transmitProgressArr[0].responseLength);
            RFIDReader.this.logMsg("TLV Properties:");
            for (int i2 = 1; i2 <= 12; i2++) {
                Object property = tlvProperties.getProperty(i2);
                if (property instanceof Integer) {
                    RFIDReader.this.logMsg(RFIDReader.propertyStrings[i2] + ": " + RFIDReader.this.toHexString(((Integer) property).intValue()));
                } else if (property instanceof String) {
                    RFIDReader.this.logMsg(RFIDReader.propertyStrings[i2] + ": " + property);
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    private class OpenTask extends AsyncTask<UsbDevice, Void, Exception> {
        private OpenTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(UsbDevice... usbDeviceArr) {
            try {
                RFIDReader.this.mReader.open(usbDeviceArr[0]);
            } catch (Exception e) {
                RFIDReader.this.logMsg(e.getMessage());
                if (!e.getMessage().equals("The device is not supported.")) {
                    return e;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Exception exc) {
            if (exc != null) {
                RFIDReader.this.logMsg(exc.toString());
                return;
            }
            RFIDReader.this.logMsg("Reader name: " + RFIDReader.this.mReader.getReaderName());
            int numSlots = RFIDReader.this.mReader.getNumSlots();
            RFIDReader.this.logMsg("Number of slots: " + numSlots);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    private class CloseTask extends AsyncTask<Void, Void, Void> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
        }

        private CloseTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            RFIDReader.this.mReader.close();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class PowerParams {
        public int action;
        public int slotNum;

        private PowerParams() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class PowerResult {
        public byte[] atr;

        /* renamed from: e */
        public Exception f7077e;

        private PowerResult() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes2.dex */
    public class PowerTask extends AsyncTask<PowerParams, Void, PowerResult> {
        private PowerTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public PowerResult doInBackground(PowerParams... powerParamsArr) {
            PowerResult powerResult = new PowerResult();
            try {
                powerResult.atr = RFIDReader.this.mReader.power(powerParamsArr[0].slotNum, powerParamsArr[0].action);
            } catch (Exception e) {
                powerResult.f7077e = e;
            }
            return powerResult;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(PowerResult powerResult) {
            if (powerResult.f7077e != null) {
                RFIDReader.this.logMsg(powerResult.f7077e.toString());
            } else if (powerResult.atr != null) {
                RFIDReader.this.logMsg("ATR:");
                RFIDReader.this.logBuffer(powerResult.atr, powerResult.atr.length);
            } else {
                RFIDReader.this.logMsg("ATR: None");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean findNFCDevice() {
        HashMap<String, UsbDevice> deviceList = this.mManager.getDeviceList();
        if (!deviceList.isEmpty()) {
            Iterator<Map.Entry<String, UsbDevice>> it = deviceList.entrySet().iterator();
            while (it.hasNext()) {
                UsbDevice value = it.next().getValue();
                int vendorId = value.getVendorId();
                int productId = value.getProductId();
                String productName = value.getProductName();
                if (productName != null) {
                    Pdlog.m3273d("NFC", "check NFC vid:" + String.format("0x%08X", Integer.valueOf(vendorId)) + " pid:" + String.format("0x%08X", Integer.valueOf(productId)) + " productName: " + productName);
                    if (vendorId == 1839 && productId == 8704) {
                        Pdlog.m3275i("NFC", "fined NFC device:" + String.format("0x%08X", Integer.valueOf(vendorId)) + " " + vendorId);
                        return true;
                    }
                } else {
                    Pdlog.m3277w("NFC", "productName is null");
                }
            }
        }
        return false;
    }

    private void power() {
        int i = this.mSlot;
        if (i != -1) {
            PowerParams powerParams = new PowerParams();
            powerParams.slotNum = i;
            powerParams.action = 2;
            logMsg("Slot " + i + ": " + powerActionStrings[2] + "...");
            new PowerTask().execute(powerParams);
        }
    }

    private void getState(int i) {
        if (i != -1) {
            try {
                logMsg("Slot " + i + ": Getting state...");
                int state = this.mReader.getState(i);
                if (state < 0 || state > 6) {
                    state = 0;
                }
                logMsg("State: " + stateStrings[state]);
            } catch (IllegalArgumentException e) {
                logMsg(e.toString());
            }
        }
    }

    private void setProtocol() {
        int i = this.mSlot;
        if (i != -1) {
            String str = "T=0/T=1";
            if (str.equals("")) {
                str = "None";
            }
            SetProtocolParams setProtocolParams = new SetProtocolParams();
            setProtocolParams.slotNum = i;
            setProtocolParams.preferredProtocols = 3;
            logMsg("Slot " + i + ": Setting protocol to " + str + "...");
            new SetProtocolTask().execute(setProtocolParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUID(int i) {
        getState(i);
        power();
        setProtocol();
        if (i != -1) {
            TransmitParams transmitParams = new TransmitParams();
            transmitParams.slotNum = i;
            transmitParams.controlCode = -1;
            transmitParams.commandString = "FF CA 00 00 00";
            logMsg("Slot " + i + ": Transmitting APDU...");
            new TransmitTask().execute(transmitParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMsg(String str) {
        Pdlog.m3273d("NFC", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logBuffer(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder();
        logMsg("bufferLength: " + i);
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString.toUpperCase());
            sb.append(" ");
        }
        logMsg(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] toByteArray(String str) {
        int i;
        int i2;
        int length = str.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'A' && charAt <= 'F') || (charAt >= 'a' && charAt <= 'f'))) {
                i3++;
            }
        }
        byte[] bArr = new byte[(i3 + 1) / 2];
        int i5 = 0;
        boolean z = true;
        for (int i6 = 0; i6 < length; i6++) {
            char charAt2 = str.charAt(i6);
            if (charAt2 < '0' || charAt2 > '9') {
                if (charAt2 >= 'A' && charAt2 <= 'F') {
                    i2 = charAt2 - 'A';
                } else if (charAt2 < 'a' || charAt2 > 'f') {
                    i = -1;
                } else {
                    i2 = charAt2 - 'a';
                }
                i = i2 + 10;
            } else {
                i = charAt2 - '0';
            }
            if (i >= 0) {
                if (z) {
                    bArr[i5] = (byte) (i << 4);
                } else {
                    bArr[i5] = (byte) (i | bArr[i5]);
                    i5++;
                }
                z = !z;
            }
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toHexString(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 != 0) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    private String toHexString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            str = str + hexString.toUpperCase() + " ";
        }
        return str;
    }
}
