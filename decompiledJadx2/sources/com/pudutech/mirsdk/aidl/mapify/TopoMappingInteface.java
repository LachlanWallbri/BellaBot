package com.pudutech.mirsdk.aidl.mapify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.CruiseTracks;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import java.util.List;

/* loaded from: classes4.dex */
public interface TopoMappingInteface extends IInterface {
    List<Destination> analyMap(String str) throws RemoteException;

    boolean[] checkVirtualWall(List<Vector3d> list) throws RemoteException;

    void clearOldData() throws RemoteException;

    List<Vector3d> createCruise(List<Vector3d> list, String str) throws RemoteException;

    void createScheduleConfig(List<Vector3d> list, List<Vector3d> list2) throws RemoteException;

    List<Destination> createTopomap(List<Vector3d> list, List<Destination> list2) throws RemoteException;

    CameraType getCamraType() throws RemoteException;

    List<CruiseTracks> getCruiseTracksList() throws RemoteException;

    List<DockerResult> getDockerChargeList() throws RemoteException;

    List<TopoTrack> getDoubleRoadTracks() throws RemoteException;

    LocateCase getMapLocateCase() throws RemoteException;

    List<TopoTrack> getTracks() throws RemoteException;

    List<Vector3d> getVirtualWallList() throws RemoteException;

    void saveChargeDockerList(List<DockerResult> list) throws RemoteException;

    void saveTopomap() throws RemoteException;

    void saveVirtualWall(List<Vector3d> list) throws RemoteException;

    void setTwoWayroad(int[] iArr) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements TopoMappingInteface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface";
        static final int TRANSACTION_analyMap = 9;
        static final int TRANSACTION_checkVirtualWall = 5;
        static final int TRANSACTION_clearOldData = 15;
        static final int TRANSACTION_createCruise = 3;
        static final int TRANSACTION_createScheduleConfig = 17;
        static final int TRANSACTION_createTopomap = 1;
        static final int TRANSACTION_getCamraType = 14;
        static final int TRANSACTION_getCruiseTracksList = 12;
        static final int TRANSACTION_getDockerChargeList = 11;
        static final int TRANSACTION_getDoubleRoadTracks = 10;
        static final int TRANSACTION_getMapLocateCase = 16;
        static final int TRANSACTION_getTracks = 2;
        static final int TRANSACTION_getVirtualWallList = 13;
        static final int TRANSACTION_saveChargeDockerList = 7;
        static final int TRANSACTION_saveTopomap = 4;
        static final int TRANSACTION_saveVirtualWall = 6;
        static final int TRANSACTION_setTwoWayroad = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static TopoMappingInteface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof TopoMappingInteface)) {
                return (TopoMappingInteface) queryLocalInterface;
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
                    List<Destination> createTopomap = createTopomap(parcel.createTypedArrayList(Vector3d.INSTANCE), parcel.createTypedArrayList(Destination.INSTANCE));
                    parcel2.writeNoException();
                    parcel2.writeTypedList(createTopomap);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<TopoTrack> tracks = getTracks();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(tracks);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Vector3d> createCruise = createCruise(parcel.createTypedArrayList(Vector3d.INSTANCE), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(createCruise);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveTopomap();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean[] checkVirtualWall = checkVirtualWall(parcel.createTypedArrayList(Vector3d.INSTANCE));
                    parcel2.writeNoException();
                    parcel2.writeBooleanArray(checkVirtualWall);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveVirtualWall(parcel.createTypedArrayList(Vector3d.INSTANCE));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveChargeDockerList(parcel.createTypedArrayList(DockerResult.INSTANCE));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTwoWayroad(parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Destination> analyMap = analyMap(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(analyMap);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<TopoTrack> doubleRoadTracks = getDoubleRoadTracks();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(doubleRoadTracks);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DockerResult> dockerChargeList = getDockerChargeList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(dockerChargeList);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CruiseTracks> cruiseTracksList = getCruiseTracksList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(cruiseTracksList);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Vector3d> virtualWallList = getVirtualWallList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(virtualWallList);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraType camraType = getCamraType();
                    parcel2.writeNoException();
                    if (camraType != null) {
                        parcel2.writeInt(1);
                        camraType.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearOldData();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    LocateCase mapLocateCase = getMapLocateCase();
                    parcel2.writeNoException();
                    if (mapLocateCase != null) {
                        parcel2.writeInt(1);
                        mapLocateCase.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    createScheduleConfig(parcel.createTypedArrayList(Vector3d.INSTANCE), parcel.createTypedArrayList(Vector3d.INSTANCE));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes4.dex */
        private static class Proxy implements TopoMappingInteface {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<Destination> createTopomap(List<Vector3d> list, List<Destination> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Destination.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<TopoTrack> getTracks() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(TopoTrack.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<Vector3d> createCruise(List<Vector3d> list, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Vector3d.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public void saveTopomap() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public boolean[] checkVirtualWall(List<Vector3d> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createBooleanArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public void saveVirtualWall(List<Vector3d> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public void saveChargeDockerList(List<DockerResult> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public void setTwoWayroad(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<Destination> analyMap(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Destination.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<TopoTrack> getDoubleRoadTracks() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(TopoTrack.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<DockerResult> getDockerChargeList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DockerResult.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<CruiseTracks> getCruiseTracksList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(CruiseTracks.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public List<Vector3d> getVirtualWallList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Vector3d.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public CameraType getCamraType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? CameraType.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public void clearOldData() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public LocateCase getMapLocateCase() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? LocateCase.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
            public void createScheduleConfig(List<Vector3d> list, List<Vector3d> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
