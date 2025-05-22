package com.pudutech.mirsdk.aidl.mapify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.MapingModuleListener;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import java.util.List;

/* loaded from: classes4.dex */
public interface LocateMappingInterface extends IInterface {
    void addLocalizationListener(String str, LocalizationListener localizationListener) throws RemoteException;

    void addMapingModuleListener(String str, MapingModuleListener mapingModuleListener) throws RemoteException;

    void cancelMapping() throws RemoteException;

    boolean checkBeginMappingMarkerVisible() throws RemoteException;

    boolean checkFinishMappingMarkerVisible() throws RemoteException;

    boolean checkMappingOpt() throws RemoteException;

    boolean checkMemoryLimit(int i) throws RemoteException;

    void deleteSavedMap(String str) throws RemoteException;

    void finishMapping() throws RemoteException;

    List<MapInfo> getMapInfoList() throws RemoteException;

    Destination getMapInitPoint(String str) throws RemoteException;

    ParcelFileDescriptor getOptimizedMap() throws RemoteException;

    ParcelFileDescriptor getRealMapData() throws RemoteException;

    String getTempMapRoot() throws RemoteException;

    void initExtendDrawing() throws RemoteException;

    void initExtendFlag() throws RemoteException;

    void reInitModules() throws RemoteException;

    void removeLocalizationListener(String str) throws RemoteException;

    void removeMapingModuleListener(String str) throws RemoteException;

    void saveOptimizedMap(String str, Destination destination) throws RemoteException;

    void startMapping() throws RemoteException;

    void updateMapVersion(String str, int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements LocateMappingInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface";
        static final int TRANSACTION_addLocalizationListener = 13;
        static final int TRANSACTION_addMapingModuleListener = 19;
        static final int TRANSACTION_cancelMapping = 6;
        static final int TRANSACTION_checkBeginMappingMarkerVisible = 1;
        static final int TRANSACTION_checkFinishMappingMarkerVisible = 2;
        static final int TRANSACTION_checkMappingOpt = 7;
        static final int TRANSACTION_checkMemoryLimit = 8;
        static final int TRANSACTION_deleteSavedMap = 11;
        static final int TRANSACTION_finishMapping = 5;
        static final int TRANSACTION_getMapInfoList = 21;
        static final int TRANSACTION_getMapInitPoint = 15;
        static final int TRANSACTION_getOptimizedMap = 9;
        static final int TRANSACTION_getRealMapData = 10;
        static final int TRANSACTION_getTempMapRoot = 14;
        static final int TRANSACTION_initExtendDrawing = 16;
        static final int TRANSACTION_initExtendFlag = 17;
        static final int TRANSACTION_reInitModules = 3;
        static final int TRANSACTION_removeLocalizationListener = 18;
        static final int TRANSACTION_removeMapingModuleListener = 20;
        static final int TRANSACTION_saveOptimizedMap = 12;
        static final int TRANSACTION_startMapping = 4;
        static final int TRANSACTION_updateMapVersion = 22;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static LocateMappingInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof LocateMappingInterface)) {
                return (LocateMappingInterface) queryLocalInterface;
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
                    boolean checkBeginMappingMarkerVisible = checkBeginMappingMarkerVisible();
                    parcel2.writeNoException();
                    parcel2.writeInt(checkBeginMappingMarkerVisible ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkFinishMappingMarkerVisible = checkFinishMappingMarkerVisible();
                    parcel2.writeNoException();
                    parcel2.writeInt(checkFinishMappingMarkerVisible ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    reInitModules();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    startMapping();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishMapping();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelMapping();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkMappingOpt = checkMappingOpt();
                    parcel2.writeNoException();
                    parcel2.writeInt(checkMappingOpt ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkMemoryLimit = checkMemoryLimit(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkMemoryLimit ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor optimizedMap = getOptimizedMap();
                    parcel2.writeNoException();
                    if (optimizedMap != null) {
                        parcel2.writeInt(1);
                        optimizedMap.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor realMapData = getRealMapData();
                    parcel2.writeNoException();
                    if (realMapData != null) {
                        parcel2.writeInt(1);
                        realMapData.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteSavedMap(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveOptimizedMap(parcel.readString(), parcel.readInt() != 0 ? Destination.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    addLocalizationListener(parcel.readString(), LocalizationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String tempMapRoot = getTempMapRoot();
                    parcel2.writeNoException();
                    parcel2.writeString(tempMapRoot);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    Destination mapInitPoint = getMapInitPoint(parcel.readString());
                    parcel2.writeNoException();
                    if (mapInitPoint != null) {
                        parcel2.writeInt(1);
                        mapInitPoint.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    initExtendDrawing();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    initExtendFlag();
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeLocalizationListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMapingModuleListener(parcel.readString(), MapingModuleListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeMapingModuleListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<MapInfo> mapInfoList = getMapInfoList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(mapInfoList);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMapVersion(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements LocateMappingInterface {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public boolean checkBeginMappingMarkerVisible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public boolean checkFinishMappingMarkerVisible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void reInitModules() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void startMapping() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void finishMapping() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void cancelMapping() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public boolean checkMappingOpt() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public boolean checkMemoryLimit(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public ParcelFileDescriptor getOptimizedMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public ParcelFileDescriptor getRealMapData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void deleteSavedMap(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void saveOptimizedMap(String str, Destination destination) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (destination != null) {
                        obtain.writeInt(1);
                        destination.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void addLocalizationListener(String str, LocalizationListener localizationListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(localizationListener != null ? localizationListener.asBinder() : null);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public String getTempMapRoot() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public Destination getMapInitPoint(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Destination.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void initExtendDrawing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void initExtendFlag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void removeLocalizationListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void addMapingModuleListener(String str, MapingModuleListener mapingModuleListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(mapingModuleListener != null ? mapingModuleListener.asBinder() : null);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void removeMapingModuleListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public List<MapInfo> getMapInfoList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(MapInfo.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface
            public void updateMapVersion(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
