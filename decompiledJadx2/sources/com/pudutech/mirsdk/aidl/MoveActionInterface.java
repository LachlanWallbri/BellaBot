package com.pudutech.mirsdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.CliffDistanceStateListener;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.FillInStateListener;
import com.pudutech.mirsdk.aidl.MapAreaDetectionListener;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public interface MoveActionInterface extends IInterface {
    void addAccessDoorStateListener(String str, AccessDoorListener accessDoorListener) throws RemoteException;

    void addCliffDistanceStateListener(String str, CliffDistanceStateListener cliffDistanceStateListener) throws RemoteException;

    boolean addCoverAround(boolean z) throws RemoteException;

    void addElevetorRequestListener(String str, ElevatorRequestListener elevatorRequestListener) throws RemoteException;

    void addFillInStateListener(String str, FillInStateListener fillInStateListener) throws RemoteException;

    void addMapAreaDetectionListener(String str, MapAreaDetectionListener mapAreaDetectionListener) throws RemoteException;

    void clearDropEvent() throws RemoteException;

    List<DestinationWithAccRange> destinationsOrderWithRange(List<String> list, boolean z) throws RemoteException;

    void enableDynamicRoadblock(boolean z) throws RemoteException;

    void enableStuckReplan(boolean z) throws RemoteException;

    double getCallAccessDoorDistance() throws RemoteException;

    double getCallElevatorDistance() throws RemoteException;

    List<ChargingPileInfo> getChargingPileInfos() throws RemoteException;

    List<String> getChargingPiles() throws RemoteException;

    boolean getCoverAround() throws RemoteException;

    List<Destination> getDestinations() throws RemoteException;

    boolean getHeavyloadMode() throws RemoteException;

    int getRoadBlockTime() throws RemoteException;

    SmoothMode getSmoothRunAndStopMode() throws RemoteException;

    List<Destination> getSpecifyMapDestinations(String str) throws RemoteException;

    String getSpeedLevel(MoveMode moveMode) throws RemoteException;

    String[] getSpeedLevels() throws RemoteException;

    double getTrayDis() throws RemoteException;

    void goChargingPile(String str) throws RemoteException;

    void goCruisePath(int i, MoveTaskMode moveTaskMode, List<String> list) throws RemoteException;

    boolean goHome(String str, MoveTaskMode moveTaskMode) throws RemoteException;

    boolean goHomeFill(String str, MoveTaskMode moveTaskMode, boolean z) throws RemoteException;

    boolean goTo(String str, MoveTaskMode moveTaskMode) throws RemoteException;

    boolean isNearByChangePile() throws RemoteException;

    DestinationWithAccRange nearestDestination(List<String> list) throws RemoteException;

    void passAccessDoor() throws RemoteException;

    void pause() throws RemoteException;

    void quitFillIn() throws RemoteException;

    void removeAccessDoorStateListener(String str) throws RemoteException;

    void removeCliffDistanceStateListener(String str) throws RemoteException;

    void removeElevatorRequestListener(String str) throws RemoteException;

    void removeFillInStateListener(String str) throws RemoteException;

    void removeMapAreaDetectionListener(String str) throws RemoteException;

    void resume() throws RemoteException;

    void rotate(double d) throws RemoteException;

    void setCallAccessDoorDistance(double d) throws RemoteException;

    void setCallElevatorDistance(double d) throws RemoteException;

    void setHeavyloadMode(boolean z) throws RemoteException;

    boolean setReplanWaitTime(int i) throws RemoteException;

    boolean setRoadBlockTime(int i) throws RemoteException;

    void setSmoothRunAndStopMode(SmoothMode smoothMode) throws RemoteException;

    boolean swithSpeedLevel(MoveMode moveMode, String str) throws RemoteException;

    void taskStop() throws RemoteException;

    void updateTrayDis(double d) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements MoveActionInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.MoveActionInterface";
        static final int TRANSACTION_addAccessDoorStateListener = 32;
        static final int TRANSACTION_addCliffDistanceStateListener = 40;
        static final int TRANSACTION_addCoverAround = 28;
        static final int TRANSACTION_addElevetorRequestListener = 24;
        static final int TRANSACTION_addFillInStateListener = 20;
        static final int TRANSACTION_addMapAreaDetectionListener = 42;
        static final int TRANSACTION_clearDropEvent = 23;
        static final int TRANSACTION_destinationsOrderWithRange = 10;
        static final int TRANSACTION_enableDynamicRoadblock = 19;
        static final int TRANSACTION_enableStuckReplan = 18;
        static final int TRANSACTION_getCallAccessDoorDistance = 35;
        static final int TRANSACTION_getCallElevatorDistance = 27;
        static final int TRANSACTION_getChargingPileInfos = 44;
        static final int TRANSACTION_getChargingPiles = 36;
        static final int TRANSACTION_getCoverAround = 29;
        static final int TRANSACTION_getDestinations = 1;
        static final int TRANSACTION_getHeavyloadMode = 31;
        static final int TRANSACTION_getRoadBlockTime = 17;
        static final int TRANSACTION_getSmoothRunAndStopMode = 39;
        static final int TRANSACTION_getSpecifyMapDestinations = 2;
        static final int TRANSACTION_getSpeedLevel = 14;
        static final int TRANSACTION_getSpeedLevels = 13;
        static final int TRANSACTION_getTrayDis = 49;
        static final int TRANSACTION_goChargingPile = 37;
        static final int TRANSACTION_goCruisePath = 6;
        static final int TRANSACTION_goHome = 4;
        static final int TRANSACTION_goHomeFill = 5;
        static final int TRANSACTION_goTo = 3;
        static final int TRANSACTION_isNearByChangePile = 46;
        static final int TRANSACTION_nearestDestination = 11;
        static final int TRANSACTION_passAccessDoor = 47;
        static final int TRANSACTION_pause = 7;
        static final int TRANSACTION_quitFillIn = 22;
        static final int TRANSACTION_removeAccessDoorStateListener = 33;
        static final int TRANSACTION_removeCliffDistanceStateListener = 41;
        static final int TRANSACTION_removeElevatorRequestListener = 25;
        static final int TRANSACTION_removeFillInStateListener = 21;
        static final int TRANSACTION_removeMapAreaDetectionListener = 43;
        static final int TRANSACTION_resume = 8;
        static final int TRANSACTION_rotate = 9;
        static final int TRANSACTION_setCallAccessDoorDistance = 34;
        static final int TRANSACTION_setCallElevatorDistance = 26;
        static final int TRANSACTION_setHeavyloadMode = 30;
        static final int TRANSACTION_setReplanWaitTime = 15;
        static final int TRANSACTION_setRoadBlockTime = 16;
        static final int TRANSACTION_setSmoothRunAndStopMode = 38;
        static final int TRANSACTION_swithSpeedLevel = 12;
        static final int TRANSACTION_taskStop = 45;
        static final int TRANSACTION_updateTrayDis = 48;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MoveActionInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MoveActionInterface)) {
                return (MoveActionInterface) queryLocalInterface;
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
                    List<Destination> destinations = getDestinations();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(destinations);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Destination> specifyMapDestinations = getSpecifyMapDestinations(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(specifyMapDestinations);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean goTo = goTo(parcel.readString(), parcel.readInt() != 0 ? MoveTaskMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(goTo ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean goHome = goHome(parcel.readString(), parcel.readInt() != 0 ? MoveTaskMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(goHome ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean goHomeFill = goHomeFill(parcel.readString(), parcel.readInt() != 0 ? MoveTaskMode.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(goHomeFill ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    goCruisePath(parcel.readInt(), parcel.readInt() != 0 ? MoveTaskMode.INSTANCE.createFromParcel(parcel) : null, parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    resume();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    rotate(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DestinationWithAccRange> destinationsOrderWithRange = destinationsOrderWithRange(parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(destinationsOrderWithRange);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    DestinationWithAccRange nearestDestination = nearestDestination(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    if (nearestDestination != null) {
                        parcel2.writeInt(1);
                        nearestDestination.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean swithSpeedLevel = swithSpeedLevel(parcel.readInt() != 0 ? MoveMode.INSTANCE.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(swithSpeedLevel ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] speedLevels = getSpeedLevels();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(speedLevels);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String speedLevel = getSpeedLevel(parcel.readInt() != 0 ? MoveMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(speedLevel);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean replanWaitTime = setReplanWaitTime(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(replanWaitTime ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean roadBlockTime = setRoadBlockTime(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(roadBlockTime ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int roadBlockTime2 = getRoadBlockTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(roadBlockTime2);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableStuckReplan(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableDynamicRoadblock(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    addFillInStateListener(parcel.readString(), FillInStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeFillInStateListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    quitFillIn();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearDropEvent();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    addElevetorRequestListener(parcel.readString(), ElevatorRequestListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeElevatorRequestListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCallElevatorDistance(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    double callElevatorDistance = getCallElevatorDistance();
                    parcel2.writeNoException();
                    parcel2.writeDouble(callElevatorDistance);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean addCoverAround = addCoverAround(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(addCoverAround ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean coverAround = getCoverAround();
                    parcel2.writeNoException();
                    parcel2.writeInt(coverAround ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHeavyloadMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean heavyloadMode = getHeavyloadMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(heavyloadMode ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAccessDoorStateListener(parcel.readString(), AccessDoorListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAccessDoorStateListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCallAccessDoorDistance(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    double callAccessDoorDistance = getCallAccessDoorDistance();
                    parcel2.writeNoException();
                    parcel2.writeDouble(callAccessDoorDistance);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> chargingPiles = getChargingPiles();
                    parcel2.writeNoException();
                    parcel2.writeStringList(chargingPiles);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    goChargingPile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSmoothRunAndStopMode(parcel.readInt() != 0 ? SmoothMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    SmoothMode smoothRunAndStopMode = getSmoothRunAndStopMode();
                    parcel2.writeNoException();
                    if (smoothRunAndStopMode != null) {
                        parcel2.writeInt(1);
                        smoothRunAndStopMode.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCliffDistanceStateListener(parcel.readString(), CliffDistanceStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCliffDistanceStateListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMapAreaDetectionListener(parcel.readString(), MapAreaDetectionListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeMapAreaDetectionListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ChargingPileInfo> chargingPileInfos = getChargingPileInfos();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(chargingPileInfos);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    taskStop();
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isNearByChangePile = isNearByChangePile();
                    parcel2.writeNoException();
                    parcel2.writeInt(isNearByChangePile ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    passAccessDoor();
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateTrayDis(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    double trayDis = getTrayDis();
                    parcel2.writeNoException();
                    parcel2.writeDouble(trayDis);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes5.dex */
        private static class Proxy implements MoveActionInterface {
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

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public List<Destination> getDestinations() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Destination.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public List<Destination> getSpecifyMapDestinations(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Destination.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean goTo(String str, MoveTaskMode moveTaskMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (moveTaskMode != null) {
                        obtain.writeInt(1);
                        moveTaskMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean goHome(String str, MoveTaskMode moveTaskMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (moveTaskMode != null) {
                        obtain.writeInt(1);
                        moveTaskMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean goHomeFill(String str, MoveTaskMode moveTaskMode, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (moveTaskMode != null) {
                        obtain.writeInt(1);
                        moveTaskMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void goCruisePath(int i, MoveTaskMode moveTaskMode, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (moveTaskMode != null) {
                        obtain.writeInt(1);
                        moveTaskMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringList(list);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void pause() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void resume() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void rotate(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public List<DestinationWithAccRange> destinationsOrderWithRange(List<String> list, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DestinationWithAccRange.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public DestinationWithAccRange nearestDestination(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DestinationWithAccRange.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean swithSpeedLevel(MoveMode moveMode, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (moveMode != null) {
                        obtain.writeInt(1);
                        moveMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public String[] getSpeedLevels() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public String getSpeedLevel(MoveMode moveMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (moveMode != null) {
                        obtain.writeInt(1);
                        moveMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean setReplanWaitTime(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean setRoadBlockTime(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public int getRoadBlockTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void enableStuckReplan(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void enableDynamicRoadblock(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void addFillInStateListener(String str, FillInStateListener fillInStateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(fillInStateListener != null ? fillInStateListener.asBinder() : null);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void removeFillInStateListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void quitFillIn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void clearDropEvent() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void addElevetorRequestListener(String str, ElevatorRequestListener elevatorRequestListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(elevatorRequestListener != null ? elevatorRequestListener.asBinder() : null);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void removeElevatorRequestListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void setCallElevatorDistance(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public double getCallElevatorDistance() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean addCoverAround(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean getCoverAround() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void setHeavyloadMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean getHeavyloadMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void addAccessDoorStateListener(String str, AccessDoorListener accessDoorListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(accessDoorListener != null ? accessDoorListener.asBinder() : null);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void removeAccessDoorStateListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void setCallAccessDoorDistance(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public double getCallAccessDoorDistance() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public List<String> getChargingPiles() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void goChargingPile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void setSmoothRunAndStopMode(SmoothMode smoothMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (smoothMode != null) {
                        obtain.writeInt(1);
                        smoothMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public SmoothMode getSmoothRunAndStopMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? SmoothMode.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void addCliffDistanceStateListener(String str, CliffDistanceStateListener cliffDistanceStateListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(cliffDistanceStateListener != null ? cliffDistanceStateListener.asBinder() : null);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void removeCliffDistanceStateListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void addMapAreaDetectionListener(String str, MapAreaDetectionListener mapAreaDetectionListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(mapAreaDetectionListener != null ? mapAreaDetectionListener.asBinder() : null);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void removeMapAreaDetectionListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public List<ChargingPileInfo> getChargingPileInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ChargingPileInfo.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void taskStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public boolean isNearByChangePile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void passAccessDoor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public void updateTrayDis(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.MoveActionInterface
            public double getTrayDis() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
