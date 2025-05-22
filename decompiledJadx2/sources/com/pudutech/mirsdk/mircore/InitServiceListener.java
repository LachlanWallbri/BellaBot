package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public interface InitServiceListener extends IInterface {
    void initCoreServiceState(CoreInitStep coreInitStep, CoreInitState coreInitState, String str) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements InitServiceListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.InitServiceListener";
        static final int TRANSACTION_initCoreServiceState = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static InitServiceListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof InitServiceListener)) {
                return (InitServiceListener) queryLocalInterface;
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
            initCoreServiceState(parcel.readInt() != 0 ? CoreInitStep.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0 ? CoreInitState.INSTANCE.createFromParcel(parcel) : null, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes6.dex */
        private static class Proxy implements InitServiceListener {
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

            @Override // com.pudutech.mirsdk.mircore.InitServiceListener
            public void initCoreServiceState(CoreInitStep coreInitStep, CoreInitState coreInitState, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (coreInitStep != null) {
                        obtain.writeInt(1);
                        coreInitStep.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (coreInitState != null) {
                        obtain.writeInt(1);
                        coreInitState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
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
