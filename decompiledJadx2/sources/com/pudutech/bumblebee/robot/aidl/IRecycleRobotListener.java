package com.pudutech.bumblebee.robot.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface IRecycleRobotListener extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements IRecycleRobotListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
        public void onNFCSignDetected(String str) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
        public void onRemoteDeviceMsg(byte[] bArr) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
        public void onRemoteDeviceResponseChecking(int i) throws RemoteException {
        }
    }

    void onNFCSignDetected(String str) throws RemoteException;

    void onRemoteDeviceMsg(byte[] bArr) throws RemoteException;

    void onRemoteDeviceResponseChecking(int i) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRecycleRobotListener {
        private static final String DESCRIPTOR = "com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener";
        static final int TRANSACTION_onNFCSignDetected = 1;
        static final int TRANSACTION_onRemoteDeviceMsg = 2;
        static final int TRANSACTION_onRemoteDeviceResponseChecking = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecycleRobotListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRecycleRobotListener)) {
                return (IRecycleRobotListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onNFCSignDetected(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onRemoteDeviceMsg(parcel.createByteArray());
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
            onRemoteDeviceResponseChecking(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
         */
        /* loaded from: classes.dex */
        public static class Proxy implements IRecycleRobotListener {
            public static IRecycleRobotListener sDefaultImpl;
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

            @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
            public void onNFCSignDetected(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNFCSignDetected(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
            public void onRemoteDeviceMsg(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoteDeviceMsg(bArr);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener
            public void onRemoteDeviceResponseChecking(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoteDeviceResponseChecking(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecycleRobotListener iRecycleRobotListener) {
            if (Proxy.sDefaultImpl != null || iRecycleRobotListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRecycleRobotListener;
            return true;
        }

        public static IRecycleRobotListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
