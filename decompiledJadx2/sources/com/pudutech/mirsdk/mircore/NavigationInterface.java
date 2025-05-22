package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.NavigationResultListener;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mircore.coreparcel.RotateResult;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public interface NavigationInterface extends IInterface {
    boolean addCoverAround(boolean z) throws RemoteException;

    void enableDynamicRoadblock(boolean z) throws RemoteException;

    void enableStuckReplan(boolean z) throws RemoteException;

    Vector3d getAimPoint() throws RemoteException;

    boolean getCoverAround() throws RemoteException;

    int getReplanWaitTime() throws RemoteException;

    int getRoadblockTimeout() throws RemoteException;

    SmoothMode getSmoothMode() throws RemoteException;

    String getSpeedLevel(MoveMode moveMode) throws RemoteException;

    String[] getSpeedLevels() throws RemoteException;

    double getTrayDis() throws RemoteException;

    NavigationResult navigation(NavigationMode navigationMode, boolean z) throws RemoteException;

    void pauseNavigation() throws RemoteException;

    boolean prepareAutoChargeTask(Vector3d vector3d) throws RemoteException;

    void prepareCruiseTask(int i, List<String> list) throws RemoteException;

    boolean prepareDeliverTask(boolean z, boolean z2) throws RemoteException;

    boolean prepareGoHomeTask() throws RemoteException;

    boolean prepareLeaveChargeTask() throws RemoteException;

    boolean prepareMoveToChargeTask() throws RemoteException;

    void resetChargeTimes() throws RemoteException;

    void resetNavigationFlag() throws RemoteException;

    RotateResult rotate(Vector3d vector3d) throws RemoteException;

    NavigationResult safelyStop() throws RemoteException;

    boolean setMovingSpeedTask(double d) throws RemoteException;

    boolean setReplanWaitTime(int i) throws RemoteException;

    boolean setRoadblockTimeout(int i) throws RemoteException;

    void startNavigation(NavigationResultListener navigationResultListener, NavigationMode navigationMode, boolean z) throws RemoteException;

    boolean switchSpeedLevel(MoveMode moveMode, String str) throws RemoteException;

    void updateDynamicConfig(boolean z) throws RemoteException;

    void updateGateLimitSpeed(double d) throws RemoteException;

    void updateSmoothMode(SmoothMode smoothMode) throws RemoteException;

    void updateSteadyFlag(boolean z) throws RemoteException;

    void updateTrayDis(double d) throws RemoteException;

    void uploadCliffCenterIr() throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements NavigationInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.NavigationInterface";
        static final int TRANSACTION_addCoverAround = 23;
        static final int TRANSACTION_enableDynamicRoadblock = 18;
        static final int TRANSACTION_enableStuckReplan = 17;
        static final int TRANSACTION_getAimPoint = 21;
        static final int TRANSACTION_getCoverAround = 24;
        static final int TRANSACTION_getReplanWaitTime = 14;
        static final int TRANSACTION_getRoadblockTimeout = 16;
        static final int TRANSACTION_getSmoothMode = 26;
        static final int TRANSACTION_getSpeedLevel = 12;
        static final int TRANSACTION_getSpeedLevels = 11;
        static final int TRANSACTION_getTrayDis = 33;
        static final int TRANSACTION_navigation = 19;
        static final int TRANSACTION_pauseNavigation = 29;
        static final int TRANSACTION_prepareAutoChargeTask = 5;
        static final int TRANSACTION_prepareCruiseTask = 1;
        static final int TRANSACTION_prepareDeliverTask = 2;
        static final int TRANSACTION_prepareGoHomeTask = 3;
        static final int TRANSACTION_prepareLeaveChargeTask = 6;
        static final int TRANSACTION_prepareMoveToChargeTask = 4;
        static final int TRANSACTION_resetChargeTimes = 31;
        static final int TRANSACTION_resetNavigationFlag = 8;
        static final int TRANSACTION_rotate = 22;
        static final int TRANSACTION_safelyStop = 20;
        static final int TRANSACTION_setMovingSpeedTask = 7;
        static final int TRANSACTION_setReplanWaitTime = 13;
        static final int TRANSACTION_setRoadblockTimeout = 15;
        static final int TRANSACTION_startNavigation = 28;
        static final int TRANSACTION_switchSpeedLevel = 10;
        static final int TRANSACTION_updateDynamicConfig = 9;
        static final int TRANSACTION_updateGateLimitSpeed = 34;
        static final int TRANSACTION_updateSmoothMode = 27;
        static final int TRANSACTION_updateSteadyFlag = 25;
        static final int TRANSACTION_updateTrayDis = 32;
        static final int TRANSACTION_uploadCliffCenterIr = 30;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static NavigationInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof NavigationInterface)) {
                return (NavigationInterface) queryLocalInterface;
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
                    prepareCruiseTask(parcel.readInt(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareDeliverTask = prepareDeliverTask(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareDeliverTask ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareGoHomeTask = prepareGoHomeTask();
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareGoHomeTask ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareMoveToChargeTask = prepareMoveToChargeTask();
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareMoveToChargeTask ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareAutoChargeTask = prepareAutoChargeTask(parcel.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareAutoChargeTask ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareLeaveChargeTask = prepareLeaveChargeTask();
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareLeaveChargeTask ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean movingSpeedTask = setMovingSpeedTask(parcel.readDouble());
                    parcel2.writeNoException();
                    parcel2.writeInt(movingSpeedTask ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetNavigationFlag();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateDynamicConfig(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean switchSpeedLevel = switchSpeedLevel(parcel.readInt() != 0 ? MoveMode.INSTANCE.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(switchSpeedLevel ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] speedLevels = getSpeedLevels();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(speedLevels);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String speedLevel = getSpeedLevel(parcel.readInt() != 0 ? MoveMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(speedLevel);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean replanWaitTime = setReplanWaitTime(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(replanWaitTime ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int replanWaitTime2 = getReplanWaitTime();
                    parcel2.writeNoException();
                    parcel2.writeInt(replanWaitTime2);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean roadblockTimeout = setRoadblockTimeout(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(roadblockTimeout ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int roadblockTimeout2 = getRoadblockTimeout();
                    parcel2.writeNoException();
                    parcel2.writeInt(roadblockTimeout2);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableStuckReplan(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableDynamicRoadblock(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    NavigationResult navigation = navigation(parcel.readInt() != 0 ? NavigationMode.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (navigation != null) {
                        parcel2.writeInt(1);
                        navigation.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    NavigationResult safelyStop = safelyStop();
                    parcel2.writeNoException();
                    if (safelyStop != null) {
                        parcel2.writeInt(1);
                        safelyStop.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    Vector3d aimPoint = getAimPoint();
                    parcel2.writeNoException();
                    if (aimPoint != null) {
                        parcel2.writeInt(1);
                        aimPoint.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    RotateResult rotate = rotate(parcel.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (rotate != null) {
                        parcel2.writeInt(1);
                        rotate.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean addCoverAround = addCoverAround(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(addCoverAround ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean coverAround = getCoverAround();
                    parcel2.writeNoException();
                    parcel2.writeInt(coverAround ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateSteadyFlag(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    SmoothMode smoothMode = getSmoothMode();
                    parcel2.writeNoException();
                    if (smoothMode != null) {
                        parcel2.writeInt(1);
                        smoothMode.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateSmoothMode(parcel.readInt() != 0 ? SmoothMode.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    startNavigation(NavigationResultListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? NavigationMode.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseNavigation();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadCliffCenterIr();
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetChargeTimes();
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateTrayDis(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    double trayDis = getTrayDis();
                    parcel2.writeNoException();
                    parcel2.writeDouble(trayDis);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateGateLimitSpeed(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes6.dex */
        private static class Proxy implements NavigationInterface {
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

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void prepareCruiseTask(int i, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean prepareDeliverTask(boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean prepareGoHomeTask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean prepareMoveToChargeTask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean prepareAutoChargeTask(Vector3d vector3d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vector3d != null) {
                        obtain.writeInt(1);
                        vector3d.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean prepareLeaveChargeTask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean setMovingSpeedTask(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void resetNavigationFlag() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void updateDynamicConfig(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean switchSpeedLevel(MoveMode moveMode, String str) throws RemoteException {
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
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public String[] getSpeedLevels() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
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
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean setReplanWaitTime(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public int getReplanWaitTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean setRoadblockTimeout(int i) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public int getRoadblockTimeout() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void enableStuckReplan(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void enableDynamicRoadblock(boolean z) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public NavigationResult navigation(NavigationMode navigationMode, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (navigationMode != null) {
                        obtain.writeInt(1);
                        navigationMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? NavigationResult.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public NavigationResult safelyStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? NavigationResult.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public Vector3d getAimPoint() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public RotateResult rotate(Vector3d vector3d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vector3d != null) {
                        obtain.writeInt(1);
                        vector3d.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? RotateResult.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean addCoverAround(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public boolean getCoverAround() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void updateSteadyFlag(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public SmoothMode getSmoothMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? SmoothMode.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void updateSmoothMode(SmoothMode smoothMode) throws RemoteException {
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
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void startNavigation(NavigationResultListener navigationResultListener, NavigationMode navigationMode, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(navigationResultListener != null ? navigationResultListener.asBinder() : null);
                    int i = 1;
                    if (navigationMode != null) {
                        obtain.writeInt(1);
                        navigationMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void pauseNavigation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void uploadCliffCenterIr() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void resetChargeTimes() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void updateTrayDis(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public double getTrayDis() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.NavigationInterface
            public void updateGateLimitSpeed(double d) throws RemoteException {
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
        }
    }
}
