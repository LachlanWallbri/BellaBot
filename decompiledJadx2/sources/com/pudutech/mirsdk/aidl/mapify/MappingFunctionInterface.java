package com.pudutech.mirsdk.aidl.mapify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.mapify.HardwareExceptListener;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;

/* loaded from: classes4.dex */
public interface MappingFunctionInterface extends IInterface {
    void addHardWareListener(String str, HardwareExceptListener hardwareExceptListener) throws RemoteException;

    void addMappingSensorListener() throws RemoteException;

    boolean checkLegalMap(String str) throws RemoteException;

    void defaultResetRobot() throws RemoteException;

    LocateMappingInterface getLocatMappingInterface() throws RemoteException;

    TopoMappingInteface getTopoMappingInteface() throws RemoteException;

    void open() throws RemoteException;

    void removeHardwareListener(String str) throws RemoteException;

    void removeMappingSensorListener() throws RemoteException;

    boolean saveMapFromPlatform(String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements MappingFunctionInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface";
        static final int TRANSACTION_addHardWareListener = 9;
        static final int TRANSACTION_addMappingSensorListener = 7;
        static final int TRANSACTION_checkLegalMap = 5;
        static final int TRANSACTION_defaultResetRobot = 1;
        static final int TRANSACTION_getLocatMappingInterface = 3;
        static final int TRANSACTION_getTopoMappingInteface = 4;
        static final int TRANSACTION_open = 2;
        static final int TRANSACTION_removeHardwareListener = 10;
        static final int TRANSACTION_removeMappingSensorListener = 8;
        static final int TRANSACTION_saveMapFromPlatform = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MappingFunctionInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MappingFunctionInterface)) {
                return (MappingFunctionInterface) queryLocalInterface;
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
                    defaultResetRobot();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    open();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    LocateMappingInterface locatMappingInterface = getLocatMappingInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(locatMappingInterface != null ? locatMappingInterface.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    TopoMappingInteface topoMappingInteface = getTopoMappingInteface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(topoMappingInteface != null ? topoMappingInteface.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkLegalMap = checkLegalMap(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkLegalMap ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean saveMapFromPlatform = saveMapFromPlatform(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(saveMapFromPlatform ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMappingSensorListener();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeMappingSensorListener();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    addHardWareListener(parcel.readString(), HardwareExceptListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeHardwareListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements MappingFunctionInterface {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public void defaultResetRobot() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public void open() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public LocateMappingInterface getLocatMappingInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return LocateMappingInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public TopoMappingInteface getTopoMappingInteface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return TopoMappingInteface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public boolean checkLegalMap(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public boolean saveMapFromPlatform(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public void addMappingSensorListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public void removeMappingSensorListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public void addHardWareListener(String str, HardwareExceptListener hardwareExceptListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(hardwareExceptListener != null ? hardwareExceptListener.asBinder() : null);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface
            public void removeHardwareListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
