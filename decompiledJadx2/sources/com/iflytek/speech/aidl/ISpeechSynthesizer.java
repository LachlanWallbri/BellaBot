package com.iflytek.speech.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.iflytek.speech.SynthesizerListener;

/* loaded from: classes3.dex */
public interface ISpeechSynthesizer extends IInterface {
    String getLocalSpeakerList() throws RemoteException;

    boolean isSpeaking() throws RemoteException;

    int pauseSpeaking(SynthesizerListener synthesizerListener) throws RemoteException;

    int resumeSpeaking(SynthesizerListener synthesizerListener) throws RemoteException;

    int startSpeaking(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException;

    int stopSpeaking(SynthesizerListener synthesizerListener) throws RemoteException;

    int synthesizeToUrl(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException;

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISpeechSynthesizer {
        private static final String DESCRIPTOR = "com.iflytek.speech.aidl.ISpeechSynthesizer";
        static final int TRANSACTION_getLocalSpeakerList = 7;
        static final int TRANSACTION_isSpeaking = 6;
        static final int TRANSACTION_pauseSpeaking = 3;
        static final int TRANSACTION_resumeSpeaking = 4;
        static final int TRANSACTION_startSpeaking = 2;
        static final int TRANSACTION_stopSpeaking = 5;
        static final int TRANSACTION_synthesizeToUrl = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpeechSynthesizer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISpeechSynthesizer)) {
                return (ISpeechSynthesizer) queryLocalInterface;
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
                    int synthesizeToUrl = synthesizeToUrl(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(synthesizeToUrl);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int startSpeaking = startSpeaking(parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null, SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(startSpeaking);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pauseSpeaking = pauseSpeaking(SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(pauseSpeaking);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int resumeSpeaking = resumeSpeaking(SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(resumeSpeaking);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int stopSpeaking = stopSpeaking(SynthesizerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(stopSpeaking);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSpeaking = isSpeaking();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSpeaking ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String localSpeakerList = getLocalSpeakerList();
                    parcel2.writeNoException();
                    parcel2.writeString(localSpeakerList);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes3.dex */
        private static class Proxy implements ISpeechSynthesizer {
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

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public int synthesizeToUrl(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException {
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
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public int startSpeaking(Intent intent, SynthesizerListener synthesizerListener) throws RemoteException {
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
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public int pauseSpeaking(SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public int resumeSpeaking(SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public int stopSpeaking(SynthesizerListener synthesizerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(synthesizerListener != null ? synthesizerListener.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public boolean isSpeaking() throws RemoteException {
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

            @Override // com.iflytek.speech.aidl.ISpeechSynthesizer
            public String getLocalSpeakerList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
