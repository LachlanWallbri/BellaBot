package com.pudutech.mirsdk.aidl.mapify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.serialize.MapStepType;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;

/* loaded from: classes4.dex */
public interface MapingModuleListener extends IInterface {
    void mapingInitStepResult(MapStepType mapStepType, MappingCoreInitState mappingCoreInitState) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements MapingModuleListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.mapify.MapingModuleListener";
        static final int TRANSACTION_mapingInitStepResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MapingModuleListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MapingModuleListener)) {
                return (MapingModuleListener) queryLocalInterface;
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
            mapingInitStepResult(parcel.readInt() != 0 ? MapStepType.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MappingCoreInitState.INSTANCE.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements MapingModuleListener {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.MapingModuleListener
            public void mapingInitStepResult(MapStepType mapStepType, MappingCoreInitState mappingCoreInitState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (mapStepType != null) {
                        obtain.writeInt(1);
                        mapStepType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (mappingCoreInitState != null) {
                        obtain.writeInt(1);
                        mappingCoreInitState.writeToParcel(obtain, 0);
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
