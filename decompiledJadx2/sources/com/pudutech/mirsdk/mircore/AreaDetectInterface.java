package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.aidl.ElevatorZoneListener;
import com.pudutech.mirsdk.mircore.aidl.ExemptZoneListener;
import com.pudutech.mirsdk.mircore.aidl.NoDetourListener;
import com.pudutech.mirsdk.mircore.aidl.RGBDFunLimitListener;
import com.pudutech.mirsdk.mircore.aidl.SpeedLimitListener;
import java.util.List;

/* loaded from: classes6.dex */
public interface AreaDetectInterface extends IInterface {
    void addElevatorZoneListener(String str, ElevatorZoneListener elevatorZoneListener) throws RemoteException;

    void addExemptZoneListener(String str, ExemptZoneListener exemptZoneListener) throws RemoteException;

    void addNoDetourListener(String str, NoDetourListener noDetourListener) throws RemoteException;

    void addRGBDFunLimitListener(String str, RGBDFunLimitListener rGBDFunLimitListener) throws RemoteException;

    void addSpeedLimitListener(String str, SpeedLimitListener speedLimitListener) throws RemoteException;

    boolean getInsideStatus(Vector3d vector3d, int i) throws RemoteException;

    void receiveAreaData(int i, List<Vector2d> list, String str, double d, int i2) throws RemoteException;

    void removeElevatorZoneListener(String str) throws RemoteException;

    void removeExemptZoneListener(String str) throws RemoteException;

    void removeNoDetourListener(String str) throws RemoteException;

    void removeRGBDFunLimitListener(String str) throws RemoteException;

    void removeSpeedLimitListener(String str) throws RemoteException;

    boolean setMap(String str) throws RemoteException;

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements AreaDetectInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.AreaDetectInterface";
        static final int TRANSACTION_addElevatorZoneListener = 9;
        static final int TRANSACTION_addExemptZoneListener = 11;
        static final int TRANSACTION_addNoDetourListener = 5;
        static final int TRANSACTION_addRGBDFunLimitListener = 7;
        static final int TRANSACTION_addSpeedLimitListener = 3;
        static final int TRANSACTION_getInsideStatus = 13;
        static final int TRANSACTION_receiveAreaData = 2;
        static final int TRANSACTION_removeElevatorZoneListener = 10;
        static final int TRANSACTION_removeExemptZoneListener = 12;
        static final int TRANSACTION_removeNoDetourListener = 6;
        static final int TRANSACTION_removeRGBDFunLimitListener = 8;
        static final int TRANSACTION_removeSpeedLimitListener = 4;
        static final int TRANSACTION_setMap = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static AreaDetectInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AreaDetectInterface)) {
                return (AreaDetectInterface) queryLocalInterface;
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
                    boolean map = setMap(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(map ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    receiveAreaData(parcel.readInt(), parcel.createTypedArrayList(Vector2d.INSTANCE), parcel.readString(), parcel.readDouble(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSpeedLimitListener(parcel.readString(), SpeedLimitListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeSpeedLimitListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addNoDetourListener(parcel.readString(), NoDetourListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeNoDetourListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    addRGBDFunLimitListener(parcel.readString(), RGBDFunLimitListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeRGBDFunLimitListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    addElevatorZoneListener(parcel.readString(), ElevatorZoneListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeElevatorZoneListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    addExemptZoneListener(parcel.readString(), ExemptZoneListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeExemptZoneListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean insideStatus = getInsideStatus(parcel.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(insideStatus ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes6.dex */
        private static class Proxy implements AreaDetectInterface {
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

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public boolean setMap(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void receiveAreaData(int i, List<Vector2d> list, String str, double d, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    obtain.writeString(str);
                    obtain.writeDouble(d);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void addSpeedLimitListener(String str, SpeedLimitListener speedLimitListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(speedLimitListener != null ? speedLimitListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void removeSpeedLimitListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void addNoDetourListener(String str, NoDetourListener noDetourListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(noDetourListener != null ? noDetourListener.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void removeNoDetourListener(String str) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void addRGBDFunLimitListener(String str, RGBDFunLimitListener rGBDFunLimitListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(rGBDFunLimitListener != null ? rGBDFunLimitListener.asBinder() : null);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void removeRGBDFunLimitListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void addElevatorZoneListener(String str, ElevatorZoneListener elevatorZoneListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(elevatorZoneListener != null ? elevatorZoneListener.asBinder() : null);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void removeElevatorZoneListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void addExemptZoneListener(String str, ExemptZoneListener exemptZoneListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(exemptZoneListener != null ? exemptZoneListener.asBinder() : null);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public void removeExemptZoneListener(String str) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.AreaDetectInterface
            public boolean getInsideStatus(Vector3d vector3d, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vector3d != null) {
                        obtain.writeInt(1);
                        vector3d.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
