package com.pudutech.mirsdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.aidl.BluetoothChargeInterface;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.LocateCameraCalibListener;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
import com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.aidl.serialize.MapPackageConfig;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public interface SDKInterface extends IInterface {
    void addBluetoothChargePile(String str, String str2, DockerDetectResult dockerDetectResult) throws RemoteException;

    void addChargePile(String str, DockerDetectResult dockerDetectResult) throws RemoteException;

    void addLisener(String str, ISDKListener iSDKListener) throws RemoteException;

    void addRelocationPoint(String str) throws RemoteException;

    void calibrationMarkerCamera(int i, LocateCameraCalibListener locateCameraCalibListener) throws RemoteException;

    boolean checkLegalMap(String str) throws RemoteException;

    void closeAuthCheck() throws RemoteException;

    void controlBatteryLevel(int i) throws RemoteException;

    void controlFaceDetect(boolean z) throws RemoteException;

    int controlWheelErrorEvent(WheelError wheelError, boolean z) throws RemoteException;

    String defaultDiningOutlet() throws RemoteException;

    DockerDetectResult detectChargePile() throws RemoteException;

    boolean enableDropDet(boolean z) throws RemoteException;

    void enableReflector(boolean z) throws RemoteException;

    AccessControlServer getAccessControlServer() throws RemoteException;

    void getBatteryLevel() throws RemoteException;

    BluetoothChargeInterface getBluetoothChargeInterface() throws RemoteException;

    DeviceInterface getDeviceInterface() throws RemoteException;

    boolean getDropDetStatus() throws RemoteException;

    ElevatorConnectionType getElevatorConnection() throws RemoteException;

    String getGitHash() throws RemoteException;

    String getHardwareVersion() throws RemoteException;

    boolean getInstallMode() throws RemoteException;

    LocalizationStatus getLocalizationStatus() throws RemoteException;

    LocateCase getLocateCase() throws RemoteException;

    MachineInfo getMachineInfo() throws RemoteException;

    List<MapInfo> getMapInfoList() throws RemoteException;

    MoveActionInterface getMoveActionInterface() throws RemoteException;

    MapPackageConfig getPdmapNameList() throws RemoteException;

    List<String> getRelocationPoints() throws RemoteException;

    boolean getSlipControlStatus() throws RemoteException;

    void init(LocateCase locateCase) throws RemoteException;

    boolean isLocalizationFinishInitialization() throws RemoteException;

    boolean isLocated() throws RemoteException;

    boolean isRelocalizationSuccess() throws RemoteException;

    boolean openSlipControl(boolean z) throws RemoteException;

    void reloadLocalization() throws RemoteException;

    void reloadLocalizationByChargingPile(ChargingPileInfo chargingPileInfo) throws RemoteException;

    void reloadMap(String str) throws RemoteException;

    void removeListener(String str) throws RemoteException;

    void securitySwitch(boolean z, boolean z2) throws RemoteException;

    void setAccessControlServer(AccessControlServer accessControlServer) throws RemoteException;

    void setElevatorConnection(ElevatorConnectionType elevatorConnectionType) throws RemoteException;

    void setInstallMode(boolean z) throws RemoteException;

    void suspendCharingUsingPile() throws RemoteException;

    void switchDefaultPdmap(String str) throws RemoteException;

    void switchUsingPdmap(String str) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements SDKInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.aidl.SDKInterface";
        static final int TRANSACTION_addBluetoothChargePile = 46;
        static final int TRANSACTION_addChargePile = 39;
        static final int TRANSACTION_addLisener = 1;
        static final int TRANSACTION_addRelocationPoint = 35;
        static final int TRANSACTION_calibrationMarkerCamera = 30;
        static final int TRANSACTION_checkLegalMap = 40;
        static final int TRANSACTION_closeAuthCheck = 9;
        static final int TRANSACTION_controlBatteryLevel = 41;
        static final int TRANSACTION_controlFaceDetect = 29;
        static final int TRANSACTION_controlWheelErrorEvent = 43;
        static final int TRANSACTION_defaultDiningOutlet = 13;
        static final int TRANSACTION_detectChargePile = 38;
        static final int TRANSACTION_enableDropDet = 4;
        static final int TRANSACTION_enableReflector = 6;
        static final int TRANSACTION_getAccessControlServer = 32;
        static final int TRANSACTION_getBatteryLevel = 42;
        static final int TRANSACTION_getBluetoothChargeInterface = 47;
        static final int TRANSACTION_getDeviceInterface = 22;
        static final int TRANSACTION_getDropDetStatus = 5;
        static final int TRANSACTION_getElevatorConnection = 36;
        static final int TRANSACTION_getGitHash = 10;
        static final int TRANSACTION_getHardwareVersion = 25;
        static final int TRANSACTION_getInstallMode = 24;
        static final int TRANSACTION_getLocalizationStatus = 20;
        static final int TRANSACTION_getLocateCase = 27;
        static final int TRANSACTION_getMachineInfo = 26;
        static final int TRANSACTION_getMapInfoList = 15;
        static final int TRANSACTION_getMoveActionInterface = 21;
        static final int TRANSACTION_getPdmapNameList = 14;
        static final int TRANSACTION_getRelocationPoints = 34;
        static final int TRANSACTION_getSlipControlStatus = 8;
        static final int TRANSACTION_init = 3;
        static final int TRANSACTION_isLocalizationFinishInitialization = 17;
        static final int TRANSACTION_isLocated = 18;
        static final int TRANSACTION_isRelocalizationSuccess = 19;
        static final int TRANSACTION_openSlipControl = 7;
        static final int TRANSACTION_reloadLocalization = 28;
        static final int TRANSACTION_reloadLocalizationByChargingPile = 45;
        static final int TRANSACTION_reloadMap = 16;
        static final int TRANSACTION_removeListener = 2;
        static final int TRANSACTION_securitySwitch = 44;
        static final int TRANSACTION_setAccessControlServer = 31;
        static final int TRANSACTION_setElevatorConnection = 37;
        static final int TRANSACTION_setInstallMode = 23;
        static final int TRANSACTION_suspendCharingUsingPile = 33;
        static final int TRANSACTION_switchDefaultPdmap = 12;
        static final int TRANSACTION_switchUsingPdmap = 11;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static SDKInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SDKInterface)) {
                return (SDKInterface) queryLocalInterface;
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
                    addLisener(parcel.readString(), ISDKListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    init(parcel.readInt() != 0 ? LocateCase.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enableDropDet = enableDropDet(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(enableDropDet ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean dropDetStatus = getDropDetStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(dropDetStatus ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableReflector(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean openSlipControl = openSlipControl(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(openSlipControl ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean slipControlStatus = getSlipControlStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(slipControlStatus ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeAuthCheck();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String gitHash = getGitHash();
                    parcel2.writeNoException();
                    parcel2.writeString(gitHash);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchUsingPdmap(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchDefaultPdmap(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String defaultDiningOutlet = defaultDiningOutlet();
                    parcel2.writeNoException();
                    parcel2.writeString(defaultDiningOutlet);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    MapPackageConfig pdmapNameList = getPdmapNameList();
                    parcel2.writeNoException();
                    if (pdmapNameList != null) {
                        parcel2.writeInt(1);
                        pdmapNameList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<MapInfo> mapInfoList = getMapInfoList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(mapInfoList);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    reloadMap(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isLocalizationFinishInitialization = isLocalizationFinishInitialization();
                    parcel2.writeNoException();
                    parcel2.writeInt(isLocalizationFinishInitialization ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isLocated = isLocated();
                    parcel2.writeNoException();
                    parcel2.writeInt(isLocated ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isRelocalizationSuccess = isRelocalizationSuccess();
                    parcel2.writeNoException();
                    parcel2.writeInt(isRelocalizationSuccess ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    LocalizationStatus localizationStatus = getLocalizationStatus();
                    parcel2.writeNoException();
                    if (localizationStatus != null) {
                        parcel2.writeInt(1);
                        localizationStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    MoveActionInterface moveActionInterface = getMoveActionInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(moveActionInterface != null ? moveActionInterface.asBinder() : null);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    DeviceInterface deviceInterface = getDeviceInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(deviceInterface != null ? deviceInterface.asBinder() : null);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInstallMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean installMode = getInstallMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(installMode ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String hardwareVersion = getHardwareVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(hardwareVersion);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    MachineInfo machineInfo = getMachineInfo();
                    parcel2.writeNoException();
                    if (machineInfo != null) {
                        parcel2.writeInt(1);
                        machineInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    LocateCase locateCase = getLocateCase();
                    parcel2.writeNoException();
                    if (locateCase != null) {
                        parcel2.writeInt(1);
                        locateCase.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    reloadLocalization();
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlFaceDetect(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    calibrationMarkerCamera(parcel.readInt(), LocateCameraCalibListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAccessControlServer(parcel.readInt() != 0 ? AccessControlServer.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    AccessControlServer accessControlServer = getAccessControlServer();
                    parcel2.writeNoException();
                    if (accessControlServer != null) {
                        parcel2.writeInt(1);
                        accessControlServer.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    suspendCharingUsingPile();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> relocationPoints = getRelocationPoints();
                    parcel2.writeNoException();
                    parcel2.writeStringList(relocationPoints);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    addRelocationPoint(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    ElevatorConnectionType elevatorConnection = getElevatorConnection();
                    parcel2.writeNoException();
                    if (elevatorConnection != null) {
                        parcel2.writeInt(1);
                        elevatorConnection.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setElevatorConnection(parcel.readInt() != 0 ? ElevatorConnectionType.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    DockerDetectResult detectChargePile = detectChargePile();
                    parcel2.writeNoException();
                    if (detectChargePile != null) {
                        parcel2.writeInt(1);
                        detectChargePile.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    addChargePile(parcel.readString(), parcel.readInt() != 0 ? DockerDetectResult.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean checkLegalMap = checkLegalMap(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkLegalMap ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    controlBatteryLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    getBatteryLevel();
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    int controlWheelErrorEvent = controlWheelErrorEvent(parcel.readInt() != 0 ? WheelError.INSTANCE.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(controlWheelErrorEvent);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    securitySwitch(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    reloadLocalizationByChargingPile(parcel.readInt() != 0 ? ChargingPileInfo.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    addBluetoothChargePile(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? DockerDetectResult.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothChargeInterface bluetoothChargeInterface = getBluetoothChargeInterface();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(bluetoothChargeInterface != null ? bluetoothChargeInterface.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes5.dex */
        public static class Proxy implements SDKInterface {
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

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void addLisener(String str, ISDKListener iSDKListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSDKListener != null ? iSDKListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void removeListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void init(LocateCase locateCase) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (locateCase != null) {
                        obtain.writeInt(1);
                        locateCase.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean enableDropDet(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean getDropDetStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void enableReflector(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean openSlipControl(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean getSlipControlStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void closeAuthCheck() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public String getGitHash() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void switchUsingPdmap(String str) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void switchDefaultPdmap(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public String defaultDiningOutlet() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public MapPackageConfig getPdmapNameList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MapPackageConfig.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public List<MapInfo> getMapInfoList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(MapInfo.INSTANCE);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void reloadMap(String str) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean isLocalizationFinishInitialization() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean isLocated() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean isRelocalizationSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public LocalizationStatus getLocalizationStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? LocalizationStatus.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public MoveActionInterface getMoveActionInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return MoveActionInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public DeviceInterface getDeviceInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return DeviceInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void setInstallMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean getInstallMode() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public String getHardwareVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public MachineInfo getMachineInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MachineInfo.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public LocateCase getLocateCase() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? LocateCase.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void reloadLocalization() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void controlFaceDetect(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void calibrationMarkerCamera(int i, LocateCameraCalibListener locateCameraCalibListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(locateCameraCalibListener != null ? locateCameraCalibListener.asBinder() : null);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void setAccessControlServer(AccessControlServer accessControlServer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accessControlServer != null) {
                        obtain.writeInt(1);
                        accessControlServer.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public AccessControlServer getAccessControlServer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? AccessControlServer.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void suspendCharingUsingPile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public List<String> getRelocationPoints() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void addRelocationPoint(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public ElevatorConnectionType getElevatorConnection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ElevatorConnectionType.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void setElevatorConnection(ElevatorConnectionType elevatorConnectionType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (elevatorConnectionType != null) {
                        obtain.writeInt(1);
                        elevatorConnectionType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public DockerDetectResult detectChargePile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DockerDetectResult.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void addChargePile(String str, DockerDetectResult dockerDetectResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (dockerDetectResult != null) {
                        obtain.writeInt(1);
                        dockerDetectResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public boolean checkLegalMap(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void controlBatteryLevel(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void getBatteryLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public int controlWheelErrorEvent(WheelError wheelError, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (wheelError != null) {
                        obtain.writeInt(1);
                        wheelError.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void securitySwitch(boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void reloadLocalizationByChargingPile(ChargingPileInfo chargingPileInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (chargingPileInfo != null) {
                        obtain.writeInt(1);
                        chargingPileInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public void addBluetoothChargePile(String str, String str2, DockerDetectResult dockerDetectResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (dockerDetectResult != null) {
                        obtain.writeInt(1);
                        dockerDetectResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.aidl.SDKInterface
            public BluetoothChargeInterface getBluetoothChargeInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                    return BluetoothChargeInterface.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
