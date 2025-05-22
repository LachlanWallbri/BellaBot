package com.pudutech.mirsdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface MapAreaDetectionListener extends IInterface {
    void OnElevatorZoneListener(boolean z) throws RemoteException;

    void OnNoDetourListener(boolean z, double d) throws RemoteException;

    void OnRGBDFunLimitListener(boolean z, int i) throws RemoteException;

    void OnSpeedLimitListener(boolean z, double d) throws RemoteException;

    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements MapAreaDetectionListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.MapAreaDetectionListener";
        static final int TRANSACTION_OnElevatorZoneListener = 4;
        static final int TRANSACTION_OnNoDetourListener = 2;
        static final int TRANSACTION_OnRGBDFunLimitListener = 3;
        static final int TRANSACTION_OnSpeedLimitListener = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MapAreaDetectionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MapAreaDetectionListener)) {
                return (MapAreaDetectionListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                OnSpeedLimitListener(parcel.readInt() != 0, parcel.readDouble());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                OnNoDetourListener(parcel.readInt() != 0, parcel.readDouble());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                OnRGBDFunLimitListener(parcel.readInt() != 0, parcel.readInt());
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
            OnElevatorZoneListener(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes5.dex */
        private static class Proxy implements MapAreaDetectionListener {
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

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnSpeedLimitListener(boolean z, double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeDouble(d);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnNoDetourListener(boolean z, double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeDouble(d);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnRGBDFunLimitListener(boolean z, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MapAreaDetectionListener
            public void OnElevatorZoneListener(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
