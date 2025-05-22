package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.ScheduleListener;
import com.pudutech.mirsdk.mircore.coreparcel.BusinessType;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public interface ScheduleInterface extends IInterface {
    void addScheduleListener(String str, ScheduleListener scheduleListener) throws RemoteException;

    DestinationWithAccRange findNextGateOnPath() throws RemoteException;

    void noticeTaskStart() throws RemoteException;

    void noticeTaskStuck() throws RemoteException;

    boolean prepareDeliverTask(Vector3d vector3d) throws RemoteException;

    boolean prepareGoHomeTask(String str, BusinessType businessType) throws RemoteException;

    void quitFillIn() throws RemoteException;

    void removeScheduleListener(String str) throws RemoteException;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements ScheduleInterface {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.ScheduleInterface";
        static final int TRANSACTION_addScheduleListener = 1;
        static final int TRANSACTION_findNextGateOnPath = 8;
        static final int TRANSACTION_noticeTaskStart = 3;
        static final int TRANSACTION_noticeTaskStuck = 4;
        static final int TRANSACTION_prepareDeliverTask = 6;
        static final int TRANSACTION_prepareGoHomeTask = 7;
        static final int TRANSACTION_quitFillIn = 5;
        static final int TRANSACTION_removeScheduleListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ScheduleInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ScheduleInterface)) {
                return (ScheduleInterface) queryLocalInterface;
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
                    addScheduleListener(parcel.readString(), ScheduleListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeScheduleListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    noticeTaskStart();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    noticeTaskStuck();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    quitFillIn();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareDeliverTask = prepareDeliverTask(parcel.readInt() != 0 ? Vector3d.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareDeliverTask ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean prepareGoHomeTask = prepareGoHomeTask(parcel.readString(), parcel.readInt() != 0 ? BusinessType.INSTANCE.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(prepareGoHomeTask ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    DestinationWithAccRange findNextGateOnPath = findNextGateOnPath();
                    parcel2.writeNoException();
                    if (findNextGateOnPath != null) {
                        parcel2.writeInt(1);
                        findNextGateOnPath.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        /* loaded from: classes6.dex */
        private static class Proxy implements ScheduleInterface {
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

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public void addScheduleListener(String str, ScheduleListener scheduleListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(scheduleListener != null ? scheduleListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public void removeScheduleListener(String str) throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public void noticeTaskStart() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public void noticeTaskStuck() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public void quitFillIn() throws RemoteException {
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

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public boolean prepareDeliverTask(Vector3d vector3d) throws RemoteException {
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
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public boolean prepareGoHomeTask(String str, BusinessType businessType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (businessType != null) {
                        obtain.writeInt(1);
                        businessType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
            public DestinationWithAccRange findNextGateOnPath() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DestinationWithAccRange.INSTANCE.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
