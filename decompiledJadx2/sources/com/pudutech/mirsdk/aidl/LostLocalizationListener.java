package com.pudutech.mirsdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;

/* loaded from: classes4.dex */
public interface LostLocalizationListener extends IInterface {
    void LostLocalization(LocalizationStatus localizationStatus) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements LostLocalizationListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.LostLocalizationListener";
        static final int TRANSACTION_LostLocalization = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static LostLocalizationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof LostLocalizationListener)) {
                return (LostLocalizationListener) queryLocalInterface;
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
            LostLocalization(parcel.readInt() != 0 ? LocalizationStatus.INSTANCE.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements LostLocalizationListener {
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

            @Override // com.pudutech.mirsdk.aidl.LostLocalizationListener
            public void LostLocalization(LocalizationStatus localizationStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (localizationStatus != null) {
                        obtain.writeInt(1);
                        localizationStatus.writeToParcel(obtain, 0);
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
