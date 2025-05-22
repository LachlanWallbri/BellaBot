package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.iflytek.speech.SpeechUnderstanderListener;

/* loaded from: classes3.dex */
public interface ISpeechUnderstander extends IInterface {
    void cancel(SpeechUnderstanderListener speechUnderstanderListener) throws RemoteException;

    boolean isUnderstanding() throws RemoteException;

    void startUnderstanding(Intent intent, SpeechUnderstanderListener speechUnderstanderListener) throws RemoteException;

    void stopUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) throws RemoteException;

    void writeAudio(Intent intent, byte[] bArr, int i, int i2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISpeechUnderstander {
        private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ISpeechUnderstander";
        static final int TRANSACTION_cancel = 3;
        static final int TRANSACTION_isUnderstanding = 4;
        static final int TRANSACTION_startUnderstanding = 1;
        static final int TRANSACTION_stopUnderstanding = 2;
        static final int TRANSACTION_writeAudio = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpeechUnderstander asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISpeechUnderstander)) {
                return (ISpeechUnderstander) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                startUnderstanding(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, SpeechUnderstanderListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                stopUnderstanding(SpeechUnderstanderListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                cancel(SpeechUnderstanderListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isUnderstanding = isUnderstanding();
                parcel2.writeNoException();
                parcel2.writeInt(isUnderstanding ? 1 : 0);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            writeAudio(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes3.dex */
        private static class Proxy implements ISpeechUnderstander {
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

            @Override // com.iflytek.speech.aidl.ISpeechUnderstander
            public void startUnderstanding(Intent intent, SpeechUnderstanderListener speechUnderstanderListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(speechUnderstanderListener != null ? speechUnderstanderListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechUnderstander
            public void stopUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(speechUnderstanderListener != null ? speechUnderstanderListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechUnderstander
            public void cancel(SpeechUnderstanderListener speechUnderstanderListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(speechUnderstanderListener != null ? speechUnderstanderListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechUnderstander
            public boolean isUnderstanding() throws RemoteException {
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

            @Override // com.iflytek.speech.aidl.ISpeechUnderstander
            public void writeAudio(Intent intent, byte[] bArr, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
