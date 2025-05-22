package com.pudutech.bumblebee.robot.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface IDeliveryRobotListener extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static class Default implements IDeliveryRobotListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
        public void onPalletState(List<Pallet> list) throws RemoteException {
        }

        @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
        public void onQRScanMsg(String str) throws RemoteException {
        }
    }

    void onPalletState(List<Pallet> list) throws RemoteException;

    void onQRScanMsg(String str) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDeliveryRobotListener {
        private static final String DESCRIPTOR = "com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener";
        static final int TRANSACTION_onPalletState = 1;
        static final int TRANSACTION_onQRScanMsg = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeliveryRobotListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDeliveryRobotListener)) {
                return (IDeliveryRobotListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPalletState(parcel.createTypedArrayList(Pallet.INSTANCE));
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onQRScanMsg(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes2.dex
         */
        /* loaded from: classes.dex */
        public static class Proxy implements IDeliveryRobotListener {
            public static IDeliveryRobotListener sDefaultImpl;
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

            @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
            public void onPalletState(List<Pallet> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPalletState(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.bumblebee.robot.aidl.IDeliveryRobotListener
            public void onQRScanMsg(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQRScanMsg(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeliveryRobotListener iDeliveryRobotListener) {
            if (Proxy.sDefaultImpl != null || iDeliveryRobotListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDeliveryRobotListener;
            return true;
        }

        public static IDeliveryRobotListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
