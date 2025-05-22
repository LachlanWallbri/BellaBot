package com.pudutech.bumblebee.robot.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.bumblebee.robot.aidl.serialize.FaultLevel;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.PowerOffEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface IRobotListener extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements IRobotListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onDeviceStatusChange(PeripheralDevice peripheralDevice, PeripheralDeviceStatus peripheralDeviceStatus, String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onFault(FaultLevel faultLevel, String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onPowerOffEvent(PowerOffEvent powerOffEvent) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
        public void onTouch(TouchPlace touchPlace, TouchState touchState) throws RemoteException {
        }
    }

    void onDeviceStatusChange(PeripheralDevice peripheralDevice, PeripheralDeviceStatus peripheralDeviceStatus, String str) throws RemoteException;

    void onFault(FaultLevel faultLevel, String str) throws RemoteException;

    void onPowerOffEvent(PowerOffEvent powerOffEvent) throws RemoteException;

    void onTouch(TouchPlace touchPlace, TouchState touchState) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRobotListener {
        private static final String DESCRIPTOR = "com.pudutech.bumblebee.robot.aidl.IRobotListener";
        static final int TRANSACTION_onDeviceStatusChange = 3;
        static final int TRANSACTION_onFault = 4;
        static final int TRANSACTION_onPowerOffEvent = 1;
        static final int TRANSACTION_onTouch = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRobotListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRobotListener)) {
                return (IRobotListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPowerOffEvent(parcel.readInt() != 0 ? PowerOffEvent.INSTANCE.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onTouch(parcel.readInt() != 0 ? TouchPlace.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TouchState.INSTANCE.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onDeviceStatusChange(parcel.readInt() != 0 ? PeripheralDevice.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PeripheralDeviceStatus.INSTANCE.createFromParcel(parcel) : null, parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onFault(parcel.readInt() != 0 ? FaultLevel.INSTANCE.createFromParcel(parcel) : null, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
         */
        /* loaded from: classes.dex */
        public static class Proxy implements IRobotListener {
            public static IRobotListener sDefaultImpl;
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

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onPowerOffEvent(PowerOffEvent powerOffEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (powerOffEvent != null) {
                        obtain.writeInt(1);
                        powerOffEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPowerOffEvent(powerOffEvent);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onTouch(TouchPlace touchPlace, TouchState touchState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (touchPlace != null) {
                        obtain.writeInt(1);
                        touchPlace.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (touchState != null) {
                        obtain.writeInt(1);
                        touchState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTouch(touchPlace, touchState);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onDeviceStatusChange(PeripheralDevice peripheralDevice, PeripheralDeviceStatus peripheralDeviceStatus, String str) throws RemoteException {
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
                    if (peripheralDeviceStatus != null) {
                        obtain.writeInt(1);
                        peripheralDeviceStatus.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceStatusChange(peripheralDevice, peripheralDeviceStatus, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
            public void onFault(FaultLevel faultLevel, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (faultLevel != null) {
                        obtain.writeInt(1);
                        faultLevel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFault(faultLevel, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRobotListener iRobotListener) {
            if (Proxy.sDefaultImpl != null || iRobotListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRobotListener;
            return true;
        }

        public static IRobotListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
