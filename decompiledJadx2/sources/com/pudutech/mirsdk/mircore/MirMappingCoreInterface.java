package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.InitMappingServiceListener;
import com.pudutech.mirsdk.mircore.coreparcel.CruisePath;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoPath;
import java.util.List;

/* loaded from: classes4.dex */
public interface MirMappingCoreInterface extends IInterface {
    void addMappingSensorListener() throws RemoteException;

    void cancelMapping() throws RemoteException;

    boolean checkBeginMappingMarkerVisible() throws RemoteException;

    boolean checkFinishMappingMarkerVisible() throws RemoteException;

    boolean checkMapLegal(String str) throws RemoteException;

    boolean checkMapLimit(int i) throws RemoteException;

    boolean[] checkVirtualWall(List<Vector3d> list) throws RemoteException;

    void finishMapping() throws RemoteException;

    CruisePath getCruisePath(List<Vector3d> list) throws RemoteException;

    int getDetectMarkerId() throws RemoteException;

    ParcelFileDescriptor getFinalMapDataUI() throws RemoteException;

    String getLoclizationMap() throws RemoteException;

    ParcelFileDescriptor getMapDataUI() throws RemoteException;

    boolean getMappingOptStatus() throws RemoteException;

    Vector3d getRobotPose() throws RemoteException;

    TopoPath getTopoPath(List<Vector3d> list, List<Destination> list2) throws RemoteException;

    void initExtendMapping(byte[] bArr, byte[] bArr2, byte[] bArr3, InitMappingServiceListener initMappingServiceListener) throws RemoteException;

    void initGenTopoPath(TopoPath topoPath, byte[] bArr, byte[] bArr2) throws RemoteException;

    void initModules(InitMappingServiceListener initMappingServiceListener) throws RemoteException;

    void reInitModules(InitMappingServiceListener initMappingServiceListener) throws RemoteException;

    void removeMappingSensorListener() throws RemoteException;

    TopoPath resetDualPath(int[] iArr) throws RemoteException;

    void startMapping() throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements MirMappingCoreInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.MirMappingCoreInterface";
        static final int TRANSACTION_addMappingSensorListener = 4;
        static final int TRANSACTION_cancelMapping = 14;
        static final int TRANSACTION_checkBeginMappingMarkerVisible = 7;
        static final int TRANSACTION_checkFinishMappingMarkerVisible = 13;
        static final int TRANSACTION_checkMapLegal = 6;
        static final int TRANSACTION_checkMapLimit = 11;
        static final int TRANSACTION_checkVirtualWall = 22;
        static final int TRANSACTION_finishMapping = 15;
        static final int TRANSACTION_getCruisePath = 23;
        static final int TRANSACTION_getDetectMarkerId = 9;
        static final int TRANSACTION_getFinalMapDataUI = 17;
        static final int TRANSACTION_getLoclizationMap = 18;
        static final int TRANSACTION_getMapDataUI = 12;
        static final int TRANSACTION_getMappingOptStatus = 16;
        static final int TRANSACTION_getRobotPose = 10;
        static final int TRANSACTION_getTopoPath = 20;
        static final int TRANSACTION_initExtendMapping = 3;
        static final int TRANSACTION_initGenTopoPath = 19;
        static final int TRANSACTION_initModules = 1;
        static final int TRANSACTION_reInitModules = 2;
        static final int TRANSACTION_removeMappingSensorListener = 5;
        static final int TRANSACTION_resetDualPath = 21;
        static final int TRANSACTION_startMapping = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MirMappingCoreInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MirMappingCoreInterface)) {
                return (MirMappingCoreInterface) queryLocalInterface;
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
                    initModules(InitMappingServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    reInitModules(InitMappingServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    initExtendMapping(parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray(), InitMappingServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMappingSensorListener();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeMappingSensorListener();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkMapLegal = checkMapLegal(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkMapLegal ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkBeginMappingMarkerVisible = checkBeginMappingMarkerVisible();
                    parcel2.writeNoException();
                    parcel2.writeInt(checkBeginMappingMarkerVisible ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    startMapping();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int detectMarkerId = getDetectMarkerId();
                    parcel2.writeNoException();
                    parcel2.writeInt(detectMarkerId);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    Vector3d robotPose = getRobotPose();
                    parcel2.writeNoException();
                    if (robotPose != null) {
                        parcel2.writeInt(1);
                        robotPose.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkMapLimit = checkMapLimit(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkMapLimit ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor mapDataUI = getMapDataUI();
                    parcel2.writeNoException();
                    if (mapDataUI != null) {
                        parcel2.writeInt(1);
                        mapDataUI.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkFinishMappingMarkerVisible = checkFinishMappingMarkerVisible();
                    parcel2.writeNoException();
                    parcel2.writeInt(checkFinishMappingMarkerVisible ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelMapping();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishMapping();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean mappingOptStatus = getMappingOptStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(mappingOptStatus ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor finalMapDataUI = getFinalMapDataUI();
                    parcel2.writeNoException();
                    if (finalMapDataUI != null) {
                        parcel2.writeInt(1);
                        finalMapDataUI.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    String loclizationMap = getLoclizationMap();
                    parcel2.writeNoException();
                    parcel2.writeString(loclizationMap);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    initGenTopoPath(parcel.readInt() != 0 ? TopoPath.INSTANCE.createFromParcel(parcel) : null, parcel.createByteArray(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    TopoPath topoPath = getTopoPath(parcel.createTypedArrayList(Vector3d.INSTANCE), parcel.createTypedArrayList(Destination.INSTANCE));
                    parcel2.writeNoException();
                    if (topoPath != null) {
                        parcel2.writeInt(1);
                        topoPath.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    TopoPath resetDualPath = resetDualPath(parcel.createIntArray());
                    parcel2.writeNoException();
                    if (resetDualPath != null) {
                        parcel2.writeInt(1);
                        resetDualPath.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean[] checkVirtualWall = checkVirtualWall(parcel.createTypedArrayList(Vector3d.INSTANCE));
                    parcel2.writeNoException();
                    parcel2.writeBooleanArray(checkVirtualWall);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    CruisePath cruisePath = getCruisePath(parcel.createTypedArrayList(Vector3d.INSTANCE));
                    parcel2.writeNoException();
                    if (cruisePath != null) {
                        parcel2.writeInt(1);
                        cruisePath.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements MirMappingCoreInterface {
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

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void initModules(InitMappingServiceListener initMappingServiceListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(initMappingServiceListener != null ? initMappingServiceListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void reInitModules(InitMappingServiceListener initMappingServiceListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(initMappingServiceListener != null ? initMappingServiceListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void initExtendMapping(byte[] bArr, byte[] bArr2, byte[] bArr3, InitMappingServiceListener initMappingServiceListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    obtain.writeByteArray(bArr3);
                    obtain.writeStrongBinder(initMappingServiceListener != null ? initMappingServiceListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void addMappingSensorListener() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void removeMappingSensorListener() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public boolean checkMapLegal(String str) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public boolean checkBeginMappingMarkerVisible() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void startMapping() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public int getDetectMarkerId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public Vector3d getRobotPose() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public boolean checkMapLimit(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public ParcelFileDescriptor getMapDataUI() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public boolean checkFinishMappingMarkerVisible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void cancelMapping() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void finishMapping() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public boolean getMappingOptStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public ParcelFileDescriptor getFinalMapDataUI() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public String getLoclizationMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public void initGenTopoPath(TopoPath topoPath, byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (topoPath != null) {
                        obtain.writeInt(1);
                        topoPath.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public TopoPath getTopoPath(List<Vector3d> list, List<Destination> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? TopoPath.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public TopoPath resetDualPath(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? TopoPath.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public boolean[] checkVirtualWall(List<Vector3d> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createBooleanArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
            public CruisePath getCruisePath(List<Vector3d> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? CruisePath.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
