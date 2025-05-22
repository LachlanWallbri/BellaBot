package com.pudutech.mirsdk.aidl.mapify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;

/* loaded from: classes4.dex */
public interface LocalizationListener extends IInterface {
    void updateRobotPosition(Vector3d vector3d) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements LocalizationListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.mapify.LocalizationListener";
        static final int TRANSACTION_updateRobotPosition = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static LocalizationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof LocalizationListener)) {
                return (LocalizationListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            updateRobotPosition(parcel.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements LocalizationListener {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.LocalizationListener
            public void updateRobotPosition(Vector3d vector3d) throws RemoteException {
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
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
