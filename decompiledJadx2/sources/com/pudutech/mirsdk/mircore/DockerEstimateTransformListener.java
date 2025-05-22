package com.pudutech.mirsdk.mircore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes6.dex */
public interface DockerEstimateTransformListener extends IInterface {
    void callDockerEstimateTransform(boolean z, double d, double d2, double d3) throws RemoteException;

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements DockerEstimateTransformListener {
        private static final String DESCRIPTOR = "com.pudutech.mirsdk.mircore.DockerEstimateTransformListener";
        static final int TRANSACTION_callDockerEstimateTransform = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static DockerEstimateTransformListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DockerEstimateTransformListener)) {
                return (DockerEstimateTransformListener) queryLocalInterface;
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
            callDockerEstimateTransform(parcel.readInt() != 0, parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes6.dex */
        private static class Proxy implements DockerEstimateTransformListener {
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

            @Override // com.pudutech.mirsdk.mircore.DockerEstimateTransformListener
            public void callDockerEstimateTransform(boolean z, double d, double d2, double d3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeDouble(d3);
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
