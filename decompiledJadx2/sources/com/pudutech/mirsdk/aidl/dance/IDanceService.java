package com.pudutech.mirsdk.aidl.dance;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.dance.DanceCallback;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceType;
import java.util.List;

/* loaded from: classes4.dex */
public interface IDanceService extends IInterface {
    void addDanceCallback(String str, DanceCallback danceCallback) throws RemoteException;

    void removeDanceCallback(String str) throws RemoteException;

    int startDancing(List<Dance> list, DanceType danceType) throws RemoteException;

    int stopDancing() throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IDanceService {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.dance.IDanceService";
        static final int TRANSACTION_addDanceCallback = 3;
        static final int TRANSACTION_removeDanceCallback = 4;
        static final int TRANSACTION_startDancing = 1;
        static final int TRANSACTION_stopDancing = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDanceService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDanceService)) {
                return (IDanceService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int startDancing = startDancing(parcel.createTypedArrayList(Dance.INSTANCE), parcel.readInt() != 0 ? DanceType.INSTANCE.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(startDancing);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int stopDancing = stopDancing();
                parcel2.writeNoException();
                parcel2.writeInt(stopDancing);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                addDanceCallback(parcel.readString(), DanceCallback.Stub.asInterface(parcel.readStrongBinder()));
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
            removeDanceCallback(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements IDanceService {
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

            @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
            public int startDancing(List<Dance> list, DanceType danceType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (danceType != null) {
                        obtain.writeInt(1);
                        danceType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
            public int stopDancing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
            public void addDanceCallback(String str, DanceCallback danceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(danceCallback != null ? danceCallback.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.dance.IDanceService
            public void removeDanceCallback(String str) throws RemoteException {
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
        }
    }
}
