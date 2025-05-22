package com.pudutech.bumblebee.robot.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener;
import com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.IUpdateListener;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDFaceScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface RobotInterface extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements RobotInterface {
        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void addDeliveryRobotListener(String str, IDeliveryRobotListener iDeliveryRobotListener) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void addDisinfectionRobotListener(String str, IDisinfectionRobotListener iDisinfectionRobotListener) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void addListener(String str, IRobotListener iRobotListener) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void addRecycleRobotListener(String str, IRecycleRobotListener iRecycleRobotListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void backFlowSprayLiquid() throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void broadcastToRemoteDevice(byte[] bArr) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void checkRemoteDeviceResponse() throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void controlLEDFaceScreen(LEDFaceScreenMode lEDFaceScreenMode) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void controlLEDScreen(LEDScreenMode lEDScreenMode) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void controlRGB(SurfaceLED surfaceLED, int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void flushLoraSerialPortCache() throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public PeripheralDeviceStatus getDeviceStatus(PeripheralDevice peripheralDevice) throws RemoteException {
            return null;
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void noticeToVIP(String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void open() throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void openSprayDevice(boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void openUvLampDevice(boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void removeDeliveryRobotListener(String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void removeDisinfectionRobotListener(String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void removeListener(String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void removeRecycleRobotListener(String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void requestPallets() throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void setPeripheralDevicePower(PeripheralDevice peripheralDevice, boolean z) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void setSprayPower(int i) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void setupLEDScreenContent(String str, int i) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void setupLEDScreenContentByYCoordinate(String str, int i, int i2) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void stopNoticeVIP() throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
        public void update(UpdateObject updateObject, IUpdateListener iUpdateListener, boolean z) throws RemoteException {
        }
    }

    void addDeliveryRobotListener(String str, IDeliveryRobotListener iDeliveryRobotListener) throws RemoteException;

    void addDisinfectionRobotListener(String str, IDisinfectionRobotListener iDisinfectionRobotListener) throws RemoteException;

    void addListener(String str, IRobotListener iRobotListener) throws RemoteException;

    void addRecycleRobotListener(String str, IRecycleRobotListener iRecycleRobotListener) throws RemoteException;

    void backFlowSprayLiquid() throws RemoteException;

    void broadcastToRemoteDevice(byte[] bArr) throws RemoteException;

    void checkRemoteDeviceResponse() throws RemoteException;

    void controlLEDFaceScreen(LEDFaceScreenMode lEDFaceScreenMode) throws RemoteException;

    void controlLEDScreen(LEDScreenMode lEDScreenMode) throws RemoteException;

    void controlRGB(SurfaceLED surfaceLED, int i, int i2, int i3) throws RemoteException;

    void flushLoraSerialPortCache() throws RemoteException;

    PeripheralDeviceStatus getDeviceStatus(PeripheralDevice peripheralDevice) throws RemoteException;

    void noticeToVIP(String str) throws RemoteException;

    void open() throws RemoteException;

    void openSprayDevice(boolean z, boolean z2) throws RemoteException;

    void openUvLampDevice(boolean z, boolean z2) throws RemoteException;

    void removeDeliveryRobotListener(String str) throws RemoteException;

    void removeDisinfectionRobotListener(String str) throws RemoteException;

    void removeListener(String str) throws RemoteException;

    void removeRecycleRobotListener(String str) throws RemoteException;

    void requestPallets() throws RemoteException;

    void setPeripheralDevicePower(PeripheralDevice peripheralDevice, boolean z) throws RemoteException;

    void setSprayPower(int i) throws RemoteException;

    void setupLEDScreenContent(String str, int i) throws RemoteException;

    void setupLEDScreenContentByYCoordinate(String str, int i, int i2) throws RemoteException;

    void stopNoticeVIP() throws RemoteException;

    void update(UpdateObject updateObject, IUpdateListener iUpdateListener, boolean z) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements RobotInterface {
        private static final String DESCRIPTOR = "com.pudutech.bumblebee.robot.aidl.RobotInterface";
        static final int TRANSACTION_addDeliveryRobotListener = 3;
        static final int TRANSACTION_addDisinfectionRobotListener = 26;
        static final int TRANSACTION_addListener = 1;
        static final int TRANSACTION_addRecycleRobotListener = 5;
        static final int TRANSACTION_backFlowSprayLiquid = 25;
        static final int TRANSACTION_broadcastToRemoteDevice = 19;
        static final int TRANSACTION_checkRemoteDeviceResponse = 20;
        static final int TRANSACTION_controlLEDFaceScreen = 18;
        static final int TRANSACTION_controlLEDScreen = 11;
        static final int TRANSACTION_controlRGB = 15;
        static final int TRANSACTION_flushLoraSerialPortCache = 21;
        static final int TRANSACTION_getDeviceStatus = 8;
        static final int TRANSACTION_noticeToVIP = 16;
        static final int TRANSACTION_open = 7;
        static final int TRANSACTION_openSprayDevice = 22;
        static final int TRANSACTION_openUvLampDevice = 24;
        static final int TRANSACTION_removeDeliveryRobotListener = 4;
        static final int TRANSACTION_removeDisinfectionRobotListener = 27;
        static final int TRANSACTION_removeListener = 2;
        static final int TRANSACTION_removeRecycleRobotListener = 6;
        static final int TRANSACTION_requestPallets = 14;
        static final int TRANSACTION_setPeripheralDevicePower = 9;
        static final int TRANSACTION_setSprayPower = 23;
        static final int TRANSACTION_setupLEDScreenContent = 12;
        static final int TRANSACTION_setupLEDScreenContentByYCoordinate = 13;
        static final int TRANSACTION_stopNoticeVIP = 17;
        static final int TRANSACTION_update = 10;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RobotInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof RobotInterface)) {
                return (RobotInterface) queryLocalInterface;
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
                    addListener(parcel.readString(), IRobotListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDeliveryRobotListener(parcel.readString(), IDeliveryRobotListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDeliveryRobotListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addRecycleRobotListener(parcel.readString(), IRecycleRobotListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeRecycleRobotListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    open();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    PeripheralDeviceStatus deviceStatus = getDeviceStatus(parcel.readInt() != 0 ? PeripheralDevice.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (deviceStatus != null) {
                        parcel2.writeInt(1);
                        deviceStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPeripheralDevicePower(parcel.readInt() != 0 ? PeripheralDevice.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    update(parcel.readInt() != 0 ? UpdateObject.INSTANCE.createFromParcel(parcel) : null, IUpdateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlLEDScreen(parcel.readInt() != 0 ? LEDScreenMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setupLEDScreenContent(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setupLEDScreenContentByYCoordinate(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestPallets();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlRGB(parcel.readInt() != 0 ? SurfaceLED.INSTANCE.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    noticeToVIP(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopNoticeVIP();
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlLEDFaceScreen(parcel.readInt() != 0 ? LEDFaceScreenMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    broadcastToRemoteDevice(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    checkRemoteDeviceResponse();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    flushLoraSerialPortCache();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    openSprayDevice(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSprayPower(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    openUvLampDevice(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    backFlowSprayLiquid();
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDisinfectionRobotListener(parcel.readString(), IDisinfectionRobotListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDisinfectionRobotListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
         */
        /* loaded from: classes.dex */
        public static class Proxy implements RobotInterface {
            public static RobotInterface sDefaultImpl;
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

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void addListener(String str, IRobotListener iRobotListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iRobotListener != null ? iRobotListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addListener(str, iRobotListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void removeListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeListener(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void addDeliveryRobotListener(String str, IDeliveryRobotListener iDeliveryRobotListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iDeliveryRobotListener != null ? iDeliveryRobotListener.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDeliveryRobotListener(str, iDeliveryRobotListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void removeDeliveryRobotListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDeliveryRobotListener(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void addRecycleRobotListener(String str, IRecycleRobotListener iRecycleRobotListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iRecycleRobotListener != null ? iRecycleRobotListener.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addRecycleRobotListener(str, iRecycleRobotListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void removeRecycleRobotListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeRecycleRobotListener(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void open() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().open();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public PeripheralDeviceStatus getDeviceStatus(PeripheralDevice peripheralDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (peripheralDevice != null) {
                        obtain.writeInt(1);
                        peripheralDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceStatus(peripheralDevice);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? PeripheralDeviceStatus.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void setPeripheralDevicePower(PeripheralDevice peripheralDevice, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (peripheralDevice != null) {
                        obtain.writeInt(1);
                        peripheralDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPeripheralDevicePower(peripheralDevice, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void update(UpdateObject updateObject, IUpdateListener iUpdateListener, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (updateObject != null) {
                        obtain.writeInt(1);
                        updateObject.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iUpdateListener != null ? iUpdateListener.asBinder() : null);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().update(updateObject, iUpdateListener, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void controlLEDScreen(LEDScreenMode lEDScreenMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lEDScreenMode != null) {
                        obtain.writeInt(1);
                        lEDScreenMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlLEDScreen(lEDScreenMode);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void setupLEDScreenContent(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setupLEDScreenContent(str, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void setupLEDScreenContentByYCoordinate(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setupLEDScreenContentByYCoordinate(str, i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void requestPallets() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestPallets();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void controlRGB(SurfaceLED surfaceLED, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surfaceLED != null) {
                        obtain.writeInt(1);
                        surfaceLED.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlRGB(surfaceLED, i, i2, i3);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void noticeToVIP(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().noticeToVIP(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void stopNoticeVIP() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopNoticeVIP();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void controlLEDFaceScreen(LEDFaceScreenMode lEDFaceScreenMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lEDFaceScreenMode != null) {
                        obtain.writeInt(1);
                        lEDFaceScreenMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlLEDFaceScreen(lEDFaceScreenMode);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void broadcastToRemoteDevice(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().broadcastToRemoteDevice(bArr);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void checkRemoteDeviceResponse() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().checkRemoteDeviceResponse();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void flushLoraSerialPortCache() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().flushLoraSerialPortCache();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void openSprayDevice(boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openSprayDevice(z, z2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void setSprayPower(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSprayPower(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void openUvLampDevice(boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openUvLampDevice(z, z2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void backFlowSprayLiquid() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().backFlowSprayLiquid();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void addDisinfectionRobotListener(String str, IDisinfectionRobotListener iDisinfectionRobotListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iDisinfectionRobotListener != null ? iDisinfectionRobotListener.asBinder() : null);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDisinfectionRobotListener(str, iDisinfectionRobotListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.RobotInterface
            public void removeDisinfectionRobotListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDisinfectionRobotListener(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(RobotInterface robotInterface) {
            if (Proxy.sDefaultImpl != null || robotInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = robotInterface;
            return true;
        }

        public static RobotInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
