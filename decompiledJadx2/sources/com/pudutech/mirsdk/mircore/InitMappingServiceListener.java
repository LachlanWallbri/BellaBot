package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;

/* loaded from: classes4.dex */
public interface InitMappingServiceListener extends IInterface {
    void initMappingCoreServiceState(MappingCoreInitStep mappingCoreInitStep, MappingCoreInitState mappingCoreInitState, String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements InitMappingServiceListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.InitMappingServiceListener";
        static final int TRANSACTION_initMappingCoreServiceState = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static InitMappingServiceListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof InitMappingServiceListener)) {
                return (InitMappingServiceListener) queryLocalInterface;
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
            initMappingCoreServiceState(parcel.readInt() != 0 ? MappingCoreInitStep.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MappingCoreInitState.INSTANCE.createFromParcel(parcel) : null, parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements InitMappingServiceListener {
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

            @Override // com.pudutech.mirsdk.mircore.InitMappingServiceListener
            public void initMappingCoreServiceState(MappingCoreInitStep mappingCoreInitStep, MappingCoreInitState mappingCoreInitState, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mappingCoreInitStep != null) {
                        obtain.writeInt(1);
                        mappingCoreInitStep.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (mappingCoreInitState != null) {
                        obtain.writeInt(1);
                        mappingCoreInitState.writeToParcel(obtain, 0);
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
