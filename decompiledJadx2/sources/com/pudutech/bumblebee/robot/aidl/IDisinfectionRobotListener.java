package com.pudutech.bumblebee.robot.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import com.pudutech.bumblebee.robot.aidl.serialize.SprayDeviceError;
import com.pudutech.bumblebee.robot.aidl.serialize.UvLampDeviceError;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface IDisinfectionRobotListener extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements IDisinfectionRobotListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onLiquidLevelChange(double d) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onSprayChamberLevelChange(double d) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onSprayDiveceError(SprayDeviceError[] sprayDeviceErrorArr) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onSprayDiveceOpen(boolean z) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onSprayLiquidStatus(boolean z) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onSpringOpenStatus(boolean z) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onUvLampDeviceError(UvLampDeviceError[] uvLampDeviceErrorArr) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onUvLampDeviceOpen(boolean z) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
        public void onUvLampPlateOpenState(OpenState openState) throws RemoteException {
        }
    }

    void onLiquidLevelChange(double d) throws RemoteException;

    void onSprayChamberLevelChange(double d) throws RemoteException;

    void onSprayDiveceError(SprayDeviceError[] sprayDeviceErrorArr) throws RemoteException;

    void onSprayDiveceOpen(boolean z) throws RemoteException;

    void onSprayLiquidStatus(boolean z) throws RemoteException;

    void onSpringOpenStatus(boolean z) throws RemoteException;

    void onUvLampDeviceError(UvLampDeviceError[] uvLampDeviceErrorArr) throws RemoteException;

    void onUvLampDeviceOpen(boolean z) throws RemoteException;

    void onUvLampPlateOpenState(OpenState openState) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDisinfectionRobotListener {
        private static final String DESCRIPTOR = "com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener";
        static final int TRANSACTION_onLiquidLevelChange = 2;
        static final int TRANSACTION_onSprayChamberLevelChange = 3;
        static final int TRANSACTION_onSprayDiveceError = 7;
        static final int TRANSACTION_onSprayDiveceOpen = 1;
        static final int TRANSACTION_onSprayLiquidStatus = 6;
        static final int TRANSACTION_onSpringOpenStatus = 5;
        static final int TRANSACTION_onUvLampDeviceError = 8;
        static final int TRANSACTION_onUvLampDeviceOpen = 4;
        static final int TRANSACTION_onUvLampPlateOpenState = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDisinfectionRobotListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDisinfectionRobotListener)) {
                return (IDisinfectionRobotListener) queryLocalInterface;
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
                    onSprayDiveceOpen(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLiquidLevelChange(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSprayChamberLevelChange(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUvLampDeviceOpen(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSpringOpenStatus(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSprayLiquidStatus(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSprayDiveceError((SprayDeviceError[]) parcel.createTypedArray(SprayDeviceError.INSTANCE));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUvLampDeviceError((UvLampDeviceError[]) parcel.createTypedArray(UvLampDeviceError.INSTANCE));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUvLampPlateOpenState(parcel.readInt() != 0 ? OpenState.INSTANCE.createFromParcel(parcel) : null);
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
        public static class Proxy implements IDisinfectionRobotListener {
            public static IDisinfectionRobotListener sDefaultImpl;
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

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onSprayDiveceOpen(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSprayDiveceOpen(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onLiquidLevelChange(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLiquidLevelChange(d);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onSprayChamberLevelChange(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSprayChamberLevelChange(d);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onUvLampDeviceOpen(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUvLampDeviceOpen(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onSpringOpenStatus(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSpringOpenStatus(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onSprayLiquidStatus(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSprayLiquidStatus(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onSprayDiveceError(SprayDeviceError[] sprayDeviceErrorArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(sprayDeviceErrorArr, 0);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSprayDiveceError(sprayDeviceErrorArr);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onUvLampDeviceError(UvLampDeviceError[] uvLampDeviceErrorArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(uvLampDeviceErrorArr, 0);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUvLampDeviceError(uvLampDeviceErrorArr);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
            public void onUvLampPlateOpenState(OpenState openState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (openState != null) {
                        obtain.writeInt(1);
                        openState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUvLampPlateOpenState(openState);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisinfectionRobotListener iDisinfectionRobotListener) {
            if (Proxy.sDefaultImpl != null || iDisinfectionRobotListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisinfectionRobotListener;
            return true;
        }

        public static IDisinfectionRobotListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
