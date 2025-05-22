package com.pudutech.mirsdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.IDeviceListener;
import com.pudutech.mirsdk.aidl.serialize.BoardInfo;
import com.pudutech.mirsdk.aidl.serialize.LidarDeviceInfo;
import com.pudutech.mirsdk.aidl.serialize.RgbdDeviceInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public interface DeviceInterface extends IInterface {
    void addListener(String str, IDeviceListener iDeviceListener) throws RemoteException;

    void controlIRLED(boolean z) throws RemoteException;

    void controlRGBD(boolean z) throws RemoteException;

    String getBatterySn() throws RemoteException;

    List<BoardInfo> getBoardUidList() throws RemoteException;

    List<String> getCameraSnList() throws RemoteException;

    List<LidarDeviceInfo> getLidarDeviceList() throws RemoteException;

    List<String> getLidarSnList() throws RemoteException;

    List<RgbdDeviceInfo> getRgbdDeviceList() throws RemoteException;

    List<String> getRgbdSnList() throws RemoteException;

    void lockMotor(boolean z) throws RemoteException;

    void removeListener(String str) throws RemoteException;

    void resetUSB() throws RemoteException;

    boolean rgbdOilStainCheck() throws RemoteException;

    void runDisinfection() throws RemoteException;

    void runMarkerCamera() throws RemoteException;

    void startLidar() throws RemoteException;

    void stopDisinfection() throws RemoteException;

    void stopLidar() throws RemoteException;

    void stopMarkerCamera() throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements DeviceInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.DeviceInterface";
        static final int TRANSACTION_addListener = 1;
        static final int TRANSACTION_controlIRLED = 5;
        static final int TRANSACTION_controlRGBD = 6;
        static final int TRANSACTION_getBatterySn = 17;
        static final int TRANSACTION_getBoardUidList = 18;
        static final int TRANSACTION_getCameraSnList = 16;
        static final int TRANSACTION_getLidarDeviceList = 20;
        static final int TRANSACTION_getLidarSnList = 15;
        static final int TRANSACTION_getRgbdDeviceList = 19;
        static final int TRANSACTION_getRgbdSnList = 14;
        static final int TRANSACTION_lockMotor = 12;
        static final int TRANSACTION_removeListener = 2;
        static final int TRANSACTION_resetUSB = 13;
        static final int TRANSACTION_rgbdOilStainCheck = 7;
        static final int TRANSACTION_runDisinfection = 10;
        static final int TRANSACTION_runMarkerCamera = 8;
        static final int TRANSACTION_startLidar = 3;
        static final int TRANSACTION_stopDisinfection = 11;
        static final int TRANSACTION_stopLidar = 4;
        static final int TRANSACTION_stopMarkerCamera = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static DeviceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceInterface)) {
                return (DeviceInterface) queryLocalInterface;
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
                    addListener(parcel.readString(), IDeviceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    startLidar();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopLidar();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlIRLED(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlRGBD(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean rgbdOilStainCheck = rgbdOilStainCheck();
                    parcel2.writeNoException();
                    parcel2.writeInt(rgbdOilStainCheck ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    runMarkerCamera();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopMarkerCamera();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    runDisinfection();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopDisinfection();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    lockMotor(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetUSB();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> rgbdSnList = getRgbdSnList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(rgbdSnList);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> lidarSnList = getLidarSnList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(lidarSnList);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> cameraSnList = getCameraSnList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(cameraSnList);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    String batterySn = getBatterySn();
                    parcel2.writeNoException();
                    parcel2.writeString(batterySn);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BoardInfo> boardUidList = getBoardUidList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(boardUidList);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<RgbdDeviceInfo> rgbdDeviceList = getRgbdDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(rgbdDeviceList);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<LidarDeviceInfo> lidarDeviceList = getLidarDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(lidarDeviceList);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes5.dex */
        private static class Proxy implements DeviceInterface {
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

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void addListener(String str, IDeviceListener iDeviceListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iDeviceListener != null ? iDeviceListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void removeListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void startLidar() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void stopLidar() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void controlIRLED(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void controlRGBD(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public boolean rgbdOilStainCheck() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void runMarkerCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void stopMarkerCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void runDisinfection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void stopDisinfection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void lockMotor(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public void resetUSB() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public List<String> getRgbdSnList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public List<String> getLidarSnList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public List<String> getCameraSnList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public String getBatterySn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public List<BoardInfo> getBoardUidList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(BoardInfo.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public List<RgbdDeviceInfo> getRgbdDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(RgbdDeviceInfo.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.DeviceInterface
            public List<LidarDeviceInfo> getLidarDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(LidarDeviceInfo.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
