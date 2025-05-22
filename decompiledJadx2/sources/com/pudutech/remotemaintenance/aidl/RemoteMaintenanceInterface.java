package com.pudutech.remotemaintenance.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* loaded from: classes.dex */
public interface RemoteMaintenanceInterface extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements RemoteMaintenanceInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
        public RemoteMaintenanceInterface init(IRemoteMaintenanceListener iRemoteMaintenanceListener) throws RemoteException {
            return null;
        }

        @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
        public void setCoordinateOrientation(double d, double d2, double d3) throws RemoteException {
        }

        @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
        public void setMapPath(String str) throws RemoteException {
        }

        @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
        public void setPower(int i) throws RemoteException {
        }

        @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
        public void setSpeed(double d) throws RemoteException {
        }

        @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
        public void setTask(String str) throws RemoteException {
        }
    }

    RemoteMaintenanceInterface init(IRemoteMaintenanceListener iRemoteMaintenanceListener) throws RemoteException;

    void setCoordinateOrientation(double d, double d2, double d3) throws RemoteException;

    void setMapPath(String str) throws RemoteException;

    void setPower(int i) throws RemoteException;

    void setSpeed(double d) throws RemoteException;

    void setTask(String str) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements RemoteMaintenanceInterface {
        private static final String DESCRIPTOR = "com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface";
        static final int TRANSACTION_init = 1;
        static final int TRANSACTION_setCoordinateOrientation = 5;
        static final int TRANSACTION_setMapPath = 6;
        static final int TRANSACTION_setPower = 2;
        static final int TRANSACTION_setSpeed = 3;
        static final int TRANSACTION_setTask = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RemoteMaintenanceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof RemoteMaintenanceInterface)) {
                return (RemoteMaintenanceInterface) queryLocalInterface;
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
                    RemoteMaintenanceInterface init = init(IRemoteMaintenanceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(init != null ? init.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPower(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSpeed(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTask(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCoordinateOrientation(parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMapPath(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes5.dex
          classes6.dex
         */
        /* loaded from: classes.dex */
        public static class Proxy implements RemoteMaintenanceInterface {
            public static RemoteMaintenanceInterface sDefaultImpl;
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

            @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public RemoteMaintenanceInterface init(IRemoteMaintenanceListener iRemoteMaintenanceListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemoteMaintenanceListener != null ? iRemoteMaintenanceListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().init(iRemoteMaintenanceListener);
                    }
                    obtain2.readException();
                    return Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setPower(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPower(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setSpeed(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSpeed(d);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setTask(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTask(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setCoordinateOrientation(double d, double d2, double d3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeDouble(d3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCoordinateOrientation(d, d2, d3);
                        obtain2.recycle();
                        obtain.recycle();
                    } else {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setMapPath(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMapPath(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(RemoteMaintenanceInterface remoteMaintenanceInterface) {
            if (Proxy.sDefaultImpl != null || remoteMaintenanceInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = remoteMaintenanceInterface;
            return true;
        }

        public static RemoteMaintenanceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
