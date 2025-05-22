package com.pudutech.mirsdk.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.IUsbControlListener;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface UsbControlInterface extends IInterface {
    void addUSBControlListener(String str, IUsbControlListener iUsbControlListener) throws RemoteException;

    int getBacklight() throws RemoteException;

    void removeUSBControlListener(String str) throws RemoteException;

    int sendUsbData(byte[] bArr) throws RemoteException;

    int setBacklight(int i) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements UsbControlInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.hardware.UsbControlInterface";
        static final int TRANSACTION_addUSBControlListener = 4;
        static final int TRANSACTION_getBacklight = 3;
        static final int TRANSACTION_removeUSBControlListener = 5;
        static final int TRANSACTION_sendUsbData = 2;
        static final int TRANSACTION_setBacklight = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static UsbControlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof UsbControlInterface)) {
                return (UsbControlInterface) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int backlight = setBacklight(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(backlight);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int sendUsbData = sendUsbData(parcel.createByteArray());
                parcel2.writeNoException();
                parcel2.writeInt(sendUsbData);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                int backlight2 = getBacklight();
                parcel2.writeNoException();
                parcel2.writeInt(backlight2);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                addUSBControlListener(parcel.readString(), IUsbControlListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            removeUSBControlListener(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
          classes5.dex
         */
        /* loaded from: classes.dex */
        private static class Proxy implements UsbControlInterface {
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

            @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
            public int setBacklight(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
            public int sendUsbData(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
            public int getBacklight() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
            public void addUSBControlListener(String str, IUsbControlListener iUsbControlListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iUsbControlListener != null ? iUsbControlListener.asBinder() : null);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.hardware.UsbControlInterface
            public void removeUSBControlListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
