package com.pudutech.mirsdk.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.IAccelerationData;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.UsbControlInterface;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.HardwareVersion;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface HardwareInterface extends IInterface {
    void addAccelerationListener(String str, IAccelerationData iAccelerationData) throws RemoteException;

    void addCANDataListener(String str, byte[] bArr, ICANData iCANData) throws RemoteException;

    void addListener(String str, IHardware iHardware) throws RemoteException;

    void addSensorListener(String str, ISensorData iSensorData) throws RemoteException;

    void clearWheelError() throws RemoteException;

    void controlCameraIRDLED(boolean z) throws RemoteException;

    void controlDevice(DeviceType deviceType, boolean z) throws RemoteException;

    void controlDisinfectionPower(boolean z) throws RemoteException;

    void controlRGBD(boolean z) throws RemoteException;

    void controlSlamCorePower(boolean z) throws RemoteException;

    void controlWheel(double d, double d2, boolean z) throws RemoteException;

    void getAccelerationData(AccelerationType accelerationType) throws RemoteException;

    CameraInterface getCamera() throws RemoteException;

    boolean getCarpetMode() throws RemoteException;

    String getCommitList() throws RemoteException;

    HardwareVersion[] getHardwareVersion() throws RemoteException;

    String getLastError(List<DeviceType> list) throws RemoteException;

    LidarInterface getLidarInterface() throws RemoteException;

    MachineInfo getMachineInfo() throws RemoteException;

    RGBDInterface getRGBDInterface() throws RemoteException;

    ScheduleCommunication getScheduler() throws RemoteException;

    UsbControlInterface getUsbControlInterface() throws RemoteException;

    boolean oilStainCheck() throws RemoteException;

    void open() throws RemoteException;

    void openCAN() throws RemoteException;

    void openCANEx(List<DeviceType> list) throws RemoteException;

    void refreshMachineInfo() throws RemoteException;

    void removeAccelerationListener(String str) throws RemoteException;

    void removeCANDataListener(String str) throws RemoteException;

    void removeListener(String str) throws RemoteException;

    void removeSensorListener(String str) throws RemoteException;

    void sendCAN(byte[] bArr) throws RemoteException;

    void setAccelerationData(AccelerationType accelerationType, double d) throws RemoteException;

    void setRgbdParkingMode(boolean z) throws RemoteException;

    void suspendChargingUsePile() throws RemoteException;

    void switchCarpetMode(boolean z) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements HardwareInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.hardware.HardwareInterface";
        static final int TRANSACTION_addAccelerationListener = 28;
        static final int TRANSACTION_addCANDataListener = 16;
        static final int TRANSACTION_addListener = 5;
        static final int TRANSACTION_addSensorListener = 11;
        static final int TRANSACTION_clearWheelError = 15;
        static final int TRANSACTION_controlCameraIRDLED = 13;
        static final int TRANSACTION_controlDevice = 32;
        static final int TRANSACTION_controlDisinfectionPower = 26;
        static final int TRANSACTION_controlRGBD = 24;
        static final int TRANSACTION_controlSlamCorePower = 35;
        static final int TRANSACTION_controlWheel = 14;
        static final int TRANSACTION_getAccelerationData = 30;
        static final int TRANSACTION_getCamera = 20;
        static final int TRANSACTION_getCarpetMode = 23;
        static final int TRANSACTION_getCommitList = 4;
        static final int TRANSACTION_getHardwareVersion = 7;
        static final int TRANSACTION_getLastError = 34;
        static final int TRANSACTION_getLidarInterface = 9;
        static final int TRANSACTION_getMachineInfo = 8;
        static final int TRANSACTION_getRGBDInterface = 19;
        static final int TRANSACTION_getScheduler = 10;
        static final int TRANSACTION_getUsbControlInterface = 36;
        static final int TRANSACTION_oilStainCheck = 25;
        static final int TRANSACTION_open = 1;
        static final int TRANSACTION_openCAN = 2;
        static final int TRANSACTION_openCANEx = 3;
        static final int TRANSACTION_refreshMachineInfo = 21;
        static final int TRANSACTION_removeAccelerationListener = 29;
        static final int TRANSACTION_removeCANDataListener = 17;
        static final int TRANSACTION_removeListener = 6;
        static final int TRANSACTION_removeSensorListener = 12;
        static final int TRANSACTION_sendCAN = 18;
        static final int TRANSACTION_setAccelerationData = 31;
        static final int TRANSACTION_setRgbdParkingMode = 27;
        static final int TRANSACTION_suspendChargingUsePile = 33;
        static final int TRANSACTION_switchCarpetMode = 22;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static HardwareInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof HardwareInterface)) {
                return (HardwareInterface) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    open();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    openCAN();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    openCANEx(parcel.createTypedArrayList(DeviceType.INSTANCE));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String commitList = getCommitList();
                    parcel2.writeNoException();
                    parcel2.writeString(commitList);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addListener(parcel.readString(), IHardware.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    HardwareVersion[] hardwareVersion = getHardwareVersion();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(hardwareVersion, 1);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    MachineInfo machineInfo = getMachineInfo();
                    parcel2.writeNoException();
                    if (machineInfo != null) {
                        parcel2.writeInt(1);
                        machineInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    LidarInterface lidarInterface = getLidarInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(lidarInterface != null ? lidarInterface.asBinder() : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    ScheduleCommunication scheduler = getScheduler();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(scheduler != null ? scheduler.asBinder() : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSensorListener(parcel.readString(), ISensorData.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeSensorListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlCameraIRDLED(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlWheel(parcel.readDouble(), parcel.readDouble(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearWheelError();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCANDataListener(parcel.readString(), parcel.createByteArray(), ICANData.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCANDataListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendCAN(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    RGBDInterface rGBDInterface = getRGBDInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(rGBDInterface != null ? rGBDInterface.asBinder() : null);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraInterface camera = getCamera();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(camera != null ? camera.asBinder() : null);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    refreshMachineInfo();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchCarpetMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean carpetMode = getCarpetMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(carpetMode ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlRGBD(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean oilStainCheck = oilStainCheck();
                    parcel2.writeNoException();
                    parcel2.writeInt(oilStainCheck ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlDisinfectionPower(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRgbdParkingMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAccelerationListener(parcel.readString(), IAccelerationData.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAccelerationListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAccelerationData(parcel.readInt() != 0 ? AccelerationType.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAccelerationData(parcel.readInt() != 0 ? AccelerationType.INSTANCE.createFromParcel(parcel) : null, parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlDevice(parcel.readInt() != 0 ? DeviceType.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    suspendChargingUsePile();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    String lastError = getLastError(parcel.createTypedArrayList(DeviceType.INSTANCE));
                    parcel2.writeNoException();
                    parcel2.writeString(lastError);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlSlamCorePower(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsbControlInterface usbControlInterface = getUsbControlInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(usbControlInterface != null ? usbControlInterface.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
          classes5.dex
         */
        /* loaded from: classes.dex */
        private static class Proxy implements HardwareInterface {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void open() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void openCAN() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void openCANEx(List<DeviceType> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public String getCommitList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void addListener(String str, IHardware iHardware) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iHardware != null ? iHardware.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void removeListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public HardwareVersion[] getHardwareVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return (HardwareVersion[]) obtain2.createTypedArray(HardwareVersion.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public MachineInfo getMachineInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MachineInfo.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public LidarInterface getLidarInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return LidarInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public ScheduleCommunication getScheduler() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return ScheduleCommunication.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void addSensorListener(String str, ISensorData iSensorData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSensorData != null ? iSensorData.asBinder() : null);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void removeSensorListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void controlCameraIRDLED(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void controlWheel(double d, double d2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void clearWheelError() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void addCANDataListener(String str, byte[] bArr, ICANData iCANData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeStrongBinder(iCANData != null ? iCANData.asBinder() : null);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void removeCANDataListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void sendCAN(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public RGBDInterface getRGBDInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return RGBDInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public CameraInterface getCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return CameraInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void refreshMachineInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void switchCarpetMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public boolean getCarpetMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void controlRGBD(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public boolean oilStainCheck() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void controlDisinfectionPower(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void setRgbdParkingMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void addAccelerationListener(String str, IAccelerationData iAccelerationData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iAccelerationData != null ? iAccelerationData.asBinder() : null);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void removeAccelerationListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void getAccelerationData(AccelerationType accelerationType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accelerationType != null) {
                        obtain.writeInt(1);
                        accelerationType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void setAccelerationData(AccelerationType accelerationType, double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accelerationType != null) {
                        obtain.writeInt(1);
                        accelerationType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeDouble(d);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void controlDevice(DeviceType deviceType, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (deviceType != null) {
                        obtain.writeInt(1);
                        deviceType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void suspendChargingUsePile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public String getLastError(List<DeviceType> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public void controlSlamCorePower(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.HardwareInterface
            public UsbControlInterface getUsbControlInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return UsbControlInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
