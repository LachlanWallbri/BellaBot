package com.pudutech.mirsdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.OnChildrenDetectedListener;

/* loaded from: classes4.dex */
public interface IChildrenDetectService extends IInterface {
    void addChildrenDetectListener(String str, OnChildrenDetectedListener onChildrenDetectedListener) throws RemoteException;

    void removeChildrenDetectListener(String str) throws RemoteException;

    int startDetect() throws RemoteException;

    int stopDetect() throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IChildrenDetectService {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.IChildrenDetectService";
        static final int TRANSACTION_addChildrenDetectListener = 3;
        static final int TRANSACTION_removeChildrenDetectListener = 4;
        static final int TRANSACTION_startDetect = 1;
        static final int TRANSACTION_stopDetect = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IChildrenDetectService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IChildrenDetectService)) {
                return (IChildrenDetectService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int startDetect = startDetect();
                parcel2.writeNoException();
                parcel2.writeInt(startDetect);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int stopDetect = stopDetect();
                parcel2.writeNoException();
                parcel2.writeInt(stopDetect);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                addChildrenDetectListener(parcel.readString(), OnChildrenDetectedListener.Stub.asInterface(parcel.readStrongBinder()));
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
            removeChildrenDetectListener(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements IChildrenDetectService {
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

            @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
            public int startDetect() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
            public int stopDetect() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
            public void addChildrenDetectListener(String str, OnChildrenDetectedListener onChildrenDetectedListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(onChildrenDetectedListener != null ? onChildrenDetectedListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
            public void removeChildrenDetectListener(String str) throws RemoteException {
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
