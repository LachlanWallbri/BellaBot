package com.pudutech.pdmqtt;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.MqttLogger;
import com.pudutech.pdmqtt.OnConnectStateChangeLis;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.OnPublishCallback;
import java.util.List;

/* loaded from: classes6.dex */
public interface MqttManager extends IInterface {
    void addOnConnectStateChangeLis(String str, String str2, OnConnectStateChangeLis onConnectStateChangeLis) throws RemoteException;

    void addOnMessageChangeListener(String str, String str2, String str3, OnMessageChangeListener onMessageChangeListener) throws RemoteException;

    void fetchClient(String str, String str2, String str3, String str4, String str5, String str6, GetClientCallback getClientCallback) throws RemoteException;

    boolean isConnect(String str) throws RemoteException;

    void publish(String str, String str2, String str3, int i, OnPublishCallback onPublishCallback) throws RemoteException;

    void release(List<String> list) throws RemoteException;

    void removeOnConnectStateChangeLis(String str) throws RemoteException;

    void removeOnMessageChangeListener(String str) throws RemoteException;

    void setLogger(MqttLogger mqttLogger) throws RemoteException;

    void subscribe(String str, String str2, List<String> list, ActionCallback actionCallback) throws RemoteException;

    void unsubscribe(String str, String str2, List<String> list, ActionCallback actionCallback) throws RemoteException;

    /* loaded from: classes6.dex */
    public static abstract class Stub extends Binder implements MqttManager {
        private static final String DESCRIPTOR = "com.pudutech.pdmqtt.MqttManager";
        static final int TRANSACTION_addOnConnectStateChangeLis = 8;
        static final int TRANSACTION_addOnMessageChangeListener = 6;
        static final int TRANSACTION_fetchClient = 1;
        static final int TRANSACTION_isConnect = 4;
        static final int TRANSACTION_publish = 5;
        static final int TRANSACTION_release = 2;
        static final int TRANSACTION_removeOnConnectStateChangeLis = 9;
        static final int TRANSACTION_removeOnMessageChangeListener = 7;
        static final int TRANSACTION_setLogger = 3;
        static final int TRANSACTION_subscribe = 10;
        static final int TRANSACTION_unsubscribe = 11;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MqttManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof MqttManager)) {
                return (MqttManager) queryLocalInterface;
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
                    fetchClient(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), GetClientCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    release(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLogger(MqttLogger.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isConnect = isConnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isConnect ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    publish(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), OnPublishCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    addOnMessageChangeListener(parcel.readString(), parcel.readString(), parcel.readString(), OnMessageChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeOnMessageChangeListener(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    addOnConnectStateChangeLis(parcel.readString(), parcel.readString(), OnConnectStateChangeLis.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeOnConnectStateChangeLis(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    subscribe(parcel.readString(), parcel.readString(), parcel.createStringArrayList(), ActionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsubscribe(parcel.readString(), parcel.readString(), parcel.createStringArrayList(), ActionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes6.dex */
        private static class Proxy implements MqttManager {
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

            @Override // com.pudutech.pdmqtt.MqttManager
            public void fetchClient(String str, String str2, String str3, String str4, String str5, String str6, GetClientCallback getClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeString(str5);
                    obtain.writeString(str6);
                    obtain.writeStrongBinder(getClientCallback != null ? getClientCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void release(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void setLogger(MqttLogger mqttLogger) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(mqttLogger != null ? mqttLogger.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public boolean isConnect(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void publish(String str, String str2, String str3, int i, OnPublishCallback onPublishCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(onPublishCallback != null ? onPublishCallback.asBinder() : null);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void addOnMessageChangeListener(String str, String str2, String str3, OnMessageChangeListener onMessageChangeListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(onMessageChangeListener != null ? onMessageChangeListener.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void removeOnMessageChangeListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void addOnConnectStateChangeLis(String str, String str2, OnConnectStateChangeLis onConnectStateChangeLis) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(onConnectStateChangeLis != null ? onConnectStateChangeLis.asBinder() : null);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void removeOnConnectStateChangeLis(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void subscribe(String str, String str2, List<String> list, ActionCallback actionCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(actionCallback != null ? actionCallback.asBinder() : null);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.pudutech.pdmqtt.MqttManager
            public void unsubscribe(String str, String str2, List<String> list, ActionCallback actionCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(actionCallback != null ? actionCallback.asBinder() : null);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
