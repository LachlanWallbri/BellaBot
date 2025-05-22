package com.pudutech.remotemaintenance.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* loaded from: classes.dex */
public interface IRemoteMaintenanceListener extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements IRemoteMaintenanceListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
        public void onConnectFailure(int i, String str) throws RemoteException {
        }

        @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
        public void onConnected() throws RemoteException {
        }

        @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
        public void onConnecting() throws RemoteException {
        }
    }

    void onConnectFailure(int i, String str) throws RemoteException;

    void onConnected() throws RemoteException;

    void onConnecting() throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRemoteMaintenanceListener {
        private static final String DESCRIPTOR = "com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener";
        static final int TRANSACTION_onConnectFailure = 3;
        static final int TRANSACTION_onConnected = 2;
        static final int TRANSACTION_onConnecting = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteMaintenanceListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteMaintenanceListener)) {
                return (IRemoteMaintenanceListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onConnecting();
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onConnected();
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onConnectFailure(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes5.dex
          classes6.dex
         */
        /* loaded from: classes.dex */
        public static class Proxy implements IRemoteMaintenanceListener {
            public static IRemoteMaintenanceListener sDefaultImpl;
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

            @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
            public void onConnecting() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnecting();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
            public void onConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnected();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener
            public void onConnectFailure(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnectFailure(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteMaintenanceListener iRemoteMaintenanceListener) {
            if (Proxy.sDefaultImpl != null || iRemoteMaintenanceListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteMaintenanceListener;
            return true;
        }

        public static IRemoteMaintenanceListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
