package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.AreaDetectInterface;
import com.pudutech.mirsdk.mircore.DockerEstimateTransformListener;
import com.pudutech.mirsdk.mircore.FaceDetectListener;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ReloadMapResultListener;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.aidl.CliffInfoListener;
import com.pudutech.mirsdk.mircore.aidl.ReflectorInfoListener;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public interface MirCoreInterface extends IInterface {
    PathSegments acrossFloorPathSegment(String str) throws RemoteException;

    void addCliffDistanceListener(String str, CliffInfoListener cliffInfoListener) throws RemoteException;

    void addDockerEstimateTransformListener(String str, DockerEstimateTransformListener dockerEstimateTransformListener) throws RemoteException;

    void addFaceDetectListener(String str, FaceDetectListener faceDetectListener) throws RemoteException;

    void addReflectorDistanceListener(String str, ReflectorInfoListener reflectorInfoListener) throws RemoteException;

    PathSegments currentFloorPathSegment(String str) throws RemoteException;

    DockerDetectResult detectChargeDocker() throws RemoteException;

    void enableFaceDetect(boolean z) throws RemoteException;

    AreaDetectInterface getAreaDetect() throws RemoteException;

    String getGitHash() throws RemoteException;

    LocalizationInterface getLocalizer() throws RemoteException;

    NavigationInterface getNavigator() throws RemoteException;

    double getPlanPathWeight(Vector3d vector3d, double d, double d2) throws RemoteException;

    ScheduleInterface getScheduler() throws RemoteException;

    boolean hasCoreReady() throws RemoteException;

    void initModules(int i, String str, List<String> list, InitServiceListener initServiceListener) throws RemoteException;

    boolean isArriveCirclePath() throws RemoteException;

    List<DestinationWithAccRange> orderDestinationWithRange(List<String> list, boolean z) throws RemoteException;

    void reloadPdmap(int i, String str, List<String> list, ReloadMapResultListener reloadMapResultListener) throws RemoteException;

    void removeCliffDistanceListener(String str) throws RemoteException;

    void removeDockerEstimateTransformListener(String str) throws RemoteException;

    void removeFaceDetectListener(String str) throws RemoteException;

    void removeReflectorDistanceListener(String str) throws RemoteException;

    void setDropDetSwitch(boolean z) throws RemoteException;

    void setRefletorSwitch(boolean z) throws RemoteException;

    void switchAutoExposure(boolean z) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements MirCoreInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.MirCoreInterface";
        static final int TRANSACTION_acrossFloorPathSegment = 9;
        static final int TRANSACTION_addCliffDistanceListener = 21;
        static final int TRANSACTION_addDockerEstimateTransformListener = 25;
        static final int TRANSACTION_addFaceDetectListener = 15;
        static final int TRANSACTION_addReflectorDistanceListener = 23;
        static final int TRANSACTION_currentFloorPathSegment = 8;
        static final int TRANSACTION_detectChargeDocker = 18;
        static final int TRANSACTION_enableFaceDetect = 17;
        static final int TRANSACTION_getAreaDetect = 4;
        static final int TRANSACTION_getGitHash = 12;
        static final int TRANSACTION_getLocalizer = 2;
        static final int TRANSACTION_getNavigator = 1;
        static final int TRANSACTION_getPlanPathWeight = 7;
        static final int TRANSACTION_getScheduler = 3;
        static final int TRANSACTION_hasCoreReady = 11;
        static final int TRANSACTION_initModules = 5;
        static final int TRANSACTION_isArriveCirclePath = 13;
        static final int TRANSACTION_orderDestinationWithRange = 10;
        static final int TRANSACTION_reloadPdmap = 6;
        static final int TRANSACTION_removeCliffDistanceListener = 22;
        static final int TRANSACTION_removeDockerEstimateTransformListener = 26;
        static final int TRANSACTION_removeFaceDetectListener = 16;
        static final int TRANSACTION_removeReflectorDistanceListener = 24;
        static final int TRANSACTION_setDropDetSwitch = 19;
        static final int TRANSACTION_setRefletorSwitch = 20;
        static final int TRANSACTION_switchAutoExposure = 14;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MirCoreInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MirCoreInterface)) {
                return (MirCoreInterface) queryLocalInterface;
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
                    NavigationInterface navigator = getNavigator();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(navigator != null ? navigator.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    LocalizationInterface localizer = getLocalizer();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(localizer != null ? localizer.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    ScheduleInterface scheduler = getScheduler();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(scheduler != null ? scheduler.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    AreaDetectInterface areaDetect = getAreaDetect();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(areaDetect != null ? areaDetect.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    initModules(parcel.readInt(), parcel.readString(), parcel.createStringArrayList(), InitServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    reloadPdmap(parcel.readInt(), parcel.readString(), parcel.createStringArrayList(), ReloadMapResultListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    double planPathWeight = getPlanPathWeight(parcel.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(parcel) : null, parcel.readDouble(), parcel.readDouble());
                    parcel2.writeNoException();
                    parcel2.writeDouble(planPathWeight);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    PathSegments currentFloorPathSegment = currentFloorPathSegment(parcel.readString());
                    parcel2.writeNoException();
                    if (currentFloorPathSegment != null) {
                        parcel2.writeInt(1);
                        currentFloorPathSegment.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    PathSegments acrossFloorPathSegment = acrossFloorPathSegment(parcel.readString());
                    parcel2.writeNoException();
                    if (acrossFloorPathSegment != null) {
                        parcel2.writeInt(1);
                        acrossFloorPathSegment.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DestinationWithAccRange> orderDestinationWithRange = orderDestinationWithRange(parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(orderDestinationWithRange);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasCoreReady = hasCoreReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasCoreReady ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String gitHash = getGitHash();
                    parcel2.writeNoException();
                    parcel2.writeString(gitHash);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isArriveCirclePath = isArriveCirclePath();
                    parcel2.writeNoException();
                    parcel2.writeInt(isArriveCirclePath ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchAutoExposure(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    addFaceDetectListener(parcel.readString(), FaceDetectListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeFaceDetectListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableFaceDetect(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    DockerDetectResult detectChargeDocker = detectChargeDocker();
                    parcel2.writeNoException();
                    if (detectChargeDocker != null) {
                        parcel2.writeInt(1);
                        detectChargeDocker.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDropDetSwitch(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRefletorSwitch(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCliffDistanceListener(parcel.readString(), CliffInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeCliffDistanceListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    addReflectorDistanceListener(parcel.readString(), ReflectorInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeReflectorDistanceListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDockerEstimateTransformListener(parcel.readString(), DockerEstimateTransformListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDockerEstimateTransformListener(parcel.readString());
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
        private static class Proxy implements MirCoreInterface {
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

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public NavigationInterface getNavigator() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return NavigationInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public LocalizationInterface getLocalizer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return LocalizationInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public ScheduleInterface getScheduler() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return ScheduleInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public AreaDetectInterface getAreaDetect() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return AreaDetectInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void initModules(int i, String str, List<String> list, InitServiceListener initServiceListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(initServiceListener != null ? initServiceListener.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void reloadPdmap(int i, String str, List<String> list, ReloadMapResultListener reloadMapResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(reloadMapResultListener != null ? reloadMapResultListener.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public double getPlanPathWeight(Vector3d vector3d, double d, double d2) throws RemoteException {
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
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public PathSegments currentFloorPathSegment(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? PathSegments.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public PathSegments acrossFloorPathSegment(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? PathSegments.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public List<DestinationWithAccRange> orderDestinationWithRange(List<String> list, boolean z) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public boolean hasCoreReady() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public String getGitHash() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public boolean isArriveCirclePath() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void switchAutoExposure(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void addFaceDetectListener(String str, FaceDetectListener faceDetectListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(faceDetectListener != null ? faceDetectListener.asBinder() : null);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void removeFaceDetectListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void enableFaceDetect(boolean z) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public DockerDetectResult detectChargeDocker() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DockerDetectResult.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void setDropDetSwitch(boolean z) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void setRefletorSwitch(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void addCliffDistanceListener(String str, CliffInfoListener cliffInfoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(cliffInfoListener != null ? cliffInfoListener.asBinder() : null);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void removeCliffDistanceListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void addReflectorDistanceListener(String str, ReflectorInfoListener reflectorInfoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(reflectorInfoListener != null ? reflectorInfoListener.asBinder() : null);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void removeReflectorDistanceListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void addDockerEstimateTransformListener(String str, DockerEstimateTransformListener dockerEstimateTransformListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(dockerEstimateTransformListener != null ? dockerEstimateTransformListener.asBinder() : null);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.MirCoreInterface
            public void removeDockerEstimateTransformListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
